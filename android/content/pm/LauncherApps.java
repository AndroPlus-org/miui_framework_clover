// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.appwidget.AppWidgetProviderInfo;
import android.content.*;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.*;
import android.os.*;
import android.util.Log;
import com.android.internal.util.Preconditions;
import java.io.IOException;
import java.util.*;

// Referenced classes of package android.content.pm:
//            ParceledListSlice, ResolveInfo, LauncherActivityInfo, ILauncherApps, 
//            PackageManager, ShortcutInfo, ApplicationInfo, IPinItemRequest

public class LauncherApps
{
    public static abstract class Callback
    {

        public abstract void onPackageAdded(String s, UserHandle userhandle);

        public abstract void onPackageChanged(String s, UserHandle userhandle);

        public abstract void onPackageRemoved(String s, UserHandle userhandle);

        public abstract void onPackagesAvailable(String as[], UserHandle userhandle, boolean flag);

        public void onPackagesSuspended(String as[], UserHandle userhandle)
        {
        }

        public abstract void onPackagesUnavailable(String as[], UserHandle userhandle, boolean flag);

        public void onPackagesUnsuspended(String as[], UserHandle userhandle)
        {
        }

        public void onShortcutsChanged(String s, List list, UserHandle userhandle)
        {
        }

        public Callback()
        {
        }
    }

    private static class CallbackMessageHandler extends Handler
    {

        static Callback _2D_get0(CallbackMessageHandler callbackmessagehandler)
        {
            return callbackmessagehandler.mCallback;
        }

        public void handleMessage(Message message)
        {
            CallbackInfo callbackinfo;
            if(mCallback == null || (message.obj instanceof CallbackInfo) ^ true)
                return;
            callbackinfo = (CallbackInfo)message.obj;
            message.what;
            JVM INSTR tableswitch 1 8: default 80
        //                       1 81
        //                       2 99
        //                       3 117
        //                       4 135
        //                       5 157
        //                       6 179
        //                       7 197
        //                       8 215;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L1:
            return;
_L2:
            mCallback.onPackageAdded(callbackinfo.packageName, callbackinfo.user);
            continue; /* Loop/switch isn't completed */
_L3:
            mCallback.onPackageRemoved(callbackinfo.packageName, callbackinfo.user);
            continue; /* Loop/switch isn't completed */
_L4:
            mCallback.onPackageChanged(callbackinfo.packageName, callbackinfo.user);
            continue; /* Loop/switch isn't completed */
_L5:
            mCallback.onPackagesAvailable(callbackinfo.packageNames, callbackinfo.user, callbackinfo.replacing);
            continue; /* Loop/switch isn't completed */
_L6:
            mCallback.onPackagesUnavailable(callbackinfo.packageNames, callbackinfo.user, callbackinfo.replacing);
            continue; /* Loop/switch isn't completed */
_L7:
            mCallback.onPackagesSuspended(callbackinfo.packageNames, callbackinfo.user);
            continue; /* Loop/switch isn't completed */
_L8:
            mCallback.onPackagesUnsuspended(callbackinfo.packageNames, callbackinfo.user);
            continue; /* Loop/switch isn't completed */
_L9:
            mCallback.onShortcutsChanged(callbackinfo.packageName, callbackinfo.shortcuts, callbackinfo.user);
            if(true) goto _L1; else goto _L10
_L10:
        }

        public void postOnPackageAdded(String s, UserHandle userhandle)
        {
            CallbackInfo callbackinfo = new CallbackInfo(null);
            callbackinfo.packageName = s;
            callbackinfo.user = userhandle;
            obtainMessage(1, callbackinfo).sendToTarget();
        }

        public void postOnPackageChanged(String s, UserHandle userhandle)
        {
            CallbackInfo callbackinfo = new CallbackInfo(null);
            callbackinfo.packageName = s;
            callbackinfo.user = userhandle;
            obtainMessage(3, callbackinfo).sendToTarget();
        }

        public void postOnPackageRemoved(String s, UserHandle userhandle)
        {
            CallbackInfo callbackinfo = new CallbackInfo(null);
            callbackinfo.packageName = s;
            callbackinfo.user = userhandle;
            obtainMessage(2, callbackinfo).sendToTarget();
        }

        public void postOnPackagesAvailable(String as[], UserHandle userhandle, boolean flag)
        {
            CallbackInfo callbackinfo = new CallbackInfo(null);
            callbackinfo.packageNames = as;
            callbackinfo.replacing = flag;
            callbackinfo.user = userhandle;
            obtainMessage(4, callbackinfo).sendToTarget();
        }

        public void postOnPackagesSuspended(String as[], UserHandle userhandle)
        {
            CallbackInfo callbackinfo = new CallbackInfo(null);
            callbackinfo.packageNames = as;
            callbackinfo.user = userhandle;
            obtainMessage(6, callbackinfo).sendToTarget();
        }

        public void postOnPackagesUnavailable(String as[], UserHandle userhandle, boolean flag)
        {
            CallbackInfo callbackinfo = new CallbackInfo(null);
            callbackinfo.packageNames = as;
            callbackinfo.replacing = flag;
            callbackinfo.user = userhandle;
            obtainMessage(5, callbackinfo).sendToTarget();
        }

        public void postOnPackagesUnsuspended(String as[], UserHandle userhandle)
        {
            CallbackInfo callbackinfo = new CallbackInfo(null);
            callbackinfo.packageNames = as;
            callbackinfo.user = userhandle;
            obtainMessage(7, callbackinfo).sendToTarget();
        }

        public void postOnShortcutChanged(String s, UserHandle userhandle, List list)
        {
            CallbackInfo callbackinfo = new CallbackInfo(null);
            callbackinfo.packageName = s;
            callbackinfo.user = userhandle;
            callbackinfo.shortcuts = list;
            obtainMessage(8, callbackinfo).sendToTarget();
        }

        private static final int MSG_ADDED = 1;
        private static final int MSG_AVAILABLE = 4;
        private static final int MSG_CHANGED = 3;
        private static final int MSG_REMOVED = 2;
        private static final int MSG_SHORTCUT_CHANGED = 8;
        private static final int MSG_SUSPENDED = 6;
        private static final int MSG_UNAVAILABLE = 5;
        private static final int MSG_UNSUSPENDED = 7;
        private Callback mCallback;

        public CallbackMessageHandler(Looper looper, Callback callback)
        {
            super(looper, null, true);
            mCallback = callback;
        }
    }

    private static class CallbackMessageHandler.CallbackInfo
    {

        String packageName;
        String packageNames[];
        boolean replacing;
        List shortcuts;
        UserHandle user;

        private CallbackMessageHandler.CallbackInfo()
        {
        }

        CallbackMessageHandler.CallbackInfo(CallbackMessageHandler.CallbackInfo callbackinfo)
        {
            this();
        }
    }

    public static final class PinItemRequest
        implements Parcelable
    {

        public boolean accept()
        {
            return accept(null);
        }

        public boolean accept(Bundle bundle)
        {
            boolean flag;
            try
            {
                flag = mInner.accept(bundle);
            }
            // Misplaced declaration of an exception variable
            catch(Bundle bundle)
            {
                throw bundle.rethrowFromSystemServer();
            }
            return flag;
        }

        public int describeContents()
        {
            return 0;
        }

        public AppWidgetProviderInfo getAppWidgetProviderInfo(Context context)
        {
            AppWidgetProviderInfo appwidgetproviderinfo;
            try
            {
                appwidgetproviderinfo = mInner.getAppWidgetProviderInfo();
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                throw context.rethrowAsRuntimeException();
            }
            if(appwidgetproviderinfo == null)
                return null;
            appwidgetproviderinfo.updateDimensions(context.getResources().getDisplayMetrics());
            return appwidgetproviderinfo;
        }

        public Bundle getExtras()
        {
            Bundle bundle;
            try
            {
                bundle = mInner.getExtras();
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowAsRuntimeException();
            }
            return bundle;
        }

        public int getRequestType()
        {
            return mRequestType;
        }

        public ShortcutInfo getShortcutInfo()
        {
            ShortcutInfo shortcutinfo;
            try
            {
                shortcutinfo = mInner.getShortcutInfo();
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowAsRuntimeException();
            }
            return shortcutinfo;
        }

        public boolean isValid()
        {
            boolean flag;
            try
            {
                flag = mInner.isValid();
            }
            catch(RemoteException remoteexception)
            {
                return false;
            }
            return flag;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(mRequestType);
            parcel.writeStrongBinder(mInner.asBinder());
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public PinItemRequest createFromParcel(Parcel parcel)
            {
                return new PinItemRequest(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public PinItemRequest[] newArray(int i)
            {
                return new PinItemRequest[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public static final int REQUEST_TYPE_APPWIDGET = 2;
        public static final int REQUEST_TYPE_SHORTCUT = 1;
        private final IPinItemRequest mInner;
        private final int mRequestType;


        public PinItemRequest(IPinItemRequest ipinitemrequest, int i)
        {
            mInner = ipinitemrequest;
            mRequestType = i;
        }

        private PinItemRequest(Parcel parcel)
        {
            getClass().getClassLoader();
            mRequestType = parcel.readInt();
            mInner = IPinItemRequest.Stub.asInterface(parcel.readStrongBinder());
        }

        PinItemRequest(Parcel parcel, PinItemRequest pinitemrequest)
        {
            this(parcel);
        }
    }

    public static class ShortcutQuery
    {

        public ShortcutQuery setActivity(ComponentName componentname)
        {
            mActivity = componentname;
            return this;
        }

        public ShortcutQuery setChangedSince(long l)
        {
            mChangedSince = l;
            return this;
        }

        public ShortcutQuery setPackage(String s)
        {
            mPackage = s;
            return this;
        }

        public ShortcutQuery setQueryFlags(int i)
        {
            mQueryFlags = i;
            return this;
        }

        public ShortcutQuery setShortcutIds(List list)
        {
            mShortcutIds = list;
            return this;
        }

        public static final int FLAG_GET_ALL_KINDS = 11;
        public static final int FLAG_GET_DYNAMIC = 1;
        public static final int FLAG_GET_KEY_FIELDS_ONLY = 4;
        public static final int FLAG_GET_MANIFEST = 8;
        public static final int FLAG_GET_PINNED = 2;
        public static final int FLAG_MATCH_ALL_KINDS = 11;
        public static final int FLAG_MATCH_DYNAMIC = 1;
        public static final int FLAG_MATCH_MANIFEST = 8;
        public static final int FLAG_MATCH_PINNED = 2;
        ComponentName mActivity;
        long mChangedSince;
        String mPackage;
        int mQueryFlags;
        List mShortcutIds;

        public ShortcutQuery()
        {
        }
    }


    static List _2D_get0(LauncherApps launcherapps)
    {
        return launcherapps.mCallbacks;
    }

    public LauncherApps(Context context)
    {
        this(context, ILauncherApps.Stub.asInterface(ServiceManager.getService("launcherapps")));
    }

    public LauncherApps(Context context, ILauncherApps ilauncherapps)
    {
        mCallbacks = new ArrayList();
        mContext = context;
        mService = ilauncherapps;
        mPm = context.getPackageManager();
        mUserManager = (UserManager)context.getSystemService(android/os/UserManager);
    }

    private void addCallbackLocked(Callback callback, Handler handler)
    {
        removeCallbackLocked(callback);
        Handler handler1 = handler;
        if(handler == null)
            handler1 = new Handler();
        callback = new CallbackMessageHandler(handler1.getLooper(), callback);
        mCallbacks.add(callback);
    }

    private List convertToActivityList(ParceledListSlice parceledlistslice, UserHandle userhandle)
    {
        if(parceledlistslice == null)
            return Collections.EMPTY_LIST;
        ArrayList arraylist = new ArrayList();
        for(Iterator iterator = parceledlistslice.getList().iterator(); iterator.hasNext(); arraylist.add(new LauncherActivityInfo(mContext, ((ResolveInfo) (parceledlistslice)).activityInfo, userhandle)))
            parceledlistslice = (ResolveInfo)iterator.next();

        return arraylist;
    }

    private int findCallbackLocked(Callback callback)
    {
        if(callback == null)
            throw new IllegalArgumentException("Callback cannot be null");
        int i = mCallbacks.size();
        for(int j = 0; j < i; j++)
            if(CallbackMessageHandler._2D_get0((CallbackMessageHandler)mCallbacks.get(j)) == callback)
                return j;

        return -1;
    }

    private ParcelFileDescriptor getShortcutIconFd(String s, String s1, int i)
    {
        try
        {
            s = mService.getShortcutIconFd(mContext.getPackageName(), s, s1, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return s;
    }

    private Drawable loadDrawableResourceFromPackage(String s, int i, UserHandle userhandle, int j)
    {
        if(i == 0)
            return null;
        try
        {
            s = getApplicationInfo(s, 0, userhandle);
            s = mContext.getPackageManager().getResourcesForApplication(s).getDrawableForDensity(i, j);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return null;
        }
        return s;
    }

    private void logErrorForInvalidProfileAccess(UserHandle userhandle)
    {
        if(UserHandle.myUserId() != userhandle.getIdentifier() && mUserManager.isManagedProfile())
            Log.w("LauncherApps", "Accessing other profiles/users from managed profile is no longer allowed.");
    }

    private void removeCallbackLocked(Callback callback)
    {
        int i = findCallbackLocked(callback);
        if(i >= 0)
            mCallbacks.remove(i);
    }

    private void startShortcut(String s, String s1, Rect rect, Bundle bundle, int i)
    {
        try
        {
            if(!mService.startShortcut(mContext.getPackageName(), s, s1, rect, bundle, i))
            {
                s = JVM INSTR new #264 <Class ActivityNotFoundException>;
                s.ActivityNotFoundException("Shortcut could not be started");
                throw s;
            }
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public List getActivityList(String s, UserHandle userhandle)
    {
        logErrorForInvalidProfileAccess(userhandle);
        try
        {
            s = convertToActivityList(mService.getLauncherActivities(mContext.getPackageName(), s, userhandle), userhandle);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return s;
    }

    public ApplicationInfo getApplicationInfo(String s, int i, UserHandle userhandle)
        throws PackageManager.NameNotFoundException
    {
        Object obj;
        Preconditions.checkNotNull(s, "packageName");
        Preconditions.checkNotNull(s, "user");
        logErrorForInvalidProfileAccess(userhandle);
        PackageManager.NameNotFoundException namenotfoundexception;
        try
        {
            obj = mService.getApplicationInfo(mContext.getPackageName(), s, i, userhandle);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        if(obj != null)
            break MISSING_BLOCK_LABEL_104;
        namenotfoundexception = JVM INSTR new #213 <Class PackageManager$NameNotFoundException>;
        obj = JVM INSTR new #293 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        namenotfoundexception.PackageManager.NameNotFoundException(((StringBuilder) (obj)).append("Package ").append(s).append(" not found for user ").append(userhandle.getIdentifier()).toString());
        throw namenotfoundexception;
        return ((ApplicationInfo) (obj));
    }

    public PinItemRequest getPinItemRequest(Intent intent)
    {
        return (PinItemRequest)intent.getParcelableExtra("android.content.pm.extra.PIN_ITEM_REQUEST");
    }

    public List getProfiles()
    {
        if(mUserManager.isManagedProfile())
        {
            ArrayList arraylist = new ArrayList(1);
            arraylist.add(Process.myUserHandle());
            return arraylist;
        } else
        {
            return mUserManager.getUserProfiles();
        }
    }

    public Drawable getShortcutBadgedIconDrawable(ShortcutInfo shortcutinfo, int i)
    {
        Object obj = null;
        Drawable drawable = getShortcutIconDrawable(shortcutinfo, i);
        if(drawable == null)
            shortcutinfo = obj;
        else
            shortcutinfo = mContext.getPackageManager().getUserBadgedIcon(drawable, shortcutinfo.getUserHandle());
        return shortcutinfo;
    }

    public IntentSender getShortcutConfigActivityIntent(LauncherActivityInfo launcheractivityinfo)
    {
        try
        {
            launcheractivityinfo = mService.getShortcutConfigActivityIntent(mContext.getPackageName(), launcheractivityinfo.getComponentName(), launcheractivityinfo.getUser());
        }
        // Misplaced declaration of an exception variable
        catch(LauncherActivityInfo launcheractivityinfo)
        {
            throw launcheractivityinfo.rethrowFromSystemServer();
        }
        return launcheractivityinfo;
    }

    public List getShortcutConfigActivityList(String s, UserHandle userhandle)
    {
        logErrorForInvalidProfileAccess(userhandle);
        try
        {
            s = convertToActivityList(mService.getShortcutConfigActivities(mContext.getPackageName(), s, userhandle), userhandle);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return s;
    }

    public Drawable getShortcutIconDrawable(ShortcutInfo shortcutinfo, int i)
    {
        Object obj;
        if(!shortcutinfo.hasIconFile())
            break MISSING_BLOCK_LABEL_112;
        obj = getShortcutIconFd(shortcutinfo);
        if(obj == null)
            return null;
        android.graphics.Bitmap bitmap = BitmapFactory.decodeFileDescriptor(((ParcelFileDescriptor) (obj)).getFileDescriptor());
        if(bitmap == null)
            break MISSING_BLOCK_LABEL_91;
        BitmapDrawable bitmapdrawable;
        bitmapdrawable = JVM INSTR new #384 <Class BitmapDrawable>;
        bitmapdrawable.BitmapDrawable(mContext.getResources(), bitmap);
        if(!shortcutinfo.hasAdaptiveBitmap())
            break MISSING_BLOCK_LABEL_80;
        shortcutinfo = new AdaptiveIconDrawable(null, bitmapdrawable);
        try
        {
            ((ParcelFileDescriptor) (obj)).close();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj) { }
        return shortcutinfo;
        try
        {
            ((ParcelFileDescriptor) (obj)).close();
        }
        // Misplaced declaration of an exception variable
        catch(ShortcutInfo shortcutinfo) { }
        return bitmapdrawable;
        try
        {
            ((ParcelFileDescriptor) (obj)).close();
        }
        // Misplaced declaration of an exception variable
        catch(ShortcutInfo shortcutinfo) { }
        return null;
        shortcutinfo;
        try
        {
            ((ParcelFileDescriptor) (obj)).close();
        }
        catch(IOException ioexception) { }
        throw shortcutinfo;
        if(shortcutinfo.hasIconResource())
            return loadDrawableResourceFromPackage(shortcutinfo.getPackage(), shortcutinfo.getIconResourceId(), shortcutinfo.getUserHandle(), i);
        if(shortcutinfo.getIcon() != null)
        {
            Icon icon = shortcutinfo.getIcon();
            switch(icon.getType())
            {
            case 3: // '\003'
            case 4: // '\004'
            default:
                return null;

            case 2: // '\002'
                return loadDrawableResourceFromPackage(shortcutinfo.getPackage(), icon.getResId(), shortcutinfo.getUserHandle(), i);

            case 1: // '\001'
            case 5: // '\005'
                return icon.loadDrawable(mContext);
            }
        } else
        {
            return null;
        }
    }

    public ParcelFileDescriptor getShortcutIconFd(ShortcutInfo shortcutinfo)
    {
        return getShortcutIconFd(shortcutinfo.getPackage(), shortcutinfo.getId(), shortcutinfo.getUserId());
    }

    public ParcelFileDescriptor getShortcutIconFd(String s, String s1, UserHandle userhandle)
    {
        return getShortcutIconFd(s, s1, userhandle.getIdentifier());
    }

    public int getShortcutIconResId(ShortcutInfo shortcutinfo)
    {
        return shortcutinfo.getIconResourceId();
    }

    public int getShortcutIconResId(String s, String s1, UserHandle userhandle)
    {
        int i = 0;
        ShortcutQuery shortcutquery = new ShortcutQuery();
        shortcutquery.setPackage(s);
        shortcutquery.setShortcutIds(Arrays.asList(new String[] {
            s1
        }));
        shortcutquery.setQueryFlags(11);
        s = getShortcuts(shortcutquery, userhandle);
        if(s.size() > 0)
            i = ((ShortcutInfo)s.get(0)).getIconResourceId();
        return i;
    }

    public List getShortcutInfo(String s, List list, UserHandle userhandle)
    {
        ShortcutQuery shortcutquery = new ShortcutQuery();
        shortcutquery.setPackage(s);
        shortcutquery.setShortcutIds(list);
        shortcutquery.setQueryFlags(11);
        return getShortcuts(shortcutquery, userhandle);
    }

    public List getShortcuts(ShortcutQuery shortcutquery, UserHandle userhandle)
    {
        logErrorForInvalidProfileAccess(userhandle);
        try
        {
            shortcutquery = mService.getShortcuts(mContext.getPackageName(), shortcutquery.mChangedSince, shortcutquery.mPackage, shortcutquery.mShortcutIds, shortcutquery.mActivity, shortcutquery.mQueryFlags, userhandle).getList();
        }
        // Misplaced declaration of an exception variable
        catch(ShortcutQuery shortcutquery)
        {
            throw shortcutquery.rethrowFromSystemServer();
        }
        return shortcutquery;
    }

    public boolean hasShortcutHostPermission()
    {
        boolean flag;
        try
        {
            flag = mService.hasShortcutHostPermission(mContext.getPackageName());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isActivityEnabled(ComponentName componentname, UserHandle userhandle)
    {
        logErrorForInvalidProfileAccess(userhandle);
        boolean flag;
        try
        {
            flag = mService.isActivityEnabled(mContext.getPackageName(), componentname, userhandle);
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isPackageEnabled(String s, UserHandle userhandle)
    {
        logErrorForInvalidProfileAccess(userhandle);
        boolean flag;
        try
        {
            flag = mService.isPackageEnabled(mContext.getPackageName(), s, userhandle);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public void pinShortcuts(String s, List list, UserHandle userhandle)
    {
        logErrorForInvalidProfileAccess(userhandle);
        try
        {
            mService.pinShortcuts(mContext.getPackageName(), s, list, userhandle);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void registerCallback(Callback callback)
    {
        registerCallback(callback, null);
    }

    public void registerCallback(Callback callback, Handler handler)
    {
        this;
        JVM INSTR monitorenter ;
        if(callback == null)
            break MISSING_BLOCK_LABEL_58;
        if(findCallbackLocked(callback) >= 0)
            break MISSING_BLOCK_LABEL_58;
        boolean flag;
        if(mCallbacks.size() == 0)
            flag = true;
        else
            flag = false;
        addCallbackLocked(callback, handler);
        if(!flag)
            break MISSING_BLOCK_LABEL_58;
        mService.addOnAppsChangedListener(mContext.getPackageName(), mAppsChangedListener);
        this;
        JVM INSTR monitorexit ;
        return;
        callback;
        throw callback.rethrowFromSystemServer();
        callback;
        this;
        JVM INSTR monitorexit ;
        throw callback;
    }

    public LauncherActivityInfo resolveActivity(Intent intent, UserHandle userhandle)
    {
        logErrorForInvalidProfileAccess(userhandle);
        try
        {
            intent = mService.resolveActivity(mContext.getPackageName(), intent.getComponent(), userhandle);
        }
        // Misplaced declaration of an exception variable
        catch(Intent intent)
        {
            throw intent.rethrowFromSystemServer();
        }
        if(intent == null)
            break MISSING_BLOCK_LABEL_53;
        intent = new LauncherActivityInfo(mContext, intent, userhandle);
        return intent;
        return null;
    }

    public void startAppDetailsActivity(ComponentName componentname, UserHandle userhandle, Rect rect, Bundle bundle)
    {
        logErrorForInvalidProfileAccess(userhandle);
        try
        {
            mService.showAppDetailsAsUser(mContext.getPackageName(), componentname, rect, bundle, userhandle);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
    }

    public void startMainActivity(ComponentName componentname, UserHandle userhandle, Rect rect, Bundle bundle)
    {
        logErrorForInvalidProfileAccess(userhandle);
        try
        {
            mService.startActivityAsUser(mContext.getPackageName(), componentname, rect, bundle, userhandle);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
    }

    public void startShortcut(ShortcutInfo shortcutinfo, Rect rect, Bundle bundle)
    {
        startShortcut(shortcutinfo.getPackage(), shortcutinfo.getId(), rect, bundle, shortcutinfo.getUserId());
    }

    public void startShortcut(String s, String s1, Rect rect, Bundle bundle, UserHandle userhandle)
    {
        logErrorForInvalidProfileAccess(userhandle);
        startShortcut(s, s1, rect, bundle, userhandle.getIdentifier());
    }

    public void unregisterCallback(Callback callback)
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        removeCallbackLocked(callback);
        i = mCallbacks.size();
        if(i != 0)
            break MISSING_BLOCK_LABEL_34;
        mService.removeOnAppsChangedListener(mAppsChangedListener);
        this;
        JVM INSTR monitorexit ;
        return;
        callback;
        throw callback.rethrowFromSystemServer();
        callback;
        this;
        JVM INSTR monitorexit ;
        throw callback;
    }

    public static final String ACTION_CONFIRM_PIN_APPWIDGET = "android.content.pm.action.CONFIRM_PIN_APPWIDGET";
    public static final String ACTION_CONFIRM_PIN_SHORTCUT = "android.content.pm.action.CONFIRM_PIN_SHORTCUT";
    static final boolean DEBUG = false;
    public static final String EXTRA_PIN_ITEM_REQUEST = "android.content.pm.extra.PIN_ITEM_REQUEST";
    static final String TAG = "LauncherApps";
    private IOnAppsChangedListener.Stub mAppsChangedListener = new IOnAppsChangedListener.Stub() {

        public void onPackageAdded(UserHandle userhandle, String s)
            throws RemoteException
        {
            LauncherApps launcherapps = LauncherApps.this;
            launcherapps;
            JVM INSTR monitorenter ;
            for(Iterator iterator = LauncherApps._2D_get0(LauncherApps.this).iterator(); iterator.hasNext(); ((CallbackMessageHandler)iterator.next()).postOnPackageAdded(s, userhandle));
            break MISSING_BLOCK_LABEL_54;
            userhandle;
            throw userhandle;
            launcherapps;
            JVM INSTR monitorexit ;
        }

        public void onPackageChanged(UserHandle userhandle, String s)
            throws RemoteException
        {
            LauncherApps launcherapps = LauncherApps.this;
            launcherapps;
            JVM INSTR monitorenter ;
            for(Iterator iterator = LauncherApps._2D_get0(LauncherApps.this).iterator(); iterator.hasNext(); ((CallbackMessageHandler)iterator.next()).postOnPackageChanged(s, userhandle));
            break MISSING_BLOCK_LABEL_54;
            userhandle;
            throw userhandle;
            launcherapps;
            JVM INSTR monitorexit ;
        }

        public void onPackageRemoved(UserHandle userhandle, String s)
            throws RemoteException
        {
            LauncherApps launcherapps = LauncherApps.this;
            launcherapps;
            JVM INSTR monitorenter ;
            for(Iterator iterator = LauncherApps._2D_get0(LauncherApps.this).iterator(); iterator.hasNext(); ((CallbackMessageHandler)iterator.next()).postOnPackageRemoved(s, userhandle));
            break MISSING_BLOCK_LABEL_54;
            userhandle;
            throw userhandle;
            launcherapps;
            JVM INSTR monitorexit ;
        }

        public void onPackagesAvailable(UserHandle userhandle, String as[], boolean flag)
            throws RemoteException
        {
            LauncherApps launcherapps = LauncherApps.this;
            launcherapps;
            JVM INSTR monitorenter ;
            for(Iterator iterator = LauncherApps._2D_get0(LauncherApps.this).iterator(); iterator.hasNext(); ((CallbackMessageHandler)iterator.next()).postOnPackagesAvailable(as, userhandle, flag));
            break MISSING_BLOCK_LABEL_58;
            userhandle;
            throw userhandle;
            launcherapps;
            JVM INSTR monitorexit ;
        }

        public void onPackagesSuspended(UserHandle userhandle, String as[])
            throws RemoteException
        {
            LauncherApps launcherapps = LauncherApps.this;
            launcherapps;
            JVM INSTR monitorenter ;
            for(Iterator iterator = LauncherApps._2D_get0(LauncherApps.this).iterator(); iterator.hasNext(); ((CallbackMessageHandler)iterator.next()).postOnPackagesSuspended(as, userhandle));
            break MISSING_BLOCK_LABEL_54;
            userhandle;
            throw userhandle;
            launcherapps;
            JVM INSTR monitorexit ;
        }

        public void onPackagesUnavailable(UserHandle userhandle, String as[], boolean flag)
            throws RemoteException
        {
            LauncherApps launcherapps = LauncherApps.this;
            launcherapps;
            JVM INSTR monitorenter ;
            for(Iterator iterator = LauncherApps._2D_get0(LauncherApps.this).iterator(); iterator.hasNext(); ((CallbackMessageHandler)iterator.next()).postOnPackagesUnavailable(as, userhandle, flag));
            break MISSING_BLOCK_LABEL_58;
            userhandle;
            throw userhandle;
            launcherapps;
            JVM INSTR monitorexit ;
        }

        public void onPackagesUnsuspended(UserHandle userhandle, String as[])
            throws RemoteException
        {
            LauncherApps launcherapps = LauncherApps.this;
            launcherapps;
            JVM INSTR monitorenter ;
            for(Iterator iterator = LauncherApps._2D_get0(LauncherApps.this).iterator(); iterator.hasNext(); ((CallbackMessageHandler)iterator.next()).postOnPackagesUnsuspended(as, userhandle));
            break MISSING_BLOCK_LABEL_54;
            userhandle;
            throw userhandle;
            launcherapps;
            JVM INSTR monitorexit ;
        }

        public void onShortcutChanged(UserHandle userhandle, String s, ParceledListSlice parceledlistslice)
        {
            List list = parceledlistslice.getList();
            parceledlistslice = LauncherApps.this;
            parceledlistslice;
            JVM INSTR monitorenter ;
            for(Iterator iterator = LauncherApps._2D_get0(LauncherApps.this).iterator(); iterator.hasNext(); ((CallbackMessageHandler)iterator.next()).postOnShortcutChanged(s, userhandle, list));
            break MISSING_BLOCK_LABEL_62;
            userhandle;
            throw userhandle;
            parceledlistslice;
            JVM INSTR monitorexit ;
        }

        final LauncherApps this$0;

            
            {
                this$0 = LauncherApps.this;
                super();
            }
    }
;
    private List mCallbacks;
    private final Context mContext;
    private final PackageManager mPm;
    private final ILauncherApps mService;
    private final UserManager mUserManager;
}
