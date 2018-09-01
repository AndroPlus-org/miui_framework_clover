// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2;

import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.params.OutputConfiguration;
import android.hardware.camera2.utils.SubmitInfo;
import android.os.*;
import android.view.Surface;

// Referenced classes of package android.hardware.camera2:
//            CaptureRequest

public interface ICameraDeviceUser
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ICameraDeviceUser
    {

        public static ICameraDeviceUser asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.camera2.ICameraDeviceUser");
            if(iinterface != null && (iinterface instanceof ICameraDeviceUser))
                return (ICameraDeviceUser)iinterface;
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
                parcel1.writeString("android.hardware.camera2.ICameraDeviceUser");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
                disconnect();
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
                CaptureRequest capturerequest;
                boolean flag;
                if(parcel.readInt() != 0)
                    capturerequest = (CaptureRequest)CaptureRequest.CREATOR.createFromParcel(parcel);
                else
                    capturerequest = null;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                parcel = submitRequest(capturerequest, flag);
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
                parcel.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
                CaptureRequest acapturerequest[] = (CaptureRequest[])parcel.createTypedArray(CaptureRequest.CREATOR);
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                parcel = submitRequestList(acapturerequest, flag1);
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

            case 4: // '\004'
                parcel.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
                long l = cancelRequest(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeLong(l);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
                beginConfigure();
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
                endConfigure(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
                deleteStream(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
                if(parcel.readInt() != 0)
                    parcel = (OutputConfiguration)OutputConfiguration.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = createStream(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
                i = createInputStream(parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
                parcel = getInputSurface();
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
                parcel.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
                parcel = createDefaultRequest(parcel.readInt());
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

            case 12: // '\f'
                parcel.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
                parcel = getCameraInfo();
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

            case 13: // '\r'
                parcel.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
                waitUntilIdle();
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
                long l1 = flush();
                parcel1.writeNoException();
                parcel1.writeLong(l1);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
                prepare(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
                tearDown(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
                prepare2(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
                i = parcel.readInt();
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (OutputConfiguration)OutputConfiguration.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            finalizeOutputConfigurations(i, parcel);
            parcel1.writeNoException();
            return true;
        }

        private static final String DESCRIPTOR = "android.hardware.camera2.ICameraDeviceUser";
        static final int TRANSACTION_beginConfigure = 5;
        static final int TRANSACTION_cancelRequest = 4;
        static final int TRANSACTION_createDefaultRequest = 11;
        static final int TRANSACTION_createInputStream = 9;
        static final int TRANSACTION_createStream = 8;
        static final int TRANSACTION_deleteStream = 7;
        static final int TRANSACTION_disconnect = 1;
        static final int TRANSACTION_endConfigure = 6;
        static final int TRANSACTION_finalizeOutputConfigurations = 18;
        static final int TRANSACTION_flush = 14;
        static final int TRANSACTION_getCameraInfo = 12;
        static final int TRANSACTION_getInputSurface = 10;
        static final int TRANSACTION_prepare = 15;
        static final int TRANSACTION_prepare2 = 17;
        static final int TRANSACTION_submitRequest = 2;
        static final int TRANSACTION_submitRequestList = 3;
        static final int TRANSACTION_tearDown = 16;
        static final int TRANSACTION_waitUntilIdle = 13;

        public Stub()
        {
            attachInterface(this, "android.hardware.camera2.ICameraDeviceUser");
        }
    }

    private static class Stub.Proxy
        implements ICameraDeviceUser
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void beginConfigure()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
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

        public long cancelRequest(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
            parcel.writeInt(i);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            l = parcel1.readLong();
            parcel1.recycle();
            parcel.recycle();
            return l;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public CameraMetadataNative createDefaultRequest(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
            parcel.writeInt(i);
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            CameraMetadataNative camerametadatanative = (CameraMetadataNative)CameraMetadataNative.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return camerametadatanative;
_L2:
            camerametadatanative = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int createInputStream(int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(9, parcel, parcel1, 0);
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

        public int createStream(OutputConfiguration outputconfiguration)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
            if(outputconfiguration == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            outputconfiguration.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            outputconfiguration;
            parcel1.recycle();
            parcel.recycle();
            throw outputconfiguration;
        }

        public void deleteStream(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
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

        public void disconnect()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
            mRemote.transact(1, parcel, parcel1, 0);
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

        public void endConfigure(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
            parcel.writeInt(i);
            mRemote.transact(6, parcel, parcel1, 0);
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

        public void finalizeOutputConfigurations(int i, OutputConfiguration outputconfiguration)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
            parcel.writeInt(i);
            if(outputconfiguration == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            outputconfiguration.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(18, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            outputconfiguration;
            parcel1.recycle();
            parcel.recycle();
            throw outputconfiguration;
        }

        public long flush()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
            mRemote.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            l = parcel1.readLong();
            parcel1.recycle();
            parcel.recycle();
            return l;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public CameraMetadataNative getCameraInfo()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            CameraMetadataNative camerametadatanative = (CameraMetadataNative)CameraMetadataNative.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return camerametadatanative;
_L2:
            camerametadatanative = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Surface getInputSurface()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Surface surface = (Surface)Surface.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return surface;
_L2:
            surface = null;
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
            return "android.hardware.camera2.ICameraDeviceUser";
        }

        public void prepare(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
            parcel.writeInt(i);
            mRemote.transact(15, parcel, parcel1, 0);
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

        public void prepare2(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(17, parcel, parcel1, 0);
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

        public SubmitInfo submitRequest(CaptureRequest capturerequest, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
            if(capturerequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            capturerequest.writeToParcel(parcel, 0);
_L3:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_128;
            capturerequest = (SubmitInfo)SubmitInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return capturerequest;
_L2:
            parcel.writeInt(0);
              goto _L3
            capturerequest;
            parcel1.recycle();
            parcel.recycle();
            throw capturerequest;
            capturerequest = null;
              goto _L4
        }

        public SubmitInfo submitRequestList(CaptureRequest acapturerequest[], boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
            parcel.writeTypedArray(acapturerequest, 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            acapturerequest = (SubmitInfo)SubmitInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return acapturerequest;
_L2:
            acapturerequest = null;
            if(true) goto _L4; else goto _L3
_L3:
            acapturerequest;
            parcel1.recycle();
            parcel.recycle();
            throw acapturerequest;
        }

        public void tearDown(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
            parcel.writeInt(i);
            mRemote.transact(16, parcel, parcel1, 0);
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

        public void waitUntilIdle()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
            mRemote.transact(13, parcel, parcel1, 0);
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void beginConfigure()
        throws RemoteException;

    public abstract long cancelRequest(int i)
        throws RemoteException;

    public abstract CameraMetadataNative createDefaultRequest(int i)
        throws RemoteException;

    public abstract int createInputStream(int i, int j, int k)
        throws RemoteException;

    public abstract int createStream(OutputConfiguration outputconfiguration)
        throws RemoteException;

    public abstract void deleteStream(int i)
        throws RemoteException;

    public abstract void disconnect()
        throws RemoteException;

    public abstract void endConfigure(int i)
        throws RemoteException;

    public abstract void finalizeOutputConfigurations(int i, OutputConfiguration outputconfiguration)
        throws RemoteException;

    public abstract long flush()
        throws RemoteException;

    public abstract CameraMetadataNative getCameraInfo()
        throws RemoteException;

    public abstract Surface getInputSurface()
        throws RemoteException;

    public abstract void prepare(int i)
        throws RemoteException;

    public abstract void prepare2(int i, int j)
        throws RemoteException;

    public abstract SubmitInfo submitRequest(CaptureRequest capturerequest, boolean flag)
        throws RemoteException;

    public abstract SubmitInfo submitRequestList(CaptureRequest acapturerequest[], boolean flag)
        throws RemoteException;

    public abstract void tearDown(int i)
        throws RemoteException;

    public abstract void waitUntilIdle()
        throws RemoteException;

    public static final int CONSTRAINED_HIGH_SPEED_MODE = 1;
    public static final int NORMAL_MODE = 0;
    public static final int NO_IN_FLIGHT_REPEATING_FRAMES = -1;
    public static final int TEMPLATE_MANUAL = 6;
    public static final int TEMPLATE_PREVIEW = 1;
    public static final int TEMPLATE_RECORD = 3;
    public static final int TEMPLATE_STILL_CAPTURE = 2;
    public static final int TEMPLATE_VIDEO_SNAPSHOT = 4;
    public static final int TEMPLATE_ZERO_SHUTTER_LAG = 5;
    public static final int VENDOR_MODE_START = 32768;
}
