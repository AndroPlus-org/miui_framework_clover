// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telecom;

import android.os.*;
import android.telecom.ParcelableCall;

// Referenced classes of package com.android.internal.telecom:
//            ICallScreeningAdapter

public interface ICallScreeningService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ICallScreeningService
    {

        public static ICallScreeningService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.telecom.ICallScreeningService");
            if(iinterface != null && (iinterface instanceof ICallScreeningService))
                return (ICallScreeningService)iinterface;
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
                parcel1.writeString("com.android.internal.telecom.ICallScreeningService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.telecom.ICallScreeningService");
                parcel1 = ICallScreeningAdapter.Stub.asInterface(parcel.readStrongBinder());
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (ParcelableCall)ParcelableCall.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            screenCall(parcel1, parcel);
            return true;
        }

        private static final String DESCRIPTOR = "com.android.internal.telecom.ICallScreeningService";
        static final int TRANSACTION_screenCall = 1;

        public Stub()
        {
            attachInterface(this, "com.android.internal.telecom.ICallScreeningService");
        }
    }

    private static class Stub.Proxy
        implements ICallScreeningService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.telecom.ICallScreeningService";
        }

        public void screenCall(ICallScreeningAdapter icallscreeningadapter, ParcelableCall parcelablecall)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.ICallScreeningService");
            if(icallscreeningadapter == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = icallscreeningadapter.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(parcelablecall == null)
                break MISSING_BLOCK_LABEL_69;
            parcel.writeInt(1);
            parcelablecall.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            icallscreeningadapter;
            parcel.recycle();
            throw icallscreeningadapter;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void screenCall(ICallScreeningAdapter icallscreeningadapter, ParcelableCall parcelablecall)
        throws RemoteException;
}
