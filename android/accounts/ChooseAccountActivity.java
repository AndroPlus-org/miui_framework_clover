// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.accounts;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.*;
import android.util.Log;
import android.view.*;
import android.widget.*;
import java.util.HashMap;

// Referenced classes of package android.accounts:
//            AccountManager, AuthenticatorDescription, AccountManagerResponse, Account

public class ChooseAccountActivity extends Activity
{
    private static class AccountArrayAdapter extends ArrayAdapter
    {

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            if(view == null)
            {
                view = mLayoutInflater.inflate(0x1090044, null);
                viewgroup = new ViewHolder(null);
                viewgroup.text = (TextView)view.findViewById(0x1020170);
                viewgroup.icon = (ImageView)view.findViewById(0x102016f);
                view.setTag(viewgroup);
            } else
            {
                viewgroup = (ViewHolder)view.getTag();
            }
            ((ViewHolder) (viewgroup)).text.setText(mInfos[i].name);
            ((ViewHolder) (viewgroup)).icon.setImageDrawable(mInfos[i].drawable);
            return view;
        }

        private AccountInfo mInfos[];
        private LayoutInflater mLayoutInflater;

        public AccountArrayAdapter(Context context, int i, AccountInfo aaccountinfo[])
        {
            super(context, i, aaccountinfo);
            mInfos = aaccountinfo;
            mLayoutInflater = (LayoutInflater)context.getSystemService("layout_inflater");
        }
    }

    private static class AccountInfo
    {

        final Drawable drawable;
        final String name;

        AccountInfo(String s, Drawable drawable1)
        {
            name = s;
            drawable = drawable1;
        }
    }

    private static class ViewHolder
    {

        ImageView icon;
        TextView text;

        private ViewHolder()
        {
        }

        ViewHolder(ViewHolder viewholder)
        {
            this();
        }
    }


    public ChooseAccountActivity()
    {
        mAccounts = null;
        mAccountManagerResponse = null;
        mTypeToAuthDescription = new HashMap();
    }

    private void getAuthDescriptions()
    {
        AuthenticatorDescription aauthenticatordescription[] = AccountManager.get(this).getAuthenticatorTypes();
        int i = 0;
        for(int j = aauthenticatordescription.length; i < j; i++)
        {
            AuthenticatorDescription authenticatordescription = aauthenticatordescription[i];
            mTypeToAuthDescription.put(authenticatordescription.type, authenticatordescription);
        }

    }

    private Drawable getDrawableForType(String s)
    {
        Object obj;
        Object obj1;
        obj = null;
        obj1 = obj;
        if(!mTypeToAuthDescription.containsKey(s))
            break MISSING_BLOCK_LABEL_44;
        obj1 = (AuthenticatorDescription)mTypeToAuthDescription.get(s);
        obj1 = createPackageContext(((AuthenticatorDescription) (obj1)).packageName, 0).getDrawable(((AuthenticatorDescription) (obj1)).iconId);
_L2:
        return ((Drawable) (obj1));
        obj1;
        obj1 = obj;
        if(Log.isLoggable("AccountManager", 5))
        {
            Log.w("AccountManager", (new StringBuilder()).append("No icon resource for account type ").append(s).toString());
            obj1 = obj;
        }
        continue; /* Loop/switch isn't completed */
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        namenotfoundexception;
        namenotfoundexception = obj;
        if(Log.isLoggable("AccountManager", 5))
        {
            Log.w("AccountManager", (new StringBuilder()).append("No icon name for account type ").append(s).toString());
            namenotfoundexception = obj;
        }
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void finish()
    {
        if(mAccountManagerResponse != null)
            if(mResult != null)
                mAccountManagerResponse.onResult(mResult);
            else
                mAccountManagerResponse.onError(4, "canceled");
        super.finish();
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        mAccounts = getIntent().getParcelableArrayExtra("accounts");
        mAccountManagerResponse = (AccountManagerResponse)getIntent().getParcelableExtra("accountManagerResponse");
        if(mAccounts == null)
        {
            setResult(0);
            finish();
            return;
        }
        AccountInfo aaccountinfo[];
        try
        {
            bundle = getActivityToken();
            mCallingUid = ActivityManager.getService().getLaunchedFromUid(bundle);
            mCallingPackage = ActivityManager.getService().getLaunchedFromPackage(bundle);
        }
        // Misplaced declaration of an exception variable
        catch(Bundle bundle)
        {
            Log.w(getClass().getSimpleName(), (new StringBuilder()).append("Unable to get caller identity \n").append(bundle).toString());
        }
        if(UserHandle.isSameApp(mCallingUid, 1000) && getIntent().getStringExtra("androidPackageName") != null)
            mCallingPackage = getIntent().getStringExtra("androidPackageName");
        if(!UserHandle.isSameApp(mCallingUid, 1000) && getIntent().getStringExtra("androidPackageName") != null)
            Log.w(getClass().getSimpleName(), (new StringBuilder()).append("Non-system Uid: ").append(mCallingUid).append(" tried to override packageName \n").toString());
        getAuthDescriptions();
        aaccountinfo = new AccountInfo[mAccounts.length];
        for(int i = 0; i < mAccounts.length; i++)
            aaccountinfo[i] = new AccountInfo(((Account)mAccounts[i]).name, getDrawableForType(((Account)mAccounts[i]).type));

        setContentView(0x1090043);
        bundle = (ListView)findViewById(0x102000a);
        bundle.setAdapter(new AccountArrayAdapter(this, 0x1090003, aaccountinfo));
        bundle.setChoiceMode(1);
        bundle.setTextFilterEnabled(true);
        bundle.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view, int j, long l)
            {
                onListItemClick((ListView)adapterview, view, j, l);
            }

            final ChooseAccountActivity this$0;

            
            {
                this$0 = ChooseAccountActivity.this;
                super();
            }
        }
);
    }

    protected void onListItemClick(ListView listview, View view, int i, long l)
    {
        listview = (Account)mAccounts[i];
        view = AccountManager.get(this);
        Integer integer = Integer.valueOf(view.getAccountVisibility(listview, mCallingPackage));
        if(integer != null && integer.intValue() == 4)
            view.setAccountVisibility(listview, mCallingPackage, 2);
        Log.d("AccountManager", (new StringBuilder()).append("selected account ").append(listview).toString());
        view = new Bundle();
        view.putString("authAccount", ((Account) (listview)).name);
        view.putString("accountType", ((Account) (listview)).type);
        mResult = view;
        finish();
    }

    private static final String TAG = "AccountManager";
    private AccountManagerResponse mAccountManagerResponse;
    private Parcelable mAccounts[];
    private String mCallingPackage;
    private int mCallingUid;
    private Bundle mResult;
    private HashMap mTypeToAuthDescription;
}
