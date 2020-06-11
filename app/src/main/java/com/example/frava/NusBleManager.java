package com.example.frava;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import java.util.List;
import java.util.UUID;

import no.nordicsemi.android.ble.PhyRequest;
import no.nordicsemi.android.ble.WriteRequest;
import no.nordicsemi.android.ble.livedata.state.ConnectionState;
import no.nordicsemi.android.ble.callback.profile.ProfileDataCallback;
import no.nordicsemi.android.ble.livedata.ObservableBleManager;

public class NusBleManager extends ObservableBleManager {

    public final static UUID SERVICE_UUID = UUID.fromString("904d0001-2ce9-078d-944d-263fd93d95b2");

    final static UUID RX_CHAR = UUID.fromString("904d0002-2ce9-078d-944d-263fd93d95b2");
    final static UUID TX_CHAR = UUID.fromString("904d0003-2ce9-078d-944d-263fd93d95b2");

    private boolean useLongWrite = true;

    private static final String TAG = "NusBleManager";

    private ProfileDataCallback mCallbacks;
    // Client characteristics
    private BluetoothGattCharacteristic rxCharacteristic, txCharacteristic;

    public NusBleManager(Context context) {
        super(context);
    }

    public void setCallbacks(ProfileDataCallback callbacks) {
        mCallbacks = callbacks;
    }

    public void observeConnection(LifecycleOwner owner, Observer<ConnectionState> observer) {

        if (owner != null) {

            getState().observe(owner, observer);
        } else {

            getState().observeForever(observer);
        }
    }

    public void removeObserver(Observer<ConnectionState> observer) {

        getState().removeObserver(observer);
    }

    @NonNull
    @Override
    protected BleManagerGattCallback getGattCallback() {
        return new MyManagerGattCallback();
    }

    @Override
    public void log(final int priority, @NonNull final String message) {
        Log.d(TAG, message);
    }

    /**
     * BluetoothGatt callbacks object.
     */
    private class MyManagerGattCallback extends BleManagerGattCallback {

        // Initialize your device here. Often you need to enable notifications and set required
        // MTU or write some initial data. Do it here.
        @Override
        protected void initialize() {
            // You may enqueue multiple operations. A queue ensures that all operations are
            // performed one after another, but it is not required.
            beginAtomicRequestQueue()
                    .add(requestMtu(247) // Remember, GATT needs 3 bytes extra. This will allow packet size of 244 bytes.
                            .with((device, mtu) -> log(Log.INFO, "MTU set to " + mtu))
                            .fail((device, status) -> log(Log.WARN, "Requested MTU not supported: " + status)))
                    .add(setPreferredPhy(PhyRequest.PHY_LE_2M_MASK, PhyRequest.PHY_LE_2M_MASK, PhyRequest.PHY_OPTION_NO_PREFERRED)
                            .fail((device, status) -> log(Log.WARN, "Requested PHY not supported: " + status)))
                    .add(enableNotifications(txCharacteristic))
                    .done(device -> log(Log.INFO, "Target initialized"))
                    .enqueue();


            setNotificationCallback(txCharacteristic)
                    .with((device, data) -> {
                        //final String text = data.getStringValue(0);
                        //Log.i(TAG, "\"" + text + "\" received");
                        if (mCallbacks != null) {
                            mCallbacks.onDataReceived(device, data);
                        }
                    });
            //requestMtu(180).enqueue();
            //enableNotifications(txCharacteristic).enqueue();
        }

        // This method will be called when the device is connected and services are discovered.
        // You need to obtain references to the characteristics and descriptors that you will use.
        // Return true if all required services are found, false otherwise.
        @Override
        public boolean isRequiredServiceSupported(@NonNull final BluetoothGatt gatt) {
            final BluetoothGattService service = gatt.getService(SERVICE_UUID);
            if (service != null) {
                rxCharacteristic = service.getCharacteristic(RX_CHAR);
                txCharacteristic = service.getCharacteristic(TX_CHAR);
            }

            boolean writeRequest = false;
            boolean writeCommand = false;
            if (rxCharacteristic != null) {
                final int rxProperties = rxCharacteristic.getProperties();
                writeRequest = (rxProperties & BluetoothGattCharacteristic.PROPERTY_WRITE) > 0;
                writeCommand = (rxProperties & BluetoothGattCharacteristic.PROPERTY_WRITE_NO_RESPONSE) > 0;

                // Set the WRITE REQUEST type when the characteristic supports it.
                // This will allow to send long write (also if the characteristic support it).
                // In case there is no WRITE REQUEST property, this manager will divide texts
                // longer then MTU-3 bytes into up to MTU-3 bytes chunks.
                if (writeRequest) {
                    // type of write used
//                    rxCharacteristic.setWriteType(BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT);
                    rxCharacteristic.setWriteType(BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE);
                } else {
                    useLongWrite = false;
                }
            }

            boolean ret = rxCharacteristic != null && txCharacteristic != null && (writeRequest || writeCommand);
            return ret;
        }

        // If you have any optional services, allocate them here. Return true only if
        // they are found.
        @Override
        protected boolean isOptionalServiceSupported(@NonNull final BluetoothGatt gatt) {
            return super.isOptionalServiceSupported(gatt);
        }

        @Override
        protected void onDeviceDisconnected() {
            // Device disconnected. Release your references here.
            rxCharacteristic = null;
            txCharacteristic = null;
        }
    }

    // Define your API.

    /**
     * Sends the given text to RX characteristic.
     * @param data the data to be sent
     */
    public void send(final byte[] data) {
        // Are we connected?
        if (rxCharacteristic == null)
            return;

        if (data.length > 0) {
//            rxCharacteristic.setValue(data);
            final WriteRequest request = writeCharacteristic(rxCharacteristic, data);
//            final WriteRequest request = sendNotification(rxCharacteristic, data);
            if (!useLongWrite) {
                // This will automatically split the long data into MTU-3-byte long packets.
                Log.d(TAG, "Short write");
                request.split();
            }
            request.enqueue();
        }
    }

    /**
     * Sends the given text to RX characteristic.
     * @param l_data the data list to be sent
     */
    public void sendList(final List<byte[]> l_data) {
        // Are we connected?
        if (rxCharacteristic == null)
            return;

        for (byte[] data : l_data) {

            if (data.length > 0) {
                send(data);
            }
        }
    }

}

