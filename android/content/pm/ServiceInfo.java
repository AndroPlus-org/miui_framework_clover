// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Printer;

// Referenced classes of package android.content.pm:
//            ComponentInfo

public class ServiceInfo extends ComponentInfo
    implements Parcelable
{

    public ServiceInfo()
    {
    }

    public ServiceInfo(ServiceInfo serviceinfo)
    {
        super(serviceinfo);
        permission = serviceinfo.permission;
        flags = serviceinfo.flags;
    }

    private ServiceInfo(Parcel parcel)
    {
        super(parcel);
        permission = parcel.readString();
        flags = parcel.readInt();
    }

    ServiceInfo(Parcel parcel, ServiceInfo serviceinfo)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public void dump(Printer printer, String s)
    {
        dump(printer, s, 3);
    }

    void dump(Printer printer, String s, int i)
    {
        super.dumpFront(printer, s);
        printer.println((new StringBuilder()).append(s).append("permission=").append(permission).toString());
        printer.println((new StringBuilder()).append(s).append("flags=0x").append(Integer.toHexString(flags)).toString());
        super.dumpBack(printer, s, i);
    }

    public String toString()
    {
        return (new StringBuilder()).append("ServiceInfo{").append(Integer.toHexString(System.identityHashCode(this))).append(" ").append(name).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        parcel.writeString(permission);
        parcel.writeInt(flags);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ServiceInfo createFromParcel(Parcel parcel)
        {
            return new ServiceInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ServiceInfo[] newArray(int i)
        {
            return new ServiceInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int FLAG_EXTERNAL_SERVICE = 4;
    public static final int FLAG_ISOLATED_PROCESS = 2;
    public static final int FLAG_SINGLE_USER = 0x40000000;
    public static final int FLAG_STOP_WITH_TASK = 1;
    public static final int FLAG_VISIBLE_TO_INSTANT_APP = 0x100000;
    public int flags;
    public String permission;

}
