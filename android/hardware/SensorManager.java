// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware;

import android.os.Handler;
import android.os.MemoryFile;
import android.util.Log;
import android.util.SparseArray;
import java.util.*;

// Referenced classes of package android.hardware:
//            LegacySensorManager, Sensor, TriggerEventListener, SensorDirectChannel, 
//            HardwareBuffer, SensorEventListener, SensorListener, SensorAdditionalInfo

public abstract class SensorManager
{
    public static abstract class DynamicSensorCallback
    {

        public void onDynamicSensorConnected(Sensor sensor)
        {
        }

        public void onDynamicSensorDisconnected(Sensor sensor)
        {
        }

        public DynamicSensorCallback()
        {
        }
    }


    public SensorManager()
    {
    }

    public static float getAltitude(float f, float f1)
    {
        return (1.0F - (float)Math.pow(f1 / f, 0.19029495120048523D)) * 44330F;
    }

    public static void getAngleChange(float af[], float af1[], float af2[])
    {
        float f = 0.0F;
        float f1 = 0.0F;
        float f2 = 0.0F;
        float f3 = 0.0F;
        float f4 = 0.0F;
        float f5 = 0.0F;
        float f6 = 0.0F;
        float f7 = 0.0F;
        float f8 = 0.0F;
        float f9 = 0.0F;
        float f10 = 0.0F;
        float f11 = 0.0F;
        float f12 = 0.0F;
        float f13 = 0.0F;
        float f14 = 0.0F;
        float f15 = 0.0F;
        float f16 = 0.0F;
        float f17 = 0.0F;
        if(af1.length == 9)
        {
            f = af1[0];
            f1 = af1[1];
            f2 = af1[2];
            f3 = af1[3];
            f4 = af1[4];
            f5 = af1[5];
            f6 = af1[6];
            f7 = af1[7];
            f8 = af1[8];
        } else
        if(af1.length == 16)
        {
            f = af1[0];
            f1 = af1[1];
            f2 = af1[2];
            f3 = af1[4];
            f4 = af1[5];
            f5 = af1[6];
            f6 = af1[8];
            f7 = af1[9];
            f8 = af1[10];
        }
        if(af2.length == 9)
        {
            f9 = af2[0];
            f10 = af2[1];
            f11 = af2[2];
            f12 = af2[3];
            f13 = af2[4];
            f14 = af2[5];
            f15 = af2[6];
            f16 = af2[7];
            f17 = af2[8];
        } else
        if(af2.length == 16)
        {
            f9 = af2[0];
            f10 = af2[1];
            f11 = af2[2];
            f12 = af2[4];
            f13 = af2[5];
            f14 = af2[6];
            f15 = af2[8];
            f16 = af2[9];
            f17 = af2[10];
        }
        af[0] = (float)Math.atan2(f9 * f1 + f12 * f4 + f15 * f7, f10 * f1 + f13 * f4 + f16 * f7);
        af[1] = (float)Math.asin(-(f11 * f1 + f14 * f4 + f17 * f7));
        af[2] = (float)Math.atan2(-(f11 * f + f14 * f3 + f17 * f6), f11 * f2 + f14 * f5 + f17 * f8);
    }

    private static int getDelay(int i)
    {
        i;
        JVM INSTR tableswitch 0 3: default 32
    //                   0 34
    //                   1 39
    //                   2 46
    //                   3 52;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        return i;
_L2:
        i = 0;
        continue; /* Loop/switch isn't completed */
_L3:
        i = 20000;
        continue; /* Loop/switch isn't completed */
_L4:
        i = 0x1046b;
        continue; /* Loop/switch isn't completed */
_L5:
        i = 0x30d40;
        if(true) goto _L1; else goto _L6
_L6:
    }

    public static float getInclination(float af[])
    {
        if(af.length == 9)
            return (float)Math.atan2(af[5], af[4]);
        else
            return (float)Math.atan2(af[6], af[5]);
    }

    private LegacySensorManager getLegacySensorManager()
    {
        SparseArray sparsearray = mSensorListByType;
        sparsearray;
        JVM INSTR monitorenter ;
        LegacySensorManager legacysensormanager1;
        if(mLegacySensorManager == null)
        {
            Log.i("SensorManager", "This application is using deprecated SensorManager API which will be removed someday.  Please consider switching to the new API.");
            LegacySensorManager legacysensormanager = JVM INSTR new #172 <Class LegacySensorManager>;
            legacysensormanager.LegacySensorManager(this);
            mLegacySensorManager = legacysensormanager;
        }
        legacysensormanager1 = mLegacySensorManager;
        sparsearray;
        JVM INSTR monitorexit ;
        return legacysensormanager1;
        Exception exception;
        exception;
        throw exception;
    }

    public static float[] getOrientation(float af[], float af1[])
    {
        if(af.length == 9)
        {
            af1[0] = (float)Math.atan2(af[1], af[4]);
            af1[1] = (float)Math.asin(-af[7]);
            af1[2] = (float)Math.atan2(-af[6], af[8]);
        } else
        {
            af1[0] = (float)Math.atan2(af[1], af[5]);
            af1[1] = (float)Math.asin(-af[9]);
            af1[2] = (float)Math.atan2(-af[8], af[10]);
        }
        return af1;
    }

    public static void getQuaternionFromVector(float af[], float af1[])
    {
        float f = 0.0F;
        if(af1.length >= 4)
        {
            af[0] = af1[3];
        } else
        {
            af[0] = 1.0F - af1[0] * af1[0] - af1[1] * af1[1] - af1[2] * af1[2];
            if(af[0] > 0.0F)
                f = (float)Math.sqrt(af[0]);
            af[0] = f;
        }
        af[1] = af1[0];
        af[2] = af1[1];
        af[3] = af1[2];
    }

    public static boolean getRotationMatrix(float af[], float af1[], float af2[], float af3[])
    {
        float f = af2[0];
        float f1 = af2[1];
        float f2 = af2[2];
        if(f * f + f1 * f1 + f2 * f2 < 0.962361F)
            return false;
        float f3 = af3[0];
        float f4 = af3[1];
        float f5 = af3[2];
        float f6 = f4 * f2 - f5 * f1;
        float f7 = f5 * f - f3 * f2;
        float f8 = f3 * f1 - f4 * f;
        float f9 = (float)Math.sqrt(f6 * f6 + f7 * f7 + f8 * f8);
        if(f9 < 0.1F)
            return false;
        f9 = 1.0F / f9;
        f6 *= f9;
        f7 *= f9;
        f8 *= f9;
        f9 = 1.0F / (float)Math.sqrt(f * f + f1 * f1 + f2 * f2);
        f *= f9;
        f1 *= f9;
        f2 *= f9;
        float f10 = f1 * f8 - f2 * f7;
        float f11 = f2 * f6 - f * f8;
        f9 = f * f7 - f1 * f6;
        if(af != null)
            if(af.length == 9)
            {
                af[0] = f6;
                af[1] = f7;
                af[2] = f8;
                af[3] = f10;
                af[4] = f11;
                af[5] = f9;
                af[6] = f;
                af[7] = f1;
                af[8] = f2;
            } else
            if(af.length == 16)
            {
                af[0] = f6;
                af[1] = f7;
                af[2] = f8;
                af[3] = 0.0F;
                af[4] = f10;
                af[5] = f11;
                af[6] = f9;
                af[7] = 0.0F;
                af[8] = f;
                af[9] = f1;
                af[10] = f2;
                af[11] = 0.0F;
                af[12] = 0.0F;
                af[13] = 0.0F;
                af[14] = 0.0F;
                af[15] = 1.0F;
            }
        if(af1 != null)
        {
            f6 = 1.0F / (float)Math.sqrt(f3 * f3 + f4 * f4 + f5 * f5);
            f7 = (f3 * f10 + f4 * f11 + f5 * f9) * f6;
            f3 = (f3 * f + f4 * f1 + f5 * f2) * f6;
            if(af1.length == 9)
            {
                af1[0] = 1.0F;
                af1[1] = 0.0F;
                af1[2] = 0.0F;
                af1[3] = 0.0F;
                af1[4] = f7;
                af1[5] = f3;
                af1[6] = 0.0F;
                af1[7] = -f3;
                af1[8] = f7;
            } else
            if(af1.length == 16)
            {
                af1[0] = 1.0F;
                af1[1] = 0.0F;
                af1[2] = 0.0F;
                af1[4] = 0.0F;
                af1[5] = f7;
                af1[6] = f3;
                af1[8] = 0.0F;
                af1[9] = -f3;
                af1[10] = f7;
                af1[14] = 0.0F;
                af1[13] = 0.0F;
                af1[12] = 0.0F;
                af1[11] = 0.0F;
                af1[7] = 0.0F;
                af1[3] = 0.0F;
                af1[15] = 1.0F;
            }
        }
        return true;
    }

    public static void getRotationMatrixFromVector(float af[], float af1[])
    {
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        float f8;
        float f9;
        float f10;
        float f = af1[0];
        float f1 = af1[1];
        f2 = af1[2];
        if(af1.length >= 4)
        {
            f3 = af1[3];
        } else
        {
            f3 = 1.0F - f * f - f1 * f1 - f2 * f2;
            if(f3 > 0.0F)
                f3 = (float)Math.sqrt(f3);
            else
                f3 = 0.0F;
        }
        f4 = 2.0F * f * f;
        f5 = 2.0F * f1 * f1;
        f6 = 2.0F * f2 * f2;
        f7 = 2.0F * f * f1;
        f8 = 2.0F * f2 * f3;
        f9 = 2.0F * f * f2;
        f10 = 2.0F * f1 * f3;
        f2 = 2.0F * f1 * f2;
        f3 = 2.0F * f * f3;
        if(af.length != 9) goto _L2; else goto _L1
_L1:
        af[0] = 1.0F - f5 - f6;
        af[1] = f7 - f8;
        af[2] = f9 + f10;
        af[3] = f7 + f8;
        af[4] = 1.0F - f4 - f6;
        af[5] = f2 - f3;
        af[6] = f9 - f10;
        af[7] = f2 + f3;
        af[8] = 1.0F - f4 - f5;
_L4:
        return;
_L2:
        if(af.length == 16)
        {
            af[0] = 1.0F - f5 - f6;
            af[1] = f7 - f8;
            af[2] = f9 + f10;
            af[3] = 0.0F;
            af[4] = f7 + f8;
            af[5] = 1.0F - f4 - f6;
            af[6] = f2 - f3;
            af[7] = 0.0F;
            af[8] = f9 - f10;
            af[9] = f2 + f3;
            af[10] = 1.0F - f4 - f5;
            af[11] = 0.0F;
            af[14] = 0.0F;
            af[13] = 0.0F;
            af[12] = 0.0F;
            af[15] = 1.0F;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static boolean remapCoordinateSystem(float af[], int i, int j, float af1[])
    {
        if(af != af1) goto _L2; else goto _L1
_L1:
        float af2[] = mTempMatrix;
        af2;
        JVM INSTR monitorenter ;
        if(!remapCoordinateSystemImpl(af, i, j, af2))
            break MISSING_BLOCK_LABEL_52;
        j = af1.length;
        for(i = 0; i < j; i++)
            af1[i] = af2[i];

        af2;
        JVM INSTR monitorexit ;
        return true;
        af2;
        JVM INSTR monitorexit ;
_L2:
        return remapCoordinateSystemImpl(af, i, j, af1);
        af;
        throw af;
    }

    private static boolean remapCoordinateSystemImpl(float af[], int i, int j, float af1[])
    {
        int k = af1.length;
        if(af.length != k)
            return false;
        if((i & 0x7c) != 0 || (j & 0x7c) != 0)
            return false;
        if((i & 3) == 0 || (j & 3) == 0)
            return false;
        if((i & 3) == (j & 3))
            return false;
        int l = i ^ j;
        int i1 = (i & 3) - 1;
        int j1 = (j & 3) - 1;
        int k1 = (l & 3) - 1;
        int l1 = l;
        if((i1 ^ (k1 + 1) % 3 | j1 ^ (k1 + 2) % 3) != 0)
            l1 = l ^ 0x80;
        boolean flag;
        int i2;
        if(i >= 128)
            i = 1;
        else
            i = 0;
        if(j >= 128)
            j = 1;
        else
            j = 0;
        if(l1 >= 128)
            flag = true;
        else
            flag = false;
        if(k == 16)
            l = 4;
        else
            l = 3;
        for(i2 = 0; i2 < 3; i2++)
        {
            int j2 = i2 * l;
            int k2 = 0;
            while(k2 < 3) 
            {
                float f;
                if(i1 == k2)
                {
                    if(i != 0)
                        f = -af[j2 + 0];
                    else
                        f = af[j2 + 0];
                    af1[j2 + k2] = f;
                }
                if(j1 == k2)
                {
                    if(j != 0)
                        f = -af[j2 + 1];
                    else
                        f = af[j2 + 1];
                    af1[j2 + k2] = f;
                }
                if(k1 == k2)
                {
                    if(flag)
                        f = -af[j2 + 2];
                    else
                        f = af[j2 + 2];
                    af1[j2 + k2] = f;
                }
                k2++;
            }
        }

        if(k == 16)
        {
            af1[14] = 0.0F;
            af1[13] = 0.0F;
            af1[12] = 0.0F;
            af1[11] = 0.0F;
            af1[7] = 0.0F;
            af1[3] = 0.0F;
            af1[15] = 1.0F;
        }
        return true;
    }

    public boolean cancelTriggerSensor(TriggerEventListener triggereventlistener, Sensor sensor)
    {
        return cancelTriggerSensorImpl(triggereventlistener, sensor, true);
    }

    protected abstract boolean cancelTriggerSensorImpl(TriggerEventListener triggereventlistener, Sensor sensor, boolean flag);

    public int configureDirectChannel(SensorDirectChannel sensordirectchannel, Sensor sensor, int i)
    {
        return configureDirectChannelImpl(sensordirectchannel, sensor, i);
    }

    protected abstract int configureDirectChannelImpl(SensorDirectChannel sensordirectchannel, Sensor sensor, int i);

    public SensorDirectChannel createDirectChannel(HardwareBuffer hardwarebuffer)
    {
        return createDirectChannelImpl(null, hardwarebuffer);
    }

    public SensorDirectChannel createDirectChannel(MemoryFile memoryfile)
    {
        return createDirectChannelImpl(memoryfile, null);
    }

    protected abstract SensorDirectChannel createDirectChannelImpl(MemoryFile memoryfile, HardwareBuffer hardwarebuffer);

    void destroyDirectChannel(SensorDirectChannel sensordirectchannel)
    {
        destroyDirectChannelImpl(sensordirectchannel);
    }

    protected abstract void destroyDirectChannelImpl(SensorDirectChannel sensordirectchannel);

    public boolean flush(SensorEventListener sensoreventlistener)
    {
        return flushImpl(sensoreventlistener);
    }

    protected abstract boolean flushImpl(SensorEventListener sensoreventlistener);

    public Sensor getDefaultSensor(int i)
    {
        Object obj;
        boolean flag;
        obj = getSensorList(i);
        flag = false;
        break MISSING_BLOCK_LABEL_8;
        Sensor sensor;
        if(i == 8 || i == 17 || i == 22 || i == 23 || i == 24 || i == 25 || i == 26 || i == 32)
            flag = true;
        obj = ((Iterable) (obj)).iterator();
        do
        {
            if(!((Iterator) (obj)).hasNext())
                break MISSING_BLOCK_LABEL_100;
            sensor = (Sensor)((Iterator) (obj)).next();
        } while(sensor.isWakeUpSensor() != flag);
        return sensor;
        return null;
    }

    public Sensor getDefaultSensor(int i, boolean flag)
    {
        for(Iterator iterator = getSensorList(i).iterator(); iterator.hasNext();)
        {
            Sensor sensor = (Sensor)iterator.next();
            if(sensor.isWakeUpSensor() == flag)
                return sensor;
        }

        return null;
    }

    public List getDynamicSensorList(int i)
    {
        Object obj = getFullDynamicSensorList();
        if(i == -1)
            return Collections.unmodifiableList(((List) (obj)));
        ArrayList arraylist = new ArrayList();
        obj = ((Iterable) (obj)).iterator();
        do
        {
            if(!((Iterator) (obj)).hasNext())
                break;
            Sensor sensor = (Sensor)((Iterator) (obj)).next();
            if(sensor.getType() == i)
                arraylist.add(sensor);
        } while(true);
        return Collections.unmodifiableList(arraylist);
    }

    protected abstract List getFullDynamicSensorList();

    protected abstract List getFullSensorList();

    public List getSensorList(int i)
    {
        Object obj = getFullSensorList();
        SparseArray sparsearray = mSensorListByType;
        sparsearray;
        JVM INSTR monitorenter ;
        Object obj1 = (List)mSensorListByType.get(i);
        Object obj2 = obj1;
        if(obj1 != null) goto _L2; else goto _L1
_L1:
        if(i != -1) goto _L4; else goto _L3
_L3:
        obj2 = obj;
_L8:
        obj2 = Collections.unmodifiableList(((List) (obj2)));
        mSensorListByType.append(i, obj2);
_L2:
        sparsearray;
        JVM INSTR monitorexit ;
        return ((List) (obj2));
_L4:
        obj1 = JVM INSTR new #262 <Class ArrayList>;
        ((ArrayList) (obj1)).ArrayList();
        obj = ((Iterable) (obj)).iterator();
_L6:
        obj2 = obj1;
        if(!((Iterator) (obj)).hasNext())
            break; /* Loop/switch isn't completed */
        Sensor sensor = (Sensor)((Iterator) (obj)).next();
        if(sensor.getType() == i)
            ((List) (obj1)).add(sensor);
        if(true) goto _L6; else goto _L5
_L5:
        if(true) goto _L8; else goto _L7
_L7:
        Exception exception;
        exception;
        throw exception;
    }

    public int getSensors()
    {
        return getLegacySensorManager().getSensors();
    }

    public boolean initDataInjection(boolean flag)
    {
        return initDataInjectionImpl(flag);
    }

    protected abstract boolean initDataInjectionImpl(boolean flag);

    public boolean injectSensorData(Sensor sensor, float af[], int i, long l)
    {
        if(sensor == null)
            throw new IllegalArgumentException("sensor cannot be null");
        if(!sensor.isDataInjectionSupported())
            throw new IllegalArgumentException("sensor does not support data injection");
        if(af == null)
            throw new IllegalArgumentException("sensor data cannot be null");
        int j = Sensor.getMaxLengthValuesArray(sensor, 23);
        if(af.length != j)
            throw new IllegalArgumentException((new StringBuilder()).append("Wrong number of values for sensor ").append(sensor.getName()).append(" actual=").append(af.length).append(" expected=").append(j).toString());
        if(i < -1 || i > 3)
            throw new IllegalArgumentException("Invalid sensor accuracy");
        if(l <= 0L)
            throw new IllegalArgumentException("Negative or zero sensor timestamp");
        else
            return injectSensorDataImpl(sensor, af, i, l);
    }

    protected abstract boolean injectSensorDataImpl(Sensor sensor, float af[], int i, long l);

    public boolean isDynamicSensorDiscoverySupported()
    {
        boolean flag = false;
        if(getSensorList(32).size() > 0)
            flag = true;
        return flag;
    }

    public void registerDynamicSensorCallback(DynamicSensorCallback dynamicsensorcallback)
    {
        registerDynamicSensorCallback(dynamicsensorcallback, null);
    }

    public void registerDynamicSensorCallback(DynamicSensorCallback dynamicsensorcallback, Handler handler)
    {
        registerDynamicSensorCallbackImpl(dynamicsensorcallback, handler);
    }

    protected abstract void registerDynamicSensorCallbackImpl(DynamicSensorCallback dynamicsensorcallback, Handler handler);

    public boolean registerListener(SensorEventListener sensoreventlistener, Sensor sensor, int i)
    {
        return registerListener(sensoreventlistener, sensor, i, ((Handler) (null)));
    }

    public boolean registerListener(SensorEventListener sensoreventlistener, Sensor sensor, int i, int j)
    {
        return registerListenerImpl(sensoreventlistener, sensor, getDelay(i), null, j, 0);
    }

    public boolean registerListener(SensorEventListener sensoreventlistener, Sensor sensor, int i, int j, Handler handler)
    {
        return registerListenerImpl(sensoreventlistener, sensor, getDelay(i), handler, j, 0);
    }

    public boolean registerListener(SensorEventListener sensoreventlistener, Sensor sensor, int i, Handler handler)
    {
        return registerListenerImpl(sensoreventlistener, sensor, getDelay(i), handler, 0, 0);
    }

    public boolean registerListener(SensorListener sensorlistener, int i)
    {
        return registerListener(sensorlistener, i, 3);
    }

    public boolean registerListener(SensorListener sensorlistener, int i, int j)
    {
        return getLegacySensorManager().registerListener(sensorlistener, i, j);
    }

    protected abstract boolean registerListenerImpl(SensorEventListener sensoreventlistener, Sensor sensor, int i, Handler handler, int j, int k);

    public boolean requestTriggerSensor(TriggerEventListener triggereventlistener, Sensor sensor)
    {
        return requestTriggerSensorImpl(triggereventlistener, sensor);
    }

    protected abstract boolean requestTriggerSensorImpl(TriggerEventListener triggereventlistener, Sensor sensor);

    public boolean setOperationParameter(SensorAdditionalInfo sensoradditionalinfo)
    {
        return setOperationParameterImpl(sensoradditionalinfo);
    }

    protected abstract boolean setOperationParameterImpl(SensorAdditionalInfo sensoradditionalinfo);

    public void unregisterDynamicSensorCallback(DynamicSensorCallback dynamicsensorcallback)
    {
        unregisterDynamicSensorCallbackImpl(dynamicsensorcallback);
    }

    protected abstract void unregisterDynamicSensorCallbackImpl(DynamicSensorCallback dynamicsensorcallback);

    public void unregisterListener(SensorEventListener sensoreventlistener)
    {
        if(sensoreventlistener == null)
        {
            return;
        } else
        {
            unregisterListenerImpl(sensoreventlistener, null);
            return;
        }
    }

    public void unregisterListener(SensorEventListener sensoreventlistener, Sensor sensor)
    {
        if(sensoreventlistener == null || sensor == null)
        {
            return;
        } else
        {
            unregisterListenerImpl(sensoreventlistener, sensor);
            return;
        }
    }

    public void unregisterListener(SensorListener sensorlistener)
    {
        unregisterListener(sensorlistener, 255);
    }

    public void unregisterListener(SensorListener sensorlistener, int i)
    {
        getLegacySensorManager().unregisterListener(sensorlistener, i);
    }

    protected abstract void unregisterListenerImpl(SensorEventListener sensoreventlistener, Sensor sensor);

    public static final int AXIS_MINUS_X = 129;
    public static final int AXIS_MINUS_Y = 130;
    public static final int AXIS_MINUS_Z = 131;
    public static final int AXIS_X = 1;
    public static final int AXIS_Y = 2;
    public static final int AXIS_Z = 3;
    public static final int DATA_X = 0;
    public static final int DATA_Y = 1;
    public static final int DATA_Z = 2;
    public static final float GRAVITY_DEATH_STAR_I = 3.530361E-007F;
    public static final float GRAVITY_EARTH = 9.80665F;
    public static final float GRAVITY_JUPITER = 23.12F;
    public static final float GRAVITY_MARS = 3.71F;
    public static final float GRAVITY_MERCURY = 3.7F;
    public static final float GRAVITY_MOON = 1.6F;
    public static final float GRAVITY_NEPTUNE = 11F;
    public static final float GRAVITY_PLUTO = 0.6F;
    public static final float GRAVITY_SATURN = 8.96F;
    public static final float GRAVITY_SUN = 275F;
    public static final float GRAVITY_THE_ISLAND = 4.815162F;
    public static final float GRAVITY_URANUS = 8.69F;
    public static final float GRAVITY_VENUS = 8.87F;
    public static final float LIGHT_CLOUDY = 100F;
    public static final float LIGHT_FULLMOON = 0.25F;
    public static final float LIGHT_NO_MOON = 0.001F;
    public static final float LIGHT_OVERCAST = 10000F;
    public static final float LIGHT_SHADE = 20000F;
    public static final float LIGHT_SUNLIGHT = 110000F;
    public static final float LIGHT_SUNLIGHT_MAX = 120000F;
    public static final float LIGHT_SUNRISE = 400F;
    public static final float MAGNETIC_FIELD_EARTH_MAX = 60F;
    public static final float MAGNETIC_FIELD_EARTH_MIN = 30F;
    public static final float PRESSURE_STANDARD_ATMOSPHERE = 1013.25F;
    public static final int RAW_DATA_INDEX = 3;
    public static final int RAW_DATA_X = 3;
    public static final int RAW_DATA_Y = 4;
    public static final int RAW_DATA_Z = 5;
    public static final int SENSOR_ACCELEROMETER = 2;
    public static final int SENSOR_ALL = 127;
    public static final int SENSOR_DELAY_FASTEST = 0;
    public static final int SENSOR_DELAY_GAME = 1;
    public static final int SENSOR_DELAY_NORMAL = 3;
    public static final int SENSOR_DELAY_UI = 2;
    public static final int SENSOR_LIGHT = 16;
    public static final int SENSOR_MAGNETIC_FIELD = 8;
    public static final int SENSOR_MAX = 64;
    public static final int SENSOR_MIN = 1;
    public static final int SENSOR_ORIENTATION = 1;
    public static final int SENSOR_ORIENTATION_RAW = 128;
    public static final int SENSOR_PROXIMITY = 32;
    public static final int SENSOR_STATUS_ACCURACY_HIGH = 3;
    public static final int SENSOR_STATUS_ACCURACY_LOW = 1;
    public static final int SENSOR_STATUS_ACCURACY_MEDIUM = 2;
    public static final int SENSOR_STATUS_NO_CONTACT = -1;
    public static final int SENSOR_STATUS_UNRELIABLE = 0;
    public static final int SENSOR_TEMPERATURE = 4;
    public static final int SENSOR_TRICORDER = 64;
    public static final float STANDARD_GRAVITY = 9.80665F;
    protected static final String TAG = "SensorManager";
    private static final float mTempMatrix[] = new float[16];
    private LegacySensorManager mLegacySensorManager;
    private final SparseArray mSensorListByType = new SparseArray();

}
