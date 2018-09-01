// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.securitycenter.applicationlock;

import android.content.Context;
import android.security.MiuiLockPatternUtils;

public class ApplicationLockHelper
{

    public ApplicationLockHelper(Context context)
    {
        mContext = context;
        mLockPatternUtils = new MiuiLockPatternUtils(context);
    }

    public boolean checkLockPattern(String s)
    {
        return false;
    }

    public void clearAppLock()
    {
        mLockPatternUtils.clearLock(null, mContext.getUserId());
    }

    public long getLockoutAttempt()
    {
        return mLockPatternUtils.getLockoutAttemptDeadline(mContext.getUserId());
    }

    public boolean isVisiblePatternLock()
    {
        return mLockPatternUtils.isVisiblePatternEnabled(mContext.getUserId());
    }

    public boolean saveLockPatternExists()
    {
        return mLockPatternUtils.savedMiuiLockPatternExists();
    }

    public long setLockoutAttempt()
    {
        return mLockPatternUtils.setLockoutAttemptDeadline(mContext.getUserId(), 30000);
    }

    private Context mContext;
    private MiuiLockPatternUtils mLockPatternUtils;
}
