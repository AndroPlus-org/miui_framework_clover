// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.location;

import android.content.Context;
import android.location.*;
import android.os.*;
import android.util.Log;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package android.hardware.location:
//            GeofenceHardwareRequestParcelable, GeofenceHardwareMonitorEvent, IGeofenceHardwareCallback, IGeofenceHardwareMonitorCallback

public final class GeofenceHardwareImpl
{
    private class GeofenceTransition
    {

        static int _2D_get0(GeofenceTransition geofencetransition)
        {
            return geofencetransition.mGeofenceId;
        }

        static Location _2D_get1(GeofenceTransition geofencetransition)
        {
            return geofencetransition.mLocation;
        }

        static int _2D_get2(GeofenceTransition geofencetransition)
        {
            return geofencetransition.mMonitoringType;
        }

        static long _2D_get3(GeofenceTransition geofencetransition)
        {
            return geofencetransition.mTimestamp;
        }

        static int _2D_get4(GeofenceTransition geofencetransition)
        {
            return geofencetransition.mTransition;
        }

        private int mGeofenceId;
        private Location mLocation;
        private int mMonitoringType;
        private int mSourcesUsed;
        private long mTimestamp;
        private int mTransition;
        final GeofenceHardwareImpl this$0;

        GeofenceTransition(int i, int j, long l, Location location, int k, 
                int i1)
        {
            this$0 = GeofenceHardwareImpl.this;
            super();
            mGeofenceId = i;
            mTransition = j;
            mTimestamp = l;
            mLocation = location;
            mMonitoringType = k;
            mSourcesUsed = i1;
        }
    }

    class Reaper
        implements android.os.IBinder.DeathRecipient
    {

        static IGeofenceHardwareCallback _2D_get0(Reaper reaper)
        {
            return reaper.mCallback;
        }

        static boolean _2D_wrap0(Reaper reaper)
        {
            return reaper.unlinkToDeath();
        }

        private boolean binderEquals(IInterface iinterface, IInterface iinterface1)
        {
            boolean flag1;
            boolean flag = true;
            flag1 = false;
            if(iinterface == null)
            {
                if(iinterface1 == null)
                    flag1 = flag;
                else
                    flag1 = false;
                return flag1;
            }
            break MISSING_BLOCK_LABEL_25;
            if(iinterface1 != null && iinterface.asBinder() == iinterface1.asBinder())
                flag1 = true;
            return flag1;
        }

        private boolean callbackEquals(IGeofenceHardwareCallback igeofencehardwarecallback)
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(mCallback != null)
            {
                flag1 = flag;
                if(mCallback.asBinder() == igeofencehardwarecallback.asBinder())
                    flag1 = true;
            }
            return flag1;
        }

        private boolean unlinkToDeath()
        {
            if(mMonitorCallback != null)
                return mMonitorCallback.asBinder().unlinkToDeath(this, 0);
            if(mCallback != null)
                return mCallback.asBinder().unlinkToDeath(this, 0);
            else
                return true;
        }

        public void binderDied()
        {
            if(mCallback == null) goto _L2; else goto _L1
_L1:
            Message message = GeofenceHardwareImpl._2D_get3(GeofenceHardwareImpl.this).obtainMessage(6, mCallback);
            message.arg1 = mMonitoringType;
            GeofenceHardwareImpl._2D_get3(GeofenceHardwareImpl.this).sendMessage(message);
_L4:
            Message message1 = GeofenceHardwareImpl._2D_get5(GeofenceHardwareImpl.this).obtainMessage(3, this);
            GeofenceHardwareImpl._2D_get5(GeofenceHardwareImpl.this).sendMessage(message1);
            return;
_L2:
            if(mMonitorCallback != null)
            {
                Message message2 = GeofenceHardwareImpl._2D_get2(GeofenceHardwareImpl.this).obtainMessage(4, mMonitorCallback);
                message2.arg1 = mMonitoringType;
                GeofenceHardwareImpl._2D_get2(GeofenceHardwareImpl.this).sendMessage(message2);
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        public boolean equals(Object obj)
        {
            boolean flag = true;
            if(obj == null)
                return false;
            if(obj == this)
                return true;
            obj = (Reaper)obj;
            if(binderEquals(((Reaper) (obj)).mCallback, mCallback) && binderEquals(((Reaper) (obj)).mMonitorCallback, mMonitorCallback))
            {
                if(((Reaper) (obj)).mMonitoringType != mMonitoringType)
                    flag = false;
            } else
            {
                flag = false;
            }
            return flag;
        }

        public int hashCode()
        {
            int i = 0;
            int j;
            if(mCallback != null)
                j = mCallback.asBinder().hashCode();
            else
                j = 0;
            if(mMonitorCallback != null)
                i = mMonitorCallback.asBinder().hashCode();
            return ((j + 527) * 31 + i) * 31 + mMonitoringType;
        }

        private IGeofenceHardwareCallback mCallback;
        private IGeofenceHardwareMonitorCallback mMonitorCallback;
        private int mMonitoringType;
        final GeofenceHardwareImpl this$0;

        Reaper(IGeofenceHardwareCallback igeofencehardwarecallback, int i)
        {
            this$0 = GeofenceHardwareImpl.this;
            super();
            mCallback = igeofencehardwarecallback;
            mMonitoringType = i;
        }

        Reaper(IGeofenceHardwareMonitorCallback igeofencehardwaremonitorcallback, int i)
        {
            this$0 = GeofenceHardwareImpl.this;
            super();
            mMonitorCallback = igeofencehardwaremonitorcallback;
            mMonitoringType = i;
        }
    }


    static boolean _2D_get0()
    {
        return DEBUG;
    }

    static ArrayList[] _2D_get1(GeofenceHardwareImpl geofencehardwareimpl)
    {
        return geofencehardwareimpl.mCallbacks;
    }

    static Handler _2D_get2(GeofenceHardwareImpl geofencehardwareimpl)
    {
        return geofencehardwareimpl.mCallbacksHandler;
    }

    static Handler _2D_get3(GeofenceHardwareImpl geofencehardwareimpl)
    {
        return geofencehardwareimpl.mGeofenceHandler;
    }

    static SparseArray _2D_get4(GeofenceHardwareImpl geofencehardwareimpl)
    {
        return geofencehardwareimpl.mGeofences;
    }

    static Handler _2D_get5(GeofenceHardwareImpl geofencehardwareimpl)
    {
        return geofencehardwareimpl.mReaperHandler;
    }

    static ArrayList _2D_get6(GeofenceHardwareImpl geofencehardwareimpl)
    {
        return geofencehardwareimpl.mReapers;
    }

    static void _2D_wrap0(GeofenceHardwareImpl geofencehardwareimpl)
    {
        geofencehardwareimpl.releaseWakeLock();
    }

    private GeofenceHardwareImpl(Context context)
    {
        mVersion = 1;
        mSupportedMonitorTypes = new int[2];
        mGeofenceHandler = new Handler() {

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR tableswitch 1 6: default 44
            //                           1 532
            //                           2 45
            //                           3 139
            //                           4 402
            //                           5 467
            //                           6 703;
                   goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
                return;
_L3:
                int i = message.arg1;
                Object obj = GeofenceHardwareImpl._2D_get4(GeofenceHardwareImpl.this);
                obj;
                JVM INSTR monitorenter ;
                Object obj1 = (IGeofenceHardwareCallback)GeofenceHardwareImpl._2D_get4(GeofenceHardwareImpl.this).get(i);
                obj;
                JVM INSTR monitorexit ;
                if(obj1 != null)
                    try
                    {
                        ((IGeofenceHardwareCallback) (obj1)).onGeofenceAdd(i, message.arg2);
                    }
                    // Misplaced declaration of an exception variable
                    catch(Message message)
                    {
                        Log.i("GeofenceHardwareImpl", (new StringBuilder()).append("Remote Exception:").append(message).toString());
                    }
                GeofenceHardwareImpl._2D_wrap0(GeofenceHardwareImpl.this);
                continue; /* Loop/switch isn't completed */
                message;
                throw message;
_L4:
                i = message.arg1;
                obj1 = GeofenceHardwareImpl._2D_get4(GeofenceHardwareImpl.this);
                obj1;
                JVM INSTR monitorenter ;
                obj = (IGeofenceHardwareCallback)GeofenceHardwareImpl._2D_get4(GeofenceHardwareImpl.this).get(i);
                obj1;
                JVM INSTR monitorexit ;
                if(obj == null) goto _L9; else goto _L8
_L8:
                boolean flag;
                Exception exception;
                int j;
                int k;
                GeofenceTransition geofencetransition;
                try
                {
                    ((IGeofenceHardwareCallback) (obj)).onGeofenceRemove(i, message.arg2);
                }
                // Misplaced declaration of an exception variable
                catch(Message message) { }
                message = ((IGeofenceHardwareCallback) (obj)).asBinder();
                j = 0;
                obj = GeofenceHardwareImpl._2D_get4(GeofenceHardwareImpl.this);
                obj;
                JVM INSTR monitorenter ;
                GeofenceHardwareImpl._2D_get4(GeofenceHardwareImpl.this).remove(i);
                k = 0;
_L14:
                flag = j;
                if(k >= GeofenceHardwareImpl._2D_get4(GeofenceHardwareImpl.this).size()) goto _L11; else goto _L10
_L10:
                obj1 = ((IGeofenceHardwareCallback)GeofenceHardwareImpl._2D_get4(GeofenceHardwareImpl.this).valueAt(k)).asBinder();
                if(obj1 != message) goto _L13; else goto _L12
_L12:
                flag = true;
_L11:
                obj;
                JVM INSTR monitorexit ;
                if(!flag)
                {
                    obj = GeofenceHardwareImpl._2D_get6(GeofenceHardwareImpl.this).iterator();
                    do
                    {
                        if(!((Iterator) (obj)).hasNext())
                            break;
                        obj1 = (Reaper)((Iterator) (obj)).next();
                        if(Reaper._2D_get0(((Reaper) (obj1))) != null && Reaper._2D_get0(((Reaper) (obj1))).asBinder() == message)
                        {
                            ((Iterator) (obj)).remove();
                            Reaper._2D_wrap0(((Reaper) (obj1)));
                            if(GeofenceHardwareImpl._2D_get0())
                                Log.d("GeofenceHardwareImpl", String.format("Removed reaper %s because binder %s is no longer needed.", new Object[] {
                                    obj1, message
                                }));
                        }
                    } while(true);
                }
                break; /* Loop/switch isn't completed */
                message;
                throw message;
_L13:
                k++;
                if(true) goto _L14; else goto _L9
                message;
                throw message;
_L9:
                GeofenceHardwareImpl._2D_wrap0(GeofenceHardwareImpl.this);
                continue; /* Loop/switch isn't completed */
_L5:
                flag = message.arg1;
                obj = GeofenceHardwareImpl._2D_get4(GeofenceHardwareImpl.this);
                obj;
                JVM INSTR monitorenter ;
                obj1 = (IGeofenceHardwareCallback)GeofenceHardwareImpl._2D_get4(GeofenceHardwareImpl.this).get(flag);
                obj;
                JVM INSTR monitorexit ;
                if(obj1 != null)
                    try
                    {
                        ((IGeofenceHardwareCallback) (obj1)).onGeofencePause(flag, message.arg2);
                    }
                    // Misplaced declaration of an exception variable
                    catch(Message message) { }
                GeofenceHardwareImpl._2D_wrap0(GeofenceHardwareImpl.this);
                continue; /* Loop/switch isn't completed */
                message;
                throw message;
_L6:
                flag = message.arg1;
                obj = GeofenceHardwareImpl._2D_get4(GeofenceHardwareImpl.this);
                obj;
                JVM INSTR monitorenter ;
                obj1 = (IGeofenceHardwareCallback)GeofenceHardwareImpl._2D_get4(GeofenceHardwareImpl.this).get(flag);
                obj;
                JVM INSTR monitorexit ;
                if(obj1 != null)
                    try
                    {
                        ((IGeofenceHardwareCallback) (obj1)).onGeofenceResume(flag, message.arg2);
                    }
                    // Misplaced declaration of an exception variable
                    catch(Message message) { }
                GeofenceHardwareImpl._2D_wrap0(GeofenceHardwareImpl.this);
                continue; /* Loop/switch isn't completed */
                message;
                throw message;
_L2:
                geofencetransition = (GeofenceTransition)message.obj;
                message = GeofenceHardwareImpl._2D_get4(GeofenceHardwareImpl.this);
                message;
                JVM INSTR monitorenter ;
                obj = (IGeofenceHardwareCallback)GeofenceHardwareImpl._2D_get4(GeofenceHardwareImpl.this).get(GeofenceTransition._2D_get0(geofencetransition));
                if(GeofenceHardwareImpl._2D_get0())
                {
                    obj1 = JVM INSTR new #55  <Class StringBuilder>;
                    ((StringBuilder) (obj1)).StringBuilder();
                    Log.d("GeofenceHardwareImpl", ((StringBuilder) (obj1)).append("GeofenceTransistionCallback: GPS : GeofenceId: ").append(GeofenceTransition._2D_get0(geofencetransition)).append(" Transition: ").append(GeofenceTransition._2D_get4(geofencetransition)).append(" Location: ").append(GeofenceTransition._2D_get1(geofencetransition)).append(":").append(GeofenceHardwareImpl._2D_get4(GeofenceHardwareImpl.this)).toString());
                }
                message;
                JVM INSTR monitorexit ;
                if(obj != null)
                    try
                    {
                        ((IGeofenceHardwareCallback) (obj)).onGeofenceTransition(GeofenceTransition._2D_get0(geofencetransition), GeofenceTransition._2D_get4(geofencetransition), GeofenceTransition._2D_get1(geofencetransition), GeofenceTransition._2D_get3(geofencetransition), GeofenceTransition._2D_get2(geofencetransition));
                    }
                    // Misplaced declaration of an exception variable
                    catch(Message message) { }
                GeofenceHardwareImpl._2D_wrap0(GeofenceHardwareImpl.this);
                continue; /* Loop/switch isn't completed */
                exception;
                throw exception;
_L7:
                exception = (IGeofenceHardwareCallback)message.obj;
                if(GeofenceHardwareImpl._2D_get0())
                    Log.d("GeofenceHardwareImpl", (new StringBuilder()).append("Geofence callback reaped:").append(exception).toString());
                k = message.arg1;
                message = GeofenceHardwareImpl._2D_get4(GeofenceHardwareImpl.this);
                message;
                JVM INSTR monitorenter ;
                flag = false;
_L16:
                if(flag >= GeofenceHardwareImpl._2D_get4(GeofenceHardwareImpl.this).size())
                    break; /* Loop/switch isn't completed */
                if(((IGeofenceHardwareCallback)GeofenceHardwareImpl._2D_get4(GeofenceHardwareImpl.this).valueAt(flag)).equals(exception))
                {
                    j = GeofenceHardwareImpl._2D_get4(GeofenceHardwareImpl.this).keyAt(flag);
                    removeGeofence(GeofenceHardwareImpl._2D_get4(GeofenceHardwareImpl.this).keyAt(flag), k);
                    GeofenceHardwareImpl._2D_get4(GeofenceHardwareImpl.this).remove(j);
                }
                flag++;
                if(true) goto _L16; else goto _L15
_L15:
                if(true) goto _L1; else goto _L17
_L17:
                exception;
                throw exception;
            }

            final GeofenceHardwareImpl this$0;

            
            {
                this$0 = GeofenceHardwareImpl.this;
                super();
            }
        }
;
        mCallbacksHandler = new Handler() {

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR tableswitch 1 4: default 36
            //                           1 37
            //                           2 152
            //                           3 219
            //                           4 257;
                   goto _L1 _L2 _L3 _L4 _L5
_L1:
                return;
_L2:
                message = (GeofenceHardwareMonitorEvent)message.obj;
                Object obj = GeofenceHardwareImpl._2D_get1(GeofenceHardwareImpl.this)[message.getMonitoringType()];
                if(obj != null)
                {
                    if(GeofenceHardwareImpl._2D_get0())
                        Log.d("GeofenceHardwareImpl", (new StringBuilder()).append("MonitoringSystemChangeCallback: ").append(message).toString());
                    for(obj = ((Iterable) (obj)).iterator(); ((Iterator) (obj)).hasNext();)
                    {
                        IGeofenceHardwareMonitorCallback igeofencehardwaremonitorcallback2 = (IGeofenceHardwareMonitorCallback)((Iterator) (obj)).next();
                        try
                        {
                            igeofencehardwaremonitorcallback2.onMonitoringSystemChange(message);
                        }
                        catch(RemoteException remoteexception)
                        {
                            Log.d("GeofenceHardwareImpl", "Error reporting onMonitoringSystemChange.", remoteexception);
                        }
                    }

                }
                GeofenceHardwareImpl._2D_wrap0(GeofenceHardwareImpl.this);
                continue; /* Loop/switch isn't completed */
_L3:
                int i = message.arg1;
                IGeofenceHardwareMonitorCallback igeofencehardwaremonitorcallback3 = (IGeofenceHardwareMonitorCallback)message.obj;
                ArrayList arraylist = GeofenceHardwareImpl._2D_get1(GeofenceHardwareImpl.this)[i];
                message = arraylist;
                if(arraylist == null)
                {
                    message = new ArrayList();
                    GeofenceHardwareImpl._2D_get1(GeofenceHardwareImpl.this)[i] = message;
                }
                if(!message.contains(igeofencehardwaremonitorcallback3))
                    message.add(igeofencehardwaremonitorcallback3);
                continue; /* Loop/switch isn't completed */
_L4:
                int j = message.arg1;
                IGeofenceHardwareMonitorCallback igeofencehardwaremonitorcallback = (IGeofenceHardwareMonitorCallback)message.obj;
                message = GeofenceHardwareImpl._2D_get1(GeofenceHardwareImpl.this)[j];
                if(message != null)
                    message.remove(igeofencehardwaremonitorcallback);
                continue; /* Loop/switch isn't completed */
_L5:
                IGeofenceHardwareMonitorCallback igeofencehardwaremonitorcallback1 = (IGeofenceHardwareMonitorCallback)message.obj;
                if(GeofenceHardwareImpl._2D_get0())
                    Log.d("GeofenceHardwareImpl", (new StringBuilder()).append("Monitor callback reaped:").append(igeofencehardwaremonitorcallback1).toString());
                message = GeofenceHardwareImpl._2D_get1(GeofenceHardwareImpl.this)[message.arg1];
                if(message != null && message.contains(igeofencehardwaremonitorcallback1))
                    message.remove(igeofencehardwaremonitorcallback1);
                if(true) goto _L1; else goto _L6
_L6:
            }

            final GeofenceHardwareImpl this$0;

            
            {
                this$0 = GeofenceHardwareImpl.this;
                super();
            }
        }
;
        mReaperHandler = new Handler() {

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR tableswitch 1 3: default 32
            //                           1 33
            //                           2 108
            //                           3 183;
                   goto _L1 _L2 _L3 _L4
_L1:
                return;
_L2:
                Object obj = (IGeofenceHardwareCallback)message.obj;
                int i = message.arg1;
                message = new Reaper(((IGeofenceHardwareCallback) (obj)), i);
                if(!GeofenceHardwareImpl._2D_get6(GeofenceHardwareImpl.this).contains(message))
                {
                    GeofenceHardwareImpl._2D_get6(GeofenceHardwareImpl.this).add(message);
                    obj = ((IGeofenceHardwareCallback) (obj)).asBinder();
                    try
                    {
                        ((IBinder) (obj)).linkToDeath(message, 0);
                    }
                    // Misplaced declaration of an exception variable
                    catch(Message message) { }
                }
                continue; /* Loop/switch isn't completed */
_L3:
                Object obj1 = (IGeofenceHardwareMonitorCallback)message.obj;
                int j = message.arg1;
                message = new Reaper(((IGeofenceHardwareMonitorCallback) (obj1)), j);
                if(!GeofenceHardwareImpl._2D_get6(GeofenceHardwareImpl.this).contains(message))
                {
                    GeofenceHardwareImpl._2D_get6(GeofenceHardwareImpl.this).add(message);
                    obj1 = ((IGeofenceHardwareMonitorCallback) (obj1)).asBinder();
                    try
                    {
                        ((IBinder) (obj1)).linkToDeath(message, 0);
                    }
                    // Misplaced declaration of an exception variable
                    catch(Message message) { }
                }
                continue; /* Loop/switch isn't completed */
_L4:
                message = (Reaper)message.obj;
                GeofenceHardwareImpl._2D_get6(GeofenceHardwareImpl.this).remove(message);
                if(true) goto _L1; else goto _L5
_L5:
            }

            final GeofenceHardwareImpl this$0;

            
            {
                this$0 = GeofenceHardwareImpl.this;
                super();
            }
        }
;
        mContext = context;
        setMonitorAvailability(0, 2);
        setMonitorAvailability(1, 2);
    }

    private void acquireWakeLock()
    {
        if(mWakeLock == null)
            mWakeLock = ((PowerManager)mContext.getSystemService("power")).newWakeLock(1, "GeofenceHardwareImpl");
        mWakeLock.acquire();
    }

    public static GeofenceHardwareImpl getInstance(Context context)
    {
        android/hardware/location/GeofenceHardwareImpl;
        JVM INSTR monitorenter ;
        if(sInstance == null)
        {
            GeofenceHardwareImpl geofencehardwareimpl = JVM INSTR new #2   <Class GeofenceHardwareImpl>;
            geofencehardwareimpl.GeofenceHardwareImpl(context);
            sInstance = geofencehardwareimpl;
        }
        context = sInstance;
        android/hardware/location/GeofenceHardwareImpl;
        JVM INSTR monitorexit ;
        return context;
        context;
        throw context;
    }

    private void releaseWakeLock()
    {
        if(mWakeLock.isHeld())
            mWakeLock.release();
    }

    private void reportGeofenceOperationStatus(int i, int j, int k)
    {
        acquireWakeLock();
        Message message = mGeofenceHandler.obtainMessage(i);
        message.arg1 = j;
        message.arg2 = k;
        message.sendToTarget();
    }

    private void setMonitorAvailability(int i, int j)
    {
        int ai[] = mSupportedMonitorTypes;
        ai;
        JVM INSTR monitorenter ;
        mSupportedMonitorTypes[i] = j;
        ai;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void updateFusedHardwareAvailability()
    {
        boolean flag;
        boolean flag1;
        if(mVersion >= 2)
        {
            if((mCapabilities & 1) != 0)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = true;
        }
        if(mFusedService == null) goto _L2; else goto _L1
_L1:
        flag1 = mFusedService.isSupported();
        if(!flag1)
            flag = false;
_L4:
        if(flag)
            setMonitorAvailability(1, 0);
        return;
_L2:
        flag = false;
        continue; /* Loop/switch isn't completed */
        RemoteException remoteexception;
        remoteexception;
        Log.e("GeofenceHardwareImpl", "RemoteException calling LocationManagerService");
        flag = false;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void updateGpsHardwareAvailability()
    {
        boolean flag;
        try
        {
            flag = mGpsService.isHardwareGeofenceSupported();
        }
        catch(RemoteException remoteexception)
        {
            Log.e("GeofenceHardwareImpl", "Remote Exception calling LocationManagerService");
            flag = false;
        }
        if(flag)
            setMonitorAvailability(0, 0);
    }

    public boolean addCircularFence(int i, GeofenceHardwareRequestParcelable geofencehardwarerequestparcelable, IGeofenceHardwareCallback igeofencehardwarecallback)
    {
        int j;
        j = geofencehardwarerequestparcelable.getId();
        if(DEBUG)
            Log.d("GeofenceHardwareImpl", String.format("addCircularFence: monitoringType=%d, %s", new Object[] {
                Integer.valueOf(i), geofencehardwarerequestparcelable
            }));
        SparseArray sparsearray = mGeofences;
        sparsearray;
        JVM INSTR monitorenter ;
        mGeofences.put(j, igeofencehardwarecallback);
        sparsearray;
        JVM INSTR monitorexit ;
        i;
        JVM INSTR tableswitch 0 1: default 84
    //                   0 158
    //                   1 229;
           goto _L1 _L2 _L3
_L1:
        boolean flag = false;
_L9:
        if(!flag) goto _L5; else goto _L4
_L4:
        geofencehardwarerequestparcelable = mReaperHandler.obtainMessage(1, igeofencehardwarecallback);
        geofencehardwarerequestparcelable.arg1 = i;
        mReaperHandler.sendMessage(geofencehardwarerequestparcelable);
_L7:
        if(DEBUG)
            Log.d("GeofenceHardwareImpl", (new StringBuilder()).append("addCircularFence: Result is: ").append(flag).toString());
        return flag;
        geofencehardwarerequestparcelable;
        throw geofencehardwarerequestparcelable;
_L2:
        if(mGpsService == null)
            return false;
        try
        {
            flag = mGpsService.addCircularHardwareGeofence(geofencehardwarerequestparcelable.getId(), geofencehardwarerequestparcelable.getLatitude(), geofencehardwarerequestparcelable.getLongitude(), geofencehardwarerequestparcelable.getRadius(), geofencehardwarerequestparcelable.getLastTransition(), geofencehardwarerequestparcelable.getMonitorTransitions(), geofencehardwarerequestparcelable.getNotificationResponsiveness(), geofencehardwarerequestparcelable.getUnknownTimer());
        }
        // Misplaced declaration of an exception variable
        catch(GeofenceHardwareRequestParcelable geofencehardwarerequestparcelable)
        {
            Log.e("GeofenceHardwareImpl", "AddGeofence: Remote Exception calling LocationManagerService");
            flag = false;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if(mFusedService == null)
            return false;
        try
        {
            mFusedService.addGeofences(new GeofenceHardwareRequestParcelable[] {
                geofencehardwarerequestparcelable
            });
        }
        // Misplaced declaration of an exception variable
        catch(GeofenceHardwareRequestParcelable geofencehardwarerequestparcelable)
        {
            Log.e("GeofenceHardwareImpl", "AddGeofence: RemoteException calling LocationManagerService");
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        flag = true;
        continue; /* Loop/switch isn't completed */
_L5:
        geofencehardwarerequestparcelable = mGeofences;
        geofencehardwarerequestparcelable;
        JVM INSTR monitorenter ;
        mGeofences.remove(j);
        geofencehardwarerequestparcelable;
        JVM INSTR monitorexit ;
        if(true) goto _L7; else goto _L6
_L6:
        igeofencehardwarecallback;
        throw igeofencehardwarecallback;
        if(true) goto _L9; else goto _L8
_L8:
    }

    int getAllowedResolutionLevel(int i, int j)
    {
        if(mContext.checkPermission("android.permission.ACCESS_FINE_LOCATION", i, j) == 0)
            return 3;
        return mContext.checkPermission("android.permission.ACCESS_COARSE_LOCATION", i, j) != 0 ? 1 : 2;
    }

    public int getCapabilitiesForMonitoringType(int i)
    {
        switch(mSupportedMonitorTypes[i])
        {
        case 0: // '\0'
            switch(i)
            {
            case 0: // '\0'
                return 1;

            case 1: // '\001'
                if(mVersion >= 2)
                    return mCapabilities;
                else
                    return 1;
            }
            break;
        }
        while(true) 
            return 0;
    }

    int getMonitoringResolutionLevel(int i)
    {
        switch(i)
        {
        default:
            return 1;

        case 0: // '\0'
            return 3;

        case 1: // '\001'
            return 3;
        }
    }

    public int[] getMonitoringTypes()
    {
        int ai[] = mSupportedMonitorTypes;
        ai;
        JVM INSTR monitorenter ;
        boolean flag;
        int i;
        boolean flag1;
        if(mSupportedMonitorTypes[0] != 2)
            flag = true;
        else
            flag = false;
        i = mSupportedMonitorTypes[1];
        if(i != 2)
            flag1 = true;
        else
            flag1 = false;
        ai;
        JVM INSTR monitorexit ;
        Exception exception;
        if(flag)
            if(flag1)
                return (new int[] {
                    0, 1
                });
            else
                return (new int[] {
                    0
                });
        break MISSING_BLOCK_LABEL_80;
        exception;
        throw exception;
        if(flag1)
            return (new int[] {
                1
            });
        else
            return new int[0];
    }

    public int getStatusOfMonitoringType(int i)
    {
        int ai[] = mSupportedMonitorTypes;
        ai;
        JVM INSTR monitorenter ;
        if(i < mSupportedMonitorTypes.length && i >= 0)
            break MISSING_BLOCK_LABEL_38;
        IllegalArgumentException illegalargumentexception = JVM INSTR new #338 <Class IllegalArgumentException>;
        illegalargumentexception.IllegalArgumentException("Unknown monitoring type");
        throw illegalargumentexception;
        Exception exception;
        exception;
        ai;
        JVM INSTR monitorexit ;
        throw exception;
        i = mSupportedMonitorTypes[i];
        ai;
        JVM INSTR monitorexit ;
        return i;
    }

    public void onCapabilities(int i)
    {
        mCapabilities = i;
        updateFusedHardwareAvailability();
    }

    public boolean pauseGeofence(int i, int j)
    {
label0:
        {
            if(DEBUG)
                Log.d("GeofenceHardwareImpl", (new StringBuilder()).append("Pause Geofence: GeofenceId: ").append(i).toString());
            synchronized(mGeofences)
            {
                if(mGeofences.get(i) == null)
                {
                    IllegalArgumentException illegalargumentexception = JVM INSTR new #338 <Class IllegalArgumentException>;
                    StringBuilder stringbuilder = JVM INSTR new #268 <Class StringBuilder>;
                    stringbuilder.StringBuilder();
                    illegalargumentexception.IllegalArgumentException(stringbuilder.append("Geofence ").append(i).append(" not registered.").toString());
                    throw illegalargumentexception;
                }
                break label0;
            }
        }
        j;
        JVM INSTR tableswitch 0 1: default 128
    //                   0 167
    //                   1 207;
           goto _L1 _L2 _L3
_L1:
        boolean flag = false;
_L5:
        if(DEBUG)
            Log.d("GeofenceHardwareImpl", (new StringBuilder()).append("pauseGeofence: Result is: ").append(flag).toString());
        return flag;
_L2:
        if(mGpsService == null)
            return false;
        try
        {
            flag = mGpsService.pauseHardwareGeofence(i);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("GeofenceHardwareImpl", "PauseGeofence: Remote Exception calling LocationManagerService");
            flag = false;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if(mFusedService == null)
            return false;
        mFusedService.pauseMonitoringGeofence(i);
        flag = true;
        continue; /* Loop/switch isn't completed */
        RemoteException remoteexception1;
        remoteexception1;
        Log.e("GeofenceHardwareImpl", "PauseGeofence: RemoteException calling LocationManagerService");
        flag = false;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public boolean registerForMonitorStateChangeCallback(int i, IGeofenceHardwareMonitorCallback igeofencehardwaremonitorcallback)
    {
        Message message = mReaperHandler.obtainMessage(2, igeofencehardwaremonitorcallback);
        message.arg1 = i;
        mReaperHandler.sendMessage(message);
        igeofencehardwaremonitorcallback = mCallbacksHandler.obtainMessage(2, igeofencehardwaremonitorcallback);
        igeofencehardwaremonitorcallback.arg1 = i;
        mCallbacksHandler.sendMessage(igeofencehardwaremonitorcallback);
        return true;
    }

    public boolean removeGeofence(int i, int j)
    {
label0:
        {
            if(DEBUG)
                Log.d("GeofenceHardwareImpl", (new StringBuilder()).append("Remove Geofence: GeofenceId: ").append(i).toString());
            synchronized(mGeofences)
            {
                if(mGeofences.get(i) == null)
                {
                    IllegalArgumentException illegalargumentexception = JVM INSTR new #338 <Class IllegalArgumentException>;
                    StringBuilder stringbuilder = JVM INSTR new #268 <Class StringBuilder>;
                    stringbuilder.StringBuilder();
                    illegalargumentexception.IllegalArgumentException(stringbuilder.append("Geofence ").append(i).append(" not registered.").toString());
                    throw illegalargumentexception;
                }
                break label0;
            }
        }
        j;
        JVM INSTR tableswitch 0 1: default 128
    //                   0 167
    //                   1 207;
           goto _L1 _L2 _L3
_L1:
        boolean flag = false;
_L5:
        if(DEBUG)
            Log.d("GeofenceHardwareImpl", (new StringBuilder()).append("removeGeofence: Result is: ").append(flag).toString());
        return flag;
_L2:
        if(mGpsService == null)
            return false;
        try
        {
            flag = mGpsService.removeHardwareGeofence(i);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("GeofenceHardwareImpl", "RemoveGeofence: Remote Exception calling LocationManagerService");
            flag = false;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if(mFusedService == null)
            return false;
        mFusedService.removeGeofences(new int[] {
            i
        });
        flag = true;
        continue; /* Loop/switch isn't completed */
        RemoteException remoteexception1;
        remoteexception1;
        Log.e("GeofenceHardwareImpl", "RemoveGeofence: RemoteException calling LocationManagerService");
        flag = false;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public void reportGeofenceAddStatus(int i, int j)
    {
        if(DEBUG)
            Log.d("GeofenceHardwareImpl", (new StringBuilder()).append("AddCallback| id:").append(i).append(", status:").append(j).toString());
        reportGeofenceOperationStatus(2, i, j);
    }

    public void reportGeofenceMonitorStatus(int i, int j, Location location, int k)
    {
        setMonitorAvailability(i, j);
        acquireWakeLock();
        location = new GeofenceHardwareMonitorEvent(i, j, k, location);
        mCallbacksHandler.obtainMessage(1, location).sendToTarget();
    }

    public void reportGeofencePauseStatus(int i, int j)
    {
        if(DEBUG)
            Log.d("GeofenceHardwareImpl", (new StringBuilder()).append("PauseCallbac| id:").append(i).append(", status").append(j).toString());
        reportGeofenceOperationStatus(4, i, j);
    }

    public void reportGeofenceRemoveStatus(int i, int j)
    {
        if(DEBUG)
            Log.d("GeofenceHardwareImpl", (new StringBuilder()).append("RemoveCallback| id:").append(i).append(", status:").append(j).toString());
        reportGeofenceOperationStatus(3, i, j);
    }

    public void reportGeofenceResumeStatus(int i, int j)
    {
        if(DEBUG)
            Log.d("GeofenceHardwareImpl", (new StringBuilder()).append("ResumeCallback| id:").append(i).append(", status:").append(j).toString());
        reportGeofenceOperationStatus(5, i, j);
    }

    public void reportGeofenceTransition(int i, Location location, int j, long l, int k, int i1)
    {
        if(location == null)
        {
            Log.e("GeofenceHardwareImpl", String.format("Invalid Geofence Transition: location=null", new Object[0]));
            return;
        }
        if(DEBUG)
            Log.d("GeofenceHardwareImpl", (new StringBuilder()).append("GeofenceTransition| ").append(location).append(", transition:").append(j).append(", transitionTimestamp:").append(l).append(", monitoringType:").append(k).append(", sourcesUsed:").append(i1).toString());
        location = new GeofenceTransition(i, j, l, location, k, i1);
        acquireWakeLock();
        mGeofenceHandler.obtainMessage(1, location).sendToTarget();
    }

    public boolean resumeGeofence(int i, int j, int k)
    {
label0:
        {
            if(DEBUG)
                Log.d("GeofenceHardwareImpl", (new StringBuilder()).append("Resume Geofence: GeofenceId: ").append(i).toString());
            synchronized(mGeofences)
            {
                if(mGeofences.get(i) == null)
                {
                    IllegalArgumentException illegalargumentexception = JVM INSTR new #338 <Class IllegalArgumentException>;
                    StringBuilder stringbuilder = JVM INSTR new #268 <Class StringBuilder>;
                    stringbuilder.StringBuilder();
                    illegalargumentexception.IllegalArgumentException(stringbuilder.append("Geofence ").append(i).append(" not registered.").toString());
                    throw illegalargumentexception;
                }
                break label0;
            }
        }
        j;
        JVM INSTR tableswitch 0 1: default 132
    //                   0 171
    //                   1 213;
           goto _L1 _L2 _L3
_L1:
        boolean flag = false;
_L5:
        if(DEBUG)
            Log.d("GeofenceHardwareImpl", (new StringBuilder()).append("resumeGeofence: Result is: ").append(flag).toString());
        return flag;
_L2:
        if(mGpsService == null)
            return false;
        try
        {
            flag = mGpsService.resumeHardwareGeofence(i, k);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("GeofenceHardwareImpl", "ResumeGeofence: Remote Exception calling LocationManagerService");
            flag = false;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if(mFusedService == null)
            return false;
        mFusedService.resumeMonitoringGeofence(i, k);
        flag = true;
        continue; /* Loop/switch isn't completed */
        RemoteException remoteexception1;
        remoteexception1;
        Log.e("GeofenceHardwareImpl", "ResumeGeofence: RemoteException calling LocationManagerService");
        flag = false;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public void setFusedGeofenceHardware(IFusedGeofenceHardware ifusedgeofencehardware)
    {
        if(mFusedService == null)
        {
            mFusedService = ifusedgeofencehardware;
            updateFusedHardwareAvailability();
        } else
        if(ifusedgeofencehardware == null)
        {
            mFusedService = null;
            Log.w("GeofenceHardwareImpl", "Fused Geofence Hardware service seems to have crashed");
        } else
        {
            Log.e("GeofenceHardwareImpl", "Error: FusedService being set again");
        }
    }

    public void setGpsHardwareGeofence(IGpsGeofenceHardware igpsgeofencehardware)
    {
        if(mGpsService == null)
        {
            mGpsService = igpsgeofencehardware;
            updateGpsHardwareAvailability();
        } else
        if(igpsgeofencehardware == null)
        {
            mGpsService = null;
            Log.w("GeofenceHardwareImpl", "GPS Geofence Hardware service seems to have crashed");
        } else
        {
            Log.e("GeofenceHardwareImpl", "Error: GpsService being set again.");
        }
    }

    public void setVersion(int i)
    {
        mVersion = i;
        updateFusedHardwareAvailability();
    }

    public boolean unregisterForMonitorStateChangeCallback(int i, IGeofenceHardwareMonitorCallback igeofencehardwaremonitorcallback)
    {
        igeofencehardwaremonitorcallback = mCallbacksHandler.obtainMessage(3, igeofencehardwaremonitorcallback);
        igeofencehardwaremonitorcallback.arg1 = i;
        mCallbacksHandler.sendMessage(igeofencehardwaremonitorcallback);
        return true;
    }

    private static final int ADD_GEOFENCE_CALLBACK = 2;
    private static final int CALLBACK_ADD = 2;
    private static final int CALLBACK_REMOVE = 3;
    private static final int CAPABILITY_GNSS = 1;
    private static final boolean DEBUG = Log.isLoggable("GeofenceHardwareImpl", 3);
    private static final int FIRST_VERSION_WITH_CAPABILITIES = 2;
    private static final int GEOFENCE_CALLBACK_BINDER_DIED = 6;
    private static final int GEOFENCE_STATUS = 1;
    private static final int GEOFENCE_TRANSITION_CALLBACK = 1;
    private static final int LOCATION_HAS_ACCURACY = 16;
    private static final int LOCATION_HAS_ALTITUDE = 2;
    private static final int LOCATION_HAS_BEARING = 8;
    private static final int LOCATION_HAS_LAT_LONG = 1;
    private static final int LOCATION_HAS_SPEED = 4;
    private static final int LOCATION_INVALID = 0;
    private static final int MONITOR_CALLBACK_BINDER_DIED = 4;
    private static final int PAUSE_GEOFENCE_CALLBACK = 4;
    private static final int REAPER_GEOFENCE_ADDED = 1;
    private static final int REAPER_MONITOR_CALLBACK_ADDED = 2;
    private static final int REAPER_REMOVED = 3;
    private static final int REMOVE_GEOFENCE_CALLBACK = 3;
    private static final int RESOLUTION_LEVEL_COARSE = 2;
    private static final int RESOLUTION_LEVEL_FINE = 3;
    private static final int RESOLUTION_LEVEL_NONE = 1;
    private static final int RESUME_GEOFENCE_CALLBACK = 5;
    private static final String TAG = "GeofenceHardwareImpl";
    private static GeofenceHardwareImpl sInstance;
    private final ArrayList mCallbacks[] = new ArrayList[2];
    private Handler mCallbacksHandler;
    private int mCapabilities;
    private final Context mContext;
    private IFusedGeofenceHardware mFusedService;
    private Handler mGeofenceHandler;
    private final SparseArray mGeofences = new SparseArray();
    private IGpsGeofenceHardware mGpsService;
    private Handler mReaperHandler;
    private final ArrayList mReapers = new ArrayList();
    private int mSupportedMonitorTypes[];
    private int mVersion;
    private android.os.PowerManager.WakeLock mWakeLock;

}
