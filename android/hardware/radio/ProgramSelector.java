// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.radio;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.*;
import java.util.stream.Stream;

public final class ProgramSelector
    implements Parcelable
{
    public static final class Identifier
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public boolean equals(Object obj)
        {
            boolean flag = true;
            if(this == obj)
                return true;
            if(!(obj instanceof Identifier))
                return false;
            obj = (Identifier)obj;
            if(((Identifier) (obj)).getType() != mType || ((Identifier) (obj)).getValue() != mValue)
                flag = false;
            return flag;
        }

        public int getType()
        {
            return mType;
        }

        public long getValue()
        {
            return mValue;
        }

        public int hashCode()
        {
            return Objects.hash(new Object[] {
                Integer.valueOf(mType), Long.valueOf(mValue)
            });
        }

        public String toString()
        {
            return (new StringBuilder()).append("Identifier(").append(mType).append(", ").append(mValue).append(")").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(mType);
            parcel.writeLong(mValue);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public Identifier createFromParcel(Parcel parcel)
            {
                return new Identifier(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public Identifier[] newArray(int i)
            {
                return new Identifier[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private final int mType;
        private final long mValue;


        public Identifier(int i, long l)
        {
            mType = i;
            mValue = l;
        }

        private Identifier(Parcel parcel)
        {
            mType = parcel.readInt();
            mValue = parcel.readLong();
        }

        Identifier(Parcel parcel, Identifier identifier)
        {
            this(parcel);
        }
    }


    public ProgramSelector(int i, Identifier identifier, Identifier aidentifier[], long al[])
    {
        Identifier aidentifier1[] = aidentifier;
        if(aidentifier == null)
            aidentifier1 = new Identifier[0];
        aidentifier = al;
        if(al == null)
            aidentifier = new long[0];
        if(Stream.of(aidentifier1).anyMatch(_.Lambda.YT5WdsCCCONt9rJHRq_uXhDUWbM.$INST$0))
        {
            throw new IllegalArgumentException("secondaryIds list must not contain nulls");
        } else
        {
            mProgramType = i;
            mPrimaryId = (Identifier)Objects.requireNonNull(identifier);
            mSecondaryIds = aidentifier1;
            mVendorIds = aidentifier;
            return;
        }
    }

    private ProgramSelector(Parcel parcel)
    {
        mProgramType = parcel.readInt();
        mPrimaryId = (Identifier)parcel.readTypedObject(Identifier.CREATOR);
        mSecondaryIds = (Identifier[])parcel.createTypedArray(Identifier.CREATOR);
        if(Stream.of(mSecondaryIds).anyMatch(_.Lambda.YT5WdsCCCONt9rJHRq_uXhDUWbM.$INST$1))
        {
            throw new IllegalArgumentException("secondaryIds list must not contain nulls");
        } else
        {
            mVendorIds = parcel.createLongArray();
            return;
        }
    }

    ProgramSelector(Parcel parcel, ProgramSelector programselector)
    {
        this(parcel);
    }

    public static ProgramSelector createAmFmSelector(int i, int j)
    {
        return createAmFmSelector(i, j, 0);
    }

    public static ProgramSelector createAmFmSelector(int i, int j, int k)
    {
        boolean flag;
        boolean flag1;
        if(i == 0 || i == 3)
            flag = true;
        else
            flag = false;
        if(i == 3 || i == 2)
            flag1 = true;
        else
            flag1 = false;
        if(!flag && flag1 ^ true && i != 1)
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown band: ").append(i).toString());
        if(k < 0 || k > 8)
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid subchannel: ").append(k).toString());
        if(k > 0 && flag1 ^ true)
            throw new IllegalArgumentException("Subchannels are not supported for non-HD radio");
        if(!isValidAmFmFrequency(flag, j))
            throw new IllegalArgumentException("Provided value is not a valid AM/FM frequency");
        Identifier identifier;
        Identifier aidentifier[];
        if(flag)
            i = 1;
        else
            i = 2;
        identifier = new Identifier(1, j);
        aidentifier = null;
        if(k > 0)
        {
            aidentifier = new Identifier[1];
            aidentifier[0] = new Identifier(4, k - 1);
        }
        return new ProgramSelector(i, identifier, aidentifier, null);
    }

    private static boolean isValidAmFmFrequency(boolean flag, int i)
    {
        boolean flag1 = true;
        boolean flag2 = false;
        if(flag)
        {
            if(i > 150 && i < 30000)
                flag = flag1;
            else
                flag = false;
            return flag;
        }
        flag = flag2;
        if(i > 60000)
        {
            flag = flag2;
            if(i < 0x1adb0)
                flag = true;
        }
        return flag;
    }

    static boolean lambda$_2D_android_hardware_radio_ProgramSelector_14965(Identifier identifier)
    {
        boolean flag;
        if(identifier == null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    static boolean lambda$_2D_android_hardware_radio_ProgramSelector_7454(Identifier identifier)
    {
        boolean flag;
        if(identifier == null)
            flag = true;
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
        if(!(obj instanceof ProgramSelector))
            return false;
        obj = (ProgramSelector)obj;
        if(((ProgramSelector) (obj)).getProgramType() == mProgramType)
            flag = mPrimaryId.equals(((ProgramSelector) (obj)).getPrimaryId());
        return flag;
    }

    public Identifier[] getAllIds(int i)
    {
        ArrayList arraylist = new ArrayList();
        if(mPrimaryId.getType() == i)
            arraylist.add(mPrimaryId);
        Identifier aidentifier[] = mSecondaryIds;
        int j = 0;
        for(int k = aidentifier.length; j < k; j++)
        {
            Identifier identifier = aidentifier[j];
            if(identifier.getType() == i)
                arraylist.add(identifier);
        }

        return (Identifier[])arraylist.toArray(new Identifier[arraylist.size()]);
    }

    public long getFirstId(int i)
    {
        if(mPrimaryId.getType() == i)
            return mPrimaryId.getValue();
        Identifier aidentifier[] = mSecondaryIds;
        int j = 0;
        for(int k = aidentifier.length; j < k; j++)
        {
            Identifier identifier = aidentifier[j];
            if(identifier.getType() == i)
                return identifier.getValue();
        }

        throw new IllegalArgumentException((new StringBuilder()).append("Identifier ").append(i).append(" not found").toString());
    }

    public Identifier getPrimaryId()
    {
        return mPrimaryId;
    }

    public int getProgramType()
    {
        return mProgramType;
    }

    public Identifier[] getSecondaryIds()
    {
        return mSecondaryIds;
    }

    public long[] getVendorIds()
    {
        return mVendorIds;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            Integer.valueOf(mProgramType), mPrimaryId
        });
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder("ProgramSelector(type=")).append(mProgramType).append(", primary=").append(mPrimaryId);
        if(mSecondaryIds.length > 0)
            stringbuilder.append(", secondary=").append(mSecondaryIds);
        if(mVendorIds.length > 0)
            stringbuilder.append(", vendor=").append(mVendorIds);
        stringbuilder.append(")");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mProgramType);
        parcel.writeTypedObject(mPrimaryId, 0);
        parcel.writeTypedArray(mSecondaryIds, 0);
        parcel.writeLongArray(mVendorIds);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ProgramSelector createFromParcel(Parcel parcel)
        {
            return new ProgramSelector(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ProgramSelector[] newArray(int i)
        {
            return new ProgramSelector[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int IDENTIFIER_TYPE_AMFM_FREQUENCY = 1;
    public static final int IDENTIFIER_TYPE_DAB_ENSEMBLE = 6;
    public static final int IDENTIFIER_TYPE_DAB_FREQUENCY = 8;
    public static final int IDENTIFIER_TYPE_DAB_SCID = 7;
    public static final int IDENTIFIER_TYPE_DAB_SIDECC = 5;
    public static final int IDENTIFIER_TYPE_DRMO_FREQUENCY = 10;
    public static final int IDENTIFIER_TYPE_DRMO_MODULATION = 11;
    public static final int IDENTIFIER_TYPE_DRMO_SERVICE_ID = 9;
    public static final int IDENTIFIER_TYPE_HD_STATION_ID_EXT = 3;
    public static final int IDENTIFIER_TYPE_HD_SUBCHANNEL = 4;
    public static final int IDENTIFIER_TYPE_RDS_PI = 2;
    public static final int IDENTIFIER_TYPE_SXM_CHANNEL = 13;
    public static final int IDENTIFIER_TYPE_SXM_SERVICE_ID = 12;
    public static final int IDENTIFIER_TYPE_VENDOR_PRIMARY_END = 1999;
    public static final int IDENTIFIER_TYPE_VENDOR_PRIMARY_START = 1000;
    public static final int PROGRAM_TYPE_AM = 1;
    public static final int PROGRAM_TYPE_AM_HD = 3;
    public static final int PROGRAM_TYPE_DAB = 5;
    public static final int PROGRAM_TYPE_DRMO = 6;
    public static final int PROGRAM_TYPE_FM = 2;
    public static final int PROGRAM_TYPE_FM_HD = 4;
    public static final int PROGRAM_TYPE_SXM = 7;
    public static final int PROGRAM_TYPE_VENDOR_END = 1999;
    public static final int PROGRAM_TYPE_VENDOR_START = 1000;
    private final Identifier mPrimaryId;
    private final int mProgramType;
    private final Identifier mSecondaryIds[];
    private final long mVendorIds[];

}
