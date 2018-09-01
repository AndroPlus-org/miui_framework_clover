// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.securityspace;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.*;
import android.content.pm.*;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Binder;
import android.os.UserHandle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;

// Referenced classes of package miui.securityspace:
//            CrossUserUtilsCompat, CrossUserUtils, XSpaceUserHandle, XSpaceIntentCompat

public class XSpaceResolverActivityHelper
{
    private static class ResolverActivityRunner
        implements Runnable
    {

        static Activity _2D_get0(ResolverActivityRunner resolveractivityrunner)
        {
            return resolveractivityrunner.mActivity;
        }

        static String _2D_get1(ResolverActivityRunner resolveractivityrunner)
        {
            return resolveractivityrunner.mAimClassName;
        }

        static int _2D_get2(ResolverActivityRunner resolveractivityrunner)
        {
            return resolveractivityrunner.mAimUserId;
        }

        static CheckBox _2D_get3(ResolverActivityRunner resolveractivityrunner)
        {
            return resolveractivityrunner.mAlwaysOption;
        }

        static int _2D_get4(ResolverActivityRunner resolveractivityrunner)
        {
            return resolveractivityrunner.mAskType;
        }

        static Context _2D_get5(ResolverActivityRunner resolveractivityrunner)
        {
            return resolveractivityrunner.mContext;
        }

        static int _2D_set0(ResolverActivityRunner resolveractivityrunner, int i)
        {
            resolveractivityrunner.mAimUserId = i;
            return i;
        }

        static int _2D_set1(ResolverActivityRunner resolveractivityrunner, int i)
        {
            resolveractivityrunner.mAskType = i;
            return i;
        }

        static void _2D_wrap0(ResolverActivityRunner resolveractivityrunner, int i)
        {
            resolveractivityrunner.forward(i);
        }

        private void forward(int i)
        {
            mOriginalIntent.putExtra("android.intent.extra.picked_user_id", i);
            CrossUserUtilsCompat.startActivityAsCaller(mActivity, mOriginalIntent, null, false, i);
            mActivity.finish();
        }

        private Drawable getAppIcon()
        {
            return CrossUserUtils.getOriginalAppIcon(mContext, mAimPackageName);
        }

        private void loadItem(int i, Drawable drawable, CharSequence charsequence)
        {
            LinearLayout linearlayout = (LinearLayout)mRootView.findViewById(i);
            ImageView imageview = (ImageView)linearlayout.findViewById(0x1020006);
            Object obj = imageview.getLayoutParams();
            int j = mIconSize;
            obj.height = j;
            obj.width = j;
            TextView textview = (TextView)linearlayout.findViewById(0x1020014);
            textview.setMinLines(1);
            obj = drawable;
            if(i == 0x110c0034)
                obj = XSpaceUserHandle.getXSpaceIcon(mContext, drawable);
            linearlayout.findViewById(0x1020015).setVisibility(8);
            linearlayout.setOnClickListener(mOnClickListener);
            imageview.setImageDrawable(((Drawable) (obj)));
            textview.setText(charsequence);
        }

        private static void startXSpaceServiceAsUser(Context context, int i, int j)
        {
            Intent intent = new Intent();
            intent.putExtra("param_intent_key_has_extra", "param_intent_value_has_extra");
            intent.putExtra("param_intent_key_default_asktype", i);
            intent.setComponent(ComponentName.unflattenFromString("com.miui.securitycore/com.miui.xspace.service.XSpaceService"));
            context.startServiceAsUser(intent, new UserHandle(j));
        }

        public CharSequence getAppLabel()
        {
            Object obj;
            Object obj1;
            obj = mContext.getPackageManager();
            obj1 = ((PackageManager) (obj)).getPackageInfo(mAimPackageName, 0);
            if(obj1 == null)
                break MISSING_BLOCK_LABEL_48;
            obj1 = ((PackageInfo) (obj1)).applicationInfo;
            if(obj1 == null)
                break MISSING_BLOCK_LABEL_48;
            obj = ((ApplicationInfo) (obj1)).loadLabel(((PackageManager) (obj)));
            if(obj != null)
                return ((CharSequence) (obj));
            break MISSING_BLOCK_LABEL_48;
            android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
            namenotfoundexception;
            namenotfoundexception.printStackTrace();
            return mAimPackageName;
        }

        public boolean needShowDefaultSettingGuide()
        {
            boolean flag;
            if(android.provider.MiuiSettings.XSpace.getGuideNotificationTimes(mContext, "key_default_guide_times") < 2)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public void run()
        {
            mOriginalIntent = (Intent)mIntent.getParcelableExtra("android.intent.extra.xspace_resolver_activity_original_intent");
            if(mOriginalIntent != null && mOriginalIntent.getComponent() != null && mOriginalIntent.getComponent().getClassName() != null)
            {
                mAimClassName = mOriginalIntent.getComponent().getClassName();
                mAskType = android.provider.MiuiSettings.XSpace.getAskType(mContext, mAimClassName);
                if(mAskType != 0)
                {
                    XSpaceIntentCompat.prepareToLeaveUser(mOriginalIntent, Binder.getCallingUserHandle().getIdentifier());
                    int i;
                    Drawable drawable;
                    CharSequence charsequence;
                    if(mAskType == 1)
                        i = 0;
                    else
                        i = 999;
                    mAimUserId = i;
                    if(needShowDefaultSettingGuide())
                        startXSpaceServiceAsUser(mContext, mAskType, 0);
                    Log.i("XSpaceResolverActivity", (new StringBuilder()).append("Direct to ").append(mAimUserId).toString());
                    forward(mAimUserId);
                }
            }
            mAlertParams.mMessage = null;
            mAlertParams.mTitle = mActivity.getString(0x110800cd);
            mRootView = mActivity.getLayoutInflater().inflate(0x11030014, null);
            mAlertParams.mView = mRootView;
            mAlertParams.mNegativeButtonText = mActivity.getResources().getString(0x1040000);
            mAlertParams.mNegativeButtonListener = new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    ResolverActivityRunner._2D_get0(ResolverActivityRunner.this).finish();
                }

                final ResolverActivityRunner this$1;

            
            {
                this$1 = ResolverActivityRunner.this;
                super();
            }
            }
;
            mAimPackageName = mIntent.getStringExtra("android.intent.extra.xspace_resolver_activity_aim_package");
            mIconSize = ((ActivityManager)mContext.getSystemService("activity")).getLauncherLargeIconSize();
            if(mOriginalIntent != null && mAimPackageName != null)
            {
                drawable = getAppIcon();
                charsequence = getAppLabel();
                loadItem(0x110c0033, drawable, charsequence);
                loadItem(0x110c0034, drawable, charsequence);
            }
            mAlwaysOption = (CheckBox)mRootView.findViewById(0x110c0032);
            if(mAlwaysOption != null && android.provider.MiuiSettings.XSpace.sSupportDefaultSettingApps.contains(mAimClassName))
            {
                mAlwaysOption.setVisibility(0);
                mAlwaysOption.setChecked(false);
            }
        }

        private Activity mActivity;
        private String mAimClassName;
        private String mAimPackageName;
        private int mAimUserId;
        private com.android.internal.app.AlertController.AlertParams mAlertParams;
        private CheckBox mAlwaysOption;
        private int mAskType;
        private Context mContext;
        private int mIconSize;
        private Intent mIntent;
        private android.view.View.OnClickListener mOnClickListener;
        private Intent mOriginalIntent;
        private View mRootView;

        public ResolverActivityRunner(Activity activity, Intent intent, com.android.internal.app.AlertController.AlertParams alertparams)
        {
            mAimClassName = "";
            mAimUserId = -1;
            mAskType = 0;
            mOnClickListener = new _cls1();
            mActivity = activity;
            mContext = mActivity.getApplicationContext();
            mIntent = intent;
            mAlertParams = alertparams;
        }
    }


    public XSpaceResolverActivityHelper()
    {
    }

    public static boolean checkAndResolve(Activity activity, Intent intent, com.android.internal.app.AlertController.AlertParams alertparams)
    {
        if(intent == null || "miui.intent.action.ACTION_XSPACE_RESOLVER_ACTIVITY".equals(intent.getAction()) ^ true)
        {
            return false;
        } else
        {
            (new ResolverActivityRunner(activity, intent, alertparams)).run();
            return true;
        }
    }

    private static final String TAG = "XSpaceResolverActivity";
    private static final String XSPACE_SERVICE_COMPONENT = "com.miui.securitycore/com.miui.xspace.service.XSpaceService";

    // Unreferenced inner class miui/securityspace/XSpaceResolverActivityHelper$ResolverActivityRunner$1

/* anonymous class */
    class ResolverActivityRunner._cls1
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            ResolverActivityRunner resolveractivityrunner = ResolverActivityRunner.this;
            int i;
            if(view.getId() == 0x110c0033)
                i = 0;
            else
                i = 999;
            ResolverActivityRunner._2D_set0(resolveractivityrunner, i);
            if(ResolverActivityRunner._2D_get3(ResolverActivityRunner.this) != null && ResolverActivityRunner._2D_get3(ResolverActivityRunner.this).isChecked())
            {
                ResolverActivityRunner resolveractivityrunner1 = ResolverActivityRunner.this;
                if(view.getId() == 0x110c0033)
                    i = 1;
                else
                    i = 2;
                ResolverActivityRunner._2D_set1(resolveractivityrunner1, i);
                android.provider.MiuiSettings.XSpace.setAskType(ResolverActivityRunner._2D_get5(ResolverActivityRunner.this), ResolverActivityRunner._2D_get1(ResolverActivityRunner.this), ResolverActivityRunner._2D_get4(ResolverActivityRunner.this));
            }
            ResolverActivityRunner._2D_wrap0(ResolverActivityRunner.this, ResolverActivityRunner._2D_get2(ResolverActivityRunner.this));
        }

        final ResolverActivityRunner this$1;

            
            {
                this$1 = ResolverActivityRunner.this;
                super();
            }
    }

}
