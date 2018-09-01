// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.companion;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.*;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.OneTimeUseBuilder;
import android.text.TextUtils;
import com.android.internal.util.*;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Pattern;

// Referenced classes of package android.companion:
//            DeviceFilter, BluetoothDeviceFilterUtils

public final class BluetoothLeDeviceFilter
    implements DeviceFilter
{
    public static final class Builder extends OneTimeUseBuilder
    {

        private void checkRangeNotEmpty(int i)
        {
            boolean flag = false;
            if(i > 0)
                flag = true;
            Preconditions.checkArgument(flag, "Range must be non-empty");
        }

        private void checkRenameNotSet()
        {
            boolean flag;
            if(mRenamePrefix == null)
                flag = true;
            else
                flag = false;
            Preconditions.checkState(flag, "Renaming rule can only be set once");
        }

        private Builder setRename(String s, String s1)
        {
            checkNotUsed();
            boolean flag;
            if(TextUtils.length(s) <= BluetoothLeDeviceFilter.getRenamePrefixLengthLimit())
                flag = true;
            else
                flag = false;
            Preconditions.checkArgument(flag, "Prefix is too long");
            mRenamePrefix = s;
            mRenameSuffix = s1;
            return this;
        }

        public BluetoothLeDeviceFilter build()
        {
            markUsed();
            return new BluetoothLeDeviceFilter(mNamePattern, mScanFilter, mRawDataFilter, mRawDataFilterMask, mRenamePrefix, mRenameSuffix, mRenameBytesFrom, mRenameBytesLength, mRenameNameFrom, mRenameNameLength, mRenameBytesReverseOrder, null);
        }

        public volatile Object build()
        {
            return build();
        }

        public Builder setNamePattern(Pattern pattern)
        {
            checkNotUsed();
            mNamePattern = pattern;
            return this;
        }

        public Builder setRawDataFilter(byte abyte0[], byte abyte1[])
        {
            boolean flag = true;
            checkNotUsed();
            Preconditions.checkNotNull(abyte0);
            boolean flag1 = flag;
            if(abyte1 != null)
                if(abyte0.length == abyte1.length)
                    flag1 = flag;
                else
                    flag1 = false;
            Preconditions.checkArgument(flag1, "Mask and filter should be the same length");
            mRawDataFilter = abyte0;
            mRawDataFilterMask = abyte1;
            return this;
        }

        public Builder setRenameFromBytes(String s, String s1, int i, int j, ByteOrder byteorder)
        {
            checkRenameNotSet();
            checkRangeNotEmpty(j);
            mRenameBytesFrom = i;
            mRenameBytesLength = j;
            boolean flag;
            if(byteorder == ByteOrder.LITTLE_ENDIAN)
                flag = true;
            else
                flag = false;
            mRenameBytesReverseOrder = flag;
            return setRename(s, s1);
        }

        public Builder setRenameFromName(String s, String s1, int i, int j)
        {
            checkRenameNotSet();
            checkRangeNotEmpty(j);
            mRenameNameFrom = i;
            mRenameNameLength = j;
            mRenameBytesReverseOrder = false;
            return setRename(s, s1);
        }

        public Builder setScanFilter(ScanFilter scanfilter)
        {
            checkNotUsed();
            mScanFilter = scanfilter;
            return this;
        }

        private Pattern mNamePattern;
        private byte mRawDataFilter[];
        private byte mRawDataFilterMask[];
        private int mRenameBytesFrom;
        private int mRenameBytesLength;
        private boolean mRenameBytesReverseOrder;
        private int mRenameNameFrom;
        private int mRenameNameLength;
        private String mRenamePrefix;
        private String mRenameSuffix;
        private ScanFilter mScanFilter;

        public Builder()
        {
            mRenameBytesFrom = -1;
            mRenameNameFrom = -1;
            mRenameBytesReverseOrder = false;
        }
    }


    private BluetoothLeDeviceFilter(Pattern pattern, ScanFilter scanfilter, byte abyte0[], byte abyte1[], String s, String s1, int i, 
            int j, int k, int l, boolean flag)
    {
        mNamePattern = pattern;
        mScanFilter = (ScanFilter)ObjectUtils.firstNotNull(scanfilter, ScanFilter.EMPTY);
        mRawDataFilter = abyte0;
        mRawDataFilterMask = abyte1;
        mRenamePrefix = s;
        mRenameSuffix = s1;
        mRenameBytesFrom = i;
        mRenameBytesLength = j;
        mRenameNameFrom = k;
        mRenameNameLength = l;
        mRenameBytesReverseOrder = flag;
    }

    BluetoothLeDeviceFilter(Pattern pattern, ScanFilter scanfilter, byte abyte0[], byte abyte1[], String s, String s1, int i, 
            int j, int k, int l, boolean flag, BluetoothLeDeviceFilter bluetoothledevicefilter)
    {
        this(pattern, scanfilter, abyte0, abyte1, s, s1, i, j, k, l, flag);
    }

    public static int getRenamePrefixLengthLimit()
    {
        return 10;
    }

    private boolean matches(BluetoothDevice bluetoothdevice)
    {
        boolean flag;
        if(BluetoothDeviceFilterUtils.matches(getScanFilter(), bluetoothdevice))
            flag = BluetoothDeviceFilterUtils.matchesName(getNamePattern(), bluetoothdevice);
        else
            flag = false;
        return flag;
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
        obj = (BluetoothLeDeviceFilter)obj;
        boolean flag1 = flag;
        if(mRenameBytesFrom == ((BluetoothLeDeviceFilter) (obj)).mRenameBytesFrom)
        {
            flag1 = flag;
            if(mRenameBytesLength == ((BluetoothLeDeviceFilter) (obj)).mRenameBytesLength)
            {
                flag1 = flag;
                if(mRenameNameFrom == ((BluetoothLeDeviceFilter) (obj)).mRenameNameFrom)
                {
                    flag1 = flag;
                    if(mRenameNameLength == ((BluetoothLeDeviceFilter) (obj)).mRenameNameLength)
                    {
                        flag1 = flag;
                        if(mRenameBytesReverseOrder == ((BluetoothLeDeviceFilter) (obj)).mRenameBytesReverseOrder)
                        {
                            flag1 = flag;
                            if(Objects.equals(mNamePattern, ((BluetoothLeDeviceFilter) (obj)).mNamePattern))
                            {
                                flag1 = flag;
                                if(Objects.equals(mScanFilter, ((BluetoothLeDeviceFilter) (obj)).mScanFilter))
                                {
                                    flag1 = flag;
                                    if(Arrays.equals(mRawDataFilter, ((BluetoothLeDeviceFilter) (obj)).mRawDataFilter))
                                    {
                                        flag1 = flag;
                                        if(Arrays.equals(mRawDataFilterMask, ((BluetoothLeDeviceFilter) (obj)).mRawDataFilterMask))
                                        {
                                            flag1 = flag;
                                            if(Objects.equals(mRenamePrefix, ((BluetoothLeDeviceFilter) (obj)).mRenamePrefix))
                                                flag1 = Objects.equals(mRenameSuffix, ((BluetoothLeDeviceFilter) (obj)).mRenameSuffix);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return flag1;
    }

    public String getDeviceDisplayName(ScanResult scanresult)
    {
        if(mRenameBytesFrom < 0 && mRenameNameFrom < 0)
            return BluetoothDeviceFilterUtils.getDeviceDisplayNameInternal(scanresult.getDevice());
        StringBuilder stringbuilder = new StringBuilder(TextUtils.emptyIfNull(mRenamePrefix));
        if(mRenameBytesFrom >= 0)
        {
            scanresult = scanresult.getScanRecord().getBytes();
            int i = mRenameBytesFrom;
            int j = (mRenameBytesFrom + mRenameBytesLength) - 1;
            int k;
            byte byte0;
            if(mRenameBytesReverseOrder)
                k = j;
            else
                k = i;
            if(mRenameBytesReverseOrder)
                byte0 = -1;
            else
                byte0 = 1;
            for(; i <= k && k <= j; k += byte0)
                stringbuilder.append(Byte.toHexString(scanresult[k], true));

        } else
        {
            stringbuilder.append(BluetoothDeviceFilterUtils.getDeviceDisplayNameInternal(scanresult.getDevice()).substring(mRenameNameFrom, mRenameNameFrom + mRenameNameLength));
        }
        return stringbuilder.append(TextUtils.emptyIfNull(mRenameSuffix)).toString();
    }

    public volatile String getDeviceDisplayName(Parcelable parcelable)
    {
        return getDeviceDisplayName((ScanResult)parcelable);
    }

    public int getMediumType()
    {
        return 1;
    }

    public Pattern getNamePattern()
    {
        return mNamePattern;
    }

    public byte[] getRawDataFilter()
    {
        return mRawDataFilter;
    }

    public byte[] getRawDataFilterMask()
    {
        return mRawDataFilterMask;
    }

    public int getRenameBytesFrom()
    {
        return mRenameBytesFrom;
    }

    public int getRenameBytesLength()
    {
        return mRenameBytesLength;
    }

    public String getRenamePrefix()
    {
        return mRenamePrefix;
    }

    public String getRenameSuffix()
    {
        return mRenameSuffix;
    }

    public ScanFilter getScanFilter()
    {
        return mScanFilter;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            mNamePattern, mScanFilter, mRawDataFilter, mRawDataFilterMask, mRenamePrefix, mRenameSuffix, Integer.valueOf(mRenameBytesFrom), Integer.valueOf(mRenameBytesLength), Integer.valueOf(mRenameNameFrom), Integer.valueOf(mRenameNameLength), 
            Boolean.valueOf(mRenameBytesReverseOrder)
        });
    }

    public boolean isRenameBytesReverseOrder()
    {
        return mRenameBytesReverseOrder;
    }

    public boolean matches(ScanResult scanresult)
    {
        boolean flag;
        if(matches(scanresult.getDevice()))
        {
            if(mRawDataFilter != null)
                flag = BitUtils.maskedEquals(scanresult.getScanRecord().getBytes(), mRawDataFilter, mRawDataFilterMask);
            else
                flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public volatile boolean matches(Parcelable parcelable)
    {
        return matches((ScanResult)parcelable);
    }

    public String toString()
    {
        return (new StringBuilder()).append("BluetoothLEDeviceFilter{mNamePattern=").append(mNamePattern).append(", mScanFilter=").append(mScanFilter).append(", mRawDataFilter=").append(Arrays.toString(mRawDataFilter)).append(", mRawDataFilterMask=").append(Arrays.toString(mRawDataFilterMask)).append(", mRenamePrefix='").append(mRenamePrefix).append('\'').append(", mRenameSuffix='").append(mRenameSuffix).append('\'').append(", mRenameBytesFrom=").append(mRenameBytesFrom).append(", mRenameBytesLength=").append(mRenameBytesLength).append(", mRenameNameFrom=").append(mRenameNameFrom).append(", mRenameNameLength=").append(mRenameNameLength).append(", mRenameBytesReverseOrder=").append(mRenameBytesReverseOrder).append('}').toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(BluetoothDeviceFilterUtils.patternToString(getNamePattern()));
        parcel.writeParcelable(mScanFilter, i);
        parcel.writeByteArray(mRawDataFilter);
        parcel.writeByteArray(mRawDataFilterMask);
        parcel.writeString(mRenamePrefix);
        parcel.writeString(mRenameSuffix);
        parcel.writeInt(mRenameBytesFrom);
        parcel.writeInt(mRenameBytesLength);
        parcel.writeInt(mRenameNameFrom);
        parcel.writeInt(mRenameNameLength);
        parcel.writeBoolean(mRenameBytesReverseOrder);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public BluetoothLeDeviceFilter createFromParcel(Parcel parcel)
        {
            Builder builder = (new Builder()).setNamePattern(BluetoothDeviceFilterUtils.patternFromString(parcel.readString())).setScanFilter((ScanFilter)parcel.readParcelable(null));
            byte abyte0[] = parcel.createByteArray();
            byte abyte1[] = parcel.createByteArray();
            if(abyte0 != null)
                builder.setRawDataFilter(abyte0, abyte1);
            String s1 = parcel.readString();
            String s = parcel.readString();
            int i = parcel.readInt();
            int j = parcel.readInt();
            int k = parcel.readInt();
            int l = parcel.readInt();
            boolean flag = parcel.readBoolean();
            if(s1 != null)
                if(i >= 0)
                {
                    if(flag)
                        parcel = ByteOrder.LITTLE_ENDIAN;
                    else
                        parcel = ByteOrder.BIG_ENDIAN;
                    builder.setRenameFromBytes(s1, s, i, j, parcel);
                } else
                {
                    builder.setRenameFromName(s1, s, k, l);
                }
            return builder.build();
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public BluetoothLeDeviceFilter[] newArray(int i)
        {
            return new BluetoothLeDeviceFilter[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final boolean DEBUG = false;
    private static final String LOG_TAG = "BluetoothLeDeviceFilter";
    private static final int RENAME_PREFIX_LENGTH_LIMIT = 10;
    private final Pattern mNamePattern;
    private final byte mRawDataFilter[];
    private final byte mRawDataFilterMask[];
    private final int mRenameBytesFrom;
    private final int mRenameBytesLength;
    private final boolean mRenameBytesReverseOrder;
    private final int mRenameNameFrom;
    private final int mRenameNameLength;
    private final String mRenamePrefix;
    private final String mRenameSuffix;
    private final ScanFilter mScanFilter;

}
