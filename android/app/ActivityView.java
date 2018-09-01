// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.Context;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.hardware.input.InputManager;
import android.os.RemoteException;
import android.util.*;
import android.view.*;
import dalvik.system.CloseGuard;

// Referenced classes of package android.app:
//            IInputForwarder, ActivityOptions, PendingIntent

public class ActivityView extends ViewGroup
{
    public static abstract class StateCallback
    {

        public abstract void onActivityViewDestroyed(ActivityView activityview);

        public abstract void onActivityViewReady(ActivityView activityview);

        public StateCallback()
        {
        }
    }

    private class SurfaceCallback
        implements android.view.SurfaceHolder.Callback
    {

        public void surfaceChanged(SurfaceHolder surfaceholder, int i, int j, int k)
        {
            if(ActivityView._2D_get3(ActivityView.this) != null)
                ActivityView._2D_get3(ActivityView.this).resize(j, k, ActivityView._2D_wrap0(ActivityView.this));
        }

        public void surfaceCreated(SurfaceHolder surfaceholder)
        {
            ActivityView._2D_set0(ActivityView.this, ActivityView._2D_get2(ActivityView.this).getHolder().getSurface());
            if(ActivityView._2D_get3(ActivityView.this) == null)
            {
                ActivityView._2D_wrap1(ActivityView.this);
                if(ActivityView._2D_get3(ActivityView.this) != null && ActivityView._2D_get0(ActivityView.this) != null)
                    ActivityView._2D_get0(ActivityView.this).onActivityViewReady(ActivityView.this);
            } else
            {
                ActivityView._2D_get3(ActivityView.this).setSurface(surfaceholder.getSurface());
            }
        }

        public void surfaceDestroyed(SurfaceHolder surfaceholder)
        {
            ActivityView._2D_get1(ActivityView.this).release();
            ActivityView._2D_set0(ActivityView.this, null);
            if(ActivityView._2D_get3(ActivityView.this) != null)
                ActivityView._2D_get3(ActivityView.this).setSurface(null);
        }

        final ActivityView this$0;

        private SurfaceCallback()
        {
            this$0 = ActivityView.this;
            super();
        }

        SurfaceCallback(SurfaceCallback surfacecallback)
        {
            this();
        }
    }


    static StateCallback _2D_get0(ActivityView activityview)
    {
        return activityview.mActivityViewCallback;
    }

    static Surface _2D_get1(ActivityView activityview)
    {
        return activityview.mSurface;
    }

    static SurfaceView _2D_get2(ActivityView activityview)
    {
        return activityview.mSurfaceView;
    }

    static VirtualDisplay _2D_get3(ActivityView activityview)
    {
        return activityview.mVirtualDisplay;
    }

    static Surface _2D_set0(ActivityView activityview, Surface surface)
    {
        activityview.mSurface = surface;
        return surface;
    }

    static int _2D_wrap0(ActivityView activityview)
    {
        return activityview.getBaseDisplayDensity();
    }

    static void _2D_wrap1(ActivityView activityview)
    {
        activityview.initVirtualDisplay();
    }

    public ActivityView(Context context)
    {
        this(context, null);
    }

    public ActivityView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public ActivityView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mGuard = CloseGuard.get();
        mSurfaceView = new SurfaceView(context);
        mSurfaceCallback = new SurfaceCallback(null);
        mSurfaceView.getHolder().addCallback(mSurfaceCallback);
        addView(mSurfaceView);
        mOpened = true;
        mGuard.open("release");
    }

    private int getBaseDisplayDensity()
    {
        WindowManager windowmanager = (WindowManager)mContext.getSystemService(android/view/WindowManager);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        windowmanager.getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.densityDpi;
    }

    private void initVirtualDisplay()
    {
        if(mVirtualDisplay != null)
            throw new IllegalStateException("Trying to initialize for the second time.");
        int i = mSurfaceView.getWidth();
        int j = mSurfaceView.getHeight();
        mVirtualDisplay = ((DisplayManager)mContext.getSystemService(android/hardware/display/DisplayManager)).createVirtualDisplay((new StringBuilder()).append("ActivityViewVirtualDisplay@").append(System.identityHashCode(this)).toString(), i, j, getBaseDisplayDensity(), mSurface, 0);
        if(mVirtualDisplay == null)
        {
            Log.e("ActivityView", "Failed to initialize ActivityView");
            return;
        } else
        {
            mInputForwarder = InputManager.getInstance().createInputForwarder(mVirtualDisplay.getDisplay().getDisplayId());
            return;
        }
    }

    private boolean injectInputEvent(InputEvent inputevent)
    {
        if(mInputForwarder == null)
            break MISSING_BLOCK_LABEL_26;
        boolean flag = mInputForwarder.forwardEvent(inputevent);
        return flag;
        inputevent;
        inputevent.rethrowAsRuntimeException();
        return false;
    }

    private void performRelease()
    {
        if(!mOpened)
            return;
        mSurfaceView.getHolder().removeCallback(mSurfaceCallback);
        if(mInputForwarder != null)
            mInputForwarder = null;
        boolean flag;
        if(mVirtualDisplay != null)
        {
            mVirtualDisplay.release();
            mVirtualDisplay = null;
            flag = true;
        } else
        {
            flag = false;
        }
        if(mSurface != null)
        {
            mSurface.release();
            mSurface = null;
        }
        if(flag && mActivityViewCallback != null)
            mActivityViewCallback.onActivityViewDestroyed(this);
        mGuard.close();
        mOpened = false;
    }

    private ActivityOptions prepareActivityOptions()
    {
        if(mVirtualDisplay == null)
        {
            throw new IllegalStateException("Trying to start activity before ActivityView is ready.");
        } else
        {
            ActivityOptions activityoptions = ActivityOptions.makeBasic();
            activityoptions.setLaunchDisplayId(mVirtualDisplay.getDisplay().getDisplayId());
            return activityoptions;
        }
    }

    protected void finalize()
        throws Throwable
    {
        if(mGuard != null)
        {
            mGuard.warnIfOpen();
            performRelease();
        }
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public boolean onGenericMotionEvent(MotionEvent motionevent)
    {
        if(motionevent.isFromSource(2) && injectInputEvent(motionevent))
            return true;
        else
            return super.onGenericMotionEvent(motionevent);
    }

    public void onLayout(boolean flag, int i, int j, int k, int l)
    {
        mSurfaceView.layout(0, 0, k - i, l - j);
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        boolean flag;
        if(!injectInputEvent(motionevent))
            flag = super.onTouchEvent(motionevent);
        else
            flag = true;
        return flag;
    }

    public void release()
    {
        if(mVirtualDisplay == null)
        {
            throw new IllegalStateException("Trying to release container that is not initialized.");
        } else
        {
            performRelease();
            return;
        }
    }

    public void setCallback(StateCallback statecallback)
    {
        mActivityViewCallback = statecallback;
        if(mVirtualDisplay != null && mActivityViewCallback != null)
            mActivityViewCallback.onActivityViewReady(this);
    }

    public void startActivity(PendingIntent pendingintent)
    {
        ActivityOptions activityoptions = prepareActivityOptions();
        try
        {
            pendingintent.send(null, 0, null, null, null, null, activityoptions.toBundle());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(PendingIntent pendingintent)
        {
            throw new RuntimeException(pendingintent);
        }
    }

    public void startActivity(Intent intent)
    {
        ActivityOptions activityoptions = prepareActivityOptions();
        getContext().startActivity(intent, activityoptions.toBundle());
    }

    private static final String DISPLAY_NAME = "ActivityViewVirtualDisplay";
    private static final String TAG = "ActivityView";
    private StateCallback mActivityViewCallback;
    private final CloseGuard mGuard;
    private IInputForwarder mInputForwarder;
    private boolean mOpened;
    private Surface mSurface;
    private final SurfaceCallback mSurfaceCallback;
    private final SurfaceView mSurfaceView;
    private VirtualDisplay mVirtualDisplay;
}
