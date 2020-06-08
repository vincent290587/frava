package com.example.frava;

import android.util.Base64;

public class SequencePacker {

    static final int WINDOW_SIZE = 8;
    int bitCount = 0;
    int[] compressed_U = new int[1000];
    byte[] decompressed;
    boolean done = false;
    int numberOfBytes;
    private final Sample[] numbers;
    int values = 0;

    private int minBitsForValue(int i) {
        if (i == 0) {
            return 2;
        }
        int i2 = 32;
        if (i < 0) {
            i = -i;
        }
        for (int i3 = 1073741824; (i & i3) != i3 && i2 > 0; i3 >>= 1) {
            i2--;
        }
        return i2;
    }

    public void putBits(int i, int i2) {
        //Log.m279i("putBits", "value = " + i + " number of bits = " + i2);
        int i3 = i & (-1 >>> (32 - i2));
        int i4 = this.bitCount;
        int i5 = i4 & 31;
        int i6 = i4 >> 5;
        this.bitCount = i4 + i2;
        int[] iArr = this.compressed_U;
        iArr[i6] = iArr[i6] | (i3 << i5);
        int i7 = 32 - i5;
        if (i2 - i7 > 0) {
            iArr[i6 + 1] = i3 >>> i7;
        }
    }

    static class Sample {

        public int bits;
        public int difference;
        public int minBits;
        public int number;

        Sample() {
        }
    }

    public SequencePacker(int[] iArr) {
        this.numbers = new Sample[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            this.numbers[i] = new Sample();
            this.numbers[i].number = iArr[i];
        }
    }

    public String pack() {
        Sample[] sampleArr;
        Sample[] sampleArr2;
        int i;
        for (int length = this.numbers.length - 1; length > 0; length--) {
            Sample[] sampleArr3 = this.numbers;
            sampleArr3[length].difference = sampleArr3[length - 1].number - this.numbers[length].number;
            Sample[] sampleArr4 = this.numbers;
            sampleArr4[length].minBits = minBitsForValue(sampleArr4[length].difference);
        }
        int i2 = 1;
        while (true) {
            sampleArr = this.numbers;
            int i3 = 0;
            if (i2 >= sampleArr.length) {
                break;
            }
            int i4 = i2;
            int i5 = 0;
            int i6 = 0;
            while (i5 < 8) {
                Sample[] sampleArr5 = this.numbers;
                if (i4 >= sampleArr5.length) {
                    break;
                }
                if (sampleArr5[i4].minBits > i6) {
                    i6 = this.numbers[i4].minBits;
                }
                i5++;
                i4++;
            }
            while (i3 < 8) {
                Sample[] sampleArr6 = this.numbers;
                if (i2 >= sampleArr6.length) {
                    break;
                }
                sampleArr6[i2].bits = (byte) i6;
                i3++;
                i2++;
            }
            i2 = i4;
        }
        int i7 = sampleArr[1].bits;
        int i8 = 1;
        int i9 = 1000;
        while (true) {
            sampleArr2 = this.numbers;
            if (i8 >= sampleArr2.length) {
                break;
            }
            if (sampleArr2[i8].minBits < i7) {
                i = this.numbers[i8].bits;
            } else {
                int i10 = i9;
                i = i7;
                i7 = i10;
            }
            if (i7 < i) {
                if (this.numbers[i8].minBits < i7) {
                    this.numbers[i8].bits = (byte) i7;
                } else {
                    i7 = 1000;
                }
            }
            i8++;
            int i11 = i;
            i9 = i7;
            i7 = i11;
        }
        putBits(sampleArr2[0].number, 32);
        putBits(this.numbers[1].bits - 2, 3);
        int i12 = this.numbers[1].bits;
        int i13 = 1;
        while (true) {
            Sample[] sampleArr7 = this.numbers;
            if (i13 >= sampleArr7.length) {
                break;
            }
            if (sampleArr7[i13].bits != i12) {
                putBits(1 << (i12 - 1), i12);
                putBits(this.numbers[i13].bits - 2, 3);
                i12 = this.numbers[i13].bits;
            }
            putBits(0 - this.numbers[i13].difference, i12);
            i13++;
        }
        int i14 = (this.bitCount + 31) >>> 5;
        byte[] bArr = new byte[(i14 * 4)];
        int i15 = 0;
        for (int i16 = 0; i16 < i14; i16++) {
            int[] iArr = this.compressed_U;
            bArr[i15 + 0] = (byte) ((iArr[i16] >>> 0) & 255);
            bArr[i15 + 1] = (byte) ((iArr[i16] >>> 8) & 255);
            bArr[i15 + 2] = (byte) ((iArr[i16] >>> 16) & 255);
            bArr[i15 + 3] = (byte) ((iArr[i16] >>> 24) & 255);
            i15 += 4;
        }
        String encodeToString = Base64.encodeToString(bArr, 3);
        System.out.println("Packed sequence to " + i14 + " bytes");
        return encodeToString;
    }
}
