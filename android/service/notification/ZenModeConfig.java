// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.notification;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.*;
import java.io.IOException;
import java.util.*;
import org.xmlpull.v1.*;

// Referenced classes of package android.service.notification:
//            Condition

public class ZenModeConfig
    implements Parcelable
{
    public static class Diff
    {

        static Diff _2D_wrap0(Diff diff1, String s, String s1)
        {
            return diff1.addLine(s, s1);
        }

        private Diff addLine(String s, String s1)
        {
            lines.add((new StringBuilder()).append(s).append(":").append(s1).toString());
            return this;
        }

        public Diff addLine(String s, Object obj, Object obj1)
        {
            return addLine(s, (new StringBuilder()).append(obj).append("->").append(obj1).toString());
        }

        public Diff addLine(String s, String s1, Object obj, Object obj1)
        {
            return addLine((new StringBuilder()).append(s).append(".").append(s1).toString(), obj, obj1);
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder("Diff[");
            int i = lines.size();
            for(int j = 0; j < i; j++)
            {
                if(j > 0)
                    stringbuilder.append(',');
                stringbuilder.append((String)lines.get(j));
            }

            return stringbuilder.append(']').toString();
        }

        private final ArrayList lines = new ArrayList();

        public Diff()
        {
        }
    }

    public static class EventInfo
    {

        public static int resolveUserId(int i)
        {
            int j = i;
            if(i == -10000)
                j = ActivityManager.getCurrentUser();
            return j;
        }

        public EventInfo copy()
        {
            EventInfo eventinfo = new EventInfo();
            eventinfo.userId = userId;
            eventinfo.calendar = calendar;
            eventinfo.reply = reply;
            return eventinfo;
        }

        public boolean equals(Object obj)
        {
            boolean flag = false;
            if(!(obj instanceof EventInfo))
                return false;
            obj = (EventInfo)obj;
            boolean flag1 = flag;
            if(userId == ((EventInfo) (obj)).userId)
            {
                flag1 = flag;
                if(Objects.equals(calendar, ((EventInfo) (obj)).calendar))
                {
                    flag1 = flag;
                    if(reply == ((EventInfo) (obj)).reply)
                        flag1 = true;
                }
            }
            return flag1;
        }

        public int hashCode()
        {
            return 0;
        }

        public static final int REPLY_ANY_EXCEPT_NO = 0;
        public static final int REPLY_YES = 2;
        public static final int REPLY_YES_OR_MAYBE = 1;
        public String calendar;
        public int reply;
        public int userId;

        public EventInfo()
        {
            userId = -10000;
        }
    }

    public static class ScheduleInfo
    {

        protected static String ts(long l)
        {
            return (new StringBuilder()).append(new Date(l)).append(" (").append(l).append(")").toString();
        }

        public ScheduleInfo copy()
        {
            ScheduleInfo scheduleinfo = new ScheduleInfo();
            if(days != null)
            {
                scheduleinfo.days = new int[days.length];
                System.arraycopy(days, 0, scheduleinfo.days, 0, days.length);
            }
            scheduleinfo.startHour = startHour;
            scheduleinfo.startMinute = startMinute;
            scheduleinfo.endHour = endHour;
            scheduleinfo.endMinute = endMinute;
            scheduleinfo.exitAtAlarm = exitAtAlarm;
            scheduleinfo.nextAlarm = nextAlarm;
            return scheduleinfo;
        }

        public boolean equals(Object obj)
        {
            boolean flag = false;
            if(!(obj instanceof ScheduleInfo))
                return false;
            obj = (ScheduleInfo)obj;
            boolean flag1 = flag;
            if(ZenModeConfig._2D_wrap0(days).equals(ZenModeConfig._2D_wrap0(((ScheduleInfo) (obj)).days)))
            {
                flag1 = flag;
                if(startHour == ((ScheduleInfo) (obj)).startHour)
                {
                    flag1 = flag;
                    if(startMinute == ((ScheduleInfo) (obj)).startMinute)
                    {
                        flag1 = flag;
                        if(endHour == ((ScheduleInfo) (obj)).endHour)
                        {
                            flag1 = flag;
                            if(endMinute == ((ScheduleInfo) (obj)).endMinute)
                            {
                                flag1 = flag;
                                if(exitAtAlarm == ((ScheduleInfo) (obj)).exitAtAlarm)
                                    flag1 = true;
                            }
                        }
                    }
                }
            }
            return flag1;
        }

        public int hashCode()
        {
            return 0;
        }

        public String toString()
        {
            return (new StringBuilder()).append("ScheduleInfo{days=").append(Arrays.toString(days)).append(", startHour=").append(startHour).append(", startMinute=").append(startMinute).append(", endHour=").append(endHour).append(", endMinute=").append(endMinute).append(", exitAtAlarm=").append(exitAtAlarm).append(", nextAlarm=").append(ts(nextAlarm)).append('}').toString();
        }

        public int days[];
        public int endHour;
        public int endMinute;
        public boolean exitAtAlarm;
        public long nextAlarm;
        public int startHour;
        public int startMinute;

        public ScheduleInfo()
        {
        }
    }

    public static class ZenRule
        implements Parcelable
    {

        static void _2D_wrap0(Diff diff1, String s, ZenRule zenrule, ZenRule zenrule1)
        {
            appendDiff(diff1, s, zenrule, zenrule1);
        }

        private void appendDiff(Diff diff1, String s, ZenRule zenrule)
        {
            if(zenrule == null)
            {
                Diff._2D_wrap0(diff1, s, "delete");
                return;
            }
            if(enabled != zenrule.enabled)
                diff1.addLine(s, "enabled", Boolean.valueOf(enabled), Boolean.valueOf(zenrule.enabled));
            if(snoozing != zenrule.snoozing)
                diff1.addLine(s, "snoozing", Boolean.valueOf(snoozing), Boolean.valueOf(zenrule.snoozing));
            if(!Objects.equals(name, zenrule.name))
                diff1.addLine(s, "name", name, zenrule.name);
            if(zenMode != zenrule.zenMode)
                diff1.addLine(s, "zenMode", Integer.valueOf(zenMode), Integer.valueOf(zenrule.zenMode));
            if(!Objects.equals(conditionId, zenrule.conditionId))
                diff1.addLine(s, "conditionId", conditionId, zenrule.conditionId);
            if(!Objects.equals(condition, zenrule.condition))
                diff1.addLine(s, "condition", condition, zenrule.condition);
            if(!Objects.equals(component, zenrule.component))
                diff1.addLine(s, "component", component, zenrule.component);
            if(!Objects.equals(id, zenrule.id))
                diff1.addLine(s, "id", id, zenrule.id);
            if(creationTime != zenrule.creationTime)
                diff1.addLine(s, "creationTime", Long.valueOf(creationTime), Long.valueOf(zenrule.creationTime));
            if(enabler != zenrule.enabler)
                diff1.addLine(s, "enabler", enabler, zenrule.enabler);
        }

        private static void appendDiff(Diff diff1, String s, ZenRule zenrule, ZenRule zenrule1)
        {
            if(diff1 == null)
                return;
            if(zenrule == null)
            {
                if(zenrule1 != null)
                    Diff._2D_wrap0(diff1, s, "insert");
                return;
            } else
            {
                zenrule.appendDiff(diff1, s, zenrule1);
                return;
            }
        }

        public int describeContents()
        {
            return 0;
        }

        public boolean equals(Object obj)
        {
            boolean flag = false;
            if(!(obj instanceof ZenRule))
                return false;
            if(obj == this)
                return true;
            obj = (ZenRule)obj;
            boolean flag1 = flag;
            if(((ZenRule) (obj)).enabled == enabled)
            {
                flag1 = flag;
                if(((ZenRule) (obj)).snoozing == snoozing)
                {
                    flag1 = flag;
                    if(Objects.equals(((ZenRule) (obj)).name, name))
                    {
                        flag1 = flag;
                        if(((ZenRule) (obj)).zenMode == zenMode)
                        {
                            flag1 = flag;
                            if(Objects.equals(((ZenRule) (obj)).conditionId, conditionId))
                            {
                                flag1 = flag;
                                if(Objects.equals(((ZenRule) (obj)).condition, condition))
                                {
                                    flag1 = flag;
                                    if(Objects.equals(((ZenRule) (obj)).component, component))
                                    {
                                        flag1 = flag;
                                        if(Objects.equals(((ZenRule) (obj)).id, id))
                                        {
                                            flag1 = flag;
                                            if(((ZenRule) (obj)).creationTime == creationTime)
                                                flag1 = Objects.equals(((ZenRule) (obj)).enabler, enabler);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return flag1;
        }

        public int hashCode()
        {
            return Objects.hash(new Object[] {
                Boolean.valueOf(enabled), Boolean.valueOf(snoozing), name, Integer.valueOf(zenMode), conditionId, condition, component, id, Long.valueOf(creationTime), enabler
            });
        }

        public boolean isAutomaticActive()
        {
            boolean flag;
            if(enabled && snoozing ^ true && component != null)
                flag = isTrueOrUnknown();
            else
                flag = false;
            return flag;
        }

        public boolean isTrueOrUnknown()
        {
            boolean flag = true;
            boolean flag1;
            if(condition != null)
            {
                flag1 = flag;
                if(condition.state != 1)
                    if(condition.state == 2)
                        flag1 = flag;
                    else
                        flag1 = false;
            } else
            {
                flag1 = false;
            }
            return flag1;
        }

        public String toString()
        {
            return (new StringBuilder(android/service/notification/ZenModeConfig$ZenRule.getSimpleName())).append('[').append("enabled=").append(enabled).append(",snoozing=").append(snoozing).append(",name=").append(name).append(",zenMode=").append(android.provider.Settings.Global.zenModeToString(zenMode)).append(",conditionId=").append(conditionId).append(",condition=").append(condition).append(",component=").append(component).append(",id=").append(id).append(",creationTime=").append(creationTime).append(",enabler=").append(enabler).append(']').toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            if(enabled)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(snoozing)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(name != null)
            {
                parcel.writeInt(1);
                parcel.writeString(name);
            } else
            {
                parcel.writeInt(0);
            }
            parcel.writeInt(zenMode);
            parcel.writeParcelable(conditionId, 0);
            parcel.writeParcelable(condition, 0);
            parcel.writeParcelable(component, 0);
            if(id != null)
            {
                parcel.writeInt(1);
                parcel.writeString(id);
            } else
            {
                parcel.writeInt(0);
            }
            parcel.writeLong(creationTime);
            if(enabler != null)
            {
                parcel.writeInt(1);
                parcel.writeString(enabler);
            } else
            {
                parcel.writeInt(0);
            }
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public ZenRule createFromParcel(Parcel parcel)
            {
                return new ZenRule(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public ZenRule[] newArray(int i)
            {
                return new ZenRule[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public ComponentName component;
        public Condition condition;
        public Uri conditionId;
        public long creationTime;
        public boolean enabled;
        public String enabler;
        public String id;
        public String name;
        public boolean snoozing;
        public int zenMode;


        public ZenRule()
        {
        }

        public ZenRule(Parcel parcel)
        {
            boolean flag = false;
            super();
            boolean flag1;
            if(parcel.readInt() == 1)
                flag1 = true;
            else
                flag1 = false;
            enabled = flag1;
            flag1 = flag;
            if(parcel.readInt() == 1)
                flag1 = true;
            snoozing = flag1;
            if(parcel.readInt() == 1)
                name = parcel.readString();
            zenMode = parcel.readInt();
            conditionId = (Uri)parcel.readParcelable(null);
            condition = (Condition)parcel.readParcelable(null);
            component = (ComponentName)parcel.readParcelable(null);
            if(parcel.readInt() == 1)
                id = parcel.readString();
            creationTime = parcel.readLong();
            if(parcel.readInt() == 1)
                enabler = parcel.readString();
        }
    }


    static String _2D_wrap0(int ai[])
    {
        return toDayList(ai);
    }

    public ZenModeConfig()
    {
        allowCalls = true;
        allowRepeatCallers = false;
        allowMessages = false;
        allowReminders = true;
        allowEvents = true;
        allowCallsFrom = 1;
        allowMessagesFrom = 1;
        user = 0;
        allowWhenScreenOff = true;
        allowWhenScreenOn = true;
        automaticRules = new ArrayMap();
    }

    public ZenModeConfig(Parcel parcel)
    {
        boolean flag = true;
        super();
        allowCalls = true;
        allowRepeatCallers = false;
        allowMessages = false;
        allowReminders = true;
        allowEvents = true;
        allowCallsFrom = 1;
        allowMessagesFrom = 1;
        user = 0;
        allowWhenScreenOff = true;
        allowWhenScreenOn = true;
        automaticRules = new ArrayMap();
        boolean flag1;
        int i;
        if(parcel.readInt() == 1)
            flag1 = true;
        else
            flag1 = false;
        allowCalls = flag1;
        if(parcel.readInt() == 1)
            flag1 = true;
        else
            flag1 = false;
        allowRepeatCallers = flag1;
        if(parcel.readInt() == 1)
            flag1 = true;
        else
            flag1 = false;
        allowMessages = flag1;
        if(parcel.readInt() == 1)
            flag1 = true;
        else
            flag1 = false;
        allowReminders = flag1;
        if(parcel.readInt() == 1)
            flag1 = true;
        else
            flag1 = false;
        allowEvents = flag1;
        allowCallsFrom = parcel.readInt();
        allowMessagesFrom = parcel.readInt();
        user = parcel.readInt();
        manualRule = (ZenRule)parcel.readParcelable(null);
        i = parcel.readInt();
        if(i > 0)
        {
            String as[] = new String[i];
            ZenRule azenrule[] = new ZenRule[i];
            parcel.readStringArray(as);
            parcel.readTypedArray(azenrule, ZenRule.CREATOR);
            for(int j = 0; j < i; j++)
                automaticRules.put(as[j], azenrule[j]);

        }
        if(parcel.readInt() == 1)
            flag1 = true;
        else
            flag1 = false;
        allowWhenScreenOff = flag1;
        if(parcel.readInt() == 1)
            flag1 = flag;
        else
            flag1 = false;
        allowWhenScreenOn = flag1;
    }

    private static void addKeys(ArraySet arrayset, ArrayMap arraymap)
    {
        if(arraymap != null)
        {
            for(int i = 0; i < arraymap.size(); i++)
                arrayset.add(arraymap.keyAt(i));

        }
    }

    private Diff diff(ZenModeConfig zenmodeconfig)
    {
        Diff diff1 = new Diff();
        if(zenmodeconfig == null)
            return Diff._2D_wrap0(diff1, "config", "delete");
        if(user != zenmodeconfig.user)
            diff1.addLine("user", Integer.valueOf(user), Integer.valueOf(zenmodeconfig.user));
        if(allowCalls != zenmodeconfig.allowCalls)
            diff1.addLine("allowCalls", Boolean.valueOf(allowCalls), Boolean.valueOf(zenmodeconfig.allowCalls));
        if(allowRepeatCallers != zenmodeconfig.allowRepeatCallers)
            diff1.addLine("allowRepeatCallers", Boolean.valueOf(allowRepeatCallers), Boolean.valueOf(zenmodeconfig.allowRepeatCallers));
        if(allowMessages != zenmodeconfig.allowMessages)
            diff1.addLine("allowMessages", Boolean.valueOf(allowMessages), Boolean.valueOf(zenmodeconfig.allowMessages));
        if(allowCallsFrom != zenmodeconfig.allowCallsFrom)
            diff1.addLine("allowCallsFrom", Integer.valueOf(allowCallsFrom), Integer.valueOf(zenmodeconfig.allowCallsFrom));
        if(allowMessagesFrom != zenmodeconfig.allowMessagesFrom)
            diff1.addLine("allowMessagesFrom", Integer.valueOf(allowMessagesFrom), Integer.valueOf(zenmodeconfig.allowMessagesFrom));
        if(allowReminders != zenmodeconfig.allowReminders)
            diff1.addLine("allowReminders", Boolean.valueOf(allowReminders), Boolean.valueOf(zenmodeconfig.allowReminders));
        if(allowEvents != zenmodeconfig.allowEvents)
            diff1.addLine("allowEvents", Boolean.valueOf(allowEvents), Boolean.valueOf(zenmodeconfig.allowEvents));
        if(allowWhenScreenOff != zenmodeconfig.allowWhenScreenOff)
            diff1.addLine("allowWhenScreenOff", Boolean.valueOf(allowWhenScreenOff), Boolean.valueOf(zenmodeconfig.allowWhenScreenOff));
        if(allowWhenScreenOn != zenmodeconfig.allowWhenScreenOn)
            diff1.addLine("allowWhenScreenOn", Boolean.valueOf(allowWhenScreenOn), Boolean.valueOf(zenmodeconfig.allowWhenScreenOn));
        ArraySet arrayset = new ArraySet();
        addKeys(arrayset, automaticRules);
        addKeys(arrayset, zenmodeconfig.automaticRules);
        int i = arrayset.size();
        int j = 0;
        while(j < i) 
        {
            String s = (String)arrayset.valueAt(j);
            ZenRule zenrule;
            ZenRule zenrule1;
            if(automaticRules != null)
                zenrule = (ZenRule)automaticRules.get(s);
            else
                zenrule = null;
            if(zenmodeconfig.automaticRules != null)
                zenrule1 = (ZenRule)zenmodeconfig.automaticRules.get(s);
            else
                zenrule1 = null;
            ZenRule._2D_wrap0(diff1, (new StringBuilder()).append("automaticRule[").append(s).append("]").toString(), zenrule, zenrule1);
            j++;
        }
        ZenRule._2D_wrap0(diff1, "manualRule", manualRule, zenmodeconfig.manualRule);
        return diff1;
    }

    public static Diff diff(ZenModeConfig zenmodeconfig, ZenModeConfig zenmodeconfig1)
    {
        if(zenmodeconfig == null)
        {
            zenmodeconfig = new Diff();
            if(zenmodeconfig1 != null)
                Diff._2D_wrap0(zenmodeconfig, "config", "insert");
            return zenmodeconfig;
        } else
        {
            return zenmodeconfig.diff(zenmodeconfig1);
        }
    }

    private static int[] generateMinuteBuckets()
    {
        int ai[] = new int[15];
        ai[0] = 15;
        ai[1] = 30;
        ai[2] = 45;
        for(int i = 1; i <= 12; i++)
            ai[i + 2] = i * 60;

        return ai;
    }

    private static String getConditionLine(Context context, ZenModeConfig zenmodeconfig, int i, boolean flag, boolean flag1)
    {
        if(zenmodeconfig == null)
            return "";
        Object obj = "";
        if(zenmodeconfig.manualRule != null)
        {
            obj = zenmodeconfig.manualRule.conditionId;
            if(zenmodeconfig.manualRule.enabler != null)
                obj = getOwnerCaption(context, zenmodeconfig.manualRule.enabler);
            else
            if(obj == null)
            {
                obj = context.getString(0x10406e8);
            } else
            {
                long l = tryParseCountdownConditionId(((Uri) (obj)));
                obj = zenmodeconfig.manualRule.condition;
                if(l > 0L)
                    obj = toTimeCondition(context, l, Math.round((float)(l - System.currentTimeMillis()) / 60000F), i, flag1);
                if(obj == null)
                    obj = "";
                else
                if(flag)
                    obj = ((Condition) (obj)).line1;
                else
                    obj = ((Condition) (obj)).summary;
                if(TextUtils.isEmpty(((CharSequence) (obj))))
                    obj = "";
            }
        }
        zenmodeconfig = zenmodeconfig.automaticRules.values().iterator();
        do
        {
            if(!zenmodeconfig.hasNext())
                break;
            ZenRule zenrule = (ZenRule)zenmodeconfig.next();
            if(zenrule.isAutomaticActive())
                if(((String) (obj)).isEmpty())
                    obj = zenrule.name;
                else
                    obj = context.getResources().getString(0x10406ea, new Object[] {
                        obj, zenrule.name
                    });
        } while(true);
        return ((String) (obj));
    }

    public static String getConditionSummary(Context context, ZenModeConfig zenmodeconfig, int i, boolean flag)
    {
        return getConditionLine(context, zenmodeconfig, i, false, flag);
    }

    public static ComponentName getEventConditionProvider()
    {
        return new ComponentName("android", "EventConditionProvider");
    }

    private static CharSequence getFormattedTime(Context context, long l, boolean flag, int i)
    {
        StringBuilder stringbuilder = new StringBuilder();
        Object obj;
        if(!flag)
            obj = "EEE ";
        else
            obj = "";
        obj = stringbuilder.append(((String) (obj)));
        if(DateFormat.is24HourFormat(context, i))
            context = "Hm";
        else
            context = "hma";
        context = ((StringBuilder) (obj)).append(context).toString();
        return DateFormat.format(DateFormat.getBestDateTimePattern(Locale.getDefault(), context), l);
    }

    private static String getOwnerCaption(Context context, String s)
    {
        context = context.getPackageManager();
        s = context.getApplicationInfo(s, 0);
        if(s == null)
            break MISSING_BLOCK_LABEL_59;
        context = s.loadLabel(context);
        if(context == null)
            break MISSING_BLOCK_LABEL_59;
        int i;
        context = context.toString().trim();
        i = context.length();
        if(i > 0)
            return context;
        break MISSING_BLOCK_LABEL_59;
        context;
        Slog.w(TAG, "Error loading owner caption", context);
        return "";
    }

    public static ComponentName getScheduleConditionProvider()
    {
        return new ComponentName("android", "ScheduleConditionProvider");
    }

    private static boolean isToday(long l)
    {
        GregorianCalendar gregoriancalendar = new GregorianCalendar();
        GregorianCalendar gregoriancalendar1 = new GregorianCalendar();
        gregoriancalendar1.setTimeInMillis(l);
        return gregoriancalendar.get(1) == gregoriancalendar1.get(1) && gregoriancalendar.get(2) == gregoriancalendar1.get(2) && gregoriancalendar.get(5) == gregoriancalendar1.get(5);
    }

    private static boolean isValidAutomaticRule(ZenRule zenrule)
    {
        boolean flag;
        if(zenrule != null && TextUtils.isEmpty(zenrule.name) ^ true && android.provider.Settings.Global.isValidZenMode(zenrule.zenMode) && zenrule.conditionId != null)
            flag = sameCondition(zenrule);
        else
            flag = false;
        return flag;
    }

    public static boolean isValidCountdownConditionId(Uri uri)
    {
        boolean flag;
        if(tryParseCountdownConditionId(uri) != 0L)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean isValidCountdownToAlarmConditionId(Uri uri)
    {
        if(tryParseCountdownConditionId(uri) != 0L)
        {
            if(uri.getPathSegments().size() < 4 || "alarm".equals(uri.getPathSegments().get(2)) ^ true)
                return false;
            boolean flag;
            try
            {
                flag = Boolean.parseBoolean((String)uri.getPathSegments().get(3));
            }
            catch(RuntimeException runtimeexception)
            {
                Slog.w(TAG, (new StringBuilder()).append("Error parsing countdown alarm condition: ").append(uri).toString(), runtimeexception);
                return false;
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public static boolean isValidEventConditionId(Uri uri)
    {
        boolean flag;
        if(tryParseEventConditionId(uri) != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean isValidHour(int i)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(i >= 0)
        {
            flag1 = flag;
            if(i < 24)
                flag1 = true;
        }
        return flag1;
    }

    private static boolean isValidManualRule(ZenRule zenrule)
    {
        boolean flag;
        if(zenrule != null)
        {
            if(android.provider.Settings.Global.isValidZenMode(zenrule.zenMode))
                flag = sameCondition(zenrule);
            else
                flag = false;
        } else
        {
            flag = true;
        }
        return flag;
    }

    public static boolean isValidMinute(int i)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(i >= 0)
        {
            flag1 = flag;
            if(i < 60)
                flag1 = true;
        }
        return flag1;
    }

    public static boolean isValidScheduleConditionId(Uri uri)
    {
        boolean flag;
        if(tryParseScheduleConditionId(uri) != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static boolean isValidSource(int i)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(i >= 0)
        {
            flag1 = flag;
            if(i <= 2)
                flag1 = true;
        }
        return flag1;
    }

    public static String newRuleId()
    {
        return UUID.randomUUID().toString().replace("-", "");
    }

    private static int prioritySendersToSource(int i, int j)
    {
        switch(i)
        {
        default:
            return j;

        case 1: // '\001'
            return 1;

        case 2: // '\002'
            return 2;

        case 0: // '\0'
            return 0;
        }
    }

    public static Condition readConditionXml(XmlPullParser xmlpullparser)
    {
        Uri uri = safeUri(xmlpullparser, "id");
        if(uri == null)
            return null;
        String s = xmlpullparser.getAttributeValue(null, "summary");
        String s1 = xmlpullparser.getAttributeValue(null, "line1");
        String s2 = xmlpullparser.getAttributeValue(null, "line2");
        int i = safeInt(xmlpullparser, "icon", -1);
        int j = safeInt(xmlpullparser, "state", -1);
        int k = safeInt(xmlpullparser, "flags", -1);
        try
        {
            xmlpullparser = new Condition(uri, s, s1, s2, i, j, k);
        }
        // Misplaced declaration of an exception variable
        catch(XmlPullParser xmlpullparser)
        {
            Slog.w(TAG, "Unable to read condition xml", xmlpullparser);
            return null;
        }
        return xmlpullparser;
    }

    public static ZenRule readRuleXml(XmlPullParser xmlpullparser)
    {
        ZenRule zenrule = new ZenRule();
        zenrule.enabled = safeBoolean(xmlpullparser, "enabled", true);
        zenrule.snoozing = safeBoolean(xmlpullparser, "snoozing", false);
        zenrule.name = xmlpullparser.getAttributeValue(null, "name");
        String s = xmlpullparser.getAttributeValue(null, "zen");
        zenrule.zenMode = tryParseZenMode(s, -1);
        if(zenrule.zenMode == -1)
        {
            Slog.w(TAG, (new StringBuilder()).append("Bad zen mode in rule xml:").append(s).toString());
            return null;
        } else
        {
            zenrule.conditionId = safeUri(xmlpullparser, "conditionId");
            zenrule.component = safeComponentName(xmlpullparser, "component");
            zenrule.creationTime = safeLong(xmlpullparser, "creationTime", 0L);
            zenrule.enabler = xmlpullparser.getAttributeValue(null, "enabler");
            zenrule.condition = readConditionXml(xmlpullparser);
            return zenrule;
        }
    }

    public static ZenModeConfig readXml(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        if(xmlpullparser.getEventType() != 2)
            return null;
        if(!"zen".equals(xmlpullparser.getName()))
            return null;
        ZenModeConfig zenmodeconfig = new ZenModeConfig();
        zenmodeconfig.user = safeInt(xmlpullparser, "user", zenmodeconfig.user);
        do
        {
            int i = xmlpullparser.next();
            if(i == 1)
                break;
            String s = xmlpullparser.getName();
            if(i == 3 && "zen".equals(s))
                return zenmodeconfig;
            if(i == 2)
                if("allow".equals(s))
                {
                    zenmodeconfig.allowCalls = safeBoolean(xmlpullparser, "calls", false);
                    zenmodeconfig.allowRepeatCallers = safeBoolean(xmlpullparser, "repeatCallers", false);
                    zenmodeconfig.allowMessages = safeBoolean(xmlpullparser, "messages", false);
                    zenmodeconfig.allowReminders = safeBoolean(xmlpullparser, "reminders", true);
                    zenmodeconfig.allowEvents = safeBoolean(xmlpullparser, "events", true);
                    int k = safeInt(xmlpullparser, "from", -1);
                    int j = safeInt(xmlpullparser, "callsFrom", -1);
                    int l = safeInt(xmlpullparser, "messagesFrom", -1);
                    if(isValidSource(j) && isValidSource(l))
                    {
                        zenmodeconfig.allowCallsFrom = j;
                        zenmodeconfig.allowMessagesFrom = l;
                    } else
                    if(isValidSource(k))
                    {
                        Slog.i(TAG, (new StringBuilder()).append("Migrating existing shared 'from': ").append(sourceToString(k)).toString());
                        zenmodeconfig.allowCallsFrom = k;
                        zenmodeconfig.allowMessagesFrom = k;
                    } else
                    {
                        zenmodeconfig.allowCallsFrom = 1;
                        zenmodeconfig.allowMessagesFrom = 1;
                    }
                    zenmodeconfig.allowWhenScreenOff = safeBoolean(xmlpullparser, "visualScreenOff", true);
                    zenmodeconfig.allowWhenScreenOn = safeBoolean(xmlpullparser, "visualScreenOn", true);
                } else
                if("manual".equals(s))
                    zenmodeconfig.manualRule = readRuleXml(xmlpullparser);
                else
                if("automatic".equals(s))
                {
                    String s1 = xmlpullparser.getAttributeValue(null, "ruleId");
                    ZenRule zenrule = readRuleXml(xmlpullparser);
                    if(s1 != null && zenrule != null)
                    {
                        zenrule.id = s1;
                        zenmodeconfig.automaticRules.put(s1, zenrule);
                    }
                }
        } while(true);
        throw new IllegalStateException("Failed to reach END_DOCUMENT");
    }

    private static boolean safeBoolean(String s, boolean flag)
    {
        if(TextUtils.isEmpty(s))
            return flag;
        else
            return Boolean.parseBoolean(s);
    }

    private static boolean safeBoolean(XmlPullParser xmlpullparser, String s, boolean flag)
    {
        return safeBoolean(xmlpullparser.getAttributeValue(null, s), flag);
    }

    private static ComponentName safeComponentName(XmlPullParser xmlpullparser, String s)
    {
        xmlpullparser = xmlpullparser.getAttributeValue(null, s);
        if(TextUtils.isEmpty(xmlpullparser))
            return null;
        else
            return ComponentName.unflattenFromString(xmlpullparser);
    }

    private static int safeInt(XmlPullParser xmlpullparser, String s, int i)
    {
        return tryParseInt(xmlpullparser.getAttributeValue(null, s), i);
    }

    private static long safeLong(XmlPullParser xmlpullparser, String s, long l)
    {
        return tryParseLong(xmlpullparser.getAttributeValue(null, s), l);
    }

    private static Uri safeUri(XmlPullParser xmlpullparser, String s)
    {
        xmlpullparser = xmlpullparser.getAttributeValue(null, s);
        if(TextUtils.isEmpty(xmlpullparser))
            return null;
        else
            return Uri.parse(xmlpullparser);
    }

    private static boolean sameCondition(ZenRule zenrule)
    {
        boolean flag = true;
        boolean flag1 = true;
        if(zenrule == null)
            return false;
        if(zenrule.conditionId == null)
        {
            if(zenrule.condition != null)
                flag1 = false;
            return flag1;
        }
        flag1 = flag;
        if(zenrule.condition != null)
            flag1 = zenrule.conditionId.equals(zenrule.condition.id);
        return flag1;
    }

    private static int sourceToPrioritySenders(int i, int j)
    {
        switch(i)
        {
        default:
            return j;

        case 0: // '\0'
            return 0;

        case 1: // '\001'
            return 1;

        case 2: // '\002'
            return 2;
        }
    }

    public static String sourceToString(int i)
    {
        switch(i)
        {
        default:
            return "UNKNOWN";

        case 0: // '\0'
            return "anyone";

        case 1: // '\001'
            return "contacts";

        case 2: // '\002'
            return "stars";
        }
    }

    public static Uri toCountdownConditionId(long l, boolean flag)
    {
        return (new android.net.Uri.Builder()).scheme("condition").authority("android").appendPath("countdown").appendPath(Long.toString(l)).appendPath("alarm").appendPath(Boolean.toString(flag)).build();
    }

    private static String toDayList(int ai[])
    {
        if(ai == null || ai.length == 0)
            return "";
        StringBuilder stringbuilder = new StringBuilder();
        for(int i = 0; i < ai.length; i++)
        {
            if(i > 0)
                stringbuilder.append('.');
            stringbuilder.append(ai[i]);
        }

        return stringbuilder.toString();
    }

    public static Uri toEventConditionId(EventInfo eventinfo)
    {
        android.net.Uri.Builder builder = (new android.net.Uri.Builder()).scheme("condition").authority("android").appendPath("event").appendQueryParameter("userId", Long.toString(eventinfo.userId));
        String s;
        if(eventinfo.calendar != null)
            s = eventinfo.calendar;
        else
            s = "";
        return builder.appendQueryParameter("calendar", s).appendQueryParameter("reply", Integer.toString(eventinfo.reply)).build();
    }

    public static Condition toNextAlarmCondition(Context context, long l, int i)
    {
        CharSequence charsequence = getFormattedTime(context, l, isToday(l), i);
        context = context.getResources().getString(0x10406eb, new Object[] {
            charsequence
        });
        return new Condition(toCountdownConditionId(l, true), "", context, "", 0, 1, 1);
    }

    public static Uri toScheduleConditionId(ScheduleInfo scheduleinfo)
    {
        return (new android.net.Uri.Builder()).scheme("condition").authority("android").appendPath("schedule").appendQueryParameter("days", toDayList(scheduleinfo.days)).appendQueryParameter("start", (new StringBuilder()).append(scheduleinfo.startHour).append(".").append(scheduleinfo.startMinute).toString()).appendQueryParameter("end", (new StringBuilder()).append(scheduleinfo.endHour).append(".").append(scheduleinfo.endMinute).toString()).appendQueryParameter("exitAtAlarm", String.valueOf(scheduleinfo.exitAtAlarm)).build();
    }

    public static Condition toTimeCondition(Context context, int i, int j)
    {
        return toTimeCondition(context, i, j, false);
    }

    public static Condition toTimeCondition(Context context, int i, int j, boolean flag)
    {
        long l = System.currentTimeMillis();
        int k;
        if(i == 0)
            k = 10000;
        else
            k = 60000 * i;
        return toTimeCondition(context, l + (long)k, i, j, flag);
    }

    public static Condition toTimeCondition(Context context, long l, int i, int j, boolean flag)
    {
        Object obj = getFormattedTime(context, l, isToday(l), j);
        Resources resources = context.getResources();
        Object obj1;
        if(i < 60)
        {
            if(flag)
                j = 0x1150024;
            else
                j = 0x1150023;
            context = resources.getQuantityString(j, i, new Object[] {
                Integer.valueOf(i), obj
            });
            if(flag)
                j = 0x1150022;
            else
                j = 0x1150021;
            obj1 = resources.getQuantityString(j, i, new Object[] {
                Integer.valueOf(i), obj
            });
            obj = resources.getString(0x10406eb, new Object[] {
                obj
            });
        } else
        if(i < 1440)
        {
            j = Math.round((float)i / 60F);
            if(flag)
                i = 0x1150020;
            else
                i = 0x115001f;
            context = resources.getQuantityString(i, j, new Object[] {
                Integer.valueOf(j), obj
            });
            if(flag)
                i = 0x115001e;
            else
                i = 0x115001d;
            obj1 = resources.getQuantityString(i, j, new Object[] {
                Integer.valueOf(j), obj
            });
            obj = resources.getString(0x10406eb, new Object[] {
                obj
            });
        } else
        {
            obj = resources.getString(0x10406eb, new Object[] {
                obj
            });
            obj1 = obj;
            context = ((Context) (obj));
        }
        return new Condition(toCountdownConditionId(l, false), context, ((String) (obj1)), ((String) (obj)), 0, 1, 1);
    }

    public static long tryParseCountdownConditionId(Uri uri)
    {
        if(!Condition.isValidId(uri, "android"))
            return 0L;
        if(uri.getPathSegments().size() < 2 || "countdown".equals(uri.getPathSegments().get(0)) ^ true)
            return 0L;
        long l;
        try
        {
            l = Long.parseLong((String)uri.getPathSegments().get(1));
        }
        catch(RuntimeException runtimeexception)
        {
            Slog.w(TAG, (new StringBuilder()).append("Error parsing countdown condition: ").append(uri).toString(), runtimeexception);
            return 0L;
        }
        return l;
    }

    private static int[] tryParseDayList(String s, String s1)
    {
        if(s == null)
            return null;
        s = s.split(s1);
        if(s.length == 0)
            return null;
        s1 = new int[s.length];
        for(int i = 0; i < s.length; i++)
        {
            int j = tryParseInt(s[i], -1);
            if(j == -1)
                return null;
            s1[i] = j;
        }

        return s1;
    }

    public static EventInfo tryParseEventConditionId(Uri uri)
    {
        boolean flag;
        if(uri != null && uri.getScheme().equals("condition") && uri.getAuthority().equals("android") && uri.getPathSegments().size() == 1)
            flag = ((String)uri.getPathSegments().get(0)).equals("event");
        else
            flag = false;
        if(!flag)
            return null;
        EventInfo eventinfo = new EventInfo();
        eventinfo.userId = tryParseInt(uri.getQueryParameter("userId"), -10000);
        eventinfo.calendar = uri.getQueryParameter("calendar");
        if(TextUtils.isEmpty(eventinfo.calendar) || tryParseLong(eventinfo.calendar, -1L) != -1L)
            eventinfo.calendar = null;
        eventinfo.reply = tryParseInt(uri.getQueryParameter("reply"), 0);
        return eventinfo;
    }

    private static int[] tryParseHourAndMinute(String s)
    {
        Object obj = null;
        if(TextUtils.isEmpty(s))
            return null;
        int i = s.indexOf('.');
        if(i < 1 || i >= s.length() - 1)
            return null;
        int j = tryParseInt(s.substring(0, i), -1);
        i = tryParseInt(s.substring(i + 1), -1);
        s = obj;
        if(isValidHour(j))
        {
            s = obj;
            if(isValidMinute(i))
            {
                s = new int[2];
                s[0] = j;
                s[1] = i;
            }
        }
        return s;
    }

    private static int tryParseInt(String s, int i)
    {
        if(TextUtils.isEmpty(s))
            return i;
        int j;
        try
        {
            j = Integer.parseInt(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return i;
        }
        return j;
    }

    private static long tryParseLong(String s, long l)
    {
        if(TextUtils.isEmpty(s))
            return l;
        long l1;
        try
        {
            l1 = Long.parseLong(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return l;
        }
        return l1;
    }

    public static ScheduleInfo tryParseScheduleConditionId(Uri uri)
    {
        boolean flag;
        if(uri != null && uri.getScheme().equals("condition") && uri.getAuthority().equals("android") && uri.getPathSegments().size() == 1)
            flag = ((String)uri.getPathSegments().get(0)).equals("schedule");
        else
            flag = false;
        if(!flag)
            return null;
        int ai[] = tryParseHourAndMinute(uri.getQueryParameter("start"));
        int ai1[] = tryParseHourAndMinute(uri.getQueryParameter("end"));
        if(ai == null || ai1 == null)
        {
            return null;
        } else
        {
            ScheduleInfo scheduleinfo = new ScheduleInfo();
            scheduleinfo.days = tryParseDayList(uri.getQueryParameter("days"), "\\.");
            scheduleinfo.startHour = ai[0];
            scheduleinfo.startMinute = ai[1];
            scheduleinfo.endHour = ai1[0];
            scheduleinfo.endMinute = ai1[1];
            scheduleinfo.exitAtAlarm = safeBoolean(uri.getQueryParameter("exitAtAlarm"), false);
            return scheduleinfo;
        }
    }

    private static int tryParseZenMode(String s, int i)
    {
        int j = tryParseInt(s, i);
        if(android.provider.Settings.Global.isValidZenMode(j))
            i = j;
        return i;
    }

    public static void writeConditionXml(Condition condition, XmlSerializer xmlserializer)
        throws IOException
    {
        xmlserializer.attribute(null, "id", condition.id.toString());
        xmlserializer.attribute(null, "summary", condition.summary);
        xmlserializer.attribute(null, "line1", condition.line1);
        xmlserializer.attribute(null, "line2", condition.line2);
        xmlserializer.attribute(null, "icon", Integer.toString(condition.icon));
        xmlserializer.attribute(null, "state", Integer.toString(condition.state));
        xmlserializer.attribute(null, "flags", Integer.toString(condition.flags));
    }

    public static void writeRuleXml(ZenRule zenrule, XmlSerializer xmlserializer)
        throws IOException
    {
        xmlserializer.attribute(null, "enabled", Boolean.toString(zenrule.enabled));
        xmlserializer.attribute(null, "snoozing", Boolean.toString(zenrule.snoozing));
        if(zenrule.name != null)
            xmlserializer.attribute(null, "name", zenrule.name);
        xmlserializer.attribute(null, "zen", Integer.toString(zenrule.zenMode));
        if(zenrule.component != null)
            xmlserializer.attribute(null, "component", zenrule.component.flattenToString());
        if(zenrule.conditionId != null)
            xmlserializer.attribute(null, "conditionId", zenrule.conditionId.toString());
        xmlserializer.attribute(null, "creationTime", Long.toString(zenrule.creationTime));
        if(zenrule.enabler != null)
            xmlserializer.attribute(null, "enabler", zenrule.enabler);
        if(zenrule.condition != null)
            writeConditionXml(zenrule.condition, xmlserializer);
    }

    public void applyNotificationPolicy(android.app.NotificationManager.Policy policy)
    {
        boolean flag = true;
        if(policy == null)
            return;
        boolean flag1;
        if((policy.priorityCategories & 8) != 0)
            flag1 = true;
        else
            flag1 = false;
        allowCalls = flag1;
        if((policy.priorityCategories & 4) != 0)
            flag1 = true;
        else
            flag1 = false;
        allowMessages = flag1;
        if((policy.priorityCategories & 2) != 0)
            flag1 = true;
        else
            flag1 = false;
        allowEvents = flag1;
        if((policy.priorityCategories & 1) != 0)
            flag1 = true;
        else
            flag1 = false;
        allowReminders = flag1;
        if((policy.priorityCategories & 0x10) != 0)
            flag1 = true;
        else
            flag1 = false;
        allowRepeatCallers = flag1;
        allowCallsFrom = prioritySendersToSource(policy.priorityCallSenders, allowCallsFrom);
        allowMessagesFrom = prioritySendersToSource(policy.priorityMessageSenders, allowMessagesFrom);
        if(policy.suppressedVisualEffects != -1)
        {
            if((policy.suppressedVisualEffects & 1) == 0)
                flag1 = true;
            else
                flag1 = false;
            allowWhenScreenOff = flag1;
            if((policy.suppressedVisualEffects & 2) == 0)
                flag1 = flag;
            else
                flag1 = false;
            allowWhenScreenOn = flag1;
        }
    }

    public ZenModeConfig copy()
    {
        Parcel parcel = Parcel.obtain();
        ZenModeConfig zenmodeconfig;
        writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        zenmodeconfig = new ZenModeConfig(parcel);
        parcel.recycle();
        return zenmodeconfig;
        Exception exception;
        exception;
        parcel.recycle();
        throw exception;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(!(obj instanceof ZenModeConfig))
            return false;
        if(obj == this)
            return true;
        obj = (ZenModeConfig)obj;
        boolean flag1 = flag;
        if(((ZenModeConfig) (obj)).allowCalls == allowCalls)
        {
            flag1 = flag;
            if(((ZenModeConfig) (obj)).allowRepeatCallers == allowRepeatCallers)
            {
                flag1 = flag;
                if(((ZenModeConfig) (obj)).allowMessages == allowMessages)
                {
                    flag1 = flag;
                    if(((ZenModeConfig) (obj)).allowCallsFrom == allowCallsFrom)
                    {
                        flag1 = flag;
                        if(((ZenModeConfig) (obj)).allowMessagesFrom == allowMessagesFrom)
                        {
                            flag1 = flag;
                            if(((ZenModeConfig) (obj)).allowReminders == allowReminders)
                            {
                                flag1 = flag;
                                if(((ZenModeConfig) (obj)).allowEvents == allowEvents)
                                {
                                    flag1 = flag;
                                    if(((ZenModeConfig) (obj)).allowWhenScreenOff == allowWhenScreenOff)
                                    {
                                        flag1 = flag;
                                        if(((ZenModeConfig) (obj)).allowWhenScreenOn == allowWhenScreenOn)
                                        {
                                            flag1 = flag;
                                            if(((ZenModeConfig) (obj)).user == user)
                                            {
                                                flag1 = flag;
                                                if(Objects.equals(((ZenModeConfig) (obj)).automaticRules, automaticRules))
                                                    flag1 = Objects.equals(((ZenModeConfig) (obj)).manualRule, manualRule);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return flag1;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            Boolean.valueOf(allowCalls), Boolean.valueOf(allowRepeatCallers), Boolean.valueOf(allowMessages), Integer.valueOf(allowCallsFrom), Integer.valueOf(allowMessagesFrom), Boolean.valueOf(allowReminders), Boolean.valueOf(allowEvents), Boolean.valueOf(allowWhenScreenOff), Boolean.valueOf(allowWhenScreenOn), Integer.valueOf(user), 
            automaticRules, manualRule
        });
    }

    public boolean isValid()
    {
        if(!isValidManualRule(manualRule))
            return false;
        int i = automaticRules.size();
        for(int j = 0; j < i; j++)
            if(!isValidAutomaticRule((ZenRule)automaticRules.valueAt(j)))
                return false;

        return true;
    }

    public android.app.NotificationManager.Policy toNotificationPolicy()
    {
        int i = 0;
        if(allowCalls)
            i = 8;
        int j = i;
        if(allowMessages)
            j = i | 4;
        i = j;
        if(allowEvents)
            i = j | 2;
        j = i;
        if(allowReminders)
            j = i | 1;
        i = j;
        if(allowRepeatCallers)
            i = j | 0x10;
        j = 0;
        if(!allowWhenScreenOff)
            j = 1;
        int k = j;
        if(!allowWhenScreenOn)
            k = j | 2;
        return new android.app.NotificationManager.Policy(i, sourceToPrioritySenders(allowCallsFrom, 1), sourceToPrioritySenders(allowMessagesFrom, 1), k);
    }

    public String toString()
    {
        return (new StringBuilder(android/service/notification/ZenModeConfig.getSimpleName())).append('[').append("user=").append(user).append(",allowCalls=").append(allowCalls).append(",allowRepeatCallers=").append(allowRepeatCallers).append(",allowMessages=").append(allowMessages).append(",allowCallsFrom=").append(sourceToString(allowCallsFrom)).append(",allowMessagesFrom=").append(sourceToString(allowMessagesFrom)).append(",allowReminders=").append(allowReminders).append(",allowEvents=").append(allowEvents).append(",allowWhenScreenOff=").append(allowWhenScreenOff).append(",allowWhenScreenOn=").append(allowWhenScreenOn).append(",automaticRules=").append(automaticRules).append(",manualRule=").append(manualRule).append(']').toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        if(allowCalls)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(allowRepeatCallers)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(allowMessages)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(allowReminders)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(allowEvents)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(allowCallsFrom);
        parcel.writeInt(allowMessagesFrom);
        parcel.writeInt(user);
        parcel.writeParcelable(manualRule, 0);
        if(!automaticRules.isEmpty())
        {
            int j = automaticRules.size();
            String as[] = new String[j];
            ZenRule azenrule[] = new ZenRule[j];
            for(i = 0; i < j; i++)
            {
                as[i] = (String)automaticRules.keyAt(i);
                azenrule[i] = (ZenRule)automaticRules.valueAt(i);
            }

            parcel.writeInt(j);
            parcel.writeStringArray(as);
            parcel.writeTypedArray(azenrule, 0);
        } else
        {
            parcel.writeInt(0);
        }
        if(allowWhenScreenOff)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(allowWhenScreenOn)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
    }

    public void writeXml(XmlSerializer xmlserializer)
        throws IOException
    {
        xmlserializer.startTag(null, "zen");
        xmlserializer.attribute(null, "version", Integer.toString(2));
        xmlserializer.attribute(null, "user", Integer.toString(user));
        xmlserializer.startTag(null, "allow");
        xmlserializer.attribute(null, "calls", Boolean.toString(allowCalls));
        xmlserializer.attribute(null, "repeatCallers", Boolean.toString(allowRepeatCallers));
        xmlserializer.attribute(null, "messages", Boolean.toString(allowMessages));
        xmlserializer.attribute(null, "reminders", Boolean.toString(allowReminders));
        xmlserializer.attribute(null, "events", Boolean.toString(allowEvents));
        xmlserializer.attribute(null, "callsFrom", Integer.toString(allowCallsFrom));
        xmlserializer.attribute(null, "messagesFrom", Integer.toString(allowMessagesFrom));
        xmlserializer.attribute(null, "visualScreenOff", Boolean.toString(allowWhenScreenOff));
        xmlserializer.attribute(null, "visualScreenOn", Boolean.toString(allowWhenScreenOn));
        xmlserializer.endTag(null, "allow");
        if(manualRule != null)
        {
            xmlserializer.startTag(null, "manual");
            writeRuleXml(manualRule, xmlserializer);
            xmlserializer.endTag(null, "manual");
        }
        int i = automaticRules.size();
        for(int j = 0; j < i; j++)
        {
            String s = (String)automaticRules.keyAt(j);
            ZenRule zenrule = (ZenRule)automaticRules.valueAt(j);
            xmlserializer.startTag(null, "automatic");
            xmlserializer.attribute(null, "ruleId", s);
            writeRuleXml(zenrule, xmlserializer);
            xmlserializer.endTag(null, "automatic");
        }

        xmlserializer.endTag(null, "zen");
    }

    private static final String ALLOW_ATT_CALLS = "calls";
    private static final String ALLOW_ATT_CALLS_FROM = "callsFrom";
    private static final String ALLOW_ATT_EVENTS = "events";
    private static final String ALLOW_ATT_FROM = "from";
    private static final String ALLOW_ATT_MESSAGES = "messages";
    private static final String ALLOW_ATT_MESSAGES_FROM = "messagesFrom";
    private static final String ALLOW_ATT_REMINDERS = "reminders";
    private static final String ALLOW_ATT_REPEAT_CALLERS = "repeatCallers";
    private static final String ALLOW_ATT_SCREEN_OFF = "visualScreenOff";
    private static final String ALLOW_ATT_SCREEN_ON = "visualScreenOn";
    private static final String ALLOW_TAG = "allow";
    public static final int ALL_DAYS[] = {
        1, 2, 3, 4, 5, 6, 7
    };
    private static final String AUTOMATIC_TAG = "automatic";
    private static final String CONDITION_ATT_COMPONENT = "component";
    private static final String CONDITION_ATT_FLAGS = "flags";
    private static final String CONDITION_ATT_ICON = "icon";
    private static final String CONDITION_ATT_ID = "id";
    private static final String CONDITION_ATT_LINE1 = "line1";
    private static final String CONDITION_ATT_LINE2 = "line2";
    private static final String CONDITION_ATT_STATE = "state";
    private static final String CONDITION_ATT_SUMMARY = "summary";
    private static final String CONDITION_TAG = "condition";
    public static final String COUNTDOWN_PATH = "countdown";
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ZenModeConfig createFromParcel(Parcel parcel)
        {
            return new ZenModeConfig(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ZenModeConfig[] newArray(int i)
        {
            return new ZenModeConfig[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int DAY_MINUTES = 1440;
    private static final boolean DEFAULT_ALLOW_CALLS = true;
    private static final boolean DEFAULT_ALLOW_EVENTS = true;
    private static final boolean DEFAULT_ALLOW_MESSAGES = false;
    private static final boolean DEFAULT_ALLOW_REMINDERS = true;
    private static final boolean DEFAULT_ALLOW_REPEAT_CALLERS = false;
    private static final boolean DEFAULT_ALLOW_SCREEN_OFF = true;
    private static final boolean DEFAULT_ALLOW_SCREEN_ON = true;
    private static final int DEFAULT_SOURCE = 1;
    public static final String EVENT_PATH = "event";
    public static final String IS_ALARM_PATH = "alarm";
    private static final String MANUAL_TAG = "manual";
    public static final int MAX_SOURCE = 2;
    private static final int MINUTES_MS = 60000;
    public static final int MINUTE_BUCKETS[] = generateMinuteBuckets();
    private static final String RULE_ATT_COMPONENT = "component";
    private static final String RULE_ATT_CONDITION_ID = "conditionId";
    private static final String RULE_ATT_CREATION_TIME = "creationTime";
    private static final String RULE_ATT_ENABLED = "enabled";
    private static final String RULE_ATT_ENABLER = "enabler";
    private static final String RULE_ATT_ID = "ruleId";
    private static final String RULE_ATT_NAME = "name";
    private static final String RULE_ATT_SNOOZING = "snoozing";
    private static final String RULE_ATT_ZEN = "zen";
    public static final String SCHEDULE_PATH = "schedule";
    private static final int SECONDS_MS = 1000;
    public static final int SOURCE_ANYONE = 0;
    public static final int SOURCE_CONTACT = 1;
    public static final int SOURCE_STAR = 2;
    public static final String SYSTEM_AUTHORITY = "android";
    private static String TAG = "ZenModeConfig";
    public static final int WEEKEND_DAYS[] = {
        6, 7
    };
    public static final int WEEKNIGHT_DAYS[] = {
        1, 2, 3, 4, 5
    };
    private static final int XML_VERSION = 2;
    private static final String ZEN_ATT_USER = "user";
    private static final String ZEN_ATT_VERSION = "version";
    public static final String ZEN_TAG = "zen";
    private static final int ZERO_VALUE_MS = 10000;
    public boolean allowCalls;
    public int allowCallsFrom;
    public boolean allowEvents;
    public boolean allowMessages;
    public int allowMessagesFrom;
    public boolean allowReminders;
    public boolean allowRepeatCallers;
    public boolean allowWhenScreenOff;
    public boolean allowWhenScreenOn;
    public ArrayMap automaticRules;
    public ZenRule manualRule;
    public int user;

}
