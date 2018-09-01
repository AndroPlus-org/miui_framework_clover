// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.accounts.Account;
import android.os.*;

// Referenced classes of package android.content:
//            ISyncContext

public interface ISyncAdapter
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ISyncAdapter
    {

        public static ISyncAdapter asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.content.ISyncAdapter");
            if(iinterface != null && (iinterface instanceof ISyncAdapter))
                return (ISyncAdapter)iinterface;
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
                parcel1.writeString("android.content.ISyncAdapter");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.content.ISyncAdapter");
                ISyncContext isynccontext = ISyncContext.Stub.asInterface(parcel.readStrongBinder());
                String s = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel1 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                startSync(isynccontext, s, parcel1, parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.content.ISyncAdapter");
                cancelSync(ISyncContext.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.content.ISyncAdapter";
        static final int TRANSACTION_cancelSync = 2;
        static final int TRANSACTION_startSync = 1;

        public Stub()
        {
            attachInterface(this, "android.content.ISyncAdapter");
        }
    }

    private static class Stub.Proxy
        implements ISyncAdapter
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
            parcel.writeInterfaceToken("android.content.ISyncAdapter");
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
            return "android.content.ISyncAdapter";
        }

        public void startSync(ISyncContext isynccontext, String s, Account account, Bundle bundle)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.ISyncAdapter");
            if(isynccontext == null)
                break MISSING_BLOCK_LABEL_27;
            ibinder = isynccontext.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            if(account == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L3:
            if(bundle == null)
                break MISSING_BLOCK_LABEL_114;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            isynccontext;
            parcel.recycle();
            throw isynccontext;
            parcel.writeInt(0);
              goto _L4
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void cancelSync(ISyncContext isynccontext)
        throws RemoteException;

    public abstract void startSync(ISyncContext isynccontext, String s, Account account, Bundle bundle)
        throws RemoteException;
}
