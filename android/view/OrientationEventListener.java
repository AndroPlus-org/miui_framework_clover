// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.content.Context;
import android.hardware.*;
import android.util.Log;

// Referenced classes of package android.view:
//            OrientationListener

public abstract class OrientationEventListener
{
    class SensorEventListenerImpl
        implements SensorEventListener
    {

        public void onAccuracyChanged(Sensor sensor, int i)
        {
        }

        public void onSensorChanged(SensorEvent sensorevent)
        {
            float af[] = sensorevent.values;
            int i = -1;
            float f = -af[0];
            float f1 = -af[1];
            float f2 = -af[2];
            if(4F * (f * f + f1 * f1) >= f2 * f2)
            {
                i = 90 - Math.round((float)Math.atan2(-f1, f) * 57.29578F);
                int j;
                do
                {
                    j = i;
                    if(i < 360)
                        break;
                    i -= 360;
                } while(true);
                do
                {
                    i = j;
                    if(j >= 0)
                        break;
                    j += 360;
                } while(true);
            }
            if(OrientationEventListener._2D_get0(OrientationEventListener.this) != null)
                OrientationEventListener._2D_get0(OrientationEventListener.this).onSensorChanged(1, sensorevent.values);
            if(i != OrientationEventListener._2D_get1(OrientationEventListener.this))
            {
                OrientationEventListener._2D_set0(OrientationEventListener.this, i);
                onOrientationChanged(i);
            }
        }

        private static final int _DATA_X = 0;
        private static final int _DATA_Y = 1;
        private static final int _DATA_Z = 2;
        final OrientationEventListener this$0;

        SensorEventListenerImpl()
        {
            this$0 = OrientationEventListener.this;
            super();
        }
    }


    static OrientationListener _2D_get0(OrientationEventListener orientationeventlistener)
    {
        return orientationeventlistener.mOldListener;
    }

    static int _2D_get1(OrientationEventListener orientationeventlistener)
    {
        return orientationeventlistener.mOrientation;
    }

    static int _2D_set0(OrientationEventListener orientationeventlistener, int i)
    {
        orientationeventlistener.mOrientation = i;
        return i;
    }

    public OrientationEventListener(Context context)
    {
        this(context, 3);
    }

    public OrientationEventListener(Context context, int i)
    {
        mOrientation = -1;
        mEnabled = false;
        mSensorManager = (SensorManager)context.getSystemService("sensor");
        mRate = i;
        mSensor = mSensorManager.getDefaultSensor(1);
        if(mSensor != null)
            mSensorEventListener = new SensorEventListenerImpl();
    }

    public boolean canDetectOrientation()
    {
        boolean flag;
        if(mSensor != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void disable()
    {
        if(mSensor == null)
        {
            Log.w("OrientationEventListener", "Cannot detect sensors. Invalid disable");
            return;
        }
        if(mEnabled)
        {
            mSensorManager.unregisterListener(mSensorEventListener);
            mEnabled = false;
        }
    }

    public void enable()
    {
        if(mSensor == null)
        {
            Log.w("OrientationEventListener", "Cannot detect sensors. Not enabled");
            return;
        }
        if(!mEnabled)
        {
            mSensorManager.registerListener(mSensorEventListener, mSensor, mRate);
            mEnabled = true;
        }
    }

    public abstract void onOrientationChanged(int i);

    void registerListener(OrientationListener orientationlistener)
    {
        mOldListener = orientationlistener;
    }

    private static final boolean DEBUG = false;
    public static final int ORIENTATION_UNKNOWN = -1;
    private static final String TAG = "OrientationEventListener";
    private static final boolean localLOGV = false;
    private boolean mEnabled;
    private OrientationListener mOldListener;
    private int mOrientation;
    private int mRate;
    private Sensor mSensor;
    private SensorEventListener mSensorEventListener;
    private SensorManager mSensorManager;
}
