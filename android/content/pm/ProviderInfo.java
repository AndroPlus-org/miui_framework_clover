// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.os.*;
import android.util.Printer;

// Referenced classes of package android.content.pm:
//            ComponentInfo, PathPermission

public final class ProviderInfo extends ComponentInfo
    implements Parcelable
{

    public ProviderInfo()
    {
        authority = null;
        readPermission = null;
        writePermission = null;
        grantUriPermissions = false;
        uriPermissionPatterns = null;
        pathPermissions = null;
        multiprocess = false;
        initOrder = 0;
        flags = 0;
        isSyncable = false;
    }

    public ProviderInfo(ProviderInfo providerinfo)
    {
        super(providerinfo);
        authority = null;
        readPermission = null;
        writePermission = null;
        grantUriPermissions = false;
        uriPermissionPatterns = null;
        pathPermissions = null;
        multiprocess = false;
        initOrder = 0;
        flags = 0;
        isSyncable = false;
        authority = providerinfo.authority;
        readPermission = providerinfo.readPermission;
        writePermission = providerinfo.writePermission;
        grantUriPermissions = providerinfo.grantUriPermissions;
        uriPermissionPatterns = providerinfo.uriPermissionPatterns;
        pathPermissions = providerinfo.pathPermissions;
        multiprocess = providerinfo.multiprocess;
        initOrder = providerinfo.initOrder;
        flags = providerinfo.flags;
        isSyncable = providerinfo.isSyncable;
    }

    private ProviderInfo(Parcel parcel)
    {
        boolean flag = true;
        super(parcel);
        authority = null;
        readPermission = null;
        writePermission = null;
        grantUriPermissions = false;
        uriPermissionPatterns = null;
        pathPermissions = null;
        multiprocess = false;
        initOrder = 0;
        flags = 0;
        isSyncable = false;
        authority = parcel.readString();
        readPermission = parcel.readString();
        writePermission = parcel.readString();
        boolean flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        grantUriPermissions = flag1;
        uriPermissionPatterns = (PatternMatcher[])parcel.createTypedArray(PatternMatcher.CREATOR);
        pathPermissions = (PathPermission[])parcel.createTypedArray(PathPermission.CREATOR);
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        multiprocess = flag1;
        initOrder = parcel.readInt();
        flags = parcel.readInt();
        if(parcel.readInt() != 0)
            flag1 = flag;
        else
            flag1 = false;
        isSyncable = flag1;
    }

    ProviderInfo(Parcel parcel, ProviderInfo providerinfo)
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

    public void dump(Printer printer, String s, int i)
    {
        super.dumpFront(printer, s);
        printer.println((new StringBuilder()).append(s).append("authority=").append(authority).toString());
        printer.println((new StringBuilder()).append(s).append("flags=0x").append(Integer.toHexString(flags)).toString());
        super.dumpBack(printer, s, i);
    }

    public String toString()
    {
        return (new StringBuilder()).append("ContentProviderInfo{name=").append(authority).append(" className=").append(name).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        super.writeToParcel(parcel, i);
        parcel.writeString(authority);
        parcel.writeString(readPermission);
        parcel.writeString(writePermission);
        int j;
        if(grantUriPermissions)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        parcel.writeTypedArray(uriPermissionPatterns, i);
        parcel.writeTypedArray(pathPermissions, i);
        if(multiprocess)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(initOrder);
        parcel.writeInt(flags);
        if(isSyncable)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ProviderInfo createFromParcel(Parcel parcel)
        {
            return new ProviderInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ProviderInfo[] newArray(int i)
        {
            return new ProviderInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int FLAG_SINGLE_USER = 0x40000000;
    public static final int FLAG_VISIBLE_TO_INSTANT_APP = 0x100000;
    public String authority;
    public int flags;
    public boolean grantUriPermissions;
    public int initOrder;
    public boolean isSyncable;
    public boolean multiprocess;
    public PathPermission pathPermissions[];
    public String readPermission;
    public PatternMatcher uriPermissionPatterns[];
    public String writePermission;

}
