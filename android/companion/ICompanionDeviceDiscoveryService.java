// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.companion;

import android.os.*;

// Referenced classes of package android.companion:
//            AssociationRequest, IFindDeviceCallback, ICompanionDeviceDiscoveryServiceCallback

public interface ICompanionDeviceDiscoveryService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ICompanionDeviceDiscoveryService
    {

        public static ICompanionDeviceDiscoveryService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.companion.ICompanionDeviceDiscoveryService");
            if(iinterface != null && (iinterface instanceof ICompanionDeviceDiscoveryService))
                return (ICompanionDeviceDiscoveryService)iinterface;
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
                parcel1.writeString("android.companion.ICompanionDeviceDiscoveryService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.companion.ICompanionDeviceDiscoveryService");
                break;
            }
            AssociationRequest associationrequest;
            if(parcel.readInt() != 0)
                associationrequest = (AssociationRequest)AssociationRequest.CREATOR.createFromParcel(parcel);
            else
                associationrequest = null;
            startDiscovery(associationrequest, parcel.readString(), IFindDeviceCallback.Stub.asInterface(parcel.readStrongBinder()), ICompanionDeviceDiscoveryServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
            parcel1.writeNoException();
            return true;
        }

        private static final String DESCRIPTOR = "android.companion.ICompanionDeviceDiscoveryService";
        static final int TRANSACTION_startDiscovery = 1;

        public Stub()
        {
            attachInterface(this, "android.companion.ICompanionDeviceDiscoveryService");
        }
    }

    private static class Stub.Proxy
        implements ICompanionDeviceDiscoveryService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.companion.ICompanionDeviceDiscoveryService";
        }

        public void startDiscovery(AssociationRequest associationrequest, String s, IFindDeviceCallback ifinddevicecallback, ICompanionDeviceDiscoveryServiceCallback icompaniondevicediscoveryservicecallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.companion.ICompanionDeviceDiscoveryService");
            if(associationrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            associationrequest.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            if(ifinddevicecallback == null)
                break MISSING_BLOCK_LABEL_136;
            associationrequest = ifinddevicecallback.asBinder();
_L4:
            parcel.writeStrongBinder(associationrequest);
            associationrequest = obj;
            if(icompaniondevicediscoveryservicecallback == null)
                break MISSING_BLOCK_LABEL_76;
            associationrequest = icompaniondevicediscoveryservicecallback.asBinder();
            parcel.writeStrongBinder(associationrequest);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            associationrequest;
            parcel1.recycle();
            parcel.recycle();
            throw associationrequest;
            associationrequest = null;
              goto _L4
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void startDiscovery(AssociationRequest associationrequest, String s, IFindDeviceCallback ifinddevicecallback, ICompanionDeviceDiscoveryServiceCallback icompaniondevicediscoveryservicecallback)
        throws RemoteException;
}
