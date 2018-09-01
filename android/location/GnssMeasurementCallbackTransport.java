// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.content.Context;
import android.os.RemoteException;

// Referenced classes of package android.location:
//            LocalListenerHelper, ILocationManager, IGnssMeasurementsListener, GnssMeasurementsEvent

class GnssMeasurementCallbackTransport extends LocalListenerHelper
{
    private class ListenerTransport extends IGnssMeasurementsListener.Stub
    {

        public void onGnssMeasurementsReceived(GnssMeasurementsEvent gnssmeasurementsevent)
        {
            gnssmeasurementsevent = gnssmeasurementsevent. new LocalListenerHelper.ListenerOperation() {

                public void execute(GnssMeasurementsEvent.Callback callback)
                    throws RemoteException
                {
                    callback.onGnssMeasurementsReceived(event);
                }

                public volatile void execute(Object obj)
                    throws RemoteException
                {
                    execute((GnssMeasurementsEvent.Callback)obj);
                }

                final ListenerTransport this$1;
                final GnssMeasurementsEvent val$event;

            
            {
                this$1 = final_listenertransport;
                event = GnssMeasurementsEvent.this;
                super();
            }
            }
;
            foreach(gnssmeasurementsevent);
        }

        public void onStatusChanged(int i)
        {
            LocalListenerHelper.ListenerOperation listeneroperation = i. new LocalListenerHelper.ListenerOperation() {

                public void execute(GnssMeasurementsEvent.Callback callback)
                    throws RemoteException
                {
                    callback.onStatusChanged(status);
                }

                public volatile void execute(Object obj)
                    throws RemoteException
                {
                    execute((GnssMeasurementsEvent.Callback)obj);
                }

                final ListenerTransport this$1;
                final int val$status;

            
            {
                this$1 = final_listenertransport;
                status = I.this;
                super();
            }
            }
;
            foreach(listeneroperation);
        }

        final GnssMeasurementCallbackTransport this$0;

        private ListenerTransport()
        {
            this$0 = GnssMeasurementCallbackTransport.this;
            super();
        }

        ListenerTransport(ListenerTransport listenertransport)
        {
            this();
        }
    }


    public GnssMeasurementCallbackTransport(Context context, ILocationManager ilocationmanager)
    {
        super(context, "GnssMeasurementListenerTransport");
        mLocationManager = ilocationmanager;
    }

    protected boolean registerWithServer()
        throws RemoteException
    {
        return mLocationManager.addGnssMeasurementsListener(mListenerTransport, getContext().getPackageName());
    }

    protected void unregisterFromServer()
        throws RemoteException
    {
        mLocationManager.removeGnssMeasurementsListener(mListenerTransport);
    }

    private final IGnssMeasurementsListener mListenerTransport = new ListenerTransport(null);
    private final ILocationManager mLocationManager;
}
