// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import java.io.*;

public class BackupUtils
{
    public static class BadVersionException extends Exception
    {

        public BadVersionException(String s)
        {
            super(s);
        }
    }


    public BackupUtils()
    {
    }

    public static String readString(DataInputStream datainputstream)
        throws IOException
    {
        if(datainputstream.readByte() == 1)
            datainputstream = datainputstream.readUTF();
        else
            datainputstream = null;
        return datainputstream;
    }

    public static void writeString(DataOutputStream dataoutputstream, String s)
        throws IOException
    {
        if(s != null)
        {
            dataoutputstream.writeByte(1);
            dataoutputstream.writeUTF(s);
        } else
        {
            dataoutputstream.writeByte(0);
        }
    }

    public static final int NOT_NULL = 1;
    public static final int NULL = 0;
}
