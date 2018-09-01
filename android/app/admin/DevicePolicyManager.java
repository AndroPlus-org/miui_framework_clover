// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.admin;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.ParceledListSlice;
import android.content.pm.StringParceledListSlice;
import android.content.pm.UserInfo;
import android.graphics.Bitmap;
import android.net.ProxyInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.Process;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.os.UserHandle;
import android.security.Credentials;
import android.util.ArraySet;
import android.util.Log;
import com.android.org.conscrypt.TrustedCertificateStore;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

// Referenced classes of package android.app.admin:
//            IDevicePolicyManager, SystemUpdateInfo, SystemUpdatePolicy, PasswordMetrics

public class DevicePolicyManager
{

    public DevicePolicyManager(Context context, IDevicePolicyManager idevicepolicymanager)
    {
        this(context, idevicepolicymanager, false);
    }

    protected DevicePolicyManager(Context context, IDevicePolicyManager idevicepolicymanager, boolean flag)
    {
        mContext = context;
        mService = idevicepolicymanager;
        mParentInstance = flag;
    }

    private static String getCaCertAlias(byte abyte0[])
        throws CertificateException
    {
        abyte0 = (X509Certificate)CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(abyte0));
        return (new TrustedCertificateStore()).getCertificateAlias(abyte0);
    }

    private ComponentName getDeviceOwnerComponentInner(boolean flag)
    {
        if(mService != null)
        {
            ComponentName componentname;
            try
            {
                componentname = mService.getDeviceOwnerComponent(flag);
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return componentname;
        } else
        {
            return null;
        }
    }

    private boolean isDeviceOwnerAppOnAnyUserInner(String s, boolean flag)
    {
        if(s == null)
            return false;
        ComponentName componentname = getDeviceOwnerComponentInner(flag);
        if(componentname == null)
            return false;
        else
            return s.equals(componentname.getPackageName());
    }

    private void throwIfParentInstance(String s)
    {
        if(mParentInstance)
            throw new SecurityException((new StringBuilder()).append(s).append(" cannot be called on the parent instance").toString());
        else
            return;
    }

    public void addCrossProfileIntentFilter(ComponentName componentname, IntentFilter intentfilter, int i)
    {
        throwIfParentInstance("addCrossProfileIntentFilter");
        if(mService == null)
            break MISSING_BLOCK_LABEL_26;
        mService.addCrossProfileIntentFilter(componentname, intentfilter, i);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public boolean addCrossProfileWidgetProvider(ComponentName componentname, String s)
    {
        throwIfParentInstance("addCrossProfileWidgetProvider");
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.addCrossProfileWidgetProvider(componentname, s);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public void addPersistentPreferredActivity(ComponentName componentname, IntentFilter intentfilter, ComponentName componentname1)
    {
        throwIfParentInstance("addPersistentPreferredActivity");
        if(mService == null)
            break MISSING_BLOCK_LABEL_26;
        mService.addPersistentPreferredActivity(componentname, intentfilter, componentname1);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void addUserRestriction(ComponentName componentname, String s)
    {
        throwIfParentInstance("addUserRestriction");
        if(mService == null)
            break MISSING_BLOCK_LABEL_26;
        mService.setUserRestriction(componentname, s, true);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public boolean approveCaCert(String s, int i, boolean flag)
    {
        if(mService != null)
        {
            try
            {
                flag = mService.approveCaCert(s, i, flag);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                throw s.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public boolean bindDeviceAdminServiceAsUser(ComponentName componentname, Intent intent, ServiceConnection serviceconnection, int i, UserHandle userhandle)
    {
        throwIfParentInstance("bindDeviceAdminServiceAsUser");
        boolean flag;
        try
        {
            serviceconnection = mContext.getServiceDispatcher(serviceconnection, mContext.getMainThreadHandler(), i);
            intent.prepareToLeaveProcess(mContext);
            flag = mService.bindDeviceAdminServiceAsUser(componentname, mContext.getIApplicationThread(), mContext.getActivityToken(), intent, serviceconnection, i, userhandle.getIdentifier());
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        return flag;
    }

    public int checkProvisioningPreCondition(String s, String s1)
    {
        int i;
        try
        {
            i = mService.checkProvisioningPreCondition(s, s1);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return i;
    }

    public void clearCrossProfileIntentFilters(ComponentName componentname)
    {
        throwIfParentInstance("clearCrossProfileIntentFilters");
        if(mService == null)
            break MISSING_BLOCK_LABEL_24;
        mService.clearCrossProfileIntentFilters(componentname);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void clearDeviceOwnerApp(String s)
    {
        throwIfParentInstance("clearDeviceOwnerApp");
        if(mService == null)
            break MISSING_BLOCK_LABEL_24;
        mService.clearDeviceOwner(s);
        return;
        s;
        throw s.rethrowFromSystemServer();
    }

    public void clearPackagePersistentPreferredActivities(ComponentName componentname, String s)
    {
        throwIfParentInstance("clearPackagePersistentPreferredActivities");
        if(mService == null)
            break MISSING_BLOCK_LABEL_25;
        mService.clearPackagePersistentPreferredActivities(componentname, s);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void clearProfileOwner(ComponentName componentname)
    {
        throwIfParentInstance("clearProfileOwner");
        if(mService == null)
            break MISSING_BLOCK_LABEL_24;
        mService.clearProfileOwner(componentname);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public boolean clearResetPasswordToken(ComponentName componentname)
    {
        throwIfParentInstance("clearResetPasswordToken");
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.clearResetPasswordToken(componentname);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public void clearUserRestriction(ComponentName componentname, String s)
    {
        throwIfParentInstance("clearUserRestriction");
        if(mService == null)
            break MISSING_BLOCK_LABEL_26;
        mService.setUserRestriction(componentname, s, false);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public Intent createAdminSupportIntent(String s)
    {
        throwIfParentInstance("createAdminSupportIntent");
        if(mService != null)
        {
            try
            {
                s = mService.createAdminSupportIntent(s);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                throw s.rethrowFromSystemServer();
            }
            return s;
        } else
        {
            return null;
        }
    }

    public UserHandle createAndInitializeUser(ComponentName componentname, String s, String s1, ComponentName componentname1, Bundle bundle)
    {
        return null;
    }

    public UserHandle createAndManageUser(ComponentName componentname, String s, ComponentName componentname1, PersistableBundle persistablebundle, int i)
    {
        throwIfParentInstance("createAndManageUser");
        try
        {
            componentname = mService.createAndManageUser(componentname, s, componentname1, persistablebundle, i);
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        return componentname;
    }

    public UserHandle createUser(ComponentName componentname, String s)
    {
        return null;
    }

    public int enableSystemApp(ComponentName componentname, Intent intent)
    {
        throwIfParentInstance("enableSystemApp");
        if(mService != null)
        {
            int i;
            try
            {
                i = mService.enableSystemAppWithIntent(componentname, mContext.getPackageName(), intent);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return i;
        } else
        {
            return 0;
        }
    }

    public void enableSystemApp(ComponentName componentname, String s)
    {
        throwIfParentInstance("enableSystemApp");
        if(mService == null)
            break MISSING_BLOCK_LABEL_32;
        mService.enableSystemApp(componentname, mContext.getPackageName(), s);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void forceRemoveActiveAdmin(ComponentName componentname, int i)
    {
        try
        {
            mService.forceRemoveActiveAdmin(componentname, i);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
    }

    public void forceUpdateUserSetupComplete()
    {
        try
        {
            mService.forceUpdateUserSetupComplete();
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public String[] getAccountTypesWithManagementDisabled()
    {
        throwIfParentInstance("getAccountTypesWithManagementDisabled");
        return getAccountTypesWithManagementDisabledAsUser(myUserId());
    }

    public String[] getAccountTypesWithManagementDisabledAsUser(int i)
    {
        if(mService != null)
        {
            String as[];
            try
            {
                as = mService.getAccountTypesWithManagementDisabledAsUser(i);
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return as;
        } else
        {
            return null;
        }
    }

    public List getActiveAdmins()
    {
        throwIfParentInstance("getActiveAdmins");
        return getActiveAdminsAsUser(myUserId());
    }

    public List getActiveAdminsAsUser(int i)
    {
        if(mService != null)
        {
            List list;
            try
            {
                list = mService.getActiveAdmins(i);
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return list;
        } else
        {
            return null;
        }
    }

    public Set getAffiliationIds(ComponentName componentname)
    {
        throwIfParentInstance("getAffiliationIds");
        try
        {
            componentname = new ArraySet(mService.getAffiliationIds(componentname));
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        return componentname;
    }

    public String getAlwaysOnVpnPackage(ComponentName componentname)
    {
        throwIfParentInstance("getAlwaysOnVpnPackage");
        if(mService != null)
        {
            try
            {
                componentname = mService.getAlwaysOnVpnPackage(componentname);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return componentname;
        } else
        {
            return null;
        }
    }

    public Bundle getApplicationRestrictions(ComponentName componentname, String s)
    {
        throwIfParentInstance("getApplicationRestrictions");
        if(mService != null)
        {
            try
            {
                componentname = mService.getApplicationRestrictions(componentname, mContext.getPackageName(), s);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return componentname;
        } else
        {
            return null;
        }
    }

    public String getApplicationRestrictionsManagingPackage(ComponentName componentname)
    {
        throwIfParentInstance("getApplicationRestrictionsManagingPackage");
        if(mService != null)
        {
            try
            {
                componentname = mService.getApplicationRestrictionsManagingPackage(componentname);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return componentname;
        } else
        {
            return null;
        }
    }

    public boolean getAutoTimeRequired()
    {
        throwIfParentInstance("getAutoTimeRequired");
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.getAutoTimeRequired();
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public List getBindDeviceAdminTargetUsers(ComponentName componentname)
    {
        throwIfParentInstance("getBindDeviceAdminTargetUsers");
        try
        {
            componentname = mService.getBindDeviceAdminTargetUsers(componentname);
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        return componentname;
    }

    public boolean getBluetoothContactSharingDisabled(ComponentName componentname)
    {
        throwIfParentInstance("getBluetoothContactSharingDisabled");
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.getBluetoothContactSharingDisabled(componentname);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return true;
        }
    }

    public boolean getBluetoothContactSharingDisabled(UserHandle userhandle)
    {
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.getBluetoothContactSharingDisabledForUser(userhandle.getIdentifier());
            }
            // Misplaced declaration of an exception variable
            catch(UserHandle userhandle)
            {
                throw userhandle.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return true;
        }
    }

    public boolean getCameraDisabled(ComponentName componentname)
    {
        throwIfParentInstance("getCameraDisabled");
        return getCameraDisabled(componentname, myUserId());
    }

    public boolean getCameraDisabled(ComponentName componentname, int i)
    {
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.getCameraDisabled(componentname, i);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public String getCertInstallerPackage(ComponentName componentname)
        throws SecurityException
    {
        throwIfParentInstance("getCertInstallerPackage");
        if(mService != null)
        {
            try
            {
                componentname = mService.getCertInstallerPackage(componentname);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return componentname;
        } else
        {
            return null;
        }
    }

    public boolean getCrossProfileCallerIdDisabled(ComponentName componentname)
    {
        throwIfParentInstance("getCrossProfileCallerIdDisabled");
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.getCrossProfileCallerIdDisabled(componentname);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public boolean getCrossProfileCallerIdDisabled(UserHandle userhandle)
    {
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.getCrossProfileCallerIdDisabledForUser(userhandle.getIdentifier());
            }
            // Misplaced declaration of an exception variable
            catch(UserHandle userhandle)
            {
                throw userhandle.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public boolean getCrossProfileContactsSearchDisabled(ComponentName componentname)
    {
        throwIfParentInstance("getCrossProfileContactsSearchDisabled");
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.getCrossProfileContactsSearchDisabled(componentname);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public boolean getCrossProfileContactsSearchDisabled(UserHandle userhandle)
    {
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.getCrossProfileContactsSearchDisabledForUser(userhandle.getIdentifier());
            }
            // Misplaced declaration of an exception variable
            catch(UserHandle userhandle)
            {
                throw userhandle.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public List getCrossProfileWidgetProviders(ComponentName componentname)
    {
        throwIfParentInstance("getCrossProfileWidgetProviders");
        if(mService != null)
        {
            try
            {
                componentname = mService.getCrossProfileWidgetProviders(componentname);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            if(componentname != null)
                return componentname;
        }
        return Collections.emptyList();
    }

    public int getCurrentFailedPasswordAttempts()
    {
        return getCurrentFailedPasswordAttempts(myUserId());
    }

    public int getCurrentFailedPasswordAttempts(int i)
    {
        if(mService != null)
        {
            try
            {
                i = mService.getCurrentFailedPasswordAttempts(i, mParentInstance);
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return i;
        } else
        {
            return -1;
        }
    }

    public List getDelegatePackages(ComponentName componentname, String s)
    {
        throwIfParentInstance("getDelegatePackages");
        if(mService != null)
        {
            try
            {
                componentname = mService.getDelegatePackages(componentname, s);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return componentname;
        } else
        {
            return null;
        }
    }

    public List getDelegatedScopes(ComponentName componentname, String s)
    {
        throwIfParentInstance("getDelegatedScopes");
        if(mService != null)
        {
            try
            {
                componentname = mService.getDelegatedScopes(componentname, s);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return componentname;
        } else
        {
            return null;
        }
    }

    public String getDeviceInitializerApp()
    {
        return null;
    }

    public ComponentName getDeviceInitializerComponent()
    {
        return null;
    }

    public String getDeviceOwner()
    {
        String s = null;
        throwIfParentInstance("getDeviceOwner");
        ComponentName componentname = getDeviceOwnerComponentOnCallingUser();
        if(componentname != null)
            s = componentname.getPackageName();
        return s;
    }

    public ComponentName getDeviceOwnerComponentOnAnyUser()
    {
        return getDeviceOwnerComponentInner(false);
    }

    public ComponentName getDeviceOwnerComponentOnCallingUser()
    {
        return getDeviceOwnerComponentInner(true);
    }

    public CharSequence getDeviceOwnerLockScreenInfo()
    {
        throwIfParentInstance("getDeviceOwnerLockScreenInfo");
        if(mService != null)
        {
            CharSequence charsequence;
            try
            {
                charsequence = mService.getDeviceOwnerLockScreenInfo();
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return charsequence;
        } else
        {
            return null;
        }
    }

    public String getDeviceOwnerNameOnAnyUser()
    {
        throwIfParentInstance("getDeviceOwnerNameOnAnyUser");
        if(mService != null)
        {
            String s;
            try
            {
                s = mService.getDeviceOwnerName();
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return s;
        } else
        {
            return null;
        }
    }

    public CharSequence getDeviceOwnerOrganizationName()
    {
        CharSequence charsequence;
        try
        {
            charsequence = mService.getDeviceOwnerOrganizationName();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return charsequence;
    }

    public int getDeviceOwnerUserId()
    {
        if(mService != null)
        {
            int i;
            try
            {
                i = mService.getDeviceOwnerUserId();
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return i;
        } else
        {
            return -10000;
        }
    }

    public boolean getDoNotAskCredentialsOnBoot()
    {
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.getDoNotAskCredentialsOnBoot();
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public boolean getForceEphemeralUsers(ComponentName componentname)
    {
        throwIfParentInstance("getForceEphemeralUsers");
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.getForceEphemeralUsers(componentname);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public ComponentName getGlobalProxyAdmin()
    {
        if(mService != null)
        {
            ComponentName componentname;
            try
            {
                componentname = mService.getGlobalProxyAdmin(myUserId());
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return componentname;
        } else
        {
            return null;
        }
    }

    public boolean getGuestUserDisabled(ComponentName componentname)
    {
        return false;
    }

    public List getInstalledCaCerts(ComponentName componentname)
    {
        ArrayList arraylist;
        arraylist = new ArrayList();
        throwIfParentInstance("getInstalledCaCerts");
        if(mService == null)
            break MISSING_BLOCK_LABEL_146;
        TrustedCertificateStore trustedcertificatestore;
        mService.enforceCanManageCaCerts(componentname, mContext.getPackageName());
        trustedcertificatestore = JVM INSTR new #389 <Class TrustedCertificateStore>;
        trustedcertificatestore.TrustedCertificateStore();
        componentname = trustedcertificatestore.userAliases().iterator();
_L1:
        String s;
        if(!componentname.hasNext())
            break MISSING_BLOCK_LABEL_146;
        s = (String)componentname.next();
        try
        {
            arraylist.add(trustedcertificatestore.getCertificate(s).getEncoded());
        }
        catch(CertificateException certificateexception)
        {
            try
            {
                String s1 = TAG;
                StringBuilder stringbuilder = JVM INSTR new #430 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Log.w(s1, stringbuilder.append("Could not encode certificate: ").append(s).toString(), certificateexception);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
        }
          goto _L1
        return arraylist;
    }

    public List getKeepUninstalledPackages(ComponentName componentname)
    {
        throwIfParentInstance("getKeepUninstalledPackages");
        if(mService != null)
        {
            try
            {
                componentname = mService.getKeepUninstalledPackages(componentname, mContext.getPackageName());
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return componentname;
        } else
        {
            return null;
        }
    }

    public int getKeyguardDisabledFeatures(ComponentName componentname)
    {
        return getKeyguardDisabledFeatures(componentname, myUserId());
    }

    public int getKeyguardDisabledFeatures(ComponentName componentname, int i)
    {
        if(mService != null)
        {
            try
            {
                i = mService.getKeyguardDisabledFeatures(componentname, i, mParentInstance);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return i;
        } else
        {
            return 0;
        }
    }

    public long getLastBugReportRequestTime()
    {
        long l;
        try
        {
            l = mService.getLastBugReportRequestTime();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return l;
    }

    public long getLastNetworkLogRetrievalTime()
    {
        long l;
        try
        {
            l = mService.getLastNetworkLogRetrievalTime();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return l;
    }

    public long getLastSecurityLogRetrievalTime()
    {
        long l;
        try
        {
            l = mService.getLastSecurityLogRetrievalTime();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return l;
    }

    public String[] getLockTaskPackages(ComponentName componentname)
    {
        throwIfParentInstance("getLockTaskPackages");
        if(mService != null)
        {
            try
            {
                componentname = mService.getLockTaskPackages(componentname);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return componentname;
        } else
        {
            return new String[0];
        }
    }

    public CharSequence getLongSupportMessage(ComponentName componentname)
    {
        throwIfParentInstance("getLongSupportMessage");
        if(mService != null)
        {
            try
            {
                componentname = mService.getLongSupportMessage(componentname);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return componentname;
        } else
        {
            return null;
        }
    }

    public CharSequence getLongSupportMessageForUser(ComponentName componentname, int i)
    {
        if(mService != null)
        {
            try
            {
                componentname = mService.getLongSupportMessageForUser(componentname, i);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return componentname;
        } else
        {
            return null;
        }
    }

    public int getMaximumFailedPasswordsForWipe(ComponentName componentname)
    {
        return getMaximumFailedPasswordsForWipe(componentname, myUserId());
    }

    public int getMaximumFailedPasswordsForWipe(ComponentName componentname, int i)
    {
        if(mService != null)
        {
            try
            {
                i = mService.getMaximumFailedPasswordsForWipe(componentname, i, mParentInstance);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return i;
        } else
        {
            return 0;
        }
    }

    public long getMaximumTimeToLock(ComponentName componentname)
    {
        return getMaximumTimeToLock(componentname, myUserId());
    }

    public long getMaximumTimeToLock(ComponentName componentname, int i)
    {
        if(mService != null)
        {
            long l;
            try
            {
                l = mService.getMaximumTimeToLock(componentname, i, mParentInstance);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return l;
        } else
        {
            return 0L;
        }
    }

    public long getMaximumTimeToLockForUserAndProfiles(int i)
    {
        if(mService != null)
        {
            long l;
            try
            {
                l = mService.getMaximumTimeToLockForUserAndProfiles(i);
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return l;
        } else
        {
            return 0L;
        }
    }

    public int getOrganizationColor(ComponentName componentname)
    {
        throwIfParentInstance("getOrganizationColor");
        int i;
        try
        {
            i = mService.getOrganizationColor(componentname);
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        return i;
    }

    public int getOrganizationColorForUser(int i)
    {
        try
        {
            i = mService.getOrganizationColorForUser(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public CharSequence getOrganizationName(ComponentName componentname)
    {
        throwIfParentInstance("getOrganizationName");
        try
        {
            componentname = mService.getOrganizationName(componentname);
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        return componentname;
    }

    public CharSequence getOrganizationNameForUser(int i)
    {
        CharSequence charsequence;
        try
        {
            charsequence = mService.getOrganizationNameForUser(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return charsequence;
    }

    public List getOwnerInstalledCaCerts(UserHandle userhandle)
    {
        try
        {
            userhandle = mService.getOwnerInstalledCaCerts(userhandle).getList();
        }
        // Misplaced declaration of an exception variable
        catch(UserHandle userhandle)
        {
            throw userhandle.rethrowFromSystemServer();
        }
        return userhandle;
    }

    public DevicePolicyManager getParentProfileInstance(ComponentName componentname)
    {
        throwIfParentInstance("getParentProfileInstance");
        try
        {
            if(!mService.isManagedProfile(componentname))
            {
                componentname = JVM INSTR new #428 <Class SecurityException>;
                componentname.SecurityException("The current user does not have a parent profile.");
                throw componentname;
            }
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        componentname = new DevicePolicyManager(mContext, mService, true);
        return componentname;
    }

    public DevicePolicyManager getParentProfileInstance(UserInfo userinfo)
    {
        mContext.checkSelfPermission("android.permission.MANAGE_PROFILE_AND_DEVICE_OWNERS");
        if(!userinfo.isManagedProfile())
            throw new SecurityException((new StringBuilder()).append("The user ").append(userinfo.id).append(" does not have a parent profile.").toString());
        else
            return new DevicePolicyManager(mContext, mService, true);
    }

    public long getPasswordExpiration(ComponentName componentname)
    {
        if(mService != null)
        {
            long l;
            try
            {
                l = mService.getPasswordExpiration(componentname, myUserId(), mParentInstance);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return l;
        } else
        {
            return 0L;
        }
    }

    public long getPasswordExpirationTimeout(ComponentName componentname)
    {
        if(mService != null)
        {
            long l;
            try
            {
                l = mService.getPasswordExpirationTimeout(componentname, myUserId(), mParentInstance);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return l;
        } else
        {
            return 0L;
        }
    }

    public int getPasswordHistoryLength(ComponentName componentname)
    {
        return getPasswordHistoryLength(componentname, myUserId());
    }

    public int getPasswordHistoryLength(ComponentName componentname, int i)
    {
        if(mService != null)
        {
            try
            {
                i = mService.getPasswordHistoryLength(componentname, i, mParentInstance);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return i;
        } else
        {
            return 0;
        }
    }

    public int getPasswordMaximumLength(int i)
    {
        return 16;
    }

    public int getPasswordMinimumLength(ComponentName componentname)
    {
        return getPasswordMinimumLength(componentname, myUserId());
    }

    public int getPasswordMinimumLength(ComponentName componentname, int i)
    {
        if(mService != null)
        {
            try
            {
                i = mService.getPasswordMinimumLength(componentname, i, mParentInstance);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return i;
        } else
        {
            return 0;
        }
    }

    public int getPasswordMinimumLetters(ComponentName componentname)
    {
        return getPasswordMinimumLetters(componentname, myUserId());
    }

    public int getPasswordMinimumLetters(ComponentName componentname, int i)
    {
        if(mService != null)
        {
            try
            {
                i = mService.getPasswordMinimumLetters(componentname, i, mParentInstance);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return i;
        } else
        {
            return 0;
        }
    }

    public int getPasswordMinimumLowerCase(ComponentName componentname)
    {
        return getPasswordMinimumLowerCase(componentname, myUserId());
    }

    public int getPasswordMinimumLowerCase(ComponentName componentname, int i)
    {
        if(mService != null)
        {
            try
            {
                i = mService.getPasswordMinimumLowerCase(componentname, i, mParentInstance);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return i;
        } else
        {
            return 0;
        }
    }

    public int getPasswordMinimumNonLetter(ComponentName componentname)
    {
        return getPasswordMinimumNonLetter(componentname, myUserId());
    }

    public int getPasswordMinimumNonLetter(ComponentName componentname, int i)
    {
        if(mService != null)
        {
            try
            {
                i = mService.getPasswordMinimumNonLetter(componentname, i, mParentInstance);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return i;
        } else
        {
            return 0;
        }
    }

    public int getPasswordMinimumNumeric(ComponentName componentname)
    {
        return getPasswordMinimumNumeric(componentname, myUserId());
    }

    public int getPasswordMinimumNumeric(ComponentName componentname, int i)
    {
        if(mService != null)
        {
            try
            {
                i = mService.getPasswordMinimumNumeric(componentname, i, mParentInstance);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return i;
        } else
        {
            return 0;
        }
    }

    public int getPasswordMinimumSymbols(ComponentName componentname)
    {
        return getPasswordMinimumSymbols(componentname, myUserId());
    }

    public int getPasswordMinimumSymbols(ComponentName componentname, int i)
    {
        if(mService != null)
        {
            try
            {
                i = mService.getPasswordMinimumSymbols(componentname, i, mParentInstance);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return i;
        } else
        {
            return 0;
        }
    }

    public int getPasswordMinimumUpperCase(ComponentName componentname)
    {
        return getPasswordMinimumUpperCase(componentname, myUserId());
    }

    public int getPasswordMinimumUpperCase(ComponentName componentname, int i)
    {
        if(mService != null)
        {
            try
            {
                i = mService.getPasswordMinimumUpperCase(componentname, i, mParentInstance);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return i;
        } else
        {
            return 0;
        }
    }

    public int getPasswordQuality(ComponentName componentname)
    {
        return getPasswordQuality(componentname, myUserId());
    }

    public int getPasswordQuality(ComponentName componentname, int i)
    {
        if(mService != null)
        {
            try
            {
                i = mService.getPasswordQuality(componentname, i, mParentInstance);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return i;
        } else
        {
            return 0;
        }
    }

    public SystemUpdateInfo getPendingSystemUpdate(ComponentName componentname)
    {
        throwIfParentInstance("getPendingSystemUpdate");
        try
        {
            componentname = mService.getPendingSystemUpdate(componentname);
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        return componentname;
    }

    public int getPermissionGrantState(ComponentName componentname, String s, String s1)
    {
        throwIfParentInstance("getPermissionGrantState");
        int i;
        try
        {
            i = mService.getPermissionGrantState(componentname, mContext.getPackageName(), s, s1);
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        return i;
    }

    public int getPermissionPolicy(ComponentName componentname)
    {
        throwIfParentInstance("getPermissionPolicy");
        int i;
        try
        {
            i = mService.getPermissionPolicy(componentname);
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        return i;
    }

    public List getPermittedAccessibilityServices(int i)
    {
        throwIfParentInstance("getPermittedAccessibilityServices");
        if(mService != null)
        {
            List list;
            try
            {
                list = mService.getPermittedAccessibilityServicesForUser(i);
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return list;
        } else
        {
            return null;
        }
    }

    public List getPermittedAccessibilityServices(ComponentName componentname)
    {
        throwIfParentInstance("getPermittedAccessibilityServices");
        if(mService != null)
        {
            try
            {
                componentname = mService.getPermittedAccessibilityServices(componentname);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return componentname;
        } else
        {
            return null;
        }
    }

    public List getPermittedCrossProfileNotificationListeners(ComponentName componentname)
    {
        throwIfParentInstance("getPermittedCrossProfileNotificationListeners");
        if(mService != null)
        {
            try
            {
                componentname = mService.getPermittedCrossProfileNotificationListeners(componentname);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return componentname;
        } else
        {
            return null;
        }
    }

    public List getPermittedInputMethods(ComponentName componentname)
    {
        throwIfParentInstance("getPermittedInputMethods");
        if(mService != null)
        {
            try
            {
                componentname = mService.getPermittedInputMethods(componentname);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return componentname;
        } else
        {
            return null;
        }
    }

    public List getPermittedInputMethodsForCurrentUser()
    {
        throwIfParentInstance("getPermittedInputMethodsForCurrentUser");
        if(mService != null)
        {
            List list;
            try
            {
                list = mService.getPermittedInputMethodsForCurrentUser();
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return list;
        } else
        {
            return null;
        }
    }

    public ComponentName getProfileOwner()
        throws IllegalArgumentException
    {
        throwIfParentInstance("getProfileOwner");
        return getProfileOwnerAsUser(Process.myUserHandle().getIdentifier());
    }

    public ComponentName getProfileOwnerAsUser(int i)
        throws IllegalArgumentException
    {
        if(mService != null)
        {
            ComponentName componentname;
            try
            {
                componentname = mService.getProfileOwner(i);
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return componentname;
        } else
        {
            return null;
        }
    }

    public String getProfileOwnerName()
        throws IllegalArgumentException
    {
        if(mService != null)
        {
            String s;
            try
            {
                s = mService.getProfileOwnerName(Process.myUserHandle().getIdentifier());
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return s;
        } else
        {
            return null;
        }
    }

    public String getProfileOwnerNameAsUser(int i)
        throws IllegalArgumentException
    {
        throwIfParentInstance("getProfileOwnerNameAsUser");
        if(mService != null)
        {
            String s;
            try
            {
                s = mService.getProfileOwnerName(i);
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return s;
        } else
        {
            return null;
        }
    }

    public int getProfileWithMinimumFailedPasswordsForWipe(int i)
    {
        if(mService != null)
        {
            try
            {
                i = mService.getProfileWithMinimumFailedPasswordsForWipe(i, mParentInstance);
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return i;
        } else
        {
            return -10000;
        }
    }

    public void getRemoveWarning(ComponentName componentname, RemoteCallback remotecallback)
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_22;
        mService.getRemoveWarning(componentname, remotecallback, myUserId());
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public long getRequiredStrongAuthTimeout(ComponentName componentname)
    {
        return getRequiredStrongAuthTimeout(componentname, myUserId());
    }

    public long getRequiredStrongAuthTimeout(ComponentName componentname, int i)
    {
        if(mService != null)
        {
            long l;
            try
            {
                l = mService.getRequiredStrongAuthTimeout(componentname, i, mParentInstance);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return l;
        } else
        {
            return 0xf731400L;
        }
    }

    public boolean getScreenCaptureDisabled(ComponentName componentname)
    {
        throwIfParentInstance("getScreenCaptureDisabled");
        return getScreenCaptureDisabled(componentname, myUserId());
    }

    public boolean getScreenCaptureDisabled(ComponentName componentname, int i)
    {
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.getScreenCaptureDisabled(componentname, i);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public CharSequence getShortSupportMessage(ComponentName componentname)
    {
        throwIfParentInstance("getShortSupportMessage");
        if(mService != null)
        {
            try
            {
                componentname = mService.getShortSupportMessage(componentname);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return componentname;
        } else
        {
            return null;
        }
    }

    public CharSequence getShortSupportMessageForUser(ComponentName componentname, int i)
    {
        if(mService != null)
        {
            try
            {
                componentname = mService.getShortSupportMessageForUser(componentname, i);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return componentname;
        } else
        {
            return null;
        }
    }

    public boolean getStorageEncryption(ComponentName componentname)
    {
        throwIfParentInstance("getStorageEncryption");
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.getStorageEncryption(componentname, myUserId());
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public int getStorageEncryptionStatus()
    {
        throwIfParentInstance("getStorageEncryptionStatus");
        return getStorageEncryptionStatus(myUserId());
    }

    public int getStorageEncryptionStatus(int i)
    {
        if(mService != null)
        {
            try
            {
                i = mService.getStorageEncryptionStatus(mContext.getPackageName(), i);
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return i;
        } else
        {
            return 0;
        }
    }

    public SystemUpdatePolicy getSystemUpdatePolicy()
    {
        throwIfParentInstance("getSystemUpdatePolicy");
        if(mService != null)
        {
            SystemUpdatePolicy systemupdatepolicy;
            try
            {
                systemupdatepolicy = mService.getSystemUpdatePolicy();
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return systemupdatepolicy;
        } else
        {
            return null;
        }
    }

    public List getTrustAgentConfiguration(ComponentName componentname, ComponentName componentname1)
    {
        return getTrustAgentConfiguration(componentname, componentname1, myUserId());
    }

    public List getTrustAgentConfiguration(ComponentName componentname, ComponentName componentname1, int i)
    {
        if(mService != null)
        {
            try
            {
                componentname = mService.getTrustAgentConfiguration(componentname, componentname1, i, mParentInstance);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return componentname;
        } else
        {
            return new ArrayList();
        }
    }

    public int getUserProvisioningState()
    {
        throwIfParentInstance("getUserProvisioningState");
        if(mService != null)
        {
            int i;
            try
            {
                i = mService.getUserProvisioningState();
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return i;
        } else
        {
            return 0;
        }
    }

    public Bundle getUserRestrictions(ComponentName componentname)
    {
        throwIfParentInstance("getUserRestrictions");
        Bundle bundle = null;
        if(mService != null)
            try
            {
                bundle = mService.getUserRestrictions(componentname);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
        componentname = bundle;
        if(bundle == null)
            componentname = new Bundle();
        return componentname;
    }

    public String getWifiMacAddress(ComponentName componentname)
    {
        throwIfParentInstance("getWifiMacAddress");
        try
        {
            componentname = mService.getWifiMacAddress(componentname);
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        return componentname;
    }

    public boolean hasCaCertInstalled(ComponentName componentname, byte abyte0[])
    {
        boolean flag;
        flag = false;
        throwIfParentInstance("hasCaCertInstalled");
        if(mService == null)
            break MISSING_BLOCK_LABEL_58;
        mService.enforceCanManageCaCerts(componentname, mContext.getPackageName());
        componentname = getCaCertAlias(abyte0);
        if(componentname != null)
            flag = true;
        return flag;
        componentname;
        Log.w(TAG, "Could not parse certificate", componentname);
        return false;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public boolean hasGrantedPolicy(ComponentName componentname, int i)
    {
        throwIfParentInstance("hasGrantedPolicy");
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.hasGrantedPolicy(componentname, i, myUserId());
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public boolean hasUserSetupCompleted()
    {
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.hasUserSetupCompleted();
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return true;
        }
    }

    public boolean installCaCert(ComponentName componentname, byte abyte0[])
    {
        throwIfParentInstance("installCaCert");
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.installCaCert(componentname, mContext.getPackageName(), abyte0);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public boolean installKeyPair(ComponentName componentname, PrivateKey privatekey, Certificate certificate, String s)
    {
        return installKeyPair(componentname, privatekey, new Certificate[] {
            certificate
        }, s, false);
    }

    public boolean installKeyPair(ComponentName componentname, PrivateKey privatekey, Certificate acertificate[], String s, boolean flag)
    {
        throwIfParentInstance("installKeyPair");
        byte abyte0[] = Credentials.convertToPem(new Certificate[] {
            acertificate[0]
        });
        byte abyte1[] = null;
        if(acertificate.length > 1)
            abyte1 = Credentials.convertToPem((Certificate[])Arrays.copyOfRange(acertificate, 1, acertificate.length));
        privatekey = ((PKCS8EncodedKeySpec)KeyFactory.getInstance(privatekey.getAlgorithm()).getKeySpec(privatekey, java/security/spec/PKCS8EncodedKeySpec)).getEncoded();
        flag = mService.installKeyPair(componentname, mContext.getPackageName(), privatekey, abyte0, abyte1, s, flag);
        return flag;
        componentname;
        Log.w(TAG, "Could not pem-encode certificate", componentname);
_L2:
        return false;
        componentname;
        Log.w(TAG, "Failed to obtain private key material", componentname);
        if(true) goto _L2; else goto _L1
_L1:
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public boolean isAccessibilityServicePermittedByAdmin(ComponentName componentname, String s, int i)
    {
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.isAccessibilityServicePermittedByAdmin(componentname, s, i);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public boolean isActivePasswordSufficient()
    {
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.isActivePasswordSufficient(myUserId(), mParentInstance);
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public boolean isAdminActive(ComponentName componentname)
    {
        throwIfParentInstance("isAdminActive");
        return isAdminActiveAsUser(componentname, myUserId());
    }

    public boolean isAdminActiveAsUser(ComponentName componentname, int i)
    {
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.isAdminActive(componentname, i);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public boolean isAffiliatedUser()
    {
        throwIfParentInstance("isAffiliatedUser");
        boolean flag;
        try
        {
            flag = mService.isAffiliatedUser();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isApplicationHidden(ComponentName componentname, String s)
    {
        throwIfParentInstance("isApplicationHidden");
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.isApplicationHidden(componentname, mContext.getPackageName(), s);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public boolean isBackupServiceEnabled(ComponentName componentname)
    {
        throwIfParentInstance("isBackupServiceEnabled");
        boolean flag;
        try
        {
            flag = mService.isBackupServiceEnabled(componentname);
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isCaCertApproved(String s, int i)
    {
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.isCaCertApproved(s, i);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                throw s.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public boolean isCallerApplicationRestrictionsManagingPackage()
    {
        throwIfParentInstance("isCallerApplicationRestrictionsManagingPackage");
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.isCallerApplicationRestrictionsManagingPackage(mContext.getPackageName());
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public boolean isCurrentInputMethodSetByOwner()
    {
        boolean flag;
        try
        {
            flag = mService.isCurrentInputMethodSetByOwner();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isDeviceManaged()
    {
        boolean flag;
        try
        {
            flag = mService.hasDeviceOwner();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isDeviceOwnerApp(String s)
    {
        throwIfParentInstance("isDeviceOwnerApp");
        return isDeviceOwnerAppOnCallingUser(s);
    }

    public boolean isDeviceOwnerAppOnAnyUser(String s)
    {
        return isDeviceOwnerAppOnAnyUserInner(s, false);
    }

    public boolean isDeviceOwnerAppOnCallingUser(String s)
    {
        return isDeviceOwnerAppOnAnyUserInner(s, true);
    }

    public boolean isDeviceProvisioned()
    {
        boolean flag;
        try
        {
            flag = mService.isDeviceProvisioned();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isDeviceProvisioningConfigApplied()
    {
        boolean flag;
        try
        {
            flag = mService.isDeviceProvisioningConfigApplied();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isInputMethodPermittedByAdmin(ComponentName componentname, String s, int i)
    {
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.isInputMethodPermittedByAdmin(componentname, s, i);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public boolean isLockTaskPermitted(String s)
    {
        throwIfParentInstance("isLockTaskPermitted");
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.isLockTaskPermitted(s);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                throw s.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public boolean isManagedProfile(ComponentName componentname)
    {
        throwIfParentInstance("isManagedProfile");
        boolean flag;
        try
        {
            flag = mService.isManagedProfile(componentname);
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isMasterVolumeMuted(ComponentName componentname)
    {
        throwIfParentInstance("isMasterVolumeMuted");
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.isMasterVolumeMuted(componentname);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public boolean isNetworkLoggingEnabled(ComponentName componentname)
    {
        throwIfParentInstance("isNetworkLoggingEnabled");
        boolean flag;
        try
        {
            flag = mService.isNetworkLoggingEnabled(componentname);
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isNotificationListenerServicePermitted(String s, int i)
    {
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.isNotificationListenerServicePermitted(s, i);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                throw s.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return true;
        }
    }

    public boolean isPackageSuspended(ComponentName componentname, String s)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        throwIfParentInstance("isPackageSuspended");
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.isPackageSuspended(componentname, mContext.getPackageName(), s);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw new android.content.pm.PackageManager.NameNotFoundException(s);
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public boolean isProfileActivePasswordSufficientForParent(int i)
    {
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.isProfileActivePasswordSufficientForParent(i);
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public boolean isProfileOwnerApp(String s)
    {
        boolean flag = false;
        throwIfParentInstance("isProfileOwnerApp");
        if(mService == null)
            break MISSING_BLOCK_LABEL_51;
        ComponentName componentname;
        try
        {
            componentname = mService.getProfileOwner(myUserId());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        if(componentname == null)
            break MISSING_BLOCK_LABEL_43;
        flag = componentname.getPackageName().equals(s);
        return flag;
        return false;
    }

    public boolean isProvisioningAllowed(String s)
    {
        throwIfParentInstance("isProvisioningAllowed");
        boolean flag;
        try
        {
            flag = mService.isProvisioningAllowed(s, mContext.getPackageName());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isRemovingAdmin(ComponentName componentname, int i)
    {
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.isRemovingAdmin(componentname, i);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public boolean isResetPasswordTokenActive(ComponentName componentname)
    {
        throwIfParentInstance("isResetPasswordTokenActive");
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.isResetPasswordTokenActive(componentname);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public boolean isSecurityLoggingEnabled(ComponentName componentname)
    {
        throwIfParentInstance("isSecurityLoggingEnabled");
        boolean flag;
        try
        {
            flag = mService.isSecurityLoggingEnabled(componentname);
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isSeparateProfileChallengeAllowed(int i)
    {
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.isSeparateProfileChallengeAllowed(i);
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public boolean isSystemOnlyUser(ComponentName componentname)
    {
        boolean flag;
        try
        {
            flag = mService.isSystemOnlyUser(componentname);
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isUninstallBlocked(ComponentName componentname, String s)
    {
        throwIfParentInstance("isUninstallBlocked");
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.isUninstallBlocked(componentname, s);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public boolean isUninstallInQueue(String s)
    {
        boolean flag;
        try
        {
            flag = mService.isUninstallInQueue(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public void lockNow()
    {
        lockNow(0);
    }

    public void lockNow(int i)
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_21;
        mService.lockNow(i, mParentInstance);
        return;
        RemoteException remoteexception;
        remoteexception;
        throw remoteexception.rethrowFromSystemServer();
    }

    protected int myUserId()
    {
        return UserHandle.myUserId();
    }

    public void notifyPendingSystemUpdate(long l)
    {
        throwIfParentInstance("notifyPendingSystemUpdate");
        if(mService == null)
            break MISSING_BLOCK_LABEL_27;
        mService.notifyPendingSystemUpdate(SystemUpdateInfo.of(l));
        return;
        RemoteException remoteexception;
        remoteexception;
        throw remoteexception.rethrowFromSystemServer();
    }

    public void notifyPendingSystemUpdate(long l, boolean flag)
    {
        throwIfParentInstance("notifyPendingSystemUpdate");
        if(mService == null)
            break MISSING_BLOCK_LABEL_28;
        mService.notifyPendingSystemUpdate(SystemUpdateInfo.of(l, flag));
        return;
        RemoteException remoteexception;
        remoteexception;
        throw remoteexception.rethrowFromSystemServer();
    }

    public boolean packageHasActiveAdmins(String s)
    {
        return packageHasActiveAdmins(s, myUserId());
    }

    public boolean packageHasActiveAdmins(String s, int i)
    {
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.packageHasActiveAdmins(s, i);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                throw s.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public void reboot(ComponentName componentname)
    {
        throwIfParentInstance("reboot");
        try
        {
            mService.reboot(componentname);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
    }

    public void removeActiveAdmin(ComponentName componentname)
    {
        throwIfParentInstance("removeActiveAdmin");
        if(mService == null)
            break MISSING_BLOCK_LABEL_28;
        mService.removeActiveAdmin(componentname, myUserId());
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public boolean removeCrossProfileWidgetProvider(ComponentName componentname, String s)
    {
        throwIfParentInstance("removeCrossProfileWidgetProvider");
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.removeCrossProfileWidgetProvider(componentname, s);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public boolean removeKeyPair(ComponentName componentname, String s)
    {
        throwIfParentInstance("removeKeyPair");
        boolean flag;
        try
        {
            flag = mService.removeKeyPair(componentname, mContext.getPackageName(), s);
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean removeUser(ComponentName componentname, UserHandle userhandle)
    {
        throwIfParentInstance("removeUser");
        boolean flag;
        try
        {
            flag = mService.removeUser(componentname, userhandle);
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        return flag;
    }

    public void reportFailedFingerprintAttempt(int i)
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_17;
        mService.reportFailedFingerprintAttempt(i);
        return;
        RemoteException remoteexception;
        remoteexception;
        throw remoteexception.rethrowFromSystemServer();
    }

    public void reportFailedPasswordAttempt(int i)
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_17;
        mService.reportFailedPasswordAttempt(i);
        return;
        RemoteException remoteexception;
        remoteexception;
        throw remoteexception.rethrowFromSystemServer();
    }

    public void reportKeyguardDismissed(int i)
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_17;
        mService.reportKeyguardDismissed(i);
        return;
        RemoteException remoteexception;
        remoteexception;
        throw remoteexception.rethrowFromSystemServer();
    }

    public void reportKeyguardSecured(int i)
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_17;
        mService.reportKeyguardSecured(i);
        return;
        RemoteException remoteexception;
        remoteexception;
        throw remoteexception.rethrowFromSystemServer();
    }

    public void reportPasswordChanged(int i)
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_17;
        mService.reportPasswordChanged(i);
        return;
        RemoteException remoteexception;
        remoteexception;
        throw remoteexception.rethrowFromSystemServer();
    }

    public void reportSuccessfulFingerprintAttempt(int i)
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_17;
        mService.reportSuccessfulFingerprintAttempt(i);
        return;
        RemoteException remoteexception;
        remoteexception;
        throw remoteexception.rethrowFromSystemServer();
    }

    public void reportSuccessfulPasswordAttempt(int i)
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_17;
        mService.reportSuccessfulPasswordAttempt(i);
        return;
        RemoteException remoteexception;
        remoteexception;
        throw remoteexception.rethrowFromSystemServer();
    }

    public boolean requestBugreport(ComponentName componentname)
    {
        throwIfParentInstance("requestBugreport");
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.requestBugreport(componentname);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public boolean resetPassword(String s, int i)
    {
        throwIfParentInstance("resetPassword");
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.resetPassword(s, i);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                throw s.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public boolean resetPasswordWithToken(ComponentName componentname, String s, byte abyte0[], int i)
    {
        throwIfParentInstance("resetPassword");
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.resetPasswordWithToken(componentname, s, abyte0, i);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public List retrieveNetworkLogs(ComponentName componentname, long l)
    {
        throwIfParentInstance("retrieveNetworkLogs");
        try
        {
            componentname = mService.retrieveNetworkLogs(componentname, l);
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        return componentname;
    }

    public List retrievePreRebootSecurityLogs(ComponentName componentname)
    {
        throwIfParentInstance("retrievePreRebootSecurityLogs");
        try
        {
            componentname = mService.retrievePreRebootSecurityLogs(componentname);
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        if(componentname == null)
            break MISSING_BLOCK_LABEL_29;
        componentname = componentname.getList();
        return componentname;
        return null;
    }

    public List retrieveSecurityLogs(ComponentName componentname)
    {
        throwIfParentInstance("retrieveSecurityLogs");
        try
        {
            componentname = mService.retrieveSecurityLogs(componentname);
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        if(componentname == null)
            break MISSING_BLOCK_LABEL_29;
        componentname = componentname.getList();
        return componentname;
        return null;
    }

    public void setAccountManagementDisabled(ComponentName componentname, String s, boolean flag)
    {
        throwIfParentInstance("setAccountManagementDisabled");
        if(mService == null)
            break MISSING_BLOCK_LABEL_26;
        mService.setAccountManagementDisabled(componentname, s, flag);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void setActiveAdmin(ComponentName componentname, boolean flag)
    {
        setActiveAdmin(componentname, flag, myUserId());
    }

    public void setActiveAdmin(ComponentName componentname, boolean flag, int i)
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_19;
        mService.setActiveAdmin(componentname, flag, i);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void setActivePasswordState(PasswordMetrics passwordmetrics, int i)
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_18;
        mService.setActivePasswordState(passwordmetrics, i);
        return;
        passwordmetrics;
        throw passwordmetrics.rethrowFromSystemServer();
    }

    public boolean setActiveProfileOwner(ComponentName componentname, String s)
        throws IllegalArgumentException
    {
        throwIfParentInstance("setActiveProfileOwner");
        if(mService != null)
        {
            boolean flag;
            try
            {
                int i = myUserId();
                mService.setActiveAdmin(componentname, false, i);
                flag = mService.setProfileOwner(componentname, s, i);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public void setAffiliationIds(ComponentName componentname, Set set)
    {
        throwIfParentInstance("setAffiliationIds");
        if(set == null)
            throw new IllegalArgumentException("ids must not be null");
        try
        {
            IDevicePolicyManager idevicepolicymanager = mService;
            ArrayList arraylist = JVM INSTR new #732 <Class ArrayList>;
            arraylist.ArrayList(set);
            idevicepolicymanager.setAffiliationIds(componentname, arraylist);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
    }

    public void setAlwaysOnVpnPackage(ComponentName componentname, String s, boolean flag)
        throws android.content.pm.PackageManager.NameNotFoundException, UnsupportedOperationException
    {
        throwIfParentInstance("setAlwaysOnVpnPackage");
        if(mService != null)
            try
            {
                if(!mService.setAlwaysOnVpnPackage(componentname, s, flag))
                {
                    componentname = JVM INSTR new #1214 <Class android.content.pm.PackageManager$NameNotFoundException>;
                    componentname.android.content.pm.PackageManager.NameNotFoundException(s);
                    throw componentname;
                }
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
    }

    public boolean setApplicationHidden(ComponentName componentname, String s, boolean flag)
    {
        throwIfParentInstance("setApplicationHidden");
        if(mService != null)
        {
            try
            {
                flag = mService.setApplicationHidden(componentname, mContext.getPackageName(), s, flag);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public void setApplicationRestrictions(ComponentName componentname, String s, Bundle bundle)
    {
        throwIfParentInstance("setApplicationRestrictions");
        if(mService == null)
            break MISSING_BLOCK_LABEL_33;
        mService.setApplicationRestrictions(componentname, mContext.getPackageName(), s, bundle);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void setApplicationRestrictionsManagingPackage(ComponentName componentname, String s)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        throwIfParentInstance("setApplicationRestrictionsManagingPackage");
        if(mService != null)
            try
            {
                if(!mService.setApplicationRestrictionsManagingPackage(componentname, s))
                {
                    componentname = JVM INSTR new #1214 <Class android.content.pm.PackageManager$NameNotFoundException>;
                    componentname.android.content.pm.PackageManager.NameNotFoundException(s);
                    throw componentname;
                }
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
    }

    public void setAutoTimeRequired(ComponentName componentname, boolean flag)
    {
        throwIfParentInstance("setAutoTimeRequired");
        if(mService == null)
            break MISSING_BLOCK_LABEL_25;
        mService.setAutoTimeRequired(componentname, flag);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void setBackupServiceEnabled(ComponentName componentname, boolean flag)
    {
        throwIfParentInstance("setBackupServiceEnabled");
        try
        {
            mService.setBackupServiceEnabled(componentname, flag);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
    }

    public void setBluetoothContactSharingDisabled(ComponentName componentname, boolean flag)
    {
        throwIfParentInstance("setBluetoothContactSharingDisabled");
        if(mService == null)
            break MISSING_BLOCK_LABEL_25;
        mService.setBluetoothContactSharingDisabled(componentname, flag);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void setCameraDisabled(ComponentName componentname, boolean flag)
    {
        throwIfParentInstance("setCameraDisabled");
        if(mService == null)
            break MISSING_BLOCK_LABEL_25;
        mService.setCameraDisabled(componentname, flag);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void setCertInstallerPackage(ComponentName componentname, String s)
        throws SecurityException
    {
        throwIfParentInstance("setCertInstallerPackage");
        if(mService == null)
            break MISSING_BLOCK_LABEL_25;
        mService.setCertInstallerPackage(componentname, s);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void setCrossProfileCallerIdDisabled(ComponentName componentname, boolean flag)
    {
        throwIfParentInstance("setCrossProfileCallerIdDisabled");
        if(mService == null)
            break MISSING_BLOCK_LABEL_25;
        mService.setCrossProfileCallerIdDisabled(componentname, flag);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void setCrossProfileContactsSearchDisabled(ComponentName componentname, boolean flag)
    {
        throwIfParentInstance("setCrossProfileContactsSearchDisabled");
        if(mService == null)
            break MISSING_BLOCK_LABEL_25;
        mService.setCrossProfileContactsSearchDisabled(componentname, flag);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void setDelegatedScopes(ComponentName componentname, String s, List list)
    {
        throwIfParentInstance("setDelegatedScopes");
        if(mService == null)
            break MISSING_BLOCK_LABEL_26;
        mService.setDelegatedScopes(componentname, s, list);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public boolean setDeviceOwner(ComponentName componentname)
    {
        return setDeviceOwner(componentname, ((String) (null)));
    }

    public boolean setDeviceOwner(ComponentName componentname, int i)
    {
        return setDeviceOwner(componentname, null, i);
    }

    public boolean setDeviceOwner(ComponentName componentname, String s)
    {
        return setDeviceOwner(componentname, s, 0);
    }

    public boolean setDeviceOwner(ComponentName componentname, String s, int i)
        throws IllegalArgumentException, IllegalStateException
    {
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.setDeviceOwner(componentname, s, i);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public void setDeviceOwnerLockScreenInfo(ComponentName componentname, CharSequence charsequence)
    {
        throwIfParentInstance("setDeviceOwnerLockScreenInfo");
        if(mService == null)
            break MISSING_BLOCK_LABEL_25;
        mService.setDeviceOwnerLockScreenInfo(componentname, charsequence);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void setDeviceProvisioningConfigApplied()
    {
        try
        {
            mService.setDeviceProvisioningConfigApplied();
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void setForceEphemeralUsers(ComponentName componentname, boolean flag)
    {
        throwIfParentInstance("setForceEphemeralUsers");
        if(mService == null)
            break MISSING_BLOCK_LABEL_25;
        mService.setForceEphemeralUsers(componentname, flag);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public ComponentName setGlobalProxy(ComponentName componentname, Proxy proxy, List list)
    {
        throwIfParentInstance("setGlobalProxy");
        if(proxy == null)
            throw new NullPointerException();
        if(mService == null)
            break MISSING_BLOCK_LABEL_253;
        if(!proxy.equals(Proxy.NO_PROXY)) goto _L2; else goto _L1
_L1:
        Object obj;
        list = null;
        obj = null;
_L6:
        return mService.setGlobalProxy(componentname, list, ((String) (obj)));
_L2:
        if(!proxy.type().equals(java.net.Proxy.Type.HTTP))
        {
            componentname = JVM INSTR new #979 <Class IllegalArgumentException>;
            componentname.IllegalArgumentException();
            throw componentname;
        }
        String s;
        int i;
        String s1;
        try
        {
            proxy = (InetSocketAddress)proxy.address();
            s = proxy.getHostName();
            i = proxy.getPort();
            proxy = JVM INSTR new #430 <Class StringBuilder>;
            proxy.StringBuilder();
            s1 = proxy.append(s).append(":").append(Integer.toString(i)).toString();
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        if(list != null) goto _L4; else goto _L3
_L3:
        proxy = "";
_L9:
        obj = proxy;
        list = s1;
        if(android.net.Proxy.validate(s, Integer.toString(i), proxy) == 0) goto _L6; else goto _L5
_L5:
        componentname = JVM INSTR new #979 <Class IllegalArgumentException>;
        componentname.IllegalArgumentException();
        throw componentname;
_L4:
        proxy = JVM INSTR new #430 <Class StringBuilder>;
        proxy.StringBuilder();
        boolean flag = true;
        list = list.iterator();
_L7:
        if(!list.hasNext())
            break MISSING_BLOCK_LABEL_245;
        obj = (String)list.next();
        if(flag)
            break MISSING_BLOCK_LABEL_239;
        proxy = proxy.append(",");
_L8:
        proxy = proxy.append(((String) (obj)).trim());
          goto _L7
        flag = false;
          goto _L8
        proxy = proxy.toString();
          goto _L9
        return null;
    }

    public void setGlobalSetting(ComponentName componentname, String s, String s1)
    {
        throwIfParentInstance("setGlobalSetting");
        if(mService == null)
            break MISSING_BLOCK_LABEL_26;
        mService.setGlobalSetting(componentname, s, s1);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void setKeepUninstalledPackages(ComponentName componentname, List list)
    {
        throwIfParentInstance("setKeepUninstalledPackages");
        if(mService == null)
            break MISSING_BLOCK_LABEL_32;
        mService.setKeepUninstalledPackages(componentname, mContext.getPackageName(), list);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public boolean setKeyguardDisabled(ComponentName componentname, boolean flag)
    {
        throwIfParentInstance("setKeyguardDisabled");
        try
        {
            flag = mService.setKeyguardDisabled(componentname, flag);
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        return flag;
    }

    public void setKeyguardDisabledFeatures(ComponentName componentname, int i)
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_22;
        mService.setKeyguardDisabledFeatures(componentname, i, mParentInstance);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void setLockTaskPackages(ComponentName componentname, String as[])
        throws SecurityException
    {
        throwIfParentInstance("setLockTaskPackages");
        if(mService == null)
            break MISSING_BLOCK_LABEL_25;
        mService.setLockTaskPackages(componentname, as);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void setLongSupportMessage(ComponentName componentname, CharSequence charsequence)
    {
        throwIfParentInstance("setLongSupportMessage");
        if(mService == null)
            break MISSING_BLOCK_LABEL_25;
        mService.setLongSupportMessage(componentname, charsequence);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void setMasterVolumeMuted(ComponentName componentname, boolean flag)
    {
        throwIfParentInstance("setMasterVolumeMuted");
        if(mService == null)
            break MISSING_BLOCK_LABEL_25;
        mService.setMasterVolumeMuted(componentname, flag);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void setMaximumFailedPasswordsForWipe(ComponentName componentname, int i)
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_22;
        mService.setMaximumFailedPasswordsForWipe(componentname, i, mParentInstance);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void setMaximumTimeToLock(ComponentName componentname, long l)
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_22;
        mService.setMaximumTimeToLock(componentname, l, mParentInstance);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void setNetworkLoggingEnabled(ComponentName componentname, boolean flag)
    {
        throwIfParentInstance("setNetworkLoggingEnabled");
        try
        {
            mService.setNetworkLoggingEnabled(componentname, flag);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
    }

    public void setOrganizationColor(ComponentName componentname, int i)
    {
        throwIfParentInstance("setOrganizationColor");
        try
        {
            mService.setOrganizationColor(componentname, i | 0xff000000);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
    }

    public void setOrganizationColorForUser(int i, int j)
    {
        try
        {
            mService.setOrganizationColorForUser(i | 0xff000000, j);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void setOrganizationName(ComponentName componentname, CharSequence charsequence)
    {
        throwIfParentInstance("setOrganizationName");
        try
        {
            mService.setOrganizationName(componentname, charsequence);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
    }

    public String[] setPackagesSuspended(ComponentName componentname, String as[], boolean flag)
    {
        throwIfParentInstance("setPackagesSuspended");
        if(mService != null)
        {
            try
            {
                componentname = mService.setPackagesSuspended(componentname, mContext.getPackageName(), as, flag);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return componentname;
        } else
        {
            return as;
        }
    }

    public void setPasswordExpirationTimeout(ComponentName componentname, long l)
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_22;
        mService.setPasswordExpirationTimeout(componentname, l, mParentInstance);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void setPasswordHistoryLength(ComponentName componentname, int i)
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_22;
        mService.setPasswordHistoryLength(componentname, i, mParentInstance);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void setPasswordMinimumLength(ComponentName componentname, int i)
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_22;
        mService.setPasswordMinimumLength(componentname, i, mParentInstance);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void setPasswordMinimumLetters(ComponentName componentname, int i)
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_22;
        mService.setPasswordMinimumLetters(componentname, i, mParentInstance);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void setPasswordMinimumLowerCase(ComponentName componentname, int i)
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_22;
        mService.setPasswordMinimumLowerCase(componentname, i, mParentInstance);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void setPasswordMinimumNonLetter(ComponentName componentname, int i)
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_22;
        mService.setPasswordMinimumNonLetter(componentname, i, mParentInstance);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void setPasswordMinimumNumeric(ComponentName componentname, int i)
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_22;
        mService.setPasswordMinimumNumeric(componentname, i, mParentInstance);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void setPasswordMinimumSymbols(ComponentName componentname, int i)
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_22;
        mService.setPasswordMinimumSymbols(componentname, i, mParentInstance);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void setPasswordMinimumUpperCase(ComponentName componentname, int i)
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_22;
        mService.setPasswordMinimumUpperCase(componentname, i, mParentInstance);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void setPasswordQuality(ComponentName componentname, int i)
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_22;
        mService.setPasswordQuality(componentname, i, mParentInstance);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public boolean setPermissionGrantState(ComponentName componentname, String s, String s1, int i)
    {
        throwIfParentInstance("setPermissionGrantState");
        boolean flag;
        try
        {
            flag = mService.setPermissionGrantState(componentname, mContext.getPackageName(), s, s1, i);
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        return flag;
    }

    public void setPermissionPolicy(ComponentName componentname, int i)
    {
        throwIfParentInstance("setPermissionPolicy");
        try
        {
            mService.setPermissionPolicy(componentname, mContext.getPackageName(), i);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
    }

    public boolean setPermittedAccessibilityServices(ComponentName componentname, List list)
    {
        throwIfParentInstance("setPermittedAccessibilityServices");
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.setPermittedAccessibilityServices(componentname, list);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public boolean setPermittedCrossProfileNotificationListeners(ComponentName componentname, List list)
    {
        throwIfParentInstance("setPermittedCrossProfileNotificationListeners");
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.setPermittedCrossProfileNotificationListeners(componentname, list);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public boolean setPermittedInputMethods(ComponentName componentname, List list)
    {
        throwIfParentInstance("setPermittedInputMethods");
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.setPermittedInputMethods(componentname, list);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public void setProfileEnabled(ComponentName componentname)
    {
        throwIfParentInstance("setProfileEnabled");
        if(mService == null)
            break MISSING_BLOCK_LABEL_24;
        mService.setProfileEnabled(componentname);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void setProfileName(ComponentName componentname, String s)
    {
        throwIfParentInstance("setProfileName");
        if(mService == null)
            break MISSING_BLOCK_LABEL_25;
        mService.setProfileName(componentname, s);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public boolean setProfileOwner(ComponentName componentname, String s, int i)
        throws IllegalArgumentException
    {
        if(mService != null)
        {
            String s1 = s;
            if(s == null)
                s1 = "";
            boolean flag;
            try
            {
                flag = mService.setProfileOwner(componentname, s1, i);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public void setRecommendedGlobalProxy(ComponentName componentname, ProxyInfo proxyinfo)
    {
        throwIfParentInstance("setRecommendedGlobalProxy");
        if(mService == null)
            break MISSING_BLOCK_LABEL_25;
        mService.setRecommendedGlobalProxy(componentname, proxyinfo);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void setRequiredStrongAuthTimeout(ComponentName componentname, long l)
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_22;
        mService.setRequiredStrongAuthTimeout(componentname, l, mParentInstance);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public boolean setResetPasswordToken(ComponentName componentname, byte abyte0[])
    {
        throwIfParentInstance("setResetPasswordToken");
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.setResetPasswordToken(componentname, abyte0);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public void setRestrictionsProvider(ComponentName componentname, ComponentName componentname1)
    {
        throwIfParentInstance("setRestrictionsProvider");
        if(mService == null)
            break MISSING_BLOCK_LABEL_25;
        mService.setRestrictionsProvider(componentname, componentname1);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void setScreenCaptureDisabled(ComponentName componentname, boolean flag)
    {
        throwIfParentInstance("setScreenCaptureDisabled");
        if(mService == null)
            break MISSING_BLOCK_LABEL_25;
        mService.setScreenCaptureDisabled(componentname, flag);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void setSecureSetting(ComponentName componentname, String s, String s1)
    {
        throwIfParentInstance("setSecureSetting");
        if(mService == null)
            break MISSING_BLOCK_LABEL_26;
        mService.setSecureSetting(componentname, s, s1);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void setSecurityLoggingEnabled(ComponentName componentname, boolean flag)
    {
        throwIfParentInstance("setSecurityLoggingEnabled");
        try
        {
            mService.setSecurityLoggingEnabled(componentname, flag);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
    }

    public void setShortSupportMessage(ComponentName componentname, CharSequence charsequence)
    {
        throwIfParentInstance("setShortSupportMessage");
        if(mService == null)
            break MISSING_BLOCK_LABEL_25;
        mService.setShortSupportMessage(componentname, charsequence);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public boolean setStatusBarDisabled(ComponentName componentname, boolean flag)
    {
        throwIfParentInstance("setStatusBarDisabled");
        try
        {
            flag = mService.setStatusBarDisabled(componentname, flag);
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        return flag;
    }

    public int setStorageEncryption(ComponentName componentname, boolean flag)
    {
        throwIfParentInstance("setStorageEncryption");
        if(mService != null)
        {
            int i;
            try
            {
                i = mService.setStorageEncryption(componentname, flag);
            }
            // Misplaced declaration of an exception variable
            catch(ComponentName componentname)
            {
                throw componentname.rethrowFromSystemServer();
            }
            return i;
        } else
        {
            return 0;
        }
    }

    public void setSystemUpdatePolicy(ComponentName componentname, SystemUpdatePolicy systemupdatepolicy)
    {
        throwIfParentInstance("setSystemUpdatePolicy");
        if(mService == null)
            break MISSING_BLOCK_LABEL_25;
        mService.setSystemUpdatePolicy(componentname, systemupdatepolicy);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void setTrustAgentConfiguration(ComponentName componentname, ComponentName componentname1, PersistableBundle persistablebundle)
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_23;
        mService.setTrustAgentConfiguration(componentname, componentname1, persistablebundle, mParentInstance);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void setUninstallBlocked(ComponentName componentname, String s, boolean flag)
    {
        throwIfParentInstance("setUninstallBlocked");
        if(mService == null)
            break MISSING_BLOCK_LABEL_33;
        mService.setUninstallBlocked(componentname, mContext.getPackageName(), s, flag);
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void setUserIcon(ComponentName componentname, Bitmap bitmap)
    {
        throwIfParentInstance("setUserIcon");
        try
        {
            mService.setUserIcon(componentname, bitmap);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
    }

    public void setUserProvisioningState(int i, int j)
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_18;
        mService.setUserProvisioningState(i, j);
        return;
        RemoteException remoteexception;
        remoteexception;
        throw remoteexception.rethrowFromSystemServer();
    }

    public void startManagedQuickContact(String s, long l, Intent intent)
    {
        startManagedQuickContact(s, l, false, 0L, intent);
    }

    public void startManagedQuickContact(String s, long l, boolean flag, long l1, Intent intent)
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_24;
        mService.startManagedQuickContact(s, l, flag, l1, intent);
        return;
        s;
        throw s.rethrowFromSystemServer();
    }

    public boolean switchUser(ComponentName componentname, UserHandle userhandle)
    {
        throwIfParentInstance("switchUser");
        boolean flag;
        try
        {
            flag = mService.switchUser(componentname, userhandle);
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        return flag;
    }

    public void uninstallAllUserCaCerts(ComponentName componentname)
    {
        throwIfParentInstance("uninstallAllUserCaCerts");
        if(mService == null)
            break MISSING_BLOCK_LABEL_62;
        IDevicePolicyManager idevicepolicymanager = mService;
        String s = mContext.getPackageName();
        TrustedCertificateStore trustedcertificatestore = JVM INSTR new #389 <Class TrustedCertificateStore>;
        trustedcertificatestore.TrustedCertificateStore();
        idevicepolicymanager.uninstallCaCerts(componentname, s, (String[])trustedcertificatestore.userAliases().toArray(new String[0]));
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
    }

    public void uninstallCaCert(ComponentName componentname, byte abyte0[])
    {
        throwIfParentInstance("uninstallCaCert");
        if(mService == null)
            break MISSING_BLOCK_LABEL_44;
        abyte0 = getCaCertAlias(abyte0);
        mService.uninstallCaCerts(componentname, mContext.getPackageName(), new String[] {
            abyte0
        });
_L1:
        return;
        componentname;
        throw componentname.rethrowFromSystemServer();
        componentname;
        Log.w(TAG, "Unable to parse certificate", componentname);
          goto _L1
    }

    public void uninstallPackageWithActiveAdmins(String s)
    {
        try
        {
            mService.uninstallPackageWithActiveAdmins(s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void wipeData(int i)
    {
        throwIfParentInstance("wipeData");
        if(mService == null)
            break MISSING_BLOCK_LABEL_24;
        mService.wipeData(i);
        return;
        RemoteException remoteexception;
        remoteexception;
        throw remoteexception.rethrowFromSystemServer();
    }

    public static final String ACCOUNT_FEATURE_DEVICE_OR_PROFILE_OWNER_ALLOWED = "android.account.DEVICE_OR_PROFILE_OWNER_ALLOWED";
    public static final String ACCOUNT_FEATURE_DEVICE_OR_PROFILE_OWNER_DISALLOWED = "android.account.DEVICE_OR_PROFILE_OWNER_DISALLOWED";
    public static final String ACTION_ADD_DEVICE_ADMIN = "android.app.action.ADD_DEVICE_ADMIN";
    public static final String ACTION_APPLICATION_DELEGATION_SCOPES_CHANGED = "android.app.action.APPLICATION_DELEGATION_SCOPES_CHANGED";
    public static final String ACTION_BUGREPORT_SHARING_ACCEPTED = "com.android.server.action.REMOTE_BUGREPORT_SHARING_ACCEPTED";
    public static final String ACTION_BUGREPORT_SHARING_DECLINED = "com.android.server.action.REMOTE_BUGREPORT_SHARING_DECLINED";
    public static final String ACTION_DEVICE_ADMIN_SERVICE = "android.app.action.DEVICE_ADMIN_SERVICE";
    public static final String ACTION_DEVICE_OWNER_CHANGED = "android.app.action.DEVICE_OWNER_CHANGED";
    public static final String ACTION_DEVICE_POLICY_MANAGER_STATE_CHANGED = "android.app.action.DEVICE_POLICY_MANAGER_STATE_CHANGED";
    public static final String ACTION_MANAGED_PROFILE_PROVISIONED = "android.app.action.MANAGED_PROFILE_PROVISIONED";
    public static final String ACTION_PROVISIONING_SUCCESSFUL = "android.app.action.PROVISIONING_SUCCESSFUL";
    public static final String ACTION_PROVISION_FINALIZATION = "android.app.action.PROVISION_FINALIZATION";
    public static final String ACTION_PROVISION_MANAGED_DEVICE = "android.app.action.PROVISION_MANAGED_DEVICE";
    public static final String ACTION_PROVISION_MANAGED_DEVICE_FROM_TRUSTED_SOURCE = "android.app.action.PROVISION_MANAGED_DEVICE_FROM_TRUSTED_SOURCE";
    public static final String ACTION_PROVISION_MANAGED_PROFILE = "android.app.action.PROVISION_MANAGED_PROFILE";
    public static final String ACTION_PROVISION_MANAGED_SHAREABLE_DEVICE = "android.app.action.PROVISION_MANAGED_SHAREABLE_DEVICE";
    public static final String ACTION_PROVISION_MANAGED_USER = "android.app.action.PROVISION_MANAGED_USER";
    public static final String ACTION_REMOTE_BUGREPORT_DISPATCH = "android.intent.action.REMOTE_BUGREPORT_DISPATCH";
    public static final String ACTION_SET_NEW_PARENT_PROFILE_PASSWORD = "android.app.action.SET_NEW_PARENT_PROFILE_PASSWORD";
    public static final String ACTION_SET_NEW_PASSWORD = "android.app.action.SET_NEW_PASSWORD";
    public static final String ACTION_SET_PROFILE_OWNER = "android.app.action.SET_PROFILE_OWNER";
    public static final String ACTION_SHOW_DEVICE_MONITORING_DIALOG = "android.app.action.SHOW_DEVICE_MONITORING_DIALOG";
    public static final String ACTION_START_ENCRYPTION = "android.app.action.START_ENCRYPTION";
    public static final String ACTION_STATE_USER_SETUP_COMPLETE = "android.app.action.STATE_USER_SETUP_COMPLETE";
    public static final String ACTION_SYSTEM_UPDATE_POLICY_CHANGED = "android.app.action.SYSTEM_UPDATE_POLICY_CHANGED";
    public static final int CODE_ACCOUNTS_NOT_EMPTY = 6;
    public static final int CODE_ADD_MANAGED_PROFILE_DISALLOWED = 15;
    public static final int CODE_CANNOT_ADD_MANAGED_PROFILE = 11;
    public static final int CODE_DEVICE_ADMIN_NOT_SUPPORTED = 13;
    public static final int CODE_HAS_DEVICE_OWNER = 1;
    public static final int CODE_HAS_PAIRED = 8;
    public static final int CODE_MANAGED_USERS_NOT_SUPPORTED = 9;
    public static final int CODE_NONSYSTEM_USER_EXISTS = 5;
    public static final int CODE_NOT_SYSTEM_USER = 7;
    public static final int CODE_NOT_SYSTEM_USER_SPLIT = 12;
    public static final int CODE_OK = 0;
    public static final int CODE_SPLIT_SYSTEM_USER_DEVICE_SYSTEM_USER = 14;
    public static final int CODE_SYSTEM_USER = 10;
    public static final int CODE_USER_HAS_PROFILE_OWNER = 2;
    public static final int CODE_USER_NOT_RUNNING = 3;
    public static final int CODE_USER_SETUP_COMPLETED = 4;
    public static final long DEFAULT_STRONG_AUTH_TIMEOUT_MS = 0xf731400L;
    public static final String DELEGATION_APP_RESTRICTIONS = "delegation-app-restrictions";
    public static final String DELEGATION_BLOCK_UNINSTALL = "delegation-block-uninstall";
    public static final String DELEGATION_CERT_INSTALL = "delegation-cert-install";
    public static final String DELEGATION_ENABLE_SYSTEM_APP = "delegation-enable-system-app";
    public static final String DELEGATION_KEEP_UNINSTALLED_PACKAGES = "delegation-keep-uninstalled-packages";
    public static final String DELEGATION_PACKAGE_ACCESS = "delegation-package-access";
    public static final String DELEGATION_PERMISSION_GRANT = "delegation-permission-grant";
    public static final int ENCRYPTION_STATUS_ACTIVATING = 2;
    public static final int ENCRYPTION_STATUS_ACTIVE = 3;
    public static final int ENCRYPTION_STATUS_ACTIVE_DEFAULT_KEY = 4;
    public static final int ENCRYPTION_STATUS_ACTIVE_PER_USER = 5;
    public static final int ENCRYPTION_STATUS_INACTIVE = 1;
    public static final int ENCRYPTION_STATUS_UNSUPPORTED = 0;
    public static final String EXTRA_ADD_EXPLANATION = "android.app.extra.ADD_EXPLANATION";
    public static final String EXTRA_BUGREPORT_NOTIFICATION_TYPE = "android.app.extra.bugreport_notification_type";
    public static final String EXTRA_DELEGATION_SCOPES = "android.app.extra.DELEGATION_SCOPES";
    public static final String EXTRA_DEVICE_ADMIN = "android.app.extra.DEVICE_ADMIN";
    public static final String EXTRA_PROFILE_OWNER_NAME = "android.app.extra.PROFILE_OWNER_NAME";
    public static final String EXTRA_PROVISIONING_ACCOUNT_TO_MIGRATE = "android.app.extra.PROVISIONING_ACCOUNT_TO_MIGRATE";
    public static final String EXTRA_PROVISIONING_ADMIN_EXTRAS_BUNDLE = "android.app.extra.PROVISIONING_ADMIN_EXTRAS_BUNDLE";
    public static final String EXTRA_PROVISIONING_DEVICE_ADMIN_COMPONENT_NAME = "android.app.extra.PROVISIONING_DEVICE_ADMIN_COMPONENT_NAME";
    public static final String EXTRA_PROVISIONING_DEVICE_ADMIN_MINIMUM_VERSION_CODE = "android.app.extra.PROVISIONING_DEVICE_ADMIN_MINIMUM_VERSION_CODE";
    public static final String EXTRA_PROVISIONING_DEVICE_ADMIN_PACKAGE_CHECKSUM = "android.app.extra.PROVISIONING_DEVICE_ADMIN_PACKAGE_CHECKSUM";
    public static final String EXTRA_PROVISIONING_DEVICE_ADMIN_PACKAGE_DOWNLOAD_COOKIE_HEADER = "android.app.extra.PROVISIONING_DEVICE_ADMIN_PACKAGE_DOWNLOAD_COOKIE_HEADER";
    public static final String EXTRA_PROVISIONING_DEVICE_ADMIN_PACKAGE_DOWNLOAD_LOCATION = "android.app.extra.PROVISIONING_DEVICE_ADMIN_PACKAGE_DOWNLOAD_LOCATION";
    public static final String EXTRA_PROVISIONING_DEVICE_ADMIN_PACKAGE_ICON_URI = "android.app.extra.PROVISIONING_DEVICE_ADMIN_PACKAGE_ICON_URI";
    public static final String EXTRA_PROVISIONING_DEVICE_ADMIN_PACKAGE_LABEL = "android.app.extra.PROVISIONING_DEVICE_ADMIN_PACKAGE_LABEL";
    public static final String EXTRA_PROVISIONING_DEVICE_ADMIN_PACKAGE_NAME = "android.app.extra.PROVISIONING_DEVICE_ADMIN_PACKAGE_NAME";
    public static final String EXTRA_PROVISIONING_DEVICE_ADMIN_SIGNATURE_CHECKSUM = "android.app.extra.PROVISIONING_DEVICE_ADMIN_SIGNATURE_CHECKSUM";
    public static final String EXTRA_PROVISIONING_DISCLAIMERS = "android.app.extra.PROVISIONING_DISCLAIMERS";
    public static final String EXTRA_PROVISIONING_DISCLAIMER_CONTENT = "android.app.extra.PROVISIONING_DISCLAIMER_CONTENT";
    public static final String EXTRA_PROVISIONING_DISCLAIMER_HEADER = "android.app.extra.PROVISIONING_DISCLAIMER_HEADER";
    public static final String EXTRA_PROVISIONING_EMAIL_ADDRESS = "android.app.extra.PROVISIONING_EMAIL_ADDRESS";
    public static final String EXTRA_PROVISIONING_KEEP_ACCOUNT_ON_MIGRATION = "android.app.extra.PROVISIONING_KEEP_ACCOUNT_ON_MIGRATION";
    public static final String EXTRA_PROVISIONING_LEAVE_ALL_SYSTEM_APPS_ENABLED = "android.app.extra.PROVISIONING_LEAVE_ALL_SYSTEM_APPS_ENABLED";
    public static final String EXTRA_PROVISIONING_LOCALE = "android.app.extra.PROVISIONING_LOCALE";
    public static final String EXTRA_PROVISIONING_LOCAL_TIME = "android.app.extra.PROVISIONING_LOCAL_TIME";
    public static final String EXTRA_PROVISIONING_LOGO_URI = "android.app.extra.PROVISIONING_LOGO_URI";
    public static final String EXTRA_PROVISIONING_MAIN_COLOR = "android.app.extra.PROVISIONING_MAIN_COLOR";
    public static final String EXTRA_PROVISIONING_ORGANIZATION_NAME = "android.app.extra.PROVISIONING_ORGANIZATION_NAME";
    public static final String EXTRA_PROVISIONING_SKIP_ENCRYPTION = "android.app.extra.PROVISIONING_SKIP_ENCRYPTION";
    public static final String EXTRA_PROVISIONING_SKIP_USER_CONSENT = "android.app.extra.PROVISIONING_SKIP_USER_CONSENT";
    public static final String EXTRA_PROVISIONING_SKIP_USER_SETUP = "android.app.extra.PROVISIONING_SKIP_USER_SETUP";
    public static final String EXTRA_PROVISIONING_SUPPORT_URL = "android.app.extra.PROVISIONING_SUPPORT_URL";
    public static final String EXTRA_PROVISIONING_TIME_ZONE = "android.app.extra.PROVISIONING_TIME_ZONE";
    public static final String EXTRA_PROVISIONING_WIFI_HIDDEN = "android.app.extra.PROVISIONING_WIFI_HIDDEN";
    public static final String EXTRA_PROVISIONING_WIFI_PAC_URL = "android.app.extra.PROVISIONING_WIFI_PAC_URL";
    public static final String EXTRA_PROVISIONING_WIFI_PASSWORD = "android.app.extra.PROVISIONING_WIFI_PASSWORD";
    public static final String EXTRA_PROVISIONING_WIFI_PROXY_BYPASS = "android.app.extra.PROVISIONING_WIFI_PROXY_BYPASS";
    public static final String EXTRA_PROVISIONING_WIFI_PROXY_HOST = "android.app.extra.PROVISIONING_WIFI_PROXY_HOST";
    public static final String EXTRA_PROVISIONING_WIFI_PROXY_PORT = "android.app.extra.PROVISIONING_WIFI_PROXY_PORT";
    public static final String EXTRA_PROVISIONING_WIFI_SECURITY_TYPE = "android.app.extra.PROVISIONING_WIFI_SECURITY_TYPE";
    public static final String EXTRA_PROVISIONING_WIFI_SSID = "android.app.extra.PROVISIONING_WIFI_SSID";
    public static final String EXTRA_REMOTE_BUGREPORT_HASH = "android.intent.extra.REMOTE_BUGREPORT_HASH";
    public static final String EXTRA_RESTRICTION = "android.app.extra.RESTRICTION";
    public static final int FLAG_EVICT_CREDENTIAL_ENCRYPTION_KEY = 1;
    public static final int FLAG_MANAGED_CAN_ACCESS_PARENT = 2;
    public static final int FLAG_PARENT_CAN_ACCESS_MANAGED = 1;
    public static final int KEYGUARD_DISABLE_FEATURES_ALL = 0x7fffffff;
    public static final int KEYGUARD_DISABLE_FEATURES_NONE = 0;
    public static final int KEYGUARD_DISABLE_FINGERPRINT = 32;
    public static final int KEYGUARD_DISABLE_REMOTE_INPUT = 64;
    public static final int KEYGUARD_DISABLE_SECURE_CAMERA = 2;
    public static final int KEYGUARD_DISABLE_SECURE_NOTIFICATIONS = 4;
    public static final int KEYGUARD_DISABLE_TRUST_AGENTS = 16;
    public static final int KEYGUARD_DISABLE_UNREDACTED_NOTIFICATIONS = 8;
    public static final int KEYGUARD_DISABLE_WIDGETS_ALL = 1;
    public static final int MAKE_USER_DEMO = 4;
    public static final int MAKE_USER_EPHEMERAL = 2;
    public static final String MIME_TYPE_PROVISIONING_NFC = "application/com.android.managedprovisioning";
    public static final int NOTIFICATION_BUGREPORT_ACCEPTED_NOT_FINISHED = 2;
    public static final int NOTIFICATION_BUGREPORT_FINISHED_NOT_ACCEPTED = 3;
    public static final int NOTIFICATION_BUGREPORT_STARTED = 1;
    public static final int PASSWORD_QUALITY_ALPHABETIC = 0x40000;
    public static final int PASSWORD_QUALITY_ALPHANUMERIC = 0x50000;
    public static final int PASSWORD_QUALITY_BIOMETRIC_WEAK = 32768;
    public static final int PASSWORD_QUALITY_COMPLEX = 0x60000;
    public static final int PASSWORD_QUALITY_MANAGED = 0x80000;
    public static final int PASSWORD_QUALITY_NUMERIC = 0x20000;
    public static final int PASSWORD_QUALITY_NUMERIC_COMPLEX = 0x30000;
    public static final int PASSWORD_QUALITY_SOMETHING = 0x10000;
    public static final int PASSWORD_QUALITY_UNSPECIFIED = 0;
    public static final int PERMISSION_GRANT_STATE_DEFAULT = 0;
    public static final int PERMISSION_GRANT_STATE_DENIED = 2;
    public static final int PERMISSION_GRANT_STATE_GRANTED = 1;
    public static final int PERMISSION_POLICY_AUTO_DENY = 2;
    public static final int PERMISSION_POLICY_AUTO_GRANT = 1;
    public static final int PERMISSION_POLICY_PROMPT = 0;
    public static final String POLICY_DISABLE_CAMERA = "policy_disable_camera";
    public static final String POLICY_DISABLE_SCREEN_CAPTURE = "policy_disable_screen_capture";
    public static final int PROFILE_KEYGUARD_FEATURES_AFFECT_OWNER = 48;
    public static final int RESET_PASSWORD_DO_NOT_ASK_CREDENTIALS_ON_BOOT = 2;
    public static final int RESET_PASSWORD_REQUIRE_ENTRY = 1;
    public static final int SKIP_SETUP_WIZARD = 1;
    public static final int STATE_USER_PROFILE_COMPLETE = 4;
    public static final int STATE_USER_SETUP_COMPLETE = 2;
    public static final int STATE_USER_SETUP_FINALIZED = 3;
    public static final int STATE_USER_SETUP_INCOMPLETE = 1;
    public static final int STATE_USER_UNMANAGED = 0;
    private static String TAG = "DevicePolicyManager";
    public static final int WIPE_EUICC = 4;
    public static final int WIPE_EXTERNAL_STORAGE = 1;
    public static final int WIPE_RESET_PROTECTION_DATA = 2;
    private final Context mContext;
    private final boolean mParentInstance;
    private final IDevicePolicyManager mService;

}
