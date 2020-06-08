package com.example.frava;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OAuthStarter extends AppCompatActivity {

    public static final int OAUTH_FIRST_REQUEST_CODE = 1113;
    private static final String TAG = "OAuthStarter";
    RequestQueue queue;

    public OAuthStarter() {

    }

    private void logText(String s) {

        final TextView textview = findViewById(R.id.text_home);

        if (textview != null) {

            CharSequence orig = textview.getText();
            textview.setText(s);
            textview.append(orig);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        queue = Volley.newRequestQueue(this);  // this = context

        Log.d(TAG, "onCreate");

        connectWithStrava();

        //goBackToMain();
    }

    private void goBackToMain() {

        // we are done, go back to the main
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Code", MainActivity.INTENT_OAUTH2_DONE);
        startActivity(intent);
    }

    private void connectWithStrava() {

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        String refresh_token = sharedPref.getString(getString(R.string.refresh_token), "");
        int expires_at = sharedPref.getInt(getString(R.string.expires_at), 0);
        long timestamp = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());

        Log.i(TAG, "connectWithStrava: " + refresh_token);

        if (refresh_token == null || refresh_token.isEmpty() || expires_at == 0) {
            // authorize app through browser
            connectWithStravaFirstTime();
        } else if ((long)expires_at < timestamp) {
            // ask for a new token
            askForNewToken(refresh_token, expires_at);
        } else {
            goBackToMain();
        }

    }

    private void connectWithStravaFirstTime() {

        Log.i(TAG, "connectWithStravaFirstTime");

        Uri intentUri = Uri.parse("https://www.strava.com/oauth/mobile/authorize")
                .buildUpon()
                .appendQueryParameter("client_id", "2940")
                .appendQueryParameter("redirect_uri", "xcomputer://xcomputer")
                .appendQueryParameter("response_type", "code")
                .appendQueryParameter("approval_prompt", "auto")
                .appendQueryParameter("scope", "profile:read_all,activity:write")
                .build();

        Intent intent = new Intent(Intent.ACTION_VIEW, intentUri);
        startActivityForResult(intent, OAUTH_FIRST_REQUEST_CODE);
    }

    private void askForNewToken(final String refresh_token, final int expires_at) {

        Log.i(TAG, "askForNewToken");

        logText("Requesting new token" + '\n');

        String url = "https://www.strava.com/oauth/token";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // parse
                        try {
                            JSONObject jobj = null;
                            jobj = new JSONObject(response);
                            Log.i(TAG, jobj.toString(1));

                            final String access_token = jobj.getString("access_token");
                            final String refresh_token = jobj.getString("refresh_token");
                            final int expires_at = jobj.getInt("expires_at");

                            storeKeys(access_token, refresh_token, expires_at);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("client_id", "2940");
                params.put("client_secret", "0670dd68e47561cb9b91b4a2751af1df0f1d708d");
                params.put("grant_type", "refresh_token");
                params.put("refresh_token", refresh_token);

                return params;
            }
        };
        queue.add(postRequest);
    }

    private void storeKeys(final String access_token, final String refresh_token, final int expires_at) {

        SharedPreferences m = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = m.edit();

        editor.putString(getString(R.string.access_token), access_token);
        Log.i(TAG, "Access: " + access_token);

        editor.putString(getString(R.string.refresh_token), refresh_token);
        Log.i(TAG, "Refresh: " + refresh_token);

        editor.putInt(getString(R.string.expires_at), expires_at);
        Log.i(TAG, "Expires_at: " + expires_at);

        editor.commit();

        logText("Storing key" + '\n');

        // we are done, go back to the main
        goBackToMain();
    }

    private void performOAuthStep2(final String code) {

        Log.i(TAG, "performOAuthStep2");
        Log.i(TAG, code);

        logText("OAuth running" + '\n');

        String url = "https://www.strava.com/oauth/token";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        //Log.d("Response", response);

                        // parse
                        try {
                            JSONObject jobj = null;
                            jobj = new JSONObject(response);
                            Log.i(TAG, jobj.toString(1));

                            String access_token = jobj.getString("access_token");
                            String refresh_token = jobj.getString("refresh_token");
                            final int expires_at = jobj.getInt("expires_at");

                            storeKeys(access_token, refresh_token, expires_at);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("client_id", "2940");
                params.put("client_secret", "0670dd68e47561cb9b91b4a2751af1df0f1d708d");
                params.put("code", code);
                params.put("grant_type", "authorization_code");

                return params;
            }
        };
        queue.add(postRequest);
    }

    // Call Back method  to get the Message form other Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.i(TAG, "onActivityResult " + requestCode + "  code=" + resultCode);

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onResume() {
        // The following line will return "appSchema://appName.com".
        super.onResume();
        String CALLBACK_URL = "xcomputer://xcomputer";
        Uri uri = getIntent().getData();
        if (uri != null) {
            Log.d(TAG, "onResume " + getIntent().getData().toString());
            Log.d(TAG, "URI " + uri.toString());
            if (uri != null && uri.toString().startsWith(CALLBACK_URL)) {
                final String access_token = uri.getQueryParameter("code");

                // start second auth step
                performOAuthStep2(access_token);
            }
        }
        // Perform other operations here.
    }
}
