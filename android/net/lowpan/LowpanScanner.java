// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.lowpan;

import android.os.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// Referenced classes of package android.net.lowpan:
//            LowpanProperties, LowpanProperty, LowpanException, ILowpanInterface, 
//            LowpanEnergyScanResult, LowpanBeaconInfo

public class LowpanScanner
{
    public static abstract class Callback
    {

        public void onEnergyScanResult(LowpanEnergyScanResult lowpanenergyscanresult)
        {
        }

        public void onNetScanBeacon(LowpanBeaconInfo lowpanbeaconinfo)
        {
        }

        public void onScanFinished()
        {
        }

        public Callback()
        {
        }
    }


    static Callback _2D_get0(LowpanScanner lowpanscanner)
    {
        return lowpanscanner.mCallback;
    }

    static Handler _2D_get1(LowpanScanner lowpanscanner)
    {
        return lowpanscanner.mHandler;
    }

    LowpanScanner(ILowpanInterface ilowpaninterface)
    {
        mCallback = null;
        mHandler = null;
        mChannelMask = null;
        mTxPower = 0x7fffffff;
        mBinder = ilowpaninterface;
    }

    private Map createScanOptionMap()
    {
        HashMap hashmap = new HashMap();
        if(mChannelMask != null)
            LowpanProperties.KEY_CHANNEL_MASK.putInMap(hashmap, mChannelMask.stream().mapToInt(_.Lambda.ahIH8UUgV8jOvhfOz4liCd3_gII.$INST$0).toArray());
        if(mTxPower != 0x7fffffff)
            LowpanProperties.KEY_MAX_TX_POWER.putInMap(hashmap, Integer.valueOf(mTxPower));
        return hashmap;
    }

    static int lambda$_2D_android_net_lowpan_LowpanScanner_4627(Integer integer)
    {
        return integer.intValue();
    }

    public void addChannel(int i)
    {
        if(mChannelMask == null)
            mChannelMask = new ArrayList();
        mChannelMask.add(Integer.valueOf(i));
    }

    public Collection getChannelMask()
    {
        return (Collection)mChannelMask.clone();
    }

    public int getTxPower()
    {
        return mTxPower;
    }

    public void setCallback(Callback callback)
    {
        setCallback(callback, null);
    }

    public void setCallback(Callback callback, Handler handler)
    {
        this;
        JVM INSTR monitorenter ;
        mCallback = callback;
        mHandler = handler;
        this;
        JVM INSTR monitorexit ;
        return;
        callback;
        throw callback;
    }

    public void setChannelMask(Collection collection)
    {
        if(collection == null)
        {
            mChannelMask = null;
        } else
        {
            if(mChannelMask == null)
                mChannelMask = new ArrayList();
            else
                mChannelMask.clear();
            mChannelMask.addAll(collection);
        }
    }

    public void setTxPower(int i)
    {
        mTxPower = i;
    }

    public void startEnergyScan()
        throws LowpanException
    {
        Map map = createScanOptionMap();
        ILowpanEnergyScanCallback.Stub stub = new ILowpanEnergyScanCallback.Stub() {

            static void lambda$_2D_android_net_lowpan_LowpanScanner$2_8042(Callback callback, int i, int j)
            {
                if(callback != null)
                {
                    LowpanEnergyScanResult lowpanenergyscanresult = new LowpanEnergyScanResult();
                    lowpanenergyscanresult.setChannel(i);
                    lowpanenergyscanresult.setMaxRssi(j);
                    callback.onEnergyScanResult(lowpanenergyscanresult);
                }
            }

            static void lambda$_2D_android_net_lowpan_LowpanScanner$2_9089(Callback callback)
            {
                callback.onScanFinished();
            }

            public void onEnergyScanFinished()
            {
                Object obj = LowpanScanner._2D_get0(LowpanScanner.this);
                Handler handler = LowpanScanner._2D_get1(LowpanScanner.this);
                if(obj == null)
                    return;
                obj = new _.Lambda.QeWpJp8A7h1GVWRfnDNEd25gCZ8((byte)2, obj);
                if(handler != null)
                    handler.post(((Runnable) (obj)));
                else
                    ((Runnable) (obj)).run();
            }

            public void onEnergyScanResult(int i, int j)
            {
                Object obj = LowpanScanner._2D_get0(LowpanScanner.this);
                Handler handler = LowpanScanner._2D_get1(LowpanScanner.this);
                if(obj == null)
                    return;
                obj = new _.Lambda.ahIH8UUgV8jOvhfOz4liCd3_gII._cls1(i, j, obj);
                if(handler != null)
                    handler.post(((Runnable) (obj)));
                else
                    ((Runnable) (obj)).run();
            }

            final LowpanScanner this$0;

            
            {
                this$0 = LowpanScanner.this;
                super();
            }
        }
;
        try
        {
            mBinder.startEnergyScan(map, stub);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowAsRuntimeException();
        }
        catch(ServiceSpecificException servicespecificexception)
        {
            throw LowpanException.rethrowFromServiceSpecificException(servicespecificexception);
        }
    }

    public void startNetScan()
        throws LowpanException
    {
        Map map = createScanOptionMap();
        ILowpanNetScanCallback.Stub stub = new ILowpanNetScanCallback.Stub() {

            static void lambda$_2D_android_net_lowpan_LowpanScanner$1_5692(Callback callback, LowpanBeaconInfo lowpanbeaconinfo)
            {
                callback.onNetScanBeacon(lowpanbeaconinfo);
            }

            static void lambda$_2D_android_net_lowpan_LowpanScanner$1_6441(Callback callback)
            {
                callback.onScanFinished();
            }

            public void onNetScanBeacon(LowpanBeaconInfo lowpanbeaconinfo)
            {
                LowpanScanner lowpanscanner = LowpanScanner.this;
                lowpanscanner;
                JVM INSTR monitorenter ;
                Callback callback;
                Handler handler;
                callback = LowpanScanner._2D_get0(LowpanScanner.this);
                handler = LowpanScanner._2D_get1(LowpanScanner.this);
                lowpanscanner;
                JVM INSTR monitorexit ;
                if(callback == null)
                    return;
                break MISSING_BLOCK_LABEL_36;
                lowpanbeaconinfo;
                throw lowpanbeaconinfo;
                lowpanbeaconinfo = new _.Lambda.lq0tFj928XFoCdCDLCq_E_OIg9U((byte)8, callback, lowpanbeaconinfo);
                if(handler != null)
                    handler.post(lowpanbeaconinfo);
                else
                    lowpanbeaconinfo.run();
                return;
            }

            public void onNetScanFinished()
            {
                LowpanScanner lowpanscanner = LowpanScanner.this;
                lowpanscanner;
                JVM INSTR monitorenter ;
                Callback callback;
                Object obj;
                callback = LowpanScanner._2D_get0(LowpanScanner.this);
                obj = LowpanScanner._2D_get1(LowpanScanner.this);
                lowpanscanner;
                JVM INSTR monitorexit ;
                if(callback == null)
                    return;
                break MISSING_BLOCK_LABEL_35;
                obj;
                throw obj;
                _.Lambda.QeWpJp8A7h1GVWRfnDNEd25gCZ8 qewpjp8a7h1gvwrfndned25gcz8 = new _.Lambda.QeWpJp8A7h1GVWRfnDNEd25gCZ8((byte)1, callback);
                if(obj != null)
                    ((Handler) (obj)).post(qewpjp8a7h1gvwrfndned25gcz8);
                else
                    qewpjp8a7h1gvwrfndned25gcz8.run();
                return;
            }

            final LowpanScanner this$0;

            
            {
                this$0 = LowpanScanner.this;
                super();
            }
        }
;
        try
        {
            mBinder.startNetScan(map, stub);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowAsRuntimeException();
        }
        catch(ServiceSpecificException servicespecificexception)
        {
            throw LowpanException.rethrowFromServiceSpecificException(servicespecificexception);
        }
    }

    public void stopEnergyScan()
    {
        try
        {
            mBinder.stopEnergyScan();
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowAsRuntimeException();
        }
    }

    public void stopNetScan()
    {
        try
        {
            mBinder.stopNetScan();
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowAsRuntimeException();
        }
    }

    private static final String TAG = android/net/lowpan/LowpanScanner.getSimpleName();
    private ILowpanInterface mBinder;
    private Callback mCallback;
    private ArrayList mChannelMask;
    private Handler mHandler;
    private int mTxPower;

}
