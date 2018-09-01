// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.location;

import android.location.FusedBatchOptions;
import android.os.*;

// Referenced classes of package android.hardware.location:
//            IFusedLocationHardwareSink

public interface IFusedLocationHardware
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IFusedLocationHardware
    {

        public static IFusedLocationHardware asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.location.IFusedLocationHardware");
            if(iinterface != null && (iinterface instanceof IFusedLocationHardware))
                return (IFusedLocationHardware)iinterface;
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
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.hardware.location.IFusedLocationHardware");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.location.IFusedLocationHardware");
                registerSink(IFusedLocationHardwareSink.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.hardware.location.IFusedLocationHardware");
                unregisterSink(IFusedLocationHardwareSink.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.hardware.location.IFusedLocationHardware");
                i = getSupportedBatchSize();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.hardware.location.IFusedLocationHardware");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (FusedBatchOptions)FusedBatchOptions.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                startBatching(i, parcel);
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.hardware.location.IFusedLocationHardware");
                stopBatching(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.hardware.location.IFusedLocationHardware");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (FusedBatchOptions)FusedBatchOptions.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                updateBatchingOptions(i, parcel);
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.hardware.location.IFusedLocationHardware");
                requestBatchOfLocations(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.hardware.location.IFusedLocationHardware");
                boolean flag2 = supportsDiagnosticDataInjection();
                parcel1.writeNoException();
                i = ((flag1) ? 1 : 0);
                if(flag2)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.hardware.location.IFusedLocationHardware");
                injectDiagnosticData(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.hardware.location.IFusedLocationHardware");
                boolean flag3 = supportsDeviceContextInjection();
                parcel1.writeNoException();
                i = ((flag) ? 1 : 0);
                if(flag3)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.hardware.location.IFusedLocationHardware");
                injectDeviceContext(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.hardware.location.IFusedLocationHardware");
                flushBatchedLocations();
                parcel1.writeNoException();
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.hardware.location.IFusedLocationHardware");
                i = getVersion();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.hardware.location.IFusedLocationHardware";
        static final int TRANSACTION_flushBatchedLocations = 12;
        static final int TRANSACTION_getSupportedBatchSize = 3;
        static final int TRANSACTION_getVersion = 13;
        static final int TRANSACTION_injectDeviceContext = 11;
        static final int TRANSACTION_injectDiagnosticData = 9;
        static final int TRANSACTION_registerSink = 1;
        static final int TRANSACTION_requestBatchOfLocations = 7;
        static final int TRANSACTION_startBatching = 4;
        static final int TRANSACTION_stopBatching = 5;
        static final int TRANSACTION_supportsDeviceContextInjection = 10;
        static final int TRANSACTION_supportsDiagnosticDataInjection = 8;
        static final int TRANSACTION_unregisterSink = 2;
        static final int TRANSACTION_updateBatchingOptions = 6;

        public Stub()
        {
            attachInterface(this, "android.hardware.location.IFusedLocationHardware");
        }
    }

    private static class Stub.Proxy
        implements IFusedLocationHardware
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void flushBatchedLocations()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IFusedLocationHardware");
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.hardware.location.IFusedLocationHardware";
        }

        public int getSupportedBatchSize()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.hardware.location.IFusedLocationHardware");
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getVersion()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.hardware.location.IFusedLocationHardware");
            mRemote.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void injectDeviceContext(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IFusedLocationHardware");
            parcel.writeInt(i);
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void injectDiagnosticData(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IFusedLocationHardware");
            parcel.writeString(s);
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void registerSink(IFusedLocationHardwareSink ifusedlocationhardwaresink)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IFusedLocationHardware");
            if(ifusedlocationhardwaresink == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ifusedlocationhardwaresink.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ifusedlocationhardwaresink;
            parcel1.recycle();
            parcel.recycle();
            throw ifusedlocationhardwaresink;
        }

        public void requestBatchOfLocations(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IFusedLocationHardware");
            parcel.writeInt(i);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void startBatching(int i, FusedBatchOptions fusedbatchoptions)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IFusedLocationHardware");
            parcel.writeInt(i);
            if(fusedbatchoptions == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            fusedbatchoptions.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            fusedbatchoptions;
            parcel1.recycle();
            parcel.recycle();
            throw fusedbatchoptions;
        }

        public void stopBatching(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IFusedLocationHardware");
            parcel.writeInt(i);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean supportsDeviceContextInjection()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.hardware.location.IFusedLocationHardware");
            mRemote.transact(10, parcel, parcel1, 0);
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

        public boolean supportsDiagnosticDataInjection()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.hardware.location.IFusedLocationHardware");
            mRemote.transact(8, parcel, parcel1, 0);
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

        public void unregisterSink(IFusedLocationHardwareSink ifusedlocationhardwaresink)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IFusedLocationHardware");
            if(ifusedlocationhardwaresink == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ifusedlocationhardwaresink.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ifusedlocationhardwaresink;
            parcel1.recycle();
            parcel.recycle();
            throw ifusedlocationhardwaresink;
        }

        public void updateBatchingOptions(int i, FusedBatchOptions fusedbatchoptions)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IFusedLocationHardware");
            parcel.writeInt(i);
            if(fusedbatchoptions == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            fusedbatchoptions.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            fusedbatchoptions;
            parcel1.recycle();
            parcel.recycle();
            throw fusedbatchoptions;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void flushBatchedLocations()
        throws RemoteException;

    public abstract int getSupportedBatchSize()
        throws RemoteException;

    public abstract int getVersion()
        throws RemoteException;

    public abstract void injectDeviceContext(int i)
        throws RemoteException;

    public abstract void injectDiagnosticData(String s)
        throws RemoteException;

    public abstract void registerSink(IFusedLocationHardwareSink ifusedlocationhardwaresink)
        throws RemoteException;

    public abstract void requestBatchOfLocations(int i)
        throws RemoteException;

    public abstract void startBatching(int i, FusedBatchOptions fusedbatchoptions)
        throws RemoteException;

    public abstract void stopBatching(int i)
        throws RemoteException;

    public abstract boolean supportsDeviceContextInjection()
        throws RemoteException;

    public abstract boolean supportsDiagnosticDataInjection()
        throws RemoteException;

    public abstract void unregisterSink(IFusedLocationHardwareSink ifusedlocationhardwaresink)
        throws RemoteException;

    public abstract void updateBatchingOptions(int i, FusedBatchOptions fusedbatchoptions)
        throws RemoteException;
}
