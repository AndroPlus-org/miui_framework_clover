// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.Parcel;
import android.os.Parcelable;

public final class BufferingParams
    implements Parcelable
{
    public static class Builder
    {

        private boolean isSizeBasedMode(int i)
        {
            boolean flag = true;
            boolean flag1 = flag;
            if(i != 2)
                if(i == 3)
                    flag1 = flag;
                else
                    flag1 = false;
            return flag1;
        }

        private boolean isTimeBasedMode(int i)
        {
            boolean flag = true;
            boolean flag1 = flag;
            if(i != 1)
                if(i == 3)
                    flag1 = flag;
                else
                    flag1 = false;
            return flag1;
        }

        public BufferingParams build()
        {
            if(isTimeBasedMode(mRebufferingMode) && mRebufferingWatermarkLowMs > mRebufferingWatermarkHighMs)
                throw new IllegalStateException((new StringBuilder()).append("Illegal watermark:").append(mRebufferingWatermarkLowMs).append(" : ").append(mRebufferingWatermarkHighMs).toString());
            if(isSizeBasedMode(mRebufferingMode) && mRebufferingWatermarkLowKB > mRebufferingWatermarkHighKB)
            {
                throw new IllegalStateException((new StringBuilder()).append("Illegal watermark:").append(mRebufferingWatermarkLowKB).append(" : ").append(mRebufferingWatermarkHighKB).toString());
            } else
            {
                BufferingParams bufferingparams = new BufferingParams(null);
                BufferingParams._2D_set0(bufferingparams, mInitialBufferingMode);
                BufferingParams._2D_set3(bufferingparams, mRebufferingMode);
                BufferingParams._2D_set2(bufferingparams, mInitialWatermarkMs);
                BufferingParams._2D_set1(bufferingparams, mInitialWatermarkKB);
                BufferingParams._2D_set7(bufferingparams, mRebufferingWatermarkLowMs);
                BufferingParams._2D_set5(bufferingparams, mRebufferingWatermarkHighMs);
                BufferingParams._2D_set6(bufferingparams, mRebufferingWatermarkLowKB);
                BufferingParams._2D_set4(bufferingparams, mRebufferingWatermarkHighKB);
                return bufferingparams;
            }
        }

        public Builder setInitialBufferingMode(int i)
        {
            switch(i)
            {
            default:
                throw new IllegalArgumentException((new StringBuilder()).append("Illegal buffering mode ").append(i).toString());

            case 0: // '\0'
            case 1: // '\001'
            case 2: // '\002'
            case 3: // '\003'
                mInitialBufferingMode = i;
                break;
            }
            return this;
        }

        public Builder setInitialBufferingWatermarkKB(int i)
        {
            mInitialWatermarkKB = i;
            return this;
        }

        public Builder setInitialBufferingWatermarkMs(int i)
        {
            mInitialWatermarkMs = i;
            return this;
        }

        public Builder setRebufferingMode(int i)
        {
            switch(i)
            {
            default:
                throw new IllegalArgumentException((new StringBuilder()).append("Illegal buffering mode ").append(i).toString());

            case 0: // '\0'
            case 1: // '\001'
            case 2: // '\002'
            case 3: // '\003'
                mRebufferingMode = i;
                break;
            }
            return this;
        }

        public Builder setRebufferingWatermarkHighKB(int i)
        {
            mRebufferingWatermarkHighKB = i;
            return this;
        }

        public Builder setRebufferingWatermarkHighMs(int i)
        {
            mRebufferingWatermarkHighMs = i;
            return this;
        }

        public Builder setRebufferingWatermarkLowKB(int i)
        {
            mRebufferingWatermarkLowKB = i;
            return this;
        }

        public Builder setRebufferingWatermarkLowMs(int i)
        {
            mRebufferingWatermarkLowMs = i;
            return this;
        }

        public Builder setRebufferingWatermarksKB(int i, int j)
        {
            mRebufferingWatermarkLowKB = i;
            mRebufferingWatermarkHighKB = j;
            return this;
        }

        public Builder setRebufferingWatermarksMs(int i, int j)
        {
            mRebufferingWatermarkLowMs = i;
            mRebufferingWatermarkHighMs = j;
            return this;
        }

        private int mInitialBufferingMode;
        private int mInitialWatermarkKB;
        private int mInitialWatermarkMs;
        private int mRebufferingMode;
        private int mRebufferingWatermarkHighKB;
        private int mRebufferingWatermarkHighMs;
        private int mRebufferingWatermarkLowKB;
        private int mRebufferingWatermarkLowMs;

        public Builder()
        {
            mInitialBufferingMode = 0;
            mRebufferingMode = 0;
            mInitialWatermarkMs = -1;
            mInitialWatermarkKB = -1;
            mRebufferingWatermarkLowMs = -1;
            mRebufferingWatermarkHighMs = -1;
            mRebufferingWatermarkLowKB = -1;
            mRebufferingWatermarkHighKB = -1;
        }

        public Builder(BufferingParams bufferingparams)
        {
            mInitialBufferingMode = 0;
            mRebufferingMode = 0;
            mInitialWatermarkMs = -1;
            mInitialWatermarkKB = -1;
            mRebufferingWatermarkLowMs = -1;
            mRebufferingWatermarkHighMs = -1;
            mRebufferingWatermarkLowKB = -1;
            mRebufferingWatermarkHighKB = -1;
            mInitialBufferingMode = BufferingParams._2D_get0(bufferingparams);
            mRebufferingMode = BufferingParams._2D_get3(bufferingparams);
            mInitialWatermarkMs = BufferingParams._2D_get2(bufferingparams);
            mInitialWatermarkKB = BufferingParams._2D_get1(bufferingparams);
            mRebufferingWatermarkLowMs = BufferingParams._2D_get7(bufferingparams);
            mRebufferingWatermarkHighMs = BufferingParams._2D_get5(bufferingparams);
            mRebufferingWatermarkLowKB = BufferingParams._2D_get6(bufferingparams);
            mRebufferingWatermarkHighKB = BufferingParams._2D_get4(bufferingparams);
        }
    }


    static int _2D_get0(BufferingParams bufferingparams)
    {
        return bufferingparams.mInitialBufferingMode;
    }

    static int _2D_get1(BufferingParams bufferingparams)
    {
        return bufferingparams.mInitialWatermarkKB;
    }

    static int _2D_get2(BufferingParams bufferingparams)
    {
        return bufferingparams.mInitialWatermarkMs;
    }

    static int _2D_get3(BufferingParams bufferingparams)
    {
        return bufferingparams.mRebufferingMode;
    }

    static int _2D_get4(BufferingParams bufferingparams)
    {
        return bufferingparams.mRebufferingWatermarkHighKB;
    }

    static int _2D_get5(BufferingParams bufferingparams)
    {
        return bufferingparams.mRebufferingWatermarkHighMs;
    }

    static int _2D_get6(BufferingParams bufferingparams)
    {
        return bufferingparams.mRebufferingWatermarkLowKB;
    }

    static int _2D_get7(BufferingParams bufferingparams)
    {
        return bufferingparams.mRebufferingWatermarkLowMs;
    }

    static int _2D_set0(BufferingParams bufferingparams, int i)
    {
        bufferingparams.mInitialBufferingMode = i;
        return i;
    }

    static int _2D_set1(BufferingParams bufferingparams, int i)
    {
        bufferingparams.mInitialWatermarkKB = i;
        return i;
    }

    static int _2D_set2(BufferingParams bufferingparams, int i)
    {
        bufferingparams.mInitialWatermarkMs = i;
        return i;
    }

    static int _2D_set3(BufferingParams bufferingparams, int i)
    {
        bufferingparams.mRebufferingMode = i;
        return i;
    }

    static int _2D_set4(BufferingParams bufferingparams, int i)
    {
        bufferingparams.mRebufferingWatermarkHighKB = i;
        return i;
    }

    static int _2D_set5(BufferingParams bufferingparams, int i)
    {
        bufferingparams.mRebufferingWatermarkHighMs = i;
        return i;
    }

    static int _2D_set6(BufferingParams bufferingparams, int i)
    {
        bufferingparams.mRebufferingWatermarkLowKB = i;
        return i;
    }

    static int _2D_set7(BufferingParams bufferingparams, int i)
    {
        bufferingparams.mRebufferingWatermarkLowMs = i;
        return i;
    }

    private BufferingParams()
    {
        mInitialBufferingMode = 0;
        mRebufferingMode = 0;
        mInitialWatermarkMs = -1;
        mInitialWatermarkKB = -1;
        mRebufferingWatermarkLowMs = -1;
        mRebufferingWatermarkHighMs = -1;
        mRebufferingWatermarkLowKB = -1;
        mRebufferingWatermarkHighKB = -1;
    }

    BufferingParams(BufferingParams bufferingparams)
    {
        this();
    }

    private BufferingParams(Parcel parcel)
    {
        mInitialBufferingMode = 0;
        mRebufferingMode = 0;
        mInitialWatermarkMs = -1;
        mInitialWatermarkKB = -1;
        mRebufferingWatermarkLowMs = -1;
        mRebufferingWatermarkHighMs = -1;
        mRebufferingWatermarkLowKB = -1;
        mRebufferingWatermarkHighKB = -1;
        mInitialBufferingMode = parcel.readInt();
        mRebufferingMode = parcel.readInt();
        mInitialWatermarkMs = parcel.readInt();
        mInitialWatermarkKB = parcel.readInt();
        mRebufferingWatermarkLowMs = parcel.readInt();
        mRebufferingWatermarkHighMs = parcel.readInt();
        mRebufferingWatermarkLowKB = parcel.readInt();
        mRebufferingWatermarkHighKB = parcel.readInt();
    }

    BufferingParams(Parcel parcel, BufferingParams bufferingparams)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public int getInitialBufferingMode()
    {
        return mInitialBufferingMode;
    }

    public int getInitialBufferingWatermarkKB()
    {
        return mInitialWatermarkKB;
    }

    public int getInitialBufferingWatermarkMs()
    {
        return mInitialWatermarkMs;
    }

    public int getRebufferingMode()
    {
        return mRebufferingMode;
    }

    public int getRebufferingWatermarkHighKB()
    {
        return mRebufferingWatermarkHighKB;
    }

    public int getRebufferingWatermarkHighMs()
    {
        return mRebufferingWatermarkHighMs;
    }

    public int getRebufferingWatermarkLowKB()
    {
        return mRebufferingWatermarkLowKB;
    }

    public int getRebufferingWatermarkLowMs()
    {
        return mRebufferingWatermarkLowMs;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mInitialBufferingMode);
        parcel.writeInt(mRebufferingMode);
        parcel.writeInt(mInitialWatermarkMs);
        parcel.writeInt(mInitialWatermarkKB);
        parcel.writeInt(mRebufferingWatermarkLowMs);
        parcel.writeInt(mRebufferingWatermarkHighMs);
        parcel.writeInt(mRebufferingWatermarkLowKB);
        parcel.writeInt(mRebufferingWatermarkHighKB);
    }

    public static final int BUFFERING_MODE_NONE = 0;
    public static final int BUFFERING_MODE_SIZE_ONLY = 2;
    public static final int BUFFERING_MODE_TIME_ONLY = 1;
    public static final int BUFFERING_MODE_TIME_THEN_SIZE = 3;
    private static final int BUFFERING_NO_WATERMARK = -1;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public BufferingParams createFromParcel(Parcel parcel)
        {
            return new BufferingParams(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public BufferingParams[] newArray(int i)
        {
            return new BufferingParams[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private int mInitialBufferingMode;
    private int mInitialWatermarkKB;
    private int mInitialWatermarkMs;
    private int mRebufferingMode;
    private int mRebufferingWatermarkHighKB;
    private int mRebufferingWatermarkHighMs;
    private int mRebufferingWatermarkLowKB;
    private int mRebufferingWatermarkLowMs;

}
