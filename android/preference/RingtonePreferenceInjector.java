// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.preference;

import android.content.Intent;

class RingtonePreferenceInjector
{

    RingtonePreferenceInjector()
    {
    }

    static void specifyRingtonePickIntentActivity(Intent intent)
    {
        intent.setClassName("com.android.thememanager", "com.android.thememanager.activity.ThemeTabActivity");
    }
}
