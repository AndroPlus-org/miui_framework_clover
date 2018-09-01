// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware;

import android.hardware.camera2.ICameraDeviceCallbacks;
import android.hardware.camera2.ICameraDeviceUser;
import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.params.VendorTagDescriptor;
import android.hardware.camera2.params.VendorTagDescriptorCache;
import android.os.*;

// Referenced classes of package android.hardware:
//            ICameraServiceListener, CameraStatus, ICameraClient, ICamera, 
//            CameraInfo

public interface ICameraService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ICameraService
    {

        public static ICameraService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.ICameraService");
            if(iinterface != null && (iinterface instanceof ICameraService))
                return (ICameraService)iinterface;
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
                parcel1.writeString("android.hardware.ICameraService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.ICameraService");
                i = getNumberOfCameras(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.hardware.ICameraService");
                parcel = getCameraInfo(parcel.readInt());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.hardware.ICameraService");
                parcel = connect(ICameraClient.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.hardware.ICameraService");
                parcel = connectDevice(android.hardware.camera2.ICameraDeviceCallbacks.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.hardware.ICameraService");
                parcel = connectLegacy(ICameraClient.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.hardware.ICameraService");
                parcel = addListener(ICameraServiceListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.hardware.ICameraService");
                removeListener(ICameraServiceListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.hardware.ICameraService");
                parcel = getCameraCharacteristics(parcel.readString());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.hardware.ICameraService");
                parcel = getCameraVendorTagDescriptor();
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.hardware.ICameraService");
                parcel = getCameraVendorTagCache();
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.hardware.ICameraService");
                parcel = getLegacyParameters(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.hardware.ICameraService");
                boolean flag = supportsCameraApi(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.hardware.ICameraService");
                String s = parcel.readString();
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                setTorchMode(s, flag1, parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.hardware.ICameraService");
                notifySystemEvent(parcel.readInt(), parcel.createIntArray());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.hardware.ICameraService";
        static final int TRANSACTION_addListener = 6;
        static final int TRANSACTION_connect = 3;
        static final int TRANSACTION_connectDevice = 4;
        static final int TRANSACTION_connectLegacy = 5;
        static final int TRANSACTION_getCameraCharacteristics = 8;
        static final int TRANSACTION_getCameraInfo = 2;
        static final int TRANSACTION_getCameraVendorTagCache = 10;
        static final int TRANSACTION_getCameraVendorTagDescriptor = 9;
        static final int TRANSACTION_getLegacyParameters = 11;
        static final int TRANSACTION_getNumberOfCameras = 1;
        static final int TRANSACTION_notifySystemEvent = 14;
        static final int TRANSACTION_removeListener = 7;
        static final int TRANSACTION_setTorchMode = 13;
        static final int TRANSACTION_supportsCameraApi = 12;

        public Stub()
        {
            attachInterface(this, "android.hardware.ICameraService");
        }
    }

    private static class Stub.Proxy
        implements ICameraService
    {

        public CameraStatus[] addListener(ICameraServiceListener icameraservicelistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.ICameraService");
            if(icameraservicelistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = icameraservicelistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            icameraservicelistener = (CameraStatus[])parcel1.createTypedArray(CameraStatus.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return icameraservicelistener;
            icameraservicelistener;
            parcel1.recycle();
            parcel.recycle();
            throw icameraservicelistener;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public ICamera connect(ICameraClient icameraclient, int i, String s, int j, int k)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.ICameraService");
            if(icameraclient == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = icameraclient.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            icameraclient = ICamera.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return icameraclient;
            icameraclient;
            parcel1.recycle();
            parcel.recycle();
            throw icameraclient;
        }

        public ICameraDeviceUser connectDevice(ICameraDeviceCallbacks icameradevicecallbacks, String s, String s1, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.ICameraService");
            if(icameradevicecallbacks == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = icameradevicecallbacks.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            icameradevicecallbacks = android.hardware.camera2.ICameraDeviceUser.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return icameradevicecallbacks;
            icameradevicecallbacks;
            parcel1.recycle();
            parcel.recycle();
            throw icameradevicecallbacks;
        }

        public ICamera connectLegacy(ICameraClient icameraclient, int i, int j, String s, int k)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.ICameraService");
            if(icameraclient == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = icameraclient.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s);
            parcel.writeInt(k);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            icameraclient = ICamera.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return icameraclient;
            icameraclient;
            parcel1.recycle();
            parcel.recycle();
            throw icameraclient;
        }

        public CameraMetadataNative getCameraCharacteristics(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.ICameraService");
            parcel.writeString(s);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (CameraMetadataNative)CameraMetadataNative.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public CameraInfo getCameraInfo(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.ICameraService");
            parcel.writeInt(i);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            CameraInfo camerainfo = (CameraInfo)CameraInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return camerainfo;
_L2:
            camerainfo = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public VendorTagDescriptorCache getCameraVendorTagCache()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.ICameraService");
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            VendorTagDescriptorCache vendortagdescriptorcache = (VendorTagDescriptorCache)VendorTagDescriptorCache.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return vendortagdescriptorcache;
_L2:
            vendortagdescriptorcache = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public VendorTagDescriptor getCameraVendorTagDescriptor()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.ICameraService");
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            VendorTagDescriptor vendortagdescriptor = (VendorTagDescriptor)VendorTagDescriptor.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return vendortagdescriptor;
_L2:
            vendortagdescriptor = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.hardware.ICameraService";
        }

        public String getLegacyParameters(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("android.hardware.ICameraService");
            parcel.writeInt(i);
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getNumberOfCameras(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.ICameraService");
            parcel.writeInt(i);
            mRemote.transact(1, parcel, parcel1, 0);
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

        public void notifySystemEvent(int i, int ai[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.ICameraService");
            parcel.writeInt(i);
            parcel.writeIntArray(ai);
            mRemote.transact(14, parcel, null, 1);
            parcel.recycle();
            return;
            ai;
            parcel.recycle();
            throw ai;
        }

        public void removeListener(ICameraServiceListener icameraservicelistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.ICameraService");
            if(icameraservicelistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = icameraservicelistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            icameraservicelistener;
            parcel1.recycle();
            parcel.recycle();
            throw icameraservicelistener;
        }

        public void setTorchMode(String s, boolean flag, IBinder ibinder)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.ICameraService");
            parcel.writeString(s);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean supportsCameraApi(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.ICameraService");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(12, parcel, parcel1, 0);
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract CameraStatus[] addListener(ICameraServiceListener icameraservicelistener)
        throws RemoteException;

    public abstract ICamera connect(ICameraClient icameraclient, int i, String s, int j, int k)
        throws RemoteException;

    public abstract ICameraDeviceUser connectDevice(ICameraDeviceCallbacks icameradevicecallbacks, String s, String s1, int i)
        throws RemoteException;

    public abstract ICamera connectLegacy(ICameraClient icameraclient, int i, int j, String s, int k)
        throws RemoteException;

    public abstract CameraMetadataNative getCameraCharacteristics(String s)
        throws RemoteException;

    public abstract CameraInfo getCameraInfo(int i)
        throws RemoteException;

    public abstract VendorTagDescriptorCache getCameraVendorTagCache()
        throws RemoteException;

    public abstract VendorTagDescriptor getCameraVendorTagDescriptor()
        throws RemoteException;

    public abstract String getLegacyParameters(int i)
        throws RemoteException;

    public abstract int getNumberOfCameras(int i)
        throws RemoteException;

    public abstract void notifySystemEvent(int i, int ai[])
        throws RemoteException;

    public abstract void removeListener(ICameraServiceListener icameraservicelistener)
        throws RemoteException;

    public abstract void setTorchMode(String s, boolean flag, IBinder ibinder)
        throws RemoteException;

    public abstract boolean supportsCameraApi(String s, int i)
        throws RemoteException;

    public static final int API_VERSION_1 = 1;
    public static final int API_VERSION_2 = 2;
    public static final int CAMERA_HAL_API_VERSION_UNSPECIFIED = -1;
    public static final int CAMERA_TYPE_ALL = 1;
    public static final int CAMERA_TYPE_BACKWARD_COMPATIBLE = 0;
    public static final int ERROR_ALREADY_EXISTS = 2;
    public static final int ERROR_CAMERA_IN_USE = 7;
    public static final int ERROR_DEPRECATED_HAL = 9;
    public static final int ERROR_DISABLED = 6;
    public static final int ERROR_DISCONNECTED = 4;
    public static final int ERROR_ILLEGAL_ARGUMENT = 3;
    public static final int ERROR_INVALID_OPERATION = 10;
    public static final int ERROR_MAX_CAMERAS_IN_USE = 8;
    public static final int ERROR_PERMISSION_DENIED = 1;
    public static final int ERROR_TIMED_OUT = 5;
    public static final int EVENT_NONE = 0;
    public static final int EVENT_USER_SWITCHED = 1;
    public static final int USE_CALLING_PID = -1;
    public static final int USE_CALLING_UID = -1;
}
