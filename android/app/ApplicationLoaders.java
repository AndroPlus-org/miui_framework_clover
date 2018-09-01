// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.os.Trace;
import android.util.ArrayMap;
import com.android.internal.os.ClassLoaderFactory;
import dalvik.system.PathClassLoader;

public class ApplicationLoaders
{

    public ApplicationLoaders()
    {
    }

    private ClassLoader getClassLoader(String s, int i, boolean flag, String s1, String s2, ClassLoader classloader, String s3, 
            String s4)
    {
        ClassLoader classloader1 = ClassLoader.getSystemClassLoader().getParent();
        ArrayMap arraymap = mLoaders;
        arraymap;
        JVM INSTR monitorenter ;
        ClassLoader classloader2;
        classloader2 = classloader;
        if(classloader == null)
            classloader2 = classloader1;
        if(classloader2 != classloader1)
            break MISSING_BLOCK_LABEL_126;
        classloader = (ClassLoader)mLoaders.get(s3);
        if(classloader == null)
            break MISSING_BLOCK_LABEL_62;
        arraymap;
        JVM INSTR monitorexit ;
        return classloader;
        Trace.traceBegin(64L, s);
        s = ClassLoaderFactory.createClassLoader(s, s1, s2, classloader2, i, flag, s4);
        Trace.traceEnd(64L);
        Trace.traceBegin(64L, "setupVulkanLayerPath");
        setupVulkanLayerPath(s, s1);
        Trace.traceEnd(64L);
        mLoaders.put(s3, s);
        arraymap;
        JVM INSTR monitorexit ;
        return s;
        Trace.traceBegin(64L, s);
        s = ClassLoaderFactory.createClassLoader(s, null, classloader2, s4);
        Trace.traceEnd(64L);
        arraymap;
        JVM INSTR monitorexit ;
        return s;
        s;
        throw s;
    }

    public static ApplicationLoaders getDefault()
    {
        return gApplicationLoaders;
    }

    private static native void setupVulkanLayerPath(ClassLoader classloader, String s);

    void addPath(ClassLoader classloader, String s)
    {
        if(!(classloader instanceof PathClassLoader))
        {
            throw new IllegalStateException("class loader is not a PathClassLoader");
        } else
        {
            ((PathClassLoader)classloader).addDexPath(s);
            return;
        }
    }

    public ClassLoader createAndCacheWebViewClassLoader(String s, String s1, String s2)
    {
        return getClassLoader(s, android.os.Build.VERSION.SDK_INT, false, s1, null, null, s2, null);
    }

    ClassLoader getClassLoader(String s, int i, boolean flag, String s1, String s2, ClassLoader classloader, String s3)
    {
        return getClassLoader(s, i, flag, s1, s2, classloader, s, s3);
    }

    private static final ApplicationLoaders gApplicationLoaders = new ApplicationLoaders();
    private final ArrayMap mLoaders = new ArrayMap();

}
