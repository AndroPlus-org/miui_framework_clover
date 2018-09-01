// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.wallpaper;

import android.graphics.Rect;
import android.os.*;

// Referenced classes of package android.service.wallpaper:
//            IWallpaperConnection

public interface IWallpaperService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IWallpaperService
    {

        public static IWallpaperService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.wallpaper.IWallpaperService");
            if(iinterface != null && (iinterface instanceof IWallpaperService))
                return (IWallpaperService)iinterface;
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
            IBinder ibinder;
            int k;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.service.wallpaper.IWallpaperService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.wallpaper.IWallpaperService");
                parcel1 = IWallpaperConnection.Stub.asInterface(parcel.readStrongBinder());
                ibinder = parcel.readStrongBinder();
                k = parcel.readInt();
                break;
            }
            boolean flag;
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            j = parcel.readInt();
            i = parcel.readInt();
            if(parcel.readInt() != 0)
                parcel = (Rect)Rect.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            attach(parcel1, ibinder, k, flag, j, i, parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.service.wallpaper.IWallpaperService";
        static final int TRANSACTION_attach = 1;

        public Stub()
        {
            attachInterface(this, "android.service.wallpaper.IWallpaperService");
        }
    }

    private static class Stub.Proxy
        implements IWallpaperService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void attach(IWallpaperConnection iwallpaperconnection, IBinder ibinder, int i, boolean flag, int j, int k, Rect rect)
            throws RemoteException
        {
            IBinder ibinder1;
            boolean flag1;
            Parcel parcel;
            ibinder1 = null;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.wallpaper.IWallpaperService");
            if(iwallpaperconnection == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder1 = iwallpaperconnection.asBinder();
            parcel.writeStrongBinder(ibinder1);
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            if(rect == null)
                break MISSING_BLOCK_LABEL_122;
            parcel.writeInt(1);
            rect.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iwallpaperconnection;
            parcel.recycle();
            throw iwallpaperconnection;
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.wallpaper.IWallpaperService";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void attach(IWallpaperConnection iwallpaperconnection, IBinder ibinder, int i, boolean flag, int j, int k, Rect rect)
        throws RemoteException;
}
