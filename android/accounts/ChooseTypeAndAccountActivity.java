// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.accounts;

import android.app.*;
import android.content.Intent;
import android.content.res.Resources;
import android.os.*;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.google.android.collect.Sets;
import java.io.IOException;
import java.util.*;

// Referenced classes of package android.accounts:
//            AccountManagerCallback, AccountManager, Account, AuthenticatorDescription, 
//            ChooseTypeAndAccountActivityInjector, OperationCanceledException, AuthenticatorException, AccountManagerFuture

public class ChooseTypeAndAccountActivity extends Activity
    implements AccountManagerCallback
{

    static Button _2D_get0(ChooseTypeAndAccountActivity choosetypeandaccountactivity)
    {
        return choosetypeandaccountactivity.mOkButton;
    }

    static int _2D_set0(ChooseTypeAndAccountActivity choosetypeandaccountactivity, int i)
    {
        choosetypeandaccountactivity.mSelectedItemIndex = i;
        return i;
    }

    public ChooseTypeAndAccountActivity()
    {
        mSelectedAccountName = null;
        mSelectedAddNewAccount = false;
        mPendingRequest = 0;
        mExistingAccounts = null;
    }

    private LinkedHashMap getAcceptableAccountChoices(AccountManager accountmanager)
    {
        Map map = accountmanager.getAccountsAndVisibilityForPackage(mCallingPackage, null);
        Account aaccount[] = accountmanager.getAccounts();
        accountmanager = new LinkedHashMap(map.size());
        int i = aaccount.length;
        int j = 0;
        do
        {
            if(j >= i)
                break;
            Account account = aaccount[j];
            if((mSetOfAllowableAccounts == null || !(mSetOfAllowableAccounts.contains(account) ^ true)) && (mSetOfRelevantAccountTypes == null || !(mSetOfRelevantAccountTypes.contains(account.type) ^ true)) && map.get(account) != null)
                accountmanager.put(account, (Integer)map.get(account));
            j++;
        } while(true);
        return accountmanager;
    }

    private Set getAllowableAccountSet(Intent intent)
    {
        Object obj = null;
        Object obj1 = intent.getParcelableArrayListExtra("allowableAccounts");
        intent = obj;
        if(obj1 != null)
        {
            HashSet hashset = new HashSet(((ArrayList) (obj1)).size());
            obj1 = ((Iterable) (obj1)).iterator();
            do
            {
                intent = hashset;
                if(!((Iterator) (obj1)).hasNext())
                    break;
                hashset.add((Account)(Parcelable)((Iterator) (obj1)).next());
            } while(true);
        }
        return intent;
    }

    private int getItemIndexToSelect(ArrayList arraylist, String s, boolean flag)
    {
        if(flag)
            return arraylist.size();
        for(int i = 0; i < arraylist.size(); i++)
            if(((Account)arraylist.get(i)).name.equals(s))
                return i;

        return -1;
    }

    private String[] getListOfDisplayableOptions(ArrayList arraylist)
    {
        int i = arraylist.size();
        int j;
        String as[];
        if(mDisallowAddAccounts)
            j = 0;
        else
            j = 1;
        as = new String[j + i];
        for(j = 0; j < arraylist.size(); j++)
            as[j] = ((Account)arraylist.get(j)).name;

        if(!mDisallowAddAccounts)
            as[arraylist.size()] = getResources().getString(0x1040062);
        return as;
    }

    private Set getReleventAccountTypes(Intent intent)
    {
        String as[] = intent.getStringArrayExtra("allowableAccountTypes");
        AuthenticatorDescription aauthenticatordescription[] = AccountManager.get(this).getAuthenticatorTypes();
        intent = new HashSet(aauthenticatordescription.length);
        int i = 0;
        for(int j = aauthenticatordescription.length; i < j; i++)
            intent.add(aauthenticatordescription[i].type);

        if(as != null)
        {
            HashSet hashset = Sets.newHashSet(as);
            hashset.retainAll(intent);
            intent = hashset;
        }
        return intent;
    }

    private void onAccountSelected(Account account)
    {
        Log.d("AccountChooser", (new StringBuilder()).append("selected account ").append(account).toString());
        setResultAndFinish(account.name, account.type);
    }

    private void overrideDescriptionIfSupplied(String s)
    {
        TextView textview = (TextView)findViewById(0x1020226);
        if(!TextUtils.isEmpty(s))
            textview.setText(s);
        else
            textview.setVisibility(8);
    }

    private final void populateUIAccountList(String as[])
    {
        ListView listview = (ListView)findViewById(0x102000a);
        listview.setAdapter(new ArrayAdapter(this, 0x109000f, as));
        listview.setChoiceMode(1);
        listview.setItemsCanFocus(false);
        listview.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view, int i, long l)
            {
                ChooseTypeAndAccountActivity._2D_set0(ChooseTypeAndAccountActivity.this, i);
                ChooseTypeAndAccountActivity._2D_get0(ChooseTypeAndAccountActivity.this).setEnabled(true);
            }

            final ChooseTypeAndAccountActivity this$0;

            
            {
                this$0 = ChooseTypeAndAccountActivity.this;
                super();
            }
        }
);
        if(mSelectedItemIndex != -1)
        {
            listview.setItemChecked(mSelectedItemIndex, true);
            if(Log.isLoggable("AccountChooser", 2))
                Log.v("AccountChooser", (new StringBuilder()).append("List item ").append(mSelectedItemIndex).append(" should be selected").toString());
        }
    }

    private void setNonLabelThemeAndCallSuperCreate(Bundle bundle)
    {
        setTheme(0x1030134);
        super.onCreate(bundle);
    }

    private void setResultAndFinish(String s, String s1)
    {
        Account account = new Account(s, s1);
        Object obj = Integer.valueOf(AccountManager.get(this).getAccountVisibility(account, mCallingPackage));
        if(obj != null && ((Integer) (obj)).intValue() == 4)
            AccountManager.get(this).setAccountVisibility(account, mCallingPackage, 2);
        if(obj != null && ((Integer) (obj)).intValue() == 3)
        {
            setResult(0);
            finish();
            return;
        }
        obj = new Bundle();
        ((Bundle) (obj)).putString("authAccount", s);
        ((Bundle) (obj)).putString("accountType", s1);
        setResult(-1, (new Intent()).putExtras(((Bundle) (obj))));
        if(Log.isLoggable("AccountChooser", 2))
            Log.v("AccountChooser", (new StringBuilder()).append("ChooseTypeAndAccountActivity.setResultAndFinish: selected account ").append(s).append(", ").append(s1).toString());
        finish();
    }

    private void startChooseAccountTypeActivity()
    {
        if(Log.isLoggable("AccountChooser", 2))
            Log.v("AccountChooser", "ChooseAccountTypeActivity.startChooseAccountTypeActivity()");
        Intent intent = new Intent();
        ChooseTypeAndAccountActivityInjector.toMiuiChooseAccountTypeActivity(intent);
        intent.setFlags(0x80000);
        intent.putExtra("allowableAccountTypes", getIntent().getStringArrayExtra("allowableAccountTypes"));
        intent.putExtra("addAccountOptions", getIntent().getBundleExtra("addAccountOptions"));
        intent.putExtra("addAccountRequiredFeatures", getIntent().getStringArrayExtra("addAccountRequiredFeatures"));
        intent.putExtra("authTokenType", getIntent().getStringExtra("authTokenType"));
        startActivityForResult(intent, 1);
        mPendingRequest = 1;
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        if(Log.isLoggable("AccountChooser", 2))
        {
            if(intent != null && intent.getExtras() != null)
                intent.getExtras().keySet();
            Bundle bundle;
            if(intent != null)
                bundle = intent.getExtras();
            else
                bundle = null;
            Log.v("AccountChooser", (new StringBuilder()).append("ChooseTypeAndAccountActivity.onActivityResult(reqCode=").append(i).append(", resCode=").append(j).append(", extras=").append(bundle).append(")").toString());
        }
        mPendingRequest = 0;
        if(j == 0)
        {
            if(mPossiblyVisibleAccounts.isEmpty())
            {
                setResult(0);
                finish();
            }
            return;
        }
        if(j != -1) goto _L2; else goto _L1
_L1:
        if(i != 1) goto _L4; else goto _L3
_L3:
        if(intent != null)
        {
            intent = intent.getStringExtra("accountType");
            if(intent != null)
            {
                runAddAccountForAuthenticator(intent);
                return;
            }
        }
        Log.d("AccountChooser", "ChooseTypeAndAccountActivity.onActivityResult: unable to find account type, pretending the request was canceled");
_L6:
        Log.d("AccountChooser", "ChooseTypeAndAccountActivity.onActivityResult: unable to find added account, pretending the request was canceled");
_L2:
        if(Log.isLoggable("AccountChooser", 2))
            Log.v("AccountChooser", "ChooseTypeAndAccountActivity.onActivityResult: canceled");
        setResult(0);
        finish();
        return;
_L4:
        if(i != 2) goto _L6; else goto _L5
_L5:
        String s;
        String s1;
        String s2;
        Account aaccount[];
        HashSet hashset;
        s = null;
        s1 = null;
        if(intent != null)
        {
            s = intent.getStringExtra("authAccount");
            s1 = intent.getStringExtra("accountType");
        }
        if(s != null)
        {
            s2 = s;
            intent = s1;
            if(s1 != null)
                continue; /* Loop/switch isn't completed */
        }
        aaccount = AccountManager.get(this).getAccountsForPackage(mCallingPackage, mCallingUid);
        hashset = new HashSet();
        intent = mExistingAccounts;
        j = intent.length;
        for(i = 0; i < j; i++)
            hashset.add((Account)intent[i]);

        j = aaccount.length;
        i = 0;
_L8:
        s2 = s;
        intent = s1;
        if(i >= j)
            continue; /* Loop/switch isn't completed */
        intent = aaccount[i];
        if(hashset.contains(intent))
            break MISSING_BLOCK_LABEL_377;
        s2 = ((Account) (intent)).name;
        intent = ((Account) (intent)).type;
        if(s2 == null && intent == null) goto _L6; else goto _L7
_L7:
        setResultAndFinish(s2, intent);
        return;
        i++;
          goto _L8
    }

    public void onCancelButtonClicked(View view)
    {
        onBackPressed();
    }

    public void onCreate(Bundle bundle)
    {
        if(Log.isLoggable("AccountChooser", 2))
            Log.v("AccountChooser", (new StringBuilder()).append("ChooseTypeAndAccountActivity.onCreate(savedInstanceState=").append(bundle).append(")").toString());
        Object obj;
        try
        {
            android.os.IBinder ibinder = getActivityToken();
            mCallingUid = ActivityManager.getService().getLaunchedFromUid(ibinder);
            mCallingPackage = ActivityManager.getService().getLaunchedFromPackage(ibinder);
            if(mCallingUid != 0 && mCallingPackage != null)
            {
                UserManager usermanager = UserManager.get(this);
                UserHandle userhandle = JVM INSTR new #520 <Class UserHandle>;
                userhandle.UserHandle(UserHandle.getUserId(mCallingUid));
                mDisallowAddAccounts = usermanager.getUserRestrictions(userhandle).getBoolean("no_modify_accounts", false);
            }
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            Log.w(getClass().getSimpleName(), (new StringBuilder()).append("Unable to get caller identity \n").append(obj).toString());
        }
        obj = getIntent();
        mSetOfAllowableAccounts = getAllowableAccountSet(((Intent) (obj)));
        mSetOfRelevantAccountTypes = getReleventAccountTypes(((Intent) (obj)));
        mDescriptionOverride = ((Intent) (obj)).getStringExtra("descriptionTextOverride");
        if(bundle != null)
        {
            mPendingRequest = bundle.getInt("pendingRequest");
            mExistingAccounts = bundle.getParcelableArray("existingAccounts");
            mSelectedAccountName = bundle.getString("selectedAccountName");
            mSelectedAddNewAccount = bundle.getBoolean("selectedAddAccount", false);
            Parcelable aparcelable[] = bundle.getParcelableArray("accountsList");
            obj = bundle.getIntegerArrayList("visibilityList");
            mAccounts = new LinkedHashMap();
            for(int i = 0; i < aparcelable.length; i++)
                mAccounts.put((Account)aparcelable[i], (Integer)((ArrayList) (obj)).get(i));

        } else
        {
            mPendingRequest = 0;
            mExistingAccounts = null;
            obj = (Account)((Intent) (obj)).getParcelableExtra("selectedAccount");
            if(obj != null)
                mSelectedAccountName = ((Account) (obj)).name;
            mAccounts = getAcceptableAccountChoices(AccountManager.get(this));
        }
        if(Log.isLoggable("AccountChooser", 2))
            Log.v("AccountChooser", (new StringBuilder()).append("selected account name is ").append(mSelectedAccountName).toString());
        mPossiblyVisibleAccounts = new ArrayList(mAccounts.size());
        Iterator iterator = mAccounts.entrySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            if(3 != ((Integer)entry.getValue()).intValue())
                mPossiblyVisibleAccounts.add((Account)entry.getKey());
        } while(true);
        if(mPossiblyVisibleAccounts.isEmpty() && mDisallowAddAccounts)
        {
            requestWindowFeature(1);
            setContentView(0x1090036);
            mDontShowPicker = true;
        }
        if(mDontShowPicker)
        {
            super.onCreate(bundle);
            return;
        }
        boolean flag;
        if(mPendingRequest == 0 && mPossiblyVisibleAccounts.isEmpty())
        {
            setNonLabelThemeAndCallSuperCreate(bundle);
            String as[];
            if(mSetOfRelevantAccountTypes.size() == 1)
            {
                mPendingRequest = 1;
                runAddAccountForAuthenticator((String)mSetOfRelevantAccountTypes.iterator().next());
            } else
            {
                startChooseAccountTypeActivity();
            }
        }
        as = getListOfDisplayableOptions(mPossiblyVisibleAccounts);
        mSelectedItemIndex = getItemIndexToSelect(mPossiblyVisibleAccounts, mSelectedAccountName, mSelectedAddNewAccount);
        super.onCreate(bundle);
        setContentView(0x1090046);
        overrideDescriptionIfSupplied(mDescriptionOverride);
        populateUIAccountList(as);
        mOkButton = (Button)findViewById(0x102001a);
        bundle = mOkButton;
        if(mSelectedItemIndex != -1)
            flag = true;
        else
            flag = false;
        bundle.setEnabled(flag);
    }

    protected void onDestroy()
    {
        if(Log.isLoggable("AccountChooser", 2))
            Log.v("AccountChooser", "ChooseTypeAndAccountActivity.onDestroy()");
        super.onDestroy();
    }

    public void onOkButtonClicked(View view)
    {
        if(mSelectedItemIndex != mPossiblyVisibleAccounts.size()) goto _L2; else goto _L1
_L1:
        startChooseAccountTypeActivity();
_L4:
        return;
_L2:
        if(mSelectedItemIndex != -1)
            onAccountSelected((Account)mPossiblyVisibleAccounts.get(mSelectedItemIndex));
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putInt("pendingRequest", mPendingRequest);
        if(mPendingRequest == 2)
            bundle.putParcelableArray("existingAccounts", mExistingAccounts);
        Parcelable aparcelable[];
        ArrayList arraylist;
        int i;
        if(mSelectedItemIndex != -1)
            if(mSelectedItemIndex == mPossiblyVisibleAccounts.size())
            {
                bundle.putBoolean("selectedAddAccount", true);
            } else
            {
                bundle.putBoolean("selectedAddAccount", false);
                bundle.putString("selectedAccountName", ((Account)mPossiblyVisibleAccounts.get(mSelectedItemIndex)).name);
            }
        aparcelable = new Parcelable[mAccounts.size()];
        arraylist = new ArrayList(mAccounts.size());
        i = 0;
        for(Iterator iterator = mAccounts.entrySet().iterator(); iterator.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            aparcelable[i] = (Parcelable)entry.getKey();
            arraylist.add((Integer)entry.getValue());
            i++;
        }

        bundle.putParcelableArray("accountsList", aparcelable);
        bundle.putIntegerArrayList("visibilityList", arraylist);
    }

    public void run(AccountManagerFuture accountmanagerfuture)
    {
        accountmanagerfuture = (Intent)((Bundle)accountmanagerfuture.getResult()).getParcelable("intent");
        if(accountmanagerfuture != null)
            try
            {
                mPendingRequest = 2;
                mExistingAccounts = AccountManager.get(this).getAccountsForPackage(mCallingPackage, mCallingUid);
                accountmanagerfuture.setFlags(accountmanagerfuture.getFlags() & 0xefffffff);
                startActivityForResult(accountmanagerfuture, 2);
                return;
            }
            // Misplaced declaration of an exception variable
            catch(AccountManagerFuture accountmanagerfuture)
            {
                setResult(0);
                finish();
                return;
            }
            // Misplaced declaration of an exception variable
            catch(AccountManagerFuture accountmanagerfuture) { }
            // Misplaced declaration of an exception variable
            catch(AccountManagerFuture accountmanagerfuture) { }
        accountmanagerfuture = new Bundle();
        accountmanagerfuture.putString("errorMessage", "error communicating with server");
        setResult(-1, (new Intent()).putExtras(accountmanagerfuture));
        finish();
        return;
    }

    protected void runAddAccountForAuthenticator(String s)
    {
        if(Log.isLoggable("AccountChooser", 2))
            Log.v("AccountChooser", (new StringBuilder()).append("runAddAccountForAuthenticator: ").append(s).toString());
        Bundle bundle = getIntent().getBundleExtra("addAccountOptions");
        String as[] = getIntent().getStringArrayExtra("addAccountRequiredFeatures");
        String s1 = getIntent().getStringExtra("authTokenType");
        AccountManager.get(this).addAccount(s, s1, as, bundle, null, this, null);
    }

    public static final String EXTRA_ADD_ACCOUNT_AUTH_TOKEN_TYPE_STRING = "authTokenType";
    public static final String EXTRA_ADD_ACCOUNT_OPTIONS_BUNDLE = "addAccountOptions";
    public static final String EXTRA_ADD_ACCOUNT_REQUIRED_FEATURES_STRING_ARRAY = "addAccountRequiredFeatures";
    public static final String EXTRA_ALLOWABLE_ACCOUNTS_ARRAYLIST = "allowableAccounts";
    public static final String EXTRA_ALLOWABLE_ACCOUNT_TYPES_STRING_ARRAY = "allowableAccountTypes";
    public static final String EXTRA_ALWAYS_PROMPT_FOR_ACCOUNT = "alwaysPromptForAccount";
    public static final String EXTRA_DESCRIPTION_TEXT_OVERRIDE = "descriptionTextOverride";
    public static final String EXTRA_SELECTED_ACCOUNT = "selectedAccount";
    private static final String KEY_INSTANCE_STATE_ACCOUNTS_LIST = "accountsList";
    private static final String KEY_INSTANCE_STATE_EXISTING_ACCOUNTS = "existingAccounts";
    private static final String KEY_INSTANCE_STATE_PENDING_REQUEST = "pendingRequest";
    private static final String KEY_INSTANCE_STATE_SELECTED_ACCOUNT_NAME = "selectedAccountName";
    private static final String KEY_INSTANCE_STATE_SELECTED_ADD_ACCOUNT = "selectedAddAccount";
    private static final String KEY_INSTANCE_STATE_VISIBILITY_LIST = "visibilityList";
    public static final int REQUEST_ADD_ACCOUNT = 2;
    public static final int REQUEST_CHOOSE_TYPE = 1;
    public static final int REQUEST_NULL = 0;
    private static final int SELECTED_ITEM_NONE = -1;
    private static final String TAG = "AccountChooser";
    private LinkedHashMap mAccounts;
    private String mCallingPackage;
    private int mCallingUid;
    private String mDescriptionOverride;
    private boolean mDisallowAddAccounts;
    private boolean mDontShowPicker;
    private Parcelable mExistingAccounts[];
    private Button mOkButton;
    private int mPendingRequest;
    private ArrayList mPossiblyVisibleAccounts;
    private String mSelectedAccountName;
    private boolean mSelectedAddNewAccount;
    private int mSelectedItemIndex;
    private Set mSetOfAllowableAccounts;
    private Set mSetOfRelevantAccountTypes;
}
