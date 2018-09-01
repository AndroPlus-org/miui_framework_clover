// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.statistics;

import android.os.Parcel;
import android.util.Log;

public class ParcelUtils
{

    public ParcelUtils()
    {
    }

    public static String[] readStringArray(Parcel parcel)
    {
        if(parcel == null)
            return null;
        int i = parcel.readInt();
        if(i < 0 || i > 100)
            return null;
        String as[];
        try
        {
            as = new String[i];
            parcel.readStringArray(as);
        }
        // Misplaced declaration of an exception variable
        catch(Parcel parcel)
        {
            parcel.printStackTrace();
            return null;
        }
        return as;
    }

    public static void writeStringArray(Parcel parcel, String as[])
    {
        if(parcel == null)
            return;
        if(as == null)
        {
            parcel.writeInt(-1);
            return;
        }
        if(as.length > 100)
        {
            parcel.writeInt(0);
            parcel.writeStringArray(new String[0]);
            Log.e("ParcelUtils", "array is too long, write failed!!!");
            return;
        } else
        {
            parcel.writeInt(as.length);
            parcel.writeStringArray(as);
            return;
        }
    }

    private static final int MAX_STRING_ARRAY_LENGTH = 100;
    private static final String TAG = "ParcelUtils";
}
