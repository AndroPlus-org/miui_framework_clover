// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.gsm;

import android.os.Bundle;
import android.telephony.CellLocation;
import miui.telephony.PhoneDebug;

public class GsmCellLocation extends CellLocation
{

    public GsmCellLocation()
    {
        mLac = -1;
        mCid = -1;
        mPsc = -1;
    }

    public GsmCellLocation(Bundle bundle)
    {
        mLac = bundle.getInt("lac", -1);
        mCid = bundle.getInt("cid", -1);
        mPsc = bundle.getInt("psc", -1);
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
        GsmCellLocation gsmcelllocation;
        try
        {
            gsmcelllocation = (GsmCellLocation)obj;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            return false;
        }
        if(obj == null)
            return false;
        boolean flag1 = flag;
        if(equalsHandlesNulls(Integer.valueOf(mLac), Integer.valueOf(gsmcelllocation.mLac)))
        {
            flag1 = flag;
            if(equalsHandlesNulls(Integer.valueOf(mCid), Integer.valueOf(gsmcelllocation.mCid)))
                flag1 = equalsHandlesNulls(Integer.valueOf(mPsc), Integer.valueOf(gsmcelllocation.mPsc));
        }
        return flag1;
    }

    public void fillInNotifierBundle(Bundle bundle)
    {
        bundle.putInt("lac", mLac);
        bundle.putInt("cid", mCid);
        bundle.putInt("psc", mPsc);
    }

    public int getCid()
    {
        return mCid;
    }

    public int getLac()
    {
        return mLac;
    }

    public int getPsc()
    {
        return mPsc;
    }

    public int hashCode()
    {
        return mLac ^ mCid;
    }

    public boolean isEmpty()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mLac == -1)
        {
            flag1 = flag;
            if(mCid == -1)
            {
                flag1 = flag;
                if(mPsc == -1)
                    flag1 = true;
            }
        }
        return flag1;
    }

    public void setLacAndCid(int i, int j)
    {
        mLac = i;
        mCid = j;
    }

    public void setPsc(int i)
    {
        mPsc = i;
    }

    public void setStateInvalid()
    {
        mLac = -1;
        mCid = -1;
        mPsc = -1;
    }

    public String toString()
    {
        if(!PhoneDebug.VDBG)
            return "GsmCellLocation:[...]";
        else
            return (new StringBuilder()).append("[").append(mLac).append(",").append(mCid).append(",").append(mPsc).append("]").toString();
    }

    private int mCid;
    private int mLac;
    private int mPsc;
}
