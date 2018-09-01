// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.companion;

import android.app.PendingIntent;
import android.os.*;
import android.text.TextUtils;

public interface IFindDeviceCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IFindDeviceCallback
    {

        public static IFindDeviceCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.companion.IFindDeviceCallback");
            if(iinterface != null && (iinterface instanceof IFindDeviceCallback))
                return (IFindDeviceCallback)iinterface;
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
                parcel1.writeString("android.companion.IFindDeviceCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.companion.IFindDeviceCallback");
                if(parcel.readInt() != 0)
                    parcel = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onSuccess(parcel);
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.companion.IFindDeviceCallback");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onFailure(parcel);
            parcel1.writeNoException();
            return true;
        }

        private static final String DESCRIPTOR = "android.companion.IFindDeviceCallback";
        static final int TRANSACTION_onFailure = 2;
        static final int TRANSACTION_onSuccess = 1;

        public Stub()
        {
            attachInterface(this, "android.companion.IFindDeviceCallback");
        }
    }

    private static class Stub.Proxy
        implements IFindDeviceCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.companion.IFindDeviceCallback";
        }

        public void onFailure(CharSequence charsequence)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.companion.IFindDeviceCallback");
            if(charsequence == null)
                break MISSING_BLOCK_LABEL_56;
            parcel.writeInt(1);
            TextUtils.writeToParcel(charsequence, parcel, 0);
_L1:
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            charsequence;
            parcel1.recycle();
            parcel.recycle();
            throw charsequence;
        }

        public void onSuccess(PendingIntent pendingintent)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.companion.IFindDeviceCallback");
            if(pendingintent == null)
                break MISSING_BLOCK_LABEL_56;
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            pendingintent;
            parcel1.recycle();
            parcel.recycle();
            throw pendingintent;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onFailure(CharSequence charsequence)
        throws RemoteException;

    public abstract void onSuccess(PendingIntent pendingintent)
        throws RemoteException;
}
