// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

// Referenced classes of package android.content.pm:
//            PackageItemInfo, PackageManager

public class PermissionInfo extends PackageItemInfo
    implements Parcelable
{

    public PermissionInfo()
    {
    }

    public PermissionInfo(PermissionInfo permissioninfo)
    {
        super(permissioninfo);
        protectionLevel = permissioninfo.protectionLevel;
        flags = permissioninfo.flags;
        group = permissioninfo.group;
        descriptionRes = permissioninfo.descriptionRes;
        nonLocalizedDescription = permissioninfo.nonLocalizedDescription;
    }

    private PermissionInfo(Parcel parcel)
    {
        super(parcel);
        protectionLevel = parcel.readInt();
        flags = parcel.readInt();
        group = parcel.readString();
        descriptionRes = parcel.readInt();
        nonLocalizedDescription = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
    }

    PermissionInfo(Parcel parcel, PermissionInfo permissioninfo)
    {
        this(parcel);
    }

    public static int fixProtectionLevel(int i)
    {
        int j = i;
        if(i == 3)
            j = 18;
        return j;
    }

    public static String protectionToString(int i)
    {
        String s = "????";
        i & 0xf;
        JVM INSTR tableswitch 0 3: default 36
    //                   0 341
    //                   1 335
    //                   2 347
    //                   3 353;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        String s1 = s;
        if((i & 0x10) != 0)
            s1 = (new StringBuilder()).append(s).append("|privileged").toString();
        s = s1;
        if((i & 0x20) != 0)
            s = (new StringBuilder()).append(s1).append("|development").toString();
        s1 = s;
        if((i & 0x40) != 0)
            s1 = (new StringBuilder()).append(s).append("|appop").toString();
        s = s1;
        if((i & 0x80) != 0)
            s = (new StringBuilder()).append(s1).append("|pre23").toString();
        s1 = s;
        if((i & 0x100) != 0)
            s1 = (new StringBuilder()).append(s).append("|installer").toString();
        String s2 = s1;
        if((i & 0x200) != 0)
            s2 = (new StringBuilder()).append(s1).append("|verifier").toString();
        s = s2;
        if((i & 0x400) != 0)
            s = (new StringBuilder()).append(s2).append("|preinstalled").toString();
        s1 = s;
        if((i & 0x800) != 0)
            s1 = (new StringBuilder()).append(s).append("|setup").toString();
        s = s1;
        if((i & 0x1000) != 0)
            s = (new StringBuilder()).append(s1).append("|instant").toString();
        s1 = s;
        if((i & 0x2000) != 0)
            s1 = (new StringBuilder()).append(s).append("|runtime").toString();
        return s1;
_L3:
        s = "dangerous";
        continue; /* Loop/switch isn't completed */
_L2:
        s = "normal";
        continue; /* Loop/switch isn't completed */
_L4:
        s = "signature";
        continue; /* Loop/switch isn't completed */
_L5:
        s = "signatureOrSystem";
        if(true) goto _L1; else goto _L6
_L6:
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
        return (new StringBuilder()).append("PermissionInfo{").append(Integer.toHexString(System.identityHashCode(this))).append(" ").append(name).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        parcel.writeInt(protectionLevel);
        parcel.writeInt(flags);
        parcel.writeString(group);
        parcel.writeInt(descriptionRes);
        TextUtils.writeToParcel(nonLocalizedDescription, parcel, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PermissionInfo createFromParcel(Parcel parcel)
        {
            return new PermissionInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PermissionInfo[] newArray(int i)
        {
            return new PermissionInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int FLAG_COSTS_MONEY = 1;
    public static final int FLAG_INSTALLED = 0x40000000;
    public static final int FLAG_REMOVED = 2;
    public static final int PROTECTION_DANGEROUS = 1;
    public static final int PROTECTION_FLAG_APPOP = 64;
    public static final int PROTECTION_FLAG_DEVELOPMENT = 32;
    public static final int PROTECTION_FLAG_INSTALLER = 256;
    public static final int PROTECTION_FLAG_INSTANT = 4096;
    public static final int PROTECTION_FLAG_PRE23 = 128;
    public static final int PROTECTION_FLAG_PREINSTALLED = 1024;
    public static final int PROTECTION_FLAG_PRIVILEGED = 16;
    public static final int PROTECTION_FLAG_RUNTIME_ONLY = 8192;
    public static final int PROTECTION_FLAG_SETUP = 2048;
    public static final int PROTECTION_FLAG_SYSTEM = 16;
    public static final int PROTECTION_FLAG_VERIFIER = 512;
    public static final int PROTECTION_MASK_BASE = 15;
    public static final int PROTECTION_MASK_FLAGS = 65520;
    public static final int PROTECTION_NORMAL = 0;
    public static final int PROTECTION_SIGNATURE = 2;
    public static final int PROTECTION_SIGNATURE_OR_SYSTEM = 3;
    public int descriptionRes;
    public int flags;
    public String group;
    public CharSequence nonLocalizedDescription;
    public int protectionLevel;

}
