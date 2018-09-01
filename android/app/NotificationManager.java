// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.ParceledListSlice;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.*;
import android.service.notification.StatusBarNotification;
import android.service.notification.ZenModeConfig;
import android.util.Log;
import java.util.*;

// Referenced classes of package android.app:
//            Notification, INotificationManager, NotificationChannel, NotificationChannelGroup, 
//            AutomaticZenRule, ActivityManager

public class NotificationManager
{
    public static class Policy
        implements Parcelable
    {

        private static String effectToString(int i)
        {
            switch(i)
            {
            case 0: // '\0'
            default:
                return (new StringBuilder()).append("UNKNOWN_").append(i).toString();

            case 1: // '\001'
                return "SUPPRESSED_EFFECT_SCREEN_OFF";

            case 2: // '\002'
                return "SUPPRESSED_EFFECT_SCREEN_ON";

            case -1: 
                return "SUPPRESSED_EFFECTS_UNSET";
            }
        }

        public static String priorityCategoriesToString(int i)
        {
            if(i == 0)
                return "";
            StringBuilder stringbuilder = new StringBuilder();
            for(int j = 0; j < ALL_PRIORITY_CATEGORIES.length; j++)
            {
                int k = ALL_PRIORITY_CATEGORIES[j];
                if((i & k) != 0)
                {
                    if(stringbuilder.length() > 0)
                        stringbuilder.append(',');
                    stringbuilder.append(priorityCategoryToString(k));
                }
                i &= k;
            }

            if(i != 0)
            {
                if(stringbuilder.length() > 0)
                    stringbuilder.append(',');
                stringbuilder.append("PRIORITY_CATEGORY_UNKNOWN_").append(i);
            }
            return stringbuilder.toString();
        }

        private static String priorityCategoryToString(int i)
        {
            switch(i)
            {
            default:
                return (new StringBuilder()).append("PRIORITY_CATEGORY_UNKNOWN_").append(i).toString();

            case 1: // '\001'
                return "PRIORITY_CATEGORY_REMINDERS";

            case 2: // '\002'
                return "PRIORITY_CATEGORY_EVENTS";

            case 4: // '\004'
                return "PRIORITY_CATEGORY_MESSAGES";

            case 8: // '\b'
                return "PRIORITY_CATEGORY_CALLS";

            case 16: // '\020'
                return "PRIORITY_CATEGORY_REPEAT_CALLERS";
            }
        }

        public static String prioritySendersToString(int i)
        {
            switch(i)
            {
            default:
                return (new StringBuilder()).append("PRIORITY_SENDERS_UNKNOWN_").append(i).toString();

            case 0: // '\0'
                return "PRIORITY_SENDERS_ANY";

            case 1: // '\001'
                return "PRIORITY_SENDERS_CONTACTS";

            case 2: // '\002'
                return "PRIORITY_SENDERS_STARRED";
            }
        }

        public static String suppressedEffectsToString(int i)
        {
            if(i <= 0)
                return "";
            StringBuilder stringbuilder = new StringBuilder();
            boolean flag = false;
            int k = i;
            for(i = ((flag) ? 1 : 0); i < ALL_SUPPRESSED_EFFECTS.length; i++)
            {
                int j = ALL_SUPPRESSED_EFFECTS[i];
                if((k & j) != 0)
                {
                    if(stringbuilder.length() > 0)
                        stringbuilder.append(',');
                    stringbuilder.append(effectToString(j));
                }
                k &= j;
            }

            if(k != 0)
            {
                if(stringbuilder.length() > 0)
                    stringbuilder.append(',');
                stringbuilder.append("UNKNOWN_").append(k);
            }
            return stringbuilder.toString();
        }

        public int describeContents()
        {
            return 0;
        }

        public boolean equals(Object obj)
        {
            boolean flag = true;
            if(!(obj instanceof Policy))
                return false;
            if(obj == this)
                return true;
            obj = (Policy)obj;
            if(((Policy) (obj)).priorityCategories == priorityCategories && ((Policy) (obj)).priorityCallSenders == priorityCallSenders && ((Policy) (obj)).priorityMessageSenders == priorityMessageSenders)
            {
                if(((Policy) (obj)).suppressedVisualEffects != suppressedVisualEffects)
                    flag = false;
            } else
            {
                flag = false;
            }
            return flag;
        }

        public int hashCode()
        {
            return Objects.hash(new Object[] {
                Integer.valueOf(priorityCategories), Integer.valueOf(priorityCallSenders), Integer.valueOf(priorityMessageSenders), Integer.valueOf(suppressedVisualEffects)
            });
        }

        public String toString()
        {
            return (new StringBuilder()).append("NotificationManager.Policy[priorityCategories=").append(priorityCategoriesToString(priorityCategories)).append(",priorityCallSenders=").append(prioritySendersToString(priorityCallSenders)).append(",priorityMessageSenders=").append(prioritySendersToString(priorityMessageSenders)).append(",suppressedVisualEffects=").append(suppressedEffectsToString(suppressedVisualEffects)).append("]").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(priorityCategories);
            parcel.writeInt(priorityCallSenders);
            parcel.writeInt(priorityMessageSenders);
            parcel.writeInt(suppressedVisualEffects);
        }

        private static final int ALL_PRIORITY_CATEGORIES[] = {
            1, 2, 4, 8, 16
        };
        private static final int ALL_SUPPRESSED_EFFECTS[] = {
            1, 2
        };
        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public Policy createFromParcel(Parcel parcel)
            {
                return new Policy(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public Policy[] newArray(int i)
            {
                return new Policy[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public static final int PRIORITY_CATEGORY_CALLS = 8;
        public static final int PRIORITY_CATEGORY_EVENTS = 2;
        public static final int PRIORITY_CATEGORY_MESSAGES = 4;
        public static final int PRIORITY_CATEGORY_REMINDERS = 1;
        public static final int PRIORITY_CATEGORY_REPEAT_CALLERS = 16;
        public static final int PRIORITY_SENDERS_ANY = 0;
        public static final int PRIORITY_SENDERS_CONTACTS = 1;
        public static final int PRIORITY_SENDERS_STARRED = 2;
        public static final int SUPPRESSED_EFFECTS_UNSET = -1;
        public static final int SUPPRESSED_EFFECT_SCREEN_OFF = 1;
        public static final int SUPPRESSED_EFFECT_SCREEN_ON = 2;
        public final int priorityCallSenders;
        public final int priorityCategories;
        public final int priorityMessageSenders;
        public final int suppressedVisualEffects;


        public Policy(int i, int j, int k)
        {
            this(i, j, k, -1);
        }

        public Policy(int i, int j, int k, int l)
        {
            priorityCategories = i;
            priorityCallSenders = j;
            priorityMessageSenders = k;
            suppressedVisualEffects = l;
        }

        public Policy(Parcel parcel)
        {
            this(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
        }
    }


    NotificationManager(Context context, Handler handler)
    {
        mContext = context;
    }

    private static void checkRequired(String s, Object obj)
    {
        if(obj == null)
            throw new IllegalArgumentException((new StringBuilder()).append(s).append(" is required").toString());
        else
            return;
    }

    private void fixLegacySmallIcon(Notification notification, String s)
    {
        if(notification.getSmallIcon() == null && notification.icon != 0)
            notification.setSmallIcon(Icon.createWithResource(s, notification.icon));
    }

    public static NotificationManager from(Context context)
    {
        return (NotificationManager)context.getSystemService("notification");
    }

    public static INotificationManager getService()
    {
        if(sService != null)
        {
            return sService;
        } else
        {
            sService = INotificationManager.Stub.asInterface(ServiceManager.getService("notification"));
            return sService;
        }
    }

    public static int zenModeFromInterruptionFilter(int i, int j)
    {
        switch(i)
        {
        default:
            return j;

        case 1: // '\001'
            return 0;

        case 2: // '\002'
            return 1;

        case 4: // '\004'
            return 3;

        case 3: // '\003'
            return 2;

        case 5: // '\005'
            return 4;
        }
    }

    public static int zenModeToInterruptionFilter(int i)
    {
        switch(i)
        {
        default:
            return 0;

        case 0: // '\0'
            return 1;

        case 1: // '\001'
            return 2;

        case 3: // '\003'
            return 4;

        case 2: // '\002'
            return 3;

        case 4: // '\004'
            return 5;
        }
    }

    public String addAutomaticZenRule(AutomaticZenRule automaticzenrule)
    {
        INotificationManager inotificationmanager = getService();
        try
        {
            automaticzenrule = inotificationmanager.addAutomaticZenRule(automaticzenrule);
        }
        // Misplaced declaration of an exception variable
        catch(AutomaticZenRule automaticzenrule)
        {
            throw automaticzenrule.rethrowFromSystemServer();
        }
        return automaticzenrule;
    }

    public boolean areNotificationsEnabled()
    {
        INotificationManager inotificationmanager = getService();
        boolean flag;
        try
        {
            flag = inotificationmanager.areNotificationsEnabled(mContext.getPackageName());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public void cancel(int i)
    {
        cancel(null, i);
    }

    public void cancel(String s, int i)
    {
        cancelAsUser(s, i, new UserHandle(UserHandle.myUserId()));
    }

    public void cancelAll()
    {
        INotificationManager inotificationmanager = getService();
        String s = mContext.getPackageName();
        if(localLOGV)
            Log.v(TAG, (new StringBuilder()).append(s).append(": cancelAll()").toString());
        try
        {
            inotificationmanager.cancelAllNotifications(s, UserHandle.myUserId());
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void cancelAsUser(String s, int i, UserHandle userhandle)
    {
        INotificationManager inotificationmanager = getService();
        String s1 = mContext.getPackageName();
        if(localLOGV)
            Log.v(TAG, (new StringBuilder()).append(s1).append(": cancel(").append(i).append(")").toString());
        try
        {
            inotificationmanager.cancelNotificationWithTag(s1, s, i, userhandle.getIdentifier());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void createNotificationChannel(NotificationChannel notificationchannel)
    {
        createNotificationChannels(Arrays.asList(new NotificationChannel[] {
            notificationchannel
        }));
    }

    public void createNotificationChannelGroup(NotificationChannelGroup notificationchannelgroup)
    {
        createNotificationChannelGroups(Arrays.asList(new NotificationChannelGroup[] {
            notificationchannelgroup
        }));
    }

    public void createNotificationChannelGroups(List list)
    {
        INotificationManager inotificationmanager = getService();
        try
        {
            String s = mContext.getPackageName();
            ParceledListSlice parceledlistslice = JVM INSTR new #227 <Class ParceledListSlice>;
            parceledlistslice.ParceledListSlice(list);
            inotificationmanager.createNotificationChannelGroups(s, parceledlistslice);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(List list)
        {
            throw list.rethrowFromSystemServer();
        }
    }

    public void createNotificationChannels(List list)
    {
        INotificationManager inotificationmanager = getService();
        try
        {
            String s = mContext.getPackageName();
            ParceledListSlice parceledlistslice = JVM INSTR new #227 <Class ParceledListSlice>;
            parceledlistslice.ParceledListSlice(list);
            inotificationmanager.createNotificationChannels(s, parceledlistslice);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(List list)
        {
            throw list.rethrowFromSystemServer();
        }
    }

    public void deleteNotificationChannel(String s)
    {
        INotificationManager inotificationmanager = getService();
        try
        {
            inotificationmanager.deleteNotificationChannel(mContext.getPackageName(), s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void deleteNotificationChannelGroup(String s)
    {
        INotificationManager inotificationmanager = getService();
        try
        {
            inotificationmanager.deleteNotificationChannelGroup(mContext.getPackageName(), s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public StatusBarNotification[] getActiveNotifications()
    {
        Object obj = getService();
        String s = mContext.getPackageName();
        StatusBarNotification astatusbarnotification[];
        try
        {
            obj = ((INotificationManager) (obj)).getAppActiveNotifications(s, UserHandle.myUserId()).getList();
            astatusbarnotification = (StatusBarNotification[])((List) (obj)).toArray(new StatusBarNotification[((List) (obj)).size()]);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return astatusbarnotification;
    }

    public AutomaticZenRule getAutomaticZenRule(String s)
    {
        INotificationManager inotificationmanager = getService();
        try
        {
            s = inotificationmanager.getAutomaticZenRule(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return s;
    }

    public Map getAutomaticZenRules()
    {
        Object obj = getService();
        try
        {
            Object obj1 = ((INotificationManager) (obj)).getZenRules();
            obj = JVM INSTR new #278 <Class HashMap>;
            ((HashMap) (obj)).HashMap();
            String s;
            AutomaticZenRule automaticzenrule;
            for(obj1 = ((Iterable) (obj1)).iterator(); ((Iterator) (obj1)).hasNext(); ((Map) (obj)).put(s, automaticzenrule))
            {
                android.service.notification.ZenModeConfig.ZenRule zenrule = (android.service.notification.ZenModeConfig.ZenRule)((Iterator) (obj1)).next();
                s = zenrule.id;
                automaticzenrule = JVM INSTR new #301 <Class AutomaticZenRule>;
                automaticzenrule.AutomaticZenRule(zenrule.name, zenrule.component, zenrule.conditionId, zenModeToInterruptionFilter(zenrule.zenMode), zenrule.enabled, zenrule.creationTime);
            }

        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            throw ((RemoteException) (obj)).rethrowFromSystemServer();
        }
        return ((Map) (obj));
    }

    public final int getCurrentInterruptionFilter()
    {
        INotificationManager inotificationmanager = getService();
        int i;
        try
        {
            i = zenModeToInterruptionFilter(inotificationmanager.getZenMode());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public ComponentName getEffectsSuppressor()
    {
        Object obj = getService();
        try
        {
            obj = ((INotificationManager) (obj)).getEffectsSuppressor();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return ((ComponentName) (obj));
    }

    public List getEnabledNotificationListenerPackages()
    {
        Object obj = getService();
        try
        {
            obj = ((INotificationManager) (obj)).getEnabledNotificationListenerPackages();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return ((List) (obj));
    }

    public List getEnabledNotificationListeners(int i)
    {
        Object obj = getService();
        try
        {
            obj = ((INotificationManager) (obj)).getEnabledNotificationListeners(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return ((List) (obj));
    }

    public int getImportance()
    {
        INotificationManager inotificationmanager = getService();
        int i;
        try
        {
            i = inotificationmanager.getPackageImportance(mContext.getPackageName());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public NotificationChannel getNotificationChannel(String s)
    {
        INotificationManager inotificationmanager = getService();
        try
        {
            s = inotificationmanager.getNotificationChannel(mContext.getPackageName(), s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return s;
    }

    public List getNotificationChannelGroups()
    {
        Object obj = getService();
        try
        {
            obj = ((INotificationManager) (obj)).getNotificationChannelGroups(mContext.getPackageName()).getList();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return ((List) (obj));
    }

    public List getNotificationChannels()
    {
        Object obj = getService();
        try
        {
            obj = ((INotificationManager) (obj)).getNotificationChannels(mContext.getPackageName()).getList();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return ((List) (obj));
    }

    public Policy getNotificationPolicy()
    {
        Object obj = getService();
        try
        {
            obj = ((INotificationManager) (obj)).getNotificationPolicy(mContext.getOpPackageName());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return ((Policy) (obj));
    }

    public int getRuleInstanceCount(ComponentName componentname)
    {
        INotificationManager inotificationmanager = getService();
        int i;
        try
        {
            i = inotificationmanager.getRuleInstanceCount(componentname);
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        return i;
    }

    public int getZenMode()
    {
        INotificationManager inotificationmanager = getService();
        int i;
        try
        {
            i = inotificationmanager.getZenMode();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public ZenModeConfig getZenModeConfig()
    {
        Object obj = getService();
        try
        {
            obj = ((INotificationManager) (obj)).getZenModeConfig();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return ((ZenModeConfig) (obj));
    }

    public boolean isNotificationAssistantAccessGranted(ComponentName componentname)
    {
        INotificationManager inotificationmanager = getService();
        boolean flag;
        try
        {
            flag = inotificationmanager.isNotificationAssistantAccessGranted(componentname);
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isNotificationListenerAccessGranted(ComponentName componentname)
    {
        INotificationManager inotificationmanager = getService();
        boolean flag;
        try
        {
            flag = inotificationmanager.isNotificationListenerAccessGranted(componentname);
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isNotificationPolicyAccessGranted()
    {
        INotificationManager inotificationmanager = getService();
        boolean flag;
        try
        {
            flag = inotificationmanager.isNotificationPolicyAccessGranted(mContext.getOpPackageName());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isNotificationPolicyAccessGrantedForPackage(String s)
    {
        INotificationManager inotificationmanager = getService();
        boolean flag;
        try
        {
            flag = inotificationmanager.isNotificationPolicyAccessGrantedForPackage(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isSystemConditionProviderEnabled(String s)
    {
        INotificationManager inotificationmanager = getService();
        boolean flag;
        try
        {
            flag = inotificationmanager.isSystemConditionProviderEnabled(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean matchesCallFilter(Bundle bundle)
    {
        INotificationManager inotificationmanager = getService();
        boolean flag;
        try
        {
            flag = inotificationmanager.matchesCallFilter(bundle);
        }
        // Misplaced declaration of an exception variable
        catch(Bundle bundle)
        {
            throw bundle.rethrowFromSystemServer();
        }
        return flag;
    }

    public void notify(int i, Notification notification)
    {
        notify(null, i, notification);
    }

    public void notify(String s, int i, Notification notification)
    {
        notifyAsUser(s, i, notification, new UserHandle(UserHandle.myUserId()));
    }

    public void notifyAsUser(String s, int i, Notification notification, UserHandle userhandle)
    {
        INotificationManager inotificationmanager = getService();
        String s1 = mContext.getPackageName();
        Notification.addFieldsFromContext(mContext, notification);
        if(notification.sound != null)
        {
            notification.sound = notification.sound.getCanonicalUri();
            if(StrictMode.vmFileUriExposureEnabled())
                notification.sound.checkFileUriExposed("Notification.sound");
        }
        fixLegacySmallIcon(notification, s1);
        if(mContext.getApplicationInfo().targetSdkVersion > 22 && notification.getSmallIcon() == null)
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid notification (no valid small icon): ").append(notification).toString());
        if(localLOGV)
            Log.v(TAG, (new StringBuilder()).append(s1).append(": notify(").append(i).append(", ").append(notification).append(")").toString());
        notification.reduceImageSizes(mContext);
        notification = Notification.Builder.maybeCloneStrippedForDelivery(notification, ((ActivityManager)mContext.getSystemService("activity")).isLowRamDevice());
        try
        {
            inotificationmanager.enqueueNotificationWithTag(s1, mContext.getOpPackageName(), s, i, notification, userhandle.getIdentifier());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public boolean removeAutomaticZenRule(String s)
    {
        INotificationManager inotificationmanager = getService();
        boolean flag;
        try
        {
            flag = inotificationmanager.removeAutomaticZenRule(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean removeAutomaticZenRules(String s)
    {
        INotificationManager inotificationmanager = getService();
        boolean flag;
        try
        {
            flag = inotificationmanager.removeAutomaticZenRules(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public final void setInterruptionFilter(int i)
    {
        INotificationManager inotificationmanager = getService();
        try
        {
            inotificationmanager.setInterruptionFilter(mContext.getOpPackageName(), i);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void setNotificationListenerAccessGranted(ComponentName componentname, boolean flag)
    {
        INotificationManager inotificationmanager = getService();
        try
        {
            inotificationmanager.setNotificationListenerAccessGranted(componentname, flag);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
    }

    public void setNotificationListenerAccessGrantedForUser(ComponentName componentname, int i, boolean flag)
    {
        INotificationManager inotificationmanager = getService();
        try
        {
            inotificationmanager.setNotificationListenerAccessGrantedForUser(componentname, i, flag);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
    }

    public void setNotificationPolicy(Policy policy)
    {
        checkRequired("policy", policy);
        INotificationManager inotificationmanager = getService();
        try
        {
            inotificationmanager.setNotificationPolicy(mContext.getOpPackageName(), policy);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Policy policy)
        {
            throw policy.rethrowFromSystemServer();
        }
    }

    public void setNotificationPolicyAccessGranted(String s, boolean flag)
    {
        INotificationManager inotificationmanager = getService();
        try
        {
            inotificationmanager.setNotificationPolicyAccessGranted(s, flag);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void setZenMode(int i, Uri uri, String s)
    {
        INotificationManager inotificationmanager = getService();
        try
        {
            inotificationmanager.setZenMode(i, uri, s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Uri uri)
        {
            throw uri.rethrowFromSystemServer();
        }
    }

    public boolean updateAutomaticZenRule(String s, AutomaticZenRule automaticzenrule)
    {
        INotificationManager inotificationmanager = getService();
        boolean flag;
        try
        {
            flag = inotificationmanager.updateAutomaticZenRule(s, automaticzenrule);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public static final String ACTION_EFFECTS_SUPPRESSOR_CHANGED = "android.os.action.ACTION_EFFECTS_SUPPRESSOR_CHANGED";
    public static final String ACTION_INTERRUPTION_FILTER_CHANGED = "android.app.action.INTERRUPTION_FILTER_CHANGED";
    public static final String ACTION_INTERRUPTION_FILTER_CHANGED_INTERNAL = "android.app.action.INTERRUPTION_FILTER_CHANGED_INTERNAL";
    public static final String ACTION_NOTIFICATION_POLICY_ACCESS_GRANTED_CHANGED = "android.app.action.NOTIFICATION_POLICY_ACCESS_GRANTED_CHANGED";
    public static final String ACTION_NOTIFICATION_POLICY_CHANGED = "android.app.action.NOTIFICATION_POLICY_CHANGED";
    public static final int IMPORTANCE_DEFAULT = 3;
    public static final int IMPORTANCE_HIGH = 4;
    public static final int IMPORTANCE_LOW = 2;
    public static final int IMPORTANCE_MAX = 5;
    public static final int IMPORTANCE_MIN = 1;
    public static final int IMPORTANCE_NONE = 0;
    public static final int IMPORTANCE_UNSPECIFIED = -1000;
    public static final int INTERRUPTION_FILTER_ALARMS = 4;
    public static final int INTERRUPTION_FILTER_ALL = 1;
    public static final int INTERRUPTION_FILTER_MIUI = 5;
    public static final int INTERRUPTION_FILTER_NONE = 3;
    public static final int INTERRUPTION_FILTER_PRIORITY = 2;
    public static final int INTERRUPTION_FILTER_UNKNOWN = 0;
    private static String TAG = "NotificationManager";
    public static final int VISIBILITY_NO_OVERRIDE = -1000;
    private static boolean localLOGV = false;
    private static INotificationManager sService;
    private Context mContext;

}
