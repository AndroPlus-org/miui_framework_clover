// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.hdmi;


public abstract class HdmiRecordListener
{
    public static class TimerStatusData
    {

        private static int bcdByteToInt(byte byte0)
        {
            return (byte0 >> 4 & 0xf) * 10 + byte0 & 0xf;
        }

        static TimerStatusData parseFrom(int i)
        {
            boolean flag = true;
            TimerStatusData timerstatusdata = new TimerStatusData();
            boolean flag1;
            if((i >> 31 & 1) != 0)
                flag1 = true;
            else
                flag1 = false;
            timerstatusdata.mOverlapped = flag1;
            timerstatusdata.mMediaInfo = i >> 29 & 3;
            if((i >> 28 & 1) != 0)
                flag1 = flag;
            else
                flag1 = false;
            timerstatusdata.mProgrammed = flag1;
            if(timerstatusdata.mProgrammed)
            {
                timerstatusdata.mProgrammedInfo = i >> 24 & 0xf;
                timerstatusdata.mDurationHour = bcdByteToInt((byte)(i >> 16 & 0xff));
                timerstatusdata.mDurationMinute = bcdByteToInt((byte)(i >> 8 & 0xff));
            } else
            {
                timerstatusdata.mNotProgrammedError = i >> 24 & 0xf;
                timerstatusdata.mDurationHour = bcdByteToInt((byte)(i >> 16 & 0xff));
                timerstatusdata.mDurationMinute = bcdByteToInt((byte)(i >> 8 & 0xff));
            }
            timerstatusdata.mExtraError = i & 0xff;
            return timerstatusdata;
        }

        public int getDurationHour()
        {
            return mDurationHour;
        }

        public int getDurationMinute()
        {
            return mDurationMinute;
        }

        public int getExtraError()
        {
            return mExtraError;
        }

        public int getMediaInfo()
        {
            return mMediaInfo;
        }

        public int getNotProgammedError()
        {
            if(isProgrammed())
                throw new IllegalStateException("Has no not-programmed error. Call getProgrammedInfo() instead.");
            else
                return mNotProgrammedError;
        }

        public int getProgrammedInfo()
        {
            if(!isProgrammed())
                throw new IllegalStateException("No programmed info. Call getNotProgammedError() instead.");
            else
                return mProgrammedInfo;
        }

        public boolean isOverlapped()
        {
            return mOverlapped;
        }

        public boolean isProgrammed()
        {
            return mProgrammed;
        }

        private int mDurationHour;
        private int mDurationMinute;
        private int mExtraError;
        private int mMediaInfo;
        private int mNotProgrammedError;
        private boolean mOverlapped;
        private boolean mProgrammed;
        private int mProgrammedInfo;

        private TimerStatusData()
        {
        }
    }


    public HdmiRecordListener()
    {
    }

    public void onClearTimerRecordingResult(int i, int j)
    {
    }

    public void onOneTouchRecordResult(int i, int j)
    {
    }

    public abstract HdmiRecordSources.RecordSource onOneTouchRecordSourceRequested(int i);

    public void onTimerRecordingResult(int i, TimerStatusData timerstatusdata)
    {
    }
}
