// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.os.*;

public class UserInfo
    implements Parcelable
{

    public UserInfo()
    {
    }

    public UserInfo(int i, String s, int j)
    {
        this(i, s, null, j);
    }

    public UserInfo(int i, String s, String s1, int j)
    {
        id = i;
        name = s;
        flags = j;
        iconPath = s1;
        profileGroupId = -10000;
        restrictedProfileParentId = -10000;
    }

    public UserInfo(UserInfo userinfo)
    {
        name = userinfo.name;
        iconPath = userinfo.iconPath;
        id = userinfo.id;
        flags = userinfo.flags;
        serialNumber = userinfo.serialNumber;
        creationTime = userinfo.creationTime;
        lastLoggedInTime = userinfo.lastLoggedInTime;
        lastLoggedInFingerprint = userinfo.lastLoggedInFingerprint;
        partial = userinfo.partial;
        profileGroupId = userinfo.profileGroupId;
        restrictedProfileParentId = userinfo.restrictedProfileParentId;
        guestToRemove = userinfo.guestToRemove;
        profileBadge = userinfo.profileBadge;
    }

    private UserInfo(Parcel parcel)
    {
        boolean flag = true;
        super();
        id = parcel.readInt();
        name = parcel.readString();
        iconPath = parcel.readString();
        flags = parcel.readInt();
        serialNumber = parcel.readInt();
        creationTime = parcel.readLong();
        lastLoggedInTime = parcel.readLong();
        lastLoggedInFingerprint = parcel.readString();
        boolean flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        partial = flag1;
        profileGroupId = parcel.readInt();
        if(parcel.readInt() != 0)
            flag1 = flag;
        else
            flag1 = false;
        guestToRemove = flag1;
        restrictedProfileParentId = parcel.readInt();
        profileBadge = parcel.readInt();
    }

    UserInfo(Parcel parcel, UserInfo userinfo)
    {
        this(parcel);
    }

    public static boolean isSystemOnly(int i)
    {
        boolean flag = false;
        if(i == 0)
            flag = UserManager.isSplitSystemUser();
        return flag;
    }

    public boolean canHaveProfile()
    {
        boolean flag = true;
        boolean flag1 = true;
        if(isManagedProfile() || isGuest() || isRestricted())
            return false;
        if(UserManager.isSplitSystemUser())
        {
            if(id == 0)
                flag1 = false;
            return flag1;
        }
        if(id == 0)
            flag1 = flag;
        else
            flag1 = false;
        return flag1;
    }

    public int describeContents()
    {
        return 0;
    }

    public UserHandle getUserHandle()
    {
        return new UserHandle(id);
    }

    public boolean isAdmin()
    {
        boolean flag;
        if((flags & 2) == 2)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isAirSpace()
    {
        boolean flag;
        if((flags & 0x400000) == 0x400000)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isDemo()
    {
        boolean flag;
        if((flags & 0x200) == 512)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isEnabled()
    {
        boolean flag;
        if((flags & 0x40) != 64)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isEphemeral()
    {
        boolean flag;
        if((flags & 0x100) == 256)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isGuest()
    {
        boolean flag;
        if((flags & 4) == 4)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isInitialized()
    {
        boolean flag;
        if((flags & 0x10) == 16)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isManagedProfile()
    {
        boolean flag;
        if((flags & 0x20) == 32)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isPrimary()
    {
        boolean flag = true;
        if((flags & 1) != 1)
            flag = false;
        return flag;
    }

    public boolean isQuietModeEnabled()
    {
        boolean flag;
        if((flags & 0x80) == 128)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isRestricted()
    {
        boolean flag;
        if((flags & 8) == 8)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isSystemOnly()
    {
        return isSystemOnly(id);
    }

    public boolean supportsSwitchTo()
    {
        if(isEphemeral() && isEnabled() ^ true)
            return false;
        else
            return isManagedProfile() ^ true;
    }

    public boolean supportsSwitchToByUser()
    {
        boolean flag = false;
        if(!UserManager.isSplitSystemUser() || id != 0)
            flag = supportsSwitchTo();
        return flag;
    }

    public String toString()
    {
        return (new StringBuilder()).append("UserInfo{").append(id).append(":").append(name).append(":").append(Integer.toHexString(flags)).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(iconPath);
        parcel.writeInt(flags);
        parcel.writeInt(serialNumber);
        parcel.writeLong(creationTime);
        parcel.writeLong(lastLoggedInTime);
        parcel.writeString(lastLoggedInFingerprint);
        if(partial)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(profileGroupId);
        if(guestToRemove)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(restrictedProfileParentId);
        parcel.writeInt(profileBadge);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public UserInfo createFromParcel(Parcel parcel)
        {
            return new UserInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public UserInfo[] newArray(int i)
        {
            return new UserInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int FLAG_ADMIN = 2;
    public static final int FLAG_AIR_SPACE = 0x400000;
    public static final int FLAG_DEMO = 512;
    public static final int FLAG_DISABLED = 64;
    public static final int FLAG_EPHEMERAL = 256;
    public static final int FLAG_GUEST = 4;
    public static final int FLAG_INITIALIZED = 16;
    public static final int FLAG_MANAGED_PROFILE = 32;
    public static final int FLAG_MASK_USER_TYPE = 65535;
    public static final int FLAG_PRIMARY = 1;
    public static final int FLAG_QUIET_MODE = 128;
    public static final int FLAG_RESTRICTED = 8;
    public static final int FLAG_XSPACE_PROFILE = 0x800000;
    public static final int FLAG__MASK_XSPACE_PROFILE_TYPE = 0xf00000;
    public static final int NO_PROFILE_GROUP_ID = -10000;
    public long creationTime;
    public int flags;
    public boolean guestToRemove;
    public String iconPath;
    public int id;
    public String lastLoggedInFingerprint;
    public long lastLoggedInTime;
    public String name;
    public boolean partial;
    public int profileBadge;
    public int profileGroupId;
    public int restrictedProfileParentId;
    public int serialNumber;

}
