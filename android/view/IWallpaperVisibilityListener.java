// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.os.*;

public interface IWallpaperVisibilityListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IWallpaperVisibilityListener
    {

        public static IWallpaperVisibilityListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.view.IWallpaperVisibilityListener");
            if(iinterface != null && (iinterface instanceof IWallpaperVisibilityListener))
                return (IWallpaperVisibilityListener)iinterface;
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
                parcel1.writeString("android.view.IWallpaperVisibilityListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.view.IWallpaperVisibilityListener");
                break;
            }
            boolean flag;
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            onWallpaperVisibilityChanged(flag, parcel.readInt());
            return true;
        }

        private static final String DESCRIPTOR = "android.view.IWallpaperVisibilityListener";
        static final int TRANSACTION_onWallpaperVisibilityChanged = 1;

        public Stub()
        {
            attachInterface(this, "android.view.IWallpaperVisibilityListener");
        }
    }

    private static class Stub.Proxy
        implements IWallpaperVisibilityListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.view.IWallpaperVisibilityListener";
        }

        public void onWallpaperVisibilityChanged(boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            j = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IWallpaperVisibilityListener");
            if(!flag)
                j = 0;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onWallpaperVisibilityChanged(boolean flag, int i)
        throws RemoteException;
}
