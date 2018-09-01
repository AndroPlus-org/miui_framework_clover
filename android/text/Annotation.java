// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text;

import android.os.Parcel;

// Referenced classes of package android.text:
//            ParcelableSpan

public class Annotation
    implements ParcelableSpan
{

    public Annotation(Parcel parcel)
    {
        mKey = parcel.readString();
        mValue = parcel.readString();
    }

    public Annotation(String s, String s1)
    {
        mKey = s;
        mValue = s1;
    }

    public int describeContents()
    {
        return 0;
    }

    public String getKey()
    {
        return mKey;
    }

    public int getSpanTypeId()
    {
        return getSpanTypeIdInternal();
    }

    public int getSpanTypeIdInternal()
    {
        return 18;
    }

    public String getValue()
    {
        return mValue;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        writeToParcelInternal(parcel, i);
    }

    public void writeToParcelInternal(Parcel parcel, int i)
    {
        parcel.writeString(mKey);
        parcel.writeString(mValue);
    }

    private final String mKey;
    private final String mValue;
}
