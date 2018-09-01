// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.hdmi;

import android.os.*;

public interface IHdmiMhlVendorCommandListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IHdmiMhlVendorCommandListener
    {

        public static IHdmiMhlVendorCommandListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.hdmi.IHdmiMhlVendorCommandListener");
            if(iinterface != null && (iinterface instanceof IHdmiMhlVendorCommandListener))
                return (IHdmiMhlVendorCommandListener)iinterface;
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
                parcel1.writeString("android.hardware.hdmi.IHdmiMhlVendorCommandListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiMhlVendorCommandListener");
                onReceived(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.createByteArray());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.hardware.hdmi.IHdmiMhlVendorCommandListener";
        static final int TRANSACTION_onReceived = 1;

        public Stub()
        {
            attachInterface(this, "android.hardware.hdmi.IHdmiMhlVendorCommandListener");
        }
    }

    private static class Stub.Proxy
        implements IHdmiMhlVendorCommandListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.hardware.hdmi.IHdmiMhlVendorCommandListener";
        }

        public void onReceived(int i, int j, int k, byte abyte0[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiMhlVendorCommandListener");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeByteArray(abyte0);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            abyte0;
            parcel.recycle();
            throw abyte0;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onReceived(int i, int j, int k, byte abyte0[])
        throws RemoteException;
}
