// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.accounts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;
import java.util.*;

// Referenced classes of package android.accounts:
//            AccountManager, AuthenticatorDescription

public class ChooseAccountTypeActivity extends Activity
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
            ((ViewHolder) (viewgroup)).text.setText(((AuthInfo)mInfos.get(i)).name);
            ((ViewHolder) (viewgroup)).icon.setImageDrawable(((AuthInfo)mInfos.get(i)).drawable);
            return view;
        }

        private ArrayList mInfos;
        private LayoutInflater mLayoutInflater;

        public AccountArrayAdapter(Context context, int i, ArrayList arraylist)
        {
            super(context, i, arraylist);
            mInfos = arraylist;
            mLayoutInflater = (LayoutInflater)context.getSystemService("layout_inflater");
        }
    }

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


    static ArrayList _2D_get0(ChooseAccountTypeActivity chooseaccounttypeactivity)
    {
        return chooseaccounttypeactivity.mAuthenticatorInfosToDisplay;
    }

    static void _2D_wrap0(ChooseAccountTypeActivity chooseaccounttypeactivity, String s)
    {
        chooseaccounttypeactivity.setResultAndFinish(s);
    }

    public ChooseAccountTypeActivity()
    {
        mTypeToAuthenticatorInfo = new HashMap();
    }

    private void buildTypeToAuthDescriptionMap()
    {
        int i;
        AuthenticatorDescription aauthenticatordescription[];
        int j;
        i = 0;
        aauthenticatordescription = AccountManager.get(this).getAuthenticatorTypes();
        j = aauthenticatordescription.length;
_L2:
        AuthenticatorDescription authenticatordescription;
        String s;
        Object obj1;
        Object obj2;
        CharSequence charsequence;
        Drawable drawable;
        Drawable drawable1;
        Object obj3;
        Drawable drawable2;
        Object obj4;
        if(i >= j)
            break; /* Loop/switch isn't completed */
        authenticatordescription = aauthenticatordescription[i];
        s = null;
        obj1 = null;
        obj2 = null;
        charsequence = null;
        drawable = null;
        drawable1 = drawable;
        obj3 = s;
        drawable2 = charsequence;
        obj4 = obj1;
        Context context = createPackageContext(authenticatordescription.packageName, 0);
        drawable1 = drawable;
        obj3 = s;
        drawable2 = charsequence;
        obj4 = obj1;
        drawable = context.getDrawable(authenticatordescription.iconId);
        drawable1 = drawable;
        obj3 = s;
        drawable2 = drawable;
        obj4 = obj1;
        charsequence = context.getResources().getText(authenticatordescription.labelId);
        obj4 = obj2;
        if(charsequence == null)
            break MISSING_BLOCK_LABEL_163;
        drawable1 = drawable;
        obj3 = s;
        drawable2 = drawable;
        obj4 = obj1;
        s = charsequence.toString();
        obj4 = s;
        drawable1 = drawable;
        obj3 = obj4;
        drawable2 = drawable;
        Object obj;
        try
        {
            obj = charsequence.toString();
        }
        catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            drawable = drawable2;
            obj = obj4;
            if(Log.isLoggable("AccountChooser", 5))
            {
                Log.w("AccountChooser", (new StringBuilder()).append("No icon name for account type ").append(authenticatordescription.type).toString());
                drawable = drawable2;
                obj = obj4;
            }
        }
        // Misplaced declaration of an exception variable
        catch(Object obj4)
        {
            drawable = drawable1;
            obj = obj3;
            if(Log.isLoggable("AccountChooser", 5))
            {
                Log.w("AccountChooser", (new StringBuilder()).append("No icon resource for account type ").append(authenticatordescription.type).toString());
                drawable = drawable1;
                obj = obj3;
            }
        }
        obj4 = new AuthInfo(authenticatordescription, ((String) (obj)), drawable);
        mTypeToAuthenticatorInfo.put(authenticatordescription.type, obj4);
        i++;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void setResultAndFinish(String s)
    {
        Bundle bundle = new Bundle();
        bundle.putString("accountType", s);
        setResult(-1, (new Intent()).putExtras(bundle));
        if(Log.isLoggable("AccountChooser", 2))
            Log.v("AccountChooser", (new StringBuilder()).append("ChooseAccountTypeActivity.setResultAndFinish: selected account type ").append(s).toString());
        finish();
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if(Log.isLoggable("AccountChooser", 2))
            Log.v("AccountChooser", (new StringBuilder()).append("ChooseAccountTypeActivity.onCreate(savedInstanceState=").append(bundle).append(")").toString());
        bundle = null;
        String as[] = getIntent().getStringArrayExtra("allowableAccountTypes");
        if(as != null)
        {
            HashSet hashset = new HashSet(as.length);
            int i = as.length;
            int j = 0;
            do
            {
                bundle = hashset;
                if(j >= i)
                    break;
                hashset.add(as[j]);
                j++;
            } while(true);
        }
        buildTypeToAuthDescriptionMap();
        mAuthenticatorInfosToDisplay = new ArrayList(mTypeToAuthenticatorInfo.size());
        Iterator iterator = mTypeToAuthenticatorInfo.entrySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Object obj = (java.util.Map.Entry)iterator.next();
            String s = (String)((java.util.Map.Entry) (obj)).getKey();
            obj = (AuthInfo)((java.util.Map.Entry) (obj)).getValue();
            if(bundle == null || !(bundle.contains(s) ^ true))
                mAuthenticatorInfosToDisplay.add(obj);
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
        } else
        {
            setContentView(0x1090045);
            bundle = (ListView)findViewById(0x102000a);
            bundle.setAdapter(new AccountArrayAdapter(this, 0x1090003, mAuthenticatorInfosToDisplay));
            bundle.setChoiceMode(0);
            bundle.setTextFilterEnabled(false);
            bundle.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView adapterview, View view, int k, long l)
                {
                    ChooseAccountTypeActivity._2D_wrap0(ChooseAccountTypeActivity.this, ((AuthInfo)ChooseAccountTypeActivity._2D_get0(ChooseAccountTypeActivity.this).get(k)).desc.type);
                }

                final ChooseAccountTypeActivity this$0;

            
            {
                this$0 = ChooseAccountTypeActivity.this;
                super();
            }
            }
);
            return;
        }
    }

    private static final String TAG = "AccountChooser";
    private ArrayList mAuthenticatorInfosToDisplay;
    private HashMap mTypeToAuthenticatorInfo;
}
