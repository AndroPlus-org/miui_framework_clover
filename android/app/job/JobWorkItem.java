// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.job;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

public final class JobWorkItem
    implements Parcelable
{

    public JobWorkItem(Intent intent)
    {
        mIntent = intent;
    }

    JobWorkItem(Parcel parcel)
    {
        if(parcel.readInt() != 0)
            mIntent = (Intent)Intent.CREATOR.createFromParcel(parcel);
        else
            mIntent = null;
        mDeliveryCount = parcel.readInt();
        mWorkId = parcel.readInt();
    }

    public void bumpDeliveryCount()
    {
        mDeliveryCount = mDeliveryCount + 1;
    }

    public int describeContents()
    {
        return 0;
    }

    public int getDeliveryCount()
    {
        return mDeliveryCount;
    }

    public Object getGrants()
    {
        return mGrants;
    }

    public Intent getIntent()
    {
        return mIntent;
    }

    public int getWorkId()
    {
        return mWorkId;
    }

    public void setGrants(Object obj)
    {
        mGrants = obj;
    }

    public void setWorkId(int i)
    {
        mWorkId = i;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(64);
        stringbuilder.append("JobWorkItem{id=");
        stringbuilder.append(mWorkId);
        stringbuilder.append(" intent=");
        stringbuilder.append(mIntent);
        if(mDeliveryCount != 0)
        {
            stringbuilder.append(" dcount=");
            stringbuilder.append(mDeliveryCount);
        }
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if(mIntent != null)
        {
            parcel.writeInt(1);
            mIntent.writeToParcel(parcel, 0);
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeInt(mDeliveryCount);
        parcel.writeInt(mWorkId);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public JobWorkItem createFromParcel(Parcel parcel)
        {
            return new JobWorkItem(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public JobWorkItem[] newArray(int i)
        {
            return new JobWorkItem[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    int mDeliveryCount;
    Object mGrants;
    final Intent mIntent;
    int mWorkId;

}
