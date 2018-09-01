// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware;

import android.content.*;
import android.content.pm.ApplicationInfo;
import android.os.*;
import android.util.*;
import dalvik.system.CloseGuard;
import java.io.*;
import java.lang.ref.WeakReference;
import java.util.*;

// Referenced classes of package android.hardware:
//            SensorManager, Sensor, TriggerEventListener, SensorEventListener, 
//            SensorDirectChannel, HardwareBuffer, SensorAdditionalInfo, SensorEvent, 
//            SensorEventCallback, SensorEventListener2, TriggerEvent

public class SystemSensorManager extends SensorManager
{
    private static abstract class BaseEventQueue
    {

        private int disableSensor(Sensor sensor)
        {
            if(nSensorEventQueue == 0L)
                throw new NullPointerException();
            if(sensor == null)
                throw new NullPointerException();
            else
                return nativeDisableSensor(nSensorEventQueue, sensor.getHandle());
        }

        private void dispose(boolean flag)
        {
            if(mCloseGuard != null)
            {
                if(flag)
                    mCloseGuard.warnIfOpen();
                mCloseGuard.close();
            }
            if(nSensorEventQueue != 0L)
            {
                nativeDestroySensorEventQueue(nSensorEventQueue);
                nSensorEventQueue = 0L;
            }
        }

        private int enableSensor(Sensor sensor, int i, int j)
        {
            if(nSensorEventQueue == 0L)
                throw new NullPointerException();
            if(sensor == null)
                throw new NullPointerException();
            else
                return nativeEnableSensor(nSensorEventQueue, sensor.getHandle(), i, j);
        }

        private static native void nativeDestroySensorEventQueue(long l);

        private static native int nativeDisableSensor(long l, int i);

        private static native int nativeEnableSensor(long l, int i, int j, int k);

        private static native int nativeFlushSensor(long l);

        private static native long nativeInitBaseEventQueue(long l, WeakReference weakreference, MessageQueue messagequeue, String s, int i, String s1);

        private static native int nativeInjectSensorData(long l, int i, float af[], int j, long l1);

        public boolean addSensor(Sensor sensor, int i, int j)
        {
            int k = sensor.getHandle();
            if(mActiveSensors.get(k))
                return false;
            mActiveSensors.put(k, true);
            addSensorEvent(sensor);
            if(enableSensor(sensor, i, j) != 0 && (j == 0 || j > 0 && enableSensor(sensor, i, 0) != 0))
            {
                removeSensor(sensor, false);
                return false;
            } else
            {
                return true;
            }
        }

        protected abstract void addSensorEvent(Sensor sensor);

        protected void dispatchAdditionalInfoEvent(int i, int j, int k, float af[], int ai[])
        {
        }

        protected abstract void dispatchFlushCompleteEvent(int i);

        protected abstract void dispatchSensorEvent(int i, float af[], int j, long l);

        public void dispose()
        {
            dispose(false);
        }

        protected void finalize()
            throws Throwable
        {
            dispose(true);
            super.finalize();
            return;
            Exception exception;
            exception;
            super.finalize();
            throw exception;
        }

        public int flush()
        {
            if(nSensorEventQueue == 0L)
                throw new NullPointerException();
            else
                return nativeFlushSensor(nSensorEventQueue);
        }

        public boolean hasSensors()
        {
            boolean flag = true;
            if(mActiveSensors.indexOfValue(true) < 0)
                flag = false;
            return flag;
        }

        protected int injectSensorDataBase(int i, float af[], int j, long l)
        {
            return nativeInjectSensorData(nSensorEventQueue, i, af, j, l);
        }

        public boolean removeAllSensors()
        {
            for(int i = 0; i < mActiveSensors.size(); i++)
            {
                if(!mActiveSensors.valueAt(i))
                    continue;
                int j = mActiveSensors.keyAt(i);
                Sensor sensor = (Sensor)SystemSensorManager._2D_get1(mManager).get(Integer.valueOf(j));
                if(sensor != null)
                {
                    disableSensor(sensor);
                    mActiveSensors.put(j, false);
                    removeSensorEvent(sensor);
                }
            }

            return true;
        }

        public boolean removeSensor(Sensor sensor, boolean flag)
        {
            int i = sensor.getHandle();
            if(mActiveSensors.get(i))
            {
                if(flag)
                    disableSensor(sensor);
                mActiveSensors.put(sensor.getHandle(), false);
                removeSensorEvent(sensor);
                return true;
            } else
            {
                return false;
            }
        }

        protected abstract void removeSensorEvent(Sensor sensor);

        protected static final int OPERATING_MODE_DATA_INJECTION = 1;
        protected static final int OPERATING_MODE_NORMAL = 0;
        private final SparseBooleanArray mActiveSensors = new SparseBooleanArray();
        private final CloseGuard mCloseGuard = CloseGuard.get();
        protected final SystemSensorManager mManager;
        protected final SparseIntArray mSensorAccuracies = new SparseIntArray();
        private long nSensorEventQueue;

        BaseEventQueue(Looper looper, SystemSensorManager systemsensormanager, int i, String s)
        {
            String s1 = s;
            if(s == null)
                s1 = "";
            nSensorEventQueue = nativeInitBaseEventQueue(SystemSensorManager._2D_get2(systemsensormanager), new WeakReference(this), looper.getQueue(), s1, i, SystemSensorManager._2D_get0(systemsensormanager).getOpPackageName());
            mCloseGuard.open("dispose");
            mManager = systemsensormanager;
        }
    }

    final class InjectEventQueue extends BaseEventQueue
    {

        protected void addSensorEvent(Sensor sensor)
        {
        }

        protected void dispatchFlushCompleteEvent(int i)
        {
        }

        protected void dispatchSensorEvent(int i, float af[], int j, long l)
        {
        }

        int injectSensorData(int i, float af[], int j, long l)
        {
            return injectSensorDataBase(i, af, j, l);
        }

        protected void removeSensorEvent(Sensor sensor)
        {
        }

        final SystemSensorManager this$0;

        public InjectEventQueue(Looper looper, SystemSensorManager systemsensormanager1, String s)
        {
            this$0 = SystemSensorManager.this;
            super(looper, systemsensormanager1, 1, s);
        }
    }

    static final class SensorEventQueue extends BaseEventQueue
    {

        public void addSensorEvent(Sensor sensor)
        {
            SensorEvent sensorevent = new SensorEvent(Sensor.getMaxLengthValuesArray(sensor, SystemSensorManager._2D_get3(mManager)));
            SparseArray sparsearray = mSensorsEvents;
            sparsearray;
            JVM INSTR monitorenter ;
            mSensorsEvents.put(sensor.getHandle(), sensorevent);
            sparsearray;
            JVM INSTR monitorexit ;
            return;
            sensor;
            throw sensor;
        }

        protected void dispatchAdditionalInfoEvent(int i, int j, int k, float af[], int ai[])
        {
            if(mListener instanceof SensorEventCallback)
            {
                Sensor sensor = (Sensor)SystemSensorManager._2D_get1(mManager).get(Integer.valueOf(i));
                if(sensor == null)
                    return;
                af = new SensorAdditionalInfo(sensor, j, k, ai, af);
                ((SensorEventCallback)mListener).onSensorAdditionalInfo(af);
            }
        }

        protected void dispatchFlushCompleteEvent(int i)
        {
            if(mListener instanceof SensorEventListener2)
            {
                Sensor sensor = (Sensor)SystemSensorManager._2D_get1(mManager).get(Integer.valueOf(i));
                if(sensor == null)
                    return;
                ((SensorEventListener2)mListener).onFlushCompleted(sensor);
            }
        }

        protected void dispatchSensorEvent(int i, float af[], int j, long l)
        {
            Sensor sensor;
            sensor = (Sensor)SystemSensorManager._2D_get1(mManager).get(Integer.valueOf(i));
            if(sensor == null)
                return;
            SparseArray sparsearray = mSensorsEvents;
            sparsearray;
            JVM INSTR monitorenter ;
            SensorEvent sensorevent = (SensorEvent)mSensorsEvents.get(i);
            sparsearray;
            JVM INSTR monitorexit ;
            if(sensorevent == null)
                return;
            break MISSING_BLOCK_LABEL_62;
            af;
            throw af;
            System.arraycopy(af, 0, sensorevent.values, 0, sensorevent.values.length);
            sensorevent.timestamp = l;
            sensorevent.accuracy = j;
            sensorevent.sensor = sensor;
            j = mSensorAccuracies.get(i);
            if(sensorevent.accuracy >= 0 && j != sensorevent.accuracy)
            {
                mSensorAccuracies.put(i, sensorevent.accuracy);
                mListener.onAccuracyChanged(sensorevent.sensor, sensorevent.accuracy);
            }
            mListener.onSensorChanged(sensorevent);
            return;
        }

        public void removeSensorEvent(Sensor sensor)
        {
            SparseArray sparsearray = mSensorsEvents;
            sparsearray;
            JVM INSTR monitorenter ;
            mSensorsEvents.delete(sensor.getHandle());
            sparsearray;
            JVM INSTR monitorexit ;
            return;
            sensor;
            throw sensor;
        }

        private final SensorEventListener mListener;
        private final SparseArray mSensorsEvents = new SparseArray();

        public SensorEventQueue(SensorEventListener sensoreventlistener, Looper looper, SystemSensorManager systemsensormanager, String s)
        {
            super(looper, systemsensormanager, 0, s);
            mListener = sensoreventlistener;
        }
    }

    static final class TriggerEventQueue extends BaseEventQueue
    {

        public void addSensorEvent(Sensor sensor)
        {
            TriggerEvent triggerevent = new TriggerEvent(Sensor.getMaxLengthValuesArray(sensor, SystemSensorManager._2D_get3(mManager)));
            SparseArray sparsearray = mTriggerEvents;
            sparsearray;
            JVM INSTR monitorenter ;
            mTriggerEvents.put(sensor.getHandle(), triggerevent);
            sparsearray;
            JVM INSTR monitorexit ;
            return;
            sensor;
            throw sensor;
        }

        protected void dispatchFlushCompleteEvent(int i)
        {
        }

        protected void dispatchSensorEvent(int i, float af[], int j, long l)
        {
            Sensor sensor;
            sensor = (Sensor)SystemSensorManager._2D_get1(mManager).get(Integer.valueOf(i));
            if(sensor == null)
                return;
            SparseArray sparsearray = mTriggerEvents;
            sparsearray;
            JVM INSTR monitorenter ;
            TriggerEvent triggerevent = (TriggerEvent)mTriggerEvents.get(i);
            sparsearray;
            JVM INSTR monitorexit ;
            if(triggerevent == null)
            {
                Log.e("SensorManager", (new StringBuilder()).append("Error: Trigger Event is null for Sensor: ").append(sensor).toString());
                return;
            } else
            {
                System.arraycopy(af, 0, triggerevent.values, 0, triggerevent.values.length);
                triggerevent.timestamp = l;
                triggerevent.sensor = sensor;
                mManager.cancelTriggerSensorImpl(mListener, sensor, false);
                mListener.onTrigger(triggerevent);
                return;
            }
            af;
            throw af;
        }

        public void removeSensorEvent(Sensor sensor)
        {
            SparseArray sparsearray = mTriggerEvents;
            sparsearray;
            JVM INSTR monitorenter ;
            mTriggerEvents.delete(sensor.getHandle());
            sparsearray;
            JVM INSTR monitorexit ;
            return;
            sensor;
            throw sensor;
        }

        private final TriggerEventListener mListener;
        private final SparseArray mTriggerEvents = new SparseArray();

        public TriggerEventQueue(TriggerEventListener triggereventlistener, Looper looper, SystemSensorManager systemsensormanager, String s)
        {
            super(looper, systemsensormanager, 0, s);
            mListener = triggereventlistener;
        }
    }


    static Context _2D_get0(SystemSensorManager systemsensormanager)
    {
        return systemsensormanager.mContext;
    }

    static HashMap _2D_get1(SystemSensorManager systemsensormanager)
    {
        return systemsensormanager.mHandleToSensor;
    }

    static long _2D_get2(SystemSensorManager systemsensormanager)
    {
        return systemsensormanager.mNativeInstance;
    }

    static int _2D_get3(SystemSensorManager systemsensormanager)
    {
        return systemsensormanager.mTargetSdkLevel;
    }

    static boolean _2D_set0(SystemSensorManager systemsensormanager, boolean flag)
    {
        systemsensormanager.mDynamicSensorListDirty = flag;
        return flag;
    }

    static void _2D_wrap0(SystemSensorManager systemsensormanager)
    {
        systemsensormanager.updateDynamicSensorList();
    }

    public SystemSensorManager(Context context, Looper looper)
    {
        mFullSensorsList = new ArrayList();
        mFullDynamicSensorsList = new ArrayList();
        mDynamicSensorListDirty = true;
        mHandleToSensor = new HashMap();
        mSensorListeners = new HashMap();
        mTriggerListeners = new HashMap();
        mDynamicSensorCallbacks = new HashMap();
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        if(!sNativeClassInited)
        {
            sNativeClassInited = true;
            nativeClassInit();
        }
        obj;
        JVM INSTR monitorexit ;
        int i;
        mMainLooper = looper;
        mTargetSdkLevel = context.getApplicationInfo().targetSdkVersion;
        mContext = context;
        mNativeInstance = nativeCreate(context.getOpPackageName());
        i = 0;
_L2:
        context = new Sensor();
        if(!nativeGetSensorAtIndex(mNativeInstance, context, i))
            return;
        break MISSING_BLOCK_LABEL_158;
        context;
        throw context;
        mFullSensorsList.add(context);
        mHandleToSensor.put(Integer.valueOf(context.getHandle()), context);
        i++;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void cleanupSensorConnection(Sensor sensor)
    {
        mHandleToSensor.remove(Integer.valueOf(sensor.getHandle()));
        if(sensor.getReportingMode() != 2) goto _L2; else goto _L1
_L1:
        HashMap hashmap = mTriggerListeners;
        hashmap;
        JVM INSTR monitorenter ;
        Iterator iterator;
        HashMap hashmap1 = JVM INSTR new #110 <Class HashMap>;
        hashmap1.HashMap(mTriggerListeners);
        iterator = hashmap1.keySet().iterator();
_L4:
        Object obj = hashmap;
        if(!iterator.hasNext())
            break; /* Loop/switch isn't completed */
        TriggerEventListener triggereventlistener = (TriggerEventListener)iterator.next();
        obj = JVM INSTR new #202 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Log.i("SensorManager", ((StringBuilder) (obj)).append("removed trigger listener").append(triggereventlistener.toString()).append(" due to sensor disconnection").toString());
        cancelTriggerSensorImpl(triggereventlistener, sensor, true);
        if(true) goto _L4; else goto _L3
        sensor;
        throw sensor;
_L3:
        obj;
        JVM INSTR monitorexit ;
        return;
_L2:
        hashmap = mSensorListeners;
        hashmap;
        JVM INSTR monitorenter ;
        obj = JVM INSTR new #110 <Class HashMap>;
        ((HashMap) (obj)).HashMap(mSensorListeners);
        iterator = ((HashMap) (obj)).keySet().iterator();
_L6:
        obj = hashmap;
        if(!iterator.hasNext())
            break; /* Loop/switch isn't completed */
        SensorEventListener sensoreventlistener = (SensorEventListener)iterator.next();
        StringBuilder stringbuilder = JVM INSTR new #202 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.i("SensorManager", stringbuilder.append("removed event listener").append(sensoreventlistener.toString()).append(" due to sensor disconnection").toString());
        unregisterListenerImpl(sensoreventlistener, sensor);
        if(true) goto _L6; else goto _L5
_L5:
        if(true) goto _L3; else goto _L7
_L7:
        sensor;
        throw sensor;
    }

    private static boolean diffSortedSensorList(List list, List list1, List list2, List list3, List list4)
    {
        boolean flag = false;
        int i = 0;
        do
        {
            int j;
            for(j = 0; j < list.size() && (i >= list1.size() || ((Sensor)list1.get(i)).getHandle() > ((Sensor)list.get(j)).getHandle()); j++)
            {
                flag = true;
                if(list4 != null)
                    list4.add((Sensor)list.get(j));
            }

            if(i < list1.size() && (j >= list.size() || ((Sensor)list1.get(i)).getHandle() < ((Sensor)list.get(j)).getHandle()))
            {
                flag = true;
                if(list3 != null)
                    list3.add((Sensor)list1.get(i));
                if(list2 != null)
                    list2.add((Sensor)list1.get(i));
                i++;
            } else
            if(i < list1.size() && j < list.size() && ((Sensor)list1.get(i)).getHandle() == ((Sensor)list.get(j)).getHandle())
            {
                if(list2 != null)
                    list2.add((Sensor)list.get(j));
                i++;
                j++;
            } else
            {
                return flag;
            }
        } while(true);
    }

    private static native void nativeClassInit();

    private static native int nativeConfigDirectChannel(long l, int i, int j, int k);

    private static native long nativeCreate(String s);

    private static native int nativeCreateDirectChannel(long l, long l1, int i, int j, HardwareBuffer hardwarebuffer);

    private static native void nativeDestroyDirectChannel(long l, int i);

    private static native void nativeGetDynamicSensors(long l, List list);

    private static native boolean nativeGetSensorAtIndex(long l, Sensor sensor, int i);

    private static native boolean nativeIsDataInjectionEnabled(long l);

    private static native int nativeSetOperationParameter(long l, int i, int j, float af[], int ai[]);

    private void setupDynamicSensorBroadcastReceiver()
    {
        if(mDynamicSensorBroadcastReceiver == null)
        {
            mDynamicSensorBroadcastReceiver = new BroadcastReceiver() {

                public void onReceive(Context context, Intent intent)
                {
                    if(intent.getAction() == "android.intent.action.DYNAMIC_SENSOR_CHANGED")
                    {
                        Log.i("SensorManager", "DYNS received DYNAMIC_SENSOR_CHANED broadcast");
                        SystemSensorManager._2D_set0(SystemSensorManager.this, true);
                        SystemSensorManager._2D_wrap0(SystemSensorManager.this);
                    }
                }

                final SystemSensorManager this$0;

            
            {
                this$0 = SystemSensorManager.this;
                super();
            }
            }
;
            IntentFilter intentfilter = new IntentFilter("dynamic_sensor_change");
            intentfilter.addAction("android.intent.action.DYNAMIC_SENSOR_CHANGED");
            mContext.registerReceiver(mDynamicSensorBroadcastReceiver, intentfilter);
        }
    }

    private void teardownDynamicSensorBroadcastReceiver()
    {
        mDynamicSensorCallbacks.clear();
        mContext.unregisterReceiver(mDynamicSensorBroadcastReceiver);
        mDynamicSensorBroadcastReceiver = null;
    }

    private void updateDynamicSensorList()
    {
        List list = mFullDynamicSensorsList;
        list;
        JVM INSTR monitorenter ;
        ArrayList arraylist2;
        ArrayList arraylist3;
        if(!mDynamicSensorListDirty)
            break MISSING_BLOCK_LABEL_289;
        ArrayList arraylist = JVM INSTR new #103 <Class ArrayList>;
        arraylist.ArrayList();
        nativeGetDynamicSensors(mNativeInstance, arraylist);
        ArrayList arraylist1 = JVM INSTR new #103 <Class ArrayList>;
        arraylist1.ArrayList();
        arraylist2 = JVM INSTR new #103 <Class ArrayList>;
        arraylist2.ArrayList();
        arraylist3 = JVM INSTR new #103 <Class ArrayList>;
        arraylist3.ArrayList();
        if(!diffSortedSensorList(mFullDynamicSensorsList, arraylist, arraylist1, arraylist2, arraylist3))
            break MISSING_BLOCK_LABEL_284;
        Log.i("SensorManager", "DYNS dynamic sensor list cached should be updated");
        mFullDynamicSensorsList = arraylist1;
        Sensor sensor;
        for(Iterator iterator = arraylist2.iterator(); iterator.hasNext(); mHandleToSensor.put(Integer.valueOf(sensor.getHandle()), sensor))
            sensor = (Sensor)iterator.next();

        break MISSING_BLOCK_LABEL_139;
        Exception exception;
        exception;
        throw exception;
        Handler handler;
        Iterator iterator2;
        handler = JVM INSTR new #299 <Class Handler>;
        handler.Handler(mContext.getMainLooper());
        iterator2 = mDynamicSensorCallbacks.entrySet().iterator();
_L1:
        Object obj;
        SensorManager.DynamicSensorCallback dynamicsensorcallback;
        if(!iterator2.hasNext())
            break MISSING_BLOCK_LABEL_251;
        obj = (java.util.Map.Entry)iterator2.next();
        dynamicsensorcallback = (SensorManager.DynamicSensorCallback)((java.util.Map.Entry) (obj)).getKey();
        if(((java.util.Map.Entry) (obj)).getValue() != null)
            break MISSING_BLOCK_LABEL_238;
        obj = handler;
_L2:
        Runnable runnable = JVM INSTR new #6   <Class SystemSensorManager$1>;
        runnable.this. _cls1();
        ((Handler) (obj)).post(runnable);
          goto _L1
        obj = (Handler)((java.util.Map.Entry) (obj)).getValue();
          goto _L2
        for(Iterator iterator1 = arraylist3.iterator(); iterator1.hasNext(); cleanupSensorConnection((Sensor)iterator1.next()));
        mDynamicSensorListDirty = false;
        list;
        JVM INSTR monitorexit ;
    }

    protected boolean cancelTriggerSensorImpl(TriggerEventListener triggereventlistener, Sensor sensor, boolean flag)
    {
        if(sensor != null && sensor.getReportingMode() != 2)
            return false;
        HashMap hashmap = mTriggerListeners;
        hashmap;
        JVM INSTR monitorenter ;
        TriggerEventQueue triggereventqueue = (TriggerEventQueue)mTriggerListeners.get(triggereventlistener);
        if(triggereventqueue == null) goto _L2; else goto _L1
_L1:
        if(sensor != null) goto _L4; else goto _L3
_L3:
        flag = triggereventqueue.removeAllSensors();
_L5:
        if(!flag)
            break MISSING_BLOCK_LABEL_79;
        if(triggereventqueue.hasSensors() ^ true)
        {
            mTriggerListeners.remove(triggereventlistener);
            triggereventqueue.dispose();
        }
        hashmap;
        JVM INSTR monitorexit ;
        return flag;
_L4:
        flag = triggereventqueue.removeSensor(sensor, flag);
        if(true) goto _L5; else goto _L2
_L2:
        hashmap;
        JVM INSTR monitorexit ;
        return false;
        triggereventlistener;
        throw triggereventlistener;
    }

    protected int configureDirectChannelImpl(SensorDirectChannel sensordirectchannel, Sensor sensor, int i)
    {
        boolean flag = false;
        if(!sensordirectchannel.isOpen())
            throw new IllegalStateException("channel is closed");
        if(i < 0 || i > 3)
            throw new IllegalArgumentException("rate parameter invalid");
        if(sensor == null && i != 0)
            throw new IllegalArgumentException("when sensor is null, rate can only be DIRECT_RATE_STOP");
        int j;
        if(sensor == null)
            j = -1;
        else
            j = sensor.getHandle();
        j = nativeConfigDirectChannel(mNativeInstance, sensordirectchannel.getNativeHandle(), j, i);
        if(i == 0)
        {
            i = ((flag) ? 1 : 0);
            if(j == 0)
                i = 1;
            return i;
        }
        if(j > 0)
            i = j;
        else
            i = 0;
        return i;
    }

    protected SensorDirectChannel createDirectChannelImpl(MemoryFile memoryfile, HardwareBuffer hardwarebuffer)
    {
        int i;
        long l;
        int j;
        if(memoryfile != null)
        {
            try
            {
                i = memoryfile.getFileDescriptor().getInt$();
            }
            // Misplaced declaration of an exception variable
            catch(MemoryFile memoryfile)
            {
                throw new IllegalArgumentException("MemoryFile object is not valid");
            }
            if(memoryfile.length() < 104)
                throw new IllegalArgumentException("Size of MemoryFile has to be greater than 104");
            l = memoryfile.length();
            j = nativeCreateDirectChannel(mNativeInstance, l, 1, i, null);
            if(j <= 0)
                throw new UncheckedIOException(new IOException((new StringBuilder()).append("create MemoryFile direct channel failed ").append(j).toString()));
            i = 1;
        } else
        if(hardwarebuffer != null)
        {
            if(hardwarebuffer.getFormat() != 33)
                throw new IllegalArgumentException("Format of HardwareBuffer must be BLOB");
            if(hardwarebuffer.getHeight() != 1)
                throw new IllegalArgumentException("Height of HardwareBuffer must be 1");
            if(hardwarebuffer.getWidth() < 104)
                throw new IllegalArgumentException("Width if HaradwareBuffer must be greater than 104");
            if((hardwarebuffer.getUsage() & 0x800000L) == 0L)
                throw new IllegalArgumentException("HardwareBuffer must set usage flag USAGE_SENSOR_DIRECT_DATA");
            l = hardwarebuffer.getWidth();
            j = nativeCreateDirectChannel(mNativeInstance, l, 2, -1, hardwarebuffer);
            if(j <= 0)
                throw new UncheckedIOException(new IOException((new StringBuilder()).append("create HardwareBuffer direct channel failed ").append(j).toString()));
            i = 2;
        } else
        {
            throw new NullPointerException("shared memory object cannot be null");
        }
        return new SensorDirectChannel(this, j, i, l);
    }

    protected void destroyDirectChannelImpl(SensorDirectChannel sensordirectchannel)
    {
        if(sensordirectchannel != null)
            nativeDestroyDirectChannel(mNativeInstance, sensordirectchannel.getNativeHandle());
    }

    protected boolean flushImpl(SensorEventListener sensoreventlistener)
    {
        boolean flag;
        flag = false;
        if(sensoreventlistener == null)
            throw new IllegalArgumentException("listener cannot be null");
        HashMap hashmap = mSensorListeners;
        hashmap;
        JVM INSTR monitorenter ;
        sensoreventlistener = (SensorEventQueue)mSensorListeners.get(sensoreventlistener);
        if(sensoreventlistener != null)
            break MISSING_BLOCK_LABEL_44;
        hashmap;
        JVM INSTR monitorexit ;
        return false;
        int i = sensoreventlistener.flush();
        if(i == 0)
            flag = true;
        hashmap;
        JVM INSTR monitorexit ;
        return flag;
        sensoreventlistener;
        throw sensoreventlistener;
    }

    protected List getFullDynamicSensorList()
    {
        setupDynamicSensorBroadcastReceiver();
        updateDynamicSensorList();
        return mFullDynamicSensorsList;
    }

    protected List getFullSensorList()
    {
        return mFullSensorsList;
    }

    protected boolean initDataInjectionImpl(boolean flag)
    {
        boolean flag1 = true;
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        if(!flag)
            break MISSING_BLOCK_LABEL_138;
        if(nativeIsDataInjectionEnabled(mNativeInstance))
            break MISSING_BLOCK_LABEL_35;
        Log.e("SensorManager", "Data Injection mode not enabled");
        obj;
        JVM INSTR monitorexit ;
        return false;
        InjectEventQueue injecteventqueue = sInjectEventQueue;
        if(injecteventqueue != null)
            break MISSING_BLOCK_LABEL_73;
        injecteventqueue = JVM INSTR new #13  <Class SystemSensorManager$InjectEventQueue>;
        injecteventqueue.this. InjectEventQueue(mMainLooper, this, mContext.getPackageName());
        sInjectEventQueue = injecteventqueue;
_L1:
        injecteventqueue = sInjectEventQueue;
        Exception exception;
        RuntimeException runtimeexception;
        if(injecteventqueue != null)
            flag = flag1;
        else
            flag = false;
        obj;
        JVM INSTR monitorexit ;
        return flag;
        runtimeexception;
        StringBuilder stringbuilder = JVM INSTR new #202 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.e("SensorManager", stringbuilder.append("Cannot create InjectEventQueue: ").append(runtimeexception).toString());
          goto _L1
        exception;
        throw exception;
        if(sInjectEventQueue != null)
        {
            sInjectEventQueue.dispose();
            sInjectEventQueue = null;
        }
        obj;
        JVM INSTR monitorexit ;
        return true;
    }

    protected boolean injectSensorDataImpl(Sensor sensor, float af[], int i, long l)
    {
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        if(sInjectEventQueue != null)
            break MISSING_BLOCK_LABEL_28;
        Log.e("SensorManager", "Data injection mode not activated before calling injectSensorData");
        obj;
        JVM INSTR monitorexit ;
        return false;
        i = sInjectEventQueue.injectSensorData(sensor.getHandle(), af, i, l);
        if(i == 0)
            break MISSING_BLOCK_LABEL_57;
        sInjectEventQueue.dispose();
        sInjectEventQueue = null;
        boolean flag;
        if(i == 0)
            flag = true;
        else
            flag = false;
        obj;
        JVM INSTR monitorexit ;
        return flag;
        sensor;
        throw sensor;
    }

    protected void registerDynamicSensorCallbackImpl(SensorManager.DynamicSensorCallback dynamicsensorcallback, Handler handler)
    {
        Log.i("SensorManager", "DYNS Register dynamic sensor callback");
        if(dynamicsensorcallback == null)
            throw new IllegalArgumentException("callback cannot be null");
        if(mDynamicSensorCallbacks.containsKey(dynamicsensorcallback))
        {
            return;
        } else
        {
            setupDynamicSensorBroadcastReceiver();
            mDynamicSensorCallbacks.put(dynamicsensorcallback, handler);
            return;
        }
    }

    protected boolean registerListenerImpl(SensorEventListener sensoreventlistener, Sensor sensor, int i, Handler handler, int j, int k)
    {
        SeempLog.record_sensor_rate(381, sensor, i);
        if(sensoreventlistener == null || sensor == null)
        {
            Log.e("SensorManager", "sensor or listener is null");
            return false;
        }
        if(sensor.getReportingMode() == 2)
        {
            Log.e("SensorManager", "Trigger Sensors should use the requestTriggerSensor.");
            return false;
        }
        if(j < 0 || i < 0)
        {
            Log.e("SensorManager", "maxBatchReportLatencyUs and delayUs should be non-negative");
            return false;
        }
        if(mSensorListeners.size() >= 128)
            throw new IllegalStateException("register failed, the sensor listeners size has exceeded the maximum limit 128");
        HashMap hashmap = mSensorListeners;
        hashmap;
        JVM INSTR monitorenter ;
        Object obj = (SensorEventQueue)mSensorListeners.get(sensoreventlistener);
        if(obj != null) goto _L2; else goto _L1
_L1:
        if(handler == null) goto _L4; else goto _L3
_L3:
        handler = handler.getLooper();
_L9:
        if(sensoreventlistener.getClass().getEnclosingClass() == null) goto _L6; else goto _L5
_L5:
        obj = sensoreventlistener.getClass().getEnclosingClass().getName();
_L10:
        SensorEventQueue sensoreventqueue;
        sensoreventqueue = JVM INSTR new #16  <Class SystemSensorManager$SensorEventQueue>;
        sensoreventqueue.SensorEventQueue(sensoreventlistener, handler, this, ((String) (obj)));
        if(sensoreventqueue.addSensor(sensor, i, j)) goto _L8; else goto _L7
_L7:
        sensoreventqueue.dispose();
        hashmap;
        JVM INSTR monitorexit ;
        return false;
_L4:
        handler = mMainLooper;
          goto _L9
_L6:
        obj = sensoreventlistener.getClass().getName();
          goto _L10
_L8:
        mSensorListeners.put(sensoreventlistener, sensoreventqueue);
        hashmap;
        JVM INSTR monitorexit ;
        return true;
_L2:
        boolean flag = ((SensorEventQueue) (obj)).addSensor(sensor, i, j);
        hashmap;
        JVM INSTR monitorexit ;
        return flag;
        sensoreventlistener;
        throw sensoreventlistener;
          goto _L9
    }

    protected boolean requestTriggerSensorImpl(TriggerEventListener triggereventlistener, Sensor sensor)
    {
        if(sensor == null)
            throw new IllegalArgumentException("sensor cannot be null");
        if(triggereventlistener == null)
            throw new IllegalArgumentException("listener cannot be null");
        if(sensor.getReportingMode() != 2)
            return false;
        if(mTriggerListeners.size() >= 128)
            throw new IllegalStateException("request failed, the trigger listeners size has exceeded the maximum limit 128");
        HashMap hashmap = mTriggerListeners;
        hashmap;
        JVM INSTR monitorenter ;
        Object obj = (TriggerEventQueue)mTriggerListeners.get(triggereventlistener);
        if(obj != null)
            break MISSING_BLOCK_LABEL_176;
        if(triggereventlistener.getClass().getEnclosingClass() == null)
            break MISSING_BLOCK_LABEL_149;
        obj = triggereventlistener.getClass().getEnclosingClass().getName();
_L1:
        TriggerEventQueue triggereventqueue;
        triggereventqueue = JVM INSTR new #19  <Class SystemSensorManager$TriggerEventQueue>;
        triggereventqueue.TriggerEventQueue(triggereventlistener, mMainLooper, this, ((String) (obj)));
        if(triggereventqueue.addSensor(sensor, 0, 0))
            break MISSING_BLOCK_LABEL_161;
        triggereventqueue.dispose();
        hashmap;
        JVM INSTR monitorexit ;
        return false;
        obj = triggereventlistener.getClass().getName();
          goto _L1
        mTriggerListeners.put(triggereventlistener, triggereventqueue);
        hashmap;
        JVM INSTR monitorexit ;
        return true;
        boolean flag = ((TriggerEventQueue) (obj)).addSensor(sensor, 0, 0);
        hashmap;
        JVM INSTR monitorexit ;
        return flag;
        triggereventlistener;
        throw triggereventlistener;
    }

    protected boolean setOperationParameterImpl(SensorAdditionalInfo sensoradditionalinfo)
    {
        int i = -1;
        if(sensoradditionalinfo.sensor != null)
            i = sensoradditionalinfo.sensor.getHandle();
        boolean flag;
        if(nativeSetOperationParameter(mNativeInstance, i, sensoradditionalinfo.type, sensoradditionalinfo.floatValues, sensoradditionalinfo.intValues) == 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    protected void unregisterDynamicSensorCallbackImpl(SensorManager.DynamicSensorCallback dynamicsensorcallback)
    {
        Log.i("SensorManager", "Removing dynamic sensor listerner");
        mDynamicSensorCallbacks.remove(dynamicsensorcallback);
    }

    protected void unregisterListenerImpl(SensorEventListener sensoreventlistener, Sensor sensor)
    {
        SeempLog.record_sensor(382, sensor);
        if(sensor != null && sensor.getReportingMode() == 2)
            return;
        HashMap hashmap = mSensorListeners;
        hashmap;
        JVM INSTR monitorenter ;
        SensorEventQueue sensoreventqueue = (SensorEventQueue)mSensorListeners.get(sensoreventlistener);
        if(sensoreventqueue == null) goto _L2; else goto _L1
_L1:
        if(sensor != null) goto _L4; else goto _L3
_L3:
        boolean flag = sensoreventqueue.removeAllSensors();
_L7:
        if(!flag) goto _L2; else goto _L5
_L5:
        if(sensoreventqueue.hasSensors() ^ true)
        {
            mSensorListeners.remove(sensoreventlistener);
            sensoreventqueue.dispose();
        }
_L2:
        hashmap;
        JVM INSTR monitorexit ;
        return;
_L4:
        flag = sensoreventqueue.removeSensor(sensor, true);
        if(true) goto _L7; else goto _L6
_L6:
        sensoreventlistener;
        throw sensoreventlistener;
    }

    private static final boolean DEBUG_DYNAMIC_SENSOR = true;
    private static final int MAX_LISTENER_COUNT = 128;
    private static final int MIN_DIRECT_CHANNEL_BUFFER_SIZE = 104;
    private static InjectEventQueue sInjectEventQueue = null;
    private static final Object sLock = new Object();
    private static boolean sNativeClassInited = false;
    private final Context mContext;
    private BroadcastReceiver mDynamicSensorBroadcastReceiver;
    private HashMap mDynamicSensorCallbacks;
    private boolean mDynamicSensorListDirty;
    private List mFullDynamicSensorsList;
    private final ArrayList mFullSensorsList;
    private final HashMap mHandleToSensor;
    private final Looper mMainLooper;
    private final long mNativeInstance;
    private final HashMap mSensorListeners;
    private final int mTargetSdkLevel;
    private final HashMap mTriggerListeners;


    // Unreferenced inner class android/hardware/SystemSensorManager$1

/* anonymous class */
    class _cls1
        implements Runnable
    {

        public void run()
        {
            Sensor sensor;
            for(Iterator iterator = addedList.iterator(); iterator.hasNext(); callback.onDynamicSensorConnected(sensor))
                sensor = (Sensor)iterator.next();

            Sensor sensor1;
            for(Iterator iterator1 = removedList.iterator(); iterator1.hasNext(); callback.onDynamicSensorDisconnected(sensor1))
                sensor1 = (Sensor)iterator1.next();

        }

        final SystemSensorManager this$0;
        final List val$addedList;
        final SensorManager.DynamicSensorCallback val$callback;
        final List val$removedList;

            
            {
                this$0 = SystemSensorManager.this;
                addedList = list;
                callback = dynamicsensorcallback;
                removedList = list1;
                super();
            }
    }

}
