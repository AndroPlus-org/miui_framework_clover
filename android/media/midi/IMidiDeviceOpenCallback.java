// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.midi;

import android.os.*;

// Referenced classes of package android.media.midi:
//            IMidiDeviceServer

public interface IMidiDeviceOpenCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMidiDeviceOpenCallback
    {

        public static IMidiDeviceOpenCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.midi.IMidiDeviceOpenCallback");
            if(iinterface != null && (iinterface instanceof IMidiDeviceOpenCallback))
                return (IMidiDeviceOpenCallback)iinterface;
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
                parcel1.writeString("android.media.midi.IMidiDeviceOpenCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.midi.IMidiDeviceOpenCallback");
                onDeviceOpened(IMidiDeviceServer.Stub.asInterface(parcel.readStrongBinder()), parcel.readStrongBinder());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.media.midi.IMidiDeviceOpenCallback";
        static final int TRANSACTION_onDeviceOpened = 1;

        public Stub()
        {
            attachInterface(this, "android.media.midi.IMidiDeviceOpenCallback");
        }
    }

    private static class Stub.Proxy
        implements IMidiDeviceOpenCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.midi.IMidiDeviceOpenCallback";
        }

        public void onDeviceOpened(IMidiDeviceServer imidideviceserver, IBinder ibinder)
            throws RemoteException
        {
            IBinder ibinder1;
            Parcel parcel;
            ibinder1 = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.midi.IMidiDeviceOpenCallback");
            if(imidideviceserver == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder1 = imidideviceserver.asBinder();
            parcel.writeStrongBinder(ibinder1);
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            imidideviceserver;
            parcel.recycle();
            throw imidideviceserver;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onDeviceOpened(IMidiDeviceServer imidideviceserver, IBinder ibinder)
        throws RemoteException;
}
