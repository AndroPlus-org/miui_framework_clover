// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Objects;

// Referenced classes of package android.bluetooth:
//            BluetoothCodecConfig

public final class BluetoothCodecStatus
    implements Parcelable
{

    public BluetoothCodecStatus(BluetoothCodecConfig bluetoothcodecconfig, BluetoothCodecConfig abluetoothcodecconfig[], BluetoothCodecConfig abluetoothcodecconfig1[])
    {
        mCodecConfig = bluetoothcodecconfig;
        mCodecsLocalCapabilities = abluetoothcodecconfig;
        mCodecsSelectableCapabilities = abluetoothcodecconfig1;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof BluetoothCodecStatus)
        {
            obj = (BluetoothCodecStatus)obj;
            boolean flag1 = flag;
            if(Objects.equals(((BluetoothCodecStatus) (obj)).mCodecConfig, mCodecConfig))
            {
                flag1 = flag;
                if(Objects.equals(((BluetoothCodecStatus) (obj)).mCodecsLocalCapabilities, mCodecsLocalCapabilities))
                    flag1 = Objects.equals(((BluetoothCodecStatus) (obj)).mCodecsSelectableCapabilities, mCodecsSelectableCapabilities);
            }
            return flag1;
        } else
        {
            return false;
        }
    }

    public BluetoothCodecConfig getCodecConfig()
    {
        return mCodecConfig;
    }

    public BluetoothCodecConfig[] getCodecsLocalCapabilities()
    {
        return mCodecsLocalCapabilities;
    }

    public BluetoothCodecConfig[] getCodecsSelectableCapabilities()
    {
        return mCodecsSelectableCapabilities;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            mCodecConfig, mCodecsLocalCapabilities, mCodecsLocalCapabilities
        });
    }

    public String toString()
    {
        return (new StringBuilder()).append("{mCodecConfig:").append(mCodecConfig).append(",mCodecsLocalCapabilities:").append(Arrays.toString(mCodecsLocalCapabilities)).append(",mCodecsSelectableCapabilities:").append(Arrays.toString(mCodecsSelectableCapabilities)).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeTypedObject(mCodecConfig, 0);
        parcel.writeTypedArray(mCodecsLocalCapabilities, 0);
        parcel.writeTypedArray(mCodecsSelectableCapabilities, 0);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public BluetoothCodecStatus createFromParcel(Parcel parcel)
        {
            return new BluetoothCodecStatus((BluetoothCodecConfig)parcel.readTypedObject(BluetoothCodecConfig.CREATOR), (BluetoothCodecConfig[])parcel.createTypedArray(BluetoothCodecConfig.CREATOR), (BluetoothCodecConfig[])parcel.createTypedArray(BluetoothCodecConfig.CREATOR));
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public BluetoothCodecStatus[] newArray(int i)
        {
            return new BluetoothCodecStatus[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final String EXTRA_CODEC_STATUS = "android.bluetooth.codec.extra.CODEC_STATUS";
    private final BluetoothCodecConfig mCodecConfig;
    private final BluetoothCodecConfig mCodecsLocalCapabilities[];
    private final BluetoothCodecConfig mCodecsSelectableCapabilities[];

}
