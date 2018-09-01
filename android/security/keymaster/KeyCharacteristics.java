// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keymaster;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.*;

// Referenced classes of package android.security.keymaster:
//            KeymasterArguments

public class KeyCharacteristics
    implements Parcelable
{

    public KeyCharacteristics()
    {
    }

    protected KeyCharacteristics(Parcel parcel)
    {
        readFromParcel(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean getBoolean(int i)
    {
        if(hwEnforced.containsTag(i))
            return hwEnforced.getBoolean(i);
        else
            return swEnforced.getBoolean(i);
    }

    public Date getDate(int i)
    {
        Date date = swEnforced.getDate(i, null);
        if(date != null)
            return date;
        else
            return hwEnforced.getDate(i, null);
    }

    public Integer getEnum(int i)
    {
        if(hwEnforced.containsTag(i))
            return Integer.valueOf(hwEnforced.getEnum(i, -1));
        if(swEnforced.containsTag(i))
            return Integer.valueOf(swEnforced.getEnum(i, -1));
        else
            return null;
    }

    public List getEnums(int i)
    {
        ArrayList arraylist = new ArrayList();
        arraylist.addAll(hwEnforced.getEnums(i));
        arraylist.addAll(swEnforced.getEnums(i));
        return arraylist;
    }

    public long getUnsignedInt(int i, long l)
    {
        if(hwEnforced.containsTag(i))
            return hwEnforced.getUnsignedInt(i, l);
        else
            return swEnforced.getUnsignedInt(i, l);
    }

    public List getUnsignedLongs(int i)
    {
        ArrayList arraylist = new ArrayList();
        arraylist.addAll(hwEnforced.getUnsignedLongs(i));
        arraylist.addAll(swEnforced.getUnsignedLongs(i));
        return arraylist;
    }

    public void readFromParcel(Parcel parcel)
    {
        swEnforced = (KeymasterArguments)KeymasterArguments.CREATOR.createFromParcel(parcel);
        hwEnforced = (KeymasterArguments)KeymasterArguments.CREATOR.createFromParcel(parcel);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        swEnforced.writeToParcel(parcel, i);
        hwEnforced.writeToParcel(parcel, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public KeyCharacteristics createFromParcel(Parcel parcel)
        {
            return new KeyCharacteristics(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public KeyCharacteristics[] newArray(int i)
        {
            return new KeyCharacteristics[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public KeymasterArguments hwEnforced;
    public KeymasterArguments swEnforced;

}
