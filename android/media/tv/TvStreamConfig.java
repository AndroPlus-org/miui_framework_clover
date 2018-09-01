// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.tv;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class TvStreamConfig
    implements Parcelable
{
    public static final class Builder
    {

        public TvStreamConfig build()
        {
            while(mStreamId == null || mType == null || mMaxWidth == null || mMaxHeight == null || mGeneration == null) 
                throw new UnsupportedOperationException();
            TvStreamConfig tvstreamconfig = new TvStreamConfig(null);
            TvStreamConfig._2D_set3(tvstreamconfig, mStreamId.intValue());
            TvStreamConfig._2D_set4(tvstreamconfig, mType.intValue());
            TvStreamConfig._2D_set2(tvstreamconfig, mMaxWidth.intValue());
            TvStreamConfig._2D_set1(tvstreamconfig, mMaxHeight.intValue());
            TvStreamConfig._2D_set0(tvstreamconfig, mGeneration.intValue());
            return tvstreamconfig;
        }

        public Builder generation(int i)
        {
            mGeneration = Integer.valueOf(i);
            return this;
        }

        public Builder maxHeight(int i)
        {
            mMaxHeight = Integer.valueOf(i);
            return this;
        }

        public Builder maxWidth(int i)
        {
            mMaxWidth = Integer.valueOf(i);
            return this;
        }

        public Builder streamId(int i)
        {
            mStreamId = Integer.valueOf(i);
            return this;
        }

        public Builder type(int i)
        {
            mType = Integer.valueOf(i);
            return this;
        }

        private Integer mGeneration;
        private Integer mMaxHeight;
        private Integer mMaxWidth;
        private Integer mStreamId;
        private Integer mType;

        public Builder()
        {
        }
    }


    static int _2D_set0(TvStreamConfig tvstreamconfig, int i)
    {
        tvstreamconfig.mGeneration = i;
        return i;
    }

    static int _2D_set1(TvStreamConfig tvstreamconfig, int i)
    {
        tvstreamconfig.mMaxHeight = i;
        return i;
    }

    static int _2D_set2(TvStreamConfig tvstreamconfig, int i)
    {
        tvstreamconfig.mMaxWidth = i;
        return i;
    }

    static int _2D_set3(TvStreamConfig tvstreamconfig, int i)
    {
        tvstreamconfig.mStreamId = i;
        return i;
    }

    static int _2D_set4(TvStreamConfig tvstreamconfig, int i)
    {
        tvstreamconfig.mType = i;
        return i;
    }

    private TvStreamConfig()
    {
    }

    TvStreamConfig(TvStreamConfig tvstreamconfig)
    {
        this();
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj == null)
            return false;
        if(!(obj instanceof TvStreamConfig))
            return false;
        obj = (TvStreamConfig)obj;
        boolean flag1 = flag;
        if(((TvStreamConfig) (obj)).mGeneration == mGeneration)
        {
            flag1 = flag;
            if(((TvStreamConfig) (obj)).mStreamId == mStreamId)
            {
                flag1 = flag;
                if(((TvStreamConfig) (obj)).mType == mType)
                {
                    flag1 = flag;
                    if(((TvStreamConfig) (obj)).mMaxWidth == mMaxWidth)
                    {
                        flag1 = flag;
                        if(((TvStreamConfig) (obj)).mMaxHeight == mMaxHeight)
                            flag1 = true;
                    }
                }
            }
        }
        return flag1;
    }

    public int getGeneration()
    {
        return mGeneration;
    }

    public int getMaxHeight()
    {
        return mMaxHeight;
    }

    public int getMaxWidth()
    {
        return mMaxWidth;
    }

    public int getStreamId()
    {
        return mStreamId;
    }

    public int getType()
    {
        return mType;
    }

    public String toString()
    {
        return (new StringBuilder()).append("TvStreamConfig {mStreamId=").append(mStreamId).append(";").append("mType=").append(mType).append(";mGeneration=").append(mGeneration).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mStreamId);
        parcel.writeInt(mType);
        parcel.writeInt(mMaxWidth);
        parcel.writeInt(mMaxHeight);
        parcel.writeInt(mGeneration);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public TvStreamConfig createFromParcel(Parcel parcel)
        {
            try
            {
                Builder builder = JVM INSTR new #20  <Class TvStreamConfig$Builder>;
                builder.Builder();
                parcel = builder.streamId(parcel.readInt()).type(parcel.readInt()).maxWidth(parcel.readInt()).maxHeight(parcel.readInt()).generation(parcel.readInt()).build();
            }
            // Misplaced declaration of an exception variable
            catch(Parcel parcel)
            {
                Log.e(TvStreamConfig.TAG, "Exception creating TvStreamConfig from parcel", parcel);
                return null;
            }
            return parcel;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public TvStreamConfig[] newArray(int i)
        {
            return new TvStreamConfig[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int STREAM_TYPE_BUFFER_PRODUCER = 2;
    public static final int STREAM_TYPE_INDEPENDENT_VIDEO_SOURCE = 1;
    static final String TAG = android/media/tv/TvStreamConfig.getSimpleName();
    private int mGeneration;
    private int mMaxHeight;
    private int mMaxWidth;
    private int mStreamId;
    private int mType;

}
