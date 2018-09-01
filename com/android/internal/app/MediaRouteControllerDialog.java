// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.*;
import android.media.MediaRouter;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.*;
import android.widget.*;

public class MediaRouteControllerDialog extends AlertDialog
{
    private final class MediaRouterCallback extends android.media.MediaRouter.SimpleCallback
    {

        public void onRouteChanged(MediaRouter mediarouter, android.media.MediaRouter.RouteInfo routeinfo)
        {
            MediaRouteControllerDialog._2D_wrap0(MediaRouteControllerDialog.this);
        }

        public void onRouteGrouped(MediaRouter mediarouter, android.media.MediaRouter.RouteInfo routeinfo, android.media.MediaRouter.RouteGroup routegroup, int i)
        {
            MediaRouteControllerDialog._2D_wrap0(MediaRouteControllerDialog.this);
        }

        public void onRouteUngrouped(MediaRouter mediarouter, android.media.MediaRouter.RouteInfo routeinfo, android.media.MediaRouter.RouteGroup routegroup)
        {
            MediaRouteControllerDialog._2D_wrap0(MediaRouteControllerDialog.this);
        }

        public void onRouteUnselected(MediaRouter mediarouter, int i, android.media.MediaRouter.RouteInfo routeinfo)
        {
            MediaRouteControllerDialog._2D_wrap0(MediaRouteControllerDialog.this);
        }

        public void onRouteVolumeChanged(MediaRouter mediarouter, android.media.MediaRouter.RouteInfo routeinfo)
        {
            if(routeinfo == MediaRouteControllerDialog._2D_get0(MediaRouteControllerDialog.this))
                MediaRouteControllerDialog._2D_wrap1(MediaRouteControllerDialog.this);
        }

        final MediaRouteControllerDialog this$0;

        private MediaRouterCallback()
        {
            this$0 = MediaRouteControllerDialog.this;
            super();
        }

        MediaRouterCallback(MediaRouterCallback mediaroutercallback)
        {
            this();
        }
    }


    static android.media.MediaRouter.RouteInfo _2D_get0(MediaRouteControllerDialog mediaroutecontrollerdialog)
    {
        return mediaroutecontrollerdialog.mRoute;
    }

    static MediaRouter _2D_get1(MediaRouteControllerDialog mediaroutecontrollerdialog)
    {
        return mediaroutecontrollerdialog.mRouter;
    }

    static SeekBar _2D_get2(MediaRouteControllerDialog mediaroutecontrollerdialog)
    {
        return mediaroutecontrollerdialog.mVolumeSlider;
    }

    static boolean _2D_get3(MediaRouteControllerDialog mediaroutecontrollerdialog)
    {
        return mediaroutecontrollerdialog.mVolumeSliderTouched;
    }

    static boolean _2D_set0(MediaRouteControllerDialog mediaroutecontrollerdialog, boolean flag)
    {
        mediaroutecontrollerdialog.mVolumeSliderTouched = flag;
        return flag;
    }

    static boolean _2D_wrap0(MediaRouteControllerDialog mediaroutecontrollerdialog)
    {
        return mediaroutecontrollerdialog.update();
    }

    static void _2D_wrap1(MediaRouteControllerDialog mediaroutecontrollerdialog)
    {
        mediaroutecontrollerdialog.updateVolume();
    }

    public MediaRouteControllerDialog(Context context, int i)
    {
        super(context, i);
        mVolumeControlEnabled = true;
        mRouter = (MediaRouter)context.getSystemService("media_router");
        mRoute = mRouter.getSelectedRoute();
    }

    private Drawable getIconDrawable()
    {
        if(!(mMediaRouteButtonDrawable instanceof StateListDrawable))
            return mMediaRouteButtonDrawable;
        if(mRoute.isConnecting())
        {
            StateListDrawable statelistdrawable = (StateListDrawable)mMediaRouteButtonDrawable;
            statelistdrawable.setState(mMediaRouteConnectingState);
            return statelistdrawable.getCurrent();
        } else
        {
            StateListDrawable statelistdrawable1 = (StateListDrawable)mMediaRouteButtonDrawable;
            statelistdrawable1.setState(mMediaRouteOnState);
            return statelistdrawable1.getCurrent();
        }
    }

    private boolean isVolumeControlAvailable()
    {
        boolean flag = true;
        if(!mVolumeControlEnabled || mRoute.getVolumeHandling() != 1)
            flag = false;
        return flag;
    }

    private Drawable obtainMediaRouteButtonDrawable()
    {
        Object obj = getContext();
        Object obj1 = new TypedValue();
        if(!((Context) (obj)).getTheme().resolveAttribute(0x10103ad, ((TypedValue) (obj1)), true))
        {
            return null;
        } else
        {
            obj1 = ((Context) (obj)).obtainStyledAttributes(((TypedValue) (obj1)).data, new int[] {
                0x1110030
            });
            obj = ((TypedArray) (obj1)).getDrawable(0);
            ((TypedArray) (obj1)).recycle();
            return ((Drawable) (obj));
        }
    }

    private boolean update()
    {
        Drawable drawable;
        if(!mRoute.isSelected() || mRoute.isDefault())
        {
            dismiss();
            return false;
        }
        setTitle(mRoute.getName());
        updateVolume();
        drawable = getIconDrawable();
        if(drawable == mCurrentIconDrawable) goto _L2; else goto _L1
_L1:
        Drawable drawable1;
        mCurrentIconDrawable = drawable;
        drawable1 = drawable;
        if(!(drawable instanceof AnimationDrawable)) goto _L4; else goto _L3
_L3:
        AnimationDrawable animationdrawable = (AnimationDrawable)drawable;
        if(mAttachedToWindow || !(mRoute.isConnecting() ^ true)) goto _L6; else goto _L5
_L5:
        if(animationdrawable.isRunning())
            animationdrawable.stop();
        drawable1 = animationdrawable.getFrame(animationdrawable.getNumberOfFrames() - 1);
_L4:
        setIcon(drawable1);
_L2:
        return true;
_L6:
        drawable1 = drawable;
        if(!animationdrawable.isRunning())
        {
            animationdrawable.start();
            drawable1 = drawable;
        }
        if(true) goto _L4; else goto _L7
_L7:
    }

    private void updateVolume()
    {
        if(!mVolumeSliderTouched)
            if(isVolumeControlAvailable())
            {
                mVolumeLayout.setVisibility(0);
                mVolumeSlider.setMax(mRoute.getVolumeMax());
                mVolumeSlider.setProgress(mRoute.getVolume());
            } else
            {
                mVolumeLayout.setVisibility(8);
            }
    }

    public View getMediaControlView()
    {
        return mControlView;
    }

    public android.media.MediaRouter.RouteInfo getRoute()
    {
        return mRoute;
    }

    public boolean isVolumeControlEnabled()
    {
        return mVolumeControlEnabled;
    }

    public void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        mAttachedToWindow = true;
        mRouter.addCallback(0, mCallback, 2);
        update();
    }

    protected void onCreate(Bundle bundle)
    {
        setTitle(mRoute.getName());
        setButton(-2, getContext().getResources().getString(0x104034b), new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                if(MediaRouteControllerDialog._2D_get0(MediaRouteControllerDialog.this).isSelected())
                    if(MediaRouteControllerDialog._2D_get0(MediaRouteControllerDialog.this).isBluetooth())
                        MediaRouteControllerDialog._2D_get1(MediaRouteControllerDialog.this).getDefaultRoute().select();
                    else
                        MediaRouteControllerDialog._2D_get1(MediaRouteControllerDialog.this).getFallbackRoute().select();
                dismiss();
            }

            final MediaRouteControllerDialog this$0;

            
            {
                this$0 = MediaRouteControllerDialog.this;
                super();
            }
        }
);
        View view = getLayoutInflater().inflate(0x1090081, null);
        setView(view, 0, 0, 0, 0);
        super.onCreate(bundle);
        View view1 = getWindow().findViewById(0x102020e);
        if(view1 != null)
            view1.setMinimumHeight(0);
        mVolumeLayout = (LinearLayout)view.findViewById(0x10202fb);
        mVolumeSlider = (SeekBar)view.findViewById(0x10202fc);
        mVolumeSlider.setOnSeekBarChangeListener(new android.widget.SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekbar, int i, boolean flag)
            {
                if(flag)
                    MediaRouteControllerDialog._2D_get0(MediaRouteControllerDialog.this).requestSetVolume(i);
            }

            public void onStartTrackingTouch(SeekBar seekbar)
            {
                if(MediaRouteControllerDialog._2D_get3(MediaRouteControllerDialog.this))
                    MediaRouteControllerDialog._2D_get2(MediaRouteControllerDialog.this).removeCallbacks(mStopTrackingTouch);
                else
                    MediaRouteControllerDialog._2D_set0(MediaRouteControllerDialog.this, true);
            }

            public void onStopTrackingTouch(SeekBar seekbar)
            {
                MediaRouteControllerDialog._2D_get2(MediaRouteControllerDialog.this).postDelayed(mStopTrackingTouch, 250L);
            }

            private final Runnable mStopTrackingTouch = new _cls1();
            final MediaRouteControllerDialog this$0;

            
            {
                this$0 = MediaRouteControllerDialog.this;
                super();
            }
        }
);
        mMediaRouteButtonDrawable = obtainMediaRouteButtonDrawable();
        mCreated = true;
        if(update())
        {
            mControlView = onCreateMediaControlView(bundle);
            bundle = (FrameLayout)view.findViewById(0x10202f8);
            if(mControlView != null)
            {
                bundle.addView(mControlView);
                bundle.setVisibility(0);
            } else
            {
                bundle.setVisibility(8);
            }
        }
    }

    public View onCreateMediaControlView(Bundle bundle)
    {
        return null;
    }

    public void onDetachedFromWindow()
    {
        mRouter.removeCallback(mCallback);
        mAttachedToWindow = false;
        super.onDetachedFromWindow();
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if(i == 25 || i == 24)
        {
            keyevent = mRoute;
            if(i == 25)
                i = -1;
            else
                i = 1;
            keyevent.requestUpdateVolume(i);
            return true;
        } else
        {
            return super.onKeyDown(i, keyevent);
        }
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        if(i == 25 || i == 24)
            return true;
        else
            return super.onKeyUp(i, keyevent);
    }

    public void setVolumeControlEnabled(boolean flag)
    {
        if(mVolumeControlEnabled != flag)
        {
            mVolumeControlEnabled = flag;
            if(mCreated)
                updateVolume();
        }
    }

    private static final int VOLUME_UPDATE_DELAY_MILLIS = 250;
    private boolean mAttachedToWindow;
    private final MediaRouterCallback mCallback = new MediaRouterCallback(null);
    private View mControlView;
    private boolean mCreated;
    private Drawable mCurrentIconDrawable;
    private Drawable mMediaRouteButtonDrawable;
    private int mMediaRouteConnectingState[] = {
        0x10100a0, 0x101009e
    };
    private int mMediaRouteOnState[] = {
        0x10102fe, 0x101009e
    };
    private final android.media.MediaRouter.RouteInfo mRoute;
    private final MediaRouter mRouter;
    private boolean mVolumeControlEnabled;
    private LinearLayout mVolumeLayout;
    private SeekBar mVolumeSlider;
    private boolean mVolumeSliderTouched;

    // Unreferenced inner class com/android/internal/app/MediaRouteControllerDialog$2$1

/* anonymous class */
    class _cls1
        implements Runnable
    {

        public void run()
        {
            if(MediaRouteControllerDialog._2D_get3(_fld0))
            {
                MediaRouteControllerDialog._2D_set0(_fld0, false);
                MediaRouteControllerDialog._2D_wrap1(_fld0);
            }
        }

        final _cls2 this$1;

            
            {
                this$1 = _cls2.this;
                super();
            }
    }

}
