// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.os.*;

public interface IOnPrimaryClipChangedListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IOnPrimaryClipChangedListener
    {

        public static IOnPrimaryClipChangedListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.content.IOnPrimaryClipChangedListener");
            if(iinterface != null && (iinterface instanceof IOnPrimaryClipChangedListener))
                return (IOnPrimaryClipChangedListener)iinterface;
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
                parcel1.writeString("android.content.IOnPrimaryClipChangedListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.content.IOnPrimaryClipChangedListener");
                dispatchPrimaryClipChanged();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.content.IOnPrimaryClipChangedListener";
        static final int TRANSACTION_dispatchPrimaryClipChanged = 1;

        public Stub()
        {
            attachInterface(this, "android.content.IOnPrimaryClipChangedListener");
        }
    }

    private static class Stub.Proxy
        implements IOnPrimaryClipChangedListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void dispatchPrimaryClipChanged()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IOnPrimaryClipChangedListener");
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.content.IOnPrimaryClipChangedListener";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void dispatchPrimaryClipChanged()
        throws RemoteException;
}
