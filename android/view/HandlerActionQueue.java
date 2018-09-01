// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.os.Handler;
import com.android.internal.util.GrowingArrayUtils;

public class HandlerActionQueue
{
    private static class HandlerAction
    {

        public boolean matches(Runnable runnable)
        {
            boolean flag;
            if(runnable == null && action == null)
                flag = true;
            else
            if(action != null)
                flag = action.equals(runnable);
            else
                flag = false;
            return flag;
        }

        final Runnable action;
        final long delay;

        public HandlerAction(Runnable runnable, long l)
        {
            action = runnable;
            delay = l;
        }
    }


    public HandlerActionQueue()
    {
    }

    public void executeActions(Handler handler)
    {
        this;
        JVM INSTR monitorenter ;
        HandlerAction ahandleraction[] = mActions;
        int i = 0;
        int j = mCount;
_L2:
        HandlerAction handleraction;
        if(i >= j)
            break; /* Loop/switch isn't completed */
        handleraction = ahandleraction[i];
        handler.postDelayed(handleraction.action, handleraction.delay);
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        mActions = null;
        mCount = 0;
        this;
        JVM INSTR monitorexit ;
        return;
        handler;
        throw handler;
    }

    public long getDelay(int i)
    {
        if(i >= mCount)
            throw new IndexOutOfBoundsException();
        else
            return mActions[i].delay;
    }

    public Runnable getRunnable(int i)
    {
        if(i >= mCount)
            throw new IndexOutOfBoundsException();
        else
            return mActions[i].action;
    }

    public void post(Runnable runnable)
    {
        postDelayed(runnable, 0L);
    }

    public void postDelayed(Runnable runnable, long l)
    {
        runnable = new HandlerAction(runnable, l);
        this;
        JVM INSTR monitorenter ;
        if(mActions == null)
            mActions = new HandlerAction[4];
        mActions = (HandlerAction[])GrowingArrayUtils.append(mActions, mCount, runnable);
        mCount = mCount + 1;
        this;
        JVM INSTR monitorexit ;
        return;
        runnable;
        throw runnable;
    }

    public void removeCallbacks(Runnable runnable)
    {
        this;
        JVM INSTR monitorenter ;
        int i = mCount;
        int j = 0;
        HandlerAction ahandleraction[] = mActions;
        int k = 0;
_L3:
        if(k >= i) goto _L2; else goto _L1
_L1:
        if(!ahandleraction[k].matches(runnable))
        {
            if(j != k)
                ahandleraction[j] = ahandleraction[k];
            j++;
        }
        k++;
          goto _L3
_L2:
        mCount = j;
        for(; j < i; j++)
            ahandleraction[j] = null;

        this;
        JVM INSTR monitorexit ;
        return;
        runnable;
        throw runnable;
    }

    public int size()
    {
        return mCount;
    }

    private HandlerAction mActions[];
    private int mCount;
}
