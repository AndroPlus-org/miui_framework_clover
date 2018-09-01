// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.hardware.display.DisplayManagerGlobal;
import android.os.*;
import android.util.*;
import android.view.animation.AnimationUtils;
import java.io.PrintWriter;

// Referenced classes of package android.view:
//            FrameInfo, DisplayInfo, DisplayEventReceiver

public final class Choreographer
{
    private final class CallbackQueue
    {

        public void addCallbackLocked(long l, Object obj, Object obj1)
        {
            CallbackRecord callbackrecord;
            callbackrecord = Choreographer._2D_wrap0(Choreographer.this, l, obj, obj1);
            obj1 = mHead;
            if(obj1 == null)
            {
                mHead = callbackrecord;
                return;
            }
            obj = obj1;
            if(l < ((CallbackRecord) (obj1)).dueTime)
            {
                callbackrecord.next = ((CallbackRecord) (obj1));
                mHead = callbackrecord;
                return;
            }
              goto _L1
_L3:
            obj = ((CallbackRecord) (obj)).next;
_L1:
            if(((CallbackRecord) (obj)).next == null)
                break; /* Loop/switch isn't completed */
            if(l >= ((CallbackRecord) (obj)).next.dueTime)
                continue; /* Loop/switch isn't completed */
            callbackrecord.next = ((CallbackRecord) (obj)).next;
            break; /* Loop/switch isn't completed */
            if(true) goto _L3; else goto _L2
_L2:
            obj.next = callbackrecord;
            return;
        }

        public CallbackRecord extractDueCallbacksLocked(long l)
        {
            CallbackRecord callbackrecord = mHead;
            if(callbackrecord == null || callbackrecord.dueTime > l)
                return null;
            CallbackRecord callbackrecord1 = callbackrecord;
            CallbackRecord callbackrecord2 = callbackrecord.next;
            do
            {
label0:
                {
                    if(callbackrecord2 != null)
                    {
                        if(callbackrecord2.dueTime <= l)
                            break label0;
                        callbackrecord1.next = null;
                    }
                    mHead = callbackrecord2;
                    return callbackrecord;
                }
                callbackrecord1 = callbackrecord2;
                callbackrecord2 = callbackrecord2.next;
            } while(true);
        }

        public boolean hasDueCallbacksLocked(long l)
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(mHead != null)
            {
                flag1 = flag;
                if(mHead.dueTime <= l)
                    flag1 = true;
            }
            return flag1;
        }

        public void removeCallbacksLocked(Object obj, Object obj1)
        {
            CallbackRecord callbackrecord = null;
            CallbackRecord callbackrecord1 = mHead;
            while(callbackrecord1 != null) 
            {
                CallbackRecord callbackrecord2 = callbackrecord1.next;
                if((obj == null || callbackrecord1.action == obj) && (obj1 == null || callbackrecord1.token == obj1))
                {
                    if(callbackrecord != null)
                        callbackrecord.next = callbackrecord2;
                    else
                        mHead = callbackrecord2;
                    Choreographer._2D_wrap1(Choreographer.this, callbackrecord1);
                } else
                {
                    callbackrecord = callbackrecord1;
                }
                callbackrecord1 = callbackrecord2;
            }
        }

        private CallbackRecord mHead;
        final Choreographer this$0;

        private CallbackQueue()
        {
            this$0 = Choreographer.this;
            super();
        }

        CallbackQueue(CallbackQueue callbackqueue)
        {
            this();
        }
    }

    private static final class CallbackRecord
    {

        public void run(long l)
        {
            if(token == Choreographer._2D_get0())
                ((FrameCallback)action).doFrame(l);
            else
                ((Runnable)action).run();
        }

        public Object action;
        public long dueTime;
        public CallbackRecord next;
        public Object token;

        private CallbackRecord()
        {
        }

        CallbackRecord(CallbackRecord callbackrecord)
        {
            this();
        }
    }

    public static interface FrameCallback
    {

        public abstract void doFrame(long l);
    }

    private final class FrameDisplayEventReceiver extends DisplayEventReceiver
        implements Runnable
    {

        public void onVsync(long l, int i, int j)
        {
            if(i != 0)
            {
                Log.d("Choreographer", "Received vsync from secondary display, but we don't support this case yet.  Choreographer needs a way to explicitly request vsync for a specific display to ensure it doesn't lose track of its scheduled vsync.");
                scheduleVsync();
                return;
            }
            long l1 = System.nanoTime();
            long l2 = l;
            if(l > l1)
            {
                Log.w("Choreographer", (new StringBuilder()).append("Frame time is ").append((float)(l - l1) * 1E-006F).append(" ms in the future!  Check that graphics HAL is generating vsync ").append("timestamps using the correct timebase.").toString());
                l2 = l1;
            }
            Message message;
            if(mHavePendingVsync)
                Log.w("Choreographer", "Already have a pending vsync event.  There should only be one at a time.");
            else
                mHavePendingVsync = true;
            mTimestampNanos = l2;
            mFrame = j;
            message = Message.obtain(Choreographer._2D_get1(Choreographer.this), this);
            message.setAsynchronous(true);
            Choreographer._2D_get1(Choreographer.this).sendMessageAtTime(message, l2 / 0xf4240L);
        }

        public void run()
        {
            mHavePendingVsync = false;
            doFrame(mTimestampNanos, mFrame);
        }

        private int mFrame;
        private boolean mHavePendingVsync;
        private long mTimestampNanos;
        final Choreographer this$0;

        public FrameDisplayEventReceiver(Looper looper, int i)
        {
            this$0 = Choreographer.this;
            super(looper, i);
        }
    }

    private final class FrameHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 0 2: default 32
        //                       0 33
        //                       1 47
        //                       2 57;
               goto _L1 _L2 _L3 _L4
_L1:
            return;
_L2:
            doFrame(System.nanoTime(), 0);
            continue; /* Loop/switch isn't completed */
_L3:
            doScheduleVsync();
            continue; /* Loop/switch isn't completed */
_L4:
            doScheduleCallback(message.arg1);
            if(true) goto _L1; else goto _L5
_L5:
        }

        final Choreographer this$0;

        public FrameHandler(Looper looper)
        {
            this$0 = Choreographer.this;
            super(looper);
        }
    }


    static Object _2D_get0()
    {
        return FRAME_CALLBACK_TOKEN;
    }

    static FrameHandler _2D_get1(Choreographer choreographer)
    {
        return choreographer.mHandler;
    }

    static CallbackRecord _2D_wrap0(Choreographer choreographer, long l, Object obj, Object obj1)
    {
        return choreographer.obtainCallbackLocked(l, obj, obj1);
    }

    static void _2D_wrap1(Choreographer choreographer, CallbackRecord callbackrecord)
    {
        choreographer.recycleCallbackLocked(callbackrecord);
    }

    private Choreographer(Looper looper, int i)
    {
        mLock = new Object();
        mTouchMoveNum = -1;
        mMotionEventType = -1;
        mConsumedMove = false;
        mConsumedDown = false;
        mFrameInfo = new FrameInfo();
        mLooper = looper;
        mHandler = new FrameHandler(looper);
        if(USE_VSYNC)
            looper = new FrameDisplayEventReceiver(looper, i);
        else
            looper = null;
        mDisplayEventReceiver = looper;
        mLastFrameTimeNanos = 0x8000000000000000L;
        mFrameIntervalNanos = (long)(1E+009F / getRefreshRate());
        mCallbackQueues = new CallbackQueue[4];
        for(i = 0; i <= 3; i++)
            mCallbackQueues[i] = new CallbackQueue(null);

    }

    Choreographer(Looper looper, int i, Choreographer choreographer)
    {
        this(looper, i);
    }

    private void dispose()
    {
        mDisplayEventReceiver.dispose();
    }

    public static long getFrameDelay()
    {
        return sFrameDelay;
    }

    public static Choreographer getInstance()
    {
        return (Choreographer)sThreadInstance.get();
    }

    private static float getRefreshRate()
    {
        return DisplayManagerGlobal.getInstance().getDisplayInfo(0).getMode().getRefreshRate();
    }

    public static Choreographer getSfInstance()
    {
        return (Choreographer)sSfThreadInstance.get();
    }

    private boolean isRunningOnLooperThreadLocked()
    {
        boolean flag;
        if(Looper.myLooper() == mLooper)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private CallbackRecord obtainCallbackLocked(long l, Object obj, Object obj1)
    {
        CallbackRecord callbackrecord = mCallbackPool;
        if(callbackrecord == null)
        {
            callbackrecord = new CallbackRecord(null);
        } else
        {
            mCallbackPool = callbackrecord.next;
            callbackrecord.next = null;
        }
        callbackrecord.dueTime = l;
        callbackrecord.action = obj;
        callbackrecord.token = obj1;
        return callbackrecord;
    }

    private void postCallbackDelayedInternal(int i, Object obj, Object obj1, long l)
    {
        if(MIUI_OPTS_INPUT && i == 2)
            mPerformedTraversal = true;
        Object obj2 = mLock;
        obj2;
        JVM INSTR monitorenter ;
        long l1 = SystemClock.uptimeMillis();
        l = l1 + l;
        mCallbackQueues[i].addCallbackLocked(l, obj, obj1);
        if(l > l1) goto _L2; else goto _L1
_L1:
        scheduleFrameLocked(l1);
_L4:
        obj2;
        JVM INSTR monitorexit ;
        return;
_L2:
        obj = mHandler.obtainMessage(2, obj);
        obj.arg1 = i;
        ((Message) (obj)).setAsynchronous(true);
        mHandler.sendMessageAtTime(((Message) (obj)), l);
        if(true) goto _L4; else goto _L3
_L3:
        obj;
        throw obj;
    }

    private void recycleCallbackLocked(CallbackRecord callbackrecord)
    {
        callbackrecord.action = null;
        callbackrecord.token = null;
        callbackrecord.next = mCallbackPool;
        mCallbackPool = callbackrecord;
    }

    public static void releaseInstance()
    {
        Choreographer choreographer = (Choreographer)sThreadInstance.get();
        sThreadInstance.remove();
        choreographer.dispose();
    }

    private void removeCallbacksInternal(int i, Object obj, Object obj1)
    {
        Object obj2 = mLock;
        obj2;
        JVM INSTR monitorenter ;
        mCallbackQueues[i].removeCallbacksLocked(obj, obj1);
        if(obj == null || obj1 != null)
            break MISSING_BLOCK_LABEL_37;
        mHandler.removeMessages(2, obj);
        obj2;
        JVM INSTR monitorexit ;
        return;
        obj;
        throw obj;
    }

    private void scheduleFrameLocked(long l)
    {
        if(mFrameScheduled) goto _L2; else goto _L1
_L1:
        mFrameScheduled = true;
        if(!OPTS_INPUT) goto _L4; else goto _L3
_L3:
        Trace.traceBegin(8L, (new StringBuilder()).append("scheduleFrameLocked-mMotionEventType:").append(mMotionEventType).append(" mTouchMoveNum:").append(mTouchMoveNum).append(" mConsumedDown:").append(mConsumedDown).append(" mConsumedMove:").append(mConsumedMove).toString());
        Trace.traceEnd(8L);
        this;
        JVM INSTR monitorenter ;
        int i = mMotionEventType;
        i;
        JVM INSTR tableswitch 0 3: default 132
    //                   0 152
    //                   1 254
    //                   2 198
    //                   3 254;
           goto _L5 _L6 _L7 _L8 _L7
_L5:
        this;
        JVM INSTR monitorexit ;
_L4:
        Exception exception;
        if(USE_VSYNC)
        {
            if(isRunningOnLooperThreadLocked())
            {
                scheduleVsyncLocked();
            } else
            {
                Message message2 = mHandler.obtainMessage(1);
                message2.setAsynchronous(true);
                mHandler.sendMessageAtFrontOfQueue(message2);
            }
        } else
        {
            l = Math.max(mLastFrameTimeNanos / 0xf4240L + sFrameDelay, l);
            Message message3 = mHandler.obtainMessage(0);
            message3.setAsynchronous(true);
            mHandler.sendMessageAtTime(message3, l);
        }
_L2:
        return;
_L6:
        mConsumedMove = false;
        if(mConsumedDown)
            continue; /* Loop/switch isn't completed */
        Message message = mHandler.obtainMessage(0);
        message.setAsynchronous(true);
        mHandler.sendMessageAtFrontOfQueue(message);
        mConsumedDown = true;
        this;
        JVM INSTR monitorexit ;
        return;
_L8:
        mConsumedDown = false;
        if(mTouchMoveNum != 1 || !(mConsumedMove ^ true))
            continue; /* Loop/switch isn't completed */
        Message message1 = mHandler.obtainMessage(0);
        message1.setAsynchronous(true);
        mHandler.sendMessageAtFrontOfQueue(message1);
        mConsumedMove = true;
        this;
        JVM INSTR monitorexit ;
        return;
_L7:
        mConsumedMove = false;
        mConsumedDown = false;
        if(true) goto _L5; else goto _L9
_L9:
        exception;
        throw exception;
    }

    private void scheduleVsyncLocked()
    {
        mDisplayEventReceiver.scheduleVsync();
    }

    public static void setFrameDelay(long l)
    {
        sFrameDelay = l;
    }

    public static long subtractFrameDelay(long l)
    {
        long l1 = sFrameDelay;
        if(l <= l1)
            l = 0L;
        else
            l -= l1;
        return l;
    }

    void doCallbacks(int i, long l)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        long l1;
        Object obj1;
        l1 = System.nanoTime();
        obj1 = mCallbackQueues[i].extractDueCallbacksLocked(l1 / 0xf4240L);
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_40;
        obj;
        JVM INSTR monitorexit ;
        return;
        mCallbacksRunning = true;
        long l2;
        long l3;
        l2 = l;
        if(i != 3)
            break MISSING_BLOCK_LABEL_111;
        l3 = l1 - l;
        Trace.traceCounter(8L, "jitterNanos", (int)l3);
        l2 = l;
        if(l3 >= mFrameIntervalNanos * 2L)
        {
            l2 = l1 - (l3 % mFrameIntervalNanos + mFrameIntervalNanos);
            mLastFrameTimeNanos = l2;
        }
        obj;
        JVM INSTR monitorexit ;
        Trace.traceBegin(8L, CALLBACK_TRACE_TITLES[i]);
        obj = obj1;
_L2:
        if(obj == null)
            break; /* Loop/switch isn't completed */
        ((CallbackRecord) (obj)).run(l2);
        obj = ((CallbackRecord) (obj)).next;
        if(true) goto _L2; else goto _L1
        obj1;
        throw obj1;
_L1:
        Object obj2 = mLock;
        obj2;
        JVM INSTR monitorenter ;
        mCallbacksRunning = false;
_L4:
        obj = ((CallbackRecord) (obj1)).next;
        recycleCallbackLocked(((CallbackRecord) (obj1)));
        obj1 = obj;
        if(obj != null) goto _L4; else goto _L3
_L3:
        Trace.traceEnd(8L);
        return;
        obj1;
        throw obj1;
        Exception exception1;
        exception1;
        obj2 = mLock;
        obj2;
        JVM INSTR monitorenter ;
        mCallbacksRunning = false;
_L6:
        obj = ((CallbackRecord) (obj1)).next;
        recycleCallbackLocked(((CallbackRecord) (obj1)));
        obj1 = obj;
        if(obj != null) goto _L6; else goto _L5
_L5:
        Trace.traceEnd(8L);
        throw exception1;
        Exception exception;
        exception;
        throw exception;
    }

    void doFrame(long l, int i)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag = mFrameScheduled;
        if(flag)
            break MISSING_BLOCK_LABEL_24;
        obj;
        JVM INSTR monitorexit ;
        return;
        long l1 = System.nanoTime();
        long l2;
        long l3;
        l2 = l1 - l;
        l3 = l;
        if(l2 >= mFrameIntervalNanos)
        {
            l3 = l2 / mFrameIntervalNanos;
            if(l3 >= (long)SKIPPED_FRAME_WARNING_LIMIT)
            {
                StringBuilder stringbuilder = JVM INSTR new #323 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Log.i("Choreographer", stringbuilder.append("Skipped ").append(l3).append(" frames!  ").append("The application may be doing too much work on its main thread.").toString());
                EventLog.writeEvent(30089, l3);
            }
            l3 = l1 - l2 % mFrameIntervalNanos;
        }
        if(l3 >= mLastFrameTimeNanos)
            break MISSING_BLOCK_LABEL_150;
        scheduleVsyncLocked();
        obj;
        JVM INSTR monitorexit ;
        return;
        mFrameInfo.setVsync(l, l3);
        mFrameScheduled = false;
        mLastFrameTimeNanos = l3;
        obj;
        JVM INSTR monitorexit ;
        Trace.traceBegin(8L, "Choreographer#doFrame");
        AnimationUtils.lockAnimationClock(l3 / 0xf4240L);
        mFrameInfo.markInputHandlingStart();
        doCallbacks(0, l3);
        mFrameInfo.markAnimationsStart();
        doCallbacks(1, l3);
        mFrameInfo.markPerformTraversalsStart();
        doCallbacks(2, l3);
        doCallbacks(3, l3);
        AnimationUtils.unlockAnimationClock();
        Trace.traceEnd(8L);
        mNeedAdvancedSf = false;
        return;
        Exception exception1;
        exception1;
        throw exception1;
        Exception exception;
        exception;
        AnimationUtils.unlockAnimationClock();
        Trace.traceEnd(8L);
        throw exception;
    }

    void doScheduleCallback(int i)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(!mFrameScheduled)
        {
            long l = SystemClock.uptimeMillis();
            if(mCallbackQueues[i].hasDueCallbacksLocked(l))
                scheduleFrameLocked(l);
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void doScheduleVsync()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mFrameScheduled)
            scheduleVsyncLocked();
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void dump(String s, PrintWriter printwriter)
    {
        String s1 = (new StringBuilder()).append(s).append("  ").toString();
        printwriter.print(s);
        printwriter.println("Choreographer:");
        printwriter.print(s1);
        printwriter.print("mFrameScheduled=");
        printwriter.println(mFrameScheduled);
        printwriter.print(s1);
        printwriter.print("mLastFrameTime=");
        printwriter.println(TimeUtils.formatUptime(mLastFrameTimeNanos / 0xf4240L));
    }

    public long getFrameIntervalNanos()
    {
        return mFrameIntervalNanos;
    }

    public long getFrameTime()
    {
        return getFrameTimeNanos() / 0xf4240L;
    }

    public long getFrameTimeNanos()
    {
        synchronized(mLock)
        {
            if(!mCallbacksRunning)
            {
                IllegalStateException illegalstateexception = JVM INSTR new #496 <Class IllegalStateException>;
                illegalstateexception.IllegalStateException("This method must only be called as part of a callback while a frame is in progress.");
                throw illegalstateexception;
            }
            break MISSING_BLOCK_LABEL_32;
        }
        long l;
        if(!USE_FRAME_TIME)
            break MISSING_BLOCK_LABEL_47;
        l = mLastFrameTimeNanos;
_L1:
        obj;
        JVM INSTR monitorexit ;
        return l;
        l = System.nanoTime();
          goto _L1
    }

    public long getLastFrameTimeNanos()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        long l;
        if(!USE_FRAME_TIME)
            break MISSING_BLOCK_LABEL_22;
        l = mLastFrameTimeNanos;
_L1:
        obj;
        JVM INSTR monitorexit ;
        return l;
        l = System.nanoTime();
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    public boolean needAdvancedSf()
    {
        return mNeedAdvancedSf;
    }

    public void postCallback(int i, Runnable runnable, Object obj)
    {
        postCallbackDelayed(i, runnable, obj, 0L);
    }

    public void postCallbackDelayed(int i, Runnable runnable, Object obj, long l)
    {
        if(runnable == null)
            throw new IllegalArgumentException("action must not be null");
        if(i < 0 || i > 3)
        {
            throw new IllegalArgumentException("callbackType is invalid");
        } else
        {
            postCallbackDelayedInternal(i, runnable, obj, l);
            return;
        }
    }

    public void postFrameCallback(FrameCallback framecallback)
    {
        postFrameCallbackDelayed(framecallback, 0L);
    }

    public void postFrameCallbackDelayed(FrameCallback framecallback, long l)
    {
        if(framecallback == null)
        {
            throw new IllegalArgumentException("callback must not be null");
        } else
        {
            postCallbackDelayedInternal(1, framecallback, FRAME_CALLBACK_TOKEN, l);
            return;
        }
    }

    public void removeCallbacks(int i, Runnable runnable, Object obj)
    {
        if(i < 0 || i > 3)
        {
            throw new IllegalArgumentException("callbackType is invalid");
        } else
        {
            removeCallbacksInternal(i, runnable, obj);
            return;
        }
    }

    public void removeFrameCallback(FrameCallback framecallback)
    {
        if(framecallback == null)
        {
            throw new IllegalArgumentException("callback must not be null");
        } else
        {
            removeCallbacksInternal(1, framecallback, FRAME_CALLBACK_TOKEN);
            return;
        }
    }

    public void setMotionEventInfo(int i, int j)
    {
        this;
        JVM INSTR monitorenter ;
        mTouchMoveNum = j;
        mMotionEventType = i;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setMotionEventInfo_MIUI(int i)
    {
        if(!MIUI_OPTS_INPUT) goto _L2; else goto _L1
_L1:
        if(i != 0) goto _L4; else goto _L3
_L3:
        mPerformedTraversal = false;
_L2:
        return;
_L4:
        if(i == 2)
        {
            if(mPerformedTraversal)
            {
                Log.w("Choreographer", "OPTS_INPUT: First frame was drawed before optimized, so skip!");
                return;
            }
            mFrameScheduled = true;
            Message message = mHandler.obtainMessage(0);
            message.setAsynchronous(true);
            mHandler.sendMessageAtFrontOfQueue(message);
            mNeedAdvancedSf = true;
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    public static final int CALLBACK_ANIMATION = 1;
    public static final int CALLBACK_COMMIT = 3;
    public static final int CALLBACK_INPUT = 0;
    private static final int CALLBACK_LAST = 3;
    private static final String CALLBACK_TRACE_TITLES[] = {
        "input", "animation", "traversal", "commit"
    };
    public static final int CALLBACK_TRAVERSAL = 2;
    private static final boolean DEBUG_FRAMES = false;
    private static final boolean DEBUG_JANK = false;
    private static final long DEFAULT_FRAME_DELAY = 10L;
    private static final Object FRAME_CALLBACK_TOKEN = new Object() {

        public String toString()
        {
            return "FRAME_CALLBACK_TOKEN";
        }

    }
;
    private static final boolean MIUI_OPTS_INPUT = SystemProperties.getBoolean("persist.sys.enable_inputopts", true);
    private static final int MOTION_EVENT_ACTION_CANCEL = 3;
    private static final int MOTION_EVENT_ACTION_DOWN = 0;
    private static final int MOTION_EVENT_ACTION_MOVE = 2;
    private static final int MOTION_EVENT_ACTION_UP = 1;
    private static final int MSG_DO_FRAME = 0;
    private static final int MSG_DO_SCHEDULE_CALLBACK = 2;
    private static final int MSG_DO_SCHEDULE_VSYNC = 1;
    private static final boolean OPTS_INPUT = SystemProperties.getBoolean("persist.vendor.qti.inputopts.enable", false);
    private static final int SKIPPED_FRAME_WARNING_LIMIT = SystemProperties.getInt("debug.choreographer.skipwarning", 30);
    private static final String TAG = "Choreographer";
    private static final boolean USE_FRAME_TIME = SystemProperties.getBoolean("debug.choreographer.frametime", true);
    private static final boolean USE_VSYNC = SystemProperties.getBoolean("debug.choreographer.vsync", true);
    private static volatile long sFrameDelay = 10L;
    private static final ThreadLocal sSfThreadInstance = new ThreadLocal() {

        protected Choreographer initialValue()
        {
            Looper looper = Looper.myLooper();
            if(looper == null)
                throw new IllegalStateException("The current thread must have a looper!");
            else
                return new Choreographer(looper, 1, null);
        }

        protected volatile Object initialValue()
        {
            return initialValue();
        }

    }
;
    private static final ThreadLocal sThreadInstance = new ThreadLocal() {

        protected Choreographer initialValue()
        {
            Looper looper = Looper.myLooper();
            if(looper == null)
                throw new IllegalStateException("The current thread must have a looper!");
            else
                return new Choreographer(looper, 0, null);
        }

        protected volatile Object initialValue()
        {
            return initialValue();
        }

    }
;
    private CallbackRecord mCallbackPool;
    private final CallbackQueue mCallbackQueues[];
    private boolean mCallbacksRunning;
    private boolean mConsumedDown;
    private boolean mConsumedMove;
    private boolean mDebugPrintNextFrameTimeDelta;
    private final FrameDisplayEventReceiver mDisplayEventReceiver;
    FrameInfo mFrameInfo;
    private long mFrameIntervalNanos;
    private boolean mFrameScheduled;
    private final FrameHandler mHandler;
    private long mLastFrameTimeNanos;
    private final Object mLock;
    private final Looper mLooper;
    private int mMotionEventType;
    private boolean mNeedAdvancedSf;
    private boolean mPerformedTraversal;
    private int mTouchMoveNum;

}
