// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.SystemClock;

// Referenced classes of package android.graphics.drawable:
//            LayerDrawable, Drawable

public class TransitionDrawable extends LayerDrawable
    implements Drawable.Callback
{
    static class TransitionState extends LayerDrawable.LayerState
    {

        public int getChangingConfigurations()
        {
            return mChangingConfigurations;
        }

        public Drawable newDrawable()
        {
            return new TransitionDrawable(this, (Resources)null, null);
        }

        public Drawable newDrawable(Resources resources)
        {
            return new TransitionDrawable(this, resources, null);
        }

        TransitionState(TransitionState transitionstate, TransitionDrawable transitiondrawable, Resources resources)
        {
            super(transitionstate, transitiondrawable, resources);
        }
    }


    TransitionDrawable()
    {
        this(new TransitionState(null, null, null), (Resources)null);
    }

    private TransitionDrawable(TransitionState transitionstate, Resources resources)
    {
        super(transitionstate, resources);
        mTransitionState = 2;
        mAlpha = 0;
    }

    TransitionDrawable(TransitionState transitionstate, Resources resources, TransitionDrawable transitiondrawable)
    {
        this(transitionstate, resources);
    }

    private TransitionDrawable(TransitionState transitionstate, Drawable adrawable[])
    {
        super(adrawable, transitionstate);
        mTransitionState = 2;
        mAlpha = 0;
    }

    public TransitionDrawable(Drawable adrawable[])
    {
        this(new TransitionState(null, null, null), adrawable);
    }

    LayerDrawable.LayerState createConstantState(LayerDrawable.LayerState layerstate, Resources resources)
    {
        return new TransitionState((TransitionState)layerstate, this, resources);
    }

    public void draw(Canvas canvas)
    {
        boolean flag = true;
        mTransitionState;
        JVM INSTR tableswitch 0 1: default 28
    //                   0 91
    //                   1 108;
           goto _L1 _L2 _L3
_L1:
        int i;
        boolean flag1;
        LayerDrawable.ChildDrawable achilddrawable[];
        i = mAlpha;
        flag1 = mCrossFade;
        achilddrawable = mLayerState.mChildren;
        if(flag)
        {
            if(!flag1 || i == 0)
                achilddrawable[0].mDrawable.draw(canvas);
            if(i == 255)
                achilddrawable[1].mDrawable.draw(canvas);
            return;
        }
        break; /* Loop/switch isn't completed */
_L2:
        mStartTimeMillis = SystemClock.uptimeMillis();
        flag = false;
        mTransitionState = 1;
        continue; /* Loop/switch isn't completed */
_L3:
        if(mStartTimeMillis >= 0L)
        {
            float f = (float)(SystemClock.uptimeMillis() - mStartTimeMillis) / (float)mDuration;
            if(f >= 1.0F)
                flag = true;
            else
                flag = false;
            f = Math.min(f, 1.0F);
            mAlpha = (int)((float)mFrom + (float)(mTo - mFrom) * f);
        }
        continue; /* Loop/switch isn't completed */
        Drawable drawable = achilddrawable[0].mDrawable;
        if(flag1)
            drawable.setAlpha(255 - i);
        drawable.draw(canvas);
        if(flag1)
            drawable.setAlpha(255);
        if(i > 0)
        {
            Drawable drawable1 = achilddrawable[1].mDrawable;
            drawable1.setAlpha(i);
            drawable1.draw(canvas);
            drawable1.setAlpha(255);
        }
        if(!flag)
            invalidateSelf();
        return;
        if(true) goto _L1; else goto _L4
_L4:
    }

    public boolean isCrossFadeEnabled()
    {
        return mCrossFade;
    }

    public void resetTransition()
    {
        mAlpha = 0;
        mTransitionState = 2;
        invalidateSelf();
    }

    public void reverseTransition(int i)
    {
        char c = '\377';
        long l = SystemClock.uptimeMillis();
        if(l - mStartTimeMillis > (long)mDuration)
        {
            if(mTo == 0)
            {
                mFrom = 0;
                mTo = 255;
                mAlpha = 0;
                mReverse = false;
            } else
            {
                mFrom = 255;
                mTo = 0;
                mAlpha = 255;
                mReverse = true;
            }
            mOriginalDuration = i;
            mDuration = i;
            mTransitionState = 0;
            invalidateSelf();
            return;
        }
        mReverse = mReverse ^ true;
        mFrom = mAlpha;
        i = c;
        if(mReverse)
            i = 0;
        mTo = i;
        if(mReverse)
            l -= mStartTimeMillis;
        else
            l = (long)mOriginalDuration - (l - mStartTimeMillis);
        mDuration = (int)l;
        mTransitionState = 0;
    }

    public void setCrossFadeEnabled(boolean flag)
    {
        mCrossFade = flag;
    }

    public void showSecondLayer()
    {
        mAlpha = 255;
        mReverse = false;
        mTransitionState = 2;
        invalidateSelf();
    }

    public void startTransition(int i)
    {
        mFrom = 0;
        mTo = 255;
        mAlpha = 0;
        mOriginalDuration = i;
        mDuration = i;
        mReverse = false;
        mTransitionState = 0;
        invalidateSelf();
    }

    private static final int TRANSITION_NONE = 2;
    private static final int TRANSITION_RUNNING = 1;
    private static final int TRANSITION_STARTING = 0;
    private int mAlpha;
    private boolean mCrossFade;
    private int mDuration;
    private int mFrom;
    private int mOriginalDuration;
    private boolean mReverse;
    private long mStartTimeMillis;
    private int mTo;
    private int mTransitionState;
}
