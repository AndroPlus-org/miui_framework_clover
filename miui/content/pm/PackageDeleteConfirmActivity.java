// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.content.pm;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.*;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// Referenced classes of package miui.content.pm:
//            IPackageDeleteConfirmObserver

public class PackageDeleteConfirmActivity extends Activity
    implements android.view.View.OnClickListener
{

    static int _2D_get0(PackageDeleteConfirmActivity packagedeleteconfirmactivity)
    {
        return packagedeleteconfirmactivity.mAutoNextStepTime;
    }

    static int _2D_get1(PackageDeleteConfirmActivity packagedeleteconfirmactivity)
    {
        return packagedeleteconfirmactivity.mCurrentStep;
    }

    static Button _2D_get2(PackageDeleteConfirmActivity packagedeleteconfirmactivity)
    {
        return packagedeleteconfirmactivity.mDeleteButton;
    }

    static Handler _2D_get3(PackageDeleteConfirmActivity packagedeleteconfirmactivity)
    {
        return packagedeleteconfirmactivity.mHandler;
    }

    static int _2D_set0(PackageDeleteConfirmActivity packagedeleteconfirmactivity, int i)
    {
        packagedeleteconfirmactivity.mAutoNextStepTime = i;
        return i;
    }

    public PackageDeleteConfirmActivity()
    {
        mCurrentStep = 1;
        mAutoNextStepTime = 5;
        delete = false;
        mHandler = new Handler() {

            public void handleMessage(Message message)
            {
                message = PackageDeleteConfirmActivity.this;
                PackageDeleteConfirmActivity._2D_set0(message, PackageDeleteConfirmActivity._2D_get0(message) - 1);
                if(PackageDeleteConfirmActivity._2D_get1(PackageDeleteConfirmActivity.this) == 3 && PackageDeleteConfirmActivity._2D_get0(PackageDeleteConfirmActivity.this) == 0)
                {
                    PackageDeleteConfirmActivity._2D_get2(PackageDeleteConfirmActivity.this).setText(0x110800d1);
                    PackageDeleteConfirmActivity._2D_get2(PackageDeleteConfirmActivity.this).setEnabled(true);
                } else
                if(PackageDeleteConfirmActivity._2D_get0(PackageDeleteConfirmActivity.this) == 0)
                {
                    PackageDeleteConfirmActivity._2D_get2(PackageDeleteConfirmActivity.this).setText(0x110800d3);
                    PackageDeleteConfirmActivity._2D_get2(PackageDeleteConfirmActivity.this).setEnabled(true);
                } else
                {
                    if(PackageDeleteConfirmActivity._2D_get1(PackageDeleteConfirmActivity.this) == 3)
                        PackageDeleteConfirmActivity._2D_get2(PackageDeleteConfirmActivity.this).setText(getString(0x110800d4, new Object[] {
                            Integer.valueOf(PackageDeleteConfirmActivity._2D_get0(PackageDeleteConfirmActivity.this))
                        }));
                    else
                        PackageDeleteConfirmActivity._2D_get2(PackageDeleteConfirmActivity.this).setText(getString(0x110800d2, new Object[] {
                            Integer.valueOf(PackageDeleteConfirmActivity._2D_get0(PackageDeleteConfirmActivity.this))
                        }));
                    PackageDeleteConfirmActivity._2D_get3(PackageDeleteConfirmActivity.this).removeMessages(100);
                    PackageDeleteConfirmActivity._2D_get3(PackageDeleteConfirmActivity.this).sendEmptyMessageDelayed(100, 1000L);
                }
            }

            final PackageDeleteConfirmActivity this$0;

            
            {
                this$0 = PackageDeleteConfirmActivity.this;
                super();
            }
        }
;
    }

    private String getWarningInfo(int i, CharSequence charsequence)
    {
        switch(i)
        {
        default:
            return null;

        case 1: // '\001'
            return getString(0x110800d5, new Object[] {
                charsequence
            });

        case 2: // '\002'
            return getString(0x110800d6, new Object[] {
                charsequence
            });

        case 3: // '\003'
            return getString(0x110800d7, new Object[] {
                charsequence
            });
        }
    }

    private CharSequence loadAppLabel()
    {
        Object obj = getPackageManager();
        try
        {
            obj = ((PackageManager) (obj)).getApplicationInfo(mPkgName, 0).loadLabel(((PackageManager) (obj)));
        }
        catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            return mPkgName;
        }
        return ((CharSequence) (obj));
    }

    public void onBackPressed()
    {
    }

    public void onClick(View view)
    {
        view.getId();
        JVM INSTR tableswitch 285999126 285999127: default 28
    //                   285999126 29
    //                   285999127 50;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        mHandler.removeMessages(100);
        delete = false;
        finish();
        continue; /* Loop/switch isn't completed */
_L3:
        if(mCurrentStep == 3)
        {
            mHandler.removeMessages(100);
            delete = true;
            finish();
        } else
        {
            mCurrentStep = mCurrentStep + 1;
            mAutoNextStepTime = 5;
            mWarningInfoView.setText(getWarningInfo(mCurrentStep, mAppLabel));
            if(mCurrentStep == 3)
                mDeleteButton.setText(getString(0x110800d4, new Object[] {
                    Integer.valueOf(mAutoNextStepTime)
                }));
            else
                mDeleteButton.setText(getString(0x110800d2, new Object[] {
                    Integer.valueOf(mAutoNextStepTime)
                }));
            mDeleteButton.setEnabled(false);
            mHandler.removeMessages(100);
            mHandler.sendEmptyMessageDelayed(100, 1000L);
        }
        if(true) goto _L1; else goto _L4
_L4:
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x11030001);
        bundle = getIntent();
        try
        {
            mPkgName = bundle.getStringExtra("extra_pkgname");
        }
        catch(Exception exception)
        {
            mPkgName = null;
        }
        if(TextUtils.isEmpty(mPkgName))
        {
            finish();
            return;
        } else
        {
            mObs = IPackageDeleteConfirmObserver.Stub.asInterface((IBinder)bundle.getExtra("observer"));
            mAppLabel = loadAppLabel();
            mWarningInfoView = (TextView)findViewById(0x110c0015);
            mCancelButton = (Button)findViewById(0x110c0016);
            mCancelButton.setOnClickListener(this);
            mDeleteButton = (Button)findViewById(0x110c0017);
            mDeleteButton.setOnClickListener(this);
            mWarningInfoView.setText(getWarningInfo(mCurrentStep, mAppLabel));
            mDeleteButton.setText(getString(0x110800d2, new Object[] {
                Integer.valueOf(mAutoNextStepTime)
            }));
            mDeleteButton.setEnabled(false);
            mHandler.sendEmptyMessageDelayed(100, 1000L);
            return;
        }
    }

    public void onStop()
    {
        mHandler.removeMessages(100);
        try
        {
            mObs.onConfirm(delete);
        }
        catch(RemoteException remoteexception)
        {
            remoteexception.printStackTrace();
        }
        super.onStop();
        finish();
    }

    private static final int DELETE_MSG_NEXT_STEP_INTERVAL = 1000;
    private static final int DELETE_MSG_NEXT_STEP_WHAT = 100;
    public static final String EXTRA_PKGNAME = "extra_pkgname";
    private static final int MAX_STEPS = 3;
    private boolean delete;
    private CharSequence mAppLabel;
    private int mAutoNextStepTime;
    private Button mCancelButton;
    private int mCurrentStep;
    private Button mDeleteButton;
    private Handler mHandler;
    private IPackageDeleteConfirmObserver mObs;
    private String mPkgName;
    private TextView mWarningInfoView;
}
