// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware;


public interface SensorListener
{

    public abstract void onAccuracyChanged(int i, int j);

    public abstract void onSensorChanged(int i, float af[]);
}
