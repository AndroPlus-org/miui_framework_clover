// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony.gsm;


public final class SmsBroadcastConfigInfo
{

    public SmsBroadcastConfigInfo(int i, int j, int k, int l, boolean flag)
    {
        mFromServiceId = i;
        mToServiceId = j;
        mFromCodeScheme = k;
        mToCodeScheme = l;
        mSelected = flag;
    }

    public int getFromCodeScheme()
    {
        return mFromCodeScheme;
    }

    public int getFromServiceId()
    {
        return mFromServiceId;
    }

    public int getToCodeScheme()
    {
        return mToCodeScheme;
    }

    public int getToServiceId()
    {
        return mToServiceId;
    }

    public boolean isSelected()
    {
        return mSelected;
    }

    public void setFromCodeScheme(int i)
    {
        mFromCodeScheme = i;
    }

    public void setFromServiceId(int i)
    {
        mFromServiceId = i;
    }

    public void setSelected(boolean flag)
    {
        mSelected = flag;
    }

    public void setToCodeScheme(int i)
    {
        mToCodeScheme = i;
    }

    public void setToServiceId(int i)
    {
        mToServiceId = i;
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder()).append("SmsBroadcastConfigInfo: Id [").append(mFromServiceId).append(',').append(mToServiceId).append("] Code [").append(mFromCodeScheme).append(',').append(mToCodeScheme).append("] ");
        String s;
        if(mSelected)
            s = "ENABLED";
        else
            s = "DISABLED";
        return stringbuilder.append(s).toString();
    }

    private int mFromCodeScheme;
    private int mFromServiceId;
    private boolean mSelected;
    private int mToCodeScheme;
    private int mToServiceId;
}
