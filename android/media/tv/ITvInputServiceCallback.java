// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.tv;

import android.os.*;

// Referenced classes of package android.media.tv:
//            TvInputInfo

public interface ITvInputServiceCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ITvInputServiceCallback
    {

        public static ITvInputServiceCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.tv.ITvInputServiceCallback");
            if(iinterface != null && (iinterface instanceof ITvInputServiceCallback))
                return (ITvInputServiceCallback)iinterface;
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
                parcel1.writeString("android.media.tv.ITvInputServiceCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.tv.ITvInputServiceCallback");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (TvInputInfo)TvInputInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                addHardwareInput(i, parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.media.tv.ITvInputServiceCallback");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (TvInputInfo)TvInputInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                addHdmiInput(i, parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.media.tv.ITvInputServiceCallback");
                removeHardwareInput(parcel.readString());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.media.tv.ITvInputServiceCallback";
        static final int TRANSACTION_addHardwareInput = 1;
        static final int TRANSACTION_addHdmiInput = 2;
        static final int TRANSACTION_removeHardwareInput = 3;

        public Stub()
        {
            attachInterface(this, "android.media.tv.ITvInputServiceCallback");
        }
    }

    private static class Stub.Proxy
        implements ITvInputServiceCallback
    {

        public void addHardwareInput(int i, TvInputInfo tvinputinfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputServiceCallback");
            parcel.writeInt(i);
            if(tvinputinfo == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            tvinputinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            tvinputinfo;
            parcel.recycle();
            throw tvinputinfo;
        }

        public void addHdmiInput(int i, TvInputInfo tvinputinfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputServiceCallback");
            parcel.writeInt(i);
            if(tvinputinfo == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            tvinputinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            tvinputinfo;
            parcel.recycle();
            throw tvinputinfo;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.tv.ITvInputServiceCallback";
        }

        public void removeHardwareInput(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputServiceCallback");
            parcel.writeString(s);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
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


    public abstract void addHardwareInput(int i, TvInputInfo tvinputinfo)
        throws RemoteException;

    public abstract void addHdmiInput(int i, TvInputInfo tvinputinfo)
        throws RemoteException;

    public abstract void removeHardwareInput(String s)
        throws RemoteException;
}
