// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware;

import android.os.RemoteException;
import android.os.ServiceManager;
import android.view.IWindowManager;
import java.util.HashMap;
import java.util.Iterator;

// Referenced classes of package android.hardware:
//            SensorManager, Sensor, SensorListener, SensorEventListener, 
//            SensorEvent

final class LegacySensorManager
{
    private static final class LegacyListener
        implements SensorEventListener
    {

        private static int getLegacySensorType(int i)
        {
            switch(i)
            {
            case 4: // '\004'
            case 5: // '\005'
            case 6: // '\006'
            default:
                return 0;

            case 1: // '\001'
                return 2;

            case 2: // '\002'
                return 8;

            case 3: // '\003'
                return 128;

            case 7: // '\007'
                return 4;
            }
        }

        private static boolean hasOrientationSensor(int i)
        {
            boolean flag = false;
            if((i & 0x81) != 0)
                flag = true;
            return flag;
        }

        private void mapSensorDataToWindow(int i, float af[], int j)
        {
            float f;
            float f1;
            float f2;
            f = af[0];
            f1 = af[1];
            f2 = af[2];
            i;
            JVM INSTR lookupswitch 4: default 60
        //                       1: 205
        //                       2: 213
        //                       8: 231
        //                       128: 205;
               goto _L1 _L2 _L3 _L4 _L2
_L1:
            af[0] = f;
            af[1] = f1;
            af[2] = f2;
            af[3] = f;
            af[4] = f1;
            af[5] = f2;
            if((j & 1) == 0) goto _L6; else goto _L5
_L5:
            i;
            JVM INSTR lookupswitch 4: default 140
        //                       1: 263
        //                       2: 244
        //                       8: 244
        //                       128: 263;
               goto _L6 _L7 _L8 _L8 _L7
_L6:
            if((j & 2) == 0) goto _L10; else goto _L9
_L9:
            f = af[0];
            f2 = af[1];
            f1 = af[2];
            i;
            JVM INSTR lookupswitch 4: default 204
        //                       1: 325
        //                       2: 305
        //                       8: 305
        //                       128: 325;
               goto _L11 _L12 _L13 _L13 _L12
_L11:
            break; /* Loop/switch isn't completed */
_L12:
            break MISSING_BLOCK_LABEL_325;
_L10:
            return;
_L2:
            f2 = -f2;
              goto _L1
_L3:
            f = -f;
            f1 = -f1;
            f2 = -f2;
              goto _L1
_L4:
            f = -f;
            f1 = -f1;
              goto _L1
_L8:
            af[0] = -f1;
            af[1] = f;
            af[2] = f2;
              goto _L6
_L7:
            int k;
            if(f < 270F)
                k = 90;
            else
                k = -270;
            af[0] = (float)k + f;
            af[1] = f2;
            af[2] = f1;
              goto _L6
_L13:
            af[0] = -f;
            af[1] = -f2;
            af[2] = f1;
              goto _L10
            if(f >= 180F)
                f -= 180F;
            else
                f += 180F;
            af[0] = f;
            af[1] = -f2;
            af[2] = -f1;
              goto _L10
        }

        boolean hasSensors()
        {
            boolean flag = false;
            if(mSensors != 0)
                flag = true;
            return flag;
        }

        public void onAccuracyChanged(Sensor sensor, int i)
        {
            mTarget.onAccuracyChanged(getLegacySensorType(sensor.getType()), i);
_L2:
            return;
            sensor;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public void onSensorChanged(SensorEvent sensorevent)
        {
            float af[] = mValues;
            af[0] = sensorevent.values[0];
            af[1] = sensorevent.values[1];
            af[2] = sensorevent.values[2];
            int i = sensorevent.sensor.getType();
            int j = getLegacySensorType(i);
            mapSensorDataToWindow(j, af, LegacySensorManager.getRotation());
            if(i == 3)
            {
                if((mSensors & 0x80) != 0)
                    mTarget.onSensorChanged(128, af);
                if((mSensors & 1) != 0)
                {
                    af[0] = mYawfilter.filter(sensorevent.timestamp, af[0]);
                    mTarget.onSensorChanged(1, af);
                }
            } else
            {
                mTarget.onSensorChanged(j, af);
            }
        }

        boolean registerSensor(int i)
        {
            if((mSensors & i) != 0)
                return false;
            boolean flag = hasOrientationSensor(mSensors);
            mSensors = mSensors | i;
            return !flag || !hasOrientationSensor(i);
        }

        boolean unregisterSensor(int i)
        {
            if((mSensors & i) == 0)
                return false;
            mSensors = mSensors & i;
            return !hasOrientationSensor(i) || !hasOrientationSensor(mSensors);
        }

        private int mSensors;
        private SensorListener mTarget;
        private float mValues[];
        private final LmsFilter mYawfilter = new LmsFilter();

        LegacyListener(SensorListener sensorlistener)
        {
            mValues = new float[6];
            mTarget = sensorlistener;
            mSensors = 0;
        }
    }

    private static final class LmsFilter
    {

        public float filter(long l, float f)
        {
            float f1;
            float f2;
            f1 = f;
            f2 = mV[mIndex];
            if(f - f2 <= 180F) goto _L2; else goto _L1
_L1:
            f1 = f - 360F;
_L4:
            float f3;
            float f4;
            mIndex = mIndex + 1;
            if(mIndex >= 24)
                mIndex = 12;
            mV[mIndex] = f1;
            mT[mIndex] = l;
            mV[mIndex - 12] = f1;
            mT[mIndex - 12] = l;
            f = 0.0F;
            f2 = 0.0F;
            f1 = 0.0F;
            f3 = 0.0F;
            f4 = 0.0F;
            for(int i = 0; i < 11; i++)
            {
                int j = mIndex - 1 - i;
                float f5 = mV[j];
                float f6 = (float)((mT[j] / 2L + mT[j + 1] / 2L) - l) * 1E-009F;
                float f7 = (float)(mT[j] - mT[j + 1]) * 1E-009F;
                f7 *= f7;
                f4 += f5 * f7;
                f3 += f6 * f7 * f6;
                f1 += f6 * f7;
                f2 += f6 * f7 * f5;
                f += f7;
            }

            break; /* Loop/switch isn't completed */
_L2:
            if(f2 - f > 180F)
                f1 = f + 360F;
            if(true) goto _L4; else goto _L3
_L3:
            f2 = (f4 * f3 + f1 * f2) / (f * f3 + f1 * f1);
            f1 = (f2 + 0.08F * ((f * f2 - f4) / f1)) * 0.002777778F;
            if(f1 >= 0.0F)
                f2 = f1;
            else
                f2 = -f1;
            f = f1;
            if(f2 >= 0.5F)
                f = (f1 - (float)Math.ceil(0.5F + f1)) + 1.0F;
            f1 = f;
            if(f < 0.0F)
                f1 = f + 1.0F;
            return f1 * 360F;
        }

        private static final int COUNT = 12;
        private static final float PREDICTION_RATIO = 0.3333333F;
        private static final float PREDICTION_TIME = 0.08F;
        private static final int SENSORS_RATE_MS = 20;
        private int mIndex;
        private long mT[];
        private float mV[];

        public LmsFilter()
        {
            mV = new float[24];
            mT = new long[24];
            mIndex = 12;
        }
    }


    public LegacySensorManager(SensorManager sensormanager)
    {
        mLegacyListenersMap = new HashMap();
        mSensorManager = sensormanager;
        android/hardware/SensorManager;
        JVM INSTR monitorenter ;
        if(sInitialized)
            break MISSING_BLOCK_LABEL_72;
        sWindowManager = android.view.IWindowManager.Stub.asInterface(ServiceManager.getService("window"));
        sensormanager = sWindowManager;
        if(sensormanager == null)
            break MISSING_BLOCK_LABEL_72;
        try
        {
            sensormanager = sWindowManager;
            android.view.IRotationWatcher.Stub stub = JVM INSTR new #6   <Class LegacySensorManager$1>;
            stub.this. _cls1();
            sRotation = sensormanager.watchRotation(stub, 0);
        }
        // Misplaced declaration of an exception variable
        catch(SensorManager sensormanager) { }
        android/hardware/SensorManager;
        JVM INSTR monitorexit ;
        return;
        sensormanager;
        throw sensormanager;
    }

    static int getRotation()
    {
        android/hardware/SensorManager;
        JVM INSTR monitorenter ;
        int i = sRotation;
        android/hardware/SensorManager;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    static void onRotationChanged(int i)
    {
        android/hardware/SensorManager;
        JVM INSTR monitorenter ;
        sRotation = i;
        android/hardware/SensorManager;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private boolean registerLegacyListener(int i, int j, SensorListener sensorlistener, int k, int l)
    {
        boolean flag;
        boolean flag1;
        flag = false;
        flag1 = flag;
        if((k & i) == 0) goto _L2; else goto _L1
_L1:
        Sensor sensor;
        sensor = mSensorManager.getDefaultSensor(j);
        flag1 = flag;
        if(sensor == null) goto _L2; else goto _L3
_L3:
        HashMap hashmap = mLegacyListenersMap;
        hashmap;
        JVM INSTR monitorenter ;
        LegacyListener legacylistener = (LegacyListener)mLegacyListenersMap.get(sensorlistener);
        LegacyListener legacylistener1;
        legacylistener1 = legacylistener;
        if(legacylistener != null)
            break MISSING_BLOCK_LABEL_86;
        legacylistener1 = JVM INSTR new #8   <Class LegacySensorManager$LegacyListener>;
        legacylistener1.LegacyListener(sensorlistener);
        mLegacyListenersMap.put(sensorlistener, legacylistener1);
        if(!legacylistener1.registerSensor(i)) goto _L5; else goto _L4
_L4:
        flag1 = mSensorManager.registerListener(legacylistener1, sensor, l);
_L7:
        hashmap;
        JVM INSTR monitorexit ;
_L2:
        return flag1;
_L5:
        flag1 = true;
        if(true) goto _L7; else goto _L6
_L6:
        sensorlistener;
        throw sensorlistener;
    }

    private void unregisterLegacyListener(int i, int j, SensorListener sensorlistener, int k)
    {
        if((k & i) == 0) goto _L2; else goto _L1
_L1:
        Sensor sensor = mSensorManager.getDefaultSensor(j);
        if(sensor == null) goto _L2; else goto _L3
_L3:
        HashMap hashmap = mLegacyListenersMap;
        hashmap;
        JVM INSTR monitorenter ;
        LegacyListener legacylistener = (LegacyListener)mLegacyListenersMap.get(sensorlistener);
        if(legacylistener == null)
            break MISSING_BLOCK_LABEL_86;
        if(legacylistener.unregisterSensor(i))
        {
            mSensorManager.unregisterListener(legacylistener, sensor);
            if(!legacylistener.hasSensors())
                mLegacyListenersMap.remove(sensorlistener);
        }
        hashmap;
        JVM INSTR monitorexit ;
_L2:
        return;
        sensorlistener;
        throw sensorlistener;
    }

    public int getSensors()
    {
        int i = 0;
        Iterator iterator = mSensorManager.getFullSensorList().iterator();
        do
            if(iterator.hasNext())
                switch(((Sensor)iterator.next()).getType())
                {
                case 1: // '\001'
                    i |= 2;
                    break;

                case 2: // '\002'
                    i |= 8;
                    break;

                case 3: // '\003'
                    i |= 0x81;
                    break;
                }
            else
                return i;
        while(true);
    }

    public boolean registerListener(SensorListener sensorlistener, int i, int j)
    {
        if(sensorlistener == null)
            return false;
        boolean flag;
        if(!registerLegacyListener(2, 1, sensorlistener, i, j))
            flag = false;
        else
            flag = true;
        if(registerLegacyListener(8, 2, sensorlistener, i, j))
            flag = true;
        if(registerLegacyListener(128, 3, sensorlistener, i, j))
            flag = true;
        if(registerLegacyListener(1, 3, sensorlistener, i, j))
            flag = true;
        if(registerLegacyListener(4, 7, sensorlistener, i, j))
            flag = true;
        return flag;
    }

    public void unregisterListener(SensorListener sensorlistener, int i)
    {
        if(sensorlistener == null)
        {
            return;
        } else
        {
            unregisterLegacyListener(2, 1, sensorlistener, i);
            unregisterLegacyListener(8, 2, sensorlistener, i);
            unregisterLegacyListener(128, 3, sensorlistener, i);
            unregisterLegacyListener(1, 3, sensorlistener, i);
            unregisterLegacyListener(4, 7, sensorlistener, i);
            return;
        }
    }

    private static boolean sInitialized;
    private static int sRotation = 0;
    private static IWindowManager sWindowManager;
    private final HashMap mLegacyListenersMap;
    private final SensorManager mSensorManager;


    // Unreferenced inner class android/hardware/LegacySensorManager$1

/* anonymous class */
    class _cls1 extends android.view.IRotationWatcher.Stub
    {

        public void onRotationChanged(int i)
        {
            LegacySensorManager.onRotationChanged(i);
        }

        final LegacySensorManager this$0;

            
            {
                this$0 = LegacySensorManager.this;
                super();
            }
    }

}
