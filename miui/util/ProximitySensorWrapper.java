// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.util;

import android.content.Context;
import android.hardware.*;
import android.os.Handler;
import android.os.Message;
import android.util.Slog;
import java.util.*;

public class ProximitySensorWrapper
{
    public static interface ProximitySensorChangeListener
    {

        public abstract void onSensorChanged(boolean flag);
    }


    static Handler _2D_get0(ProximitySensorWrapper proximitysensorwrapper)
    {
        return proximitysensorwrapper.mHandler;
    }

    static int _2D_get1(ProximitySensorWrapper proximitysensorwrapper)
    {
        return proximitysensorwrapper.mProximitySensorState;
    }

    static Sensor _2D_get2(ProximitySensorWrapper proximitysensorwrapper)
    {
        return proximitysensorwrapper.mSensor;
    }

    static int _2D_set0(ProximitySensorWrapper proximitysensorwrapper, int i)
    {
        proximitysensorwrapper.mProximitySensorState = i;
        return i;
    }

    static void _2D_wrap0(ProximitySensorWrapper proximitysensorwrapper, boolean flag)
    {
        proximitysensorwrapper.notifyListeners(flag);
    }

    public ProximitySensorWrapper(Context context)
    {
        mContext = context;
        mProximitySensorState = -1;
        mSensorManager = (SensorManager)mContext.getSystemService("sensor");
        mSensor = mSensorManager.getDefaultSensor(8);
    }

    private void notifyListeners(boolean flag)
    {
        List list = mProximitySensorChangeListeners;
        list;
        JVM INSTR monitorenter ;
        for(Iterator iterator = mProximitySensorChangeListeners.iterator(); iterator.hasNext(); ((ProximitySensorChangeListener)iterator.next()).onSensorChanged(flag));
        break MISSING_BLOCK_LABEL_49;
        Exception exception;
        exception;
        throw exception;
        list;
        JVM INSTR monitorexit ;
    }

    private void unregisterSensorEventListenerLocked()
    {
        if(mProximitySensorChangeListeners.size() == 0)
            mSensorManager.unregisterListener(mSensorListener, mSensor);
    }

    public void registerListener(ProximitySensorChangeListener proximitysensorchangelistener)
    {
        List list = mProximitySensorChangeListeners;
        list;
        JVM INSTR monitorenter ;
        if(!mProximitySensorChangeListeners.contains(proximitysensorchangelistener))
        {
            if(mProximitySensorChangeListeners.size() == 0)
                mSensorManager.registerListener(mSensorListener, mSensor, 0);
            mProximitySensorChangeListeners.add(proximitysensorchangelistener);
        }
        list;
        JVM INSTR monitorexit ;
        return;
        proximitysensorchangelistener;
        throw proximitysensorchangelistener;
    }

    public void unregisterAllListeners()
    {
        List list = mProximitySensorChangeListeners;
        list;
        JVM INSTR monitorenter ;
        mProximitySensorChangeListeners.clear();
        unregisterSensorEventListenerLocked();
        list;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void unregisterListener(ProximitySensorChangeListener proximitysensorchangelistener)
    {
        List list = mProximitySensorChangeListeners;
        list;
        JVM INSTR monitorenter ;
        mProximitySensorChangeListeners.remove(proximitysensorchangelistener);
        unregisterSensorEventListenerLocked();
        list;
        JVM INSTR monitorexit ;
        return;
        proximitysensorchangelistener;
        throw proximitysensorchangelistener;
    }

    private static final int EVENT_FAR = 1;
    private static final int EVENT_TOO_CLOSE = 0;
    private static final float PROXIMITY_THRESHOLD = 4F;
    public static final int STATE_STABLE_DELAY = 300;
    private final Context mContext;
    private final Handler mHandler = new Handler() {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 0 1: default 28
        //                       0 29
        //                       1 40;
               goto _L1 _L2 _L3
_L1:
            return;
_L2:
            ProximitySensorWrapper._2D_wrap0(ProximitySensorWrapper.this, true);
            continue; /* Loop/switch isn't completed */
_L3:
            ProximitySensorWrapper._2D_wrap0(ProximitySensorWrapper.this, false);
            if(true) goto _L1; else goto _L4
_L4:
        }

        final ProximitySensorWrapper this$0;

            
            {
                this$0 = ProximitySensorWrapper.this;
                super();
            }
    }
;
    private final List mProximitySensorChangeListeners = new ArrayList();
    private int mProximitySensorState;
    private final Sensor mSensor;
    private final SensorEventListener mSensorListener = new SensorEventListener() {

        public void onAccuracyChanged(Sensor sensor, int i)
        {
        }

        public void onSensorChanged(SensorEvent sensorevent)
        {
            float f = sensorevent.values[0];
            boolean flag;
            if((double)f >= 0.0D && f < 4F)
            {
                if(f < ProximitySensorWrapper._2D_get2(ProximitySensorWrapper.this).getMaximumRange())
                    flag = true;
                else
                    flag = false;
            } else
            {
                flag = false;
            }
            Slog.d("ProximitySensorWrapper", (new StringBuilder()).append("proximity distance: ").append(f).toString());
            if(!flag) goto _L2; else goto _L1
_L1:
            if(ProximitySensorWrapper._2D_get1(ProximitySensorWrapper.this) != 1)
            {
                ProximitySensorWrapper._2D_set0(ProximitySensorWrapper.this, 1);
                ProximitySensorWrapper._2D_get0(ProximitySensorWrapper.this).removeMessages(1);
                ProximitySensorWrapper._2D_get0(ProximitySensorWrapper.this).sendEmptyMessageDelayed(0, 300L);
            }
_L4:
            return;
_L2:
            if(ProximitySensorWrapper._2D_get1(ProximitySensorWrapper.this) != 0)
            {
                ProximitySensorWrapper._2D_set0(ProximitySensorWrapper.this, 0);
                ProximitySensorWrapper._2D_get0(ProximitySensorWrapper.this).removeMessages(0);
                ProximitySensorWrapper._2D_get0(ProximitySensorWrapper.this).sendEmptyMessageDelayed(1, 300L);
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        final ProximitySensorWrapper this$0;

            
            {
                this$0 = ProximitySensorWrapper.this;
                super();
            }
    }
;
    private final SensorManager mSensorManager;
}
