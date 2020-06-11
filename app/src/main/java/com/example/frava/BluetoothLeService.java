package com.example.frava;

import android.app.Service;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import java.util.List;

import no.nordicsemi.android.ble.livedata.state.ConnectionState;
import no.nordicsemi.android.ble.callback.profile.ProfileDataCallback;
import no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerCompat;
import no.nordicsemi.android.support.v18.scanner.ScanResult;
import no.nordicsemi.android.support.v18.scanner.ScanSettings;

public class BluetoothLeService extends Service implements LeCallbacks {

    private static final String TAG = "BluetoothLeService";

    private NusBleManager bleManager;
    private BluetoothDevice device;
    private ScannerStateLiveData scannerStateLiveData;
    private DevicesLiveData devicesLiveData;
    private ProfileDataCallback mCallbacks;

    private final Observer<ConnectionState> conn_obs = state -> {
        // TODO something here
        Log.d(TAG, "onChanged connection state " + state.toString());
        if (state.isConnected()) {
            stopScan();
        } else {
            device = null;
            startScan();
        }
    };

    // Binder given to clients
    private final IBinder binder = new LocalBinder();

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder {
        BluetoothLeService getService() {
            // Return this instance of BluetoothLeService so clients can call public methods
            return BluetoothLeService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void registerCallbacks(ProfileDataCallback callbacks) {
        mCallbacks = callbacks;
        bleManager.setCallbacks(mCallbacks);
    }

    public void registerObserver(LifecycleOwner owner, Observer<ConnectionState> observer) {

        bleManager.observeConnection(owner, observer);
    }

    public void removeObserver(Observer<ConnectionState> observer) {

        bleManager.removeObserver(observer);
    }

    @Override
    public void onCreate() {
        // Code to execute when the service is first created

        bleManager = new NusBleManager(this);
        scannerStateLiveData = new ScannerStateLiveData(Utils.isBleEnabled(),
                Utils.isLocationEnabled(this));
        devicesLiveData = new DevicesLiveData(true, true);

        bleManager.getState().observeForever(conn_obs);
    }

    @Override
    public void onDestroy() {
        stopScan();
        disconnect();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startid) {
        startScan();
        return START_STICKY;
    }

    /* access modifiers changed from: private */
    public boolean isVincentDevice(BluetoothDevice bluetoothDevice) {
        String[] split = bluetoothDevice.getAddress().split(":");
        return split[4].equalsIgnoreCase("37") && split[5].equalsIgnoreCase("B5");
    }

    /* access modifiers changed from: private */
    public void onPositiveResult(@NonNull final ScanResult result) {

        DiscoveredBluetoothDevice disc_device = new DiscoveredBluetoothDevice(result);
        BluetoothDevice _device = disc_device.getDevice();

        Log.d(TAG, "onPositiveResult");

        if (_device != null && isVincentDevice(_device)) {
            this.connect(_device);
        }
    }

    private final no.nordicsemi.android.support.v18.scanner.ScanCallback scanCallback = new no.nordicsemi.android.support.v18.scanner.ScanCallback() {
        @Override
        public void onScanResult(final int callbackType, @NonNull final no.nordicsemi.android.support.v18.scanner.ScanResult result) {
            // This callback will be called only if the scan report delay is not set or is set to 0.

            Log.i(TAG, "onScanResult");

            // If the packet has been obtained while Location was disabled, mark Location as not required
            if (Utils.isLocationRequired(getApplication()) && !Utils.isLocationEnabled(getApplication()))
                Utils.markLocationNotRequired(getApplication());

            if (devicesLiveData.deviceDiscovered(result)) {
                devicesLiveData.applyFilter();
                scannerStateLiveData.recordFound();
                onPositiveResult(result);
            }
        }

        @Override
        public void onBatchScanResults(@NonNull final List<no.nordicsemi.android.support.v18.scanner.ScanResult> results) {
            // This callback will be called only if the report delay set above is greater then 0.

            // If the packet has been obtained while Location was disabled, mark Location as not required
            if (Utils.isLocationRequired(getApplication()) && !Utils.isLocationEnabled(getApplication()))
                Utils.markLocationNotRequired(getApplication());

            for (final no.nordicsemi.android.support.v18.scanner.ScanResult result : results) {

                if (devicesLiveData.deviceDiscovered(result)) {
                    devicesLiveData.applyFilter();
                    scannerStateLiveData.recordFound();
                    onPositiveResult(result);
                }
            }

        }

        @Override
        public void onScanFailed(final int errorCode) {
            scannerStateLiveData.scanningStopped();
        }
    };


    /**
     * Start scanning for Bluetooth devices.
     */
    public void startScan() {

        if (scannerStateLiveData.isScanning()) {
            return;
        }

        // Scanning settings
        final ScanSettings settings = new ScanSettings.Builder()
                .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
                .setReportDelay(500)
                .setUseHardwareBatchingIfSupported(false)
                .build();

        final BluetoothLeScannerCompat scanner = BluetoothLeScannerCompat.getScanner();
        scanner.startScan(null, settings, scanCallback);
        scannerStateLiveData.scanningStarted();

        Log.i(TAG, "Scan started");
    }

    /**
     * Stop scanning for bluetooth devices.
     */
    public void stopScan() {
        if (scannerStateLiveData.isScanning() && scannerStateLiveData.isBluetoothEnabled()) {
            final BluetoothLeScannerCompat scanner = BluetoothLeScannerCompat.getScanner();
            scanner.stopScan(scanCallback);
            scannerStateLiveData.scanningStopped();
        }
    }


    private boolean isConnected() {

        return device != null;
    }

    /**
     * Connect to the given peripheral.
     *
     * @param target the target device.
     */
    public void connect(@NonNull final BluetoothDevice target) {
        // Prevent from calling again when called again (screen orientation changed).
        if (device == null) {
            device = target;

            stopScan();
            Log.i(TAG, "Connecting to: MAC:" + device.getAddress());
            reconnect();

            Toast.makeText(this, "BLE Connected", Toast.LENGTH_SHORT);
        }
    }

    /**
     * Reconnects to previously connected device.
     * If this device was not supported, its services were cleared on disconnection, so
     * reconnection may help.
     */
    public void reconnect() {
        if (device != null) {
            bleManager.connect(device)
                    .retry(3, 100)
                    .useAutoConnect(false)
                    .enqueue();
        }
    }

    /**
     * Disconnect from peripheral.
     */
    private void disconnect() {
        device = null;
        bleManager.disconnect().enqueue();

        Toast.makeText(this, "BLE Disconnected", Toast.LENGTH_SHORT);
    }

    @Override
    public void onDataSend(final byte[] data) {

        if (bleManager != null && isConnected()) {
            bleManager.send(data);
        }
    }

    @Override
    public void onListDataSend(final List<byte[]> l_data) {

        if (bleManager != null && isConnected()) {
            bleManager.sendList(l_data);
        }
    }
}
