// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.tv;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public final class TvInputHardwareInfo
    implements Parcelable
{
    public static final class Builder
    {

        public Builder audioAddress(String s)
        {
            mAudioAddress = s;
            return this;
        }

        public Builder audioType(int i)
        {
            mAudioType = i;
            return this;
        }

        public TvInputHardwareInfo build()
        {
            if(mDeviceId == null || mType == null)
                throw new UnsupportedOperationException();
            while(mType.intValue() == 9 && mHdmiPortId == null || mType.intValue() != 9 && mHdmiPortId != null) 
                throw new UnsupportedOperationException();
            TvInputHardwareInfo tvinputhardwareinfo = new TvInputHardwareInfo(null);
            TvInputHardwareInfo._2D_set3(tvinputhardwareinfo, mDeviceId.intValue());
            TvInputHardwareInfo._2D_set5(tvinputhardwareinfo, mType.intValue());
            TvInputHardwareInfo._2D_set1(tvinputhardwareinfo, mAudioType);
            if(TvInputHardwareInfo._2D_get0(tvinputhardwareinfo) != 0)
                TvInputHardwareInfo._2D_set0(tvinputhardwareinfo, mAudioAddress);
            if(mHdmiPortId != null)
                TvInputHardwareInfo._2D_set4(tvinputhardwareinfo, mHdmiPortId.intValue());
            TvInputHardwareInfo._2D_set2(tvinputhardwareinfo, mCableConnectionStatus.intValue());
            return tvinputhardwareinfo;
        }

        public Builder cableConnectionStatus(int i)
        {
            mCableConnectionStatus = Integer.valueOf(i);
            return this;
        }

        public Builder deviceId(int i)
        {
            mDeviceId = Integer.valueOf(i);
            return this;
        }

        public Builder hdmiPortId(int i)
        {
            mHdmiPortId = Integer.valueOf(i);
            return this;
        }

        public Builder type(int i)
        {
            mType = Integer.valueOf(i);
            return this;
        }

        private String mAudioAddress;
        private int mAudioType;
        private Integer mCableConnectionStatus;
        private Integer mDeviceId;
        private Integer mHdmiPortId;
        private Integer mType;

        public Builder()
        {
            mDeviceId = null;
            mType = null;
            mAudioType = 0;
            mAudioAddress = "";
            mHdmiPortId = null;
            mCableConnectionStatus = Integer.valueOf(0);
        }
    }


    static int _2D_get0(TvInputHardwareInfo tvinputhardwareinfo)
    {
        return tvinputhardwareinfo.mAudioType;
    }

    static String _2D_set0(TvInputHardwareInfo tvinputhardwareinfo, String s)
    {
        tvinputhardwareinfo.mAudioAddress = s;
        return s;
    }

    static int _2D_set1(TvInputHardwareInfo tvinputhardwareinfo, int i)
    {
        tvinputhardwareinfo.mAudioType = i;
        return i;
    }

    static int _2D_set2(TvInputHardwareInfo tvinputhardwareinfo, int i)
    {
        tvinputhardwareinfo.mCableConnectionStatus = i;
        return i;
    }

    static int _2D_set3(TvInputHardwareInfo tvinputhardwareinfo, int i)
    {
        tvinputhardwareinfo.mDeviceId = i;
        return i;
    }

    static int _2D_set4(TvInputHardwareInfo tvinputhardwareinfo, int i)
    {
        tvinputhardwareinfo.mHdmiPortId = i;
        return i;
    }

    static int _2D_set5(TvInputHardwareInfo tvinputhardwareinfo, int i)
    {
        tvinputhardwareinfo.mType = i;
        return i;
    }

    private TvInputHardwareInfo()
    {
    }

    TvInputHardwareInfo(TvInputHardwareInfo tvinputhardwareinfo)
    {
        this();
    }

    public int describeContents()
    {
        return 0;
    }

    public String getAudioAddress()
    {
        return mAudioAddress;
    }

    public int getAudioType()
    {
        return mAudioType;
    }

    public int getCableConnectionStatus()
    {
        return mCableConnectionStatus;
    }

    public int getDeviceId()
    {
        return mDeviceId;
    }

    public int getHdmiPortId()
    {
        if(mType != 9)
            throw new IllegalStateException();
        else
            return mHdmiPortId;
    }

    public int getType()
    {
        return mType;
    }

    public void readFromParcel(Parcel parcel)
    {
        mDeviceId = parcel.readInt();
        mType = parcel.readInt();
        mAudioType = parcel.readInt();
        mAudioAddress = parcel.readString();
        if(mType == 9)
            mHdmiPortId = parcel.readInt();
        mCableConnectionStatus = parcel.readInt();
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(128);
        stringbuilder.append("TvInputHardwareInfo {id=").append(mDeviceId);
        stringbuilder.append(", type=").append(mType);
        stringbuilder.append(", audio_type=").append(mAudioType);
        stringbuilder.append(", audio_addr=").append(mAudioAddress);
        if(mType == 9)
            stringbuilder.append(", hdmi_port=").append(mHdmiPortId);
        stringbuilder.append(", cable_connection_status=").append(mCableConnectionStatus);
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mDeviceId);
        parcel.writeInt(mType);
        parcel.writeInt(mAudioType);
        parcel.writeString(mAudioAddress);
        if(mType == 9)
            parcel.writeInt(mHdmiPortId);
        parcel.writeInt(mCableConnectionStatus);
    }

    public static final int CABLE_CONNECTION_STATUS_CONNECTED = 1;
    public static final int CABLE_CONNECTION_STATUS_DISCONNECTED = 2;
    public static final int CABLE_CONNECTION_STATUS_UNKNOWN = 0;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public TvInputHardwareInfo createFromParcel(Parcel parcel)
        {
            TvInputHardwareInfo tvinputhardwareinfo;
            try
            {
                tvinputhardwareinfo = JVM INSTR new #9   <Class TvInputHardwareInfo>;
                tvinputhardwareinfo.TvInputHardwareInfo(null);
                tvinputhardwareinfo.readFromParcel(parcel);
            }
            // Misplaced declaration of an exception variable
            catch(Parcel parcel)
            {
                Log.e("TvInputHardwareInfo", "Exception creating TvInputHardwareInfo from parcel", parcel);
                return null;
            }
            return tvinputhardwareinfo;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public TvInputHardwareInfo[] newArray(int i)
        {
            return new TvInputHardwareInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    static final String TAG = "TvInputHardwareInfo";
    public static final int TV_INPUT_TYPE_COMPONENT = 6;
    public static final int TV_INPUT_TYPE_COMPOSITE = 3;
    public static final int TV_INPUT_TYPE_DISPLAY_PORT = 10;
    public static final int TV_INPUT_TYPE_DVI = 8;
    public static final int TV_INPUT_TYPE_HDMI = 9;
    public static final int TV_INPUT_TYPE_OTHER_HARDWARE = 1;
    public static final int TV_INPUT_TYPE_SCART = 5;
    public static final int TV_INPUT_TYPE_SVIDEO = 4;
    public static final int TV_INPUT_TYPE_TUNER = 2;
    public static final int TV_INPUT_TYPE_VGA = 7;
    private String mAudioAddress;
    private int mAudioType;
    private int mCableConnectionStatus;
    private int mDeviceId;
    private int mHdmiPortId;
    private int mType;

}
