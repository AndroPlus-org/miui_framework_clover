// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.location;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.IFusedGeofenceHardware;
import android.location.IGpsGeofenceHardware;
import android.os.Binder;
import android.os.IBinder;

// Referenced classes of package android.hardware.location:
//            GeofenceHardwareImpl, GeofenceHardwareRequestParcelable, IGeofenceHardwareCallback, IGeofenceHardwareMonitorCallback

public class GeofenceHardwareService extends Service
{

    static Context _2D_get0(GeofenceHardwareService geofencehardwareservice)
    {
        return geofencehardwareservice.mContext;
    }

    static GeofenceHardwareImpl _2D_get1(GeofenceHardwareService geofencehardwareservice)
    {
        return geofencehardwareservice.mGeofenceHardwareImpl;
    }

    static void _2D_wrap0(GeofenceHardwareService geofencehardwareservice, int i, int j, int k)
    {
        geofencehardwareservice.checkPermission(i, j, k);
    }

    public GeofenceHardwareService()
    {
        mBinder = new IGeofenceHardware.Stub() {

            public boolean addCircularFence(int i, GeofenceHardwareRequestParcelable geofencehardwarerequestparcelable, IGeofenceHardwareCallback igeofencehardwarecallback)
            {
                GeofenceHardwareService._2D_get0(GeofenceHardwareService.this).enforceCallingPermission("android.permission.LOCATION_HARDWARE", "Location Hardware permission not granted to access hardware geofence");
                GeofenceHardwareService._2D_wrap0(GeofenceHardwareService.this, Binder.getCallingPid(), Binder.getCallingUid(), i);
                return GeofenceHardwareService._2D_get1(GeofenceHardwareService.this).addCircularFence(i, geofencehardwarerequestparcelable, igeofencehardwarecallback);
            }

            public int[] getMonitoringTypes()
            {
                GeofenceHardwareService._2D_get0(GeofenceHardwareService.this).enforceCallingPermission("android.permission.LOCATION_HARDWARE", "Location Hardware permission not granted to access hardware geofence");
                return GeofenceHardwareService._2D_get1(GeofenceHardwareService.this).getMonitoringTypes();
            }

            public int getStatusOfMonitoringType(int i)
            {
                GeofenceHardwareService._2D_get0(GeofenceHardwareService.this).enforceCallingPermission("android.permission.LOCATION_HARDWARE", "Location Hardware permission not granted to access hardware geofence");
                return GeofenceHardwareService._2D_get1(GeofenceHardwareService.this).getStatusOfMonitoringType(i);
            }

            public boolean pauseGeofence(int i, int j)
            {
                GeofenceHardwareService._2D_get0(GeofenceHardwareService.this).enforceCallingPermission("android.permission.LOCATION_HARDWARE", "Location Hardware permission not granted to access hardware geofence");
                GeofenceHardwareService._2D_wrap0(GeofenceHardwareService.this, Binder.getCallingPid(), Binder.getCallingUid(), j);
                return GeofenceHardwareService._2D_get1(GeofenceHardwareService.this).pauseGeofence(i, j);
            }

            public boolean registerForMonitorStateChangeCallback(int i, IGeofenceHardwareMonitorCallback igeofencehardwaremonitorcallback)
            {
                GeofenceHardwareService._2D_get0(GeofenceHardwareService.this).enforceCallingPermission("android.permission.LOCATION_HARDWARE", "Location Hardware permission not granted to access hardware geofence");
                GeofenceHardwareService._2D_wrap0(GeofenceHardwareService.this, Binder.getCallingPid(), Binder.getCallingUid(), i);
                return GeofenceHardwareService._2D_get1(GeofenceHardwareService.this).registerForMonitorStateChangeCallback(i, igeofencehardwaremonitorcallback);
            }

            public boolean removeGeofence(int i, int j)
            {
                GeofenceHardwareService._2D_get0(GeofenceHardwareService.this).enforceCallingPermission("android.permission.LOCATION_HARDWARE", "Location Hardware permission not granted to access hardware geofence");
                GeofenceHardwareService._2D_wrap0(GeofenceHardwareService.this, Binder.getCallingPid(), Binder.getCallingUid(), j);
                return GeofenceHardwareService._2D_get1(GeofenceHardwareService.this).removeGeofence(i, j);
            }

            public boolean resumeGeofence(int i, int j, int k)
            {
                GeofenceHardwareService._2D_get0(GeofenceHardwareService.this).enforceCallingPermission("android.permission.LOCATION_HARDWARE", "Location Hardware permission not granted to access hardware geofence");
                GeofenceHardwareService._2D_wrap0(GeofenceHardwareService.this, Binder.getCallingPid(), Binder.getCallingUid(), j);
                return GeofenceHardwareService._2D_get1(GeofenceHardwareService.this).resumeGeofence(i, j, k);
            }

            public void setFusedGeofenceHardware(IFusedGeofenceHardware ifusedgeofencehardware)
            {
                GeofenceHardwareService._2D_get1(GeofenceHardwareService.this).setFusedGeofenceHardware(ifusedgeofencehardware);
            }

            public void setGpsGeofenceHardware(IGpsGeofenceHardware igpsgeofencehardware)
            {
                GeofenceHardwareService._2D_get1(GeofenceHardwareService.this).setGpsHardwareGeofence(igpsgeofencehardware);
            }

            public boolean unregisterForMonitorStateChangeCallback(int i, IGeofenceHardwareMonitorCallback igeofencehardwaremonitorcallback)
            {
                GeofenceHardwareService._2D_get0(GeofenceHardwareService.this).enforceCallingPermission("android.permission.LOCATION_HARDWARE", "Location Hardware permission not granted to access hardware geofence");
                GeofenceHardwareService._2D_wrap0(GeofenceHardwareService.this, Binder.getCallingPid(), Binder.getCallingUid(), i);
                return GeofenceHardwareService._2D_get1(GeofenceHardwareService.this).unregisterForMonitorStateChangeCallback(i, igeofencehardwaremonitorcallback);
            }

            final GeofenceHardwareService this$0;

            
            {
                this$0 = GeofenceHardwareService.this;
                super();
            }
        }
;
    }

    private void checkPermission(int i, int j, int k)
    {
        if(mGeofenceHardwareImpl.getAllowedResolutionLevel(i, j) < mGeofenceHardwareImpl.getMonitoringResolutionLevel(k))
            throw new SecurityException((new StringBuilder()).append("Insufficient permissions to access hardware geofence for type: ").append(k).toString());
        else
            return;
    }

    public IBinder onBind(Intent intent)
    {
        return mBinder;
    }

    public void onCreate()
    {
        mContext = this;
        mGeofenceHardwareImpl = GeofenceHardwareImpl.getInstance(mContext);
    }

    public void onDestroy()
    {
        mGeofenceHardwareImpl = null;
    }

    public boolean onUnbind(Intent intent)
    {
        return false;
    }

    private IBinder mBinder;
    private Context mContext;
    private GeofenceHardwareImpl mGeofenceHardwareImpl;
}
