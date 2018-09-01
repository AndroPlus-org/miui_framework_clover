// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.util;

import android.content.Context;
import android.media.ExtraRingtoneManager;
import android.net.Uri;
import miui.telephony.SubscriptionManager;
import miui.telephony.TelephonyManager;

public class SimRingtoneUtils
{

    public SimRingtoneUtils()
    {
    }

    private static boolean canSlotSettingRingtoneType(int i)
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = flag;
        if(i == 1) goto _L2; else goto _L1
_L1:
        if(i != 8) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(i != 16)
            flag1 = false;
        if(true) goto _L2; else goto _L5
_L5:
    }

    public static Uri getDefaultRingtoneUri(Context context, int i)
    {
        return getDefaultSoundUriBySlot(context, 1, i);
    }

    public static Uri getDefaultSmsDeliveredUri(Context context, int i)
    {
        return getDefaultSoundUriBySlot(context, 8, i);
    }

    public static Uri getDefaultSmsReceivedUri(Context context, int i)
    {
        return getDefaultSoundUriBySlot(context, 16, i);
    }

    public static Uri getDefaultSoundUri(Context context, int i)
    {
        switch(i)
        {
        default:
            return null;

        case 1: // '\001'
            return android.provider.Settings.System.DEFAULT_RINGTONE_URI;

        case 2: // '\002'
            return android.provider.Settings.System.DEFAULT_NOTIFICATION_URI;

        case 4: // '\004'
            return android.provider.Settings.System.DEFAULT_ALARM_ALERT_URI;

        case 8: // '\b'
            return android.provider.MiuiSettings.System.DEFAULT_SMS_DELIVERED_RINGTONE_URI;

        case 16: // '\020'
            return android.provider.MiuiSettings.System.DEFAULT_SMS_RECEIVED_RINGTONE_URI;

        case 32: // ' '
            return null;

        case 64: // '@'
            return android.provider.MiuiSettings.System.DEFAULT_RINGTONE_URI_SLOT_1;

        case 128: 
            return android.provider.MiuiSettings.System.DEFAULT_RINGTONE_URI_SLOT_2;

        case 1024: 
            return android.provider.MiuiSettings.System.DEFAULT_SMS_RECEIVED_SOUND_URI_SLOT_1;

        case 2048: 
            return android.provider.MiuiSettings.System.DEFAULT_SMS_RECEIVED_SOUND_URI_SLOT_2;

        case 256: 
            return android.provider.MiuiSettings.System.DEFAULT_SMS_DELIVERED_SOUND_URI_SLOT_1;

        case 512: 
            return android.provider.MiuiSettings.System.DEFAULT_SMS_DELIVERED_SOUND_URI_SLOT_2;
        }
    }

    private static Uri getDefaultSoundUriBySlot(Context context, int i, int j)
    {
        int k = i;
        if(j != SubscriptionManager.INVALID_SLOT_ID)
        {
            k = i;
            if(isDefaultSoundUniform(context, i) ^ true)
                k = getExtraRingtoneTypeBySlot(i, j);
        }
        return ExtraRingtoneManager.getDefaultSoundActualUri(context, k);
    }

    public static int getExtraRingtoneTypeBySlot(int i, int j)
    {
        if(j < 0 || j >= TelephonyManager.getDefault().getPhoneCount())
            return i;
        if(i == 1)
        {
            if(j == 0)
                i = 64;
            else
                i = 128;
            return i;
        }
        if(i == 8)
        {
            if(j == 0)
                i = 256;
            else
                i = 512;
            return i;
        }
        if(i == 16)
        {
            if(j == 0)
                i = 1024;
            else
                i = 2048;
            return i;
        } else
        {
            return i;
        }
    }

    private static String getSoundUniformSettingName(int i)
    {
        if(i == 1)
            return "ringtone_sound_use_uniform";
        if(i == 8)
            return "sms_delivered_sound_use_uniform";
        if(i == 16)
            return "sms_received_sound_use_uniform";
        else
            return null;
    }

    public static boolean isDefaultSoundUniform(Context context, int i)
    {
        boolean flag = true;
        if(canSlotSettingRingtoneType(i))
        {
            if(android.provider.Settings.System.getInt(context.getContentResolver(), getSoundUniformSettingName(i), 1) != 1)
                flag = false;
            return flag;
        } else
        {
            return true;
        }
    }

    public static void setDefaultSoundUniform(Context context, int i, boolean flag)
    {
        if(canSlotSettingRingtoneType(i))
        {
            android.content.ContentResolver contentresolver = context.getContentResolver();
            context = getSoundUniformSettingName(i);
            if(flag)
                i = 1;
            else
                i = 0;
            android.provider.Settings.System.putInt(contentresolver, context, i);
        }
    }
}
