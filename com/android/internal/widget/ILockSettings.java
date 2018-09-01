// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.app.trust.IStrongAuthTracker;
import android.os.*;

// Referenced classes of package com.android.internal.widget:
//            ICheckCredentialProgressCallback, VerifyCredentialResponse

public interface ILockSettings
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ILockSettings
    {

        public static ILockSettings asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.widget.ILockSettings");
            if(iinterface != null && (iinterface instanceof ILockSettings))
                return (ILockSettings)iinterface;
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
            boolean flag10;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("com.android.internal.widget.ILockSettings");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.widget.ILockSettings");
                setRawLockPassword(parcel.createByteArray(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.widget.ILockSettings");
                String s = parcel.readString();
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                setBoolean(s, flag, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.widget.ILockSettings");
                setLong(parcel.readString(), parcel.readLong(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.widget.ILockSettings");
                setString(parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.internal.widget.ILockSettings");
                String s1 = parcel.readString();
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                flag1 = getBoolean(s1, flag1, parcel.readInt());
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.internal.widget.ILockSettings");
                long l = getLong(parcel.readString(), parcel.readLong(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeLong(l);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.internal.widget.ILockSettings");
                parcel = getString(parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.android.internal.widget.ILockSettings");
                setLockCredential(parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.android.internal.widget.ILockSettings");
                resetKeyStore(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.android.internal.widget.ILockSettings");
                parcel = checkCredential(parcel.readString(), parcel.readInt(), parcel.readInt(), ICheckCredentialProgressCallback.Stub.asInterface(parcel.readStrongBinder()));
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
                parcel.enforceInterface("com.android.internal.widget.ILockSettings");
                parcel = verifyCredential(parcel.readString(), parcel.readInt(), parcel.readLong(), parcel.readInt());
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
                parcel.enforceInterface("com.android.internal.widget.ILockSettings");
                parcel = verifyTiedProfileChallenge(parcel.readString(), parcel.readInt(), parcel.readLong(), parcel.readInt());
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
                parcel.enforceInterface("com.android.internal.widget.ILockSettings");
                boolean flag2 = checkVoldPassword(parcel.readInt());
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("com.android.internal.widget.ILockSettings");
                boolean flag3 = havePattern(parcel.readInt());
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("com.android.internal.widget.ILockSettings");
                boolean flag4 = havePassword(parcel.readInt());
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("com.android.internal.widget.ILockSettings");
                i = parcel.readInt();
                boolean flag5;
                if(parcel.readInt() != 0)
                    flag5 = true;
                else
                    flag5 = false;
                setSeparateProfileChallengeEnabled(i, flag5, parcel.readString());
                parcel1.writeNoException();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("com.android.internal.widget.ILockSettings");
                boolean flag6 = getSeparateProfileChallengeEnabled(parcel.readInt());
                parcel1.writeNoException();
                if(flag6)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("com.android.internal.widget.ILockSettings");
                registerStrongAuthTracker(android.app.trust.IStrongAuthTracker.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("com.android.internal.widget.ILockSettings");
                unregisterStrongAuthTracker(android.app.trust.IStrongAuthTracker.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 20: // '\024'
                parcel.enforceInterface("com.android.internal.widget.ILockSettings");
                requireStrongAuth(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 21: // '\025'
                parcel.enforceInterface("com.android.internal.widget.ILockSettings");
                systemReady();
                parcel1.writeNoException();
                return true;

            case 22: // '\026'
                parcel.enforceInterface("com.android.internal.widget.ILockSettings");
                userPresent(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 23: // '\027'
                parcel.enforceInterface("com.android.internal.widget.ILockSettings");
                i = getStrongAuthForUser(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 24: // '\030'
                parcel.enforceInterface("com.android.internal.widget.ILockSettings");
                long l1 = addEscrowToken(parcel.createByteArray(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeLong(l1);
                return true;

            case 25: // '\031'
                parcel.enforceInterface("com.android.internal.widget.ILockSettings");
                boolean flag7 = removeEscrowToken(parcel.readLong(), parcel.readInt());
                parcel1.writeNoException();
                if(flag7)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 26: // '\032'
                parcel.enforceInterface("com.android.internal.widget.ILockSettings");
                boolean flag8 = isEscrowTokenActive(parcel.readLong(), parcel.readInt());
                parcel1.writeNoException();
                if(flag8)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 27: // '\033'
                parcel.enforceInterface("com.android.internal.widget.ILockSettings");
                boolean flag9 = setLockCredentialWithToken(parcel.readString(), parcel.readInt(), parcel.readLong(), parcel.createByteArray(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                if(flag9)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 28: // '\034'
                parcel.enforceInterface("com.android.internal.widget.ILockSettings");
                unlockUserWithToken(parcel.readLong(), parcel.createByteArray(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 29: // '\035'
                parcel.enforceInterface("com.android.internal.widget.ILockSettings");
                sanitizePassword();
                parcel1.writeNoException();
                return true;

            case 30: // '\036'
                parcel.enforceInterface("com.android.internal.widget.ILockSettings");
                parcel = getPassword();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 31: // '\037'
                parcel.enforceInterface("com.android.internal.widget.ILockSettings");
                savePrivacyPasswordPattern(parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 32: // ' '
                parcel.enforceInterface("com.android.internal.widget.ILockSettings");
                flag10 = checkPrivacyPasswordPattern(parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                break;
            }
            if(flag10)
                i = 1;
            else
                i = 0;
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "com.android.internal.widget.ILockSettings";
        static final int TRANSACTION_addEscrowToken = 24;
        static final int TRANSACTION_checkCredential = 10;
        static final int TRANSACTION_checkPrivacyPasswordPattern = 32;
        static final int TRANSACTION_checkVoldPassword = 13;
        static final int TRANSACTION_getBoolean = 5;
        static final int TRANSACTION_getLong = 6;
        static final int TRANSACTION_getPassword = 30;
        static final int TRANSACTION_getSeparateProfileChallengeEnabled = 17;
        static final int TRANSACTION_getString = 7;
        static final int TRANSACTION_getStrongAuthForUser = 23;
        static final int TRANSACTION_havePassword = 15;
        static final int TRANSACTION_havePattern = 14;
        static final int TRANSACTION_isEscrowTokenActive = 26;
        static final int TRANSACTION_registerStrongAuthTracker = 18;
        static final int TRANSACTION_removeEscrowToken = 25;
        static final int TRANSACTION_requireStrongAuth = 20;
        static final int TRANSACTION_resetKeyStore = 9;
        static final int TRANSACTION_sanitizePassword = 29;
        static final int TRANSACTION_savePrivacyPasswordPattern = 31;
        static final int TRANSACTION_setBoolean = 2;
        static final int TRANSACTION_setLockCredential = 8;
        static final int TRANSACTION_setLockCredentialWithToken = 27;
        static final int TRANSACTION_setLong = 3;
        static final int TRANSACTION_setRawLockPassword = 1;
        static final int TRANSACTION_setSeparateProfileChallengeEnabled = 16;
        static final int TRANSACTION_setString = 4;
        static final int TRANSACTION_systemReady = 21;
        static final int TRANSACTION_unlockUserWithToken = 28;
        static final int TRANSACTION_unregisterStrongAuthTracker = 19;
        static final int TRANSACTION_userPresent = 22;
        static final int TRANSACTION_verifyCredential = 11;
        static final int TRANSACTION_verifyTiedProfileChallenge = 12;

        public Stub()
        {
            attachInterface(this, "com.android.internal.widget.ILockSettings");
        }
    }

    private static class Stub.Proxy
        implements ILockSettings
    {

        public long addEscrowToken(byte abyte0[], int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("com.android.internal.widget.ILockSettings");
            parcel.writeByteArray(abyte0);
            parcel.writeInt(i);
            mRemote.transact(24, parcel, parcel1, 0);
            parcel1.readException();
            l = parcel1.readLong();
            parcel1.recycle();
            parcel.recycle();
            return l;
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public VerifyCredentialResponse checkCredential(String s, int i, int j, ICheckCredentialProgressCallback icheckcredentialprogresscallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.widget.ILockSettings");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            s = obj;
            if(icheckcredentialprogresscallback == null)
                break MISSING_BLOCK_LABEL_54;
            s = icheckcredentialprogresscallback.asBinder();
            parcel.writeStrongBinder(s);
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (VerifyCredentialResponse)VerifyCredentialResponse.CREATOR.createFromParcel(parcel1);
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

        public boolean checkPrivacyPasswordPattern(String s, String s1, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.widget.ILockSettings");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean checkVoldPassword(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.widget.ILockSettings");
            parcel.writeInt(i);
            mRemote.transact(13, parcel, parcel1, 0);
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

        public boolean getBoolean(String s, boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            Parcel parcel1;
            j = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.widget.ILockSettings");
            parcel.writeString(s);
            if(flag)
                j = 1;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(5, parcel, parcel1, 0);
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
            return "com.android.internal.widget.ILockSettings";
        }

        public long getLong(String s, long l, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.widget.ILockSettings");
            parcel.writeString(s);
            parcel.writeLong(l);
            parcel.writeInt(i);
            mRemote.transact(6, parcel, parcel1, 0);
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

        public String getPassword()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.android.internal.widget.ILockSettings");
            mRemote.transact(30, parcel, parcel1, 0);
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

        public boolean getSeparateProfileChallengeEnabled(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.widget.ILockSettings");
            parcel.writeInt(i);
            mRemote.transact(17, parcel, parcel1, 0);
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

        public String getString(String s, String s1, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.widget.ILockSettings");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
            mRemote.transact(7, parcel, parcel1, 0);
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

        public int getStrongAuthForUser(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.widget.ILockSettings");
            parcel.writeInt(i);
            mRemote.transact(23, parcel, parcel1, 0);
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

        public boolean havePassword(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.widget.ILockSettings");
            parcel.writeInt(i);
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean havePattern(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.widget.ILockSettings");
            parcel.writeInt(i);
            mRemote.transact(14, parcel, parcel1, 0);
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

        public boolean isEscrowTokenActive(long l, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.widget.ILockSettings");
            parcel.writeLong(l);
            parcel.writeInt(i);
            mRemote.transact(26, parcel, parcel1, 0);
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

        public void registerStrongAuthTracker(IStrongAuthTracker istrongauthtracker)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.widget.ILockSettings");
            if(istrongauthtracker == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = istrongauthtracker.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(18, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            istrongauthtracker;
            parcel1.recycle();
            parcel.recycle();
            throw istrongauthtracker;
        }

        public boolean removeEscrowToken(long l, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.widget.ILockSettings");
            parcel.writeLong(l);
            parcel.writeInt(i);
            mRemote.transact(25, parcel, parcel1, 0);
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

        public void requireStrongAuth(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.widget.ILockSettings");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(20, parcel, parcel1, 0);
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

        public void resetKeyStore(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.widget.ILockSettings");
            parcel.writeInt(i);
            mRemote.transact(9, parcel, parcel1, 0);
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

        public void sanitizePassword()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.widget.ILockSettings");
            mRemote.transact(29, parcel, parcel1, 0);
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

        public void savePrivacyPasswordPattern(String s, String s1, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.widget.ILockSettings");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
            mRemote.transact(31, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setBoolean(String s, boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            Parcel parcel1;
            j = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.widget.ILockSettings");
            parcel.writeString(s);
            if(flag)
                j = 1;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setLockCredential(String s, int i, String s1, int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.widget.ILockSettings");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeString(s1);
            parcel.writeInt(j);
            parcel.writeInt(k);
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

        public boolean setLockCredentialWithToken(String s, int i, long l, byte abyte0[], int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.widget.ILockSettings");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeLong(l);
            parcel.writeByteArray(abyte0);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(27, parcel, parcel1, 0);
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

        public void setLong(String s, long l, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.widget.ILockSettings");
            parcel.writeString(s);
            parcel.writeLong(l);
            parcel.writeInt(i);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setRawLockPassword(byte abyte0[], int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.widget.ILockSettings");
            parcel.writeByteArray(abyte0);
            parcel.writeInt(i);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        public void setSeparateProfileChallengeEnabled(int i, boolean flag, String s)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.widget.ILockSettings");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setString(String s, String s1, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.widget.ILockSettings");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void systemReady()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.widget.ILockSettings");
            mRemote.transact(21, parcel, parcel1, 0);
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

        public void unlockUserWithToken(long l, byte abyte0[], int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.widget.ILockSettings");
            parcel.writeLong(l);
            parcel.writeByteArray(abyte0);
            parcel.writeInt(i);
            mRemote.transact(28, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        public void unregisterStrongAuthTracker(IStrongAuthTracker istrongauthtracker)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.widget.ILockSettings");
            if(istrongauthtracker == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = istrongauthtracker.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            istrongauthtracker;
            parcel1.recycle();
            parcel.recycle();
            throw istrongauthtracker;
        }

        public void userPresent(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.widget.ILockSettings");
            parcel.writeInt(i);
            mRemote.transact(22, parcel, parcel1, 0);
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

        public VerifyCredentialResponse verifyCredential(String s, int i, long l, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.widget.ILockSettings");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeLong(l);
            parcel.writeInt(j);
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (VerifyCredentialResponse)VerifyCredentialResponse.CREATOR.createFromParcel(parcel1);
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

        public VerifyCredentialResponse verifyTiedProfileChallenge(String s, int i, long l, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.widget.ILockSettings");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeLong(l);
            parcel.writeInt(j);
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (VerifyCredentialResponse)VerifyCredentialResponse.CREATOR.createFromParcel(parcel1);
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


    public abstract long addEscrowToken(byte abyte0[], int i)
        throws RemoteException;

    public abstract VerifyCredentialResponse checkCredential(String s, int i, int j, ICheckCredentialProgressCallback icheckcredentialprogresscallback)
        throws RemoteException;

    public abstract boolean checkPrivacyPasswordPattern(String s, String s1, int i)
        throws RemoteException;

    public abstract boolean checkVoldPassword(int i)
        throws RemoteException;

    public abstract boolean getBoolean(String s, boolean flag, int i)
        throws RemoteException;

    public abstract long getLong(String s, long l, int i)
        throws RemoteException;

    public abstract String getPassword()
        throws RemoteException;

    public abstract boolean getSeparateProfileChallengeEnabled(int i)
        throws RemoteException;

    public abstract String getString(String s, String s1, int i)
        throws RemoteException;

    public abstract int getStrongAuthForUser(int i)
        throws RemoteException;

    public abstract boolean havePassword(int i)
        throws RemoteException;

    public abstract boolean havePattern(int i)
        throws RemoteException;

    public abstract boolean isEscrowTokenActive(long l, int i)
        throws RemoteException;

    public abstract void registerStrongAuthTracker(IStrongAuthTracker istrongauthtracker)
        throws RemoteException;

    public abstract boolean removeEscrowToken(long l, int i)
        throws RemoteException;

    public abstract void requireStrongAuth(int i, int j)
        throws RemoteException;

    public abstract void resetKeyStore(int i)
        throws RemoteException;

    public abstract void sanitizePassword()
        throws RemoteException;

    public abstract void savePrivacyPasswordPattern(String s, String s1, int i)
        throws RemoteException;

    public abstract void setBoolean(String s, boolean flag, int i)
        throws RemoteException;

    public abstract void setLockCredential(String s, int i, String s1, int j, int k)
        throws RemoteException;

    public abstract boolean setLockCredentialWithToken(String s, int i, long l, byte abyte0[], int j, int k)
        throws RemoteException;

    public abstract void setLong(String s, long l, int i)
        throws RemoteException;

    public abstract void setRawLockPassword(byte abyte0[], int i)
        throws RemoteException;

    public abstract void setSeparateProfileChallengeEnabled(int i, boolean flag, String s)
        throws RemoteException;

    public abstract void setString(String s, String s1, int i)
        throws RemoteException;

    public abstract void systemReady()
        throws RemoteException;

    public abstract void unlockUserWithToken(long l, byte abyte0[], int i)
        throws RemoteException;

    public abstract void unregisterStrongAuthTracker(IStrongAuthTracker istrongauthtracker)
        throws RemoteException;

    public abstract void userPresent(int i)
        throws RemoteException;

    public abstract VerifyCredentialResponse verifyCredential(String s, int i, long l, int j)
        throws RemoteException;

    public abstract VerifyCredentialResponse verifyTiedProfileChallenge(String s, int i, long l, int j)
        throws RemoteException;
}
