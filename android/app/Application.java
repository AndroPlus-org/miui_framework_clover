// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.*;
import android.content.res.Configuration;
import android.os.Bundle;
import com.miui.whetstone.app.WhetstoneAppManager;
import java.util.ArrayList;

// Referenced classes of package android.app:
//            ContextImpl, LoadedApk, Activity

public class Application extends ContextWrapper
    implements ComponentCallbacks2
{
    public static interface ActivityLifecycleCallbacks
    {

        public abstract void onActivityCreated(Activity activity, Bundle bundle);

        public abstract void onActivityDestroyed(Activity activity);

        public abstract void onActivityPaused(Activity activity);

        public abstract void onActivityResumed(Activity activity);

        public abstract void onActivitySaveInstanceState(Activity activity, Bundle bundle);

        public abstract void onActivityStarted(Activity activity);

        public abstract void onActivityStopped(Activity activity);
    }

    public static interface OnProvideAssistDataListener
    {

        public abstract void onProvideAssistData(Activity activity, Bundle bundle);
    }


    public Application()
    {
        super(null);
        mComponentCallbacks = new ArrayList();
        mActivityLifecycleCallbacks = new ArrayList();
        mAssistCallbacks = null;
    }

    private Object[] collectActivityLifecycleCallbacks()
    {
        Object aobj[] = null;
        ArrayList arraylist = mActivityLifecycleCallbacks;
        arraylist;
        JVM INSTR monitorenter ;
        if(mActivityLifecycleCallbacks.size() > 0)
            aobj = mActivityLifecycleCallbacks.toArray();
        arraylist;
        JVM INSTR monitorexit ;
        return aobj;
        Exception exception;
        exception;
        throw exception;
    }

    private Object[] collectComponentCallbacks()
    {
        Object aobj[] = null;
        ArrayList arraylist = mComponentCallbacks;
        arraylist;
        JVM INSTR monitorenter ;
        if(mComponentCallbacks.size() > 0)
            aobj = mComponentCallbacks.toArray();
        arraylist;
        JVM INSTR monitorexit ;
        return aobj;
        Exception exception;
        exception;
        throw exception;
    }

    final void attach(Context context)
    {
        attachBaseContext(context);
        mLoadedApk = ContextImpl.getImpl(context).mPackageInfo;
    }

    void dispatchActivityCreated(Activity activity, Bundle bundle)
    {
        Object aobj[] = collectActivityLifecycleCallbacks();
        if(aobj != null)
        {
            for(int i = 0; i < aobj.length; i++)
                ((ActivityLifecycleCallbacks)aobj[i]).onActivityCreated(activity, bundle);

        }
    }

    void dispatchActivityDestroyed(Activity activity)
    {
        Object aobj[] = collectActivityLifecycleCallbacks();
        if(aobj != null)
        {
            for(int i = 0; i < aobj.length; i++)
                ((ActivityLifecycleCallbacks)aobj[i]).onActivityDestroyed(activity);

        }
    }

    void dispatchActivityPaused(Activity activity)
    {
        Object aobj[] = collectActivityLifecycleCallbacks();
        if(aobj != null)
        {
            for(int i = 0; i < aobj.length; i++)
                ((ActivityLifecycleCallbacks)aobj[i]).onActivityPaused(activity);

        }
    }

    void dispatchActivityResumed(Activity activity)
    {
        Object aobj[] = collectActivityLifecycleCallbacks();
        if(aobj != null)
        {
            for(int i = 0; i < aobj.length; i++)
                ((ActivityLifecycleCallbacks)aobj[i]).onActivityResumed(activity);

        }
    }

    void dispatchActivitySaveInstanceState(Activity activity, Bundle bundle)
    {
        Object aobj[] = collectActivityLifecycleCallbacks();
        if(aobj != null)
        {
            for(int i = 0; i < aobj.length; i++)
                ((ActivityLifecycleCallbacks)aobj[i]).onActivitySaveInstanceState(activity, bundle);

        }
    }

    void dispatchActivityStarted(Activity activity)
    {
        Object aobj[] = collectActivityLifecycleCallbacks();
        if(aobj != null)
        {
            for(int i = 0; i < aobj.length; i++)
                ((ActivityLifecycleCallbacks)aobj[i]).onActivityStarted(activity);

        }
    }

    void dispatchActivityStopped(Activity activity)
    {
        Object aobj[] = collectActivityLifecycleCallbacks();
        if(aobj != null)
        {
            for(int i = 0; i < aobj.length; i++)
                ((ActivityLifecycleCallbacks)aobj[i]).onActivityStopped(activity);

        }
    }

    void dispatchOnProvideAssistData(Activity activity, Bundle bundle)
    {
        this;
        JVM INSTR monitorenter ;
        ArrayList arraylist = mAssistCallbacks;
        if(arraylist != null)
            break MISSING_BLOCK_LABEL_14;
        this;
        JVM INSTR monitorexit ;
        return;
        Object aobj[] = mAssistCallbacks.toArray();
        this;
        JVM INSTR monitorexit ;
        if(aobj != null)
        {
            for(int i = 0; i < aobj.length; i++)
                ((OnProvideAssistDataListener)aobj[i]).onProvideAssistData(activity, bundle);

        }
        break MISSING_BLOCK_LABEL_63;
        activity;
        throw activity;
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        Object aobj[] = collectComponentCallbacks();
        if(aobj != null)
        {
            for(int i = 0; i < aobj.length; i++)
                ((ComponentCallbacks)aobj[i]).onConfigurationChanged(configuration);

        }
    }

    public void onCreate()
    {
        WhetstoneAppManager.getInstance().attach(this);
    }

    public void onLowMemory()
    {
        Object aobj[] = collectComponentCallbacks();
        if(aobj != null)
        {
            for(int i = 0; i < aobj.length; i++)
                ((ComponentCallbacks)aobj[i]).onLowMemory();

        }
    }

    public void onTerminate()
    {
    }

    public void onTrimMemory(int i)
    {
        Object aobj[] = collectComponentCallbacks();
        if(aobj != null)
        {
            for(int j = 0; j < aobj.length; j++)
            {
                Object obj = aobj[j];
                if(obj instanceof ComponentCallbacks2)
                    ((ComponentCallbacks2)obj).onTrimMemory(i);
            }

        }
    }

    public void registerActivityLifecycleCallbacks(ActivityLifecycleCallbacks activitylifecyclecallbacks)
    {
        ArrayList arraylist = mActivityLifecycleCallbacks;
        arraylist;
        JVM INSTR monitorenter ;
        mActivityLifecycleCallbacks.add(activitylifecyclecallbacks);
        arraylist;
        JVM INSTR monitorexit ;
        return;
        activitylifecyclecallbacks;
        throw activitylifecyclecallbacks;
    }

    public void registerComponentCallbacks(ComponentCallbacks componentcallbacks)
    {
        ArrayList arraylist = mComponentCallbacks;
        arraylist;
        JVM INSTR monitorenter ;
        mComponentCallbacks.add(componentcallbacks);
        arraylist;
        JVM INSTR monitorexit ;
        return;
        componentcallbacks;
        throw componentcallbacks;
    }

    public void registerOnProvideAssistDataListener(OnProvideAssistDataListener onprovideassistdatalistener)
    {
        this;
        JVM INSTR monitorenter ;
        if(mAssistCallbacks == null)
        {
            ArrayList arraylist = JVM INSTR new #28  <Class ArrayList>;
            arraylist.ArrayList();
            mAssistCallbacks = arraylist;
        }
        mAssistCallbacks.add(onprovideassistdatalistener);
        this;
        JVM INSTR monitorexit ;
        return;
        onprovideassistdatalistener;
        throw onprovideassistdatalistener;
    }

    public void unregisterActivityLifecycleCallbacks(ActivityLifecycleCallbacks activitylifecyclecallbacks)
    {
        ArrayList arraylist = mActivityLifecycleCallbacks;
        arraylist;
        JVM INSTR monitorenter ;
        mActivityLifecycleCallbacks.remove(activitylifecyclecallbacks);
        arraylist;
        JVM INSTR monitorexit ;
        return;
        activitylifecyclecallbacks;
        throw activitylifecyclecallbacks;
    }

    public void unregisterComponentCallbacks(ComponentCallbacks componentcallbacks)
    {
        ArrayList arraylist = mComponentCallbacks;
        arraylist;
        JVM INSTR monitorenter ;
        mComponentCallbacks.remove(componentcallbacks);
        arraylist;
        JVM INSTR monitorexit ;
        return;
        componentcallbacks;
        throw componentcallbacks;
    }

    public void unregisterOnProvideAssistDataListener(OnProvideAssistDataListener onprovideassistdatalistener)
    {
        this;
        JVM INSTR monitorenter ;
        if(mAssistCallbacks != null)
            mAssistCallbacks.remove(onprovideassistdatalistener);
        this;
        JVM INSTR monitorexit ;
        return;
        onprovideassistdatalistener;
        throw onprovideassistdatalistener;
    }

    private ArrayList mActivityLifecycleCallbacks;
    private ArrayList mAssistCallbacks;
    private ArrayList mComponentCallbacks;
    public LoadedApk mLoadedApk;
}
