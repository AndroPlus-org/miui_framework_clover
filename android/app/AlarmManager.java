// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.*;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import java.io.IOException;
import libcore.util.ZoneInfoDB;

// Referenced classes of package android.app:
//            IAlarmManager, PendingIntent, IAlarmCompleteListener

public class AlarmManager
{
    public static final class AlarmClockInfo
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public PendingIntent getShowIntent()
        {
            return mShowIntent;
        }

        public long getTriggerTime()
        {
            return mTriggerTime;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeLong(mTriggerTime);
            parcel.writeParcelable(mShowIntent, i);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public AlarmClockInfo createFromParcel(Parcel parcel)
            {
                return new AlarmClockInfo(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public AlarmClockInfo[] newArray(int i)
            {
                return new AlarmClockInfo[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private final PendingIntent mShowIntent;
        private final long mTriggerTime;


        public AlarmClockInfo(long l, PendingIntent pendingintent)
        {
            mTriggerTime = l;
            mShowIntent = pendingintent;
        }

        AlarmClockInfo(Parcel parcel)
        {
            mTriggerTime = parcel.readLong();
            mShowIntent = (PendingIntent)parcel.readParcelable(android/app/PendingIntent.getClassLoader());
        }
    }

    final class ListenerWrapper extends IAlarmListener.Stub
        implements Runnable
    {

        public void cancel()
        {
            try
            {
                AlarmManager._2D_get0(AlarmManager.this).remove(null, this);
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            android/app/AlarmManager;
            JVM INSTR monitorenter ;
            if(AlarmManager._2D_get1() != null)
                AlarmManager._2D_get1().remove(mListener);
            android/app/AlarmManager;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void doAlarm(IAlarmCompleteListener ialarmcompletelistener)
        {
            mCompletion = ialarmcompletelistener;
            android/app/AlarmManager;
            JVM INSTR monitorenter ;
            if(AlarmManager._2D_get1() != null)
                AlarmManager._2D_get1().remove(mListener);
            android/app/AlarmManager;
            JVM INSTR monitorexit ;
            mHandler.post(this);
            return;
            ialarmcompletelistener;
            throw ialarmcompletelistener;
        }

        public void run()
        {
            mListener.onAlarm();
            mCompletion.alarmComplete(this);
_L1:
            return;
            Exception exception;
            exception;
            Log.e("AlarmManager", "Unable to report completion to Alarm Manager!", exception);
              goto _L1
            Exception exception2;
            exception2;
            try
            {
                mCompletion.alarmComplete(this);
            }
            catch(Exception exception1)
            {
                Log.e("AlarmManager", "Unable to report completion to Alarm Manager!", exception1);
            }
            throw exception2;
        }

        public void setHandler(Handler handler)
        {
            mHandler = handler;
        }

        IAlarmCompleteListener mCompletion;
        Handler mHandler;
        final OnAlarmListener mListener;
        final AlarmManager this$0;

        public ListenerWrapper(OnAlarmListener onalarmlistener)
        {
            this$0 = AlarmManager.this;
            super();
            mListener = onalarmlistener;
        }
    }

    public static interface OnAlarmListener
    {

        public abstract void onAlarm();
    }


    static IAlarmManager _2D_get0(AlarmManager alarmmanager)
    {
        return alarmmanager.mService;
    }

    static ArrayMap _2D_get1()
    {
        return sWrappers;
    }

    AlarmManager(IAlarmManager ialarmmanager, Context context)
    {
        mService = ialarmmanager;
        mPackageName = context.getPackageName();
        mTargetSdkVersion = context.getApplicationInfo().targetSdkVersion;
        boolean flag;
        if(mTargetSdkVersion < 19)
            flag = true;
        else
            flag = false;
        mAlwaysExact = flag;
        mMainThreadHandler = new Handler(context.getMainLooper());
    }

    private long legacyExactLength()
    {
        long l;
        if(mAlwaysExact)
            l = 0L;
        else
            l = -1L;
        return l;
    }

    private void setImpl(int i, long l, long l1, long l2, 
            int j, PendingIntent pendingintent, OnAlarmListener onalarmlistener, String s, Handler handler, WorkSource worksource, AlarmClockInfo alarmclockinfo)
    {
        long l3;
        Object obj;
        l3 = l;
        if(l < 0L)
            l3 = 0L;
        obj = null;
        if(onalarmlistener == null)
            break MISSING_BLOCK_LABEL_105;
        android/app/AlarmManager;
        JVM INSTR monitorenter ;
        ListenerWrapper listenerwrapper;
        if(sWrappers == null)
        {
            obj = JVM INSTR new #126 <Class ArrayMap>;
            ((ArrayMap) (obj)).ArrayMap();
            sWrappers = ((ArrayMap) (obj));
        }
        listenerwrapper = (ListenerWrapper)sWrappers.get(onalarmlistener);
        obj = listenerwrapper;
        if(listenerwrapper != null)
            break MISSING_BLOCK_LABEL_90;
        obj = JVM INSTR new #11  <Class AlarmManager$ListenerWrapper>;
        ((ListenerWrapper) (obj)).this. ListenerWrapper(onalarmlistener);
        sWrappers.put(onalarmlistener, obj);
        android/app/AlarmManager;
        JVM INSTR monitorexit ;
        if(handler == null)
            handler = mMainThreadHandler;
        ((ListenerWrapper) (obj)).setHandler(handler);
        try
        {
            mService.set(mPackageName, i, l3, l1, l2, j, pendingintent, ((IAlarmListener) (obj)), s, worksource, alarmclockinfo);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(PendingIntent pendingintent)
        {
            throw pendingintent.rethrowFromSystemServer();
        }
        pendingintent;
_L2:
        android/app/AlarmManager;
        JVM INSTR monitorexit ;
        throw pendingintent;
        pendingintent;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void cancel(OnAlarmListener onalarmlistener)
    {
        if(onalarmlistener == null)
            throw new NullPointerException("cancel() called with a null OnAlarmListener");
        ListenerWrapper listenerwrapper = null;
        android/app/AlarmManager;
        JVM INSTR monitorenter ;
        if(sWrappers != null)
            listenerwrapper = (ListenerWrapper)sWrappers.get(onalarmlistener);
        android/app/AlarmManager;
        JVM INSTR monitorexit ;
        if(listenerwrapper == null)
        {
            Log.w("AlarmManager", (new StringBuilder()).append("Unrecognized alarm listener ").append(onalarmlistener).toString());
            return;
        } else
        {
            listenerwrapper.cancel();
            return;
        }
        onalarmlistener;
        throw onalarmlistener;
    }

    public void cancel(PendingIntent pendingintent)
    {
        if(pendingintent == null)
            if(mTargetSdkVersion >= 24)
            {
                throw new NullPointerException("cancel() called with a null PendingIntent");
            } else
            {
                Log.e("AlarmManager", "cancel() called with a null PendingIntent");
                return;
            }
        try
        {
            mService.remove(pendingintent, null);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(PendingIntent pendingintent)
        {
            throw pendingintent.rethrowFromSystemServer();
        }
    }

    public AlarmClockInfo getNextAlarmClock()
    {
        return getNextAlarmClock(UserHandle.myUserId());
    }

    public AlarmClockInfo getNextAlarmClock(int i)
    {
        AlarmClockInfo alarmclockinfo;
        try
        {
            alarmclockinfo = mService.getNextAlarmClock(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return alarmclockinfo;
    }

    public long getNextWakeFromIdleTime()
    {
        long l;
        try
        {
            l = mService.getNextWakeFromIdleTime();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return l;
    }

    public void set(int i, long l, long l1, long l2, 
            OnAlarmListener onalarmlistener, Handler handler, WorkSource worksource)
    {
        setImpl(i, l, l1, l2, 0, null, onalarmlistener, null, handler, worksource, null);
    }

    public void set(int i, long l, long l1, long l2, 
            PendingIntent pendingintent, WorkSource worksource)
    {
        setImpl(i, l, l1, l2, 0, pendingintent, null, null, null, worksource, null);
    }

    public void set(int i, long l, long l1, long l2, 
            String s, OnAlarmListener onalarmlistener, Handler handler, WorkSource worksource)
    {
        setImpl(i, l, l1, l2, 0, null, onalarmlistener, s, handler, worksource, null);
    }

    public void set(int i, long l, PendingIntent pendingintent)
    {
        setImpl(i, l, legacyExactLength(), 0L, 0, pendingintent, null, null, null, null, null);
    }

    public void set(int i, long l, String s, OnAlarmListener onalarmlistener, Handler handler)
    {
        setImpl(i, l, legacyExactLength(), 0L, 0, null, onalarmlistener, s, handler, null, null);
    }

    public void setAlarmClock(AlarmClockInfo alarmclockinfo, PendingIntent pendingintent)
    {
        setImpl(0, alarmclockinfo.getTriggerTime(), 0L, 0L, 0, pendingintent, null, null, null, null, alarmclockinfo);
    }

    public void setAndAllowWhileIdle(int i, long l, PendingIntent pendingintent)
    {
        setImpl(i, l, -1L, 0L, 4, pendingintent, null, null, null, null, null);
    }

    public void setExact(int i, long l, PendingIntent pendingintent)
    {
        setImpl(i, l, 0L, 0L, 0, pendingintent, null, null, null, null, null);
    }

    public void setExact(int i, long l, String s, OnAlarmListener onalarmlistener, Handler handler)
    {
        setImpl(i, l, 0L, 0L, 0, null, onalarmlistener, s, handler, null, null);
    }

    public void setExactAndAllowWhileIdle(int i, long l, PendingIntent pendingintent)
    {
        setImpl(i, l, 0L, 0L, 4, pendingintent, null, null, null, null, null);
    }

    public void setIdleUntil(int i, long l, String s, OnAlarmListener onalarmlistener, Handler handler)
    {
        setImpl(i, l, 0L, 0L, 16, null, onalarmlistener, s, handler, null, null);
    }

    public void setInexactRepeating(int i, long l, long l1, PendingIntent pendingintent)
    {
        setImpl(i, l, -1L, l1, 0, pendingintent, null, null, null, null, null);
    }

    public void setRepeating(int i, long l, long l1, PendingIntent pendingintent)
    {
        setImpl(i, l, legacyExactLength(), l1, 0, pendingintent, null, null, null, null, null);
    }

    public void setTime(long l)
    {
        try
        {
            mService.setTime(l);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void setTimeZone(String s)
    {
        if(TextUtils.isEmpty(s))
            return;
        if(mTargetSdkVersion < 23) goto _L2; else goto _L1
_L1:
        boolean flag = false;
        boolean flag1 = ZoneInfoDB.getInstance().hasTimeZone(s);
        flag = flag1;
_L4:
        if(!flag)
            throw new IllegalArgumentException((new StringBuilder()).append("Timezone: ").append(s).append(" is not an Olson ID").toString());
_L2:
        try
        {
            mService.setTimeZone(s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        IOException ioexception;
        ioexception;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setWindow(int i, long l, long l1, PendingIntent pendingintent)
    {
        setImpl(i, l, l1, 0L, 0, pendingintent, null, null, null, null, null);
    }

    public void setWindow(int i, long l, long l1, String s, OnAlarmListener onalarmlistener, 
            Handler handler)
    {
        setImpl(i, l, l1, 0L, 0, null, onalarmlistener, s, handler, null, null);
    }

    public static final String ACTION_NEXT_ALARM_CLOCK_CHANGED = "android.app.action.NEXT_ALARM_CLOCK_CHANGED";
    public static final int ELAPSED_REALTIME = 3;
    public static final int ELAPSED_REALTIME_WAKEUP = 2;
    public static final int FLAG_ALLOW_WHILE_IDLE = 4;
    public static final int FLAG_ALLOW_WHILE_IDLE_UNRESTRICTED = 8;
    public static final int FLAG_IDLE_UNTIL = 16;
    public static final int FLAG_STANDALONE = 1;
    public static final int FLAG_WAKE_FROM_IDLE = 2;
    public static final long INTERVAL_DAY = 0x5265c00L;
    public static final long INTERVAL_FIFTEEN_MINUTES = 0xdbba0L;
    public static final long INTERVAL_HALF_DAY = 0x2932e00L;
    public static final long INTERVAL_HALF_HOUR = 0x1b7740L;
    public static final long INTERVAL_HOUR = 0x36ee80L;
    public static final int RTC = 1;
    public static final int RTC_WAKEUP = 0;
    private static final String TAG = "AlarmManager";
    public static final long WINDOW_EXACT = 0L;
    public static final long WINDOW_HEURISTIC = -1L;
    private static ArrayMap sWrappers;
    private final boolean mAlwaysExact;
    private final Handler mMainThreadHandler;
    private final String mPackageName;
    private final IAlarmManager mService;
    private final int mTargetSdkVersion;
}
