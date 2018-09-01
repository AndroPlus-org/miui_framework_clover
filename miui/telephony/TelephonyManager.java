// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.telephony;

import android.content.Context;
import android.os.Process;
import android.os.ResultReceiver;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.telephony.PhoneStateListener;
import android.telephony.Rlog;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import miui.os.Build;

// Referenced classes of package miui.telephony:
//            TelephonyManagerEx

public abstract class TelephonyManager
{
    static class Holder
    {

        static final TelephonyManagerEx INSTANCE = TelephonyManagerEx.getDefault();


        private Holder()
        {
        }
    }


    public TelephonyManager()
    {
        BUILD_OPERATOR_TYPE = null;
    }

    public static boolean checkCallingOrSelfPermissionGranted(int i)
    {
        return UserHandle.getAppId(i) == 1000 || Process.myUid() >= 0 && UserHandle.isSameApp(i, Process.myUid());
    }

    public static TelephonyManager getDefault()
    {
        return Holder.INSTANCE;
    }

    public static boolean isCustForEsVodafone()
    {
        return "es_vodafone".equals(CUSTOMIZED_REGION);
    }

    public static boolean isCustSingleSimDevice()
    {
        return IS_CUST_SINGLE_SIM;
    }

    public static boolean isDomesticRoamingEnable(Context context)
    {
        boolean flag = true;
        if(android.provider.Settings.Global.getInt(context.getContentResolver(), "data_domestic_roaming", 1) == 0)
            flag = false;
        return flag;
    }

    public static boolean isSupportDomesticRoaming()
    {
        return Build.checkRegion("PL");
    }

    public static boolean setDomesticRoamingEnable(Context context, boolean flag)
    {
        context = context.getContentResolver();
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        return android.provider.Settings.Global.putInt(context, "data_domestic_roaming", i);
    }

    public abstract int getCallState();

    public abstract int getCallStateForSlot(int i);

    public abstract int getCallStateForSubscription(int i);

    public int getCtVolteSupportedMode()
    {
        return 0;
    }

    public abstract int getDataActivity();

    public abstract int getDataActivityForSlot(int i);

    public abstract int getDataActivityForSubscription(int i);

    public abstract int getDataState();

    public abstract int getDataStateForSlot(int i);

    public abstract int getDataStateForSubscription(int i);

    public abstract String getDeviceId();

    public abstract String getDeviceIdForSlot(int i);

    public abstract String getDeviceIdForSubscription(int i);

    public List getDeviceIdList()
    {
        Rlog.d("TelephonyManager", "unexpected getDeviceIdList method call");
        return null;
    }

    public abstract String getDeviceSoftwareVersion();

    public abstract String getDeviceSoftwareVersionForSlot(int i);

    public abstract String getDeviceSoftwareVersionForSubscription(int i);

    public int getIccCardCount()
    {
        int i = getPhoneCount();
        int j = 0;
        for(int k = 0; k < i;)
        {
            int l = j;
            if(hasIccCard(k))
                l = j + 1;
            k++;
            j = l;
        }

        return j;
    }

    public abstract String getImei();

    public abstract String getImeiForSlot(int i);

    public abstract String getImeiForSubscription(int i);

    public List getImeiList()
    {
        return new ArrayList(0);
    }

    public abstract String getLine1Number();

    public abstract String getLine1NumberForSlot(int i);

    public abstract String getLine1NumberForSubscription(int i);

    public abstract String getMeid();

    public abstract String getMeidForSlot(int i);

    public abstract String getMeidForSubscription(int i);

    public List getMeidList()
    {
        return new ArrayList(0);
    }

    public abstract String getMiuiDeviceId();

    public abstract String getNetworkCountryIso();

    public abstract String getNetworkCountryIsoForSlot(int i);

    public abstract String getNetworkCountryIsoForSubscription(int i);

    public abstract String getNetworkOperator();

    public abstract String getNetworkOperatorForSlot(int i);

    public abstract String getNetworkOperatorForSubscription(int i);

    public abstract String getNetworkOperatorName();

    public abstract String getNetworkOperatorNameForSlot(int i);

    public abstract String getNetworkOperatorNameForSubscription(int i);

    public abstract int getNetworkType();

    public abstract int getNetworkTypeForSlot(int i);

    public abstract int getNetworkTypeForSubscription(int i);

    public abstract int getPhoneCount();

    public abstract int getPhoneType();

    public abstract int getPhoneTypeForSlot(int i);

    public abstract int getPhoneTypeForSubscription(int i);

    public abstract String getSimCountryIso();

    public abstract String getSimCountryIsoForSlot(int i);

    public abstract String getSimCountryIsoForSubscription(int i);

    public abstract String getSimOperator();

    public abstract String getSimOperatorForSlot(int i);

    public abstract String getSimOperatorForSubscription(int i);

    public abstract String getSimOperatorName();

    public abstract String getSimOperatorNameForSlot(int i);

    public abstract String getSimOperatorNameForSubscription(int i);

    public abstract String getSimSerialNumber();

    public abstract String getSimSerialNumberForSlot(int i);

    public abstract String getSimSerialNumberForSubscription(int i);

    public abstract int getSimState();

    public abstract int getSimStateForSlot(int i);

    public abstract int getSimStateForSubscription(int i);

    public abstract String getSmallDeviceId();

    public String getSpn(String s, int i, String s1, boolean flag)
    {
        throw new UnsupportedOperationException("Only support android L and above");
    }

    public abstract String getSubscriberId();

    public abstract String getSubscriberIdForSlot(int i);

    public abstract String getSubscriberIdForSubscription(int i);

    public abstract String getVoiceMailAlphaTag();

    public abstract String getVoiceMailAlphaTagForSlot(int i);

    public abstract String getVoiceMailAlphaTagForSubscription(int i);

    public abstract String getVoiceMailNumber();

    public abstract String getVoiceMailNumberForSlot(int i);

    public abstract String getVoiceMailNumberForSubscription(int i);

    public abstract boolean hasIccCard();

    public abstract boolean hasIccCard(int i);

    public boolean isChinaTelecomTest(String s)
    {
        if(Build.IS_CT_CUSTOMIZATION_TEST && TextUtils.isEmpty(s) ^ true)
        {
            boolean flag;
            if(!s.equals("46003") && !s.equals("46011") && !s.equals("45502") && !s.equals("45507"))
                flag = s.equals("00101");
            else
                flag = true;
            return flag;
        } else
        {
            return false;
        }
    }

    public boolean isDisableLte(boolean flag)
    {
        if(!Build.IS_GLOBAL_BUILD)
            return false;
        boolean flag1;
        if(!"ido".equals(Build.DEVICE))
            flag1 = "kenzo".equals(Build.DEVICE);
        else
            flag1 = true;
        if(!flag1 || flag ^ true)
            return flag1;
        int i = getPhoneCount();
        for(int j = 0; j < i; j++)
        {
            String s = getSimOperatorForSlot(j);
            if(s != null && s.startsWith("510"))
                return true;
        }

        return false;
    }

    public boolean isDualVolteSupported()
    {
        return false;
    }

    public boolean isImsRegistered(int i)
    {
        return false;
    }

    public abstract boolean isMultiSimEnabled();

    public boolean isSameOperator(String s, String s1)
    {
        throw new UnsupportedOperationException("Only support android L and above");
    }

    public abstract boolean isVoiceCapable();

    public boolean isVolteEnabledByPlatform()
    {
        return false;
    }

    public boolean isVolteEnabledByPlatform(int i)
    {
        return false;
    }

    public boolean isVolteEnabledByUser()
    {
        return false;
    }

    public boolean isVolteEnabledByUser(int i)
    {
        return false;
    }

    public boolean isVtEnabledByPlatform()
    {
        return false;
    }

    public boolean isVtEnabledByPlatform(int i)
    {
        return false;
    }

    public abstract void listen(PhoneStateListener phonestatelistener, int i);

    public abstract void listenForSlot(int i, PhoneStateListener phonestatelistener, int j);

    public abstract void listenForSubscription(int i, PhoneStateListener phonestatelistener, int j);

    public void setCallForwardingOption(int i, int j, int k, String s, ResultReceiver resultreceiver)
    {
        throw new UnsupportedOperationException("setCallForwardingOption not supported");
    }

    public void setIccCardActivate(int i, boolean flag)
    {
        throw new UnsupportedOperationException("Only support android L and above");
    }

    public static final String ACTION_PHONE_STATE_CHANGED = "android.intent.action.PHONE_STATE";
    public static final String ACTION_RESPOND_VIA_MESSAGE = "android.intent.action.RESPOND_VIA_MESSAGE";
    public static final int CALL_STATE_IDLE = 0;
    public static final int CALL_STATE_OFFHOOK = 2;
    public static final int CALL_STATE_RINGING = 1;
    public static final int CF_ACTION_DISABLE = 0;
    public static final int CF_ACTION_ENABLE = 1;
    public static final int CF_ACTION_ERASURE = 4;
    public static final int CF_ACTION_REGISTRATION = 3;
    public static final int CF_REASON_ALL = 4;
    public static final int CF_REASON_ALL_CONDITIONAL = 5;
    public static final int CF_REASON_BUSY = 1;
    public static final int CF_REASON_NOT_REACHABLE = 3;
    public static final int CF_REASON_NO_REPLY = 2;
    public static final int CF_REASON_UNCONDITIONAL = 0;
    public static final int CT_VOLTE_MODE_HVOLTE = 2;
    public static final int CT_VOLTE_MODE_NOT_SUPPORT = 0;
    public static final int CT_VOLTE_MODE_VOLTE_ONLY = 1;
    public static final String CUSTOMIZED_REGION = SystemProperties.get("ro.miui.customized.region", "");
    public static final int DATA_ACTIVITY_DORMANT = 4;
    public static final int DATA_ACTIVITY_IN = 1;
    public static final int DATA_ACTIVITY_INOUT = 3;
    public static final int DATA_ACTIVITY_NONE = 0;
    public static final int DATA_ACTIVITY_OUT = 2;
    public static final int DATA_CONNECTED = 2;
    public static final int DATA_CONNECTING = 1;
    public static final int DATA_DISCONNECTED = 0;
    public static final String DATA_DOMESTIC_ROAMING = "data_domestic_roaming";
    public static final int DATA_SUSPENDED = 3;
    public static final String EXTRA_INCOMING_NUMBER = "incoming_number";
    public static final String EXTRA_STATE = "state";
    public static final String EXTRA_STATE_IDLE;
    public static final String EXTRA_STATE_OFFHOOK;
    public static final String EXTRA_STATE_RINGING;
    private static final boolean IS_CUST_SINGLE_SIM;
    public static final String MCC_CHINA = "460";
    public static final int NETWORK_TYPE_1xRTT = 7;
    public static final int NETWORK_TYPE_CDMA = 4;
    public static final int NETWORK_TYPE_DC_HSPAP = 20;
    public static final int NETWORK_TYPE_EDGE = 2;
    public static final int NETWORK_TYPE_EHRPD = 14;
    public static final int NETWORK_TYPE_EVDO_0 = 5;
    public static final int NETWORK_TYPE_EVDO_A = 6;
    public static final int NETWORK_TYPE_EVDO_B = 12;
    public static final int NETWORK_TYPE_GPRS = 1;
    public static final int NETWORK_TYPE_HSDPA = 8;
    public static final int NETWORK_TYPE_HSPA = 10;
    public static final int NETWORK_TYPE_HSPAP = 15;
    public static final int NETWORK_TYPE_HSUPA = 9;
    public static final int NETWORK_TYPE_IDEN = 11;
    public static final int NETWORK_TYPE_LTE = 13;
    public static final int NETWORK_TYPE_LTE_CA = 19;
    public static final int NETWORK_TYPE_UMTS = 3;
    public static final int NETWORK_TYPE_UNKNOWN = 0;
    public static final String OPERATOR_NUMERIC_CHINA_MOBILE = "46000";
    public static final String OPERATOR_NUMERIC_CHINA_TELECOM = "46003";
    public static final String OPERATOR_NUMERIC_CHINA_UNICOM = "46001";
    public static final int PHONE_TYPE_CDMA = 2;
    public static final int PHONE_TYPE_GSM = 1;
    public static final int PHONE_TYPE_NONE = 0;
    public static final int PHONE_TYPE_SIP = 3;
    public static final int SET_CALL_FOWARD_FAILURE = -1;
    public static final int SET_CALL_FOWARD_NOT_SUPPORT = -2;
    public static final int SET_CALL_FOWARD_SUCCESS = 0;
    public static final int SET_CALL_FOWARD_UT_DATA_DISABLED = -3;
    public static final int SIM_STATE_ABSENT = 1;
    public static final int SIM_STATE_NETWORK_LOCKED = 4;
    public static final int SIM_STATE_PIN_REQUIRED = 2;
    public static final int SIM_STATE_PUK_REQUIRED = 3;
    public static final int SIM_STATE_READY = 5;
    public static final int SIM_STATE_UNKNOWN = 0;
    protected static final String TAG = "TeleMgr";
    private String BUILD_OPERATOR_TYPE;

    static 
    {
        boolean flag = true;
        EXTRA_STATE_IDLE = android.telephony.TelephonyManager.EXTRA_STATE_IDLE;
        EXTRA_STATE_RINGING = android.telephony.TelephonyManager.EXTRA_STATE_RINGING;
        EXTRA_STATE_OFFHOOK = android.telephony.TelephonyManager.EXTRA_STATE_OFFHOOK;
        if(SystemProperties.getInt("ro.miui.singlesim", 0) != 1)
            flag = false;
        IS_CUST_SINGLE_SIM = flag;
    }
}
