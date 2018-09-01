// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.accessibilityservice;

import android.os.Handler;
import android.os.RemoteException;
import android.util.ArrayMap;
import android.util.Log;

// Referenced classes of package android.accessibilityservice:
//            IAccessibilityServiceConnection

public final class FingerprintGestureController
{
    public static abstract class FingerprintGestureCallback
    {

        public void onGestureDetected(int i)
        {
        }

        public void onGestureDetectionAvailabilityChanged(boolean flag)
        {
        }

        public FingerprintGestureCallback()
        {
        }
    }


    public FingerprintGestureController(IAccessibilityServiceConnection iaccessibilityserviceconnection)
    {
        mAccessibilityServiceConnection = iaccessibilityserviceconnection;
    }

    static void lambda$_2D_android_accessibilityservice_FingerprintGestureController_5924(FingerprintGestureCallback fingerprintgesturecallback, boolean flag)
    {
        fingerprintgesturecallback.onGestureDetectionAvailabilityChanged(flag);
    }

    static void lambda$_2D_android_accessibilityservice_FingerprintGestureController_6679(FingerprintGestureCallback fingerprintgesturecallback, int i)
    {
        fingerprintgesturecallback.onGestureDetected(i);
    }

    public boolean isGestureDetectionAvailable()
    {
        boolean flag;
        try
        {
            flag = mAccessibilityServiceConnection.isFingerprintGestureDetectionAvailable();
        }
        catch(RemoteException remoteexception)
        {
            Log.w("FingerprintGestureController", "Failed to check if fingerprint gestures are active", remoteexception);
            remoteexception.rethrowFromSystemServer();
            return false;
        }
        return flag;
    }

    public void onGesture(int i)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        ArrayMap arraymap = new ArrayMap(mCallbackHandlerMap);
        obj;
        JVM INSTR monitorexit ;
        int j = arraymap.size();
        int k = 0;
        while(k < j) 
        {
            obj = (FingerprintGestureCallback)arraymap.keyAt(k);
            Handler handler = (Handler)arraymap.valueAt(k);
            Exception exception;
            if(handler != null)
                handler.post(new _.Lambda.ktCbXYLeLcj2fWU6KTqcB7Ybd9k(i, obj));
            else
                ((FingerprintGestureCallback) (obj)).onGestureDetected(i);
            k++;
        }
        break MISSING_BLOCK_LABEL_97;
        exception;
        throw exception;
    }

    public void onGestureDetectionActiveChanged(boolean flag)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        ArrayMap arraymap = new ArrayMap(mCallbackHandlerMap);
        obj;
        JVM INSTR monitorexit ;
        int i = arraymap.size();
        int j = 0;
        while(j < i) 
        {
            FingerprintGestureCallback fingerprintgesturecallback = (FingerprintGestureCallback)arraymap.keyAt(j);
            obj = (Handler)arraymap.valueAt(j);
            Exception exception;
            if(obj != null)
                ((Handler) (obj)).post(new _.Lambda.ktCbXYLeLcj2fWU6KTqcB7Ybd9k._cls1(flag, fingerprintgesturecallback));
            else
                fingerprintgesturecallback.onGestureDetectionAvailabilityChanged(flag);
            j++;
        }
        break MISSING_BLOCK_LABEL_97;
        exception;
        throw exception;
    }

    public void registerFingerprintGestureCallback(FingerprintGestureCallback fingerprintgesturecallback, Handler handler)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        mCallbackHandlerMap.put(fingerprintgesturecallback, handler);
        obj;
        JVM INSTR monitorexit ;
        return;
        fingerprintgesturecallback;
        throw fingerprintgesturecallback;
    }

    public void unregisterFingerprintGestureCallback(FingerprintGestureCallback fingerprintgesturecallback)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        mCallbackHandlerMap.remove(fingerprintgesturecallback);
        obj;
        JVM INSTR monitorexit ;
        return;
        fingerprintgesturecallback;
        throw fingerprintgesturecallback;
    }

    public static final int FINGERPRINT_GESTURE_SWIPE_DOWN = 8;
    public static final int FINGERPRINT_GESTURE_SWIPE_LEFT = 2;
    public static final int FINGERPRINT_GESTURE_SWIPE_RIGHT = 1;
    public static final int FINGERPRINT_GESTURE_SWIPE_UP = 4;
    private static final String LOG_TAG = "FingerprintGestureController";
    private final IAccessibilityServiceConnection mAccessibilityServiceConnection;
    private final ArrayMap mCallbackHandlerMap = new ArrayMap(1);
    private final Object mLock = new Object();
}
