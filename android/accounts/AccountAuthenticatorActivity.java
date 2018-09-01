// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.accounts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

// Referenced classes of package android.accounts:
//            AccountAuthenticatorResponse

public class AccountAuthenticatorActivity extends Activity
{

    public AccountAuthenticatorActivity()
    {
        mAccountAuthenticatorResponse = null;
        mResultBundle = null;
    }

    public void finish()
    {
        if(mAccountAuthenticatorResponse != null)
        {
            if(mResultBundle != null)
                mAccountAuthenticatorResponse.onResult(mResultBundle);
            else
                mAccountAuthenticatorResponse.onError(4, "canceled");
            mAccountAuthenticatorResponse = null;
        }
        super.finish();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        mAccountAuthenticatorResponse = (AccountAuthenticatorResponse)getIntent().getParcelableExtra("accountAuthenticatorResponse");
        if(mAccountAuthenticatorResponse != null)
            mAccountAuthenticatorResponse.onRequestContinued();
    }

    public final void setAccountAuthenticatorResult(Bundle bundle)
    {
        mResultBundle = bundle;
    }

    private AccountAuthenticatorResponse mAccountAuthenticatorResponse;
    private Bundle mResultBundle;
}
