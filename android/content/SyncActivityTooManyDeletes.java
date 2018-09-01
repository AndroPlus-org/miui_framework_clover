// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.accounts.Account;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

// Referenced classes of package android.content:
//            ContentResolver, Intent

public class SyncActivityTooManyDeletes extends Activity
    implements android.widget.AdapterView.OnItemClickListener
{

    public SyncActivityTooManyDeletes()
    {
    }

    private void startSyncReallyDelete()
    {
        Bundle bundle = new Bundle();
        bundle.putBoolean("deletions_override", true);
        bundle.putBoolean("force", true);
        bundle.putBoolean("expedited", true);
        bundle.putBoolean("upload", true);
        ContentResolver.requestSync(mAccount, mAuthority, bundle);
    }

    private void startSyncUndoDeletes()
    {
        Bundle bundle = new Bundle();
        bundle.putBoolean("discard_deletions", true);
        bundle.putBoolean("force", true);
        bundle.putBoolean("expedited", true);
        bundle.putBoolean("upload", true);
        ContentResolver.requestSync(mAccount, mAuthority, bundle);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        bundle = getIntent().getExtras();
        if(bundle == null)
        {
            finish();
            return;
        } else
        {
            mNumDeletes = bundle.getLong("numDeletes");
            mAccount = (Account)bundle.getParcelable("account");
            mAuthority = bundle.getString("authority");
            mProvider = bundle.getString("provider");
            Object obj = new ArrayAdapter(this, 0x1090003, 0x1020014, new CharSequence[] {
                getResources().getText(0x1040628), getResources().getText(0x104062b), getResources().getText(0x1040627)
            });
            bundle = new ListView(this);
            bundle.setAdapter(((android.widget.ListAdapter) (obj)));
            bundle.setItemsCanFocus(true);
            bundle.setOnItemClickListener(this);
            TextView textview = new TextView(this);
            textview.setText(String.format(getResources().getText(0x104062a).toString(), new Object[] {
                Long.valueOf(mNumDeletes), mProvider, mAccount.name
            }));
            LinearLayout linearlayout = new LinearLayout(this);
            linearlayout.setOrientation(1);
            obj = new android.widget.LinearLayout.LayoutParams(-1, -2, 0.0F);
            linearlayout.addView(textview, ((android.view.ViewGroup.LayoutParams) (obj)));
            linearlayout.addView(bundle, ((android.view.ViewGroup.LayoutParams) (obj)));
            setContentView(linearlayout);
            return;
        }
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        if(i != 0) goto _L2; else goto _L1
_L1:
        startSyncReallyDelete();
_L4:
        finish();
        return;
_L2:
        if(i == 1)
            startSyncUndoDeletes();
        if(true) goto _L4; else goto _L3
_L3:
    }

    private Account mAccount;
    private String mAuthority;
    private long mNumDeletes;
    private String mProvider;
}
