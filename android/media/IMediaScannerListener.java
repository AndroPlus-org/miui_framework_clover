// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.net.Uri;
import android.os.*;

public interface IMediaScannerListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMediaScannerListener
    {

        public static IMediaScannerListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.IMediaScannerListener");
            if(iinterface != null && (iinterface instanceof IMediaScannerListener))
                return (IMediaScannerListener)iinterface;
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
                parcel1.writeString("android.media.IMediaScannerListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.IMediaScannerListener");
                parcel1 = parcel.readString();
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (Uri)Uri.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            scanCompleted(parcel1, parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.media.IMediaScannerListener";
        static final int TRANSACTION_scanCompleted = 1;

        public Stub()
        {
            attachInterface(this, "android.media.IMediaScannerListener");
        }
    }

    private static class Stub.Proxy
        implements IMediaScannerListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.IMediaScannerListener";
        }

        public void scanCompleted(String s, Uri uri)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IMediaScannerListener");
            parcel.writeString(s);
            if(uri == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void scanCompleted(String s, Uri uri)
        throws RemoteException;
}
