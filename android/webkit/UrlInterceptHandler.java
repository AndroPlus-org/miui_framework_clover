// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;

import java.util.Map;

// Referenced classes of package android.webkit:
//            PluginData

public interface UrlInterceptHandler
{

    public abstract PluginData getPluginData(String s, Map map);

    public abstract CacheManager.CacheResult service(String s, Map map);
}
