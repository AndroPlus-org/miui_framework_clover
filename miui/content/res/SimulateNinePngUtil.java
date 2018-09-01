// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.content.res;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.zip.CRC32;

public class SimulateNinePngUtil
{
    private static class NinePathInputStream extends InputStream
    {

        public void close()
            throws IOException
        {
            if(mInputStream != null)
                mInputStream.close();
        }

        public int read()
            throws IOException
        {
            if(mCount < mExtraHeaderData.length)
            {
                byte abyte0[] = mExtraHeaderData;
                int i = mCount;
                mCount = i + 1;
                return abyte0[i];
            } else
            {
                return mInputStream.read();
            }
        }

        public int read(byte abyte0[], int i, int j)
            throws IOException
        {
            Arrays.checkOffsetAndCount(abyte0.length, i, j);
            int k;
            for(k = 0; mCount < mExtraHeaderData.length && k < j; k++)
            {
                byte abyte1[] = mExtraHeaderData;
                int l = mCount;
                mCount = l + 1;
                abyte0[i + k] = abyte1[l];
            }

            int i1 = k;
            if(k < j)
                i1 = k + mInputStream.read(abyte0, i + k, j - k);
            return i1;
        }

        private int mCount;
        private byte mExtraHeaderData[];
        private InputStream mInputStream;

        public NinePathInputStream(InputStream inputstream, byte abyte0[])
        {
            mCount = 0;
            mInputStream = inputstream;
            mExtraHeaderData = abyte0;
            mCount = 0;
        }
    }


    public SimulateNinePngUtil()
    {
    }

    private static int computePatchColor(byte abyte0[])
    {
        return 1;
    }

    private static int convertByteToIntByBigEndian(byte abyte0[], int i)
    {
        return ((abyte0[i + 0] & 0xff) << 24) + 0 + ((abyte0[i + 1] & 0xff) << 16) + ((abyte0[i + 2] & 0xff) << 8) + (abyte0[i + 3] & 0xff);
    }

    private static void convertBytesFromIntByBigEndian(byte abyte0[], int i, int j)
    {
        abyte0[i + 0] = (byte)(j >>> 24 & 0xff);
        abyte0[i + 1] = (byte)(j >> 16 & 0xff);
        abyte0[i + 2] = (byte)(j >> 8 & 0xff);
        abyte0[i + 3] = (byte)(j & 0xff);
    }

    public static byte[] convertIntoNinePngData(byte abyte0[])
    {
        while(abyte0 == null || abyte0.length < 41 || isPngFormat(abyte0) ^ true) 
            return null;
        if(isNinePngFormat(abyte0))
            return abyte0;
        byte abyte1[] = getNinePatchChunk(abyte0);
        byte abyte2[] = new byte[abyte0.length + 12 + abyte1.length];
        for(int i = 0; i < 33; i++)
            abyte2[i] = abyte0[i];

        convertBytesFromIntByBigEndian(abyte2, 33, abyte1.length);
        fillNinePngFormatMark(abyte2);
        for(int j = 0; j < abyte1.length; j++)
            abyte2[j + 41] = abyte1[j];

        int l = abyte1.length + 41;
        CRC32 crc32 = new CRC32();
        crc32.update(abyte2, 37, abyte1.length + 4);
        convertBytesFromIntByBigEndian(abyte2, l, (int)crc32.getValue());
        for(int k = 0; k < abyte0.length - 33; k++)
            abyte2[l + 4 + k] = abyte0[k + 33];

        return abyte2;
    }

    public static InputStream convertIntoNinePngStream(InputStream inputstream)
    {
        Object obj = null;
        byte abyte0[];
        int i;
        abyte0 = new byte[41];
        i = inputstream.read(abyte0);
        if(i > 0) goto _L2; else goto _L1
_L1:
        Object obj1 = null;
_L4:
        abyte0 = convertIntoNinePngData(((byte []) (obj1)));
        obj1 = obj;
        if(abyte0 != null)
            try
            {
                obj1 = JVM INSTR new #6   <Class SimulateNinePngUtil$NinePathInputStream>;
                ((NinePathInputStream) (obj1)).NinePathInputStream(inputstream, abyte0);
            }
            // Misplaced declaration of an exception variable
            catch(InputStream inputstream)
            {
                inputstream.printStackTrace();
                obj1 = obj;
            }
        return ((InputStream) (obj1));
_L2:
        obj1 = abyte0;
        if(i >= abyte0.length) goto _L4; else goto _L3
_L3:
        obj1 = Arrays.copyOf(abyte0, i);
          goto _L4
    }

    private static void fillNinePngFormatMark(byte abyte0[])
    {
        abyte0[37] = (byte)110;
        abyte0[38] = (byte)112;
        abyte0[39] = (byte)84;
        abyte0[40] = (byte)99;
    }

    private static byte[] getNinePatchChunk(byte abyte0[])
    {
        int i = convertByteToIntByBigEndian(abyte0, 16);
        int j = convertByteToIntByBigEndian(abyte0, 20);
        byte abyte1[] = new byte[52];
        abyte1[0] = (byte)1;
        abyte1[1] = (byte)2;
        abyte1[2] = (byte)2;
        abyte1[3] = (byte)1;
        convertBytesFromIntByBigEndian(abyte1, 36, i);
        convertBytesFromIntByBigEndian(abyte1, 44, j);
        convertBytesFromIntByBigEndian(abyte1, 48, computePatchColor(abyte0));
        return abyte1;
    }

    private static boolean isNinePngFormat(byte abyte0[])
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(abyte0 != null)
        {
            flag1 = flag;
            if(abyte0.length > 40)
            {
                flag1 = flag;
                if(abyte0[37] == 110)
                {
                    flag1 = flag;
                    if(abyte0[38] == 112)
                    {
                        flag1 = flag;
                        if(abyte0[39] == 84)
                        {
                            flag1 = flag;
                            if(abyte0[40] == 99)
                                flag1 = true;
                        }
                    }
                }
            }
        }
        return flag1;
    }

    private static boolean isPngFormat(byte abyte0[])
    {
        for(int i = 0; i < PNG_HEAD_FORMAT.length; i++)
            if(abyte0[i] != PNG_HEAD_FORMAT[i])
                return false;

        return true;
    }

    private static byte PNG_HEAD_FORMAT[] = {
        -119, 80, 78, 71, 13, 10, 26, 10
    };

}
