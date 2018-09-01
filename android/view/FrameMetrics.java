// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;


public final class FrameMetrics
{

    FrameMetrics()
    {
        mTimingData = new long[16];
    }

    public FrameMetrics(FrameMetrics framemetrics)
    {
        mTimingData = new long[16];
        System.arraycopy(framemetrics.mTimingData, 0, mTimingData, 0, mTimingData.length);
    }

    public long getMetric(int i)
    {
        boolean flag = true;
        if(i < 0 || i > 11)
            return -1L;
        if(mTimingData == null)
            return -1L;
        if(i == 9)
        {
            if((mTimingData[0] & 1L) != 0L)
                i = ((flag) ? 1 : 0);
            else
                i = 0;
            return (long)i;
        }
        if(i == 10)
            return mTimingData[1];
        if(i == 11)
        {
            return mTimingData[2];
        } else
        {
            i *= 2;
            return mTimingData[DURATIONS[i + 1]] - mTimingData[DURATIONS[i]];
        }
    }

    public static final int ANIMATION_DURATION = 2;
    public static final int COMMAND_ISSUE_DURATION = 6;
    public static final int DRAW_DURATION = 4;
    private static final int DURATIONS[] = {
        1, 5, 5, 6, 6, 7, 7, 8, 8, 9, 
        10, 11, 11, 12, 12, 13, 1, 13
    };
    public static final int FIRST_DRAW_FRAME = 9;
    private static final int FRAME_INFO_FLAG_FIRST_DRAW = 1;
    public static final int INPUT_HANDLING_DURATION = 1;
    public static final int INTENDED_VSYNC_TIMESTAMP = 10;
    public static final int LAYOUT_MEASURE_DURATION = 3;
    public static final int SWAP_BUFFERS_DURATION = 7;
    public static final int SYNC_DURATION = 5;
    public static final int TOTAL_DURATION = 8;
    public static final int UNKNOWN_DELAY_DURATION = 0;
    public static final int VSYNC_TIMESTAMP = 11;
    final long mTimingData[];

}
