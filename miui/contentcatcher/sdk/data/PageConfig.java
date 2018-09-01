// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.contentcatcher.sdk.data;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public class PageConfig
    implements Parcelable
{

    public PageConfig()
    {
        mFeatures = new ArrayList();
        mCatchers = new ArrayList();
    }

    public PageConfig(Parcel parcel)
    {
        mFeatures = new ArrayList();
        mCatchers = new ArrayList();
        mFeatures = parcel.readArrayList(ClassLoader.getSystemClassLoader());
        mCatchers = parcel.readArrayList(ClassLoader.getSystemClassLoader());
    }

    public PageConfig(ArrayList arraylist, ArrayList arraylist1)
    {
        mFeatures = new ArrayList();
        mCatchers = new ArrayList();
        mFeatures = arraylist;
        mCatchers = arraylist1;
    }

    public int describeContents()
    {
        return 0;
    }

    public ArrayList getCatchers()
    {
        return mCatchers;
    }

    public ArrayList getFeatures()
    {
        return mFeatures;
    }

    public void setCatchers(ArrayList arraylist)
    {
        mCatchers = arraylist;
    }

    public void setFeatures(ArrayList arraylist)
    {
        mFeatures = arraylist;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeList(mFeatures);
        parcel.writeList(mCatchers);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PageConfig createFromParcel(Parcel parcel)
        {
            return new PageConfig(parcel);
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

        public PageConfig[] newArray(int i)
        {
            return new PageConfig[i];
        }

    }
;
    private ArrayList mCatchers;
    private ArrayList mFeatures;

}
