// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;


final class FrameInfo
{

    FrameInfo()
    {
        mFrameInfo = new long[9];
    }

    public void addFlags(long l)
    {
        long al[] = mFrameInfo;
        al[0] = al[0] | l;
    }

    public void markAnimationsStart()
    {
        mFrameInfo[6] = System.nanoTime();
    }

    public void markDrawStart()
    {
        mFrameInfo[8] = System.nanoTime();
    }

    public void markInputHandlingStart()
    {
        mFrameInfo[5] = System.nanoTime();
    }

    public void markPerformTraversalsStart()
    {
        mFrameInfo[7] = System.nanoTime();
    }

    public void setVsync(long l, long l1)
    {
        mFrameInfo[1] = l;
        mFrameInfo[2] = l1;
        mFrameInfo[3] = 0x7fffffffffffffffL;
        mFrameInfo[4] = 0L;
        mFrameInfo[0] = 0L;
    }

    public void updateInputEventTime(long l, long l1)
    {
        if(l1 < mFrameInfo[3])
            mFrameInfo[3] = l1;
        if(l > mFrameInfo[4])
            mFrameInfo[4] = l;
    }

    private static final int ANIMATION_START = 6;
    private static final int DRAW_START = 8;
    private static final int FLAGS = 0;
    public static final long FLAG_WINDOW_LAYOUT_CHANGED = 1L;
    private static final int HANDLE_INPUT_START = 5;
    private static final int INTENDED_VSYNC = 1;
    private static final int NEWEST_INPUT_EVENT = 4;
    private static final int OLDEST_INPUT_EVENT = 3;
    private static final int PERFORM_TRAVERSALS_START = 7;
    private static final int VSYNC = 2;
    long mFrameInfo[];
}
