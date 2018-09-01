// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.securitycenter.applicationlock;

import android.content.Context;
import android.security.MiuiLockPatternUtils;
import miui.security.SecurityManager;

public class MiuiLockPatternUtilsWrapper
{

    public MiuiLockPatternUtilsWrapper(Context context)
    {
        mLockPatternUtils = new MiuiLockPatternUtils(context);
        mSecurityManager = (SecurityManager)context.getSystemService("security");
    }

    public boolean checkMiuiLockPattern(String s)
    {
        return false;
    }

    public void clearLockoutAttemptDeadline()
    {
        mLockPatternUtils.clearLockoutAttemptDeadline();
    }

    public boolean isTactileFeedbackEnabled()
    {
        return mLockPatternUtils.isTactileFeedbackEnabled();
    }

    public void saveMiuiLockPattern(String s)
    {
    }

    public boolean savedMiuiLockPatternExists()
    {
        return mSecurityManager.haveAccessControlPassword();
    }

    private MiuiLockPatternUtils mLockPatternUtils;
    private SecurityManager mSecurityManager;
}
