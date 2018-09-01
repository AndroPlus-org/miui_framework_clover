// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.app.ExtraNotificationManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Binder;
import android.os.SystemProperties;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import miui.process.ProcessManager;
import miui.util.AudioManagerHelper;

public class AudioServiceInjector
{

    public AudioServiceInjector()
    {
    }

    public static void adjustDefaultStreamVolume(int ai[])
    {
        int i = 0;
        while(i < ai.length) 
        {
            if(i != 0 && i != 6)
                ai[i] = 10;
            i++;
        }
    }

    public static void adjustHiFiVolume(int i, Context context)
    {
        int j = AudioManagerHelper.getHiFiVolume(context);
        if(i != -1) goto _L2; else goto _L1
_L1:
        AudioManagerHelper.setHiFiVolume(context, j - 10);
_L4:
        return;
_L2:
        if(i == 1 && j < 100)
            AudioManagerHelper.setHiFiVolume(context, j + 10);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static void adjustMaxStreamVolume(int ai[])
    {
        int i = 0;
        while(i < ai.length) 
        {
            if(i != 0 && i != 6)
                ai[i] = 15;
            i++;
        }
    }

    public static int calculateStreamMaxVolume(int i, int j, Context context)
    {
        int k = (j + 5) / 10;
        j = k;
        if(3 == i)
        {
            j = k;
            if(AudioManagerHelper.isHiFiMode(context))
                j = k + 10;
        }
        return j;
    }

    public static int calculateStreamVolume(int i, int j, Context context)
    {
        int k = (j + 5) / 10;
        j = k;
        if(i == 3)
        {
            j = k;
            if(AudioManagerHelper.isHiFiMode(context))
                j = k + AudioManagerHelper.getHiFiVolume(context) / 10;
        }
        return j;
    }

    public static int checkForRingerModeChange(Context context, int i, int j, int k)
    {
        return AudioManagerHelper.getValidatedRingerMode(context, j);
    }

    public static void checkMusicStream(Object aobj[], Context context, int i, int j)
    {
    }

    public static int getRingerModeAffectedStreams(int i, Context context)
    {
        if(!android.provider.MiuiSettings.SilenceMode.isSupported)
            return i;
        int j = i;
        if(android.provider.MiuiSettings.SilenceMode.getZenMode(context) == 4)
            j = i | 0x2e;
        return j;
    }

    public static boolean isOnlyAdjustVolume(int i)
    {
        boolean flag = false;
        if((0x100000 & i) != 0)
            flag = true;
        return flag;
    }

    public static boolean isOnlyAdjustVolume(int i, int j, int k)
    {
        boolean flag = false;
        if((0x100000 & i) == 0)
        {
            if(j == 2)
                flag = isXOptMode() ^ true;
        } else
        {
            flag = true;
        }
        return flag;
    }

    public static boolean isPackageProtectedWhenUserBackground(int i, PackageInfo packageinfo)
    {
        return ProcessManager.isLockedApplication(packageinfo.packageName, i);
    }

    public static boolean isXOptMode()
    {
        return SystemProperties.getBoolean("persist.sys.miui_optimization", "1".equals(SystemProperties.get("ro.miui.cts")) ^ true) ^ true;
    }

    public static void setStreamVolumeIntAlt(Object obj, int i, int j, int k, int l, int ai[], Context context)
    {
        boolean flag;
        Class class1;
        flag = AudioManagerHelper.isHiFiMode(context);
        class1 = obj.getClass();
        Method method = null;
        if(android.os.Build.VERSION.SDK_INT < 23) goto _L2; else goto _L1
_L1:
        if(class1 == null)
            break MISSING_BLOCK_LABEL_71;
        method = class1.getDeclaredMethod("setStreamVolumeInt", new Class[] {
            Integer.TYPE, Integer.TYPE, Integer.TYPE, Boolean.TYPE, java/lang/String
        });
_L10:
        if(3 != i || !flag || j < l) goto _L4; else goto _L3
_L3:
        if(method == null) goto _L6; else goto _L5
_L5:
        method.setAccessible(true);
        if(android.os.Build.VERSION.SDK_INT < 23) goto _L8; else goto _L7
_L7:
        method.invoke(obj, new Object[] {
            Integer.valueOf(ai[i]), Integer.valueOf(l), Integer.valueOf(k), Boolean.valueOf(false), "AudioService"
        });
_L6:
        i = (l + 5) / 10;
        AudioManagerHelper.setHiFiVolume(context, (mOriginalIndexWhenSetStreamVolume - i) * 10);
_L12:
        return;
_L2:
        if(class1 == null) goto _L10; else goto _L9
_L9:
        method = class1.getDeclaredMethod("setStreamVolumeInt", new Class[] {
            Integer.TYPE, Integer.TYPE, Integer.TYPE, Boolean.TYPE
        });
          goto _L10
_L8:
        method.invoke(obj, new Object[] {
            Integer.valueOf(ai[i]), Integer.valueOf(l), Integer.valueOf(k), Boolean.valueOf(false)
        });
          goto _L6
_L4:
        if(method == null) goto _L12; else goto _L11
_L11:
label0:
        {
            method.setAccessible(true);
            if(android.os.Build.VERSION.SDK_INT < 23)
                break label0;
            context = JVM INSTR new #161 <Class StringBuilder>;
            context.StringBuilder();
            context = context.append("Pid:").append(Binder.getCallingPid()).append(" Uid:").append(Binder.getCallingUid()).toString();
            method.invoke(obj, new Object[] {
                Integer.valueOf(ai[i]), Integer.valueOf(j), Integer.valueOf(k), Boolean.valueOf(false), context
            });
        }
          goto _L12
        try
        {
            method.invoke(obj, new Object[] {
                Integer.valueOf(ai[i]), Integer.valueOf(j), Integer.valueOf(k), Boolean.valueOf(false)
            });
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            ((UnsupportedOperationException) (obj)).printStackTrace();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            ((NoSuchMethodException) (obj)).printStackTrace();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            ((IllegalAccessException) (obj)).printStackTrace();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            ((InvocationTargetException) (obj)).printStackTrace();
        }
          goto _L12
    }

    public static boolean shouldAdjustHiFiVolume(int i, int j, int k, int l, Context context)
    {
        if(i != 3 || AudioManagerHelper.isHiFiMode(context) ^ true)
            return false;
        i = AudioManagerHelper.getHiFiVolume(context);
        boolean flag;
        if(j == -1 && i > 0)
            i = 1;
        else
            i = 0;
        if(j == 1 && k == l)
            flag = true;
        else
            flag = false;
        if(i != 0)
            flag = true;
        return flag;
    }

    public static void updateRestriction(Context context)
    {
        ExtraNotificationManager.updateRestriction(context);
    }

    private static final String TAG = "AudioService";
    private static final int availableDevice = 140;
    public static int mOriginalIndexWhenSetStreamVolume;
}
