// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keymaster;

import android.os.Parcel;

// Referenced classes of package android.security.keymaster:
//            KeymasterArgument, KeymasterDefs

class KeymasterIntArgument extends KeymasterArgument
{

    public KeymasterIntArgument(int i, int j)
    {
        super(i);
        switch(KeymasterDefs.getTagType(i))
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Bad int tag ").append(i).toString());

        case 268435456: 
        case 536870912: 
        case 805306368: 
        case 1073741824: 
            value = j;
            break;
        }
    }

    public KeymasterIntArgument(int i, Parcel parcel)
    {
        super(i);
        value = parcel.readInt();
    }

    public void writeValue(Parcel parcel)
    {
        parcel.writeInt(value);
    }

    public final int value;
}
