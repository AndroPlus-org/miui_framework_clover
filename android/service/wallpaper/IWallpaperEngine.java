// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.wallpaper;

import android.graphics.Rect;
import android.os.*;
import android.view.MotionEvent;

public interface IWallpaperEngine
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IWallpaperEngine
    {

        public static IWallpaperEngine asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.wallpaper.IWallpaperEngine");
            if(iinterface != null && (iinterface instanceof IWallpaperEngine))
                return (IWallpaperEngine)iinterface;
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
                parcel1.writeString("android.service.wallpaper.IWallpaperEngine");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.wallpaper.IWallpaperEngine");
                setDesiredSize(parcel.readInt(), parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.service.wallpaper.IWallpaperEngine");
                if(parcel.readInt() != 0)
                    parcel = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setDisplayPadding(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.service.wallpaper.IWallpaperEngine");
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                setVisibility(flag);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.service.wallpaper.IWallpaperEngine");
                if(parcel.readInt() != 0)
                    parcel = (MotionEvent)MotionEvent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                dispatchPointer(parcel);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.service.wallpaper.IWallpaperEngine");
                parcel1 = parcel.readString();
                int k = parcel.readInt();
                i = parcel.readInt();
                j = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                dispatchWallpaperCommand(parcel1, k, i, j, parcel);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.service.wallpaper.IWallpaperEngine");
                requestWallpaperColors();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.service.wallpaper.IWallpaperEngine");
                destroy();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.service.wallpaper.IWallpaperEngine";
        static final int TRANSACTION_destroy = 7;
        static final int TRANSACTION_dispatchPointer = 4;
        static final int TRANSACTION_dispatchWallpaperCommand = 5;
        static final int TRANSACTION_requestWallpaperColors = 6;
        static final int TRANSACTION_setDesiredSize = 1;
        static final int TRANSACTION_setDisplayPadding = 2;
        static final int TRANSACTION_setVisibility = 3;

        public Stub()
        {
            attachInterface(this, "android.service.wallpaper.IWallpaperEngine");
        }
    }

    private static class Stub.Proxy
        implements IWallpaperEngine
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void destroy()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.wallpaper.IWallpaperEngine");
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void dispatchPointer(MotionEvent motionevent)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.wallpaper.IWallpaperEngine");
            if(motionevent == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            motionevent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            motionevent;
            parcel.recycle();
            throw motionevent;
        }

        public void dispatchWallpaperCommand(String s, int i, int j, int k, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.wallpaper.IWallpaperEngine");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_77;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.wallpaper.IWallpaperEngine";
        }

        public void requestWallpaperColors()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.wallpaper.IWallpaperEngine");
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setDesiredSize(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.wallpaper.IWallpaperEngine");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setDisplayPadding(Rect rect)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.wallpaper.IWallpaperEngine");
            if(rect == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            rect.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            rect;
            parcel.recycle();
            throw rect;
        }

        public void setVisibility(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.wallpaper.IWallpaperEngine");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(3, parcel, null, 1);
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


    public abstract void destroy()
        throws RemoteException;

    public abstract void dispatchPointer(MotionEvent motionevent)
        throws RemoteException;

    public abstract void dispatchWallpaperCommand(String s, int i, int j, int k, Bundle bundle)
        throws RemoteException;

    public abstract void requestWallpaperColors()
        throws RemoteException;

    public abstract void setDesiredSize(int i, int j)
        throws RemoteException;

    public abstract void setDisplayPadding(Rect rect)
        throws RemoteException;

    public abstract void setVisibility(boolean flag)
        throws RemoteException;
}
