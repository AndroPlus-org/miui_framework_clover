// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.os.*;

public interface IDockedStackListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IDockedStackListener
    {

        public static IDockedStackListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.view.IDockedStackListener");
            if(iinterface != null && (iinterface instanceof IDockedStackListener))
                return (IDockedStackListener)iinterface;
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
                parcel1.writeString("android.view.IDockedStackListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.view.IDockedStackListener");
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                onDividerVisibilityChanged(flag);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.view.IDockedStackListener");
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                onDockedStackExistsChanged(flag1);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.view.IDockedStackListener");
                boolean flag2;
                long l;
                boolean flag4;
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                l = parcel.readLong();
                if(parcel.readInt() != 0)
                    flag4 = true;
                else
                    flag4 = false;
                onDockedStackMinimizedChanged(flag2, l, flag4);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.view.IDockedStackListener");
                boolean flag3;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                onAdjustedForImeChanged(flag3, parcel.readLong());
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.view.IDockedStackListener");
                onDockSideChanged(parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.view.IDockedStackListener";
        static final int TRANSACTION_onAdjustedForImeChanged = 4;
        static final int TRANSACTION_onDividerVisibilityChanged = 1;
        static final int TRANSACTION_onDockSideChanged = 5;
        static final int TRANSACTION_onDockedStackExistsChanged = 2;
        static final int TRANSACTION_onDockedStackMinimizedChanged = 3;

        public Stub()
        {
            attachInterface(this, "android.view.IDockedStackListener");
        }
    }

    private static class Stub.Proxy
        implements IDockedStackListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.view.IDockedStackListener";
        }

        public void onAdjustedForImeChanged(boolean flag, long l)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IDockedStackListener");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            parcel.writeLong(l);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onDividerVisibilityChanged(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IDockedStackListener");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onDockSideChanged(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IDockedStackListener");
            parcel.writeInt(i);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onDockedStackExistsChanged(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IDockedStackListener");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onDockedStackMinimizedChanged(boolean flag, long l, boolean flag1)
            throws RemoteException
        {
            boolean flag2;
            Parcel parcel;
            flag2 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IDockedStackListener");
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeLong(l);
            if(flag1)
                i = ((flag2) ? 1 : 0);
            else
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


    public abstract void onAdjustedForImeChanged(boolean flag, long l)
        throws RemoteException;

    public abstract void onDividerVisibilityChanged(boolean flag)
        throws RemoteException;

    public abstract void onDockSideChanged(int i)
        throws RemoteException;

    public abstract void onDockedStackExistsChanged(boolean flag)
        throws RemoteException;

    public abstract void onDockedStackMinimizedChanged(boolean flag, long l, boolean flag1)
        throws RemoteException;
}
