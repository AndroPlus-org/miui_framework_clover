// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.content.Context;
import android.os.RemoteException;
import java.util.List;

// Referenced classes of package android.location:
//            LocalListenerHelper, ILocationManager, IBatchedLocationCallback, BatchedLocationCallback

class BatchedLocationCallbackTransport extends LocalListenerHelper
{
    private class CallbackTransport extends IBatchedLocationCallback.Stub
    {

        public void onLocationBatch(List list)
        {
            list = list. new LocalListenerHelper.ListenerOperation() {

                public void execute(BatchedLocationCallback batchedlocationcallback)
                    throws RemoteException
                {
                    batchedlocationcallback.onLocationBatch(locations);
                }

                public volatile void execute(Object obj)
                    throws RemoteException
                {
                    execute((BatchedLocationCallback)obj);
                }

                final CallbackTransport this$1;
                final List val$locations;

            
            {
                this$1 = final_callbacktransport;
                locations = List.this;
                super();
            }
            }
;
            foreach(list);
        }

        final BatchedLocationCallbackTransport this$0;

        private CallbackTransport()
        {
            this$0 = BatchedLocationCallbackTransport.this;
            super();
        }

        CallbackTransport(CallbackTransport callbacktransport)
        {
            this();
        }
    }


    public BatchedLocationCallbackTransport(Context context, ILocationManager ilocationmanager)
    {
        super(context, "BatchedLocationCallbackTransport");
        mLocationManager = ilocationmanager;
    }

    protected boolean registerWithServer()
        throws RemoteException
    {
        return mLocationManager.addGnssBatchingCallback(mCallbackTransport, getContext().getPackageName());
    }

    protected void unregisterFromServer()
        throws RemoteException
    {
        mLocationManager.removeGnssBatchingCallback();
    }

    private final IBatchedLocationCallback mCallbackTransport = new CallbackTransport(null);
    private final ILocationManager mLocationManager;
}
