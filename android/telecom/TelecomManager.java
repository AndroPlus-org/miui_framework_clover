// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;

import android.content.*;
import android.net.Uri;
import android.os.*;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.telecom.ITelecomService;
import java.util.*;

// Referenced classes of package android.telecom:
//            Log, PhoneAccountHandle, TelecomAnalytics, PhoneAccount

public class TelecomManager
{

    public TelecomManager(Context context)
    {
        this(context, null);
    }

    public TelecomManager(Context context, ITelecomService itelecomservice)
    {
        Context context1 = context.getApplicationContext();
        if(context1 != null)
            mContext = context1;
        else
            mContext = context;
        mTelecomServiceOverride = itelecomservice;
        Log.initMd5Sum();
    }

    public static TelecomManager from(Context context)
    {
        return (TelecomManager)context.getSystemService("telecom");
    }

    private ITelecomService getTelecomService()
    {
        if(mTelecomServiceOverride != null)
            return mTelecomServiceOverride;
        else
            return com.android.internal.telecom.ITelecomService.Stub.asInterface(ServiceManager.getService("telecom"));
    }

    private boolean isServiceConnected()
    {
        boolean flag;
        if(getTelecomService() != null)
            flag = true;
        else
            flag = false;
        if(!flag)
            Log.w("TelecomManager", "Telecom Service not found.");
        return flag;
    }

    public void acceptRingingCall()
    {
        if(isServiceConnected())
            getTelecomService().acceptRingingCall(mContext.getPackageName());
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelecomManager", "Error calling ITelecomService#acceptRingingCall", remoteexception);
          goto _L1
    }

    public void acceptRingingCall(int i)
    {
        if(isServiceConnected())
            getTelecomService().acceptRingingCallWithVideoState(mContext.getPackageName(), i);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelecomManager", "Error calling ITelecomService#acceptRingingCallWithVideoState", remoteexception);
          goto _L1
    }

    public void addNewIncomingCall(PhoneAccountHandle phoneaccounthandle, Bundle bundle)
    {
        ITelecomService itelecomservice;
        if(!isServiceConnected())
            break MISSING_BLOCK_LABEL_38;
        itelecomservice = getTelecomService();
        Bundle bundle1;
        bundle1 = bundle;
        if(bundle != null)
            break MISSING_BLOCK_LABEL_29;
        bundle1 = JVM INSTR new #276 <Class Bundle>;
        bundle1.Bundle();
        itelecomservice.addNewIncomingCall(phoneaccounthandle, bundle1);
_L1:
        return;
        bundle;
        Log.e("TelecomManager", (new StringBuilder()).append("RemoteException adding a new incoming call: ").append(phoneaccounthandle).toString(), bundle);
          goto _L1
    }

    public void addNewUnknownCall(PhoneAccountHandle phoneaccounthandle, Bundle bundle)
    {
        ITelecomService itelecomservice;
        if(!isServiceConnected())
            break MISSING_BLOCK_LABEL_38;
        itelecomservice = getTelecomService();
        Bundle bundle1;
        bundle1 = bundle;
        if(bundle != null)
            break MISSING_BLOCK_LABEL_29;
        bundle1 = JVM INSTR new #276 <Class Bundle>;
        bundle1.Bundle();
        itelecomservice.addNewUnknownCall(phoneaccounthandle, bundle1);
_L1:
        return;
        bundle;
        Log.e("TelecomManager", (new StringBuilder()).append("RemoteException adding a new unknown call: ").append(phoneaccounthandle).toString(), bundle);
          goto _L1
    }

    public void cancelMissedCallsNotification()
    {
        ITelecomService itelecomservice;
        itelecomservice = getTelecomService();
        if(itelecomservice == null)
            break MISSING_BLOCK_LABEL_22;
        itelecomservice.cancelMissedCallsNotification(mContext.getOpPackageName());
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelecomManager", "Error calling ITelecomService#cancelMissedCallsNotification", remoteexception);
          goto _L1
    }

    public void clearAccounts()
    {
        if(isServiceConnected())
            getTelecomService().clearAccounts(mContext.getPackageName());
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelecomManager", "Error calling ITelecomService#clearAccounts", remoteexception);
          goto _L1
    }

    public void clearAccountsForPackage(String s)
    {
        if(isServiceConnected() && TextUtils.isEmpty(s) ^ true)
            getTelecomService().clearAccounts(s);
_L1:
        return;
        s;
        Log.e("TelecomManager", "Error calling ITelecomService#clearAccountsForPackage", s);
          goto _L1
    }

    public void clearPhoneAccounts()
    {
        clearAccounts();
    }

    public Intent createManageBlockedNumbersIntent()
    {
        ITelecomService itelecomservice = getTelecomService();
        Object obj = null;
        Intent intent = obj;
        if(itelecomservice != null)
            try
            {
                intent = itelecomservice.createManageBlockedNumbersIntent();
            }
            catch(RemoteException remoteexception)
            {
                Log.e("TelecomManager", "Error calling ITelecomService#createManageBlockedNumbersIntent", remoteexception);
                remoteexception = obj;
            }
        return intent;
    }

    public TelecomAnalytics dumpAnalytics()
    {
        ITelecomService itelecomservice = getTelecomService();
        Object obj = null;
        TelecomAnalytics telecomanalytics = obj;
        if(itelecomservice != null)
            try
            {
                telecomanalytics = itelecomservice.dumpCallAnalytics();
            }
            catch(RemoteException remoteexception)
            {
                Log.e("TelecomManager", "Error dumping call analytics", remoteexception);
                remoteexception = obj;
            }
        return telecomanalytics;
    }

    public void enablePhoneAccount(PhoneAccountHandle phoneaccounthandle, boolean flag)
    {
        ITelecomService itelecomservice;
        itelecomservice = getTelecomService();
        if(itelecomservice == null)
            break MISSING_BLOCK_LABEL_18;
        itelecomservice.enablePhoneAccount(phoneaccounthandle, flag);
_L1:
        return;
        phoneaccounthandle;
        Log.e("TelecomManager", "Error enablePhoneAbbount", phoneaccounthandle);
          goto _L1
    }

    public boolean endCall()
    {
        boolean flag;
        if(!isServiceConnected())
            break MISSING_BLOCK_LABEL_30;
        flag = getTelecomService().endCall();
        return flag;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelecomManager", "Error calling ITelecomService#endCall", remoteexception);
        return false;
    }

    public Uri getAdnUriForPhoneAccount(PhoneAccountHandle phoneaccounthandle)
    {
        ITelecomService itelecomservice;
        itelecomservice = getTelecomService();
        if(itelecomservice == null || phoneaccounthandle == null)
            break MISSING_BLOCK_LABEL_41;
        phoneaccounthandle = itelecomservice.getAdnUriForPhoneAccount(phoneaccounthandle, mContext.getOpPackageName());
        return phoneaccounthandle;
        phoneaccounthandle;
        Log.e("TelecomManager", "Error calling ITelecomService#getAdnUriForPhoneAccount", phoneaccounthandle);
        return Uri.parse("content://icc/adn");
    }

    public List getAllPhoneAccountHandles()
    {
        List list;
        if(!isServiceConnected())
            break MISSING_BLOCK_LABEL_30;
        list = getTelecomService().getAllPhoneAccountHandles();
        return list;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelecomManager", "Error calling ITelecomService#getAllPhoneAccountHandles", remoteexception);
        return Collections.EMPTY_LIST;
    }

    public List getAllPhoneAccounts()
    {
        List list;
        if(!isServiceConnected())
            break MISSING_BLOCK_LABEL_30;
        list = getTelecomService().getAllPhoneAccounts();
        return list;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelecomManager", "Error calling ITelecomService#getAllPhoneAccounts", remoteexception);
        return Collections.EMPTY_LIST;
    }

    public int getAllPhoneAccountsCount()
    {
        int i;
        if(!isServiceConnected())
            break MISSING_BLOCK_LABEL_30;
        i = getTelecomService().getAllPhoneAccountsCount();
        return i;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelecomManager", "Error calling ITelecomService#getAllPhoneAccountsCount", remoteexception);
        return 0;
    }

    public List getCallCapablePhoneAccounts()
    {
        return getCallCapablePhoneAccounts(false);
    }

    public List getCallCapablePhoneAccounts(boolean flag)
    {
        List list;
        if(!isServiceConnected())
            break MISSING_BLOCK_LABEL_61;
        list = getTelecomService().getCallCapablePhoneAccounts(flag, mContext.getOpPackageName());
        return list;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelecomManager", (new StringBuilder()).append("Error calling ITelecomService#getCallCapablePhoneAccounts(").append(flag).append(")").toString(), remoteexception);
        return new ArrayList();
    }

    public int getCallState()
    {
        int i;
        if(!isServiceConnected())
            break MISSING_BLOCK_LABEL_30;
        i = getTelecomService().getCallState();
        return i;
        RemoteException remoteexception;
        remoteexception;
        Log.d("TelecomManager", "RemoteException calling getCallState().", remoteexception);
        return 0;
    }

    public PhoneAccountHandle getConnectionManager()
    {
        return getSimCallManager();
    }

    public int getCurrentTtyMode()
    {
        int i;
        if(!isServiceConnected())
            break MISSING_BLOCK_LABEL_37;
        i = getTelecomService().getCurrentTtyMode(mContext.getOpPackageName());
        return i;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelecomManager", "RemoteException attempting to get the current TTY mode.", remoteexception);
        return 0;
    }

    public String getDefaultDialerPackage()
    {
        String s;
        if(!isServiceConnected())
            break MISSING_BLOCK_LABEL_30;
        s = getTelecomService().getDefaultDialerPackage();
        return s;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelecomManager", "RemoteException attempting to get the default dialer package name.", remoteexception);
        return null;
    }

    public PhoneAccountHandle getDefaultOutgoingPhoneAccount(String s)
    {
        if(!isServiceConnected())
            break MISSING_BLOCK_LABEL_38;
        s = getTelecomService().getDefaultOutgoingPhoneAccount(s, mContext.getOpPackageName());
        return s;
        s;
        Log.e("TelecomManager", "Error calling ITelecomService#getDefaultOutgoingPhoneAccount", s);
        return null;
    }

    public ComponentName getDefaultPhoneApp()
    {
        ComponentName componentname;
        if(!isServiceConnected())
            break MISSING_BLOCK_LABEL_30;
        componentname = getTelecomService().getDefaultPhoneApp();
        return componentname;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelecomManager", "RemoteException attempting to get the default phone app.", remoteexception);
        return null;
    }

    public String getLine1Number(PhoneAccountHandle phoneaccounthandle)
    {
        if(!isServiceConnected())
            break MISSING_BLOCK_LABEL_38;
        phoneaccounthandle = getTelecomService().getLine1Number(phoneaccounthandle, mContext.getOpPackageName());
        return phoneaccounthandle;
        phoneaccounthandle;
        Log.e("TelecomManager", "RemoteException calling ITelecomService#getLine1Number.", phoneaccounthandle);
        return null;
    }

    public PhoneAccount getPhoneAccount(PhoneAccountHandle phoneaccounthandle)
    {
        if(!isServiceConnected())
            break MISSING_BLOCK_LABEL_31;
        phoneaccounthandle = getTelecomService().getPhoneAccount(phoneaccounthandle);
        return phoneaccounthandle;
        phoneaccounthandle;
        Log.e("TelecomManager", "Error calling ITelecomService#getPhoneAccount", phoneaccounthandle);
        return null;
    }

    public List getPhoneAccountsForPackage()
    {
        List list;
        if(!isServiceConnected())
            break MISSING_BLOCK_LABEL_37;
        list = getTelecomService().getPhoneAccountsForPackage(mContext.getPackageName());
        return list;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelecomManager", "Error calling ITelecomService#getPhoneAccountsForPackage", remoteexception);
        return null;
    }

    public List getPhoneAccountsSupportingScheme(String s)
    {
        if(!isServiceConnected())
            break MISSING_BLOCK_LABEL_38;
        s = getTelecomService().getPhoneAccountsSupportingScheme(s, mContext.getOpPackageName());
        return s;
        s;
        Log.e("TelecomManager", "Error calling ITelecomService#getPhoneAccountsSupportingScheme", s);
        return new ArrayList();
    }

    public List getSelfManagedPhoneAccounts()
    {
        List list;
        if(!isServiceConnected())
            break MISSING_BLOCK_LABEL_37;
        list = getTelecomService().getSelfManagedPhoneAccounts(mContext.getOpPackageName());
        return list;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelecomManager", "Error calling ITelecomService#getSelfManagedPhoneAccounts()", remoteexception);
        return new ArrayList();
    }

    public PhoneAccountHandle getSimCallManager()
    {
        PhoneAccountHandle phoneaccounthandle;
        if(!isServiceConnected())
            break MISSING_BLOCK_LABEL_29;
        phoneaccounthandle = getTelecomService().getSimCallManager();
        return phoneaccounthandle;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelecomManager", "Error calling ITelecomService#getSimCallManager");
        return null;
    }

    public PhoneAccountHandle getSimCallManager(int i)
    {
        PhoneAccountHandle phoneaccounthandle;
        if(!isServiceConnected())
            break MISSING_BLOCK_LABEL_30;
        phoneaccounthandle = getTelecomService().getSimCallManagerForUser(i);
        return phoneaccounthandle;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelecomManager", "Error calling ITelecomService#getSimCallManagerForUser");
        return null;
    }

    public String getSystemDialerPackage()
    {
        String s;
        if(!isServiceConnected())
            break MISSING_BLOCK_LABEL_30;
        s = getTelecomService().getSystemDialerPackage();
        return s;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelecomManager", "RemoteException attempting to get the system dialer package name.", remoteexception);
        return null;
    }

    public PhoneAccountHandle getUserSelectedOutgoingPhoneAccount()
    {
        PhoneAccountHandle phoneaccounthandle;
        if(!isServiceConnected())
            break MISSING_BLOCK_LABEL_30;
        phoneaccounthandle = getTelecomService().getUserSelectedOutgoingPhoneAccount();
        return phoneaccounthandle;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelecomManager", "Error calling ITelecomService#getUserSelectedOutgoingPhoneAccount", remoteexception);
        return null;
    }

    public String getVoiceMailNumber(PhoneAccountHandle phoneaccounthandle)
    {
        if(!isServiceConnected())
            break MISSING_BLOCK_LABEL_38;
        phoneaccounthandle = getTelecomService().getVoiceMailNumber(phoneaccounthandle, mContext.getOpPackageName());
        return phoneaccounthandle;
        phoneaccounthandle;
        Log.e("TelecomManager", "RemoteException calling ITelecomService#hasVoiceMailNumber.", phoneaccounthandle);
        return null;
    }

    public boolean handleMmi(String s)
    {
        ITelecomService itelecomservice;
        itelecomservice = getTelecomService();
        if(itelecomservice == null)
            break MISSING_BLOCK_LABEL_37;
        boolean flag = itelecomservice.handlePinMmi(s, mContext.getOpPackageName());
        return flag;
        s;
        Log.e("TelecomManager", "Error calling ITelecomService#handlePinMmi", s);
        return false;
    }

    public boolean handleMmi(String s, PhoneAccountHandle phoneaccounthandle)
    {
        ITelecomService itelecomservice;
        itelecomservice = getTelecomService();
        if(itelecomservice == null)
            break MISSING_BLOCK_LABEL_40;
        boolean flag = itelecomservice.handlePinMmiForPhoneAccount(phoneaccounthandle, s, mContext.getOpPackageName());
        return flag;
        s;
        Log.e("TelecomManager", "Error calling ITelecomService#handlePinMmi", s);
        return false;
    }

    public boolean isInCall()
    {
        boolean flag;
        if(!isServiceConnected())
            break MISSING_BLOCK_LABEL_37;
        flag = getTelecomService().isInCall(mContext.getOpPackageName());
        return flag;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelecomManager", "RemoteException calling isInCall().", remoteexception);
        return false;
    }

    public boolean isInManagedCall()
    {
        boolean flag;
        if(!isServiceConnected())
            break MISSING_BLOCK_LABEL_37;
        flag = getTelecomService().isInManagedCall(mContext.getOpPackageName());
        return flag;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelecomManager", "RemoteException calling isInManagedCall().", remoteexception);
        return false;
    }

    public boolean isIncomingCallPermitted(PhoneAccountHandle phoneaccounthandle)
    {
        ITelecomService itelecomservice;
        if(phoneaccounthandle == null)
            return false;
        itelecomservice = getTelecomService();
        if(itelecomservice == null)
            break MISSING_BLOCK_LABEL_36;
        boolean flag = itelecomservice.isIncomingCallPermitted(phoneaccounthandle);
        return flag;
        phoneaccounthandle;
        Log.e("TelecomManager", "Error isIncomingCallPermitted", phoneaccounthandle);
        return false;
    }

    public boolean isOutgoingCallPermitted(PhoneAccountHandle phoneaccounthandle)
    {
        ITelecomService itelecomservice;
        itelecomservice = getTelecomService();
        if(itelecomservice == null)
            break MISSING_BLOCK_LABEL_30;
        boolean flag = itelecomservice.isOutgoingCallPermitted(phoneaccounthandle);
        return flag;
        phoneaccounthandle;
        Log.e("TelecomManager", "Error isOutgoingCallPermitted", phoneaccounthandle);
        return false;
    }

    public boolean isRinging()
    {
        boolean flag;
        if(!isServiceConnected())
            break MISSING_BLOCK_LABEL_37;
        flag = getTelecomService().isRinging(mContext.getOpPackageName());
        return flag;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelecomManager", "RemoteException attempting to get ringing state of phone app.", remoteexception);
        return false;
    }

    public boolean isTtySupported()
    {
        boolean flag;
        if(!isServiceConnected())
            break MISSING_BLOCK_LABEL_37;
        flag = getTelecomService().isTtySupported(mContext.getOpPackageName());
        return flag;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelecomManager", "RemoteException attempting to get TTY supported state.", remoteexception);
        return false;
    }

    public boolean isVoiceMailNumber(PhoneAccountHandle phoneaccounthandle, String s)
    {
        boolean flag;
        if(!isServiceConnected())
            break MISSING_BLOCK_LABEL_39;
        flag = getTelecomService().isVoiceMailNumber(phoneaccounthandle, s, mContext.getOpPackageName());
        return flag;
        phoneaccounthandle;
        Log.e("TelecomManager", "RemoteException calling ITelecomService#isVoiceMailNumber.", phoneaccounthandle);
        return false;
    }

    public void placeCall(Uri uri, Bundle bundle)
    {
        ITelecomService itelecomservice;
        Bundle bundle1;
        itelecomservice = getTelecomService();
        if(itelecomservice == null)
            break MISSING_BLOCK_LABEL_55;
        if(uri == null)
            Log.w("TelecomManager", "Cannot place call to empty address.");
        bundle1 = bundle;
        if(bundle != null)
            break MISSING_BLOCK_LABEL_39;
        bundle1 = JVM INSTR new #276 <Class Bundle>;
        bundle1.Bundle();
        itelecomservice.placeCall(uri, bundle1, mContext.getOpPackageName());
_L1:
        return;
        uri;
        Log.e("TelecomManager", "Error calling ITelecomService#placeCall", uri);
          goto _L1
    }

    public void registerPhoneAccount(PhoneAccount phoneaccount)
    {
        if(isServiceConnected())
            getTelecomService().registerPhoneAccount(phoneaccount);
_L1:
        return;
        phoneaccount;
        Log.e("TelecomManager", "Error calling ITelecomService#registerPhoneAccount", phoneaccount);
          goto _L1
    }

    public boolean setDefaultDialer(String s)
    {
        boolean flag;
        if(!isServiceConnected())
            break MISSING_BLOCK_LABEL_31;
        flag = getTelecomService().setDefaultDialer(s);
        return flag;
        s;
        Log.e("TelecomManager", "RemoteException attempting to set the default dialer.", s);
        return false;
    }

    public void setUserSelectedOutgoingPhoneAccount(PhoneAccountHandle phoneaccounthandle)
    {
        if(isServiceConnected())
            getTelecomService().setUserSelectedOutgoingPhoneAccount(phoneaccounthandle);
_L1:
        return;
        phoneaccounthandle;
        Log.e("TelecomManager", "Error calling ITelecomService#setUserSelectedOutgoingPhoneAccount");
          goto _L1
    }

    public void showInCallScreen(boolean flag)
    {
        ITelecomService itelecomservice;
        itelecomservice = getTelecomService();
        if(itelecomservice == null)
            break MISSING_BLOCK_LABEL_23;
        itelecomservice.showInCallScreen(flag, mContext.getOpPackageName());
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelecomManager", "Error calling ITelecomService#showCallScreen", remoteexception);
          goto _L1
    }

    public void silenceRinger()
    {
        if(isServiceConnected())
            getTelecomService().silenceRinger(mContext.getOpPackageName());
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("TelecomManager", "Error calling ITelecomService#silenceRinger", remoteexception);
          goto _L1
    }

    public void unregisterPhoneAccount(PhoneAccountHandle phoneaccounthandle)
    {
        if(isServiceConnected())
            getTelecomService().unregisterPhoneAccount(phoneaccounthandle);
_L1:
        return;
        phoneaccounthandle;
        Log.e("TelecomManager", "Error calling ITelecomService#unregisterPhoneAccount", phoneaccounthandle);
          goto _L1
    }

    public static final String ACTION_CALL_TYPE = "codeaurora.telecom.action.CALL_TYPE";
    public static final String ACTION_CHANGE_DEFAULT_DIALER = "android.telecom.action.CHANGE_DEFAULT_DIALER";
    public static final String ACTION_CHANGE_PHONE_ACCOUNTS = "android.telecom.action.CHANGE_PHONE_ACCOUNTS";
    public static final String ACTION_CONFIGURE_PHONE_ACCOUNT = "android.telecom.action.CONFIGURE_PHONE_ACCOUNT";
    public static final String ACTION_CURRENT_TTY_MODE_CHANGED = "android.telecom.action.CURRENT_TTY_MODE_CHANGED";
    public static final String ACTION_DEFAULT_DIALER_CHANGED = "android.telecom.action.DEFAULT_DIALER_CHANGED";
    public static final String ACTION_INCOMING_CALL = "android.telecom.action.INCOMING_CALL";
    public static final String ACTION_NEW_UNKNOWN_CALL = "android.telecom.action.NEW_UNKNOWN_CALL";
    public static final String ACTION_PHONE_ACCOUNT_REGISTERED = "android.telecom.action.PHONE_ACCOUNT_REGISTERED";
    public static final String ACTION_PHONE_ACCOUNT_UNREGISTERED = "android.telecom.action.PHONE_ACCOUNT_UNREGISTERED";
    public static final String ACTION_SHOW_CALL_ACCESSIBILITY_SETTINGS = "android.telecom.action.SHOW_CALL_ACCESSIBILITY_SETTINGS";
    public static final String ACTION_SHOW_CALL_SETTINGS = "android.telecom.action.SHOW_CALL_SETTINGS";
    public static final String ACTION_SHOW_MISSED_CALLS_NOTIFICATION = "android.telecom.action.SHOW_MISSED_CALLS_NOTIFICATION";
    public static final String ACTION_SHOW_RESPOND_VIA_SMS_SETTINGS = "android.telecom.action.SHOW_RESPOND_VIA_SMS_SETTINGS";
    public static final String ACTION_TTY_PREFERRED_MODE_CHANGED = "android.telecom.action.TTY_PREFERRED_MODE_CHANGED";
    public static final char DTMF_CHARACTER_PAUSE = 44;
    public static final char DTMF_CHARACTER_WAIT = 59;
    public static final String EXTRA_CALL_AUDIO_STATE = "android.telecom.extra.CALL_AUDIO_STATE";
    public static final String EXTRA_CALL_BACK_INTENT = "android.telecom.extra.CALL_BACK_INTENT";
    public static final String EXTRA_CALL_BACK_NUMBER = "android.telecom.extra.CALL_BACK_NUMBER";
    public static final String EXTRA_CALL_CREATED_TIME_MILLIS = "android.telecom.extra.CALL_CREATED_TIME_MILLIS";
    public static final String EXTRA_CALL_DISCONNECT_CAUSE = "android.telecom.extra.CALL_DISCONNECT_CAUSE";
    public static final String EXTRA_CALL_DISCONNECT_MESSAGE = "android.telecom.extra.CALL_DISCONNECT_MESSAGE";
    public static final String EXTRA_CALL_SUBJECT = "android.telecom.extra.CALL_SUBJECT";
    public static final String EXTRA_CALL_TECHNOLOGY_TYPE = "android.telecom.extra.CALL_TECHNOLOGY_TYPE";
    public static final String EXTRA_CALL_TELECOM_ROUTING_END_TIME_MILLIS = "android.telecom.extra.CALL_TELECOM_ROUTING_END_TIME_MILLIS";
    public static final String EXTRA_CALL_TELECOM_ROUTING_START_TIME_MILLIS = "android.telecom.extra.CALL_TELECOM_ROUTING_START_TIME_MILLIS";
    public static final String EXTRA_CALL_TYPE_CS = "codeaurora.telecom.extra.CALL_TYPE_CS";
    public static final String EXTRA_CHANGE_DEFAULT_DIALER_PACKAGE_NAME = "android.telecom.extra.CHANGE_DEFAULT_DIALER_PACKAGE_NAME";
    public static final String EXTRA_CLEAR_MISSED_CALLS_INTENT = "android.telecom.extra.CLEAR_MISSED_CALLS_INTENT";
    public static final String EXTRA_CONNECTION_SERVICE = "android.telecom.extra.CONNECTION_SERVICE";
    public static final String EXTRA_CURRENT_TTY_MODE = "android.telecom.intent.extra.CURRENT_TTY_MODE";
    public static final String EXTRA_HANDOVER_FROM_PHONE_ACCOUNT = "android.telecom.extra.HANDOVER_FROM_PHONE_ACCOUNT";
    public static final String EXTRA_INCOMING_CALL_ADDRESS = "android.telecom.extra.INCOMING_CALL_ADDRESS";
    public static final String EXTRA_INCOMING_CALL_EXTRAS = "android.telecom.extra.INCOMING_CALL_EXTRAS";
    public static final String EXTRA_INCOMING_VIDEO_STATE = "android.telecom.extra.INCOMING_VIDEO_STATE";
    public static final String EXTRA_IS_HANDOVER = "android.telecom.extra.IS_HANDOVER";
    public static final String EXTRA_NEW_OUTGOING_CALL_CANCEL_TIMEOUT = "android.telecom.extra.NEW_OUTGOING_CALL_CANCEL_TIMEOUT";
    public static final String EXTRA_NOTIFICATION_COUNT = "android.telecom.extra.NOTIFICATION_COUNT";
    public static final String EXTRA_NOTIFICATION_PHONE_NUMBER = "android.telecom.extra.NOTIFICATION_PHONE_NUMBER";
    public static final String EXTRA_OUTGOING_CALL_EXTRAS = "android.telecom.extra.OUTGOING_CALL_EXTRAS";
    public static final String EXTRA_PHONE_ACCOUNT_HANDLE = "android.telecom.extra.PHONE_ACCOUNT_HANDLE";
    public static final String EXTRA_START_CALL_WITH_RTT = "android.telecom.extra.START_CALL_WITH_RTT";
    public static final String EXTRA_START_CALL_WITH_SPEAKERPHONE = "android.telecom.extra.START_CALL_WITH_SPEAKERPHONE";
    public static final String EXTRA_START_CALL_WITH_VIDEO_STATE = "android.telecom.extra.START_CALL_WITH_VIDEO_STATE";
    public static final String EXTRA_TTY_PREFERRED_MODE = "android.telecom.intent.extra.TTY_PREFERRED";
    public static final String EXTRA_UNKNOWN_CALL_HANDLE = "android.telecom.extra.UNKNOWN_CALL_HANDLE";
    public static final String GATEWAY_ORIGINAL_ADDRESS = "android.telecom.extra.GATEWAY_ORIGINAL_ADDRESS";
    public static final String GATEWAY_PROVIDER_PACKAGE = "android.telecom.extra.GATEWAY_PROVIDER_PACKAGE";
    public static final String METADATA_INCLUDE_EXTERNAL_CALLS = "android.telecom.INCLUDE_EXTERNAL_CALLS";
    public static final String METADATA_INCLUDE_SELF_MANAGED_CALLS = "android.telecom.INCLUDE_SELF_MANAGED_CALLS";
    public static final String METADATA_IN_CALL_SERVICE_CAR_MODE_UI = "android.telecom.IN_CALL_SERVICE_CAR_MODE_UI";
    public static final String METADATA_IN_CALL_SERVICE_RINGING = "android.telecom.IN_CALL_SERVICE_RINGING";
    public static final String METADATA_IN_CALL_SERVICE_UI = "android.telecom.IN_CALL_SERVICE_UI";
    public static final int PRESENTATION_ALLOWED = 1;
    public static final int PRESENTATION_PAYPHONE = 4;
    public static final int PRESENTATION_RESTRICTED = 2;
    public static final int PRESENTATION_UNKNOWN = 3;
    private static final String TAG = "TelecomManager";
    public static final int TTY_MODE_FULL = 1;
    public static final int TTY_MODE_HCO = 2;
    public static final int TTY_MODE_OFF = 0;
    public static final int TTY_MODE_VCO = 3;
    private final Context mContext;
    private final ITelecomService mTelecomServiceOverride;
}
