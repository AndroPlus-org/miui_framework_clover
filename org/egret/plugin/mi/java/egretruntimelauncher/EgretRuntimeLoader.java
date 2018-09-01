// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.egret.plugin.mi.java.egretruntimelauncher;

import android.util.Log;
import dalvik.system.DexClassLoader;
import java.io.File;

public class EgretRuntimeLoader
{

    private EgretRuntimeLoader()
    {
        egretGameEngineClass = null;
        loaded = false;
    }

    public static EgretRuntimeLoader get()
    {
        if(instance == null)
            instance = new EgretRuntimeLoader();
        return instance;
    }

    public Class getEgretGameEngineClass()
    {
        return egretGameEngineClass;
    }

    public boolean isLoaded()
    {
        return loaded;
    }

    public void load(String s)
    {
        loaded = true;
        if(s.endsWith(".jar"))
            loadJar(s);
    }

    public void loadJar(String s)
    {
        File file = new File(s);
        file.setExecutable(true);
        Log.d("EgretRuntimeLoader", (new StringBuilder()).append("loadJar: ").append(s).append(": ").append(String.valueOf(file.exists())).toString());
        DexClassLoader dexclassloader = JVM INSTR new #92  <Class DexClassLoader>;
        File file1 = JVM INSTR new #56  <Class File>;
        file1.File(s);
        dexclassloader.DexClassLoader(s, file1.getParent(), null, getClass().getClassLoader());
        if(egretGameEngineClass == null)
            egretGameEngineClass = dexclassloader.loadClass("org.egret.egretframeworknative.engine.EgretGameEngine");
_L1:
        return;
        s;
        Log.e("Loader", "need dex format jar");
        s.printStackTrace();
          goto _L1
    }

    private static final String GAME_ENGINE_CLASS = "org.egret.egretframeworknative.engine.EgretGameEngine";
    private static final String TAG = "EgretRuntimeLoader";
    private static EgretRuntimeLoader instance = null;
    private Class egretGameEngineClass;
    private boolean loaded;

}
