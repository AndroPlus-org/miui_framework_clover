// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.telephony;

import android.content.*;
import android.os.*;
import android.telephony.*;
import android.text.TextUtils;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import miui.util.AppConstants;
import miui.util.FeatureParser;

// Referenced classes of package miui.telephony:
//            SubscriptionManager, TelephonyManagerEx, IMiuiTelephony, SubscriptionInfo, 
//            TelephonyManager

public class SubscriptionManagerEx extends miui.telephony.SubscriptionManager
{
    static class ConstantsDefiner
    {

        static int getDefaultPhoneIdConstant()
        {
            return 0x7fffffff;
        }

        static int getDefaultSlotIdConstant()
        {
            return 0x7fffffff;
        }

        static int getDefaultSubscriptionIdConstant()
        {
            return 0x7fffffff;
        }

        static int getInvalidPhoneIdConstant()
        {
            return -1;
        }

        static int getInvalidSlotIdConstant()
        {
            return -1;
        }

        static int getInvalidSubscriptionIdConstant()
        {
            return -1;
        }

        static String getPhoneKeyConstant()
        {
            return "phone_id";
        }

        static String getSlotKeyConstant()
        {
            return "slot_id";
        }

        static String getSubscriptionKeyConstant()
        {
            return "subscription_id";
        }

        private static final String PHONE_ID = "phone_id";
        private static final String SLOT_ID = "slot_id";
        private static final String SUBSCRIPTION_ID = "subscription_id";

        private ConstantsDefiner()
        {
        }
    }

    static class Holder
    {

        static final Context CONTEXT;
        static final SubscriptionManagerEx INSTANCE = new SubscriptionManagerEx(null);
        static final SubscriptionManager SUBSCRIPTION_MANAGER;

        static 
        {
            CONTEXT = AppConstants.getCurrentApplication();
            SUBSCRIPTION_MANAGER = SubscriptionManager.from(CONTEXT);
        }

        private Holder()
        {
        }
    }

    static class SubscriptionInfoImpl extends miui.telephony.SubscriptionInfo
    {

        public static List from(List list)
        {
            if(list == null)
                return new ArrayList();
            ArrayList arraylist = new ArrayList();
            for(int i = 0; i < list.size(); i++)
                arraylist.add(i, from((SubscriptionInfo)list.get(i)));

            return arraylist;
        }

        public static miui.telephony.SubscriptionInfo from(SubscriptionInfo subscriptioninfo)
        {
            Object obj = null;
            if(subscriptioninfo == null)
                subscriptioninfo = obj;
            else
                subscriptioninfo = new SubscriptionInfoImpl(subscriptioninfo);
            return subscriptioninfo;
        }

        private String getDefaultDisplayName()
        {
            String s;
            String s1;
            s = "";
            s1 = s;
            if(mSlotId == SubscriptionManagerEx.INVALID_SLOT_ID) goto _L2; else goto _L1
_L1:
            s1 = TelephonyManagerEx.getDefault().getMiuiTelephony().getSpn(TelephonyManager.getDefault().getSimOperatorForSlot(mSlotId), mSlotId, TelephonyManager.getDefault().getSimOperatorNameForSlot(mSlotId), true);
            s = s1;
_L4:
            s1 = s;
            if(TextUtils.isEmpty(s))
                s1 = Holder.CONTEXT.getString(0x110800b2, new Object[] {
                    Integer.valueOf(mSlotId + 1)
                });
_L2:
            return s1;
            Exception exception;
            exception;
            if(true) goto _L4; else goto _L3
_L3:
        }

        public CharSequence getDisplayName()
        {
            Object obj;
            if(mSubInfo.getNameSource() == 2)
                obj = mSubInfo.getDisplayName();
            else
                obj = getDefaultDisplayName();
            return ((CharSequence) (obj));
        }

        public String getDisplayNumber()
        {
            return mSubInfo.getNumber();
        }

        public String getIccId()
        {
            return mSubInfo.getIccId();
        }

        public int getMcc()
        {
            return mSubInfo.getMcc();
        }

        public int getMnc()
        {
            return mSubInfo.getMnc();
        }

        public int getPhoneId()
        {
            return mSlotId;
        }

        public int getSlotId()
        {
            return mSlotId;
        }

        public int getSubscriptionId()
        {
            return mSubscriptionId;
        }

        public boolean isActivated()
        {
            if(mSlotId == SubscriptionManagerEx.INVALID_SLOT_ID)
                break MISSING_BLOCK_LABEL_40;
            if(!TelephonyManager.getDefault().isMultiSimEnabled())
                return true;
            boolean flag = TelephonyManagerEx.getDefault().getMiuiTelephony().isIccCardActivate(mSlotId);
            return flag;
            Exception exception;
            exception;
            return true;
        }

        int mSlotId;
        private final SubscriptionInfo mSubInfo;
        private final int mSubscriptionId;

        private SubscriptionInfoImpl(SubscriptionInfo subscriptioninfo)
        {
            int i;
            if(SubscriptionManagerEx.isValidSubscriptionId(subscriptioninfo.getSubscriptionId()))
                i = subscriptioninfo.getSubscriptionId();
            else
                i = SubscriptionManagerEx.INVALID_SUBSCRIPTION_ID;
            mSubscriptionId = i;
            if(SubscriptionManagerEx.isValidSlotId(subscriptioninfo.getSimSlotIndex()))
                i = subscriptioninfo.getSimSlotIndex();
            else
                i = SubscriptionManagerEx.INVALID_SLOT_ID;
            mSlotId = i;
            mSubInfo = subscriptioninfo;
        }
    }


    static void _2D_wrap0(SubscriptionManagerEx subscriptionmanagerex, boolean flag)
    {
        subscriptionmanagerex.initSubscriptionListener(flag);
    }

    private SubscriptionManagerEx()
    {
        mReceiverRegistered = new AtomicBoolean(false);
    }

    SubscriptionManagerEx(SubscriptionManagerEx subscriptionmanagerex)
    {
        this();
    }

    public static SubscriptionManagerEx getDefault()
    {
        return Holder.INSTANCE;
    }

    private IMiuiTelephony getMiuiTelephony()
    {
        return TelephonyManagerEx.getDefault().getMiuiTelephony();
    }

    private void initSubscriptionListener(boolean flag)
    {
        if(mSubscriptionListener != null)
            return;
        if(flag && Looper.myLooper() != Looper.getMainLooper())
        {
            Rlog.i("SubMgr", (new StringBuilder()).append("initSubscriptionListener failed for pkg=").append(Holder.CONTEXT.getOpPackageName()).append(" threadName=").append(Thread.currentThread().getName()).toString());
            return;
        } else
        {
            mSubscriptionListener = new android.telephony.SubscriptionManager.OnSubscriptionsChangedListener() {

                public void onSubscriptionsChanged()
                {
                    onSubscriptionInfoChanged();
                }

                final SubscriptionManagerEx this$0;

            
            {
                this$0 = SubscriptionManagerEx.this;
                super();
            }
            }
;
            SubscriptionManager.from(Holder.CONTEXT).addOnSubscriptionsChangedListener(mSubscriptionListener);
            return;
        }
    }

    protected void addOnSubscriptionsChangedListenerInternal()
    {
        if(mReceiverRegistered.compareAndSet(false, true))
        {
            initSubscriptionListener(true);
            if(mSubscriptionListener == null)
                (new Handler(Looper.getMainLooper())).post(new Runnable() {

                    public void run()
                    {
                        Rlog.i("SubMgr", (new StringBuilder()).append("initSubscriptionListener in main Thread for pkg=").append(Holder.CONTEXT.getOpPackageName()).toString());
                        SubscriptionManagerEx._2D_wrap0(SubscriptionManagerEx.this, false);
                    }

                    final SubscriptionManagerEx this$0;

            
            {
                this$0 = SubscriptionManagerEx.this;
                super();
            }
                }
);
            if("qcom".equals(FeatureParser.getString("vendor")))
            {
                IntentFilter intentfilter = new IntentFilter();
                intentfilter.addAction("org.codeaurora.intent.action.ACTION_UICC_MANUAL_PROVISION_STATUS_CHANGED");
                Holder.CONTEXT.registerReceiver(mSubscriptionChangedReceiver, intentfilter);
            }
        }
    }

    protected List getAllSubscriptionInfoListInternal()
    {
        return SubscriptionInfoImpl.from(Holder.SUBSCRIPTION_MANAGER.getAllSubscriptionInfoList());
    }

    public int getDefaultDataSlotId()
    {
        int i = SystemProperties.getInt("persist.radio.default.data", INVALID_SLOT_ID);
        int j = i;
        if(i == INVALID_SLOT_ID)
            j = getDefaultSlotIdInternal();
        return j;
    }

    public int getDefaultDataSubscriptionId()
    {
        int i = SubscriptionManager.getDefaultDataSubscriptionId();
        int j = i;
        if(i == INVALID_SUBSCRIPTION_ID)
            j = getSubscriptionIdForSlot(getDefaultSlotIdInternal());
        return j;
    }

    public miui.telephony.SubscriptionInfo getDefaultDataSubscriptionInfo()
    {
        return SubscriptionInfoImpl.from(Holder.SUBSCRIPTION_MANAGER.getDefaultDataSubscriptionInfo());
    }

    protected int getDefaultSlotIdInternal()
    {
        int i;
        try
        {
            i = getMiuiTelephony().getSystemDefaultSlotId();
        }
        catch(Exception exception)
        {
            return 0;
        }
        return i;
    }

    public int getDefaultSmsSubscriptionId()
    {
        int i = SubscriptionManager.getDefaultSmsSubscriptionId();
        int j = i;
        if(!isValidSubscriptionId(i))
            j = INVALID_SUBSCRIPTION_ID;
        return j;
    }

    public miui.telephony.SubscriptionInfo getDefaultSmsSubscriptionInfo()
    {
        return SubscriptionInfoImpl.from(Holder.SUBSCRIPTION_MANAGER.getDefaultSmsSubscriptionInfo());
    }

    public int getDefaultVoiceSlotId()
    {
        return SystemProperties.getInt("persist.radio.default.voice", INVALID_SLOT_ID);
    }

    public int getDefaultVoiceSubscriptionId()
    {
        int i = SubscriptionManager.getDefaultVoiceSubscriptionId();
        int j = i;
        if(!isValidSubscriptionId(i))
            j = INVALID_SUBSCRIPTION_ID;
        return j;
    }

    public miui.telephony.SubscriptionInfo getDefaultVoiceSubscriptionInfo()
    {
        return SubscriptionInfoImpl.from(Holder.SUBSCRIPTION_MANAGER.getDefaultVoiceSubscriptionInfo());
    }

    protected int getSlotId(int i)
    {
        long l = Binder.clearCallingIdentity();
        Iterator iterator = getSubscriptionInfoList().iterator();
        miui.telephony.SubscriptionInfo subscriptioninfo;
        do
        {
            if(!iterator.hasNext())
                break MISSING_BLOCK_LABEL_58;
            subscriptioninfo = (miui.telephony.SubscriptionInfo)iterator.next();
        } while(subscriptioninfo.getSubscriptionId() != i);
        i = subscriptioninfo.getSlotId();
        Binder.restoreCallingIdentity(l);
        return i;
        Binder.restoreCallingIdentity(l);
        return SubscriptionManager.getPhoneId(i);
        Exception exception;
        exception;
        Binder.restoreCallingIdentity(l);
        throw exception;
    }

    public int getSubscriptionIdForSlot(int i)
    {
        long l;
        if(!isValidSlotId(i))
            return INVALID_SUBSCRIPTION_ID;
        if(i == DEFAULT_SLOT_ID)
            return DEFAULT_SUBSCRIPTION_ID;
        l = Binder.clearCallingIdentity();
        Iterator iterator = getSubscriptionInfoList().iterator();
        miui.telephony.SubscriptionInfo subscriptioninfo;
        do
        {
            if(!iterator.hasNext())
                break MISSING_BLOCK_LABEL_80;
            subscriptioninfo = (miui.telephony.SubscriptionInfo)iterator.next();
        } while(subscriptioninfo.getSlotId() != i);
        i = subscriptioninfo.getSubscriptionId();
        Binder.restoreCallingIdentity(l);
        return i;
        Binder.restoreCallingIdentity(l);
        int ai[] = SubscriptionManager.getSubId(i);
        Exception exception;
        if(ai != null && ai.length > 0)
            i = ai[0];
        else
            i = INVALID_SUBSCRIPTION_ID;
        return i;
        exception;
        Binder.restoreCallingIdentity(l);
        throw exception;
    }

    protected List getSubscriptionInfoListInternal()
    {
        return SubscriptionInfoImpl.from(Holder.SUBSCRIPTION_MANAGER.getActiveSubscriptionInfoList());
    }

    protected void removeOnSubscriptionsChangedListenerInternal()
    {
        mReceiverRegistered.set(false);
        if(mSubscriptionListener != null)
        {
            SubscriptionManager.from(Holder.CONTEXT).removeOnSubscriptionsChangedListener(mSubscriptionListener);
            mSubscriptionListener = null;
        }
        if(mSubscriptionChangedReceiver == null || !"qcom".equals(FeatureParser.getString("vendor")))
            break MISSING_BLOCK_LABEL_63;
        Holder.CONTEXT.unregisterReceiver(mSubscriptionChangedReceiver);
_L1:
        return;
        Exception exception;
        exception;
        Rlog.i("SubMgr", "unregister SubscriptionChangedReceiver error!!!");
          goto _L1
    }

    public void setDefaultDataSlotId(int i)
    {
        if(!isValidSlotId(i) || i == DEFAULT_SLOT_ID)
            return;
        getMiuiTelephony().setDefaultDataSlotId(i, Holder.CONTEXT.getOpPackageName());
_L1:
        return;
        Exception exception;
        exception;
        Rlog.e("SubMgr", (new StringBuilder()).append("Failed to set default data slot id ").append(i).append(" - ").toString(), exception);
          goto _L1
    }

    public void setDefaultSmsSubscriptionId(int i)
    {
        if(!isValidSubscriptionId(i))
            i = INVALID_SUBSCRIPTION_ID;
        if(i == DEFAULT_SUBSCRIPTION_ID || i == getDefaultSmsSubscriptionId())
        {
            return;
        } else
        {
            Holder.SUBSCRIPTION_MANAGER.setDefaultSmsSubId(i);
            return;
        }
    }

    public void setDefaultVoiceSlotId(int i)
    {
        if(i == DEFAULT_SLOT_ID)
            return;
        IMiuiTelephony imiuitelephony = getMiuiTelephony();
        if(!isValidSlotId(i)) goto _L2; else goto _L1
_L1:
        int j = i;
_L3:
        imiuitelephony.setDefaultVoiceSlotId(j, Holder.CONTEXT.getOpPackageName());
_L4:
        return;
_L2:
        j = INVALID_SLOT_ID;
          goto _L3
        Exception exception;
        exception;
        Rlog.e("SubMgr", (new StringBuilder()).append("Failed to set default voice slot id ").append(i).append(" - ").toString(), exception);
          goto _L4
    }

    public int setDisplayNameForSlot(String s, int i)
    {
        if(!isValidSlotId(i))
            return 0;
        if(i == DEFAULT_SLOT_ID)
            return setDisplayNameForSubscription(s, getSubscriptionIdForSlot(getDefaultSlotId()));
        else
            return setDisplayNameForSubscription(s, getSubscriptionIdForSlot(i));
    }

    public int setDisplayNameForSubscription(String s, int i)
    {
        if(!isValidSubscriptionId(i))
            return 0;
        if(i == DEFAULT_SUBSCRIPTION_ID)
            return setDisplayNameForSlot(s, getDefaultSlotId());
        else
            return Holder.SUBSCRIPTION_MANAGER.setDisplayName(s, i, 2L);
    }

    public int setDisplayNumberForSlot(String s, int i)
    {
        if(!isValidSlotId(i))
            return 0;
        if(i == DEFAULT_SLOT_ID)
            return setDisplayNameForSubscription(s, getSubscriptionIdForSlot(getDefaultSlotId()));
        else
            return setDisplayNameForSubscription(s, getSubscriptionIdForSlot(i));
    }

    public int setDisplayNumberForSubscription(String s, int i)
    {
        if(!isValidSubscriptionId(i))
            return 0;
        if(i == DEFAULT_SUBSCRIPTION_ID)
            return setDisplayNumberForSlot(s, getDefaultSlotId());
        else
            return Holder.SUBSCRIPTION_MANAGER.setDisplayNumber(s, i);
    }

    public static final String ACTION_DEFAULT_DATA_SLOT_CHANGED = "miui.intent.action.ACTION_DEFAULT_DATA_SLOT_CHANGED";
    public static final String ACTION_DEFAULT_DATA_SLOT_READY = "miui.intent.action.ACTION_DEFAULT_DATA_SLOT_READY";
    private static final String ACTION_UICC_MANUAL_PROVISION_STATUS_CHANGED = "org.codeaurora.intent.action.ACTION_UICC_MANUAL_PROVISION_STATUS_CHANGED";
    static final String DEFAULT_DATA_SLOT_PROPERTY = "persist.radio.default.data";
    static final String DEFAULT_VOICE_SLOT_PROPERTY = "persist.radio.default.voice";
    public static final String KEY_OLD_DATA_SLOT = "old_data_slot";
    public static final String KEY_SIM_INSERT_STATE_ARRAY = "sim_insert_state_array";
    public static final int SIM_CHANGED = 4;
    public static final int SIM_NEW_CARD = 2;
    public static final int SIM_NO_CARD = 1;
    public static final int SIM_NO_CHANGE = 0;
    public static final int SIM_REMOVED = 3;
    private AtomicBoolean mReceiverRegistered;
    private BroadcastReceiver mSubscriptionChangedReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent)
        {
            if("org.codeaurora.intent.action.ACTION_UICC_MANUAL_PROVISION_STATUS_CHANGED".equals(intent.getAction()))
                onSubscriptionInfoChanged();
        }

        final SubscriptionManagerEx this$0;

            
            {
                this$0 = SubscriptionManagerEx.this;
                super();
            }
    }
;
    private android.telephony.SubscriptionManager.OnSubscriptionsChangedListener mSubscriptionListener;
}
