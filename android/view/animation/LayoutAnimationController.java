// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.animation;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import java.util.Random;

// Referenced classes of package android.view.animation:
//            Animation, LinearInterpolator, Interpolator, AnimationUtils

public class LayoutAnimationController
{
    public static class AnimationParameters
    {

        public int count;
        public int index;

        public AnimationParameters()
        {
        }
    }


    public LayoutAnimationController(Context context, AttributeSet attributeset)
    {
        attributeset = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.LayoutAnimation);
        mDelay = Animation.Description.parseValue(attributeset.peekValue(1)).value;
        mOrder = attributeset.getInt(3, 0);
        int i = attributeset.getResourceId(2, 0);
        if(i > 0)
            setAnimation(context, i);
        i = attributeset.getResourceId(0, 0);
        if(i > 0)
            setInterpolator(context, i);
        attributeset.recycle();
    }

    public LayoutAnimationController(Animation animation)
    {
        this(animation, 0.5F);
    }

    public LayoutAnimationController(Animation animation, float f)
    {
        mDelay = f;
        setAnimation(animation);
    }

    public Animation getAnimation()
    {
        return mAnimation;
    }

    public final Animation getAnimationForView(View view)
    {
        long l = getDelayForView(view) + mAnimation.getStartOffset();
        mMaxDelay = Math.max(mMaxDelay, l);
        try
        {
            view = mAnimation.clone();
            view.setStartOffset(l);
        }
        // Misplaced declaration of an exception variable
        catch(View view)
        {
            return null;
        }
        return view;
    }

    public float getDelay()
    {
        return mDelay;
    }

    protected long getDelayForView(View view)
    {
        view = view.getLayoutParams().layoutAnimationParameters;
        if(view == null)
            return 0L;
        float f = mDelay * (float)mAnimation.getDuration();
        long l = (long)((float)getTransformedIndex(view) * f);
        float f1 = f * (float)((AnimationParameters) (view)).count;
        if(mInterpolator == null)
            mInterpolator = new LinearInterpolator();
        f = (float)l / f1;
        return (long)(mInterpolator.getInterpolation(f) * f1);
    }

    public Interpolator getInterpolator()
    {
        return mInterpolator;
    }

    public int getOrder()
    {
        return mOrder;
    }

    protected int getTransformedIndex(AnimationParameters animationparameters)
    {
        switch(getOrder())
        {
        default:
            return animationparameters.index;

        case 1: // '\001'
            return animationparameters.count - 1 - animationparameters.index;

        case 2: // '\002'
            break;
        }
        if(mRandomizer == null)
            mRandomizer = new Random();
        return (int)((float)animationparameters.count * mRandomizer.nextFloat());
    }

    public boolean isDone()
    {
        boolean flag;
        if(AnimationUtils.currentAnimationTimeMillis() > mAnimation.getStartTime() + mMaxDelay + mDuration)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void setAnimation(Context context, int i)
    {
        setAnimation(AnimationUtils.loadAnimation(context, i));
    }

    public void setAnimation(Animation animation)
    {
        mAnimation = animation;
        mAnimation.setFillBefore(true);
    }

    public void setDelay(float f)
    {
        mDelay = f;
    }

    public void setInterpolator(Context context, int i)
    {
        setInterpolator(AnimationUtils.loadInterpolator(context, i));
    }

    public void setInterpolator(Interpolator interpolator)
    {
        mInterpolator = interpolator;
    }

    public void setOrder(int i)
    {
        mOrder = i;
    }

    public void start()
    {
        mDuration = mAnimation.getDuration();
        mMaxDelay = 0x8000000000000000L;
        mAnimation.setStartTime(-1L);
    }

    public boolean willOverlap()
    {
        boolean flag;
        if(mDelay < 1.0F)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static final int ORDER_NORMAL = 0;
    public static final int ORDER_RANDOM = 2;
    public static final int ORDER_REVERSE = 1;
    protected Animation mAnimation;
    private float mDelay;
    private long mDuration;
    protected Interpolator mInterpolator;
    private long mMaxDelay;
    private int mOrder;
    protected Random mRandomizer;
}
