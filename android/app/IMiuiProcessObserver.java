// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;


public abstract class IMiuiProcessObserver extends IProcessObserver.Stub
{

    public IMiuiProcessObserver()
    {
    }

    public abstract void onForegroundActivitiesChanged(int i, int j, boolean flag);

    public abstract void onImportanceChanged(int i, int j, int k);

    public abstract void onProcessDied(int i, int j);

    public abstract void onProcessStateChanged(int i, int j, int k);
}
