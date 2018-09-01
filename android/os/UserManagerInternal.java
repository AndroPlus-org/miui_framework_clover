// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.content.pm.UserInfo;
import android.graphics.Bitmap;

// Referenced classes of package android.os:
//            Bundle

public abstract class UserManagerInternal
{
    public static interface UserRestrictionsListener
    {

        public abstract void onUserRestrictionsChanged(int i, Bundle bundle, Bundle bundle1);
    }


    public UserManagerInternal()
    {
    }

    public abstract void addUserRestrictionsListener(UserRestrictionsListener userrestrictionslistener);

    public abstract UserInfo createUserEvenWhenDisallowed(String s, int i);

    public abstract Bundle getBaseUserRestrictions(int i);

    public abstract int[] getUserIds();

    public abstract boolean getUserRestriction(int i, String s);

    public abstract boolean isUserRunning(int i);

    public abstract boolean isUserUnlocked(int i);

    public abstract boolean isUserUnlockingOrUnlocked(int i);

    public abstract void onEphemeralUserStop(int i);

    public abstract void removeAllUsers();

    public abstract boolean removeUserEvenWhenDisallowed(int i);

    public abstract void removeUserRestrictionsListener(UserRestrictionsListener userrestrictionslistener);

    public abstract void removeUserState(int i);

    public abstract void setBaseUserRestrictionsByDpmsForMigration(int i, Bundle bundle);

    public abstract void setDeviceManaged(boolean flag);

    public abstract void setDevicePolicyUserRestrictions(int i, Bundle bundle, boolean flag, int j);

    public abstract void setForceEphemeralUsers(boolean flag);

    public abstract void setUserIcon(int i, Bitmap bitmap);

    public abstract void setUserManaged(int i, boolean flag);

    public abstract void setUserState(int i, int j);

    public static final int CAMERA_DISABLED_GLOBALLY = 2;
    public static final int CAMERA_DISABLED_LOCALLY = 1;
    public static final int CAMERA_NOT_DISABLED = 0;
}
