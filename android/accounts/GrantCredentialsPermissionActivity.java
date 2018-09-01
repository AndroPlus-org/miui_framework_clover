// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.accounts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.IOException;

// Referenced classes of package android.accounts:
//            AccountManager, AuthenticatorDescription, Account, AccountAuthenticatorResponse, 
//            AccountManagerCallback, OperationCanceledException, AuthenticatorException, AccountManagerFuture

public class GrantCredentialsPermissionActivity extends Activity
    implements android.view.View.OnClickListener
{

    public GrantCredentialsPermissionActivity()
    {
        mResultBundle = null;
    }

    private String getAccountLabel(Account account)
    {
        AuthenticatorDescription aauthenticatordescription[] = AccountManager.get(this).getAuthenticatorTypes();
        int i = 0;
        for(int j = aauthenticatordescription.length; i < j; i++)
        {
            AuthenticatorDescription authenticatordescription = aauthenticatordescription[i];
            if(authenticatordescription.type.equals(account.type))
            {
                String s;
                try
                {
                    s = createPackageContext(authenticatordescription.packageName, 0).getString(authenticatordescription.labelId);
                }
                catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
                {
                    return account.type;
                }
                catch(android.content.res.Resources.NotFoundException notfoundexception)
                {
                    return account.type;
                }
                return s;
            }
        }

        return account.type;
    }

    private View newPackageView(String s)
    {
        View view = mInflater.inflate(0x10900b3, null);
        ((TextView)view.findViewById(0x102035e)).setText(s);
        return view;
    }

    public void finish()
    {
        AccountAuthenticatorResponse accountauthenticatorresponse = (AccountAuthenticatorResponse)getIntent().getParcelableExtra("response");
        if(accountauthenticatorresponse != null)
            if(mResultBundle != null)
                accountauthenticatorresponse.onResult(mResultBundle);
            else
                accountauthenticatorresponse.onError(4, "canceled");
        super.finish();
    }

    public void onClick(View view)
    {
        view.getId();
        JVM INSTR lookupswitch 2: default 32
    //                   16908703: 37
    //                   16908837: 90;
           goto _L1 _L2 _L3
_L1:
        finish();
        return;
_L2:
        AccountManager.get(this).updateAppPermission(mAccount, mAuthTokenType, mUid, true);
        view = new Intent();
        view.putExtra("retry", true);
        setResult(-1, view);
        setAccountAuthenticatorResult(view.getExtras());
        continue; /* Loop/switch isn't completed */
_L3:
        AccountManager.get(this).updateAppPermission(mAccount, mAuthTokenType, mUid, false);
        setResult(0);
        if(true) goto _L1; else goto _L4
_L4:
    }

    protected void onCreate(final Bundle authTokenTypeView)
    {
        String s;
        super.onCreate(authTokenTypeView);
        setContentView(0x1090068);
        setTitle(0x104025d);
        mInflater = (LayoutInflater)getSystemService("layout_inflater");
        authTokenTypeView = getIntent().getExtras();
        if(authTokenTypeView == null)
        {
            setResult(0);
            finish();
            return;
        }
        mAccount = (Account)authTokenTypeView.getParcelable("account");
        mAuthTokenType = authTokenTypeView.getString("authTokenType");
        mUid = authTokenTypeView.getInt("uid");
        PackageManager packagemanager = getPackageManager();
        String as[];
        for(as = packagemanager.getPackagesForUid(mUid); mAccount == null || mAuthTokenType == null || as == null;)
        {
            setResult(0);
            finish();
            return;
        }

        LinearLayout linearlayout;
        int i;
        int j;
        String s1;
        try
        {
            s = getAccountLabel(mAccount);
        }
        // Misplaced declaration of an exception variable
        catch(final Bundle authTokenTypeView)
        {
            setResult(0);
            finish();
            return;
        }
        authTokenTypeView = (TextView)findViewById(0x10201b2);
        authTokenTypeView.setVisibility(8);
        authTokenTypeView = new AccountManagerCallback() {

            public void run(AccountManagerFuture accountmanagerfuture)
            {
                String s2 = (String)accountmanagerfuture.getResult();
                if(!TextUtils.isEmpty(s2))
                {
                    GrantCredentialsPermissionActivity grantcredentialspermissionactivity = GrantCredentialsPermissionActivity.this;
                    accountmanagerfuture = JVM INSTR new #14  <Class GrantCredentialsPermissionActivity$1$1>;
                    authTokenTypeView.s2. _cls1();
                    grantcredentialspermissionactivity.runOnUiThread(accountmanagerfuture);
                }
_L2:
                return;
                accountmanagerfuture;
                continue; /* Loop/switch isn't completed */
                accountmanagerfuture;
                continue; /* Loop/switch isn't completed */
                accountmanagerfuture;
                if(true) goto _L2; else goto _L1
_L1:
            }

            final GrantCredentialsPermissionActivity this$0;
            final TextView val$authTokenTypeView;

            
            {
                this$0 = GrantCredentialsPermissionActivity.this;
                authTokenTypeView = textview;
                super();
            }
        }
;
        if(!"com.android.AccountManager.ACCOUNT_ACCESS_TOKEN_TYPE".equals(mAuthTokenType))
            AccountManager.get(this).getAuthTokenLabel(mAccount.type, mAuthTokenType, authTokenTypeView, null);
        findViewById(0x102019f).setOnClickListener(this);
        findViewById(0x1020225).setOnClickListener(this);
        linearlayout = (LinearLayout)findViewById(0x102035f);
        i = 0;
        j = as.length;
        if(i >= j)
            break; /* Loop/switch isn't completed */
        authTokenTypeView = as[i];
        s1 = packagemanager.getApplicationLabel(packagemanager.getApplicationInfo(authTokenTypeView, 0)).toString();
        authTokenTypeView = s1;
_L4:
        linearlayout.addView(newPackageView(authTokenTypeView));
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break MISSING_BLOCK_LABEL_234;
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        namenotfoundexception;
        if(true) goto _L4; else goto _L3
_L3:
        ((TextView)findViewById(0x102016e)).setText(mAccount.name);
        ((TextView)findViewById(0x1020171)).setText(s);
        return;
    }

    public final void setAccountAuthenticatorResult(Bundle bundle)
    {
        mResultBundle = bundle;
    }

    public static final String EXTRAS_ACCOUNT = "account";
    public static final String EXTRAS_AUTH_TOKEN_TYPE = "authTokenType";
    public static final String EXTRAS_REQUESTING_UID = "uid";
    public static final String EXTRAS_RESPONSE = "response";
    private Account mAccount;
    private String mAuthTokenType;
    protected LayoutInflater mInflater;
    private Bundle mResultBundle;
    private int mUid;

    // Unreferenced inner class android/accounts/GrantCredentialsPermissionActivity$1$1

/* anonymous class */
    class _cls1
        implements Runnable
    {

        public void run()
        {
            if(!isFinishing())
            {
                authTokenTypeView.setText(authTokenLabel);
                authTokenTypeView.setVisibility(0);
            }
        }

        final _cls1 this$1;
        final String val$authTokenLabel;
        final TextView val$authTokenTypeView;

            
            {
                this$1 = final__pcls1;
                authTokenTypeView = textview;
                authTokenLabel = String.this;
                super();
            }
    }

}
