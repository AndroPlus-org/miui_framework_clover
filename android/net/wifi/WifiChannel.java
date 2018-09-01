// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi;

import android.os.Parcel;
import android.os.Parcelable;

public class WifiChannel
    implements Parcelable
{

    public WifiChannel()
    {
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean isValid()
    {
        if(freqMHz < 2412 || freqMHz > 5825)
            return false;
        return channelNum >= 1 && channelNum <= 196;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(freqMHz);
        parcel.writeInt(channelNum);
        if(isDFS)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WifiChannel createFromParcel(Parcel parcel)
        {
            boolean flag = false;
            WifiChannel wifichannel = new WifiChannel();
            wifichannel.freqMHz = parcel.readInt();
            wifichannel.channelNum = parcel.readInt();
            if(parcel.readInt() != 0)
                flag = true;
            wifichannel.isDFS = flag;
            return wifichannel;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WifiChannel[] newArray(int i)
        {
            return new WifiChannel[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int MAX_CHANNEL_NUM = 196;
    private static final int MAX_FREQ_MHZ = 5825;
    private static final int MIN_CHANNEL_NUM = 1;
    private static final int MIN_FREQ_MHZ = 2412;
    public int channelNum;
    public int freqMHz;
    public boolean isDFS;

}
