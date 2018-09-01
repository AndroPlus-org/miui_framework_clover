// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.midi;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.media.midi:
//            MidiDeviceInfo

public final class MidiDeviceStatus
    implements Parcelable
{

    public MidiDeviceStatus(MidiDeviceInfo midideviceinfo)
    {
        mDeviceInfo = midideviceinfo;
        mInputPortOpen = new boolean[midideviceinfo.getInputPortCount()];
        mOutputPortOpenCount = new int[midideviceinfo.getOutputPortCount()];
    }

    public MidiDeviceStatus(MidiDeviceInfo midideviceinfo, boolean aflag[], int ai[])
    {
        mDeviceInfo = midideviceinfo;
        mInputPortOpen = new boolean[aflag.length];
        System.arraycopy(aflag, 0, mInputPortOpen, 0, aflag.length);
        mOutputPortOpenCount = new int[ai.length];
        System.arraycopy(ai, 0, mOutputPortOpenCount, 0, ai.length);
    }

    public int describeContents()
    {
        return 0;
    }

    public MidiDeviceInfo getDeviceInfo()
    {
        return mDeviceInfo;
    }

    public int getOutputPortOpenCount(int i)
    {
        return mOutputPortOpenCount[i];
    }

    public boolean isInputPortOpen(int i)
    {
        return mInputPortOpen[i];
    }

    public String toString()
    {
        int i = mDeviceInfo.getInputPortCount();
        int j = mDeviceInfo.getOutputPortCount();
        StringBuilder stringbuilder = new StringBuilder("mInputPortOpen=[");
        for(int k = 0; k < i; k++)
        {
            stringbuilder.append(mInputPortOpen[k]);
            if(k < i - 1)
                stringbuilder.append(",");
        }

        stringbuilder.append("] mOutputPortOpenCount=[");
        for(int l = 0; l < j; l++)
        {
            stringbuilder.append(mOutputPortOpenCount[l]);
            if(l < j - 1)
                stringbuilder.append(",");
        }

        stringbuilder.append("]");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(mDeviceInfo, i);
        parcel.writeBooleanArray(mInputPortOpen);
        parcel.writeIntArray(mOutputPortOpenCount);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public MidiDeviceStatus createFromParcel(Parcel parcel)
        {
            return new MidiDeviceStatus((MidiDeviceInfo)parcel.readParcelable(android/media/midi/MidiDeviceInfo.getClassLoader()), parcel.createBooleanArray(), parcel.createIntArray());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public MidiDeviceStatus[] newArray(int i)
        {
            return new MidiDeviceStatus[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = "MidiDeviceStatus";
    private final MidiDeviceInfo mDeviceInfo;
    private final boolean mInputPortOpen[];
    private final int mOutputPortOpenCount[];

}
