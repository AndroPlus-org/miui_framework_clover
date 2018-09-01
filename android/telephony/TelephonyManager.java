// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.app.ActivityThread;
import android.app.PendingIntent;
import android.content.*;
import android.content.res.Resources;
import android.net.*;
import android.os.*;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.util.Log;
import android.util.SeempLog;
import com.android.ims.internal.IImsServiceController;
import com.android.ims.internal.IImsServiceFeatureListener;
import com.android.internal.telecom.ITelecomService;
import com.android.internal.telephony.*;
import com.android.internal.util.Preconditions;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import miui.telephony.TelephonyManagerEx;

// Referenced classes of package android.telephony:
//            SubscriptionManager, Rlog, CarrierConfigManager, CellLocation, 
//            PhoneStateListener, TelephonyScanManager, UssdResponse, ServiceState, 
//            VisualVoicemailSmsFilterSettings, ImsiEncryptionInfo, SignalStrength, IccOpenLogicalChannelResponse, 
//            NetworkScanRequest, NetworkScan

public class TelephonyManager
{
    public static final class MultiSimVariants extends Enum
    {

        public static MultiSimVariants valueOf(String s)
        {
            return (MultiSimVariants)Enum.valueOf(android/telephony/TelephonyManager$MultiSimVariants, s);
        }

        public static MultiSimVariants[] values()
        {
            return $VALUES;
        }

        private static final MultiSimVariants $VALUES[];
        public static final MultiSimVariants DSDA;
        public static final MultiSimVariants DSDS;
        public static final MultiSimVariants TSTS;
        public static final MultiSimVariants UNKNOWN;

        static 
        {
            DSDS = new MultiSimVariants("DSDS", 0);
            DSDA = new MultiSimVariants("DSDA", 1);
            TSTS = new MultiSimVariants("TSTS", 2);
            UNKNOWN = new MultiSimVariants("UNKNOWN", 3);
            $VALUES = (new MultiSimVariants[] {
                DSDS, DSDA, TSTS, UNKNOWN
            });
        }

        private MultiSimVariants(String s, int i)
        {
            super(s, i);
        }
    }

    public static abstract class UssdResponseCallback
    {

        public void onReceiveUssdResponse(TelephonyManager telephonymanager, String s, CharSequence charsequence)
        {
        }

        public void onReceiveUssdResponseFailed(TelephonyManager telephonymanager, String s, int i)
        {
        }

        public UssdResponseCallback()
        {
        }
    }

    public static interface WifiCallingChoices
    {

        public static final int ALWAYS_USE = 0;
        public static final int ASK_EVERY_TIME = 1;
        public static final int NEVER_USE = 2;
    }


    private static int[] _2D_getandroid_2D_telephony_2D_TelephonyManager$MultiSimVariantsSwitchesValues()
    {
        if(_2D_android_2D_telephony_2D_TelephonyManager$MultiSimVariantsSwitchesValues != null)
            return _2D_android_2D_telephony_2D_TelephonyManager$MultiSimVariantsSwitchesValues;
        int ai[] = new int[MultiSimVariants.values().length];
        try
        {
            ai[MultiSimVariants.DSDA.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror3) { }
        try
        {
            ai[MultiSimVariants.DSDS.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            ai[MultiSimVariants.TSTS.ordinal()] = 3;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            ai[MultiSimVariants.UNKNOWN.ordinal()] = 4;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        _2D_android_2D_telephony_2D_TelephonyManager$MultiSimVariantsSwitchesValues = ai;
        return ai;
    }

    private TelephonyManager()
    {
        mContext = null;
        mSubId = -1;
    }

    public TelephonyManager(Context context)
    {
        this(context, 0x7fffffff);
    }

    public TelephonyManager(Context context, int i)
    {
        mSubId = i;
        Context context1 = context.getApplicationContext();
        if(context1 != null)
            mContext = context1;
        else
            mContext = context;
        mSubscriptionManager = SubscriptionManager.from(mContext);
    }

    public static TelephonyManager from(Context context)
    {
        return (TelephonyManager)context.getSystemService("phone");
    }

    public static TelephonyManager getDefault()
    {
        return sInstance;
    }

    private static int getDefaultDataSubscriptionId()
    {
        return SubscriptionManager.getDefaultDataSubscriptionId();
    }

    private ITelephony getITelephony()
    {
        return com.android.internal.telephony.ITelephony.Stub.asInterface(ServiceManager.getService("phone"));
    }

    public static int getIntAtIndex(ContentResolver contentresolver, String s, int i)
        throws android.provider.Settings.SettingNotFoundException
    {
        contentresolver = android.provider.Settings.Global.getString(contentresolver, s);
        if(contentresolver == null)
            break MISSING_BLOCK_LABEL_44;
        contentresolver = contentresolver.split(",");
        if(i < 0 || i >= contentresolver.length || contentresolver[i] == null)
            break MISSING_BLOCK_LABEL_44;
        i = Integer.parseInt(contentresolver[i]);
        return i;
        contentresolver;
        throw new android.provider.Settings.SettingNotFoundException(s);
    }

    public static int getIntWithSubId(ContentResolver contentresolver, String s, int i)
        throws android.provider.Settings.SettingNotFoundException
    {
        int j;
        StringBuilder stringbuilder = JVM INSTR new #456 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        j = android.provider.Settings.Global.getInt(contentresolver, stringbuilder.append(s).append(i).toString());
        return j;
        android.provider.Settings.SettingNotFoundException settingnotfoundexception;
        settingnotfoundexception;
        int k;
        boolean flag;
        try
        {
            k = android.provider.Settings.Global.getInt(contentresolver, s);
            StringBuilder stringbuilder1 = JVM INSTR new #456 <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            android.provider.Settings.Global.putInt(contentresolver, stringbuilder1.append(s).append(i).toString(), k);
        }
        // Misplaced declaration of an exception variable
        catch(ContentResolver contentresolver)
        {
            throw new android.provider.Settings.SettingNotFoundException(s);
        }
        i = k;
        if(!s.equals("mobile_data")) goto _L2; else goto _L1
_L1:
        if("true".equalsIgnoreCase(SystemProperties.get("ro.com.android.mobiledata", "true")))
            i = 1;
        else
            i = 0;
_L4:
        if(i == k)
            break MISSING_BLOCK_LABEL_110;
        android.provider.Settings.Global.putInt(contentresolver, s, i);
        return k;
_L2:
        if(!s.equals("data_roaming"))
            continue; /* Loop/switch isn't completed */
        flag = "true".equalsIgnoreCase(SystemProperties.get("ro.com.android.dataroaming", "false"));
        if(flag)
            i = 1;
        else
            i = 0;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static int getLteOnCdmaModeStatic()
    {
        String s = "";
        int i = SystemProperties.getInt("telephony.lteOnCdmaDevice", -1);
        int j = i;
        Object obj = s;
        if(i == -1)
        {
            obj = sProductTypePattern.matcher(sKernelCmdLine);
            if(((Matcher) (obj)).find())
            {
                obj = ((Matcher) (obj)).group(1);
                if(sLteOnCdmaProductType.equals(obj))
                    j = 1;
                else
                    j = 0;
            } else
            {
                j = 0;
                obj = s;
            }
        }
        Rlog.d("TelephonyManager", (new StringBuilder()).append("getLteOnCdmaMode=").append(j).append(" curVal=").append(i).append(" product_type='").append(((String) (obj))).append("' lteOnCdmaProductType='").append(sLteOnCdmaProductType).append("'").toString());
        return j;
    }

    public static int getNetworkClass(int i)
    {
        switch(i)
        {
        default:
            return 0;

        case 1: // '\001'
        case 2: // '\002'
        case 4: // '\004'
        case 7: // '\007'
        case 11: // '\013'
        case 16: // '\020'
            return 1;

        case 3: // '\003'
        case 5: // '\005'
        case 6: // '\006'
        case 8: // '\b'
        case 9: // '\t'
        case 10: // '\n'
        case 12: // '\f'
        case 14: // '\016'
        case 15: // '\017'
        case 17: // '\021'
        case 20: // '\024'
            return 2;

        case 13: // '\r'
        case 18: // '\022'
        case 19: // '\023'
            return 3;
        }
    }

    public static String getNetworkTypeName(int i)
    {
        switch(i)
        {
        default:
            return "UNKNOWN";

        case 1: // '\001'
            return "GPRS";

        case 2: // '\002'
            return "EDGE";

        case 3: // '\003'
            return "UMTS";

        case 8: // '\b'
            return "HSDPA";

        case 9: // '\t'
            return "HSUPA";

        case 10: // '\n'
            return "HSPA";

        case 4: // '\004'
            return "CDMA";

        case 5: // '\005'
            return "CDMA - EvDo rev. 0";

        case 6: // '\006'
            return "CDMA - EvDo rev. A";

        case 12: // '\f'
            return "CDMA - EvDo rev. B";

        case 7: // '\007'
            return "CDMA - 1xRTT";

        case 13: // '\r'
            return "LTE";

        case 14: // '\016'
            return "CDMA - eHRPD";

        case 11: // '\013'
            return "iDEN";

        case 15: // '\017'
            return "HSPA+";

        case 16: // '\020'
            return "GSM";

        case 17: // '\021'
            return "TD_SCDMA";

        case 18: // '\022'
            return "IWLAN";

        case 19: // '\023'
            return "LTE_CA";

        case 20: // '\024'
            return "DC_HSPAP";
        }
    }

    private String getOpPackageName()
    {
        if(mContext != null)
            return mContext.getOpPackageName();
        else
            return ActivityThread.currentOpPackageName();
    }

    private int getPhoneId()
    {
        return SubscriptionManager.getPhoneId(getSubId());
    }

    private int getPhoneId(int i)
    {
        return SubscriptionManager.getPhoneId(getSubId(i));
    }

    public static int getPhoneType(int i)
    {
        switch(i)
        {
        default:
            return 1;

        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
            return 2;

        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 9: // '\t'
        case 10: // '\n'
        case 12: // '\f'
        case 13: // '\r'
        case 14: // '\016'
        case 15: // '\017'
        case 16: // '\020'
        case 17: // '\021'
        case 18: // '\022'
        case 19: // '\023'
        case 20: // '\024'
        case 22: // '\026'
            return 1;

        case 7: // '\007'
        case 8: // '\b'
        case 21: // '\025'
            return 2;

        case 11: // '\013'
            break;
        }
        return getLteOnCdmaModeStatic() != 1 ? 1 : 2;
    }

    private int getPhoneTypeFromNetworkType()
    {
        return getPhoneTypeFromNetworkType(getPhoneId());
    }

    private int getPhoneTypeFromNetworkType(int i)
    {
        String s = getTelephonyProperty(i, "ro.telephony.default_network", null);
        if(s != null && s.isEmpty() ^ true)
            return getPhoneType(Integer.parseInt(s));
        else
            return 0;
    }

    private int getPhoneTypeFromProperty()
    {
        return getPhoneTypeFromProperty(getPhoneId());
    }

    private int getPhoneTypeFromProperty(int i)
    {
        String s = getTelephonyProperty(i, "gsm.current.phone-type", null);
        if(s == null || s.isEmpty())
            return getPhoneTypeFromNetworkType(i);
        else
            return Integer.parseInt(s);
    }

    private static String getProcCmdLine()
    {
        Object obj;
        byte abyte0[];
        StringBuilder stringbuilder;
        Object obj1;
        obj = "";
        abyte0 = null;
        stringbuilder = null;
        obj1 = abyte0;
        Object obj2 = JVM INSTR new #617 <Class FileInputStream>;
        obj1 = abyte0;
        ((FileInputStream) (obj2)).FileInputStream("/proc/cmdline");
        int i;
        abyte0 = new byte[2048];
        i = ((FileInputStream) (obj2)).read(abyte0);
        obj1 = obj;
        if(i <= 0)
            break MISSING_BLOCK_LABEL_58;
        obj1 = JVM INSTR new #439 <Class String>;
        ((String) (obj1)).String(abyte0, 0, i);
        if(obj2 != null)
            try
            {
                ((FileInputStream) (obj2)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj2) { }
_L2:
        Rlog.d("TelephonyManager", (new StringBuilder()).append("/proc/cmdline=").append(((String) (obj1))).toString());
        return ((String) (obj1));
        IOException ioexception;
        ioexception;
        obj2 = stringbuilder;
_L5:
        obj1 = obj2;
        stringbuilder = JVM INSTR new #456 <Class StringBuilder>;
        obj1 = obj2;
        stringbuilder.StringBuilder();
        obj1 = obj2;
        Rlog.d("TelephonyManager", stringbuilder.append("No /proc/cmdline exception=").append(ioexception).toString());
        obj1 = obj;
        if(obj2 == null) goto _L2; else goto _L1
_L1:
        ((FileInputStream) (obj2)).close();
        obj1 = obj;
          goto _L2
        obj1;
        obj1 = obj;
          goto _L2
        obj2;
        obj = obj1;
_L4:
        if(obj != null)
            try
            {
                ((FileInputStream) (obj)).close();
            }
            catch(IOException ioexception1) { }
        throw obj2;
        Exception exception;
        exception;
        obj = obj2;
        obj2 = exception;
        if(true) goto _L4; else goto _L3
_L3:
        ioexception;
          goto _L5
    }

    private int getSubId()
    {
        if(SubscriptionManager.isUsableSubIdValue(mSubId))
            return mSubId;
        else
            return SubscriptionManager.getDefaultSubscriptionId();
    }

    private int getSubId(int i)
    {
        if(SubscriptionManager.isUsableSubIdValue(mSubId))
            return mSubId;
        else
            return i;
    }

    private int getSubIdForPhoneAccountHandle(PhoneAccountHandle phoneaccounthandle)
    {
        byte byte0 = -1;
        ITelecomService itelecomservice = getTelecomService();
        int i = byte0;
        if(itelecomservice != null)
            try
            {
                i = getSubIdForPhoneAccount(itelecomservice.getPhoneAccount(phoneaccounthandle));
            }
            // Misplaced declaration of an exception variable
            catch(PhoneAccountHandle phoneaccounthandle)
            {
                i = byte0;
            }
        return i;
    }

    private IPhoneSubInfo getSubscriberInfo()
    {
        return com.android.internal.telephony.IPhoneSubInfo.Stub.asInterface(ServiceManager.getService("iphonesubinfo"));
    }

    private ITelecomService getTelecomService()
    {
        return com.android.internal.telecom.ITelecomService.Stub.asInterface(ServiceManager.getService("telecom"));
    }

    public static String getTelephonyProperty(int i, String s, String s1)
    {
        Object obj = null;
        String s2 = SystemProperties.get(s);
        s = obj;
        if(s2 != null)
        {
            s = obj;
            if(s2.length() > 0)
            {
                String as[] = s2.split(",");
                s = obj;
                if(i >= 0)
                {
                    s = obj;
                    if(i < as.length)
                    {
                        s = obj;
                        if(as[i] != null)
                            s = as[i];
                    }
                }
            }
        }
        if(s == null)
            s = s1;
        return s;
    }

    private ITelephonyRegistry getTelephonyRegistry()
    {
        return com.android.internal.telephony.ITelephonyRegistry.Stub.asInterface(ServiceManager.getService("telephony.registry"));
    }

    private boolean isImsiEncryptionRequired(int i, int j)
    {
        Object obj = (CarrierConfigManager)mContext.getSystemService("carrier_config");
        if(obj == null)
            return false;
        obj = ((CarrierConfigManager) (obj)).getConfigForSubId(i);
        if(obj == null)
            return false;
        else
            return isKeyEnabled(((PersistableBundle) (obj)).getInt("imsi_key_availability_int"), j);
    }

    private static boolean isKeyEnabled(int i, int j)
    {
        boolean flag = true;
        if((i >> j - 1 & 1) != 1)
            flag = false;
        return flag;
    }

    public static boolean putIntAtIndex(ContentResolver contentresolver, String s, int i, int j)
    {
        String s1 = "";
        String as[] = null;
        String s2 = android.provider.Settings.Global.getString(contentresolver, s);
        if(i == 0x7fffffff)
            throw new RuntimeException((new StringBuilder()).append("putIntAtIndex index == MAX_VALUE index=").append(i).toString());
        if(i < 0)
            throw new RuntimeException((new StringBuilder()).append("putIntAtIndex index < 0 index=").append(i).toString());
        if(s2 != null)
            as = s2.split(",");
        for(int k = 0; k < i; k++)
        {
            String s3 = "";
            s2 = s3;
            if(as != null)
            {
                s2 = s3;
                if(k < as.length)
                    s2 = as[k];
            }
            s1 = (new StringBuilder()).append(s1).append(s2).append(",").toString();
        }

        s1 = (new StringBuilder()).append(s1).append(j).toString();
        s2 = s1;
        if(as != null)
        {
            i++;
            do
            {
                s2 = s1;
                if(i >= as.length)
                    break;
                s1 = (new StringBuilder()).append(s1).append(",").append(as[i]).toString();
                i++;
            } while(true);
        }
        return android.provider.Settings.Global.putString(contentresolver, s, s2);
    }

    public static void setTelephonyProperty(int i, String s, String s1)
    {
        String s2;
label0:
        {
            if(!"gsm.sim.operator.alpha".equals(s) && !"gsm.operator.alpha".equals(s) && !"gsm.sim.operator.numeric".equals(s))
            {
                s2 = s1;
                if(!"gsm.operator.numeric".equals(s))
                    break label0;
            }
            s2 = TelephonyManagerEx.getDefault().onOperatorNumericOrNameSet(i, s, s1);
        }
        s1 = "";
        String s3 = null;
        String s4 = SystemProperties.get(s);
        String s6 = s2;
        if(s2 == null)
            s6 = "";
        String as[] = s3;
        if(s4 != null)
            as = s4.split(",");
        if(!SubscriptionManager.isValidPhoneId(i))
        {
            Rlog.d("TelephonyManager", (new StringBuilder()).append("setTelephonyProperty: invalid phoneId=").append(i).append(" property=").append(s).append(" value: ").append(s6).append(" prop=").append(s4).toString());
            return;
        }
        for(int j = 0; j < i; j++)
        {
            String s5 = "";
            s3 = s5;
            if(as != null)
            {
                s3 = s5;
                if(j < as.length)
                    s3 = as[j];
            }
            s1 = (new StringBuilder()).append(s1).append(s3).append(",").toString();
        }

        s1 = (new StringBuilder()).append(s1).append(s6).toString();
        s3 = s1;
        if(as != null)
        {
            int k = i + 1;
            do
            {
                s3 = s1;
                if(k >= as.length)
                    break;
                s1 = (new StringBuilder()).append(s1).append(",").append(as[k]).toString();
                k++;
            } while(true);
        }
        if(s3.length() > 91)
        {
            Rlog.d("TelephonyManager", (new StringBuilder()).append("setTelephonyProperty: property too long phoneId=").append(i).append(" property=").append(s).append(" value: ").append(s6).append(" propVal=").append(s3).toString());
            return;
        } else
        {
            SystemProperties.set(s, s3);
            return;
        }
    }

    public void answerRingingCall()
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_15;
        itelephony.answerRingingCall();
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelephonyManager", "Error calling ITelephony#answerRingingCall", remoteexception);
          goto _L1
    }

    public void call(String s, String s1)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_17;
        itelephony.call(s, s1);
_L1:
        return;
        s;
        Log.e("TelephonyManager", "Error calling ITelephony#call", s);
          goto _L1
    }

    public boolean canChangeDtmfToneLength()
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_29;
        boolean flag = itelephony.canChangeDtmfToneLength();
        return flag;
        Object obj;
        obj;
        Log.e("TelephonyManager", "Permission error calling ITelephony#canChangeDtmfToneLength", ((Throwable) (obj)));
_L2:
        return false;
        obj;
        Log.e("TelephonyManager", "Error calling ITelephony#canChangeDtmfToneLength", ((Throwable) (obj)));
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void carrierActionReportDefaultNetworkStatus(int i, boolean flag)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_17;
        itelephony.carrierActionReportDefaultNetworkStatus(i, flag);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelephonyManager", "Error calling ITelephony#carrierActionReportDefaultNetworkStatus", remoteexception);
          goto _L1
    }

    public void carrierActionSetMeteredApnsEnabled(int i, boolean flag)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_17;
        itelephony.carrierActionSetMeteredApnsEnabled(i, flag);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelephonyManager", "Error calling ITelephony#carrierActionSetMeteredApnsEnabled", remoteexception);
          goto _L1
    }

    public void carrierActionSetRadioEnabled(int i, boolean flag)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_17;
        itelephony.carrierActionSetRadioEnabled(i, flag);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelephonyManager", "Error calling ITelephony#carrierActionSetRadioEnabled", remoteexception);
          goto _L1
    }

    public int checkCarrierPrivilegesForPackage(String s)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_30;
        int i = itelephony.checkCarrierPrivilegesForPackage(s);
        return i;
        s;
        Rlog.e("TelephonyManager", "checkCarrierPrivilegesForPackage NPE", s);
_L2:
        return 0;
        s;
        Rlog.e("TelephonyManager", "checkCarrierPrivilegesForPackage RemoteException", s);
        if(true) goto _L2; else goto _L1
_L1:
    }

    public int checkCarrierPrivilegesForPackageAnyPhone(String s)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_30;
        int i = itelephony.checkCarrierPrivilegesForPackageAnyPhone(s);
        return i;
        s;
        Rlog.e("TelephonyManager", "checkCarrierPrivilegesForPackageAnyPhone NPE", s);
_L2:
        return 0;
        s;
        Rlog.e("TelephonyManager", "checkCarrierPrivilegesForPackageAnyPhone RemoteException", s);
        if(true) goto _L2; else goto _L1
_L1:
    }

    public TelephonyManager createForPhoneAccountHandle(PhoneAccountHandle phoneaccounthandle)
    {
        int i = getSubIdForPhoneAccountHandle(phoneaccounthandle);
        if(!SubscriptionManager.isValidSubscriptionId(i))
            return null;
        else
            return new TelephonyManager(mContext, i);
    }

    public TelephonyManager createForSubscriptionId(int i)
    {
        return new TelephonyManager(mContext, i);
    }

    public void dial(String s)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_16;
        itelephony.dial(s);
_L1:
        return;
        s;
        Log.e("TelephonyManager", "Error calling ITelephony#dial", s);
          goto _L1
    }

    public boolean disableDataConnectivity()
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_29;
        boolean flag = itelephony.disableDataConnectivity();
        return flag;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelephonyManager", "Error calling ITelephony#disableDataConnectivity", remoteexception);
        return false;
    }

    public void disableLocationUpdates()
    {
        disableLocationUpdates(getSubId());
    }

    public void disableLocationUpdates(int i)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_16;
        itelephony.disableLocationUpdatesForSubscriber(i);
_L2:
        return;
        Object obj;
        obj;
        continue; /* Loop/switch isn't completed */
        obj;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void disableVisualVoicemailSmsFilter(int i)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_23;
        itelephony.disableVisualVoicemailSmsFilter(mContext.getOpPackageName(), i);
_L2:
        return;
        Object obj;
        obj;
        continue; /* Loop/switch isn't completed */
        obj;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean enableDataConnectivity()
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_29;
        boolean flag = itelephony.enableDataConnectivity();
        return flag;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelephonyManager", "Error calling ITelephony#enableDataConnectivity", remoteexception);
        return false;
    }

    public void enableLocationUpdates()
    {
        enableLocationUpdates(getSubId());
    }

    public void enableLocationUpdates(int i)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_16;
        itelephony.enableLocationUpdatesForSubscriber(i);
_L2:
        return;
        Object obj;
        obj;
        continue; /* Loop/switch isn't completed */
        obj;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void enableVideoCalling(boolean flag)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_16;
        itelephony.enableVideoCalling(flag);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelephonyManager", "Error calling ITelephony#enableVideoCalling", remoteexception);
          goto _L1
    }

    public void enableVisualVoicemailSmsFilter(int i, VisualVoicemailSmsFilterSettings visualvoicemailsmsfiltersettings)
    {
        if(visualvoicemailsmsfiltersettings == null)
            throw new IllegalArgumentException("Settings cannot be null");
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_39;
        itelephony.enableVisualVoicemailSmsFilter(mContext.getOpPackageName(), i, visualvoicemailsmsfiltersettings);
_L2:
        return;
        visualvoicemailsmsfiltersettings;
        continue; /* Loop/switch isn't completed */
        visualvoicemailsmsfiltersettings;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean endCall()
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_29;
        boolean flag = itelephony.endCall();
        return flag;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelephonyManager", "Error calling ITelephony#endCall", remoteexception);
        return false;
    }

    public void factoryReset(int i)
    {
        Object obj;
        obj = JVM INSTR new #456 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Log.d("TelephonyManager", ((StringBuilder) (obj)).append("factoryReset: subId=").append(i).toString());
        obj = getITelephony();
        if(obj == null)
            break MISSING_BLOCK_LABEL_44;
        ((ITelephony) (obj)).factoryReset(i);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public VisualVoicemailSmsFilterSettings getActiveVisualVoicemailSmsFilterSettings(int i)
    {
        Object obj = getITelephony();
        if(obj == null)
            break MISSING_BLOCK_LABEL_20;
        obj = ((ITelephony) (obj)).getActiveVisualVoicemailSmsFilterSettings(i);
        return ((VisualVoicemailSmsFilterSettings) (obj));
        Object obj1;
        obj1;
_L2:
        return null;
        obj1;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public String getAidForAppType(int i)
    {
        return getAidForAppType(getSubId(), i);
    }

    public String getAidForAppType(int i, int j)
    {
        Object obj = getITelephony();
        if(obj == null)
            break MISSING_BLOCK_LABEL_31;
        obj = ((ITelephony) (obj)).getAidForAppType(i, j);
        return ((String) (obj));
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelephonyManager", "Error calling ITelephony#getAidForAppType", remoteexception);
        return null;
    }

    public List getAllCellInfo()
    {
        Object obj;
        try
        {
            obj = getITelephony();
        }
        catch(RemoteException remoteexception)
        {
            return null;
        }
        catch(NullPointerException nullpointerexception)
        {
            return null;
        }
        if(obj == null)
            return null;
        obj = ((ITelephony) (obj)).getAllCellInfo(getOpPackageName());
        return ((List) (obj));
    }

    public List getAllowedCarriers(int i)
    {
        Object obj = getITelephony();
        if(obj == null)
            break MISSING_BLOCK_LABEL_30;
        obj = ((ITelephony) (obj)).getAllowedCarriers(i);
        return ((List) (obj));
        Object obj1;
        obj1;
        Log.e("TelephonyManager", "Error calling ITelephony#getAllowedCarriers", ((Throwable) (obj1)));
_L2:
        return new ArrayList(0);
        obj1;
        Log.e("TelephonyManager", "Error calling ITelephony#getAllowedCarriers", ((Throwable) (obj1)));
        if(true) goto _L2; else goto _L1
_L1:
    }

    public int getCallState()
    {
        ITelecomService itelecomservice = getTelecomService();
        if(itelecomservice == null)
            break MISSING_BLOCK_LABEL_29;
        int i = itelecomservice.getCallState();
        return i;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelephonyManager", "Error calling ITelecomService#getCallState", remoteexception);
        return 0;
    }

    public int getCallState(int i)
    {
        return getCallStateForSlot(SubscriptionManager.getPhoneId(i));
    }

    public int getCallStateForSlot(int i)
    {
        ITelephony itelephony;
        try
        {
            itelephony = getITelephony();
        }
        catch(RemoteException remoteexception)
        {
            return 0;
        }
        catch(NullPointerException nullpointerexception)
        {
            return 0;
        }
        if(itelephony == null)
            return 0;
        i = itelephony.getCallStateForSlot(i);
        return i;
    }

    public PersistableBundle getCarrierConfig()
    {
        return ((CarrierConfigManager)mContext.getSystemService(android/telephony/CarrierConfigManager)).getConfigForSubId(getSubId());
    }

    public ImsiEncryptionInfo getCarrierInfoForImsiEncryption(int i)
    {
        Object obj = getSubscriberInfo();
        int j;
        if(obj == null)
        {
            try
            {
                obj = JVM INSTR new #713 <Class RuntimeException>;
                ((RuntimeException) (obj)).RuntimeException("IMSI error: Subscriber Info is null");
                throw obj;
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                Rlog.e("TelephonyManager", (new StringBuilder()).append("getCarrierInfoForImsiEncryption RemoteException").append(obj).toString());
                throw new RuntimeException("IMSI error: Remote Exception");
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                Rlog.e("TelephonyManager", (new StringBuilder()).append("getCarrierInfoForImsiEncryption NullPointerException").append(obj).toString());
            }
            break MISSING_BLOCK_LABEL_118;
        }
        j = getSubId(SubscriptionManager.getDefaultDataSubscriptionId());
        if(i == 1 || i == 2)
            break MISSING_BLOCK_LABEL_129;
        obj = JVM INSTR new #876 <Class IllegalArgumentException>;
        ((IllegalArgumentException) (obj)).IllegalArgumentException("IMSI error: Invalid key type");
        throw obj;
        throw new RuntimeException("IMSI error: Null Pointer exception");
        obj = ((IPhoneSubInfo) (obj)).getCarrierInfoForImsiEncryption(j, i, mContext.getOpPackageName());
        if(obj != null)
            break MISSING_BLOCK_LABEL_180;
        if(isImsiEncryptionRequired(j, i))
        {
            Rlog.e("TelephonyManager", "IMSI error: key is required but not found");
            obj = JVM INSTR new #713 <Class RuntimeException>;
            ((RuntimeException) (obj)).RuntimeException("IMSI error: key is required but not found");
            throw obj;
        }
        return ((ImsiEncryptionInfo) (obj));
    }

    public List getCarrierPackageNamesForIntent(Intent intent)
    {
        return getCarrierPackageNamesForIntentAndPhone(intent, getPhoneId());
    }

    public List getCarrierPackageNamesForIntentAndPhone(Intent intent, int i)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_31;
        intent = itelephony.getCarrierPackageNamesForIntentAndPhone(intent, i);
        return intent;
        intent;
        Rlog.e("TelephonyManager", "getCarrierPackageNamesForIntentAndPhone NPE", intent);
_L2:
        return null;
        intent;
        Rlog.e("TelephonyManager", "getCarrierPackageNamesForIntentAndPhone RemoteException", intent);
        if(true) goto _L2; else goto _L1
_L1:
    }

    public int getCdmaEriIconIndex()
    {
        return getCdmaEriIconIndex(getSubId());
    }

    public int getCdmaEriIconIndex(int i)
    {
        ITelephony itelephony;
        try
        {
            itelephony = getITelephony();
        }
        catch(RemoteException remoteexception)
        {
            return -1;
        }
        catch(NullPointerException nullpointerexception)
        {
            return -1;
        }
        if(itelephony == null)
            return -1;
        i = itelephony.getCdmaEriIconIndexForSubscriber(i, getOpPackageName());
        return i;
    }

    public int getCdmaEriIconMode()
    {
        return getCdmaEriIconMode(getSubId());
    }

    public int getCdmaEriIconMode(int i)
    {
        ITelephony itelephony;
        try
        {
            itelephony = getITelephony();
        }
        catch(RemoteException remoteexception)
        {
            return -1;
        }
        catch(NullPointerException nullpointerexception)
        {
            return -1;
        }
        if(itelephony == null)
            return -1;
        i = itelephony.getCdmaEriIconModeForSubscriber(i, getOpPackageName());
        return i;
    }

    public String getCdmaEriText()
    {
        return getCdmaEriText(getSubId());
    }

    public String getCdmaEriText(int i)
    {
        Object obj;
        try
        {
            obj = getITelephony();
        }
        catch(RemoteException remoteexception)
        {
            return null;
        }
        catch(NullPointerException nullpointerexception)
        {
            return null;
        }
        if(obj == null)
            return null;
        obj = ((ITelephony) (obj)).getCdmaEriTextForSubscriber(i, getOpPackageName());
        return ((String) (obj));
    }

    public String getCdmaMdn()
    {
        return getCdmaMdn(getSubId());
    }

    public String getCdmaMdn(int i)
    {
        Object obj;
        try
        {
            obj = getITelephony();
        }
        catch(RemoteException remoteexception)
        {
            return null;
        }
        catch(NullPointerException nullpointerexception)
        {
            return null;
        }
        if(obj == null)
            return null;
        obj = ((ITelephony) (obj)).getCdmaMdn(i);
        return ((String) (obj));
    }

    public String getCdmaMin()
    {
        return getCdmaMin(getSubId());
    }

    public String getCdmaMin(int i)
    {
        Object obj;
        try
        {
            obj = getITelephony();
        }
        catch(RemoteException remoteexception)
        {
            return null;
        }
        catch(NullPointerException nullpointerexception)
        {
            return null;
        }
        if(obj == null)
            return null;
        obj = ((ITelephony) (obj)).getCdmaMin(i);
        return ((String) (obj));
    }

    public String getCdmaPrlVersion()
    {
        return getCdmaPrlVersion(getSubId());
    }

    public String getCdmaPrlVersion(int i)
    {
        Object obj = getITelephony();
        if(obj == null)
            break MISSING_BLOCK_LABEL_30;
        obj = ((ITelephony) (obj)).getCdmaPrlVersion(i);
        return ((String) (obj));
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelephonyManager", "Error calling ITelephony#getCdmaPrlVersion", remoteexception);
        return null;
    }

    public CellLocation getCellLocation()
    {
        SeempLog.record(49);
        Object obj;
        try
        {
            obj = getITelephony();
        }
        catch(RemoteException remoteexception)
        {
            Rlog.d("TelephonyManager", (new StringBuilder()).append("getCellLocation returning null due to RemoteException ").append(remoteexception).toString());
            return null;
        }
        catch(NullPointerException nullpointerexception)
        {
            Rlog.d("TelephonyManager", (new StringBuilder()).append("getCellLocation returning null due to NullPointerException ").append(nullpointerexception).toString());
            return null;
        }
        if(obj != null)
            break MISSING_BLOCK_LABEL_26;
        Rlog.d("TelephonyManager", "getCellLocation returning null because telephony is null");
        return null;
        obj = ((ITelephony) (obj)).getCellLocation(mContext.getOpPackageName());
        if(obj == null)
            break MISSING_BLOCK_LABEL_51;
        if(!((Bundle) (obj)).isEmpty())
            break MISSING_BLOCK_LABEL_62;
        Rlog.d("TelephonyManager", "getCellLocation returning null because bundle is empty");
        return null;
        obj = CellLocation.newFromBundle(((Bundle) (obj)));
        if(!((CellLocation) (obj)).isEmpty())
            break MISSING_BLOCK_LABEL_85;
        Rlog.d("TelephonyManager", "getCellLocation returning null because CellLocation is empty");
        return null;
        return ((CellLocation) (obj));
    }

    public CellNetworkScanResult getCellNetworkScanResults(int i)
    {
        Object obj = getITelephony();
        if(obj == null)
            break MISSING_BLOCK_LABEL_30;
        obj = ((ITelephony) (obj)).getCellNetworkScanResults(i);
        return ((CellNetworkScanResult) (obj));
        Object obj1;
        obj1;
        Rlog.e("TelephonyManager", "getCellNetworkScanResults NPE", ((Throwable) (obj1)));
_L2:
        return null;
        obj1;
        Rlog.e("TelephonyManager", "getCellNetworkScanResults RemoteException", ((Throwable) (obj1)));
        if(true) goto _L2; else goto _L1
_L1:
    }

    public List getClientRequestStats(int i)
    {
        Object obj = getITelephony();
        if(obj == null)
            break MISSING_BLOCK_LABEL_34;
        obj = ((ITelephony) (obj)).getClientRequestStats(getOpPackageName(), i);
        return ((List) (obj));
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelephonyManager", "Error calling ITelephony#getClientRequestStats", remoteexception);
        return null;
    }

    public String getCompleteVoiceMailNumber()
    {
        return getCompleteVoiceMailNumber(getSubId());
    }

    public String getCompleteVoiceMailNumber(int i)
    {
        Object obj;
        try
        {
            obj = getSubscriberInfo();
        }
        catch(RemoteException remoteexception)
        {
            return null;
        }
        catch(NullPointerException nullpointerexception)
        {
            return null;
        }
        if(obj == null)
            return null;
        obj = ((IPhoneSubInfo) (obj)).getCompleteVoiceMailNumberForSubscriber(i);
        return ((String) (obj));
    }

    public int getCurrentPhoneType()
    {
        return getCurrentPhoneType(getSubId());
    }

    public int getCurrentPhoneType(int i)
    {
        if(i == -1)
            i = 0;
        else
            i = SubscriptionManager.getPhoneId(i);
        return getCurrentPhoneTypeForSlot(i);
    }

    public int getCurrentPhoneTypeForSlot(int i)
    {
        ITelephony itelephony;
        int j;
        try
        {
            itelephony = getITelephony();
        }
        catch(RemoteException remoteexception)
        {
            return getPhoneTypeFromProperty(i);
        }
        catch(NullPointerException nullpointerexception)
        {
            return getPhoneTypeFromProperty(i);
        }
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_17;
        return itelephony.getActivePhoneTypeForSlot(i);
        j = getPhoneTypeFromProperty(i);
        return j;
    }

    public int getDataActivationState(int i)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_24;
        i = itelephony.getDataActivationState(i, getOpPackageName());
        return i;
        Object obj;
        obj;
_L2:
        return 0;
        obj;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public int getDataActivity()
    {
        ITelephony itelephony;
        int i;
        try
        {
            itelephony = getITelephony();
        }
        catch(RemoteException remoteexception)
        {
            return 0;
        }
        catch(NullPointerException nullpointerexception)
        {
            return 0;
        }
        if(itelephony == null)
            return 0;
        i = itelephony.getDataActivity();
        return i;
    }

    public boolean getDataEnabled()
    {
        return isDataEnabled();
    }

    public boolean getDataEnabled(int i)
    {
        boolean flag = false;
        ITelephony itelephony = getITelephony();
        boolean flag1 = flag;
        if(itelephony != null)
            try
            {
                flag1 = itelephony.getDataEnabled(i);
            }
            catch(RemoteException remoteexception)
            {
                Log.e("TelephonyManager", "Error calling ITelephony#getDataEnabled", remoteexception);
                flag1 = flag;
            }
            catch(NullPointerException nullpointerexception)
            {
                flag1 = flag;
            }
        return flag1;
    }

    public int getDataNetworkType()
    {
        return getDataNetworkType(getDefaultDataSubscriptionId());
    }

    public int getDataNetworkType(int i)
    {
        ITelephony itelephony;
        try
        {
            itelephony = getITelephony();
        }
        catch(RemoteException remoteexception)
        {
            return 0;
        }
        catch(NullPointerException nullpointerexception)
        {
            return 0;
        }
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_23;
        i = itelephony.getDataNetworkTypeForSubscriber(i, getOpPackageName());
        return i;
        return 0;
    }

    public int getDataState()
    {
        ITelephony itelephony;
        int i;
        try
        {
            itelephony = getITelephony();
        }
        catch(RemoteException remoteexception)
        {
            return 0;
        }
        catch(NullPointerException nullpointerexception)
        {
            return 0;
        }
        if(itelephony == null)
            return 0;
        i = itelephony.getDataState();
        return i;
    }

    public String getDeviceId()
    {
        Object obj;
        try
        {
            obj = getITelephony();
        }
        catch(RemoteException remoteexception)
        {
            return null;
        }
        catch(NullPointerException nullpointerexception)
        {
            return null;
        }
        if(obj == null)
            return null;
        obj = ((ITelephony) (obj)).getDeviceId(mContext.getOpPackageName());
        return ((String) (obj));
    }

    public String getDeviceId(int i)
    {
        SeempLog.record_str(8, (new StringBuilder()).append("").append(i).toString());
        Object obj;
        try
        {
            obj = getSubscriberInfo();
        }
        catch(RemoteException remoteexception)
        {
            return null;
        }
        catch(NullPointerException nullpointerexception)
        {
            return null;
        }
        if(obj == null)
            return null;
        obj = ((IPhoneSubInfo) (obj)).getDeviceIdForPhone(i, mContext.getOpPackageName());
        return ((String) (obj));
    }

    public String getDeviceSoftwareVersion()
    {
        return getDeviceSoftwareVersion(getSlotIndex());
    }

    public String getDeviceSoftwareVersion(int i)
    {
        Object obj = getITelephony();
        if(obj == null)
            return null;
        try
        {
            obj = ((ITelephony) (obj)).getDeviceSoftwareVersionForSlot(i, getOpPackageName());
        }
        catch(RemoteException remoteexception)
        {
            return null;
        }
        catch(NullPointerException nullpointerexception)
        {
            return null;
        }
        return ((String) (obj));
    }

    public boolean getEmergencyCallbackMode()
    {
        return getEmergencyCallbackMode(getSubId());
    }

    public boolean getEmergencyCallbackMode(int i)
    {
        ITelephony itelephony;
        boolean flag;
        try
        {
            itelephony = getITelephony();
        }
        catch(RemoteException remoteexception)
        {
            Log.e("TelephonyManager", "Error calling ITelephony#getEmergencyCallbackMode", remoteexception);
            return false;
        }
        if(itelephony == null)
            return false;
        flag = itelephony.getEmergencyCallbackMode(i);
        return flag;
    }

    public String getEsn()
    {
        return getEsn(getSubId());
    }

    public String getEsn(int i)
    {
        Object obj = getITelephony();
        if(obj == null)
            break MISSING_BLOCK_LABEL_30;
        obj = ((ITelephony) (obj)).getEsn(i);
        return ((String) (obj));
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelephonyManager", "Error calling ITelephony#getEsn", remoteexception);
        return null;
    }

    public String[] getForbiddenPlmns()
    {
        return getForbiddenPlmns(getSubId(), 2);
    }

    public String[] getForbiddenPlmns(int i, int j)
    {
        ITelephony itelephony;
        String as[];
        try
        {
            itelephony = getITelephony();
        }
        catch(RemoteException remoteexception)
        {
            return null;
        }
        catch(NullPointerException nullpointerexception)
        {
            return null;
        }
        if(itelephony == null)
            return null;
        as = itelephony.getForbiddenPlmns(i, j);
        return as;
    }

    public String getGroupIdLevel1()
    {
        Object obj;
        try
        {
            obj = getSubscriberInfo();
        }
        catch(RemoteException remoteexception)
        {
            return null;
        }
        catch(NullPointerException nullpointerexception)
        {
            return null;
        }
        if(obj == null)
            return null;
        obj = ((IPhoneSubInfo) (obj)).getGroupIdLevel1(mContext.getOpPackageName());
        return ((String) (obj));
    }

    public String getGroupIdLevel1(int i)
    {
        Object obj;
        try
        {
            obj = getSubscriberInfo();
        }
        catch(RemoteException remoteexception)
        {
            return null;
        }
        catch(NullPointerException nullpointerexception)
        {
            return null;
        }
        if(obj == null)
            return null;
        obj = ((IPhoneSubInfo) (obj)).getGroupIdLevel1ForSubscriber(i, mContext.getOpPackageName());
        return ((String) (obj));
    }

    public String getIccAuthentication(int i, int j, int k, String s)
    {
        IPhoneSubInfo iphonesubinfo;
        try
        {
            iphonesubinfo = getSubscriberInfo();
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return null;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return null;
        }
        if(iphonesubinfo == null)
            return null;
        s = iphonesubinfo.getIccSimChallengeResponse(i, j, k, s);
        return s;
    }

    public String getIccAuthentication(int i, int j, String s)
    {
        return getIccAuthentication(getSubId(), i, j, s);
    }

    public String getImei()
    {
        return getImei(getSlotIndex());
    }

    public String getImei(int i)
    {
        Object obj = getITelephony();
        if(obj == null)
            return null;
        try
        {
            obj = ((ITelephony) (obj)).getImeiForSlot(i, getOpPackageName());
        }
        catch(RemoteException remoteexception)
        {
            return null;
        }
        catch(NullPointerException nullpointerexception)
        {
            return null;
        }
        return ((String) (obj));
    }

    public IImsServiceController getImsServiceControllerAndListen(int i, int j, IImsServiceFeatureListener iimsservicefeaturelistener)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_54;
        iimsservicefeaturelistener = itelephony.getImsServiceControllerAndListen(i, j, iimsservicefeaturelistener);
        return iimsservicefeaturelistener;
        iimsservicefeaturelistener;
        Rlog.e("TelephonyManager", (new StringBuilder()).append("getImsServiceControllerAndListen, RemoteException: ").append(iimsservicefeaturelistener.getMessage()).toString());
        return null;
    }

    public String getIsimChallengeResponse(String s)
    {
        IPhoneSubInfo iphonesubinfo;
        try
        {
            iphonesubinfo = getSubscriberInfo();
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return null;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return null;
        }
        if(iphonesubinfo == null)
            return null;
        s = iphonesubinfo.getIsimChallengeResponse(s);
        return s;
    }

    public String getIsimDomain()
    {
        Object obj;
        try
        {
            obj = getSubscriberInfo();
        }
        catch(RemoteException remoteexception)
        {
            return null;
        }
        catch(NullPointerException nullpointerexception)
        {
            return null;
        }
        if(obj == null)
            return null;
        obj = ((IPhoneSubInfo) (obj)).getIsimDomain();
        return ((String) (obj));
    }

    public String getIsimImpi()
    {
        Object obj;
        try
        {
            obj = getSubscriberInfo();
        }
        catch(RemoteException remoteexception)
        {
            return null;
        }
        catch(NullPointerException nullpointerexception)
        {
            return null;
        }
        if(obj == null)
            return null;
        obj = ((IPhoneSubInfo) (obj)).getIsimImpi();
        return ((String) (obj));
    }

    public String[] getIsimImpu()
    {
        IPhoneSubInfo iphonesubinfo;
        String as[];
        try
        {
            iphonesubinfo = getSubscriberInfo();
        }
        catch(RemoteException remoteexception)
        {
            return null;
        }
        catch(NullPointerException nullpointerexception)
        {
            return null;
        }
        if(iphonesubinfo == null)
            return null;
        as = iphonesubinfo.getIsimImpu();
        return as;
    }

    public String getIsimIst()
    {
        Object obj;
        try
        {
            obj = getSubscriberInfo();
        }
        catch(RemoteException remoteexception)
        {
            return null;
        }
        catch(NullPointerException nullpointerexception)
        {
            return null;
        }
        if(obj == null)
            return null;
        obj = ((IPhoneSubInfo) (obj)).getIsimIst();
        return ((String) (obj));
    }

    public String[] getIsimPcscf()
    {
        IPhoneSubInfo iphonesubinfo;
        String as[];
        try
        {
            iphonesubinfo = getSubscriberInfo();
        }
        catch(RemoteException remoteexception)
        {
            return null;
        }
        catch(NullPointerException nullpointerexception)
        {
            return null;
        }
        if(iphonesubinfo == null)
            return null;
        as = iphonesubinfo.getIsimPcscf();
        return as;
    }

    public String getLine1AlphaTag()
    {
        return getLine1AlphaTag(getSubId());
    }

    public String getLine1AlphaTag(int i)
    {
        Object obj = null;
        ITelephony itelephony = getITelephony();
        Object obj1 = obj;
        if(itelephony != null)
            try
            {
                obj1 = itelephony.getLine1AlphaTagForDisplay(i, getOpPackageName());
            }
            catch(RemoteException remoteexception)
            {
                remoteexception = obj;
            }
            catch(NullPointerException nullpointerexception)
            {
                nullpointerexception = obj;
            }
        if(obj1 != null)
            return ((String) (obj1));
        try
        {
            obj1 = getSubscriberInfo();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj1)
        {
            return null;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj1)
        {
            return null;
        }
        if(obj1 == null)
            return null;
        obj1 = ((IPhoneSubInfo) (obj1)).getLine1AlphaTagForSubscriber(i, getOpPackageName());
        return ((String) (obj1));
    }

    public String getLine1Number()
    {
        return getLine1Number(getSubId());
    }

    public String getLine1Number(int i)
    {
        Object obj;
        SeempLog.record_str(9, (new StringBuilder()).append("").append(i).toString());
        obj = null;
        ITelephony itelephony = getITelephony();
        Object obj1 = obj;
        if(itelephony != null)
            try
            {
                obj1 = itelephony.getLine1NumberForDisplay(i, mContext.getOpPackageName());
            }
            catch(RemoteException remoteexception)
            {
                remoteexception = obj;
            }
            catch(NullPointerException nullpointerexception)
            {
                nullpointerexception = obj;
            }
        if(obj1 != null)
            return ((String) (obj1));
        try
        {
            obj1 = getSubscriberInfo();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj1)
        {
            return null;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj1)
        {
            return null;
        }
        if(obj1 == null)
            return null;
        obj1 = ((IPhoneSubInfo) (obj1)).getLine1NumberForSubscriber(i, mContext.getOpPackageName());
        return ((String) (obj1));
    }

    public String getLocaleFromDefaultSim()
    {
        Object obj = getITelephony();
        if(obj == null)
            break MISSING_BLOCK_LABEL_19;
        obj = ((ITelephony) (obj)).getLocaleFromDefaultSim();
        return ((String) (obj));
        RemoteException remoteexception;
        remoteexception;
        return null;
    }

    public int getLteOnCdmaMode()
    {
        return getLteOnCdmaMode(getSubId());
    }

    public int getLteOnCdmaMode(int i)
    {
        ITelephony itelephony;
        try
        {
            itelephony = getITelephony();
        }
        catch(RemoteException remoteexception)
        {
            return -1;
        }
        catch(NullPointerException nullpointerexception)
        {
            return -1;
        }
        if(itelephony == null)
            return -1;
        i = itelephony.getLteOnCdmaModeForSubscriber(i, getOpPackageName());
        return i;
    }

    public String getMeid()
    {
        return getMeid(getSlotIndex());
    }

    public String getMeid(int i)
    {
        Object obj = getITelephony();
        if(obj == null)
            return null;
        try
        {
            obj = ((ITelephony) (obj)).getMeidForSlot(i, getOpPackageName());
        }
        catch(RemoteException remoteexception)
        {
            return null;
        }
        catch(NullPointerException nullpointerexception)
        {
            return null;
        }
        return ((String) (obj));
    }

    public String[] getMergedSubscriberIds()
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_23;
        String as[] = itelephony.getMergedSubscriberIds(getOpPackageName());
        return as;
        Object obj;
        obj;
_L2:
        return null;
        obj;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public String getMmsUAProfUrl()
    {
        if(mContext == null)
            return null;
        else
            return mContext.getResources().getString(0x1040150);
    }

    public String getMmsUserAgent()
    {
        if(mContext == null)
            return null;
        else
            return mContext.getResources().getString(0x104014f);
    }

    public String getMsisdn()
    {
        return getMsisdn(getSubId());
    }

    public String getMsisdn(int i)
    {
        Object obj;
        try
        {
            obj = getSubscriberInfo();
        }
        catch(RemoteException remoteexception)
        {
            return null;
        }
        catch(NullPointerException nullpointerexception)
        {
            return null;
        }
        if(obj == null)
            return null;
        obj = ((IPhoneSubInfo) (obj)).getMsisdnForSubscriber(i, getOpPackageName());
        return ((String) (obj));
    }

    public MultiSimVariants getMultiSimConfiguration()
    {
        String s = SystemProperties.get("persist.radio.multisim.config");
        if(s.equals("dsds"))
            return MultiSimVariants.DSDS;
        if(s.equals("dsda"))
            return MultiSimVariants.DSDA;
        if(s.equals("tsts"))
            return MultiSimVariants.TSTS;
        else
            return MultiSimVariants.UNKNOWN;
    }

    public String getNai()
    {
        return getNai(getSlotIndex());
    }

    public String getNai(int i)
    {
        int ai[] = SubscriptionManager.getSubId(i);
        StringBuilder stringbuilder;
        Object obj;
        try
        {
            obj = getSubscriberInfo();
        }
        catch(RemoteException remoteexception)
        {
            return null;
        }
        catch(NullPointerException nullpointerexception)
        {
            return null;
        }
        if(obj == null)
            return null;
        obj = ((IPhoneSubInfo) (obj)).getNaiForSubscriber(ai[0], mContext.getOpPackageName());
        if(Log.isLoggable("TelephonyManager", 2))
        {
            stringbuilder = JVM INSTR new #456 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Rlog.v("TelephonyManager", stringbuilder.append("Nai = ").append(((String) (obj))).toString());
        }
        return ((String) (obj));
    }

    public List getNeighboringCellInfo()
    {
        SeempLog.record(50);
        Object obj;
        try
        {
            obj = getITelephony();
        }
        catch(RemoteException remoteexception)
        {
            return null;
        }
        catch(NullPointerException nullpointerexception)
        {
            return null;
        }
        if(obj == null)
            return null;
        obj = ((ITelephony) (obj)).getNeighboringCellInfo(mContext.getOpPackageName());
        return ((List) (obj));
    }

    public String getNetworkCountryIso()
    {
        return getNetworkCountryIsoForPhone(getPhoneId());
    }

    public String getNetworkCountryIso(int i)
    {
        return getNetworkCountryIsoForPhone(getPhoneId(i));
    }

    public String getNetworkCountryIsoForPhone(int i)
    {
        Object obj;
        try
        {
            obj = getITelephony();
        }
        catch(RemoteException remoteexception)
        {
            return "";
        }
        if(obj == null)
            return "";
        obj = ((ITelephony) (obj)).getNetworkCountryIsoForPhone(i);
        return ((String) (obj));
    }

    public String getNetworkOperator()
    {
        return getNetworkOperatorForPhone(getPhoneId());
    }

    public String getNetworkOperator(int i)
    {
        return getNetworkOperatorForPhone(SubscriptionManager.getPhoneId(i));
    }

    public String getNetworkOperatorForPhone(int i)
    {
        return getTelephonyProperty(i, "gsm.operator.numeric", "");
    }

    public String getNetworkOperatorName()
    {
        return getNetworkOperatorName(getSubId());
    }

    public String getNetworkOperatorName(int i)
    {
        return getTelephonyProperty(SubscriptionManager.getPhoneId(i), "gsm.operator.alpha", "");
    }

    public String getNetworkSpecifier()
    {
        return String.valueOf(getSubId());
    }

    public int getNetworkType()
    {
        ITelephony itelephony;
        int i;
        try
        {
            itelephony = getITelephony();
        }
        catch(RemoteException remoteexception)
        {
            return 0;
        }
        catch(NullPointerException nullpointerexception)
        {
            return 0;
        }
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_18;
        i = itelephony.getNetworkType();
        return i;
        return 0;
    }

    public int getNetworkType(int i)
    {
        ITelephony itelephony;
        try
        {
            itelephony = getITelephony();
        }
        catch(RemoteException remoteexception)
        {
            return 0;
        }
        catch(NullPointerException nullpointerexception)
        {
            return 0;
        }
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_23;
        i = itelephony.getNetworkTypeForSubscriber(i, getOpPackageName());
        return i;
        return 0;
    }

    public String getNetworkTypeName()
    {
        return getNetworkTypeName(getNetworkType());
    }

    public String getOtaSpNumberSchema(String s)
    {
        return getOtaSpNumberSchemaForPhone(getPhoneId(), s);
    }

    public String getOtaSpNumberSchemaForPhone(int i, String s)
    {
        if(SubscriptionManager.isValidPhoneId(i))
            return getTelephonyProperty(i, "ro.cdma.otaspnumschema", s);
        else
            return s;
    }

    public List getPackagesWithCarrierPrivileges()
    {
        Object obj = getITelephony();
        if(obj == null)
            break MISSING_BLOCK_LABEL_29;
        obj = ((ITelephony) (obj)).getPackagesWithCarrierPrivileges();
        return ((List) (obj));
        Object obj1;
        obj1;
        Rlog.e("TelephonyManager", "getPackagesWithCarrierPrivileges NPE", ((Throwable) (obj1)));
_L2:
        return Collections.EMPTY_LIST;
        obj1;
        Rlog.e("TelephonyManager", "getPackagesWithCarrierPrivileges RemoteException", ((Throwable) (obj1)));
        if(true) goto _L2; else goto _L1
_L1:
    }

    public String[] getPcscfAddress(String s)
    {
        ITelephony itelephony;
        try
        {
            itelephony = getITelephony();
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return new String[0];
        }
        if(itelephony != null)
            break MISSING_BLOCK_LABEL_14;
        return new String[0];
        s = itelephony.getPcscfAddress(s, getOpPackageName());
        return s;
    }

    public int getPhoneCount()
    {
        int i = 1;
        _2D_getandroid_2D_telephony_2D_TelephonyManager$MultiSimVariantsSwitchesValues()[getMultiSimConfiguration().ordinal()];
        JVM INSTR tableswitch 1 4: default 44
    //                   1 126
    //                   2 126
    //                   3 131
    //                   4 46;
           goto _L1 _L2 _L2 _L3 _L4
_L1:
        return i;
_L4:
        if(sIsNorilDevice)
            return 1;
        if(isVoiceCapable() || isSmsCapable())
            i = 1;
        else
        if(mContext == null)
        {
            i = 1;
        } else
        {
            ConnectivityManager connectivitymanager = (ConnectivityManager)mContext.getSystemService("connectivity");
            if(connectivitymanager == null)
                i = 1;
            else
            if(connectivitymanager.isNetworkSupported(0))
                i = 1;
            else
                i = 0;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        i = 2;
        continue; /* Loop/switch isn't completed */
_L3:
        i = 3;
        if(true) goto _L1; else goto _L5
_L5:
    }

    public int getPhoneType()
    {
        if(!isVoiceCapable())
            return 0;
        else
            return getCurrentPhoneType();
    }

    public int getPreferredNetworkType(int i)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_30;
        i = itelephony.getPreferredNetworkType(i);
        return i;
        Object obj;
        obj;
        Rlog.e("TelephonyManager", "getPreferredNetworkType NPE", ((Throwable) (obj)));
_L2:
        return -1;
        obj;
        Rlog.e("TelephonyManager", "getPreferredNetworkType RemoteException", ((Throwable) (obj)));
        if(true) goto _L2; else goto _L1
_L1:
    }

    public ServiceState getServiceState()
    {
        return getServiceStateForSubscriber(getSubId());
    }

    public ServiceState getServiceStateForSubscriber(int i)
    {
        Object obj = getITelephony();
        if(obj == null)
            break MISSING_BLOCK_LABEL_34;
        obj = ((ITelephony) (obj)).getServiceStateForSubscriber(i, getOpPackageName());
        return ((ServiceState) (obj));
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelephonyManager", "Error calling ITelephony#getServiceStateForSubscriber", remoteexception);
        return null;
    }

    public SignalStrength getSignalStrength()
    {
        Object obj = getITelephony();
        if(obj == null)
            break MISSING_BLOCK_LABEL_33;
        obj = ((ITelephony) (obj)).getSignalStrength(getSubId());
        return ((SignalStrength) (obj));
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelephonyManager", "Error calling ITelephony#getSignalStrength", remoteexception);
        return null;
    }

    public int getSimCount()
    {
        return !isMultiSimEnabled() ? 1 : 2;
    }

    public String getSimCountryIso()
    {
        return getSimCountryIsoForPhone(getPhoneId());
    }

    public String getSimCountryIso(int i)
    {
        return getSimCountryIsoForPhone(SubscriptionManager.getPhoneId(i));
    }

    public String getSimCountryIsoForPhone(int i)
    {
        return getTelephonyProperty(i, "gsm.sim.operator.iso-country", "");
    }

    public String getSimOperator()
    {
        return getSimOperatorNumeric();
    }

    public String getSimOperator(int i)
    {
        return getSimOperatorNumeric(i);
    }

    public String getSimOperatorName()
    {
        return getSimOperatorNameForPhone(getPhoneId());
    }

    public String getSimOperatorName(int i)
    {
        return getSimOperatorNameForPhone(SubscriptionManager.getPhoneId(i));
    }

    public String getSimOperatorNameForPhone(int i)
    {
        return getTelephonyProperty(i, "gsm.sim.operator.alpha", "");
    }

    public String getSimOperatorNumeric()
    {
        int i = mSubId;
        int i1 = i;
        if(!SubscriptionManager.isUsableSubIdValue(i))
        {
            int j = SubscriptionManager.getDefaultDataSubscriptionId();
            i1 = j;
            if(!SubscriptionManager.isUsableSubIdValue(j))
            {
                int k = SubscriptionManager.getDefaultSmsSubscriptionId();
                i1 = k;
                if(!SubscriptionManager.isUsableSubIdValue(k))
                {
                    int l = SubscriptionManager.getDefaultVoiceSubscriptionId();
                    i1 = l;
                    if(!SubscriptionManager.isUsableSubIdValue(l))
                        i1 = SubscriptionManager.getDefaultSubscriptionId();
                }
            }
        }
        return getSimOperatorNumeric(i1);
    }

    public String getSimOperatorNumeric(int i)
    {
        return getSimOperatorNumericForPhone(SubscriptionManager.getPhoneId(i));
    }

    public String getSimOperatorNumericForPhone(int i)
    {
        return getTelephonyProperty(i, "gsm.sim.operator.numeric", "");
    }

    public String getSimSerialNumber()
    {
        return getSimSerialNumber(getSubId());
    }

    public String getSimSerialNumber(int i)
    {
        SeempLog.record_str(388, (new StringBuilder()).append("").append(i).toString());
        Object obj;
        try
        {
            obj = getSubscriberInfo();
        }
        catch(RemoteException remoteexception)
        {
            return null;
        }
        catch(NullPointerException nullpointerexception)
        {
            return null;
        }
        if(obj == null)
            return null;
        obj = ((IPhoneSubInfo) (obj)).getIccSerialNumberForSubscriber(i, mContext.getOpPackageName());
        return ((String) (obj));
    }

    public int getSimState()
    {
        int i = getSlotIndex();
        if(i < 0)
        {
            for(int j = 0; j < getPhoneCount(); j++)
            {
                int k = getSimState(j);
                if(k != 1)
                {
                    Rlog.d("TelephonyManager", (new StringBuilder()).append("getSimState: default sim:").append(i).append(", sim state for ").append("slotIndex=").append(j).append(" is ").append(k).append(", return state as unknown").toString());
                    return 0;
                }
            }

            Rlog.d("TelephonyManager", (new StringBuilder()).append("getSimState: default sim:").append(i).append(", all SIMs absent, return ").append("state as absent").toString());
            return 1;
        } else
        {
            return getSimState(i);
        }
    }

    public int getSimState(int i)
    {
        return SubscriptionManager.getSimStateForSlotIndex(i);
    }

    public int getSlotIndex()
    {
        int i = SubscriptionManager.getSlotIndex(getSubId());
        int j = i;
        if(i == -1)
            j = 0x7fffffff;
        return j;
    }

    public boolean getSmsReceiveCapable(boolean flag)
    {
        return getSmsReceiveCapableForPhone(getPhoneId(), flag);
    }

    public boolean getSmsReceiveCapableForPhone(int i, boolean flag)
    {
        if(sIsNorilDevice)
            return false;
        if(SubscriptionManager.isValidPhoneId(i))
            return Boolean.parseBoolean(getTelephonyProperty(i, "telephony.sms.receive", String.valueOf(flag)));
        else
            return flag;
    }

    public boolean getSmsSendCapable(boolean flag)
    {
        return getSmsSendCapableForPhone(getPhoneId(), flag);
    }

    public boolean getSmsSendCapableForPhone(int i, boolean flag)
    {
        if(sIsNorilDevice)
            return false;
        if(SubscriptionManager.isValidPhoneId(i))
            return Boolean.parseBoolean(getTelephonyProperty(i, "telephony.sms.send", String.valueOf(flag)));
        else
            return flag;
    }

    public int getSubIdForPhoneAccount(PhoneAccount phoneaccount)
    {
        byte byte0 = -1;
        ITelephony itelephony = getITelephony();
        int i = byte0;
        if(itelephony != null)
            try
            {
                i = itelephony.getSubIdForPhoneAccount(phoneaccount);
            }
            // Misplaced declaration of an exception variable
            catch(PhoneAccount phoneaccount)
            {
                i = byte0;
            }
        return i;
    }

    public String getSubscriberId()
    {
        return getSubscriberId(getSubId());
    }

    public String getSubscriberId(int i)
    {
        SeempLog.record_str(389, (new StringBuilder()).append("").append(i).toString());
        Object obj;
        try
        {
            obj = getSubscriberInfo();
        }
        catch(RemoteException remoteexception)
        {
            return null;
        }
        catch(NullPointerException nullpointerexception)
        {
            return null;
        }
        if(obj == null)
            return null;
        obj = ((IPhoneSubInfo) (obj)).getSubscriberIdForSubscriber(i, mContext.getOpPackageName());
        return ((String) (obj));
    }

    public List getTelephonyHistograms()
    {
        Object obj = getITelephony();
        if(obj == null)
            break MISSING_BLOCK_LABEL_29;
        obj = ((ITelephony) (obj)).getTelephonyHistograms();
        return ((List) (obj));
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelephonyManager", "Error calling ITelephony#getTelephonyHistograms", remoteexception);
        return null;
    }

    public int getTetherApnRequired()
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_29;
        int i = itelephony.getTetherApnRequired();
        return i;
        Object obj;
        obj;
        Rlog.e("TelephonyManager", "hasMatchedTetherApnSetting NPE", ((Throwable) (obj)));
_L2:
        return 2;
        obj;
        Rlog.e("TelephonyManager", "hasMatchedTetherApnSetting RemoteException", ((Throwable) (obj)));
        if(true) goto _L2; else goto _L1
_L1:
    }

    public String getVisualVoicemailPackageName()
    {
        Object obj = getITelephony();
        if(obj == null)
            break MISSING_BLOCK_LABEL_30;
        obj = ((ITelephony) (obj)).getVisualVoicemailPackageName(mContext.getOpPackageName(), getSubId());
        return ((String) (obj));
        Object obj1;
        obj1;
_L2:
        return null;
        obj1;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public Bundle getVisualVoicemailSettings()
    {
        Object obj = getITelephony();
        if(obj == null)
            break MISSING_BLOCK_LABEL_30;
        obj = ((ITelephony) (obj)).getVisualVoicemailSettings(mContext.getOpPackageName(), mSubId);
        return ((Bundle) (obj));
        Object obj1;
        obj1;
_L2:
        return null;
        obj1;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public VisualVoicemailSmsFilterSettings getVisualVoicemailSmsFilterSettings(int i)
    {
        Object obj = getITelephony();
        if(obj == null)
            break MISSING_BLOCK_LABEL_27;
        obj = ((ITelephony) (obj)).getVisualVoicemailSmsFilterSettings(mContext.getOpPackageName(), i);
        return ((VisualVoicemailSmsFilterSettings) (obj));
        Object obj1;
        obj1;
_L2:
        return null;
        obj1;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public int getVoiceActivationState(int i)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_24;
        i = itelephony.getVoiceActivationState(i, getOpPackageName());
        return i;
        Object obj;
        obj;
_L2:
        return 0;
        obj;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public String getVoiceMailAlphaTag()
    {
        return getVoiceMailAlphaTag(getSubId());
    }

    public String getVoiceMailAlphaTag(int i)
    {
        Object obj;
        try
        {
            obj = getSubscriberInfo();
        }
        catch(RemoteException remoteexception)
        {
            return null;
        }
        catch(NullPointerException nullpointerexception)
        {
            return null;
        }
        if(obj == null)
            return null;
        obj = ((IPhoneSubInfo) (obj)).getVoiceMailAlphaTagForSubscriber(i, getOpPackageName());
        return ((String) (obj));
    }

    public String getVoiceMailNumber()
    {
        return getVoiceMailNumber(getSubId());
    }

    public String getVoiceMailNumber(int i)
    {
        Object obj;
        try
        {
            obj = getSubscriberInfo();
        }
        catch(RemoteException remoteexception)
        {
            return null;
        }
        catch(NullPointerException nullpointerexception)
        {
            return null;
        }
        if(obj == null)
            return null;
        obj = ((IPhoneSubInfo) (obj)).getVoiceMailNumberForSubscriber(i, getOpPackageName());
        return ((String) (obj));
    }

    public int getVoiceMessageCount()
    {
        return getVoiceMessageCount(getSubId());
    }

    public int getVoiceMessageCount(int i)
    {
        ITelephony itelephony;
        try
        {
            itelephony = getITelephony();
        }
        catch(RemoteException remoteexception)
        {
            return 0;
        }
        catch(NullPointerException nullpointerexception)
        {
            return 0;
        }
        if(itelephony == null)
            return 0;
        i = itelephony.getVoiceMessageCountForSubscriber(i);
        return i;
    }

    public int getVoiceNetworkType()
    {
        return getVoiceNetworkType(getSubId());
    }

    public int getVoiceNetworkType(int i)
    {
        ITelephony itelephony;
        try
        {
            itelephony = getITelephony();
        }
        catch(RemoteException remoteexception)
        {
            return 0;
        }
        catch(NullPointerException nullpointerexception)
        {
            return 0;
        }
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_23;
        i = itelephony.getVoiceNetworkTypeForSubscriber(i, getOpPackageName());
        return i;
        return 0;
    }

    public Uri getVoicemailRingtoneUri(PhoneAccountHandle phoneaccounthandle)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_30;
        phoneaccounthandle = itelephony.getVoicemailRingtoneUri(phoneaccounthandle);
        return phoneaccounthandle;
        phoneaccounthandle;
        Log.e("TelephonyManager", "Error calling ITelephony#getVoicemailRingtoneUri", phoneaccounthandle);
        return null;
    }

    public NetworkStats getVtDataUsage(int i)
    {
        boolean flag;
        Object obj;
        if(i == 1)
            flag = true;
        else
            flag = false;
        obj = getITelephony();
        if(obj == null)
            break MISSING_BLOCK_LABEL_46;
        obj = ((ITelephony) (obj)).getVtDataUsage(getSubId(), flag);
        return ((NetworkStats) (obj));
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelephonyManager", "Error calling ITelephony#getVtDataUsage", remoteexception);
        return null;
    }

    public boolean handlePinMmi(String s)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_30;
        boolean flag = itelephony.handlePinMmi(s);
        return flag;
        s;
        Log.e("TelephonyManager", "Error calling ITelephony#handlePinMmi", s);
        return false;
    }

    public boolean handlePinMmiForSubscriber(int i, String s)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_33;
        boolean flag = itelephony.handlePinMmiForSubscriber(i, s);
        return flag;
        s;
        Log.e("TelephonyManager", "Error calling ITelephony#handlePinMmi", s);
        return false;
    }

    public boolean hasCarrierPrivileges()
    {
        return hasCarrierPrivileges(getSubId());
    }

    public boolean hasCarrierPrivileges(int i)
    {
        boolean flag = true;
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_45;
        i = itelephony.getCarrierPrivilegeStatus(mSubId);
        if(i != 1)
            flag = false;
        return flag;
        Object obj;
        obj;
        Rlog.e("TelephonyManager", "hasCarrierPrivileges NPE", ((Throwable) (obj)));
_L2:
        return false;
        obj;
        Rlog.e("TelephonyManager", "hasCarrierPrivileges RemoteException", ((Throwable) (obj)));
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean hasIccCard()
    {
        return hasIccCard(getSlotIndex());
    }

    public boolean hasIccCard(int i)
    {
        ITelephony itelephony;
        boolean flag;
        try
        {
            itelephony = getITelephony();
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        catch(NullPointerException nullpointerexception)
        {
            return false;
        }
        if(itelephony == null)
            return false;
        flag = itelephony.hasIccCardUsingSlotIndex(i);
        return flag;
    }

    public boolean iccCloseLogicalChannel(int i)
    {
        return iccCloseLogicalChannel(getSubId(), i);
    }

    public boolean iccCloseLogicalChannel(int i, int j)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_23;
        boolean flag = itelephony.iccCloseLogicalChannel(i, j);
        return flag;
        Object obj;
        obj;
_L2:
        return false;
        obj;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public byte[] iccExchangeSimIO(int i, int j, int k, int l, int i1, int j1, String s)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_36;
        s = itelephony.iccExchangeSimIO(i, j, k, l, i1, j1, s);
        return s;
        s;
_L2:
        return null;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public byte[] iccExchangeSimIO(int i, int j, int k, int l, int i1, String s)
    {
        return iccExchangeSimIO(getSubId(), i, j, k, l, i1, s);
    }

    public IccOpenLogicalChannelResponse iccOpenLogicalChannel(int i, String s, int j)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_29;
        s = itelephony.iccOpenLogicalChannel(i, getOpPackageName(), s, j);
        return s;
        s;
_L2:
        return null;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public IccOpenLogicalChannelResponse iccOpenLogicalChannel(String s)
    {
        return iccOpenLogicalChannel(getSubId(), s, -1);
    }

    public IccOpenLogicalChannelResponse iccOpenLogicalChannel(String s, int i)
    {
        return iccOpenLogicalChannel(getSubId(), s, i);
    }

    public String iccTransmitApduBasicChannel(int i, int j, int k, int l, int i1, int j1, String s)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_36;
        s = itelephony.iccTransmitApduBasicChannel(i, j, k, l, i1, j1, s);
        return s;
        s;
_L2:
        return "";
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public String iccTransmitApduBasicChannel(int i, int j, int k, int l, int i1, String s)
    {
        return iccTransmitApduBasicChannel(getSubId(), i, j, k, l, i1, s);
    }

    public String iccTransmitApduLogicalChannel(int i, int j, int k, int l, int i1, int j1, int k1, 
            String s)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_38;
        s = itelephony.iccTransmitApduLogicalChannel(i, j, k, l, i1, j1, k1, s);
        return s;
        s;
_L2:
        return "";
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public String iccTransmitApduLogicalChannel(int i, int j, int k, int l, int i1, int j1, String s)
    {
        return iccTransmitApduLogicalChannel(getSubId(), i, j, k, l, i1, j1, s);
    }

    public int invokeOemRilRequestRaw(byte abyte0[], byte abyte1[])
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_23;
        int i = itelephony.invokeOemRilRequestRaw(abyte0, abyte1);
        return i;
        abyte0;
_L2:
        return -1;
        abyte0;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean isConcurrentVoiceAndDataSupported()
    {
        boolean flag = false;
        ITelephony itelephony;
        try
        {
            itelephony = getITelephony();
        }
        catch(RemoteException remoteexception)
        {
            Log.e("TelephonyManager", "Error calling ITelephony#isConcurrentVoiceAndDataAllowed", remoteexception);
            return false;
        }
        if(itelephony != null) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        flag = itelephony.isConcurrentVoiceAndDataAllowed(getSubId());
        if(true) goto _L1; else goto _L3
_L3:
    }

    public boolean isDataConnectivityPossible()
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_36;
        boolean flag = itelephony.isDataConnectivityPossible(getSubId(SubscriptionManager.getDefaultDataSubscriptionId()));
        return flag;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelephonyManager", "Error calling ITelephony#isDataAllowed", remoteexception);
        return false;
    }

    public boolean isDataEnabled()
    {
        return getDataEnabled(getDefaultDataSubscriptionId());
    }

    public boolean isHearingAidCompatibilitySupported()
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_29;
        boolean flag = itelephony.isHearingAidCompatibilitySupported();
        return flag;
        Object obj;
        obj;
        Log.e("TelephonyManager", "Permission error calling ITelephony#isHearingAidCompatibilitySupported", ((Throwable) (obj)));
_L2:
        return false;
        obj;
        Log.e("TelephonyManager", "Error calling ITelephony#isHearingAidCompatibilitySupported", ((Throwable) (obj)));
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean isIdle()
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_33;
        boolean flag = itelephony.isIdle(getOpPackageName());
        return flag;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelephonyManager", "Error calling ITelephony#isIdle", remoteexception);
        return true;
    }

    public boolean isImsRegistered()
    {
        ITelephony itelephony;
        boolean flag;
        try
        {
            itelephony = getITelephony();
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        catch(NullPointerException nullpointerexception)
        {
            return false;
        }
        if(itelephony == null)
            return false;
        flag = itelephony.isImsRegistered();
        return flag;
    }

    public boolean isImsRegisteredForSubscriber(int i)
    {
        ITelephony itelephony;
        boolean flag;
        try
        {
            itelephony = getITelephony();
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        catch(NullPointerException nullpointerexception)
        {
            return false;
        }
        if(itelephony == null)
            return false;
        flag = itelephony.isImsRegisteredForSubscriber(i);
        return flag;
    }

    public boolean isMultiSimEnabled()
    {
        boolean flag;
        if(!multiSimConfig.equals("dsds") && !multiSimConfig.equals("dsda"))
            flag = multiSimConfig.equals("tsts");
        else
            flag = true;
        return flag;
    }

    public boolean isNetworkRoaming()
    {
        return isNetworkRoaming(getSubId());
    }

    public boolean isNetworkRoaming(int i)
    {
        return Boolean.parseBoolean(getTelephonyProperty(SubscriptionManager.getPhoneId(i), "gsm.operator.isroaming", null));
    }

    public boolean isOffhook()
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_33;
        boolean flag = itelephony.isOffhook(getOpPackageName());
        return flag;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelephonyManager", "Error calling ITelephony#isOffhook", remoteexception);
        return false;
    }

    public boolean isRadioOn()
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_33;
        boolean flag = itelephony.isRadioOn(getOpPackageName());
        return flag;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelephonyManager", "Error calling ITelephony#isRadioOn", remoteexception);
        return false;
    }

    public boolean isRinging()
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_33;
        boolean flag = itelephony.isRinging(getOpPackageName());
        return flag;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelephonyManager", "Error calling ITelephony#isRinging", remoteexception);
        return false;
    }

    public boolean isSmsCapable()
    {
        if(sIsNorilDevice)
            return false;
        if(mContext == null)
            return true;
        else
            return mContext.getResources().getBoolean(0x11200a0);
    }

    public boolean isTtyModeSupported()
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_29;
        boolean flag = itelephony.isTtyModeSupported();
        return flag;
        Object obj;
        obj;
        Log.e("TelephonyManager", "Permission error calling ITelephony#isTtyModeSupported", ((Throwable) (obj)));
_L2:
        return false;
        obj;
        Log.e("TelephonyManager", "Error calling ITelephony#isTtyModeSupported", ((Throwable) (obj)));
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean isVideoCallingEnabled()
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_33;
        boolean flag = itelephony.isVideoCallingEnabled(getOpPackageName());
        return flag;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelephonyManager", "Error calling ITelephony#isVideoCallingEnabled", remoteexception);
        return false;
    }

    public boolean isVideoTelephonyAvailable()
    {
        boolean flag;
        try
        {
            flag = getITelephony().isVideoTelephonyAvailable();
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        catch(NullPointerException nullpointerexception)
        {
            return false;
        }
        return flag;
    }

    public boolean isVisualVoicemailEnabled(PhoneAccountHandle phoneaccounthandle)
    {
        return false;
    }

    public boolean isVoiceCapable()
    {
        if(sIsNorilDevice)
            return false;
        if(mContext == null)
            return true;
        else
            return mContext.getResources().getBoolean(0x11200cb);
    }

    public boolean isVoicemailVibrationEnabled(PhoneAccountHandle phoneaccounthandle)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_30;
        boolean flag = itelephony.isVoicemailVibrationEnabled(phoneaccounthandle);
        return flag;
        phoneaccounthandle;
        Log.e("TelephonyManager", "Error calling ITelephony#isVoicemailVibrationEnabled", phoneaccounthandle);
        return false;
    }

    public boolean isVolteAvailable()
    {
        boolean flag;
        try
        {
            flag = getITelephony().isVolteAvailable();
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        catch(NullPointerException nullpointerexception)
        {
            return false;
        }
        return flag;
    }

    public boolean isWifiCallingAvailable()
    {
        boolean flag;
        try
        {
            flag = getITelephony().isWifiCallingAvailable();
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        catch(NullPointerException nullpointerexception)
        {
            return false;
        }
        return flag;
    }

    public boolean isWorldPhone()
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_29;
        boolean flag = itelephony.isWorldPhone();
        return flag;
        Object obj;
        obj;
        Log.e("TelephonyManager", "Permission error calling ITelephony#isWorldPhone", ((Throwable) (obj)));
_L2:
        return false;
        obj;
        Log.e("TelephonyManager", "Error calling ITelephony#isWorldPhone", ((Throwable) (obj)));
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void listen(PhoneStateListener phonestatelistener, int i)
    {
        if(mContext == null)
            return;
        boolean flag;
        ITelephonyRegistry itelephonyregistry;
        if(getITelephony() != null)
            flag = true;
        else
            flag = false;
        if(phonestatelistener.mSubId == null)
            phonestatelistener.mSubId = Integer.valueOf(mSubId);
        itelephonyregistry = getTelephonyRegistry();
        if(itelephonyregistry == null)
            break MISSING_BLOCK_LABEL_76;
        itelephonyregistry.listenForSubscriber(phonestatelistener.mSubId.intValue(), getOpPackageName(), phonestatelistener.callback, i, flag);
_L1:
        return;
        try
        {
            Rlog.w("TelephonyManager", "telephony registry not ready.");
        }
        // Misplaced declaration of an exception variable
        catch(PhoneStateListener phonestatelistener) { }
          goto _L1
    }

    public boolean needsOtaServiceProvisioning()
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_29;
        boolean flag = itelephony.needsOtaServiceProvisioning();
        return flag;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelephonyManager", "Error calling ITelephony#needsOtaServiceProvisioning", remoteexception);
        return false;
    }

    public String nvReadItem(int i)
    {
        Object obj = getITelephony();
        if(obj == null)
            break MISSING_BLOCK_LABEL_30;
        obj = ((ITelephony) (obj)).nvReadItem(i);
        return ((String) (obj));
        Object obj1;
        obj1;
        Rlog.e("TelephonyManager", "nvReadItem NPE", ((Throwable) (obj1)));
_L2:
        return "";
        obj1;
        Rlog.e("TelephonyManager", "nvReadItem RemoteException", ((Throwable) (obj1)));
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean nvResetConfig(int i)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_30;
        boolean flag = itelephony.nvResetConfig(i);
        return flag;
        Object obj;
        obj;
        Rlog.e("TelephonyManager", "nvResetConfig NPE", ((Throwable) (obj)));
_L2:
        return false;
        obj;
        Rlog.e("TelephonyManager", "nvResetConfig RemoteException", ((Throwable) (obj)));
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean nvWriteCdmaPrl(byte abyte0[])
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_30;
        boolean flag = itelephony.nvWriteCdmaPrl(abyte0);
        return flag;
        abyte0;
        Rlog.e("TelephonyManager", "nvWriteCdmaPrl NPE", abyte0);
_L2:
        return false;
        abyte0;
        Rlog.e("TelephonyManager", "nvWriteCdmaPrl RemoteException", abyte0);
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean nvWriteItem(int i, String s)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_33;
        boolean flag = itelephony.nvWriteItem(i, s);
        return flag;
        s;
        Rlog.e("TelephonyManager", "nvWriteItem NPE", s);
_L2:
        return false;
        s;
        Rlog.e("TelephonyManager", "nvWriteItem RemoteException", s);
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void requestModemActivityInfo(ResultReceiver resultreceiver)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony != null)
            try
            {
                itelephony.requestModemActivityInfo(resultreceiver);
                return;
            }
            catch(RemoteException remoteexception)
            {
                Log.e("TelephonyManager", "Error calling ITelephony#getModemActivityInfo", remoteexception);
            }
        resultreceiver.send(0, null);
        return;
    }

    public NetworkScan requestNetworkScan(NetworkScanRequest networkscanrequest, TelephonyScanManager.NetworkScanCallback networkscancallback)
    {
        this;
        JVM INSTR monitorenter ;
        if(mTelephonyScanManager == null)
        {
            TelephonyScanManager telephonyscanmanager = JVM INSTR new #1742 <Class TelephonyScanManager>;
            telephonyscanmanager.TelephonyScanManager();
            mTelephonyScanManager = telephonyscanmanager;
        }
        this;
        JVM INSTR monitorexit ;
        return mTelephonyScanManager.requestNetworkScan(getSubId(), networkscanrequest, networkscancallback);
        networkscanrequest;
        throw networkscanrequest;
    }

    public void sendDialerSpecialCode(String s)
    {
        getITelephony().sendDialerSpecialCode(mContext.getOpPackageName(), s);
_L1:
        return;
        s;
        throw new IllegalStateException("Telephony service unavailable");
        s;
        s.rethrowFromSystemServer();
          goto _L1
    }

    public String sendEnvelopeWithStatus(int i, String s)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_21;
        s = itelephony.sendEnvelopeWithStatus(i, s);
        return s;
        s;
_L2:
        return "";
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public String sendEnvelopeWithStatus(String s)
    {
        return sendEnvelopeWithStatus(getSubId(), s);
    }

    public void sendUssdRequest(String s, final UssdResponseCallback callback, final Handler final_handler)
    {
        Preconditions.checkNotNull(callback, "UssdResponseCallback cannot be null.");
        callback = new ResultReceiver(this) {

            protected void onReceiveResult(int i, Bundle bundle)
            {
                Rlog.d("TelephonyManager", (new StringBuilder()).append("USSD:").append(i).toString());
                Preconditions.checkNotNull(bundle, "ussdResponse cannot be null.");
                bundle = (UssdResponse)bundle.getParcelable("USSD_RESPONSE");
                if(i == 100)
                    callback.onReceiveUssdResponse(telephonyManager, bundle.getUssdRequest(), bundle.getReturnMessage());
                else
                    callback.onReceiveUssdResponseFailed(telephonyManager, bundle.getUssdRequest(), i);
            }

            final TelephonyManager this$0;
            final UssdResponseCallback val$callback;
            final TelephonyManager val$telephonyManager;

            
            {
                this$0 = TelephonyManager.this;
                callback = ussdresponsecallback;
                telephonyManager = telephonymanager1;
                super(final_handler);
            }
        }
;
        final_handler = getITelephony();
        if(final_handler == null)
            break MISSING_BLOCK_LABEL_41;
        final_handler.handleUssdRequest(getSubId(), s, callback);
_L1:
        return;
        final_handler;
        Log.e("TelephonyManager", "Error calling ITelephony#sendUSSDCode", final_handler);
        s = new UssdResponse(s, "");
        final_handler = new Bundle();
        final_handler.putParcelable("USSD_RESPONSE", s);
        callback.send(-2, final_handler);
          goto _L1
    }

    public void sendVisualVoicemailSms(String s, int i, String s1, PendingIntent pendingintent)
    {
        sendVisualVoicemailSmsForSubscriber(mSubId, s, i, s1, pendingintent);
    }

    public void sendVisualVoicemailSmsForSubscriber(int i, String s, int j, String s1, PendingIntent pendingintent)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_32;
        itelephony.sendVisualVoicemailSmsForSubscriber(mContext.getOpPackageName(), i, s, j, s1, pendingintent);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public int setAllowedCarriers(int i, List list)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_31;
        i = itelephony.setAllowedCarriers(i, list);
        return i;
        list;
        Log.e("TelephonyManager", "Error calling ITelephony#setAllowedCarriers", list);
_L2:
        return -1;
        list;
        Log.e("TelephonyManager", "Error calling ITelephony#setAllowedCarriers", list);
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void setBasebandVersion(String s)
    {
        setBasebandVersionForPhone(getPhoneId(), s);
    }

    public void setBasebandVersionForPhone(int i, String s)
    {
        if(SubscriptionManager.isValidPhoneId(i))
        {
            StringBuilder stringbuilder = (new StringBuilder()).append("gsm.version.baseband");
            String s1;
            if(i == 0)
                s1 = "";
            else
                s1 = Integer.toString(i);
            SystemProperties.set(stringbuilder.append(s1).toString(), s);
        }
    }

    public void setCarrierInfoForImsiEncryption(ImsiEncryptionInfo imsiencryptioninfo)
    {
        IPhoneSubInfo iphonesubinfo = getSubscriberInfo();
        if(iphonesubinfo == null)
            return;
        try
        {
            iphonesubinfo.setCarrierInfoForImsiEncryption(mSubId, mContext.getOpPackageName(), imsiencryptioninfo);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(ImsiEncryptionInfo imsiencryptioninfo)
        {
            return;
        }
        // Misplaced declaration of an exception variable
        catch(ImsiEncryptionInfo imsiencryptioninfo)
        {
            Rlog.e("TelephonyManager", "setCarrierInfoForImsiEncryption RemoteException", imsiencryptioninfo);
        }
        return;
    }

    public void setCellInfoListRate(int i)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_16;
        itelephony.setCellInfoListRate(i);
_L2:
        return;
        Object obj;
        obj;
        continue; /* Loop/switch isn't completed */
        obj;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void setDataActivationState(int i, int j)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_17;
        itelephony.setDataActivationState(i, j);
_L2:
        return;
        Object obj;
        obj;
        continue; /* Loop/switch isn't completed */
        obj;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void setDataEnabled(int i, boolean flag)
    {
        Object obj;
        obj = JVM INSTR new #456 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Log.d("TelephonyManager", ((StringBuilder) (obj)).append("setDataEnabled: enabled=").append(flag).toString());
        obj = getITelephony();
        if(obj == null)
            break MISSING_BLOCK_LABEL_45;
        ((ITelephony) (obj)).setDataEnabled(i, flag);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelephonyManager", "Error calling ITelephony#setDataEnabled", remoteexception);
          goto _L1
    }

    public void setDataEnabled(boolean flag)
    {
        setDataEnabled(getDefaultDataSubscriptionId(), flag);
    }

    public void setDataNetworkType(int i)
    {
        setDataNetworkTypeForPhone(getPhoneId(SubscriptionManager.getDefaultDataSubscriptionId()), i);
    }

    public void setDataNetworkTypeForPhone(int i, int j)
    {
        if(SubscriptionManager.isValidPhoneId(i))
            setTelephonyProperty(i, "gsm.network.type", ServiceState.rilRadioTechnologyToString(j));
    }

    public void setImsRegistrationState(boolean flag)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_16;
        itelephony.setImsRegistrationState(flag);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean setLine1NumberForDisplay(int i, String s, String s1)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_27;
        boolean flag = itelephony.setLine1NumberForDisplayForSubscriber(i, s, s1);
        return flag;
        s;
_L2:
        return false;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean setLine1NumberForDisplay(String s, String s1)
    {
        return setLine1NumberForDisplay(getSubId(), s, s1);
    }

    public void setNetworkCountryIso(String s)
    {
        setNetworkCountryIsoForPhone(getPhoneId(), s);
    }

    public void setNetworkCountryIsoForPhone(int i, String s)
    {
        if(SubscriptionManager.isValidPhoneId(i))
            setTelephonyProperty(i, "gsm.operator.iso-country", s);
    }

    public void setNetworkOperatorName(String s)
    {
        setNetworkOperatorNameForPhone(getPhoneId(), s);
    }

    public void setNetworkOperatorNameForPhone(int i, String s)
    {
        if(SubscriptionManager.isValidPhoneId(i))
            setTelephonyProperty(i, "gsm.operator.alpha", s);
    }

    public void setNetworkOperatorNumeric(String s)
    {
        setNetworkOperatorNumericForPhone(getPhoneId(), s);
    }

    public void setNetworkOperatorNumericForPhone(int i, String s)
    {
        setTelephonyProperty(i, "gsm.operator.numeric", s);
    }

    public void setNetworkRoaming(boolean flag)
    {
        setNetworkRoamingForPhone(getPhoneId(), flag);
    }

    public void setNetworkRoamingForPhone(int i, boolean flag)
    {
        if(SubscriptionManager.isValidPhoneId(i))
        {
            String s;
            if(flag)
                s = "true";
            else
                s = "false";
            setTelephonyProperty(i, "gsm.operator.isroaming", s);
        }
    }

    public void setNetworkSelectionModeAutomatic(int i)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_16;
        itelephony.setNetworkSelectionModeAutomatic(i);
_L1:
        return;
        Object obj;
        obj;
        Rlog.e("TelephonyManager", "setNetworkSelectionModeAutomatic NPE", ((Throwable) (obj)));
          goto _L1
        obj;
        Rlog.e("TelephonyManager", "setNetworkSelectionModeAutomatic RemoteException", ((Throwable) (obj)));
          goto _L1
    }

    public boolean setNetworkSelectionModeManual(int i, OperatorInfo operatorinfo, boolean flag)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_35;
        flag = itelephony.setNetworkSelectionModeManual(i, operatorinfo, flag);
        return flag;
        operatorinfo;
        Rlog.e("TelephonyManager", "setNetworkSelectionModeManual NPE", operatorinfo);
_L2:
        return false;
        operatorinfo;
        Rlog.e("TelephonyManager", "setNetworkSelectionModeManual RemoteException", operatorinfo);
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean setOperatorBrandOverride(int i, String s)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_33;
        boolean flag = itelephony.setOperatorBrandOverride(i, s);
        return flag;
        s;
        Rlog.e("TelephonyManager", "setOperatorBrandOverride NPE", s);
_L2:
        return false;
        s;
        Rlog.e("TelephonyManager", "setOperatorBrandOverride RemoteException", s);
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean setOperatorBrandOverride(String s)
    {
        return setOperatorBrandOverride(getSubId(), s);
    }

    public void setPhoneType(int i)
    {
        setPhoneType(getPhoneId(), i);
    }

    public void setPhoneType(int i, int j)
    {
        if(SubscriptionManager.isValidPhoneId(i))
            setTelephonyProperty(i, "gsm.current.phone-type", String.valueOf(j));
    }

    public void setPolicyDataEnabled(boolean flag, int i)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_17;
        itelephony.setPolicyDataEnabled(flag, i);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelephonyManager", "Error calling ITelephony#setPolicyDataEnabled", remoteexception);
          goto _L1
    }

    public boolean setPreferredNetworkType(int i, int j)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_33;
        boolean flag = itelephony.setPreferredNetworkType(i, j);
        return flag;
        Object obj;
        obj;
        Rlog.e("TelephonyManager", "setPreferredNetworkType NPE", ((Throwable) (obj)));
_L2:
        return false;
        obj;
        Rlog.e("TelephonyManager", "setPreferredNetworkType RemoteException", ((Throwable) (obj)));
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean setPreferredNetworkTypeToGlobal()
    {
        return setPreferredNetworkTypeToGlobal(getSubId());
    }

    public boolean setPreferredNetworkTypeToGlobal(int i)
    {
        return setPreferredNetworkType(i, 10);
    }

    public boolean setRadio(boolean flag)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_30;
        flag = itelephony.setRadio(flag);
        return flag;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelephonyManager", "Error calling ITelephony#setRadio", remoteexception);
        return false;
    }

    public boolean setRadioPower(boolean flag)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_30;
        flag = itelephony.setRadioPower(flag);
        return flag;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelephonyManager", "Error calling ITelephony#setRadioPower", remoteexception);
        return false;
    }

    public boolean setRoamingOverride(int i, List list, List list1, List list2, List list3)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_41;
        boolean flag = itelephony.setRoamingOverride(i, list, list1, list2, list3);
        return flag;
        list;
        Rlog.e("TelephonyManager", "setRoamingOverride NPE", list);
_L2:
        return false;
        list;
        Rlog.e("TelephonyManager", "setRoamingOverride RemoteException", list);
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean setRoamingOverride(List list, List list1, List list2, List list3)
    {
        return setRoamingOverride(getSubId(), list, list1, list2, list3);
    }

    public void setSimCountryIso(String s)
    {
        setSimCountryIsoForPhone(getPhoneId(), s);
    }

    public void setSimCountryIsoForPhone(int i, String s)
    {
        setTelephonyProperty(i, "gsm.sim.operator.iso-country", s);
    }

    public void setSimOperatorName(String s)
    {
        setSimOperatorNameForPhone(getPhoneId(), s);
    }

    public void setSimOperatorNameForPhone(int i, String s)
    {
        setTelephonyProperty(i, "gsm.sim.operator.alpha", s);
    }

    public void setSimOperatorNumeric(String s)
    {
        setSimOperatorNumericForPhone(getPhoneId(), s);
    }

    public void setSimOperatorNumericForPhone(int i, String s)
    {
        setTelephonyProperty(i, "gsm.sim.operator.numeric", s);
    }

    public void setSimPowerState(int i)
    {
        setSimPowerStateForSlot(getSlotIndex(), i);
    }

    public void setSimPowerStateForSlot(int i, int j)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_17;
        itelephony.setSimPowerStateForSlot(i, j);
_L1:
        return;
        Object obj;
        obj;
        Log.e("TelephonyManager", "Permission error calling ITelephony#setSimPowerStateForSlot", ((Throwable) (obj)));
          goto _L1
        obj;
        Log.e("TelephonyManager", "Error calling ITelephony#setSimPowerStateForSlot", ((Throwable) (obj)));
          goto _L1
    }

    public void setSimState(String s)
    {
        setSimStateForPhone(getPhoneId(), s);
    }

    public void setSimStateForPhone(int i, String s)
    {
        setTelephonyProperty(i, "gsm.sim.state", s);
    }

    public void setVisualVoicemailEnabled(PhoneAccountHandle phoneaccounthandle, boolean flag)
    {
    }

    public void setVisualVoicemailSmsFilterSettings(VisualVoicemailSmsFilterSettings visualvoicemailsmsfiltersettings)
    {
        if(visualvoicemailsmsfiltersettings == null)
            disableVisualVoicemailSmsFilter(mSubId);
        else
            enableVisualVoicemailSmsFilter(mSubId, visualvoicemailsmsfiltersettings);
    }

    public void setVoiceActivationState(int i, int j)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_17;
        itelephony.setVoiceActivationState(i, j);
_L2:
        return;
        Object obj;
        obj;
        continue; /* Loop/switch isn't completed */
        obj;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean setVoiceMailNumber(int i, String s, String s1)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_27;
        boolean flag = itelephony.setVoiceMailNumber(i, s, s1);
        return flag;
        s;
_L2:
        return false;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean setVoiceMailNumber(String s, String s1)
    {
        return setVoiceMailNumber(getSubId(), s, s1);
    }

    public void setVoicemailRingtoneUri(PhoneAccountHandle phoneaccounthandle, Uri uri)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_21;
        itelephony.setVoicemailRingtoneUri(getOpPackageName(), phoneaccounthandle, uri);
_L1:
        return;
        phoneaccounthandle;
        Log.e("TelephonyManager", "Error calling ITelephony#setVoicemailRingtoneUri", phoneaccounthandle);
          goto _L1
    }

    public void setVoicemailVibrationEnabled(PhoneAccountHandle phoneaccounthandle, boolean flag)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_21;
        itelephony.setVoicemailVibrationEnabled(getOpPackageName(), phoneaccounthandle, flag);
_L1:
        return;
        phoneaccounthandle;
        Log.e("TelephonyManager", "Error calling ITelephony#isVoicemailVibrationEnabled", phoneaccounthandle);
          goto _L1
    }

    public void silenceRinger()
    {
        getTelecomService().silenceRinger(getOpPackageName());
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelephonyManager", "Error calling ITelecomService#silenceRinger", remoteexception);
          goto _L1
    }

    public boolean supplyPin(String s)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_30;
        boolean flag = itelephony.supplyPin(s);
        return flag;
        s;
        Log.e("TelephonyManager", "Error calling ITelephony#supplyPin", s);
        return false;
    }

    public int[] supplyPinReportResult(String s)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_30;
        s = itelephony.supplyPinReportResult(s);
        return s;
        s;
        Log.e("TelephonyManager", "Error calling ITelephony#supplyPinReportResult", s);
        return new int[0];
    }

    public boolean supplyPuk(String s, String s1)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_33;
        boolean flag = itelephony.supplyPuk(s, s1);
        return flag;
        s;
        Log.e("TelephonyManager", "Error calling ITelephony#supplyPuk", s);
        return false;
    }

    public int[] supplyPukReportResult(String s, String s1)
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_31;
        s = itelephony.supplyPukReportResult(s, s1);
        return s;
        s;
        Log.e("TelephonyManager", "Error calling ITelephony#]", s);
        return new int[0];
    }

    public void toggleRadioOnOff()
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_15;
        itelephony.toggleRadioOnOff();
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelephonyManager", "Error calling ITelephony#toggleRadioOnOff", remoteexception);
          goto _L1
    }

    public void updateServiceLocation()
    {
        ITelephony itelephony = getITelephony();
        if(itelephony == null)
            break MISSING_BLOCK_LABEL_15;
        itelephony.updateServiceLocation();
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelephonyManager", "Error calling ITelephony#updateServiceLocation", remoteexception);
          goto _L1
    }

    private static final int _2D_android_2D_telephony_2D_TelephonyManager$MultiSimVariantsSwitchesValues[];
    public static final String ACTION_CONFIGURE_VOICEMAIL = "android.telephony.action.CONFIGURE_VOICEMAIL";
    public static final String ACTION_EMERGENCY_ASSISTANCE = "android.telephony.action.EMERGENCY_ASSISTANCE";
    public static final String ACTION_PHONE_STATE_CHANGED = "android.intent.action.PHONE_STATE";
    public static final String ACTION_PRECISE_CALL_STATE_CHANGED = "android.intent.action.PRECISE_CALL_STATE";
    public static final String ACTION_PRECISE_DATA_CONNECTION_STATE_CHANGED = "android.intent.action.PRECISE_DATA_CONNECTION_STATE_CHANGED";
    public static final String ACTION_RESPOND_VIA_MESSAGE = "android.intent.action.RESPOND_VIA_MESSAGE";
    public static final String ACTION_SHOW_VOICEMAIL_NOTIFICATION = "android.telephony.action.SHOW_VOICEMAIL_NOTIFICATION";
    public static final int APPTYPE_CSIM = 4;
    public static final int APPTYPE_ISIM = 5;
    public static final int APPTYPE_RUIM = 3;
    public static final int APPTYPE_SIM = 1;
    public static final int APPTYPE_USIM = 2;
    public static final int AUTHTYPE_EAP_AKA = 129;
    public static final int AUTHTYPE_EAP_SIM = 128;
    public static final int CALL_STATE_IDLE = 0;
    public static final int CALL_STATE_OFFHOOK = 2;
    public static final int CALL_STATE_RINGING = 1;
    public static final int CARD_POWER_DOWN = 0;
    public static final int CARD_POWER_UP = 1;
    public static final int CARD_POWER_UP_PASS_THROUGH = 2;
    public static final int CARRIER_PRIVILEGE_STATUS_ERROR_LOADING_RULES = -2;
    public static final int CARRIER_PRIVILEGE_STATUS_HAS_ACCESS = 1;
    public static final int CARRIER_PRIVILEGE_STATUS_NO_ACCESS = 0;
    public static final int CARRIER_PRIVILEGE_STATUS_RULES_NOT_LOADED = -1;
    public static final int DATA_ACTIVITY_DORMANT = 4;
    public static final int DATA_ACTIVITY_IN = 1;
    public static final int DATA_ACTIVITY_INOUT = 3;
    public static final int DATA_ACTIVITY_NONE = 0;
    public static final int DATA_ACTIVITY_OUT = 2;
    public static final int DATA_CONNECTED = 2;
    public static final int DATA_CONNECTING = 1;
    public static final int DATA_DISCONNECTED = 0;
    public static final int DATA_SUSPENDED = 3;
    public static final int DATA_UNKNOWN = -1;
    public static final boolean EMERGENCY_ASSISTANCE_ENABLED = true;
    public static final String EMR_DIAL_ACCOUNT = "emr_dial_account";
    public static final String EVENT_CALL_FORWARDED = "android.telephony.event.EVENT_CALL_FORWARDED";
    public static final String EVENT_DOWNGRADE_DATA_DISABLED = "android.telephony.event.EVENT_DOWNGRADE_DATA_DISABLED";
    public static final String EVENT_DOWNGRADE_DATA_LIMIT_REACHED = "android.telephony.event.EVENT_DOWNGRADE_DATA_LIMIT_REACHED";
    public static final String EVENT_HANDOVER_TO_WIFI_FAILED = "android.telephony.event.EVENT_HANDOVER_TO_WIFI_FAILED";
    public static final String EVENT_HANDOVER_VIDEO_FROM_WIFI_TO_LTE = "android.telephony.event.EVENT_HANDOVER_VIDEO_FROM_WIFI_TO_LTE";
    public static final String EVENT_NOTIFY_INTERNATIONAL_CALL_ON_WFC = "android.telephony.event.EVENT_NOTIFY_INTERNATIONAL_CALL_ON_WFC";
    public static final String EVENT_PHONE_ACCOUNT_CHANGED = "org.codeaurora.event.PHONE_ACCOUNT_CHANGED";
    public static final String EXTRA_BACKGROUND_CALL_STATE = "background_state";
    public static final String EXTRA_CALL_VOICEMAIL_INTENT = "android.telephony.extra.CALL_VOICEMAIL_INTENT";
    public static final String EXTRA_DATA_APN = "apn";
    public static final String EXTRA_DATA_APN_TYPE = "apnType";
    public static final String EXTRA_DATA_CHANGE_REASON = "reason";
    public static final String EXTRA_DATA_FAILURE_CAUSE = "failCause";
    public static final String EXTRA_DATA_LINK_PROPERTIES_KEY = "linkProperties";
    public static final String EXTRA_DATA_NETWORK_TYPE = "networkType";
    public static final String EXTRA_DATA_STATE = "state";
    public static final String EXTRA_DISCONNECT_CAUSE = "disconnect_cause";
    public static final String EXTRA_FOREGROUND_CALL_STATE = "foreground_state";
    public static final String EXTRA_HIDE_PUBLIC_SETTINGS = "android.telephony.extra.HIDE_PUBLIC_SETTINGS";
    public static final String EXTRA_INCOMING_NUMBER = "incoming_number";
    public static final String EXTRA_IS_REFRESH = "android.telephony.extra.IS_REFRESH";
    public static final String EXTRA_LAUNCH_VOICEMAIL_SETTINGS_INTENT = "android.telephony.extra.LAUNCH_VOICEMAIL_SETTINGS_INTENT";
    public static final String EXTRA_NOTIFICATION_COUNT = "android.telephony.extra.NOTIFICATION_COUNT";
    public static final String EXTRA_PHONE_ACCOUNT_HANDLE = "android.telephony.extra.PHONE_ACCOUNT_HANDLE";
    public static final String EXTRA_PRECISE_DISCONNECT_CAUSE = "precise_disconnect_cause";
    public static final String EXTRA_RINGING_CALL_STATE = "ringing_state";
    public static final String EXTRA_STATE = "state";
    public static final String EXTRA_STATE_IDLE;
    public static final String EXTRA_STATE_OFFHOOK;
    public static final String EXTRA_STATE_RINGING;
    public static final String EXTRA_VISUAL_VOICEMAIL_ENABLED_BY_USER_BOOL = "android.telephony.extra.VISUAL_VOICEMAIL_ENABLED_BY_USER_BOOL";
    public static final String EXTRA_VOICEMAIL_NUMBER = "android.telephony.extra.VOICEMAIL_NUMBER";
    public static final String EXTRA_VOICEMAIL_SCRAMBLED_PIN_STRING = "android.telephony.extra.VOICEMAIL_SCRAMBLED_PIN_STRING";
    public static final int KEY_TYPE_EPDG = 1;
    public static final int KEY_TYPE_WLAN = 2;
    public static final String METADATA_HIDE_VOICEMAIL_SETTINGS_MENU = "android.telephony.HIDE_VOICEMAIL_SETTINGS_MENU";
    public static final String MODEM_ACTIVITY_RESULT_KEY = "controller_activity";
    public static final int NETWORK_CLASS_2_G = 1;
    public static final int NETWORK_CLASS_3_G = 2;
    public static final int NETWORK_CLASS_4_G = 3;
    public static final int NETWORK_CLASS_UNKNOWN = 0;
    public static final int NETWORK_TYPE_1xRTT = 7;
    public static final int NETWORK_TYPE_CDMA = 4;
    public static final int NETWORK_TYPE_EDGE = 2;
    public static final int NETWORK_TYPE_EHRPD = 14;
    public static final int NETWORK_TYPE_EVDO_0 = 5;
    public static final int NETWORK_TYPE_EVDO_A = 6;
    public static final int NETWORK_TYPE_EVDO_B = 12;
    public static final int NETWORK_TYPE_GPRS = 1;
    public static final int NETWORK_TYPE_GSM = 16;
    public static final int NETWORK_TYPE_HSDPA = 8;
    public static final int NETWORK_TYPE_HSPA = 10;
    public static final int NETWORK_TYPE_HSPAP = 15;
    public static final int NETWORK_TYPE_HSUPA = 9;
    public static final int NETWORK_TYPE_IDEN = 11;
    public static final int NETWORK_TYPE_IWLAN = 18;
    public static final int NETWORK_TYPE_LTE = 13;
    public static final int NETWORK_TYPE_LTE_CA = 19;
    public static final int NETWORK_TYPE_TD_SCDMA = 17;
    public static final int NETWORK_TYPE_UMTS = 3;
    public static final int NETWORK_TYPE_UNKNOWN = 0;
    public static final int OTASP_NEEDED = 2;
    public static final int OTASP_NOT_NEEDED = 3;
    public static final int OTASP_SIM_UNPROVISIONED = 5;
    public static final int OTASP_UNINITIALIZED = 0;
    public static final int OTASP_UNKNOWN = 1;
    public static final int PHONE_TYPE_CDMA = 2;
    public static final int PHONE_TYPE_GSM = 1;
    public static final int PHONE_TYPE_NONE = 0;
    public static final int PHONE_TYPE_SIP = 3;
    public static final int SIM_ACTIVATION_STATE_ACTIVATED = 2;
    public static final int SIM_ACTIVATION_STATE_ACTIVATING = 1;
    public static final int SIM_ACTIVATION_STATE_DEACTIVATED = 3;
    public static final int SIM_ACTIVATION_STATE_RESTRICTED = 4;
    public static final int SIM_ACTIVATION_STATE_UNKNOWN = 0;
    public static final int SIM_STATE_ABSENT = 1;
    public static final int SIM_STATE_CARD_IO_ERROR = 8;
    public static final int SIM_STATE_CARD_RESTRICTED = 9;
    public static final int SIM_STATE_NETWORK_LOCKED = 4;
    public static final int SIM_STATE_NOT_READY = 6;
    public static final int SIM_STATE_PERM_DISABLED = 7;
    public static final int SIM_STATE_PIN_REQUIRED = 2;
    public static final int SIM_STATE_PUK_REQUIRED = 3;
    public static final int SIM_STATE_READY = 5;
    public static final int SIM_STATE_UNKNOWN = 0;
    private static final String TAG = "TelephonyManager";
    public static final int USSD_ERROR_SERVICE_UNAVAIL = -2;
    public static final String USSD_RESPONSE = "USSD_RESPONSE";
    public static final int USSD_RETURN_FAILURE = -1;
    public static final int USSD_RETURN_SUCCESS = 100;
    public static final String VVM_TYPE_CVVM = "vvm_type_cvvm";
    public static final String VVM_TYPE_OMTP = "vvm_type_omtp";
    private static String multiSimConfig = SystemProperties.get("persist.radio.multisim.config");
    private static TelephonyManager sInstance = new TelephonyManager();
    private static final boolean sIsNorilDevice = SystemProperties.getBoolean("ro.radio.noril", false);
    private static final String sKernelCmdLine = getProcCmdLine();
    private static final String sLteOnCdmaProductType = SystemProperties.get("telephony.lteOnCdmaProductType", "");
    private static final Pattern sProductTypePattern = Pattern.compile("\\sproduct_type\\s*=\\s*(\\w+)");
    private final Context mContext;
    private final int mSubId;
    private SubscriptionManager mSubscriptionManager;
    private TelephonyScanManager mTelephonyScanManager;

    static 
    {
        EXTRA_STATE_IDLE = com.android.internal.telephony.PhoneConstants.State.IDLE.toString();
        EXTRA_STATE_RINGING = com.android.internal.telephony.PhoneConstants.State.RINGING.toString();
        EXTRA_STATE_OFFHOOK = com.android.internal.telephony.PhoneConstants.State.OFFHOOK.toString();
    }
}
