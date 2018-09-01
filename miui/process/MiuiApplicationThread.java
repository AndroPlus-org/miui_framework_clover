// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.process;


public class MiuiApplicationThread extends IMiuiApplicationThread.Stub
{

    public MiuiApplicationThread()
    {
    }

    public boolean longScreenshot(int i)
    {
        if(mContentPort == null)
            mContentPort = new miui.util.LongScreenshotUtils.ContentPort();
        return mContentPort.longScreenshot(i);
    }

    private miui.util.LongScreenshotUtils.ContentPort mContentPort;
}
