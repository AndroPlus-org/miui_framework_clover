// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.statistics;

import android.os.Parcel;

public class PerfEventSocket
{

    public PerfEventSocket()
    {
    }

    public static native int receivePerfEvent(int i, Parcel parcel, int j);

    public static native int sendPerfEvent(int i, Parcel parcel, int j);
}
