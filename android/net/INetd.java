// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.*;
import java.io.FileDescriptor;

// Referenced classes of package android.net:
//            UidRange

public interface INetd
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements INetd
    {

        public static INetd asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.net.INetd");
            if(iinterface != null && (iinterface instanceof INetd))
                return (INetd)iinterface;
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
                parcel1.writeString("android.net.INetd");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.net.INetd");
                boolean flag = isAlive();
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.net.INetd");
                String s = parcel.readString();
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                flag1 = firewallReplaceUidChain(s, flag1, parcel.createIntArray());
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.net.INetd");
                boolean flag2;
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                flag2 = bandwidthEnableDataSaver(flag2);
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.net.INetd");
                boolean flag3;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                networkRejectNonSecureVpn(flag3, (UidRange[])parcel.createTypedArray(UidRange.CREATOR));
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.net.INetd");
                socketDestroy((UidRange[])parcel.createTypedArray(UidRange.CREATOR), parcel.createIntArray());
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.net.INetd");
                setResolverConfiguration(parcel.readInt(), parcel.createStringArray(), parcel.createStringArray(), parcel.createIntArray());
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.net.INetd");
                i = parcel.readInt();
                j = parcel.readInt();
                String as[];
                String as1[];
                int ai[];
                if(j < 0)
                    as = null;
                else
                    as = new String[j];
                j = parcel.readInt();
                if(j < 0)
                    as1 = null;
                else
                    as1 = new String[j];
                j = parcel.readInt();
                if(j < 0)
                    ai = null;
                else
                    ai = new int[j];
                j = parcel.readInt();
                if(j < 0)
                    parcel = null;
                else
                    parcel = new int[j];
                getResolverInfo(i, as, as1, ai, parcel);
                parcel1.writeNoException();
                parcel1.writeStringArray(as);
                parcel1.writeStringArray(as1);
                parcel1.writeIntArray(ai);
                parcel1.writeIntArray(parcel);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.net.INetd");
                addPrivateDnsServer(parcel.readString(), parcel.readInt(), parcel.readString(), parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.net.INetd");
                removePrivateDnsServer(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.net.INetd");
                boolean flag4 = tetherApplyDnsInterfaces();
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.net.INetd");
                interfaceAddAddress(parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.net.INetd");
                interfaceDelAddress(parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.net.INetd");
                setProcSysNet(parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.net.INetd");
                i = getMetricsReportingLevel();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.net.INetd");
                setMetricsReportingLevel(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.net.INetd");
                i = ipSecAllocateSpi(parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.net.INetd");
                ipSecAddSecurityAssociation(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readLong(), parcel.readInt(), parcel.readString(), parcel.createByteArray(), parcel.readInt(), parcel.readString(), parcel.createByteArray(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.net.INetd");
                ipSecDeleteSecurityAssociation(parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("android.net.INetd");
                ipSecApplyTransportModeTransform(parcel.readRawFileDescriptor(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 20: // '\024'
                parcel.enforceInterface("android.net.INetd");
                ipSecRemoveTransportModeTransform(parcel.readRawFileDescriptor());
                parcel1.writeNoException();
                return true;

            case 21: // '\025'
                parcel.enforceInterface("android.net.INetd");
                wakeupAddInterface(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 22: // '\026'
                parcel.enforceInterface("android.net.INetd");
                wakeupDelInterface(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 23: // '\027'
                parcel.enforceInterface("android.net.INetd");
                setIPv6AddrGenMode(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.net.INetd";
        static final int TRANSACTION_addPrivateDnsServer = 8;
        static final int TRANSACTION_bandwidthEnableDataSaver = 3;
        static final int TRANSACTION_firewallReplaceUidChain = 2;
        static final int TRANSACTION_getMetricsReportingLevel = 14;
        static final int TRANSACTION_getResolverInfo = 7;
        static final int TRANSACTION_interfaceAddAddress = 11;
        static final int TRANSACTION_interfaceDelAddress = 12;
        static final int TRANSACTION_ipSecAddSecurityAssociation = 17;
        static final int TRANSACTION_ipSecAllocateSpi = 16;
        static final int TRANSACTION_ipSecApplyTransportModeTransform = 19;
        static final int TRANSACTION_ipSecDeleteSecurityAssociation = 18;
        static final int TRANSACTION_ipSecRemoveTransportModeTransform = 20;
        static final int TRANSACTION_isAlive = 1;
        static final int TRANSACTION_networkRejectNonSecureVpn = 4;
        static final int TRANSACTION_removePrivateDnsServer = 9;
        static final int TRANSACTION_setIPv6AddrGenMode = 23;
        static final int TRANSACTION_setMetricsReportingLevel = 15;
        static final int TRANSACTION_setProcSysNet = 13;
        static final int TRANSACTION_setResolverConfiguration = 6;
        static final int TRANSACTION_socketDestroy = 5;
        static final int TRANSACTION_tetherApplyDnsInterfaces = 10;
        static final int TRANSACTION_wakeupAddInterface = 21;
        static final int TRANSACTION_wakeupDelInterface = 22;

        public Stub()
        {
            attachInterface(this, "android.net.INetd");
        }
    }

    private static class Stub.Proxy
        implements INetd
    {

        public void addPrivateDnsServer(String s, int i, String s1, String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetd");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeString(s1);
            parcel.writeStringArray(as);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public boolean bandwidthEnableDataSaver(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetd");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
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

        public boolean firewallReplaceUidChain(String s, boolean flag, int ai[])
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetd");
            parcel.writeString(s);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            parcel.writeIntArray(ai);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
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

        public String getInterfaceDescriptor()
        {
            return "android.net.INetd";
        }

        public int getMetricsReportingLevel()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.INetd");
            mRemote.transact(14, parcel, parcel1, 0);
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

        public void getResolverInfo(int i, String as[], String as1[], int ai[], int ai1[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetd");
            parcel.writeInt(i);
            if(as != null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(-1);
_L7:
            if(as1 != null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(-1);
_L8:
            if(ai != null) goto _L6; else goto _L5
_L5:
            parcel.writeInt(-1);
_L9:
            if(ai1 != null)
                break MISSING_BLOCK_LABEL_168;
            parcel.writeInt(-1);
_L10:
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.readStringArray(as);
            parcel1.readStringArray(as1);
            parcel1.readIntArray(ai);
            parcel1.readIntArray(ai1);
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(as.length);
              goto _L7
            as;
            parcel1.recycle();
            parcel.recycle();
            throw as;
_L4:
            parcel.writeInt(as1.length);
              goto _L8
_L6:
            parcel.writeInt(ai.length);
              goto _L9
            parcel.writeInt(ai1.length);
              goto _L10
        }

        public void interfaceAddAddress(String s, String s1, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetd");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void interfaceDelAddress(String s, String s1, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetd");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void ipSecAddSecurityAssociation(int i, int j, int k, String s, String s1, long l, 
                int i1, String s2, byte abyte0[], int j1, String s3, byte abyte1[], int k1, 
                int l1, int i2, int j2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetd");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeLong(l);
            parcel.writeInt(i1);
            parcel.writeString(s2);
            parcel.writeByteArray(abyte0);
            parcel.writeInt(j1);
            parcel.writeString(s3);
            parcel.writeByteArray(abyte1);
            parcel.writeInt(k1);
            parcel.writeInt(l1);
            parcel.writeInt(i2);
            parcel.writeInt(j2);
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int ipSecAllocateSpi(int i, int j, String s, String s1, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetd");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(k);
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void ipSecApplyTransportModeTransform(FileDescriptor filedescriptor, int i, int j, String s, String s1, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetd");
            parcel.writeRawFileDescriptor(filedescriptor);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(k);
            mRemote.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            filedescriptor;
            parcel1.recycle();
            parcel.recycle();
            throw filedescriptor;
        }

        public void ipSecDeleteSecurityAssociation(int i, int j, String s, String s1, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetd");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(k);
            mRemote.transact(18, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void ipSecRemoveTransportModeTransform(FileDescriptor filedescriptor)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetd");
            parcel.writeRawFileDescriptor(filedescriptor);
            mRemote.transact(20, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            filedescriptor;
            parcel1.recycle();
            parcel.recycle();
            throw filedescriptor;
        }

        public boolean isAlive()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.INetd");
            mRemote.transact(1, parcel, parcel1, 0);
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

        public void networkRejectNonSecureVpn(boolean flag, UidRange auidrange[])
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetd");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            parcel.writeTypedArray(auidrange, 0);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            auidrange;
            parcel1.recycle();
            parcel.recycle();
            throw auidrange;
        }

        public void removePrivateDnsServer(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetd");
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

        public void setIPv6AddrGenMode(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetd");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(23, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setMetricsReportingLevel(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetd");
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

        public void setProcSysNet(int i, int j, String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetd");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
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

        public void setResolverConfiguration(int i, String as[], String as1[], int ai[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetd");
            parcel.writeInt(i);
            parcel.writeStringArray(as);
            parcel.writeStringArray(as1);
            parcel.writeIntArray(ai);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            as;
            parcel1.recycle();
            parcel.recycle();
            throw as;
        }

        public void socketDestroy(UidRange auidrange[], int ai[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetd");
            parcel.writeTypedArray(auidrange, 0);
            parcel.writeIntArray(ai);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            auidrange;
            parcel1.recycle();
            parcel.recycle();
            throw auidrange;
        }

        public boolean tetherApplyDnsInterfaces()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.INetd");
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

        public void wakeupAddInterface(String s, String s1, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetd");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(21, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void wakeupDelInterface(String s, String s1, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetd");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(22, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
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


    public abstract void addPrivateDnsServer(String s, int i, String s1, String as[])
        throws RemoteException;

    public abstract boolean bandwidthEnableDataSaver(boolean flag)
        throws RemoteException;

    public abstract boolean firewallReplaceUidChain(String s, boolean flag, int ai[])
        throws RemoteException;

    public abstract int getMetricsReportingLevel()
        throws RemoteException;

    public abstract void getResolverInfo(int i, String as[], String as1[], int ai[], int ai1[])
        throws RemoteException;

    public abstract void interfaceAddAddress(String s, String s1, int i)
        throws RemoteException;

    public abstract void interfaceDelAddress(String s, String s1, int i)
        throws RemoteException;

    public abstract void ipSecAddSecurityAssociation(int i, int j, int k, String s, String s1, long l, 
            int i1, String s2, byte abyte0[], int j1, String s3, byte abyte1[], int k1, 
            int l1, int i2, int j2)
        throws RemoteException;

    public abstract int ipSecAllocateSpi(int i, int j, String s, String s1, int k)
        throws RemoteException;

    public abstract void ipSecApplyTransportModeTransform(FileDescriptor filedescriptor, int i, int j, String s, String s1, int k)
        throws RemoteException;

    public abstract void ipSecDeleteSecurityAssociation(int i, int j, String s, String s1, int k)
        throws RemoteException;

    public abstract void ipSecRemoveTransportModeTransform(FileDescriptor filedescriptor)
        throws RemoteException;

    public abstract boolean isAlive()
        throws RemoteException;

    public abstract void networkRejectNonSecureVpn(boolean flag, UidRange auidrange[])
        throws RemoteException;

    public abstract void removePrivateDnsServer(String s)
        throws RemoteException;

    public abstract void setIPv6AddrGenMode(String s, int i)
        throws RemoteException;

    public abstract void setMetricsReportingLevel(int i)
        throws RemoteException;

    public abstract void setProcSysNet(int i, int j, String s, String s1, String s2)
        throws RemoteException;

    public abstract void setResolverConfiguration(int i, String as[], String as1[], int ai[])
        throws RemoteException;

    public abstract void socketDestroy(UidRange auidrange[], int ai[])
        throws RemoteException;

    public abstract boolean tetherApplyDnsInterfaces()
        throws RemoteException;

    public abstract void wakeupAddInterface(String s, String s1, int i, int j)
        throws RemoteException;

    public abstract void wakeupDelInterface(String s, String s1, int i, int j)
        throws RemoteException;

    public static final int CONF = 1;
    public static final int IPV4 = 4;
    public static final int IPV6 = 6;
    public static final int IPV6_ADDR_GEN_MODE_DEFAULT = 0;
    public static final int IPV6_ADDR_GEN_MODE_EUI64 = 0;
    public static final int IPV6_ADDR_GEN_MODE_NONE = 1;
    public static final int IPV6_ADDR_GEN_MODE_RANDOM = 3;
    public static final int IPV6_ADDR_GEN_MODE_STABLE_PRIVACY = 2;
    public static final int NEIGH = 2;
    public static final int PRIVATE_DNS_BAD_ADDRESS = 1;
    public static final int PRIVATE_DNS_BAD_FINGERPRINT = 4;
    public static final int PRIVATE_DNS_BAD_PORT = 2;
    public static final int PRIVATE_DNS_SUCCESS = 0;
    public static final int PRIVATE_DNS_UNKNOWN_ALGORITHM = 3;
    public static final int RESOLVER_PARAMS_COUNT = 4;
    public static final int RESOLVER_PARAMS_MAX_SAMPLES = 3;
    public static final int RESOLVER_PARAMS_MIN_SAMPLES = 2;
    public static final int RESOLVER_PARAMS_SAMPLE_VALIDITY = 0;
    public static final int RESOLVER_PARAMS_SUCCESS_THRESHOLD = 1;
    public static final int RESOLVER_STATS_COUNT = 7;
    public static final int RESOLVER_STATS_ERRORS = 1;
    public static final int RESOLVER_STATS_INTERNAL_ERRORS = 3;
    public static final int RESOLVER_STATS_LAST_SAMPLE_TIME = 5;
    public static final int RESOLVER_STATS_RTT_AVG = 4;
    public static final int RESOLVER_STATS_SUCCESSES = 0;
    public static final int RESOLVER_STATS_TIMEOUTS = 2;
    public static final int RESOLVER_STATS_USABLE = 6;
}
