// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.timezone;

import android.os.*;

// Referenced classes of package android.app.timezone:
//            RulesState, ICallback

public interface IRulesManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IRulesManager
    {

        public static IRulesManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.timezone.IRulesManager");
            if(iinterface != null && (iinterface instanceof IRulesManager))
                return (IRulesManager)iinterface;
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
            byte abyte0[];
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.app.timezone.IRulesManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.timezone.IRulesManager");
                parcel = getRulesState();
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

            case 2: // '\002'
                parcel.enforceInterface("android.app.timezone.IRulesManager");
                ParcelFileDescriptor parcelfiledescriptor;
                if(parcel.readInt() != 0)
                    parcelfiledescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcelfiledescriptor = null;
                i = requestInstall(parcelfiledescriptor, parcel.createByteArray(), ICallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.app.timezone.IRulesManager");
                i = requestUninstall(parcel.createByteArray(), ICallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.app.timezone.IRulesManager");
                abyte0 = parcel.createByteArray();
                break;
            }
            boolean flag;
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            requestNothing(abyte0, flag);
            parcel1.writeNoException();
            return true;
        }

        private static final String DESCRIPTOR = "android.app.timezone.IRulesManager";
        static final int TRANSACTION_getRulesState = 1;
        static final int TRANSACTION_requestInstall = 2;
        static final int TRANSACTION_requestNothing = 4;
        static final int TRANSACTION_requestUninstall = 3;

        public Stub()
        {
            attachInterface(this, "android.app.timezone.IRulesManager");
        }
    }

    private static class Stub.Proxy
        implements IRulesManager
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.app.timezone.IRulesManager";
        }

        public RulesState getRulesState()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.timezone.IRulesManager");
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            RulesState rulesstate = (RulesState)RulesState.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return rulesstate;
_L2:
            rulesstate = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int requestInstall(ParcelFileDescriptor parcelfiledescriptor, byte abyte0[], ICallback icallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.timezone.IRulesManager");
            if(parcelfiledescriptor == null)
                break MISSING_BLOCK_LABEL_104;
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L1:
            parcel.writeByteArray(abyte0);
            parcelfiledescriptor = obj;
            if(icallback == null)
                break MISSING_BLOCK_LABEL_57;
            parcelfiledescriptor = icallback.asBinder();
            int i;
            parcel.writeStrongBinder(parcelfiledescriptor);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            parcelfiledescriptor;
            parcel1.recycle();
            parcel.recycle();
            throw parcelfiledescriptor;
        }

        public void requestNothing(byte abyte0[], boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.timezone.IRulesManager");
            parcel.writeByteArray(abyte0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        public int requestUninstall(byte abyte0[], ICallback icallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.timezone.IRulesManager");
            parcel.writeByteArray(abyte0);
            abyte0 = obj;
            if(icallback == null)
                break MISSING_BLOCK_LABEL_38;
            abyte0 = icallback.asBinder();
            int i;
            parcel.writeStrongBinder(abyte0);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract RulesState getRulesState()
        throws RemoteException;

    public abstract int requestInstall(ParcelFileDescriptor parcelfiledescriptor, byte abyte0[], ICallback icallback)
        throws RemoteException;

    public abstract void requestNothing(byte abyte0[], boolean flag)
        throws RemoteException;

    public abstract int requestUninstall(byte abyte0[], ICallback icallback)
        throws RemoteException;
}
