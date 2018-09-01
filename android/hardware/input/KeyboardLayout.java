// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.input;

import android.os.*;

public final class KeyboardLayout
    implements Parcelable, Comparable
{

    private KeyboardLayout(Parcel parcel)
    {
        mDescriptor = parcel.readString();
        mLabel = parcel.readString();
        mCollection = parcel.readString();
        mPriority = parcel.readInt();
        mLocales = (LocaleList)LocaleList.CREATOR.createFromParcel(parcel);
        mVendorId = parcel.readInt();
        mProductId = parcel.readInt();
    }

    KeyboardLayout(Parcel parcel, KeyboardLayout keyboardlayout)
    {
        this(parcel);
    }

    public KeyboardLayout(String s, String s1, String s2, int i, LocaleList localelist, int j, int k)
    {
        mDescriptor = s;
        mLabel = s1;
        mCollection = s2;
        mPriority = i;
        mLocales = localelist;
        mVendorId = j;
        mProductId = k;
    }

    public int compareTo(KeyboardLayout keyboardlayout)
    {
        int i = Integer.compare(keyboardlayout.mPriority, mPriority);
        int j = i;
        if(i == 0)
            j = mLabel.compareToIgnoreCase(keyboardlayout.mLabel);
        i = j;
        if(j == 0)
            i = mCollection.compareToIgnoreCase(keyboardlayout.mCollection);
        return i;
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((KeyboardLayout)obj);
    }

    public int describeContents()
    {
        return 0;
    }

    public String getCollection()
    {
        return mCollection;
    }

    public String getDescriptor()
    {
        return mDescriptor;
    }

    public String getLabel()
    {
        return mLabel;
    }

    public LocaleList getLocales()
    {
        return mLocales;
    }

    public int getProductId()
    {
        return mProductId;
    }

    public int getVendorId()
    {
        return mVendorId;
    }

    public String toString()
    {
        if(mCollection.isEmpty())
            return mLabel;
        else
            return (new StringBuilder()).append(mLabel).append(" - ").append(mCollection).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mDescriptor);
        parcel.writeString(mLabel);
        parcel.writeString(mCollection);
        parcel.writeInt(mPriority);
        mLocales.writeToParcel(parcel, 0);
        parcel.writeInt(mVendorId);
        parcel.writeInt(mProductId);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public KeyboardLayout createFromParcel(Parcel parcel)
        {
            return new KeyboardLayout(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public KeyboardLayout[] newArray(int i)
        {
            return new KeyboardLayout[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final String mCollection;
    private final String mDescriptor;
    private final String mLabel;
    private final LocaleList mLocales;
    private final int mPriority;
    private final int mProductId;
    private final int mVendorId;

}
