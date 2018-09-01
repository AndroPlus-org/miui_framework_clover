// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.carrier;

import android.app.Service;
import android.content.Intent;
import android.os.*;
import com.android.internal.telephony.ITelephonyRegistry;

// Referenced classes of package android.service.carrier:
//            CarrierIdentifier

public abstract class CarrierService extends Service
{
    private class ICarrierServiceWrapper extends ICarrierService.Stub
    {

        public PersistableBundle getCarrierConfig(CarrierIdentifier carrieridentifier)
        {
            return onLoadConfig(carrieridentifier);
        }

        final CarrierService this$0;

        private ICarrierServiceWrapper()
        {
            this$0 = CarrierService.this;
            super();
        }

        ICarrierServiceWrapper(ICarrierServiceWrapper icarrierservicewrapper)
        {
            this();
        }
    }


    public CarrierService()
    {
        if(sRegistry == null)
            sRegistry = com.android.internal.telephony.ITelephonyRegistry.Stub.asInterface(ServiceManager.getService("telephony.registry"));
    }

    public final void notifyCarrierNetworkChange(boolean flag)
    {
        if(sRegistry != null)
            sRegistry.notifyCarrierNetworkChange(flag);
_L2:
        return;
        Object obj;
        obj;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public IBinder onBind(Intent intent)
    {
        return mStubWrapper;
    }

    public abstract PersistableBundle onLoadConfig(CarrierIdentifier carrieridentifier);

    public static final String CARRIER_SERVICE_INTERFACE = "android.service.carrier.CarrierService";
    private static ITelephonyRegistry sRegistry;
    private final ICarrierService.Stub mStubWrapper = new ICarrierServiceWrapper(null);
}
