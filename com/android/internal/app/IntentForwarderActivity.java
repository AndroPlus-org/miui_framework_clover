// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.app.*;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.*;
import android.os.*;
import android.util.Slog;
import android.widget.Toast;
import java.util.Iterator;

// Referenced classes of package com.android.internal.app:
//            ResolverActivity, ChooserActivity

public class IntentForwarderActivity extends Activity
{
    public static interface Injector
    {

        public abstract IPackageManager getIPackageManager();

        public abstract PackageManager getPackageManager();

        public abstract UserManager getUserManager();
    }

    private class InjectorImpl
        implements Injector
    {

        public IPackageManager getIPackageManager()
        {
            return AppGlobals.getPackageManager();
        }

        public PackageManager getPackageManager()
        {
            return IntentForwarderActivity.this.getPackageManager();
        }

        public UserManager getUserManager()
        {
            return (UserManager)getSystemService(android/os/UserManager);
        }

        final IntentForwarderActivity this$0;

        private InjectorImpl()
        {
            this$0 = IntentForwarderActivity.this;
            super();
        }

        InjectorImpl(InjectorImpl injectorimpl)
        {
            this();
        }
    }


    public IntentForwarderActivity()
    {
    }

    private int getManagedProfile()
    {
        for(Iterator iterator = mInjector.getUserManager().getProfiles(UserHandle.myUserId()).iterator(); iterator.hasNext();)
        {
            UserInfo userinfo = (UserInfo)iterator.next();
            if(userinfo.isManagedProfile())
                return userinfo.id;
        }

        Slog.wtf(TAG, (new StringBuilder()).append(FORWARD_INTENT_TO_MANAGED_PROFILE).append(" has been called, but there is no managed profile").toString());
        return -10000;
    }

    private int getProfileParent()
    {
        UserInfo userinfo = mInjector.getUserManager().getProfileParent(UserHandle.myUserId());
        if(userinfo == null)
        {
            Slog.wtf(TAG, (new StringBuilder()).append(FORWARD_INTENT_TO_PARENT).append(" has been called, but there is no parent").toString());
            return -10000;
        } else
        {
            return userinfo.id;
        }
    }

    private void sanitizeIntent(Intent intent)
    {
        intent.setPackage(null);
        intent.setComponent(null);
    }

    Intent canForward(Intent intent, int i)
    {
        Intent intent1;
        String s;
        intent1 = new Intent(intent);
        intent1.addFlags(0x3000000);
        sanitizeIntent(intent1);
        intent = intent1;
        if("android.intent.action.CHOOSER".equals(intent1.getAction()))
        {
            if(intent1.hasExtra("android.intent.extra.INITIAL_INTENTS"))
            {
                Slog.wtf(TAG, "An chooser intent with extra initial intents cannot be forwarded to a different user");
                return null;
            }
            if(intent1.hasExtra("android.intent.extra.REPLACEMENT_EXTRAS"))
            {
                Slog.wtf(TAG, "A chooser intent with replacement extras cannot be forwarded to a different user");
                return null;
            }
            Intent intent2 = (Intent)intent1.getParcelableExtra("android.intent.extra.INTENT");
            intent = intent2;
            if(intent2 == null)
            {
                Slog.wtf(TAG, "Cannot forward a chooser intent with no extra android.intent.extra.INTENT");
                return null;
            }
        }
        if(intent1.getSelector() != null)
            intent = intent1.getSelector();
        s = intent.resolveTypeIfNeeded(getContentResolver());
        sanitizeIntent(intent);
        boolean flag = mInjector.getIPackageManager().canForwardTo(intent, s, getUserId(), i);
        if(flag)
            return intent1;
        break MISSING_BLOCK_LABEL_173;
        intent;
        Slog.e(TAG, "PackageManagerService is dead?");
        return null;
    }

    protected Injector createInjector()
    {
        return new InjectorImpl(null);
    }

    protected void onCreate(Bundle bundle)
    {
        startActivityAsCaller(bundle, null, false, j);
_L1:
        if(k != 0)
            Toast.makeText(this, getString(i), 1).show();
_L2:
        finish();
        return;
        runtimeexception;
        j = -1;
        bundle = "?";
        l = ActivityManager.getService().getLaunchedFromUid(getActivityToken());
        j = l;
        obj = ActivityManager.getService().getLaunchedFromPackage(getActivityToken());
        bundle = ((Bundle) (obj));
        j = l;
_L3:
        Slog.wtf(TAG, (new StringBuilder()).append("Unable to launch as UID ").append(j).append(" package ").append(bundle).append(", while running in ").append(ActivityThread.currentProcessName()).toString(), runtimeexception);
          goto _L1
        super.onCreate(bundle);
        mInjector = createInjector();
        Object obj = getIntent();
        bundle = ((Intent) (obj)).getComponent().getClassName();
        int i;
        int j;
        if(bundle.equals(FORWARD_INTENT_TO_PARENT))
        {
            i = 0x1040241;
            j = getProfileParent();
        } else
        if(bundle.equals(FORWARD_INTENT_TO_MANAGED_PROFILE))
        {
            i = 0x1040242;
            j = getManagedProfile();
        } else
        {
            Slog.wtf(TAG, (new StringBuilder()).append(com/android/internal/app/IntentForwarderActivity.getName()).append(" cannot be called directly").toString());
            i = -1;
            j = -10000;
        }
        if(j == -10000)
        {
            finish();
            return;
        }
        int k = getUserId();
        bundle = canForward(((Intent) (obj)), j);
        RuntimeException runtimeexception;
        int l;
        if(bundle != null)
        {
            if("android.intent.action.CHOOSER".equals(bundle.getAction()))
                ((Intent)bundle.getParcelableExtra("android.intent.extra.INTENT")).prepareToLeaveUser(k);
            else
                bundle.prepareToLeaveUser(k);
            obj = mInjector.getPackageManager().resolveActivityAsUser(bundle, 0x10000, j);
            if(obj == null || ((ResolveInfo) (obj)).activityInfo == null || "android".equals(((ResolveInfo) (obj)).activityInfo.packageName) ^ true)
            {
                k = 1;
            } else
            {
                boolean flag;
                if(!com/android/internal/app/ResolverActivity.getName().equals(((ResolveInfo) (obj)).activityInfo.name))
                    flag = com/android/internal/app/ChooserActivity.getName().equals(((ResolveInfo) (obj)).activityInfo.name);
                else
                    flag = true;
                k = flag ^ true;
            }
            break MISSING_BLOCK_LABEL_196;
        }
        Slog.wtf(TAG, (new StringBuilder()).append("the intent: ").append(obj).append(" cannot be forwarded from user ").append(k).append(" to user ").append(j).toString());
          goto _L2
        RemoteException remoteexception;
        remoteexception;
          goto _L3
    }

    public static String FORWARD_INTENT_TO_MANAGED_PROFILE = "com.android.internal.app.ForwardIntentToManagedProfile";
    public static String FORWARD_INTENT_TO_PARENT = "com.android.internal.app.ForwardIntentToParent";
    public static String TAG = "IntentForwarderActivity";
    private Injector mInjector;

}
