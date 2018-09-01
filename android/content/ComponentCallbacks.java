// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.content.res.Configuration;

public interface ComponentCallbacks
{

    public abstract void onConfigurationChanged(Configuration configuration);

    public abstract void onLowMemory();
}
