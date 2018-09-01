// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import miui.os.Build;

// Referenced classes of package android.widget:
//            Toast

class ToastInjector
{

    ToastInjector()
    {
    }

    static CharSequence addAppName(Context context, CharSequence charsequence)
    {
        Object obj = charsequence;
        if(Build.IS_INTERNATIONAL_BUILD) goto _L2; else goto _L1
_L1:
        obj = charsequence;
        if((context.getApplicationInfo().flags & 1) != 0) goto _L2; else goto _L3
_L3:
        int i = context.getApplicationInfo().labelRes;
        if(i == 0)
            break MISSING_BLOCK_LABEL_91;
        obj = JVM INSTR new #36  <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        context = ((StringBuilder) (obj)).append(context.getResources().getString(i)).append("\uFF1A").toString();
_L4:
        obj = JVM INSTR new #36  <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        obj = ((StringBuilder) (obj)).append(context).append(charsequence.toString()).toString();
_L2:
        return ((CharSequence) (obj));
        context = "";
          goto _L4
        context;
        context.printStackTrace();
        return charsequence;
        context;
        return charsequence;
    }

    static boolean interceptBackgroundToast(Toast toast, Context context)
    {
        return true;
    }
}
