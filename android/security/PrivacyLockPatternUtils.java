// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security;

import android.os.ServiceManager;
import android.util.Log;
import com.android.internal.widget.ILockSettings;
import com.android.internal.widget.LockPatternUtils;
import java.util.List;

public class PrivacyLockPatternUtils
{

    public PrivacyLockPatternUtils()
    {
    }

    public static boolean checkPrivacyPasswordPattern(List list, String s, int i)
    {
        boolean flag;
        try
        {
            flag = com.android.internal.widget.ILockSettings.Stub.asInterface(ServiceManager.getService("lock_settings")).checkPrivacyPasswordPattern(LockPatternUtils.patternToString(list), s, i);
        }
        // Misplaced declaration of an exception variable
        catch(List list)
        {
            Log.i("PrivacyLockPatternUtils", "CheckPrivacyPasswordPattern error", list);
            return true;
        }
        return flag;
    }

    public static void savePrivacyPasswordPattern(List list, String s, int i)
    {
        list = LockPatternUtils.patternToString(list);
        com.android.internal.widget.ILockSettings.Stub.asInterface(ServiceManager.getService("lock_settings")).savePrivacyPasswordPattern(list, s, i);
_L1:
        return;
        list;
        Log.e("PrivacyLockPatternUtils", "savePrivacyPasswordPattern error", list);
          goto _L1
    }

    private static final String TAG = "PrivacyLockPatternUtils";
}
