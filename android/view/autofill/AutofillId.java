// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.autofill;

import android.os.Parcel;
import android.os.Parcelable;

public final class AutofillId
    implements Parcelable
{

    public AutofillId(int i)
    {
        mVirtual = false;
        mViewId = i;
        mVirtualId = -1;
    }

    public AutofillId(int i, int j)
    {
        mVirtual = true;
        mViewId = i;
        mVirtualId = j;
    }

    private AutofillId(Parcel parcel)
    {
        boolean flag = true;
        super();
        mViewId = parcel.readInt();
        if(parcel.readInt() != 1)
            flag = false;
        mVirtual = flag;
        mVirtualId = parcel.readInt();
    }

    AutofillId(Parcel parcel, AutofillId autofillid)
    {
        this(parcel);
    }

    public AutofillId(AutofillId autofillid, int i)
    {
        mVirtual = true;
        mViewId = autofillid.mViewId;
        mVirtualId = i;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        obj = (AutofillId)obj;
        if(mViewId != ((AutofillId) (obj)).mViewId)
            return false;
        return mVirtualId == ((AutofillId) (obj)).mVirtualId;
    }

    public int getViewId()
    {
        return mViewId;
    }

    public int getVirtualChildId()
    {
        return mVirtualId;
    }

    public int hashCode()
    {
        return (mViewId + 31) * 31 + mVirtualId;
    }

    public boolean isVirtual()
    {
        return mVirtual;
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder()).append(mViewId);
        if(mVirtual)
            stringbuilder.append(':').append(mVirtualId);
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mViewId);
        if(mVirtual)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(mVirtualId);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public AutofillId createFromParcel(Parcel parcel)
        {
            return new AutofillId(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public AutofillId[] newArray(int i)
        {
            return new AutofillId[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final int mViewId;
    private final boolean mVirtual;
    private final int mVirtualId;

}
