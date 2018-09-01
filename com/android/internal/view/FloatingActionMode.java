// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.*;
import com.android.internal.util.Preconditions;
import com.android.internal.view.menu.MenuBuilder;
import com.android.internal.widget.FloatingToolbar;
import java.util.Arrays;

public final class FloatingActionMode extends ActionMode
{
    private static final class FloatingToolbarVisibilityHelper
    {

        public void activate()
        {
            mHideRequested = false;
            mMoving = false;
            mOutOfBounds = false;
            mWindowFocused = true;
            mActive = true;
        }

        public void deactivate()
        {
            mActive = false;
            mToolbar.dismiss();
        }

        public void setHideRequested(boolean flag)
        {
            mHideRequested = flag;
        }

        public void setMoving(boolean flag)
        {
            boolean flag1;
            if(System.currentTimeMillis() - mLastShowTime > 500L)
                flag1 = true;
            else
                flag1 = false;
            if(!flag || flag1)
                mMoving = flag;
        }

        public void setOutOfBounds(boolean flag)
        {
            mOutOfBounds = flag;
        }

        public void setWindowFocused(boolean flag)
        {
            mWindowFocused = flag;
        }

        public void updateToolbarVisibility()
        {
            if(!mActive)
                return;
            if(mHideRequested || mMoving || mOutOfBounds || mWindowFocused ^ true)
            {
                mToolbar.hide();
            } else
            {
                mToolbar.show();
                mLastShowTime = System.currentTimeMillis();
            }
        }

        private static final long MIN_SHOW_DURATION_FOR_MOVE_HIDE = 500L;
        private boolean mActive;
        private boolean mHideRequested;
        private long mLastShowTime;
        private boolean mMoving;
        private boolean mOutOfBounds;
        private final FloatingToolbar mToolbar;
        private boolean mWindowFocused;

        public FloatingToolbarVisibilityHelper(FloatingToolbar floatingtoolbar)
        {
            mWindowFocused = true;
            mToolbar = (FloatingToolbar)Preconditions.checkNotNull(floatingtoolbar);
        }
    }


    static android.view.ActionMode.Callback2 _2D_get0(FloatingActionMode floatingactionmode)
    {
        return floatingactionmode.mCallback;
    }

    static FloatingToolbarVisibilityHelper _2D_get1(FloatingActionMode floatingactionmode)
    {
        return floatingactionmode.mFloatingToolbarVisibilityHelper;
    }

    static boolean _2D_wrap0(FloatingActionMode floatingactionmode)
    {
        return floatingactionmode.isViewStillActive();
    }

    public FloatingActionMode(Context context, android.view.ActionMode.Callback2 callback2, View view, FloatingToolbar floatingtoolbar)
    {
        mContext = (Context)Preconditions.checkNotNull(context);
        mCallback = (android.view.ActionMode.Callback2)Preconditions.checkNotNull(callback2);
        mMenu = (new MenuBuilder(context)).setDefaultShowAsAction(1);
        setType(1);
        mMenu.setCallback(new com.android.internal.view.menu.MenuBuilder.Callback() {

            public boolean onMenuItemSelected(MenuBuilder menubuilder, MenuItem menuitem)
            {
                return FloatingActionMode._2D_get0(FloatingActionMode.this).onActionItemClicked(FloatingActionMode.this, menuitem);
            }

            public void onMenuModeChange(MenuBuilder menubuilder)
            {
            }

            final FloatingActionMode this$0;

            
            {
                this$0 = FloatingActionMode.this;
                super();
            }
        }
);
        mOriginatingView = (View)Preconditions.checkNotNull(view);
        mOriginatingView.getLocationOnScreen(mViewPositionOnScreen);
        mBottomAllowance = context.getResources().getDimensionPixelSize(0x1050049);
        setFloatingToolbar((FloatingToolbar)Preconditions.checkNotNull(floatingtoolbar));
    }

    private static boolean intersectsClosed(Rect rect, Rect rect1)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(rect.left <= rect1.right)
        {
            flag1 = flag;
            if(rect1.left <= rect.right)
            {
                flag1 = flag;
                if(rect.top <= rect1.bottom)
                {
                    flag1 = flag;
                    if(rect1.top <= rect.bottom)
                        flag1 = true;
                }
            }
        }
        return flag1;
    }

    private boolean isContentRectWithinBounds()
    {
        ((WindowManager)mContext.getSystemService(android/view/WindowManager)).getDefaultDisplay().getRealSize(mDisplaySize);
        mScreenRect.set(0, 0, mDisplaySize.x, mDisplaySize.y);
        boolean flag;
        if(intersectsClosed(mContentRectOnScreen, mScreenRect))
            flag = intersectsClosed(mContentRectOnScreen, mViewRectOnScreen);
        else
            flag = false;
        return flag;
    }

    private boolean isViewStillActive()
    {
        boolean flag = false;
        if(mOriginatingView.getWindowVisibility() == 0)
            flag = mOriginatingView.isShown();
        return flag;
    }

    private void repositionToolbar()
    {
        mContentRectOnScreen.set(mContentRect);
        android.view.ViewParent viewparent = mOriginatingView.getParent();
        if(viewparent instanceof ViewGroup)
        {
            ((ViewGroup)viewparent).getChildVisibleRect(mOriginatingView, mContentRectOnScreen, null, true);
            mContentRectOnScreen.offset(mRootViewPositionOnScreen[0], mRootViewPositionOnScreen[1]);
        } else
        {
            mContentRectOnScreen.offset(mViewPositionOnScreen[0], mViewPositionOnScreen[1]);
        }
        if(isContentRectWithinBounds())
        {
            mFloatingToolbarVisibilityHelper.setOutOfBounds(false);
            mContentRectOnScreen.set(Math.max(mContentRectOnScreen.left, mViewRectOnScreen.left), Math.max(mContentRectOnScreen.top, mViewRectOnScreen.top), Math.min(mContentRectOnScreen.right, mViewRectOnScreen.right), Math.min(mContentRectOnScreen.bottom, mViewRectOnScreen.bottom + mBottomAllowance));
            if(!mContentRectOnScreen.equals(mPreviousContentRectOnScreen))
            {
                mOriginatingView.removeCallbacks(mMovingOff);
                mFloatingToolbarVisibilityHelper.setMoving(true);
                mOriginatingView.postDelayed(mMovingOff, 50L);
                mFloatingToolbar.setContentRect(mContentRectOnScreen);
                mFloatingToolbar.updateLayout();
            }
        } else
        {
            mFloatingToolbarVisibilityHelper.setOutOfBounds(true);
            mContentRectOnScreen.setEmpty();
        }
        mFloatingToolbarVisibilityHelper.updateToolbarVisibility();
        mPreviousContentRectOnScreen.set(mContentRectOnScreen);
    }

    private void reset()
    {
        mFloatingToolbar.dismiss();
        mFloatingToolbarVisibilityHelper.deactivate();
        mOriginatingView.removeCallbacks(mMovingOff);
        mOriginatingView.removeCallbacks(mHideOff);
    }

    private void setFloatingToolbar(FloatingToolbar floatingtoolbar)
    {
        mFloatingToolbar = floatingtoolbar.setMenu(mMenu).setOnMenuItemClickListener(new _.Lambda.IoKM3AcgDw3Ok5aFi0zlym2p3IA(this));
        mFloatingToolbarVisibilityHelper = new FloatingToolbarVisibilityHelper(mFloatingToolbar);
        mFloatingToolbarVisibilityHelper.activate();
    }

    public void finish()
    {
        reset();
        mCallback.onDestroyActionMode(this);
    }

    public View getCustomView()
    {
        return null;
    }

    public Menu getMenu()
    {
        return mMenu;
    }

    public MenuInflater getMenuInflater()
    {
        return new MenuInflater(mContext);
    }

    public CharSequence getSubtitle()
    {
        return null;
    }

    public CharSequence getTitle()
    {
        return null;
    }

    public void hide(long l)
    {
        long l1 = l;
        if(l == -1L)
            l1 = ViewConfiguration.getDefaultActionModeHideDuration();
        l = Math.min(3000L, l1);
        mOriginatingView.removeCallbacks(mHideOff);
        if(l <= 0L)
        {
            mHideOff.run();
        } else
        {
            mFloatingToolbarVisibilityHelper.setHideRequested(true);
            mFloatingToolbarVisibilityHelper.updateToolbarVisibility();
            mOriginatingView.postDelayed(mHideOff, l);
        }
    }

    public void invalidate()
    {
        mCallback.onPrepareActionMode(this, mMenu);
        invalidateContentRect();
    }

    public void invalidateContentRect()
    {
        mCallback.onGetContentRect(this, mOriginatingView, mContentRect);
        repositionToolbar();
    }

    boolean lambda$_2D_com_android_internal_view_FloatingActionMode_4856(MenuItem menuitem)
    {
        return mMenu.performItemAction(menuitem, 0);
    }

    public void onWindowFocusChanged(boolean flag)
    {
        mFloatingToolbarVisibilityHelper.setWindowFocused(flag);
        mFloatingToolbarVisibilityHelper.updateToolbarVisibility();
    }

    public void setCustomView(View view)
    {
    }

    public void setSubtitle(int i)
    {
    }

    public void setSubtitle(CharSequence charsequence)
    {
    }

    public void setTitle(int i)
    {
    }

    public void setTitle(CharSequence charsequence)
    {
    }

    public void updateViewLocationInWindow()
    {
        mOriginatingView.getLocationOnScreen(mViewPositionOnScreen);
        mOriginatingView.getRootView().getLocationOnScreen(mRootViewPositionOnScreen);
        mOriginatingView.getGlobalVisibleRect(mViewRectOnScreen);
        mViewRectOnScreen.offset(mRootViewPositionOnScreen[0], mRootViewPositionOnScreen[1]);
        if(!Arrays.equals(mViewPositionOnScreen, mPreviousViewPositionOnScreen) || mViewRectOnScreen.equals(mPreviousViewRectOnScreen) ^ true)
        {
            repositionToolbar();
            mPreviousViewPositionOnScreen[0] = mViewPositionOnScreen[0];
            mPreviousViewPositionOnScreen[1] = mViewPositionOnScreen[1];
            mPreviousViewRectOnScreen.set(mViewRectOnScreen);
        }
    }

    private static final int MAX_HIDE_DURATION = 3000;
    private static final int MOVING_HIDE_DELAY = 50;
    private final int mBottomAllowance;
    private final android.view.ActionMode.Callback2 mCallback;
    private final Rect mContentRect = new Rect();
    private final Rect mContentRectOnScreen = new Rect();
    private final Context mContext;
    private final Point mDisplaySize = new Point();
    private FloatingToolbar mFloatingToolbar;
    private FloatingToolbarVisibilityHelper mFloatingToolbarVisibilityHelper;
    private final Runnable mHideOff = new Runnable() {

        public void run()
        {
            if(FloatingActionMode._2D_wrap0(FloatingActionMode.this))
            {
                FloatingActionMode._2D_get1(FloatingActionMode.this).setHideRequested(false);
                FloatingActionMode._2D_get1(FloatingActionMode.this).updateToolbarVisibility();
            }
        }

        final FloatingActionMode this$0;

            
            {
                this$0 = FloatingActionMode.this;
                super();
            }
    }
;
    private final MenuBuilder mMenu;
    private final Runnable mMovingOff = new Runnable() {

        public void run()
        {
            if(FloatingActionMode._2D_wrap0(FloatingActionMode.this))
            {
                FloatingActionMode._2D_get1(FloatingActionMode.this).setMoving(false);
                FloatingActionMode._2D_get1(FloatingActionMode.this).updateToolbarVisibility();
            }
        }

        final FloatingActionMode this$0;

            
            {
                this$0 = FloatingActionMode.this;
                super();
            }
    }
;
    private final View mOriginatingView;
    private final Rect mPreviousContentRectOnScreen = new Rect();
    private final int mPreviousViewPositionOnScreen[] = new int[2];
    private final Rect mPreviousViewRectOnScreen = new Rect();
    private final int mRootViewPositionOnScreen[] = new int[2];
    private final Rect mScreenRect = new Rect();
    private final int mViewPositionOnScreen[] = new int[2];
    private final Rect mViewRectOnScreen = new Rect();
}
