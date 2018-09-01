// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import java.util.Date;

abstract class Utils
{

    private Utils()
    {
    }

    static Date cloneIfNotNull(Date date)
    {
        Date date1 = null;
        if(date != null)
            date1 = (Date)date.clone();
        return date1;
    }

    static byte[] cloneIfNotNull(byte abyte0[])
    {
        byte abyte1[] = null;
        if(abyte0 != null)
            abyte1 = (byte[])abyte0.clone();
        return abyte1;
    }
}
