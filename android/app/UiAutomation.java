// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.accessibilityservice.*;
import android.graphics.*;
import android.hardware.display.DisplayManagerGlobal;
import android.os.*;
import android.util.Log;
import android.view.*;
import android.view.accessibility.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;
import libcore.io.IoUtils;

// Referenced classes of package android.app:
//            IUiAutomationConnection, ActivityManager, IActivityManager

public final class UiAutomation
{
    public static interface AccessibilityEventFilter
    {

        public abstract boolean accept(AccessibilityEvent accessibilityevent);
    }

    private class IAccessibilityServiceClientImpl extends android.accessibilityservice.AccessibilityService.IAccessibilityServiceClientWrapper
    {

        final UiAutomation this$0;

        public IAccessibilityServiceClientImpl(Looper looper)
        {
            this$0 = UiAutomation.this;
            super(null, looper, new _cls1(UiAutomation.this));
        }
    }

    public static interface OnAccessibilityEventListener
    {

        public abstract void onAccessibilityEvent(AccessibilityEvent accessibilityevent);
    }


    static ArrayList _2D_get0(UiAutomation uiautomation)
    {
        return uiautomation.mEventQueue;
    }

    static Object _2D_get1(UiAutomation uiautomation)
    {
        return uiautomation.mLock;
    }

    static OnAccessibilityEventListener _2D_get2(UiAutomation uiautomation)
    {
        return uiautomation.mOnAccessibilityEventListener;
    }

    static boolean _2D_get3(UiAutomation uiautomation)
    {
        return uiautomation.mWaitingForEventDelivery;
    }

    static int _2D_set0(UiAutomation uiautomation, int i)
    {
        uiautomation.mConnectionId = i;
        return i;
    }

    static long _2D_set1(UiAutomation uiautomation, long l)
    {
        uiautomation.mLastEventTimeMillis = l;
        return l;
    }

    public UiAutomation(Looper looper, IUiAutomationConnection iuiautomationconnection)
    {
        mConnectionId = -1;
        if(looper == null)
            throw new IllegalArgumentException("Looper cannot be null!");
        if(iuiautomationconnection == null)
        {
            throw new IllegalArgumentException("Connection cannot be null!");
        } else
        {
            mUiAutomationConnection = iuiautomationconnection;
            mClient = new IAccessibilityServiceClientImpl(looper);
            return;
        }
    }

    private static float getDegreesForRotation(int i)
    {
        switch(i)
        {
        default:
            return 0.0F;

        case 1: // '\001'
            return 270F;

        case 2: // '\002'
            return 180F;

        case 3: // '\003'
            return 90F;
        }
    }

    private boolean isConnectedLocked()
    {
        boolean flag;
        if(mConnectionId != -1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void throwIfConnectedLocked()
    {
        if(mConnectionId != -1)
            throw new IllegalStateException("UiAutomation not connected!");
        else
            return;
    }

    private void throwIfNotConnectedLocked()
    {
        if(!isConnectedLocked())
            throw new IllegalStateException("UiAutomation not connected!");
        else
            return;
    }

    public void clearWindowAnimationFrameStats()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        throwIfNotConnectedLocked();
        obj;
        JVM INSTR monitorexit ;
        mUiAutomationConnection.clearWindowAnimationFrameStats();
_L1:
        return;
        Exception exception;
        exception;
        throw exception;
        RemoteException remoteexception;
        remoteexception;
        Log.e(LOG_TAG, "Error clearing window animation frame stats!", remoteexception);
          goto _L1
    }

    public boolean clearWindowContentFrameStats(int i)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        throwIfNotConnectedLocked();
        obj;
        JVM INSTR monitorexit ;
        boolean flag;
        Exception exception;
        try
        {
            flag = mUiAutomationConnection.clearWindowContentFrameStats(i);
        }
        catch(RemoteException remoteexception)
        {
            Log.e(LOG_TAG, "Error clearing window content frame stats!", remoteexception);
            return false;
        }
        return flag;
        exception;
        throw exception;
    }

    public void connect()
    {
        connect(0);
    }

    public void connect(int i)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag;
        throwIfConnectedLocked();
        flag = mIsConnecting;
        if(!flag)
            break MISSING_BLOCK_LABEL_23;
        obj;
        JVM INSTR monitorexit ;
        return;
        mIsConnecting = true;
        obj;
        JVM INSTR monitorexit ;
        long l;
        Exception exception;
        try
        {
            mUiAutomationConnection.connect(mClient, i);
            mFlags = i;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            throw new RuntimeException("Error while connecting UiAutomation", ((Throwable) (obj)));
        }
        obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        l = SystemClock.uptimeMillis();
_L2:
        flag = isConnectedLocked();
        if(!flag)
            break MISSING_BLOCK_LABEL_97;
        mIsConnecting = false;
        obj;
        JVM INSTR monitorexit ;
        return;
        exception;
        throw exception;
        long l1 = 5000L - (SystemClock.uptimeMillis() - l);
        if(l1 > 0L)
            break MISSING_BLOCK_LABEL_148;
        RuntimeException runtimeexception = JVM INSTR new #175 <Class RuntimeException>;
        runtimeexception.RuntimeException("Error while connecting UiAutomation");
        throw runtimeexception;
        runtimeexception;
        mIsConnecting = false;
        throw runtimeexception;
        runtimeexception;
        obj;
        JVM INSTR monitorexit ;
        throw runtimeexception;
        try
        {
            mLock.wait(l1);
        }
        catch(InterruptedException interruptedexception) { }
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void destroy()
    {
        disconnect();
        mIsDestroyed = true;
    }

    public void disconnect()
    {
        synchronized(mLock)
        {
            if(mIsConnecting)
            {
                IllegalStateException illegalstateexception = JVM INSTR new #123 <Class IllegalStateException>;
                illegalstateexception.IllegalStateException("Cannot call disconnect() while connecting!");
                throw illegalstateexception;
            }
            break MISSING_BLOCK_LABEL_31;
        }
        throwIfNotConnectedLocked();
        mConnectionId = -1;
        obj;
        JVM INSTR monitorexit ;
        try
        {
            mUiAutomationConnection.disconnect();
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException("Error while disconnecting UiAutomation", remoteexception);
        }
    }

    public AccessibilityEvent executeAndWaitForEvent(Runnable runnable, AccessibilityEventFilter accessibilityeventfilter, long l)
        throws TimeoutException
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        throwIfNotConnectedLocked();
        mEventQueue.clear();
        mWaitingForEventDelivery = true;
        obj;
        JVM INSTR monitorexit ;
        long l1;
        l1 = SystemClock.uptimeMillis();
        runnable.run();
        long l2 = SystemClock.uptimeMillis();
_L2:
        obj = JVM INSTR new #96  <Class ArrayList>;
        ((ArrayList) (obj)).ArrayList();
        runnable = ((Runnable) (mLock));
        runnable;
        JVM INSTR monitorenter ;
        ((List) (obj)).addAll(mEventQueue);
        mEventQueue.clear();
        runnable;
        JVM INSTR monitorexit ;
_L1:
        boolean flag;
        do
        {
            if(((List) (obj)).isEmpty())
                break MISSING_BLOCK_LABEL_212;
            runnable = (AccessibilityEvent)((List) (obj)).remove(0);
        } while(runnable.getEventTime() < l1);
        flag = accessibilityeventfilter.accept(runnable);
        if(!flag)
            break MISSING_BLOCK_LABEL_205;
        accessibilityeventfilter = ((AccessibilityEventFilter) (mLock));
        accessibilityeventfilter;
        JVM INSTR monitorenter ;
        mWaitingForEventDelivery = false;
        mEventQueue.clear();
        mLock.notifyAll();
        accessibilityeventfilter;
        JVM INSTR monitorexit ;
        return runnable;
        runnable;
        throw runnable;
        accessibilityeventfilter;
        runnable;
        JVM INSTR monitorexit ;
        throw accessibilityeventfilter;
        accessibilityeventfilter;
        runnable = ((Runnable) (mLock));
        runnable;
        JVM INSTR monitorenter ;
        mWaitingForEventDelivery = false;
        mEventQueue.clear();
        mLock.notifyAll();
        runnable;
        JVM INSTR monitorexit ;
        throw accessibilityeventfilter;
        runnable;
        throw runnable;
        runnable.recycle();
          goto _L1
        long l3 = l - (SystemClock.uptimeMillis() - l2);
        if(l3 > 0L)
            break MISSING_BLOCK_LABEL_265;
        runnable = JVM INSTR new #200 <Class TimeoutException>;
        accessibilityeventfilter = JVM INSTR new #238 <Class StringBuilder>;
        accessibilityeventfilter.StringBuilder();
        runnable.TimeoutException(accessibilityeventfilter.append("Expected event not received within: ").append(l).append(" ms.").toString());
        throw runnable;
        runnable = ((Runnable) (mLock));
        runnable;
        JVM INSTR monitorenter ;
        flag = mEventQueue.isEmpty();
        if(!flag)
            break MISSING_BLOCK_LABEL_295;
        try
        {
            mLock.wait(l3);
        }
        catch(InterruptedException interruptedexception) { }
        runnable;
        JVM INSTR monitorexit ;
          goto _L2
        accessibilityeventfilter;
        runnable;
        JVM INSTR monitorexit ;
        throw accessibilityeventfilter;
        accessibilityeventfilter;
        runnable;
        JVM INSTR monitorexit ;
        throw accessibilityeventfilter;
    }

    public ParcelFileDescriptor executeShellCommand(String s)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        throwIfNotConnectedLocked();
        obj;
        JVM INSTR monitorexit ;
        ParcelFileDescriptor parcelfiledescriptor;
        ParcelFileDescriptor parcelfiledescriptor1;
        ParcelFileDescriptor parcelfiledescriptor2;
        ParcelFileDescriptor parcelfiledescriptor3;
        parcelfiledescriptor = null;
        parcelfiledescriptor1 = null;
        parcelfiledescriptor2 = null;
        obj = null;
        parcelfiledescriptor3 = null;
        ParcelFileDescriptor aparcelfiledescriptor[] = ParcelFileDescriptor.createPipe();
        ParcelFileDescriptor parcelfiledescriptor4;
        ParcelFileDescriptor parcelfiledescriptor5;
        parcelfiledescriptor5 = aparcelfiledescriptor[0];
        parcelfiledescriptor4 = aparcelfiledescriptor[1];
        parcelfiledescriptor3 = parcelfiledescriptor4;
        parcelfiledescriptor1 = parcelfiledescriptor5;
        parcelfiledescriptor2 = parcelfiledescriptor4;
        parcelfiledescriptor = parcelfiledescriptor5;
        obj = parcelfiledescriptor4;
        mUiAutomationConnection.executeShellCommand(s, parcelfiledescriptor4, null);
        IoUtils.closeQuietly(parcelfiledescriptor4);
        parcelfiledescriptor1 = parcelfiledescriptor5;
_L1:
        return parcelfiledescriptor1;
        s;
        throw s;
        s;
        obj = parcelfiledescriptor3;
        Log.e(LOG_TAG, "Error executing shell command!", s);
        IoUtils.closeQuietly(parcelfiledescriptor3);
          goto _L1
        s;
        obj = parcelfiledescriptor2;
        Log.e(LOG_TAG, "Error executing shell command!", s);
        IoUtils.closeQuietly(parcelfiledescriptor2);
        parcelfiledescriptor1 = parcelfiledescriptor;
          goto _L1
        s;
        IoUtils.closeQuietly(((AutoCloseable) (obj)));
        throw s;
    }

    public ParcelFileDescriptor[] executeShellCommandRw(String s)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        throwIfNotConnectedLocked();
        obj;
        JVM INSTR monitorexit ;
        ParcelFileDescriptor parcelfiledescriptor;
        ParcelFileDescriptor parcelfiledescriptor1;
        ParcelFileDescriptor parcelfiledescriptor2;
        ParcelFileDescriptor parcelfiledescriptor3;
        ParcelFileDescriptor parcelfiledescriptor4;
        Object obj1;
        Object obj2;
        ParcelFileDescriptor aparcelfiledescriptor[];
        Object obj3;
        ParcelFileDescriptor parcelfiledescriptor6;
        ParcelFileDescriptor parcelfiledescriptor7;
        ParcelFileDescriptor parcelfiledescriptor8;
        ParcelFileDescriptor parcelfiledescriptor9;
        ParcelFileDescriptor parcelfiledescriptor10;
        parcelfiledescriptor = null;
        parcelfiledescriptor1 = null;
        parcelfiledescriptor2 = null;
        parcelfiledescriptor3 = null;
        parcelfiledescriptor4 = null;
        obj1 = null;
        obj2 = null;
        aparcelfiledescriptor = null;
        obj3 = null;
        parcelfiledescriptor6 = null;
        parcelfiledescriptor7 = parcelfiledescriptor6;
        parcelfiledescriptor8 = aparcelfiledescriptor;
        parcelfiledescriptor9 = obj3;
        parcelfiledescriptor10 = obj1;
        obj = obj2;
        ParcelFileDescriptor aparcelfiledescriptor1[] = ParcelFileDescriptor.createPipe();
        ParcelFileDescriptor parcelfiledescriptor11;
        ParcelFileDescriptor parcelfiledescriptor12;
        parcelfiledescriptor12 = aparcelfiledescriptor1[0];
        parcelfiledescriptor11 = aparcelfiledescriptor1[1];
        parcelfiledescriptor4 = parcelfiledescriptor11;
        parcelfiledescriptor7 = parcelfiledescriptor6;
        parcelfiledescriptor1 = parcelfiledescriptor12;
        parcelfiledescriptor8 = aparcelfiledescriptor;
        parcelfiledescriptor2 = parcelfiledescriptor11;
        parcelfiledescriptor9 = obj3;
        parcelfiledescriptor = parcelfiledescriptor12;
        parcelfiledescriptor10 = obj1;
        parcelfiledescriptor3 = parcelfiledescriptor11;
        obj = obj2;
        aparcelfiledescriptor = ParcelFileDescriptor.createPipe();
        ParcelFileDescriptor parcelfiledescriptor5;
        parcelfiledescriptor6 = aparcelfiledescriptor[0];
        parcelfiledescriptor5 = aparcelfiledescriptor[1];
        parcelfiledescriptor4 = parcelfiledescriptor11;
        parcelfiledescriptor7 = parcelfiledescriptor5;
        parcelfiledescriptor1 = parcelfiledescriptor12;
        parcelfiledescriptor8 = parcelfiledescriptor6;
        parcelfiledescriptor2 = parcelfiledescriptor11;
        parcelfiledescriptor9 = parcelfiledescriptor5;
        parcelfiledescriptor = parcelfiledescriptor12;
        parcelfiledescriptor10 = parcelfiledescriptor6;
        parcelfiledescriptor3 = parcelfiledescriptor11;
        obj = parcelfiledescriptor6;
        mUiAutomationConnection.executeShellCommand(s, parcelfiledescriptor11, parcelfiledescriptor6);
        IoUtils.closeQuietly(parcelfiledescriptor11);
        IoUtils.closeQuietly(parcelfiledescriptor6);
        parcelfiledescriptor1 = parcelfiledescriptor12;
        parcelfiledescriptor7 = parcelfiledescriptor5;
_L1:
        return (new ParcelFileDescriptor[] {
            parcelfiledescriptor1, parcelfiledescriptor7
        });
        s;
        throw s;
        s;
        parcelfiledescriptor3 = parcelfiledescriptor4;
        obj = parcelfiledescriptor8;
        Log.e(LOG_TAG, "Error executing shell command!", s);
        IoUtils.closeQuietly(parcelfiledescriptor4);
        IoUtils.closeQuietly(parcelfiledescriptor8);
          goto _L1
        s;
        parcelfiledescriptor3 = parcelfiledescriptor2;
        obj = parcelfiledescriptor10;
        Log.e(LOG_TAG, "Error executing shell command!", s);
        IoUtils.closeQuietly(parcelfiledescriptor2);
        IoUtils.closeQuietly(parcelfiledescriptor10);
        parcelfiledescriptor7 = parcelfiledescriptor9;
        parcelfiledescriptor1 = parcelfiledescriptor;
          goto _L1
        s;
        IoUtils.closeQuietly(parcelfiledescriptor3);
        IoUtils.closeQuietly(((AutoCloseable) (obj)));
        throw s;
    }

    public AccessibilityNodeInfo findFocus(int i)
    {
        return AccessibilityInteractionClient.getInstance().findFocus(mConnectionId, -2, AccessibilityNodeInfo.ROOT_NODE_ID, i);
    }

    public int getConnectionId()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        throwIfNotConnectedLocked();
        i = mConnectionId;
        obj;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public int getFlags()
    {
        return mFlags;
    }

    public AccessibilityNodeInfo getRootInActiveWindow()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        throwIfNotConnectedLocked();
        i = mConnectionId;
        obj;
        JVM INSTR monitorexit ;
        return AccessibilityInteractionClient.getInstance().getRootInActiveWindow(i);
        Exception exception;
        exception;
        throw exception;
    }

    public final AccessibilityServiceInfo getServiceInfo()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        IAccessibilityServiceConnection iaccessibilityserviceconnection;
        throwIfNotConnectedLocked();
        iaccessibilityserviceconnection = AccessibilityInteractionClient.getInstance().getConnection(mConnectionId);
        obj;
        JVM INSTR monitorexit ;
        if(iaccessibilityserviceconnection == null)
            break MISSING_BLOCK_LABEL_54;
        obj = iaccessibilityserviceconnection.getServiceInfo();
        return ((AccessibilityServiceInfo) (obj));
        Exception exception;
        exception;
        throw exception;
        RemoteException remoteexception;
        remoteexception;
        Log.w(LOG_TAG, "Error while getting AccessibilityServiceInfo", remoteexception);
        return null;
    }

    public WindowAnimationFrameStats getWindowAnimationFrameStats()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        throwIfNotConnectedLocked();
        obj;
        JVM INSTR monitorexit ;
        WindowAnimationFrameStats windowanimationframestats;
        Exception exception;
        try
        {
            windowanimationframestats = mUiAutomationConnection.getWindowAnimationFrameStats();
        }
        catch(RemoteException remoteexception)
        {
            Log.e(LOG_TAG, "Error getting window animation frame stats!", remoteexception);
            return null;
        }
        return windowanimationframestats;
        exception;
        throw exception;
    }

    public WindowContentFrameStats getWindowContentFrameStats(int i)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        throwIfNotConnectedLocked();
        obj;
        JVM INSTR monitorexit ;
        WindowContentFrameStats windowcontentframestats;
        Exception exception;
        try
        {
            windowcontentframestats = mUiAutomationConnection.getWindowContentFrameStats(i);
        }
        catch(RemoteException remoteexception)
        {
            Log.e(LOG_TAG, "Error getting window content frame stats!", remoteexception);
            return null;
        }
        return windowcontentframestats;
        exception;
        throw exception;
    }

    public List getWindows()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        throwIfNotConnectedLocked();
        i = mConnectionId;
        obj;
        JVM INSTR monitorexit ;
        return AccessibilityInteractionClient.getInstance().getWindows(i);
        Exception exception;
        exception;
        throw exception;
    }

    public boolean grantRuntimePermission(String s, String s1, UserHandle userhandle)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        throwIfNotConnectedLocked();
        obj;
        JVM INSTR monitorexit ;
        try
        {
            mUiAutomationConnection.grantRuntimePermission(s, s1, userhandle.getIdentifier());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.e(LOG_TAG, "Error granting runtime permission", s);
            return false;
        }
        return true;
        s;
        throw s;
    }

    public boolean injectInputEvent(InputEvent inputevent, boolean flag)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        throwIfNotConnectedLocked();
        obj;
        JVM INSTR monitorexit ;
        try
        {
            flag = mUiAutomationConnection.injectInputEvent(inputevent, flag);
        }
        // Misplaced declaration of an exception variable
        catch(InputEvent inputevent)
        {
            Log.e(LOG_TAG, "Error while injecting input event!", inputevent);
            return false;
        }
        return flag;
        inputevent;
        throw inputevent;
    }

    public boolean isDestroyed()
    {
        return mIsDestroyed;
    }

    public final boolean performGlobalAction(int i)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        IAccessibilityServiceConnection iaccessibilityserviceconnection;
        throwIfNotConnectedLocked();
        iaccessibilityserviceconnection = AccessibilityInteractionClient.getInstance().getConnection(mConnectionId);
        obj;
        JVM INSTR monitorexit ;
        if(iaccessibilityserviceconnection == null)
            break MISSING_BLOCK_LABEL_57;
        boolean flag = iaccessibilityserviceconnection.performGlobalAction(i);
        return flag;
        Exception exception;
        exception;
        throw exception;
        RemoteException remoteexception;
        remoteexception;
        Log.w(LOG_TAG, "Error while calling performGlobalAction", remoteexception);
        return false;
    }

    public boolean revokeRuntimePermission(String s, String s1, UserHandle userhandle)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        throwIfNotConnectedLocked();
        obj;
        JVM INSTR monitorexit ;
        try
        {
            mUiAutomationConnection.revokeRuntimePermission(s, s1, userhandle.getIdentifier());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.e(LOG_TAG, "Error revoking runtime permission", s);
            return false;
        }
        return true;
        s;
        throw s;
    }

    public void setOnAccessibilityEventListener(OnAccessibilityEventListener onaccessibilityeventlistener)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        mOnAccessibilityEventListener = onaccessibilityeventlistener;
        obj;
        JVM INSTR monitorexit ;
        return;
        onaccessibilityeventlistener;
        throw onaccessibilityeventlistener;
    }

    public boolean setRotation(int i)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        throwIfNotConnectedLocked();
        obj;
        JVM INSTR monitorexit ;
        switch(i)
        {
        default:
            throw new IllegalArgumentException("Invalid rotation.");

        case -2: 
        case -1: 
        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
            break;
        }
        break MISSING_BLOCK_LABEL_68;
        Exception exception;
        exception;
        throw exception;
        try
        {
            mUiAutomationConnection.setRotation(i);
        }
        catch(RemoteException remoteexception)
        {
            Log.e(LOG_TAG, "Error while setting rotation!", remoteexception);
            return false;
        }
        return true;
    }

    public void setRunAsMonkey(boolean flag)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        throwIfNotConnectedLocked();
        obj;
        JVM INSTR monitorexit ;
        ActivityManager.getService().setUserIsMonkey(flag);
_L1:
        return;
        Object obj1;
        obj1;
        throw obj1;
        obj1;
        Log.e(LOG_TAG, "Error while setting run as monkey!", ((Throwable) (obj1)));
          goto _L1
    }

    public final void setServiceInfo(AccessibilityServiceInfo accessibilityserviceinfo)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        IAccessibilityServiceConnection iaccessibilityserviceconnection;
        throwIfNotConnectedLocked();
        AccessibilityInteractionClient.getInstance().clearCache();
        iaccessibilityserviceconnection = AccessibilityInteractionClient.getInstance().getConnection(mConnectionId);
        obj;
        JVM INSTR monitorexit ;
        if(iaccessibilityserviceconnection == null)
            break MISSING_BLOCK_LABEL_41;
        iaccessibilityserviceconnection.setServiceInfo(accessibilityserviceinfo);
_L1:
        return;
        accessibilityserviceinfo;
        throw accessibilityserviceinfo;
        accessibilityserviceinfo;
        Log.w(LOG_TAG, "Error while setting AccessibilityServiceInfo", accessibilityserviceinfo);
          goto _L1
    }

    public Bitmap takeScreenshot()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        throwIfNotConnectedLocked();
        obj;
        JVM INSTR monitorexit ;
        int i;
        int j;
        int k;
        obj = DisplayManagerGlobal.getInstance().getRealDisplay(0);
        Point point = new Point();
        ((Display) (obj)).getRealSize(point);
        i = point.x;
        j = point.y;
        k = ((Display) (obj)).getRotation();
        k;
        JVM INSTR tableswitch 0 3: default 84
    //                   0 118
    //                   1 149
    //                   2 161
    //                   3 173;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append("Invalid rotation: ").append(k).toString());
        Exception exception;
        exception;
        throw exception;
_L2:
        float f;
        float f1;
        f = i;
        f1 = j;
_L7:
        try
        {
            obj = mUiAutomationConnection.takeScreenshot((int)f, (int)f1);
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            Log.e(LOG_TAG, "Error while taking screnshot!", ((Throwable) (obj)));
            return null;
        }
        if(obj == null)
            return null;
        break; /* Loop/switch isn't completed */
_L3:
        f = j;
        f1 = i;
        continue; /* Loop/switch isn't completed */
_L4:
        f = i;
        f1 = j;
        continue; /* Loop/switch isn't completed */
_L5:
        f = j;
        f1 = i;
        if(true) goto _L7; else goto _L6
_L6:
        Object obj1 = obj;
        if(k != 0)
        {
            obj1 = Bitmap.createBitmap(i, j, android.graphics.Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(((Bitmap) (obj1)));
            canvas.translate(((Bitmap) (obj1)).getWidth() / 2, ((Bitmap) (obj1)).getHeight() / 2);
            canvas.rotate(getDegreesForRotation(k));
            canvas.translate(-f / 2.0F, -f1 / 2.0F);
            canvas.drawBitmap(((Bitmap) (obj)), 0.0F, 0.0F, null);
            canvas.setBitmap(null);
            ((Bitmap) (obj)).recycle();
        }
        ((Bitmap) (obj1)).setHasAlpha(false);
        return ((Bitmap) (obj1));
    }

    public void waitForIdle(long l, long l1)
        throws TimeoutException
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        long l2;
        throwIfNotConnectedLocked();
        l2 = SystemClock.uptimeMillis();
        if(mLastEventTimeMillis <= 0L)
            mLastEventTimeMillis = l2;
_L1:
        long l3 = SystemClock.uptimeMillis();
        if(l1 - (l3 - l2) > 0L)
            break MISSING_BLOCK_LABEL_106;
        TimeoutException timeoutexception = JVM INSTR new #200 <Class TimeoutException>;
        StringBuilder stringbuilder = JVM INSTR new #238 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        timeoutexception.TimeoutException(stringbuilder.append("No idle state with idle timeout: ").append(l).append(" within global timeout: ").append(l1).toString());
        throw timeoutexception;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        long l4 = mLastEventTimeMillis;
        l4 = l - (l3 - l4);
        if(l4 <= 0L)
            return;
        try
        {
            mLock.wait(l4);
        }
        catch(InterruptedException interruptedexception) { }
          goto _L1
    }

    private static final int CONNECTION_ID_UNDEFINED = -1;
    private static final long CONNECT_TIMEOUT_MILLIS = 5000L;
    private static final boolean DEBUG = false;
    public static final int FLAG_DONT_SUPPRESS_ACCESSIBILITY_SERVICES = 1;
    private static final String LOG_TAG = android/app/UiAutomation.getSimpleName();
    public static final int ROTATION_FREEZE_0 = 0;
    public static final int ROTATION_FREEZE_180 = 2;
    public static final int ROTATION_FREEZE_270 = 3;
    public static final int ROTATION_FREEZE_90 = 1;
    public static final int ROTATION_FREEZE_CURRENT = -1;
    public static final int ROTATION_UNFREEZE = -2;
    private final IAccessibilityServiceClient mClient;
    private int mConnectionId;
    private final ArrayList mEventQueue = new ArrayList();
    private int mFlags;
    private boolean mIsConnecting;
    private boolean mIsDestroyed;
    private long mLastEventTimeMillis;
    private final Object mLock = new Object();
    private OnAccessibilityEventListener mOnAccessibilityEventListener;
    private final IUiAutomationConnection mUiAutomationConnection;
    private boolean mWaitingForEventDelivery;


    // Unreferenced inner class android/app/UiAutomation$IAccessibilityServiceClientImpl$1

/* anonymous class */
    static final class IAccessibilityServiceClientImpl._cls1
        implements android.accessibilityservice.AccessibilityService.Callbacks
    {

        public void init(int i, IBinder ibinder)
        {
            ibinder = ((IBinder) (UiAutomation._2D_get1(this$0)));
            ibinder;
            JVM INSTR monitorenter ;
            UiAutomation._2D_set0(this$0, i);
            UiAutomation._2D_get1(this$0).notifyAll();
            ibinder;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void onAccessibilityButtonAvailabilityChanged(boolean flag)
        {
        }

        public void onAccessibilityButtonClicked()
        {
        }

        public void onAccessibilityEvent(AccessibilityEvent accessibilityevent)
        {
            Object obj = UiAutomation._2D_get1(this$0);
            obj;
            JVM INSTR monitorenter ;
            UiAutomation._2D_set1(this$0, accessibilityevent.getEventTime());
            if(UiAutomation._2D_get3(this$0))
                UiAutomation._2D_get0(this$0).add(AccessibilityEvent.obtain(accessibilityevent));
            UiAutomation._2D_get1(this$0).notifyAll();
            obj;
            JVM INSTR monitorexit ;
            obj = UiAutomation._2D_get2(this$0);
            if(obj != null)
                ((OnAccessibilityEventListener) (obj)).onAccessibilityEvent(AccessibilityEvent.obtain(accessibilityevent));
            return;
            accessibilityevent;
            throw accessibilityevent;
        }

        public void onFingerprintCapturingGesturesChanged(boolean flag)
        {
        }

        public void onFingerprintGesture(int i)
        {
        }

        public boolean onGesture(int i)
        {
            return false;
        }

        public void onInterrupt()
        {
        }

        public boolean onKeyEvent(KeyEvent keyevent)
        {
            return false;
        }

        public void onMagnificationChanged(Region region, float f, float f1, float f2)
        {
        }

        public void onPerformGestureResult(int i, boolean flag)
        {
        }

        public void onServiceConnected()
        {
        }

        public void onSoftKeyboardShowModeChanged(int i)
        {
        }

        final UiAutomation val$this$0;

            
            {
                this$0 = uiautomation;
                super();
            }
    }

}
