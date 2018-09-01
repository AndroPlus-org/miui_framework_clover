// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.content.res.Resources;
import android.os.*;
import android.util.Log;
import miui.os.Build;

// Referenced classes of package android.telephony:
//            Rlog

public class SignalStrength
    implements Parcelable
{

    public SignalStrength()
    {
        mGsmSignalStrength = 99;
        mGsmBitErrorRate = -1;
        mCdmaDbm = -1;
        mCdmaEcio = -1;
        mEvdoDbm = -1;
        mEvdoEcio = -1;
        mEvdoSnr = -1;
        mLteSignalStrength = 99;
        mLteRsrp = 0x7fffffff;
        mLteRsrq = 0x7fffffff;
        mLteRssnr = 0x7fffffff;
        mLteCqi = 0x7fffffff;
        mLteRsrpBoost = 0;
        mTdScdmaRscp = 0x7fffffff;
        isGsm = true;
    }

    public SignalStrength(int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1, int i2, int j2, int k2, int l2, int i3, int j3, 
            boolean flag)
    {
        initialize(i, j, k, l, i1, j1, k1, l1, i2, j2, k2, l2, i3, flag);
        mTdScdmaRscp = j3;
    }

    public SignalStrength(int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1, int i2, int j2, int k2, int l2, int i3, boolean flag)
    {
        initialize(i, j, k, l, i1, j1, k1, l1, i2, j2, k2, l2, 0, flag);
        mTdScdmaRscp = i3;
    }

    public SignalStrength(int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1, int i2, int j2, int k2, int l2, boolean flag)
    {
        initialize(i, j, k, l, i1, j1, k1, l1, i2, j2, k2, l2, 0, flag);
    }

    public SignalStrength(int i, int j, int k, int l, int i1, int j1, int k1, 
            boolean flag)
    {
        initialize(i, j, k, l, i1, j1, k1, 99, 0x7fffffff, 0x7fffffff, 0x7fffffff, 0x7fffffff, 0, flag);
    }

    public SignalStrength(Parcel parcel)
    {
        boolean flag = false;
        super();
        mGsmSignalStrength = parcel.readInt();
        mGsmBitErrorRate = parcel.readInt();
        mCdmaDbm = parcel.readInt();
        mCdmaEcio = parcel.readInt();
        mEvdoDbm = parcel.readInt();
        mEvdoEcio = parcel.readInt();
        mEvdoSnr = parcel.readInt();
        mLteSignalStrength = parcel.readInt();
        mLteRsrp = parcel.readInt();
        mLteRsrq = parcel.readInt();
        mLteRssnr = parcel.readInt();
        mLteCqi = parcel.readInt();
        mLteRsrpBoost = parcel.readInt();
        mTdScdmaRscp = parcel.readInt();
        if(parcel.readInt() != 0)
            flag = true;
        isGsm = flag;
    }

    public SignalStrength(SignalStrength signalstrength)
    {
        copyFrom(signalstrength);
    }

    public SignalStrength(boolean flag)
    {
        mGsmSignalStrength = 99;
        mGsmBitErrorRate = -1;
        mCdmaDbm = -1;
        mCdmaEcio = -1;
        mEvdoDbm = -1;
        mEvdoEcio = -1;
        mEvdoSnr = -1;
        mLteSignalStrength = 99;
        mLteRsrp = 0x7fffffff;
        mLteRsrq = 0x7fffffff;
        mLteRssnr = 0x7fffffff;
        mLteCqi = 0x7fffffff;
        mLteRsrpBoost = 0;
        mTdScdmaRscp = 0x7fffffff;
        isGsm = flag;
    }

    private static void log(String s)
    {
        Rlog.w("SignalStrength", s);
    }

    public static SignalStrength makeSignalStrengthFromRilParcel(Parcel parcel)
    {
        SignalStrength signalstrength = new SignalStrength();
        signalstrength.mGsmSignalStrength = parcel.readInt();
        signalstrength.mGsmBitErrorRate = parcel.readInt();
        signalstrength.mCdmaDbm = parcel.readInt();
        signalstrength.mCdmaEcio = parcel.readInt();
        signalstrength.mEvdoDbm = parcel.readInt();
        signalstrength.mEvdoEcio = parcel.readInt();
        signalstrength.mEvdoSnr = parcel.readInt();
        signalstrength.mLteSignalStrength = parcel.readInt();
        signalstrength.mLteRsrp = parcel.readInt();
        signalstrength.mLteRsrq = parcel.readInt();
        signalstrength.mLteRssnr = parcel.readInt();
        signalstrength.mLteCqi = parcel.readInt();
        signalstrength.mTdScdmaRscp = parcel.readInt();
        return signalstrength;
    }

    public static SignalStrength newFromBundle(Bundle bundle)
    {
        SignalStrength signalstrength = new SignalStrength();
        signalstrength.setFromNotifierBundle(bundle);
        return signalstrength;
    }

    private void setFromNotifierBundle(Bundle bundle)
    {
        mGsmSignalStrength = bundle.getInt("GsmSignalStrength");
        mGsmBitErrorRate = bundle.getInt("GsmBitErrorRate");
        mCdmaDbm = bundle.getInt("CdmaDbm");
        mCdmaEcio = bundle.getInt("CdmaEcio");
        mEvdoDbm = bundle.getInt("EvdoDbm");
        mEvdoEcio = bundle.getInt("EvdoEcio");
        mEvdoSnr = bundle.getInt("EvdoSnr");
        mLteSignalStrength = bundle.getInt("LteSignalStrength");
        mLteRsrp = bundle.getInt("LteRsrp");
        mLteRsrq = bundle.getInt("LteRsrq");
        mLteRssnr = bundle.getInt("LteRssnr");
        mLteCqi = bundle.getInt("LteCqi");
        mLteRsrpBoost = bundle.getInt("lteRsrpBoost");
        mTdScdmaRscp = bundle.getInt("TdScdma");
        isGsm = bundle.getBoolean("isGsm");
    }

    protected void copyFrom(SignalStrength signalstrength)
    {
        mGsmSignalStrength = signalstrength.mGsmSignalStrength;
        mGsmBitErrorRate = signalstrength.mGsmBitErrorRate;
        mCdmaDbm = signalstrength.mCdmaDbm;
        mCdmaEcio = signalstrength.mCdmaEcio;
        mEvdoDbm = signalstrength.mEvdoDbm;
        mEvdoEcio = signalstrength.mEvdoEcio;
        mEvdoSnr = signalstrength.mEvdoSnr;
        mLteSignalStrength = signalstrength.mLteSignalStrength;
        mLteRsrp = signalstrength.mLteRsrp;
        mLteRsrq = signalstrength.mLteRsrq;
        mLteRssnr = signalstrength.mLteRssnr;
        mLteCqi = signalstrength.mLteCqi;
        mLteRsrpBoost = signalstrength.mLteRsrpBoost;
        mTdScdmaRscp = signalstrength.mTdScdmaRscp;
        isGsm = signalstrength.isGsm;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        SignalStrength signalstrength;
        try
        {
            signalstrength = (SignalStrength)obj;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            return false;
        }
        if(obj == null)
            return false;
        boolean flag1 = flag;
        if(mGsmSignalStrength == signalstrength.mGsmSignalStrength)
        {
            flag1 = flag;
            if(mGsmBitErrorRate == signalstrength.mGsmBitErrorRate)
            {
                flag1 = flag;
                if(mCdmaDbm == signalstrength.mCdmaDbm)
                {
                    flag1 = flag;
                    if(mCdmaEcio == signalstrength.mCdmaEcio)
                    {
                        flag1 = flag;
                        if(mEvdoDbm == signalstrength.mEvdoDbm)
                        {
                            flag1 = flag;
                            if(mEvdoEcio == signalstrength.mEvdoEcio)
                            {
                                flag1 = flag;
                                if(mEvdoSnr == signalstrength.mEvdoSnr)
                                {
                                    flag1 = flag;
                                    if(mLteSignalStrength == signalstrength.mLteSignalStrength)
                                    {
                                        flag1 = flag;
                                        if(mLteRsrp == signalstrength.mLteRsrp)
                                        {
                                            flag1 = flag;
                                            if(mLteRsrq == signalstrength.mLteRsrq)
                                            {
                                                flag1 = flag;
                                                if(mLteRssnr == signalstrength.mLteRssnr)
                                                {
                                                    flag1 = flag;
                                                    if(mLteCqi == signalstrength.mLteCqi)
                                                    {
                                                        flag1 = flag;
                                                        if(mLteRsrpBoost == signalstrength.mLteRsrpBoost)
                                                        {
                                                            flag1 = flag;
                                                            if(mTdScdmaRscp == signalstrength.mTdScdmaRscp)
                                                            {
                                                                flag1 = flag;
                                                                if(isGsm == signalstrength.isGsm)
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
        return flag1;
    }

    public void fillInNotifierBundle(Bundle bundle)
    {
        bundle.putInt("GsmSignalStrength", mGsmSignalStrength);
        bundle.putInt("GsmBitErrorRate", mGsmBitErrorRate);
        bundle.putInt("CdmaDbm", mCdmaDbm);
        bundle.putInt("CdmaEcio", mCdmaEcio);
        bundle.putInt("EvdoDbm", mEvdoDbm);
        bundle.putInt("EvdoEcio", mEvdoEcio);
        bundle.putInt("EvdoSnr", mEvdoSnr);
        bundle.putInt("LteSignalStrength", mLteSignalStrength);
        bundle.putInt("LteRsrp", mLteRsrp);
        bundle.putInt("LteRsrq", mLteRsrq);
        bundle.putInt("LteRssnr", mLteRssnr);
        bundle.putInt("LteCqi", mLteCqi);
        bundle.putInt("lteRsrpBoost", mLteRsrpBoost);
        bundle.putInt("TdScdma", mTdScdmaRscp);
        bundle.putBoolean("isGsm", isGsm);
    }

    public int getAsuLevel()
    {
        if(!isGsm) goto _L2; else goto _L1
_L1:
        int i;
        if(getLteLevel() == 0 && getLteAsuLevel() == 255)
        {
            if(getTdScdmaLevel() == 0 && getTdScdmaAsuLevel() == 255)
                i = getGsmAsuLevel();
            else
                i = getTdScdmaAsuLevel();
        } else
        {
            i = getLteAsuLevel();
        }
_L4:
        return i;
_L2:
        i = getCdmaAsuLevel();
        int j = getEvdoAsuLevel();
        if(j != 0)
            if(i == 0)
                i = j;
            else
            if(i >= j)
                i = j;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public int getCdmaAsuLevel()
    {
        int i = getCdmaDbm();
        int j = getCdmaEcio();
        if(i >= -75)
            i = 16;
        else
        if(i >= -82)
            i = 8;
        else
        if(i >= -90)
            i = 4;
        else
        if(i >= -95)
            i = 2;
        else
        if(i >= -100)
            i = 1;
        else
            i = 99;
        if(j >= -90)
            j = 16;
        else
        if(j >= -100)
            j = 8;
        else
        if(j >= -115)
            j = 4;
        else
        if(j >= -130)
            j = 2;
        else
        if(j >= -150)
            j = 1;
        else
            j = 99;
        if(i >= j);
        return i;
    }

    public int getCdmaDbm()
    {
        return mCdmaDbm;
    }

    public int getCdmaEcio()
    {
        return mCdmaEcio;
    }

    public int getCdmaLevel()
    {
        int i = getCdmaDbm();
        getCdmaEcio();
        if(i >= -90)
            i = 5;
        else
        if(i >= -93)
            i = 4;
        else
        if(i >= -97)
            i = 3;
        else
        if(i >= -101)
            i = 2;
        else
        if(i >= -109)
            i = 1;
        else
            i = 0;
        return i;
    }

    public int getDbm()
    {
        int k;
        int l;
        if(isGsm())
        {
            int i;
            if(getLteDbm() == 0x7fffffff)
            {
                if(getTdScdmaLevel() == 0 && getTdScdmaAsuLevel() == 255)
                    i = getGsmDbm();
                else
                    i = getTdScdmaDbm();
            } else
            {
                i = getLteDbm();
            }
            return i;
        }
        k = getCdmaDbm();
        l = getEvdoDbm();
        if(l != -120) goto _L2; else goto _L1
_L1:
        int j = k;
_L4:
        return j;
_L2:
        if(k == -120)
        {
            j = l;
        } else
        {
            j = k;
            if(k >= l)
                j = l;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public int getEvdoAsuLevel()
    {
        int i = getEvdoDbm();
        int j = getEvdoSnr();
        if(i >= -65)
            i = 16;
        else
        if(i >= -75)
            i = 8;
        else
        if(i >= -85)
            i = 4;
        else
        if(i >= -95)
            i = 2;
        else
        if(i >= -105)
            i = 1;
        else
            i = 99;
        if(j >= 7)
            j = 16;
        else
        if(j >= 6)
            j = 8;
        else
        if(j >= 5)
            j = 4;
        else
        if(j >= 3)
            j = 2;
        else
        if(j >= 1)
            j = 1;
        else
            j = 99;
        if(i >= j);
        return i;
    }

    public int getEvdoDbm()
    {
        return mEvdoDbm;
    }

    public int getEvdoEcio()
    {
        return mEvdoEcio;
    }

    public int getEvdoLevel()
    {
        int i = getEvdoDbm();
        getEvdoSnr();
        if(i >= -90)
            i = 5;
        else
        if(i >= -93)
            i = 4;
        else
        if(i >= -97)
            i = 3;
        else
        if(i >= -101)
            i = 2;
        else
        if(i >= -109)
            i = 1;
        else
            i = 0;
        return i;
    }

    public int getEvdoSnr()
    {
        return mEvdoSnr;
    }

    public int getGsmAsuLevel()
    {
        return getGsmSignalStrength();
    }

    public int getGsmBitErrorRate()
    {
        return mGsmBitErrorRate;
    }

    public int getGsmDbm()
    {
        int i = getGsmSignalStrength();
        if(i == 99)
            i = -1;
        if(i != -1)
            i = i * 2 - 113;
        else
            i = -1;
        return i;
    }

    public int getGsmLevel()
    {
        int i = getGsmSignalStrength();
        if(i <= 0 || i == 99)
            i = 0;
        else
        if(i >= 12)
            i = 5;
        else
        if(i >= 9)
            i = 4;
        else
        if(i >= 6)
            i = 3;
        else
        if(i >= 3)
            i = 2;
        else
            i = 1;
        return i;
    }

    public int getGsmSignalStrength()
    {
        return mGsmSignalStrength;
    }

    public int getLevel()
    {
        if(!isGsm) goto _L2; else goto _L1
_L1:
        int l;
        int i = getLteLevel();
        l = i;
        if(i == 0)
        {
            int j = getTdScdmaLevel();
            l = j;
            if(j == 0)
                l = getGsmLevel();
        }
_L4:
        return l;
_L2:
        l = getCdmaLevel();
        int k = getEvdoLevel();
        if(k != 0)
            if(l == 0)
                l = k;
            else
            if(l >= k)
                l = k;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public int getLteAsuLevel()
    {
        int i = getLteDbm();
        if(i == 0x7fffffff)
            i = 255;
        else
            i += 140;
        return i;
    }

    public int getLteCqi()
    {
        return mLteCqi;
    }

    public int getLteDbm()
    {
        return mLteRsrp;
    }

    public int getLteLevel()
    {
        boolean flag;
        int i;
        flag = false;
        i = -1;
        if(Resources.getSystem().getIntArray(0x1070031).length == 6) goto _L2; else goto _L1
_L1:
        Log.wtf("SignalStrength", "getLteLevel - config_lteDbmThresholds has invalid num of elements. Cannot evaluate RSRP signal.");
_L4:
        if(i != -1)
            return i;
        break; /* Loop/switch isn't completed */
_L2:
        int ai[];
        if(Build.IS_CU_CUSTOMIZATION_TEST || Build.IS_CM_CUSTOMIZATION || Build.IS_CT_CUSTOMIZATION || Build.IS_INTERNATIONAL_BUILD || SystemProperties.getBoolean("persist.radio.signal_enhance", false))
            ai = RSRP_THRESH_STRICT;
        else
            ai = RSRP_THRESH_LENIENT;
        if(mLteRsrp > ai[6])
            i = -1;
        else
        if(mLteRsrp >= ai[5] - mLteRsrpBoost)
            i = 5;
        else
        if(mLteRsrp >= ai[4] - mLteRsrpBoost)
            i = 4;
        else
        if(mLteRsrp >= ai[3] - mLteRsrpBoost)
            i = 3;
        else
        if(mLteRsrp >= ai[2] - mLteRsrpBoost)
            i = 2;
        else
        if(mLteRsrp >= ai[1] - mLteRsrpBoost)
            i = 1;
        else
        if(mLteRsrp >= ai[0])
            i = 0;
        if(true) goto _L4; else goto _L3
_L3:
        if(mLteSignalStrength <= 63) goto _L6; else goto _L5
_L5:
        i = 0;
_L8:
        return i;
_L6:
        if(mLteSignalStrength >= 12)
            i = 5;
        else
        if(mLteSignalStrength >= 9)
            i = 4;
        else
        if(mLteSignalStrength >= 6)
            i = 3;
        else
        if(mLteSignalStrength >= 3)
        {
            i = 2;
        } else
        {
            i = ((flag) ? 1 : 0);
            if(mLteSignalStrength >= 0)
                i = 1;
        }
        if(true) goto _L8; else goto _L7
_L7:
    }

    public int getLteRsrp()
    {
        return mLteRsrp;
    }

    public int getLteRsrpBoost()
    {
        return mLteRsrpBoost;
    }

    public int getLteRsrq()
    {
        return mLteRsrq;
    }

    public int getLteRssnr()
    {
        return mLteRssnr;
    }

    public int getLteSignalStrength()
    {
        return mLteSignalStrength;
    }

    public int getTdScdmaAsuLevel()
    {
        int i = getTdScdmaDbm();
        if(i == 0x7fffffff)
            i = 255;
        else
            i += 120;
        return i;
    }

    public int getTdScdmaDbm()
    {
        return mTdScdmaRscp;
    }

    public int getTdScdmaLevel()
    {
        int i = getTdScdmaDbm();
        if(i > -25 || i == 0x7fffffff)
            i = 0;
        else
        if(i >= -88)
            i = 5;
        else
        if(i >= -95)
            i = 4;
        else
        if(i >= -102)
            i = 3;
        else
        if(i >= -109)
            i = 2;
        else
        if(i >= -120)
            i = 1;
        else
            i = 0;
        return i;
    }

    public int hashCode()
    {
        int i = mGsmSignalStrength;
        int j = mGsmBitErrorRate;
        int k = mCdmaDbm;
        int l = mCdmaEcio;
        int i1 = mEvdoDbm;
        int j1 = mEvdoEcio;
        int k1 = mEvdoSnr;
        int l1 = mLteSignalStrength;
        int i2 = mLteRsrp;
        int j2 = mLteRsrq;
        int k2 = mLteRssnr;
        int l2 = mLteCqi;
        int i3 = mLteRsrpBoost;
        int j3 = mTdScdmaRscp;
        int k3;
        if(isGsm)
            k3 = 1;
        else
            k3 = 0;
        return k3 + (j3 * 31 + (i * 31 + j * 31 + k * 31 + l * 31 + i1 * 31 + j1 * 31 + k1 * 31 + l1 * 31 + i2 * 31 + j2 * 31 + k2 * 31 + l2 * 31 + i3 * 31));
    }

    public void initialize(int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1, int i2, int j2, int k2, int l2, int i3, boolean flag)
    {
        mGsmSignalStrength = i;
        mGsmBitErrorRate = j;
        mCdmaDbm = k;
        mCdmaEcio = l;
        mEvdoDbm = i1;
        mEvdoEcio = j1;
        mEvdoSnr = k1;
        mLteSignalStrength = l1;
        mLteRsrp = i2;
        mLteRsrq = j2;
        mLteRssnr = k2;
        mLteCqi = l2;
        mLteRsrpBoost = i3;
        mTdScdmaRscp = 0x7fffffff;
        isGsm = flag;
    }

    public void initialize(int i, int j, int k, int l, int i1, int j1, int k1, 
            boolean flag)
    {
        initialize(i, j, k, l, i1, j1, k1, 99, 0x7fffffff, 0x7fffffff, 0x7fffffff, 0x7fffffff, 0, flag);
    }

    public boolean isGsm()
    {
        return isGsm;
    }

    public void setGsm(boolean flag)
    {
        isGsm = flag;
    }

    public void setLteRsrpBoost(int i)
    {
        mLteRsrpBoost = i;
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder()).append("SignalStrength: ").append(mGsmSignalStrength).append(" ").append(mGsmBitErrorRate).append(" ").append(mCdmaDbm).append(" ").append(mCdmaEcio).append(" ").append(mEvdoDbm).append(" ").append(mEvdoEcio).append(" ").append(mEvdoSnr).append(" ").append(mLteSignalStrength).append(" ").append(mLteRsrp).append(" ").append(mLteRsrq).append(" ").append(mLteRssnr).append(" ").append(mLteCqi).append(" ").append(mLteRsrpBoost).append(" ").append(mTdScdmaRscp).append(" ");
        String s;
        if(isGsm)
            s = "gsm|lte";
        else
            s = "cdma";
        return stringbuilder.append(s).toString();
    }

    public void validateInput()
    {
        byte byte0 = 99;
        byte byte1 = -1;
        byte byte2 = -120;
        int i = 0x7fffffff;
        int j;
        if(mGsmSignalStrength >= 0)
            j = mGsmSignalStrength;
        else
            j = 99;
        mGsmSignalStrength = j;
        if(mCdmaDbm > 0)
            j = -mCdmaDbm;
        else
            j = -120;
        mCdmaDbm = j;
        if(mCdmaEcio > 0)
            j = -mCdmaEcio;
        else
            j = -160;
        mCdmaEcio = j;
        j = byte2;
        if(mEvdoDbm > 0)
            j = -mEvdoDbm;
        mEvdoDbm = j;
        if(mEvdoEcio >= 0)
            j = -mEvdoEcio;
        else
            j = -1;
        mEvdoEcio = j;
        j = byte1;
        if(mEvdoSnr > 0)
        {
            j = byte1;
            if(mEvdoSnr <= 8)
                j = mEvdoSnr;
        }
        mEvdoSnr = j;
        j = byte0;
        if(mLteSignalStrength >= 0)
            j = mLteSignalStrength;
        mLteSignalStrength = j;
        if(mLteRsrp >= 44 && mLteRsrp <= 140)
            j = -mLteRsrp;
        else
            j = 0x7fffffff;
        mLteRsrp = j;
        if(mLteRsrq >= 3 && mLteRsrq <= 20)
            j = -mLteRsrq;
        else
            j = 0x7fffffff;
        mLteRsrq = j;
        if(mLteRssnr >= -200 && mLteRssnr <= 300)
            j = mLteRssnr;
        else
            j = 0x7fffffff;
        mLteRssnr = j;
        j = i;
        if(mTdScdmaRscp >= 25)
        {
            j = i;
            if(mTdScdmaRscp <= 120)
                j = -mTdScdmaRscp;
        }
        mTdScdmaRscp = j;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mGsmSignalStrength);
        parcel.writeInt(mGsmBitErrorRate);
        parcel.writeInt(mCdmaDbm);
        parcel.writeInt(mCdmaEcio);
        parcel.writeInt(mEvdoDbm);
        parcel.writeInt(mEvdoEcio);
        parcel.writeInt(mEvdoSnr);
        parcel.writeInt(mLteSignalStrength);
        parcel.writeInt(mLteRsrp);
        parcel.writeInt(mLteRsrq);
        parcel.writeInt(mLteRssnr);
        parcel.writeInt(mLteCqi);
        parcel.writeInt(mLteRsrpBoost);
        parcel.writeInt(mTdScdmaRscp);
        if(isGsm)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public SignalStrength createFromParcel(Parcel parcel)
        {
            return new SignalStrength(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public SignalStrength[] newArray(int i)
        {
            return new SignalStrength[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final boolean DBG = false;
    public static final int INVALID = 0x7fffffff;
    private static final String LOG_TAG = "SignalStrength";
    public static final int NUM_SIGNAL_STRENGTH_BINS = 6;
    private static final int RSRP_THRESH_LENIENT[] = {
        -140, -140, -125, -115, -110, -102, -44
    };
    private static final int RSRP_THRESH_STRICT[] = {
        -140, -120, -115, -110, -105, -97, -44
    };
    public static final int SIGNAL_STRENGTH_EXCELLENT = 5;
    public static final int SIGNAL_STRENGTH_GOOD = 3;
    public static final int SIGNAL_STRENGTH_GREAT = 4;
    public static final int SIGNAL_STRENGTH_MODERATE = 2;
    public static final String SIGNAL_STRENGTH_NAMES[] = {
        "none", "poor", "moderate", "good", "great", "excellent"
    };
    public static final int SIGNAL_STRENGTH_NONE_OR_UNKNOWN = 0;
    public static final int SIGNAL_STRENGTH_POOR = 1;
    private boolean isGsm;
    private int mCdmaDbm;
    private int mCdmaEcio;
    private int mEvdoDbm;
    private int mEvdoEcio;
    private int mEvdoSnr;
    private int mGsmBitErrorRate;
    private int mGsmSignalStrength;
    private int mLteCqi;
    private int mLteRsrp;
    private int mLteRsrpBoost;
    private int mLteRsrq;
    private int mLteRssnr;
    private int mLteSignalStrength;
    private int mTdScdmaRscp;

}
