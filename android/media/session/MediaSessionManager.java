// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.session;

import android.content.ComponentName;
import android.content.Context;
import android.media.IRemoteVolumeController;
import android.os.*;
import android.util.ArrayMap;
import android.util.Log;
import android.view.KeyEvent;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.media.session:
//            ISessionManager, MediaController, ISession

public final class MediaSessionManager
{
    public static abstract class Callback
    {

        public abstract void onAddressedPlayerChanged(ComponentName componentname);

        public abstract void onAddressedPlayerChanged(MediaSession.Token token);

        public abstract void onMediaKeyEventDispatched(KeyEvent keyevent, ComponentName componentname);

        public abstract void onMediaKeyEventDispatched(KeyEvent keyevent, MediaSession.Token token);

        public Callback()
        {
        }
    }

    private static final class CallbackImpl extends ICallback.Stub
    {

        static Callback _2D_get0(CallbackImpl callbackimpl)
        {
            return callbackimpl.mCallback;
        }

        public void onAddressedPlayerChangedToMediaButtonReceiver(ComponentName componentname)
        {
            mHandler.post(componentname. new Runnable() {

                public void run()
                {
                    CallbackImpl._2D_get0(CallbackImpl.this).onAddressedPlayerChanged(mediaButtonReceiver);
                }

                final CallbackImpl this$1;
                final ComponentName val$mediaButtonReceiver;

            
            {
                this$1 = final_callbackimpl;
                mediaButtonReceiver = ComponentName.this;
                super();
            }
            }
);
        }

        public void onAddressedPlayerChangedToMediaSession(MediaSession.Token token)
        {
            mHandler.post(token. new Runnable() {

                public void run()
                {
                    CallbackImpl._2D_get0(CallbackImpl.this).onAddressedPlayerChanged(sessionToken);
                }

                final CallbackImpl this$1;
                final MediaSession.Token val$sessionToken;

            
            {
                this$1 = final_callbackimpl;
                sessionToken = MediaSession.Token.this;
                super();
            }
            }
);
        }

        public void onMediaKeyEventDispatchedToMediaButtonReceiver(final KeyEvent event, ComponentName componentname)
        {
            mHandler.post(componentname. new Runnable() {

                public void run()
                {
                    CallbackImpl._2D_get0(CallbackImpl.this).onMediaKeyEventDispatched(event, mediaButtonReceiver);
                }

                final CallbackImpl this$1;
                final KeyEvent val$event;
                final ComponentName val$mediaButtonReceiver;

            
            {
                this$1 = final_callbackimpl;
                event = keyevent;
                mediaButtonReceiver = ComponentName.this;
                super();
            }
            }
);
        }

        public void onMediaKeyEventDispatchedToMediaSession(final KeyEvent event, MediaSession.Token token)
        {
            mHandler.post(token. new Runnable() {

                public void run()
                {
                    CallbackImpl._2D_get0(CallbackImpl.this).onMediaKeyEventDispatched(event, sessionToken);
                }

                final CallbackImpl this$1;
                final KeyEvent val$event;
                final MediaSession.Token val$sessionToken;

            
            {
                this$1 = final_callbackimpl;
                event = keyevent;
                sessionToken = MediaSession.Token.this;
                super();
            }
            }
);
        }

        private final Callback mCallback;
        private final Handler mHandler;

        public CallbackImpl(Callback callback, Handler handler)
        {
            mCallback = callback;
            mHandler = handler;
        }
    }

    public static interface OnActiveSessionsChangedListener
    {

        public abstract void onActiveSessionsChanged(List list);
    }

    public static interface OnMediaKeyListener
    {

        public abstract boolean onMediaKey(KeyEvent keyevent);
    }

    private static final class OnMediaKeyListenerImpl extends IOnMediaKeyListener.Stub
    {

        static OnMediaKeyListener _2D_get0(OnMediaKeyListenerImpl onmediakeylistenerimpl)
        {
            return onmediakeylistenerimpl.mListener;
        }

        public void onMediaKey(final KeyEvent event, ResultReceiver resultreceiver)
        {
            if(mListener == null || mHandler == null)
            {
                Log.w("SessionManager", "Failed to call media key listener. Either mListener or mHandler is null");
                return;
            } else
            {
                mHandler.post(resultreceiver. new Runnable() {

                    public void run()
                    {
                        boolean flag = OnMediaKeyListenerImpl._2D_get0(OnMediaKeyListenerImpl.this).onMediaKey(event);
                        Log.d("SessionManager", (new StringBuilder()).append("The media key listener is returned ").append(flag).toString());
                        if(result != null)
                        {
                            ResultReceiver resultreceiver = result;
                            int i;
                            if(flag)
                                i = 1;
                            else
                                i = 0;
                            resultreceiver.send(i, null);
                        }
                    }

                    final OnMediaKeyListenerImpl this$1;
                    final KeyEvent val$event;
                    final ResultReceiver val$result;

            
            {
                this$1 = final_onmediakeylistenerimpl;
                event = keyevent;
                result = ResultReceiver.this;
                super();
            }
                }
);
                return;
            }
        }

        private Handler mHandler;
        private OnMediaKeyListener mListener;

        public OnMediaKeyListenerImpl(OnMediaKeyListener onmediakeylistener, Handler handler)
        {
            mListener = onmediakeylistener;
            mHandler = handler;
        }
    }

    public static interface OnVolumeKeyLongPressListener
    {

        public abstract void onVolumeKeyLongPress(KeyEvent keyevent);
    }

    private static final class OnVolumeKeyLongPressListenerImpl extends IOnVolumeKeyLongPressListener.Stub
    {

        static OnVolumeKeyLongPressListener _2D_get0(OnVolumeKeyLongPressListenerImpl onvolumekeylongpresslistenerimpl)
        {
            return onvolumekeylongpresslistenerimpl.mListener;
        }

        public void onVolumeKeyLongPress(KeyEvent keyevent)
        {
            if(mListener == null || mHandler == null)
            {
                Log.w("SessionManager", "Failed to call volume key long-press listener. Either mListener or mHandler is null");
                return;
            } else
            {
                mHandler.post(keyevent. new Runnable() {

                    public void run()
                    {
                        OnVolumeKeyLongPressListenerImpl._2D_get0(OnVolumeKeyLongPressListenerImpl.this).onVolumeKeyLongPress(event);
                    }

                    final OnVolumeKeyLongPressListenerImpl this$1;
                    final KeyEvent val$event;

            
            {
                this$1 = final_onvolumekeylongpresslistenerimpl;
                event = KeyEvent.this;
                super();
            }
                }
);
                return;
            }
        }

        private Handler mHandler;
        private OnVolumeKeyLongPressListener mListener;

        public OnVolumeKeyLongPressListenerImpl(OnVolumeKeyLongPressListener onvolumekeylongpresslistener, Handler handler)
        {
            mListener = onvolumekeylongpresslistener;
            mHandler = handler;
        }
    }

    private static final class SessionsChangedWrapper
    {

        static Context _2D_get0(SessionsChangedWrapper sessionschangedwrapper)
        {
            return sessionschangedwrapper.mContext;
        }

        static Handler _2D_get1(SessionsChangedWrapper sessionschangedwrapper)
        {
            return sessionschangedwrapper.mHandler;
        }

        static OnActiveSessionsChangedListener _2D_get2(SessionsChangedWrapper sessionschangedwrapper)
        {
            return sessionschangedwrapper.mListener;
        }

        static IActiveSessionsListener.Stub _2D_get3(SessionsChangedWrapper sessionschangedwrapper)
        {
            return sessionschangedwrapper.mStub;
        }

        static void _2D_wrap0(SessionsChangedWrapper sessionschangedwrapper)
        {
            sessionschangedwrapper.release();
        }

        private void release()
        {
            mListener = null;
            mContext = null;
            mHandler = null;
        }

        private Context mContext;
        private Handler mHandler;
        private OnActiveSessionsChangedListener mListener;
        private final IActiveSessionsListener.Stub mStub = new _cls1();

        public SessionsChangedWrapper(Context context, OnActiveSessionsChangedListener onactivesessionschangedlistener, Handler handler)
        {
            mContext = context;
            mListener = onactivesessionschangedlistener;
            mHandler = handler;
        }
    }


    public MediaSessionManager(Context context)
    {
        mContext = context;
    }

    public void addOnActiveSessionsChangedListener(OnActiveSessionsChangedListener onactivesessionschangedlistener, ComponentName componentname)
    {
        addOnActiveSessionsChangedListener(onactivesessionschangedlistener, componentname, null);
    }

    public void addOnActiveSessionsChangedListener(OnActiveSessionsChangedListener onactivesessionschangedlistener, ComponentName componentname, int i, Handler handler)
    {
        Handler handler1;
        if(onactivesessionschangedlistener == null)
            throw new IllegalArgumentException("listener may not be null");
        handler1 = handler;
        if(handler == null)
            handler1 = new Handler();
        handler = ((Handler) (mLock));
        handler;
        JVM INSTR monitorenter ;
        if(mListeners.get(onactivesessionschangedlistener) == null)
            break MISSING_BLOCK_LABEL_64;
        Log.w("SessionManager", "Attempted to add session listener twice, ignoring.");
        handler;
        JVM INSTR monitorexit ;
        return;
        SessionsChangedWrapper sessionschangedwrapper;
        sessionschangedwrapper = JVM INSTR new #39  <Class MediaSessionManager$SessionsChangedWrapper>;
        sessionschangedwrapper.SessionsChangedWrapper(mContext, onactivesessionschangedlistener, handler1);
        mService.addSessionsListener(SessionsChangedWrapper._2D_get3(sessionschangedwrapper), componentname, i);
        mListeners.put(onactivesessionschangedlistener, sessionschangedwrapper);
_L2:
        handler;
        JVM INSTR monitorexit ;
        return;
        onactivesessionschangedlistener;
        Log.e("SessionManager", "Error in addOnActiveSessionsChangedListener.", onactivesessionschangedlistener);
        if(true) goto _L2; else goto _L1
_L1:
        onactivesessionschangedlistener;
        throw onactivesessionschangedlistener;
    }

    public void addOnActiveSessionsChangedListener(OnActiveSessionsChangedListener onactivesessionschangedlistener, ComponentName componentname, Handler handler)
    {
        addOnActiveSessionsChangedListener(onactivesessionschangedlistener, componentname, UserHandle.myUserId(), handler);
    }

    public ISession createSession(MediaSession.CallbackStub callbackstub, String s, int i)
        throws RemoteException
    {
        return mService.createSession(mContext.getPackageName(), callbackstub, s, i);
    }

    public void dispatchAdjustVolume(int i, int j, int k)
    {
        mService.dispatchAdjustVolume(i, j, k);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("SessionManager", "Failed to send adjust volume.", remoteexception);
          goto _L1
    }

    public void dispatchMediaKeyEvent(KeyEvent keyevent)
    {
        dispatchMediaKeyEvent(keyevent, false);
    }

    public void dispatchMediaKeyEvent(KeyEvent keyevent, boolean flag)
    {
        mService.dispatchMediaKeyEvent(keyevent, flag);
_L1:
        return;
        keyevent;
        Log.e("SessionManager", "Failed to send key event.", keyevent);
          goto _L1
    }

    public void dispatchVolumeKeyEvent(KeyEvent keyevent, int i, boolean flag)
    {
        mService.dispatchVolumeKeyEvent(keyevent, i, flag);
_L1:
        return;
        keyevent;
        Log.e("SessionManager", "Failed to send volume key event.", keyevent);
          goto _L1
    }

    public List getActiveSessions(ComponentName componentname)
    {
        return getActiveSessionsForUser(componentname, UserHandle.myUserId());
    }

    public List getActiveSessionsForUser(ComponentName componentname, int i)
    {
        ArrayList arraylist = new ArrayList();
        int j;
        componentname = mService.getSessions(componentname, i);
        j = componentname.size();
        i = 0;
_L2:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        MediaController mediacontroller = JVM INSTR new #213 <Class MediaController>;
        mediacontroller.MediaController(mContext, ISessionController.Stub.asInterface((IBinder)componentname.get(i)));
        arraylist.add(mediacontroller);
        i++;
        if(true) goto _L2; else goto _L1
        componentname;
        Log.e("SessionManager", "Failed to get active sessions: ", componentname);
_L1:
        return arraylist;
    }

    public boolean isGlobalPriorityActive()
    {
        boolean flag;
        try
        {
            flag = mService.isGlobalPriorityActive();
        }
        catch(RemoteException remoteexception)
        {
            Log.e("SessionManager", "Failed to check if the global priority is active.", remoteexception);
            return false;
        }
        return flag;
    }

    public void removeOnActiveSessionsChangedListener(OnActiveSessionsChangedListener onactivesessionschangedlistener)
    {
        if(onactivesessionschangedlistener == null)
            throw new IllegalArgumentException("listener may not be null");
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        onactivesessionschangedlistener = (SessionsChangedWrapper)mListeners.remove(onactivesessionschangedlistener);
        if(onactivesessionschangedlistener == null)
            break MISSING_BLOCK_LABEL_54;
        mService.removeSessionsListener(SessionsChangedWrapper._2D_get3(onactivesessionschangedlistener));
        SessionsChangedWrapper._2D_wrap0(onactivesessionschangedlistener);
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        Object obj1;
        obj1;
        Log.e("SessionManager", "Error in removeOnActiveSessionsChangedListener.", ((Throwable) (obj1)));
        SessionsChangedWrapper._2D_wrap0(onactivesessionschangedlistener);
          goto _L1
        onactivesessionschangedlistener;
        throw onactivesessionschangedlistener;
        obj1;
        SessionsChangedWrapper._2D_wrap0(onactivesessionschangedlistener);
        throw obj1;
    }

    public void setCallback(Callback callback, Handler handler)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(callback != null) goto _L2; else goto _L1
_L1:
        mCallback = null;
        mService.setCallback(null);
_L3:
        obj;
        JVM INSTR monitorexit ;
        return;
_L2:
        Handler handler1;
        handler1 = handler;
        if(handler != null)
            break MISSING_BLOCK_LABEL_46;
        handler1 = JVM INSTR new #116 <Class Handler>;
        handler1.Handler();
        handler = JVM INSTR new #9   <Class MediaSessionManager$CallbackImpl>;
        handler.CallbackImpl(callback, handler1);
        mCallback = handler;
        mService.setCallback(mCallback);
          goto _L3
        callback;
        Log.e("SessionManager", "Failed to set media key callback", callback);
          goto _L3
        callback;
        throw callback;
    }

    public void setOnMediaKeyListener(OnMediaKeyListener onmediakeylistener, Handler handler)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(onmediakeylistener != null) goto _L2; else goto _L1
_L1:
        mOnMediaKeyListener = null;
        mService.setOnMediaKeyListener(null);
_L3:
        obj;
        JVM INSTR monitorexit ;
        return;
_L2:
        Handler handler1;
        handler1 = handler;
        if(handler != null)
            break MISSING_BLOCK_LABEL_46;
        handler1 = JVM INSTR new #116 <Class Handler>;
        handler1.Handler();
        handler = JVM INSTR new #26  <Class MediaSessionManager$OnMediaKeyListenerImpl>;
        handler.OnMediaKeyListenerImpl(onmediakeylistener, handler1);
        mOnMediaKeyListener = handler;
        mService.setOnMediaKeyListener(mOnMediaKeyListener);
          goto _L3
        onmediakeylistener;
        Log.e("SessionManager", "Failed to set media key listener", onmediakeylistener);
          goto _L3
        onmediakeylistener;
        throw onmediakeylistener;
    }

    public void setOnVolumeKeyLongPressListener(OnVolumeKeyLongPressListener onvolumekeylongpresslistener, Handler handler)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(onvolumekeylongpresslistener != null) goto _L2; else goto _L1
_L1:
        mOnVolumeKeyLongPressListener = null;
        mService.setOnVolumeKeyLongPressListener(null);
_L3:
        obj;
        JVM INSTR monitorexit ;
        return;
_L2:
        Handler handler1;
        handler1 = handler;
        if(handler != null)
            break MISSING_BLOCK_LABEL_46;
        handler1 = JVM INSTR new #116 <Class Handler>;
        handler1.Handler();
        handler = JVM INSTR new #34  <Class MediaSessionManager$OnVolumeKeyLongPressListenerImpl>;
        handler.OnVolumeKeyLongPressListenerImpl(onvolumekeylongpresslistener, handler1);
        mOnVolumeKeyLongPressListener = handler;
        mService.setOnVolumeKeyLongPressListener(mOnVolumeKeyLongPressListener);
          goto _L3
        onvolumekeylongpresslistener;
        Log.e("SessionManager", "Failed to set volume key long press listener", onvolumekeylongpresslistener);
          goto _L3
        onvolumekeylongpresslistener;
        throw onvolumekeylongpresslistener;
    }

    public void setRemoteVolumeController(IRemoteVolumeController iremotevolumecontroller)
    {
        mService.setRemoteVolumeController(iremotevolumecontroller);
_L1:
        return;
        iremotevolumecontroller;
        Log.e("SessionManager", "Error in setRemoteVolumeController.", iremotevolumecontroller);
          goto _L1
    }

    public static final int RESULT_MEDIA_KEY_HANDLED = 1;
    public static final int RESULT_MEDIA_KEY_NOT_HANDLED = 0;
    private static final String TAG = "SessionManager";
    private CallbackImpl mCallback;
    private Context mContext;
    private final ArrayMap mListeners = new ArrayMap();
    private final Object mLock = new Object();
    private OnMediaKeyListenerImpl mOnMediaKeyListener;
    private OnVolumeKeyLongPressListenerImpl mOnVolumeKeyLongPressListener;
    private final ISessionManager mService = ISessionManager.Stub.asInterface(ServiceManager.getService("media_session"));

    // Unreferenced inner class android/media/session/MediaSessionManager$SessionsChangedWrapper$1

/* anonymous class */
    class SessionsChangedWrapper._cls1 extends IActiveSessionsListener.Stub
    {

        public void onActiveSessionsChanged(List list)
        {
            Handler handler = SessionsChangedWrapper._2D_get1(SessionsChangedWrapper.this);
            if(handler != null)
                handler.post(list. new Runnable() {

                    public void run()
                    {
    class SessionsChangedWrapper._cls1 extends IActiveSessionsListener.Stub
    {

        public void onActiveSessionsChanged(List list)
        {
            Handler handler = SessionsChangedWrapper._2D_get1(SessionsChangedWrapper.this);
            if(handler != null)
                handler.post(list. new Runnable()                         Object obj = SessionsChangedWrapper._2D_get0(_fld1);
                        if(obj != null)
                        {
                            ArrayList arraylist = new ArrayList();
                            int i = tokens.size();
                            for(int j = 0; j < i; j++)
                                arraylist.add(new MediaController(((Context) (obj)), (MediaSession.Token)tokens.get(j)));

                            obj = SessionsChangedWrapper._2D_get2(_fld1);
                            if(obj != null)
                                ((OnActiveSessionsChangedListener) (obj)).onActiveSessionsChanged(arraylist);
                        }
                    }

                    final SessionsChangedWrapper._cls1 this$2;
                    final List val$tokens;

            
            {
                this$2 = final__pcls1;
                tokens = List.this;
                super();
            }
                }
);
        }

        final SessionsChangedWrapper this$1;

            
            {
                this$1 = SessionsChangedWrapper.this;
                super();
            }
    }

}
