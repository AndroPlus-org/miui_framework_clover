// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.util.*;

public final class BluetoothAvrcpPlayerSettings
    implements Parcelable
{

    public BluetoothAvrcpPlayerSettings(int i)
    {
        mSettingsValue = new HashMap();
        mSettings = i;
    }

    private BluetoothAvrcpPlayerSettings(Parcel parcel)
    {
        mSettingsValue = new HashMap();
        mSettings = parcel.readInt();
        int i = parcel.readInt();
        for(int j = 0; j < i; j++)
            mSettingsValue.put(Integer.valueOf(parcel.readInt()), Integer.valueOf(parcel.readInt()));

    }

    BluetoothAvrcpPlayerSettings(Parcel parcel, BluetoothAvrcpPlayerSettings bluetoothavrcpplayersettings)
    {
        this(parcel);
    }

    public void addSettingValue(int i, int j)
    {
        if((mSettings & i) == 0)
        {
            Log.e("BluetoothAvrcpPlayerSettings", (new StringBuilder()).append("Setting not supported: ").append(i).append(" ").append(mSettings).toString());
            throw new IllegalStateException((new StringBuilder()).append("Setting not supported: ").append(i).toString());
        } else
        {
            mSettingsValue.put(Integer.valueOf(i), Integer.valueOf(j));
            return;
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public int getSettingValue(int i)
    {
        if((mSettings & i) == 0)
        {
            Log.e("BluetoothAvrcpPlayerSettings", (new StringBuilder()).append("Setting not supported: ").append(i).append(" ").append(mSettings).toString());
            throw new IllegalStateException((new StringBuilder()).append("Setting not supported: ").append(i).toString());
        }
        Integer integer = (Integer)mSettingsValue.get(Integer.valueOf(i));
        if(integer == null)
            return -1;
        else
            return integer.intValue();
    }

    public int getSettings()
    {
        return mSettings;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mSettings);
        parcel.writeInt(mSettingsValue.size());
        for(Iterator iterator = mSettingsValue.keySet().iterator(); iterator.hasNext(); parcel.writeInt(((Integer)mSettingsValue.get(Integer.valueOf(i))).intValue()))
        {
            i = ((Integer)iterator.next()).intValue();
            parcel.writeInt(i);
        }

    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public BluetoothAvrcpPlayerSettings createFromParcel(Parcel parcel)
        {
            return new BluetoothAvrcpPlayerSettings(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public BluetoothAvrcpPlayerSettings[] newArray(int i)
        {
            return new BluetoothAvrcpPlayerSettings[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int SETTING_EQUALIZER = 1;
    public static final int SETTING_REPEAT = 2;
    public static final int SETTING_SCAN = 8;
    public static final int SETTING_SHUFFLE = 4;
    public static final int STATE_ALL_TRACK = 3;
    public static final int STATE_GROUP = 4;
    public static final int STATE_INVALID = -1;
    public static final int STATE_OFF = 0;
    public static final int STATE_ON = 1;
    public static final int STATE_SINGLE_TRACK = 2;
    public static final String TAG = "BluetoothAvrcpPlayerSettings";
    private int mSettings;
    private Map mSettingsValue;

}
