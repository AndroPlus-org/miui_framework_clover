// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.accounts;

import android.content.IntentSender;
import android.os.*;
import java.util.Map;

// Referenced classes of package android.accounts:
//            Account, IAccountManagerResponse, AuthenticatorDescription

public interface IAccountManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IAccountManager
    {

        public static IAccountManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.accounts.IAccountManager");
            if(iinterface != null && (iinterface instanceof IAccountManager))
                return (IAccountManager)iinterface;
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
                parcel1.writeString("android.accounts.IAccountManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.accounts.IAccountManager");
                if(parcel.readInt() != 0)
                    parcel = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getPassword(parcel);
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.accounts.IAccountManager");
                Account account;
                if(parcel.readInt() != 0)
                    account = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account = null;
                parcel = getUserData(account, parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.accounts.IAccountManager");
                parcel = getAuthenticatorTypes(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.accounts.IAccountManager");
                parcel = getAccounts(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.accounts.IAccountManager");
                parcel = getAccountsForPackage(parcel.readString(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.accounts.IAccountManager");
                parcel = getAccountsByTypeForPackage(parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.accounts.IAccountManager");
                parcel = getAccountsAsUser(parcel.readString(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.accounts.IAccountManager");
                IAccountManagerResponse iaccountmanagerresponse2 = IAccountManagerResponse.Stub.asInterface(parcel.readStrongBinder());
                Account account1;
                if(parcel.readInt() != 0)
                    account1 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account1 = null;
                hasFeatures(iaccountmanagerresponse2, account1, parcel.createStringArray(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.accounts.IAccountManager");
                getAccountByTypeAndFeatures(IAccountManagerResponse.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.createStringArray(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.accounts.IAccountManager");
                getAccountsByFeatures(IAccountManagerResponse.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.createStringArray(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.accounts.IAccountManager");
                Account account2;
                String s1;
                boolean flag;
                if(parcel.readInt() != 0)
                    account2 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account2 = null;
                s1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag = addAccountExplicitly(account2, s1, parcel);
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.accounts.IAccountManager");
                IAccountManagerResponse iaccountmanagerresponse3 = IAccountManagerResponse.Stub.asInterface(parcel.readStrongBinder());
                Account account3;
                boolean flag1;
                if(parcel.readInt() != 0)
                    account3 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account3 = null;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                removeAccount(iaccountmanagerresponse3, account3, flag1);
                parcel1.writeNoException();
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.accounts.IAccountManager");
                IAccountManagerResponse iaccountmanagerresponse4 = IAccountManagerResponse.Stub.asInterface(parcel.readStrongBinder());
                Account account4;
                boolean flag2;
                if(parcel.readInt() != 0)
                    account4 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account4 = null;
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                removeAccountAsUser(iaccountmanagerresponse4, account4, flag2, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.accounts.IAccountManager");
                boolean flag3;
                if(parcel.readInt() != 0)
                    parcel = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag3 = removeAccountExplicitly(parcel);
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.accounts.IAccountManager");
                IAccountManagerResponse iaccountmanagerresponse5 = IAccountManagerResponse.Stub.asInterface(parcel.readStrongBinder());
                Account account5;
                if(parcel.readInt() != 0)
                    account5 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account5 = null;
                copyAccountToUser(iaccountmanagerresponse5, account5, parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.accounts.IAccountManager");
                invalidateAuthToken(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.accounts.IAccountManager");
                Account account6;
                if(parcel.readInt() != 0)
                    account6 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account6 = null;
                parcel = peekAuthToken(account6, parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.accounts.IAccountManager");
                Account account7;
                if(parcel.readInt() != 0)
                    account7 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account7 = null;
                setAuthToken(account7, parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("android.accounts.IAccountManager");
                Account account8;
                if(parcel.readInt() != 0)
                    account8 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account8 = null;
                setPassword(account8, parcel.readString());
                parcel1.writeNoException();
                return true;

            case 20: // '\024'
                parcel.enforceInterface("android.accounts.IAccountManager");
                if(parcel.readInt() != 0)
                    parcel = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                clearPassword(parcel);
                parcel1.writeNoException();
                return true;

            case 21: // '\025'
                parcel.enforceInterface("android.accounts.IAccountManager");
                Account account9;
                if(parcel.readInt() != 0)
                    account9 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account9 = null;
                setUserData(account9, parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 22: // '\026'
                parcel.enforceInterface("android.accounts.IAccountManager");
                Account account10;
                String s2;
                boolean flag4;
                if(parcel.readInt() != 0)
                    account10 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account10 = null;
                s2 = parcel.readString();
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag4 = true;
                else
                    flag4 = false;
                updateAppPermission(account10, s2, i, flag4);
                parcel1.writeNoException();
                return true;

            case 23: // '\027'
                parcel.enforceInterface("android.accounts.IAccountManager");
                IAccountManagerResponse iaccountmanagerresponse6 = IAccountManagerResponse.Stub.asInterface(parcel.readStrongBinder());
                Account account11;
                boolean flag5;
                String s8;
                boolean flag21;
                if(parcel.readInt() != 0)
                    account11 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account11 = null;
                s8 = parcel.readString();
                if(parcel.readInt() != 0)
                    flag5 = true;
                else
                    flag5 = false;
                if(parcel.readInt() != 0)
                    flag21 = true;
                else
                    flag21 = false;
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                getAuthToken(iaccountmanagerresponse6, account11, s8, flag5, flag21, parcel);
                parcel1.writeNoException();
                return true;

            case 24: // '\030'
                parcel.enforceInterface("android.accounts.IAccountManager");
                IAccountManagerResponse iaccountmanagerresponse = IAccountManagerResponse.Stub.asInterface(parcel.readStrongBinder());
                String s9 = parcel.readString();
                String s3 = parcel.readString();
                String as1[] = parcel.createStringArray();
                boolean flag6;
                if(parcel.readInt() != 0)
                    flag6 = true;
                else
                    flag6 = false;
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                addAccount(iaccountmanagerresponse, s9, s3, as1, flag6, parcel);
                parcel1.writeNoException();
                return true;

            case 25: // '\031'
                parcel.enforceInterface("android.accounts.IAccountManager");
                IAccountManagerResponse iaccountmanagerresponse14 = IAccountManagerResponse.Stub.asInterface(parcel.readStrongBinder());
                String s14 = parcel.readString();
                String s4 = parcel.readString();
                String as[] = parcel.createStringArray();
                Bundle bundle;
                boolean flag7;
                if(parcel.readInt() != 0)
                    flag7 = true;
                else
                    flag7 = false;
                if(parcel.readInt() != 0)
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle = null;
                addAccountAsUser(iaccountmanagerresponse14, s14, s4, as, flag7, bundle, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 26: // '\032'
                parcel.enforceInterface("android.accounts.IAccountManager");
                IAccountManagerResponse iaccountmanagerresponse7 = IAccountManagerResponse.Stub.asInterface(parcel.readStrongBinder());
                Account account12;
                boolean flag8;
                String s10;
                if(parcel.readInt() != 0)
                    account12 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account12 = null;
                s10 = parcel.readString();
                if(parcel.readInt() != 0)
                    flag8 = true;
                else
                    flag8 = false;
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                updateCredentials(iaccountmanagerresponse7, account12, s10, flag8, parcel);
                parcel1.writeNoException();
                return true;

            case 27: // '\033'
                parcel.enforceInterface("android.accounts.IAccountManager");
                IAccountManagerResponse iaccountmanagerresponse8 = IAccountManagerResponse.Stub.asInterface(parcel.readStrongBinder());
                String s = parcel.readString();
                boolean flag9;
                if(parcel.readInt() != 0)
                    flag9 = true;
                else
                    flag9 = false;
                editProperties(iaccountmanagerresponse8, s, flag9);
                parcel1.writeNoException();
                return true;

            case 28: // '\034'
                parcel.enforceInterface("android.accounts.IAccountManager");
                IAccountManagerResponse iaccountmanagerresponse12 = IAccountManagerResponse.Stub.asInterface(parcel.readStrongBinder());
                Account account13;
                Bundle bundle2;
                boolean flag10;
                if(parcel.readInt() != 0)
                    account13 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account13 = null;
                if(parcel.readInt() != 0)
                    bundle2 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle2 = null;
                if(parcel.readInt() != 0)
                    flag10 = true;
                else
                    flag10 = false;
                confirmCredentialsAsUser(iaccountmanagerresponse12, account13, bundle2, flag10, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 29: // '\035'
                parcel.enforceInterface("android.accounts.IAccountManager");
                boolean flag11;
                if(parcel.readInt() != 0)
                    parcel = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag11 = accountAuthenticated(parcel);
                parcel1.writeNoException();
                if(flag11)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 30: // '\036'
                parcel.enforceInterface("android.accounts.IAccountManager");
                getAuthTokenLabel(IAccountManagerResponse.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 31: // '\037'
                parcel.enforceInterface("android.accounts.IAccountManager");
                parcel = getSharedAccountsAsUser(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 32: // ' '
                parcel.enforceInterface("android.accounts.IAccountManager");
                Account account14;
                boolean flag12;
                if(parcel.readInt() != 0)
                    account14 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account14 = null;
                flag12 = removeSharedAccountAsUser(account14, parcel.readInt());
                parcel1.writeNoException();
                if(flag12)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 33: // '!'
                parcel.enforceInterface("android.accounts.IAccountManager");
                addSharedAccountsFromParentUser(parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 34: // '"'
                parcel.enforceInterface("android.accounts.IAccountManager");
                IAccountManagerResponse iaccountmanagerresponse9 = IAccountManagerResponse.Stub.asInterface(parcel.readStrongBinder());
                Account account15;
                if(parcel.readInt() != 0)
                    account15 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account15 = null;
                renameAccount(iaccountmanagerresponse9, account15, parcel.readString());
                parcel1.writeNoException();
                return true;

            case 35: // '#'
                parcel.enforceInterface("android.accounts.IAccountManager");
                if(parcel.readInt() != 0)
                    parcel = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getPreviousName(parcel);
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 36: // '$'
                parcel.enforceInterface("android.accounts.IAccountManager");
                Account account16;
                boolean flag13;
                if(parcel.readInt() != 0)
                    account16 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account16 = null;
                flag13 = renameSharedAccountAsUser(account16, parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag13)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 37: // '%'
                parcel.enforceInterface("android.accounts.IAccountManager");
                IAccountManagerResponse iaccountmanagerresponse1 = IAccountManagerResponse.Stub.asInterface(parcel.readStrongBinder());
                String s5 = parcel.readString();
                String s11 = parcel.readString();
                String as2[] = parcel.createStringArray();
                boolean flag14;
                if(parcel.readInt() != 0)
                    flag14 = true;
                else
                    flag14 = false;
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                startAddAccountSession(iaccountmanagerresponse1, s5, s11, as2, flag14, parcel);
                parcel1.writeNoException();
                return true;

            case 38: // '&'
                parcel.enforceInterface("android.accounts.IAccountManager");
                IAccountManagerResponse iaccountmanagerresponse10 = IAccountManagerResponse.Stub.asInterface(parcel.readStrongBinder());
                Account account17;
                boolean flag15;
                String s12;
                if(parcel.readInt() != 0)
                    account17 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account17 = null;
                s12 = parcel.readString();
                if(parcel.readInt() != 0)
                    flag15 = true;
                else
                    flag15 = false;
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                startUpdateCredentialsSession(iaccountmanagerresponse10, account17, s12, flag15, parcel);
                parcel1.writeNoException();
                return true;

            case 39: // '\''
                parcel.enforceInterface("android.accounts.IAccountManager");
                IAccountManagerResponse iaccountmanagerresponse13 = IAccountManagerResponse.Stub.asInterface(parcel.readStrongBinder());
                Bundle bundle1;
                Bundle bundle3;
                boolean flag16;
                if(parcel.readInt() != 0)
                    bundle1 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle1 = null;
                if(parcel.readInt() != 0)
                    flag16 = true;
                else
                    flag16 = false;
                if(parcel.readInt() != 0)
                    bundle3 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle3 = null;
                finishSessionAsUser(iaccountmanagerresponse13, bundle1, flag16, bundle3, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 40: // '('
                parcel.enforceInterface("android.accounts.IAccountManager");
                boolean flag17;
                if(parcel.readInt() != 0)
                    parcel = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag17 = someUserHasAccount(parcel);
                parcel1.writeNoException();
                if(flag17)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 41: // ')'
                parcel.enforceInterface("android.accounts.IAccountManager");
                IAccountManagerResponse iaccountmanagerresponse11 = IAccountManagerResponse.Stub.asInterface(parcel.readStrongBinder());
                Account account18;
                if(parcel.readInt() != 0)
                    account18 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account18 = null;
                isCredentialsUpdateSuggested(iaccountmanagerresponse11, account18, parcel.readString());
                parcel1.writeNoException();
                return true;

            case 42: // '*'
                parcel.enforceInterface("android.accounts.IAccountManager");
                if(parcel.readInt() != 0)
                    parcel = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getPackagesAndVisibilityForAccount(parcel);
                parcel1.writeNoException();
                parcel1.writeMap(parcel);
                return true;

            case 43: // '+'
                parcel.enforceInterface("android.accounts.IAccountManager");
                Account account19;
                Bundle bundle4;
                boolean flag18;
                String s13;
                if(parcel.readInt() != 0)
                    account19 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account19 = null;
                s13 = parcel.readString();
                if(parcel.readInt() != 0)
                    bundle4 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle4 = null;
                flag18 = addAccountExplicitlyWithVisibility(account19, s13, bundle4, parcel.readHashMap(getClass().getClassLoader()));
                parcel1.writeNoException();
                if(flag18)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 44: // ','
                parcel.enforceInterface("android.accounts.IAccountManager");
                Account account20;
                boolean flag19;
                if(parcel.readInt() != 0)
                    account20 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account20 = null;
                flag19 = setAccountVisibility(account20, parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag19)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 45: // '-'
                parcel.enforceInterface("android.accounts.IAccountManager");
                Account account21;
                if(parcel.readInt() != 0)
                    account21 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account21 = null;
                i = getAccountVisibility(account21, parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 46: // '.'
                parcel.enforceInterface("android.accounts.IAccountManager");
                parcel = getAccountsAndVisibilityForPackage(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeMap(parcel);
                return true;

            case 47: // '/'
                parcel.enforceInterface("android.accounts.IAccountManager");
                registerAccountListener(parcel.createStringArray(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 48: // '0'
                parcel.enforceInterface("android.accounts.IAccountManager");
                unregisterAccountListener(parcel.createStringArray(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 49: // '1'
                parcel.enforceInterface("android.accounts.IAccountManager");
                Account account22;
                String s6;
                boolean flag20;
                if(parcel.readInt() != 0)
                    account22 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account22 = null;
                s6 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (UserHandle)UserHandle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag20 = hasAccountAccess(account22, s6, parcel);
                parcel1.writeNoException();
                if(flag20)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 50: // '2'
                parcel.enforceInterface("android.accounts.IAccountManager");
                Account account23;
                String s7;
                if(parcel.readInt() != 0)
                    account23 = (Account)Account.CREATOR.createFromParcel(parcel);
                else
                    account23 = null;
                s7 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (UserHandle)UserHandle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = createRequestAccountAccessIntentSenderAsUser(account23, s7, parcel);
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

            case 51: // '3'
                parcel.enforceInterface("android.accounts.IAccountManager");
                onAccountAccessed(parcel.readString());
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.accounts.IAccountManager";
        static final int TRANSACTION_accountAuthenticated = 29;
        static final int TRANSACTION_addAccount = 24;
        static final int TRANSACTION_addAccountAsUser = 25;
        static final int TRANSACTION_addAccountExplicitly = 11;
        static final int TRANSACTION_addAccountExplicitlyWithVisibility = 43;
        static final int TRANSACTION_addSharedAccountsFromParentUser = 33;
        static final int TRANSACTION_clearPassword = 20;
        static final int TRANSACTION_confirmCredentialsAsUser = 28;
        static final int TRANSACTION_copyAccountToUser = 15;
        static final int TRANSACTION_createRequestAccountAccessIntentSenderAsUser = 50;
        static final int TRANSACTION_editProperties = 27;
        static final int TRANSACTION_finishSessionAsUser = 39;
        static final int TRANSACTION_getAccountByTypeAndFeatures = 9;
        static final int TRANSACTION_getAccountVisibility = 45;
        static final int TRANSACTION_getAccounts = 4;
        static final int TRANSACTION_getAccountsAndVisibilityForPackage = 46;
        static final int TRANSACTION_getAccountsAsUser = 7;
        static final int TRANSACTION_getAccountsByFeatures = 10;
        static final int TRANSACTION_getAccountsByTypeForPackage = 6;
        static final int TRANSACTION_getAccountsForPackage = 5;
        static final int TRANSACTION_getAuthToken = 23;
        static final int TRANSACTION_getAuthTokenLabel = 30;
        static final int TRANSACTION_getAuthenticatorTypes = 3;
        static final int TRANSACTION_getPackagesAndVisibilityForAccount = 42;
        static final int TRANSACTION_getPassword = 1;
        static final int TRANSACTION_getPreviousName = 35;
        static final int TRANSACTION_getSharedAccountsAsUser = 31;
        static final int TRANSACTION_getUserData = 2;
        static final int TRANSACTION_hasAccountAccess = 49;
        static final int TRANSACTION_hasFeatures = 8;
        static final int TRANSACTION_invalidateAuthToken = 16;
        static final int TRANSACTION_isCredentialsUpdateSuggested = 41;
        static final int TRANSACTION_onAccountAccessed = 51;
        static final int TRANSACTION_peekAuthToken = 17;
        static final int TRANSACTION_registerAccountListener = 47;
        static final int TRANSACTION_removeAccount = 12;
        static final int TRANSACTION_removeAccountAsUser = 13;
        static final int TRANSACTION_removeAccountExplicitly = 14;
        static final int TRANSACTION_removeSharedAccountAsUser = 32;
        static final int TRANSACTION_renameAccount = 34;
        static final int TRANSACTION_renameSharedAccountAsUser = 36;
        static final int TRANSACTION_setAccountVisibility = 44;
        static final int TRANSACTION_setAuthToken = 18;
        static final int TRANSACTION_setPassword = 19;
        static final int TRANSACTION_setUserData = 21;
        static final int TRANSACTION_someUserHasAccount = 40;
        static final int TRANSACTION_startAddAccountSession = 37;
        static final int TRANSACTION_startUpdateCredentialsSession = 38;
        static final int TRANSACTION_unregisterAccountListener = 48;
        static final int TRANSACTION_updateAppPermission = 22;
        static final int TRANSACTION_updateCredentials = 26;

        public Stub()
        {
            attachInterface(this, "android.accounts.IAccountManager");
        }
    }

    private static class Stub.Proxy
        implements IAccountManager
    {

        public boolean accountAuthenticated(Account account)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(account == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(29, parcel, parcel1, 0);
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
            parcel.writeInt(0);
              goto _L1
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
        }

        public void addAccount(IAccountManagerResponse iaccountmanagerresponse, String s, String s1, String as[], boolean flag, Bundle bundle)
            throws RemoteException
        {
            int i;
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(iaccountmanagerresponse == null)
                break MISSING_BLOCK_LABEL_35;
            ibinder = iaccountmanagerresponse.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeStringArray(as);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_131;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(24, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iaccountmanagerresponse;
            parcel1.recycle();
            parcel.recycle();
            throw iaccountmanagerresponse;
        }

        public void addAccountAsUser(IAccountManagerResponse iaccountmanagerresponse, String s, String s1, String as[], boolean flag, Bundle bundle, int i)
            throws RemoteException
        {
            int j;
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            j = 1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(iaccountmanagerresponse == null)
                break MISSING_BLOCK_LABEL_35;
            ibinder = iaccountmanagerresponse.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeStringArray(as);
            if(!flag)
                j = 0;
            parcel.writeInt(j);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_138;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(25, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iaccountmanagerresponse;
            parcel1.recycle();
            parcel.recycle();
            throw iaccountmanagerresponse;
        }

        public boolean addAccountExplicitly(Account account, String s, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(account == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_129;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            int i;
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
_L2:
            parcel.writeInt(0);
              goto _L3
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
            parcel.writeInt(0);
              goto _L4
        }

        public boolean addAccountExplicitlyWithVisibility(Account account, String s, Bundle bundle, Map map)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(account == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_136;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            int i;
            parcel.writeMap(map);
            mRemote.transact(43, parcel, parcel1, 0);
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
_L2:
            parcel.writeInt(0);
              goto _L3
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
            parcel.writeInt(0);
              goto _L4
        }

        public void addSharedAccountsFromParentUser(int i, int j, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s);
            mRemote.transact(33, parcel, parcel1, 0);
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

        public void clearPassword(Account account)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(account == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(20, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
        }

        public void confirmCredentialsAsUser(IAccountManagerResponse iaccountmanagerresponse, Account account, Bundle bundle, boolean flag, int i)
            throws RemoteException
        {
            int j;
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            j = 1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(iaccountmanagerresponse == null)
                break MISSING_BLOCK_LABEL_35;
            ibinder = iaccountmanagerresponse.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(account == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L3:
            if(bundle == null)
                break MISSING_BLOCK_LABEL_150;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            if(!flag)
                j = 0;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(28, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            iaccountmanagerresponse;
            parcel1.recycle();
            parcel.recycle();
            throw iaccountmanagerresponse;
            parcel.writeInt(0);
              goto _L4
        }

        public void copyAccountToUser(IAccountManagerResponse iaccountmanagerresponse, Account account, int i, int j)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(iaccountmanagerresponse == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = iaccountmanagerresponse.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(account == null)
                break MISSING_BLOCK_LABEL_102;
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iaccountmanagerresponse;
            parcel1.recycle();
            parcel.recycle();
            throw iaccountmanagerresponse;
        }

        public IntentSender createRequestAccountAccessIntentSenderAsUser(Account account, String s, UserHandle userhandle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(account == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L5:
            parcel.writeString(s);
            if(userhandle == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            userhandle.writeToParcel(parcel, 0);
_L6:
            mRemote.transact(50, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_144;
            account = (IntentSender)IntentSender.CREATOR.createFromParcel(parcel1);
_L7:
            parcel1.recycle();
            parcel.recycle();
            return account;
_L2:
            parcel.writeInt(0);
              goto _L5
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
_L4:
            parcel.writeInt(0);
              goto _L6
            account = null;
              goto _L7
        }

        public void editProperties(IAccountManagerResponse iaccountmanagerresponse, String s, boolean flag)
            throws RemoteException
        {
            IBinder ibinder;
            int i;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(iaccountmanagerresponse == null)
                break MISSING_BLOCK_LABEL_35;
            ibinder = iaccountmanagerresponse.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(27, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iaccountmanagerresponse;
            parcel1.recycle();
            parcel.recycle();
            throw iaccountmanagerresponse;
        }

        public void finishSessionAsUser(IAccountManagerResponse iaccountmanagerresponse, Bundle bundle, boolean flag, Bundle bundle1, int i)
            throws RemoteException
        {
            int j;
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            j = 1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(iaccountmanagerresponse == null)
                break MISSING_BLOCK_LABEL_35;
            ibinder = iaccountmanagerresponse.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(bundle == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L3:
            if(!flag)
                j = 0;
            parcel.writeInt(j);
            if(bundle1 == null)
                break MISSING_BLOCK_LABEL_157;
            parcel.writeInt(1);
            bundle1.writeToParcel(parcel, 0);
_L4:
            parcel.writeInt(i);
            mRemote.transact(39, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            iaccountmanagerresponse;
            parcel1.recycle();
            parcel.recycle();
            throw iaccountmanagerresponse;
            parcel.writeInt(0);
              goto _L4
        }

        public void getAccountByTypeAndFeatures(IAccountManagerResponse iaccountmanagerresponse, String s, String as[], String s1)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(iaccountmanagerresponse == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = iaccountmanagerresponse.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeStringArray(as);
            parcel.writeString(s1);
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iaccountmanagerresponse;
            parcel1.recycle();
            parcel.recycle();
            throw iaccountmanagerresponse;
        }

        public int getAccountVisibility(Account account, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(account == null)
                break MISSING_BLOCK_LABEL_75;
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L1:
            int i;
            parcel.writeString(s);
            mRemote.transact(45, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
        }

        public Account[] getAccounts(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            s = (Account[])parcel1.createTypedArray(Account.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public Map getAccountsAndVisibilityForPackage(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(46, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readHashMap(getClass().getClassLoader());
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public Account[] getAccountsAsUser(String s, int i, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeString(s1);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            s = (Account[])parcel1.createTypedArray(Account.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void getAccountsByFeatures(IAccountManagerResponse iaccountmanagerresponse, String s, String as[], String s1)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(iaccountmanagerresponse == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = iaccountmanagerresponse.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeStringArray(as);
            parcel.writeString(s1);
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iaccountmanagerresponse;
            parcel1.recycle();
            parcel.recycle();
            throw iaccountmanagerresponse;
        }

        public Account[] getAccountsByTypeForPackage(String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            s = (Account[])parcel1.createTypedArray(Account.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public Account[] getAccountsForPackage(String s, int i, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeString(s1);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            s = (Account[])parcel1.createTypedArray(Account.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void getAuthToken(IAccountManagerResponse iaccountmanagerresponse, Account account, String s, boolean flag, boolean flag1, Bundle bundle)
            throws RemoteException
        {
            IBinder ibinder;
            boolean flag2;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            flag2 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(iaccountmanagerresponse == null)
                break MISSING_BLOCK_LABEL_35;
            ibinder = iaccountmanagerresponse.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(account == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(flag1)
                i = ((flag2) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_182;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(23, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            iaccountmanagerresponse;
            parcel1.recycle();
            parcel.recycle();
            throw iaccountmanagerresponse;
            parcel.writeInt(0);
              goto _L4
        }

        public void getAuthTokenLabel(IAccountManagerResponse iaccountmanagerresponse, String s, String s1)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(iaccountmanagerresponse == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = iaccountmanagerresponse.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(30, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iaccountmanagerresponse;
            parcel1.recycle();
            parcel.recycle();
            throw iaccountmanagerresponse;
        }

        public AuthenticatorDescription[] getAuthenticatorTypes(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            AuthenticatorDescription aauthenticatordescription[];
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            parcel.writeInt(i);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            aauthenticatordescription = (AuthenticatorDescription[])parcel1.createTypedArray(AuthenticatorDescription.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return aauthenticatordescription;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.accounts.IAccountManager";
        }

        public Map getPackagesAndVisibilityForAccount(Account account)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(account == null)
                break MISSING_BLOCK_LABEL_70;
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(42, parcel, parcel1, 0);
            parcel1.readException();
            account = parcel1.readHashMap(getClass().getClassLoader());
            parcel1.recycle();
            parcel.recycle();
            return account;
            parcel.writeInt(0);
              goto _L1
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
        }

        public String getPassword(Account account)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(account == null)
                break MISSING_BLOCK_LABEL_62;
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            account = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return account;
            parcel.writeInt(0);
              goto _L1
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
        }

        public String getPreviousName(Account account)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(account == null)
                break MISSING_BLOCK_LABEL_63;
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(35, parcel, parcel1, 0);
            parcel1.readException();
            account = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return account;
            parcel.writeInt(0);
              goto _L1
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
        }

        public Account[] getSharedAccountsAsUser(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            Account aaccount[];
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            parcel.writeInt(i);
            mRemote.transact(31, parcel, parcel1, 0);
            parcel1.readException();
            aaccount = (Account[])parcel1.createTypedArray(Account.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return aaccount;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getUserData(Account account, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(account == null)
                break MISSING_BLOCK_LABEL_72;
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            account = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return account;
            parcel.writeInt(0);
              goto _L1
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
        }

        public boolean hasAccountAccess(Account account, String s, UserHandle userhandle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(account == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            if(userhandle == null)
                break MISSING_BLOCK_LABEL_129;
            parcel.writeInt(1);
            userhandle.writeToParcel(parcel, 0);
_L4:
            int i;
            mRemote.transact(49, parcel, parcel1, 0);
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
_L2:
            parcel.writeInt(0);
              goto _L3
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
            parcel.writeInt(0);
              goto _L4
        }

        public void hasFeatures(IAccountManagerResponse iaccountmanagerresponse, Account account, String as[], String s)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(iaccountmanagerresponse == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = iaccountmanagerresponse.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(account == null)
                break MISSING_BLOCK_LABEL_102;
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L1:
            parcel.writeStringArray(as);
            parcel.writeString(s);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iaccountmanagerresponse;
            parcel1.recycle();
            parcel.recycle();
            throw iaccountmanagerresponse;
        }

        public void invalidateAuthToken(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            parcel.writeString(s);
            parcel.writeString(s1);
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

        public void isCredentialsUpdateSuggested(IAccountManagerResponse iaccountmanagerresponse, Account account, String s)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(iaccountmanagerresponse == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = iaccountmanagerresponse.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(account == null)
                break MISSING_BLOCK_LABEL_95;
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            mRemote.transact(41, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iaccountmanagerresponse;
            parcel1.recycle();
            parcel.recycle();
            throw iaccountmanagerresponse;
        }

        public void onAccountAccessed(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            parcel.writeString(s);
            mRemote.transact(51, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String peekAuthToken(Account account, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(account == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            account = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return account;
            parcel.writeInt(0);
              goto _L1
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
        }

        public void registerAccountListener(String as[], String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            parcel.writeStringArray(as);
            parcel.writeString(s);
            mRemote.transact(47, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            as;
            parcel1.recycle();
            parcel.recycle();
            throw as;
        }

        public void removeAccount(IAccountManagerResponse iaccountmanagerresponse, Account account, boolean flag)
            throws RemoteException
        {
            int i;
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(iaccountmanagerresponse == null)
                break MISSING_BLOCK_LABEL_35;
            ibinder = iaccountmanagerresponse.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(account == null)
                break MISSING_BLOCK_LABEL_103;
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iaccountmanagerresponse;
            parcel1.recycle();
            parcel.recycle();
            throw iaccountmanagerresponse;
        }

        public void removeAccountAsUser(IAccountManagerResponse iaccountmanagerresponse, Account account, boolean flag, int i)
            throws RemoteException
        {
            int j;
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            j = 1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(iaccountmanagerresponse == null)
                break MISSING_BLOCK_LABEL_35;
            ibinder = iaccountmanagerresponse.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(account == null)
                break MISSING_BLOCK_LABEL_110;
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                j = 0;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iaccountmanagerresponse;
            parcel1.recycle();
            parcel.recycle();
            throw iaccountmanagerresponse;
        }

        public boolean removeAccountExplicitly(Account account)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(account == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L1:
            int i;
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
            parcel.writeInt(0);
              goto _L1
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
        }

        public boolean removeSharedAccountAsUser(Account account, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(account == null)
                break MISSING_BLOCK_LABEL_81;
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L1:
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
            parcel.writeInt(0);
              goto _L1
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
        }

        public void renameAccount(IAccountManagerResponse iaccountmanagerresponse, Account account, String s)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(iaccountmanagerresponse == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = iaccountmanagerresponse.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(account == null)
                break MISSING_BLOCK_LABEL_95;
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            mRemote.transact(34, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iaccountmanagerresponse;
            parcel1.recycle();
            parcel.recycle();
            throw iaccountmanagerresponse;
        }

        public boolean renameSharedAccountAsUser(Account account, String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(account == null)
                break MISSING_BLOCK_LABEL_94;
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(36, parcel, parcel1, 0);
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
            parcel.writeInt(0);
              goto _L1
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
        }

        public boolean setAccountVisibility(Account account, String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(account == null)
                break MISSING_BLOCK_LABEL_94;
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(44, parcel, parcel1, 0);
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
            parcel.writeInt(0);
              goto _L1
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
        }

        public void setAuthToken(Account account, String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(account == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(18, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
        }

        public void setPassword(Account account, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(account == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            mRemote.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
        }

        public void setUserData(Account account, String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(account == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(21, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
        }

        public boolean someUserHasAccount(Account account)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(account == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(40, parcel, parcel1, 0);
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
            parcel.writeInt(0);
              goto _L1
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
        }

        public void startAddAccountSession(IAccountManagerResponse iaccountmanagerresponse, String s, String s1, String as[], boolean flag, Bundle bundle)
            throws RemoteException
        {
            int i;
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(iaccountmanagerresponse == null)
                break MISSING_BLOCK_LABEL_35;
            ibinder = iaccountmanagerresponse.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeStringArray(as);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_131;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(37, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iaccountmanagerresponse;
            parcel1.recycle();
            parcel.recycle();
            throw iaccountmanagerresponse;
        }

        public void startUpdateCredentialsSession(IAccountManagerResponse iaccountmanagerresponse, Account account, String s, boolean flag, Bundle bundle)
            throws RemoteException
        {
            int i;
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(iaccountmanagerresponse == null)
                break MISSING_BLOCK_LABEL_35;
            ibinder = iaccountmanagerresponse.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(account == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_157;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(38, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            iaccountmanagerresponse;
            parcel1.recycle();
            parcel.recycle();
            throw iaccountmanagerresponse;
            parcel.writeInt(0);
              goto _L4
        }

        public void unregisterAccountListener(String as[], String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            parcel.writeStringArray(as);
            parcel.writeString(s);
            mRemote.transact(48, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            as;
            parcel1.recycle();
            parcel.recycle();
            throw as;
        }

        public void updateAppPermission(Account account, String s, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(account == null)
                break MISSING_BLOCK_LABEL_96;
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(22, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            account;
            parcel1.recycle();
            parcel.recycle();
            throw account;
        }

        public void updateCredentials(IAccountManagerResponse iaccountmanagerresponse, Account account, String s, boolean flag, Bundle bundle)
            throws RemoteException
        {
            int i;
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManager");
            if(iaccountmanagerresponse == null)
                break MISSING_BLOCK_LABEL_35;
            ibinder = iaccountmanagerresponse.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(account == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            account.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_157;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(26, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            iaccountmanagerresponse;
            parcel1.recycle();
            parcel.recycle();
            throw iaccountmanagerresponse;
            parcel.writeInt(0);
              goto _L4
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract boolean accountAuthenticated(Account account)
        throws RemoteException;

    public abstract void addAccount(IAccountManagerResponse iaccountmanagerresponse, String s, String s1, String as[], boolean flag, Bundle bundle)
        throws RemoteException;

    public abstract void addAccountAsUser(IAccountManagerResponse iaccountmanagerresponse, String s, String s1, String as[], boolean flag, Bundle bundle, int i)
        throws RemoteException;

    public abstract boolean addAccountExplicitly(Account account, String s, Bundle bundle)
        throws RemoteException;

    public abstract boolean addAccountExplicitlyWithVisibility(Account account, String s, Bundle bundle, Map map)
        throws RemoteException;

    public abstract void addSharedAccountsFromParentUser(int i, int j, String s)
        throws RemoteException;

    public abstract void clearPassword(Account account)
        throws RemoteException;

    public abstract void confirmCredentialsAsUser(IAccountManagerResponse iaccountmanagerresponse, Account account, Bundle bundle, boolean flag, int i)
        throws RemoteException;

    public abstract void copyAccountToUser(IAccountManagerResponse iaccountmanagerresponse, Account account, int i, int j)
        throws RemoteException;

    public abstract IntentSender createRequestAccountAccessIntentSenderAsUser(Account account, String s, UserHandle userhandle)
        throws RemoteException;

    public abstract void editProperties(IAccountManagerResponse iaccountmanagerresponse, String s, boolean flag)
        throws RemoteException;

    public abstract void finishSessionAsUser(IAccountManagerResponse iaccountmanagerresponse, Bundle bundle, boolean flag, Bundle bundle1, int i)
        throws RemoteException;

    public abstract void getAccountByTypeAndFeatures(IAccountManagerResponse iaccountmanagerresponse, String s, String as[], String s1)
        throws RemoteException;

    public abstract int getAccountVisibility(Account account, String s)
        throws RemoteException;

    public abstract Account[] getAccounts(String s, String s1)
        throws RemoteException;

    public abstract Map getAccountsAndVisibilityForPackage(String s, String s1)
        throws RemoteException;

    public abstract Account[] getAccountsAsUser(String s, int i, String s1)
        throws RemoteException;

    public abstract void getAccountsByFeatures(IAccountManagerResponse iaccountmanagerresponse, String s, String as[], String s1)
        throws RemoteException;

    public abstract Account[] getAccountsByTypeForPackage(String s, String s1, String s2)
        throws RemoteException;

    public abstract Account[] getAccountsForPackage(String s, int i, String s1)
        throws RemoteException;

    public abstract void getAuthToken(IAccountManagerResponse iaccountmanagerresponse, Account account, String s, boolean flag, boolean flag1, Bundle bundle)
        throws RemoteException;

    public abstract void getAuthTokenLabel(IAccountManagerResponse iaccountmanagerresponse, String s, String s1)
        throws RemoteException;

    public abstract AuthenticatorDescription[] getAuthenticatorTypes(int i)
        throws RemoteException;

    public abstract Map getPackagesAndVisibilityForAccount(Account account)
        throws RemoteException;

    public abstract String getPassword(Account account)
        throws RemoteException;

    public abstract String getPreviousName(Account account)
        throws RemoteException;

    public abstract Account[] getSharedAccountsAsUser(int i)
        throws RemoteException;

    public abstract String getUserData(Account account, String s)
        throws RemoteException;

    public abstract boolean hasAccountAccess(Account account, String s, UserHandle userhandle)
        throws RemoteException;

    public abstract void hasFeatures(IAccountManagerResponse iaccountmanagerresponse, Account account, String as[], String s)
        throws RemoteException;

    public abstract void invalidateAuthToken(String s, String s1)
        throws RemoteException;

    public abstract void isCredentialsUpdateSuggested(IAccountManagerResponse iaccountmanagerresponse, Account account, String s)
        throws RemoteException;

    public abstract void onAccountAccessed(String s)
        throws RemoteException;

    public abstract String peekAuthToken(Account account, String s)
        throws RemoteException;

    public abstract void registerAccountListener(String as[], String s)
        throws RemoteException;

    public abstract void removeAccount(IAccountManagerResponse iaccountmanagerresponse, Account account, boolean flag)
        throws RemoteException;

    public abstract void removeAccountAsUser(IAccountManagerResponse iaccountmanagerresponse, Account account, boolean flag, int i)
        throws RemoteException;

    public abstract boolean removeAccountExplicitly(Account account)
        throws RemoteException;

    public abstract boolean removeSharedAccountAsUser(Account account, int i)
        throws RemoteException;

    public abstract void renameAccount(IAccountManagerResponse iaccountmanagerresponse, Account account, String s)
        throws RemoteException;

    public abstract boolean renameSharedAccountAsUser(Account account, String s, int i)
        throws RemoteException;

    public abstract boolean setAccountVisibility(Account account, String s, int i)
        throws RemoteException;

    public abstract void setAuthToken(Account account, String s, String s1)
        throws RemoteException;

    public abstract void setPassword(Account account, String s)
        throws RemoteException;

    public abstract void setUserData(Account account, String s, String s1)
        throws RemoteException;

    public abstract boolean someUserHasAccount(Account account)
        throws RemoteException;

    public abstract void startAddAccountSession(IAccountManagerResponse iaccountmanagerresponse, String s, String s1, String as[], boolean flag, Bundle bundle)
        throws RemoteException;

    public abstract void startUpdateCredentialsSession(IAccountManagerResponse iaccountmanagerresponse, Account account, String s, boolean flag, Bundle bundle)
        throws RemoteException;

    public abstract void unregisterAccountListener(String as[], String s)
        throws RemoteException;

    public abstract void updateAppPermission(Account account, String s, int i, boolean flag)
        throws RemoteException;

    public abstract void updateCredentials(IAccountManagerResponse iaccountmanagerresponse, Account account, String s, boolean flag, Bundle bundle)
        throws RemoteException;
}
