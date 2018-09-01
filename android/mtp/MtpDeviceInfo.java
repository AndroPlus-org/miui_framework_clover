// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.mtp;


public class MtpDeviceInfo
{

    private MtpDeviceInfo()
    {
    }

    private static boolean isSupported(int ai[], int i)
    {
        int j = ai.length;
        for(int k = 0; k < j; k++)
            if(ai[k] == i)
                return true;

        return false;
    }

    public final int[] getEventsSupported()
    {
        return mEventsSupported;
    }

    public final String getManufacturer()
    {
        return mManufacturer;
    }

    public final String getModel()
    {
        return mModel;
    }

    public final int[] getOperationsSupported()
    {
        return mOperationsSupported;
    }

    public final String getSerialNumber()
    {
        return mSerialNumber;
    }

    public final String getVersion()
    {
        return mVersion;
    }

    public boolean isEventSupported(int i)
    {
        return isSupported(mEventsSupported, i);
    }

    public boolean isOperationSupported(int i)
    {
        return isSupported(mOperationsSupported, i);
    }

    private int mEventsSupported[];
    private String mManufacturer;
    private String mModel;
    private int mOperationsSupported[];
    private String mSerialNumber;
    private String mVersion;
}
