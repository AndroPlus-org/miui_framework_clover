// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.accounts;

import android.accounts.AccountManager;
import android.accounts.AuthenticatorDescription;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.*;
import android.util.Log;
import java.util.*;
import miui.content.res.IconCustomizer;

public class MiuiChooseAccountTypeActivity extends PreferenceActivity
{
    private static class AuthInfo
    {

        final AuthenticatorDescription desc;
        final Drawable drawable;
        final String name;

        AuthInfo(AuthenticatorDescription authenticatordescription, String s, Drawable drawable1)
        {
            desc = authenticatordescription;
            name = s;
            drawable = drawable1;
        }
    }

    private static class ProviderPreference extends Preference
    {

        public String getAccountType()
        {
            return mAccountType;
        }

        private String mAccountType;

        public ProviderPreference(Context context, String s, Drawable drawable, CharSequence charsequence)
        {
            super(context);
            mAccountType = s;
            setIcon(drawable);
            setPersistent(false);
            setTitle(charsequence);
        }
    }


    public MiuiChooseAccountTypeActivity()
    {
        mTypeToAuthenticatorInfo = new HashMap();
    }

    private boolean hasXiaomiAccount()
    {
        boolean flag = true;
        AccountManager accountmanager = (AccountManager)getSystemService("account");
        if(accountmanager.getAccountsByType("com.xiaomi").length > 0)
            return true;
        if(accountmanager.getAccountsByType("com.xiaomi.unactivated").length <= 0)
            flag = false;
        return flag;
    }

    private void setResultAndFinish(String s)
    {
        Bundle bundle = new Bundle();
        bundle.putString("accountType", s);
        setResult(-1, (new Intent()).putExtras(bundle));
        if(Log.isLoggable("MiuiChooseAccountType", 2))
            Log.v("MiuiChooseAccountType", (new StringBuilder()).append("ChooseAccountTypeActivity.setResultAndFinish: selected account type ").append(s).toString());
        finish();
    }

    protected void buildTypeToAuthDescriptionMap()
    {
        boolean flag;
        AuthenticatorDescription aauthenticatordescription[];
        int i;
        int j;
        flag = hasXiaomiAccount();
        aauthenticatordescription = AccountManager.get(this).getAuthenticatorTypes();
        i = aauthenticatordescription.length;
        j = 0;
_L2:
        AuthenticatorDescription authenticatordescription;
        Object obj;
        Object obj1;
        android.graphics.drawable.BitmapDrawable bitmapdrawable;
        Object obj2;
        Object obj3;
        if(j >= i)
            break; /* Loop/switch isn't completed */
        authenticatordescription = aauthenticatordescription[j];
        obj = null;
        obj1 = null;
        bitmapdrawable = null;
        obj2 = bitmapdrawable;
        obj3 = obj1;
        Object obj4 = createPackageContext(authenticatordescription.packageName, 0);
        obj2 = bitmapdrawable;
        obj3 = obj1;
        bitmapdrawable = IconCustomizer.generateIconStyleDrawable(((Context) (obj4)).getResources().getDrawable(authenticatordescription.iconId));
        obj2 = bitmapdrawable;
        obj3 = bitmapdrawable;
        CharSequence charsequence = ((Context) (obj4)).getResources().getText(authenticatordescription.labelId);
        obj1 = bitmapdrawable;
        obj4 = obj;
        if(charsequence == null)
            break MISSING_BLOCK_LABEL_143;
        obj2 = bitmapdrawable;
        obj3 = bitmapdrawable;
        obj4 = charsequence.toString();
        obj1 = bitmapdrawable;
_L3:
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        boolean flag1;
        if(!"com.xiaomi.unactivated".equals(authenticatordescription.type))
        {
            if("com.xiaomi".equals(authenticatordescription.type))
                flag1 = flag;
            else
                flag1 = false;
        } else
        {
            flag1 = true;
        }
        if(!flag1)
        {
            obj2 = new AuthInfo(authenticatordescription, ((String) (obj4)), ((Drawable) (obj1)));
            mTypeToAuthenticatorInfo.put(authenticatordescription.type, obj2);
        }
        j++;
        if(true) goto _L2; else goto _L1
        obj3;
        obj1 = obj2;
        obj4 = obj;
        if(Log.isLoggable("MiuiChooseAccountType", 5))
        {
            Log.w("MiuiChooseAccountType", (new StringBuilder()).append("No icon resource for account type ").append(authenticatordescription.type).toString());
            obj1 = obj2;
            obj4 = obj;
        }
          goto _L3
        namenotfoundexception;
        obj1 = obj3;
        obj4 = obj;
        if(Log.isLoggable("MiuiChooseAccountType", 5))
        {
            Log.w("MiuiChooseAccountType", (new StringBuilder()).append("No icon name for account type ").append(authenticatordescription.type).toString());
            obj1 = obj3;
            obj4 = obj;
        }
          goto _L3
_L1:
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        addPreferencesFromResource(0x11050000);
        PreferenceCategory preferencecategory = (PreferenceCategory)findPreference("pref_add_account");
        bundle = null;
        String as[] = getIntent().getStringArrayExtra("allowableAccountTypes");
        if(as != null)
        {
            HashSet hashset = new HashSet(as.length);
            int i = 0;
            int j = as.length;
            do
            {
                bundle = hashset;
                if(i >= j)
                    break;
                hashset.add(as[i]);
                i++;
            } while(true);
        }
        buildTypeToAuthDescriptionMap();
        mAuthenticatorInfosToDisplay = new ArrayList(mTypeToAuthenticatorInfo.size());
        Iterator iterator = mTypeToAuthenticatorInfo.entrySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Object obj1 = (java.util.Map.Entry)iterator.next();
            String s = (String)((java.util.Map.Entry) (obj1)).getKey();
            obj1 = (AuthInfo)((java.util.Map.Entry) (obj1)).getValue();
            if(bundle == null || !(bundle.contains(s) ^ true))
                mAuthenticatorInfosToDisplay.add(obj1);
        } while(true);
        if(mAuthenticatorInfosToDisplay.isEmpty())
        {
            bundle = new Bundle();
            bundle.putString("errorMessage", "no allowable account types");
            setResult(-1, (new Intent()).putExtras(bundle));
            finish();
            return;
        }
        if(mAuthenticatorInfosToDisplay.size() == 1)
        {
            setResultAndFinish(((AuthInfo)mAuthenticatorInfosToDisplay.get(0)).desc.type);
            return;
        }
        Object obj;
        for(bundle = mAuthenticatorInfosToDisplay.iterator(); bundle.hasNext(); preferencecategory.addPreference(((Preference) (obj))))
        {
            obj = (AuthInfo)bundle.next();
            obj = new ProviderPreference(this, ((AuthInfo) (obj)).desc.type, ((AuthInfo) (obj)).drawable, ((AuthInfo) (obj)).name);
            ((ProviderPreference) (obj)).setPersistent(false);
        }

    }

    public boolean onPreferenceTreeClick(PreferenceScreen preferencescreen, Preference preference)
    {
        if(preference instanceof ProviderPreference)
        {
            preferencescreen = (ProviderPreference)preference;
            if(Log.isLoggable("MiuiChooseAccountType", 2))
                Log.v("MiuiChooseAccountType", (new StringBuilder()).append("Attempting to add account of type ").append(preferencescreen.getAccountType()).toString());
            setResultAndFinish(((ProviderPreference)preference).getAccountType());
            return true;
        } else
        {
            return super.onPreferenceTreeClick(preferencescreen, preference);
        }
    }

    private static final String TAG = "MiuiChooseAccountType";
    private ArrayList mAuthenticatorInfosToDisplay;
    private HashMap mTypeToAuthenticatorInfo;
}
