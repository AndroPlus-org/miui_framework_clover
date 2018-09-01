// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.ComponentName;
import android.os.Parcel;
import android.os.Parcelable;
import java.io.PrintWriter;

public class WaitResult
    implements Parcelable
{

    public WaitResult()
    {
    }

    private WaitResult(Parcel parcel)
    {
        boolean flag = false;
        super();
        result = parcel.readInt();
        if(parcel.readInt() != 0)
            flag = true;
        timeout = flag;
        who = ComponentName.readFromParcel(parcel);
        thisTime = parcel.readLong();
        totalTime = parcel.readLong();
    }

    WaitResult(Parcel parcel, WaitResult waitresult)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public void dump(PrintWriter printwriter, String s)
    {
        printwriter.println((new StringBuilder()).append(s).append("WaitResult:").toString());
        printwriter.println((new StringBuilder()).append(s).append("  result=").append(result).toString());
        printwriter.println((new StringBuilder()).append(s).append("  timeout=").append(timeout).toString());
        printwriter.println((new StringBuilder()).append(s).append("  who=").append(who).toString());
        printwriter.println((new StringBuilder()).append(s).append("  thisTime=").append(thisTime).toString());
        printwriter.println((new StringBuilder()).append(s).append("  totalTime=").append(totalTime).toString());
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(result);
        if(timeout)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        ComponentName.writeToParcel(who, parcel);
        parcel.writeLong(thisTime);
        parcel.writeLong(totalTime);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WaitResult createFromParcel(Parcel parcel)
        {
            return new WaitResult(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WaitResult[] newArray(int i)
        {
            return new WaitResult[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public int result;
    public long thisTime;
    public boolean timeout;
    public long totalTime;
    public ComponentName who;

}
