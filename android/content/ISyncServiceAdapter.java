// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.os.*;

// Referenced classes of package android.content:
//            ISyncContext

public interface ISyncServiceAdapter
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ISyncServiceAdapter
    {

        public static ISyncServiceAdapter asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.content.ISyncServiceAdapter");
            if(iinterface != null && (iinterface instanceof ISyncServiceAdapter))
                return (ISyncServiceAdapter)iinterface;
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
                parcel1.writeString("android.content.ISyncServiceAdapter");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.content.ISyncServiceAdapter");
                parcel1 = ISyncContext.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                startSync(parcel1, parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.content.ISyncServiceAdapter");
                cancelSync(ISyncContext.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.content.ISyncServiceAdapter";
        static final int TRANSACTION_cancelSync = 2;
        static final int TRANSACTION_startSync = 1;

        public Stub()
        {
            attachInterface(this, "android.content.ISyncServiceAdapter");
        }
    }

    private static class Stub.Proxy
        implements ISyncServiceAdapter
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void cancelSync(ISyncContext isynccontext)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.ISyncServiceAdapter");
            if(isynccontext == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = isynccontext.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            isynccontext;
            parcel.recycle();
            throw isynccontext;
        }

        public String getInterfaceDescriptor()
        {
            return "android.content.ISyncServiceAdapter";
        }

        public void startSync(ISyncContext isynccontext, Bundle bundle)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.ISyncServiceAdapter");
            if(isynccontext == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = isynccontext.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_69;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            isynccontext;
            parcel.recycle();
            throw isynccontext;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void cancelSync(ISyncContext isynccontext)
        throws RemoteException;

    public abstract void startSync(ISyncContext isynccontext, Bundle bundle)
        throws RemoteException;
}
