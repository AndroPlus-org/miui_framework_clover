// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;

// Referenced classes of package android.security:
//            MiuiLockPatternUtils

public final class ChooseLockSettingsHelper
{

    public ChooseLockSettingsHelper(Activity activity)
    {
        this(((Context) (activity)));
        mActivity = activity;
    }

    public ChooseLockSettingsHelper(Activity activity, int i)
    {
        mActivity = activity;
        mContext = activity;
        mLockPatternUtils = new MiuiLockPatternUtils(mContext, i);
    }

    public ChooseLockSettingsHelper(Activity activity, Fragment fragment)
    {
        this(activity);
        mFragment = fragment;
    }

    public ChooseLockSettingsHelper(Context context)
    {
        mContext = context;
        mLockPatternUtils = new MiuiLockPatternUtils(mContext);
    }

    private boolean confirmPassword(int i)
    {
        if(!mLockPatternUtils.isLockPasswordEnabled(mLockPatternUtils.getCurrentOrCallingUserId()))
            return false;
        Intent intent = new Intent();
        intent.setClassName("com.android.settings", "com.android.settings.ConfirmLockPassword");
        if(i == -1024)
        {
            intent.setFlags(0x10000000);
            mContext.startActivity(intent);
        } else
        if(mFragment != null)
            mFragment.startActivityForResult(intent, i);
        else
            mActivity.startActivityForResult(intent, i);
        return true;
    }

    private boolean confirmPattern(int i, CharSequence charsequence, CharSequence charsequence1)
    {
        if(!mLockPatternUtils.isLockPatternEnabled(mLockPatternUtils.getCurrentOrCallingUserId()) || mLockPatternUtils.savedPatternExists() ^ true)
            return false;
        Intent intent = new Intent();
        intent.putExtra("com.android.settings.ConfirmLockPattern.header", charsequence);
        intent.putExtra("com.android.settings.ConfirmLockPattern.footer", charsequence1);
        intent.setClassName("com.android.settings", "com.android.settings.ConfirmLockPattern");
        if(i == -1024)
        {
            intent.setFlags(0x10000000);
            mContext.startActivity(intent);
        } else
        if(mFragment != null)
            mFragment.startActivityForResult(intent, i);
        else
            mActivity.startActivityForResult(intent, i);
        return true;
    }

    public boolean isACLockEnabled()
    {
        boolean flag = false;
        if(1 == android.provider.Settings.Secure.getInt(mContext.getContentResolver(), "access_control_lock_enabled", 0))
            flag = mLockPatternUtils.savedMiuiLockPatternExists();
        return flag;
    }

    public boolean isPasswordForPrivacyModeEnabled()
    {
        boolean flag = true;
        if(1 != android.provider.Settings.Secure.getInt(mContext.getContentResolver(), "password_for_privacymode", 0))
            flag = false;
        return flag;
    }

    public boolean isPrivacyModeEnabled()
    {
        boolean flag = true;
        if(1 != android.provider.Settings.Secure.getInt(mContext.getContentResolver(), "privacy_mode_enabled", 0))
            flag = false;
        return flag;
    }

    public boolean isPrivacyPasswordEnabled()
    {
        boolean flag = true;
        if(1 != android.provider.Settings.Secure.getInt(mContext.getContentResolver(), "privacy_password_is_open", 0))
            flag = false;
        return flag;
    }

    public boolean isPrivateGalleryEnabled()
    {
        boolean flag = false;
        if(1 == android.provider.Settings.Secure.getInt(mContext.getContentResolver(), "private_gallery_lock_enabled", 0))
            flag = mLockPatternUtils.savedMiuiLockPatternExists();
        return flag;
    }

    public boolean isPrivateSmsEnabled()
    {
        boolean flag = false;
        if(1 == android.provider.Settings.Secure.getInt(mContext.getContentResolver(), "private_sms_lock_enabled", 0))
            flag = mLockPatternUtils.savedMiuiLockPatternExists();
        return flag;
    }

    public boolean launchConfirmationActivity(int i, CharSequence charsequence, CharSequence charsequence1)
    {
        boolean flag;
        if(mActivity == null)
            return false;
        if(mLockPatternUtils == null)
            return false;
        flag = false;
        mLockPatternUtils.getKeyguardStoredPasswordQuality(mLockPatternUtils.getCurrentOrCallingUserId());
        JVM INSTR lookupswitch 5: default 84
    //                   65536: 87
    //                   131072: 99
    //                   262144: 99
    //                   327680: 99
    //                   393216: 99;
           goto _L1 _L2 _L3 _L3 _L3 _L3
_L1:
        return flag;
_L2:
        flag = confirmPattern(i, charsequence, charsequence1);
        continue; /* Loop/switch isn't completed */
_L3:
        flag = confirmPassword(i);
        if(true) goto _L1; else goto _L4
_L4:
    }

    public boolean launchConfirmationActivity(CharSequence charsequence, CharSequence charsequence1)
    {
        boolean flag;
        flag = false;
        if(mLockPatternUtils == null)
            return false;
        mLockPatternUtils.getKeyguardStoredPasswordQuality(mLockPatternUtils.getCurrentOrCallingUserId());
        JVM INSTR lookupswitch 5: default 76
    //                   65536: 78
    //                   131072: 91
    //                   262144: 91
    //                   327680: 91
    //                   393216: 91;
           goto _L1 _L2 _L3 _L3 _L3 _L3
_L1:
        return flag;
_L2:
        flag = confirmPattern(-1024, charsequence, charsequence1);
        continue; /* Loop/switch isn't completed */
_L3:
        flag = confirmPassword(-1024);
        if(true) goto _L1; else goto _L4
_L4:
    }

    public void setACLockEnabled(boolean flag)
    {
        android.content.ContentResolver contentresolver = mContext.getContentResolver();
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        android.provider.Settings.Secure.putInt(contentresolver, "access_control_lock_enabled", i);
        if(!flag)
            mLockPatternUtils.saveMiuiLockPattern(null);
    }

    public void setPasswordForPrivacyModeEnabled(boolean flag)
    {
        android.content.ContentResolver contentresolver = mContext.getContentResolver();
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        android.provider.Settings.Secure.putInt(contentresolver, "password_for_privacymode", i);
    }

    public void setPrivacyModeEnabled(boolean flag)
    {
        android.content.ContentResolver contentresolver = mContext.getContentResolver();
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        android.provider.Settings.Secure.putInt(contentresolver, "privacy_mode_enabled", i);
    }

    public void setPrivacyPasswordEnable(boolean flag)
    {
        setPrivacyPasswordEnabledAsUser(flag, UserHandle.myUserId());
    }

    public void setPrivacyPasswordEnabledAsUser(boolean flag, int i)
    {
        android.content.ContentResolver contentresolver = mContext.getContentResolver();
        int j;
        if(flag)
            j = 1;
        else
            j = 0;
        android.provider.Settings.Secure.putIntForUser(contentresolver, "privacy_password_is_open", j, i);
    }

    public void setPrivateGalleryEnabled(boolean flag)
    {
        setPrivateGalleryEnabledAsUser(flag, UserHandle.myUserId());
    }

    public void setPrivateGalleryEnabledAsUser(boolean flag, int i)
    {
        android.content.ContentResolver contentresolver = mContext.getContentResolver();
        int j;
        if(flag)
            j = 1;
        else
            j = 0;
        android.provider.Settings.Secure.putIntForUser(contentresolver, "private_gallery_lock_enabled", j, i);
    }

    public void setPrivateSmsEnabled(boolean flag)
    {
        setPrivateSmsEnabledAsUser(flag, UserHandle.myUserId());
    }

    public void setPrivateSmsEnabledAsUser(boolean flag, int i)
    {
        android.content.ContentResolver contentresolver = mContext.getContentResolver();
        int j;
        if(flag)
            j = 1;
        else
            j = 0;
        android.provider.Settings.Secure.putIntForUser(contentresolver, "private_sms_lock_enabled", j, i);
    }

    public MiuiLockPatternUtils utils()
    {
        return mLockPatternUtils;
    }

    public static final String BG_COLOR = "com.android.settings.bgColor";
    public static final int DISABLE_ACCESS_CONTROL = 1;
    public static final int DISABLE_AC_FOR_PRIVACY_MODE = 3;
    public static final int DISABLE_PRIVACY_MODE = 4;
    public static final int ENABLE_AC_FOR_PRIVACY_MODE = 2;
    public static final String EXTRA_CONFIRM_PURPOSE = "confirm_purpose";
    public static final String EXTRA_KEY_PASSWORD = "password";
    public static final String FOOTER_TEXT = "com.android.settings.ConfirmLockPattern.footer";
    public static final String FOOTER_TEXT_COLOR = "com.android.settings.footerTextColor";
    public static final String FOOTER_WRONG_TEXT = "com.android.settings.ConfirmLockPattern.footer_wrong";
    public static final String FORGET_PATTERN_COLOR = "com.android.settings.forgetPatternColor";
    public static final String HEADER_TEXT = "com.android.settings.ConfirmLockPattern.header";
    public static final String HEADER_WRONG_TEXT = "com.android.settings.ConfirmLockPattern.header_wrong";
    public static final String LOCK_BTN_WHITE = "com.android.settings.lockBtnWhite";
    public static final int LOCK_SETTINGS_TYPE_AC = 0;
    public static final int LOCK_SETTINGS_TYPE_GALLERY = 2;
    public static final int LOCK_SETTINGS_TYPE_SMS = 1;
    private static final int NO_REQUEST_FOR_ACTIVITY_RESULT = -1024;
    public static final String SHOW_FORGET_PASSWORD = "com.android.settings.forgetPassword";
    public static final String TITLE_COLOR = "com.android.settings.titleColor";
    public static final String USERID_TO_CONFIRM_PASSWORD = "com.android.settings.userIdToConfirm";
    private Activity mActivity;
    private Context mContext;
    private Fragment mFragment;
    private MiuiLockPatternUtils mLockPatternUtils;
}
