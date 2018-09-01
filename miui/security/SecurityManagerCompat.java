// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.security;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageParser;
import android.miui.Shell;
import android.os.*;
import android.util.Log;
import java.io.File;

public class SecurityManagerCompat
{

    public SecurityManagerCompat()
    {
    }

    public static void checkAppHidden(PackageManager packagemanager, String s, UserHandle userhandle)
    {
        if(packagemanager.getApplicationHiddenSettingAsUser(s, userhandle))
            packagemanager.setApplicationHiddenSettingAsUser(s, false, userhandle);
    }

    public static PackageParser createPackageParser(String s)
    {
        return new PackageParser();
    }

    public static android.content.pm.PackageParser.Package parsePackage(PackageParser packageparser, String s)
    {
        try
        {
            File file = JVM INSTR new #73  <Class File>;
            file.File(s);
            packageparser = packageparser.parsePackage(file, 0);
        }
        // Misplaced declaration of an exception variable
        catch(PackageParser packageparser)
        {
            packageparser.printStackTrace();
            return null;
        }
        return packageparser;
    }

    private static void sendCancelBootAlarm(Context context, long l)
    {
        Intent intent = new Intent("org.codeaurora.poweroffalarm.action.CANCEL_ALARM");
        intent.addFlags(0x10000000);
        intent.setPackage("com.qualcomm.qti.poweroffalarm");
        intent.putExtra("time", 1000L * l);
        context.sendBroadcast(intent);
        Log.d("SecurityManagerCompat", "Send cancel poweroff alarm broadcast");
    }

    private static void sendSetBootAlarm(Context context, long l)
    {
        Intent intent = new Intent("org.codeaurora.poweroffalarm.action.SET_ALARM");
        intent.addFlags(0x10000000);
        intent.setPackage("com.qualcomm.qti.poweroffalarm");
        intent.putExtra("time", 1000L * l);
        context.sendBroadcast(intent);
        Log.d("SecurityManagerCompat", "Send set poweroff alarm broadcast");
    }

    public static void startActvity(Context context, IApplicationThread iapplicationthread, IBinder ibinder, String s, Intent intent)
    {
        ActivityManagerNative.getDefault().startActivity(iapplicationthread, null, intent, intent.resolveTypeIfNeeded(context.getContentResolver()), ibinder, s, -1, 0, null, null);
_L2:
        return;
        context;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static void startActvityAsUser(Context context, IApplicationThread iapplicationthread, IBinder ibinder, String s, Intent intent, int i)
    {
        ActivityManagerNative.getDefault().startActivityAsUser(iapplicationthread, null, intent, intent.resolveTypeIfNeeded(context.getContentResolver()), ibinder, s, -1, 0, null, null, i);
_L2:
        return;
        context;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static void writeBootTime(Context context, String s, long l)
    {
        if(s.equals("mediatek"))
        {
            s = (AlarmManager)context.getSystemService("alarm");
            context = PendingIntent.getBroadcast(context, 0, new Intent(), 0);
            byte byte0;
            if(android.os.Build.VERSION.SDK_INT > 26)
                byte0 = 7;
            else
                byte0 = 8;
            s.set(byte0, 1000L * l, context);
        } else
        if(s.equals("leadcore"))
            Shell.write("/sys/comip/rtc_alarm", String.valueOf(l));
        else
        if(s.equals("pinecone"))
        {
            Shell.write("/sys/class/rtc/rtc1/wakealarm", String.valueOf(0));
            Shell.write("/sys/class/rtc/rtc1/wakealarm", String.valueOf(l));
        } else
        {
            writeQcomBootTime(context, l);
        }
    }

    private static void writeQcomBootTime(Context context, long l)
    {
        if((new File("/sys/class/rtc/rtc0/wakealarm")).exists())
        {
            Shell.write("/sys/class/rtc/rtc0/wakealarm", String.valueOf(l));
            Log.d("SecurityManagerCompat", "Wake up time updated to wakealarm");
        } else
        if(android.os.Build.VERSION.SDK_INT < 26)
        {
            AlarmManager alarmmanager = (AlarmManager)context.getSystemService("alarm");
            context = PendingIntent.getBroadcast(context, 0, new Intent(), 0x8000000);
            try
            {
                alarmmanager.setExact(5, 1000L * l, context);
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                Log.e("SecurityManagerCompat", "alarm type 5 not supported", context);
            }
        } else
        {
            sendCancelBootAlarm(context, l);
            sendSetBootAlarm(context, l);
        }
    }

    private static final String ACTION_CANCEL_POWEROFF_ALARM = "org.codeaurora.poweroffalarm.action.CANCEL_ALARM";
    private static final String ACTION_SET_POWEROFF_ALARM = "org.codeaurora.poweroffalarm.action.SET_ALARM";
    public static final String LEADCORE = "leadcore";
    public static final String MTK = "mediatek";
    private static final String PINECONE = "pinecone";
    private static final String POWER_OFF_ALARM_PACKAGE = "com.qualcomm.qti.poweroffalarm";
    private static final int PRE_SCHEDULE_POWER_OFF_ALARM = 7;
    private static final int RTC_POWEROFF_WAKEUP_MTK = 8;
    private static final int RTC_POWEROFF_WAKEUP_QCOM_M = 5;
    private static final String TAG = "SecurityManagerCompat";
    private static final String TIME = "time";
    public static final String WAKEALARM_PATH_OF_LEADCORE = "/sys/comip/rtc_alarm";
    private static final String WAKEALARM_PATH_OF_PINECONE = "/sys/class/rtc/rtc1/wakealarm";
    public static final String WAKEALARM_PATH_OF_QCOM = "/sys/class/rtc/rtc0/wakealarm";
}
