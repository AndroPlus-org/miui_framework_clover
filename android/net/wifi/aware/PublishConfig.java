// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.aware;

import android.os.Parcel;
import android.os.Parcelable;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import libcore.util.HexEncoding;

// Referenced classes of package android.net.wifi.aware:
//            WifiAwareUtils, TlvBufferUtils, Characteristics

public final class PublishConfig
    implements Parcelable
{
    public static final class Builder
    {

        public PublishConfig build()
        {
            return new PublishConfig(mServiceName, mServiceSpecificInfo, mMatchFilter, mPublishType, mTtlSec, mEnableTerminateNotification);
        }

        public Builder setMatchFilter(List list)
        {
            mMatchFilter = (new TlvBufferUtils.TlvConstructor(0, 1)).allocateAndPut(list).getArray();
            return this;
        }

        public Builder setPublishType(int i)
        {
            if(i < 0 || i > 1)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Invalid publishType - ").append(i).toString());
            } else
            {
                mPublishType = i;
                return this;
            }
        }

        public Builder setServiceName(String s)
        {
            if(s == null)
            {
                throw new IllegalArgumentException("Invalid service name - must be non-null");
            } else
            {
                mServiceName = s.getBytes(StandardCharsets.UTF_8);
                return this;
            }
        }

        public Builder setServiceSpecificInfo(byte abyte0[])
        {
            mServiceSpecificInfo = abyte0;
            return this;
        }

        public Builder setTerminateNotificationEnabled(boolean flag)
        {
            mEnableTerminateNotification = flag;
            return this;
        }

        public Builder setTtlSec(int i)
        {
            if(i < 0)
            {
                throw new IllegalArgumentException("Invalid ttlSec - must be non-negative");
            } else
            {
                mTtlSec = i;
                return this;
            }
        }

        private boolean mEnableTerminateNotification;
        private byte mMatchFilter[];
        private int mPublishType;
        private byte mServiceName[];
        private byte mServiceSpecificInfo[];
        private int mTtlSec;

        public Builder()
        {
            mPublishType = 0;
            mTtlSec = 0;
            mEnableTerminateNotification = true;
        }
    }


    public PublishConfig(byte abyte0[], byte abyte1[], byte abyte2[], int i, int j, boolean flag)
    {
        mServiceName = abyte0;
        mServiceSpecificInfo = abyte1;
        mMatchFilter = abyte2;
        mPublishType = i;
        mTtlSec = j;
        mEnableTerminateNotification = flag;
    }

    public void assertValid(Characteristics characteristics)
        throws IllegalArgumentException
    {
        WifiAwareUtils.validateServiceName(mServiceName);
        if(!TlvBufferUtils.isValid(mMatchFilter, 0, 1))
            throw new IllegalArgumentException("Invalid txFilter configuration - LV fields do not match up to length");
        if(mPublishType < 0 || mPublishType > 1)
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid publishType - ").append(mPublishType).toString());
        if(mTtlSec < 0)
            throw new IllegalArgumentException("Invalid ttlSec - must be non-negative");
        if(characteristics != null)
        {
            int i = characteristics.getMaxServiceNameLength();
            if(i != 0 && mServiceName.length > i)
                throw new IllegalArgumentException("Service name longer than supported by device characteristics");
            i = characteristics.getMaxServiceSpecificInfoLength();
            if(i != 0 && mServiceSpecificInfo != null && mServiceSpecificInfo.length > i)
                throw new IllegalArgumentException("Service specific info longer than supported by device characteristics");
            i = characteristics.getMaxMatchFilterLength();
            if(i != 0 && mMatchFilter != null && mMatchFilter.length > i)
                throw new IllegalArgumentException("Match filter longer than supported by device characteristics");
        }
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
        if(!(obj instanceof PublishConfig))
            return false;
        obj = (PublishConfig)obj;
        if(Arrays.equals(mServiceName, ((PublishConfig) (obj)).mServiceName) && Arrays.equals(mServiceSpecificInfo, ((PublishConfig) (obj)).mServiceSpecificInfo) && Arrays.equals(mMatchFilter, ((PublishConfig) (obj)).mMatchFilter) && mPublishType == ((PublishConfig) (obj)).mPublishType && mTtlSec == ((PublishConfig) (obj)).mTtlSec)
        {
            if(mEnableTerminateNotification != ((PublishConfig) (obj)).mEnableTerminateNotification)
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public int hashCode()
    {
        int i = Arrays.hashCode(mServiceName);
        int j = Arrays.hashCode(mServiceSpecificInfo);
        int k = Arrays.hashCode(mMatchFilter);
        int l = mPublishType;
        int i1 = mTtlSec;
        int j1;
        if(mEnableTerminateNotification)
            j1 = 1;
        else
            j1 = 0;
        return (((((i + 527) * 31 + j) * 31 + k) * 31 + l) * 31 + i1) * 31 + j1;
    }

    public String toString()
    {
        boolean flag = false;
        StringBuilder stringbuilder = (new StringBuilder()).append("PublishConfig [mServiceName='");
        Object obj;
        int i;
        if(mServiceName == null)
            obj = "<null>";
        else
            obj = String.valueOf(HexEncoding.encode(mServiceName));
        obj = stringbuilder.append(((String) (obj))).append(", mServiceName.length=");
        if(mServiceName == null)
            i = 0;
        else
            i = mServiceName.length;
        stringbuilder = ((StringBuilder) (obj)).append(i).append(", mServiceSpecificInfo='");
        if(mServiceSpecificInfo == null)
            obj = "<null>";
        else
            obj = String.valueOf(HexEncoding.encode(mServiceSpecificInfo));
        obj = stringbuilder.append(((String) (obj))).append(", mServiceSpecificInfo.length=");
        if(mServiceSpecificInfo == null)
            i = 0;
        else
            i = mServiceSpecificInfo.length;
        obj = ((StringBuilder) (obj)).append(i).append(", mMatchFilter=").append((new TlvBufferUtils.TlvIterable(0, 1, mMatchFilter)).toString()).append(", mMatchFilter.length=");
        if(mMatchFilter == null)
            i = ((flag) ? 1 : 0);
        else
            i = mMatchFilter.length;
        return ((StringBuilder) (obj)).append(i).append(", mPublishType=").append(mPublishType).append(", mTtlSec=").append(mTtlSec).append(", mEnableTerminateNotification=").append(mEnableTerminateNotification).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeByteArray(mServiceName);
        parcel.writeByteArray(mServiceSpecificInfo);
        parcel.writeByteArray(mMatchFilter);
        parcel.writeInt(mPublishType);
        parcel.writeInt(mTtlSec);
        if(mEnableTerminateNotification)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PublishConfig createFromParcel(Parcel parcel)
        {
            byte abyte0[] = parcel.createByteArray();
            byte abyte1[] = parcel.createByteArray();
            byte abyte2[] = parcel.createByteArray();
            int i = parcel.readInt();
            int j = parcel.readInt();
            boolean flag;
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            return new PublishConfig(abyte0, abyte1, abyte2, i, j, flag);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PublishConfig[] newArray(int i)
        {
            return new PublishConfig[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int PUBLISH_TYPE_SOLICITED = 1;
    public static final int PUBLISH_TYPE_UNSOLICITED = 0;
    public final boolean mEnableTerminateNotification;
    public final byte mMatchFilter[];
    public final int mPublishType;
    public final byte mServiceName[];
    public final byte mServiceSpecificInfo[];
    public final int mTtlSec;

}
