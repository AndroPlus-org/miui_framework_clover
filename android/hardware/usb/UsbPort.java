// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.usb;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.Preconditions;

public final class UsbPort
    implements Parcelable
{

    public UsbPort(String s, int i)
    {
        mId = s;
        mSupportedModes = i;
    }

    public static void checkDataRole(int i)
    {
        Preconditions.checkArgumentInRange(i, 0, 2, "powerRole");
    }

    public static void checkMode(int i)
    {
        Preconditions.checkArgumentInRange(i, 0, 3, "portMode");
    }

    public static void checkPowerRole(int i)
    {
        Preconditions.checkArgumentInRange(i, 0, 2, "powerRole");
    }

    public static void checkRoles(int i, int j)
    {
        Preconditions.checkArgumentInRange(i, 0, 2, "powerRole");
        Preconditions.checkArgumentInRange(j, 0, 2, "dataRole");
    }

    public static int combineRolesAsBit(int i, int j)
    {
        checkRoles(i, j);
        return 1 << (i + 0) * 3 + j;
    }

    public static String dataRoleToString(int i)
    {
        switch(i)
        {
        default:
            return Integer.toString(i);

        case 0: // '\0'
            return "no-data";

        case 1: // '\001'
            return "host";

        case 2: // '\002'
            return "device";
        }
    }

    public static String modeToString(int i)
    {
        StringBuilder stringbuilder = new StringBuilder();
        if(i == 0)
            return "none";
        if((i & 3) == 3)
            stringbuilder.append("dual, ");
        else
        if((i & 2) == 2)
            stringbuilder.append("dfp, ");
        else
        if((i & 1) == 1)
            stringbuilder.append("ufp, ");
        if((i & 4) == 4)
            stringbuilder.append("audio_acc, ");
        if((i & 8) == 8)
            stringbuilder.append("debug_acc, ");
        if(stringbuilder.length() == 0)
            return Integer.toString(i);
        else
            return stringbuilder.substring(0, stringbuilder.length() - 2);
    }

    public static String powerRoleToString(int i)
    {
        switch(i)
        {
        default:
            return Integer.toString(i);

        case 0: // '\0'
            return "no-power";

        case 1: // '\001'
            return "source";

        case 2: // '\002'
            return "sink";
        }
    }

    public static String roleCombinationsToString(int i)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("[");
        boolean flag = true;
        int k = i;
        i = ((flag) ? 1 : 0);
        while(k != 0) 
        {
            int j = Integer.numberOfTrailingZeros(k);
            k &= 1 << j;
            int l = j / 3;
            if(i != 0)
                i = 0;
            else
                stringbuilder.append(", ");
            stringbuilder.append(powerRoleToString(l + 0));
            stringbuilder.append(':');
            stringbuilder.append(dataRoleToString(j % 3));
        }
        stringbuilder.append("]");
        return stringbuilder.toString();
    }

    public int describeContents()
    {
        return 0;
    }

    public String getId()
    {
        return mId;
    }

    public int getSupportedModes()
    {
        return mSupportedModes;
    }

    public boolean isModeSupported(int i)
    {
        return (mSupportedModes & i) == i;
    }

    public String toString()
    {
        return (new StringBuilder()).append("UsbPort{id=").append(mId).append(", supportedModes=").append(modeToString(mSupportedModes)).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mId);
        parcel.writeInt(mSupportedModes);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public UsbPort createFromParcel(Parcel parcel)
        {
            return new UsbPort(parcel.readString(), parcel.readInt());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public UsbPort[] newArray(int i)
        {
            return new UsbPort[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int DATA_ROLE_DEVICE = 2;
    public static final int DATA_ROLE_HOST = 1;
    public static final int DATA_ROLE_NONE = 0;
    public static final int MODE_AUDIO_ACCESSORY = 4;
    public static final int MODE_DEBUG_ACCESSORY = 8;
    public static final int MODE_DFP = 2;
    public static final int MODE_DUAL = 3;
    public static final int MODE_NONE = 0;
    public static final int MODE_UFP = 1;
    private static final int NUM_DATA_ROLES = 3;
    public static final int POWER_ROLE_NONE = 0;
    private static final int POWER_ROLE_OFFSET = 0;
    public static final int POWER_ROLE_SINK = 2;
    public static final int POWER_ROLE_SOURCE = 1;
    private final String mId;
    private final int mSupportedModes;

}
