// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.*;
import android.os.*;
import android.util.Log;

// Referenced classes of package com.android.internal.app:
//            AlertActivity

public class ConfirmUserCreationActivity extends AlertActivity
    implements android.content.DialogInterface.OnClickListener
{

    public ConfirmUserCreationActivity()
    {
    }

    private String checkUserCreationRequirements()
    {
        Object obj = getCallingPackage();
        if(obj == null)
            throw new SecurityException("User Creation intent must be launched with startActivityForResult");
        boolean flag;
        boolean flag1;
        Account account;
        boolean flag2;
        try
        {
            obj = getPackageManager().getApplicationInfo(((String) (obj)), 0);
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            throw new SecurityException("Cannot find the calling package");
        }
        if(!mUserManager.hasUserRestriction("no_add_user"))
            flag = mUserManager.isAdminUser() ^ true;
        else
            flag = true;
        flag1 = mUserManager.canAddMoreUsers();
        account = new Account(mAccountName, mAccountType);
        if(mAccountName != null && mAccountType != null)
            flag2 = AccountManager.get(this).someUserHasAccount(account) | mUserManager.someUserHasSeedAccount(mAccountName, mAccountType);
        else
            flag2 = false;
        mCanProceed = true;
        obj = ((ApplicationInfo) (obj)).loadLabel(getPackageManager()).toString();
        if(flag)
        {
            setResult(1);
            return null;
        }
        if(flag1 ^ true)
        {
            setResult(2);
            return null;
        }
        if(flag2)
            obj = getString(0x1040672, new Object[] {
                obj, mAccountName
            });
        else
            obj = getString(0x1040673, new Object[] {
                obj, mAccountName
            });
        return ((String) (obj));
    }

    public void onClick(DialogInterface dialoginterface, int i)
    {
        setResult(0);
        if(i == -1 && mCanProceed)
        {
            Log.i("CreateUser", "Ok, creating user");
            dialoginterface = mUserManager.createUser(mUserName, 0);
            if(dialoginterface == null)
            {
                Log.e("CreateUser", "Couldn't create user");
                finish();
                return;
            }
            mUserManager.setSeedAccountData(((UserInfo) (dialoginterface)).id, mAccountName, mAccountType, mAccountOptions);
            setResult(-1);
        }
        finish();
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        bundle = getIntent();
        mUserName = bundle.getStringExtra("android.os.extra.USER_NAME");
        mAccountName = bundle.getStringExtra("android.os.extra.USER_ACCOUNT_NAME");
        mAccountType = bundle.getStringExtra("android.os.extra.USER_ACCOUNT_TYPE");
        mAccountOptions = (PersistableBundle)bundle.getParcelableExtra("android.os.extra.USER_ACCOUNT_OPTIONS");
        mUserManager = (UserManager)getSystemService(android/os/UserManager);
        String s = checkUserCreationRequirements();
        if(s == null)
        {
            finish();
            return;
        }
        bundle = mAlertParams;
        bundle.mMessage = s;
        bundle.mPositiveButtonText = getString(0x104000a);
        bundle.mPositiveButtonListener = this;
        if(mCanProceed)
        {
            bundle.mNegativeButtonText = getString(0x1040000);
            bundle.mNegativeButtonListener = this;
        }
        setupAlert();
    }

    private static final String TAG = "CreateUser";
    private String mAccountName;
    private PersistableBundle mAccountOptions;
    private String mAccountType;
    private boolean mCanProceed;
    private UserManager mUserManager;
    private String mUserName;
}
