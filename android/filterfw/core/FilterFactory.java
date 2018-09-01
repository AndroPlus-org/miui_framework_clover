// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;

import android.util.Log;
import dalvik.system.PathClassLoader;
import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Iterator;

// Referenced classes of package android.filterfw.core:
//            Filter

public class FilterFactory
{

    public FilterFactory()
    {
        mPackages = new HashSet();
    }

    public static void addFilterLibrary(String s)
    {
        if(mLogVerbose)
            Log.v("FilterFactory", (new StringBuilder()).append("Adding filter library ").append(s).toString());
        Object obj = mClassLoaderGuard;
        obj;
        JVM INSTR monitorenter ;
        if(!mLibraries.contains(s))
            break MISSING_BLOCK_LABEL_64;
        if(mLogVerbose)
            Log.v("FilterFactory", "Library already added");
        obj;
        JVM INSTR monitorexit ;
        return;
        mLibraries.add(s);
        PathClassLoader pathclassloader = JVM INSTR new #85  <Class PathClassLoader>;
        pathclassloader.PathClassLoader(s, mCurrentClassLoader);
        mCurrentClassLoader = pathclassloader;
        obj;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s;
    }

    public static FilterFactory sharedFactory()
    {
        if(mSharedFactory == null)
            mSharedFactory = new FilterFactory();
        return mSharedFactory;
    }

    public void addPackage(String s)
    {
        if(mLogVerbose)
            Log.v("FilterFactory", (new StringBuilder()).append("Adding package ").append(s).toString());
        mPackages.add(s);
    }

    public Filter createFilterByClass(Class class1, String s)
    {
        Object obj;
        try
        {
            class1.asSubclass(android/filterfw/core/Filter);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Attempting to allocate class '").append(class1).append("' which is not a subclass of Filter!").toString());
        }
        try
        {
            obj = class1.getConstructor(new Class[] {
                java/lang/String
            });
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("The filter class '").append(class1).append("' does not have a constructor of the form <init>(String name)!").toString());
        }
        class1 = null;
        obj = (Filter)((Constructor) (obj)).newInstance(new Object[] {
            s
        });
        class1 = ((Class) (obj));
_L2:
        if(class1 == null)
            throw new IllegalArgumentException((new StringBuilder()).append("Could not construct the filter '").append(s).append("'!").toString());
        else
            return class1;
        Throwable throwable;
        throwable;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public Filter createFilterByClassName(String s, String s1)
    {
        Object obj;
        Iterator iterator;
        if(mLogVerbose)
            Log.v("FilterFactory", (new StringBuilder()).append("Looking up class ").append(s).toString());
        obj = null;
        iterator = mPackages.iterator();
_L2:
        Object obj1;
        Object obj2;
        obj1 = obj;
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_197;
        obj1 = (String)iterator.next();
        obj2 = obj;
        if(!mLogVerbose)
            break MISSING_BLOCK_LABEL_127;
        obj2 = obj;
        Object obj3 = JVM INSTR new #59  <Class StringBuilder>;
        obj2 = obj;
        ((StringBuilder) (obj3)).StringBuilder();
        obj2 = obj;
        Log.v("FilterFactory", ((StringBuilder) (obj3)).append("Trying ").append(((String) (obj1))).append(".").append(s).toString());
        obj2 = obj;
        obj3 = mClassLoaderGuard;
        obj2 = obj;
        obj3;
        JVM INSTR monitorenter ;
        ClassLoader classloader = mCurrentClassLoader;
        obj2 = JVM INSTR new #59  <Class StringBuilder>;
        ((StringBuilder) (obj2)).StringBuilder();
        obj1 = classloader.loadClass(((StringBuilder) (obj2)).append(((String) (obj1))).append(".").append(s).toString());
        obj2 = obj1;
        obj3;
        JVM INSTR monitorexit ;
        obj = obj1;
        if(obj1 == null)
            continue; /* Loop/switch isn't completed */
        if(obj1 == null)
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown filter class '").append(s).append("'!").toString());
        else
            return createFilterByClass(((Class) (obj1)), s1);
        obj1;
        obj2 = obj;
        obj3;
        JVM INSTR monitorexit ;
        obj2 = obj;
        try
        {
            throw obj1;
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            classnotfoundexception = ((ClassNotFoundException) (obj2));
        }
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static final String TAG = "FilterFactory";
    private static Object mClassLoaderGuard = new Object();
    private static ClassLoader mCurrentClassLoader = Thread.currentThread().getContextClassLoader();
    private static HashSet mLibraries = new HashSet();
    private static boolean mLogVerbose = Log.isLoggable("FilterFactory", 2);
    private static FilterFactory mSharedFactory;
    private HashSet mPackages;

}
