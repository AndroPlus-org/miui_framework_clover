// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.gamebooster.service;

import android.graphics.Rect;
import android.os.*;

public interface IMiuiFreeformService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMiuiFreeformService
    {

        public static IMiuiFreeformService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.miui.gamebooster.service.IMiuiFreeformService");
            if(iinterface != null && (iinterface instanceof IMiuiFreeformService))
                return (IMiuiFreeformService)iinterface;
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
                parcel1.writeString("com.miui.gamebooster.service.IMiuiFreeformService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.miui.gamebooster.service.IMiuiFreeformService");
                i = parcel.readInt();
                j = parcel.readInt();
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                moveTaskToStack(i, j, flag);
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.miui.gamebooster.service.IMiuiFreeformService");
                i = parcel.readInt();
                break;
            }
            Rect rect;
            if(parcel.readInt() != 0)
                rect = (Rect)Rect.CREATOR.createFromParcel(parcel);
            else
                rect = null;
            resizeTask(i, rect, parcel.readInt());
            parcel1.writeNoException();
            return true;
        }

        private static final String DESCRIPTOR = "com.miui.gamebooster.service.IMiuiFreeformService";
        static final int TRANSACTION_moveTaskToStack = 1;
        static final int TRANSACTION_resizeTask = 2;

        public Stub()
        {
            attachInterface(this, "com.miui.gamebooster.service.IMiuiFreeformService");
        }
    }

    private static class Stub.Proxy
        implements IMiuiFreeformService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.miui.gamebooster.service.IMiuiFreeformService";
        }

        public void moveTaskToStack(int i, int j, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.gamebooster.service.IMiuiFreeformService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void resizeTask(int i, Rect rect, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.gamebooster.service.IMiuiFreeformService");
            parcel.writeInt(i);
            if(rect == null)
                break MISSING_BLOCK_LABEL_78;
            parcel.writeInt(1);
            rect.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(j);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            rect;
            parcel1.recycle();
            parcel.recycle();
            throw rect;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void moveTaskToStack(int i, int j, boolean flag)
        throws RemoteException;

    public abstract void resizeTask(int i, Rect rect, int j)
        throws RemoteException;
}
