// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.content;

import android.content.*;
import android.net.Uri;
import android.os.*;
import android.util.Slog;
import com.android.internal.os.BackgroundThread;
import com.android.internal.util.Preconditions;
import java.util.HashSet;

public abstract class PackageMonitor extends BroadcastReceiver
{

    public PackageMonitor()
    {
        mChangeUserId = -10000;
        mTempArray = new String[1];
    }

    public boolean anyPackagesAppearing()
    {
        boolean flag;
        if(mAppearingPackages != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean anyPackagesDisappearing()
    {
        boolean flag;
        if(mDisappearingPackages != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean didSomePackagesChange()
    {
        return mSomePackagesChanged;
    }

    public int getChangingUserId()
    {
        return mChangeUserId;
    }

    String getPackageName(Intent intent)
    {
        intent = intent.getData();
        if(intent != null)
            intent = intent.getSchemeSpecificPart();
        else
            intent = null;
        return intent;
    }

    public Handler getRegisteredHandler()
    {
        return mRegisteredHandler;
    }

    public boolean isComponentModified(String s)
    {
        if(s == null || mModifiedComponents == null)
            return false;
        for(int i = mModifiedComponents.length - 1; i >= 0; i--)
            if(s.equals(mModifiedComponents[i]))
                return true;

        return false;
    }

    public int isPackageAppearing(String s)
    {
        if(mAppearingPackages != null)
        {
            for(int i = mAppearingPackages.length - 1; i >= 0; i--)
                if(s.equals(mAppearingPackages[i]))
                    return mChangeType;

        }
        return 0;
    }

    public int isPackageDisappearing(String s)
    {
        if(mDisappearingPackages != null)
        {
            for(int i = mDisappearingPackages.length - 1; i >= 0; i--)
                if(s.equals(mDisappearingPackages[i]))
                    return mChangeType;

        }
        return 0;
    }

    public boolean isPackageModified(String s)
    {
        if(mModifiedPackages != null)
        {
            for(int i = mModifiedPackages.length - 1; i >= 0; i--)
                if(s.equals(mModifiedPackages[i]))
                    return true;

        }
        return false;
    }

    boolean isPackageUpdating(String s)
    {
        HashSet hashset = mUpdatingPackages;
        hashset;
        JVM INSTR monitorenter ;
        boolean flag = mUpdatingPackages.contains(s);
        hashset;
        JVM INSTR monitorexit ;
        return flag;
        s;
        throw s;
    }

    public boolean isReplacing()
    {
        boolean flag = true;
        if(mChangeType != 1)
            flag = false;
        return flag;
    }

    public void onBeginPackageChanges()
    {
    }

    public void onFinishPackageChanges()
    {
    }

    public boolean onHandleForceStop(Intent intent, String as[], int i, boolean flag)
    {
        return false;
    }

    public void onHandleUserStop(Intent intent, int i)
    {
    }

    public void onPackageAdded(String s, int i)
    {
    }

    public void onPackageAppeared(String s, int i)
    {
    }

    public boolean onPackageChanged(String s, int i, String as[])
    {
        if(as != null)
        {
            int j = as.length;
            for(i = 0; i < j; i++)
                if(s.equals(as[i]))
                    return true;

        }
        return false;
    }

    public void onPackageDataCleared(String s, int i)
    {
    }

    public void onPackageDisappeared(String s, int i)
    {
    }

    public void onPackageModified(String s)
    {
    }

    public void onPackageRemoved(String s, int i)
    {
    }

    public void onPackageRemovedAllUsers(String s, int i)
    {
    }

    public void onPackageUpdateFinished(String s, int i)
    {
    }

    public void onPackageUpdateStarted(String s, int i)
    {
    }

    public void onPackagesAvailable(String as[])
    {
    }

    public void onPackagesSuspended(String as[])
    {
    }

    public void onPackagesUnavailable(String as[])
    {
    }

    public void onPackagesUnsuspended(String as[])
    {
    }

    public void onReceive(Context context, Intent intent)
    {
        int i;
        i = 2;
        mChangeUserId = intent.getIntExtra("android.intent.extra.user_handle", -10000);
        if(mChangeUserId == -10000)
        {
            Slog.w("PackageMonitor", (new StringBuilder()).append("Intent broadcast does not contain user handle: ").append(intent).toString());
            return;
        }
        onBeginPackageChanges();
        mAppearingPackages = null;
        mDisappearingPackages = null;
        mSomePackagesChanged = false;
        mModifiedComponents = null;
        context = intent.getAction();
        if(!"android.intent.action.PACKAGE_ADDED".equals(context)) goto _L2; else goto _L1
_L1:
        context = getPackageName(intent);
        i = intent.getIntExtra("android.intent.extra.UID", 0);
        mSomePackagesChanged = true;
        if(context == null) goto _L4; else goto _L3
_L3:
        mAppearingPackages = mTempArray;
        mTempArray[0] = context;
        if(intent.getBooleanExtra("android.intent.extra.REPLACING", false))
        {
            mModifiedPackages = mTempArray;
            mChangeType = 1;
            onPackageUpdateFinished(context, i);
            onPackageModified(context);
        } else
        {
            mChangeType = 3;
            onPackageAdded(context, i);
        }
        onPackageAppeared(context, mChangeType);
        if(mChangeType != 1) goto _L4; else goto _L5
_L5:
        intent = mUpdatingPackages;
        intent;
        JVM INSTR monitorenter ;
        mUpdatingPackages.remove(context);
        intent;
        JVM INSTR monitorexit ;
_L4:
        if(mSomePackagesChanged)
            onSomePackagesChanged();
        onFinishPackageChanges();
        mChangeUserId = -10000;
        return;
        context;
        throw context;
_L2:
        if(!"android.intent.action.PACKAGE_REMOVED".equals(context)) goto _L7; else goto _L6
_L6:
        context = getPackageName(intent);
        i = intent.getIntExtra("android.intent.extra.UID", 0);
        if(context == null)
            continue; /* Loop/switch isn't completed */
        mDisappearingPackages = mTempArray;
        mTempArray[0] = context;
        if(!intent.getBooleanExtra("android.intent.extra.REPLACING", false)) goto _L9; else goto _L8
_L8:
        mChangeType = 1;
        intent = mUpdatingPackages;
        intent;
        JVM INSTR monitorenter ;
        onPackageUpdateStarted(context, i);
_L10:
        onPackageDisappeared(context, mChangeType);
        continue; /* Loop/switch isn't completed */
_L9:
        mChangeType = 3;
        mSomePackagesChanged = true;
        onPackageRemoved(context, i);
        if(intent.getBooleanExtra("android.intent.extra.REMOVED_FOR_ALL_USERS", false))
            onPackageRemovedAllUsers(context, i);
        if(true) goto _L10; else goto _L7
_L7:
        if("android.intent.action.PACKAGE_CHANGED".equals(context))
        {
            context = getPackageName(intent);
            i = intent.getIntExtra("android.intent.extra.UID", 0);
            mModifiedComponents = intent.getStringArrayExtra("android.intent.extra.changed_component_name_list");
            if(context != null)
            {
                mModifiedPackages = mTempArray;
                mTempArray[0] = context;
                mChangeType = 3;
                if(onPackageChanged(context, i, mModifiedComponents))
                    mSomePackagesChanged = true;
                onPackageModified(context);
            }
        } else
        if("android.intent.action.PACKAGE_DATA_CLEARED".equals(context))
        {
            context = getPackageName(intent);
            i = intent.getIntExtra("android.intent.extra.UID", 0);
            if(context != null)
                onPackageDataCleared(context, i);
        } else
        if("android.intent.action.QUERY_PACKAGE_RESTART".equals(context))
        {
            mDisappearingPackages = intent.getStringArrayExtra("android.intent.extra.PACKAGES");
            mChangeType = 2;
            if(onHandleForceStop(intent, mDisappearingPackages, intent.getIntExtra("android.intent.extra.UID", 0), false))
                setResultCode(-1);
        } else
        if("android.intent.action.PACKAGE_RESTARTED".equals(context))
        {
            mDisappearingPackages = (new String[] {
                getPackageName(intent)
            });
            mChangeType = 2;
            onHandleForceStop(intent, mDisappearingPackages, intent.getIntExtra("android.intent.extra.UID", 0), true);
        } else
        if("android.intent.action.UID_REMOVED".equals(context))
            onUidRemoved(intent.getIntExtra("android.intent.extra.UID", 0));
        else
        if("android.intent.action.USER_STOPPED".equals(context))
        {
            if(intent.hasExtra("android.intent.extra.user_handle"))
                onHandleUserStop(intent, intent.getIntExtra("android.intent.extra.user_handle", 0));
        } else
        if("android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE".equals(context))
        {
            context = intent.getStringArrayExtra("android.intent.extra.changed_package_list");
            mAppearingPackages = context;
            if(intent.getBooleanExtra("android.intent.extra.REPLACING", false))
                i = 1;
            mChangeType = i;
            mSomePackagesChanged = true;
            if(context != null)
            {
                onPackagesAvailable(context);
                i = 0;
                while(i < context.length) 
                {
                    onPackageAppeared(context[i], mChangeType);
                    i++;
                }
            }
        } else
        if("android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE".equals(context))
        {
            context = intent.getStringArrayExtra("android.intent.extra.changed_package_list");
            mDisappearingPackages = context;
            if(intent.getBooleanExtra("android.intent.extra.REPLACING", false))
                i = 1;
            mChangeType = i;
            mSomePackagesChanged = true;
            if(context != null)
            {
                onPackagesUnavailable(context);
                int j = 0;
                while(j < context.length) 
                {
                    onPackageDisappeared(context[j], mChangeType);
                    j++;
                }
            }
        } else
        if("android.intent.action.PACKAGES_SUSPENDED".equals(context))
        {
            context = intent.getStringArrayExtra("android.intent.extra.changed_package_list");
            mSomePackagesChanged = true;
            onPackagesSuspended(context);
        } else
        if("android.intent.action.PACKAGES_UNSUSPENDED".equals(context))
        {
            context = intent.getStringArrayExtra("android.intent.extra.changed_package_list");
            mSomePackagesChanged = true;
            onPackagesUnsuspended(context);
        }
        if(true) goto _L4; else goto _L11
_L11:
    }

    public void onSomePackagesChanged()
    {
    }

    public void onUidRemoved(int i)
    {
    }

    public void register(Context context, Looper looper, UserHandle userhandle, boolean flag)
    {
        if(looper == null)
            looper = BackgroundThread.getHandler();
        else
            looper = new Handler(looper);
        register(context, userhandle, flag, ((Handler) (looper)));
    }

    public void register(Context context, Looper looper, boolean flag)
    {
        register(context, looper, ((UserHandle) (null)), flag);
    }

    public void register(Context context, UserHandle userhandle, boolean flag, Handler handler)
    {
        if(mRegisteredContext != null)
            throw new IllegalStateException("Already registered");
        mRegisteredContext = context;
        mRegisteredHandler = (Handler)Preconditions.checkNotNull(handler);
        if(userhandle == null) goto _L2; else goto _L1
_L1:
        context.registerReceiverAsUser(this, userhandle, sPackageFilt, null, mRegisteredHandler);
        context.registerReceiverAsUser(this, userhandle, sNonDataFilt, null, mRegisteredHandler);
        if(flag)
            context.registerReceiverAsUser(this, userhandle, sExternalFilt, null, mRegisteredHandler);
_L4:
        return;
_L2:
        context.registerReceiver(this, sPackageFilt, null, mRegisteredHandler);
        context.registerReceiver(this, sNonDataFilt, null, mRegisteredHandler);
        if(flag)
            context.registerReceiver(this, sExternalFilt, null, mRegisteredHandler);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void unregister()
    {
        if(mRegisteredContext == null)
        {
            throw new IllegalStateException("Not registered");
        } else
        {
            mRegisteredContext.unregisterReceiver(this);
            mRegisteredContext = null;
            return;
        }
    }

    public static final int PACKAGE_PERMANENT_CHANGE = 3;
    public static final int PACKAGE_TEMPORARY_CHANGE = 2;
    public static final int PACKAGE_UNCHANGED = 0;
    public static final int PACKAGE_UPDATING = 1;
    static final IntentFilter sExternalFilt;
    static final IntentFilter sNonDataFilt;
    static final IntentFilter sPackageFilt;
    String mAppearingPackages[];
    int mChangeType;
    int mChangeUserId;
    String mDisappearingPackages[];
    String mModifiedComponents[];
    String mModifiedPackages[];
    Context mRegisteredContext;
    Handler mRegisteredHandler;
    boolean mSomePackagesChanged;
    String mTempArray[];
    final HashSet mUpdatingPackages = new HashSet();

    static 
    {
        sPackageFilt = new IntentFilter();
        sNonDataFilt = new IntentFilter();
        sExternalFilt = new IntentFilter();
        sPackageFilt.addAction("android.intent.action.PACKAGE_ADDED");
        sPackageFilt.addAction("android.intent.action.PACKAGE_REMOVED");
        sPackageFilt.addAction("android.intent.action.PACKAGE_CHANGED");
        sPackageFilt.addAction("android.intent.action.QUERY_PACKAGE_RESTART");
        sPackageFilt.addAction("android.intent.action.PACKAGE_RESTARTED");
        sPackageFilt.addAction("android.intent.action.PACKAGE_DATA_CLEARED");
        sPackageFilt.addDataScheme("package");
        sNonDataFilt.addAction("android.intent.action.UID_REMOVED");
        sNonDataFilt.addAction("android.intent.action.USER_STOPPED");
        sNonDataFilt.addAction("android.intent.action.PACKAGES_SUSPENDED");
        sNonDataFilt.addAction("android.intent.action.PACKAGES_UNSUSPENDED");
        sExternalFilt.addAction("android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE");
        sExternalFilt.addAction("android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE");
    }
}
