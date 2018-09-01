// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.view.animation.*;
import miui.os.Environment;

public class EdgeEffect
{

    public EdgeEffect(Context context)
    {
        mState = 0;
        mDisplacement = 0.5F;
        mTargetDisplacement = 0.5F;
        mPaint.setAntiAlias(true);
        TypedArray typedarray = context.obtainStyledAttributes(com.android.internal.R.styleable.EdgeEffect);
        int i = typedarray.getColor(0, 0xff666666);
        typedarray.recycle();
        mPaint.setColor(0xffffff & i | 0x33000000);
        mPaint.setStyle(android.graphics.Paint.Style.FILL);
        mPaint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.SRC_ATOP));
        mIsUsingMiui = Environment.isUsingMiui(context);
    }

    private void update()
    {
        float f;
        f = Math.min((float)(AnimationUtils.currentAnimationTimeMillis() - mStartTime) / mDuration, 1.0F);
        float f1 = mInterpolator.getInterpolation(f);
        mGlowAlpha = mGlowAlphaStart + (mGlowAlphaFinish - mGlowAlphaStart) * f1;
        mGlowScaleY = mGlowScaleYStart + (mGlowScaleYFinish - mGlowScaleYStart) * f1;
        mDisplacement = (mDisplacement + mTargetDisplacement) / 2.0F;
        if(f < 0.999F) goto _L2; else goto _L1
_L1:
        mState;
        JVM INSTR tableswitch 1 4: default 128
    //                   1 176
    //                   2 129
    //                   3 231
    //                   4 223;
           goto _L2 _L3 _L4 _L5 _L6
_L2:
        return;
_L4:
        mState = 3;
        mStartTime = AnimationUtils.currentAnimationTimeMillis();
        mDuration = 600F;
        mGlowAlphaStart = mGlowAlpha;
        mGlowScaleYStart = mGlowScaleY;
        mGlowAlphaFinish = 0.0F;
        mGlowScaleYFinish = 0.0F;
        continue; /* Loop/switch isn't completed */
_L3:
        mState = 4;
        mStartTime = AnimationUtils.currentAnimationTimeMillis();
        mDuration = 2000F;
        mGlowAlphaStart = mGlowAlpha;
        mGlowScaleYStart = mGlowScaleY;
        mGlowAlphaFinish = 0.0F;
        mGlowScaleYFinish = 0.0F;
        continue; /* Loop/switch isn't completed */
_L6:
        mState = 3;
        continue; /* Loop/switch isn't completed */
_L5:
        mState = 0;
        if(true) goto _L2; else goto _L7
_L7:
    }

    public boolean draw(Canvas canvas)
    {
        if(mIsUsingMiui)
            return false;
        update();
        int i = canvas.save();
        float f = mBounds.centerX();
        float f1 = mBounds.height();
        float f2 = mRadius;
        canvas.scale(1.0F, Math.min(mGlowScaleY, 1.0F) * mBaseGlowScale, f, 0.0F);
        float f3 = Math.max(0.0F, Math.min(mDisplacement, 1.0F));
        f3 = ((float)mBounds.width() * (f3 - 0.5F)) / 2.0F;
        canvas.clipRect(mBounds);
        canvas.translate(f3, 0.0F);
        mPaint.setAlpha((int)(mGlowAlpha * 255F));
        canvas.drawCircle(f, f1 - f2, mRadius, mPaint);
        canvas.restoreToCount(i);
        boolean flag = false;
        boolean flag1 = flag;
        if(mState == 3)
        {
            flag1 = flag;
            if(mGlowScaleY == 0.0F)
            {
                mState = 0;
                flag1 = true;
            }
        }
        if(mState != 0)
            flag1 = true;
        return flag1;
    }

    public void finish()
    {
        mState = 0;
    }

    public int getColor()
    {
        return mPaint.getColor();
    }

    public int getMaxHeight()
    {
        return (int)((float)mBounds.height() * 2.0F + 0.5F);
    }

    public boolean isFinished()
    {
        boolean flag = false;
        if(mState == 0)
            flag = true;
        return flag;
    }

    public void onAbsorb(int i)
    {
        mState = 2;
        i = Math.min(Math.max(100, Math.abs(i)), 10000);
        mStartTime = AnimationUtils.currentAnimationTimeMillis();
        mDuration = (float)i * 0.02F + 0.15F;
        mGlowAlphaStart = 0.09F;
        mGlowScaleYStart = Math.max(mGlowScaleY, 0.0F);
        mGlowScaleYFinish = Math.min(((float)((i / 100) * i) * 0.00015F) / 2.0F + 0.025F, 1.0F);
        mGlowAlphaFinish = Math.max(mGlowAlphaStart, Math.min((float)(i * 6) * 1E-005F, 0.15F));
        mTargetDisplacement = 0.5F;
    }

    public void onPull(float f)
    {
        onPull(f, 0.5F);
    }

    public void onPull(float f, float f1)
    {
        long l = AnimationUtils.currentAnimationTimeMillis();
        mTargetDisplacement = f1;
        if(mState == 4 && (float)(l - mStartTime) < mDuration)
            return;
        if(mState != 1)
            mGlowScaleY = Math.max(0.0F, mGlowScaleY);
        mState = 1;
        mStartTime = l;
        mDuration = 167F;
        mPullDistance = mPullDistance + f;
        f = Math.abs(f);
        f = Math.min(0.15F, mGlowAlpha + 0.8F * f);
        mGlowAlphaStart = f;
        mGlowAlpha = f;
        if(mPullDistance == 0.0F)
        {
            mGlowScaleYStart = 0.0F;
            mGlowScaleY = 0.0F;
        } else
        {
            f = (float)(Math.max(0.0D, 1.0D - 1.0D / Math.sqrt(Math.abs(mPullDistance) * (float)mBounds.height()) - 0.29999999999999999D) / 0.69999999999999996D);
            mGlowScaleYStart = f;
            mGlowScaleY = f;
        }
        mGlowAlphaFinish = mGlowAlpha;
        mGlowScaleYFinish = mGlowScaleY;
    }

    public void onRelease()
    {
        mPullDistance = 0.0F;
        if(mState != 1 && mState != 4)
        {
            return;
        } else
        {
            mState = 3;
            mGlowAlphaStart = mGlowAlpha;
            mGlowScaleYStart = mGlowScaleY;
            mGlowAlphaFinish = 0.0F;
            mGlowScaleYFinish = 0.0F;
            mStartTime = AnimationUtils.currentAnimationTimeMillis();
            mDuration = 600F;
            return;
        }
    }

    public void setColor(int i)
    {
        mPaint.setColor(i);
    }

    public void setSize(int i, int j)
    {
        float f = 1.0F;
        float f1 = ((float)i * 0.6F) / SIN;
        float f2 = f1 - COS * f1;
        float f3 = ((float)j * 0.6F) / SIN;
        float f4 = COS;
        mRadius = f1;
        if(f2 > 0.0F)
            f = Math.min((f3 - f4 * f3) / f2, 1.0F);
        mBaseGlowScale = f;
        mBounds.set(mBounds.left, mBounds.top, i, (int)Math.min(j, f2));
    }

    private static final double ANGLE = 0.52359877559829882D;
    private static final float COS = (float)Math.cos(0.52359877559829882D);
    private static final float EPSILON = 0.001F;
    private static final float GLOW_ALPHA_START = 0.09F;
    private static final float MAX_ALPHA = 0.15F;
    private static final float MAX_GLOW_SCALE = 2F;
    private static final int MAX_VELOCITY = 10000;
    private static final int MIN_VELOCITY = 100;
    private static final int PULL_DECAY_TIME = 2000;
    private static final float PULL_DISTANCE_ALPHA_GLOW_FACTOR = 0.8F;
    private static final float PULL_GLOW_BEGIN = 0F;
    private static final int PULL_TIME = 167;
    private static final float RADIUS_FACTOR = 0.6F;
    private static final int RECEDE_TIME = 600;
    private static final float SIN = (float)Math.sin(0.52359877559829882D);
    private static final int STATE_ABSORB = 2;
    private static final int STATE_IDLE = 0;
    private static final int STATE_PULL = 1;
    private static final int STATE_PULL_DECAY = 4;
    private static final int STATE_RECEDE = 3;
    private static final String TAG = "EdgeEffect";
    private static final int VELOCITY_GLOW_FACTOR = 6;
    private float mBaseGlowScale;
    private final Rect mBounds = new Rect();
    private float mDisplacement;
    private float mDuration;
    private float mGlowAlpha;
    private float mGlowAlphaFinish;
    private float mGlowAlphaStart;
    private float mGlowScaleY;
    private float mGlowScaleYFinish;
    private float mGlowScaleYStart;
    private final Interpolator mInterpolator = new DecelerateInterpolator();
    boolean mIsUsingMiui;
    private final Paint mPaint = new Paint();
    private float mPullDistance;
    private float mRadius;
    private long mStartTime;
    private int mState;
    private float mTargetDisplacement;

}
