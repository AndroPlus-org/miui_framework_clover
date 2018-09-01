// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            Handler, SystemClock, Message

public abstract class CountDownTimer
{

    static boolean _2D_get0(CountDownTimer countdowntimer)
    {
        return countdowntimer.mCancelled;
    }

    static long _2D_get1(CountDownTimer countdowntimer)
    {
        return countdowntimer.mCountdownInterval;
    }

    static long _2D_get2(CountDownTimer countdowntimer)
    {
        return countdowntimer.mStopTimeInFuture;
    }

    public CountDownTimer(long l, long l1)
    {
        mCancelled = false;
        mHandler = new Handler() {

            public void handleMessage(Message message)
            {
                message = CountDownTimer.this;
                message;
                JVM INSTR monitorenter ;
                boolean flag = CountDownTimer._2D_get0(CountDownTimer.this);
                if(!flag)
                    break MISSING_BLOCK_LABEL_22;
                message;
                JVM INSTR monitorexit ;
                return;
                long l2 = CountDownTimer._2D_get2(CountDownTimer.this) - SystemClock.elapsedRealtime();
                if(l2 > 0L) goto _L2; else goto _L1
_L1:
                onFinish();
_L5:
                message;
                JVM INSTR monitorexit ;
                return;
_L2:
                long l3;
                l3 = SystemClock.elapsedRealtime();
                onTick(l2);
                l3 = SystemClock.elapsedRealtime() - l3;
                if(l2 >= CountDownTimer._2D_get1(CountDownTimer.this)) goto _L4; else goto _L3
_L3:
                l2 -= l3;
                l3 = l2;
                if(l2 < 0L)
                    l3 = 0L;
_L7:
                sendMessageDelayed(obtainMessage(1), l3);
                  goto _L5
                Exception exception;
                exception;
                throw exception;
_L4:
                l2 = CountDownTimer._2D_get1(CountDownTimer.this) - l3;
_L8:
                l3 = l2;
                if(l2 >= 0L) goto _L7; else goto _L6
_L6:
                l3 = CountDownTimer._2D_get1(CountDownTimer.this);
                l2 += l3;
                  goto _L8
            }

            final CountDownTimer this$0;

            
            {
                this$0 = CountDownTimer.this;
                super();
            }
        }
;
        mMillisInFuture = l;
        mCountdownInterval = l1;
    }

    public final void cancel()
    {
        this;
        JVM INSTR monitorenter ;
        mCancelled = true;
        mHandler.removeMessages(1);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public abstract void onFinish();

    public abstract void onTick(long l);

    public final CountDownTimer start()
    {
        this;
        JVM INSTR monitorenter ;
        mCancelled = false;
        if(mMillisInFuture > 0L)
            break MISSING_BLOCK_LABEL_24;
        onFinish();
        this;
        JVM INSTR monitorexit ;
        return this;
        mStopTimeInFuture = SystemClock.elapsedRealtime() + mMillisInFuture;
        mHandler.sendMessage(mHandler.obtainMessage(1));
        this;
        JVM INSTR monitorexit ;
        return this;
        Exception exception;
        exception;
        throw exception;
    }

    private static final int MSG = 1;
    private boolean mCancelled;
    private final long mCountdownInterval;
    private Handler mHandler;
    private final long mMillisInFuture;
    private long mStopTimeInFuture;
}
