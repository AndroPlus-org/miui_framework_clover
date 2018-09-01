// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.os.Looper;

// Referenced classes of package android.view:
//            InputEventReceiver, Choreographer, InputChannel

public class BatchedInputEventReceiver extends InputEventReceiver
{
    private final class BatchedInputRunnable
        implements Runnable
    {

        public void run()
        {
            doConsumeBatchedInput(mChoreographer.getFrameTimeNanos());
        }

        final BatchedInputEventReceiver this$0;

        private BatchedInputRunnable()
        {
            this$0 = BatchedInputEventReceiver.this;
            super();
        }

        BatchedInputRunnable(BatchedInputRunnable batchedinputrunnable)
        {
            this();
        }
    }


    public BatchedInputEventReceiver(InputChannel inputchannel, Looper looper, Choreographer choreographer)
    {
        super(inputchannel, looper);
        mChoreographer = choreographer;
    }

    private void scheduleBatchedInput()
    {
        if(!mBatchedInputScheduled)
        {
            mBatchedInputScheduled = true;
            mChoreographer.postCallback(0, mBatchedInputRunnable, null);
        }
    }

    private void unscheduleBatchedInput()
    {
        if(mBatchedInputScheduled)
        {
            mBatchedInputScheduled = false;
            mChoreographer.removeCallbacks(0, mBatchedInputRunnable, null);
        }
    }

    public void dispose()
    {
        unscheduleBatchedInput();
        super.dispose();
    }

    void doConsumeBatchedInput(long l)
    {
        if(mBatchedInputScheduled)
        {
            mBatchedInputScheduled = false;
            if(consumeBatchedInputEvents(l) && l != -1L)
                scheduleBatchedInput();
        }
    }

    public void onBatchedInputEventPending()
    {
        scheduleBatchedInput();
    }

    private final BatchedInputRunnable mBatchedInputRunnable = new BatchedInputRunnable(null);
    private boolean mBatchedInputScheduled;
    Choreographer mChoreographer;
}
