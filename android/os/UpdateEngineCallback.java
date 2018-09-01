// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


public abstract class UpdateEngineCallback
{

    public UpdateEngineCallback()
    {
    }

    public abstract void onPayloadApplicationComplete(int i);

    public abstract void onStatusUpdate(int i, float f);
}
