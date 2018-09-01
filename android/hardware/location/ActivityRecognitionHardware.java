// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.location;

import android.content.Context;
import android.os.*;
import android.text.TextUtils;
import android.util.Log;

// Referenced classes of package android.hardware.location:
//            ActivityRecognitionEvent, ActivityChangedEvent, IActivityRecognitionHardwareSink

public class ActivityRecognitionHardware extends IActivityRecognitionHardware.Stub
{
    private static class Event
    {

        public int activity;
        public long timestamp;
        public int type;

        private Event()
        {
        }
    }

    private class SinkList extends RemoteCallbackList
    {

        private void disableActivityEventIfEnabled(int i, int j)
        {
            if(ActivityRecognitionHardware._2D_get3(ActivityRecognitionHardware.this)[i][j] != 1)
            {
                return;
            } else
            {
                int k = ActivityRecognitionHardware._2D_wrap0(ActivityRecognitionHardware.this, i, j);
                ActivityRecognitionHardware._2D_get3(ActivityRecognitionHardware.this)[i][j] = 0;
                Log.e("ActivityRecognitionHW", String.format("DisableActivityEvent: activityType=%d, eventType=%d, result=%d", new Object[] {
                    Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(k)
                }));
                return;
            }
        }

        public void onCallbackDied(IActivityRecognitionHardwareSink iactivityrecognitionhardwaresink)
        {
            int i = ActivityRecognitionHardware._2D_get1(ActivityRecognitionHardware.this).getRegisteredCallbackCount();
            if(ActivityRecognitionHardware._2D_get0())
                Log.d("ActivityRecognitionHW", (new StringBuilder()).append("RegisteredCallbackCount: ").append(i).toString());
            if(i != 0)
                return;
            for(int j = 0; j < ActivityRecognitionHardware._2D_get2(ActivityRecognitionHardware.this); j++)
            {
                for(int k = 0; k < 3; k++)
                    disableActivityEventIfEnabled(j, k);

            }

        }

        public volatile void onCallbackDied(IInterface iinterface)
        {
            onCallbackDied((IActivityRecognitionHardwareSink)iinterface);
        }

        final ActivityRecognitionHardware this$0;

        private SinkList()
        {
            this$0 = ActivityRecognitionHardware.this;
            super();
        }

        SinkList(SinkList sinklist)
        {
            this();
        }
    }


    static boolean _2D_get0()
    {
        return DEBUG;
    }

    static SinkList _2D_get1(ActivityRecognitionHardware activityrecognitionhardware)
    {
        return activityrecognitionhardware.mSinks;
    }

    static int _2D_get2(ActivityRecognitionHardware activityrecognitionhardware)
    {
        return activityrecognitionhardware.mSupportedActivitiesCount;
    }

    static int[][] _2D_get3(ActivityRecognitionHardware activityrecognitionhardware)
    {
        return activityrecognitionhardware.mSupportedActivitiesEnabledEvents;
    }

    static int _2D_wrap0(ActivityRecognitionHardware activityrecognitionhardware, int i, int j)
    {
        return activityrecognitionhardware.nativeDisableActivityEvent(i, j);
    }

    private ActivityRecognitionHardware(Context context)
    {
        nativeInitialize();
        mContext = context;
        mSupportedActivitiesCount = mSupportedActivities.length;
        mSupportedActivitiesEnabledEvents = new int[mSupportedActivitiesCount][3];
    }

    private void checkPermissions()
    {
        mContext.enforceCallingPermission("android.permission.LOCATION_HARDWARE", "Permission 'android.permission.LOCATION_HARDWARE' not granted to access ActivityRecognitionHardware");
    }

    private String[] fetchSupportedActivities()
    {
        String as[] = nativeGetSupportedActivities();
        if(as != null)
            return as;
        else
            return new String[0];
    }

    private String getActivityName(int i)
    {
        if(i < 0 || i >= mSupportedActivities.length)
        {
            Log.e("ActivityRecognitionHW", String.format("Invalid ActivityType: %d, SupportedActivities: %d", new Object[] {
                Integer.valueOf(i), Integer.valueOf(mSupportedActivities.length)
            }));
            return null;
        } else
        {
            return mSupportedActivities[i];
        }
    }

    private int getActivityType(String s)
    {
        if(TextUtils.isEmpty(s))
            return -1;
        int i = mSupportedActivities.length;
        for(int j = 0; j < i; j++)
            if(s.equals(mSupportedActivities[j]))
                return j;

        return -1;
    }

    public static ActivityRecognitionHardware getInstance(Context context)
    {
        Object obj = sSingletonInstanceLock;
        obj;
        JVM INSTR monitorenter ;
        if(sSingletonInstance == null)
        {
            ActivityRecognitionHardware activityrecognitionhardware = JVM INSTR new #2   <Class ActivityRecognitionHardware>;
            activityrecognitionhardware.ActivityRecognitionHardware(context);
            sSingletonInstance = activityrecognitionhardware;
        }
        context = sSingletonInstance;
        obj;
        JVM INSTR monitorexit ;
        return context;
        context;
        throw context;
    }

    public static boolean isSupported()
    {
        return nativeIsSupported();
    }

    private static native void nativeClassInit();

    private native int nativeDisableActivityEvent(int i, int j);

    private native int nativeEnableActivityEvent(int i, int j, long l);

    private native int nativeFlush();

    private native String[] nativeGetSupportedActivities();

    private native void nativeInitialize();

    private static native boolean nativeIsSupported();

    private native void nativeRelease();

    private void onActivityChanged(Event aevent[])
    {
        if(aevent == null || aevent.length == 0)
        {
            if(DEBUG)
                Log.d("ActivityRecognitionHW", "No events to broadcast for onActivityChanged.");
            return;
        }
        int i = aevent.length;
        ActivityRecognitionEvent aactivityrecognitionevent[] = new ActivityRecognitionEvent[i];
        for(int j = 0; j < i; j++)
        {
            Event event = aevent[j];
            aactivityrecognitionevent[j] = new ActivityRecognitionEvent(getActivityName(event.activity), event.type, event.timestamp);
        }

        aevent = new ActivityChangedEvent(aactivityrecognitionevent);
        i = mSinks.beginBroadcast();
        int k = 0;
        while(k < i) 
        {
            IActivityRecognitionHardwareSink iactivityrecognitionhardwaresink = (IActivityRecognitionHardwareSink)mSinks.getBroadcastItem(k);
            try
            {
                iactivityrecognitionhardwaresink.onActivityChanged(aevent);
            }
            catch(RemoteException remoteexception)
            {
                Log.e("ActivityRecognitionHW", "Error delivering activity changed event.", remoteexception);
            }
            k++;
        }
        mSinks.finishBroadcast();
    }

    public boolean disableActivityEvent(String s, int i)
    {
        checkPermissions();
        int j = getActivityType(s);
        if(j == -1)
            return false;
        if(nativeDisableActivityEvent(j, i) == 0)
        {
            mSupportedActivitiesEnabledEvents[j][i] = 0;
            return true;
        } else
        {
            return false;
        }
    }

    public boolean enableActivityEvent(String s, int i, long l)
    {
        checkPermissions();
        int j = getActivityType(s);
        if(j == -1)
            return false;
        if(nativeEnableActivityEvent(j, i, l) == 0)
        {
            mSupportedActivitiesEnabledEvents[j][i] = 1;
            return true;
        } else
        {
            return false;
        }
    }

    public boolean flush()
    {
        boolean flag = false;
        checkPermissions();
        if(nativeFlush() == 0)
            flag = true;
        return flag;
    }

    public String[] getSupportedActivities()
    {
        checkPermissions();
        return mSupportedActivities;
    }

    public boolean isActivitySupported(String s)
    {
        checkPermissions();
        boolean flag;
        if(getActivityType(s) != -1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean registerSink(IActivityRecognitionHardwareSink iactivityrecognitionhardwaresink)
    {
        checkPermissions();
        return mSinks.register(iactivityrecognitionhardwaresink);
    }

    public boolean unregisterSink(IActivityRecognitionHardwareSink iactivityrecognitionhardwaresink)
    {
        checkPermissions();
        return mSinks.unregister(iactivityrecognitionhardwaresink);
    }

    private static final boolean DEBUG = Log.isLoggable("ActivityRecognitionHW", 3);
    private static final String ENFORCE_HW_PERMISSION_MESSAGE = "Permission 'android.permission.LOCATION_HARDWARE' not granted to access ActivityRecognitionHardware";
    private static final int EVENT_TYPE_COUNT = 3;
    private static final int EVENT_TYPE_DISABLED = 0;
    private static final int EVENT_TYPE_ENABLED = 1;
    private static final String HARDWARE_PERMISSION = "android.permission.LOCATION_HARDWARE";
    private static final int INVALID_ACTIVITY_TYPE = -1;
    private static final int NATIVE_SUCCESS_RESULT = 0;
    private static final String TAG = "ActivityRecognitionHW";
    private static ActivityRecognitionHardware sSingletonInstance;
    private static final Object sSingletonInstanceLock = new Object();
    private final Context mContext;
    private final SinkList mSinks = new SinkList(null);
    private final String mSupportedActivities[] = fetchSupportedActivities();
    private final int mSupportedActivitiesCount;
    private final int mSupportedActivitiesEnabledEvents[][];

    static 
    {
        nativeClassInit();
    }
}
