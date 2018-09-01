// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml;

import android.graphics.Canvas;
import android.util.Log;
import java.lang.ref.WeakReference;

// Referenced classes of package miui.maml:
//            MultipleRenderable, ScreenElementRoot, RenderThread

public class RendererCore
{
    public static interface OnReleaseListener
    {

        public abstract boolean OnRendererCoreReleased(RendererCore renderercore);
    }


    public RendererCore(ScreenElementRoot screenelementroot, RenderThread renderthread)
    {
        this(screenelementroot, renderthread, true);
    }

    public RendererCore(ScreenElementRoot screenelementroot, RenderThread renderthread, boolean flag)
    {
        mMultipleRenderable = new MultipleRenderable();
        mThread = renderthread;
        mRoot = screenelementroot;
        mRoot.setRenderControllerRenderable(mMultipleRenderable);
        mRoot.selfInit();
        if(flag)
            attach(renderthread);
    }

    public void addRenderable(RendererController.IRenderable irenderable)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = mCleaned;
        if(!flag)
            break MISSING_BLOCK_LABEL_14;
        this;
        JVM INSTR monitorexit ;
        return;
        mMultipleRenderable.add(irenderable);
        StringBuilder stringbuilder = JVM INSTR new #62  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.d("RendererCore", stringbuilder.append("add: ").append(irenderable).append(" size:").append(mMultipleRenderable.size()).toString());
        mRoot.selfResume();
        mReleased = false;
        this;
        JVM INSTR monitorexit ;
        return;
        irenderable;
        throw irenderable;
    }

    public void attach(RenderThread renderthread)
    {
        mThread = renderthread;
        if(mRoot != null)
        {
            mRoot.attachToRenderThread(mThread);
            mRoot.requestUpdate();
        }
    }

    public void cleanUp()
    {
        mCleaned = true;
        Log.d("RendererCore", (new StringBuilder()).append("cleanUp: ").append(toString()).toString());
        if(mRoot != null)
        {
            mRoot.detachFromRenderThread(mThread);
            mRoot.selfFinish();
            mRoot = null;
        }
    }

    protected void finalize()
        throws Throwable
    {
        cleanUp();
        super.finalize();
    }

    public ScreenElementRoot getRoot()
    {
        return mRoot;
    }

    public void pauseRenderable(RendererController.IRenderable irenderable)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = mCleaned;
        if(!flag)
            break MISSING_BLOCK_LABEL_14;
        this;
        JVM INSTR monitorexit ;
        return;
        if(mMultipleRenderable.pause(irenderable) == 0)
        {
            irenderable = JVM INSTR new #62  <Class StringBuilder>;
            irenderable.StringBuilder();
            Log.d("RendererCore", irenderable.append("self pause: ").append(toString()).toString());
            mRoot.selfPause();
        }
        this;
        JVM INSTR monitorexit ;
        return;
        irenderable;
        throw irenderable;
    }

    public void removeRenderable(RendererController.IRenderable irenderable)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = mCleaned;
        if(!flag)
            break MISSING_BLOCK_LABEL_14;
        this;
        JVM INSTR monitorexit ;
        return;
        mMultipleRenderable.remove(irenderable);
        StringBuilder stringbuilder = JVM INSTR new #62  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.d("RendererCore", stringbuilder.append("remove: ").append(irenderable).append(" size:").append(mMultipleRenderable.size()).toString());
        if(mMultipleRenderable.size() == 0)
        {
            mRoot.selfPause();
            if(!mReleased && mOnReleaseListener != null && mOnReleaseListener.get() != null && ((OnReleaseListener)mOnReleaseListener.get()).OnRendererCoreReleased(this))
                cleanUp();
            mReleased = true;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        irenderable;
        throw irenderable;
    }

    public void render(Canvas canvas)
    {
        if(mCleaned || mThread.isStarted() ^ true)
        {
            return;
        } else
        {
            mRoot.render(canvas);
            return;
        }
    }

    public void resumeRenderable(RendererController.IRenderable irenderable)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = mCleaned;
        if(!flag)
            break MISSING_BLOCK_LABEL_14;
        this;
        JVM INSTR monitorexit ;
        return;
        mMultipleRenderable.resume(irenderable);
        irenderable = JVM INSTR new #62  <Class StringBuilder>;
        irenderable.StringBuilder();
        Log.d("RendererCore", irenderable.append("self resume: ").append(toString()).toString());
        mRoot.selfResume();
        this;
        JVM INSTR monitorexit ;
        return;
        irenderable;
        throw irenderable;
    }

    public void setOnReleaseListener(OnReleaseListener onreleaselistener)
    {
        mOnReleaseListener = new WeakReference(onreleaselistener);
    }

    private static final String LOG_TAG = "RendererCore";
    private boolean mCleaned;
    private MultipleRenderable mMultipleRenderable;
    private WeakReference mOnReleaseListener;
    private boolean mReleased;
    private ScreenElementRoot mRoot;
    private RenderThread mThread;
}
