// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.internal.search;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import java.io.*;
import java.util.*;
import miui.os.Build;
import miui.text.ChinesePinyinConverter;
import miui.util.FeatureParser;
import miui.util.cache.Cache;
import miui.util.cache.LruCache;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchUtils
{

    public SearchUtils()
    {
    }

    public static int Levenshtein(Object aobj[], Object aobj1[], Comparator comparator)
    {
        Object aobj2[] = aobj;
        Object aobj3[] = aobj1;
        if(aobj.length < aobj1.length)
        {
            aobj3 = aobj;
            aobj2 = aobj1;
        }
        int i = aobj2.length;
        int j = aobj3.length;
        aobj = new int[j + 1];
        for(int k = 0; k <= j; k++)
            aobj[k] = k;

        for(int l = 1; l <= i; l++)
        {
            Object obj = l - 1;
            aobj[0] = l;
            int i1 = 1;
            while(i1 <= j) 
            {
                Object obj1 = aobj[i1];
                int j1 = ((int) (aobj[i1]));
                int k1;
                if(comparator.compare(aobj2[l], aobj3[i1]) == 0)
                    k1 = 0;
                else
                    k1 = 1;
                aobj[i1] = Math.min(j1 + 1, k1 + obj);
                aobj[i1] = Math.min(aobj[i1 - 1] + 1, ((int) (aobj[i1])));
                obj = obj1;
                i1++;
            }
        }

        return ((int) (aobj[j]));
    }

    public static void clearPackageExistedCache()
    {
        sInstalledPackageList = null;
    }

    public static boolean getBoolean(Context context, String s)
    {
        if(TextUtils.isEmpty(s))
            break MISSING_BLOCK_LABEL_31;
        boolean flag;
        Resources resources = context.getResources();
        flag = resources.getBoolean(resources.getIdentifier(s, "bool", context.getPackageName()));
        return flag;
        context;
        return false;
    }

    public static Context getPackageContext(Context context, String s)
    {
        String s1 = s;
_L2:
        if(TextUtils.isEmpty(s1))
            break; /* Loop/switch isn't completed */
        if(s1.equals(context.getPackageName()))
            return context;
        if(sContextCache != null && sContextCache.get(s1) != null)
            return (Context)sContextCache.get(s1);
        Context context1;
        context1 = context.createPackageContext(s1, 3);
        if(sContextCache == null)
        {
            LruCache lrucache = JVM INSTR new #93  <Class LruCache>;
            lrucache.LruCache(5);
            sContextCache = lrucache;
        }
        sContextCache.put(s1, context1, 1);
        return context1;
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        namenotfoundexception;
        if(!s1.contains("."))
            break; /* Loop/switch isn't completed */
        s1 = s1.substring(0, s1.lastIndexOf('.'));
        if(true) goto _L2; else goto _L1
_L1:
        throw new RuntimeException((new StringBuilder()).append("Application package ").append(s).append(" not found").toString());
    }

    public static String getPinyin(String s)
    {
        if(TextUtils.isEmpty(s))
            return null;
        StringBuilder stringbuilder = new StringBuilder();
        for(s = ChinesePinyinConverter.getInstance().get(s, false, true).iterator(); s.hasNext();)
        {
            miui.text.ChinesePinyinConverter.Token token = (miui.text.ChinesePinyinConverter.Token)s.next();
            if(token.type == 2)
                stringbuilder.append(token.target.substring(0, 1).toUpperCase()).append(token.target.substring(1));
            else
                stringbuilder.append(token.target.toUpperCase());
        }

        return stringbuilder.toString();
    }

    public static String getString(Context context, String s)
    {
        if(TextUtils.isEmpty(s))
            break MISSING_BLOCK_LABEL_31;
        Resources resources = context.getResources();
        context = resources.getString(resources.getIdentifier(s, "string", context.getPackageName()));
        return context;
        context;
        return "";
    }

    public static boolean isEmpty(List list)
    {
        boolean flag;
        if(list != null)
            flag = list.isEmpty();
        else
            flag = true;
        return flag;
    }

    public static boolean isOldmanMode()
    {
        boolean flag = true;
        if(Build.getUserMode() != 1)
            flag = false;
        return flag;
    }

    public static boolean isPackageExisted(Context context, String s)
    {
        if(sInstalledPackageList == null)
            sInstalledPackageList = context.getPackageManager().getInstalledPackages(128);
        for(context = sInstalledPackageList.iterator(); context.hasNext();)
            if(s.contains(((PackageInfo)context.next()).packageName))
                return true;

        return false;
    }

    public static boolean isSecondSpace()
    {
        boolean flag = false;
        if(UserHandle.myUserId() != 0)
            flag = true;
        return flag;
    }

    public static void logCost(double d, double d1, Object obj)
    {
        Log.d("Utils", (new StringBuilder()).append((new Throwable()).getStackTrace()[1]).append(" (").append(obj).append(")").append(" takes ").append(d1 - d).append("ms").toString());
    }

    public static JSONObject readJSONObject(InputStream inputstream)
        throws IOException, JSONException
    {
        inputstream = new InputStreamReader(inputstream);
        StringBuilder stringbuilder = new StringBuilder();
        char ac[] = new char[1024];
        do
        {
            int i = inputstream.read(ac, 0, ac.length);
            if(i > 0)
            {
                stringbuilder.append(ac, 0, i);
            } else
            {
                inputstream.close();
                return new JSONObject(stringbuilder.toString());
            }
        } while(true);
    }

    public static boolean removeViaFeature(String s)
    {
        if(TextUtils.isEmpty(s))
            return false;
        String as[] = s.split("&&");
        int i = as.length;
        for(int j = 0; j < i; j++)
        {
            String s1 = as[j].trim();
            boolean flag = true;
            s = s1;
            if(s1.startsWith("!"))
            {
                flag = false;
                s = s1.substring(1);
            }
            if(FeatureParser.getBoolean(s, false) != flag)
                return true;
        }

        return false;
    }

    public static boolean removeViaSecondSpace(boolean flag)
    {
        if(!flag)
            flag = isSecondSpace();
        else
            flag = false;
        return flag;
    }

    private static final String TAG = "Utils";
    private static Cache sContextCache;
    private static List sInstalledPackageList;
}
