// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.hdmi;

import android.os.*;

// Referenced classes of package android.hardware.hdmi:
//            HdmiHotplugEvent

public interface IHdmiHotplugEventListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IHdmiHotplugEventListener
    {

        public static IHdmiHotplugEventListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.hdmi.IHdmiHotplugEventListener");
            if(iinterface != null && (iinterface instanceof IHdmiHotplugEventListener))
                return (IHdmiHotplugEventListener)iinterface;
            else
                return new Proxy(ibinder);
        }

        public IBinder asBinder()
        {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            throws RemoteException
        {
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.hardware.hdmi.IHdmiHotplugEventListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiHotplugEventListener");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (HdmiHotplugEvent)HdmiHotplugEvent.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onReceived(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.hardware.hdmi.IHdmiHotplugEventListener";
        static final int TRANSACTION_onReceived = 1;

        public Stub()
        {
            attachInterface(this, "android.hardware.hdmi.IHdmiHotplugEventListener");
        }
    }

    private static class Stub.Proxy
        implements IHdmiHotplugEventListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.hardware.hdmi.IHdmiHotplugEventListener";
        }

        public void onReceived(HdmiHotplugEvent hdmihotplugevent)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiHotplugEventListener");
            if(hdmihotplugevent == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            hdmihotplugevent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            hdmihotplugevent;
            parcel.recycle();
            throw hdmihotplugevent;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onReceived(HdmiHotplugEvent hdmihotplugevent)
        throws RemoteException;
}
