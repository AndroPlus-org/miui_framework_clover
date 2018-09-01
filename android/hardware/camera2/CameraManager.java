// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2;

import android.app.ActivityThread;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.hardware.CameraStatus;
import android.hardware.ICameraService;
import android.hardware.camera2.impl.CameraDeviceImpl;
import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.legacy.CameraDeviceUserShim;
import android.hardware.camera2.legacy.LegacyMetadataMapper;
import android.os.*;
import android.util.ArrayMap;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package android.hardware.camera2:
//            CameraAccessException, CameraCharacteristics, CameraDevice

public final class CameraManager
{
    public static abstract class AvailabilityCallback
    {

        public void onCameraAvailable(String s)
        {
        }

        public void onCameraUnavailable(String s)
        {
        }

        public AvailabilityCallback()
        {
        }
    }

    private static final class CameraManagerGlobal extends android.hardware.ICameraServiceListener.Stub
        implements android.os.IBinder.DeathRecipient
    {

        static Object _2D_get0(CameraManagerGlobal cameramanagerglobal)
        {
            return cameramanagerglobal.mLock;
        }

        static void _2D_wrap0(CameraManagerGlobal cameramanagerglobal)
        {
            cameramanagerglobal.scheduleCameraServiceReconnectionLocked();
        }

        private void connectCameraServiceLocked()
        {
            Object obj;
            int i = 0;
            if(mCameraService != null || sCameraServiceDisabled)
                return;
            Log.i("CameraManagerGlobal", "Connecting to camera service");
            obj = ServiceManager.getService("media.camera");
            if(obj == null)
                return;
            CameraStatus acamerastatus[];
            int j;
            CameraStatus camerastatus;
            try
            {
                ((IBinder) (obj)).linkToDeath(this, 0);
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                return;
            }
            obj = android.hardware.ICameraService.Stub.asInterface(((IBinder) (obj)));
            try
            {
                CameraMetadataNative.setupGlobalVendorTagDescriptor();
            }
            catch(ServiceSpecificException servicespecificexception)
            {
                handleRecoverableSetupErrors(servicespecificexception);
            }
            acamerastatus = ((ICameraService) (obj)).addListener(this);
            j = acamerastatus.length;
_L2:
            if(i >= j)
                break; /* Loop/switch isn't completed */
            camerastatus = acamerastatus[i];
            onStatusChangedLocked(camerastatus.status, camerastatus.cameraId);
            i++;
            if(true) goto _L2; else goto _L1
_L1:
            mCameraService = ((ICameraService) (obj));
_L4:
            return;
            Object obj1;
            obj1;
            throw new IllegalStateException("Failed to register a camera service listener", ((Throwable) (obj1)));
            obj1;
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static CameraManagerGlobal get()
        {
            return gCameraManager;
        }

        private void handleRecoverableSetupErrors(ServiceSpecificException servicespecificexception)
        {
            switch(servicespecificexception.errorCode)
            {
            default:
                throw new IllegalStateException(servicespecificexception);

            case 4: // '\004'
                Log.w("CameraManagerGlobal", servicespecificexception.getMessage());
                break;
            }
        }

        private boolean isAvailable(int i)
        {
            switch(i)
            {
            default:
                return false;

            case 1: // '\001'
                return true;
            }
        }

        private void onStatusChangedLocked(int i, String s)
        {
            boolean flag1;
label0:
            {
                boolean flag = false;
                String s1 = ActivityThread.currentOpPackageName();
                String s2 = SystemProperties.get("vendor.camera.aux.packagelist");
                flag1 = flag;
                if(s2.length() <= 0)
                    break label0;
                Object obj = new android.text.TextUtils.SimpleStringSplitter(',');
                ((android.text.TextUtils.StringSplitter) (obj)).setString(s2);
                obj = ((Iterable) (obj)).iterator();
                do
                {
                    flag1 = flag;
                    if(!((Iterator) (obj)).hasNext())
                        break label0;
                } while(!s1.equals((String)((Iterator) (obj)).next()));
                flag1 = true;
            }
            if(!flag1 && Integer.parseInt(s) >= 2)
            {
                Log.w("CameraManagerGlobal", (new StringBuilder()).append("[soar.cts] ignore the status update of camera: ").append(s).toString());
                return;
            }
            if(!validStatus(i))
            {
                Log.e("CameraManagerGlobal", String.format("Ignoring invalid device %s status 0x%x", new Object[] {
                    s, Integer.valueOf(i)
                }));
                return;
            }
            Integer integer = (Integer)mDeviceStatus.put(s, Integer.valueOf(i));
            if(integer != null && integer.intValue() == i)
                return;
            if(integer != null && isAvailable(i) == isAvailable(integer.intValue()))
                return;
            int j = mCallbackMap.size();
            for(int k = 0; k < j; k++)
            {
                Handler handler = (Handler)mCallbackMap.valueAt(k);
                postSingleUpdate((AvailabilityCallback)mCallbackMap.keyAt(k), handler, s, i);
            }

        }

        private void onTorchStatusChangedLocked(int i, String s)
        {
            boolean flag1;
label0:
            {
                boolean flag = false;
                String s1 = ActivityThread.currentOpPackageName();
                Object obj = SystemProperties.get("vendor.camera.aux.packagelist");
                flag1 = flag;
                if(((String) (obj)).length() <= 0)
                    break label0;
                android.text.TextUtils.SimpleStringSplitter simplestringsplitter = new android.text.TextUtils.SimpleStringSplitter(',');
                simplestringsplitter.setString(((String) (obj)));
                obj = simplestringsplitter.iterator();
                do
                {
                    flag1 = flag;
                    if(!((Iterator) (obj)).hasNext())
                        break label0;
                } while(!s1.equals((String)((Iterator) (obj)).next()));
                flag1 = true;
            }
            if(!flag1 && Integer.parseInt(s) >= 2)
            {
                Log.w("CameraManagerGlobal", (new StringBuilder()).append("ignore the torch status update of camera: ").append(s).toString());
                return;
            }
            if(!validTorchStatus(i))
            {
                Log.e("CameraManagerGlobal", String.format("Ignoring invalid device %s torch status 0x%x", new Object[] {
                    s, Integer.valueOf(i)
                }));
                return;
            }
            Integer integer = (Integer)mTorchStatus.put(s, Integer.valueOf(i));
            if(integer != null && integer.intValue() == i)
                return;
            int j = mTorchCallbackMap.size();
            for(int k = 0; k < j; k++)
            {
                Handler handler = (Handler)mTorchCallbackMap.valueAt(k);
                postSingleTorchUpdate((TorchCallback)mTorchCallbackMap.keyAt(k), handler, s, i);
            }

        }

        private void postSingleTorchUpdate(final TorchCallback callback, Handler handler, final String id, int i)
        {
            i;
            JVM INSTR tableswitch 1 2: default 24
        //                       1 40
        //                       2 40;
               goto _L1 _L2 _L2
_L1:
            handler.post(id. new Runnable() {

                public void run()
                {
                    callback.onTorchModeUnavailable(id);
                }

                final CameraManagerGlobal this$1;
                final TorchCallback val$callback;
                final String val$id;

            
            {
                this$1 = final_cameramanagerglobal;
                callback = torchcallback;
                id = String.this;
                super();
            }
            }
);
_L4:
            return;
_L2:
            handler.post(i. new Runnable() {

                public void run()
                {
                    TorchCallback torchcallback = callback;
                    String s = id;
                    boolean flag;
                    if(status == 2)
                        flag = true;
                    else
                        flag = false;
                    torchcallback.onTorchModeChanged(s, flag);
                }

                final CameraManagerGlobal this$1;
                final TorchCallback val$callback;
                final String val$id;
                final int val$status;

            
            {
                this$1 = final_cameramanagerglobal;
                callback = torchcallback;
                id = s;
                status = I.this;
                super();
            }
            }
);
            if(true) goto _L4; else goto _L3
_L3:
        }

        private void postSingleUpdate(final AvailabilityCallback callback, Handler handler, String s, int i)
        {
            if(isAvailable(i))
                handler.post(s. new Runnable() {

                    public void run()
                    {
                        callback.onCameraAvailable(id);
                    }

                    final CameraManagerGlobal this$1;
                    final AvailabilityCallback val$callback;
                    final String val$id;

            
            {
                this$1 = final_cameramanagerglobal;
                callback = availabilitycallback;
                id = String.this;
                super();
            }
                }
);
            else
                handler.post(s. new Runnable() {

                    public void run()
                    {
                        callback.onCameraUnavailable(id);
                    }

                    final CameraManagerGlobal this$1;
                    final AvailabilityCallback val$callback;
                    final String val$id;

            
            {
                this$1 = final_cameramanagerglobal;
                callback = availabilitycallback;
                id = String.this;
                super();
            }
                }
);
        }

        private void scheduleCameraServiceReconnectionLocked()
        {
            Handler handler;
            if(mCallbackMap.size() > 0)
                handler = (Handler)mCallbackMap.valueAt(0);
            else
            if(mTorchCallbackMap.size() > 0)
                handler = (Handler)mTorchCallbackMap.valueAt(0);
            else
                return;
            handler.postDelayed(new Runnable() {

                public void run()
                {
                    if(getCameraService() != null) goto _L2; else goto _L1
_L1:
                    Object obj = CameraManagerGlobal._2D_get0(CameraManagerGlobal.this);
                    obj;
                    JVM INSTR monitorenter ;
                    CameraManagerGlobal._2D_wrap0(CameraManagerGlobal.this);
                    obj;
                    JVM INSTR monitorexit ;
_L2:
                    return;
                    Exception exception;
                    exception;
                    throw exception;
                }

                final CameraManagerGlobal this$1;

            
            {
                this$1 = CameraManagerGlobal.this;
                super();
            }
            }
, 1000L);
        }

        private void updateCallbackLocked(AvailabilityCallback availabilitycallback, Handler handler)
        {
            for(int i = 0; i < mDeviceStatus.size(); i++)
                postSingleUpdate(availabilitycallback, handler, (String)mDeviceStatus.keyAt(i), ((Integer)mDeviceStatus.valueAt(i)).intValue());

        }

        private void updateTorchCallbackLocked(TorchCallback torchcallback, Handler handler)
        {
            for(int i = 0; i < mTorchStatus.size(); i++)
                postSingleTorchUpdate(torchcallback, handler, (String)mTorchStatus.keyAt(i), ((Integer)mTorchStatus.valueAt(i)).intValue());

        }

        private boolean validStatus(int i)
        {
            switch(i)
            {
            case -1: 
            default:
                return false;

            case -2: 
            case 0: // '\0'
            case 1: // '\001'
            case 2: // '\002'
                return true;
            }
        }

        private boolean validTorchStatus(int i)
        {
            switch(i)
            {
            default:
                return false;

            case 0: // '\0'
            case 1: // '\001'
            case 2: // '\002'
                return true;
            }
        }

        public IBinder asBinder()
        {
            return this;
        }

        public void binderDied()
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            ICameraService icameraservice = mCameraService;
            if(icameraservice != null)
                break MISSING_BLOCK_LABEL_19;
            obj;
            JVM INSTR monitorexit ;
            return;
            mCameraService = null;
            int i = 0;
_L2:
            if(i >= mDeviceStatus.size())
                break; /* Loop/switch isn't completed */
            onStatusChangedLocked(0, (String)mDeviceStatus.keyAt(i));
            i++;
            if(true) goto _L2; else goto _L1
_L1:
            i = 0;
_L4:
            if(i >= mTorchStatus.size())
                break; /* Loop/switch isn't completed */
            onTorchStatusChangedLocked(0, (String)mTorchStatus.keyAt(i));
            i++;
            if(true) goto _L4; else goto _L3
_L3:
            scheduleCameraServiceReconnectionLocked();
            obj;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public String[] getCameraIdList()
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            connectCameraServiceLocked();
            int i = 0;
            String s;
            Object obj1;
            s = ActivityThread.currentOpPackageName();
            obj1 = SystemProperties.get("vendor.camera.aux.packagelist");
            boolean flag = i;
            if(((String) (obj1)).length() <= 0)
                break MISSING_BLOCK_LABEL_98;
            android.text.TextUtils.SimpleStringSplitter simplestringsplitter = JVM INSTR new #199 <Class android.text.TextUtils$SimpleStringSplitter>;
            simplestringsplitter.android.text.TextUtils.SimpleStringSplitter(',');
            simplestringsplitter.setString(((String) (obj1)));
            obj1 = simplestringsplitter.iterator();
_L2:
            flag = i;
            if(!((Iterator) (obj1)).hasNext())
                break MISSING_BLOCK_LABEL_98;
            if(!s.equals((String)((Iterator) (obj1)).next())) goto _L2; else goto _L1
_L1:
            flag = true;
            int j;
            int k;
            j = 0;
            k = 0;
_L11:
            if(k < mDeviceStatus.size() && (flag || k != 2)) goto _L4; else goto _L3
_L3:
            String as[] = new String[j];
            i = 0;
            j = 0;
_L8:
            k = mDeviceStatus.size();
            if(j < k && (flag || j != 2)) goto _L6; else goto _L5
_L5:
            obj;
            JVM INSTR monitorexit ;
            return as;
_L4:
            int l = ((Integer)mDeviceStatus.valueAt(k)).intValue();
            i = j;
            if(l != 0)
                if(l == 2)
                    i = j;
                else
                    i = j + 1;
            k++;
            j = i;
            continue; /* Loop/switch isn't completed */
_L6:
            l = ((Integer)mDeviceStatus.valueAt(j)).intValue();
            k = i;
            if(l != 0)
            {
                if(l != 2)
                    break; /* Loop/switch isn't completed */
                k = i;
            }
_L9:
            j++;
            i = k;
            if(true) goto _L8; else goto _L7
_L7:
            as[i] = (String)mDeviceStatus.keyAt(j);
            k = i + 1;
              goto _L9
            Exception exception;
            exception;
            throw exception;
            if(true) goto _L11; else goto _L10
_L10:
        }

        public ICameraService getCameraService()
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            ICameraService icameraservice;
            connectCameraServiceLocked();
            if(mCameraService == null && sCameraServiceDisabled ^ true)
                Log.e("CameraManagerGlobal", "Camera service is unavailable");
            icameraservice = mCameraService;
            obj;
            JVM INSTR monitorexit ;
            return icameraservice;
            Exception exception;
            exception;
            throw exception;
        }

        public void onStatusChanged(int i, String s)
            throws RemoteException
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            onStatusChangedLocked(i, s);
            obj;
            JVM INSTR monitorexit ;
            return;
            s;
            throw s;
        }

        public void onTorchStatusChanged(int i, String s)
            throws RemoteException
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            onTorchStatusChangedLocked(i, s);
            obj;
            JVM INSTR monitorexit ;
            return;
            s;
            throw s;
        }

        public void registerAvailabilityCallback(AvailabilityCallback availabilitycallback, Handler handler)
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            connectCameraServiceLocked();
            if((Handler)mCallbackMap.put(availabilitycallback, handler) == null)
                updateCallbackLocked(availabilitycallback, handler);
            if(mCameraService == null)
                scheduleCameraServiceReconnectionLocked();
            obj;
            JVM INSTR monitorexit ;
            return;
            availabilitycallback;
            throw availabilitycallback;
        }

        public void registerTorchCallback(TorchCallback torchcallback, Handler handler)
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            connectCameraServiceLocked();
            if((Handler)mTorchCallbackMap.put(torchcallback, handler) == null)
                updateTorchCallbackLocked(torchcallback, handler);
            if(mCameraService == null)
                scheduleCameraServiceReconnectionLocked();
            obj;
            JVM INSTR monitorexit ;
            return;
            torchcallback;
            throw torchcallback;
        }

        public void setTorchMode(String s, boolean flag)
            throws CameraAccessException
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            if(s != null)
                break MISSING_BLOCK_LABEL_29;
            s = JVM INSTR new #357 <Class IllegalArgumentException>;
            s.IllegalArgumentException("cameraId was null");
            throw s;
            s;
            obj;
            JVM INSTR monitorexit ;
            throw s;
            boolean flag1 = false;
            Object obj1;
            String s1;
            obj1 = ActivityThread.currentOpPackageName();
            s1 = SystemProperties.get("vendor.camera.aux.packagelist");
            boolean flag2 = flag1;
            Object obj2;
            if(s1.length() <= 0)
                break MISSING_BLOCK_LABEL_121;
            obj2 = JVM INSTR new #199 <Class android.text.TextUtils$SimpleStringSplitter>;
            ((android.text.TextUtils.SimpleStringSplitter) (obj2)).android.text.TextUtils.SimpleStringSplitter(',');
            ((android.text.TextUtils.StringSplitter) (obj2)).setString(s1);
            obj2 = ((Iterable) (obj2)).iterator();
_L2:
            flag2 = flag1;
            if(!((Iterator) (obj2)).hasNext())
                break MISSING_BLOCK_LABEL_121;
            if(!((String) (obj1)).equals((String)((Iterator) (obj2)).next())) goto _L2; else goto _L1
_L1:
            flag2 = true;
            if(flag2)
                break MISSING_BLOCK_LABEL_147;
            if(Integer.parseInt(s) >= 2)
            {
                s = JVM INSTR new #357 <Class IllegalArgumentException>;
                s.IllegalArgumentException("invalid cameraId");
                throw s;
            }
            obj1 = getCameraService();
            if(obj1 != null)
                break MISSING_BLOCK_LABEL_172;
            s = JVM INSTR new #355 <Class CameraAccessException>;
            s.CameraAccessException(2, "Camera service is currently unavailable");
            throw s;
            ((ICameraService) (obj1)).setTorchMode(s, flag, mTorchClientBinder);
_L3:
            obj;
            JVM INSTR monitorexit ;
            return;
            s;
            s = JVM INSTR new #355 <Class CameraAccessException>;
            s.CameraAccessException(2, "Camera service is currently unavailable");
            throw s;
            s;
            CameraManager.throwAsPublicException(s);
              goto _L3
        }

        public void unregisterAvailabilityCallback(AvailabilityCallback availabilitycallback)
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            mCallbackMap.remove(availabilitycallback);
            obj;
            JVM INSTR monitorexit ;
            return;
            availabilitycallback;
            throw availabilitycallback;
        }

        public void unregisterTorchCallback(TorchCallback torchcallback)
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            mTorchCallbackMap.remove(torchcallback);
            obj;
            JVM INSTR monitorexit ;
            return;
            torchcallback;
            throw torchcallback;
        }

        private static final String CAMERA_SERVICE_BINDER_NAME = "media.camera";
        private static final String TAG = "CameraManagerGlobal";
        private static final CameraManagerGlobal gCameraManager = new CameraManagerGlobal();
        public static final boolean sCameraServiceDisabled = SystemProperties.getBoolean("config.disable_cameraservice", false);
        private final int CAMERA_SERVICE_RECONNECT_DELAY_MS = 1000;
        private final boolean DEBUG = false;
        private final ArrayMap mCallbackMap = new ArrayMap();
        private ICameraService mCameraService;
        private final ArrayMap mDeviceStatus = new ArrayMap();
        private final Object mLock = new Object();
        private final ArrayMap mTorchCallbackMap = new ArrayMap();
        private Binder mTorchClientBinder;
        private final ArrayMap mTorchStatus = new ArrayMap();


        private CameraManagerGlobal()
        {
            mTorchClientBinder = new Binder();
        }
    }

    public static abstract class TorchCallback
    {

        public void onTorchModeChanged(String s, boolean flag)
        {
        }

        public void onTorchModeUnavailable(String s)
        {
        }

        public TorchCallback()
        {
        }
    }


    public CameraManager(Context context)
    {
        DEBUG = false;
        mLock = new Object();
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        mContext = context;
        obj;
        JVM INSTR monitorexit ;
        return;
        context;
        throw context;
    }

    private CameraDevice openCameraDeviceUserAsync(String s, CameraDevice.StateCallback statecallback, Handler handler, int i)
        throws CameraAccessException
    {
        CameraCharacteristics cameracharacteristics = getCameraCharacteristics(s);
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1 = null;
        CameraDeviceImpl cameradeviceimpl;
        cameradeviceimpl = JVM INSTR new #74  <Class CameraDeviceImpl>;
        cameradeviceimpl.CameraDeviceImpl(s, statecallback, handler, cameracharacteristics, mContext.getApplicationInfo().targetSdkVersion);
        statecallback = cameradeviceimpl.getCallbacks();
        if(!supportsCamera2ApiLocked(s)) goto _L2; else goto _L1
_L1:
        handler = CameraManagerGlobal.get().getCameraService();
        if(handler != null) goto _L4; else goto _L3
_L3:
        s = JVM INSTR new #64  <Class ServiceSpecificException>;
        s.ServiceSpecificException(4, "Camera service is currently unavailable");
        throw s;
        statecallback;
        if(((ServiceSpecificException) (statecallback)).errorCode == 9)
        {
            s = JVM INSTR new #114 <Class AssertionError>;
            s.AssertionError("Should've gone down the shim path");
            throw s;
        }
          goto _L5
        s;
        obj;
        JVM INSTR monitorexit ;
        throw s;
_L4:
        s = handler.connectDevice(statecallback, s, mContext.getOpPackageName(), i);
_L6:
        cameradeviceimpl.setRemoteDevice(s);
        obj;
        JVM INSTR monitorexit ;
        return cameradeviceimpl;
_L2:
        i = Integer.parseInt(s);
        Log.i("CameraManager", "Using legacy camera HAL.");
        s = CameraDeviceUserShim.connectBinderShim(statecallback, i);
          goto _L6
        statecallback;
        statecallback = JVM INSTR new #155 <Class IllegalArgumentException>;
        handler = JVM INSTR new #157 <Class StringBuilder>;
        handler.StringBuilder();
        statecallback.IllegalArgumentException(handler.append("Expected cameraId to be numeric, but it was: ").append(s).toString());
        throw statecallback;
        s;
        s = JVM INSTR new #64  <Class ServiceSpecificException>;
        s.ServiceSpecificException(4, "Camera service is currently unavailable");
        cameradeviceimpl.setRemoteFailure(s);
        throwAsPublicException(s);
        s = obj1;
          goto _L6
_L5:
        if(((ServiceSpecificException) (statecallback)).errorCode != 7 && ((ServiceSpecificException) (statecallback)).errorCode != 8) goto _L8; else goto _L7
_L7:
        cameradeviceimpl.setRemoteFailure(statecallback);
        if(((ServiceSpecificException) (statecallback)).errorCode != 6 && ((ServiceSpecificException) (statecallback)).errorCode != 4) goto _L10; else goto _L9
_L9:
        throwAsPublicException(statecallback);
        s = obj1;
        break; /* Loop/switch isn't completed */
_L8:
        if(((ServiceSpecificException) (statecallback)).errorCode == 6 || ((ServiceSpecificException) (statecallback)).errorCode == 4 || ((ServiceSpecificException) (statecallback)).errorCode == 10) goto _L7; else goto _L11
_L11:
        throwAsPublicException(statecallback);
        s = obj1;
        break; /* Loop/switch isn't completed */
_L10:
        i = ((ServiceSpecificException) (statecallback)).errorCode;
        s = obj1;
        if(i != 7) goto _L6; else goto _L9
    }

    private boolean supportsCamera2ApiLocked(String s)
    {
        return supportsCameraApiLocked(s, 2);
    }

    private boolean supportsCameraApiLocked(String s, int i)
    {
        ICameraService icameraservice;
        boolean flag;
        try
        {
            icameraservice = CameraManagerGlobal.get().getCameraService();
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return false;
        }
        if(icameraservice == null)
            return false;
        flag = icameraservice.supportsCameraApi(s, i);
        return flag;
    }

    public static void throwAsPublicException(Throwable throwable)
        throws CameraAccessException
    {
        if(!(throwable instanceof ServiceSpecificException))
            break MISSING_BLOCK_LABEL_137;
        throwable = (ServiceSpecificException)throwable;
        ((ServiceSpecificException) (throwable)).errorCode;
        JVM INSTR tableswitch 1 9: default 68
    //                   1 124
    //                   2 111
    //                   3 111
    //                   4 84
    //                   5 68
    //                   6 89
    //                   7 94
    //                   8 99
    //                   9 104;
           goto _L1 _L2 _L3 _L3 _L4 _L1 _L5 _L6 _L7 _L8
_L2:
        break MISSING_BLOCK_LABEL_124;
_L1:
        char c = '\003';
_L9:
        throw new CameraAccessException(c, throwable.getMessage(), throwable);
_L4:
        c = '\002';
        continue; /* Loop/switch isn't completed */
_L5:
        c = '\001';
        continue; /* Loop/switch isn't completed */
_L6:
        c = '\004';
        continue; /* Loop/switch isn't completed */
_L7:
        c = '\005';
        continue; /* Loop/switch isn't completed */
_L8:
        c = '\u03E8';
        if(true) goto _L9; else goto _L3
_L3:
        throw new IllegalArgumentException(throwable.getMessage(), throwable);
        throw new SecurityException(throwable.getMessage(), throwable);
        if(throwable instanceof DeadObjectException)
            throw new CameraAccessException(2, "Camera service has died unexpectedly", throwable);
        if(throwable instanceof RemoteException)
            throw new UnsupportedOperationException("An unknown RemoteException was thrown which should never happen.", throwable);
        if(throwable instanceof RuntimeException)
            throw (RuntimeException)throwable;
        else
            return;
    }

    public CameraCharacteristics getCameraCharacteristics(String s)
        throws CameraAccessException
    {
        RemoteException remoteexception;
        remoteexception = null;
        if(CameraManagerGlobal.sCameraServiceDisabled)
            throw new IllegalArgumentException("No cameras available on device");
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        ICameraService icameraservice = CameraManagerGlobal.get().getCameraService();
        if(icameraservice != null)
            break MISSING_BLOCK_LABEL_56;
        s = JVM INSTR new #62  <Class CameraAccessException>;
        s.CameraAccessException(2, "Camera service is currently unavailable");
        throw s;
        s;
        obj;
        JVM INSTR monitorexit ;
        throw s;
        if(supportsCamera2ApiLocked(s)) goto _L2; else goto _L1
_L1:
        int i = Integer.parseInt(s);
        s = LegacyMetadataMapper.createCharacteristics(icameraservice.getLegacyParameters(i), icameraservice.getCameraInfo(i));
_L3:
        obj;
        JVM INSTR monitorexit ;
        return s;
_L2:
        s = new CameraCharacteristics(icameraservice.getCameraCharacteristics(s));
          goto _L3
        remoteexception;
        s = JVM INSTR new #62  <Class CameraAccessException>;
        s.CameraAccessException(2, "Camera service is currently unavailable", remoteexception);
        throw s;
        s;
        throwAsPublicException(s);
        s = remoteexception;
          goto _L3
    }

    public String[] getCameraIdList()
        throws CameraAccessException
    {
        return CameraManagerGlobal.get().getCameraIdList();
    }

    public void openCamera(String s, CameraDevice.StateCallback statecallback, Handler handler)
        throws CameraAccessException
    {
        openCameraForUid(s, statecallback, handler, -1);
    }

    public void openCameraForUid(String s, CameraDevice.StateCallback statecallback, Handler handler, int i)
        throws CameraAccessException
    {
label0:
        {
            if(s == null)
                throw new IllegalArgumentException("cameraId was null");
            if(statecallback == null)
                throw new IllegalArgumentException("callback was null");
            Handler handler1 = handler;
            if(handler == null)
            {
                if(Looper.myLooper() == null)
                    break label0;
                handler1 = new Handler();
            }
            if(CameraManagerGlobal.sCameraServiceDisabled)
            {
                throw new IllegalArgumentException("No cameras available on device");
            } else
            {
                openCameraDeviceUserAsync(s, statecallback, handler1, i);
                return;
            }
        }
        throw new IllegalArgumentException("Handler argument is null, but no looper exists in the calling thread");
    }

    public void registerAvailabilityCallback(AvailabilityCallback availabilitycallback, Handler handler)
    {
        Handler handler1 = handler;
        if(handler == null)
        {
            handler = Looper.myLooper();
            if(handler == null)
                throw new IllegalArgumentException("No handler given, and current thread has no looper!");
            handler1 = new Handler(handler);
        }
        CameraManagerGlobal.get().registerAvailabilityCallback(availabilitycallback, handler1);
    }

    public void registerTorchCallback(TorchCallback torchcallback, Handler handler)
    {
        Handler handler1 = handler;
        if(handler == null)
        {
            handler = Looper.myLooper();
            if(handler == null)
                throw new IllegalArgumentException("No handler given, and current thread has no looper!");
            handler1 = new Handler(handler);
        }
        CameraManagerGlobal.get().registerTorchCallback(torchcallback, handler1);
    }

    public void setTorchMode(String s, boolean flag)
        throws CameraAccessException
    {
        if(CameraManagerGlobal.sCameraServiceDisabled)
        {
            throw new IllegalArgumentException("No cameras available on device");
        } else
        {
            CameraManagerGlobal.get().setTorchMode(s, flag);
            return;
        }
    }

    public void unregisterAvailabilityCallback(AvailabilityCallback availabilitycallback)
    {
        CameraManagerGlobal.get().unregisterAvailabilityCallback(availabilitycallback);
    }

    public void unregisterTorchCallback(TorchCallback torchcallback)
    {
        CameraManagerGlobal.get().unregisterTorchCallback(torchcallback);
    }

    private static final int API_VERSION_1 = 1;
    private static final int API_VERSION_2 = 2;
    private static final int CAMERA_TYPE_ALL = 1;
    private static final int CAMERA_TYPE_BACKWARD_COMPATIBLE = 0;
    private static final String TAG = "CameraManager";
    private static final int USE_CALLING_UID = -1;
    private final boolean DEBUG;
    private final Context mContext;
    private ArrayList mDeviceIdList;
    private final Object mLock;
}
