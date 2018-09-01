// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

// Referenced classes of package android.content.pm:
//            PackageItemInfo, PackageManager

public class PermissionGroupInfo extends PackageItemInfo
    implements Parcelable
{

    public PermissionGroupInfo()
    {
    }

    public PermissionGroupInfo(PermissionGroupInfo permissiongroupinfo)
    {
        super(permissiongroupinfo);
        descriptionRes = permissiongroupinfo.descriptionRes;
        nonLocalizedDescription = permissiongroupinfo.nonLocalizedDescription;
        flags = permissiongroupinfo.flags;
        priority = permissiongroupinfo.priority;
    }

    private PermissionGroupInfo(Parcel parcel)
    {
        super(parcel);
        descriptionRes = parcel.readInt();
        nonLocalizedDescription = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        flags = parcel.readInt();
        priority = parcel.readInt();
    }

    PermissionGroupInfo(Parcel parcel, PermissionGroupInfo permissiongroupinfo)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public CharSequence loadDescription(PackageManager packagemanager)
    {
        if(nonLocalizedDescription != null)
            return nonLocalizedDescription;
        if(descriptionRes != 0)
        {
            packagemanager = packagemanager.getText(packageName, descriptionRes, null);
            if(packagemanager != null)
                return packagemanager;
        }
        return null;
    }

    public String toString()
    {
        return (new StringBuilder()).append("PermissionGroupInfo{").append(Integer.toHexString(System.identityHashCode(this))).append(" ").append(name).append(" flgs=0x").append(Integer.toHexString(flags)).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        parcel.writeInt(descriptionRes);
        TextUtils.writeToParcel(nonLocalizedDescription, parcel, i);
        parcel.writeInt(flags);
        parcel.writeInt(priority);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PermissionGroupInfo createFromParcel(Parcel parcel)
        {
            return new PermissionGroupInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PermissionGroupInfo[] newArray(int i)
        {
            return new PermissionGroupInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int FLAG_PERSONAL_INFO = 1;
    public int descriptionRes;
    public int flags;
    public CharSequence nonLocalizedDescription;
    public int priority;

}
