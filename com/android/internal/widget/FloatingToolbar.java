// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.animation.*;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.text.TextUtils;
import android.util.*;
import android.view.*;
import android.view.animation.*;
import android.widget.*;
import com.android.internal.util.Preconditions;
import java.util.*;

public final class FloatingToolbar
{
    private static final class FloatingToolbarPopup
    {

        static ViewGroup _2D_get0(FloatingToolbarPopup floatingtoolbarpopup)
        {
            return floatingtoolbarpopup.mContentContainer;
        }

        static Context _2D_get1(FloatingToolbarPopup floatingtoolbarpopup)
        {
            return floatingtoolbarpopup.mContext;
        }

        static OverflowPanelViewHelper _2D_get10(FloatingToolbarPopup floatingtoolbarpopup)
        {
            return floatingtoolbarpopup.mOverflowPanelViewHelper;
        }

        static PopupWindow _2D_get11(FloatingToolbarPopup floatingtoolbarpopup)
        {
            return floatingtoolbarpopup.mPopupWindow;
        }

        static ViewGroup _2D_get2(FloatingToolbarPopup floatingtoolbarpopup)
        {
            return floatingtoolbarpopup.mMainPanel;
        }

        static Size _2D_get3(FloatingToolbarPopup floatingtoolbarpopup)
        {
            return floatingtoolbarpopup.mMainPanelSize;
        }

        static android.view.MenuItem.OnMenuItemClickListener _2D_get4(FloatingToolbarPopup floatingtoolbarpopup)
        {
            return floatingtoolbarpopup.mOnMenuItemClickListener;
        }

        static boolean _2D_get5(FloatingToolbarPopup floatingtoolbarpopup)
        {
            return floatingtoolbarpopup.mOpenOverflowUpwards;
        }

        static ImageButton _2D_get6(FloatingToolbarPopup floatingtoolbarpopup)
        {
            return floatingtoolbarpopup.mOverflowButton;
        }

        static Size _2D_get7(FloatingToolbarPopup floatingtoolbarpopup)
        {
            return floatingtoolbarpopup.mOverflowButtonSize;
        }

        static OverflowPanel _2D_get8(FloatingToolbarPopup floatingtoolbarpopup)
        {
            return floatingtoolbarpopup.mOverflowPanel;
        }

        static Size _2D_get9(FloatingToolbarPopup floatingtoolbarpopup)
        {
            return floatingtoolbarpopup.mOverflowPanelSize;
        }

        static boolean _2D_wrap0(FloatingToolbarPopup floatingtoolbarpopup)
        {
            return floatingtoolbarpopup.isInRTLMode();
        }

        static boolean _2D_wrap1(FloatingToolbarPopup floatingtoolbarpopup)
        {
            return floatingtoolbarpopup.isOverflowAnimating();
        }

        static void _2D_wrap2(FloatingToolbarPopup floatingtoolbarpopup)
        {
            floatingtoolbarpopup.positionContentYCoordinatesIfOpeningOverflowUpwards();
        }

        static void _2D_wrap3(FloatingToolbarPopup floatingtoolbarpopup)
        {
            floatingtoolbarpopup.setContentAreaAsTouchableSurface();
        }

        static void _2D_wrap4(View view, int i)
        {
            setHeight(view, i);
        }

        static void _2D_wrap5(FloatingToolbarPopup floatingtoolbarpopup)
        {
            floatingtoolbarpopup.setPanelsStatesAtRestingPosition();
        }

        static void _2D_wrap6(View view, int i)
        {
            setWidth(view, i);
        }

        private int calculateOverflowHeight(int i)
        {
            int j = Math.min(4, Math.min(Math.max(2, i), mOverflowPanel.getCount()));
            i = 0;
            if(j < mOverflowPanel.getCount())
                i = (int)((float)mLineHeight * 0.5F);
            return mLineHeight * j + mOverflowButtonSize.getHeight() + i;
        }

        private void cancelDismissAndHideAnimations()
        {
            mDismissAnimation.cancel();
            mHideAnimation.cancel();
        }

        private void cancelOverflowAnimations()
        {
            mContentContainer.clearAnimation();
            mMainPanel.animate().cancel();
            mOverflowPanel.animate().cancel();
            mToArrow.stop();
            mToOverflow.stop();
        }

        private void clearPanels()
        {
            mOverflowPanelSize = null;
            mMainPanelSize = null;
            mIsOverflowOpen = false;
            mMainPanel.removeAllViews();
            ArrayAdapter arrayadapter = (ArrayAdapter)mOverflowPanel.getAdapter();
            arrayadapter.clear();
            mOverflowPanel.setAdapter(arrayadapter);
            mContentContainer.removeAllViews();
        }

        private void closeOverflow()
        {
            final int targetWidth = mMainPanelSize.getWidth();
            final int startWidth = mContentContainer.getWidth();
            final float left = mContentContainer.getX();
            Animation animation = (left + (float)mContentContainer.getWidth()). new Animation() {

                protected void applyTransformation(float f, Transformation transformation)
                {
                    int i = (int)((float)(targetWidth - startWidth) * f);
                    FloatingToolbarPopup._2D_wrap6(FloatingToolbarPopup._2D_get0(FloatingToolbarPopup.this), startWidth + i);
                    if(FloatingToolbarPopup._2D_wrap0(FloatingToolbarPopup.this))
                    {
                        FloatingToolbarPopup._2D_get0(FloatingToolbarPopup.this).setX(left);
                        FloatingToolbarPopup._2D_get2(FloatingToolbarPopup.this).setX(0.0F);
                        FloatingToolbarPopup._2D_get8(FloatingToolbarPopup.this).setX(0.0F);
                    } else
                    {
                        FloatingToolbarPopup._2D_get0(FloatingToolbarPopup.this).setX(right - (float)FloatingToolbarPopup._2D_get0(FloatingToolbarPopup.this).getWidth());
                        FloatingToolbarPopup._2D_get2(FloatingToolbarPopup.this).setX(FloatingToolbarPopup._2D_get0(FloatingToolbarPopup.this).getWidth() - targetWidth);
                        FloatingToolbarPopup._2D_get8(FloatingToolbarPopup.this).setX(FloatingToolbarPopup._2D_get0(FloatingToolbarPopup.this).getWidth() - startWidth);
                    }
                }

                final FloatingToolbarPopup this$1;
                final float val$left;
                final float val$right;
                final int val$startWidth;
                final int val$targetWidth;

            
            {
                this$1 = final_floatingtoolbarpopup;
                targetWidth = i;
                startWidth = j;
                left = f;
                right = F.this;
                super();
            }
            }
;
            Animation animation1 = (mContentContainer.getY() + (float)mContentContainer.getHeight()). new Animation() {

                protected void applyTransformation(float f, Transformation transformation)
                {
                    int i = (int)((float)(targetHeight - startHeight) * f);
                    FloatingToolbarPopup._2D_wrap4(FloatingToolbarPopup._2D_get0(FloatingToolbarPopup.this), startHeight + i);
                    if(FloatingToolbarPopup._2D_get5(FloatingToolbarPopup.this))
                    {
                        FloatingToolbarPopup._2D_get0(FloatingToolbarPopup.this).setY(bottom - (float)FloatingToolbarPopup._2D_get0(FloatingToolbarPopup.this).getHeight());
                        FloatingToolbarPopup._2D_wrap2(FloatingToolbarPopup.this);
                    }
                }

                final FloatingToolbarPopup this$1;
                final float val$bottom;
                final int val$startHeight;
                final int val$targetHeight;

            
            {
                this$1 = final_floatingtoolbarpopup;
                targetHeight = i;
                startHeight = j;
                bottom = F.this;
                super();
            }
            }
;
            final float overflowButtonStartX = mOverflowButton.getX();
            final float overflowButtonTargetX;
            Animation animation2;
            if(isInRTLMode())
                overflowButtonTargetX = (overflowButtonStartX - (float)startWidth) + (float)mOverflowButton.getWidth();
            else
                overflowButtonTargetX = ((float)startWidth + overflowButtonStartX) - (float)mOverflowButton.getWidth();
            animation2 = startWidth. new Animation() {

                protected void applyTransformation(float f, Transformation transformation)
                {
                    float f1 = overflowButtonStartX;
                    float f2 = overflowButtonTargetX;
                    float f3 = overflowButtonStartX;
                    int i;
                    float f4;
                    if(FloatingToolbarPopup._2D_wrap0(FloatingToolbarPopup.this))
                        i = 0;
                    else
                        i = FloatingToolbarPopup._2D_get0(FloatingToolbarPopup.this).getWidth() - startWidth;
                    f4 = i;
                    FloatingToolbarPopup._2D_get6(FloatingToolbarPopup.this).setX(f1 + (f2 - f3) * f + f4);
                }

                final FloatingToolbarPopup this$1;
                final float val$overflowButtonStartX;
                final float val$overflowButtonTargetX;
                final int val$startWidth;

            
            {
                this$1 = final_floatingtoolbarpopup;
                overflowButtonStartX = f;
                overflowButtonTargetX = f1;
                startWidth = I.this;
                super();
            }
            }
;
            animation.setInterpolator(mFastOutSlowInInterpolator);
            animation.setDuration(getAdjustedDuration(250));
            animation1.setInterpolator(mLogAccelerateInterpolator);
            animation1.setDuration(getAdjustedDuration(250));
            animation2.setInterpolator(mFastOutSlowInInterpolator);
            animation2.setDuration(getAdjustedDuration(250));
            mCloseOverflowAnimation.getAnimations().clear();
            mCloseOverflowAnimation.addAnimation(animation);
            mCloseOverflowAnimation.addAnimation(animation1);
            mCloseOverflowAnimation.addAnimation(animation2);
            mContentContainer.startAnimation(mCloseOverflowAnimation);
            mIsOverflowOpen = false;
            mMainPanel.animate().alpha(1.0F).withLayer().setInterpolator(mFastOutLinearInInterpolator).setDuration(100L).start();
            mOverflowPanel.animate().alpha(0.0F).withLayer().setInterpolator(mLinearOutSlowInInterpolator).setDuration(150L).start();
        }

        private ViewGroup createMainPanel()
        {
            return new LinearLayout(mContext) {

                public boolean onInterceptTouchEvent(MotionEvent motionevent)
                {
                    return FloatingToolbarPopup._2D_wrap1(FloatingToolbarPopup.this);
                }

                protected void onMeasure(int i, int j)
                {
                    if(FloatingToolbarPopup._2D_wrap1(FloatingToolbarPopup.this))
                        i = android.view.View.MeasureSpec.makeMeasureSpec(FloatingToolbarPopup._2D_get3(FloatingToolbarPopup.this).getWidth(), 0x40000000);
                    super.onMeasure(i, j);
                }

                final FloatingToolbarPopup this$1;

            
            {
                this$1 = FloatingToolbarPopup.this;
                super(context);
            }
            }
;
        }

        private android.view.animation.Animation.AnimationListener createOverflowAnimationListener()
        {
            return new android.view.animation.Animation.AnimationListener() {

                void lambda$_2D_com_android_internal_widget_FloatingToolbar$FloatingToolbarPopup$13_68144()
                {
                    FloatingToolbarPopup._2D_wrap5(FloatingToolbarPopup.this);
                    FloatingToolbarPopup._2D_wrap3(FloatingToolbarPopup.this);
                }

                public void onAnimationEnd(Animation animation)
                {
                    FloatingToolbarPopup._2D_get0(FloatingToolbarPopup.this).post(new _.Lambda.hZenqyGYSNt5KiruOSsv736MIDs((byte)0, this));
                }

                public void onAnimationRepeat(Animation animation)
                {
                }

                public void onAnimationStart(Animation animation)
                {
                    FloatingToolbarPopup._2D_get6(FloatingToolbarPopup.this).setEnabled(false);
                    FloatingToolbarPopup._2D_get2(FloatingToolbarPopup.this).setVisibility(0);
                    FloatingToolbarPopup._2D_get8(FloatingToolbarPopup.this).setVisibility(0);
                }

                final FloatingToolbarPopup this$1;

            
            {
                this$1 = FloatingToolbarPopup.this;
                super();
            }
            }
;
        }

        private ImageButton createOverflowButton()
        {
            ImageButton imagebutton = (ImageButton)LayoutInflater.from(mContext).inflate(0x1090062, null);
            imagebutton.setImageDrawable(mOverflow);
            imagebutton.setOnClickListener(new _.Lambda.nZD8NeHZxo4kFQHu5zIWiAfZj2s._cls2(this, imagebutton));
            return imagebutton;
        }

        private OverflowPanel createOverflowPanel()
        {
            OverflowPanel overflowpanel = new OverflowPanel(this);
            overflowpanel.setLayoutParams(new android.view.ViewGroup.LayoutParams(-1, -1));
            overflowpanel.setDivider(null);
            overflowpanel.setDividerHeight(0);
            overflowpanel.setAdapter(new ArrayAdapter(mContext, 0) {

                public View getView(int i, View view, ViewGroup viewgroup)
                {
                    return FloatingToolbarPopup._2D_get10(FloatingToolbarPopup.this).getView((MenuItem)getItem(i), FloatingToolbarPopup._2D_get9(FloatingToolbarPopup.this).getWidth(), view);
                }

                final FloatingToolbarPopup this$1;

            
            {
                this$1 = FloatingToolbarPopup.this;
                super(context, i);
            }
            }
);
            overflowpanel.setOnItemClickListener(new _.Lambda.nZD8NeHZxo4kFQHu5zIWiAfZj2s._cls3(this, overflowpanel));
            return overflowpanel;
        }

        private int getAdjustedDuration(int i)
        {
            if(mTransitionDurationScale < 150)
                return Math.max(i - 50, 0);
            if(mTransitionDurationScale > 300)
                return i + 50;
            else
                return (int)((float)i * ValueAnimator.getDurationScale());
        }

        private int getAdjustedToolbarWidth(int i)
        {
            int j = i;
            refreshViewPort();
            int k = mViewPortOnScreen.width();
            int l = mParent.getResources().getDimensionPixelSize(0x1050086);
            if(i <= 0)
                j = mParent.getResources().getDimensionPixelSize(0x1050090);
            return Math.min(j, k - l * 2);
        }

        private int getOverflowWidth()
        {
            int i = 0;
            int j = mOverflowPanel.getAdapter().getCount();
            for(int k = 0; k < j; k++)
            {
                MenuItem menuitem = (MenuItem)mOverflowPanel.getAdapter().getItem(k);
                i = Math.max(mOverflowPanelViewHelper.calculateWidth(menuitem), i);
            }

            return i;
        }

        private boolean hasOverflow()
        {
            boolean flag;
            if(mOverflowPanelSize != null)
                flag = true;
            else
                flag = false;
            return flag;
        }

        private boolean isInRTLMode()
        {
            boolean flag = true;
            if(mContext.getApplicationInfo().hasRtlSupport())
            {
                if(mContext.getResources().getConfiguration().getLayoutDirection() != 1)
                    flag = false;
            } else
            {
                flag = false;
            }
            return flag;
        }

        private boolean isOverflowAnimating()
        {
            boolean flag;
            boolean flag1;
            if(mOpenOverflowAnimation.hasStarted())
                flag = mOpenOverflowAnimation.hasEnded() ^ true;
            else
                flag = false;
            if(mCloseOverflowAnimation.hasStarted())
                flag1 = mCloseOverflowAnimation.hasEnded() ^ true;
            else
                flag1 = false;
            if(flag)
                flag1 = true;
            return flag1;
        }

        private void layoutOverflowPanelItems(List list)
        {
            ArrayAdapter arrayadapter = (ArrayAdapter)mOverflowPanel.getAdapter();
            arrayadapter.clear();
            int i = list.size();
            for(int j = 0; j < i; j++)
                arrayadapter.add((MenuItem)list.get(j));

            mOverflowPanel.setAdapter(arrayadapter);
            if(mOpenOverflowUpwards)
                mOverflowPanel.setY(0.0F);
            else
                mOverflowPanel.setY(mOverflowButtonSize.getHeight());
            mOverflowPanelSize = new Size(Math.max(getOverflowWidth(), mOverflowButtonSize.getWidth()), calculateOverflowHeight(4));
            setSize(mOverflowPanel, mOverflowPanelSize);
        }

        private void maybeComputeTransitionDurationScale()
        {
            if(mMainPanelSize != null && mOverflowPanelSize != null)
            {
                int i = mMainPanelSize.getWidth() - mOverflowPanelSize.getWidth();
                int j = mOverflowPanelSize.getHeight() - mMainPanelSize.getHeight();
                mTransitionDurationScale = (int)(Math.sqrt(i * i + j * j) / (double)mContentContainer.getContext().getResources().getDisplayMetrics().density);
            }
        }

        private static Size measure(View view)
        {
            boolean flag;
            if(view.getParent() == null)
                flag = true;
            else
                flag = false;
            Preconditions.checkState(flag);
            view.measure(0, 0);
            return new Size(view.getMeasuredWidth(), view.getMeasuredHeight());
        }

        private void openOverflow()
        {
            final int targetWidth = mOverflowPanelSize.getWidth();
            final int targetHeight = mOverflowPanelSize.getHeight();
            final int startWidth = mContentContainer.getWidth();
            final int startHeight = mContentContainer.getHeight();
            final float overflowButtonTargetX = mContentContainer.getY();
            final float left = mContentContainer.getX();
            Animation animation = (left + (float)mContentContainer.getWidth()). new Animation() {

                protected void applyTransformation(float f, Transformation transformation)
                {
                    int i = (int)((float)(targetWidth - startWidth) * f);
                    FloatingToolbarPopup._2D_wrap6(FloatingToolbarPopup._2D_get0(FloatingToolbarPopup.this), startWidth + i);
                    if(FloatingToolbarPopup._2D_wrap0(FloatingToolbarPopup.this))
                    {
                        FloatingToolbarPopup._2D_get0(FloatingToolbarPopup.this).setX(left);
                        FloatingToolbarPopup._2D_get2(FloatingToolbarPopup.this).setX(0.0F);
                        FloatingToolbarPopup._2D_get8(FloatingToolbarPopup.this).setX(0.0F);
                    } else
                    {
                        FloatingToolbarPopup._2D_get0(FloatingToolbarPopup.this).setX(right - (float)FloatingToolbarPopup._2D_get0(FloatingToolbarPopup.this).getWidth());
                        FloatingToolbarPopup._2D_get2(FloatingToolbarPopup.this).setX(FloatingToolbarPopup._2D_get0(FloatingToolbarPopup.this).getWidth() - startWidth);
                        FloatingToolbarPopup._2D_get8(FloatingToolbarPopup.this).setX(FloatingToolbarPopup._2D_get0(FloatingToolbarPopup.this).getWidth() - targetWidth);
                    }
                }

                final FloatingToolbarPopup this$1;
                final float val$left;
                final float val$right;
                final int val$startWidth;
                final int val$targetWidth;

            
            {
                this$1 = final_floatingtoolbarpopup;
                targetWidth = i;
                startWidth = j;
                left = f;
                right = F.this;
                super();
            }
            }
;
            Animation animation1 = overflowButtonTargetX. new Animation() {

                protected void applyTransformation(float f, Transformation transformation)
                {
                    int i = (int)((float)(targetHeight - startHeight) * f);
                    FloatingToolbarPopup._2D_wrap4(FloatingToolbarPopup._2D_get0(FloatingToolbarPopup.this), startHeight + i);
                    if(FloatingToolbarPopup._2D_get5(FloatingToolbarPopup.this))
                    {
                        FloatingToolbarPopup._2D_get0(FloatingToolbarPopup.this).setY(startY - (float)(FloatingToolbarPopup._2D_get0(FloatingToolbarPopup.this).getHeight() - startHeight));
                        FloatingToolbarPopup._2D_wrap2(FloatingToolbarPopup.this);
                    }
                }

                final FloatingToolbarPopup this$1;
                final int val$startHeight;
                final float val$startY;
                final int val$targetHeight;

            
            {
                this$1 = final_floatingtoolbarpopup;
                targetHeight = i;
                startHeight = j;
                startY = F.this;
                super();
            }
            }
;
            final float overflowButtonStartX = mOverflowButton.getX();
            Animation animation2;
            if(isInRTLMode())
                overflowButtonTargetX = ((float)targetWidth + overflowButtonStartX) - (float)mOverflowButton.getWidth();
            else
                overflowButtonTargetX = (overflowButtonStartX - (float)targetWidth) + (float)mOverflowButton.getWidth();
            animation2 = startWidth. new Animation() {

                protected void applyTransformation(float f, Transformation transformation)
                {
                    float f1 = overflowButtonStartX;
                    float f2 = overflowButtonTargetX;
                    float f3 = overflowButtonStartX;
                    int i;
                    float f4;
                    if(FloatingToolbarPopup._2D_wrap0(FloatingToolbarPopup.this))
                        i = 0;
                    else
                        i = FloatingToolbarPopup._2D_get0(FloatingToolbarPopup.this).getWidth() - startWidth;
                    f4 = i;
                    FloatingToolbarPopup._2D_get6(FloatingToolbarPopup.this).setX(f1 + (f2 - f3) * f + f4);
                }

                final FloatingToolbarPopup this$1;
                final float val$overflowButtonStartX;
                final float val$overflowButtonTargetX;
                final int val$startWidth;

            
            {
                this$1 = final_floatingtoolbarpopup;
                overflowButtonStartX = f;
                overflowButtonTargetX = f1;
                startWidth = I.this;
                super();
            }
            }
;
            animation.setInterpolator(mLogAccelerateInterpolator);
            animation.setDuration(getAdjustedDuration(250));
            animation1.setInterpolator(mFastOutSlowInInterpolator);
            animation1.setDuration(getAdjustedDuration(250));
            animation2.setInterpolator(mFastOutSlowInInterpolator);
            animation2.setDuration(getAdjustedDuration(250));
            mOpenOverflowAnimation.getAnimations().clear();
            mOpenOverflowAnimation.getAnimations().clear();
            mOpenOverflowAnimation.addAnimation(animation);
            mOpenOverflowAnimation.addAnimation(animation1);
            mOpenOverflowAnimation.addAnimation(animation2);
            mContentContainer.startAnimation(mOpenOverflowAnimation);
            mIsOverflowOpen = true;
            mMainPanel.animate().alpha(0.0F).withLayer().setInterpolator(mLinearOutSlowInInterpolator).setDuration(250L).start();
            mOverflowPanel.setAlpha(1.0F);
        }

        private void positionContentYCoordinatesIfOpeningOverflowUpwards()
        {
            if(mOpenOverflowUpwards)
            {
                mMainPanel.setY(mContentContainer.getHeight() - mMainPanelSize.getHeight());
                mOverflowButton.setY(mContentContainer.getHeight() - mOverflowButton.getHeight());
                mOverflowPanel.setY(mContentContainer.getHeight() - mOverflowPanelSize.getHeight());
            }
        }

        private void preparePopupContent()
        {
            mContentContainer.removeAllViews();
            if(hasOverflow())
                mContentContainer.addView(mOverflowPanel);
            mContentContainer.addView(mMainPanel);
            if(hasOverflow())
                mContentContainer.addView(mOverflowButton);
            setPanelsStatesAtRestingPosition();
            setContentAreaAsTouchableSurface();
            if(isInRTLMode())
            {
                mContentContainer.setAlpha(0.0F);
                mContentContainer.post(mPreparePopupContentRTLHelper);
            }
        }

        private void refreshCoordinatesAndOverflowDirection(Rect rect)
        {
            refreshViewPort();
            int i = Math.min(rect.centerX() - mPopupWindow.getWidth() / 2, mViewPortOnScreen.right - mPopupWindow.getWidth());
            int j = rect.top - mViewPortOnScreen.top;
            int k = mViewPortOnScreen.bottom - rect.bottom;
            int l = mMarginVertical * 2;
            int i1 = mLineHeight + l;
            int j1;
            int l1;
            int j2;
            if(!hasOverflow())
            {
                if(j >= i1)
                    j = rect.top - i1;
                else
                if(k >= i1)
                    j = rect.bottom;
                else
                if(k >= mLineHeight)
                    j = rect.bottom - mMarginVertical;
                else
                    j = Math.max(mViewPortOnScreen.top, rect.top - i1);
            } else
            {
                int l2 = calculateOverflowHeight(2) + l;
                int k1 = (mViewPortOnScreen.bottom - rect.top) + i1;
                int i2 = rect.bottom;
                int k2 = mViewPortOnScreen.top;
                if(j >= l2)
                {
                    updateOverflowHeight(j - l);
                    j = rect.top - mPopupWindow.getHeight();
                    mOpenOverflowUpwards = true;
                } else
                if(j >= i1 && k1 >= l2)
                {
                    updateOverflowHeight(k1 - l);
                    j = rect.top - i1;
                    mOpenOverflowUpwards = false;
                } else
                if(k >= l2)
                {
                    updateOverflowHeight(k - l);
                    j = rect.bottom;
                    mOpenOverflowUpwards = false;
                } else
                if(k >= i1 && mViewPortOnScreen.height() >= l2)
                {
                    updateOverflowHeight(((i2 - k2) + i1) - l);
                    j = (rect.bottom + i1) - mPopupWindow.getHeight();
                    mOpenOverflowUpwards = true;
                } else
                {
                    updateOverflowHeight(mViewPortOnScreen.height() - l);
                    j = mViewPortOnScreen.top;
                    mOpenOverflowUpwards = false;
                }
            }
            mParent.getRootView().getLocationOnScreen(mTmpCoords);
            l = mTmpCoords[0];
            j1 = mTmpCoords[1];
            mParent.getRootView().getLocationInWindow(mTmpCoords);
            l1 = mTmpCoords[0];
            j2 = mTmpCoords[1];
            mCoordsOnWindow.set(Math.max(0, i - (l - l1)), Math.max(0, j - (j1 - j2)));
        }

        private void refreshViewPort()
        {
            mParent.getWindowVisibleDisplayFrame(mViewPortOnScreen);
        }

        private void runDismissAnimation()
        {
            mDismissAnimation.start();
        }

        private void runHideAnimation()
        {
            mHideAnimation.start();
        }

        private void runShowAnimation()
        {
            mShowAnimation.start();
        }

        private void setButtonTagAndClickListener(View view, MenuItem menuitem)
        {
            view.setTag(menuitem);
            view.setOnClickListener(mMenuItemButtonOnClickListener);
        }

        private void setContentAreaAsTouchableSurface()
        {
            Preconditions.checkNotNull(mMainPanelSize);
            int i;
            int j;
            if(mIsOverflowOpen)
            {
                Preconditions.checkNotNull(mOverflowPanelSize);
                i = mOverflowPanelSize.getWidth();
                j = mOverflowPanelSize.getHeight();
            } else
            {
                i = mMainPanelSize.getWidth();
                j = mMainPanelSize.getHeight();
            }
            mTouchableRegion.set((int)mContentContainer.getX(), (int)mContentContainer.getY(), (int)mContentContainer.getX() + i, (int)mContentContainer.getY() + j);
        }

        private static void setHeight(View view, int i)
        {
            setSize(view, view.getLayoutParams().width, i);
        }

        private void setPanelsStatesAtRestingPosition()
        {
            mOverflowButton.setEnabled(true);
            mOverflowPanel.awakenScrollBars();
            if(mIsOverflowOpen)
            {
                Size size = mOverflowPanelSize;
                setSize(mContentContainer, size);
                mMainPanel.setAlpha(0.0F);
                mMainPanel.setVisibility(4);
                mOverflowPanel.setAlpha(1.0F);
                mOverflowPanel.setVisibility(0);
                mOverflowButton.setImageDrawable(mArrow);
                mOverflowButton.setContentDescription(mContext.getString(0x104022e));
                if(isInRTLMode())
                {
                    mContentContainer.setX(mMarginHorizontal);
                    mMainPanel.setX(0.0F);
                    mOverflowButton.setX(size.getWidth() - mOverflowButtonSize.getWidth());
                    mOverflowPanel.setX(0.0F);
                } else
                {
                    mContentContainer.setX(mPopupWindow.getWidth() - size.getWidth() - mMarginHorizontal);
                    mMainPanel.setX(-mContentContainer.getX());
                    mOverflowButton.setX(0.0F);
                    mOverflowPanel.setX(0.0F);
                }
                if(mOpenOverflowUpwards)
                {
                    mContentContainer.setY(mMarginVertical);
                    mMainPanel.setY(size.getHeight() - mContentContainer.getHeight());
                    mOverflowButton.setY(size.getHeight() - mOverflowButtonSize.getHeight());
                    mOverflowPanel.setY(0.0F);
                } else
                {
                    mContentContainer.setY(mMarginVertical);
                    mMainPanel.setY(0.0F);
                    mOverflowButton.setY(0.0F);
                    mOverflowPanel.setY(mOverflowButtonSize.getHeight());
                }
            } else
            {
                Size size1 = mMainPanelSize;
                setSize(mContentContainer, size1);
                mMainPanel.setAlpha(1.0F);
                mMainPanel.setVisibility(0);
                mOverflowPanel.setAlpha(0.0F);
                mOverflowPanel.setVisibility(4);
                mOverflowButton.setImageDrawable(mOverflow);
                mOverflowButton.setContentDescription(mContext.getString(0x104022f));
                if(hasOverflow())
                {
                    if(isInRTLMode())
                    {
                        mContentContainer.setX(mMarginHorizontal);
                        mMainPanel.setX(0.0F);
                        mOverflowButton.setX(0.0F);
                        mOverflowPanel.setX(0.0F);
                    } else
                    {
                        mContentContainer.setX(mPopupWindow.getWidth() - size1.getWidth() - mMarginHorizontal);
                        mMainPanel.setX(0.0F);
                        mOverflowButton.setX(size1.getWidth() - mOverflowButtonSize.getWidth());
                        mOverflowPanel.setX(size1.getWidth() - mOverflowPanelSize.getWidth());
                    }
                    if(mOpenOverflowUpwards)
                    {
                        mContentContainer.setY((mMarginVertical + mOverflowPanelSize.getHeight()) - size1.getHeight());
                        mMainPanel.setY(0.0F);
                        mOverflowButton.setY(0.0F);
                        mOverflowPanel.setY(size1.getHeight() - mOverflowPanelSize.getHeight());
                    } else
                    {
                        mContentContainer.setY(mMarginVertical);
                        mMainPanel.setY(0.0F);
                        mOverflowButton.setY(0.0F);
                        mOverflowPanel.setY(mOverflowButtonSize.getHeight());
                    }
                } else
                {
                    mContentContainer.setX(mMarginHorizontal);
                    mContentContainer.setY(mMarginVertical);
                    mMainPanel.setX(0.0F);
                    mMainPanel.setY(0.0F);
                }
            }
        }

        private static void setSize(View view, int i, int j)
        {
            view.setMinimumWidth(i);
            view.setMinimumHeight(j);
            android.view.ViewGroup.LayoutParams layoutparams = view.getLayoutParams();
            android.view.ViewGroup.LayoutParams layoutparams1 = layoutparams;
            if(layoutparams == null)
                layoutparams1 = new android.view.ViewGroup.LayoutParams(0, 0);
            layoutparams1.width = i;
            layoutparams1.height = j;
            view.setLayoutParams(layoutparams1);
        }

        private static void setSize(View view, Size size)
        {
            setSize(view, size.getWidth(), size.getHeight());
        }

        private void setTouchableSurfaceInsetsComputer()
        {
            ViewTreeObserver viewtreeobserver = mPopupWindow.getContentView().getRootView().getViewTreeObserver();
            viewtreeobserver.removeOnComputeInternalInsetsListener(mInsetsComputer);
            viewtreeobserver.addOnComputeInternalInsetsListener(mInsetsComputer);
        }

        private static void setWidth(View view, int i)
        {
            setSize(view, i, view.getLayoutParams().height);
        }

        private void setZeroTouchableSurface()
        {
            mTouchableRegion.setEmpty();
        }

        private void updateOverflowHeight(int i)
        {
            if(hasOverflow())
            {
                i = calculateOverflowHeight((i - mOverflowButtonSize.getHeight()) / mLineHeight);
                if(mOverflowPanelSize.getHeight() != i)
                    mOverflowPanelSize = new Size(mOverflowPanelSize.getWidth(), i);
                setSize(mOverflowPanel, mOverflowPanelSize);
                if(mIsOverflowOpen)
                {
                    setSize(mContentContainer, mOverflowPanelSize);
                    if(mOpenOverflowUpwards)
                    {
                        i = mOverflowPanelSize.getHeight() - i;
                        mContentContainer.setY(mContentContainer.getY() + (float)i);
                        mOverflowButton.setY(mOverflowButton.getY() - (float)i);
                    }
                } else
                {
                    setSize(mContentContainer, mMainPanelSize);
                }
                updatePopupSize();
            }
        }

        private void updatePopupSize()
        {
            int i = 0;
            int j = 0;
            if(mMainPanelSize != null)
            {
                i = Math.max(0, mMainPanelSize.getWidth());
                j = Math.max(0, mMainPanelSize.getHeight());
            }
            int k = j;
            int l = i;
            if(mOverflowPanelSize != null)
            {
                l = Math.max(i, mOverflowPanelSize.getWidth());
                k = Math.max(j, mOverflowPanelSize.getHeight());
            }
            mPopupWindow.setWidth(mMarginHorizontal * 2 + l);
            mPopupWindow.setHeight(mMarginVertical * 2 + k);
            maybeComputeTransitionDurationScale();
        }

        public void dismiss()
        {
            if(mDismissed)
            {
                return;
            } else
            {
                mHidden = false;
                mDismissed = true;
                mHideAnimation.cancel();
                runDismissAnimation();
                setZeroTouchableSurface();
                return;
            }
        }

        public void hide()
        {
            if(!isShowing())
            {
                return;
            } else
            {
                mHidden = true;
                runHideAnimation();
                setZeroTouchableSurface();
                return;
            }
        }

        public boolean isHidden()
        {
            return mHidden;
        }

        public boolean isShowing()
        {
            boolean flag;
            if(!mDismissed)
                flag = mHidden ^ true;
            else
                flag = false;
            return flag;
        }

        void lambda$_2D_com_android_internal_widget_FloatingToolbar$FloatingToolbarPopup_14167(android.view.ViewTreeObserver.InternalInsetsInfo internalinsetsinfo)
        {
            internalinsetsinfo.contentInsets.setEmpty();
            internalinsetsinfo.visibleInsets.setEmpty();
            internalinsetsinfo.touchableRegion.set(mTouchableRegion);
            internalinsetsinfo.setTouchableInsets(3);
        }

        void lambda$_2D_com_android_internal_widget_FloatingToolbar$FloatingToolbarPopup_65053(ImageButton imagebutton, View view)
        {
            if(mIsOverflowOpen)
            {
                imagebutton.setImageDrawable(mToOverflow);
                mToOverflow.start();
                closeOverflow();
            } else
            {
                imagebutton.setImageDrawable(mToArrow);
                mToArrow.start();
                openOverflow();
            }
        }

        void lambda$_2D_com_android_internal_widget_FloatingToolbar$FloatingToolbarPopup_66418(OverflowPanel overflowpanel, AdapterView adapterview, View view, int i, long l)
        {
            overflowpanel = (MenuItem)overflowpanel.getAdapter().getItem(i);
            if(mOnMenuItemClickListener != null)
                mOnMenuItemClickListener.onMenuItemClick(overflowpanel);
        }

        public List layoutMainPanelItems(List list, int i)
        {
            int j;
            LinkedList linkedlist;
            int k;
            int l;
            Preconditions.checkNotNull(list);
            j = i;
            linkedlist = new LinkedList();
            LinkedList linkedlist1 = new LinkedList();
            for(list = list.iterator(); list.hasNext();)
            {
                MenuItem menuitem1 = (MenuItem)list.next();
                if(menuitem1.requiresOverflow())
                    linkedlist1.add(menuitem1);
                else
                    linkedlist.add(menuitem1);
            }

            linkedlist.addAll(linkedlist1);
            mMainPanel.removeAllViews();
            mMainPanel.setPaddingRelative(0, 0, 0, 0);
            k = -1;
            l = 1;
_L6:
            if(linkedlist.isEmpty()) goto _L2; else goto _L1
_L1:
            MenuItem menuitem = (MenuItem)linkedlist.peek();
            if(l != 0 || !menuitem.requiresOverflow()) goto _L3; else goto _L2
_L2:
            if(!linkedlist.isEmpty())
                mMainPanel.setPaddingRelative(0, 0, mOverflowButtonSize.getWidth(), 0);
            mMainPanelSize = measure(mMainPanel);
            return linkedlist;
_L3:
            list = FloatingToolbar._2D_wrap4(mContext, menuitem, mIconTextSpacing);
            if(l != 0)
                list.setPaddingRelative((int)((double)list.getPaddingStart() * 1.5D), list.getPaddingTop(), list.getPaddingEnd(), list.getPaddingBottom());
            Object obj;
            int i1;
            int j1;
            int k1;
            View view;
            android.view.ViewGroup.LayoutParams layoutparams;
            if(linkedlist.size() == 1)
                i1 = 1;
            else
                i1 = 0;
            if(i1 != 0)
                list.setPaddingRelative(list.getPaddingStart(), list.getPaddingTop(), (int)((double)list.getPaddingEnd() * 1.5D), list.getPaddingBottom());
            list.measure(0, 0);
            j1 = Math.min(list.getMeasuredWidth(), i);
            if(l == 0 && k != menuitem.getGroupId())
                l = 1;
            else
                l = 0;
            if(l != 0)
                k = list.getPaddingEnd() * 2;
            else
                k = 0;
            if(j1 <= j - mOverflowButtonSize.getWidth() - k)
                k1 = 1;
            else
                k1 = 0;
            if(i1 != 0 && j1 <= j - k)
                i1 = 1;
            else
                i1 = 0;
            if(k1 == 0 && i1 == 0) goto _L2; else goto _L4
_L4:
            if(l != 0)
            {
                view = FloatingToolbar._2D_wrap3(mContext);
                l = view.getLayoutParams().width;
                obj = mMainPanel.getChildAt(mMainPanel.getChildCount() - 1);
                k1 = ((View) (obj)).getPaddingEnd();
                i1 = k / 2;
                ((View) (obj)).setPaddingRelative(((View) (obj)).getPaddingStart(), ((View) (obj)).getPaddingTop(), (k1 + i1) - l, ((View) (obj)).getPaddingBottom());
                layoutparams = ((View) (obj)).getLayoutParams();
                layoutparams.width = layoutparams.width + (k / 2 - l);
                ((View) (obj)).setLayoutParams(layoutparams);
                list.setPaddingRelative(list.getPaddingStart() + k / 2, list.getPaddingTop(), list.getPaddingEnd(), list.getPaddingBottom());
                mMainPanel.addView(view);
            }
            setButtonTagAndClickListener(list, menuitem);
            list.setTooltipText(menuitem.getTooltipText());
            mMainPanel.addView(list);
            obj = list.getLayoutParams();
            obj.width = k / 2 + j1;
            list.setLayoutParams(((android.view.ViewGroup.LayoutParams) (obj)));
            j -= j1 + k;
            linkedlist.pop();
            k = menuitem.getGroupId();
            l = 0;
            if(true) goto _L6; else goto _L5
_L5:
        }

        public void layoutMenuItems(List list, android.view.MenuItem.OnMenuItemClickListener onmenuitemclicklistener, int i)
        {
            mOnMenuItemClickListener = onmenuitemclicklistener;
            cancelOverflowAnimations();
            clearPanels();
            list = layoutMainPanelItems(list, getAdjustedToolbarWidth(i));
            if(!list.isEmpty())
                layoutOverflowPanelItems(list);
            updatePopupSize();
        }

        public void show(Rect rect)
        {
            Preconditions.checkNotNull(rect);
            if(isShowing())
            {
                return;
            } else
            {
                mHidden = false;
                mDismissed = false;
                cancelDismissAndHideAnimations();
                cancelOverflowAnimations();
                refreshCoordinatesAndOverflowDirection(rect);
                preparePopupContent();
                mPopupWindow.showAtLocation(mParent, 0, mCoordsOnWindow.x, mCoordsOnWindow.y);
                setTouchableSurfaceInsetsComputer();
                runShowAnimation();
                return;
            }
        }

        public void updateCoordinates(Rect rect)
        {
            Preconditions.checkNotNull(rect);
            if(!isShowing() || mPopupWindow.isShowing() ^ true)
            {
                return;
            } else
            {
                cancelOverflowAnimations();
                refreshCoordinatesAndOverflowDirection(rect);
                preparePopupContent();
                mPopupWindow.update(mCoordsOnWindow.x, mCoordsOnWindow.y, mPopupWindow.getWidth(), mPopupWindow.getHeight());
                return;
            }
        }

        private static final int MAX_OVERFLOW_SIZE = 4;
        private static final int MIN_OVERFLOW_SIZE = 2;
        private final Drawable mArrow;
        private final AnimationSet mCloseOverflowAnimation = new AnimationSet(true);
        private final ViewGroup mContentContainer;
        private final Context mContext;
        private final Point mCoordsOnWindow = new Point();
        private final AnimatorSet mDismissAnimation;
        private boolean mDismissed;
        private final Interpolator mFastOutLinearInInterpolator;
        private final Interpolator mFastOutSlowInInterpolator;
        private boolean mHidden;
        private final AnimatorSet mHideAnimation;
        private final int mIconTextSpacing;
        private final android.view.ViewTreeObserver.OnComputeInternalInsetsListener mInsetsComputer = new _.Lambda.nZD8NeHZxo4kFQHu5zIWiAfZj2s._cls1(this);
        private boolean mIsOverflowOpen;
        private final int mLineHeight;
        private final Interpolator mLinearOutSlowInInterpolator;
        private final Interpolator mLogAccelerateInterpolator = new LogAccelerateInterpolator(null);
        private final ViewGroup mMainPanel = createMainPanel();
        private Size mMainPanelSize;
        private final int mMarginHorizontal;
        private final int mMarginVertical;
        private final android.view.View.OnClickListener mMenuItemButtonOnClickListener = new _cls2();
        private android.view.MenuItem.OnMenuItemClickListener mOnMenuItemClickListener;
        private final AnimationSet mOpenOverflowAnimation = new AnimationSet(true);
        private boolean mOpenOverflowUpwards;
        private final Drawable mOverflow;
        private final android.view.animation.Animation.AnimationListener mOverflowAnimationListener = createOverflowAnimationListener();
        private final ImageButton mOverflowButton = createOverflowButton();
        private final Size mOverflowButtonSize;
        private final OverflowPanel mOverflowPanel = createOverflowPanel();
        private Size mOverflowPanelSize;
        private final OverflowPanelViewHelper mOverflowPanelViewHelper;
        private final View mParent;
        private final PopupWindow mPopupWindow;
        private final Runnable mPreparePopupContentRTLHelper = new _cls1();
        private final AnimatorSet mShowAnimation;
        private final int mTmpCoords[] = new int[2];
        private final AnimatedVectorDrawable mToArrow;
        private final AnimatedVectorDrawable mToOverflow;
        private final Region mTouchableRegion = new Region();
        private int mTransitionDurationScale;
        private final Rect mViewPortOnScreen = new Rect();

        public FloatingToolbarPopup(Context context, View view)
        {
            mDismissed = true;
            mParent = (View)Preconditions.checkNotNull(view);
            mContext = (Context)Preconditions.checkNotNull(context);
            mContentContainer = FloatingToolbar._2D_wrap2(context);
            mPopupWindow = FloatingToolbar._2D_wrap5(mContentContainer);
            mMarginHorizontal = view.getResources().getDimensionPixelSize(0x1050086);
            mMarginVertical = view.getResources().getDimensionPixelSize(0x1050092);
            mLineHeight = context.getResources().getDimensionPixelSize(0x1050085);
            mIconTextSpacing = context.getResources().getDimensionPixelSize(0x1050089);
            mFastOutSlowInInterpolator = AnimationUtils.loadInterpolator(mContext, 0x10c000d);
            mLinearOutSlowInInterpolator = AnimationUtils.loadInterpolator(mContext, 0x10c000e);
            mFastOutLinearInInterpolator = AnimationUtils.loadInterpolator(mContext, 0x10c000f);
            mArrow = mContext.getResources().getDrawable(0x10802db, mContext.getTheme());
            mArrow.setAutoMirrored(true);
            mOverflow = mContext.getResources().getDrawable(0x10802d9, mContext.getTheme());
            mOverflow.setAutoMirrored(true);
            mToArrow = (AnimatedVectorDrawable)mContext.getResources().getDrawable(0x10802da, mContext.getTheme());
            mToArrow.setAutoMirrored(true);
            mToOverflow = (AnimatedVectorDrawable)mContext.getResources().getDrawable(0x10802dc, mContext.getTheme());
            mToOverflow.setAutoMirrored(true);
            mOverflowButtonSize = measure(mOverflowButton);
            mOverflowPanelViewHelper = new OverflowPanelViewHelper(mContext);
            mOpenOverflowAnimation.setAnimationListener(mOverflowAnimationListener);
            mCloseOverflowAnimation.setAnimationListener(mOverflowAnimationListener);
            mShowAnimation = FloatingToolbar._2D_wrap0(mContentContainer);
            mDismissAnimation = FloatingToolbar._2D_wrap1(mContentContainer, 150, new _cls3());
            mHideAnimation = FloatingToolbar._2D_wrap1(mContentContainer, 0, new _cls4());
        }
    }

    private static final class FloatingToolbarPopup.LogAccelerateInterpolator
        implements Interpolator
    {

        private static float computeLog(float f, int i)
        {
            return (float)(1.0D - Math.pow(i, -f));
        }

        public float getInterpolation(float f)
        {
            return 1.0F - computeLog(1.0F - f, 100) * LOGS_SCALE;
        }

        private static final int BASE = 100;
        private static final float LOGS_SCALE = 1.0F / computeLog(1.0F, 100);


        private FloatingToolbarPopup.LogAccelerateInterpolator()
        {
        }

        FloatingToolbarPopup.LogAccelerateInterpolator(FloatingToolbarPopup.LogAccelerateInterpolator logaccelerateinterpolator)
        {
            this();
        }
    }

    private static final class FloatingToolbarPopup.OverflowPanel extends ListView
    {

        protected boolean awakenScrollBars()
        {
            return super.awakenScrollBars();
        }

        public boolean dispatchTouchEvent(MotionEvent motionevent)
        {
            if(FloatingToolbarPopup._2D_wrap1(mPopup))
                return true;
            else
                return super.dispatchTouchEvent(motionevent);
        }

        protected void onMeasure(int i, int j)
        {
            super.onMeasure(i, android.view.View.MeasureSpec.makeMeasureSpec(FloatingToolbarPopup._2D_get9(mPopup).getHeight() - FloatingToolbarPopup._2D_get7(mPopup).getHeight(), 0x40000000));
        }

        private final FloatingToolbarPopup mPopup;

        FloatingToolbarPopup.OverflowPanel(FloatingToolbarPopup floatingtoolbarpopup)
        {
            super(FloatingToolbarPopup._2D_get1((FloatingToolbarPopup)Preconditions.checkNotNull(floatingtoolbarpopup)));
            mPopup = floatingtoolbarpopup;
            setScrollBarDefaultDelayBeforeFade(ViewConfiguration.getScrollDefaultDelay() * 3);
            setScrollIndicators(3);
        }
    }

    private static final class FloatingToolbarPopup.OverflowPanelViewHelper
    {

        private View createMenuButton(MenuItem menuitem)
        {
            menuitem = FloatingToolbar._2D_wrap4(mContext, menuitem, mIconTextSpacing);
            menuitem.setPadding(mSidePadding, 0, mSidePadding, 0);
            return menuitem;
        }

        public int calculateWidth(MenuItem menuitem)
        {
            FloatingToolbar._2D_wrap6(mCalculator, menuitem, mIconTextSpacing);
            mCalculator.measure(0, 0);
            return mCalculator.getMeasuredWidth();
        }

        public View getView(MenuItem menuitem, int i, View view)
        {
            Preconditions.checkNotNull(menuitem);
            if(view != null)
                FloatingToolbar._2D_wrap6(view, menuitem, mIconTextSpacing);
            else
                view = createMenuButton(menuitem);
            view.setMinimumWidth(i);
            return view;
        }

        private final View mCalculator = createMenuButton(null);
        private final Context mContext;
        private final int mIconTextSpacing;
        private final int mSidePadding;

        public FloatingToolbarPopup.OverflowPanelViewHelper(Context context)
        {
            mContext = (Context)Preconditions.checkNotNull(context);
            mIconTextSpacing = context.getResources().getDimensionPixelSize(0x1050089);
            mSidePadding = context.getResources().getDimensionPixelSize(0x105008f);
        }
    }


    static FloatingToolbarPopup _2D_get0(FloatingToolbar floatingtoolbar)
    {
        return floatingtoolbar.mPopup;
    }

    static boolean _2D_set0(FloatingToolbar floatingtoolbar, boolean flag)
    {
        floatingtoolbar.mWidthChanged = flag;
        return flag;
    }

    static AnimatorSet _2D_wrap0(View view)
    {
        return createEnterAnimation(view);
    }

    static AnimatorSet _2D_wrap1(View view, int i, android.animation.Animator.AnimatorListener animatorlistener)
    {
        return createExitAnimation(view, i, animatorlistener);
    }

    static ViewGroup _2D_wrap2(Context context)
    {
        return createContentContainer(context);
    }

    static View _2D_wrap3(Context context)
    {
        return createDivider(context);
    }

    static View _2D_wrap4(Context context, MenuItem menuitem, int i)
    {
        return createMenuItemButton(context, menuitem, i);
    }

    static PopupWindow _2D_wrap5(ViewGroup viewgroup)
    {
        return createPopupWindow(viewgroup);
    }

    static void _2D_wrap6(View view, MenuItem menuitem, int i)
    {
        updateMenuItemButton(view, menuitem, i);
    }

    public FloatingToolbar(Window window)
    {
        mShowingMenuItems = new ArrayList();
        mMenuItemClickListener = NO_OP_MENUITEM_CLICK_LISTENER;
        mWidthChanged = true;
        mContext = applyDefaultTheme(window.getContext());
        mWindow = (Window)Preconditions.checkNotNull(window);
        mPopup = new FloatingToolbarPopup(mContext, window.getDecorView());
    }

    private static Context applyDefaultTheme(Context context)
    {
        TypedArray typedarray = context.obtainStyledAttributes(new int[] {
            0x111004e
        });
        int i;
        if(typedarray.getBoolean(0, true))
            i = 0x1030237;
        else
            i = 0x1030224;
        typedarray.recycle();
        return new ContextThemeWrapper(context, i);
    }

    private static ViewGroup createContentContainer(Context context)
    {
        context = (ViewGroup)LayoutInflater.from(context).inflate(0x109005f, null);
        context.setLayoutParams(new android.view.ViewGroup.LayoutParams(-2, -2));
        context.setTag("floating_toolbar");
        return context;
    }

    private static View createDivider(Context context)
    {
        View view = new View(context);
        int i = (int)TypedValue.applyDimension(1, 1.0F, context.getResources().getDisplayMetrics());
        android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(i, -1);
        layoutparams.setMarginsRelative(0, i * 10, 0, i * 10);
        view.setLayoutParams(layoutparams);
        context = context.obtainStyledAttributes((new TypedValue()).data, new int[] {
            0x1110034
        });
        view.setBackgroundColor(context.getColor(0, 0));
        context.recycle();
        view.setImportantForAccessibility(2);
        view.setEnabled(false);
        view.setFocusable(false);
        view.setContentDescription(null);
        return view;
    }

    private static AnimatorSet createEnterAnimation(View view)
    {
        AnimatorSet animatorset = new AnimatorSet();
        animatorset.playTogether(new Animator[] {
            ObjectAnimator.ofFloat(view, View.ALPHA, new float[] {
                0.0F, 1.0F
            }).setDuration(150L)
        });
        return animatorset;
    }

    private static AnimatorSet createExitAnimation(View view, int i, android.animation.Animator.AnimatorListener animatorlistener)
    {
        AnimatorSet animatorset = new AnimatorSet();
        animatorset.playTogether(new Animator[] {
            ObjectAnimator.ofFloat(view, View.ALPHA, new float[] {
                1.0F, 0.0F
            }).setDuration(100L)
        });
        animatorset.setStartDelay(i);
        animatorset.addListener(animatorlistener);
        return animatorset;
    }

    private static View createMenuItemButton(Context context, MenuItem menuitem, int i)
    {
        context = LayoutInflater.from(context).inflate(0x1090060, null);
        if(menuitem != null)
            updateMenuItemButton(context, menuitem, i);
        return context;
    }

    private static PopupWindow createPopupWindow(ViewGroup viewgroup)
    {
        LinearLayout linearlayout = new LinearLayout(viewgroup.getContext());
        PopupWindow popupwindow = new PopupWindow(linearlayout);
        popupwindow.setClippingEnabled(false);
        popupwindow.setWindowLayoutType(1005);
        popupwindow.setAnimationStyle(0);
        popupwindow.setBackgroundDrawable(new ColorDrawable(0));
        viewgroup.setLayoutParams(new android.view.ViewGroup.LayoutParams(-2, -2));
        linearlayout.addView(viewgroup);
        return popupwindow;
    }

    private void doShow()
    {
        List list = getVisibleAndEnabledMenuItems(mMenu);
        tidy(list);
        if(!isCurrentlyShowing(list) || mWidthChanged)
        {
            mPopup.dismiss();
            mPopup.layoutMenuItems(list, mMenuItemClickListener, mSuggestedWidth);
            mShowingMenuItems = list;
        }
        if(mPopup.isShowing()) goto _L2; else goto _L1
_L1:
        mPopup.show(mContentRect);
_L4:
        mWidthChanged = false;
        mPreviousContentRect.set(mContentRect);
        return;
_L2:
        if(!mPreviousContentRect.equals(mContentRect))
            mPopup.updateCoordinates(mContentRect);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private List getVisibleAndEnabledMenuItems(Menu menu)
    {
        ArrayList arraylist = new ArrayList();
        int i = 0;
        while(menu != null && i < menu.size()) 
        {
            MenuItem menuitem = menu.getItem(i);
            if(menuitem.isVisible() && menuitem.isEnabled())
            {
                android.view.SubMenu submenu = menuitem.getSubMenu();
                if(submenu != null)
                    arraylist.addAll(getVisibleAndEnabledMenuItems(((Menu) (submenu))));
                else
                    arraylist.add(menuitem);
            }
            i++;
        }
        return arraylist;
    }

    private boolean isCurrentlyShowing(List list)
    {
        if(mShowingMenuItems == null || list.size() != mShowingMenuItems.size())
            return false;
        int i = list.size();
        for(int j = 0; j < i; j++)
        {
            MenuItem menuitem = (MenuItem)list.get(j);
            MenuItem menuitem1 = (MenuItem)mShowingMenuItems.get(j);
            if(menuitem.getItemId() != menuitem1.getItemId() || TextUtils.equals(menuitem.getTitle(), menuitem1.getTitle()) ^ true || Objects.equals(menuitem.getIcon(), menuitem1.getIcon()) ^ true || menuitem.getGroupId() != menuitem1.getGroupId())
                return false;
        }

        return true;
    }

    static boolean lambda$_2D_com_android_internal_widget_FloatingToolbar_3099(MenuItem menuitem)
    {
        return false;
    }

    private void registerOrientationHandler()
    {
        unregisterOrientationHandler();
        mWindow.getDecorView().addOnLayoutChangeListener(mOrientationChangeHandler);
    }

    private void tidy(List list)
    {
        int i = -1;
        Drawable drawable = null;
        int j = list.size();
        for(int k = 0; k < j; k++)
        {
            MenuItem menuitem = (MenuItem)list.get(k);
            if(menuitem.getItemId() == 0x1020041)
            {
                i = k;
                drawable = menuitem.getIcon();
            }
            if(!TextUtils.isEmpty(menuitem.getTitle()))
                menuitem.setIcon(null);
        }

        if(i > -1)
        {
            MenuItem menuitem1 = (MenuItem)list.remove(i);
            menuitem1.setIcon(drawable);
            list.add(0, menuitem1);
        }
    }

    private void unregisterOrientationHandler()
    {
        mWindow.getDecorView().removeOnLayoutChangeListener(mOrientationChangeHandler);
    }

    private static void updateMenuItemButton(View view, MenuItem menuitem, int i)
    {
        Object obj = (TextView)view.findViewById(0x102026e);
        ImageView imageview;
        if(TextUtils.isEmpty(menuitem.getTitle()))
        {
            ((TextView) (obj)).setVisibility(8);
        } else
        {
            ((TextView) (obj)).setVisibility(0);
            ((TextView) (obj)).setText(menuitem.getTitle());
        }
        imageview = (ImageView)view.findViewById(0x102026c);
        if(menuitem.getIcon() == null)
        {
            imageview.setVisibility(8);
            if(obj != null)
                ((TextView) (obj)).setPaddingRelative(0, 0, 0, 0);
        } else
        {
            imageview.setVisibility(0);
            imageview.setImageDrawable(menuitem.getIcon());
            if(obj != null)
                ((TextView) (obj)).setPaddingRelative(i, 0, 0, 0);
        }
        obj = menuitem.getContentDescription();
        if(TextUtils.isEmpty(((CharSequence) (obj))))
            view.setContentDescription(menuitem.getTitle());
        else
            view.setContentDescription(((CharSequence) (obj)));
    }

    public void dismiss()
    {
        unregisterOrientationHandler();
        mPopup.dismiss();
    }

    public void hide()
    {
        mPopup.hide();
    }

    public boolean isHidden()
    {
        return mPopup.isHidden();
    }

    public boolean isShowing()
    {
        return mPopup.isShowing();
    }

    public FloatingToolbar setContentRect(Rect rect)
    {
        mContentRect.set((Rect)Preconditions.checkNotNull(rect));
        return this;
    }

    public FloatingToolbar setMenu(Menu menu)
    {
        mMenu = (Menu)Preconditions.checkNotNull(menu);
        return this;
    }

    public FloatingToolbar setOnMenuItemClickListener(android.view.MenuItem.OnMenuItemClickListener onmenuitemclicklistener)
    {
        if(onmenuitemclicklistener != null)
            mMenuItemClickListener = onmenuitemclicklistener;
        else
            mMenuItemClickListener = NO_OP_MENUITEM_CLICK_LISTENER;
        return this;
    }

    public FloatingToolbar setSuggestedWidth(int i)
    {
        boolean flag;
        if((double)Math.abs(i - mSuggestedWidth) > (double)mSuggestedWidth * 0.20000000000000001D)
            flag = true;
        else
            flag = false;
        mWidthChanged = flag;
        mSuggestedWidth = i;
        return this;
    }

    public FloatingToolbar show()
    {
        registerOrientationHandler();
        doShow();
        return this;
    }

    public FloatingToolbar updateLayout()
    {
        if(mPopup.isShowing())
            doShow();
        return this;
    }

    public static final String FLOATING_TOOLBAR_TAG = "floating_toolbar";
    private static final android.view.MenuItem.OnMenuItemClickListener NO_OP_MENUITEM_CLICK_LISTENER;
    private final Rect mContentRect = new Rect();
    private final Context mContext;
    private Menu mMenu;
    private android.view.MenuItem.OnMenuItemClickListener mMenuItemClickListener;
    private final android.view.View.OnLayoutChangeListener mOrientationChangeHandler = new android.view.View.OnLayoutChangeListener() {

        public void onLayoutChange(View view, int i, int j, int k, int l, int i1, int j1, 
                int k1, int l1)
        {
            mNewRect.set(i, j, k, l);
            mOldRect.set(i1, j1, k1, l1);
            if(FloatingToolbar._2D_get0(FloatingToolbar.this).isShowing() && mNewRect.equals(mOldRect) ^ true)
            {
                FloatingToolbar._2D_set0(FloatingToolbar.this, true);
                updateLayout();
            }
        }

        private final Rect mNewRect = new Rect();
        private final Rect mOldRect = new Rect();
        final FloatingToolbar this$0;

            
            {
                this$0 = FloatingToolbar.this;
                super();
            }
    }
;
    private final FloatingToolbarPopup mPopup;
    private final Rect mPreviousContentRect = new Rect();
    private List mShowingMenuItems;
    private int mSuggestedWidth;
    private boolean mWidthChanged;
    private final Window mWindow;

    static 
    {
        NO_OP_MENUITEM_CLICK_LISTENER = _.Lambda.nZD8NeHZxo4kFQHu5zIWiAfZj2s.$INST$0;
    }

    // Unreferenced inner class com/android/internal/widget/FloatingToolbar$FloatingToolbarPopup$1

/* anonymous class */
    class FloatingToolbarPopup._cls1
        implements Runnable
    {

        public void run()
        {
            FloatingToolbarPopup._2D_wrap5(FloatingToolbarPopup.this);
            FloatingToolbarPopup._2D_wrap3(FloatingToolbarPopup.this);
            FloatingToolbarPopup._2D_get0(FloatingToolbarPopup.this).setAlpha(1.0F);
        }

        final FloatingToolbarPopup this$1;

            
            {
                this$1 = FloatingToolbarPopup.this;
                super();
            }
    }


    // Unreferenced inner class com/android/internal/widget/FloatingToolbar$FloatingToolbarPopup$2

/* anonymous class */
    class FloatingToolbarPopup._cls2
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            if((view.getTag() instanceof MenuItem) && FloatingToolbarPopup._2D_get4(FloatingToolbarPopup.this) != null)
                FloatingToolbarPopup._2D_get4(FloatingToolbarPopup.this).onMenuItemClick((MenuItem)view.getTag());
        }

        final FloatingToolbarPopup this$1;

            
            {
                this$1 = FloatingToolbarPopup.this;
                super();
            }
    }


    // Unreferenced inner class com/android/internal/widget/FloatingToolbar$FloatingToolbarPopup$3

/* anonymous class */
    class FloatingToolbarPopup._cls3 extends AnimatorListenerAdapter
    {

        public void onAnimationEnd(Animator animator)
        {
            FloatingToolbarPopup._2D_get11(FloatingToolbarPopup.this).dismiss();
            FloatingToolbarPopup._2D_get0(FloatingToolbarPopup.this).removeAllViews();
        }

        final FloatingToolbarPopup this$1;

            
            {
                this$1 = FloatingToolbarPopup.this;
                super();
            }
    }


    // Unreferenced inner class com/android/internal/widget/FloatingToolbar$FloatingToolbarPopup$4

/* anonymous class */
    class FloatingToolbarPopup._cls4 extends AnimatorListenerAdapter
    {

        public void onAnimationEnd(Animator animator)
        {
            FloatingToolbarPopup._2D_get11(FloatingToolbarPopup.this).dismiss();
        }

        final FloatingToolbarPopup this$1;

            
            {
                this$1 = FloatingToolbarPopup.this;
                super();
            }
    }

}
