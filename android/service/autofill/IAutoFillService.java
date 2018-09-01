// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.autofill;

import android.os.*;

// Referenced classes of package android.service.autofill:
//            FillRequest, IFillCallback, SaveRequest, ISaveCallback

public interface IAutoFillService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IAutoFillService
    {

        public static IAutoFillService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.autofill.IAutoFillService");
            if(iinterface != null && (iinterface instanceof IAutoFillService))
                return (IAutoFillService)iinterface;
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
                parcel1.writeString("android.service.autofill.IAutoFillService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.autofill.IAutoFillService");
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                onConnectedStateChanged(flag);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.service.autofill.IAutoFillService");
                if(parcel.readInt() != 0)
                    parcel1 = (FillRequest)FillRequest.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                onFillRequest(parcel1, IFillCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.service.autofill.IAutoFillService");
                break;
            }
            if(parcel.readInt() != 0)
                parcel1 = (SaveRequest)SaveRequest.CREATOR.createFromParcel(parcel);
            else
                parcel1 = null;
            onSaveRequest(parcel1, ISaveCallback.Stub.asInterface(parcel.readStrongBinder()));
            return true;
        }

        private static final String DESCRIPTOR = "android.service.autofill.IAutoFillService";
        static final int TRANSACTION_onConnectedStateChanged = 1;
        static final int TRANSACTION_onFillRequest = 2;
        static final int TRANSACTION_onSaveRequest = 3;

        public Stub()
        {
            attachInterface(this, "android.service.autofill.IAutoFillService");
        }
    }

    private static class Stub.Proxy
        implements IAutoFillService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.autofill.IAutoFillService";
        }

        public void onConnectedStateChanged(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.autofill.IAutoFillService");
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

        public void onFillRequest(FillRequest fillrequest, IFillCallback ifillcallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.autofill.IAutoFillService");
            if(fillrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            fillrequest.writeToParcel(parcel, 0);
_L4:
            fillrequest = obj;
            if(ifillcallback == null)
                break MISSING_BLOCK_LABEL_44;
            fillrequest = ifillcallback.asBinder();
            parcel.writeStrongBinder(fillrequest);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
            if(true) goto _L4; else goto _L3
_L3:
            fillrequest;
            parcel.recycle();
            throw fillrequest;
        }

        public void onSaveRequest(SaveRequest saverequest, ISaveCallback isavecallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.autofill.IAutoFillService");
            if(saverequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            saverequest.writeToParcel(parcel, 0);
_L4:
            saverequest = obj;
            if(isavecallback == null)
                break MISSING_BLOCK_LABEL_44;
            saverequest = isavecallback.asBinder();
            parcel.writeStrongBinder(saverequest);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
            if(true) goto _L4; else goto _L3
_L3:
            saverequest;
            parcel.recycle();
            throw saverequest;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onConnectedStateChanged(boolean flag)
        throws RemoteException;

    public abstract void onFillRequest(FillRequest fillrequest, IFillCallback ifillcallback)
        throws RemoteException;

    public abstract void onSaveRequest(SaveRequest saverequest, ISaveCallback isavecallback)
        throws RemoteException;
}
