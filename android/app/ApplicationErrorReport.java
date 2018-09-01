// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.*;
import android.content.pm.*;
import android.os.*;
import android.util.Printer;
import com.android.internal.util.FastPrintWriter;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ApplicationErrorReport
    implements Parcelable
{
    public static class AnrInfo
    {

        public void dump(Printer printer, String s)
        {
            printer.println((new StringBuilder()).append(s).append("activity: ").append(activity).toString());
            printer.println((new StringBuilder()).append(s).append("cause: ").append(cause).toString());
            printer.println((new StringBuilder()).append(s).append("info: ").append(info).toString());
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeString(activity);
            parcel.writeString(cause);
            parcel.writeString(info);
        }

        public String activity;
        public String cause;
        public String info;

        public AnrInfo()
        {
        }

        public AnrInfo(Parcel parcel)
        {
            activity = parcel.readString();
            cause = parcel.readString();
            info = parcel.readString();
        }
    }

    public static class BatteryInfo
    {

        public void dump(Printer printer, String s)
        {
            printer.println((new StringBuilder()).append(s).append("usagePercent: ").append(usagePercent).toString());
            printer.println((new StringBuilder()).append(s).append("durationMicros: ").append(durationMicros).toString());
            printer.println((new StringBuilder()).append(s).append("usageDetails: ").append(usageDetails).toString());
            printer.println((new StringBuilder()).append(s).append("checkinDetails: ").append(checkinDetails).toString());
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(usagePercent);
            parcel.writeLong(durationMicros);
            parcel.writeString(usageDetails);
            parcel.writeString(checkinDetails);
        }

        public String checkinDetails;
        public long durationMicros;
        public String usageDetails;
        public int usagePercent;

        public BatteryInfo()
        {
        }

        public BatteryInfo(Parcel parcel)
        {
            usagePercent = parcel.readInt();
            durationMicros = parcel.readLong();
            usageDetails = parcel.readString();
            checkinDetails = parcel.readString();
        }
    }

    public static class CrashInfo
    {

        private String sanitizeString(String s)
        {
            if(s != null && s.length() > 20480)
            {
                String s1 = (new StringBuilder()).append("\n[TRUNCATED ").append(s.length() - 20480).append(" CHARS]\n").toString();
                StringBuilder stringbuilder = new StringBuilder(s1.length() + 20480);
                stringbuilder.append(s.substring(0, 10240));
                stringbuilder.append(s1);
                stringbuilder.append(s.substring(s.length() - 10240));
                return stringbuilder.toString();
            } else
            {
                return s;
            }
        }

        public void appendStackTrace(String s)
        {
            stackTrace = sanitizeString((new StringBuilder()).append(stackTrace).append(s).toString());
        }

        public void dump(Printer printer, String s)
        {
            printer.println((new StringBuilder()).append(s).append("exceptionClassName: ").append(exceptionClassName).toString());
            printer.println((new StringBuilder()).append(s).append("exceptionMessage: ").append(exceptionMessage).toString());
            printer.println((new StringBuilder()).append(s).append("throwFileName: ").append(throwFileName).toString());
            printer.println((new StringBuilder()).append(s).append("throwClassName: ").append(throwClassName).toString());
            printer.println((new StringBuilder()).append(s).append("throwMethodName: ").append(throwMethodName).toString());
            printer.println((new StringBuilder()).append(s).append("throwLineNumber: ").append(throwLineNumber).toString());
            printer.println((new StringBuilder()).append(s).append("stackTrace: ").append(stackTrace).toString());
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.dataPosition();
            parcel.writeString(exceptionClassName);
            parcel.writeString(exceptionMessage);
            parcel.writeString(throwFileName);
            parcel.writeString(throwClassName);
            parcel.writeString(throwMethodName);
            parcel.writeInt(throwLineNumber);
            parcel.writeString(stackTrace);
            parcel.dataPosition();
        }

        public String exceptionClassName;
        public String exceptionMessage;
        public String stackTrace;
        public String throwClassName;
        public String throwFileName;
        public int throwLineNumber;
        public String throwMethodName;

        public CrashInfo()
        {
        }

        public CrashInfo(Parcel parcel)
        {
            exceptionClassName = parcel.readString();
            exceptionMessage = parcel.readString();
            throwFileName = parcel.readString();
            throwClassName = parcel.readString();
            throwMethodName = parcel.readString();
            throwLineNumber = parcel.readInt();
            stackTrace = parcel.readString();
        }

        public CrashInfo(Throwable throwable)
        {
            StringWriter stringwriter = new StringWriter();
            Object obj = new FastPrintWriter(stringwriter, false, 256);
            throwable.printStackTrace(((PrintWriter) (obj)));
            ((PrintWriter) (obj)).flush();
            stackTrace = sanitizeString(stringwriter.toString());
            exceptionMessage = throwable.getMessage();
            obj = throwable;
            do
            {
                if(throwable.getCause() == null)
                    break;
                Throwable throwable1 = throwable.getCause();
                Throwable throwable2 = ((Throwable) (obj));
                if(throwable1.getStackTrace() != null)
                {
                    throwable2 = ((Throwable) (obj));
                    if(throwable1.getStackTrace().length > 0)
                        throwable2 = throwable1;
                }
                String s = throwable1.getMessage();
                obj = throwable2;
                throwable = throwable1;
                if(s != null)
                {
                    obj = throwable2;
                    throwable = throwable1;
                    if(s.length() > 0)
                    {
                        exceptionMessage = s;
                        obj = throwable2;
                        throwable = throwable1;
                    }
                }
            } while(true);
            exceptionClassName = ((Throwable) (obj)).getClass().getName();
            if(((Throwable) (obj)).getStackTrace().length > 0)
            {
                throwable = ((Throwable) (obj)).getStackTrace()[0];
                throwFileName = throwable.getFileName();
                throwClassName = throwable.getClassName();
                throwMethodName = throwable.getMethodName();
                throwLineNumber = throwable.getLineNumber();
            } else
            {
                throwFileName = "unknown";
                throwClassName = "unknown";
                throwMethodName = "unknown";
                throwLineNumber = 0;
            }
            exceptionMessage = sanitizeString(exceptionMessage);
        }
    }

    public static class ParcelableCrashInfo extends CrashInfo
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public ParcelableCrashInfo createFromParcel(Parcel parcel)
            {
                return new ParcelableCrashInfo(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public ParcelableCrashInfo[] newArray(int i)
            {
                return new ParcelableCrashInfo[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;


        public ParcelableCrashInfo()
        {
        }

        public ParcelableCrashInfo(Parcel parcel)
        {
            super(parcel);
        }

        public ParcelableCrashInfo(Throwable throwable)
        {
            super(throwable);
        }
    }

    public static class RunningServiceInfo
    {

        public void dump(Printer printer, String s)
        {
            printer.println((new StringBuilder()).append(s).append("durationMillis: ").append(durationMillis).toString());
            printer.println((new StringBuilder()).append(s).append("serviceDetails: ").append(serviceDetails).toString());
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeLong(durationMillis);
            parcel.writeString(serviceDetails);
        }

        public long durationMillis;
        public String serviceDetails;

        public RunningServiceInfo()
        {
        }

        public RunningServiceInfo(Parcel parcel)
        {
            durationMillis = parcel.readLong();
            serviceDetails = parcel.readString();
        }
    }


    public ApplicationErrorReport()
    {
    }

    ApplicationErrorReport(Parcel parcel)
    {
        readFromParcel(parcel);
    }

    public static ComponentName getErrorReportReceiver(Context context, String s, int i)
    {
        PackageManager packagemanager;
        if(android.provider.Settings.Global.getInt(context.getContentResolver(), "send_action_app_error", 0) == 0)
            return null;
        packagemanager = context.getPackageManager();
        context = null;
        String s1 = packagemanager.getInstallerPackageName(s);
        context = s1;
_L2:
        if(context != null)
        {
            context = getErrorReportReceiver(packagemanager, s, ((String) (context)));
            if(context != null)
                return context;
        }
        break MISSING_BLOCK_LABEL_54;
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
        if(true) goto _L2; else goto _L1
_L1:
        if((i & 1) != 0)
        {
            context = getErrorReportReceiver(packagemanager, s, SystemProperties.get("ro.error.receiver.system.apps"));
            if(context != null)
                return context;
        }
        return getErrorReportReceiver(packagemanager, s, SystemProperties.get("ro.error.receiver.default"));
    }

    static ComponentName getErrorReportReceiver(PackageManager packagemanager, String s, String s1)
    {
        if(s1 == null || s1.length() == 0)
            return null;
        if(s1.equals(s))
            return null;
        s = new Intent("android.intent.action.APP_ERROR");
        s.setPackage(s1);
        packagemanager = packagemanager.resolveActivity(s, 0);
        if(packagemanager == null || ((ResolveInfo) (packagemanager)).activityInfo == null)
            return null;
        else
            return new ComponentName(s1, ((ResolveInfo) (packagemanager)).activityInfo.name);
    }

    public int describeContents()
    {
        return 0;
    }

    public void dump(Printer printer, String s)
    {
        printer.println((new StringBuilder()).append(s).append("type: ").append(type).toString());
        printer.println((new StringBuilder()).append(s).append("packageName: ").append(packageName).toString());
        printer.println((new StringBuilder()).append(s).append("installerPackageName: ").append(installerPackageName).toString());
        printer.println((new StringBuilder()).append(s).append("processName: ").append(processName).toString());
        printer.println((new StringBuilder()).append(s).append("time: ").append(time).toString());
        printer.println((new StringBuilder()).append(s).append("systemApp: ").append(systemApp).toString());
        type;
        JVM INSTR tableswitch 1 5: default 232
    //                   1 233
    //                   2 245
    //                   3 257
    //                   4 232
    //                   5 269;
           goto _L1 _L2 _L3 _L4 _L1 _L5
_L1:
        return;
_L2:
        crashInfo.dump(printer, s);
        continue; /* Loop/switch isn't completed */
_L3:
        anrInfo.dump(printer, s);
        continue; /* Loop/switch isn't completed */
_L4:
        batteryInfo.dump(printer, s);
        continue; /* Loop/switch isn't completed */
_L5:
        runningServiceInfo.dump(printer, s);
        if(true) goto _L1; else goto _L6
_L6:
    }

    public void readFromParcel(Parcel parcel)
    {
        boolean flag1;
        type = parcel.readInt();
        packageName = parcel.readString();
        installerPackageName = parcel.readString();
        processName = parcel.readString();
        time = parcel.readLong();
        boolean flag;
        if(parcel.readInt() == 1)
            flag = true;
        else
            flag = false;
        systemApp = flag;
        if(parcel.readInt() == 1)
            flag1 = true;
        else
            flag1 = false;
        type;
        JVM INSTR tableswitch 1 5: default 104
    //                   1 115
    //                   2 156
    //                   3 186
    //                   4 104
    //                   5 216;
           goto _L1 _L2 _L3 _L4 _L1 _L5
_L1:
        return;
_L2:
        if(flag1)
            parcel = new CrashInfo(parcel);
        else
            parcel = null;
        crashInfo = parcel;
        anrInfo = null;
        batteryInfo = null;
        runningServiceInfo = null;
        continue; /* Loop/switch isn't completed */
_L3:
        anrInfo = new AnrInfo(parcel);
        crashInfo = null;
        batteryInfo = null;
        runningServiceInfo = null;
        continue; /* Loop/switch isn't completed */
_L4:
        batteryInfo = new BatteryInfo(parcel);
        anrInfo = null;
        crashInfo = null;
        runningServiceInfo = null;
        continue; /* Loop/switch isn't completed */
_L5:
        batteryInfo = null;
        anrInfo = null;
        crashInfo = null;
        runningServiceInfo = new RunningServiceInfo(parcel);
        if(true) goto _L1; else goto _L6
_L6:
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(type);
        parcel.writeString(packageName);
        parcel.writeString(installerPackageName);
        parcel.writeString(processName);
        parcel.writeLong(time);
        int j;
        if(systemApp)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        if(crashInfo != null)
            j = ((flag) ? 1 : 0);
        else
            j = 0;
        parcel.writeInt(j);
        type;
        JVM INSTR tableswitch 1 5: default 112
    //                   1 125
    //                   2 144
    //                   3 156
    //                   4 112
    //                   5 168;
           goto _L1 _L2 _L3 _L4 _L1 _L5
_L1:
        return;
_L2:
        if(crashInfo != null)
            crashInfo.writeToParcel(parcel, i);
        continue; /* Loop/switch isn't completed */
_L3:
        anrInfo.writeToParcel(parcel, i);
        continue; /* Loop/switch isn't completed */
_L4:
        batteryInfo.writeToParcel(parcel, i);
        continue; /* Loop/switch isn't completed */
_L5:
        runningServiceInfo.writeToParcel(parcel, i);
        if(true) goto _L1; else goto _L6
_L6:
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ApplicationErrorReport createFromParcel(Parcel parcel)
        {
            return new ApplicationErrorReport(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ApplicationErrorReport[] newArray(int i)
        {
            return new ApplicationErrorReport[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    static final String DEFAULT_ERROR_RECEIVER_PROPERTY = "ro.error.receiver.default";
    static final String SYSTEM_APPS_ERROR_RECEIVER_PROPERTY = "ro.error.receiver.system.apps";
    public static final int TYPE_ANR = 2;
    public static final int TYPE_BATTERY = 3;
    public static final int TYPE_CRASH = 1;
    public static final int TYPE_NONE = 0;
    public static final int TYPE_RUNNING_SERVICE = 5;
    public AnrInfo anrInfo;
    public BatteryInfo batteryInfo;
    public CrashInfo crashInfo;
    public String installerPackageName;
    public String packageName;
    public String processName;
    public RunningServiceInfo runningServiceInfo;
    public boolean systemApp;
    public long time;
    public int type;

}
