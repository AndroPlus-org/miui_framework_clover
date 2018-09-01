// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml;

import android.util.Log;
import android.view.MotionEvent;
import java.util.*;
import miui.maml.elements.FramerateController;

// Referenced classes of package miui.maml:
//            FramerateTokenList, RenderThread

public class RendererController
    implements FramerateTokenList.FramerateChangeListener
{
    public static abstract class EmptyListener
        implements Listener
    {

        public void doRender()
        {
        }

        public void finish()
        {
        }

        public void init()
        {
        }

        public void onHover(MotionEvent motionevent)
        {
        }

        public void onTouch(MotionEvent motionevent)
        {
        }

        public void pause()
        {
        }

        public void resume()
        {
        }

        public void tick(long l)
        {
        }

        public EmptyListener()
        {
        }
    }

    public static interface IRenderable
    {

        public abstract void doRender();
    }

    public static interface ISelfUpdateRenderable
        extends IRenderable
    {

        public abstract void triggerUpdate();
    }

    public static interface Listener
        extends ISelfUpdateRenderable
    {

        public abstract void finish();

        public abstract void init();

        public abstract void onHover(MotionEvent motionevent);

        public abstract void onTouch(MotionEvent motionevent);

        public abstract void pause();

        public abstract void resume();

        public abstract void tick(long l);
    }


    public RendererController()
    {
        mFramerateControllers = new ArrayList();
        mFramerateTokenList = new FramerateTokenList();
        mSelfPaused = true;
        mLock = new byte[0];
        mFrameTime = 0x7fffffffffffffffL;
        mMsgLock = new Object();
        mTouchX = -1F;
        mTouchY = -1F;
        mWriteRunnableQueue = new ArrayList();
        mReadRunnableQueue = new ArrayList();
        mWriteRunnableQueueLock = new Object();
        mFramerateTokenList = new FramerateTokenList(this);
    }

    public RendererController(Listener listener)
    {
        this();
        setListener(listener);
    }

    public void addFramerateController(FramerateController frameratecontroller)
    {
        if(mFramerateControllers.contains(frameratecontroller))
        {
            return;
        } else
        {
            mFramerateControllers.add(frameratecontroller);
            return;
        }
    }

    public final FramerateTokenList.FramerateToken createToken(String s)
    {
        return mFramerateTokenList.createToken(s);
    }

    public final void doRender()
    {
        if(mListener != null)
        {
            mPendingRender = true;
            mListener.doRender();
        }
    }

    public final void doneRender()
    {
        mPendingRender = false;
        triggerUpdate();
    }

    public void finish()
    {
        byte abyte0[] = mLock;
        abyte0;
        JVM INSTR monitorenter ;
        boolean flag = mInited;
        if(flag)
            break MISSING_BLOCK_LABEL_19;
        abyte0;
        JVM INSTR monitorexit ;
        return;
        Object obj = mListener;
        if(obj == null)
            break MISSING_BLOCK_LABEL_37;
        mListener.finish();
_L1:
        obj = mMsgLock;
        obj;
        JVM INSTR monitorenter ;
        if(mMsgQueue != null)
            for(; mMsgQueue.size() > 0; ((MotionEvent)mMsgQueue.poll()).recycle());
        break MISSING_BLOCK_LABEL_107;
        Exception exception1;
        exception1;
        obj;
        JVM INSTR monitorexit ;
        throw exception1;
        obj;
        abyte0;
        JVM INSTR monitorexit ;
        throw obj;
        obj;
        ((Exception) (obj)).printStackTrace();
        Log.e("RendererController", ((Exception) (obj)).toString());
          goto _L1
        obj;
        JVM INSTR monitorexit ;
        Object obj1 = mWriteRunnableQueueLock;
        obj1;
        JVM INSTR monitorenter ;
        mWriteRunnableQueue.clear();
        obj1;
        JVM INSTR monitorexit ;
        mInited = false;
        abyte0;
        JVM INSTR monitorexit ;
        mFramerateTokenList.clear();
        return;
        Exception exception;
        exception;
        obj1;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final MotionEvent getMessage()
    {
        MotionEvent motionevent;
        motionevent = null;
        if(mMsgQueue == null)
            return null;
        Object obj = mMsgLock;
        obj;
        JVM INSTR monitorenter ;
        LinkedList linkedlist = mMsgQueue;
        if(linkedlist != null) goto _L2; else goto _L1
_L1:
        obj;
        JVM INSTR monitorexit ;
        return motionevent;
_L2:
        motionevent = (MotionEvent)mMsgQueue.poll();
        if(true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public final boolean hasInited()
    {
        return mInited;
    }

    public final boolean hasMessage()
    {
        boolean flag;
        flag = false;
        if(mMsgQueue == null)
            return false;
        Object obj = mMsgLock;
        obj;
        JVM INSTR monitorenter ;
        LinkedList linkedlist = mMsgQueue;
        if(linkedlist != null) goto _L2; else goto _L1
_L1:
        obj;
        JVM INSTR monitorexit ;
        return flag;
_L2:
        int i = mMsgQueue.size();
        if(i > 0)
            flag = true;
        if(true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public final boolean hasRunnable()
    {
        Object obj = mWriteRunnableQueueLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag = mWriteRunnableQueue.isEmpty();
        obj;
        JVM INSTR monitorexit ;
        return flag ^ true;
        Exception exception;
        exception;
        throw exception;
    }

    public void init()
    {
        byte abyte0[] = mLock;
        abyte0;
        JVM INSTR monitorenter ;
        boolean flag = mInited;
        if(!flag)
            break MISSING_BLOCK_LABEL_19;
        abyte0;
        JVM INSTR monitorexit ;
        return;
        Listener listener = mListener;
        if(listener == null)
            break MISSING_BLOCK_LABEL_37;
        mListener.init();
_L1:
        mInited = true;
        abyte0;
        JVM INSTR monitorexit ;
        return;
        Object obj;
        obj;
        ((Exception) (obj)).printStackTrace();
        Log.e("RendererController", ((Exception) (obj)).toString());
          goto _L1
        obj;
        throw obj;
    }

    public final boolean isSelfPaused()
    {
        return mSelfPaused;
    }

    public void onFrameRateChage(float f, float f1)
    {
        if(f1 > 0.0F)
            triggerUpdate();
    }

    public void onHover(MotionEvent motionevent)
    {
        if(motionevent == null)
            return;
        if(mListener == null)
            break MISSING_BLOCK_LABEL_22;
        mListener.onHover(motionevent);
_L1:
        return;
        motionevent;
        motionevent.printStackTrace();
        Log.e("RendererController", motionevent.toString());
          goto _L1
        motionevent;
        motionevent.printStackTrace();
        Log.e("RendererController", motionevent.toString());
          goto _L1
    }

    public void onTouch(MotionEvent motionevent)
    {
        if(motionevent == null)
            return;
        if(mListener == null)
            break MISSING_BLOCK_LABEL_22;
        mListener.onTouch(motionevent);
_L1:
        return;
        motionevent;
        motionevent.printStackTrace();
        Log.e("RendererController", motionevent.toString());
          goto _L1
        motionevent;
        motionevent.printStackTrace();
        Log.e("RendererController", motionevent.toString());
          goto _L1
    }

    public void pause()
    {
        if(!mInited)
            return;
        byte abyte0[] = mLock;
        abyte0;
        JVM INSTR monitorenter ;
        mPaused = true;
        if(!mSelfPaused && mListener != null)
            mListener.pause();
        abyte0;
        JVM INSTR monitorexit ;
        mPendingRender = false;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final boolean pendingRender()
    {
        return mPendingRender;
    }

    public void postMessage(MotionEvent motionevent)
    {
        Object obj = mMsgLock;
        obj;
        JVM INSTR monitorenter ;
        if(mMsgQueue == null)
        {
            LinkedList linkedlist = JVM INSTR new #137 <Class LinkedList>;
            linkedlist.LinkedList();
            mMsgQueue = linkedlist;
        }
        if(motionevent.getActionMasked() == 2 && motionevent.getX() == mTouchX) goto _L2; else goto _L1
_L1:
        mMsgQueue.add(motionevent);
        mTouchX = motionevent.getX();
        mTouchY = motionevent.getY();
_L8:
        if(mMsgQueue.size() <= 3) goto _L4; else goto _L3
_L3:
        Object obj1 = null;
        Iterator iterator = mMsgQueue.iterator();
_L6:
        motionevent = obj1;
        if(!iterator.hasNext())
            break; /* Loop/switch isn't completed */
        motionevent = (MotionEvent)iterator.next();
        if(motionevent.getActionMasked() != 2) goto _L6; else goto _L5
_L5:
        if(motionevent == null) goto _L4; else goto _L7
_L7:
        mMsgQueue.remove(motionevent);
        motionevent.recycle();
_L4:
        obj;
        JVM INSTR monitorexit ;
        triggerUpdate();
        return;
_L2:
        float f;
        float f1;
        f = motionevent.getY();
        f1 = mTouchY;
        if(f == f1) goto _L8; else goto _L1
        motionevent;
        throw motionevent;
    }

    public void postRunnable(Runnable runnable)
    {
        if(runnable == null)
            throw new NullPointerException("postRunnable null");
        Object obj = mWriteRunnableQueueLock;
        obj;
        JVM INSTR monitorenter ;
        if(!mWriteRunnableQueue.contains(runnable))
            mWriteRunnableQueue.add(runnable);
        obj;
        JVM INSTR monitorexit ;
        requestUpdate();
        return;
        runnable;
        throw runnable;
    }

    public void removeFramerateController(FramerateController frameratecontroller)
    {
        mFramerateControllers.remove(frameratecontroller);
    }

    public final void requestUpdate()
    {
        mShouldUpdate = true;
        triggerUpdate();
    }

    public void resume()
    {
        if(!mInited)
            return;
        byte abyte0[] = mLock;
        abyte0;
        JVM INSTR monitorenter ;
        mPaused = false;
        if(!mSelfPaused && mListener != null)
            mListener.resume();
        abyte0;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void runRunnables()
    {
        Object obj = mWriteRunnableQueueLock;
        obj;
        JVM INSTR monitorenter ;
        ArrayList arraylist = mWriteRunnableQueue;
        mWriteRunnableQueue = mReadRunnableQueue;
        mReadRunnableQueue = arraylist;
        obj;
        JVM INSTR monitorexit ;
        int i = mReadRunnableQueue.size();
        for(int j = 0; j < i; j++)
            ((Runnable)mReadRunnableQueue.get(j)).run();

        break MISSING_BLOCK_LABEL_72;
        Exception exception;
        exception;
        throw exception;
        mReadRunnableQueue.clear();
        return;
    }

    public void selfPause()
    {
        if(!mInited)
            return;
        byte abyte0[] = mLock;
        abyte0;
        JVM INSTR monitorenter ;
        if(!mSelfPaused)
        {
            mSelfPaused = true;
            if(!mPaused && mListener != null)
                mListener.pause();
        }
        abyte0;
        JVM INSTR monitorexit ;
        mPendingRender = false;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void selfResume()
    {
        if(!mInited)
            return;
        byte abyte0[] = mLock;
        abyte0;
        JVM INSTR monitorenter ;
        if(mSelfPaused)
        {
            mSelfPaused = false;
            if(!mPaused && mListener != null)
                mListener.resume();
        }
        abyte0;
        JVM INSTR monitorexit ;
        if(mRenderThread != null)
            mRenderThread.setPaused(false);
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setListener(Listener listener)
    {
        mListener = listener;
    }

    public void setRenderThread(RenderThread renderthread)
    {
        mRenderThread = renderthread;
    }

    public void tick(long l)
    {
        mShouldUpdate = false;
        if(mListener != null)
            try
            {
                mListener.tick(l);
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
                Log.e("RendererController", exception.toString());
            }
        mLastUpdateSystemTime = l;
    }

    public void triggerUpdate()
    {
        if(mRenderThread != null)
            mRenderThread.signal();
        if(mListener != null)
            mListener.triggerUpdate();
    }

    public long update(long l)
    {
label0:
        {
            long l1 = updateFramerate(l);
            boolean flag = hasRunnable();
            long l2;
            if(mPendingRender)
            {
                l2 = l1;
                if(!flag)
                    break label0;
            }
            runRunnables();
            MotionEvent motionevent = getMessage();
            if(motionevent != null)
                if(motionevent.isTouchEvent())
                    onTouch(motionevent);
                else
                    onHover(motionevent);
            tick(l);
            doRender();
            if(!mShouldUpdate)
            {
                l2 = l1;
                if(!hasMessage())
                    break label0;
            }
            l2 = 0L;
        }
        return l2;
    }

    public final long updateFramerate(long l)
    {
        long l1 = 0x7fffffffffffffffL;
        int i = mFramerateControllers.size();
        for(int j = 0; j < i;)
        {
            long l2 = ((FramerateController)mFramerateControllers.get(j)).updateFramerate(l);
            long l3 = l1;
            if(l2 < l1)
                l3 = l2;
            j++;
            l1 = l3;
        }

        float f = mFramerateTokenList.getFramerate();
        if(mCurFramerate != f)
        {
            if(mCurFramerate >= 1.0F && f < 1.0F)
                requestUpdate();
            mCurFramerate = f;
            if(f != 0.0F)
                l = (long)(1000F / f);
            else
                l = 0x7fffffffffffffffL;
            mFrameTime = l;
        }
        l = l1;
        if(mFrameTime < l1)
            l = mFrameTime;
        return l;
    }

    public long updateIfNeeded(long l)
    {
        long l1;
        long l2;
        long l3;
        l1 = updateFramerate(l);
        boolean flag;
        if(mFrameTime < 0x7fffffffffffffffL)
            l2 = mFrameTime - (l - mLastUpdateSystemTime);
        else
            l2 = 0x7fffffffffffffffL;
        flag = hasRunnable();
        if(l2 > 0L && !mShouldUpdate && !hasMessage() && !flag) goto _L2; else goto _L1
_L1:
label0:
        {
            if(mPendingRender)
            {
                l3 = l1;
                if(!flag)
                    break label0;
            }
            runRunnables();
            MotionEvent motionevent = getMessage();
            if(motionevent != null)
                if(motionevent.isTouchEvent())
                    onTouch(motionevent);
                else
                    onHover(motionevent);
            tick(l);
            doRender();
            if(!mShouldUpdate)
            {
                l3 = l1;
                if(!hasMessage())
                    break label0;
            }
            l3 = 0L;
        }
_L4:
        return l3;
_L2:
        l3 = l1;
        if(l2 < l1)
            l3 = l2;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static final String LOG_TAG = "RendererController";
    private static final int MAX_MSG_COUNT = 3;
    private float mCurFramerate;
    private long mFrameTime;
    private ArrayList mFramerateControllers;
    private FramerateTokenList mFramerateTokenList;
    private boolean mInited;
    private long mLastUpdateSystemTime;
    private Listener mListener;
    private byte mLock[];
    private Object mMsgLock;
    private LinkedList mMsgQueue;
    private boolean mPaused;
    private boolean mPendingRender;
    private ArrayList mReadRunnableQueue;
    private RenderThread mRenderThread;
    private boolean mSelfPaused;
    private boolean mShouldUpdate;
    private float mTouchX;
    private float mTouchY;
    private ArrayList mWriteRunnableQueue;
    private Object mWriteRunnableQueueLock;
}
