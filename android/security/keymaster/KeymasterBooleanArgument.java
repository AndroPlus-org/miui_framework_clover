// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keymaster;

import android.os.Parcel;

// Referenced classes of package android.security.keymaster:
//            KeymasterArgument, KeymasterDefs

class KeymasterBooleanArgument extends KeymasterArgument
{

    public KeymasterBooleanArgument(int i)
    {
        super(i);
        value = true;
        switch(KeymasterDefs.getTagType(i))
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Bad bool tag ").append(i).toString());

        case 1879048192: 
            return;
        }
    }

    public KeymasterBooleanArgument(int i, Parcel parcel)
    {
        super(i);
        value = true;
    }

    public void writeValue(Parcel parcel)
    {
    }

    public final boolean value;
}
