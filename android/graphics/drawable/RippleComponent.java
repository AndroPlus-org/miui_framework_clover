// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.drawable;

import android.animation.Animator;
import android.graphics.*;
import android.view.DisplayListCanvas;
import android.view.RenderNodeAnimator;
import java.util.ArrayList;

// Referenced classes of package android.graphics.drawable:
//            RippleDrawable

abstract class RippleComponent
{
    public static class RenderNodeAnimatorSet
    {

        public void add(RenderNodeAnimator rendernodeanimator)
        {
            mAnimators.add(rendernodeanimator);
        }

        public void cancel()
        {
            ArrayList arraylist = mAnimators;
            int i = arraylist.size();
            for(int j = 0; j < i; j++)
                ((RenderNodeAnimator)arraylist.get(j)).cancel();

        }

        public void clear()
        {
            mAnimators.clear();
        }

        public void end()
        {
            ArrayList arraylist = mAnimators;
            int i = arraylist.size();
            for(int j = 0; j < i; j++)
                ((RenderNodeAnimator)arraylist.get(j)).end();

        }

        public boolean isRunning()
        {
            ArrayList arraylist = mAnimators;
            int i = arraylist.size();
            for(int j = 0; j < i; j++)
                if(((RenderNodeAnimator)arraylist.get(j)).isRunning())
                    return true;

            return false;
        }

        public void start(DisplayListCanvas displaylistcanvas)
        {
            if(displaylistcanvas == null)
                throw new IllegalArgumentException("Hardware canvas must be non-null");
            ArrayList arraylist = mAnimators;
            int i = arraylist.size();
            for(int j = 0; j < i; j++)
            {
                RenderNodeAnimator rendernodeanimator = (RenderNodeAnimator)arraylist.get(j);
                rendernodeanimator.setTarget(displaylistcanvas);
                rendernodeanimator.start();
            }

        }

        private final ArrayList mAnimators = new ArrayList();

        public RenderNodeAnimatorSet()
        {
        }
    }


    public RippleComponent(RippleDrawable rippledrawable, Rect rect, boolean flag)
    {
        mOwner = rippledrawable;
        mBounds = rect;
        mForceSoftware = flag;
    }

    private void cancelSoftwareAnimations()
    {
        if(mSoftwareAnimator != null)
        {
            mSoftwareAnimator.cancel();
            mSoftwareAnimator = null;
        }
    }

    private void endHardwareAnimations()
    {
        if(mHardwareAnimator != null)
        {
            mHardwareAnimator.end();
            mHardwareAnimator = null;
        }
        if(mHasPendingHardwareAnimator)
        {
            mHasPendingHardwareAnimator = false;
            jumpValuesToExit();
        }
    }

    private void endSoftwareAnimations()
    {
        if(mSoftwareAnimator != null)
        {
            mSoftwareAnimator.end();
            mSoftwareAnimator = null;
        }
    }

    private static float getTargetRadius(Rect rect)
    {
        float f = (float)rect.width() / 2.0F;
        float f1 = (float)rect.height() / 2.0F;
        return (float)Math.sqrt(f * f + f1 * f1);
    }

    private void startPendingAnimation(DisplayListCanvas displaylistcanvas, Paint paint)
    {
        if(mHasPendingHardwareAnimator)
        {
            mHasPendingHardwareAnimator = false;
            mHardwareAnimator = createHardwareExit(new Paint(paint));
            mHardwareAnimator.start(displaylistcanvas);
            jumpValuesToExit();
        }
    }

    public void cancel()
    {
        cancelSoftwareAnimations();
        endHardwareAnimations();
    }

    protected abstract RenderNodeAnimatorSet createHardwareExit(Paint paint);

    protected abstract Animator createSoftwareEnter(boolean flag);

    protected abstract Animator createSoftwareExit();

    public boolean draw(Canvas canvas, Paint paint)
    {
        boolean flag;
        if(!mForceSoftware && canvas.isHardwareAccelerated())
            flag = canvas instanceof DisplayListCanvas;
        else
            flag = false;
        if(mHasDisplayListCanvas != flag)
        {
            mHasDisplayListCanvas = flag;
            if(!flag)
                endHardwareAnimations();
        }
        if(flag)
        {
            DisplayListCanvas displaylistcanvas = (DisplayListCanvas)canvas;
            startPendingAnimation(displaylistcanvas, paint);
            if(mHardwareAnimator != null)
                return drawHardware(displaylistcanvas);
        }
        return drawSoftware(canvas, paint);
    }

    protected abstract boolean drawHardware(DisplayListCanvas displaylistcanvas);

    protected abstract boolean drawSoftware(Canvas canvas, Paint paint);

    public void end()
    {
        endSoftwareAnimations();
        endHardwareAnimations();
    }

    public final void enter(boolean flag)
    {
        cancel();
        mSoftwareAnimator = createSoftwareEnter(flag);
        if(mSoftwareAnimator != null)
            mSoftwareAnimator.start();
    }

    public final void exit()
    {
        cancel();
        if(mHasDisplayListCanvas)
        {
            mHasPendingHardwareAnimator = true;
            invalidateSelf();
        } else
        {
            mSoftwareAnimator = createSoftwareExit();
            mSoftwareAnimator.start();
        }
    }

    public void getBounds(Rect rect)
    {
        int i = (int)Math.ceil(mTargetRadius);
        rect.set(-i, -i, i, i);
    }

    protected final void invalidateSelf()
    {
        mOwner.invalidateSelf(false);
    }

    protected final boolean isHardwareAnimating()
    {
        boolean flag;
        if(mHardwareAnimator == null || !mHardwareAnimator.isRunning())
            flag = mHasPendingHardwareAnimator;
        else
            flag = true;
        return flag;
    }

    protected abstract void jumpValuesToExit();

    public void onBoundsChange()
    {
        if(!mHasMaxRadius)
        {
            mTargetRadius = getTargetRadius(mBounds);
            onTargetRadiusChanged(mTargetRadius);
        }
    }

    protected final void onHotspotBoundsChanged()
    {
        if(!mHasMaxRadius)
        {
            float f = (float)mBounds.width() / 2.0F;
            float f1 = (float)mBounds.height() / 2.0F;
            onTargetRadiusChanged((float)Math.sqrt(f * f + f1 * f1));
        }
    }

    protected void onTargetRadiusChanged(float f)
    {
    }

    public final void setup(float f, int i)
    {
        if(f >= 0.0F)
        {
            mHasMaxRadius = true;
            mTargetRadius = f;
        } else
        {
            mTargetRadius = getTargetRadius(mBounds);
        }
        mDensityScale = (float)i * 0.00625F;
        onTargetRadiusChanged(mTargetRadius);
    }

    protected final Rect mBounds;
    protected float mDensityScale;
    private final boolean mForceSoftware;
    private RenderNodeAnimatorSet mHardwareAnimator;
    private boolean mHasDisplayListCanvas;
    private boolean mHasMaxRadius;
    private boolean mHasPendingHardwareAnimator;
    private final RippleDrawable mOwner;
    private Animator mSoftwareAnimator;
    protected float mTargetRadius;
}
