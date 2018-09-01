// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.metrics;


public class WakeupEvent
{

    public WakeupEvent()
    {
    }

    public String toString()
    {
        return String.format("WakeupEvent(%tT.%tL, %s, uid: %d)", new Object[] {
            Long.valueOf(timestampMs), Long.valueOf(timestampMs), iface, Integer.valueOf(uid)
        });
    }

    public String iface;
    public long timestampMs;
    public int uid;
}
