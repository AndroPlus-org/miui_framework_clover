// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.os.*;

public interface IDexModuleRegisterCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IDexModuleRegisterCallback
    {

        public static IDexModuleRegisterCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.content.pm.IDexModuleRegisterCallback");
            if(iinterface != null && (iinterface instanceof IDexModuleRegisterCallback))
                return (IDexModuleRegisterCallback)iinterface;
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
                parcel1.writeString("android.content.pm.IDexModuleRegisterCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.content.pm.IDexModuleRegisterCallback");
                parcel1 = parcel.readString();
                break;
            }
            boolean flag;
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            onDexModuleRegistered(parcel1, flag, parcel.readString());
            return true;
        }

        private static final String DESCRIPTOR = "android.content.pm.IDexModuleRegisterCallback";
        static final int TRANSACTION_onDexModuleRegistered = 1;

        public Stub()
        {
            attachInterface(this, "android.content.pm.IDexModuleRegisterCallback");
        }
    }

    private static class Stub.Proxy
        implements IDexModuleRegisterCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.content.pm.IDexModuleRegisterCallback";
        }

        public void onDexModuleRegistered(String s, boolean flag, String s1)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IDexModuleRegisterCallback");
            parcel.writeString(s);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            parcel.writeString(s1);
            mRemote.transact(1, parcel, null, 1);
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


    public abstract void onDexModuleRegistered(String s, boolean flag, String s1)
        throws RemoteException;
}
