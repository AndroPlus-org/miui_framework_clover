// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.os.*;

// Referenced classes of package android.view:
//            AppTransitionAnimationSpec

public interface IAppTransitionAnimationSpecsFuture
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IAppTransitionAnimationSpecsFuture
    {

        public static IAppTransitionAnimationSpecsFuture asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.view.IAppTransitionAnimationSpecsFuture");
            if(iinterface != null && (iinterface instanceof IAppTransitionAnimationSpecsFuture))
                return (IAppTransitionAnimationSpecsFuture)iinterface;
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
                parcel1.writeString("android.view.IAppTransitionAnimationSpecsFuture");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.view.IAppTransitionAnimationSpecsFuture");
                parcel = get();
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.view.IAppTransitionAnimationSpecsFuture";
        static final int TRANSACTION_get = 1;

        public Stub()
        {
            attachInterface(this, "android.view.IAppTransitionAnimationSpecsFuture");
        }
    }

    private static class Stub.Proxy
        implements IAppTransitionAnimationSpecsFuture
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public AppTransitionAnimationSpec[] get()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            AppTransitionAnimationSpec aapptransitionanimationspec[];
            parcel.writeInterfaceToken("android.view.IAppTransitionAnimationSpecsFuture");
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            aapptransitionanimationspec = (AppTransitionAnimationSpec[])parcel1.createTypedArray(AppTransitionAnimationSpec.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return aapptransitionanimationspec;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.view.IAppTransitionAnimationSpecsFuture";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract AppTransitionAnimationSpec[] get()
        throws RemoteException;
}
