// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.*;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.*;

// Referenced classes of package android.widget:
//            FrameLayout, ZoomControls

public class ZoomButtonsController
    implements android.view.View.OnTouchListener
{
    private class Container extends FrameLayout
    {

        public boolean dispatchKeyEvent(KeyEvent keyevent)
        {
            boolean flag;
            if(ZoomButtonsController._2D_wrap0(ZoomButtonsController.this, keyevent))
                flag = true;
            else
                flag = super.dispatchKeyEvent(keyevent);
            return flag;
        }

        final ZoomButtonsController this$0;

        public Container(Context context)
        {
            this$0 = ZoomButtonsController.this;
            super(context);
        }
    }

    public static interface OnZoomListener
    {

        public abstract void onVisibilityChanged(boolean flag);

        public abstract void onZoom(boolean flag);
    }


    static int _2D_get0()
    {
        return ZOOM_CONTROLS_TIMEOUT;
    }

    static OnZoomListener _2D_get1(ZoomButtonsController zoombuttonscontroller)
    {
        return zoombuttonscontroller.mCallback;
    }

    static Handler _2D_get2(ZoomButtonsController zoombuttonscontroller)
    {
        return zoombuttonscontroller.mHandler;
    }

    static boolean _2D_get3(ZoomButtonsController zoombuttonscontroller)
    {
        return zoombuttonscontroller.mIsVisible;
    }

    static View _2D_get4(ZoomButtonsController zoombuttonscontroller)
    {
        return zoombuttonscontroller.mOwnerView;
    }

    static boolean _2D_wrap0(ZoomButtonsController zoombuttonscontroller, KeyEvent keyevent)
    {
        return zoombuttonscontroller.onContainerKey(keyevent);
    }

    static void _2D_wrap1(ZoomButtonsController zoombuttonscontroller, int i)
    {
        zoombuttonscontroller.dismissControlsDelayed(i);
    }

    static void _2D_wrap2(ZoomButtonsController zoombuttonscontroller)
    {
        zoombuttonscontroller.onPostConfigurationChanged();
    }

    static void _2D_wrap3(ZoomButtonsController zoombuttonscontroller)
    {
        zoombuttonscontroller.refreshPositioningVariables();
    }

    public ZoomButtonsController(View view)
    {
        mAutoDismissControls = true;
        mContext = view.getContext();
        mWindowManager = (WindowManager)mContext.getSystemService("window");
        mOwnerView = view;
        mTouchPaddingScaledSq = (int)(mContext.getResources().getDisplayMetrics().density * 20F);
        mTouchPaddingScaledSq = mTouchPaddingScaledSq * mTouchPaddingScaledSq;
    }

    private FrameLayout createContainer()
    {
        android.view.WindowManager.LayoutParams layoutparams = new android.view.WindowManager.LayoutParams(-2, -2);
        layoutparams.gravity = 0x800033;
        layoutparams.flags = 0x20218;
        layoutparams.height = -2;
        layoutparams.width = -1;
        layoutparams.type = 1000;
        layoutparams.format = -3;
        layoutparams.windowAnimations = 0x1030307;
        mContainerLayoutParams = layoutparams;
        Container container = new Container(mContext);
        container.setLayoutParams(layoutparams);
        container.setMeasureAllChildren(true);
        ((LayoutInflater)mContext.getSystemService("layout_inflater")).inflate(0x1090127, container);
        mControls = (ZoomControls)container.findViewById(0x10204bf);
        mControls.setOnZoomInClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                ZoomButtonsController._2D_wrap1(ZoomButtonsController.this, ZoomButtonsController._2D_get0());
                if(ZoomButtonsController._2D_get1(ZoomButtonsController.this) != null)
                    ZoomButtonsController._2D_get1(ZoomButtonsController.this).onZoom(true);
            }

            final ZoomButtonsController this$0;

            
            {
                this$0 = ZoomButtonsController.this;
                super();
            }
        }
);
        mControls.setOnZoomOutClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                ZoomButtonsController._2D_wrap1(ZoomButtonsController.this, ZoomButtonsController._2D_get0());
                if(ZoomButtonsController._2D_get1(ZoomButtonsController.this) != null)
                    ZoomButtonsController._2D_get1(ZoomButtonsController.this).onZoom(false);
            }

            final ZoomButtonsController this$0;

            
            {
                this$0 = ZoomButtonsController.this;
                super();
            }
        }
);
        return container;
    }

    private void dismissControlsDelayed(int i)
    {
        if(mAutoDismissControls)
        {
            mHandler.removeMessages(3);
            mHandler.sendEmptyMessageDelayed(3, i);
        }
    }

    private View findViewForTouch(int i, int j)
    {
        int k = i - mContainerRawLocation[0];
        int l = j - mContainerRawLocation[1];
        Rect rect = mTempRect;
        View view = null;
        j = 0x7fffffff;
        i = mContainer.getChildCount() - 1;
        while(i >= 0) 
        {
            View view1 = mContainer.getChildAt(i);
            int i1;
            View view2;
            if(view1.getVisibility() != 0)
            {
                i1 = j;
                view2 = view;
            } else
            {
                view1.getHitRect(rect);
                if(rect.contains(k, l))
                    return view1;
                int j1;
                if(k >= rect.left && k <= rect.right)
                    i1 = 0;
                else
                    i1 = Math.min(Math.abs(rect.left - k), Math.abs(k - rect.right));
                if(l >= rect.top && l <= rect.bottom)
                    j1 = 0;
                else
                    j1 = Math.min(Math.abs(rect.top - l), Math.abs(l - rect.bottom));
                j1 = i1 * i1 + j1 * j1;
                view2 = view;
                i1 = j;
                if(j1 < mTouchPaddingScaledSq)
                {
                    view2 = view;
                    i1 = j;
                    if(j1 < j)
                    {
                        view2 = view1;
                        i1 = j1;
                    }
                }
            }
            i--;
            view = view2;
            j = i1;
        }
        return view;
    }

    private boolean isInterestingKey(int i)
    {
        switch(i)
        {
        default:
            return false;

        case 4: // '\004'
        case 19: // '\023'
        case 20: // '\024'
        case 21: // '\025'
        case 22: // '\026'
        case 23: // '\027'
        case 66: // 'B'
            return true;
        }
    }

    private boolean onContainerKey(KeyEvent keyevent)
    {
        int i = keyevent.getKeyCode();
        if(isInterestingKey(i))
        {
            if(i == 4)
            {
                if(keyevent.getAction() == 0 && keyevent.getRepeatCount() == 0)
                {
                    if(mOwnerView != null)
                    {
                        android.view.KeyEvent.DispatcherState dispatcherstate = mOwnerView.getKeyDispatcherState();
                        if(dispatcherstate != null)
                            dispatcherstate.startTracking(keyevent, this);
                    }
                    return true;
                }
                if(keyevent.getAction() == 1 && keyevent.isTracking() && keyevent.isCanceled() ^ true)
                {
                    setVisible(false);
                    return true;
                }
            } else
            {
                dismissControlsDelayed(ZOOM_CONTROLS_TIMEOUT);
            }
            return false;
        }
        ViewRootImpl viewrootimpl = mOwnerView.getViewRootImpl();
        if(viewrootimpl != null)
            viewrootimpl.dispatchInputEvent(keyevent);
        return true;
    }

    private void onPostConfigurationChanged()
    {
        dismissControlsDelayed(ZOOM_CONTROLS_TIMEOUT);
        refreshPositioningVariables();
    }

    private void refreshPositioningVariables()
    {
        if(mOwnerView.getWindowToken() == null)
            return;
        int i = mOwnerView.getHeight();
        int j = mOwnerView.getWidth();
        i -= mContainer.getHeight();
        mOwnerView.getLocationOnScreen(mOwnerViewRawLocation);
        mContainerRawLocation[0] = mOwnerViewRawLocation[0];
        mContainerRawLocation[1] = mOwnerViewRawLocation[1] + i;
        int ai[] = mTempIntArray;
        mOwnerView.getLocationInWindow(ai);
        mContainerLayoutParams.x = ai[0];
        mContainerLayoutParams.width = j;
        mContainerLayoutParams.y = ai[1] + i;
        if(mIsVisible)
            mWindowManager.updateViewLayout(mContainer, mContainerLayoutParams);
    }

    private void setTouchTargetView(View view)
    {
        mTouchTargetView = view;
        if(view != null)
            view.getLocationInWindow(mTouchTargetWindowLocation);
    }

    public ViewGroup getContainer()
    {
        return mContainer;
    }

    public View getZoomControls()
    {
        return mControls;
    }

    public boolean isAutoDismissed()
    {
        return mAutoDismissControls;
    }

    public boolean isVisible()
    {
        return mIsVisible;
    }

    public boolean onTouch(View view, MotionEvent motionevent)
    {
        int i;
        View view1;
        i = motionevent.getAction();
        if(motionevent.getPointerCount() > 1)
            return false;
        if(mReleaseTouchListenerOnUp)
        {
            if(i == 1 || i == 3)
            {
                mOwnerView.setOnTouchListener(null);
                setTouchTargetView(null);
                mReleaseTouchListenerOnUp = false;
            }
            return true;
        }
        dismissControlsDelayed(ZOOM_CONTROLS_TIMEOUT);
        view1 = mTouchTargetView;
        view = view1;
        i;
        JVM INSTR tableswitch 0 3: default 100
    //                   0 246
    //                   1 269
    //                   2 103
    //                   3 269;
           goto _L1 _L2 _L3 _L4 _L3
_L3:
        break MISSING_BLOCK_LABEL_269;
_L4:
        break; /* Loop/switch isn't completed */
_L1:
        view = view1;
_L5:
        if(view != null)
        {
            int k = mContainerRawLocation[0];
            int l = mTouchTargetWindowLocation[0];
            int j = mContainerRawLocation[1];
            int i1 = mTouchTargetWindowLocation[1];
            motionevent = MotionEvent.obtain(motionevent);
            motionevent.offsetLocation(mOwnerViewRawLocation[0] - (k + l), mOwnerViewRawLocation[1] - (j + i1));
            float f = motionevent.getX();
            float f1 = motionevent.getY();
            if(f < 0.0F && f > -20F)
                motionevent.offsetLocation(-f, 0.0F);
            if(f1 < 0.0F && f1 > -20F)
                motionevent.offsetLocation(0.0F, -f1);
            boolean flag = view.dispatchTouchEvent(motionevent);
            motionevent.recycle();
            return flag;
        } else
        {
            return false;
        }
_L2:
        view = findViewForTouch((int)motionevent.getRawX(), (int)motionevent.getRawY());
        setTouchTargetView(view);
          goto _L5
        setTouchTargetView(null);
        view = view1;
          goto _L5
    }

    public void setAutoDismissed(boolean flag)
    {
        if(mAutoDismissControls == flag)
        {
            return;
        } else
        {
            mAutoDismissControls = flag;
            return;
        }
    }

    public void setFocusable(boolean flag)
    {
        int i = mContainerLayoutParams.flags;
        if(flag)
        {
            android.view.WindowManager.LayoutParams layoutparams = mContainerLayoutParams;
            layoutparams.flags = layoutparams.flags & -9;
        } else
        {
            android.view.WindowManager.LayoutParams layoutparams1 = mContainerLayoutParams;
            layoutparams1.flags = layoutparams1.flags | 8;
        }
        if(mContainerLayoutParams.flags != i && mIsVisible)
            mWindowManager.updateViewLayout(mContainer, mContainerLayoutParams);
    }

    public void setOnZoomListener(OnZoomListener onzoomlistener)
    {
        mCallback = onzoomlistener;
    }

    public void setVisible(boolean flag)
    {
        if(flag)
        {
            if(mOwnerView.getWindowToken() == null)
            {
                if(!mHandler.hasMessages(4))
                    mHandler.sendEmptyMessage(4);
                return;
            }
            dismissControlsDelayed(ZOOM_CONTROLS_TIMEOUT);
        }
        if(mIsVisible == flag)
            return;
        mIsVisible = flag;
        if(!flag) goto _L2; else goto _L1
_L1:
        if(mContainerLayoutParams.token == null)
            mContainerLayoutParams.token = mOwnerView.getWindowToken();
        mWindowManager.addView(mContainer, mContainerLayoutParams);
        if(mPostedVisibleInitializer == null)
            mPostedVisibleInitializer = new Runnable() {

                public void run()
                {
                    ZoomButtonsController._2D_wrap3(ZoomButtonsController.this);
                    if(ZoomButtonsController._2D_get1(ZoomButtonsController.this) != null)
                        ZoomButtonsController._2D_get1(ZoomButtonsController.this).onVisibilityChanged(true);
                }

                final ZoomButtonsController this$0;

            
            {
                this$0 = ZoomButtonsController.this;
                super();
            }
            }
;
        mHandler.post(mPostedVisibleInitializer);
        mContext.registerReceiver(mConfigurationChangedReceiver, mConfigurationChangedFilter);
        mOwnerView.setOnTouchListener(this);
        mReleaseTouchListenerOnUp = false;
_L4:
        return;
_L2:
        if(mTouchTargetView != null)
            mReleaseTouchListenerOnUp = true;
        else
            mOwnerView.setOnTouchListener(null);
        mContext.unregisterReceiver(mConfigurationChangedReceiver);
        mWindowManager.removeViewImmediate(mContainer);
        mHandler.removeCallbacks(mPostedVisibleInitializer);
        if(mCallback != null)
            mCallback.onVisibilityChanged(false);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setZoomInEnabled(boolean flag)
    {
        mControls.setIsZoomInEnabled(flag);
    }

    public void setZoomOutEnabled(boolean flag)
    {
        mControls.setIsZoomOutEnabled(flag);
    }

    public void setZoomSpeed(long l)
    {
        mControls.setZoomSpeed(l);
    }

    private static final int MSG_DISMISS_ZOOM_CONTROLS = 3;
    private static final int MSG_POST_CONFIGURATION_CHANGED = 2;
    private static final int MSG_POST_SET_VISIBLE = 4;
    private static final String TAG = "ZoomButtonsController";
    private static final int ZOOM_CONTROLS_TIMEOUT = (int)ViewConfiguration.getZoomControlsTimeout();
    private static final int ZOOM_CONTROLS_TOUCH_PADDING = 20;
    private boolean mAutoDismissControls;
    private OnZoomListener mCallback;
    private final IntentFilter mConfigurationChangedFilter = new IntentFilter("android.intent.action.CONFIGURATION_CHANGED");
    private final BroadcastReceiver mConfigurationChangedReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent)
        {
            if(!ZoomButtonsController._2D_get3(ZoomButtonsController.this))
            {
                return;
            } else
            {
                ZoomButtonsController._2D_get2(ZoomButtonsController.this).removeMessages(2);
                ZoomButtonsController._2D_get2(ZoomButtonsController.this).sendEmptyMessage(2);
                return;
            }
        }

        final ZoomButtonsController this$0;

            
            {
                this$0 = ZoomButtonsController.this;
                super();
            }
    }
;
    private final FrameLayout mContainer = createContainer();
    private android.view.WindowManager.LayoutParams mContainerLayoutParams;
    private final int mContainerRawLocation[] = new int[2];
    private final Context mContext;
    private ZoomControls mControls;
    private final Handler mHandler = new Handler() {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 2 4: default 32
        //                       2 33
        //                       3 43
        //                       4 54;
               goto _L1 _L2 _L3 _L4
_L1:
            return;
_L2:
            ZoomButtonsController._2D_wrap2(ZoomButtonsController.this);
            continue; /* Loop/switch isn't completed */
_L3:
            setVisible(false);
            continue; /* Loop/switch isn't completed */
_L4:
            if(ZoomButtonsController._2D_get4(ZoomButtonsController.this).getWindowToken() == null)
                Log.e("ZoomButtonsController", "Cannot make the zoom controller visible if the owner view is not attached to a window.");
            else
                setVisible(true);
            if(true) goto _L1; else goto _L5
_L5:
        }

        final ZoomButtonsController this$0;

            
            {
                this$0 = ZoomButtonsController.this;
                super();
            }
    }
;
    private boolean mIsVisible;
    private final View mOwnerView;
    private final int mOwnerViewRawLocation[] = new int[2];
    private Runnable mPostedVisibleInitializer;
    private boolean mReleaseTouchListenerOnUp;
    private final int mTempIntArray[] = new int[2];
    private final Rect mTempRect = new Rect();
    private int mTouchPaddingScaledSq;
    private View mTouchTargetView;
    private final int mTouchTargetWindowLocation[] = new int[2];
    private final WindowManager mWindowManager;

}
