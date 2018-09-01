// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.*;

// Referenced classes of package android.net:
//            IpSecConfig, IpSecTransformResponse, IpSecUdpEncapResponse, IpSecSpiResponse

public interface IIpSecService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IIpSecService
    {

        public static IIpSecService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.net.IIpSecService");
            if(iinterface != null && (iinterface instanceof IIpSecService))
                return (IIpSecService)iinterface;
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
                parcel1.writeString("android.net.IIpSecService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.net.IIpSecService");
                parcel = reserveSecurityParameterIndex(parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readStrongBinder());
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

            case 2: // '\002'
                parcel.enforceInterface("android.net.IIpSecService");
                releaseSecurityParameterIndex(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.net.IIpSecService");
                parcel = openUdpEncapsulationSocket(parcel.readInt(), parcel.readStrongBinder());
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
                parcel.enforceInterface("android.net.IIpSecService");
                closeUdpEncapsulationSocket(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.net.IIpSecService");
                IpSecConfig ipsecconfig;
                if(parcel.readInt() != 0)
                    ipsecconfig = (IpSecConfig)IpSecConfig.CREATOR.createFromParcel(parcel);
                else
                    ipsecconfig = null;
                parcel = createTransportModeTransform(ipsecconfig, parcel.readStrongBinder());
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

            case 6: // '\006'
                parcel.enforceInterface("android.net.IIpSecService");
                deleteTransportModeTransform(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.net.IIpSecService");
                ParcelFileDescriptor parcelfiledescriptor;
                if(parcel.readInt() != 0)
                    parcelfiledescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcelfiledescriptor = null;
                applyTransportModeTransform(parcelfiledescriptor, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.net.IIpSecService");
                break;
            }
            ParcelFileDescriptor parcelfiledescriptor1;
            if(parcel.readInt() != 0)
                parcelfiledescriptor1 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
            else
                parcelfiledescriptor1 = null;
            removeTransportModeTransform(parcelfiledescriptor1, parcel.readInt());
            parcel1.writeNoException();
            return true;
        }

        private static final String DESCRIPTOR = "android.net.IIpSecService";
        static final int TRANSACTION_applyTransportModeTransform = 7;
        static final int TRANSACTION_closeUdpEncapsulationSocket = 4;
        static final int TRANSACTION_createTransportModeTransform = 5;
        static final int TRANSACTION_deleteTransportModeTransform = 6;
        static final int TRANSACTION_openUdpEncapsulationSocket = 3;
        static final int TRANSACTION_releaseSecurityParameterIndex = 2;
        static final int TRANSACTION_removeTransportModeTransform = 8;
        static final int TRANSACTION_reserveSecurityParameterIndex = 1;

        public Stub()
        {
            attachInterface(this, "android.net.IIpSecService");
        }
    }

    private static class Stub.Proxy
        implements IIpSecService
    {

        public void applyTransportModeTransform(ParcelFileDescriptor parcelfiledescriptor, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IIpSecService");
            if(parcelfiledescriptor == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            parcelfiledescriptor;
            parcel1.recycle();
            parcel.recycle();
            throw parcelfiledescriptor;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void closeUdpEncapsulationSocket(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IIpSecService");
            parcel.writeInt(i);
            mRemote.transact(4, parcel, parcel1, 0);
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

        public IpSecTransformResponse createTransportModeTransform(IpSecConfig ipsecconfig, IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IIpSecService");
            if(ipsecconfig == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            ipsecconfig.writeToParcel(parcel, 0);
_L3:
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_108;
            ipsecconfig = (IpSecTransformResponse)IpSecTransformResponse.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return ipsecconfig;
_L2:
            parcel.writeInt(0);
              goto _L3
            ipsecconfig;
            parcel1.recycle();
            parcel.recycle();
            throw ipsecconfig;
            ipsecconfig = null;
              goto _L4
        }

        public void deleteTransportModeTransform(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IIpSecService");
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

        public String getInterfaceDescriptor()
        {
            return "android.net.IIpSecService";
        }

        public IpSecUdpEncapResponse openUdpEncapsulationSocket(int i, IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IIpSecService");
            parcel.writeInt(i);
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ibinder = (IpSecUdpEncapResponse)IpSecUdpEncapResponse.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return ibinder;
_L2:
            ibinder = null;
            if(true) goto _L4; else goto _L3
_L3:
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void releaseSecurityParameterIndex(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IIpSecService");
            parcel.writeInt(i);
            mRemote.transact(2, parcel, parcel1, 0);
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

        public void removeTransportModeTransform(ParcelFileDescriptor parcelfiledescriptor, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IIpSecService");
            if(parcelfiledescriptor == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            parcelfiledescriptor;
            parcel1.recycle();
            parcel.recycle();
            throw parcelfiledescriptor;
        }

        public IpSecSpiResponse reserveSecurityParameterIndex(int i, String s, int j, IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IIpSecService");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeInt(j);
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (IpSecSpiResponse)IpSecSpiResponse.CREATOR.createFromParcel(parcel1);
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void applyTransportModeTransform(ParcelFileDescriptor parcelfiledescriptor, int i)
        throws RemoteException;

    public abstract void closeUdpEncapsulationSocket(int i)
        throws RemoteException;

    public abstract IpSecTransformResponse createTransportModeTransform(IpSecConfig ipsecconfig, IBinder ibinder)
        throws RemoteException;

    public abstract void deleteTransportModeTransform(int i)
        throws RemoteException;

    public abstract IpSecUdpEncapResponse openUdpEncapsulationSocket(int i, IBinder ibinder)
        throws RemoteException;

    public abstract void releaseSecurityParameterIndex(int i)
        throws RemoteException;

    public abstract void removeTransportModeTransform(ParcelFileDescriptor parcelfiledescriptor, int i)
        throws RemoteException;

    public abstract IpSecSpiResponse reserveSecurityParameterIndex(int i, String s, int j, IBinder ibinder)
        throws RemoteException;
}
