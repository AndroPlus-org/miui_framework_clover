// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone.component;

import android.app.AppGlobals;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageManager;
import android.util.Log;
import android.util.Slog;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedList;
import miui.util.ReflectionUtils;

public abstract class ComponentHelper
{
    public static class WtLinkedRingbuffer
        implements Iterable
    {

        public void add(Object obj)
        {
            this;
            JVM INSTR monitorenter ;
            mList.add(obj);
            for(; mList.size() > mMaxSize; mList.remove(0));
            break MISSING_BLOCK_LABEL_42;
            obj;
            throw obj;
            this;
            JVM INSTR monitorexit ;
        }

        public Iterator iterator()
        {
            return mList.iterator();
        }

        public String toString()
        {
            this;
            JVM INSTR monitorenter ;
            Object obj;
            obj = JVM INSTR new #52  <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            for(Iterator iterator1 = mList.iterator(); iterator1.hasNext(); ((StringBuilder) (obj)).append(iterator1.next().toString()).append("\n"));
            break MISSING_BLOCK_LABEL_56;
            obj;
            throw obj;
            obj = ((StringBuilder) (obj)).toString();
            this;
            JVM INSTR monitorexit ;
            return ((String) (obj));
        }

        private LinkedList mList;
        private int mMaxSize;

        public WtLinkedRingbuffer(int i)
        {
            mList = new LinkedList();
            mMaxSize = i;
        }
    }


    private ComponentHelper()
    {
    }

    public static int getCallerAdj(int i)
    {
        if(mExtraActivityManagerService == null)
            break MISSING_BLOCK_LABEL_91;
        if(mGetCurAdjByPid == null)
            try
            {
                mGetCurAdjByPid = ReflectionUtils.findMethodExact(mExtraActivityManagerService, "getCurAdjByPid", new Class[] {
                    Integer.TYPE
                });
            }
            catch(NoSuchMethodException nosuchmethodexception)
            {
                Slog.e("WhetstonePackageState", Log.getStackTraceString(nosuchmethodexception));
            }
        if(mGetCurAdjByPid == null)
            break MISSING_BLOCK_LABEL_91;
        i = ((Integer)mGetCurAdjByPid.invoke(null, new Object[] {
            Integer.valueOf(i)
        })).intValue();
        return i;
        Exception exception;
        exception;
        Slog.e("WhetstonePackageState", Log.getStackTraceString(exception));
        return 0x7fffffff;
    }

    public static void init(Class class1)
    {
        mExtraActivityManagerService = class1;
    }

    public static boolean isCallerHasForegroundActivities(int i)
    {
        if(mExtraActivityManagerService == null)
            break MISSING_BLOCK_LABEL_91;
        boolean flag;
        if(mHasForegroundActivities == null)
            try
            {
                mHasForegroundActivities = ReflectionUtils.findMethodExact(mExtraActivityManagerService, "hasForegroundActivities", new Class[] {
                    Integer.TYPE
                });
            }
            catch(NoSuchMethodException nosuchmethodexception)
            {
                Slog.e("WhetstonePackageState", Log.getStackTraceString(nosuchmethodexception));
            }
        if(mHasForegroundActivities == null)
            break MISSING_BLOCK_LABEL_91;
        flag = ((Boolean)mHasForegroundActivities.invoke(null, new Object[] {
            Integer.valueOf(i)
        })).booleanValue();
        return flag;
        Exception exception;
        exception;
        Slog.e("WhetstonePackageState", Log.getStackTraceString(exception));
        return false;
    }

    public static boolean isCallerSystemUid(int i)
    {
        boolean flag;
        if(i < 10000)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean isSystemPackage(String s, int i)
    {
        boolean flag = true;
        boolean flag1;
        try
        {
            s = AppGlobals.getPackageManager().getApplicationInfo(s, 0, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Slog.e("WhetstonePackageState", Log.getStackTraceString(s));
            return true;
        }
        if(s == null)
            return true;
        i = ((ApplicationInfo) (s)).flags;
        flag1 = flag;
        if((i & 1) == 0)
            if((i & 0x80) != 0)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    private static final String TAG = "ComponentUtils";
    private static Class mExtraActivityManagerService = null;
    private static Method mGetCurAdjByPid;
    private static Method mHasForegroundActivities;

}
