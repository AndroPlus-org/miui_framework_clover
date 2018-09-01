// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.location;

import android.os.*;

// Referenced classes of package android.hardware.location:
//            ActivityChangedEvent

public interface IActivityRecognitionHardwareSink
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IActivityRecognitionHardwareSink
    {

        public static IActivityRecognitionHardwareSink asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.location.IActivityRecognitionHardwareSink");
            if(iinterface != null && (iinterface instanceof IActivityRecognitionHardwareSink))
                return (IActivityRecognitionHardwareSink)iinterface;
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
                parcel1.writeString("android.hardware.location.IActivityRecognitionHardwareSink");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.location.IActivityRecognitionHardwareSink");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (ActivityChangedEvent)ActivityChangedEvent.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onActivityChanged(parcel);
            parcel1.writeNoException();
            return true;
        }

        private static final String DESCRIPTOR = "android.hardware.location.IActivityRecognitionHardwareSink";
        static final int TRANSACTION_onActivityChanged = 1;

        public Stub()
        {
            attachInterface(this, "android.hardware.location.IActivityRecognitionHardwareSink");
        }
    }

    private static class Stub.Proxy
        implements IActivityRecognitionHardwareSink
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.hardware.location.IActivityRecognitionHardwareSink";
        }

        public void onActivityChanged(ActivityChangedEvent activitychangedevent)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardwareSink");
            if(activitychangedevent == null)
                break MISSING_BLOCK_LABEL_56;
            parcel.writeInt(1);
            activitychangedevent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            activitychangedevent;
            parcel1.recycle();
            parcel.recycle();
            throw activitychangedevent;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onActivityChanged(ActivityChangedEvent activitychangedevent)
        throws RemoteException;
}
