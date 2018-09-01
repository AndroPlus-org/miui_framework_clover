// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.Intent;
import android.os.Bundle;
import java.util.HashMap;

// Referenced classes of package android.app:
//            Activity, LocalActivityManager

public class ActivityGroup extends Activity
{

    public ActivityGroup()
    {
        this(true);
    }

    public ActivityGroup(boolean flag)
    {
        mLocalActivityManager = new LocalActivityManager(this, flag);
    }

    void dispatchActivityResult(String s, int i, int j, Intent intent)
    {
        if(s != null)
        {
            Activity activity = mLocalActivityManager.getActivity(s);
            if(activity != null)
            {
                activity.onActivityResult(i, j, intent);
                return;
            }
        }
        super.dispatchActivityResult(s, i, j, intent);
    }

    public Activity getCurrentActivity()
    {
        return mLocalActivityManager.getCurrentActivity();
    }

    public final LocalActivityManager getLocalActivityManager()
    {
        return mLocalActivityManager;
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if(bundle != null)
            bundle = bundle.getBundle("android:states");
        else
            bundle = null;
        mLocalActivityManager.dispatchCreate(bundle);
    }

    protected void onDestroy()
    {
        super.onDestroy();
        mLocalActivityManager.dispatchDestroy(isFinishing());
    }

    protected void onPause()
    {
        super.onPause();
        mLocalActivityManager.dispatchPause(isFinishing());
    }

    protected void onResume()
    {
        super.onResume();
        mLocalActivityManager.dispatchResume();
    }

    public HashMap onRetainNonConfigurationChildInstances()
    {
        return mLocalActivityManager.dispatchRetainNonConfigurationInstance();
    }

    protected void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        Bundle bundle1 = mLocalActivityManager.saveInstanceState();
        if(bundle1 != null)
            bundle.putBundle("android:states", bundle1);
    }

    protected void onStop()
    {
        super.onStop();
        mLocalActivityManager.dispatchStop();
    }

    static final String PARENT_NON_CONFIG_INSTANCE_KEY = "android:parent_non_config_instance";
    private static final String STATES_KEY = "android:states";
    protected LocalActivityManager mLocalActivityManager;
}
