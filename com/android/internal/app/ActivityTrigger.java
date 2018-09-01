// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.util.Log;

public class ActivityTrigger
{

    public ActivityTrigger()
    {
    }

    private native void native_at_deinit();

    private native float native_at_miscActivity(int i, String s, int j, int k);

    private native void native_at_pauseActivity(String s);

    private native void native_at_resumeActivity(String s);

    private native int native_at_startActivity(String s, int i);

    private native void native_at_stopActivity(String s);

    public float activityMiscTrigger(int i, String s, int j, int k)
    {
        return native_at_miscActivity(i, s, j, k);
    }

    public void activityPauseTrigger(Intent intent, ActivityInfo activityinfo, ApplicationInfo applicationinfo)
    {
        ComponentName componentname = intent.getComponent();
        activityinfo = null;
        Log.d("ActivityTrigger", "ActivityTrigger activityPauseTrigger ");
        intent = activityinfo;
        if(componentname != null)
        {
            intent = activityinfo;
            if(applicationinfo != null)
                intent = (new StringBuilder()).append(componentname.flattenToString()).append("/").append(applicationinfo.versionCode).toString();
        }
        native_at_pauseActivity(intent);
    }

    public void activityResumeTrigger(Intent intent, ActivityInfo activityinfo, ApplicationInfo applicationinfo, boolean flag)
    {
        activityinfo = intent.getComponent();
        intent = null;
        if(activityinfo != null)
            intent = (new StringBuilder()).append(activityinfo.flattenToString()).append("/").append(applicationinfo.versionCode).toString();
        native_at_resumeActivity(intent);
        if(flag)
        {
            Log.d("ActivityTrigger", (new StringBuilder()).append("activityResumeTrigger: The activity in ").append(applicationinfo).append(" is now in focus and seems to be in full-screen mode").toString());
            if(applicationinfo.isAppWhiteListed())
            {
                Log.d("ActivityTrigger", (new StringBuilder()).append("activityResumeTrigger: whiteListed ").append(intent).append(" appInfo.flags - ").append(Integer.toHexString(applicationinfo.flags)).toString());
                applicationinfo.setAppOverrideDensity();
            } else
            {
                applicationinfo.setOverrideDensity(0);
                Log.e("ActivityTrigger", (new StringBuilder()).append("activityResumeTrigger: not whiteListed").append(intent).toString());
            }
        } else
        {
            Log.d("ActivityTrigger", (new StringBuilder()).append("activityResumeTrigger: Activity is not Triggerred in full screen ").append(applicationinfo).toString());
            applicationinfo.setOverrideDensity(0);
        }
    }

    public void activityStartTrigger(Intent intent, ActivityInfo activityinfo, ApplicationInfo applicationinfo, boolean flag)
    {
        ComponentName componentname = intent.getComponent();
        intent = null;
        if(componentname != null)
            intent = (new StringBuilder()).append(componentname.flattenToString()).append("/").append(applicationinfo.versionCode).toString();
        int i = native_at_startActivity(intent, 0);
        if((i & 0x200) != 0)
            activityinfo.flags = activityinfo.flags | 0x200;
        if(flag)
        {
            Log.d("ActivityTrigger", (new StringBuilder()).append("activityStartTrigger: Activity is Triggerred in full screen ").append(applicationinfo).toString());
            if((i & 1) != 0)
            {
                Log.e("ActivityTrigger", (new StringBuilder()).append("activityStartTrigger: whiteListed ").append(intent).append(" appInfo.flags - ").append(Integer.toHexString(applicationinfo.flags)).toString());
                applicationinfo.setAppOverrideDensity();
                applicationinfo.setAppWhiteListed(1);
            } else
            {
                applicationinfo.setOverrideDensity(0);
                applicationinfo.setAppWhiteListed(0);
                Log.e("ActivityTrigger", (new StringBuilder()).append("activityStartTrigger: not whiteListed").append(intent).toString());
            }
        } else
        {
            Log.d("ActivityTrigger", (new StringBuilder()).append("activityStartTrigger: Activity is not Triggerred in full screen ").append(applicationinfo).toString());
            applicationinfo.setOverrideDensity(0);
        }
    }

    public void activityStopTrigger(Intent intent, ActivityInfo activityinfo, ApplicationInfo applicationinfo)
    {
        ComponentName componentname = intent.getComponent();
        activityinfo = null;
        Log.d("ActivityTrigger", "ActivityTrigger activityStopTrigger ");
        intent = activityinfo;
        if(componentname != null)
        {
            intent = activityinfo;
            if(applicationinfo != null)
                intent = (new StringBuilder()).append(componentname.flattenToString()).append("/").append(applicationinfo.versionCode).toString();
        }
        native_at_stopActivity(intent);
    }

    protected void finalize()
    {
        native_at_deinit();
    }

    public static final int ANIMATION_SCALE = 3;
    private static final int FLAG_HARDWARE_ACCELERATED = 512;
    private static final int FLAG_OVERRIDE_RESOLUTION = 1;
    public static final int NETWORK_OPTS = 2;
    public static final int START_PROCESS = 1;
    private static final String TAG = "ActivityTrigger";
}
