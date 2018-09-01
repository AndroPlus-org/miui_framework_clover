// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.location;

import android.location.Location;
import android.os.RemoteException;
import java.lang.ref.WeakReference;
import java.util.HashMap;

// Referenced classes of package android.hardware.location:
//            GeofenceHardwareRequest, GeofenceHardwareRequestParcelable, IGeofenceHardware, GeofenceHardwareCallback, 
//            GeofenceHardwareMonitorCallback, GeofenceHardwareMonitorEvent

public final class GeofenceHardware
{
    class GeofenceHardwareCallbackWrapper extends IGeofenceHardwareCallback.Stub
    {

        public void onGeofenceAdd(int i, int j)
        {
            GeofenceHardwareCallback geofencehardwarecallback = (GeofenceHardwareCallback)mCallback.get();
            if(geofencehardwarecallback != null)
                geofencehardwarecallback.onGeofenceAdd(i, j);
        }

        public void onGeofencePause(int i, int j)
        {
            GeofenceHardwareCallback geofencehardwarecallback = (GeofenceHardwareCallback)mCallback.get();
            if(geofencehardwarecallback != null)
                geofencehardwarecallback.onGeofencePause(i, j);
        }

        public void onGeofenceRemove(int i, int j)
        {
            GeofenceHardwareCallback geofencehardwarecallback = (GeofenceHardwareCallback)mCallback.get();
            if(geofencehardwarecallback != null)
            {
                geofencehardwarecallback.onGeofenceRemove(i, j);
                GeofenceHardware._2D_wrap0(GeofenceHardware.this, geofencehardwarecallback);
            }
        }

        public void onGeofenceResume(int i, int j)
        {
            GeofenceHardwareCallback geofencehardwarecallback = (GeofenceHardwareCallback)mCallback.get();
            if(geofencehardwarecallback != null)
                geofencehardwarecallback.onGeofenceResume(i, j);
        }

        public void onGeofenceTransition(int i, int j, Location location, long l, int k)
        {
            GeofenceHardwareCallback geofencehardwarecallback = (GeofenceHardwareCallback)mCallback.get();
            if(geofencehardwarecallback != null)
                geofencehardwarecallback.onGeofenceTransition(i, j, location, l, k);
        }

        private WeakReference mCallback;
        final GeofenceHardware this$0;

        GeofenceHardwareCallbackWrapper(GeofenceHardwareCallback geofencehardwarecallback)
        {
            this$0 = GeofenceHardware.this;
            super();
            mCallback = new WeakReference(geofencehardwarecallback);
        }
    }

    class GeofenceHardwareMonitorCallbackWrapper extends IGeofenceHardwareMonitorCallback.Stub
    {

        public void onMonitoringSystemChange(GeofenceHardwareMonitorEvent geofencehardwaremonitorevent)
        {
            boolean flag = false;
            GeofenceHardwareMonitorCallback geofencehardwaremonitorcallback = (GeofenceHardwareMonitorCallback)mCallback.get();
            if(geofencehardwaremonitorcallback == null)
                return;
            int i = geofencehardwaremonitorevent.getMonitoringType();
            if(geofencehardwaremonitorevent.getMonitoringStatus() == 0)
                flag = true;
            geofencehardwaremonitorcallback.onMonitoringSystemChange(i, flag, geofencehardwaremonitorevent.getLocation());
            if(android.os.Build.VERSION.SDK_INT >= 21)
                geofencehardwaremonitorcallback.onMonitoringSystemChange(geofencehardwaremonitorevent);
        }

        private WeakReference mCallback;
        final GeofenceHardware this$0;

        GeofenceHardwareMonitorCallbackWrapper(GeofenceHardwareMonitorCallback geofencehardwaremonitorcallback)
        {
            this$0 = GeofenceHardware.this;
            super();
            mCallback = new WeakReference(geofencehardwaremonitorcallback);
        }
    }


    static void _2D_wrap0(GeofenceHardware geofencehardware, GeofenceHardwareCallback geofencehardwarecallback)
    {
        geofencehardware.removeCallback(geofencehardwarecallback);
    }

    public GeofenceHardware(IGeofenceHardware igeofencehardware)
    {
        mCallbacks = new HashMap();
        mMonitorCallbacks = new HashMap();
        mService = igeofencehardware;
    }

    private GeofenceHardwareCallbackWrapper getCallbackWrapper(GeofenceHardwareCallback geofencehardwarecallback)
    {
        HashMap hashmap = mCallbacks;
        hashmap;
        JVM INSTR monitorenter ;
        GeofenceHardwareCallbackWrapper geofencehardwarecallbackwrapper = (GeofenceHardwareCallbackWrapper)mCallbacks.get(geofencehardwarecallback);
        GeofenceHardwareCallbackWrapper geofencehardwarecallbackwrapper1;
        geofencehardwarecallbackwrapper1 = geofencehardwarecallbackwrapper;
        if(geofencehardwarecallbackwrapper != null)
            break MISSING_BLOCK_LABEL_49;
        geofencehardwarecallbackwrapper1 = JVM INSTR new #6   <Class GeofenceHardware$GeofenceHardwareCallbackWrapper>;
        geofencehardwarecallbackwrapper1.this. GeofenceHardwareCallbackWrapper(geofencehardwarecallback);
        mCallbacks.put(geofencehardwarecallback, geofencehardwarecallbackwrapper1);
        hashmap;
        JVM INSTR monitorexit ;
        return geofencehardwarecallbackwrapper1;
        geofencehardwarecallback;
        throw geofencehardwarecallback;
    }

    private GeofenceHardwareMonitorCallbackWrapper getMonitorCallbackWrapper(GeofenceHardwareMonitorCallback geofencehardwaremonitorcallback)
    {
        HashMap hashmap = mMonitorCallbacks;
        hashmap;
        JVM INSTR monitorenter ;
        GeofenceHardwareMonitorCallbackWrapper geofencehardwaremonitorcallbackwrapper = (GeofenceHardwareMonitorCallbackWrapper)mMonitorCallbacks.get(geofencehardwaremonitorcallback);
        GeofenceHardwareMonitorCallbackWrapper geofencehardwaremonitorcallbackwrapper1;
        geofencehardwaremonitorcallbackwrapper1 = geofencehardwaremonitorcallbackwrapper;
        if(geofencehardwaremonitorcallbackwrapper != null)
            break MISSING_BLOCK_LABEL_49;
        geofencehardwaremonitorcallbackwrapper1 = JVM INSTR new #9   <Class GeofenceHardware$GeofenceHardwareMonitorCallbackWrapper>;
        geofencehardwaremonitorcallbackwrapper1.this. GeofenceHardwareMonitorCallbackWrapper(geofencehardwaremonitorcallback);
        mMonitorCallbacks.put(geofencehardwaremonitorcallback, geofencehardwaremonitorcallbackwrapper1);
        hashmap;
        JVM INSTR monitorexit ;
        return geofencehardwaremonitorcallbackwrapper1;
        geofencehardwaremonitorcallback;
        throw geofencehardwaremonitorcallback;
    }

    private void removeCallback(GeofenceHardwareCallback geofencehardwarecallback)
    {
        HashMap hashmap = mCallbacks;
        hashmap;
        JVM INSTR monitorenter ;
        mCallbacks.remove(geofencehardwarecallback);
        hashmap;
        JVM INSTR monitorexit ;
        return;
        geofencehardwarecallback;
        throw geofencehardwarecallback;
    }

    private void removeMonitorCallback(GeofenceHardwareMonitorCallback geofencehardwaremonitorcallback)
    {
        HashMap hashmap = mMonitorCallbacks;
        hashmap;
        JVM INSTR monitorenter ;
        mMonitorCallbacks.remove(geofencehardwaremonitorcallback);
        hashmap;
        JVM INSTR monitorexit ;
        return;
        geofencehardwaremonitorcallback;
        throw geofencehardwaremonitorcallback;
    }

    public boolean addGeofence(int i, int j, GeofenceHardwareRequest geofencehardwarerequest, GeofenceHardwareCallback geofencehardwarecallback)
    {
        try
        {
            if(geofencehardwarerequest.getType() == 0)
            {
                IGeofenceHardware igeofencehardware = mService;
                GeofenceHardwareRequestParcelable geofencehardwarerequestparcelable = JVM INSTR new #103 <Class GeofenceHardwareRequestParcelable>;
                geofencehardwarerequestparcelable.GeofenceHardwareRequestParcelable(i, geofencehardwarerequest);
                return igeofencehardware.addCircularFence(j, geofencehardwarerequestparcelable, getCallbackWrapper(geofencehardwarecallback));
            } else
            {
                geofencehardwarerequest = JVM INSTR new #116 <Class IllegalArgumentException>;
                geofencehardwarerequest.IllegalArgumentException("Geofence Request type not supported");
                throw geofencehardwarerequest;
            }
        }
        // Misplaced declaration of an exception variable
        catch(GeofenceHardwareRequest geofencehardwarerequest)
        {
            return false;
        }
    }

    public int[] getMonitoringTypes()
    {
        int ai[];
        try
        {
            ai = mService.getMonitoringTypes();
        }
        catch(RemoteException remoteexception)
        {
            return new int[0];
        }
        return ai;
    }

    public int getStatusOfMonitoringType(int i)
    {
        try
        {
            i = mService.getStatusOfMonitoringType(i);
        }
        catch(RemoteException remoteexception)
        {
            return 2;
        }
        return i;
    }

    public boolean pauseGeofence(int i, int j)
    {
        boolean flag;
        try
        {
            flag = mService.pauseGeofence(i, j);
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return flag;
    }

    public boolean registerForMonitorStateChangeCallback(int i, GeofenceHardwareMonitorCallback geofencehardwaremonitorcallback)
    {
        boolean flag;
        try
        {
            flag = mService.registerForMonitorStateChangeCallback(i, getMonitorCallbackWrapper(geofencehardwaremonitorcallback));
        }
        // Misplaced declaration of an exception variable
        catch(GeofenceHardwareMonitorCallback geofencehardwaremonitorcallback)
        {
            return false;
        }
        return flag;
    }

    public boolean removeGeofence(int i, int j)
    {
        boolean flag;
        try
        {
            flag = mService.removeGeofence(i, j);
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return flag;
    }

    public boolean resumeGeofence(int i, int j, int k)
    {
        boolean flag;
        try
        {
            flag = mService.resumeGeofence(i, j, k);
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return flag;
    }

    public boolean unregisterForMonitorStateChangeCallback(int i, GeofenceHardwareMonitorCallback geofencehardwaremonitorcallback)
    {
        boolean flag = false;
        boolean flag1 = mService.unregisterForMonitorStateChangeCallback(i, getMonitorCallbackWrapper(geofencehardwaremonitorcallback));
        flag = flag1;
        if(!flag1)
            break MISSING_BLOCK_LABEL_38;
        flag = flag1;
        removeMonitorCallback(geofencehardwaremonitorcallback);
        flag = flag1;
_L2:
        return flag;
        geofencehardwaremonitorcallback;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static final int GEOFENCE_ENTERED = 1;
    public static final int GEOFENCE_ERROR_ID_EXISTS = 2;
    public static final int GEOFENCE_ERROR_ID_UNKNOWN = 3;
    public static final int GEOFENCE_ERROR_INSUFFICIENT_MEMORY = 6;
    public static final int GEOFENCE_ERROR_INVALID_TRANSITION = 4;
    public static final int GEOFENCE_ERROR_TOO_MANY_GEOFENCES = 1;
    public static final int GEOFENCE_EXITED = 2;
    public static final int GEOFENCE_FAILURE = 5;
    public static final int GEOFENCE_SUCCESS = 0;
    public static final int GEOFENCE_UNCERTAIN = 4;
    public static final int MONITORING_TYPE_FUSED_HARDWARE = 1;
    public static final int MONITORING_TYPE_GPS_HARDWARE = 0;
    public static final int MONITOR_CURRENTLY_AVAILABLE = 0;
    public static final int MONITOR_CURRENTLY_UNAVAILABLE = 1;
    public static final int MONITOR_UNSUPPORTED = 2;
    static final int NUM_MONITORS = 2;
    public static final int SOURCE_TECHNOLOGY_BLUETOOTH = 16;
    public static final int SOURCE_TECHNOLOGY_CELL = 8;
    public static final int SOURCE_TECHNOLOGY_GNSS = 1;
    public static final int SOURCE_TECHNOLOGY_SENSORS = 4;
    public static final int SOURCE_TECHNOLOGY_WIFI = 2;
    private HashMap mCallbacks;
    private HashMap mMonitorCallbacks;
    private IGeofenceHardware mService;
}
