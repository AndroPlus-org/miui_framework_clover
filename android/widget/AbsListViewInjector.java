// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;
import miui.os.Environment;

// Referenced classes of package android.widget:
//            AbsListView

public class AbsListViewInjector
{
    public static class BackEaseOutInterpolater
        implements Interpolator
    {

        public float getInterpolation(float f)
        {
            float f1;
            if(overshot == 0.0F)
                f1 = 1.70158F;
            else
                f1 = overshot;
            f--;
            return f * f * ((f1 + 1.0F) * f + f1) + 1.0F;
        }

        public float overshot;

        public BackEaseOutInterpolater()
        {
            overshot = 0.0F;
        }
    }

    public static class CircEaseOutInterpolator
        implements Interpolator
    {

        public float getInterpolation(float f)
        {
            f--;
            return (float)Math.sqrt(1.0F - f * f);
        }

        public CircEaseOutInterpolator()
        {
        }
    }


    public AbsListViewInjector()
    {
    }

    private static boolean edgeReached(AbsListView abslistview, int i)
    {
        boolean flag;
        boolean flag1;
        int j = abslistview.getChildCount();
        if(j <= 0)
            break MISSING_BLOCK_LABEL_224;
        int k = abslistview.mFirstPosition;
        Rect rect = abslistview.mListPadding;
        int l = abslistview.getChildAt(0).getTop();
        int i1 = abslistview.getChildAt(abslistview.getChildCount() - 1).getBottom();
        boolean flag2;
        if(k == 0)
        {
            if(l >= rect.top)
                flag1 = true;
            else
                flag1 = false;
        } else
        {
            flag1 = false;
        }
        if(k + j == abslistview.mItemCount)
        {
            if(i1 <= abslistview.getHeight() - rect.bottom)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = false;
        }
        if(flag)
            flag2 = flag1;
        else
            flag2 = false;
        abslistview.mIsShortList = flag2;
        if(!abslistview.mIsShortList) goto _L2; else goto _L1
_L1:
        if(abslistview.mOffsetRevise == 0)
            abslistview.mOffsetRevise = i;
          goto _L3
_L2:
        if(!flag1) goto _L5; else goto _L4
_L4:
        if(abslistview.mOffsetRevise == 0 || i < abslistview.mOffsetRevise)
            abslistview.mOffsetRevise = i;
          goto _L3
_L5:
        if(!flag || abslistview.mOffsetRevise != 0 && i <= abslistview.mOffsetRevise) goto _L3; else goto _L6
_L6:
        abslistview.mOffsetRevise = i;
_L3:
        if(flag1 && i > 0 || flag && i < 0)
            return true;
        return false;
    }

    public static int getPanSpeed(AbsListView abslistview, int i)
    {
        int j = 0;
        if(isAnimating(abslistview))
            return 0;
        if(abslistview.mLastY != 0x80000000)
        {
            i -= abslistview.mLastY;
        } else
        {
            int k = abslistview.mDownMotionY;
            if(abslistview.mTouchMode > 2)
                j = abslistview.mMotionCorrection;
            i = i - k - j;
        }
        return i;
    }

    public static void initOnTouchDown(AbsListView abslistview, MotionEvent motionevent)
    {
        abslistview.mIsTouching = true;
        abslistview.mInertia = 0;
        abslistview.mOffsetRevise = 0;
        abslistview.mDownMotionY = (int)motionevent.getY();
    }

    private static boolean isAnimating(AbsListView abslistview)
    {
        boolean flag;
        if(abslistview.mAnimatorSet != null)
            flag = abslistview.mAnimatorSet.isRunning();
        else
            flag = false;
        return flag;
    }

    private static boolean isSpringOverscrollEnabled(AbsListView abslistview)
    {
        boolean flag;
        if(abslistview.getOverScrollMode() != 2)
            flag = Environment.isUsingMiui(abslistview.getContext());
        else
            flag = false;
        return flag;
    }

    public static boolean needFinishActionMode(AbsListView abslistview)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(!Environment.isUsingMiui(abslistview.getContext()))
        {
            flag1 = flag;
            if(abslistview.getCheckedItemCount() == 0)
                flag1 = true;
        }
        return flag1;
    }

    public static void onRenderTick(AbsListView abslistview, Canvas canvas)
    {
        if(!isSpringOverscrollEnabled(abslistview))
            return;
        if(isAnimating(abslistview))
        {
            setListScale(abslistview, canvas, 0, false);
            return;
        }
        if(abslistview.mIsTouching)
        {
            if(abslistview.mScaleYDirty || abslistview.mScaleY != 1.0F)
            {
                setListScale(abslistview, canvas, abslistview.mInertia, false);
                abslistview.mScaleYDirty = false;
            }
            return;
        }
        if(!edgeReached(abslistview, abslistview.mInertia)) goto _L2; else goto _L1
_L1:
        setListScale(abslistview, canvas, abslistview.mInertia, true);
        abslistview.mInertia = 0;
_L4:
        return;
_L2:
        abslistview.mInertia = (int)((float)abslistview.mInertia * 0.98F);
        if(abslistview.mInertia == 0)
            abslistview.mScaleY = 1.0F;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static void resetScale(AbsListView abslistview)
    {
        abslistview.mInertia = 0;
        abslistview.mScaleY = 1.0F;
        abslistview.invalidate();
    }

    private static void setListScale(AbsListView abslistview, Canvas canvas, int i, boolean flag)
    {
        if(i == 0)
        {
            canvas.scale(1.0F, abslistview.mScaleY, abslistview.mLastPivotX, abslistview.mLastPivotY);
            abslistview.invalidate();
            return;
        }
        double d = Math.min(Math.max(0.0D, Math.sqrt(Math.abs(i)) * 3D * 0.001D), 0.10000000000000001D);
        double d1 = d;
        if(abslistview.mIsShortList)
        {
            d1 = d;
            if(i < 0)
                d1 = -d;
        }
        float f = 1.0F + (float)d1;
        abslistview.mLastPivotX = abslistview.getWidth() >> 1;
        if(i > 0 || abslistview.mIsShortList)
            abslistview.mLastPivotY = 0.0F;
        else
            abslistview.mLastPivotY = abslistview.getHeight();
        if(flag)
        {
            if(abslistview.mScaleY != 1.0F)
            {
                canvas.scale(1.0F, abslistview.mScaleY, abslistview.mLastPivotX, abslistview.mLastPivotY);
                abslistview.invalidate();
            }
            if(abslistview.mAnimatorSet != null)
                abslistview.mAnimatorSet.cancel();
            abslistview.mAnimatorSet = new AnimatorSet();
            canvas = ValueAnimator.ofFloat(new float[] {
                f, 1.0F
            });
            canvas.setDuration(400L);
            canvas.setInterpolator(new BackEaseOutInterpolater());
            canvas.addUpdateListener(new android.animation.ValueAnimator.AnimatorUpdateListener(abslistview) {

                public void onAnimationUpdate(ValueAnimator valueanimator1)
                {
                    listView.mScaleY = ((Float)valueanimator1.getAnimatedValue()).floatValue();
                    listView.invalidate();
                }

                final AbsListView val$listView;

            
            {
                listView = abslistview;
                super();
            }
            }
);
            if(abslistview.mScaleY == 1.0F)
            {
                ValueAnimator valueanimator = ValueAnimator.ofFloat(new float[] {
                    1.0F, f
                });
                valueanimator.setDuration(200L);
                valueanimator.setInterpolator(new CircEaseOutInterpolator());
                valueanimator.addUpdateListener(new android.animation.ValueAnimator.AnimatorUpdateListener(abslistview) {

                    public void onAnimationUpdate(ValueAnimator valueanimator1)
                    {
                        listView.mScaleY = ((Float)valueanimator1.getAnimatedValue()).floatValue();
                        listView.invalidate();
                    }

                    final AbsListView val$listView;

            
            {
                listView = abslistview;
                super();
            }
                }
);
                abslistview.mAnimatorSet.play(valueanimator).before(canvas);
            } else
            {
                abslistview.mAnimatorSet.play(canvas);
            }
            abslistview.mAnimatorSet.start();
        } else
        {
            canvas.scale(1.0F, f, abslistview.mLastPivotX, abslistview.mLastPivotY);
            abslistview.mScaleY = f;
            abslistview.invalidate();
        }
    }

    public static boolean setListScaleIfNeeded(AbsListView abslistview, int i)
    {
        boolean flag = true;
        if(isSpringOverscrollEnabled(abslistview))
        {
            if(isAnimating(abslistview))
                return true;
            boolean flag1 = edgeReached(abslistview, i);
            if(flag1)
            {
                abslistview.mInertia = i - abslistview.mOffsetRevise;
                abslistview.mScaleYDirty = true;
                abslistview.invalidate();
            }
            if(abslistview.mInertia != 0)
                i = ((flag) ? 1 : 0);
            else
                i = 0;
            return i & flag1;
        } else
        {
            return false;
        }
    }
}
