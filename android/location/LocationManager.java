// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.*;
import android.util.Log;
import android.util.SeempLog;
import com.android.internal.location.ProviderProperties;
import java.util.*;

// Referenced classes of package android.location:
//            GnssMeasurementCallbackTransport, GnssNavigationMessageCallbackTransport, BatchedLocationCallbackTransport, LocationProvider, 
//            ILocationManager, Geofence, LocationRequest, GpsStatus, 
//            Location, GnssStatus, Criteria, LocationListener, 
//            OnNmeaMessageListener, BatchedLocationCallback

public class LocationManager
{
    private class GnssStatusListenerTransport extends IGnssStatusListener.Stub
    {

        static GnssStatus.Callback _2D_get0(GnssStatusListenerTransport gnssstatuslistenertransport)
        {
            return gnssstatuslistenertransport.mGnssCallback;
        }

        static OnNmeaMessageListener _2D_get1(GnssStatusListenerTransport gnssstatuslistenertransport)
        {
            return gnssstatuslistenertransport.mGnssNmeaListener;
        }

        static GpsStatus.Listener _2D_get2(GnssStatusListenerTransport gnssstatuslistenertransport)
        {
            return gnssstatuslistenertransport.mGpsListener;
        }

        static GpsStatus.NmeaListener _2D_get3(GnssStatusListenerTransport gnssstatuslistenertransport)
        {
            return gnssstatuslistenertransport.mGpsNmeaListener;
        }

        static ArrayList _2D_get4(GnssStatusListenerTransport gnssstatuslistenertransport)
        {
            return gnssstatuslistenertransport.mNmeaBuffer;
        }

        public void onFirstFix(int i)
        {
            if(mGnssCallback != null)
            {
                LocationManager._2D_set1(LocationManager.this, i);
                Message message = Message.obtain();
                message.what = 3;
                mGnssHandler.sendMessage(message);
            }
        }

        public void onGnssStarted()
        {
            if(mGnssCallback != null)
            {
                Message message = Message.obtain();
                message.what = 1;
                mGnssHandler.sendMessage(message);
            }
        }

        public void onGnssStopped()
        {
            if(mGnssCallback != null)
            {
                Message message = Message.obtain();
                message.what = 2;
                mGnssHandler.sendMessage(message);
            }
        }

        public void onNmeaReceived(long l, String s)
        {
            if(mGnssNmeaListener == null)
                break MISSING_BLOCK_LABEL_76;
            ArrayList arraylist = mNmeaBuffer;
            arraylist;
            JVM INSTR monitorenter ;
            ArrayList arraylist1 = mNmeaBuffer;
            Nmea nmea = JVM INSTR new #16  <Class LocationManager$GnssStatusListenerTransport$Nmea>;
            nmea.this. Nmea(l, s);
            arraylist1.add(nmea);
            arraylist;
            JVM INSTR monitorexit ;
            s = Message.obtain();
            s.what = 1000;
            mGnssHandler.removeMessages(1000);
            mGnssHandler.sendMessage(s);
            return;
            s;
            throw s;
        }

        public void onSvStatusChanged(int i, int ai[], float af[], float af1[], float af2[], float af3[])
        {
            if(mGnssCallback != null)
            {
                LocationManager._2D_set0(LocationManager.this, new GnssStatus(i, ai, af, af1, af2, af3));
                ai = Message.obtain();
                ai.what = 4;
                mGnssHandler.removeMessages(4);
                mGnssHandler.sendMessage(ai);
            }
        }

        private static final int NMEA_RECEIVED = 1000;
        private final GnssStatus.Callback mGnssCallback;
        private final Handler mGnssHandler;
        private final OnNmeaMessageListener mGnssNmeaListener;
        private final GpsStatus.Listener mGpsListener;
        private final GpsStatus.NmeaListener mGpsNmeaListener;
        private final ArrayList mNmeaBuffer;
        final LocationManager this$0;

        GnssStatusListenerTransport(GnssStatus.Callback callback)
        {
            this(callback, null);
        }

        GnssStatusListenerTransport(GnssStatus.Callback callback, Handler handler)
        {
            this$0 = LocationManager.this;
            super();
            mGnssCallback = callback;
            mGnssHandler = new GnssHandler(handler);
            mGnssNmeaListener = null;
            mNmeaBuffer = null;
            mGpsListener = null;
            mGpsNmeaListener = null;
        }

        GnssStatusListenerTransport(GpsStatus.Listener listener)
        {
            this(listener, null);
        }

        GnssStatusListenerTransport(GpsStatus.Listener listener, Handler handler)
        {
            this$0 = LocationManager.this;
            super();
            mGpsListener = listener;
            mGnssHandler = new GnssHandler(handler);
            mGpsNmeaListener = null;
            mNmeaBuffer = null;
            if(mGpsListener != null)
                locationmanager = new _cls1();
            else
                locationmanager = null;
            mGnssCallback = LocationManager.this;
            mGnssNmeaListener = null;
        }

        GnssStatusListenerTransport(GpsStatus.NmeaListener nmealistener)
        {
            this(nmealistener, null);
        }

        GnssStatusListenerTransport(GpsStatus.NmeaListener nmealistener, Handler handler)
        {
            Object obj = null;
            this$0 = LocationManager.this;
            super();
            mGpsListener = null;
            mGnssHandler = new GnssHandler(handler);
            mGpsNmeaListener = nmealistener;
            mNmeaBuffer = new ArrayList();
            mGnssCallback = null;
            locationmanager = obj;
            if(mGpsNmeaListener != null)
                locationmanager = new _cls2();
            mGnssNmeaListener = LocationManager.this;
        }

        GnssStatusListenerTransport(OnNmeaMessageListener onnmeamessagelistener)
        {
            this(onnmeamessagelistener, null);
        }

        GnssStatusListenerTransport(OnNmeaMessageListener onnmeamessagelistener, Handler handler)
        {
            this$0 = LocationManager.this;
            super();
            mGnssCallback = null;
            mGnssHandler = new GnssHandler(handler);
            mGnssNmeaListener = onnmeamessagelistener;
            mGpsListener = null;
            mGpsNmeaListener = null;
            mNmeaBuffer = new ArrayList();
        }
    }

    private class GnssStatusListenerTransport.GnssHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR lookupswitch 5: default 56
        //                       1: 151
        //                       2: 164
        //                       3: 177
        //                       4: 200
        //                       1000: 57;
               goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
            return;
_L6:
            message = GnssStatusListenerTransport._2D_get4(GnssStatusListenerTransport.this);
            message;
            JVM INSTR monitorenter ;
            int i = GnssStatusListenerTransport._2D_get4(GnssStatusListenerTransport.this).size();
            int j = 0;
_L8:
            if(j >= i)
                break; /* Loop/switch isn't completed */
            GnssStatusListenerTransport.Nmea nmea = (GnssStatusListenerTransport.Nmea)GnssStatusListenerTransport._2D_get4(GnssStatusListenerTransport.this).get(j);
            GnssStatusListenerTransport._2D_get1(GnssStatusListenerTransport.this).onNmeaMessage(nmea.mNmea, nmea.mTimestamp);
            j++;
            if(true) goto _L8; else goto _L7
_L7:
            GnssStatusListenerTransport._2D_get4(GnssStatusListenerTransport.this).clear();
            message;
            JVM INSTR monitorexit ;
            continue; /* Loop/switch isn't completed */
            Exception exception;
            exception;
            throw exception;
_L2:
            GnssStatusListenerTransport._2D_get0(GnssStatusListenerTransport.this).onStarted();
            continue; /* Loop/switch isn't completed */
_L3:
            GnssStatusListenerTransport._2D_get0(GnssStatusListenerTransport.this).onStopped();
            continue; /* Loop/switch isn't completed */
_L4:
            GnssStatusListenerTransport._2D_get0(GnssStatusListenerTransport.this).onFirstFix(LocationManager._2D_get2(_fld0));
            continue; /* Loop/switch isn't completed */
_L5:
            GnssStatusListenerTransport._2D_get0(GnssStatusListenerTransport.this).onSatelliteStatusChanged(LocationManager._2D_get0(_fld0));
            if(true) goto _L1; else goto _L9
_L9:
        }

        final GnssStatusListenerTransport this$1;

        public GnssStatusListenerTransport.GnssHandler(Handler handler)
        {
            this$1 = GnssStatusListenerTransport.this;
            if(handler != null)
                gnssstatuslistenertransport = handler.getLooper();
            else
                gnssstatuslistenertransport = Looper.myLooper();
            super(GnssStatusListenerTransport.this);
        }
    }

    private class GnssStatusListenerTransport.Nmea
    {

        String mNmea;
        long mTimestamp;
        final GnssStatusListenerTransport this$1;

        GnssStatusListenerTransport.Nmea(long l, String s)
        {
            this$1 = GnssStatusListenerTransport.this;
            super();
            mTimestamp = l;
            mNmea = s;
        }
    }

    private class ListenerTransport extends ILocationListener.Stub
    {

        static void _2D_wrap0(ListenerTransport listenertransport, Message message)
        {
            listenertransport._handleMessage(message);
        }

        private void _handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 4: default 36
        //                       1 50
        //                       2 78
        //                       3 122
        //                       4 141;
               goto _L1 _L2 _L3 _L4 _L5
_L1:
            break; /* Loop/switch isn't completed */
_L5:
            break MISSING_BLOCK_LABEL_141;
_L6:
            try
            {
                LocationManager._2D_get1(LocationManager.this).locationCallbackFinished(this);
                return;
            }
            // Misplaced declaration of an exception variable
            catch(Message message)
            {
                throw message.rethrowFromSystemServer();
            }
_L2:
            message = new Location((Location)message.obj);
            mListener.onLocationChanged(message);
              goto _L6
_L3:
            Bundle bundle = (Bundle)message.obj;
            message = bundle.getString("provider");
            int i = bundle.getInt("status");
            bundle = bundle.getBundle("extras");
            mListener.onStatusChanged(message, i, bundle);
              goto _L6
_L4:
            mListener.onProviderEnabled((String)message.obj);
              goto _L6
            mListener.onProviderDisabled((String)message.obj);
              goto _L6
        }

        public void onLocationChanged(Location location)
        {
            Message message = Message.obtain();
            message.what = 1;
            message.obj = location;
            mListenerHandler.sendMessage(message);
        }

        public void onProviderDisabled(String s)
        {
            Message message = Message.obtain();
            message.what = 4;
            message.obj = s;
            mListenerHandler.sendMessage(message);
        }

        public void onProviderEnabled(String s)
        {
            Message message = Message.obtain();
            message.what = 3;
            message.obj = s;
            mListenerHandler.sendMessage(message);
        }

        public void onStatusChanged(String s, int i, Bundle bundle)
        {
            Message message = Message.obtain();
            message.what = 2;
            Bundle bundle1 = new Bundle();
            bundle1.putString("provider", s);
            bundle1.putInt("status", i);
            if(bundle != null)
                bundle1.putBundle("extras", bundle);
            message.obj = bundle1;
            mListenerHandler.sendMessage(message);
        }

        private static final int TYPE_LOCATION_CHANGED = 1;
        private static final int TYPE_PROVIDER_DISABLED = 4;
        private static final int TYPE_PROVIDER_ENABLED = 3;
        private static final int TYPE_STATUS_CHANGED = 2;
        private LocationListener mListener;
        private final Handler mListenerHandler;
        final LocationManager this$0;

        ListenerTransport(LocationListener locationlistener, Looper looper)
        {
            this$0 = LocationManager.this;
            super();
            mListener = locationlistener;
            if(looper == null)
                mListenerHandler = new _cls1();
            else
                mListenerHandler = new _cls2(looper);
        }
    }


    static GnssStatus _2D_get0(LocationManager locationmanager)
    {
        return locationmanager.mGnssStatus;
    }

    static ILocationManager _2D_get1(LocationManager locationmanager)
    {
        return locationmanager.mService;
    }

    static int _2D_get2(LocationManager locationmanager)
    {
        return locationmanager.mTimeToFirstFix;
    }

    static GnssStatus _2D_set0(LocationManager locationmanager, GnssStatus gnssstatus)
    {
        locationmanager.mGnssStatus = gnssstatus;
        return gnssstatus;
    }

    static int _2D_set1(LocationManager locationmanager, int i)
    {
        locationmanager.mTimeToFirstFix = i;
        return i;
    }

    public LocationManager(Context context, ILocationManager ilocationmanager)
    {
        mListeners = new HashMap();
        mService = ilocationmanager;
        mContext = context;
        mGnssMeasurementCallbackTransport = new GnssMeasurementCallbackTransport(mContext, mService);
        mGnssNavigationMessageCallbackTransport = new GnssNavigationMessageCallbackTransport(mContext, mService);
        mBatchedLocationCallbackTransport = new BatchedLocationCallbackTransport(mContext, mService);
    }

    private static void checkCriteria(Criteria criteria)
    {
        if(criteria == null)
            throw new IllegalArgumentException((new StringBuilder()).append("invalid criteria: ").append(criteria).toString());
        else
            return;
    }

    private static void checkGeofence(Geofence geofence)
    {
        if(geofence == null)
            throw new IllegalArgumentException((new StringBuilder()).append("invalid geofence: ").append(geofence).toString());
        else
            return;
    }

    private static void checkListener(LocationListener locationlistener)
    {
        if(locationlistener == null)
            throw new IllegalArgumentException((new StringBuilder()).append("invalid listener: ").append(locationlistener).toString());
        else
            return;
    }

    private void checkPendingIntent(PendingIntent pendingintent)
    {
        if(pendingintent == null)
            throw new IllegalArgumentException((new StringBuilder()).append("invalid pending intent: ").append(pendingintent).toString());
        if(!pendingintent.isTargetedToPackage())
        {
            pendingintent = new IllegalArgumentException("pending intent must be targeted to package");
            if(mContext.getApplicationInfo().targetSdkVersion > 16)
                throw pendingintent;
            Log.w("LocationManager", pendingintent);
        }
    }

    private static void checkProvider(String s)
    {
        if(s == null)
            throw new IllegalArgumentException((new StringBuilder()).append("invalid provider: ").append(s).toString());
        else
            return;
    }

    private LocationProvider createProvider(String s, ProviderProperties providerproperties)
    {
        return new LocationProvider(s, providerproperties);
    }

    private void requestLocationUpdates(LocationRequest locationrequest, LocationListener locationlistener, Looper looper, PendingIntent pendingintent)
    {
        SeempLog.record(47);
        String s = mContext.getPackageName();
        locationlistener = wrapListener(locationlistener, looper);
        try
        {
            mService.requestLocationUpdates(locationrequest, locationlistener, pendingintent, s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(LocationRequest locationrequest)
        {
            throw locationrequest.rethrowFromSystemServer();
        }
    }

    private ListenerTransport wrapListener(LocationListener locationlistener, Looper looper)
    {
        if(locationlistener == null)
            return null;
        HashMap hashmap = mListeners;
        hashmap;
        JVM INSTR monitorenter ;
        ListenerTransport listenertransport = (ListenerTransport)mListeners.get(locationlistener);
        ListenerTransport listenertransport1;
        listenertransport1 = listenertransport;
        if(listenertransport != null)
            break MISSING_BLOCK_LABEL_48;
        listenertransport1 = JVM INSTR new #19  <Class LocationManager$ListenerTransport>;
        listenertransport1.this. ListenerTransport(locationlistener, looper);
        mListeners.put(locationlistener, listenertransport1);
        hashmap;
        JVM INSTR monitorexit ;
        return listenertransport1;
        locationlistener;
        throw locationlistener;
    }

    public void addGeofence(LocationRequest locationrequest, Geofence geofence, PendingIntent pendingintent)
    {
        checkPendingIntent(pendingintent);
        checkGeofence(geofence);
        try
        {
            mService.requestGeofence(locationrequest, geofence, pendingintent, mContext.getPackageName());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(LocationRequest locationrequest)
        {
            throw locationrequest.rethrowFromSystemServer();
        }
    }

    public boolean addGpsMeasurementListener(GpsMeasurementsEvent.Listener listener)
    {
        return false;
    }

    public boolean addGpsNavigationMessageListener(GpsNavigationMessageEvent.Listener listener)
    {
        return false;
    }

    public boolean addGpsStatusListener(GpsStatus.Listener listener)
    {
        SeempLog.record(43);
        if(mGpsStatusListeners.get(listener) != null)
            return true;
        GnssStatusListenerTransport gnssstatuslistenertransport;
        boolean flag;
        try
        {
            gnssstatuslistenertransport = JVM INSTR new #6   <Class LocationManager$GnssStatusListenerTransport>;
            gnssstatuslistenertransport.this. GnssStatusListenerTransport(listener);
            flag = mService.registerGnssStatusCallback(gnssstatuslistenertransport, mContext.getPackageName());
        }
        // Misplaced declaration of an exception variable
        catch(GpsStatus.Listener listener)
        {
            throw listener.rethrowFromSystemServer();
        }
        if(!flag)
            break MISSING_BLOCK_LABEL_61;
        mGpsStatusListeners.put(listener, gnssstatuslistenertransport);
        return flag;
    }

    public boolean addNmeaListener(GpsStatus.NmeaListener nmealistener)
    {
        SeempLog.record(44);
        if(mGpsNmeaListeners.get(nmealistener) != null)
            return true;
        GnssStatusListenerTransport gnssstatuslistenertransport;
        boolean flag;
        try
        {
            gnssstatuslistenertransport = JVM INSTR new #6   <Class LocationManager$GnssStatusListenerTransport>;
            gnssstatuslistenertransport.this. GnssStatusListenerTransport(nmealistener);
            flag = mService.registerGnssStatusCallback(gnssstatuslistenertransport, mContext.getPackageName());
        }
        // Misplaced declaration of an exception variable
        catch(GpsStatus.NmeaListener nmealistener)
        {
            throw nmealistener.rethrowFromSystemServer();
        }
        if(!flag)
            break MISSING_BLOCK_LABEL_61;
        mGpsNmeaListeners.put(nmealistener, gnssstatuslistenertransport);
        return flag;
    }

    public boolean addNmeaListener(OnNmeaMessageListener onnmeamessagelistener)
    {
        return addNmeaListener(onnmeamessagelistener, null);
    }

    public boolean addNmeaListener(OnNmeaMessageListener onnmeamessagelistener, Handler handler)
    {
        if(mGpsNmeaListeners.get(onnmeamessagelistener) != null)
            return true;
        GnssStatusListenerTransport gnssstatuslistenertransport;
        boolean flag;
        try
        {
            gnssstatuslistenertransport = JVM INSTR new #6   <Class LocationManager$GnssStatusListenerTransport>;
            gnssstatuslistenertransport.this. GnssStatusListenerTransport(onnmeamessagelistener, handler);
            flag = mService.registerGnssStatusCallback(gnssstatuslistenertransport, mContext.getPackageName());
        }
        // Misplaced declaration of an exception variable
        catch(OnNmeaMessageListener onnmeamessagelistener)
        {
            throw onnmeamessagelistener.rethrowFromSystemServer();
        }
        if(!flag)
            break MISSING_BLOCK_LABEL_58;
        mGnssNmeaListeners.put(onnmeamessagelistener, gnssstatuslistenertransport);
        return flag;
    }

    public void addProximityAlert(double d, double d1, float f, long l, 
            PendingIntent pendingintent)
    {
        SeempLog.record(45);
        checkPendingIntent(pendingintent);
        long l1 = l;
        if(l < 0L)
            l1 = 0x7fffffffffffffffL;
        Geofence geofence = Geofence.createCircle(d, d1, f);
        LocationRequest locationrequest = (new LocationRequest()).setExpireIn(l1);
        try
        {
            mService.requestGeofence(locationrequest, geofence, pendingintent, mContext.getPackageName());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(PendingIntent pendingintent)
        {
            throw pendingintent.rethrowFromSystemServer();
        }
    }

    public void addTestProvider(String s, boolean flag, boolean flag1, boolean flag2, boolean flag3, boolean flag4, boolean flag5, 
            boolean flag6, int i, int j)
    {
        ProviderProperties providerproperties = new ProviderProperties(flag, flag1, flag2, flag3, flag4, flag5, flag6, i, j);
        if(s.matches("[^a-zA-Z0-9]"))
            throw new IllegalArgumentException((new StringBuilder()).append("provider name contains illegal character: ").append(s).toString());
        try
        {
            mService.addTestProvider(s, providerproperties, mContext.getOpPackageName());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void clearTestProviderEnabled(String s)
    {
        try
        {
            mService.clearTestProviderEnabled(s, mContext.getOpPackageName());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void clearTestProviderLocation(String s)
    {
        try
        {
            mService.clearTestProviderLocation(s, mContext.getOpPackageName());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void clearTestProviderStatus(String s)
    {
        try
        {
            mService.clearTestProviderStatus(s, mContext.getOpPackageName());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void flushGnssBatch()
    {
        try
        {
            mService.flushGnssBatch(mContext.getPackageName());
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public List getAllProviders()
    {
        List list;
        try
        {
            list = mService.getAllProviders();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return list;
    }

    public String getBestProvider(Criteria criteria, boolean flag)
    {
        checkCriteria(criteria);
        try
        {
            criteria = mService.getBestProvider(criteria, flag);
        }
        // Misplaced declaration of an exception variable
        catch(Criteria criteria)
        {
            throw criteria.rethrowFromSystemServer();
        }
        return criteria;
    }

    public int getGnssBatchSize()
    {
        int i;
        try
        {
            i = mService.getGnssBatchSize(mContext.getPackageName());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public int getGnssYearOfHardware()
    {
        int i;
        try
        {
            i = mService.getGnssYearOfHardware();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public GpsStatus getGpsStatus(GpsStatus gpsstatus)
    {
        GpsStatus gpsstatus1 = gpsstatus;
        if(gpsstatus == null)
            gpsstatus1 = new GpsStatus();
        if(mGnssStatus != null)
            gpsstatus1.setStatus(mGnssStatus, mTimeToFirstFix);
        return gpsstatus1;
    }

    public Location getLastKnownLocation(String s)
    {
        SeempLog.record(46);
        checkProvider(s);
        String s1 = mContext.getPackageName();
        s = LocationRequest.createFromDeprecatedProvider(s, 0L, 0.0F, true);
        try
        {
            s = mService.getLastLocation(s, s1);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return s;
    }

    public Location getLastLocation()
    {
        Object obj = mContext.getPackageName();
        try
        {
            obj = mService.getLastLocation(null, ((String) (obj)));
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return ((Location) (obj));
    }

    public LocationProvider getProvider(String s)
    {
        checkProvider(s);
        ProviderProperties providerproperties;
        try
        {
            providerproperties = mService.getProviderProperties(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        if(providerproperties == null)
            return null;
        s = createProvider(s, providerproperties);
        return s;
    }

    public List getProviders(Criteria criteria, boolean flag)
    {
        checkCriteria(criteria);
        try
        {
            criteria = mService.getProviders(criteria, flag);
        }
        // Misplaced declaration of an exception variable
        catch(Criteria criteria)
        {
            throw criteria.rethrowFromSystemServer();
        }
        return criteria;
    }

    public List getProviders(boolean flag)
    {
        List list;
        try
        {
            list = mService.getProviders(null, flag);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return list;
    }

    public boolean isProviderEnabled(String s)
    {
        checkProvider(s);
        boolean flag;
        try
        {
            flag = mService.isProviderEnabled(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean registerGnssBatchedLocationCallback(long l, boolean flag, BatchedLocationCallback batchedlocationcallback, Handler handler)
    {
        mBatchedLocationCallbackTransport.add(batchedlocationcallback, handler);
        try
        {
            flag = mService.startGnssBatch(l, flag, mContext.getPackageName());
        }
        // Misplaced declaration of an exception variable
        catch(BatchedLocationCallback batchedlocationcallback)
        {
            throw batchedlocationcallback.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean registerGnssMeasurementsCallback(GnssMeasurementsEvent.Callback callback)
    {
        return registerGnssMeasurementsCallback(callback, null);
    }

    public boolean registerGnssMeasurementsCallback(GnssMeasurementsEvent.Callback callback, Handler handler)
    {
        return mGnssMeasurementCallbackTransport.add(callback, handler);
    }

    public boolean registerGnssNavigationMessageCallback(GnssNavigationMessage.Callback callback)
    {
        return registerGnssNavigationMessageCallback(callback, null);
    }

    public boolean registerGnssNavigationMessageCallback(GnssNavigationMessage.Callback callback, Handler handler)
    {
        return mGnssNavigationMessageCallbackTransport.add(callback, handler);
    }

    public boolean registerGnssStatusCallback(GnssStatus.Callback callback)
    {
        return registerGnssStatusCallback(callback, null);
    }

    public boolean registerGnssStatusCallback(GnssStatus.Callback callback, Handler handler)
    {
        if(mGnssStatusListeners.get(callback) != null)
            return true;
        GnssStatusListenerTransport gnssstatuslistenertransport;
        boolean flag;
        try
        {
            gnssstatuslistenertransport = JVM INSTR new #6   <Class LocationManager$GnssStatusListenerTransport>;
            gnssstatuslistenertransport.this. GnssStatusListenerTransport(callback, handler);
            flag = mService.registerGnssStatusCallback(gnssstatuslistenertransport, mContext.getPackageName());
        }
        // Misplaced declaration of an exception variable
        catch(GnssStatus.Callback callback)
        {
            throw callback.rethrowFromSystemServer();
        }
        if(!flag)
            break MISSING_BLOCK_LABEL_58;
        mGnssStatusListeners.put(callback, gnssstatuslistenertransport);
        return flag;
    }

    public void removeAllGeofences(PendingIntent pendingintent)
    {
        checkPendingIntent(pendingintent);
        String s = mContext.getPackageName();
        try
        {
            mService.removeGeofence(null, pendingintent, s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(PendingIntent pendingintent)
        {
            throw pendingintent.rethrowFromSystemServer();
        }
    }

    public void removeGeofence(Geofence geofence, PendingIntent pendingintent)
    {
        checkPendingIntent(pendingintent);
        checkGeofence(geofence);
        String s = mContext.getPackageName();
        try
        {
            mService.removeGeofence(geofence, pendingintent, s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Geofence geofence)
        {
            throw geofence.rethrowFromSystemServer();
        }
    }

    public void removeGpsMeasurementListener(GpsMeasurementsEvent.Listener listener)
    {
    }

    public void removeGpsNavigationMessageListener(GpsNavigationMessageEvent.Listener listener)
    {
    }

    public void removeGpsStatusListener(GpsStatus.Listener listener)
    {
        try
        {
            listener = (GnssStatusListenerTransport)mGpsStatusListeners.remove(listener);
        }
        // Misplaced declaration of an exception variable
        catch(GpsStatus.Listener listener)
        {
            throw listener.rethrowFromSystemServer();
        }
        if(listener == null)
            break MISSING_BLOCK_LABEL_26;
        mService.unregisterGnssStatusCallback(listener);
    }

    public void removeNmeaListener(GpsStatus.NmeaListener nmealistener)
    {
        try
        {
            nmealistener = (GnssStatusListenerTransport)mGpsNmeaListeners.remove(nmealistener);
        }
        // Misplaced declaration of an exception variable
        catch(GpsStatus.NmeaListener nmealistener)
        {
            throw nmealistener.rethrowFromSystemServer();
        }
        if(nmealistener == null)
            break MISSING_BLOCK_LABEL_26;
        mService.unregisterGnssStatusCallback(nmealistener);
    }

    public void removeNmeaListener(OnNmeaMessageListener onnmeamessagelistener)
    {
        try
        {
            onnmeamessagelistener = (GnssStatusListenerTransport)mGnssNmeaListeners.remove(onnmeamessagelistener);
        }
        // Misplaced declaration of an exception variable
        catch(OnNmeaMessageListener onnmeamessagelistener)
        {
            throw onnmeamessagelistener.rethrowFromSystemServer();
        }
        if(onnmeamessagelistener == null)
            break MISSING_BLOCK_LABEL_26;
        mService.unregisterGnssStatusCallback(onnmeamessagelistener);
    }

    public void removeProximityAlert(PendingIntent pendingintent)
    {
        checkPendingIntent(pendingintent);
        String s = mContext.getPackageName();
        try
        {
            mService.removeGeofence(null, pendingintent, s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(PendingIntent pendingintent)
        {
            throw pendingintent.rethrowFromSystemServer();
        }
    }

    public void removeTestProvider(String s)
    {
        try
        {
            mService.removeTestProvider(s, mContext.getOpPackageName());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void removeUpdates(PendingIntent pendingintent)
    {
        checkPendingIntent(pendingintent);
        String s = mContext.getPackageName();
        try
        {
            mService.removeUpdates(null, pendingintent, s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(PendingIntent pendingintent)
        {
            throw pendingintent.rethrowFromSystemServer();
        }
    }

    public void removeUpdates(LocationListener locationlistener)
    {
        String s;
        checkListener(locationlistener);
        s = mContext.getPackageName();
        HashMap hashmap = mListeners;
        hashmap;
        JVM INSTR monitorenter ;
        locationlistener = (ListenerTransport)mListeners.remove(locationlistener);
        hashmap;
        JVM INSTR monitorexit ;
        if(locationlistener == null)
            return;
        break MISSING_BLOCK_LABEL_43;
        locationlistener;
        throw locationlistener;
        try
        {
            mService.removeUpdates(locationlistener, null, s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(LocationListener locationlistener)
        {
            throw locationlistener.rethrowFromSystemServer();
        }
    }

    public void requestLocationUpdates(long l, float f, Criteria criteria, PendingIntent pendingintent)
    {
        SeempLog.record(47);
        checkCriteria(criteria);
        checkPendingIntent(pendingintent);
        requestLocationUpdates(LocationRequest.createFromDeprecatedCriteria(criteria, l, f, false), ((LocationListener) (null)), ((Looper) (null)), pendingintent);
    }

    public void requestLocationUpdates(long l, float f, Criteria criteria, LocationListener locationlistener, Looper looper)
    {
        SeempLog.record(47);
        checkCriteria(criteria);
        checkListener(locationlistener);
        requestLocationUpdates(LocationRequest.createFromDeprecatedCriteria(criteria, l, f, false), locationlistener, looper, ((PendingIntent) (null)));
    }

    public void requestLocationUpdates(LocationRequest locationrequest, PendingIntent pendingintent)
    {
        SeempLog.record(47);
        checkPendingIntent(pendingintent);
        requestLocationUpdates(locationrequest, ((LocationListener) (null)), ((Looper) (null)), pendingintent);
    }

    public void requestLocationUpdates(LocationRequest locationrequest, LocationListener locationlistener, Looper looper)
    {
        SeempLog.record(47);
        checkListener(locationlistener);
        requestLocationUpdates(locationrequest, locationlistener, looper, ((PendingIntent) (null)));
    }

    public void requestLocationUpdates(String s, long l, float f, PendingIntent pendingintent)
    {
        SeempLog.record(47);
        checkProvider(s);
        checkPendingIntent(pendingintent);
        requestLocationUpdates(LocationRequest.createFromDeprecatedProvider(s, l, f, false), ((LocationListener) (null)), ((Looper) (null)), pendingintent);
    }

    public void requestLocationUpdates(String s, long l, float f, LocationListener locationlistener)
    {
        SeempLog.record(47);
        checkProvider(s);
        checkListener(locationlistener);
        requestLocationUpdates(LocationRequest.createFromDeprecatedProvider(s, l, f, false), locationlistener, ((Looper) (null)), ((PendingIntent) (null)));
    }

    public void requestLocationUpdates(String s, long l, float f, LocationListener locationlistener, Looper looper)
    {
        SeempLog.record(47);
        checkProvider(s);
        checkListener(locationlistener);
        requestLocationUpdates(LocationRequest.createFromDeprecatedProvider(s, l, f, false), locationlistener, looper, ((PendingIntent) (null)));
    }

    public void requestSingleUpdate(Criteria criteria, PendingIntent pendingintent)
    {
        SeempLog.record(64);
        checkCriteria(criteria);
        checkPendingIntent(pendingintent);
        requestLocationUpdates(LocationRequest.createFromDeprecatedCriteria(criteria, 0L, 0.0F, true), ((LocationListener) (null)), ((Looper) (null)), pendingintent);
    }

    public void requestSingleUpdate(Criteria criteria, LocationListener locationlistener, Looper looper)
    {
        SeempLog.record(64);
        checkCriteria(criteria);
        checkListener(locationlistener);
        requestLocationUpdates(LocationRequest.createFromDeprecatedCriteria(criteria, 0L, 0.0F, true), locationlistener, looper, ((PendingIntent) (null)));
    }

    public void requestSingleUpdate(String s, PendingIntent pendingintent)
    {
        SeempLog.record(64);
        checkProvider(s);
        checkPendingIntent(pendingintent);
        requestLocationUpdates(LocationRequest.createFromDeprecatedProvider(s, 0L, 0.0F, true), ((LocationListener) (null)), ((Looper) (null)), pendingintent);
    }

    public void requestSingleUpdate(String s, LocationListener locationlistener, Looper looper)
    {
        SeempLog.record(64);
        checkProvider(s);
        checkListener(locationlistener);
        requestLocationUpdates(LocationRequest.createFromDeprecatedProvider(s, 0L, 0.0F, true), locationlistener, looper, ((PendingIntent) (null)));
    }

    public boolean sendExtraCommand(String s, String s1, Bundle bundle)
    {
        SeempLog.record(48);
        boolean flag;
        try
        {
            flag = mService.sendExtraCommand(s, s1, bundle);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean sendNiResponse(int i, int j)
    {
        boolean flag;
        try
        {
            flag = mService.sendNiResponse(i, j);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public void setTestProviderEnabled(String s, boolean flag)
    {
        try
        {
            mService.setTestProviderEnabled(s, flag, mContext.getOpPackageName());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void setTestProviderLocation(String s, Location location)
    {
label0:
        {
            IllegalArgumentException illegalargumentexception;
            if(!location.isComplete())
            {
                illegalargumentexception = new IllegalArgumentException((new StringBuilder()).append("Incomplete location object, missing timestamp or accuracy? ").append(location).toString());
                if(mContext.getApplicationInfo().targetSdkVersion > 16)
                    break label0;
                Log.w("LocationManager", illegalargumentexception);
                location.makeComplete();
            }
            try
            {
                mService.setTestProviderLocation(s, location, mContext.getOpPackageName());
                return;
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                throw s.rethrowFromSystemServer();
            }
        }
        throw illegalargumentexception;
    }

    public void setTestProviderStatus(String s, int i, Bundle bundle, long l)
    {
        try
        {
            mService.setTestProviderStatus(s, i, bundle, l, mContext.getOpPackageName());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public boolean unregisterGnssBatchedLocationCallback(BatchedLocationCallback batchedlocationcallback)
    {
        mBatchedLocationCallbackTransport.remove(batchedlocationcallback);
        boolean flag;
        try
        {
            flag = mService.stopGnssBatch();
        }
        // Misplaced declaration of an exception variable
        catch(BatchedLocationCallback batchedlocationcallback)
        {
            throw batchedlocationcallback.rethrowFromSystemServer();
        }
        return flag;
    }

    public void unregisterGnssMeasurementsCallback(GnssMeasurementsEvent.Callback callback)
    {
        mGnssMeasurementCallbackTransport.remove(callback);
    }

    public void unregisterGnssNavigationMessageCallback(GnssNavigationMessage.Callback callback)
    {
        mGnssNavigationMessageCallbackTransport.remove(callback);
    }

    public void unregisterGnssStatusCallback(GnssStatus.Callback callback)
    {
        try
        {
            callback = (GnssStatusListenerTransport)mGnssStatusListeners.remove(callback);
        }
        // Misplaced declaration of an exception variable
        catch(GnssStatus.Callback callback)
        {
            throw callback.rethrowFromSystemServer();
        }
        if(callback == null)
            break MISSING_BLOCK_LABEL_26;
        mService.unregisterGnssStatusCallback(callback);
    }

    public static final String EXTRA_GPS_ENABLED = "enabled";
    public static final String FUSED_PROVIDER = "fused";
    public static final String GPS_ENABLED_CHANGE_ACTION = "android.location.GPS_ENABLED_CHANGE";
    public static final String GPS_FIX_CHANGE_ACTION = "android.location.GPS_FIX_CHANGE";
    public static final String GPS_PROVIDER = "gps";
    public static final String HIGH_POWER_REQUEST_CHANGE_ACTION = "android.location.HIGH_POWER_REQUEST_CHANGE";
    public static final String KEY_LOCATION_CHANGED = "location";
    public static final String KEY_PROVIDER_ENABLED = "providerEnabled";
    public static final String KEY_PROXIMITY_ENTERING = "entering";
    public static final String KEY_STATUS_CHANGED = "status";
    public static final String MODE_CHANGED_ACTION = "android.location.MODE_CHANGED";
    public static final String NETWORK_PROVIDER = "network";
    public static final String PASSIVE_PROVIDER = "passive";
    public static final String PROVIDERS_CHANGED_ACTION = "android.location.PROVIDERS_CHANGED";
    private static final String TAG = "LocationManager";
    private final BatchedLocationCallbackTransport mBatchedLocationCallbackTransport;
    private final Context mContext;
    private final GnssMeasurementCallbackTransport mGnssMeasurementCallbackTransport;
    private final GnssNavigationMessageCallbackTransport mGnssNavigationMessageCallbackTransport;
    private final HashMap mGnssNmeaListeners = new HashMap();
    private volatile GnssStatus mGnssStatus;
    private final HashMap mGnssStatusListeners = new HashMap();
    private final HashMap mGpsNmeaListeners = new HashMap();
    private final HashMap mGpsStatusListeners = new HashMap();
    private HashMap mListeners;
    private final ILocationManager mService;
    private int mTimeToFirstFix;

    // Unreferenced inner class android/location/LocationManager$GnssStatusListenerTransport$1

/* anonymous class */
    class GnssStatusListenerTransport._cls1 extends GnssStatus.Callback
    {

        public void onFirstFix(int i)
        {
            GnssStatusListenerTransport._2D_get2(GnssStatusListenerTransport.this).onGpsStatusChanged(3);
        }

        public void onSatelliteStatusChanged(GnssStatus gnssstatus)
        {
            GnssStatusListenerTransport._2D_get2(GnssStatusListenerTransport.this).onGpsStatusChanged(4);
        }

        public void onStarted()
        {
            GnssStatusListenerTransport._2D_get2(GnssStatusListenerTransport.this).onGpsStatusChanged(1);
        }

        public void onStopped()
        {
            GnssStatusListenerTransport._2D_get2(GnssStatusListenerTransport.this).onGpsStatusChanged(2);
        }

        final GnssStatusListenerTransport this$1;

            
            {
                this$1 = GnssStatusListenerTransport.this;
                super();
            }
    }


    // Unreferenced inner class android/location/LocationManager$GnssStatusListenerTransport$2

/* anonymous class */
    class GnssStatusListenerTransport._cls2
        implements OnNmeaMessageListener
    {

        public void onNmeaMessage(String s, long l)
        {
            GnssStatusListenerTransport._2D_get3(GnssStatusListenerTransport.this).onNmeaReceived(l, s);
        }

        final GnssStatusListenerTransport this$1;

            
            {
                this$1 = GnssStatusListenerTransport.this;
                super();
            }
    }


    // Unreferenced inner class android/location/LocationManager$ListenerTransport$1

/* anonymous class */
    class ListenerTransport._cls1 extends Handler
    {

        public void handleMessage(Message message)
        {
            ListenerTransport._2D_wrap0(ListenerTransport.this, message);
        }

        final ListenerTransport this$1;

            
            {
                this$1 = ListenerTransport.this;
                super();
            }
    }


    // Unreferenced inner class android/location/LocationManager$ListenerTransport$2

/* anonymous class */
    class ListenerTransport._cls2 extends Handler
    {

        public void handleMessage(Message message)
        {
            ListenerTransport._2D_wrap0(ListenerTransport.this, message);
        }

        final ListenerTransport this$1;

            
            {
                this$1 = ListenerTransport.this;
                super(looper);
            }
    }

}
