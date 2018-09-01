// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.accounts;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class CantAddAccountActivity extends Activity
{

    public CantAddAccountActivity()
    {
    }

    public void onCancelButtonClicked(View view)
    {
        onBackPressed();
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x1090036);
    }

    public static final String EXTRA_ERROR_CODE = "android.accounts.extra.ERROR_CODE";
}
