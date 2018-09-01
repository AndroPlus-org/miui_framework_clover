// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.os.*;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.*;

// Referenced classes of package com.android.internal.os:
//            IRegionalizationService

public class RegionalizationEnvironment
{
    private static class Package
    {

        public File getDirectory()
        {
            return new File(mStorage, mName);
        }

        public String getExcludedListFilePath()
        {
            return (new StringBuilder()).append(getDirectory().getAbsolutePath()).append("/exclude.list").toString();
        }

        public String getName()
        {
            return mName;
        }

        public int getPriority()
        {
            return mPriority;
        }

        public String getStoragePos()
        {
            return mStorage;
        }

        private final String mName;
        private final int mPriority;
        private final String mStorage;

        public Package(String s, int i, String s1)
        {
            mName = s;
            mPriority = i;
            mStorage = s1;
        }
    }


    public RegionalizationEnvironment()
    {
    }

    public static List getAllPackageDirectories()
    {
        ArrayList arraylist = new ArrayList();
        Package package1;
        for(Iterator iterator = mPackages.iterator(); iterator.hasNext(); arraylist.add(package1.getDirectory()))
        {
            package1 = (Package)iterator.next();
            Log.v("RegionalizationEnvironment", (new StringBuilder()).append("Package Directoriy(").append(package1.getPriority()).append("):").append(package1.getDirectory()).toString());
        }

        return arraylist;
    }

    public static List getAllPackageNames()
    {
        ArrayList arraylist = new ArrayList();
        for(Iterator iterator = mPackages.iterator(); iterator.hasNext(); arraylist.add(((Package)iterator.next()).getName()));
        return arraylist;
    }

    public static int getPackagesCount()
    {
        return mPackages.size();
    }

    public static IRegionalizationService getRegionalizationService()
    {
        return mRegionalizationService;
    }

    public static String getStoragePos()
    {
        for(Iterator iterator = mPackages.iterator(); iterator.hasNext();)
        {
            String s = ((Package)iterator.next()).getStoragePos();
            if(!TextUtils.isEmpty(s))
                return s;
        }

        try
        {
            mPackages.clear();
            IOException ioexception = JVM INSTR new #132 <Class IOException>;
            ioexception.IOException("Read wrong package for Carrier!");
            throw ioexception;
        }
        catch(IOException ioexception1)
        {
            Log.e("RegionalizationEnvironment", (new StringBuilder()).append("Get storage pos error, caused by: ").append(ioexception1.getMessage()).toString());
        }
        return "";
    }

    private static void init()
    {
        mRegionalizationService = IRegionalizationService.Stub.asInterface(ServiceManager.getService("regionalization"));
        if(mRegionalizationService != null)
        {
            loadSwitchedPackages();
            loadExcludedApplist();
            isLoaded = true;
        }
    }

    public static boolean isExcludedApp(String s)
    {
        if(getPackagesCount() == 0)
            return false;
        if(!s.endsWith(".apk"))
            return mExcludedApps.contains((new StringBuilder()).append(s).append(".apk").toString());
        else
            return mExcludedApps.contains(s);
    }

    public static boolean isRegionalizationCarrierOverlayPackage(String s, String s1)
    {
        Object obj;
        boolean flag;
        if(!isSupported() || s == null)
            return false;
        obj = getAllPackageNames();
        flag = false;
        obj = ((Iterable) (obj)).iterator();
_L4:
        if(!((Iterator) (obj)).hasNext()) goto _L2; else goto _L1
_L1:
        boolean flag1;
        flag1 = flag;
        if(s.indexOf((String)((Iterator) (obj)).next()) != -1)
            flag1 = true;
        flag = flag1;
        if(!flag1) goto _L4; else goto _L3
_L3:
        if(!s1.equals("Framework") || s.indexOf("Framework") == -1) goto _L6; else goto _L5
_L5:
        return true;
_L6:
        flag = flag1;
        if(!s1.equals("app")) goto _L4; else goto _L5
_L2:
        return false;
    }

    public static boolean isSupported()
    {
        if(SUPPORTED && isLoaded ^ true)
            init();
        return SUPPORTED;
    }

    private static void loadExcludedApplist()
    {
        Iterator iterator;
        Log.d("RegionalizationEnvironment", "loadExcludedApps!");
        if(getPackagesCount() == 0)
            return;
        iterator = mPackages.iterator();
_L2:
        Object obj;
        Object obj1;
        if(!iterator.hasNext())
            break; /* Loop/switch isn't completed */
        obj = (Package)iterator.next();
        Log.d("RegionalizationEnvironment", (new StringBuilder()).append("load excluded apps for ").append(((Package) (obj)).getDirectory()).toString());
        obj1 = ((Package) (obj)).getExcludedListFilePath();
        obj = null;
        obj1 = (ArrayList)mRegionalizationService.readFile(((String) (obj1)), null);
        obj = obj1;
_L3:
        if(obj != null && ((ArrayList) (obj)).size() > 0)
        {
            obj = ((Iterable) (obj)).iterator();
            while(((Iterator) (obj)).hasNext()) 
            {
                String s = (String)((Iterator) (obj)).next();
                if(!TextUtils.isEmpty(s))
                {
                    int i = s.lastIndexOf("/");
                    if(i != -1)
                    {
                        s = s.substring(i + 1);
                        if(!TextUtils.isEmpty(s) && mExcludedApps.contains(s) ^ true)
                            mExcludedApps.add(s);
                    }
                }
            }
        }
        if(true) goto _L2; else goto _L1
        RemoteException remoteexception;
        remoteexception;
        remoteexception.printStackTrace();
          goto _L3
_L1:
    }

    private static void loadSwitchedPackages()
    {
        Object obj;
        Log.d("RegionalizationEnvironment", "load packages for Carrier!");
        obj = null;
        Object obj1 = (ArrayList)mRegionalizationService.readFile("/persist/speccfg/spec", null);
        obj = obj1;
_L5:
        if(obj == null) goto _L2; else goto _L1
_L1:
        if(((ArrayList) (obj)).size() <= 0) goto _L2; else goto _L3
_L3:
        if(!((String)((ArrayList) (obj)).get(0)).startsWith("packStorage="))
        {
            obj = JVM INSTR new #132 <Class IOException>;
            ((IOException) (obj)).IOException("Can't read storage pos for Carrier package!");
            throw obj;
        }
          goto _L4
        obj;
        Log.e("RegionalizationEnvironment", (new StringBuilder()).append("Load package for carrier error, caused by: ").append(((IOException) (obj)).getMessage()).toString());
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        remoteexception.printStackTrace();
          goto _L5
_L4:
        String s;
        int i;
        s = ((String)((ArrayList) (obj)).get(0)).substring("packStorage=".length());
        if(TextUtils.isEmpty(s))
        {
            obj = JVM INSTR new #132 <Class IOException>;
            ((IOException) (obj)).IOException("Storage pos for Carrier package is wrong!");
            throw obj;
        }
        if(!((String)((ArrayList) (obj)).get(1)).matches("^packCount=[0-9]$"))
        {
            obj = JVM INSTR new #132 <Class IOException>;
            ((IOException) (obj)).IOException("Can't read package count of Carrier!");
            throw obj;
        }
        i = Integer.parseInt(((String)((ArrayList) (obj)).get(1)).substring("packCount=".length()));
        if(i <= 0)
            break MISSING_BLOCK_LABEL_204;
        if(((ArrayList) (obj)).size() > i)
            break MISSING_BLOCK_LABEL_217;
        obj = JVM INSTR new #132 <Class IOException>;
        ((IOException) (obj)).IOException("Package count of Carrier is wrong!");
        throw obj;
        int j = 2;
_L9:
        if(j >= i + 2) goto _L2; else goto _L6
_L6:
        boolean flag;
        if(!((String)((ArrayList) (obj)).get(j)).matches("^strSpec[0-9]=\\w+$"))
            break MISSING_BLOCK_LABEL_394;
        remoteexception = ((String)((ArrayList) (obj)).get(j)).substring("strSpec".length() + 2);
        flag = TextUtils.isEmpty(remoteexception);
        if(flag) goto _L8; else goto _L7
_L7:
        flag = false;
        boolean flag1;
        IRegionalizationService iregionalizationservice = mRegionalizationService;
        StringBuilder stringbuilder = JVM INSTR new #79  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        flag1 = iregionalizationservice.checkFileExists(stringbuilder.append(s).append("/").append(remoteexception).toString());
        flag = flag1;
_L10:
        if(!flag)
            break MISSING_BLOCK_LABEL_375;
        ArrayList arraylist = mPackages;
        Package package1 = JVM INSTR new #6   <Class RegionalizationEnvironment$Package>;
        package1.Package(remoteexception, j, s);
        arraylist.add(package1);
_L8:
        j++;
          goto _L9
        RemoteException remoteexception1;
        remoteexception1;
        remoteexception1.printStackTrace();
          goto _L10
        mPackages.clear();
        IOException ioexception = JVM INSTR new #132 <Class IOException>;
        ioexception.IOException("Read wrong packages for Carrier!");
        throw ioexception;
        mPackages.clear();
        IOException ioexception1 = JVM INSTR new #132 <Class IOException>;
        ioexception1.IOException("Read wrong packages for Carrier!");
        throw ioexception1;
          goto _L5
    }

    private static final boolean DEBUG = true;
    public static final String ISREGIONAL_APP = "app";
    public static final String ISREGIONAL_FRAMEWORK = "Framework";
    private static final String SPEC_FILE_PATH = "/persist/speccfg/spec";
    private static final boolean SUPPORTED = SystemProperties.getBoolean("ro.regionalization.support", false);
    private static final String TAG = "RegionalizationEnvironment";
    private static boolean isLoaded = false;
    private static ArrayList mExcludedApps = new ArrayList();
    private static ArrayList mPackages = new ArrayList();
    private static IRegionalizationService mRegionalizationService = null;

}
