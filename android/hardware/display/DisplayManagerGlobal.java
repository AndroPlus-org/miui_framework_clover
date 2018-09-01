// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.display;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.media.projection.MediaProjection;
import android.os.*;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.*;
import java.util.ArrayList;

// Referenced classes of package android.hardware.display:
//            IDisplayManager, VirtualDisplay, WifiDisplayStatus, IVirtualDisplayCallback

public final class DisplayManagerGlobal
{
    private static final class DisplayListenerDelegate extends Handler
    {

        public void clearEvents()
        {
            removeCallbacksAndMessages(null);
        }

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 3: default 32
        //                       1 33
        //                       2 49
        //                       3 65;
               goto _L1 _L2 _L3 _L4
_L1:
            return;
_L2:
            mListener.onDisplayAdded(message.arg1);
            continue; /* Loop/switch isn't completed */
_L3:
            mListener.onDisplayChanged(message.arg1);
            continue; /* Loop/switch isn't completed */
_L4:
            mListener.onDisplayRemoved(message.arg1);
            if(true) goto _L1; else goto _L5
_L5:
        }

        public void sendDisplayEvent(int i, int j)
        {
            sendMessage(obtainMessage(j, i, 0));
        }

        public final DisplayManager.DisplayListener mListener;

        public DisplayListenerDelegate(DisplayManager.DisplayListener displaylistener, Handler handler)
        {
            if(handler != null)
                handler = handler.getLooper();
            else
                handler = Looper.myLooper();
            super(handler, null, true);
            mListener = displaylistener;
        }
    }

    private final class DisplayManagerCallback extends IDisplayManagerCallback.Stub
    {

        public void onDisplayEvent(int i, int j)
        {
            DisplayManagerGlobal._2D_wrap0(DisplayManagerGlobal.this, i, j);
        }

        final DisplayManagerGlobal this$0;

        private DisplayManagerCallback()
        {
            this$0 = DisplayManagerGlobal.this;
            super();
        }

        DisplayManagerCallback(DisplayManagerCallback displaymanagercallback)
        {
            this();
        }
    }

    private static final class VirtualDisplayCallback extends IVirtualDisplayCallback.Stub
    {

        public void onPaused()
        {
            if(mDelegate != null)
                mDelegate.sendEmptyMessage(0);
        }

        public void onResumed()
        {
            if(mDelegate != null)
                mDelegate.sendEmptyMessage(1);
        }

        public void onStopped()
        {
            if(mDelegate != null)
                mDelegate.sendEmptyMessage(2);
        }

        private VirtualDisplayCallbackDelegate mDelegate;

        public VirtualDisplayCallback(VirtualDisplay.Callback callback, Handler handler)
        {
            if(callback != null)
                mDelegate = new VirtualDisplayCallbackDelegate(callback, handler);
        }
    }

    private static final class VirtualDisplayCallbackDelegate extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 0 2: default 32
        //                       0 33
        //                       1 43
        //                       2 53;
               goto _L1 _L2 _L3 _L4
_L1:
            return;
_L2:
            mCallback.onPaused();
            continue; /* Loop/switch isn't completed */
_L3:
            mCallback.onResumed();
            continue; /* Loop/switch isn't completed */
_L4:
            mCallback.onStopped();
            if(true) goto _L1; else goto _L5
_L5:
        }

        public static final int MSG_DISPLAY_PAUSED = 0;
        public static final int MSG_DISPLAY_RESUMED = 1;
        public static final int MSG_DISPLAY_STOPPED = 2;
        private final VirtualDisplay.Callback mCallback;

        public VirtualDisplayCallbackDelegate(VirtualDisplay.Callback callback, Handler handler)
        {
            if(handler != null)
                handler = handler.getLooper();
            else
                handler = Looper.myLooper();
            super(handler, null, true);
            mCallback = callback;
        }
    }


    static void _2D_wrap0(DisplayManagerGlobal displaymanagerglobal, int i, int j)
    {
        displaymanagerglobal.handleDisplayEvent(i, j);
    }

    private DisplayManagerGlobal(IDisplayManager idisplaymanager)
    {
        mDm = idisplaymanager;
    }

    private int findDisplayListenerLocked(DisplayManager.DisplayListener displaylistener)
    {
        int i = mDisplayListeners.size();
        for(int j = 0; j < i; j++)
            if(((DisplayListenerDelegate)mDisplayListeners.get(j)).mListener == displaylistener)
                return j;

        return -1;
    }

    public static DisplayManagerGlobal getInstance()
    {
        android/hardware/display/DisplayManagerGlobal;
        JVM INSTR monitorenter ;
        Object obj;
        if(sInstance != null)
            break MISSING_BLOCK_LABEL_35;
        obj = ServiceManager.getService("display");
        if(obj == null)
            break MISSING_BLOCK_LABEL_35;
        DisplayManagerGlobal displaymanagerglobal = JVM INSTR new #2   <Class DisplayManagerGlobal>;
        displaymanagerglobal.DisplayManagerGlobal(IDisplayManager.Stub.asInterface(((android.os.IBinder) (obj))));
        sInstance = displaymanagerglobal;
        obj = sInstance;
        android/hardware/display/DisplayManagerGlobal;
        JVM INSTR monitorexit ;
        return ((DisplayManagerGlobal) (obj));
        Exception exception;
        exception;
        throw exception;
    }

    private void handleDisplayEvent(int i, int j)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int k = mDisplayListeners.size();
        int l = 0;
_L2:
        if(l >= k)
            break; /* Loop/switch isn't completed */
        ((DisplayListenerDelegate)mDisplayListeners.get(l)).sendDisplayEvent(i, j);
        l++;
        if(true) goto _L2; else goto _L1
_L1:
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void registerCallbackIfNeededLocked()
    {
        if(mCallback != null)
            break MISSING_BLOCK_LABEL_33;
        mCallback = new DisplayManagerCallback(null);
        mDm.registerCallback(mCallback);
        return;
        RemoteException remoteexception;
        remoteexception;
        throw remoteexception.rethrowFromSystemServer();
    }

    public void connectWifiDisplay(String s)
    {
        if(s == null)
            throw new IllegalArgumentException("deviceAddress must not be null");
        try
        {
            mDm.connectWifiDisplay(s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public VirtualDisplay createVirtualDisplay(Context context, MediaProjection mediaprojection, String s, int i, int j, int k, Surface surface, 
            int l, VirtualDisplay.Callback callback, Handler handler, String s1)
    {
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException("name must be non-null and non-empty");
        while(i <= 0 || j <= 0 || k <= 0) 
            throw new IllegalArgumentException("width, height, and densityDpi must be greater than 0");
        callback = new VirtualDisplayCallback(callback, handler);
        if(mediaprojection != null)
            mediaprojection = mediaprojection.getProjection();
        else
            mediaprojection = null;
        try
        {
            i = mDm.createVirtualDisplay(callback, mediaprojection, context.getPackageName(), s, i, j, k, surface, l, s1);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw context.rethrowFromSystemServer();
        }
        if(i < 0)
        {
            Log.e("DisplayManager", (new StringBuilder()).append("Could not create virtual display: ").append(s).toString());
            return null;
        }
        context = getRealDisplay(i);
        if(context == null)
        {
            Log.wtf("DisplayManager", (new StringBuilder()).append("Could not obtain display info for newly created virtual display: ").append(s).toString());
            try
            {
                mDm.releaseVirtualDisplay(callback);
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                throw context.rethrowFromSystemServer();
            }
            return null;
        } else
        {
            return new VirtualDisplay(this, context, callback, surface);
        }
    }

    public void disconnectWifiDisplay()
    {
        try
        {
            mDm.disconnectWifiDisplay();
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void forgetWifiDisplay(String s)
    {
        if(s == null)
            throw new IllegalArgumentException("deviceAddress must not be null");
        try
        {
            mDm.forgetWifiDisplay(s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public Display getCompatibleDisplay(int i, Resources resources)
    {
        DisplayInfo displayinfo = getDisplayInfo(i);
        if(displayinfo == null)
            return null;
        else
            return new Display(this, i, displayinfo, resources);
    }

    public Display getCompatibleDisplay(int i, DisplayAdjustments displayadjustments)
    {
        DisplayInfo displayinfo = getDisplayInfo(i);
        if(displayinfo == null)
            return null;
        else
            return new Display(this, i, displayinfo, displayadjustments);
    }

    public int[] getDisplayIds()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int ai[];
        ai = mDm.getDisplayIds();
        registerCallbackIfNeededLocked();
        obj;
        JVM INSTR monitorexit ;
        return ai;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        RemoteException remoteexception;
        remoteexception;
        throw remoteexception.rethrowFromSystemServer();
    }

    public DisplayInfo getDisplayInfo(int i)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        DisplayInfo displayinfo = mDm.getDisplayInfo(i);
        if(displayinfo != null)
            break MISSING_BLOCK_LABEL_26;
        obj;
        JVM INSTR monitorexit ;
        return null;
        registerCallbackIfNeededLocked();
        obj;
        JVM INSTR monitorexit ;
        return displayinfo;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        RemoteException remoteexception;
        remoteexception;
        throw remoteexception.rethrowFromSystemServer();
    }

    public Display getRealDisplay(int i)
    {
        return getCompatibleDisplay(i, DisplayAdjustments.DEFAULT_DISPLAY_ADJUSTMENTS);
    }

    public Point getStableDisplaySize()
    {
        Point point;
        try
        {
            point = mDm.getStableDisplaySize();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return point;
    }

    public WifiDisplayStatus getWifiDisplayStatus()
    {
        WifiDisplayStatus wifidisplaystatus;
        try
        {
            wifidisplaystatus = mDm.getWifiDisplayStatus();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return wifidisplaystatus;
    }

    public void pauseWifiDisplay()
    {
        try
        {
            mDm.pauseWifiDisplay();
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void registerDisplayListener(DisplayManager.DisplayListener displaylistener, Handler handler)
    {
        if(displaylistener == null)
            throw new IllegalArgumentException("listener must not be null");
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(findDisplayListenerLocked(displaylistener) < 0)
        {
            ArrayList arraylist = mDisplayListeners;
            DisplayListenerDelegate displaylistenerdelegate = JVM INSTR new #6   <Class DisplayManagerGlobal$DisplayListenerDelegate>;
            displaylistenerdelegate.DisplayListenerDelegate(displaylistener, handler);
            arraylist.add(displaylistenerdelegate);
            registerCallbackIfNeededLocked();
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        displaylistener;
        throw displaylistener;
    }

    public void releaseVirtualDisplay(IVirtualDisplayCallback ivirtualdisplaycallback)
    {
        try
        {
            mDm.releaseVirtualDisplay(ivirtualdisplaycallback);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(IVirtualDisplayCallback ivirtualdisplaycallback)
        {
            throw ivirtualdisplaycallback.rethrowFromSystemServer();
        }
    }

    public void renameWifiDisplay(String s, String s1)
    {
        if(s == null)
            throw new IllegalArgumentException("deviceAddress must not be null");
        try
        {
            mDm.renameWifiDisplay(s, s1);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void requestColorMode(int i, int j)
    {
        try
        {
            mDm.requestColorMode(i, j);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void resizeVirtualDisplay(IVirtualDisplayCallback ivirtualdisplaycallback, int i, int j, int k)
    {
        try
        {
            mDm.resizeVirtualDisplay(ivirtualdisplaycallback, i, j, k);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(IVirtualDisplayCallback ivirtualdisplaycallback)
        {
            throw ivirtualdisplaycallback.rethrowFromSystemServer();
        }
    }

    public void resumeWifiDisplay()
    {
        try
        {
            mDm.resumeWifiDisplay();
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void setVirtualDisplaySurface(IVirtualDisplayCallback ivirtualdisplaycallback, Surface surface)
    {
        try
        {
            mDm.setVirtualDisplaySurface(ivirtualdisplaycallback, surface);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(IVirtualDisplayCallback ivirtualdisplaycallback)
        {
            throw ivirtualdisplaycallback.rethrowFromSystemServer();
        }
    }

    public void startWifiDisplayScan()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        i = mWifiDisplayScanNestCount;
        mWifiDisplayScanNestCount = i + 1;
        if(i != 0)
            break MISSING_BLOCK_LABEL_36;
        registerCallbackIfNeededLocked();
        mDm.startWifiDisplayScan();
        obj;
        JVM INSTR monitorexit ;
        return;
        Object obj1;
        obj1;
        throw ((RemoteException) (obj1)).rethrowFromSystemServer();
        obj1;
        obj;
        JVM INSTR monitorexit ;
        throw obj1;
    }

    public void stopWifiDisplayScan()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        i = mWifiDisplayScanNestCount - 1;
        mWifiDisplayScanNestCount = i;
        if(i != 0) goto _L2; else goto _L1
_L1:
        mDm.stopWifiDisplayScan();
_L4:
        obj;
        JVM INSTR monitorexit ;
        return;
        Object obj1;
        obj1;
        throw ((RemoteException) (obj1)).rethrowFromSystemServer();
        obj1;
        obj;
        JVM INSTR monitorexit ;
        throw obj1;
_L2:
        if(mWifiDisplayScanNestCount >= 0) goto _L4; else goto _L3
_L3:
        StringBuilder stringbuilder = JVM INSTR new #171 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.wtf("DisplayManager", stringbuilder.append("Wifi display scan nest count became negative: ").append(mWifiDisplayScanNestCount).toString());
        mWifiDisplayScanNestCount = 0;
          goto _L4
    }

    public void unregisterDisplayListener(DisplayManager.DisplayListener displaylistener)
    {
        if(displaylistener == null)
            throw new IllegalArgumentException("listener must not be null");
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i = findDisplayListenerLocked(displaylistener);
        if(i < 0)
            break MISSING_BLOCK_LABEL_55;
        ((DisplayListenerDelegate)mDisplayListeners.get(i)).clearEvents();
        mDisplayListeners.remove(i);
        obj;
        JVM INSTR monitorexit ;
        return;
        displaylistener;
        throw displaylistener;
    }

    private static final boolean DEBUG = false;
    public static final int EVENT_DISPLAY_ADDED = 1;
    public static final int EVENT_DISPLAY_CHANGED = 2;
    public static final int EVENT_DISPLAY_REMOVED = 3;
    private static final String TAG = "DisplayManager";
    private static final boolean USE_CACHE = false;
    private static DisplayManagerGlobal sInstance;
    private DisplayManagerCallback mCallback;
    private int mDisplayIdCache[];
    private final SparseArray mDisplayInfoCache = new SparseArray();
    private final ArrayList mDisplayListeners = new ArrayList();
    private final IDisplayManager mDm;
    private final Object mLock = new Object();
    private int mWifiDisplayScanNestCount;
}
