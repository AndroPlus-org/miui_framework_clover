// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.os.*;

// Referenced classes of package android.app:
//            WallpaperColors

public interface IWallpaperManagerCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IWallpaperManagerCallback
    {

        public static IWallpaperManagerCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.IWallpaperManagerCallback");
            if(iinterface != null && (iinterface instanceof IWallpaperManagerCallback))
                return (IWallpaperManagerCallback)iinterface;
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
                parcel1.writeString("android.app.IWallpaperManagerCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.IWallpaperManagerCallback");
                onWallpaperChanged();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.app.IWallpaperManagerCallback");
                break;
            }
            if(parcel.readInt() != 0)
                parcel1 = (WallpaperColors)WallpaperColors.CREATOR.createFromParcel(parcel);
            else
                parcel1 = null;
            onWallpaperColorsChanged(parcel1, parcel.readInt(), parcel.readInt());
            return true;
        }

        private static final String DESCRIPTOR = "android.app.IWallpaperManagerCallback";
        static final int TRANSACTION_onWallpaperChanged = 1;
        static final int TRANSACTION_onWallpaperColorsChanged = 2;

        public Stub()
        {
            attachInterface(this, "android.app.IWallpaperManagerCallback");
        }
    }

    private static class Stub.Proxy
        implements IWallpaperManagerCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.app.IWallpaperManagerCallback";
        }

        public void onWallpaperChanged()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IWallpaperManagerCallback");
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onWallpaperColorsChanged(WallpaperColors wallpapercolors, int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IWallpaperManagerCallback");
            if(wallpapercolors == null)
                break MISSING_BLOCK_LABEL_62;
            parcel.writeInt(1);
            wallpapercolors.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            wallpapercolors;
            parcel.recycle();
            throw wallpapercolors;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onWallpaperChanged()
        throws RemoteException;

    public abstract void onWallpaperColorsChanged(WallpaperColors wallpapercolors, int i, int j)
        throws RemoteException;
}
