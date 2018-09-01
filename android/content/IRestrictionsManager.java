// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.os.*;

// Referenced classes of package android.content:
//            Intent

public interface IRestrictionsManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IRestrictionsManager
    {

        public static IRestrictionsManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.content.IRestrictionsManager");
            if(iinterface != null && (iinterface instanceof IRestrictionsManager))
                return (IRestrictionsManager)iinterface;
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
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.content.IRestrictionsManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.content.IRestrictionsManager");
                parcel = getApplicationRestrictions(parcel.readString());
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
                parcel.enforceInterface("android.content.IRestrictionsManager");
                boolean flag1 = hasRestrictionsProvider();
                parcel1.writeNoException();
                i = ((flag) ? 1 : 0);
                if(flag1)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.content.IRestrictionsManager");
                String s = parcel.readString();
                String s2 = parcel.readString();
                String s3 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (PersistableBundle)PersistableBundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                requestPermission(s, s2, s3, parcel);
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.content.IRestrictionsManager");
                String s1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (PersistableBundle)PersistableBundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                notifyPermissionResponse(s1, parcel);
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.content.IRestrictionsManager");
                parcel = createLocalApprovalIntent();
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

        private static final String DESCRIPTOR = "android.content.IRestrictionsManager";
        static final int TRANSACTION_createLocalApprovalIntent = 5;
        static final int TRANSACTION_getApplicationRestrictions = 1;
        static final int TRANSACTION_hasRestrictionsProvider = 2;
        static final int TRANSACTION_notifyPermissionResponse = 4;
        static final int TRANSACTION_requestPermission = 3;

        public Stub()
        {
            attachInterface(this, "android.content.IRestrictionsManager");
        }
    }

    private static class Stub.Proxy
        implements IRestrictionsManager
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public Intent createLocalApprovalIntent()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IRestrictionsManager");
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            intent = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Bundle getApplicationRestrictions(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IRestrictionsManager");
            parcel.writeString(s);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (Bundle)Bundle.CREATOR.createFromParcel(parcel1);
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

        public String getInterfaceDescriptor()
        {
            return "android.content.IRestrictionsManager";
        }

        public boolean hasRestrictionsProvider()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.content.IRestrictionsManager");
            mRemote.transact(2, parcel, parcel1, 0);
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void notifyPermissionResponse(String s, PersistableBundle persistablebundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IRestrictionsManager");
            parcel.writeString(s);
            if(persistablebundle == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            persistablebundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void requestPermission(String s, String s1, String s2, PersistableBundle persistablebundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IRestrictionsManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            if(persistablebundle == null)
                break MISSING_BLOCK_LABEL_86;
            parcel.writeInt(1);
            persistablebundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
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


    public abstract Intent createLocalApprovalIntent()
        throws RemoteException;

    public abstract Bundle getApplicationRestrictions(String s)
        throws RemoteException;

    public abstract boolean hasRestrictionsProvider()
        throws RemoteException;

    public abstract void notifyPermissionResponse(String s, PersistableBundle persistablebundle)
        throws RemoteException;

    public abstract void requestPermission(String s, String s1, String s2, PersistableBundle persistablebundle)
        throws RemoteException;
}
