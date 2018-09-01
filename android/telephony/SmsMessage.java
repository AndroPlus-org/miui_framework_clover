// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.content.res.Resources;
import android.os.Binder;
import android.text.TextUtils;
import com.android.internal.telephony.GsmAlphabet;
import com.android.internal.telephony.Sms7BitEncodingTranslator;
import com.android.internal.telephony.SmsMessageBase;
import java.util.ArrayList;
import java.util.Arrays;

// Referenced classes of package android.telephony:
//            Rlog, TelephonyManager, SubscriptionManager, SmsManager

public class SmsMessage
{
    public static final class MessageClass extends Enum
    {

        public static MessageClass valueOf(String s)
        {
            return (MessageClass)Enum.valueOf(android/telephony/SmsMessage$MessageClass, s);
        }

        public static MessageClass[] values()
        {
            return $VALUES;
        }

        private static final MessageClass $VALUES[];
        public static final MessageClass CLASS_0;
        public static final MessageClass CLASS_1;
        public static final MessageClass CLASS_2;
        public static final MessageClass CLASS_3;
        public static final MessageClass UNKNOWN;

        static 
        {
            UNKNOWN = new MessageClass("UNKNOWN", 0);
            CLASS_0 = new MessageClass("CLASS_0", 1);
            CLASS_1 = new MessageClass("CLASS_1", 2);
            CLASS_2 = new MessageClass("CLASS_2", 3);
            CLASS_3 = new MessageClass("CLASS_3", 4);
            $VALUES = (new MessageClass[] {
                UNKNOWN, CLASS_0, CLASS_1, CLASS_2, CLASS_3
            });
        }

        private MessageClass(String s, int i)
        {
            super(s, i);
        }
    }

    private static class NoEmsSupportConfig
    {

        public String toString()
        {
            return (new StringBuilder()).append("NoEmsSupportConfig { mOperatorNumber = ").append(mOperatorNumber).append(", mIsPrefix = ").append(mIsPrefix).append(", mGid1 = ").append(mGid1).append(" }").toString();
        }

        String mGid1;
        boolean mIsPrefix;
        String mOperatorNumber;

        public NoEmsSupportConfig(String as[])
        {
            mOperatorNumber = as[0];
            mIsPrefix = "prefix".equals(as[1]);
            if(as.length > 2)
                as = as[2];
            else
                as = null;
            mGid1 = as;
        }
    }

    public static class SubmitPdu
    {

        public String toString()
        {
            return (new StringBuilder()).append("SubmitPdu: encodedScAddress = ").append(Arrays.toString(encodedScAddress)).append(", encodedMessage = ").append(Arrays.toString(encodedMessage)).toString();
        }

        public byte encodedMessage[];
        public byte encodedScAddress[];

        protected SubmitPdu(com.android.internal.telephony.SmsMessageBase.SubmitPduBase submitpdubase)
        {
            encodedMessage = submitpdubase.encodedMessage;
            encodedScAddress = submitpdubase.encodedScAddress;
        }
    }


    private static int[] _2D_getcom_2D_android_2D_internal_2D_telephony_2D_SmsConstants$MessageClassSwitchesValues()
    {
        if(_2D_com_2D_android_2D_internal_2D_telephony_2D_SmsConstants$MessageClassSwitchesValues != null)
            return _2D_com_2D_android_2D_internal_2D_telephony_2D_SmsConstants$MessageClassSwitchesValues;
        int ai[] = new int[com.android.internal.telephony.SmsConstants.MessageClass.values().length];
        try
        {
            ai[com.android.internal.telephony.SmsConstants.MessageClass.CLASS_0.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror4) { }
        try
        {
            ai[com.android.internal.telephony.SmsConstants.MessageClass.CLASS_1.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror3) { }
        try
        {
            ai[com.android.internal.telephony.SmsConstants.MessageClass.CLASS_2.ordinal()] = 3;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            ai[com.android.internal.telephony.SmsConstants.MessageClass.CLASS_3.ordinal()] = 4;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            ai[com.android.internal.telephony.SmsConstants.MessageClass.UNKNOWN.ordinal()] = 5;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        _2D_com_2D_android_2D_internal_2D_telephony_2D_SmsConstants$MessageClassSwitchesValues = ai;
        return ai;
    }

    public SmsMessage(SmsMessageBase smsmessagebase)
    {
        mSubId = 0;
        mWrappedSmsMessage = smsmessagebase;
    }

    public static int[] calculateLength(CharSequence charsequence, boolean flag)
    {
        if(useCdmaFormatForMoSms())
            charsequence = com.android.internal.telephony.cdma.SmsMessage.calculateLength(charsequence, flag, true);
        else
            charsequence = com.android.internal.telephony.gsm.SmsMessage.calculateLength(charsequence, flag);
        return (new int[] {
            ((com.android.internal.telephony.GsmAlphabet.TextEncodingDetails) (charsequence)).msgCount, ((com.android.internal.telephony.GsmAlphabet.TextEncodingDetails) (charsequence)).codeUnitCount, ((com.android.internal.telephony.GsmAlphabet.TextEncodingDetails) (charsequence)).codeUnitsRemaining, ((com.android.internal.telephony.GsmAlphabet.TextEncodingDetails) (charsequence)).codeUnitSize
        });
    }

    public static int[] calculateLength(String s, boolean flag)
    {
        return calculateLength(((CharSequence) (s)), flag);
    }

    public static SmsMessage createFromEfRecord(int i, byte abyte0[])
    {
        if(isCdmaVoice())
            abyte0 = com.android.internal.telephony.cdma.SmsMessage.createFromEfRecord(i, abyte0);
        else
            abyte0 = com.android.internal.telephony.gsm.SmsMessage.createFromEfRecord(i, abyte0);
        if(abyte0 != null)
        {
            return new SmsMessage(abyte0);
        } else
        {
            Rlog.e("SmsMessage", "createFromEfRecord(): wrappedMessage is null");
            return null;
        }
    }

    public static SmsMessage createFromEfRecord(int i, byte abyte0[], int j)
    {
        SmsMessage smsmessage = null;
        if(isCdmaVoice(j))
            abyte0 = com.android.internal.telephony.cdma.SmsMessage.createFromEfRecord(i, abyte0);
        else
            abyte0 = com.android.internal.telephony.gsm.SmsMessage.createFromEfRecord(i, abyte0);
        if(abyte0 != null)
            smsmessage = new SmsMessage(abyte0);
        return smsmessage;
    }

    public static SmsMessage createFromPdu(byte abyte0[])
    {
label0:
        {
            int i = TelephonyManager.getDefault().getCurrentPhoneType();
            Object obj;
            SmsMessage smsmessage;
            if(2 == i)
                obj = "3gpp2";
            else
                obj = "3gpp";
            smsmessage = createFromPdu(abyte0, ((String) (obj)));
            if(smsmessage != null)
            {
                obj = smsmessage;
                if(smsmessage.mWrappedSmsMessage != null)
                    break label0;
            }
            if(2 == i)
                obj = "3gpp";
            else
                obj = "3gpp2";
            obj = createFromPdu(abyte0, ((String) (obj)));
        }
        return ((SmsMessage) (obj));
    }

    public static SmsMessage createFromPdu(byte abyte0[], String s)
    {
        if("3gpp2".equals(s))
            abyte0 = com.android.internal.telephony.cdma.SmsMessage.createFromPdu(abyte0);
        else
        if("3gpp".equals(s))
        {
            abyte0 = com.android.internal.telephony.gsm.SmsMessage.createFromPdu(abyte0);
        } else
        {
            Rlog.e("SmsMessage", (new StringBuilder()).append("createFromPdu(): unsupported message format ").append(s).toString());
            return null;
        }
        if(abyte0 != null)
        {
            return new SmsMessage(abyte0);
        } else
        {
            Rlog.e("SmsMessage", "createFromPdu(): wrappedMessage is null");
            return null;
        }
    }

    public static ArrayList fragmentText(String s)
    {
        com.android.internal.telephony.GsmAlphabet.TextEncodingDetails textencodingdetails;
        int i;
        String s2;
        int l;
        if(useCdmaFormatForMoSms())
            textencodingdetails = com.android.internal.telephony.cdma.SmsMessage.calculateLength(s, false, true);
        else
            textencodingdetails = com.android.internal.telephony.gsm.SmsMessage.calculateLength(s, false);
        if(textencodingdetails.codeUnitSize == 1)
        {
            int j;
            String s1;
            int i1;
            if(textencodingdetails.languageTable != 0 && textencodingdetails.languageShiftTable != 0)
                i = 7;
            else
            if(textencodingdetails.languageTable != 0 || textencodingdetails.languageShiftTable != 0)
                i = 4;
            else
                i = 0;
            j = i;
            if(textencodingdetails.msgCount > 1)
                j = i + 6;
            i = j;
            if(j != 0)
                i = j + 1;
            i = 160 - i;
        } else
        if(textencodingdetails.msgCount > 1)
        {
            k = 134;
            i = k;
            if(!hasEmsSupport())
            {
                i = k;
                if(textencodingdetails.msgCount < 10)
                    i = 132;
            }
        } else
        {
            i = 140;
        }
        s1 = null;
        if(Resources.getSystem().getBoolean(0x11200a2))
            s1 = Sms7BitEncodingTranslator.translate(s);
        s2 = s1;
        if(TextUtils.isEmpty(s1))
            s2 = s;
        l = 0;
        i1 = s2.length();
        s = new ArrayList(textencodingdetails.msgCount);
        do
        {
            int k;
label0:
            {
                if(l < i1)
                {
                    if(textencodingdetails.codeUnitSize == 1)
                    {
                        if(useCdmaFormatForMoSms() && textencodingdetails.msgCount == 1)
                            k = l + Math.min(i, i1 - l);
                        else
                            k = GsmAlphabet.findGsmSeptetLimitIndex(s2, l, i, textencodingdetails.languageTable, textencodingdetails.languageShiftTable);
                    } else
                    {
                        k = SmsMessageBase.findNextUnicodePosition(l, i, s2);
                    }
                    if(k > l && k <= i1)
                        break label0;
                    Rlog.e("SmsMessage", (new StringBuilder()).append("fragmentText failed (").append(l).append(" >= ").append(k).append(" or ").append(k).append(" >= ").append(i1).append(")").toString());
                }
                return s;
            }
            s.add(s2.substring(l, k));
            l = k;
        } while(true);
    }

    public static SubmitPdu getSubmitPdu(String s, String s1, String s2, boolean flag)
    {
        return getSubmitPdu(s, s1, s2, flag, SubscriptionManager.getDefaultSmsSubscriptionId());
    }

    public static SubmitPdu getSubmitPdu(String s, String s1, String s2, boolean flag, int i)
    {
        if(useCdmaFormatForMoSms(i))
            s = com.android.internal.telephony.cdma.SmsMessage.getSubmitPdu(s, s1, s2, flag, null);
        else
            s = com.android.internal.telephony.gsm.SmsMessage.getSubmitPdu(s, s1, s2, flag);
        return new SubmitPdu(s);
    }

    public static SubmitPdu getSubmitPdu(String s, String s1, short word0, byte abyte0[], boolean flag)
    {
        if(useCdmaFormatForMoSms())
            s = com.android.internal.telephony.cdma.SmsMessage.getSubmitPdu(s, s1, word0, abyte0, flag);
        else
            s = com.android.internal.telephony.gsm.SmsMessage.getSubmitPdu(s, s1, word0, abyte0, flag);
        return new SubmitPdu(s);
    }

    public static int getTPLayerLengthForPDU(String s)
    {
        if(isCdmaVoice())
            return com.android.internal.telephony.cdma.SmsMessage.getTPLayerLengthForPDU(s);
        else
            return com.android.internal.telephony.gsm.SmsMessage.getTPLayerLengthForPDU(s);
    }

    public static boolean hasEmsSupport()
    {
        long l;
        if(!isNoEmsSupportConfigListExisted())
            return true;
        l = Binder.clearCallingIdentity();
        String s;
        String s1;
        s = TelephonyManager.getDefault().getSimOperatorNumeric();
        s1 = TelephonyManager.getDefault().getGroupIdLevel1();
        Binder.restoreCallingIdentity(l);
        if(TextUtils.isEmpty(s)) goto _L2; else goto _L1
_L1:
        NoEmsSupportConfig anoemssupportconfig[];
        int i;
        int j;
        anoemssupportconfig = mNoEmsSupportConfigList;
        i = anoemssupportconfig.length;
        j = 0;
_L3:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        NoEmsSupportConfig noemssupportconfig = anoemssupportconfig[j];
        if(s.startsWith(noemssupportconfig.mOperatorNumber) && (TextUtils.isEmpty(noemssupportconfig.mGid1) || !TextUtils.isEmpty(noemssupportconfig.mGid1) && noemssupportconfig.mGid1.equalsIgnoreCase(s1)))
            return false;
        break MISSING_BLOCK_LABEL_121;
        Exception exception;
        exception;
        Binder.restoreCallingIdentity(l);
        throw exception;
        j++;
        if(true) goto _L3; else goto _L2
_L2:
        return true;
    }

    private static boolean isCdmaVoice()
    {
        return isCdmaVoice(SubscriptionManager.getDefaultSmsSubscriptionId());
    }

    private static boolean isCdmaVoice(int i)
    {
        boolean flag;
        if(2 == TelephonyManager.getDefault().getCurrentPhoneType(i))
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static boolean isNoEmsSupportConfigListExisted()
    {
        if(!mIsNoEmsSupportConfigListLoaded)
        {
            Resources resources = Resources.getSystem();
            if(resources != null)
            {
                String as[] = resources.getStringArray(0x107005c);
                if(as != null && as.length > 0)
                {
                    mNoEmsSupportConfigList = new NoEmsSupportConfig[as.length];
                    for(int i = 0; i < as.length; i++)
                        mNoEmsSupportConfigList[i] = new NoEmsSupportConfig(as[i].split(";"));

                }
                mIsNoEmsSupportConfigListLoaded = true;
            }
        }
        return mNoEmsSupportConfigList != null && mNoEmsSupportConfigList.length != 0;
    }

    public static SmsMessage newFromCMT(byte abyte0[])
    {
        abyte0 = com.android.internal.telephony.gsm.SmsMessage.newFromCMT(abyte0);
        if(abyte0 != null)
        {
            return new SmsMessage(abyte0);
        } else
        {
            Rlog.e("SmsMessage", "newFromCMT(): wrappedMessage is null");
            return null;
        }
    }

    public static boolean shouldAppendPageNumberAsPrefix()
    {
        long l;
        if(!isNoEmsSupportConfigListExisted())
            return false;
        l = Binder.clearCallingIdentity();
        String s;
        String s1;
        s = TelephonyManager.getDefault().getSimOperatorNumeric();
        s1 = TelephonyManager.getDefault().getGroupIdLevel1();
        NoEmsSupportConfig anoemssupportconfig[];
        int i;
        int j;
        Binder.restoreCallingIdentity(l);
        anoemssupportconfig = mNoEmsSupportConfigList;
        i = anoemssupportconfig.length;
        j = 0;
_L2:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        NoEmsSupportConfig noemssupportconfig = anoemssupportconfig[j];
        if(s.startsWith(noemssupportconfig.mOperatorNumber) && (TextUtils.isEmpty(noemssupportconfig.mGid1) || !TextUtils.isEmpty(noemssupportconfig.mGid1) && noemssupportconfig.mGid1.equalsIgnoreCase(s1)))
            return noemssupportconfig.mIsPrefix;
        break MISSING_BLOCK_LABEL_118;
        Exception exception;
        exception;
        Binder.restoreCallingIdentity(l);
        throw exception;
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        return false;
    }

    private static boolean useCdmaFormatForMoSms()
    {
        return useCdmaFormatForMoSms(SubscriptionManager.getDefaultSmsSubscriptionId());
    }

    private static boolean useCdmaFormatForMoSms(int i)
    {
        SmsManager smsmanager = SmsManager.getSmsManagerForSubscriptionId(i);
        if(!smsmanager.isImsSmsSupported())
            return isCdmaVoice(i);
        else
            return "3gpp2".equals(smsmanager.getImsSmsFormat());
    }

    public String getDisplayMessageBody()
    {
        return mWrappedSmsMessage.getDisplayMessageBody();
    }

    public String getDisplayOriginatingAddress()
    {
        return mWrappedSmsMessage.getDisplayOriginatingAddress();
    }

    public String getEmailBody()
    {
        return mWrappedSmsMessage.getEmailBody();
    }

    public String getEmailFrom()
    {
        return mWrappedSmsMessage.getEmailFrom();
    }

    public int getEncodingType()
    {
        return mWrappedSmsMessage.getEncodingType();
    }

    public int getIndexOnIcc()
    {
        return mWrappedSmsMessage.getIndexOnIcc();
    }

    public int getIndexOnSim()
    {
        return mWrappedSmsMessage.getIndexOnIcc();
    }

    public String getMessageBody()
    {
        return mWrappedSmsMessage.getMessageBody();
    }

    public MessageClass getMessageClass()
    {
        switch(_2D_getcom_2D_android_2D_internal_2D_telephony_2D_SmsConstants$MessageClassSwitchesValues()[mWrappedSmsMessage.getMessageClass().ordinal()])
        {
        default:
            return MessageClass.UNKNOWN;

        case 1: // '\001'
            return MessageClass.CLASS_0;

        case 2: // '\002'
            return MessageClass.CLASS_1;

        case 3: // '\003'
            return MessageClass.CLASS_2;

        case 4: // '\004'
            return MessageClass.CLASS_3;
        }
    }

    public String getOriginatingAddress()
    {
        return mWrappedSmsMessage.getOriginatingAddress();
    }

    public byte[] getPdu()
    {
        return mWrappedSmsMessage.getPdu();
    }

    public int getProtocolIdentifier()
    {
        return mWrappedSmsMessage.getProtocolIdentifier();
    }

    public String getPseudoSubject()
    {
        return mWrappedSmsMessage.getPseudoSubject();
    }

    public String getRecipientAddress()
    {
        return mWrappedSmsMessage.getRecipientAddress();
    }

    public String getServiceCenterAddress()
    {
        return mWrappedSmsMessage.getServiceCenterAddress();
    }

    public int getStatus()
    {
        return mWrappedSmsMessage.getStatus();
    }

    public int getStatusOnIcc()
    {
        return mWrappedSmsMessage.getStatusOnIcc();
    }

    public int getStatusOnSim()
    {
        return mWrappedSmsMessage.getStatusOnIcc();
    }

    public int getSubId()
    {
        return mSubId;
    }

    public long getTimestampMillis()
    {
        return mWrappedSmsMessage.getTimestampMillis();
    }

    public byte[] getUserData()
    {
        return mWrappedSmsMessage.getUserData();
    }

    public boolean isCphsMwiMessage()
    {
        return mWrappedSmsMessage.isCphsMwiMessage();
    }

    public boolean isEmail()
    {
        return mWrappedSmsMessage.isEmail();
    }

    public boolean isMWIClearMessage()
    {
        return mWrappedSmsMessage.isMWIClearMessage();
    }

    public boolean isMWISetMessage()
    {
        return mWrappedSmsMessage.isMWISetMessage();
    }

    public boolean isMwiDontStore()
    {
        return mWrappedSmsMessage.isMwiDontStore();
    }

    public boolean isReplace()
    {
        return mWrappedSmsMessage.isReplace();
    }

    public boolean isReplyPathPresent()
    {
        return mWrappedSmsMessage.isReplyPathPresent();
    }

    public boolean isStatusReportMessage()
    {
        return mWrappedSmsMessage.isStatusReportMessage();
    }

    public void setSubId(int i)
    {
        mSubId = i;
    }

    private static final int _2D_com_2D_android_2D_internal_2D_telephony_2D_SmsConstants$MessageClassSwitchesValues[];
    public static final int ENCODING_16BIT = 3;
    public static final int ENCODING_7BIT = 1;
    public static final int ENCODING_8BIT = 2;
    public static final int ENCODING_KSC5601 = 4;
    public static final int ENCODING_UNKNOWN = 0;
    public static final String FORMAT_3GPP = "3gpp";
    public static final String FORMAT_3GPP2 = "3gpp2";
    private static final String LOG_TAG = "SmsMessage";
    public static final int MAX_USER_DATA_BYTES = 140;
    public static final int MAX_USER_DATA_BYTES_WITH_HEADER = 134;
    public static final int MAX_USER_DATA_SEPTETS = 160;
    public static final int MAX_USER_DATA_SEPTETS_WITH_HEADER = 153;
    private static boolean mIsNoEmsSupportConfigListLoaded = false;
    private static NoEmsSupportConfig mNoEmsSupportConfigList[] = null;
    private int mSubId;
    public SmsMessageBase mWrappedSmsMessage;

}
