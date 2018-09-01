// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.animation;

import android.os.SystemClock;
import android.util.ArrayMap;
import android.view.Choreographer;
import java.util.ArrayList;

// Referenced classes of package android.animation:
//            ObjectAnimator, Animator

public class AnimationHandler
{
    static interface AnimationFrameCallback
    {

        public abstract void commitAnimationFrame(long l);

        public abstract boolean doAnimationFrame(long l);
    }

    public static interface AnimationFrameCallbackProvider
    {

        public abstract long getFrameDelay();

        public abstract long getFrameTime();

        public abstract void postCommitCallback(Runnable runnable);

        public abstract void postFrameCallback(android.view.Choreographer.FrameCallback framecallback);

        public abstract void setFrameDelay(long l);
    }

    private class MyFrameCallbackProvider
        implements AnimationFrameCallbackProvider
    {

        public long getFrameDelay()
        {
            return Choreographer.getFrameDelay();
        }

        public long getFrameTime()
        {
            return mChoreographer.getFrameTime();
        }

        public void postCommitCallback(Runnable runnable)
        {
            mChoreographer.postCallback(3, runnable, null);
        }

        public void postFrameCallback(android.view.Choreographer.FrameCallback framecallback)
        {
            mChoreographer.postFrameCallback(framecallback);
        }

        public void setFrameDelay(long l)
        {
            Choreographer.setFrameDelay(l);
        }

        final Choreographer mChoreographer;
        final AnimationHandler this$0;

        private MyFrameCallbackProvider()
        {
            this$0 = AnimationHandler.this;
            super();
            mChoreographer = Choreographer.getInstance();
        }

        MyFrameCallbackProvider(MyFrameCallbackProvider myframecallbackprovider)
        {
            this();
        }
    }


    static ArrayList _2D_get0(AnimationHandler animationhandler)
    {
        return animationhandler.mAnimationCallbacks;
    }

    static AnimationFrameCallbackProvider _2D_wrap0(AnimationHandler animationhandler)
    {
        return animationhandler.getProvider();
    }

    static void _2D_wrap1(AnimationHandler animationhandler, AnimationFrameCallback animationframecallback, long l)
    {
        animationhandler.commitAnimationFrame(animationframecallback, l);
    }

    static void _2D_wrap2(AnimationHandler animationhandler, long l)
    {
        animationhandler.doAnimationFrame(l);
    }

    public AnimationHandler()
    {
        mListDirty = false;
    }

    private void cleanUpList()
    {
        if(mListDirty)
        {
            for(int i = mAnimationCallbacks.size() - 1; i >= 0; i--)
                if(mAnimationCallbacks.get(i) == null)
                    mAnimationCallbacks.remove(i);

            mListDirty = false;
        }
    }

    private void commitAnimationFrame(AnimationFrameCallback animationframecallback, long l)
    {
        if(!mDelayedCallbackStartTime.containsKey(animationframecallback) && mCommitCallbacks.contains(animationframecallback))
        {
            animationframecallback.commitAnimationFrame(l);
            mCommitCallbacks.remove(animationframecallback);
        }
    }

    private void doAnimationFrame(long l)
    {
        long l1 = SystemClock.uptimeMillis();
        int i = mAnimationCallbacks.size();
        int j = 0;
        do
        {
            if(j >= i)
                break;
            final AnimationFrameCallback callback = (AnimationFrameCallback)mAnimationCallbacks.get(j);
            if(callback != null && isCallbackDue(callback, l1))
            {
                callback.doAnimationFrame(l);
                if(mCommitCallbacks.contains(callback))
                    getProvider().postCommitCallback(new Runnable() {

                        public void run()
                        {
                            AnimationHandler._2D_wrap1(AnimationHandler.this, callback, AnimationHandler._2D_wrap0(AnimationHandler.this).getFrameTime());
                        }

                        final AnimationHandler this$0;
                        final AnimationFrameCallback val$callback;

            
            {
                this$0 = AnimationHandler.this;
                callback = animationframecallback;
                super();
            }
                    }
);
            }
            j++;
        } while(true);
        cleanUpList();
    }

    public static int getAnimationCount()
    {
        AnimationHandler animationhandler = (AnimationHandler)sAnimatorHandler.get();
        if(animationhandler == null)
            return 0;
        else
            return animationhandler.getCallbackSize();
    }

    private int getCallbackSize()
    {
        int i = 0;
        for(int j = mAnimationCallbacks.size() - 1; j >= 0;)
        {
            int k = i;
            if(mAnimationCallbacks.get(j) != null)
                k = i + 1;
            j--;
            i = k;
        }

        return i;
    }

    public static long getFrameDelay()
    {
        return getInstance().getProvider().getFrameDelay();
    }

    public static AnimationHandler getInstance()
    {
        if(sAnimatorHandler.get() == null)
            sAnimatorHandler.set(new AnimationHandler());
        return (AnimationHandler)sAnimatorHandler.get();
    }

    private AnimationFrameCallbackProvider getProvider()
    {
        if(mProvider == null)
            mProvider = new MyFrameCallbackProvider(null);
        return mProvider;
    }

    private boolean isCallbackDue(AnimationFrameCallback animationframecallback, long l)
    {
        Long long1 = (Long)mDelayedCallbackStartTime.get(animationframecallback);
        if(long1 == null)
            return true;
        if(long1.longValue() < l)
        {
            mDelayedCallbackStartTime.remove(animationframecallback);
            return true;
        } else
        {
            return false;
        }
    }

    public static void setFrameDelay(long l)
    {
        getInstance().getProvider().setFrameDelay(l);
    }

    public void addAnimationFrameCallback(AnimationFrameCallback animationframecallback, long l)
    {
        if(mAnimationCallbacks.size() == 0)
            getProvider().postFrameCallback(mFrameCallback);
        if(!mAnimationCallbacks.contains(animationframecallback))
            mAnimationCallbacks.add(animationframecallback);
        if(l > 0L)
            mDelayedCallbackStartTime.put(animationframecallback, Long.valueOf(SystemClock.uptimeMillis() + l));
    }

    public void addOneShotCommitCallback(AnimationFrameCallback animationframecallback)
    {
        if(!mCommitCallbacks.contains(animationframecallback))
            mCommitCallbacks.add(animationframecallback);
    }

    void autoCancelBasedOn(ObjectAnimator objectanimator)
    {
        int i = mAnimationCallbacks.size() - 1;
        do
        {
            if(i < 0)
                break;
            AnimationFrameCallback animationframecallback = (AnimationFrameCallback)mAnimationCallbacks.get(i);
            if(animationframecallback != null && objectanimator.shouldAutoCancel(animationframecallback))
                ((Animator)mAnimationCallbacks.get(i)).cancel();
            i--;
        } while(true);
    }

    public void removeCallback(AnimationFrameCallback animationframecallback)
    {
        mCommitCallbacks.remove(animationframecallback);
        mDelayedCallbackStartTime.remove(animationframecallback);
        int i = mAnimationCallbacks.indexOf(animationframecallback);
        if(i >= 0)
        {
            mAnimationCallbacks.set(i, null);
            mListDirty = true;
        }
    }

    public void setProvider(AnimationFrameCallbackProvider animationframecallbackprovider)
    {
        if(animationframecallbackprovider == null)
            mProvider = new MyFrameCallbackProvider(null);
        else
            mProvider = animationframecallbackprovider;
    }

    public static final ThreadLocal sAnimatorHandler = new ThreadLocal();
    private final ArrayList mAnimationCallbacks = new ArrayList();
    private final ArrayList mCommitCallbacks = new ArrayList();
    private final ArrayMap mDelayedCallbackStartTime = new ArrayMap();
    private final android.view.Choreographer.FrameCallback mFrameCallback = new android.view.Choreographer.FrameCallback() {

        public void doFrame(long l)
        {
            AnimationHandler._2D_wrap2(AnimationHandler.this, AnimationHandler._2D_wrap0(AnimationHandler.this).getFrameTime());
            if(AnimationHandler._2D_get0(AnimationHandler.this).size() > 0)
                AnimationHandler._2D_wrap0(AnimationHandler.this).postFrameCallback(this);
        }

        final AnimationHandler this$0;

            
            {
                this$0 = AnimationHandler.this;
                super();
            }
    }
;
    private boolean mListDirty;
    private AnimationFrameCallbackProvider mProvider;

}
