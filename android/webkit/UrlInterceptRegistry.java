// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;

import java.util.*;

// Referenced classes of package android.webkit:
//            UrlInterceptHandler, PluginData

public final class UrlInterceptRegistry
{

    public UrlInterceptRegistry()
    {
    }

    private static LinkedList getHandlers()
    {
        android/webkit/UrlInterceptRegistry;
        JVM INSTR monitorenter ;
        LinkedList linkedlist1;
        if(mHandlerList == null)
        {
            LinkedList linkedlist = JVM INSTR new #27  <Class LinkedList>;
            linkedlist.LinkedList();
            mHandlerList = linkedlist;
        }
        linkedlist1 = mHandlerList;
        android/webkit/UrlInterceptRegistry;
        JVM INSTR monitorexit ;
        return linkedlist1;
        Exception exception;
        exception;
        throw exception;
    }

    public static PluginData getPluginData(String s, Map map)
    {
        android/webkit/UrlInterceptRegistry;
        JVM INSTR monitorenter ;
        boolean flag = urlInterceptDisabled();
        if(!flag)
            break MISSING_BLOCK_LABEL_16;
        android/webkit/UrlInterceptRegistry;
        JVM INSTR monitorexit ;
        return null;
        java.util.ListIterator listiterator = getHandlers().listIterator();
        PluginData plugindata;
        do
        {
            if(!listiterator.hasNext())
                break MISSING_BLOCK_LABEL_61;
            plugindata = ((UrlInterceptHandler)listiterator.next()).getPluginData(s, map);
        } while(plugindata == null);
        android/webkit/UrlInterceptRegistry;
        JVM INSTR monitorexit ;
        return plugindata;
        android/webkit/UrlInterceptRegistry;
        JVM INSTR monitorexit ;
        return null;
        s;
        throw s;
    }

    public static CacheManager.CacheResult getSurrogate(String s, Map map)
    {
        android/webkit/UrlInterceptRegistry;
        JVM INSTR monitorenter ;
        boolean flag = urlInterceptDisabled();
        if(!flag)
            break MISSING_BLOCK_LABEL_16;
        android/webkit/UrlInterceptRegistry;
        JVM INSTR monitorexit ;
        return null;
        java.util.ListIterator listiterator = getHandlers().listIterator();
        CacheManager.CacheResult cacheresult;
        do
        {
            if(!listiterator.hasNext())
                break MISSING_BLOCK_LABEL_61;
            cacheresult = ((UrlInterceptHandler)listiterator.next()).service(s, map);
        } while(cacheresult == null);
        android/webkit/UrlInterceptRegistry;
        JVM INSTR monitorexit ;
        return cacheresult;
        android/webkit/UrlInterceptRegistry;
        JVM INSTR monitorexit ;
        return null;
        s;
        throw s;
    }

    public static boolean registerHandler(UrlInterceptHandler urlintercepthandler)
    {
        android/webkit/UrlInterceptRegistry;
        JVM INSTR monitorenter ;
        if(getHandlers().contains(urlintercepthandler))
            break MISSING_BLOCK_LABEL_25;
        getHandlers().addFirst(urlintercepthandler);
        android/webkit/UrlInterceptRegistry;
        JVM INSTR monitorexit ;
        return true;
        android/webkit/UrlInterceptRegistry;
        JVM INSTR monitorexit ;
        return false;
        urlintercepthandler;
        throw urlintercepthandler;
    }

    public static void setUrlInterceptDisabled(boolean flag)
    {
        android/webkit/UrlInterceptRegistry;
        JVM INSTR monitorenter ;
        mDisabled = flag;
        android/webkit/UrlInterceptRegistry;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public static boolean unregisterHandler(UrlInterceptHandler urlintercepthandler)
    {
        android/webkit/UrlInterceptRegistry;
        JVM INSTR monitorenter ;
        boolean flag = getHandlers().remove(urlintercepthandler);
        android/webkit/UrlInterceptRegistry;
        JVM INSTR monitorexit ;
        return flag;
        urlintercepthandler;
        throw urlintercepthandler;
    }

    public static boolean urlInterceptDisabled()
    {
        android/webkit/UrlInterceptRegistry;
        JVM INSTR monitorenter ;
        boolean flag = mDisabled;
        android/webkit/UrlInterceptRegistry;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    private static final String LOGTAG = "intercept";
    private static boolean mDisabled = false;
    private static LinkedList mHandlerList;

}
