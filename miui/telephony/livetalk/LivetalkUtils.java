// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.telephony.livetalk;

import android.content.*;
import android.database.Cursor;
import android.util.Pair;
import miui.telephony.TelephonyManager;
import org.json.JSONArray;
import org.json.JSONException;

public class LivetalkUtils
{

    public LivetalkUtils()
    {
    }

    public static void addPrompt(Context context)
    {
        android.provider.Settings.System.putInt(context.getContentResolver(), "need_prompt", 1);
    }

    public static boolean getInternalDialAvaiable(Context context)
    {
        return false;
    }

    public static boolean getInternationalDialAvaiable(Context context)
    {
        return false;
    }

    public static int getInternationalRemainMins(Context context)
    {
        return android.provider.Settings.System.getInt(context.getContentResolver(), "recent_country_remain_mins", 0);
    }

    public static Pair getLivetalkCleanerInfo(Context context)
    {
        return null;
    }

    public static int getLivetalkDialRange(Context context)
    {
        return android.provider.Settings.System.getInt(context.getContentResolver(), "livetalk_dial_range", 0);
    }

    public static Pair getLivetalkInfo(Context context)
    {
        return null;
    }

    public static Intent getLivetalkIntentWithParam(int i)
    {
        Intent intent = new Intent();
        intent.setAction("com.miui.livetalk.MY_LIVETALK_VIEW");
        intent.putExtra("fromView", i);
        return intent;
    }

    public static Pair getLivetalkOptimizeInfo(Context context)
    {
        return null;
    }

    public static int getLivetalkServiceStatus(Context context)
    {
        return 0;
    }

    public static int getLivetalkStatus(Context context)
    {
        return 0;
    }

    public static Pair getLivetalkinfoForKK(ContentResolver contentresolver, Context context)
    {
        return null;
    }

    public static Intent getPurchaseIntentWithParam(int i)
    {
        Intent intent = new Intent();
        intent.setAction("com.miui.livetalk.PURCHASE_VIEW");
        intent.putExtra("fromView", i);
        return intent;
    }

    public static int getRemainMins(Context context)
    {
        return android.provider.Settings.System.getInt(context.getContentResolver(), "livetalk_remain_minutes", 0);
    }

    public static int[] getSimActivatedState(Context context)
    {
        ContentResolver contentresolver = context.getContentResolver();
        int i = TelephonyManager.getDefault().getPhoneCount();
        context = new int[i];
        for(int j = 0; j < i; j++)
            context[j] = android.provider.Settings.System.getInt(contentresolver, (new StringBuilder()).append("sim_card_activated_status").append(j).toString(), 0);

        return context;
    }

    public static String[] getSimNumber(Context context)
    {
        context = context.getContentResolver();
        int i = TelephonyManager.getDefault().getPhoneCount();
        String as[] = new String[i];
        for(int j = 0; j < i; j++)
            as[j] = android.provider.Settings.System.getString(context, (new StringBuilder()).append("sim_card_number").append(j).toString());

        return as;
    }

    public static Intent getWelComeIntentWithParam(int i)
    {
        Intent intent = new Intent();
        intent.setAction("com.miui.livetalk.WELCOME_VIEW");
        intent.putExtra("fromView", i);
        return intent;
    }

    public static boolean isInternalDialEnable(Context context)
    {
        return false;
    }

    public static boolean isInternationalDialEnable(Context context)
    {
        return false;
    }

    public static boolean isLiveTalkCallbackNumber(String s)
    {
        return false;
    }

    public static boolean isLivetalkEnabled(Context context)
    {
        boolean flag = true;
        if(android.provider.Settings.System.getInt(context.getContentResolver(), "livetalk_enabled", 0) != 1)
            flag = false;
        return flag;
    }

    public static boolean isLivetalkSwitchOn(Context context)
    {
        boolean flag = true;
        if(android.provider.Settings.System.getInt(context.getContentResolver(), "livetalk_switch_state", 1) != 1)
            flag = false;
        return flag;
    }

    public static boolean isLivetalkUseCurrentAccount(Context context)
    {
        boolean flag = true;
        if(android.provider.Settings.System.getInt(context.getContentResolver(), "livetalk_use_current_account", 0) != 1)
            flag = false;
        return flag;
    }

    public static boolean isPrompt(Context context)
    {
        return false;
    }

    public static boolean isShowInSafeCenter(Context context)
    {
        return false;
    }

    public static void removePrompt(Context context)
    {
        android.provider.Settings.System.putInt(context.getContentResolver(), "need_prompt", 0);
    }

    public static void setInternalDialEnable(Context context, boolean flag)
    {
        context = context.getContentResolver();
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        android.provider.Settings.System.putInt(context, "internal_dial_enable", i);
    }

    public static void setInternationalDialEnable(Context context, boolean flag)
    {
        context = context.getContentResolver();
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        android.provider.Settings.System.putInt(context, "international_dial_enable", i);
    }

    private static boolean supportLivetalk(Context context)
    {
        return false;
    }

    public static void updateLivetalkCallBackNumber(JSONArray jsonarray)
    {
        int i;
        if(jsonarray == null)
            return;
        sCallBackNumbers = new String[jsonarray.length()];
        i = 0;
_L2:
        if(i >= jsonarray.length())
            break; /* Loop/switch isn't completed */
        sCallBackNumbers[i] = jsonarray.getString(i);
        i++;
        if(true) goto _L2; else goto _L1
        jsonarray;
        jsonarray.printStackTrace();
_L1:
    }

    public static void updateLivetalkCallBackNumber(JSONArray jsonarray, int i)
        throws JSONException
    {
        if(jsonarray == null)
            return;
        int j = jsonarray.length();
        String as[] = new String[j];
        for(int k = 0; k < j; k++)
            as[k] = jsonarray.getString(k);

        sCallBackNumbers = as;
        LIVETALK_NUMBER_POOL_VERSION = i;
    }

    public static boolean updateLivetalkCallBackNumber(Cursor cursor)
    {
        if(cursor == null || cursor.getCount() == 0)
            return false;
        sCallBackNumbers = new String[cursor.getCount()];
        cursor.moveToFirst();
        int i = 0;
_L2:
        if(i >= cursor.getCount())
            break; /* Loop/switch isn't completed */
        sCallBackNumbers[i] = cursor.getString(cursor.getColumnIndex("number"));
        cursor.moveToNext();
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        cursor.close();
        return true;
        Object obj;
        obj;
        ((Exception) (obj)).printStackTrace();
        cursor.close();
        return false;
        obj;
        cursor.close();
        throw obj;
    }

    public static final String DAIL_MODE = "dial_mode";
    public static final int DIAL_MODE_GENERAL = 0;
    public static final int DIAL_MODE_LIVETALK = 1;
    public static final String ENABLE_LIVETALK_SUMMARY_CN = "enable_livetalk_summary_cn";
    public static final String ENABLE_LIVETALK_SUMMARY_EN = "enable_livetalk_summary_en";
    public static final String ENABLE_LIVETALK_TITLE_CN = "enable_livetalk_title_cn";
    public static final String ENABLE_LIVETALK_TITLE_EN = "enable_livetalk_title_en";
    public static final String FROM_VIEW = "fromView";
    public static final String INTENT_ACCEPT_BACK_CALL = "com.miui.livetalk_ACCEPT_BACK_CALL";
    private static final String INTENT_MY_LIVETALK = "com.miui.livetalk.MY_LIVETALK_VIEW";
    private static final String INTENT_PURCHASE_ACTION = "com.miui.livetalk.PURCHASE_VIEW";
    public static final String INTENT_RECORD_CALL_BACK_INFO = "com.miui.livetalk_RECORD_CALLBACK_INFO";
    private static final String INTENT_WELCOME_ACTION = "com.miui.livetalk.WELCOME_VIEW";
    public static final String IS_LIVETALK_DIAL = "isLivetalk";
    public static final int IS_NEED_PROMPT = 1;
    public static final int LIVETALK_AVAILABLE = 1;
    public static final String LIVETALK_AVAILABLE_STATUS = "livetalk_available_status";
    public static final String LIVETALK_DIAL_RANGE = "livetalk_dial_range";
    public static final int LIVETALK_DIAL_RANGE_DEMOSTIC = 1;
    public static final int LIVETALK_DIAL_RANGE_INTERNATIONAL = 2;
    public static final int LIVETALK_DIAL_RANGE_WHOLE = 0;
    public static final String LIVETALK_ENABLED = "livetalk_enabled";
    public static final String LIVETALK_INTERNAL_DIAL_AVAIABLE = "internal_dial_avaiable";
    public static final String LIVETALK_INTERNAL_DIAL_ENABLE = "internal_dial_enable";
    public static final String LIVETALK_INTERNATIONAL_DIAL_AVAIABLE = "international_dial_avaiable";
    public static final String LIVETALK_INTERNATIONAL_DIAL_ENABLE = "international_dial_enable";
    public static final int LIVETALK_NOT_AVAILABLE = 0;
    public static int LIVETALK_NUMBER_POOL_VERSION = 0;
    public static final String LIVETALK_RECENT_COUNTRY_REMAIN_MINS = "recent_country_remain_mins";
    public static final String LIVETALK_REMAIN_MINUTES = "livetalk_remain_minutes";
    public static final String LIVETALK_SERVICE_NAME = "com.miui.livetalk.service.LivetalkService";
    public static final String LIVETALK_SERVICE_STATUS = "livetalk_service_status";
    public static final String LIVETALK_SWITCH_STATE = "livetalk_switch_state";
    public static final String LIVETALK_USE_CURRENT_MI_ACCOUNT = "livetalk_use_current_account";
    public static final int LIVETALK_WITH_170 = 2;
    private static final String META_DATA_SUPPORT_LIVETALK = "support_livetalk";
    public static final int MY_LIVETALK_FROM_CONTACTS = 202;
    public static final int MY_LIVETALK_FROM_NOTIFICATION = 200;
    public static final int MY_LIVETALK_FROM_SETTING = 201;
    public static final String NEED_PROMPT = "need_prompt";
    public static final int NOT_NEED_PROMPT = 0;
    public static final String ONLY_REGULAR_CALL = "only_regular_call";
    public static final String PARAM_NUMBER = "number";
    public static final int PURCHASE_FROM_DIALPAGE = 2;
    public static final int PURCHASE_FROM_INTERNATIONAL = 8;
    public static final int PURCHASE_FROM_NOTIFICATION = 5;
    public static final int PURCHASE_FROM_SAFE_CENTER_CLEANER = 7;
    public static final int PURCHASE_FROM_SAFE_CENTER_OPTIMIZE = 6;
    public static final int PURCHASE_FROM_SETTING = 4;
    public static final int PURCHASE_FROM_SMS = 1;
    public static final int PURCHASE_FROM_YELLOWPAGE = 3;
    public static final String SAFE_CENTER_CLEANER_SUMMARY = "safe_center_cleaner_summary";
    public static final String SAFE_CENTER_CLEANER_TITLE = "safe_center_cleaner_title";
    public static final String SAFE_CENTER_OPTIMIZE_SUMMARY_CN = "safe_center_optimize_summary_cn";
    public static final String SAFE_CENTER_OPTIMIZE_SUMMARY_EN = "safe_center_optimize_summary_en";
    public static final String SAFE_CENTER_OPTIMIZE_TITLE_CN = "safe_center_optimize_title_cn";
    public static final String SAFE_CENTER_OPTIMIZE_TITLE_EN = "safe_center_optimize_title_en";
    public static final String SIM_CARD_ACTIVATED_STATE = "sim_card_activated_status";
    public static final String SIM_CARD_NUMBER = "sim_card_number";
    private static final String TAG = "LivetalkUtils";
    public static final String USER_CONFIG_COMPLETED = "user_config_completed";
    public static final int WELCOME_FROM_PURCHASE = 102;
    public static final int WELCOME_FROM_SETTING = 101;
    private static String sCallBackNumbers[];

    static 
    {
        LIVETALK_NUMBER_POOL_VERSION = 0;
    }
}
