// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.os.*;

// Referenced classes of package android.view:
//            IGraphicsStatsCallback

public interface IGraphicsStats
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IGraphicsStats
    {

        public static IGraphicsStats asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.view.IGraphicsStats");
            if(iinterface != null && (iinterface instanceof IGraphicsStats))
                return (IGraphicsStats)iinterface;
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
                parcel1.writeString("android.view.IGraphicsStats");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.view.IGraphicsStats");
                parcel = requestBufferForProcess(parcel.readString(), IGraphicsStatsCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                break;
            }
            if(parcel != null)
            {
                parcel1.writeInt(1);
                parcel.writeToParcel(parcel1, 1);
            } else
            {
                parcel1.writeInt(0);
            }
            return true;
        }

        private static final String DESCRIPTOR = "android.view.IGraphicsStats";
        static final int TRANSACTION_requestBufferForProcess = 1;

        public Stub()
        {
            attachInterface(this, "android.view.IGraphicsStats");
        }
    }

    private static class Stub.Proxy
        implements IGraphicsStats
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.view.IGraphicsStats";
        }

        public ParcelFileDescriptor requestBufferForProcess(String s, IGraphicsStatsCallback igraphicsstatscallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IGraphicsStats");
            parcel.writeString(s);
            s = obj;
            if(igraphicsstatscallback == null)
                break MISSING_BLOCK_LABEL_38;
            s = igraphicsstatscallback.asBinder();
            parcel.writeStrongBinder(s);
            mRemote.transact(1, parcel, parcel1, 0);
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


    public abstract ParcelFileDescriptor requestBufferForProcess(String s, IGraphicsStatsCallback igraphicsstatscallback)
        throws RemoteException;
}
