// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.app.ActivityThread;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import android.os.*;
import java.util.List;
import java.util.Objects;

// Referenced classes of package android.bluetooth:
//            IBluetoothGatt

public final class BleScanWrapper
    implements Parcelable
{

    public BleScanWrapper(int i, ScanSettings scansettings, List list, WorkSource worksource, List list1, String s)
    {
        mClientIf = i;
        mSettings = scansettings;
        mFilters = list;
        mWorkSource = worksource;
        mResultStorages = list1;
        mOpPackageName = s;
    }

    private BleScanWrapper(Parcel parcel)
    {
        mClientIf = parcel.readInt();
        if(parcel.readInt() != 0)
            mSettings = (ScanSettings)ScanSettings.CREATOR.createFromParcel(parcel);
        else
            mSettings = null;
        mFilters = parcel.createTypedArrayList(ScanFilter.CREATOR);
        if(parcel.readInt() != 0)
            mWorkSource = (WorkSource)WorkSource.CREATOR.createFromParcel(parcel);
        else
            mWorkSource = null;
        mResultStorages = parcel.readArrayList(getClass().getClassLoader());
        mOpPackageName = parcel.readString();
    }

    BleScanWrapper(Parcel parcel, BleScanWrapper blescanwrapper)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        obj = (BleScanWrapper)obj;
        if(mClientIf != ((BleScanWrapper) (obj)).mClientIf)
            flag = false;
        return flag;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            Integer.valueOf(mClientIf)
        });
    }

    public void startScan(IBluetoothGatt ibluetoothgatt)
        throws RemoteException
    {
        ibluetoothgatt.startScan(mClientIf, mSettings, mFilters, mResultStorages, ActivityThread.currentOpPackageName());
    }

    public void stopScan(IBluetoothGatt ibluetoothgatt)
        throws RemoteException
    {
        ibluetoothgatt.stopScan(mClientIf);
    }

    public String toString()
    {
        return (new StringBuilder()).append(android/bluetooth/BleScanWrapper.getSimpleName()).append("[mClientIf = ").append(mClientIf).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mClientIf);
        if(mSettings != null)
        {
            parcel.writeInt(1);
            mSettings.writeToParcel(parcel, 0);
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeTypedList(mFilters);
        if(mWorkSource != null)
        {
            parcel.writeInt(1);
            mWorkSource.writeToParcel(parcel, 0);
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeList(mResultStorages);
        parcel.writeString(mOpPackageName);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public BleScanWrapper createFromParcel(Parcel parcel)
        {
            return new BleScanWrapper(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public BleScanWrapper[] newArray(int i)
        {
            return new BleScanWrapper[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final int mClientIf;
    private final List mFilters;
    private final String mOpPackageName;
    private final List mResultStorages;
    private final ScanSettings mSettings;
    private final WorkSource mWorkSource;

}
