// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.pm.EphemeralResolveInfo;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import java.util.*;

// Referenced classes of package android.app:
//            InstantAppResolverService

public abstract class EphemeralResolverService extends InstantAppResolverService
{

    public EphemeralResolverService()
    {
    }

    void _onGetInstantAppIntentFilter(int ai[], String s, String s1, InstantAppResolverService.InstantAppResolutionCallback instantappresolutioncallback)
    {
        if(DEBUG_EPHEMERAL)
            Log.d("PackageManager", (new StringBuilder()).append("Legacy resolver; getInstantAppIntentFilter; prefix: ").append(Arrays.toString(ai)).toString());
        ai = onGetEphemeralIntentFilter(s1);
        s = new ArrayList(1);
        s.add(ai.getInstantAppResolveInfo());
        instantappresolutioncallback.onInstantAppResolveInfo(s);
    }

    void _onGetInstantAppResolveInfo(int ai[], String s, InstantAppResolverService.InstantAppResolutionCallback instantappresolutioncallback)
    {
        if(DEBUG_EPHEMERAL)
            Log.d("PackageManager", (new StringBuilder()).append("Legacy resolver; getInstantAppResolveInfo; prefix: ").append(Arrays.toString(ai)).toString());
        ai = onGetEphemeralResolveInfo(ai);
        int i;
        if(ai == null)
            i = 0;
        else
            i = ai.size();
        s = new ArrayList(i);
        for(int j = 0; j < i; j++)
            s.add(((EphemeralResolveInfo)ai.get(j)).getInstantAppResolveInfo());

        instantappresolutioncallback.onInstantAppResolveInfo(s);
    }

    public Looper getLooper()
    {
        return super.getLooper();
    }

    public abstract List onEphemeralResolveInfoList(int ai[], int i);

    public EphemeralResolveInfo onGetEphemeralIntentFilter(String s)
    {
        throw new IllegalStateException("Must define");
    }

    public List onGetEphemeralResolveInfo(int ai[])
    {
        return onEphemeralResolveInfoList(ai, -4096);
    }

    private static final boolean DEBUG_EPHEMERAL;
    private static final String TAG = "PackageManager";

    static 
    {
        DEBUG_EPHEMERAL = Build.IS_DEBUGGABLE;
    }
}
