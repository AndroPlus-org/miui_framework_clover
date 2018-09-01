// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.util;

import android.animation.*;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.os.SystemProperties;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import miui.graphics.BitmapFactory;

// Referenced classes of package miui.util:
//            ScreenshotUtils

public class ScreenshotDrawable extends Drawable
    implements android.animation.ValueAnimator.AnimatorUpdateListener
{

    static Bitmap _2D_get0(ScreenshotDrawable screenshotdrawable)
    {
        return screenshotdrawable.mBluredBitmap;
    }

    static View _2D_get1(ScreenshotDrawable screenshotdrawable)
    {
        return screenshotdrawable.mOwnerView;
    }

    static Bitmap _2D_set0(ScreenshotDrawable screenshotdrawable, Bitmap bitmap)
    {
        screenshotdrawable.mBluredBitmap = bitmap;
        return bitmap;
    }

    public ScreenshotDrawable(View view)
    {
        mPaint = new Paint(3);
        mSrcRect = new Rect();
        mOwnerView = view;
        view = view.getResources();
        int i;
        if(isdDisplayOled)
            i = 0x11060011;
        else
            i = 0x11060010;
        mBgColor = view.getColor(i);
    }

    private int mixColor(int i, int j)
    {
        return (Color.alpha(i) * j) / 255 << 24 | 0xffffff & i;
    }

    public static void processBlurBehindFlag(View view, android.view.ViewGroup.LayoutParams layoutparams, boolean flag)
    {
        if((((android.view.WindowManager.LayoutParams)layoutparams).flags & 4) == 0) goto _L2; else goto _L1
_L1:
        if(!(view.getBackground() instanceof ScreenshotDrawable))
        {
            layoutparams = new ScreenshotDrawable(view);
            layoutparams.setOriginalDrawable(view.getBackground());
            view.setBackground(layoutparams);
        }
_L4:
        return;
_L2:
        if(flag && (view.getBackground() instanceof ScreenshotDrawable))
            ((ScreenshotDrawable)view.getBackground()).startVisibilityAnimator(false);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void rebuildBluredBitmap()
    {
        if(sHasRealBlur)
            return;
        mBluredBitmap = BitmapFactory.fastBlur(ScreenshotUtils.getScreenshot(mOwnerView.getContext(), 1.0F / (float)ScreenshotUtils.REAL_BLUR_MINIFY, 0, 0, true), mBluredBitmap, (int)((float)ScreenshotUtils.REAL_BLUR_RADIUS * Resources.getSystem().getDisplayMetrics().density));
_L1:
        return;
        Throwable throwable;
        throwable;
        Log.e("ScreenshotDrawable", "Screenshot and fastblur failed.", throwable);
          goto _L1
    }

    private void startVisibilityAnimator(boolean flag)
    {
        if(mVisibilityChangeAnimator != null && mVisibilityChangeAnimator.isRunning())
            mVisibilityChangeAnimator.end();
        if(flag)
        {
            mVisibilityChangeAnimator = ValueAnimator.ofInt(new int[] {
                0, 255
            });
            setAlpha(0);
        } else
        {
            mVisibilityChangeAnimator = ValueAnimator.ofInt(new int[] {
                mPaint.getAlpha(), 0
            });
            mVisibilityChangeAnimator.addListener(new AnimatorListenerAdapter() {

                public void onAnimationEnd(Animator animator)
                {
                    if(ScreenshotDrawable._2D_get0(ScreenshotDrawable.this) != null)
                    {
                        ScreenshotDrawable._2D_get0(ScreenshotDrawable.this).recycle();
                        ScreenshotDrawable._2D_set0(ScreenshotDrawable.this, null);
                    }
                    if(getCallback() instanceof View)
                        ((View)getCallback()).setBackground(getOriginalDrawable());
                }

                final ScreenshotDrawable this$0;

            
            {
                this$0 = ScreenshotDrawable.this;
                super();
            }
            }
);
        }
        mVisibilityChangeAnimator.setDuration(200L);
        mVisibilityChangeAnimator.addUpdateListener(this);
        mVisibilityChangeAnimator.start();
    }

    public void draw(Canvas canvas)
    {
        if(mBluredBitmap != null)
        {
            if(mSrcRect.isEmpty())
            {
                mOwnerView.getLocationOnScreen(sTempLoc);
                int i = sTempLoc[0] / ScreenshotUtils.REAL_BLUR_MINIFY;
                int j = sTempLoc[1] / ScreenshotUtils.REAL_BLUR_MINIFY;
                int k = getBounds().width() / ScreenshotUtils.REAL_BLUR_MINIFY;
                int l = getBounds().height() / ScreenshotUtils.REAL_BLUR_MINIFY;
                mSrcRect.set(i, j, i + k, j + l);
            }
            canvas.drawBitmap(mBluredBitmap, mSrcRect, getBounds(), mPaint);
        }
        canvas.drawColor(mixColor(mBgColor, mPaint.getAlpha()));
        if(mOriginalDrawable != null)
            mOriginalDrawable.draw(canvas);
    }

    public int getOpacity()
    {
        return 0;
    }

    public Drawable getOriginalDrawable()
    {
        return mOriginalDrawable;
    }

    public void onAnimationUpdate(ValueAnimator valueanimator)
    {
        setAlpha(((Integer)valueanimator.getAnimatedValue()).intValue());
    }

    void processShow()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mOwnerView.getRootView().getLayoutParams() instanceof android.view.WindowManager.LayoutParams)
        {
            Object obj = (android.view.WindowManager.LayoutParams)mOwnerView.getRootView().getLayoutParams();
            flag1 = flag;
            if(((android.view.WindowManager.LayoutParams) (obj)).windowAnimations != 0)
            {
                obj = mOwnerView.getContext().obtainStyledAttributes(((android.view.WindowManager.LayoutParams) (obj)).windowAnimations, com.android.internal.R.styleable.WindowAnimation);
                int i = ((TypedArray) (obj)).getResourceId(0, 0);
                ((TypedArray) (obj)).recycle();
                flag1 = flag;
                if(i != 0)
                    flag1 = true;
            }
        }
        if(flag1)
        {
            if(mVisibilityChangeAnimator != null && mVisibilityChangeAnimator.isRunning())
                mVisibilityChangeAnimator.end();
            setAlpha(255);
        } else
        {
            startVisibilityAnimator(true);
        }
    }

    public void setAlpha(int i)
    {
        mPaint.setAlpha(i);
        if(mOriginalDrawable != null)
            mOriginalDrawable.setAlpha(i);
        invalidateSelf();
    }

    public void setBounds(int i, int j, int k, int l)
    {
        super.setBounds(i, j, k, l);
        mSrcRect.setEmpty();
        if(mOriginalDrawable != null)
            mOriginalDrawable.setBounds(i, j, k, l);
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
    }

    public void setOriginalDrawable(Drawable drawable)
    {
        mOriginalDrawable = drawable;
    }

    public boolean setVisible(boolean flag, boolean flag1)
    {
        if(flag)
        {
            if(!(mOwnerView.getRootView().getLayoutParams() instanceof android.view.WindowManager.LayoutParams))
                mOwnerView.getRootView().addOnAttachStateChangeListener(new android.view.View.OnAttachStateChangeListener() {

                    public void onViewAttachedToWindow(View view)
                    {
                        processShow();
                        ScreenshotDrawable._2D_get1(ScreenshotDrawable.this).getRootView().removeOnAttachStateChangeListener(this);
                    }

                    public void onViewDetachedFromWindow(View view)
                    {
                    }

                    final ScreenshotDrawable this$0;

            
            {
                this$0 = ScreenshotDrawable.this;
                super();
            }
                }
);
            else
                processShow();
            mSrcRect.setEmpty();
            rebuildBluredBitmap();
        }
        return super.setVisible(flag, flag1);
    }

    private static final String TAG = "ScreenshotDrawable";
    private static boolean isdDisplayOled = "oled".equals(SystemProperties.get("ro.display.type"));
    private static boolean sHasRealBlur = SystemProperties.getBoolean("ro.miui.has_real_blur", false);
    private static int sTempLoc[] = new int[2];
    private int mBgColor;
    private Bitmap mBluredBitmap;
    private Drawable mOriginalDrawable;
    private View mOwnerView;
    private Paint mPaint;
    private Rect mSrcRect;
    private ValueAnimator mVisibilityChangeAnimator;

}
