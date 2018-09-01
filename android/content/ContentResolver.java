// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.accounts.Account;
import android.app.*;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ParceledListSlice;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.database.*;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.*;
import android.text.TextUtils;
import android.util.Log;
import android.util.SeempLog;
import com.android.internal.util.*;
import dalvik.system.CloseGuard;
import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package android.content:
//            Intent, Context, IContentService, SyncInfo, 
//            ContentProviderClient, OperationApplicationException, IContentProvider, ContentProvider, 
//            ContentResolverInjector, SyncStatusObserver, SyncRequest, SyncAdapterType, 
//            SyncStatusInfo, ContentProviderResult, ContentValues

public abstract class ContentResolver
{
    private final class CursorWrapperInner extends CrossProcessCursorWrapper
    {

        public void close()
        {
            mCloseGuard.close();
            super.close();
            if(mProviderReleased.compareAndSet(false, true))
                releaseProvider(mContentProvider);
        }

        protected void finalize()
            throws Throwable
        {
            if(mCloseGuard != null)
                mCloseGuard.warnIfOpen();
            close();
            super.finalize();
            return;
            Exception exception;
            exception;
            super.finalize();
            throw exception;
        }

        private final CloseGuard mCloseGuard = CloseGuard.get();
        private final IContentProvider mContentProvider;
        private final AtomicBoolean mProviderReleased = new AtomicBoolean();
        final ContentResolver this$0;

        CursorWrapperInner(Cursor cursor, IContentProvider icontentprovider)
        {
            this$0 = ContentResolver.this;
            super(cursor);
            mContentProvider = icontentprovider;
            mCloseGuard.open("close");
        }
    }

    public class OpenResourceIdResult
    {

        public int id;
        public Resources r;
        final ContentResolver this$0;

        public OpenResourceIdResult()
        {
            this$0 = ContentResolver.this;
            super();
        }
    }

    private final class ParcelFileDescriptorInner extends ParcelFileDescriptor
    {

        public void releaseResources()
        {
            if(mProviderReleased.compareAndSet(false, true))
                releaseProvider(mContentProvider);
        }

        private final IContentProvider mContentProvider;
        private final AtomicBoolean mProviderReleased = new AtomicBoolean();
        final ContentResolver this$0;

        ParcelFileDescriptorInner(ParcelFileDescriptor parcelfiledescriptor, IContentProvider icontentprovider)
        {
            this$0 = ContentResolver.this;
            super(parcelfiledescriptor);
            mContentProvider = icontentprovider;
        }
    }


    public ContentResolver(Context context)
    {
        if(context == null)
            context = ActivityThread.currentApplication();
        mContext = context;
        mPackageName = mContext.getOpPackageName();
        mTargetSdkVersion = mContext.getApplicationInfo().targetSdkVersion;
    }

    public static void addPeriodicSync(Account account, String s, Bundle bundle, long l)
    {
        validateSyncExtrasBundle(bundle);
        if(bundle.getBoolean("force", false) || bundle.getBoolean("do_not_retry", false) || bundle.getBoolean("ignore_backoff", false) || bundle.getBoolean("ignore_settings", false) || bundle.getBoolean("initialize", false) || bundle.getBoolean("force", false) || bundle.getBoolean("expedited", false))
            throw new IllegalArgumentException("illegal extras were set");
        getContentService().addPeriodicSync(account, s, bundle, l);
_L2:
        return;
        account;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static Object addStatusChangeListener(int i, SyncStatusObserver syncstatusobserver)
    {
        if(syncstatusobserver == null)
            throw new IllegalArgumentException("you passed in a null callback");
        ISyncStatusObserver.Stub stub;
        try
        {
            stub = JVM INSTR new #6   <Class ContentResolver$1>;
            stub._cls1(syncstatusobserver);
            getContentService().addStatusChangeListener(i, stub);
        }
        // Misplaced declaration of an exception variable
        catch(SyncStatusObserver syncstatusobserver)
        {
            throw new RuntimeException("the ContentService should always be reachable", syncstatusobserver);
        }
        return stub;
    }

    public static void cancelSync(Account account, String s)
    {
        getContentService().cancelSync(account, s, null);
_L2:
        return;
        account;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static void cancelSync(SyncRequest syncrequest)
    {
        if(syncrequest == null)
            throw new IllegalArgumentException("request cannot be null");
        getContentService().cancelRequest(syncrequest);
_L2:
        return;
        syncrequest;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static void cancelSyncAsUser(Account account, String s, int i)
    {
        getContentService().cancelSyncAsUser(account, s, null, i);
_L2:
        return;
        account;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static Bundle createSqlQueryBundle(String s, String as[], String s1)
    {
        if(s == null && as == null && s1 == null)
            return null;
        Bundle bundle = new Bundle();
        if(s != null)
            bundle.putString("android:query-arg-sql-selection", s);
        if(as != null)
            bundle.putStringArray("android:query-arg-sql-selection-args", as);
        if(s1 != null)
            bundle.putString("android:query-arg-sql-sort-order", s1);
        return bundle;
    }

    public static String createSqlSortClause(Bundle bundle)
    {
        String s;
        int j;
label0:
        {
            String as[] = bundle.getStringArray("android:query-arg-sort-columns");
            if(as == null || as.length == 0)
                throw new IllegalArgumentException("Can't create sort clause without columns.");
            String s1 = TextUtils.join(", ", as);
            int i = bundle.getInt("android:query-arg-sort-collation", 3);
            if(i != 0)
            {
                s = s1;
                if(i != 1)
                    break label0;
            }
            s = (new StringBuilder()).append(s1).append(" COLLATE NOCASE").toString();
        }
        j = bundle.getInt("android:query-arg-sort-direction", 0x80000000);
        bundle = s;
        if(j == 0x80000000) goto _L2; else goto _L1
_L1:
        j;
        JVM INSTR tableswitch 0 1: default 116
    //                   0 127
    //                   1 150;
           goto _L3 _L4 _L5
_L3:
        throw new IllegalArgumentException("Unsupported sort direction value. See ContentResolver documentation for details.");
_L4:
        bundle = (new StringBuilder()).append(s).append(" ASC").toString();
_L2:
        return bundle;
_L5:
        bundle = (new StringBuilder()).append(s).append(" DESC").toString();
        if(true) goto _L2; else goto _L6
_L6:
    }

    public static IContentService getContentService()
    {
        if(sContentService != null)
        {
            return sContentService;
        } else
        {
            sContentService = IContentService.Stub.asInterface(ServiceManager.getService("content"));
            return sContentService;
        }
    }

    public static SyncInfo getCurrentSync()
    {
        Object obj = getContentService().getCurrentSyncs();
        if(((List) (obj)).isEmpty())
            return null;
        try
        {
            obj = (SyncInfo)((List) (obj)).get(0);
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException("the ContentService should always be reachable", remoteexception);
        }
        return ((SyncInfo) (obj));
    }

    public static List getCurrentSyncs()
    {
        List list;
        try
        {
            list = getContentService().getCurrentSyncs();
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException("the ContentService should always be reachable", remoteexception);
        }
        return list;
    }

    public static List getCurrentSyncsAsUser(int i)
    {
        List list;
        try
        {
            list = getContentService().getCurrentSyncsAsUser(i);
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException("the ContentService should always be reachable", remoteexception);
        }
        return list;
    }

    public static int getIsSyncable(Account account, String s)
    {
        int i;
        try
        {
            i = getContentService().getIsSyncable(account, s);
        }
        // Misplaced declaration of an exception variable
        catch(Account account)
        {
            throw new RuntimeException("the ContentService should always be reachable", account);
        }
        return i;
    }

    public static int getIsSyncableAsUser(Account account, String s, int i)
    {
        try
        {
            i = getContentService().getIsSyncableAsUser(account, s, i);
        }
        // Misplaced declaration of an exception variable
        catch(Account account)
        {
            throw new RuntimeException("the ContentService should always be reachable", account);
        }
        return i;
    }

    public static boolean getMasterSyncAutomatically()
    {
        boolean flag;
        try
        {
            flag = getContentService().getMasterSyncAutomatically();
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException("the ContentService should always be reachable", remoteexception);
        }
        return flag;
    }

    public static boolean getMasterSyncAutomaticallyAsUser(int i)
    {
        boolean flag;
        try
        {
            flag = getContentService().getMasterSyncAutomaticallyAsUser(i);
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException("the ContentService should always be reachable", remoteexception);
        }
        return flag;
    }

    public static List getPeriodicSyncs(Account account, String s)
    {
        try
        {
            account = getContentService().getPeriodicSyncs(account, s, null);
        }
        // Misplaced declaration of an exception variable
        catch(Account account)
        {
            throw new RuntimeException("the ContentService should always be reachable", account);
        }
        return account;
    }

    public static String[] getSyncAdapterPackagesForAuthorityAsUser(String s, int i)
    {
        try
        {
            s = getContentService().getSyncAdapterPackagesForAuthorityAsUser(s, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return (String[])ArrayUtils.emptyArray(java/lang/String);
        }
        return s;
    }

    public static SyncAdapterType[] getSyncAdapterTypes()
    {
        SyncAdapterType asyncadaptertype[];
        try
        {
            asyncadaptertype = getContentService().getSyncAdapterTypes();
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException("the ContentService should always be reachable", remoteexception);
        }
        return asyncadaptertype;
    }

    public static SyncAdapterType[] getSyncAdapterTypesAsUser(int i)
    {
        SyncAdapterType asyncadaptertype[];
        try
        {
            asyncadaptertype = getContentService().getSyncAdapterTypesAsUser(i);
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException("the ContentService should always be reachable", remoteexception);
        }
        return asyncadaptertype;
    }

    public static boolean getSyncAutomatically(Account account, String s)
    {
        boolean flag;
        try
        {
            flag = getContentService().getSyncAutomatically(account, s);
        }
        // Misplaced declaration of an exception variable
        catch(Account account)
        {
            throw new RuntimeException("the ContentService should always be reachable", account);
        }
        return flag;
    }

    public static boolean getSyncAutomaticallyAsUser(Account account, String s, int i)
    {
        boolean flag;
        try
        {
            flag = getContentService().getSyncAutomaticallyAsUser(account, s, i);
        }
        // Misplaced declaration of an exception variable
        catch(Account account)
        {
            throw new RuntimeException("the ContentService should always be reachable", account);
        }
        return flag;
    }

    public static SyncStatusInfo getSyncStatus(Account account, String s)
    {
        try
        {
            account = getContentService().getSyncStatus(account, s, null);
        }
        // Misplaced declaration of an exception variable
        catch(Account account)
        {
            throw new RuntimeException("the ContentService should always be reachable", account);
        }
        return account;
    }

    public static SyncStatusInfo getSyncStatusAsUser(Account account, String s, int i)
    {
        try
        {
            account = getContentService().getSyncStatusAsUser(account, s, null, i);
        }
        // Misplaced declaration of an exception variable
        catch(Account account)
        {
            throw new RuntimeException("the ContentService should always be reachable", account);
        }
        return account;
    }

    public static boolean invalidPeriodicExtras(Bundle bundle)
    {
        return bundle.getBoolean("force", false) || bundle.getBoolean("do_not_retry", false) || bundle.getBoolean("ignore_backoff", false) || bundle.getBoolean("ignore_settings", false) || bundle.getBoolean("initialize", false) || bundle.getBoolean("force", false) || bundle.getBoolean("expedited", false);
    }

    public static boolean isSyncActive(Account account, String s)
    {
        if(account == null)
            throw new IllegalArgumentException("account must not be null");
        if(s == null)
            throw new IllegalArgumentException("authority must not be null");
        boolean flag;
        try
        {
            flag = getContentService().isSyncActive(account, s, null);
        }
        // Misplaced declaration of an exception variable
        catch(Account account)
        {
            throw new RuntimeException("the ContentService should always be reachable", account);
        }
        return flag;
    }

    public static boolean isSyncPending(Account account, String s)
    {
        return isSyncPendingAsUser(account, s, UserHandle.myUserId());
    }

    public static boolean isSyncPendingAsUser(Account account, String s, int i)
    {
        boolean flag;
        try
        {
            flag = getContentService().isSyncPendingAsUser(account, s, null, i);
        }
        // Misplaced declaration of an exception variable
        catch(Account account)
        {
            throw new RuntimeException("the ContentService should always be reachable", account);
        }
        return flag;
    }

    private void maybeLogQueryToEventLog(long l, Uri uri, String as[], Bundle bundle)
    {
    }

    private void maybeLogUpdateToEventLog(long l, Uri uri, String s, String s1)
    {
    }

    public static void removePeriodicSync(Account account, String s, Bundle bundle)
    {
        validateSyncExtrasBundle(bundle);
        try
        {
            getContentService().removePeriodicSync(account, s, bundle);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Account account)
        {
            throw new RuntimeException("the ContentService should always be reachable", account);
        }
    }

    public static void removeStatusChangeListener(Object obj)
    {
        if(obj == null)
            throw new IllegalArgumentException("you passed in a null handle");
        getContentService().removeStatusChangeListener((ISyncStatusObserver.Stub)obj);
_L2:
        return;
        obj;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static void requestSync(Account account, String s, Bundle bundle)
    {
        requestSyncAsUser(account, s, UserHandle.myUserId(), bundle);
    }

    public static void requestSync(SyncRequest syncrequest)
    {
        getContentService().sync(syncrequest);
_L2:
        return;
        syncrequest;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static void requestSyncAsUser(Account account, String s, int i, Bundle bundle)
    {
        if(bundle == null)
            throw new IllegalArgumentException("Must specify extras.");
        account = (new SyncRequest.Builder()).setSyncAdapter(account, s).setExtras(bundle).syncOnce().build();
        getContentService().syncAsUser(account, i);
_L2:
        return;
        account;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private int samplePercentForDuration(long l)
    {
        if(l >= 500L)
            return 100;
        else
            return (int)((100L * l) / 500L) + 1;
    }

    public static void setIsSyncable(Account account, String s, int i)
    {
        getContentService().setIsSyncable(account, s, i);
_L2:
        return;
        account;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static void setMasterSyncAutomatically(boolean flag)
    {
        setMasterSyncAutomaticallyAsUser(flag, UserHandle.myUserId());
    }

    public static void setMasterSyncAutomaticallyAsUser(boolean flag, int i)
    {
        getContentService().setMasterSyncAutomaticallyAsUser(flag, i);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static void setSyncAutomatically(Account account, String s, boolean flag)
    {
        setSyncAutomaticallyAsUser(account, s, flag, UserHandle.myUserId());
    }

    public static void setSyncAutomaticallyAsUser(Account account, String s, boolean flag, int i)
    {
        getContentService().setSyncAutomaticallyAsUser(account, s, flag, i);
_L2:
        return;
        account;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static int syncErrorStringToInt(String s)
    {
        int i = 0;
        for(int k = SYNC_ERROR_NAMES.length; i < k; i++)
            if(SYNC_ERROR_NAMES[i].equals(s))
                return i + 1;

        if(s == null)
            break MISSING_BLOCK_LABEL_72;
        int j = Integer.parseInt(s);
        return j;
        NumberFormatException numberformatexception;
        numberformatexception;
        Log.d("ContentResolver", (new StringBuilder()).append("error parsing sync error: ").append(s).toString());
        return 0;
    }

    public static String syncErrorToString(int i)
    {
        if(i < 1 || i > SYNC_ERROR_NAMES.length)
            return String.valueOf(i);
        else
            return SYNC_ERROR_NAMES[i - 1];
    }

    public static void validateSyncExtrasBundle(Bundle bundle)
    {
        Iterator iterator = bundle.keySet().iterator();
_L2:
        Object obj;
        do
        {
            if(!iterator.hasNext())
                break MISSING_BLOCK_LABEL_140;
            obj = bundle.get((String)iterator.next());
        } while(obj == null);
        if((obj instanceof Long) || (obj instanceof Integer) || (obj instanceof Boolean) || (obj instanceof Float) || (obj instanceof Double) || (obj instanceof String) || (obj instanceof Account)) goto _L2; else goto _L1
_L1:
        IllegalArgumentException illegalargumentexception = JVM INSTR new #249 <Class IllegalArgumentException>;
        bundle = JVM INSTR new #326 <Class StringBuilder>;
        bundle.StringBuilder();
        illegalargumentexception.IllegalArgumentException(bundle.append("unexpected value type: ").append(obj.getClass().getName()).toString());
        throw illegalargumentexception;
        bundle;
        throw bundle;
        bundle;
        throw new IllegalArgumentException("error unparceling Bundle", bundle);
    }

    public final ContentProviderClient acquireContentProviderClient(Uri uri)
    {
        Preconditions.checkNotNull(uri, "uri");
        uri = acquireProvider(uri);
        if(uri != null)
            return new ContentProviderClient(this, uri, true);
        else
            return null;
    }

    public final ContentProviderClient acquireContentProviderClient(String s)
    {
        Preconditions.checkNotNull(s, "name");
        s = acquireProvider(s);
        if(s != null)
            return new ContentProviderClient(this, s, true);
        else
            return null;
    }

    protected IContentProvider acquireExistingProvider(Context context, String s)
    {
        return acquireProvider(context, s);
    }

    public final IContentProvider acquireExistingProvider(Uri uri)
    {
        if(!"content".equals(uri.getScheme()))
            return null;
        uri = uri.getAuthority();
        if(uri != null)
            return acquireExistingProvider(mContext, ((String) (uri)));
        else
            return null;
    }

    protected abstract IContentProvider acquireProvider(Context context, String s);

    public final IContentProvider acquireProvider(Uri uri)
    {
        if(!"content".equals(uri.getScheme()))
            return null;
        uri = uri.getAuthority();
        if(uri != null)
            return acquireProvider(mContext, ((String) (uri)));
        else
            return null;
    }

    public final IContentProvider acquireProvider(String s)
    {
        if(s == null)
            return null;
        else
            return acquireProvider(mContext, s);
    }

    public final ContentProviderClient acquireUnstableContentProviderClient(Uri uri)
    {
        Preconditions.checkNotNull(uri, "uri");
        uri = acquireUnstableProvider(uri);
        if(uri != null)
            return new ContentProviderClient(this, uri, false);
        else
            return null;
    }

    public final ContentProviderClient acquireUnstableContentProviderClient(String s)
    {
        Preconditions.checkNotNull(s, "name");
        s = acquireUnstableProvider(s);
        if(s != null)
            return new ContentProviderClient(this, s, false);
        else
            return null;
    }

    protected abstract IContentProvider acquireUnstableProvider(Context context, String s);

    public final IContentProvider acquireUnstableProvider(Uri uri)
    {
        if(!"content".equals(uri.getScheme()))
            return null;
        if(uri.getAuthority() != null)
            return acquireUnstableProvider(mContext, uri.getAuthority());
        else
            return null;
    }

    public final IContentProvider acquireUnstableProvider(String s)
    {
        if(s == null)
            return null;
        else
            return acquireUnstableProvider(mContext, s);
    }

    public void appNotRespondingViaProvider(IContentProvider icontentprovider)
    {
        throw new UnsupportedOperationException("appNotRespondingViaProvider");
    }

    public ContentProviderResult[] applyBatch(String s, ArrayList arraylist)
        throws RemoteException, OperationApplicationException
    {
        ContentProviderClient contentproviderclient;
        Preconditions.checkNotNull(s, "authority");
        Preconditions.checkNotNull(arraylist, "operations");
        contentproviderclient = acquireContentProviderClient(s);
        if(contentproviderclient == null)
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown authority ").append(s).toString());
        s = contentproviderclient.applyBatch(arraylist);
        contentproviderclient.release();
        return s;
        s;
        contentproviderclient.release();
        throw s;
    }

    public final int bulkInsert(Uri uri, ContentValues acontentvalues[])
    {
        IContentProvider icontentprovider;
        Preconditions.checkNotNull(uri, "url");
        Preconditions.checkNotNull(acontentvalues, "values");
        icontentprovider = acquireProvider(uri);
        if(icontentprovider == null)
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown URL ").append(uri).toString());
        int i;
        try
        {
            long l = SystemClock.uptimeMillis();
            i = icontentprovider.bulkInsert(mPackageName, uri, acontentvalues);
            maybeLogUpdateToEventLog(SystemClock.uptimeMillis() - l, uri, "bulkinsert", null);
        }
        // Misplaced declaration of an exception variable
        catch(Uri uri)
        {
            releaseProvider(icontentprovider);
            return 0;
        }
        releaseProvider(icontentprovider);
        return i;
        uri;
        releaseProvider(icontentprovider);
        throw uri;
    }

    public final Bundle call(Uri uri, String s, String s1, Bundle bundle)
    {
        IContentProvider icontentprovider;
        Preconditions.checkNotNull(uri, "uri");
        Preconditions.checkNotNull(s, "method");
        icontentprovider = acquireProvider(uri);
        if(icontentprovider == null)
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown URI ").append(uri).toString());
        try
        {
            uri = icontentprovider.call(mPackageName, s, s1, bundle);
            Bundle.setDefusable(uri, true);
        }
        // Misplaced declaration of an exception variable
        catch(Uri uri)
        {
            releaseProvider(icontentprovider);
            return null;
        }
        releaseProvider(icontentprovider);
        return uri;
        uri;
        releaseProvider(icontentprovider);
        throw uri;
    }

    public void cancelSync(Uri uri)
    {
        if(uri != null)
            uri = uri.getAuthority();
        else
            uri = null;
        cancelSync(null, ((String) (uri)));
    }

    public final Uri canonicalize(Uri uri)
    {
        IContentProvider icontentprovider;
        Preconditions.checkNotNull(uri, "url");
        icontentprovider = acquireProvider(uri);
        if(icontentprovider == null)
            return null;
        try
        {
            uri = icontentprovider.canonicalize(mPackageName, uri);
        }
        // Misplaced declaration of an exception variable
        catch(Uri uri)
        {
            releaseProvider(icontentprovider);
            return null;
        }
        releaseProvider(icontentprovider);
        return uri;
        uri;
        releaseProvider(icontentprovider);
        throw uri;
    }

    public final int delete(Uri uri, String s, String as[])
    {
        IContentProvider icontentprovider;
        Preconditions.checkNotNull(uri, "url");
        icontentprovider = acquireProvider(uri);
        if(icontentprovider == null)
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown URL ").append(uri).toString());
        int i;
        try
        {
            long l = SystemClock.uptimeMillis();
            i = icontentprovider.delete(mPackageName, uri, s, as);
            maybeLogUpdateToEventLog(SystemClock.uptimeMillis() - l, uri, "delete", s);
        }
        // Misplaced declaration of an exception variable
        catch(Uri uri)
        {
            releaseProvider(icontentprovider);
            return -1;
        }
        releaseProvider(icontentprovider);
        return i;
        uri;
        releaseProvider(icontentprovider);
        throw uri;
    }

    public Bundle getCache(Uri uri)
    {
        try
        {
            uri = getContentService().getCache(mContext.getPackageName(), uri, mContext.getUserId());
        }
        // Misplaced declaration of an exception variable
        catch(Uri uri)
        {
            throw uri.rethrowFromSystemServer();
        }
        if(uri == null)
            break MISSING_BLOCK_LABEL_39;
        uri.setClassLoader(mContext.getClassLoader());
        return uri;
    }

    public List getOutgoingPersistedUriPermissions()
    {
        List list;
        try
        {
            list = ActivityManager.getService().getPersistedUriPermissions(mPackageName, false).getList();
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException("Activity manager has died", remoteexception);
        }
        return list;
    }

    public String getPackageName()
    {
        return mPackageName;
    }

    public List getPersistedUriPermissions()
    {
        List list;
        try
        {
            list = ActivityManager.getService().getPersistedUriPermissions(mPackageName, true).getList();
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException("Activity manager has died", remoteexception);
        }
        return list;
    }

    public OpenResourceIdResult getResourceId(Uri uri)
        throws FileNotFoundException
    {
        String s = uri.getAuthority();
        if(TextUtils.isEmpty(s))
            throw new FileNotFoundException((new StringBuilder()).append("No authority: ").append(uri).toString());
        Object obj;
        List list;
        try
        {
            obj = mContext.getPackageManager().getResourcesForApplication(s);
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            throw new FileNotFoundException((new StringBuilder()).append("No package found for authority: ").append(uri).toString());
        }
        list = uri.getPathSegments();
        if(list == null)
            throw new FileNotFoundException((new StringBuilder()).append("No path: ").append(uri).toString());
        int i = list.size();
        if(i == 1)
            try
            {
                i = Integer.parseInt((String)list.get(0));
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                throw new FileNotFoundException((new StringBuilder()).append("Single path segment is not a resource ID: ").append(uri).toString());
            }
        else
        if(i == 2)
            i = ((Resources) (obj)).getIdentifier((String)list.get(1), (String)list.get(0), s);
        else
            throw new FileNotFoundException((new StringBuilder()).append("More than two path segments: ").append(uri).toString());
        if(i == 0)
        {
            throw new FileNotFoundException((new StringBuilder()).append("No resource found for: ").append(uri).toString());
        } else
        {
            uri = new OpenResourceIdResult();
            uri.r = ((Resources) (obj));
            uri.id = i;
            return uri;
        }
    }

    public String[] getStreamTypes(Uri uri, String s)
    {
        IContentProvider icontentprovider;
        Preconditions.checkNotNull(uri, "url");
        Preconditions.checkNotNull(s, "mimeTypeFilter");
        icontentprovider = acquireProvider(uri);
        if(icontentprovider == null)
            return null;
        try
        {
            uri = icontentprovider.getStreamTypes(uri, s);
        }
        // Misplaced declaration of an exception variable
        catch(Uri uri)
        {
            releaseProvider(icontentprovider);
            return null;
        }
        releaseProvider(icontentprovider);
        return uri;
        uri;
        releaseProvider(icontentprovider);
        throw uri;
    }

    public int getTargetSdkVersion()
    {
        return mTargetSdkVersion;
    }

    public final String getType(Uri uri)
    {
        IContentProvider icontentprovider;
        Preconditions.checkNotNull(uri, "url");
        icontentprovider = acquireExistingProvider(uri);
        if(icontentprovider == null)
            break MISSING_BLOCK_LABEL_111;
        String s1 = icontentprovider.getType(uri);
        releaseProvider(icontentprovider);
        return s1;
        Exception exception1;
        exception1;
        StringBuilder stringbuilder = JVM INSTR new #326 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.w("ContentResolver", stringbuilder.append("Failed to get type for: ").append(uri).append(" (").append(exception1.getMessage()).append(")").toString());
        releaseProvider(icontentprovider);
        return null;
        uri;
        releaseProvider(icontentprovider);
        return null;
        uri;
        releaseProvider(icontentprovider);
        throw uri;
        if(!"content".equals(uri.getScheme()))
            return null;
        String s;
        try
        {
            s = ActivityManager.getService().getProviderMimeType(ContentProvider.getUriWithoutUserId(uri), resolveUserId(uri));
        }
        // Misplaced declaration of an exception variable
        catch(Uri uri)
        {
            return null;
        }
        catch(Exception exception)
        {
            Log.w("ContentResolver", (new StringBuilder()).append("Failed to get type for: ").append(uri).append(" (").append(exception.getMessage()).append(")").toString());
            return null;
        }
        return s;
    }

    public Drawable getTypeDrawable(String s)
    {
        return MimeIconUtils.loadMimeIcon(mContext, s);
    }

    public final Uri insert(Uri uri, ContentValues contentvalues)
    {
        IContentProvider icontentprovider;
        SeempLog.record_uri(37, uri);
        Preconditions.checkNotNull(uri, "url");
        icontentprovider = acquireProvider(uri);
        if(icontentprovider == null)
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown URL ").append(uri).toString());
        try
        {
            long l = SystemClock.uptimeMillis();
            contentvalues = icontentprovider.insert(mPackageName, uri, contentvalues);
            maybeLogUpdateToEventLog(SystemClock.uptimeMillis() - l, uri, "insert", null);
        }
        // Misplaced declaration of an exception variable
        catch(Uri uri)
        {
            releaseProvider(icontentprovider);
            return null;
        }
        releaseProvider(icontentprovider);
        return contentvalues;
        uri;
        releaseProvider(icontentprovider);
        throw uri;
    }

    public void notifyChange(Uri uri, ContentObserver contentobserver)
    {
        notifyChange(uri, contentobserver, true);
    }

    public void notifyChange(Uri uri, ContentObserver contentobserver, int i)
    {
        Preconditions.checkNotNull(uri, "uri");
        notifyChange(ContentProvider.getUriWithoutUserId(uri), contentobserver, i, ContentProvider.getUserIdFromUri(uri, mContext.getUserId()));
    }

    public void notifyChange(Uri uri, ContentObserver contentobserver, int i, int j)
    {
        android.database.IContentObserver icontentobserver = null;
        IContentService icontentservice = getContentService();
        if(contentobserver != null) goto _L2; else goto _L1
_L1:
        if(contentobserver == null) goto _L4; else goto _L3
_L3:
        boolean flag = contentobserver.deliverSelfNotifications();
_L5:
        icontentservice.notifyChange(uri, icontentobserver, flag, i, j, mTargetSdkVersion);
_L6:
        return;
_L2:
        icontentobserver = contentobserver.getContentObserver();
          goto _L1
_L4:
        flag = false;
          goto _L5
        uri;
          goto _L6
    }

    public void notifyChange(Uri uri, ContentObserver contentobserver, boolean flag)
    {
        Preconditions.checkNotNull(uri, "uri");
        notifyChange(ContentProvider.getUriWithoutUserId(uri), contentobserver, flag, ContentProvider.getUserIdFromUri(uri, mContext.getUserId()));
    }

    public void notifyChange(Uri uri, ContentObserver contentobserver, boolean flag, int i)
    {
        int j;
        android.database.IContentObserver icontentobserver;
        j = 0;
        icontentobserver = null;
        IContentService icontentservice = getContentService();
        if(contentobserver != null) goto _L2; else goto _L1
_L1:
        if(contentobserver == null) goto _L4; else goto _L3
_L3:
        boolean flag1 = contentobserver.deliverSelfNotifications();
_L5:
        if(flag)
            j = 1;
        icontentservice.notifyChange(uri, icontentobserver, flag1, j, i, mTargetSdkVersion);
_L6:
        return;
_L2:
        icontentobserver = contentobserver.getContentObserver();
          goto _L1
_L4:
        flag1 = false;
          goto _L5
        uri;
          goto _L6
    }

    public final AssetFileDescriptor openAssetFileDescriptor(Uri uri, String s)
        throws FileNotFoundException
    {
        return openAssetFileDescriptor(uri, s, null);
    }

    public final AssetFileDescriptor openAssetFileDescriptor(Uri uri, String s, CancellationSignal cancellationsignal)
        throws FileNotFoundException
    {
        Object obj;
        IContentProvider icontentprovider;
        ParcelFileDescriptorInner parcelfiledescriptorinner;
        IContentProvider icontentprovider1;
        Object obj1;
        Object obj2;
        Object obj3;
        Object obj4;
        IContentProvider icontentprovider2;
        IContentProvider icontentprovider3;
        Object obj5;
        IContentProvider icontentprovider4;
        Preconditions.checkNotNull(uri, "uri");
        Preconditions.checkNotNull(s, "mode");
        obj = uri.getScheme();
        if("android.resource".equals(obj))
        {
            if(!"r".equals(s))
                throw new FileNotFoundException((new StringBuilder()).append("Can't write resources: ").append(uri).toString());
            s = getResourceId(uri);
            try
            {
                s = ((OpenResourceIdResult) (s)).r.openRawResourceFd(((OpenResourceIdResult) (s)).id);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                throw new FileNotFoundException((new StringBuilder()).append("Resource does not exist: ").append(uri).toString());
            }
            return s;
        }
        if("file".equals(obj))
            return new AssetFileDescriptor(ParcelFileDescriptor.open(new File(uri.getPath()), ParcelFileDescriptor.parseMode(s)), 0L, -1L);
        if("r".equals(s))
            return openTypedAssetFileDescriptor(uri, "*/*", null, cancellationsignal);
        icontentprovider = acquireUnstableProvider(uri);
        if(icontentprovider == null)
            throw new FileNotFoundException((new StringBuilder()).append("No content provider: ").append(uri).toString());
        parcelfiledescriptorinner = null;
        icontentprovider1 = null;
        obj1 = null;
        obj2 = null;
        obj3 = null;
        if(cancellationsignal == null)
            break MISSING_BLOCK_LABEL_330;
        obj4 = obj2;
        icontentprovider2 = icontentprovider;
        obj = parcelfiledescriptorinner;
        icontentprovider3 = icontentprovider;
        obj5 = obj1;
        icontentprovider4 = icontentprovider;
        cancellationsignal.throwIfCanceled();
        obj4 = obj2;
        icontentprovider2 = icontentprovider;
        obj = parcelfiledescriptorinner;
        icontentprovider3 = icontentprovider;
        obj5 = obj1;
        icontentprovider4 = icontentprovider;
        obj3 = icontentprovider.createCancellationSignal();
        obj4 = obj2;
        icontentprovider2 = icontentprovider;
        obj = parcelfiledescriptorinner;
        icontentprovider3 = icontentprovider;
        obj5 = obj1;
        icontentprovider4 = icontentprovider;
        cancellationsignal.setRemote(((android.os.ICancellationSignal) (obj3)));
        obj4 = obj2;
        icontentprovider2 = icontentprovider;
        obj = parcelfiledescriptorinner;
        icontentprovider3 = icontentprovider;
        obj5 = obj1;
        icontentprovider4 = icontentprovider;
        AssetFileDescriptor assetfiledescriptor = icontentprovider.openAssetFile(mPackageName, uri, s, ((android.os.ICancellationSignal) (obj3)));
        s = assetfiledescriptor;
        obj3 = s;
        obj5 = icontentprovider1;
        if(s == null)
        {
            if(cancellationsignal != null)
                cancellationsignal.setRemote(null);
            if(icontentprovider != null)
                releaseUnstableProvider(icontentprovider);
            return null;
        }
        break MISSING_BLOCK_LABEL_829;
        obj;
        obj4 = obj2;
        icontentprovider2 = icontentprovider;
        obj = parcelfiledescriptorinner;
        icontentprovider3 = icontentprovider;
        obj5 = obj1;
        icontentprovider4 = icontentprovider;
        unstableProviderDied(icontentprovider);
        obj4 = obj2;
        icontentprovider2 = icontentprovider;
        obj = parcelfiledescriptorinner;
        icontentprovider3 = icontentprovider;
        obj5 = obj1;
        icontentprovider4 = icontentprovider;
        icontentprovider1 = acquireProvider(uri);
        if(icontentprovider1 != null)
            break MISSING_BLOCK_LABEL_743;
        obj4 = icontentprovider1;
        icontentprovider2 = icontentprovider;
        obj = icontentprovider1;
        icontentprovider3 = icontentprovider;
        obj5 = icontentprovider1;
        icontentprovider4 = icontentprovider;
        obj3 = JVM INSTR new #788 <Class FileNotFoundException>;
        obj4 = icontentprovider1;
        icontentprovider2 = icontentprovider;
        obj = icontentprovider1;
        icontentprovider3 = icontentprovider;
        obj5 = icontentprovider1;
        icontentprovider4 = icontentprovider;
        s = JVM INSTR new #326 <Class StringBuilder>;
        obj4 = icontentprovider1;
        icontentprovider2 = icontentprovider;
        obj = icontentprovider1;
        icontentprovider3 = icontentprovider;
        obj5 = icontentprovider1;
        icontentprovider4 = icontentprovider;
        s.StringBuilder();
        obj4 = icontentprovider1;
        icontentprovider2 = icontentprovider;
        obj = icontentprovider1;
        icontentprovider3 = icontentprovider;
        obj5 = icontentprovider1;
        icontentprovider4 = icontentprovider;
        ((FileNotFoundException) (obj3)).FileNotFoundException(s.append("No content provider: ").append(uri).toString());
        obj4 = icontentprovider1;
        icontentprovider2 = icontentprovider;
        obj = icontentprovider1;
        icontentprovider3 = icontentprovider;
        obj5 = icontentprovider1;
        icontentprovider4 = icontentprovider;
        throw obj3;
        s;
        obj = obj4;
        icontentprovider3 = icontentprovider2;
        obj5 = JVM INSTR new #788 <Class FileNotFoundException>;
        obj = obj4;
        icontentprovider3 = icontentprovider2;
        s = JVM INSTR new #326 <Class StringBuilder>;
        obj = obj4;
        icontentprovider3 = icontentprovider2;
        s.StringBuilder();
        obj = obj4;
        icontentprovider3 = icontentprovider2;
        ((FileNotFoundException) (obj5)).FileNotFoundException(s.append("Failed opening content provider: ").append(uri).toString());
        obj = obj4;
        icontentprovider3 = icontentprovider2;
        throw obj5;
        uri;
        if(cancellationsignal != null)
            cancellationsignal.setRemote(null);
        if(obj != null)
            releaseProvider(((IContentProvider) (obj)));
        if(icontentprovider3 != null)
            releaseUnstableProvider(icontentprovider3);
        throw uri;
        obj4 = icontentprovider1;
        icontentprovider2 = icontentprovider;
        obj = icontentprovider1;
        icontentprovider3 = icontentprovider;
        obj5 = icontentprovider1;
        icontentprovider4 = icontentprovider;
        s = icontentprovider1.openAssetFile(mPackageName, uri, s, ((android.os.ICancellationSignal) (obj3)));
        obj3 = s;
        obj5 = icontentprovider1;
        if(s == null)
        {
            if(cancellationsignal != null)
                cancellationsignal.setRemote(null);
            if(icontentprovider1 != null)
                releaseProvider(icontentprovider1);
            if(icontentprovider != null)
                releaseUnstableProvider(icontentprovider);
            return null;
        }
        s = ((String) (obj5));
        if(obj5 != null)
            break MISSING_BLOCK_LABEL_863;
        obj4 = obj5;
        icontentprovider2 = icontentprovider;
        obj = obj5;
        icontentprovider3 = icontentprovider;
        icontentprovider4 = icontentprovider;
        s = acquireProvider(uri);
        obj4 = s;
        icontentprovider2 = icontentprovider;
        obj = s;
        icontentprovider3 = icontentprovider;
        obj5 = s;
        icontentprovider4 = icontentprovider;
        releaseUnstableProvider(icontentprovider);
        icontentprovider = null;
        obj2 = null;
        icontentprovider1 = null;
        obj4 = s;
        icontentprovider2 = icontentprovider1;
        obj = s;
        icontentprovider3 = icontentprovider;
        obj5 = s;
        icontentprovider4 = obj2;
        parcelfiledescriptorinner = JVM INSTR new #14  <Class ContentResolver$ParcelFileDescriptorInner>;
        obj4 = s;
        icontentprovider2 = icontentprovider1;
        obj = s;
        icontentprovider3 = icontentprovider;
        obj5 = s;
        icontentprovider4 = obj2;
        parcelfiledescriptorinner.this. ParcelFileDescriptorInner(((AssetFileDescriptor) (obj3)).getParcelFileDescriptor(), s);
        obj = null;
        obj5 = null;
        obj4 = null;
        icontentprovider2 = icontentprovider1;
        icontentprovider3 = icontentprovider;
        icontentprovider4 = obj2;
        s = new AssetFileDescriptor(parcelfiledescriptorinner, ((AssetFileDescriptor) (obj3)).getStartOffset(), ((AssetFileDescriptor) (obj3)).getDeclaredLength());
        if(cancellationsignal != null)
            cancellationsignal.setRemote(null);
        return s;
        uri;
        obj = obj5;
        icontentprovider3 = icontentprovider4;
        throw uri;
    }

    public final ParcelFileDescriptor openFileDescriptor(Uri uri, String s)
        throws FileNotFoundException
    {
        return openFileDescriptor(uri, s, null);
    }

    public final ParcelFileDescriptor openFileDescriptor(Uri uri, String s, CancellationSignal cancellationsignal)
        throws FileNotFoundException
    {
        uri = openAssetFileDescriptor(uri, s, cancellationsignal);
        if(uri == null)
            return null;
        if(uri.getDeclaredLength() < 0L)
            return uri.getParcelFileDescriptor();
        try
        {
            uri.close();
        }
        // Misplaced declaration of an exception variable
        catch(Uri uri) { }
        throw new FileNotFoundException("Not a whole file");
    }

    public final InputStream openInputStream(Uri uri)
        throws FileNotFoundException
    {
        Object obj = null;
        Preconditions.checkNotNull(uri, "uri");
        Object obj1 = uri.getScheme();
        if("android.resource".equals(obj1))
        {
            obj = getResourceId(uri);
            try
            {
                obj = ((OpenResourceIdResult) (obj)).r.openRawResource(((OpenResourceIdResult) (obj)).id);
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                throw new FileNotFoundException((new StringBuilder()).append("Resource does not exist: ").append(uri).toString());
            }
            return ((InputStream) (obj));
        }
        if("file".equals(obj1))
            return new FileInputStream(uri.getPath());
        obj1 = openAssetFileDescriptor(uri, "r", null);
        uri = ((Uri) (obj));
        if(obj1 != null)
            try
            {
                uri = ((AssetFileDescriptor) (obj1)).createInputStream();
            }
            // Misplaced declaration of an exception variable
            catch(Uri uri)
            {
                throw new FileNotFoundException("Unable to create stream");
            }
        return uri;
    }

    public final OutputStream openOutputStream(Uri uri)
        throws FileNotFoundException
    {
        return openOutputStream(uri, "w");
    }

    public final OutputStream openOutputStream(Uri uri, String s)
        throws FileNotFoundException
    {
        Object obj = null;
        s = openAssetFileDescriptor(uri, s, null);
        uri = obj;
        if(s != null)
            try
            {
                uri = s.createOutputStream();
            }
            // Misplaced declaration of an exception variable
            catch(Uri uri)
            {
                throw new FileNotFoundException("Unable to create stream");
            }
        return uri;
    }

    public final AssetFileDescriptor openTypedAssetFileDescriptor(Uri uri, String s, Bundle bundle)
        throws FileNotFoundException
    {
        return openTypedAssetFileDescriptor(uri, s, bundle, null);
    }

    public final AssetFileDescriptor openTypedAssetFileDescriptor(Uri uri, String s, Bundle bundle, CancellationSignal cancellationsignal)
        throws FileNotFoundException
    {
        IContentProvider icontentprovider;
        ParcelFileDescriptorInner parcelfiledescriptorinner;
        Object obj;
        Object obj1;
        IContentProvider icontentprovider1;
        android.os.ICancellationSignal icancellationsignal;
        IContentProvider icontentprovider2;
        Object obj2;
        IContentProvider icontentprovider3;
        Object obj3;
        IContentProvider icontentprovider4;
        Object obj4;
        Preconditions.checkNotNull(uri, "uri");
        Preconditions.checkNotNull(s, "mimeType");
        icontentprovider = acquireUnstableProvider(uri);
        if(icontentprovider == null)
            throw new FileNotFoundException((new StringBuilder()).append("No content provider: ").append(uri).toString());
        parcelfiledescriptorinner = null;
        obj = null;
        obj1 = null;
        icontentprovider1 = null;
        icancellationsignal = null;
        if(cancellationsignal == null)
            break MISSING_BLOCK_LABEL_169;
        icontentprovider2 = icontentprovider;
        obj2 = icontentprovider1;
        icontentprovider3 = icontentprovider;
        obj3 = parcelfiledescriptorinner;
        icontentprovider4 = icontentprovider;
        obj4 = obj1;
        cancellationsignal.throwIfCanceled();
        icontentprovider2 = icontentprovider;
        obj2 = icontentprovider1;
        icontentprovider3 = icontentprovider;
        obj3 = parcelfiledescriptorinner;
        icontentprovider4 = icontentprovider;
        obj4 = obj1;
        icancellationsignal = icontentprovider.createCancellationSignal();
        icontentprovider2 = icontentprovider;
        obj2 = icontentprovider1;
        icontentprovider3 = icontentprovider;
        obj3 = parcelfiledescriptorinner;
        icontentprovider4 = icontentprovider;
        obj4 = obj1;
        cancellationsignal.setRemote(icancellationsignal);
        icontentprovider2 = icontentprovider;
        obj2 = icontentprovider1;
        icontentprovider3 = icontentprovider;
        obj3 = parcelfiledescriptorinner;
        icontentprovider4 = icontentprovider;
        obj4 = obj1;
        AssetFileDescriptor assetfiledescriptor = icontentprovider.openTypedAssetFile(mPackageName, uri, s, bundle, icancellationsignal);
        s = assetfiledescriptor;
        obj4 = obj;
        bundle = s;
        if(s == null)
        {
            if(cancellationsignal != null)
                cancellationsignal.setRemote(null);
            if(icontentprovider != null)
                releaseUnstableProvider(icontentprovider);
            return null;
        }
        break MISSING_BLOCK_LABEL_668;
        obj3;
        icontentprovider2 = icontentprovider;
        obj2 = icontentprovider1;
        icontentprovider3 = icontentprovider;
        obj3 = parcelfiledescriptorinner;
        icontentprovider4 = icontentprovider;
        obj4 = obj1;
        unstableProviderDied(icontentprovider);
        icontentprovider2 = icontentprovider;
        obj2 = icontentprovider1;
        icontentprovider3 = icontentprovider;
        obj3 = parcelfiledescriptorinner;
        icontentprovider4 = icontentprovider;
        obj4 = obj1;
        icontentprovider1 = acquireProvider(uri);
        if(icontentprovider1 != null)
            break MISSING_BLOCK_LABEL_580;
        icontentprovider2 = icontentprovider;
        obj2 = icontentprovider1;
        icontentprovider3 = icontentprovider;
        obj3 = icontentprovider1;
        icontentprovider4 = icontentprovider;
        obj4 = icontentprovider1;
        bundle = JVM INSTR new #788 <Class FileNotFoundException>;
        icontentprovider2 = icontentprovider;
        obj2 = icontentprovider1;
        icontentprovider3 = icontentprovider;
        obj3 = icontentprovider1;
        icontentprovider4 = icontentprovider;
        obj4 = icontentprovider1;
        s = JVM INSTR new #326 <Class StringBuilder>;
        icontentprovider2 = icontentprovider;
        obj2 = icontentprovider1;
        icontentprovider3 = icontentprovider;
        obj3 = icontentprovider1;
        icontentprovider4 = icontentprovider;
        obj4 = icontentprovider1;
        s.StringBuilder();
        icontentprovider2 = icontentprovider;
        obj2 = icontentprovider1;
        icontentprovider3 = icontentprovider;
        obj3 = icontentprovider1;
        icontentprovider4 = icontentprovider;
        obj4 = icontentprovider1;
        bundle.FileNotFoundException(s.append("No content provider: ").append(uri).toString());
        icontentprovider2 = icontentprovider;
        obj2 = icontentprovider1;
        icontentprovider3 = icontentprovider;
        obj3 = icontentprovider1;
        icontentprovider4 = icontentprovider;
        obj4 = icontentprovider1;
        throw bundle;
        s;
        icontentprovider3 = icontentprovider2;
        obj3 = obj2;
        s = JVM INSTR new #788 <Class FileNotFoundException>;
        icontentprovider3 = icontentprovider2;
        obj3 = obj2;
        bundle = JVM INSTR new #326 <Class StringBuilder>;
        icontentprovider3 = icontentprovider2;
        obj3 = obj2;
        bundle.StringBuilder();
        icontentprovider3 = icontentprovider2;
        obj3 = obj2;
        s.FileNotFoundException(bundle.append("Failed opening content provider: ").append(uri).toString());
        icontentprovider3 = icontentprovider2;
        obj3 = obj2;
        throw s;
        uri;
        if(cancellationsignal != null)
            cancellationsignal.setRemote(null);
        if(obj3 != null)
            releaseProvider(((IContentProvider) (obj3)));
        if(icontentprovider3 != null)
            releaseUnstableProvider(icontentprovider3);
        throw uri;
        icontentprovider2 = icontentprovider;
        obj2 = icontentprovider1;
        icontentprovider3 = icontentprovider;
        obj3 = icontentprovider1;
        icontentprovider4 = icontentprovider;
        obj4 = icontentprovider1;
        s = icontentprovider1.openTypedAssetFile(mPackageName, uri, s, bundle, icancellationsignal);
        obj4 = icontentprovider1;
        bundle = s;
        if(s == null)
        {
            if(cancellationsignal != null)
                cancellationsignal.setRemote(null);
            if(icontentprovider1 != null)
                releaseProvider(icontentprovider1);
            if(icontentprovider != null)
                releaseUnstableProvider(icontentprovider);
            return null;
        }
        s = ((String) (obj4));
        if(obj4 != null)
            break MISSING_BLOCK_LABEL_702;
        icontentprovider2 = icontentprovider;
        obj2 = obj4;
        icontentprovider3 = icontentprovider;
        obj3 = obj4;
        icontentprovider4 = icontentprovider;
        s = acquireProvider(uri);
        icontentprovider2 = icontentprovider;
        obj2 = s;
        icontentprovider3 = icontentprovider;
        obj3 = s;
        icontentprovider4 = icontentprovider;
        obj4 = s;
        releaseUnstableProvider(icontentprovider);
        icontentprovider = null;
        icancellationsignal = null;
        icontentprovider1 = null;
        icontentprovider2 = icontentprovider1;
        obj2 = s;
        icontentprovider3 = icontentprovider;
        obj3 = s;
        icontentprovider4 = icancellationsignal;
        obj4 = s;
        parcelfiledescriptorinner = JVM INSTR new #14  <Class ContentResolver$ParcelFileDescriptorInner>;
        icontentprovider2 = icontentprovider1;
        obj2 = s;
        icontentprovider3 = icontentprovider;
        obj3 = s;
        icontentprovider4 = icancellationsignal;
        obj4 = s;
        parcelfiledescriptorinner.this. ParcelFileDescriptorInner(bundle.getParcelFileDescriptor(), s);
        obj3 = null;
        obj4 = null;
        obj2 = null;
        icontentprovider2 = icontentprovider1;
        icontentprovider3 = icontentprovider;
        icontentprovider4 = icancellationsignal;
        s = new AssetFileDescriptor(parcelfiledescriptorinner, bundle.getStartOffset(), bundle.getDeclaredLength());
        if(cancellationsignal != null)
            cancellationsignal.setRemote(null);
        return s;
        uri;
        icontentprovider3 = icontentprovider4;
        obj3 = obj4;
        throw uri;
    }

    public void putCache(Uri uri, Bundle bundle)
    {
        try
        {
            getContentService().putCache(mContext.getPackageName(), uri, bundle, mContext.getUserId());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Uri uri)
        {
            throw uri.rethrowFromSystemServer();
        }
    }

    public final Cursor query(Uri uri, String as[], Bundle bundle, CancellationSignal cancellationsignal)
    {
        IContentProvider icontentprovider;
        Object obj;
        Object obj1;
        IContentProvider icontentprovider1;
        Object obj2;
        Object obj3;
        IContentProvider icontentprovider2;
        Object obj4;
        Object obj5;
        Object obj6;
        SeempLog.record_uri(13, uri);
        Preconditions.checkNotNull(uri, "uri");
        icontentprovider = acquireUnstableProvider(uri);
        if(icontentprovider == null)
            return null;
        obj = null;
        obj1 = null;
        icontentprovider1 = null;
        obj2 = null;
        obj3 = null;
        icontentprovider2 = obj;
        obj4 = obj3;
        obj5 = obj1;
        obj6 = obj2;
        long l = SystemClock.uptimeMillis();
        Object obj7;
        obj7 = null;
        if(cancellationsignal == null)
            break MISSING_BLOCK_LABEL_142;
        icontentprovider2 = obj;
        obj4 = obj3;
        obj5 = obj1;
        obj6 = obj2;
        cancellationsignal.throwIfCanceled();
        icontentprovider2 = obj;
        obj4 = obj3;
        obj5 = obj1;
        obj6 = obj2;
        obj7 = icontentprovider.createCancellationSignal();
        icontentprovider2 = obj;
        obj4 = obj3;
        obj5 = obj1;
        obj6 = obj2;
        cancellationsignal.setRemote(((android.os.ICancellationSignal) (obj7)));
        icontentprovider2 = obj;
        obj4 = obj3;
        obj5 = obj1;
        obj6 = obj2;
        Cursor cursor = icontentprovider.query(mPackageName, uri, as, bundle, ((android.os.ICancellationSignal) (obj7)));
        obj7 = cursor;
_L5:
        if(obj7 == null)
        {
            if(obj7 != null)
                ((Cursor) (obj7)).close();
            if(cancellationsignal != null)
                cancellationsignal.setRemote(null);
            if(icontentprovider != null)
                releaseUnstableProvider(icontentprovider);
            if(icontentprovider1 != null)
                releaseProvider(icontentprovider1);
            return null;
        }
        break; /* Loop/switch isn't completed */
        obj5;
        icontentprovider2 = obj;
        obj4 = obj3;
        obj5 = obj1;
        obj6 = obj2;
        unstableProviderDied(icontentprovider);
        icontentprovider2 = obj;
        obj4 = obj3;
        obj5 = obj1;
        obj6 = obj2;
        if(!ContentResolverInjector.isForceAcquireUnstableProvider(mPackageName, uri)) goto _L2; else goto _L1
_L1:
        icontentprovider2 = obj;
        obj4 = obj3;
        obj5 = obj1;
        obj6 = obj2;
        try
        {
            obj7 = ContentResolverInjector.unstableQuery(this, uri, as, bundle, ((android.os.ICancellationSignal) (obj7)));
            continue; /* Loop/switch isn't completed */
        }
        // Misplaced declaration of an exception variable
        catch(Uri uri) { }
          goto _L3
_L2:
        icontentprovider2 = obj;
        obj4 = obj3;
        obj5 = obj1;
        obj6 = obj2;
        icontentprovider1 = acquireProvider(uri);
        if(icontentprovider1 == null)
        {
            if(cancellationsignal != null)
                cancellationsignal.setRemote(null);
            if(icontentprovider != null)
                releaseUnstableProvider(icontentprovider);
            if(icontentprovider1 != null)
                releaseProvider(icontentprovider1);
            return null;
        }
        icontentprovider2 = icontentprovider1;
        obj4 = obj3;
        obj5 = icontentprovider1;
        obj6 = obj2;
        obj7 = icontentprovider1.query(mPackageName, uri, as, bundle, ((android.os.ICancellationSignal) (obj7)));
        if(true) goto _L5; else goto _L4
_L4:
        icontentprovider2 = icontentprovider1;
        obj4 = obj7;
        obj5 = icontentprovider1;
        obj6 = obj7;
        ((Cursor) (obj7)).getCount();
        icontentprovider2 = icontentprovider1;
        obj4 = obj7;
        obj5 = icontentprovider1;
        obj6 = obj7;
        maybeLogQueryToEventLog(SystemClock.uptimeMillis() - l, uri, as, bundle);
        if(icontentprovider1 == null) goto _L7; else goto _L6
_L6:
        uri = icontentprovider1;
_L8:
        icontentprovider2 = icontentprovider1;
        obj4 = obj7;
        obj5 = icontentprovider1;
        obj6 = obj7;
        uri = new CursorWrapperInner(((Cursor) (obj7)), uri);
        if(cancellationsignal != null)
            cancellationsignal.setRemote(null);
        if(icontentprovider != null)
            releaseUnstableProvider(icontentprovider);
        return uri;
_L7:
        icontentprovider2 = icontentprovider1;
        obj4 = obj7;
        obj5 = icontentprovider1;
        obj6 = obj7;
        uri = acquireProvider(uri);
        if(true) goto _L8; else goto _L3
_L3:
        if(obj4 != null)
            ((Cursor) (obj4)).close();
        if(cancellationsignal != null)
            cancellationsignal.setRemote(null);
        if(icontentprovider != null)
            releaseUnstableProvider(icontentprovider);
        if(icontentprovider2 != null)
            releaseProvider(icontentprovider2);
        return null;
        uri;
        if(obj6 != null)
            ((Cursor) (obj6)).close();
        if(cancellationsignal != null)
            cancellationsignal.setRemote(null);
        if(icontentprovider != null)
            releaseUnstableProvider(icontentprovider);
        if(obj5 != null)
            releaseProvider(((IContentProvider) (obj5)));
        throw uri;
    }

    public final Cursor query(Uri uri, String as[], String s, String as1[], String s1)
    {
        SeempLog.record_uri(13, uri);
        return query(uri, as, s, as1, s1, null);
    }

    public final Cursor query(Uri uri, String as[], String s, String as1[], String s1, CancellationSignal cancellationsignal)
    {
        return query(uri, as, createSqlQueryBundle(s, as1, s1), cancellationsignal);
    }

    public final boolean refresh(Uri uri, Bundle bundle, CancellationSignal cancellationsignal)
    {
        IContentProvider icontentprovider;
        android.os.ICancellationSignal icancellationsignal;
        Preconditions.checkNotNull(uri, "url");
        icontentprovider = acquireProvider(uri);
        if(icontentprovider == null)
            return false;
        icancellationsignal = null;
        if(cancellationsignal == null)
            break MISSING_BLOCK_LABEL_48;
        cancellationsignal.throwIfCanceled();
        icancellationsignal = icontentprovider.createCancellationSignal();
        cancellationsignal.setRemote(icancellationsignal);
        boolean flag = icontentprovider.refresh(mPackageName, uri, bundle, icancellationsignal);
        releaseProvider(icontentprovider);
        return flag;
        uri;
        releaseProvider(icontentprovider);
        return false;
        uri;
        releaseProvider(icontentprovider);
        throw uri;
    }

    public final void registerContentObserver(Uri uri, boolean flag, ContentObserver contentobserver)
    {
        Preconditions.checkNotNull(uri, "uri");
        Preconditions.checkNotNull(contentobserver, "observer");
        registerContentObserver(ContentProvider.getUriWithoutUserId(uri), flag, contentobserver, ContentProvider.getUserIdFromUri(uri, mContext.getUserId()));
    }

    public final void registerContentObserver(Uri uri, boolean flag, ContentObserver contentobserver, int i)
    {
        getContentService().registerContentObserver(uri, flag, contentobserver.getContentObserver(), i, mTargetSdkVersion);
_L2:
        return;
        uri;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void releasePersistableUriPermission(Uri uri, int i)
    {
        Preconditions.checkNotNull(uri, "uri");
        ActivityManager.getService().releasePersistableUriPermission(ContentProvider.getUriWithoutUserId(uri), i, resolveUserId(uri));
_L2:
        return;
        uri;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public abstract boolean releaseProvider(IContentProvider icontentprovider);

    public abstract boolean releaseUnstableProvider(IContentProvider icontentprovider);

    public int resolveUserId(Uri uri)
    {
        return ContentProvider.getUserIdFromUri(uri, mContext.getUserId());
    }

    public void startSync(Uri uri, Bundle bundle)
    {
        String s = null;
        Account account = null;
        Object obj = null;
        if(bundle != null)
        {
            String s1 = bundle.getString("account");
            account = obj;
            if(!TextUtils.isEmpty(s1))
                account = new Account(s1, "com.google");
            bundle.remove("account");
        }
        if(uri != null)
            s = uri.getAuthority();
        requestSync(account, s, bundle);
    }

    public void takePersistableUriPermission(Uri uri, int i)
    {
        Preconditions.checkNotNull(uri, "uri");
        ActivityManager.getService().takePersistableUriPermission(ContentProvider.getUriWithoutUserId(uri), i, resolveUserId(uri));
_L2:
        return;
        uri;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public final Uri uncanonicalize(Uri uri)
    {
        IContentProvider icontentprovider;
        Preconditions.checkNotNull(uri, "url");
        icontentprovider = acquireProvider(uri);
        if(icontentprovider == null)
            return null;
        try
        {
            uri = icontentprovider.uncanonicalize(mPackageName, uri);
        }
        // Misplaced declaration of an exception variable
        catch(Uri uri)
        {
            releaseProvider(icontentprovider);
            return null;
        }
        releaseProvider(icontentprovider);
        return uri;
        uri;
        releaseProvider(icontentprovider);
        throw uri;
    }

    public final void unregisterContentObserver(ContentObserver contentobserver)
    {
        Preconditions.checkNotNull(contentobserver, "observer");
        contentobserver = contentobserver.releaseContentObserver();
        if(contentobserver == null)
            break MISSING_BLOCK_LABEL_26;
        getContentService().unregisterContentObserver(contentobserver);
_L2:
        return;
        contentobserver;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public abstract void unstableProviderDied(IContentProvider icontentprovider);

    public final int update(Uri uri, ContentValues contentvalues, String s, String as[])
    {
        IContentProvider icontentprovider;
        Preconditions.checkNotNull(uri, "uri");
        icontentprovider = acquireProvider(uri);
        if(icontentprovider == null)
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown URI ").append(uri).toString());
        int i;
        try
        {
            long l = SystemClock.uptimeMillis();
            i = icontentprovider.update(mPackageName, uri, contentvalues, s, as);
            maybeLogUpdateToEventLog(SystemClock.uptimeMillis() - l, uri, "update", s);
        }
        // Misplaced declaration of an exception variable
        catch(Uri uri)
        {
            releaseProvider(icontentprovider);
            return -1;
        }
        releaseProvider(icontentprovider);
        return i;
        uri;
        releaseProvider(icontentprovider);
        throw uri;
    }

    public static final Intent ACTION_SYNC_CONN_STATUS_CHANGED = new Intent("com.android.sync.SYNC_CONN_STATUS_CHANGED");
    public static final String ANY_CURSOR_ITEM_TYPE = "vnd.android.cursor.item/*";
    public static final String CONTENT_SERVICE_NAME = "content";
    public static final String CURSOR_DIR_BASE_TYPE = "vnd.android.cursor.dir";
    public static final String CURSOR_ITEM_BASE_TYPE = "vnd.android.cursor.item";
    private static final boolean ENABLE_CONTENT_SAMPLE = false;
    public static final String EXTRA_HONORED_ARGS = "android.content.extra.HONORED_ARGS";
    public static final String EXTRA_REFRESH_SUPPORTED = "android.content.extra.REFRESH_SUPPORTED";
    public static final String EXTRA_SIZE = "android.content.extra.SIZE";
    public static final String EXTRA_TOTAL_COUNT = "android.content.extra.TOTAL_COUNT";
    public static final int NOTIFY_SKIP_NOTIFY_FOR_DESCENDANTS = 2;
    public static final int NOTIFY_SYNC_TO_NETWORK = 1;
    public static final String QUERY_ARG_LIMIT = "android:query-arg-limit";
    public static final String QUERY_ARG_OFFSET = "android:query-arg-offset";
    public static final String QUERY_ARG_SORT_COLLATION = "android:query-arg-sort-collation";
    public static final String QUERY_ARG_SORT_COLUMNS = "android:query-arg-sort-columns";
    public static final String QUERY_ARG_SORT_DIRECTION = "android:query-arg-sort-direction";
    public static final String QUERY_ARG_SQL_SELECTION = "android:query-arg-sql-selection";
    public static final String QUERY_ARG_SQL_SELECTION_ARGS = "android:query-arg-sql-selection-args";
    public static final String QUERY_ARG_SQL_SORT_ORDER = "android:query-arg-sql-sort-order";
    public static final int QUERY_SORT_DIRECTION_ASCENDING = 0;
    public static final int QUERY_SORT_DIRECTION_DESCENDING = 1;
    public static final String SCHEME_ANDROID_RESOURCE = "android.resource";
    public static final String SCHEME_CONTENT = "content";
    public static final String SCHEME_FILE = "file";
    private static final int SLOW_THRESHOLD_MILLIS = 500;
    public static final int SYNC_ERROR_AUTHENTICATION = 2;
    public static final int SYNC_ERROR_CONFLICT = 5;
    public static final int SYNC_ERROR_INTERNAL = 8;
    public static final int SYNC_ERROR_IO = 3;
    private static final String SYNC_ERROR_NAMES[] = {
        "already-in-progress", "authentication-error", "io-error", "parse-error", "conflict", "too-many-deletions", "too-many-retries", "internal-error"
    };
    public static final int SYNC_ERROR_PARSE = 4;
    public static final int SYNC_ERROR_SYNC_ALREADY_IN_PROGRESS = 1;
    public static final int SYNC_ERROR_TOO_MANY_DELETIONS = 6;
    public static final int SYNC_ERROR_TOO_MANY_RETRIES = 7;
    public static final String SYNC_EXTRAS_ACCOUNT = "account";
    public static final String SYNC_EXTRAS_DISALLOW_METERED = "allow_metered";
    public static final String SYNC_EXTRAS_DISCARD_LOCAL_DELETIONS = "discard_deletions";
    public static final String SYNC_EXTRAS_DO_NOT_RETRY = "do_not_retry";
    public static final String SYNC_EXTRAS_EXPECTED_DOWNLOAD = "expected_download";
    public static final String SYNC_EXTRAS_EXPECTED_UPLOAD = "expected_upload";
    public static final String SYNC_EXTRAS_EXPEDITED = "expedited";
    public static final String SYNC_EXTRAS_FORCE = "force";
    public static final String SYNC_EXTRAS_IGNORE_BACKOFF = "ignore_backoff";
    public static final String SYNC_EXTRAS_IGNORE_SETTINGS = "ignore_settings";
    public static final String SYNC_EXTRAS_INITIALIZE = "initialize";
    public static final String SYNC_EXTRAS_MANUAL = "force";
    public static final String SYNC_EXTRAS_OVERRIDE_TOO_MANY_DELETIONS = "deletions_override";
    public static final String SYNC_EXTRAS_PRIORITY = "sync_priority";
    public static final String SYNC_EXTRAS_REQUIRE_CHARGING = "require_charging";
    public static final String SYNC_EXTRAS_UPLOAD = "upload";
    public static final int SYNC_OBSERVER_TYPE_ACTIVE = 4;
    public static final int SYNC_OBSERVER_TYPE_ALL = 0x7fffffff;
    public static final int SYNC_OBSERVER_TYPE_PENDING = 2;
    public static final int SYNC_OBSERVER_TYPE_SETTINGS = 1;
    public static final int SYNC_OBSERVER_TYPE_STATUS = 8;
    private static final String TAG = "ContentResolver";
    private static IContentService sContentService;
    private final Context mContext;
    final String mPackageName;
    private final Random mRandom = new Random();
    final int mTargetSdkVersion;


    // Unreferenced inner class android/content/ContentResolver$1

/* anonymous class */
    static final class _cls1 extends ISyncStatusObserver.Stub
    {

        public void onStatusChanged(int i)
            throws RemoteException
        {
            callback.onStatusChanged(i);
        }

        final SyncStatusObserver val$callback;

            
            {
                callback = syncstatusobserver;
                super();
            }
    }

}
