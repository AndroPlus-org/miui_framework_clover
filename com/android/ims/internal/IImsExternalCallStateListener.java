// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal;

import android.os.*;
import com.android.ims.ImsExternalCallState;
import java.util.List;

public interface IImsExternalCallStateListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IImsExternalCallStateListener
    {

        public static IImsExternalCallStateListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.ims.internal.IImsExternalCallStateListener");
            if(iinterface != null && (iinterface instanceof IImsExternalCallStateListener))
                return (IImsExternalCallStateListener)iinterface;
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
                parcel1.writeString("com.android.ims.internal.IImsExternalCallStateListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.ims.internal.IImsExternalCallStateListener");
                onImsExternalCallStateUpdate(parcel.createTypedArrayList(ImsExternalCallState.CREATOR));
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.ims.internal.IImsExternalCallStateListener";
        static final int TRANSACTION_onImsExternalCallStateUpdate = 1;

        public Stub()
        {
            attachInterface(this, "com.android.ims.internal.IImsExternalCallStateListener");
        }
    }

    private static class Stub.Proxy
        implements IImsExternalCallStateListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.ims.internal.IImsExternalCallStateListener";
        }

        public void onImsExternalCallStateUpdate(List list)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsExternalCallStateListener");
            parcel.writeTypedList(list);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            list;
            parcel.recycle();
            throw list;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onImsExternalCallStateUpdate(List list)
        throws RemoteException;
}
