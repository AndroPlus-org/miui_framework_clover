// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.provider;

import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.content.res.*;
import android.database.Cursor;
import android.graphics.Rect;
import android.net.Uri;
import android.os.*;
import android.text.TextUtils;
import android.util.*;
import java.io.File;
import java.util.*;
import miui.hareware.display.DisplayFeatureManager;
import miui.mqsas.sdk.MQSEventManagerDelegate;
import miui.os.Build;
import miui.security.SecurityManager;
import miui.securityspace.XSpaceUserHandle;
import miui.telephony.SubscriptionManager;
import miui.telephony.TelephonyManager;
import miui.util.*;
import org.json.*;

public class MiuiSettings
{
    public static final class Ad
    {

        public static String getAaid(ContentResolver contentresolver)
        {
            return Settings.Global.getString(contentresolver, "ad_aaid");
        }

        public static long getPersonalizedAdEnableTime(ContentResolver contentresolver)
            throws SecurityException
        {
            long l = Settings.Global.getLong(contentresolver, "personalized_ad_time", 0L);
            Slog.i("Ad", (new StringBuilder()).append("getPersonalizedAdEnableTime: ").append(l).toString());
            return l;
        }

        private static int getPersonizedAdSettings(ContentResolver contentresolver)
        {
            return Settings.Global.getInt(contentresolver, "personalized_ad_enabled", 1);
        }

        public static boolean isPersonalizedAdDialogPromoted(ContentResolver contentresolver)
        {
            boolean flag = false;
            int i = getPersonizedAdSettings(contentresolver);
            Slog.i("Ad", (new StringBuilder()).append("isPersonalizedAdDialogPromoted getAdSettings: ").append(i).toString());
            if((i & 2) != 0)
                flag = true;
            return flag;
        }

        public static boolean isPersonalizedAdEnabled(ContentResolver contentresolver)
        {
            boolean flag = false;
            int i = getPersonizedAdSettings(contentresolver);
            Slog.i("Ad", (new StringBuilder()).append("isPersonalizedAdEnabled getAdSettings: ").append(i).toString());
            if((i & 1) != 0)
                flag = true;
            return flag;
        }

        public static void resetAaid(Context context)
            throws SecurityException
        {
            Object obj = getAaid(context.getContentResolver());
            String s = ((String) (obj));
            if(TextUtils.isEmpty(((CharSequence) (obj))))
                s = "";
            String s1 = UUID.randomUUID().toString();
            Settings.Global.putString(context.getContentResolver(), "ad_aaid", s1);
            obj = new Intent("miui.intent.action.ad.AAID_RESET");
            ((Intent) (obj)).putExtra("old_aaid", s);
            ((Intent) (obj)).putExtra("new_aaid", s1);
            context.sendBroadcast(((Intent) (obj)));
        }

        public static void setPersonalizedAdDialogPromoted(ContentResolver contentresolver, boolean flag)
            throws SecurityException
        {
            int i = getPersonizedAdSettings(contentresolver);
            int j;
            if(flag)
                j = i | 2;
            else
                j = i & -3;
            Slog.i("Ad", (new StringBuilder()).append("setPersonalizedAdDialogPromoted: ").append(flag).append(", oldAdSettings: ").append(i).append(", newAdSettings: ").append(j).toString());
            setPersonizedAdSettings(contentresolver, j);
        }

        public static void setPersonalizedAdEnable(ContentResolver contentresolver, boolean flag)
            throws SecurityException
        {
            int i = getPersonizedAdSettings(contentresolver);
            int j;
            if(flag)
                j = i | 1;
            else
                j = i & -2;
            Slog.i("Ad", (new StringBuilder()).append("setPersonalizedAdEnable: ").append(flag).append(", oldAdSettings: ").append(i).append(", newAdSettigns: ").append(j).toString());
            setPersonizedAdSettings(contentresolver, j);
        }

        public static void setPersonalizedAdEnableTime(ContentResolver contentresolver, long l)
            throws SecurityException
        {
            Slog.i("Ad", (new StringBuilder()).append("setPersonalizedAdEnableTime: ").append(l).toString());
            Settings.Global.putLong(contentresolver, "personalized_ad_time", l);
        }

        private static void setPersonizedAdSettings(ContentResolver contentresolver, int i)
        {
            Settings.Global.putInt(contentresolver, "personalized_ad_enabled", i);
        }

        private static final String AAID = "ad_aaid";
        public static final String ACTION_AAID_RESET = "miui.intent.action.ad.AAID_RESET";
        private static final int BIT_FLAG_PERSONALIZED_AD_DIALOG_PROMOTED = 2;
        private static final int BIT_FLAG_PERSONALIZED_AD_ENABLE = 1;
        public static final String EXTRA_KEY_NEW_AAID = "new_aaid";
        public static final String EXTRA_KEY_OLD_AAID = "old_aaid";
        private static final String PERSONALIZED_AD_SETTINGS = "personalized_ad_enabled";
        private static final String PERSONALIZED_AD_TIME = "personalized_ad_time";
        private static final String TAG = "Ad";

        public Ad()
        {
        }
    }

    public static final class AntiSpam
    {

        public static int getEndTimeForQuietMode(Context context)
        {
            return Settings.Secure.getInt(context.getContentResolver(), "end_time_of_qm", 420);
        }

        public static int getMarkedNumberBlockType(int i)
        {
            return ((Integer)mapIdToBlockType.get(Integer.valueOf(i))).intValue();
        }

        public static int getMode(Context context, String s, int i)
        {
            return Settings.Secure.getInt(context.getContentResolver(), s, i);
        }

        public static long getNextAutoEndTime(Context context)
        {
            return Settings.Secure.getLong(context.getContentResolver(), "next_auto_end_time_of_qm", 0L);
        }

        public static long getNextAutoStartTime(Context context)
        {
            return Settings.Secure.getLong(context.getContentResolver(), "next_auto_start_time_of_qm", 0L);
        }

        public static int getNotificationType(Context context, int i)
        {
            ContentResolver contentresolver = context.getContentResolver();
            if(i == 1)
                context = "show_notification_type";
            else
                context = "show_notification_type_sim_2";
            return Settings.Secure.getInt(contentresolver, context, 0);
        }

        public static int getQuietRepeatType(Context context)
        {
            return Settings.Secure.getInt(context.getContentResolver(), "quiet_repeat_type", 127);
        }

        public static long getSMSClassifierUpdateTime(Context context)
        {
            return Settings.Secure.getLong(context.getContentResolver(), "sms_classifier_update_time", 0L);
        }

        public static int getStartTimeForQuietMode(Context context)
        {
            return Settings.Secure.getInt(context.getContentResolver(), "start_time_of_qm", 1380);
        }

        public static boolean getState(Context context, String s, boolean flag)
        {
            return miui.provider.ExtraSettings.Secure.getBoolean(context.getContentResolver(), s, flag);
        }

        public static int getVipListForQuietMode(Context context)
        {
            return Settings.Secure.getInt(context.getContentResolver(), "vip_list_for_qm", 0);
        }

        public static boolean hasNewAntispam(Context context)
        {
            return miui.provider.ExtraSettings.Secure.getBoolean(context.getContentResolver(), "has_new_antispam", false);
        }

        public static boolean isAntiSpam(Context context)
        {
            boolean flag = true;
            if(isAntiSpamSettingsSharedForSims(context))
                return isAntiSpamEnableForSim(context, 1);
            boolean flag1;
            boolean flag2;
            if(SubscriptionManager.getDefault().getSubscriptionInfoForSlot(0) != null)
                flag1 = true;
            else
                flag1 = false;
            if(SubscriptionManager.getDefault().getSubscriptionInfoForSlot(1) != null)
                flag2 = true;
            else
                flag2 = false;
            if(flag1 || flag2)
            {
                if(!isAntiSpamEnableForSim(context, 1) || !flag1)
                {
                    if(!isAntiSpamEnableForSim(context, 2))
                        flag2 = false;
                } else
                {
                    flag2 = true;
                }
                return flag2;
            }
            flag2 = flag;
            if(!isAntiSpamEnableForSim(context, 1))
                flag2 = isAntiSpamEnableForSim(context, 2);
            return flag2;
        }

        public static boolean isAntiSpamEnableForSim(Context context, int i)
        {
            ContentResolver contentresolver = context.getContentResolver();
            if(i == 1)
                context = "antispam_enable_for_sim_1";
            else
                context = "antispam_enable_for_sim_2";
            return miui.provider.ExtraSettings.Secure.getBoolean(contentresolver, context, true);
        }

        public static boolean isAntiSpamSettingsSharedForSims(Context context)
        {
            return miui.provider.ExtraSettings.Secure.getBoolean(context.getContentResolver(), "antispam_settings_shared_for_sims", true);
        }

        public static boolean isAutoTimerOfQuietModeEnable(Context context)
        {
            boolean flag = true;
            if(Settings.Secure.getInt(context.getContentResolver(), "auto_timer_of_qm_enable", 0) != 1)
                flag = false;
            return flag;
        }

        public static boolean isMarkNumBlockOpen(Context context, int i)
        {
            boolean flag;
            boolean flag1;
            flag = true;
            flag1 = true;
            if(i != 1) goto _L2; else goto _L1
_L1:
            boolean flag2 = flag1;
            if(getMode(context, "fraud_num_state", 1) == 0) goto _L4; else goto _L3
_L3:
            if(getMode(context, "agent_num_state", 1) != 0) goto _L6; else goto _L5
_L5:
            flag2 = flag1;
_L4:
            return flag2;
_L6:
            flag2 = flag1;
            if(getMode(context, "sell_num_state", 1) != 0)
            {
                flag2 = flag1;
                if(getMode(context, "harass_num_state", 1) != 0)
                    flag2 = false;
            }
            if(true) goto _L4; else goto _L2
_L2:
            if(i != 2) goto _L8; else goto _L7
_L7:
            flag2 = flag;
            if(getMode(context, "fraud_num_state_sim_2", 1) == 0) goto _L10; else goto _L9
_L9:
            if(getMode(context, "agent_num_state_sim_2", 1) != 0) goto _L12; else goto _L11
_L11:
            flag2 = flag;
_L10:
            return flag2;
_L12:
            flag2 = flag;
            if(getMode(context, "sell_num_state_sim_2", 1) != 0)
            {
                flag2 = flag;
                if(getMode(context, "harass_num_state_sim_2", 1) != 0)
                    flag2 = false;
            }
            if(true) goto _L10; else goto _L8
_L8:
            return false;
        }

        private static boolean isMarkNumBlockSet(Context context)
        {
            return getState(context, "mark_guide_is_set", false);
        }

        public static boolean isMarkingTypeGuided(Context context, String s)
        {
            return getState(context, s, false);
        }

        public static boolean isQuietModeEnable(Context context)
        {
            return ExtraNotificationManager.isQuietModeEnable(context, -3);
        }

        public static boolean isQuietModeEnable(Context context, int i)
        {
            return ExtraNotificationManager.isQuietModeEnable(context, i);
        }

        public static boolean isQuietModeEnableForCurrentUser(Context context)
        {
            context = context.getContentResolver().query(Uri.withAppendedPath(Uri.parse("content://antispamCommon/zenmode"), "1"), null, null, null, null);
            if(context != null)
            {
                if(context != null)
                    context.close();
                return true;
            }
            if(context != null)
                context.close();
_L2:
            return false;
            context;
            context.printStackTrace();
            if(true) goto _L2; else goto _L1
_L1:
            context;
            throw context;
        }

        public static boolean isQuietWristband(Context context)
        {
            boolean flag = true;
            if(Settings.Secure.getInt(context.getContentResolver(), "quiet_wristband", 0) != 1)
                flag = false;
            return flag;
        }

        public static boolean isRepeatedCallActionEnable(Context context)
        {
            return ExtraNotificationManager.isRepeatedCallEnable(context);
        }

        public static boolean isSMSClassifierAutoUpdate(Context context)
        {
            return miui.provider.ExtraSettings.Secure.getBoolean(context.getContentResolver(), "sms_classifier_auto_update", true);
        }

        public static boolean isVipCallActionEnable(Context context)
        {
            boolean flag = true;
            if(Settings.Secure.getInt(context.getContentResolver(), "call_act_of_vip", 0) != 1)
                flag = false;
            return flag;
        }

        public static String mapIdToString(int i)
        {
            switch(i)
            {
            default:
                return "";

            case 1: // '\001'
                return "mark_guide_fraud";

            case 2: // '\002'
                return "mark_guide_agent";

            case 3: // '\003'
                return "mark_guide_sell";

            case 10: // '\n'
                return "mark_guide_harass";
            }
        }

        public static void resetMarkedNumberBlockSettings(Context context)
        {
            setMode(context, "fraud_num_state", 1);
            setMode(context, "agent_num_state", 1);
            setMode(context, "sell_num_state", 1);
            setMode(context, "harass_num_state", 1);
            setMode(context, "fraud_num_state_sim_2", 1);
            setMode(context, "agent_num_state_sim_2", 1);
            setMode(context, "sell_num_state_sim_2", 1);
            setMode(context, "harass_num_state_sim_2", 1);
        }

        public static void setAntiSpamEnableForSim(Context context, int i, boolean flag)
        {
            ContentResolver contentresolver = context.getContentResolver();
            if(i == 1)
                context = "antispam_enable_for_sim_1";
            else
                context = "antispam_enable_for_sim_2";
            miui.provider.ExtraSettings.Secure.putBoolean(contentresolver, context, flag);
        }

        public static void setAntiSpamSettingsSharedForSims(Context context, boolean flag)
        {
            miui.provider.ExtraSettings.Secure.putBoolean(context.getContentResolver(), "antispam_settings_shared_for_sims", flag);
        }

        public static void setAutoTimerOfQuietMode(Context context, boolean flag)
        {
            context = context.getContentResolver();
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            Settings.Secure.putInt(context, "auto_timer_of_qm_enable", i);
        }

        public static void setEndTimeForQuietMode(Context context, int i)
        {
            Settings.Secure.putInt(context.getContentResolver(), "end_time_of_qm", i);
        }

        public static void setHasNewAntispam(Context context, boolean flag)
        {
            miui.provider.ExtraSettings.Secure.putBoolean(context.getContentResolver(), "has_new_antispam", flag);
        }

        public static void setMarkNumBlockSet(Context context, boolean flag)
        {
            setState(context, "mark_guide_is_set", flag);
        }

        public static void setMarkingTypeGuided(Context context, String s, boolean flag)
        {
            setState(context, s, flag);
        }

        public static void setMode(Context context, String s, int i)
        {
            Settings.Secure.putInt(context.getContentResolver(), s, i);
        }

        public static void setNextAutoEndTime(Context context, long l)
        {
            Settings.Secure.putLong(context.getContentResolver(), "next_auto_end_time_of_qm", l);
        }

        public static void setNextAutoStartTime(Context context, long l)
        {
            Settings.Secure.putLong(context.getContentResolver(), "next_auto_start_time_of_qm", l);
        }

        public static void setNotificationType(Context context, int i, int j)
        {
            ContentResolver contentresolver = context.getContentResolver();
            if(j == 1)
                context = "show_notification_type";
            else
                context = "show_notification_type_sim_2";
            Settings.Secure.putInt(contentresolver, context, i);
        }

        public static void setQuietMode(Context context, boolean flag)
        {
            ExtraNotificationManager.setQuietMode(context, flag, -3);
        }

        public static void setQuietMode(Context context, boolean flag, int i)
        {
            ExtraNotificationManager.setQuietMode(context, flag, i);
        }

        public static void setQuietRepeatType(Context context, int i)
        {
            Settings.Secure.putInt(context.getContentResolver(), "quiet_repeat_type", i);
        }

        public static void setQuietWristband(Context context, boolean flag)
        {
            context = context.getContentResolver();
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            Settings.Secure.putInt(context, "quiet_wristband", i);
        }

        public static void setRepeatedCallActionEnable(Context context, boolean flag)
        {
            ExtraNotificationManager.setRepeatedCallEnable(context, flag);
        }

        public static void setSMSClassifierAutoUpdate(Context context, boolean flag)
        {
            miui.provider.ExtraSettings.Secure.putBoolean(context.getContentResolver(), "sms_classifier_auto_update", flag);
        }

        public static void setSMSClassifierUpdateTime(Context context, long l)
        {
            Settings.Secure.putLong(context.getContentResolver(), "sms_classifier_update_time", l);
        }

        public static void setStartTimeForQuietMode(Context context, int i)
        {
            Settings.Secure.putInt(context.getContentResolver(), "start_time_of_qm", i);
        }

        public static void setState(Context context, String s, boolean flag)
        {
            miui.provider.ExtraSettings.Secure.putBoolean(context.getContentResolver(), s, flag);
        }

        public static void setVipCallActionEnable(Context context, boolean flag)
        {
            context = context.getContentResolver();
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            Settings.Secure.putInt(context, "call_act_of_vip", i);
        }

        public static void setVipListForQuietMode(Context context, int i)
        {
            Settings.Secure.putInt(context.getContentResolver(), "vip_list_for_qm", i);
        }

        public static boolean shouldShowGuidingDialog(Context context, int i)
        {
            boolean flag;
            if(((HashMap)mapIdToState.get(Integer.valueOf(1))).get(Integer.valueOf(i)) != null && isMarkNumBlockOpen(context, 1) ^ true && isMarkNumBlockOpen(context, 2) ^ true && isMarkNumBlockSet(context) ^ true)
                flag = isMarkingTypeGuided(context, mapIdToString(i)) ^ true;
            else
                flag = false;
            return flag;
        }

        public static String ACTION_SOURCE_CALL = "action_source_call";
        public static String ACTION_SOURCE_OTHER = "action_source_other";
        public static String ACTION_SOURCE_SMS = "action_source_sms";
        public static final int AGENT = 2;
        public static final String AGENT_NUM_STATE = "agent_num_state";
        public static final String AGENT_NUM_STATE_SIM_2 = "agent_num_state_sim_2";
        public static final String ANTISPAM_ENABLE_FOR_SIM_1 = "antispam_enable_for_sim_1";
        public static final String ANTISPAM_ENABLE_FOR_SIM_2 = "antispam_enable_for_sim_2";
        public static final String ANTISPAM_MODE = "antispam_mode_enable";
        public static final String ANTISPAM_PKG = "com.miui.securitycenter";
        public static final String ANTISPAM_SETTINGS_SHARED_FOR_SIMS = "antispam_settings_shared_for_sims";
        public static final String AUTO_TIMER_OF_QM_ENABLE = "auto_timer_of_qm_enable";
        public static final String CALL_ACT_OF_REPEATED = "call_act_of_repeated";
        private static final String CALL_ACT_OF_VIP = "call_act_of_vip";
        public static final String CALL_TRANSFER_NUM_STATE = "call_transfer_num_state";
        public static final String CONTACT_CALL_MODE = "contact_call_mode";
        public static final String CONTACT_SMS_MODE = "contact_sms_mode";
        public static final int DISABLE = 0;
        public static final String EMPTY_CALL_MODE = "empty_call_mode";
        public static final int ENABLE = 1;
        private static final String END_TIME_OF_QM = "end_time_of_qm";
        public static final int FRAUD = 1;
        public static final String FRAUD_NUM_STATE = "fraud_num_state";
        public static final String FRAUD_NUM_STATE_SIM_2 = "fraud_num_state_sim_2";
        public static final int GUIDE_TYPE_DECLINE = 1;
        public static final int GUIDE_TYPE_END_CALL = 2;
        public static final int GUIDE_TYPE_MANUAL_MARK = 3;
        public static final int HARASS = 10;
        public static final String HARASS_NUM_STATE = "harass_num_state";
        public static final String HARASS_NUM_STATE_SIM_2 = "harass_num_state_sim_2";
        private static final String HAS_NEW_ANTISPAM = "has_new_antispam";
        public static final int IS_BLACKLIST_NOTIFICATION = 0;
        public static String KEY_ANTISPAM_ACTION_SOURCE = "antispam_action_source";
        public static final String KEY_SIM_ID = "key_sim_id";
        public static final String MARK_GUIDE_AGENT = "mark_guide_agent";
        public static final String MARK_GUIDE_FRAUD = "mark_guide_fraud";
        public static final String MARK_GUIDE_HARASS = "mark_guide_harass";
        public static final String MARK_GUIDE_IS_SET = "mark_guide_is_set";
        public static final String MARK_GUIDE_SELL = "mark_guide_sell";
        public static final String MARK_GUIDE_TYPE = "mark_guide_type";
        public static final String MARK_GUIDE_YELLOWPAGE_CID = "mark_guide_yellowpage_cid";
        public static final String MARK_NUM_GUIDE_CLASS = "com.miui.antispam.ui.activity.MarkNumGuideActivity";
        public static final int MARK_PROVIDER_ID_MIUI = 398;
        public static final String MARK_TIME_AGENT = "mark_time_agent";
        public static final String MARK_TIME_AGENT_SIM_2 = "mark_time_agent_sim_2";
        public static final int MARK_TIME_DEFAULT = 50;
        public static final String MARK_TIME_FRAUD = "mark_time_fraud";
        public static final String MARK_TIME_FRAUD_SIM_2 = "mark_time_fraud_sim_2";
        public static final String MARK_TIME_HARASS = "mark_time_harass";
        public static final String MARK_TIME_HARASS_SIM_2 = "mark_time_harass_sim_2";
        public static final String MARK_TIME_SELL = "mark_time_sell";
        public static final String MARK_TIME_SELL_SIM_2 = "mark_time_sell_sim_2";
        private static final String NEXT_AUTO_END_TIME_OF_QM = "next_auto_end_time_of_qm";
        private static final String NEXT_AUTO_START_TIME_OF_QM = "next_auto_start_time_of_qm";
        public static final String NOTIFICATION_BLOCK_TYPE = "notification_block_type";
        public static final String NOTIFICATION_INTERCEPT_NUMBER = "notification_intercept_number";
        public static final String NOTIFICATION_SHOW_TYPE = "notification_show_type";
        public static final int NOT_BLACKLIST_NOTIFICATION = 1;
        public static final String OVERSEA_CALL_MODE = "oversea_call_mode";
        public static final String QUIET_MODE_ENABLE = "quiet_mode_enable";
        private static final String QUIET_REPEAT_TYPE = "quiet_repeat_type";
        public static final String QUIET_WRISTBAND = "quiet_wristband";
        public static final String REPEATED_MARK_NUM_PERMISSION = "repeated_mark_num_permission";
        public static final int SELL = 3;
        public static final String SELL_NUM_STATE = "sell_num_state";
        public static final String SELL_NUM_STATE_SIM_2 = "sell_num_state_sim_2";
        public static final String SERVICE_SMS_MODE = "service_sms_mode";
        public static final int SHOW_ALL = 0;
        public static final int SHOW_NONE = 2;
        public static final String SHOW_NOTIFICATION_TYPE = "show_notification_type";
        public static final String SHOW_NOTIFICATION_TYPE_SIM_2 = "show_notification_type_sim_2";
        public static final int SHOW_NOT_BLACKLIST = 1;
        public static final int SIM_ID_1 = 1;
        public static final int SIM_ID_2 = 2;
        public static final String SMS_CLASSIFIER_AUTO_UPDATE = "sms_classifier_auto_update";
        public static final String SMS_CLASSIFIER_UPDATE_TIME = "sms_classifier_update_time";
        private static final String START_TIME_OF_QM = "start_time_of_qm";
        public static final String STRANGER_CALL_MODE = "stranger_call_mode";
        public static final String STRANGER_SMS_MODE = "stranger_sms_mode";
        public static final int VIP_ALL_CONTACTS = 0;
        public static final int VIP_CUSTOM = 2;
        private static final String VIP_LIST_FOR_QM = "vip_list_for_qm";
        public static final int VIP_STAR_CONTACTS = 1;
        public static final HashMap mapIdToBlockType = new HashMap() {

            
            {
                put(Integer.valueOf(1), Integer.valueOf(8));
                put(Integer.valueOf(2), Integer.valueOf(10));
                put(Integer.valueOf(3), Integer.valueOf(12));
                put(Integer.valueOf(10), Integer.valueOf(14));
            }
        }
;
        public static final HashMap mapIdToMarkTime = new HashMap() {

            
            {
                put(Integer.valueOf(1), new HashMap());
                put(Integer.valueOf(2), new HashMap());
                ((HashMap)get(Integer.valueOf(1))).put(Integer.valueOf(1), "mark_time_fraud");
                ((HashMap)get(Integer.valueOf(1))).put(Integer.valueOf(2), "mark_time_agent");
                ((HashMap)get(Integer.valueOf(1))).put(Integer.valueOf(3), "mark_time_sell");
                ((HashMap)get(Integer.valueOf(1))).put(Integer.valueOf(10), "mark_time_harass");
                ((HashMap)get(Integer.valueOf(2))).put(Integer.valueOf(1), "mark_time_fraud_sim_2");
                ((HashMap)get(Integer.valueOf(2))).put(Integer.valueOf(2), "mark_time_agent_sim_2");
                ((HashMap)get(Integer.valueOf(2))).put(Integer.valueOf(3), "mark_time_sell_sim_2");
                ((HashMap)get(Integer.valueOf(2))).put(Integer.valueOf(10), "mark_time_harass_sim_2");
            }
        }
;
        public static final HashMap mapIdToState = new HashMap() {

            
            {
                put(Integer.valueOf(1), new HashMap());
                put(Integer.valueOf(2), new HashMap());
                ((HashMap)get(Integer.valueOf(1))).put(Integer.valueOf(1), "fraud_num_state");
                ((HashMap)get(Integer.valueOf(1))).put(Integer.valueOf(2), "agent_num_state");
                ((HashMap)get(Integer.valueOf(1))).put(Integer.valueOf(3), "sell_num_state");
                ((HashMap)get(Integer.valueOf(1))).put(Integer.valueOf(10), "harass_num_state");
                ((HashMap)get(Integer.valueOf(2))).put(Integer.valueOf(1), "fraud_num_state_sim_2");
                ((HashMap)get(Integer.valueOf(2))).put(Integer.valueOf(2), "agent_num_state_sim_2");
                ((HashMap)get(Integer.valueOf(2))).put(Integer.valueOf(3), "sell_num_state_sim_2");
                ((HashMap)get(Integer.valueOf(2))).put(Integer.valueOf(10), "harass_num_state_sim_2");
            }
        }
;


        public AntiSpam()
        {
        }
    }

    public static final class AntiVirus
    {

        public static boolean isInstallMonitorEnabled(Context context)
        {
            return miui.provider.ExtraSettings.System.getBoolean(context.getContentResolver(), "virus_scan_install", true);
        }

        public static void setInstallMonitorEnabled(Context context, boolean flag)
        {
            miui.provider.ExtraSettings.System.putBoolean(context.getContentResolver(), "virus_scan_install", flag);
        }

        private static final String INSTALL_MONITOR_ENABLED = "virus_scan_install";

        public AntiVirus()
        {
        }
    }

    public static final class ForceTouch
    {

        private static void checkHighend()
        {
            if("capricorn".equals(SystemProperties.get("ro.product.device", "")))
            {
                long l = getTotalInternalMemory();
                long l1 = HardwareInfo.getTotalPhysicalMemory();
                if(l < 0x1dcd650000L || l1 < 0xee6b2800L)
                    mSupportForceTouch = 0;
            }
        }

        public static float getDeepPressure()
        {
            if(mDeepPress < 0.0F)
                mDeepPress = FeatureParser.getFloat("force_touch_deep", 0.8F).floatValue();
            return mDeepPress;
        }

        public static float getLightPressure()
        {
            if(mLightPress < 0.0F)
                mLightPress = FeatureParser.getFloat("force_touch_light", 0.4F).floatValue();
            return mLightPress;
        }

        private static long getTotalInternalMemory()
        {
            long l = 0L;
            Object obj = new ArrayList();
            ((ArrayList) (obj)).add(Environment.getDataDirectory());
            if(!"mixed".equals(SystemProperties.get("ro.boot.sdcard.type", "mixed")))
            {
                String s = Environment.getExternalStorageState();
                if("mounted".equals(s) || "mounted_ro".equals(s))
                    ((ArrayList) (obj)).add(Environment.getExternalStorageDirectory());
            }
            for(obj = ((Iterable) (obj)).iterator(); ((Iterator) (obj)).hasNext();)
                l += ((File)((Iterator) (obj)).next()).getTotalSpace();

            if(l >= 0x1dcd65000L)
                l = (long)Math.pow(2D, Math.ceil(Math.log(l / 0x3b9aca00L) / Math.log(2D))) * 0x3b9aca00L;
            else
                l = (l / 0x3b9aca00L + 1L) * 0x3b9aca00L;
            return l;
        }

        public static boolean isEnabled(Context context)
        {
            boolean flag = true;
            if(!isSupport())
                return false;
            if(Settings.System.getInt(context.getContentResolver(), "force_touch_enable", 1) == 0)
                flag = false;
            return flag;
        }

        public static boolean isSupport()
        {
            boolean flag = true;
            if(mSupportForceTouch < 0)
            {
                int i;
                if(FeatureParser.getBoolean("support_force_touch", false))
                    i = 1;
                else
                    i = 0;
                mSupportForceTouch = i;
                if(mSupportForceTouch == 1)
                    checkHighend();
            }
            if(mSupportForceTouch != 1)
                flag = false;
            return flag;
        }

        public static boolean setEnabled(Context context, boolean flag)
        {
            int i = 0;
            if(!isSupport())
                return false;
            context = context.getContentResolver();
            if(flag)
                i = 1;
            Settings.System.putInt(context, "force_touch_enable", i);
            return true;
        }

        private static final String ForceTouchEnable = "force_touch_enable";
        private static float mDeepPress = -1F;
        private static float mLightPress = -1F;
        private static int mSupportForceTouch = -1;


        public ForceTouch()
        {
        }
    }

    public static final class FrequentPhrases
    {

        public static ArrayList getFrequentPhrases(Context context)
        {
            context = miui.provider.ExtraSettings.System.getString(context.getContentResolver(), "frequent_phrases");
            if(TextUtils.isEmpty(context))
                return null;
            try
            {
                context = new JSONObject(context);
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                context.printStackTrace();
                return null;
            }
            context = context.optJSONArray("phrases");
            if(context == null || context.length() == 0)
                return null;
            ArrayList arraylist = new ArrayList();
            int i = 0;
            while(i < context.length()) 
            {
                String s = context.optString(i);
                if(!TextUtils.isEmpty(s))
                    arraylist.add(s);
                i++;
            }
            return arraylist;
        }

        public static void setFrequentPhrases(Context context, ArrayList arraylist)
        {
            if(arraylist != null && arraylist.size() != 0) goto _L2; else goto _L1
_L1:
            miui.provider.ExtraSettings.System.putString(context.getContentResolver(), "frequent_phrases", "");
_L4:
            return;
_L2:
            JSONArray jsonarray = new JSONArray();
            for(int i = 0; i < arraylist.size(); i++)
                jsonarray.put(arraylist.get(i));

            arraylist = new JSONObject();
            try
            {
                arraylist.put("phrases", jsonarray);
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                context.printStackTrace();
                return;
            }
            miui.provider.ExtraSettings.System.putString(context.getContentResolver(), "frequent_phrases", arraylist.toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        private static final String FREQUENT_PHRASES = "frequent_phrases";
        private static final String TAG_PHRASES = "phrases";

        public FrequentPhrases()
        {
        }
    }

    public static class Global
    {

        public static boolean changeOpenSecondSpaceStatusIcon(ContentResolver contentresolver, boolean flag)
        {
            return putBoolean(contentresolver, "open_second_space_status_icon", flag);
        }

        public static boolean changePrivacyContactMode(ContentResolver contentresolver, boolean flag)
        {
            return putBoolean(contentresolver, "open_privacy_contact_in_second_space", flag);
        }

        public static boolean getBoolean(ContentResolver contentresolver, String s)
        {
            boolean flag = false;
            if(Settings.Global.getInt(contentresolver, s, 0) != 0)
                flag = true;
            return flag;
        }

        public static boolean isOpenSecondSpaceStatusIcon(ContentResolver contentresolver)
        {
            boolean flag = true;
            if(Settings.Global.getInt(contentresolver, "open_second_space_status_icon", 1) == 0)
                flag = false;
            return flag;
        }

        public static boolean isOpenedPrivacyContactMode(ContentResolver contentresolver)
        {
            return getBoolean(contentresolver, "open_privacy_contact_in_second_space");
        }

        public static boolean putBoolean(ContentResolver contentresolver, String s, boolean flag)
        {
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            return Settings.Global.putInt(contentresolver, s, i);
        }

        public static final String CAN_NAV_BAR_HIDE = "can_nav_bar_hide";
        public static final String FORCE_FSG_NAV_BAR = "force_fsg_nav_bar";
        public static final String FORCE_IMMERSIVE_NAV_BAR = "force_immersive_nav_bar";
        public static final String HIDE_NAV_BAR = "hide_nav_bar";
        public static final String OPEN_PRIVACY_CONTACT_IN_SECOND_SPACE = "open_privacy_contact_in_second_space";
        public static final String OPEN_SECOND_SPACE_STATUS_ICON = "open_second_space_status_icon";
        public static final String POLICY_CONTROL_DEFAULT = "immersive.preconfirms=*";
        public static final String POLICY_CONTROL_IMMERSIVE_NAV_BAR = "immersive.navigation=*:immersive.preconfirms=*";
        public static final String SHOW_ASSISTANT_BUTTON = "show_assistant_button";
        public static final String SHOW_GESTURE_BACK_ANIMATION = "show_gesture_back_animation";

        public Global()
        {
        }
    }

    public static final class Key
    {

        public static String getKeyAndGestureShortcutFunction(Context context, String s)
        {
            String s1;
            s1 = Settings.System.getStringForUser(context.getContentResolver(), s, -2);
            context = s1;
            if(!TextUtils.isEmpty(s1)) goto _L2; else goto _L1
_L1:
            if(!"double_click_power_key".equals(s)) goto _L4; else goto _L3
_L3:
            context = null;
_L2:
            return context;
_L4:
            if("three_gesture_down".equals(s))
                context = "screen_shot";
            else
            if("long_press_home_key".equals(s))
            {
                if(!Build.IS_GLOBAL_BUILD)
                    context = "launch_voice_assistant";
                else
                    context = "launch_google_search";
            } else
            if("long_press_menu_key".equals(s))
                context = "show_menu";
            else
            if("long_press_menu_key_when_lock".equals(s))
                context = "turn_on_torch";
            else
            if("long_press_back_key".equals(s))
                context = null;
            else
            if("key_combination_power_home".equals(s))
                context = null;
            else
            if("key_combination_power_back".equals(s))
            {
                context = null;
            } else
            {
                context = s1;
                if("key_combination_power_menu".equals(s))
                    context = null;
            }
            if(true) goto _L2; else goto _L5
_L5:
        }

        public static boolean isTSMClientInstalled(Context context)
        {
            context = context.getPackageManager();
            if(context == null)
                break MISSING_BLOCK_LABEL_24;
            context = context.getPackageInfo("com.miui.tsmclient", 0);
            if(context != null)
                return true;
            break MISSING_BLOCK_LABEL_24;
            context;
            return false;
        }

        public static void updateOldKeyFunctionToNew(Context context)
        {
            if(Settings.System.getIntForUser(context.getContentResolver(), "key_updated", 0, -2) == 0)
            {
                String s = System.getScreenKeyLongPressAction(context, "screen_key_long_press_app_switch");
                String s1 = System.getScreenKeyLongPressAction(context, "screen_key_long_press_home");
                String s2 = System.getScreenKeyLongPressAction(context, "screen_key_long_press_back");
                if("voice_assistant".equals(s1))
                    Settings.System.putStringForUser(context.getContentResolver(), "long_press_home_key", "launch_voice_assistant", -2);
                else
                if("voice_assistant".equals(s2))
                    Settings.System.putStringForUser(context.getContentResolver(), "long_press_back_key", "launch_voice_assistant", -2);
                else
                if("voice_assistant".equals(s))
                    Settings.System.putStringForUser(context.getContentResolver(), "long_press_menu_key", "launch_voice_assistant", -2);
                if("close_app".equals(s2))
                    Settings.System.putStringForUser(context.getContentResolver(), "long_press_back_key", "close_app", -2);
                else
                if("close_app".equals(s1))
                    Settings.System.putStringForUser(context.getContentResolver(), "long_press_home_key", "close_app", -2);
                else
                if("close_app".equals(s))
                    Settings.System.putStringForUser(context.getContentResolver(), "long_press_menu_key", "close_app", -2);
                Settings.System.putIntForUser(context.getContentResolver(), "key_updated", 1, -2);
            }
        }

        public static final String CLOSE_APP = "close_app";
        public static final String DOUBLE_CLICK_POWER_KEY = "double_click_power_key";
        public static final String GO_TO_SLEEP = "go_to_sleep";
        public static final String KEY_BANK_CARD = "key_bank_card_in_ese";
        public static final int KEY_BANK_CARD_DISABLE = 0;
        public static final String KEY_COMBINATION_POWER_BACK = "key_combination_power_back";
        public static final String KEY_COMBINATION_POWER_HOME = "key_combination_power_home";
        public static final String KEY_COMBINATION_POWER_MENU = "key_combination_power_menu";
        public static final String KEY_NONE = "key_none";
        public static final String KEY_TRANS_CARD = "key_trans_card_in_ese";
        public static final int KEY_TRANS_CARD_DISABLE = 0;
        public static final String KEY_UPDATED = "key_updated";
        public static final String LAUNCH_CAMERA = "launch_camera";
        public static final String LAUNCH_GOOGLE_SEARCH = "launch_google_search";
        public static final String LAUNCH_RECENTS = "launch_recents";
        public static final String LAUNCH_VOICE_ASSISTANT = "launch_voice_assistant";
        public static final String LONG_PRESS_BACK_KEY = "long_press_back_key";
        public static final String LONG_PRESS_HOME_KEY = "long_press_home_key";
        public static final String LONG_PRESS_MENU_KEY = "long_press_menu_key";
        public static final String LONG_PRESS_MENU_KEY_WHEN_LOCK = "long_press_menu_key_when_lock";
        public static final String LONG_PRESS_POWER_LAUNCH_XIAOAI = "long_press_power_launch_xiaoai";
        public static final int LONG_PRESS_POWER_LAUNCH_XIAOAI_DISABLE = 0;
        public static final int LONG_PRESS_POWER_LAUNCH_XIAOAI_ENABLE = 1;
        public static final String LONG_PRESS_VOLUME_DOWN = "key_long_press_volume_down";
        public static final String LONG_PRESS_VOLUME_DOWN_DEFAULT = "none";
        public static final String LONG_PRESS_VOLUME_DOWN_PAY = "public_transportation_shortcuts";
        public static final String LONG_PRESS_VOLUME_DOWN_STREET_SNAP = "Street-snap";
        public static final String LONG_PRESS_VOLUME_DOWN_STREET_SNAP_MOVIE = "Street-snap-movie";
        public static final String LONG_PRESS_VOLUME_DOWN_STREET_SNAP_PICTURE = "Street-snap-picture";
        public static final String MI_PAY = "mi_pay";
        public static final String NONE = "none";
        public static final String SCREEN_SHOT = "screen_shot";
        public static final String SEND_BACK_WHEN_XIAOAI_APPEAR = "send_back_when_xiaoai_appear";
        public static final String SHOW_MENU = "show_menu";
        public static final String SINGLE_KEY_USE_ACTION = "single_key_use_enable";
        public static final int SINGLE_KEY_USE_DISABLE = 0;
        public static final int SINGLE_KEY_USE_ENABLE = 1;
        public static final String SPLIT_SCREEN = "split_screen";
        public static final String THREE_GESTURE_DOWN = "three_gesture_down";
        public static final String TURN_ON_TORCH = "turn_on_torch";
        public static final String VOLUMEKEY_LAUNCH_CAMERA = "volumekey_launch_camera";

        public Key()
        {
        }
    }

    public static final class MiuiVoip
    {

        public static int getVoipContactCount(Context context)
        {
            return miui.provider.ExtraSettings.System.getInt(context.getContentResolver(), "miui_voip_contact_count", 0);
        }

        public static int getVoipNewContactCount(Context context)
        {
            return miui.provider.ExtraSettings.System.getInt(context.getContentResolver(), "miui_voip_new_contact_count", 0);
        }

        public static boolean isVoipActivated(Context context)
        {
            return miui.provider.ExtraSettings.System.getBoolean(context.getContentResolver(), "miui_voip_activated", false);
        }

        public static boolean isVoipCallLogAuto(Context context)
        {
            return miui.provider.ExtraSettings.System.getBoolean(context.getContentResolver(), "miui_voip_calllog_auto", false);
        }

        public static boolean isVoipEnabled(Context context)
        {
            return miui.provider.ExtraSettings.System.getBoolean(context.getContentResolver(), "miui_voip_enabled", false);
        }

        public static boolean isVoipWifiAuto(Context context)
        {
            return miui.provider.ExtraSettings.System.getBoolean(context.getContentResolver(), "miui_voip_wifi_auto", false);
        }

        public static void setVoipActivated(Context context, boolean flag)
        {
            miui.provider.ExtraSettings.System.putBoolean(context.getContentResolver(), "miui_voip_activated", flag);
        }

        public static void setVoipCallLogAuto(Context context, boolean flag)
        {
            miui.provider.ExtraSettings.System.putBoolean(context.getContentResolver(), "miui_voip_calllog_auto", flag);
        }

        public static void setVoipContactCount(Context context, int i)
        {
            miui.provider.ExtraSettings.System.putInt(context.getContentResolver(), "miui_voip_contact_count", i);
        }

        public static void setVoipEnabled(Context context, boolean flag)
        {
            miui.provider.ExtraSettings.System.putBoolean(context.getContentResolver(), "miui_voip_enabled", flag);
        }

        public static void setVoipNewContactCount(Context context, int i)
        {
            miui.provider.ExtraSettings.System.putInt(context.getContentResolver(), "miui_voip_new_contact_count", i);
        }

        public static void setVoipWifiAuto(Context context, boolean flag)
        {
            miui.provider.ExtraSettings.System.putBoolean(context.getContentResolver(), "miui_voip_wifi_auto", flag);
        }

        private static final String MIUI_VOIP_ACTIVATED = "miui_voip_activated";
        private static final String MIUI_VOIP_CALLLOG_AUTO = "miui_voip_calllog_auto";
        private static final String MIUI_VOIP_CONTACT_COUNT = "miui_voip_contact_count";
        private static final String MIUI_VOIP_ENABLED = "miui_voip_enabled";
        private static final String MIUI_VOIP_NEW_CONTACT_COUNT = "miui_voip_new_contact_count";
        private static final String MIUI_VOIP_WIFI_AUTO = "miui_voip_wifi_auto";

        public MiuiVoip()
        {
        }
    }

    public static final class Privacy
    {

        public static boolean isEnabled(Context context, String s)
        {
            return ((SecurityManager)context.getSystemService("security")).isAppPrivacyEnabled(s);
        }

        public static void setEnabled(Context context, String s, boolean flag)
        {
            ((SecurityManager)context.getSystemService("security")).setAppPrivacyStatus(s, flag);
        }

        public static final String ACTION_PRIVACY_AUTHORIZATION_DIALOG = "miui.intent.action.PRIVACY_AUTHORIZATION_DIALOG";
        public static final String PRIVACY_PREFIX = "privacy_status_";

        public Privacy()
        {
        }
    }

    public static final class ScreenEffect
    {

        private static int getDefaultSaturation()
        {
            byte byte0 = 10;
            if(FeatureParser.getBoolean("is_hongmi", false))
                byte0 = 11;
            return FeatureParser.getInteger("display_ce", byte0);
        }

        private static int getDefaultScreenOptimizeMode()
        {
            byte byte0;
            if((SCREEN_EFFECT_SUPPORTED & 1) == 0)
                byte0 = 2;
            else
                byte0 = 1;
            return byte0;
        }

        public static HashMap getScreenModePkgList(Context context, String s)
        {
            s = Settings.System.getStringForUser(context.getContentResolver(), s, -2);
            context = new HashMap();
            if(!TextUtils.isEmpty(s))
            {
                String as[] = s.split(",");
                if(as != null)
                {
                    for(int i = 0; i < as.length; i++)
                    {
                        s = as[i];
                        int j = s.indexOf('=');
                        context.put(s.substring(0, j), Boolean.valueOf(s.substring(j + 1)));
                    }

                }
            }
            return context;
        }

        public static boolean isInPaperModeTimeSchedule(Context context, int i, int j)
        {
            int k;
            boolean flag;
            context = Calendar.getInstance();
            k = context.get(11) * 60 + context.get(12);
            flag = false;
            if(i <= j) goto _L2; else goto _L1
_L1:
            if(k >= j && k < i)
                flag = false;
            else
                flag = true;
_L4:
            return flag;
_L2:
            if(i < j)
                if(k >= i && k < j)
                    flag = true;
                else
                    flag = false;
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static boolean isScreenPaperMode()
        {
            boolean flag = false;
            if(DisplayFeatureManager.getInstance().getEyeCare() > 0)
                flag = true;
            return flag;
        }

        public static void setScreenModePkgList(Context context, HashMap hashmap, String s)
        {
            if(hashmap == null || hashmap.size() == 0)
                return;
            StringBuilder stringbuilder = new StringBuilder();
            hashmap = hashmap.entrySet().iterator();
            do
            {
                if(!hashmap.hasNext())
                    break;
                java.util.Map.Entry entry = (java.util.Map.Entry)hashmap.next();
                stringbuilder.append((String)entry.getKey());
                stringbuilder.append('=');
                stringbuilder.append((Boolean)entry.getValue());
                if(hashmap.hasNext())
                    stringbuilder.append(",");
            } while(true);
            Settings.System.putString(context.getContentResolver(), s, stringbuilder.toString());
        }

        public static void setScreenPaperMode(boolean flag)
        {
            if(flag)
            {
                int i = SystemProperties.getInt("persist.sys.eyecare_cache", DEFAULT_PAPER_MODE_LEVEL);
                DisplayFeatureManager.getInstance().setEyeCare(i);
            } else
            {
                DisplayFeatureManager.getInstance().setEyeCare(0);
            }
        }

        public static final int DEFALUT_SCREEN_COLOR = 2;
        public static final int DEFAULT_PAPER_MODE_LEVEL;
        public static final int DEFAULT_SCREEN_OPTIMIZE_MODE = getDefaultScreenOptimizeMode();
        public static final int DEFAULT_SCREEN_SATURATION = getDefaultSaturation();
        public static final int FLAG_SUPPORT_ADAPT_MODE = 1;
        public static final int FLAG_SUPPORT_ENHANCE_MODE = 2;
        public static final int FLAG_SUPPORT_MONOCHROME_MODE = 8;
        public static final int FLAG_SUPPORT_STANDARD_MODE = 4;
        public static final int MONOCHROME_MODE_DEFAULT = 2;
        public static final int MONOCHROME_MODE_GLOBAL = 1;
        public static final int MONOCHROME_MODE_LOCAL = 2;
        public static final String NIGHT_LIGHT_LEVEL = "night_light_level";
        public static final int PAPER_MODE_AUTO_TWILIGHT = 1;
        public static final int PAPER_MODE_CUSTOMIZE_TIME = 2;
        public static final int PAPER_MODE_DEFAULT = 1;
        public static final int PAPER_MODE_GLOBAL = 1;
        public static final int PAPER_MODE_LOCAL = 2;
        public static final int PAPER_MODE_MAX_LEVEL;
        public static final String PAPER_MODE_SCHEDULER_TYPE = "paper_mode_scheduler_type";
        public static final int PAPER_MODE_TIME_CANCEL = 0;
        public static final String PROPERTY_SCREEN_PAPER_MODE_LEVEL = "persist.sys.eyecare_cache";
        public static final int SCREEN_COLOR_COOL = 3;
        public static final String SCREEN_COLOR_LEVEL = "screen_color_level";
        public static final int SCREEN_COLOR_NATURE = 2;
        public static final int SCREEN_COLOR_WARM = 1;
        public static final int SCREEN_EFFECT_SUPPORTED = FeatureParser.getInteger("screen_effect_supported", 0);
        public static final String SCREEN_MONOCHROME_MODE = "screen_monochrome_mode";
        public static final String SCREEN_MONOCHROME_MODE_ENABLED = "screen_monochrome_mode_enabled";
        public static final boolean SCREEN_MONOCHROME_MODE_ENABLED_DEFAULT = false;
        public static final String SCREEN_MONOCHROME_MODE_WHITE_LIST = "screen_monochrome_mode_white_list";
        public static final int SCREEN_OPTIMIZE_ADAPT = 1;
        public static final int SCREEN_OPTIMIZE_ENHANCE = 2;
        public static final String SCREEN_OPTIMIZE_MODE = "screen_optimize_mode";
        public static final int SCREEN_OPTIMIZE_STANDARD = 3;
        public static final String SCREEN_PAPER_MODE = "screen_paper_mode";
        public static final String SCREEN_PAPER_MODE_ENABLED = "screen_paper_mode_enabled";
        public static final boolean SCREEN_PAPER_MODE_ENABLED_DEFAULT = false;
        public static final String SCREEN_PAPER_MODE_LEVEL = "screen_paper_mode_level";
        public static final String SCREEN_PAPER_MODE_TIME_ENABLED = "screen_paper_mode_time_enabled";
        public static final boolean SCREEN_PAPER_MODE_TIME_ENABLED_DEFAULT = false;
        public static final String SCREEN_PAPER_MODE_TIME_END = "screen_paper_mode_time_end";
        public static final String SCREEN_PAPER_MODE_TIME_START = "screen_paper_mode_time_start";
        public static final String SCREEN_PAPER_MODE_TWILIGHT_END = "screen_paper_mode_twilight_end";
        public static final int SCREEN_PAPER_MODE_TWILIGHT_END_DEAULT = 1080;
        public static final String SCREEN_PAPER_MODE_TWILIGHT_START = "screen_paper_mode_twilight_start";
        public static final int SCREEN_PAPER_MODE_TWILIGHT_START_DEAULT = 360;
        public static final String SCREEN_PAPER_MODE_WHITE_LIST = "screen_paper_mode_white_list";
        public static final int SCREEN_SATURATION_STANDARD = 10;
        public static final int SCREEN_SATURATION_VIVID = 11;
        public static final boolean isScreenPaperModeSupported = FeatureParser.getBoolean("support_screen_paper_mode", false);

        static 
        {
            PAPER_MODE_MAX_LEVEL = SystemProperties.getInt("sys.paper_mode_max_level", Math.round(FeatureParser.getFloat("paper_mode_max_level", 8F).floatValue()));
            DEFAULT_PAPER_MODE_LEVEL = SystemProperties.getInt("sys.paper_mode_default_level", (PAPER_MODE_MAX_LEVEL / 8) * 5);
        }

        public ScreenEffect()
        {
        }
    }

    public static class Secure extends SystemSettings.Secure
    {

        static Intent _2D_wrap0(String s)
        {
            return buildNewPasswordIntent(s);
        }

        private static Intent buildNewPasswordIntent(String s)
        {
            Intent intent = new Intent("android.app.action.SET_NEW_PASSWORD");
            intent.putExtra("set_keyguard_password", false);
            intent.putExtra("common_password_business_key", s);
            return intent;
        }

        public static void changeOpenCrossUserNotification(ContentResolver contentresolver, boolean flag, int i)
        {
            int j;
            if(flag)
                j = 1;
            else
                j = 0;
            Settings.Secure.putIntForUser(contentresolver, "open_cross_user_notification", j, i);
        }

        public static void changeOpenSwitchUserNotification(ContentResolver contentresolver, boolean flag, int i)
        {
            int j;
            if(flag)
                j = 1;
            else
                j = 0;
            Settings.Secure.putIntForUser(contentresolver, "open_switch_user_notification", j, i);
        }

        public static void enableHttpInvokeApp(ContentResolver contentresolver, boolean flag)
        {
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            Settings.Secure.putInt(contentresolver, "http_invoke_app", i);
        }

        public static void enableUploadDebugLog(ContentResolver contentresolver, boolean flag)
        {
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            Settings.Secure.putInt(contentresolver, "upload_debug_log_pref", i);
        }

        public static void enableUserExperienceProgram(ContentResolver contentresolver, boolean flag)
        {
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            Settings.Secure.putInt(contentresolver, "upload_log_pref", i);
        }

        public static boolean getBoolean(ContentResolver contentresolver, String s, boolean flag)
        {
            boolean flag1 = true;
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            if(Settings.Secure.getInt(contentresolver, s, i) != 0)
                flag = flag1;
            else
                flag = false;
            return flag;
        }

        public static int getCtaSupported(ContentResolver contentresolver)
        {
            if(Build.IS_INTERNATIONAL_BUILD)
                return 0;
            else
                return Settings.Secure.getInt(contentresolver, "tst_support", -1);
        }

        public static long getDisableHybridIconTipTS(ContentResolver contentresolver)
        {
            return Settings.Secure.getLong(contentresolver, "ts_user_disable_hybrid_icon_tip", -1L);
        }

        public static int getSecondSpaceEntranceStatus(ContentResolver contentresolver, int i)
        {
            return Settings.Secure.getIntForUser(contentresolver, "second_space_entrance_status", 1, i);
        }

        public static boolean hasCommonPassword(Context context)
        {
            boolean flag = false;
            if(LockPatternUtilsWrapper.getActivePasswordQuality(context) != 0)
                flag = true;
            return flag;
        }

        public static boolean isCommonPasswordEnabledForBusiness(Context context, String s)
        {
            boolean flag = true;
            boolean flag1 = true;
            if("miui_keyguard".equals(s))
            {
                if(LockPatternUtilsWrapper.getActivePasswordQuality(context) == 0)
                    flag1 = false;
                return flag1;
            }
            if(Settings.Secure.getInt(context.getContentResolver(), s, 0) != 0)
                flag1 = flag;
            else
                flag1 = false;
            return flag1;
        }

        public static boolean isFingerprintEnabledForBusiness(Context context, String s)
        {
            boolean flag = false;
            if(Settings.Secure.getInt(context.getContentResolver(), s, 0) == 2)
                flag = true;
            return flag;
        }

        public static boolean isForceCloseDialogEnabled(Context context)
        {
            boolean flag = true;
            boolean flag1 = true;
            int i;
            try
            {
                i = Settings.Secure.getIntForUser(context.getContentResolver(), FORCE_CLOCE_DIALOG_ENABLED, -2);
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                boolean flag2 = flag;
                if("user".equals(Build.TYPE))
                {
                    flag2 = flag;
                    if(!Build.IS_DEVELOPMENT_VERSION)
                        flag2 = Build.IS_INTERNATIONAL_BUILD;
                }
                return flag2;
            }
            if(1 != i)
                flag1 = false;
            return flag1;
        }

        public static boolean isGreenKidActive(ContentResolver contentresolver)
        {
            boolean flag = true;
            if(Settings.Secure.getInt(contentresolver, "green_kid_active", 0) != 1)
                flag = false;
            return flag;
        }

        public static boolean isHttpInvokeAppEnable(ContentResolver contentresolver)
        {
            boolean flag = true;
            if(Settings.Secure.getInt(contentresolver, "http_invoke_app", 1) != 1)
                flag = false;
            return flag;
        }

        public static boolean isOpenCrossUserNotification(ContentResolver contentresolver, int i)
        {
            boolean flag = true;
            if(Settings.Secure.getIntForUser(contentresolver, "open_cross_user_notification", 1, i) == 0)
                flag = false;
            return flag;
        }

        public static boolean isOpenSwitchUserNotification(ContentResolver contentresolver, int i)
        {
            boolean flag = true;
            if(Settings.Secure.getIntForUser(contentresolver, "open_switch_user_notification", 1, i) == 0)
                flag = false;
            return flag;
        }

        public static boolean isSecureSpace(ContentResolver contentresolver)
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(UserHandle.myUserId() != 0)
            {
                flag1 = flag;
                if(Settings.Secure.getInt(contentresolver, "is_second_space", 0) != 0)
                    flag1 = true;
            }
            return flag1;
        }

        public static boolean isTimeChangeDisallow(ContentResolver contentresolver)
        {
            boolean flag = true;
            if(Settings.Secure.getInt(contentresolver, "time_change_disallow", 0) != 1)
                flag = false;
            return flag;
        }

        public static boolean isUploadDebugLogEnable(ContentResolver contentresolver)
        {
            boolean flag = true;
            int i;
            if(isUserExperienceProgramEnable(contentresolver))
                i = 1;
            else
                i = 0;
            if(Settings.Secure.getInt(contentresolver, "upload_debug_log_pref", i) != 1)
                flag = false;
            return flag;
        }

        public static boolean isUserExperienceProgramEnable(ContentResolver contentresolver)
        {
            boolean flag = true;
            int i;
            if(Build.IS_DEVELOPMENT_VERSION)
                i = 1;
            else
                i = 0;
            if(Settings.Secure.getInt(contentresolver, "upload_log_pref", i) != 1)
                flag = false;
            return flag;
        }

        public static void putBoolean(ContentResolver contentresolver, String s, boolean flag)
        {
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            Settings.Secure.putInt(contentresolver, s, i);
        }

        public static void setCtaSupported(ContentResolver contentresolver, int i)
        {
            if(Build.IS_INTERNATIONAL_BUILD)
            {
                return;
            } else
            {
                Settings.Secure.putInt(contentresolver, "tst_support", i);
                return;
            }
        }

        public static boolean setDisableHybridIconTipTS(ContentResolver contentresolver, long l)
        {
            return Settings.Secure.putLong(contentresolver, "ts_user_disable_hybrid_icon_tip", l);
        }

        public static void setSecondSpaceEntranceStatus(ContentResolver contentresolver, boolean flag, int i)
        {
            int j;
            if(flag)
                j = 1;
            else
                j = 0;
            Settings.Secure.putIntForUser(contentresolver, "second_space_entrance_status", j, i);
        }

        public static boolean setTimeChangeDisallow(ContentResolver contentresolver, boolean flag)
        {
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            return Settings.Secure.putInt(contentresolver, "time_change_disallow", i);
        }

        public static void showApplyPasswordConfirmDialog(Activity activity, android.content.DialogInterface.OnClickListener onclicklistener, String s, String s1)
        {
            onclicklistener = new android.content.DialogInterface.OnClickListener(activity, s, onclicklistener) {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    if(i == -1)
                        Settings.Secure.putInt(act.getContentResolver(), businessKey, 2);
                    if(clickListener != null)
                        clickListener.onClick(dialoginterface, i);
                }

                final Activity val$act;
                final String val$businessKey;
                final android.content.DialogInterface.OnClickListener val$clickListener;

            
            {
                act = activity;
                businessKey = s;
                clickListener = onclicklistener;
                super();
            }
            }
;
            (new android.app.AlertDialog.Builder(activity)).setCancelable(false).setIconAttribute(0x1010355).setTitle(activity.getString(0x110800af, new Object[] {
                s1
            })).setMessage(activity.getString(0x110800b0, new Object[] {
                s1
            })).setPositiveButton(0x110800b1, onclicklistener).setNegativeButton(0x110800ab, onclicklistener).create().show();
        }

        private static void showConfirmDialog(Activity activity, android.content.DialogInterface.OnClickListener onclicklistener)
        {
            (new android.app.AlertDialog.Builder(activity)).setCancelable(false).setIconAttribute(0x1010355).setTitle(0x110800ad).setMessage(0x110800ae).setPositiveButton(0x110800ac, onclicklistener).setNegativeButton(0x110800ab, onclicklistener).create().show();
        }

        public static void showSetPasswordConfirmDialog(Activity activity, android.content.DialogInterface.OnClickListener onclicklistener, String s, int i)
        {
            android.content.DialogInterface.OnClickListener onclicklistener1 = JVM INSTR new #11  <Class MiuiSettings$Secure$2>;
            onclicklistener1._cls2(activity, s, i, onclicklistener);
            showConfirmDialog(activity, onclicklistener1);
_L2:
            return;
            activity;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public static void showSetPasswordConfirmDialog(Fragment fragment, android.content.DialogInterface.OnClickListener onclicklistener, String s, int i)
        {
            onclicklistener = new android.content.DialogInterface.OnClickListener(fragment, s, i, onclicklistener) {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    if(i == -1)
                        fragment.startActivityForResult(Secure._2D_wrap0(businessKey), setPasswordRequestCode);
                    if(dialogClickListener != null)
                        dialogClickListener.onClick(dialoginterface, i);
                }

                final String val$businessKey;
                final android.content.DialogInterface.OnClickListener val$dialogClickListener;
                final Fragment val$fragment;
                final int val$setPasswordRequestCode;

            
            {
                fragment = fragment1;
                businessKey = s;
                setPasswordRequestCode = i;
                dialogClickListener = onclicklistener;
                super();
            }
            }
;
            showConfirmDialog(fragment.getActivity(), onclicklistener);
        }

        public static final String ACCESS_CONTROL_LOCK_CONVENIENT = "access_control_lock_convenient";
        public static final String ACCESS_CONTROL_LOCK_ENABLED = "access_control_lock_enabled";
        public static final String ACCESS_CONTROL_LOCK_MODE = "access_control_lock_mode";
        public static final String ACCESS_CONTROL_MASK_NOTIFICATION = "access_control_mask_notification";
        public static String APP_ENCRYPT_PASSWORD = "app_encrypt_password";
        public static final String BATTERY_LIGHT_TURN_ON = "battery_light_turn_on";
        public static final String BLUETOOTH_TRACE_LOG = "bluetooth_trace_log";
        public static final String CHILDREN_MODE_DENY_SMS_PERMISSION = "children_mode_deny_sms_permission";
        public static final String CHILDREN_MODE_ENABLED = "children_mode_enabled";
        public static final String CLOUD_MESSAGING_ON = "cloud_messaging_on";
        public static final int DEFAULT_EXPERIMENTAL_PERMISSION_ALLOW = 1;
        public static final int DEFAULT_FIND_DEVICE_PIN_LOCK = 0;
        public static final String DEFAULT_INPUT_METHOD_CHOOSED = "default_input_method_choosed";
        public static final int DEFAULT_LOCAL_AUTO_BACKUP = 0;
        public static final int DEFAULT_PERMANENTLY_LOCK_SIM_CHANGE = 0;
        public static final int DEFAULT_VPN_ENABLE_PASSWORD = 0;
        public static final String ENABLE_MIKEY_MODE = "enable_mikey_mode";
        public static final String EXPERIMENTAL_PERMISSION_ALLOW = "experimental_permission_allow";
        public static String FIND_DEVICE_PIN_LOCK = "find_device_pin_lock";
        public static String FORCE_CLOCE_DIALOG_ENABLED = "force_close_dialog_enabled";
        public static final String GREEN_KID_ACTIVE = "green_kid_active";
        public static final String HAS_SHOW_GUIDE = "has_show_guide";
        public static final String HTTP_INVOKE_APP = "http_invoke_app";
        public static final String IS_SECOND_SPACE = "is_second_space";
        public static final String KEY_FIRST_ENTER_SECURITY_SPACE = "first_enter_security_space";
        public static final String LOCAL_AUTO_BACKUP = "local_auto_backup";
        public static final String LOCK_SCREEN_SECURE_AFTER_TIMEOUT = "enable_lock_screen_secure_after_timeout";
        public static String MIUI_OPTIMIZATION = "miui_optimization";
        public static final String MOBILE_POLICY = "mobile_policy";
        public static final String OPEN_CROSS_USER_NOTIFICATION = "open_cross_user_notification";
        public static final String OPEN_SWITCH_USER_NOTIFICATION = "open_switch_user_notification";
        public static final String PACKAGE_ACCESSIBILITY_SERVICE_IGNORED = "package_accessibillity_service_ignored";
        public static final String PASSWORD_FOR_PRIVACYMODE = "password_for_privacymode";
        public static String PERMANENTLY_LOCK_SIM_CHANGE = "permanently_lock_sim_change";
        public static final String PRIVACY_PASSWORD_IS_OPEN = "privacy_password_is_open";
        public static final String PRIVATE_GALLERY_LOCK_ENABLED = "private_gallery_lock_enabled";
        public static final String PRIVATE_GALLERY_LOCK_PATTERN_VISIBLE = "private_gallery_lock_pattern_visible_pattern";
        public static final String PRIVATE_SMS_LOCK_ENABLED = "private_sms_lock_enabled";
        public static final String PRIVATE_SMS_LOCK_PATTERN_VISIBLE = "private_sms_lock_pattern_visible_pattern";
        public static String REGISTER_FIND_DEVICE_SIM_NUMBER = "resister_find_device_sim_number";
        public static final String SCREEN_BUTTONS_HAS_BEEN_DISABLED = "screen_buttons_has_been_disabled";
        public static final String SCREEN_BUTTONS_TURN_ON = "screen_buttons_turn_on";
        public static final String SECOND_SPACE_ENTRANCE_STATUS = "second_space_entrance_status";
        public static final String SECOND_USER_ID = "second_user_id";
        public static final String SSPACE_USED = "sSpace_used";
        public static final String SYNC_ON_WIFI_ONLY = "sync_on_wifi_only";
        public static final String TIMESTAMP_USER_DISABLE_HYBRID_ICON_TIP = "ts_user_disable_hybrid_icon_tip";
        public static final String TIME_CHANGE_DISALLOW = "time_change_disallow";
        public static final String TST_SUPPORT = "tst_support";
        public static final int TST_SUPP_NOT_SUPPORT = 0;
        public static final int TST_SUPP_SUPPORT = 1;
        public static final int TST_SUPP_UNKNOWN = -1;
        public static String UNLOCK_FAILED_ATTEMPTS = "unlock_failed_attempts";
        public static final String UPLOAD_DEBUG_LOG = "upload_debug_log_pref";
        public static final String UPLOAD_LOG = "upload_log_pref";
        public static final String USB_MODE = "usb_mode";
        public static final int USB_MODE_ASK_USER = 0;
        public static final int USB_MODE_CHARGE_ONLY = 1;
        public static final int USB_MODE_MOUNT_STORAGE = 2;
        public static final String VPN_ENABLE_PASSWORD = "vpn_password_enable";
        public static final String XSPACE_ENABLED = "xspace_enabled";
        public static final String XSPACE_USED = "xSpace_used";


        public Secure()
        {
        }
    }

    public static final class SettingsCloudData
    {

        public static boolean getCloudDataBoolean(ContentResolver contentresolver, String s, String s1, boolean flag)
        {
            contentresolver = getCloudDataSingle(contentresolver, s, null, null, false);
            if(contentresolver != null)
                return contentresolver.getBoolean(s1, flag);
            else
                return flag;
        }

        public static int getCloudDataInt(ContentResolver contentresolver, String s, String s1, int i)
        {
            contentresolver = getCloudDataSingle(contentresolver, s, null, null, false);
            if(contentresolver != null)
                return contentresolver.getInt(s1, i);
            else
                return i;
        }

        public static List getCloudDataList(ContentResolver contentresolver, String s)
        {
            ContentResolver contentresolver1;
            ContentResolver contentresolver2;
            contentresolver1 = null;
            contentresolver2 = null;
            contentresolver = contentresolver.query(URI_CLOUD_ALL_DATA, new String[] {
                s
            }, null, null, null);
            if(contentresolver == null)
                break MISSING_BLOCK_LABEL_77;
            contentresolver2 = contentresolver;
            contentresolver1 = contentresolver;
            s = contentresolver.getExtras();
            if(s == null)
                break MISSING_BLOCK_LABEL_77;
            contentresolver2 = contentresolver;
            contentresolver1 = contentresolver;
            if(!(s.isEmpty() ^ true))
                break MISSING_BLOCK_LABEL_77;
            contentresolver2 = contentresolver;
            contentresolver1 = contentresolver;
            s = contentresolver.getExtras().getParcelableArrayList("productData");
            IOUtils.closeQuietly(contentresolver);
            return s;
            IOUtils.closeQuietly(contentresolver);
_L2:
            return null;
            contentresolver;
            contentresolver1 = contentresolver2;
            contentresolver.printStackTrace();
            IOUtils.closeQuietly(contentresolver2);
            if(true) goto _L2; else goto _L1
_L1:
            contentresolver;
            IOUtils.closeQuietly(contentresolver1);
            throw contentresolver;
        }

        public static long getCloudDataLong(ContentResolver contentresolver, String s, String s1, long l)
        {
            contentresolver = getCloudDataSingle(contentresolver, s, null, null, false);
            if(contentresolver != null)
                return contentresolver.getLong(s1, l);
            else
                return l;
        }

        public static Uri getCloudDataNotifyUri()
        {
            return URI_CLOUD_ALL_DATA_NOTIFY;
        }

        public static CloudData getCloudDataSingle(ContentResolver contentresolver, String s, String s1, String s2, boolean flag)
        {
            ContentResolver contentresolver1;
            ContentResolver contentresolver2;
            if(s == null)
                throw new NullPointerException("moduleName is null");
            if(flag && (s1 == null || s2 == null))
                throw new NullPointerException("Want cache, but key or propertyName is null");
            contentresolver1 = null;
            contentresolver2 = null;
            contentresolver = contentresolver.query(URI_CLOUD_ALL_DATA_SINGLE, new String[] {
                s, s1, s2, String.valueOf(flag)
            }, null, null, null);
            if(contentresolver == null)
                break MISSING_BLOCK_LABEL_141;
            contentresolver2 = contentresolver;
            contentresolver1 = contentresolver;
            s = contentresolver.getExtras();
            if(s == null)
                break MISSING_BLOCK_LABEL_141;
            contentresolver2 = contentresolver;
            contentresolver1 = contentresolver;
            if(!(s.isEmpty() ^ true))
                break MISSING_BLOCK_LABEL_141;
            contentresolver2 = contentresolver;
            contentresolver1 = contentresolver;
            s = (CloudData)contentresolver.getExtras().getParcelable("productData");
            IOUtils.closeQuietly(contentresolver);
            return s;
            IOUtils.closeQuietly(contentresolver);
_L2:
            return null;
            contentresolver;
            contentresolver1 = contentresolver2;
            contentresolver.printStackTrace();
            IOUtils.closeQuietly(contentresolver2);
            if(true) goto _L2; else goto _L1
_L1:
            contentresolver;
            IOUtils.closeQuietly(contentresolver1);
            throw contentresolver;
        }

        public static String getCloudDataString(ContentResolver contentresolver, String s, String s1, String s2)
        {
            contentresolver = getCloudDataSingle(contentresolver, s, null, null, false);
            if(contentresolver != null)
                return contentresolver.getString(s1, s2);
            else
                return s2;
        }

        public static final String PRODUCT_DATA = "productData";
        private static final Uri URI_CLOUD_ALL_DATA = Uri.parse("content://com.android.settings.cloud.CloudSettings/cloud_all_data");
        private static final Uri URI_CLOUD_ALL_DATA_NOTIFY = Uri.parse("content://com.android.settings.cloud.CloudSettings/cloud_all_data/notify");
        private static final Uri URI_CLOUD_ALL_DATA_SINGLE = Uri.parse("content://com.android.settings.cloud.CloudSettings/cloud_all_data/single");


        public SettingsCloudData()
        {
        }
    }

    public static class SettingsCloudData.CloudData
        implements Parcelable
    {

        private boolean hasKey(String s)
            throws JSONException
        {
            initJson();
            return json.has(s);
        }

        private void initJson()
            throws JSONException
        {
            if(json == null)
                json = new JSONObject(data);
        }

        public int describeContents()
        {
            return 0;
        }

        public boolean equals(Object obj)
        {
            boolean flag = true;
            if(obj == this)
                return true;
            if(!(obj instanceof SettingsCloudData.CloudData))
                return false;
            obj = (SettingsCloudData.CloudData)obj;
            if(data != ((SettingsCloudData.CloudData) (obj)).data)
                if(data != null)
                    flag = data.equals(((SettingsCloudData.CloudData) (obj)).data);
                else
                    flag = false;
            return flag;
        }

        public boolean getBoolean(String s, boolean flag)
        {
            boolean flag1;
            if(!hasKey(s))
                break MISSING_BLOCK_LABEL_24;
            flag1 = json.getBoolean(s);
            return flag1;
            s;
            s.printStackTrace();
            return flag;
        }

        public int getInt(String s, int i)
        {
            int j;
            if(!hasKey(s))
                break MISSING_BLOCK_LABEL_24;
            j = json.getInt(s);
            return j;
            s;
            s.printStackTrace();
            return i;
        }

        public long getLong(String s, long l)
        {
            long l1;
            if(!hasKey(s))
                break MISSING_BLOCK_LABEL_26;
            l1 = json.getLong(s);
            return l1;
            s;
            s.printStackTrace();
            return l;
        }

        public String getString(String s, String s1)
        {
            if(!hasKey(s))
                break MISSING_BLOCK_LABEL_24;
            s = json.getString(s);
            return s;
            s;
            s.printStackTrace();
            return s1;
        }

        public int hashCode()
        {
            int i;
            if(data != null)
                i = data.hashCode();
            else
                i = 0;
            return i;
        }

        public JSONObject json()
        {
            JSONObject jsonobject;
            try
            {
                initJson();
                jsonobject = json;
            }
            catch(JSONException jsonexception)
            {
                jsonexception.printStackTrace();
                return null;
            }
            return jsonobject;
        }

        public String toString()
        {
            return data.toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeString(data);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SettingsCloudData.CloudData createFromParcel(Parcel parcel)
            {
                return new SettingsCloudData.CloudData(parcel.readString());
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public SettingsCloudData.CloudData[] newArray(int i)
            {
                return new SettingsCloudData.CloudData[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private String data;
        private JSONObject json;


        public SettingsCloudData.CloudData(String s)
        {
            data = s;
        }
    }

    public static final class SilenceMode
    {

        public static void enableVIPMode(Context context, boolean flag)
        {
            ExtraNotificationManager.enableVIPMode(context, flag, -3);
        }

        public static void enableVIPMode(Context context, boolean flag, int i)
        {
            ExtraNotificationManager.enableVIPMode(context, flag, i);
        }

        public static int getLastestQuietMode(Context context)
        {
            return 4;
        }

        public static long getRemainTime(Context context)
        {
            return ExtraNotificationManager.getRemainTime(context);
        }

        public static int getZenMode(Context context)
        {
            return ExtraNotificationManager.getZenMode(context);
        }

        public static boolean isDNDEnabled(Context context)
        {
            boolean flag = true;
            if(getZenMode(context) != 1)
                flag = false;
            return flag;
        }

        public static boolean isSilenceModeEnable(Context context)
        {
            if(!isSupported)
                return AntiSpam.isQuietModeEnable(context);
            else
                return ExtraNotificationManager.isSilenceModeEnable(context, -3);
        }

        public static boolean isSilenceModeEnable(Context context, int i)
        {
            return ExtraNotificationManager.isSilenceModeEnable(context, i);
        }

        public static boolean isVIPModeEnable(Context context)
        {
            return ExtraNotificationManager.isVIPModeEnable(context);
        }

        public static boolean muteMusicStream(Context context)
        {
            boolean flag = true;
            if(getZenMode(context) == 1)
            {
                if(1 != Settings.System.getIntForUser(context.getContentResolver(), "mute_music", 1, -3))
                    flag = false;
            } else
            {
                flag = false;
            }
            return flag;
        }

        public static void reportRingerModeInfo(String s, String s1, String s2, long l)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("type=").append(s).append(";");
            stringbuilder.append("mode=").append(s1).append(";");
            stringbuilder.append("params=").append(s2).append(";");
            stringbuilder.append("time=").append(l).append(";");
            if(DEBUG_MODE)
                Log.d("SilenceMode", (new StringBuilder()).append("reportRingerModeInfo:").append(stringbuilder.toString()).toString());
            MQSEventManagerDelegate.getInstance().reportSimpleEvent(77, stringbuilder.toString());
        }

        public static void setRemainTime(Context context, long l)
        {
            Settings.Secure.putLong(context.getContentResolver(), "remain_time", l);
        }

        public static void setSilenceMode(Context context, int i, Uri uri)
        {
            ExtraNotificationManager.setSilenceMode(context, i, uri);
        }

        public static void setSilenceMode(Context context, int i, Uri uri, int j)
        {
            ExtraNotificationManager.setSilenceMode(context, i, uri);
        }

        public static boolean showNotification(Context context)
        {
            boolean flag = true;
            if(getZenMode(context) == 1)
            {
                if(1 != Settings.System.getIntForUser(context.getContentResolver(), "show_notification", 1, -3))
                    flag = false;
            } else
            {
                flag = false;
            }
            return flag;
        }

        protected static final boolean DEBUG_MODE = Log.isLoggable("SilenceMode.MOD", 3);
        public static final String LASTEST_MODE = "default_mode";
        public static final String MISTAT_RINGERMODE_LIST[] = {
            "normal", "DND", "null", "silence", "new"
        };
        public static final String MISTAT_SILENCE_DND = "silence_DND";
        public static final int MIUI_ALARMS = 3;
        public static final int MIUI_IMPORTANT_INTERRUPTIONS = 1;
        public static final int MIUI_NO_INTERRUPTIONS = 2;
        public static final String MUTE_MUSIC = "mute_music";
        public static final int NORMAL = 0;
        public static final String REMAIN_TIME = "remain_time";
        public static final String SHOW_NOTIFICATION = "show_notification";
        public static final String SILENCE_MODE = "zen_mode";
        private static final String TAG = "SilenceMode";
        public static final String VIP_ENABLE = "vip_enable";
        public static final int ZEN_MODE_MIUI_SILENT = 4;
        public static final boolean isSupported;

        static 
        {
            boolean flag;
            if(android.os.Build.VERSION.SDK_INT >= 24)
                flag = FeatureParser.getBoolean("support_new_silentmode", false);
            else
                flag = false;
            isSupported = flag;
        }

        public SilenceMode()
        {
        }
    }

    public static class System extends SystemSettings.System
    {

        private static int[] _2D_getandroid_2D_provider_2D_MiuiSettings$System$SmallWindowTypeSwitchesValues()
        {
            if(_2D_android_2D_provider_2D_MiuiSettings$System$SmallWindowTypeSwitchesValues != null)
                return _2D_android_2D_provider_2D_MiuiSettings$System$SmallWindowTypeSwitchesValues;
            int ai[] = new int[SmallWindowType.values().length];
            try
            {
                ai[SmallWindowType.A1_STYLE.ordinal()] = 1;
            }
            catch(NoSuchFieldError nosuchfielderror3) { }
            try
            {
                ai[SmallWindowType.A7_LATTICE.ordinal()] = 2;
            }
            catch(NoSuchFieldError nosuchfielderror2) { }
            try
            {
                ai[SmallWindowType.B7_FULL.ordinal()] = 3;
            }
            catch(NoSuchFieldError nosuchfielderror1) { }
            try
            {
                ai[SmallWindowType.X7_STYLE.ordinal()] = 4;
            }
            catch(NoSuchFieldError nosuchfielderror) { }
            _2D_android_2D_provider_2D_MiuiSettings$System$SmallWindowTypeSwitchesValues = ai;
            return ai;
        }

        public static boolean belongToCrossXSpaceSettings(String s, int i)
        {
            boolean flag;
            if(XSpaceUserHandle.isXSpaceUserId(i))
                flag = MiuiSettings.CROSS_PROFILE_SETTINGS.contains(s);
            else
                flag = false;
            return flag;
        }

        private static String flattenOrderToString(List list)
        {
            StringBuilder stringbuilder = new StringBuilder();
            int i = list.size();
            int j = 0;
            do
            {
                stringbuilder.append(((ComponentName)list.get(j)).flattenToShortString());
                if(j == i - 1)
                    return stringbuilder.toString();
                stringbuilder.append(",");
                j++;
            } while(true);
        }

        public static List getActivityResolveOrder(ContentResolver contentresolver)
        {
            return unflattenOrderFromString(getString(contentresolver, "activity_resolve_order"));
        }

        public static boolean getBooleanForUser(ContentResolver contentresolver, String s, boolean flag, int i)
        {
            boolean flag1 = true;
            int j;
            if(flag)
                j = 1;
            else
                j = 0;
            if(Settings.System.getIntForUser(contentresolver, s, j, i) != 0)
                flag = flag1;
            else
                flag = false;
            return flag;
        }

        public static HashSet getDisableWifiAutoConnectSsid(Context context)
        {
            String s = Settings.System.getStringForUser(context.getContentResolver(), "disable_wifi_auto_connect_ssid", -3);
            context = new HashSet();
            if(!TextUtils.isEmpty(s))
            {
                String as[] = s.split(",");
                if(as != null)
                {
                    int i = 0;
                    while(i < as.length) 
                    {
                        try
                        {
                            String s1 = JVM INSTR new #764 <Class String>;
                            s1.String(Base64.decode(as[i], 2));
                            context.add(s1);
                        }
                        catch(IllegalArgumentException illegalargumentexception) { }
                        i++;
                    }
                }
            }
            return context;
        }

        public static Rect getDisplayWindowSizeInSmartCover()
        {
            int ai[];
            SmallWindowType smallwindowtype;
            int ai1[];
            Rect rect;
            Resources resources = Resources.getSystem();
            smallwindowtype = getSmallWindowMode();
            ai1 = new int[4];
            ai1[0] = resources.getInteger(0x1107000f);
            ai1[1] = resources.getInteger(0x11070010);
            ai1[2] = resources.getInteger(0x11070011);
            ai1[3] = resources.getInteger(0x11070012);
            rect = new Rect(ai1[0], ai1[1], ai1[2], ai1[3]);
            if(smallwindowtype == null)
                return rect;
            ai = new int[4];
            ai[0] = FeatureParser.getInteger("smartcover_smallwindow_left", ai1[0]);
            ai[1] = FeatureParser.getInteger("smartcover_smallwindow_top", ai1[1]);
            ai[2] = FeatureParser.getInteger("smartcover_smallwindow_right", ai1[2]);
            ai[3] = FeatureParser.getInteger("smartcover_smallwindow_bottom", ai1[3]);
            ai1 = ai;
            if(!FeatureParser.getBoolean("support_multiple_small_win_cover", false)) goto _L2; else goto _L1
_L1:
            _2D_getandroid_2D_provider_2D_MiuiSettings$System$SmallWindowTypeSwitchesValues()[smallwindowtype.ordinal()];
            JVM INSTR tableswitch 1 4: default 180
        //                       1 220
        //                       2 230
        //                       3 240
        //                       4 210;
               goto _L3 _L4 _L5 _L6 _L7
_L3:
            ai1 = ai;
_L2:
            if(ai1 != null && ai1.length == 4)
                rect.set(ai1[0], ai1[1], ai1[2], ai1[3]);
            return rect;
_L7:
            ai1 = FeatureParser.getIntArray("smartcover_smallwindow_x7_size");
            continue; /* Loop/switch isn't completed */
_L4:
            ai1 = FeatureParser.getIntArray("smartcover_smallwindow_a1_size");
            continue; /* Loop/switch isn't completed */
_L5:
            ai1 = FeatureParser.getIntArray("smartcover_smallwindow_a7_size");
            continue; /* Loop/switch isn't completed */
_L6:
            ai1 = FeatureParser.getIntArray("smartcover_smallwindow_b7_size");
            if(true) goto _L2; else goto _L8
_L8:
        }

        public static int getHapticFeedbackLevel(Context context)
        {
            return Settings.System.getIntForUser(context.getContentResolver(), "haptic_feedback_level", HAPTIC_FEEDBACK_LEVEL_DEFAULT, -3);
        }

        public static Set getHotSpotMacBlackSet(Context context)
        {
            String s = Settings.System.getStringForUser(context.getContentResolver(), "hotspot_mac_black_set", -2);
            context = new HashSet();
            if(!TextUtils.isEmpty(s))
            {
                String as[] = s.split(",");
                if(as != null)
                {
                    int i = 0;
                    while(i < as.length) 
                    {
                        try
                        {
                            String s1 = JVM INSTR new #764 <Class String>;
                            s1.String(Base64.decode(as[i], 2));
                            context.add(s1);
                        }
                        catch(IllegalArgumentException illegalargumentexception) { }
                        i++;
                    }
                }
            }
            return context;
        }

        public static int getHotSpotMaxStationNum(Context context)
        {
            int i = 0;
            int j = Settings.System.getIntForUser(context.getContentResolver(), "hotspot_max_station_num", -2);
            i = j;
_L2:
            return i;
            context;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public static String getHotSpotVendorSpecific(Context context)
        {
            return Settings.System.getStringForUser(context.getContentResolver(), "hotspot_vendor_specific", -2);
        }

        public static String getScreenKeyLongPressAction(Context context, String s)
        {
            String s1;
            String s2;
            s1 = Settings.System.getStringForUser(context.getContentResolver(), s, -2);
            s2 = s1;
            if(!TextUtils.isEmpty(s1)) goto _L2; else goto _L1
_L1:
            s2 = "none";
            context = context.getResources().getStringArray(0x1109000b);
            if(!"screen_key_long_press_menu".equals(s)) goto _L4; else goto _L3
_L3:
            s2 = context[0];
_L2:
            return s2;
_L4:
            if("screen_key_long_press_home".equals(s))
                s2 = context[1];
            else
            if("screen_key_long_press_back".equals(s))
                s2 = context[2];
            else
            if("screen_key_long_press_app_switch".equals(s))
                s2 = context[3];
            if(true) goto _L2; else goto _L5
_L5:
        }

        public static ArrayList getScreenKeyOrder(Context context)
        {
            ArrayList arraylist;
            arraylist = new ArrayList();
            context = Settings.System.getString(context.getContentResolver(), "screen_key_order");
            if(TextUtils.isEmpty(context)) goto _L2; else goto _L1
_L1:
            int i;
            context = context.split(" ");
            i = 0;
_L3:
            if(i >= context.length)
                break; /* Loop/switch isn't completed */
            int j = Integer.valueOf(context[i]).intValue();
            if(screenKeys.contains(Integer.valueOf(j)))
                arraylist.add(Integer.valueOf(j));
            i++;
            if(true) goto _L3; else goto _L2
            context;
            arraylist.clear();
_L2:
            Iterator iterator = screenKeys.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                context = (Integer)iterator.next();
                if(!arraylist.contains(context))
                    arraylist.add(context);
            } while(true);
            return arraylist;
        }

        public static int getShowCustomCarrierDefault()
        {
            int i;
            if(Build.IS_CM_CUSTOMIZATION_TEST || Build.IS_CU_CUSTOMIZATION_TEST || Build.IS_CT_CUSTOMIZATION_TEST || Resources.getSystem().getBoolean(0x110a001a))
                i = 1;
            else
                i = 0;
            return i;
        }

        public static SmallWindowType getSmallWindowMode()
        {
            int j;
            int i = SystemProperties.getInt("persist.sys.smallwin_type", -99);
            j = i;
            if(i == -99)
            {
                int ai[];
                if(FeatureParser.getBoolean("support_multiple_small_win_cover", false))
                {
                    ai = FeatureParser.getIntArray("small_win_cover_type");
                } else
                {
                    ai = new int[1];
                    if(FeatureParser.getBoolean("support_small_win_cover", false))
                        ai[0] = FeatureParser.getInteger("small_win_cover_type", -1);
                    else
                        ai[0] = -1;
                }
                if(ai == null)
                    return null;
                j = ai[0];
            }
            j;
            JVM INSTR tableswitch 0 3: default 108
        //                       0 112
        //                       1 119
        //                       2 126
        //                       3 133;
               goto _L1 _L2 _L3 _L4 _L5
_L1:
            SmallWindowType smallwindowtype = null;
_L7:
            return smallwindowtype;
_L2:
            smallwindowtype = SmallWindowType.X7_STYLE;
            continue; /* Loop/switch isn't completed */
_L3:
            smallwindowtype = SmallWindowType.A1_STYLE;
            continue; /* Loop/switch isn't completed */
_L4:
            smallwindowtype = SmallWindowType.A7_LATTICE;
            continue; /* Loop/switch isn't completed */
_L5:
            smallwindowtype = SmallWindowType.B7_FULL;
            if(true) goto _L7; else goto _L6
_L6:
        }

        public static String getString(ContentResolver contentresolver, String s)
        {
            return Settings.System.getString(contentresolver, s);
        }

        public static String getString(ContentResolver contentresolver, String s, String s1)
        {
            android/provider/MiuiSettings$System;
            JVM INSTR monitorenter ;
            s = Settings.System.getString(contentresolver, s);
            contentresolver = s;
            if(s == null)
                contentresolver = s1;
            android/provider/MiuiSettings$System;
            JVM INSTR monitorexit ;
            return contentresolver;
            contentresolver;
            throw contentresolver;
        }

        public static String getStringForUser(ContentResolver contentresolver, String s, int i)
        {
            return Settings.System.getStringForUser(contentresolver, s, i);
        }

        public static int getT9IndexingKeyDefault()
        {
            int i;
            if(Build.checkRegion("TW"))
                i = 1;
            else
                i = 0;
            return i;
        }

        public static boolean isCdmaPreciseAnswerStateEnabled(Context context)
        {
            boolean flag = true;
            if(Settings.System.getInt(context.getContentResolver(), "cdma_precise_answer_state", 1) != 1)
                flag = false;
            return flag;
        }

        private static boolean isCnFromOperator(String s)
        {
            String s1 = "";
            String s2 = s1;
            if(!TextUtils.isEmpty(s))
            {
                s2 = s1;
                if(s.length() >= 3)
                    s2 = s.substring(0, 3);
            }
            return "460".equals(s2);
        }

        public static boolean isHapticFeedbackDisabled(Context context)
        {
            boolean flag = false;
            if(Settings.System.getIntForUser(context.getContentResolver(), "haptic_feedback_enabled", 0, -3) == 0)
                flag = true;
            return flag;
        }

        public static final boolean isInCnRegion()
        {
            int j;
            TelephonyManager telephonymanager = TelephonyManager.getDefault();
            boolean flag;
            int i;
            String s;
            Object obj;
            if(telephonymanager.getIccCardCount() > 0)
                flag = true;
            else
                flag = false;
            i = telephonymanager.getPhoneCount();
            s = null;
            obj = null;
            if(!flag) goto _L2; else goto _L1
_L1:
            j = 0;
            s = obj;
_L6:
            if(j >= i) goto _L2; else goto _L3
_L3:
            s = telephonymanager.getNetworkOperatorForSlot(j);
            if(TextUtils.isEmpty(s)) goto _L4; else goto _L2
_L2:
            return isCnFromOperator(s) || Build.checkRegion("CN") && (!flag || TextUtils.isEmpty(s));
_L4:
            j++;
            if(true) goto _L6; else goto _L5
_L5:
        }

        public static boolean isInSmallWindowMode(Context context)
        {
            return getBoolean(context.getContentResolver(), "is_small_window", false);
        }

        public static boolean isMiDropEnabled(Context context)
        {
            return getBoolean(context.getContentResolver(), "key_midrop_enabled", false);
        }

        public static boolean isMiuiPublicSettings(PackageInfo packageinfo, String s)
        {
            if(PUBLIC_SETTINGS.contains(s) && (packageinfo.applicationInfo.flags & 1) != 0)
            {
                return true;
            } else
            {
                Slog.d("SystemSettings", "Want to modify SystemSettings? See MiuiSettings.System.PUBLIC_SETTINGS");
                return false;
            }
        }

        public static boolean isSimpleMode(Context context)
        {
            boolean flag = true;
            int i;
            if(Build.IS_INTERNATIONAL_BUILD)
                i = 0;
            else
                i = 1;
            if(Settings.System.getInt(context.getContentResolver(), "simple_mode", i) != 1)
                flag = false;
            return flag;
        }

        public static boolean isTouchAssistantEnabledForUser(Context context, int i, boolean flag)
        {
            flag = true;
            if(Settings.System.getIntForUser(context.getContentResolver(), "touch_assistant_enabled", 0, i) != 1)
                flag = false;
            return flag;
        }

        public static boolean isTouchAssistantTemporaryForUser(Context context, int i, boolean flag)
        {
            if(Settings.System.getIntForUser(context.getContentResolver(), "touch_assistant_enabled", 2, i) == 2)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public static void putActivityResolveOrder(ContentResolver contentresolver, List list)
        {
            putString(contentresolver, "activity_resolve_order", flattenOrderToString(list));
        }

        public static boolean putBoolean(ContentResolver contentresolver, String s, boolean flag)
        {
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            return Settings.System.putInt(contentresolver, s, i);
        }

        public static boolean putBooleanForUser(ContentResolver contentresolver, String s, boolean flag, int i)
        {
            int j;
            if(flag)
                j = 1;
            else
                j = 0;
            return Settings.System.putIntForUser(contentresolver, s, j, i);
        }

        public static boolean putString(ContentResolver contentresolver, String s, String s1)
        {
            return Settings.System.putString(contentresolver, s, s1);
        }

        public static boolean putStringForUser(ContentResolver contentresolver, String s, String s1, int i)
        {
            return Settings.System.putStringForUser(contentresolver, s, s1, i);
        }

        public static void setDisableWifiAutoConnectSsid(Context context, HashSet hashset)
        {
            if(hashset == null)
                return;
            StringBuilder stringbuilder = new StringBuilder();
            for(hashset = hashset.iterator(); hashset.hasNext();)
            {
                String s = (String)hashset.next();
                try
                {
                    stringbuilder.append(Base64.encodeToString(s.getBytes(), 2));
                    stringbuilder.append(",");
                }
                catch(IllegalArgumentException illegalargumentexception) { }
            }

            Settings.System.putStringForUser(context.getContentResolver(), "disable_wifi_auto_connect_ssid", stringbuilder.toString(), -3);
        }

        public static void setHotSpotMacBlackSet(Context context, Set set)
        {
            if(set != null)
            {
                StringBuilder stringbuilder = new StringBuilder();
                set = set.iterator();
                do
                {
                    if(!set.hasNext())
                        break;
                    String s = (String)set.next();
                    try
                    {
                        if(s.matches("^[a-fA-F0-9]{2}+:[a-fA-F0-9]{2}+:[a-fA-F0-9]{2}+:[a-fA-F0-9]{2}+:[a-fA-F0-9]{2}+:[a-fA-F0-9]{2}$"))
                        {
                            stringbuilder.append(Base64.encodeToString(s.getBytes(), 2));
                            stringbuilder.append(",");
                        }
                    }
                    catch(IllegalArgumentException illegalargumentexception) { }
                } while(true);
                Settings.System.putStringForUser(context.getContentResolver(), "hotspot_mac_black_set", stringbuilder.toString(), -2);
            }
        }

        public static boolean setHotSpotMaxStationNum(Context context, int i)
        {
            if(i > 0 && i < 2008)
            {
                Settings.System.putIntForUser(context.getContentResolver(), "hotspot_max_station_num", i, -2);
                return true;
            } else
            {
                return false;
            }
        }

        public static void setHotSpotVendorSpecific(Context context, String s)
        {
            if(s != null)
                Settings.System.putStringForUser(context.getContentResolver(), "hotspot_vendor_specific", s, -2);
        }

        public static void setSimpleMode(Context context, boolean flag)
        {
            context = context.getContentResolver();
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            Settings.System.putInt(context, "simple_mode", i);
        }

        public static void setSmartCoverMode(boolean flag)
        {
            SmallWindowType smallwindowtype;
            if(!FeatureParser.getBoolean("support_hall_sensor", false) || flag ^ true)
            {
                SystemProperties.set("persist.sys.smartcover_mode", String.valueOf(0));
                return;
            }
            smallwindowtype = getSmallWindowMode();
            if(smallwindowtype == null)
            {
                SystemProperties.set("persist.sys.smartcover_mode", String.valueOf(1));
                return;
            }
            _2D_getandroid_2D_provider_2D_MiuiSettings$System$SmallWindowTypeSwitchesValues()[smallwindowtype.ordinal()];
            JVM INSTR tableswitch 1 4: default 84
        //                       1 95
        //                       2 108
        //                       3 121
        //                       4 95;
               goto _L1 _L2 _L3 _L4 _L2
_L1:
            SystemProperties.set("persist.sys.smartcover_mode", String.valueOf(1));
_L6:
            return;
_L2:
            SystemProperties.set("persist.sys.smartcover_mode", String.valueOf(2));
            continue; /* Loop/switch isn't completed */
_L3:
            SystemProperties.set("persist.sys.smartcover_mode", String.valueOf(3));
            continue; /* Loop/switch isn't completed */
_L4:
            SystemProperties.set("persist.sys.smartcover_mode", String.valueOf(4));
            if(true) goto _L6; else goto _L5
_L5:
        }

        public static void setUseWordPhoto(Context context, boolean flag)
        {
            context = context.getContentResolver();
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            Settings.System.putInt(context, "word_photo", i);
        }

        private static List unflattenOrderFromString(String s)
        {
            ArrayList arraylist = new ArrayList();
            if(s != null)
            {
                s = s.split(",");
                for(int i = 0; i < s.length; i++)
                    arraylist.add(ComponentName.unflattenFromString(s[i]));

            }
            return arraylist;
        }

        public static void updateScreenColor()
        {
            DisplayFeatureManager displayfeaturemanager;
            if("1".equals(SystemProperties.get("sys.boot_completed", "0")) && FeatureParser.getBoolean("support_screen_color_persist", false))
                return;
            Slog.d("DisplayFeatureManager", "updateScreenColor begin");
            displayfeaturemanager = DisplayFeatureManager.getInstance();
            if(!ScreenEffect.isScreenPaperMode()) goto _L2; else goto _L1
_L1:
            Slog.d("DisplayFeatureManager", "setScreenPaperMode true");
            ScreenEffect.setScreenPaperMode(true);
_L4:
            int i = displayfeaturemanager.getScreenSaturation();
            Slog.d("DisplayFeatureManager", (new StringBuilder()).append("setScreenSaturation ").append(i).toString());
            displayfeaturemanager.setScreenSaturation(i);
            if(FeatureParser.getBoolean("support_screen_optimize", false))
            {
                int j = displayfeaturemanager.getScreenCabc();
                Slog.d("DisplayFeatureManager", (new StringBuilder()).append("setScreenCabc ").append(j).toString());
                displayfeaturemanager.setScreenCabc(j);
            }
            Slog.d("DisplayFeatureManager", "updateScreenColor end");
            return;
_L2:
            int k = displayfeaturemanager.getScreenGamut();
            if(FeatureParser.getInteger("screen_standard_mode", 0) == 1 && k != 0)
            {
                Slog.d("DisplayFeatureManager", (new StringBuilder()).append("setScreenGamut ").append(k).toString());
                displayfeaturemanager.setScreenGamut(k);
            } else
            {
                int l = displayfeaturemanager.getColorPrefer();
                if(l != 2)
                {
                    Slog.d("DisplayFeatureManager", (new StringBuilder()).append("setColorPrefer ").append(l).toString());
                    displayfeaturemanager.setColorPrefer(l);
                }
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static boolean useWordPhoto(Context context)
        {
            boolean flag = true;
            if(Settings.System.getInt(context.getContentResolver(), "word_photo", 0) != 1)
                flag = false;
            return flag;
        }

        private static final int _2D_android_2D_provider_2D_MiuiSettings$System$SmallWindowTypeSwitchesValues[];
        public static final String ACTIVITY_RESOLVE_ORDER = "activity_resolve_order";
        private static final String ACTIVITY_RESOLVE_ORDER_DELIMETER = ",";
        public static final String ALWAYS_ENABLE_MMS = "always_enable_mms";
        public static final String ANR_PACKAGES = "anr_pkgs";
        public static final String AUTO_TEST_MODE_ENABLE = "auto_test_mode_on";
        public static final String BATTERY_INDICATOR_STYLE = "battery_indicator_style";
        public static final int BATTERY_INDICATOR_STYLE_GRAPHIC = 0;
        public static final int BATTERY_INDICATOR_STYLE_NUMBER = 1;
        public static final int BATTERY_INDICATOR_STYLE_TOP = 2;
        public static final String BATTERY_LEVEL_LOW_CUSTOMIZED = "battery_level_low_customized";
        public static final String BREATHING_LIGHT_COLOR = "breathing_light_color";
        public static final String BREATHING_LIGHT_FREQ = "breathing_light_freq";
        public static final String CALENDAR_ALERT = "calendar_alert";
        public static final String CALL_BREATHING_LIGHT_COLOR = "call_breathing_light_color";
        public static final int CALL_BREATHING_LIGHT_COLOR_DEFAULT = Resources.getSystem().getColor(0x11060002);
        public static final String CALL_BREATHING_LIGHT_FREQ = "call_breathing_light_freq";
        public static final int CALL_BREATHING_LIGHT_FREQ_DEFAULT = Resources.getSystem().getInteger(0x1107000b);
        public static final String CAMERA_KEY_PREFERRED_ACTION_APP_COMPONENT = "camera_key_preferred_action_app_component";
        public static final String CAMERA_KEY_PREFERRED_ACTION_SHORTCUT_ID = "camera_key_preferred_action_shortcut_id";
        public static final int CAMERA_KEY_PREFERRED_ACTION_SHORTCUT_ID_CALL = 3;
        public static final int CAMERA_KEY_PREFERRED_ACTION_SHORTCUT_ID_HOME = 0;
        public static final int CAMERA_KEY_PREFERRED_ACTION_SHORTCUT_ID_SCREENSHOT = 1;
        public static final int CAMERA_KEY_PREFERRED_ACTION_SHORTCUT_ID_SEARCH = 2;
        public static final int CAMERA_KEY_PREFERRED_ACTION_SHORTCUT_ID_WAKE = 4;
        public static final String CAMERA_KEY_PREFERRED_ACTION_TOGGLE_ID = "camera_key_preferred_action_toggle_id";
        public static final String CAMERA_KEY_PREFERRED_ACTION_TYPE = "camera_key_preferred_action_type";
        public static final int CAMERA_KEY_PREFERRED_ACTION_TYPE_APP = 3;
        public static final int CAMERA_KEY_PREFERRED_ACTION_TYPE_DEFAULT = 0;
        public static final int CAMERA_KEY_PREFERRED_ACTION_TYPE_NONE = 0;
        public static final int CAMERA_KEY_PREFERRED_ACTION_TYPE_SHORTCUT = 1;
        public static final int CAMERA_KEY_PREFERRED_ACTION_TYPE_TOGGLE = 2;
        public static final String CDMA_PRECISE_ANSWER_STATE = "cdma_precise_answer_state";
        public static final int CDMA_PRECISE_ANSWER_STATE_DEFAULT = 1;
        public static final int CDMA_PRECISE_ANSWER_STATE_DISABLE = 0;
        public static final int CDMA_PRECISE_ANSWER_STATE_ENABLE = 1;
        private static final String CN_OPERATOR = "460";
        public static final String CONFIG_NETWORK_PRIORITY_SUPPORT = "sys.net.support.netprio";
        public static final String CURRENT_LIVE_WALLPAPER_PACKAGENAME = "current_live_wallpaper_packagename";
        public static final String DATA_AND_WIFI_ROAM = "data_and_wifi_roam";
        public static final int DATA_AND_WIFI_ROAM_DEFAULT = 0;
        public static final String DEFAULT_ALARM_ALERT = "default_alarm_alert";
        public static final Uri DEFAULT_CALENDAR_ALERT_URI = Settings.System.getUriFor("calendar_alert");
        public static final boolean DEFAULT_ENABLE_DEMO_MODE = false;
        public static final Uri DEFAULT_RINGTONE_URI_SLOT_1 = Settings.System.getUriFor("ringtone_sound_slot_1");
        public static final Uri DEFAULT_RINGTONE_URI_SLOT_2 = Settings.System.getUriFor("ringtone_sound_slot_2");
        public static final int DEFAULT_SCREEN_BUTTONS_TIMEOUT = 5000;
        public static final Uri DEFAULT_SMS_DELIVERED_RINGTONE_URI = Settings.System.getUriFor("sms_delivered_sound");
        public static final Uri DEFAULT_SMS_DELIVERED_SOUND_URI_SLOT_1 = Settings.System.getUriFor("sms_delivered_sound_slot_1");
        public static final Uri DEFAULT_SMS_DELIVERED_SOUND_URI_SLOT_2 = Settings.System.getUriFor("sms_delivered_sound_slot_2");
        public static final Uri DEFAULT_SMS_RECEIVED_RINGTONE_URI = Settings.System.getUriFor("sms_received_sound");
        public static final Uri DEFAULT_SMS_RECEIVED_SOUND_URI_SLOT_1 = Settings.System.getUriFor("sms_received_sound_slot_1");
        public static final Uri DEFAULT_SMS_RECEIVED_SOUND_URI_SLOT_2 = Settings.System.getUriFor("sms_received_sound_slot_2");
        public static final boolean DEFAULT_TOUCH_SENSITIVE = false;
        public static final String DIAL_PAD_TOUCH_TONE = "dial_pad_touch_tone";
        public static final int DIAL_PAD_TOUCH_TONE_DEFAULT = -1;
        public static final int DIAL_PAD_TOUCH_TONE_HUMAN = 1;
        public static final int DIAL_PAD_TOUCH_TONE_PIANO = 0;
        public static final String DISABLE_WIFI_AUTO_CONNECT_SSID = "disable_wifi_auto_connect_ssid";
        public static final String DRIVE_MODE_CALLING_AUTO_SMS_REPLY = "drive_mode_calling_auto_sms_reply";
        public static final String DRIVE_MODE_DRIVE_MODE = "drive_mode_drive_mode";
        public static final String DRIVE_MODE_INVERT_STOP_REPORT = "drive_mode_invert_stop_report";
        public static final String DRIVE_MODE_ONLY_CONTACT_REPLY = "drive_mode_only_contact_reply";
        public static final String DRIVE_MODE_PHONE_REPORT = "drive_mode_phone_report";
        public static final String DRIVE_MODE_SMS_AUTO_REPLY_CONTENT = "drive_mode_sms_auto_reply_content";
        public static final String DRIVE_MODE_SMS_AUTO_SMS_REPLY = "drive_mode_sms_auto_sms_reply";
        public static final String DRIVE_MODE_SMS_REPORT = "drive_mode_sms_report";
        public static final String EDGE_HANDGRIP_MODE = "edge_handgrip";
        public static final String EDGE_HANDGRIP_MODE_BACK = "edge_handgrip_back";
        public static final String EDGE_HANDGRIP_MODE_CLEAN = "edge_handgrip_clean";
        public static final String EDGE_HANDGRIP_MODE_PHOTO = "edge_handgrip_photo";
        public static final String EDGE_HANDGRIP_MODE_SCREENSHOT = "edge_handgrip_screenshot";
        public static final String ENABLE_AUTO_DISABLE_SCREEN_ROTATION = "enable_auto_disable_screen_rotation";
        public static final String ENABLE_DEMO_MODE = "enable_demo_mode";
        public static final String ENABLE_SCREEN_ON_PROXIMITY_SENSOR = "enable_screen_on_proximity_sensor";
        public static final String ENABLE_SNAPSHOT_SCREENLOCK = "enable_snapshot_screenlock";
        public static final int ENABLE_SNAPSHOT_SCREENLOCK_DEFAULT = 0;
        public static final String ENABLE_TELOCATION = "enable_telocation";
        public static final int ENABLE_TELOCATION_DEFAULT = 1;
        public static final int FINGERPRINT_NAV_ACTION_HOME = 1;
        public static final int FINGERPRINT_NAV_ACTION_NONE = 0;
        public static final String FINGERPRINT_NAV_CENTER_ACTION = "fingerprint_nav_center_action";
        public static final String FLASH_WHEN_RING_ENABLED = "flash_when_ring_enabled";
        public static final boolean FLASH_WHEN_RING_ENABLED_DEFAULT = false;
        public static final String GESTURE_WAKEUP_MODE = "gesture_wakeup";
        public static final String HANDY_MODE_ENTER_DIRECT = "handy_mode_enter_direct";
        public static final String HANDY_MODE_SIZE = "handy_mode_size";
        public static final String HANDY_MODE_STATE = "handy_mode_state";
        public static final String HAPTIC_FEEDBACK_LEVEL = "haptic_feedback_level";
        public static final int HAPTIC_FEEDBACK_LEVEL_DEFAULT = SystemProperties.getInt("ro.haptic.default_level", 1);
        public static final int HAPTIC_FEEDBACK_LEVEL_MAX = 2;
        public static final int HAPTIC_FEEDBACK_LEVEL_MIN = 0;
        public static final String HAS_FOLLOWED_MIPUB = "has_followed_mipub";
        public static final String HAS_SCREENSHOT_SOUND = "has_screenshot_sound";
        public static final boolean HAS_SCREENSHOT_SOUND_DEFAULT = true;
        public static final String HOTSOPT_MAC_BLACK_SET = "hotspot_mac_black_set";
        public static final String HOTSOPT_MAX_STATION_NUM = "hotspot_max_station_num";
        public static final String HOTSOPT_VENDOR_SPECIFIC = "hotspot_vendor_specific";
        public static final String IS_SECURITY_ENCRYTPION_ENABLED = "is_security_encryption_enabled";
        public static final String IS_SHOW_THREE_GESTURE_ALERT = "is_show_three_gesture_alert";
        public static final String KEEP_LAUNCHER_IN_MEMORY = "keep_launcher_in_memory";
        public static final int KEEP_LAUNCHER_IN_MEMORY_DEFAULT = 1;
        public static final String KEYGUARD_DISABLE_POWER_KEY_LONG_PRESS = "keyguard_disable_power_key_long_press";
        public static final int KEYGUARD_DISABLE_POWER_KEY_LONG_PRESS_DEFAULT = 0;
        public static final String KEYGUARD_LEFT_FUNCTION_CHOOSER = "keyguard_left_function_chooser";
        public static final String KEYGUARD_LEFT_FUNCTION_ENABLED = "keyguard_left_function_enabled";
        public static final String KEYGUARD_RIGHT_FUNCTION_CHOOSER = "keyguard_right_function_chooser";
        public static final String KEYGUARD_RIGHT_FUNCTION_ENABLED = "keyguard_right_function_enabled";
        public static final String KEY_AUTO_DISABLE_SCREEN_BUTTON = "auto_disable_screen_button";
        public static final String KEY_COMMON_PASSWORD_CHILDRENMODE = "childrenmode";
        public static final String KEY_COMMON_PASSWORD_KEYGUARD = "miui_keyguard";
        public static final String KEY_FUNCTION_LIMIT_SWITCH = "persist.sys.func_limit_switch";
        public static final String KEY_IN_SMALL_WINDOW_MODE = "is_small_window";
        public static final String KEY_MIDROP_ENABLED = "key_midrop_enabled";
        public static final String KEY_SECURITY_CENTER_ALLOW_CONNECT_NETWORK = "persist.sys.sc_allow_conn";
        public static final String KEY_TOUCH_ASSISTANT_ENABLED = "touch_assistant_enabled";
        public static final String KEY_TOUCH_ASSISTANT_SHOW_ON_KEYGUARD = "touch_assistant_show_on_keyguard";
        public static final String KEY_TOUCH_ASSISTANT_TEMPORARY = "touch_assistant_temporary";
        public static final String KEY_WAKEUP_FOR_KEYGUARD_NOTIFICATION = "wakeup_for_keyguard_notification";
        public static final String LAST_AUDIBLE_RING_VOLUME = "last_audible_ring_volume";
        public static final String LAST_VALID_DEVICE_ID = "last_valid_device_id";
        public static final String LOCAL_SEA_LEVEL_PRESSURE = "persist.sea_level_pres";
        public static final String LOCKED_APPS = "locked_apps";
        public static final String MEMORY_LOW_THRESHOLD_PROPERTY = "sys.memory.threshold.low";
        public static final String MIUI_HOME_ENABLE_AUTO_FILL_EMPTY_CELLS = "miui_home_enable_auto_fill_empty_cells";
        public static final String MIUI_HOME_LOCK_SCREEN_CELLS = "miui_home_lock_screen_cells";
        public static final String MIUI_HOME_SCREEN_CELLS_SIZE = "miui_home_screen_cells_size";
        public static final String MIUI_RECENTS_SHOW_MEM_INFO = "miui_recents_show_mem_info";
        public static final String MMS_BREATHING_LIGHT_COLOR = "mms_breathing_light_color";
        public static final String MMS_BREATHING_LIGHT_FREQ = "mms_breathing_light_freq";
        public static final String MMS_PRIVATE_ADDRESS_MARKER = "mms_private_address_marker";
        public static final String MMS_SYNC_WILD_MSG_STATE = "mms_sync_wild_msg_state";
        public static final int MMS_SYNC_WILD_MSG_STATE_DONE = 3;
        public static final int MMS_SYNC_WILD_MSG_STATE_DOWNLOAD_PENDING = 2;
        public static final int MMS_SYNC_WILD_MSG_STATE_INIT = 0;
        public static final int MMS_SYNC_WILD_MSG_STATE_MSG_FOUND = 1;
        public static final int MMS_SYNC_WILD_MSG_STATE_UPGRADE = 4;
        public static final int MMS_SYNC_WILD_MSG_STATE_UPGRADE_SIM = 5;
        public static final String MMS_SYNC_WILD_NUMBERS = "mms_sync_wild_numbers";
        public static final String MMS_THREAD_MARKER = "mms_thread_marker";
        public static final String MMS_UPLOAD_OLD_MSG_ACCOUNTS = "mms_upload_old_msg_accounts";
        public static final String MMS_UPLOAD_OLD_MSG_STATE = "mms_upload_old_msg_state";
        public static final int MMS_UPLOAD_OLD_MSG_STATE_INIT = 0;
        public static final int MMS_UPLOAD_OLD_MSG_STATE_NEED_PROMPT = 1;
        public static final String NEED_RESET_SCREEN_OFF_TIMEOUT = "need_reset_screen_off_timeout";
        public static final int NETWORK_TRAFFIC_POLICY_MODE_CLOSED = 0;
        public static final int NETWORK_TRAFFIC_POLICY_MODE_FAST = 2;
        public static final int NETWORK_TRAFFIC_POLICY_MODE_NORMAL = 1;
        public static final String NEW_NUMERIC_PASSWORD_TYPE = "new_numeric_password_type";
        public static final String NEXT_ALARM_CLOCK_FORMATTED = "next_alarm_clock_formatted";
        public static final String PACKAGE_DELETE_BY_RESTORE_PHONE = "package_delete_by_restore_phone";
        public static final String PERSIST_SYS_LINE_BREAKING = "persist.sys.line_breaking";
        public static final String POWER_MODE = "power_mode";
        public static final String POWER_MODE_KEY_PROPERTY = "persist.sys.aries.power_profile";
        public static final String POWER_MODE_VALUES[] = {
            "high", "middle"
        };
        public static final String POWER_MODE_VALUE_DEFAULT = "middle";
        public static final String POWER_MODE_VALUE_HIGH = "high";
        public static final String POWER_MODE_VALUE_LOW = "low";
        public static final String POWER_MODE_VALUE_MIDDLE = "middle";
        public static final String PRIVATE_SMS_NOTIFICATION_ENABLED = "pref_key_enable_private_notification";
        public static final Set PUBLIC_SETTINGS;
        public static final String RECENT_APPS_KEY_SHOW = "recent_apps_key_show";
        public static final String REMOTE_CONTROL_PACKAGE_NAME = "remote_control_pkg_name";
        public static final String REMOTE_CONTROL_PROCESS_NAME = "remote_control_proc_name";
        public static final String RINGTONE_SOUND_SLOT_1 = "ringtone_sound_slot_1";
        public static final String RINGTONE_SOUND_SLOT_2 = "ringtone_sound_slot_2";
        public static final String RINGTONE_SOUND_USE_UNIFORM = "ringtone_sound_use_uniform";
        public static final String SCREENSHOT_NOTIFICATION_ENABLED = "screenshot_notification_enabled";
        public static final String SCREEN_BUTTONS_TIMEOUT = "screen_buttons_timeout";
        public static final int SCREEN_KEY_BACK = 3;
        public static final int SCREEN_KEY_COUNT = 4;
        public static final int SCREEN_KEY_HOME = 1;
        public static final String SCREEN_KEY_LONG_PRESS_ACTION_CLOSE_APP = "close_app";
        public static final String SCREEN_KEY_LONG_PRESS_ACTION_GOOGLE_NOW = "google_now";
        public static final String SCREEN_KEY_LONG_PRESS_ACTION_NONE = "none";
        public static final String SCREEN_KEY_LONG_PRESS_ACTION_QUICK_SEARCH = "quick_search";
        public static final String SCREEN_KEY_LONG_PRESS_ACTION_RECENT_PANEL = "recent_panel";
        public static final String SCREEN_KEY_LONG_PRESS_ACTION_SHOW_MENU = "show_menu";
        public static final String SCREEN_KEY_LONG_PRESS_ACTION_VOICE_ASSISTANT = "voice_assistant";
        public static final String SCREEN_KEY_LONG_PRESS_APP_SWITCH = "screen_key_long_press_app_switch";
        public static final String SCREEN_KEY_LONG_PRESS_BACK = "screen_key_long_press_back";
        public static final String SCREEN_KEY_LONG_PRESS_HOME = "screen_key_long_press_home";
        public static final String SCREEN_KEY_LONG_PRESS_MENU = "screen_key_long_press_menu";
        public static final String SCREEN_KEY_LONG_PRESS_TIMEOUT = "screen_key_long_press_timeout";
        public static final int SCREEN_KEY_LONG_PRESS_TIMEOUT_DEFAULT = 500;
        public static final int SCREEN_KEY_MENU = 0;
        public static final String SCREEN_KEY_ORDER = "screen_key_order";
        public static final String SCREEN_KEY_PRESS_APP_SWITCH = "screen_key_press_app_switch";
        public static final boolean SCREEN_KEY_PRESS_APP_SWITCH_DEFAULT = true;
        public static final int SCREEN_KEY_RECENT_APPS = 2;
        public static final String SCREEN_OFF_BY_LID_PROPERTY_STRING = "sys.keyguard.screen_off_by_lid";
        public static final String SHOW_LOCK_BEFORE_UNLOCK = "show_lock_before_unlock";
        public static final boolean SHOW_LOCK_BEFORE_UNLOCK_DEFAULT;
        public static final String SHOW_ROUNDED_CORNERS = "show_rounded_corners";
        private static final String SIMPLE_MODE = "simple_mode";
        public static final int SMARTCOVER_DISABLED = 0;
        public static final int SMARTCOVER_FULL_MODE = 4;
        public static final String SMARTCOVER_GUIDE_KEY = "smart_cover_key";
        public static final int SMARTCOVER_LATTICE_MODE = 3;
        public static final int SMARTCOVER_MODE_DEFAULT_VALUE = -1;
        public static final String SMARTCOVER_MODE_KEY = "persist.sys.smartcover_mode";
        public static final int SMARTCOVER_NORMAL_MODE = 1;
        public static final int SMARTCOVER_SMALLWINDOW_MODE = 2;
        public static final String SMARTCOVER_SMALLWIN_TYPE = "persist.sys.smallwin_type";
        public static final String SMS_DELIVERED_SOUND = "sms_delivered_sound";
        public static final String SMS_DELIVERED_SOUND_SLOT_1 = "sms_delivered_sound_slot_1";
        public static final String SMS_DELIVERED_SOUND_SLOT_2 = "sms_delivered_sound_slot_2";
        public static final String SMS_DELIVERED_SOUND_USE_UNIFORM = "sms_delivered_sound_use_uniform";
        public static final String SMS_NOTIFICATION_BODY_ENABLED = "pref_key_enable_notification_body";
        public static final String SMS_NOTIFICATION_ENABLED = "pref_key_enable_notification";
        public static final String SMS_RECEIVED_SOUND = "sms_received_sound";
        public static final String SMS_RECEIVED_SOUND_SLOT_1 = "sms_received_sound_slot_1";
        public static final String SMS_RECEIVED_SOUND_SLOT_2 = "sms_received_sound_slot_2";
        public static final String SMS_RECEIVED_SOUND_USE_UNIFORM = "sms_received_sound_use_uniform";
        public static final String SMS_WAKE_UP_SCREEN_ENABLED = "pref_key_enable_wake_up_screen";
        public static final String STATUS_BAR_COLLAPSE_AFTER_CLICKED = "status_bar_collapse_after_clicked";
        public static final String STATUS_BAR_CUSTOM_CARRIER = "status_bar_custom_carrier";
        public static final String STATUS_BAR_EXPANDABLE_UNDER_FULLSCREEN = "status_bar_expandable_under_fullscreen";
        public static final String STATUS_BAR_EXPANDABLE_UNDER_KEYGUARD = "status_bar_expandable_under_keyguard";
        public static final String STATUS_BAR_REAL_CARRIER = "status_bar_real_carrier";
        public static final String STATUS_BAR_SHOW_CUSTOM_CARRIER = "status_bar_show_custom_carrier";
        public static final String STATUS_BAR_SHOW_NETWORK_ASSISTANT = "status_bar_show_network_assistant";
        public static final int STATUS_BAR_SHOW_NETWORK_ASSISTANT_DEFAULT = 0;
        public static final String STATUS_BAR_SHOW_NETWORK_SPEED = "status_bar_show_network_speed";
        public static final int STATUS_BAR_SHOW_NETWORK_SPEED_DEFAULT = 0;
        public static final String STATUS_BAR_SHOW_NOTIFICATION_ICON = "status_bar_show_notification_icon";
        public static final int STATUS_BAR_SHOW_NOTIFICATION_ICON_DEFAULT;
        public static final String STATUS_BAR_STYLE = "status_bar_style_type";
        public static final int STATUS_BAR_STYLE_DEFAULT = 0;
        public static final int STATUS_BAR_STYLE_LIST = 0;
        public static final int STATUS_BAR_STYLE_PAGE = 1;
        public static final String STATUS_BAR_TOGGLE_LIST = "status_bar_toggle_list_order_new";
        public static final String STATUS_BAR_TOGGLE_PAGE = "status_bar_toggle_page_order";
        public static final String STATUS_BAR_UPDATE_NETWORK_SPEED_INTERVAL = "status_bar_network_speed_interval";
        public static final int STATUS_BAR_UPDATE_NETWORK_SPEED_INTERVAL_DEFAULT = 4000;
        public static final String SYSTEM_NEW_VERSION_FOUND = "system_new_version_found";
        public static final String T9_INDEXING_KEY = "t9_indexing_key";
        public static final int T9_INDEXING_KEY_PINYIN = 0;
        public static final int T9_INDEXING_KEY_ZHUYIN = 1;
        public static final String THREE_GESTURE_SCREENSHOT = "three_gesture_screenshot";
        public static final boolean THREE_GESTURE_SCREENSHOT_DEFAULT = false;
        public static final String TORCH_STATE = "torch_state";
        public static final String TOUCH_SENSITIVE = "touch_sensitive";
        public static final String TRACKBALL_WAKE_SCREEN = "trackball_wake_screen";
        public static final String UI_MODE_SCALE = "ui_mode_scale";
        public static final String USER_GUIDE_LOCK_SCREEN_UNLOCK = "user_guide_lock_screen_unlock";
        public static final String USER_GUIDE_STATUS_BAR_CALL = "user_guide_status_bar_call";
        public static final String USER_GUIDE_STATUS_BAR_NOTIFICATION = "user_guide_status_bar_notification";
        public static final String USER_GUIDE_STATUS_BAR_TOGGLE = "user_guide_status_bar_toggle";
        public static final String USER_GUIDE_STATUS_BAR_TOGGLE_LIST = "user_guide_status_bar_toggle_list";
        public static final String USER_NETWORK_PRIORITY_ENABLED = "user_network_priority_enabled";
        public static final String VIBRATE_IN_NORMAL = "vibrate_in_normal";
        public static final boolean VIBRATE_IN_NORMAL_DEFAULT;
        public static final String VIBRATE_IN_SILENT = "vibrate_in_silent";
        public static final boolean VIBRATE_IN_SILENT_DEFAULT = true;
        public static final String VOICEASSIST_INVERT_STOP_REPORT = "voiceassist_invert_stop_report";
        public static final String VOICEASSIST_MENU_LONG_LAUNCH = "voiceassist_menu_long_press_launch";
        public static final String VOICEASSIST_PHONE_BY_EAR_LAUNCH = "voiceassist_phone_by_ear_launch";
        public static final String VOICEASSIST_PHONE_REPORT = "voiceassist_phone_report";
        public static final String VOICEASSIST_REPORT_METHOD = "voiceassist_report_method";
        public static final int VOICEASSIST_REPORT_OFF = 2;
        public static final int VOICEASSIST_REPORT_ON = 1;
        public static final int VOICEASSIST_REPORT_WIREDON = 0;
        public static final String VOICEASSIST_SMS_REPORT = "voiceassist_sms_report";
        public static final String VOLUMEKEY_WAKE_SCREEN = "volumekey_wake_screen";
        public static final String WIFI_CONNECT_TYPE = "wifi_connect_type";
        public static final int WIFI_CONNECT_TYPE_ASK = 2;
        public static final int WIFI_CONNECT_TYPE_AUTO = 0;
        public static final int WIFI_CONNECT_TYPE_MANUL = 1;
        public static final String WIFI_DIALOG_REMIND_TYPE = "wifi_dialog_remind_type";
        public static final int WIFI_DIALOG_REMIND_TYPE_CLOSE = 0;
        public static final int WIFI_DIALOG_REMIND_TYPE_OPEN = 1;
        public static final String WIFI_MASTER_KEY = "wifi_master_key";
        public static final int WIFI_MASTER_KEY_DEFAULT = 1;
        public static final String WIFI_PRIORITY_TYPE = "wifi_priority_type";
        public static final int WIFI_PRIORITY_TYPE_DEFAULT = 0;
        public static final int WIFI_PRIORITY_TYPE_MAMUAL = 1;
        public static final int WIFI_SELECT_SSID_ASK = 2;
        public static final int WIFI_SELECT_SSID_AUTO = 0;
        public static final int WIFI_SELECT_SSID_MANUL = 1;
        public static final String WIFI_SELECT_SSID_TYPE = "wifi_select_ssid_type";
        public static final String WIFI_SHARE = "wifi";
        public static final String WINDOW_TYPE_LAYER = "window_type_layer";
        public static final int WIRELESS_COMPATIABLE_MODE = 0;
        public static final String WIRELESS_CONNECT_MODE = "wireless_compatible_mode";
        public static final int WIRELESS_HIGH_MODE = 1;
        private static final String WORD_PHOTO = "word_photo";
        public static ArrayList screenKeys;

        static 
        {
            int i;
            boolean flag;
            if(Build.IS_CTA_BUILD || Build.IS_CM_CUSTOMIZATION_TEST || Build.IS_CU_CUSTOMIZATION_TEST)
                i = 1;
            else
                i = 0;
            STATUS_BAR_SHOW_NOTIFICATION_ICON_DEFAULT = i;
            VIBRATE_IN_NORMAL_DEFAULT = Build.IS_CM_CUSTOMIZATION_TEST;
            if(Build.IS_CM_CUSTOMIZATION_TEST)
                flag = false;
            else
                flag = true;
            SHOW_LOCK_BEFORE_UNLOCK_DEFAULT = flag;
            screenKeys = new ArrayList();
            screenKeys.add(Integer.valueOf(2));
            screenKeys.add(Integer.valueOf(1));
            screenKeys.add(Integer.valueOf(3));
            PUBLIC_SETTINGS = new ArraySet();
            PUBLIC_SETTINGS.add("sync_for_sim_com.xiaomi-call_log-0");
            PUBLIC_SETTINGS.add("sync_for_sim_com.xiaomi-call_log-1");
            PUBLIC_SETTINGS.add("setting_last_time_alert_call_log");
            PUBLIC_SETTINGS.add("sync_for_sim_com.xiaomi-wifi-0");
            PUBLIC_SETTINGS.add("sync_for_sim_com.xiaomi-wifi-1");
            PUBLIC_SETTINGS.add("setting_last_time_alert_wifi");
            PUBLIC_SETTINGS.add("sync_for_sim_com.xiaomi-com.miui.gallery.cloud.provider-0");
            PUBLIC_SETTINGS.add("sync_for_sim_com.xiaomi-com.miui.gallery.cloud.provider-1");
            PUBLIC_SETTINGS.add("setting_last_time_alert_com.miui.gallery.cloud.provider");
            PUBLIC_SETTINGS.add("sync_for_sim_com.xiaomi-records-0");
            PUBLIC_SETTINGS.add("sync_for_sim_com.xiaomi-records-1");
            PUBLIC_SETTINGS.add("setting_last_time_alert_records");
            PUBLIC_SETTINGS.add("sync_for_sim_com.xiaomi-com.android.calendar-0");
            PUBLIC_SETTINGS.add("sync_for_sim_com.xiaomi-com.android.calendar-1");
            PUBLIC_SETTINGS.add("setting_last_time_alert_com.android.calendar");
            PUBLIC_SETTINGS.add("sync_for_sim_com.xiaomi-notes-0");
            PUBLIC_SETTINGS.add("sync_for_sim_com.xiaomi-notes-1");
            PUBLIC_SETTINGS.add("setting_last_time_alert_notes");
            PUBLIC_SETTINGS.add("sync_for_sim_com.xiaomi-sms-0");
            PUBLIC_SETTINGS.add("sync_for_sim_com.xiaomi-sms-1");
            PUBLIC_SETTINGS.add("setting_last_time_alert_sms");
            PUBLIC_SETTINGS.add("sync_for_sim_com.xiaomi-com.android.contacts-0");
            PUBLIC_SETTINGS.add("sync_for_sim_com.xiaomi-com.android.contacts-1");
            PUBLIC_SETTINGS.add("setting_last_time_alert_com.android.contacts");
            PUBLIC_SETTINGS.add("sync_for_sim_com.xiaomi-com.miui.browser-0");
            PUBLIC_SETTINGS.add("sync_for_sim_com.xiaomi-com.miui.browser-1");
            PUBLIC_SETTINGS.add("setting_last_time_alert_com.miui.browser");
            PUBLIC_SETTINGS.add("sync_for_sim_com.xiaomi-antispam-0");
            PUBLIC_SETTINGS.add("sync_for_sim_com.xiaomi-antispam-1");
            PUBLIC_SETTINGS.add("setting_last_time_alert_antispam");
            PUBLIC_SETTINGS.add("sync_for_sim_com.xiaomi-com.miui.player-0");
            PUBLIC_SETTINGS.add("sync_for_sim_com.xiaomi-com.miui.player-1");
            PUBLIC_SETTINGS.add("setting_last_time_alert_com.miui.player");
            PUBLIC_SETTINGS.add("com.xiaomi.opensdk.pdc.host");
            PUBLIC_SETTINGS.add("micloud_network_availability");
            PUBLIC_SETTINGS.add("micloud_hosts");
            PUBLIC_SETTINGS.add("micloud_accountname");
            PUBLIC_SETTINGS.add("micloud_hosts_v2");
            PUBLIC_SETTINGS.add("micloud_accountname_v2");
            PUBLIC_SETTINGS.add("micloud_updatehosts_third_party");
            PUBLIC_SETTINGS.add("micloud_gdpr_permission_granted");
            PUBLIC_SETTINGS.add("mms_sync_wild_msg_state");
            PUBLIC_SETTINGS.add("mms_sync_wild_numbers");
            PUBLIC_SETTINGS.add("mms_upload_old_msg_state");
            PUBLIC_SETTINGS.add("mms_upload_old_msg_accounts");
            PUBLIC_SETTINGS.add("mms_thread_marker");
            PUBLIC_SETTINGS.add("mms_private_address_marker");
            PUBLIC_SETTINGS.add("miprofile.settings.miprofile_user_notice");
            PUBLIC_SETTINGS.add("miprofile.settings.miprofile_badge_notice");
            PUBLIC_SETTINGS.add("miprofile.settings.miprofile_set");
            PUBLIC_SETTINGS.add("miprofile.settings.miprofile_on");
            PUBLIC_SETTINGS.add("miprofile.settings.miprofile_visible");
            PUBLIC_SETTINGS.add("debug_switch");
            PUBLIC_SETTINGS.add("debug_dp_path");
            PUBLIC_SETTINGS.add("xunlei_token");
            PUBLIC_SETTINGS.add("vip_token");
            PUBLIC_SETTINGS.add("xunlei_usage_permission");
            PUBLIC_SETTINGS.add("default_alarm_alert");
            PUBLIC_SETTINGS.add("next_alarm_clock_formatted");
            PUBLIC_SETTINGS.add("voiceassist_report_method");
            PUBLIC_SETTINGS.add("voiceassist_phone_report");
            PUBLIC_SETTINGS.add("voiceassist_sms_report");
            PUBLIC_SETTINGS.add("last_valid_device_id");
            PUBLIC_SETTINGS.add("livetalk_service_status");
            PUBLIC_SETTINGS.add("livetalk_enabled");
            PUBLIC_SETTINGS.add("livetalk_switch_state");
            PUBLIC_SETTINGS.add("need_prompt");
            PUBLIC_SETTINGS.add("livetalk_use_current_account");
            PUBLIC_SETTINGS.add("internal_dial_avaiable");
            PUBLIC_SETTINGS.add("international_dial_avaiable");
            PUBLIC_SETTINGS.add("recent_country_remain_mins");
            PUBLIC_SETTINGS.add("livetalk_dial_range");
            PUBLIC_SETTINGS.add("livetalk_available_status");
            PUBLIC_SETTINGS.add("livetalk_remain_minutes");
            PUBLIC_SETTINGS.add("lock_wallpaper_provider_authority");
            PUBLIC_SETTINGS.add((new StringBuilder()).append("clock_changed_time_").append("1x2").toString());
            PUBLIC_SETTINGS.add((new StringBuilder()).append("clock_changed_time_").append("2x2").toString());
            PUBLIC_SETTINGS.add((new StringBuilder()).append("clock_changed_time_").append("2x4").toString());
            PUBLIC_SETTINGS.add((new StringBuilder()).append("clock_changed_time_").append("4x4").toString());
            PUBLIC_SETTINGS.add((new StringBuilder()).append("clock_changed_time_").append("3x4").toString());
            PUBLIC_SETTINGS.add("pref_key_wallpaper_screen_span");
            PUBLIC_SETTINGS.add("com.xiaomi.market.enable_share_progress_status");
            PUBLIC_SETTINGS.add("com.miui.home.enable_share_progress_status");
            PUBLIC_SETTINGS.add("com.xiaomi.discover.enable_share_progress_status");
            PUBLIC_SETTINGS.add("com.xiaomi.mipicks.enable_share_progress_status");
            PUBLIC_SETTINGS.add("touch_assistant_enabled");
            PUBLIC_SETTINGS.add("touch_assistant_show_on_keyguard");
            PUBLIC_SETTINGS.add("frequent_phrases");
            PUBLIC_SETTINGS.add("ringtone_sound_slot_1");
            PUBLIC_SETTINGS.add("ringtone_sound_slot_2");
            PUBLIC_SETTINGS.add("sms_received_sound");
            PUBLIC_SETTINGS.add("sms_received_sound_slot_1");
            PUBLIC_SETTINGS.add("sms_received_sound_slot_2");
            PUBLIC_SETTINGS.add("calendar_alert");
            PUBLIC_SETTINGS.add("updatable_system_app_count");
            PUBLIC_SETTINGS.add("show_touches");
            PUBLIC_SETTINGS.add("handy_mode_state");
            PUBLIC_SETTINGS.add("long_press_power_launch_xiaoai");
            PUBLIC_SETTINGS.add("send_back_when_xiaoai_appear");
            PUBLIC_SETTINGS.add("double_click_power_key");
        }

        public System()
        {
        }
    }

    public static final class System.SmallWindowType extends Enum
    {

        public static System.SmallWindowType valueOf(String s)
        {
            return (System.SmallWindowType)Enum.valueOf(android/provider/MiuiSettings$System$SmallWindowType, s);
        }

        public static System.SmallWindowType[] values()
        {
            return $VALUES;
        }

        private static final System.SmallWindowType $VALUES[];
        public static final System.SmallWindowType A1_STYLE;
        public static final System.SmallWindowType A7_LATTICE;
        public static final System.SmallWindowType B7_FULL;
        public static final System.SmallWindowType X7_STYLE;

        static 
        {
            X7_STYLE = new System.SmallWindowType("X7_STYLE", 0);
            A1_STYLE = new System.SmallWindowType("A1_STYLE", 1);
            A7_LATTICE = new System.SmallWindowType("A7_LATTICE", 2);
            B7_FULL = new System.SmallWindowType("B7_FULL", 3);
            $VALUES = (new System.SmallWindowType[] {
                X7_STYLE, A1_STYLE, A7_LATTICE, B7_FULL
            });
        }

        private System.SmallWindowType(String s, int i)
        {
            super(s, i);
        }
    }

    public static class Telephony
    {

        public static String getAutoIpPrefix(ContentResolver contentresolver, String s)
        {
            return getAutoIpPrefix(contentresolver, s, -1);
        }

        public static String getAutoIpPrefix(ContentResolver contentresolver, String s, int i)
        {
            String s1 = "autoip_prefix";
            if(i >= 0)
                s1 = (new StringBuilder()).append("autoip_prefix").append(i).toString();
            return System.getString(contentresolver, s1, s);
        }

        public static int getCallBackgroundType(ContentResolver contentresolver)
        {
            return Settings.System.getInt(contentresolver, "incall_background_key", 0);
        }

        public static int getCallWaitingTone(Context context)
        {
            int i = Settings.System.getInt(context.getContentResolver(), "call_waiting_tone", 0);
            int j = i;
            if("lithium".equals(Build.DEVICE))
            {
                j = i;
                if(i == 2)
                    j = 0;
            }
            return j;
        }

        public static String getContactCountrycode(ContentResolver contentresolver)
        {
            return System.getString(contentresolver, "persist.radio.countrycode");
        }

        public static String getCurrentAeraCode(ContentResolver contentresolver, int i)
        {
            String s = "current_areacode";
            if(i >= 0)
                s = (new StringBuilder()).append("current_areacode").append(i).toString();
            return System.getString(contentresolver, s);
        }

        public static boolean getEnabledAutoRedial(ContentResolver contentresolver)
        {
            return System.getBoolean(contentresolver, "button_auto_redial", false);
        }

        public static boolean getEnabledVoiceService(ContentResolver contentresolver)
        {
            return System.getBoolean(contentresolver, "button_voice_service", false);
        }

        public static String getIVRParameterValue(ContentResolver contentresolver, String s)
        {
            return System.getString(contentresolver, s, "");
        }

        public static int getMissedCallNotifyTimes(ContentResolver contentresolver)
        {
            return Settings.System.getInt(contentresolver, "button_missed_call_notify_times", 0);
        }

        public static int getRecordScenario(ContentResolver contentresolver)
        {
            return Integer.valueOf(System.getString(contentresolver, "radio_record_scenario", "0")).intValue();
        }

        public static String getRecordWhiteList(ContentResolver contentresolver)
        {
            return System.getString(contentresolver, "record_white_list");
        }

        public static String getVibrateKey(ContentResolver contentresolver)
        {
            return System.getString(contentresolver, "button_connect_disconnect_vibrate", "100");
        }

        public static boolean isAntispamStangerEnabled(ContentResolver contentresolver)
        {
            return System.getBoolean(contentresolver, "button_antispam_strange", false);
        }

        public static boolean isAutoAddZeroPrefix(ContentResolver contentresolver, int i)
        {
            String s = "button_add_zero_prefix";
            if(i >= 0)
                s = (new StringBuilder()).append("button_add_zero_prefix").append(i).toString();
            return System.getBoolean(contentresolver, s, false);
        }

        public static boolean isAutoCountryCodeEnable(ContentResolver contentresolver)
        {
            return System.getBoolean(contentresolver, "auto_country_code", false);
        }

        public static boolean isAutoIpEnable(ContentResolver contentresolver, int i)
        {
            String s = "button_autoip";
            if(i >= 0)
                s = (new StringBuilder()).append("button_autoip").append(i).toString();
            return System.getBoolean(contentresolver, s, false);
        }

        public static boolean isAutoIpSupportLocalNum(ContentResolver contentresolver, int i)
        {
            String s = "button_auto_ip_support_local_numbers";
            if(i >= 0)
                s = (new StringBuilder()).append("button_auto_ip_support_local_numbers").append(i).toString();
            return System.getBoolean(contentresolver, s, false);
        }

        public static boolean isAutoRecordEnabled(ContentResolver contentresolver)
        {
            return System.getBoolean(contentresolver, "button_auto_record_call", false);
        }

        public static boolean isCrescendoRingerEnable(ContentResolver contentresolver)
        {
            return System.getBoolean(contentresolver, "button_crescendo_ringer", false);
        }

        public static boolean isHandonRingerEnabled(ContentResolver contentresolver)
        {
            return System.getBoolean(contentresolver, "button_handon_ringer", false);
        }

        public static boolean isIncomingVideoShowEnabled(ContentResolver contentresolver)
        {
            return System.getBoolean(contentresolver, "button_incoming_video_show", true);
        }

        public static boolean isProximitySensorEnable(ContentResolver contentresolver)
        {
            return System.getBoolean(contentresolver, "button_enable_proximity", true);
        }

        public static boolean isRecordNotificationEnabled(ContentResolver contentresolver)
        {
            return System.getBoolean(contentresolver, "button_call_recording_notification", true);
        }

        public static boolean isRejectViaSmsEnable(ContentResolver contentresolver)
        {
            return System.getBoolean(contentresolver, "button_enable_reject_via_sms", true);
        }

        public static boolean isTelocationEnable(ContentResolver contentresolver)
        {
            return System.getBoolean(contentresolver, "enable_telocation", true);
        }

        public static boolean isTurnOverMuteEnabled(ContentResolver contentresolver)
        {
            return System.getBoolean(contentresolver, "button_turn_over_mute", false);
        }

        public static boolean isUnknownNumberRecordEnabled(ContentResolver contentresolver)
        {
            return System.getBoolean(contentresolver, "button_record_unknown_number", false);
        }

        public static boolean isYellowpageRecordEnabled(ContentResolver contentresolver)
        {
            return System.getBoolean(contentresolver, "button_record_yellowpage_number", false);
        }

        public static void setAntispamStangerEnabled(ContentResolver contentresolver, boolean flag)
        {
            System.putBoolean(contentresolver, "button_antispam_strange", flag);
        }

        public static void setAutoAddZeroPrefixEnable(ContentResolver contentresolver, boolean flag, int i)
        {
            String s = "button_add_zero_prefix";
            if(i >= 0)
                s = (new StringBuilder()).append("button_add_zero_prefix").append(i).toString();
            System.putBoolean(contentresolver, s, flag);
        }

        public static void setAutoCountryCodeEnable(ContentResolver contentresolver, boolean flag)
        {
            System.putBoolean(contentresolver, "auto_country_code", flag);
        }

        public static void setAutoIpEnable(ContentResolver contentresolver, boolean flag, int i)
        {
            String s = "button_autoip";
            if(i >= 0)
                s = (new StringBuilder()).append("button_autoip").append(i).toString();
            System.putBoolean(contentresolver, s, flag);
        }

        public static void setAutoIpPrefix(ContentResolver contentresolver, String s)
        {
            setAutoIpPrefix(contentresolver, s, -1);
        }

        public static void setAutoIpPrefix(ContentResolver contentresolver, String s, int i)
        {
            String s1 = "autoip_prefix";
            if(i >= 0)
                s1 = (new StringBuilder()).append("autoip_prefix").append(i).toString();
            System.putString(contentresolver, s1, s);
        }

        public static void setAutoIpSupportLocalNumEnable(ContentResolver contentresolver, boolean flag, int i)
        {
            String s = "button_auto_ip_support_local_numbers";
            if(i >= 0)
                s = (new StringBuilder()).append("button_auto_ip_support_local_numbers").append(i).toString();
            System.putBoolean(contentresolver, s, flag);
        }

        public static void setAutoRecordEnabled(ContentResolver contentresolver, boolean flag)
        {
            System.putBoolean(contentresolver, "button_auto_record_call", flag);
        }

        public static void setAutoRedialEnabled(ContentResolver contentresolver, boolean flag)
        {
            System.putBoolean(contentresolver, "button_auto_redial", flag);
        }

        public static boolean setCallBackgroundType(ContentResolver contentresolver, int i)
        {
            return Settings.System.putInt(contentresolver, "incall_background_key", i);
        }

        public static void setCallWaitingTone(Context context, int i)
        {
            Settings.System.putInt(context.getContentResolver(), "call_waiting_tone", i);
        }

        public static void setContactCountrycode(ContentResolver contentresolver, String s)
        {
            System.putString(contentresolver, "persist.radio.countrycode", s);
        }

        public static void setCrescendoRingerEnable(ContentResolver contentresolver, boolean flag)
        {
            System.putBoolean(contentresolver, "button_crescendo_ringer", flag);
        }

        public static void setCurrentAeraCode(ContentResolver contentresolver, String s, int i)
        {
            String s1 = "current_areacode";
            if(i >= 0)
                s1 = (new StringBuilder()).append("current_areacode").append(i).toString();
            System.putString(contentresolver, s1, s);
        }

        public static void setHandonRingerEnabled(ContentResolver contentresolver, boolean flag)
        {
            System.putBoolean(contentresolver, "button_handon_ringer", flag);
        }

        public static void setIVRParameterValue(ContentResolver contentresolver, String s, String s1)
        {
            System.putString(contentresolver, s, s1);
        }

        public static void setIncomingVideoShowEnabled(ContentResolver contentresolver, boolean flag)
        {
            System.putBoolean(contentresolver, "button_incoming_video_show", flag);
        }

        public static void setMissedCallNotifyTimes(ContentResolver contentresolver, int i)
        {
            Settings.System.putInt(contentresolver, "button_missed_call_notify_times", i);
        }

        public static void setProximitySensorEnable(ContentResolver contentresolver, boolean flag)
        {
            System.putBoolean(contentresolver, "button_enable_proximity", flag);
        }

        public static void setRecordNotificationEnabled(ContentResolver contentresolver, boolean flag)
        {
            System.putBoolean(contentresolver, "button_call_recording_notification", flag);
        }

        public static void setRecordScenario(ContentResolver contentresolver, int i)
        {
            System.putString(contentresolver, "radio_record_scenario", String.valueOf(i));
        }

        public static void setRecordWhiteList(ContentResolver contentresolver, String s)
        {
            System.putString(contentresolver, "record_white_list", s);
        }

        public static void setRejectViaSmsEnable(ContentResolver contentresolver, boolean flag)
        {
            System.putBoolean(contentresolver, "button_enable_reject_via_sms", flag);
        }

        public static void setTelocationEnable(ContentResolver contentresolver, boolean flag)
        {
            System.putBoolean(contentresolver, "enable_telocation", flag);
        }

        public static void setTurnOverMuteEnabled(ContentResolver contentresolver, boolean flag)
        {
            System.putBoolean(contentresolver, "button_turn_over_mute", flag);
        }

        public static void setUnknownNumberRecordEnabled(ContentResolver contentresolver, boolean flag)
        {
            System.putBoolean(contentresolver, "button_record_unknown_number", flag);
        }

        public static void setVibrateKey(ContentResolver contentresolver, String s)
        {
            System.putString(contentresolver, "button_connect_disconnect_vibrate", s);
        }

        public static void setVoiceServiceEnabled(ContentResolver contentresolver, boolean flag)
        {
            System.putBoolean(contentresolver, "button_voice_service", flag);
        }

        public static void setYellowpageRecordEnabled(ContentResolver contentresolver, boolean flag)
        {
            System.putBoolean(contentresolver, "button_record_yellowpage_number", flag);
        }

        public static final String ADD_ZERO_PREFIX_SWITCH = "button_add_zero_prefix";
        public static final String AUTOIP_PREFIX = "autoip_prefix";
        public static final String AUTOIP_SWITCH = "button_autoip";
        public static final String AUTO_COUNTRY_CODE = "auto_country_code";
        public static final String AUTO_IP_SUPPORT_LOCAL_NUMBERS = "button_auto_ip_support_local_numbers";
        private static final String CALL_WAITING_TONE = "call_waiting_tone";
        public static final int CALL_WAITING_TONE_CONTINUOUSLY = 0;
        public static final int CALL_WAITING_TONE_TWICE_AND_VIBRATE = 2;
        public static final int CALL_WAITING_TONE_TWICE_ONLY = 1;
        public static final String CONNECT_DISCONNECT_VIBRATE = "button_connect_disconnect_vibrate";
        public static final String CONTACT_COUNTRYCODE = "persist.radio.countrycode";
        public static final String CURRENT_AREACODE = "current_areacode";
        public static final String ENABLED_ANTISPAM_STRANGE = "button_antispam_strange";
        public static final String ENABLED_AUTO_RECORD = "button_auto_record_call";
        public static final String ENABLED_AUTO_REDIAL = "button_auto_redial";
        public static final String ENABLED_HANDON_RINGER = "button_handon_ringer";
        public static final String ENABLED_INCOMING_VIDEO_SHOW = "button_incoming_video_show";
        public static final String ENABLED_RECORD_NOTIFY = "button_call_recording_notification";
        public static final String ENABLED_TURN_OVER_MUTE = "button_turn_over_mute";
        public static final String ENABLED_UNKNOWN_NUMBER_RECORD = "button_record_unknown_number";
        public static final String ENABLED_VOICE_SERVICE = "button_voice_service";
        public static final String ENABLED_YELLOWPAGE_RECORD = "button_record_yellowpage_number";
        public static final String ENABLE_CRESCENDO_RINGER = "button_crescendo_ringer";
        public static final String ENABLE_PROXIMITY_KEY = "button_enable_proximity";
        public static final String ENABLE_REJECT_VIA_SMS_KEY = "button_enable_reject_via_sms";
        public static final String ENABLE_TELOCATION = "enable_telocation";
        public static final String INCALL_BACKGROUND_KEY = "incall_background_key";
        public static final int INCALL_BACKGROUND_KEY_DEFAULT = 0;
        public static final int INCALL_BACKGROUND_KEY_LOCK_WALLPAPER = 1;
        public static final String IVR_MO_AREA_CODE_SLOT_1 = "ivr_mo_area_code_slot_1";
        public static final String IVR_MO_AREA_CODE_SLOT_2 = "ivr_mo_area_code_slot_2";
        public static final String IVR_TYPE_SLOT_1 = "ivr_type_slot_1";
        public static final String IVR_TYPE_SLOT_2 = "ivr_type_slot_2";
        public static final String MISSED_CALL_NOTIFY_TIMES = "button_missed_call_notify_times";
        public static final String RECORD_SCENARIO = "radio_record_scenario";
        public static final String RECORD_WHITE_LIST = "record_white_list";
        public static final String VOLTE_FEATURE_DISABLED = "volte_feature_disabled";

        public Telephony()
        {
        }
    }

    public static final class VirtualSim
    {

        public static long getMiSimId(Context context)
        {
            return miui.provider.ExtraSettings.Secure.getLong(context.getContentResolver(), "misim_id", 0L);
        }

        public static String getSupportNetworkType(Context context)
        {
            return miui.provider.ExtraSettings.Secure.getString(context.getContentResolver(), "virtual_sim_network_type", "");
        }

        public static String getVirtualSimIccId(Context context)
        {
            return miui.provider.ExtraSettings.Secure.getString(context.getContentResolver(), "virtual_sim_iccid", "");
        }

        public static String getVirtualSimImsi(Context context)
        {
            return miui.provider.ExtraSettings.Secure.getString(context.getContentResolver(), "virtual_sim_imsi", "");
        }

        public static int getVirtualSimSlotId(Context context)
        {
            return miui.provider.ExtraSettings.Secure.getInt(context.getContentResolver(), "virtual_sim_slot_id", 0);
        }

        public static int getVirtualSimStatus(Context context)
        {
            return miui.provider.ExtraSettings.Secure.getInt(context.getContentResolver(), "virtual_sim_status", 2);
        }

        public static int getVirtualSimType(Context context)
        {
            return miui.provider.ExtraSettings.Secure.getInt(context.getContentResolver(), "virtual_sim_type", 0);
        }

        public static boolean isMiSimEnabled(Context context)
        {
            String s = miui.provider.ExtraSettings.Secure.getString(context.getContentResolver(), "misim_imsi", null);
            boolean flag;
            if(!TextUtils.isEmpty(s) && isVirtualSimEnabled(context))
                flag = s.equals(getVirtualSimImsi(context));
            else
                flag = false;
            return flag;
        }

        public static boolean isSupport4G(Context context)
        {
            context = miui.provider.ExtraSettings.Secure.getString(context.getContentResolver(), "virtual_sim_network_type", "");
            boolean flag;
            if(!TextUtils.isEmpty(context))
                flag = context.contains("4G");
            else
                flag = true;
            return flag;
        }

        public static boolean isVirtualSimEnabled(Context context)
        {
            return TextUtils.isEmpty(getVirtualSimImsi(context)) ^ true;
        }

        public static void setMiSimId(Context context, long l)
        {
            miui.provider.ExtraSettings.Secure.putLong(context.getContentResolver(), "misim_id", l);
        }

        public static void setMiSimImsi(Context context, String s)
        {
            miui.provider.ExtraSettings.Secure.putString(context.getContentResolver(), "misim_imsi", s);
        }

        public static void setSupportNetworkType(Context context, String s)
        {
            miui.provider.ExtraSettings.Secure.putString(context.getContentResolver(), "virtual_sim_network_type", s);
        }

        public static void setVirtualSimIccId(Context context, String s)
        {
            miui.provider.ExtraSettings.Secure.putString(context.getContentResolver(), "virtual_sim_iccid", s);
        }

        public static void setVirtualSimImsi(Context context, String s)
        {
            miui.provider.ExtraSettings.Secure.putString(context.getContentResolver(), "virtual_sim_imsi", s);
        }

        public static void setVirtualSimSlotId(Context context, int i)
        {
            miui.provider.ExtraSettings.Secure.putInt(context.getContentResolver(), "virtual_sim_slot_id", i);
        }

        public static void setVirtualSimStatus(Context context, int i)
        {
            miui.provider.ExtraSettings.Secure.putInt(context.getContentResolver(), "virtual_sim_status", i);
        }

        public static void setVirtualSimType(Context context, int i)
        {
            miui.provider.ExtraSettings.Secure.putInt(context.getContentResolver(), "virtual_sim_type", i);
        }

        public static final int DC_ONLY_VIRTUAL_SIM = 1;
        public static final String MISIM_ID = "misim_id";
        public static final String MISIM_IMSI = "misim_imsi";
        public static final int NORMAL_VIRTUAL_SIM = 0;
        public static final int STATUS_DISABLING = 1;
        public static final int STATUS_ENABLING = 0;
        public static final int STATUS_NORMAL = 2;
        public static final String VIRTUAL_SIM_ICCID = "virtual_sim_iccid";
        public static final String VIRTUAL_SIM_IMSI = "virtual_sim_imsi";
        public static final String VIRTUAL_SIM_NETWORK_TYPE = "virtual_sim_network_type";
        public static final String VIRTUAL_SIM_SLOT_ID = "virtual_sim_slot_id";
        public static final String VIRTUAL_SIM_STATUS = "virtual_sim_status";
        public static final String VIRTUAL_SIM_TYPE = "virtual_sim_type";

        public VirtualSim()
        {
        }
    }

    public static final class XSpace
    {

        public static boolean belongToCrossXSpaceSecureSettings(String s, int i)
        {
            boolean flag;
            if(XSpaceUserHandle.isXSpaceUserId(i))
                flag = CROSS_PROFILE_SECURE_SETTINGS.contains(s);
            else
                flag = false;
            return flag;
        }

        public static int getAskType(Context context, String s)
        {
            if(s.equals("com.tencent.mm.ui.tools.ShareImgUI"))
                break MISSING_BLOCK_LABEL_20;
            String s1 = s;
            if(!s.equals("com.tencent.mm.ui.tools.ShareToTimeLineUI"))
                break MISSING_BLOCK_LABEL_23;
            s1 = "com.tencent.mm.plugin.base.stub.WXEntryActivity";
            s = s1;
            if(s1.equals("com.sina.weibo.composerinde.ComposerDispatchActivity"))
                s = "com.sina.weibo.SSOActivity";
            if(s.equals("com.tencent.mobileqq.activity.JumpActivity"))
                break MISSING_BLOCK_LABEL_57;
            s1 = s;
            if(!s.equals("com.tencent.mobileqq.activity.qfileJumpActivity"))
                break MISSING_BLOCK_LABEL_60;
            s1 = "com.tencent.mobileqq.activity.qqSendActivity";
            int i;
            try
            {
                i = Settings.Secure.getIntForUser(context.getContentResolver(), s1, 0);
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                return 0;
            }
            return i;
        }

        public static int getGuideNotificationTimes(Context context, String s)
        {
            int i;
            try
            {
                i = Settings.Secure.getIntForUser(context.getContentResolver(), s, 0);
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                return 0;
            }
            return i;
        }

        public static String getSettingAppType(String s)
        {
            String s1 = "";
            if(!"weixin_pay".equals(s)) goto _L2; else goto _L1
_L1:
            s1 = "com.tencent.mm.plugin.base.stub.WXPayEntryActivity";
_L4:
            return s1;
_L2:
            if("weixin_share".equals(s))
                s1 = "com.tencent.mm.plugin.base.stub.WXEntryActivity";
            else
            if("weixin_open".equals(s))
                s1 = "com.tencent.mm.ui.LauncherUI";
            else
            if("weibo_send".equals(s))
                s1 = "com.sina.weibo.SSOActivity";
            else
            if("weibo_open".equals(s))
                s1 = "com.sina.weibo.SplashActivity";
            else
            if("qq_send".equals(s))
                s1 = "com.tencent.mobileqq.activity.qqSendActivity";
            else
            if("qq_login".equals(s))
                s1 = "com.tencent.open.agent.AgentActivity";
            else
            if("qq_open".equals(s))
                s1 = "com.tencent.mobileqq.activity.SplashActivity";
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static void resetDefaultSetting(Context context, String s)
        {
            if(!"com.tencent.mm".equals(s)) goto _L2; else goto _L1
_L1:
            setAskType(context, "com.tencent.mm.plugin.base.stub.WXPayEntryActivity", 0);
            setAskType(context, "com.tencent.mm.plugin.base.stub.WXEntryActivity", 0);
            setAskType(context, "com.tencent.mm.ui.LauncherUI", 0);
_L4:
            return;
_L2:
            if("com.sina.weibo".equals(s))
            {
                setAskType(context, "com.sina.weibo.SSOActivity", 0);
                setAskType(context, "com.sina.weibo.SplashActivity", 0);
            } else
            if("com.tencent.mobileqq".equals(s))
            {
                setAskType(context, "com.tencent.mobileqq.activity.qqSendActivity", 0);
                setAskType(context, "com.tencent.open.agent.AgentActivity", 0);
                setAskType(context, "com.tencent.mobileqq.activity.SplashActivity", 0);
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static void setAskType(Context context, String s, int i)
        {
            String s1;
label0:
            {
                if(!s.equals("com.tencent.mm.ui.tools.ShareImgUI"))
                {
                    s1 = s;
                    if(!s.equals("com.tencent.mm.ui.tools.ShareToTimeLineUI"))
                        break label0;
                }
                s1 = "com.tencent.mm.plugin.base.stub.WXEntryActivity";
            }
label1:
            {
                s = s1;
                if(s1.equals("com.sina.weibo.composerinde.ComposerDispatchActivity"))
                    s = "com.sina.weibo.SSOActivity";
                if(!s.equals("com.tencent.mobileqq.activity.JumpActivity"))
                {
                    s1 = s;
                    if(!s.equals("com.tencent.mobileqq.activity.qfileJumpActivity"))
                        break label1;
                }
                s1 = "com.tencent.mobileqq.activity.qqSendActivity";
            }
            Settings.Secure.putIntForUser(context.getContentResolver(), s1, i, 0);
        }

        public static void setGuideNotificationTimes(Context context, String s, int i)
        {
            Settings.Secure.putIntForUser(context.getContentResolver(), s, i, 0);
        }

        public static final Set CROSS_PROFILE_SECURE_SETTINGS;
        public static final int DEFAULT_SETTING_ALWAYS_ASK = 0;
        public static final int DEFAULT_SETTING_DEFAULT = 0;
        public static final int DEFAULT_SETTING_MAIN = 1;
        public static final int DEFAULT_SETTING_XSPACE = 2;
        public static final String KEY_DEFAULT_GUIDE_TIMES = "key_default_guide_times";
        public static final String KEY_WEIBO_OPEN = "com.sina.weibo.SplashActivity";
        public static final String KEY_WEIBO_SEND = "com.sina.weibo.composerinde.ComposerDispatchActivity";
        public static final String KEY_WEIBO_SHARE = "com.sina.weibo.SSOActivity";
        public static final String KEY_WEIXIN_OPEN = "com.tencent.mm.ui.LauncherUI";
        public static final String KEY_WEIXIN_PAY = "com.tencent.mm.plugin.base.stub.WXPayEntryActivity";
        public static final String KEY_WEIXIN_SEND_TO_CIRCLE = "com.tencent.mm.ui.tools.ShareToTimeLineUI";
        public static final String KEY_WEIXIN_SEND_TO_FRIEND = "com.tencent.mm.ui.tools.ShareImgUI";
        public static final String KEY_WEIXIN_SHARE = "com.tencent.mm.plugin.base.stub.WXEntryActivity";
        public static final String KEY_XSPACE_BOOT_GUIDE_TIMES = "key_xspace_boot_guide_times";
        public static final String KEY_XSPACE_COMPETE_GUIDE_TIMES = "key_xspace_compete_guide_times";
        public static final String KEY_XSPACE_QQ_LOGIN = "com.tencent.open.agent.AgentActivity";
        public static final String KEY_XSPACE_QQ_OPEN = "com.tencent.mobileqq.activity.SplashActivity";
        public static final String KEY_XSPACE_QQ_SEND = "com.tencent.mobileqq.activity.qqSendActivity";
        public static final String KEY_XSPACE_QQ_SEND_TO_FRIEND = "com.tencent.mobileqq.activity.JumpActivity";
        public static final String KEY_XSPACE_QQ_SEND_TO_PC = "com.tencent.mobileqq.activity.qfileJumpActivity";
        public static final String PARAM_INTENT_KEY_COMPETE_XSPACE_GUIDE = "param_intent_key_compete_xspace_guide";
        public static final String PARAM_INTENT_KEY_DEFAULT = "param_intent_key_default";
        public static final String PARAM_INTENT_KEY_DEFAULT_ASKTYPE = "param_intent_key_default_asktype";
        public static final String PARAM_INTENT_KEY_HAS_EXTRA = "param_intent_key_has_extra";
        public static final String PARAM_INTENT_VALUE_COMPETE_BOOT_XSPACE_GUIDE = "param_intent_value_compete_boot_xspace_guide";
        public static final String PARAM_INTENT_VALUE_COMPETE_INSTALL_XSPACE_GUIDE = "param_intent_value_compete_install_xspace_guide";
        public static final String PARAM_INTENT_VALUE_COMPETE_TIMING_XSPACE_GUIDE = "param_intent_value_compete_timing_xspace_guide";
        public static final String PARAM_INTENT_VALUE_DEFAULT = "param_intent_value_default";
        public static final String PARAM_INTENT_VALUE_HAS_EXTRA = "param_intent_value_has_extra";
        public static final String PARAM_INTENT_VALUE_UNINSTALL_XSPACE_USER = "param_intent_value_uninstall_xspace_user";
        public static final String PREKEY_XSPACE_QQ_LOGIN = "qq_login";
        public static final String PREKEY_XSPACE_QQ_OPEN = "qq_open";
        public static final String PREKEY_XSPACE_QQ_SEND = "qq_send";
        public static final String PREKEY_XSPACE_WEIBO_OPEN = "weibo_open";
        public static final String PREKEY_XSPACE_WEIBO_SEND = "weibo_send";
        public static final String PREKEY_XSPACE_WEIXIN_OPEN = "weixin_open";
        public static final String PREKEY_XSPACE_WEIXIN_PAY = "weixin_pay";
        public static final String PREKEY_XSPACE_WEIXIN_SHARE = "weixin_share";
        public static final String QQ_PACKAGE_NAME = "com.tencent.mobileqq";
        public static final String WEIBO_PACKAGE_NAME = "com.sina.weibo";
        public static final String WEIXIN_PACKAGE_NAME = "com.tencent.mm";
        public static ArrayList sCompeteXSpaceApps;
        public static final ArrayList sSupportDefaultSettingApps;

        static 
        {
            CROSS_PROFILE_SECURE_SETTINGS = new ArraySet();
            CROSS_PROFILE_SECURE_SETTINGS.add("lock_screen_allow_private_notifications");
            CROSS_PROFILE_SECURE_SETTINGS.add("lock_screen_show_notifications");
            sSupportDefaultSettingApps = new ArrayList();
            sSupportDefaultSettingApps.add("com.tencent.mm.plugin.base.stub.WXPayEntryActivity");
            sSupportDefaultSettingApps.add("com.tencent.mm.ui.tools.ShareImgUI");
            sSupportDefaultSettingApps.add("com.tencent.mm.ui.tools.ShareToTimeLineUI");
            sSupportDefaultSettingApps.add("com.tencent.mm.plugin.base.stub.WXEntryActivity");
            sSupportDefaultSettingApps.add("com.tencent.mm.ui.LauncherUI");
            sSupportDefaultSettingApps.add("com.sina.weibo.composerinde.ComposerDispatchActivity");
            sSupportDefaultSettingApps.add("com.sina.weibo.SSOActivity");
            sSupportDefaultSettingApps.add("com.sina.weibo.SplashActivity");
            sSupportDefaultSettingApps.add("com.tencent.mobileqq.activity.JumpActivity");
            sSupportDefaultSettingApps.add("com.tencent.mobileqq.activity.qfileJumpActivity");
            sSupportDefaultSettingApps.add("com.tencent.open.agent.AgentActivity");
            sSupportDefaultSettingApps.add("com.tencent.mobileqq.activity.SplashActivity");
            sCompeteXSpaceApps = new ArrayList();
            sCompeteXSpaceApps.add("com.excelliance.multiaccount");
            sCompeteXSpaceApps.add("com.lbe.parallel.intl");
            sCompeteXSpaceApps.add("com.parallel.space.lite");
            sCompeteXSpaceApps.add("info.cloneapp.mochat.in.goast");
            sCompeteXSpaceApps.add("com.jiubang.commerce.gomultiple");
            sCompeteXSpaceApps.add("com.applisto.appcloner");
            sCompeteXSpaceApps.add("com.trigtech.privateme");
            sCompeteXSpaceApps.add("com.polestar.multiaccount");
            sCompeteXSpaceApps.add("com.vaibhav.accountsmanager");
            sCompeteXSpaceApps.add("com.in.parallel.accounts");
            sCompeteXSpaceApps.add("com.lbe.parallel");
            sCompeteXSpaceApps.add("com.excelliance.dualaid");
        }

        public XSpace()
        {
        }
    }


    public MiuiSettings()
    {
    }

    public static void getConfigurationForUser(ContentResolver contentresolver, Configuration configuration, int i)
    {
        int j;
        j = Settings.System.getInt(contentresolver, "ui_mode_scale", 1) & 0xf;
        break MISSING_BLOCK_LABEL_12;
        if(j == 12 || j == 13 || j == 14 || j == 15)
            i = 1;
        else
        if(j == 11)
            i = 1;
        else
            i = 0;
        if(i != 0)
            configuration.fontScale = MiuiConfiguration.getFontScale(j);
        return;
    }

    public static final String ACTION_ACCOUNT_LIST = "android.settings.ACCOUNT_LIST";
    public static String APP_LOCK_USE_FINGERPRINT_STATE = "com_miui_applicatinlock_use_fingerprint_state";
    public static final Set CROSS_PROFILE_SETTINGS;
    private static final String DOWNLOADMANAGER_DEBUG_DP_PATH = "debug_dp_path";
    private static final String DOWNLOADMANAGER_DEBUG_SWITCH = "debug_switch";
    private static final String DOWNLOADMANAGER_XUNLEI_USAGE_PERMISSION = "xunlei_usage_permission";
    private static final String DOWNLOADMANAGER_XUNLEI_VIP_TOKEN = "vip_token";
    private static final String DOWNLOADMANAGER__XUNLEI_TOKEN = "xunlei_token";
    private static final String LAST_SYNC_TIME_ANTISPAM = "setting_last_time_alert_antispam";
    private static final String LAST_SYNC_TIME_BROWSER = "setting_last_time_alert_com.miui.browser";
    private static final String LAST_SYNC_TIME_CALENDER = "setting_last_time_alert_com.android.calendar";
    private static final String LAST_SYNC_TIME_CALL_LOG = "setting_last_time_alert_call_log";
    private static final String LAST_SYNC_TIME_CONTACT = "setting_last_time_alert_com.android.contacts";
    private static final String LAST_SYNC_TIME_GALLERY = "setting_last_time_alert_com.miui.gallery.cloud.provider";
    private static final String LAST_SYNC_TIME_MUSIC = "setting_last_time_alert_com.miui.player";
    private static final String LAST_SYNC_TIME_NOTES = "setting_last_time_alert_notes";
    private static final String LAST_SYNC_TIME_RECORDER = "setting_last_time_alert_records";
    private static final String LAST_SYNC_TIME_SMS = "setting_last_time_alert_sms";
    private static final String LAST_SYNC_TIME_WIFI = "setting_last_time_alert_wifi";
    private static final String MARKET_ENABLE_SHARE_PROGRESS_STATUS = "com.xiaomi.market.enable_share_progress_status";
    private static final String MARKET_ENABLE_SHARE_PROGRESS_STATUS_INTERNATIONAL = "com.xiaomi.discover.enable_share_progress_status";
    private static final String MICLOUD_GDPR_PERMISSION_GRANTED = "micloud_gdpr_permission_granted";
    private static final String MICLOUD_NETWORK_AVAILABILITY_KEY = "micloud_network_availability";
    private static final String MIPICKS_ENABLE_SHARE_PROGRESS_STATUS = "com.xiaomi.mipicks.enable_share_progress_status";
    private static final String MIUI_HOME_ENABLE_SHARE_PROGRESS_STATUS = "com.miui.home.enable_share_progress_status";
    private static final String OPEN_PDC_HOST_KEY = "com.xiaomi.opensdk.pdc.host";
    private static final String PREF_KEY_WALLPAPER_SCREEN_SPAN = "pref_key_wallpaper_screen_span";
    public static final String PRIVACY_PASSWORD_BIND_XIAOMI_ACCOUNT = "privacy_password_bind_xiaomi_account";
    public static final String PRIVACY_PASSWORD_FINGERPRINT_AUTHENTICATION_NUM = "privacy_password_finger_authentication_num";
    private static final String SCREEN_RECORDER_SHOW_TOUCHES = "show_touches";
    private static final String SETTING_MICLOUD_ACCOUNTNAME = "micloud_accountname";
    private static final String SETTING_MICLOUD_ACCOUNTNAME_V2 = "micloud_accountname_v2";
    private static final String SETTING_MICLOUD_HOSTS = "micloud_hosts";
    private static final String SETTING_MICLOUD_HOSTS_V2 = "micloud_hosts_v2";
    private static final String SETTING_MICLOUD_UPDATEHOSTS_THIRD_PARTY = "micloud_updatehosts_third_party";
    public static final String SETTING_UPDATABLE_SYSTEM_APP_COUNT = "updatable_system_app_count";
    private static final String SYNC_SETTING_ANTISPAM_0 = "sync_for_sim_com.xiaomi-antispam-0";
    private static final String SYNC_SETTING_ANTISPAM_1 = "sync_for_sim_com.xiaomi-antispam-1";
    private static final String SYNC_SETTING_BROWSER_0 = "sync_for_sim_com.xiaomi-com.miui.browser-0";
    private static final String SYNC_SETTING_BROWSER_1 = "sync_for_sim_com.xiaomi-com.miui.browser-1";
    private static final String SYNC_SETTING_CALENDER_0 = "sync_for_sim_com.xiaomi-com.android.calendar-0";
    private static final String SYNC_SETTING_CALENDER_1 = "sync_for_sim_com.xiaomi-com.android.calendar-1";
    private static final String SYNC_SETTING_CALL_LOG_0 = "sync_for_sim_com.xiaomi-call_log-0";
    private static final String SYNC_SETTING_CALL_LOG_1 = "sync_for_sim_com.xiaomi-call_log-1";
    private static final String SYNC_SETTING_CONTACT_0 = "sync_for_sim_com.xiaomi-com.android.contacts-0";
    private static final String SYNC_SETTING_CONTACT_1 = "sync_for_sim_com.xiaomi-com.android.contacts-1";
    private static final String SYNC_SETTING_GALLERY_0 = "sync_for_sim_com.xiaomi-com.miui.gallery.cloud.provider-0";
    private static final String SYNC_SETTING_GALLERY_1 = "sync_for_sim_com.xiaomi-com.miui.gallery.cloud.provider-1";
    private static final String SYNC_SETTING_MUSIC_0 = "sync_for_sim_com.xiaomi-com.miui.player-0";
    private static final String SYNC_SETTING_MUSIC_1 = "sync_for_sim_com.xiaomi-com.miui.player-1";
    private static final String SYNC_SETTING_NOTES_0 = "sync_for_sim_com.xiaomi-notes-0";
    private static final String SYNC_SETTING_NOTES_1 = "sync_for_sim_com.xiaomi-notes-1";
    private static final String SYNC_SETTING_RECORDER_0 = "sync_for_sim_com.xiaomi-records-0";
    private static final String SYNC_SETTING_RECORDER_1 = "sync_for_sim_com.xiaomi-records-1";
    private static final String SYNC_SETTING_SMS_0 = "sync_for_sim_com.xiaomi-sms-0";
    private static final String SYNC_SETTING_SMS_1 = "sync_for_sim_com.xiaomi-sms-1";
    private static final String SYNC_SETTING_WIFI_0 = "sync_for_sim_com.xiaomi-wifi-0";
    private static final String SYNC_SETTING_WIFI_1 = "sync_for_sim_com.xiaomi-wifi-1";

    static 
    {
        CROSS_PROFILE_SETTINGS = new ArraySet();
        CROSS_PROFILE_SETTINGS.add("ringtone");
        CROSS_PROFILE_SETTINGS.add("notification_sound");
        CROSS_PROFILE_SETTINGS.add("alarm_alert");
        CROSS_PROFILE_SETTINGS.add("user_rotation");
        CROSS_PROFILE_SETTINGS.add("accelerometer_rotation");
        CROSS_PROFILE_SETTINGS.add("hide_rotation_lock_toggle_for_accessibility");
        CROSS_PROFILE_SETTINGS.add("frequent_phrases");
    }

    // Unreferenced inner class android/provider/MiuiSettings$Secure$2

/* anonymous class */
    static final class Secure._cls2
        implements android.content.DialogInterface.OnClickListener
    {

        public void onClick(DialogInterface dialoginterface, int i)
        {
            if(i == -1)
                activity.startActivityForResult(Secure._2D_wrap0(businessKey), setPasswordRequestCode);
            if(dialogClickListener != null)
                dialogClickListener.onClick(dialoginterface, i);
        }

        final Activity val$activity;
        final String val$businessKey;
        final android.content.DialogInterface.OnClickListener val$dialogClickListener;
        final int val$setPasswordRequestCode;

            
            {
                activity = activity1;
                businessKey = s;
                setPasswordRequestCode = i;
                dialogClickListener = onclicklistener;
                super();
            }
    }

}
