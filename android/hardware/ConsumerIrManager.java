// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware;

import android.content.Context;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;

// Referenced classes of package android.hardware:
//            IConsumerIrService

public final class ConsumerIrManager
{
    public final class CarrierFrequencyRange
    {

        public int getMaxFrequency()
        {
            return mMaxFrequency;
        }

        public int getMinFrequency()
        {
            return mMinFrequency;
        }

        private final int mMaxFrequency;
        private final int mMinFrequency;
        final ConsumerIrManager this$0;

        public CarrierFrequencyRange(int i, int j)
        {
            this$0 = ConsumerIrManager.this;
            super();
            mMinFrequency = i;
            mMaxFrequency = j;
        }
    }


    public ConsumerIrManager(Context context)
        throws android.os.ServiceManager.ServiceNotFoundException
    {
        mPackageName = context.getPackageName();
    }

    public CarrierFrequencyRange[] getCarrierFrequencies()
    {
        if(mService == null)
        {
            Log.w("ConsumerIr", "no consumer ir service.");
            return null;
        }
        int ai[];
        ai = mService.getCarrierFrequencies();
        if(ai.length % 2 == 0)
            break MISSING_BLOCK_LABEL_44;
        Log.w("ConsumerIr", "consumer ir service returned an uneven number of frequencies.");
        return null;
        CarrierFrequencyRange acarrierfrequencyrange[];
        int i;
        try
        {
            acarrierfrequencyrange = new CarrierFrequencyRange[ai.length / 2];
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        i = 0;
        if(i >= ai.length)
            break; /* Loop/switch isn't completed */
        acarrierfrequencyrange[i / 2] = new CarrierFrequencyRange(ai[i], ai[i + 1]);
        i += 2;
        if(true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_54;
_L1:
        return acarrierfrequencyrange;
    }

    public boolean hasIrEmitter()
    {
        if(mService == null)
        {
            Log.w("ConsumerIr", "no consumer ir service.");
            return false;
        }
        boolean flag;
        try
        {
            flag = mService.hasIrEmitter();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public void transmit(int i, int ai[])
    {
        if(mService == null)
        {
            Log.w("ConsumerIr", "failed to transmit; no consumer ir service.");
            return;
        }
        try
        {
            mService.transmit(mPackageName, i, ai);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(int ai[])
        {
            throw ai.rethrowFromSystemServer();
        }
    }

    private static final String TAG = "ConsumerIr";
    private final String mPackageName;
    private final IConsumerIrService mService = IConsumerIrService.Stub.asInterface(ServiceManager.getServiceOrThrow("consumer_ir"));
}
