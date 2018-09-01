// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keymaster;

import android.os.Parcel;
import java.util.Date;

// Referenced classes of package android.security.keymaster:
//            KeymasterArgument, KeymasterDefs

class KeymasterDateArgument extends KeymasterArgument
{

    public KeymasterDateArgument(int i, Parcel parcel)
    {
        super(i);
        date = new Date(parcel.readLong());
    }

    public KeymasterDateArgument(int i, Date date1)
    {
        super(i);
        switch(KeymasterDefs.getTagType(i))
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Bad date tag ").append(i).toString());

        case 1610612736: 
            date = date1;
            break;
        }
    }

    public void writeValue(Parcel parcel)
    {
        parcel.writeLong(date.getTime());
    }

    public final Date date;
}
