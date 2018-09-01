// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.view.*;
import miui.maml.*;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.elements:
//            ElementGroupRC

public class WindowScreenElement extends ElementGroupRC
{
    private class ProxyListener extends miui.maml.RendererController.EmptyListener
    {

        public void doRender()
        {
            WindowScreenElement._2D_get0(WindowScreenElement.this).postInvalidate();
        }

        public void onHover(MotionEvent motionevent)
        {
            WindowScreenElement.this.onHover(motionevent);
        }

        public void onTouch(MotionEvent motionevent)
        {
            WindowScreenElement.this.onTouch(motionevent);
        }

        public void tick(long l)
        {
            doTick(l);
        }

        public void triggerUpdate()
        {
            mRoot.getRendererController().triggerUpdate();
        }

        final WindowScreenElement this$0;

        private ProxyListener()
        {
            this$0 = WindowScreenElement.this;
            super();
        }

        ProxyListener(ProxyListener proxylistener)
        {
            this();
        }
    }

    private class WindowView extends View
    {

        protected void onDraw(Canvas canvas)
        {
            doRenderWithTranslation(canvas);
            mController.doneRender();
        }

        public boolean onTouchEvent(MotionEvent motionevent)
        {
            mController.postMessage(motionevent);
            return super.onTouchEvent(motionevent);
        }

        final WindowScreenElement this$0;

        public WindowView(Context context)
        {
            this$0 = WindowScreenElement.this;
            super(context);
        }
    }


    static WindowView _2D_get0(WindowScreenElement windowscreenelement)
    {
        return windowscreenelement.mView;
    }

    static void _2D_wrap0(WindowScreenElement windowscreenelement)
    {
        windowscreenelement.addView();
    }

    static void _2D_wrap1(WindowScreenElement windowscreenelement)
    {
        windowscreenelement.removeView();
    }

    public WindowScreenElement(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        mWindowContext = screenelementroot.getContext().mContext;
        mView = new WindowView(mWindowContext);
        mWindowManager = (WindowManager)mWindowContext.getSystemService("window");
        mLayoutParams = new android.view.WindowManager.LayoutParams((int)screenelementroot.getWidth(), (int)screenelementroot.getHeight());
        mLayoutParams.format = 1;
        mLayoutParams.flags = 256;
    }

    private final void addView()
    {
        if(!mViewAdded)
        {
            mWindowManager.addView(mView, mLayoutParams);
            mViewAdded = true;
        }
    }

    private final void removeView()
    {
        if(mViewAdded)
        {
            mWindowManager.removeView(mView);
            mViewAdded = false;
        }
    }

    public void init()
    {
        super.init();
        if(isVisible())
            addView();
    }

    protected void onControllerCreated(RendererController renderercontroller)
    {
        renderercontroller.setListener(new ProxyListener(null));
    }

    protected void onVisibilityChange(final boolean _v)
    {
        getContext().getHandler().post(new Runnable() {

            public void run()
            {
                if(_v)
                    WindowScreenElement._2D_wrap0(WindowScreenElement.this);
                else
                    WindowScreenElement._2D_wrap1(WindowScreenElement.this);
            }

            final WindowScreenElement this$0;
            final boolean val$_v;

            
            {
                this$0 = WindowScreenElement.this;
                _v = flag;
                super();
            }
        }
);
    }

    public void render(Canvas canvas)
    {
    }

    public static final String TAG_NAME = "Window";
    private android.view.WindowManager.LayoutParams mLayoutParams;
    private WindowView mView;
    private boolean mViewAdded;
    private Context mWindowContext;
    private WindowManager mWindowManager;
}
