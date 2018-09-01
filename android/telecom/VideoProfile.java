// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;

import android.os.Parcel;
import android.os.Parcelable;

public class VideoProfile
    implements Parcelable
{
    public static final class CameraCapabilities
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public int getHeight()
        {
            return mHeight;
        }

        public float getMaxZoom()
        {
            return mMaxZoom;
        }

        public int getWidth()
        {
            return mWidth;
        }

        public boolean isZoomSupported()
        {
            return mZoomSupported;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(getWidth());
            parcel.writeInt(getHeight());
            if(isZoomSupported())
                i = 1;
            else
                i = 0;
            parcel.writeByte((byte)i);
            parcel.writeFloat(getMaxZoom());
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public CameraCapabilities createFromParcel(Parcel parcel)
            {
                int i = parcel.readInt();
                int j = parcel.readInt();
                boolean flag;
                if(parcel.readByte() != 0)
                    flag = true;
                else
                    flag = false;
                return new CameraCapabilities(i, j, flag, parcel.readFloat());
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public CameraCapabilities[] newArray(int i)
            {
                return new CameraCapabilities[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private final int mHeight;
        private final float mMaxZoom;
        private final int mWidth;
        private final boolean mZoomSupported;


        public CameraCapabilities(int i, int j)
        {
            this(i, j, false, 1.0F);
        }

        public CameraCapabilities(int i, int j, boolean flag, float f)
        {
            mWidth = i;
            mHeight = j;
            mZoomSupported = flag;
            mMaxZoom = f;
        }
    }


    public VideoProfile(int i)
    {
        this(i, 4);
    }

    public VideoProfile(int i, int j)
    {
        mVideoState = i;
        mQuality = j;
    }

    private static boolean hasState(int i, int j)
    {
        boolean flag;
        if((i & j) == j)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean isAudioOnly(int i)
    {
        boolean flag;
        if(!hasState(i, 1))
            flag = hasState(i, 2) ^ true;
        else
            flag = false;
        return flag;
    }

    public static boolean isBidirectional(int i)
    {
        return hasState(i, 3);
    }

    public static boolean isPaused(int i)
    {
        return hasState(i, 4);
    }

    public static boolean isReceptionEnabled(int i)
    {
        return hasState(i, 2);
    }

    public static boolean isTransmissionEnabled(int i)
    {
        return hasState(i, 1);
    }

    public static boolean isVideo(int i)
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(!hasState(i, 1))
        {
            flag1 = flag;
            if(!hasState(i, 2))
                flag1 = hasState(i, 3);
        }
        return flag1;
    }

    public static String videoStateToString(int i)
    {
        StringBuilder stringbuilder;
        stringbuilder = new StringBuilder();
        stringbuilder.append("Audio");
        if(i != 0) goto _L2; else goto _L1
_L1:
        stringbuilder.append(" Only");
_L4:
        return stringbuilder.toString();
_L2:
        if(isTransmissionEnabled(i))
            stringbuilder.append(" Tx");
        if(isReceptionEnabled(i))
            stringbuilder.append(" Rx");
        if(isPaused(i))
            stringbuilder.append(" Pause");
        if(true) goto _L4; else goto _L3
_L3:
    }

    public int describeContents()
    {
        return 0;
    }

    public int getQuality()
    {
        return mQuality;
    }

    public int getVideoState()
    {
        return mVideoState;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("[VideoProfile videoState = ");
        stringbuilder.append(videoStateToString(mVideoState));
        stringbuilder.append(" videoQuality = ");
        stringbuilder.append(mQuality);
        stringbuilder.append("]");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mVideoState);
        parcel.writeInt(mQuality);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public VideoProfile createFromParcel(Parcel parcel)
        {
            int i = parcel.readInt();
            int j = parcel.readInt();
            android/telecom/VideoProfile.getClassLoader();
            return new VideoProfile(i, j);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public VideoProfile[] newArray(int i)
        {
            return new VideoProfile[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int QUALITY_DEFAULT = 4;
    public static final int QUALITY_HIGH = 1;
    public static final int QUALITY_LOW = 3;
    public static final int QUALITY_MEDIUM = 2;
    public static final int QUALITY_UNKNOWN = 0;
    public static final int STATE_AUDIO_ONLY = 0;
    public static final int STATE_BIDIRECTIONAL = 3;
    public static final int STATE_PAUSED = 4;
    public static final int STATE_RX_ENABLED = 2;
    public static final int STATE_TX_ENABLED = 1;
    private final int mQuality;
    private final int mVideoState;

}
