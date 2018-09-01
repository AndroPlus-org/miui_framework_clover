// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.egret.plugin.mi.runtime;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import org.egret.plugin.mi.java.egretruntimelauncher.EgretRuntimeLauncher;

public class EgretLoader
{
    private class GameEngineMethod
    {

        public static final String CALL_INTERFACE = "callEgretInterface";
        public static final String ENABLE_INTERFACE = "enableEgretRuntimeInterface";
        public static final String GET_VIEW = "game_engine_get_view";
        public static final String INIT = "game_engine_init";
        public static final String ON_PAUSE = "game_engine_onPause";
        public static final String ON_RESUME = "game_engine_onResume";
        public static final String SET_INTERFACE = "setRuntimeInterfaceSet";
        public static final String SET_OPTIONS = "game_engine_set_options";
        public static final String STOP = "game_engine_onStop";
        final EgretLoader this$0;

        private GameEngineMethod()
        {
            this$0 = EgretLoader.this;
            super();
        }
    }

    private class GameOptionName
    {

        public static final String EGRET_ROOT = "egret.runtime.egretRoot";
        public static final String GAME_ID = "egret.runtime.gameId";
        public static final String LOADER_TYPE = "egret.runtime.libraryLoaderType";
        public static final String LOADER_URL = "egret.runtime.loaderUrl";
        public static final String ORIENTATION = "egret.runtime.screenOrientation";
        public static final String UPDATE_URL = "egret.runtime.updateUrl";
        final EgretLoader this$0;

        private GameOptionName()
        {
            this$0 = EgretLoader.this;
            super();
        }
    }


    static void _2D_wrap0(EgretLoader egretloader)
    {
        egretloader.startGameEngine();
    }

    static void _2D_wrap1(EgretLoader egretloader, Class class1)
    {
        egretloader.startGame(class1);
    }

    public EgretLoader(Context context)
    {
        Log.d("EgretLoader", "EgretLoader(Context context)");
        if(checkContext(context))
        {
            options = new HashMap();
            activity = (Activity)context;
        }
    }

    private Object callGameEngineMethod(String s)
    {
        return callGameEngineMethod(s, null, null);
    }

    private Object callGameEngineMethod(String s, Class aclass[], Object aobj[])
    {
        if(s == null || gameEngine == null)
            return null;
        try
        {
            s = ((String) (gameEngine.getClass().getDeclaredMethod(s, aclass).invoke(gameEngine, aobj)));
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            s.printStackTrace();
            return null;
        }
        return s;
    }

    private void callInitRuntime()
    {
        Activity activity1 = activity;
        callGameEngineMethod("game_engine_init", new Class[] {
            android/content/Context
        }, new Object[] {
            activity1
        });
    }

    private void callSetGameOptions()
    {
        HashMap hashmap = new HashMap();
        java.util.Map.Entry entry;
        for(Iterator iterator = options.entrySet().iterator(); iterator.hasNext(); Log.d("EgretLoader", (new StringBuilder()).append((String)entry.getKey()).append(": ").append((String)entry.getValue()).toString()))
        {
            entry = (java.util.Map.Entry)iterator.next();
            hashmap.put((String)entry.getKey(), entry.getValue());
        }

        callGameEngineMethod("game_engine_set_options", new Class[] {
            java/util/HashMap
        }, new Object[] {
            hashmap
        });
    }

    private void callSetRuntimeView()
    {
        View view = (View)callGameEngineMethod("game_engine_get_view");
        if(view != null)
        {
            setScreenOrientation();
            activity.setContentView(view);
        }
    }

    private boolean checkContext(Context context)
    {
        if(!android/app/Activity.isInstance(context))
            return false;
        try
        {
            context.getClass().getMethod("setEgretRuntimeListener", new Class[] {
                java/lang/Object
            }).invoke(context, new Object[] {
                this
            });
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            return false;
        }
        return true;
    }

    private boolean checkEgretGameEngine()
    {
        if(gameEngine == null)
        {
            Log.d("EgretLoader", "Egret game engine is null");
            return true;
        } else
        {
            return false;
        }
    }

    private void setScreenOrientation()
    {
        if(options.containsKey("egret.runtime.screenOrientation") && ((String)options.get("egret.runtime.screenOrientation")).equals("landscape"))
            activity.setRequestedOrientation(0);
        else
            activity.setRequestedOrientation(1);
    }

    private void startGame(Class class1)
    {
        try
        {
            gameEngine = class1.newInstance();
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            class1.printStackTrace();
            return;
        }
        activity.runOnUiThread(new Runnable() {

            public void run()
            {
                EgretLoader._2D_wrap0(EgretLoader.this);
            }

            final EgretLoader this$0;

            
            {
                this$0 = EgretLoader.this;
                super();
            }
        }
);
    }

    private void startGameEngine()
    {
        callSetGameOptions();
        callInitRuntime();
        callSetRuntimeView();
    }

    public boolean checkEgretContext()
    {
        if(activity == null)
        {
            Log.d("EgretLoader", "The context is not activity");
            return true;
        } else
        {
            return false;
        }
    }

    public void init(String s)
    {
        Log.d("EgretLoader", (new StringBuilder()).append("init: ").append(s).toString());
    }

    public void onPause()
    {
        Log.d("EgretLoader", "onPause()");
        if(checkEgretContext() || checkEgretGameEngine())
        {
            return;
        } else
        {
            callGameEngineMethod("game_engine_onPause");
            return;
        }
    }

    public void onResume()
    {
        Log.d("EgretLoader", "onResume()");
        if(checkEgretContext() || checkEgretGameEngine())
        {
            return;
        } else
        {
            callGameEngineMethod("game_engine_onResume");
            return;
        }
    }

    public void onStop()
    {
        Log.d("EgretLoader", "stop()");
        if(checkEgretContext() || checkEgretGameEngine())
        {
            return;
        } else
        {
            callGameEngineMethod("game_engine_onStop");
            gameEngine = null;
            return;
        }
    }

    public void setOption(String s, String s1)
    {
        Log.d("EgretLoader", (new StringBuilder()).append("setOption: ").append(s).append("->").append(s1).toString());
        if(checkEgretContext())
            return;
        if(!s.equals("gameId")) goto _L2; else goto _L1
_L1:
        String s2 = "egret.runtime.gameId";
_L4:
        options.put(s2, s1);
        return;
_L2:
        s2 = s;
        if(s.equals("gameUrl"))
        {
            s = "egret.runtime.loaderUrl";
            s2 = s;
            if(!options.containsKey("egret.runtime.updateUrl"))
            {
                options.put("egret.runtime.updateUrl", s1);
                s2 = s;
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setScreenOrientation(String s)
    {
        if(s.equals("landscape"))
            options.put("egret.runtime.screenOrientation", "landscape");
        else
            options.put("egret.runtime.screenOrientation", "portrait");
    }

    public void start(String s)
    {
        Log.d("EgretLoader", (new StringBuilder()).append("start: ").append(s).toString());
        if(checkEgretContext())
        {
            return;
        } else
        {
            s = (new File(activity.getFilesDir(), "egret")).getAbsolutePath();
            options.put("egret.runtime.egretRoot", s);
            options.put("egret.runtime.libraryLoaderType", "2");
            launcher = new EgretRuntimeLauncher(activity, s, "2000", "3321031F35156D389B0B272910695E3F", 0);
            launcher.run(new org.egret.plugin.mi.java.egretruntimelauncher.EgretRuntimeLauncher.EgretRuntimeDownloadListener() {

                public void onError(String s1)
                {
                }

                public void onProgress(String s1, int i, int j)
                {
                    Log.d("EgretLoader", (new StringBuilder()).append(s1).append(": ").append(String.valueOf(i)).append("/").append(String.valueOf(j)).toString());
                }

                public void onProgressTotal(int i, int j)
                {
                    Log.d("EgretLoader", (new StringBuilder()).append("progress: ").append(String.valueOf(i)).append("/").append(String.valueOf(j)).toString());
                }

                public void onSuccess(Class class1)
                {
                    EgretLoader._2D_wrap1(EgretLoader.this, class1);
                }

                final EgretLoader this$0;

            
            {
                this$0 = EgretLoader.this;
                super();
            }
            }
);
            return;
        }
    }

    public void stopEgretRuntime()
    {
        onStop();
    }

    private static final String LOGTAG = "EgretLoader";
    private static final String MI_APPID = "2000";
    private static final String MI_APPKEY = "3321031F35156D389B0B272910695E3F";
    private Activity activity;
    private Object gameEngine;
    private EgretRuntimeLauncher launcher;
    private HashMap options;
}
