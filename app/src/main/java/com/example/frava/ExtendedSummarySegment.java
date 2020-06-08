package com.example.frava;

import android.util.Base64;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import io.swagger.client.model.LatLng;
import io.swagger.client.model.LatLngStream;
import io.swagger.client.model.StreamSet;
import io.swagger.client.model.SummarySegment;
import io.swagger.client.model.TimeStream;

public class ExtendedSummarySegment {

    public char crc;
    public short size;

    public SummarySegment summarySegment;
    public StreamSet streamSet;


    public ExtendedSummarySegment(SummarySegment seg) {
        this.streamSet = null;
        summarySegment = seg;
    }

    public void attachStreams(StreamSet streamSet) {
        this.streamSet = streamSet;
    }

    public byte[] toByteBuffer() {

        ByteBuffer allocate = ByteBuffer.allocate(10000);
        allocate.order(ByteOrder.LITTLE_ENDIAN);

        allocate.putInt((int) summarySegment.getDistance().intValue());

        LatLng latlng = summarySegment.getStartLatlng();
        allocate.putInt((int) (latlng.get(0) * 1.1930464E7d));
        allocate.putInt((int) (latlng.get(1) * 1.1930464E7d));

        latlng = summarySegment.getEndLatlng();
        allocate.putInt((int) (latlng.get(0) * 1.1930464E7d));
        allocate.putInt((int) (latlng.get(1) * 1.1930464E7d));

        allocate.putShort((short) 0);
        allocate.putShort((short) 0);
        allocate.putShort((short) 0);

        // set segment name
        {
            byte[] bytes = summarySegment.getName().getBytes(StandardCharsets.UTF_8);
            int length = bytes.length;
            if (length > 19) {
                length = 19;
            }
            allocate.put((byte) length);
            allocate.put(bytes, 0, length);
        }

        // set segment coordinates
        {
            LatLngStream latLngStream = streamSet.getLatlng();
            List<LatLng> list_latlng = latLngStream.getData();
            byte[] encodeBinaryPolyline = MapTool.encodeBinaryPolyline(list_latlng, list_latlng.size() * 6);
            int binpoly_length = encodeBinaryPolyline.length;
            if (binpoly_length > 2000) {
                binpoly_length = 2000;
            }
            allocate.putShort((short) binpoly_length);
            allocate.put(encodeBinaryPolyline, 0, binpoly_length);
        }

        // PR time
        allocate.putShort((short) 0);
        // KOM time
        allocate.putShort((short) 0);
        // isHazardous
        allocate.putShort((short) 0);

        // encode streams
        {
            TimeStream timeStream = streamSet.getTime();
            int stream_length = timeStream.getOriginalSize();
            int[] pr_times = MapTool.toIntArray(timeStream.getData());
            SequencePacker packer = new SequencePacker(pr_times);
            String encodeBinaryString = packer.pack();
            byte[] encodeBinaryStream = Base64.decode(encodeBinaryString, 0);
            int bin_length = encodeBinaryStream.length;

            // allocate <- streams
            allocate.putShort((short) bin_length);
            allocate.putShort((short) stream_length);
            allocate.put(encodeBinaryStream, 0, bin_length);
        }

        // padding
        allocate.putInt((int) 0);

        // allocate length
        short position = (short) allocate.position();
        allocate.putShort(position);

        // calculate CRC
        char c = 0;
        for (int i5 = 0; i5 < allocate.position(); i5++) {
            c = MapTool.GetCRC16(c, allocate.get(i5));
        }
        allocate.putShort((short) c);

        this.crc = c;
        this.size = (short) (position + 4);

        return allocate.array();
    }

}
