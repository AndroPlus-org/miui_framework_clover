// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.telephony.RILConstants;

public class RadioAccessFamily
    implements Parcelable
{

    public RadioAccessFamily(int i, int j)
    {
        mPhoneId = i;
        mRadioAccessFamily = j;
    }

    private static int getAdjustedRaf(int i)
    {
        int j = i;
        if((0x10006 & i) > 0)
            j = i | 0x10006;
        i = j;
        if((0x8e08 & j) > 0)
            i = j | 0x8e08;
        j = i;
        if((i & 0x70) > 0)
            j = i | 0x70;
        i = j;
        if((j & 0x3180) > 0)
            i = j | 0x3180;
        j = i;
        if((0x84000 & i) > 0)
            j = i | 0x84000;
        return j;
    }

    public static int getHighestRafCapability(int i)
    {
        if((0x84000 & i) > 0)
            return 3;
        if((0x8e08 & i | 0xbf80) > 0)
            return 2;
        return (i & 0x70 | 0x10006) <= 0 ? 0 : 1;
    }

    public static int getNetworkTypeFromRaf(int i)
    {
        getAdjustedRaf(i);
        JVM INSTR lookupswitch 22: default 192
    //                   112: 248
    //                   12672: 253
    //                   12784: 213
    //                   36360: 208
    //                   65542: 203
    //                   101902: 198
    //                   114686: 259
    //                   131072: 265
    //                   167432: 271
    //                   196614: 283
    //                   232974: 295
    //                   245758: 313
    //                   540672: 236
    //                   553456: 218
    //                   577032: 242
    //                   642574: 224
    //                   655358: 230
    //                   671744: 277
    //                   708104: 301
    //                   737286: 289
    //                   773646: 307
    //                   786430: 319;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23
_L1:
        i = RILConstants.PREFERRED_NETWORK_MODE;
_L25:
        return i;
_L7:
        i = 0;
        continue; /* Loop/switch isn't completed */
_L6:
        i = 1;
        continue; /* Loop/switch isn't completed */
_L5:
        i = 2;
        continue; /* Loop/switch isn't completed */
_L4:
        i = 4;
        continue; /* Loop/switch isn't completed */
_L15:
        i = 8;
        continue; /* Loop/switch isn't completed */
_L17:
        i = 9;
        continue; /* Loop/switch isn't completed */
_L18:
        i = 10;
        continue; /* Loop/switch isn't completed */
_L14:
        i = 11;
        continue; /* Loop/switch isn't completed */
_L16:
        i = 12;
        continue; /* Loop/switch isn't completed */
_L2:
        i = 5;
        continue; /* Loop/switch isn't completed */
_L3:
        i = 6;
        continue; /* Loop/switch isn't completed */
_L8:
        i = 7;
        continue; /* Loop/switch isn't completed */
_L9:
        i = 13;
        continue; /* Loop/switch isn't completed */
_L10:
        i = 14;
        continue; /* Loop/switch isn't completed */
_L19:
        i = 15;
        continue; /* Loop/switch isn't completed */
_L11:
        i = 16;
        continue; /* Loop/switch isn't completed */
_L21:
        i = 17;
        continue; /* Loop/switch isn't completed */
_L12:
        i = 18;
        continue; /* Loop/switch isn't completed */
_L20:
        i = 19;
        continue; /* Loop/switch isn't completed */
_L22:
        i = 20;
        continue; /* Loop/switch isn't completed */
_L13:
        i = 21;
        continue; /* Loop/switch isn't completed */
_L23:
        i = 22;
        if(true) goto _L25; else goto _L24
_L24:
    }

    public static int getRafFromNetworkType(int i)
    {
        i;
        JVM INSTR tableswitch 0 22: default 108
    //                   0 112
    //                   1 118
    //                   2 124
    //                   3 130
    //                   4 136
    //                   5 173
    //                   6 179
    //                   7 186
    //                   8 143
    //                   9 149
    //                   10 155
    //                   11 161
    //                   12 167
    //                   13 192
    //                   14 198
    //                   15 204
    //                   16 210
    //                   17 216
    //                   18 222
    //                   19 228
    //                   20 234
    //                   21 240
    //                   22 246;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24
_L1:
        i = 1;
_L26:
        return i;
_L2:
        i = 0x18e0e;
        continue; /* Loop/switch isn't completed */
_L3:
        i = 0x10006;
        continue; /* Loop/switch isn't completed */
_L4:
        i = 36360;
        continue; /* Loop/switch isn't completed */
_L5:
        i = 0x18e0e;
        continue; /* Loop/switch isn't completed */
_L6:
        i = 12784;
        continue; /* Loop/switch isn't completed */
_L10:
        i = 0x871f0;
        continue; /* Loop/switch isn't completed */
_L11:
        i = 0x9ce0e;
        continue; /* Loop/switch isn't completed */
_L12:
        i = 0x9fffe;
        continue; /* Loop/switch isn't completed */
_L13:
        i = 0x84000;
        continue; /* Loop/switch isn't completed */
_L14:
        i = 0x8ce08;
        continue; /* Loop/switch isn't completed */
_L7:
        i = 112;
        continue; /* Loop/switch isn't completed */
_L8:
        i = 12672;
        continue; /* Loop/switch isn't completed */
_L9:
        i = 0x1bffe;
        continue; /* Loop/switch isn't completed */
_L15:
        i = 0x20000;
        continue; /* Loop/switch isn't completed */
_L16:
        i = 0x28e08;
        continue; /* Loop/switch isn't completed */
_L17:
        i = 0xa4000;
        continue; /* Loop/switch isn't completed */
_L18:
        i = 0x30006;
        continue; /* Loop/switch isn't completed */
_L19:
        i = 0xb4006;
        continue; /* Loop/switch isn't completed */
_L20:
        i = 0x38e0e;
        continue; /* Loop/switch isn't completed */
_L21:
        i = 0xace08;
        continue; /* Loop/switch isn't completed */
_L22:
        i = 0xbce0e;
        continue; /* Loop/switch isn't completed */
_L23:
        i = 0x3bffe;
        continue; /* Loop/switch isn't completed */
_L24:
        i = 0xbfffe;
        if(true) goto _L26; else goto _L25
_L25:
    }

    public static int rafTypeFromString(String s)
    {
        s = s.toUpperCase().split("\\|");
        int i = 0;
        int j = 0;
        for(int k = s.length; j < k; j++)
        {
            int l = singleRafTypeFromString(s[j].trim());
            if(l == 1)
                return l;
            i |= l;
        }

        return i;
    }

    public static int singleRafTypeFromString(String s)
    {
        if(s.equals("GPRS"))
            return 2;
        if(s.equals("EDGE"))
            return 4;
        if(s.equals("UMTS"))
            return 8;
        if(s.equals("IS95A"))
            return 16;
        if(s.equals("IS95B"))
            return 32;
        if(s.equals("1XRTT"))
            return 64;
        if(s.equals("EVDO_0"))
            return 128;
        if(s.equals("EVDO_A"))
            return 256;
        if(s.equals("HSDPA"))
            return 512;
        if(s.equals("HSUPA"))
            return 1024;
        if(s.equals("HSPA"))
            return 2048;
        if(s.equals("EVDO_B"))
            return 4096;
        if(s.equals("EHRPD"))
            return 8192;
        if(s.equals("LTE"))
            return 16384;
        if(s.equals("HSPAP"))
            return 32768;
        if(s.equals("GSM"))
            return 0x10000;
        if(s.equals("TD_SCDMA"))
            return 0x20000;
        if(s.equals("HS"))
            return 36352;
        if(s.equals("CDMA"))
            return 112;
        if(s.equals("EVDO"))
            return 12672;
        if(s.equals("WCDMA"))
            return 36360;
        return !s.equals("LTE_CA") ? 1 : 0x80000;
    }

    public int describeContents()
    {
        return 0;
    }

    public int getPhoneId()
    {
        return mPhoneId;
    }

    public int getRadioAccessFamily()
    {
        return mRadioAccessFamily;
    }

    public String toString()
    {
        return (new StringBuilder()).append("{ mPhoneId = ").append(mPhoneId).append(", mRadioAccessFamily = ").append(mRadioAccessFamily).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mPhoneId);
        parcel.writeInt(mRadioAccessFamily);
    }

    private static final int CDMA = 112;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public RadioAccessFamily createFromParcel(Parcel parcel)
        {
            return new RadioAccessFamily(parcel.readInt(), parcel.readInt());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public RadioAccessFamily[] newArray(int i)
        {
            return new RadioAccessFamily[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int EVDO = 12672;
    private static final int GSM = 0x10006;
    private static final int HS = 36352;
    private static final int LTE = 0x84000;
    public static final int RAF_1xRTT = 64;
    public static final int RAF_EDGE = 4;
    public static final int RAF_EHRPD = 8192;
    public static final int RAF_EVDO_0 = 128;
    public static final int RAF_EVDO_A = 256;
    public static final int RAF_EVDO_B = 4096;
    public static final int RAF_GPRS = 2;
    public static final int RAF_GSM = 0x10000;
    public static final int RAF_HSDPA = 512;
    public static final int RAF_HSPA = 2048;
    public static final int RAF_HSPAP = 32768;
    public static final int RAF_HSUPA = 1024;
    public static final int RAF_IS95A = 16;
    public static final int RAF_IS95B = 32;
    public static final int RAF_LTE = 16384;
    public static final int RAF_LTE_CA = 0x80000;
    public static final int RAF_TD_SCDMA = 0x20000;
    public static final int RAF_UMTS = 8;
    public static final int RAF_UNKNOWN = 1;
    private static final int WCDMA = 36360;
    private int mPhoneId;
    private int mRadioAccessFamily;

}
