// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone;

import android.util.Slog;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.*;

public class WhetstoneSleepCleanConfig
{
    private class PackageInfo
    {

        private String cleanType2String(int i)
        {
            String s = null;
            i;
            JVM INSTR tableswitch 0 2: default 28
        //                       0 30
        //                       1 36
        //                       2 42;
               goto _L1 _L2 _L3 _L4
_L1:
            return s;
_L2:
            s = "CLEAN_TYPE_ALL";
            continue; /* Loop/switch isn't completed */
_L3:
            s = "CLEAN_TYPE_UI";
            continue; /* Loop/switch isn't completed */
_L4:
            s = "CLEAN_TYPE_PROTECTED";
            if(true) goto _L1; else goto _L5
_L5:
        }

        private String limitStart2String(int i)
        {
            String s = null;
            i;
            JVM INSTR tableswitch 0 2: default 28
        //                       0 30
        //                       1 36
        //                       2 42;
               goto _L1 _L2 _L3 _L4
_L1:
            return s;
_L2:
            s = "LIMIT_START_ALL_WAYS";
            continue; /* Loop/switch isn't completed */
_L3:
            s = "LIMIT_START_EXCEPT_UI";
            continue; /* Loop/switch isn't completed */
_L4:
            s = "LIMIT_START_NOT";
            if(true) goto _L1; else goto _L5
_L5:
        }

        public String toString()
        {
            return (new StringBuilder()).append("packageName = ").append(packageName).append(", cleanType = ").append(cleanType).append(", limitStart = ").append(limitStart).toString();
        }

        int cleanType;
        int limitStart;
        String packageName;
        final WhetstoneSleepCleanConfig this$0;

        PackageInfo(String s, int i, int j)
        {
            this$0 = WhetstoneSleepCleanConfig.this;
            super();
            packageName = s;
            cleanType = i;
            limitStart = j;
        }
    }


    public WhetstoneSleepCleanConfig()
    {
        mSleepState = 0;
        mWhiteList = new ArrayList();
        mProtecedProcess = new ArrayList();
    }

    public WhetstoneSleepCleanConfig(WhetstoneSleepCleanConfig whetstonesleepcleanconfig)
    {
        mSleepState = 0;
        mWhiteList = new ArrayList(whetstonesleepcleanconfig.getWhiteList());
        mProtecedProcess = new ArrayList(whetstonesleepcleanconfig.getProtectedProcess());
    }

    public boolean addPackageControlCleanInfo2Config(String s, int i)
    {
        if(s == null)
        {
            Slog.e("WhetstoneSleepCleanConfig", "error, sleep mode packageName is null ");
            return false;
        }
        for(Iterator iterator = mWhiteList.iterator(); iterator.hasNext();)
        {
            PackageInfo packageinfo = (PackageInfo)iterator.next();
            if(packageinfo.packageName != null && packageinfo.packageName.equals(s))
            {
                packageinfo.cleanType = i;
                Slog.d("WhetstoneSleepCleanConfig", (new StringBuilder()).append("update the package ").append(s).append(" sleep mode info clean type ").append(i).append(" the limit is ").append(packageinfo.limitStart).toString());
                return true;
            }
        }

        s = new PackageInfo(s, i, 0);
        mWhiteList.add(s);
        return true;
    }

    public boolean addPackageControlInfo2Config(String s, int i, int j)
    {
        if(s == null)
        {
            Slog.e("WhetstoneSleepCleanConfig", "error, sleep mode packageName is null ");
            return false;
        }
        for(Iterator iterator = mWhiteList.iterator(); iterator.hasNext();)
        {
            PackageInfo packageinfo = (PackageInfo)iterator.next();
            if(packageinfo.packageName != null && packageinfo.packageName.equals(s))
            {
                packageinfo.cleanType = i;
                packageinfo.limitStart = j;
                Slog.d("WhetstoneSleepCleanConfig", (new StringBuilder()).append("update the package ").append(s).append(" sleep mode info clean type ").append(i).append(" the limit is ").append(j).toString());
                return true;
            }
        }

        s = new PackageInfo(s, i, j);
        mWhiteList.add(s);
        return true;
    }

    public boolean addPackageControlStartInfo2Config(String s, int i)
    {
        if(s == null)
        {
            Slog.e("WhetstoneSleepCleanConfig", "error, sleep mode packageName is null ");
            return false;
        }
        for(Iterator iterator = mWhiteList.iterator(); iterator.hasNext();)
        {
            PackageInfo packageinfo = (PackageInfo)iterator.next();
            if(packageinfo.packageName != null && packageinfo.packageName.equals(s))
            {
                packageinfo.limitStart = i;
                Slog.d("WhetstoneSleepCleanConfig", (new StringBuilder()).append("update the package ").append(s).append(" sleep mode info clean type ").append(packageinfo.cleanType).append(" the limit is ").append(i).toString());
                return true;
            }
        }

        s = new PackageInfo(s, 0, i);
        mWhiteList.add(s);
        return true;
    }

    public void addProtectedProcessList(String s)
    {
        mProtecedProcess.add(s);
    }

    public void addProtectedProcessList(List list)
    {
        if(list == null)
        {
            Slog.e("WhetstoneSleepCleanConfig", "sleep mode add the process list is null");
            mProtecedProcess.clear();
            return;
        } else
        {
            Slog.d("WhetstoneSleepCleanConfig", (new StringBuilder()).append("Process White List : ").append(list.toString()).toString());
            mProtecedProcess = list;
            return;
        }
    }

    public boolean checkIfProcessBelongProtected(String s, String s1, boolean flag)
    {
label0:
        {
            if(s == null || s1 == null)
                return false;
            String s2 = (new StringBuilder()).append(s1).append("/").append(s).toString();
            if(mSleepState != 1 && mSleepState != 3)
                break label0;
            s = mProtecedProcess.iterator();
            do
                if(!s.hasNext())
                    break label0;
            while(!s2.equalsIgnoreCase((String)s.next()));
            return true;
        }
        for(s = mWhiteList.iterator(); s.hasNext();)
        {
            PackageInfo packageinfo = (PackageInfo)s.next();
            if(packageinfo.packageName.equals(s1))
            {
                if(packageinfo.cleanType == 1 && flag)
                    return true;
                if(packageinfo.cleanType == 2)
                    return true;
            }
        }

        return false;
    }

    public boolean checkIfProcessStartAllow(String s, String s1, boolean flag)
    {
label0:
        {
            if(s == null || s1 == null)
                return false;
            String s2 = (new StringBuilder()).append(s1).append("/").append(s).toString();
            if(mSleepState != 1 && mSleepState != 3)
                break label0;
            s = mProtecedProcess.iterator();
            do
                if(!s.hasNext())
                    break label0;
            while(!s2.equalsIgnoreCase((String)s.next()));
            return true;
        }
        for(Iterator iterator = mWhiteList.iterator(); iterator.hasNext();)
        {
            s = (PackageInfo)iterator.next();
            if(((PackageInfo) (s)).packageName.equals(s1))
            {
                if(((PackageInfo) (s)).limitStart == 1 && flag ^ true)
                    return true;
                if(((PackageInfo) (s)).limitStart == 2)
                    return true;
            }
        }

        return false;
    }

    public void clearData()
    {
        mWhiteList.clear();
        mProtecedProcess.clear();
    }

    public void dump()
    {
        Slog.d("WhetstoneSleepCleanConfig", "===================dump sleep mode config info======================");
        for(Iterator iterator = mWhiteList.iterator(); iterator.hasNext(); Slog.d("WhetstoneSleepCleanConfig", ((PackageInfo)iterator.next()).toString()));
        Slog.d("WhetstoneSleepCleanConfig", "dump the protected process list");
        for(Iterator iterator1 = mProtecedProcess.iterator(); iterator1.hasNext(); Slog.d("WhetstoneSleepCleanConfig", ((String)iterator1.next()).toString()));
    }

    public void dump(FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        printwriter.println("===================dump sleep mode config info======================");
        for(filedescriptor = mWhiteList.iterator(); filedescriptor.hasNext(); printwriter.println(((PackageInfo)filedescriptor.next()).toString()));
        printwriter.println("dump the protected process list");
        for(filedescriptor = mProtecedProcess.iterator(); filedescriptor.hasNext(); printwriter.println(((String)filedescriptor.next()).toString()));
        printwriter.flush();
    }

    public List getProtectedProcess()
    {
        return mProtecedProcess;
    }

    public List getWhiteList()
    {
        return mWhiteList;
    }

    public void setSleepState(int i)
    {
        mSleepState = i;
    }

    public String toString()
    {
        String s = "mWhiteList : \n";
        for(Iterator iterator = mWhiteList.iterator(); iterator.hasNext();)
        {
            PackageInfo packageinfo = (PackageInfo)iterator.next();
            s = (new StringBuilder()).append(s).append(packageinfo.toString()).append("\n").toString();
        }

        s = (new StringBuilder()).append(s).append("mProtecedProcess : \n").toString();
        for(Iterator iterator1 = mProtecedProcess.iterator(); iterator1.hasNext();)
        {
            String s1 = (String)iterator1.next();
            s = (new StringBuilder()).append(s).append(s1).append("\n").toString();
        }

        return s;
    }

    public static final int CLEAN_TYPE_ALL = 0;
    public static final int CLEAN_TYPE_PROTECTED = 2;
    public static final int CLEAN_TYPE_UI = 1;
    public static final int LIMIT_START_ALL_WAYS = 0;
    public static final int LIMIT_START_EXCEPT_UI = 1;
    public static final int LIMIT_START_NOT = 2;
    private static final int STATE_DEEP_SLEEP = 2;
    private static final int STATE_LIGHT_SLEEP1 = 1;
    private static final int STATE_LIGHT_SLEEP2 = 3;
    private static final int STATE_NO_SLEEP = 0;
    public static final String TAG = "WhetstoneSleepCleanConfig";
    private List mProtecedProcess;
    private int mSleepState;
    private List mWhiteList;
}
