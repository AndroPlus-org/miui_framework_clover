// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security;

import android.app.ActivityManager;
import android.content.Context;
import android.os.*;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.widget.ILockSettings;
import com.android.internal.widget.LockPatternUtils;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import miui.security.SecurityManager;

// Referenced classes of package android.security:
//            PrivacyLockPatternUtils

public class MiuiLockPatternUtils extends LockPatternUtils
{
    public static class MiuiLockPatternData
    {

        public final AtomicBoolean mHaveNonZeroFile = new AtomicBoolean(false);
        public String mLockFile;
        public String mLockFilename;
        public FileObserver mPasswordObserver;
        public String mTag;

        public MiuiLockPatternData(String s, String s1)
        {
            mTag = s;
            mLockFile = s1;
        }
    }

    private static class PasswordFileObserver extends FileObserver
    {

        public void onEvent(int i, String s)
        {
            int j = MiuiLockPatternUtils._2D_get0().length;
            i = 0;
            while(i < j) 
            {
                MiuiLockPatternData miuilockpatterndata = MiuiLockPatternUtils._2D_get0()[i];
                if(miuilockpatterndata.mLockFile.equals(s) && TextUtils.isEmpty(miuilockpatterndata.mLockFilename) ^ true)
                {
                    Log.d("MiuiLockPatternUtils", (new StringBuilder()).append(miuilockpatterndata.mTag).append("password file changed").toString());
                    AtomicBoolean atomicboolean = miuilockpatterndata.mHaveNonZeroFile;
                    boolean flag;
                    if((new File(miuilockpatterndata.mLockFilename)).length() > 0L)
                        flag = true;
                    else
                        flag = false;
                    atomicboolean.set(flag);
                }
                i++;
            }
        }

        public PasswordFileObserver(String s, int i)
        {
            super(s, i);
        }
    }


    static MiuiLockPatternData[] _2D_get0()
    {
        return mMiuiLockPatternDatas;
    }

    public MiuiLockPatternUtils(Context context)
    {
        this(context, 0);
    }

    public MiuiLockPatternUtils(Context context, int i)
    {
        boolean flag = true;
        super(context);
        mContext = context;
        mType = i;
        MiuiLockPatternData miuilockpatterndata = mMiuiLockPatternDatas[i];
        boolean flag1;
        if(miuilockpatterndata.mLockFilename == null)
        {
            String s = (new StringBuilder()).append(Environment.getDataDirectory().getAbsolutePath()).append("/system/").toString();
            miuilockpatterndata.mLockFilename = (new StringBuilder()).append(s).append(miuilockpatterndata.mLockFile).toString();
            AtomicBoolean atomicboolean = miuilockpatterndata.mHaveNonZeroFile;
            if((new File(miuilockpatterndata.mLockFilename)).length() > 0L)
                flag1 = true;
            else
                flag1 = false;
            atomicboolean.set(flag1);
            miuilockpatterndata.mPasswordObserver = new PasswordFileObserver(s, 904);
            miuilockpatterndata.mPasswordObserver.startWatching();
        }
        if(context.checkCallingOrSelfPermission("android.permission.INTERACT_ACROSS_USERS_FULL") == 0)
            flag1 = flag;
        else
            flag1 = false;
        mMultiUserMode = flag1;
        mSecurityManager = (SecurityManager)mContext.getSystemService("security");
    }

    private boolean checkAccessKeyguardStoragePermission()
    {
        boolean flag = false;
        if(mContext.checkPermission("android.permission.ACCESS_KEYGUARD_SECURE_STORAGE", Process.myPid(), Process.myUid()) == 0)
            flag = true;
        return flag;
    }

    public static boolean checkPrivacyPasswordPattern(String s, String s1, int i)
    {
        Object obj;
        StringBuilder stringbuilder;
        Object obj1;
        if(s == null)
            throw new RuntimeException("pattern is null");
        obj = null;
        stringbuilder = null;
        obj1 = obj;
        List list = LockPatternUtils.stringToPattern(s);
        obj1 = obj;
        s = JVM INSTR new #203 <Class RandomAccessFile>;
        obj1 = obj;
        s.RandomAccessFile(s1, "r");
        boolean flag;
        obj1 = new byte[(int)s.length()];
        s.readFully(((byte []) (obj1)), 0, obj1.length);
        flag = Arrays.equals(((byte []) (obj1)), LockPatternUtils.patternToHash(list));
        if(s != null)
            try
            {
                s.close();
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
        return flag;
        Exception exception;
        exception;
        s = stringbuilder;
_L4:
        obj1 = s;
        stringbuilder = JVM INSTR new #96  <Class StringBuilder>;
        obj1 = s;
        stringbuilder.StringBuilder();
        obj1 = s;
        Log.e("MiuiLockPatternUtils", stringbuilder.append("checkPrivacyPasswordPattern error ").append(s1).toString(), exception);
        if(s != null)
            try
            {
                s.close();
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
        return false;
        s;
_L2:
        if(obj1 != null)
            try
            {
                ((RandomAccessFile) (obj1)).close();
            }
            // Misplaced declaration of an exception variable
            catch(String s1) { }
        throw s;
        s1;
        obj1 = s;
        s = s1;
        if(true) goto _L2; else goto _L1
_L1:
        exception;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private String getFileNameAsUser(int i)
    {
        String s = mMiuiLockPatternDatas[mType].mLockFilename;
        if(i != 0)
            s = (new File(Environment.getUserSystemDirectory(i), mMiuiLockPatternDatas[mType].mLockFile)).getAbsolutePath();
        return s;
    }

    private ILockSettings getLockSettingsService()
    {
        if(mLockSettingsService == null)
            mLockSettingsService = com.android.internal.widget.ILockSettings.Stub.asInterface(ServiceManager.getService("lock_settings"));
        return mLockSettingsService;
    }

    private int getTimeoutInMsByFailedAttempts(int i)
    {
        i = Math.min(Math.max(i - 5, 0), FAILED_ATTEMPT_TIMEOUT_SECONDS_ARRAY.length - 1);
        return FAILED_ATTEMPT_TIMEOUT_SECONDS_ARRAY[i] * 1000;
    }

    public static void savePrivacyPasswordPattern(String s, String s1, int i)
    {
        Object obj;
        RuntimeException runtimeexception;
        String s2;
        if(s == null)
            throw new RuntimeException("pattern is null");
        obj = null;
        runtimeexception = null;
        s2 = obj;
        byte abyte0[] = LockPatternUtils.patternToHash(LockPatternUtils.stringToPattern(s));
        s2 = obj;
        s = JVM INSTR new #203 <Class RandomAccessFile>;
        s2 = obj;
        s.RandomAccessFile(s1, "rw");
        if(abyte0 == null) goto _L2; else goto _L1
_L1:
        if(abyte0.length != 0) goto _L3; else goto _L2
_L2:
        s.setLength(0L);
_L4:
        if(s == null)
            break MISSING_BLOCK_LABEL_73;
        s.close();
_L5:
        return;
_L3:
        s.write(abyte0, 0, abyte0.length);
          goto _L4
        s1;
_L7:
        s2 = s;
        Log.e("MiuiLockPatternUtils", "savePrivacyPasswordPattern error", s1);
        s2 = s;
        runtimeexception = JVM INSTR new #194 <Class RuntimeException>;
        s2 = s;
        runtimeexception.RuntimeException(s1);
        s2 = s;
        throw runtimeexception;
        s1;
_L6:
        if(s2 != null)
            try
            {
                s2.close();
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
        throw s1;
        s;
          goto _L5
        s1;
        s2 = s;
          goto _L6
        s1;
        s = runtimeexception;
          goto _L7
    }

    private void setLong(String s, long l, int i)
    {
        getLockSettingsService().setLong(s, l, getCurrentOrCallingUserId());
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("MiuiLockPatternUtils", (new StringBuilder()).append("Couldn't write long ").append(s).append(remoteexception).toString());
          goto _L1
    }

    public boolean checkMiuiLockPattern(List list)
    {
        if(android.os.Build.VERSION.SDK_INT >= 24)
            return PrivacyLockPatternUtils.checkPrivacyPasswordPattern(list, mMiuiLockPatternDatas[mType].mLockFilename, 0);
        byte abyte0[];
        int i;
        boolean flag;
        try
        {
            String s = mMiuiLockPatternDatas[mType].mLockFilename;
            RandomAccessFile randomaccessfile = JVM INSTR new #203 <Class RandomAccessFile>;
            randomaccessfile.RandomAccessFile(s, "r");
            abyte0 = new byte[(int)randomaccessfile.length()];
            i = randomaccessfile.read(abyte0, 0, abyte0.length);
            randomaccessfile.close();
        }
        // Misplaced declaration of an exception variable
        catch(List list)
        {
            return true;
        }
        // Misplaced declaration of an exception variable
        catch(List list)
        {
            return true;
        }
        if(i <= 0)
            return true;
        flag = Arrays.equals(abyte0, LockPatternUtils.patternToHash(list));
        return flag;
    }

    public boolean checkMiuiLockPatternAsUser(List list, int i)
    {
        if(i == 0)
        {
            if(android.os.Build.VERSION.SDK_INT >= 24)
                return PrivacyLockPatternUtils.checkPrivacyPasswordPattern(list, mMiuiLockPatternDatas[mType].mLockFilename, i);
            else
                return checkMiuiLockPattern(list);
        } else
        {
            String s = getFileNameAsUser(i);
            return TextUtils.equals(mSecurityManager.readSystemDataStringFile(s), new String(LockPatternUtils.patternToHash(list)));
        }
    }

    public void clearLockoutAttemptDeadline()
    {
        setLong("lockscreen.lockoutattemptdeadline", 0L);
    }

    public String getBluetoothAddressToUnlock()
    {
        if(checkAccessKeyguardStoragePermission())
            return android.provider.Settings.Secure.getString(mContext.getContentResolver(), "bluetooth_address_to_unlock");
        else
            throw new SecurityException("Need android.permission.ACCESS_KEYGUARD_SECURE_STORAGE permission to access");
    }

    public String getBluetoothKeyToUnlock()
    {
        if(checkAccessKeyguardStoragePermission())
            return android.provider.Settings.Secure.getString(mContext.getContentResolver(), "bluetooth_key_to_unlock");
        else
            throw new SecurityException("Need android.permission.ACCESS_KEYGUARD_SECURE_STORAGE permission to access");
    }

    public String getBluetoothNameToUnlock()
    {
        if(checkAccessKeyguardStoragePermission())
            return android.provider.Settings.Secure.getString(mContext.getContentResolver(), "bluetooth_name_to_unlock");
        else
            throw new SecurityException("Need android.permission.ACCESS_KEYGUARD_SECURE_STORAGE permission to access");
    }

    public boolean getBluetoothUnlockEnabled()
    {
        boolean flag = false;
        if(checkAccessKeyguardStoragePermission())
        {
            if(android.provider.Settings.Secure.getInt(mContext.getContentResolver(), "bluetooth_unlock_status", 0) != 0)
                flag = true;
            return flag;
        } else
        {
            throw new SecurityException("Need android.permission.ACCESS_KEYGUARD_SECURE_STORAGE permission to access");
        }
    }

    int getCurrentOrCallingUserId()
    {
        if(mMultiUserMode)
            return ActivityManager.getCurrentUser();
        else
            return UserHandle.getCallingUserId();
    }

    public long getKeyguardLockoutAttemptDeadline(int i)
    {
        long l = getLong("lockscreen.lockoutattemptdeadline", 0L);
        long l1 = SystemClock.elapsedRealtime();
        if(l < l1)
            return 0L;
        if(l > (long)getTimeoutInMsByFailedAttempts(i) + l1)
        {
            setLong("lockscreen.lockoutattemptdeadline", (long)getTimeoutInMsByFailedAttempts(i) + l1);
            return (long)getTimeoutInMsByFailedAttempts(i) + l1;
        } else
        {
            return l;
        }
    }

    protected long getLong(String s, long l)
    {
        long l1;
        try
        {
            l1 = getLockSettingsService().getLong(s, l, getCurrentOrCallingUserId());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return l;
        }
        return l1;
    }

    public String getOwnerInfo()
    {
        return android.provider.Settings.Secure.getString(mContext.getContentResolver(), "lock_screen_owner_info");
    }

    public void reportSuccessfulPasswordAttempt()
    {
        reportSuccessfulPasswordAttempt(UserHandle.getCallingUserId());
    }

    public void reportSuccessfulPasswordAttempt(int i)
    {
        setLong("lockscreen.lockoutattemptdeadline", 0L);
        super.reportSuccessfulPasswordAttempt(i);
    }

    public void saveMiuiLockPattern(List list)
    {
        if(android.os.Build.VERSION.SDK_INT < 24) goto _L2; else goto _L1
_L1:
        PrivacyLockPatternUtils.savePrivacyPasswordPattern(list, mMiuiLockPatternDatas[mType].mLockFilename, 0);
_L5:
        return;
_L2:
        byte abyte0[];
        String s;
        abyte0 = LockPatternUtils.patternToHash(list);
        s = mMiuiLockPatternDatas[mType].mLockFilename;
        RandomAccessFile randomaccessfile;
        randomaccessfile = JVM INSTR new #203 <Class RandomAccessFile>;
        randomaccessfile.RandomAccessFile(s, "rw");
        if(list != null)
            break MISSING_BLOCK_LABEL_104;
        randomaccessfile.setLength(0L);
_L3:
        randomaccessfile.close();
        continue; /* Loop/switch isn't completed */
        list;
        Log.e("MiuiLockPatternUtils", (new StringBuilder()).append("Unable to save lock pattern to ").append(s).toString());
        continue; /* Loop/switch isn't completed */
        randomaccessfile.write(abyte0, 0, abyte0.length);
          goto _L3
        list;
        Log.e("MiuiLockPatternUtils", (new StringBuilder()).append("Unable to save lock pattern to ").append(s).toString());
        if(true) goto _L5; else goto _L4
_L4:
    }

    public void saveMiuiLockPatternAsUser(List list, int i)
    {
        String s;
        if(i == 0)
        {
            if(android.os.Build.VERSION.SDK_INT >= 24)
                PrivacyLockPatternUtils.savePrivacyPasswordPattern(list, mMiuiLockPatternDatas[mType].mLockFilename, i);
            else
                saveMiuiLockPattern(list);
        } else
        {
label0:
            {
                byte abyte0[] = LockPatternUtils.patternToHash(list);
                s = getFileNameAsUser(i);
                if(abyte0 == null)
                    break label0;
                try
                {
                    list = mSecurityManager;
                    String s1 = JVM INSTR new #339 <Class String>;
                    s1.String(abyte0, "UTF-8");
                    list.putSystemDataStringFile(s, s1);
                }
                // Misplaced declaration of an exception variable
                catch(List list)
                {
                    Log.e("MiuiLockPatternUtils", "save pattern as user failed");
                }
            }
        }
_L1:
        return;
        mSecurityManager.putSystemDataStringFile(s, "");
          goto _L1
    }

    public boolean savedMiuiLockPatternExists()
    {
        return savedMiuiLockPatternExistsAsUser(UserHandle.myUserId());
    }

    public boolean savedMiuiLockPatternExistsAsUser(int i)
    {
        if(i == 0)
            return mMiuiLockPatternDatas[mType].mHaveNonZeroFile.get();
        else
            return true;
    }

    boolean savedPatternExists()
    {
        boolean flag;
        try
        {
            flag = getLockSettingsService().havePattern(getCurrentOrCallingUserId());
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return flag;
    }

    public void setBluetoothAddressToUnlock(String s)
    {
        if(checkAccessKeyguardStoragePermission())
        {
            android.provider.Settings.Secure.putString(mContext.getContentResolver(), "bluetooth_address_to_unlock", s);
            return;
        } else
        {
            throw new SecurityException("Need android.permission.ACCESS_KEYGUARD_SECURE_STORAGE permission to access");
        }
    }

    public void setBluetoothKeyToUnlock(String s)
    {
        if(checkAccessKeyguardStoragePermission())
        {
            android.provider.Settings.Secure.putString(mContext.getContentResolver(), "bluetooth_key_to_unlock", s);
            return;
        } else
        {
            throw new SecurityException("Need android.permission.ACCESS_KEYGUARD_SECURE_STORAGE permission to access");
        }
    }

    public void setBluetoothNameToUnlock(String s)
    {
        if(checkAccessKeyguardStoragePermission())
        {
            android.provider.Settings.Secure.putString(mContext.getContentResolver(), "bluetooth_name_to_unlock", s);
            return;
        } else
        {
            throw new SecurityException("Need android.permission.ACCESS_KEYGUARD_SECURE_STORAGE permission to access");
        }
    }

    public void setBluetoothUnlockEnabled(boolean flag)
    {
        if(checkAccessKeyguardStoragePermission())
        {
            android.content.ContentResolver contentresolver = mContext.getContentResolver();
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            android.provider.Settings.Secure.putInt(contentresolver, "bluetooth_unlock_status", i);
            return;
        } else
        {
            throw new SecurityException("Need android.permission.ACCESS_KEYGUARD_SECURE_STORAGE permission to access");
        }
    }

    public long setKeyguardLockoutAttemptDeadline(int i)
    {
        long l = SystemClock.elapsedRealtime() + (long)getTimeoutInMsByFailedAttempts(i);
        setLong("lockscreen.lockoutattemptdeadline", l);
        return l;
    }

    public void setKeyguardPasswordQuality(int i)
    {
        if(mContext.checkPermission("miui.permission.USE_INTERNAL_GENERAL_API", Process.myPid(), Process.myUid()) == 0)
        {
            setLong("lockscreen.password_type", i);
            return;
        } else
        {
            throw new SecurityException("Need miui.permission.USE_INTERNAL_GENERAL_API permission to access");
        }
    }

    protected void setLong(String s, long l)
    {
        setLong(s, l, getCurrentOrCallingUserId());
    }

    public static final int FAILED_ATTEMPT_TIMEOUT_SECONDS_ARRAY[] = {
        30, 60, 300
    };
    private static final String MATCHED_BLUETOOTH_DEVICE_ADDRESS_TO_UNLOCK = "bluetooth_address_to_unlock";
    private static final String MATCHED_BLUETOOTH_DEVICE_NAME_TO_UNLOCK = "bluetooth_name_to_unlock";
    private static final String MATCHED_BLUETOOTH_KEY_TO_UNLOCK = "bluetooth_key_to_unlock";
    private static final String MATCHED_BLUETOOTH_UNLOCK_STATUS = "bluetooth_unlock_status";
    public static final int MIUI_LOCK_PATTERN_DATA_TYPE_AC = 0;
    public static final int MIUI_LOCK_PATTERN_DATA_TYPE_GALLERY = 2;
    public static final int MIUI_LOCK_PATTERN_DATA_TYPE_PRIVACY_PASSWORD = 3;
    public static final int MIUI_LOCK_PATTERN_DATA_TYPE_SMS = 1;
    private static final String SYSTEM_DIRECTORY = "/system/";
    private static final String TAG = "MiuiLockPatternUtils";
    private static MiuiLockPatternData mMiuiLockPatternDatas[] = {
        new MiuiLockPatternData("access_control", "access_control.key"), new MiuiLockPatternData("sms", "sms_private.key"), new MiuiLockPatternData("gallery", "gallery_private.key"), new MiuiLockPatternData("privacy_password_setting", "privacy_password_setting.key")
    };
    private Context mContext;
    private ILockSettings mLockSettingsService;
    private final boolean mMultiUserMode;
    private SecurityManager mSecurityManager;
    private int mType;

}
