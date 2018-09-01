// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.SystemClock;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public abstract class BaseSurfaceHolder
    implements SurfaceHolder
{

    public BaseSurfaceHolder()
    {
        mSurface = new Surface();
        mRequestedWidth = -1;
        mRequestedHeight = -1;
        mRequestedFormat = -1;
        mRequestedType = -1;
        mLastLockTime = 0L;
        mType = -1;
    }

    private final Canvas internalLockCanvas(Rect rect, boolean flag)
    {
        Object obj;
        Object obj1;
        if(mType == 3)
            throw new android.view.SurfaceHolder.BadSurfaceTypeException("Surface type is SURFACE_TYPE_PUSH_BUFFERS");
        mSurfaceLock.lock();
        obj = null;
        obj1 = obj;
        if(!onAllowLockCanvas()) goto _L2; else goto _L1
_L1:
        obj1 = rect;
        if(rect == null)
        {
            if(mTmpDirty == null)
                mTmpDirty = new Rect();
            mTmpDirty.set(mSurfaceFrame);
            obj1 = mTmpDirty;
        }
        if(!flag) goto _L4; else goto _L3
_L3:
        try
        {
            obj1 = mSurface.lockHardwareCanvas();
        }
        // Misplaced declaration of an exception variable
        catch(Rect rect)
        {
            Log.e("BaseSurfaceHolder", "Exception locking surface", rect);
            obj1 = obj;
        }
_L2:
        if(obj1 != null)
        {
            mLastLockTime = SystemClock.uptimeMillis();
            return ((Canvas) (obj1));
        }
        break; /* Loop/switch isn't completed */
_L4:
        obj1 = mSurface.lockCanvas(((Rect) (obj1)));
        if(true) goto _L2; else goto _L5
_L5:
        long l = SystemClock.uptimeMillis();
        long l1 = mLastLockTime + 100L;
        long l2 = l;
        if(l1 > l)
        {
            try
            {
                Thread.sleep(l1 - l);
            }
            // Misplaced declaration of an exception variable
            catch(Rect rect) { }
            l2 = SystemClock.uptimeMillis();
        }
        mLastLockTime = l2;
        mSurfaceLock.unlock();
        return null;
    }

    public void addCallback(android.view.SurfaceHolder.Callback callback)
    {
        ArrayList arraylist = mCallbacks;
        arraylist;
        JVM INSTR monitorenter ;
        if(!mCallbacks.contains(callback))
            mCallbacks.add(callback);
        arraylist;
        JVM INSTR monitorexit ;
        return;
        callback;
        throw callback;
    }

    public android.view.SurfaceHolder.Callback[] getCallbacks()
    {
        if(mHaveGottenCallbacks)
            return mGottenCallbacks;
        ArrayList arraylist = mCallbacks;
        arraylist;
        JVM INSTR monitorenter ;
        int i = mCallbacks.size();
        if(i <= 0)
            break MISSING_BLOCK_LABEL_79;
        if(mGottenCallbacks == null || mGottenCallbacks.length != i)
            mGottenCallbacks = new android.view.SurfaceHolder.Callback[i];
        mCallbacks.toArray(mGottenCallbacks);
_L1:
        mHaveGottenCallbacks = true;
        arraylist;
        JVM INSTR monitorexit ;
        return mGottenCallbacks;
        mGottenCallbacks = null;
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    public int getRequestedFormat()
    {
        return mRequestedFormat;
    }

    public int getRequestedHeight()
    {
        return mRequestedHeight;
    }

    public int getRequestedType()
    {
        return mRequestedType;
    }

    public int getRequestedWidth()
    {
        return mRequestedWidth;
    }

    public Surface getSurface()
    {
        return mSurface;
    }

    public Rect getSurfaceFrame()
    {
        return mSurfaceFrame;
    }

    public Canvas lockCanvas()
    {
        return internalLockCanvas(null, false);
    }

    public Canvas lockCanvas(Rect rect)
    {
        return internalLockCanvas(rect, false);
    }

    public Canvas lockHardwareCanvas()
    {
        return internalLockCanvas(null, true);
    }

    public abstract boolean onAllowLockCanvas();

    public abstract void onRelayoutContainer();

    public abstract void onUpdateSurface();

    public void removeCallback(android.view.SurfaceHolder.Callback callback)
    {
        ArrayList arraylist = mCallbacks;
        arraylist;
        JVM INSTR monitorenter ;
        mCallbacks.remove(callback);
        arraylist;
        JVM INSTR monitorexit ;
        return;
        callback;
        throw callback;
    }

    public void setFixedSize(int i, int j)
    {
        if(mRequestedWidth != i || mRequestedHeight != j)
        {
            mRequestedWidth = i;
            mRequestedHeight = j;
            onRelayoutContainer();
        }
    }

    public void setFormat(int i)
    {
        if(mRequestedFormat != i)
        {
            mRequestedFormat = i;
            onUpdateSurface();
        }
    }

    public void setSizeFromLayout()
    {
        if(mRequestedWidth != -1 || mRequestedHeight != -1)
        {
            mRequestedHeight = -1;
            mRequestedWidth = -1;
            onRelayoutContainer();
        }
    }

    public void setSurfaceFrameSize(int i, int j)
    {
        mSurfaceFrame.top = 0;
        mSurfaceFrame.left = 0;
        mSurfaceFrame.right = i;
        mSurfaceFrame.bottom = j;
    }

    public void setType(int i)
    {
        i;
        JVM INSTR tableswitch 1 2: default 24
    //                   1 57
    //                   2 57;
           goto _L1 _L2 _L2
_L1:
        i;
        JVM INSTR tableswitch 0 3: default 56
    //                   0 62
    //                   1 56
    //                   2 56
    //                   3 62;
           goto _L3 _L4 _L3 _L3 _L4
_L3:
        return;
_L2:
        i = 0;
          goto _L1
_L4:
        if(mRequestedType != i)
        {
            mRequestedType = i;
            onUpdateSurface();
        }
          goto _L3
    }

    public void ungetCallbacks()
    {
        mHaveGottenCallbacks = false;
    }

    public void unlockCanvasAndPost(Canvas canvas)
    {
        mSurface.unlockCanvasAndPost(canvas);
        mSurfaceLock.unlock();
    }

    static final boolean DEBUG = false;
    private static final String TAG = "BaseSurfaceHolder";
    public final ArrayList mCallbacks = new ArrayList();
    android.view.SurfaceHolder.Callback mGottenCallbacks[];
    boolean mHaveGottenCallbacks;
    long mLastLockTime;
    protected int mRequestedFormat;
    int mRequestedHeight;
    int mRequestedType;
    int mRequestedWidth;
    public Surface mSurface;
    final Rect mSurfaceFrame = new Rect();
    public final ReentrantLock mSurfaceLock = new ReentrantLock();
    Rect mTmpDirty;
    int mType;
}
