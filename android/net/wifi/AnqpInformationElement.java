// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi;


public class AnqpInformationElement
{

    public AnqpInformationElement(int i, int j, byte abyte0[])
    {
        mVendorId = i;
        mElementId = j;
        mPayload = abyte0;
    }

    public int getElementId()
    {
        return mElementId;
    }

    public byte[] getPayload()
    {
        return mPayload;
    }

    public int getVendorId()
    {
        return mVendorId;
    }

    public static final int ANQP_3GPP_NETWORK = 264;
    public static final int ANQP_CAPABILITY_LIST = 257;
    public static final int ANQP_CIVIC_LOC = 266;
    public static final int ANQP_DOM_NAME = 268;
    public static final int ANQP_EMERGENCY_ALERT = 269;
    public static final int ANQP_EMERGENCY_NAI = 271;
    public static final int ANQP_EMERGENCY_NUMBER = 259;
    public static final int ANQP_GEO_LOC = 265;
    public static final int ANQP_IP_ADDR_AVAILABILITY = 262;
    public static final int ANQP_LOC_URI = 267;
    public static final int ANQP_NAI_REALM = 263;
    public static final int ANQP_NEIGHBOR_REPORT = 272;
    public static final int ANQP_NWK_AUTH_TYPE = 260;
    public static final int ANQP_QUERY_LIST = 256;
    public static final int ANQP_ROAMING_CONSORTIUM = 261;
    public static final int ANQP_TDLS_CAP = 270;
    public static final int ANQP_VENDOR_SPEC = 56797;
    public static final int ANQP_VENUE_NAME = 258;
    public static final int HOTSPOT20_VENDOR_ID = 0x506f9a;
    public static final int HS_CAPABILITY_LIST = 2;
    public static final int HS_CONN_CAPABILITY = 5;
    public static final int HS_FRIENDLY_NAME = 3;
    public static final int HS_ICON_FILE = 11;
    public static final int HS_ICON_REQUEST = 10;
    public static final int HS_NAI_HOME_REALM_QUERY = 6;
    public static final int HS_OPERATING_CLASS = 7;
    public static final int HS_OSU_PROVIDERS = 8;
    public static final int HS_QUERY_LIST = 1;
    public static final int HS_WAN_METRICS = 4;
    private final int mElementId;
    private final byte mPayload[];
    private final int mVendorId;
}
