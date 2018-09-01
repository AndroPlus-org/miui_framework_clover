// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.accessibilityservice;

import android.os.*;
import android.util.ArrayMap;
import android.util.Slog;
import com.android.internal.util.Preconditions;

// Referenced classes of package android.accessibilityservice:
//            IAccessibilityServiceConnection

public final class AccessibilityButtonController
{
    public static abstract class AccessibilityButtonCallback
    {

        public void onAvailabilityChanged(AccessibilityButtonController accessibilitybuttoncontroller, boolean flag)
        {
        }

        public void onClicked(AccessibilityButtonController accessibilitybuttoncontroller)
        {
        }

        public AccessibilityButtonCallback()
        {
        }
    }


    AccessibilityButtonController(IAccessibilityServiceConnection iaccessibilityserviceconnection)
    {
        mServiceConnection = iaccessibilityserviceconnection;
    }

    void dispatchAccessibilityButtonAvailabilityChanged(boolean flag)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mCallbacks != null && !mCallbacks.isEmpty())
            break MISSING_BLOCK_LABEL_35;
        Slog.w("A11yButtonController", "Received accessibility button availability change with no callbacks!");
        obj;
        JVM INSTR monitorexit ;
        return;
        ArrayMap arraymap = new ArrayMap(mCallbacks);
        obj;
        JVM INSTR monitorexit ;
        int i = 0;
        for(int j = arraymap.size(); i < j; i++)
        {
            obj = (AccessibilityButtonCallback)arraymap.keyAt(i);
            ((Handler)arraymap.valueAt(i)).post(new _.Lambda.kpEvk0Apj34UqdMMU09LT6cNwG4._cls1(flag, this, obj));
        }

        break MISSING_BLOCK_LABEL_109;
        Exception exception;
        exception;
        throw exception;
    }

    void dispatchAccessibilityButtonClicked()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mCallbacks != null && !mCallbacks.isEmpty())
            break MISSING_BLOCK_LABEL_35;
        Slog.w("A11yButtonController", "Received accessibility button click with no callbacks!");
        obj;
        JVM INSTR monitorexit ;
        return;
        ArrayMap arraymap = new ArrayMap(mCallbacks);
        obj;
        JVM INSTR monitorexit ;
        int i = 0;
        for(int j = arraymap.size(); i < j; i++)
        {
            obj = (AccessibilityButtonCallback)arraymap.keyAt(i);
            ((Handler)arraymap.valueAt(i)).post(new _.Lambda.kpEvk0Apj34UqdMMU09LT6cNwG4(this, obj));
        }

        break MISSING_BLOCK_LABEL_104;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean isAccessibilityButtonAvailable()
    {
        boolean flag;
        try
        {
            flag = mServiceConnection.isAccessibilityButtonAvailable();
        }
        catch(RemoteException remoteexception)
        {
            Slog.w("A11yButtonController", "Failed to get accessibility button availability.", remoteexception);
            remoteexception.rethrowFromSystemServer();
            return false;
        }
        return flag;
    }

    void lambda$_2D_android_accessibilityservice_AccessibilityButtonController_6699(AccessibilityButtonCallback accessibilitybuttoncallback)
    {
        accessibilitybuttoncallback.onClicked(this);
    }

    void lambda$_2D_android_accessibilityservice_AccessibilityButtonController_7728(AccessibilityButtonCallback accessibilitybuttoncallback, boolean flag)
    {
        accessibilitybuttoncallback.onAvailabilityChanged(this, flag);
    }

    public void registerAccessibilityButtonCallback(AccessibilityButtonCallback accessibilitybuttoncallback)
    {
        registerAccessibilityButtonCallback(accessibilitybuttoncallback, new Handler(Looper.getMainLooper()));
    }

    public void registerAccessibilityButtonCallback(AccessibilityButtonCallback accessibilitybuttoncallback, Handler handler)
    {
        Preconditions.checkNotNull(accessibilitybuttoncallback);
        Preconditions.checkNotNull(handler);
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mCallbacks == null)
        {
            ArrayMap arraymap = JVM INSTR new #34  <Class ArrayMap>;
            arraymap.ArrayMap();
            mCallbacks = arraymap;
        }
        mCallbacks.put(accessibilitybuttoncallback, handler);
        obj;
        JVM INSTR monitorexit ;
        return;
        accessibilitybuttoncallback;
        throw accessibilitybuttoncallback;
    }

    public void unregisterAccessibilityButtonCallback(AccessibilityButtonCallback accessibilitybuttoncallback)
    {
        Preconditions.checkNotNull(accessibilitybuttoncallback);
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        ArrayMap arraymap = mCallbacks;
        if(arraymap != null)
            break MISSING_BLOCK_LABEL_24;
        obj;
        JVM INSTR monitorexit ;
        return;
        int i = mCallbacks.indexOfKey(accessibilitybuttoncallback);
        boolean flag;
        if(i >= 0)
            flag = true;
        else
            flag = false;
        if(!flag)
            break MISSING_BLOCK_LABEL_57;
        mCallbacks.removeAt(i);
        obj;
        JVM INSTR monitorexit ;
        return;
        accessibilitybuttoncallback;
        throw accessibilitybuttoncallback;
    }

    private static final String LOG_TAG = "A11yButtonController";
    private ArrayMap mCallbacks;
    private final Object mLock = new Object();
    private final IAccessibilityServiceConnection mServiceConnection;
}
