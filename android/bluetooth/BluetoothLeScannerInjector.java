// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.os.*;
import android.util.Log;
import com.miui.whetstone.IPowerKeeperPolicy;
import com.miui.whetstone.server.IWhetstoneActivityManager;
import java.util.*;

// Referenced classes of package android.bluetooth:
//            BleScanWrapper, IBluetoothManager, IBluetoothGatt

public class BluetoothLeScannerInjector
{
    private static class BleScanStatisticsCallbackWrapper extends android.bluetooth.le.IScannerCallback.Stub
    {

        public void onBatchScanResults(List list)
        {
        }

        public void onFoundOrLost(boolean flag, ScanResult scanresult)
        {
        }

        public void onScanManagerErrorCallback(int i)
        {
        }

        public void onScanResult(ScanResult scanresult)
        {
            mScanCallback.onScanResult(1, scanresult);
        }

        public void onScannerRegistered(int i, int j)
        {
        }

        public void startBleScanStatistics()
        {
            mBluetoothGatt.registerStatisticsClient(this);
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.e("BluetoothLeScannerInjector", "Failed to start ble scan statistics.", remoteexception);
              goto _L1
        }

        public void stopBleScanStatistics()
        {
            mBluetoothGatt.unregisterStatisticsClient(this);
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.e("BluetoothLeScannerInjector", "Failed to stop ble scan statistics.", remoteexception);
              goto _L1
        }

        private IBluetoothGatt mBluetoothGatt;
        private final ScanCallback mScanCallback;

        public BleScanStatisticsCallbackWrapper(IBluetoothGatt ibluetoothgatt, ScanCallback scancallback)
        {
            mBluetoothGatt = ibluetoothgatt;
            mScanCallback = scancallback;
        }
    }


    public BluetoothLeScannerInjector()
    {
    }

    public static void cleanupLeScanStatistics()
    {
        if(sLeScanStatisticsClients != null)
            sLeScanStatisticsClients.clear();
    }

    public static boolean isLeScanAllowed()
    {
        int i;
        boolean flag;
        i = Process.myUid();
        flag = true;
        IWhetstoneActivityManager iwhetstoneactivitymanager = com.miui.whetstone.server.IWhetstoneActivityManager.Stub.asInterface(ServiceManager.getService("whetstone.activity"));
        if(iwhetstoneactivitymanager != null)
            try
            {
                flag = iwhetstoneactivitymanager.getPowerKeeperPolicy().isLeScanAllowed(i);
            }
            catch(Exception exception)
            {
                flag = true;
                exception.printStackTrace();
            }
        return flag;
    }

    public static void startLeScan(int i, ScanSettings scansettings, List list, WorkSource worksource, List list1, String s, IBinder ibinder)
    {
        int j = Process.myUid();
        BleScanWrapper blescanwrapper = JVM INSTR new #75  <Class BleScanWrapper>;
        blescanwrapper.BleScanWrapper(i, scansettings, list, worksource, list1, s);
        scansettings = JVM INSTR new #80  <Class Bundle>;
        scansettings.Bundle();
        scansettings.putInt("uid", j);
        scansettings.putParcelable("BleScanWrapper", blescanwrapper);
        scansettings.putBinder("IBinder", ibinder);
        list = com.miui.whetstone.server.IWhetstoneActivityManager.Stub.asInterface(ServiceManager.getService("whetstone.activity"));
        if(list == null)
            break MISSING_BLOCK_LABEL_80;
        list.getPowerKeeperPolicy().startLeScan(scansettings);
_L1:
        return;
        scansettings;
        scansettings.printStackTrace();
          goto _L1
    }

    public static boolean startLeScanStatistics(IBluetoothManager ibluetoothmanager, BluetoothAdapter.LeScanCallback lescancallback)
    {
        Log.d("BluetoothLeScannerInjector", "startLeScanStatistics()");
        if(lescancallback == null)
        {
            Log.e("BluetoothLeScannerInjector", "startLeScanStatistics: null callback");
            return false;
        }
        Map map = sLeScanStatisticsClients;
        map;
        JVM INSTR monitorenter ;
        IBluetoothGatt ibluetoothgatt = ibluetoothmanager.getBluetoothGatt();
        if(ibluetoothgatt != null)
            break MISSING_BLOCK_LABEL_43;
        map;
        JVM INSTR monitorexit ;
        return false;
        ibluetoothmanager = JVM INSTR new #6   <Class BluetoothLeScannerInjector$1>;
        ibluetoothmanager._cls1(lescancallback);
        BleScanStatisticsCallbackWrapper blescanstatisticscallbackwrapper = JVM INSTR new #8   <Class BluetoothLeScannerInjector$BleScanStatisticsCallbackWrapper>;
        blescanstatisticscallbackwrapper.BleScanStatisticsCallbackWrapper(ibluetoothgatt, ibluetoothmanager);
        blescanstatisticscallbackwrapper.startBleScanStatistics();
        sLeScanStatisticsClients.put(lescancallback, blescanstatisticscallbackwrapper);
        map;
        JVM INSTR monitorexit ;
        return true;
        ibluetoothmanager;
        Log.e("BluetoothLeScannerInjector", "", ibluetoothmanager);
        map;
        JVM INSTR monitorexit ;
        return false;
        ibluetoothmanager;
        throw ibluetoothmanager;
    }

    public static void stopLeScan(int i, IBinder ibinder)
    {
        Bundle bundle;
        int j = Process.myUid();
        BleScanWrapper blescanwrapper = JVM INSTR new #75  <Class BleScanWrapper>;
        blescanwrapper.BleScanWrapper(i, null, null, null, null, null);
        bundle = JVM INSTR new #80  <Class Bundle>;
        bundle.Bundle();
        bundle.putInt("uid", j);
        bundle.putParcelable("BleScanWrapper", blescanwrapper);
        bundle.putBinder("IBinder", ibinder);
        ibinder = com.miui.whetstone.server.IWhetstoneActivityManager.Stub.asInterface(ServiceManager.getService("whetstone.activity"));
        if(ibinder == null)
            break MISSING_BLOCK_LABEL_78;
        ibinder.getPowerKeeperPolicy().stopLeScan(bundle);
_L1:
        return;
        ibinder;
        ibinder.printStackTrace();
          goto _L1
    }

    public static void stopLeScanStatistics(BluetoothAdapter.LeScanCallback lescancallback)
    {
        Log.d("BluetoothLeScannerInjector", "stopLeScanStatistics()");
        Map map = sLeScanStatisticsClients;
        map;
        JVM INSTR monitorenter ;
        lescancallback = (BleScanStatisticsCallbackWrapper)sLeScanStatisticsClients.remove(lescancallback);
        if(lescancallback != null)
            break MISSING_BLOCK_LABEL_42;
        Log.d("BluetoothLeScannerInjector", "no ble scan statistics callback found.");
        map;
        JVM INSTR monitorexit ;
        return;
        lescancallback.stopBleScanStatistics();
        map;
        JVM INSTR monitorexit ;
        return;
        lescancallback;
        throw lescancallback;
    }

    private static final String TAG = "BluetoothLeScannerInjector";
    private static final Map sLeScanStatisticsClients = new HashMap();


    // Unreferenced inner class android/bluetooth/BluetoothLeScannerInjector$1

/* anonymous class */
    static final class _cls1 extends ScanCallback
    {

        public void onScanResult(int i, ScanResult scanresult)
        {
            ScanRecord scanrecord = scanresult.getScanRecord();
            if(scanrecord == null)
            {
                return;
            } else
            {
                callback.onLeScan(scanresult.getDevice(), scanresult.getRssi(), scanrecord.getBytes());
                return;
            }
        }

        final BluetoothAdapter.LeScanCallback val$callback;

            
            {
                callback = lescancallback;
                super();
            }
    }

}
