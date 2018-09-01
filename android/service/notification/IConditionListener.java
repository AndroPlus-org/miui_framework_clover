// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.notification;

import android.os.*;

// Referenced classes of package android.service.notification:
//            Condition

public interface IConditionListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IConditionListener
    {

        public static IConditionListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.notification.IConditionListener");
            if(iinterface != null && (iinterface instanceof IConditionListener))
                return (IConditionListener)iinterface;
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
                parcel1.writeString("android.service.notification.IConditionListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.notification.IConditionListener");
                onConditionsReceived((Condition[])parcel.createTypedArray(Condition.CREATOR));
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.service.notification.IConditionListener";
        static final int TRANSACTION_onConditionsReceived = 1;

        public Stub()
        {
            attachInterface(this, "android.service.notification.IConditionListener");
        }
    }

    private static class Stub.Proxy
        implements IConditionListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.notification.IConditionListener";
        }

        public void onConditionsReceived(Condition acondition[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.notification.IConditionListener");
            parcel.writeTypedArray(acondition, 0);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            acondition;
            parcel.recycle();
            throw acondition;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onConditionsReceived(Condition acondition[])
        throws RemoteException;
}
