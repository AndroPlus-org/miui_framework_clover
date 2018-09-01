// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.usage;

import android.content.ComponentName;
import android.content.res.Configuration;
import java.util.List;

public abstract class UsageStatsManagerInternal
{
    public static abstract class AppIdleStateChangeListener
    {

        public abstract void onAppIdleStateChanged(String s, int i, boolean flag);

        public abstract void onParoleStateChanged(boolean flag);

        public AppIdleStateChangeListener()
        {
        }
    }


    public UsageStatsManagerInternal()
    {
    }

    public abstract void addAppIdleStateChangeListener(AppIdleStateChangeListener appidlestatechangelistener);

    public abstract void applyRestoredPayload(int i, String s, byte abyte0[]);

    public abstract byte[] getBackupPayload(int i, String s);

    public abstract int[] getIdleUidsForUser(int i);

    public abstract boolean isAppIdle(String s, int i, int j);

    public abstract boolean isAppIdleParoleOn();

    public abstract void prepareShutdown();

    public abstract List queryUsageStatsForUser(int i, int j, long l, long l1, boolean flag);

    public abstract void removeAppIdleStateChangeListener(AppIdleStateChangeListener appidlestatechangelistener);

    public abstract void reportConfigurationChange(Configuration configuration, int i);

    public abstract void reportContentProviderUsage(String s, String s1, int i);

    public abstract void reportEvent(ComponentName componentname, int i, int j);

    public abstract void reportEvent(String s, int i, int j);

    public abstract void reportShortcutUsage(String s, String s1, int i);
}
