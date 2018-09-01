// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.View;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import miui.maml.*;
import miui.maml.data.IndexedVariable;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.elements:
//            ViewHolderScreenElement

public class GLLayerScreenElement extends ViewHolderScreenElement
{
    private class GLRenderer
        implements android.opengl.GLSurfaceView.Renderer
    {

        public void onDrawFrame(GL10 gl10)
        {
            if(GLLayerScreenElement._2D_get4(GLLayerScreenElement.this) != null)
            {
                GLLayerScreenElement._2D_get0(GLLayerScreenElement.this).set(gl10);
                GLLayerScreenElement._2D_get4(GLLayerScreenElement.this).perform();
                GLLayerScreenElement._2D_get0(GLLayerScreenElement.this).set(null);
            }
            if(mController != null)
                mController.doneRender();
        }

        public void onSurfaceChanged(GL10 gl10, int i, int j)
        {
            if(GLLayerScreenElement._2D_get2(GLLayerScreenElement.this) != null)
            {
                GLLayerScreenElement._2D_get0(GLLayerScreenElement.this).set(gl10);
                GLLayerScreenElement._2D_get6(GLLayerScreenElement.this).set(i);
                GLLayerScreenElement._2D_get1(GLLayerScreenElement.this).set(j);
                GLLayerScreenElement._2D_get2(GLLayerScreenElement.this).perform();
                GLLayerScreenElement._2D_get0(GLLayerScreenElement.this).set(null);
            }
        }

        public void onSurfaceCreated(GL10 gl10, EGLConfig eglconfig)
        {
            if(GLLayerScreenElement._2D_get3(GLLayerScreenElement.this) != null)
            {
                GLLayerScreenElement._2D_get0(GLLayerScreenElement.this).set(gl10);
                GLLayerScreenElement._2D_get3(GLLayerScreenElement.this).perform();
                GLLayerScreenElement._2D_get0(GLLayerScreenElement.this).set(null);
            }
        }

        final GLLayerScreenElement this$0;

        private GLRenderer()
        {
            this$0 = GLLayerScreenElement.this;
            super();
        }

        GLRenderer(GLRenderer glrenderer)
        {
            this();
        }
    }

    private class ProxyListener extends miui.maml.RendererController.EmptyListener
    {

        public void doRender()
        {
            GLLayerScreenElement._2D_get5(GLLayerScreenElement.this).requestRender();
        }

        public void tick(long l)
        {
        }

        public void triggerUpdate()
        {
            mRoot.getRendererController().triggerUpdate();
        }

        final GLLayerScreenElement this$0;

        private ProxyListener()
        {
            this$0 = GLLayerScreenElement.this;
            super();
        }

        ProxyListener(ProxyListener proxylistener)
        {
            this();
        }
    }


    static IndexedVariable _2D_get0(GLLayerScreenElement gllayerscreenelement)
    {
        return gllayerscreenelement.mCanvasVar;
    }

    static IndexedVariable _2D_get1(GLLayerScreenElement gllayerscreenelement)
    {
        return gllayerscreenelement.mHVar;
    }

    static CommandTrigger _2D_get2(GLLayerScreenElement gllayerscreenelement)
    {
        return gllayerscreenelement.mOnSurfaceChangeCommands;
    }

    static CommandTrigger _2D_get3(GLLayerScreenElement gllayerscreenelement)
    {
        return gllayerscreenelement.mOnSurfaceCreateCommands;
    }

    static CommandTrigger _2D_get4(GLLayerScreenElement gllayerscreenelement)
    {
        return gllayerscreenelement.mOnSurfaceDrawCommands;
    }

    static GLSurfaceView _2D_get5(GLLayerScreenElement gllayerscreenelement)
    {
        return gllayerscreenelement.mView;
    }

    static IndexedVariable _2D_get6(GLLayerScreenElement gllayerscreenelement)
    {
        return gllayerscreenelement.mWVar;
    }

    public GLLayerScreenElement(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        load(element);
    }

    private void load(Element element)
    {
        mView = new GLSurfaceView(mRoot.getContext().mContext);
        mLayoutParams = new android.view.WindowManager.LayoutParams((int)mRoot.getWidth(), (int)mRoot.getHeight());
        mLayoutParams.format = 1;
        mLayoutParams.flags = 256;
        mView.setRenderer(new GLRenderer(null));
        int i;
        if(mController != null)
            i = 0;
        else
            i = 1;
        mView.setRenderMode(i);
        if(mTriggers != null)
        {
            mOnSurfaceCreateCommands = mTriggers.find("create");
            mOnSurfaceChangeCommands = mTriggers.find("change");
            mOnSurfaceDrawCommands = mTriggers.find("draw");
        }
        if(mOnSurfaceDrawCommands == null)
            Log.e("GLLayerScreenElement", "no draw commands.");
        element = getVariables();
        mCanvasVar = new IndexedVariable("__objGLCanvas", element, false);
        mViewVar = new IndexedVariable("__objGLView", element, false);
        mWVar = new IndexedVariable("__w", element, true);
        mHVar = new IndexedVariable("__h", element, true);
    }

    protected void doTick(long l)
    {
        doTickSelf(l);
        udpateView();
    }

    protected View getView()
    {
        return mView;
    }

    public void init()
    {
        mViewVar.set(mView);
        super.init();
    }

    protected void onControllerCreated(RendererController renderercontroller)
    {
        renderercontroller.setListener(new ProxyListener(null));
    }

    private static final String LOG_TAG = "MAML GLLayerScreenElement";
    public static final String TAG_NAME = "GLLayer";
    private IndexedVariable mCanvasVar;
    private IndexedVariable mHVar;
    private android.view.WindowManager.LayoutParams mLayoutParams;
    private CommandTrigger mOnSurfaceChangeCommands;
    private CommandTrigger mOnSurfaceCreateCommands;
    private CommandTrigger mOnSurfaceDrawCommands;
    private GLSurfaceView mView;
    private IndexedVariable mViewVar;
    private IndexedVariable mWVar;
}
