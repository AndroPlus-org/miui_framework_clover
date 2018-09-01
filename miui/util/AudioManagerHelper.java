// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.util;

import android.content.Context;
import android.media.AudioManager;
import android.os.Vibrator;

public class AudioManagerHelper
{

    public AudioManagerHelper()
    {
    }

    public static int getHiFiVolume(Context context)
    {
        int i;
        try
        {
            i = Integer.valueOf(((AudioManager)context.getSystemService("audio")).getParameters("hifi_volume").replace("hifi_volume=", "")).intValue();
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            return 0;
        }
        return i;
    }

    public static int getNewValidatedRingerModeForUser(Context context, int i, int j)
    {
        j = i;
        if(i == 1)
            j = 0;
        return j;
    }

    public static int getValidatedRingerMode(Context context, int i)
    {
        return getValidatedRingerModeForUser(context, i, -3);
    }

    public static int getValidatedRingerModeForUser(Context context, int i, int j)
    {
        if(android.provider.MiuiSettings.SilenceMode.isSupported)
            return getNewValidatedRingerModeForUser(context, i, j);
        boolean flag = isVibrateEnabledForUser(context, i, j);
        if(i == 0)
        {
            if(flag)
                return 1;
        } else
        if(1 == i && !flag)
            return 0;
        return i;
    }

    public static boolean isHiFiMode(Context context)
    {
        context = (AudioManager)context.getSystemService("audio");
        boolean flag = context.isWiredHeadsetOn();
        boolean flag1 = context.getParameters("hifi_mode").contains("true");
        if(!flag)
            flag1 = false;
        return flag1;
    }

    public static boolean isNewSilentEnabled(Context context)
    {
        boolean flag = false;
        if(android.provider.MiuiSettings.SilenceMode.getZenMode(context) != 0)
            flag = true;
        return flag;
    }

    public static boolean isNewVibrateEnabled(Context context)
    {
        return isVibrateEnabled(context, android.provider.MiuiSettings.SilenceMode.getZenMode(context));
    }

    public static boolean isNewVibrateEnabledForUser(Context context, int i)
    {
        return isVibrateEnabledForUser(context, android.provider.MiuiSettings.SilenceMode.getZenMode(context), i);
    }

    public static boolean isSilentEnabled(Context context)
    {
        if(android.provider.MiuiSettings.SilenceMode.isSupported)
            return isNewSilentEnabled(context);
        boolean flag;
        if(((AudioManager)context.getSystemService("audio")).getRingerMode() != 2)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean isVibrateEnabled(Context context)
    {
        if(android.provider.MiuiSettings.SilenceMode.isSupported)
            return isNewVibrateEnabled(context);
        else
            return isVibrateEnabled(context, ((AudioManager)context.getSystemService("audio")).getRingerMode());
    }

    public static boolean isVibrateEnabled(Context context, int i)
    {
        return isVibrateEnabledForUser(context, i, -3);
    }

    public static boolean isVibrateEnabledForUser(Context context, int i)
    {
        if(android.provider.MiuiSettings.SilenceMode.isSupported)
            return isNewVibrateEnabledForUser(context, i);
        else
            return isVibrateEnabledForUser(context, ((AudioManager)context.getSystemService("audio")).getRingerMode(), i);
    }

    public static boolean isVibrateEnabledForUser(Context context, int i, int j)
    {
        boolean flag = false;
        boolean flag1 = true;
        boolean flag2 = ((Vibrator)context.getSystemService("vibrator")).hasVibrator();
        int k;
        if(android.provider.MiuiSettings.SilenceMode.isSupported)
            k = 0;
        else
            k = 2;
        if(k != i)
        {
            if(!flag2 || android.provider.Settings.System.getIntForUser(context.getContentResolver(), "vibrate_in_silent", 1, j) != 1)
                flag1 = false;
            return flag1;
        }
        flag1 = flag;
        if(flag2)
        {
            context = context.getContentResolver();
            if(android.provider.MiuiSettings.System.VIBRATE_IN_NORMAL_DEFAULT)
                i = 1;
            else
                i = 0;
            flag1 = flag;
            if(android.provider.Settings.System.getIntForUser(context, "vibrate_in_normal", i, j) == 1)
                flag1 = true;
        }
        return flag1;
    }

    public static void newToggleSilentForUser(Context context, int i, int j)
    {
        if(isSilentEnabled(context))
            i = 0;
        else
            i = android.provider.MiuiSettings.SilenceMode.getLastestQuietMode(context);
        android.provider.MiuiSettings.SilenceMode.setSilenceMode(context, i, null);
    }

    public static void setHiFiVolume(Context context, int i)
    {
        ((AudioManager)context.getSystemService("audio")).setParameters((new StringBuilder()).append("hifi_volume=").append(i).toString());
    }

    public static void setVibrateSetting(Context context, boolean flag, boolean flag1)
    {
        setVibrateSettingForUser(context, flag, flag1, -3);
    }

    public static void setVibrateSettingForUser(Context context, boolean flag, boolean flag1, int i)
    {
        android.content.ContentResolver contentresolver = context.getContentResolver();
        String s;
        int j;
        if(flag1)
            s = "vibrate_in_silent";
        else
            s = "vibrate_in_normal";
        if(flag)
            j = 1;
        else
            j = 0;
        android.provider.Settings.System.putIntForUser(contentresolver, s, j, i);
        if(android.provider.MiuiSettings.SilenceMode.isSupported)
            validateRingerMode(context, flag, flag1);
        else
            validateRingerMode(context, i);
    }

    public static void toggleSilent(Context context, int i)
    {
        toggleSilentForUser(context, i, -3);
    }

    public static void toggleSilentForUser(Context context, int i, int j)
    {
        if(android.provider.MiuiSettings.SilenceMode.isSupported)
        {
            newToggleSilentForUser(context, i, j);
            return;
        }
        AudioManager audiomanager = (AudioManager)context.getSystemService("audio");
        if(2 == audiomanager.getRingerMode())
        {
            if(isVibrateEnabledForUser(context, 0, j))
                j = 1;
            else
                j = 0;
        } else
        {
            j = 2;
        }
        audiomanager.setRingerMode(j);
        if(i != 0)
            audiomanager.adjustStreamVolume(2, 0, i);
    }

    public static void toggleVibrateSetting(Context context)
    {
        toggleVibrateSettingForUser(context, -3);
    }

    public static void toggleVibrateSettingForUser(Context context, int i)
    {
        setVibrateSettingForUser(context, isVibrateEnabledForUser(context, i) ^ true, isSilentEnabled(context), i);
    }

    private static void validateRingerMode(Context context, int i)
    {
        AudioManager audiomanager = (AudioManager)context.getSystemService("audio");
        int j = audiomanager.getRingerMode();
        i = getValidatedRingerModeForUser(context, j, i);
        if(j != i)
            audiomanager.setRingerMode(i);
    }

    private static void validateRingerMode(Context context, boolean flag, boolean flag1)
    {
        if(!flag1)
            return;
        if(android.provider.MiuiSettings.SilenceMode.getZenMode(context) == 4)
        {
            context = (AudioManager)context.getSystemService("audio");
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            context.setRingerMode(i);
        }
    }

    public static final int FLAG_ONLY_SET_VOLUME = 0x100000;
    public static final int FLAG_SHOW_UI_WARNINGS = 1024;
}
