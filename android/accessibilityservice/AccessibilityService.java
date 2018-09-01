// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.accessibilityservice;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ParceledListSlice;
import android.graphics.Region;
import android.os.*;
import android.util.*;
import android.view.*;
import android.view.accessibility.*;
import com.android.internal.os.HandlerCaller;
import com.android.internal.os.SomeArgs;
import java.util.List;

// Referenced classes of package android.accessibilityservice:
//            AccessibilityButtonController, FingerprintGestureController, IAccessibilityServiceConnection, AccessibilityServiceInfo, 
//            GestureDescription

public abstract class AccessibilityService extends Service
{
    public static interface Callbacks
    {

        public abstract void init(int i, IBinder ibinder);

        public abstract void onAccessibilityButtonAvailabilityChanged(boolean flag);

        public abstract void onAccessibilityButtonClicked();

        public abstract void onAccessibilityEvent(AccessibilityEvent accessibilityevent);

        public abstract void onFingerprintCapturingGesturesChanged(boolean flag);

        public abstract void onFingerprintGesture(int i);

        public abstract boolean onGesture(int i);

        public abstract void onInterrupt();

        public abstract boolean onKeyEvent(KeyEvent keyevent);

        public abstract void onMagnificationChanged(Region region, float f, float f1, float f2);

        public abstract void onPerformGestureResult(int i, boolean flag);

        public abstract void onServiceConnected();

        public abstract void onSoftKeyboardShowModeChanged(int i);
    }

    public static abstract class GestureResultCallback
    {

        public void onCancelled(GestureDescription gesturedescription)
        {
        }

        public void onCompleted(GestureDescription gesturedescription)
        {
        }

        public GestureResultCallback()
        {
        }
    }

    private static class GestureResultCallbackInfo
    {

        GestureResultCallback callback;
        GestureDescription gestureDescription;
        Handler handler;

        GestureResultCallbackInfo(GestureDescription gesturedescription, GestureResultCallback gestureresultcallback, Handler handler1)
        {
            gestureDescription = gesturedescription;
            callback = gestureresultcallback;
            handler = handler1;
        }
    }

    public static class IAccessibilityServiceClientWrapper extends IAccessibilityServiceClient.Stub
        implements com.android.internal.os.HandlerCaller.Callback
    {

        public void clearAccessibilityCache()
        {
            Message message = mCaller.obtainMessage(5);
            mCaller.sendMessage(message);
        }

        public void executeMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 13: default 72
        //                       1 183
        //                       2 165
        //                       3 101
        //                       4 297
        //                       5 322
        //                       6 329
        //                       7 405
        //                       8 482
        //                       9 506
        //                       10 547
        //                       11 586
        //                       12 608
        //                       13 626;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14
_L1:
            Log.w("AccessibilityService", (new StringBuilder()).append("Unknown message type ").append(message.what).toString());
            return;
_L4:
            AccessibilityEvent accessibilityevent = (AccessibilityEvent)message.obj;
            boolean flag;
            if(message.arg1 != 0)
                flag = true;
            else
                flag = false;
            if(accessibilityevent == null)
                break MISSING_BLOCK_LABEL_155;
            AccessibilityInteractionClient.getInstance().onAccessibilityEvent(accessibilityevent);
            if(flag && mConnectionId != -1)
                mCallback.onAccessibilityEvent(accessibilityevent);
            accessibilityevent.recycle();
_L15:
            return;
            message;
              goto _L15
_L3:
            if(mConnectionId != -1)
                mCallback.onInterrupt();
            return;
_L2:
            mConnectionId = message.arg1;
            SomeArgs someargs = (SomeArgs)message.obj;
            IAccessibilityServiceConnection iaccessibilityserviceconnection = (IAccessibilityServiceConnection)someargs.arg1;
            message = (IBinder)someargs.arg2;
            someargs.recycle();
            if(iaccessibilityserviceconnection != null)
            {
                AccessibilityInteractionClient.getInstance().addConnection(mConnectionId, iaccessibilityserviceconnection);
                mCallback.init(mConnectionId, message);
                mCallback.onServiceConnected();
            } else
            {
                AccessibilityInteractionClient.getInstance().removeConnection(mConnectionId);
                mConnectionId = -1;
                AccessibilityInteractionClient.getInstance().clearCache();
                mCallback.init(-1, null);
            }
            return;
_L5:
            if(mConnectionId != -1)
            {
                int i = message.arg1;
                mCallback.onGesture(i);
            }
            return;
_L6:
            AccessibilityInteractionClient.getInstance().clearCache();
            return;
_L7:
            KeyEvent keyevent = (KeyEvent)message.obj;
            IAccessibilityServiceConnection iaccessibilityserviceconnection1 = AccessibilityInteractionClient.getInstance().getConnection(mConnectionId);
            if(iaccessibilityserviceconnection1 == null)
                break MISSING_BLOCK_LABEL_381;
            int j;
            boolean flag1;
            flag1 = mCallback.onKeyEvent(keyevent);
            j = message.arg1;
            try
            {
                iaccessibilityserviceconnection1.setOnKeyEventResult(flag1, j);
            }
            // Misplaced declaration of an exception variable
            catch(Message message) { }
            keyevent.recycle();
_L17:
            return;
            message;
            if(true) goto _L17; else goto _L16
_L16:
            message;
            try
            {
                keyevent.recycle();
            }
            catch(IllegalStateException illegalstateexception) { }
            throw message;
_L8:
            if(mConnectionId != -1)
            {
                message = (SomeArgs)message.obj;
                Region region = (Region)((SomeArgs) (message)).arg1;
                float f = ((Float)((SomeArgs) (message)).arg2).floatValue();
                float f1 = ((Float)((SomeArgs) (message)).arg3).floatValue();
                float f2 = ((Float)((SomeArgs) (message)).arg4).floatValue();
                mCallback.onMagnificationChanged(region, f, f1, f2);
            }
            return;
_L9:
            if(mConnectionId != -1)
            {
                int k = message.arg1;
                mCallback.onSoftKeyboardShowModeChanged(k);
            }
            return;
_L10:
            if(mConnectionId != -1)
            {
                boolean flag2;
                if(message.arg2 == 1)
                    flag2 = true;
                else
                    flag2 = false;
                mCallback.onPerformGestureResult(message.arg1, flag2);
            }
            return;
_L11:
            if(mConnectionId != -1)
            {
                Callbacks callbacks = mCallback;
                boolean flag3;
                if(message.arg1 == 1)
                    flag3 = true;
                else
                    flag3 = false;
                callbacks.onFingerprintCapturingGesturesChanged(flag3);
            }
            return;
_L12:
            if(mConnectionId != -1)
                mCallback.onFingerprintGesture(message.arg1);
            return;
_L13:
            if(mConnectionId != -1)
                mCallback.onAccessibilityButtonClicked();
            return;
_L14:
            if(mConnectionId != -1)
            {
                boolean flag4;
                if(message.arg1 != 0)
                    flag4 = true;
                else
                    flag4 = false;
                mCallback.onAccessibilityButtonAvailabilityChanged(flag4);
            }
            return;
        }

        public void init(IAccessibilityServiceConnection iaccessibilityserviceconnection, int i, IBinder ibinder)
        {
            iaccessibilityserviceconnection = mCaller.obtainMessageIOO(1, i, iaccessibilityserviceconnection, ibinder);
            mCaller.sendMessage(iaccessibilityserviceconnection);
        }

        public void onAccessibilityButtonAvailabilityChanged(boolean flag)
        {
            Object obj = mCaller;
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            obj = ((HandlerCaller) (obj)).obtainMessageI(13, i);
            mCaller.sendMessage(((Message) (obj)));
        }

        public void onAccessibilityButtonClicked()
        {
            Message message = mCaller.obtainMessage(12);
            mCaller.sendMessage(message);
        }

        public void onAccessibilityEvent(AccessibilityEvent accessibilityevent, boolean flag)
        {
            accessibilityevent = mCaller.obtainMessageBO(3, flag, accessibilityevent);
            mCaller.sendMessage(accessibilityevent);
        }

        public void onFingerprintCapturingGesturesChanged(boolean flag)
        {
            HandlerCaller handlercaller = mCaller;
            HandlerCaller handlercaller1 = mCaller;
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            handlercaller.sendMessage(handlercaller1.obtainMessageI(10, i));
        }

        public void onFingerprintGesture(int i)
        {
            mCaller.sendMessage(mCaller.obtainMessageI(11, i));
        }

        public void onGesture(int i)
        {
            Message message = mCaller.obtainMessageI(4, i);
            mCaller.sendMessage(message);
        }

        public void onInterrupt()
        {
            Message message = mCaller.obtainMessage(2);
            mCaller.sendMessage(message);
        }

        public void onKeyEvent(KeyEvent keyevent, int i)
        {
            keyevent = mCaller.obtainMessageIO(6, i, keyevent);
            mCaller.sendMessage(keyevent);
        }

        public void onMagnificationChanged(Region region, float f, float f1, float f2)
        {
            SomeArgs someargs = SomeArgs.obtain();
            someargs.arg1 = region;
            someargs.arg2 = Float.valueOf(f);
            someargs.arg3 = Float.valueOf(f1);
            someargs.arg4 = Float.valueOf(f2);
            region = mCaller.obtainMessageO(7, someargs);
            mCaller.sendMessage(region);
        }

        public void onPerformGestureResult(int i, boolean flag)
        {
            Object obj = mCaller;
            int j;
            if(flag)
                j = 1;
            else
                j = 0;
            obj = ((HandlerCaller) (obj)).obtainMessageII(9, i, j);
            mCaller.sendMessage(((Message) (obj)));
        }

        public void onSoftKeyboardShowModeChanged(int i)
        {
            Message message = mCaller.obtainMessageI(8, i);
            mCaller.sendMessage(message);
        }

        private static final int DO_ACCESSIBILITY_BUTTON_AVAILABILITY_CHANGED = 13;
        private static final int DO_ACCESSIBILITY_BUTTON_CLICKED = 12;
        private static final int DO_CLEAR_ACCESSIBILITY_CACHE = 5;
        private static final int DO_GESTURE_COMPLETE = 9;
        private static final int DO_INIT = 1;
        private static final int DO_ON_ACCESSIBILITY_EVENT = 3;
        private static final int DO_ON_FINGERPRINT_ACTIVE_CHANGED = 10;
        private static final int DO_ON_FINGERPRINT_GESTURE = 11;
        private static final int DO_ON_GESTURE = 4;
        private static final int DO_ON_INTERRUPT = 2;
        private static final int DO_ON_KEY_EVENT = 6;
        private static final int DO_ON_MAGNIFICATION_CHANGED = 7;
        private static final int DO_ON_SOFT_KEYBOARD_SHOW_MODE_CHANGED = 8;
        private final Callbacks mCallback;
        private final HandlerCaller mCaller;
        private int mConnectionId;

        public IAccessibilityServiceClientWrapper(Context context, Looper looper, Callbacks callbacks)
        {
            mConnectionId = -1;
            mCallback = callbacks;
            mCaller = new HandlerCaller(context, looper, this, true);
        }
    }

    public static final class MagnificationController
    {

        private void setMagnificationCallbackEnabled(boolean flag)
        {
            IAccessibilityServiceConnection iaccessibilityserviceconnection;
            iaccessibilityserviceconnection = AccessibilityInteractionClient.getInstance().getConnection(AccessibilityService._2D_get0(mService));
            if(iaccessibilityserviceconnection == null)
                break MISSING_BLOCK_LABEL_25;
            iaccessibilityserviceconnection.setMagnificationCallbackEnabled(flag);
            return;
            RemoteException remoteexception;
            remoteexception;
            throw new RuntimeException(remoteexception);
        }

        public void addListener(OnMagnificationChangedListener onmagnificationchangedlistener)
        {
            addListener(onmagnificationchangedlistener, null);
        }

        public void addListener(OnMagnificationChangedListener onmagnificationchangedlistener, Handler handler)
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            boolean flag;
            if(mListeners == null)
            {
                ArrayMap arraymap = JVM INSTR new #65  <Class ArrayMap>;
                arraymap.ArrayMap();
                mListeners = arraymap;
            }
            flag = mListeners.isEmpty();
            mListeners.put(onmagnificationchangedlistener, handler);
            if(!flag)
                break MISSING_BLOCK_LABEL_59;
            setMagnificationCallbackEnabled(true);
            obj;
            JVM INSTR monitorexit ;
            return;
            onmagnificationchangedlistener;
            throw onmagnificationchangedlistener;
        }

        void dispatchMagnificationChanged(final Region region, final float scale, final float centerX, float f)
        {
            final Object listener = mLock;
            listener;
            JVM INSTR monitorenter ;
            if(mListeners != null && !mListeners.isEmpty())
                break MISSING_BLOCK_LABEL_43;
            Slog.d("AccessibilityService", "Received magnification changed callback with no listeners registered!");
            setMagnificationCallbackEnabled(false);
            listener;
            JVM INSTR monitorexit ;
            return;
            ArrayMap arraymap = new ArrayMap(mListeners);
            listener;
            JVM INSTR monitorexit ;
            int i = 0;
            int j = arraymap.size();
            while(i < j) 
            {
                listener = (OnMagnificationChangedListener)arraymap.keyAt(i);
                Handler handler = (Handler)arraymap.valueAt(i);
                if(handler != null)
                    handler.post(f. new Runnable() {

                        public void run()
                        {
                            listener.onMagnificationChanged(MagnificationController.this, region, scale, centerX, centerY);
                        }

                        final MagnificationController this$1;
                        final float val$centerX;
                        final float val$centerY;
                        final MagnificationController.OnMagnificationChangedListener val$listener;
                        final Region val$region;
                        final float val$scale;

            
            {
                this$1 = final_magnificationcontroller;
                listener = onmagnificationchangedlistener;
                region = region1;
                scale = f;
                centerX = f1;
                centerY = F.this;
                super();
            }
                    }
);
                else
                    ((OnMagnificationChangedListener) (listener)).onMagnificationChanged(this, region, scale, centerX, f);
                i++;
            }
            break MISSING_BLOCK_LABEL_154;
            region;
            throw region;
        }

        public float getCenterX()
        {
            IAccessibilityServiceConnection iaccessibilityserviceconnection;
            iaccessibilityserviceconnection = AccessibilityInteractionClient.getInstance().getConnection(AccessibilityService._2D_get0(mService));
            if(iaccessibilityserviceconnection == null)
                break MISSING_BLOCK_LABEL_42;
            float f = iaccessibilityserviceconnection.getMagnificationCenterX();
            return f;
            RemoteException remoteexception;
            remoteexception;
            Log.w("AccessibilityService", "Failed to obtain center X", remoteexception);
            remoteexception.rethrowFromSystemServer();
            return 0.0F;
        }

        public float getCenterY()
        {
            IAccessibilityServiceConnection iaccessibilityserviceconnection;
            iaccessibilityserviceconnection = AccessibilityInteractionClient.getInstance().getConnection(AccessibilityService._2D_get0(mService));
            if(iaccessibilityserviceconnection == null)
                break MISSING_BLOCK_LABEL_42;
            float f = iaccessibilityserviceconnection.getMagnificationCenterY();
            return f;
            RemoteException remoteexception;
            remoteexception;
            Log.w("AccessibilityService", "Failed to obtain center Y", remoteexception);
            remoteexception.rethrowFromSystemServer();
            return 0.0F;
        }

        public Region getMagnificationRegion()
        {
            Object obj;
            obj = AccessibilityInteractionClient.getInstance().getConnection(AccessibilityService._2D_get0(mService));
            if(obj == null)
                break MISSING_BLOCK_LABEL_42;
            obj = ((IAccessibilityServiceConnection) (obj)).getMagnificationRegion();
            return ((Region) (obj));
            RemoteException remoteexception;
            remoteexception;
            Log.w("AccessibilityService", "Failed to obtain magnified region", remoteexception);
            remoteexception.rethrowFromSystemServer();
            return Region.obtain();
        }

        public float getScale()
        {
            IAccessibilityServiceConnection iaccessibilityserviceconnection;
            iaccessibilityserviceconnection = AccessibilityInteractionClient.getInstance().getConnection(AccessibilityService._2D_get0(mService));
            if(iaccessibilityserviceconnection == null)
                break MISSING_BLOCK_LABEL_42;
            float f = iaccessibilityserviceconnection.getMagnificationScale();
            return f;
            RemoteException remoteexception;
            remoteexception;
            Log.w("AccessibilityService", "Failed to obtain scale", remoteexception);
            remoteexception.rethrowFromSystemServer();
            return 1.0F;
        }

        void onServiceConnected()
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            if(mListeners != null && mListeners.isEmpty() ^ true)
                setMagnificationCallbackEnabled(true);
            obj;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public boolean removeListener(OnMagnificationChangedListener onmagnificationchangedlistener)
        {
            if(mListeners == null)
                return false;
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            int i = mListeners.indexOfKey(onmagnificationchangedlistener);
            boolean flag;
            if(i >= 0)
                flag = true;
            else
                flag = false;
            if(!flag)
                break MISSING_BLOCK_LABEL_46;
            mListeners.removeAt(i);
            if(!flag)
                break MISSING_BLOCK_LABEL_66;
            if(mListeners.isEmpty())
                setMagnificationCallbackEnabled(false);
            obj;
            JVM INSTR monitorexit ;
            return flag;
            onmagnificationchangedlistener;
            throw onmagnificationchangedlistener;
        }

        public boolean reset(boolean flag)
        {
            IAccessibilityServiceConnection iaccessibilityserviceconnection;
            iaccessibilityserviceconnection = AccessibilityInteractionClient.getInstance().getConnection(AccessibilityService._2D_get0(mService));
            if(iaccessibilityserviceconnection == null)
                break MISSING_BLOCK_LABEL_43;
            flag = iaccessibilityserviceconnection.resetMagnification(flag);
            return flag;
            RemoteException remoteexception;
            remoteexception;
            Log.w("AccessibilityService", "Failed to reset", remoteexception);
            remoteexception.rethrowFromSystemServer();
            return false;
        }

        public boolean setCenter(float f, float f1, boolean flag)
        {
            IAccessibilityServiceConnection iaccessibilityserviceconnection;
            iaccessibilityserviceconnection = AccessibilityInteractionClient.getInstance().getConnection(AccessibilityService._2D_get0(mService));
            if(iaccessibilityserviceconnection == null)
                break MISSING_BLOCK_LABEL_53;
            flag = iaccessibilityserviceconnection.setMagnificationScaleAndCenter((0.0F / 0.0F), f, f1, flag);
            return flag;
            RemoteException remoteexception;
            remoteexception;
            Log.w("AccessibilityService", "Failed to set center", remoteexception);
            remoteexception.rethrowFromSystemServer();
            return false;
        }

        public boolean setScale(float f, boolean flag)
        {
            IAccessibilityServiceConnection iaccessibilityserviceconnection;
            iaccessibilityserviceconnection = AccessibilityInteractionClient.getInstance().getConnection(AccessibilityService._2D_get0(mService));
            if(iaccessibilityserviceconnection == null)
                break MISSING_BLOCK_LABEL_48;
            flag = iaccessibilityserviceconnection.setMagnificationScaleAndCenter(f, (0.0F / 0.0F), (0.0F / 0.0F), flag);
            return flag;
            RemoteException remoteexception;
            remoteexception;
            Log.w("AccessibilityService", "Failed to set scale", remoteexception);
            remoteexception.rethrowFromSystemServer();
            return false;
        }

        private ArrayMap mListeners;
        private final Object mLock;
        private final AccessibilityService mService;

        MagnificationController(AccessibilityService accessibilityservice, Object obj)
        {
            mService = accessibilityservice;
            mLock = obj;
        }
    }

    public static interface MagnificationController.OnMagnificationChangedListener
    {

        public abstract void onMagnificationChanged(MagnificationController magnificationcontroller, Region region, float f, float f1, float f2);
    }

    public static final class SoftKeyboardController
    {

        private void setSoftKeyboardCallbackEnabled(boolean flag)
        {
            IAccessibilityServiceConnection iaccessibilityserviceconnection;
            iaccessibilityserviceconnection = AccessibilityInteractionClient.getInstance().getConnection(AccessibilityService._2D_get0(mService));
            if(iaccessibilityserviceconnection == null)
                break MISSING_BLOCK_LABEL_25;
            iaccessibilityserviceconnection.setSoftKeyboardCallbackEnabled(flag);
            return;
            RemoteException remoteexception;
            remoteexception;
            throw new RuntimeException(remoteexception);
        }

        public void addOnShowModeChangedListener(OnShowModeChangedListener onshowmodechangedlistener)
        {
            addOnShowModeChangedListener(onshowmodechangedlistener, null);
        }

        public void addOnShowModeChangedListener(OnShowModeChangedListener onshowmodechangedlistener, Handler handler)
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            boolean flag;
            if(mListeners == null)
            {
                ArrayMap arraymap = JVM INSTR new #65  <Class ArrayMap>;
                arraymap.ArrayMap();
                mListeners = arraymap;
            }
            flag = mListeners.isEmpty();
            mListeners.put(onshowmodechangedlistener, handler);
            if(!flag)
                break MISSING_BLOCK_LABEL_59;
            setSoftKeyboardCallbackEnabled(true);
            obj;
            JVM INSTR monitorexit ;
            return;
            onshowmodechangedlistener;
            throw onshowmodechangedlistener;
        }

        void dispatchSoftKeyboardShowModeChanged(int i)
        {
            final Object listener = mLock;
            listener;
            JVM INSTR monitorenter ;
            if(mListeners != null && !mListeners.isEmpty())
                break MISSING_BLOCK_LABEL_40;
            Slog.w("AccessibilityService", "Received soft keyboard show mode changed callback with no listeners registered!");
            setSoftKeyboardCallbackEnabled(false);
            listener;
            JVM INSTR monitorexit ;
            return;
            ArrayMap arraymap = new ArrayMap(mListeners);
            listener;
            JVM INSTR monitorexit ;
            int j = 0;
            int k = arraymap.size();
            while(j < k) 
            {
                listener = (OnShowModeChangedListener)arraymap.keyAt(j);
                Handler handler = (Handler)arraymap.valueAt(j);
                Exception exception;
                if(handler != null)
                    handler.post(i. new Runnable() {

                        public void run()
                        {
                            listener.onShowModeChanged(SoftKeyboardController.this, showMode);
                        }

                        final SoftKeyboardController this$1;
                        final SoftKeyboardController.OnShowModeChangedListener val$listener;
                        final int val$showMode;

            
            {
                this$1 = final_softkeyboardcontroller;
                listener = onshowmodechangedlistener;
                showMode = I.this;
                super();
            }
                    }
);
                else
                    ((OnShowModeChangedListener) (listener)).onShowModeChanged(this, i);
                j++;
            }
            break MISSING_BLOCK_LABEL_134;
            exception;
            throw exception;
        }

        public int getShowMode()
        {
            int i;
            try
            {
                i = android.provider.Settings.Secure.getInt(mService.getContentResolver(), "accessibility_soft_keyboard_mode");
            }
            catch(android.provider.Settings.SettingNotFoundException settingnotfoundexception)
            {
                Log.v("AccessibilityService", "Failed to obtain the soft keyboard mode", settingnotfoundexception);
                return 0;
            }
            return i;
        }

        void onServiceConnected()
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            if(mListeners != null && mListeners.isEmpty() ^ true)
                setSoftKeyboardCallbackEnabled(true);
            obj;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public boolean removeOnShowModeChangedListener(OnShowModeChangedListener onshowmodechangedlistener)
        {
            if(mListeners == null)
                return false;
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            int i = mListeners.indexOfKey(onshowmodechangedlistener);
            boolean flag;
            if(i >= 0)
                flag = true;
            else
                flag = false;
            if(!flag)
                break MISSING_BLOCK_LABEL_46;
            mListeners.removeAt(i);
            if(!flag)
                break MISSING_BLOCK_LABEL_66;
            if(mListeners.isEmpty())
                setSoftKeyboardCallbackEnabled(false);
            obj;
            JVM INSTR monitorexit ;
            return flag;
            onshowmodechangedlistener;
            throw onshowmodechangedlistener;
        }

        public boolean setShowMode(int i)
        {
            IAccessibilityServiceConnection iaccessibilityserviceconnection;
            iaccessibilityserviceconnection = AccessibilityInteractionClient.getInstance().getConnection(AccessibilityService._2D_get0(mService));
            if(iaccessibilityserviceconnection == null)
                break MISSING_BLOCK_LABEL_43;
            boolean flag = iaccessibilityserviceconnection.setSoftKeyboardShowMode(i);
            return flag;
            RemoteException remoteexception;
            remoteexception;
            Log.w("AccessibilityService", "Failed to set soft keyboard behavior", remoteexception);
            remoteexception.rethrowFromSystemServer();
            return false;
        }

        private ArrayMap mListeners;
        private final Object mLock;
        private final AccessibilityService mService;

        SoftKeyboardController(AccessibilityService accessibilityservice, Object obj)
        {
            mService = accessibilityservice;
            mLock = obj;
        }
    }

    public static interface SoftKeyboardController.OnShowModeChangedListener
    {

        public abstract void onShowModeChanged(SoftKeyboardController softkeyboardcontroller, int i);
    }


    static int _2D_get0(AccessibilityService accessibilityservice)
    {
        return accessibilityservice.mConnectionId;
    }

    static int _2D_set0(AccessibilityService accessibilityservice, int i)
    {
        accessibilityservice.mConnectionId = i;
        return i;
    }

    static IBinder _2D_set1(AccessibilityService accessibilityservice, IBinder ibinder)
    {
        accessibilityservice.mWindowToken = ibinder;
        return ibinder;
    }

    static void _2D_wrap0(AccessibilityService accessibilityservice)
    {
        accessibilityservice.dispatchServiceConnected();
    }

    static void _2D_wrap1(AccessibilityService accessibilityservice, boolean flag)
    {
        accessibilityservice.onAccessibilityButtonAvailabilityChanged(flag);
    }

    static void _2D_wrap2(AccessibilityService accessibilityservice)
    {
        accessibilityservice.onAccessibilityButtonClicked();
    }

    static void _2D_wrap3(AccessibilityService accessibilityservice, boolean flag)
    {
        accessibilityservice.onFingerprintCapturingGesturesChanged(flag);
    }

    static void _2D_wrap4(AccessibilityService accessibilityservice, int i)
    {
        accessibilityservice.onFingerprintGesture(i);
    }

    static void _2D_wrap5(AccessibilityService accessibilityservice, Region region, float f, float f1, float f2)
    {
        accessibilityservice.onMagnificationChanged(region, f, f1, f2);
    }

    static void _2D_wrap6(AccessibilityService accessibilityservice, int i)
    {
        accessibilityservice.onSoftKeyboardShowModeChanged(i);
    }

    public AccessibilityService()
    {
        mConnectionId = -1;
    }

    private void dispatchServiceConnected()
    {
        if(mMagnificationController != null)
            mMagnificationController.onServiceConnected();
        if(mSoftKeyboardController != null)
            mSoftKeyboardController.onServiceConnected();
        onServiceConnected();
    }

    private void onAccessibilityButtonAvailabilityChanged(boolean flag)
    {
        getAccessibilityButtonController().dispatchAccessibilityButtonAvailabilityChanged(flag);
    }

    private void onAccessibilityButtonClicked()
    {
        getAccessibilityButtonController().dispatchAccessibilityButtonClicked();
    }

    private void onFingerprintCapturingGesturesChanged(boolean flag)
    {
        getFingerprintGestureController().onGestureDetectionActiveChanged(flag);
    }

    private void onFingerprintGesture(int i)
    {
        getFingerprintGestureController().onGesture(i);
    }

    private void onMagnificationChanged(Region region, float f, float f1, float f2)
    {
        if(mMagnificationController != null)
            mMagnificationController.dispatchMagnificationChanged(region, f, f1, f2);
    }

    private void onSoftKeyboardShowModeChanged(int i)
    {
        if(mSoftKeyboardController != null)
            mSoftKeyboardController.dispatchSoftKeyboardShowModeChanged(i);
    }

    private void sendServiceInfo()
    {
        IAccessibilityServiceConnection iaccessibilityserviceconnection;
        iaccessibilityserviceconnection = AccessibilityInteractionClient.getInstance().getConnection(mConnectionId);
        if(mInfo == null || iaccessibilityserviceconnection == null)
            break MISSING_BLOCK_LABEL_43;
        iaccessibilityserviceconnection.setServiceInfo(mInfo);
        mInfo = null;
        AccessibilityInteractionClient.getInstance().clearCache();
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.w("AccessibilityService", "Error while setting AccessibilityServiceInfo", remoteexception);
        remoteexception.rethrowFromSystemServer();
          goto _L1
    }

    public final void disableSelf()
    {
        IAccessibilityServiceConnection iaccessibilityserviceconnection;
        iaccessibilityserviceconnection = AccessibilityInteractionClient.getInstance().getConnection(mConnectionId);
        if(iaccessibilityserviceconnection == null)
            break MISSING_BLOCK_LABEL_21;
        iaccessibilityserviceconnection.disableSelf();
        return;
        RemoteException remoteexception;
        remoteexception;
        throw new RuntimeException(remoteexception);
    }

    public final boolean dispatchGesture(GestureDescription gesturedescription, GestureResultCallback gestureresultcallback, Handler handler)
    {
        IAccessibilityServiceConnection iaccessibilityserviceconnection;
        List list;
        iaccessibilityserviceconnection = AccessibilityInteractionClient.getInstance().getConnection(mConnectionId);
        if(iaccessibilityserviceconnection == null)
            return false;
        list = GestureDescription.MotionEventGenerator.getGestureStepsFromGestureDescription(gesturedescription, 100);
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        mGestureStatusCallbackSequence = mGestureStatusCallbackSequence + 1;
        if(gestureresultcallback == null)
            break MISSING_BLOCK_LABEL_99;
        if(mGestureStatusCallbackInfos == null)
        {
            SparseArray sparsearray = JVM INSTR new #262 <Class SparseArray>;
            sparsearray.SparseArray();
            mGestureStatusCallbackInfos = sparsearray;
        }
        GestureResultCallbackInfo gestureresultcallbackinfo = JVM INSTR new #16  <Class AccessibilityService$GestureResultCallbackInfo>;
        gestureresultcallbackinfo.GestureResultCallbackInfo(gesturedescription, gestureresultcallback, handler);
        mGestureStatusCallbackInfos.put(mGestureStatusCallbackSequence, gestureresultcallbackinfo);
        int i = mGestureStatusCallbackSequence;
        gesturedescription = JVM INSTR new #272 <Class ParceledListSlice>;
        gesturedescription.ParceledListSlice(list);
        iaccessibilityserviceconnection.sendGesture(i, gesturedescription);
        obj;
        JVM INSTR monitorexit ;
        return true;
        gesturedescription;
        obj;
        JVM INSTR monitorexit ;
        throw gesturedescription;
        gesturedescription;
        throw new RuntimeException(gesturedescription);
    }

    public AccessibilityNodeInfo findFocus(int i)
    {
        return AccessibilityInteractionClient.getInstance().findFocus(mConnectionId, -2, AccessibilityNodeInfo.ROOT_NODE_ID, i);
    }

    public final AccessibilityButtonController getAccessibilityButtonController()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        AccessibilityButtonController accessibilitybuttoncontroller1;
        if(mAccessibilityButtonController == null)
        {
            AccessibilityButtonController accessibilitybuttoncontroller = JVM INSTR new #180 <Class AccessibilityButtonController>;
            accessibilitybuttoncontroller.AccessibilityButtonController(AccessibilityInteractionClient.getInstance().getConnection(mConnectionId));
            mAccessibilityButtonController = accessibilitybuttoncontroller;
        }
        accessibilitybuttoncontroller1 = mAccessibilityButtonController;
        obj;
        JVM INSTR monitorexit ;
        return accessibilitybuttoncontroller1;
        Exception exception;
        exception;
        throw exception;
    }

    public final FingerprintGestureController getFingerprintGestureController()
    {
        if(mFingerprintGestureController == null)
            mFingerprintGestureController = new FingerprintGestureController(AccessibilityInteractionClient.getInstance().getConnection(mConnectionId));
        return mFingerprintGestureController;
    }

    public final MagnificationController getMagnificationController()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        MagnificationController magnificationcontroller1;
        if(mMagnificationController == null)
        {
            MagnificationController magnificationcontroller = JVM INSTR new #22  <Class AccessibilityService$MagnificationController>;
            magnificationcontroller.MagnificationController(this, mLock);
            mMagnificationController = magnificationcontroller;
        }
        magnificationcontroller1 = mMagnificationController;
        obj;
        JVM INSTR monitorexit ;
        return magnificationcontroller1;
        Exception exception;
        exception;
        throw exception;
    }

    public AccessibilityNodeInfo getRootInActiveWindow()
    {
        return AccessibilityInteractionClient.getInstance().getRootInActiveWindow(mConnectionId);
    }

    public final AccessibilityServiceInfo getServiceInfo()
    {
        Object obj;
        obj = AccessibilityInteractionClient.getInstance().getConnection(mConnectionId);
        if(obj == null)
            break MISSING_BLOCK_LABEL_40;
        obj = ((IAccessibilityServiceConnection) (obj)).getServiceInfo();
        return ((AccessibilityServiceInfo) (obj));
        RemoteException remoteexception;
        remoteexception;
        Log.w("AccessibilityService", "Error while getting AccessibilityServiceInfo", remoteexception);
        remoteexception.rethrowFromSystemServer();
        return null;
    }

    public final SoftKeyboardController getSoftKeyboardController()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        SoftKeyboardController softkeyboardcontroller1;
        if(mSoftKeyboardController == null)
        {
            SoftKeyboardController softkeyboardcontroller = JVM INSTR new #30  <Class AccessibilityService$SoftKeyboardController>;
            softkeyboardcontroller.SoftKeyboardController(this, mLock);
            mSoftKeyboardController = softkeyboardcontroller;
        }
        softkeyboardcontroller1 = mSoftKeyboardController;
        obj;
        JVM INSTR monitorexit ;
        return softkeyboardcontroller1;
        Exception exception;
        exception;
        throw exception;
    }

    public Object getSystemService(String s)
    {
        if(getBaseContext() == null)
            throw new IllegalStateException("System services not available to Activities before onCreate()");
        if("window".equals(s))
        {
            if(mWindowManager == null)
                mWindowManager = (WindowManager)getBaseContext().getSystemService(s);
            return mWindowManager;
        } else
        {
            return super.getSystemService(s);
        }
    }

    public List getWindows()
    {
        return AccessibilityInteractionClient.getInstance().getWindows(mConnectionId);
    }

    public abstract void onAccessibilityEvent(AccessibilityEvent accessibilityevent);

    public final IBinder onBind(Intent intent)
    {
        return new IAccessibilityServiceClientWrapper(this, getMainLooper(), new Callbacks() {

            public void init(int i, IBinder ibinder)
            {
                AccessibilityService._2D_set0(AccessibilityService.this, i);
                AccessibilityService._2D_set1(AccessibilityService.this, ibinder);
                ((WindowManagerImpl)getSystemService("window")).setDefaultToken(ibinder);
            }

            public void onAccessibilityButtonAvailabilityChanged(boolean flag)
            {
                AccessibilityService._2D_wrap1(AccessibilityService.this, flag);
            }

            public void onAccessibilityButtonClicked()
            {
                AccessibilityService._2D_wrap2(AccessibilityService.this);
            }

            public void onAccessibilityEvent(AccessibilityEvent accessibilityevent)
            {
                AccessibilityService.this.onAccessibilityEvent(accessibilityevent);
            }

            public void onFingerprintCapturingGesturesChanged(boolean flag)
            {
                AccessibilityService._2D_wrap3(AccessibilityService.this, flag);
            }

            public void onFingerprintGesture(int i)
            {
                AccessibilityService._2D_wrap4(AccessibilityService.this, i);
            }

            public boolean onGesture(int i)
            {
                return AccessibilityService.this.onGesture(i);
            }

            public void onInterrupt()
            {
                AccessibilityService.this.onInterrupt();
            }

            public boolean onKeyEvent(KeyEvent keyevent)
            {
                return AccessibilityService.this.onKeyEvent(keyevent);
            }

            public void onMagnificationChanged(Region region, float f, float f1, float f2)
            {
                AccessibilityService._2D_wrap5(AccessibilityService.this, region, f, f1, f2);
            }

            public void onPerformGestureResult(int i, boolean flag)
            {
                AccessibilityService.this.onPerformGestureResult(i, flag);
            }

            public void onServiceConnected()
            {
                AccessibilityService._2D_wrap0(AccessibilityService.this);
            }

            public void onSoftKeyboardShowModeChanged(int i)
            {
                AccessibilityService._2D_wrap6(AccessibilityService.this, i);
            }

            final AccessibilityService this$0;

            
            {
                this$0 = AccessibilityService.this;
                super();
            }
        }
);
    }

    protected boolean onGesture(int i)
    {
        return false;
    }

    public abstract void onInterrupt();

    protected boolean onKeyEvent(KeyEvent keyevent)
    {
        return false;
    }

    void onPerformGestureResult(int i, final boolean completedSuccessfully)
    {
        if(mGestureStatusCallbackInfos == null)
            return;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        final Object finalCallbackInfo = (GestureResultCallbackInfo)mGestureStatusCallbackInfos.get(i);
        obj;
        JVM INSTR monitorexit ;
        if(finalCallbackInfo == null || ((GestureResultCallbackInfo) (finalCallbackInfo)).gestureDescription == null || ((GestureResultCallbackInfo) (finalCallbackInfo)).callback == null)
            break MISSING_BLOCK_LABEL_104;
        if(((GestureResultCallbackInfo) (finalCallbackInfo)).handler != null)
        {
            ((GestureResultCallbackInfo) (finalCallbackInfo)).handler.post(new Runnable() {

                public void run()
                {
                    if(completedSuccessfully)
                        finalCallbackInfo.callback.onCompleted(finalCallbackInfo.gestureDescription);
                    else
                        finalCallbackInfo.callback.onCancelled(finalCallbackInfo.gestureDescription);
                }

                final AccessibilityService this$0;
                final boolean val$completedSuccessfully;
                final GestureResultCallbackInfo val$finalCallbackInfo;

            
            {
                this$0 = AccessibilityService.this;
                completedSuccessfully = flag;
                finalCallbackInfo = gestureresultcallbackinfo;
                super();
            }
            }
);
            return;
        }
        break MISSING_BLOCK_LABEL_87;
        finalCallbackInfo;
        throw finalCallbackInfo;
        if(completedSuccessfully)
            ((GestureResultCallbackInfo) (finalCallbackInfo)).callback.onCompleted(((GestureResultCallbackInfo) (finalCallbackInfo)).gestureDescription);
        else
            ((GestureResultCallbackInfo) (finalCallbackInfo)).callback.onCancelled(((GestureResultCallbackInfo) (finalCallbackInfo)).gestureDescription);
    }

    protected void onServiceConnected()
    {
    }

    public final boolean performGlobalAction(int i)
    {
        IAccessibilityServiceConnection iaccessibilityserviceconnection;
        iaccessibilityserviceconnection = AccessibilityInteractionClient.getInstance().getConnection(mConnectionId);
        if(iaccessibilityserviceconnection == null)
            break MISSING_BLOCK_LABEL_41;
        boolean flag = iaccessibilityserviceconnection.performGlobalAction(i);
        return flag;
        RemoteException remoteexception;
        remoteexception;
        Log.w("AccessibilityService", "Error while calling performGlobalAction", remoteexception);
        remoteexception.rethrowFromSystemServer();
        return false;
    }

    public final void setServiceInfo(AccessibilityServiceInfo accessibilityserviceinfo)
    {
        mInfo = accessibilityserviceinfo;
        sendServiceInfo();
    }

    public static final int GESTURE_SWIPE_DOWN = 2;
    public static final int GESTURE_SWIPE_DOWN_AND_LEFT = 15;
    public static final int GESTURE_SWIPE_DOWN_AND_RIGHT = 16;
    public static final int GESTURE_SWIPE_DOWN_AND_UP = 8;
    public static final int GESTURE_SWIPE_LEFT = 3;
    public static final int GESTURE_SWIPE_LEFT_AND_DOWN = 10;
    public static final int GESTURE_SWIPE_LEFT_AND_RIGHT = 5;
    public static final int GESTURE_SWIPE_LEFT_AND_UP = 9;
    public static final int GESTURE_SWIPE_RIGHT = 4;
    public static final int GESTURE_SWIPE_RIGHT_AND_DOWN = 12;
    public static final int GESTURE_SWIPE_RIGHT_AND_LEFT = 6;
    public static final int GESTURE_SWIPE_RIGHT_AND_UP = 11;
    public static final int GESTURE_SWIPE_UP = 1;
    public static final int GESTURE_SWIPE_UP_AND_DOWN = 7;
    public static final int GESTURE_SWIPE_UP_AND_LEFT = 13;
    public static final int GESTURE_SWIPE_UP_AND_RIGHT = 14;
    public static final int GLOBAL_ACTION_BACK = 1;
    public static final int GLOBAL_ACTION_HOME = 2;
    public static final int GLOBAL_ACTION_NOTIFICATIONS = 4;
    public static final int GLOBAL_ACTION_POWER_DIALOG = 6;
    public static final int GLOBAL_ACTION_QUICK_SETTINGS = 5;
    public static final int GLOBAL_ACTION_RECENTS = 3;
    public static final int GLOBAL_ACTION_TOGGLE_SPLIT_SCREEN = 7;
    private static final String LOG_TAG = "AccessibilityService";
    public static final String SERVICE_INTERFACE = "android.accessibilityservice.AccessibilityService";
    public static final String SERVICE_META_DATA = "android.accessibilityservice";
    public static final int SHOW_MODE_AUTO = 0;
    public static final int SHOW_MODE_HIDDEN = 1;
    private AccessibilityButtonController mAccessibilityButtonController;
    private int mConnectionId;
    private FingerprintGestureController mFingerprintGestureController;
    private SparseArray mGestureStatusCallbackInfos;
    private int mGestureStatusCallbackSequence;
    private AccessibilityServiceInfo mInfo;
    private final Object mLock = new Object();
    private MagnificationController mMagnificationController;
    private SoftKeyboardController mSoftKeyboardController;
    private WindowManager mWindowManager;
    private IBinder mWindowToken;
}
