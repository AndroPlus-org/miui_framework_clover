// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml;

import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class MultipleRenderable
    implements RendererController.IRenderable
{
    private static class RenderableInfo
    {

        public boolean paused;
        public WeakReference r;

        public RenderableInfo(RendererController.IRenderable irenderable)
        {
            r = new WeakReference(irenderable);
        }
    }


    public MultipleRenderable()
    {
        mList = new ArrayList();
    }

    private RenderableInfo find(RendererController.IRenderable irenderable)
    {
        int i = mList.size();
        for(int j = 0; j < i; j++)
        {
            RenderableInfo renderableinfo = (RenderableInfo)mList.get(j);
            if(renderableinfo.r.get() == irenderable)
                return renderableinfo;
        }

        return null;
    }

    private int setPause(RendererController.IRenderable irenderable, boolean flag)
    {
        Log.d("MultipleRenderable", (new StringBuilder()).append("setPause: ").append(flag).append(" ").append(irenderable).toString());
        irenderable = find(irenderable);
        if(irenderable == null)
            return mActiveCount;
        if(((RenderableInfo) (irenderable)).paused != flag)
        {
            irenderable.paused = flag;
            int i;
            if(flag)
                i = mActiveCount - 1;
            else
                i = mActiveCount + 1;
            mActiveCount = i;
        }
        return mActiveCount;
    }

    public void add(RendererController.IRenderable irenderable)
    {
        this;
        JVM INSTR monitorenter ;
        RenderableInfo renderableinfo = find(irenderable);
        if(renderableinfo == null)
            break MISSING_BLOCK_LABEL_15;
        this;
        JVM INSTR monitorexit ;
        return;
        Object obj = JVM INSTR new #51  <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Log.d("MultipleRenderable", ((StringBuilder) (obj)).append("add: ").append(irenderable).toString());
        obj = mList;
        RenderableInfo renderableinfo1 = JVM INSTR new #8   <Class MultipleRenderable$RenderableInfo>;
        renderableinfo1.RenderableInfo(irenderable);
        ((ArrayList) (obj)).add(renderableinfo1);
        mActiveCount = mActiveCount + 1;
        this;
        JVM INSTR monitorexit ;
        return;
        irenderable;
        throw irenderable;
    }

    public void doRender()
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        mActiveCount = 0;
        i = mList.size() - 1;
_L2:
        if(i < 0)
            break MISSING_BLOCK_LABEL_97;
        Object obj;
        obj = (RenderableInfo)mList.get(i);
        if(!((RenderableInfo) (obj)).paused)
            break; /* Loop/switch isn't completed */
_L3:
        i--;
        if(true) goto _L2; else goto _L1
_L1:
        obj = (RendererController.IRenderable)((RenderableInfo) (obj)).r.get();
        if(obj == null)
            break MISSING_BLOCK_LABEL_85;
        ((RendererController.IRenderable) (obj)).doRender();
        mActiveCount = mActiveCount + 1;
          goto _L3
        Exception exception;
        exception;
        throw exception;
        mList.remove(i);
          goto _L3
        this;
        JVM INSTR monitorexit ;
    }

    public int pause(RendererController.IRenderable irenderable)
    {
        this;
        JVM INSTR monitorenter ;
        int i = setPause(irenderable, true);
        this;
        JVM INSTR monitorexit ;
        return i;
        irenderable;
        throw irenderable;
    }

    public void remove(RendererController.IRenderable irenderable)
    {
        this;
        JVM INSTR monitorenter ;
        int i = mList.size();
        if(i != 0)
            break MISSING_BLOCK_LABEL_17;
        this;
        JVM INSTR monitorexit ;
        return;
        i--;
_L3:
        if(i < 0) goto _L2; else goto _L1
_L1:
        Object obj;
        RendererController.IRenderable irenderable1;
        obj = (RenderableInfo)mList.get(i);
        irenderable1 = (RendererController.IRenderable)((RenderableInfo) (obj)).r.get();
        if(irenderable1 != null && irenderable1 != irenderable)
            continue; /* Loop/switch isn't completed */
        if(!((RenderableInfo) (obj)).paused)
            mActiveCount = mActiveCount - 1;
        mList.remove(i);
        obj = JVM INSTR new #51  <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Log.d("MultipleRenderable", ((StringBuilder) (obj)).append("remove: ").append(irenderable1).toString());
        i--;
          goto _L3
_L2:
        return;
        irenderable;
        throw irenderable;
    }

    public int resume(RendererController.IRenderable irenderable)
    {
        this;
        JVM INSTR monitorenter ;
        int i = setPause(irenderable, false);
        this;
        JVM INSTR monitorexit ;
        return i;
        irenderable;
        throw irenderable;
    }

    public int size()
    {
        this;
        JVM INSTR monitorenter ;
        int i = mList.size();
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    private static final String LOG_TAG = "MultipleRenderable";
    private int mActiveCount;
    private ArrayList mList;
}
