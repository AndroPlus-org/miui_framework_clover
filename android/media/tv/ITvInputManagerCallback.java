// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.tv;

import android.os.*;

// Referenced classes of package android.media.tv:
//            TvInputInfo

public interface ITvInputManagerCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ITvInputManagerCallback
    {

        public static ITvInputManagerCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.tv.ITvInputManagerCallback");
            if(iinterface != null && (iinterface instanceof ITvInputManagerCallback))
                return (ITvInputManagerCallback)iinterface;
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
                parcel1.writeString("android.media.tv.ITvInputManagerCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.tv.ITvInputManagerCallback");
                onInputAdded(parcel.readString());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.media.tv.ITvInputManagerCallback");
                onInputRemoved(parcel.readString());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.media.tv.ITvInputManagerCallback");
                onInputUpdated(parcel.readString());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.media.tv.ITvInputManagerCallback");
                onInputStateChanged(parcel.readString(), parcel.readInt());
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.media.tv.ITvInputManagerCallback");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (TvInputInfo)TvInputInfo.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onTvInputInfoUpdated(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.media.tv.ITvInputManagerCallback";
        static final int TRANSACTION_onInputAdded = 1;
        static final int TRANSACTION_onInputRemoved = 2;
        static final int TRANSACTION_onInputStateChanged = 4;
        static final int TRANSACTION_onInputUpdated = 3;
        static final int TRANSACTION_onTvInputInfoUpdated = 5;

        public Stub()
        {
            attachInterface(this, "android.media.tv.ITvInputManagerCallback");
        }
    }

    private static class Stub.Proxy
        implements ITvInputManagerCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.tv.ITvInputManagerCallback";
        }

        public void onInputAdded(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManagerCallback");
            parcel.writeString(s);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onInputRemoved(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManagerCallback");
            parcel.writeString(s);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onInputStateChanged(String s, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManagerCallback");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onInputUpdated(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManagerCallback");
            parcel.writeString(s);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onTvInputInfoUpdated(TvInputInfo tvinputinfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManagerCallback");
            if(tvinputinfo == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            tvinputinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            tvinputinfo;
            parcel.recycle();
            throw tvinputinfo;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onInputAdded(String s)
        throws RemoteException;

    public abstract void onInputRemoved(String s)
        throws RemoteException;

    public abstract void onInputStateChanged(String s, int i)
        throws RemoteException;

    public abstract void onInputUpdated(String s)
        throws RemoteException;

    public abstract void onTvInputInfoUpdated(TvInputInfo tvinputinfo)
        throws RemoteException;
}
