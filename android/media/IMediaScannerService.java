// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.*;

// Referenced classes of package android.media:
//            IMediaScannerListener

public interface IMediaScannerService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMediaScannerService
    {

        public static IMediaScannerService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.IMediaScannerService");
            if(iinterface != null && (iinterface instanceof IMediaScannerService))
                return (IMediaScannerService)iinterface;
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
                parcel1.writeString("android.media.IMediaScannerService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.IMediaScannerService");
                requestScanFile(parcel.readString(), parcel.readString(), IMediaScannerListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.media.IMediaScannerService");
                scanFile(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.media.IMediaScannerService";
        static final int TRANSACTION_requestScanFile = 1;
        static final int TRANSACTION_scanFile = 2;

        public Stub()
        {
            attachInterface(this, "android.media.IMediaScannerService");
        }
    }

    private static class Stub.Proxy
        implements IMediaScannerService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.IMediaScannerService";
        }

        public void requestScanFile(String s, String s1, IMediaScannerListener imediascannerlistener)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IMediaScannerService");
            parcel.writeString(s);
            parcel.writeString(s1);
            s = obj;
            if(imediascannerlistener == null)
                break MISSING_BLOCK_LABEL_46;
            s = imediascannerlistener.asBinder();
            parcel.writeStrongBinder(s);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void scanFile(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IMediaScannerService");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void requestScanFile(String s, String s1, IMediaScannerListener imediascannerlistener)
        throws RemoteException;

    public abstract void scanFile(String s, String s1)
        throws RemoteException;
}
