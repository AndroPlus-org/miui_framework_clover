// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.tv;

import android.content.Context;
import android.graphics.*;
import android.media.PlaybackParams;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.*;
import android.view.*;
import java.lang.ref.WeakReference;
import java.util.*;

// Referenced classes of package android.media.tv:
//            TvInputManager, TvContentRating

public class TvView extends ViewGroup
{
    private class MySessionCallback extends TvInputManager.SessionCallback
    {

        public void onChannelRetuned(TvInputManager.Session session, Uri uri)
        {
            if(this != TvView._2D_get4(TvView.this))
            {
                Log.w("TvView", "onChannelRetuned - session not created");
                return;
            }
            if(TvView._2D_get0(TvView.this) != null)
                TvView._2D_get0(TvView.this).onChannelRetuned(mInputId, uri);
        }

        public void onContentAllowed(TvInputManager.Session session)
        {
            if(this != TvView._2D_get4(TvView.this))
            {
                Log.w("TvView", "onContentAllowed - session not created");
                return;
            }
            if(TvView._2D_get0(TvView.this) != null)
                TvView._2D_get0(TvView.this).onContentAllowed(mInputId);
        }

        public void onContentBlocked(TvInputManager.Session session, TvContentRating tvcontentrating)
        {
            if(this != TvView._2D_get4(TvView.this))
            {
                Log.w("TvView", "onContentBlocked - session not created");
                return;
            }
            if(TvView._2D_get0(TvView.this) != null)
                TvView._2D_get0(TvView.this).onContentBlocked(mInputId, tvcontentrating);
        }

        public void onLayoutSurface(TvInputManager.Session session, int i, int j, int k, int l)
        {
            if(this != TvView._2D_get4(TvView.this))
            {
                Log.w("TvView", "onLayoutSurface - session not created");
                return;
            } else
            {
                TvView._2D_set9(TvView.this, i);
                TvView._2D_set11(TvView.this, j);
                TvView._2D_set10(TvView.this, k);
                TvView._2D_set8(TvView.this, l);
                TvView._2D_set13(TvView.this, true);
                requestLayout();
                return;
            }
        }

        public void onSessionCreated(TvInputManager.Session session)
        {
            if(this != TvView._2D_get4(TvView.this))
            {
                Log.w("TvView", "onSessionCreated - session already created");
                if(session != null)
                    session.release();
                return;
            }
            TvView._2D_set2(TvView.this, session);
            if(session == null) goto _L2; else goto _L1
_L1:
            for(Iterator iterator = TvView._2D_get2(TvView.this).iterator(); iterator.hasNext(); TvView._2D_get3(TvView.this).sendAppPrivateCommand((String)((Pair) (session)).first, (Bundle)((Pair) (session)).second))
                session = (Pair)iterator.next();

            TvView._2D_get2(TvView.this).clear();
            Object obj = TvView._2D_get13();
            obj;
            JVM INSTR monitorenter ;
            if(hasWindowFocus() && TvView.this == TvView._2D_get12().get() && TvView._2D_wrap0(TvView.this))
                TvView._2D_get3(TvView.this).setMain();
            obj;
            JVM INSTR monitorexit ;
            if(TvView._2D_get6(TvView.this) != null)
            {
                TvView._2D_wrap5(TvView.this, TvView._2D_get6(TvView.this));
                if(TvView._2D_get7(TvView.this))
                    TvView._2D_wrap2(TvView.this, TvView._2D_get8(TvView.this), TvView._2D_get10(TvView.this), TvView._2D_get9(TvView.this));
            }
            TvView._2D_wrap1(TvView.this);
            if(TvView._2D_get5(TvView.this) != null)
                TvView._2D_get3(TvView.this).setStreamVolume(TvView._2D_get5(TvView.this).floatValue());
            if(TvView._2D_get1(TvView.this) != null)
                TvView._2D_get3(TvView.this).setCaptionEnabled(TvView._2D_get1(TvView.this).booleanValue());
            if(mChannelUri != null)
                TvView._2D_get3(TvView.this).tune(mChannelUri, mTuneParams);
            else
                TvView._2D_get3(TvView.this).timeShiftPlay(mRecordedProgramUri);
            TvView._2D_wrap3(TvView.this);
_L4:
            return;
            session;
            throw session;
_L2:
            TvView._2D_set3(TvView.this, null);
            if(TvView._2D_get0(TvView.this) != null)
                TvView._2D_get0(TvView.this).onConnectionFailed(mInputId);
            if(true) goto _L4; else goto _L3
_L3:
        }

        public void onSessionEvent(TvInputManager.Session session, String s, Bundle bundle)
        {
            if(this != TvView._2D_get4(TvView.this))
            {
                Log.w("TvView", "onSessionEvent - session not created");
                return;
            }
            if(TvView._2D_get0(TvView.this) != null)
                TvView._2D_get0(TvView.this).onEvent(mInputId, s, bundle);
        }

        public void onSessionReleased(TvInputManager.Session session)
        {
            if(this != TvView._2D_get4(TvView.this))
            {
                Log.w("TvView", "onSessionReleased - session not created");
                return;
            }
            TvView._2D_set0(TvView.this, false);
            TvView._2D_set1(TvView.this, null);
            TvView._2D_set3(TvView.this, null);
            TvView._2D_set2(TvView.this, null);
            if(TvView._2D_get0(TvView.this) != null)
                TvView._2D_get0(TvView.this).onDisconnected(mInputId);
        }

        public void onTimeShiftCurrentPositionChanged(TvInputManager.Session session, long l)
        {
            if(this != TvView._2D_get4(TvView.this))
            {
                Log.w("TvView", "onTimeShiftCurrentPositionChanged - session not created");
                return;
            }
            if(TvView._2D_get11(TvView.this) != null)
                TvView._2D_get11(TvView.this).onTimeShiftCurrentPositionChanged(mInputId, l);
        }

        public void onTimeShiftStartPositionChanged(TvInputManager.Session session, long l)
        {
            if(this != TvView._2D_get4(TvView.this))
            {
                Log.w("TvView", "onTimeShiftStartPositionChanged - session not created");
                return;
            }
            if(TvView._2D_get11(TvView.this) != null)
                TvView._2D_get11(TvView.this).onTimeShiftStartPositionChanged(mInputId, l);
        }

        public void onTimeShiftStatusChanged(TvInputManager.Session session, int i)
        {
            if(this != TvView._2D_get4(TvView.this))
            {
                Log.w("TvView", "onTimeShiftStatusChanged - session not created");
                return;
            }
            if(TvView._2D_get0(TvView.this) != null)
                TvView._2D_get0(TvView.this).onTimeShiftStatusChanged(mInputId, i);
        }

        public void onTrackSelected(TvInputManager.Session session, int i, String s)
        {
            if(this != TvView._2D_get4(TvView.this))
            {
                Log.w("TvView", "onTrackSelected - session not created");
                return;
            }
            if(TvView._2D_get0(TvView.this) != null)
                TvView._2D_get0(TvView.this).onTrackSelected(mInputId, i, s);
        }

        public void onTracksChanged(TvInputManager.Session session, List list)
        {
            if(this != TvView._2D_get4(TvView.this))
            {
                Log.w("TvView", "onTracksChanged - session not created");
                return;
            }
            if(TvView._2D_get0(TvView.this) != null)
                TvView._2D_get0(TvView.this).onTracksChanged(mInputId, list);
        }

        public void onVideoAvailable(TvInputManager.Session session)
        {
            if(this != TvView._2D_get4(TvView.this))
            {
                Log.w("TvView", "onVideoAvailable - session not created");
                return;
            }
            if(TvView._2D_get0(TvView.this) != null)
                TvView._2D_get0(TvView.this).onVideoAvailable(mInputId);
        }

        public void onVideoSizeChanged(TvInputManager.Session session, int i, int j)
        {
            if(this != TvView._2D_get4(TvView.this))
            {
                Log.w("TvView", "onVideoSizeChanged - session not created");
                return;
            }
            if(TvView._2D_get0(TvView.this) != null)
                TvView._2D_get0(TvView.this).onVideoSizeChanged(mInputId, i, j);
        }

        public void onVideoUnavailable(TvInputManager.Session session, int i)
        {
            if(this != TvView._2D_get4(TvView.this))
            {
                Log.w("TvView", "onVideoUnavailable - session not created");
                return;
            }
            if(TvView._2D_get0(TvView.this) != null)
                TvView._2D_get0(TvView.this).onVideoUnavailable(mInputId, i);
        }

        Uri mChannelUri;
        final String mInputId;
        Uri mRecordedProgramUri;
        Bundle mTuneParams;
        final TvView this$0;

        MySessionCallback(String s, Uri uri)
        {
            this$0 = TvView.this;
            super();
            mInputId = s;
            mRecordedProgramUri = uri;
        }

        MySessionCallback(String s, Uri uri, Bundle bundle)
        {
            this$0 = TvView.this;
            super();
            mInputId = s;
            mChannelUri = uri;
            mTuneParams = bundle;
        }
    }

    public static interface OnUnhandledInputEventListener
    {

        public abstract boolean onUnhandledInputEvent(InputEvent inputevent);
    }

    public static abstract class TimeShiftPositionCallback
    {

        public void onTimeShiftCurrentPositionChanged(String s, long l)
        {
        }

        public void onTimeShiftStartPositionChanged(String s, long l)
        {
        }

        public TimeShiftPositionCallback()
        {
        }
    }

    public static abstract class TvInputCallback
    {

        public void onChannelRetuned(String s, Uri uri)
        {
        }

        public void onConnectionFailed(String s)
        {
        }

        public void onContentAllowed(String s)
        {
        }

        public void onContentBlocked(String s, TvContentRating tvcontentrating)
        {
        }

        public void onDisconnected(String s)
        {
        }

        public void onEvent(String s, String s1, Bundle bundle)
        {
        }

        public void onTimeShiftStatusChanged(String s, int i)
        {
        }

        public void onTrackSelected(String s, int i, String s1)
        {
        }

        public void onTracksChanged(String s, List list)
        {
        }

        public void onVideoAvailable(String s)
        {
        }

        public void onVideoSizeChanged(String s, int i, int j)
        {
        }

        public void onVideoUnavailable(String s, int i)
        {
        }

        public TvInputCallback()
        {
        }
    }


    static TvInputCallback _2D_get0(TvView tvview)
    {
        return tvview.mCallback;
    }

    static Boolean _2D_get1(TvView tvview)
    {
        return tvview.mCaptionEnabled;
    }

    static int _2D_get10(TvView tvview)
    {
        return tvview.mSurfaceWidth;
    }

    static TimeShiftPositionCallback _2D_get11(TvView tvview)
    {
        return tvview.mTimeShiftPositionCallback;
    }

    static WeakReference _2D_get12()
    {
        return sMainTvView;
    }

    static Object _2D_get13()
    {
        return sMainTvViewLock;
    }

    static Queue _2D_get2(TvView tvview)
    {
        return tvview.mPendingAppPrivateCommands;
    }

    static TvInputManager.Session _2D_get3(TvView tvview)
    {
        return tvview.mSession;
    }

    static MySessionCallback _2D_get4(TvView tvview)
    {
        return tvview.mSessionCallback;
    }

    static Float _2D_get5(TvView tvview)
    {
        return tvview.mStreamVolume;
    }

    static Surface _2D_get6(TvView tvview)
    {
        return tvview.mSurface;
    }

    static boolean _2D_get7(TvView tvview)
    {
        return tvview.mSurfaceChanged;
    }

    static int _2D_get8(TvView tvview)
    {
        return tvview.mSurfaceFormat;
    }

    static int _2D_get9(TvView tvview)
    {
        return tvview.mSurfaceHeight;
    }

    static boolean _2D_set0(TvView tvview, boolean flag)
    {
        tvview.mOverlayViewCreated = flag;
        return flag;
    }

    static Rect _2D_set1(TvView tvview, Rect rect)
    {
        tvview.mOverlayViewFrame = rect;
        return rect;
    }

    static int _2D_set10(TvView tvview, int i)
    {
        tvview.mSurfaceViewRight = i;
        return i;
    }

    static int _2D_set11(TvView tvview, int i)
    {
        tvview.mSurfaceViewTop = i;
        return i;
    }

    static int _2D_set12(TvView tvview, int i)
    {
        tvview.mSurfaceWidth = i;
        return i;
    }

    static boolean _2D_set13(TvView tvview, boolean flag)
    {
        tvview.mUseRequestedSurfaceLayout = flag;
        return flag;
    }

    static TvInputManager.Session _2D_set2(TvView tvview, TvInputManager.Session session)
    {
        tvview.mSession = session;
        return session;
    }

    static MySessionCallback _2D_set3(TvView tvview, MySessionCallback mysessioncallback)
    {
        tvview.mSessionCallback = mysessioncallback;
        return mysessioncallback;
    }

    static Surface _2D_set4(TvView tvview, Surface surface)
    {
        tvview.mSurface = surface;
        return surface;
    }

    static boolean _2D_set5(TvView tvview, boolean flag)
    {
        tvview.mSurfaceChanged = flag;
        return flag;
    }

    static int _2D_set6(TvView tvview, int i)
    {
        tvview.mSurfaceFormat = i;
        return i;
    }

    static int _2D_set7(TvView tvview, int i)
    {
        tvview.mSurfaceHeight = i;
        return i;
    }

    static int _2D_set8(TvView tvview, int i)
    {
        tvview.mSurfaceViewBottom = i;
        return i;
    }

    static int _2D_set9(TvView tvview, int i)
    {
        tvview.mSurfaceViewLeft = i;
        return i;
    }

    static boolean _2D_wrap0(TvView tvview)
    {
        return tvview.checkChangeHdmiCecActiveSourcePermission();
    }

    static void _2D_wrap1(TvView tvview)
    {
        tvview.createSessionOverlayView();
    }

    static void _2D_wrap2(TvView tvview, int i, int j, int k)
    {
        tvview.dispatchSurfaceChanged(i, j, k);
    }

    static void _2D_wrap3(TvView tvview)
    {
        tvview.ensurePositionTracking();
    }

    static void _2D_wrap4(TvView tvview)
    {
        tvview.relayoutSessionOverlayView();
    }

    static void _2D_wrap5(TvView tvview, Surface surface)
    {
        tvview.setSessionSurface(surface);
    }

    public TvView(Context context)
    {
        this(context, null, 0);
    }

    public TvView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public TvView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mHandler = new Handler();
        mPendingAppPrivateCommands = new ArrayDeque();
        mSurfaceHolderCallback = new android.view.SurfaceHolder.Callback() {

            public void surfaceChanged(SurfaceHolder surfaceholder, int j, int k, int l)
            {
                TvView._2D_set6(TvView.this, j);
                TvView._2D_set12(TvView.this, k);
                TvView._2D_set7(TvView.this, l);
                TvView._2D_set5(TvView.this, true);
                TvView._2D_wrap2(TvView.this, TvView._2D_get8(TvView.this), TvView._2D_get10(TvView.this), TvView._2D_get9(TvView.this));
            }

            public void surfaceCreated(SurfaceHolder surfaceholder)
            {
                TvView._2D_set4(TvView.this, surfaceholder.getSurface());
                TvView._2D_wrap5(TvView.this, TvView._2D_get6(TvView.this));
            }

            public void surfaceDestroyed(SurfaceHolder surfaceholder)
            {
                TvView._2D_set4(TvView.this, null);
                TvView._2D_set5(TvView.this, false);
                TvView._2D_wrap5(TvView.this, null);
            }

            final TvView this$0;

            
            {
                this$0 = TvView.this;
                super();
            }
        }
;
        mFinishedInputEventCallback = new TvInputManager.Session.FinishedInputEventCallback() {

            public void onFinishedInputEvent(Object obj, boolean flag)
            {
                if(flag)
                    return;
                obj = (InputEvent)obj;
                if(dispatchUnhandledInputEvent(((InputEvent) (obj))))
                    return;
                ViewRootImpl viewrootimpl = getViewRootImpl();
                if(viewrootimpl != null)
                    viewrootimpl.dispatchUnhandledInputEvent(((InputEvent) (obj)));
            }

            final TvView this$0;

            
            {
                this$0 = TvView.this;
                super();
            }
        }
;
        mAttrs = attributeset;
        mDefStyleAttr = i;
        resetSurfaceView();
        mTvInputManager = (TvInputManager)getContext().getSystemService("tv_input");
    }

    private boolean checkChangeHdmiCecActiveSourcePermission()
    {
        boolean flag = false;
        if(getContext().checkSelfPermission("android.permission.CHANGE_HDMI_CEC_ACTIVE_SOURCE") == 0)
            flag = true;
        return flag;
    }

    private void createSessionOverlayView()
    {
        if(mSession == null || isAttachedToWindow() ^ true || mOverlayViewCreated || mWindowZOrder != 0)
        {
            return;
        } else
        {
            mOverlayViewFrame = getViewFrameOnScreen();
            mSession.createOverlayView(this, mOverlayViewFrame);
            mOverlayViewCreated = true;
            return;
        }
    }

    private void dispatchSurfaceChanged(int i, int j, int k)
    {
        if(mSession == null)
        {
            return;
        } else
        {
            mSession.dispatchSurfaceChanged(i, j, k);
            return;
        }
    }

    private void ensurePositionTracking()
    {
        if(mSession == null)
            return;
        TvInputManager.Session session = mSession;
        boolean flag;
        if(mTimeShiftPositionCallback != null)
            flag = true;
        else
            flag = false;
        session.timeShiftEnablePositionTracking(flag);
    }

    private Rect getViewFrameOnScreen()
    {
        Rect rect = new Rect();
        getGlobalVisibleRect(rect);
        RectF rectf = new RectF(rect);
        getMatrix().mapRect(rectf);
        rectf.round(rect);
        return rect;
    }

    private void relayoutSessionOverlayView()
    {
        if(mSession == null || isAttachedToWindow() ^ true || mOverlayViewCreated ^ true || mWindowZOrder != 0)
            return;
        Rect rect = getViewFrameOnScreen();
        if(rect.equals(mOverlayViewFrame))
        {
            return;
        } else
        {
            mSession.relayoutOverlayView(rect);
            mOverlayViewFrame = rect;
            return;
        }
    }

    private void removeSessionOverlayView()
    {
        if(mSession == null || mOverlayViewCreated ^ true)
        {
            return;
        } else
        {
            mSession.removeOverlayView();
            mOverlayViewCreated = false;
            mOverlayViewFrame = null;
            return;
        }
    }

    private void resetInternal()
    {
        mSessionCallback = null;
        mPendingAppPrivateCommands.clear();
        if(mSession != null)
        {
            setSessionSurface(null);
            removeSessionOverlayView();
            mUseRequestedSurfaceLayout = false;
            mSession.release();
            mSession = null;
            resetSurfaceView();
        }
    }

    private void resetSurfaceView()
    {
        if(mSurfaceView != null)
        {
            mSurfaceView.getHolder().removeCallback(mSurfaceHolderCallback);
            removeView(mSurfaceView);
        }
        mSurface = null;
        mSurfaceView = new SurfaceView(getContext(), mAttrs, mDefStyleAttr) {

            protected void updateSurface()
            {
                super.updateSurface();
                TvView._2D_wrap4(TvView.this);
            }

            final TvView this$0;

            
            {
                this$0 = TvView.this;
                super(context, attributeset, i);
            }
        }
;
        mSurfaceView.setSecure(true);
        mSurfaceView.getHolder().addCallback(mSurfaceHolderCallback);
        if(mWindowZOrder != 1) goto _L2; else goto _L1
_L1:
        mSurfaceView.setZOrderMediaOverlay(true);
_L4:
        addView(mSurfaceView);
        return;
_L2:
        if(mWindowZOrder == 2)
            mSurfaceView.setZOrderOnTop(true);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void setSessionSurface(Surface surface)
    {
        if(mSession == null)
        {
            return;
        } else
        {
            mSession.setSurface(surface);
            return;
        }
    }

    protected void dispatchDraw(Canvas canvas)
    {
        if(mWindowZOrder != 2)
            canvas.drawColor(0, android.graphics.PorterDuff.Mode.CLEAR);
        super.dispatchDraw(canvas);
    }

    public boolean dispatchGenericMotionEvent(MotionEvent motionevent)
    {
        boolean flag = true;
        if(super.dispatchGenericMotionEvent(motionevent))
            return true;
        if(mSession == null)
            return false;
        motionevent = motionevent.copy();
        if(mSession.dispatchInputEvent(motionevent, motionevent, mFinishedInputEventCallback, mHandler) == 0)
            flag = false;
        return flag;
    }

    public boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        boolean flag = true;
        if(super.dispatchKeyEvent(keyevent))
            return true;
        if(mSession == null)
            return false;
        keyevent = keyevent.copy();
        if(mSession.dispatchInputEvent(keyevent, keyevent, mFinishedInputEventCallback, mHandler) == 0)
            flag = false;
        return flag;
    }

    public boolean dispatchTouchEvent(MotionEvent motionevent)
    {
        boolean flag = true;
        if(super.dispatchTouchEvent(motionevent))
            return true;
        if(mSession == null)
            return false;
        motionevent = motionevent.copy();
        if(mSession.dispatchInputEvent(motionevent, motionevent, mFinishedInputEventCallback, mHandler) == 0)
            flag = false;
        return flag;
    }

    public boolean dispatchTrackballEvent(MotionEvent motionevent)
    {
        boolean flag = true;
        if(super.dispatchTrackballEvent(motionevent))
            return true;
        if(mSession == null)
            return false;
        motionevent = motionevent.copy();
        if(mSession.dispatchInputEvent(motionevent, motionevent, mFinishedInputEventCallback, mHandler) == 0)
            flag = false;
        return flag;
    }

    public boolean dispatchUnhandledInputEvent(InputEvent inputevent)
    {
        if(mOnUnhandledInputEventListener != null && mOnUnhandledInputEventListener.onUnhandledInputEvent(inputevent))
            return true;
        else
            return onUnhandledInputEvent(inputevent);
    }

    public void dispatchWindowFocusChanged(boolean flag)
    {
        super.dispatchWindowFocusChanged(flag);
        Object obj = sMainTvViewLock;
        obj;
        JVM INSTR monitorenter ;
        if(!flag)
            break MISSING_BLOCK_LABEL_46;
        if(this == sMainTvView.get() && mSession != null && checkChangeHdmiCecActiveSourcePermission())
            mSession.setMain();
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void draw(Canvas canvas)
    {
        if(mWindowZOrder != 2)
            canvas.drawColor(0, android.graphics.PorterDuff.Mode.CLEAR);
        super.draw(canvas);
    }

    public boolean gatherTransparentRegion(Region region)
    {
        if(mWindowZOrder != 2 && region != null)
        {
            int i = getWidth();
            int j = getHeight();
            if(i > 0 && j > 0)
            {
                int ai[] = new int[2];
                getLocationInWindow(ai);
                int k = ai[0];
                int l = ai[1];
                region.op(k, l, k + i, l + j, android.graphics.Region.Op.UNION);
            }
        }
        return super.gatherTransparentRegion(region);
    }

    public String getSelectedTrack(int i)
    {
        if(mSession == null)
            return null;
        else
            return mSession.getSelectedTrack(i);
    }

    public List getTracks(int i)
    {
        if(mSession == null)
            return null;
        else
            return mSession.getTracks(i);
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        createSessionOverlayView();
    }

    protected void onDetachedFromWindow()
    {
        removeSessionOverlayView();
        super.onDetachedFromWindow();
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        if(mUseRequestedSurfaceLayout)
            mSurfaceView.layout(mSurfaceViewLeft, mSurfaceViewTop, mSurfaceViewRight, mSurfaceViewBottom);
        else
            mSurfaceView.layout(0, 0, k - i, l - j);
    }

    protected void onMeasure(int i, int j)
    {
        mSurfaceView.measure(i, j);
        int k = mSurfaceView.getMeasuredWidth();
        int l = mSurfaceView.getMeasuredHeight();
        int i1 = mSurfaceView.getMeasuredState();
        setMeasuredDimension(resolveSizeAndState(k, i, i1), resolveSizeAndState(l, j, i1 << 16));
    }

    public boolean onUnhandledInputEvent(InputEvent inputevent)
    {
        return false;
    }

    protected void onVisibilityChanged(View view, int i)
    {
        super.onVisibilityChanged(view, i);
        mSurfaceView.setVisibility(i);
        if(i == 0)
            createSessionOverlayView();
        else
            removeSessionOverlayView();
    }

    public void requestUnblockContent(TvContentRating tvcontentrating)
    {
        unblockContent(tvcontentrating);
    }

    public void reset()
    {
        Object obj = sMainTvViewLock;
        obj;
        JVM INSTR monitorenter ;
        if(this == sMainTvView.get())
            sMainTvView = NULL_TV_VIEW;
        obj;
        JVM INSTR monitorexit ;
        resetInternal();
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void selectTrack(int i, String s)
    {
        if(mSession != null)
            mSession.selectTrack(i, s);
    }

    public void sendAppPrivateCommand(String s, Bundle bundle)
    {
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException("action cannot be null or an empty string");
        if(mSession != null)
        {
            mSession.sendAppPrivateCommand(s, bundle);
        } else
        {
            Log.w("TvView", (new StringBuilder()).append("sendAppPrivateCommand - session not yet created (action \"").append(s).append("\" pending)").toString());
            mPendingAppPrivateCommands.add(Pair.create(s, bundle));
        }
    }

    public void setCallback(TvInputCallback tvinputcallback)
    {
        mCallback = tvinputcallback;
    }

    public void setCaptionEnabled(boolean flag)
    {
        mCaptionEnabled = Boolean.valueOf(flag);
        if(mSession != null)
            mSession.setCaptionEnabled(flag);
    }

    public void setMain()
    {
        Object obj = sMainTvViewLock;
        obj;
        JVM INSTR monitorenter ;
        WeakReference weakreference = JVM INSTR new #209 <Class WeakReference>;
        weakreference.WeakReference(this);
        sMainTvView = weakreference;
        if(hasWindowFocus() && mSession != null)
            mSession.setMain();
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setOnUnhandledInputEventListener(OnUnhandledInputEventListener onunhandledinputeventlistener)
    {
        mOnUnhandledInputEventListener = onunhandledinputeventlistener;
    }

    public void setStreamVolume(float f)
    {
        mStreamVolume = Float.valueOf(f);
        if(mSession == null)
        {
            return;
        } else
        {
            mSession.setStreamVolume(f);
            return;
        }
    }

    public void setTimeShiftPositionCallback(TimeShiftPositionCallback timeshiftpositioncallback)
    {
        mTimeShiftPositionCallback = timeshiftpositioncallback;
        ensurePositionTracking();
    }

    public void setZOrderMediaOverlay(boolean flag)
    {
        if(flag)
        {
            mWindowZOrder = 1;
            removeSessionOverlayView();
        } else
        {
            mWindowZOrder = 0;
            createSessionOverlayView();
        }
        if(mSurfaceView != null)
        {
            mSurfaceView.setZOrderOnTop(false);
            mSurfaceView.setZOrderMediaOverlay(flag);
        }
    }

    public void setZOrderOnTop(boolean flag)
    {
        if(flag)
        {
            mWindowZOrder = 2;
            removeSessionOverlayView();
        } else
        {
            mWindowZOrder = 0;
            createSessionOverlayView();
        }
        if(mSurfaceView != null)
        {
            mSurfaceView.setZOrderMediaOverlay(false);
            mSurfaceView.setZOrderOnTop(flag);
        }
    }

    public void timeShiftPause()
    {
        if(mSession != null)
            mSession.timeShiftPause();
    }

    public void timeShiftPlay(String s, Uri uri)
    {
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException("inputId cannot be null or an empty string");
        Object obj = sMainTvViewLock;
        obj;
        JVM INSTR monitorenter ;
        if(sMainTvView.get() == null)
        {
            WeakReference weakreference = JVM INSTR new #209 <Class WeakReference>;
            weakreference.WeakReference(this);
            sMainTvView = weakreference;
        }
        obj;
        JVM INSTR monitorexit ;
        if(mSessionCallback == null || !TextUtils.equals(mSessionCallback.mInputId, s)) goto _L2; else goto _L1
_L1:
        if(mSession != null)
            mSession.timeShiftPlay(uri);
        else
            mSessionCallback.mRecordedProgramUri = uri;
_L4:
        return;
        s;
        throw s;
_L2:
        resetInternal();
        mSessionCallback = new MySessionCallback(s, uri);
        if(mTvInputManager != null)
            mTvInputManager.createSession(s, mSessionCallback, mHandler);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void timeShiftResume()
    {
        if(mSession != null)
            mSession.timeShiftResume();
    }

    public void timeShiftSeekTo(long l)
    {
        if(mSession != null)
            mSession.timeShiftSeekTo(l);
    }

    public void timeShiftSetPlaybackParams(PlaybackParams playbackparams)
    {
        if(mSession != null)
            mSession.timeShiftSetPlaybackParams(playbackparams);
    }

    public void tune(String s, Uri uri)
    {
        tune(s, uri, null);
    }

    public void tune(String s, Uri uri, Bundle bundle)
    {
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException("inputId cannot be null or an empty string");
        Object obj = sMainTvViewLock;
        obj;
        JVM INSTR monitorenter ;
        if(sMainTvView.get() == null)
        {
            WeakReference weakreference = JVM INSTR new #209 <Class WeakReference>;
            weakreference.WeakReference(this);
            sMainTvView = weakreference;
        }
        obj;
        JVM INSTR monitorexit ;
        if(mSessionCallback == null || !TextUtils.equals(mSessionCallback.mInputId, s)) goto _L2; else goto _L1
_L1:
        if(mSession != null)
        {
            mSession.tune(uri, bundle);
        } else
        {
            mSessionCallback.mChannelUri = uri;
            mSessionCallback.mTuneParams = bundle;
        }
_L4:
        return;
        s;
        throw s;
_L2:
        resetInternal();
        mSessionCallback = new MySessionCallback(s, uri, bundle);
        if(mTvInputManager != null)
            mTvInputManager.createSession(s, mSessionCallback, mHandler);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void unblockContent(TvContentRating tvcontentrating)
    {
        if(mSession != null)
            mSession.unblockContent(tvcontentrating);
    }

    private static final boolean DEBUG = false;
    private static final WeakReference NULL_TV_VIEW;
    private static final String TAG = "TvView";
    private static final int ZORDER_MEDIA = 0;
    private static final int ZORDER_MEDIA_OVERLAY = 1;
    private static final int ZORDER_ON_TOP = 2;
    private static WeakReference sMainTvView;
    private static final Object sMainTvViewLock = new Object();
    private final AttributeSet mAttrs;
    private TvInputCallback mCallback;
    private Boolean mCaptionEnabled;
    private final int mDefStyleAttr;
    private final TvInputManager.Session.FinishedInputEventCallback mFinishedInputEventCallback;
    private final Handler mHandler;
    private OnUnhandledInputEventListener mOnUnhandledInputEventListener;
    private boolean mOverlayViewCreated;
    private Rect mOverlayViewFrame;
    private final Queue mPendingAppPrivateCommands;
    private TvInputManager.Session mSession;
    private MySessionCallback mSessionCallback;
    private Float mStreamVolume;
    private Surface mSurface;
    private boolean mSurfaceChanged;
    private int mSurfaceFormat;
    private int mSurfaceHeight;
    private final android.view.SurfaceHolder.Callback mSurfaceHolderCallback;
    private SurfaceView mSurfaceView;
    private int mSurfaceViewBottom;
    private int mSurfaceViewLeft;
    private int mSurfaceViewRight;
    private int mSurfaceViewTop;
    private int mSurfaceWidth;
    private TimeShiftPositionCallback mTimeShiftPositionCallback;
    private final TvInputManager mTvInputManager;
    private boolean mUseRequestedSurfaceLayout;
    private int mWindowZOrder;

    static 
    {
        NULL_TV_VIEW = new WeakReference(null);
        sMainTvView = NULL_TV_VIEW;
    }
}
