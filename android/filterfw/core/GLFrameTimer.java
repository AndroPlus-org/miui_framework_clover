// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;


// Referenced classes of package android.filterfw.core:
//            StopWatchMap

class GLFrameTimer
{

    GLFrameTimer()
    {
    }

    public static StopWatchMap get()
    {
        if(mTimer == null)
            mTimer = new StopWatchMap();
        return mTimer;
    }

    private static StopWatchMap mTimer = null;

}
