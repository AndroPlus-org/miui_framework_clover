// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.securitycenter.applicationlock;

import android.content.Context;
import android.security.ChooseLockSettingsHelper;

// Referenced classes of package miui.securitycenter.applicationlock:
//            MiuiLockPatternUtilsWrapper

public class ChooserLockSettingsHelperWrapper
{

    public ChooserLockSettingsHelperWrapper(Context context)
    {
        mMiuiLockPatternUtilsWrapper = new MiuiLockPatternUtilsWrapper(context);
        mChooseLockSettingsHelper = new ChooseLockSettingsHelper(context);
    }

    public boolean isACLockEnabled()
    {
        return mMiuiLockPatternUtilsWrapper.savedMiuiLockPatternExists();
    }

    public void setACLockEnabled(boolean flag)
    {
        mChooseLockSettingsHelper.setACLockEnabled(flag);
    }

    public void setPasswordForPrivacyModeEnabled(boolean flag)
    {
        mChooseLockSettingsHelper.setPasswordForPrivacyModeEnabled(flag);
    }

    public void setPrivacyModeEnabled(boolean flag)
    {
        mChooseLockSettingsHelper.setPrivacyModeEnabled(flag);
    }

    public MiuiLockPatternUtilsWrapper utils()
    {
        return mMiuiLockPatternUtilsWrapper;
    }

    private ChooseLockSettingsHelper mChooseLockSettingsHelper;
    private MiuiLockPatternUtilsWrapper mMiuiLockPatternUtilsWrapper;
}
