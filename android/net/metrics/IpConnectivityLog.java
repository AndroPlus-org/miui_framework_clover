// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.metrics;

import android.net.ConnectivityMetricsEvent;
import android.net.IIpConnectivityMetrics;
import android.os.*;
import android.util.Log;
import com.android.internal.util.BitUtils;

public class IpConnectivityLog
{

    public IpConnectivityLog()
    {
    }

    public IpConnectivityLog(IIpConnectivityMetrics iipconnectivitymetrics)
    {
        mService = iipconnectivitymetrics;
    }

    private boolean checkLoggerService()
    {
        if(mService != null)
            return true;
        IIpConnectivityMetrics iipconnectivitymetrics = android.net.IIpConnectivityMetrics.Stub.asInterface(ServiceManager.getService("connmetrics"));
        if(iipconnectivitymetrics == null)
        {
            return false;
        } else
        {
            mService = iipconnectivitymetrics;
            return true;
        }
    }

    private static ConnectivityMetricsEvent makeEv(Parcelable parcelable)
    {
        ConnectivityMetricsEvent connectivitymetricsevent = new ConnectivityMetricsEvent();
        connectivitymetricsevent.data = parcelable;
        return connectivitymetricsevent;
    }

    public boolean log(int i, int ai[], Parcelable parcelable)
    {
        parcelable = makeEv(parcelable);
        parcelable.netId = i;
        parcelable.transports = BitUtils.packBits(ai);
        return log(((ConnectivityMetricsEvent) (parcelable)));
    }

    public boolean log(long l, Parcelable parcelable)
    {
        parcelable = makeEv(parcelable);
        parcelable.timestamp = l;
        return log(((ConnectivityMetricsEvent) (parcelable)));
    }

    public boolean log(ConnectivityMetricsEvent connectivitymetricsevent)
    {
        boolean flag = false;
        if(!checkLoggerService())
            return false;
        if(connectivitymetricsevent.timestamp == 0L)
            connectivitymetricsevent.timestamp = System.currentTimeMillis();
        int i;
        try
        {
            i = mService.logEvent(connectivitymetricsevent);
        }
        // Misplaced declaration of an exception variable
        catch(ConnectivityMetricsEvent connectivitymetricsevent)
        {
            Log.e(TAG, "Error logging event", connectivitymetricsevent);
            return false;
        }
        if(i >= 0)
            flag = true;
        return flag;
    }

    public boolean log(Parcelable parcelable)
    {
        return log(makeEv(parcelable));
    }

    public boolean log(String s, Parcelable parcelable)
    {
        parcelable = makeEv(parcelable);
        parcelable.ifname = s;
        return log(((ConnectivityMetricsEvent) (parcelable)));
    }

    private static final boolean DBG = false;
    public static final String SERVICE_NAME = "connmetrics";
    private static final String TAG = android/net/metrics/IpConnectivityLog.getSimpleName();
    private IIpConnectivityMetrics mService;

}
