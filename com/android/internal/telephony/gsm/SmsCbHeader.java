// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony.gsm;

import android.telephony.SmsCbCmasInfo;
import android.telephony.SmsCbEtwsInfo;
import java.util.Arrays;

public class SmsCbHeader
{

    public SmsCbHeader(byte abyte0[])
        throws IllegalArgumentException
    {
        if(abyte0 == null || abyte0.length < 6)
            throw new IllegalArgumentException("Illegal PDU");
        if(abyte0.length > 88) goto _L2; else goto _L1
_L1:
        int k;
        int i1;
        mGeographicalScope = (abyte0[0] & 0xc0) >>> 6;
        mSerialNumber = (abyte0[0] & 0xff) << 8 | abyte0[1] & 0xff;
        mMessageIdentifier = (abyte0[2] & 0xff) << 8 | abyte0[3] & 0xff;
        if(isEtwsMessage() && abyte0.length <= 56)
        {
            mFormat = 3;
            mDataCodingScheme = -1;
            mPageIndex = -1;
            mNrOfPages = -1;
            boolean flag;
            boolean flag2;
            byte byte0;
            if((abyte0[4] & 1) != 0)
                flag = true;
            else
                flag = false;
            if((abyte0[5] & 0x80) != 0)
                flag2 = true;
            else
                flag2 = false;
            byte0 = abyte0[4];
            if(abyte0.length > 6)
                abyte0 = Arrays.copyOfRange(abyte0, 6, abyte0.length);
            else
                abyte0 = null;
            mEtwsInfo = new SmsCbEtwsInfo((byte0 & 0xfe) >>> 1, flag, flag2, true, abyte0);
            mCmasInfo = null;
            return;
        }
        mFormat = 1;
        mDataCodingScheme = abyte0[4] & 0xff;
        k = (abyte0[5] & 0xf0) >>> 4;
        i1 = abyte0[5] & 0xf;
        if(k != 0 && i1 != 0) goto _L4; else goto _L3
_L3:
        int i;
        int k1;
        i = 1;
        k1 = 1;
_L5:
        mPageIndex = i;
        mNrOfPages = k1;
_L6:
        if(isEtwsMessage())
        {
            boolean flag1 = isEtwsEmergencyUserAlert();
            boolean flag3 = isEtwsPopupAlert();
            mEtwsInfo = new SmsCbEtwsInfo(getEtwsWarningType(), flag1, flag3, false, null);
            mCmasInfo = null;
        } else
        if(isCmasMessage())
        {
            int l1 = getCmasMessageClass();
            int l = getCmasSeverity();
            int j = getCmasUrgency();
            int j1 = getCmasCertainty();
            mEtwsInfo = null;
            mCmasInfo = new SmsCbCmasInfo(l1, -1, -1, l, j, j1);
        } else
        {
            mEtwsInfo = null;
            mCmasInfo = null;
        }
        return;
_L4:
        k1 = i1;
        i = k;
        if(k <= i1) goto _L5; else goto _L3
_L2:
        mFormat = 2;
        i = abyte0[0];
        if(i != 1)
            throw new IllegalArgumentException((new StringBuilder()).append("Unsupported message type ").append(i).toString());
        mMessageIdentifier = (abyte0[1] & 0xff) << 8 | abyte0[2] & 0xff;
        mGeographicalScope = (abyte0[3] & 0xc0) >>> 6;
        mSerialNumber = (abyte0[3] & 0xff) << 8 | abyte0[4] & 0xff;
        mDataCodingScheme = abyte0[5] & 0xff;
        mPageIndex = 1;
        mNrOfPages = 1;
          goto _L6
    }

    private int getCmasCertainty()
    {
        switch(mMessageIdentifier)
        {
        case 4379: 
        case 4380: 
        case 4381: 
        case 4382: 
        case 4383: 
        default:
            return -1;

        case 4371: 
        case 4373: 
        case 4375: 
        case 4377: 
        case 4384: 
        case 4386: 
        case 4388: 
        case 4390: 
            return 0;

        case 4372: 
        case 4374: 
        case 4376: 
        case 4378: 
        case 4385: 
        case 4387: 
        case 4389: 
        case 4391: 
            return 1;
        }
    }

    private int getCmasMessageClass()
    {
        switch(mMessageIdentifier)
        {
        default:
            return -1;

        case 4370: 
        case 4383: 
            return 0;

        case 4371: 
        case 4372: 
        case 4384: 
        case 4385: 
            return 1;

        case 4373: 
        case 4374: 
        case 4375: 
        case 4376: 
        case 4377: 
        case 4378: 
        case 4386: 
        case 4387: 
        case 4388: 
        case 4389: 
        case 4390: 
        case 4391: 
            return 2;

        case 4379: 
        case 4392: 
            return 3;

        case 4380: 
        case 4393: 
            return 4;

        case 4381: 
        case 4394: 
            return 5;

        case 4382: 
        case 4395: 
            return 6;
        }
    }

    private int getCmasSeverity()
    {
        switch(mMessageIdentifier)
        {
        case 4379: 
        case 4380: 
        case 4381: 
        case 4382: 
        case 4383: 
        default:
            return -1;

        case 4371: 
        case 4372: 
        case 4373: 
        case 4374: 
        case 4384: 
        case 4385: 
        case 4386: 
        case 4387: 
            return 0;

        case 4375: 
        case 4376: 
        case 4377: 
        case 4378: 
        case 4388: 
        case 4389: 
        case 4390: 
        case 4391: 
            return 1;
        }
    }

    private int getCmasUrgency()
    {
        switch(mMessageIdentifier)
        {
        case 4379: 
        case 4380: 
        case 4381: 
        case 4382: 
        case 4383: 
        default:
            return -1;

        case 4371: 
        case 4372: 
        case 4375: 
        case 4376: 
        case 4384: 
        case 4385: 
        case 4388: 
        case 4389: 
            return 0;

        case 4373: 
        case 4374: 
        case 4377: 
        case 4378: 
        case 4386: 
        case 4387: 
        case 4390: 
        case 4391: 
            return 1;
        }
    }

    private int getEtwsWarningType()
    {
        return mMessageIdentifier - 4352;
    }

    private boolean isCmasMessage()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mMessageIdentifier >= 4370)
        {
            flag1 = flag;
            if(mMessageIdentifier <= 4399)
                flag1 = true;
        }
        return flag1;
    }

    private boolean isEtwsEmergencyUserAlert()
    {
        boolean flag = false;
        if((mSerialNumber & 0x2000) != 0)
            flag = true;
        return flag;
    }

    private boolean isEtwsMessage()
    {
        boolean flag;
        if((mMessageIdentifier & 0xfff8) == 4352)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private boolean isEtwsPopupAlert()
    {
        boolean flag = false;
        if((mSerialNumber & 0x1000) != 0)
            flag = true;
        return flag;
    }

    SmsCbCmasInfo getCmasInfo()
    {
        return mCmasInfo;
    }

    int getDataCodingScheme()
    {
        return mDataCodingScheme;
    }

    SmsCbEtwsInfo getEtwsInfo()
    {
        return mEtwsInfo;
    }

    int getGeographicalScope()
    {
        return mGeographicalScope;
    }

    int getNumberOfPages()
    {
        return mNrOfPages;
    }

    int getPageIndex()
    {
        return mPageIndex;
    }

    int getSerialNumber()
    {
        return mSerialNumber;
    }

    int getServiceCategory()
    {
        return mMessageIdentifier;
    }

    boolean isEmergencyMessage()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mMessageIdentifier >= 4352)
        {
            flag1 = flag;
            if(mMessageIdentifier <= 6399)
                flag1 = true;
        }
        return flag1;
    }

    boolean isEtwsPrimaryNotification()
    {
        boolean flag;
        if(mFormat == 3)
            flag = true;
        else
            flag = false;
        return flag;
    }

    boolean isUmtsFormat()
    {
        boolean flag;
        if(mFormat == 2)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public String toString()
    {
        return (new StringBuilder()).append("SmsCbHeader{GS=").append(mGeographicalScope).append(", serialNumber=0x").append(Integer.toHexString(mSerialNumber)).append(", messageIdentifier=0x").append(Integer.toHexString(mMessageIdentifier)).append(", DCS=0x").append(Integer.toHexString(mDataCodingScheme)).append(", page ").append(mPageIndex).append(" of ").append(mNrOfPages).append('}').toString();
    }

    static final int FORMAT_ETWS_PRIMARY = 3;
    static final int FORMAT_GSM = 1;
    static final int FORMAT_UMTS = 2;
    private static final int MESSAGE_TYPE_CBS_MESSAGE = 1;
    static final int PDU_HEADER_LENGTH = 6;
    private static final int PDU_LENGTH_ETWS = 56;
    private static final int PDU_LENGTH_GSM = 88;
    private final SmsCbCmasInfo mCmasInfo;
    private final int mDataCodingScheme;
    private final SmsCbEtwsInfo mEtwsInfo;
    private final int mFormat;
    private final int mGeographicalScope;
    private final int mMessageIdentifier;
    private final int mNrOfPages;
    private final int mPageIndex;
    private final int mSerialNumber;
}
