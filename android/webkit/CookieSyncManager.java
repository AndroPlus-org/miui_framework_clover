// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;

import android.content.Context;

// Referenced classes of package android.webkit:
//            WebSyncManager, CookieManager

public final class CookieSyncManager extends WebSyncManager
{

    private CookieSyncManager()
    {
        super(null, null);
    }

    private static void checkInstanceIsAllowed()
    {
        if(!sGetInstanceAllowed)
            throw new IllegalStateException("CookieSyncManager::createInstance() needs to be called before CookieSyncManager::getInstance()");
        else
            return;
    }

    public static CookieSyncManager createInstance(Context context)
    {
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        if(context != null)
            break MISSING_BLOCK_LABEL_27;
        context = JVM INSTR new #38  <Class IllegalArgumentException>;
        context.IllegalArgumentException("Invalid context argument");
        throw context;
        context;
        obj;
        JVM INSTR monitorexit ;
        throw context;
        setGetInstanceIsAllowed();
        context = getInstance();
        obj;
        JVM INSTR monitorexit ;
        return context;
    }

    public static CookieSyncManager getInstance()
    {
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        CookieSyncManager cookiesyncmanager1;
        checkInstanceIsAllowed();
        if(sRef == null)
        {
            CookieSyncManager cookiesyncmanager = JVM INSTR new #2   <Class CookieSyncManager>;
            cookiesyncmanager.CookieSyncManager();
            sRef = cookiesyncmanager;
        }
        cookiesyncmanager1 = sRef;
        obj;
        JVM INSTR monitorexit ;
        return cookiesyncmanager1;
        Exception exception;
        exception;
        throw exception;
    }

    static void setGetInstanceIsAllowed()
    {
        sGetInstanceAllowed = true;
    }

    public void resetSync()
    {
    }

    public volatile void run()
    {
        super.run();
    }

    public void startSync()
    {
    }

    public void stopSync()
    {
    }

    public void sync()
    {
        CookieManager.getInstance().flush();
    }

    protected void syncFromRamToFlash()
    {
        CookieManager.getInstance().flush();
    }

    private static boolean sGetInstanceAllowed = false;
    private static final Object sLock = new Object();
    private static CookieSyncManager sRef;

}
