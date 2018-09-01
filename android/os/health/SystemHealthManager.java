// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.health;

import android.content.Context;
import android.os.*;
import com.android.internal.app.IBatteryStats;

// Referenced classes of package android.os.health:
//            HealthStatsParceler, HealthStats

public class SystemHealthManager
{

    public SystemHealthManager()
    {
        this(com.android.internal.app.IBatteryStats.Stub.asInterface(ServiceManager.getService("batterystats")));
    }

    public SystemHealthManager(IBatteryStats ibatterystats)
    {
        mBatteryStats = ibatterystats;
    }

    public static SystemHealthManager from(Context context)
    {
        return (SystemHealthManager)context.getSystemService("systemhealth");
    }

    public HealthStats takeMyUidSnapshot()
    {
        return takeUidSnapshot(Process.myUid());
    }

    public HealthStats takeUidSnapshot(int i)
    {
        HealthStats healthstats;
        try
        {
            healthstats = mBatteryStats.takeUidSnapshot(i).getHealthStats();
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException(remoteexception);
        }
        return healthstats;
    }

    public HealthStats[] takeUidSnapshots(int ai[])
    {
        HealthStatsParceler ahealthstatsparceler[];
        HealthStats ahealthstats[];
        int i;
        int j;
        try
        {
            ahealthstatsparceler = mBatteryStats.takeUidSnapshots(ai);
            ahealthstats = new HealthStats[ai.length];
            i = ai.length;
        }
        // Misplaced declaration of an exception variable
        catch(int ai[])
        {
            throw new RuntimeException(ai);
        }
        j = 0;
        if(j >= i)
            break; /* Loop/switch isn't completed */
        ahealthstats[j] = ahealthstatsparceler[j].getHealthStats();
        j++;
        if(true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_24;
_L1:
        return ahealthstats;
    }

    private final IBatteryStats mBatteryStats;
}
