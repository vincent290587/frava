package com.example.frava;

import android.app.Service;
import android.content.Intent;
import android.icu.util.TimeZone;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.swagger.client.model.LatLng;
import io.swagger.client.model.PolylineMap;
import io.swagger.client.model.Route;
import no.nordicsemi.android.ble.data.Data;

public class GpsHandler extends Service {

    private static final String TAG = "GpsHandler";

    private int nb_segments_sent;
    MutableLiveData<String> gps_status;

    // Binder given to clients
    private final IBinder binder = new GpsHandler.LocalBinder();

    List<ExtendedSummarySegment> m_seg_list;

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder {
        GpsHandler getService() {
            // Return this instance of GpsHandler so clients can call public methods
            return GpsHandler.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public GpsHandler() {
        gps_status = new MutableLiveData<>();
    }

    public void subscribe(Observer<String> observer) {

        gps_status.observeForever(observer);
    }

    public void handleCommand(@NonNull Data data, LeCallbacks interf) {

        IncomingCommands command = IncomingCommands.fromByte(data.getByte(0));

        Log.i(TAG, "Received GPS command " + data.getByte(0));

        switch (command) {

            case RequestPhoneStatus:
                sendPhoneStatus(interf);
                break;

            case GPSReadyToReceiveSegmentList:
                sendSegmentsList(interf);
                break;

            case SegmentFileRequest:
                handleSegmentRequest(data, interf);
                break;

            default:
                break;
        }
        
    }

    public void handleSegmentRequest(@NonNull Data data, LeCallbacks interf) {

        if (m_seg_list == null) {
            return;
        }

        Log.i(TAG, "handleSegmentRequest");

        // get ID
        byte[] val = data.getValue();
        ByteBuffer wrap = ByteBuffer.wrap(val);
        wrap.order(ByteOrder.LITTLE_ENDIAN);

        long seg_id = wrap.getLong(1);

        for (ExtendedSummarySegment ext_seg : m_seg_list) {

            if (seg_id == ext_seg.summarySegment.getId()) {

                // send whole segment
                _sendSegment(ext_seg, interf);
                return;
            }
        }

        Log.e(TAG, "handleSegmentRequest: not found " + seg_id);
    }

    public void sendRoute(Route route) {

        PolylineMap map = route.getMap();
        String poly = map.getPolyline();
        if (poly == null) {
            poly = map.getSummaryPolyline();
        }
        // TODO
    }

    private void _sendSegment(ExtendedSummarySegment ext_seg, LeCallbacks interf) {

        List<byte[]> l_data = new ArrayList<>();

        Log.i(TAG, "_sendSegment");

        nb_segments_sent += 1;

        gps_status.postValue("Nb segments sent: " + nb_segments_sent);

        byte[] ext_seg_bytes = ext_seg.toByteBuffer();

        ByteBuffer wrap = ByteBuffer.wrap(newPacket());
        wrap.order(ByteOrder.LITTLE_ENDIAN);

        wrap.put(OutgoingCommands.SegmentFileUploadStart.byteValue());
        wrap.putLong(ext_seg.summarySegment.getId());

        short s = ext_seg.size;
        wrap.putInt(s);

        // add to list
        l_data.add(wrap.array());

        int i = 0;
        while (true) {
            int i2 = s - i;
            if (i2 > 19) {
                ByteBuffer wrap2 = ByteBuffer.wrap(newPacket());
                wrap2.order(ByteOrder.LITTLE_ENDIAN);

                wrap2.put(OutgoingCommands.SegmentFileUploadData.byteValue());
                wrap2.put(ext_seg_bytes, i, 19);
                l_data.add(wrap2.array());
                i += 19;
            } else {
                ByteBuffer wrap3 = ByteBuffer.wrap(newPacket());
                wrap3.order(ByteOrder.LITTLE_ENDIAN);

                wrap3.put(OutgoingCommands.SegmentFileUploadEnd.byteValue());
                wrap3.put(ext_seg_bytes, i, i2);
                l_data.add(wrap3.array());

                // send the data
                if (interf != null) {
                    interf.onListDataSend(l_data);
                }
                return;
            }
        }

    }

    public void sendPhoneStatus(LeCallbacks interf) {

        byte[] data = new byte[20];
        ByteBuffer wrap = ByteBuffer.wrap(data);
        wrap.order(ByteOrder.LITTLE_ENDIAN);

        wrap.put(OutgoingCommands.PhoneStatus.byteValue());
        wrap.put((byte) 66);
        wrap.put((byte) ((int) ((((float) (TimeZone.getDefault().getOffset(new Date().getTime()) / 60000)) / 60.0f) * 4.0f)));
        wrap.put((byte) 0);
        wrap.put((byte) 1);

        Log.i(TAG, "sendPhoneStatus");

        // send the data
        if (interf != null) {
            interf.onDataSend(data);
        }
    }

    public void sendSegmentsListReady(List<ExtendedSummarySegment> seg_list, LeCallbacks interf) {

        byte[] data = new byte[20];
        ByteBuffer wrap = ByteBuffer.wrap(data);
        wrap.order(ByteOrder.LITTLE_ENDIAN);

        m_seg_list = seg_list;

        nb_segments_sent = 0;

        wrap.put(OutgoingCommands.NewSegmentListReady.byteValue());
        if (seg_list == null) {
            wrap.put((byte) 0);
        } else {
            wrap.put((byte) seg_list.size());
        }

        Log.i(TAG, "sendSegmentsListReady");

        gps_status.postValue("sendSegmentsListReady");

        // send the data
        if (interf != null) {
            interf.onDataSend(data);
        }
    }

    private void sendSegmentsList(LeCallbacks interf) {

        int counter = 1;

        if (m_seg_list == null) {
            return;
        }

        Log.i(TAG, "sendSegmentsList");

        gps_status.postValue("sendSegmentsList");

        List<byte[]> l_data = new ArrayList<>();

        for (ExtendedSummarySegment ext_seg : m_seg_list) {

            byte[] data = new byte[20];
            ByteBuffer wrap = ByteBuffer.wrap(data);
            wrap.order(ByteOrder.LITTLE_ENDIAN);

            if (counter == m_seg_list.size()) {
                wrap.put(OutgoingCommands.SegmentListItemDone.byteValue());
            } else {
                wrap.put(OutgoingCommands.SegmentListItem.byteValue());
            }

            wrap.putLong(ext_seg.summarySegment.getId());

            LatLng latlng = ext_seg.summarySegment.getStartLatlng();
            wrap.putInt((int) (latlng.get(0) * 1.1930464E7d));
            wrap.putInt((int) (latlng.get(1) * 1.1930464E7d));
            wrap.putChar((char) 0);
            wrap.put((byte) 0);

            l_data.add(data);

            counter += 1;
        }

        // send the data
        if (interf != null) {
            interf.onListDataSend(l_data);
        }

    }


    public enum OutgoingCommands {
        InvalidCommand(0),
        RequestFitFileList(1),
        RequestFitFileDownload(2),
        RequestFitFileDelete(3),
        NewSegmentListReady(4),
        EmailNotification(5),
        EmailNotificationBody(6),
        EmailNotificationNext(7),
        SMSNotification(8),
        SMSNotificationBody(9),
        SMSNotificationNext(10),
        CallNotification(11),
        RequestSlowDownloads(12),
        NavigationNewRoute(13),
        NavigationRouteDataContinue(14),
        NavigationStep(15),
        NavigationStepContinued(16),
        NavigationRouteData(17),
        SegmentListItem(18),
        SegmentListItemDone(19),
        SegmentFileUploadStart(20),
        SegmentFileUploadData(21),
        SegmentFileUploadEnd(22),
        PhoneStatus(25),
        SegmentUpdateCancel(26),
        NavigationError(27),
        NavigationNewDestination(29),
        NavigationNewFile(30),
        NavigationNewFileDest(31),
        NavFileUploadDataStart(32),
        NavFileUploadData(33),
        NavFileUploadEnd(34),
        NavigationPhoneCancel_V2(35),
        SettingsRequest(36),
        UpdateSetting(37),
        UpdateSettingContinue(38),
        Notification(39),
        NotificationBody(40),
        NotificationNext(41),
        TrainingFileUploadStart(42),
        TrainingFileUploadData(43),
        TrainingFileUploadEnd(44),
        MapFileReadyStart(45),
        MapFileReadyEnd(46),
        MapFileStart(47),
        MapFileData(48),
        MapFileEnd(49),
        MapFileError(50),
        MapFileListRequest(51),
        MapFileDelete(52);

        public int value;

        OutgoingCommands(int i) {
            this.value = i;
        }

        public byte byteValue() {
            return (byte) this.value;
        }

        public static OutgoingCommands fromByte(byte b) {
            for (OutgoingCommands outgoingCommands : values()) {
                if (outgoingCommands.byteValue() == b) {
                    return outgoingCommands;
                }
            }
            return null;
        }
    }

    public enum IncomingCommands {
        InvalidCommand(0),
        FitFileTransferStart(1),
        FitFileTransferData(2),
        FitFileTransferEnd(3),
        SwitchingToHighSpeed(4),
        ConnectedInHighSpeed(5),
        SwitchingToLowSpeed(6),
        ConnectedInLowSpeed(7),
        DebugText(8),
        ErrorPacket(9),
        FileDeleteConfirmation(10),
        FileListSending(11),
        NavigationRerouteRequest(12),
        LiveSensorData(13),
        LiveSessionStatus(14),
        GPSReadyToReceiveSegmentList(15),
        RequestPhoneStatus(16),
        SegmentFileRequest(17),
        SegmentFileRequestEnd(18),
        NavigationNewFileReady(20),
        SettingsSendStart(22),
        NavigationGPSCancel(23),
        SettingsSendData(24),
        SettingsSendError(25),
        TrainingSendError(27),
        MapFileReq(28),
        MapFileError(29),
        RequestMTUUpdate(30),
        MapFileSizeError(31),
        MapFileListData(32),
        MapFileListEnd(33),
        MapFileDeleteComplete(34),
        MapFileDeleteError(35),
        MapFileListError(36),
        NavigationFailed(37);

        public int value;

        public byte byteValue() {
            return (byte) this.value;
        }

        IncomingCommands(int i) {
            this.value = i;
        }

        public static IncomingCommands fromByte(byte b) {
            for (IncomingCommands incomingCommands : values()) {
                if (incomingCommands.byteValue() == b) {
                    return incomingCommands;
                }
            }
            return null;
        }
    }

    private byte[] newPacket() {
        byte[] bArr = new byte[20];
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = 0;
        }
        return bArr;
    }

}
