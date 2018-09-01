// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.securitycenter.powercenter;

import android.content.Context;
import android.os.Bundle;
import android.os.UserManager;
import com.android.internal.os.BatterySipper;
import com.android.internal.os.BatteryStatsHelper;
import java.util.*;

// Referenced classes of package miui.securitycenter.powercenter:
//            BatterySipper, BatterySipperHelper

public class PowerRankHelper
{

    public PowerRankHelper(Context context)
    {
        mMaxPower = 1.0D;
        mHelper = new BatteryStatsHelper(context);
        mHelper.create((Bundle)null);
        mUm = (UserManager)context.getSystemService("user");
        mContext = context;
    }

    private void addEntry(miui.securitycenter.powercenter.BatterySipper batterysipper)
    {
        if(batterysipper.value > mMaxPower)
            mMaxPower = batterysipper.value;
        mTotalPower = mTotalPower + batterysipper.value;
        mMiscPower = mMiscPower + batterysipper.value;
        mMiscUsageList.add(batterysipper);
    }

    public List getAppUsageList()
    {
        return mAppUsageList;
    }

    public List getMiscUsageList()
    {
        return mMiscUsageList;
    }

    public double getMiscUsageTotal()
    {
        return mMiscPower;
    }

    public double getUsageTotal()
    {
        return mTotalPower;
    }

    public void refreshStats()
    {
        mHelper.clearStats();
        mMaxPower = 0.0D;
        mTotalPower = 0.0D;
        mMiscPower = 0.0D;
        mAppUsageList.clear();
        mMiscUsageList.clear();
        mHelper.refreshStats(0, -1);
        Object obj = mHelper.getUsageList();
        miui.securitycenter.powercenter.BatterySipper batterysipper = BatterySipperHelper.makeBatterySipper(mContext, 10, null);
        for(obj = ((Iterable) (obj)).iterator(); ((Iterator) (obj)).hasNext();)
        {
            Object obj1 = (BatterySipper)((Iterator) (obj)).next();
            if(((BatterySipper) (obj1)).drainType == com.android.internal.os.BatterySipper.DrainType.APP)
            {
                obj1 = BatterySipperHelper.makeBatterySipper(mContext, 6, ((BatterySipper) (obj1)));
                mTotalPower = mTotalPower + ((miui.securitycenter.powercenter.BatterySipper) (obj1)).value;
                mAppUsageList.add(obj1);
            } else
            if(((BatterySipper) (obj1)).drainType == com.android.internal.os.BatterySipper.DrainType.PHONE)
                addEntry(BatterySipperHelper.makeBatterySipper(mContext, 2, ((BatterySipper) (obj1))));
            else
            if(((BatterySipper) (obj1)).drainType == com.android.internal.os.BatterySipper.DrainType.SCREEN)
                addEntry(BatterySipperHelper.makeBatterySipper(mContext, 5, ((BatterySipper) (obj1))));
            else
            if(((BatterySipper) (obj1)).drainType == com.android.internal.os.BatterySipper.DrainType.WIFI)
                addEntry(BatterySipperHelper.makeBatterySipper(mContext, 3, ((BatterySipper) (obj1))));
            else
            if(((BatterySipper) (obj1)).drainType == com.android.internal.os.BatterySipper.DrainType.BLUETOOTH)
                addEntry(BatterySipperHelper.makeBatterySipper(mContext, 4, ((BatterySipper) (obj1))));
            else
            if(((BatterySipper) (obj1)).drainType == com.android.internal.os.BatterySipper.DrainType.IDLE)
                addEntry(BatterySipperHelper.makeBatterySipper(mContext, 0, ((BatterySipper) (obj1))));
            else
            if(((BatterySipper) (obj1)).drainType == com.android.internal.os.BatterySipper.DrainType.CELL)
                addEntry(BatterySipperHelper.makeBatterySipper(mContext, 1, ((BatterySipper) (obj1))));
            else
                BatterySipperHelper.addBatterySipper(batterysipper, ((BatterySipper) (obj1)));
        }

        if(batterysipper.usageTime > 0L && batterysipper.value > 0.0D)
            addEntry(batterysipper);
        if(mAppUsageList.size() >= 2)
            Collections.sort(mAppUsageList);
        if(mMiscUsageList.size() >= 2)
            Collections.sort(mMiscUsageList);
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "PowerRankHelper";
    private final List mAppUsageList = new ArrayList();
    private Context mContext;
    private BatteryStatsHelper mHelper;
    private double mMaxPower;
    private double mMiscPower;
    private final List mMiscUsageList = new ArrayList();
    private double mTotalPower;
    private UserManager mUm;
}
