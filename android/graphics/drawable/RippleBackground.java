// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.drawable;

import android.animation.*;
import android.graphics.*;
import android.util.FloatProperty;
import android.view.DisplayListCanvas;
import android.view.RenderNodeAnimator;
import android.view.animation.LinearInterpolator;

// Referenced classes of package android.graphics.drawable:
//            RippleComponent, RippleDrawable

class RippleBackground extends RippleComponent
{
    private static abstract class BackgroundProperty extends FloatProperty
    {

        public BackgroundProperty(String s)
        {
            super(s);
        }
    }


    static float _2D_get0(RippleBackground ripplebackground)
    {
        return ripplebackground.mOpacity;
    }

    static float _2D_set0(RippleBackground ripplebackground, float f)
    {
        ripplebackground.mOpacity = f;
        return f;
    }

    public RippleBackground(RippleDrawable rippledrawable, Rect rect, boolean flag, boolean flag1)
    {
        super(rippledrawable, rect, flag1);
        mOpacity = 0.0F;
        mIsBounded = flag;
    }

    protected RippleComponent.RenderNodeAnimatorSet createHardwareExit(Paint paint)
    {
        RippleComponent.RenderNodeAnimatorSet rendernodeanimatorset = new RippleComponent.RenderNodeAnimatorSet();
        int i = paint.getAlpha();
        paint.setAlpha((int)(mOpacity * (float)i + 0.5F));
        mPropPaint = CanvasProperty.createPaint(paint);
        mPropRadius = CanvasProperty.createFloat(mTargetRadius);
        mPropX = CanvasProperty.createFloat(0.0F);
        mPropY = CanvasProperty.createFloat(0.0F);
        int j;
        if(mIsBounded)
            j = (int)((1.0F - mOpacity) * 120F);
        else
            j = 0;
        paint = new RenderNodeAnimator(mPropPaint, 1, 0.0F);
        paint.setInterpolator(LINEAR_INTERPOLATOR);
        paint.setDuration(480L);
        if(j > 0)
        {
            paint.setStartDelay(j);
            paint.setStartValue(i);
        }
        rendernodeanimatorset.add(paint);
        if(j > 0)
        {
            paint = new RenderNodeAnimator(mPropPaint, 1, i);
            paint.setInterpolator(LINEAR_INTERPOLATOR);
            paint.setDuration(j);
            rendernodeanimatorset.add(paint);
        }
        return rendernodeanimatorset;
    }

    protected Animator createSoftwareEnter(boolean flag)
    {
        int i;
        ObjectAnimator objectanimator;
        if(flag)
            i = 120;
        else
            i = 600;
        i = (int)((1.0F - mOpacity) * (float)i);
        objectanimator = ObjectAnimator.ofFloat(this, OPACITY, new float[] {
            1.0F
        });
        objectanimator.setAutoCancel(true);
        objectanimator.setDuration(i);
        objectanimator.setInterpolator(LINEAR_INTERPOLATOR);
        return objectanimator;
    }

    protected Animator createSoftwareExit()
    {
        AnimatorSet animatorset = new AnimatorSet();
        Object obj = ObjectAnimator.ofFloat(this, OPACITY, new float[] {
            0.0F
        });
        ((ObjectAnimator) (obj)).setInterpolator(LINEAR_INTERPOLATOR);
        ((ObjectAnimator) (obj)).setDuration(480L);
        ((ObjectAnimator) (obj)).setAutoCancel(true);
        obj = animatorset.play(((Animator) (obj)));
        int i;
        if(mIsBounded)
            i = (int)((1.0F - mOpacity) * 120F);
        else
            i = 0;
        if(i > 0)
        {
            ObjectAnimator objectanimator = ObjectAnimator.ofFloat(this, OPACITY, new float[] {
                1.0F
            });
            objectanimator.setInterpolator(LINEAR_INTERPOLATOR);
            objectanimator.setDuration(i);
            objectanimator.setAutoCancel(true);
            ((android.animation.AnimatorSet.Builder) (obj)).after(objectanimator);
        }
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
        if(j > 0)
        {
            paint.setAlpha(j);
            canvas.drawCircle(0.0F, 0.0F, mTargetRadius, paint);
            paint.setAlpha(i);
            flag = true;
        }
        return flag;
    }

    public boolean isVisible()
    {
        boolean flag;
        if(mOpacity <= 0.0F)
            flag = isHardwareAnimating();
        else
            flag = true;
        return flag;
    }

    protected void jumpValuesToExit()
    {
        mOpacity = 0.0F;
    }

    private static final TimeInterpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    private static final BackgroundProperty OPACITY = new BackgroundProperty("opacity") {

        public Float get(RippleBackground ripplebackground)
        {
            return Float.valueOf(RippleBackground._2D_get0(ripplebackground));
        }

        public volatile Object get(Object obj)
        {
            return get((RippleBackground)obj);
        }

        public void setValue(RippleBackground ripplebackground, float f)
        {
            RippleBackground._2D_set0(ripplebackground, f);
            ripplebackground.invalidateSelf();
        }

        public volatile void setValue(Object obj, float f)
        {
            setValue((RippleBackground)obj, f);
        }

    }
;
    private static final int OPACITY_ENTER_DURATION = 600;
    private static final int OPACITY_ENTER_DURATION_FAST = 120;
    private static final int OPACITY_EXIT_DURATION = 480;
    private boolean mIsBounded;
    private float mOpacity;
    private CanvasProperty mPropPaint;
    private CanvasProperty mPropRadius;
    private CanvasProperty mPropX;
    private CanvasProperty mPropY;

}
