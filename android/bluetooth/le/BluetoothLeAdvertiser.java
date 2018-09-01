// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth.le;

import android.bluetooth.*;
import android.os.*;
import android.util.Log;
import android.util.SparseArray;
import java.util.*;

// Referenced classes of package android.bluetooth.le:
//            AdvertiseData, BluetoothLeUtils, AdvertiseSettings, AdvertisingSetParameters, 
//            AdvertisingSetCallback, IAdvertisingSetCallback, AdvertiseCallback, PeriodicAdvertisingParameters, 
//            AdvertisingSet

public final class BluetoothLeAdvertiser
{

    static Map _2D_get0(BluetoothLeAdvertiser bluetoothleadvertiser)
    {
        return bluetoothleadvertiser.mAdvertisingSets;
    }

    static IBluetoothManager _2D_get1(BluetoothLeAdvertiser bluetoothleadvertiser)
    {
        return bluetoothleadvertiser.mBluetoothManager;
    }

    static Map _2D_get2(BluetoothLeAdvertiser bluetoothleadvertiser)
    {
        return bluetoothleadvertiser.mCallbackWrappers;
    }

    static void _2D_wrap0(BluetoothLeAdvertiser bluetoothleadvertiser, AdvertiseCallback advertisecallback, int i)
    {
        bluetoothleadvertiser.postStartFailure(advertisecallback, i);
    }

    static void _2D_wrap1(BluetoothLeAdvertiser bluetoothleadvertiser, AdvertiseCallback advertisecallback, AdvertiseSettings advertisesettings)
    {
        bluetoothleadvertiser.postStartSuccess(advertisecallback, advertisesettings);
    }

    public BluetoothLeAdvertiser(IBluetoothManager ibluetoothmanager)
    {
        mBluetoothManager = ibluetoothmanager;
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    private int byteLength(byte abyte0[])
    {
        int i;
        if(abyte0 == null)
            i = 0;
        else
            i = abyte0.length;
        return i;
    }

    private void postStartFailure(final AdvertiseCallback callback, final int error)
    {
        mHandler.post(new Runnable() {

            public void run()
            {
                callback.onStartFailure(error);
            }

            final BluetoothLeAdvertiser this$0;
            final AdvertiseCallback val$callback;
            final int val$error;

            
            {
                this$0 = BluetoothLeAdvertiser.this;
                callback = advertisecallback;
                error = i;
                super();
            }
        }
);
    }

    private void postStartSetFailure(Handler handler, final AdvertisingSetCallback callback, final int error)
    {
        handler.post(new Runnable() {

            public void run()
            {
                callback.onAdvertisingSetStarted(null, 0, error);
            }

            final BluetoothLeAdvertiser this$0;
            final AdvertisingSetCallback val$callback;
            final int val$error;

            
            {
                this$0 = BluetoothLeAdvertiser.this;
                callback = advertisingsetcallback;
                error = i;
                super();
            }
        }
);
    }

    private void postStartSuccess(final AdvertiseCallback callback, final AdvertiseSettings settings)
    {
        mHandler.post(new Runnable() {

            public void run()
            {
                callback.onStartSuccess(settings);
            }

            final BluetoothLeAdvertiser this$0;
            final AdvertiseCallback val$callback;
            final AdvertiseSettings val$settings;

            
            {
                this$0 = BluetoothLeAdvertiser.this;
                callback = advertisecallback;
                settings = advertisesettings;
                super();
            }
        }
);
    }

    private int totalBytes(AdvertiseData advertisedata, boolean flag)
    {
        if(advertisedata == null)
            return 0;
        int i;
        int j;
        if(flag)
            i = 3;
        else
            i = 0;
        j = i;
        if(advertisedata.getServiceUuids() != null)
        {
            int k = 0;
            int l = 0;
            int i1 = 0;
            for(Iterator iterator = advertisedata.getServiceUuids().iterator(); iterator.hasNext();)
            {
                ParcelUuid parceluuid = (ParcelUuid)iterator.next();
                if(BluetoothUuid.is16BitUuid(parceluuid))
                    k++;
                else
                if(BluetoothUuid.is32BitUuid(parceluuid))
                    l++;
                else
                    i1++;
            }

            j = i;
            if(k != 0)
                j = i + (k * 2 + 2);
            i = j;
            if(l != 0)
                i = j + (l * 4 + 2);
            j = i;
            if(i1 != 0)
                j = i + (i1 * 16 + 2);
        }
        Iterator iterator1 = advertisedata.getServiceData().keySet().iterator();
        ParcelUuid parceluuid1;
        for(i = j; iterator1.hasNext(); i += byteLength((byte[])advertisedata.getServiceData().get(parceluuid1)) + (j + 2))
        {
            parceluuid1 = (ParcelUuid)iterator1.next();
            j = BluetoothUuid.uuidToBytes(parceluuid1).length;
        }

        for(j = 0; j < advertisedata.getManufacturerSpecificData().size(); j++)
            i += byteLength((byte[])advertisedata.getManufacturerSpecificData().valueAt(j)) + 4;

        j = i;
        if(advertisedata.getIncludeTxPowerLevel())
            j = i + 3;
        i = j;
        if(advertisedata.getIncludeDeviceName())
        {
            i = j;
            if(mBluetoothAdapter.getName() != null)
                i = j + (mBluetoothAdapter.getName().length() + 2);
        }
        return i;
    }

    public void cleanup()
    {
        mLegacyAdvertisers.clear();
        mCallbackWrappers.clear();
        mAdvertisingSets.clear();
    }

    public void startAdvertising(AdvertiseSettings advertisesettings, AdvertiseData advertisedata, AdvertiseCallback advertisecallback)
    {
        startAdvertising(advertisesettings, advertisedata, null, advertisecallback);
    }

    public void startAdvertising(AdvertiseSettings advertisesettings, AdvertiseData advertisedata, AdvertiseData advertisedata1, AdvertiseCallback advertisecallback)
    {
        Map map = mLegacyAdvertisers;
        map;
        JVM INSTR monitorenter ;
        BluetoothLeUtils.checkAdapterStateOn(mBluetoothAdapter);
        if(advertisecallback != null)
            break MISSING_BLOCK_LABEL_39;
        advertisesettings = JVM INSTR new #240 <Class IllegalArgumentException>;
        advertisesettings.IllegalArgumentException("callback cannot be null");
        throw advertisesettings;
        advertisesettings;
        map;
        JVM INSTR monitorexit ;
        throw advertisesettings;
        boolean flag;
        flag = advertisesettings.isConnectable();
        if(totalBytes(advertisedata, flag) <= 31 && totalBytes(advertisedata1, false) <= 31)
            break MISSING_BLOCK_LABEL_79;
        postStartFailure(advertisecallback, 1);
        map;
        JVM INSTR monitorexit ;
        return;
        if(!mLegacyAdvertisers.containsKey(advertisecallback))
            break MISSING_BLOCK_LABEL_104;
        postStartFailure(advertisecallback, 3);
        map;
        JVM INSTR monitorexit ;
        return;
        AdvertisingSetParameters.Builder builder;
        builder = JVM INSTR new #258 <Class AdvertisingSetParameters$Builder>;
        builder.AdvertisingSetParameters.Builder();
        builder.setLegacyMode(true);
        builder.setConnectable(flag);
        builder.setScannable(true);
        if(advertisesettings.getMode() != 0) goto _L2; else goto _L1
_L1:
        builder.setInterval(1600);
_L7:
        if(advertisesettings.getTxPowerLevel() != 0) goto _L4; else goto _L3
_L3:
        builder.setTxPowerLevel(-21);
_L9:
        int i = 0;
        int j = advertisesettings.getTimeout();
        if(j > 0)
        {
            if(j >= 10)
                break MISSING_BLOCK_LABEL_329;
            i = 1;
        }
_L13:
        advertisesettings = wrapOldCallback(advertisecallback, advertisesettings);
        mLegacyAdvertisers.put(advertisecallback, advertisesettings);
        startAdvertisingSet(builder.build(), advertisedata, advertisedata1, null, null, i, 0, advertisesettings);
        map;
        JVM INSTR monitorexit ;
        return;
_L2:
        if(advertisesettings.getMode() != 1) goto _L6; else goto _L5
_L5:
        builder.setInterval(400);
          goto _L7
_L6:
        if(advertisesettings.getMode() != 2) goto _L7; else goto _L8
_L8:
        builder.setInterval(160);
          goto _L7
_L4:
label0:
        {
            if(advertisesettings.getTxPowerLevel() != 1)
                break label0;
            builder.setTxPowerLevel(-15);
        }
          goto _L9
        if(advertisesettings.getTxPowerLevel() != 2) goto _L11; else goto _L10
_L10:
        builder.setTxPowerLevel(-7);
          goto _L9
_L11:
        if(advertisesettings.getTxPowerLevel() != 3) goto _L9; else goto _L12
_L12:
        builder.setTxPowerLevel(1);
          goto _L9
        i = j / 10;
          goto _L13
    }

    public void startAdvertisingSet(AdvertisingSetParameters advertisingsetparameters, AdvertiseData advertisedata, AdvertiseData advertisedata1, PeriodicAdvertisingParameters periodicadvertisingparameters, AdvertiseData advertisedata2, int i, int j, 
            AdvertisingSetCallback advertisingsetcallback)
    {
        startAdvertisingSet(advertisingsetparameters, advertisedata, advertisedata1, periodicadvertisingparameters, advertisedata2, i, j, advertisingsetcallback, new Handler(Looper.getMainLooper()));
    }

    public void startAdvertisingSet(AdvertisingSetParameters advertisingsetparameters, AdvertiseData advertisedata, AdvertiseData advertisedata1, PeriodicAdvertisingParameters periodicadvertisingparameters, AdvertiseData advertisedata2, int i, int j, 
            AdvertisingSetCallback advertisingsetcallback, Handler handler)
    {
        BluetoothLeUtils.checkAdapterStateOn(mBluetoothAdapter);
        if(advertisingsetcallback == null)
            throw new IllegalArgumentException("callback cannot be null");
        boolean flag = advertisingsetparameters.isConnectable();
        if(advertisingsetparameters.isLegacy())
        {
            if(totalBytes(advertisedata, flag) > 31)
                throw new IllegalArgumentException("Legacy advertising data too big");
            if(totalBytes(advertisedata1, false) > 31)
                throw new IllegalArgumentException("Legacy scan response data too big");
        } else
        {
            boolean flag1 = mBluetoothAdapter.isLeCodedPhySupported();
            boolean flag2 = mBluetoothAdapter.isLe2MPhySupported();
            int k = advertisingsetparameters.getPrimaryPhy();
            int l = advertisingsetparameters.getSecondaryPhy();
            if(k == 3 && flag1 ^ true)
                throw new IllegalArgumentException("Unsupported primary PHY selected");
            if(l == 3 && flag1 ^ true || l == 2 && flag2 ^ true)
                throw new IllegalArgumentException("Unsupported secondary PHY selected");
            l = mBluetoothAdapter.getLeMaximumAdvertisingDataLength();
            if(totalBytes(advertisedata, flag) > l)
                throw new IllegalArgumentException("Advertising data too big");
            if(totalBytes(advertisedata1, false) > l)
                throw new IllegalArgumentException("Scan response data too big");
            if(totalBytes(advertisedata2, false) > l)
                throw new IllegalArgumentException("Periodic advertising data too big");
            flag = mBluetoothAdapter.isLePeriodicAdvertisingSupported();
            if(periodicadvertisingparameters != null && flag ^ true)
                throw new IllegalArgumentException("Controller does not support LE Periodic Advertising");
        }
        if(j < 0 || j > 255)
            throw new IllegalArgumentException((new StringBuilder()).append("maxExtendedAdvertisingEvents out of range: ").append(j).toString());
        if(j != 0 && mBluetoothAdapter.isLePeriodicAdvertisingSupported() ^ true)
            throw new IllegalArgumentException("Can't use maxExtendedAdvertisingEvents with controller that don't support LE Extended Advertising");
        if(i < 0 || i > 65535)
            throw new IllegalArgumentException((new StringBuilder()).append("duration out of range: ").append(i).toString());
        IBluetoothGatt ibluetoothgatt;
        IAdvertisingSetCallback iadvertisingsetcallback;
        try
        {
            ibluetoothgatt = mBluetoothManager.getBluetoothGatt();
        }
        // Misplaced declaration of an exception variable
        catch(AdvertisingSetParameters advertisingsetparameters)
        {
            Log.e("BluetoothLeAdvertiser", "Failed to get Bluetooth gatt - ", advertisingsetparameters);
            postStartSetFailure(handler, advertisingsetcallback, 4);
            return;
        }
        iadvertisingsetcallback = wrap(advertisingsetcallback, handler);
        if(mCallbackWrappers.putIfAbsent(advertisingsetcallback, iadvertisingsetcallback) != null)
            throw new IllegalArgumentException("callback instance already associated with advertising");
        try
        {
            ibluetoothgatt.startAdvertisingSet(advertisingsetparameters, advertisedata, advertisedata1, periodicadvertisingparameters, advertisedata2, i, j, iadvertisingsetcallback);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(AdvertisingSetParameters advertisingsetparameters)
        {
            Log.e("BluetoothLeAdvertiser", "Failed to start advertising set - ", advertisingsetparameters);
        }
        postStartSetFailure(handler, advertisingsetcallback, 4);
    }

    public void startAdvertisingSet(AdvertisingSetParameters advertisingsetparameters, AdvertiseData advertisedata, AdvertiseData advertisedata1, PeriodicAdvertisingParameters periodicadvertisingparameters, AdvertiseData advertisedata2, AdvertisingSetCallback advertisingsetcallback)
    {
        startAdvertisingSet(advertisingsetparameters, advertisedata, advertisedata1, periodicadvertisingparameters, advertisedata2, 0, 0, advertisingsetcallback, new Handler(Looper.getMainLooper()));
    }

    public void startAdvertisingSet(AdvertisingSetParameters advertisingsetparameters, AdvertiseData advertisedata, AdvertiseData advertisedata1, PeriodicAdvertisingParameters periodicadvertisingparameters, AdvertiseData advertisedata2, AdvertisingSetCallback advertisingsetcallback, Handler handler)
    {
        startAdvertisingSet(advertisingsetparameters, advertisedata, advertisedata1, periodicadvertisingparameters, advertisedata2, 0, 0, advertisingsetcallback, handler);
    }

    public void stopAdvertising(AdvertiseCallback advertisecallback)
    {
        Map map = mLegacyAdvertisers;
        map;
        JVM INSTR monitorenter ;
        if(advertisecallback != null)
            break MISSING_BLOCK_LABEL_28;
        advertisecallback = JVM INSTR new #240 <Class IllegalArgumentException>;
        advertisecallback.IllegalArgumentException("callback cannot be null");
        throw advertisecallback;
        advertisecallback;
        map;
        JVM INSTR monitorexit ;
        throw advertisecallback;
        AdvertisingSetCallback advertisingsetcallback = (AdvertisingSetCallback)mLegacyAdvertisers.get(advertisecallback);
        if(advertisingsetcallback != null)
            break MISSING_BLOCK_LABEL_49;
        map;
        JVM INSTR monitorexit ;
        return;
        stopAdvertisingSet(advertisingsetcallback);
        mLegacyAdvertisers.remove(advertisecallback);
        map;
        JVM INSTR monitorexit ;
    }

    public void stopAdvertisingSet(AdvertisingSetCallback advertisingsetcallback)
    {
        int i;
        i = mBluetoothAdapter.getLeState();
        StringBuilder stringbuilder = JVM INSTR new #348 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.d("BluetoothLeAdvertiser", stringbuilder.append("stopAdvertisingSet(): ").append(BluetoothAdapter.nameForState(i)).toString());
        if(mBluetoothAdapter == null || i != 12 && i != 15 && i != 13 && i != 11)
        {
            try
            {
                advertisingsetcallback = JVM INSTR new #413 <Class IllegalStateException>;
                advertisingsetcallback.IllegalStateException("BT Adapter is not turned ON");
                throw advertisingsetcallback;
            }
            // Misplaced declaration of an exception variable
            catch(AdvertisingSetCallback advertisingsetcallback)
            {
                Log.e("BluetoothLeAdvertiser", "Failed to stop Advertisement:", advertisingsetcallback);
            }
            return;
        }
        if(advertisingsetcallback == null)
            throw new IllegalArgumentException("callback cannot be null");
        advertisingsetcallback = (IAdvertisingSetCallback)mCallbackWrappers.remove(advertisingsetcallback);
        if(advertisingsetcallback == null)
            return;
        mBluetoothManager.getBluetoothGatt().stopAdvertisingSet(advertisingsetcallback);
_L1:
        return;
        advertisingsetcallback;
        Log.e("BluetoothLeAdvertiser", "Failed to stop advertising - ", advertisingsetcallback);
          goto _L1
    }

    IAdvertisingSetCallback wrap(final AdvertisingSetCallback callback, final Handler handler)
    {
        return new IAdvertisingSetCallback.Stub() {

            public void onAdvertisingDataSet(final int advertiserId, int i)
            {
                handler.post(i. new Runnable() {

                    public void run()
                    {
                        AdvertisingSet advertisingset = (AdvertisingSet)BluetoothLeAdvertiser._2D_get0(_fld0).get(Integer.valueOf(advertiserId));
                        callback.onAdvertisingDataSet(advertisingset, status);
                    }

                    final _cls2 this$1;
                    final int val$advertiserId;
                    final AdvertisingSetCallback val$callback;
                    final int val$status;

            
            {
                this$1 = final__pcls2;
                advertiserId = i;
                callback = advertisingsetcallback;
                status = I.this;
                super();
            }
                }
);
            }

            public void onAdvertisingEnabled(final int advertiserId, final boolean enabled, int i)
            {
                handler.post(i. new Runnable() {

                    public void run()
                    {
                        AdvertisingSet advertisingset = (AdvertisingSet)BluetoothLeAdvertiser._2D_get0(_fld0).get(Integer.valueOf(advertiserId));
                        callback.onAdvertisingEnabled(advertisingset, enabled, status);
                    }

                    final _cls2 this$1;
                    final int val$advertiserId;
                    final AdvertisingSetCallback val$callback;
                    final boolean val$enabled;
                    final int val$status;

            
            {
                this$1 = final__pcls2;
                advertiserId = i;
                callback = advertisingsetcallback;
                enabled = flag;
                status = I.this;
                super();
            }
                }
);
            }

            public void onAdvertisingParametersUpdated(final int advertiserId, final int txPower, int i)
            {
                handler.post(i. new Runnable() {

                    public void run()
                    {
                        AdvertisingSet advertisingset = (AdvertisingSet)BluetoothLeAdvertiser._2D_get0(_fld0).get(Integer.valueOf(advertiserId));
                        callback.onAdvertisingParametersUpdated(advertisingset, txPower, status);
                    }

                    final _cls2 this$1;
                    final int val$advertiserId;
                    final AdvertisingSetCallback val$callback;
                    final int val$status;
                    final int val$txPower;

            
            {
                this$1 = final__pcls2;
                advertiserId = i;
                callback = advertisingsetcallback;
                txPower = j;
                status = I.this;
                super();
            }
                }
);
            }

            public void onAdvertisingSetStarted(final int advertiserId, int i, final int status)
            {
                handler.post(i. new Runnable() {

                    public void run()
                    {
                        if(status != 0)
                        {
                            callback.onAdvertisingSetStarted(null, 0, status);
                            BluetoothLeAdvertiser._2D_get2(_fld0).remove(callback);
                            return;
                        } else
                        {
                            AdvertisingSet advertisingset = new AdvertisingSet(advertiserId, BluetoothLeAdvertiser._2D_get1(_fld0));
                            BluetoothLeAdvertiser._2D_get0(_fld0).put(Integer.valueOf(advertiserId), advertisingset);
                            callback.onAdvertisingSetStarted(advertisingset, txPower, status);
                            return;
                        }
                    }

                    final _cls2 this$1;
                    final int val$advertiserId;
                    final AdvertisingSetCallback val$callback;
                    final int val$status;
                    final int val$txPower;

            
            {
                this$1 = final__pcls2;
                status = i;
                callback = advertisingsetcallback;
                advertiserId = j;
                txPower = I.this;
                super();
            }
                }
);
            }

            public void onAdvertisingSetStopped(final int advertiserId)
            {
                handler.post(callback. new Runnable() {

                    public void run()
                    {
                        AdvertisingSet advertisingset = (AdvertisingSet)BluetoothLeAdvertiser._2D_get0(_fld0).get(Integer.valueOf(advertiserId));
                        callback.onAdvertisingSetStopped(advertisingset);
                        BluetoothLeAdvertiser._2D_get0(_fld0).remove(Integer.valueOf(advertiserId));
                        BluetoothLeAdvertiser._2D_get2(_fld0).remove(callback);
                    }

                    final _cls2 this$1;
                    final int val$advertiserId;
                    final AdvertisingSetCallback val$callback;

            
            {
                this$1 = final__pcls2;
                advertiserId = i;
                callback = AdvertisingSetCallback.this;
                super();
            }
                }
);
            }

            public void onOwnAddressRead(final int advertiserId, final int addressType, String s)
            {
                handler.post(s. new Runnable() {

                    public void run()
                    {
                        AdvertisingSet advertisingset = (AdvertisingSet)BluetoothLeAdvertiser._2D_get0(_fld0).get(Integer.valueOf(advertiserId));
                        callback.onOwnAddressRead(advertisingset, addressType, address);
                    }

                    final _cls2 this$1;
                    final String val$address;
                    final int val$addressType;
                    final int val$advertiserId;
                    final AdvertisingSetCallback val$callback;

            
            {
                this$1 = final__pcls2;
                advertiserId = i;
                callback = advertisingsetcallback;
                addressType = j;
                address = String.this;
                super();
            }
                }
);
            }

            public void onPeriodicAdvertisingDataSet(final int advertiserId, int i)
            {
                handler.post(i. new Runnable() {

                    public void run()
                    {
                        AdvertisingSet advertisingset = (AdvertisingSet)BluetoothLeAdvertiser._2D_get0(_fld0).get(Integer.valueOf(advertiserId));
                        callback.onPeriodicAdvertisingDataSet(advertisingset, status);
                    }

                    final _cls2 this$1;
                    final int val$advertiserId;
                    final AdvertisingSetCallback val$callback;
                    final int val$status;

            
            {
                this$1 = final__pcls2;
                advertiserId = i;
                callback = advertisingsetcallback;
                status = I.this;
                super();
            }
                }
);
            }

            public void onPeriodicAdvertisingEnabled(final int advertiserId, final boolean enable, int i)
            {
                handler.post(i. new Runnable() {

                    public void run()
                    {
                        AdvertisingSet advertisingset = (AdvertisingSet)BluetoothLeAdvertiser._2D_get0(_fld0).get(Integer.valueOf(advertiserId));
                        callback.onPeriodicAdvertisingEnabled(advertisingset, enable, status);
                    }

                    final _cls2 this$1;
                    final int val$advertiserId;
                    final AdvertisingSetCallback val$callback;
                    final boolean val$enable;
                    final int val$status;

            
            {
                this$1 = final__pcls2;
                advertiserId = i;
                callback = advertisingsetcallback;
                enable = flag;
                status = I.this;
                super();
            }
                }
);
            }

            public void onPeriodicAdvertisingParametersUpdated(final int advertiserId, int i)
            {
                handler.post(i. new Runnable() {

                    public void run()
                    {
                        AdvertisingSet advertisingset = (AdvertisingSet)BluetoothLeAdvertiser._2D_get0(_fld0).get(Integer.valueOf(advertiserId));
                        callback.onPeriodicAdvertisingParametersUpdated(advertisingset, status);
                    }

                    final _cls2 this$1;
                    final int val$advertiserId;
                    final AdvertisingSetCallback val$callback;
                    final int val$status;

            
            {
                this$1 = final__pcls2;
                advertiserId = i;
                callback = advertisingsetcallback;
                status = I.this;
                super();
            }
                }
);
            }

            public void onScanResponseDataSet(final int advertiserId, int i)
            {
                handler.post(i. new Runnable() {

                    public void run()
                    {
                        AdvertisingSet advertisingset = (AdvertisingSet)BluetoothLeAdvertiser._2D_get0(_fld0).get(Integer.valueOf(advertiserId));
                        callback.onScanResponseDataSet(advertisingset, status);
                    }

                    final _cls2 this$1;
                    final int val$advertiserId;
                    final AdvertisingSetCallback val$callback;
                    final int val$status;

            
            {
                this$1 = final__pcls2;
                advertiserId = i;
                callback = advertisingsetcallback;
                status = I.this;
                super();
            }
                }
);
            }

            final BluetoothLeAdvertiser this$0;
            final AdvertisingSetCallback val$callback;
            final Handler val$handler;

            
            {
                this$0 = BluetoothLeAdvertiser.this;
                handler = handler1;
                callback = advertisingsetcallback;
                super();
            }
        }
;
    }

    AdvertisingSetCallback wrapOldCallback(final AdvertiseCallback callback, final AdvertiseSettings settings)
    {
        return new AdvertisingSetCallback() {

            public void onAdvertisingEnabled(AdvertisingSet advertisingset, boolean flag, int i)
            {
                if(flag)
                {
                    Log.e("BluetoothLeAdvertiser", "Legacy advertiser should be only disabled on timeout, but was enabled!");
                    return;
                } else
                {
                    stopAdvertising(callback);
                    return;
                }
            }

            public void onAdvertisingSetStarted(AdvertisingSet advertisingset, int i, int j)
            {
                if(j != 0)
                {
                    BluetoothLeAdvertiser._2D_wrap0(BluetoothLeAdvertiser.this, callback, j);
                    return;
                } else
                {
                    BluetoothLeAdvertiser._2D_wrap1(BluetoothLeAdvertiser.this, callback, settings);
                    return;
                }
            }

            final BluetoothLeAdvertiser this$0;
            final AdvertiseCallback val$callback;
            final AdvertiseSettings val$settings;

            
            {
                this$0 = BluetoothLeAdvertiser.this;
                callback = advertisecallback;
                settings = advertisesettings;
                super();
            }
        }
;
    }

    private static final int FLAGS_FIELD_BYTES = 3;
    private static final int MANUFACTURER_SPECIFIC_DATA_LENGTH = 2;
    private static final int MAX_ADVERTISING_DATA_BYTES = 1650;
    private static final int MAX_LEGACY_ADVERTISING_DATA_BYTES = 31;
    private static final int OVERHEAD_BYTES_PER_FIELD = 2;
    private static final String TAG = "BluetoothLeAdvertiser";
    private final Map mAdvertisingSets = Collections.synchronizedMap(new HashMap());
    private BluetoothAdapter mBluetoothAdapter;
    private final IBluetoothManager mBluetoothManager;
    private final Map mCallbackWrappers = Collections.synchronizedMap(new HashMap());
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final Map mLegacyAdvertisers = new HashMap();
}
