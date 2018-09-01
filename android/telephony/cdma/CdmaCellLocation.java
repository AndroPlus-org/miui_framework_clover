// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.cdma;

import android.os.Bundle;
import android.telephony.CellLocation;
import miui.telephony.PhoneDebug;

public class CdmaCellLocation extends CellLocation
{

    public CdmaCellLocation()
    {
        mBaseStationId = -1;
        mBaseStationLatitude = 0x7fffffff;
        mBaseStationLongitude = 0x7fffffff;
        mSystemId = -1;
        mNetworkId = -1;
        mBaseStationId = -1;
        mBaseStationLatitude = 0x7fffffff;
        mBaseStationLongitude = 0x7fffffff;
        mSystemId = -1;
        mNetworkId = -1;
    }

    public CdmaCellLocation(Bundle bundle)
    {
        mBaseStationId = -1;
        mBaseStationLatitude = 0x7fffffff;
        mBaseStationLongitude = 0x7fffffff;
        mSystemId = -1;
        mNetworkId = -1;
        mBaseStationId = bundle.getInt("baseStationId", mBaseStationId);
        mBaseStationLatitude = bundle.getInt("baseStationLatitude", mBaseStationLatitude);
        mBaseStationLongitude = bundle.getInt("baseStationLongitude", mBaseStationLongitude);
        mSystemId = bundle.getInt("systemId", mSystemId);
        mNetworkId = bundle.getInt("networkId", mNetworkId);
    }

    public static double convertQuartSecToDecDegrees(int i)
    {
        while(Double.isNaN(i) || i < 0xffd87300 || i > 0x278d00) 
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid coordiante value:").append(i).toString());
        return (double)i / 14400D;
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

    public boolean equals(Object obj)
    {
        boolean flag = false;
        CdmaCellLocation cdmacelllocation;
        try
        {
            cdmacelllocation = (CdmaCellLocation)obj;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            return false;
        }
        if(obj == null)
            return false;
        boolean flag1 = flag;
        if(equalsHandlesNulls(Integer.valueOf(mBaseStationId), Integer.valueOf(cdmacelllocation.mBaseStationId)))
        {
            flag1 = flag;
            if(equalsHandlesNulls(Integer.valueOf(mBaseStationLatitude), Integer.valueOf(cdmacelllocation.mBaseStationLatitude)))
            {
                flag1 = flag;
                if(equalsHandlesNulls(Integer.valueOf(mBaseStationLongitude), Integer.valueOf(cdmacelllocation.mBaseStationLongitude)))
                {
                    flag1 = flag;
                    if(equalsHandlesNulls(Integer.valueOf(mSystemId), Integer.valueOf(cdmacelllocation.mSystemId)))
                        flag1 = equalsHandlesNulls(Integer.valueOf(mNetworkId), Integer.valueOf(cdmacelllocation.mNetworkId));
                }
            }
        }
        return flag1;
    }

    public void fillInNotifierBundle(Bundle bundle)
    {
        bundle.putInt("baseStationId", mBaseStationId);
        bundle.putInt("baseStationLatitude", mBaseStationLatitude);
        bundle.putInt("baseStationLongitude", mBaseStationLongitude);
        bundle.putInt("systemId", mSystemId);
        bundle.putInt("networkId", mNetworkId);
    }

    public int getBaseStationId()
    {
        return mBaseStationId;
    }

    public int getBaseStationLatitude()
    {
        return mBaseStationLatitude;
    }

    public int getBaseStationLongitude()
    {
        return mBaseStationLongitude;
    }

    public int getNetworkId()
    {
        return mNetworkId;
    }

    public int getSystemId()
    {
        return mSystemId;
    }

    public int hashCode()
    {
        return mBaseStationId ^ mBaseStationLatitude ^ mBaseStationLongitude ^ mSystemId ^ mNetworkId;
    }

    public boolean isEmpty()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mBaseStationId == -1)
        {
            flag1 = flag;
            if(mBaseStationLatitude == 0x7fffffff)
            {
                flag1 = flag;
                if(mBaseStationLongitude == 0x7fffffff)
                {
                    flag1 = flag;
                    if(mSystemId == -1)
                    {
                        flag1 = flag;
                        if(mNetworkId == -1)
                            flag1 = true;
                    }
                }
            }
        }
        return flag1;
    }

    public void setCellLocationData(int i, int j, int k)
    {
        mBaseStationId = i;
        mBaseStationLatitude = j;
        mBaseStationLongitude = k;
    }

    public void setCellLocationData(int i, int j, int k, int l, int i1)
    {
        mBaseStationId = i;
        mBaseStationLatitude = j;
        mBaseStationLongitude = k;
        mSystemId = l;
        mNetworkId = i1;
    }

    public void setStateInvalid()
    {
        mBaseStationId = -1;
        mBaseStationLatitude = 0x7fffffff;
        mBaseStationLongitude = 0x7fffffff;
        mSystemId = -1;
        mNetworkId = -1;
    }

    public String toString()
    {
        if(!PhoneDebug.VDBG)
            return "CdmaCellLocation:[...]";
        else
            return (new StringBuilder()).append("[").append(mBaseStationId).append(",").append(mBaseStationLatitude).append(",").append(mBaseStationLongitude).append(",").append(mSystemId).append(",").append(mNetworkId).append("]").toString();
    }

    public static final int INVALID_LAT_LONG = 0x7fffffff;
    private int mBaseStationId;
    private int mBaseStationLatitude;
    private int mBaseStationLongitude;
    private int mNetworkId;
    private int mSystemId;
}
