// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.net;

import android.app.PendingIntent;
import android.net.NetworkInfo;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class LegacyVpnInfo
    implements Parcelable
{

    private static int[] _2D_getandroid_2D_net_2D_NetworkInfo$DetailedStateSwitchesValues()
    {
        if(_2D_android_2D_net_2D_NetworkInfo$DetailedStateSwitchesValues != null)
            return _2D_android_2D_net_2D_NetworkInfo$DetailedStateSwitchesValues;
        int ai[] = new int[android.net.NetworkInfo.DetailedState.values().length];
        try
        {
            ai[android.net.NetworkInfo.DetailedState.AUTHENTICATING.ordinal()] = 5;
        }
        catch(NoSuchFieldError nosuchfielderror12) { }
        try
        {
            ai[android.net.NetworkInfo.DetailedState.BLOCKED.ordinal()] = 6;
        }
        catch(NoSuchFieldError nosuchfielderror11) { }
        try
        {
            ai[android.net.NetworkInfo.DetailedState.CAPTIVE_PORTAL_CHECK.ordinal()] = 7;
        }
        catch(NoSuchFieldError nosuchfielderror10) { }
        try
        {
            ai[android.net.NetworkInfo.DetailedState.CONNECTED.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror9) { }
        try
        {
            ai[android.net.NetworkInfo.DetailedState.CONNECTING.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror8) { }
        try
        {
            ai[android.net.NetworkInfo.DetailedState.DISCONNECTED.ordinal()] = 3;
        }
        catch(NoSuchFieldError nosuchfielderror7) { }
        try
        {
            ai[android.net.NetworkInfo.DetailedState.DISCONNECTING.ordinal()] = 8;
        }
        catch(NoSuchFieldError nosuchfielderror6) { }
        try
        {
            ai[android.net.NetworkInfo.DetailedState.FAILED.ordinal()] = 4;
        }
        catch(NoSuchFieldError nosuchfielderror5) { }
        try
        {
            ai[android.net.NetworkInfo.DetailedState.IDLE.ordinal()] = 9;
        }
        catch(NoSuchFieldError nosuchfielderror4) { }
        try
        {
            ai[android.net.NetworkInfo.DetailedState.OBTAINING_IPADDR.ordinal()] = 10;
        }
        catch(NoSuchFieldError nosuchfielderror3) { }
        try
        {
            ai[android.net.NetworkInfo.DetailedState.SCANNING.ordinal()] = 11;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            ai[android.net.NetworkInfo.DetailedState.SUSPENDED.ordinal()] = 12;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            ai[android.net.NetworkInfo.DetailedState.VERIFYING_POOR_LINK.ordinal()] = 13;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        _2D_android_2D_net_2D_NetworkInfo$DetailedStateSwitchesValues = ai;
        return ai;
    }

    public LegacyVpnInfo()
    {
        state = -1;
    }

    public static int stateFromNetworkInfo(NetworkInfo networkinfo)
    {
        switch(_2D_getandroid_2D_net_2D_NetworkInfo$DetailedStateSwitchesValues()[networkinfo.getDetailedState().ordinal()])
        {
        default:
            Log.w("LegacyVpnInfo", (new StringBuilder()).append("Unhandled state ").append(networkinfo.getDetailedState()).append(" ; treating as disconnected").toString());
            return 0;

        case 2: // '\002'
            return 2;

        case 1: // '\001'
            return 3;

        case 3: // '\003'
            return 0;

        case 4: // '\004'
            return 5;
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(key);
        parcel.writeInt(state);
        parcel.writeParcelable(intent, i);
    }

    private static final int _2D_android_2D_net_2D_NetworkInfo$DetailedStateSwitchesValues[];
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public LegacyVpnInfo createFromParcel(Parcel parcel)
        {
            LegacyVpnInfo legacyvpninfo = new LegacyVpnInfo();
            legacyvpninfo.key = parcel.readString();
            legacyvpninfo.state = parcel.readInt();
            legacyvpninfo.intent = (PendingIntent)parcel.readParcelable(null);
            return legacyvpninfo;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public LegacyVpnInfo[] newArray(int i)
        {
            return new LegacyVpnInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int STATE_CONNECTED = 3;
    public static final int STATE_CONNECTING = 2;
    public static final int STATE_DISCONNECTED = 0;
    public static final int STATE_FAILED = 5;
    public static final int STATE_INITIALIZING = 1;
    public static final int STATE_TIMEOUT = 4;
    private static final String TAG = "LegacyVpnInfo";
    public PendingIntent intent;
    public String key;
    public int state;

}
