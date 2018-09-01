// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.drawable;

import android.animation.*;
import android.graphics.*;
import android.util.FloatProperty;
import android.util.MathUtils;
import android.view.DisplayListCanvas;
import android.view.RenderNodeAnimator;
import android.view.animation.LinearInterpolator;

// Referenced classes of package android.graphics.drawable:
//            RippleComponent, RippleDrawable

class RippleForeground extends RippleComponent
{
    private static final class LogDecelerateInterpolator
        implements TimeInterpolator
    {

        private float computeLog(float f)
        {
            return (1.0F - (float)Math.pow(mBase, -f * mTimeScale)) + mDrift * f;
        }

        public float getInterpolation(float f)
        {
            return computeLog(f) * mOutputScale;
        }

        private final float mBase;
        private final float mDrift;
        private final float mOutputScale = 1.0F / computeLog(1.0F);
        private final float mTimeScale;

        public LogDecelerateInterpolator(float f, float f1, float f2)
        {
            mBase = f;
            mDrift = f2;
            mTimeScale = 1.0F / f1;
        }
    }


    static float _2D_get0(RippleForeground rippleforeground)
    {
        return rippleforeground.mOpacity;
    }

    static float _2D_get1(RippleForeground rippleforeground)
    {
        return rippleforeground.mTweenRadius;
    }

    static float _2D_get2(RippleForeground rippleforeground)
    {
        return rippleforeground.mTweenX;
    }

    static boolean _2D_set0(RippleForeground rippleforeground, boolean flag)
    {
        rippleforeground.mHasFinishedExit = flag;
        return flag;
    }

    static float _2D_set1(RippleForeground rippleforeground, float f)
    {
        rippleforeground.mOpacity = f;
        return f;
    }

    static float _2D_set2(RippleForeground rippleforeground, float f)
    {
        rippleforeground.mTweenRadius = f;
        return f;
    }

    static float _2D_set3(RippleForeground rippleforeground, float f)
    {
        rippleforeground.mTweenX = f;
        return f;
    }

    static float _2D_set4(RippleForeground rippleforeground, float f)
    {
        rippleforeground.mTweenY = f;
        return f;
    }

    public RippleForeground(RippleDrawable rippledrawable, Rect rect, float f, float f1, boolean flag, boolean flag1)
    {
        super(rippledrawable, rect, flag1);
        mTargetX = 0.0F;
        mTargetY = 0.0F;
        mBoundedRadius = 0.0F;
        mOpacity = 1.0F;
        mTweenRadius = 0.0F;
        mTweenX = 0.0F;
        mTweenY = 0.0F;
        mIsBounded = flag;
        mStartingX = f;
        mStartingY = f1;
        if(flag)
            mBoundedRadius = (float)(Math.random() * 350D * 0.10000000000000001D) + 315F;
        else
            mBoundedRadius = 0.0F;
    }

    private void clampStartingPosition()
    {
        float f = mBounds.exactCenterX();
        float f1 = mBounds.exactCenterY();
        float f2 = mStartingX - f;
        float f3 = mStartingY - f1;
        float f4 = mTargetRadius;
        if(f2 * f2 + f3 * f3 > f4 * f4)
        {
            double d = Math.atan2(f3, f2);
            mClampedStartingX = (float)(Math.cos(d) * (double)f4) + f;
            mClampedStartingY = (float)(Math.sin(d) * (double)f4) + f1;
        } else
        {
            mClampedStartingX = mStartingX;
            mClampedStartingY = mStartingY;
        }
    }

    private void computeBoundedTargetValues()
    {
        mTargetX = (mClampedStartingX - mBounds.exactCenterX()) * 0.7F;
        mTargetY = (mClampedStartingY - mBounds.exactCenterY()) * 0.7F;
        mTargetRadius = mBoundedRadius;
    }

    private float getCurrentRadius()
    {
        return MathUtils.lerp(0.0F, mTargetRadius, mTweenRadius);
    }

    private float getCurrentX()
    {
        return MathUtils.lerp(mClampedStartingX - mBounds.exactCenterX(), mTargetX, mTweenX);
    }

    private float getCurrentY()
    {
        return MathUtils.lerp(mClampedStartingY - mBounds.exactCenterY(), mTargetY, mTweenY);
    }

    private int getOpacityExitDuration()
    {
        return (int)((mOpacity * 1000F) / 3F + 0.5F);
    }

    private int getRadiusExitDuration()
    {
        return (int)(Math.sqrt(((mTargetRadius - getCurrentRadius()) / 4424F) * mDensityScale) * 1000D + 0.5D);
    }

    protected RippleComponent.RenderNodeAnimatorSet createHardwareExit(Paint paint)
    {
        int i;
        int j;
        int k;
        float f;
        float f1;
        float f2;
        RenderNodeAnimator rendernodeanimator;
        RenderNodeAnimator rendernodeanimator1;
        RenderNodeAnimator rendernodeanimator2;
        RippleComponent.RenderNodeAnimatorSet rendernodeanimatorset;
        if(mIsBounded)
        {
            computeBoundedTargetValues();
            i = 800;
            j = 300;
            k = 400;
        } else
        {
            i = getRadiusExitDuration();
            j = i;
            k = getOpacityExitDuration();
        }
        f = getCurrentX();
        f1 = getCurrentY();
        f2 = getCurrentRadius();
        paint.setAlpha((int)((float)paint.getAlpha() * mOpacity + 0.5F));
        mPropPaint = CanvasProperty.createPaint(paint);
        mPropRadius = CanvasProperty.createFloat(f2);
        mPropX = CanvasProperty.createFloat(f);
        mPropY = CanvasProperty.createFloat(f1);
        paint = new RenderNodeAnimator(mPropRadius, mTargetRadius);
        paint.setDuration(i);
        paint.setInterpolator(DECELERATE_INTERPOLATOR);
        rendernodeanimator = new RenderNodeAnimator(mPropX, mTargetX);
        rendernodeanimator.setDuration(j);
        rendernodeanimator.setInterpolator(DECELERATE_INTERPOLATOR);
        rendernodeanimator1 = new RenderNodeAnimator(mPropY, mTargetY);
        rendernodeanimator1.setDuration(j);
        rendernodeanimator1.setInterpolator(DECELERATE_INTERPOLATOR);
        rendernodeanimator2 = new RenderNodeAnimator(mPropPaint, 1, 0.0F);
        rendernodeanimator2.setDuration(k);
        rendernodeanimator2.setInterpolator(LINEAR_INTERPOLATOR);
        rendernodeanimator2.addListener(mAnimationListener);
        rendernodeanimatorset = new RippleComponent.RenderNodeAnimatorSet();
        rendernodeanimatorset.add(paint);
        rendernodeanimatorset.add(rendernodeanimator2);
        rendernodeanimatorset.add(rendernodeanimator);
        rendernodeanimatorset.add(rendernodeanimator1);
        return rendernodeanimatorset;
    }

    protected Animator createSoftwareEnter(boolean flag)
    {
        if(mIsBounded)
        {
            return null;
        } else
        {
            int i = (int)(Math.sqrt((mTargetRadius / 1024F) * mDensityScale) * 1000D + 0.5D);
            ObjectAnimator objectanimator = ObjectAnimator.ofFloat(this, TWEEN_RADIUS, new float[] {
                1.0F
            });
            objectanimator.setAutoCancel(true);
            objectanimator.setDuration(i);
            objectanimator.setInterpolator(LINEAR_INTERPOLATOR);
            objectanimator.setStartDelay(80L);
            ObjectAnimator objectanimator1 = ObjectAnimator.ofFloat(this, TWEEN_ORIGIN, new float[] {
                1.0F
            });
            objectanimator1.setAutoCancel(true);
            objectanimator1.setDuration(i);
            objectanimator1.setInterpolator(LINEAR_INTERPOLATOR);
            objectanimator1.setStartDelay(80L);
            ObjectAnimator objectanimator2 = ObjectAnimator.ofFloat(this, OPACITY, new float[] {
                1.0F
            });
            objectanimator2.setAutoCancel(true);
            objectanimator2.setDuration(120L);
            objectanimator2.setInterpolator(LINEAR_INTERPOLATOR);
            AnimatorSet animatorset = new AnimatorSet();
            animatorset.play(objectanimator1).with(objectanimator).with(objectanimator2);
            return animatorset;
        }
    }

    protected Animator createSoftwareExit()
    {
        int i;
        int j;
        int k;
        ObjectAnimator objectanimator;
        ObjectAnimator objectanimator1;
        ObjectAnimator objectanimator2;
        AnimatorSet animatorset;
        if(mIsBounded)
        {
            computeBoundedTargetValues();
            i = 800;
            j = 300;
            k = 400;
        } else
        {
            i = getRadiusExitDuration();
            j = i;
            k = getOpacityExitDuration();
        }
        objectanimator = ObjectAnimator.ofFloat(this, TWEEN_RADIUS, new float[] {
            1.0F
        });
        objectanimator.setAutoCancel(true);
        objectanimator.setDuration(i);
        objectanimator.setInterpolator(DECELERATE_INTERPOLATOR);
        objectanimator1 = ObjectAnimator.ofFloat(this, TWEEN_ORIGIN, new float[] {
            1.0F
        });
        objectanimator1.setAutoCancel(true);
        objectanimator1.setDuration(j);
        objectanimator1.setInterpolator(DECELERATE_INTERPOLATOR);
        objectanimator2 = ObjectAnimator.ofFloat(this, OPACITY, new float[] {
            0.0F
        });
        objectanimator2.setAutoCancel(true);
        objectanimator2.setDuration(k);
        objectanimator2.setInterpolator(LINEAR_INTERPOLATOR);
        animatorset = new AnimatorSet();
        animatorset.play(objectanimator1).with(objectanimator).with(objectanimator2);
        animatorset.addListener(mAnimationListener);
        return animatorset;
    }

    protected boolean drawHardware(DisplayListCanvas displaylistcanvas)
    {
        displaylistcanvas.drawCircle(mPropX, mPropY, mPropRadius, mPropPaint);
        return true;
    }

    protected boolean drawSoftware(Canvas canvas, Paint paint)
    {
        boolean flag = false;
        int i = paint.getAlpha();
        int j = (int)((float)i * mOpacity + 0.5F);
        float f = getCurrentRadius();
        boolean flag1 = flag;
        if(j > 0)
        {
            flag1 = flag;
            if(f > 0.0F)
            {
                float f1 = getCurrentX();
                float f2 = getCurrentY();
                paint.setAlpha(j);
                canvas.drawCircle(f1, f2, f, paint);
                paint.setAlpha(i);
                flag1 = true;
            }
        }
        return flag1;
    }

    public void getBounds(Rect rect)
    {
        int i = (int)mTargetX;
        int j = (int)mTargetY;
        int k = (int)mTargetRadius + 1;
        rect.set(i - k, j - k, i + k, j + k);
    }

    public boolean hasFinishedExit()
    {
        return mHasFinishedExit;
    }

    protected void jumpValuesToExit()
    {
        mOpacity = 0.0F;
        mTweenX = 1.0F;
        mTweenY = 1.0F;
        mTweenRadius = 1.0F;
    }

    public void move(float f, float f1)
    {
        mStartingX = f;
        mStartingY = f1;
        clampStartingPosition();
    }

    protected void onTargetRadiusChanged(float f)
    {
        clampStartingPosition();
    }

    private static final int BOUNDED_OPACITY_EXIT_DURATION = 400;
    private static final int BOUNDED_ORIGIN_EXIT_DURATION = 300;
    private static final int BOUNDED_RADIUS_EXIT_DURATION = 800;
    private static final TimeInterpolator DECELERATE_INTERPOLATOR = new LogDecelerateInterpolator(400F, 1.4F, 0.0F);
    private static final TimeInterpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    private static final float MAX_BOUNDED_RADIUS = 350F;
    private static final FloatProperty OPACITY = new FloatProperty("opacity") {

        public Float get(RippleForeground rippleforeground)
        {
            return Float.valueOf(RippleForeground._2D_get0(rippleforeground));
        }

        public volatile Object get(Object obj)
        {
            return get((RippleForeground)obj);
        }

        public void setValue(RippleForeground rippleforeground, float f)
        {
            RippleForeground._2D_set1(rippleforeground, f);
            rippleforeground.invalidateSelf();
        }

        public volatile void setValue(Object obj, float f)
        {
            setValue((RippleForeground)obj, f);
        }

    }
;
    private static final int OPACITY_ENTER_DURATION_FAST = 120;
    private static final int RIPPLE_ENTER_DELAY = 80;
    private static final FloatProperty TWEEN_ORIGIN = new FloatProperty("tweenOrigin") {

        public Float get(RippleForeground rippleforeground)
        {
            return Float.valueOf(RippleForeground._2D_get2(rippleforeground));
        }

        public volatile Object get(Object obj)
        {
            return get((RippleForeground)obj);
        }

        public void setValue(RippleForeground rippleforeground, float f)
        {
            RippleForeground._2D_set3(rippleforeground, f);
            RippleForeground._2D_set4(rippleforeground, f);
            rippleforeground.invalidateSelf();
        }

        public volatile void setValue(Object obj, float f)
        {
            setValue((RippleForeground)obj, f);
        }

    }
;
    private static final FloatProperty TWEEN_RADIUS = new FloatProperty("tweenRadius") {

        public Float get(RippleForeground rippleforeground)
        {
            return Float.valueOf(RippleForeground._2D_get1(rippleforeground));
        }

        public volatile Object get(Object obj)
        {
            return get((RippleForeground)obj);
        }

        public void setValue(RippleForeground rippleforeground, float f)
        {
            RippleForeground._2D_set2(rippleforeground, f);
            rippleforeground.invalidateSelf();
        }

        public volatile void setValue(Object obj, float f)
        {
            setValue((RippleForeground)obj, f);
        }

    }
;
    private static final float WAVE_OPACITY_DECAY_VELOCITY = 3F;
    private static final float WAVE_TOUCH_DOWN_ACCELERATION = 1024F;
    private static final float WAVE_TOUCH_UP_ACCELERATION = 3400F;
    private final AnimatorListenerAdapter mAnimationListener = new AnimatorListenerAdapter() {

        public void onAnimationEnd(Animator animator)
        {
            RippleForeground._2D_set0(RippleForeground.this, true);
        }

        final RippleForeground this$0;

            
            {
                this$0 = RippleForeground.this;
                super();
            }
    }
;
    private float mBoundedRadius;
    private float mClampedStartingX;
    private float mClampedStartingY;
    private boolean mHasFinishedExit;
    private boolean mIsBounded;
    private float mOpacity;
    private CanvasProperty mPropPaint;
    private CanvasProperty mPropRadius;
    private CanvasProperty mPropX;
    private CanvasProperty mPropY;
    private float mStartingX;
    private float mStartingY;
    private float mTargetX;
    private float mTargetY;
    private float mTweenRadius;
    private float mTweenX;
    private float mTweenY;

}
