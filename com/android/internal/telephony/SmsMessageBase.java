// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony;

import android.telephony.SmsMessage;
import android.text.Emoji;
import java.text.BreakIterator;
import java.util.Arrays;

// Referenced classes of package com.android.internal.telephony:
//            SmsAddress, SmsHeader

public abstract class SmsMessageBase
{
    public static abstract class SubmitPduBase
    {

        public String toString()
        {
            return (new StringBuilder()).append("SubmitPdu: encodedScAddress = ").append(Arrays.toString(encodedScAddress)).append(", encodedMessage = ").append(Arrays.toString(encodedMessage)).toString();
        }

        public byte encodedMessage[];
        public byte encodedScAddress[];

        public SubmitPduBase()
        {
        }
    }


    public SmsMessageBase()
    {
        mStatusOnIcc = -1;
        mIndexOnIcc = -1;
    }

    public static GsmAlphabet.TextEncodingDetails calcUnicodeEncodingDetails(CharSequence charsequence)
    {
        GsmAlphabet.TextEncodingDetails textencodingdetails = new GsmAlphabet.TextEncodingDetails();
        int i = charsequence.length() * 2;
        textencodingdetails.codeUnitSize = 3;
        textencodingdetails.codeUnitCount = charsequence.length();
        if(i > 140)
        {
            int j = 134;
            char c = j;
            if(!SmsMessage.hasEmsSupport())
            {
                c = j;
                if(i <= 1188)
                    c = '\204';
            }
            i = 0;
            for(j = 0; i < charsequence.length(); j++)
            {
                int k = findNextUnicodePosition(i, c, charsequence);
                if(k == charsequence.length())
                    textencodingdetails.codeUnitsRemaining = (c / 2 + i) - charsequence.length();
                i = k;
            }

            textencodingdetails.msgCount = j;
        } else
        {
            textencodingdetails.msgCount = 1;
            textencodingdetails.codeUnitsRemaining = (140 - i) / 2;
        }
        return textencodingdetails;
    }

    public static int findNextUnicodePosition(int i, int j, CharSequence charsequence)
    {
        int k;
        k = Math.min(j / 2 + i, charsequence.length());
        j = k;
        if(k >= charsequence.length()) goto _L2; else goto _L1
_L1:
        BreakIterator breakiterator;
        breakiterator = BreakIterator.getCharacterInstance();
        breakiterator.setText(charsequence.toString());
        j = k;
        if(breakiterator.isBoundary(k)) goto _L2; else goto _L3
_L3:
        for(j = breakiterator.preceding(k); j + 4 <= k && Emoji.isRegionalIndicatorSymbol(Character.codePointAt(charsequence, j)) && Emoji.isRegionalIndicatorSymbol(Character.codePointAt(charsequence, j + 2)); j += 4);
        if(j <= i) goto _L4; else goto _L2
_L2:
        return j;
_L4:
        j = k;
        if(Character.isHighSurrogate(charsequence.charAt(k - 1)))
            j = k - 1;
        if(true) goto _L2; else goto _L5
_L5:
    }

    protected void extractEmailAddressFromMessageBody()
    {
        String as[] = mMessageBody.split("( /)|( )", 2);
        if(as.length < 2)
        {
            return;
        } else
        {
            mEmailFrom = as[0];
            mEmailBody = as[1];
            mIsEmail = android.provider.Telephony.Mms.isEmailAddress(mEmailFrom);
            return;
        }
    }

    public String getDisplayMessageBody()
    {
        if(mIsEmail)
            return mEmailBody;
        else
            return getMessageBody();
    }

    public String getDisplayOriginatingAddress()
    {
        if(mIsEmail)
            return mEmailFrom;
        else
            return getOriginatingAddress();
    }

    public String getEmailBody()
    {
        return mEmailBody;
    }

    public String getEmailFrom()
    {
        return mEmailFrom;
    }

    public int getEncodingType()
    {
        return 0;
    }

    public int getIndexOnIcc()
    {
        return mIndexOnIcc;
    }

    public String getMessageBody()
    {
        return mMessageBody;
    }

    public abstract SmsConstants.MessageClass getMessageClass();

    public String getOriginatingAddress()
    {
        if(mOriginatingAddress == null)
            return null;
        else
            return mOriginatingAddress.getAddressString();
    }

    public byte[] getPdu()
    {
        return mPdu;
    }

    public abstract int getProtocolIdentifier();

    public String getPseudoSubject()
    {
        String s;
        if(mPseudoSubject == null)
            s = "";
        else
            s = mPseudoSubject;
        return s;
    }

    public String getRecipientAddress()
    {
        if(mRecipientAddress == null)
            return null;
        else
            return mRecipientAddress.getAddressString();
    }

    public String getServiceCenterAddress()
    {
        return mScAddress;
    }

    public abstract int getStatus();

    public int getStatusOnIcc()
    {
        return mStatusOnIcc;
    }

    public long getTimestampMillis()
    {
        return mScTimeMillis;
    }

    public byte[] getUserData()
    {
        return mUserData;
    }

    public SmsHeader getUserDataHeader()
    {
        return mUserDataHeader;
    }

    public abstract boolean isCphsMwiMessage();

    public boolean isEmail()
    {
        return mIsEmail;
    }

    public abstract boolean isMWIClearMessage();

    public abstract boolean isMWISetMessage();

    public abstract boolean isMwiDontStore();

    public abstract boolean isReplace();

    public abstract boolean isReplyPathPresent();

    public abstract boolean isStatusReportMessage();

    protected void parseMessageBody()
    {
        if(mOriginatingAddress != null && mOriginatingAddress.couldBeEmailGateway())
            extractEmailAddressFromMessageBody();
    }

    protected String mEmailBody;
    protected String mEmailFrom;
    protected int mIndexOnIcc;
    protected boolean mIsEmail;
    protected boolean mIsMwi;
    protected String mMessageBody;
    public int mMessageRef;
    protected boolean mMwiDontStore;
    protected boolean mMwiSense;
    protected SmsAddress mOriginatingAddress;
    protected byte mPdu[];
    protected String mPseudoSubject;
    protected SmsAddress mRecipientAddress;
    protected String mScAddress;
    protected long mScTimeMillis;
    protected int mStatusOnIcc;
    protected byte mUserData[];
    protected SmsHeader mUserDataHeader;
}
