// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.os.*;

public interface IActivityPendingResult
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IActivityPendingResult
    {

        public static IActivityPendingResult asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.IActivityPendingResult");
            if(iinterface != null && (iinterface instanceof IActivityPendingResult))
                return (IActivityPendingResult)iinterface;
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
            boolean flag = false;
            String s;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.app.IActivityPendingResult");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.IActivityPendingResult");
                i = parcel.readInt();
                s = parcel.readString();
                break;
            }
            boolean flag1;
            if(parcel.readInt() != 0)
                parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            flag1 = sendResult(i, s, parcel);
            parcel1.writeNoException();
            i = ((flag) ? 1 : 0);
            if(flag1)
                i = 1;
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "android.app.IActivityPendingResult";
        static final int TRANSACTION_sendResult = 1;

        public Stub()
        {
            attachInterface(this, "android.app.IActivityPendingResult");
        }
    }

    private static class Stub.Proxy
        implements IActivityPendingResult
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.app.IActivityPendingResult";
        }

        public boolean sendResult(int i, String s, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IActivityPendingResult");
            parcel.writeInt(i);
            parcel.writeString(s);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_93;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
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


    public abstract boolean sendResult(int i, String s, Bundle bundle)
        throws RemoteException;
}
