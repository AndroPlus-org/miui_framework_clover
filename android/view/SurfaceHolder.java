// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.graphics.Canvas;
import android.graphics.Rect;

// Referenced classes of package android.view:
//            Surface

public interface SurfaceHolder
{
    public static class BadSurfaceTypeException extends RuntimeException
    {

        public BadSurfaceTypeException()
        {
        }

        public BadSurfaceTypeException(String s)
        {
            super(s);
        }
    }

    public static interface Callback
    {

        public abstract void surfaceChanged(SurfaceHolder surfaceholder, int i, int j, int k);

        public abstract void surfaceCreated(SurfaceHolder surfaceholder);

        public abstract void surfaceDestroyed(SurfaceHolder surfaceholder);
    }

    public static interface Callback2
        extends Callback
    {

        public abstract void surfaceRedrawNeeded(SurfaceHolder surfaceholder);

        public void surfaceRedrawNeededAsync(SurfaceHolder surfaceholder, Runnable runnable)
        {
            surfaceRedrawNeeded(surfaceholder);
            runnable.run();
        }
    }


    public abstract void addCallback(Callback callback);

    public abstract Surface getSurface();

    public abstract Rect getSurfaceFrame();

    public abstract boolean isCreating();

    public abstract Canvas lockCanvas();

    public abstract Canvas lockCanvas(Rect rect);

    public Canvas lockHardwareCanvas()
    {
        throw new IllegalStateException("This SurfaceHolder doesn't support lockHardwareCanvas");
    }

    public abstract void removeCallback(Callback callback);

    public abstract void setFixedSize(int i, int j);

    public abstract void setFormat(int i);

    public abstract void setKeepScreenOn(boolean flag);

    public abstract void setSizeFromLayout();

    public abstract void setType(int i);

    public abstract void unlockCanvasAndPost(Canvas canvas);

    public static final int SURFACE_TYPE_GPU = 2;
    public static final int SURFACE_TYPE_HARDWARE = 1;
    public static final int SURFACE_TYPE_NORMAL = 0;
    public static final int SURFACE_TYPE_PUSH_BUFFERS = 3;
}
