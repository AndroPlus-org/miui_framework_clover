// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.Log;
import android.view.*;
import android.view.accessibility.AccessibilityNodeInfo;
import miui.maml.data.Variables;

// Referenced classes of package miui.maml:
//            ScreenElementRoot, RenderThread, ScreenContext

public class MiAdvancedView extends View
    implements RendererController.IRenderable
{

    public MiAdvancedView(Context context, ScreenElementRoot screenelementroot)
    {
        super(context);
        mPaused = true;
        setClickable(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
        mRoot = screenelementroot;
        if(mRoot != null)
            mRoot.setOnHoverChangeListener(new ScreenElementRoot.OnHoverChangeListener() {

                public void onHoverChange(String s)
                {
                    setContentDescription(s);
                    sendAccessibilityEvent(32768);
                }

                final MiAdvancedView this$0;

            
            {
                this$0 = MiAdvancedView.this;
                super();
            }
            }
);
    }

    public MiAdvancedView(Context context, ScreenElementRoot screenelementroot, RenderThread renderthread)
    {
        this(context, screenelementroot);
        if(renderthread != null)
        {
            mUseExternalRenderThread = true;
            mThread = renderthread;
            init();
        }
    }

    public void cleanUp()
    {
        cleanUp(false);
    }

    public void cleanUp(boolean flag)
    {
        mRoot.setKeepResource(flag);
        setOnTouchListener(null);
        if(mThread != null)
            if(!mUseExternalRenderThread)
            {
                mThread.setStop();
                mThread = null;
            } else
            {
                mRoot.detachFromRenderThread(mThread);
                mRoot.selfFinish();
            }
    }

    public void doRender()
    {
        postInvalidate();
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
        mRoot.setRenderControllerRenderable(this);
        mRoot.setConfiguration(getResources().getConfiguration());
        mRoot.attachToRenderThread(mThread);
        mRoot.selfInit();
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        if(!mUseExternalRenderThread && mThread == null)
        {
            mThread = new RenderThread();
            init();
            onCreateRenderThread(mThread);
            mThread.setPaused(mPaused);
            mThread.start();
        }
    }

    protected void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        mRoot.onConfigurationChanged(configuration);
    }

    protected void onCreateRenderThread(RenderThread renderthread)
    {
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
    }

    protected void onDraw(Canvas canvas)
    {
        if(mThread == null || mThread.isStarted() ^ true)
            return;
        if(!mLoggedHardwareRender)
        {
            Log.d("MiAdvancedView", (new StringBuilder()).append("canvas hardware render: ").append(canvas.isHardwareAccelerated()).toString());
            mLoggedHardwareRender = true;
        }
        if(mScale != 0.0F)
        {
            int i = canvas.save();
            canvas.scale(mScale, mScale, mPivotX, mPivotY);
            mRoot.render(canvas);
            canvas.restoreToCount(i);
        } else
        {
            mRoot.render(canvas);
        }
    }

    public boolean onHoverEvent(MotionEvent motionevent)
    {
        if(mRoot != null)
            mRoot.postMessage(MotionEvent.obtain(motionevent));
        return super.onHoverEvent(motionevent);
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        if(mRoot != null)
            accessibilitynodeinfo.setText(mRoot.getRawAttr("accessibilityText"));
        super.onInitializeAccessibilityNodeInfo(accessibilitynodeinfo);
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        Variables variables = mRoot.getContext().mVariables;
        variables.put("view_width", (float)(k - i) / mRoot.getScale());
        variables.put("view_height", (float)(l - j) / mRoot.getScale());
        k = i;
        i = j;
        for(Object obj = mParent; obj instanceof View; obj = ((View) (obj)).getParent())
        {
            obj = (View)obj;
            k += ((View) (obj)).getLeft() - ((View) (obj)).getScrollX();
            i += ((View) (obj)).getTop() - ((View) (obj)).getScrollY();
        }

        variables.put("view_x", (float)k / mRoot.getScale());
        variables.put("view_y", (float)i / mRoot.getScale());
        mRoot.requestUpdate();
    }

    public void onPause()
    {
        mPaused = true;
        if(mThread != null)
            if(!mUseExternalRenderThread)
                mThread.setPaused(true);
            else
                mRoot.selfPause();
    }

    public void onResume()
    {
        mPaused = false;
        if(mThread != null)
            if(!mUseExternalRenderThread)
                mThread.setPaused(false);
            else
                mRoot.selfResume();
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if(mRoot != null)
        {
            boolean flag = mRoot.needDisallowInterceptTouchEvent();
            if(mNeedDisallowInterceptTouchEvent != flag)
            {
                getParent().requestDisallowInterceptTouchEvent(flag);
                mNeedDisallowInterceptTouchEvent = flag;
            }
            mRoot.postMessage(MotionEvent.obtain(motionevent));
            return true;
        } else
        {
            return false;
        }
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

    private static final String LOG_TAG = "MiAdvancedView";
    private boolean mLoggedHardwareRender;
    protected boolean mNeedDisallowInterceptTouchEvent;
    private boolean mPaused;
    private int mPivotX;
    private int mPivotY;
    protected ScreenElementRoot mRoot;
    private float mScale;
    private RenderThread mThread;
    private boolean mUseExternalRenderThread;
}
