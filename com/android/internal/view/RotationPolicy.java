// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.graphics.Point;
import android.net.Uri;
import android.os.*;
import android.util.Log;
import android.view.IWindowManager;
import android.view.WindowManagerGlobal;

public final class RotationPolicy
{
    public static abstract class RotationPolicyListener
    {

        public abstract void onChange();

        final ContentObserver mObserver = new _cls1(new Handler());

        public RotationPolicyListener()
        {
        }
    }


    private RotationPolicy()
    {
    }

    private static boolean areAllRotationsAllowed(Context context)
    {
        return context.getResources().getBoolean(0x1120006);
    }

    public static int getRotationLockOrientation(Context context)
    {
        Point point;
        if(areAllRotationsAllowed(context))
            break MISSING_BLOCK_LABEL_60;
        point = new Point();
        context = WindowManagerGlobal.getWindowManagerService();
        int i;
        int j;
        context.getInitialDisplaySize(0, point);
        i = point.x;
        j = point.y;
        if(i < j)
            j = 1;
        else
            j = 2;
        return j;
        context;
        Log.w("RotationPolicy", "Unable to get the display size");
        return 0;
    }

    public static boolean isRotationLockToggleVisible(Context context)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(isRotationSupported(context))
        {
            flag1 = flag;
            if(android.provider.Settings.System.getIntForUser(context.getContentResolver(), "hide_rotation_lock_toggle_for_accessibility", 0, -2) == 0)
                flag1 = true;
        }
        return flag1;
    }

    public static boolean isRotationLocked(Context context)
    {
        boolean flag = false;
        if(android.provider.Settings.System.getIntForUser(context.getContentResolver(), "accelerometer_rotation", 0, -2) == 0)
            flag = true;
        return flag;
    }

    public static boolean isRotationSupported(Context context)
    {
        PackageManager packagemanager = context.getPackageManager();
        boolean flag;
        if(packagemanager.hasSystemFeature("android.hardware.sensor.accelerometer") && packagemanager.hasSystemFeature("android.hardware.screen.portrait") && packagemanager.hasSystemFeature("android.hardware.screen.landscape"))
            flag = context.getResources().getBoolean(0x11200a9);
        else
            flag = false;
        return flag;
    }

    public static void registerRotationPolicyListener(Context context, RotationPolicyListener rotationpolicylistener)
    {
        registerRotationPolicyListener(context, rotationpolicylistener, UserHandle.getCallingUserId());
    }

    public static void registerRotationPolicyListener(Context context, RotationPolicyListener rotationpolicylistener, int i)
    {
        context.getContentResolver().registerContentObserver(android.provider.Settings.System.getUriFor("accelerometer_rotation"), false, rotationpolicylistener.mObserver, i);
        context.getContentResolver().registerContentObserver(android.provider.Settings.System.getUriFor("hide_rotation_lock_toggle_for_accessibility"), false, rotationpolicylistener.mObserver, i);
    }

    public static void setRotationLock(Context context, boolean flag)
    {
        android.provider.Settings.System.putIntForUser(context.getContentResolver(), "hide_rotation_lock_toggle_for_accessibility", 0, -2);
        byte byte0;
        if(areAllRotationsAllowed(context))
            byte0 = -1;
        else
            byte0 = 0;
        setRotationLock(flag, byte0);
    }

    private static void setRotationLock(boolean flag, int i)
    {
        AsyncTask.execute(new Runnable(flag, i) {

            public void run()
            {
                IWindowManager iwindowmanager;
                iwindowmanager = WindowManagerGlobal.getWindowManagerService();
                if(!enabled)
                    break MISSING_BLOCK_LABEL_22;
                iwindowmanager.freezeRotation(rotation);
_L1:
                return;
                try
                {
                    iwindowmanager.thawRotation();
                }
                catch(RemoteException remoteexception)
                {
                    Log.w("RotationPolicy", "Unable to save auto-rotate setting");
                }
                  goto _L1
            }

            final boolean val$enabled;
            final int val$rotation;

            
            {
                enabled = flag;
                rotation = i;
                super();
            }
        }
);
    }

    public static void setRotationLockForAccessibility(Context context, boolean flag)
    {
        context = context.getContentResolver();
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        android.provider.Settings.System.putIntForUser(context, "hide_rotation_lock_toggle_for_accessibility", i, -2);
        setRotationLock(flag, 0);
    }

    public static void unregisterRotationPolicyListener(Context context, RotationPolicyListener rotationpolicylistener)
    {
        context.getContentResolver().unregisterContentObserver(rotationpolicylistener.mObserver);
    }

    private static final int CURRENT_ROTATION = -1;
    private static final int NATURAL_ROTATION = 0;
    private static final String TAG = "RotationPolicy";

    // Unreferenced inner class com/android/internal/view/RotationPolicy$RotationPolicyListener$1

/* anonymous class */
    class RotationPolicyListener._cls1 extends ContentObserver
    {

        public void onChange(boolean flag, Uri uri)
        {
            RotationPolicyListener.this.onChange();
        }

        final RotationPolicyListener this$1;

            
            {
                this$1 = RotationPolicyListener.this;
                super(handler);
            }
    }

}
