// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.content.Context;
import android.os.*;
import java.io.PrintWriter;

// Referenced classes of package android.location:
//            ILocationPolicyManager, LocationPolicy, ILocationPolicyListener, Location

public class LocationPolicyManager
{

    public LocationPolicyManager(ILocationPolicyManager ilocationpolicymanager)
    {
        if(ilocationpolicymanager == null)
        {
            throw new IllegalArgumentException("missing ILocationPolicyManager");
        } else
        {
            mService = ilocationpolicymanager;
            return;
        }
    }

    public static void dumpPolicy(PrintWriter printwriter, int i)
    {
        printwriter.write("[");
        if((i & 0xff) != 0)
            printwriter.write("REJECT_ALL_BACKGROUND");
        if((i & 1) != 0)
            printwriter.write("REJECT_HIGH_POWER_BACKGROUND");
        if((i & 2) != 0)
            printwriter.write("REJECT_NON_PASSIVE_BACKGROUND");
        printwriter.write("]");
    }

    public static void dumpRules(PrintWriter printwriter, int i)
    {
        printwriter.write("[");
        if((i & 0xff) != 0)
            printwriter.write("REJECT_ALL");
        if((i & 1) != 0)
            printwriter.write("REJECT_HIGH_POWER");
        if((i & 2) != 0)
            printwriter.write("REJECT_NON_PASSIVE");
        printwriter.write("]");
    }

    public static LocationPolicyManager from(Context context)
    {
        return (LocationPolicyManager)context.getSystemService("locationpolicy");
    }

    public static boolean isAllowedByLocationPolicy(Context context, int i)
    {
        if(sInstance == null)
            sInstance = from(context);
        return sInstance.checkUidLocationOp(Binder.getCallingUid(), i);
    }

    public static boolean isAllowedByLocationPolicy(Context context, int i, int j)
    {
        if(sInstance == null)
            sInstance = from(context);
        return sInstance.checkUidLocationOp(i, j);
    }

    public static boolean isUidValidForPolicy(Context context, int i)
    {
        return UserHandle.isApp(i);
    }

    public boolean checkUidLocationOp(int i, int j)
    {
        boolean flag;
        try
        {
            flag = mService.checkUidLocationOp(i, j);
        }
        catch(RemoteException remoteexception)
        {
            remoteexception.printStackTrace();
            return true;
        }
        return flag;
    }

    public boolean checkUidNavigationScreenLock(int i)
    {
        boolean flag;
        try
        {
            flag = mService.checkUidNavigationScreenLock(i);
        }
        catch(RemoteException remoteexception)
        {
            remoteexception.printStackTrace();
            return false;
        }
        return flag;
    }

    public LocationPolicy[] getLocationPolicies()
    {
        LocationPolicy alocationpolicy[];
        try
        {
            alocationpolicy = mService.getLocationPolicies();
        }
        catch(RemoteException remoteexception)
        {
            remoteexception.printStackTrace();
            return null;
        }
        return alocationpolicy;
    }

    public boolean getRestrictBackground()
    {
        boolean flag;
        try
        {
            flag = mService.getRestrictBackground();
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return flag;
    }

    public int getUidPolicy(int i)
    {
        try
        {
            i = mService.getUidPolicy(i);
        }
        catch(RemoteException remoteexception)
        {
            remoteexception.printStackTrace();
            return 0;
        }
        return i;
    }

    public int[] getUidsWithPolicy(int i)
    {
        int ai[];
        try
        {
            ai = mService.getUidsWithPolicy(i);
        }
        catch(RemoteException remoteexception)
        {
            remoteexception.printStackTrace();
            return new int[0];
        }
        return ai;
    }

    public void registerListener(ILocationPolicyListener ilocationpolicylistener)
    {
        mService.registerListener(ilocationpolicylistener);
_L1:
        return;
        ilocationpolicylistener;
        ilocationpolicylistener.printStackTrace();
          goto _L1
    }

    public void setFakeGpsFeatureOnState(boolean flag)
    {
        mService.setFakeGpsFeatureOnState(flag);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void setLocationPolicies(LocationPolicy alocationpolicy[])
    {
        mService.setLocationPolicies(alocationpolicy);
_L1:
        return;
        alocationpolicy;
        alocationpolicy.printStackTrace();
          goto _L1
    }

    public void setPhoneStationary(boolean flag, Location location)
    {
        mService.setPhoneStationary(flag, location);
_L2:
        return;
        location;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void setRestrictBackground(boolean flag)
    {
        mService.setRestrictBackground(flag);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void setUidNavigationStart(int i)
    {
        mService.setUidNavigationStart(i);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        remoteexception.printStackTrace();
          goto _L1
    }

    public void setUidNavigationStop(int i)
    {
        mService.setUidNavigationStop(i);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        remoteexception.printStackTrace();
          goto _L1
    }

    public void setUidPolicy(int i, int j)
    {
        mService.setUidPolicy(i, j);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        remoteexception.printStackTrace();
          goto _L1
    }

    public void unregisterListener(ILocationPolicyListener ilocationpolicylistener)
    {
        mService.unregisterListener(ilocationpolicylistener);
_L1:
        return;
        ilocationpolicylistener;
        ilocationpolicylistener.printStackTrace();
          goto _L1
    }

    private static final boolean ALLOW_PLATFORM_APP_POLICY = true;
    public static final int OP_BLE_SCAN_LOCATION = 3;
    public static final int OP_GET_CELL_LOCATION = 1;
    public static final int OP_GET_GPS_LOCATION = 0;
    public static final int OP_WIFI_SCAN_LOCATION = 2;
    public static final int POLICY_NONE = 0;
    public static final int POLICY_REJECT_ALL_BACKGROUND = 255;
    public static final int POLICY_REJECT_HIGH_POWER_BACKGROUND = 1;
    public static final int POLICY_REJECT_NON_PASSIVE_BACKGROUND = 2;
    public static final int RULE_ALLOW_ALL = 0;
    public static final int RULE_REJECT_ALL = 255;
    public static final int RULE_REJECT_HIGH_POWER = 1;
    public static final int RULE_REJECT_NON_PASSIVE = 2;
    private static LocationPolicyManager sInstance = null;
    private ILocationPolicyManager mService;

}
