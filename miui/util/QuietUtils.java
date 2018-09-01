// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.util;

import android.app.ActivityManager;
import android.app.ActivityThread;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.AudioSystem;
import android.net.Uri;
import android.os.PowerManager;
import android.util.Log;
import java.util.*;
import miui.os.Build;

public class QuietUtils
{

    public QuietUtils()
    {
    }

    private static boolean checkAuthorizePackage(Context context, String s, boolean flag)
    {
        if(AUTHORIZE_PACKAGE.contains(s))
            return true;
        return flag && isTopActivity(context, s);
    }

    public static boolean checkNewQuiet(int i, int j, String s, CharSequence charsequence)
    {
        int k;
        if(android.os.Build.VERSION.SDK_INT < 21)
            return false;
        s = ActivityThread.currentApplication();
        charsequence = (PowerManager)s.getSystemService("power");
        charsequence = s.getPackageName();
        k = android.provider.MiuiSettings.SilenceMode.getZenMode(s);
        Log.i("QuietUtils", (new StringBuilder()).append("type:").append(i).append(", flags:").append(j).append(", cpkg:").append(charsequence).toString());
        i;
        JVM INSTR tableswitch 2 8: default 120
    //                   2 122
    //                   3 120
    //                   4 179
    //                   5 120
    //                   6 195
    //                   7 120
    //                   8 205;
           goto _L1 _L2 _L1 _L3 _L1 _L4 _L1 _L5
_L1:
        return false;
_L2:
        if(checkNewZenModeEnable(s, charsequence) && "android".equals(charsequence) ^ true && "com.android.deskclock".equals(charsequence) ^ true)
        {
            Log.w("QuietUtils", (new StringBuilder()).append("POWER_MANAGER_WAKEUP pkg:").append(charsequence).toString());
            return true;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if(checkNewZenModeEnable(s, charsequence) && k == 3)
            return true;
        continue; /* Loop/switch isn't completed */
_L4:
        if(checkNewZenModeEnable(s, charsequence))
            return true;
        continue; /* Loop/switch isn't completed */
_L5:
        if((j == 5 || j == 2) && checkNewZenModeEnable(s, charsequence) && "com.android.phone".equals(charsequence) ^ true && "com.miui.voip".equals(charsequence) ^ true && "android".equals(charsequence) ^ true && AudioSystem.getDevicesForStream(2) == 2)
        {
            Log.d("QuietUtils", "speaker volume is 0");
            return true;
        }
        if(true) goto _L1; else goto _L6
_L6:
    }

    private static boolean checkNewZenModeEnable(Context context, String s)
    {
        return android.provider.MiuiSettings.SilenceMode.isSilenceModeEnable(context);
    }

    public static boolean checkQuiet(int i, int j, String s, CharSequence charsequence)
    {
        android.app.Application application;
        PowerManager powermanager;
        String s1;
        if(android.provider.MiuiSettings.SilenceMode.isSupported)
            return checkNewQuiet(i, j, s, charsequence);
        application = ActivityThread.currentApplication();
        powermanager = (PowerManager)application.getSystemService("power");
        s1 = application.getPackageName();
        i;
        JVM INSTR tableswitch 1 8: default 84
    //                   1 86
    //                   2 193
    //                   3 84
    //                   4 255
    //                   5 393
    //                   6 582
    //                   7 667
    //                   8 757;
           goto _L1 _L2 _L3 _L1 _L4 _L5 _L6 _L7 _L8
_L1:
        return false;
_L2:
        if(((0x10000000 & j) != 0 || j == 26 || j == 10 || j == 6 || j == 1) && ("android".equals(s1) ^ true && "com.android.deskclock".equals(s1) ^ true && "com.google.android.talk".equals(s1) ^ true && checkZenmod(application, s1)))
        {
            Log.w("QuietUtils", (new StringBuilder()).append("POWER_MANAGER pkg:").append(s1).toString());
            return true;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if(checkZenmod(application, s1) && "android".equals(s1) ^ true && "com.android.deskclock".equals(s1) ^ true)
        {
            Log.w("QuietUtils", (new StringBuilder()).append("POWER_MANAGER_WAKEUP pkg:").append(s1).toString());
            return true;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if(checkZenmod(application, s1) && "com.android.deskclock".equals(s1) ^ true && "android".equals(s1) ^ true)
        {
            boolean flag;
            if("com.android.cellbroadcastreceiver".equals(s1))
                flag = Build.checkRegion("CL");
            else
                flag = false;
            if(flag ^ true)
            {
                if(!powermanager.isScreenOn())
                {
                    Log.w("QuietUtils", (new StringBuilder()).append("VIBRATOR pkg:").append(s1).toString());
                    return true;
                }
                if(!isTopActivity(application, s1))
                {
                    Log.w("QuietUtils", (new StringBuilder()).append("VIBRATOR pkg:").append(s1).toString());
                    return true;
                }
            }
        }
        if(true) goto _L1; else goto _L5
_L5:
        if(charsequence != null)
            s1 = charsequence.toString();
        else
        if(s != null)
            s1 = s;
        else
            s1 = "";
        if(checkZenmod(application, s1))
        {
            if(!powermanager.isScreenOn())
            {
                Log.w("QuietUtils", (new StringBuilder()).append("NOTIFICATION pkg:").append(s).append(" targetPkg:").append(charsequence).toString());
                return true;
            }
            if(charsequence == null)
                continue; /* Loop/switch isn't completed */
            if(!isTopActivity(application, charsequence.toString()))
            {
                Log.w("QuietUtils", (new StringBuilder()).append("NOTIFICATION pkg:").append(s).append(" targetPkg:").append(charsequence).toString());
                return true;
            }
        }
        continue; /* Loop/switch isn't completed */
        if(s == null || isTopActivity(application, s.toString())) goto _L1; else goto _L9
_L9:
        Log.w("QuietUtils", (new StringBuilder()).append("NOTIFICATION pkg:").append(s).append(" targetPkg:").append(charsequence).toString());
        return true;
_L6:
        if(checkZenmod(application, s1))
        {
            if(!powermanager.isScreenOn())
            {
                Log.w("QuietUtils", (new StringBuilder()).append("SOUND_POOL pkg:").append(s1).toString());
                return true;
            }
            if(!checkAuthorizePackage(application, s1, true))
            {
                Log.w("QuietUtils", (new StringBuilder()).append("SOUND_POOL pkg:").append(s1).toString());
                return true;
            }
        }
        continue; /* Loop/switch isn't completed */
_L7:
        if(j == 2 && checkZenmod(application, s1))
        {
            if(!powermanager.isScreenOn())
            {
                Log.w("QuietUtils", (new StringBuilder()).append("MEDIA_PLAYER pkg:").append(s1).toString());
                return true;
            }
            if(!checkAuthorizePackage(application, s1, true))
            {
                Log.w("QuietUtils", (new StringBuilder()).append("MEDIA_PLAYER pkg:").append(s1).toString());
                return true;
            }
        }
        continue; /* Loop/switch isn't completed */
_L8:
        if((j == 5 || j == 2) && "android".equals(s1) ^ true && "com.android.systemui".equals(s1) ^ true && checkZenmod(application, s1))
        {
            if(!powermanager.isScreenOn())
            {
                Log.w("QuietUtils", (new StringBuilder()).append("AUDIO_MANAGER pkg:").append(s1).toString());
                return true;
            }
            if(!checkAuthorizePackage(application, s1, true))
            {
                Log.w("QuietUtils", (new StringBuilder()).append("AUDIO_MANAGER pkg:").append(s1).toString());
                return true;
            }
        }
        if(true) goto _L1; else goto _L10
_L10:
    }

    private static boolean checkZenmod(Context context, String s)
    {
        boolean flag = false;
        boolean flag1 = false;
        if(android.os.Build.VERSION.SDK_INT < 21)
        {
            boolean flag2 = flag1;
            if(android.provider.MiuiSettings.AntiSpam.isQuietModeEnable(context))
            {
                flag2 = flag1;
                if("com.android.phone".equals(s) ^ true)
                    flag2 = "com.miui.voip".equals(s) ^ true;
            }
            return flag2;
        }
        boolean flag3 = flag;
        if(android.provider.MiuiSettings.AntiSpam.isQuietModeEnable(context))
        {
            flag3 = flag;
            if(PHONE_AND_SMS_PACKAGE.contains(s) ^ true)
                flag3 = isZenmode(context, "4") ^ true;
        }
        return flag3;
    }

    private static boolean isTopActivity(Context context, String s)
    {
        context = ((ActivityManager)context.getSystemService("activity")).getRunningAppProcesses().iterator();
_L4:
        String as[];
        int i;
        android.app.ActivityManager.RunningAppProcessInfo runningappprocessinfo;
        do
        {
            if(!context.hasNext())
                break MISSING_BLOCK_LABEL_116;
            runningappprocessinfo = (android.app.ActivityManager.RunningAppProcessInfo)context.next();
        } while(runningappprocessinfo.importance != 100);
        as = runningappprocessinfo.pkgList;
        i = as.length;
        int j = 0;
_L2:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        boolean flag = as[j].equals(s);
        if(flag)
            return true;
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        if(true) goto _L4; else goto _L3
_L3:
        context;
        Log.i("QuietUtils", (new StringBuilder()).append("Fail to get RunningProcessInfo:").append(context.toString()).toString());
        return false;
    }

    private static boolean isZenmode(Context context, String s)
    {
        context = context.getContentResolver().query(Uri.withAppendedPath(Uri.parse("content://antispamCommon/zenmode"), s), null, null, null, null);
        if(context != null)
        {
            if(context != null)
                try
                {
                    context.close();
                }
                // Misplaced declaration of an exception variable
                catch(Context context) { }
            return true;
        }
        if(context != null)
            try
            {
                context.close();
            }
            // Misplaced declaration of an exception variable
            catch(Context context) { }
_L2:
        return false;
        context;
        s = JVM INSTR new #128 <Class StringBuilder>;
        s.StringBuilder();
        Log.e("QuietUtils", s.append("exception when checkZenmodConfig :").append(context.toString()).toString());
        if(true) goto _L2; else goto _L1
_L1:
        context;
        throw context;
    }

    private static ArrayList AUTHORIZE_PACKAGE = new ArrayList(Arrays.asList(new String[] {
        "android", "com.android.deskclock", "com.android.providers.telephony"
    }));
    private static ArrayList PHONE_AND_SMS_PACKAGE = new ArrayList(Arrays.asList(new String[] {
        "com.android.phone", "com.android.incallui", "com.android.server.telecom", "com.miui.voip", "com.android.mms"
    }));
    private static final String TAG = "QuietUtils";
    public static final int TYPE_AUDIO_MANAGER = 8;
    public static final int TYPE_MEDIA_PLAYER = 7;
    public static final int TYPE_NOTIFICATION = 5;
    public static final int TYPE_POWER_MANAGER = 1;
    public static final int TYPE_POWER_MANAGER_SERVICE = 3;
    public static final int TYPE_POWER_MANAGER_WAKEUP = 2;
    public static final int TYPE_SOUND_POOL = 6;
    public static final int TYPE_VIBRATOR = 4;
    public static final String ZENMODE_TYPE_ALLW_FROM = "5";
    public static final String ZENMODE_TYPE_CALL_STATUS = "3";
    public static final String ZENMODE_TYPE_EVENT_STATUS = "4";
    public static final String ZENMODE_TYPE_MESSAGE_STATUS = "2";
    public static final String ZENMODE_TYPE_STATUS = "1";

}
