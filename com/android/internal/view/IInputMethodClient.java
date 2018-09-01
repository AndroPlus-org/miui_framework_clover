// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view;

import android.os.*;

// Referenced classes of package com.android.internal.view:
//            InputBindResult

public interface IInputMethodClient
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IInputMethodClient
    {

        public static IInputMethodClient asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.view.IInputMethodClient");
            if(iinterface != null && (iinterface instanceof IInputMethodClient))
                return (IInputMethodClient)iinterface;
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
                parcel1.writeString("com.android.internal.view.IInputMethodClient");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.view.IInputMethodClient");
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                setUsingInputMethod(flag);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.view.IInputMethodClient");
                if(parcel.readInt() != 0)
                    parcel = (InputBindResult)InputBindResult.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onBindMethod(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.view.IInputMethodClient");
                onUnbindMethod(parcel.readInt(), parcel.readInt());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.view.IInputMethodClient");
                boolean flag1;
                boolean flag3;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                setActive(flag1, flag3);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.internal.view.IInputMethodClient");
                setUserActionNotificationSequenceNumber(parcel.readInt());
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.internal.view.IInputMethodClient");
                break;
            }
            boolean flag2;
            if(parcel.readInt() != 0)
                flag2 = true;
            else
                flag2 = false;
            reportFullscreenMode(flag2);
            return true;
        }

        private static final String DESCRIPTOR = "com.android.internal.view.IInputMethodClient";
        static final int TRANSACTION_onBindMethod = 2;
        static final int TRANSACTION_onUnbindMethod = 3;
        static final int TRANSACTION_reportFullscreenMode = 6;
        static final int TRANSACTION_setActive = 4;
        static final int TRANSACTION_setUserActionNotificationSequenceNumber = 5;
        static final int TRANSACTION_setUsingInputMethod = 1;

        public Stub()
        {
            attachInterface(this, "com.android.internal.view.IInputMethodClient");
        }
    }

    private static class Stub.Proxy
        implements IInputMethodClient
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.view.IInputMethodClient";
        }

        public void onBindMethod(InputBindResult inputbindresult)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodClient");
            if(inputbindresult == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            inputbindresult.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            inputbindresult;
            parcel.recycle();
            throw inputbindresult;
        }

        public void onUnbindMethod(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodClient");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void reportFullscreenMode(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodClient");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setActive(boolean flag, boolean flag1)
            throws RemoteException
        {
            boolean flag2;
            Parcel parcel;
            flag2 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodClient");
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(flag1)
                i = ((flag2) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setUserActionNotificationSequenceNumber(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodClient");
            parcel.writeInt(i);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setUsingInputMethod(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputMethodClient");
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onBindMethod(InputBindResult inputbindresult)
        throws RemoteException;

    public abstract void onUnbindMethod(int i, int j)
        throws RemoteException;

    public abstract void reportFullscreenMode(boolean flag)
        throws RemoteException;

    public abstract void setActive(boolean flag, boolean flag1)
        throws RemoteException;

    public abstract void setUserActionNotificationSequenceNumber(int i)
        throws RemoteException;

    public abstract void setUsingInputMethod(boolean flag)
        throws RemoteException;
}
