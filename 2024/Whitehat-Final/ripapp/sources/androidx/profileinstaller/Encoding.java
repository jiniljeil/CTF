package androidx.profileinstaller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import kotlin.UByte;

class Encoding {
    static final int SIZEOF_BYTE = 8;
    static final int UINT_16_SIZE = 2;
    static final int UINT_32_SIZE = 4;
    static final int UINT_8_SIZE = 1;

    private Encoding() {
    }

    static int utf8Length(String str) {
        return str.getBytes(StandardCharsets.UTF_8).length;
    }

    static void writeUInt(OutputStream outputStream, long j, int i) throws IOException {
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) ((int) ((j >> (i2 * 8)) & 255));
        }
        outputStream.write(bArr);
    }

    static void writeUInt8(OutputStream outputStream, int i) throws IOException {
        writeUInt(outputStream, (long) i, 1);
    }

    static void writeUInt16(OutputStream outputStream, int i) throws IOException {
        writeUInt(outputStream, (long) i, 2);
    }

    static void writeUInt32(OutputStream outputStream, long j) throws IOException {
        writeUInt(outputStream, j, 4);
    }

    static void writeString(OutputStream outputStream, String str) throws IOException {
        outputStream.write(str.getBytes(StandardCharsets.UTF_8));
    }

    static int bitsToBytes(int i) {
        return ((i + 7) & -8) / 8;
    }

    static byte[] read(InputStream inputStream, int i) throws IOException {
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < i) {
            int read = inputStream.read(bArr, i2, i - i2);
            if (read >= 0) {
                i2 += read;
            } else {
                throw error("Not enough bytes to read: " + i);
            }
        }
        return bArr;
    }

    static long readUInt(InputStream inputStream, int i) throws IOException {
        byte[] read = read(inputStream, i);
        long j = 0;
        for (int i2 = 0; i2 < i; i2++) {
            j += ((long) (read[i2] & UByte.MAX_VALUE)) << (i2 * 8);
        }
        return j;
    }

    static int readUInt8(InputStream inputStream) throws IOException {
        return (int) readUInt(inputStream, 1);
    }

    static int readUInt16(InputStream inputStream) throws IOException {
        return (int) readUInt(inputStream, 2);
    }

    static long readUInt32(InputStream inputStream) throws IOException {
        return readUInt(inputStream, 4);
    }

    static String readString(InputStream inputStream, int i) throws IOException {
        return new String(read(inputStream, i), StandardCharsets.UTF_8);
    }

    static byte[] readCompressed(InputStream inputStream, int i, int i2) throws IOException {
        Inflater inflater = new Inflater();
        try {
            byte[] bArr = new byte[i2];
            byte[] bArr2 = new byte[2048];
            int i3 = 0;
            int i4 = 0;
            while (!inflater.finished() && !inflater.needsDictionary() && i3 < i) {
                int read = inputStream.read(bArr2);
                if (read >= 0) {
                    inflater.setInput(bArr2, 0, read);
                    i4 += inflater.inflate(bArr, i4, i2 - i4);
                    i3 += read;
                } else {
                    throw error("Invalid zip data. Stream ended after $totalBytesRead bytes. Expected " + i + " bytes");
                }
            }
            if (i3 != i) {
                throw error("Didn't read enough bytes during decompression. expected=" + i + " actual=" + i3);
            } else if (inflater.finished()) {
                inflater.end();
                return bArr;
            } else {
                throw error("Inflater did not finish");
            }
        } catch (DataFormatException e) {
            throw error(e.getMessage());
        } catch (Throwable th) {
            inflater.end();
            throw th;
        }
    }

    static void writeCompressed(OutputStream outputStream, byte[] bArr) throws IOException {
        writeUInt32(outputStream, (long) bArr.length);
        byte[] compress = compress(bArr);
        writeUInt32(outputStream, (long) compress.length);
        outputStream.write(compress);
    }

    static byte[] compress(byte[] bArr) throws IOException {
        DeflaterOutputStream deflaterOutputStream;
        Deflater deflater = new Deflater(1);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream, deflater);
            deflaterOutputStream.write(bArr);
            deflaterOutputStream.close();
            deflater.end();
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            deflater.end();
            throw th;
        }
        throw th;
    }

    static void writeAll(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[512];
        while (true) {
            int read = inputStream.read(bArr);
            if (read > 0) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    static RuntimeException error(String str) {
        return new IllegalStateException(str);
    }
}
