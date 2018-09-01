// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.accounts;

import android.content.Intent;

public class ChooseTypeAndAccountActivityInjector
{

    public ChooseTypeAndAccountActivityInjector()
    {
    }

    public static void toMiuiChooseAccountTypeActivity(Intent intent)
    {
        intent.setClassName("com.miui.rom", "miui.accounts.MiuiChooseAccountTypeActivity");
    }
}
