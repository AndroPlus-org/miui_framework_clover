// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.tv;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.hardware.hdmi.HdmiDeviceInfo;
import android.media.PlaybackParams;
import android.net.Uri;
import android.os.*;
import android.text.TextUtils;
import android.util.Log;
import android.view.*;
import android.widget.FrameLayout;
import com.android.internal.os.SomeArgs;
import com.android.internal.util.Preconditions;
import java.util.*;

// Referenced classes of package android.media.tv:
//            TvInputManager, TvInputInfo, TvInputHardwareInfo, ITvInputSession, 
//            ITvInputSessionCallback, TvContract, TvContentRating, ITvInputServiceCallback, 
//            ITvInputSessionWrapper

public abstract class TvInputService extends Service
{
    public static abstract class HardwareSession extends Session
    {

        static TvInputManager.Session _2D_get0(HardwareSession hardwaresession)
        {
            return hardwaresession.mHardwareSession;
        }

        static TvInputManager.SessionCallback _2D_get1(HardwareSession hardwaresession)
        {
            return hardwaresession.mHardwareSessionCallback;
        }

        static ITvInputSession _2D_get2(HardwareSession hardwaresession)
        {
            return hardwaresession.mProxySession;
        }

        static ITvInputSessionCallback _2D_get3(HardwareSession hardwaresession)
        {
            return hardwaresession.mProxySessionCallback;
        }

        static Handler _2D_get4(HardwareSession hardwaresession)
        {
            return hardwaresession.mServiceHandler;
        }

        static TvInputManager.Session _2D_set0(HardwareSession hardwaresession, TvInputManager.Session session)
        {
            hardwaresession.mHardwareSession = session;
            return session;
        }

        static ITvInputSession _2D_set1(HardwareSession hardwaresession, ITvInputSession itvinputsession)
        {
            hardwaresession.mProxySession = itvinputsession;
            return itvinputsession;
        }

        static ITvInputSessionCallback _2D_set2(HardwareSession hardwaresession, ITvInputSessionCallback itvinputsessioncallback)
        {
            hardwaresession.mProxySessionCallback = itvinputsessioncallback;
            return itvinputsessioncallback;
        }

        static Handler _2D_set3(HardwareSession hardwaresession, Handler handler)
        {
            hardwaresession.mServiceHandler = handler;
            return handler;
        }

        public abstract String getHardwareInputId();

        public void onHardwareVideoAvailable()
        {
        }

        public void onHardwareVideoUnavailable(int i)
        {
        }

        public final boolean onSetSurface(Surface surface)
        {
            Log.e("TvInputService", "onSetSurface() should not be called in HardwareProxySession.");
            return false;
        }

        void release()
        {
            if(mHardwareSession != null)
            {
                mHardwareSession.release();
                mHardwareSession = null;
            }
            super.release();
        }

        private TvInputManager.Session mHardwareSession;
        private final TvInputManager.SessionCallback mHardwareSessionCallback = new _cls1();
        private ITvInputSession mProxySession;
        private ITvInputSessionCallback mProxySessionCallback;
        private Handler mServiceHandler;

        public HardwareSession(Context context)
        {
            super(context);
        }
    }

    private static final class OverlayViewCleanUpTask extends AsyncTask
    {

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((View[])aobj);
        }

        protected transient Void doInBackground(View aview[])
        {
            aview = aview[0];
            try
            {
                Thread.sleep(5000L);
            }
            // Misplaced declaration of an exception variable
            catch(View aview[])
            {
                return null;
            }
            if(isCancelled())
                return null;
            if(aview.isAttachedToWindow())
            {
                Log.e("TvInputService", (new StringBuilder()).append("Time out on releasing overlay view. Killing ").append(aview.getContext().getPackageName()).toString());
                Process.killProcess(Process.myPid());
            }
            return null;
        }

        private OverlayViewCleanUpTask()
        {
        }

        OverlayViewCleanUpTask(OverlayViewCleanUpTask overlayviewcleanuptask)
        {
            this();
        }
    }

    public static abstract class RecordingSession
    {

        static ITvInputSessionCallback _2D_get0(RecordingSession recordingsession)
        {
            return recordingsession.mSessionCallback;
        }

        static void _2D_wrap0(RecordingSession recordingsession, ITvInputSessionCallback itvinputsessioncallback)
        {
            recordingsession.initialize(itvinputsessioncallback);
        }

        private void executeOrPostRunnableOnMainThread(Runnable runnable)
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            if(mSessionCallback != null) goto _L2; else goto _L1
_L1:
            mPendingActions.add(runnable);
_L3:
            obj;
            JVM INSTR monitorexit ;
            return;
_L2:
            if(!mHandler.getLooper().isCurrentThread())
                break MISSING_BLOCK_LABEL_55;
            runnable.run();
              goto _L3
            runnable;
            throw runnable;
            mHandler.post(runnable);
              goto _L3
        }

        private void initialize(ITvInputSessionCallback itvinputsessioncallback)
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            mSessionCallback = itvinputsessioncallback;
            for(itvinputsessioncallback = mPendingActions.iterator(); itvinputsessioncallback.hasNext(); ((Runnable)itvinputsessioncallback.next()).run());
            break MISSING_BLOCK_LABEL_53;
            itvinputsessioncallback;
            throw itvinputsessioncallback;
            mPendingActions.clear();
            obj;
            JVM INSTR monitorexit ;
        }

        void appPrivateCommand(String s, Bundle bundle)
        {
            onAppPrivateCommand(s, bundle);
        }

        public void notifyError(int i)
        {
            int j;
label0:
            {
                if(i >= 0)
                {
                    j = i;
                    if(i <= 2)
                        break label0;
                }
                Log.w("TvInputService", (new StringBuilder()).append("notifyError - invalid error code (").append(i).append(") is changed to RECORDING_ERROR_UNKNOWN.").toString());
                j = 0;
            }
            executeOrPostRunnableOnMainThread(j. new Runnable() {

                public void run()
                {
                    if(RecordingSession._2D_get0(RecordingSession.this) != null)
                        RecordingSession._2D_get0(RecordingSession.this).onError(validError);
_L1:
                    return;
                    RemoteException remoteexception;
                    remoteexception;
                    Log.w("TvInputService", "error in notifyError", remoteexception);
                      goto _L1
                }

                final RecordingSession this$1;
                final int val$validError;

            
            {
                this$1 = final_recordingsession;
                validError = I.this;
                super();
            }
            }
);
        }

        public void notifyRecordingStopped(Uri uri)
        {
            executeOrPostRunnableOnMainThread(uri. new Runnable() {

                public void run()
                {
                    if(RecordingSession._2D_get0(RecordingSession.this) != null)
                        RecordingSession._2D_get0(RecordingSession.this).onRecordingStopped(recordedProgramUri);
_L1:
                    return;
                    RemoteException remoteexception;
                    remoteexception;
                    Log.w("TvInputService", "error in notifyRecordingStopped", remoteexception);
                      goto _L1
                }

                final RecordingSession this$1;
                final Uri val$recordedProgramUri;

            
            {
                this$1 = final_recordingsession;
                recordedProgramUri = Uri.this;
                super();
            }
            }
);
        }

        public void notifySessionEvent(final String eventType, Bundle bundle)
        {
            Preconditions.checkNotNull(eventType);
            executeOrPostRunnableOnMainThread(bundle. new Runnable() {

                public void run()
                {
                    if(RecordingSession._2D_get0(RecordingSession.this) != null)
                        RecordingSession._2D_get0(RecordingSession.this).onSessionEvent(eventType, eventArgs);
_L1:
                    return;
                    RemoteException remoteexception;
                    remoteexception;
                    Log.w("TvInputService", (new StringBuilder()).append("error in sending event (event=").append(eventType).append(")").toString(), remoteexception);
                      goto _L1
                }

                final RecordingSession this$1;
                final Bundle val$eventArgs;
                final String val$eventType;

            
            {
                this$1 = final_recordingsession;
                eventType = s;
                eventArgs = Bundle.this;
                super();
            }
            }
);
        }

        public void notifyTuned(Uri uri)
        {
            executeOrPostRunnableOnMainThread(uri. new Runnable() {

                public void run()
                {
                    if(RecordingSession._2D_get0(RecordingSession.this) != null)
                        RecordingSession._2D_get0(RecordingSession.this).onTuned(channelUri);
_L1:
                    return;
                    RemoteException remoteexception;
                    remoteexception;
                    Log.w("TvInputService", "error in notifyTuned", remoteexception);
                      goto _L1
                }

                final RecordingSession this$1;
                final Uri val$channelUri;

            
            {
                this$1 = final_recordingsession;
                channelUri = Uri.this;
                super();
            }
            }
);
        }

        public void onAppPrivateCommand(String s, Bundle bundle)
        {
        }

        public abstract void onRelease();

        public abstract void onStartRecording(Uri uri);

        public abstract void onStopRecording();

        public abstract void onTune(Uri uri);

        public void onTune(Uri uri, Bundle bundle)
        {
            onTune(uri);
        }

        void release()
        {
            onRelease();
        }

        void startRecording(Uri uri)
        {
            onStartRecording(uri);
        }

        void stopRecording()
        {
            onStopRecording();
        }

        void tune(Uri uri, Bundle bundle)
        {
            onTune(uri, bundle);
        }

        final Handler mHandler;
        private final Object mLock = new Object();
        private final List mPendingActions = new ArrayList();
        private ITvInputSessionCallback mSessionCallback;

        public RecordingSession(Context context)
        {
            mHandler = new Handler(context.getMainLooper());
        }
    }

    private final class ServiceHandler extends Handler
    {

        private void broadcastAddHardwareInput(int i, TvInputInfo tvinputinfo)
        {
            int j = TvInputService._2D_get0(TvInputService.this).beginBroadcast();
            int k = 0;
            while(k < j) 
            {
                try
                {
                    ((ITvInputServiceCallback)TvInputService._2D_get0(TvInputService.this).getBroadcastItem(k)).addHardwareInput(i, tvinputinfo);
                }
                catch(RemoteException remoteexception)
                {
                    Log.e("TvInputService", "error in broadcastAddHardwareInput", remoteexception);
                }
                k++;
            }
            TvInputService._2D_get0(TvInputService.this).finishBroadcast();
        }

        private void broadcastAddHdmiInput(int i, TvInputInfo tvinputinfo)
        {
            int j = TvInputService._2D_get0(TvInputService.this).beginBroadcast();
            int k = 0;
            while(k < j) 
            {
                try
                {
                    ((ITvInputServiceCallback)TvInputService._2D_get0(TvInputService.this).getBroadcastItem(k)).addHdmiInput(i, tvinputinfo);
                }
                catch(RemoteException remoteexception)
                {
                    Log.e("TvInputService", "error in broadcastAddHdmiInput", remoteexception);
                }
                k++;
            }
            TvInputService._2D_get0(TvInputService.this).finishBroadcast();
        }

        private void broadcastRemoveHardwareInput(String s)
        {
            int i = TvInputService._2D_get0(TvInputService.this).beginBroadcast();
            int j = 0;
            while(j < i) 
            {
                try
                {
                    ((ITvInputServiceCallback)TvInputService._2D_get0(TvInputService.this).getBroadcastItem(j)).removeHardwareInput(s);
                }
                catch(RemoteException remoteexception)
                {
                    Log.e("TvInputService", "error in broadcastRemoveHardwareInput", remoteexception);
                }
                j++;
            }
            TvInputService._2D_get0(TvInputService.this).finishBroadcast();
        }

        public final void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 7: default 48
        //                       1 77
        //                       2 382
        //                       3 463
        //                       4 566
        //                       5 597
        //                       6 624
        //                       7 655;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            Log.w("TvInputService", (new StringBuilder()).append("Unhandled message code: ").append(message.what).toString());
            return;
_L2:
            Object obj;
            Object obj1;
            obj = (SomeArgs)message.obj;
            obj1 = (InputChannel)((SomeArgs) (obj)).arg1;
            message = (ITvInputSessionCallback)((SomeArgs) (obj)).arg2;
            String s = (String)((SomeArgs) (obj)).arg3;
            ((SomeArgs) (obj)).recycle();
            obj = onCreateSession(s);
            if(obj != null) goto _L10; else goto _L9
_L9:
            message.onSessionCreated(null, null);
_L11:
            return;
            message;
            Log.e("TvInputService", "error in onSessionCreated", message);
              goto _L11
_L10:
            obj1 = new ITvInputSessionWrapper(TvInputService.this, ((Session) (obj)), ((InputChannel) (obj1)));
            if(!(obj instanceof HardwareSession)) goto _L13; else goto _L12
_L12:
            HardwareSession hardwaresession;
            String s1;
            hardwaresession = (HardwareSession)obj;
            s1 = hardwaresession.getHardwareInputId();
            if(!TextUtils.isEmpty(s1) && !(TvInputService._2D_wrap0(TvInputService.this, s1) ^ true)) goto _L15; else goto _L14
_L14:
            if(TextUtils.isEmpty(s1))
                Log.w("TvInputService", "Hardware input id is not setup yet.");
            else
                Log.w("TvInputService", (new StringBuilder()).append("Invalid hardware input id : ").append(s1).toString());
            ((Session) (obj)).onRelease();
            message.onSessionCreated(null, null);
_L16:
            return;
            message;
            Log.e("TvInputService", "error in onSessionCreated", message);
              goto _L16
_L15:
            HardwareSession._2D_set1(hardwaresession, ((ITvInputSession) (obj1)));
            HardwareSession._2D_set2(hardwaresession, message);
            HardwareSession._2D_set3(hardwaresession, TvInputService._2D_get1(TvInputService.this));
            ((TvInputManager)getSystemService("tv_input")).createSession(s1, HardwareSession._2D_get1(hardwaresession), TvInputService._2D_get1(TvInputService.this));
_L17:
            return;
_L13:
            SomeArgs someargs1 = SomeArgs.obtain();
            someargs1.arg1 = obj;
            someargs1.arg2 = obj1;
            someargs1.arg3 = message;
            someargs1.arg4 = null;
            TvInputService._2D_get1(TvInputService.this).obtainMessage(2, someargs1).sendToTarget();
              goto _L17
_L3:
            message = (SomeArgs)message.obj;
            Session session = (Session)((SomeArgs) (message)).arg1;
            ITvInputSession itvinputsession = (ITvInputSession)((SomeArgs) (message)).arg2;
            obj = (ITvInputSessionCallback)((SomeArgs) (message)).arg3;
            IBinder ibinder = (IBinder)((SomeArgs) (message)).arg4;
            try
            {
                ((ITvInputSessionCallback) (obj)).onSessionCreated(itvinputsession, ibinder);
            }
            catch(RemoteException remoteexception1)
            {
                Log.e("TvInputService", "error in onSessionCreated", remoteexception1);
            }
            if(session != null)
                Session._2D_wrap0(session, ((ITvInputSessionCallback) (obj)));
            message.recycle();
            return;
_L4:
            SomeArgs someargs = (SomeArgs)message.obj;
            message = (ITvInputSessionCallback)someargs.arg1;
            obj = (String)someargs.arg2;
            someargs.recycle();
            obj = onCreateRecordingSession(((String) (obj)));
            if(obj != null) goto _L19; else goto _L18
_L18:
            message.onSessionCreated(null, null);
_L20:
            return;
            message;
            Log.e("TvInputService", "error in onSessionCreated", message);
              goto _L20
_L19:
            ITvInputSessionWrapper itvinputsessionwrapper = new ITvInputSessionWrapper(TvInputService.this, ((RecordingSession) (obj)));
            try
            {
                message.onSessionCreated(itvinputsessionwrapper, null);
            }
            catch(RemoteException remoteexception)
            {
                Log.e("TvInputService", "error in onSessionCreated", remoteexception);
            }
            RecordingSession._2D_wrap0(((RecordingSession) (obj)), message);
            return;
_L5:
            message = (TvInputHardwareInfo)message.obj;
            TvInputInfo tvinputinfo = onHardwareAdded(message);
            if(tvinputinfo != null)
                broadcastAddHardwareInput(message.getDeviceId(), tvinputinfo);
            return;
_L6:
            message = (TvInputHardwareInfo)message.obj;
            message = onHardwareRemoved(message);
            if(message != null)
                broadcastRemoveHardwareInput(message);
            return;
_L7:
            HdmiDeviceInfo hdmideviceinfo = (HdmiDeviceInfo)message.obj;
            message = onHdmiDeviceAdded(hdmideviceinfo);
            if(message != null)
                broadcastAddHdmiInput(hdmideviceinfo.getId(), message);
            return;
_L8:
            message = (HdmiDeviceInfo)message.obj;
            message = onHdmiDeviceRemoved(message);
            if(message != null)
                broadcastRemoveHardwareInput(message);
            return;
        }

        private static final int DO_ADD_HARDWARE_INPUT = 4;
        private static final int DO_ADD_HDMI_INPUT = 6;
        private static final int DO_CREATE_RECORDING_SESSION = 3;
        private static final int DO_CREATE_SESSION = 1;
        private static final int DO_NOTIFY_SESSION_CREATED = 2;
        private static final int DO_REMOVE_HARDWARE_INPUT = 5;
        private static final int DO_REMOVE_HDMI_INPUT = 7;
        final TvInputService this$0;

        private ServiceHandler()
        {
            this$0 = TvInputService.this;
            super();
        }

        ServiceHandler(ServiceHandler servicehandler)
        {
            this();
        }
    }

    public static abstract class Session
        implements android.view.KeyEvent.Callback
    {

        static long _2D_get0(Session session)
        {
            return session.mCurrentPositionMs;
        }

        static Rect _2D_get1(Session session)
        {
            return session.mOverlayFrame;
        }

        static boolean _2D_get2(Session session)
        {
            return session.mOverlayViewEnabled;
        }

        static ITvInputSessionCallback _2D_get3(Session session)
        {
            return session.mSessionCallback;
        }

        static long _2D_get4(Session session)
        {
            return session.mStartPositionMs;
        }

        static TimeShiftPositionTrackingRunnable _2D_get5(Session session)
        {
            return session.mTimeShiftPositionTrackingRunnable;
        }

        static IBinder _2D_get6(Session session)
        {
            return session.mWindowToken;
        }

        static long _2D_set0(Session session, long l)
        {
            session.mCurrentPositionMs = l;
            return l;
        }

        static boolean _2D_set1(Session session, boolean flag)
        {
            session.mOverlayViewEnabled = flag;
            return flag;
        }

        static long _2D_set2(Session session, long l)
        {
            session.mStartPositionMs = l;
            return l;
        }

        static void _2D_wrap0(Session session, ITvInputSessionCallback itvinputsessioncallback)
        {
            session.initialize(itvinputsessioncallback);
        }

        static void _2D_wrap1(Session session, long l)
        {
            session.notifyTimeShiftCurrentPositionChanged(l);
        }

        static void _2D_wrap2(Session session, long l)
        {
            session.notifyTimeShiftStartPositionChanged(l);
        }

        private void executeOrPostRunnableOnMainThread(Runnable runnable)
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            if(mSessionCallback != null) goto _L2; else goto _L1
_L1:
            mPendingActions.add(runnable);
_L3:
            obj;
            JVM INSTR monitorexit ;
            return;
_L2:
            if(!mHandler.getLooper().isCurrentThread())
                break MISSING_BLOCK_LABEL_55;
            runnable.run();
              goto _L3
            runnable;
            throw runnable;
            mHandler.post(runnable);
              goto _L3
        }

        private void initialize(ITvInputSessionCallback itvinputsessioncallback)
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            mSessionCallback = itvinputsessioncallback;
            for(itvinputsessioncallback = mPendingActions.iterator(); itvinputsessioncallback.hasNext(); ((Runnable)itvinputsessioncallback.next()).run());
            break MISSING_BLOCK_LABEL_53;
            itvinputsessioncallback;
            throw itvinputsessioncallback;
            mPendingActions.clear();
            obj;
            JVM INSTR monitorexit ;
        }

        private void notifyTimeShiftCurrentPositionChanged(long l)
        {
            executeOrPostRunnableOnMainThread(l. new Runnable() {

                public void run()
                {
                    if(Session._2D_get3(Session.this) != null)
                        Session._2D_get3(Session.this).onTimeShiftCurrentPositionChanged(timeMs);
_L1:
                    return;
                    RemoteException remoteexception;
                    remoteexception;
                    Log.w("TvInputService", "error in notifyTimeShiftCurrentPositionChanged", remoteexception);
                      goto _L1
                }

                final Session this$1;
                final long val$timeMs;

            
            {
                this$1 = final_session;
                timeMs = J.this;
                super();
            }
            }
);
        }

        private void notifyTimeShiftStartPositionChanged(long l)
        {
            executeOrPostRunnableOnMainThread(l. new Runnable() {

                public void run()
                {
                    if(Session._2D_get3(Session.this) != null)
                        Session._2D_get3(Session.this).onTimeShiftStartPositionChanged(timeMs);
_L1:
                    return;
                    RemoteException remoteexception;
                    remoteexception;
                    Log.w("TvInputService", "error in notifyTimeShiftStartPositionChanged", remoteexception);
                      goto _L1
                }

                final Session this$1;
                final long val$timeMs;

            
            {
                this$1 = final_session;
                timeMs = J.this;
                super();
            }
            }
);
        }

        void appPrivateCommand(String s, Bundle bundle)
        {
            onAppPrivateCommand(s, bundle);
        }

        void createOverlayView(IBinder ibinder, Rect rect)
        {
            if(mOverlayViewContainer != null)
                removeOverlayView(false);
            mWindowToken = ibinder;
            mOverlayFrame = rect;
            onOverlayViewSizeChanged(rect.right - rect.left, rect.bottom - rect.top);
            if(!mOverlayViewEnabled)
                return;
            mOverlayView = onCreateOverlayView();
            if(mOverlayView == null)
                return;
            if(mOverlayViewCleanUpTask != null)
            {
                mOverlayViewCleanUpTask.cancel(true);
                mOverlayViewCleanUpTask = null;
            }
            mOverlayViewContainer = new FrameLayout(mContext.getApplicationContext());
            mOverlayViewContainer.addView(mOverlayView);
            int i = 536;
            if(ActivityManager.isHighEndGfx())
                i = 0x1000218;
            mWindowParams = new android.view.WindowManager.LayoutParams(rect.right - rect.left, rect.bottom - rect.top, rect.left, rect.top, 1004, i, -2);
            rect = mWindowParams;
            rect.privateFlags = ((android.view.WindowManager.LayoutParams) (rect)).privateFlags | 0x40;
            mWindowParams.gravity = 0x800033;
            mWindowParams.token = ibinder;
            mWindowManager.addView(mOverlayViewContainer, mWindowParams);
        }

        int dispatchInputEvent(InputEvent inputevent, InputEventReceiver inputeventreceiver)
        {
            boolean flag;
            boolean flag1;
            flag = false;
            flag1 = false;
            if(!(inputevent instanceof KeyEvent)) goto _L2; else goto _L1
_L1:
            boolean flag2;
            int i;
            KeyEvent keyevent = (KeyEvent)inputevent;
            if(keyevent.dispatch(this, mDispatcherState, this))
                return 1;
            flag2 = TvInputService.isNavigationKey(keyevent.getKeyCode());
            if(!KeyEvent.isMediaKey(keyevent.getKeyCode()))
            {
                if(keyevent.getKeyCode() == 222)
                    i = 1;
                else
                    i = 0;
            } else
            {
                i = 1;
            }
_L4:
            if(mOverlayViewContainer == null || mOverlayViewContainer.isAttachedToWindow() ^ true || i != 0)
                return 0;
            break; /* Loop/switch isn't completed */
_L2:
            MotionEvent motionevent;
            flag2 = flag;
            i = ((flag1) ? 1 : 0);
            if(!(inputevent instanceof MotionEvent))
                continue; /* Loop/switch isn't completed */
            motionevent = (MotionEvent)inputevent;
            i = motionevent.getSource();
            if(!motionevent.isTouchEvent())
                break; /* Loop/switch isn't completed */
            flag2 = flag;
            i = ((flag1) ? 1 : 0);
            if(onTouchEvent(motionevent))
                return 1;
            if(true) goto _L4; else goto _L3
_L3:
            if((i & 4) == 0)
                break; /* Loop/switch isn't completed */
            flag2 = flag;
            i = ((flag1) ? 1 : 0);
            if(onTrackballEvent(motionevent))
                return 1;
            if(true) goto _L4; else goto _L5
_L5:
            flag2 = flag;
            i = ((flag1) ? 1 : 0);
            if(onGenericMotionEvent(motionevent))
                return 1;
            if(true) goto _L4; else goto _L6
_L6:
            if(!mOverlayViewContainer.hasWindowFocus())
                mOverlayViewContainer.getViewRootImpl().windowFocusChanged(true, true);
            if(flag2 && mOverlayViewContainer.hasFocusable())
            {
                mOverlayViewContainer.getViewRootImpl().dispatchInputEvent(inputevent);
                return 1;
            } else
            {
                mOverlayViewContainer.getViewRootImpl().dispatchInputEvent(inputevent, inputeventreceiver);
                return -1;
            }
        }

        void dispatchSurfaceChanged(int i, int j, int k)
        {
            onSurfaceChanged(i, j, k);
        }

        public void layoutSurface(final int left, final int top, final int right, int i)
        {
            if(left > right || top > i)
            {
                throw new IllegalArgumentException("Invalid parameter");
            } else
            {
                executeOrPostRunnableOnMainThread(i. new Runnable() {

                    public void run()
                    {
                        if(Session._2D_get3(Session.this) != null)
                            Session._2D_get3(Session.this).onLayoutSurface(left, top, right, bottom);
_L1:
                        return;
                        RemoteException remoteexception;
                        remoteexception;
                        Log.w("TvInputService", "error in layoutSurface", remoteexception);
                          goto _L1
                    }

                    final Session this$1;
                    final int val$bottom;
                    final int val$left;
                    final int val$right;
                    final int val$top;

            
            {
                this$1 = final_session;
                left = i;
                top = j;
                right = k;
                bottom = I.this;
                super();
            }
                }
);
                return;
            }
        }

        public void notifyChannelRetuned(Uri uri)
        {
            executeOrPostRunnableOnMainThread(uri. new Runnable() {

                public void run()
                {
                    if(Session._2D_get3(Session.this) != null)
                        Session._2D_get3(Session.this).onChannelRetuned(channelUri);
_L1:
                    return;
                    RemoteException remoteexception;
                    remoteexception;
                    Log.w("TvInputService", "error in notifyChannelRetuned", remoteexception);
                      goto _L1
                }

                final Session this$1;
                final Uri val$channelUri;

            
            {
                this$1 = final_session;
                channelUri = Uri.this;
                super();
            }
            }
);
        }

        public void notifyContentAllowed()
        {
            executeOrPostRunnableOnMainThread(new Runnable() {

                public void run()
                {
                    if(Session._2D_get3(Session.this) != null)
                        Session._2D_get3(Session.this).onContentAllowed();
_L1:
                    return;
                    RemoteException remoteexception;
                    remoteexception;
                    Log.w("TvInputService", "error in notifyContentAllowed", remoteexception);
                      goto _L1
                }

                final Session this$1;

            
            {
                this$1 = Session.this;
                super();
            }
            }
);
        }

        public void notifyContentBlocked(TvContentRating tvcontentrating)
        {
            Preconditions.checkNotNull(tvcontentrating);
            executeOrPostRunnableOnMainThread(tvcontentrating. new Runnable() {

                public void run()
                {
                    if(Session._2D_get3(Session.this) != null)
                        Session._2D_get3(Session.this).onContentBlocked(rating.flattenToString());
_L1:
                    return;
                    RemoteException remoteexception;
                    remoteexception;
                    Log.w("TvInputService", "error in notifyContentBlocked", remoteexception);
                      goto _L1
                }

                final Session this$1;
                final TvContentRating val$rating;

            
            {
                this$1 = final_session;
                rating = TvContentRating.this;
                super();
            }
            }
);
        }

        public void notifySessionEvent(final String eventType, Bundle bundle)
        {
            Preconditions.checkNotNull(eventType);
            executeOrPostRunnableOnMainThread(bundle. new Runnable() {

                public void run()
                {
                    if(Session._2D_get3(Session.this) != null)
                        Session._2D_get3(Session.this).onSessionEvent(eventType, eventArgs);
_L1:
                    return;
                    RemoteException remoteexception;
                    remoteexception;
                    Log.w("TvInputService", (new StringBuilder()).append("error in sending event (event=").append(eventType).append(")").toString(), remoteexception);
                      goto _L1
                }

                final Session this$1;
                final Bundle val$eventArgs;
                final String val$eventType;

            
            {
                this$1 = final_session;
                eventType = s;
                eventArgs = Bundle.this;
                super();
            }
            }
);
        }

        public void notifyTimeShiftStatusChanged(int i)
        {
            executeOrPostRunnableOnMainThread(i. new Runnable() {

                public void run()
                {
                    Session session = Session.this;
                    boolean flag;
                    if(status == 3)
                        flag = true;
                    else
                        flag = false;
                    session.timeShiftEnablePositionTracking(flag);
                    if(Session._2D_get3(Session.this) != null)
                        Session._2D_get3(Session.this).onTimeShiftStatusChanged(status);
_L1:
                    return;
                    RemoteException remoteexception;
                    remoteexception;
                    Log.w("TvInputService", "error in notifyTimeShiftStatusChanged", remoteexception);
                      goto _L1
                }

                final Session this$1;
                final int val$status;

            
            {
                this$1 = final_session;
                status = I.this;
                super();
            }
            }
);
        }

        public void notifyTrackSelected(final int type, String s)
        {
            executeOrPostRunnableOnMainThread(s. new Runnable() {

                public void run()
                {
                    if(Session._2D_get3(Session.this) != null)
                        Session._2D_get3(Session.this).onTrackSelected(type, trackId);
_L1:
                    return;
                    RemoteException remoteexception;
                    remoteexception;
                    Log.w("TvInputService", "error in notifyTrackSelected", remoteexception);
                      goto _L1
                }

                final Session this$1;
                final String val$trackId;
                final int val$type;

            
            {
                this$1 = final_session;
                type = i;
                trackId = String.this;
                super();
            }
            }
);
        }

        public void notifyTracksChanged(List list)
        {
            executeOrPostRunnableOnMainThread((new ArrayList(list)). new Runnable() {

                public void run()
                {
                    if(Session._2D_get3(Session.this) != null)
                        Session._2D_get3(Session.this).onTracksChanged(tracksCopy);
_L1:
                    return;
                    RemoteException remoteexception;
                    remoteexception;
                    Log.w("TvInputService", "error in notifyTracksChanged", remoteexception);
                      goto _L1
                }

                final Session this$1;
                final List val$tracksCopy;

            
            {
                this$1 = final_session;
                tracksCopy = List.this;
                super();
            }
            }
);
        }

        public void notifyVideoAvailable()
        {
            executeOrPostRunnableOnMainThread(new Runnable() {

                public void run()
                {
                    if(Session._2D_get3(Session.this) != null)
                        Session._2D_get3(Session.this).onVideoAvailable();
_L1:
                    return;
                    RemoteException remoteexception;
                    remoteexception;
                    Log.w("TvInputService", "error in notifyVideoAvailable", remoteexception);
                      goto _L1
                }

                final Session this$1;

            
            {
                this$1 = Session.this;
                super();
            }
            }
);
        }

        public void notifyVideoUnavailable(int i)
        {
            if(i < 0 || i > 4)
                Log.e("TvInputService", (new StringBuilder()).append("notifyVideoUnavailable - unknown reason: ").append(i).toString());
            executeOrPostRunnableOnMainThread(i. new Runnable() {

                public void run()
                {
                    if(Session._2D_get3(Session.this) != null)
                        Session._2D_get3(Session.this).onVideoUnavailable(reason);
_L1:
                    return;
                    RemoteException remoteexception;
                    remoteexception;
                    Log.w("TvInputService", "error in notifyVideoUnavailable", remoteexception);
                      goto _L1
                }

                final Session this$1;
                final int val$reason;

            
            {
                this$1 = final_session;
                reason = I.this;
                super();
            }
            }
);
        }

        public void onAppPrivateCommand(String s, Bundle bundle)
        {
        }

        public View onCreateOverlayView()
        {
            return null;
        }

        public boolean onGenericMotionEvent(MotionEvent motionevent)
        {
            return false;
        }

        public boolean onKeyDown(int i, KeyEvent keyevent)
        {
            return false;
        }

        public boolean onKeyLongPress(int i, KeyEvent keyevent)
        {
            return false;
        }

        public boolean onKeyMultiple(int i, int j, KeyEvent keyevent)
        {
            return false;
        }

        public boolean onKeyUp(int i, KeyEvent keyevent)
        {
            return false;
        }

        public void onOverlayViewSizeChanged(int i, int j)
        {
        }

        public abstract void onRelease();

        public boolean onSelectTrack(int i, String s)
        {
            return false;
        }

        public abstract void onSetCaptionEnabled(boolean flag);

        public void onSetMain(boolean flag)
        {
        }

        public abstract void onSetStreamVolume(float f);

        public abstract boolean onSetSurface(Surface surface);

        public void onSurfaceChanged(int i, int j, int k)
        {
        }

        public long onTimeShiftGetCurrentPosition()
        {
            return 0x8000000000000000L;
        }

        public long onTimeShiftGetStartPosition()
        {
            return 0x8000000000000000L;
        }

        public void onTimeShiftPause()
        {
        }

        public void onTimeShiftPlay(Uri uri)
        {
        }

        public void onTimeShiftResume()
        {
        }

        public void onTimeShiftSeekTo(long l)
        {
        }

        public void onTimeShiftSetPlaybackParams(PlaybackParams playbackparams)
        {
        }

        public boolean onTouchEvent(MotionEvent motionevent)
        {
            return false;
        }

        public boolean onTrackballEvent(MotionEvent motionevent)
        {
            return false;
        }

        public abstract boolean onTune(Uri uri);

        public boolean onTune(Uri uri, Bundle bundle)
        {
            return onTune(uri);
        }

        public void onUnblockContent(TvContentRating tvcontentrating)
        {
        }

        void relayoutOverlayView(Rect rect)
        {
            if(mOverlayFrame == null || mOverlayFrame.width() != rect.width() || mOverlayFrame.height() != rect.height())
                onOverlayViewSizeChanged(rect.right - rect.left, rect.bottom - rect.top);
            mOverlayFrame = rect;
            if(!mOverlayViewEnabled || mOverlayViewContainer == null)
            {
                return;
            } else
            {
                mWindowParams.x = rect.left;
                mWindowParams.y = rect.top;
                mWindowParams.width = rect.right - rect.left;
                mWindowParams.height = rect.bottom - rect.top;
                mWindowManager.updateViewLayout(mOverlayViewContainer, mWindowParams);
                return;
            }
        }

        void release()
        {
            onRelease();
            if(mSurface != null)
            {
                mSurface.release();
                mSurface = null;
            }
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            mSessionCallback = null;
            mPendingActions.clear();
            obj;
            JVM INSTR monitorexit ;
            removeOverlayView(true);
            mHandler.removeCallbacks(mTimeShiftPositionTrackingRunnable);
            return;
            Exception exception;
            exception;
            throw exception;
        }

        void removeOverlayView(boolean flag)
        {
            if(flag)
            {
                mWindowToken = null;
                mOverlayFrame = null;
            }
            if(mOverlayViewContainer != null)
            {
                mOverlayViewContainer.removeView(mOverlayView);
                mOverlayView = null;
                mWindowManager.removeView(mOverlayViewContainer);
                mOverlayViewContainer = null;
                mWindowParams = null;
            }
        }

        void scheduleOverlayViewCleanup()
        {
            FrameLayout framelayout = mOverlayViewContainer;
            if(framelayout != null)
            {
                mOverlayViewCleanUpTask = new OverlayViewCleanUpTask(null);
                mOverlayViewCleanUpTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new View[] {
                    framelayout
                });
            }
        }

        void selectTrack(int i, String s)
        {
            onSelectTrack(i, s);
        }

        void setCaptionEnabled(boolean flag)
        {
            onSetCaptionEnabled(flag);
        }

        void setMain(boolean flag)
        {
            onSetMain(flag);
        }

        public void setOverlayViewEnabled(boolean flag)
        {
            mHandler.post(flag. new Runnable() {

                public void run()
                {
                    if(enable == Session._2D_get2(Session.this))
                        return;
                    Session._2D_set1(Session.this, enable);
                    if(enable)
                    {
                        if(Session._2D_get6(Session.this) != null)
                            createOverlayView(Session._2D_get6(Session.this), Session._2D_get1(Session.this));
                    } else
                    {
                        removeOverlayView(false);
                    }
                }

                final Session this$1;
                final boolean val$enable;

            
            {
                this$1 = final_session;
                enable = Z.this;
                super();
            }
            }
);
        }

        void setStreamVolume(float f)
        {
            onSetStreamVolume(f);
        }

        void setSurface(Surface surface)
        {
            onSetSurface(surface);
            if(mSurface != null)
                mSurface.release();
            mSurface = surface;
        }

        void timeShiftEnablePositionTracking(boolean flag)
        {
            if(flag)
            {
                mHandler.post(mTimeShiftPositionTrackingRunnable);
            } else
            {
                mHandler.removeCallbacks(mTimeShiftPositionTrackingRunnable);
                mStartPositionMs = 0x8000000000000000L;
                mCurrentPositionMs = 0x8000000000000000L;
            }
        }

        void timeShiftPause()
        {
            onTimeShiftPause();
        }

        void timeShiftPlay(Uri uri)
        {
            mCurrentPositionMs = 0L;
            onTimeShiftPlay(uri);
        }

        void timeShiftResume()
        {
            onTimeShiftResume();
        }

        void timeShiftSeekTo(long l)
        {
            onTimeShiftSeekTo(l);
        }

        void timeShiftSetPlaybackParams(PlaybackParams playbackparams)
        {
            onTimeShiftSetPlaybackParams(playbackparams);
        }

        void tune(Uri uri, Bundle bundle)
        {
            mCurrentPositionMs = 0x8000000000000000L;
            onTune(uri, bundle);
        }

        void unblockContent(String s)
        {
            onUnblockContent(TvContentRating.unflattenFromString(s));
        }

        private static final int POSITION_UPDATE_INTERVAL_MS = 1000;
        private final Context mContext;
        private long mCurrentPositionMs;
        private final android.view.KeyEvent.DispatcherState mDispatcherState = new android.view.KeyEvent.DispatcherState();
        final Handler mHandler;
        private final Object mLock = new Object();
        private Rect mOverlayFrame;
        private View mOverlayView;
        private OverlayViewCleanUpTask mOverlayViewCleanUpTask;
        private FrameLayout mOverlayViewContainer;
        private boolean mOverlayViewEnabled;
        private final List mPendingActions = new ArrayList();
        private ITvInputSessionCallback mSessionCallback;
        private long mStartPositionMs;
        private Surface mSurface;
        private final TimeShiftPositionTrackingRunnable mTimeShiftPositionTrackingRunnable = new TimeShiftPositionTrackingRunnable(null);
        private final WindowManager mWindowManager;
        private android.view.WindowManager.LayoutParams mWindowParams;
        private IBinder mWindowToken;

        public Session(Context context)
        {
            mStartPositionMs = 0x8000000000000000L;
            mCurrentPositionMs = 0x8000000000000000L;
            mContext = context;
            mWindowManager = (WindowManager)context.getSystemService("window");
            mHandler = new Handler(context.getMainLooper());
        }
    }

    private final class Session.TimeShiftPositionTrackingRunnable
        implements Runnable
    {

        public void run()
        {
            long l = onTimeShiftGetStartPosition();
            if(Session._2D_get4(Session.this) == 0x8000000000000000L || Session._2D_get4(Session.this) != l)
            {
                Session._2D_set2(Session.this, l);
                Session._2D_wrap2(Session.this, l);
            }
            long l1 = onTimeShiftGetCurrentPosition();
            l = l1;
            if(l1 < Session._2D_get4(Session.this))
            {
                Log.w("TvInputService", (new StringBuilder()).append("Current position (").append(l1).append(") cannot be earlier than").append(" start position (").append(Session._2D_get4(Session.this)).append("). Reset to the start ").append("position.").toString());
                l = Session._2D_get4(Session.this);
            }
            if(Session._2D_get0(Session.this) == 0x8000000000000000L || Session._2D_get0(Session.this) != l)
            {
                Session._2D_set0(Session.this, l);
                Session._2D_wrap1(Session.this, l);
            }
            mHandler.removeCallbacks(Session._2D_get5(Session.this));
            mHandler.postDelayed(Session._2D_get5(Session.this), 1000L);
        }

        final Session this$1;

        private Session.TimeShiftPositionTrackingRunnable()
        {
            this$1 = Session.this;
            super();
        }

        Session.TimeShiftPositionTrackingRunnable(Session.TimeShiftPositionTrackingRunnable timeshiftpositiontrackingrunnable)
        {
            this();
        }
    }


    static RemoteCallbackList _2D_get0(TvInputService tvinputservice)
    {
        return tvinputservice.mCallbacks;
    }

    static Handler _2D_get1(TvInputService tvinputservice)
    {
        return tvinputservice.mServiceHandler;
    }

    static boolean _2D_wrap0(TvInputService tvinputservice, String s)
    {
        return tvinputservice.isPassthroughInput(s);
    }

    public TvInputService()
    {
    }

    public static boolean isNavigationKey(int i)
    {
        switch(i)
        {
        default:
            return false;

        case 19: // '\023'
        case 20: // '\024'
        case 21: // '\025'
        case 22: // '\026'
        case 23: // '\027'
        case 61: // '='
        case 62: // '>'
        case 66: // 'B'
        case 92: // '\\'
        case 93: // ']'
        case 122: // 'z'
        case 123: // '{'
            return true;
        }
    }

    private boolean isPassthroughInput(String s)
    {
        if(mTvInputManager == null)
            mTvInputManager = (TvInputManager)getSystemService("tv_input");
        s = mTvInputManager.getTvInputInfo(s);
        boolean flag;
        if(s != null)
            flag = s.isPassthroughInput();
        else
            flag = false;
        return flag;
    }

    public final IBinder onBind(Intent intent)
    {
        return new ITvInputService.Stub() {

            public void createRecordingSession(ITvInputSessionCallback itvinputsessioncallback, String s)
            {
                if(itvinputsessioncallback == null)
                {
                    return;
                } else
                {
                    SomeArgs someargs = SomeArgs.obtain();
                    someargs.arg1 = itvinputsessioncallback;
                    someargs.arg2 = s;
                    TvInputService._2D_get1(TvInputService.this).obtainMessage(3, someargs).sendToTarget();
                    return;
                }
            }

            public void createSession(InputChannel inputchannel, ITvInputSessionCallback itvinputsessioncallback, String s)
            {
                if(inputchannel == null)
                    Log.w("TvInputService", "Creating session without input channel");
                if(itvinputsessioncallback == null)
                {
                    return;
                } else
                {
                    SomeArgs someargs = SomeArgs.obtain();
                    someargs.arg1 = inputchannel;
                    someargs.arg2 = itvinputsessioncallback;
                    someargs.arg3 = s;
                    TvInputService._2D_get1(TvInputService.this).obtainMessage(1, someargs).sendToTarget();
                    return;
                }
            }

            public void notifyHardwareAdded(TvInputHardwareInfo tvinputhardwareinfo)
            {
                TvInputService._2D_get1(TvInputService.this).obtainMessage(4, tvinputhardwareinfo).sendToTarget();
            }

            public void notifyHardwareRemoved(TvInputHardwareInfo tvinputhardwareinfo)
            {
                TvInputService._2D_get1(TvInputService.this).obtainMessage(5, tvinputhardwareinfo).sendToTarget();
            }

            public void notifyHdmiDeviceAdded(HdmiDeviceInfo hdmideviceinfo)
            {
                TvInputService._2D_get1(TvInputService.this).obtainMessage(6, hdmideviceinfo).sendToTarget();
            }

            public void notifyHdmiDeviceRemoved(HdmiDeviceInfo hdmideviceinfo)
            {
                TvInputService._2D_get1(TvInputService.this).obtainMessage(7, hdmideviceinfo).sendToTarget();
            }

            public void registerCallback(ITvInputServiceCallback itvinputservicecallback)
            {
                if(itvinputservicecallback != null)
                    TvInputService._2D_get0(TvInputService.this).register(itvinputservicecallback);
            }

            public void unregisterCallback(ITvInputServiceCallback itvinputservicecallback)
            {
                if(itvinputservicecallback != null)
                    TvInputService._2D_get0(TvInputService.this).unregister(itvinputservicecallback);
            }

            final TvInputService this$0;

            
            {
                this$0 = TvInputService.this;
                super();
            }
        }
;
    }

    public RecordingSession onCreateRecordingSession(String s)
    {
        return null;
    }

    public abstract Session onCreateSession(String s);

    public TvInputInfo onHardwareAdded(TvInputHardwareInfo tvinputhardwareinfo)
    {
        return null;
    }

    public String onHardwareRemoved(TvInputHardwareInfo tvinputhardwareinfo)
    {
        return null;
    }

    public TvInputInfo onHdmiDeviceAdded(HdmiDeviceInfo hdmideviceinfo)
    {
        return null;
    }

    public String onHdmiDeviceRemoved(HdmiDeviceInfo hdmideviceinfo)
    {
        return null;
    }

    private static final boolean DEBUG = false;
    private static final int DETACH_OVERLAY_VIEW_TIMEOUT_MS = 5000;
    public static final String SERVICE_INTERFACE = "android.media.tv.TvInputService";
    public static final String SERVICE_META_DATA = "android.media.tv.input";
    private static final String TAG = "TvInputService";
    private final RemoteCallbackList mCallbacks = new RemoteCallbackList();
    private final Handler mServiceHandler = new ServiceHandler(null);
    private TvInputManager mTvInputManager;

    // Unreferenced inner class android/media/tv/TvInputService$HardwareSession$1

/* anonymous class */
    class HardwareSession._cls1 extends TvInputManager.SessionCallback
    {

        public void onSessionCreated(TvInputManager.Session session)
        {
            HardwareSession._2D_set0(HardwareSession.this, session);
            SomeArgs someargs = SomeArgs.obtain();
            if(session != null)
            {
                someargs.arg1 = HardwareSession.this;
                someargs.arg2 = HardwareSession._2D_get2(HardwareSession.this);
                someargs.arg3 = HardwareSession._2D_get3(HardwareSession.this);
                someargs.arg4 = session.getToken();
                session.tune(TvContract.buildChannelUriForPassthroughInput(getHardwareInputId()));
            } else
            {
                someargs.arg1 = null;
                someargs.arg2 = null;
                someargs.arg3 = HardwareSession._2D_get3(HardwareSession.this);
                someargs.arg4 = null;
                onRelease();
            }
            HardwareSession._2D_get4(HardwareSession.this).obtainMessage(2, someargs).sendToTarget();
        }

        public void onVideoAvailable(TvInputManager.Session session)
        {
            if(HardwareSession._2D_get0(HardwareSession.this) == session)
                onHardwareVideoAvailable();
        }

        public void onVideoUnavailable(TvInputManager.Session session, int i)
        {
            if(HardwareSession._2D_get0(HardwareSession.this) == session)
                onHardwareVideoUnavailable(i);
        }

        final HardwareSession this$1;

            
            {
                this$1 = HardwareSession.this;
                super();
            }
    }

}
