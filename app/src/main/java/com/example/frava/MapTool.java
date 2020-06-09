package com.example.frava;


import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import io.swagger.client.model.LatLng;

public class MapTool {

    public static List<LatLng> decodePolyline(String str) {
        int i;
        int i2;
        ArrayList arrayList = new ArrayList();
        int length = str.length();
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i3 < length) {
            int i6 = 0;
            int i7 = 0;
            while (true) {
                i = i3 + 1;
                int charAt = str.charAt(i3) - '?';
                i6 |= (charAt & 31) << i7;
                i7 += 5;
                if (charAt < 32) {
                    break;
                }
                i3 = i;
            }
            int i8 = ((i6 & 1) != 0 ? ~(i6 >> 1) : i6 >> 1) + i4;
            int i9 = 0;
            int i10 = 0;
            while (true) {
                i2 = i + 1;
                int charAt2 = str.charAt(i) - '?';
                i9 |= (charAt2 & 31) << i10;
                i10 += 5;
                if (charAt2 < 32) {
                    break;
                }
                i = i2;
            }
            int i11 = i9 & 1;
            int i12 = i9 >> 1;
            if (i11 != 0) {
                i12 = ~i12;
            }
            i5 += i12;
            LatLng latlng = new LatLng();
            // lat
            latlng.add((float) ((float) (((double) i8) / 100000.0d)));
            // lon
            latlng.add((float) ((float) (((double) i5) / 100000.0d)));
            arrayList.add(latlng);
            i4 = i8;
            i3 = i2;
        }
        return arrayList;
    }


    public static class PolylineEncoder {
        /* access modifiers changed from: private */
        public int curBytes;
        private int lastLatitude;
        private int lastLongitude;
        /* access modifiers changed from: private */
        public byte[] polylineBuffer;

        public PolylineEncoder(int i) {
            this.polylineBuffer = new byte[i];
        }

        public String getString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < this.curBytes; i++) {
                sb.append((char) this.polylineBuffer[i]);
            }
            return sb.toString();
        }

        private void PolylineEncoder_addCoordinatePart(int i) {
            int i2;
            int i3 = i << 1;
            if (i < 0) {
                i3 = ~i3;
            }
            int[] iArr = new int[10];
            int i4 = 0;
            while (i3 > 0) {
                iArr[i4] = i3 % 32;
                i3 >>= 5;
                i4++;
            }
            if (this.curBytes + i4 + 1 < this.polylineBuffer.length) {
                int i5 = 0;
                while (true) {
                    i2 = i4 - 1;
                    if (i5 >= i2) {
                        break;
                    }
                    byte[] bArr = this.polylineBuffer;
                    int i6 = this.curBytes;
                    this.curBytes = i6 + 1;
                    bArr[i6] = (byte) ((iArr[i5] | 32) + 63);
                    i5++;
                }
                if (i4 > 0) {
                    byte[] bArr2 = this.polylineBuffer;
                    int i7 = this.curBytes;
                    this.curBytes = i7 + 1;
                    bArr2[i7] = (byte) (iArr[i2] + 63);
                } else {
                    byte[] bArr3 = this.polylineBuffer;
                    int i8 = this.curBytes;
                    this.curBytes = i8 + 1;
                    bArr3[i8] = 63;
                }
                this.polylineBuffer[this.curBytes] = 0;
                return;
            }
            throw new IllegalStateException("Polyline byte buffer is too small curBytes=" + this.curBytes + "   abIndex=" + i4 + "    polylineBuffer.length=" + this.polylineBuffer.length);
        }

        public void addCoordinate(LatLng latLng) {
            int i = (int) (latLng.get(1) * 100000.0d);
            int i2 = (int) (latLng.get(0) * 100000.0d);
            if (i != 0 && Math.abs(i) < Integer.MAX_VALUE && i2 != 0 && Math.abs(i2) < Integer.MAX_VALUE) {
                if (this.lastLongitude == 0 && this.lastLatitude == 0) {
                    PolylineEncoder_addCoordinatePart(i2);
                    PolylineEncoder_addCoordinatePart(i);
                    this.lastLatitude = i2;
                    this.lastLongitude = i;
                    return;
                }
                PolylineEncoder_addCoordinatePart(i2 - this.lastLatitude);
                PolylineEncoder_addCoordinatePart(i - this.lastLongitude);
                this.lastLatitude = i2;
                this.lastLongitude = i;
            }
        }
    }

    public static class BinaryPolylineEncoder {
        private int curBytes = 0;
        private int lastLatitude;
        private int lastLongitude;
        private byte[] output;

        private static final int MASK = 127;

        public BinaryPolylineEncoder(int i) {
            this.output = new byte[(i * 4)];
        }

        public byte[] bytes() {
            byte[] bArr = new byte[this.curBytes];
            for (int i = 0; i < this.curBytes; i++) {
                bArr[i] = this.output[i];
            }
            return bArr;
        }

        public void addFirstCoordinate(LatLng latLng) {
            this.lastLatitude = (int) (latLng.get(0) * 100000.0d);
            int i = (int) (latLng.get(1) * 100000.0d);
            this.lastLongitude = i;
            byte[] bArr = this.output;
            int i2 = this.curBytes;
            int i3 = i2 + 1;
            this.curBytes = i3;
            int i4 = this.lastLatitude;
            bArr[i2] = (byte) (i4 & 255);
            int i5 = i3 + 1;
            this.curBytes = i5;
            bArr[i3] = (byte) ((i4 >>> 8) & 255);
            int i6 = i5 + 1;
            this.curBytes = i6;
            bArr[i5] = (byte) ((i4 >>> 16) & 255);
            int i7 = i6 + 1;
            this.curBytes = i7;
            bArr[i6] = (byte) ((i4 >>> 24) & 255);
            int i8 = i7 + 1;
            this.curBytes = i8;
            bArr[i7] = (byte) (i & 255);
            int i9 = i8 + 1;
            this.curBytes = i9;
            bArr[i8] = (byte) ((i >>> 8) & 255);
            int i10 = i9 + 1;
            this.curBytes = i10;
            bArr[i9] = (byte) ((i >>> 16) & 255);
            this.curBytes = i10 + 1;
            bArr[i10] = (byte) ((i >>> 24) & 255);
        }

        private void addCoordinatePart(int i) {
            int i2;
            int i3 = i << 1;
            if (i < 0) {
                i3 = ~i3;
            }
            int[] iArr = new int[10];
            int i4 = 0;
            while (i3 > 0) {
                iArr[i4] = i3 & MASK;
                i3 >>>= 7;
                i4++;
            }
            int i5 = 0;
            while (true) {
                i2 = i4 - 1;
                if (i5 >= i2) {
                    break;
                }
                byte[] bArr = this.output;
                int i6 = this.curBytes;
                this.curBytes = i6 + 1;
                bArr[i6] = (byte) (iArr[i5] | 128);
                i5++;
            }
            if (i4 > 0) {
                byte[] bArr2 = this.output;
                int i7 = this.curBytes;
                this.curBytes = i7 + 1;
                bArr2[i7] = (byte) iArr[i2];
                return;
            }
            byte[] bArr3 = this.output;
            int i8 = this.curBytes;
            this.curBytes = i8 + 1;
            bArr3[i8] = 0;
        }

        public void addCoordinate(LatLng latLng) {
            int i = (int) (latLng.get(1) * 100000.0d);
            int i2 = (int) (latLng.get(0) * 100000.0d);
            if (i != 0 && Math.abs(i) < Integer.MAX_VALUE && i2 != 0 && Math.abs(i2) < Integer.MAX_VALUE) {
                if (this.lastLongitude == 0 && this.lastLatitude == 0) {
                    addCoordinatePart(i2);
                    addCoordinatePart(i);
                    this.lastLatitude = i2;
                    this.lastLongitude = i;
                    return;
                }
                addCoordinatePart(i2 - this.lastLatitude);
                addCoordinatePart(i - this.lastLongitude);
                this.lastLatitude = i2;
                this.lastLongitude = i;
            }
        }
    }

    public static String encodePolyline(List<LatLng> list, int i) {
        PolylineEncoder polylineEncoder = new PolylineEncoder((list.size() * 7) + 10);
        for (LatLng addCoordinate : list) {
            polylineEncoder.addCoordinate(addCoordinate);
            if (polylineEncoder.curBytes > i) {
                break;
            }
        }
        byte[] bArr = new byte[polylineEncoder.curBytes];
        for (int i2 = 0; i2 < polylineEncoder.curBytes; i2++) {
            bArr[i2] = polylineEncoder.polylineBuffer[i2];
        }
        return new String(bArr, StandardCharsets.UTF_8);
    }

    public static String encodePolyline(List<LatLng> list) {
        PolylineEncoder polylineEncoder = new PolylineEncoder((list.size() * 7) + 10);
        for (LatLng addCoordinate : list) {
            polylineEncoder.addCoordinate(addCoordinate);
        }
        byte[] bArr = new byte[polylineEncoder.curBytes];
        for (int i = 0; i < polylineEncoder.curBytes; i++) {
            bArr[i] = polylineEncoder.polylineBuffer[i];
        }
        return new String(bArr, StandardCharsets.UTF_8);
    }

    public static byte[] encodeBinaryPolyline(List<LatLng> list, int i) {
        BinaryPolylineEncoder binaryPolylineEncoder = new BinaryPolylineEncoder(i);
        boolean z = true;
        for (LatLng next : list) {
            if (z) {
                binaryPolylineEncoder.addFirstCoordinate(next);
            } else {
                binaryPolylineEncoder.addCoordinate(next);
            }
            z = false;
        }
        return binaryPolylineEncoder.bytes();
    }

    public static int[] toIntArray(List<Integer> list){
        int[] ret = new int[list.size()];
        for(int i = 0;i < ret.length;i++)
            ret[i] = list.get(i);
        return ret;
    }

    public static char GetCRC16(char c, byte b) {
        final char[] crc_table = {0, 52225, 55297, 5120, 61441, 15360, 10240, 58369, 40961, 27648, 30720, 46081, 20480, 39937, 34817, 17408};
        char[] cArr = crc_table;
        char c2 = (char) ((((char) ((c >>> 4) & 0x0FFF)) ^ cArr[c & 15]) ^ cArr[b & 0xF]);
        return (char) ((((char) ((c2 >>> 4) & 0x0FFF)) ^ cArr[c2 & 15]) ^ cArr[(b >>> 4) & 15]);
    }

    public static boolean isDebugVersion() {

        String name = BuildConfig.BUILD_TYPE;

        return name.contains("debug");
    }
}