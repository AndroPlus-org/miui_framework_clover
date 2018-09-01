// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.provider;

import android.content.Context;
import android.database.sqlite.SQLiteStatement;
import android.media.ExtraRingtoneManager;
import android.net.Uri;
import android.text.TextUtils;
import java.io.File;

public class SettingProviderHelper
{

    public SettingProviderHelper()
    {
    }

    public static String getRingtoneAuthority(Uri uri)
    {
        String s = uri.getAuthority();
        String s1 = s;
        if(s == null)
            s1 = "";
        if(s1.equals("media") || ExtraRingtoneManager.isExtraCases(uri))
            return "media";
        else
            return s1;
    }

    public static Uri getRingtoneUriForExtraCases(Uri uri, int i)
    {
        if("media".equals(getRingtoneAuthority(uri)))
            return ExtraRingtoneManager.getUriForExtraCases(uri, i);
        else
            return uri;
    }

    public static void loadDefaultRingtoneSettings(SQLiteStatement sqlitestatement, Context context)
    {
        loadRingtoneSetting(sqlitestatement, context, "ringtone_default", miui.system.R.string.def_ringtone);
        loadRingtoneSetting(sqlitestatement, context, "ringtone", miui.system.R.string.def_ringtone);
        loadRingtoneSetting(sqlitestatement, context, "alarm_alert", miui.system.R.string.def_alarm_alert);
        loadRingtoneSetting(sqlitestatement, context, "notification_sound", miui.system.R.string.def_notification_sound);
        loadRingtoneSetting(sqlitestatement, context, "calendar_alert", miui.system.R.string.def_notification_sound);
        loadRingtoneSetting(sqlitestatement, context, "sms_delivered_sound", miui.system.R.string.def_sms_delivered_sound);
        loadRingtoneSetting(sqlitestatement, context, "sms_received_sound", miui.system.R.string.def_sms_received_sound);
        loadRingtoneSetting(sqlitestatement, context, "ringtone_sound_slot_1", miui.system.R.string.def_ringtone_slot_1);
        loadRingtoneSetting(sqlitestatement, context, "ringtone_sound_slot_2", miui.system.R.string.def_ringtone_slot_2);
        loadRingtoneSetting(sqlitestatement, context, "sms_delivered_sound_slot_1", miui.system.R.string.def_sms_delivered_sound_slot_1);
        loadRingtoneSetting(sqlitestatement, context, "sms_delivered_sound_slot_2", miui.system.R.string.def_sms_delivered_sound_slot_2);
        loadRingtoneSetting(sqlitestatement, context, "sms_received_sound_slot_1", miui.system.R.string.def_sms_received_sound_slot_1);
        loadRingtoneSetting(sqlitestatement, context, "sms_received_sound_slot_2", miui.system.R.string.def_sms_received_sound_slot_2);
    }

    private static void loadRingtoneSetting(SQLiteStatement sqlitestatement, Context context, String s, int i)
    {
        context = context.getString(i);
        if(!TextUtils.isEmpty(context) && (new File(context)).exists())
        {
            sqlitestatement.bindString(1, s);
            sqlitestatement.bindString(2, (new StringBuilder()).append("file://").append(context).toString());
            sqlitestatement.execute();
        }
    }
}
