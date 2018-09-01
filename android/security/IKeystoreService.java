// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security;

import android.os.*;
import android.security.keymaster.ExportResult;
import android.security.keymaster.KeyCharacteristics;
import android.security.keymaster.KeymasterArguments;
import android.security.keymaster.KeymasterBlob;
import android.security.keymaster.KeymasterCertificateChain;
import android.security.keymaster.OperationResult;

// Referenced classes of package android.security:
//            KeystoreArguments

public interface IKeystoreService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IKeystoreService
    {

        public static IKeystoreService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.security.IKeystoreService");
            if(iinterface != null && (iinterface instanceof IKeystoreService))
                return (IKeystoreService)iinterface;
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
                parcel1.writeString("android.security.IKeystoreService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.security.IKeystoreService");
                i = getState(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.security.IKeystoreService");
                parcel = get(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeByteArray(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.security.IKeystoreService");
                i = insert(parcel.readString(), parcel.createByteArray(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.security.IKeystoreService");
                i = del(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.security.IKeystoreService");
                i = exist(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.security.IKeystoreService");
                parcel = list(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.security.IKeystoreService");
                i = reset();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.security.IKeystoreService");
                i = onUserPasswordChanged(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.security.IKeystoreService");
                i = lock(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.security.IKeystoreService");
                i = unlock(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.security.IKeystoreService");
                i = isEmpty(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.security.IKeystoreService");
                String s = parcel.readString();
                i = parcel.readInt();
                int k = parcel.readInt();
                j = parcel.readInt();
                int i1 = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (KeystoreArguments)KeystoreArguments.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = generate(s, i, k, j, i1, parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.security.IKeystoreService");
                i = import_key(parcel.readString(), parcel.createByteArray(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.security.IKeystoreService");
                parcel = sign(parcel.readString(), parcel.createByteArray());
                parcel1.writeNoException();
                parcel1.writeByteArray(parcel);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.security.IKeystoreService");
                i = verify(parcel.readString(), parcel.createByteArray(), parcel.createByteArray());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.security.IKeystoreService");
                parcel = get_pubkey(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeByteArray(parcel);
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.security.IKeystoreService");
                parcel = grant(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.security.IKeystoreService");
                i = ungrant(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 19: // '\023'
                parcel.enforceInterface("android.security.IKeystoreService");
                long l1 = getmtime(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeLong(l1);
                return true;

            case 20: // '\024'
                parcel.enforceInterface("android.security.IKeystoreService");
                i = duplicate(parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 21: // '\025'
                parcel.enforceInterface("android.security.IKeystoreService");
                i = is_hardware_backed(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 22: // '\026'
                parcel.enforceInterface("android.security.IKeystoreService");
                i = clear_uid(parcel.readLong());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 23: // '\027'
                parcel.enforceInterface("android.security.IKeystoreService");
                i = addRngEntropy(parcel.createByteArray());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 24: // '\030'
                parcel.enforceInterface("android.security.IKeystoreService");
                String s2 = parcel.readString();
                KeymasterArguments keymasterarguments;
                byte abyte0[];
                if(parcel.readInt() != 0)
                    keymasterarguments = (KeymasterArguments)KeymasterArguments.CREATOR.createFromParcel(parcel);
                else
                    keymasterarguments = null;
                abyte0 = parcel.createByteArray();
                i = parcel.readInt();
                j = parcel.readInt();
                parcel = new KeyCharacteristics();
                i = generateKey(s2, keymasterarguments, abyte0, i, j, parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 25: // '\031'
                parcel.enforceInterface("android.security.IKeystoreService");
                String s4 = parcel.readString();
                KeymasterBlob keymasterblob;
                KeymasterBlob keymasterblob2;
                if(parcel.readInt() != 0)
                    keymasterblob = (KeymasterBlob)KeymasterBlob.CREATOR.createFromParcel(parcel);
                else
                    keymasterblob = null;
                if(parcel.readInt() != 0)
                    keymasterblob2 = (KeymasterBlob)KeymasterBlob.CREATOR.createFromParcel(parcel);
                else
                    keymasterblob2 = null;
                i = parcel.readInt();
                parcel = new KeyCharacteristics();
                i = getKeyCharacteristics(s4, keymasterblob, keymasterblob2, i, parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 26: // '\032'
                parcel.enforceInterface("android.security.IKeystoreService");
                String s3 = parcel.readString();
                KeymasterArguments keymasterarguments1;
                int l;
                byte abyte1[];
                if(parcel.readInt() != 0)
                    keymasterarguments1 = (KeymasterArguments)KeymasterArguments.CREATOR.createFromParcel(parcel);
                else
                    keymasterarguments1 = null;
                i = parcel.readInt();
                abyte1 = parcel.createByteArray();
                l = parcel.readInt();
                j = parcel.readInt();
                parcel = new KeyCharacteristics();
                i = importKey(s3, keymasterarguments1, i, abyte1, l, j, parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 27: // '\033'
                parcel.enforceInterface("android.security.IKeystoreService");
                String s5 = parcel.readString();
                i = parcel.readInt();
                KeymasterBlob keymasterblob1;
                KeymasterBlob keymasterblob3;
                if(parcel.readInt() != 0)
                    keymasterblob1 = (KeymasterBlob)KeymasterBlob.CREATOR.createFromParcel(parcel);
                else
                    keymasterblob1 = null;
                if(parcel.readInt() != 0)
                    keymasterblob3 = (KeymasterBlob)KeymasterBlob.CREATOR.createFromParcel(parcel);
                else
                    keymasterblob3 = null;
                parcel = exportKey(s5, i, keymasterblob1, keymasterblob3, parcel.readInt());
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

            case 28: // '\034'
                parcel.enforceInterface("android.security.IKeystoreService");
                IBinder ibinder = parcel.readStrongBinder();
                String s6 = parcel.readString();
                i = parcel.readInt();
                KeymasterArguments keymasterarguments2;
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                if(parcel.readInt() != 0)
                    keymasterarguments2 = (KeymasterArguments)KeymasterArguments.CREATOR.createFromParcel(parcel);
                else
                    keymasterarguments2 = null;
                parcel = begin(ibinder, s6, i, flag, keymasterarguments2, parcel.createByteArray(), parcel.readInt());
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

            case 29: // '\035'
                parcel.enforceInterface("android.security.IKeystoreService");
                IBinder ibinder1 = parcel.readStrongBinder();
                KeymasterArguments keymasterarguments3;
                if(parcel.readInt() != 0)
                    keymasterarguments3 = (KeymasterArguments)KeymasterArguments.CREATOR.createFromParcel(parcel);
                else
                    keymasterarguments3 = null;
                parcel = update(ibinder1, keymasterarguments3, parcel.createByteArray());
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

            case 30: // '\036'
                parcel.enforceInterface("android.security.IKeystoreService");
                IBinder ibinder2 = parcel.readStrongBinder();
                KeymasterArguments keymasterarguments4;
                if(parcel.readInt() != 0)
                    keymasterarguments4 = (KeymasterArguments)KeymasterArguments.CREATOR.createFromParcel(parcel);
                else
                    keymasterarguments4 = null;
                parcel = finish(ibinder2, keymasterarguments4, parcel.createByteArray(), parcel.createByteArray());
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

            case 31: // '\037'
                parcel.enforceInterface("android.security.IKeystoreService");
                i = abort(parcel.readStrongBinder());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 32: // ' '
                parcel.enforceInterface("android.security.IKeystoreService");
                boolean flag1 = isOperationAuthorized(parcel.readStrongBinder());
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 33: // '!'
                parcel.enforceInterface("android.security.IKeystoreService");
                i = addAuthToken(parcel.createByteArray());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 34: // '"'
                parcel.enforceInterface("android.security.IKeystoreService");
                i = onUserAdded(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 35: // '#'
                parcel.enforceInterface("android.security.IKeystoreService");
                i = onUserRemoved(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 36: // '$'
                parcel.enforceInterface("android.security.IKeystoreService");
                String s1 = parcel.readString();
                KeymasterCertificateChain keymastercertificatechain1;
                if(parcel.readInt() != 0)
                    parcel = (KeymasterArguments)KeymasterArguments.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                keymastercertificatechain1 = new KeymasterCertificateChain();
                i = attestKey(s1, parcel, keymastercertificatechain1);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                if(keymastercertificatechain1 != null)
                {
                    parcel1.writeInt(1);
                    keymastercertificatechain1.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 37: // '%'
                parcel.enforceInterface("android.security.IKeystoreService");
                KeymasterCertificateChain keymastercertificatechain;
                if(parcel.readInt() != 0)
                    parcel = (KeymasterArguments)KeymasterArguments.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                keymastercertificatechain = new KeymasterCertificateChain();
                i = attestDeviceIds(parcel, keymastercertificatechain);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                if(keymastercertificatechain != null)
                {
                    parcel1.writeInt(1);
                    keymastercertificatechain.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 38: // '&'
                parcel.enforceInterface("android.security.IKeystoreService");
                i = onDeviceOffBody();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.security.IKeystoreService";
        static final int TRANSACTION_abort = 31;
        static final int TRANSACTION_addAuthToken = 33;
        static final int TRANSACTION_addRngEntropy = 23;
        static final int TRANSACTION_attestDeviceIds = 37;
        static final int TRANSACTION_attestKey = 36;
        static final int TRANSACTION_begin = 28;
        static final int TRANSACTION_clear_uid = 22;
        static final int TRANSACTION_del = 4;
        static final int TRANSACTION_duplicate = 20;
        static final int TRANSACTION_exist = 5;
        static final int TRANSACTION_exportKey = 27;
        static final int TRANSACTION_finish = 30;
        static final int TRANSACTION_generate = 12;
        static final int TRANSACTION_generateKey = 24;
        static final int TRANSACTION_get = 2;
        static final int TRANSACTION_getKeyCharacteristics = 25;
        static final int TRANSACTION_getState = 1;
        static final int TRANSACTION_get_pubkey = 16;
        static final int TRANSACTION_getmtime = 19;
        static final int TRANSACTION_grant = 17;
        static final int TRANSACTION_importKey = 26;
        static final int TRANSACTION_import_key = 13;
        static final int TRANSACTION_insert = 3;
        static final int TRANSACTION_isEmpty = 11;
        static final int TRANSACTION_isOperationAuthorized = 32;
        static final int TRANSACTION_is_hardware_backed = 21;
        static final int TRANSACTION_list = 6;
        static final int TRANSACTION_lock = 9;
        static final int TRANSACTION_onDeviceOffBody = 38;
        static final int TRANSACTION_onUserAdded = 34;
        static final int TRANSACTION_onUserPasswordChanged = 8;
        static final int TRANSACTION_onUserRemoved = 35;
        static final int TRANSACTION_reset = 7;
        static final int TRANSACTION_sign = 14;
        static final int TRANSACTION_ungrant = 18;
        static final int TRANSACTION_unlock = 10;
        static final int TRANSACTION_update = 29;
        static final int TRANSACTION_verify = 15;

        public Stub()
        {
            attachInterface(this, "android.security.IKeystoreService");
        }
    }

    private static class Stub.Proxy
        implements IKeystoreService
    {

        public int abort(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.security.IKeystoreService");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(31, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public int addAuthToken(byte abyte0[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.security.IKeystoreService");
            parcel.writeByteArray(abyte0);
            mRemote.transact(33, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        public int addRngEntropy(byte abyte0[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.security.IKeystoreService");
            parcel.writeByteArray(abyte0);
            mRemote.transact(23, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public int attestDeviceIds(KeymasterArguments keymasterarguments, KeymasterCertificateChain keymastercertificatechain)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeystoreService");
            if(keymasterarguments == null)
                break MISSING_BLOCK_LABEL_84;
            parcel.writeInt(1);
            keymasterarguments.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(37, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(parcel1.readInt() != 0)
                keymastercertificatechain.readFromParcel(parcel1);
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            keymasterarguments;
            parcel1.recycle();
            parcel.recycle();
            throw keymasterarguments;
        }

        public int attestKey(String s, KeymasterArguments keymasterarguments, KeymasterCertificateChain keymastercertificatechain)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeystoreService");
            parcel.writeString(s);
            if(keymasterarguments == null)
                break MISSING_BLOCK_LABEL_96;
            parcel.writeInt(1);
            keymasterarguments.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(36, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(parcel1.readInt() != 0)
                keymastercertificatechain.readFromParcel(parcel1);
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public OperationResult begin(IBinder ibinder, String s, int i, boolean flag, KeymasterArguments keymasterarguments, byte abyte0[], int j)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeystoreService");
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            if(keymasterarguments == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            keymasterarguments.writeToParcel(parcel, 0);
_L3:
            parcel.writeByteArray(abyte0);
            parcel.writeInt(j);
            mRemote.transact(28, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_168;
            ibinder = (OperationResult)OperationResult.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return ibinder;
_L2:
            parcel.writeInt(0);
              goto _L3
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
            ibinder = null;
              goto _L4
        }

        public int clear_uid(long l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.security.IKeystoreService");
            parcel.writeLong(l);
            mRemote.transact(22, parcel, parcel1, 0);
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

        public int del(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeystoreService");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(4, parcel, parcel1, 0);
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

        public int duplicate(String s, int i, String s1, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeystoreService");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeString(s1);
            parcel.writeInt(j);
            mRemote.transact(20, parcel, parcel1, 0);
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

        public int exist(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeystoreService");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(5, parcel, parcel1, 0);
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

        public ExportResult exportKey(String s, int i, KeymasterBlob keymasterblob, KeymasterBlob keymasterblob1, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeystoreService");
            parcel.writeString(s);
            parcel.writeInt(i);
            if(keymasterblob == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            keymasterblob.writeToParcel(parcel, 0);
_L5:
            if(keymasterblob1 == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            keymasterblob1.writeToParcel(parcel, 0);
_L6:
            parcel.writeInt(j);
            mRemote.transact(27, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_159;
            s = (ExportResult)ExportResult.CREATOR.createFromParcel(parcel1);
_L7:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            parcel.writeInt(0);
              goto _L5
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
_L4:
            parcel.writeInt(0);
              goto _L6
            s = null;
              goto _L7
        }

        public OperationResult finish(IBinder ibinder, KeymasterArguments keymasterarguments, byte abyte0[], byte abyte1[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeystoreService");
            parcel.writeStrongBinder(ibinder);
            if(keymasterarguments == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            keymasterarguments.writeToParcel(parcel, 0);
_L3:
            parcel.writeByteArray(abyte0);
            parcel.writeByteArray(abyte1);
            mRemote.transact(30, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_131;
            ibinder = (OperationResult)OperationResult.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return ibinder;
_L2:
            parcel.writeInt(0);
              goto _L3
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
            ibinder = null;
              goto _L4
        }

        public int generate(String s, int i, int j, int k, int l, KeystoreArguments keystorearguments)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeystoreService");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            if(keystorearguments == null)
                break MISSING_BLOCK_LABEL_108;
            parcel.writeInt(1);
            keystorearguments.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int generateKey(String s, KeymasterArguments keymasterarguments, byte abyte0[], int i, int j, KeyCharacteristics keycharacteristics)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeystoreService");
            parcel.writeString(s);
            if(keymasterarguments == null)
                break MISSING_BLOCK_LABEL_117;
            parcel.writeInt(1);
            keymasterarguments.writeToParcel(parcel, 0);
_L1:
            parcel.writeByteArray(abyte0);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(24, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(parcel1.readInt() != 0)
                keycharacteristics.readFromParcel(parcel1);
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public byte[] get(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeystoreService");
            parcel.writeString(s);
            parcel.writeInt(i);
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

        public String getInterfaceDescriptor()
        {
            return "android.security.IKeystoreService";
        }

        public int getKeyCharacteristics(String s, KeymasterBlob keymasterblob, KeymasterBlob keymasterblob1, int i, KeyCharacteristics keycharacteristics)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeystoreService");
            parcel.writeString(s);
            if(keymasterblob == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            keymasterblob.writeToParcel(parcel, 0);
_L3:
            if(keymasterblob1 == null)
                break MISSING_BLOCK_LABEL_143;
            parcel.writeInt(1);
            keymasterblob1.writeToParcel(parcel, 0);
_L4:
            parcel.writeInt(i);
            mRemote.transact(25, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(parcel1.readInt() != 0)
                keycharacteristics.readFromParcel(parcel1);
            parcel1.recycle();
            parcel.recycle();
            return i;
_L2:
            parcel.writeInt(0);
              goto _L3
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
            parcel.writeInt(0);
              goto _L4
        }

        public int getState(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeystoreService");
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

        public byte[] get_pubkey(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeystoreService");
            parcel.writeString(s);
            mRemote.transact(16, parcel, parcel1, 0);
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

        public long getmtime(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("android.security.IKeystoreService");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            l = parcel1.readLong();
            parcel1.recycle();
            parcel.recycle();
            return l;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String grant(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeystoreService");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(17, parcel, parcel1, 0);
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

        public int importKey(String s, KeymasterArguments keymasterarguments, int i, byte abyte0[], int j, int k, KeyCharacteristics keycharacteristics)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeystoreService");
            parcel.writeString(s);
            if(keymasterarguments == null)
                break MISSING_BLOCK_LABEL_122;
            parcel.writeInt(1);
            keymasterarguments.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            parcel.writeByteArray(abyte0);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(26, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(parcel1.readInt() != 0)
                keycharacteristics.readFromParcel(parcel1);
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int import_key(String s, byte abyte0[], int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeystoreService");
            parcel.writeString(s);
            parcel.writeByteArray(abyte0);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(13, parcel, parcel1, 0);
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

        public int insert(String s, byte abyte0[], int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeystoreService");
            parcel.writeString(s);
            parcel.writeByteArray(abyte0);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(3, parcel, parcel1, 0);
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

        public int isEmpty(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeystoreService");
            parcel.writeInt(i);
            mRemote.transact(11, parcel, parcel1, 0);
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

        public boolean isOperationAuthorized(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.security.IKeystoreService");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(32, parcel, parcel1, 0);
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
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public int is_hardware_backed(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.security.IKeystoreService");
            parcel.writeString(s);
            mRemote.transact(21, parcel, parcel1, 0);
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

        public String[] list(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeystoreService");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createStringArray();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int lock(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeystoreService");
            parcel.writeInt(i);
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

        public int onDeviceOffBody()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.security.IKeystoreService");
            mRemote.transact(38, parcel, parcel1, 0);
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

        public int onUserAdded(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeystoreService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(34, parcel, parcel1, 0);
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

        public int onUserPasswordChanged(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeystoreService");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(8, parcel, parcel1, 0);
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

        public int onUserRemoved(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeystoreService");
            parcel.writeInt(i);
            mRemote.transact(35, parcel, parcel1, 0);
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

        public int reset()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.security.IKeystoreService");
            mRemote.transact(7, parcel, parcel1, 0);
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

        public byte[] sign(String s, byte abyte0[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeystoreService");
            parcel.writeString(s);
            parcel.writeByteArray(abyte0);
            mRemote.transact(14, parcel, parcel1, 0);
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

        public int ungrant(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeystoreService");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(18, parcel, parcel1, 0);
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

        public int unlock(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeystoreService");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(10, parcel, parcel1, 0);
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

        public OperationResult update(IBinder ibinder, KeymasterArguments keymasterarguments, byte abyte0[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeystoreService");
            parcel.writeStrongBinder(ibinder);
            if(keymasterarguments == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            keymasterarguments.writeToParcel(parcel, 0);
_L3:
            parcel.writeByteArray(abyte0);
            mRemote.transact(29, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_124;
            ibinder = (OperationResult)OperationResult.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return ibinder;
_L2:
            parcel.writeInt(0);
              goto _L3
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
            ibinder = null;
              goto _L4
        }

        public int verify(String s, byte abyte0[], byte abyte1[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.security.IKeystoreService");
            parcel.writeString(s);
            parcel.writeByteArray(abyte0);
            parcel.writeByteArray(abyte1);
            mRemote.transact(15, parcel, parcel1, 0);
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract int abort(IBinder ibinder)
        throws RemoteException;

    public abstract int addAuthToken(byte abyte0[])
        throws RemoteException;

    public abstract int addRngEntropy(byte abyte0[])
        throws RemoteException;

    public abstract int attestDeviceIds(KeymasterArguments keymasterarguments, KeymasterCertificateChain keymastercertificatechain)
        throws RemoteException;

    public abstract int attestKey(String s, KeymasterArguments keymasterarguments, KeymasterCertificateChain keymastercertificatechain)
        throws RemoteException;

    public abstract OperationResult begin(IBinder ibinder, String s, int i, boolean flag, KeymasterArguments keymasterarguments, byte abyte0[], int j)
        throws RemoteException;

    public abstract int clear_uid(long l)
        throws RemoteException;

    public abstract int del(String s, int i)
        throws RemoteException;

    public abstract int duplicate(String s, int i, String s1, int j)
        throws RemoteException;

    public abstract int exist(String s, int i)
        throws RemoteException;

    public abstract ExportResult exportKey(String s, int i, KeymasterBlob keymasterblob, KeymasterBlob keymasterblob1, int j)
        throws RemoteException;

    public abstract OperationResult finish(IBinder ibinder, KeymasterArguments keymasterarguments, byte abyte0[], byte abyte1[])
        throws RemoteException;

    public abstract int generate(String s, int i, int j, int k, int l, KeystoreArguments keystorearguments)
        throws RemoteException;

    public abstract int generateKey(String s, KeymasterArguments keymasterarguments, byte abyte0[], int i, int j, KeyCharacteristics keycharacteristics)
        throws RemoteException;

    public abstract byte[] get(String s, int i)
        throws RemoteException;

    public abstract int getKeyCharacteristics(String s, KeymasterBlob keymasterblob, KeymasterBlob keymasterblob1, int i, KeyCharacteristics keycharacteristics)
        throws RemoteException;

    public abstract int getState(int i)
        throws RemoteException;

    public abstract byte[] get_pubkey(String s)
        throws RemoteException;

    public abstract long getmtime(String s, int i)
        throws RemoteException;

    public abstract String grant(String s, int i)
        throws RemoteException;

    public abstract int importKey(String s, KeymasterArguments keymasterarguments, int i, byte abyte0[], int j, int k, KeyCharacteristics keycharacteristics)
        throws RemoteException;

    public abstract int import_key(String s, byte abyte0[], int i, int j)
        throws RemoteException;

    public abstract int insert(String s, byte abyte0[], int i, int j)
        throws RemoteException;

    public abstract int isEmpty(int i)
        throws RemoteException;

    public abstract boolean isOperationAuthorized(IBinder ibinder)
        throws RemoteException;

    public abstract int is_hardware_backed(String s)
        throws RemoteException;

    public abstract String[] list(String s, int i)
        throws RemoteException;

    public abstract int lock(int i)
        throws RemoteException;

    public abstract int onDeviceOffBody()
        throws RemoteException;

    public abstract int onUserAdded(int i, int j)
        throws RemoteException;

    public abstract int onUserPasswordChanged(int i, String s)
        throws RemoteException;

    public abstract int onUserRemoved(int i)
        throws RemoteException;

    public abstract int reset()
        throws RemoteException;

    public abstract byte[] sign(String s, byte abyte0[])
        throws RemoteException;

    public abstract int ungrant(String s, int i)
        throws RemoteException;

    public abstract int unlock(int i, String s)
        throws RemoteException;

    public abstract OperationResult update(IBinder ibinder, KeymasterArguments keymasterarguments, byte abyte0[])
        throws RemoteException;

    public abstract int verify(String s, byte abyte0[], byte abyte1[])
        throws RemoteException;
}
