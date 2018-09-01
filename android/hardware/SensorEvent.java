// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware;


// Referenced classes of package android.hardware:
//            Sensor

public class SensorEvent
{

    SensorEvent(int i)
    {
        values = new float[i];
    }

    public int accuracy;
    public Sensor sensor;
    public long timestamp;
    public final float values[];
}
