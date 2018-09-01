// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.app.admin.DevicePolicyManager;
import android.app.admin.PasswordMetrics;
import android.app.trust.TrustManager;
import android.content.*;
import android.content.pm.UserInfo;
import android.content.res.Resources;
import android.os.*;
import android.os.storage.IStorageManager;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseIntArray;
import com.google.android.collect.Lists;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.*;
import libcore.util.HexEncoding;
import miui.securityspace.XSpaceUserHandle;

// Referenced classes of package com.android.internal.widget:
//            ILockSettings, VerifyCredentialResponse, ICheckCredentialProgressCallback

public class LockPatternUtils
{
    public static interface CheckCredentialProgressCallback
    {

        public abstract void onEarlyMatched();
    }

    public static final class RequestThrottledException extends Exception
    {

        public int getTimeoutMs()
        {
            return mTimeoutMs;
        }

        private int mTimeoutMs;

        public RequestThrottledException(int i)
        {
            mTimeoutMs = i;
        }
    }

    public static class StrongAuthTracker
    {

        static H _2D_get0(StrongAuthTracker strongauthtracker)
        {
            return strongauthtracker.mHandler;
        }

        public static int getDefaultFlags(Context context)
        {
            int i;
            if(context.getResources().getBoolean(0x11200a7))
                i = 1;
            else
                i = 0;
            return i;
        }

        public int getStrongAuthForUser(int i)
        {
            return mStrongAuthRequiredForUser.get(i, mDefaultStrongAuthFlags);
        }

        protected void handleStrongAuthRequiredChanged(int i, int j)
        {
            if(i != getStrongAuthForUser(j))
            {
                if(i == mDefaultStrongAuthFlags)
                    mStrongAuthRequiredForUser.delete(j);
                else
                    mStrongAuthRequiredForUser.put(j, i);
                onStrongAuthRequiredChanged(j);
            }
        }

        public boolean isFingerprintAllowedForUser(int i)
        {
            boolean flag = false;
            if((getStrongAuthForUser(i) & -5) == 0)
                flag = true;
            return flag;
        }

        public boolean isTrustAllowedForUser(int i)
        {
            boolean flag = false;
            if(getStrongAuthForUser(i) == 0)
                flag = true;
            return flag;
        }

        public void onStrongAuthRequiredChanged(int i)
        {
        }

        private static final int ALLOWING_FINGERPRINT = 4;
        public static final int SOME_AUTH_REQUIRED_AFTER_USER_REQUEST = 4;
        public static final int STRONG_AUTH_NOT_REQUIRED = 0;
        public static final int STRONG_AUTH_REQUIRED_AFTER_BOOT = 1;
        public static final int STRONG_AUTH_REQUIRED_AFTER_DPM_LOCK_NOW = 2;
        public static final int STRONG_AUTH_REQUIRED_AFTER_LOCKOUT = 8;
        public static final int STRONG_AUTH_REQUIRED_AFTER_TIMEOUT = 16;
        private final int mDefaultStrongAuthFlags;
        private final H mHandler;
        private final SparseIntArray mStrongAuthRequiredForUser;
        protected final android.app.trust.IStrongAuthTracker.Stub mStub;

        public StrongAuthTracker(Context context)
        {
            this(context, Looper.myLooper());
        }

        public StrongAuthTracker(Context context, Looper looper)
        {
            mStrongAuthRequiredForUser = new SparseIntArray();
            mStub = new _cls1();
            mHandler = new H(looper);
            mDefaultStrongAuthFlags = getDefaultFlags(context);
        }
    }

    private class StrongAuthTracker.H extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 1: default 24
        //                       1 25;
               goto _L1 _L2
_L1:
            return;
_L2:
            handleStrongAuthRequiredChanged(message.arg1, message.arg2);
            if(true) goto _L1; else goto _L3
_L3:
        }

        static final int MSG_ON_STRONG_AUTH_REQUIRED_CHANGED = 1;
        final StrongAuthTracker this$1;

        public StrongAuthTracker.H(Looper looper)
        {
            this$1 = StrongAuthTracker.this;
            super(looper);
        }
    }


    static Handler _2D_get0(LockPatternUtils lockpatternutils)
    {
        return lockpatternutils.mHandler;
    }

    public LockPatternUtils(Context context)
    {
        Object obj = null;
        super();
        mContext = context;
        mContentResolver = context.getContentResolver();
        Looper looper = Looper.myLooper();
        context = obj;
        if(looper != null)
            context = new Handler(looper);
        mHandler = context;
    }

    private boolean checkCredential(String s, int i, int j, CheckCredentialProgressCallback checkcredentialprogresscallback)
        throws RequestThrottledException
    {
        checkcredentialprogresscallback = getLockSettings().checkCredential(s, i, j, wrapCallback(checkcredentialprogresscallback));
        if(checkcredentialprogresscallback.getResponseCode() == 0)
            return true;
        try
        {
            if(checkcredentialprogresscallback.getResponseCode() == 1)
            {
                s = JVM INSTR new #13  <Class LockPatternUtils$RequestThrottledException>;
                s.RequestThrottledException(checkcredentialprogresscallback.getTimeout());
                throw s;
            }
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return false;
        }
        return false;
    }

    private int computePasswordQuality(int i, String s, int j)
    {
        if(i == 2)
            i = Math.max(j, PasswordMetrics.computeForPassword(s).quality);
        else
        if(i == 1)
            i = 0x10000;
        else
            i = 0;
        return i;
    }

    public static boolean frpCredentialEnabled(Context context)
    {
        return context.getResources().getBoolean(0x112005a);
    }

    private boolean getBoolean(String s, boolean flag, int i)
    {
        boolean flag1;
        try
        {
            flag1 = getLockSettings().getBoolean(s, flag, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return flag;
        }
        return flag1;
    }

    private long getLong(String s, long l, int i)
    {
        long l1;
        try
        {
            l1 = getLockSettings().getLong(s, l, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return l;
        }
        return l1;
    }

    private int getRequestedPasswordHistoryLength(int i)
    {
        return getDevicePolicyManager().getPasswordHistoryLength(null, i);
    }

    private String getSalt(int i)
    {
        long l = getLong("lockscreen.password_salt", 0L, i);
        long l1 = l;
        if(l == 0L)
            try
            {
                l1 = SecureRandom.getInstance("SHA1PRNG").nextLong();
                setLong("lockscreen.password_salt", l1, i);
                StringBuilder stringbuilder = JVM INSTR new #273 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Log.v("LockPatternUtils", stringbuilder.append("Initialized lock password salt for user: ").append(i).toString());
            }
            catch(NoSuchAlgorithmException nosuchalgorithmexception)
            {
                throw new IllegalStateException("Couldn't get SecureRandom number", nosuchalgorithmexception);
            }
        return Long.toHexString(l1);
    }

    private String getString(String s, int i)
    {
        try
        {
            s = getLockSettings().getString(s, null, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return null;
        }
        return s;
    }

    private TrustManager getTrustManager()
    {
        TrustManager trustmanager = (TrustManager)mContext.getSystemService("trust");
        if(trustmanager == null)
            Log.e("LockPatternUtils", "Can't get TrustManagerService: is it running?", new IllegalStateException("Stack trace:"));
        return trustmanager;
    }

    private UserManager getUserManager()
    {
        if(mUserManager == null)
            mUserManager = UserManager.get(mContext);
        return mUserManager;
    }

    private boolean hasSeparateChallenge(int i)
    {
        boolean flag;
        try
        {
            flag = getLockSettings().getSeparateProfileChallengeEnabled(i);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("LockPatternUtils", "Couldn't get separate profile challenge enabled");
            return false;
        }
        return flag;
    }

    public static boolean isDeviceEncryptionEnabled()
    {
        return StorageManager.isEncrypted();
    }

    private boolean isDoNotAskCredentialsOnBootSet()
    {
        return getDevicePolicyManager().getDoNotAskCredentialsOnBoot();
    }

    public static boolean isFileEncryptionEnabled()
    {
        return StorageManager.isFileEncryptedNativeOrEmulated();
    }

    private boolean isLockPasswordEnabled(int i, int j)
    {
        boolean flag;
        if(i == 0x40000 || i == 0x20000 || i == 0x30000 || i == 0x50000 || i == 0x60000)
            i = 1;
        else
        if(i == 0x80000)
            i = 1;
        else
            i = 0;
        if(i != 0)
            flag = savedPasswordExists(j);
        else
            flag = false;
        return flag;
    }

    private boolean isLockPatternEnabled(int i, int j)
    {
        boolean flag;
        if(i == 0x10000)
            flag = savedPatternExists(j);
        else
            flag = false;
        return flag;
    }

    private boolean isManagedProfile(int i)
    {
        UserInfo userinfo = getUserManager().getUserInfo(i);
        boolean flag;
        if(userinfo != null)
            flag = userinfo.isManagedProfile();
        else
            flag = false;
        return flag;
    }

    private void onAfterChangingPassword(int i)
    {
        getTrustManager().reportEnabledTrustAgentsChanged(i);
    }

    public static String patternStringToBaseZero(String s)
    {
        if(s == null)
            return "";
        int i = s.length();
        byte abyte0[] = new byte[i];
        s = s.getBytes();
        for(int j = 0; j < i; j++)
            abyte0[j] = (byte)(s[j] - 49);

        return new String(abyte0);
    }

    public static byte[] patternToHash(List list)
    {
        if(list == null)
            return null;
        int i = list.size();
        byte abyte0[] = new byte[i];
        for(int j = 0; j < i; j++)
        {
            LockPatternView.Cell cell = (LockPatternView.Cell)list.get(j);
            abyte0[j] = (byte)(cell.getRow() * 3 + cell.getColumn());
        }

        try
        {
            list = MessageDigest.getInstance("SHA-1").digest(abyte0);
        }
        // Misplaced declaration of an exception variable
        catch(List list)
        {
            return abyte0;
        }
        return list;
    }

    public static String patternToString(List list)
    {
        if(list == null)
            return "";
        int i = list.size();
        byte abyte0[] = new byte[i];
        for(int j = 0; j < i; j++)
        {
            LockPatternView.Cell cell = (LockPatternView.Cell)list.get(j);
            abyte0[j] = (byte)(cell.getRow() * 3 + cell.getColumn() + 49);
        }

        return new String(abyte0);
    }

    private boolean savedPasswordExists(int i)
    {
        boolean flag;
        try
        {
            flag = getLockSettings().havePassword(i);
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return flag;
    }

    private boolean savedPatternExists(int i)
    {
        boolean flag;
        try
        {
            flag = getLockSettings().havePattern(i);
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return flag;
    }

    private void setBoolean(String s, boolean flag, int i)
    {
        getLockSettings().setBoolean(s, flag, i);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("LockPatternUtils", (new StringBuilder()).append("Couldn't write boolean ").append(s).append(remoteexception).toString());
          goto _L1
    }

    private void setLong(String s, long l, int i)
    {
        getLockSettings().setLong(s, l, i);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("LockPatternUtils", (new StringBuilder()).append("Couldn't write long ").append(s).append(remoteexception).toString());
          goto _L1
    }

    private void setString(String s, String s1, int i)
    {
        getLockSettings().setString(s, s1, i);
_L1:
        return;
        s1;
        Log.e("LockPatternUtils", (new StringBuilder()).append("Couldn't write string ").append(s).append(s1).toString());
          goto _L1
    }

    private boolean shouldEncryptWithCredentials(boolean flag)
    {
        if(isCredentialRequiredToDecrypt(flag))
            flag = isDoNotAskCredentialsOnBootSet() ^ true;
        else
            flag = false;
        return flag;
    }

    public static List stringToPattern(String s)
    {
        if(s == null)
            return null;
        ArrayList arraylist = Lists.newArrayList();
        s = s.getBytes();
        for(int i = 0; i < s.length; i++)
        {
            byte byte0 = (byte)(s[i] - 49);
            arraylist.add(LockPatternView.Cell.of(byte0 / 3, byte0 % 3));
        }

        return arraylist;
    }

    private void throwIfCalledOnMainThread()
    {
        if(Looper.getMainLooper().isCurrentThread())
            throw new IllegalStateException("should not be called from the main thread.");
        else
            return;
    }

    private void updateCryptoUserInfo(int i)
    {
        String s;
        Object obj;
        if(i != 0)
            return;
        if(isOwnerInfoEnabled(i))
            s = getOwnerInfo(i);
        else
            s = "";
        obj = ServiceManager.getService("mount");
        if(obj == null)
        {
            Log.e("LockPatternUtils", "Could not find the mount service to update the user info");
            return;
        }
        obj = android.os.storage.IStorageManager.Stub.asInterface(((IBinder) (obj)));
        Log.d("LockPatternUtils", "Setting owner info");
        ((IStorageManager) (obj)).setField("OwnerInfo", s);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("LockPatternUtils", "Error changing user info", remoteexception);
          goto _L1
    }

    private void updateEncryptionPassword(final int type, final String password)
    {
        if(!isDeviceEncryptionEnabled())
            return;
        final IBinder service = ServiceManager.getService("mount");
        if(service == null)
        {
            Log.e("LockPatternUtils", "Could not find the mount service to update the encryption password");
            return;
        } else
        {
            (new AsyncTask() {

                protected volatile Object doInBackground(Object aobj[])
                {
                    return doInBackground((Void[])aobj);
                }

                protected transient Void doInBackground(Void avoid[])
                {
                    avoid = android.os.storage.IStorageManager.Stub.asInterface(service);
                    try
                    {
                        avoid.changeEncryptionPassword(type, password);
                    }
                    // Misplaced declaration of an exception variable
                    catch(Void avoid[])
                    {
                        Log.e("LockPatternUtils", "Error changing encryption password", avoid);
                    }
                    return null;
                }

                final LockPatternUtils this$0;
                final String val$password;
                final IBinder val$service;
                final int val$type;

            
            {
                this$0 = LockPatternUtils.this;
                service = ibinder;
                type = i;
                password = s;
                super();
            }
            }
).execute(new Void[0]);
            return;
        }
    }

    private void updateEncryptionPasswordIfNeeded(String s, int i, int j)
    {
        if(j == 0 && isDeviceEncryptionEnabled())
            if(!shouldEncryptWithCredentials(true))
            {
                clearEncryptionPassword();
            } else
            {
                if(i == 0x20000)
                    j = 1;
                else
                    j = 0;
                if(i == 0x30000)
                    i = 1;
                else
                    i = 0;
                if(j != 0 || i != 0)
                    i = 3;
                else
                    i = 0;
                updateEncryptionPassword(i, s);
            }
    }

    private void updatePasswordHistory(String s, int i)
    {
        String s1 = getString("lockscreen.passwordhistory", i);
        String s2 = s1;
        if(s1 == null)
            s2 = "";
        int j = getRequestedPasswordHistoryLength(i);
        if(j == 0)
        {
            s = "";
        } else
        {
            s = passwordToHash(s, i);
            s2 = (new StringBuilder()).append(new String(s, StandardCharsets.UTF_8)).append(",").append(s2).toString();
            s = s2.substring(0, Math.min((s.length * j + j) - 1, s2.length()));
        }
        setString("lockscreen.passwordhistory", s, i);
        onAfterChangingPassword(i);
    }

    public static boolean userOwnsFrpCredential(Context context, UserInfo userinfo)
    {
        boolean flag;
        if(userinfo != null && userinfo.isPrimary() && userinfo.isAdmin())
            flag = frpCredentialEnabled(context);
        else
            flag = false;
        return flag;
    }

    private byte[] verifyCredential(String s, int i, long l, int j)
        throws RequestThrottledException
    {
        try
        {
            s = getLockSettings().verifyCredential(s, i, l, j);
            if(s.getResponseCode() == 0)
                return s.getPayload();
            if(s.getResponseCode() == 1)
            {
                RequestThrottledException requestthrottledexception = JVM INSTR new #13  <Class LockPatternUtils$RequestThrottledException>;
                requestthrottledexception.RequestThrottledException(s.getTimeout());
                throw requestthrottledexception;
            }
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return null;
        }
        return null;
    }

    private ICheckCredentialProgressCallback wrapCallback(final CheckCredentialProgressCallback callback)
    {
        if(callback == null)
            return null;
        if(mHandler == null)
            throw new IllegalStateException("Must construct LockPatternUtils on a looper thread to use progress callbacks.");
        else
            return new ICheckCredentialProgressCallback.Stub() {

                static void _2D_com_android_internal_widget_LockPatternUtils$2_2D_mthref_2D_0(CheckCredentialProgressCallback checkcredentialprogresscallback)
                {
                    checkcredentialprogresscallback.onEarlyMatched();
                }

                public void onCredentialVerified()
                    throws RemoteException
                {
                    Handler handler = LockPatternUtils._2D_get0(LockPatternUtils.this);
                    CheckCredentialProgressCallback checkcredentialprogresscallback = callback;
                    checkcredentialprogresscallback.getClass();
                    handler.post(new _.Lambda.hZenqyGYSNt5KiruOSsv736MIDs((byte)1, checkcredentialprogresscallback));
                }

                final LockPatternUtils this$0;
                final CheckCredentialProgressCallback val$callback;

            
            {
                this$0 = LockPatternUtils.this;
                callback = checkcredentialprogresscallback;
                super();
            }
            }
;
    }

    public long addEscrowToken(byte abyte0[], int i)
    {
        long l;
        try
        {
            l = getLockSettings().addEscrowToken(abyte0, i);
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            return 0L;
        }
        return l;
    }

    public boolean checkPassword(String s, int i)
        throws RequestThrottledException
    {
        return checkPassword(s, i, null);
    }

    public boolean checkPassword(String s, int i, CheckCredentialProgressCallback checkcredentialprogresscallback)
        throws RequestThrottledException
    {
        throwIfCalledOnMainThread();
        return checkCredential(s, 2, i, checkcredentialprogresscallback);
    }

    public boolean checkPasswordHistory(String s, int i)
    {
        String s1 = new String(passwordToHash(s, i), StandardCharsets.UTF_8);
        String s2 = getString("lockscreen.passwordhistory", i);
        if(s2 == null)
            return false;
        int j = s1.length();
        i = getRequestedPasswordHistoryLength(i);
        if(i == 0)
            return false;
        i = (j * i + i) - 1;
        s = s2;
        if(s2.length() > i)
            s = s2.substring(0, i);
        return s.contains(s1);
    }

    public boolean checkPattern(List list, int i)
        throws RequestThrottledException
    {
        return checkPattern(list, i, null);
    }

    public boolean checkPattern(List list, int i, CheckCredentialProgressCallback checkcredentialprogresscallback)
        throws RequestThrottledException
    {
        throwIfCalledOnMainThread();
        return checkCredential(patternToString(list), 1, i, checkcredentialprogresscallback);
    }

    public boolean checkVoldPassword(int i)
    {
        boolean flag;
        try
        {
            flag = getLockSettings().checkVoldPassword(i);
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return flag;
    }

    public void clearEncryptionPassword()
    {
        updateEncryptionPassword(1, null);
    }

    public void clearLock(String s, int i)
    {
        setLong("lockscreen.password_type", 0L, i);
        try
        {
            getLockSettings().setLockCredential(null, -1, s, 0, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
        if(i == 0)
        {
            updateEncryptionPassword(1, null);
            setCredentialRequiredToDecrypt(false);
        }
        onAfterChangingPassword(i);
    }

    public void disableSyntheticPassword()
    {
        setLong("enable-sp", 0L, 0);
    }

    public void enableSyntheticPassword()
    {
        setLong("enable-sp", 1L, 0);
    }

    public int getActivePasswordQuality(int i)
    {
        int j = getKeyguardStoredPasswordQuality(i);
        if(isLockPasswordEnabled(j, i))
            return j;
        if(isLockPatternEnabled(j, i))
            return j;
        else
            return 0;
    }

    public int getCurrentFailedPasswordAttempts(int i)
    {
        if(i == -9999 && frpCredentialEnabled(mContext))
            return 0;
        else
            return getDevicePolicyManager().getCurrentFailedPasswordAttempts(i);
    }

    public String getDeviceOwnerInfo()
    {
        return getString("lockscreen.device_owner_info", 0);
    }

    public DevicePolicyManager getDevicePolicyManager()
    {
        if(mDevicePolicyManager == null)
        {
            mDevicePolicyManager = (DevicePolicyManager)mContext.getSystemService("device_policy");
            if(mDevicePolicyManager == null)
                Log.e("LockPatternUtils", "Can't get DevicePolicyManagerService: is it running?", new IllegalStateException("Stack trace:"));
        }
        return mDevicePolicyManager;
    }

    public List getEnabledTrustAgents(int i)
    {
        String s = getString("lockscreen.enabledtrustagents", i);
        if(TextUtils.isEmpty(s))
            return null;
        String as[] = s.split(",");
        ArrayList arraylist = new ArrayList(as.length);
        i = 0;
        for(int j = as.length; i < j; i++)
        {
            String s1 = as[i];
            if(!TextUtils.isEmpty(s1))
                arraylist.add(ComponentName.unflattenFromString(s1));
        }

        return arraylist;
    }

    public long getKeyguardLockoutAttemptDeadline(int i)
    {
        return -1L;
    }

    public int getKeyguardStoredPasswordQuality(int i)
    {
        return (int)getLong("lockscreen.password_type", 0L, i);
    }

    public ILockSettings getLockSettings()
    {
        if(mLockSettingsService == null)
            mLockSettingsService = ILockSettings.Stub.asInterface(ServiceManager.getService("lock_settings"));
        return mLockSettingsService;
    }

    public long getLockoutAttemptDeadline(int i)
    {
        long l = getLong("lockscreen.lockoutattemptdeadline", 0L, i);
        long l1 = getLong("lockscreen.lockoutattempttimeoutmss", 0L, i);
        long l2 = SystemClock.elapsedRealtime();
        if(l < l2 && l != 0L)
        {
            setLong("lockscreen.lockoutattemptdeadline", 0L, i);
            setLong("lockscreen.lockoutattempttimeoutmss", 0L, i);
            return 0L;
        }
        long l3 = l;
        if(l > l2 + l1)
        {
            l3 = l2 + l1;
            setLong("lockscreen.lockoutattemptdeadline", l3, i);
        }
        return l3;
    }

    public int getMaximumFailedPasswordsForWipe(int i)
    {
        if(i == -9999 && frpCredentialEnabled(mContext))
            return 0;
        else
            return getDevicePolicyManager().getMaximumFailedPasswordsForWipe(null, i);
    }

    public String getOwnerInfo(int i)
    {
        return getString("lock_screen_owner_info", i);
    }

    public boolean getPowerButtonInstantlyLocks(int i)
    {
        return getBoolean("lockscreen.power_button_instantly_locks", true, i);
    }

    public int getRequestedMinimumPasswordLength(int i)
    {
        return getDevicePolicyManager().getPasswordMinimumLength(null, i);
    }

    public int getRequestedPasswordMinimumLetters(int i)
    {
        return getDevicePolicyManager().getPasswordMinimumLetters(null, i);
    }

    public int getRequestedPasswordMinimumLowerCase(int i)
    {
        return getDevicePolicyManager().getPasswordMinimumLowerCase(null, i);
    }

    public int getRequestedPasswordMinimumNonLetter(int i)
    {
        return getDevicePolicyManager().getPasswordMinimumNonLetter(null, i);
    }

    public int getRequestedPasswordMinimumNumeric(int i)
    {
        return getDevicePolicyManager().getPasswordMinimumNumeric(null, i);
    }

    public int getRequestedPasswordMinimumSymbols(int i)
    {
        return getDevicePolicyManager().getPasswordMinimumSymbols(null, i);
    }

    public int getRequestedPasswordMinimumUpperCase(int i)
    {
        return getDevicePolicyManager().getPasswordMinimumUpperCase(null, i);
    }

    public int getRequestedPasswordQuality(int i)
    {
        return getDevicePolicyManager().getPasswordQuality(null, i);
    }

    public int getStrongAuthForUser(int i)
    {
        try
        {
            i = getLockSettings().getStrongAuthForUser(i);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("LockPatternUtils", "Could not get StrongAuth", remoteexception);
            return StrongAuthTracker.getDefaultFlags(mContext);
        }
        return i;
    }

    public boolean isCredentialRequiredToDecrypt(boolean flag)
    {
        int i = android.provider.Settings.Global.getInt(mContentResolver, "require_password_to_decrypt", -1);
        if(i != -1)
            if(i != 0)
                flag = true;
            else
                flag = false;
        return flag;
    }

    public boolean isDeviceOwnerInfoEnabled()
    {
        boolean flag;
        if(getDeviceOwnerInfo() != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isEscrowTokenActive(long l, int i)
    {
        boolean flag;
        try
        {
            flag = getLockSettings().isEscrowTokenActive(l, i);
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return flag;
    }

    public boolean isFingerprintAllowedForUser(int i)
    {
        boolean flag = false;
        if((getStrongAuthForUser(i) & -5) == 0)
            flag = true;
        return flag;
    }

    public boolean isLegacyLockPatternEnabled(int i)
    {
        return getBoolean("legacy_lock_pattern_enabled", true, i);
    }

    public boolean isLockPasswordEnabled(int i)
    {
        return isLockPasswordEnabled(getKeyguardStoredPasswordQuality(i), i);
    }

    public boolean isLockPatternEnabled(int i)
    {
        return isLockPatternEnabled(getKeyguardStoredPasswordQuality(i), i);
    }

    public boolean isLockScreenDisabled(int i)
    {
        boolean flag3;
        if(isSecure(i))
            return false;
        boolean flag = mContext.getResources().getBoolean(0x1120042);
        boolean flag1;
        UserInfo userinfo;
        boolean flag2;
        if(UserManager.isSplitSystemUser() && i == 0)
            flag1 = true;
        else
            flag1 = false;
        userinfo = getUserManager().getUserInfo(i);
        if(UserManager.isDeviceInDemoMode(mContext) && userinfo != null)
            flag2 = userinfo.isDemo();
        else
            flag2 = false;
        if(getBoolean("lockscreen.disabled", false, i)) goto _L2; else goto _L1
_L1:
        flag3 = flag2;
        if(!flag) goto _L4; else goto _L3
_L3:
        if(flag1 ^ true) goto _L2; else goto _L5
_L5:
        flag3 = flag2;
_L4:
        return flag3;
_L2:
        flag3 = true;
        if(true) goto _L4; else goto _L6
_L6:
    }

    public boolean isManagedProfileWithUnifiedChallenge(int i)
    {
        boolean flag;
        if(isManagedProfile(i))
            flag = hasSeparateChallenge(i) ^ true;
        else
            flag = false;
        return flag;
    }

    public boolean isOwnerInfoEnabled(int i)
    {
        return getBoolean("lock_screen_owner_info_enabled", false, i);
    }

    public boolean isPatternEverChosen(int i)
    {
        return getBoolean("lockscreen.patterneverchosen", false, i);
    }

    public boolean isPowerButtonInstantlyLocksEverChosen(int i)
    {
        boolean flag;
        if(getString("lockscreen.power_button_instantly_locks", i) != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isSecure(int i)
    {
        int j = getKeyguardStoredPasswordQuality(i);
        boolean flag;
        if(!isLockPatternEnabled(j, i))
            flag = isLockPasswordEnabled(j, i);
        else
            flag = true;
        return flag;
    }

    public boolean isSeparateProfileChallengeAllowed(int i)
    {
        boolean flag;
        if(isManagedProfile(i))
            flag = getDevicePolicyManager().isSeparateProfileChallengeAllowed(i);
        else
            flag = false;
        return flag;
    }

    public boolean isSeparateProfileChallengeAllowedToUnify(int i)
    {
        return getDevicePolicyManager().isProfileActivePasswordSufficientForParent(i);
    }

    public boolean isSeparateProfileChallengeEnabled(int i)
    {
        if(XSpaceUserHandle.isXSpaceUserId(i))
            return hasSeparateChallenge(i);
        boolean flag;
        if(isManagedProfile(i))
            flag = hasSeparateChallenge(i);
        else
            flag = false;
        return flag;
    }

    public boolean isSyntheticPasswordEnabled()
    {
        boolean flag = false;
        if(getLong("enable-sp", 0L, 0) != 0L)
            flag = true;
        return flag;
    }

    public boolean isTactileFeedbackEnabled()
    {
        boolean flag = true;
        if(android.provider.Settings.System.getIntForUser(mContentResolver, "haptic_feedback_enabled", 1, -2) == 0)
            flag = false;
        return flag;
    }

    public boolean isTrustAllowedForUser(int i)
    {
        boolean flag = false;
        if(getStrongAuthForUser(i) == 0)
            flag = true;
        return flag;
    }

    public boolean isTrustUsuallyManaged(int i)
    {
        if(!(mLockSettingsService instanceof ILockSettings.Stub))
            throw new IllegalStateException("May only be called by TrustManagerService. Use TrustManager.isTrustUsuallyManaged()");
        boolean flag;
        try
        {
            flag = getLockSettings().getBoolean("lockscreen.istrustusuallymanaged", false, i);
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return flag;
    }

    public boolean isVisiblePatternEnabled(int i)
    {
        return getBoolean("lock_pattern_visible_pattern", false, i);
    }

    public boolean isVisiblePatternEverChosen(int i)
    {
        boolean flag;
        if(getString("lock_pattern_visible_pattern", i) != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public byte[] passwordToHash(String s, int i)
    {
        if(s == null)
            return null;
        try
        {
            Object obj = JVM INSTR new #273 <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            byte abyte0[] = ((StringBuilder) (obj)).append(s).append(getSalt(i)).toString().getBytes();
            s = MessageDigest.getInstance("SHA-1").digest(abyte0);
            byte abyte1[] = MessageDigest.getInstance("MD5").digest(abyte0);
            abyte0 = new byte[s.length + abyte1.length];
            System.arraycopy(s, 0, abyte0, 0, s.length);
            System.arraycopy(abyte1, 0, abyte0, s.length, abyte1.length);
            s = HexEncoding.encode(abyte0);
            abyte0 = JVM INSTR new #403 <Class String>;
            abyte0.String(s);
            s = abyte0.getBytes(StandardCharsets.UTF_8);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new AssertionError("Missing digest algorithm: ", s);
        }
        return s;
    }

    public void registerStrongAuthTracker(StrongAuthTracker strongauthtracker)
    {
        try
        {
            getLockSettings().registerStrongAuthTracker(strongauthtracker.mStub);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(StrongAuthTracker strongauthtracker)
        {
            throw new RuntimeException("Could not register StrongAuthTracker");
        }
    }

    public boolean removeEscrowToken(long l, int i)
    {
        boolean flag;
        try
        {
            flag = getLockSettings().removeEscrowToken(l, i);
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return flag;
    }

    public void reportFailedPasswordAttempt(int i)
    {
        if(i == -9999 && frpCredentialEnabled(mContext))
        {
            return;
        } else
        {
            getDevicePolicyManager().reportFailedPasswordAttempt(i);
            getTrustManager().reportUnlockAttempt(false, i);
            return;
        }
    }

    public void reportPasswordLockout(int i, int j)
    {
        if(j == -9999 && frpCredentialEnabled(mContext))
        {
            return;
        } else
        {
            getTrustManager().reportUnlockLockout(i, j);
            return;
        }
    }

    public void reportPatternWasChosen(int i)
    {
        setBoolean("lockscreen.patterneverchosen", true, i);
    }

    public void reportSuccessfulPasswordAttempt(int i)
    {
        if(i == -9999 && frpCredentialEnabled(mContext))
        {
            return;
        } else
        {
            getDevicePolicyManager().reportSuccessfulPasswordAttempt(i);
            getTrustManager().reportUnlockAttempt(true, i);
            return;
        }
    }

    public void requireCredentialEntry(int i)
    {
        requireStrongAuth(4, i);
    }

    public void requireStrongAuth(int i, int j)
    {
        getLockSettings().requireStrongAuth(i, j);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("LockPatternUtils", (new StringBuilder()).append("Error while requesting strong auth: ").append(remoteexception).toString());
          goto _L1
    }

    public void resetKeyStore(int i)
    {
        getLockSettings().resetKeyStore(i);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("LockPatternUtils", (new StringBuilder()).append("Couldn't reset keystore ").append(remoteexception).toString());
          goto _L1
    }

    public void sanitizePassword()
    {
        getLockSettings().sanitizePassword();
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("LockPatternUtils", (new StringBuilder()).append("Couldn't sanitize password").append(remoteexception).toString());
          goto _L1
    }

    public void saveLockPassword(String s, String s1, int i, int j)
    {
        if(s == null) goto _L2; else goto _L1
_L1:
        if(s.length() >= 4) goto _L3; else goto _L2
_L2:
        s = JVM INSTR new #919 <Class IllegalArgumentException>;
        s.IllegalArgumentException("password must not be null and at least of length 4");
        throw s;
_L5:
        return;
_L3:
        try
        {
            setLong("lockscreen.password_type", computePasswordQuality(2, s, i), j);
            getLockSettings().setLockCredential(s, 2, s1, i, j);
            updateEncryptionPasswordIfNeeded(s, PasswordMetrics.computeForPassword(s).quality, j);
            updatePasswordHistory(s, j);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.e("LockPatternUtils", (new StringBuilder()).append("Unable to save lock password ").append(s).toString());
        }
        if(true) goto _L5; else goto _L4
_L4:
    }

    public void saveLockPattern(List list, int i)
    {
        saveLockPattern(list, null, i);
    }

    public void saveLockPattern(List list, String s, int i)
    {
        if(list == null) goto _L2; else goto _L1
_L1:
        if(list.size() >= 4) goto _L3; else goto _L2
_L2:
        list = JVM INSTR new #919 <Class IllegalArgumentException>;
        list.IllegalArgumentException("pattern must not be null and at least 4 dots long.");
        throw list;
        list;
        Log.e("LockPatternUtils", (new StringBuilder()).append("Couldn't save lock pattern ").append(list).toString());
_L4:
        return;
_L3:
        setLong("lockscreen.password_type", 0x10000L, i);
        getLockSettings().setLockCredential(patternToString(list), 1, s, 0x10000, i);
        if(i != 0)
            break MISSING_BLOCK_LABEL_105;
        if(isDeviceEncryptionEnabled())
        {
            if(shouldEncryptWithCredentials(true))
                break MISSING_BLOCK_LABEL_118;
            clearEncryptionPassword();
        }
_L5:
        reportPatternWasChosen(i);
        onAfterChangingPassword(i);
          goto _L4
        updateEncryptionPassword(2, patternToString(list));
          goto _L5
    }

    public void setCredentialRequiredToDecrypt(boolean flag)
    {
        int i = 1;
        boolean flag1;
        if(!getUserManager().isSystemUser())
            flag1 = getUserManager().isPrimaryUser();
        else
            flag1 = true;
        if(!flag1)
            throw new IllegalStateException("Only the system or primary user may call setCredentialRequiredForDecrypt()");
        if(isDeviceEncryptionEnabled())
        {
            ContentResolver contentresolver = mContext.getContentResolver();
            if(!flag)
                i = 0;
            android.provider.Settings.Global.putInt(contentresolver, "require_password_to_decrypt", i);
        }
    }

    public void setDeviceOwnerInfo(String s)
    {
        String s1 = s;
        if(s != null)
        {
            s1 = s;
            if(s.isEmpty())
                s1 = null;
        }
        setString("lockscreen.device_owner_info", s1, 0);
    }

    public void setEnabledTrustAgents(Collection collection, int i)
    {
        StringBuilder stringbuilder = new StringBuilder();
        for(Iterator iterator = collection.iterator(); iterator.hasNext(); stringbuilder.append(collection.flattenToShortString()))
        {
            collection = (ComponentName)iterator.next();
            if(stringbuilder.length() > 0)
                stringbuilder.append(',');
        }

        setString("lockscreen.enabledtrustagents", stringbuilder.toString(), i);
        getTrustManager().reportEnabledTrustAgentsChanged(i);
    }

    public long setKeyguardLockoutAttemptDeadline(int i)
    {
        return -1L;
    }

    public void setLegacyLockPatternEnabled(int i)
    {
        setBoolean("lock_pattern_autolock", true, i);
    }

    public boolean setLockCredentialWithToken(String s, int i, int j, long l, byte abyte0[], int k)
    {
        if(i == -1) goto _L2; else goto _L1
_L1:
        try
        {
            if(TextUtils.isEmpty(s) || s.length() < 4)
            {
                s = JVM INSTR new #919 <Class IllegalArgumentException>;
                s.IllegalArgumentException("password must not be null and at least of length 4");
                throw s;
            }
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.e("LockPatternUtils", "Unable to save lock password ", s);
            s.rethrowFromSystemServer();
            return false;
        }
        j = computePasswordQuality(i, s, j);
        if(!getLockSettings().setLockCredentialWithToken(s, i, l, abyte0, j, k))
            return false;
        setLong("lockscreen.password_type", j, k);
        updateEncryptionPasswordIfNeeded(s, j, k);
        updatePasswordHistory(s, k);
_L4:
        onAfterChangingPassword(k);
        return true;
_L2:
        if(!TextUtils.isEmpty(s))
        {
            s = JVM INSTR new #919 <Class IllegalArgumentException>;
            s.IllegalArgumentException("password must be emtpy for NONE type");
            throw s;
        }
        if(!getLockSettings().setLockCredentialWithToken(null, -1, l, abyte0, 0, k))
            return false;
        setLong("lockscreen.password_type", 0L, k);
        if(k != 0) goto _L4; else goto _L3
_L3:
        updateEncryptionPassword(1, null);
        setCredentialRequiredToDecrypt(false);
          goto _L4
    }

    public void setLockScreenDisabled(boolean flag, int i)
    {
        setBoolean("lockscreen.disabled", flag, i);
    }

    public long setLockoutAttemptDeadline(int i, int j)
    {
        long l = SystemClock.elapsedRealtime() + (long)j;
        if(i == -9999)
        {
            return l;
        } else
        {
            setLong("lockscreen.lockoutattemptdeadline", l, i);
            setLong("lockscreen.lockoutattempttimeoutmss", j, i);
            return l;
        }
    }

    public void setOwnerInfo(String s, int i)
    {
        setString("lock_screen_owner_info", s, i);
        updateCryptoUserInfo(i);
    }

    public void setOwnerInfoEnabled(boolean flag, int i)
    {
        setBoolean("lock_screen_owner_info_enabled", flag, i);
        updateCryptoUserInfo(i);
    }

    public void setPowerButtonInstantlyLocks(boolean flag, int i)
    {
        setBoolean("lockscreen.power_button_instantly_locks", flag, i);
    }

    public void setSeparateProfileChallengeEnabled(int i, boolean flag, String s)
    {
        if(!isManagedProfile(i) && XSpaceUserHandle.isXSpaceUserId(i) ^ true)
            return;
        getLockSettings().setSeparateProfileChallengeEnabled(i, flag, s);
        onAfterChangingPassword(i);
_L1:
        return;
        s;
        Log.e("LockPatternUtils", "Couldn't update work profile challenge enabled");
          goto _L1
    }

    public void setTrustUsuallyManaged(boolean flag, int i)
    {
        getLockSettings().setBoolean("lockscreen.istrustusuallymanaged", flag, i);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void setVisiblePasswordEnabled(boolean flag, int i)
    {
        if(i != 0)
            return;
        Object obj = ServiceManager.getService("mount");
        if(obj == null)
        {
            Log.e("LockPatternUtils", "Could not find the mount service to update the user info");
            return;
        }
        IStorageManager istoragemanager = android.os.storage.IStorageManager.Stub.asInterface(((IBinder) (obj)));
        if(flag)
            obj = "1";
        else
            obj = "0";
        istoragemanager.setField("PasswordVisible", ((String) (obj)));
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("LockPatternUtils", "Error changing password visible state", remoteexception);
          goto _L1
    }

    public void setVisiblePatternEnabled(boolean flag, int i)
    {
        setBoolean("lock_pattern_visible_pattern", flag, i);
        if(i != 0)
            return;
        Object obj = ServiceManager.getService("mount");
        if(obj == null)
        {
            Log.e("LockPatternUtils", "Could not find the mount service to update the user info");
            return;
        }
        IStorageManager istoragemanager = android.os.storage.IStorageManager.Stub.asInterface(((IBinder) (obj)));
        if(flag)
            obj = "1";
        else
            obj = "0";
        istoragemanager.setField("PatternVisible", ((String) (obj)));
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("LockPatternUtils", "Error changing pattern visible state", remoteexception);
          goto _L1
    }

    public void unlockUserWithToken(long l, byte abyte0[], int i)
    {
        getLockSettings().unlockUserWithToken(l, abyte0, i);
_L1:
        return;
        abyte0;
        Log.e("LockPatternUtils", "Unable to unlock user with token", abyte0);
        abyte0.rethrowFromSystemServer();
          goto _L1
    }

    public void unregisterStrongAuthTracker(StrongAuthTracker strongauthtracker)
    {
        getLockSettings().unregisterStrongAuthTracker(strongauthtracker.mStub);
_L1:
        return;
        strongauthtracker;
        Log.e("LockPatternUtils", "Could not unregister StrongAuthTracker", strongauthtracker);
          goto _L1
    }

    public void userPresent(int i)
    {
        try
        {
            getLockSettings().userPresent(i);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public byte[] verifyPassword(String s, long l, int i)
        throws RequestThrottledException
    {
        throwIfCalledOnMainThread();
        return verifyCredential(s, 2, l, i);
    }

    public byte[] verifyPattern(List list, long l, int i)
        throws RequestThrottledException
    {
        throwIfCalledOnMainThread();
        return verifyCredential(patternToString(list), 1, l, i);
    }

    public byte[] verifyTiedProfileChallenge(String s, boolean flag, long l, int i)
        throws RequestThrottledException
    {
        throwIfCalledOnMainThread();
        Object obj;
        int j;
        try
        {
            obj = getLockSettings();
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return null;
        }
        if(flag)
            j = 1;
        else
            j = 2;
        obj = ((ILockSettings) (obj)).verifyTiedProfileChallenge(s, j, l, i);
        if(((VerifyCredentialResponse) (obj)).getResponseCode() == 0)
            return ((VerifyCredentialResponse) (obj)).getPayload();
        if(((VerifyCredentialResponse) (obj)).getResponseCode() == 1)
        {
            s = JVM INSTR new #13  <Class LockPatternUtils$RequestThrottledException>;
            s.RequestThrottledException(((VerifyCredentialResponse) (obj)).getTimeout());
            throw s;
        }
        return null;
    }

    public static final String BIOMETRIC_WEAK_EVER_CHOSEN_KEY = "lockscreen.biometricweakeverchosen";
    public static final int CREDENTIAL_TYPE_NONE = -1;
    public static final int CREDENTIAL_TYPE_PASSWORD = 2;
    public static final int CREDENTIAL_TYPE_PATTERN = 1;
    private static final boolean DEBUG = false;
    public static final String DISABLE_LOCKSCREEN_KEY = "lockscreen.disabled";
    private static final String ENABLED_TRUST_AGENTS = "lockscreen.enabledtrustagents";
    public static final int FAILED_ATTEMPTS_BEFORE_RESET = 9;
    public static final int FAILED_ATTEMPTS_BEFORE_WIPE_GRACE = 5;
    public static final long FAILED_ATTEMPT_COUNTDOWN_INTERVAL_MS = 1000L;
    private static final boolean FRP_CREDENTIAL_ENABLED = true;
    private static final String IS_TRUST_USUALLY_MANAGED = "lockscreen.istrustusuallymanaged";
    public static final String LEGACY_LOCK_PATTERN_ENABLED = "legacy_lock_pattern_enabled";
    public static final String LOCKOUT_ATTEMPT_DEADLINE = "lockscreen.lockoutattemptdeadline";
    public static final String LOCKOUT_ATTEMPT_TIMEOUT_MS = "lockscreen.lockoutattempttimeoutmss";
    public static final String LOCKOUT_PERMANENT_KEY = "lockscreen.lockedoutpermanently";
    public static final String LOCKSCREEN_BIOMETRIC_WEAK_FALLBACK = "lockscreen.biometric_weak_fallback";
    public static final String LOCKSCREEN_OPTIONS = "lockscreen.options";
    public static final String LOCKSCREEN_POWER_BUTTON_INSTANTLY_LOCKS = "lockscreen.power_button_instantly_locks";
    public static final String LOCKSCREEN_WIDGETS_ENABLED = "lockscreen.widgets_enabled";
    public static final String LOCK_PASSWORD_SALT_KEY = "lockscreen.password_salt";
    private static final String LOCK_SCREEN_DEVICE_OWNER_INFO = "lockscreen.device_owner_info";
    private static final String LOCK_SCREEN_OWNER_INFO = "lock_screen_owner_info";
    private static final String LOCK_SCREEN_OWNER_INFO_ENABLED = "lock_screen_owner_info_enabled";
    public static final int MIN_LOCK_PASSWORD_SIZE = 4;
    public static final int MIN_LOCK_PATTERN_SIZE = 4;
    public static final int MIN_PATTERN_REGISTER_FAIL = 4;
    public static final String PASSWORD_HISTORY_KEY = "lockscreen.passwordhistory";
    public static final String PASSWORD_TYPE_ALTERNATE_KEY = "lockscreen.password_type_alternate";
    public static final String PASSWORD_TYPE_KEY = "lockscreen.password_type";
    public static final String PATTERN_EVER_CHOSEN_KEY = "lockscreen.patterneverchosen";
    public static final String PROFILE_KEY_NAME_DECRYPT = "profile_key_name_decrypt_";
    public static final String PROFILE_KEY_NAME_ENCRYPT = "profile_key_name_encrypt_";
    public static final String SYNTHETIC_PASSWORD_ENABLED_KEY = "enable-sp";
    public static final String SYNTHETIC_PASSWORD_HANDLE_KEY = "sp-handle";
    public static final String SYNTHETIC_PASSWORD_KEY_PREFIX = "synthetic_password_";
    private static final String TAG = "LockPatternUtils";
    public static final int USER_FRP = -9999;
    private final ContentResolver mContentResolver;
    private final Context mContext;
    private DevicePolicyManager mDevicePolicyManager;
    private final Handler mHandler;
    private ILockSettings mLockSettingsService;
    private UserManager mUserManager;

    // Unreferenced inner class com/android/internal/widget/LockPatternUtils$StrongAuthTracker$1

/* anonymous class */
    class StrongAuthTracker._cls1 extends android.app.trust.IStrongAuthTracker.Stub
    {

        public void onStrongAuthRequiredChanged(int i, int j)
        {
            StrongAuthTracker._2D_get0(StrongAuthTracker.this).obtainMessage(1, i, j).sendToTarget();
        }

        final StrongAuthTracker this$1;

            
            {
                this$1 = StrongAuthTracker.this;
                super();
            }
    }

}
