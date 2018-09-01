// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.location;

import android.os.*;

// Referenced classes of package android.hardware.location:
//            IActivityRecognitionHardwareSink

public interface IActivityRecognitionHardware
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IActivityRecognitionHardware
    {

        public static IActivityRecognitionHardware asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.location.IActivityRecognitionHardware");
            if(iinterface != null && (iinterface instanceof IActivityRecognitionHardware))
                return (IActivityRecognitionHardware)iinterface;
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
            boolean flag = false;
            boolean flag1 = false;
            boolean flag2 = false;
            boolean flag3 = false;
            boolean flag4 = false;
            boolean flag5 = false;
            boolean flag11;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.hardware.location.IActivityRecognitionHardware");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.location.IActivityRecognitionHardware");
                parcel = getSupportedActivities();
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.hardware.location.IActivityRecognitionHardware");
                boolean flag6 = isActivitySupported(parcel.readString());
                parcel1.writeNoException();
                i = ((flag5) ? 1 : 0);
                if(flag6)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.hardware.location.IActivityRecognitionHardware");
                boolean flag7 = registerSink(IActivityRecognitionHardwareSink.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                i = ((flag) ? 1 : 0);
                if(flag7)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.hardware.location.IActivityRecognitionHardware");
                boolean flag8 = unregisterSink(IActivityRecognitionHardwareSink.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                i = ((flag1) ? 1 : 0);
                if(flag8)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.hardware.location.IActivityRecognitionHardware");
                boolean flag9 = enableActivityEvent(parcel.readString(), parcel.readInt(), parcel.readLong());
                parcel1.writeNoException();
                i = ((flag2) ? 1 : 0);
                if(flag9)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.hardware.location.IActivityRecognitionHardware");
                boolean flag10 = disableActivityEvent(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                i = ((flag3) ? 1 : 0);
                if(flag10)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.hardware.location.IActivityRecognitionHardware");
                flag11 = flush();
                parcel1.writeNoException();
                i = ((flag4) ? 1 : 0);
                break;
            }
            if(flag11)
                i = 1;
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "android.hardware.location.IActivityRecognitionHardware";
        static final int TRANSACTION_disableActivityEvent = 6;
        static final int TRANSACTION_enableActivityEvent = 5;
        static final int TRANSACTION_flush = 7;
        static final int TRANSACTION_getSupportedActivities = 1;
        static final int TRANSACTION_isActivitySupported = 2;
        static final int TRANSACTION_registerSink = 3;
        static final int TRANSACTION_unregisterSink = 4;

        public Stub()
        {
            attachInterface(this, "android.hardware.location.IActivityRecognitionHardware");
        }
    }

    private static class Stub.Proxy
        implements IActivityRecognitionHardware
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public boolean disableActivityEvent(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardware");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean enableActivityEvent(String s, int i, long l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardware");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeLong(l);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean flush()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardware");
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.hardware.location.IActivityRecognitionHardware";
        }

        public String[] getSupportedActivities()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardware");
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            as = parcel1.createStringArray();
            parcel1.recycle();
            parcel.recycle();
            return as;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isActivitySupported(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardware");
            parcel.writeString(s);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean registerSink(IActivityRecognitionHardwareSink iactivityrecognitionhardwaresink)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardware");
            if(iactivityrecognitionhardwaresink == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iactivityrecognitionhardwaresink.asBinder();
            int i;
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            iactivityrecognitionhardwaresink;
            parcel1.recycle();
            parcel.recycle();
            throw iactivityrecognitionhardwaresink;
        }

        public boolean unregisterSink(IActivityRecognitionHardwareSink iactivityrecognitionhardwaresink)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardware");
            if(iactivityrecognitionhardwaresink == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iactivityrecognitionhardwaresink.asBinder();
            int i;
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            iactivityrecognitionhardwaresink;
            parcel1.recycle();
            parcel.recycle();
            throw iactivityrecognitionhardwaresink;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract boolean disableActivityEvent(String s, int i)
        throws RemoteException;

    public abstract boolean enableActivityEvent(String s, int i, long l)
        throws RemoteException;

    public abstract boolean flush()
        throws RemoteException;

    public abstract String[] getSupportedActivities()
        throws RemoteException;

    public abstract boolean isActivitySupported(String s)
        throws RemoteException;

    public abstract boolean registerSink(IActivityRecognitionHardwareSink iactivityrecognitionhardwaresink)
        throws RemoteException;

    public abstract boolean unregisterSink(IActivityRecognitionHardwareSink iactivityrecognitionhardwaresink)
        throws RemoteException;
}
