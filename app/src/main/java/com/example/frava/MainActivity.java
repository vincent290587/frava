package com.example.frava;

import android.Manifest;
import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.swagger.client.model.Route;
import no.nordicsemi.android.ble.callback.profile.ProfileDataCallback;
import no.nordicsemi.android.ble.data.Data;


public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    public static final String TAG = "MainActivity";

    public static final int SEGMENT_LIST_READY  = 999;
    public static final int INTENT_OAUTH2_START = 1110;
    public static final int INTENT_OAUTH2_DONE  = 1111;

    /**
     * permissions request code
     */
    private final static int REQUEST_CODE_ASK_PERMISSIONS = 1;

    /**
     * Permissions that need to be explicitly requested from end user.
     */
    private static final String[] REQUIRED_SDK_PERMISSIONS = new String[] {
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.BLUETOOTH,
            Manifest.permission.BLUETOOTH_ADMIN,
            Manifest.permission.ACCESS_FINE_LOCATION,
    };

    StravaManager stravaViewModel;
    BluetoothLeService ble_service;
    GpsHandler gps_handler;
    int is_started;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.segments_dashboard, R.id.routes_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        // force creation here
        stravaViewModel = new ViewModelProvider(this).get(StravaManager.class);
        stravaViewModel.m_route_to_send.observe(this, new Observer<Route>() {
            @Override
            public void onChanged(Route route) {
                if (gps_handler != null) {
                    gps_handler.sendRoute(route, ble_service);
                }
            }
        });

        // pass commands from fragment to main activity
        stravaViewModel.m_command.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer command) {
                if (gps_handler != null) {
                    switch (command) {
                        case SEGMENT_LIST_READY:
                        {
                            gps_handler.sendSegmentsListReady(
                                    stravaViewModel.m_seg_list.getValue(), ble_service);
                        } break;
                        default:
                            break;
                    }
                }
            }
        });
    }

    public void startOAuth() {

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        String refresh_token = sharedPref.getString(getString(R.string.refresh_token), "");
        int expires_at = sharedPref.getInt(getString(R.string.expires_at), 0);
        long timestamp = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());

        Log.i(TAG, "connectWithStrava: " + refresh_token);

        //if (refresh_token == null || refresh_token.isEmpty() || expires_at == 0 || expires_at <= timestamp)
        {
            Intent intent = new Intent(this, com.example.frava.OAuthStarter.class);
            intent.putExtra("Code", com.example.frava.OAuthStarter.OAUTH_FIRST_REQUEST_CODE);
            startActivity(intent);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        {
            // Bind to GpsHandler
            Intent intent = new Intent(this, GpsHandler.class);
            bindService(intent, connectionGps, Context.BIND_AUTO_CREATE);
            startService(intent);
        }

        if (Utils.isBleEnabled() == true) {
            // Bind to LocalService
            Intent intent = new Intent(this, BluetoothLeService.class);
            bindService(intent, connectionBle, Context.BIND_AUTO_CREATE);
            startService(intent);
        }

        checkPermissions();
    }

    // Function to check and request permission.
    protected void checkPermissions() {
        final List<String> missingPermissions = new ArrayList<String>();
        // check all required dynamic permissions
        for (final String permission : REQUIRED_SDK_PERMISSIONS) {
            final int result = ContextCompat.checkSelfPermission(this, permission);
            if (result != PackageManager.PERMISSION_GRANTED) {
                missingPermissions.add(permission);
            }
        }
        if (!missingPermissions.isEmpty()) {
            // request all missing permissions
            final String[] permissions = missingPermissions
                    .toArray(new String[missingPermissions.size()]);
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE_ASK_PERMISSIONS);
        } else {
            final int[] grantResults = new int[REQUIRED_SDK_PERMISSIONS.length];
            Arrays.fill(grantResults, PackageManager.PERMISSION_GRANTED);
            onRequestPermissionsResult(REQUEST_CODE_ASK_PERMISSIONS, REQUIRED_SDK_PERMISSIONS,
                    grantResults);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                for (int index = permissions.length - 1; index >= 0; --index) {
                    if (grantResults[index] != PackageManager.PERMISSION_GRANTED) {
                        // exit the app if one permission is not granted
                        Toast.makeText(this, "Required permission '" + permissions[index]
                                + "' not granted, exiting", Toast.LENGTH_LONG).show();
                        finish();
                        return;
                    }
                }
                // all permissions were granted
                //initialize();
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (Utils.isBleEnabled() == true) {
            unbindService(connectionBle);
        }
        unbindService(connectionGps);
        Log.d(TAG, "onDestroy()");
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        Intent intent1 = getIntent();
        int code = intent1.getIntExtra("Code", 0);

        Log.d(TAG, "onResume " + intent1.toString());
        if (code == INTENT_OAUTH2_DONE) {

            Log.i(TAG, "INTENT_OAUTH2_DONE");

            // get the DB !
            if (is_started == 0) {
                Intent intent = new Intent(this, StravaQueries.class);
                startService(intent);
            }

            is_started = 1;
        } else if (code == INTENT_OAUTH2_START) {

            Log.i(TAG, "INTENT_OAUTH2_START");
            startOAuth();

        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            default:
                Log.e(TAG, "wrong request code");
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }

    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private final Observer<String> gps_observer = new Observer<String>() {
        @Override
        public void onChanged(String s) {
//            final TextView textView = findViewById(R.id.section_label);
//            if (db_text != null) {
//                textView.setText(db_text);
//                textView.append("GPS event: \n");
//            } else {
//                textView.setText("GPS event: \n");
//            }
//            textView.append(s);
//            textView.append("\n");
        }
    };

    private final ProfileDataCallback bleCallbacks = new ProfileDataCallback() {

        @Override
        public void onInvalidDataReceived(@NonNull BluetoothDevice device, @NonNull Data data) {
            Log.d(TAG, "Invalid NUS data RECV len=" + data.size());
        }

        @Override
        public void onDataReceived(@NonNull BluetoothDevice device, @NonNull Data data) {

            Log.i(TAG, "NUS data RECV len=" + data.size());
            if (gps_handler != null && ble_service != null) {
                gps_handler.handleCommand(data, ble_service);
            }
        }
    };

    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection connectionBle = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            BluetoothLeService.LocalBinder binder = (BluetoothLeService.LocalBinder) service;
            ble_service = binder.getService();
            ble_service.registerCallbacks(bleCallbacks);
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            ble_service = null;
        }
    };

    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection connectionGps = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            GpsHandler.LocalBinder binder = (GpsHandler.LocalBinder) service;
            gps_handler = binder.getService();
            gps_handler.subscribe(gps_observer);
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            gps_handler = null;
        }
    };

}
