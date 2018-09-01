// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.display;

import android.view.Display;
import android.view.Surface;

// Referenced classes of package android.hardware.display:
//            DisplayManagerGlobal, IVirtualDisplayCallback

public final class VirtualDisplay
{
    public static abstract class Callback
    {

        public void onPaused()
        {
        }

        public void onResumed()
        {
        }

        public void onStopped()
        {
        }

        public Callback()
        {
        }
    }


    VirtualDisplay(DisplayManagerGlobal displaymanagerglobal, Display display, IVirtualDisplayCallback ivirtualdisplaycallback, Surface surface)
    {
        mGlobal = displaymanagerglobal;
        mDisplay = display;
        mToken = ivirtualdisplaycallback;
        mSurface = surface;
    }

    public Display getDisplay()
    {
        return mDisplay;
    }

    public Surface getSurface()
    {
        return mSurface;
    }

    public void release()
    {
        if(mToken != null)
        {
            mGlobal.releaseVirtualDisplay(mToken);
            mToken = null;
        }
    }

    public void resize(int i, int j, int k)
    {
        mGlobal.resizeVirtualDisplay(mToken, i, j, k);
    }

    public void setSurface(Surface surface)
    {
        if(mSurface != surface)
        {
            mGlobal.setVirtualDisplaySurface(mToken, surface);
            mSurface = surface;
        }
    }

    public String toString()
    {
        return (new StringBuilder()).append("VirtualDisplay{display=").append(mDisplay).append(", token=").append(mToken).append(", surface=").append(mSurface).append("}").toString();
    }

    private final Display mDisplay;
    private final DisplayManagerGlobal mGlobal;
    private Surface mSurface;
    private IVirtualDisplayCallback mToken;
}
