// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.content.*;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.os.*;
import android.util.*;
import com.android.internal.app.IBatteryStats;
import com.android.internal.util.ArrayUtils;
import java.io.*;
import java.util.*;

// Referenced classes of package com.android.internal.os:
//            BatterySipper, PowerCalculator, PowerProfile, MobileRadioPowerCalculator, 
//            BatteryStatsImpl, CpuPowerCalculator, MemoryPowerCalculator, WakelockPowerCalculator, 
//            WifiPowerCalculator, BluetoothPowerCalculator, SensorPowerCalculator, CameraPowerCalculator, 
//            FlashlightPowerCalculator, WifiPowerEstimator

public class BatteryStatsHelper
{

    public BatteryStatsHelper(Context context)
    {
        this(context, true);
    }

    public BatteryStatsHelper(Context context, boolean flag)
    {
        this(context, flag, checkWifiOnly(context));
    }

    public BatteryStatsHelper(Context context, boolean flag, boolean flag1)
    {
        mUsageList = new ArrayList();
        mWifiSippers = new ArrayList();
        mBluetoothSippers = new ArrayList();
        mUserSippers = new SparseArray();
        mMobilemsppList = new ArrayList();
        mStatsType = 0;
        mStatsPeriod = 0L;
        mMaxPower = 1.0D;
        mMaxRealPower = 1.0D;
        mHasWifiPowerReporting = false;
        mHasBluetoothPowerReporting = false;
        mContext = context;
        mCollectBatteryBroadcast = flag;
        mWifiOnly = flag1;
        mPackageManager = context.getPackageManager();
        context = context.getResources();
        mSystemPackageArray = context.getStringArray(0x1070014);
        mServicepackageArray = context.getStringArray(0x1070013);
    }

    private void addBluetoothUsage()
    {
        BatterySipper batterysipper = new BatterySipper(BatterySipper.DrainType.BLUETOOTH, null, 0.0D);
        mBluetoothPowerCalculator.calculateRemaining(batterysipper, mStats, mRawRealtimeUs, mRawUptimeUs, mStatsType);
        aggregateSippers(batterysipper, mBluetoothSippers, "Bluetooth");
        if(batterysipper.totalPowerMah > 0.0D)
            mUsageList.add(batterysipper);
    }

    private BatterySipper addEntry(BatterySipper.DrainType draintype, long l, double d)
    {
        draintype = new BatterySipper(draintype, null, 0.0D);
        draintype.usagePowerMah = d;
        draintype.usageTimeMs = l;
        draintype.sumPower();
        mUsageList.add(draintype);
        return draintype;
    }

    private void addIdleUsage()
    {
        double d = ((double)(mTypeBatteryRealtimeUs / 1000L) * mPowerProfile.getAveragePower("cpu.idle") + (double)(mTypeBatteryUptimeUs / 1000L) * mPowerProfile.getAveragePower("cpu.awake")) / 3600000D;
        if(d != 0.0D)
            addEntry(BatterySipper.DrainType.IDLE, mTypeBatteryRealtimeUs / 1000L, d);
    }

    private void addMemoryUsage()
    {
        BatterySipper batterysipper = new BatterySipper(BatterySipper.DrainType.MEMORY, null, 0.0D);
        mMemoryPowerCalculator.calculateRemaining(batterysipper, mStats, mRawRealtimeUs, mRawUptimeUs, mStatsType);
        batterysipper.sumPower();
        if(batterysipper.totalPowerMah > 0.0D)
            mUsageList.add(batterysipper);
    }

    private void addPhoneUsage()
    {
        long l = mStats.getPhoneOnTime(mRawRealtimeUs, mStatsType) / 1000L;
        double d = (mPowerProfile.getAveragePower("radio.active") * (double)l) / 3600000D;
        if(d != 0.0D)
            addEntry(BatterySipper.DrainType.PHONE, l, d);
    }

    private void addRadioUsage()
    {
        BatterySipper batterysipper = new BatterySipper(BatterySipper.DrainType.CELL, null, 0.0D);
        mMobileRadioPowerCalculator.calculateRemaining(batterysipper, mStats, mRawRealtimeUs, mRawUptimeUs, mStatsType);
        batterysipper.sumPower();
        if(batterysipper.totalPowerMah > 0.0D)
            mUsageList.add(batterysipper);
    }

    private void addScreenUsage()
    {
        long l = mStats.getScreenOnTime(mRawRealtimeUs, mStatsType) / 1000L;
        double d = 0.0D + (double)l * mPowerProfile.getAveragePower("screen.on");
        double d1 = mPowerProfile.getAveragePower("screen.full");
        for(int i = 0; i < 5; i++)
            d += (((double)((float)i + 0.5F) * d1) / 5D) * (double)(mStats.getScreenBrightnessTime(i, mRawRealtimeUs, mStatsType) / 1000L);

        d /= 3600000D;
        if(d != 0.0D)
            addEntry(BatterySipper.DrainType.SCREEN, l, d);
    }

    private void addUserUsage()
    {
        for(int i = 0; i < mUserSippers.size(); i++)
        {
            int j = mUserSippers.keyAt(i);
            BatterySipper batterysipper = new BatterySipper(BatterySipper.DrainType.USER, null, 0.0D);
            batterysipper.userId = j;
            aggregateSippers(batterysipper, (List)mUserSippers.valueAt(i), "User");
            mUsageList.add(batterysipper);
        }

    }

    private void addWiFiUsage()
    {
        BatterySipper batterysipper = new BatterySipper(BatterySipper.DrainType.WIFI, null, 0.0D);
        mWifiPowerCalculator.calculateRemaining(batterysipper, mStats, mRawRealtimeUs, mRawUptimeUs, mStatsType);
        aggregateSippers(batterysipper, mWifiSippers, "WIFI");
        if(batterysipper.totalPowerMah > 0.0D)
            mUsageList.add(batterysipper);
    }

    private void aggregateSippers(BatterySipper batterysipper, List list, String s)
    {
        for(int i = 0; i < list.size(); i++)
            batterysipper.add((BatterySipper)list.get(i));

        batterysipper.computeMobilemspp();
        batterysipper.sumPower();
    }

    public static boolean checkHasBluetoothPowerReporting(BatteryStats batterystats, PowerProfile powerprofile)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(batterystats.hasBluetoothActivityReporting())
        {
            flag1 = flag;
            if(powerprofile.getAveragePower("bluetooth.controller.idle") != 0.0D)
            {
                flag1 = flag;
                if(powerprofile.getAveragePower("bluetooth.controller.rx") != 0.0D)
                {
                    flag1 = flag;
                    if(powerprofile.getAveragePower("bluetooth.controller.tx") != 0.0D)
                        flag1 = true;
                }
            }
        }
        return flag1;
    }

    public static boolean checkHasWifiPowerReporting(BatteryStats batterystats, PowerProfile powerprofile)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(batterystats.hasWifiActivityReporting())
        {
            flag1 = flag;
            if(powerprofile.getAveragePower("wifi.controller.idle") != 0.0D)
            {
                flag1 = flag;
                if(powerprofile.getAveragePower("wifi.controller.rx") != 0.0D)
                {
                    flag1 = flag;
                    if(powerprofile.getAveragePower("wifi.controller.tx") != 0.0D)
                        flag1 = true;
                }
            }
        }
        return flag1;
    }

    public static boolean checkWifiOnly(Context context)
    {
        return ((ConnectivityManager)context.getSystemService("connectivity")).isNetworkSupported(0) ^ true;
    }

    public static void dropFile(Context context, String s)
    {
        makeFilePath(context, s).delete();
    }

    private static BatteryStatsImpl getStats(IBatteryStats ibatterystats)
    {
        Object obj;
        Object obj1;
        obj = null;
        obj1 = null;
        Object obj2 = ibatterystats.getStatisticsStream();
        if(obj2 == null) goto _L2; else goto _L1
_L1:
        Object obj4;
        Object obj5;
        obj4 = null;
        obj5 = null;
        ibatterystats = JVM INSTR new #387 <Class android.os.ParcelFileDescriptor$AutoCloseInputStream>;
        ibatterystats.android.os.ParcelFileDescriptor.AutoCloseInputStream(((ParcelFileDescriptor) (obj2)));
        obj5 = readFully(ibatterystats, MemoryFile.getSize(((ParcelFileDescriptor) (obj2)).getFileDescriptor()));
        obj2 = Parcel.obtain();
        ((Parcel) (obj2)).unmarshall(((byte []) (obj5)), 0, obj5.length);
        ((Parcel) (obj2)).setDataPosition(0);
        obj5 = (BatteryStatsImpl)BatteryStatsImpl.CREATOR.createFromParcel(((Parcel) (obj2)));
        obj2 = obj1;
        if(ibatterystats == null)
            break MISSING_BLOCK_LABEL_88;
        ibatterystats.close();
        obj2 = obj1;
_L5:
        if(obj2 == null) goto _L4; else goto _L3
_L3:
        throw obj2;
        ibatterystats;
_L6:
        Exception exception;
        Object obj3;
        try
        {
            Log.w(TAG, "Unable to read statistics stream", ibatterystats);
        }
        // Misplaced declaration of an exception variable
        catch(IBatteryStats ibatterystats)
        {
            Log.w(TAG, "RemoteException:", ibatterystats);
        }
_L2:
        return new BatteryStatsImpl();
        obj2;
          goto _L5
_L4:
        return ((BatteryStatsImpl) (obj5));
        obj3;
        ibatterystats = ((IBatteryStats) (obj5));
_L10:
        throw obj3;
        exception;
        obj = obj3;
        obj3 = exception;
_L9:
        exception = ((Exception) (obj));
        if(ibatterystats == null)
            break MISSING_BLOCK_LABEL_144;
        ibatterystats.close();
        exception = ((Exception) (obj));
_L7:
        if(exception == null)
            break MISSING_BLOCK_LABEL_196;
        throw exception;
        ibatterystats;
          goto _L6
        ibatterystats;
label0:
        {
            if(obj != null)
                break label0;
            exception = ibatterystats;
        }
          goto _L7
        exception = ((Exception) (obj));
        if(obj == ibatterystats) goto _L7; else goto _L8
_L8:
        ((Throwable) (obj)).addSuppressed(ibatterystats);
        exception = ((Exception) (obj));
          goto _L7
        throw obj3;
        obj3;
        ibatterystats = obj4;
          goto _L9
        obj3;
          goto _L9
        obj3;
          goto _L10
    }

    private void load()
    {
        if(mBatteryInfo == null)
            return;
        mStats = getStats(mBatteryInfo);
        if(mCollectBatteryBroadcast)
            mBatteryBroadcast = mContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
    }

    private static File makeFilePath(Context context, String s)
    {
        return new File(context.getFilesDir(), s);
    }

    public static String makemAh(double d)
    {
        if(d == 0.0D)
            return "0";
        String s;
        if(d < 1.0000000000000001E-005D)
            s = "%.8f";
        else
        if(d < 0.0001D)
            s = "%.7f";
        else
        if(d < 0.001D)
            s = "%.6f";
        else
        if(d < 0.01D)
            s = "%.5f";
        else
        if(d < 0.10000000000000001D)
            s = "%.4f";
        else
        if(d < 1.0D)
            s = "%.3f";
        else
        if(d < 10D)
            s = "%.2f";
        else
        if(d < 100D)
            s = "%.1f";
        else
            s = "%.0f";
        return String.format(Locale.ENGLISH, s, new Object[] {
            Double.valueOf(d)
        });
    }

    private void processAppUsage(SparseArray sparsearray)
    {
        boolean flag;
        BatterySipper batterysipper;
        SparseArray sparsearray1;
        int i;
        int j;
        if(sparsearray.get(-1) != null)
            flag = true;
        else
            flag = false;
        mStatsPeriod = mTypeBatteryRealtimeUs;
        batterysipper = null;
        sparsearray1 = mStats.getUidStats();
        i = sparsearray1.size();
        j = 0;
        do
        {
label0:
            {
label1:
                {
                    if(j >= i)
                        break label0;
                    android.os.BatteryStats.Uid uid = (android.os.BatteryStats.Uid)sparsearray1.valueAt(j);
                    BatterySipper batterysipper1 = new BatterySipper(BatterySipper.DrainType.APP, uid, 0.0D);
                    mCpuPowerCalculator.calculateApp(batterysipper1, uid, mRawRealtimeUs, mRawUptimeUs, mStatsType);
                    mWakelockPowerCalculator.calculateApp(batterysipper1, uid, mRawRealtimeUs, mRawUptimeUs, mStatsType);
                    mMobileRadioPowerCalculator.calculateApp(batterysipper1, uid, mRawRealtimeUs, mRawUptimeUs, mStatsType);
                    mWifiPowerCalculator.calculateApp(batterysipper1, uid, mRawRealtimeUs, mRawUptimeUs, mStatsType);
                    mBluetoothPowerCalculator.calculateApp(batterysipper1, uid, mRawRealtimeUs, mRawUptimeUs, mStatsType);
                    mSensorPowerCalculator.calculateApp(batterysipper1, uid, mRawRealtimeUs, mRawUptimeUs, mStatsType);
                    mCameraPowerCalculator.calculateApp(batterysipper1, uid, mRawRealtimeUs, mRawUptimeUs, mStatsType);
                    mFlashlightPowerCalculator.calculateApp(batterysipper1, uid, mRawRealtimeUs, mRawUptimeUs, mStatsType);
                    BatterySipper batterysipper2;
                    if(batterysipper1.sumPower() == 0.0D)
                    {
                        batterysipper2 = batterysipper;
                        if(uid.getUid() != 0)
                            break label1;
                    }
                    int k = batterysipper1.getUid();
                    int l = UserHandle.getUserId(k);
                    if(k == 1010)
                        mWifiSippers.add(batterysipper1);
                    else
                    if(k == 1002)
                        mBluetoothSippers.add(batterysipper1);
                    else
                    if(!flag && sparsearray.get(l) == null && UserHandle.getAppId(k) >= 10000)
                    {
                        List list = (List)mUserSippers.get(l);
                        Object obj = list;
                        if(list == null)
                        {
                            obj = new ArrayList();
                            mUserSippers.put(l, obj);
                        }
                        ((List) (obj)).add(batterysipper1);
                    } else
                    {
                        mUsageList.add(batterysipper1);
                    }
                    batterysipper2 = batterysipper;
                    if(k == 0)
                        batterysipper2 = batterysipper1;
                }
                j++;
                batterysipper = batterysipper2;
                continue;
            }
            if(batterysipper != null)
            {
                mWakelockPowerCalculator.calculateRemaining(batterysipper, mStats, mRawRealtimeUs, mRawUptimeUs, mStatsType);
                batterysipper.sumPower();
            }
            return;
        } while(true);
    }

    private void processMiscUsage()
    {
        addUserUsage();
        addPhoneUsage();
        addScreenUsage();
        addWiFiUsage();
        addBluetoothUsage();
        addMemoryUsage();
        addIdleUsage();
        if(!mWifiOnly)
            addRadioUsage();
    }

    public static byte[] readFully(FileInputStream fileinputstream)
        throws IOException
    {
        return readFully(fileinputstream, fileinputstream.available());
    }

    public static byte[] readFully(FileInputStream fileinputstream, int i)
        throws IOException
    {
        boolean flag = false;
        byte abyte0[] = new byte[i];
        i = ((flag) ? 1 : 0);
        do
        {
            int j;
            int k;
            do
            {
                j = fileinputstream.read(abyte0, i, abyte0.length - i);
                if(j <= 0)
                    return abyte0;
                j = i + j;
                k = fileinputstream.available();
                i = j;
            } while(k <= abyte0.length - j);
            byte abyte1[] = new byte[j + k];
            System.arraycopy(abyte0, 0, abyte1, 0, j);
            abyte0 = abyte1;
            i = j;
        } while(true);
    }

    public static BatteryStats statsFromFile(Context context, String s)
    {
        ArrayMap arraymap = sFileXfer;
        arraymap;
        JVM INSTR monitorenter ;
        File file;
        file = makeFilePath(context, s);
        context = (BatteryStats)sFileXfer.get(file);
        if(context == null)
            break MISSING_BLOCK_LABEL_31;
        arraymap;
        JVM INSTR monitorexit ;
        return context;
        Object obj;
        Object obj2;
        obj = null;
        obj2 = null;
        context = ((Context) (obj));
        s = JVM INSTR new #434 <Class FileInputStream>;
        context = ((Context) (obj));
        s.FileInputStream(file);
        context = readFully(s);
        obj = Parcel.obtain();
        ((Parcel) (obj)).unmarshall(context, 0, context.length);
        ((Parcel) (obj)).setDataPosition(0);
        context = (BatteryStats)BatteryStatsImpl.CREATOR.createFromParcel(((Parcel) (obj)));
        if(s == null)
            break MISSING_BLOCK_LABEL_99;
        try
        {
            s.close();
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
        arraymap;
        JVM INSTR monitorexit ;
        return context;
        Object obj1;
        obj1;
        s = obj2;
_L4:
        context = s;
        Log.w(TAG, "Unable to read history to file", ((Throwable) (obj1)));
        if(s == null)
            break MISSING_BLOCK_LABEL_134;
        try
        {
            s.close();
        }
        // Misplaced declaration of an exception variable
        catch(Context context) { }
        arraymap;
        JVM INSTR monitorexit ;
        return getStats(com.android.internal.app.IBatteryStats.Stub.asInterface(ServiceManager.getService("batterystats")));
        s;
        obj1 = context;
_L2:
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_167;
        try
        {
            ((FileInputStream) (obj1)).close();
        }
        // Misplaced declaration of an exception variable
        catch(Context context) { }
        throw s;
        context;
        arraymap;
        JVM INSTR monitorexit ;
        throw context;
        context;
        obj1 = s;
        s = context;
        if(true) goto _L2; else goto _L1
_L1:
        obj1;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void clearStats()
    {
        mStats = null;
    }

    public long convertMsToUs(long l)
    {
        return 1000L * l;
    }

    public long convertUsToMs(long l)
    {
        return l / 1000L;
    }

    public void create(BatteryStats batterystats)
    {
        mPowerProfile = new PowerProfile(mContext);
        mStats = batterystats;
    }

    public void create(Bundle bundle)
    {
        if(bundle != null)
        {
            mStats = sStatsXfer;
            mBatteryBroadcast = sBatteryBroadcastXfer;
        }
        mBatteryInfo = com.android.internal.app.IBatteryStats.Stub.asInterface(ServiceManager.getService("batterystats"));
        mPowerProfile = new PowerProfile(mContext);
    }

    public Intent getBatteryBroadcast()
    {
        if(mBatteryBroadcast == null && mCollectBatteryBroadcast)
            load();
        return mBatteryBroadcast;
    }

    public double getComputedPower()
    {
        return mComputedPower;
    }

    public long getForegroundActivityTotalTimeUs(android.os.BatteryStats.Uid uid, long l)
    {
        uid = uid.getForegroundActivityTimer();
        if(uid != null)
            return uid.getTotalTimeLocked(l, 0);
        else
            return 0L;
    }

    public double getMaxDrainedPower()
    {
        return mMaxDrainedPower;
    }

    public double getMaxPower()
    {
        return mMaxPower;
    }

    public double getMaxRealPower()
    {
        return mMaxRealPower;
    }

    public double getMinDrainedPower()
    {
        return mMinDrainedPower;
    }

    public List getMobilemsppList()
    {
        return mMobilemsppList;
    }

    public PowerProfile getPowerProfile()
    {
        return mPowerProfile;
    }

    public long getProcessForegroundTimeMs(android.os.BatteryStats.Uid uid, int i)
    {
        int j = 0;
        long l = convertMsToUs(SystemClock.elapsedRealtime());
        int ai[] = new int[1];
        ai[0] = 0;
        long l1 = 0L;
        for(int k = ai.length; j < k; j++)
            l1 += uid.getProcessStateTime(ai[j], l, i);

        return convertUsToMs(Math.min(l1, getForegroundActivityTotalTimeUs(uid, l)));
    }

    public BatteryStats getStats()
    {
        if(mStats == null)
            load();
        return mStats;
    }

    public long getStatsPeriod()
    {
        return mStatsPeriod;
    }

    public int getStatsType()
    {
        return mStatsType;
    }

    public double getTotalPower()
    {
        return mTotalPower;
    }

    public List getUsageList()
    {
        return mUsageList;
    }

    public boolean isTypeService(BatterySipper batterysipper)
    {
        String as[] = mPackageManager.getPackagesForUid(batterysipper.getUid());
        if(as == null)
            return false;
        int i = as.length;
        for(int j = 0; j < i; j++)
        {
            batterysipper = as[j];
            if(ArrayUtils.contains(mServicepackageArray, batterysipper))
                return true;
        }

        return false;
    }

    public boolean isTypeSystem(BatterySipper batterysipper)
    {
        int i;
        if(batterysipper.uidObj == null)
            i = -1;
        else
            i = batterysipper.getUid();
        batterysipper.mPackages = mPackageManager.getPackagesForUid(i);
        if(i >= 0 && i < 10000)
            return true;
        if(batterysipper.mPackages != null)
        {
            batterysipper = batterysipper.mPackages;
            int k = batterysipper.length;
            for(int j = 0; j < k; j++)
            {
                Object obj = batterysipper[j];
                if(ArrayUtils.contains(mSystemPackageArray, obj))
                    return true;
            }

        }
        return false;
    }

    public void refreshStats(int i, int j)
    {
        SparseArray sparsearray = new SparseArray(1);
        sparsearray.put(j, new UserHandle(j));
        refreshStats(i, sparsearray);
    }

    public void refreshStats(int i, SparseArray sparsearray)
    {
        refreshStats(i, sparsearray, SystemClock.elapsedRealtime() * 1000L, SystemClock.uptimeMillis() * 1000L);
    }

    public void refreshStats(int i, SparseArray sparsearray, long l, long l1)
    {
        getStats();
        mMaxPower = 0.0D;
        mMaxRealPower = 0.0D;
        mComputedPower = 0.0D;
        mTotalPower = 0.0D;
        mUsageList.clear();
        mWifiSippers.clear();
        mBluetoothSippers.clear();
        mUserSippers.clear();
        mMobilemsppList.clear();
        if(mStats == null)
            return;
        if(mCpuPowerCalculator == null)
            mCpuPowerCalculator = new CpuPowerCalculator(mPowerProfile);
        mCpuPowerCalculator.reset();
        if(mMemoryPowerCalculator == null)
            mMemoryPowerCalculator = new MemoryPowerCalculator(mPowerProfile);
        mMemoryPowerCalculator.reset();
        if(mWakelockPowerCalculator == null)
            mWakelockPowerCalculator = new WakelockPowerCalculator(mPowerProfile);
        mWakelockPowerCalculator.reset();
        if(mMobileRadioPowerCalculator == null)
            mMobileRadioPowerCalculator = new MobileRadioPowerCalculator(mPowerProfile, mStats);
        mMobileRadioPowerCalculator.reset(mStats);
        boolean flag = checkHasWifiPowerReporting(mStats, mPowerProfile);
        if(mWifiPowerCalculator == null || flag != mHasWifiPowerReporting)
        {
            Object obj;
            if(flag)
                obj = new WifiPowerCalculator(mPowerProfile);
            else
                obj = new WifiPowerEstimator(mPowerProfile);
            mWifiPowerCalculator = ((PowerCalculator) (obj));
            mHasWifiPowerReporting = flag;
        }
        mWifiPowerCalculator.reset();
        flag = checkHasBluetoothPowerReporting(mStats, mPowerProfile);
        if(mBluetoothPowerCalculator == null || flag != mHasBluetoothPowerReporting)
        {
            mBluetoothPowerCalculator = new BluetoothPowerCalculator(mPowerProfile);
            mHasBluetoothPowerReporting = flag;
        }
        mBluetoothPowerCalculator.reset();
        if(mSensorPowerCalculator == null)
            mSensorPowerCalculator = new SensorPowerCalculator(mPowerProfile, (SensorManager)mContext.getSystemService("sensor"));
        mSensorPowerCalculator.reset();
        if(mCameraPowerCalculator == null)
            mCameraPowerCalculator = new CameraPowerCalculator(mPowerProfile);
        mCameraPowerCalculator.reset();
        if(mFlashlightPowerCalculator == null)
            mFlashlightPowerCalculator = new FlashlightPowerCalculator(mPowerProfile);
        mFlashlightPowerCalculator.reset();
        mStatsType = i;
        mRawUptimeUs = l1;
        mRawRealtimeUs = l;
        mBatteryUptimeUs = mStats.getBatteryUptime(l1);
        mBatteryRealtimeUs = mStats.getBatteryRealtime(l);
        mTypeBatteryUptimeUs = mStats.computeBatteryUptime(l1, mStatsType);
        mTypeBatteryRealtimeUs = mStats.computeBatteryRealtime(l, mStatsType);
        mBatteryTimeRemainingUs = mStats.computeBatteryTimeRemaining(l);
        mChargeTimeRemainingUs = mStats.computeChargeTimeRemaining(l);
        mMinDrainedPower = ((double)mStats.getLowDischargeAmountSinceCharge() * mPowerProfile.getBatteryCapacity()) / 100D;
        mMaxDrainedPower = ((double)mStats.getHighDischargeAmountSinceCharge() * mPowerProfile.getBatteryCapacity()) / 100D;
        processAppUsage(sparsearray);
        for(i = 0; i < mUsageList.size(); i++)
        {
            sparsearray = (BatterySipper)mUsageList.get(i);
            sparsearray.computeMobilemspp();
            if(((BatterySipper) (sparsearray)).mobilemspp != 0.0D)
                mMobilemsppList.add(sparsearray);
        }

        for(i = 0; i < mUserSippers.size(); i++)
        {
            sparsearray = (List)mUserSippers.valueAt(i);
            for(int j = 0; j < sparsearray.size(); j++)
            {
                BatterySipper batterysipper = (BatterySipper)sparsearray.get(j);
                batterysipper.computeMobilemspp();
                if(batterysipper.mobilemspp != 0.0D)
                    mMobilemsppList.add(batterysipper);
            }

        }

        Collections.sort(mMobilemsppList, new Comparator() {

            public int compare(BatterySipper batterysipper1, BatterySipper batterysipper2)
            {
                return Double.compare(batterysipper2.mobilemspp, batterysipper1.mobilemspp);
            }

            public volatile int compare(Object obj1, Object obj2)
            {
                return compare((BatterySipper)obj1, (BatterySipper)obj2);
            }

            final BatteryStatsHelper this$0;

            
            {
                this$0 = BatteryStatsHelper.this;
                super();
            }
        }
);
        processMiscUsage();
        Collections.sort(mUsageList);
        if(!mUsageList.isEmpty())
        {
            double d = ((BatterySipper)mUsageList.get(0)).totalPowerMah;
            mMaxPower = d;
            mMaxRealPower = d;
            int k = mUsageList.size();
            for(i = 0; i < k; i++)
            {
                double d1 = mComputedPower;
                mComputedPower = ((BatterySipper)mUsageList.get(i)).totalPowerMah + d1;
            }

        }
        mTotalPower = mComputedPower;
        if(mStats.getLowDischargeAmountSinceCharge() <= 1) goto _L2; else goto _L1
_L1:
        if(mMinDrainedPower <= mComputedPower) goto _L4; else goto _L3
_L3:
        double d2 = mMinDrainedPower - mComputedPower;
        mTotalPower = mMinDrainedPower;
        sparsearray = new BatterySipper(BatterySipper.DrainType.UNACCOUNTED, null, d2);
        int i1 = Collections.binarySearch(mUsageList, sparsearray);
        i = i1;
        if(i1 < 0)
            i = -(i1 + 1);
        mUsageList.add(i, sparsearray);
        mMaxPower = Math.max(mMaxPower, d2);
_L2:
        double d3 = removeHiddenBatterySippers(mUsageList);
        double d5 = getTotalPower() - d3;
        if(Math.abs(d5) > 0.001D)
        {
            i = 0;
            for(int j1 = mUsageList.size(); i < j1; i++)
            {
                sparsearray = (BatterySipper)mUsageList.get(i);
                if(!((BatterySipper) (sparsearray)).shouldHide)
                {
                    sparsearray.proportionalSmearMah = ((((BatterySipper) (sparsearray)).totalPowerMah + ((BatterySipper) (sparsearray)).screenPowerMah) / d5) * d3;
                    sparsearray.sumPower();
                }
            }

        }
        break; /* Loop/switch isn't completed */
_L4:
        if(mMaxDrainedPower < mComputedPower)
        {
            double d4 = mComputedPower - mMaxDrainedPower;
            sparsearray = new BatterySipper(BatterySipper.DrainType.OVERCOUNTED, null, d4);
            int k1 = Collections.binarySearch(mUsageList, sparsearray);
            i = k1;
            if(k1 < 0)
                i = -(k1 + 1);
            mUsageList.add(i, sparsearray);
            mMaxPower = Math.max(mMaxPower, d4);
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    public void refreshStats(int i, List list)
    {
        int j = list.size();
        SparseArray sparsearray = new SparseArray(j);
        for(int k = 0; k < j; k++)
        {
            UserHandle userhandle = (UserHandle)list.get(k);
            sparsearray.put(userhandle.getIdentifier(), userhandle);
        }

        refreshStats(i, sparsearray);
    }

    public double removeHiddenBatterySippers(List list)
    {
        double d = 0.0D;
        BatterySipper batterysipper = null;
        for(int i = list.size() - 1; i >= 0;)
        {
            BatterySipper batterysipper1 = (BatterySipper)list.get(i);
            batterysipper1.shouldHide = shouldHideSipper(batterysipper1);
            double d1 = d;
            if(batterysipper1.shouldHide)
            {
                d1 = d;
                if(batterysipper1.drainType != BatterySipper.DrainType.OVERCOUNTED)
                {
                    d1 = d;
                    if(batterysipper1.drainType != BatterySipper.DrainType.SCREEN)
                    {
                        d1 = d;
                        if(batterysipper1.drainType != BatterySipper.DrainType.UNACCOUNTED)
                        {
                            d1 = d;
                            if(batterysipper1.drainType != BatterySipper.DrainType.BLUETOOTH)
                            {
                                d1 = d;
                                if(batterysipper1.drainType != BatterySipper.DrainType.WIFI)
                                {
                                    d1 = d;
                                    if(batterysipper1.drainType != BatterySipper.DrainType.IDLE)
                                        d1 = d + batterysipper1.totalPowerMah;
                                }
                            }
                        }
                    }
                }
            }
            if(batterysipper1.drainType == BatterySipper.DrainType.SCREEN)
                batterysipper = batterysipper1;
            i--;
            d = d1;
        }

        if(batterysipper != null)
            smearScreenBatterySipper(list, batterysipper);
        return d;
    }

    public void setPackageManager(PackageManager packagemanager)
    {
        mPackageManager = packagemanager;
    }

    public void setServicePackageArray(String as[])
    {
        mServicepackageArray = as;
    }

    public void setSystemPackageArray(String as[])
    {
        mSystemPackageArray = as;
    }

    public boolean shouldHideSipper(BatterySipper batterysipper)
    {
        BatterySipper.DrainType draintype;
        draintype = batterysipper.drainType;
        break MISSING_BLOCK_LABEL_5;
        boolean flag;
        if(draintype == BatterySipper.DrainType.IDLE || draintype == BatterySipper.DrainType.CELL || draintype == BatterySipper.DrainType.SCREEN || draintype == BatterySipper.DrainType.UNACCOUNTED || draintype == BatterySipper.DrainType.OVERCOUNTED || isTypeService(batterysipper))
            flag = true;
        else
            flag = isTypeSystem(batterysipper);
        return flag;
    }

    public void smearScreenBatterySipper(List list, BatterySipper batterysipper)
    {
        long l = 0L;
        SparseLongArray sparselongarray = new SparseLongArray();
        int i = 0;
        for(int k = list.size(); i < k;)
        {
            android.os.BatteryStats.Uid uid = ((BatterySipper)list.get(i)).uidObj;
            long l1 = l;
            if(uid != null)
            {
                l1 = getProcessForegroundTimeMs(uid, 0);
                sparselongarray.put(uid.getUid(), l1);
                l1 = l + l1;
            }
            i++;
            l = l1;
        }

        if(batterysipper != null && l >= 0x927c0L)
        {
            double d = batterysipper.totalPowerMah;
            int j = 0;
            for(int i1 = list.size(); j < i1; j++)
            {
                batterysipper = (BatterySipper)list.get(j);
                batterysipper.screenPowerMah = ((double)sparselongarray.get(batterysipper.getUid(), 0L) * d) / (double)l;
            }

        }
    }

    public void storeState()
    {
        sStatsXfer = mStats;
        sBatteryBroadcastXfer = mBatteryBroadcast;
    }

    public void storeStatsHistoryInFile(String s)
    {
        ArrayMap arraymap = sFileXfer;
        arraymap;
        JVM INSTR monitorenter ;
        File file;
        file = makeFilePath(mContext, s);
        sFileXfer.put(file, getStats());
        String s1;
        Object obj1;
        s1 = null;
        obj1 = null;
        s = s1;
        Object obj2 = JVM INSTR new #935 <Class FileOutputStream>;
        s = s1;
        ((FileOutputStream) (obj2)).FileOutputStream(file);
        s = Parcel.obtain();
        getStats().writeToParcelWithoutUids(s, 0);
        ((FileOutputStream) (obj2)).write(s.marshall());
        if(obj2 == null)
            break MISSING_BLOCK_LABEL_82;
        try
        {
            ((FileOutputStream) (obj2)).close();
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
_L2:
        arraymap;
        JVM INSTR monitorexit ;
        return;
        Object obj;
        obj;
        obj2 = obj1;
_L5:
        s = ((String) (obj2));
        Log.w(TAG, "Unable to write history to file", ((Throwable) (obj)));
        if(obj2 == null) goto _L2; else goto _L1
_L1:
        try
        {
            ((FileOutputStream) (obj2)).close();
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
          goto _L2
        obj2;
        obj = s;
_L4:
        if(obj == null)
            break MISSING_BLOCK_LABEL_142;
        try
        {
            ((FileOutputStream) (obj)).close();
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
        throw obj2;
        s;
        arraymap;
        JVM INSTR monitorexit ;
        throw s;
        s;
        obj = obj2;
        obj2 = s;
        if(true) goto _L4; else goto _L3
_L3:
        obj;
          goto _L5
    }

    static final boolean DEBUG = false;
    private static final String TAG = com/android/internal/os/BatteryStatsHelper.getSimpleName();
    private static Intent sBatteryBroadcastXfer;
    private static ArrayMap sFileXfer = new ArrayMap();
    private static BatteryStats sStatsXfer;
    private Intent mBatteryBroadcast;
    private IBatteryStats mBatteryInfo;
    long mBatteryRealtimeUs;
    long mBatteryTimeRemainingUs;
    long mBatteryUptimeUs;
    PowerCalculator mBluetoothPowerCalculator;
    private final List mBluetoothSippers;
    PowerCalculator mCameraPowerCalculator;
    long mChargeTimeRemainingUs;
    private final boolean mCollectBatteryBroadcast;
    private double mComputedPower;
    private final Context mContext;
    PowerCalculator mCpuPowerCalculator;
    PowerCalculator mFlashlightPowerCalculator;
    boolean mHasBluetoothPowerReporting;
    boolean mHasWifiPowerReporting;
    private double mMaxDrainedPower;
    private double mMaxPower;
    private double mMaxRealPower;
    PowerCalculator mMemoryPowerCalculator;
    private double mMinDrainedPower;
    MobileRadioPowerCalculator mMobileRadioPowerCalculator;
    private final List mMobilemsppList;
    private PackageManager mPackageManager;
    private PowerProfile mPowerProfile;
    long mRawRealtimeUs;
    long mRawUptimeUs;
    PowerCalculator mSensorPowerCalculator;
    private String mServicepackageArray[];
    private BatteryStats mStats;
    private long mStatsPeriod;
    private int mStatsType;
    private String mSystemPackageArray[];
    private double mTotalPower;
    long mTypeBatteryRealtimeUs;
    long mTypeBatteryUptimeUs;
    private final List mUsageList;
    private final SparseArray mUserSippers;
    PowerCalculator mWakelockPowerCalculator;
    private final boolean mWifiOnly;
    PowerCalculator mWifiPowerCalculator;
    private final List mWifiSippers;

}
