// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware;


// Referenced classes of package android.hardware:
//            Sensor

public final class TriggerEvent
{

    TriggerEvent(int i)
    {
        values = new float[i];
    }

    public Sensor sensor;
    public long timestamp;
    public final float values[];
}
