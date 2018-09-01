// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.accounts;

import android.content.Context;
import android.os.*;
import android.text.TextUtils;
import android.util.Log;
import java.util.Arrays;

// Referenced classes of package android.accounts:
//            NetworkErrorException, IAccountAuthenticatorResponse, AccountAuthenticatorResponse, Account, 
//            AccountManager

public abstract class AbstractAccountAuthenticator
{
    private class Transport extends IAccountAuthenticator.Stub
    {

        public void addAccount(IAccountAuthenticatorResponse iaccountauthenticatorresponse, String s, String s1, String as[], Bundle bundle)
            throws RemoteException
        {
            if(Log.isLoggable("AccountAuthenticator", 2))
            {
                Object obj = (new StringBuilder()).append("addAccount: accountType ").append(s).append(", authTokenType ").append(s1).append(", features ");
                Object obj1;
                if(as == null)
                    obj1 = "[]";
                else
                    obj1 = Arrays.toString(as);
                Log.v("AccountAuthenticator", ((StringBuilder) (obj)).append(((String) (obj1))).toString());
            }
            AbstractAccountAuthenticator._2D_wrap0(AbstractAccountAuthenticator.this);
            obj1 = AbstractAccountAuthenticator.this;
            obj = JVM INSTR new #62  <Class AccountAuthenticatorResponse>;
            ((AccountAuthenticatorResponse) (obj)).AccountAuthenticatorResponse(iaccountauthenticatorresponse);
            s1 = ((AbstractAccountAuthenticator) (obj1)).addAccount(((AccountAuthenticatorResponse) (obj)), s, s1, as, bundle);
            if(!Log.isLoggable("AccountAuthenticator", 2))
                break MISSING_BLOCK_LABEL_155;
            if(s1 == null)
                break MISSING_BLOCK_LABEL_122;
            s1.keySet();
            as = JVM INSTR new #36  <Class StringBuilder>;
            as.StringBuilder();
            Log.v("AccountAuthenticator", as.append("addAccount: result ").append(AccountManager.sanitizeResult(s1)).toString());
            if(s1 == null)
                break MISSING_BLOCK_LABEL_177;
            iaccountauthenticatorresponse.onResult(s1);
_L1:
            return;
            try
            {
                iaccountauthenticatorresponse.onError(5, "null bundle returned");
            }
            // Misplaced declaration of an exception variable
            catch(String s1)
            {
                AbstractAccountAuthenticator._2D_wrap1(AbstractAccountAuthenticator.this, iaccountauthenticatorresponse, "addAccount", s, s1);
            }
              goto _L1
        }

        public void addAccountFromCredentials(IAccountAuthenticatorResponse iaccountauthenticatorresponse, Account account, Bundle bundle)
            throws RemoteException
        {
            AbstractAccountAuthenticator._2D_wrap0(AbstractAccountAuthenticator.this);
            AbstractAccountAuthenticator abstractaccountauthenticator = AbstractAccountAuthenticator.this;
            AccountAuthenticatorResponse accountauthenticatorresponse = JVM INSTR new #62  <Class AccountAuthenticatorResponse>;
            accountauthenticatorresponse.AccountAuthenticatorResponse(iaccountauthenticatorresponse);
            bundle = abstractaccountauthenticator.addAccountFromCredentials(accountauthenticatorresponse, account, bundle);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_45;
            iaccountauthenticatorresponse.onResult(bundle);
_L1:
            return;
            bundle;
            AbstractAccountAuthenticator._2D_wrap1(AbstractAccountAuthenticator.this, iaccountauthenticatorresponse, "addAccountFromCredentials", account.toString(), bundle);
              goto _L1
        }

        public void confirmCredentials(IAccountAuthenticatorResponse iaccountauthenticatorresponse, Account account, Bundle bundle)
            throws RemoteException
        {
            if(Log.isLoggable("AccountAuthenticator", 2))
                Log.v("AccountAuthenticator", (new StringBuilder()).append("confirmCredentials: ").append(account).toString());
            AbstractAccountAuthenticator._2D_wrap0(AbstractAccountAuthenticator.this);
            Object obj;
            AbstractAccountAuthenticator abstractaccountauthenticator = AbstractAccountAuthenticator.this;
            obj = JVM INSTR new #62  <Class AccountAuthenticatorResponse>;
            ((AccountAuthenticatorResponse) (obj)).AccountAuthenticatorResponse(iaccountauthenticatorresponse);
            obj = abstractaccountauthenticator.confirmCredentials(((AccountAuthenticatorResponse) (obj)), account, bundle);
            if(!Log.isLoggable("AccountAuthenticator", 2))
                break MISSING_BLOCK_LABEL_120;
            if(obj == null)
                break MISSING_BLOCK_LABEL_89;
            ((Bundle) (obj)).keySet();
            bundle = JVM INSTR new #36  <Class StringBuilder>;
            bundle.StringBuilder();
            Log.v("AccountAuthenticator", bundle.append("confirmCredentials: result ").append(AccountManager.sanitizeResult(((Bundle) (obj)))).toString());
            if(obj == null)
                break MISSING_BLOCK_LABEL_133;
            iaccountauthenticatorresponse.onResult(((Bundle) (obj)));
_L1:
            return;
            bundle;
            AbstractAccountAuthenticator._2D_wrap1(AbstractAccountAuthenticator.this, iaccountauthenticatorresponse, "confirmCredentials", account.toString(), bundle);
              goto _L1
        }

        public void editProperties(IAccountAuthenticatorResponse iaccountauthenticatorresponse, String s)
            throws RemoteException
        {
            AbstractAccountAuthenticator._2D_wrap0(AbstractAccountAuthenticator.this);
            Object obj;
            obj = AbstractAccountAuthenticator.this;
            AccountAuthenticatorResponse accountauthenticatorresponse = JVM INSTR new #62  <Class AccountAuthenticatorResponse>;
            accountauthenticatorresponse.AccountAuthenticatorResponse(iaccountauthenticatorresponse);
            obj = ((AbstractAccountAuthenticator) (obj)).editProperties(accountauthenticatorresponse, s);
            if(obj == null)
                break MISSING_BLOCK_LABEL_42;
            iaccountauthenticatorresponse.onResult(((Bundle) (obj)));
_L1:
            return;
            Exception exception;
            exception;
            AbstractAccountAuthenticator._2D_wrap1(AbstractAccountAuthenticator.this, iaccountauthenticatorresponse, "editProperties", s, exception);
              goto _L1
        }

        public void finishSession(IAccountAuthenticatorResponse iaccountauthenticatorresponse, String s, Bundle bundle)
            throws RemoteException
        {
            if(Log.isLoggable("AccountAuthenticator", 2))
                Log.v("AccountAuthenticator", (new StringBuilder()).append("finishSession: accountType ").append(s).toString());
            AbstractAccountAuthenticator._2D_wrap0(AbstractAccountAuthenticator.this);
            AbstractAccountAuthenticator abstractaccountauthenticator = AbstractAccountAuthenticator.this;
            AccountAuthenticatorResponse accountauthenticatorresponse = JVM INSTR new #62  <Class AccountAuthenticatorResponse>;
            accountauthenticatorresponse.AccountAuthenticatorResponse(iaccountauthenticatorresponse);
            bundle = abstractaccountauthenticator.finishSession(accountauthenticatorresponse, s, bundle);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_77;
            bundle.keySet();
            if(Log.isLoggable("AccountAuthenticator", 2))
            {
                StringBuilder stringbuilder = JVM INSTR new #36  <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Log.v("AccountAuthenticator", stringbuilder.append("finishSession: result ").append(AccountManager.sanitizeResult(bundle)).toString());
            }
            if(bundle == null)
                break MISSING_BLOCK_LABEL_130;
            iaccountauthenticatorresponse.onResult(bundle);
_L1:
            return;
            bundle;
            AbstractAccountAuthenticator._2D_wrap1(AbstractAccountAuthenticator.this, iaccountauthenticatorresponse, "finishSession", s, bundle);
              goto _L1
        }

        public void getAccountCredentialsForCloning(IAccountAuthenticatorResponse iaccountauthenticatorresponse, Account account)
            throws RemoteException
        {
            AbstractAccountAuthenticator._2D_wrap0(AbstractAccountAuthenticator.this);
            Object obj;
            AbstractAccountAuthenticator abstractaccountauthenticator = AbstractAccountAuthenticator.this;
            obj = JVM INSTR new #62  <Class AccountAuthenticatorResponse>;
            ((AccountAuthenticatorResponse) (obj)).AccountAuthenticatorResponse(iaccountauthenticatorresponse);
            obj = abstractaccountauthenticator.getAccountCredentialsForCloning(((AccountAuthenticatorResponse) (obj)), account);
            if(obj == null)
                break MISSING_BLOCK_LABEL_45;
            iaccountauthenticatorresponse.onResult(((Bundle) (obj)));
_L1:
            return;
            Exception exception;
            exception;
            AbstractAccountAuthenticator._2D_wrap1(AbstractAccountAuthenticator.this, iaccountauthenticatorresponse, "getAccountCredentialsForCloning", account.toString(), exception);
              goto _L1
        }

        public void getAccountRemovalAllowed(IAccountAuthenticatorResponse iaccountauthenticatorresponse, Account account)
            throws RemoteException
        {
            AbstractAccountAuthenticator._2D_wrap0(AbstractAccountAuthenticator.this);
            Object obj;
            obj = AbstractAccountAuthenticator.this;
            AccountAuthenticatorResponse accountauthenticatorresponse = JVM INSTR new #62  <Class AccountAuthenticatorResponse>;
            accountauthenticatorresponse.AccountAuthenticatorResponse(iaccountauthenticatorresponse);
            obj = ((AbstractAccountAuthenticator) (obj)).getAccountRemovalAllowed(accountauthenticatorresponse, account);
            if(obj == null)
                break MISSING_BLOCK_LABEL_42;
            iaccountauthenticatorresponse.onResult(((Bundle) (obj)));
_L1:
            return;
            Exception exception;
            exception;
            AbstractAccountAuthenticator._2D_wrap1(AbstractAccountAuthenticator.this, iaccountauthenticatorresponse, "getAccountRemovalAllowed", account.toString(), exception);
              goto _L1
        }

        public void getAuthToken(IAccountAuthenticatorResponse iaccountauthenticatorresponse, Account account, String s, Bundle bundle)
            throws RemoteException
        {
            if(Log.isLoggable("AccountAuthenticator", 2))
                Log.v("AccountAuthenticator", (new StringBuilder()).append("getAuthToken: ").append(account).append(", authTokenType ").append(s).toString());
            AbstractAccountAuthenticator._2D_wrap0(AbstractAccountAuthenticator.this);
            Object obj;
            AbstractAccountAuthenticator abstractaccountauthenticator = AbstractAccountAuthenticator.this;
            obj = JVM INSTR new #62  <Class AccountAuthenticatorResponse>;
            ((AccountAuthenticatorResponse) (obj)).AccountAuthenticatorResponse(iaccountauthenticatorresponse);
            obj = abstractaccountauthenticator.getAuthToken(((AccountAuthenticatorResponse) (obj)), account, s, bundle);
            if(!Log.isLoggable("AccountAuthenticator", 2))
                break MISSING_BLOCK_LABEL_134;
            if(obj == null)
                break MISSING_BLOCK_LABEL_100;
            ((Bundle) (obj)).keySet();
            bundle = JVM INSTR new #36  <Class StringBuilder>;
            bundle.StringBuilder();
            Log.v("AccountAuthenticator", bundle.append("getAuthToken: result ").append(AccountManager.sanitizeResult(((Bundle) (obj)))).toString());
            if(obj == null)
                break MISSING_BLOCK_LABEL_147;
            iaccountauthenticatorresponse.onResult(((Bundle) (obj)));
_L1:
            return;
            bundle;
            AbstractAccountAuthenticator._2D_wrap1(AbstractAccountAuthenticator.this, iaccountauthenticatorresponse, "getAuthToken", (new StringBuilder()).append(account.toString()).append(",").append(s).toString(), bundle);
              goto _L1
        }

        public void getAuthTokenLabel(IAccountAuthenticatorResponse iaccountauthenticatorresponse, String s)
            throws RemoteException
        {
            if(Log.isLoggable("AccountAuthenticator", 2))
                Log.v("AccountAuthenticator", (new StringBuilder()).append("getAuthTokenLabel: authTokenType ").append(s).toString());
            AbstractAccountAuthenticator._2D_wrap0(AbstractAccountAuthenticator.this);
            Bundle bundle;
            bundle = JVM INSTR new #70  <Class Bundle>;
            bundle.Bundle();
            bundle.putString("authTokenLabelKey", AbstractAccountAuthenticator.this.getAuthTokenLabel(s));
            if(!Log.isLoggable("AccountAuthenticator", 2))
                break MISSING_BLOCK_LABEL_114;
            if(bundle == null)
                break MISSING_BLOCK_LABEL_81;
            bundle.keySet();
            StringBuilder stringbuilder = JVM INSTR new #36  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.v("AccountAuthenticator", stringbuilder.append("getAuthTokenLabel: result ").append(AccountManager.sanitizeResult(bundle)).toString());
            iaccountauthenticatorresponse.onResult(bundle);
_L1:
            return;
            Exception exception;
            exception;
            AbstractAccountAuthenticator._2D_wrap1(AbstractAccountAuthenticator.this, iaccountauthenticatorresponse, "getAuthTokenLabel", s, exception);
              goto _L1
        }

        public void hasFeatures(IAccountAuthenticatorResponse iaccountauthenticatorresponse, Account account, String as[])
            throws RemoteException
        {
            AbstractAccountAuthenticator._2D_wrap0(AbstractAccountAuthenticator.this);
            AbstractAccountAuthenticator abstractaccountauthenticator = AbstractAccountAuthenticator.this;
            AccountAuthenticatorResponse accountauthenticatorresponse = JVM INSTR new #62  <Class AccountAuthenticatorResponse>;
            accountauthenticatorresponse.AccountAuthenticatorResponse(iaccountauthenticatorresponse);
            as = abstractaccountauthenticator.hasFeatures(accountauthenticatorresponse, account, as);
            if(as == null)
                break MISSING_BLOCK_LABEL_45;
            iaccountauthenticatorresponse.onResult(as);
_L1:
            return;
            as;
            AbstractAccountAuthenticator._2D_wrap1(AbstractAccountAuthenticator.this, iaccountauthenticatorresponse, "hasFeatures", account.toString(), as);
              goto _L1
        }

        public void isCredentialsUpdateSuggested(IAccountAuthenticatorResponse iaccountauthenticatorresponse, Account account, String s)
            throws RemoteException
        {
            AbstractAccountAuthenticator._2D_wrap0(AbstractAccountAuthenticator.this);
            AbstractAccountAuthenticator abstractaccountauthenticator = AbstractAccountAuthenticator.this;
            AccountAuthenticatorResponse accountauthenticatorresponse = JVM INSTR new #62  <Class AccountAuthenticatorResponse>;
            accountauthenticatorresponse.AccountAuthenticatorResponse(iaccountauthenticatorresponse);
            s = abstractaccountauthenticator.isCredentialsUpdateSuggested(accountauthenticatorresponse, account, s);
            if(s == null)
                break MISSING_BLOCK_LABEL_45;
            iaccountauthenticatorresponse.onResult(s);
_L1:
            return;
            s;
            AbstractAccountAuthenticator._2D_wrap1(AbstractAccountAuthenticator.this, iaccountauthenticatorresponse, "isCredentialsUpdateSuggested", account.toString(), s);
              goto _L1
        }

        public void startAddAccountSession(IAccountAuthenticatorResponse iaccountauthenticatorresponse, String s, String s1, String as[], Bundle bundle)
            throws RemoteException
        {
            if(Log.isLoggable("AccountAuthenticator", 2))
            {
                Object obj = (new StringBuilder()).append("startAddAccountSession: accountType ").append(s).append(", authTokenType ").append(s1).append(", features ");
                Object obj1;
                if(as == null)
                    obj1 = "[]";
                else
                    obj1 = Arrays.toString(as);
                Log.v("AccountAuthenticator", ((StringBuilder) (obj)).append(((String) (obj1))).toString());
            }
            AbstractAccountAuthenticator._2D_wrap0(AbstractAccountAuthenticator.this);
            obj = AbstractAccountAuthenticator.this;
            obj1 = JVM INSTR new #62  <Class AccountAuthenticatorResponse>;
            ((AccountAuthenticatorResponse) (obj1)).AccountAuthenticatorResponse(iaccountauthenticatorresponse);
            as = ((AbstractAccountAuthenticator) (obj)).startAddAccountSession(((AccountAuthenticatorResponse) (obj1)), s, s1, as, bundle);
            if(!Log.isLoggable("AccountAuthenticator", 2))
                break MISSING_BLOCK_LABEL_156;
            if(as == null)
                break MISSING_BLOCK_LABEL_125;
            as.keySet();
            s1 = JVM INSTR new #36  <Class StringBuilder>;
            s1.StringBuilder();
            Log.v("AccountAuthenticator", s1.append("startAddAccountSession: result ").append(AccountManager.sanitizeResult(as)).toString());
            if(as == null)
                break MISSING_BLOCK_LABEL_169;
            iaccountauthenticatorresponse.onResult(as);
_L1:
            return;
            s1;
            AbstractAccountAuthenticator._2D_wrap1(AbstractAccountAuthenticator.this, iaccountauthenticatorresponse, "startAddAccountSession", s, s1);
              goto _L1
        }

        public void startUpdateCredentialsSession(IAccountAuthenticatorResponse iaccountauthenticatorresponse, Account account, String s, Bundle bundle)
            throws RemoteException
        {
            if(Log.isLoggable("AccountAuthenticator", 2))
                Log.v("AccountAuthenticator", (new StringBuilder()).append("startUpdateCredentialsSession: ").append(account).append(", authTokenType ").append(s).toString());
            AbstractAccountAuthenticator._2D_wrap0(AbstractAccountAuthenticator.this);
            AbstractAccountAuthenticator abstractaccountauthenticator = AbstractAccountAuthenticator.this;
            AccountAuthenticatorResponse accountauthenticatorresponse = JVM INSTR new #62  <Class AccountAuthenticatorResponse>;
            accountauthenticatorresponse.AccountAuthenticatorResponse(iaccountauthenticatorresponse);
            bundle = abstractaccountauthenticator.startUpdateCredentialsSession(accountauthenticatorresponse, account, s, bundle);
            if(!Log.isLoggable("AccountAuthenticator", 2))
                break MISSING_BLOCK_LABEL_134;
            if(bundle == null)
                break MISSING_BLOCK_LABEL_100;
            bundle.keySet();
            StringBuilder stringbuilder = JVM INSTR new #36  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.v("AccountAuthenticator", stringbuilder.append("startUpdateCredentialsSession: result ").append(AccountManager.sanitizeResult(bundle)).toString());
            if(bundle == null)
                break MISSING_BLOCK_LABEL_147;
            iaccountauthenticatorresponse.onResult(bundle);
_L1:
            return;
            bundle;
            AbstractAccountAuthenticator._2D_wrap1(AbstractAccountAuthenticator.this, iaccountauthenticatorresponse, "startUpdateCredentialsSession", (new StringBuilder()).append(account.toString()).append(",").append(s).toString(), bundle);
              goto _L1
        }

        public void updateCredentials(IAccountAuthenticatorResponse iaccountauthenticatorresponse, Account account, String s, Bundle bundle)
            throws RemoteException
        {
            if(Log.isLoggable("AccountAuthenticator", 2))
                Log.v("AccountAuthenticator", (new StringBuilder()).append("updateCredentials: ").append(account).append(", authTokenType ").append(s).toString());
            AbstractAccountAuthenticator._2D_wrap0(AbstractAccountAuthenticator.this);
            AbstractAccountAuthenticator abstractaccountauthenticator = AbstractAccountAuthenticator.this;
            AccountAuthenticatorResponse accountauthenticatorresponse = JVM INSTR new #62  <Class AccountAuthenticatorResponse>;
            accountauthenticatorresponse.AccountAuthenticatorResponse(iaccountauthenticatorresponse);
            bundle = abstractaccountauthenticator.updateCredentials(accountauthenticatorresponse, account, s, bundle);
            if(!Log.isLoggable("AccountAuthenticator", 2))
                break MISSING_BLOCK_LABEL_134;
            if(bundle == null)
                break MISSING_BLOCK_LABEL_100;
            bundle.keySet();
            StringBuilder stringbuilder = JVM INSTR new #36  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.v("AccountAuthenticator", stringbuilder.append("updateCredentials: result ").append(AccountManager.sanitizeResult(bundle)).toString());
            if(bundle == null)
                break MISSING_BLOCK_LABEL_147;
            iaccountauthenticatorresponse.onResult(bundle);
_L1:
            return;
            bundle;
            AbstractAccountAuthenticator._2D_wrap1(AbstractAccountAuthenticator.this, iaccountauthenticatorresponse, "updateCredentials", (new StringBuilder()).append(account.toString()).append(",").append(s).toString(), bundle);
              goto _L1
        }

        final AbstractAccountAuthenticator this$0;

        private Transport()
        {
            this$0 = AbstractAccountAuthenticator.this;
            super();
        }

        Transport(Transport transport)
        {
            this();
        }
    }


    static void _2D_wrap0(AbstractAccountAuthenticator abstractaccountauthenticator)
    {
        abstractaccountauthenticator.checkBinderPermission();
    }

    static void _2D_wrap1(AbstractAccountAuthenticator abstractaccountauthenticator, IAccountAuthenticatorResponse iaccountauthenticatorresponse, String s, String s1, Exception exception)
    {
        abstractaccountauthenticator.handleException(iaccountauthenticatorresponse, s, s1, exception);
    }

    public AbstractAccountAuthenticator(Context context)
    {
        mTransport = new Transport(null);
        mContext = context;
    }

    private void checkBinderPermission()
    {
        int i = Binder.getCallingUid();
        if(mContext.checkCallingOrSelfPermission("android.permission.ACCOUNT_MANAGER") != 0)
            throw new SecurityException((new StringBuilder()).append("caller uid ").append(i).append(" lacks ").append("android.permission.ACCOUNT_MANAGER").toString());
        else
            return;
    }

    private void handleException(IAccountAuthenticatorResponse iaccountauthenticatorresponse, String s, String s1, Exception exception)
        throws RemoteException
    {
        if(exception instanceof NetworkErrorException)
        {
            if(Log.isLoggable("AccountAuthenticator", 2))
                Log.v("AccountAuthenticator", (new StringBuilder()).append(s).append("(").append(s1).append(")").toString(), exception);
            iaccountauthenticatorresponse.onError(3, exception.getMessage());
        } else
        if(exception instanceof UnsupportedOperationException)
        {
            if(Log.isLoggable("AccountAuthenticator", 2))
                Log.v("AccountAuthenticator", (new StringBuilder()).append(s).append("(").append(s1).append(")").toString(), exception);
            iaccountauthenticatorresponse.onError(6, (new StringBuilder()).append(s).append(" not supported").toString());
        } else
        if(exception instanceof IllegalArgumentException)
        {
            if(Log.isLoggable("AccountAuthenticator", 2))
                Log.v("AccountAuthenticator", (new StringBuilder()).append(s).append("(").append(s1).append(")").toString(), exception);
            iaccountauthenticatorresponse.onError(7, (new StringBuilder()).append(s).append(" not supported").toString());
        } else
        {
            Log.w("AccountAuthenticator", (new StringBuilder()).append(s).append("(").append(s1).append(")").toString(), exception);
            iaccountauthenticatorresponse.onError(1, (new StringBuilder()).append(s).append(" failed").toString());
        }
    }

    public abstract Bundle addAccount(AccountAuthenticatorResponse accountauthenticatorresponse, String s, String s1, String as[], Bundle bundle)
        throws NetworkErrorException;

    public Bundle addAccountFromCredentials(final AccountAuthenticatorResponse response, Account account, Bundle bundle)
        throws NetworkErrorException
    {
        (new Thread(new Runnable() {

            public void run()
            {
                Bundle bundle1 = new Bundle();
                bundle1.putBoolean("booleanResult", false);
                response.onResult(bundle1);
            }

            final AbstractAccountAuthenticator this$0;
            final AccountAuthenticatorResponse val$response;

            
            {
                this$0 = AbstractAccountAuthenticator.this;
                response = accountauthenticatorresponse;
                super();
            }
        }
)).start();
        return null;
    }

    public abstract Bundle confirmCredentials(AccountAuthenticatorResponse accountauthenticatorresponse, Account account, Bundle bundle)
        throws NetworkErrorException;

    public abstract Bundle editProperties(AccountAuthenticatorResponse accountauthenticatorresponse, String s);

    public Bundle finishSession(AccountAuthenticatorResponse accountauthenticatorresponse, String s, Bundle bundle)
        throws NetworkErrorException
    {
        if(TextUtils.isEmpty(s))
        {
            Log.e("AccountAuthenticator", "Account type cannot be empty.");
            accountauthenticatorresponse = new Bundle();
            accountauthenticatorresponse.putInt("errorCode", 7);
            accountauthenticatorresponse.putString("errorMessage", "accountType cannot be empty.");
            return accountauthenticatorresponse;
        }
        if(bundle == null)
        {
            Log.e("AccountAuthenticator", "Session bundle cannot be null.");
            accountauthenticatorresponse = new Bundle();
            accountauthenticatorresponse.putInt("errorCode", 7);
            accountauthenticatorresponse.putString("errorMessage", "sessionBundle cannot be null.");
            return accountauthenticatorresponse;
        }
        if(!bundle.containsKey("android.accounts.AbstractAccountAuthenticato.KEY_AUTH_TOKEN_TYPE"))
        {
            s = new Bundle();
            s.putInt("errorCode", 6);
            s.putString("errorMessage", "Authenticator must override finishSession if startAddAccountSession or startUpdateCredentialsSession is overridden.");
            accountauthenticatorresponse.onResult(s);
            return s;
        }
        String s1 = bundle.getString("android.accounts.AbstractAccountAuthenticato.KEY_AUTH_TOKEN_TYPE");
        Bundle bundle1 = bundle.getBundle("android.accounts.AbstractAccountAuthenticator.KEY_OPTIONS");
        String as[] = bundle.getStringArray("android.accounts.AbstractAccountAuthenticator.KEY_REQUIRED_FEATURES");
        Account account = (Account)bundle.getParcelable("android.accounts.AbstractAccountAuthenticator.KEY_ACCOUNT");
        boolean flag = bundle.containsKey("android.accounts.AbstractAccountAuthenticator.KEY_ACCOUNT");
        Bundle bundle2 = new Bundle(bundle);
        bundle2.remove("android.accounts.AbstractAccountAuthenticato.KEY_AUTH_TOKEN_TYPE");
        bundle2.remove("android.accounts.AbstractAccountAuthenticator.KEY_REQUIRED_FEATURES");
        bundle2.remove("android.accounts.AbstractAccountAuthenticator.KEY_OPTIONS");
        bundle2.remove("android.accounts.AbstractAccountAuthenticator.KEY_ACCOUNT");
        bundle = bundle2;
        if(bundle1 != null)
        {
            bundle1.putAll(bundle2);
            bundle = bundle1;
        }
        if(flag)
            return updateCredentials(accountauthenticatorresponse, account, s1, bundle1);
        else
            return addAccount(accountauthenticatorresponse, s, s1, as, bundle);
    }

    public Bundle getAccountCredentialsForCloning(final AccountAuthenticatorResponse response, Account account)
        throws NetworkErrorException
    {
        (new Thread(new Runnable() {

            public void run()
            {
                Bundle bundle = new Bundle();
                bundle.putBoolean("booleanResult", false);
                response.onResult(bundle);
            }

            final AbstractAccountAuthenticator this$0;
            final AccountAuthenticatorResponse val$response;

            
            {
                this$0 = AbstractAccountAuthenticator.this;
                response = accountauthenticatorresponse;
                super();
            }
        }
)).start();
        return null;
    }

    public Bundle getAccountRemovalAllowed(AccountAuthenticatorResponse accountauthenticatorresponse, Account account)
        throws NetworkErrorException
    {
        accountauthenticatorresponse = new Bundle();
        accountauthenticatorresponse.putBoolean("booleanResult", true);
        return accountauthenticatorresponse;
    }

    public abstract Bundle getAuthToken(AccountAuthenticatorResponse accountauthenticatorresponse, Account account, String s, Bundle bundle)
        throws NetworkErrorException;

    public abstract String getAuthTokenLabel(String s);

    public final IBinder getIBinder()
    {
        return mTransport.asBinder();
    }

    public abstract Bundle hasFeatures(AccountAuthenticatorResponse accountauthenticatorresponse, Account account, String as[])
        throws NetworkErrorException;

    public Bundle isCredentialsUpdateSuggested(AccountAuthenticatorResponse accountauthenticatorresponse, Account account, String s)
        throws NetworkErrorException
    {
        accountauthenticatorresponse = new Bundle();
        accountauthenticatorresponse.putBoolean("booleanResult", false);
        return accountauthenticatorresponse;
    }

    public Bundle startAddAccountSession(final AccountAuthenticatorResponse response, String s, final String authTokenType, final String requiredFeatures[], final Bundle options)
        throws NetworkErrorException
    {
        (new Thread(new Runnable() {

            public void run()
            {
                Bundle bundle = new Bundle();
                bundle.putString("android.accounts.AbstractAccountAuthenticato.KEY_AUTH_TOKEN_TYPE", authTokenType);
                bundle.putStringArray("android.accounts.AbstractAccountAuthenticator.KEY_REQUIRED_FEATURES", requiredFeatures);
                bundle.putBundle("android.accounts.AbstractAccountAuthenticator.KEY_OPTIONS", options);
                Bundle bundle1 = new Bundle();
                bundle1.putBundle("accountSessionBundle", bundle);
                response.onResult(bundle1);
            }

            final AbstractAccountAuthenticator this$0;
            final String val$authTokenType;
            final Bundle val$options;
            final String val$requiredFeatures[];
            final AccountAuthenticatorResponse val$response;

            
            {
                this$0 = AbstractAccountAuthenticator.this;
                authTokenType = s;
                requiredFeatures = as;
                options = bundle;
                response = accountauthenticatorresponse;
                super();
            }
        }
)).start();
        return null;
    }

    public Bundle startUpdateCredentialsSession(final AccountAuthenticatorResponse response, final Account account, final String authTokenType, final Bundle options)
        throws NetworkErrorException
    {
        (new Thread(new Runnable() {

            public void run()
            {
                Bundle bundle = new Bundle();
                bundle.putString("android.accounts.AbstractAccountAuthenticato.KEY_AUTH_TOKEN_TYPE", authTokenType);
                bundle.putParcelable("android.accounts.AbstractAccountAuthenticator.KEY_ACCOUNT", account);
                bundle.putBundle("android.accounts.AbstractAccountAuthenticator.KEY_OPTIONS", options);
                Bundle bundle1 = new Bundle();
                bundle1.putBundle("accountSessionBundle", bundle);
                response.onResult(bundle1);
            }

            final AbstractAccountAuthenticator this$0;
            final Account val$account;
            final String val$authTokenType;
            final Bundle val$options;
            final AccountAuthenticatorResponse val$response;

            
            {
                this$0 = AbstractAccountAuthenticator.this;
                authTokenType = s;
                account = account1;
                options = bundle;
                response = accountauthenticatorresponse;
                super();
            }
        }
)).start();
        return null;
    }

    public abstract Bundle updateCredentials(AccountAuthenticatorResponse accountauthenticatorresponse, Account account, String s, Bundle bundle)
        throws NetworkErrorException;

    private static final String KEY_ACCOUNT = "android.accounts.AbstractAccountAuthenticator.KEY_ACCOUNT";
    private static final String KEY_AUTH_TOKEN_TYPE = "android.accounts.AbstractAccountAuthenticato.KEY_AUTH_TOKEN_TYPE";
    public static final String KEY_CUSTOM_TOKEN_EXPIRY = "android.accounts.expiry";
    private static final String KEY_OPTIONS = "android.accounts.AbstractAccountAuthenticator.KEY_OPTIONS";
    private static final String KEY_REQUIRED_FEATURES = "android.accounts.AbstractAccountAuthenticator.KEY_REQUIRED_FEATURES";
    private static final String TAG = "AccountAuthenticator";
    private final Context mContext;
    private Transport mTransport;
}
