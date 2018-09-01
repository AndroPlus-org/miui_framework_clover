// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.animation.LayoutTransition;
import android.app.*;
import android.content.*;
import android.content.pm.ApplicationInfo;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.hardware.display.DisplayManager;
import android.hardware.input.InputManager;
import android.media.AudioManager;
import android.os.*;
import android.util.*;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.accessibility.IAccessibilityInteractionConnectionCallback;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Scroller;
import com.android.internal.os.IResultReceiver;
import com.android.internal.os.SomeArgs;
import com.android.internal.policy.DecorView;
import com.android.internal.policy.PhoneFallbackEventHandler;
import com.android.internal.util.Preconditions;
import com.android.internal.view.*;
import com.miui.internal.contentcatcher.IInterceptor;
import java.io.*;
import java.lang.ref.WeakReference;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;
import miui.os.MiuiInit;

// Referenced classes of package android.view:
//            ViewParent, Surface, InputEventConsistencyVerifier, WindowManagerGlobal, 
//            WindowLeaked, ViewConfiguration, Choreographer, MotionEvent, 
//            KeyEvent, View, InputEvent, ThreadedRenderer, 
//            WindowCallbacks, ViewTreeObserver, ViewGroup, InputEventReceiver, 
//            RenderNode, HandlerActionQueue, DragEvent, IWindowSession, 
//            Display, DisplayAdjustments, FrameInfo, PointerIcon, 
//            InputQueue, InputChannel, FocusFinder, AccessibilityInteractionController, 
//            WindowInsets, DisplayListCanvas, SoundEffectConstants, FallbackEventHandler, 
//            ContextMenu, ActionMode, MagnificationSpec, VelocityTracker, 
//            InputDevice, KeyCharacterMap, ViewRootImplInjector, ViewDebug

public final class ViewRootImpl
    implements ViewParent, View.AttachInfo.Callbacks, ThreadedRenderer.DrawCallbacks
{
    static final class AccessibilityInteractionConnection extends android.view.accessibility.IAccessibilityInteractionConnection.Stub
    {

        public void findAccessibilityNodeInfoByAccessibilityId(long l, Region region, int i, IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback, int j, int k, 
                long l1, MagnificationSpec magnificationspec, Bundle bundle)
        {
            ViewRootImpl viewrootimpl = (ViewRootImpl)mViewRootImpl.get();
            if(viewrootimpl != null && viewrootimpl.mView != null)
                viewrootimpl.getAccessibilityInteractionController().findAccessibilityNodeInfoByAccessibilityIdClientThread(l, region, i, iaccessibilityinteractionconnectioncallback, j, k, l1, magnificationspec, bundle);
            else
                try
                {
                    iaccessibilityinteractionconnectioncallback.setFindAccessibilityNodeInfosResult(null, i);
                }
                // Misplaced declaration of an exception variable
                catch(Region region) { }
        }

        public void findAccessibilityNodeInfosByText(long l, String s, Region region, int i, IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback, int j, 
                int k, long l1, MagnificationSpec magnificationspec)
        {
            ViewRootImpl viewrootimpl = (ViewRootImpl)mViewRootImpl.get();
            if(viewrootimpl != null && viewrootimpl.mView != null)
                viewrootimpl.getAccessibilityInteractionController().findAccessibilityNodeInfosByTextClientThread(l, s, region, i, iaccessibilityinteractionconnectioncallback, j, k, l1, magnificationspec);
            else
                try
                {
                    iaccessibilityinteractionconnectioncallback.setFindAccessibilityNodeInfosResult(null, i);
                }
                // Misplaced declaration of an exception variable
                catch(String s) { }
        }

        public void findAccessibilityNodeInfosByViewId(long l, String s, Region region, int i, IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback, int j, 
                int k, long l1, MagnificationSpec magnificationspec)
        {
            ViewRootImpl viewrootimpl = (ViewRootImpl)mViewRootImpl.get();
            if(viewrootimpl != null && viewrootimpl.mView != null)
                viewrootimpl.getAccessibilityInteractionController().findAccessibilityNodeInfosByViewIdClientThread(l, s, region, i, iaccessibilityinteractionconnectioncallback, j, k, l1, magnificationspec);
            else
                try
                {
                    iaccessibilityinteractionconnectioncallback.setFindAccessibilityNodeInfoResult(null, i);
                }
                // Misplaced declaration of an exception variable
                catch(String s) { }
        }

        public void findFocus(long l, int i, Region region, int j, IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback, int k, 
                int i1, long l1, MagnificationSpec magnificationspec)
        {
            ViewRootImpl viewrootimpl = (ViewRootImpl)mViewRootImpl.get();
            if(viewrootimpl != null && viewrootimpl.mView != null)
                viewrootimpl.getAccessibilityInteractionController().findFocusClientThread(l, i, region, j, iaccessibilityinteractionconnectioncallback, k, i1, l1, magnificationspec);
            else
                try
                {
                    iaccessibilityinteractionconnectioncallback.setFindAccessibilityNodeInfoResult(null, j);
                }
                // Misplaced declaration of an exception variable
                catch(Region region) { }
        }

        public void focusSearch(long l, int i, Region region, int j, IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback, int k, 
                int i1, long l1, MagnificationSpec magnificationspec)
        {
            ViewRootImpl viewrootimpl = (ViewRootImpl)mViewRootImpl.get();
            if(viewrootimpl != null && viewrootimpl.mView != null)
                viewrootimpl.getAccessibilityInteractionController().focusSearchClientThread(l, i, region, j, iaccessibilityinteractionconnectioncallback, k, i1, l1, magnificationspec);
            else
                try
                {
                    iaccessibilityinteractionconnectioncallback.setFindAccessibilityNodeInfoResult(null, j);
                }
                // Misplaced declaration of an exception variable
                catch(Region region) { }
        }

        public void performAccessibilityAction(long l, int i, Bundle bundle, int j, IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback, int k, 
                int i1, long l1)
        {
            ViewRootImpl viewrootimpl = (ViewRootImpl)mViewRootImpl.get();
            if(viewrootimpl != null && viewrootimpl.mView != null)
                viewrootimpl.getAccessibilityInteractionController().performAccessibilityActionClientThread(l, i, bundle, j, iaccessibilityinteractionconnectioncallback, k, i1, l1);
            else
                try
                {
                    iaccessibilityinteractionconnectioncallback.setPerformAccessibilityActionResult(false, j);
                }
                // Misplaced declaration of an exception variable
                catch(Bundle bundle) { }
        }

        private final WeakReference mViewRootImpl;

        AccessibilityInteractionConnection(ViewRootImpl viewrootimpl)
        {
            mViewRootImpl = new WeakReference(viewrootimpl);
        }
    }

    final class AccessibilityInteractionConnectionManager
        implements android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
    {

        public void ensureConnection()
        {
            boolean flag;
            if(mAttachInfo.mAccessibilityWindowId != -1)
                flag = true;
            else
                flag = false;
            if(!flag)
                mAttachInfo.mAccessibilityWindowId = mAccessibilityManager.addAccessibilityInteractionConnection(mWindow, new AccessibilityInteractionConnection(ViewRootImpl.this));
        }

        public void ensureNoConnection()
        {
            boolean flag;
            if(mAttachInfo.mAccessibilityWindowId != -1)
                flag = true;
            else
                flag = false;
            if(flag)
            {
                mAttachInfo.mAccessibilityWindowId = -1;
                mAccessibilityManager.removeAccessibilityInteractionConnection(mWindow);
            }
        }

        public void onAccessibilityStateChanged(boolean flag)
        {
            if(flag)
            {
                ensureConnection();
                if(mAttachInfo.mHasWindowFocus && mView != null)
                {
                    mView.sendAccessibilityEvent(32);
                    View view = mView.findFocus();
                    if(view != null && view != mView)
                        view.sendAccessibilityEvent(8);
                }
            } else
            {
                ensureNoConnection();
                mHandler.obtainMessage(21).sendToTarget();
            }
        }

        final ViewRootImpl this$0;

        AccessibilityInteractionConnectionManager()
        {
            this$0 = ViewRootImpl.this;
            super();
        }
    }

    public static interface ActivityConfigCallback
    {

        public abstract void onConfigurationChanged(Configuration configuration, int i);
    }

    abstract class AsyncInputStage extends InputStage
    {

        private void dequeue(QueuedInputEvent queuedinputevent, QueuedInputEvent queuedinputevent1)
        {
            if(queuedinputevent1 == null)
                mQueueHead = queuedinputevent.mNext;
            else
                queuedinputevent1.mNext = queuedinputevent.mNext;
            if(mQueueTail == queuedinputevent)
                mQueueTail = queuedinputevent1;
            queuedinputevent.mNext = null;
            mQueueLength = mQueueLength - 1;
            Trace.traceCounter(4L, mTraceCounter, mQueueLength);
        }

        private void enqueue(QueuedInputEvent queuedinputevent)
        {
            if(mQueueTail == null)
            {
                mQueueHead = queuedinputevent;
                mQueueTail = queuedinputevent;
            } else
            {
                mQueueTail.mNext = queuedinputevent;
                mQueueTail = queuedinputevent;
            }
            mQueueLength = mQueueLength + 1;
            Trace.traceCounter(4L, mTraceCounter, mQueueLength);
        }

        protected void apply(QueuedInputEvent queuedinputevent, int i)
        {
            if(i == 3)
                defer(queuedinputevent);
            else
                super.apply(queuedinputevent, i);
        }

        protected void defer(QueuedInputEvent queuedinputevent)
        {
            queuedinputevent.mFlags = queuedinputevent.mFlags | 2;
            enqueue(queuedinputevent);
        }

        void dump(String s, PrintWriter printwriter)
        {
            printwriter.print(s);
            printwriter.print(getClass().getName());
            printwriter.print(": mQueueLength=");
            printwriter.println(mQueueLength);
            super.dump(s, printwriter);
        }

        protected void forward(QueuedInputEvent queuedinputevent)
        {
            queuedinputevent.mFlags = queuedinputevent.mFlags & -3;
            QueuedInputEvent queuedinputevent1 = mQueueHead;
            if(queuedinputevent1 == null)
            {
                super.forward(queuedinputevent);
                return;
            }
            int i = queuedinputevent.mEvent.getDeviceId();
            QueuedInputEvent queuedinputevent3 = null;
            boolean flag;
            boolean flag1;
            for(flag = false; queuedinputevent1 != null && queuedinputevent1 != queuedinputevent; flag = flag1)
            {
                flag1 = flag;
                if(!flag)
                {
                    flag1 = flag;
                    if(i == queuedinputevent1.mEvent.getDeviceId())
                        flag1 = true;
                }
                queuedinputevent3 = queuedinputevent1;
                queuedinputevent1 = queuedinputevent1.mNext;
            }

            if(flag)
            {
                if(queuedinputevent1 == null)
                    enqueue(queuedinputevent);
                return;
            }
            QueuedInputEvent queuedinputevent4 = queuedinputevent1;
            if(queuedinputevent1 != null)
            {
                queuedinputevent4 = queuedinputevent1.mNext;
                dequeue(queuedinputevent, queuedinputevent3);
            }
            super.forward(queuedinputevent);
            queuedinputevent = queuedinputevent4;
            do
            {
label0:
                {
label1:
                    {
                        if(queuedinputevent != null)
                        {
                            if(i != queuedinputevent.mEvent.getDeviceId())
                                break label0;
                            if((queuedinputevent.mFlags & 2) == 0)
                                break label1;
                        }
                        return;
                    }
                    QueuedInputEvent queuedinputevent2 = queuedinputevent.mNext;
                    dequeue(queuedinputevent, queuedinputevent3);
                    super.forward(queuedinputevent);
                    queuedinputevent = queuedinputevent2;
                    continue;
                }
                queuedinputevent3 = queuedinputevent;
                queuedinputevent = queuedinputevent.mNext;
            } while(true);
        }

        protected static final int DEFER = 3;
        private QueuedInputEvent mQueueHead;
        private int mQueueLength;
        private QueuedInputEvent mQueueTail;
        private final String mTraceCounter;
        final ViewRootImpl this$0;

        public AsyncInputStage(InputStage inputstage, String s)
        {
            this$0 = ViewRootImpl.this;
            super(inputstage);
            mTraceCounter = s;
        }
    }

    public static final class CalledFromWrongThreadException extends AndroidRuntimeException
    {

        public CalledFromWrongThreadException(String s)
        {
            super(s);
        }
    }

    public static interface ConfigChangedCallback
    {

        public abstract void onConfigurationChanged(Configuration configuration);
    }

    final class ConsumeBatchedInputImmediatelyRunnable
        implements Runnable
    {

        public void run()
        {
            doConsumeBatchedInput(-1L);
        }

        final ViewRootImpl this$0;

        ConsumeBatchedInputImmediatelyRunnable()
        {
            this$0 = ViewRootImpl.this;
            super();
        }
    }

    final class ConsumeBatchedInputRunnable
        implements Runnable
    {

        public void run()
        {
            doConsumeBatchedInput(mChoreographer.getFrameTimeNanos());
        }

        final ViewRootImpl this$0;

        ConsumeBatchedInputRunnable()
        {
            this$0 = ViewRootImpl.this;
            super();
        }
    }

    final class EarlyPostImeInputStage extends InputStage
    {

        private int processKeyEvent(QueuedInputEvent queuedinputevent)
        {
            queuedinputevent = (KeyEvent)queuedinputevent.mEvent;
            if(mAttachInfo.mTooltipHost != null)
                mAttachInfo.mTooltipHost.handleTooltipKey(queuedinputevent);
            if(ViewRootImpl._2D_wrap1(ViewRootImpl.this, queuedinputevent))
            {
                return 1;
            } else
            {
                mFallbackEventHandler.preDispatchKeyEvent(queuedinputevent);
                return 0;
            }
        }

        private int processPointerEvent(QueuedInputEvent queuedinputevent)
        {
            queuedinputevent = (MotionEvent)queuedinputevent.mEvent;
            if(mTranslator != null)
                mTranslator.translateEventInScreenToAppWindow(queuedinputevent);
            int i = queuedinputevent.getAction();
            if(i == 0 || i == 8)
                ensureTouchMode(queuedinputevent.isFromSource(4098));
            if(i == 0 && mAttachInfo.mTooltipHost != null)
                mAttachInfo.mTooltipHost.hideTooltip();
            if(mCurScrollY != 0)
                queuedinputevent.offsetLocation(0.0F, mCurScrollY);
            if(queuedinputevent.isTouchEvent())
            {
                mLastTouchPoint.x = queuedinputevent.getRawX();
                mLastTouchPoint.y = queuedinputevent.getRawY();
                mLastTouchSource = queuedinputevent.getSource();
            }
            return 0;
        }

        protected int onProcess(QueuedInputEvent queuedinputevent)
        {
            if(queuedinputevent.mEvent instanceof KeyEvent)
                return processKeyEvent(queuedinputevent);
            if((queuedinputevent.mEvent.getSource() & 2) != 0)
                return processPointerEvent(queuedinputevent);
            else
                return 0;
        }

        final ViewRootImpl this$0;

        public EarlyPostImeInputStage(InputStage inputstage)
        {
            this$0 = ViewRootImpl.this;
            super(inputstage);
        }
    }

    final class HighContrastTextManager
        implements android.view.accessibility.AccessibilityManager.HighTextContrastChangeListener
    {

        public void onHighTextContrastStateChanged(boolean flag)
        {
            mAttachInfo.mHighContrastText = flag;
            destroyHardwareResources();
            invalidate();
        }

        final ViewRootImpl this$0;

        HighContrastTextManager()
        {
            this$0 = ViewRootImpl.this;
            super();
            mAttachInfo.mHighContrastText = mAccessibilityManager.isHighTextContrastEnabled();
        }
    }

    final class ImeInputStage extends AsyncInputStage
        implements android.view.inputmethod.InputMethodManager.FinishedInputEventCallback
    {

        public void onFinishedInputEvent(Object obj, boolean flag)
        {
            obj = (QueuedInputEvent)obj;
            if(flag)
            {
                finish(((QueuedInputEvent) (obj)), true);
                return;
            } else
            {
                forward(((QueuedInputEvent) (obj)));
                return;
            }
        }

        protected int onProcess(QueuedInputEvent queuedinputevent)
        {
            if(mLastWasImTarget && ViewRootImpl._2D_wrap3(ViewRootImpl.this) ^ true)
            {
                InputMethodManager inputmethodmanager = InputMethodManager.peekInstance();
                if(inputmethodmanager != null)
                {
                    int i = inputmethodmanager.dispatchInputEvent(queuedinputevent.mEvent, queuedinputevent, this, mHandler);
                    if(i == 1)
                        return 1;
                    return i != 0 ? 3 : 0;
                }
            }
            return 0;
        }

        final ViewRootImpl this$0;

        public ImeInputStage(InputStage inputstage, String s)
        {
            this$0 = ViewRootImpl.this;
            super(inputstage, s);
        }
    }

    abstract class InputStage
    {

        private boolean isBack(InputEvent inputevent)
        {
            boolean flag = false;
            if(inputevent instanceof KeyEvent)
            {
                if(((KeyEvent)inputevent).getKeyCode() == 4)
                    flag = true;
                return flag;
            } else
            {
                return false;
            }
        }

        protected void apply(QueuedInputEvent queuedinputevent, int i)
        {
            if(i == 0)
                forward(queuedinputevent);
            else
            if(i == 1)
                finish(queuedinputevent, true);
            else
            if(i == 2)
                finish(queuedinputevent, false);
            else
                throw new IllegalArgumentException((new StringBuilder()).append("Invalid result: ").append(i).toString());
        }

        public final void deliver(QueuedInputEvent queuedinputevent)
        {
            if((queuedinputevent.mFlags & 4) != 0)
                forward(queuedinputevent);
            else
            if(shouldDropInputEvent(queuedinputevent))
                finish(queuedinputevent, false);
            else
                apply(queuedinputevent, onProcess(queuedinputevent));
        }

        void dump(String s, PrintWriter printwriter)
        {
            if(mNext != null)
                mNext.dump(s, printwriter);
        }

        protected void finish(QueuedInputEvent queuedinputevent, boolean flag)
        {
            queuedinputevent.mFlags = queuedinputevent.mFlags | 4;
            if(flag)
                queuedinputevent.mFlags = queuedinputevent.mFlags | 8;
            forward(queuedinputevent);
        }

        protected void forward(QueuedInputEvent queuedinputevent)
        {
            onDeliverToNext(queuedinputevent);
        }

        protected void onDeliverToNext(QueuedInputEvent queuedinputevent)
        {
            if(mNext != null)
                mNext.deliver(queuedinputevent);
            else
                ViewRootImpl._2D_wrap6(ViewRootImpl.this, queuedinputevent);
        }

        protected int onProcess(QueuedInputEvent queuedinputevent)
        {
            return 0;
        }

        protected boolean shouldDropInputEvent(QueuedInputEvent queuedinputevent)
        {
            if(mView == null || mAdded ^ true)
            {
                Slog.w(ViewRootImpl._2D_get5(ViewRootImpl.this), (new StringBuilder()).append("Dropping event due to root view being removed: ").append(queuedinputevent.mEvent).toString());
                return true;
            }
            if(!mAttachInfo.mHasWindowFocus && queuedinputevent.mEvent.isFromSource(2) ^ true || mStopped || mIsAmbientMode && queuedinputevent.mEvent.isFromSource(1) ^ true || mPausedForTransition && isBack(queuedinputevent.mEvent) ^ true)
            {
                if(ViewRootImpl.isTerminalInputEvent(queuedinputevent.mEvent))
                {
                    queuedinputevent.mEvent.cancel();
                    Slog.w(ViewRootImpl._2D_get5(ViewRootImpl.this), (new StringBuilder()).append("Cancelling event due to no window focus: ").append(queuedinputevent.mEvent).toString());
                    return false;
                } else
                {
                    Slog.w(ViewRootImpl._2D_get5(ViewRootImpl.this), (new StringBuilder()).append("Dropping event due to no window focus: ").append(queuedinputevent.mEvent).toString());
                    return true;
                }
            } else
            {
                return false;
            }
        }

        protected static final int FINISH_HANDLED = 1;
        protected static final int FINISH_NOT_HANDLED = 2;
        protected static final int FORWARD = 0;
        private final InputStage mNext;
        final ViewRootImpl this$0;

        public InputStage(InputStage inputstage)
        {
            this$0 = ViewRootImpl.this;
            super();
            mNext = inputstage;
        }
    }

    final class InvalidateOnAnimationRunnable
        implements Runnable
    {

        private void postIfNeededLocked()
        {
            if(!mPosted)
            {
                mChoreographer.postCallback(1, this, null);
                mPosted = true;
            }
        }

        public void addView(View view)
        {
            this;
            JVM INSTR monitorenter ;
            mViews.add(view);
            postIfNeededLocked();
            this;
            JVM INSTR monitorexit ;
            return;
            view;
            throw view;
        }

        public void addViewRect(View.AttachInfo.InvalidateInfo invalidateinfo)
        {
            this;
            JVM INSTR monitorenter ;
            mViewRects.add(invalidateinfo);
            postIfNeededLocked();
            this;
            JVM INSTR monitorexit ;
            return;
            invalidateinfo;
            throw invalidateinfo;
        }

        public void removeView(View view)
        {
            this;
            JVM INSTR monitorenter ;
            int i;
            mViews.remove(view);
            i = mViewRects.size();
_L2:
            int j;
            j = i - 1;
            if(i <= 0)
                break; /* Loop/switch isn't completed */
            View.AttachInfo.InvalidateInfo invalidateinfo = (View.AttachInfo.InvalidateInfo)mViewRects.get(j);
            if(invalidateinfo.target == view)
            {
                mViewRects.remove(j);
                invalidateinfo.recycle();
            }
            i = j;
            if(true) goto _L2; else goto _L1
_L1:
            if(mPosted && mViews.isEmpty() && mViewRects.isEmpty())
            {
                mChoreographer.removeCallbacks(1, this, null);
                mPosted = false;
            }
            this;
            JVM INSTR monitorexit ;
            return;
            view;
            throw view;
        }

        public void run()
        {
            this;
            JVM INSTR monitorenter ;
            int i;
            mPosted = false;
            i = mViews.size();
            if(i == 0) goto _L2; else goto _L1
_L1:
            ArrayList arraylist = mViews;
            if(mTempViews == null) goto _L4; else goto _L3
_L3:
            Object aobj[] = mTempViews;
_L9:
            mTempViews = (View[])arraylist.toArray(aobj);
            mViews.clear();
_L2:
            int j = mViewRects.size();
            if(j == 0) goto _L6; else goto _L5
_L5:
            arraylist = mViewRects;
            if(mTempViewRects == null) goto _L8; else goto _L7
_L7:
            aobj = mTempViewRects;
_L10:
            mTempViewRects = (View.AttachInfo.InvalidateInfo[])arraylist.toArray(aobj);
            mViewRects.clear();
_L6:
            this;
            JVM INSTR monitorexit ;
            for(int k = 0; k < i; k++)
            {
                mTempViews[k].invalidate();
                mTempViews[k] = null;
            }

            break MISSING_BLOCK_LABEL_162;
_L4:
            aobj = new View[i];
              goto _L9
_L8:
            aobj = new View.AttachInfo.InvalidateInfo[j];
              goto _L10
            Exception exception;
            exception;
            throw exception;
            for(int l = 0; l < j; l++)
            {
                View.AttachInfo.InvalidateInfo invalidateinfo = mTempViewRects[l];
                invalidateinfo.target.invalidate(invalidateinfo.left, invalidateinfo.top, invalidateinfo.right, invalidateinfo.bottom);
                invalidateinfo.recycle();
            }

            return;
              goto _L9
        }

        private boolean mPosted;
        private View.AttachInfo.InvalidateInfo mTempViewRects[];
        private View mTempViews[];
        private final ArrayList mViewRects = new ArrayList();
        private final ArrayList mViews = new ArrayList();
        final ViewRootImpl this$0;

        InvalidateOnAnimationRunnable()
        {
            this$0 = ViewRootImpl.this;
            super();
        }
    }

    final class NativePostImeInputStage extends AsyncInputStage
        implements InputQueue.FinishedInputEventCallback
    {

        public void onFinishedInputEvent(Object obj, boolean flag)
        {
            obj = (QueuedInputEvent)obj;
            if(flag)
            {
                finish(((QueuedInputEvent) (obj)), true);
                return;
            } else
            {
                forward(((QueuedInputEvent) (obj)));
                return;
            }
        }

        protected int onProcess(QueuedInputEvent queuedinputevent)
        {
            if(mInputQueue != null)
            {
                mInputQueue.sendInputEvent(queuedinputevent.mEvent, queuedinputevent, false, this);
                return 3;
            } else
            {
                return 0;
            }
        }

        final ViewRootImpl this$0;

        public NativePostImeInputStage(InputStage inputstage, String s)
        {
            this$0 = ViewRootImpl.this;
            super(inputstage, s);
        }
    }

    final class NativePreImeInputStage extends AsyncInputStage
        implements InputQueue.FinishedInputEventCallback
    {

        public void onFinishedInputEvent(Object obj, boolean flag)
        {
            obj = (QueuedInputEvent)obj;
            if(flag)
            {
                finish(((QueuedInputEvent) (obj)), true);
                return;
            } else
            {
                forward(((QueuedInputEvent) (obj)));
                return;
            }
        }

        protected int onProcess(QueuedInputEvent queuedinputevent)
        {
            if(mInputQueue != null && (queuedinputevent.mEvent instanceof KeyEvent))
            {
                mInputQueue.sendInputEvent(queuedinputevent.mEvent, queuedinputevent, true, this);
                return 3;
            } else
            {
                return 0;
            }
        }

        final ViewRootImpl this$0;

        public NativePreImeInputStage(InputStage inputstage, String s)
        {
            this$0 = ViewRootImpl.this;
            super(inputstage, s);
        }
    }

    private static final class QueuedInputEvent
    {

        private boolean flagToString(String s, int i, boolean flag, StringBuilder stringbuilder)
        {
            if((mFlags & i) != 0)
            {
                if(flag)
                    stringbuilder.append("|");
                stringbuilder.append(s);
                return true;
            } else
            {
                return flag;
            }
        }

        public boolean shouldSendToSynthesizer()
        {
            return (mFlags & 0x20) != 0;
        }

        public boolean shouldSkipIme()
        {
            boolean flag = true;
            if((mFlags & 1) != 0)
                return true;
            if(mEvent instanceof MotionEvent)
            {
                if(!mEvent.isFromSource(2))
                    flag = mEvent.isFromSource(0x400000);
            } else
            {
                flag = false;
            }
            return flag;
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder("QueuedInputEvent{flags=");
            if(!flagToString("UNHANDLED", 32, flagToString("RESYNTHESIZED", 16, flagToString("FINISHED_HANDLED", 8, flagToString("FINISHED", 4, flagToString("DEFERRED", 2, flagToString("DELIVER_POST_IME", 1, false, stringbuilder), stringbuilder), stringbuilder), stringbuilder), stringbuilder), stringbuilder))
                stringbuilder.append("0");
            StringBuilder stringbuilder1 = stringbuilder.append(", hasNextQueuedEvent=");
            String s;
            if(mEvent != null)
                s = "true";
            else
                s = "false";
            stringbuilder1.append(s);
            stringbuilder1 = stringbuilder.append(", hasInputEventReceiver=");
            if(mReceiver != null)
                s = "true";
            else
                s = "false";
            stringbuilder1.append(s);
            stringbuilder.append(", mEvent=").append(mEvent).append("}");
            return stringbuilder.toString();
        }

        public static final int FLAG_DEFERRED = 2;
        public static final int FLAG_DELIVER_POST_IME = 1;
        public static final int FLAG_FINISHED = 4;
        public static final int FLAG_FINISHED_HANDLED = 8;
        public static final int FLAG_RESYNTHESIZED = 16;
        public static final int FLAG_UNHANDLED = 32;
        public InputEvent mEvent;
        public int mFlags;
        public QueuedInputEvent mNext;
        public InputEventReceiver mReceiver;

        private QueuedInputEvent()
        {
        }

        QueuedInputEvent(QueuedInputEvent queuedinputevent)
        {
            this();
        }
    }

    private class SendWindowContentChangedAccessibilityEvent
        implements Runnable
    {

        public void removeCallbacksAndRun()
        {
            mHandler.removeCallbacks(this);
            run();
        }

        public void run()
        {
            View view = mSource;
            mSource = null;
            if(view == null)
            {
                Log.e("ViewRootImpl", "Accessibility content change has no source");
                return;
            }
            if(AccessibilityManager.getInstance(mContext).isEnabled())
            {
                mLastEventTimeMillis = SystemClock.uptimeMillis();
                AccessibilityEvent accessibilityevent = AccessibilityEvent.obtain();
                accessibilityevent.setEventType(2048);
                accessibilityevent.setContentChangeTypes(mChangeTypes);
                view.sendAccessibilityEventUnchecked(accessibilityevent);
            } else
            {
                mLastEventTimeMillis = 0L;
            }
            view.resetSubtreeAccessibilityStateChanged();
            mChangeTypes = 0;
        }

        public void runOrPost(View view, int i)
        {
            if(mHandler.getLooper() != Looper.myLooper())
            {
                Log.e("ViewRootImpl", "Accessibility content change on non-UI thread. Future Android versions will throw an exception.", new CalledFromWrongThreadException("Only the original thread that created a view hierarchy can touch its views."));
                mHandler.removeCallbacks(this);
                if(mSource != null)
                    run();
            }
            if(mSource != null)
            {
                View view1 = ViewRootImpl._2D_wrap0(ViewRootImpl.this, mSource, view);
                if(view1 != null)
                    view = view1;
                mSource = view;
                mChangeTypes = mChangeTypes | i;
                return;
            }
            mSource = view;
            mChangeTypes = i;
            long l = SystemClock.uptimeMillis() - mLastEventTimeMillis;
            long l1 = ViewConfiguration.getSendRecurringAccessibilityEventsInterval();
            if(l >= l1)
                removeCallbacksAndRun();
            else
                mHandler.postDelayed(this, l1 - l);
        }

        private int mChangeTypes;
        public long mLastEventTimeMillis;
        public View mSource;
        final ViewRootImpl this$0;

        private SendWindowContentChangedAccessibilityEvent()
        {
            this$0 = ViewRootImpl.this;
            super();
            mChangeTypes = 0;
        }

        SendWindowContentChangedAccessibilityEvent(SendWindowContentChangedAccessibilityEvent sendwindowcontentchangedaccessibilityevent)
        {
            this();
        }
    }

    final class SyntheticInputStage extends InputStage
    {

        protected void onDeliverToNext(QueuedInputEvent queuedinputevent)
        {
            if((queuedinputevent.mFlags & 0x10) != 0 || !(queuedinputevent.mEvent instanceof MotionEvent)) goto _L2; else goto _L1
_L1:
            MotionEvent motionevent;
            int i;
            motionevent = (MotionEvent)queuedinputevent.mEvent;
            i = motionevent.getSource();
            if((i & 4) == 0) goto _L4; else goto _L3
_L3:
            mTrackball.cancel(motionevent);
_L2:
            super.onDeliverToNext(queuedinputevent);
            return;
_L4:
            if((i & 0x10) != 0)
                SyntheticJoystickHandler._2D_wrap0(mJoystick, motionevent);
            else
            if((i & 0x200000) == 0x200000)
                mTouchNavigation.cancel(motionevent);
            if(true) goto _L2; else goto _L5
_L5:
        }

        protected int onProcess(QueuedInputEvent queuedinputevent)
        {
            queuedinputevent.mFlags = queuedinputevent.mFlags | 0x10;
            if(queuedinputevent.mEvent instanceof MotionEvent)
            {
                queuedinputevent = (MotionEvent)queuedinputevent.mEvent;
                int i = queuedinputevent.getSource();
                if((i & 4) != 0)
                {
                    mTrackball.process(queuedinputevent);
                    return 1;
                }
                if((i & 0x10) != 0)
                {
                    mJoystick.process(queuedinputevent);
                    return 1;
                }
                if((i & 0x200000) == 0x200000)
                {
                    mTouchNavigation.process(queuedinputevent);
                    return 1;
                }
            } else
            if((queuedinputevent.mFlags & 0x20) != 0)
            {
                mKeyboard.process((KeyEvent)queuedinputevent.mEvent);
                return 1;
            }
            return 0;
        }

        private final SyntheticJoystickHandler mJoystick;
        private final SyntheticKeyboardHandler mKeyboard;
        private final SyntheticTouchNavigationHandler mTouchNavigation;
        private final SyntheticTrackballHandler mTrackball;
        final ViewRootImpl this$0;

        public SyntheticInputStage()
        {
            this$0 = ViewRootImpl.this;
            super(null);
            mTrackball = new SyntheticTrackballHandler();
            mJoystick = new SyntheticJoystickHandler();
            mTouchNavigation = new SyntheticTouchNavigationHandler();
            mKeyboard = new SyntheticKeyboardHandler();
        }
    }

    final class SyntheticJoystickHandler extends Handler
    {

        static void _2D_wrap0(SyntheticJoystickHandler syntheticjoystickhandler, MotionEvent motionevent)
        {
            syntheticjoystickhandler.cancel(motionevent);
        }

        private void cancel(MotionEvent motionevent)
        {
            removeMessages(1);
            removeMessages(2);
            update(motionevent, false);
        }

        private int joystickAxisValueToDirection(float f)
        {
            if(f >= 0.5F)
                return 1;
            return f > -0.5F ? 0 : -1;
        }

        private void update(MotionEvent motionevent, boolean flag)
        {
            long l = motionevent.getEventTime();
            int i = motionevent.getMetaState();
            int j = motionevent.getDeviceId();
            int k = motionevent.getSource();
            int i1 = joystickAxisValueToDirection(motionevent.getAxisValue(15));
            int j1 = i1;
            if(i1 == 0)
                j1 = joystickAxisValueToDirection(motionevent.getX());
            int k1 = joystickAxisValueToDirection(motionevent.getAxisValue(16));
            i1 = k1;
            if(k1 == 0)
                i1 = joystickAxisValueToDirection(motionevent.getY());
            if(j1 != mLastXDirection)
            {
                if(mLastXKeyCode != 0)
                {
                    removeMessages(1);
                    enqueueInputEvent(new KeyEvent(l, l, 1, mLastXKeyCode, 0, i, j, 0, 1024, k));
                    mLastXKeyCode = 0;
                }
                mLastXDirection = j1;
                if(j1 != 0 && flag)
                {
                    if(j1 > 0)
                        j1 = 22;
                    else
                        j1 = 21;
                    mLastXKeyCode = j1;
                    motionevent = new KeyEvent(l, l, 0, mLastXKeyCode, 0, i, j, 0, 1024, k);
                    enqueueInputEvent(motionevent);
                    motionevent = obtainMessage(1, motionevent);
                    motionevent.setAsynchronous(true);
                    sendMessageDelayed(motionevent, ViewConfiguration.getKeyRepeatTimeout());
                }
            }
            if(i1 != mLastYDirection)
            {
                if(mLastYKeyCode != 0)
                {
                    removeMessages(2);
                    enqueueInputEvent(new KeyEvent(l, l, 1, mLastYKeyCode, 0, i, j, 0, 1024, k));
                    mLastYKeyCode = 0;
                }
                mLastYDirection = i1;
                if(i1 != 0 && flag)
                {
                    if(i1 > 0)
                        i1 = 20;
                    else
                        i1 = 19;
                    mLastYKeyCode = i1;
                    motionevent = new KeyEvent(l, l, 0, mLastYKeyCode, 0, i, j, 0, 1024, k);
                    enqueueInputEvent(motionevent);
                    motionevent = obtainMessage(2, motionevent);
                    motionevent.setAsynchronous(true);
                    sendMessageDelayed(motionevent, ViewConfiguration.getKeyRepeatTimeout());
                }
            }
        }

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 2: default 28
        //                       1 29
        //                       2 29;
               goto _L1 _L2 _L2
_L1:
            return;
_L2:
            KeyEvent keyevent = (KeyEvent)message.obj;
            keyevent = KeyEvent.changeTimeRepeat(keyevent, SystemClock.uptimeMillis(), keyevent.getRepeatCount() + 1);
            if(mAttachInfo.mHasWindowFocus)
            {
                enqueueInputEvent(keyevent);
                message = obtainMessage(message.what, keyevent);
                message.setAsynchronous(true);
                sendMessageDelayed(message, ViewConfiguration.getKeyRepeatDelay());
            }
            if(true) goto _L1; else goto _L3
_L3:
        }

        public void process(MotionEvent motionevent)
        {
            motionevent.getActionMasked();
            JVM INSTR tableswitch 2 3: default 28
        //                       2 70
        //                       3 62;
               goto _L1 _L2 _L3
_L1:
            Log.w(ViewRootImpl._2D_get5(ViewRootImpl.this), (new StringBuilder()).append("Unexpected action: ").append(motionevent.getActionMasked()).toString());
_L5:
            return;
_L3:
            cancel(motionevent);
            continue; /* Loop/switch isn't completed */
_L2:
            update(motionevent, true);
            if(true) goto _L5; else goto _L4
_L4:
        }

        private static final int MSG_ENQUEUE_X_AXIS_KEY_REPEAT = 1;
        private static final int MSG_ENQUEUE_Y_AXIS_KEY_REPEAT = 2;
        private static final String TAG = "SyntheticJoystickHandler";
        private int mLastXDirection;
        private int mLastXKeyCode;
        private int mLastYDirection;
        private int mLastYKeyCode;
        final ViewRootImpl this$0;

        public SyntheticJoystickHandler()
        {
            this$0 = ViewRootImpl.this;
            super(true);
        }
    }

    final class SyntheticKeyboardHandler
    {

        public void process(KeyEvent keyevent)
        {
            if((keyevent.getFlags() & 0x400) != 0)
                return;
            KeyCharacterMap.FallbackAction fallbackaction = keyevent.getKeyCharacterMap().getFallbackAction(keyevent.getKeyCode(), keyevent.getMetaState());
            if(fallbackaction != null)
            {
                int i = keyevent.getFlags();
                keyevent = KeyEvent.obtain(keyevent.getDownTime(), keyevent.getEventTime(), keyevent.getAction(), fallbackaction.keyCode, keyevent.getRepeatCount(), fallbackaction.metaState, keyevent.getDeviceId(), keyevent.getScanCode(), i | 0x400, keyevent.getSource(), null);
                fallbackaction.recycle();
                enqueueInputEvent(keyevent);
            }
        }

        final ViewRootImpl this$0;

        SyntheticKeyboardHandler()
        {
            this$0 = ViewRootImpl.this;
            super();
        }
    }

    final class SyntheticTouchNavigationHandler extends Handler
    {

        static float _2D_get0(SyntheticTouchNavigationHandler synthetictouchnavigationhandler)
        {
            return synthetictouchnavigationhandler.mFlingVelocity;
        }

        static int _2D_get1(SyntheticTouchNavigationHandler synthetictouchnavigationhandler)
        {
            return synthetictouchnavigationhandler.mPendingKeyCode;
        }

        static int _2D_get2(SyntheticTouchNavigationHandler synthetictouchnavigationhandler)
        {
            return synthetictouchnavigationhandler.mPendingKeyMetaState;
        }

        static float _2D_set0(SyntheticTouchNavigationHandler synthetictouchnavigationhandler, float f)
        {
            synthetictouchnavigationhandler.mFlingVelocity = f;
            return f;
        }

        static boolean _2D_set1(SyntheticTouchNavigationHandler synthetictouchnavigationhandler, boolean flag)
        {
            synthetictouchnavigationhandler.mFlinging = flag;
            return flag;
        }

        static boolean _2D_wrap0(SyntheticTouchNavigationHandler synthetictouchnavigationhandler, long l)
        {
            return synthetictouchnavigationhandler.postFling(l);
        }

        static void _2D_wrap1(SyntheticTouchNavigationHandler synthetictouchnavigationhandler, long l)
        {
            synthetictouchnavigationhandler.finishKeys(l);
        }

        static void _2D_wrap2(SyntheticTouchNavigationHandler synthetictouchnavigationhandler, long l, int i, int j)
        {
            synthetictouchnavigationhandler.sendKeyDownOrRepeat(l, i, j);
        }

        private void cancelFling()
        {
            if(mFlinging)
            {
                removeCallbacks(mFlingRunnable);
                mFlinging = false;
            }
        }

        private float consumeAccumulatedMovement(long l, int i, float f, int j, int k)
        {
            float f1;
            do
            {
                f1 = f;
                if(f > -mConfigTickDistance)
                    break;
                sendKeyDownOrRepeat(l, j, i);
                f += mConfigTickDistance;
            } while(true);
            for(; f1 >= mConfigTickDistance; f1 -= mConfigTickDistance)
                sendKeyDownOrRepeat(l, k, i);

            return f1;
        }

        private void consumeAccumulatedMovement(long l, int i)
        {
            float f;
            float f1;
            f = Math.abs(mAccumulatedX);
            f1 = Math.abs(mAccumulatedY);
            if(f < f1) goto _L2; else goto _L1
_L1:
            if(f >= mConfigTickDistance)
            {
                mAccumulatedX = consumeAccumulatedMovement(l, i, mAccumulatedX, 21, 22);
                mAccumulatedY = 0.0F;
                mConsumedMovement = true;
            }
_L4:
            return;
_L2:
            if(f1 >= mConfigTickDistance)
            {
                mAccumulatedY = consumeAccumulatedMovement(l, i, mAccumulatedY, 19, 20);
                mAccumulatedX = 0.0F;
                mConsumedMovement = true;
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        private void finishKeys(long l)
        {
            cancelFling();
            sendKeyUp(l);
        }

        private void finishTracking(long l)
        {
            if(mActivePointerId >= 0)
            {
                mActivePointerId = -1;
                mVelocityTracker.recycle();
                mVelocityTracker = null;
            }
        }

        private boolean postFling(long l)
        {
            if(mFlingVelocity >= mConfigMinFlingVelocity)
            {
                long l1 = (long)((mConfigTickDistance / mFlingVelocity) * 1000F);
                postAtTime(mFlingRunnable, l + l1);
                return true;
            } else
            {
                return false;
            }
        }

        private void sendKeyDownOrRepeat(long l, int i, int j)
        {
            if(mPendingKeyCode != i)
            {
                sendKeyUp(l);
                mPendingKeyDownTime = l;
                mPendingKeyCode = i;
                mPendingKeyRepeatCount = 0;
            } else
            {
                mPendingKeyRepeatCount = mPendingKeyRepeatCount + 1;
            }
            mPendingKeyMetaState = j;
            enqueueInputEvent(new KeyEvent(mPendingKeyDownTime, l, 0, mPendingKeyCode, mPendingKeyRepeatCount, mPendingKeyMetaState, mCurrentDeviceId, 1024, mCurrentSource));
        }

        private void sendKeyUp(long l)
        {
            if(mPendingKeyCode != 0)
            {
                enqueueInputEvent(new KeyEvent(mPendingKeyDownTime, l, 1, mPendingKeyCode, 0, mPendingKeyMetaState, mCurrentDeviceId, 0, 1024, mCurrentSource));
                mPendingKeyCode = 0;
            }
        }

        private boolean startFling(long l, float f, float f1)
        {
            mPendingKeyCode;
            JVM INSTR tableswitch 19 22: default 36
        //                       19 116
        //                       20 151
        //                       21 50
        //                       22 84;
               goto _L1 _L2 _L3 _L4 _L5
_L1:
            mFlinging = postFling(l);
            return mFlinging;
_L4:
            if(-f >= mConfigMinFlingVelocity && Math.abs(f1) < mConfigMinFlingVelocity)
                mFlingVelocity = -f;
            else
                return false;
            continue; /* Loop/switch isn't completed */
_L5:
            if(f >= mConfigMinFlingVelocity && Math.abs(f1) < mConfigMinFlingVelocity)
                mFlingVelocity = f;
            else
                return false;
            continue; /* Loop/switch isn't completed */
_L2:
            if(-f1 >= mConfigMinFlingVelocity && Math.abs(f) < mConfigMinFlingVelocity)
                mFlingVelocity = -f1;
            else
                return false;
            continue; /* Loop/switch isn't completed */
_L3:
            if(f1 >= mConfigMinFlingVelocity && Math.abs(f) < mConfigMinFlingVelocity)
                mFlingVelocity = f1;
            else
                return false;
            if(true) goto _L1; else goto _L6
_L6:
        }

        public void cancel(MotionEvent motionevent)
        {
            if(mCurrentDeviceId == motionevent.getDeviceId() && mCurrentSource == motionevent.getSource())
            {
                long l = motionevent.getEventTime();
                finishKeys(l);
                finishTracking(l);
            }
        }

        public void process(MotionEvent motionevent)
        {
            long l;
            int i;
            l = motionevent.getEventTime();
            i = motionevent.getDeviceId();
            int j = motionevent.getSource();
            if(mCurrentDeviceId != i || mCurrentSource != j)
            {
                finishKeys(l);
                finishTracking(l);
                mCurrentDeviceId = i;
                mCurrentSource = j;
                mCurrentDeviceSupported = false;
                Object obj = motionevent.getDevice();
                if(obj != null)
                {
                    InputDevice.MotionRange motionrange = ((InputDevice) (obj)).getMotionRange(0);
                    obj = ((InputDevice) (obj)).getMotionRange(1);
                    if(motionrange != null && obj != null)
                    {
                        mCurrentDeviceSupported = true;
                        float f = motionrange.getResolution();
                        float f2 = f;
                        if(f <= 0.0F)
                            f2 = motionrange.getRange() / 48F;
                        float f4 = ((InputDevice.MotionRange) (obj)).getResolution();
                        f = f4;
                        if(f4 <= 0.0F)
                            f = ((InputDevice.MotionRange) (obj)).getRange() / 48F;
                        mConfigTickDistance = 12F * ((f2 + f) * 0.5F);
                        mConfigMinFlingVelocity = mConfigTickDistance * 6F;
                        mConfigMaxFlingVelocity = mConfigTickDistance * 20F;
                    }
                }
            }
            if(!mCurrentDeviceSupported)
                return;
            i = motionevent.getActionMasked();
            i;
            JVM INSTR tableswitch 0 3: default 244
        //                       0 245
        //                       1 336
        //                       2 336
        //                       3 525;
               goto _L1 _L2 _L3 _L3 _L4
_L1:
            return;
_L2:
            boolean flag = mFlinging;
            finishKeys(l);
            finishTracking(l);
            mActivePointerId = motionevent.getPointerId(0);
            mVelocityTracker = VelocityTracker.obtain();
            mVelocityTracker.addMovement(motionevent);
            mStartX = motionevent.getX();
            mStartY = motionevent.getY();
            mLastX = mStartX;
            mLastY = mStartY;
            mAccumulatedX = 0.0F;
            mAccumulatedY = 0.0F;
            mConsumedMovement = flag;
            continue; /* Loop/switch isn't completed */
_L3:
            if(mActivePointerId >= 0)
            {
                int k = motionevent.findPointerIndex(mActivePointerId);
                if(k < 0)
                {
                    finishKeys(l);
                    finishTracking(l);
                } else
                {
                    mVelocityTracker.addMovement(motionevent);
                    float f3 = motionevent.getX(k);
                    float f1 = motionevent.getY(k);
                    mAccumulatedX = mAccumulatedX + (f3 - mLastX);
                    mAccumulatedY = mAccumulatedY + (f1 - mLastY);
                    mLastX = f3;
                    mLastY = f1;
                    consumeAccumulatedMovement(l, motionevent.getMetaState());
                    if(i == 1)
                    {
                        if(mConsumedMovement && mPendingKeyCode != 0)
                        {
                            mVelocityTracker.computeCurrentVelocity(1000, mConfigMaxFlingVelocity);
                            if(!startFling(l, mVelocityTracker.getXVelocity(mActivePointerId), mVelocityTracker.getYVelocity(mActivePointerId)))
                                finishKeys(l);
                        }
                        finishTracking(l);
                    }
                }
            }
            continue; /* Loop/switch isn't completed */
_L4:
            finishKeys(l);
            finishTracking(l);
            if(true) goto _L1; else goto _L5
_L5:
        }

        private static final float DEFAULT_HEIGHT_MILLIMETERS = 48F;
        private static final float DEFAULT_WIDTH_MILLIMETERS = 48F;
        private static final float FLING_TICK_DECAY = 0.8F;
        private static final boolean LOCAL_DEBUG = false;
        private static final String LOCAL_TAG = "SyntheticTouchNavigationHandler";
        private static final float MAX_FLING_VELOCITY_TICKS_PER_SECOND = 20F;
        private static final float MIN_FLING_VELOCITY_TICKS_PER_SECOND = 6F;
        private static final int TICK_DISTANCE_MILLIMETERS = 12;
        private float mAccumulatedX;
        private float mAccumulatedY;
        private int mActivePointerId;
        private float mConfigMaxFlingVelocity;
        private float mConfigMinFlingVelocity;
        private float mConfigTickDistance;
        private boolean mConsumedMovement;
        private int mCurrentDeviceId;
        private boolean mCurrentDeviceSupported;
        private int mCurrentSource;
        private final Runnable mFlingRunnable = new _cls1();
        private float mFlingVelocity;
        private boolean mFlinging;
        private float mLastX;
        private float mLastY;
        private int mPendingKeyCode;
        private long mPendingKeyDownTime;
        private int mPendingKeyMetaState;
        private int mPendingKeyRepeatCount;
        private float mStartX;
        private float mStartY;
        private VelocityTracker mVelocityTracker;
        final ViewRootImpl this$0;

        public SyntheticTouchNavigationHandler()
        {
            this$0 = ViewRootImpl.this;
            super(true);
            mCurrentDeviceId = -1;
            mActivePointerId = -1;
            mPendingKeyCode = 0;
        }
    }

    final class SyntheticTrackballHandler
    {

        public void cancel(MotionEvent motionevent)
        {
            mLastTime = 0xffffffff80000000L;
            if(mView != null && mAdded)
                ensureTouchMode(false);
        }

        public void process(MotionEvent motionevent)
        {
            long l = SystemClock.uptimeMillis();
            if(mLastTime + 250L < l)
            {
                mX.reset(0);
                mY.reset(0);
                mLastTime = l;
            }
            int i = motionevent.getAction();
            int k = motionevent.getMetaState();
            float f;
            int j1;
            switch(i)
            {
            case 0: // '\0'
                mX.reset(2);
                mY.reset(2);
                enqueueInputEvent(new KeyEvent(l, l, 0, 23, 0, k, -1, 0, 1024, 257));
                continue;

            case 1: // '\001'
                mX.reset(2);
                mY.reset(2);
                enqueueInputEvent(new KeyEvent(l, l, 1, 23, 0, k, -1, 0, 1024, 257));
                continue;
            }
            break;
            do
            {
                f = mX.collect(motionevent.getX(), motionevent.getEventTime(), "X");
                float f2 = mY.collect(motionevent.getY(), motionevent.getEventTime(), "Y");
                int i1 = 0;
                int j = 0;
                float f3 = 1.0F;
                float f1;
                int l1;
                if(f > f2)
                {
                    j1 = mX.generate();
                    f1 = f3;
                    l1 = i1;
                    j = j1;
                    if(j1 != 0)
                    {
                        if(j1 > 0)
                            l1 = 22;
                        else
                            l1 = 21;
                        f1 = mX.acceleration;
                        mY.reset(2);
                        j = j1;
                    }
                } else
                {
                    f1 = f3;
                    l1 = i1;
                    if(f2 > 0.0F)
                    {
                        int k1 = mY.generate();
                        f1 = f3;
                        l1 = i1;
                        j = k1;
                        if(k1 != 0)
                        {
                            if(k1 > 0)
                                l1 = 20;
                            else
                                l1 = 19;
                            f1 = mY.acceleration;
                            mX.reset(2);
                            j = k1;
                        }
                    }
                }
                if(l1 != 0)
                {
                    j1 = j;
                    if(j < 0)
                        j1 = -j;
                    i1 = (int)((float)j1 * f1);
                    long l2 = l;
                    j = j1;
                    if(i1 > j1)
                    {
                        j = j1 - 1;
                        enqueueInputEvent(new KeyEvent(l, l, 2, l1, i1 - j, k, -1, 0, 1024, 257));
                        l2 = l;
                    }
                    while(j > 0) 
                    {
                        j--;
                        l2 = SystemClock.uptimeMillis();
                        enqueueInputEvent(new KeyEvent(l2, l2, 0, l1, 0, k, -1, 0, 1024, 257));
                        enqueueInputEvent(new KeyEvent(l2, l2, 1, l1, 0, k, -1, 0, 1024, 257));
                    }
                    mLastTime = l2;
                }
                return;
            } while(true);
        }

        private long mLastTime;
        private final TrackballAxis mX = new TrackballAxis();
        private final TrackballAxis mY = new TrackballAxis();
        final ViewRootImpl this$0;

        SyntheticTrackballHandler()
        {
            this$0 = ViewRootImpl.this;
            super();
        }
    }

    static final class SystemUiVisibilityInfo
    {

        int globalVisibility;
        int localChanges;
        int localValue;
        int seq;

        SystemUiVisibilityInfo()
        {
        }
    }

    class TakenSurfaceHolder extends BaseSurfaceHolder
    {

        public boolean isCreating()
        {
            return mIsCreating;
        }

        public boolean onAllowLockCanvas()
        {
            return mDrawingAllowed;
        }

        public void onRelayoutContainer()
        {
        }

        public void onUpdateSurface()
        {
            throw new IllegalStateException("Shouldn't be here");
        }

        public void setFixedSize(int i, int j)
        {
            throw new UnsupportedOperationException("Currently only support sizing from layout");
        }

        public void setFormat(int i)
        {
            ((RootViewSurfaceTaker)mView).setSurfaceFormat(i);
        }

        public void setKeepScreenOn(boolean flag)
        {
            ((RootViewSurfaceTaker)mView).setSurfaceKeepScreenOn(flag);
        }

        public void setType(int i)
        {
            ((RootViewSurfaceTaker)mView).setSurfaceType(i);
        }

        final ViewRootImpl this$0;

        TakenSurfaceHolder()
        {
            this$0 = ViewRootImpl.this;
            super();
        }
    }

    static final class TrackballAxis
    {

        float collect(float f, long l, String s)
        {
            long l1;
            if(f > 0.0F)
            {
                l1 = (long)(150F * f);
                if(dir < 0)
                {
                    position = 0.0F;
                    step = 0;
                    acceleration = 1.0F;
                    lastMoveTime = 0L;
                }
                dir = 1;
            } else
            if(f < 0.0F)
            {
                l1 = (long)(-f * 150F);
                if(dir > 0)
                {
                    position = 0.0F;
                    step = 0;
                    acceleration = 1.0F;
                    lastMoveTime = 0L;
                }
                dir = -1;
            } else
            {
                l1 = 0L;
            }
            if(l1 > 0L)
            {
                long l2 = l - lastMoveTime;
                lastMoveTime = l;
                float f1 = acceleration;
                if(l2 < l1)
                {
                    float f2 = (float)(l1 - l2) * 0.025F;
                    float f4 = f1;
                    if(f2 > 1.0F)
                        f4 = f1 * f2;
                    if(f4 >= 20F)
                        f4 = 20F;
                    acceleration = f4;
                } else
                {
                    float f3 = (float)(l2 - l1) * 0.025F;
                    float f5 = f1;
                    if(f3 > 1.0F)
                        f5 = f1 / f3;
                    if(f5 <= 1.0F)
                        f5 = 1.0F;
                    acceleration = f5;
                }
            }
            position = position + f;
            return Math.abs(position);
        }

        int generate()
        {
            int i = 0;
            nonAccelMovement = 0;
            do
            {
                int j;
                if(position >= 0.0F)
                    j = 1;
                else
                    j = -1;
                switch(step)
                {
                default:
                    if(Math.abs(position) < 1.0F)
                        return i;
                    break;

                case 0: // '\0'
                    if(Math.abs(position) < 0.5F)
                        return i;
                    i += j;
                    nonAccelMovement = nonAccelMovement + j;
                    step = 1;
                    continue;

                case 1: // '\001'
                    if(Math.abs(position) < 2.0F)
                        return i;
                    i += j;
                    nonAccelMovement = nonAccelMovement + j;
                    position = position - (float)j * 2.0F;
                    step = 2;
                    continue;
                }
                i += j;
                position = position - (float)j * 1.0F;
                float f = acceleration * 1.1F;
                if(f >= 20F)
                    f = acceleration;
                acceleration = f;
            } while(true);
        }

        void reset(int i)
        {
            position = 0.0F;
            acceleration = 1.0F;
            lastMoveTime = 0L;
            step = i;
            dir = 0;
        }

        static final float ACCEL_MOVE_SCALING_FACTOR = 0.025F;
        static final long FAST_MOVE_TIME = 150L;
        static final float FIRST_MOVEMENT_THRESHOLD = 0.5F;
        static final float MAX_ACCELERATION = 20F;
        static final float SECOND_CUMULATIVE_MOVEMENT_THRESHOLD = 2F;
        static final float SUBSEQUENT_INCREMENTAL_MOVEMENT_THRESHOLD = 1F;
        float acceleration;
        int dir;
        long lastMoveTime;
        int nonAccelMovement;
        float position;
        int step;

        TrackballAxis()
        {
            acceleration = 1.0F;
            lastMoveTime = 0L;
        }
    }

    final class TraversalRunnable
        implements Runnable
    {

        public void run()
        {
            doTraversal();
            notifyContentChangeToContentCatcher();
        }

        final ViewRootImpl this$0;

        TraversalRunnable()
        {
            this$0 = ViewRootImpl.this;
            super();
        }
    }

    final class ViewPostImeInputStage extends InputStage
    {

        private void maybeUpdatePointerIcon(MotionEvent motionevent)
        {
            if(motionevent.getPointerCount() == 1 && motionevent.isFromSource(8194))
            {
                if(motionevent.getActionMasked() == 9 || motionevent.getActionMasked() == 10)
                    ViewRootImpl._2D_set0(ViewRootImpl.this, 1);
                if(motionevent.getActionMasked() != 10 && !ViewRootImpl._2D_wrap4(ViewRootImpl.this, motionevent) && motionevent.getActionMasked() == 7)
                    ViewRootImpl._2D_set0(ViewRootImpl.this, 1);
            }
        }

        private boolean performFocusNavigation(KeyEvent keyevent)
        {
            int i = 0;
            keyevent.getKeyCode();
            JVM INSTR lookupswitch 5: default 56
        //                       19: 210
        //                       20: 223
        //                       21: 184
        //                       22: 197
        //                       61: 237;
               goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
            View view;
            if(i == 0)
                break MISSING_BLOCK_LABEL_294;
            view = mView.findFocus();
            if(view == null)
                break MISSING_BLOCK_LABEL_279;
            keyevent = view.focusSearch(i);
            if(keyevent != null && keyevent != view)
            {
                view.getFocusedRect(mTempRect);
                if(mView instanceof ViewGroup)
                {
                    ((ViewGroup)mView).offsetDescendantRectToMyCoords(view, mTempRect);
                    ((ViewGroup)mView).offsetRectIntoDescendantCoords(keyevent, mTempRect);
                }
                if(keyevent.requestFocus(i, mTempRect))
                {
                    playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i));
                    return true;
                }
            }
            break; /* Loop/switch isn't completed */
_L4:
            if(keyevent.hasNoModifiers())
                i = 17;
            continue; /* Loop/switch isn't completed */
_L5:
            if(keyevent.hasNoModifiers())
                i = 66;
            continue; /* Loop/switch isn't completed */
_L2:
            if(keyevent.hasNoModifiers())
                i = 33;
            continue; /* Loop/switch isn't completed */
_L3:
            if(keyevent.hasNoModifiers())
                i = 130;
            continue; /* Loop/switch isn't completed */
_L6:
            if(keyevent.hasNoModifiers())
                i = 2;
            else
            if(keyevent.hasModifiers(1))
                i = 1;
            if(true) goto _L1; else goto _L7
_L7:
            if(mView.dispatchUnhandledMove(view, i))
                return true;
            break MISSING_BLOCK_LABEL_294;
            if(mView.restoreDefaultFocus())
                return true;
            return false;
        }

        private boolean performKeyboardGroupNavigation(int i)
        {
            View view = mView.findFocus();
            if(view == null && mView.restoreDefaultFocus())
                return true;
            int j;
            View view1;
            if(view == null)
                view = keyboardNavigationClusterSearch(null, i);
            else
                view = view.keyboardNavigationClusterSearch(null, i);
            j = i;
            if(i == 2 || i == 1)
                j = 130;
            view1 = view;
            if(view != null)
            {
                view1 = view;
                if(view.isRootNamespace())
                {
                    if(view.restoreFocusNotInCluster())
                    {
                        playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i));
                        return true;
                    }
                    view1 = keyboardNavigationClusterSearch(null, i);
                }
            }
            if(view1 != null && view1.restoreFocusInCluster(j))
            {
                playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i));
                return true;
            } else
            {
                return false;
            }
        }

        private int processGenericMotionEvent(QueuedInputEvent queuedinputevent)
        {
            queuedinputevent = (MotionEvent)queuedinputevent.mEvent;
            return !mView.dispatchGenericMotionEvent(queuedinputevent) ? 0 : 1;
        }

        private int processKeyEvent(QueuedInputEvent queuedinputevent)
        {
            KeyEvent keyevent = (KeyEvent)queuedinputevent.mEvent;
            if(mView.dispatchKeyEvent(keyevent))
                return 1;
            if(shouldDropInputEvent(queuedinputevent))
                return 2;
            boolean flag = false;
            byte byte0 = flag;
            if(keyevent.getAction() == 0)
            {
                byte0 = flag;
                if(keyevent.getKeyCode() == 61)
                    if(KeyEvent.metaStateHasModifiers(keyevent.getMetaState(), 0x10000))
                    {
                        byte0 = 2;
                    } else
                    {
                        byte0 = flag;
                        if(KeyEvent.metaStateHasModifiers(keyevent.getMetaState(), 0x10001))
                            byte0 = 1;
                    }
            }
            if(keyevent.getAction() == 0 && KeyEvent.metaStateHasNoModifiers(keyevent.getMetaState()) ^ true && keyevent.getRepeatCount() == 0 && KeyEvent.isModifierKey(keyevent.getKeyCode()) ^ true && byte0 == 0)
            {
                if(mView.dispatchKeyShortcutEvent(keyevent))
                    return 1;
                if(shouldDropInputEvent(queuedinputevent))
                    return 2;
            }
            if(mFallbackEventHandler.dispatchKeyEvent(keyevent))
                return 1;
            if(shouldDropInputEvent(queuedinputevent))
                return 2;
            if(keyevent.getAction() == 0)
                if(byte0 != 0)
                {
                    if(performKeyboardGroupNavigation(byte0))
                        return 1;
                } else
                if(performFocusNavigation(keyevent))
                    return 1;
            return 0;
        }

        private int processPointerEvent(QueuedInputEvent queuedinputevent)
        {
            int i = 1;
            queuedinputevent = (MotionEvent)queuedinputevent.mEvent;
            ViewRootImplInjector.checkForThreeGesture(queuedinputevent);
            mAttachInfo.mUnbufferedDispatchRequested = false;
            mAttachInfo.mHandlingPointerEvent = true;
            boolean flag = mView.dispatchPointerEvent(queuedinputevent);
            int j = queuedinputevent.getActionMasked();
            if(j == 2)
                mHaveMoveEvent = true;
            else
            if(j == 1)
            {
                mHaveMoveEvent = false;
                mIsPerfLockAcquired = false;
            }
            maybeUpdatePointerIcon(queuedinputevent);
            ViewRootImpl._2D_wrap11(ViewRootImpl.this, queuedinputevent);
            mAttachInfo.mHandlingPointerEvent = false;
            if(mAttachInfo.mUnbufferedDispatchRequested && mUnbufferedInputDispatch ^ true)
            {
                mUnbufferedInputDispatch = true;
                if(mConsumeBatchedInputScheduled)
                    scheduleConsumeBatchedInputImmediately();
            }
            if(!flag)
                i = 0;
            return i;
        }

        private int processTrackballEvent(QueuedInputEvent queuedinputevent)
        {
            queuedinputevent = (MotionEvent)queuedinputevent.mEvent;
            if(queuedinputevent.isFromSource(0x20004) && (!hasPointerCapture() || mView.dispatchCapturedPointerEvent(queuedinputevent)))
                return 1;
            return !mView.dispatchTrackballEvent(queuedinputevent) ? 0 : 1;
        }

        protected void onDeliverToNext(QueuedInputEvent queuedinputevent)
        {
            if(mUnbufferedInputDispatch && (queuedinputevent.mEvent instanceof MotionEvent) && ((MotionEvent)queuedinputevent.mEvent).isTouchEvent() && ViewRootImpl.isTerminalInputEvent(queuedinputevent.mEvent))
            {
                mUnbufferedInputDispatch = false;
                scheduleConsumeBatchedInput();
            }
            super.onDeliverToNext(queuedinputevent);
        }

        protected int onProcess(QueuedInputEvent queuedinputevent)
        {
            if(queuedinputevent.mEvent instanceof KeyEvent)
                return processKeyEvent(queuedinputevent);
            int i = queuedinputevent.mEvent.getSource();
            if((i & 2) != 0)
                return processPointerEvent(queuedinputevent);
            if((i & 4) != 0)
                return processTrackballEvent(queuedinputevent);
            else
                return processGenericMotionEvent(queuedinputevent);
        }

        final ViewRootImpl this$0;

        public ViewPostImeInputStage(InputStage inputstage)
        {
            this$0 = ViewRootImpl.this;
            super(inputstage);
        }
    }

    final class ViewPreImeInputStage extends InputStage
    {

        private int processKeyEvent(QueuedInputEvent queuedinputevent)
        {
            queuedinputevent = (KeyEvent)queuedinputevent.mEvent;
            dispatchKeyEventToContentCatcher(queuedinputevent);
            return !mView.dispatchKeyEventPreIme(queuedinputevent) ? 0 : 1;
        }

        protected int onProcess(QueuedInputEvent queuedinputevent)
        {
            if(queuedinputevent.mEvent instanceof KeyEvent)
                return processKeyEvent(queuedinputevent);
            else
                return 0;
        }

        final ViewRootImpl this$0;

        public ViewPreImeInputStage(InputStage inputstage)
        {
            this$0 = ViewRootImpl.this;
            super(inputstage);
        }
    }

    final class ViewRootHandler extends Handler
    {

        public String getMessageName(Message message)
        {
            switch(message.what)
            {
            case 10: // '\n'
            case 12: // '\f'
            case 20: // '\024'
            case 22: // '\026'
            case 26: // '\032'
            default:
                return super.getMessageName(message);

            case 1: // '\001'
                return "MSG_INVALIDATE";

            case 2: // '\002'
                return "MSG_INVALIDATE_RECT";

            case 3: // '\003'
                return "MSG_DIE";

            case 4: // '\004'
                return "MSG_RESIZED";

            case 5: // '\005'
                return "MSG_RESIZED_REPORT";

            case 6: // '\006'
                return "MSG_WINDOW_FOCUS_CHANGED";

            case 7: // '\007'
                return "MSG_DISPATCH_INPUT_EVENT";

            case 8: // '\b'
                return "MSG_DISPATCH_APP_VISIBILITY";

            case 9: // '\t'
                return "MSG_DISPATCH_GET_NEW_SURFACE";

            case 11: // '\013'
                return "MSG_DISPATCH_KEY_FROM_IME";

            case 13: // '\r'
                return "MSG_CHECK_FOCUS";

            case 14: // '\016'
                return "MSG_CLOSE_SYSTEM_DIALOGS";

            case 15: // '\017'
                return "MSG_DISPATCH_DRAG_EVENT";

            case 16: // '\020'
                return "MSG_DISPATCH_DRAG_LOCATION_EVENT";

            case 17: // '\021'
                return "MSG_DISPATCH_SYSTEM_UI_VISIBILITY";

            case 18: // '\022'
                return "MSG_UPDATE_CONFIGURATION";

            case 19: // '\023'
                return "MSG_PROCESS_INPUT_EVENTS";

            case 21: // '\025'
                return "MSG_CLEAR_ACCESSIBILITY_FOCUS_HOST";

            case 23: // '\027'
                return "MSG_WINDOW_MOVED";

            case 24: // '\030'
                return "MSG_SYNTHESIZE_INPUT_EVENT";

            case 25: // '\031'
                return "MSG_DISPATCH_WINDOW_SHOWN";

            case 27: // '\033'
                return "MSG_UPDATE_POINTER_ICON";

            case 28: // '\034'
                return "MSG_POINTER_CAPTURE_CHANGED";

            case 29: // '\035'
                return "MSG_DRAW_FINISHED";
            }
        }

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 29: default 136
        //                       1 137
        //                       2 150
        //                       3 1553
        //                       4 243
        //                       5 401
        //                       6 1004
        //                       7 1563
        //                       8 206
        //                       9 233
        //                       10 136
        //                       11 1632
        //                       12 136
        //                       13 1678
        //                       14 1693
        //                       15 1723
        //                       16 1723
        //                       17 1753
        //                       18 1770
        //                       19 188
        //                       20 136
        //                       21 1848
        //                       22 1860
        //                       23 870
        //                       24 1609
        //                       25 1887
        //                       26 1897
        //                       27 1924
        //                       28 1943
        //                       29 1968;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L1 _L11 _L1 _L12 _L13 _L14 _L14 _L15 _L16 _L17 _L1 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26
_L1:
            return;
_L2:
            ((View)message.obj).invalidate();
            continue; /* Loop/switch isn't completed */
_L3:
            message = (View.AttachInfo.InvalidateInfo)message.obj;
            ((View.AttachInfo.InvalidateInfo) (message)).target.invalidate(((View.AttachInfo.InvalidateInfo) (message)).left, ((View.AttachInfo.InvalidateInfo) (message)).top, ((View.AttachInfo.InvalidateInfo) (message)).right, ((View.AttachInfo.InvalidateInfo) (message)).bottom);
            message.recycle();
            continue; /* Loop/switch isn't completed */
_L17:
            mProcessInputEventsScheduled = false;
            doProcessInputEvents();
            continue; /* Loop/switch isn't completed */
_L9:
            ViewRootImpl viewrootimpl = ViewRootImpl.this;
            boolean flag;
            if(message.arg1 != 0)
                flag = true;
            else
                flag = false;
            viewrootimpl.handleAppVisibility(flag);
            continue; /* Loop/switch isn't completed */
_L10:
            handleGetNewSurface();
            continue; /* Loop/switch isn't completed */
_L5:
            SomeArgs someargs = (SomeArgs)message.obj;
            if(mWinFrame.equals(someargs.arg1) && mPendingOverscanInsets.equals(someargs.arg5) && mPendingContentInsets.equals(someargs.arg2) && mPendingStableInsets.equals(someargs.arg6) && mPendingVisibleInsets.equals(someargs.arg3) && mPendingOutsets.equals(someargs.arg7) && mPendingBackDropFrame.equals(someargs.arg8) && someargs.arg4 == null && someargs.argi1 == 0 && mDisplay.getDisplayId() == someargs.argi3)
                continue; /* Loop/switch isn't completed */
_L6:
            if(!mAdded)
                continue; /* Loop/switch isn't completed */
            SomeArgs someargs1 = (SomeArgs)message.obj;
            int i = someargs1.argi3;
            Object obj1 = (MergedConfiguration)someargs1.arg4;
            boolean flag1;
            int k;
            if(mDisplay.getDisplayId() != i)
                k = 1;
            else
                k = 0;
            if(!ViewRootImpl._2D_get1(ViewRootImpl.this).equals(obj1))
            {
                ViewRootImpl viewrootimpl1 = ViewRootImpl.this;
                if(k != 0)
                    k = i;
                else
                    k = -1;
                ViewRootImpl._2D_wrap12(viewrootimpl1, ((MergedConfiguration) (obj1)), false, k);
            } else
            if(k != 0)
                onMovedToDisplay(i, ViewRootImpl._2D_get0(ViewRootImpl.this));
            if(mWinFrame.equals(someargs1.arg1) && !(mPendingOverscanInsets.equals(someargs1.arg5) ^ true) && !(mPendingContentInsets.equals(someargs1.arg2) ^ true) && !(mPendingStableInsets.equals(someargs1.arg6) ^ true) && !(mPendingVisibleInsets.equals(someargs1.arg3) ^ true))
                k = mPendingOutsets.equals(someargs1.arg7) ^ true;
            else
                k = 1;
            mWinFrame.set((Rect)someargs1.arg1);
            mPendingOverscanInsets.set((Rect)someargs1.arg5);
            mPendingContentInsets.set((Rect)someargs1.arg2);
            mPendingStableInsets.set((Rect)someargs1.arg6);
            mPendingVisibleInsets.set((Rect)someargs1.arg3);
            mPendingOutsets.set((Rect)someargs1.arg7);
            mPendingBackDropFrame.set((Rect)someargs1.arg8);
            obj1 = ViewRootImpl.this;
            if(someargs1.argi1 != 0)
                flag1 = true;
            else
                flag1 = false;
            obj1.mForceNextWindowRelayout = flag1;
            obj1 = ViewRootImpl.this;
            if(someargs1.argi2 != 0)
                flag1 = true;
            else
                flag1 = false;
            obj1.mPendingAlwaysConsumeNavBar = flag1;
            someargs1.recycle();
            if(message.what == 5)
                ViewRootImpl._2D_wrap14(ViewRootImpl.this);
            if(mView != null && k != 0)
                ViewRootImpl._2D_wrap7(mView);
            requestLayout();
            continue; /* Loop/switch isn't completed */
_L20:
            if(mAdded)
            {
                int l = mWinFrame.width();
                int j1 = mWinFrame.height();
                int k1 = message.arg1;
                int j = message.arg2;
                mWinFrame.left = k1;
                mWinFrame.right = k1 + l;
                mWinFrame.top = j;
                mWinFrame.bottom = j + j1;
                mPendingBackDropFrame.set(mWinFrame);
                ViewRootImpl._2D_wrap10(ViewRootImpl.this, mWinFrame);
            }
            continue; /* Loop/switch isn't completed */
_L7:
            if(!mAdded)
                continue; /* Loop/switch isn't completed */
            WindowManager.LayoutParams layoutparams;
            boolean flag2;
            if(message.arg1 != 0)
                flag2 = true;
            else
                flag2 = false;
            mAttachInfo.mHasWindowFocus = flag2;
            ViewRootImpl._2D_wrap13(ViewRootImpl.this, flag2);
            if(!flag2) goto _L28; else goto _L27
_L27:
            boolean flag4;
            if(message.arg2 != 0)
                flag4 = true;
            else
                flag4 = false;
            ViewRootImpl._2D_wrap2(ViewRootImpl.this, flag4);
            if(mAttachInfo.mThreadedRenderer == null || !mSurface.isValid()) goto _L28; else goto _L29
_L29:
            mFullRedrawNeeded = true;
            Rect rect;
            try
            {
                layoutparams = mWindowAttributes;
            }
            catch(Surface.OutOfResourcesException outofresourcesexception)
            {
                Log.e(ViewRootImpl._2D_get5(ViewRootImpl.this), "OutOfResourcesException locking surface", outofresourcesexception);
                Object obj;
                boolean flag3;
                SomeArgs someargs2;
                int i1;
                try
                {
                    if(!mWindowSession.outOfMemory(mWindow))
                    {
                        Slog.w(ViewRootImpl._2D_get5(ViewRootImpl.this), "No processes killed for memory; killing self");
                        Process.killProcess(Process.myPid());
                    }
                }
                catch(RemoteException remoteexception) { }
                sendMessageDelayed(obtainMessage(message.what, message.arg1, message.arg2), 500L);
                return;
            }
            if(layoutparams == null)
                break MISSING_BLOCK_LABEL_1444;
            rect = layoutparams.surfaceInsets;
_L30:
            mAttachInfo.mThreadedRenderer.initializeIfNeeded(mWidth, mHeight, mAttachInfo, mSurface, rect);
_L28:
            mLastWasImTarget = WindowManager.LayoutParams.mayUseInputMethod(mWindowAttributes.flags);
            message = InputMethodManager.peekInstance();
            if(message != null && mLastWasImTarget && ViewRootImpl._2D_wrap3(ViewRootImpl.this) ^ true)
                message.onPreWindowFocus(mView, flag2);
            if(mView != null)
            {
                mAttachInfo.mKeyDispatchState.reset();
                mView.dispatchWindowFocusChanged(flag2);
                mAttachInfo.mTreeObserver.dispatchOnWindowFocusChange(flag2);
                if(mAttachInfo.mTooltipHost != null)
                    mAttachInfo.mTooltipHost.hideTooltip();
            }
            if(flag2)
            {
                if(message != null && mLastWasImTarget && ViewRootImpl._2D_wrap3(ViewRootImpl.this) ^ true)
                    message.onPostWindowFocus(mView, mView.findFocus(), mWindowAttributes.softInputMode, mHasHadWindowFocus ^ true, mWindowAttributes.flags);
                message = mWindowAttributes;
                message.softInputMode = ((WindowManager.LayoutParams) (message)).softInputMode & 0xfffffeff;
                message = (WindowManager.LayoutParams)mView.getLayoutParams();
                message.softInputMode = ((WindowManager.LayoutParams) (message)).softInputMode & 0xfffffeff;
                mHasHadWindowFocus = true;
            } else
            if(mPointerCapture)
                ViewRootImpl._2D_wrap9(ViewRootImpl.this, false);
            continue; /* Loop/switch isn't completed */
            rect = null;
              goto _L30
_L4:
            doDie();
            continue; /* Loop/switch isn't completed */
_L8:
            someargs2 = (SomeArgs)message.obj;
            message = (InputEvent)someargs2.arg1;
            obj = (InputEventReceiver)someargs2.arg2;
            enqueueInputEvent(message, ((InputEventReceiver) (obj)), 0, true);
            someargs2.recycle();
            continue; /* Loop/switch isn't completed */
_L21:
            message = (InputEvent)message.obj;
            enqueueInputEvent(message, null, 32, true);
            continue; /* Loop/switch isn't completed */
_L11:
            obj = (KeyEvent)message.obj;
            message = ((Message) (obj));
            if((((KeyEvent) (obj)).getFlags() & 8) != 0)
                message = KeyEvent.changeFlags(((KeyEvent) (obj)), ((KeyEvent) (obj)).getFlags() & -9);
            enqueueInputEvent(message, null, 1, true);
            continue; /* Loop/switch isn't completed */
_L12:
            message = InputMethodManager.peekInstance();
            if(message != null)
                message.checkFocus();
            continue; /* Loop/switch isn't completed */
_L13:
            if(mView != null)
                mView.onCloseSystemDialogs((String)message.obj);
            continue; /* Loop/switch isn't completed */
_L14:
            message = (DragEvent)message.obj;
            message.mLocalState = mLocalDragState;
            ViewRootImpl._2D_wrap8(ViewRootImpl.this, message);
            continue; /* Loop/switch isn't completed */
_L15:
            handleDispatchSystemUiVisibilityChanged((SystemUiVisibilityInfo)message.obj);
            continue; /* Loop/switch isn't completed */
_L16:
            obj = (Configuration)message.obj;
            message = ((Message) (obj));
            if(((Configuration) (obj)).isOtherSeqNewer(ViewRootImpl._2D_get1(ViewRootImpl.this).getMergedConfiguration()))
                message = ViewRootImpl._2D_get1(ViewRootImpl.this).getGlobalConfiguration();
            ViewRootImpl._2D_get2(ViewRootImpl.this).setConfiguration(message, ViewRootImpl._2D_get1(ViewRootImpl.this).getOverrideConfiguration());
            ViewRootImpl._2D_wrap12(ViewRootImpl.this, ViewRootImpl._2D_get2(ViewRootImpl.this), false, -1);
            continue; /* Loop/switch isn't completed */
_L18:
            setAccessibilityFocus(null, null);
            continue; /* Loop/switch isn't completed */
_L19:
            if(mView != null)
                invalidateWorld(mView);
            continue; /* Loop/switch isn't completed */
_L22:
            handleDispatchWindowShown();
            continue; /* Loop/switch isn't completed */
_L23:
            obj = (IResultReceiver)message.obj;
            i1 = message.arg1;
            handleRequestKeyboardShortcuts(((IResultReceiver) (obj)), i1);
            continue; /* Loop/switch isn't completed */
_L24:
            message = (MotionEvent)message.obj;
            ViewRootImpl._2D_wrap15(ViewRootImpl.this, message);
            continue; /* Loop/switch isn't completed */
_L25:
            if(message.arg1 != 0)
                flag3 = true;
            else
                flag3 = false;
            ViewRootImpl._2D_wrap9(ViewRootImpl.this, flag3);
            continue; /* Loop/switch isn't completed */
_L26:
            pendingDrawFinished();
            if(true) goto _L1; else goto _L31
_L31:
        }

        public boolean sendMessageAtTime(Message message, long l)
        {
            if(message.what == 26 && message.obj == null)
                throw new NullPointerException("Attempted to call MSG_REQUEST_KEYBOARD_SHORTCUTS with null receiver:");
            else
                return super.sendMessageAtTime(message, l);
        }

        final ViewRootImpl this$0;

        ViewRootHandler()
        {
            this$0 = ViewRootImpl.this;
            super();
        }
    }

    static class W extends IWindow.Stub
    {

        private static int checkCallingPermission(String s)
        {
            int i;
            try
            {
                i = ActivityManager.getService().checkPermission(s, Binder.getCallingPid(), Binder.getCallingUid());
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                return -1;
            }
            return i;
        }

        public void closeSystemDialogs(String s)
        {
            ViewRootImpl viewrootimpl = (ViewRootImpl)mViewAncestor.get();
            if(viewrootimpl != null)
                viewrootimpl.dispatchCloseSystemDialogs(s);
        }

        public void dispatchAppVisibility(boolean flag)
        {
            ViewRootImpl viewrootimpl = (ViewRootImpl)mViewAncestor.get();
            if(viewrootimpl != null)
                viewrootimpl.dispatchAppVisibility(flag);
        }

        public void dispatchDragEvent(DragEvent dragevent)
        {
            ViewRootImpl viewrootimpl = (ViewRootImpl)mViewAncestor.get();
            if(viewrootimpl != null)
                viewrootimpl.dispatchDragEvent(dragevent);
        }

        public void dispatchGetNewSurface()
        {
            ViewRootImpl viewrootimpl = (ViewRootImpl)mViewAncestor.get();
            if(viewrootimpl != null)
                viewrootimpl.dispatchGetNewSurface();
        }

        public void dispatchPointerCaptureChanged(boolean flag)
        {
            ViewRootImpl viewrootimpl = (ViewRootImpl)mViewAncestor.get();
            if(viewrootimpl != null)
                viewrootimpl.dispatchPointerCaptureChanged(flag);
        }

        public void dispatchSystemUiVisibilityChanged(int i, int j, int k, int l)
        {
            ViewRootImpl viewrootimpl = (ViewRootImpl)mViewAncestor.get();
            if(viewrootimpl != null)
                viewrootimpl.dispatchSystemUiVisibilityChanged(i, j, k, l);
        }

        public void dispatchWallpaperCommand(String s, int i, int j, int k, Bundle bundle, boolean flag)
        {
            if(!flag)
                break MISSING_BLOCK_LABEL_19;
            mWindowSession.wallpaperCommandComplete(asBinder(), null);
_L2:
            return;
            s;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public void dispatchWallpaperOffsets(float f, float f1, float f2, float f3, boolean flag)
        {
            if(!flag)
                break MISSING_BLOCK_LABEL_18;
            mWindowSession.wallpaperOffsetsComplete(asBinder());
_L2:
            return;
            RemoteException remoteexception;
            remoteexception;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public void dispatchWindowShown()
        {
            ViewRootImpl viewrootimpl = (ViewRootImpl)mViewAncestor.get();
            if(viewrootimpl != null)
                viewrootimpl.dispatchWindowShown();
        }

        public void executeCommand(String s, String s1, ParcelFileDescriptor parcelfiledescriptor)
        {
            Object obj;
            View view;
            Object obj1;
            Object obj2;
            obj = (ViewRootImpl)mViewAncestor.get();
            if(obj == null)
                break MISSING_BLOCK_LABEL_121;
            view = ((ViewRootImpl) (obj)).mView;
            if(view == null)
                break MISSING_BLOCK_LABEL_121;
            if(checkCallingPermission("android.permission.DUMP") != 0)
                throw new SecurityException((new StringBuilder()).append("Insufficient permissions to invoke executeCommand() from pid=").append(Binder.getCallingPid()).append(", uid=").append(Binder.getCallingUid()).toString());
            obj1 = null;
            obj2 = null;
            obj = obj1;
            android.os.ParcelFileDescriptor.AutoCloseOutputStream autocloseoutputstream = JVM INSTR new #137 <Class android.os.ParcelFileDescriptor$AutoCloseOutputStream>;
            obj = obj1;
            autocloseoutputstream.android.os.ParcelFileDescriptor.AutoCloseOutputStream(parcelfiledescriptor);
            ViewDebug.dispatchCommand(view, s, s1, autocloseoutputstream);
            if(autocloseoutputstream == null)
                break MISSING_BLOCK_LABEL_121;
            autocloseoutputstream.close();
_L1:
            return;
            s;
            s.printStackTrace();
              goto _L1
            s1;
            s = obj2;
_L4:
            obj = s;
            s1.printStackTrace();
            if(s != null)
                try
                {
                    s.close();
                }
                // Misplaced declaration of an exception variable
                catch(String s)
                {
                    s.printStackTrace();
                }
              goto _L1
            s;
_L3:
            if(obj != null)
                try
                {
                    ((OutputStream) (obj)).close();
                }
                // Misplaced declaration of an exception variable
                catch(String s1)
                {
                    s1.printStackTrace();
                }
            throw s;
            s;
            obj = autocloseoutputstream;
            if(true) goto _L3; else goto _L2
_L2:
            s1;
            s = autocloseoutputstream;
              goto _L4
        }

        public void moved(int i, int j)
        {
            ViewRootImpl viewrootimpl = (ViewRootImpl)mViewAncestor.get();
            if(viewrootimpl != null)
                viewrootimpl.dispatchMoved(i, j);
        }

        public void requestAppKeyboardShortcuts(IResultReceiver iresultreceiver, int i)
        {
            ViewRootImpl viewrootimpl = (ViewRootImpl)mViewAncestor.get();
            if(viewrootimpl != null)
                viewrootimpl.dispatchRequestKeyboardShortcuts(iresultreceiver, i);
        }

        public void resized(Rect rect, Rect rect1, Rect rect2, Rect rect3, Rect rect4, Rect rect5, boolean flag, 
                MergedConfiguration mergedconfiguration, Rect rect6, boolean flag1, boolean flag2, int i)
        {
            ViewRootImpl viewrootimpl = (ViewRootImpl)mViewAncestor.get();
            if(viewrootimpl != null)
                ViewRootImpl._2D_wrap5(viewrootimpl, rect, rect1, rect2, rect3, rect4, rect5, flag, mergedconfiguration, rect6, flag1, flag2, i);
        }

        public void updatePointerIcon(float f, float f1)
        {
            ViewRootImpl viewrootimpl = (ViewRootImpl)mViewAncestor.get();
            if(viewrootimpl != null)
                viewrootimpl.updatePointerIcon(f, f1);
        }

        public void windowFocusChanged(boolean flag, boolean flag1)
        {
            ViewRootImpl viewrootimpl = (ViewRootImpl)mViewAncestor.get();
            if(viewrootimpl != null)
                viewrootimpl.windowFocusChanged(flag, flag1);
        }

        private final WeakReference mViewAncestor;
        private final IWindowSession mWindowSession;

        W(ViewRootImpl viewrootimpl)
        {
            mViewAncestor = new WeakReference(viewrootimpl);
            mWindowSession = viewrootimpl.mWindowSession;
        }
    }

    final class WindowInputEventReceiver extends InputEventReceiver
    {

        public void dispose()
        {
            unscheduleConsumeBatchedInput();
            super.dispose();
        }

        public void onBatchedInputEventPending()
        {
            if(mUnbufferedInputDispatch)
                super.onBatchedInputEventPending();
            else
                scheduleConsumeBatchedInput();
        }

        public void onInputEvent(InputEvent inputevent, int i)
        {
            enqueueInputEvent(inputevent, this, 0, true);
        }

        final ViewRootImpl this$0;

        public WindowInputEventReceiver(InputChannel inputchannel, Looper looper)
        {
            this$0 = ViewRootImpl.this;
            super(inputchannel, looper);
        }
    }

    static interface WindowStoppedCallback
    {

        public abstract void windowStopped(boolean flag);
    }


    static Configuration _2D_get0(ViewRootImpl viewrootimpl)
    {
        return viewrootimpl.mLastConfigurationFromResources;
    }

    static MergedConfiguration _2D_get1(ViewRootImpl viewrootimpl)
    {
        return viewrootimpl.mLastReportedMergedConfiguration;
    }

    static MergedConfiguration _2D_get2(ViewRootImpl viewrootimpl)
    {
        return viewrootimpl.mPendingMergedConfiguration;
    }

    static Choreographer.FrameCallback _2D_get3(ViewRootImpl viewrootimpl)
    {
        return viewrootimpl.mRenderProfiler;
    }

    static boolean _2D_get4(ViewRootImpl viewrootimpl)
    {
        return viewrootimpl.mRenderProfilingEnabled;
    }

    static String _2D_get5(ViewRootImpl viewrootimpl)
    {
        return viewrootimpl.mTag;
    }

    static int _2D_set0(ViewRootImpl viewrootimpl, int i)
    {
        viewrootimpl.mPointerIconType = i;
        return i;
    }

    static boolean _2D_set1(ViewRootImpl viewrootimpl, boolean flag)
    {
        viewrootimpl.mProfileRendering = flag;
        return flag;
    }

    static View _2D_wrap0(ViewRootImpl viewrootimpl, View view, View view1)
    {
        return viewrootimpl.getCommonPredecessor(view, view1);
    }

    static boolean _2D_wrap1(ViewRootImpl viewrootimpl, KeyEvent keyevent)
    {
        return viewrootimpl.checkForLeavingTouchModeAndConsume(keyevent);
    }

    static void _2D_wrap10(ViewRootImpl viewrootimpl, Rect rect)
    {
        viewrootimpl.maybeHandleWindowMove(rect);
    }

    static void _2D_wrap11(ViewRootImpl viewrootimpl, MotionEvent motionevent)
    {
        viewrootimpl.maybeUpdateTooltip(motionevent);
    }

    static void _2D_wrap12(ViewRootImpl viewrootimpl, MergedConfiguration mergedconfiguration, boolean flag, int i)
    {
        viewrootimpl.performConfigurationChange(mergedconfiguration, flag, i);
    }

    static void _2D_wrap13(ViewRootImpl viewrootimpl, boolean flag)
    {
        viewrootimpl.profileRendering(flag);
    }

    static void _2D_wrap14(ViewRootImpl viewrootimpl)
    {
        viewrootimpl.reportNextDraw();
    }

    static void _2D_wrap15(ViewRootImpl viewrootimpl, MotionEvent motionevent)
    {
        viewrootimpl.resetPointerIcon(motionevent);
    }

    static boolean _2D_wrap2(ViewRootImpl viewrootimpl, boolean flag)
    {
        return viewrootimpl.ensureTouchModeLocally(flag);
    }

    static boolean _2D_wrap3(ViewRootImpl viewrootimpl)
    {
        return viewrootimpl.isInLocalFocusMode();
    }

    static boolean _2D_wrap4(ViewRootImpl viewrootimpl, MotionEvent motionevent)
    {
        return viewrootimpl.updatePointerIcon(motionevent);
    }

    static void _2D_wrap5(ViewRootImpl viewrootimpl, Rect rect, Rect rect1, Rect rect2, Rect rect3, Rect rect4, Rect rect5, boolean flag, 
            MergedConfiguration mergedconfiguration, Rect rect6, boolean flag1, boolean flag2, int i)
    {
        viewrootimpl.dispatchResized(rect, rect1, rect2, rect3, rect4, rect5, flag, mergedconfiguration, rect6, flag1, flag2, i);
    }

    static void _2D_wrap6(ViewRootImpl viewrootimpl, QueuedInputEvent queuedinputevent)
    {
        viewrootimpl.finishInputEvent(queuedinputevent);
    }

    static void _2D_wrap7(View view)
    {
        forceLayout(view);
    }

    static void _2D_wrap8(ViewRootImpl viewrootimpl, DragEvent dragevent)
    {
        viewrootimpl.handleDragEvent(dragevent);
    }

    static void _2D_wrap9(ViewRootImpl viewrootimpl, boolean flag)
    {
        viewrootimpl.handlePointerCaptureChanged(flag);
    }

    public ViewRootImpl(Context context, Display display)
    {
        mAppVisible = true;
        mForceDecorViewVisibility = false;
        mOrigWindowType = -1;
        mStopped = false;
        mIsAmbientMode = false;
        mPausedForTransition = false;
        mLastInCompatMode = false;
        mPendingInputEventQueueLengthCounterName = "pq";
        mWindowAttributesChanged = false;
        mWindowAttributesChangesFlag = 0;
        mFpsStartTime = -1L;
        mFpsPrevTime = -1L;
        mPointerIconType = 1;
        mCustomPointerIcon = null;
        mInLayout = false;
        mLayoutRequesters = new ArrayList();
        mHandlingLayoutInLayoutRequest = false;
        InputEventConsistencyVerifier inputeventconsistencyverifier;
        if(InputEventConsistencyVerifier.isInstrumentationEnabled())
            inputeventconsistencyverifier = new InputEventConsistencyVerifier(this, 0);
        else
            inputeventconsistencyverifier = null;
        mInputEventConsistencyVerifier = inputeventconsistencyverifier;
        mTag = "ViewRootImpl";
        mHaveMoveEvent = false;
        mIsPerfLockAcquired = false;
        mPerf = null;
        mProfile = false;
        mDrawsNeededToReport = 0;
        mContext = context;
        mDisplay = display;
        mBasePackageName = context.getBasePackageName();
        mLocation.fillInStackTrace();
        mWidth = -1;
        mHeight = -1;
        mDirty = new Rect();
        mTargetSdkVersion = context.getApplicationInfo().targetSdkVersion;
        mViewVisibility = 8;
        mFirst = true;
        mAdded = false;
        mAttachInfo = new View.AttachInfo(mWindowSession, mWindow, display, this, mHandler, this, context);
        mAccessibilityManager = AccessibilityManager.getInstance(context);
        mAccessibilityManager.addAccessibilityStateChangeListener(mAccessibilityInteractionConnectionManager, mHandler);
        mAccessibilityManager.addHighTextContrastStateChangeListener(mHighContrastTextManager, mHandler);
        mViewConfiguration = ViewConfiguration.get(context);
        mDensity = context.getResources().getDisplayMetrics().densityDpi;
        mNoncompatDensity = context.getResources().getDisplayMetrics().noncompatDensityDpi;
        mFallbackEventHandler = new PhoneFallbackEventHandler(context);
        mChoreographer = Choreographer.getInstance();
        mDisplayManager = (DisplayManager)context.getSystemService("display");
        if(!sCompatibilityDone)
        {
            sAlwaysAssignFocus = true;
            sCompatibilityDone = true;
        }
        loadSystemProperties();
    }

    public static void addConfigCallback(ConfigChangedCallback configchangedcallback)
    {
        ArrayList arraylist = sConfigCallbacks;
        arraylist;
        JVM INSTR monitorenter ;
        sConfigCallbacks.add(configchangedcallback);
        arraylist;
        JVM INSTR monitorexit ;
        return;
        configchangedcallback;
        throw configchangedcallback;
    }

    public static void addFirstDrawHandler(Runnable runnable)
    {
        ArrayList arraylist = sFirstDrawHandlers;
        arraylist;
        JVM INSTR monitorenter ;
        if(!sFirstDrawComplete)
            sFirstDrawHandlers.add(runnable);
        arraylist;
        JVM INSTR monitorexit ;
        return;
        runnable;
        throw runnable;
    }

    private void adjustInputEventForCompatibility(InputEvent inputevent)
    {
        if(mTargetSdkVersion < 23 && (inputevent instanceof MotionEvent))
        {
            inputevent = (MotionEvent)inputevent;
            int i = inputevent.getButtonState();
            int j = (i & 0x60) >> 4;
            if(j != 0)
                inputevent.setButtonState(i | j);
        }
    }

    private void applyKeepScreenOnFlag(WindowManager.LayoutParams layoutparams)
    {
        if(mAttachInfo.mKeepScreenOn)
            layoutparams.flags = layoutparams.flags | 0x80;
        else
            layoutparams.flags = layoutparams.flags & 0xffffff7f | mClientWindowLayoutFlags & 0x80;
    }

    private boolean checkForLeavingTouchModeAndConsume(KeyEvent keyevent)
    {
        if(!mAttachInfo.mInTouchMode)
            return false;
        int i = keyevent.getAction();
        if(i != 0 && i != 2)
            return false;
        if((keyevent.getFlags() & 4) != 0)
            return false;
        if(isNavigationKey(keyevent))
            return ensureTouchMode(false);
        if(isTypingKey(keyevent))
        {
            ensureTouchMode(false);
            return false;
        } else
        {
            return false;
        }
    }

    private boolean collectViewAttributes()
    {
        if(mAttachInfo.mRecomputeGlobalAttributes)
        {
            mAttachInfo.mRecomputeGlobalAttributes = false;
            boolean flag = mAttachInfo.mKeepScreenOn;
            mAttachInfo.mKeepScreenOn = false;
            mAttachInfo.mSystemUiVisibility = 0;
            mAttachInfo.mHasSystemUiListeners = false;
            mView.dispatchCollectViewAttributes(mAttachInfo, 0);
            Object obj = mAttachInfo;
            obj.mSystemUiVisibility = ((View.AttachInfo) (obj)).mSystemUiVisibility & mAttachInfo.mDisabledSystemUiVisibility;
            obj = mWindowAttributes;
            View.AttachInfo attachinfo = mAttachInfo;
            for(attachinfo.mSystemUiVisibility = attachinfo.mSystemUiVisibility | getImpliedSystemUiVisibility(((WindowManager.LayoutParams) (obj))); mAttachInfo.mKeepScreenOn != flag || mAttachInfo.mSystemUiVisibility != ((WindowManager.LayoutParams) (obj)).subtreeSystemUiVisibility || mAttachInfo.mHasSystemUiListeners != ((WindowManager.LayoutParams) (obj)).hasSystemUiListeners;)
            {
                applyKeepScreenOnFlag(((WindowManager.LayoutParams) (obj)));
                obj.subtreeSystemUiVisibility = mAttachInfo.mSystemUiVisibility;
                obj.hasSystemUiListeners = mAttachInfo.mHasSystemUiListeners;
                mView.dispatchWindowSystemUiVisiblityChanged(mAttachInfo.mSystemUiVisibility);
                return true;
            }

        }
        return false;
    }

    private void deliverInputEvent(QueuedInputEvent queuedinputevent)
    {
        Trace.asyncTraceBegin(8L, "deliverInputEvent", queuedinputevent.mEvent.getSequenceNumber());
        if(mInputEventConsistencyVerifier != null)
            mInputEventConsistencyVerifier.onInputEvent(queuedinputevent.mEvent, 0);
        InputStage inputstage;
        if(queuedinputevent.shouldSendToSynthesizer())
            inputstage = mSyntheticInputStage;
        else
        if(queuedinputevent.shouldSkipIme())
            inputstage = mFirstPostImeInputStage;
        else
            inputstage = mFirstInputStage;
        if(inputstage != null)
            inputstage.deliver(queuedinputevent);
        else
            finishInputEvent(queuedinputevent);
    }

    private void destroyHardwareRenderer()
    {
        ThreadedRenderer threadedrenderer = mAttachInfo.mThreadedRenderer;
        if(threadedrenderer != null)
        {
            if(mView != null)
                threadedrenderer.destroyHardwareResources(mView);
            threadedrenderer.destroy();
            threadedrenderer.setRequested(false);
            mAttachInfo.mThreadedRenderer = null;
            mAttachInfo.mHardwareAccelerated = false;
        }
    }

    private int dipToPx(int i)
    {
        return (int)(mContext.getResources().getDisplayMetrics().density * (float)i + 0.5F);
    }

    private void dispatchResized(Rect rect, Rect rect1, Rect rect2, Rect rect3, Rect rect4, Rect rect5, boolean flag, 
            MergedConfiguration mergedconfiguration, Rect rect6, boolean flag1, boolean flag2, int i)
    {
        if(!mDragResizing) goto _L2; else goto _L1
_L1:
        boolean flag3 = rect.equals(rect6);
        Object obj = mWindowCallbacks;
        obj;
        JVM INSTR monitorenter ;
        int j = mWindowCallbacks.size() - 1;
_L3:
        if(j < 0)
            break; /* Loop/switch isn't completed */
        ((WindowCallbacks)mWindowCallbacks.get(j)).onWindowSizeIsChanging(rect6, flag3, rect3, rect4);
        j--;
        if(true) goto _L3; else goto _L2
_L2:
        obj = mHandler;
        int k;
        Message message;
        SomeArgs someargs;
        if(flag)
            k = 5;
        else
            k = 4;
        message = ((ViewRootHandler) (obj)).obtainMessage(k);
        if(mTranslator != null)
        {
            mTranslator.translateRectInScreenToAppWindow(rect);
            mTranslator.translateRectInScreenToAppWindow(rect1);
            mTranslator.translateRectInScreenToAppWindow(rect2);
            mTranslator.translateRectInScreenToAppWindow(rect3);
        }
        someargs = SomeArgs.obtain();
        if(Binder.getCallingPid() == Process.myPid())
            k = 1;
        else
            k = 0;
        obj = rect;
        if(k != 0)
            obj = new Rect(rect);
        someargs.arg1 = obj;
        rect = rect2;
        if(k != 0)
            rect = new Rect(rect2);
        someargs.arg2 = rect;
        rect = rect3;
        if(k != 0)
            rect = new Rect(rect3);
        someargs.arg3 = rect;
        rect = mergedconfiguration;
        if(k != 0)
        {
            rect = mergedconfiguration;
            if(mergedconfiguration != null)
                rect = new MergedConfiguration(mergedconfiguration);
        }
        someargs.arg4 = rect;
        rect = rect1;
        if(k != 0)
            rect = new Rect(rect1);
        someargs.arg5 = rect;
        rect = rect4;
        if(k != 0)
            rect = new Rect(rect4);
        someargs.arg6 = rect;
        rect = rect5;
        if(k != 0)
            rect = new Rect(rect5);
        someargs.arg7 = rect;
        rect = rect6;
        if(k != 0)
            rect = new Rect(rect6);
        someargs.arg8 = rect;
        if(flag1)
            k = 1;
        else
            k = 0;
        someargs.argi1 = k;
        if(flag2)
            k = 1;
        else
            k = 0;
        someargs.argi2 = k;
        someargs.argi3 = i;
        message.obj = someargs;
        mHandler.sendMessage(message);
        return;
        rect;
        throw rect;
    }

    private void draw(boolean flag)
    {
        Object obj;
        obj = mSurface;
        if(!((Surface) (obj)).isValid())
            return;
        if(sFirstDrawComplete) goto _L2; else goto _L1
_L1:
        Object obj1 = sFirstDrawHandlers;
        obj1;
        JVM INSTR monitorenter ;
        int i;
        sFirstDrawComplete = true;
        i = sFirstDrawHandlers.size();
        int k = 0;
_L3:
        if(k >= i)
            break; /* Loop/switch isn't completed */
        mHandler.post((Runnable)sFirstDrawHandlers.get(k));
        k++;
        if(true) goto _L3; else goto _L2
_L2:
        boolean flag1;
        boolean flag3;
        float f;
        Rect rect;
        scrollToRectOrFocus(null, false);
        if(mAttachInfo.mViewScrollChanged)
        {
            if(mHaveMoveEvent && mIsPerfLockAcquired ^ true)
            {
                mIsPerfLockAcquired = true;
                if(mPerf == null)
                    mPerf = new BoostFramework();
                if(mPerf != null)
                {
                    obj1 = mContext.getPackageName();
                    mPerf.perfHint(4224, ((String) (obj1)), -1, 4);
                }
            }
            mAttachInfo.mViewScrollChanged = false;
            mAttachInfo.mTreeObserver.dispatchOnScrollChanged();
        }
        if(mScroller != null)
            flag1 = mScroller.computeScrollOffset();
        else
            flag1 = false;
        if(flag1)
            k = mScroller.getCurrY();
        else
            k = mScrollY;
        if(mCurScrollY != k)
        {
            mCurScrollY = k;
            boolean flag2 = true;
            flag = flag2;
            if(mView instanceof RootViewSurfaceTaker)
            {
                ((RootViewSurfaceTaker)mView).onRootViewScrollYChanged(mCurScrollY);
                flag = flag2;
            }
        }
        f = mAttachInfo.mApplicationScale;
        flag3 = mAttachInfo.mScalingRequired;
        rect = mDirty;
        if(mSurfaceHolder != null)
        {
            rect.setEmpty();
            if(flag1 && mScroller != null)
                mScroller.abortAnimation();
            return;
        }
        break MISSING_BLOCK_LABEL_333;
        obj;
        throw obj;
        if(flag)
        {
            mAttachInfo.mIgnoreDirtyState = true;
            rect.set(0, 0, (int)((float)mWidth * f + 0.5F), (int)((float)mHeight * f + 0.5F));
        }
        mAttachInfo.mTreeObserver.dispatchOnDraw();
        int l = -mCanvasOffsetX;
        int i1 = -mCanvasOffsetY + k;
        Object obj2 = mWindowAttributes;
        int j;
        boolean flag4;
        Drawable drawable;
        if(obj2 != null)
            obj2 = ((WindowManager.LayoutParams) (obj2)).surfaceInsets;
        else
            obj2 = null;
        j = l;
        k = i1;
        if(obj2 != null)
        {
            j = l - ((Rect) (obj2)).left;
            k = i1 - ((Rect) (obj2)).top;
            rect.offset(((Rect) (obj2)).left, ((Rect) (obj2)).right);
        }
        i1 = 0;
        drawable = mAttachInfo.mAccessibilityFocusDrawable;
        flag4 = i1;
        if(drawable != null)
        {
            Rect rect1 = mAttachInfo.mTmpInvalRect;
            if(!getAccessibilityFocusedRect(rect1))
                rect1.setEmpty();
            flag4 = i1;
            if(!rect1.equals(drawable.getBounds()))
                flag4 = true;
        }
        mAttachInfo.mDrawingTime = mChoreographer.getFrameTimeNanos() / 0xf4240L;
        if(!rect.isEmpty() || mIsAnimating || flag4)
            if(mAttachInfo.mThreadedRenderer != null && mAttachInfo.mThreadedRenderer.isEnabled())
            {
                if(!flag4)
                    flag = mInvalidateRootRequested;
                else
                    flag = true;
                mInvalidateRootRequested = false;
                mIsAnimating = false;
                if(mHardwareYOffset != k || mHardwareXOffset != j)
                {
                    mHardwareYOffset = k;
                    mHardwareXOffset = j;
                    flag = true;
                }
                if(flag)
                    mAttachInfo.mThreadedRenderer.invalidateRoot();
                rect.setEmpty();
                flag = updateContentDrawBounds();
                if(mReportNextDraw)
                    mAttachInfo.mThreadedRenderer.setStopped(false);
                if(flag)
                    requestDrawWindow();
                if(MIUI_OPTS_INPUT && mChoreographer.needAdvancedSf())
                    ((Surface) (obj)).advancedSfComposition();
                mAttachInfo.mThreadedRenderer.draw(mView, mAttachInfo, this);
            } else
            {
                if(mAttachInfo.mThreadedRenderer != null && mAttachInfo.mThreadedRenderer.isEnabled() ^ true && mAttachInfo.mThreadedRenderer.isRequested())
                {
                    try
                    {
                        mAttachInfo.mThreadedRenderer.initializeIfNeeded(mWidth, mHeight, mAttachInfo, mSurface, ((Rect) (obj2)));
                    }
                    catch(Surface.OutOfResourcesException outofresourcesexception)
                    {
                        handleOutOfResourcesException(outofresourcesexception);
                        return;
                    }
                    mFullRedrawNeeded = true;
                    scheduleTraversals();
                    return;
                }
                if(!drawSoftware(((Surface) (obj)), mAttachInfo, j, k, flag3, rect))
                    return;
            }
        if(flag1)
        {
            mFullRedrawNeeded = true;
            scheduleTraversals();
        }
        return;
    }

    private void drawAccessibilityFocusedDrawableIfNeeded(Canvas canvas)
    {
        Rect rect = mAttachInfo.mTmpInvalRect;
        if(!getAccessibilityFocusedRect(rect)) goto _L2; else goto _L1
_L1:
        Drawable drawable = getAccessibilityFocusedDrawable();
        if(drawable != null)
        {
            drawable.setBounds(rect);
            drawable.draw(canvas);
        }
_L4:
        return;
_L2:
        if(mAttachInfo.mAccessibilityFocusDrawable != null)
            mAttachInfo.mAccessibilityFocusDrawable.setBounds(0, 0, 0, 0);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private boolean drawSoftware(Surface surface, View.AttachInfo attachinfo, int i, int j, boolean flag, Rect rect)
    {
        int k;
        int i1;
        int j1;
        int k1;
        Canvas canvas;
        k = rect.left;
        i1 = rect.top;
        j1 = rect.right;
        k1 = rect.bottom;
        canvas = mSurface.lockCanvas(rect);
        if(k == rect.left && i1 == rect.top) goto _L2; else goto _L1
_L1:
        attachinfo.mIgnoreDirtyState = true;
_L6:
        canvas.setDensity(mDensity);
          goto _L3
_L7:
        canvas.drawColor(0, android.graphics.PorterDuff.Mode.CLEAR);
_L8:
        rect.setEmpty();
        mIsAnimating = false;
        rect = mView;
        rect.mPrivateFlags = ((View) (rect)).mPrivateFlags | 0x20;
        float f;
        float f1;
        f = -i;
        f1 = -j;
        canvas.translate(f, f1);
        if(mTranslator != null)
            mTranslator.translateCanvas(canvas);
        if(!flag) goto _L5; else goto _L4
_L4:
        i = mNoncompatDensity;
_L9:
        canvas.setScreenDensity(i);
        attachinfo.mSetIgnoreDirtyState = false;
        mView.draw(canvas);
        drawAccessibilityFocusedDrawableIfNeeded(canvas);
        if(!attachinfo.mSetIgnoreDirtyState)
            attachinfo.mIgnoreDirtyState = false;
        int l;
        try
        {
            surface.unlockCanvasAndPost(canvas);
        }
        // Misplaced declaration of an exception variable
        catch(Surface surface)
        {
            Log.e(mTag, "Could not unlock surface", surface);
            mLayoutRequested = true;
            return false;
        }
        return true;
_L2:
        try
        {
            if(j1 != rect.right)
                break; /* Loop/switch isn't completed */
            l = rect.bottom;
        }
        // Misplaced declaration of an exception variable
        catch(Surface surface)
        {
            handleOutOfResourcesException(surface);
            return false;
        }
        // Misplaced declaration of an exception variable
        catch(Surface surface)
        {
            Log.e(mTag, "Could not lock surface", surface);
            mLayoutRequested = true;
            return false;
        }
        if(k1 == l) goto _L6; else goto _L1
_L3:
        if(canvas.isOpaque() && j == 0 && i == 0) goto _L8; else goto _L7
_L5:
        i = 0;
          goto _L9
        rect;
        if(!attachinfo.mSetIgnoreDirtyState)
            attachinfo.mIgnoreDirtyState = false;
        throw rect;
        attachinfo;
        try
        {
            surface.unlockCanvasAndPost(canvas);
        }
        // Misplaced declaration of an exception variable
        catch(Surface surface)
        {
            Log.e(mTag, "Could not unlock surface", surface);
            mLayoutRequested = true;
            return false;
        }
        throw attachinfo;
    }

    private void dumpViewHierarchy(String s, PrintWriter printwriter, View view)
    {
        printwriter.print(s);
        if(view == null)
        {
            printwriter.println("null");
            return;
        }
        printwriter.println(view.toString());
        if(!(view instanceof ViewGroup))
            return;
        view = (ViewGroup)view;
        int i = view.getChildCount();
        if(i <= 0)
            return;
        s = (new StringBuilder()).append(s).append("  ").toString();
        for(int j = 0; j < i; j++)
            dumpViewHierarchy(s, printwriter, view.getChildAt(j));

    }

    private void enableHardwareAcceleration(WindowManager.LayoutParams layoutparams)
    {
        mAttachInfo.mHardwareAccelerated = false;
        mAttachInfo.mHardwareAccelerationRequested = false;
        if(mTranslator != null)
            return;
        boolean flag;
        if((layoutparams.flags & 0x1000000) != 0)
            flag = true;
        else
            flag = false;
        if(flag)
        {
            if(!ThreadedRenderer.isAvailable())
                return;
            boolean flag1;
            if((layoutparams.privateFlags & 1) != 0)
                flag = true;
            else
                flag = false;
            if((layoutparams.privateFlags & 2) != 0)
                flag1 = true;
            else
                flag1 = false;
            if(flag)
            {
                mAttachInfo.mHardwareAccelerationRequested = true;
            } else
            {
                Rect rect;
                if(!ThreadedRenderer.sRendererDisabled || ThreadedRenderer.sSystemRendererDisabled && flag1)
                {
                    if(mAttachInfo.mThreadedRenderer != null)
                        mAttachInfo.mThreadedRenderer.destroy();
                    rect = layoutparams.surfaceInsets;
                }
                while(false) 
                {
                    boolean flag2;
                    boolean flag3;
                    if(rect.left != 0 || rect.right != 0 || rect.top != 0)
                        flag2 = true;
                    else
                    if(rect.bottom != 0)
                        flag2 = true;
                    else
                        flag2 = false;
                    if(layoutparams.format == -1)
                        flag3 = flag2;
                    else
                        flag3 = true;
                    if(mContext.getResources().getConfiguration().isScreenWideColorGamut())
                    {
                        if(layoutparams.getColorMode() == 1)
                            flag2 = true;
                        else
                            flag2 = false;
                    } else
                    {
                        flag2 = false;
                    }
                    mAttachInfo.mThreadedRenderer = ThreadedRenderer.create(mContext, flag3, layoutparams.getTitle().toString());
                    mAttachInfo.mThreadedRenderer.setWideGamut(flag2);
                    if(mAttachInfo.mThreadedRenderer != null)
                    {
                        layoutparams = mAttachInfo;
                        mAttachInfo.mHardwareAccelerationRequested = true;
                        layoutparams.mHardwareAccelerated = true;
                    }
                }
            }
        }
    }

    private void endDragResizing()
    {
        if(mDragResizing)
        {
            mDragResizing = false;
            for(int i = mWindowCallbacks.size() - 1; i >= 0; i--)
                ((WindowCallbacks)mWindowCallbacks.get(i)).onWindowDragResizeEnd();

            mFullRedrawNeeded = true;
        }
    }

    private boolean ensureTouchModeLocally(boolean flag)
    {
        if(mAttachInfo.mInTouchMode == flag)
            return false;
        mAttachInfo.mInTouchMode = flag;
        mAttachInfo.mTreeObserver.dispatchOnTouchModeChanged(flag);
        if(flag)
            flag = enterTouchMode();
        else
            flag = leaveTouchMode();
        return flag;
    }

    private boolean enterTouchMode()
    {
        if(mView != null && mView.hasFocus())
        {
            View view = mView.findFocus();
            if(view != null && view.isFocusableInTouchMode() ^ true)
            {
                ViewGroup viewgroup = findAncestorToTakeFocusInTouchMode(view);
                if(viewgroup != null)
                {
                    return viewgroup.requestFocus();
                } else
                {
                    view.clearFocusInternal(null, true, false);
                    return true;
                }
            }
        }
        return false;
    }

    private static ViewGroup findAncestorToTakeFocusInTouchMode(View view)
    {
        for(view = view.getParent(); view instanceof ViewGroup; view = view.getParent())
        {
            view = (ViewGroup)view;
            if(view.getDescendantFocusability() == 0x40000 && view.isFocusableInTouchMode())
                return view;
            if(view.isRootNamespace())
                return null;
        }

        return null;
    }

    private void finishInputEvent(QueuedInputEvent queuedinputevent)
    {
        Trace.asyncTraceEnd(8L, "deliverInputEvent", queuedinputevent.mEvent.getSequenceNumber());
        if(queuedinputevent.mReceiver != null)
        {
            boolean flag;
            if((queuedinputevent.mFlags & 8) != 0)
                flag = true;
            else
                flag = false;
            queuedinputevent.mReceiver.finishInputEvent(queuedinputevent.mEvent, flag);
        } else
        {
            queuedinputevent.mEvent.recycleIfNeededAfterDispatch();
        }
        recycleQueuedInputEvent(queuedinputevent);
    }

    private static void forceLayout(View view)
    {
        view.forceLayout();
        if(view instanceof ViewGroup)
        {
            view = (ViewGroup)view;
            int i = view.getChildCount();
            for(int j = 0; j < i; j++)
                forceLayout(view.getChildAt(j));

        }
    }

    private Drawable getAccessibilityFocusedDrawable()
    {
        if(mAttachInfo.mAccessibilityFocusDrawable == null)
        {
            TypedValue typedvalue = new TypedValue();
            if(mView.mContext.getTheme().resolveAttribute(0x1110004, typedvalue, true))
                mAttachInfo.mAccessibilityFocusDrawable = mView.mContext.getDrawable(typedvalue.resourceId);
        }
        return mAttachInfo.mAccessibilityFocusDrawable;
    }

    private boolean getAccessibilityFocusedRect(Rect rect)
    {
        Object obj = AccessibilityManager.getInstance(mView.mContext);
        if(!((AccessibilityManager) (obj)).isEnabled() || ((AccessibilityManager) (obj)).isTouchExplorationEnabled() ^ true)
            return false;
        obj = mAccessibilityFocusedHost;
        if(obj == null || ((View) (obj)).mAttachInfo == null)
            return false;
        if(((View) (obj)).getAccessibilityNodeProvider() == null)
            ((View) (obj)).getBoundsOnScreen(rect, true);
        else
        if(mAccessibilityFocusedVirtualView != null)
            mAccessibilityFocusedVirtualView.getBoundsInScreen(rect);
        else
            return false;
        obj = mAttachInfo;
        rect.offset(0, ((View.AttachInfo) (obj)).mViewRootImpl.mScrollY);
        rect.offset(-((View.AttachInfo) (obj)).mWindowLeft, -((View.AttachInfo) (obj)).mWindowTop);
        if(!rect.intersect(0, 0, ((View.AttachInfo) (obj)).mViewRootImpl.mWidth, ((View.AttachInfo) (obj)).mViewRootImpl.mHeight))
            rect.setEmpty();
        return rect.isEmpty() ^ true;
    }

    private AudioManager getAudioManager()
    {
        if(mView == null)
            throw new IllegalStateException("getAudioManager called when there is no mView");
        if(mAudioManager == null)
            mAudioManager = (AudioManager)mView.getContext().getSystemService("audio");
        return mAudioManager;
    }

    private View getCommonPredecessor(View view, View view1)
    {
        if(mTempHashSet == null)
            mTempHashSet = new HashSet();
        HashSet hashset = mTempHashSet;
        hashset.clear();
        while(view != null) 
        {
            hashset.add(view);
            view = view.mParent;
            if(view instanceof View)
                view = (View)view;
            else
                view = null;
        }
        for(view = view1; view != null;)
        {
            if(hashset.contains(view))
            {
                hashset.clear();
                return view;
            }
            view = view.mParent;
            if(view instanceof View)
                view = (View)view;
            else
                view = null;
        }

        hashset.clear();
        return null;
    }

    private static void getGfxInfo(View view, int ai[])
    {
        RenderNode rendernode = view.mRenderNode;
        ai[0] = ai[0] + 1;
        if(rendernode != null)
            ai[1] = ai[1] + rendernode.getDebugSize();
        if(view instanceof ViewGroup)
        {
            view = (ViewGroup)view;
            int i = view.getChildCount();
            for(int j = 0; j < i; j++)
                getGfxInfo(view.getChildAt(j), ai);

        }
    }

    private int getImpliedSystemUiVisibility(WindowManager.LayoutParams layoutparams)
    {
        char c = '\0';
        if((layoutparams.flags & 0x4000000) != 0)
            c = '\u0500';
        int i = c;
        if((layoutparams.flags & 0x8000000) != 0)
            i = c | 0x300;
        return i;
    }

    private static int getRootMeasureSpec(int i, int j)
    {
        j;
        JVM INSTR tableswitch -2 -1: default 24
    //                   -2 45
    //                   -1 34;
           goto _L1 _L2 _L3
_L1:
        i = View.MeasureSpec.makeMeasureSpec(j, 0x40000000);
_L5:
        return i;
_L3:
        i = View.MeasureSpec.makeMeasureSpec(i, 0x40000000);
        continue; /* Loop/switch isn't completed */
_L2:
        i = View.MeasureSpec.makeMeasureSpec(i, 0x80000000);
        if(true) goto _L5; else goto _L4
_L4:
    }

    static HandlerActionQueue getRunQueue()
    {
        HandlerActionQueue handleractionqueue = (HandlerActionQueue)sRunQueues.get();
        if(handleractionqueue != null)
        {
            return handleractionqueue;
        } else
        {
            HandlerActionQueue handleractionqueue1 = new HandlerActionQueue();
            sRunQueues.set(handleractionqueue1);
            return handleractionqueue1;
        }
    }

    private ArrayList getValidLayoutRequesters(ArrayList arraylist, boolean flag)
    {
        int i;
        Object obj;
        int j;
        i = arraylist.size();
        obj = null;
        j = 0;
_L15:
        if(j >= i) goto _L2; else goto _L1
_L1:
        View view;
        Object obj1;
        view = (View)arraylist.get(j);
        obj1 = obj;
        if(view == null) goto _L4; else goto _L3
_L3:
        obj1 = obj;
        if(view.mAttachInfo == null) goto _L4; else goto _L5
_L5:
        obj1 = obj;
        if(view.mParent == null) goto _L4; else goto _L6
_L6:
        if(flag) goto _L8; else goto _L7
_L7:
        obj1 = obj;
        if((view.mPrivateFlags & 0x1000) != 4096) goto _L4; else goto _L8
_L8:
        boolean flag1;
        flag1 = false;
        obj1 = view;
_L13:
        boolean flag2 = flag1;
        if(obj1 == null) goto _L10; else goto _L9
_L9:
        if((((View) (obj1)).mViewFlags & 0xc) != 8) goto _L12; else goto _L11
_L11:
        flag2 = true;
_L10:
        obj1 = obj;
        if(!flag2)
        {
            obj1 = obj;
            if(obj == null)
                obj1 = new ArrayList();
            ((ArrayList) (obj1)).add(view);
        }
_L4:
        j++;
        obj = obj1;
        continue; /* Loop/switch isn't completed */
_L12:
        if(((View) (obj1)).mParent instanceof View)
            obj1 = (View)((View) (obj1)).mParent;
        else
            obj1 = null;
        if(true) goto _L13; else goto _L2
_L2:
        if(!flag)
        {
            for(int k = 0; k < i; k++)
            {
                for(View view1 = (View)arraylist.get(k); view1 != null && (view1.mPrivateFlags & 0x1000) != 0;)
                {
                    view1.mPrivateFlags = view1.mPrivateFlags & 0xffffefff;
                    if(view1.mParent instanceof View)
                        view1 = (View)view1.mParent;
                    else
                        view1 = null;
                }

            }

        }
        arraylist.clear();
        return ((ArrayList) (obj));
        if(true) goto _L15; else goto _L14
_L14:
    }

    private void handleDragEvent(DragEvent dragevent)
    {
        if(mView == null || !mAdded) goto _L2; else goto _L1
_L1:
        int i;
        i = dragevent.mAction;
        if(i == 1)
        {
            mCurrentDragView = null;
            mDragDescription = dragevent.mClipDescription;
        } else
        {
            if(i == 4)
                mDragDescription = null;
            dragevent.mClipDescription = mDragDescription;
        }
        if(i != 6) goto _L4; else goto _L3
_L3:
        if(View.sCascadedDragDrop)
            mView.dispatchDragEnterExitInPreN(dragevent);
        setDragFocus(null, dragevent);
_L2:
        dragevent.recycle();
        return;
_L4:
        boolean flag;
        if(i == 2 || i == 3)
        {
            mDragPoint.set(dragevent.mX, dragevent.mY);
            if(mTranslator != null)
                mTranslator.translatePointInScreenToAppWindow(mDragPoint);
            if(mCurScrollY != 0)
                mDragPoint.offset(0.0F, mCurScrollY);
            dragevent.mX = mDragPoint.x;
            dragevent.mY = mDragPoint.y;
        }
        View view = mCurrentDragView;
        if(i == 3 && dragevent.mClipData != null)
            dragevent.mClipData.prepareToEnterProcess();
        flag = mView.dispatchDragEvent(dragevent);
        if(i == 2 && dragevent.mEventHandlerWasCalled ^ true)
            setDragFocus(null, dragevent);
        if(view == mCurrentDragView)
            break MISSING_BLOCK_LABEL_274;
        if(view == null)
            break MISSING_BLOCK_LABEL_254;
        mWindowSession.dragRecipientExited(mWindow);
        if(mCurrentDragView != null)
            mWindowSession.dragRecipientEntered(mWindow);
_L5:
        RemoteException remoteexception;
        if(i == 3)
            try
            {
                String s = mTag;
                StringBuilder stringbuilder = JVM INSTR new #1416 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Log.i(s, stringbuilder.append("Reporting drop result: ").append(flag).toString());
                mWindowSession.reportDropResult(mWindow, flag);
            }
            catch(RemoteException remoteexception1)
            {
                Log.e(mTag, "Unable to report drop result");
            }
        if(i == 4)
        {
            mCurrentDragView = null;
            setLocalDragState(null);
            mAttachInfo.mDragToken = null;
            if(mAttachInfo.mDragSurface != null)
            {
                mAttachInfo.mDragSurface.release();
                mAttachInfo.mDragSurface = null;
            }
        }
          goto _L2
        remoteexception;
        Slog.e(mTag, "Unable to note drag target change");
          goto _L5
    }

    private void handleOutOfResourcesException(Surface.OutOfResourcesException outofresourcesexception)
    {
        Log.e(mTag, "OutOfResourcesException initializing HW surface", outofresourcesexception);
        try
        {
            if(!mWindowSession.outOfMemory(mWindow) && Process.myUid() != 1000)
            {
                Slog.w(mTag, "No processes killed for memory; killing self");
                Process.killProcess(Process.myPid());
            }
        }
        // Misplaced declaration of an exception variable
        catch(Surface.OutOfResourcesException outofresourcesexception) { }
        mLayoutRequested = true;
    }

    private void handlePointerCaptureChanged(boolean flag)
    {
        if(mPointerCapture == flag)
            return;
        mPointerCapture = flag;
        if(mView != null)
            mView.dispatchPointerCaptureChanged(flag);
    }

    private void handleWindowContentChangedEvent(AccessibilityEvent accessibilityevent)
    {
        View view;
        AccessibilityNodeProvider accessibilitynodeprovider;
        int i;
        view = mAccessibilityFocusedHost;
        if(view == null || mAccessibilityFocusedVirtualView == null)
            return;
        accessibilitynodeprovider = view.getAccessibilityNodeProvider();
        if(accessibilitynodeprovider == null)
        {
            mAccessibilityFocusedHost = null;
            mAccessibilityFocusedVirtualView = null;
            view.clearAccessibilityFocusNoCallbacks(0);
            return;
        }
        i = accessibilityevent.getContentChangeTypes();
        if((i & 1) == 0 && i != 0)
            return;
        int j = AccessibilityNodeInfo.getAccessibilityViewId(accessibilityevent.getSourceNodeId());
        boolean flag = false;
        for(accessibilityevent = mAccessibilityFocusedHost; accessibilityevent != null && flag ^ true;)
            if(j == accessibilityevent.getAccessibilityViewId())
            {
                flag = true;
            } else
            {
                accessibilityevent = accessibilityevent.getParent();
                if(accessibilityevent instanceof View)
                    accessibilityevent = (View)accessibilityevent;
                else
                    accessibilityevent = null;
            }

        if(!flag)
            return;
        flag = AccessibilityNodeInfo.getVirtualDescendantId(mAccessibilityFocusedVirtualView.getSourceNodeId());
        accessibilityevent = mTempRect;
        mAccessibilityFocusedVirtualView.getBoundsInScreen(accessibilityevent);
        mAccessibilityFocusedVirtualView = accessibilitynodeprovider.createAccessibilityNodeInfo(flag);
        if(mAccessibilityFocusedVirtualView != null) goto _L2; else goto _L1
_L1:
        mAccessibilityFocusedHost = null;
        view.clearAccessibilityFocusNoCallbacks(0);
        accessibilitynodeprovider.performAction(flag, android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_CLEAR_ACCESSIBILITY_FOCUS.getId(), null);
        invalidateRectOnScreen(accessibilityevent);
_L4:
        return;
_L2:
        Rect rect = mAccessibilityFocusedVirtualView.getBoundsInScreen();
        if(!accessibilityevent.equals(rect))
        {
            accessibilityevent.union(rect);
            invalidateRectOnScreen(accessibilityevent);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void invalidateRectOnScreen(Rect rect)
    {
        Rect rect1 = mDirty;
        if(!rect1.isEmpty() && rect1.contains(rect) ^ true)
        {
            mAttachInfo.mSetIgnoreDirtyState = true;
            mAttachInfo.mIgnoreDirtyState = true;
        }
        rect1.union(rect.left, rect.top, rect.right, rect.bottom);
        float f = mAttachInfo.mApplicationScale;
        boolean flag = rect1.intersect(0, 0, (int)((float)mWidth * f + 0.5F), (int)((float)mHeight * f + 0.5F));
        if(!flag)
            rect1.setEmpty();
        if(!mWillDrawSoon && (flag || mIsAnimating))
            scheduleTraversals();
    }

    public static void invokeFunctor(long l, boolean flag)
    {
        ThreadedRenderer.invokeFunctor(l, flag);
    }

    private boolean isInLocalFocusMode()
    {
        boolean flag = false;
        if((mWindowAttributes.flags & 0x10000000) != 0)
            flag = true;
        return flag;
    }

    static boolean isInTouchMode()
    {
        IWindowSession iwindowsession;
        iwindowsession = WindowManagerGlobal.peekWindowSession();
        if(iwindowsession == null)
            break MISSING_BLOCK_LABEL_18;
        boolean flag = iwindowsession.getInTouchMode();
        return flag;
        RemoteException remoteexception;
        remoteexception;
        return false;
    }

    private static boolean isNavigationKey(KeyEvent keyevent)
    {
        switch(keyevent.getKeyCode())
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

    static boolean isTerminalInputEvent(InputEvent inputevent)
    {
        boolean flag;
        boolean flag1;
        int i;
        flag = true;
        flag1 = true;
        if(inputevent instanceof KeyEvent)
        {
            if(((KeyEvent)inputevent).getAction() != 1)
                flag1 = false;
            return flag1;
        }
        i = ((MotionEvent)inputevent).getAction();
        flag1 = flag;
        if(i == 1) goto _L2; else goto _L1
_L1:
        if(i != 3) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(i != 10)
            flag1 = false;
        if(true) goto _L2; else goto _L5
_L5:
    }

    private static boolean isTypingKey(KeyEvent keyevent)
    {
        boolean flag = false;
        if(keyevent.getUnicodeChar() > 0)
            flag = true;
        return flag;
    }

    public static boolean isViewDescendantOf(View view, View view1)
    {
        if(view == view1)
            return true;
        view = view.getParent();
        boolean flag;
        if(view instanceof ViewGroup)
            flag = isViewDescendantOf((View)view, view1);
        else
            flag = false;
        return flag;
    }

    private boolean leaveTouchMode()
    {
        if(mView != null)
        {
            if(mView.hasFocus())
            {
                View view = mView.findFocus();
                if(!(view instanceof ViewGroup))
                    return false;
                if(((ViewGroup)view).getDescendantFocusability() != 0x40000)
                    return false;
            }
            View view1 = focusSearch(null, 130);
            if(view1 != null)
                return view1.requestFocus(130);
        }
        return false;
    }

    private void maybeHandleWindowMove(Rect rect)
    {
        boolean flag;
        if(mAttachInfo.mWindowLeft == rect.left)
        {
            if(mAttachInfo.mWindowTop != rect.top)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = true;
        }
        if(flag)
        {
            if(mTranslator != null)
                mTranslator.translateRectInScreenToAppWinFrame(rect);
            mAttachInfo.mWindowLeft = rect.left;
            mAttachInfo.mWindowTop = rect.top;
        }
        if(flag || mAttachInfo.mNeedsUpdateLightCenter)
        {
            if(mAttachInfo.mThreadedRenderer != null)
                mAttachInfo.mThreadedRenderer.setLightCenter(mAttachInfo);
            mAttachInfo.mNeedsUpdateLightCenter = false;
        }
    }

    private void maybeUpdateTooltip(MotionEvent motionevent)
    {
        if(motionevent.getPointerCount() != 1)
            return;
        int i = motionevent.getActionMasked();
        if(i != 9 && i != 7 && i != 10)
            return;
        AccessibilityManager accessibilitymanager = AccessibilityManager.getInstance(mContext);
        if(accessibilitymanager.isEnabled() && accessibilitymanager.isTouchExplorationEnabled())
            return;
        if(mView == null)
        {
            Slog.d(mTag, "maybeUpdateTooltip called after view was removed");
            return;
        } else
        {
            mView.dispatchTooltipHoverEvent(motionevent);
            return;
        }
    }

    private boolean measureHierarchy(View view, WindowManager.LayoutParams layoutparams, Resources resources, int i, int j)
    {
        boolean flag;
        boolean flag1;
        int k;
        flag = false;
        flag1 = false;
        k = ((flag1) ? 1 : 0);
        if(layoutparams.width != -2) goto _L2; else goto _L1
_L1:
        int l;
        DisplayMetrics displaymetrics = resources.getDisplayMetrics();
        resources.getValue(0x1050041, mTmpValue, true);
        l = 0;
        if(mTmpValue.type == 5)
            l = (int)mTmpValue.getDimension(displaymetrics);
        k = ((flag1) ? 1 : 0);
        if(l == 0) goto _L2; else goto _L3
_L3:
        k = ((flag1) ? 1 : 0);
        if(i <= l) goto _L2; else goto _L4
_L4:
        int i1;
        k = getRootMeasureSpec(l, layoutparams.width);
        i1 = getRootMeasureSpec(j, layoutparams.height);
        performMeasure(k, i1);
        if((view.getMeasuredWidthAndState() & 0x1000000) != 0) goto _L6; else goto _L5
_L5:
        k = 1;
_L2:
        boolean flag2;
label0:
        {
            flag2 = flag;
            if(k != 0)
                break label0;
            performMeasure(getRootMeasureSpec(i, layoutparams.width), getRootMeasureSpec(j, layoutparams.height));
            if(mWidth == view.getMeasuredWidth())
            {
                flag2 = flag;
                if(mHeight == view.getMeasuredHeight())
                    break label0;
            }
            flag2 = true;
        }
        return flag2;
_L6:
        performMeasure(getRootMeasureSpec((l + i) / 2, layoutparams.width), i1);
        k = ((flag1) ? 1 : 0);
        if((view.getMeasuredWidthAndState() & 0x1000000) == 0)
            k = 1;
        if(true) goto _L2; else goto _L7
_L7:
    }

    private QueuedInputEvent obtainQueuedInputEvent(InputEvent inputevent, InputEventReceiver inputeventreceiver, int i)
    {
        QueuedInputEvent queuedinputevent = mQueuedInputEventPool;
        if(queuedinputevent != null)
        {
            mQueuedInputEventPoolSize = mQueuedInputEventPoolSize - 1;
            mQueuedInputEventPool = queuedinputevent.mNext;
            queuedinputevent.mNext = null;
        } else
        {
            queuedinputevent = new QueuedInputEvent(null);
        }
        queuedinputevent.mEvent = inputevent;
        queuedinputevent.mReceiver = inputeventreceiver;
        queuedinputevent.mFlags = i;
        return queuedinputevent;
    }

    private void performConfigurationChange(MergedConfiguration mergedconfiguration, boolean flag, int i)
    {
        Configuration configuration1;
        if(mergedconfiguration == null)
            throw new IllegalArgumentException("No merged config provided.");
        Configuration configuration = mergedconfiguration.getGlobalConfiguration();
        configuration1 = mergedconfiguration.getOverrideConfiguration();
        CompatibilityInfo compatibilityinfo = mDisplay.getDisplayAdjustments().getCompatibilityInfo();
        mergedconfiguration = configuration;
        if(!compatibilityinfo.equals(CompatibilityInfo.DEFAULT_COMPATIBILITY_INFO))
        {
            mergedconfiguration = new Configuration(configuration);
            compatibilityinfo.applyToConfiguration(mNoncompatDensity, mergedconfiguration);
        }
        ArrayList arraylist = sConfigCallbacks;
        arraylist;
        JVM INSTR monitorenter ;
        int j = sConfigCallbacks.size() - 1;
_L2:
        if(j < 0)
            break; /* Loop/switch isn't completed */
        ((ConfigChangedCallback)sConfigCallbacks.get(j)).onConfigurationChanged(mergedconfiguration);
        j--;
        if(true) goto _L2; else goto _L1
_L1:
        mLastReportedMergedConfiguration.setConfiguration(mergedconfiguration, configuration1);
        mForceNextConfigUpdate = flag;
        if(mActivityConfigCallback != null)
            mActivityConfigCallback.onConfigurationChanged(configuration1, i);
        else
            updateConfiguration(i);
        mForceNextConfigUpdate = false;
        return;
        mergedconfiguration;
        throw mergedconfiguration;
    }

    private void performDraw()
    {
        boolean flag;
        if(mAttachInfo.mDisplayState == 1 && mReportNextDraw ^ true)
            return;
        if(mView == null)
            return;
        flag = mFullRedrawNeeded;
        mFullRedrawNeeded = false;
        mIsDrawing = true;
        Trace.traceBegin(8L, "draw");
        draw(flag);
        mIsDrawing = false;
        Trace.traceEnd(8L);
        if(mAttachInfo.mPendingAnimatingRenderNodes == null)
            break MISSING_BLOCK_LABEL_152;
        int i = mAttachInfo.mPendingAnimatingRenderNodes.size();
        for(int j = 0; j < i; j++)
            ((RenderNode)mAttachInfo.mPendingAnimatingRenderNodes.get(j)).endAllAnimators();

        break MISSING_BLOCK_LABEL_140;
        Exception exception;
        exception;
        mIsDrawing = false;
        Trace.traceEnd(8L);
        throw exception;
        mAttachInfo.mPendingAnimatingRenderNodes.clear();
        if(mReportNextDraw)
        {
            mReportNextDraw = false;
            if(mWindowDrawCountDown != null)
            {
                SurfaceHolder.Callback acallback[];
                SurfaceCallbackHelper surfacecallbackhelper;
                try
                {
                    mWindowDrawCountDown.await();
                }
                catch(InterruptedException interruptedexception)
                {
                    Log.e(mTag, "Window redraw count down interruped!");
                }
                mWindowDrawCountDown = null;
            }
            if(mAttachInfo.mThreadedRenderer != null)
            {
                mAttachInfo.mThreadedRenderer.fence();
                mAttachInfo.mThreadedRenderer.setStopped(mStopped);
            }
            if(mSurfaceHolder != null && mSurface.isValid())
            {
                surfacecallbackhelper = new SurfaceCallbackHelper(new _.Lambda.XmA8Y30pNAdQP9ujRlGx1qfDHH8((byte)4, this));
                acallback = mSurfaceHolder.getCallbacks();
                surfacecallbackhelper.dispatchSurfaceRedrawNeededAsync(mSurfaceHolder, acallback);
            } else
            {
                pendingDrawFinished();
            }
        }
        return;
    }

    private void performLayout(WindowManager.LayoutParams layoutparams, int i, int j)
    {
        View view;
        mLayoutRequested = false;
        mScrollMayChange = true;
        mInLayout = true;
        view = mView;
        if(view == null)
            return;
        Trace.traceBegin(8L, "layout");
        ArrayList arraylist;
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        mInLayout = false;
        if(mLayoutRequesters.size() <= 0)
            break MISSING_BLOCK_LABEL_253;
        arraylist = getValidLayoutRequesters(mLayoutRequesters, false);
        if(arraylist == null)
            break MISSING_BLOCK_LABEL_253;
        int k;
        mHandlingLayoutInLayoutRequest = true;
        k = arraylist.size();
        int l = 0;
_L2:
        if(l >= k)
            break; /* Loop/switch isn't completed */
        View view1 = (View)arraylist.get(l);
        StringBuilder stringbuilder = JVM INSTR new #1416 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.w("View", stringbuilder.append("requestLayout() improperly called by ").append(view1).append(" during layout: running second layout pass").toString());
        view1.requestLayout();
        l++;
        if(true) goto _L2; else goto _L1
_L1:
        measureHierarchy(view, layoutparams, mView.getContext().getResources(), i, j);
        mInLayout = true;
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        mHandlingLayoutInLayoutRequest = false;
        layoutparams = getValidLayoutRequesters(mLayoutRequesters, true);
        if(layoutparams == null)
            break MISSING_BLOCK_LABEL_253;
        HandlerActionQueue handleractionqueue = getRunQueue();
        Runnable runnable = JVM INSTR new #14  <Class ViewRootImpl$2>;
        runnable.this. _cls2();
        handleractionqueue.post(runnable);
        Trace.traceEnd(8L);
        mInLayout = false;
        return;
        layoutparams;
        Trace.traceEnd(8L);
        throw layoutparams;
    }

    private void performMeasure(int i, int j)
    {
        if(mView == null)
            return;
        Trace.traceBegin(8L, "measure");
        mView.measure(i, j);
        Trace.traceEnd(8L);
        return;
        Exception exception;
        exception;
        Trace.traceEnd(8L);
        throw exception;
    }

    private void performTraversals()
    {
        View view;
        int i;
        int j;
        int k;
        int l;
        boolean flag;
        Object obj;
        int i1;
        boolean flag1;
        int j1;
        int k1;
        boolean flag2;
        Object obj1;
        Rect rect;
        Object obj3;
        int i2;
        int j2;
        boolean flag6;
        int l2;
        int i3;
        view = mView;
        if(view == null || mAdded ^ true)
            return;
        mIsInTraversal = true;
        mWillDrawSoon = true;
        i = 0;
        j = 0;
        k = 0;
        l = 0;
        flag = false;
        obj = mWindowAttributes;
        i1 = getHostVisibility();
        if(!mFirst)
        {
            if(mViewVisibility == i1 && !mNewSurfaceNeeded)
                flag1 = mAppVisibilityChanged;
            else
                flag1 = true;
        } else
        {
            flag1 = false;
        }
        mAppVisibilityChanged = false;
        if(!mFirst)
        {
            Point point;
            boolean flag5;
            if(mViewVisibility == 0)
                j1 = 1;
            else
                j1 = 0;
            if(i1 == 0)
                k1 = 1;
            else
                k1 = 0;
            if(j1 != k1)
                flag2 = true;
            else
                flag2 = false;
        } else
        {
            flag2 = false;
        }
        obj1 = null;
        if(mWindowAttributesChanged)
        {
            mWindowAttributesChanged = false;
            flag = true;
            obj1 = obj;
        }
        if(mDisplay.getDisplayAdjustments().getCompatibilityInfo().supportsScreen() == mLastInCompatMode)
        {
            obj1 = obj;
            mFullRedrawNeeded = true;
            mLayoutRequested = true;
            if(mLastInCompatMode)
            {
                obj.privateFlags = ((WindowManager.LayoutParams) (obj)).privateFlags & 0xffffff7f;
                mLastInCompatMode = false;
            } else
            {
                obj.privateFlags = ((WindowManager.LayoutParams) (obj)).privateFlags | 0x80;
                mLastInCompatMode = true;
            }
        }
        mWindowAttributesChangesFlag = 0;
        rect = mWinFrame;
        if(!mFirst) goto _L2; else goto _L1
_L1:
        mFullRedrawNeeded = true;
        mLayoutRequested = true;
        obj3 = mContext.getResources().getConfiguration();
        if(shouldUseDisplaySize(((WindowManager.LayoutParams) (obj))))
        {
            point = new Point();
            mDisplay.getRealSize(point);
            k1 = point.x;
            j1 = point.y;
        } else
        {
            k1 = dipToPx(((Configuration) (obj3)).screenWidthDp);
            j1 = dipToPx(((Configuration) (obj3)).screenHeightDp);
        }
        mAttachInfo.mUse32BitDrawingCache = true;
        mAttachInfo.mHasWindowFocus = false;
        mAttachInfo.mWindowVisibility = i1;
        mAttachInfo.mRecomputeGlobalAttributes = false;
        mLastConfigurationFromResources.setTo(((Configuration) (obj3)));
        mLastSystemUiVisibility = mAttachInfo.mSystemUiVisibility;
        if(mViewLayoutDirectionInitial == 2)
            view.setLayoutDirection(((Configuration) (obj3)).getLayoutDirection());
        view.dispatchAttachedToWindow(mAttachInfo, 0);
        mAttachInfo.mTreeObserver.dispatchOnWindowAttachedChange(true);
        dispatchApplyInsets(view);
_L9:
        if(flag1)
        {
            mAttachInfo.mWindowVisibility = i1;
            view.dispatchWindowVisibilityChanged(i1);
            if(flag2)
            {
                boolean flag3;
                if(i1 == 0)
                    flag3 = true;
                else
                    flag3 = false;
                view.dispatchVisibilityAggregated(flag3);
            }
            if(i1 != 0 || mNewSurfaceNeeded)
            {
                endDragResizing();
                destroyHardwareResources();
            }
            if(i1 == 8)
                mHasHadWindowFocus = false;
        }
        if(mAttachInfo.mWindowVisibility != 0)
            view.clearAccessibilityFocus();
        getRunQueue().executeActions(mAttachInfo.mHandler);
        i2 = 0;
        j2 = 0;
        flag5 = false;
        if(mLayoutRequested)
        {
            if(mStopped)
                flag6 = mReportNextDraw;
            else
                flag6 = true;
        } else
        {
            flag6 = false;
        }
        l2 = k1;
        i3 = j1;
        flag2 = i;
        if(!flag6) goto _L4; else goto _L3
_L3:
        obj3 = mView.getContext().getResources();
        if(!mFirst) goto _L6; else goto _L5
_L5:
        mAttachInfo.mInTouchMode = mAddedTouchMode ^ true;
        ensureTouchModeLocally(mAddedTouchMode);
        i2 = ((flag5) ? 1 : 0);
_L12:
        flag2 = i | measureHierarchy(view, ((WindowManager.LayoutParams) (obj)), ((Resources) (obj3)), k1, j1);
        i3 = j1;
        l2 = k1;
_L4:
        if(collectViewAttributes())
            obj1 = obj;
        if(mAttachInfo.mForceReportNewAttributes)
        {
            mAttachInfo.mForceReportNewAttributes = false;
            obj1 = obj;
        }
        if(!mFirst)
        {
            obj3 = obj1;
            if(!mAttachInfo.mViewVisibilityChanged)
                break MISSING_BLOCK_LABEL_1183;
        }
        mAttachInfo.mViewVisibilityChanged = false;
        j1 = mSoftInputMode & 0xf0;
        obj3 = obj1;
        if(j1 != 0)
            break MISSING_BLOCK_LABEL_1183;
        i = mAttachInfo.mScrollContainers.size();
        for(k1 = 0; k1 < i; k1++)
            if(((View)mAttachInfo.mScrollContainers.get(k1)).isShown())
                j1 = 16;

        break MISSING_BLOCK_LABEL_1131;
_L2:
        i3 = rect.width();
        i2 = rect.height();
        if(i3 != mWidth) goto _L8; else goto _L7
_L7:
        k1 = i3;
        j1 = i2;
        if(i2 == mHeight) goto _L9; else goto _L8
_L8:
        mFullRedrawNeeded = true;
        mLayoutRequested = true;
        i = 1;
        k1 = i3;
        j1 = i2;
          goto _L9
_L6:
        flag2 = j2;
        if(!mPendingOverscanInsets.equals(mAttachInfo.mOverscanInsets))
            flag2 = true;
        if(!mPendingContentInsets.equals(mAttachInfo.mContentInsets))
            flag2 = true;
        if(!mPendingStableInsets.equals(mAttachInfo.mStableInsets))
            flag2 = true;
        if(!mPendingVisibleInsets.equals(mAttachInfo.mVisibleInsets))
            mAttachInfo.mVisibleInsets.set(mPendingVisibleInsets);
        if(!mPendingOutsets.equals(mAttachInfo.mOutsets))
            flag2 = true;
        if(mPendingAlwaysConsumeNavBar != mAttachInfo.mAlwaysConsumeNavBar)
            flag2 = true;
        if(((WindowManager.LayoutParams) (obj)).width == -2) goto _L11; else goto _L10
_L10:
        i2 = ((flag2) ? 1 : 0);
        if(((WindowManager.LayoutParams) (obj)).height != -2) goto _L12; else goto _L11
_L11:
        i = 1;
        if(shouldUseDisplaySize(((WindowManager.LayoutParams) (obj))))
        {
            Point point1 = new Point();
            mDisplay.getRealSize(point1);
            k1 = point1.x;
            j1 = point1.y;
            i2 = ((flag2) ? 1 : 0);
        } else
        {
            Configuration configuration = ((Resources) (obj3)).getConfiguration();
            k1 = dipToPx(configuration.screenWidthDp);
            j1 = dipToPx(configuration.screenHeightDp);
            i2 = ((flag2) ? 1 : 0);
        }
          goto _L12
        int l1;
        boolean flag4;
        boolean flag7;
        boolean flag8;
        boolean flag9;
        int j3;
        boolean flag10;
        boolean flag11;
        k1 = j1;
        if(j1 == 0)
            k1 = 32;
        obj3 = obj1;
        if((((WindowManager.LayoutParams) (obj)).softInputMode & 0xf0) != k1)
        {
            obj.softInputMode = ((WindowManager.LayoutParams) (obj)).softInputMode & 0xffffff0f | k1;
            obj3 = obj;
        }
        break MISSING_BLOCK_LABEL_1183;
        int k2;
        boolean flag13;
        boolean flag14;
        boolean flag15;
        boolean flag18;
        mForceNextWindowRelayout = false;
        ThreadedRenderer threadedrenderer;
        SurfaceHolder.Callback acallback[];
        boolean flag12;
        boolean flag16;
        boolean flag17;
        if(flag10)
            if(flag8)
            {
                if(!mFirst)
                    flag4 = flag1;
                else
                    flag4 = true;
            } else
            {
                flag4 = false;
            }
        if(mSurfaceHolder != null)
        {
            mSurfaceHolder.mSurfaceLock.lock();
            mDrawingAllowed = true;
        }
        flag12 = false;
        flag13 = false;
        flag14 = false;
        i2 = 0;
        flag15 = mSurface.isValid();
        j1 = i2;
        flag9 = flag13;
        l1 = k;
        j = l2;
        k2 = i;
        if(mAttachInfo.mThreadedRenderer == null)
            break MISSING_BLOCK_LABEL_1673;
        j1 = i2;
        flag9 = flag13;
        l1 = k;
        j = l2;
        k2 = i;
        if(!mAttachInfo.mThreadedRenderer.pauseSurface(mSurface))
            break MISSING_BLOCK_LABEL_1644;
        j1 = i2;
        flag9 = flag13;
        l1 = k;
        j = l2;
        k2 = i;
        mDirty.set(0, 0, mWidth, mHeight);
        j1 = i2;
        flag9 = flag13;
        l1 = k;
        j = l2;
        k2 = i;
        mChoreographer.mFrameInfo.addFlags(1L);
        j1 = i2;
        flag9 = flag13;
        l1 = k;
        j = l2;
        k2 = i;
        j2 = relayoutWindow(((WindowManager.LayoutParams) (obj3)), i1, flag4);
        k1 = i3;
        j1 = i2;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = i;
        if(mPendingMergedConfiguration.equals(mLastReportedMergedConfiguration))
            break MISSING_BLOCK_LABEL_1775;
        j1 = i2;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = i;
        performConfigurationChange(mPendingMergedConfiguration, mFirst ^ true, -1);
        k1 = 1;
        j1 = i2;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = k1;
        flag16 = mPendingOverscanInsets.equals(mAttachInfo.mOverscanInsets);
        j1 = i2;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = k1;
        i3 = mPendingContentInsets.equals(mAttachInfo.mContentInsets) ^ true;
        j1 = i3;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = k1;
        flag7 = mPendingVisibleInsets.equals(mAttachInfo.mVisibleInsets);
        j1 = i3;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = k1;
        flag17 = mPendingStableInsets.equals(mAttachInfo.mStableInsets);
        j1 = i3;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = k1;
        flag18 = mPendingOutsets.equals(mAttachInfo.mOutsets);
        if((j2 & 0x20) != 0)
            i2 = 1;
        else
            i2 = 0;
        j1 = i3;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = k1;
        if(mPendingAlwaysConsumeNavBar != mAttachInfo.mAlwaysConsumeNavBar)
            l2 = 1;
        else
            l2 = 0;
        if(i3 == 0)
            break MISSING_BLOCK_LABEL_2037;
        j1 = i3;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = k1;
        mAttachInfo.mContentInsets.set(mPendingContentInsets);
        i = i3;
        if(!(flag16 ^ true))
            break MISSING_BLOCK_LABEL_2082;
        j1 = i3;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = k1;
        mAttachInfo.mOverscanInsets.set(mPendingOverscanInsets);
        i = 1;
        j1 = i;
        if(!(flag17 ^ true))
            break MISSING_BLOCK_LABEL_2127;
        j1 = i;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = k1;
        mAttachInfo.mStableInsets.set(mPendingStableInsets);
        j1 = 1;
        i = j1;
        if(l2 == 0)
            break MISSING_BLOCK_LABEL_2163;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = k1;
        mAttachInfo.mAlwaysConsumeNavBar = mPendingAlwaysConsumeNavBar;
        i = 1;
        if(i != 0) goto _L14; else goto _L13
_L13:
        j1 = i;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = k1;
        if(mLastSystemUiVisibility == mAttachInfo.mSystemUiVisibility) goto _L15; else goto _L14
_L14:
        j1 = i;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = k1;
        mLastSystemUiVisibility = mAttachInfo.mSystemUiVisibility;
        j1 = i;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = k1;
        mLastOverscanRequested = mAttachInfo.mOverscanRequested;
        j1 = i;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = k1;
        mAttachInfo.mOutsets.set(mPendingOutsets);
        j1 = i;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = k1;
        mApplyInsetsRequested = false;
        j1 = i;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = k1;
        dispatchApplyInsets(view);
_L28:
        if(!(flag7 ^ true))
            break MISSING_BLOCK_LABEL_2374;
        j1 = i;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = k1;
        mAttachInfo.mVisibleInsets.set(mPendingVisibleInsets);
        if(flag15) goto _L17; else goto _L16
_L16:
        flag7 = flag14;
        i2 = l;
        j1 = i;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = k1;
        if(!mSurface.isValid())
            break MISSING_BLOCK_LABEL_2638;
        l2 = 1;
        i3 = 1;
        j1 = i;
        flag9 = flag13;
        l1 = l2;
        j = j2;
        k2 = k1;
        mFullRedrawNeeded = true;
        j1 = i;
        flag9 = flag13;
        l1 = l2;
        j = j2;
        k2 = k1;
        mPreviousTransparentRegion.setEmpty();
        j1 = i;
        flag9 = flag13;
        l1 = l2;
        j = j2;
        k2 = k1;
        threadedrenderer = mAttachInfo.mThreadedRenderer;
        flag7 = flag14;
        i2 = i3;
        if(threadedrenderer == null)
            break MISSING_BLOCK_LABEL_2638;
        flag14 = flag12;
        j1 = i;
        flag9 = flag13;
        l1 = l2;
        j = j2;
        k2 = k1;
        flag13 = mAttachInfo.mThreadedRenderer.initialize(mSurface);
        flag7 = flag13;
        i2 = i3;
        if(!flag13)
            break MISSING_BLOCK_LABEL_2638;
        flag7 = flag13;
        i2 = i3;
        flag14 = flag13;
        j1 = i;
        flag9 = flag13;
        l1 = l2;
        j = j2;
        k2 = k1;
        if((view.mPrivateFlags & 0x200) != 0)
            break MISSING_BLOCK_LABEL_2638;
        flag14 = flag13;
        j1 = i;
        flag9 = flag13;
        l1 = l2;
        j = j2;
        k2 = k1;
        mSurface.allocateBuffers();
        i2 = i3;
        flag7 = flag13;
_L32:
        boolean flag19;
        if((j2 & 0x10) != 0)
            flag19 = true;
        else
            flag19 = false;
        if((j2 & 8) != 0)
            flag9 = true;
        else
            flag9 = false;
        if(!flag19)
            flag13 = flag9;
        else
            flag13 = true;
        l = i;
        flag14 = flag7;
        l2 = i2;
        i3 = j2;
        k = k1;
        j1 = i;
        flag9 = flag7;
        l1 = i2;
        j = j2;
        k2 = k1;
        if(mDragResizing == flag13) goto _L19; else goto _L18
_L18:
        if(!flag13) goto _L21; else goto _L20
_L20:
        if(flag19)
            i3 = 0;
        else
            i3 = 1;
        j1 = i;
        flag9 = flag7;
        l1 = i2;
        j = j2;
        k2 = k1;
        mResizeMode = i3;
        j1 = i;
        flag9 = flag7;
        l1 = i2;
        j = j2;
        k2 = k1;
        startDragResizing(mPendingBackDropFrame, mWinFrame.equals(mPendingBackDropFrame), mPendingVisibleInsets, mPendingStableInsets, mResizeMode);
        k = k1;
        i3 = j2;
        l2 = i2;
        flag14 = flag7;
        l = i;
_L19:
        mAttachInfo.mWindowLeft = rect.left;
        mAttachInfo.mWindowTop = rect.top;
        if(mWidth != rect.width() || mHeight != rect.height())
        {
            mWidth = rect.width();
            mHeight = rect.height();
        }
        if(mSurfaceHolder == null) goto _L23; else goto _L22
_L22:
        if(mSurface.isValid())
            mSurfaceHolder.mSurface = mSurface;
        mSurfaceHolder.setSurfaceFrameSize(mWidth, mHeight);
        mSurfaceHolder.mSurfaceLock.unlock();
        if(!mSurface.isValid()) goto _L25; else goto _L24
        if(obj3 != null)
        {
            if((view.mPrivateFlags & 0x200) != 0 && !PixelFormat.formatHasAlpha(((WindowManager.LayoutParams) (obj3)).format))
                obj3.format = -3;
            View.AttachInfo attachinfo = mAttachInfo;
            if((((WindowManager.LayoutParams) (obj3)).flags & 0x2000000) != 0)
                flag4 = true;
            else
                flag4 = false;
            attachinfo.mOverscanRequested = flag4;
        }
        j1 = ((flag2) ? 1 : 0);
        if(mApplyInsetsRequested)
        {
            mApplyInsetsRequested = false;
            mLastOverscanRequested = mAttachInfo.mOverscanRequested;
            dispatchApplyInsets(view);
            j1 = ((flag2) ? 1 : 0);
            if(mLayoutRequested)
                j1 = flag2 | measureHierarchy(view, ((WindowManager.LayoutParams) (obj)), mView.getContext().getResources(), l2, i3);
        }
        if(flag6)
            mLayoutRequested = false;
        if(flag6 && j1)
        {
            if(mWidth != view.getMeasuredWidth() || mHeight != view.getMeasuredHeight() || ((WindowManager.LayoutParams) (obj)).width == -2 && rect.width() < l2 && rect.width() != mWidth)
                j1 = 1;
            else
            if(((WindowManager.LayoutParams) (obj)).height == -2 && rect.height() < i3)
            {
                if(rect.height() != mHeight)
                    j1 = 1;
                else
                    j1 = 0;
            } else
            {
                j1 = 0;
            }
        } else
        {
            j1 = 0;
        }
        if(mDragResizing && mResizeMode == 0)
            k1 = 1;
        else
            k1 = 0;
        flag7 = mActivityRelaunched;
        if(!mAttachInfo.mTreeObserver.hasComputeInternalInsetsListeners())
            flag8 = mAttachInfo.mHasNonEmptyGivenInternalInsets;
        else
            flag8 = true;
        flag9 = false;
        flag4 = false;
        l2 = 0;
        l1 = 0;
        i = 0;
        i3 = 0;
        j3 = mSurface.getGenerationId();
        if(i1 == 0)
            flag10 = true;
        else
            flag10 = false;
        flag11 = mForceNextWindowRelayout;
        if(mFirst || j1 | k1 | flag7 || i2 != 0 || flag1 || obj3 != null || mForceNextWindowRelayout)
            break MISSING_BLOCK_LABEL_1477;
        maybeHandleWindowMove(rect);
        i = l1;
        k1 = j;
        flag7 = flag6;
_L39:
        if(flag7)
        {
            if(mStopped)
                flag4 = mReportNextDraw;
            else
                flag4 = true;
        } else
        {
            flag4 = false;
        }
        if(!flag4)
            flag6 = mAttachInfo.mRecomputeGlobalAttributes;
        else
            flag6 = true;
        if(flag4)
        {
            performLayout(((WindowManager.LayoutParams) (obj)), mWidth, mHeight);
            if((view.mPrivateFlags & 0x200) != 0)
            {
                view.getLocationInWindow(mTmpLocation);
                mTransparentRegion.set(mTmpLocation[0], mTmpLocation[1], (mTmpLocation[0] + view.mRight) - view.mLeft, (mTmpLocation[1] + view.mBottom) - view.mTop);
                view.gatherTransparentRegion(mTransparentRegion);
                if(mTranslator != null)
                    mTranslator.translateRegionInWindowToScreen(mTransparentRegion);
                if(!mTransparentRegion.equals(mPreviousTransparentRegion))
                {
                    mPreviousTransparentRegion.set(mTransparentRegion);
                    mFullRedrawNeeded = true;
                    RemoteException remoteexception;
                    Object obj2;
                    ViewTreeObserver.InternalInsetsInfo internalinsetsinfo;
                    Object obj4;
                    try
                    {
                        mWindowSession.setTransparentRegion(mWindow, mTransparentRegion);
                    }
                    catch(RemoteException remoteexception1) { }
                }
            }
        }
        if(flag6)
        {
            mAttachInfo.mRecomputeGlobalAttributes = false;
            mAttachInfo.mTreeObserver.dispatchOnGlobalLayout();
        }
        if(flag8)
        {
            internalinsetsinfo = mAttachInfo.mGivenInternalInsets;
            internalinsetsinfo.reset();
            mAttachInfo.mTreeObserver.dispatchOnComputeInternalInsets(internalinsetsinfo);
            mAttachInfo.mHasNonEmptyGivenInternalInsets = internalinsetsinfo.isEmpty() ^ true;
            if(flag9 || mLastGivenInsets.equals(internalinsetsinfo) ^ true)
            {
                mLastGivenInsets.set(internalinsetsinfo);
                if(mTranslator != null)
                {
                    obj4 = mTranslator.getTranslatedContentInsets(internalinsetsinfo.contentInsets);
                    obj2 = mTranslator.getTranslatedVisibleInsets(internalinsetsinfo.visibleInsets);
                    obj = mTranslator.getTranslatedTouchableArea(internalinsetsinfo.touchableRegion);
                } else
                {
                    obj4 = internalinsetsinfo.contentInsets;
                    obj2 = internalinsetsinfo.visibleInsets;
                    obj = internalinsetsinfo.touchableRegion;
                }
                try
                {
                    mWindowSession.setInsets(mWindow, internalinsetsinfo.mTouchableInsets, ((Rect) (obj4)), ((Rect) (obj2)), ((Region) (obj)));
                }
                // Misplaced declaration of an exception variable
                catch(RemoteException remoteexception) { }
            }
        }
        if(mFirst && sAlwaysAssignFocus && mView != null && !mView.hasFocus())
            mView.restoreDefaultFocus();
        if(flag1 || mFirst)
            flag4 = flag10;
        else
            flag4 = false;
        if(mAttachInfo.mHasWindowFocus)
            flag9 = flag10;
        else
            flag9 = false;
        if(flag9)
            flag1 = mLostWindowFocus;
        else
            flag1 = false;
        if(flag1)
            mLostWindowFocus = false;
        else
        if(!flag9 && mHadWindowFocus)
            mLostWindowFocus = true;
        if(flag4 || flag1)
        {
            if(mWindowAttributes == null)
                j1 = 0;
            else
            if(mWindowAttributes.type == 2005)
                j1 = 1;
            else
                j1 = 0;
            if(j1 == 0)
                view.sendAccessibilityEvent(32);
        }
        mFirst = false;
        mWillDrawSoon = false;
        mNewSurfaceNeeded = false;
        mActivityRelaunched = false;
        mViewVisibility = i1;
        mHadWindowFocus = flag9;
        if(flag9 && isInLocalFocusMode() ^ true)
        {
            flag4 = WindowManager.LayoutParams.mayUseInputMethod(mWindowAttributes.flags);
            if(flag4 != mLastWasImTarget)
            {
                mLastWasImTarget = flag4;
                obj = InputMethodManager.peekInstance();
                if(obj != null && flag4)
                {
                    ((InputMethodManager) (obj)).onPreWindowFocus(mView, flag9);
                    ((InputMethodManager) (obj)).onPostWindowFocus(mView, mView.findFocus(), mWindowAttributes.softInputMode, mHasHadWindowFocus ^ true, mWindowAttributes.flags);
                }
            }
        }
        if((i & 2) != 0)
            reportNextDraw();
        if(!mAttachInfo.mTreeObserver.dispatchOnPreDraw())
            j1 = flag10 ^ true;
        else
            j1 = 1;
        if(j1 != 0 || (k1 ^ 1) == 0) goto _L27; else goto _L26
_L15:
        j1 = i;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = k1;
        if(mApplyInsetsRequested)
            break; /* Loop/switch isn't completed */
        j1 = i;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = k1;
        if(mLastOverscanRequested == mAttachInfo.mOverscanRequested && !(flag18 ^ true)) goto _L28; else goto _L14
        obj2;
        break MISSING_BLOCK_LABEL_3958;
_L17:
        j1 = i;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = k1;
        if(mSurface.isValid()) goto _L30; else goto _L29
_L29:
        j1 = i;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = k1;
        if(mLastScrolledFocus == null)
            break MISSING_BLOCK_LABEL_4061;
        j1 = i;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = k1;
        mLastScrolledFocus.clear();
        j1 = i;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = k1;
        mCurScrollY = 0;
        j1 = i;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = k1;
        mScrollY = 0;
        j1 = i;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = k1;
        if(!(mView instanceof RootViewSurfaceTaker))
            break MISSING_BLOCK_LABEL_4169;
        j1 = i;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = k1;
        ((RootViewSurfaceTaker)mView).onRootViewScrollYChanged(mCurScrollY);
        j1 = i;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = k1;
        if(mScroller == null)
            break MISSING_BLOCK_LABEL_4219;
        j1 = i;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = k1;
        mScroller.abortAnimation();
        flag7 = flag14;
        i2 = l;
        j1 = i;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = k1;
        if(mAttachInfo.mThreadedRenderer == null) goto _L32; else goto _L31
_L31:
        flag7 = flag14;
        i2 = l;
        j1 = i;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = k1;
        if(!mAttachInfo.mThreadedRenderer.isEnabled()) goto _L32; else goto _L33
_L33:
        j1 = i;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = k1;
        mAttachInfo.mThreadedRenderer.destroy();
        flag7 = flag14;
        i2 = l;
          goto _L32
        j1 = i;
        flag9 = flag14;
        l1 = l2;
        j = j2;
        k2 = k1;
        try
        {
            handleOutOfResourcesException(((Surface.OutOfResourcesException) (obj2)));
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj2)
        {
            l = j1;
        }
        flag14 = flag9;
        l2 = l1;
        i3 = j;
        k = k2;
          goto _L19
_L30:
        j1 = i;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = k1;
        if(j3 != mSurface.getGenerationId() || i2 != 0) goto _L35; else goto _L34
_L34:
        flag7 = flag14;
        i2 = l;
        if(!flag11) goto _L32; else goto _L35
_L35:
        flag7 = flag14;
        i2 = l;
        j1 = i;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = k1;
        if(mSurfaceHolder != null) goto _L32; else goto _L36
_L36:
        flag7 = flag14;
        i2 = l;
        j1 = i;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = k1;
        if(mAttachInfo.mThreadedRenderer == null) goto _L32; else goto _L37
_L37:
        j1 = i;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = k1;
        mFullRedrawNeeded = true;
        j1 = i;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = k1;
        mAttachInfo.mThreadedRenderer.updateSurface(mSurface);
        flag7 = flag14;
        i2 = l;
          goto _L32
        obj2;
        j1 = i;
        flag9 = flag13;
        l1 = k;
        j = j2;
        k2 = k1;
        handleOutOfResourcesException(((Surface.OutOfResourcesException) (obj2)));
        return;
_L21:
        j1 = i;
        flag9 = flag7;
        l1 = i2;
        j = j2;
        k2 = k1;
        endDragResizing();
        l = i;
        flag14 = flag7;
        l2 = i2;
        i3 = j2;
        k = k1;
          goto _L19
_L24:
        if(!flag15)
        {
            mSurfaceHolder.ungetCallbacks();
            mIsCreating = true;
            acallback = mSurfaceHolder.getCallbacks();
            if(acallback != null)
            {
                j1 = 0;
                for(k1 = acallback.length; j1 < k1; j1++)
                    acallback[j1].surfaceCreated(mSurfaceHolder);

            }
            flag = true;
        }
        if(flag || j3 != mSurface.getGenerationId())
        {
            obj2 = mSurfaceHolder.getCallbacks();
            if(obj2 != null)
            {
                j1 = 0;
                for(k1 = obj2.length; j1 < k1; j1++)
                    obj2[j1].surfaceChanged(mSurfaceHolder, ((WindowManager.LayoutParams) (obj)).format, mWidth, mHeight);

            }
        }
        mIsCreating = false;
_L23:
        obj2 = mAttachInfo.mThreadedRenderer;
        if(obj2 != null && ((ThreadedRenderer) (obj2)).isEnabled() && (flag14 || mWidth != ((ThreadedRenderer) (obj2)).getWidth() || mHeight != ((ThreadedRenderer) (obj2)).getHeight() || mNeedsRendererSetup))
        {
            ((ThreadedRenderer) (obj2)).setup(mWidth, mHeight, mAttachInfo, mWindowAttributes.surfaceInsets);
            mNeedsRendererSetup = false;
        }
        if(!mStopped)
            break; /* Loop/switch isn't completed */
        flag9 = flag4;
        flag7 = flag6;
        k1 = l2;
        i = i3;
        if(!mReportNextDraw) goto _L39; else goto _L38
_L38:
        if((i3 & 1) != 0)
            flag9 = true;
        else
            flag9 = false;
        if(ensureTouchModeLocally(flag9) || mWidth != view.getMeasuredWidth() || mHeight != view.getMeasuredHeight() || l != 0)
            break MISSING_BLOCK_LABEL_4849;
        flag9 = flag4;
        flag7 = flag6;
        k1 = l2;
        i = i3;
          goto _L40
_L42:
        k1 = getRootMeasureSpec(mWidth, ((WindowManager.LayoutParams) (obj)).width);
        i = getRootMeasureSpec(mHeight, ((WindowManager.LayoutParams) (obj)).height);
        performMeasure(k1, i);
        i2 = view.getMeasuredWidth();
        l1 = view.getMeasuredHeight();
        j1 = 0;
        if(((WindowManager.LayoutParams) (obj)).horizontalWeight > 0.0F)
        {
            k1 = View.MeasureSpec.makeMeasureSpec(i2 + (int)((float)(mWidth - i2) * ((WindowManager.LayoutParams) (obj)).horizontalWeight), 0x40000000);
            j1 = 1;
        }
        if(((WindowManager.LayoutParams) (obj)).verticalWeight > 0.0F)
        {
            i = View.MeasureSpec.makeMeasureSpec(l1 + (int)((float)(mHeight - l1) * ((WindowManager.LayoutParams) (obj)).verticalWeight), 0x40000000);
            j1 = 1;
        }
        if(j1 != 0)
            performMeasure(k1, i);
        flag7 = true;
        flag9 = flag4;
        k1 = l2;
        i = i3;
        break; /* Loop/switch isn't completed */
_L25:
        if(!flag15) goto _L23; else goto _L41
_L41:
        mSurfaceHolder.ungetCallbacks();
        obj2 = mSurfaceHolder.getCallbacks();
        if(obj2 != null)
        {
            j1 = 0;
            for(k1 = obj2.length; j1 < k1; j1++)
                obj2[j1].surfaceDestroyed(mSurfaceHolder);

        }
        mSurfaceHolder.mSurfaceLock.lock();
        obj4 = mSurfaceHolder;
        obj2 = JVM INSTR new #634 <Class Surface>;
        ((Surface) (obj2)).Surface();
        obj4.mSurface = ((Surface) (obj2));
        mSurfaceHolder.mSurfaceLock.unlock();
          goto _L23
        obj;
        mSurfaceHolder.mSurfaceLock.unlock();
        throw obj;
_L40:
        if(k == 0) goto _L39; else goto _L42
_L26:
        if(mPendingTransitions != null && mPendingTransitions.size() > 0)
        {
            for(j1 = 0; j1 < mPendingTransitions.size(); j1++)
                ((LayoutTransition)mPendingTransitions.get(j1)).startChangingAnimations();

            mPendingTransitions.clear();
        }
        performDraw();
_L44:
        mIsInTraversal = false;
        return;
_L27:
        if(flag10)
            scheduleTraversals();
        else
        if(mPendingTransitions != null && mPendingTransitions.size() > 0)
        {
            for(j1 = 0; j1 < mPendingTransitions.size(); j1++)
                ((LayoutTransition)mPendingTransitions.get(j1)).endChangingAnimations();

            mPendingTransitions.clear();
        }
        if(true) goto _L44; else goto _L43
_L43:
          goto _L19
    }

    private void postDrawFinished()
    {
        mHandler.sendEmptyMessage(29);
    }

    private void postSendWindowContentChangedCallback(View view, int i)
    {
        if(mSendWindowContentChangedAccessibilityEvent == null)
            mSendWindowContentChangedAccessibilityEvent = new SendWindowContentChangedAccessibilityEvent(null);
        mSendWindowContentChangedAccessibilityEvent.runOrPost(view, i);
    }

    private void profileRendering(boolean flag)
    {
        if(mProfileRendering)
        {
            mRenderProfilingEnabled = flag;
            if(mRenderProfiler != null)
                mChoreographer.removeFrameCallback(mRenderProfiler);
            if(mRenderProfilingEnabled)
            {
                if(mRenderProfiler == null)
                    mRenderProfiler = new Choreographer.FrameCallback() {

                        public void doFrame(long l)
                        {
                            mDirty.set(0, 0, mWidth, mHeight);
                            scheduleTraversals();
                            if(ViewRootImpl._2D_get4(ViewRootImpl.this))
                                mChoreographer.postFrameCallback(ViewRootImpl._2D_get3(ViewRootImpl.this));
                        }

                        final ViewRootImpl this$0;

            
            {
                this$0 = ViewRootImpl.this;
                super();
            }
                    }
;
                mChoreographer.postFrameCallback(mRenderProfiler);
            } else
            {
                mRenderProfiler = null;
            }
        }
    }

    private void recycleQueuedInputEvent(QueuedInputEvent queuedinputevent)
    {
        queuedinputevent.mEvent = null;
        queuedinputevent.mReceiver = null;
        if(mQueuedInputEventPoolSize < 10)
        {
            mQueuedInputEventPoolSize = mQueuedInputEventPoolSize + 1;
            queuedinputevent.mNext = mQueuedInputEventPool;
            mQueuedInputEventPool = queuedinputevent;
        }
    }

    private int relayoutWindow(WindowManager.LayoutParams layoutparams, int i, boolean flag)
        throws RemoteException
    {
        float f = mAttachInfo.mApplicationScale;
        int j = 0;
        boolean flag1 = j;
        if(layoutparams != null)
        {
            flag1 = j;
            if(mTranslator != null)
            {
                flag1 = true;
                layoutparams.backup();
                mTranslator.translateWindowLayout(layoutparams);
            }
        }
        if(layoutparams != null && mOrigWindowType != layoutparams.type && mTargetSdkVersion < 14)
        {
            Slog.w(mTag, (new StringBuilder()).append("Window type can not be changed after the window is added; ignoring change of ").append(mView).toString());
            layoutparams.type = mOrigWindowType;
        }
        IWindowSession iwindowsession = mWindowSession;
        W w = mWindow;
        int k = mSeq;
        int l = (int)((float)mView.getMeasuredWidth() * f + 0.5F);
        int i1 = (int)((float)mView.getMeasuredHeight() * f + 0.5F);
        if(flag)
            j = 1;
        else
            j = 0;
        i = iwindowsession.relayout(w, k, layoutparams, l, i1, i, j, mWinFrame, mPendingOverscanInsets, mPendingContentInsets, mPendingVisibleInsets, mPendingStableInsets, mPendingOutsets, mPendingBackDropFrame, mPendingMergedConfiguration, mSurface);
        if((i & 0x40) != 0)
            flag = true;
        else
            flag = false;
        mPendingAlwaysConsumeNavBar = flag;
        if(flag1)
            layoutparams.restore();
        if(mTranslator != null)
        {
            mTranslator.translateRectInScreenToAppWinFrame(mWinFrame);
            mTranslator.translateRectInScreenToAppWindow(mPendingOverscanInsets);
            mTranslator.translateRectInScreenToAppWindow(mPendingContentInsets);
            mTranslator.translateRectInScreenToAppWindow(mPendingVisibleInsets);
            mTranslator.translateRectInScreenToAppWindow(mPendingStableInsets);
        }
        return i;
    }

    private void removeSendWindowContentChangedCallback()
    {
        if(mSendWindowContentChangedAccessibilityEvent != null)
            mHandler.removeCallbacks(mSendWindowContentChangedAccessibilityEvent);
    }

    private void reportDrawFinished()
    {
        mDrawsNeededToReport = 0;
        mWindowSession.finishDrawing(mWindow);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void reportNextDraw()
    {
        if(!mReportNextDraw)
            drawPending();
        mReportNextDraw = true;
    }

    private void requestDrawWindow()
    {
        if(mReportNextDraw)
            mWindowDrawCountDown = new CountDownLatch(mWindowCallbacks.size());
        for(int i = mWindowCallbacks.size() - 1; i >= 0; i--)
            ((WindowCallbacks)mWindowCallbacks.get(i)).onRequestDraw(mReportNextDraw);

    }

    private void resetPointerIcon(MotionEvent motionevent)
    {
        mPointerIconType = 1;
        updatePointerIcon(motionevent);
    }

    private void scheduleProcessInputEvents()
    {
        if(!mProcessInputEventsScheduled)
        {
            mProcessInputEventsScheduled = true;
            Message message = mHandler.obtainMessage(19);
            message.setAsynchronous(true);
            mHandler.sendMessage(message);
        }
    }

    private void setTag()
    {
        String as[] = mWindowAttributes.getTitle().toString().split("\\.");
        if(as.length > 0)
            mTag = (new StringBuilder()).append("ViewRootImpl[").append(as[as.length - 1]).append("]").toString();
    }

    private static boolean shouldUseDisplaySize(WindowManager.LayoutParams layoutparams)
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = flag;
        if(layoutparams.type == 2014) goto _L2; else goto _L1
_L1:
        if(layoutparams.type != 2011) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(layoutparams.type != 2020)
            flag1 = false;
        if(true) goto _L2; else goto _L5
_L5:
    }

    private void startDragResizing(Rect rect, boolean flag, Rect rect1, Rect rect2, int i)
    {
        if(!mDragResizing)
        {
            mDragResizing = true;
            for(int j = mWindowCallbacks.size() - 1; j >= 0; j--)
                ((WindowCallbacks)mWindowCallbacks.get(j)).onWindowDragResizeStart(rect, flag, rect1, rect2, i);

            mFullRedrawNeeded = true;
        }
    }

    private void trackFPS()
    {
        long l = System.currentTimeMillis();
        if(mFpsStartTime >= 0L) goto _L2; else goto _L1
_L1:
        mFpsPrevTime = l;
        mFpsStartTime = l;
        mFpsNumFrames = 0;
_L4:
        return;
_L2:
        mFpsNumFrames = mFpsNumFrames + 1;
        String s = Integer.toHexString(System.identityHashCode(this));
        long l1 = mFpsPrevTime;
        long l2 = l - mFpsStartTime;
        Log.v(mTag, (new StringBuilder()).append("0x").append(s).append("\tFrame time:\t").append(l - l1).toString());
        mFpsPrevTime = l;
        if(l2 > 1000L)
        {
            float f = ((float)mFpsNumFrames * 1000F) / (float)l2;
            Log.v(mTag, (new StringBuilder()).append("0x").append(s).append("\tFPS:\t").append(f).toString());
            mFpsStartTime = l;
            mFpsNumFrames = 0;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private boolean updateContentDrawBounds()
    {
        boolean flag = false;
        for(int i = mWindowCallbacks.size() - 1; i >= 0; i--)
            flag |= ((WindowCallbacks)mWindowCallbacks.get(i)).onContentDrawn(mWindowAttributes.surfaceInsets.left, mWindowAttributes.surfaceInsets.top, mWidth, mHeight);

        boolean flag1;
        if(mDragResizing)
            flag1 = mReportNextDraw;
        else
            flag1 = false;
        return flag1 | flag;
    }

    private boolean updatePointerIcon(MotionEvent motionevent)
    {
        float f = motionevent.getX(0);
        float f1 = motionevent.getY(0);
        if(mView == null)
        {
            Slog.d(mTag, "updatePointerIcon called after view was removed");
            return false;
        }
        while(f < 0.0F || f >= (float)mView.getWidth() || f1 < 0.0F || f1 >= (float)mView.getHeight()) 
        {
            Slog.d(mTag, "updatePointerIcon called with position out of bounds");
            return false;
        }
        motionevent = mView.onResolvePointerIcon(motionevent, 0);
        int i;
        if(motionevent != null)
            i = motionevent.getType();
        else
            i = 1000;
        if(mPointerIconType != i)
        {
            mPointerIconType = i;
            mCustomPointerIcon = null;
            if(mPointerIconType != -1)
            {
                InputManager.getInstance().setPointerIconType(i);
                return true;
            }
        }
        if(mPointerIconType == -1 && motionevent.equals(mCustomPointerIcon) ^ true)
        {
            mCustomPointerIcon = motionevent;
            InputManager.getInstance().setCustomPointerIcon(mCustomPointerIcon);
        }
        return true;
    }

    void _2D_android_view_ViewRootImpl_2D_mthref_2D_0()
    {
        postDrawFinished();
    }

    public void addWindowCallbacks(WindowCallbacks windowcallbacks)
    {
        ArrayList arraylist = mWindowCallbacks;
        arraylist;
        JVM INSTR monitorenter ;
        mWindowCallbacks.add(windowcallbacks);
        arraylist;
        JVM INSTR monitorexit ;
        return;
        windowcallbacks;
        throw windowcallbacks;
    }

    void addWindowStoppedCallback(WindowStoppedCallback windowstoppedcallback)
    {
        mWindowStoppedCallbacks.add(windowstoppedcallback);
    }

    public void bringChildToFront(View view)
    {
    }

    public boolean canResolveLayoutDirection()
    {
        return true;
    }

    public boolean canResolveTextAlignment()
    {
        return true;
    }

    public boolean canResolveTextDirection()
    {
        return true;
    }

    public void cancelInvalidate(View view)
    {
        mHandler.removeMessages(1, view);
        mHandler.removeMessages(2, view);
        mInvalidateOnAnimationRunnable.removeView(view);
    }

    void changeCanvasOpacity(boolean flag)
    {
        Log.d(mTag, (new StringBuilder()).append("changeCanvasOpacity: opaque=").append(flag).toString());
        if(mAttachInfo.mThreadedRenderer != null)
            mAttachInfo.mThreadedRenderer.setOpaque(flag);
    }

    void checkThread()
    {
        if(mThread != Thread.currentThread())
            throw new CalledFromWrongThreadException("Only the original thread that created a view hierarchy can touch its views.");
        else
            return;
    }

    public void childDrawableStateChanged(View view)
    {
    }

    public void childHasTransientStateChanged(View view, boolean flag)
    {
    }

    public void clearChildFocus(View view)
    {
        checkThread();
        scheduleTraversals();
    }

    public void createContextMenu(ContextMenu contextmenu)
    {
    }

    public void debug()
    {
        mView.debug();
    }

    void destroyHardwareResources()
    {
        if(mAttachInfo.mThreadedRenderer != null)
        {
            mAttachInfo.mThreadedRenderer.destroyHardwareResources(mView);
            mAttachInfo.mThreadedRenderer.destroy();
        }
    }

    public void detachFunctor(long l)
    {
        if(mAttachInfo.mThreadedRenderer != null)
            mAttachInfo.mThreadedRenderer.stopDrawing();
    }

    boolean die(boolean flag)
    {
        if(flag && mIsInTraversal ^ true)
        {
            doDie();
            return false;
        }
        if(!mIsDrawing)
            destroyHardwareRenderer();
        else
            Log.e(mTag, (new StringBuilder()).append("Attempting to destroy the window while drawing!\n  window=").append(this).append(", title=").append(mWindowAttributes.getTitle()).toString());
        mHandler.sendEmptyMessage(3);
        return true;
    }

    public void dispatchAppVisibility(boolean flag)
    {
        Message message = mHandler.obtainMessage(8);
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        message.arg1 = i;
        mHandler.sendMessage(message);
    }

    void dispatchApplyInsets(View view)
    {
        view.dispatchApplyWindowInsets(getWindowInsets(true));
    }

    public void dispatchCheckFocus()
    {
        if(!mHandler.hasMessages(13))
            mHandler.sendEmptyMessage(13);
    }

    public void dispatchCloseSystemDialogs(String s)
    {
        Message message = Message.obtain();
        message.what = 14;
        message.obj = s;
        mHandler.sendMessage(message);
    }

    void dispatchDetachedFromWindow()
    {
        if(mView != null && mView.mAttachInfo != null)
        {
            mAttachInfo.mTreeObserver.dispatchOnWindowAttachedChange(false);
            mView.dispatchDetachedFromWindow();
        }
        mAccessibilityInteractionConnectionManager.ensureNoConnection();
        mAccessibilityManager.removeAccessibilityStateChangeListener(mAccessibilityInteractionConnectionManager);
        mAccessibilityManager.removeHighTextContrastStateChangeListener(mHighContrastTextManager);
        removeSendWindowContentChangedCallback();
        destroyHardwareRenderer();
        setAccessibilityFocus(null, null);
        mView.assignParent(null);
        mView = null;
        mAttachInfo.mRootView = null;
        mSurface.release();
        if(mInputQueueCallback != null && mInputQueue != null)
        {
            mInputQueueCallback.onInputQueueDestroyed(mInputQueue);
            mInputQueue.dispose();
            mInputQueueCallback = null;
            mInputQueue = null;
        }
        if(mInputEventReceiver != null)
        {
            mInputEventReceiver.dispose();
            mInputEventReceiver = null;
        }
        try
        {
            mWindowSession.remove(mWindow);
        }
        catch(RemoteException remoteexception) { }
        if(mInputChannel != null)
        {
            mInputChannel.dispose();
            mInputChannel = null;
        }
        mDisplayManager.unregisterDisplayListener(mDisplayListener);
        unscheduleTraversals();
    }

    public void dispatchDragEvent(DragEvent dragevent)
    {
        byte byte0;
        if(dragevent.getAction() == 2)
        {
            byte0 = 16;
            mHandler.removeMessages(16);
        } else
        {
            byte0 = 15;
        }
        dragevent = mHandler.obtainMessage(byte0, dragevent);
        mHandler.sendMessage(dragevent);
    }

    public void dispatchGetNewSurface()
    {
        Message message = mHandler.obtainMessage(9);
        mHandler.sendMessage(message);
    }

    public void dispatchInputEvent(InputEvent inputevent)
    {
        dispatchInputEvent(inputevent, null);
    }

    public void dispatchInputEvent(InputEvent inputevent, InputEventReceiver inputeventreceiver)
    {
        SomeArgs someargs = SomeArgs.obtain();
        someargs.arg1 = inputevent;
        someargs.arg2 = inputeventreceiver;
        inputevent = mHandler.obtainMessage(7, someargs);
        inputevent.setAsynchronous(true);
        mHandler.sendMessage(inputevent);
    }

    public void dispatchInvalidateDelayed(View view, long l)
    {
        view = mHandler.obtainMessage(1, view);
        mHandler.sendMessageDelayed(view, l);
    }

    public void dispatchInvalidateOnAnimation(View view)
    {
        mInvalidateOnAnimationRunnable.addView(view);
    }

    public void dispatchInvalidateRectDelayed(View.AttachInfo.InvalidateInfo invalidateinfo, long l)
    {
        invalidateinfo = mHandler.obtainMessage(2, invalidateinfo);
        mHandler.sendMessageDelayed(invalidateinfo, l);
    }

    public void dispatchInvalidateRectOnAnimation(View.AttachInfo.InvalidateInfo invalidateinfo)
    {
        mInvalidateOnAnimationRunnable.addViewRect(invalidateinfo);
    }

    public final void dispatchKeyEventToContentCatcher(KeyEvent keyevent)
    {
        Activity activity = mView.getAttachedActivityInstance();
        if(activity != null && activity.getInterceptor() != null)
            activity.getInterceptor().dispatchKeyEvent(keyevent, mView, activity);
    }

    public void dispatchKeyFromIme(KeyEvent keyevent)
    {
        keyevent = mHandler.obtainMessage(11, keyevent);
        keyevent.setAsynchronous(true);
        mHandler.sendMessage(keyevent);
    }

    public void dispatchMoved(int i, int j)
    {
        int k = i;
        int l = j;
        if(mTranslator != null)
        {
            PointF pointf = new PointF(i, j);
            mTranslator.translatePointInScreenToAppWindow(pointf);
            k = (int)((double)pointf.x + 0.5D);
            l = (int)((double)pointf.y + 0.5D);
        }
        Message message = mHandler.obtainMessage(23, k, l);
        mHandler.sendMessage(message);
    }

    public void dispatchPointerCaptureChanged(boolean flag)
    {
        mHandler.removeMessages(28);
        Message message = mHandler.obtainMessage(28);
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        message.arg1 = i;
        mHandler.sendMessage(message);
    }

    public void dispatchRequestKeyboardShortcuts(IResultReceiver iresultreceiver, int i)
    {
        mHandler.obtainMessage(26, i, 0, iresultreceiver).sendToTarget();
    }

    public void dispatchSystemUiVisibilityChanged(int i, int j, int k, int l)
    {
        SystemUiVisibilityInfo systemuivisibilityinfo = new SystemUiVisibilityInfo();
        systemuivisibilityinfo.seq = i;
        systemuivisibilityinfo.globalVisibility = j;
        systemuivisibilityinfo.localValue = k;
        systemuivisibilityinfo.localChanges = l;
        mHandler.sendMessage(mHandler.obtainMessage(17, systemuivisibilityinfo));
    }

    public void dispatchUnhandledInputEvent(InputEvent inputevent)
    {
        Object obj = inputevent;
        if(inputevent instanceof MotionEvent)
            obj = MotionEvent.obtain((MotionEvent)inputevent);
        synthesizeInputEvent(((InputEvent) (obj)));
    }

    public void dispatchWindowShown()
    {
        mHandler.sendEmptyMessage(25);
    }

    void doConsumeBatchedInput(long l)
    {
        if(mConsumeBatchedInputScheduled)
        {
            mConsumeBatchedInputScheduled = false;
            if(mInputEventReceiver != null && mInputEventReceiver.consumeBatchedInputEvents(l) && l != -1L)
                scheduleConsumeBatchedInput();
            doProcessInputEvents();
        }
    }

    void doDie()
    {
        checkThread();
        this;
        JVM INSTR monitorenter ;
        boolean flag = mRemoved;
        if(!flag)
            break MISSING_BLOCK_LABEL_18;
        this;
        JVM INSTR monitorexit ;
        return;
        int i;
        mRemoved = true;
        if(mAdded)
            dispatchDetachedFromWindow();
        if(!mAdded || !(mFirst ^ true))
            break MISSING_BLOCK_LABEL_127;
        destroyHardwareRenderer();
        if(mView == null)
            break MISSING_BLOCK_LABEL_127;
        i = mView.getVisibility();
        boolean flag1;
        if(mViewVisibility != i)
            flag1 = true;
        else
            flag1 = false;
        flag = mWindowAttributesChanged;
        if(!flag && !flag1)
            break MISSING_BLOCK_LABEL_120;
        Exception exception;
        try
        {
            if((relayoutWindow(mWindowAttributes, i, false) & 2) != 0)
                mWindowSession.finishDrawing(mWindow);
        }
        catch(RemoteException remoteexception) { }
        mSurface.release();
        mAdded = false;
        this;
        JVM INSTR monitorexit ;
        WindowManagerGlobal.getInstance().doRemoveView(this);
        return;
        exception;
        throw exception;
    }

    void doProcessInputEvents()
    {
        while(mPendingInputEventHead != null) 
        {
            QueuedInputEvent queuedinputevent = mPendingInputEventHead;
            mPendingInputEventHead = queuedinputevent.mNext;
            if(mPendingInputEventHead == null)
                mPendingInputEventTail = null;
            queuedinputevent.mNext = null;
            mPendingInputEventCount = mPendingInputEventCount - 1;
            Trace.traceCounter(4L, mPendingInputEventQueueLengthCounterName, mPendingInputEventCount);
            long l = queuedinputevent.mEvent.getEventTimeNano();
            long l1 = l;
            long l2 = l1;
            if(queuedinputevent.mEvent instanceof MotionEvent)
            {
                MotionEvent motionevent = (MotionEvent)queuedinputevent.mEvent;
                l2 = l1;
                if(motionevent.getHistorySize() > 0)
                    l2 = motionevent.getHistoricalEventTimeNano(0);
            }
            mChoreographer.mFrameInfo.updateInputEventTime(l, l2);
            deliverInputEvent(queuedinputevent);
        }
        if(mProcessInputEventsScheduled)
        {
            mProcessInputEventsScheduled = false;
            mHandler.removeMessages(19);
        }
    }

    void doTraversal()
    {
        if(mTraversalScheduled)
        {
            mTraversalScheduled = false;
            mHandler.getLooper().getQueue().removeSyncBarrier(mTraversalBarrier);
            if(mProfile)
                Debug.startMethodTracing("ViewAncestor");
            performTraversals();
            if(mProfile)
            {
                Debug.stopMethodTracing();
                mProfile = false;
            }
        }
    }

    void drawPending()
    {
        mDrawsNeededToReport = mDrawsNeededToReport + 1;
    }

    public void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        filedescriptor = (new StringBuilder()).append(s).append("  ").toString();
        printwriter.print(s);
        printwriter.println("ViewRoot:");
        printwriter.print(filedescriptor);
        printwriter.print("mAdded=");
        printwriter.print(mAdded);
        printwriter.print(" mRemoved=");
        printwriter.println(mRemoved);
        printwriter.print(filedescriptor);
        printwriter.print("mConsumeBatchedInputScheduled=");
        printwriter.println(mConsumeBatchedInputScheduled);
        printwriter.print(filedescriptor);
        printwriter.print("mConsumeBatchedInputImmediatelyScheduled=");
        printwriter.println(mConsumeBatchedInputImmediatelyScheduled);
        printwriter.print(filedescriptor);
        printwriter.print("mPendingInputEventCount=");
        printwriter.println(mPendingInputEventCount);
        printwriter.print(filedescriptor);
        printwriter.print("mProcessInputEventsScheduled=");
        printwriter.println(mProcessInputEventsScheduled);
        printwriter.print(filedescriptor);
        printwriter.print("mTraversalScheduled=");
        printwriter.print(mTraversalScheduled);
        printwriter.print(filedescriptor);
        printwriter.print("mIsAmbientMode=");
        printwriter.print(mIsAmbientMode);
        if(mTraversalScheduled)
        {
            printwriter.print(" (barrier=");
            printwriter.print(mTraversalBarrier);
            printwriter.println(")");
        } else
        {
            printwriter.println();
        }
        mFirstInputStage.dump(filedescriptor, printwriter);
        mChoreographer.dump(s, printwriter);
        printwriter.print(s);
        printwriter.println("View Hierarchy:");
        dumpViewHierarchy(filedescriptor, printwriter, mView);
    }

    public void dumpGfxInfo(int ai[])
    {
        ai[1] = 0;
        ai[0] = 0;
        if(mView != null)
            getGfxInfo(mView, ai);
    }

    void enqueueInputEvent(InputEvent inputevent)
    {
        enqueueInputEvent(inputevent, null, 0, false);
    }

    void enqueueInputEvent(InputEvent inputevent, InputEventReceiver inputeventreceiver, int i, boolean flag)
    {
        adjustInputEventForCompatibility(inputevent);
        inputevent = obtainQueuedInputEvent(inputevent, inputeventreceiver, i);
        inputeventreceiver = mPendingInputEventTail;
        if(inputeventreceiver == null)
        {
            mPendingInputEventHead = inputevent;
            mPendingInputEventTail = inputevent;
        } else
        {
            inputeventreceiver.mNext = inputevent;
            mPendingInputEventTail = inputevent;
        }
        mPendingInputEventCount = mPendingInputEventCount + 1;
        Trace.traceCounter(4L, mPendingInputEventQueueLengthCounterName, mPendingInputEventCount);
        if(flag)
            doProcessInputEvents();
        else
            scheduleProcessInputEvents();
    }

    boolean ensureTouchMode(boolean flag)
    {
        if(mAttachInfo.mInTouchMode == flag)
            return false;
        try
        {
            mWindowSession.setInTouchMode(flag);
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException(remoteexception);
        }
        return ensureTouchModeLocally(flag);
    }

    public View focusSearch(View view, int i)
    {
        checkThread();
        if(!(mView instanceof ViewGroup))
            return null;
        else
            return FocusFinder.getInstance().findNextFocus((ViewGroup)mView, view, i);
    }

    public void focusableViewAvailable(View view)
    {
        checkThread();
        if(mView == null) goto _L2; else goto _L1
_L1:
        if(mView.hasFocus()) goto _L4; else goto _L3
_L3:
        if(sAlwaysAssignFocus)
            view.requestFocus();
_L2:
        return;
_L4:
        View view1 = mView.findFocus();
        if((view1 instanceof ViewGroup) && ((ViewGroup)view1).getDescendantFocusability() == 0x40000 && isViewDescendantOf(view, view1))
            view.requestFocus();
        if(true) goto _L2; else goto _L5
_L5:
    }

    public View getAccessibilityFocusedHost()
    {
        return mAccessibilityFocusedHost;
    }

    public AccessibilityNodeInfo getAccessibilityFocusedVirtualView()
    {
        return mAccessibilityFocusedVirtualView;
    }

    public AccessibilityInteractionController getAccessibilityInteractionController()
    {
        if(mView == null)
            throw new IllegalStateException("getAccessibilityInteractionController called when there is no mView");
        if(mAccessibilityInteractionController == null)
            mAccessibilityInteractionController = new AccessibilityInteractionController(this);
        return mAccessibilityInteractionController;
    }

    public boolean getChildVisibleRect(View view, Rect rect, Point point)
    {
        if(view != mView)
            throw new RuntimeException("child is not mine, honest!");
        else
            return rect.intersect(0, 0, mWidth, mHeight);
    }

    public int getDisplayId()
    {
        return mDisplay.getDisplayId();
    }

    int getHostVisibility()
    {
        int i;
        if(mAppVisible || mForceDecorViewVisibility)
            i = mView.getVisibility();
        else
            i = 8;
        return i;
    }

    public void getLastTouchPoint(Point point)
    {
        point.x = (int)mLastTouchPoint.x;
        point.y = (int)mLastTouchPoint.y;
    }

    public int getLastTouchSource()
    {
        return mLastTouchSource;
    }

    public int getLayoutDirection()
    {
        return 0;
    }

    final WindowLeaked getLocation()
    {
        return mLocation;
    }

    public ViewParent getParent()
    {
        return null;
    }

    public ViewParent getParentForAccessibility()
    {
        return null;
    }

    public int getTextAlignment()
    {
        return 1;
    }

    public int getTextDirection()
    {
        return 1;
    }

    public CharSequence getTitle()
    {
        return mWindowAttributes.getTitle();
    }

    public View getView()
    {
        return mView;
    }

    public int getWindowFlags()
    {
        return mWindowAttributes.flags;
    }

    WindowInsets getWindowInsets(boolean flag)
    {
        if(mLastWindowInsets != null && !flag) goto _L2; else goto _L1
_L1:
        Rect rect1;
        Rect rect4;
        Rect rect5;
label0:
        {
            mDispatchContentInsets.set(mAttachInfo.mContentInsets);
            mDispatchStableInsets.set(mAttachInfo.mStableInsets);
            Rect rect = mDispatchContentInsets;
            Rect rect2 = mDispatchStableInsets;
            rect4 = rect;
            rect5 = rect2;
            if(flag)
                break label0;
            if(mPendingContentInsets.equals(rect))
            {
                rect4 = rect;
                rect5 = rect2;
                if(!(mPendingStableInsets.equals(rect2) ^ true))
                    break label0;
            }
            rect4 = mPendingContentInsets;
            rect5 = mPendingStableInsets;
        }
        rect1 = mAttachInfo.mOutsets;
          goto _L3
_L5:
        Rect rect3 = new Rect(rect4.left + rect1.left, rect4.top + rect1.top, rect4.right + rect1.right, rect4.bottom + rect1.bottom);
_L6:
        mLastWindowInsets = new WindowInsets(rect3, null, rect5, mContext.getResources().getConfiguration().isScreenRound(), mAttachInfo.mAlwaysConsumeNavBar);
_L2:
        return mLastWindowInsets;
_L3:
        if(rect1.left > 0 || rect1.top > 0 || rect1.right > 0) goto _L5; else goto _L4
_L4:
        rect3 = rect4;
        if(rect1.bottom <= 0) goto _L6; else goto _L5
    }

    void handleAppVisibility(boolean flag)
    {
        if(mAppVisible != flag)
        {
            mAppVisible = flag;
            mAppVisibilityChanged = true;
            scheduleTraversals();
            if(!mAppVisible)
                WindowManagerGlobal.trimForeground();
        }
    }

    public void handleDispatchSystemUiVisibilityChanged(SystemUiVisibilityInfo systemuivisibilityinfo)
    {
        if(mSeq != systemuivisibilityinfo.seq)
        {
            mSeq = systemuivisibilityinfo.seq;
            mAttachInfo.mForceReportNewAttributes = true;
            scheduleTraversals();
        }
        if(mView == null)
            return;
        if(systemuivisibilityinfo.localChanges != 0)
            mView.updateLocalSystemUiVisibility(systemuivisibilityinfo.localValue, systemuivisibilityinfo.localChanges);
        int i = systemuivisibilityinfo.globalVisibility & 7;
        if(i != mAttachInfo.mGlobalSystemUiVisibility)
        {
            mAttachInfo.mGlobalSystemUiVisibility = i;
            mView.dispatchSystemUiVisibilityChanged(i);
        }
    }

    public void handleDispatchWindowShown()
    {
        mAttachInfo.mTreeObserver.dispatchOnWindowShown();
    }

    void handleGetNewSurface()
    {
        mNewSurfaceNeeded = true;
        mFullRedrawNeeded = true;
        scheduleTraversals();
    }

    public void handleRequestKeyboardShortcuts(IResultReceiver iresultreceiver, int i)
    {
        Bundle bundle;
        bundle = new Bundle();
        ArrayList arraylist = new ArrayList();
        if(mView != null)
            mView.requestKeyboardShortcuts(arraylist, i);
        bundle.putParcelableArrayList("shortcuts_array", arraylist);
        iresultreceiver.send(0, bundle);
_L2:
        return;
        iresultreceiver;
        if(true) goto _L2; else goto _L1
_L1:
    }

    boolean hasPointerCapture()
    {
        return mPointerCapture;
    }

    void invalidate()
    {
        mDirty.set(0, 0, mWidth, mHeight);
        if(!mWillDrawSoon)
            scheduleTraversals();
    }

    public void invalidateChild(View view, Rect rect)
    {
        invalidateChildInParent(null, rect);
    }

    public ViewParent invalidateChildInParent(int ai[], Rect rect)
    {
label0:
        {
            checkThread();
            if(rect == null)
            {
                invalidate();
                return null;
            }
            if(rect.isEmpty() && mIsAnimating ^ true)
                return null;
            if(mCurScrollY == 0)
            {
                ai = rect;
                if(mTranslator == null)
                    break label0;
            }
            mTempRect.set(rect);
            rect = mTempRect;
            if(mCurScrollY != 0)
                rect.offset(0, -mCurScrollY);
            if(mTranslator != null)
                mTranslator.translateRectInAppWindowToScreen(rect);
            ai = rect;
            if(mAttachInfo.mScalingRequired)
            {
                rect.inset(-1, -1);
                ai = rect;
            }
        }
        invalidateRectOnScreen(ai);
        return null;
    }

    void invalidateWorld(View view)
    {
        view.invalidate();
        if(view instanceof ViewGroup)
        {
            view = (ViewGroup)view;
            for(int i = 0; i < view.getChildCount(); i++)
                invalidateWorld(view.getChildAt(i));

        }
    }

    boolean isInLayout()
    {
        return mInLayout;
    }

    public boolean isLayoutDirectionResolved()
    {
        return true;
    }

    public boolean isLayoutRequested()
    {
        return mLayoutRequested;
    }

    public boolean isTextAlignmentResolved()
    {
        return true;
    }

    public boolean isTextDirectionResolved()
    {
        return true;
    }

    public View keyboardNavigationClusterSearch(View view, int i)
    {
        checkThread();
        return FocusFinder.getInstance().findNextKeyboardNavigationCluster(mView, view, i);
    }

    public void loadSystemProperties()
    {
        mHandler.post(new Runnable() {

            public void run()
            {
                ViewRootImpl._2D_set1(ViewRootImpl.this, SystemProperties.getBoolean("viewroot.profile_rendering", false));
                ViewRootImpl._2D_wrap13(ViewRootImpl.this, mAttachInfo.mHasWindowFocus);
                if(mAttachInfo.mThreadedRenderer != null && mAttachInfo.mThreadedRenderer.loadSystemProperties())
                    invalidate();
                boolean flag = SystemProperties.getBoolean("debug.layout", false);
                if(flag != mAttachInfo.mDebugLayout)
                {
                    mAttachInfo.mDebugLayout = flag;
                    if(!mHandler.hasMessages(22))
                        mHandler.sendEmptyMessageDelayed(22, 200L);
                }
            }

            final ViewRootImpl this$0;

            
            {
                this$0 = ViewRootImpl.this;
                super();
            }
        }
);
    }

    public void notifyChildRebuilt()
    {
        if(mView instanceof RootViewSurfaceTaker)
        {
            if(mSurfaceHolderCallback != null)
                mSurfaceHolder.removeCallback(mSurfaceHolderCallback);
            mSurfaceHolderCallback = ((RootViewSurfaceTaker)mView).willYouTakeTheSurface();
            if(mSurfaceHolderCallback != null)
            {
                mSurfaceHolder = new TakenSurfaceHolder();
                mSurfaceHolder.setFormat(0);
                mSurfaceHolder.addCallback(mSurfaceHolderCallback);
            } else
            {
                mSurfaceHolder = null;
            }
            mInputQueueCallback = ((RootViewSurfaceTaker)mView).willYouTakeTheInputQueue();
            if(mInputQueueCallback != null)
                mInputQueueCallback.onInputQueueCreated(mInputQueue);
        }
    }

    public void notifyContentChangeToContentCatcher()
    {
        if(mView != null && (mView instanceof DecorView))
        {
            Object obj = ((DecorView)mView).getWindowContext();
            if(obj != null && (obj instanceof Activity))
            {
                obj = (Activity)obj;
                if(((Activity) (obj)).getInterceptor() != null)
                    ((Activity) (obj)).getInterceptor().notifyContentChange();
            }
        }
    }

    void notifyRendererOfFramePending()
    {
        if(mAttachInfo.mThreadedRenderer != null)
            mAttachInfo.mThreadedRenderer.notifyFramePending();
    }

    public void notifySubtreeAccessibilityStateChanged(View view, View view1, int i)
    {
        postSendWindowContentChangedCallback((View)Preconditions.checkNotNull(view1), i);
    }

    public void onDescendantInvalidated(View view, View view1)
    {
        if((view1.mPrivateFlags & 0x40) != 0)
            mIsAnimating = true;
        invalidate();
    }

    public void onMovedToDisplay(int i, Configuration configuration)
    {
        if(mDisplay.getDisplayId() == i)
        {
            return;
        } else
        {
            mDisplay = ResourcesManager.getInstance().getAdjustedDisplay(i, mView.getResources());
            mAttachInfo.mDisplayState = mDisplay.getState();
            mView.dispatchMovedToDisplay(mDisplay, configuration);
            return;
        }
    }

    public boolean onNestedFling(View view, float f, float f1, boolean flag)
    {
        return false;
    }

    public boolean onNestedPreFling(View view, float f, float f1)
    {
        return false;
    }

    public boolean onNestedPrePerformAccessibilityAction(View view, int i, Bundle bundle)
    {
        return false;
    }

    public void onNestedPreScroll(View view, int i, int j, int ai[])
    {
    }

    public void onNestedScroll(View view, int i, int j, int k, int l)
    {
    }

    public void onNestedScrollAccepted(View view, View view1, int i)
    {
    }

    public void onPostDraw(DisplayListCanvas displaylistcanvas)
    {
        drawAccessibilityFocusedDrawableIfNeeded(displaylistcanvas);
        for(int i = mWindowCallbacks.size() - 1; i >= 0; i--)
            ((WindowCallbacks)mWindowCallbacks.get(i)).onPostDraw(displaylistcanvas);

    }

    public void onPreDraw(DisplayListCanvas displaylistcanvas)
    {
        if(mCurScrollY != 0 && mHardwareYOffset != 0 && mAttachInfo.mThreadedRenderer.isOpaque())
            displaylistcanvas.drawColor(0xff000000);
        displaylistcanvas.translate(-mHardwareXOffset, -mHardwareYOffset);
    }

    public boolean onStartNestedScroll(View view, View view1, int i)
    {
        return false;
    }

    public void onStopNestedScroll(View view)
    {
    }

    public void onWindowTitleChanged()
    {
        mAttachInfo.mForceReportNewAttributes = true;
    }

    void outputDisplayList(View view)
    {
        view.mRenderNode.output();
        if(mAttachInfo.mThreadedRenderer != null)
            mAttachInfo.mThreadedRenderer.serializeDisplayListTree();
    }

    void pendingDrawFinished()
    {
        if(mDrawsNeededToReport == 0)
            throw new RuntimeException("Unbalanced drawPending/pendingDrawFinished calls");
        mDrawsNeededToReport = mDrawsNeededToReport - 1;
        if(mDrawsNeededToReport == 0)
            reportDrawFinished();
    }

    public boolean performHapticFeedback(int i, boolean flag)
    {
        try
        {
            flag = mWindowSession.performHapticFeedback(mWindow, i, flag);
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return flag;
    }

    public void playSoundEffect(int i)
    {
        checkThread();
        Object obj = getAudioManager();
        i;
        JVM INSTR tableswitch 0 4: default 44
    //                   0 125
    //                   1 137
    //                   2 149
    //                   3 143
    //                   4 131;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        try
        {
            obj = JVM INSTR new #1335 <Class IllegalArgumentException>;
            StringBuilder stringbuilder = JVM INSTR new #1416 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            ((IllegalArgumentException) (obj)).IllegalArgumentException(stringbuilder.append("unknown effect id ").append(i).append(" not defined in ").append(android/view/SoundEffectConstants.getCanonicalName()).toString());
            throw obj;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            Log.e(mTag, (new StringBuilder()).append("FATAL EXCEPTION when attempting to play sound effect: ").append(obj).toString());
        }
        ((IllegalStateException) (obj)).printStackTrace();
        return;
_L2:
        ((AudioManager) (obj)).playSoundEffect(0);
        return;
_L6:
        ((AudioManager) (obj)).playSoundEffect(2);
        return;
_L3:
        ((AudioManager) (obj)).playSoundEffect(3);
        return;
_L5:
        ((AudioManager) (obj)).playSoundEffect(4);
        return;
_L4:
        ((AudioManager) (obj)).playSoundEffect(1);
        return;
    }

    void pokeDrawLockIfNeeded()
    {
        int i = mAttachInfo.mDisplayState;
        if(mView == null || !mAdded || !mTraversalScheduled || i != 3 && i != 4)
            break MISSING_BLOCK_LABEL_52;
        mWindowSession.pokeDrawLock(mWindow);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void profile()
    {
        mProfile = true;
    }

    public void recomputeViewAttributes(View view)
    {
        checkThread();
        if(mView == view)
        {
            mAttachInfo.mRecomputeGlobalAttributes = true;
            if(!mWillDrawSoon)
                scheduleTraversals();
        }
    }

    public void registerAnimatingRenderNode(RenderNode rendernode)
    {
        if(mAttachInfo.mThreadedRenderer != null)
        {
            mAttachInfo.mThreadedRenderer.registerAnimatingRenderNode(rendernode);
        } else
        {
            if(mAttachInfo.mPendingAnimatingRenderNodes == null)
                mAttachInfo.mPendingAnimatingRenderNodes = new ArrayList();
            mAttachInfo.mPendingAnimatingRenderNodes.add(rendernode);
        }
    }

    public void registerVectorDrawableAnimator(android.graphics.drawable.AnimatedVectorDrawable.VectorDrawableAnimatorRT vectordrawableanimatorrt)
    {
        if(mAttachInfo.mThreadedRenderer != null)
            mAttachInfo.mThreadedRenderer.registerVectorDrawableAnimator(vectordrawableanimatorrt);
    }

    public void removeWindowCallbacks(WindowCallbacks windowcallbacks)
    {
        ArrayList arraylist = mWindowCallbacks;
        arraylist;
        JVM INSTR monitorenter ;
        mWindowCallbacks.remove(windowcallbacks);
        arraylist;
        JVM INSTR monitorexit ;
        return;
        windowcallbacks;
        throw windowcallbacks;
    }

    void removeWindowStoppedCallback(WindowStoppedCallback windowstoppedcallback)
    {
        mWindowStoppedCallbacks.remove(windowstoppedcallback);
    }

    public void reportActivityRelaunched()
    {
        mActivityRelaunched = true;
    }

    public void reportDrawFinish()
    {
        if(mWindowDrawCountDown != null)
            mWindowDrawCountDown.countDown();
    }

    public void requestChildFocus(View view, View view1)
    {
        checkThread();
        scheduleTraversals();
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean flag)
    {
        if(rect == null)
            return scrollToRectOrFocus(null, flag);
        rect.offset(view.getLeft() - view.getScrollX(), view.getTop() - view.getScrollY());
        flag = scrollToRectOrFocus(rect, flag);
        mTempRect.set(rect);
        mTempRect.offset(0, -mCurScrollY);
        mTempRect.offset(mAttachInfo.mWindowLeft, mAttachInfo.mWindowTop);
        try
        {
            mWindowSession.onRectangleOnScreenRequested(mWindow, mTempRect);
        }
        // Misplaced declaration of an exception variable
        catch(View view) { }
        return flag;
    }

    public void requestDisallowInterceptTouchEvent(boolean flag)
    {
    }

    public void requestFitSystemWindows()
    {
        checkThread();
        mApplyInsetsRequested = true;
        scheduleTraversals();
    }

    public void requestInvalidateRootRenderNode()
    {
        mInvalidateRootRequested = true;
    }

    public void requestLayout()
    {
        if(!mHandlingLayoutInLayoutRequest)
        {
            checkThread();
            mLayoutRequested = true;
            scheduleTraversals();
        }
    }

    boolean requestLayoutDuringLayout(View view)
    {
        if(view.mParent == null || view.mAttachInfo == null)
            return true;
        if(!mLayoutRequesters.contains(view))
            mLayoutRequesters.add(view);
        return !mHandlingLayoutInLayoutRequest;
    }

    void requestPointerCapture(boolean flag)
    {
        if(mPointerCapture == flag)
        {
            return;
        } else
        {
            InputManager.getInstance().requestPointerCapture(mAttachInfo.mWindowToken, flag);
            return;
        }
    }

    public boolean requestSendAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
    {
        if(mView == null || mStopped || mPausedForTransition)
            return false;
        if(accessibilityevent.getEventType() != 2048 && mSendWindowContentChangedAccessibilityEvent != null && mSendWindowContentChangedAccessibilityEvent.mSource != null)
            mSendWindowContentChangedAccessibilityEvent.removeCallbacksAndRun();
        accessibilityevent.getEventType();
        JVM INSTR lookupswitch 3: default 96
    //                   2048: 199
    //                   32768: 106
    //                   65536: 160;
           goto _L1 _L2 _L3 _L4
_L1:
        mAccessibilityManager.sendAccessibilityEvent(accessibilityevent);
        return true;
_L3:
        long l = accessibilityevent.getSourceNodeId();
        int i = AccessibilityNodeInfo.getAccessibilityViewId(l);
        View view1 = mView.findViewByAccessibilityId(i);
        if(view1 != null)
        {
            view = view1.getAccessibilityNodeProvider();
            if(view != null)
                setAccessibilityFocus(view1, view.createAccessibilityNodeInfo(AccessibilityNodeInfo.getVirtualDescendantId(l)));
        }
        continue; /* Loop/switch isn't completed */
_L4:
        int j = AccessibilityNodeInfo.getAccessibilityViewId(accessibilityevent.getSourceNodeId());
        view = mView.findViewByAccessibilityId(j);
        if(view != null && view.getAccessibilityNodeProvider() != null)
            setAccessibilityFocus(null, null);
        continue; /* Loop/switch isn't completed */
_L2:
        handleWindowContentChangedEvent(accessibilityevent);
        if(true) goto _L1; else goto _L5
_L5:
    }

    public void requestTransitionStart(LayoutTransition layouttransition)
    {
        if(mPendingTransitions == null || mPendingTransitions.contains(layouttransition) ^ true)
        {
            if(mPendingTransitions == null)
                mPendingTransitions = new ArrayList();
            mPendingTransitions.add(layouttransition);
        }
    }

    public void requestTransparentRegion(View view)
    {
        checkThread();
        if(mView == view)
        {
            view = mView;
            view.mPrivateFlags = view.mPrivateFlags | 0x200;
            mWindowAttributesChanged = true;
            mWindowAttributesChangesFlag = 0;
            requestLayout();
        }
    }

    public void requestUpdateConfiguration(Configuration configuration)
    {
        configuration = mHandler.obtainMessage(18, configuration);
        mHandler.sendMessage(configuration);
    }

    void scheduleConsumeBatchedInput()
    {
        if(!mConsumeBatchedInputScheduled)
        {
            mConsumeBatchedInputScheduled = true;
            mChoreographer.postCallback(0, mConsumedBatchedInputRunnable, null);
        }
    }

    void scheduleConsumeBatchedInputImmediately()
    {
        if(!mConsumeBatchedInputImmediatelyScheduled)
        {
            unscheduleConsumeBatchedInput();
            mConsumeBatchedInputImmediatelyScheduled = true;
            mHandler.post(mConsumeBatchedInputImmediatelyRunnable);
        }
    }

    void scheduleTraversals()
    {
        if(!mTraversalScheduled)
        {
            mTraversalScheduled = true;
            mTraversalBarrier = mHandler.getLooper().getQueue().postSyncBarrier();
            mChoreographer.postCallback(2, mTraversalRunnable, null);
            if(!mUnbufferedInputDispatch)
                scheduleConsumeBatchedInput();
            notifyRendererOfFramePending();
            pokeDrawLockIfNeeded();
        }
    }

    boolean scrollToRectOrFocus(Rect rect, boolean flag)
    {
        Rect rect1;
        Rect rect2;
        int i;
        boolean flag1;
        rect1 = mAttachInfo.mContentInsets;
        rect2 = mAttachInfo.mVisibleInsets;
        i = 0;
        flag1 = false;
        if(rect2.left > rect1.left || rect2.top > rect1.top || rect2.right > rect1.right || rect2.bottom > rect1.bottom) goto _L2; else goto _L1
_L1:
        flag2 = flag1;
_L5:
        if(i != mScrollY)
        {
            View view;
            if(!flag)
            {
                if(mScroller == null)
                    mScroller = new Scroller(mView.getContext());
                mScroller.startScroll(0, mScrollY, 0, i - mScrollY);
            } else
            if(mScroller != null)
                mScroller.abortAnimation();
            mScrollY = i;
        }
        return flag2;
_L2:
        int j = mScrollY;
        View view1 = mView.findFocus();
        boolean flag2;
        if(view1 == null)
            return false;
        if(mLastScrolledFocus != null)
            view = (View)mLastScrolledFocus.get();
        else
            view = null;
        if(view1 != view)
            rect = null;
        if(view1 != view || !(mScrollMayChange ^ true)) goto _L4; else goto _L3
_L3:
        flag2 = flag1;
        i = j;
        if(rect == null) goto _L5; else goto _L4
_L4:
        mLastScrolledFocus = new WeakReference(view1);
        mScrollMayChange = false;
        flag2 = flag1;
        i = j;
        if(view1.getGlobalVisibleRect(mVisRect, null))
        {
            if(rect == null)
            {
                view1.getFocusedRect(mTempRect);
                if(mView instanceof ViewGroup)
                    ((ViewGroup)mView).offsetDescendantRectToMyCoords(view1, mTempRect);
            } else
            {
                mTempRect.set(rect);
            }
            flag2 = flag1;
            i = j;
            if(mTempRect.intersect(mVisRect))
            {
                if(mTempRect.height() > mView.getHeight() - rect2.top - rect2.bottom)
                    i = j;
                else
                if(mTempRect.top < rect2.top)
                    i = mTempRect.top - rect2.top;
                else
                if(mTempRect.bottom > mView.getHeight() - rect2.bottom)
                    i = mTempRect.bottom - (mView.getHeight() - rect2.bottom);
                else
                    i = 0;
                flag2 = true;
            }
        }
          goto _L5
    }

    void setAccessibilityFocus(View view, AccessibilityNodeInfo accessibilitynodeinfo)
    {
        if(mAccessibilityFocusedVirtualView != null)
        {
            AccessibilityNodeInfo accessibilitynodeinfo1 = mAccessibilityFocusedVirtualView;
            View view1 = mAccessibilityFocusedHost;
            mAccessibilityFocusedHost = null;
            mAccessibilityFocusedVirtualView = null;
            view1.clearAccessibilityFocusNoCallbacks(64);
            AccessibilityNodeProvider accessibilitynodeprovider = view1.getAccessibilityNodeProvider();
            if(accessibilitynodeprovider != null)
            {
                accessibilitynodeinfo1.getBoundsInParent(mTempRect);
                view1.invalidate(mTempRect);
                accessibilitynodeprovider.performAction(AccessibilityNodeInfo.getVirtualDescendantId(accessibilitynodeinfo1.getSourceNodeId()), 128, null);
            }
            accessibilitynodeinfo1.recycle();
        }
        if(mAccessibilityFocusedHost != null && mAccessibilityFocusedHost != view)
            mAccessibilityFocusedHost.clearAccessibilityFocusNoCallbacks(64);
        mAccessibilityFocusedHost = view;
        mAccessibilityFocusedVirtualView = accessibilitynodeinfo;
        if(mAttachInfo.mThreadedRenderer != null)
            mAttachInfo.mThreadedRenderer.invalidateRoot();
    }

    public void setActivityConfigCallback(ActivityConfigCallback activityconfigcallback)
    {
        mActivityConfigCallback = activityconfigcallback;
    }

    public void setDragFocus(View view, DragEvent dragevent)
    {
        if(mCurrentDragView != view && View.sCascadedDragDrop ^ true)
        {
            float f = dragevent.mX;
            float f1 = dragevent.mY;
            int i = dragevent.mAction;
            ClipData clipdata = dragevent.mClipData;
            dragevent.mX = 0.0F;
            dragevent.mY = 0.0F;
            dragevent.mClipData = null;
            if(mCurrentDragView != null)
            {
                dragevent.mAction = 6;
                mCurrentDragView.callDragEventHandler(dragevent);
            }
            if(view != null)
            {
                dragevent.mAction = 5;
                view.callDragEventHandler(dragevent);
            }
            dragevent.mAction = i;
            dragevent.mX = f;
            dragevent.mY = f1;
            dragevent.mClipData = clipdata;
        }
        mCurrentDragView = view;
    }

    public void setIsAmbientMode(boolean flag)
    {
        mIsAmbientMode = flag;
    }

    void setLayoutParams(WindowManager.LayoutParams layoutparams, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        int j;
        int k;
        int l;
        int i1;
        boolean flag1;
        i = mWindowAttributes.surfaceInsets.left;
        j = mWindowAttributes.surfaceInsets.top;
        k = mWindowAttributes.surfaceInsets.right;
        l = mWindowAttributes.surfaceInsets.bottom;
        i1 = mWindowAttributes.softInputMode;
        flag1 = mWindowAttributes.hasManualSurfaceInsets;
        mClientWindowLayoutFlags = layoutparams.flags;
        int j1 = mWindowAttributes.privateFlags;
        layoutparams.systemUiVisibility = mWindowAttributes.systemUiVisibility;
        layoutparams.subtreeSystemUiVisibility = mWindowAttributes.subtreeSystemUiVisibility;
        mWindowAttributesChangesFlag = mWindowAttributes.copyFrom(layoutparams);
        if((mWindowAttributesChangesFlag & 0x80000) != 0)
            mAttachInfo.mRecomputeGlobalAttributes = true;
        if((mWindowAttributesChangesFlag & 1) != 0)
            mAttachInfo.mNeedsUpdateLightCenter = true;
        if(mWindowAttributes.packageName == null)
            mWindowAttributes.packageName = mBasePackageName;
        mWindowAttributes.extraPrivateFlags = MiuiInit.getNotchConfig(mWindowAttributes.packageName);
        WindowManager.LayoutParams layoutparams1 = mWindowAttributes;
        layoutparams1.privateFlags = layoutparams1.privateFlags | j1 & 0x80;
        if(!mWindowAttributes.preservePreviousSurfaceInsets) goto _L2; else goto _L1
_L1:
        mWindowAttributes.surfaceInsets.set(i, j, k, l);
        mWindowAttributes.hasManualSurfaceInsets = flag1;
_L6:
        applyKeepScreenOnFlag(mWindowAttributes);
        if(!flag)
            break MISSING_BLOCK_LABEL_275;
        mSoftInputMode = layoutparams.softInputMode;
        requestLayout();
        if((layoutparams.softInputMode & 0xf0) == 0)
            mWindowAttributes.softInputMode = mWindowAttributes.softInputMode & 0xffffff0f | i1 & 0xf0;
        mWindowAttributesChanged = true;
        scheduleTraversals();
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if(mWindowAttributes.surfaceInsets.left == i && mWindowAttributes.surfaceInsets.top == j) goto _L4; else goto _L3
_L3:
        mNeedsRendererSetup = true;
        break; /* Loop/switch isn't completed */
        layoutparams;
        throw layoutparams;
_L4:
        if(mWindowAttributes.surfaceInsets.right != k) goto _L3; else goto _L5
_L5:
        j = mWindowAttributes.surfaceInsets.bottom;
        if(j == l) goto _L6; else goto _L3
    }

    void setLocalDragState(Object obj)
    {
        mLocalDragState = obj;
    }

    public void setPausedForTransition(boolean flag)
    {
        mPausedForTransition = flag;
    }

    public void setReportNextDraw()
    {
        reportNextDraw();
        invalidate();
    }

    public void setView(View view, WindowManager.LayoutParams layoutparams, View view1)
    {
        this;
        JVM INSTR monitorenter ;
        if(mView != null) goto _L2; else goto _L1
_L1:
        Object obj;
        mView = view;
        mAttachInfo.mDisplayState = mDisplay.getState();
        mDisplayManager.registerDisplayListener(mDisplayListener, mHandler);
        mViewLayoutDirectionInitial = mView.getRawLayoutDirection();
        mFallbackEventHandler.setView(view);
        mWindowAttributes.copyFrom(layoutparams);
        if(mWindowAttributes.packageName == null)
            mWindowAttributes.packageName = mBasePackageName;
        mWindowAttributes.extraPrivateFlags = MiuiInit.getNotchConfig(mWindowAttributes.packageName);
        layoutparams = mWindowAttributes;
        setTag();
        mClientWindowLayoutFlags = layoutparams.flags;
        setAccessibilityFocus(null, null);
        if(view instanceof RootViewSurfaceTaker)
        {
            mSurfaceHolderCallback = ((RootViewSurfaceTaker)view).willYouTakeTheSurface();
            if(mSurfaceHolderCallback != null)
            {
                TakenSurfaceHolder takensurfaceholder = JVM INSTR new #91  <Class ViewRootImpl$TakenSurfaceHolder>;
                takensurfaceholder.this. TakenSurfaceHolder();
                mSurfaceHolder = takensurfaceholder;
                mSurfaceHolder.setFormat(0);
                mSurfaceHolder.addCallback(mSurfaceHolderCallback);
            }
        }
        if(!layoutparams.hasManualSurfaceInsets)
            layoutparams.setSurfaceInsets(view, false, true);
        obj = mDisplay.getDisplayAdjustments().getCompatibilityInfo();
        mTranslator = ((CompatibilityInfo) (obj)).getTranslator();
        if(mSurfaceHolder == null)
            enableHardwareAcceleration(layoutparams);
        boolean flag = false;
        if(mTranslator == null)
            break MISSING_BLOCK_LABEL_280;
        mSurface.setCompatibilityTranslator(mTranslator);
        flag = true;
        layoutparams.backup();
        mTranslator.translateWindowLayout(layoutparams);
        if(!((CompatibilityInfo) (obj)).supportsScreen())
        {
            layoutparams.privateFlags = layoutparams.privateFlags | 0x80;
            mLastInCompatMode = true;
        }
        mSoftInputMode = layoutparams.softInputMode;
        mWindowAttributesChanged = true;
        mWindowAttributesChangesFlag = -1;
        mAttachInfo.mRootView = view;
        obj = mAttachInfo;
        boolean flag1;
        float f;
        int i;
        if(mTranslator != null)
            flag1 = true;
        else
            flag1 = false;
        obj.mScalingRequired = flag1;
        obj = mAttachInfo;
        if(mTranslator != null) goto _L4; else goto _L3
_L3:
        f = 1.0F;
_L17:
        obj.mApplicationScale = f;
        if(view1 == null)
            break MISSING_BLOCK_LABEL_392;
        mAttachInfo.mPanelParentWindowToken = view1.getApplicationWindowToken();
        mAdded = true;
        requestLayout();
        if((mWindowAttributes.inputFeatures & 2) == 0)
        {
            view1 = JVM INSTR new #2777 <Class InputChannel>;
            view1.InputChannel();
            mInputChannel = view1;
        }
        if((mWindowAttributes.privateFlags & 0x4000) != 0)
            flag1 = true;
        else
            flag1 = false;
        mForceDecorViewVisibility = flag1;
        if(mInputChannel == null)
        {
            view1 = JVM INSTR new #2777 <Class InputChannel>;
            view1.InputChannel();
            mInputChannel = view1;
        }
        mOrigWindowType = mWindowAttributes.type;
        mAttachInfo.mRecomputeGlobalAttributes = true;
        collectViewAttributes();
        i = mWindowSession.addToDisplay(mWindow, mSeq, mWindowAttributes, getHostVisibility(), mDisplay.getDisplayId(), mAttachInfo.mContentInsets, mAttachInfo.mStableInsets, mAttachInfo.mOutsets, mInputChannel);
        if(!flag)
            break MISSING_BLOCK_LABEL_561;
        layoutparams.restore();
        if(mTranslator != null)
            mTranslator.translateRectInScreenToAppWindow(mAttachInfo.mContentInsets);
        mPendingOverscanInsets.set(0, 0, 0, 0);
        mPendingContentInsets.set(mAttachInfo.mContentInsets);
        mPendingStableInsets.set(mAttachInfo.mStableInsets);
        mPendingVisibleInsets.set(0, 0, 0, 0);
        view1 = mAttachInfo;
        if((i & 4) != 0)
            flag1 = true;
        else
            flag1 = false;
        view1.mAlwaysConsumeNavBar = flag1;
        mPendingAlwaysConsumeNavBar = mAttachInfo.mAlwaysConsumeNavBar;
        if(i >= 0) goto _L6; else goto _L5
_L5:
        mAttachInfo.mRootView = null;
        mAdded = false;
        mFallbackEventHandler.setView(null);
        unscheduleTraversals();
        setAccessibilityFocus(null, null);
        i;
        JVM INSTR tableswitch -10 -1: default 760
    //                   -10 1214
    //                   -9 1173
    //                   -8 1122
    //                   -7 1065
    //                   -6 1062
    //                   -5 1021
    //                   -4 980
    //                   -3 939
    //                   -2 898
    //                   -1 898;
           goto _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L16
_L7:
        layoutparams = JVM INSTR new #3012 <Class RuntimeException>;
        view = JVM INSTR new #1416 <Class StringBuilder>;
        view.StringBuilder();
        layoutparams.RuntimeException(view.append("Unable to add window -- unknown error code ").append(i).toString());
        throw layoutparams;
        view;
        this;
        JVM INSTR monitorexit ;
        throw view;
_L4:
        f = mTranslator.applicationScale;
          goto _L17
        view;
        mAdded = false;
        mView = null;
        mAttachInfo.mRootView = null;
        mInputChannel = null;
        mFallbackEventHandler.setView(null);
        unscheduleTraversals();
        setAccessibilityFocus(null, null);
        view1 = JVM INSTR new #3012 <Class RuntimeException>;
        view1.RuntimeException("Adding window failed", view);
        throw view1;
        view;
        if(!flag) goto _L19; else goto _L18
_L18:
        layoutparams.restore();
_L19:
        throw view;
_L16:
        view = JVM INSTR new #3489 <Class WindowManager$BadTokenException>;
        view1 = JVM INSTR new #1416 <Class StringBuilder>;
        view1.StringBuilder();
        view.WindowManager.BadTokenException(view1.append("Unable to add window -- token ").append(layoutparams.token).append(" is not valid; is your activity running?").toString());
        throw view;
_L15:
        view = JVM INSTR new #3489 <Class WindowManager$BadTokenException>;
        view1 = JVM INSTR new #1416 <Class StringBuilder>;
        view1.StringBuilder();
        view.WindowManager.BadTokenException(view1.append("Unable to add window -- token ").append(layoutparams.token).append(" is not for an application").toString());
        throw view;
_L14:
        view = JVM INSTR new #3489 <Class WindowManager$BadTokenException>;
        view1 = JVM INSTR new #1416 <Class StringBuilder>;
        view1.StringBuilder();
        view.WindowManager.BadTokenException(view1.append("Unable to add window -- app for token ").append(layoutparams.token).append(" is exiting").toString());
        throw view;
_L13:
        layoutparams = JVM INSTR new #3489 <Class WindowManager$BadTokenException>;
        view = JVM INSTR new #1416 <Class StringBuilder>;
        view.StringBuilder();
        layoutparams.WindowManager.BadTokenException(view.append("Unable to add window -- window ").append(mWindow).append(" has already been added").toString());
        throw layoutparams;
_L12:
        return;
_L11:
        layoutparams = JVM INSTR new #3489 <Class WindowManager$BadTokenException>;
        view = JVM INSTR new #1416 <Class StringBuilder>;
        view.StringBuilder();
        layoutparams.WindowManager.BadTokenException(view.append("Unable to add window ").append(mWindow).append(" -- another window of type ").append(mWindowAttributes.type).append(" already exists").toString());
        throw layoutparams;
_L10:
        view = JVM INSTR new #3489 <Class WindowManager$BadTokenException>;
        layoutparams = JVM INSTR new #1416 <Class StringBuilder>;
        layoutparams.StringBuilder();
        view.WindowManager.BadTokenException(layoutparams.append("Unable to add window ").append(mWindow).append(" -- permission denied for window type ").append(mWindowAttributes.type).toString());
        throw view;
_L9:
        layoutparams = JVM INSTR new #3517 <Class WindowManager$InvalidDisplayException>;
        view = JVM INSTR new #1416 <Class StringBuilder>;
        view.StringBuilder();
        layoutparams.WindowManager.InvalidDisplayException(view.append("Unable to add window ").append(mWindow).append(" -- the specified display can not be found").toString());
        throw layoutparams;
_L8:
        layoutparams = JVM INSTR new #3517 <Class WindowManager$InvalidDisplayException>;
        view = JVM INSTR new #1416 <Class StringBuilder>;
        view.StringBuilder();
        layoutparams.WindowManager.InvalidDisplayException(view.append("Unable to add window ").append(mWindow).append(" -- the specified window type ").append(mWindowAttributes.type).append(" is not valid").toString());
        throw layoutparams;
_L6:
        if(view instanceof RootViewSurfaceTaker)
            mInputQueueCallback = ((RootViewSurfaceTaker)view).willYouTakeTheInputQueue();
        if((mWindowAttributes.inputFeatures & 2) != 0)
            mInputChannel = null;
        if(mInputChannel != null)
        {
            if(mInputQueueCallback != null)
            {
                view1 = JVM INSTR new #2764 <Class InputQueue>;
                view1.InputQueue();
                mInputQueue = view1;
                mInputQueueCallback.onInputQueueCreated(mInputQueue);
            }
            view1 = JVM INSTR new #112 <Class ViewRootImpl$WindowInputEventReceiver>;
            view1.this. WindowInputEventReceiver(mInputChannel, Looper.myLooper());
            mInputEventReceiver = view1;
        }
        view.assignParent(this);
        StringBuilder stringbuilder;
        if((i & 1) != 0)
            flag1 = true;
        else
            flag1 = false;
        mAddedTouchMode = flag1;
        if((i & 2) != 0)
            flag1 = true;
        else
            flag1 = false;
        mAppVisible = flag1;
        if(mAccessibilityManager.isEnabled())
            mAccessibilityInteractionConnectionManager.ensureConnection();
        if(view.getImportantForAccessibility() == 0)
            view.setImportantForAccessibility(1);
        view = layoutparams.getTitle();
        layoutparams = JVM INSTR new #71  <Class ViewRootImpl$SyntheticInputStage>;
        layoutparams.this. SyntheticInputStage();
        mSyntheticInputStage = layoutparams;
        layoutparams = JVM INSTR new #100 <Class ViewRootImpl$ViewPostImeInputStage>;
        layoutparams.this. ViewPostImeInputStage(mSyntheticInputStage);
        view1 = JVM INSTR new #59  <Class ViewRootImpl$NativePostImeInputStage>;
        obj = JVM INSTR new #1416 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        view1.this. NativePostImeInputStage(layoutparams, ((StringBuilder) (obj)).append("aq:native-post-ime:").append(view).toString());
        layoutparams = JVM INSTR new #44  <Class ViewRootImpl$EarlyPostImeInputStage>;
        layoutparams.this. EarlyPostImeInputStage(view1);
        obj = JVM INSTR new #50  <Class ViewRootImpl$ImeInputStage>;
        view1 = JVM INSTR new #1416 <Class StringBuilder>;
        view1.StringBuilder();
        ((ImeInputStage) (obj)).this. ImeInputStage(layoutparams, view1.append("aq:ime:").append(view).toString());
        view1 = JVM INSTR new #103 <Class ViewRootImpl$ViewPreImeInputStage>;
        view1.this. ViewPreImeInputStage(((InputStage) (obj)));
        obj = JVM INSTR new #62  <Class ViewRootImpl$NativePreImeInputStage>;
        stringbuilder = JVM INSTR new #1416 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        ((NativePreImeInputStage) (obj)).this. NativePreImeInputStage(view1, stringbuilder.append("aq:native-pre-ime:").append(view).toString());
        mFirstInputStage = ((InputStage) (obj));
        mFirstPostImeInputStage = layoutparams;
        layoutparams = JVM INSTR new #1416 <Class StringBuilder>;
        layoutparams.StringBuilder();
        mPendingInputEventQueueLengthCounterName = layoutparams.append("aq:pending:").append(view).toString();
_L2:
        this;
        JVM INSTR monitorexit ;
    }

    void setWindowStopped(boolean flag)
    {
        if(mStopped == flag) goto _L2; else goto _L1
_L1:
        ThreadedRenderer threadedrenderer;
        mStopped = flag;
        threadedrenderer = mAttachInfo.mThreadedRenderer;
        if(threadedrenderer != null)
            threadedrenderer.setStopped(mStopped);
        if(mStopped) goto _L4; else goto _L3
_L3:
        scheduleTraversals();
_L5:
        for(int i = 0; i < mWindowStoppedCallbacks.size(); i++)
            ((WindowStoppedCallback)mWindowStoppedCallbacks.get(i)).windowStopped(flag);

        break; /* Loop/switch isn't completed */
_L4:
        if(threadedrenderer != null)
            threadedrenderer.destroyHardwareResources(mView);
        if(true) goto _L5; else goto _L2
_L2:
    }

    public boolean showContextMenuForChild(View view)
    {
        return false;
    }

    public boolean showContextMenuForChild(View view, float f, float f1)
    {
        return false;
    }

    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback)
    {
        return null;
    }

    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback, int i)
    {
        return null;
    }

    public void synthesizeInputEvent(InputEvent inputevent)
    {
        inputevent = mHandler.obtainMessage(24, inputevent);
        inputevent.setAsynchronous(true);
        mHandler.sendMessage(inputevent);
    }

    void transformMatrixToGlobal(Matrix matrix)
    {
        matrix.preTranslate(mAttachInfo.mWindowLeft, mAttachInfo.mWindowTop);
    }

    void transformMatrixToLocal(Matrix matrix)
    {
        matrix.postTranslate(-mAttachInfo.mWindowLeft, -mAttachInfo.mWindowTop);
    }

    void unscheduleConsumeBatchedInput()
    {
        if(mConsumeBatchedInputScheduled)
        {
            mConsumeBatchedInputScheduled = false;
            mChoreographer.removeCallbacks(0, mConsumedBatchedInputRunnable, null);
        }
    }

    void unscheduleTraversals()
    {
        if(mTraversalScheduled)
        {
            mTraversalScheduled = false;
            mHandler.getLooper().getQueue().removeSyncBarrier(mTraversalBarrier);
            mChoreographer.removeCallbacks(2, mTraversalRunnable, null);
        }
    }

    public void updateConfiguration(int i)
    {
        if(mView == null)
            return;
        Resources resources = mView.getResources();
        Configuration configuration = resources.getConfiguration();
        if(i != -1)
            onMovedToDisplay(i, configuration);
        if(mForceNextConfigUpdate || mLastConfigurationFromResources.diff(configuration) != 0)
        {
            mDisplay = ResourcesManager.getInstance().getAdjustedDisplay(mDisplay.getDisplayId(), resources);
            int j = mLastConfigurationFromResources.getLayoutDirection();
            i = configuration.getLayoutDirection();
            mLastConfigurationFromResources.setTo(configuration);
            if(j != i && mViewLayoutDirectionInitial == 2)
                mView.setLayoutDirection(i);
            mView.dispatchConfigurationChanged(configuration);
            mForceNextWindowRelayout = true;
            requestLayout();
        }
    }

    public void updatePointerIcon(float f, float f1)
    {
        mHandler.removeMessages(27);
        Object obj = MotionEvent.obtain(0L, SystemClock.uptimeMillis(), 7, f, f1, 0);
        obj = mHandler.obtainMessage(27, obj);
        mHandler.sendMessage(((Message) (obj)));
    }

    public void windowFocusChanged(boolean flag, boolean flag1)
    {
        boolean flag2 = true;
        Message message = Message.obtain();
        message.what = 6;
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        message.arg1 = i;
        if(flag1)
            i = ((flag2) ? 1 : 0);
        else
            i = 0;
        message.arg2 = i;
        mHandler.sendMessage(message);
    }

    private static final boolean DBG = false;
    private static final boolean DEBUG_CONFIGURATION = false;
    private static final boolean DEBUG_DIALOG = false;
    private static final boolean DEBUG_DRAW = false;
    private static final boolean DEBUG_FPS = false;
    private static final boolean DEBUG_IMF = false;
    private static final boolean DEBUG_INPUT_RESIZE = false;
    private static final boolean DEBUG_INPUT_STAGES = false;
    private static final boolean DEBUG_KEEP_SCREEN_ON = false;
    private static final boolean DEBUG_LAYOUT = false;
    private static final boolean DEBUG_ORIENTATION = false;
    private static final boolean DEBUG_TRACKBALL = false;
    private static final boolean LOCAL_LOGV = false;
    private static final int MAX_QUEUED_INPUT_EVENT_POOL_SIZE = 10;
    static final int MAX_TRACKBALL_DELAY = 250;
    private static final boolean MIUI_OPTS_INPUT = SystemProperties.getBoolean("persist.sys.enable_inputopts", true);
    private static final int MSG_CHECK_FOCUS = 13;
    private static final int MSG_CLEAR_ACCESSIBILITY_FOCUS_HOST = 21;
    private static final int MSG_CLOSE_SYSTEM_DIALOGS = 14;
    private static final int MSG_DIE = 3;
    private static final int MSG_DISPATCH_APP_VISIBILITY = 8;
    private static final int MSG_DISPATCH_DRAG_EVENT = 15;
    private static final int MSG_DISPATCH_DRAG_LOCATION_EVENT = 16;
    private static final int MSG_DISPATCH_GET_NEW_SURFACE = 9;
    private static final int MSG_DISPATCH_INPUT_EVENT = 7;
    private static final int MSG_DISPATCH_KEY_FROM_IME = 11;
    private static final int MSG_DISPATCH_SYSTEM_UI_VISIBILITY = 17;
    private static final int MSG_DISPATCH_WINDOW_SHOWN = 25;
    private static final int MSG_DRAW_FINISHED = 29;
    private static final int MSG_INVALIDATE = 1;
    private static final int MSG_INVALIDATE_RECT = 2;
    private static final int MSG_INVALIDATE_WORLD = 22;
    private static final int MSG_POINTER_CAPTURE_CHANGED = 28;
    private static final int MSG_PROCESS_INPUT_EVENTS = 19;
    private static final int MSG_REQUEST_KEYBOARD_SHORTCUTS = 26;
    private static final int MSG_RESIZED = 4;
    private static final int MSG_RESIZED_REPORT = 5;
    private static final int MSG_SYNTHESIZE_INPUT_EVENT = 24;
    private static final int MSG_UPDATE_CONFIGURATION = 18;
    private static final int MSG_UPDATE_POINTER_ICON = 27;
    private static final int MSG_WINDOW_FOCUS_CHANGED = 6;
    private static final int MSG_WINDOW_MOVED = 23;
    public static final String PROPERTY_EMULATOR_WIN_OUTSET_BOTTOM_PX = "ro.emu.win_outset_bottom_px";
    private static final String PROPERTY_PROFILE_RENDERING = "viewroot.profile_rendering";
    private static final String TAG = "ViewRootImpl";
    private static final boolean USE_MT_RENDERER = true;
    static final Interpolator mResizeInterpolator = new AccelerateDecelerateInterpolator();
    private static boolean sAlwaysAssignFocus;
    private static boolean sCompatibilityDone = false;
    private static final ArrayList sConfigCallbacks = new ArrayList();
    static boolean sFirstDrawComplete = false;
    static final ArrayList sFirstDrawHandlers = new ArrayList();
    static final ThreadLocal sRunQueues = new ThreadLocal();
    View mAccessibilityFocusedHost;
    AccessibilityNodeInfo mAccessibilityFocusedVirtualView;
    final AccessibilityInteractionConnectionManager mAccessibilityInteractionConnectionManager = new AccessibilityInteractionConnectionManager();
    AccessibilityInteractionController mAccessibilityInteractionController;
    final AccessibilityManager mAccessibilityManager;
    private ActivityConfigCallback mActivityConfigCallback;
    private boolean mActivityRelaunched;
    boolean mAdded;
    boolean mAddedTouchMode;
    private boolean mAppVisibilityChanged;
    boolean mAppVisible;
    boolean mApplyInsetsRequested;
    final View.AttachInfo mAttachInfo;
    AudioManager mAudioManager;
    final String mBasePackageName;
    private int mCanvasOffsetX;
    private int mCanvasOffsetY;
    Choreographer mChoreographer;
    int mClientWindowLayoutFlags;
    final ConsumeBatchedInputImmediatelyRunnable mConsumeBatchedInputImmediatelyRunnable = new ConsumeBatchedInputImmediatelyRunnable();
    boolean mConsumeBatchedInputImmediatelyScheduled;
    boolean mConsumeBatchedInputScheduled;
    final ConsumeBatchedInputRunnable mConsumedBatchedInputRunnable = new ConsumeBatchedInputRunnable();
    final Context mContext;
    int mCurScrollY;
    View mCurrentDragView;
    private PointerIcon mCustomPointerIcon;
    private final int mDensity;
    Rect mDirty;
    final Rect mDispatchContentInsets = new Rect();
    final Rect mDispatchStableInsets = new Rect();
    Display mDisplay;
    private final android.hardware.display.DisplayManager.DisplayListener mDisplayListener = new android.hardware.display.DisplayManager.DisplayListener() {

        private int toViewScreenState(int i)
        {
            int j = 1;
            if(i == 1)
                j = 0;
            return j;
        }

        public void onDisplayAdded(int i)
        {
        }

        public void onDisplayChanged(int i)
        {
            if(mView != null && mDisplay.getDisplayId() == i)
            {
                int j = mAttachInfo.mDisplayState;
                int k = mDisplay.getState();
                if(j != k)
                {
                    mAttachInfo.mDisplayState = k;
                    pokeDrawLockIfNeeded();
                    if(j != 0)
                    {
                        i = toViewScreenState(j);
                        k = toViewScreenState(k);
                        if(i != k)
                            mView.dispatchScreenStateChanged(k);
                        if(j == 1)
                        {
                            mFullRedrawNeeded = true;
                            scheduleTraversals();
                        }
                    }
                }
            }
        }

        public void onDisplayRemoved(int i)
        {
        }

        final ViewRootImpl this$0;

            
            {
                this$0 = ViewRootImpl.this;
                super();
            }
    }
;
    final DisplayManager mDisplayManager;
    ClipDescription mDragDescription;
    final PointF mDragPoint = new PointF();
    private boolean mDragResizing;
    boolean mDrawingAllowed;
    int mDrawsNeededToReport;
    FallbackEventHandler mFallbackEventHandler;
    boolean mFirst;
    InputStage mFirstInputStage;
    InputStage mFirstPostImeInputStage;
    private boolean mForceDecorViewVisibility;
    private boolean mForceNextConfigUpdate;
    boolean mForceNextWindowRelayout;
    private int mFpsNumFrames;
    private long mFpsPrevTime;
    private long mFpsStartTime;
    boolean mFullRedrawNeeded;
    boolean mHadWindowFocus;
    final ViewRootHandler mHandler = new ViewRootHandler();
    boolean mHandlingLayoutInLayoutRequest;
    int mHardwareXOffset;
    int mHardwareYOffset;
    boolean mHasHadWindowFocus;
    boolean mHaveMoveEvent;
    int mHeight;
    final HighContrastTextManager mHighContrastTextManager = new HighContrastTextManager();
    private boolean mInLayout;
    InputChannel mInputChannel;
    protected final InputEventConsistencyVerifier mInputEventConsistencyVerifier;
    WindowInputEventReceiver mInputEventReceiver;
    InputQueue mInputQueue;
    InputQueue.Callback mInputQueueCallback;
    final InvalidateOnAnimationRunnable mInvalidateOnAnimationRunnable = new InvalidateOnAnimationRunnable();
    private boolean mInvalidateRootRequested;
    boolean mIsAmbientMode;
    public boolean mIsAnimating;
    boolean mIsCreating;
    boolean mIsDrawing;
    boolean mIsInTraversal;
    boolean mIsPerfLockAcquired;
    private final Configuration mLastConfigurationFromResources = new Configuration();
    final ViewTreeObserver.InternalInsetsInfo mLastGivenInsets = new ViewTreeObserver.InternalInsetsInfo();
    boolean mLastInCompatMode;
    boolean mLastOverscanRequested;
    private final MergedConfiguration mLastReportedMergedConfiguration = new MergedConfiguration();
    WeakReference mLastScrolledFocus;
    int mLastSystemUiVisibility;
    final PointF mLastTouchPoint = new PointF();
    int mLastTouchSource;
    boolean mLastWasImTarget;
    private WindowInsets mLastWindowInsets;
    boolean mLayoutRequested;
    ArrayList mLayoutRequesters;
    volatile Object mLocalDragState;
    final WindowLeaked mLocation = new WindowLeaked(null);
    boolean mLostWindowFocus;
    private boolean mNeedsRendererSetup;
    boolean mNewSurfaceNeeded;
    private final int mNoncompatDensity;
    int mOrigWindowType;
    boolean mPausedForTransition;
    boolean mPendingAlwaysConsumeNavBar;
    final Rect mPendingBackDropFrame = new Rect();
    final Rect mPendingContentInsets = new Rect();
    int mPendingInputEventCount;
    QueuedInputEvent mPendingInputEventHead;
    String mPendingInputEventQueueLengthCounterName;
    QueuedInputEvent mPendingInputEventTail;
    private final MergedConfiguration mPendingMergedConfiguration = new MergedConfiguration();
    final Rect mPendingOutsets = new Rect();
    final Rect mPendingOverscanInsets = new Rect();
    final Rect mPendingStableInsets = new Rect();
    private ArrayList mPendingTransitions;
    final Rect mPendingVisibleInsets = new Rect();
    BoostFramework mPerf;
    boolean mPointerCapture;
    private int mPointerIconType;
    final Region mPreviousTransparentRegion = new Region();
    boolean mProcessInputEventsScheduled;
    private boolean mProfile;
    private boolean mProfileRendering;
    private QueuedInputEvent mQueuedInputEventPool;
    private int mQueuedInputEventPoolSize;
    private boolean mRemoved;
    private Choreographer.FrameCallback mRenderProfiler;
    private boolean mRenderProfilingEnabled;
    boolean mReportNextDraw;
    private int mResizeMode;
    boolean mScrollMayChange;
    int mScrollY;
    Scroller mScroller;
    SendWindowContentChangedAccessibilityEvent mSendWindowContentChangedAccessibilityEvent;
    int mSeq;
    int mSoftInputMode;
    boolean mStopped;
    final Surface mSurface = new Surface();
    BaseSurfaceHolder mSurfaceHolder;
    SurfaceHolder.Callback2 mSurfaceHolderCallback;
    InputStage mSyntheticInputStage;
    private String mTag;
    final int mTargetSdkVersion;
    HashSet mTempHashSet;
    final Rect mTempRect = new Rect();
    final Thread mThread = Thread.currentThread();
    final int mTmpLocation[] = new int[2];
    final TypedValue mTmpValue = new TypedValue();
    android.content.res.CompatibilityInfo.Translator mTranslator;
    final Region mTransparentRegion = new Region();
    int mTraversalBarrier;
    final TraversalRunnable mTraversalRunnable = new TraversalRunnable();
    public boolean mTraversalScheduled;
    boolean mUnbufferedInputDispatch;
    View mView;
    final ViewConfiguration mViewConfiguration;
    private int mViewLayoutDirectionInitial;
    int mViewVisibility;
    final Rect mVisRect = new Rect();
    int mWidth;
    boolean mWillDrawSoon;
    final Rect mWinFrame = new Rect();
    final W mWindow = new W(this);
    final WindowManager.LayoutParams mWindowAttributes = new WindowManager.LayoutParams();
    boolean mWindowAttributesChanged;
    int mWindowAttributesChangesFlag;
    final ArrayList mWindowCallbacks = new ArrayList();
    CountDownLatch mWindowDrawCountDown;
    final IWindowSession mWindowSession = WindowManagerGlobal.getWindowSession();
    private final ArrayList mWindowStoppedCallbacks = new ArrayList();


    // Unreferenced inner class android/view/ViewRootImpl$2

/* anonymous class */
    class _cls2
        implements Runnable
    {

        public void run()
        {
            int i = finalRequesters.size();
            for(int j = 0; j < i; j++)
            {
                View view = (View)finalRequesters.get(j);
                Log.w("View", (new StringBuilder()).append("requestLayout() improperly called by ").append(view).append(" during second layout pass: posting in next frame").toString());
                view.requestLayout();
            }

        }

        final ViewRootImpl this$0;
        final ArrayList val$finalRequesters;

            
            {
                this$0 = ViewRootImpl.this;
                finalRequesters = arraylist;
                super();
            }
    }


    // Unreferenced inner class android/view/ViewRootImpl$SyntheticTouchNavigationHandler$1

/* anonymous class */
    class SyntheticTouchNavigationHandler._cls1
        implements Runnable
    {

        public void run()
        {
            long l = SystemClock.uptimeMillis();
            SyntheticTouchNavigationHandler._2D_wrap2(SyntheticTouchNavigationHandler.this, l, SyntheticTouchNavigationHandler._2D_get1(SyntheticTouchNavigationHandler.this), SyntheticTouchNavigationHandler._2D_get2(SyntheticTouchNavigationHandler.this));
            SyntheticTouchNavigationHandler synthetictouchnavigationhandler = SyntheticTouchNavigationHandler.this;
            SyntheticTouchNavigationHandler._2D_set0(synthetictouchnavigationhandler, SyntheticTouchNavigationHandler._2D_get0(synthetictouchnavigationhandler) * 0.8F);
            if(!SyntheticTouchNavigationHandler._2D_wrap0(SyntheticTouchNavigationHandler.this, l))
            {
                SyntheticTouchNavigationHandler._2D_set1(SyntheticTouchNavigationHandler.this, false);
                SyntheticTouchNavigationHandler._2D_wrap1(SyntheticTouchNavigationHandler.this, l);
            }
        }

        final SyntheticTouchNavigationHandler this$1;

            
            {
                this$1 = SyntheticTouchNavigationHandler.this;
                super();
            }
    }

}
