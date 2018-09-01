// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.ifaa.android.manager;

import android.content.Context;

public abstract class IFAAManager
{

    public IFAAManager()
    {
    }

    public abstract String getDeviceModel();

    public abstract int getSupportBIOTypes(Context context);

    public abstract int getVersion();

    public native byte[] processCmd(Context context, byte abyte0[]);

    public abstract int startBIOManager(Context context, int i);

    public static final int IFAA_VERSION_V2 = 2;
    static int mIfaaVer = 1;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 24)
            mIfaaVer = 2;
        else
            System.loadLibrary("teeclientjni");
    }
}
