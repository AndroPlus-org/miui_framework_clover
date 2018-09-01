// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.app.*;
import android.content.*;
import android.net.Uri;
import android.os.*;
import android.text.TextUtils;
import android.util.*;
import com.android.internal.telephony.*;
import java.util.*;

// Referenced classes of package android.telephony:
//            SmsMessage

public final class SmsManager
{

    private SmsManager(int i)
    {
        mSubId = i;
    }

    private ArrayList createMessageListFromRawRecords(List list)
    {
        ArrayList arraylist = new ArrayList();
        if(list != null)
        {
            int i = list.size();
            for(int j = 0; j < i; j++)
            {
                Object obj = (SmsRawData)list.get(j);
                if(obj == null)
                    continue;
                obj = SmsMessage.createFromEfRecord(j + 1, ((SmsRawData) (obj)).getBytes(), getSubscriptionId());
                if(obj != null)
                    arraylist.add(obj);
            }

        }
        return arraylist;
    }

    public static SmsManager getDefault()
    {
        return sInstance;
    }

    public static int getDefaultSmsSubscriptionId()
    {
        int i;
        try
        {
            i = com.android.internal.telephony.ISms.Stub.asInterface(ServiceManager.getService("isms")).getPreferredSmsSubscription();
        }
        catch(RemoteException remoteexception)
        {
            return -1;
        }
        catch(NullPointerException nullpointerexception)
        {
            return -1;
        }
        return i;
    }

    private static ISms getISmsService()
    {
        return com.android.internal.telephony.ISms.Stub.asInterface(ServiceManager.getService("isms"));
    }

    private static ISms getISmsServiceOrThrow()
    {
        ISms isms = getISmsService();
        if(isms == null)
            throw new UnsupportedOperationException("Sms is not supported");
        else
            return isms;
    }

    public static Bundle getMmsConfig(BaseBundle basebundle)
    {
        Bundle bundle = new Bundle();
        bundle.putBoolean("enabledTransID", basebundle.getBoolean("enabledTransID"));
        bundle.putBoolean("enabledMMS", basebundle.getBoolean("enabledMMS"));
        bundle.putBoolean("enableGroupMms", basebundle.getBoolean("enableGroupMms"));
        bundle.putBoolean("enabledNotifyWapMMSC", basebundle.getBoolean("enabledNotifyWapMMSC"));
        bundle.putBoolean("aliasEnabled", basebundle.getBoolean("aliasEnabled"));
        bundle.putBoolean("allowAttachAudio", basebundle.getBoolean("allowAttachAudio"));
        bundle.putBoolean("enableMultipartSMS", basebundle.getBoolean("enableMultipartSMS"));
        bundle.putBoolean("enableSMSDeliveryReports", basebundle.getBoolean("enableSMSDeliveryReports"));
        bundle.putBoolean("supportMmsContentDisposition", basebundle.getBoolean("supportMmsContentDisposition"));
        bundle.putBoolean("sendMultipartSmsAsSeparateMessages", basebundle.getBoolean("sendMultipartSmsAsSeparateMessages"));
        bundle.putBoolean("enableMMSReadReports", basebundle.getBoolean("enableMMSReadReports"));
        bundle.putBoolean("enableMMSDeliveryReports", basebundle.getBoolean("enableMMSDeliveryReports"));
        bundle.putBoolean("mmsCloseConnection", basebundle.getBoolean("mmsCloseConnection"));
        bundle.putInt("maxMessageSize", basebundle.getInt("maxMessageSize"));
        bundle.putInt("maxImageWidth", basebundle.getInt("maxImageWidth"));
        bundle.putInt("maxImageHeight", basebundle.getInt("maxImageHeight"));
        bundle.putInt("recipientLimit", basebundle.getInt("recipientLimit"));
        bundle.putInt("aliasMinChars", basebundle.getInt("aliasMinChars"));
        bundle.putInt("aliasMaxChars", basebundle.getInt("aliasMaxChars"));
        bundle.putInt("smsToMmsTextThreshold", basebundle.getInt("smsToMmsTextThreshold"));
        bundle.putInt("smsToMmsTextLengthThreshold", basebundle.getInt("smsToMmsTextLengthThreshold"));
        bundle.putInt("maxMessageTextSize", basebundle.getInt("maxMessageTextSize"));
        bundle.putInt("maxSubjectLength", basebundle.getInt("maxSubjectLength"));
        bundle.putInt("httpSocketTimeout", basebundle.getInt("httpSocketTimeout"));
        bundle.putString("uaProfTagName", basebundle.getString("uaProfTagName"));
        bundle.putString("userAgent", basebundle.getString("userAgent"));
        bundle.putString("uaProfUrl", basebundle.getString("uaProfUrl"));
        bundle.putString("httpParams", basebundle.getString("httpParams"));
        bundle.putString("emailGatewayNumber", basebundle.getString("emailGatewayNumber"));
        bundle.putString("naiSuffix", basebundle.getString("naiSuffix"));
        bundle.putBoolean("config_cellBroadcastAppLinks", basebundle.getBoolean("config_cellBroadcastAppLinks"));
        bundle.putBoolean("supportHttpCharsetHeader", basebundle.getBoolean("supportHttpCharsetHeader"));
        return bundle;
    }

    public static SmsManager getSmsManagerForSubscriptionId(int i)
    {
        Object obj = sLockObject;
        obj;
        JVM INSTR monitorenter ;
        SmsManager smsmanager = (SmsManager)sSubInstances.get(Integer.valueOf(i));
        SmsManager smsmanager1;
        smsmanager1 = smsmanager;
        if(smsmanager != null)
            break MISSING_BLOCK_LABEL_51;
        smsmanager1 = JVM INSTR new #2   <Class SmsManager>;
        smsmanager1.SmsManager(i);
        sSubInstances.put(Integer.valueOf(i), smsmanager1);
        obj;
        JVM INSTR monitorexit ;
        return smsmanager1;
        Exception exception;
        exception;
        throw exception;
    }

    private void sendMultipartTextMessageInternal(String s, String s1, List list, List list1, List list2, boolean flag)
    {
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException("Invalid destinationAddress");
        if(list == null || list.size() < 1)
            throw new IllegalArgumentException("Invalid message body");
        if(list.size() <= 1) goto _L2; else goto _L1
_L1:
        getISmsServiceOrThrow().sendMultipartTextForSubscriber(getSubscriptionId(), ActivityThread.currentPackageName(), s, s1, list, list1, list2, flag);
_L4:
        return;
_L2:
        Object obj = null;
        Object obj1 = null;
        PendingIntent pendingintent = obj;
        if(list1 != null)
        {
            pendingintent = obj;
            if(list1.size() > 0)
                pendingintent = (PendingIntent)list1.get(0);
        }
        list1 = obj1;
        if(list2 != null)
        {
            list1 = obj1;
            if(list2.size() > 0)
                list1 = (PendingIntent)list2.get(0);
        }
        sendTextMessage(s, s1, (String)list.get(0), pendingintent, list1);
        continue; /* Loop/switch isn't completed */
        s;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void sendMultipartTextMessageInternal(String s, String s1, List list, List list1, List list2, boolean flag, int i, 
            boolean flag1, int j)
    {
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException("Invalid destinationAddress");
        if(list == null || list.size() < 1)
            throw new IllegalArgumentException("Invalid message body");
        if(list.size() <= 1) goto _L2; else goto _L1
_L1:
        ISms isms = getISmsServiceOrThrow();
        if(isms == null)
            break MISSING_BLOCK_LABEL_92;
        isms.sendMultipartTextForSubscriberWithOptions(getSubscriptionId(), ActivityThread.currentPackageName(), s, s1, list, list1, list2, flag, i, flag1, j);
_L4:
        return;
_L2:
        Object obj = null;
        Object obj1 = null;
        PendingIntent pendingintent = obj;
        if(list1 != null)
        {
            pendingintent = obj;
            if(list1.size() > 0)
                pendingintent = (PendingIntent)list1.get(0);
        }
        list1 = obj1;
        if(list2 != null)
        {
            list1 = obj1;
            if(list2.size() > 0)
                list1 = (PendingIntent)list2.get(0);
        }
        sendTextMessageInternal(s, s1, (String)list.get(0), pendingintent, list1, flag, i, flag1, j);
        continue; /* Loop/switch isn't completed */
        s;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void sendTextMessageInternal(String s, String s1, String s2, PendingIntent pendingintent, PendingIntent pendingintent1, boolean flag)
    {
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException("Invalid destinationAddress");
        if(TextUtils.isEmpty(s2))
            throw new IllegalArgumentException("Invalid message body");
        getISmsServiceOrThrow().sendTextForSubscriber(getSubscriptionId(), ActivityThread.currentPackageName(), s, s1, s2, pendingintent, pendingintent1, flag);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void sendTextMessageInternal(String s, String s1, String s2, PendingIntent pendingintent, PendingIntent pendingintent1, boolean flag, int i, 
            boolean flag1, int j)
    {
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException("Invalid destinationAddress");
        if(TextUtils.isEmpty(s2))
            throw new IllegalArgumentException("Invalid message body");
        ISms isms = getISmsServiceOrThrow();
        if(isms == null)
            break MISSING_BLOCK_LABEL_75;
        isms.sendTextForSubscriberWithOptions(getSubscriptionId(), ActivityThread.currentPackageName(), s, s1, s2, pendingintent, pendingintent1, flag, i, flag1, j);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public Uri addMultimediaMessageDraft(Uri uri)
    {
        if(uri == null)
            throw new IllegalArgumentException("Uri contentUri null");
        IMms imms = com.android.internal.telephony.IMms.Stub.asInterface(ServiceManager.getService("imms"));
        if(imms == null)
            break MISSING_BLOCK_LABEL_43;
        uri = imms.addMultimediaMessageDraft(ActivityThread.currentPackageName(), uri);
        return uri;
        uri;
        return null;
    }

    public Uri addTextMessageDraft(String s, String s1)
    {
        IMms imms = com.android.internal.telephony.IMms.Stub.asInterface(ServiceManager.getService("imms"));
        if(imms == null)
            break MISSING_BLOCK_LABEL_29;
        s = imms.addTextMessageDraft(ActivityThread.currentPackageName(), s, s1);
        return s;
        s;
        return null;
    }

    public boolean archiveStoredConversation(long l, boolean flag)
    {
        IMms imms = com.android.internal.telephony.IMms.Stub.asInterface(ServiceManager.getService("imms"));
        if(imms == null)
            break MISSING_BLOCK_LABEL_33;
        flag = imms.archiveStoredConversation(ActivityThread.currentPackageName(), l, flag);
        return flag;
        RemoteException remoteexception;
        remoteexception;
        return false;
    }

    public boolean copyMessageToIcc(byte abyte0[], byte abyte1[], int i)
    {
        boolean flag;
        SeempLog.record(79);
        flag = false;
        if(abyte1 == null)
            throw new IllegalArgumentException("pdu is NULL");
        ISms isms = getISmsService();
        boolean flag1 = flag;
        if(isms != null)
            try
            {
                flag1 = isms.copyMessageToIccEfForSubscriber(getSubscriptionId(), ActivityThread.currentPackageName(), i, abyte1, abyte0);
            }
            // Misplaced declaration of an exception variable
            catch(byte abyte0[])
            {
                flag1 = flag;
            }
        return flag1;
    }

    public String createAppSpecificSmsToken(PendingIntent pendingintent)
    {
        try
        {
            pendingintent = getISmsServiceOrThrow().createAppSpecificSmsToken(getSubscriptionId(), ActivityThread.currentPackageName(), pendingintent);
        }
        // Misplaced declaration of an exception variable
        catch(PendingIntent pendingintent)
        {
            pendingintent.rethrowFromSystemServer();
            return null;
        }
        return pendingintent;
    }

    public boolean deleteMessageFromIcc(int i)
    {
        boolean flag;
        byte abyte0[];
        SeempLog.record(80);
        flag = false;
        abyte0 = new byte[175];
        Arrays.fill(abyte0, (byte)-1);
        ISms isms = getISmsService();
        boolean flag1 = flag;
        if(isms != null)
            try
            {
                flag1 = isms.updateMessageOnIccEfForSubscriber(getSubscriptionId(), ActivityThread.currentPackageName(), i, 0, abyte0);
            }
            catch(RemoteException remoteexception)
            {
                flag1 = flag;
            }
        return flag1;
    }

    public boolean deleteStoredConversation(long l)
    {
        IMms imms = com.android.internal.telephony.IMms.Stub.asInterface(ServiceManager.getService("imms"));
        if(imms == null)
            break MISSING_BLOCK_LABEL_30;
        boolean flag = imms.deleteStoredConversation(ActivityThread.currentPackageName(), l);
        return flag;
        RemoteException remoteexception;
        remoteexception;
        return false;
    }

    public boolean deleteStoredMessage(Uri uri)
    {
        if(uri == null)
            throw new IllegalArgumentException("Empty message URI");
        IMms imms = com.android.internal.telephony.IMms.Stub.asInterface(ServiceManager.getService("imms"));
        if(imms == null)
            break MISSING_BLOCK_LABEL_43;
        boolean flag = imms.deleteStoredMessage(ActivityThread.currentPackageName(), uri);
        return flag;
        uri;
        return false;
    }

    public boolean disableCellBroadcast(int i, int j)
    {
        boolean flag = false;
        ISms isms = getISmsService();
        boolean flag1 = flag;
        if(isms != null)
            try
            {
                flag1 = isms.disableCellBroadcastForSubscriber(getSubscriptionId(), i, j);
            }
            catch(RemoteException remoteexception)
            {
                flag1 = flag;
            }
        return flag1;
    }

    public boolean disableCellBroadcastRange(int i, int j, int k)
    {
        boolean flag;
        flag = false;
        if(j < i)
            throw new IllegalArgumentException("endMessageId < startMessageId");
        ISms isms = getISmsService();
        boolean flag1 = flag;
        if(isms != null)
            try
            {
                flag1 = isms.disableCellBroadcastRangeForSubscriber(getSubscriptionId(), i, j, k);
            }
            catch(RemoteException remoteexception)
            {
                flag1 = flag;
            }
        return flag1;
    }

    public ArrayList divideMessage(String s)
    {
        if(s == null)
            throw new IllegalArgumentException("text is null");
        else
            return SmsMessage.fragmentText(s);
    }

    public void downloadMultimediaMessage(Context context, String s, Uri uri, Bundle bundle, PendingIntent pendingintent)
    {
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException("Empty MMS location URL");
        if(uri == null)
            throw new IllegalArgumentException("Uri contentUri null");
        context = com.android.internal.telephony.IMms.Stub.asInterface(ServiceManager.getService("imms"));
        if(context == null)
            return;
        context.downloadMessage(getSubscriptionId(), ActivityThread.currentPackageName(), s, uri, bundle, pendingintent);
_L2:
        return;
        context;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean enableCellBroadcast(int i, int j)
    {
        boolean flag = false;
        ISms isms = getISmsService();
        boolean flag1 = flag;
        if(isms != null)
            try
            {
                flag1 = isms.enableCellBroadcastForSubscriber(getSubscriptionId(), i, j);
            }
            catch(RemoteException remoteexception)
            {
                flag1 = flag;
            }
        return flag1;
    }

    public boolean enableCellBroadcastRange(int i, int j, int k)
    {
        boolean flag;
        flag = false;
        if(j < i)
            throw new IllegalArgumentException("endMessageId < startMessageId");
        ISms isms = getISmsService();
        boolean flag1 = flag;
        if(isms != null)
            try
            {
                flag1 = isms.enableCellBroadcastRangeForSubscriber(getSubscriptionId(), i, j, k);
            }
            catch(RemoteException remoteexception)
            {
                flag1 = flag;
            }
        return flag1;
    }

    public ArrayList getAllMessagesFromIcc()
    {
        Object obj = null;
        ISms isms = getISmsService();
        Object obj1 = obj;
        if(isms != null)
            try
            {
                obj1 = isms.getAllMessagesFromIccEfForSubscriber(getSubscriptionId(), ActivityThread.currentPackageName());
            }
            catch(RemoteException remoteexception)
            {
                remoteexception = obj;
            }
        return createMessageListFromRawRecords(((List) (obj1)));
    }

    public boolean getAutoPersisting()
    {
        IMms imms = com.android.internal.telephony.IMms.Stub.asInterface(ServiceManager.getService("imms"));
        if(imms == null)
            break MISSING_BLOCK_LABEL_24;
        boolean flag = imms.getAutoPersisting();
        return flag;
        RemoteException remoteexception;
        remoteexception;
        return false;
    }

    public Bundle getCarrierConfigValues()
    {
        Object obj = com.android.internal.telephony.IMms.Stub.asInterface(ServiceManager.getService("imms"));
        if(obj == null)
            break MISSING_BLOCK_LABEL_28;
        obj = ((IMms) (obj)).getCarrierConfigValues(getSubscriptionId());
        return ((Bundle) (obj));
        RemoteException remoteexception;
        remoteexception;
        return null;
    }

    public String getImsSmsFormat()
    {
        String s = "unknown";
        ISms isms = getISmsService();
        Object obj = s;
        if(isms != null)
            try
            {
                obj = isms.getImsSmsFormatForSubscriber(getSubscriptionId());
            }
            catch(RemoteException remoteexception)
            {
                remoteexception = s;
            }
        return ((String) (obj));
    }

    public int getSmsCapacityOnIcc()
    {
        byte byte0 = -1;
        ISms isms = getISmsService();
        int i = byte0;
        if(isms != null)
            try
            {
                i = isms.getSmsCapacityOnIccForSubscriber(getSubscriptionId());
            }
            catch(RemoteException remoteexception)
            {
                i = byte0;
            }
        return i;
    }

    public int getSubscriptionId()
    {
        int i;
        Context context;
        if(mSubId == -1002)
            i = getDefaultSmsSubscriptionId();
        else
            i = mSubId;
        context = ActivityThread.currentApplication().getApplicationContext();
        try
        {
            getISmsService();
        }
        catch(RemoteException remoteexception)
        {
            Log.e("SmsManager", "Exception in getSubscriptionId");
        }
        if(false)
        {
            Log.d("SmsManager", "getSubscriptionId isSmsSimPickActivityNeeded is true");
            Intent intent = new Intent();
            intent.setClassName("com.qualcomm.qti.simsettings", "com.qualcomm.qti.simsettings.SimDialogActivity");
            intent.addFlags(0x10000000);
            intent.putExtra(DIALOG_TYPE_KEY, 2);
            try
            {
                context.startActivity(intent);
            }
            catch(ActivityNotFoundException activitynotfoundexception)
            {
                Log.e("SmsManager", "Unable to launch Settings application.");
            }
        }
        return i;
    }

    public Uri importMultimediaMessage(Uri uri, String s, long l, boolean flag, boolean flag1)
    {
        if(uri == null)
            throw new IllegalArgumentException("Uri contentUri null");
        IMms imms = com.android.internal.telephony.IMms.Stub.asInterface(ServiceManager.getService("imms"));
        if(imms == null)
            break MISSING_BLOCK_LABEL_52;
        uri = imms.importMultimediaMessage(ActivityThread.currentPackageName(), uri, s, l, flag, flag1);
        return uri;
        uri;
        return null;
    }

    public Uri importTextMessage(String s, int i, String s1, long l, boolean flag, boolean flag1)
    {
        IMms imms = com.android.internal.telephony.IMms.Stub.asInterface(ServiceManager.getService("imms"));
        if(imms == null)
            break MISSING_BLOCK_LABEL_39;
        s = imms.importTextMessage(ActivityThread.currentPackageName(), s, i, s1, l, flag, flag1);
        return s;
        s;
        return null;
    }

    public void injectSmsPdu(byte abyte0[], String s, PendingIntent pendingintent)
    {
        if(!s.equals("3gpp") && s.equals("3gpp2") ^ true)
            throw new IllegalArgumentException("Invalid pdu format. format must be either 3gpp or 3gpp2");
        ISms isms = com.android.internal.telephony.ISms.Stub.asInterface(ServiceManager.getService("isms"));
        if(isms == null)
            break MISSING_BLOCK_LABEL_62;
        isms.injectSmsPduForSubscriber(getSubscriptionId(), abyte0, s, pendingintent);
_L2:
        return;
        abyte0;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean isImsSmsSupported()
    {
        boolean flag = false;
        ISms isms = getISmsService();
        boolean flag1 = flag;
        if(isms != null)
            try
            {
                flag1 = isms.isImsSmsSupportedForSubscriber(getSubscriptionId());
            }
            catch(RemoteException remoteexception)
            {
                flag1 = flag;
            }
        return flag1;
    }

    public boolean isSMSPromptEnabled()
    {
        boolean flag;
        try
        {
            flag = com.android.internal.telephony.ISms.Stub.asInterface(ServiceManager.getService("isms")).isSMSPromptEnabled();
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

    public void sendDataMessage(String s, String s1, short word0, byte abyte0[], PendingIntent pendingintent, PendingIntent pendingintent1)
    {
        SeempLog.record_str(73, s);
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException("Invalid destinationAddress");
        if(abyte0 == null || abyte0.length == 0)
            throw new IllegalArgumentException("Invalid message data");
        getISmsServiceOrThrow().sendDataForSubscriber(getSubscriptionId(), ActivityThread.currentPackageName(), s, s1, word0 & 0xffff, abyte0, pendingintent, pendingintent1);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void sendDataMessageWithSelfPermissions(String s, String s1, short word0, byte abyte0[], PendingIntent pendingintent, PendingIntent pendingintent1)
    {
        SeempLog.record_str(73, s);
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException("Invalid destinationAddress");
        if(abyte0 == null || abyte0.length == 0)
            throw new IllegalArgumentException("Invalid message data");
        getISmsServiceOrThrow().sendDataForSubscriberWithSelfPermissions(getSubscriptionId(), ActivityThread.currentPackageName(), s, s1, word0 & 0xffff, abyte0, pendingintent, pendingintent1);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void sendMultimediaMessage(Context context, Uri uri, String s, Bundle bundle, PendingIntent pendingintent)
    {
        if(uri == null)
            throw new IllegalArgumentException("Uri contentUri null");
        context = com.android.internal.telephony.IMms.Stub.asInterface(ServiceManager.getService("imms"));
        if(context == null)
            return;
        context.sendMessage(getSubscriptionId(), ActivityThread.currentPackageName(), uri, s, bundle, pendingintent);
_L2:
        return;
        context;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void sendMultipartTextMessage(String s, String s1, ArrayList arraylist, ArrayList arraylist1, ArrayList arraylist2)
    {
        sendMultipartTextMessageInternal(s, s1, arraylist, arraylist1, arraylist2, true);
    }

    public void sendMultipartTextMessage(String s, String s1, ArrayList arraylist, ArrayList arraylist1, ArrayList arraylist2, int i, boolean flag, 
            int j)
    {
        sendMultipartTextMessageInternal(s, s1, arraylist, arraylist1, arraylist2, true);
    }

    public void sendMultipartTextMessageWithoutPersisting(String s, String s1, List list, List list1, List list2)
    {
        sendMultipartTextMessageInternal(s, s1, list, list1, list2, false);
    }

    public void sendMultipartTextMessageWithoutPersisting(String s, String s1, List list, List list1, List list2, int i, boolean flag, 
            int j)
    {
        sendMultipartTextMessageInternal(s, s1, list, list1, list2, false, i, flag, j);
    }

    public void sendStoredMultimediaMessage(Uri uri, Bundle bundle, PendingIntent pendingintent)
    {
        if(uri == null)
            throw new IllegalArgumentException("Empty message URI");
        IMms imms = com.android.internal.telephony.IMms.Stub.asInterface(ServiceManager.getService("imms"));
        if(imms == null)
            break MISSING_BLOCK_LABEL_48;
        imms.sendStoredMessage(getSubscriptionId(), ActivityThread.currentPackageName(), uri, bundle, pendingintent);
_L2:
        return;
        uri;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void sendStoredMultipartTextMessage(Uri uri, String s, ArrayList arraylist, ArrayList arraylist1)
    {
        if(uri == null)
            throw new IllegalArgumentException("Empty message URI");
        getISmsServiceOrThrow().sendStoredMultipartText(getSubscriptionId(), ActivityThread.currentPackageName(), uri, s, arraylist, arraylist1);
_L2:
        return;
        uri;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void sendStoredTextMessage(Uri uri, String s, PendingIntent pendingintent, PendingIntent pendingintent1)
    {
        if(uri == null)
            throw new IllegalArgumentException("Empty message URI");
        getISmsServiceOrThrow().sendStoredText(getSubscriptionId(), ActivityThread.currentPackageName(), uri, s, pendingintent, pendingintent1);
_L2:
        return;
        uri;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void sendTextMessage(String s, String s1, String s2, PendingIntent pendingintent, PendingIntent pendingintent1)
    {
        SeempLog.record_str(75, s);
        sendTextMessageInternal(s, s1, s2, pendingintent, pendingintent1, true);
    }

    public void sendTextMessage(String s, String s1, String s2, PendingIntent pendingintent, PendingIntent pendingintent1, int i, boolean flag, 
            int j)
    {
        sendTextMessageInternal(s, s1, s2, pendingintent, pendingintent1, true, i, flag, j);
    }

    public void sendTextMessageWithSelfPermissions(String s, String s1, String s2, PendingIntent pendingintent, PendingIntent pendingintent1, boolean flag)
    {
        SeempLog.record_str(75, s);
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException("Invalid destinationAddress");
        if(TextUtils.isEmpty(s2))
            throw new IllegalArgumentException("Invalid message body");
        getISmsServiceOrThrow().sendTextForSubscriberWithSelfPermissions(getSubscriptionId(), ActivityThread.currentPackageName(), s, s1, s2, pendingintent, pendingintent1, flag);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void sendTextMessageWithoutPersisting(String s, String s1, String s2, PendingIntent pendingintent, PendingIntent pendingintent1)
    {
        sendTextMessageInternal(s, s1, s2, pendingintent, pendingintent1, false);
    }

    public void sendTextMessageWithoutPersisting(String s, String s1, String s2, PendingIntent pendingintent, PendingIntent pendingintent1, int i, boolean flag, 
            int j)
    {
        sendTextMessageInternal(s, s1, s2, pendingintent, pendingintent1, false, i, flag, j);
    }

    public void setAutoPersisting(boolean flag)
    {
        IMms imms = com.android.internal.telephony.IMms.Stub.asInterface(ServiceManager.getService("imms"));
        if(imms == null)
            break MISSING_BLOCK_LABEL_24;
        imms.setAutoPersisting(ActivityThread.currentPackageName(), flag);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean updateMessageOnIcc(int i, int j, byte abyte0[])
    {
        boolean flag;
        SeempLog.record(81);
        flag = false;
        ISms isms = getISmsService();
        boolean flag1 = flag;
        if(isms != null)
            try
            {
                flag1 = isms.updateMessageOnIccEfForSubscriber(getSubscriptionId(), ActivityThread.currentPackageName(), i, j, abyte0);
            }
            // Misplaced declaration of an exception variable
            catch(byte abyte0[])
            {
                flag1 = flag;
            }
        return flag1;
    }

    public boolean updateStoredMessageStatus(Uri uri, ContentValues contentvalues)
    {
        if(uri == null)
            throw new IllegalArgumentException("Empty message URI");
        IMms imms = com.android.internal.telephony.IMms.Stub.asInterface(ServiceManager.getService("imms"));
        if(imms == null)
            break MISSING_BLOCK_LABEL_46;
        boolean flag = imms.updateStoredMessageStatus(ActivityThread.currentPackageName(), uri, contentvalues);
        return flag;
        uri;
        return false;
    }

    public static final int CDMA_SMS_RECORD_LENGTH = 255;
    public static final int CELL_BROADCAST_RAN_TYPE_CDMA = 1;
    public static final int CELL_BROADCAST_RAN_TYPE_GSM = 0;
    private static final int DEFAULT_SUBSCRIPTION_ID = -1002;
    private static String DIALOG_TYPE_KEY = "dialog_type";
    public static final String EXTRA_MMS_DATA = "android.telephony.extra.MMS_DATA";
    public static final String EXTRA_MMS_HTTP_STATUS = "android.telephony.extra.MMS_HTTP_STATUS";
    public static final String MESSAGE_STATUS_READ = "read";
    public static final String MESSAGE_STATUS_SEEN = "seen";
    public static final String MMS_CONFIG_ALIAS_ENABLED = "aliasEnabled";
    public static final String MMS_CONFIG_ALIAS_MAX_CHARS = "aliasMaxChars";
    public static final String MMS_CONFIG_ALIAS_MIN_CHARS = "aliasMinChars";
    public static final String MMS_CONFIG_ALLOW_ATTACH_AUDIO = "allowAttachAudio";
    public static final String MMS_CONFIG_APPEND_TRANSACTION_ID = "enabledTransID";
    public static final String MMS_CONFIG_CLOSE_CONNECTION = "mmsCloseConnection";
    public static final String MMS_CONFIG_EMAIL_GATEWAY_NUMBER = "emailGatewayNumber";
    public static final String MMS_CONFIG_GROUP_MMS_ENABLED = "enableGroupMms";
    public static final String MMS_CONFIG_HTTP_PARAMS = "httpParams";
    public static final String MMS_CONFIG_HTTP_SOCKET_TIMEOUT = "httpSocketTimeout";
    public static final String MMS_CONFIG_MAX_IMAGE_HEIGHT = "maxImageHeight";
    public static final String MMS_CONFIG_MAX_IMAGE_WIDTH = "maxImageWidth";
    public static final String MMS_CONFIG_MAX_MESSAGE_SIZE = "maxMessageSize";
    public static final String MMS_CONFIG_MESSAGE_TEXT_MAX_SIZE = "maxMessageTextSize";
    public static final String MMS_CONFIG_MMS_DELIVERY_REPORT_ENABLED = "enableMMSDeliveryReports";
    public static final String MMS_CONFIG_MMS_ENABLED = "enabledMMS";
    public static final String MMS_CONFIG_MMS_READ_REPORT_ENABLED = "enableMMSReadReports";
    public static final String MMS_CONFIG_MULTIPART_SMS_ENABLED = "enableMultipartSMS";
    public static final String MMS_CONFIG_NAI_SUFFIX = "naiSuffix";
    public static final String MMS_CONFIG_NOTIFY_WAP_MMSC_ENABLED = "enabledNotifyWapMMSC";
    public static final String MMS_CONFIG_RECIPIENT_LIMIT = "recipientLimit";
    public static final String MMS_CONFIG_SEND_MULTIPART_SMS_AS_SEPARATE_MESSAGES = "sendMultipartSmsAsSeparateMessages";
    public static final String MMS_CONFIG_SHOW_CELL_BROADCAST_APP_LINKS = "config_cellBroadcastAppLinks";
    public static final String MMS_CONFIG_SMS_DELIVERY_REPORT_ENABLED = "enableSMSDeliveryReports";
    public static final String MMS_CONFIG_SMS_TO_MMS_TEXT_LENGTH_THRESHOLD = "smsToMmsTextLengthThreshold";
    public static final String MMS_CONFIG_SMS_TO_MMS_TEXT_THRESHOLD = "smsToMmsTextThreshold";
    public static final String MMS_CONFIG_SUBJECT_MAX_LENGTH = "maxSubjectLength";
    public static final String MMS_CONFIG_SUPPORT_HTTP_CHARSET_HEADER = "supportHttpCharsetHeader";
    public static final String MMS_CONFIG_SUPPORT_MMS_CONTENT_DISPOSITION = "supportMmsContentDisposition";
    public static final String MMS_CONFIG_UA_PROF_TAG_NAME = "uaProfTagName";
    public static final String MMS_CONFIG_UA_PROF_URL = "uaProfUrl";
    public static final String MMS_CONFIG_USER_AGENT = "userAgent";
    public static final int MMS_ERROR_CONFIGURATION_ERROR = 7;
    public static final int MMS_ERROR_HTTP_FAILURE = 4;
    public static final int MMS_ERROR_INVALID_APN = 2;
    public static final int MMS_ERROR_IO_ERROR = 5;
    public static final int MMS_ERROR_NO_DATA_NETWORK = 8;
    public static final int MMS_ERROR_RETRY = 6;
    public static final int MMS_ERROR_UNABLE_CONNECT_MMS = 3;
    public static final int MMS_ERROR_UNSPECIFIED = 1;
    private static final String PHONE_PACKAGE_NAME = "com.android.phone";
    public static final int RESULT_ERROR_FDN_CHECK_FAILURE = 6;
    public static final int RESULT_ERROR_GENERIC_FAILURE = 1;
    public static final int RESULT_ERROR_LIMIT_EXCEEDED = 5;
    public static final int RESULT_ERROR_NO_SERVICE = 4;
    public static final int RESULT_ERROR_NULL_PDU = 3;
    public static final int RESULT_ERROR_RADIO_OFF = 2;
    public static final int RESULT_ERROR_SHORT_CODE_NEVER_ALLOWED = 8;
    public static final int RESULT_ERROR_SHORT_CODE_NOT_ALLOWED = 7;
    private static final int SMS_PICK = 2;
    public static final int SMS_RECORD_LENGTH = 176;
    public static final int SMS_TYPE_INCOMING = 0;
    public static final int SMS_TYPE_OUTGOING = 1;
    public static final int STATUS_ON_ICC_FREE = 0;
    public static final int STATUS_ON_ICC_READ = 1;
    public static final int STATUS_ON_ICC_SENT = 5;
    public static final int STATUS_ON_ICC_UNREAD = 3;
    public static final int STATUS_ON_ICC_UNSENT = 7;
    private static final String TAG = "SmsManager";
    private static final SmsManager sInstance = new SmsManager(-1002);
    private static final Object sLockObject = new Object();
    private static final Map sSubInstances = new ArrayMap();
    private int mSubId;

}
