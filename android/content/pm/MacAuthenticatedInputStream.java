// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import java.io.*;
import javax.crypto.Mac;

public class MacAuthenticatedInputStream extends FilterInputStream
{

    public MacAuthenticatedInputStream(InputStream inputstream, Mac mac)
    {
        super(inputstream);
        mMac = mac;
    }

    public boolean isTagEqual(byte abyte0[])
    {
        boolean flag = false;
        byte abyte1[];
        for(abyte1 = mMac.doFinal(); abyte0 == null || abyte1 == null || abyte0.length != abyte1.length;)
            return false;

        int i = 0;
        for(int j = 0; j < abyte0.length; j++)
            i |= abyte0[j] ^ abyte1[j];

        if(i == 0)
            flag = true;
        return flag;
    }

    public int read()
        throws IOException
    {
        int i = super.read();
        if(i >= 0)
            mMac.update((byte)i);
        return i;
    }

    public int read(byte abyte0[], int i, int j)
        throws IOException
    {
        j = super.read(abyte0, i, j);
        if(j > 0)
            mMac.update(abyte0, i, j);
        return j;
    }

    private final Mac mMac;
}
