// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.accounts.AccountManager;
import android.app.ActivityManager;
import android.app.IActivityManager;
import android.app.admin.DevicePolicyManager;
import android.content.*;
import android.content.pm.PackageManager;
import android.content.pm.UserInfo;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.telephony.TelephonyManager;
import com.android.internal.os.RoSystemProperties;
import java.io.IOException;
import java.util.*;

// Referenced classes of package android.os:
//            Build, SystemProperties, RemoteException, IUserManager, 
//            UserHandle, Process, ParcelFileDescriptor, PersistableBundle, 
//            Bundle, Parcelable, Parcel

public class UserManager
{
    public static final class EnforcingUser
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public UserHandle getUserHandle()
        {
            return UserHandle.of(userId);
        }

        public int getUserRestrictionSource()
        {
            return userRestrictionSource;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(userId);
            parcel.writeInt(userRestrictionSource);
        }

        public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

            public EnforcingUser createFromParcel(Parcel parcel)
            {
                return new EnforcingUser(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public EnforcingUser[] newArray(int i)
            {
                return new EnforcingUser[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private final int userId;
        private final int userRestrictionSource;


        public EnforcingUser(int i, int j)
        {
            userId = i;
            userRestrictionSource = j;
        }

        private EnforcingUser(Parcel parcel)
        {
            userId = parcel.readInt();
            userRestrictionSource = parcel.readInt();
        }

        EnforcingUser(Parcel parcel, EnforcingUser enforcinguser)
        {
            this(parcel);
        }
    }


    public UserManager(Context context, IUserManager iusermanager)
    {
        mService = iusermanager;
        mContext = context.getApplicationContext();
    }

    public static Intent createUserCreationIntent(String s, String s1, String s2, PersistableBundle persistablebundle)
    {
        Intent intent = new Intent("android.os.action.CREATE_USER");
        if(s != null)
            intent.putExtra("android.os.extra.USER_NAME", s);
        if(s1 != null && s2 == null)
            throw new IllegalArgumentException("accountType must be specified if accountName is specified");
        if(s1 != null)
            intent.putExtra("android.os.extra.USER_ACCOUNT_NAME", s1);
        if(s2 != null)
            intent.putExtra("android.os.extra.USER_ACCOUNT_TYPE", s2);
        if(persistablebundle != null)
            intent.putExtra("android.os.extra.USER_ACCOUNT_OPTIONS", persistablebundle);
        return intent;
    }

    public static UserManager get(Context context)
    {
        return (UserManager)context.getSystemService("user");
    }

    public static int getMaxSupportedUsers()
    {
        if(Build.ID.startsWith("JVP"))
            return 1;
        if(ActivityManager.isLowRamDeviceStatic())
            return 1;
        else
            return SystemProperties.getInt("fw.max_users", Resources.getSystem().getInteger(0x10e0074));
    }

    public static boolean isDeviceInDemoMode(Context context)
    {
        boolean flag = false;
        if(android.provider.Settings.Global.getInt(context.getContentResolver(), "device_demo_mode", 0) > 0)
            flag = true;
        return flag;
    }

    public static boolean isGuestUserEphemeral()
    {
        return Resources.getSystem().getBoolean(0x1120072);
    }

    public static boolean isSplitSystemUser()
    {
        return RoSystemProperties.FW_SYSTEM_USER_SPLIT;
    }

    public static boolean supportsMultipleUsers()
    {
        boolean flag;
        if(getMaxSupportedUsers() > 1)
            flag = SystemProperties.getBoolean("fw.show_multiuserui", Resources.getSystem().getBoolean(0x1120063));
        else
            flag = false;
        return flag;
    }

    public boolean canAddMoreManagedProfiles(int i, boolean flag)
    {
        try
        {
            flag = mService.canAddMoreManagedProfiles(i, flag);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean canAddMoreUsers()
    {
        boolean flag = true;
        List list = getUsers(true);
        int i = list.size();
        int j = 0;
        for(int k = 0; k < i;)
        {
            int l = j;
            if(!((UserInfo)list.get(k)).isGuest())
                l = j + 1;
            k++;
            j = l;
        }

        if(j >= getMaxSupportedUsers())
            flag = false;
        return flag;
    }

    public boolean canHaveRestrictedProfile(int i)
    {
        boolean flag;
        try
        {
            flag = mService.canHaveRestrictedProfile(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean canSwitchUsers()
    {
        boolean flag = false;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        if(android.provider.Settings.Global.getInt(mContext.getContentResolver(), "allow_user_switching_when_system_user_locked", 0) != 0)
            flag1 = true;
        else
            flag1 = false;
        flag2 = isUserUnlocked(UserHandle.SYSTEM);
        if(TelephonyManager.getDefault().getCallState() != 0)
            flag3 = true;
        else
            flag3 = false;
        if(flag1 || flag2)
            flag = flag3 ^ true;
        return flag;
    }

    public void clearSeedAccountData()
    {
        try
        {
            mService.clearSeedAccountData();
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public UserInfo createGuest(Context context, String s)
    {
        try
        {
            s = mService.createUser(s, 4);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw context.rethrowFromSystemServer();
        }
        if(s == null)
            break MISSING_BLOCK_LABEL_34;
        android.provider.Settings.Secure.putStringForUser(context.getContentResolver(), "skip_first_use_hints", "1", ((UserInfo) (s)).id);
        return s;
    }

    public UserInfo createProfileForUser(String s, int i, int j)
    {
        return createProfileForUser(s, i, j, null);
    }

    public UserInfo createProfileForUser(String s, int i, int j, String as[])
    {
        try
        {
            s = mService.createProfileForUser(s, i, j, as);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return s;
    }

    public UserInfo createProfileForUserEvenWhenDisallowed(String s, int i, int j, String as[])
    {
        try
        {
            s = mService.createProfileForUserEvenWhenDisallowed(s, i, j, as);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return s;
    }

    public UserInfo createRestrictedProfile(String s)
    {
        UserHandle userhandle;
        try
        {
            userhandle = Process.myUserHandle();
            s = mService.createRestrictedProfile(s, userhandle.getIdentifier());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        if(s == null)
            break MISSING_BLOCK_LABEL_41;
        AccountManager.get(mContext).addSharedAccountsFromParentUser(userhandle, UserHandle.of(((UserInfo) (s)).id));
        return s;
    }

    public UserInfo createUser(String s, int i)
    {
        try
        {
            s = mService.createUser(s, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        if(s == null)
            break MISSING_BLOCK_LABEL_66;
        if(s.isAdmin() ^ true && s.isDemo() ^ true)
        {
            mService.setUserRestriction("no_sms", true, ((UserInfo) (s)).id);
            mService.setUserRestriction("no_outgoing_calls", true, ((UserInfo) (s)).id);
        }
        return s;
    }

    public void evictCredentialEncryptionKey(int i)
    {
        try
        {
            mService.evictCredentialEncryptionKey(i);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public Bundle getApplicationRestrictions(String s)
    {
        try
        {
            s = mService.getApplicationRestrictions(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return s;
    }

    public Bundle getApplicationRestrictions(String s, UserHandle userhandle)
    {
        try
        {
            s = mService.getApplicationRestrictionsForUser(s, userhandle.getIdentifier());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return s;
    }

    public Drawable getBadgedDrawableForUser(Drawable drawable, UserHandle userhandle, Rect rect, int i)
    {
        return mContext.getPackageManager().getUserBadgedDrawableForDensity(drawable, userhandle, rect, i);
    }

    public Drawable getBadgedIconForUser(Drawable drawable, UserHandle userhandle)
    {
        return mContext.getPackageManager().getUserBadgedIcon(drawable, userhandle);
    }

    public CharSequence getBadgedLabelForUser(CharSequence charsequence, UserHandle userhandle)
    {
        return mContext.getPackageManager().getUserBadgedLabel(charsequence, userhandle);
    }

    public int getCredentialOwnerProfile(int i)
    {
        try
        {
            i = mService.getCredentialOwnerProfile(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public Bundle getDefaultGuestRestrictions()
    {
        Bundle bundle;
        try
        {
            bundle = mService.getDefaultGuestRestrictions();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return bundle;
    }

    public int[] getEnabledProfileIds(int i)
    {
        return getProfileIds(i, true);
    }

    public List getEnabledProfiles(int i)
    {
        List list;
        try
        {
            list = mService.getProfiles(i, true);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return list;
    }

    public int getManagedProfileBadge(int i)
    {
        try
        {
            i = mService.getManagedProfileBadge(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public UserInfo getPrimaryUser()
    {
        UserInfo userinfo;
        try
        {
            userinfo = mService.getPrimaryUser();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return userinfo;
    }

    public int[] getProfileIds(int i, boolean flag)
    {
        int ai[];
        try
        {
            ai = mService.getProfileIds(i, flag);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return ai;
    }

    public int[] getProfileIdsWithDisabled(int i)
    {
        return getProfileIds(i, false);
    }

    public UserInfo getProfileParent(int i)
    {
        UserInfo userinfo;
        try
        {
            userinfo = mService.getProfileParent(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return userinfo;
    }

    public List getProfiles(int i)
    {
        List list;
        try
        {
            list = mService.getProfiles(i, false);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return list;
    }

    public String getSeedAccountName()
    {
        String s;
        try
        {
            s = mService.getSeedAccountName();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return s;
    }

    public PersistableBundle getSeedAccountOptions()
    {
        PersistableBundle persistablebundle;
        try
        {
            persistablebundle = mService.getSeedAccountOptions();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return persistablebundle;
    }

    public String getSeedAccountType()
    {
        String s;
        try
        {
            s = mService.getSeedAccountType();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return s;
    }

    public long getSerialNumberForUser(UserHandle userhandle)
    {
        return (long)getUserSerialNumber(userhandle.getIdentifier());
    }

    public long[] getSerialNumbersOfUsers(boolean flag)
    {
        List list;
        long al[];
        int i;
        try
        {
            list = mService.getUsers(flag);
            al = new long[list.size()];
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        i = 0;
        if(i >= al.length)
            break; /* Loop/switch isn't completed */
        al[i] = ((UserInfo)list.get(i)).serialNumber;
        i++;
        if(true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_23;
_L1:
        return al;
    }

    public String getUserAccount(int i)
    {
        String s;
        try
        {
            s = mService.getUserAccount(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return s;
    }

    public int getUserCount()
    {
        List list = getUsers();
        int i;
        if(list != null)
            i = list.size();
        else
            i = 1;
        return i;
    }

    public long getUserCreationTime(UserHandle userhandle)
    {
        long l;
        try
        {
            l = mService.getUserCreationTime(userhandle.getIdentifier());
        }
        // Misplaced declaration of an exception variable
        catch(UserHandle userhandle)
        {
            throw userhandle.rethrowFromSystemServer();
        }
        return l;
    }

    public UserHandle getUserForSerialNumber(long l)
    {
        int i = getUserHandle((int)l);
        UserHandle userhandle;
        if(i >= 0)
            userhandle = new UserHandle(i);
        else
            userhandle = null;
        return userhandle;
    }

    public int getUserHandle()
    {
        return UserHandle.myUserId();
    }

    public int getUserHandle(int i)
    {
        try
        {
            i = mService.getUserHandle(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public Bitmap getUserIcon(int i)
    {
        Object obj;
        Bitmap bitmap;
        Exception exception;
        try
        {
            obj = mService.getUserIcon(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        if(obj == null)
            break MISSING_BLOCK_LABEL_50;
        bitmap = BitmapFactory.decodeFileDescriptor(((ParcelFileDescriptor) (obj)).getFileDescriptor());
        ((ParcelFileDescriptor) (obj)).close();
_L2:
        return bitmap;
        obj;
        if(true) goto _L2; else goto _L1
_L1:
        exception;
        try
        {
            ((ParcelFileDescriptor) (obj)).close();
        }
        catch(IOException ioexception) { }
        throw exception;
        return null;
    }

    public UserInfo getUserInfo(int i)
    {
        UserInfo userinfo;
        try
        {
            userinfo = mService.getUserInfo(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return userinfo;
    }

    public String getUserName()
    {
        Object obj = getUserInfo(getUserHandle());
        if(obj == null)
            obj = "";
        else
            obj = ((UserInfo) (obj)).name;
        return ((String) (obj));
    }

    public List getUserProfiles()
    {
        int ai[] = getProfileIds(UserHandle.myUserId(), true);
        ArrayList arraylist = new ArrayList(ai.length);
        int i = 0;
        for(int j = ai.length; i < j; i++)
            arraylist.add(UserHandle.of(ai[i]));

        return arraylist;
    }

    public int getUserRestrictionSource(String s, UserHandle userhandle)
    {
        int i;
        try
        {
            i = mService.getUserRestrictionSource(s, userhandle.getIdentifier());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return i;
    }

    public List getUserRestrictionSources(String s, UserHandle userhandle)
    {
        try
        {
            s = mService.getUserRestrictionSources(s, userhandle.getIdentifier());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return s;
    }

    public Bundle getUserRestrictions()
    {
        return getUserRestrictions(Process.myUserHandle());
    }

    public Bundle getUserRestrictions(UserHandle userhandle)
    {
        try
        {
            userhandle = mService.getUserRestrictions(userhandle.getIdentifier());
        }
        // Misplaced declaration of an exception variable
        catch(UserHandle userhandle)
        {
            throw userhandle.rethrowFromSystemServer();
        }
        return userhandle;
    }

    public int getUserSerialNumber(int i)
    {
        try
        {
            i = mService.getUserSerialNumber(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public List getUsers()
    {
        List list;
        try
        {
            list = mService.getUsers(false);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return list;
    }

    public List getUsers(boolean flag)
    {
        List list;
        try
        {
            list = mService.getUsers(flag);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return list;
    }

    public boolean hasBaseUserRestriction(String s, UserHandle userhandle)
    {
        boolean flag;
        try
        {
            flag = mService.hasBaseUserRestriction(s, userhandle.getIdentifier());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean hasUserRestriction(String s)
    {
        return hasUserRestriction(s, Process.myUserHandle());
    }

    public boolean hasUserRestriction(String s, UserHandle userhandle)
    {
        boolean flag;
        try
        {
            flag = mService.hasUserRestriction(s, userhandle.getIdentifier());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isAdminUser()
    {
        return isUserAdmin(UserHandle.myUserId());
    }

    public boolean isDemoUser()
    {
        boolean flag;
        try
        {
            flag = mService.isDemoUser(UserHandle.myUserId());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isEphemeralUser()
    {
        return isUserEphemeral(UserHandle.myUserId());
    }

    public boolean isGuestUser()
    {
        UserInfo userinfo = getUserInfo(UserHandle.myUserId());
        boolean flag;
        if(userinfo != null)
            flag = userinfo.isGuest();
        else
            flag = false;
        return flag;
    }

    public boolean isGuestUser(int i)
    {
        UserInfo userinfo = getUserInfo(i);
        boolean flag;
        if(userinfo != null)
            flag = userinfo.isGuest();
        else
            flag = false;
        return flag;
    }

    public boolean isLinkedUser()
    {
        boolean flag;
        try
        {
            flag = mService.isRestricted();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isManagedProfile()
    {
        if(mIsManagedProfileCached != null)
            return mIsManagedProfileCached.booleanValue();
        boolean flag;
        try
        {
            mIsManagedProfileCached = Boolean.valueOf(mService.isManagedProfile(UserHandle.myUserId()));
            flag = mIsManagedProfileCached.booleanValue();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isManagedProfile(int i)
    {
        if(i == UserHandle.myUserId())
            return isManagedProfile();
        boolean flag;
        try
        {
            flag = mService.isManagedProfile(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isPrimaryUser()
    {
        UserInfo userinfo = getUserInfo(UserHandle.myUserId());
        boolean flag;
        if(userinfo != null)
            flag = userinfo.isPrimary();
        else
            flag = false;
        return flag;
    }

    public boolean isQuietModeEnabled(UserHandle userhandle)
    {
        boolean flag;
        try
        {
            flag = mService.isQuietModeEnabled(userhandle.getIdentifier());
        }
        // Misplaced declaration of an exception variable
        catch(UserHandle userhandle)
        {
            throw userhandle.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isSameProfileGroup(int i, int j)
    {
        boolean flag;
        try
        {
            flag = mService.isSameProfileGroup(i, j);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isSystemUser()
    {
        boolean flag = false;
        if(UserHandle.myUserId() == 0)
            flag = true;
        return flag;
    }

    public boolean isUserAGoat()
    {
        return mContext.getPackageManager().isPackageAvailable("com.coffeestainstudios.goatsimulator");
    }

    public boolean isUserAdmin(int i)
    {
        UserInfo userinfo = getUserInfo(i);
        boolean flag;
        if(userinfo != null)
            flag = userinfo.isAdmin();
        else
            flag = false;
        return flag;
    }

    public boolean isUserEphemeral(int i)
    {
        UserInfo userinfo = getUserInfo(i);
        boolean flag;
        if(userinfo != null)
            flag = userinfo.isEphemeral();
        else
            flag = false;
        return flag;
    }

    public boolean isUserNameSet()
    {
        boolean flag;
        try
        {
            flag = mService.isUserNameSet(getUserHandle());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isUserRunning(int i)
    {
        boolean flag;
        try
        {
            flag = mService.isUserRunning(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isUserRunning(UserHandle userhandle)
    {
        return isUserRunning(userhandle.getIdentifier());
    }

    public boolean isUserRunningOrStopping(UserHandle userhandle)
    {
        boolean flag;
        try
        {
            flag = ActivityManager.getService().isUserRunning(userhandle.getIdentifier(), 1);
        }
        // Misplaced declaration of an exception variable
        catch(UserHandle userhandle)
        {
            throw userhandle.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isUserSwitcherEnabled()
    {
        if(!supportsMultipleUsers())
            return false;
        if(isDeviceInDemoMode(mContext))
            return false;
        Object obj = getUsers(true);
        if(obj == null)
            return false;
        int i = 0;
        obj = ((Iterable) (obj)).iterator();
        do
        {
            if(!((Iterator) (obj)).hasNext())
                break;
            if(((UserInfo)((Iterator) (obj)).next()).supportsSwitchToByUser())
                i++;
        } while(true);
        boolean flag = ((DevicePolicyManager)mContext.getSystemService(android/app/admin/DevicePolicyManager)).getGuestUserDisabled(null) ^ true;
        if(i > 1)
            flag = true;
        return flag;
    }

    public boolean isUserUnlocked()
    {
        return isUserUnlocked(Process.myUserHandle());
    }

    public boolean isUserUnlocked(int i)
    {
        boolean flag;
        try
        {
            flag = mService.isUserUnlocked(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isUserUnlocked(UserHandle userhandle)
    {
        return isUserUnlocked(userhandle.getIdentifier());
    }

    public boolean isUserUnlockingOrUnlocked(int i)
    {
        boolean flag;
        try
        {
            flag = mService.isUserUnlockingOrUnlocked(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isUserUnlockingOrUnlocked(UserHandle userhandle)
    {
        return isUserUnlockingOrUnlocked(userhandle.getIdentifier());
    }

    public boolean markGuestForDeletion(int i)
    {
        boolean flag;
        try
        {
            flag = mService.markGuestForDeletion(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean removeUser(int i)
    {
        boolean flag;
        try
        {
            flag = mService.removeUser(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean removeUserEvenWhenDisallowed(int i)
    {
        boolean flag;
        try
        {
            flag = mService.removeUserEvenWhenDisallowed(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public void setApplicationRestrictions(String s, Bundle bundle, UserHandle userhandle)
    {
        try
        {
            mService.setApplicationRestrictions(s, bundle, userhandle.getIdentifier());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void setDefaultGuestRestrictions(Bundle bundle)
    {
        try
        {
            mService.setDefaultGuestRestrictions(bundle);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Bundle bundle)
        {
            throw bundle.rethrowFromSystemServer();
        }
    }

    public void setQuietModeEnabled(int i, boolean flag)
    {
        try
        {
            mService.setQuietModeEnabled(i, flag);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public boolean setRestrictionsChallenge(String s)
    {
        return false;
    }

    public void setSeedAccountData(int i, String s, String s1, PersistableBundle persistablebundle)
    {
        try
        {
            mService.setSeedAccountData(i, s, s1, persistablebundle, true);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void setUserAccount(int i, String s)
    {
        try
        {
            mService.setUserAccount(i, s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void setUserEnabled(int i)
    {
        try
        {
            mService.setUserEnabled(i);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void setUserIcon(int i, Bitmap bitmap)
    {
        try
        {
            mService.setUserIcon(i, bitmap);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Bitmap bitmap)
        {
            throw bitmap.rethrowFromSystemServer();
        }
    }

    public void setUserName(int i, String s)
    {
        try
        {
            mService.setUserName(i, s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void setUserRestriction(String s, boolean flag)
    {
        setUserRestriction(s, flag, Process.myUserHandle());
    }

    public void setUserRestriction(String s, boolean flag, UserHandle userhandle)
    {
        try
        {
            mService.setUserRestriction(s, flag, userhandle.getIdentifier());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void setUserRestrictions(Bundle bundle)
    {
        throw new UnsupportedOperationException("This method is no longer supported");
    }

    public void setUserRestrictions(Bundle bundle, UserHandle userhandle)
    {
        throw new UnsupportedOperationException("This method is no longer supported");
    }

    public boolean someUserHasSeedAccount(String s, String s1)
    {
        boolean flag;
        try
        {
            flag = mService.someUserHasSeedAccount(s, s1);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean trySetQuietModeDisabled(int i, IntentSender intentsender)
    {
        boolean flag;
        try
        {
            flag = mService.trySetQuietModeDisabled(i, intentsender);
        }
        // Misplaced declaration of an exception variable
        catch(IntentSender intentsender)
        {
            throw intentsender.rethrowFromSystemServer();
        }
        return flag;
    }

    private static final String ACTION_CREATE_USER = "android.os.action.CREATE_USER";
    public static final String ACTION_USER_RESTRICTIONS_CHANGED = "android.os.action.USER_RESTRICTIONS_CHANGED";
    public static final String ALLOW_PARENT_PROFILE_APP_LINKING = "allow_parent_profile_app_linking";
    public static final String DISALLOW_ADD_MANAGED_PROFILE = "no_add_managed_profile";
    public static final String DISALLOW_ADD_USER = "no_add_user";
    public static final String DISALLOW_ADJUST_VOLUME = "no_adjust_volume";
    public static final String DISALLOW_APPS_CONTROL = "no_control_apps";
    public static final String DISALLOW_AUTOFILL = "no_autofill";
    public static final String DISALLOW_BLUETOOTH = "no_bluetooth";
    public static final String DISALLOW_BLUETOOTH_SHARING = "no_bluetooth_sharing";
    public static final String DISALLOW_CAMERA = "no_camera";
    public static final String DISALLOW_CONFIG_BLUETOOTH = "no_config_bluetooth";
    public static final String DISALLOW_CONFIG_CELL_BROADCASTS = "no_config_cell_broadcasts";
    public static final String DISALLOW_CONFIG_CREDENTIALS = "no_config_credentials";
    public static final String DISALLOW_CONFIG_MOBILE_NETWORKS = "no_config_mobile_networks";
    public static final String DISALLOW_CONFIG_TETHERING = "no_config_tethering";
    public static final String DISALLOW_CONFIG_VPN = "no_config_vpn";
    public static final String DISALLOW_CONFIG_WIFI = "no_config_wifi";
    public static final String DISALLOW_CREATE_WINDOWS = "no_create_windows";
    public static final String DISALLOW_CROSS_PROFILE_COPY_PASTE = "no_cross_profile_copy_paste";
    public static final String DISALLOW_DATA_ROAMING = "no_data_roaming";
    public static final String DISALLOW_DEBUGGING_FEATURES = "no_debugging_features";
    public static final String DISALLOW_FACTORY_RESET = "no_factory_reset";
    public static final String DISALLOW_FUN = "no_fun";
    public static final String DISALLOW_INSTALL_APPS = "no_install_apps";
    public static final String DISALLOW_INSTALL_UNKNOWN_SOURCES = "no_install_unknown_sources";
    public static final String DISALLOW_MODIFY_ACCOUNTS = "no_modify_accounts";
    public static final String DISALLOW_MOUNT_PHYSICAL_MEDIA = "no_physical_media";
    public static final String DISALLOW_NETWORK_RESET = "no_network_reset";
    public static final String DISALLOW_OEM_UNLOCK = "no_oem_unlock";
    public static final String DISALLOW_OUTGOING_BEAM = "no_outgoing_beam";
    public static final String DISALLOW_OUTGOING_CALLS = "no_outgoing_calls";
    public static final String DISALLOW_RECORD_AUDIO = "no_record_audio";
    public static final String DISALLOW_REMOVE_MANAGED_PROFILE = "no_remove_managed_profile";
    public static final String DISALLOW_REMOVE_USER = "no_remove_user";
    public static final String DISALLOW_RUN_IN_BACKGROUND = "no_run_in_background";
    public static final String DISALLOW_SAFE_BOOT = "no_safe_boot";
    public static final String DISALLOW_SET_USER_ICON = "no_set_user_icon";
    public static final String DISALLOW_SET_WALLPAPER = "no_set_wallpaper";
    public static final String DISALLOW_SHARE_LOCATION = "no_share_location";
    public static final String DISALLOW_SMS = "no_sms";
    public static final String DISALLOW_UNINSTALL_APPS = "no_uninstall_apps";
    public static final String DISALLOW_UNMUTE_DEVICE = "disallow_unmute_device";
    public static final String DISALLOW_UNMUTE_MICROPHONE = "no_unmute_microphone";
    public static final String DISALLOW_USB_FILE_TRANSFER = "no_usb_file_transfer";
    public static final String DISALLOW_WALLPAPER = "no_wallpaper";
    public static final String ENSURE_VERIFY_APPS = "ensure_verify_apps";
    public static final String EXTRA_USER_ACCOUNT_NAME = "android.os.extra.USER_ACCOUNT_NAME";
    public static final String EXTRA_USER_ACCOUNT_OPTIONS = "android.os.extra.USER_ACCOUNT_OPTIONS";
    public static final String EXTRA_USER_ACCOUNT_TYPE = "android.os.extra.USER_ACCOUNT_TYPE";
    public static final String EXTRA_USER_NAME = "android.os.extra.USER_NAME";
    public static final String KEY_RESTRICTIONS_PENDING = "restrictions_pending";
    public static final int PIN_VERIFICATION_FAILED_INCORRECT = -3;
    public static final int PIN_VERIFICATION_FAILED_NOT_SET = -2;
    public static final int PIN_VERIFICATION_SUCCESS = -1;
    public static final int RESTRICTION_NOT_SET = 0;
    public static final int RESTRICTION_SOURCE_DEVICE_OWNER = 2;
    public static final int RESTRICTION_SOURCE_PROFILE_OWNER = 4;
    public static final int RESTRICTION_SOURCE_SYSTEM = 1;
    private static final String TAG = "UserManager";
    public static final int USER_CREATION_FAILED_NOT_PERMITTED = 1;
    public static final int USER_CREATION_FAILED_NO_MORE_USERS = 2;
    private final Context mContext;
    private Boolean mIsManagedProfileCached;
    private final IUserManager mService;
}
