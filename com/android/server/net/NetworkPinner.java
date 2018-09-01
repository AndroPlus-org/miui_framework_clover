// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.server.net;

import android.content.Context;
import android.net.*;
import android.util.Log;

public class NetworkPinner extends android.net.ConnectivityManager.NetworkCallback
{
    private static class Callback extends android.net.ConnectivityManager.NetworkCallback
    {

        public void onAvailable(Network network)
        {
            Object obj = NetworkPinner.sLock;
            obj;
            JVM INSTR monitorenter ;
            Callback callback = NetworkPinner._2D_get2();
            if(this == callback)
                break MISSING_BLOCK_LABEL_18;
            obj;
            JVM INSTR monitorexit ;
            return;
            if(NetworkPinner._2D_get1().getBoundNetworkForProcess() == null && NetworkPinner.sNetwork == null)
            {
                NetworkPinner._2D_get1().bindProcessToNetwork(network);
                NetworkPinner.sNetwork = network;
                String s = NetworkPinner._2D_get0();
                StringBuilder stringbuilder = JVM INSTR new #48  <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Log.d(s, stringbuilder.append("Wifi alternate reality enabled on network ").append(network).toString());
            }
            NetworkPinner.sLock.notify();
            obj;
            JVM INSTR monitorexit ;
            return;
            network;
            throw network;
        }

        public void onLost(Network network)
        {
            Object obj = NetworkPinner.sLock;
            obj;
            JVM INSTR monitorenter ;
            Callback callback = NetworkPinner._2D_get2();
            if(this == callback)
                break MISSING_BLOCK_LABEL_18;
            obj;
            JVM INSTR monitorexit ;
            return;
            if(network.equals(NetworkPinner.sNetwork) && network.equals(NetworkPinner._2D_get1().getBoundNetworkForProcess()))
            {
                NetworkPinner.unpin();
                String s = NetworkPinner._2D_get0();
                StringBuilder stringbuilder = JVM INSTR new #48  <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Log.d(s, stringbuilder.append("Wifi alternate reality disabled on network ").append(network).toString());
            }
            NetworkPinner.sLock.notify();
            obj;
            JVM INSTR monitorexit ;
            return;
            network;
            throw network;
        }

        private Callback()
        {
        }

        Callback(Callback callback)
        {
            this();
        }
    }


    static String _2D_get0()
    {
        return TAG;
    }

    static ConnectivityManager _2D_get1()
    {
        return sCM;
    }

    static Callback _2D_get2()
    {
        return sCallback;
    }

    public NetworkPinner()
    {
    }

    private static void maybeInitConnectivityManager(Context context)
    {
        if(sCM == null)
        {
            sCM = (ConnectivityManager)context.getSystemService("connectivity");
            if(sCM == null)
                throw new IllegalStateException("Bad luck, ConnectivityService not started.");
        }
    }

    public static void pin(Context context, NetworkRequest networkrequest)
    {
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        if(sCallback != null)
            break MISSING_BLOCK_LABEL_39;
        maybeInitConnectivityManager(context);
        context = JVM INSTR new #6   <Class NetworkPinner$Callback>;
        context.Callback(null);
        sCallback = context;
        sCM.registerNetworkCallback(networkrequest, sCallback);
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        context;
        Log.d(TAG, "Failed to register network callback", context);
        sCallback = null;
          goto _L1
        context;
        throw context;
    }

    public static void unpin()
    {
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        Callback callback = sCallback;
        if(callback == null) goto _L2; else goto _L1
_L1:
        sCM.bindProcessToNetwork(null);
        sCM.unregisterNetworkCallback(sCallback);
_L3:
        sCallback = null;
        sNetwork = null;
_L2:
        obj;
        JVM INSTR monitorexit ;
        return;
        Object obj1;
        obj1;
        Log.d(TAG, "Failed to unregister network callback", ((Throwable) (obj1)));
          goto _L3
        obj1;
        throw obj1;
    }

    private static final String TAG = com/android/server/net/NetworkPinner.getSimpleName();
    private static ConnectivityManager sCM;
    private static Callback sCallback;
    protected static final Object sLock = new Object();
    protected static Network sNetwork;

}
