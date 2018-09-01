// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keymaster;

import android.os.*;

// Referenced classes of package android.security.keymaster:
//            KeymasterDefs, KeymasterIntArgument, KeymasterLongArgument, KeymasterDateArgument, 
//            KeymasterBlobArgument, KeymasterBooleanArgument

abstract class KeymasterArgument
    implements Parcelable
{

    protected KeymasterArgument(int i)
    {
        tag = i;
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(tag);
        writeValue(parcel);
    }

    public abstract void writeValue(Parcel parcel);

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public KeymasterArgument createFromParcel(Parcel parcel)
        {
            int i = parcel.dataPosition();
            int j = parcel.readInt();
            switch(KeymasterDefs.getTagType(j))
            {
            default:
                throw new ParcelFormatException((new StringBuilder()).append("Bad tag: ").append(j).append(" at ").append(i).toString());

            case 268435456: 
            case 536870912: 
            case 805306368: 
            case 1073741824: 
                return new KeymasterIntArgument(j, parcel);

            case -1610612736: 
            case 1342177280: 
                return new KeymasterLongArgument(j, parcel);

            case 1610612736: 
                return new KeymasterDateArgument(j, parcel);

            case -2147483648: 
            case -1879048192: 
                return new KeymasterBlobArgument(j, parcel);

            case 1879048192: 
                return new KeymasterBooleanArgument(j, parcel);
            }
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public KeymasterArgument[] newArray(int i)
        {
            return new KeymasterArgument[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public final int tag;

}
