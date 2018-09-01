// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaRouter;
import android.util.AttributeSet;
import android.view.View;
import com.android.internal.app.MediaRouteDialogPresenter;

// Referenced classes of package android.app:
//            Activity

public class MediaRouteButton extends View
{
    private final class MediaRouterCallback extends android.media.MediaRouter.SimpleCallback
    {

        public void onRouteAdded(MediaRouter mediarouter, android.media.MediaRouter.RouteInfo routeinfo)
        {
            MediaRouteButton._2D_wrap0(MediaRouteButton.this);
        }

        public void onRouteChanged(MediaRouter mediarouter, android.media.MediaRouter.RouteInfo routeinfo)
        {
            MediaRouteButton._2D_wrap0(MediaRouteButton.this);
        }

        public void onRouteGrouped(MediaRouter mediarouter, android.media.MediaRouter.RouteInfo routeinfo, android.media.MediaRouter.RouteGroup routegroup, int i)
        {
            MediaRouteButton._2D_wrap0(MediaRouteButton.this);
        }

        public void onRouteRemoved(MediaRouter mediarouter, android.media.MediaRouter.RouteInfo routeinfo)
        {
            MediaRouteButton._2D_wrap0(MediaRouteButton.this);
        }

        public void onRouteSelected(MediaRouter mediarouter, int i, android.media.MediaRouter.RouteInfo routeinfo)
        {
            MediaRouteButton._2D_wrap0(MediaRouteButton.this);
        }

        public void onRouteUngrouped(MediaRouter mediarouter, android.media.MediaRouter.RouteInfo routeinfo, android.media.MediaRouter.RouteGroup routegroup)
        {
            MediaRouteButton._2D_wrap0(MediaRouteButton.this);
        }

        public void onRouteUnselected(MediaRouter mediarouter, int i, android.media.MediaRouter.RouteInfo routeinfo)
        {
            MediaRouteButton._2D_wrap0(MediaRouteButton.this);
        }

        final MediaRouteButton this$0;

        private MediaRouterCallback()
        {
            this$0 = MediaRouteButton.this;
            super();
        }

        MediaRouterCallback(MediaRouterCallback mediaroutercallback)
        {
            this();
        }
    }


    static void _2D_wrap0(MediaRouteButton mediaroutebutton)
    {
        mediaroutebutton.refreshRoute();
    }

    public MediaRouteButton(Context context)
    {
        this(context, null);
    }

    public MediaRouteButton(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x10103ad);
    }

    public MediaRouteButton(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public MediaRouteButton(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mRouter = (MediaRouter)context.getSystemService("media_router");
        mCallback = new MediaRouterCallback(null);
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.MediaRouteButton, i, j);
        setRemoteIndicatorDrawable(context.getDrawable(3));
        mMinWidth = context.getDimensionPixelSize(0, 0);
        mMinHeight = context.getDimensionPixelSize(1, 0);
        i = context.getInteger(2, 1);
        context.recycle();
        setClickable(true);
        setRouteTypes(i);
    }

    private Activity getActivity()
    {
        for(Context context = getContext(); context instanceof ContextWrapper; context = ((ContextWrapper)context).getBaseContext())
            if(context instanceof Activity)
                return (Activity)context;

        throw new IllegalStateException("The MediaRouteButton's Context is not an Activity.");
    }

    private void refreshRoute()
    {
        Object obj;
        boolean flag;
        boolean flag1;
        obj = mRouter.getSelectedRoute();
        boolean flag2;
        if(!((android.media.MediaRouter.RouteInfo) (obj)).isDefault())
            flag = ((android.media.MediaRouter.RouteInfo) (obj)).matchesTypes(mRouteTypes);
        else
            flag = false;
        if(flag)
            flag1 = ((android.media.MediaRouter.RouteInfo) (obj)).isConnecting();
        else
            flag1 = false;
        flag2 = false;
        if(mRemoteActive != flag)
        {
            mRemoteActive = flag;
            flag2 = true;
        }
        if(mIsConnecting != flag1)
        {
            mIsConnecting = flag1;
            flag2 = true;
        }
        if(flag2)
            refreshDrawableState();
        if(mAttachedToWindow)
            setEnabled(mRouter.isRouteAvailable(mRouteTypes, 1));
        if(mRemoteIndicator == null || !(mRemoteIndicator.getCurrent() instanceof AnimationDrawable)) goto _L2; else goto _L1
_L1:
        obj = (AnimationDrawable)mRemoteIndicator.getCurrent();
        if(!mAttachedToWindow) goto _L4; else goto _L3
_L3:
        if((flag2 || flag1) && ((AnimationDrawable) (obj)).isRunning() ^ true)
            ((AnimationDrawable) (obj)).start();
_L2:
        return;
_L4:
        if(flag && flag1 ^ true)
        {
            if(((AnimationDrawable) (obj)).isRunning())
                ((AnimationDrawable) (obj)).stop();
            ((AnimationDrawable) (obj)).selectDrawable(((AnimationDrawable) (obj)).getNumberOfFrames() - 1);
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    private void setRemoteIndicatorDrawable(Drawable drawable)
    {
        if(mRemoteIndicator != null)
        {
            mRemoteIndicator.setCallback(null);
            unscheduleDrawable(mRemoteIndicator);
        }
        mRemoteIndicator = drawable;
        if(drawable != null)
        {
            drawable.setCallback(this);
            drawable.setState(getDrawableState());
            boolean flag;
            if(getVisibility() == 0)
                flag = true;
            else
                flag = false;
            drawable.setVisible(flag, false);
        }
        refreshDrawableState();
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        Drawable drawable = mRemoteIndicator;
        if(drawable != null && drawable.isStateful() && drawable.setState(getDrawableState()))
            invalidateDrawable(drawable);
    }

    public int getRouteTypes()
    {
        return mRouteTypes;
    }

    public void jumpDrawablesToCurrentState()
    {
        super.jumpDrawablesToCurrentState();
        if(mRemoteIndicator != null)
            mRemoteIndicator.jumpToCurrentState();
    }

    public void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        mAttachedToWindow = true;
        if(mRouteTypes != 0)
            mRouter.addCallback(mRouteTypes, mCallback, 8);
        refreshRoute();
    }

    protected int[] onCreateDrawableState(int i)
    {
        int ai[] = super.onCreateDrawableState(i + 1);
        if(!mIsConnecting) goto _L2; else goto _L1
_L1:
        mergeDrawableStates(ai, CHECKED_STATE_SET);
_L4:
        return ai;
_L2:
        if(mRemoteActive)
            mergeDrawableStates(ai, ACTIVATED_STATE_SET);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onDetachedFromWindow()
    {
        mAttachedToWindow = false;
        if(mRouteTypes != 0)
            mRouter.removeCallback(mCallback);
        super.onDetachedFromWindow();
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if(mRemoteIndicator == null)
        {
            return;
        } else
        {
            int i = getPaddingLeft();
            int j = getWidth();
            int k = getPaddingRight();
            int l = getPaddingTop();
            int i1 = getHeight();
            int j1 = getPaddingBottom();
            int k1 = mRemoteIndicator.getIntrinsicWidth();
            int l1 = mRemoteIndicator.getIntrinsicHeight();
            j = i + (j - k - i - k1) / 2;
            i1 = l + (i1 - j1 - l - l1) / 2;
            mRemoteIndicator.setBounds(j, i1, j + k1, i1 + l1);
            mRemoteIndicator.draw(canvas);
            return;
        }
    }

    protected void onMeasure(int i, int j)
    {
        int k;
        int l;
        int k1;
        boolean flag = false;
        k = android.view.View.MeasureSpec.getSize(i);
        l = android.view.View.MeasureSpec.getSize(j);
        int i1 = android.view.View.MeasureSpec.getMode(i);
        int j1 = android.view.View.MeasureSpec.getMode(j);
        j = mMinWidth;
        if(mRemoteIndicator != null)
            i = mRemoteIndicator.getIntrinsicWidth() + getPaddingLeft() + getPaddingRight();
        else
            i = 0;
        k1 = Math.max(j, i);
        j = mMinHeight;
        i = ((flag) ? 1 : 0);
        if(mRemoteIndicator != null)
            i = mRemoteIndicator.getIntrinsicHeight() + getPaddingTop() + getPaddingBottom();
        j = Math.max(j, i);
        i1;
        JVM INSTR lookupswitch 3: default 136
    //                   -2147483648: 194
    //                   0: 136
    //                   1073741824: 188;
           goto _L1 _L2 _L1 _L3
_L1:
        i = k1;
_L7:
        j1;
        JVM INSTR lookupswitch 3: default 176
    //                   -2147483648: 211
    //                   0: 176
    //                   1073741824: 205;
           goto _L4 _L5 _L4 _L6
_L4:
        setMeasuredDimension(i, j);
        return;
_L3:
        i = k;
          goto _L7
_L2:
        i = Math.min(k, k1);
          goto _L7
_L6:
        j = l;
          goto _L4
_L5:
        j = Math.min(l, j);
          goto _L4
    }

    public boolean performClick()
    {
        boolean flag = super.performClick();
        if(!flag)
            playSoundEffect(0);
        if(showDialogInternal())
            flag = true;
        return flag;
    }

    public void setContentDescription(CharSequence charsequence)
    {
        super.setContentDescription(charsequence);
        setTooltipText(charsequence);
    }

    public void setExtendedSettingsClickListener(android.view.View.OnClickListener onclicklistener)
    {
        mExtendedSettingsClickListener = onclicklistener;
    }

    public void setRouteTypes(int i)
    {
        if(mRouteTypes != i)
        {
            if(mAttachedToWindow && mRouteTypes != 0)
                mRouter.removeCallback(mCallback);
            mRouteTypes = i;
            if(mAttachedToWindow && i != 0)
                mRouter.addCallback(i, mCallback, 8);
            refreshRoute();
        }
    }

    public void setVisibility(int i)
    {
        super.setVisibility(i);
        if(mRemoteIndicator != null)
        {
            Drawable drawable = mRemoteIndicator;
            boolean flag;
            if(getVisibility() == 0)
                flag = true;
            else
                flag = false;
            drawable.setVisible(flag, false);
        }
    }

    public void showDialog()
    {
        showDialogInternal();
    }

    boolean showDialogInternal()
    {
        boolean flag = false;
        if(!mAttachedToWindow)
            return false;
        if(MediaRouteDialogPresenter.showDialogFragment(getActivity(), mRouteTypes, mExtendedSettingsClickListener) != null)
            flag = true;
        return flag;
    }

    protected boolean verifyDrawable(Drawable drawable)
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(!super.verifyDrawable(drawable))
            if(drawable == mRemoteIndicator)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    private static final int ACTIVATED_STATE_SET[] = {
        0x10102fe
    };
    private static final int CHECKED_STATE_SET[] = {
        0x10100a0
    };
    private boolean mAttachedToWindow;
    private final MediaRouterCallback mCallback;
    private android.view.View.OnClickListener mExtendedSettingsClickListener;
    private boolean mIsConnecting;
    private int mMinHeight;
    private int mMinWidth;
    private boolean mRemoteActive;
    private Drawable mRemoteIndicator;
    private int mRouteTypes;
    private final MediaRouter mRouter;

}
