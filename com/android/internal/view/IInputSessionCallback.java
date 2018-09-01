// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view;

import android.os.*;

// Referenced classes of package com.android.internal.view:
//            IInputMethodSession

public interface IInputSessionCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IInputSessionCallback
    {

        public static IInputSessionCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.view.IInputSessionCallback");
            if(iinterface != null && (iinterface instanceof IInputSessionCallback))
                return (IInputSessionCallback)iinterface;
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
                parcel1.writeString("com.android.internal.view.IInputSessionCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.view.IInputSessionCallback");
                sessionCreated(IInputMethodSession.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.internal.view.IInputSessionCallback";
        static final int TRANSACTION_sessionCreated = 1;

        public Stub()
        {
            attachInterface(this, "com.android.internal.view.IInputSessionCallback");
        }
    }

    private static class Stub.Proxy
        implements IInputSessionCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.view.IInputSessionCallback";
        }

        public void sessionCreated(IInputMethodSession iinputmethodsession)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.view.IInputSessionCallback");
            if(iinputmethodsession == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = iinputmethodsession.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            iinputmethodsession;
            parcel.recycle();
            throw iinputmethodsession;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void sessionCreated(IInputMethodSession iinputmethodsession)
        throws RemoteException;
}
