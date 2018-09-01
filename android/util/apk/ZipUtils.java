// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util.apk;

import android.util.Pair;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

abstract class ZipUtils
{

    private ZipUtils()
    {
    }

    private static void assertByteOrderLittleEndian(ByteBuffer bytebuffer)
    {
        if(bytebuffer.order() != ByteOrder.LITTLE_ENDIAN)
            throw new IllegalArgumentException("ByteBuffer byte order must be little endian");
        else
            return;
    }

    private static int findZipEndOfCentralDirectoryRecord(ByteBuffer bytebuffer)
    {
        assertByteOrderLittleEndian(bytebuffer);
        int i = bytebuffer.capacity();
        if(i < 22)
            return -1;
        int j = Math.min(i - 22, 65535);
        for(int k = 0; k <= j; k++)
        {
            int l = i - 22 - k;
            if(bytebuffer.getInt(l) == 0x6054b50 && getUnsignedInt16(bytebuffer, l + 20) == k)
                return l;
        }

        return -1;
    }

    static Pair findZipEndOfCentralDirectoryRecord(RandomAccessFile randomaccessfile)
        throws IOException
    {
        if(randomaccessfile.length() < 22L)
            return null;
        Pair pair = findZipEndOfCentralDirectoryRecord(randomaccessfile, 0);
        if(pair != null)
            return pair;
        else
            return findZipEndOfCentralDirectoryRecord(randomaccessfile, 65535);
    }

    private static Pair findZipEndOfCentralDirectoryRecord(RandomAccessFile randomaccessfile, int i)
        throws IOException
    {
        if(i < 0 || i > 65535)
            throw new IllegalArgumentException((new StringBuilder()).append("maxCommentSize: ").append(i).toString());
        long l = randomaccessfile.length();
        if(l < 22L)
            return null;
        ByteBuffer bytebuffer = ByteBuffer.allocate((int)Math.min(i, l - 22L) + 22);
        bytebuffer.order(ByteOrder.LITTLE_ENDIAN);
        l -= bytebuffer.capacity();
        randomaccessfile.seek(l);
        randomaccessfile.readFully(bytebuffer.array(), bytebuffer.arrayOffset(), bytebuffer.capacity());
        i = findZipEndOfCentralDirectoryRecord(bytebuffer);
        if(i == -1)
        {
            return null;
        } else
        {
            bytebuffer.position(i);
            randomaccessfile = bytebuffer.slice();
            randomaccessfile.order(ByteOrder.LITTLE_ENDIAN);
            return Pair.create(randomaccessfile, Long.valueOf((long)i + l));
        }
    }

    private static int getUnsignedInt16(ByteBuffer bytebuffer, int i)
    {
        return bytebuffer.getShort(i) & 0xffff;
    }

    private static long getUnsignedInt32(ByteBuffer bytebuffer, int i)
    {
        return (long)bytebuffer.getInt(i) & 0xffffffffL;
    }

    public static long getZipEocdCentralDirectoryOffset(ByteBuffer bytebuffer)
    {
        assertByteOrderLittleEndian(bytebuffer);
        return getUnsignedInt32(bytebuffer, bytebuffer.position() + 16);
    }

    public static long getZipEocdCentralDirectorySizeBytes(ByteBuffer bytebuffer)
    {
        assertByteOrderLittleEndian(bytebuffer);
        return getUnsignedInt32(bytebuffer, bytebuffer.position() + 12);
    }

    public static final boolean isZip64EndOfCentralDirectoryLocatorPresent(RandomAccessFile randomaccessfile, long l)
        throws IOException
    {
        boolean flag = false;
        l -= 20L;
        if(l < 0L)
            return false;
        randomaccessfile.seek(l);
        if(randomaccessfile.readInt() == 0x504b0607)
            flag = true;
        return flag;
    }

    private static void setUnsignedInt32(ByteBuffer bytebuffer, int i, long l)
    {
        if(l < 0L || l > 0xffffffffL)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("uint32 value of out range: ").append(l).toString());
        } else
        {
            bytebuffer.putInt(bytebuffer.position() + i, (int)l);
            return;
        }
    }

    public static void setZipEocdCentralDirectoryOffset(ByteBuffer bytebuffer, long l)
    {
        assertByteOrderLittleEndian(bytebuffer);
        setUnsignedInt32(bytebuffer, bytebuffer.position() + 16, l);
    }

    private static final int UINT16_MAX_VALUE = 65535;
    private static final int ZIP64_EOCD_LOCATOR_SIG_REVERSE_BYTE_ORDER = 0x504b0607;
    private static final int ZIP64_EOCD_LOCATOR_SIZE = 20;
    private static final int ZIP_EOCD_CENTRAL_DIR_OFFSET_FIELD_OFFSET = 16;
    private static final int ZIP_EOCD_CENTRAL_DIR_SIZE_FIELD_OFFSET = 12;
    private static final int ZIP_EOCD_COMMENT_LENGTH_FIELD_OFFSET = 20;
    private static final int ZIP_EOCD_REC_MIN_SIZE = 22;
    private static final int ZIP_EOCD_REC_SIG = 0x6054b50;
}
