// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.usb;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.hardware.usb:
//            UsbPort

public final class UsbPortStatus
    implements Parcelable
{

    public UsbPortStatus(int i, int j, int k, int l)
    {
        mCurrentMode = i;
        mCurrentPowerRole = j;
        mCurrentDataRole = k;
        mSupportedRoleCombinations = l;
    }

    public int describeContents()
    {
        return 0;
    }

    public int getCurrentDataRole()
    {
        return mCurrentDataRole;
    }

    public int getCurrentMode()
    {
        return mCurrentMode;
    }

    public int getCurrentPowerRole()
    {
        return mCurrentPowerRole;
    }

    public int getSupportedRoleCombinations()
    {
        return mSupportedRoleCombinations;
    }

    public boolean isConnected()
    {
        boolean flag = false;
        if(mCurrentMode != 0)
            flag = true;
        return flag;
    }

    public boolean isRoleCombinationSupported(int i, int j)
    {
        boolean flag = false;
        if((mSupportedRoleCombinations & UsbPort.combineRolesAsBit(i, j)) != 0)
            flag = true;
        return flag;
    }

    public String toString()
    {
        return (new StringBuilder()).append("UsbPortStatus{connected=").append(isConnected()).append(", currentMode=").append(UsbPort.modeToString(mCurrentMode)).append(", currentPowerRole=").append(UsbPort.powerRoleToString(mCurrentPowerRole)).append(", currentDataRole=").append(UsbPort.dataRoleToString(mCurrentDataRole)).append(", supportedRoleCombinations=").append(UsbPort.roleCombinationsToString(mSupportedRoleCombinations)).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mCurrentMode);
        parcel.writeInt(mCurrentPowerRole);
        parcel.writeInt(mCurrentDataRole);
        parcel.writeInt(mSupportedRoleCombinations);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public UsbPortStatus createFromParcel(Parcel parcel)
        {
            return new UsbPortStatus(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public UsbPortStatus[] newArray(int i)
        {
            return new UsbPortStatus[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final int mCurrentDataRole;
    private final int mCurrentMode;
    private final int mCurrentPowerRole;
    private final int mSupportedRoleCombinations;

}
