// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.app.*;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class HeavyWeightSwitcherActivity extends Activity
{

    public HeavyWeightSwitcherActivity()
    {
        mSwitchOldListener = new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                try
                {
                    ActivityManager.getService().moveTaskToFront(mCurTask, 0, null);
                }
                // Misplaced declaration of an exception variable
                catch(View view) { }
                finish();
            }

            final HeavyWeightSwitcherActivity this$0;

            
            {
                this$0 = HeavyWeightSwitcherActivity.this;
                super();
            }
        }
;
        mSwitchNewListener = new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                try
                {
                    ActivityManager.getService().finishHeavyWeightApp();
                }
                // Misplaced declaration of an exception variable
                catch(View view) { }
                if(!mHasResult) goto _L2; else goto _L1
_L1:
                startIntentSenderForResult(mStartIntent, -1, null, 0x2000000, 0x2000000, 0);
_L4:
                finish();
                return;
_L2:
                try
                {
                    startIntentSenderForResult(mStartIntent, -1, null, 0, 0, 0);
                }
                // Misplaced declaration of an exception variable
                catch(View view)
                {
                    Log.w("HeavyWeightSwitcherActivity", "Failure starting", view);
                }
                if(true) goto _L4; else goto _L3
_L3:
            }

            final HeavyWeightSwitcherActivity this$0;

            
            {
                this$0 = HeavyWeightSwitcherActivity.this;
                super();
            }
        }
;
        mCancelListener = new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                finish();
            }

            final HeavyWeightSwitcherActivity this$0;

            
            {
                this$0 = HeavyWeightSwitcherActivity.this;
                super();
            }
        }
;
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(3);
        mStartIntent = (IntentSender)getIntent().getParcelableExtra("intent");
        mHasResult = getIntent().getBooleanExtra("has_result", false);
        mCurApp = getIntent().getStringExtra("cur_app");
        mCurTask = getIntent().getIntExtra("cur_task", 0);
        mNewApp = getIntent().getStringExtra("new_app");
        setContentView(0x1090069);
        setIconAndText(0x1020349, 0x1020347, 0x1020348, mCurApp, 0x10403fb, 0x10403fc);
        setIconAndText(0x1020320, 0x102031e, 0x102031f, mNewApp, 0x10403c7, 0x10403c8);
        findViewById(0x102042b).setOnClickListener(mSwitchOldListener);
        findViewById(0x102042a).setOnClickListener(mSwitchNewListener);
        findViewById(0x10201e8).setOnClickListener(mCancelListener);
        bundle = new TypedValue();
        getTheme().resolveAttribute(0x1010355, bundle, true);
        getWindow().setFeatureDrawableResource(3, ((TypedValue) (bundle)).resourceId);
    }

    void setDrawable(int i, Drawable drawable)
    {
        if(drawable != null)
            ((ImageView)findViewById(i)).setImageDrawable(drawable);
    }

    void setIconAndText(int i, int j, int k, String s, int l, int i1)
    {
        String s1;
        Object obj;
        Object obj1;
        String s2;
        s1 = "";
        obj = null;
        obj1 = obj;
        s2 = s1;
        if(mCurApp == null)
            break MISSING_BLOCK_LABEL_72;
        s2 = s1;
        obj1 = getPackageManager().getApplicationInfo(s, 0);
        s2 = s1;
        s = ((ApplicationInfo) (obj1)).loadLabel(getPackageManager());
        s2 = s;
        obj1 = ((ApplicationInfo) (obj1)).loadIcon(getPackageManager());
        s2 = s;
_L2:
        setDrawable(i, ((Drawable) (obj1)));
        setText(j, getString(l, new Object[] {
            s2
        }));
        setText(k, getText(i1));
        return;
        s;
        obj1 = obj;
        if(true) goto _L2; else goto _L1
_L1:
    }

    void setText(int i, CharSequence charsequence)
    {
        ((TextView)findViewById(i)).setText(charsequence);
    }

    public static final String KEY_CUR_APP = "cur_app";
    public static final String KEY_CUR_TASK = "cur_task";
    public static final String KEY_HAS_RESULT = "has_result";
    public static final String KEY_INTENT = "intent";
    public static final String KEY_NEW_APP = "new_app";
    private android.view.View.OnClickListener mCancelListener;
    String mCurApp;
    int mCurTask;
    boolean mHasResult;
    String mNewApp;
    IntentSender mStartIntent;
    private android.view.View.OnClickListener mSwitchNewListener;
    private android.view.View.OnClickListener mSwitchOldListener;
}
