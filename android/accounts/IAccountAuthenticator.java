// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.accounts;

import android.os.*;

// Referenced classes of package android.accounts:
//            IAccountAuthenticatorResponse, Account

public interface IAccountAuthenticator
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IAccountAuthenticator
    {

        public static IAccountAuthenticator asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.accounts.IAccountAuthenticator");
            if(iinterface != null && (iinterface instanceof IAccountAuthenticator))
                return (IAccountAuthenticator)iinterface;
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
            IAccountAuthenticatorResponse iaccountauthenticatorresponse7;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.accounts.IAccountAuthenticator");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.accounts.IAccountAuthenticator");
                parcel1 = IAccountAuthenticatorResponse.Stub.asInterface(parcel.readStrongBinder());
                String s = parcel.readString();
                String s2 = parcel.readString();
                String as[] = parcel.createStringArray();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                addAccount(parcel1, s, s2, as, parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.accounts.IAccountAuthenticator");
                IAccountAuthenticatorResponse iaccountauthenticatorresponse = IAccountAuthenticatorResponse.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel1 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                confirmCredentials(iaccountauthenticatorresponse, parcel1, parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.accounts.IAccountAuthenticator");
                IAccountAuthenticatorResponse iaccountauthenticatorresponse1 = IAccountAuthenticatorResponse.Stub.asInterface(parcel.readStrongBinder());
                String s4;
                if(parcel.readInt() != 0)
                    parcel1 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                s4 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                getAuthToken(iaccountauthenticatorresponse1, parcel1, s4, parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.accounts.IAccountAuthenticator");
                getAuthTokenLabel(IAccountAuthenticatorResponse.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.accounts.IAccountAuthenticator");
                IAccountAuthenticatorResponse iaccountauthenticatorresponse2 = IAccountAuthenticatorResponse.Stub.asInterface(parcel.readStrongBinder());
                String s5;
                if(parcel.readInt() != 0)
                    parcel1 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                s5 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                updateCredentials(iaccountauthenticatorresponse2, parcel1, s5, parcel);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.accounts.IAccountAuthenticator");
                editProperties(IAccountAuthenticatorResponse.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.accounts.IAccountAuthenticator");
                IAccountAuthenticatorResponse iaccountauthenticatorresponse3 = IAccountAuthenticatorResponse.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel1 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                hasFeatures(iaccountauthenticatorresponse3, parcel1, parcel.createStringArray());
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.accounts.IAccountAuthenticator");
                parcel1 = IAccountAuthenticatorResponse.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                getAccountRemovalAllowed(parcel1, parcel);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.accounts.IAccountAuthenticator");
                parcel1 = IAccountAuthenticatorResponse.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                getAccountCredentialsForCloning(parcel1, parcel);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.accounts.IAccountAuthenticator");
                IAccountAuthenticatorResponse iaccountauthenticatorresponse4 = IAccountAuthenticatorResponse.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel1 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                addAccountFromCredentials(iaccountauthenticatorresponse4, parcel1, parcel);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.accounts.IAccountAuthenticator");
                parcel1 = IAccountAuthenticatorResponse.Stub.asInterface(parcel.readStrongBinder());
                String s3 = parcel.readString();
                String s1 = parcel.readString();
                String as1[] = parcel.createStringArray();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                startAddAccountSession(parcel1, s3, s1, as1, parcel);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.accounts.IAccountAuthenticator");
                IAccountAuthenticatorResponse iaccountauthenticatorresponse5 = IAccountAuthenticatorResponse.Stub.asInterface(parcel.readStrongBinder());
                String s6;
                if(parcel.readInt() != 0)
                    parcel1 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                s6 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                startUpdateCredentialsSession(iaccountauthenticatorresponse5, parcel1, s6, parcel);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.accounts.IAccountAuthenticator");
                IAccountAuthenticatorResponse iaccountauthenticatorresponse6 = IAccountAuthenticatorResponse.Stub.asInterface(parcel.readStrongBinder());
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                finishSession(iaccountauthenticatorresponse6, parcel1, parcel);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.accounts.IAccountAuthenticator");
                iaccountauthenticatorresponse7 = IAccountAuthenticatorResponse.Stub.asInterface(parcel.readStrongBinder());
                break;
            }
            if(parcel.readInt() != 0)
                parcel1 = (Account)Account.CREATOR.createFromParcel(parcel);
            else
                parcel1 = null;
            isCredentialsUpdateSuggested(iaccountauthenticatorresponse7, parcel1, parcel.readString());
            return true;
        }

        private static final String DESCRIPTOR = "android.accounts.IAccountAuthenticator";
        static final int TRANSACTION_addAccount = 1;
        static final int TRANSACTION_addAccountFromCredentials = 10;
        static final int TRANSACTION_confirmCredentials = 2;
        static final int TRANSACTION_editProperties = 6;
        static final int TRANSACTION_finishSession = 13;
        static final int TRANSACTION_getAccountCredentialsForCloning = 9;
        static final int TRANSACTION_getAccountRemovalAllowed = 8;
        static final int TRANSACTION_getAuthToken = 3;
        static final int TRANSACTION_getAuthTokenLabel = 4;
        static final int TRANSACTION_hasFeatures = 7;
        static final int TRANSACTION_isCredentialsUpdateSuggested = 14;
        static final int TRANSACTION_startAddAccountSession = 11;
        static final int TRANSACTION_startUpdateCredentialsSession = 12;
        static final int TRANSACTION_updateCredentials = 5;

        public Stub()
        {
            attachInterface(this, "android.accounts.IAccountAuthenticator");
        }
    }

    private static class Stub.Proxy
        implements IAccountAuthenticator
    {

        public void addAccount(IAccountAuthenticatorResponse iaccountauthenticatorresponse, String s, String s1, String as[], Bundle bundle)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
            if(iaccountauthenticatorresponse == null)
                break MISSING_BLOCK_LABEL_27;
            ibinder = iaccountauthenticatorresponse.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeStringArray(as);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_93;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iaccountauthenticatorresponse;
            parcel.recycle();
            throw iaccountauthenticatorresponse;
        }

        public void addAccountFromCredentials(IAccountAuthenticatorResponse iaccountauthenticatorresponse, Account account, Bundle bundle)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
            if(iaccountauthenticatorresponse == null)
                break MISSING_BLOCK_LABEL_27;
            ibinder = iaccountauthenticatorresponse.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(account == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L3:
            if(bundle == null)
                break MISSING_BLOCK_LABEL_107;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(10, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            iaccountauthenticatorresponse;
            parcel.recycle();
            throw iaccountauthenticatorresponse;
            parcel.writeInt(0);
              goto _L4
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void confirmCredentials(IAccountAuthenticatorResponse iaccountauthenticatorresponse, Account account, Bundle bundle)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
            if(iaccountauthenticatorresponse == null)
                break MISSING_BLOCK_LABEL_27;
            ibinder = iaccountauthenticatorresponse.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(account == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L3:
            if(bundle == null)
                break MISSING_BLOCK_LABEL_106;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            iaccountauthenticatorresponse;
            parcel.recycle();
            throw iaccountauthenticatorresponse;
            parcel.writeInt(0);
              goto _L4
        }

        public void editProperties(IAccountAuthenticatorResponse iaccountauthenticatorresponse, String s)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
            if(iaccountauthenticatorresponse == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = iaccountauthenticatorresponse.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            iaccountauthenticatorresponse;
            parcel.recycle();
            throw iaccountauthenticatorresponse;
        }

        public void finishSession(IAccountAuthenticatorResponse iaccountauthenticatorresponse, String s, Bundle bundle)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
            if(iaccountauthenticatorresponse == null)
                break MISSING_BLOCK_LABEL_27;
            ibinder = iaccountauthenticatorresponse.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(13, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iaccountauthenticatorresponse;
            parcel.recycle();
            throw iaccountauthenticatorresponse;
        }

        public void getAccountCredentialsForCloning(IAccountAuthenticatorResponse iaccountauthenticatorresponse, Account account)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
            if(iaccountauthenticatorresponse == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = iaccountauthenticatorresponse.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(account == null)
                break MISSING_BLOCK_LABEL_70;
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iaccountauthenticatorresponse;
            parcel.recycle();
            throw iaccountauthenticatorresponse;
        }

        public void getAccountRemovalAllowed(IAccountAuthenticatorResponse iaccountauthenticatorresponse, Account account)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
            if(iaccountauthenticatorresponse == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = iaccountauthenticatorresponse.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(account == null)
                break MISSING_BLOCK_LABEL_70;
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iaccountauthenticatorresponse;
            parcel.recycle();
            throw iaccountauthenticatorresponse;
        }

        public void getAuthToken(IAccountAuthenticatorResponse iaccountauthenticatorresponse, Account account, String s, Bundle bundle)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
            if(iaccountauthenticatorresponse == null)
                break MISSING_BLOCK_LABEL_27;
            ibinder = iaccountauthenticatorresponse.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(account == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_114;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            iaccountauthenticatorresponse;
            parcel.recycle();
            throw iaccountauthenticatorresponse;
            parcel.writeInt(0);
              goto _L4
        }

        public void getAuthTokenLabel(IAccountAuthenticatorResponse iaccountauthenticatorresponse, String s)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
            if(iaccountauthenticatorresponse == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = iaccountauthenticatorresponse.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            iaccountauthenticatorresponse;
            parcel.recycle();
            throw iaccountauthenticatorresponse;
        }

        public String getInterfaceDescriptor()
        {
            return "android.accounts.IAccountAuthenticator";
        }

        public void hasFeatures(IAccountAuthenticatorResponse iaccountauthenticatorresponse, Account account, String as[])
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
            if(iaccountauthenticatorresponse == null)
                break MISSING_BLOCK_LABEL_27;
            ibinder = iaccountauthenticatorresponse.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(account == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L1:
            parcel.writeStringArray(as);
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iaccountauthenticatorresponse;
            parcel.recycle();
            throw iaccountauthenticatorresponse;
        }

        public void isCredentialsUpdateSuggested(IAccountAuthenticatorResponse iaccountauthenticatorresponse, Account account, String s)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
            if(iaccountauthenticatorresponse == null)
                break MISSING_BLOCK_LABEL_27;
            ibinder = iaccountauthenticatorresponse.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(account == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            mRemote.transact(14, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iaccountauthenticatorresponse;
            parcel.recycle();
            throw iaccountauthenticatorresponse;
        }

        public void startAddAccountSession(IAccountAuthenticatorResponse iaccountauthenticatorresponse, String s, String s1, String as[], Bundle bundle)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
            if(iaccountauthenticatorresponse == null)
                break MISSING_BLOCK_LABEL_27;
            ibinder = iaccountauthenticatorresponse.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeStringArray(as);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_94;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(11, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iaccountauthenticatorresponse;
            parcel.recycle();
            throw iaccountauthenticatorresponse;
        }

        public void startUpdateCredentialsSession(IAccountAuthenticatorResponse iaccountauthenticatorresponse, Account account, String s, Bundle bundle)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
            if(iaccountauthenticatorresponse == null)
                break MISSING_BLOCK_LABEL_27;
            ibinder = iaccountauthenticatorresponse.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(account == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_115;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(12, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            iaccountauthenticatorresponse;
            parcel.recycle();
            throw iaccountauthenticatorresponse;
            parcel.writeInt(0);
              goto _L4
        }

        public void updateCredentials(IAccountAuthenticatorResponse iaccountauthenticatorresponse, Account account, String s, Bundle bundle)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
            if(iaccountauthenticatorresponse == null)
                break MISSING_BLOCK_LABEL_27;
            ibinder = iaccountauthenticatorresponse.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(account == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_114;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            iaccountauthenticatorresponse;
            parcel.recycle();
            throw iaccountauthenticatorresponse;
            parcel.writeInt(0);
              goto _L4
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void addAccount(IAccountAuthenticatorResponse iaccountauthenticatorresponse, String s, String s1, String as[], Bundle bundle)
        throws RemoteException;

    public abstract void addAccountFromCredentials(IAccountAuthenticatorResponse iaccountauthenticatorresponse, Account account, Bundle bundle)
        throws RemoteException;

    public abstract void confirmCredentials(IAccountAuthenticatorResponse iaccountauthenticatorresponse, Account account, Bundle bundle)
        throws RemoteException;

    public abstract void editProperties(IAccountAuthenticatorResponse iaccountauthenticatorresponse, String s)
        throws RemoteException;

    public abstract void finishSession(IAccountAuthenticatorResponse iaccountauthenticatorresponse, String s, Bundle bundle)
        throws RemoteException;

    public abstract void getAccountCredentialsForCloning(IAccountAuthenticatorResponse iaccountauthenticatorresponse, Account account)
        throws RemoteException;

    public abstract void getAccountRemovalAllowed(IAccountAuthenticatorResponse iaccountauthenticatorresponse, Account account)
        throws RemoteException;

    public abstract void getAuthToken(IAccountAuthenticatorResponse iaccountauthenticatorresponse, Account account, String s, Bundle bundle)
        throws RemoteException;

    public abstract void getAuthTokenLabel(IAccountAuthenticatorResponse iaccountauthenticatorresponse, String s)
        throws RemoteException;

    public abstract void hasFeatures(IAccountAuthenticatorResponse iaccountauthenticatorresponse, Account account, String as[])
        throws RemoteException;

    public abstract void isCredentialsUpdateSuggested(IAccountAuthenticatorResponse iaccountauthenticatorresponse, Account account, String s)
        throws RemoteException;

    public abstract void startAddAccountSession(IAccountAuthenticatorResponse iaccountauthenticatorresponse, String s, String s1, String as[], Bundle bundle)
        throws RemoteException;

    public abstract void startUpdateCredentialsSession(IAccountAuthenticatorResponse iaccountauthenticatorresponse, Account account, String s, Bundle bundle)
        throws RemoteException;

    public abstract void updateCredentials(IAccountAuthenticatorResponse iaccountauthenticatorresponse, Account account, String s, Bundle bundle)
        throws RemoteException;
}
