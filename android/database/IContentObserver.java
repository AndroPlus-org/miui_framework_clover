// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database;

import android.net.Uri;
import android.os.*;

public interface IContentObserver
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IContentObserver
    {

        public static IContentObserver asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.database.IContentObserver");
            if(iinterface != null && (iinterface instanceof IContentObserver))
                return (IContentObserver)iinterface;
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
                parcel1.writeString("android.database.IContentObserver");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.database.IContentObserver");
                break;
            }
            boolean flag;
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            if(parcel.readInt() != 0)
                parcel1 = (Uri)Uri.CREATOR.createFromParcel(parcel);
            else
                parcel1 = null;
            onChange(flag, parcel1, parcel.readInt());
            return true;
        }

        private static final String DESCRIPTOR = "android.database.IContentObserver";
        static final int TRANSACTION_onChange = 1;

        public Stub()
        {
            attachInterface(this, "android.database.IContentObserver");
        }
    }

    private static class Stub.Proxy
        implements IContentObserver
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.database.IContentObserver";
        }

        public void onChange(boolean flag, Uri uri, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            j = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.database.IContentObserver");
            if(!flag)
                j = 0;
            parcel.writeInt(j);
            if(uri == null)
                break MISSING_BLOCK_LABEL_76;
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            uri;
            parcel.recycle();
            throw uri;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onChange(boolean flag, Uri uri, int i)
        throws RemoteException;
}
