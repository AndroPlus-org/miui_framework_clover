// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

public class AudioRoutesInfo
    implements Parcelable
{

    public AudioRoutesInfo()
    {
        mainType = 0;
    }

    public AudioRoutesInfo(AudioRoutesInfo audioroutesinfo)
    {
        mainType = 0;
        bluetoothName = audioroutesinfo.bluetoothName;
        mainType = audioroutesinfo.mainType;
    }

    AudioRoutesInfo(Parcel parcel)
    {
        mainType = 0;
        bluetoothName = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        mainType = parcel.readInt();
    }

    private static String typeToString(int i)
    {
        if(i == 0)
            return "SPEAKER";
        if((i & 1) != 0)
            return "HEADSET";
        if((i & 2) != 0)
            return "HEADPHONES";
        if((i & 4) != 0)
            return "DOCK_SPEAKERS";
        if((i & 8) != 0)
            return "HDMI";
        if((i & 0x10) != 0)
            return "USB";
        else
            return Integer.toHexString(i);
    }

    public int describeContents()
    {
        return 0;
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder()).append(getClass().getSimpleName()).append("{ type=").append(typeToString(mainType));
        String s;
        if(TextUtils.isEmpty(bluetoothName))
            s = "";
        else
            s = (new StringBuilder()).append(", bluetoothName=").append(bluetoothName).toString();
        return stringbuilder.append(s).append(" }").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        TextUtils.writeToParcel(bluetoothName, parcel, i);
        parcel.writeInt(mainType);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public AudioRoutesInfo createFromParcel(Parcel parcel)
        {
            return new AudioRoutesInfo(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public AudioRoutesInfo[] newArray(int i)
        {
            return new AudioRoutesInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int MAIN_DOCK_SPEAKERS = 4;
    public static final int MAIN_HDMI = 8;
    public static final int MAIN_HEADPHONES = 2;
    public static final int MAIN_HEADSET = 1;
    public static final int MAIN_SPEAKER = 0;
    public static final int MAIN_USB = 16;
    public CharSequence bluetoothName;
    public int mainType;

}
