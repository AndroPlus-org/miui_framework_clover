// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.content.Context;
import android.util.Log;

// Referenced classes of package android.os:
//            RemoteException, IHardwarePropertiesManager, CpuUsageInfo

public class HardwarePropertiesManager
{

    public HardwarePropertiesManager(Context context, IHardwarePropertiesManager ihardwarepropertiesmanager)
    {
        mContext = context;
        mService = ihardwarepropertiesmanager;
    }

    public CpuUsageInfo[] getCpuUsages()
    {
        CpuUsageInfo acpuusageinfo[];
        try
        {
            acpuusageinfo = mService.getCpuUsages(mContext.getOpPackageName());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return acpuusageinfo;
    }

    public float[] getDeviceTemperatures(int i, int j)
    {
        switch(i)
        {
        default:
            Log.w(TAG, "Unknown device temperature type.");
            return new float[0];

        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
            switch(j)
            {
            default:
                Log.w(TAG, "Unknown device temperature source.");
                return new float[0];

            case 0: // '\0'
            case 1: // '\001'
            case 2: // '\002'
            case 3: // '\003'
                break;
            }
            break;
        }
        float af[];
        try
        {
            af = mService.getDeviceTemperatures(mContext.getOpPackageName(), i, j);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return af;
    }

    public float[] getFanSpeeds()
    {
        float af[];
        try
        {
            af = mService.getFanSpeeds(mContext.getOpPackageName());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return af;
    }

    public static final int DEVICE_TEMPERATURE_BATTERY = 2;
    public static final int DEVICE_TEMPERATURE_CPU = 0;
    public static final int DEVICE_TEMPERATURE_GPU = 1;
    public static final int DEVICE_TEMPERATURE_SKIN = 3;
    private static final String TAG = android/os/HardwarePropertiesManager.getSimpleName();
    public static final int TEMPERATURE_CURRENT = 0;
    public static final int TEMPERATURE_SHUTDOWN = 2;
    public static final int TEMPERATURE_THROTTLING = 1;
    public static final int TEMPERATURE_THROTTLING_BELOW_VR_MIN = 3;
    public static final float UNDEFINED_TEMPERATURE = -3.402823E+038F;
    private final Context mContext;
    private final IHardwarePropertiesManager mService;

}
