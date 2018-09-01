// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth.le;

import android.app.ActivityThread;
import android.app.PendingIntent;
import android.bluetooth.*;
import android.os.*;
import android.util.Log;
import java.util.*;

// Referenced classes of package android.bluetooth.le:
//            ScanSettings, ScanFilter, BluetoothLeUtils, TruncatedFilter, 
//            ScanCallback, ScanResult

public final class BluetoothLeScanner
{
    private class BleScanCallbackWrapper extends IScannerCallback.Stub
    {

        static ScanCallback _2D_get0(BleScanCallbackWrapper blescancallbackwrapper)
        {
            return blescancallbackwrapper.mScanCallback;
        }

        void flushPendingBatchResults()
        {
            this;
            JVM INSTR monitorenter ;
            if(mScannerId > 0)
                break MISSING_BLOCK_LABEL_42;
            StringBuilder stringbuilder = JVM INSTR new #63  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.e("BluetoothLeScanner", stringbuilder.append("Error state, mLeHandle: ").append(mScannerId).toString());
            this;
            JVM INSTR monitorexit ;
            return;
            mBluetoothGatt.flushPendingBatchResults(mScannerId);
_L1:
            this;
            JVM INSTR monitorexit ;
            return;
            Object obj;
            obj;
            Log.e("BluetoothLeScanner", "Failed to get pending scan results", ((Throwable) (obj)));
              goto _L1
            obj;
            throw obj;
        }

        public void onBatchScanResults(List list)
        {
            (new Handler(Looper.getMainLooper())).post(list. new Runnable() {

                public void run()
                {
                    BleScanCallbackWrapper._2D_get0(BleScanCallbackWrapper.this).onBatchScanResults(results);
                }

                final BleScanCallbackWrapper this$1;
                final List val$results;

            
            {
                this$1 = final_blescancallbackwrapper;
                results = List.this;
                super();
            }
            }
);
        }

        public void onFoundOrLost(final boolean onFound, ScanResult scanresult)
        {
            this;
            JVM INSTR monitorenter ;
            int i = mScannerId;
            if(i > 0)
                break MISSING_BLOCK_LABEL_14;
            this;
            JVM INSTR monitorexit ;
            return;
            this;
            JVM INSTR monitorexit ;
            (new Handler(Looper.getMainLooper())).post(scanresult. new Runnable() {

                public void run()
                {
                    if(onFound)
                        BleScanCallbackWrapper._2D_get0(BleScanCallbackWrapper.this).onScanResult(2, scanResult);
                    else
                        BleScanCallbackWrapper._2D_get0(BleScanCallbackWrapper.this).onScanResult(4, scanResult);
                }

                final BleScanCallbackWrapper this$1;
                final boolean val$onFound;
                final ScanResult val$scanResult;

            
            {
                this$1 = final_blescancallbackwrapper;
                onFound = flag;
                scanResult = ScanResult.this;
                super();
            }
            }
);
            return;
            scanresult;
            throw scanresult;
        }

        public void onScanManagerErrorCallback(int i)
        {
            this;
            JVM INSTR monitorenter ;
            int j = mScannerId;
            if(j > 0)
                break MISSING_BLOCK_LABEL_14;
            this;
            JVM INSTR monitorexit ;
            return;
            this;
            JVM INSTR monitorexit ;
            BluetoothLeScanner._2D_wrap0(BluetoothLeScanner.this, mScanCallback, i);
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void onScanResult(ScanResult scanresult)
        {
            this;
            JVM INSTR monitorenter ;
            int i = mScannerId;
            if(i > 0)
                break MISSING_BLOCK_LABEL_14;
            this;
            JVM INSTR monitorexit ;
            return;
            this;
            JVM INSTR monitorexit ;
            (new Handler(Looper.getMainLooper())).post(scanresult. new Runnable() {

                public void run()
                {
                    BleScanCallbackWrapper._2D_get0(BleScanCallbackWrapper.this).onScanResult(1, scanResult);
                }

                final BleScanCallbackWrapper this$1;
                final ScanResult val$scanResult;

            
            {
                this$1 = final_blescancallbackwrapper;
                scanResult = ScanResult.this;
                super();
            }
            }
);
            return;
            scanresult;
            throw scanresult;
        }

        public void onScannerRegistered(int i, int j)
        {
            Log.d("BluetoothLeScanner", (new StringBuilder()).append("onScannerRegistered() - status=").append(i).append(" scannerId=").append(j).append(" mScannerId=").append(mScannerId).toString());
            this;
            JVM INSTR monitorenter ;
            if(i != 0) goto _L2; else goto _L1
_L1:
            boolean flag = BluetoothLeScannerInjector.isLeScanAllowed();
            if(!flag) goto _L4; else goto _L3
_L3:
            if(mScannerId != -1) goto _L6; else goto _L5
_L5:
            mBluetoothGatt.unregisterClient(j);
_L7:
            if(mScannerId != -1)
                BluetoothLeScannerInjector.startLeScan(mScannerId, mSettings, mFilters, mWorkSource, mResultStorages, ActivityThread.currentOpPackageName(), asBinder());
            notifyAll();
            this;
            JVM INSTR monitorexit ;
            return;
_L6:
            mScannerId = j;
            mBluetoothGatt.startScan(mScannerId, mSettings, mFilters, mResultStorages, ActivityThread.currentOpPackageName());
              goto _L7
            Object obj;
            obj;
            StringBuilder stringbuilder = JVM INSTR new #63  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.e("BluetoothLeScanner", stringbuilder.append("fail to start le scan: ").append(obj).toString());
            mScannerId = -1;
              goto _L7
            obj;
            throw obj;
_L4:
            Log.i("BluetoothLeScanner", "start scan is blocked");
              goto _L7
_L2:
            if(i != 6)
                break MISSING_BLOCK_LABEL_233;
            mScannerId = -2;
              goto _L7
            mScannerId = -1;
              goto _L7
        }

        public void startRegistration()
        {
            this;
            JVM INSTR monitorenter ;
            int i;
            if(mScannerId == -1)
                break MISSING_BLOCK_LABEL_21;
            i = mScannerId;
            if(i != -2)
                break MISSING_BLOCK_LABEL_24;
            this;
            JVM INSTR monitorexit ;
            return;
            mBluetoothGatt.registerScanner(this, mWorkSource);
            wait(2000L);
_L3:
            if(mScannerId <= 0) goto _L2; else goto _L1
_L1:
            BluetoothLeScanner._2D_get0(BluetoothLeScanner.this).put(mScanCallback, this);
_L4:
            this;
            JVM INSTR monitorexit ;
            return;
            Object obj;
            obj;
            Log.e("BluetoothLeScanner", "application registeration exception", ((Throwable) (obj)));
            BluetoothLeScanner._2D_wrap0(BluetoothLeScanner.this, mScanCallback, 3);
              goto _L3
            obj;
            throw obj;
_L2:
            if(mScannerId == 0)
                mScannerId = -1;
            i = mScannerId;
            if(i != -2)
                break MISSING_BLOCK_LABEL_129;
            this;
            JVM INSTR monitorexit ;
            return;
            BluetoothLeScanner._2D_wrap0(BluetoothLeScanner.this, mScanCallback, 2);
              goto _L4
        }

        public void stopLeScan()
        {
            this;
            JVM INSTR monitorenter ;
            if(mScannerId > 0)
                break MISSING_BLOCK_LABEL_42;
            StringBuilder stringbuilder = JVM INSTR new #63  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.e("BluetoothLeScanner", stringbuilder.append("Error state, mLeHandle: ").append(mScannerId).toString());
            this;
            JVM INSTR monitorexit ;
            return;
            mBluetoothGatt.stopScan(mScannerId);
            mBluetoothGatt.unregisterScanner(mScannerId);
_L1:
            BluetoothLeScannerInjector.stopLeScan(mScannerId, asBinder());
            mScannerId = -1;
            this;
            JVM INSTR monitorexit ;
            return;
            Object obj;
            obj;
            Log.e("BluetoothLeScanner", "Failed to stop scan and unregister", ((Throwable) (obj)));
              goto _L1
            obj;
            throw obj;
        }

        private static final int REGISTRATION_CALLBACK_TIMEOUT_MILLIS = 2000;
        private IBluetoothGatt mBluetoothGatt;
        private final List mFilters;
        private List mResultStorages;
        private final ScanCallback mScanCallback;
        private int mScannerId;
        private ScanSettings mSettings;
        private final WorkSource mWorkSource;
        final BluetoothLeScanner this$0;

        public BleScanCallbackWrapper(IBluetoothGatt ibluetoothgatt, List list, ScanSettings scansettings, WorkSource worksource, ScanCallback scancallback, List list1)
        {
            this$0 = BluetoothLeScanner.this;
            super();
            mBluetoothGatt = ibluetoothgatt;
            mFilters = list;
            mSettings = scansettings;
            mWorkSource = worksource;
            mScanCallback = scancallback;
            mScannerId = 0;
            mResultStorages = list1;
        }
    }


    static Map _2D_get0(BluetoothLeScanner bluetoothlescanner)
    {
        return bluetoothlescanner.mLeScanClients;
    }

    static void _2D_wrap0(BluetoothLeScanner bluetoothlescanner, ScanCallback scancallback, int i)
    {
        bluetoothlescanner.postCallbackError(scancallback, i);
    }

    public BluetoothLeScanner(IBluetoothManager ibluetoothmanager)
    {
        mBluetoothManager = ibluetoothmanager;
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    private boolean isHardwareResourcesAvailableForScan(ScanSettings scansettings)
    {
        boolean flag = false;
        int i = scansettings.getCallbackType();
        if((i & 2) != 0 || (i & 4) != 0)
        {
            if(mBluetoothAdapter.isOffloadedFilteringSupported())
                flag = mBluetoothAdapter.isHardwareTrackingFiltersAvailable();
            return flag;
        } else
        {
            return true;
        }
    }

    private boolean isRoutingAllowedForScan(ScanSettings scansettings)
    {
        return scansettings.getCallbackType() != 8 || scansettings.getScanMode() != -1;
    }

    private boolean isSettingsAndFilterComboAllowed(ScanSettings scansettings, List list)
    {
label0:
        {
            if((scansettings.getCallbackType() & 6) == 0)
                break label0;
            if(list == null)
                return false;
            scansettings = list.iterator();
            do
                if(!scansettings.hasNext())
                    break label0;
            while(!((ScanFilter)scansettings.next()).isAllFieldsEmpty());
            return false;
        }
        return true;
    }

    private boolean isSettingsConfigAllowedForScan(ScanSettings scansettings)
    {
        if(mBluetoothAdapter.isOffloadedFilteringSupported())
            return true;
        return scansettings.getCallbackType() == 1 && scansettings.getReportDelayMillis() == 0L;
    }

    private void postCallbackError(final ScanCallback callback, final int errorCode)
    {
        mHandler.post(new Runnable() {

            public void run()
            {
                callback.onScanFailed(errorCode);
            }

            final BluetoothLeScanner this$0;
            final ScanCallback val$callback;
            final int val$errorCode;

            
            {
                this$0 = BluetoothLeScanner.this;
                callback = scancallback;
                errorCode = i;
                super();
            }
        }
);
    }

    private int postCallbackErrorOrReturn(ScanCallback scancallback, int i)
    {
        if(scancallback == null)
        {
            return i;
        } else
        {
            postCallbackError(scancallback, i);
            return 0;
        }
    }

    private int startScan(List list, ScanSettings scansettings, WorkSource worksource, ScanCallback scancallback, PendingIntent pendingintent, List list1)
    {
        BluetoothLeUtils.checkAdapterStateOn(mBluetoothAdapter);
        if(scancallback == null && pendingintent == null)
            throw new IllegalArgumentException("callback is null");
        if(scansettings == null)
            throw new IllegalArgumentException("settings is null");
        Map map = mLeScanClients;
        map;
        JVM INSTR monitorenter ;
        if(scancallback == null)
            break MISSING_BLOCK_LABEL_84;
        int i;
        if(!mLeScanClients.containsKey(scancallback))
            break MISSING_BLOCK_LABEL_84;
        i = postCallbackErrorOrReturn(scancallback, 1);
        map;
        JVM INSTR monitorexit ;
        return i;
        Object obj;
        try
        {
            obj = mBluetoothManager.getBluetoothGatt();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            obj = null;
        }
        if(obj != null)
            break MISSING_BLOCK_LABEL_123;
        i = postCallbackErrorOrReturn(scancallback, 3);
        map;
        JVM INSTR monitorexit ;
        return i;
        List list2 = list;
        if(scansettings.getCallbackType() != 8)
            break MISSING_BLOCK_LABEL_175;
        if(list == null)
            break MISSING_BLOCK_LABEL_151;
        list2 = list;
        if(!list.isEmpty())
            break MISSING_BLOCK_LABEL_175;
        list = JVM INSTR new #180 <Class ScanFilter$Builder>;
        list.ScanFilter.Builder();
        list2 = Arrays.asList(new ScanFilter[] {
            list.build()
        });
        if(isSettingsConfigAllowedForScan(scansettings))
            break MISSING_BLOCK_LABEL_198;
        i = postCallbackErrorOrReturn(scancallback, 4);
        map;
        JVM INSTR monitorexit ;
        return i;
        if(isHardwareResourcesAvailableForScan(scansettings))
            break MISSING_BLOCK_LABEL_221;
        i = postCallbackErrorOrReturn(scancallback, 5);
        map;
        JVM INSTR monitorexit ;
        return i;
        if(isSettingsAndFilterComboAllowed(scansettings, list2))
            break MISSING_BLOCK_LABEL_246;
        i = postCallbackErrorOrReturn(scancallback, 4);
        map;
        JVM INSTR monitorexit ;
        return i;
        if(isRoutingAllowedForScan(scansettings))
            break MISSING_BLOCK_LABEL_269;
        i = postCallbackErrorOrReturn(scancallback, 4);
        map;
        JVM INSTR monitorexit ;
        return i;
        if(scancallback == null) goto _L2; else goto _L1
_L1:
        list = JVM INSTR new #8   <Class BluetoothLeScanner$BleScanCallbackWrapper>;
        list.this. BleScanCallbackWrapper(((IBluetoothGatt) (obj)), list2, scansettings, worksource, scancallback, list1);
        list.startRegistration();
_L4:
        map;
        JVM INSTR monitorexit ;
        return 0;
_L2:
        try
        {
            ((IBluetoothGatt) (obj)).startScanForIntent(pendingintent, scansettings, list2, ActivityThread.currentOpPackageName());
        }
        // Misplaced declaration of an exception variable
        catch(List list)
        {
            return 3;
        }
        if(true) goto _L4; else goto _L3
_L3:
        list;
        throw list;
    }

    public void cleanup()
    {
        mLeScanClients.clear();
        BluetoothLeScannerInjector.cleanupLeScanStatistics();
    }

    public void flushPendingScanResults(ScanCallback scancallback)
    {
        BluetoothLeUtils.checkAdapterStateOn(mBluetoothAdapter);
        if(scancallback == null)
            throw new IllegalArgumentException("callback cannot be null!");
        Map map = mLeScanClients;
        map;
        JVM INSTR monitorenter ;
        scancallback = (BleScanCallbackWrapper)mLeScanClients.get(scancallback);
        if(scancallback != null)
            break MISSING_BLOCK_LABEL_49;
        map;
        JVM INSTR monitorexit ;
        return;
        scancallback.flushPendingBatchResults();
        map;
        JVM INSTR monitorexit ;
        return;
        scancallback;
        throw scancallback;
    }

    public boolean startLeScanStatistics(android.bluetooth.BluetoothAdapter.LeScanCallback lescancallback)
    {
        return BluetoothLeScannerInjector.startLeScanStatistics(mBluetoothManager, lescancallback);
    }

    public int startScan(List list, ScanSettings scansettings, PendingIntent pendingintent)
    {
        if(scansettings == null)
            scansettings = (new ScanSettings.Builder()).build();
        return startScan(list, scansettings, null, null, pendingintent, null);
    }

    public void startScan(ScanCallback scancallback)
    {
        startScan(null, (new ScanSettings.Builder()).build(), scancallback);
    }

    public void startScan(List list, ScanSettings scansettings, ScanCallback scancallback)
    {
        startScan(list, scansettings, null, scancallback, null, null);
    }

    public void startScanFromSource(WorkSource worksource, ScanCallback scancallback)
    {
        startScanFromSource(null, (new ScanSettings.Builder()).build(), worksource, scancallback);
    }

    public void startScanFromSource(List list, ScanSettings scansettings, WorkSource worksource, ScanCallback scancallback)
    {
        startScan(list, scansettings, worksource, scancallback, null, null);
    }

    public void startTruncatedScan(List list, ScanSettings scansettings, ScanCallback scancallback)
    {
        int i = list.size();
        ArrayList arraylist = new ArrayList(i);
        ArrayList arraylist1 = new ArrayList(i);
        for(Iterator iterator = list.iterator(); iterator.hasNext(); arraylist1.add(list.getStorageDescriptors()))
        {
            list = (TruncatedFilter)iterator.next();
            arraylist.add(list.getFilter());
        }

        startScan(arraylist, scansettings, null, scancallback, null, arraylist1);
    }

    public void stopLeScanStatistics(android.bluetooth.BluetoothAdapter.LeScanCallback lescancallback)
    {
        BluetoothLeScannerInjector.stopLeScanStatistics(lescancallback);
    }

    public void stopScan(PendingIntent pendingintent)
    {
        BluetoothLeUtils.checkAdapterStateOn(mBluetoothAdapter);
        mBluetoothManager.getBluetoothGatt().stopScanForIntent(pendingintent, ActivityThread.currentOpPackageName());
_L2:
        return;
        pendingintent;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void stopScan(ScanCallback scancallback)
    {
        BluetoothLeUtils.checkAdapterStateOn(mBluetoothAdapter);
        Map map = mLeScanClients;
        map;
        JVM INSTR monitorenter ;
        scancallback = (BleScanCallbackWrapper)mLeScanClients.remove(scancallback);
        if(scancallback != null)
            break MISSING_BLOCK_LABEL_44;
        Log.d("BluetoothLeScanner", "could not find callback wrapper");
        map;
        JVM INSTR monitorexit ;
        return;
        scancallback.stopLeScan();
        map;
        JVM INSTR monitorexit ;
        return;
        scancallback;
        throw scancallback;
    }

    private static final boolean DBG = true;
    public static final String EXTRA_CALLBACK_TYPE = "android.bluetooth.le.extra.CALLBACK_TYPE";
    public static final String EXTRA_ERROR_CODE = "android.bluetooth.le.extra.ERROR_CODE";
    public static final String EXTRA_LIST_SCAN_RESULT = "android.bluetooth.le.extra.LIST_SCAN_RESULT";
    private static final String TAG = "BluetoothLeScanner";
    private static final boolean VDBG = false;
    private BluetoothAdapter mBluetoothAdapter;
    private final IBluetoothManager mBluetoothManager;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final Map mLeScanClients = new HashMap();
}
