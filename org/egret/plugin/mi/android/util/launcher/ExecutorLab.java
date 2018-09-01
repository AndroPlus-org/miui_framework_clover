// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.egret.plugin.mi.android.util.launcher;

import android.util.Log;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorLab
{

    private ExecutorLab()
    {
        pool = Executors.newFixedThreadPool(3);
        running = true;
    }

    public static ExecutorLab getInstance()
    {
        if(instance == null)
            instance = new ExecutorLab();
        return instance;
    }

    public static void releaseInstance()
    {
        if(instance != null)
        {
            instance.shutDown();
            instance = null;
        }
    }

    private void shutDown()
    {
        if(!pool.isShutdown())
        {
            running = false;
            pool.shutdown();
            while(!pool.isTerminated()) 
                try
                {
                    Thread.sleep(100L);
                }
                catch(InterruptedException interruptedexception)
                {
                    interruptedexception.printStackTrace();
                }
            pool = null;
        }
    }

    public void addTask(Runnable runnable)
    {
        if(!running)
        {
            Log.d("ExecutorLab", "ExecutorLab is stop");
            return;
        } else
        {
            pool.execute(runnable);
            return;
        }
    }

    public boolean isRunning()
    {
        return running;
    }

    private static final String TAG = "ExecutorLab";
    private static final int THREAD_SIZE = 3;
    private static ExecutorLab instance = null;
    private ExecutorService pool;
    private volatile boolean running;

}
