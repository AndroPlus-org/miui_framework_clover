// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keymaster;

import android.os.Parcel;

// Referenced classes of package android.security.keymaster:
//            KeymasterArgument, KeymasterDefs

class KeymasterBlobArgument extends KeymasterArgument
{

    public KeymasterBlobArgument(int i, Parcel parcel)
    {
        super(i);
        blob = parcel.createByteArray();
    }

    public KeymasterBlobArgument(int i, byte abyte0[])
    {
        super(i);
        switch(KeymasterDefs.getTagType(i))
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Bad blob tag ").append(i).toString());

        case -2147483648: 
        case -1879048192: 
            blob = abyte0;
            break;
        }
    }

    public void writeValue(Parcel parcel)
    {
        parcel.writeByteArray(blob);
    }

    public final byte blob[];
}
