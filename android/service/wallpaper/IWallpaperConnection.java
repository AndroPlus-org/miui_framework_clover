// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.wallpaper;

import android.app.WallpaperColors;
import android.os.*;

// Referenced classes of package android.service.wallpaper:
//            IWallpaperEngine

public interface IWallpaperConnection
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IWallpaperConnection
    {

        public static IWallpaperConnection asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.wallpaper.IWallpaperConnection");
            if(iinterface != null && (iinterface instanceof IWallpaperConnection))
                return (IWallpaperConnection)iinterface;
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
                parcel1.writeString("android.service.wallpaper.IWallpaperConnection");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.wallpaper.IWallpaperConnection");
                attachEngine(IWallpaperEngine.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.service.wallpaper.IWallpaperConnection");
                engineShown(IWallpaperEngine.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.service.wallpaper.IWallpaperConnection");
                parcel = setWallpaper(parcel.readString());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.service.wallpaper.IWallpaperConnection");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (WallpaperColors)WallpaperColors.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onWallpaperColorsChanged(parcel);
            parcel1.writeNoException();
            return true;
        }

        private static final String DESCRIPTOR = "android.service.wallpaper.IWallpaperConnection";
        static final int TRANSACTION_attachEngine = 1;
        static final int TRANSACTION_engineShown = 2;
        static final int TRANSACTION_onWallpaperColorsChanged = 4;
        static final int TRANSACTION_setWallpaper = 3;

        public Stub()
        {
            attachInterface(this, "android.service.wallpaper.IWallpaperConnection");
        }
    }

    private static class Stub.Proxy
        implements IWallpaperConnection
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void attachEngine(IWallpaperEngine iwallpaperengine)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.wallpaper.IWallpaperConnection");
            if(iwallpaperengine == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iwallpaperengine.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iwallpaperengine;
            parcel1.recycle();
            parcel.recycle();
            throw iwallpaperengine;
        }

        public void engineShown(IWallpaperEngine iwallpaperengine)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.wallpaper.IWallpaperConnection");
            if(iwallpaperengine == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iwallpaperengine.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iwallpaperengine;
            parcel1.recycle();
            parcel.recycle();
            throw iwallpaperengine;
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.wallpaper.IWallpaperConnection";
        }

        public void onWallpaperColorsChanged(WallpaperColors wallpapercolors)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.wallpaper.IWallpaperConnection");
            if(wallpapercolors == null)
                break MISSING_BLOCK_LABEL_56;
            parcel.writeInt(1);
            wallpapercolors.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            wallpapercolors;
            parcel1.recycle();
            parcel.recycle();
            throw wallpapercolors;
        }

        public ParcelFileDescriptor setWallpaper(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.wallpaper.IWallpaperConnection");
            parcel.writeString(s);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
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


    public abstract void attachEngine(IWallpaperEngine iwallpaperengine)
        throws RemoteException;

    public abstract void engineShown(IWallpaperEngine iwallpaperengine)
        throws RemoteException;

    public abstract void onWallpaperColorsChanged(WallpaperColors wallpapercolors)
        throws RemoteException;

    public abstract ParcelFileDescriptor setWallpaper(String s)
        throws RemoteException;
}
