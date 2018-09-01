// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import com.android.internal.content.ReferrerIntent;
import java.util.*;

// Referenced classes of package android.app:
//            ActivityThread, Activity

public class LocalActivityManager
{
    private static class LocalActivityRecord extends Binder
    {

        Activity activity;
        ActivityInfo activityInfo;
        int curState;
        final String id;
        Bundle instanceState;
        Intent intent;
        Window window;

        LocalActivityRecord(String s, Intent intent1)
        {
            curState = 0;
            id = s;
            intent = intent1;
        }
    }


    public LocalActivityManager(Activity activity, boolean flag)
    {
        mCurState = 1;
        mParent = activity;
        mSingleMode = flag;
    }

    private void moveToState(LocalActivityRecord localactivityrecord, int i)
    {
        if(localactivityrecord.curState == 0 || localactivityrecord.curState == 5)
            return;
        if(localactivityrecord.curState == 1)
        {
            Object obj = mParent.getLastNonConfigurationChildInstances();
            Object obj1 = null;
            if(obj != null)
                obj1 = ((HashMap) (obj)).get(localactivityrecord.id);
            obj = null;
            if(obj1 != null)
            {
                obj = new Activity.NonConfigurationInstances();
                obj.activity = obj1;
            }
            if(localactivityrecord.activityInfo == null)
                localactivityrecord.activityInfo = mActivityThread.resolveActivityInfo(localactivityrecord.intent);
            localactivityrecord.activity = mActivityThread.startActivityNow(mParent, localactivityrecord.id, localactivityrecord.intent, localactivityrecord.activityInfo, localactivityrecord, localactivityrecord.instanceState, ((Activity.NonConfigurationInstances) (obj)));
            if(localactivityrecord.activity == null)
                return;
            localactivityrecord.window = localactivityrecord.activity.getWindow();
            localactivityrecord.instanceState = null;
            localactivityrecord.curState = 3;
            if(i == 4)
            {
                mActivityThread.performResumeActivity(localactivityrecord, true, "moveToState-INITIALIZING");
                localactivityrecord.curState = 4;
            }
            return;
        }
        switch(localactivityrecord.curState)
        {
        default:
            return;

        case 2: // '\002'
            if(i == 3)
            {
                mActivityThread.performRestartActivity(localactivityrecord);
                localactivityrecord.curState = 3;
            }
            if(i == 4)
            {
                mActivityThread.performRestartActivity(localactivityrecord);
                mActivityThread.performResumeActivity(localactivityrecord, true, "moveToState-CREATED");
                localactivityrecord.curState = 4;
            }
            return;

        case 3: // '\003'
            if(i == 4)
            {
                mActivityThread.performResumeActivity(localactivityrecord, true, "moveToState-STARTED");
                localactivityrecord.instanceState = null;
                localactivityrecord.curState = 4;
            }
            if(i == 2)
            {
                mActivityThread.performStopActivity(localactivityrecord, false, "moveToState-STARTED");
                localactivityrecord.curState = 2;
            }
            return;

        case 4: // '\004'
            break;
        }
        if(i == 3)
        {
            performPause(localactivityrecord, mFinishing);
            localactivityrecord.curState = 3;
        }
        if(i == 2)
        {
            performPause(localactivityrecord, mFinishing);
            mActivityThread.performStopActivity(localactivityrecord, false, "moveToState-RESUMED");
            localactivityrecord.curState = 2;
        }
    }

    private Window performDestroy(LocalActivityRecord localactivityrecord, boolean flag)
    {
        Window window = localactivityrecord.window;
        if(localactivityrecord.curState == 4 && flag ^ true)
            performPause(localactivityrecord, flag);
        mActivityThread.performDestroyActivity(localactivityrecord, flag);
        localactivityrecord.activity = null;
        localactivityrecord.window = null;
        if(flag)
            localactivityrecord.instanceState = null;
        localactivityrecord.curState = 5;
        return window;
    }

    private void performPause(LocalActivityRecord localactivityrecord, boolean flag)
    {
        boolean flag1;
        Bundle bundle;
        if(localactivityrecord.instanceState == null)
            flag1 = true;
        else
            flag1 = false;
        bundle = mActivityThread.performPauseActivity(localactivityrecord, flag, flag1, "performPause");
        if(flag1)
            localactivityrecord.instanceState = bundle;
    }

    public Window destroyActivity(String s, boolean flag)
    {
        LocalActivityRecord localactivityrecord = (LocalActivityRecord)mActivities.get(s);
        Window window = null;
        if(localactivityrecord != null)
        {
            Window window1 = performDestroy(localactivityrecord, flag);
            window = window1;
            if(flag)
            {
                mActivities.remove(s);
                mActivityArray.remove(localactivityrecord);
                window = window1;
            }
        }
        return window;
    }

    public void dispatchCreate(Bundle bundle)
    {
        if(bundle == null) goto _L2; else goto _L1
_L1:
        Iterator iterator = bundle.keySet().iterator();
_L3:
        Object obj;
        if(!iterator.hasNext())
            break; /* Loop/switch isn't completed */
        obj = (String)iterator.next();
        Bundle bundle1;
        LocalActivityRecord localactivityrecord;
        bundle1 = bundle.getBundle(((String) (obj)));
        localactivityrecord = (LocalActivityRecord)mActivities.get(obj);
        if(localactivityrecord != null)
        {
            try
            {
                localactivityrecord.instanceState = bundle1;
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                Log.e("LocalActivityManager", "Exception thrown when restoring LocalActivityManager state", ((Throwable) (obj)));
            }
            continue; /* Loop/switch isn't completed */
        }
        LocalActivityRecord localactivityrecord1 = JVM INSTR new #6   <Class LocalActivityManager$LocalActivityRecord>;
        localactivityrecord1.LocalActivityRecord(((String) (obj)), null);
        localactivityrecord1.instanceState = bundle1;
        mActivities.put(obj, localactivityrecord1);
        mActivityArray.add(localactivityrecord1);
        if(true) goto _L3; else goto _L2
_L2:
        mCurState = 2;
        return;
    }

    public void dispatchDestroy(boolean flag)
    {
        int i = mActivityArray.size();
        for(int j = 0; j < i; j++)
        {
            LocalActivityRecord localactivityrecord = (LocalActivityRecord)mActivityArray.get(j);
            mActivityThread.performDestroyActivity(localactivityrecord, flag);
        }

        mActivities.clear();
        mActivityArray.clear();
    }

    public void dispatchPause(boolean flag)
    {
        if(flag)
            mFinishing = true;
        mCurState = 3;
        if(mSingleMode)
        {
            if(mResumed != null)
                moveToState(mResumed, 3);
        } else
        {
            int i = mActivityArray.size();
            int j = 0;
            while(j < i) 
            {
                LocalActivityRecord localactivityrecord = (LocalActivityRecord)mActivityArray.get(j);
                if(localactivityrecord.curState == 4)
                    moveToState(localactivityrecord, 3);
                j++;
            }
        }
    }

    public void dispatchResume()
    {
        mCurState = 4;
        if(mSingleMode)
        {
            if(mResumed != null)
                moveToState(mResumed, 4);
        } else
        {
            int i = mActivityArray.size();
            int j = 0;
            while(j < i) 
            {
                moveToState((LocalActivityRecord)mActivityArray.get(j), 4);
                j++;
            }
        }
    }

    public HashMap dispatchRetainNonConfigurationInstance()
    {
        HashMap hashmap = null;
        int i = mActivityArray.size();
        for(int j = 0; j < i;)
        {
            LocalActivityRecord localactivityrecord = (LocalActivityRecord)mActivityArray.get(j);
            HashMap hashmap1 = hashmap;
            if(localactivityrecord != null)
            {
                hashmap1 = hashmap;
                if(localactivityrecord.activity != null)
                {
                    Object obj = localactivityrecord.activity.onRetainNonConfigurationInstance();
                    hashmap1 = hashmap;
                    if(obj != null)
                    {
                        hashmap1 = hashmap;
                        if(hashmap == null)
                            hashmap1 = new HashMap();
                        hashmap1.put(localactivityrecord.id, obj);
                    }
                }
            }
            j++;
            hashmap = hashmap1;
        }

        return hashmap;
    }

    public void dispatchStop()
    {
        mCurState = 2;
        int i = mActivityArray.size();
        for(int j = 0; j < i; j++)
            moveToState((LocalActivityRecord)mActivityArray.get(j), 2);

    }

    public Activity getActivity(String s)
    {
        Object obj = null;
        LocalActivityRecord localactivityrecord = (LocalActivityRecord)mActivities.get(s);
        s = obj;
        if(localactivityrecord != null)
            s = localactivityrecord.activity;
        return s;
    }

    public Activity getCurrentActivity()
    {
        Activity activity = null;
        if(mResumed != null)
            activity = mResumed.activity;
        return activity;
    }

    public String getCurrentId()
    {
        String s = null;
        if(mResumed != null)
            s = mResumed.id;
        return s;
    }

    public void removeAllActivities()
    {
        dispatchDestroy(true);
    }

    public Bundle saveInstanceState()
    {
        Bundle bundle = null;
        int i = mActivityArray.size();
        for(int j = 0; j < i;)
        {
            LocalActivityRecord localactivityrecord = (LocalActivityRecord)mActivityArray.get(j);
            Bundle bundle1 = bundle;
            if(bundle == null)
                bundle1 = new Bundle();
            if((localactivityrecord.instanceState != null || localactivityrecord.curState == 4) && localactivityrecord.activity != null)
            {
                bundle = new Bundle();
                localactivityrecord.activity.performSaveInstanceState(bundle);
                localactivityrecord.instanceState = bundle;
            }
            if(localactivityrecord.instanceState != null)
                bundle1.putBundle(localactivityrecord.id, localactivityrecord.instanceState);
            j++;
            bundle = bundle1;
        }

        return bundle;
    }

    public Window startActivity(String s, Intent intent)
    {
        if(mCurState == 1)
            throw new IllegalStateException("Activities can't be added until the containing group has been created.");
        boolean flag = false;
        boolean flag1 = false;
        Object obj = null;
        Object obj1 = (LocalActivityRecord)mActivities.get(s);
        Object obj2;
        boolean flag2;
        Object obj3;
        if(obj1 == null)
        {
            obj2 = new LocalActivityRecord(s, intent);
            flag2 = true;
            obj3 = obj;
        } else
        {
            obj3 = obj;
            flag2 = flag;
            obj2 = obj1;
            if(((LocalActivityRecord) (obj1)).intent != null)
            {
                boolean flag3 = ((LocalActivityRecord) (obj1)).intent.filterEquals(intent);
                obj3 = obj;
                flag2 = flag;
                obj2 = obj1;
                flag1 = flag3;
                if(flag3)
                {
                    obj3 = ((LocalActivityRecord) (obj1)).activityInfo;
                    flag2 = flag;
                    obj2 = obj1;
                    flag1 = flag3;
                }
            }
        }
        obj1 = obj3;
        if(obj3 == null)
            obj1 = mActivityThread.resolveActivityInfo(intent);
        if(mSingleMode)
        {
            obj3 = mResumed;
            if(obj3 != null && obj3 != obj2 && mCurState == 4)
                moveToState(((LocalActivityRecord) (obj3)), 3);
        }
        if(flag2)
        {
            mActivities.put(s, obj2);
            mActivityArray.add(obj2);
        } else
        if(((LocalActivityRecord) (obj2)).activityInfo != null)
        {
            if(obj1 == ((LocalActivityRecord) (obj2)).activityInfo || ((ActivityInfo) (obj1)).name.equals(((LocalActivityRecord) (obj2)).activityInfo.name) && ((ActivityInfo) (obj1)).packageName.equals(((LocalActivityRecord) (obj2)).activityInfo.packageName))
            {
                if(((ActivityInfo) (obj1)).launchMode != 0 || (intent.getFlags() & 0x20000000) != 0)
                {
                    s = new ArrayList(1);
                    s.add(new ReferrerIntent(intent, mParent.getPackageName()));
                    mActivityThread.performNewIntents(((android.os.IBinder) (obj2)), s, false);
                    obj2.intent = intent;
                    moveToState(((LocalActivityRecord) (obj2)), mCurState);
                    if(mSingleMode)
                        mResumed = ((LocalActivityRecord) (obj2));
                    return ((LocalActivityRecord) (obj2)).window;
                }
                if(flag1 && (intent.getFlags() & 0x4000000) == 0)
                {
                    obj2.intent = intent;
                    moveToState(((LocalActivityRecord) (obj2)), mCurState);
                    if(mSingleMode)
                        mResumed = ((LocalActivityRecord) (obj2));
                    return ((LocalActivityRecord) (obj2)).window;
                }
            }
            performDestroy(((LocalActivityRecord) (obj2)), true);
        }
        obj2.intent = intent;
        obj2.curState = 1;
        obj2.activityInfo = ((ActivityInfo) (obj1));
        moveToState(((LocalActivityRecord) (obj2)), mCurState);
        if(mSingleMode)
            mResumed = ((LocalActivityRecord) (obj2));
        return ((LocalActivityRecord) (obj2)).window;
    }

    static final int CREATED = 2;
    static final int DESTROYED = 5;
    static final int INITIALIZING = 1;
    static final int RESTORED = 0;
    static final int RESUMED = 4;
    static final int STARTED = 3;
    private static final String TAG = "LocalActivityManager";
    private static final boolean localLOGV = false;
    private final Map mActivities = new HashMap();
    private final ArrayList mActivityArray = new ArrayList();
    private final ActivityThread mActivityThread = ActivityThread.currentActivityThread();
    private int mCurState;
    private boolean mFinishing;
    private final Activity mParent;
    private LocalActivityRecord mResumed;
    private boolean mSingleMode;
}
