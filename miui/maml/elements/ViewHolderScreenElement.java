// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewManager;
import java.util.ArrayList;
import miui.maml.RendererController;
import miui.maml.ScreenElementRoot;
import miui.maml.animation.BaseAnimation;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.elements:
//            ElementGroupRC

public abstract class ViewHolderScreenElement extends ElementGroupRC
{
    private class ProxyListener extends miui.maml.RendererController.EmptyListener
    {

        public void doRender()
        {
            getView().postInvalidate();
        }

        public void tick(long l)
        {
            doTickChildren(l);
        }

        public void triggerUpdate()
        {
            mRoot.getRendererController().triggerUpdate();
        }

        final ViewHolderScreenElement this$0;

        private ProxyListener()
        {
            this$0 = ViewHolderScreenElement.this;
            super();
        }

        ProxyListener(ProxyListener proxylistener)
        {
            this();
        }
    }


    static boolean _2D_get0(ViewHolderScreenElement viewholderscreenelement)
    {
        return viewholderscreenelement.mHardware;
    }

    static android.view.ViewGroup.LayoutParams _2D_get1(ViewHolderScreenElement viewholderscreenelement)
    {
        return viewholderscreenelement.mLayoutParams;
    }

    static boolean _2D_get2(ViewHolderScreenElement viewholderscreenelement)
    {
        return viewholderscreenelement.mViewAdded;
    }

    static boolean _2D_set0(ViewHolderScreenElement viewholderscreenelement, boolean flag)
    {
        viewholderscreenelement.mViewAdded = flag;
        return flag;
    }

    public ViewHolderScreenElement(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        mLayoutParams = getLayoutParam();
        mHardware = Boolean.parseBoolean(getAttr(element, "hardware"));
        mUpdatePosition = getAttrAsBoolean(getAttr(element, "updatePosition"), true);
        mUpdateSize = getAttrAsBoolean(getAttr(element, "updateSize"), true);
        mUpdateTranslation = getAttrAsBoolean(getAttr(element, "updateTranslation"), true);
    }

    private final void finishView()
    {
        if(mViewAdded && mRoot.getViewManager() != null)
            postInMainThread(new Runnable() {

                public void run()
                {
                    ViewManager viewmanager = mRoot.getViewManager();
                    if(viewmanager != null)
                    {
                        View view = getView();
                        viewmanager.removeView(view);
                        ViewHolderScreenElement._2D_set0(ViewHolderScreenElement.this, false);
                        onViewRemoved(view);
                    }
                }

                final ViewHolderScreenElement this$0;

            
            {
                this$0 = ViewHolderScreenElement.this;
                super();
            }
            }
);
    }

    private static boolean getAttrAsBoolean(String s, boolean flag)
    {
        if(TextUtils.isEmpty(s))
            return flag;
        else
            return Boolean.parseBoolean(s);
    }

    private final void initView()
    {
        if(!mViewAdded)
            postInMainThread(new Runnable() {

                public void run()
                {
                    ViewManager viewmanager = mRoot.getViewManager();
                    if(!ViewHolderScreenElement._2D_get2(ViewHolderScreenElement.this) && viewmanager != null)
                    {
                        View view = getView();
                        onUpdateView(view);
                        viewmanager.addView(view, ViewHolderScreenElement._2D_get1(ViewHolderScreenElement.this));
                        if(ViewHolderScreenElement._2D_get0(ViewHolderScreenElement.this))
                            view.setLayerType(2, null);
                        ViewHolderScreenElement._2D_set0(ViewHolderScreenElement.this, true);
                        onViewAdded(view);
                    }
                }

                final ViewHolderScreenElement this$0;

            
            {
                this$0 = ViewHolderScreenElement.this;
                super();
            }
            }
);
    }

    private boolean updateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        boolean flag = false;
        int i = (int)getWidth();
        if(layoutparams.width != i)
        {
            layoutparams.width = i;
            flag = true;
        }
        i = (int)getHeight();
        if(layoutparams.height != i)
        {
            layoutparams.height = i;
            flag = true;
        }
        return flag;
    }

    protected void doTick(long l)
    {
        if(mController == null)
        {
            super.doTick(l);
            getView().postInvalidate();
        } else
        {
            doTickSelf(l);
        }
        udpateView();
    }

    protected void doTickSelf(long l)
    {
        if(mAnimations != null)
        {
            int i = mAnimations.size();
            for(int j = 0; j < i; j++)
                ((BaseAnimation)mAnimations.get(j)).tick(l);

        }
        mAlpha = evaluateAlpha();
        int k;
        if(mAlpha < 0)
            k = 0;
        else
            k = mAlpha;
        mAlpha = k;
    }

    public void finish()
    {
        super.finish();
        finishView();
    }

    protected android.view.ViewGroup.LayoutParams getLayoutParam()
    {
        android.view.WindowManager.LayoutParams layoutparams = new android.view.WindowManager.LayoutParams(-1, -1);
        layoutparams.format = 1;
        layoutparams.flags = 256;
        return layoutparams;
    }

    protected abstract View getView();

    public void init()
    {
        super.init();
        if(mRoot.getViewManager() != null)
            initView();
        else
            Log.e("MAML ViewHolderScreenElement", "ViewManager must be set before init");
    }

    protected boolean isViewAdded()
    {
        return mViewAdded;
    }

    protected void onControllerCreated(RendererController renderercontroller)
    {
        renderercontroller.setListener(new ProxyListener(null));
    }

    protected void onUpdateView(View view)
    {
        if(mUpdatePosition)
        {
            view.setX(getAbsoluteLeft());
            view.setY(getAbsoluteTop());
        }
        if(mUpdateTranslation)
        {
            view.setPivotX(getPivotX());
            view.setPivotY(getPivotY());
            view.setRotation(getRotation());
            view.setRotationX(getRotationX());
            view.setRotationY(getRotationY());
            view.setAlpha((float)getAlpha() / 255F);
            view.setScaleX(getScaleX());
            view.setScaleY(getScaleY());
        }
        if(mUpdateSize && updateLayoutParams(mLayoutParams))
            view.setLayoutParams(mLayoutParams);
    }

    protected void onViewAdded(View view)
    {
    }

    protected void onViewRemoved(View view)
    {
    }

    protected void onVisibilityChange(final boolean _v)
    {
        postInMainThread(new Runnable() {

            public void run()
            {
                View view = getView();
                int i;
                if(_v)
                    i = 0;
                else
                    i = 4;
                view.setVisibility(i);
            }

            final ViewHolderScreenElement this$0;
            final boolean val$_v;

            
            {
                this$0 = ViewHolderScreenElement.this;
                _v = flag;
                super();
            }
        }
);
    }

    public void render(Canvas canvas)
    {
    }

    public void setHardwareLayer(final boolean b)
    {
        postInMainThread(new Runnable() {

            public void run()
            {
                View view = getView();
                byte byte0;
                if(b)
                    byte0 = 2;
                else
                    byte0 = 0;
                view.setLayerType(byte0, null);
            }

            final ViewHolderScreenElement this$0;
            final boolean val$b;

            
            {
                this$0 = ViewHolderScreenElement.this;
                b = flag;
                super();
            }
        }
);
    }

    protected void udpateView()
    {
        if(mUpdatePosition || mUpdateTranslation || mUpdateSize)
            postInMainThread(new Runnable() {

                public void run()
                {
                    if(ViewHolderScreenElement._2D_get2(ViewHolderScreenElement.this))
                    {
                        View view = getView();
                        onUpdateView(view);
                    }
                }

                final ViewHolderScreenElement this$0;

            
            {
                this$0 = ViewHolderScreenElement.this;
                super();
            }
            }
);
    }

    private static final String LOG_TAG = "MAML ViewHolderScreenElement";
    private boolean mHardware;
    private android.view.ViewGroup.LayoutParams mLayoutParams;
    protected boolean mUpdatePosition;
    protected boolean mUpdateSize;
    protected boolean mUpdateTranslation;
    private boolean mViewAdded;
}
