// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.util;

import android.content.Context;
import android.os.UserHandle;
import com.android.internal.widget.LockPatternUtils;

public class LockPatternUtilsWrapper
{

    public LockPatternUtilsWrapper()
    {
    }

    public static int getActivePasswordQuality(Context context)
    {
        return (new LockPatternUtils(context)).getActivePasswordQuality(UserHandle.myUserId());
    }
}
