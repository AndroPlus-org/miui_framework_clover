// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.location;

import android.os.*;

// Referenced classes of package android.hardware.location:
//            IActivityRecognitionHardware

public interface IActivityRecognitionHardwareClient
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IActivityRecognitionHardwareClient
    {

        public static IActivityRecognitionHardwareClient asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.location.IActivityRecognitionHardwareClient");
            if(iinterface != null && (iinterface instanceof IActivityRecognitionHardwareClient))
                return (IActivityRecognitionHardwareClient)iinterface;
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
                parcel1.writeString("android.hardware.location.IActivityRecognitionHardwareClient");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.location.IActivityRecognitionHardwareClient");
                break;
            }
            boolean flag;
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            onAvailabilityChanged(flag, IActivityRecognitionHardware.Stub.asInterface(parcel.readStrongBinder()));
            return true;
        }

        private static final String DESCRIPTOR = "android.hardware.location.IActivityRecognitionHardwareClient";
        static final int TRANSACTION_onAvailabilityChanged = 1;

        public Stub()
        {
            attachInterface(this, "android.hardware.location.IActivityRecognitionHardwareClient");
        }
    }

    private static class Stub.Proxy
        implements IActivityRecognitionHardwareClient
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.hardware.location.IActivityRecognitionHardwareClient";
        }

        public void onAvailabilityChanged(boolean flag, IActivityRecognitionHardware iactivityrecognitionhardware)
            throws RemoteException
        {
            IBinder ibinder;
            int i;
            Parcel parcel;
            ibinder = null;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardwareClient");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            if(iactivityrecognitionhardware == null)
                break MISSING_BLOCK_LABEL_39;
            ibinder = iactivityrecognitionhardware.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            iactivityrecognitionhardware;
            parcel.recycle();
            throw iactivityrecognitionhardware;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onAvailabilityChanged(boolean flag, IActivityRecognitionHardware iactivityrecognitionhardware)
        throws RemoteException;
}
