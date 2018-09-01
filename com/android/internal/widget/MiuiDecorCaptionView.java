// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.app.*;
import android.content.*;
import android.graphics.Rect;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.*;
import android.view.*;
import com.android.internal.policy.PhoneWindow;
import com.miui.gamebooster.service.IMiuiFreeformService;
import java.util.ArrayList;

// Referenced classes of package com.android.internal.widget:
//            DecorCaptionView

public class MiuiDecorCaptionView extends DecorCaptionView
    implements android.view.View.OnTouchListener, android.view.GestureDetector.OnGestureListener
{

    static IMiuiFreeformService _2D_set0(MiuiDecorCaptionView miuidecorcaptionview, IMiuiFreeformService imiuifreeformservice)
    {
        miuidecorcaptionview.mService = imiuifreeformservice;
        return imiuifreeformservice;
    }

    static int _2D_wrap0(MiuiDecorCaptionView miuidecorcaptionview)
    {
        return miuidecorcaptionview.getStackId();
    }

    public MiuiDecorCaptionView(Context context)
    {
        super(context);
        mOwner = null;
        mShow = false;
        mDragging = false;
        mOverlayWithAppContent = false;
        mTouchDispatchList = new ArrayList(2);
        mCloseRect = new Rect();
        mMaximizeRect = new Rect();
        mMoveRect = new Rect();
        init(context);
    }

    public MiuiDecorCaptionView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mOwner = null;
        mShow = false;
        mDragging = false;
        mOverlayWithAppContent = false;
        mTouchDispatchList = new ArrayList(2);
        mCloseRect = new Rect();
        mMaximizeRect = new Rect();
        mMoveRect = new Rect();
        init(context);
    }

    public MiuiDecorCaptionView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mOwner = null;
        mShow = false;
        mDragging = false;
        mOverlayWithAppContent = false;
        mTouchDispatchList = new ArrayList(2);
        mCloseRect = new Rect();
        mMaximizeRect = new Rect();
        mMoveRect = new Rect();
        init(context);
    }

    private int getStackId()
    {
        byte byte0 = -1;
        android.view.Window.WindowControllerCallback windowcontrollercallback = mOwner.getWindowControllerCallback();
        int i = byte0;
        if(windowcontrollercallback != null)
            try
            {
                i = windowcontrollercallback.getWindowStackId();
            }
            catch(RemoteException remoteexception)
            {
                Log.e("MiuiDecorCaptionView", "Failed to get the workspace ID of a PhoneWindow.");
                i = byte0;
            }
        if(i == -1)
            return 1;
        else
            return i;
    }

    private void init(Context context)
    {
        mDragSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mGestureDetector = new GestureDetector(context, this);
        Intent intent = new Intent();
        intent.setAction("com.miui.gamebooster.service.MiuiFreeformService");
        intent.setPackage("com.miui.securitycenter");
        context.bindService(intent, mConn, 1);
    }

    private boolean isFillingScreen()
    {
        boolean flag = false;
        if(((getWindowSystemUiVisibility() | getSystemUiVisibility()) & 0xa05) != 0)
            flag = true;
        return flag;
    }

    private boolean passedSlop(int i, int j)
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(Math.abs(i - mTouchDownX) <= mDragSlop)
            if(Math.abs(j - mTouchDownY) > mDragSlop)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    private void updateCaptionVisibility()
    {
        boolean flag = false;
        int i;
        View view;
        if(!isFillingScreen())
            i = mShow ^ true;
        else
            i = 1;
        view = mCaption;
        if(i != 0)
            i = 8;
        else
            i = 0;
        view.setVisibility(i);
        mCaption.setOnTouchListener(this);
        if(MiuiMultiWindowUtils.getOrientation(getContext()) == 2)
        {
            if(getStackId() == 2)
                i = 1;
            else
                i = 0;
        } else
        {
            i = 0;
        }
        view = mMove;
        if(i != 0)
            i = ((flag) ? 1 : 0);
        else
            i = 8;
        view.setVisibility(i);
    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutparams)
    {
        View view1 = view;
        if(mContext.getPackageName().equals("com.tencent.mm"))
        {
            view1 = view;
            if(view.getParent() != null)
            {
                view = (ViewGroup)view.getParent();
                view1 = view;
                if(view.getParent() != null)
                {
                    ((ViewGroup)view.getParent()).removeView(view);
                    view1 = view;
                }
            }
        }
        if(!(layoutparams instanceof android.view.ViewGroup.MarginLayoutParams))
            throw new IllegalArgumentException((new StringBuilder()).append("params ").append(layoutparams).append(" must subclass MarginLayoutParams").toString());
        if(i >= 2 || getChildCount() >= 2)
            throw new IllegalStateException("DecorCaptionView can only handle 1 client view");
        super.addView(view1, 0, layoutparams);
        mContent = view1;
        mRoot = (ViewGroup)getParent();
        if(mContext.getPackageName().equals("com.tencent.mobileqq") && mRoot != null)
            mRoot.setOnHierarchyChangeListener(new android.view.ViewGroup.OnHierarchyChangeListener() {

                public void onChildViewAdded(View view2, View view3)
                {
                    if(!(view3 instanceof ViewGroup) && MiuiDecorCaptionView._2D_wrap0(MiuiDecorCaptionView.this) == 2)
                        view3.setAlpha(0.0F);
                }

                public void onChildViewRemoved(View view2, View view3)
                {
                }

                final MiuiDecorCaptionView this$0;

            
            {
                this$0 = MiuiDecorCaptionView.this;
                super();
            }
            }
);
    }

    public ArrayList buildTouchDispatchChildList()
    {
        mTouchDispatchList.ensureCapacity(3);
        if(mCaption != null)
            mTouchDispatchList.add(mCaption);
        if(mContent != null)
            mTouchDispatchList.add(mContent);
        return mTouchDispatchList;
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return layoutparams instanceof android.view.ViewGroup.MarginLayoutParams;
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return new android.view.ViewGroup.MarginLayoutParams(-1, -1);
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new android.view.ViewGroup.MarginLayoutParams(getContext(), attributeset);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return new android.view.ViewGroup.MarginLayoutParams(layoutparams);
    }

    public View getCaption()
    {
        return mCaption;
    }

    public int getCaptionHeight()
    {
        int i;
        if(mCaption != null)
            i = mCaption.getHeight();
        else
            i = 0;
        return i;
    }

    public boolean isCaptionShowing()
    {
        return mShow;
    }

    public void onConfigurationChanged(boolean flag)
    {
        mShow = flag;
        updateCaptionVisibility();
    }

    public boolean onDown(MotionEvent motionevent)
    {
        return false;
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        mCaption = getChildAt(0);
    }

    public boolean onFling(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
    {
        return false;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        boolean flag = false;
        if(motionevent.getAction() == 0)
        {
            int i = (int)motionevent.getX();
            int j = (int)motionevent.getY();
            if(mMoveRect.contains(i, j))
                mClickTarget = mMove;
            if(mMaximizeRect.contains(i, j))
                mClickTarget = mMaximize;
            if(mCloseRect.contains(i, j))
                mClickTarget = mClose;
        }
        if(mClickTarget != null)
            flag = true;
        return flag;
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        if(mCaption.getVisibility() != 8)
        {
            mCaption.layout(0, 0, mCaption.getMeasuredWidth(), mCaption.getMeasuredHeight());
            i = mCaption.getBottom() - mCaption.getTop();
            mMove.getHitRect(mMoveRect);
            mMaximize.getHitRect(mMaximizeRect);
            mClose.getHitRect(mCloseRect);
        } else
        {
            i = 0;
            mMoveRect.setEmpty();
            mMaximizeRect.setEmpty();
            mCloseRect.setEmpty();
        }
        if(mContent != null)
            if(mOverlayWithAppContent)
                mContent.layout(0, 0, mContent.getMeasuredWidth(), mContent.getMeasuredHeight());
            else
                mContent.layout(0, i, mContent.getMeasuredWidth(), mContent.getMeasuredHeight() + i);
        mOwner.notifyRestrictedCaptionAreaCallback(mMaximize.getLeft(), mMaximize.getTop(), mClose.getRight(), mClose.getBottom());
    }

    public void onLongPress(MotionEvent motionevent)
    {
    }

    protected void onMeasure(int i, int j)
    {
        int k;
        if(mCaption.getVisibility() != 8)
        {
            measureChildWithMargins(mCaption, i, 0, j, 0);
            k = mCaption.getMeasuredHeight();
        } else
        {
            k = 0;
        }
        if(mContent != null)
            if(mOverlayWithAppContent)
                measureChildWithMargins(mContent, i, 0, j, 0);
            else
                measureChildWithMargins(mContent, i, 0, j, k);
        setMeasuredDimension(android.view.View.MeasureSpec.getSize(i), android.view.View.MeasureSpec.getSize(j));
    }

    public boolean onScroll(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
    {
        return false;
    }

    public void onShowPress(MotionEvent motionevent)
    {
    }

    public boolean onSingleTapUp(MotionEvent motionevent)
    {
        if(mService == null)
        {
            motionevent = new Intent("com.miui.gamebooster.service.MiuiFreeformService");
            mContext.bindService(motionevent, mConn, 1);
        }
        if(mService == null)
            return false;
        motionevent = ActivityManagerNative.getDefault();
        int i = motionevent.getTaskForActivity(getRootView().getAttachedActivityInstance().getActivityToken(), false);
        if(mClickTarget != mMove) goto _L2; else goto _L1
_L1:
        motionevent = JVM INSTR new #81  <Class Rect>;
        motionevent.Rect();
        getBoundsOnScreen(motionevent);
        motionevent = MiuiMultiWindowUtils.getFreeformRect(getContext(), motionevent);
        mService.resizeTask(i, motionevent, 0);
_L5:
        return true;
_L2:
        if(mClickTarget != mClose) goto _L4; else goto _L3
_L3:
        mService.moveTaskToStack(i, 1, false);
          goto _L5
        motionevent;
          goto _L5
_L4:
        if(mClickTarget != mMaximize) goto _L5; else goto _L6
_L6:
        mService.moveTaskToStack(i, 1, true);
          goto _L5
    }

    public boolean onTouch(View view, MotionEvent motionevent)
    {
        int i;
        int j;
        boolean flag1;
        boolean flag2;
        boolean flag = true;
        i = (int)motionevent.getX();
        j = (int)motionevent.getY();
        if(motionevent.getToolType(motionevent.getActionIndex()) == 3)
            flag1 = true;
        else
            flag1 = false;
        if((motionevent.getButtonState() & 1) != 0)
            flag2 = true;
        else
            flag2 = false;
        motionevent.getActionMasked();
        JVM INSTR tableswitch 0 3: default 76
    //                   0 102
    //                   1 197
    //                   2 141
    //                   3 197;
           goto _L1 _L2 _L3 _L4 _L3
_L1:
        if(!mDragging)
            flag = mCheckForDragging;
        return flag;
_L2:
        if(!mShow)
            return false;
        if(!flag1 || flag2)
        {
            mCheckForDragging = true;
            mTouchDownX = i;
            mTouchDownY = j;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if(!mDragging && mCheckForDragging && (flag1 || passedSlop(i, j)))
        {
            mCheckForDragging = false;
            mDragging = true;
            startMovingTask(motionevent.getRawX(), motionevent.getRawY());
        }
        if(true) goto _L1; else goto _L3
_L3:
        if(mDragging)
        {
            mDragging = false;
            return mCheckForDragging ^ true;
        }
        if(true) goto _L1; else goto _L5
_L5:
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if(mClickTarget != null)
        {
            mGestureDetector.onTouchEvent(motionevent);
            int i = motionevent.getAction();
            if(i == 1 || i == 3)
                mClickTarget = null;
            return true;
        } else
        {
            return false;
        }
    }

    public void removeContentView()
    {
        if(mContent != null)
        {
            removeView(mContent);
            mContent = null;
        }
    }

    public void setPhoneWindow(PhoneWindow phonewindow, boolean flag)
    {
        mOwner = phonewindow;
        mShow = flag;
        mOverlayWithAppContent = phonewindow.isOverlayWithDecorCaptionEnabled();
        if(mOverlayWithAppContent)
            mCaption.setBackgroundColor(0);
        mOwner.getDecorView().setOutlineProvider(ViewOutlineProvider.BOUNDS);
        mMove = findViewById(0x1020313);
        mMaximize = findViewById(0x10202f4);
        mClose = findViewById(0x10201fd);
        updateCaptionVisibility();
    }

    public boolean shouldDelayChildPressedState()
    {
        return false;
    }

    private static final String TAG = "MiuiDecorCaptionView";
    private View mCaption;
    private boolean mCheckForDragging;
    private View mClickTarget;
    private View mClose;
    private final Rect mCloseRect;
    private ServiceConnection mConn = new ServiceConnection() {

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            MiuiDecorCaptionView._2D_set0(MiuiDecorCaptionView.this, com.miui.gamebooster.service.IMiuiFreeformService.Stub.asInterface(ibinder));
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            MiuiDecorCaptionView._2D_set0(MiuiDecorCaptionView.this, null);
        }

        final MiuiDecorCaptionView this$0;

            
            {
                this$0 = MiuiDecorCaptionView.this;
                super();
            }
    }
;
    private View mContent;
    private int mDragSlop;
    private boolean mDragging;
    private GestureDetector mGestureDetector;
    private View mMaximize;
    private final Rect mMaximizeRect;
    private View mMove;
    private final Rect mMoveRect;
    private boolean mOverlayWithAppContent;
    private PhoneWindow mOwner;
    private ViewGroup mRoot;
    private IMiuiFreeformService mService;
    private boolean mShow;
    private ArrayList mTouchDispatchList;
    private int mTouchDownX;
    private int mTouchDownY;
}
