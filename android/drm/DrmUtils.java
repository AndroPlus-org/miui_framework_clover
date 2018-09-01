// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.drm;

import java.io.*;
import java.util.*;

public class DrmUtils
{
    public static class ExtendedMetadataParser
    {

        private int readByte(byte abyte0[], int i)
        {
            return abyte0[i];
        }

        private String readMultipleBytes(byte abyte0[], int i, int j)
        {
            byte abyte1[] = new byte[i];
            int k = j;
            for(int l = 0; k < j + i; l++)
            {
                abyte1[l] = abyte0[k];
                k++;
            }

            return new String(abyte1);
        }

        public String get(String s)
        {
            return (String)mMap.get(s);
        }

        public Iterator iterator()
        {
            return mMap.values().iterator();
        }

        public Iterator keyIterator()
        {
            return mMap.keySet().iterator();
        }

        HashMap mMap;

        private ExtendedMetadataParser(byte abyte0[])
        {
            mMap = new HashMap();
            for(int i = 0; i < abyte0.length;)
            {
                int j = readByte(abyte0, i);
                int k = i + 1;
                i = readByte(abyte0, k);
                k++;
                String s = readMultipleBytes(abyte0, j, k);
                j = k + j;
                String s1 = readMultipleBytes(abyte0, i, j);
                String s2 = s1;
                if(s1.equals(" "))
                    s2 = "";
                i = j + i;
                mMap.put(s, s2);
            }

        }

        ExtendedMetadataParser(byte abyte0[], ExtendedMetadataParser extendedmetadataparser)
        {
            this(abyte0);
        }
    }


    public DrmUtils()
    {
    }

    public static ExtendedMetadataParser getExtendedMetadataParser(byte abyte0[])
    {
        return new ExtendedMetadataParser(abyte0, null);
    }

    private static void quietlyDispose(Closeable closeable)
    {
        if(closeable == null)
            break MISSING_BLOCK_LABEL_10;
        closeable.close();
_L2:
        return;
        closeable;
        if(true) goto _L2; else goto _L1
_L1:
    }

    static byte[] readBytes(File file)
        throws IOException
    {
        FileInputStream fileinputstream;
        BufferedInputStream bufferedinputstream;
        fileinputstream = new FileInputStream(file);
        bufferedinputstream = new BufferedInputStream(fileinputstream);
        file = null;
        int i = bufferedinputstream.available();
        if(i <= 0)
            break MISSING_BLOCK_LABEL_39;
        file = new byte[i];
        bufferedinputstream.read(file);
        quietlyDispose(bufferedinputstream);
        quietlyDispose(fileinputstream);
        return file;
        file;
        quietlyDispose(bufferedinputstream);
        quietlyDispose(fileinputstream);
        throw file;
    }

    static byte[] readBytes(String s)
        throws IOException
    {
        return readBytes(new File(s));
    }

    static void removeFile(String s)
        throws IOException
    {
        (new File(s)).delete();
    }

    static void writeToFile(String s, byte abyte0[])
        throws IOException
    {
        Object obj;
        obj = null;
        if(s == null || abyte0 == null)
            break MISSING_BLOCK_LABEL_28;
        FileOutputStream fileoutputstream;
        fileoutputstream = JVM INSTR new #66  <Class FileOutputStream>;
        fileoutputstream.FileOutputStream(s);
        fileoutputstream.write(abyte0);
        quietlyDispose(fileoutputstream);
        return;
        s;
        abyte0 = obj;
_L2:
        quietlyDispose(abyte0);
        throw s;
        s;
        abyte0 = fileoutputstream;
        if(true) goto _L2; else goto _L1
_L1:
    }
}
