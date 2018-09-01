// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.os.Trace;
import dalvik.system.*;

public class ClassLoaderFactory
{

    private ClassLoaderFactory()
    {
    }

    public static ClassLoader createClassLoader(String s, String s1, ClassLoader classloader, String s2)
    {
        if(isPathClassLoaderName(s2))
            return new PathClassLoader(s, s1, classloader);
        if(isDelegateLastClassLoaderName(s2))
            return new DelegateLastClassLoader(s, s1, classloader);
        else
            throw new AssertionError((new StringBuilder()).append("Invalid classLoaderName: ").append(s2).toString());
    }

    public static ClassLoader createClassLoader(String s, String s1, String s2, ClassLoader classloader, int i, boolean flag, String s3)
    {
        classloader = createClassLoader(s, s1, classloader, s3);
        boolean flag1 = false;
        s = s.split(":");
        int j = 0;
        int k = s.length;
        do
        {
label0:
            {
                boolean flag2 = flag1;
                if(j < k)
                {
                    if(!s[j].startsWith("/vendor/"))
                        break label0;
                    flag2 = true;
                }
                Trace.traceBegin(64L, "createClassloaderNamespace");
                s = createClassloaderNamespace(classloader, i, s1, s2, flag, flag2);
                Trace.traceEnd(64L);
                if(s != null)
                    throw new UnsatisfiedLinkError((new StringBuilder()).append("Unable to create namespace for the classloader ").append(classloader).append(": ").append(s).toString());
                else
                    return classloader;
            }
            j++;
        } while(true);
    }

    private static native String createClassloaderNamespace(ClassLoader classloader, int i, String s, String s1, boolean flag, boolean flag1);

    public static boolean isDelegateLastClassLoaderName(String s)
    {
        return DELEGATE_LAST_CLASS_LOADER_NAME.equals(s);
    }

    public static boolean isPathClassLoaderName(String s)
    {
        boolean flag;
        if(s != null && !PATH_CLASS_LOADER_NAME.equals(s))
            flag = DEX_CLASS_LOADER_NAME.equals(s);
        else
            flag = true;
        return flag;
    }

    public static boolean isValidClassLoaderName(String s)
    {
        boolean flag;
        if(s != null)
        {
            if(!isPathClassLoaderName(s))
                flag = isDelegateLastClassLoaderName(s);
            else
                flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    private static final String DELEGATE_LAST_CLASS_LOADER_NAME = dalvik/system/DelegateLastClassLoader.getName();
    private static final String DEX_CLASS_LOADER_NAME = dalvik/system/DexClassLoader.getName();
    private static final String PATH_CLASS_LOADER_NAME = dalvik/system/PathClassLoader.getName();

}
