// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.process;

import android.content.ComponentName;
import android.content.pm.ApplicationInfo;

public abstract class ProcessManagerInternal
{

    public ProcessManagerInternal()
    {
    }

    public abstract void forceStopPackage(String s, int i, String s1);

    public abstract void notifyActivityChanged(ComponentName componentname);

    public abstract void notifyForegroundInfoChanged(ApplicationInfo applicationinfo);

    public abstract void recordKillProcessEventIfNeeded(String s, String s1, int i);

    public abstract void updateProcessForegroundLocked(int i);
}
