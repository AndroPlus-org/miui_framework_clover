// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.accounts;

import android.app.Activity;
import android.content.*;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.database.SQLException;
import android.os.*;
import android.text.TextUtils;
import android.util.Log;
import android.util.SeempLog;
import com.google.android.collect.Maps;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import miui.content.pm.ExtraPackageManager;

// Referenced classes of package android.accounts:
//            AuthenticatorException, Account, IAccountManager, OperationCanceledException, 
//            AccountManagerFuture, AccountManagerCallback, OnAccountsUpdateListener, AuthenticatorDescription, 
//            IAccountManagerResponse

public class AccountManager
{
    private abstract class AmsTask extends FutureTask
        implements AccountManagerFuture
    {

        static void _2D_wrap0(AmsTask amstask, Throwable throwable)
        {
            amstask.setException(throwable);
        }

        private Bundle internalGetResult(Long long1, TimeUnit timeunit)
            throws OperationCanceledException, IOException, AuthenticatorException
        {
            if(!isDone())
                AccountManager._2D_wrap1(AccountManager.this);
            if(long1 != null)
                break MISSING_BLOCK_LABEL_34;
            long1 = (Bundle)get();
            cancel(true);
            return long1;
            long1 = (Bundle)get(long1.longValue(), timeunit);
            cancel(true);
            return long1;
            long1;
            long1 = long1.getCause();
            if(long1 instanceof IOException)
                throw (IOException)long1;
            break MISSING_BLOCK_LABEL_82;
            long1;
            cancel(true);
            throw long1;
            if(long1 instanceof UnsupportedOperationException)
            {
                timeunit = JVM INSTR new #64  <Class AuthenticatorException>;
                timeunit.AuthenticatorException(long1);
                throw timeunit;
            }
            if(long1 instanceof AuthenticatorException)
                throw (AuthenticatorException)long1;
            if(long1 instanceof RuntimeException)
                throw (RuntimeException)long1;
            if(long1 instanceof Error)
            {
                throw (Error)long1;
            } else
            {
                timeunit = JVM INSTR new #112 <Class IllegalStateException>;
                timeunit.IllegalStateException(long1);
                throw timeunit;
            }
            long1;
            cancel(true);
_L2:
            throw new OperationCanceledException();
            long1;
            cancel(true);
            if(true) goto _L2; else goto _L1
_L1:
            long1;
            long1 = JVM INSTR new #60  <Class OperationCanceledException>;
            long1.OperationCanceledException();
            throw long1;
        }

        public abstract void doWork()
            throws RemoteException;

        protected void done()
        {
            if(mCallback != null)
                AccountManager._2D_wrap3(AccountManager.this, mHandler, mCallback, this);
        }

        public Bundle getResult()
            throws OperationCanceledException, IOException, AuthenticatorException
        {
            return internalGetResult(null, null);
        }

        public Bundle getResult(long l, TimeUnit timeunit)
            throws OperationCanceledException, IOException, AuthenticatorException
        {
            return internalGetResult(Long.valueOf(l), timeunit);
        }

        public volatile Object getResult()
            throws OperationCanceledException, IOException, AuthenticatorException
        {
            return getResult();
        }

        public volatile Object getResult(long l, TimeUnit timeunit)
            throws OperationCanceledException, IOException, AuthenticatorException
        {
            return getResult(l, timeunit);
        }

        protected void set(Bundle bundle)
        {
            if(bundle == null)
                Log.e("AccountManager", "the bundle must not be null", new Exception());
            super.set(bundle);
        }

        protected volatile void set(Object obj)
        {
            set((Bundle)obj);
        }

        public final AccountManagerFuture start()
        {
            try
            {
                doWork();
            }
            catch(RemoteException remoteexception)
            {
                setException(remoteexception);
            }
            return this;
        }

        final Activity mActivity;
        final AccountManagerCallback mCallback;
        final Handler mHandler;
        final IAccountManagerResponse mResponse = new Response(null);
        final AccountManager this$0;

        public AmsTask(Activity activity, Handler handler, AccountManagerCallback accountmanagercallback)
        {
            this$0 = AccountManager.this;
            super(new _cls1(AccountManager.this));
            mHandler = handler;
            mCallback = accountmanagercallback;
            mActivity = activity;
        }
    }

    private class AmsTask.Response extends IAccountManagerResponse.Stub
    {

        public void onError(int i, String s)
        {
            while(i == 4 || i == 100 || i == 101) 
            {
                cancel(true);
                return;
            }
            AmsTask._2D_wrap0(AmsTask.this, AccountManager._2D_wrap0(_fld0, i, s));
        }

        public void onResult(Bundle bundle)
        {
            if(bundle == null)
            {
                onError(5, "null bundle returned");
                return;
            }
            Intent intent = (Intent)bundle.getParcelable("intent");
            if(intent != null && mActivity != null)
            {
                if(ExtraPackageManager.handleIfContainsXiaomiAccountType(intent))
                    return;
                mActivity.startActivity(intent);
            } else
            if(bundle.getBoolean("retry"))
                try
                {
                    doWork();
                }
                // Misplaced declaration of an exception variable
                catch(Bundle bundle)
                {
                    throw bundle.rethrowFromSystemServer();
                }
            else
                set(bundle);
        }

        final AmsTask this$1;

        private AmsTask.Response()
        {
            this$1 = AmsTask.this;
            super();
        }

        AmsTask.Response(AmsTask.Response response)
        {
            this();
        }
    }

    private abstract class BaseFutureTask extends FutureTask
    {

        static void _2D_wrap0(BaseFutureTask basefuturetask, Throwable throwable)
        {
            basefuturetask.setException(throwable);
        }

        static void _2D_wrap1(BaseFutureTask basefuturetask, Object obj)
        {
            basefuturetask.set(obj);
        }

        public abstract Object bundleToResult(Bundle bundle)
            throws AuthenticatorException;

        public abstract void doWork()
            throws RemoteException;

        protected void postRunnableToHandler(Runnable runnable)
        {
            Handler handler;
            if(mHandler == null)
                handler = AccountManager._2D_get3(AccountManager.this);
            else
                handler = mHandler;
            handler.post(runnable);
        }

        protected void startTask()
        {
            doWork();
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            setException(remoteexception);
              goto _L1
        }

        final Handler mHandler;
        public final IAccountManagerResponse mResponse = new Response();
        final AccountManager this$0;

        public BaseFutureTask(Handler handler)
        {
            this$0 = AccountManager.this;
            super(new _cls1(AccountManager.this));
            mHandler = handler;
        }
    }

    protected class BaseFutureTask.Response extends IAccountManagerResponse.Stub
    {

        public void onError(int i, String s)
        {
            while(i == 4 || i == 100 || i == 101) 
            {
                cancel(true);
                return;
            }
            BaseFutureTask._2D_wrap0(BaseFutureTask.this, AccountManager._2D_wrap0(_fld0, i, s));
        }

        public void onResult(Bundle bundle)
        {
            bundle = ((Bundle) (bundleToResult(bundle)));
            if(bundle == null)
                return;
            try
            {
                BaseFutureTask._2D_wrap1(BaseFutureTask.this, bundle);
                return;
            }
            // Misplaced declaration of an exception variable
            catch(Bundle bundle) { }
            // Misplaced declaration of an exception variable
            catch(Bundle bundle) { }
            onError(5, "no result in response");
            return;
        }

        final BaseFutureTask this$1;

        protected BaseFutureTask.Response()
        {
            this$1 = BaseFutureTask.this;
            super();
        }
    }

    private abstract class Future2Task extends BaseFutureTask
        implements AccountManagerFuture
    {

        private Object internalGetResult(Long long1, TimeUnit timeunit)
            throws OperationCanceledException, IOException, AuthenticatorException
        {
            if(!isDone())
                AccountManager._2D_wrap1(AccountManager.this);
            if(long1 != null)
                break MISSING_BLOCK_LABEL_31;
            long1 = ((Long) (get()));
            cancel(true);
            return long1;
            long1 = ((Long) (get(long1.longValue(), timeunit)));
            cancel(true);
            return long1;
            long1;
            long1 = long1.getCause();
            if(long1 instanceof IOException)
                throw (IOException)long1;
            break MISSING_BLOCK_LABEL_76;
            long1;
            cancel(true);
            throw long1;
            if(long1 instanceof UnsupportedOperationException)
            {
                timeunit = JVM INSTR new #37  <Class AuthenticatorException>;
                timeunit.AuthenticatorException(long1);
                throw timeunit;
            }
            if(long1 instanceof AuthenticatorException)
                throw (AuthenticatorException)long1;
            if(long1 instanceof RuntimeException)
                throw (RuntimeException)long1;
            if(long1 instanceof Error)
            {
                throw (Error)long1;
            } else
            {
                timeunit = JVM INSTR new #85  <Class IllegalStateException>;
                timeunit.IllegalStateException(long1);
                throw timeunit;
            }
            long1;
            cancel(true);
_L2:
            throw new OperationCanceledException();
            long1;
            cancel(true);
            continue; /* Loop/switch isn't completed */
            long1;
            cancel(true);
            if(true) goto _L2; else goto _L1
_L1:
        }

        protected void done()
        {
            if(mCallback != null)
                postRunnableToHandler(new Runnable() {

                    public void run()
                    {
                        mCallback.run(Future2Task.this);
                    }

                    final Future2Task this$1;

            
            {
                this$1 = Future2Task.this;
                super();
            }
                }
);
        }

        public Object getResult()
            throws OperationCanceledException, IOException, AuthenticatorException
        {
            return internalGetResult(null, null);
        }

        public Object getResult(long l, TimeUnit timeunit)
            throws OperationCanceledException, IOException, AuthenticatorException
        {
            return internalGetResult(Long.valueOf(l), timeunit);
        }

        public Future2Task start()
        {
            startTask();
            return this;
        }

        final AccountManagerCallback mCallback;
        final AccountManager this$0;

        public Future2Task(Handler handler, AccountManagerCallback accountmanagercallback)
        {
            this$0 = AccountManager.this;
            super(handler);
            mCallback = accountmanagercallback;
        }
    }

    private class GetAuthTokenByTypeAndFeaturesTask extends AmsTask
        implements AccountManagerCallback
    {

        static int _2D_set0(GetAuthTokenByTypeAndFeaturesTask getauthtokenbytypeandfeaturestask, int i)
        {
            getauthtokenbytypeandfeaturestask.mNumAccounts = i;
            return i;
        }

        static void _2D_wrap0(GetAuthTokenByTypeAndFeaturesTask getauthtokenbytypeandfeaturestask, Throwable throwable)
        {
            getauthtokenbytypeandfeaturestask.setException(throwable);
        }

        public void doWork()
            throws RemoteException
        {
            SeempLog.record(31);
            AccountManager._2D_wrap2(AccountManager.this, mAccountType, mFeatures, new AccountManagerCallback() {

                public void run(AccountManagerFuture accountmanagerfuture)
                {
                    Object obj;
                    try
                    {
                        obj = (Bundle)accountmanagerfuture.getResult();
                        accountmanagerfuture = ((Bundle) (obj)).getString("authAccount");
                        obj = ((Bundle) (obj)).getString("accountType");
                    }
                    // Misplaced declaration of an exception variable
                    catch(AccountManagerFuture accountmanagerfuture)
                    {
                        GetAuthTokenByTypeAndFeaturesTask._2D_wrap0(GetAuthTokenByTypeAndFeaturesTask.this, accountmanagerfuture);
                        return;
                    }
                    // Misplaced declaration of an exception variable
                    catch(AccountManagerFuture accountmanagerfuture)
                    {
                        GetAuthTokenByTypeAndFeaturesTask._2D_wrap0(GetAuthTokenByTypeAndFeaturesTask.this, accountmanagerfuture);
                        return;
                    }
                    // Misplaced declaration of an exception variable
                    catch(AccountManagerFuture accountmanagerfuture)
                    {
                        GetAuthTokenByTypeAndFeaturesTask._2D_wrap0(GetAuthTokenByTypeAndFeaturesTask.this, accountmanagerfuture);
                        return;
                    }
                    if(accountmanagerfuture == null)
                    {
                        if(mActivity != null)
                        {
                            mFuture = addAccount(mAccountType, mAuthTokenType, mFeatures, mAddAccountOptions, mActivity, mMyCallback, mHandler);
                        } else
                        {
                            accountmanagerfuture = new Bundle();
                            accountmanagerfuture.putString("authAccount", null);
                            accountmanagerfuture.putString("accountType", null);
                            accountmanagerfuture.putString("authtoken", null);
                            accountmanagerfuture.putBinder("accountAccessId", null);
                            try
                            {
                                mResponse.onResult(accountmanagerfuture);
                            }
                            // Misplaced declaration of an exception variable
                            catch(AccountManagerFuture accountmanagerfuture) { }
                        }
                    } else
                    {
                        GetAuthTokenByTypeAndFeaturesTask._2D_set0(GetAuthTokenByTypeAndFeaturesTask.this, 1);
                        accountmanagerfuture = new Account(accountmanagerfuture, ((String) (obj)));
                        if(mActivity == null)
                            mFuture = getAuthToken(accountmanagerfuture, mAuthTokenType, false, mMyCallback, mHandler);
                        else
                            mFuture = getAuthToken(accountmanagerfuture, mAuthTokenType, mLoginOptions, mActivity, mMyCallback, mHandler);
                    }
                }

                final GetAuthTokenByTypeAndFeaturesTask this$1;

            
            {
                this$1 = GetAuthTokenByTypeAndFeaturesTask.this;
                super();
            }
            }
, mHandler);
        }

        public void run(AccountManagerFuture accountmanagerfuture)
        {
            Object obj;
            String s;
            obj = (Bundle)accountmanagerfuture.getResult();
            if(mNumAccounts != 0)
                break MISSING_BLOCK_LABEL_114;
            accountmanagerfuture = ((Bundle) (obj)).getString("authAccount");
            s = ((Bundle) (obj)).getString("accountType");
            if(TextUtils.isEmpty(accountmanagerfuture) || TextUtils.isEmpty(s))
            {
                accountmanagerfuture = JVM INSTR new #101 <Class AuthenticatorException>;
                accountmanagerfuture.AuthenticatorException("account not in result");
                setException(accountmanagerfuture);
                return;
            }
            try
            {
                obj = ((Bundle) (obj)).getString("accountAccessId");
                Account account = JVM INSTR new #130 <Class Account>;
                account.Account(accountmanagerfuture, s, ((String) (obj)));
                mNumAccounts = 1;
                getAuthToken(account, mAuthTokenType, null, mActivity, mMyCallback, mHandler);
                return;
            }
            // Misplaced declaration of an exception variable
            catch(AccountManagerFuture accountmanagerfuture)
            {
                cancel(true);
            }
            // Misplaced declaration of an exception variable
            catch(AccountManagerFuture accountmanagerfuture)
            {
                setException(accountmanagerfuture);
            }
            // Misplaced declaration of an exception variable
            catch(AccountManagerFuture accountmanagerfuture)
            {
                setException(accountmanagerfuture);
            }
            break MISSING_BLOCK_LABEL_119;
            set(((Bundle) (obj)));
        }

        final String mAccountType;
        final Bundle mAddAccountOptions;
        final String mAuthTokenType;
        final String mFeatures[];
        volatile AccountManagerFuture mFuture;
        final Bundle mLoginOptions;
        final AccountManagerCallback mMyCallback;
        private volatile int mNumAccounts;
        final AccountManager this$0;

        GetAuthTokenByTypeAndFeaturesTask(String s, String s1, String as[], Activity activity, Bundle bundle, Bundle bundle1, 
                AccountManagerCallback accountmanagercallback, Handler handler)
        {
            this$0 = AccountManager.this;
            super(activity, handler, accountmanagercallback);
            mFuture = null;
            mNumAccounts = 0;
            if(s == null)
            {
                throw new IllegalArgumentException("account type is null");
            } else
            {
                mAccountType = s;
                mAuthTokenType = s1;
                mFeatures = as;
                mAddAccountOptions = bundle;
                mLoginOptions = bundle1;
                mMyCallback = this;
                return;
            }
        }
    }


    static HashMap _2D_get0(AccountManager accountmanager)
    {
        return accountmanager.mAccountsUpdatedListeners;
    }

    static HashMap _2D_get1(AccountManager accountmanager)
    {
        return accountmanager.mAccountsUpdatedListenersTypes;
    }

    static Context _2D_get2(AccountManager accountmanager)
    {
        return accountmanager.mContext;
    }

    static Handler _2D_get3(AccountManager accountmanager)
    {
        return accountmanager.mMainHandler;
    }

    static IAccountManager _2D_get4(AccountManager accountmanager)
    {
        return accountmanager.mService;
    }

    static Exception _2D_wrap0(AccountManager accountmanager, int i, String s)
    {
        return accountmanager.convertErrorToException(i, s);
    }

    static void _2D_wrap1(AccountManager accountmanager)
    {
        accountmanager.ensureNotOnMainThread();
    }

    static void _2D_wrap2(AccountManager accountmanager, String s, String as[], AccountManagerCallback accountmanagercallback, Handler handler)
    {
        accountmanager.getAccountByTypeAndFeatures(s, as, accountmanagercallback, handler);
    }

    static void _2D_wrap3(AccountManager accountmanager, Handler handler, AccountManagerCallback accountmanagercallback, AccountManagerFuture accountmanagerfuture)
    {
        accountmanager.postToHandler(handler, accountmanagercallback, accountmanagerfuture);
    }

    static void _2D_wrap4(AccountManager accountmanager, Handler handler, OnAccountsUpdateListener onaccountsupdatelistener, Account aaccount[])
    {
        accountmanager.postToHandler(handler, onaccountsupdatelistener, aaccount);
    }

    public AccountManager(Context context, IAccountManager iaccountmanager)
    {
        mAccountsUpdatedListeners = Maps.newHashMap();
        mAccountsUpdatedListenersTypes = Maps.newHashMap();
        mAccountsChangedBroadcastReceiver = new BroadcastReceiver() {

            public void onReceive(Context context1, Intent intent)
            {
                Account aaccount[] = getAccounts();
                context1 = AccountManager._2D_get0(AccountManager.this);
                context1;
                JVM INSTR monitorenter ;
                java.util.Map.Entry entry;
                for(intent = AccountManager._2D_get0(AccountManager.this).entrySet().iterator(); intent.hasNext(); AccountManager._2D_wrap4(AccountManager.this, (Handler)entry.getValue(), (OnAccountsUpdateListener)entry.getKey(), aaccount))
                    entry = (java.util.Map.Entry)intent.next();

                break MISSING_BLOCK_LABEL_90;
                intent;
                throw intent;
                context1;
                JVM INSTR monitorexit ;
            }

            final AccountManager this$0;

            
            {
                this$0 = AccountManager.this;
                super();
            }
        }
;
        mContext = context;
        mService = iaccountmanager;
        mMainHandler = new Handler(mContext.getMainLooper());
    }

    public AccountManager(Context context, IAccountManager iaccountmanager, Handler handler)
    {
        mAccountsUpdatedListeners = Maps.newHashMap();
        mAccountsUpdatedListenersTypes = Maps.newHashMap();
        mAccountsChangedBroadcastReceiver = new _cls1();
        mContext = context;
        mService = iaccountmanager;
        mMainHandler = handler;
    }

    private Exception convertErrorToException(int i, String s)
    {
        if(i == 3)
            return new IOException(s);
        if(i == 6)
            return new UnsupportedOperationException(s);
        if(i == 5)
            return new AuthenticatorException(s);
        if(i == 7)
            return new IllegalArgumentException(s);
        else
            return new AuthenticatorException(s);
    }

    private void ensureNotOnMainThread()
    {
        Looper looper = Looper.myLooper();
        if(looper != null && looper == mContext.getMainLooper())
        {
            IllegalStateException illegalstateexception = new IllegalStateException("calling this from your main thread can lead to deadlock");
            Log.e("AccountManager", "calling this from your main thread can lead to deadlock and/or ANRs", illegalstateexception);
            if(mContext.getApplicationInfo().targetSdkVersion >= 8)
                throw illegalstateexception;
        }
    }

    public static AccountManager get(Context context)
    {
        if(context == null)
            throw new IllegalArgumentException("context is null");
        else
            return (AccountManager)context.getSystemService("account");
    }

    private void getAccountByTypeAndFeatures(String s, String as[], AccountManagerCallback accountmanagercallback, Handler handler)
    {
        (new AmsTask(handler, accountmanagercallback, s, as) {

            public void doWork()
                throws RemoteException
            {
                AccountManager._2D_get4(AccountManager.this).getAccountByTypeAndFeatures(mResponse, accountType, features, AccountManager._2D_get2(AccountManager.this).getOpPackageName());
            }

            final AccountManager this$0;
            final String val$accountType;
            final String val$features[];

            
            {
                this$0 = final_accountmanager1;
                accountType = s;
                features = as;
                super(final_activity, handler, accountmanagercallback);
            }
        }
).start();
    }

    public static Intent newChooseAccountIntent(Account account, ArrayList arraylist, String as[], boolean flag, String s, String s1, String as1[], Bundle bundle)
    {
        return newChooseAccountIntent(account, ((List) (arraylist)), as, s, s1, as1, bundle);
    }

    public static Intent newChooseAccountIntent(Account account, List list, String as[], String s, String s1, String as1[], Bundle bundle)
    {
        Object obj = null;
        Intent intent = new Intent();
        ComponentName componentname = ComponentName.unflattenFromString(Resources.getSystem().getString(0x1040120));
        intent.setClassName(componentname.getPackageName(), componentname.getClassName());
        if(list == null)
            list = obj;
        else
            list = new ArrayList(list);
        intent.putExtra("allowableAccounts", list);
        intent.putExtra("allowableAccountTypes", as);
        intent.putExtra("addAccountOptions", bundle);
        intent.putExtra("selectedAccount", account);
        intent.putExtra("descriptionTextOverride", s);
        intent.putExtra("authTokenType", s1);
        intent.putExtra("addAccountRequiredFeatures", as1);
        return intent;
    }

    private void postToHandler(Handler handler, final AccountManagerCallback callback, final AccountManagerFuture future)
    {
        Handler handler1 = handler;
        if(handler == null)
            handler1 = mMainHandler;
        handler1.post(new Runnable() {

            public void run()
            {
                callback.run(future);
            }

            final AccountManager this$0;
            final AccountManagerCallback val$callback;
            final AccountManagerFuture val$future;

            
            {
                this$0 = AccountManager.this;
                callback = accountmanagercallback;
                future = accountmanagerfuture;
                super();
            }
        }
);
    }

    private void postToHandler(Handler handler, final OnAccountsUpdateListener listener, Account aaccount[])
    {
        final Account accountsCopy[] = new Account[aaccount.length];
        System.arraycopy(aaccount, 0, accountsCopy, 0, accountsCopy.length);
        aaccount = handler;
        if(handler == null)
            aaccount = mMainHandler;
        aaccount.post(new Runnable() {

            public void run()
            {
                HashMap hashmap = AccountManager._2D_get0(AccountManager.this);
                hashmap;
                JVM INSTR monitorenter ;
                if(!AccountManager._2D_get0(AccountManager.this).containsKey(listener)) goto _L2; else goto _L1
_L1:
                Set set = (Set)AccountManager._2D_get1(AccountManager.this).get(listener);
                if(set == null) goto _L4; else goto _L3
_L3:
                ArrayList arraylist;
                Account aaccount1[];
                arraylist = JVM INSTR new #53  <Class ArrayList>;
                arraylist.ArrayList();
                aaccount1 = accountsCopy;
                int i = 0;
                int j = aaccount1.length;
_L7:
                if(i >= j) goto _L6; else goto _L5
_L5:
                Account account = aaccount1[i];
                if(set.contains(account.type))
                    arraylist.add(account);
                i++;
                  goto _L7
_L6:
                listener.onAccountsUpdated((Account[])arraylist.toArray(new Account[arraylist.size()]));
_L2:
                hashmap;
                JVM INSTR monitorexit ;
                return;
_L4:
                listener.onAccountsUpdated(accountsCopy);
                  goto _L2
                Object obj;
                obj;
                Log.e("AccountManager", "Can't update accounts", ((Throwable) (obj)));
                  goto _L2
                obj;
                throw obj;
            }

            final AccountManager this$0;
            final Account val$accountsCopy[];
            final OnAccountsUpdateListener val$listener;

            
            {
                this$0 = AccountManager.this;
                listener = onaccountsupdatelistener;
                accountsCopy = aaccount;
                super();
            }
        }
);
    }

    public static Bundle sanitizeResult(Bundle bundle)
    {
        if(bundle != null && bundle.containsKey("authtoken") && TextUtils.isEmpty(bundle.getString("authtoken")) ^ true)
        {
            bundle = new Bundle(bundle);
            bundle.putString("authtoken", "<omitted for logging purposes>");
            return bundle;
        } else
        {
            return bundle;
        }
    }

    public AccountManagerFuture addAccount(final String accountType, String s, String as[], Bundle bundle, final Activity final_activity1, final AccountManagerCallback final_accountmanagercallback, final Handler final_handler)
    {
        SeempLog.record(29);
        if(accountType == null)
            throw new IllegalArgumentException("accountType is null");
        Bundle bundle1 = new Bundle();
        if(bundle != null)
            bundle1.putAll(bundle);
        bundle1.putString("androidPackageName", mContext.getPackageName());
        return (new AmsTask(s, as, final_activity1, bundle1) {

            public void doWork()
                throws RemoteException
            {
                SeempLog.record(31);
                IAccountManager iaccountmanager = AccountManager._2D_get4(AccountManager.this);
                IAccountManagerResponse iaccountmanagerresponse = mResponse;
                String s1 = accountType;
                String s2 = authTokenType;
                String as1[] = requiredFeatures;
                boolean flag;
                if(activity != null)
                    flag = true;
                else
                    flag = false;
                iaccountmanager.addAccount(iaccountmanagerresponse, s1, s2, as1, flag, optionsIn);
            }

            final AccountManager this$0;
            final String val$accountType;
            final Activity val$activity;
            final String val$authTokenType;
            final Bundle val$optionsIn;
            final String val$requiredFeatures[];

            
            {
                this$0 = final_accountmanager1;
                accountType = s;
                authTokenType = s1;
                requiredFeatures = as;
                activity = activity2;
                optionsIn = bundle;
                super(final_activity1, final_handler, final_accountmanagercallback);
            }
        }
).start();
    }

    public AccountManagerFuture addAccountAsUser(final String accountType, final String authTokenType, String as[], Bundle bundle, final Activity final_activity1, final AccountManagerCallback final_accountmanagercallback, final Handler final_handler, 
            UserHandle userhandle)
    {
        if(accountType == null)
            throw new IllegalArgumentException("accountType is null");
        if(userhandle == null)
            throw new IllegalArgumentException("userHandle is null");
        Bundle bundle1 = new Bundle();
        if(bundle != null)
            bundle1.putAll(bundle);
        bundle1.putString("androidPackageName", mContext.getPackageName());
        return (new AmsTask(as, final_activity1, bundle1, userhandle) {

            public void doWork()
                throws RemoteException
            {
                SeempLog.record(31);
                IAccountManager iaccountmanager = AccountManager._2D_get4(AccountManager.this);
                IAccountManagerResponse iaccountmanagerresponse = mResponse;
                String s = accountType;
                String s1 = authTokenType;
                String as1[] = requiredFeatures;
                boolean flag;
                if(activity != null)
                    flag = true;
                else
                    flag = false;
                iaccountmanager.addAccountAsUser(iaccountmanagerresponse, s, s1, as1, flag, optionsIn, userHandle.getIdentifier());
            }

            final AccountManager this$0;
            final String val$accountType;
            final Activity val$activity;
            final String val$authTokenType;
            final Bundle val$optionsIn;
            final String val$requiredFeatures[];
            final UserHandle val$userHandle;

            
            {
                this$0 = final_accountmanager1;
                accountType = s;
                authTokenType = s1;
                requiredFeatures = as;
                activity = activity2;
                optionsIn = bundle;
                userHandle = userhandle;
                super(final_activity1, final_handler, final_accountmanagercallback);
            }
        }
).start();
    }

    public boolean addAccountExplicitly(Account account, String s, Bundle bundle)
    {
        SeempLog.record(24);
        if(account == null)
            throw new IllegalArgumentException("account is null");
        boolean flag;
        try
        {
            flag = mService.addAccountExplicitly(account, s, bundle);
        }
        // Misplaced declaration of an exception variable
        catch(Account account)
        {
            throw account.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean addAccountExplicitly(Account account, String s, Bundle bundle, Map map)
    {
        if(account == null)
            throw new IllegalArgumentException("account is null");
        boolean flag;
        try
        {
            flag = mService.addAccountExplicitlyWithVisibility(account, s, bundle, map);
        }
        // Misplaced declaration of an exception variable
        catch(Account account)
        {
            throw account.rethrowFromSystemServer();
        }
        return flag;
    }

    public void addOnAccountsUpdatedListener(OnAccountsUpdateListener onaccountsupdatelistener, Handler handler, boolean flag)
    {
        addOnAccountsUpdatedListener(onaccountsupdatelistener, handler, flag, null);
    }

    public void addOnAccountsUpdatedListener(OnAccountsUpdateListener onaccountsupdatelistener, Handler handler, boolean flag, String as[])
    {
label0:
        {
            if(onaccountsupdatelistener == null)
                throw new IllegalArgumentException("the listener is null");
            synchronized(mAccountsUpdatedListeners)
            {
                if(mAccountsUpdatedListeners.containsKey(onaccountsupdatelistener))
                {
                    onaccountsupdatelistener = JVM INSTR new #312 <Class IllegalStateException>;
                    onaccountsupdatelistener.IllegalStateException("this listener is already added");
                    throw onaccountsupdatelistener;
                }
                break label0;
            }
        }
        boolean flag1;
        flag1 = mAccountsUpdatedListeners.isEmpty();
        mAccountsUpdatedListeners.put(onaccountsupdatelistener, handler);
        if(as == null) goto _L2; else goto _L1
_L1:
        HashMap hashmap1 = mAccountsUpdatedListenersTypes;
        HashSet hashset = JVM INSTR new #541 <Class HashSet>;
        hashset.HashSet(Arrays.asList(as));
        hashmap1.put(onaccountsupdatelistener, hashset);
_L4:
        if(!flag1)
            break MISSING_BLOCK_LABEL_152;
        IntentFilter intentfilter = JVM INSTR new #550 <Class IntentFilter>;
        intentfilter.IntentFilter();
        intentfilter.addAction("android.accounts.action.VISIBLE_ACCOUNTS_CHANGED");
        intentfilter.addAction("android.intent.action.DEVICE_STORAGE_OK");
        mContext.registerReceiver(mAccountsChangedBroadcastReceiver, intentfilter);
        mService.registerAccountListener(as, mContext.getOpPackageName());
        hashmap;
        JVM INSTR monitorexit ;
        if(flag)
            postToHandler(handler, onaccountsupdatelistener, getAccounts());
        return;
_L2:
        mAccountsUpdatedListenersTypes.put(onaccountsupdatelistener, null);
        if(true) goto _L4; else goto _L3
_L3:
        onaccountsupdatelistener;
        throw onaccountsupdatelistener.rethrowFromSystemServer();
    }

    public void addSharedAccountsFromParentUser(UserHandle userhandle, UserHandle userhandle1)
    {
        try
        {
            mService.addSharedAccountsFromParentUser(userhandle.getIdentifier(), userhandle1.getIdentifier(), mContext.getOpPackageName());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(UserHandle userhandle)
        {
            throw userhandle.rethrowFromSystemServer();
        }
    }

    public String blockingGetAuthToken(Account account, String s, boolean flag)
        throws OperationCanceledException, IOException, AuthenticatorException
    {
        if(account == null)
            throw new IllegalArgumentException("account is null");
        if(s == null)
            throw new IllegalArgumentException("authTokenType is null");
        Bundle bundle = (Bundle)getAuthToken(account, s, flag, null, null).getResult();
        if(bundle == null)
        {
            Log.e("AccountManager", (new StringBuilder()).append("blockingGetAuthToken: null was returned from getResult() for ").append(account).append(", authTokenType ").append(s).toString());
            return null;
        } else
        {
            return bundle.getString("authtoken");
        }
    }

    public void clearPassword(Account account)
    {
        SeempLog.record(27);
        if(account == null)
            throw new IllegalArgumentException("account is null");
        try
        {
            mService.clearPassword(account);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Account account)
        {
            throw account.rethrowFromSystemServer();
        }
    }

    public AccountManagerFuture confirmCredentials(Account account, Bundle bundle, Activity activity, AccountManagerCallback accountmanagercallback, Handler handler)
    {
        return confirmCredentialsAsUser(account, bundle, activity, accountmanagercallback, handler, Process.myUserHandle());
    }

    public AccountManagerFuture confirmCredentialsAsUser(Account account, Bundle bundle, final Activity final_activity1, final AccountManagerCallback final_accountmanagercallback, final Handler final_handler, UserHandle userhandle)
    {
        if(account == null)
            throw new IllegalArgumentException("account is null");
        else
            return (new AmsTask(account, bundle, final_activity1, userhandle.getIdentifier()) {

                public void doWork()
                    throws RemoteException
                {
                    SeempLog.record(31);
                    IAccountManager iaccountmanager = AccountManager._2D_get4(AccountManager.this);
                    IAccountManagerResponse iaccountmanagerresponse = mResponse;
                    Account account1 = account;
                    Bundle bundle1 = options;
                    boolean flag;
                    if(activity != null)
                        flag = true;
                    else
                        flag = false;
                    iaccountmanager.confirmCredentialsAsUser(iaccountmanagerresponse, account1, bundle1, flag, userId);
                }

                final AccountManager this$0;
                final Account val$account;
                final Activity val$activity;
                final Bundle val$options;
                final int val$userId;

            
            {
                this$0 = final_accountmanager1;
                account = account1;
                options = bundle;
                activity = activity2;
                userId = i;
                super(final_activity1, final_handler, final_accountmanagercallback);
            }
            }
).start();
    }

    public AccountManagerFuture copyAccountToUser(Account account, UserHandle userhandle, UserHandle userhandle1, final AccountManagerCallback final_accountmanagercallback, final Handler final_handler)
    {
        if(account == null)
            throw new IllegalArgumentException("account is null");
        if(userhandle1 == null || userhandle == null)
            throw new IllegalArgumentException("fromUser and toUser cannot be null");
        else
            return (new Future2Task(account, userhandle, userhandle1) {

                public Boolean bundleToResult(Bundle bundle)
                    throws AuthenticatorException
                {
                    if(!bundle.containsKey("booleanResult"))
                        throw new AuthenticatorException("no result in response");
                    else
                        return Boolean.valueOf(bundle.getBoolean("booleanResult"));
                }

                public volatile Object bundleToResult(Bundle bundle)
                    throws AuthenticatorException
                {
                    return bundleToResult(bundle);
                }

                public void doWork()
                    throws RemoteException
                {
                    SeempLog.record(34);
                    AccountManager._2D_get4(AccountManager.this).copyAccountToUser(mResponse, account, fromUser.getIdentifier(), toUser.getIdentifier());
                }

                final AccountManager this$0;
                final Account val$account;
                final UserHandle val$fromUser;
                final UserHandle val$toUser;

            
            {
                this$0 = final_accountmanager1;
                account = account1;
                fromUser = userhandle;
                toUser = userhandle1;
                super(final_handler, final_accountmanagercallback);
            }
            }
).start();
    }

    public IntentSender createRequestAccountAccessIntentSenderAsUser(Account account, String s, UserHandle userhandle)
    {
        try
        {
            account = mService.createRequestAccountAccessIntentSenderAsUser(account, s, userhandle);
        }
        // Misplaced declaration of an exception variable
        catch(Account account)
        {
            throw account.rethrowFromSystemServer();
        }
        return account;
    }

    public AccountManagerFuture editProperties(String s, final Activity final_activity1, AccountManagerCallback accountmanagercallback, Handler handler)
    {
        SeempLog.record(30);
        if(s == null)
            throw new IllegalArgumentException("accountType is null");
        else
            return (new AmsTask(handler, accountmanagercallback, s, final_activity1) {

                public void doWork()
                    throws RemoteException
                {
                    SeempLog.record(31);
                    IAccountManager iaccountmanager = AccountManager._2D_get4(AccountManager.this);
                    IAccountManagerResponse iaccountmanagerresponse = mResponse;
                    String s1 = accountType;
                    boolean flag;
                    if(activity != null)
                        flag = true;
                    else
                        flag = false;
                    iaccountmanager.editProperties(iaccountmanagerresponse, s1, flag);
                }

                final AccountManager this$0;
                final String val$accountType;
                final Activity val$activity;

            
            {
                this$0 = final_accountmanager1;
                accountType = s;
                activity = activity2;
                super(final_activity1, handler, accountmanagercallback);
            }
            }
).start();
    }

    public AccountManagerFuture finishSession(Bundle bundle, Activity activity, AccountManagerCallback accountmanagercallback, Handler handler)
    {
        return finishSessionAsUser(bundle, activity, Process.myUserHandle(), accountmanagercallback, handler);
    }

    public AccountManagerFuture finishSessionAsUser(Bundle bundle, final Activity final_activity1, UserHandle userhandle, final AccountManagerCallback final_accountmanagercallback, final Handler final_handler)
    {
        if(bundle == null)
        {
            throw new IllegalArgumentException("sessionBundle is null");
        } else
        {
            Bundle bundle1 = new Bundle();
            bundle1.putString("androidPackageName", mContext.getPackageName());
            return (new AmsTask(bundle, final_activity1, bundle1, userhandle) {

                public void doWork()
                    throws RemoteException
                {
                    IAccountManager iaccountmanager = AccountManager._2D_get4(AccountManager.this);
                    IAccountManagerResponse iaccountmanagerresponse = mResponse;
                    Bundle bundle2 = sessionBundle;
                    boolean flag;
                    if(activity != null)
                        flag = true;
                    else
                        flag = false;
                    iaccountmanager.finishSessionAsUser(iaccountmanagerresponse, bundle2, flag, appInfo, userHandle.getIdentifier());
                }

                final AccountManager this$0;
                final Activity val$activity;
                final Bundle val$appInfo;
                final Bundle val$sessionBundle;
                final UserHandle val$userHandle;

            
            {
                this$0 = final_accountmanager1;
                sessionBundle = bundle;
                activity = activity2;
                appInfo = bundle1;
                userHandle = userhandle;
                super(final_activity1, final_handler, final_accountmanagercallback);
            }
            }
).start();
        }
    }

    public int getAccountVisibility(Account account, String s)
    {
        if(account == null)
            throw new IllegalArgumentException("account is null");
        int i;
        try
        {
            i = mService.getAccountVisibility(account, s);
        }
        // Misplaced declaration of an exception variable
        catch(Account account)
        {
            throw account.rethrowFromSystemServer();
        }
        return i;
    }

    public Account[] getAccounts()
    {
        Account aaccount[];
        try
        {
            aaccount = mService.getAccounts(null, mContext.getOpPackageName());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return aaccount;
    }

    public Map getAccountsAndVisibilityForPackage(String s, String s1)
    {
        try
        {
            s = mService.getAccountsAndVisibilityForPackage(s, s1);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return s;
    }

    public Account[] getAccountsAsUser(int i)
    {
        Account aaccount[];
        try
        {
            aaccount = mService.getAccountsAsUser(null, i, mContext.getOpPackageName());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return aaccount;
    }

    public Account[] getAccountsByType(String s)
    {
        return getAccountsByTypeAsUser(s, Process.myUserHandle());
    }

    public AccountManagerFuture getAccountsByTypeAndFeatures(String s, String as[], AccountManagerCallback accountmanagercallback, final Handler final_handler)
    {
        if(s == null)
            throw new IllegalArgumentException("type is null");
        else
            return (new Future2Task(accountmanagercallback, s, as) {

                public volatile Object bundleToResult(Bundle bundle)
                    throws AuthenticatorException
                {
                    return bundleToResult(bundle);
                }

                public Account[] bundleToResult(Bundle bundle)
                    throws AuthenticatorException
                {
                    if(!bundle.containsKey("accounts"))
                        throw new AuthenticatorException("no result in response");
                    bundle = bundle.getParcelableArray("accounts");
                    Account aaccount[] = new Account[bundle.length];
                    for(int i = 0; i < bundle.length; i++)
                        aaccount[i] = (Account)bundle[i];

                    return aaccount;
                }

                public void doWork()
                    throws RemoteException
                {
                    SeempLog.record(31);
                    AccountManager._2D_get4(AccountManager.this).getAccountsByFeatures(mResponse, type, features, AccountManager._2D_get2(AccountManager.this).getOpPackageName());
                }

                final AccountManager this$0;
                final String val$features[];
                final String val$type;

            
            {
                this$0 = final_accountmanager1;
                type = s;
                features = as;
                super(final_handler, accountmanagercallback);
            }
            }
).start();
    }

    public Account[] getAccountsByTypeAsUser(String s, UserHandle userhandle)
    {
        try
        {
            s = mService.getAccountsAsUser(s, userhandle.getIdentifier(), mContext.getOpPackageName());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return s;
    }

    public Account[] getAccountsByTypeForPackage(String s, String s1)
    {
        try
        {
            s = mService.getAccountsByTypeForPackage(s, s1, mContext.getOpPackageName());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return s;
    }

    public Account[] getAccountsForPackage(String s, int i)
    {
        try
        {
            s = mService.getAccountsForPackage(s, i, mContext.getOpPackageName());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return s;
    }

    public AccountManagerFuture getAuthToken(Account account, String s, Bundle bundle, final Activity final_activity, AccountManagerCallback accountmanagercallback, final Handler final_handler)
    {
        if(account == null)
            throw new IllegalArgumentException("account is null");
        if(s == null)
            throw new IllegalArgumentException("authTokenType is null");
        Bundle bundle1 = new Bundle();
        if(bundle != null)
            bundle1.putAll(bundle);
        bundle1.putString("androidPackageName", mContext.getPackageName());
        return (new AmsTask(accountmanagercallback, account, s, bundle1) {

            public void doWork()
                throws RemoteException
            {
                SeempLog.record(31);
                AccountManager._2D_get4(AccountManager.this).getAuthToken(mResponse, account, authTokenType, false, true, optionsIn);
            }

            final AccountManager this$0;
            final Account val$account;
            final String val$authTokenType;
            final Bundle val$optionsIn;

            
            {
                this$0 = final_accountmanager1;
                account = account1;
                authTokenType = s;
                optionsIn = bundle;
                super(final_activity, final_handler, accountmanagercallback);
            }
        }
).start();
    }

    public AccountManagerFuture getAuthToken(Account account, String s, Bundle bundle, boolean flag, AccountManagerCallback accountmanagercallback, Handler handler)
    {
        if(account == null)
            throw new IllegalArgumentException("account is null");
        if(s == null)
            throw new IllegalArgumentException("authTokenType is null");
        Bundle bundle1 = new Bundle();
        if(bundle != null)
            bundle1.putAll(bundle);
        bundle1.putString("androidPackageName", mContext.getPackageName());
        return (new AmsTask(account, s, flag, bundle1) {

            public void doWork()
                throws RemoteException
            {
                SeempLog.record(31);
                AccountManager._2D_get4(AccountManager.this).getAuthToken(mResponse, account, authTokenType, notifyAuthFailure, false, optionsIn);
            }

            final AccountManager this$0;
            final Account val$account;
            final String val$authTokenType;
            final boolean val$notifyAuthFailure;
            final Bundle val$optionsIn;

            
            {
                this$0 = final_accountmanager1;
                account = account1;
                authTokenType = s;
                notifyAuthFailure = flag;
                optionsIn = bundle;
                super(final_activity, final_handler, final_accountmanagercallback);
            }
        }
).start();
    }

    public AccountManagerFuture getAuthToken(Account account, String s, boolean flag, AccountManagerCallback accountmanagercallback, Handler handler)
    {
        return getAuthToken(account, s, null, flag, accountmanagercallback, handler);
    }

    public AccountManagerFuture getAuthTokenByFeatures(String s, String s1, String as[], Activity activity, Bundle bundle, Bundle bundle1, AccountManagerCallback accountmanagercallback, 
            Handler handler)
    {
        if(s == null)
            throw new IllegalArgumentException("account type is null");
        if(s1 == null)
        {
            throw new IllegalArgumentException("authTokenType is null");
        } else
        {
            s = new GetAuthTokenByTypeAndFeaturesTask(s, s1, as, activity, bundle, bundle1, accountmanagercallback, handler);
            s.start();
            return s;
        }
    }

    public AccountManagerFuture getAuthTokenLabel(String s, String s1, AccountManagerCallback accountmanagercallback, final Handler final_handler)
    {
        if(s == null)
            throw new IllegalArgumentException("accountType is null");
        if(s1 == null)
            throw new IllegalArgumentException("authTokenType is null");
        else
            return (new Future2Task(accountmanagercallback, s, s1) {

                public volatile Object bundleToResult(Bundle bundle)
                    throws AuthenticatorException
                {
                    return bundleToResult(bundle);
                }

                public String bundleToResult(Bundle bundle)
                    throws AuthenticatorException
                {
                    if(!bundle.containsKey("authTokenLabelKey"))
                        throw new AuthenticatorException("no result in response");
                    else
                        return bundle.getString("authTokenLabelKey");
                }

                public void doWork()
                    throws RemoteException
                {
                    SeempLog.record(31);
                    AccountManager._2D_get4(AccountManager.this).getAuthTokenLabel(mResponse, accountType, authTokenType);
                }

                final AccountManager this$0;
                final String val$accountType;
                final String val$authTokenType;

            
            {
                this$0 = final_accountmanager1;
                accountType = s;
                authTokenType = s1;
                super(final_handler, accountmanagercallback);
            }
            }
).start();
    }

    public AuthenticatorDescription[] getAuthenticatorTypes()
    {
        AuthenticatorDescription aauthenticatordescription[];
        try
        {
            aauthenticatordescription = mService.getAuthenticatorTypes(UserHandle.getCallingUserId());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return aauthenticatordescription;
    }

    public AuthenticatorDescription[] getAuthenticatorTypesAsUser(int i)
    {
        AuthenticatorDescription aauthenticatordescription[];
        try
        {
            aauthenticatordescription = mService.getAuthenticatorTypes(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return aauthenticatordescription;
    }

    public Map getPackagesAndVisibilityForAccount(Account account)
    {
        if(account == null)
            try
            {
                account = JVM INSTR new #304 <Class IllegalArgumentException>;
                account.IllegalArgumentException("account is null");
                throw account;
            }
            // Misplaced declaration of an exception variable
            catch(Account account)
            {
                throw account.rethrowFromSystemServer();
            }
        account = mService.getPackagesAndVisibilityForAccount(account);
        return account;
    }

    public String getPassword(Account account)
    {
        SeempLog.record(22);
        if(account == null)
            throw new IllegalArgumentException("account is null");
        try
        {
            account = mService.getPassword(account);
        }
        // Misplaced declaration of an exception variable
        catch(Account account)
        {
            throw account.rethrowFromSystemServer();
        }
        return account;
    }

    public String getPreviousName(Account account)
    {
        if(account == null)
            throw new IllegalArgumentException("account is null");
        try
        {
            account = mService.getPreviousName(account);
        }
        // Misplaced declaration of an exception variable
        catch(Account account)
        {
            throw account.rethrowFromSystemServer();
        }
        return account;
    }

    public Account[] getSharedAccounts(UserHandle userhandle)
    {
        try
        {
            userhandle = mService.getSharedAccountsAsUser(userhandle.getIdentifier());
        }
        // Misplaced declaration of an exception variable
        catch(UserHandle userhandle)
        {
            throw userhandle.rethrowFromSystemServer();
        }
        return userhandle;
    }

    public String getUserData(Account account, String s)
    {
        SeempLog.record(23);
        if(account == null)
            throw new IllegalArgumentException("account is null");
        if(s == null)
            throw new IllegalArgumentException("key is null");
        try
        {
            account = mService.getUserData(account, s);
        }
        // Misplaced declaration of an exception variable
        catch(Account account)
        {
            throw account.rethrowFromSystemServer();
        }
        return account;
    }

    public boolean hasAccountAccess(Account account, String s, UserHandle userhandle)
    {
        boolean flag;
        try
        {
            flag = mService.hasAccountAccess(account, s, userhandle);
        }
        // Misplaced declaration of an exception variable
        catch(Account account)
        {
            throw account.rethrowFromSystemServer();
        }
        return flag;
    }

    public AccountManagerFuture hasFeatures(Account account, String as[], AccountManagerCallback accountmanagercallback, final Handler final_handler)
    {
        if(account == null)
            throw new IllegalArgumentException("account is null");
        if(as == null)
            throw new IllegalArgumentException("features is null");
        else
            return (new Future2Task(accountmanagercallback, account, as) {

                public Boolean bundleToResult(Bundle bundle)
                    throws AuthenticatorException
                {
                    if(!bundle.containsKey("booleanResult"))
                        throw new AuthenticatorException("no result in response");
                    else
                        return Boolean.valueOf(bundle.getBoolean("booleanResult"));
                }

                public volatile Object bundleToResult(Bundle bundle)
                    throws AuthenticatorException
                {
                    return bundleToResult(bundle);
                }

                public void doWork()
                    throws RemoteException
                {
                    SeempLog.record(31);
                    AccountManager._2D_get4(AccountManager.this).hasFeatures(mResponse, account, features, AccountManager._2D_get2(AccountManager.this).getOpPackageName());
                }

                final AccountManager this$0;
                final Account val$account;
                final String val$features[];

            
            {
                this$0 = final_accountmanager1;
                account = account1;
                features = as;
                super(final_handler, accountmanagercallback);
            }
            }
).start();
    }

    public void invalidateAuthToken(String s, String s1)
    {
        if(s == null)
            throw new IllegalArgumentException("accountType is null");
        if(s1 == null)
            break MISSING_BLOCK_LABEL_30;
        mService.invalidateAuthToken(s, s1);
        return;
        s;
        throw s.rethrowFromSystemServer();
    }

    public AccountManagerFuture isCredentialsUpdateSuggested(Account account, String s, AccountManagerCallback accountmanagercallback, final Handler final_handler)
    {
        if(account == null)
            throw new IllegalArgumentException("account is null");
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException("status token is empty");
        else
            return (new Future2Task(accountmanagercallback, account, s) {

                public Boolean bundleToResult(Bundle bundle)
                    throws AuthenticatorException
                {
                    if(!bundle.containsKey("booleanResult"))
                        throw new AuthenticatorException("no result in response");
                    else
                        return Boolean.valueOf(bundle.getBoolean("booleanResult"));
                }

                public volatile Object bundleToResult(Bundle bundle)
                    throws AuthenticatorException
                {
                    return bundleToResult(bundle);
                }

                public void doWork()
                    throws RemoteException
                {
                    AccountManager._2D_get4(AccountManager.this).isCredentialsUpdateSuggested(mResponse, account, statusToken);
                }

                final AccountManager this$0;
                final Account val$account;
                final String val$statusToken;

            
            {
                this$0 = final_accountmanager1;
                account = account1;
                statusToken = s;
                super(final_handler, accountmanagercallback);
            }
            }
).start();
    }

    public boolean notifyAccountAuthenticated(Account account)
    {
        if(account == null)
            throw new IllegalArgumentException("account is null");
        boolean flag;
        try
        {
            flag = mService.accountAuthenticated(account);
        }
        // Misplaced declaration of an exception variable
        catch(Account account)
        {
            throw account.rethrowFromSystemServer();
        }
        return flag;
    }

    public String peekAuthToken(Account account, String s)
    {
        if(account == null)
            throw new IllegalArgumentException("account is null");
        if(s == null)
            throw new IllegalArgumentException("authTokenType is null");
        try
        {
            account = mService.peekAuthToken(account, s);
        }
        // Misplaced declaration of an exception variable
        catch(Account account)
        {
            throw account.rethrowFromSystemServer();
        }
        return account;
    }

    public AccountManagerFuture removeAccount(Account account, AccountManagerCallback accountmanagercallback, Handler handler)
    {
        SeempLog.record(25);
        if(account == null)
            throw new IllegalArgumentException("account is null");
        else
            return (new Future2Task(handler, accountmanagercallback, account) {

                public Boolean bundleToResult(Bundle bundle)
                    throws AuthenticatorException
                {
                    if(!bundle.containsKey("booleanResult"))
                        throw new AuthenticatorException("no result in response");
                    else
                        return Boolean.valueOf(bundle.getBoolean("booleanResult"));
                }

                public volatile Object bundleToResult(Bundle bundle)
                    throws AuthenticatorException
                {
                    return bundleToResult(bundle);
                }

                public void doWork()
                    throws RemoteException
                {
                    SeempLog.record(31);
                    AccountManager._2D_get4(AccountManager.this).removeAccount(mResponse, account, false);
                }

                final AccountManager this$0;
                final Account val$account;

            
            {
                this$0 = final_accountmanager1;
                account = account1;
                super(handler, accountmanagercallback);
            }
            }
).start();
    }

    public AccountManagerFuture removeAccount(Account account, final Activity final_activity1, AccountManagerCallback accountmanagercallback, Handler handler)
    {
        SeempLog.record(28);
        if(account == null)
            throw new IllegalArgumentException("account is null");
        else
            return (new AmsTask(handler, accountmanagercallback, account, final_activity1) {

                public void doWork()
                    throws RemoteException
                {
                    SeempLog.record(34);
                    IAccountManager iaccountmanager = AccountManager._2D_get4(AccountManager.this);
                    IAccountManagerResponse iaccountmanagerresponse = mResponse;
                    Account account1 = account;
                    boolean flag;
                    if(activity != null)
                        flag = true;
                    else
                        flag = false;
                    iaccountmanager.removeAccount(iaccountmanagerresponse, account1, flag);
                }

                final AccountManager this$0;
                final Account val$account;
                final Activity val$activity;

            
            {
                this$0 = final_accountmanager1;
                account = account1;
                activity = activity2;
                super(final_activity1, handler, accountmanagercallback);
            }
            }
).start();
    }

    public AccountManagerFuture removeAccountAsUser(Account account, AccountManagerCallback accountmanagercallback, final Handler final_handler, UserHandle userhandle)
    {
        if(account == null)
            throw new IllegalArgumentException("account is null");
        if(userhandle == null)
            throw new IllegalArgumentException("userHandle is null");
        else
            return (new Future2Task(accountmanagercallback, account, userhandle) {

                public Boolean bundleToResult(Bundle bundle)
                    throws AuthenticatorException
                {
                    if(!bundle.containsKey("booleanResult"))
                        throw new AuthenticatorException("no result in response");
                    else
                        return Boolean.valueOf(bundle.getBoolean("booleanResult"));
                }

                public volatile Object bundleToResult(Bundle bundle)
                    throws AuthenticatorException
                {
                    return bundleToResult(bundle);
                }

                public void doWork()
                    throws RemoteException
                {
                    SeempLog.record(31);
                    AccountManager._2D_get4(AccountManager.this).removeAccountAsUser(mResponse, account, false, userHandle.getIdentifier());
                }

                final AccountManager this$0;
                final Account val$account;
                final UserHandle val$userHandle;

            
            {
                this$0 = final_accountmanager1;
                account = account1;
                userHandle = userhandle;
                super(final_handler, accountmanagercallback);
            }
            }
).start();
    }

    public AccountManagerFuture removeAccountAsUser(Account account, final Activity final_activity1, AccountManagerCallback accountmanagercallback, final Handler final_handler, UserHandle userhandle)
    {
        if(account == null)
            throw new IllegalArgumentException("account is null");
        if(userhandle == null)
            throw new IllegalArgumentException("userHandle is null");
        else
            return (new AmsTask(accountmanagercallback, account, final_activity1, userhandle) {

                public void doWork()
                    throws RemoteException
                {
                    SeempLog.record(34);
                    IAccountManager iaccountmanager = AccountManager._2D_get4(AccountManager.this);
                    IAccountManagerResponse iaccountmanagerresponse = mResponse;
                    Account account1 = account;
                    boolean flag;
                    if(activity != null)
                        flag = true;
                    else
                        flag = false;
                    iaccountmanager.removeAccountAsUser(iaccountmanagerresponse, account1, flag, userHandle.getIdentifier());
                }

                final AccountManager this$0;
                final Account val$account;
                final Activity val$activity;
                final UserHandle val$userHandle;

            
            {
                this$0 = final_accountmanager1;
                account = account1;
                activity = activity2;
                userHandle = userhandle;
                super(final_activity1, final_handler, accountmanagercallback);
            }
            }
).start();
    }

    public boolean removeAccountExplicitly(Account account)
    {
        if(account == null)
            throw new IllegalArgumentException("account is null");
        boolean flag;
        try
        {
            flag = mService.removeAccountExplicitly(account);
        }
        // Misplaced declaration of an exception variable
        catch(Account account)
        {
            throw account.rethrowFromSystemServer();
        }
        return flag;
    }

    public void removeOnAccountsUpdatedListener(OnAccountsUpdateListener onaccountsupdatelistener)
    {
        if(onaccountsupdatelistener == null)
            throw new IllegalArgumentException("listener is null");
        HashMap hashmap = mAccountsUpdatedListeners;
        hashmap;
        JVM INSTR monitorenter ;
        if(mAccountsUpdatedListeners.containsKey(onaccountsupdatelistener))
            break MISSING_BLOCK_LABEL_45;
        Log.e("AccountManager", "Listener was not previously added");
        hashmap;
        JVM INSTR monitorexit ;
        return;
        Set set = (Set)mAccountsUpdatedListenersTypes.get(onaccountsupdatelistener);
        if(set == null)
            break MISSING_BLOCK_LABEL_139;
        String as[] = (String[])set.toArray(new String[set.size()]);
_L1:
        mAccountsUpdatedListeners.remove(onaccountsupdatelistener);
        mAccountsUpdatedListenersTypes.remove(onaccountsupdatelistener);
        if(mAccountsUpdatedListeners.isEmpty())
            mContext.unregisterReceiver(mAccountsChangedBroadcastReceiver);
        mService.unregisterAccountListener(as, mContext.getOpPackageName());
        hashmap;
        JVM INSTR monitorexit ;
        return;
        as = null;
          goto _L1
        onaccountsupdatelistener;
        throw onaccountsupdatelistener.rethrowFromSystemServer();
        onaccountsupdatelistener;
        hashmap;
        JVM INSTR monitorexit ;
        throw onaccountsupdatelistener;
    }

    public boolean removeSharedAccount(Account account, UserHandle userhandle)
    {
        boolean flag;
        try
        {
            flag = mService.removeSharedAccountAsUser(account, userhandle.getIdentifier());
        }
        // Misplaced declaration of an exception variable
        catch(Account account)
        {
            throw account.rethrowFromSystemServer();
        }
        return flag;
    }

    public AccountManagerFuture renameAccount(Account account, String s, AccountManagerCallback accountmanagercallback, final Handler final_handler)
    {
        if(account == null)
            throw new IllegalArgumentException("account is null.");
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException("newName is empty or null.");
        else
            return (new Future2Task(accountmanagercallback, account, s) {

                public Account bundleToResult(Bundle bundle)
                    throws AuthenticatorException
                {
                    return new Account(bundle.getString("authAccount"), bundle.getString("accountType"), bundle.getString("accountAccessId"));
                }

                public volatile Object bundleToResult(Bundle bundle)
                    throws AuthenticatorException
                {
                    return bundleToResult(bundle);
                }

                public void doWork()
                    throws RemoteException
                {
                    SeempLog.record(31);
                    AccountManager._2D_get4(AccountManager.this).renameAccount(mResponse, account, newName);
                }

                final AccountManager this$0;
                final Account val$account;
                final String val$newName;

            
            {
                this$0 = final_accountmanager1;
                account = account1;
                newName = s;
                super(final_handler, accountmanagercallback);
            }
            }
).start();
    }

    public boolean setAccountVisibility(Account account, String s, int i)
    {
        if(account == null)
            throw new IllegalArgumentException("account is null");
        boolean flag;
        try
        {
            flag = mService.setAccountVisibility(account, s, i);
        }
        // Misplaced declaration of an exception variable
        catch(Account account)
        {
            throw account.rethrowFromSystemServer();
        }
        return flag;
    }

    public void setAuthToken(Account account, String s, String s1)
    {
        if(account == null)
            throw new IllegalArgumentException("account is null");
        if(s == null)
            throw new IllegalArgumentException("authTokenType is null");
        try
        {
            mService.setAuthToken(account, s, s1);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Account account)
        {
            throw account.rethrowFromSystemServer();
        }
    }

    public void setPassword(Account account, String s)
    {
        SeempLog.record(26);
        if(account == null)
            throw new IllegalArgumentException("account is null");
        try
        {
            mService.setPassword(account, s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Account account)
        {
            throw account.rethrowFromSystemServer();
        }
    }

    public void setUserData(Account account, String s, String s1)
    {
        SeempLog.record(28);
        if(account == null)
            throw new IllegalArgumentException("account is null");
        if(s == null)
            throw new IllegalArgumentException("key is null");
        try
        {
            mService.setUserData(account, s, s1);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Account account)
        {
            throw account.rethrowFromSystemServer();
        }
    }

    public boolean someUserHasAccount(Account account)
    {
        boolean flag;
        try
        {
            flag = mService.someUserHasAccount(account);
        }
        // Misplaced declaration of an exception variable
        catch(Account account)
        {
            throw account.rethrowFromSystemServer();
        }
        return flag;
    }

    public AccountManagerFuture startAddAccountSession(final String accountType, String s, String as[], Bundle bundle, final Activity final_activity1, final AccountManagerCallback final_accountmanagercallback, final Handler final_handler)
    {
        if(accountType == null)
            throw new IllegalArgumentException("accountType is null");
        Bundle bundle1 = new Bundle();
        if(bundle != null)
            bundle1.putAll(bundle);
        bundle1.putString("androidPackageName", mContext.getPackageName());
        return (new AmsTask(s, as, final_activity1, bundle1) {

            public void doWork()
                throws RemoteException
            {
                IAccountManager iaccountmanager = AccountManager._2D_get4(AccountManager.this);
                IAccountManagerResponse iaccountmanagerresponse = mResponse;
                String s1 = accountType;
                String s2 = authTokenType;
                String as1[] = requiredFeatures;
                boolean flag;
                if(activity != null)
                    flag = true;
                else
                    flag = false;
                iaccountmanager.startAddAccountSession(iaccountmanagerresponse, s1, s2, as1, flag, optionsIn);
            }

            final AccountManager this$0;
            final String val$accountType;
            final Activity val$activity;
            final String val$authTokenType;
            final Bundle val$optionsIn;
            final String val$requiredFeatures[];

            
            {
                this$0 = final_accountmanager1;
                accountType = s;
                authTokenType = s1;
                requiredFeatures = as;
                activity = activity2;
                optionsIn = bundle;
                super(final_activity1, final_handler, final_accountmanagercallback);
            }
        }
).start();
    }

    public AccountManagerFuture startUpdateCredentialsSession(Account account, String s, Bundle bundle, final Activity final_activity1, final AccountManagerCallback final_accountmanagercallback, final Handler final_handler)
    {
        if(account == null)
            throw new IllegalArgumentException("account is null");
        Bundle bundle1 = new Bundle();
        if(bundle != null)
            bundle1.putAll(bundle);
        bundle1.putString("androidPackageName", mContext.getPackageName());
        return (new AmsTask(account, s, final_activity1, bundle1) {

            public void doWork()
                throws RemoteException
            {
                IAccountManager iaccountmanager = AccountManager._2D_get4(AccountManager.this);
                IAccountManagerResponse iaccountmanagerresponse = mResponse;
                Account account1 = account;
                String s1 = authTokenType;
                boolean flag;
                if(activity != null)
                    flag = true;
                else
                    flag = false;
                iaccountmanager.startUpdateCredentialsSession(iaccountmanagerresponse, account1, s1, flag, optionsIn);
            }

            final AccountManager this$0;
            final Account val$account;
            final Activity val$activity;
            final String val$authTokenType;
            final Bundle val$optionsIn;

            
            {
                this$0 = final_accountmanager1;
                account = account1;
                authTokenType = s;
                activity = activity2;
                optionsIn = bundle;
                super(final_activity1, final_handler, final_accountmanagercallback);
            }
        }
).start();
    }

    public void updateAppPermission(Account account, String s, int i, boolean flag)
    {
        try
        {
            mService.updateAppPermission(account, s, i, flag);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Account account)
        {
            throw account.rethrowFromSystemServer();
        }
    }

    public AccountManagerFuture updateCredentials(Account account, String s, Bundle bundle, final Activity final_activity1, final AccountManagerCallback final_accountmanagercallback, final Handler final_handler)
    {
        if(account == null)
            throw new IllegalArgumentException("account is null");
        else
            return (new AmsTask(account, s, final_activity1, bundle) {

                public void doWork()
                    throws RemoteException
                {
                    IAccountManager iaccountmanager = AccountManager._2D_get4(AccountManager.this);
                    IAccountManagerResponse iaccountmanagerresponse = mResponse;
                    Account account1 = account;
                    String s1 = authTokenType;
                    boolean flag;
                    if(activity != null)
                        flag = true;
                    else
                        flag = false;
                    iaccountmanager.updateCredentials(iaccountmanagerresponse, account1, s1, flag, options);
                }

                final AccountManager this$0;
                final Account val$account;
                final Activity val$activity;
                final String val$authTokenType;
                final Bundle val$options;

            
            {
                this$0 = final_accountmanager1;
                account = account1;
                authTokenType = s;
                activity = activity2;
                options = bundle;
                super(final_activity1, final_handler, final_accountmanagercallback);
            }
            }
).start();
    }

    public static final String ACCOUNT_ACCESS_TOKEN_TYPE = "com.android.AccountManager.ACCOUNT_ACCESS_TOKEN_TYPE";
    public static final String ACTION_ACCOUNT_REMOVED = "android.accounts.action.ACCOUNT_REMOVED";
    public static final String ACTION_AUTHENTICATOR_INTENT = "android.accounts.AccountAuthenticator";
    public static final String ACTION_VISIBLE_ACCOUNTS_CHANGED = "android.accounts.action.VISIBLE_ACCOUNTS_CHANGED";
    public static final String AUTHENTICATOR_ATTRIBUTES_NAME = "account-authenticator";
    public static final String AUTHENTICATOR_META_DATA_NAME = "android.accounts.AccountAuthenticator";
    public static final int ERROR_CODE_BAD_ARGUMENTS = 7;
    public static final int ERROR_CODE_BAD_AUTHENTICATION = 9;
    public static final int ERROR_CODE_BAD_REQUEST = 8;
    public static final int ERROR_CODE_CANCELED = 4;
    public static final int ERROR_CODE_INVALID_RESPONSE = 5;
    public static final int ERROR_CODE_MANAGEMENT_DISABLED_FOR_ACCOUNT_TYPE = 101;
    public static final int ERROR_CODE_NETWORK_ERROR = 3;
    public static final int ERROR_CODE_REMOTE_EXCEPTION = 1;
    public static final int ERROR_CODE_UNSUPPORTED_OPERATION = 6;
    public static final int ERROR_CODE_USER_RESTRICTED = 100;
    public static final String KEY_ACCOUNTS = "accounts";
    public static final String KEY_ACCOUNT_ACCESS_ID = "accountAccessId";
    public static final String KEY_ACCOUNT_AUTHENTICATOR_RESPONSE = "accountAuthenticatorResponse";
    public static final String KEY_ACCOUNT_MANAGER_RESPONSE = "accountManagerResponse";
    public static final String KEY_ACCOUNT_NAME = "authAccount";
    public static final String KEY_ACCOUNT_SESSION_BUNDLE = "accountSessionBundle";
    public static final String KEY_ACCOUNT_STATUS_TOKEN = "accountStatusToken";
    public static final String KEY_ACCOUNT_TYPE = "accountType";
    public static final String KEY_ANDROID_PACKAGE_NAME = "androidPackageName";
    public static final String KEY_AUTHENTICATOR_TYPES = "authenticator_types";
    public static final String KEY_AUTHTOKEN = "authtoken";
    public static final String KEY_AUTH_FAILED_MESSAGE = "authFailedMessage";
    public static final String KEY_AUTH_TOKEN_LABEL = "authTokenLabelKey";
    public static final String KEY_BOOLEAN_RESULT = "booleanResult";
    public static final String KEY_CALLER_PID = "callerPid";
    public static final String KEY_CALLER_UID = "callerUid";
    public static final String KEY_ERROR_CODE = "errorCode";
    public static final String KEY_ERROR_MESSAGE = "errorMessage";
    public static final String KEY_INTENT = "intent";
    public static final String KEY_LAST_AUTHENTICATED_TIME = "lastAuthenticatedTime";
    public static final String KEY_NOTIFY_ON_FAILURE = "notifyOnAuthFailure";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_USERDATA = "userdata";
    public static final String LOGIN_ACCOUNTS_CHANGED_ACTION = "android.accounts.LOGIN_ACCOUNTS_CHANGED";
    public static final String PACKAGE_NAME_KEY_LEGACY_NOT_VISIBLE = "android:accounts:key_legacy_not_visible";
    public static final String PACKAGE_NAME_KEY_LEGACY_VISIBLE = "android:accounts:key_legacy_visible";
    private static final String TAG = "AccountManager";
    public static final int VISIBILITY_NOT_VISIBLE = 3;
    public static final int VISIBILITY_UNDEFINED = 0;
    public static final int VISIBILITY_USER_MANAGED_NOT_VISIBLE = 4;
    public static final int VISIBILITY_USER_MANAGED_VISIBLE = 2;
    public static final int VISIBILITY_VISIBLE = 1;
    private final BroadcastReceiver mAccountsChangedBroadcastReceiver;
    private final HashMap mAccountsUpdatedListeners;
    private final HashMap mAccountsUpdatedListenersTypes;
    private final Context mContext;
    private final Handler mMainHandler;
    private final IAccountManager mService;

    // Unreferenced inner class android/accounts/AccountManager$AmsTask$1

/* anonymous class */
    static final class AmsTask._cls1
        implements Callable
    {

        public Bundle call()
            throws Exception
        {
            throw new IllegalStateException("this should never be called");
        }

        public volatile Object call()
            throws Exception
        {
            return call();
        }

        final AccountManager val$this$0;

            
            {
                this$0 = accountmanager;
                super();
            }
    }


    // Unreferenced inner class android/accounts/AccountManager$BaseFutureTask$1

/* anonymous class */
    static final class BaseFutureTask._cls1
        implements Callable
    {

        public Object call()
            throws Exception
        {
            throw new IllegalStateException("this should never be called");
        }

        final AccountManager val$this$0;

            
            {
                this$0 = accountmanager;
                super();
            }
    }

}
