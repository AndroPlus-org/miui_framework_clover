// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

public class ServiceStartArgs
    implements Parcelable
{

    public ServiceStartArgs(Parcel parcel)
    {
        boolean flag = false;
        super();
        if(parcel.readInt() != 0)
            flag = true;
        taskRemoved = flag;
        startId = parcel.readInt();
        flags = parcel.readInt();
        if(parcel.readInt() != 0)
            args = (Intent)Intent.CREATOR.createFromParcel(parcel);
        else
            args = null;
    }

    public ServiceStartArgs(boolean flag, int i, int j, Intent intent)
    {
        taskRemoved = flag;
        startId = i;
        flags = j;
        args = intent;
    }

    public int describeContents()
    {
        return 0;
    }

    public String toString()
    {
        return (new StringBuilder()).append("ServiceStartArgs{taskRemoved=").append(taskRemoved).append(", startId=").append(startId).append(", flags=0x").append(Integer.toHexString(flags)).append(", args=").append(args).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        int j;
        if(taskRemoved)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        parcel.writeInt(startId);
        parcel.writeInt(i);
        if(args != null)
        {
            parcel.writeInt(1);
            args.writeToParcel(parcel, 0);
        } else
        {
            parcel.writeInt(0);
        }
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ServiceStartArgs createFromParcel(Parcel parcel)
        {
            return new ServiceStartArgs(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ServiceStartArgs[] newArray(int i)
        {
            return new ServiceStartArgs[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public final Intent args;
    public final int flags;
    public final int startId;
    public final boolean taskRemoved;

}
