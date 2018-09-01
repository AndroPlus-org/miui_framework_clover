// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.INetworkPolicyManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.DisplayMetrics;
import com.android.internal.telephony.IOnSubscriptionsChangedListener;
import com.android.internal.telephony.ISub;
import com.android.internal.telephony.ITelephonyRegistry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// Referenced classes of package android.telephony:
//            SubscriptionInfo, TelephonyManager, Rlog, SubscriptionPlan

public class SubscriptionManager
{
    public static class OnSubscriptionsChangedListener
    {

        static Handler _2D_get0(OnSubscriptionsChangedListener onsubscriptionschangedlistener)
        {
            return onsubscriptionschangedlistener.mHandler;
        }

        private void log(String s)
        {
            Rlog.d("SubscriptionManager", s);
        }

        public void onSubscriptionsChanged()
        {
        }

        IOnSubscriptionsChangedListener callback;
        private final Handler mHandler;

        public OnSubscriptionsChangedListener()
        {
            Looper looper;
            if(Looper.myLooper() == null)
                looper = Looper.getMainLooper();
            else
                looper = Looper.myLooper();
            mHandler = new _cls1(looper);
            callback = new _cls2();
        }
    }


    public SubscriptionManager(Context context)
    {
        mContext = context;
    }

    public static SubscriptionManager from(Context context)
    {
        return (SubscriptionManager)context.getSystemService("telephony_subscription_service");
    }

    public static boolean getBooleanSubscriptionProperty(int i, String s, boolean flag, Context context)
    {
        boolean flag1;
        flag1 = true;
        s = getSubscriptionProperty(i, s, context);
        if(s == null)
            break MISSING_BLOCK_LABEL_40;
        i = Integer.parseInt(s);
        if(i == 1)
            flag = flag1;
        else
            flag = false;
        return flag;
        s;
        logd("getBooleanSubscriptionProperty NumberFormat exception");
        return flag;
    }

    public static int getDefaultDataSubscriptionId()
    {
        byte byte0 = -1;
        ISub isub = com.android.internal.telephony.ISub.Stub.asInterface(ServiceManager.getService("isub"));
        int i = byte0;
        if(isub != null)
            try
            {
                i = isub.getDefaultDataSubId();
            }
            catch(RemoteException remoteexception)
            {
                i = byte0;
            }
        return i;
    }

    public static int getDefaultSmsSubscriptionId()
    {
        byte byte0 = -1;
        ISub isub = com.android.internal.telephony.ISub.Stub.asInterface(ServiceManager.getService("isub"));
        int i = byte0;
        if(isub != null)
            try
            {
                i = isub.getDefaultSmsSubId();
            }
            catch(RemoteException remoteexception)
            {
                i = byte0;
            }
        return i;
    }

    public static int getDefaultSubscriptionId()
    {
        byte byte0 = -1;
        ISub isub = com.android.internal.telephony.ISub.Stub.asInterface(ServiceManager.getService("isub"));
        int i = byte0;
        if(isub != null)
            try
            {
                i = isub.getDefaultSubId();
            }
            catch(RemoteException remoteexception)
            {
                i = byte0;
            }
        return i;
    }

    public static int getDefaultVoicePhoneId()
    {
        return getPhoneId(getDefaultVoiceSubscriptionId());
    }

    public static int getDefaultVoiceSubscriptionId()
    {
        byte byte0 = -1;
        ISub isub = com.android.internal.telephony.ISub.Stub.asInterface(ServiceManager.getService("isub"));
        int i = byte0;
        if(isub != null)
            try
            {
                i = isub.getDefaultVoiceSubId();
            }
            catch(RemoteException remoteexception)
            {
                i = byte0;
            }
        return i;
    }

    public static int getIntegerSubscriptionProperty(int i, String s, int j, Context context)
    {
        s = getSubscriptionProperty(i, s, context);
        if(s == null)
            break MISSING_BLOCK_LABEL_24;
        i = Integer.parseInt(s);
        return i;
        s;
        logd("getBooleanSubscriptionProperty NumberFormat exception");
        return j;
    }

    public static int getPhoneId(int i)
    {
        byte byte0;
        if(!isValidSubscriptionId(i))
            return -1;
        byte0 = -1;
        ISub isub = com.android.internal.telephony.ISub.Stub.asInterface(ServiceManager.getService("isub"));
        int j = byte0;
        if(isub != null)
            try
            {
                j = isub.getPhoneId(i);
            }
            catch(RemoteException remoteexception)
            {
                j = byte0;
            }
        return j;
    }

    public static Resources getResourcesForSubId(Context context, int i)
    {
        Object obj = from(context).getActiveSubscriptionInfo(i);
        Object obj1 = context.getResources().getConfiguration();
        Configuration configuration = new Configuration();
        configuration.setTo(((Configuration) (obj1)));
        if(obj != null)
        {
            configuration.mcc = ((SubscriptionInfo) (obj)).getMcc();
            configuration.mnc = ((SubscriptionInfo) (obj)).getMnc();
            if(configuration.mnc == 0)
                configuration.mnc = 65535;
        }
        obj1 = context.getResources().getDisplayMetrics();
        obj = new DisplayMetrics();
        ((DisplayMetrics) (obj)).setTo(((DisplayMetrics) (obj1)));
        return new Resources(context.getResources().getAssets(), ((DisplayMetrics) (obj)), configuration);
    }

    public static int getSimStateForSlotIndex(int i)
    {
        boolean flag = false;
        ISub isub = com.android.internal.telephony.ISub.Stub.asInterface(ServiceManager.getService("isub"));
        int j = ((flag) ? 1 : 0);
        if(isub != null)
            try
            {
                j = isub.getSimStateForSlotIndex(i);
            }
            catch(RemoteException remoteexception)
            {
                j = ((flag) ? 1 : 0);
            }
        return j;
    }

    public static int getSlotIndex(int i)
    {
        byte byte0;
        isValidSubscriptionId(i);
        byte0 = -1;
        ISub isub = com.android.internal.telephony.ISub.Stub.asInterface(ServiceManager.getService("isub"));
        int j = byte0;
        if(isub != null)
            try
            {
                j = isub.getSlotIndex(i);
            }
            catch(RemoteException remoteexception)
            {
                j = byte0;
            }
        return j;
    }

    public static int[] getSubId(int i)
    {
        Object obj;
        if(!isValidSlotIndex(i))
        {
            logd("[getSubId]- fail");
            return null;
        }
        obj = null;
        ISub isub = com.android.internal.telephony.ISub.Stub.asInterface(ServiceManager.getService("isub"));
        Object obj1 = obj;
        if(isub != null)
            try
            {
                obj1 = isub.getSubId(i);
            }
            catch(RemoteException remoteexception)
            {
                remoteexception = obj;
            }
        return ((int []) (obj1));
    }

    private static String getSubscriptionProperty(int i, String s, Context context)
    {
        Object obj = null;
        ISub isub = com.android.internal.telephony.ISub.Stub.asInterface(ServiceManager.getService("isub"));
        String s1 = obj;
        if(isub != null)
            try
            {
                s1 = isub.getSubscriptionProperty(i, s, context.getOpPackageName());
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                s1 = obj;
            }
        return s1;
    }

    public static boolean isUsableSubIdValue(int i)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(i >= 0)
        {
            flag1 = flag;
            if(i <= 0x7ffffffe)
                flag1 = true;
        }
        return flag1;
    }

    public static boolean isValidPhoneId(int i)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(i >= 0)
        {
            flag1 = flag;
            if(i < TelephonyManager.getDefault().getPhoneCount())
                flag1 = true;
        }
        return flag1;
    }

    public static boolean isValidSlotIndex(int i)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(i >= 0)
        {
            flag1 = flag;
            if(i < TelephonyManager.getDefault().getSimCount())
                flag1 = true;
        }
        return flag1;
    }

    public static boolean isValidSubscriptionId(int i)
    {
        boolean flag;
        if(i > -1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static void logd(String s)
    {
        Rlog.d("SubscriptionManager", s);
    }

    public static void putPhoneIdAndSubIdExtra(Intent intent, int i)
    {
        int ai[] = getSubId(i);
        if(ai != null && ai.length > 0)
            putPhoneIdAndSubIdExtra(intent, i, ai[0]);
        else
            logd("putPhoneIdAndSubIdExtra: no valid subs");
    }

    public static void putPhoneIdAndSubIdExtra(Intent intent, int i, int j)
    {
        intent.putExtra("subscription", j);
        intent.putExtra("android.telephony.extra.SUBSCRIPTION_INDEX", j);
        intent.putExtra("phone", i);
        intent.putExtra("slot", i);
        miui.telephony.SubscriptionManager.putSlotIdPhoneIdAndSubIdExtra(intent, i, i, j);
    }

    public static void setDefaultDataSubId(int i)
    {
        ISub isub = com.android.internal.telephony.ISub.Stub.asInterface(ServiceManager.getService("isub"));
        if(isub == null)
            break MISSING_BLOCK_LABEL_20;
        isub.setDefaultDataSubId(i);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static void setDefaultVoiceSubId(int i)
    {
        ISub isub = com.android.internal.telephony.ISub.Stub.asInterface(ServiceManager.getService("isub"));
        if(isub == null)
            break MISSING_BLOCK_LABEL_20;
        isub.setDefaultVoiceSubId(i);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static void setSubscriptionProperty(int i, String s, String s1)
    {
        ISub isub = com.android.internal.telephony.ISub.Stub.asInterface(ServiceManager.getService("isub"));
        if(isub == null)
            break MISSING_BLOCK_LABEL_22;
        isub.setSubscriptionProperty(i, s, s1);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void addOnSubscriptionsChangedListener(OnSubscriptionsChangedListener onsubscriptionschangedlistener)
    {
        String s;
        ITelephonyRegistry itelephonyregistry;
        if(mContext != null)
            s = mContext.getOpPackageName();
        else
            s = "<unknown>";
        itelephonyregistry = com.android.internal.telephony.ITelephonyRegistry.Stub.asInterface(ServiceManager.getService("telephony.registry"));
        if(itelephonyregistry == null)
            break MISSING_BLOCK_LABEL_40;
        itelephonyregistry.addOnSubscriptionsChangedListener(s, onsubscriptionschangedlistener.callback);
_L2:
        return;
        onsubscriptionschangedlistener;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public Uri addSubscriptionInfoRecord(String s, int i)
    {
        if(s == null)
            logd("[addSubscriptionInfoRecord]- null iccId");
        if(!isValidSlotIndex(i))
            logd("[addSubscriptionInfoRecord]- invalid slotIndex");
        ISub isub = com.android.internal.telephony.ISub.Stub.asInterface(ServiceManager.getService("isub"));
        if(isub != null)
            try
            {
                isub.addSubInfoRecord(s, i);
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
        return null;
    }

    public boolean allDefaultsSelected()
    {
        if(!isValidSubscriptionId(getDefaultDataSubscriptionId()))
            return false;
        if(!isValidSubscriptionId(getDefaultSmsSubscriptionId()))
            return false;
        return isValidSubscriptionId(getDefaultVoiceSubscriptionId());
    }

    public void clearDefaultsForInactiveSubIds()
    {
        ISub isub = com.android.internal.telephony.ISub.Stub.asInterface(ServiceManager.getService("isub"));
        if(isub == null)
            break MISSING_BLOCK_LABEL_19;
        isub.clearDefaultsForInactiveSubIds();
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void clearSubscriptionInfo()
    {
        ISub isub = com.android.internal.telephony.ISub.Stub.asInterface(ServiceManager.getService("isub"));
        if(isub == null)
            break MISSING_BLOCK_LABEL_20;
        isub.clearSubInfo();
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public List getAccessibleSubscriptionInfoList()
    {
        Object obj = null;
        ISub isub = com.android.internal.telephony.ISub.Stub.asInterface(ServiceManager.getService("isub"));
        Object obj1 = obj;
        if(isub != null)
            try
            {
                obj1 = isub.getAccessibleSubscriptionInfoList(mContext.getOpPackageName());
            }
            catch(RemoteException remoteexception)
            {
                remoteexception = obj;
            }
        return ((List) (obj1));
    }

    public int[] getActiveSubscriptionIdList()
    {
        int ai[] = null;
        ISub isub = com.android.internal.telephony.ISub.Stub.asInterface(ServiceManager.getService("isub"));
        Object obj = ai;
        if(isub != null)
            try
            {
                obj = isub.getActiveSubIdList();
            }
            catch(RemoteException remoteexception)
            {
                remoteexception = ai;
            }
        ai = ((int []) (obj));
        if(obj == null)
            ai = new int[0];
        return ai;
    }

    public SubscriptionInfo getActiveSubscriptionInfo(int i)
    {
        Object obj;
        if(!isValidSubscriptionId(i))
            return null;
        obj = null;
        ISub isub = com.android.internal.telephony.ISub.Stub.asInterface(ServiceManager.getService("isub"));
        Object obj1 = obj;
        if(isub != null)
            try
            {
                obj1 = isub.getActiveSubscriptionInfo(i, mContext.getOpPackageName());
            }
            catch(RemoteException remoteexception)
            {
                remoteexception = obj;
            }
        return ((SubscriptionInfo) (obj1));
    }

    public int getActiveSubscriptionInfoCount()
    {
        boolean flag = false;
        ISub isub = com.android.internal.telephony.ISub.Stub.asInterface(ServiceManager.getService("isub"));
        int i = ((flag) ? 1 : 0);
        if(isub != null)
            try
            {
                i = isub.getActiveSubInfoCount(mContext.getOpPackageName());
            }
            catch(RemoteException remoteexception)
            {
                i = ((flag) ? 1 : 0);
            }
        return i;
    }

    public int getActiveSubscriptionInfoCountMax()
    {
        boolean flag = false;
        ISub isub = com.android.internal.telephony.ISub.Stub.asInterface(ServiceManager.getService("isub"));
        int i = ((flag) ? 1 : 0);
        if(isub != null)
            try
            {
                i = isub.getActiveSubInfoCountMax();
            }
            catch(RemoteException remoteexception)
            {
                i = ((flag) ? 1 : 0);
            }
        return i;
    }

    public SubscriptionInfo getActiveSubscriptionInfoForIccIndex(String s)
    {
        Object obj;
        if(s == null)
        {
            logd("[getActiveSubscriptionInfoForIccIndex]- null iccid");
            return null;
        }
        obj = null;
        ISub isub = com.android.internal.telephony.ISub.Stub.asInterface(ServiceManager.getService("isub"));
        SubscriptionInfo subscriptioninfo = obj;
        if(isub != null)
            try
            {
                subscriptioninfo = isub.getActiveSubscriptionInfoForIccId(s, mContext.getOpPackageName());
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                subscriptioninfo = obj;
            }
        return subscriptioninfo;
    }

    public SubscriptionInfo getActiveSubscriptionInfoForSimSlotIndex(int i)
    {
        Object obj;
        if(!isValidSlotIndex(i))
        {
            logd("[getActiveSubscriptionInfoForSimSlotIndex]- invalid slotIndex");
            return null;
        }
        obj = null;
        ISub isub = com.android.internal.telephony.ISub.Stub.asInterface(ServiceManager.getService("isub"));
        Object obj1 = obj;
        if(isub != null)
            try
            {
                obj1 = isub.getActiveSubscriptionInfoForSimSlotIndex(i, mContext.getOpPackageName());
            }
            catch(RemoteException remoteexception)
            {
                remoteexception = obj;
            }
        return ((SubscriptionInfo) (obj1));
    }

    public List getActiveSubscriptionInfoList()
    {
        Object obj = null;
        ISub isub = com.android.internal.telephony.ISub.Stub.asInterface(ServiceManager.getService("isub"));
        Object obj1 = obj;
        if(isub != null)
            try
            {
                obj1 = isub.getActiveSubscriptionInfoList(mContext.getOpPackageName());
            }
            catch(RemoteException remoteexception)
            {
                remoteexception = obj;
            }
        return ((List) (obj1));
    }

    public int getAllSubscriptionInfoCount()
    {
        boolean flag = false;
        ISub isub = com.android.internal.telephony.ISub.Stub.asInterface(ServiceManager.getService("isub"));
        int i = ((flag) ? 1 : 0);
        if(isub != null)
            try
            {
                i = isub.getAllSubInfoCount(mContext.getOpPackageName());
            }
            catch(RemoteException remoteexception)
            {
                i = ((flag) ? 1 : 0);
            }
        return i;
    }

    public List getAllSubscriptionInfoList()
    {
        Object obj = null;
        ISub isub = com.android.internal.telephony.ISub.Stub.asInterface(ServiceManager.getService("isub"));
        Object obj1 = obj;
        if(isub != null)
            try
            {
                obj1 = isub.getAllSubInfoList(mContext.getOpPackageName());
            }
            catch(RemoteException remoteexception)
            {
                remoteexception = ((RemoteException) (obj));
            }
        obj = obj1;
        if(obj1 == null)
            obj = new ArrayList();
        return ((List) (obj));
    }

    public List getAvailableSubscriptionInfoList()
    {
        Object obj = null;
        ISub isub = com.android.internal.telephony.ISub.Stub.asInterface(ServiceManager.getService("isub"));
        Object obj1 = obj;
        if(isub != null)
            try
            {
                obj1 = isub.getAvailableSubscriptionInfoList(mContext.getOpPackageName());
            }
            catch(RemoteException remoteexception)
            {
                remoteexception = obj;
            }
        return ((List) (obj1));
    }

    public int getDefaultDataPhoneId()
    {
        return getPhoneId(getDefaultDataSubscriptionId());
    }

    public SubscriptionInfo getDefaultDataSubscriptionInfo()
    {
        return getActiveSubscriptionInfo(getDefaultDataSubscriptionId());
    }

    public int getDefaultSmsPhoneId()
    {
        return getPhoneId(getDefaultSmsSubscriptionId());
    }

    public SubscriptionInfo getDefaultSmsSubscriptionInfo()
    {
        return getActiveSubscriptionInfo(getDefaultSmsSubscriptionId());
    }

    public SubscriptionInfo getDefaultVoiceSubscriptionInfo()
    {
        return getActiveSubscriptionInfo(getDefaultVoiceSubscriptionId());
    }

    public List getSubscriptionPlans(int i)
    {
        Object obj = android.net.INetworkPolicyManager.Stub.asInterface(ServiceManager.getService("netpolicy"));
        try
        {
            obj = ((INetworkPolicyManager) (obj)).getSubscriptionPlans(i, mContext.getOpPackageName());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        if(obj != null)
            break MISSING_BLOCK_LABEL_35;
        obj = Collections.emptyList();
_L1:
        return ((List) (obj));
        obj = Arrays.asList(((Object []) (obj)));
          goto _L1
    }

    public boolean isActiveSubId(int i)
    {
        ISub isub = com.android.internal.telephony.ISub.Stub.asInterface(ServiceManager.getService("isub"));
        if(isub == null)
            break MISSING_BLOCK_LABEL_24;
        boolean flag = isub.isActiveSubId(i);
        return flag;
        RemoteException remoteexception;
        remoteexception;
        return false;
    }

    public boolean isNetworkRoaming(int i)
    {
        if(getPhoneId(i) < 0)
            return false;
        else
            return TelephonyManager.getDefault().isNetworkRoaming(i);
    }

    public void removeOnSubscriptionsChangedListener(OnSubscriptionsChangedListener onsubscriptionschangedlistener)
    {
        String s;
        ITelephonyRegistry itelephonyregistry;
        if(mContext != null)
            s = mContext.getOpPackageName();
        else
            s = "<unknown>";
        itelephonyregistry = com.android.internal.telephony.ITelephonyRegistry.Stub.asInterface(ServiceManager.getService("telephony.registry"));
        if(itelephonyregistry == null)
            break MISSING_BLOCK_LABEL_40;
        itelephonyregistry.removeOnSubscriptionsChangedListener(s, onsubscriptionschangedlistener.callback);
_L2:
        return;
        onsubscriptionschangedlistener;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void requestEmbeddedSubscriptionInfoListRefresh()
    {
        ISub isub = com.android.internal.telephony.ISub.Stub.asInterface(ServiceManager.getService("isub"));
        if(isub == null)
            break MISSING_BLOCK_LABEL_19;
        isub.requestEmbeddedSubscriptionInfoListRefresh();
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public int setDataRoaming(int i, int j)
    {
        boolean flag;
        if(i < 0 || isValidSubscriptionId(j) ^ true)
        {
            logd("[setDataRoaming]- fail");
            return -1;
        }
        flag = false;
        ISub isub = com.android.internal.telephony.ISub.Stub.asInterface(ServiceManager.getService("isub"));
        int k = ((flag) ? 1 : 0);
        if(isub != null)
            try
            {
                k = isub.setDataRoaming(i, j);
            }
            catch(RemoteException remoteexception)
            {
                k = ((flag) ? 1 : 0);
            }
        return k;
    }

    public void setDefaultSmsSubId(int i)
    {
        ISub isub = com.android.internal.telephony.ISub.Stub.asInterface(ServiceManager.getService("isub"));
        if(isub == null)
            break MISSING_BLOCK_LABEL_20;
        isub.setDefaultSmsSubId(i);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public int setDisplayName(String s, int i)
    {
        return setDisplayName(s, i, -1L);
    }

    public int setDisplayName(String s, int i, long l)
    {
        boolean flag;
        if(!isValidSubscriptionId(i))
        {
            logd("[setDisplayName]- fail");
            return -1;
        }
        flag = false;
        ISub isub = com.android.internal.telephony.ISub.Stub.asInterface(ServiceManager.getService("isub"));
        int j = ((flag) ? 1 : 0);
        if(isub != null)
            try
            {
                j = isub.setDisplayNameUsingSrc(s, i, l);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                j = ((flag) ? 1 : 0);
            }
        return j;
    }

    public int setDisplayNumber(String s, int i)
    {
        boolean flag;
        if(s == null || isValidSubscriptionId(i) ^ true)
        {
            logd("[setDisplayNumber]- fail");
            return -1;
        }
        flag = false;
        ISub isub = com.android.internal.telephony.ISub.Stub.asInterface(ServiceManager.getService("isub"));
        int j = ((flag) ? 1 : 0);
        if(isub != null)
            try
            {
                j = isub.setDisplayNumber(s, i);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                j = ((flag) ? 1 : 0);
            }
        return j;
    }

    public int setIconTint(int i, int j)
    {
        boolean flag;
        if(!isValidSubscriptionId(j))
        {
            logd("[setIconTint]- fail");
            return -1;
        }
        flag = false;
        ISub isub = com.android.internal.telephony.ISub.Stub.asInterface(ServiceManager.getService("isub"));
        int k = ((flag) ? 1 : 0);
        if(isub != null)
            try
            {
                k = isub.setIconTint(i, j);
            }
            catch(RemoteException remoteexception)
            {
                k = ((flag) ? 1 : 0);
            }
        return k;
    }

    public void setSubscriptionPlans(int i, List list)
    {
        INetworkPolicyManager inetworkpolicymanager = android.net.INetworkPolicyManager.Stub.asInterface(ServiceManager.getService("netpolicy"));
        try
        {
            inetworkpolicymanager.setSubscriptionPlans(i, (SubscriptionPlan[])list.toArray(new SubscriptionPlan[list.size()]), mContext.getOpPackageName());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(List list)
        {
            throw list.rethrowFromSystemServer();
        }
    }

    public static final String ACCESS_RULES = "access_rules";
    public static final String ACTION_DEFAULT_SMS_SUBSCRIPTION_CHANGED = "android.telephony.action.DEFAULT_SMS_SUBSCRIPTION_CHANGED";
    public static final String ACTION_DEFAULT_SUBSCRIPTION_CHANGED = "android.telephony.action.DEFAULT_SUBSCRIPTION_CHANGED";
    public static final String CARRIER_NAME = "carrier_name";
    public static final String CB_ALERT_REMINDER_INTERVAL = "alert_reminder_interval";
    public static final String CB_ALERT_SOUND_DURATION = "alert_sound_duration";
    public static final String CB_ALERT_SPEECH = "enable_alert_speech";
    public static final String CB_ALERT_VIBRATE = "enable_alert_vibrate";
    public static final String CB_AMBER_ALERT = "enable_cmas_amber_alerts";
    public static final String CB_CHANNEL_50_ALERT = "enable_channel_50_alerts";
    public static final String CB_CMAS_TEST_ALERT = "enable_cmas_test_alerts";
    public static final String CB_EMERGENCY_ALERT = "enable_emergency_alerts";
    public static final String CB_ETWS_TEST_ALERT = "enable_etws_test_alerts";
    public static final String CB_EXTREME_THREAT_ALERT = "enable_cmas_extreme_threat_alerts";
    public static final String CB_OPT_OUT_DIALOG = "show_cmas_opt_out_dialog";
    public static final String CB_SEVERE_THREAT_ALERT = "enable_cmas_severe_threat_alerts";
    public static final String COLOR = "color";
    public static final int COLOR_1 = 0;
    public static final int COLOR_2 = 1;
    public static final int COLOR_3 = 2;
    public static final int COLOR_4 = 3;
    public static final int COLOR_DEFAULT = 0;
    public static final Uri CONTENT_URI = Uri.parse("content://telephony/siminfo");
    public static final String DATA_ROAMING = "data_roaming";
    public static final int DATA_ROAMING_DEFAULT = 0;
    public static final int DATA_ROAMING_DISABLE = 0;
    public static final int DATA_ROAMING_ENABLE = 1;
    private static final boolean DBG = false;
    public static final int DEFAULT_NAME_RES = 0x104000e;
    public static final int DEFAULT_PHONE_INDEX = 0x7fffffff;
    public static final int DEFAULT_SIM_SLOT_INDEX = 0x7fffffff;
    public static final int DEFAULT_SUBSCRIPTION_ID = 0x7fffffff;
    public static final String DISPLAY_NAME = "display_name";
    public static final int DISPLAY_NUMBER_DEFAULT = 1;
    public static final int DISPLAY_NUMBER_FIRST = 1;
    public static final String DISPLAY_NUMBER_FORMAT = "display_number_format";
    public static final int DISPLAY_NUMBER_LAST = 2;
    public static final int DISPLAY_NUMBER_NONE = 0;
    public static final int DUMMY_SUBSCRIPTION_ID_BASE = -2;
    public static final String EXTRA_SUBSCRIPTION_INDEX = "android.telephony.extra.SUBSCRIPTION_INDEX";
    public static final String ICC_ID = "icc_id";
    public static final int INVALID_PHONE_INDEX = -1;
    public static final int INVALID_SIM_SLOT_INDEX = -1;
    public static final int INVALID_SUBSCRIPTION_ID = -1;
    public static final String IS_EMBEDDED = "is_embedded";
    public static final String IS_REMOVABLE = "is_removable";
    private static final String LOG_TAG = "SubscriptionManager";
    public static final int MAX_SUBSCRIPTION_ID_VALUE = 0x7ffffffe;
    public static final String MCC = "mcc";
    public static final int MIN_SUBSCRIPTION_ID_VALUE = 0;
    public static final String MNC = "mnc";
    public static final String NAME_SOURCE = "name_source";
    public static final int NAME_SOURCE_DEFAULT_SOURCE = 0;
    public static final int NAME_SOURCE_SIM_SOURCE = 1;
    public static final int NAME_SOURCE_UNDEFINDED = -1;
    public static final int NAME_SOURCE_USER_INPUT = 2;
    public static final String NUMBER = "number";
    public static final int SIM_NOT_INSERTED = -1;
    public static final int SIM_PROVISIONED = 0;
    public static final String SIM_PROVISIONING_STATUS = "sim_provisioning_status";
    public static final String SIM_SLOT_INDEX = "sim_id";
    public static final String SUB_DEFAULT_CHANGED_ACTION = "android.intent.action.SUB_DEFAULT_CHANGED";
    public static final String UNIQUE_KEY_SUBSCRIPTION_ID = "_id";
    private static final boolean VDBG = false;
    private final Context mContext;


    // Unreferenced inner class android/telephony/SubscriptionManager$OnSubscriptionsChangedListener$1

/* anonymous class */
    class OnSubscriptionsChangedListener._cls1 extends Handler
    {

        public void handleMessage(Message message)
        {
            onSubscriptionsChanged();
        }

        final OnSubscriptionsChangedListener this$1;

            
            {
                this$1 = OnSubscriptionsChangedListener.this;
                super(looper);
            }
    }


    // Unreferenced inner class android/telephony/SubscriptionManager$OnSubscriptionsChangedListener$2

/* anonymous class */
    class OnSubscriptionsChangedListener._cls2 extends com.android.internal.telephony.IOnSubscriptionsChangedListener.Stub
    {

        public void onSubscriptionsChanged()
        {
            OnSubscriptionsChangedListener._2D_get0(OnSubscriptionsChangedListener.this).sendEmptyMessage(0);
        }

        final OnSubscriptionsChangedListener this$1;

            
            {
                this$1 = OnSubscriptionsChangedListener.this;
                super();
            }
    }

}
