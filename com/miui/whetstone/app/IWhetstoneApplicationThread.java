// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone.app;

import android.os.IInterface;
import android.os.RemoteException;

public interface IWhetstoneApplicationThread
    extends IInterface
{

    public abstract android.os.Debug.MemoryInfo dumpMemInfo(String as[])
        throws RemoteException;

    public abstract boolean longScreenshot(int i)
        throws RemoteException;

    public static final int DUMP_MEM_INFO_TRANSACTION = 1;
    public static final int LONG_SCREENSHOT_TRANSACTION = 2;
    public static final String descriptor = "com.miui.whetstone.app.IWhetstoneApplicationThread";
}
