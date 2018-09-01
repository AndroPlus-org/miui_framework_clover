// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.location;

import android.os.*;

// Referenced classes of package android.hardware.location:
//            IActivityRecognitionHardware

public interface IActivityRecognitionHardwareWatcher
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IActivityRecognitionHardwareWatcher
    {

        public static IActivityRecognitionHardwareWatcher asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.location.IActivityRecognitionHardwareWatcher");
            if(iinterface != null && (iinterface instanceof IActivityRecognitionHardwareWatcher))
                return (IActivityRecognitionHardwareWatcher)iinterface;
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
                parcel1.writeString("android.hardware.location.IActivityRecognitionHardwareWatcher");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.location.IActivityRecognitionHardwareWatcher");
                onInstanceChanged(IActivityRecognitionHardware.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.hardware.location.IActivityRecognitionHardwareWatcher";
        static final int TRANSACTION_onInstanceChanged = 1;

        public Stub()
        {
            attachInterface(this, "android.hardware.location.IActivityRecognitionHardwareWatcher");
        }
    }

    private static class Stub.Proxy
        implements IActivityRecognitionHardwareWatcher
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.hardware.location.IActivityRecognitionHardwareWatcher";
        }

        public void onInstanceChanged(IActivityRecognitionHardware iactivityrecognitionhardware)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardwareWatcher");
            if(iactivityrecognitionhardware == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iactivityrecognitionhardware.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iactivityrecognitionhardware;
            parcel1.recycle();
            parcel.recycle();
            throw iactivityrecognitionhardware;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onInstanceChanged(IActivityRecognitionHardware iactivityrecognitionhardware)
        throws RemoteException;
}
