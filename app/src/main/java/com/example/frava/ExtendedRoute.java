package com.example.frava;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import io.swagger.client.model.LatLng;
import io.swagger.client.model.PolylineMap;
import io.swagger.client.model.Route;

public class ExtendedRoute {

    public char crc;
    public short size;
    private Route summaryRoute;

    public ExtendedRoute(Route route) {
        summaryRoute = route;
    }

    public byte[] toByteBuffer() {

        ByteBuffer allocate = ByteBuffer.allocate(10000);
        allocate.order(ByteOrder.LITTLE_ENDIAN);

        allocate.putInt((int) summaryRoute.getDistance().intValue());

        allocate.putShort((short) 0);
        allocate.putShort((short) 0);
        allocate.putShort((short) 0);

        // set segment name
        {
            byte[] bytes = summaryRoute.getName().getBytes(StandardCharsets.UTF_8);
            int length = bytes.length;
            if (length > 19) {
                length = 19;
            }
            allocate.put((byte) length);
            allocate.put(bytes, 0, length);
        }

        // convert string polyline to binary polyline
        {
            PolylineMap map = summaryRoute.getMap();
            String poly = map.getPolyline();
            if (poly == null) {
                poly = map.getSummaryPolyline();
            }
            List<LatLng> list_latlng = MapTool.decodePolyline(poly);
            // TODO thicken the LatLng list
            byte[] encodeBinaryPolyline = MapTool.encodeBinaryPolyline(list_latlng, list_latlng.size() * 6);
            int binpoly_length = encodeBinaryPolyline.length;
            if (binpoly_length > 2000) {
                binpoly_length = 2000;
            }
            allocate.putShort((short) binpoly_length);
            allocate.put(encodeBinaryPolyline, 0, binpoly_length);
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
