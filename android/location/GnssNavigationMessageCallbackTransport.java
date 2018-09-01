// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.content.Context;
import android.os.RemoteException;

// Referenced classes of package android.location:
//            LocalListenerHelper, ILocationManager, IGnssNavigationMessageListener, GnssNavigationMessage

class GnssNavigationMessageCallbackTransport extends LocalListenerHelper
{
    private class ListenerTransport extends IGnssNavigationMessageListener.Stub
    {

        public void onGnssNavigationMessageReceived(GnssNavigationMessage gnssnavigationmessage)
        {
            gnssnavigationmessage = gnssnavigationmessage. new LocalListenerHelper.ListenerOperation() {

                public void execute(GnssNavigationMessage.Callback callback)
                    throws RemoteException
                {
                    callback.onGnssNavigationMessageReceived(event);
                }

                public volatile void execute(Object obj)
                    throws RemoteException
                {
                    execute((GnssNavigationMessage.Callback)obj);
                }

                final ListenerTransport this$1;
                final GnssNavigationMessage val$event;

            
            {
                this$1 = final_listenertransport;
                event = GnssNavigationMessage.this;
                super();
            }
            }
;
            foreach(gnssnavigationmessage);
        }

        public void onStatusChanged(int i)
        {
            LocalListenerHelper.ListenerOperation listeneroperation = i. new LocalListenerHelper.ListenerOperation() {

                public void execute(GnssNavigationMessage.Callback callback)
                    throws RemoteException
                {
                    callback.onStatusChanged(status);
                }

                public volatile void execute(Object obj)
                    throws RemoteException
                {
                    execute((GnssNavigationMessage.Callback)obj);
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

        final GnssNavigationMessageCallbackTransport this$0;

        private ListenerTransport()
        {
            this$0 = GnssNavigationMessageCallbackTransport.this;
            super();
        }

        ListenerTransport(ListenerTransport listenertransport)
        {
            this();
        }
    }


    public GnssNavigationMessageCallbackTransport(Context context, ILocationManager ilocationmanager)
    {
        super(context, "GnssNavigationMessageCallbackTransport");
        mLocationManager = ilocationmanager;
    }

    protected boolean registerWithServer()
        throws RemoteException
    {
        return mLocationManager.addGnssNavigationMessageListener(mListenerTransport, getContext().getPackageName());
    }

    protected void unregisterFromServer()
        throws RemoteException
    {
        mLocationManager.removeGnssNavigationMessageListener(mListenerTransport);
    }

    private final IGnssNavigationMessageListener mListenerTransport = new ListenerTransport(null);
    private final ILocationManager mLocationManager;
}
