// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security;

import android.content.pm.StringParceledListSlice;
import android.os.*;
import java.util.List;

public interface IKeyChainService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IKeyChainService
    {

        public static IKeyChainService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.security.IKeyChainService");
            if(iinterface != null && (iinterface instanceof IKeyChainService))
                return (IKeyChainService)iinterface;
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
            boolean flag8;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.security.IKeyChainService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.security.IKeyChainService");
                parcel = requestPrivateKey(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.security.IKeyChainService");
                parcel = getCertificate(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeByteArray(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.security.IKeyChainService");
                parcel = getCaCertificates(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeByteArray(parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.security.IKeyChainService");
                parcel = installCaCertificate(parcel.createByteArray());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.security.IKeyChainService");
                boolean flag = installKeyPair(parcel.createByteArray(), parcel.createByteArray(), parcel.createByteArray(), parcel.readString());
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.security.IKeyChainService");
                boolean flag1 = removeKeyPair(parcel.readString());
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.security.IKeyChainService");
                boolean flag2 = deleteCaCertificate(parcel.readString());
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.security.IKeyChainService");
                boolean flag3 = reset();
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.security.IKeyChainService");
                parcel = getUserCaAliases();
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
                parcel.enforceInterface("android.security.IKeyChainService");
                parcel = getSystemCaAliases();
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
                parcel.enforceInterface("android.security.IKeyChainService");
                boolean flag4 = containsCaAlias(parcel.readString());
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.security.IKeyChainService");
                String s = parcel.readString();
                boolean flag5;
                if(parcel.readInt() != 0)
                    flag5 = true;
                else
                    flag5 = false;
                parcel = getEncodedCaCertificate(s, flag5);
                parcel1.writeNoException();
                parcel1.writeByteArray(parcel);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.security.IKeyChainService");
                String s1 = parcel.readString();
                boolean flag6;
                if(parcel.readInt() != 0)
                    flag6 = true;
                else
                    flag6 = false;
                parcel = getCaCertificateChainAliases(s1, flag6);
                parcel1.writeNoException();
                parcel1.writeStringList(parcel);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.security.IKeyChainService");
                i = parcel.readInt();
                String s2 = parcel.readString();
                boolean flag7;
                if(parcel.readInt() != 0)
                    flag7 = true;
                else
                    flag7 = false;
                setGrant(i, s2, flag7);
                parcel1.writeNoException();
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.security.IKeyChainService");
                flag8 = hasGrant(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                break;
            }
            if(flag8)
                i = 1;
            else
                i = 0;
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "android.security.IKeyChainService";
        static final int TRANSACTION_containsCaAlias = 11;
        static final int TRANSACTION_deleteCaCertificate = 7;
        static final int TRANSACTION_getCaCertificateChainAliases = 13;
        static final int TRANSACTION_getCaCertificates = 3;
        static final int TRANSACTION_getCertificate = 2;
        static final int TRANSACTION_getEncodedCaCertificate = 12;
        static final int TRANSACTION_getSystemCaAliases = 10;
        static final int TRANSACTION_getUserCaAliases = 9;
        static final int TRANSACTION_hasGrant = 15;
        static final int TRANSACTION_installCaCertificate = 4;
        static final int TRANSACTION_installKeyPair = 5;
        static final int TRANSACTION_removeKeyPair = 6;
        static final int TRANSACTION_requestPrivateKey = 1;
        static final int TRANSACTION_reset = 8;
        static final int TRANSACTION_setGrant = 14;

        public Stub()
        {
            attachInterface(this, "android.security.IKeyChainService");
        }
    }

    private static class Stub.Proxy
        implements IKeyChainService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public boolean containsCaAlias(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.security.IKeyChainService");
            parcel.writeString(s);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean deleteCaCertificate(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.security.IKeyChainService");
            parcel.writeString(s);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public List getCaCertificateChainAliases(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeyChainService");
            parcel.writeString(s);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createStringArrayList();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public byte[] getCaCertificates(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeyChainService");
            parcel.writeString(s);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createByteArray();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public byte[] getCertificate(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeyChainService");
            parcel.writeString(s);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createByteArray();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public byte[] getEncodedCaCertificate(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeyChainService");
            parcel.writeString(s);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createByteArray();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String getInterfaceDescriptor()
        {
            return "android.security.IKeyChainService";
        }

        public StringParceledListSlice getSystemCaAliases()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeyChainService");
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            StringParceledListSlice stringparceledlistslice = (StringParceledListSlice)StringParceledListSlice.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return stringparceledlistslice;
_L2:
            stringparceledlistslice = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public StringParceledListSlice getUserCaAliases()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeyChainService");
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            StringParceledListSlice stringparceledlistslice = (StringParceledListSlice)StringParceledListSlice.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return stringparceledlistslice;
_L2:
            stringparceledlistslice = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean hasGrant(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeyChainService");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(15, parcel, parcel1, 0);
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

        public String installCaCertificate(byte abyte0[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeyChainService");
            parcel.writeByteArray(abyte0);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            abyte0 = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return abyte0;
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        public boolean installKeyPair(byte abyte0[], byte abyte1[], byte abyte2[], String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.security.IKeyChainService");
            parcel.writeByteArray(abyte0);
            parcel.writeByteArray(abyte1);
            parcel.writeByteArray(abyte2);
            parcel.writeString(s);
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
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        public boolean removeKeyPair(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.security.IKeyChainService");
            parcel.writeString(s);
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

        public String requestPrivateKey(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeyChainService");
            parcel.writeString(s);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean reset()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.security.IKeyChainService");
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

        public void setGrant(int i, String s, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeyChainService");
            parcel.writeInt(i);
            parcel.writeString(s);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(14, parcel, parcel1, 0);
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


    public abstract boolean containsCaAlias(String s)
        throws RemoteException;

    public abstract boolean deleteCaCertificate(String s)
        throws RemoteException;

    public abstract List getCaCertificateChainAliases(String s, boolean flag)
        throws RemoteException;

    public abstract byte[] getCaCertificates(String s)
        throws RemoteException;

    public abstract byte[] getCertificate(String s)
        throws RemoteException;

    public abstract byte[] getEncodedCaCertificate(String s, boolean flag)
        throws RemoteException;

    public abstract StringParceledListSlice getSystemCaAliases()
        throws RemoteException;

    public abstract StringParceledListSlice getUserCaAliases()
        throws RemoteException;

    public abstract boolean hasGrant(int i, String s)
        throws RemoteException;

    public abstract String installCaCertificate(byte abyte0[])
        throws RemoteException;

    public abstract boolean installKeyPair(byte abyte0[], byte abyte1[], byte abyte2[], String s)
        throws RemoteException;

    public abstract boolean removeKeyPair(String s)
        throws RemoteException;

    public abstract String requestPrivateKey(String s)
        throws RemoteException;

    public abstract boolean reset()
        throws RemoteException;

    public abstract void setGrant(int i, String s, boolean flag)
        throws RemoteException;
}
