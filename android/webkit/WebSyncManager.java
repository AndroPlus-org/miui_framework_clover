// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;

import android.content.Context;
import android.os.Handler;

// Referenced classes of package android.webkit:
//            WebViewDatabase

abstract class WebSyncManager
    implements Runnable
{

    protected WebSyncManager(Context context, String s)
    {
    }

    protected Object clone()
        throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException("doesn't implement Cloneable");
    }

    protected void onSyncInit()
    {
    }

    public void resetSync()
    {
    }

    public void run()
    {
    }

    public void startSync()
    {
    }

    public void stopSync()
    {
    }

    public void sync()
    {
    }

    abstract void syncFromRamToFlash();

    protected static final String LOGTAG = "websync";
    protected WebViewDatabase mDataBase;
    protected Handler mHandler;
}
