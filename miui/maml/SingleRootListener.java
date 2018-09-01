// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml;

import android.view.MotionEvent;
import java.lang.ref.WeakReference;

// Referenced classes of package miui.maml:
//            ScreenElementRoot

public class SingleRootListener
    implements RendererController.Listener
{

    public SingleRootListener(ScreenElementRoot screenelementroot, RendererController.IRenderable irenderable)
    {
        setRoot(screenelementroot);
        setRenderable(irenderable);
    }

    public void doRender()
    {
        RendererController.IRenderable irenderable;
        if(mRenderable != null)
            irenderable = (RendererController.IRenderable)mRenderable.get();
        else
            irenderable = null;
        if(irenderable != null)
            irenderable.doRender();
    }

    public void finish()
    {
        mRoot.finish();
    }

    public void init()
    {
        mRoot.init();
    }

    public void onHover(MotionEvent motionevent)
    {
        mRoot.onHover(motionevent);
        motionevent.recycle();
    }

    public void onTouch(MotionEvent motionevent)
    {
        mRoot.onTouch(motionevent);
        motionevent.recycle();
    }

    public void pause()
    {
        mRoot.pause();
    }

    public void resume()
    {
        mRoot.resume();
    }

    public void setRenderable(RendererController.IRenderable irenderable)
    {
        WeakReference weakreference = null;
        if(irenderable != null)
            weakreference = new WeakReference(irenderable);
        mRenderable = weakreference;
    }

    public void setRoot(ScreenElementRoot screenelementroot)
    {
        if(screenelementroot == null)
        {
            throw new NullPointerException("root is null");
        } else
        {
            mRoot = screenelementroot;
            return;
        }
    }

    public void tick(long l)
    {
        mRoot.tick(l);
    }

    public void triggerUpdate()
    {
        RendererController.IRenderable irenderable;
        if(mRenderable != null)
            irenderable = (RendererController.IRenderable)mRenderable.get();
        else
            irenderable = null;
        if(irenderable != null && (irenderable instanceof RendererController.ISelfUpdateRenderable))
            ((RendererController.ISelfUpdateRenderable)irenderable).triggerUpdate();
    }

    private static final String LOG_TAG = "SingleRootListener";
    private WeakReference mRenderable;
    private ScreenElementRoot mRoot;
}
