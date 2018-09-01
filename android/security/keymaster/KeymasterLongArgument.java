// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keymaster;

import android.os.Parcel;

// Referenced classes of package android.security.keymaster:
//            KeymasterArgument, KeymasterDefs

class KeymasterLongArgument extends KeymasterArgument
{

    public KeymasterLongArgument(int i, long l)
    {
        super(i);
        switch(KeymasterDefs.getTagType(i))
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Bad long tag ").append(i).toString());

        case -1610612736: 
        case 1342177280: 
            value = l;
            break;
        }
    }

    public KeymasterLongArgument(int i, Parcel parcel)
    {
        super(i);
        value = parcel.readLong();
    }

    public void writeValue(Parcel parcel)
    {
        parcel.writeLong(value);
    }

    public final long value;
}
