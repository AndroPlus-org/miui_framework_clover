// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.os.*;
import android.text.TextUtils;
import miui.telephony.PhoneDebug;

// Referenced classes of package android.telephony:
//            Rlog

public class ServiceState
    implements Parcelable
{

    public ServiceState()
    {
        mVoiceRegState = 1;
        mDataRegState = 1;
        mLteEarfcnRsrpBoost = 0;
    }

    public ServiceState(Parcel parcel)
    {
        boolean flag = true;
        super();
        mVoiceRegState = 1;
        mDataRegState = 1;
        mLteEarfcnRsrpBoost = 0;
        mVoiceRegState = parcel.readInt();
        mDataRegState = parcel.readInt();
        mVoiceRoamingType = parcel.readInt();
        mDataRoamingType = parcel.readInt();
        mVoiceOperatorAlphaLong = parcel.readString();
        mVoiceOperatorAlphaShort = parcel.readString();
        mVoiceOperatorNumeric = parcel.readString();
        mDataOperatorAlphaLong = parcel.readString();
        mDataOperatorAlphaShort = parcel.readString();
        mDataOperatorNumeric = parcel.readString();
        boolean flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        mIsManualNetworkSelection = flag1;
        mRilVoiceRadioTechnology = parcel.readInt();
        mRilDataRadioTechnology = parcel.readInt();
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        mCssIndicator = flag1;
        mNetworkId = parcel.readInt();
        mSystemId = parcel.readInt();
        mCdmaRoamingIndicator = parcel.readInt();
        mCdmaDefaultRoamingIndicator = parcel.readInt();
        mCdmaEriIconIndex = parcel.readInt();
        mCdmaEriIconMode = parcel.readInt();
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        mIsEmergencyOnly = flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        mIsDataRoamingFromRegistration = flag1;
        if(parcel.readInt() != 0)
            flag1 = flag;
        else
            flag1 = false;
        mIsUsingCarrierAggregation = flag1;
        mLteEarfcnRsrpBoost = parcel.readInt();
    }

    public ServiceState(ServiceState servicestate)
    {
        mVoiceRegState = 1;
        mDataRegState = 1;
        mLteEarfcnRsrpBoost = 0;
        copyFrom(servicestate);
    }

    public static boolean bearerBitmapHasCdma(int i)
    {
        boolean flag = false;
        if((i & 0x18f8) != 0)
            flag = true;
        return flag;
    }

    public static boolean bitmaskHasTech(int i, int j)
    {
        boolean flag = true;
        if(i == 0)
            return true;
        if(j >= 1)
        {
            if((1 << j - 1 & i) == 0)
                flag = false;
            return flag;
        } else
        {
            return false;
        }
    }

    private static boolean equalsHandlesNulls(Object obj, Object obj1)
    {
        boolean flag;
        if(obj == null)
        {
            if(obj1 == null)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = obj.equals(obj1);
        }
        return flag;
    }

    public static int getBitmaskForTech(int i)
    {
        if(i >= 1)
            return 1 << i - 1;
        else
            return 0;
    }

    public static int getBitmaskFromString(String s)
    {
        s = s.split("\\|");
        int i = 0;
        int j = s.length;
        for(int k = 0; k < j; k++)
        {
            String s1 = s[k];
            int l;
            try
            {
                l = Integer.parseInt(s1.trim());
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                return 0;
            }
            if(l == 0)
                return 0;
            i |= getBitmaskForTech(l);
        }

        return i;
    }

    public static final String getRoamingLogString(int i)
    {
        switch(i)
        {
        default:
            return "UNKNOWN";

        case 0: // '\0'
            return "home";

        case 1: // '\001'
            return "roaming";

        case 2: // '\002'
            return "Domestic Roaming";

        case 3: // '\003'
            return "International Roaming";
        }
    }

    public static boolean isCdma(int i)
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = flag;
        if(i == 4) goto _L2; else goto _L1
_L1:
        if(i != 5) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(i != 6)
        {
            flag1 = flag;
            if(i != 7)
            {
                flag1 = flag;
                if(i != 8)
                {
                    flag1 = flag;
                    if(i != 12)
                    {
                        flag1 = flag;
                        if(i != 13)
                            flag1 = false;
                    }
                }
            }
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    public static boolean isGsm(int i)
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = flag;
        if(i == 1) goto _L2; else goto _L1
_L1:
        if(i != 2) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(i != 3)
        {
            flag1 = flag;
            if(i != 9)
            {
                flag1 = flag;
                if(i != 10)
                {
                    flag1 = flag;
                    if(i != 11)
                    {
                        flag1 = flag;
                        if(i != 14)
                        {
                            flag1 = flag;
                            if(i != 15)
                            {
                                flag1 = flag;
                                if(i != 20)
                                {
                                    flag1 = flag;
                                    if(i != 16)
                                    {
                                        flag1 = flag;
                                        if(i != 17)
                                        {
                                            flag1 = flag;
                                            if(i != 18)
                                            {
                                                flag1 = flag;
                                                if(i != 19)
                                                    flag1 = false;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    public static boolean isLte(int i)
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(i != 14)
            if(i == 19)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public static ServiceState mergeServiceStates(ServiceState servicestate, ServiceState servicestate1)
    {
        if(servicestate1.mVoiceRegState != 0)
        {
            return servicestate;
        } else
        {
            servicestate = new ServiceState(servicestate);
            servicestate.mVoiceRegState = servicestate1.mVoiceRegState;
            servicestate.mIsEmergencyOnly = false;
            return servicestate;
        }
    }

    public static ServiceState newFromBundle(Bundle bundle)
    {
        ServiceState servicestate = new ServiceState();
        servicestate.setFromNotifierBundle(bundle);
        return servicestate;
    }

    private int rilRadioTechnologyToNetworkType(int i)
    {
        switch(i)
        {
        default:
            return 0;

        case 1: // '\001'
            return 1;

        case 2: // '\002'
            return 2;

        case 3: // '\003'
            return 3;

        case 9: // '\t'
            return 8;

        case 10: // '\n'
            return 9;

        case 11: // '\013'
            return 10;

        case 4: // '\004'
        case 5: // '\005'
            return 4;

        case 6: // '\006'
            return 7;

        case 7: // '\007'
            return 5;

        case 8: // '\b'
            return 6;

        case 12: // '\f'
            return 12;

        case 13: // '\r'
            return 14;

        case 14: // '\016'
            return 13;

        case 15: // '\017'
        case 20: // '\024'
            return 15;

        case 16: // '\020'
            return 16;

        case 17: // '\021'
            return 17;

        case 18: // '\022'
            return 18;

        case 19: // '\023'
            return 19;
        }
    }

    public static String rilRadioTechnologyToString(int i)
    {
        i;
        JVM INSTR tableswitch 0 20: default 100
    //                   0 130
    //                   1 137
    //                   2 144
    //                   3 151
    //                   4 158
    //                   5 165
    //                   6 172
    //                   7 179
    //                   8 186
    //                   9 193
    //                   10 200
    //                   11 207
    //                   12 214
    //                   13 221
    //                   14 228
    //                   15 235
    //                   16 242
    //                   17 256
    //                   18 249
    //                   19 263
    //                   20 270;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22
_L1:
        String s;
        s = "Unexpected";
        Rlog.w("PHONE", (new StringBuilder()).append("Unexpected radioTechnology=").append(i).toString());
_L24:
        return s;
_L2:
        s = "Unknown";
        continue; /* Loop/switch isn't completed */
_L3:
        s = "GPRS";
        continue; /* Loop/switch isn't completed */
_L4:
        s = "EDGE";
        continue; /* Loop/switch isn't completed */
_L5:
        s = "UMTS";
        continue; /* Loop/switch isn't completed */
_L6:
        s = "CDMA-IS95A";
        continue; /* Loop/switch isn't completed */
_L7:
        s = "CDMA-IS95B";
        continue; /* Loop/switch isn't completed */
_L8:
        s = "1xRTT";
        continue; /* Loop/switch isn't completed */
_L9:
        s = "EvDo-rev.0";
        continue; /* Loop/switch isn't completed */
_L10:
        s = "EvDo-rev.A";
        continue; /* Loop/switch isn't completed */
_L11:
        s = "HSDPA";
        continue; /* Loop/switch isn't completed */
_L12:
        s = "HSUPA";
        continue; /* Loop/switch isn't completed */
_L13:
        s = "HSPA";
        continue; /* Loop/switch isn't completed */
_L14:
        s = "EvDo-rev.B";
        continue; /* Loop/switch isn't completed */
_L15:
        s = "eHRPD";
        continue; /* Loop/switch isn't completed */
_L16:
        s = "LTE";
        continue; /* Loop/switch isn't completed */
_L17:
        s = "HSPAP";
        continue; /* Loop/switch isn't completed */
_L18:
        s = "GSM";
        continue; /* Loop/switch isn't completed */
_L20:
        s = "IWLAN";
        continue; /* Loop/switch isn't completed */
_L19:
        s = "TD-SCDMA";
        continue; /* Loop/switch isn't completed */
_L21:
        s = "LTE_CA";
        continue; /* Loop/switch isn't completed */
_L22:
        s = "DC_HSPAP";
        if(true) goto _L24; else goto _L23
_L23:
    }

    public static String rilServiceStateToString(int i)
    {
        switch(i)
        {
        default:
            return "UNKNOWN";

        case 0: // '\0'
            return "IN_SERVICE";

        case 1: // '\001'
            return "OUT_OF_SERVICE";

        case 2: // '\002'
            return "EMERGENCY_ONLY";

        case 3: // '\003'
            return "POWER_OFF";
        }
    }

    private void setFromNotifierBundle(Bundle bundle)
    {
        mVoiceRegState = bundle.getInt("voiceRegState");
        mDataRegState = bundle.getInt("dataRegState");
        mVoiceRoamingType = bundle.getInt("voiceRoamingType");
        mDataRoamingType = bundle.getInt("dataRoamingType");
        mVoiceOperatorAlphaLong = bundle.getString("operator-alpha-long");
        mVoiceOperatorAlphaShort = bundle.getString("operator-alpha-short");
        mVoiceOperatorNumeric = bundle.getString("operator-numeric");
        mDataOperatorAlphaLong = bundle.getString("data-operator-alpha-long");
        mDataOperatorAlphaShort = bundle.getString("data-operator-alpha-short");
        mDataOperatorNumeric = bundle.getString("data-operator-numeric");
        mIsManualNetworkSelection = bundle.getBoolean("manual");
        mRilVoiceRadioTechnology = bundle.getInt("radioTechnology");
        mRilDataRadioTechnology = bundle.getInt("dataRadioTechnology");
        mCssIndicator = bundle.getBoolean("cssIndicator");
        mNetworkId = bundle.getInt("networkId");
        mSystemId = bundle.getInt("systemId");
        mCdmaRoamingIndicator = bundle.getInt("cdmaRoamingIndicator");
        mCdmaDefaultRoamingIndicator = bundle.getInt("cdmaDefaultRoamingIndicator");
        mIsEmergencyOnly = bundle.getBoolean("emergencyOnly");
        mIsDataRoamingFromRegistration = bundle.getBoolean("isDataRoamingFromRegistration");
        mIsUsingCarrierAggregation = bundle.getBoolean("isUsingCarrierAggregation");
        mLteEarfcnRsrpBoost = bundle.getInt("LteEarfcnRsrpBoost");
    }

    private void setNullState(int i)
    {
        mVoiceRegState = i;
        mDataRegState = i;
        mVoiceRoamingType = 0;
        mDataRoamingType = 0;
        mVoiceOperatorAlphaLong = null;
        mVoiceOperatorAlphaShort = null;
        mVoiceOperatorNumeric = null;
        mDataOperatorAlphaLong = null;
        mDataOperatorAlphaShort = null;
        mDataOperatorNumeric = null;
        mIsManualNetworkSelection = false;
        mRilVoiceRadioTechnology = 0;
        mRilDataRadioTechnology = 0;
        mCssIndicator = false;
        mNetworkId = -1;
        mSystemId = -1;
        mCdmaRoamingIndicator = -1;
        mCdmaDefaultRoamingIndicator = -1;
        mCdmaEriIconIndex = -1;
        mCdmaEriIconMode = -1;
        mIsEmergencyOnly = false;
        mIsDataRoamingFromRegistration = false;
        mIsUsingCarrierAggregation = false;
        mLteEarfcnRsrpBoost = 0;
    }

    protected void copyFrom(ServiceState servicestate)
    {
        mVoiceRegState = servicestate.mVoiceRegState;
        mDataRegState = servicestate.mDataRegState;
        mVoiceRoamingType = servicestate.mVoiceRoamingType;
        mDataRoamingType = servicestate.mDataRoamingType;
        mVoiceOperatorAlphaLong = servicestate.mVoiceOperatorAlphaLong;
        mVoiceOperatorAlphaShort = servicestate.mVoiceOperatorAlphaShort;
        mVoiceOperatorNumeric = servicestate.mVoiceOperatorNumeric;
        mDataOperatorAlphaLong = servicestate.mDataOperatorAlphaLong;
        mDataOperatorAlphaShort = servicestate.mDataOperatorAlphaShort;
        mDataOperatorNumeric = servicestate.mDataOperatorNumeric;
        mIsManualNetworkSelection = servicestate.mIsManualNetworkSelection;
        mRilVoiceRadioTechnology = servicestate.mRilVoiceRadioTechnology;
        mRilDataRadioTechnology = servicestate.mRilDataRadioTechnology;
        mCssIndicator = servicestate.mCssIndicator;
        mNetworkId = servicestate.mNetworkId;
        mSystemId = servicestate.mSystemId;
        mCdmaRoamingIndicator = servicestate.mCdmaRoamingIndicator;
        mCdmaDefaultRoamingIndicator = servicestate.mCdmaDefaultRoamingIndicator;
        mCdmaEriIconIndex = servicestate.mCdmaEriIconIndex;
        mCdmaEriIconMode = servicestate.mCdmaEriIconMode;
        mIsEmergencyOnly = servicestate.mIsEmergencyOnly;
        mIsDataRoamingFromRegistration = servicestate.mIsDataRoamingFromRegistration;
        mIsUsingCarrierAggregation = servicestate.mIsUsingCarrierAggregation;
        mLteEarfcnRsrpBoost = servicestate.mLteEarfcnRsrpBoost;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        ServiceState servicestate;
        try
        {
            servicestate = (ServiceState)obj;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            return false;
        }
        if(obj == null)
            return false;
        boolean flag1 = flag;
        if(mVoiceRegState == servicestate.mVoiceRegState)
        {
            flag1 = flag;
            if(mDataRegState == servicestate.mDataRegState)
            {
                flag1 = flag;
                if(mIsManualNetworkSelection == servicestate.mIsManualNetworkSelection)
                {
                    flag1 = flag;
                    if(mVoiceRoamingType == servicestate.mVoiceRoamingType)
                    {
                        flag1 = flag;
                        if(mDataRoamingType == servicestate.mDataRoamingType)
                        {
                            flag1 = flag;
                            if(equalsHandlesNulls(mVoiceOperatorAlphaLong, servicestate.mVoiceOperatorAlphaLong))
                            {
                                flag1 = flag;
                                if(equalsHandlesNulls(mVoiceOperatorAlphaShort, servicestate.mVoiceOperatorAlphaShort))
                                {
                                    flag1 = flag;
                                    if(equalsHandlesNulls(mVoiceOperatorNumeric, servicestate.mVoiceOperatorNumeric))
                                    {
                                        flag1 = flag;
                                        if(equalsHandlesNulls(mDataOperatorAlphaLong, servicestate.mDataOperatorAlphaLong))
                                        {
                                            flag1 = flag;
                                            if(equalsHandlesNulls(mDataOperatorAlphaShort, servicestate.mDataOperatorAlphaShort))
                                            {
                                                flag1 = flag;
                                                if(equalsHandlesNulls(mDataOperatorNumeric, servicestate.mDataOperatorNumeric))
                                                {
                                                    flag1 = flag;
                                                    if(equalsHandlesNulls(Integer.valueOf(mRilVoiceRadioTechnology), Integer.valueOf(servicestate.mRilVoiceRadioTechnology)))
                                                    {
                                                        flag1 = flag;
                                                        if(equalsHandlesNulls(Integer.valueOf(mRilDataRadioTechnology), Integer.valueOf(servicestate.mRilDataRadioTechnology)))
                                                        {
                                                            flag1 = flag;
                                                            if(equalsHandlesNulls(Boolean.valueOf(mCssIndicator), Boolean.valueOf(servicestate.mCssIndicator)))
                                                            {
                                                                flag1 = flag;
                                                                if(equalsHandlesNulls(Integer.valueOf(mNetworkId), Integer.valueOf(servicestate.mNetworkId)))
                                                                {
                                                                    flag1 = flag;
                                                                    if(equalsHandlesNulls(Integer.valueOf(mSystemId), Integer.valueOf(servicestate.mSystemId)))
                                                                    {
                                                                        flag1 = flag;
                                                                        if(equalsHandlesNulls(Integer.valueOf(mCdmaRoamingIndicator), Integer.valueOf(servicestate.mCdmaRoamingIndicator)))
                                                                        {
                                                                            flag1 = flag;
                                                                            if(equalsHandlesNulls(Integer.valueOf(mCdmaDefaultRoamingIndicator), Integer.valueOf(servicestate.mCdmaDefaultRoamingIndicator)))
                                                                            {
                                                                                flag1 = flag;
                                                                                if(mIsEmergencyOnly == servicestate.mIsEmergencyOnly)
                                                                                {
                                                                                    flag1 = flag;
                                                                                    if(mIsDataRoamingFromRegistration == servicestate.mIsDataRoamingFromRegistration)
                                                                                    {
                                                                                        flag1 = flag;
                                                                                        if(mIsUsingCarrierAggregation == servicestate.mIsUsingCarrierAggregation)
                                                                                            flag1 = true;
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return flag1;
    }

    public void fillInNotifierBundle(Bundle bundle)
    {
        bundle.putInt("voiceRegState", mVoiceRegState);
        bundle.putInt("dataRegState", mDataRegState);
        bundle.putInt("voiceRoamingType", mVoiceRoamingType);
        bundle.putInt("dataRoamingType", mDataRoamingType);
        bundle.putString("operator-alpha-long", mVoiceOperatorAlphaLong);
        bundle.putString("operator-alpha-short", mVoiceOperatorAlphaShort);
        bundle.putString("operator-numeric", mVoiceOperatorNumeric);
        bundle.putString("data-operator-alpha-long", mDataOperatorAlphaLong);
        bundle.putString("data-operator-alpha-short", mDataOperatorAlphaShort);
        bundle.putString("data-operator-numeric", mDataOperatorNumeric);
        bundle.putBoolean("manual", mIsManualNetworkSelection);
        bundle.putInt("radioTechnology", mRilVoiceRadioTechnology);
        bundle.putInt("dataRadioTechnology", mRilDataRadioTechnology);
        bundle.putBoolean("cssIndicator", mCssIndicator);
        bundle.putInt("networkId", mNetworkId);
        bundle.putInt("systemId", mSystemId);
        bundle.putInt("cdmaRoamingIndicator", mCdmaRoamingIndicator);
        bundle.putInt("cdmaDefaultRoamingIndicator", mCdmaDefaultRoamingIndicator);
        bundle.putBoolean("emergencyOnly", mIsEmergencyOnly);
        bundle.putBoolean("isDataRoamingFromRegistration", mIsDataRoamingFromRegistration);
        bundle.putBoolean("isUsingCarrierAggregation", mIsUsingCarrierAggregation);
        bundle.putInt("LteEarfcnRsrpBoost", mLteEarfcnRsrpBoost);
    }

    public int getCdmaDefaultRoamingIndicator()
    {
        return mCdmaDefaultRoamingIndicator;
    }

    public int getCdmaEriIconIndex()
    {
        return mCdmaEriIconIndex;
    }

    public int getCdmaEriIconMode()
    {
        return mCdmaEriIconMode;
    }

    public int getCdmaRoamingIndicator()
    {
        return mCdmaRoamingIndicator;
    }

    public int getCssIndicator()
    {
        int i;
        if(mCssIndicator)
            i = 1;
        else
            i = 0;
        return i;
    }

    public int getDataNetworkType()
    {
        return rilRadioTechnologyToNetworkType(mRilDataRadioTechnology);
    }

    public String getDataOperatorAlphaLong()
    {
        return mDataOperatorAlphaLong;
    }

    public String getDataOperatorAlphaShort()
    {
        return mDataOperatorAlphaShort;
    }

    public String getDataOperatorNumeric()
    {
        return mDataOperatorNumeric;
    }

    public int getDataRegState()
    {
        return mDataRegState;
    }

    public boolean getDataRoaming()
    {
        boolean flag = false;
        if(mDataRoamingType != 0)
            flag = true;
        return flag;
    }

    public boolean getDataRoamingFromRegistration()
    {
        return mIsDataRoamingFromRegistration;
    }

    public int getDataRoamingType()
    {
        return mDataRoamingType;
    }

    public boolean getIsManualSelection()
    {
        return mIsManualNetworkSelection;
    }

    public int getLteEarfcnRsrpBoost()
    {
        return mLteEarfcnRsrpBoost;
    }

    public int getNetworkId()
    {
        return mNetworkId;
    }

    public int getNetworkType()
    {
        Rlog.e("PHONE", "ServiceState.getNetworkType() DEPRECATED will be removed *******");
        return rilRadioTechnologyToNetworkType(mRilVoiceRadioTechnology);
    }

    public String getOperatorAlpha()
    {
        if(TextUtils.isEmpty(mVoiceOperatorAlphaLong))
            return mVoiceOperatorAlphaShort;
        else
            return mVoiceOperatorAlphaLong;
    }

    public String getOperatorAlphaLong()
    {
        return mVoiceOperatorAlphaLong;
    }

    public String getOperatorAlphaShort()
    {
        return mVoiceOperatorAlphaShort;
    }

    public String getOperatorNumeric()
    {
        return mVoiceOperatorNumeric;
    }

    public int getRadioTechnology()
    {
        Rlog.e("PHONE", "ServiceState.getRadioTechnology() DEPRECATED will be removed *******");
        return getRilDataRadioTechnology();
    }

    public int getRilDataRadioTechnology()
    {
        return mRilDataRadioTechnology;
    }

    public int getRilVoiceRadioTechnology()
    {
        return mRilVoiceRadioTechnology;
    }

    public boolean getRoaming()
    {
        boolean flag;
        if(!getVoiceRoaming())
            flag = getDataRoaming();
        else
            flag = true;
        return flag;
    }

    public int getState()
    {
        return getVoiceRegState();
    }

    public int getSystemId()
    {
        return mSystemId;
    }

    public int getVoiceNetworkType()
    {
        return rilRadioTechnologyToNetworkType(mRilVoiceRadioTechnology);
    }

    public String getVoiceOperatorAlphaLong()
    {
        return mVoiceOperatorAlphaLong;
    }

    public String getVoiceOperatorAlphaShort()
    {
        return mVoiceOperatorAlphaShort;
    }

    public String getVoiceOperatorNumeric()
    {
        return mVoiceOperatorNumeric;
    }

    public int getVoiceRegState()
    {
        return mVoiceRegState;
    }

    public boolean getVoiceRoaming()
    {
        boolean flag = false;
        if(mVoiceRoamingType != 0)
            flag = true;
        return flag;
    }

    public int getVoiceRoamingType()
    {
        return mVoiceRoamingType;
    }

    public int hashCode()
    {
        int i = 1;
        int j = mVoiceRegState;
        int k = mDataRegState;
        int l = mVoiceRoamingType;
        int i1 = mDataRoamingType;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        int l2;
        int i3;
        int j3;
        int k3;
        if(mIsManualNetworkSelection)
            j1 = 1;
        else
            j1 = 0;
        if(mVoiceOperatorAlphaLong == null)
            k1 = 0;
        else
            k1 = mVoiceOperatorAlphaLong.hashCode();
        if(mVoiceOperatorAlphaShort == null)
            l1 = 0;
        else
            l1 = mVoiceOperatorAlphaShort.hashCode();
        if(mVoiceOperatorNumeric == null)
            i2 = 0;
        else
            i2 = mVoiceOperatorNumeric.hashCode();
        if(mDataOperatorAlphaLong == null)
            j2 = 0;
        else
            j2 = mDataOperatorAlphaLong.hashCode();
        if(mDataOperatorAlphaShort == null)
            k2 = 0;
        else
            k2 = mDataOperatorAlphaShort.hashCode();
        if(mDataOperatorNumeric == null)
            l2 = 0;
        else
            l2 = mDataOperatorNumeric.hashCode();
        i3 = mCdmaRoamingIndicator;
        j3 = mCdmaDefaultRoamingIndicator;
        if(mIsEmergencyOnly)
            k3 = 1;
        else
            k3 = 0;
        if(!mIsDataRoamingFromRegistration)
            i = 0;
        return k3 + (j3 + (l2 + (i1 + (j * 31 + k * 37 + l) + j1 + k1 + l1 + i2 + j2 + k2) + i3)) + i;
    }

    public boolean isEmergencyOnly()
    {
        return mIsEmergencyOnly;
    }

    public boolean isUsingCarrierAggregation()
    {
        return mIsUsingCarrierAggregation;
    }

    public void setCdmaDefaultRoamingIndicator(int i)
    {
        mCdmaDefaultRoamingIndicator = i;
    }

    public void setCdmaEriIconIndex(int i)
    {
        mCdmaEriIconIndex = i;
    }

    public void setCdmaEriIconMode(int i)
    {
        mCdmaEriIconMode = i;
    }

    public void setCdmaRoamingIndicator(int i)
    {
        mCdmaRoamingIndicator = i;
    }

    public void setCssIndicator(int i)
    {
        boolean flag = false;
        if(i != 0)
            flag = true;
        mCssIndicator = flag;
    }

    public void setDataOperatorAlphaLong(String s)
    {
        mDataOperatorAlphaLong = s;
    }

    public void setDataOperatorName(String s, String s1, String s2)
    {
        mDataOperatorAlphaLong = s;
        mDataOperatorAlphaShort = s1;
        mDataOperatorNumeric = s2;
    }

    public void setDataRegState(int i)
    {
        mDataRegState = i;
    }

    public void setDataRoaming(boolean flag)
    {
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        mDataRoamingType = i;
    }

    public void setDataRoamingFromRegistration(boolean flag)
    {
        mIsDataRoamingFromRegistration = flag;
    }

    public void setDataRoamingType(int i)
    {
        mDataRoamingType = i;
    }

    public void setEmergencyOnly(boolean flag)
    {
        mIsEmergencyOnly = flag;
    }

    public void setIsManualSelection(boolean flag)
    {
        mIsManualNetworkSelection = flag;
    }

    public void setIsUsingCarrierAggregation(boolean flag)
    {
        mIsUsingCarrierAggregation = flag;
    }

    public void setLteEarfcnRsrpBoost(int i)
    {
        mLteEarfcnRsrpBoost = i;
    }

    public void setOperatorAlphaLong(String s)
    {
        mVoiceOperatorAlphaLong = s;
        mDataOperatorAlphaLong = s;
    }

    public void setOperatorName(String s, String s1, String s2)
    {
        mVoiceOperatorAlphaLong = s;
        mVoiceOperatorAlphaShort = s1;
        mVoiceOperatorNumeric = s2;
        mDataOperatorAlphaLong = s;
        mDataOperatorAlphaShort = s1;
        mDataOperatorNumeric = s2;
    }

    public void setRilDataRadioTechnology(int i)
    {
        if(i == 19)
        {
            i = 14;
            mIsUsingCarrierAggregation = true;
        } else
        {
            mIsUsingCarrierAggregation = false;
        }
        mRilDataRadioTechnology = i;
    }

    public void setRilVoiceRadioTechnology(int i)
    {
        int j = i;
        if(i == 19)
            j = 14;
        mRilVoiceRadioTechnology = j;
    }

    public void setRoaming(boolean flag)
    {
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        mVoiceRoamingType = i;
        mDataRoamingType = mVoiceRoamingType;
    }

    public void setState(int i)
    {
        setVoiceRegState(i);
    }

    public void setStateOff()
    {
        setNullState(3);
    }

    public void setStateOutOfService()
    {
        setNullState(1);
    }

    public void setSystemAndNetworkId(int i, int j)
    {
        mSystemId = i;
        mNetworkId = j;
    }

    public void setVoiceOperatorAlphaLong(String s)
    {
        mVoiceOperatorAlphaLong = s;
    }

    public void setVoiceOperatorName(String s, String s1, String s2)
    {
        mVoiceOperatorAlphaLong = s;
        mVoiceOperatorAlphaShort = s1;
        mVoiceOperatorNumeric = s2;
    }

    public void setVoiceRegState(int i)
    {
        mVoiceRegState = i;
    }

    public void setVoiceRoaming(boolean flag)
    {
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        mVoiceRoamingType = i;
    }

    public void setVoiceRoamingType(int i)
    {
        mVoiceRoamingType = i;
    }

    public String toString()
    {
        byte byte0 = -1;
        StringBuilder stringbuilder = (new StringBuilder()).append("{mVoiceRegState=").append(mVoiceRegState).append("(").append(rilServiceStateToString(mVoiceRegState)).append(")").append(", mDataRegState=").append(mDataRegState).append("(").append(rilServiceStateToString(mDataRegState)).append(")").append(", mVoiceRoamingType=").append(getRoamingLogString(mVoiceRoamingType)).append(", mDataRoamingType=").append(getRoamingLogString(mDataRoamingType)).append(", mVoiceOperatorAlphaLong=").append(mVoiceOperatorAlphaLong).append(", mVoiceOperatorAlphaShort=").append(mVoiceOperatorAlphaShort).append(", mDataOperatorAlphaLong=").append(mDataOperatorAlphaLong).append(", mDataOperatorAlphaShort=").append(mDataOperatorAlphaShort).append(", isManualNetworkSelection=").append(mIsManualNetworkSelection);
        Object obj;
        int i;
        if(mIsManualNetworkSelection)
            obj = "(manual)";
        else
            obj = "(automatic)";
        stringbuilder = stringbuilder.append(((String) (obj))).append(", mRilVoiceRadioTechnology=").append(mRilVoiceRadioTechnology).append("(").append(rilRadioTechnologyToString(mRilVoiceRadioTechnology)).append(")").append(", mRilDataRadioTechnology=").append(mRilDataRadioTechnology).append("(").append(rilRadioTechnologyToString(mRilDataRadioTechnology)).append(")").append(", mCssIndicator=");
        if(mCssIndicator)
            obj = "supported";
        else
            obj = "unsupported";
        obj = stringbuilder.append(((String) (obj))).append(", mNetworkId=");
        if(PhoneDebug.VDBG)
            i = mNetworkId;
        else
            i = -1;
        obj = ((StringBuilder) (obj)).append(i).append(", mSystemId=");
        i = byte0;
        if(PhoneDebug.VDBG)
            i = mSystemId;
        return ((StringBuilder) (obj)).append(i).append(", mCdmaRoamingIndicator=").append(mCdmaRoamingIndicator).append(", mCdmaDefaultRoamingIndicator=").append(mCdmaDefaultRoamingIndicator).append(", mIsEmergencyOnly=").append(mIsEmergencyOnly).append(", mIsDataRoamingFromRegistration=").append(mIsDataRoamingFromRegistration).append(", mIsUsingCarrierAggregation=").append(mIsUsingCarrierAggregation).append(", mLteEarfcnRsrpBoost=").append(mLteEarfcnRsrpBoost).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(mVoiceRegState);
        parcel.writeInt(mDataRegState);
        parcel.writeInt(mVoiceRoamingType);
        parcel.writeInt(mDataRoamingType);
        parcel.writeString(mVoiceOperatorAlphaLong);
        parcel.writeString(mVoiceOperatorAlphaShort);
        parcel.writeString(mVoiceOperatorNumeric);
        parcel.writeString(mDataOperatorAlphaLong);
        parcel.writeString(mDataOperatorAlphaShort);
        parcel.writeString(mDataOperatorNumeric);
        if(mIsManualNetworkSelection)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(mRilVoiceRadioTechnology);
        parcel.writeInt(mRilDataRadioTechnology);
        if(mCssIndicator)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(mNetworkId);
        parcel.writeInt(mSystemId);
        parcel.writeInt(mCdmaRoamingIndicator);
        parcel.writeInt(mCdmaDefaultRoamingIndicator);
        parcel.writeInt(mCdmaEriIconIndex);
        parcel.writeInt(mCdmaEriIconMode);
        if(mIsEmergencyOnly)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mIsDataRoamingFromRegistration)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mIsUsingCarrierAggregation)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(mLteEarfcnRsrpBoost);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ServiceState createFromParcel(Parcel parcel)
        {
            return new ServiceState(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ServiceState[] newArray(int i)
        {
            return new ServiceState[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    static final boolean DBG = false;
    static final String LOG_TAG = "PHONE";
    public static final int REGISTRATION_STATE_HOME_NETWORK = 1;
    public static final int REGISTRATION_STATE_NOT_REGISTERED_AND_NOT_SEARCHING = 0;
    public static final int REGISTRATION_STATE_NOT_REGISTERED_AND_SEARCHING = 2;
    public static final int REGISTRATION_STATE_REGISTRATION_DENIED = 3;
    public static final int REGISTRATION_STATE_ROAMING = 5;
    public static final int REGISTRATION_STATE_UNKNOWN = 4;
    public static final int RIL_RADIO_CDMA_TECHNOLOGY_BITMASK = 6392;
    public static final int RIL_RADIO_TECHNOLOGY_1xRTT = 6;
    public static final int RIL_RADIO_TECHNOLOGY_EDGE = 2;
    public static final int RIL_RADIO_TECHNOLOGY_EHRPD = 13;
    public static final int RIL_RADIO_TECHNOLOGY_EVDO_0 = 7;
    public static final int RIL_RADIO_TECHNOLOGY_EVDO_A = 8;
    public static final int RIL_RADIO_TECHNOLOGY_EVDO_B = 12;
    public static final int RIL_RADIO_TECHNOLOGY_GPRS = 1;
    public static final int RIL_RADIO_TECHNOLOGY_GSM = 16;
    public static final int RIL_RADIO_TECHNOLOGY_HSDPA = 9;
    public static final int RIL_RADIO_TECHNOLOGY_HSPA = 11;
    public static final int RIL_RADIO_TECHNOLOGY_HSPAP = 15;
    public static final int RIL_RADIO_TECHNOLOGY_HSUPA = 10;
    public static final int RIL_RADIO_TECHNOLOGY_IS95A = 4;
    public static final int RIL_RADIO_TECHNOLOGY_IS95B = 5;
    public static final int RIL_RADIO_TECHNOLOGY_IWLAN = 18;
    public static final int RIL_RADIO_TECHNOLOGY_LTE = 14;
    public static final int RIL_RADIO_TECHNOLOGY_LTE_CA = 19;
    public static final int RIL_RADIO_TECHNOLOGY_TD_SCDMA = 17;
    public static final int RIL_RADIO_TECHNOLOGY_UMTS = 3;
    public static final int RIL_RADIO_TECHNOLOGY_UNKNOWN = 0;
    public static final int RIL_REG_STATE_DENIED = 3;
    public static final int RIL_REG_STATE_DENIED_EMERGENCY_CALL_ENABLED = 13;
    public static final int RIL_REG_STATE_HOME = 1;
    public static final int RIL_REG_STATE_NOT_REG = 0;
    public static final int RIL_REG_STATE_NOT_REG_EMERGENCY_CALL_ENABLED = 10;
    public static final int RIL_REG_STATE_ROAMING = 5;
    public static final int RIL_REG_STATE_SEARCHING = 2;
    public static final int RIL_REG_STATE_SEARCHING_EMERGENCY_CALL_ENABLED = 12;
    public static final int RIL_REG_STATE_UNKNOWN = 4;
    public static final int RIL_REG_STATE_UNKNOWN_EMERGENCY_CALL_ENABLED = 14;
    public static final int ROAMING_TYPE_DOMESTIC = 2;
    public static final int ROAMING_TYPE_INTERNATIONAL = 3;
    public static final int ROAMING_TYPE_NOT_ROAMING = 0;
    public static final int ROAMING_TYPE_UNKNOWN = 1;
    public static final int STATE_EMERGENCY_ONLY = 2;
    public static final int STATE_IN_SERVICE = 0;
    public static final int STATE_OUT_OF_SERVICE = 1;
    public static final int STATE_POWER_OFF = 3;
    static final boolean VDBG = false;
    private int mCdmaDefaultRoamingIndicator;
    private int mCdmaEriIconIndex;
    private int mCdmaEriIconMode;
    private int mCdmaRoamingIndicator;
    private boolean mCssIndicator;
    private String mDataOperatorAlphaLong;
    private String mDataOperatorAlphaShort;
    private String mDataOperatorNumeric;
    private int mDataRegState;
    private int mDataRoamingType;
    private boolean mIsDataRoamingFromRegistration;
    private boolean mIsEmergencyOnly;
    private boolean mIsManualNetworkSelection;
    private boolean mIsUsingCarrierAggregation;
    private int mLteEarfcnRsrpBoost;
    private int mNetworkId;
    private int mRilDataRadioTechnology;
    private int mRilVoiceRadioTechnology;
    private int mSystemId;
    private String mVoiceOperatorAlphaLong;
    private String mVoiceOperatorAlphaShort;
    private String mVoiceOperatorNumeric;
    private int mVoiceRegState;
    private int mVoiceRoamingType;

}
