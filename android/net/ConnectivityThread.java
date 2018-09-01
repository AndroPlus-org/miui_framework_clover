// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.HandlerThread;
import android.os.Looper;

public final class ConnectivityThread extends HandlerThread
{
    private static class Singleton
    {

        static ConnectivityThread _2D_get0()
        {
            return INSTANCE;
        }

        private static final ConnectivityThread INSTANCE = ConnectivityThread._2D_wrap0();


        private Singleton()
        {
        }
    }


    static ConnectivityThread _2D_wrap0()
    {
        return createInstance();
    }

    private ConnectivityThread()
    {
        super("ConnectivityThread");
    }

    private static ConnectivityThread createInstance()
    {
        ConnectivityThread connectivitythread = new ConnectivityThread();
        connectivitythread.start();
        return connectivitythread;
    }

    public static ConnectivityThread get()
    {
        return Singleton._2D_get0();
    }

    public static Looper getInstanceLooper()
    {
        return Singleton._2D_get0().getLooper();
    }
}
