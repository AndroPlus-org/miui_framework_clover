// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;


public abstract class ServiceWorkerWebSettings
{

    public ServiceWorkerWebSettings()
    {
    }

    public abstract boolean getAllowContentAccess();

    public abstract boolean getAllowFileAccess();

    public abstract boolean getBlockNetworkLoads();

    public abstract int getCacheMode();

    public abstract void setAllowContentAccess(boolean flag);

    public abstract void setAllowFileAccess(boolean flag);

    public abstract void setBlockNetworkLoads(boolean flag);

    public abstract void setCacheMode(int i);
}
