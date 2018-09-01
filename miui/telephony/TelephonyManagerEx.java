// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.telephony;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.Country;
import android.location.CountryDetector;
import android.os.*;
import android.telephony.*;
import com.android.internal.telecom.ITelecomService;
import com.android.internal.telephony.ITelephony;
import com.android.internal.telephony.ITelephonyRegistry;
import java.util.*;
import miui.util.AppConstants;

// Referenced classes of package miui.telephony:
//            TelephonyManager, PhoneNumberUtils, SubscriptionManager, IMiuiTelephony

public class TelephonyManagerEx extends miui.telephony.TelephonyManager
{
    static class Holder
    {

        static final Context CONTEXT;
        static final int CT_VOLTE_SUPPORTED_MODE;
        static final TelephonyManagerEx INSTANCE = new TelephonyManagerEx(null);
        static final boolean IS_DUAL_VOLTE_SUPPORTED;
        static final int PHONE_COUNT;
        static final SubscriptionManager SUBSCRIPTION_MANAGER = SubscriptionManager.getDefault();
        static final TelephonyManager TELEPHONY_MANAGER;

        static 
        {
            CONTEXT = AppConstants.getCurrentApplication();
            TELEPHONY_MANAGER = (TelephonyManager)CONTEXT.getSystemService("phone");
            PHONE_COUNT = TELEPHONY_MANAGER.getPhoneCount();
            IS_DUAL_VOLTE_SUPPORTED = CONTEXT.getResources().getBoolean(0x110a001f);
            CT_VOLTE_SUPPORTED_MODE = CONTEXT.getResources().getInteger(0x1107001b);
        }

        private Holder()
        {
        }
    }


    private TelephonyManagerEx()
    {
    }

    TelephonyManagerEx(TelephonyManagerEx telephonymanagerex)
    {
        this();
    }

    private static String getCountryIso(Context context)
    {
        String s = null;
        Object obj = (CountryDetector)context.getSystemService("country_detector");
        String s1 = s;
        if(obj != null)
        {
            obj = ((CountryDetector) (obj)).detectCountry();
            s1 = s;
            if(obj != null)
                s1 = ((Country) (obj)).getCountryIso();
        }
        s = s1;
        if(s1 == null)
        {
            s = context.getResources().getConfiguration().locale.getCountry();
            Rlog.w("TelephonyManager", (new StringBuilder()).append("No CountryDetector; falling back to countryIso based on locale: ").append(s).toString());
        }
        return s;
    }

    public static TelephonyManagerEx getDefault()
    {
        return Holder.INSTANCE;
    }

    private ITelephony getITelephony()
    {
        return com.android.internal.telephony.ITelephony.Stub.asInterface(ServiceManager.getService("phone"));
    }

    private ITelecomService getTelecomService()
    {
        return com.android.internal.telecom.ITelecomService.Stub.asInterface(ServiceManager.getService("telecom"));
    }

    public static boolean isLocalEmergencyNumber(Context context, String s)
    {
        return isLocalEmergencyNumberInternal(context, s, true);
    }

    private static boolean isLocalEmergencyNumberInternal(Context context, String s, boolean flag)
    {
        if("IN".equalsIgnoreCase(getCountryIso(context)))
        {
            String as[] = new String[4];
            as[0] = "100";
            as[1] = "101";
            as[2] = "102";
            as[3] = "108";
            int i = as.length;
            for(int k = 0; k < i; k++)
                if(as[k].equals(s))
                {
                    Rlog.d("TelephonyManager", (new StringBuilder()).append("isLocalEmergencyNumberInternal :number:").append(s).append(" is not a real IN emergency number,return false").toString());
                    return false;
                }

        }
        int j = Holder.INSTANCE.getPhoneCount();
        if(j < 2)
        {
            if(flag)
                flag = PhoneNumberUtils.isLocalEmergencyNumber(context, s);
            else
                flag = PhoneNumberUtils.isPotentialLocalEmergencyNumber(context, s);
            return flag;
        }
        for(int l = 0; l < j; l++)
        {
            int i1 = Holder.SUBSCRIPTION_MANAGER.getSubscriptionIdForSlot(l);
            if(flag && PhoneNumberUtils.isLocalEmergencyNumber(context, i1, s) || !flag && PhoneNumberUtils.isPotentialLocalEmergencyNumber(context, i1, s))
                return true;
        }

        return false;
    }

    public static boolean isPotentialLocalEmergencyNumber(Context context, String s)
    {
        return isLocalEmergencyNumberInternal(context, s, false);
    }

    private int normalizeSlotId(int i)
    {
        if(i == SubscriptionManager.DEFAULT_SLOT_ID)
            return Holder.SUBSCRIPTION_MANAGER.getDefaultSlotId();
        else
            return i;
    }

    private int normalizeSubscriptionId(int i)
    {
        if(i == SubscriptionManager.DEFAULT_SUBSCRIPTION_ID)
            return Holder.SUBSCRIPTION_MANAGER.getDefaultSubscriptionId();
        else
            return i;
    }

    public void answerRingingCall()
    {
        getTelecomService().acceptRingingCall(Holder.CONTEXT.getOpPackageName());
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Rlog.e("TelephonyManager", "Error calling ITelecomService#acceptRingingCall", remoteexception);
          goto _L1
    }

    public void cancelMissedCallsNotification()
    {
        getTelecomService().cancelMissedCallsNotification(Holder.CONTEXT.getOpPackageName());
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Rlog.e("TelephonyManager", "Error call ITelecomService#cancelMissedCallsNotification", remoteexception);
          goto _L1
    }

    public boolean enableDataConnectivity()
    {
        return Holder.TELEPHONY_MANAGER.enableDataConnectivity();
    }

    public boolean enableDataConnectivityForSlot(int i)
    {
        if(i != SubscriptionManager.DEFAULT_SLOT_ID && i != Holder.SUBSCRIPTION_MANAGER.getDefaultDataSlotId())
            return false;
        else
            return Holder.TELEPHONY_MANAGER.enableDataConnectivity();
    }

    public boolean enableDataConnectivityForSubscription(int i)
    {
        if(i != SubscriptionManager.DEFAULT_SUBSCRIPTION_ID && i != Holder.SUBSCRIPTION_MANAGER.getDefaultDataSubscriptionId())
            return false;
        else
            return Holder.TELEPHONY_MANAGER.enableDataConnectivity();
    }

    public boolean endCall()
    {
        boolean flag;
        try
        {
            flag = getTelecomService().endCall();
        }
        catch(RemoteException remoteexception)
        {
            Rlog.e("TelephonyManager", "Error calling ITelecomService#endCall", remoteexception);
            return false;
        }
        return flag;
    }

    public List getAllCellInfo()
    {
        return getAllCellInfoForSubscription(Holder.SUBSCRIPTION_MANAGER.getDefaultSubscriptionId());
    }

    public List getAllCellInfoForSlot(int i)
    {
        return getAllCellInfoForSubscription(Holder.SUBSCRIPTION_MANAGER.getSubscriptionIdForSlot(i));
    }

    public List getAllCellInfoForSubscription(int i)
    {
        return Holder.TELEPHONY_MANAGER.getAllCellInfo();
    }

    public int getCallState()
    {
        return Holder.TELEPHONY_MANAGER.getCallState();
    }

    public int getCallStateForSlot(int i)
    {
        return getCallStateForSubscription(Holder.SUBSCRIPTION_MANAGER.getSubscriptionIdForSlot(i));
    }

    public int getCallStateForSubscription(int i)
    {
        return Holder.TELEPHONY_MANAGER.getCallState(normalizeSubscriptionId(i));
    }

    public CellLocation getCellLocation()
    {
        return Holder.TELEPHONY_MANAGER.getCellLocation();
    }

    public CellLocation getCellLocationForSlot(int i)
    {
        Object obj;
        try
        {
            obj = getMiuiTelephony();
        }
        catch(Exception exception)
        {
            Rlog.d("TelephonyManager", (new StringBuilder()).append("getCellLocationForSlot returning null due to Exception ").append(exception).toString());
            return null;
        }
        if(obj == null)
            return null;
        obj = ((IMiuiTelephony) (obj)).getCellLocationForSlot(i, Holder.CONTEXT.getOpPackageName());
        if(!((Bundle) (obj)).isEmpty())
            break MISSING_BLOCK_LABEL_43;
        Rlog.d("TelephonyManager", "getCellLocationForSlot returning null because bundle is empty");
        return null;
        obj = CellLocation.newFromBundle(((Bundle) (obj)));
        if(!((CellLocation) (obj)).isEmpty())
            break MISSING_BLOCK_LABEL_66;
        Rlog.d("TelephonyManager", "getCellLocationForSlot returning null because CellLocation is empty");
        return null;
        return ((CellLocation) (obj));
    }

    public CellLocation getCellLocationForSubscription(int i)
    {
        return Holder.TELEPHONY_MANAGER.getCellLocation();
    }

    public int getCtVolteSupportedMode()
    {
        return Holder.CT_VOLTE_SUPPORTED_MODE;
    }

    public int getDataActivity()
    {
        return Holder.TELEPHONY_MANAGER.getDataActivity();
    }

    public int getDataActivityForSlot(int i)
    {
        if(i != SubscriptionManager.DEFAULT_SLOT_ID && i != Holder.SUBSCRIPTION_MANAGER.getDefaultDataSlotId())
            return 0;
        else
            return Holder.TELEPHONY_MANAGER.getDataActivity();
    }

    public int getDataActivityForSubscription(int i)
    {
        if(i != SubscriptionManager.DEFAULT_SUBSCRIPTION_ID && i != Holder.SUBSCRIPTION_MANAGER.getDefaultDataSubscriptionId())
            return 0;
        else
            return Holder.TELEPHONY_MANAGER.getDataActivity();
    }

    public int getDataState()
    {
        return Holder.TELEPHONY_MANAGER.getDataState();
    }

    public int getDataStateForSlot(int i)
    {
        if(i != SubscriptionManager.DEFAULT_SLOT_ID && i != Holder.SUBSCRIPTION_MANAGER.getDefaultDataSlotId())
            return 0;
        else
            return Holder.TELEPHONY_MANAGER.getDataState();
    }

    public int getDataStateForSubscription(int i)
    {
        if(i != SubscriptionManager.DEFAULT_SUBSCRIPTION_ID && i != Holder.SUBSCRIPTION_MANAGER.getDefaultDataSubscriptionId())
            return 0;
        else
            return Holder.TELEPHONY_MANAGER.getDataState();
    }

    public String getDeviceId()
    {
        return getDeviceIdForSlot(Holder.SUBSCRIPTION_MANAGER.getDefaultSlotId());
    }

    public String getDeviceIdForSlot(int i)
    {
        return Holder.TELEPHONY_MANAGER.getDeviceId(normalizeSlotId(i));
    }

    public String getDeviceIdForSubscription(int i)
    {
        return getDeviceIdForSlot(Holder.SUBSCRIPTION_MANAGER.getSlotIdForSubscription(i));
    }

    public List getDeviceIdList()
    {
        List list = null;
        IMiuiTelephony imiuitelephony;
        try
        {
            imiuitelephony = getMiuiTelephony();
        }
        catch(Exception exception)
        {
            Rlog.e("TelephonyManager", (new StringBuilder()).append("getDeviceIdList").append(exception).toString());
            return new ArrayList(0);
        }
        if(imiuitelephony != null) goto _L2; else goto _L1
_L1:
        return list;
_L2:
        list = imiuitelephony.getDeviceIdList(Holder.CONTEXT.getOpPackageName());
        if(true) goto _L1; else goto _L3
_L3:
    }

    public String getDeviceSoftwareVersion()
    {
        return Holder.TELEPHONY_MANAGER.getDeviceSoftwareVersion();
    }

    public String getDeviceSoftwareVersionForSlot(int i)
    {
        return Holder.TELEPHONY_MANAGER.getDeviceSoftwareVersion();
    }

    public String getDeviceSoftwareVersionForSubscription(int i)
    {
        return Holder.TELEPHONY_MANAGER.getDeviceSoftwareVersion();
    }

    public String getImei()
    {
        return getImeiForSlot(Holder.SUBSCRIPTION_MANAGER.getDefaultSlotId());
    }

    public String getImeiForSlot(int i)
    {
        String s = null;
        IMiuiTelephony imiuitelephony;
        try
        {
            imiuitelephony = getMiuiTelephony();
        }
        catch(Exception exception)
        {
            Rlog.e("TelephonyManager", (new StringBuilder()).append("getImeiForSlot ").append(exception).toString());
            return null;
        }
        if(imiuitelephony != null) goto _L2; else goto _L1
_L1:
        return s;
_L2:
        s = imiuitelephony.getImei(normalizeSlotId(i), Holder.CONTEXT.getOpPackageName());
        if(true) goto _L1; else goto _L3
_L3:
    }

    public String getImeiForSubscription(int i)
    {
        return getImeiForSlot(Holder.SUBSCRIPTION_MANAGER.getSlotIdForSubscription(i));
    }

    public List getImeiList()
    {
        List list = null;
        IMiuiTelephony imiuitelephony;
        try
        {
            imiuitelephony = getMiuiTelephony();
        }
        catch(Exception exception)
        {
            Rlog.e("TelephonyManager", (new StringBuilder()).append("getImeiList").append(exception).toString());
            return new ArrayList(0);
        }
        if(imiuitelephony != null) goto _L2; else goto _L1
_L1:
        return list;
_L2:
        list = imiuitelephony.getImeiList(Holder.CONTEXT.getOpPackageName());
        if(true) goto _L1; else goto _L3
_L3:
    }

    public String getLine1Number()
    {
        return getLine1NumberForSubscription(Holder.SUBSCRIPTION_MANAGER.getDefaultSubscriptionId());
    }

    public String getLine1NumberForSlot(int i)
    {
        return getLine1NumberForSubscription(Holder.SUBSCRIPTION_MANAGER.getSubscriptionIdForSlot(i));
    }

    public String getLine1NumberForSubscription(int i)
    {
        return Holder.TELEPHONY_MANAGER.getLine1Number(normalizeSubscriptionId(i));
    }

    public String getMeid()
    {
        return getMeidForSlot(Holder.SUBSCRIPTION_MANAGER.getDefaultSlotId());
    }

    public String getMeidForSlot(int i)
    {
        String s = null;
        IMiuiTelephony imiuitelephony;
        try
        {
            imiuitelephony = getMiuiTelephony();
        }
        catch(Exception exception)
        {
            Rlog.e("TelephonyManager", (new StringBuilder()).append("getMeidForSlot ").append(exception).toString());
            return null;
        }
        if(imiuitelephony != null) goto _L2; else goto _L1
_L1:
        return s;
_L2:
        s = imiuitelephony.getMeid(normalizeSlotId(i), Holder.CONTEXT.getOpPackageName());
        if(true) goto _L1; else goto _L3
_L3:
    }

    public String getMeidForSubscription(int i)
    {
        return getMeidForSlot(Holder.SUBSCRIPTION_MANAGER.getSlotIdForSubscription(i));
    }

    public List getMeidList()
    {
        List list = null;
        IMiuiTelephony imiuitelephony;
        try
        {
            imiuitelephony = getMiuiTelephony();
        }
        catch(Exception exception)
        {
            Rlog.e("TelephonyManager", (new StringBuilder()).append("getMeidList").append(exception).toString());
            return new ArrayList(0);
        }
        if(imiuitelephony != null) goto _L2; else goto _L1
_L1:
        return list;
_L2:
        list = imiuitelephony.getMeidList(Holder.CONTEXT.getOpPackageName());
        if(true) goto _L1; else goto _L3
_L3:
    }

    public String getMiuiDeviceId()
    {
        String s = null;
        IMiuiTelephony imiuitelephony;
        try
        {
            imiuitelephony = getMiuiTelephony();
        }
        catch(Exception exception)
        {
            Rlog.e("TelephonyManager", (new StringBuilder()).append("getDeviceId").append(exception).toString());
            return null;
        }
        if(imiuitelephony != null) goto _L2; else goto _L1
_L1:
        return s;
_L2:
        s = imiuitelephony.getDeviceId(Holder.CONTEXT.getOpPackageName());
        if(true) goto _L1; else goto _L3
_L3:
    }

    public IMiuiTelephony getMiuiTelephony()
    {
        if(sRegistry != null) goto _L2; else goto _L1
_L1:
        miui/telephony/TelephonyManagerEx;
        JVM INSTR monitorenter ;
        if(sRegistry == null)
            sRegistry = com.android.internal.telephony.ITelephonyRegistry.Stub.asInterface(ServiceManager.getService("telephony.registry"));
        miui/telephony/TelephonyManagerEx;
        JVM INSTR monitorexit ;
_L2:
        if(sRegistry != null)
            return sRegistry.getMiuiTelephony();
        break MISSING_BLOCK_LABEL_62;
        Object obj;
        obj;
        miui/telephony/TelephonyManagerEx;
        JVM INSTR monitorexit ;
        throw obj;
        obj;
        Rlog.e("TelephonyManager", "getMiuiTelephony error", ((Throwable) (obj)));
        return null;
    }

    public String getMsisdn()
    {
        return getMsisdnForSubscription(Holder.SUBSCRIPTION_MANAGER.getDefaultSubscriptionId());
    }

    public String getMsisdnForSlot(int i)
    {
        return getMsisdnForSubscription(Holder.SUBSCRIPTION_MANAGER.getSubscriptionIdForSlot(i));
    }

    public String getMsisdnForSubscription(int i)
    {
        return Holder.TELEPHONY_MANAGER.getMsisdn(normalizeSubscriptionId(i));
    }

    public List getNeighboringCellInfo()
    {
        return Holder.TELEPHONY_MANAGER.getNeighboringCellInfo();
    }

    public List getNeighboringCellInfoForSlot(int i)
    {
        return Holder.TELEPHONY_MANAGER.getNeighboringCellInfo();
    }

    public List getNeighboringCellInfoForSubscription(int i)
    {
        return Holder.TELEPHONY_MANAGER.getNeighboringCellInfo();
    }

    public int getNetworkClass(int i)
    {
        return TelephonyManager.getNetworkClass(i);
    }

    public String getNetworkCountryIso()
    {
        return getNetworkCountryIsoForSubscription(Holder.SUBSCRIPTION_MANAGER.getDefaultSubscriptionId());
    }

    public String getNetworkCountryIsoForSlot(int i)
    {
        return Holder.TELEPHONY_MANAGER.getNetworkCountryIsoForPhone(normalizeSlotId(i));
    }

    public String getNetworkCountryIsoForSubscription(int i)
    {
        return Holder.TELEPHONY_MANAGER.getNetworkCountryIso(normalizeSubscriptionId(i));
    }

    public String getNetworkOperator()
    {
        return getNetworkOperatorForSubscription(Holder.SUBSCRIPTION_MANAGER.getDefaultSubscriptionId());
    }

    public String getNetworkOperatorForSlot(int i)
    {
        return Holder.TELEPHONY_MANAGER.getNetworkOperatorForPhone(normalizeSlotId(i));
    }

    public String getNetworkOperatorForSubscription(int i)
    {
        return Holder.TELEPHONY_MANAGER.getNetworkOperator(normalizeSubscriptionId(i));
    }

    public String getNetworkOperatorName()
    {
        return getNetworkOperatorNameForSubscription(Holder.SUBSCRIPTION_MANAGER.getDefaultSubscriptionId());
    }

    public String getNetworkOperatorNameForSlot(int i)
    {
        return getNetworkOperatorNameForSubscription(Holder.SUBSCRIPTION_MANAGER.getSubscriptionIdForSlot(i));
    }

    public String getNetworkOperatorNameForSubscription(int i)
    {
        return Holder.TELEPHONY_MANAGER.getNetworkOperatorName(normalizeSubscriptionId(i));
    }

    public int getNetworkType()
    {
        return getNetworkTypeForSubscription(Holder.SUBSCRIPTION_MANAGER.getDefaultSubscriptionId());
    }

    public int getNetworkTypeForSlot(int i)
    {
        return getNetworkTypeForSubscription(Holder.SUBSCRIPTION_MANAGER.getSubscriptionIdForSlot(i));
    }

    public int getNetworkTypeForSubscription(int i)
    {
        return Holder.TELEPHONY_MANAGER.getNetworkType(normalizeSubscriptionId(i));
    }

    public String getNetworkTypeName(int i)
    {
        return TelephonyManager.getNetworkTypeName(i);
    }

    public int getPhoneCount()
    {
        return Holder.PHONE_COUNT;
    }

    public int getPhoneType()
    {
        return getPhoneTypeForSubscription(Holder.SUBSCRIPTION_MANAGER.getDefaultSubscriptionId());
    }

    public int getPhoneTypeForSlot(int i)
    {
        return getPhoneTypeForSubscription(Holder.SUBSCRIPTION_MANAGER.getSubscriptionIdForSlot(i));
    }

    public int getPhoneTypeForSubscription(int i)
    {
        if(!isVoiceCapable())
            return 0;
        else
            return Holder.TELEPHONY_MANAGER.getCurrentPhoneType(normalizeSubscriptionId(i));
    }

    public String getSimCountryIso()
    {
        return getSimCountryIsoForSubscription(Holder.SUBSCRIPTION_MANAGER.getDefaultSubscriptionId());
    }

    public String getSimCountryIsoForSlot(int i)
    {
        return getSimCountryIsoForSubscription(Holder.SUBSCRIPTION_MANAGER.getSubscriptionIdForSlot(i));
    }

    public String getSimCountryIsoForSubscription(int i)
    {
        return Holder.TELEPHONY_MANAGER.getSimCountryIso(normalizeSubscriptionId(i));
    }

    public String getSimOperator()
    {
        return getSimOperatorForSubscription(Holder.SUBSCRIPTION_MANAGER.getDefaultSubscriptionId());
    }

    public String getSimOperatorForSlot(int i)
    {
        return getSimOperatorForSubscription(Holder.SUBSCRIPTION_MANAGER.getSubscriptionIdForSlot(i));
    }

    public String getSimOperatorForSubscription(int i)
    {
        return Holder.TELEPHONY_MANAGER.getSimOperator(normalizeSubscriptionId(i));
    }

    public String getSimOperatorName()
    {
        return getSimOperatorNameForSubscription(Holder.SUBSCRIPTION_MANAGER.getDefaultSubscriptionId());
    }

    public String getSimOperatorNameForSlot(int i)
    {
        return Holder.TELEPHONY_MANAGER.getSimOperatorNameForPhone(normalizeSlotId(i));
    }

    public String getSimOperatorNameForSubscription(int i)
    {
        return Holder.TELEPHONY_MANAGER.getSimOperatorName(normalizeSubscriptionId(i));
    }

    public String getSimSerialNumber()
    {
        return getSimSerialNumberForSubscription(Holder.SUBSCRIPTION_MANAGER.getDefaultSubscriptionId());
    }

    public String getSimSerialNumberForSlot(int i)
    {
        return getSimSerialNumberForSubscription(Holder.SUBSCRIPTION_MANAGER.getSubscriptionIdForSlot(i));
    }

    public String getSimSerialNumberForSubscription(int i)
    {
        return Holder.TELEPHONY_MANAGER.getSimSerialNumber(normalizeSubscriptionId(i));
    }

    public int getSimState()
    {
        return getSimStateForSlot(Holder.SUBSCRIPTION_MANAGER.getDefaultSlotId());
    }

    public int getSimStateForSlot(int i)
    {
        return Holder.TELEPHONY_MANAGER.getSimState(normalizeSlotId(i));
    }

    public int getSimStateForSubscription(int i)
    {
        return getSimStateForSlot(Holder.SUBSCRIPTION_MANAGER.getSlotIdForSubscription(i));
    }

    public String getSmallDeviceId()
    {
        String s = null;
        IMiuiTelephony imiuitelephony;
        try
        {
            imiuitelephony = getMiuiTelephony();
        }
        catch(Exception exception)
        {
            Rlog.e("TelephonyManager", (new StringBuilder()).append("getSmallDeviceId").append(exception).toString());
            return null;
        }
        if(imiuitelephony != null) goto _L2; else goto _L1
_L1:
        return s;
_L2:
        s = imiuitelephony.getSmallDeviceId(Holder.CONTEXT.getOpPackageName());
        if(true) goto _L1; else goto _L3
_L3:
    }

    public String getSpn(String s, int i, String s1, boolean flag)
    {
        String s2 = null;
        IMiuiTelephony imiuitelephony = getMiuiTelephony();
        if(imiuitelephony == null)
            s = null;
        else
            try
            {
                s = imiuitelephony.getSpn(s, i, s1, flag);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                Rlog.e("TelephonyManager", "getSpn error", s);
                s = s2;
            }
        s2 = s;
        if(s == null)
            if((s1 == null || s1.length() == 0) && SubscriptionManager.isValidSlotId(i))
                s2 = getSimOperatorNameForSlot(i);
            else
                s2 = s1;
        return s2;
    }

    public String getSubscriberId()
    {
        return getSubscriberIdForSubscription(Holder.SUBSCRIPTION_MANAGER.getDefaultSubscriptionId());
    }

    public String getSubscriberIdForSlot(int i)
    {
        return getSubscriberIdForSubscription(Holder.SUBSCRIPTION_MANAGER.getSubscriptionIdForSlot(i));
    }

    public String getSubscriberIdForSubscription(int i)
    {
        return Holder.TELEPHONY_MANAGER.getSubscriberId(normalizeSubscriptionId(i));
    }

    public String getTelephonyProperty(int i, String s, String s1)
    {
        return TelephonyManager.getTelephonyProperty(normalizeSlotId(i), s, s1);
    }

    public String getTelephonySetting(int i, ContentResolver contentresolver, String s)
    {
        i = normalizeSlotId(i);
        contentresolver = android.provider.Settings.Global.getString(contentresolver, s);
        if(contentresolver != null)
        {
            contentresolver = contentresolver.split(",");
            if(i >= 0 && i < contentresolver.length && contentresolver[i] != null)
                return contentresolver[i];
        }
        return "";
    }

    public String getVoiceMailAlphaTag()
    {
        return getVoiceMailAlphaTagForSubscription(Holder.SUBSCRIPTION_MANAGER.getDefaultSubscriptionId());
    }

    public String getVoiceMailAlphaTagForSlot(int i)
    {
        return getVoiceMailAlphaTagForSubscription(Holder.SUBSCRIPTION_MANAGER.getSubscriptionIdForSlot(i));
    }

    public String getVoiceMailAlphaTagForSubscription(int i)
    {
        return Holder.TELEPHONY_MANAGER.getVoiceMailAlphaTag(normalizeSubscriptionId(i));
    }

    public String getVoiceMailNumber()
    {
        return getVoiceMailNumberForSubscription(Holder.SUBSCRIPTION_MANAGER.getDefaultSubscriptionId());
    }

    public String getVoiceMailNumberForSlot(int i)
    {
        return getVoiceMailNumberForSubscription(Holder.SUBSCRIPTION_MANAGER.getSubscriptionIdForSlot(i));
    }

    public String getVoiceMailNumberForSubscription(int i)
    {
        return Holder.TELEPHONY_MANAGER.getVoiceMailNumber(normalizeSubscriptionId(i));
    }

    public int getVoiceNetworkType()
    {
        return getVoiceNetworkTypeForSubscription(Holder.SUBSCRIPTION_MANAGER.getDefaultSubscriptionId());
    }

    public int getVoiceNetworkTypeForSlot(int i)
    {
        return getVoiceNetworkTypeForSubscription(Holder.SUBSCRIPTION_MANAGER.getSubscriptionIdForSlot(i));
    }

    public int getVoiceNetworkTypeForSubscription(int i)
    {
        return Holder.TELEPHONY_MANAGER.getVoiceNetworkType(normalizeSubscriptionId(i));
    }

    public boolean hasIccCard()
    {
        int i = getPhoneCount();
        for(int j = 0; j < i; j++)
            if(hasIccCard(j))
                return true;

        return false;
    }

    public boolean hasIccCard(int i)
    {
        return Holder.TELEPHONY_MANAGER.hasIccCard(normalizeSlotId(i));
    }

    public boolean isDualVolteSupported()
    {
        boolean flag;
        if(Holder.IS_DUAL_VOLTE_SUPPORTED)
            flag = isCustSingleSimDevice() ^ true;
        else
            flag = false;
        return flag;
    }

    public boolean isImsRegistered(int i)
    {
        boolean flag = false;
        IMiuiTelephony imiuitelephony;
        try
        {
            imiuitelephony = getMiuiTelephony();
        }
        catch(Exception exception)
        {
            Rlog.e("TelephonyManager", "isImsRegistered exception", exception);
            return false;
        }
        if(imiuitelephony != null) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        flag = imiuitelephony.isImsRegistered(i);
        if(true) goto _L1; else goto _L3
_L3:
    }

    public boolean isMultiSimEnabled()
    {
        boolean flag = true;
        if(getPhoneCount() <= 1)
            flag = false;
        return flag;
    }

    public boolean isNetworkRoaming()
    {
        return isNetworkRoamingForSubscription(Holder.SUBSCRIPTION_MANAGER.getDefaultSubscriptionId());
    }

    public boolean isNetworkRoamingForSlot(int i)
    {
        return isNetworkRoamingForSubscription(Holder.SUBSCRIPTION_MANAGER.getSubscriptionIdForSlot(i));
    }

    public boolean isNetworkRoamingForSubscription(int i)
    {
        return Holder.TELEPHONY_MANAGER.isNetworkRoaming(normalizeSubscriptionId(i));
    }

    public boolean isRadioOn()
    {
        int i = getPhoneCount();
        for(int j = 0; j < i; j++)
            if(isRadioOnForSlot(j))
                return true;

        return false;
    }

    public boolean isRadioOnForSlot(int i)
    {
        return isRadioOnForSubscription(Holder.SUBSCRIPTION_MANAGER.getSubscriptionIdForSlot(i));
    }

    public boolean isRadioOnForSubscription(int i)
    {
        boolean flag;
        try
        {
            flag = getITelephony().isRadioOnForSubscriber(normalizeSubscriptionId(i), Holder.CONTEXT.getOpPackageName());
        }
        catch(RemoteException remoteexception)
        {
            Rlog.e("TelephonyManager", "Error calling ITelephony#supplyPukReportResultForSubscriber", remoteexception);
            return false;
        }
        return flag;
    }

    public boolean isSameOperator(String s, String s1)
    {
        boolean flag = false;
        IMiuiTelephony imiuitelephony;
        try
        {
            imiuitelephony = getMiuiTelephony();
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Rlog.e("TelephonyManager", "isSameOperator error", s);
            return false;
        }
        if(imiuitelephony != null) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        flag = imiuitelephony.isSameOperator(s, s1);
        if(true) goto _L1; else goto _L3
_L3:
    }

    public boolean isSmsCapable()
    {
        return Holder.TELEPHONY_MANAGER.isSmsCapable();
    }

    public boolean isVoiceCapable()
    {
        return Holder.TELEPHONY_MANAGER.isVoiceCapable();
    }

    public boolean isVolteEnabledByPlatform()
    {
        boolean flag = false;
        IMiuiTelephony imiuitelephony;
        try
        {
            imiuitelephony = getMiuiTelephony();
        }
        catch(Exception exception)
        {
            Rlog.e("TelephonyManager", "isVolteEnabledByPlatform exception", exception);
            return false;
        }
        if(imiuitelephony != null) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        flag = imiuitelephony.isVolteEnabledByPlatform();
        if(true) goto _L1; else goto _L3
_L3:
    }

    public boolean isVolteEnabledByPlatform(int i)
    {
        boolean flag = false;
        IMiuiTelephony imiuitelephony;
        try
        {
            imiuitelephony = getMiuiTelephony();
        }
        catch(Exception exception)
        {
            Rlog.e("TelephonyManager", "isVolteEnabledByPlatform exception", exception);
            return false;
        }
        if(imiuitelephony != null) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        flag = imiuitelephony.isVolteEnabledByPlatformForSlot(i);
        if(true) goto _L1; else goto _L3
_L3:
    }

    public boolean isVolteEnabledByUser()
    {
        boolean flag = false;
        IMiuiTelephony imiuitelephony;
        try
        {
            imiuitelephony = getMiuiTelephony();
        }
        catch(Exception exception)
        {
            Rlog.e("TelephonyManager", "isVolteEnabledByUser exception", exception);
            return false;
        }
        if(imiuitelephony != null) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        flag = imiuitelephony.isVolteEnabledByUser();
        if(true) goto _L1; else goto _L3
_L3:
    }

    public boolean isVolteEnabledByUser(int i)
    {
        boolean flag = false;
        IMiuiTelephony imiuitelephony;
        try
        {
            imiuitelephony = getMiuiTelephony();
        }
        catch(Exception exception)
        {
            Rlog.e("TelephonyManager", "isVolteEnabledByUser exception", exception);
            return false;
        }
        if(imiuitelephony != null) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        flag = imiuitelephony.isVolteEnabledByUserForSlot(i);
        if(true) goto _L1; else goto _L3
_L3:
    }

    public boolean isVtEnabledByPlatform()
    {
        boolean flag = false;
        IMiuiTelephony imiuitelephony;
        try
        {
            imiuitelephony = getMiuiTelephony();
        }
        catch(Exception exception)
        {
            Rlog.e("TelephonyManager", "isVtEnabledByPlatform exception", exception);
            return false;
        }
        if(imiuitelephony != null) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        flag = imiuitelephony.isVtEnabledByPlatform();
        if(true) goto _L1; else goto _L3
_L3:
    }

    public boolean isVtEnabledByPlatform(int i)
    {
        boolean flag = false;
        IMiuiTelephony imiuitelephony;
        try
        {
            imiuitelephony = getMiuiTelephony();
        }
        catch(Exception exception)
        {
            Rlog.e("TelephonyManager", "isVtEnabledByPlatform exception", exception);
            return false;
        }
        if(imiuitelephony != null) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        flag = imiuitelephony.isVtEnabledByPlatformForSlot(i);
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void listen(PhoneStateListener phonestatelistener, int i)
    {
        Holder.TELEPHONY_MANAGER.listen(phonestatelistener, i);
    }

    public void listenForSlot(int i, PhoneStateListener phonestatelistener, int j)
    {
        listenForSubscription(Holder.SUBSCRIPTION_MANAGER.getSubscriptionIdForSlot(i), phonestatelistener, j);
    }

    public void listenForSubscription(int i, PhoneStateListener phonestatelistener, int j)
    {
        Integer integer = phonestatelistener.updateSubscription(Integer.valueOf(i));
        Holder.TELEPHONY_MANAGER.listen(phonestatelistener, j);
        phonestatelistener.updateSubscription(integer);
    }

    public String onOperatorNumericOrNameSet(int i, String s, String s1)
    {
        IMiuiTelephony imiuitelephony;
        try
        {
            imiuitelephony = getMiuiTelephony();
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Rlog.e("TelephonyManager", "onOperatorNumericOrNameSet error", s);
            return s1;
        }
        if(imiuitelephony != null) goto _L2; else goto _L1
_L1:
        return s1;
_L2:
        s = imiuitelephony.onOperatorNumericOrNameSet(i, s, s1);
        s1 = s;
        if(true) goto _L1; else goto _L3
_L3:
    }

    public boolean putTelephonySetting(int i, ContentResolver contentresolver, String s, String s1)
    {
        int j = normalizeSlotId(i);
        StringBuilder stringbuilder = new StringBuilder(128);
        String as[] = null;
        String s2 = android.provider.Settings.Global.getString(contentresolver, s);
        if(s2 != null)
            as = s2.split(",");
        for(i = 0; i < j; i++)
        {
            String s3 = "";
            s2 = s3;
            if(as != null)
            {
                s2 = s3;
                if(i < as.length)
                    s2 = as[i];
            }
            stringbuilder.append(s2).append(',');
        }

        s2 = s1;
        if(s1 == null)
            s2 = "";
        stringbuilder.append(s2);
        if(as != null)
            for(i = j + 1; i < as.length; i++)
                stringbuilder.append(',').append(as[i]);

        return android.provider.Settings.Global.putString(contentresolver, s, stringbuilder.toString());
    }

    public void setCallForwardingOption(int i, int j, int k, String s, ResultReceiver resultreceiver)
    {
        IMiuiTelephony imiuitelephony = getMiuiTelephony();
        if(imiuitelephony == null)
            break MISSING_BLOCK_LABEL_25;
        imiuitelephony.setCallForwardingOption(i, j, k, s, resultreceiver);
_L1:
        return;
        s;
        Rlog.e("TelephonyManager", "setCallForwardingOption exception", s);
        if(resultreceiver != null)
            resultreceiver.send(-1, null);
          goto _L1
    }

    public void setIccCardActivate(int i, boolean flag)
    {
        IMiuiTelephony imiuitelephony = getMiuiTelephony();
        if(imiuitelephony == null)
            break MISSING_BLOCK_LABEL_17;
        imiuitelephony.setIccCardActivate(i, flag);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Rlog.e("TelephonyManager", "setIccCardActivate error", remoteexception);
          goto _L1
    }

    public void setTelephonyProperty(int i, String s, String s1)
    {
        TelephonyManager.setTelephonyProperty(normalizeSlotId(i), s, s1);
    }

    public boolean showCallScreen()
    {
        return showCallScreenWithDialpad(false);
    }

    public boolean showCallScreenWithDialpad(boolean flag)
    {
        try
        {
            getTelecomService().showInCallScreen(flag, Holder.CONTEXT.getOpPackageName());
        }
        catch(RemoteException remoteexception)
        {
            Rlog.e("TelephonyManager", "Error calling ITelecomService#showInCallScreen", remoteexception);
            return false;
        }
        return true;
    }

    public void silenceRinger()
    {
        getTelecomService().silenceRinger(Holder.CONTEXT.getOpPackageName());
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Rlog.e("TelephonyManager", "Error call ITelecomService#silenceRinger", remoteexception);
          goto _L1
    }

    public int[] supplyPinReportResult(String s)
    {
        return supplyPinReportResultForSubscription(Holder.SUBSCRIPTION_MANAGER.getDefaultSubscriptionId(), s);
    }

    public int[] supplyPinReportResultForSlot(int i, String s)
    {
        return supplyPinReportResultForSubscription(Holder.SUBSCRIPTION_MANAGER.getSubscriptionIdForSlot(i), s);
    }

    public int[] supplyPinReportResultForSubscription(int i, String s)
    {
        try
        {
            s = getITelephony().supplyPinReportResultForSubscriber(normalizeSubscriptionId(i), s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Rlog.e("TelephonyManager", "Error calling ITelephony#supplyPinReportResultForSubscriber", s);
            return new int[0];
        }
        return s;
    }

    public int[] supplyPukReportResult(String s, String s1)
    {
        return supplyPukReportResultForSubscription(Holder.SUBSCRIPTION_MANAGER.getDefaultSubscriptionId(), s, s1);
    }

    public int[] supplyPukReportResultForSlot(int i, String s, String s1)
    {
        return supplyPukReportResultForSubscription(Holder.SUBSCRIPTION_MANAGER.getSubscriptionIdForSlot(i), s, s1);
    }

    public int[] supplyPukReportResultForSubscription(int i, String s, String s1)
    {
        try
        {
            s = getITelephony().supplyPukReportResultForSubscriber(normalizeSubscriptionId(i), s, s1);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Rlog.e("TelephonyManager", "Error calling ITelephony#supplyPukReportResultForSubscriber", s);
            return new int[0];
        }
        return s;
    }

    public static final int NETWORK_CLASS_2_G = 1;
    public static final int NETWORK_CLASS_3_G = 2;
    public static final int NETWORK_CLASS_4_G = 3;
    public static final int NETWORK_CLASS_UNKNOWN = 0;
    public static final int NETWORK_TYPE_GSM = 16;
    public static final int NETWORK_TYPE_TD_SCDMA = 17;
    public static final String PROPERTY_DBG_VOLTE_AVAIL_OVERRIDE = "persist.dbg.volte_avail_ovr";
    public static final String PROPERTY_DBG_VT_AVAIL_OVERRIDE = "persist.dbg.vt_avail_ovr";
    private static final String TAG = "TelephonyManager";
    private static ITelephonyRegistry sRegistry;
}
