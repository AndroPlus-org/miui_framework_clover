// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.companion;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.OneTimeUseBuilder;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.CollectionUtils;
import java.util.*;

// Referenced classes of package android.companion:
//            DeviceFilter

public final class AssociationRequest
    implements Parcelable
{
    public static final class Builder extends OneTimeUseBuilder
    {

        public Builder addDeviceFilter(DeviceFilter devicefilter)
        {
            checkNotUsed();
            if(devicefilter != null)
                mDeviceFilters = ArrayUtils.add(mDeviceFilters, devicefilter);
            return this;
        }

        public AssociationRequest build()
        {
            markUsed();
            return new AssociationRequest(mSingleDevice, mDeviceFilters, null);
        }

        public volatile Object build()
        {
            return build();
        }

        public Builder setSingleDevice(boolean flag)
        {
            checkNotUsed();
            mSingleDevice = flag;
            return this;
        }

        private ArrayList mDeviceFilters;
        private boolean mSingleDevice;

        public Builder()
        {
            mSingleDevice = false;
            mDeviceFilters = null;
        }
    }


    private AssociationRequest(Parcel parcel)
    {
        boolean flag = false;
        if(parcel.readByte() != 0)
            flag = true;
        this(flag, parcel.readParcelableList(new ArrayList(), android/companion/AssociationRequest.getClassLoader()));
    }

    AssociationRequest(Parcel parcel, AssociationRequest associationrequest)
    {
        this(parcel);
    }

    private AssociationRequest(boolean flag, List list)
    {
        mSingleDevice = flag;
        mDeviceFilters = CollectionUtils.emptyIfNull(list);
    }

    AssociationRequest(boolean flag, List list, AssociationRequest associationrequest)
    {
        this(flag, list);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        obj = (AssociationRequest)obj;
        if(mSingleDevice == ((AssociationRequest) (obj)).mSingleDevice)
            flag = Objects.equals(mDeviceFilters, ((AssociationRequest) (obj)).mDeviceFilters);
        return flag;
    }

    public List getDeviceFilters()
    {
        return mDeviceFilters;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            Boolean.valueOf(mSingleDevice), mDeviceFilters
        });
    }

    public boolean isSingleDevice()
    {
        return mSingleDevice;
    }

    public String toString()
    {
        return (new StringBuilder()).append("AssociationRequest{mSingleDevice=").append(mSingleDevice).append(", mDeviceFilters=").append(mDeviceFilters).append('}').toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        int j;
        if(mSingleDevice)
            j = 1;
        else
            j = 0;
        parcel.writeByte((byte)j);
        parcel.writeParcelableList(mDeviceFilters, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public AssociationRequest createFromParcel(Parcel parcel)
        {
            return new AssociationRequest(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public AssociationRequest[] newArray(int i)
        {
            return new AssociationRequest[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final List mDeviceFilters;
    private final boolean mSingleDevice;

}
