// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.session;

import android.os.*;
import java.util.List;

public interface IActiveSessionsListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IActiveSessionsListener
    {

        public static IActiveSessionsListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.session.IActiveSessionsListener");
            if(iinterface != null && (iinterface instanceof IActiveSessionsListener))
                return (IActiveSessionsListener)iinterface;
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
                parcel1.writeString("android.media.session.IActiveSessionsListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.session.IActiveSessionsListener");
                onActiveSessionsChanged(parcel.createTypedArrayList(MediaSession.Token.CREATOR));
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.media.session.IActiveSessionsListener";
        static final int TRANSACTION_onActiveSessionsChanged = 1;

        public Stub()
        {
            attachInterface(this, "android.media.session.IActiveSessionsListener");
        }
    }

    private static class Stub.Proxy
        implements IActiveSessionsListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.session.IActiveSessionsListener";
        }

        public void onActiveSessionsChanged(List list)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.IActiveSessionsListener");
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


    public abstract void onActiveSessionsChanged(List list)
        throws RemoteException;
}
