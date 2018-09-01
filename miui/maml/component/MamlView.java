// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.component;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.SystemClock;
import android.view.*;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import miui.maml.*;
import miui.maml.data.Variables;
import miui.maml.util.MamlAccessHelper;

public class MamlView extends FrameLayout
{
    private class InnerView extends View
    {

        protected void onDraw(Canvas canvas)
        {
            if(MamlView._2D_get0(MamlView.this) && MamlView._2D_get4(MamlView.this).isStarted() ^ true)
                return;
            if(MamlView._2D_get3(MamlView.this) != 0.0F)
            {
                int i = canvas.save();
                canvas.scale(MamlView._2D_get3(MamlView.this), MamlView._2D_get3(MamlView.this), MamlView._2D_get1(MamlView.this), MamlView._2D_get2(MamlView.this));
                mRoot.render(canvas);
                canvas.restoreToCount(i);
            } else
            {
                mRoot.render(canvas);
            }
            MamlView._2D_get4(MamlView.this).doneRender();
        }

        final MamlView this$0;

        public InnerView(Context context)
        {
            this$0 = MamlView.this;
            super(context);
        }
    }


    static boolean _2D_get0(MamlView mamlview)
    {
        return mamlview.mHasDelay;
    }

    static int _2D_get1(MamlView mamlview)
    {
        return mamlview.mPivotX;
    }

    static int _2D_get2(MamlView mamlview)
    {
        return mamlview.mPivotY;
    }

    static float _2D_get3(MamlView mamlview)
    {
        return mamlview.mScale;
    }

    static RenderVsyncUpdater _2D_get4(MamlView mamlview)
    {
        return mamlview.mUpdater;
    }

    static InnerView _2D_get5(MamlView mamlview)
    {
        return mamlview.mView;
    }

    public MamlView(Context context, ScreenElementRoot screenelementroot)
    {
        this(context, screenelementroot, new Handler(), 0L);
    }

    public MamlView(Context context, ScreenElementRoot screenelementroot, long l)
    {
        this(context, screenelementroot, new Handler(), l);
    }

    public MamlView(Context context, ScreenElementRoot screenelementroot, Handler handler, long l)
    {
        super(context);
        setClickable(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
        if(screenelementroot == null)
            throw new NullPointerException();
        mView = new InnerView(context);
        context = new android.view.ViewGroup.LayoutParams(-1, -1);
        addView(mView, context);
        mRoot = screenelementroot;
        mRoot.setViewManager(this);
        mRoot.setOnHoverChangeListener(new miui.maml.ScreenElementRoot.OnHoverChangeListener() {

            public void onHoverChange(String s)
            {
                setContentDescription(s);
                sendAccessibilityEvent(32768);
            }

            final MamlView this$0;

            
            {
                this$0 = MamlView.this;
                super();
            }
        }
);
        mUpdater = new RenderVsyncUpdater(mRoot, handler) {

            protected void doRenderImp()
            {
                MamlView._2D_get5(MamlView.this).postInvalidate();
            }

            final MamlView this$0;

            
            {
                this$0 = MamlView.this;
                super(screenelementroot, handler);
            }
        }
;
        if(l > 0L)
        {
            mHasDelay = true;
            long l1 = SystemClock.elapsedRealtime();
            mUpdater.setStartDelay(l1, l);
        }
        init();
        if(android.os.Build.VERSION.SDK_INT >= 23)
        {
            mMamlAccessHelper = new MamlAccessHelper(mRoot, this);
            setAccessibilityDelegate(mMamlAccessHelper);
        }
    }

    public void cleanUp()
    {
        cleanUp(false);
    }

    public void cleanUp(boolean flag)
    {
        setOnTouchListener(null);
        mRoot.setKeepResource(flag);
        mUpdater.cleanUp();
    }

    protected boolean dispatchHoverEvent(MotionEvent motionevent)
    {
        if(mMamlAccessHelper != null && mMamlAccessHelper.dispatchHoverEvent(motionevent))
            return true;
        else
            return super.dispatchHoverEvent(motionevent);
    }

    public final ScreenElementRoot getRoot()
    {
        return mRoot;
    }

    protected int getSuggestedMinimumHeight()
    {
        return (int)mRoot.getHeight();
    }

    protected int getSuggestedMinimumWidth()
    {
        return (int)mRoot.getWidth();
    }

    public void init()
    {
        mRoot.setConfiguration(getResources().getConfiguration());
        mUpdater.init();
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        onResume();
    }

    protected void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        mRoot.onConfigurationChanged(configuration);
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        onPause();
    }

    public boolean onHoverEvent(MotionEvent motionevent)
    {
        mRoot.postMessage(MotionEvent.obtain(motionevent));
        return super.onHoverEvent(motionevent);
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        accessibilitynodeinfo.setText(mRoot.getRawAttr("accessibilityText"));
        super.onInitializeAccessibilityNodeInfo(accessibilitynodeinfo);
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        Variables variables = mRoot.getContext().mVariables;
        variables.put("view_width", (float)(k - i) / mRoot.getScale());
        variables.put("view_height", (float)(l - j) / mRoot.getScale());
        for(Object obj = mParent; obj instanceof View; obj = ((View) (obj)).getParent())
        {
            obj = (View)obj;
            i += ((View) (obj)).getLeft() - ((View) (obj)).getScrollX();
            j += ((View) (obj)).getTop() - ((View) (obj)).getScrollY();
        }

        variables.put("view_x", (float)i / mRoot.getScale());
        variables.put("view_y", (float)j / mRoot.getScale());
        mUpdater.triggerUpdate();
    }

    public void onPause()
    {
        mUpdater.onPause();
    }

    public void onResume()
    {
        mUpdater.onResume();
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        boolean flag = mRoot.needDisallowInterceptTouchEvent();
        if(mNeedDisallowInterceptTouchEvent != flag)
        {
            getParent().requestDisallowInterceptTouchEvent(flag);
            mNeedDisallowInterceptTouchEvent = flag;
        }
        mRoot.postMessage(MotionEvent.obtain(motionevent));
        return true;
    }

    public MamlView setAutoCleanup(boolean flag)
    {
        mUpdater.setAutoCleanup(flag);
        return this;
    }

    public void setScale(float f, int i, int j)
    {
        mScale = f;
        mPivotX = i;
        mPivotY = j;
    }

    public void setVisibility(int i)
    {
        super.setVisibility(i);
        if(i != 0) goto _L2; else goto _L1
_L1:
        onResume();
_L4:
        return;
_L2:
        if(i == 4 || i == 8)
            onPause();
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static final String TAG = "MamlView";
    private boolean mHasDelay;
    private MamlAccessHelper mMamlAccessHelper;
    protected boolean mNeedDisallowInterceptTouchEvent;
    private int mPivotX;
    private int mPivotY;
    protected ScreenElementRoot mRoot;
    private float mScale;
    private RenderVsyncUpdater mUpdater;
    private InnerView mView;
}
