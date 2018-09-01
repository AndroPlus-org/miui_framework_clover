// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.lowpan;

import android.net.IpPrefix;
import android.os.*;
import java.util.Map;

// Referenced classes of package android.net.lowpan:
//            ILowpanInterfaceListener, LowpanProvision, LowpanCredential, LowpanIdentity, 
//            LowpanChannelInfo, LowpanBeaconInfo, ILowpanEnergyScanCallback, ILowpanNetScanCallback

public interface ILowpanInterface
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ILowpanInterface
    {

        public static ILowpanInterface asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.net.lowpan.ILowpanInterface");
            if(iinterface != null && (iinterface instanceof ILowpanInterface))
                return (ILowpanInterface)iinterface;
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
                parcel1.writeString("android.net.lowpan.ILowpanInterface");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                parcel = getName();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                parcel = getNcpVersion();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                parcel = getDriverVersion();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                parcel = getSupportedChannels();
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                parcel = getSupportedNetworkTypes();
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                parcel = getMacAddress();
                parcel1.writeNoException();
                parcel1.writeByteArray(parcel);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                boolean flag = isEnabled();
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                setEnabled(flag1);
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                boolean flag2 = isUp();
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                boolean flag3 = isCommissioned();
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                boolean flag4 = isConnected();
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                parcel = getState();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                parcel = getRole();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                parcel = getPartitionId();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                parcel = getExtendedAddress();
                parcel1.writeNoException();
                parcel1.writeByteArray(parcel);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                parcel = getLowpanIdentity();
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

            case 17: // '\021'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                parcel = getLowpanCredential();
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

            case 18: // '\022'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                parcel = getLinkAddresses();
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 19: // '\023'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                parcel = getLinkNetworks();
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 20: // '\024'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                if(parcel.readInt() != 0)
                    parcel = (LowpanProvision)LowpanProvision.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                join(parcel);
                parcel1.writeNoException();
                return true;

            case 21: // '\025'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                if(parcel.readInt() != 0)
                    parcel = (LowpanProvision)LowpanProvision.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                form(parcel);
                parcel1.writeNoException();
                return true;

            case 22: // '\026'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                if(parcel.readInt() != 0)
                    parcel = (LowpanProvision)LowpanProvision.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                attach(parcel);
                parcel1.writeNoException();
                return true;

            case 23: // '\027'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                leave();
                parcel1.writeNoException();
                return true;

            case 24: // '\030'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                reset();
                parcel1.writeNoException();
                return true;

            case 25: // '\031'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                if(parcel.readInt() != 0)
                    parcel = (LowpanBeaconInfo)LowpanBeaconInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                startCommissioningSession(parcel);
                parcel1.writeNoException();
                return true;

            case 26: // '\032'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                closeCommissioningSession();
                parcel1.writeNoException();
                return true;

            case 27: // '\033'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                sendToCommissioner(parcel.createByteArray());
                return true;

            case 28: // '\034'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                beginLowPower();
                parcel1.writeNoException();
                return true;

            case 29: // '\035'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                pollForData();
                return true;

            case 30: // '\036'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                onHostWake();
                return true;

            case 31: // '\037'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                addListener(ILowpanInterfaceListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 32: // ' '
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                removeListener(ILowpanInterfaceListener.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 33: // '!'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                startNetScan(parcel.readHashMap(getClass().getClassLoader()), ILowpanNetScanCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 34: // '"'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                stopNetScan();
                return true;

            case 35: // '#'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                startEnergyScan(parcel.readHashMap(getClass().getClassLoader()), ILowpanEnergyScanCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 36: // '$'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                stopEnergyScan();
                return true;

            case 37: // '%'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                IpPrefix ipprefix;
                if(parcel.readInt() != 0)
                    ipprefix = (IpPrefix)IpPrefix.CREATOR.createFromParcel(parcel);
                else
                    ipprefix = null;
                addOnMeshPrefix(ipprefix, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 38: // '&'
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                if(parcel.readInt() != 0)
                    parcel = (IpPrefix)IpPrefix.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                removeOnMeshPrefix(parcel);
                return true;

            case 39: // '\''
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                IpPrefix ipprefix1;
                if(parcel.readInt() != 0)
                    ipprefix1 = (IpPrefix)IpPrefix.CREATOR.createFromParcel(parcel);
                else
                    ipprefix1 = null;
                addExternalRoute(ipprefix1, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 40: // '('
                parcel.enforceInterface("android.net.lowpan.ILowpanInterface");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (IpPrefix)IpPrefix.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            removeExternalRoute(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.net.lowpan.ILowpanInterface";
        static final int TRANSACTION_addExternalRoute = 39;
        static final int TRANSACTION_addListener = 31;
        static final int TRANSACTION_addOnMeshPrefix = 37;
        static final int TRANSACTION_attach = 22;
        static final int TRANSACTION_beginLowPower = 28;
        static final int TRANSACTION_closeCommissioningSession = 26;
        static final int TRANSACTION_form = 21;
        static final int TRANSACTION_getDriverVersion = 3;
        static final int TRANSACTION_getExtendedAddress = 15;
        static final int TRANSACTION_getLinkAddresses = 18;
        static final int TRANSACTION_getLinkNetworks = 19;
        static final int TRANSACTION_getLowpanCredential = 17;
        static final int TRANSACTION_getLowpanIdentity = 16;
        static final int TRANSACTION_getMacAddress = 6;
        static final int TRANSACTION_getName = 1;
        static final int TRANSACTION_getNcpVersion = 2;
        static final int TRANSACTION_getPartitionId = 14;
        static final int TRANSACTION_getRole = 13;
        static final int TRANSACTION_getState = 12;
        static final int TRANSACTION_getSupportedChannels = 4;
        static final int TRANSACTION_getSupportedNetworkTypes = 5;
        static final int TRANSACTION_isCommissioned = 10;
        static final int TRANSACTION_isConnected = 11;
        static final int TRANSACTION_isEnabled = 7;
        static final int TRANSACTION_isUp = 9;
        static final int TRANSACTION_join = 20;
        static final int TRANSACTION_leave = 23;
        static final int TRANSACTION_onHostWake = 30;
        static final int TRANSACTION_pollForData = 29;
        static final int TRANSACTION_removeExternalRoute = 40;
        static final int TRANSACTION_removeListener = 32;
        static final int TRANSACTION_removeOnMeshPrefix = 38;
        static final int TRANSACTION_reset = 24;
        static final int TRANSACTION_sendToCommissioner = 27;
        static final int TRANSACTION_setEnabled = 8;
        static final int TRANSACTION_startCommissioningSession = 25;
        static final int TRANSACTION_startEnergyScan = 35;
        static final int TRANSACTION_startNetScan = 33;
        static final int TRANSACTION_stopEnergyScan = 36;
        static final int TRANSACTION_stopNetScan = 34;

        public Stub()
        {
            attachInterface(this, "android.net.lowpan.ILowpanInterface");
        }
    }

    private static class Stub.Proxy
        implements ILowpanInterface
    {

        public void addExternalRoute(IpPrefix ipprefix, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
            if(ipprefix == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            ipprefix.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(39, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ipprefix;
            parcel1.recycle();
            parcel.recycle();
            throw ipprefix;
        }

        public void addListener(ILowpanInterfaceListener ilowpaninterfacelistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
            if(ilowpaninterfacelistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ilowpaninterfacelistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(31, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ilowpaninterfacelistener;
            parcel1.recycle();
            parcel.recycle();
            throw ilowpaninterfacelistener;
        }

        public void addOnMeshPrefix(IpPrefix ipprefix, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
            if(ipprefix == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            ipprefix.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(37, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ipprefix;
            parcel1.recycle();
            parcel.recycle();
            throw ipprefix;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void attach(LowpanProvision lowpanprovision)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
            if(lowpanprovision == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            lowpanprovision.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(22, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            lowpanprovision;
            parcel1.recycle();
            parcel.recycle();
            throw lowpanprovision;
        }

        public void beginLowPower()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
            mRemote.transact(28, parcel, parcel1, 0);
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

        public void closeCommissioningSession()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
            mRemote.transact(26, parcel, parcel1, 0);
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

        public void form(LowpanProvision lowpanprovision)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
            if(lowpanprovision == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            lowpanprovision.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(21, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            lowpanprovision;
            parcel1.recycle();
            parcel.recycle();
            throw lowpanprovision;
        }

        public String getDriverVersion()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
            mRemote.transact(3, parcel, parcel1, 0);
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

        public byte[] getExtendedAddress()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            byte abyte0[];
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
            mRemote.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            abyte0 = parcel1.createByteArray();
            parcel1.recycle();
            parcel.recycle();
            return abyte0;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.net.lowpan.ILowpanInterface";
        }

        public String[] getLinkAddresses()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
            mRemote.transact(18, parcel, parcel1, 0);
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

        public IpPrefix[] getLinkNetworks()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            IpPrefix aipprefix[];
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
            mRemote.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            aipprefix = (IpPrefix[])parcel1.createTypedArray(IpPrefix.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return aipprefix;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public LowpanCredential getLowpanCredential()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            LowpanCredential lowpancredential = (LowpanCredential)LowpanCredential.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return lowpancredential;
_L2:
            lowpancredential = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public LowpanIdentity getLowpanIdentity()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            LowpanIdentity lowpanidentity = (LowpanIdentity)LowpanIdentity.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return lowpanidentity;
_L2:
            lowpanidentity = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public byte[] getMacAddress()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            byte abyte0[];
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            abyte0 = parcel1.createByteArray();
            parcel1.recycle();
            parcel.recycle();
            return abyte0;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getName()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
            mRemote.transact(1, parcel, parcel1, 0);
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

        public String getNcpVersion()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
            mRemote.transact(2, parcel, parcel1, 0);
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

        public String getPartitionId()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
            mRemote.transact(14, parcel, parcel1, 0);
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

        public String getRole()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
            mRemote.transact(13, parcel, parcel1, 0);
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

        public String getState()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
            mRemote.transact(12, parcel, parcel1, 0);
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

        public LowpanChannelInfo[] getSupportedChannels()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            LowpanChannelInfo alowpanchannelinfo[];
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            alowpanchannelinfo = (LowpanChannelInfo[])parcel1.createTypedArray(LowpanChannelInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return alowpanchannelinfo;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String[] getSupportedNetworkTypes()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
            mRemote.transact(5, parcel, parcel1, 0);
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

        public boolean isCommissioned()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
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

        public boolean isConnected()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
            mRemote.transact(11, parcel, parcel1, 0);
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

        public boolean isEnabled()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
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

        public boolean isUp()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
            mRemote.transact(9, parcel, parcel1, 0);
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

        public void join(LowpanProvision lowpanprovision)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
            if(lowpanprovision == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            lowpanprovision.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(20, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            lowpanprovision;
            parcel1.recycle();
            parcel.recycle();
            throw lowpanprovision;
        }

        public void leave()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
            mRemote.transact(23, parcel, parcel1, 0);
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

        public void onHostWake()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
            mRemote.transact(30, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void pollForData()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
            mRemote.transact(29, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void removeExternalRoute(IpPrefix ipprefix)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
            if(ipprefix == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            ipprefix.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(40, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ipprefix;
            parcel.recycle();
            throw ipprefix;
        }

        public void removeListener(ILowpanInterfaceListener ilowpaninterfacelistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
            if(ilowpaninterfacelistener == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = ilowpaninterfacelistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(32, parcel, null, 1);
            parcel.recycle();
            return;
            ilowpaninterfacelistener;
            parcel.recycle();
            throw ilowpaninterfacelistener;
        }

        public void removeOnMeshPrefix(IpPrefix ipprefix)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
            if(ipprefix == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            ipprefix.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(38, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ipprefix;
            parcel.recycle();
            throw ipprefix;
        }

        public void reset()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
            mRemote.transact(24, parcel, parcel1, 0);
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

        public void sendToCommissioner(byte abyte0[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
            parcel.writeByteArray(abyte0);
            mRemote.transact(27, parcel, null, 1);
            parcel.recycle();
            return;
            abyte0;
            parcel.recycle();
            throw abyte0;
        }

        public void setEnabled(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(8, parcel, parcel1, 0);
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

        public void startCommissioningSession(LowpanBeaconInfo lowpanbeaconinfo)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
            if(lowpanbeaconinfo == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            lowpanbeaconinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(25, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            lowpanbeaconinfo;
            parcel1.recycle();
            parcel.recycle();
            throw lowpanbeaconinfo;
        }

        public void startEnergyScan(Map map, ILowpanEnergyScanCallback ilowpanenergyscancallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
            parcel.writeMap(map);
            map = obj;
            if(ilowpanenergyscancallback == null)
                break MISSING_BLOCK_LABEL_38;
            map = ilowpanenergyscancallback.asBinder();
            parcel.writeStrongBinder(map);
            mRemote.transact(35, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            map;
            parcel1.recycle();
            parcel.recycle();
            throw map;
        }

        public void startNetScan(Map map, ILowpanNetScanCallback ilowpannetscancallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
            parcel.writeMap(map);
            map = obj;
            if(ilowpannetscancallback == null)
                break MISSING_BLOCK_LABEL_38;
            map = ilowpannetscancallback.asBinder();
            parcel.writeStrongBinder(map);
            mRemote.transact(33, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            map;
            parcel1.recycle();
            parcel.recycle();
            throw map;
        }

        public void stopEnergyScan()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
            mRemote.transact(36, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void stopNetScan()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanInterface");
            mRemote.transact(34, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void addExternalRoute(IpPrefix ipprefix, int i)
        throws RemoteException;

    public abstract void addListener(ILowpanInterfaceListener ilowpaninterfacelistener)
        throws RemoteException;

    public abstract void addOnMeshPrefix(IpPrefix ipprefix, int i)
        throws RemoteException;

    public abstract void attach(LowpanProvision lowpanprovision)
        throws RemoteException;

    public abstract void beginLowPower()
        throws RemoteException;

    public abstract void closeCommissioningSession()
        throws RemoteException;

    public abstract void form(LowpanProvision lowpanprovision)
        throws RemoteException;

    public abstract String getDriverVersion()
        throws RemoteException;

    public abstract byte[] getExtendedAddress()
        throws RemoteException;

    public abstract String[] getLinkAddresses()
        throws RemoteException;

    public abstract IpPrefix[] getLinkNetworks()
        throws RemoteException;

    public abstract LowpanCredential getLowpanCredential()
        throws RemoteException;

    public abstract LowpanIdentity getLowpanIdentity()
        throws RemoteException;

    public abstract byte[] getMacAddress()
        throws RemoteException;

    public abstract String getName()
        throws RemoteException;

    public abstract String getNcpVersion()
        throws RemoteException;

    public abstract String getPartitionId()
        throws RemoteException;

    public abstract String getRole()
        throws RemoteException;

    public abstract String getState()
        throws RemoteException;

    public abstract LowpanChannelInfo[] getSupportedChannels()
        throws RemoteException;

    public abstract String[] getSupportedNetworkTypes()
        throws RemoteException;

    public abstract boolean isCommissioned()
        throws RemoteException;

    public abstract boolean isConnected()
        throws RemoteException;

    public abstract boolean isEnabled()
        throws RemoteException;

    public abstract boolean isUp()
        throws RemoteException;

    public abstract void join(LowpanProvision lowpanprovision)
        throws RemoteException;

    public abstract void leave()
        throws RemoteException;

    public abstract void onHostWake()
        throws RemoteException;

    public abstract void pollForData()
        throws RemoteException;

    public abstract void removeExternalRoute(IpPrefix ipprefix)
        throws RemoteException;

    public abstract void removeListener(ILowpanInterfaceListener ilowpaninterfacelistener)
        throws RemoteException;

    public abstract void removeOnMeshPrefix(IpPrefix ipprefix)
        throws RemoteException;

    public abstract void reset()
        throws RemoteException;

    public abstract void sendToCommissioner(byte abyte0[])
        throws RemoteException;

    public abstract void setEnabled(boolean flag)
        throws RemoteException;

    public abstract void startCommissioningSession(LowpanBeaconInfo lowpanbeaconinfo)
        throws RemoteException;

    public abstract void startEnergyScan(Map map, ILowpanEnergyScanCallback ilowpanenergyscancallback)
        throws RemoteException;

    public abstract void startNetScan(Map map, ILowpanNetScanCallback ilowpannetscancallback)
        throws RemoteException;

    public abstract void stopEnergyScan()
        throws RemoteException;

    public abstract void stopNetScan()
        throws RemoteException;

    public static final int ERROR_ALREADY = 9;
    public static final int ERROR_BUSY = 8;
    public static final int ERROR_CANCELED = 10;
    public static final int ERROR_DISABLED = 3;
    public static final int ERROR_FEATURE_NOT_SUPPORTED = 11;
    public static final int ERROR_FORM_FAILED_AT_SCAN = 15;
    public static final int ERROR_INVALID_ARGUMENT = 2;
    public static final int ERROR_IO_FAILURE = 6;
    public static final int ERROR_JOIN_FAILED_AT_AUTH = 14;
    public static final int ERROR_JOIN_FAILED_AT_SCAN = 13;
    public static final int ERROR_JOIN_FAILED_UNKNOWN = 12;
    public static final int ERROR_NCP_PROBLEM = 7;
    public static final int ERROR_TIMEOUT = 5;
    public static final int ERROR_UNSPECIFIED = 1;
    public static final int ERROR_WRONG_STATE = 4;
    public static final String KEY_CHANNEL_MASK = "android.net.lowpan.property.CHANNEL_MASK";
    public static final String KEY_MAX_TX_POWER = "android.net.lowpan.property.MAX_TX_POWER";
    public static final String NETWORK_TYPE_THREAD_V1 = "org.threadgroup.thread.v1";
    public static final String NETWORK_TYPE_UNKNOWN = "unknown";
    public static final String PERM_ACCESS_LOWPAN_STATE = "android.permission.ACCESS_LOWPAN_STATE";
    public static final String PERM_CHANGE_LOWPAN_STATE = "android.permission.CHANGE_LOWPAN_STATE";
    public static final String PERM_READ_LOWPAN_CREDENTIAL = "android.permission.READ_LOWPAN_CREDENTIAL";
    public static final String ROLE_COORDINATOR = "coordinator";
    public static final String ROLE_DETACHED = "detached";
    public static final String ROLE_END_DEVICE = "end-device";
    public static final String ROLE_LEADER = "leader";
    public static final String ROLE_ROUTER = "router";
    public static final String ROLE_SLEEPY_END_DEVICE = "sleepy-end-device";
    public static final String ROLE_SLEEPY_ROUTER = "sleepy-router";
    public static final String STATE_ATTACHED = "attached";
    public static final String STATE_ATTACHING = "attaching";
    public static final String STATE_COMMISSIONING = "commissioning";
    public static final String STATE_FAULT = "fault";
    public static final String STATE_OFFLINE = "offline";
}
