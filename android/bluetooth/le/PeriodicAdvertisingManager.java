// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth.le;

import android.bluetooth.*;
import android.os.*;
import android.util.Log;
import java.util.IdentityHashMap;
import java.util.Map;

// Referenced classes of package android.bluetooth.le:
//            ScanResult, PeriodicAdvertisingCallback, IPeriodicAdvertisingCallback, PeriodicAdvertisingReport

public final class PeriodicAdvertisingManager
{

    public PeriodicAdvertisingManager(IBluetoothManager ibluetoothmanager)
    {
        mBluetoothManager = ibluetoothmanager;
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        callbackWrappers = new IdentityHashMap();
    }

    private IPeriodicAdvertisingCallback wrap(final PeriodicAdvertisingCallback callback, final Handler handler)
    {
        return new IPeriodicAdvertisingCallback.Stub() {

            public void onPeriodicAdvertisingReport(PeriodicAdvertisingReport periodicadvertisingreport)
            {
                handler.post(periodicadvertisingreport. new Runnable() {

                    public void run()
                    {
                        callback.onPeriodicAdvertisingReport(report);
                    }

                    final _cls1 this$1;
                    final PeriodicAdvertisingCallback val$callback;
                    final PeriodicAdvertisingReport val$report;

            
            {
                this$1 = final__pcls1;
                callback = periodicadvertisingcallback;
                report = PeriodicAdvertisingReport.this;
                super();
            }
                }
);
            }

            public void onSyncEstablished(final int syncHandle, final BluetoothDevice device, final int advertisingSid, final int skip, final int timeout, int i)
            {
                handler.post(i. new Runnable() {

                    public void run()
                    {
                        callback.onSyncEstablished(syncHandle, device, advertisingSid, skip, timeout, status);
                        if(status != 0)
                            callbackWrappers.remove(callback);
                    }

                    final _cls1 this$1;
                    final int val$advertisingSid;
                    final PeriodicAdvertisingCallback val$callback;
                    final BluetoothDevice val$device;
                    final int val$skip;
                    final int val$status;
                    final int val$syncHandle;
                    final int val$timeout;

            
            {
                this$1 = final__pcls1;
                callback = periodicadvertisingcallback;
                syncHandle = i;
                device = bluetoothdevice;
                advertisingSid = j;
                skip = k;
                timeout = l;
                status = I.this;
                super();
            }
                }
);
            }

            public void onSyncLost(int i)
            {
                handler.post(i. new Runnable() {

                    public void run()
                    {
                        callback.onSyncLost(syncHandle);
                        callbackWrappers.remove(callback);
                    }

                    final _cls1 this$1;
                    final PeriodicAdvertisingCallback val$callback;
                    final int val$syncHandle;

            
            {
                this$1 = final__pcls1;
                callback = periodicadvertisingcallback;
                syncHandle = I.this;
                super();
            }
                }
);
            }

            final PeriodicAdvertisingManager this$0;
            final PeriodicAdvertisingCallback val$callback;
            final Handler val$handler;

            
            {
                this$0 = PeriodicAdvertisingManager.this;
                handler = handler1;
                callback = periodicadvertisingcallback;
                super();
            }
        }
;
    }

    public void registerSync(ScanResult scanresult, int i, int j, PeriodicAdvertisingCallback periodicadvertisingcallback)
    {
        registerSync(scanresult, i, j, periodicadvertisingcallback, null);
    }

    public void registerSync(ScanResult scanresult, int i, int j, PeriodicAdvertisingCallback periodicadvertisingcallback, Handler handler)
    {
        if(periodicadvertisingcallback == null)
            throw new IllegalArgumentException("callback can't be null");
        if(scanresult == null)
            throw new IllegalArgumentException("scanResult can't be null");
        if(scanresult.getAdvertisingSid() == 255)
            throw new IllegalArgumentException("scanResult must contain a valid sid");
        if(i < 0 || i > 499)
            throw new IllegalArgumentException("timeout must be between 10 and 16384");
        if(j < 10 || j > 16384)
            throw new IllegalArgumentException("timeout must be between 10 and 16384");
        IBluetoothGatt ibluetoothgatt;
        Handler handler1;
        try
        {
            ibluetoothgatt = mBluetoothManager.getBluetoothGatt();
        }
        // Misplaced declaration of an exception variable
        catch(Handler handler)
        {
            Log.e("PeriodicAdvertisingManager", "Failed to get Bluetooth gatt - ", handler);
            periodicadvertisingcallback.onSyncEstablished(0, scanresult.getDevice(), scanresult.getAdvertisingSid(), i, j, 2);
            return;
        }
        handler1 = handler;
        if(handler == null)
            handler1 = new Handler(Looper.getMainLooper());
        handler = wrap(periodicadvertisingcallback, handler1);
        callbackWrappers.put(periodicadvertisingcallback, handler);
        try
        {
            ibluetoothgatt.registerSync(scanresult, i, j, handler);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(ScanResult scanresult)
        {
            Log.e("PeriodicAdvertisingManager", "Failed to register sync - ", scanresult);
        }
    }

    public void unregisterSync(PeriodicAdvertisingCallback periodicadvertisingcallback)
    {
        if(periodicadvertisingcallback == null)
            throw new IllegalArgumentException("callback can't be null");
        IBluetoothGatt ibluetoothgatt;
        try
        {
            ibluetoothgatt = mBluetoothManager.getBluetoothGatt();
        }
        // Misplaced declaration of an exception variable
        catch(PeriodicAdvertisingCallback periodicadvertisingcallback)
        {
            Log.e("PeriodicAdvertisingManager", "Failed to get Bluetooth gatt - ", periodicadvertisingcallback);
            return;
        }
        periodicadvertisingcallback = (IPeriodicAdvertisingCallback)callbackWrappers.remove(periodicadvertisingcallback);
        if(periodicadvertisingcallback == null)
            throw new IllegalArgumentException("callback was not properly registered");
        try
        {
            ibluetoothgatt.unregisterSync(periodicadvertisingcallback);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(PeriodicAdvertisingCallback periodicadvertisingcallback)
        {
            Log.e("PeriodicAdvertisingManager", "Failed to cancel sync creation - ", periodicadvertisingcallback);
        }
    }

    private static final int SKIP_MAX = 499;
    private static final int SKIP_MIN = 0;
    private static final int SYNC_STARTING = -1;
    private static final String TAG = "PeriodicAdvertisingManager";
    private static final int TIMEOUT_MAX = 16384;
    private static final int TIMEOUT_MIN = 10;
    Map callbackWrappers;
    private BluetoothAdapter mBluetoothAdapter;
    private final IBluetoothManager mBluetoothManager;
}
