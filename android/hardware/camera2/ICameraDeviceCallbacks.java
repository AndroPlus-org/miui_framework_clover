// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2;

import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.impl.CaptureResultExtras;
import android.os.*;

public interface ICameraDeviceCallbacks
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ICameraDeviceCallbacks
    {

        public static ICameraDeviceCallbacks asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.camera2.ICameraDeviceCallbacks");
            if(iinterface != null && (iinterface instanceof ICameraDeviceCallbacks))
                return (ICameraDeviceCallbacks)iinterface;
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
                parcel1.writeString("android.hardware.camera2.ICameraDeviceCallbacks");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.camera2.ICameraDeviceCallbacks");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (CaptureResultExtras)CaptureResultExtras.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onDeviceError(i, parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.hardware.camera2.ICameraDeviceCallbacks");
                onDeviceIdle();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.hardware.camera2.ICameraDeviceCallbacks");
                if(parcel.readInt() != 0)
                    parcel1 = (CaptureResultExtras)CaptureResultExtras.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                onCaptureStarted(parcel1, parcel.readLong());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.hardware.camera2.ICameraDeviceCallbacks");
                if(parcel.readInt() != 0)
                    parcel1 = (CameraMetadataNative)CameraMetadataNative.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    parcel = (CaptureResultExtras)CaptureResultExtras.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onResultReceived(parcel1, parcel);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.hardware.camera2.ICameraDeviceCallbacks");
                onPrepared(parcel.readInt());
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.hardware.camera2.ICameraDeviceCallbacks");
                onRepeatingRequestError(parcel.readLong(), parcel.readInt());
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.hardware.camera2.ICameraDeviceCallbacks");
                onRequestQueueEmpty();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.hardware.camera2.ICameraDeviceCallbacks";
        static final int TRANSACTION_onCaptureStarted = 3;
        static final int TRANSACTION_onDeviceError = 1;
        static final int TRANSACTION_onDeviceIdle = 2;
        static final int TRANSACTION_onPrepared = 5;
        static final int TRANSACTION_onRepeatingRequestError = 6;
        static final int TRANSACTION_onRequestQueueEmpty = 7;
        static final int TRANSACTION_onResultReceived = 4;

        public Stub()
        {
            attachInterface(this, "android.hardware.camera2.ICameraDeviceCallbacks");
        }
    }

    private static class Stub.Proxy
        implements ICameraDeviceCallbacks
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.hardware.camera2.ICameraDeviceCallbacks";
        }

        public void onCaptureStarted(CaptureResultExtras captureresultextras, long l)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceCallbacks");
            if(captureresultextras == null)
                break MISSING_BLOCK_LABEL_56;
            parcel.writeInt(1);
            captureresultextras.writeToParcel(parcel, 0);
_L1:
            parcel.writeLong(l);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            captureresultextras;
            parcel.recycle();
            throw captureresultextras;
        }

        public void onDeviceError(int i, CaptureResultExtras captureresultextras)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceCallbacks");
            parcel.writeInt(i);
            if(captureresultextras == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            captureresultextras.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            captureresultextras;
            parcel.recycle();
            throw captureresultextras;
        }

        public void onDeviceIdle()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceCallbacks");
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onPrepared(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceCallbacks");
            parcel.writeInt(i);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onRepeatingRequestError(long l, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceCallbacks");
            parcel.writeLong(l);
            parcel.writeInt(i);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onRequestQueueEmpty()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceCallbacks");
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onResultReceived(CameraMetadataNative camerametadatanative, CaptureResultExtras captureresultextras)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceCallbacks");
            if(camerametadatanative == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            camerametadatanative.writeToParcel(parcel, 0);
_L3:
            if(captureresultextras == null)
                break MISSING_BLOCK_LABEL_74;
            parcel.writeInt(1);
            captureresultextras.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            camerametadatanative;
            parcel.recycle();
            throw camerametadatanative;
            parcel.writeInt(0);
              goto _L4
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onCaptureStarted(CaptureResultExtras captureresultextras, long l)
        throws RemoteException;

    public abstract void onDeviceError(int i, CaptureResultExtras captureresultextras)
        throws RemoteException;

    public abstract void onDeviceIdle()
        throws RemoteException;

    public abstract void onPrepared(int i)
        throws RemoteException;

    public abstract void onRepeatingRequestError(long l, int i)
        throws RemoteException;

    public abstract void onRequestQueueEmpty()
        throws RemoteException;

    public abstract void onResultReceived(CameraMetadataNative camerametadatanative, CaptureResultExtras captureresultextras)
        throws RemoteException;

    public static final int ERROR_CAMERA_BUFFER = 5;
    public static final int ERROR_CAMERA_DEVICE = 1;
    public static final int ERROR_CAMERA_DISCONNECTED = 0;
    public static final int ERROR_CAMERA_INVALID_ERROR = -1;
    public static final int ERROR_CAMERA_REQUEST = 3;
    public static final int ERROR_CAMERA_RESULT = 4;
    public static final int ERROR_CAMERA_SERVICE = 2;
}
