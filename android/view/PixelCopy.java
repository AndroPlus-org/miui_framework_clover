// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Handler;

// Referenced classes of package android.view:
//            Surface, ThreadedRenderer, SurfaceView, SurfaceHolder, 
//            Window, View, ViewRootImpl

public final class PixelCopy
{
    public static interface OnPixelCopyFinishedListener
    {

        public abstract void onPixelCopyFinished(int i);
    }


    private PixelCopy()
    {
    }

    public static void request(Surface surface, Bitmap bitmap, OnPixelCopyFinishedListener onpixelcopyfinishedlistener, Handler handler)
    {
        request(surface, null, bitmap, onpixelcopyfinishedlistener, handler);
    }

    public static void request(Surface surface, Rect rect, Bitmap bitmap, OnPixelCopyFinishedListener onpixelcopyfinishedlistener, Handler handler)
    {
        validateBitmapDest(bitmap);
        if(!surface.isValid())
            throw new IllegalArgumentException("Surface isn't valid, source.isValid() == false");
        if(rect != null && rect.isEmpty())
        {
            throw new IllegalArgumentException("sourceRect is empty");
        } else
        {
            handler.post(new Runnable(onpixelcopyfinishedlistener, ThreadedRenderer.copySurfaceInto(surface, rect, bitmap)) {

                public void run()
                {
                    listener.onPixelCopyFinished(result);
                }

                final OnPixelCopyFinishedListener val$listener;
                final int val$result;

            
            {
                listener = onpixelcopyfinishedlistener;
                result = i;
                super();
            }
            }
);
            return;
        }
    }

    public static void request(SurfaceView surfaceview, Bitmap bitmap, OnPixelCopyFinishedListener onpixelcopyfinishedlistener, Handler handler)
    {
        request(surfaceview.getHolder().getSurface(), bitmap, onpixelcopyfinishedlistener, handler);
    }

    public static void request(SurfaceView surfaceview, Rect rect, Bitmap bitmap, OnPixelCopyFinishedListener onpixelcopyfinishedlistener, Handler handler)
    {
        request(surfaceview.getHolder().getSurface(), rect, bitmap, onpixelcopyfinishedlistener, handler);
    }

    public static void request(Window window, Bitmap bitmap, OnPixelCopyFinishedListener onpixelcopyfinishedlistener, Handler handler)
    {
        request(window, null, bitmap, onpixelcopyfinishedlistener, handler);
    }

    public static void request(Window window, Rect rect, Bitmap bitmap, OnPixelCopyFinishedListener onpixelcopyfinishedlistener, Handler handler)
    {
        validateBitmapDest(bitmap);
        if(window == null)
            throw new IllegalArgumentException("source is null");
        if(window.peekDecorView() == null)
            throw new IllegalArgumentException("Only able to copy windows with decor views");
        Surface surface = null;
        if(window.peekDecorView().getViewRootImpl() != null)
            surface = window.peekDecorView().getViewRootImpl().mSurface;
        if(surface == null || surface.isValid() ^ true)
        {
            throw new IllegalArgumentException("Window doesn't have a backing surface!");
        } else
        {
            request(surface, rect, bitmap, onpixelcopyfinishedlistener, handler);
            return;
        }
    }

    private static void validateBitmapDest(Bitmap bitmap)
    {
        if(bitmap == null)
            throw new IllegalArgumentException("Bitmap cannot be null");
        if(bitmap.isRecycled())
            throw new IllegalArgumentException("Bitmap is recycled");
        if(!bitmap.isMutable())
            throw new IllegalArgumentException("Bitmap is immutable");
        else
            return;
    }

    public static final int ERROR_DESTINATION_INVALID = 5;
    public static final int ERROR_SOURCE_INVALID = 4;
    public static final int ERROR_SOURCE_NO_DATA = 3;
    public static final int ERROR_TIMEOUT = 2;
    public static final int ERROR_UNKNOWN = 1;
    public static final int SUCCESS = 0;
}
