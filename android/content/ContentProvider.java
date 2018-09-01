// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.app.AppOpsManager;
import android.content.pm.PathPermission;
import android.content.pm.ProviderInfo;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.*;
import android.text.TextUtils;
import android.util.Log;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import miui.securityspace.XSpaceUserHandle;

// Referenced classes of package android.content:
//            ComponentCallbacks2, Context, ContentProviderInjector, OperationApplicationException, 
//            ContentProviderResult, ContentProviderOperation, ClipDescription, ContentResolver, 
//            IContentProvider, ContentValues, ContentProviderNative

public abstract class ContentProvider
    implements ComponentCallbacks2
{
    public static interface PipeDataWriter
    {

        public abstract void writeDataToPipe(ParcelFileDescriptor parcelfiledescriptor, Uri uri, String s, Bundle bundle, Object obj);
    }

    class Transport extends ContentProviderNative
    {

        private void enforceFilePermission(String s, Uri uri, String s1, IBinder ibinder)
            throws FileNotFoundException, SecurityException
        {
            if(s1 != null && s1.indexOf('w') != -1)
            {
                if(enforceWritePermission(s, uri, ibinder) != 0)
                    throw new FileNotFoundException("App op not allowed");
            } else
            if(enforceReadPermission(s, uri, ibinder) != 0)
                throw new FileNotFoundException("App op not allowed");
        }

        private int enforceReadPermission(String s, Uri uri, IBinder ibinder)
            throws SecurityException
        {
            int i = enforceReadPermissionInner(uri, s, ibinder);
            if(i != 0)
                return i;
            if(mReadOp != -1)
                return mAppOpsManager.noteProxyOp(mReadOp, s);
            else
                return 0;
        }

        private int enforceWritePermission(String s, Uri uri, IBinder ibinder)
            throws SecurityException
        {
            int i = enforceWritePermissionInner(uri, s, ibinder);
            if(i != 0)
                return i;
            if(mWriteOp != -1)
                return mAppOpsManager.noteProxyOp(mWriteOp, s);
            else
                return 0;
        }

        public ContentProviderResult[] applyBatch(String s, ArrayList arraylist)
            throws OperationApplicationException
        {
            int ai[];
            int i = arraylist.size();
            ai = new int[i];
            for(int j = 0; j < i; j++)
            {
                ContentProviderOperation contentprovideroperation = (ContentProviderOperation)arraylist.get(j);
                Uri uri = contentprovideroperation.getUri();
                ContentProvider._2D_wrap2(ContentProvider.this, uri);
                ai[j] = ContentProvider.getUserIdFromUri(uri);
                ContentProviderOperation contentprovideroperation1 = contentprovideroperation;
                if(ai[j] != -2)
                {
                    contentprovideroperation1 = new ContentProviderOperation(contentprovideroperation, true);
                    arraylist.set(j, contentprovideroperation1);
                }
                if(contentprovideroperation1.isReadOperation() && enforceReadPermission(s, uri, null) != 0)
                    throw new OperationApplicationException("App op not allowed", 0);
                if(contentprovideroperation1.isWriteOperation() && enforceWritePermission(s, uri, null) != 0)
                    throw new OperationApplicationException("App op not allowed", 0);
            }

            s = ContentProvider._2D_wrap1(ContentProvider.this, s);
            arraylist = ContentProvider.this.applyBatch(arraylist);
            if(arraylist == null) goto _L2; else goto _L1
_L1:
            int k = 0;
_L3:
            if(k >= arraylist.length)
                break; /* Loop/switch isn't completed */
            if(ai[k] == -2)
                break MISSING_BLOCK_LABEL_219;
            arraylist[k] = new ContentProviderResult(arraylist[k], ai[k]);
            k++;
            if(true) goto _L3; else goto _L2
_L2:
            ContentProvider._2D_wrap1(ContentProvider.this, s);
            return arraylist;
            arraylist;
            ContentProvider._2D_wrap1(ContentProvider.this, s);
            throw arraylist;
        }

        public int bulkInsert(String s, Uri uri, ContentValues acontentvalues[])
        {
            ContentProvider._2D_wrap2(ContentProvider.this, uri);
            uri = ContentProvider._2D_wrap0(ContentProvider.this, uri);
            if(enforceWritePermission(s, uri, null) != 0)
                return 0;
            s = ContentProvider._2D_wrap1(ContentProvider.this, s);
            int i = ContentProvider.this.bulkInsert(uri, acontentvalues);
            ContentProvider._2D_wrap1(ContentProvider.this, s);
            return i;
            uri;
            ContentProvider._2D_wrap1(ContentProvider.this, s);
            throw uri;
        }

        public Bundle call(String s, String s1, String s2, Bundle bundle)
        {
            Bundle.setDefusable(bundle, true);
            s = ContentProvider._2D_wrap1(ContentProvider.this, s);
            s1 = ContentProvider.this.call(s1, s2, bundle);
            ContentProvider._2D_wrap1(ContentProvider.this, s);
            return s1;
            s1;
            ContentProvider._2D_wrap1(ContentProvider.this, s);
            throw s1;
        }

        public Uri canonicalize(String s, Uri uri)
        {
            int i;
            ContentProvider._2D_wrap2(ContentProvider.this, uri);
            i = ContentProvider.getUserIdFromUri(uri);
            uri = ContentProvider.getUriWithoutUserId(uri);
            if(enforceReadPermission(s, uri, null) != 0)
                return null;
            s = ContentProvider._2D_wrap1(ContentProvider.this, s);
            uri = ContentProvider.maybeAddUserId(ContentProvider.this.canonicalize(uri), i);
            ContentProvider._2D_wrap1(ContentProvider.this, s);
            return uri;
            uri;
            ContentProvider._2D_wrap1(ContentProvider.this, s);
            throw uri;
        }

        public ICancellationSignal createCancellationSignal()
        {
            return CancellationSignal.createTransport();
        }

        public int delete(String s, Uri uri, String s1, String as[])
        {
            ContentProvider._2D_wrap2(ContentProvider.this, uri);
            uri = ContentProvider._2D_wrap0(ContentProvider.this, uri);
            if(enforceWritePermission(s, uri, null) != 0)
                return 0;
            s = ContentProvider._2D_wrap1(ContentProvider.this, s);
            int i = ContentProvider.this.delete(uri, s1, as);
            ContentProvider._2D_wrap1(ContentProvider.this, s);
            return i;
            uri;
            ContentProvider._2D_wrap1(ContentProvider.this, s);
            throw uri;
        }

        ContentProvider getContentProvider()
        {
            return ContentProvider.this;
        }

        public String getProviderName()
        {
            return getContentProvider().getClass().getName();
        }

        public String[] getStreamTypes(Uri uri, String s)
        {
            ContentProvider._2D_wrap2(ContentProvider.this, uri);
            uri = ContentProvider._2D_wrap0(ContentProvider.this, uri);
            return ContentProvider.this.getStreamTypes(uri, s);
        }

        public String getType(Uri uri)
        {
            ContentProvider._2D_wrap2(ContentProvider.this, uri);
            uri = ContentProvider._2D_wrap0(ContentProvider.this, uri);
            return ContentProvider.this.getType(uri);
        }

        public Uri insert(String s, Uri uri, ContentValues contentvalues)
        {
            int i;
            ContentProvider._2D_wrap2(ContentProvider.this, uri);
            i = ContentProvider.getUserIdFromUri(uri);
            uri = ContentProvider._2D_wrap0(ContentProvider.this, uri);
            if(enforceWritePermission(s, uri, null) != 0)
                return rejectInsert(uri, contentvalues);
            s = ContentProvider._2D_wrap1(ContentProvider.this, s);
            uri = ContentProvider.maybeAddUserId(ContentProvider.this.insert(uri, contentvalues), i);
            ContentProvider._2D_wrap1(ContentProvider.this, s);
            return uri;
            uri;
            ContentProvider._2D_wrap1(ContentProvider.this, s);
            throw uri;
        }

        public AssetFileDescriptor openAssetFile(String s, Uri uri, String s1, ICancellationSignal icancellationsignal)
            throws FileNotFoundException
        {
            ContentProvider._2D_wrap2(ContentProvider.this, uri);
            uri = ContentProvider._2D_wrap0(ContentProvider.this, uri);
            enforceFilePermission(s, uri, s1, null);
            s = ContentProvider._2D_wrap1(ContentProvider.this, s);
            uri = ContentProvider.this.openAssetFile(uri, s1, CancellationSignal.fromTransport(icancellationsignal));
            ContentProvider._2D_wrap1(ContentProvider.this, s);
            return uri;
            uri;
            ContentProvider._2D_wrap1(ContentProvider.this, s);
            throw uri;
        }

        public ParcelFileDescriptor openFile(String s, Uri uri, String s1, ICancellationSignal icancellationsignal, IBinder ibinder)
            throws FileNotFoundException
        {
            ContentProvider._2D_wrap2(ContentProvider.this, uri);
            uri = ContentProvider._2D_wrap0(ContentProvider.this, uri);
            enforceFilePermission(s, uri, s1, ibinder);
            s = ContentProvider._2D_wrap1(ContentProvider.this, s);
            uri = ContentProvider.this.openFile(uri, s1, CancellationSignal.fromTransport(icancellationsignal));
            ContentProvider._2D_wrap1(ContentProvider.this, s);
            return uri;
            uri;
            ContentProvider._2D_wrap1(ContentProvider.this, s);
            throw uri;
        }

        public AssetFileDescriptor openTypedAssetFile(String s, Uri uri, String s1, Bundle bundle, ICancellationSignal icancellationsignal)
            throws FileNotFoundException
        {
            Bundle.setDefusable(bundle, true);
            ContentProvider._2D_wrap2(ContentProvider.this, uri);
            uri = ContentProvider._2D_wrap0(ContentProvider.this, uri);
            enforceFilePermission(s, uri, "r", null);
            s = ContentProvider._2D_wrap1(ContentProvider.this, s);
            uri = ContentProvider.this.openTypedAssetFile(uri, s1, bundle, CancellationSignal.fromTransport(icancellationsignal));
            ContentProvider._2D_wrap1(ContentProvider.this, s);
            return uri;
            uri;
            ContentProvider._2D_wrap1(ContentProvider.this, s);
            throw uri;
        }

        public Cursor query(String s, Uri uri, String as[], Bundle bundle, ICancellationSignal icancellationsignal)
        {
            ContentProvider._2D_wrap2(ContentProvider.this, uri);
            uri = ContentProvider._2D_wrap0(ContentProvider.this, uri);
            if(enforceReadPermission(s, uri, null) != 0)
            {
                if(as != null)
                    return new MatrixCursor(as, 0);
                s = ContentProvider.this.query(uri, as, bundle, CancellationSignal.fromTransport(icancellationsignal));
                if(s == null)
                    return null;
                else
                    return new MatrixCursor(s.getColumnNames(), 0);
            }
            s = ContentProvider._2D_wrap1(ContentProvider.this, s);
            uri = ContentProvider.this.query(uri, as, bundle, CancellationSignal.fromTransport(icancellationsignal));
            ContentProvider._2D_wrap1(ContentProvider.this, s);
            return uri;
            uri;
            ContentProvider._2D_wrap1(ContentProvider.this, s);
            throw uri;
        }

        public boolean refresh(String s, Uri uri, Bundle bundle, ICancellationSignal icancellationsignal)
            throws RemoteException
        {
            ContentProvider._2D_wrap2(ContentProvider.this, uri);
            uri = ContentProvider.getUriWithoutUserId(uri);
            if(enforceReadPermission(s, uri, null) != 0)
                return false;
            s = ContentProvider._2D_wrap1(ContentProvider.this, s);
            boolean flag = ContentProvider.this.refresh(uri, bundle, CancellationSignal.fromTransport(icancellationsignal));
            ContentProvider._2D_wrap1(ContentProvider.this, s);
            return flag;
            uri;
            ContentProvider._2D_wrap1(ContentProvider.this, s);
            throw uri;
        }

        public Uri uncanonicalize(String s, Uri uri)
        {
            int i;
            ContentProvider._2D_wrap2(ContentProvider.this, uri);
            i = ContentProvider.getUserIdFromUri(uri);
            uri = ContentProvider.getUriWithoutUserId(uri);
            if(enforceReadPermission(s, uri, null) != 0)
                return null;
            s = ContentProvider._2D_wrap1(ContentProvider.this, s);
            uri = ContentProvider.maybeAddUserId(ContentProvider.this.uncanonicalize(uri), i);
            ContentProvider._2D_wrap1(ContentProvider.this, s);
            return uri;
            uri;
            ContentProvider._2D_wrap1(ContentProvider.this, s);
            throw uri;
        }

        public int update(String s, Uri uri, ContentValues contentvalues, String s1, String as[])
        {
            ContentProvider._2D_wrap2(ContentProvider.this, uri);
            uri = ContentProvider._2D_wrap0(ContentProvider.this, uri);
            if(enforceWritePermission(s, uri, null) != 0)
                return 0;
            s = ContentProvider._2D_wrap1(ContentProvider.this, s);
            int i = ContentProvider.this.update(uri, contentvalues, s1, as);
            ContentProvider._2D_wrap1(ContentProvider.this, s);
            return i;
            uri;
            ContentProvider._2D_wrap1(ContentProvider.this, s);
            throw uri;
        }

        AppOpsManager mAppOpsManager;
        int mReadOp;
        int mWriteOp;
        final ContentProvider this$0;

        Transport()
        {
            this$0 = ContentProvider.this;
            super();
            mAppOpsManager = null;
            mReadOp = -1;
            mWriteOp = -1;
        }
    }


    static Uri _2D_wrap0(ContentProvider contentprovider, Uri uri)
    {
        return contentprovider.maybeGetUriWithoutUserId(uri);
    }

    static String _2D_wrap1(ContentProvider contentprovider, String s)
    {
        return contentprovider.setCallingPackage(s);
    }

    static void _2D_wrap2(ContentProvider contentprovider, Uri uri)
    {
        contentprovider.validateIncomingUri(uri);
    }

    public ContentProvider()
    {
        mContext = null;
        mCallingPackage = new ThreadLocal();
        mTransport = new Transport();
    }

    public ContentProvider(Context context, String s, String s1, PathPermission apathpermission[])
    {
        mContext = null;
        mCallingPackage = new ThreadLocal();
        mTransport = new Transport();
        mContext = context;
        mReadPermission = s;
        mWritePermission = s1;
        mPathPermissions = apathpermission;
    }

    private void attachInfo(Context context, ProviderInfo providerinfo, boolean flag)
    {
        mNoPerms = flag;
        if(mContext == null)
        {
            mContext = context;
            if(context != null)
                mTransport.mAppOpsManager = (AppOpsManager)context.getSystemService("appops");
            mMyUid = Process.myUid();
            if(providerinfo != null)
            {
                setReadPermission(providerinfo.readPermission);
                setWritePermission(providerinfo.writePermission);
                setPathPermissions(providerinfo.pathPermissions);
                mExported = providerinfo.exported;
                if((providerinfo.flags & 0x40000000) != 0)
                    flag = true;
                else
                    flag = false;
                mSingleUser = flag;
                setAuthorities(providerinfo.authority);
            }
            onCreate();
        }
    }

    private int checkPermissionAndAppOp(String s, String s1, IBinder ibinder)
    {
        if(getContext().checkPermission(s, Binder.getCallingPid(), Binder.getCallingUid(), ibinder) != 0)
            return 2;
        int i = AppOpsManager.permissionToOpCode(s);
        int j = i;
        if(ContentProviderInjector.isMmsProviderClass(getClass().getName()))
        {
            j = i;
            if(i == 14)
                j = 10005;
        }
        if(j != -1)
            return mTransport.mAppOpsManager.noteProxyOp(j, s1);
        else
            return 0;
    }

    public static ContentProvider coerceToLocalContentProvider(IContentProvider icontentprovider)
    {
        if(icontentprovider instanceof Transport)
            return ((Transport)icontentprovider).getContentProvider();
        else
            return null;
    }

    public static String getAuthorityWithoutUserId(String s)
    {
        if(s == null)
            return null;
        else
            return s.substring(s.lastIndexOf('@') + 1);
    }

    public static Uri getUriWithoutUserId(Uri uri)
    {
        if(uri == null)
        {
            return null;
        } else
        {
            android.net.Uri.Builder builder = uri.buildUpon();
            builder.authority(getAuthorityWithoutUserId(uri.getAuthority()));
            return builder.build();
        }
    }

    public static int getUserIdFromAuthority(String s)
    {
        return getUserIdFromAuthority(s, -2);
    }

    public static int getUserIdFromAuthority(String s, int i)
    {
        if(s == null)
            return i;
        int j = s.lastIndexOf('@');
        if(j == -1)
            return i;
        s = s.substring(0, j);
        try
        {
            i = Integer.parseInt(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.w("ContentProvider", "Error parsing userId.", s);
            return -10000;
        }
        return i;
    }

    public static int getUserIdFromUri(Uri uri)
    {
        return getUserIdFromUri(uri, -2);
    }

    public static int getUserIdFromUri(Uri uri, int i)
    {
        if(uri == null)
            return i;
        else
            return getUserIdFromAuthority(uri.getAuthority(), i);
    }

    public static Uri maybeAddUserId(Uri uri, int i)
    {
        if(uri == null)
            return null;
        if(i != -2 && "content".equals(uri.getScheme()) && !uriHasUserId(uri))
        {
            android.net.Uri.Builder builder = uri.buildUpon();
            builder.encodedAuthority((new StringBuilder()).append("").append(i).append("@").append(uri.getEncodedAuthority()).toString());
            return builder.build();
        } else
        {
            return uri;
        }
    }

    private Uri maybeGetUriWithoutUserId(Uri uri)
    {
        if(mSingleUser)
            return uri;
        else
            return getUriWithoutUserId(uri);
    }

    private String setCallingPackage(String s)
    {
        String s1 = (String)mCallingPackage.get();
        mCallingPackage.set(s);
        return s1;
    }

    public static boolean uriHasUserId(Uri uri)
    {
        if(uri == null)
            return false;
        else
            return TextUtils.isEmpty(uri.getUserInfo()) ^ true;
    }

    private void validateIncomingUri(Uri uri)
        throws SecurityException
    {
        String s = uri.getAuthority();
        if(!mSingleUser)
        {
            int i = getUserIdFromAuthority(s, -2);
            if(i != -2 && i != mContext.getUserId() && ContentProviderInjector.isCrossUserIncomingUri(mContext, i) ^ true)
                throw new SecurityException((new StringBuilder()).append("trying to query a ContentProvider in user ").append(mContext.getUserId()).append(" with a uri belonging to user ").append(i).toString());
        }
        if(!matchesOurAuthorities(getAuthorityWithoutUserId(s)))
        {
            uri = (new StringBuilder()).append("The authority of the uri ").append(uri).append(" does not match the one of the ").append("contentProvider: ").toString();
            if(mAuthority != null)
                uri = (new StringBuilder()).append(uri).append(mAuthority).toString();
            else
                uri = (new StringBuilder()).append(uri).append(Arrays.toString(mAuthorities)).toString();
            throw new SecurityException(uri);
        } else
        {
            return;
        }
    }

    public ContentProviderResult[] applyBatch(ArrayList arraylist)
        throws OperationApplicationException
    {
        int i = arraylist.size();
        ContentProviderResult acontentproviderresult[] = new ContentProviderResult[i];
        for(int j = 0; j < i; j++)
            acontentproviderresult[j] = ((ContentProviderOperation)arraylist.get(j)).apply(this, acontentproviderresult, j);

        return acontentproviderresult;
    }

    public void attachInfo(Context context, ProviderInfo providerinfo)
    {
        attachInfo(context, providerinfo, false);
    }

    public void attachInfoForTesting(Context context, ProviderInfo providerinfo)
    {
        attachInfo(context, providerinfo, true);
    }

    public int bulkInsert(Uri uri, ContentValues acontentvalues[])
    {
        int i = acontentvalues.length;
        for(int j = 0; j < i; j++)
            insert(uri, acontentvalues[j]);

        return i;
    }

    public Bundle call(String s, String s1, Bundle bundle)
    {
        return null;
    }

    public Uri canonicalize(Uri uri)
    {
        return null;
    }

    boolean checkUser(int i, int j, Context context)
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = flag;
        if(UserHandle.getUserId(j) == context.getUserId()) goto _L2; else goto _L1
_L1:
        if(!XSpaceUserHandle.isUidBelongtoXSpace(j) || context.getUserId() != 0) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(!mSingleUser)
        {
            flag1 = flag;
            if(context.checkPermission("android.permission.INTERACT_ACROSS_USERS", i, j) != 0)
                flag1 = false;
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    public abstract int delete(Uri uri, String s, String as[]);

    public void dump(FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        printwriter.println("nothing to dump");
    }

    protected int enforceReadPermissionInner(Uri uri, String s, IBinder ibinder)
        throws SecurityException
    {
        Context context = getContext();
        int i = Binder.getCallingPid();
        int j = Binder.getCallingUid();
        Object obj = null;
        String s2 = null;
        boolean flag = false;
        int l = 0;
        if(UserHandle.isSameApp(j, mMyUid))
            return 0;
        String s3 = obj;
        int i1 = ((flag) ? 1 : 0);
        if(mExported)
        {
            s3 = obj;
            i1 = ((flag) ? 1 : 0);
            if(checkUser(i, j, context))
            {
                s3 = getReadPermission();
                if(s3 != null)
                {
                    l = checkPermissionAndAppOp(s3, s, ibinder);
                    if(l == 0)
                        return 0;
                    s2 = s3;
                    l = Math.max(0, l);
                }
                int k;
                PathPermission apathpermission[];
                boolean flag1;
                if(s3 == null)
                    k = 1;
                else
                    k = 0;
                apathpermission = getPathPermissions();
                flag1 = k;
                s3 = s2;
                i1 = l;
                if(apathpermission != null)
                {
                    String s4 = uri.getPath();
                    int j1 = 0;
                    int k1 = apathpermission.length;
                    do
                    {
                        flag1 = k;
                        s3 = s2;
                        i1 = l;
                        if(j1 >= k1)
                            break;
                        PathPermission pathpermission = apathpermission[j1];
                        String s1 = pathpermission.getReadPermission();
                        flag1 = k;
                        s3 = s2;
                        i1 = l;
                        if(s1 != null)
                        {
                            flag1 = k;
                            s3 = s2;
                            i1 = l;
                            if(pathpermission.match(s4))
                            {
                                k = checkPermissionAndAppOp(s1, s, ibinder);
                                if(k == 0)
                                    return 0;
                                flag1 = false;
                                s3 = s1;
                                i1 = Math.max(l, k);
                            }
                        }
                        j1++;
                        k = ((flag1) ? 1 : 0);
                        s2 = s3;
                        l = i1;
                    } while(true);
                }
                if(flag1)
                    return 0;
            }
        }
        l = UserHandle.getUserId(j);
        if(mSingleUser && UserHandle.isSameUser(mMyUid, j) ^ true)
            s = maybeAddUserId(uri, l);
        else
            s = uri;
        if(context.checkUriPermission(s, i, j, 1, ibinder) == 0)
            return 0;
        if(i1 == 1)
            return 1;
        if("android.permission.MANAGE_DOCUMENTS".equals(mReadPermission))
            s = " requires that you obtain access using ACTION_OPEN_DOCUMENT or related APIs";
        else
        if(mExported)
            s = (new StringBuilder()).append(" requires ").append(s3).append(", or grantUriPermission()").toString();
        else
            s = " requires the provider be exported, or grantUriPermission()";
        throw new SecurityException((new StringBuilder()).append("Permission Denial: reading ").append(getClass().getName()).append(" uri ").append(uri).append(" from pid=").append(i).append(", uid=").append(j).append(s).toString());
    }

    protected int enforceWritePermissionInner(Uri uri, String s, IBinder ibinder)
        throws SecurityException
    {
        Context context = getContext();
        int i = Binder.getCallingPid();
        int j = Binder.getCallingUid();
        Object obj = null;
        String s2 = null;
        boolean flag = false;
        int l = 0;
        if(UserHandle.isSameApp(j, mMyUid))
            return 0;
        String s3 = obj;
        int i1 = ((flag) ? 1 : 0);
        if(mExported)
        {
            s3 = obj;
            i1 = ((flag) ? 1 : 0);
            if(checkUser(i, j, context))
            {
                s3 = getWritePermission();
                if(s3 != null)
                {
                    l = checkPermissionAndAppOp(s3, s, ibinder);
                    if(l == 0)
                        return 0;
                    s2 = s3;
                    l = Math.max(0, l);
                }
                int k;
                PathPermission apathpermission[];
                boolean flag1;
                if(s3 == null)
                    k = 1;
                else
                    k = 0;
                apathpermission = getPathPermissions();
                flag1 = k;
                s3 = s2;
                i1 = l;
                if(apathpermission != null)
                {
                    String s4 = uri.getPath();
                    int j1 = 0;
                    int k1 = apathpermission.length;
                    do
                    {
                        flag1 = k;
                        s3 = s2;
                        i1 = l;
                        if(j1 >= k1)
                            break;
                        PathPermission pathpermission = apathpermission[j1];
                        String s1 = pathpermission.getWritePermission();
                        flag1 = k;
                        s3 = s2;
                        i1 = l;
                        if(s1 != null)
                        {
                            flag1 = k;
                            s3 = s2;
                            i1 = l;
                            if(pathpermission.match(s4))
                            {
                                k = checkPermissionAndAppOp(s1, s, ibinder);
                                if(k == 0)
                                    return 0;
                                flag1 = false;
                                s3 = s1;
                                i1 = Math.max(l, k);
                            }
                        }
                        j1++;
                        k = ((flag1) ? 1 : 0);
                        s2 = s3;
                        l = i1;
                    } while(true);
                }
                if(flag1)
                    return 0;
            }
        }
        if(context.checkUriPermission(uri, i, j, 2, ibinder) == 0)
            return 0;
        if(i1 == 1)
            return 1;
        if(mExported)
            s = (new StringBuilder()).append(" requires ").append(s3).append(", or grantUriPermission()").toString();
        else
            s = " requires the provider be exported, or grantUriPermission()";
        throw new SecurityException((new StringBuilder()).append("Permission Denial: writing ").append(getClass().getName()).append(" uri ").append(uri).append(" from pid=").append(i).append(", uid=").append(j).append(s).toString());
    }

    public AppOpsManager getAppOpsManager()
    {
        return mTransport.mAppOpsManager;
    }

    public final String getCallingPackage()
    {
        String s = (String)mCallingPackage.get();
        if(s != null)
            mTransport.mAppOpsManager.checkPackage(Binder.getCallingUid(), s);
        return s;
    }

    public final Context getContext()
    {
        return mContext;
    }

    public IContentProvider getIContentProvider()
    {
        return mTransport;
    }

    public final PathPermission[] getPathPermissions()
    {
        return mPathPermissions;
    }

    public final String getReadPermission()
    {
        return mReadPermission;
    }

    public String[] getStreamTypes(Uri uri, String s)
    {
        return null;
    }

    public abstract String getType(Uri uri);

    public final String getWritePermission()
    {
        return mWritePermission;
    }

    public abstract Uri insert(Uri uri, ContentValues contentvalues);

    protected boolean isTemporary()
    {
        return false;
    }

    protected final boolean matchesOurAuthorities(String s)
    {
        if(mAuthority != null)
            return mAuthority.equals(s);
        if(mAuthorities != null)
        {
            int i = mAuthorities.length;
            for(int j = 0; j < i; j++)
                if(mAuthorities[j].equals(s))
                    return true;

        }
        return false;
    }

    public void onConfigurationChanged(Configuration configuration)
    {
    }

    public abstract boolean onCreate();

    public void onLowMemory()
    {
    }

    public void onTrimMemory(int i)
    {
    }

    public AssetFileDescriptor openAssetFile(Uri uri, String s)
        throws FileNotFoundException
    {
        Object obj = null;
        s = openFile(uri, s);
        uri = obj;
        if(s != null)
            uri = new AssetFileDescriptor(s, 0L, -1L);
        return uri;
    }

    public AssetFileDescriptor openAssetFile(Uri uri, String s, CancellationSignal cancellationsignal)
        throws FileNotFoundException
    {
        return openAssetFile(uri, s);
    }

    public ParcelFileDescriptor openFile(Uri uri, String s)
        throws FileNotFoundException
    {
        throw new FileNotFoundException((new StringBuilder()).append("No files supported by provider at ").append(uri).toString());
    }

    public ParcelFileDescriptor openFile(Uri uri, String s, CancellationSignal cancellationsignal)
        throws FileNotFoundException
    {
        return openFile(uri, s);
    }

    protected final ParcelFileDescriptor openFileHelper(Uri uri, String s)
        throws FileNotFoundException
    {
        Cursor cursor = query(uri, new String[] {
            "_data"
        }, null, null, null);
        int i;
        if(cursor != null)
            i = cursor.getCount();
        else
            i = 0;
        if(i != 1)
        {
            if(cursor != null)
                cursor.close();
            if(i == 0)
                throw new FileNotFoundException((new StringBuilder()).append("No entry for ").append(uri).toString());
            else
                throw new FileNotFoundException((new StringBuilder()).append("Multiple items at ").append(uri).toString());
        }
        cursor.moveToFirst();
        i = cursor.getColumnIndex("_data");
        if(i >= 0)
            uri = cursor.getString(i);
        else
            uri = null;
        cursor.close();
        if(uri == null)
        {
            throw new FileNotFoundException("Column _data not found.");
        } else
        {
            int j = ParcelFileDescriptor.parseMode(s);
            return ParcelFileDescriptor.open(new File(uri), j);
        }
    }

    public ParcelFileDescriptor openPipeHelper(Uri uri, String s, Bundle bundle, Object obj, PipeDataWriter pipedatawriter)
        throws FileNotFoundException
    {
        ParcelFileDescriptor aparcelfiledescriptor[];
        try
        {
            aparcelfiledescriptor = ParcelFileDescriptor.createPipe();
            AsyncTask asynctask = JVM INSTR new #8   <Class ContentProvider$1>;
            asynctask.this. _cls1();
            asynctask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])null);
        }
        // Misplaced declaration of an exception variable
        catch(Uri uri)
        {
            throw new FileNotFoundException("failure making pipe");
        }
        uri = aparcelfiledescriptor[0];
        return uri;
    }

    public AssetFileDescriptor openTypedAssetFile(Uri uri, String s, Bundle bundle)
        throws FileNotFoundException
    {
        if("*/*".equals(s))
            return openAssetFile(uri, "r");
        bundle = getType(uri);
        if(bundle != null && ClipDescription.compareMimeTypes(bundle, s))
            return openAssetFile(uri, "r");
        else
            throw new FileNotFoundException((new StringBuilder()).append("Can't open ").append(uri).append(" as type ").append(s).toString());
    }

    public AssetFileDescriptor openTypedAssetFile(Uri uri, String s, Bundle bundle, CancellationSignal cancellationsignal)
        throws FileNotFoundException
    {
        return openTypedAssetFile(uri, s, bundle);
    }

    public Cursor query(Uri uri, String as[], Bundle bundle, CancellationSignal cancellationsignal)
    {
        String s;
        String s1;
        if(bundle == null)
            bundle = Bundle.EMPTY;
        s = bundle.getString("android:query-arg-sql-sort-order");
        s1 = s;
        if(s == null)
        {
            s1 = s;
            if(bundle.containsKey("android:query-arg-sort-columns"))
                s1 = ContentResolver.createSqlSortClause(bundle);
        }
        return query(uri, as, bundle.getString("android:query-arg-sql-selection"), bundle.getStringArray("android:query-arg-sql-selection-args"), s1, cancellationsignal);
    }

    public abstract Cursor query(Uri uri, String as[], String s, String as1[], String s1);

    public Cursor query(Uri uri, String as[], String s, String as1[], String s1, CancellationSignal cancellationsignal)
    {
        return query(uri, as, s, as1, s1);
    }

    public boolean refresh(Uri uri, Bundle bundle, CancellationSignal cancellationsignal)
    {
        return false;
    }

    public Uri rejectInsert(Uri uri, ContentValues contentvalues)
    {
        return uri.buildUpon().appendPath("0").build();
    }

    public final void setAppOps(int i, int j)
    {
        if(!mNoPerms)
        {
            mTransport.mReadOp = i;
            mTransport.mWriteOp = j;
        }
    }

    protected final void setAuthorities(String s)
    {
        if(s != null)
            if(s.indexOf(';') == -1)
            {
                mAuthority = s;
                mAuthorities = null;
            } else
            {
                mAuthority = null;
                mAuthorities = s.split(";");
            }
    }

    protected final void setPathPermissions(PathPermission apathpermission[])
    {
        mPathPermissions = apathpermission;
    }

    protected final void setReadPermission(String s)
    {
        mReadPermission = s;
    }

    protected final void setWritePermission(String s)
    {
        mWritePermission = s;
    }

    public void shutdown()
    {
        Log.w("ContentProvider", "implement ContentProvider shutdown() to make sure all database connections are gracefully shutdown");
    }

    public Uri uncanonicalize(Uri uri)
    {
        return uri;
    }

    public abstract int update(Uri uri, ContentValues contentvalues, String s, String as[]);

    private static final String TAG = "ContentProvider";
    private String mAuthorities[];
    private String mAuthority;
    private final ThreadLocal mCallingPackage;
    private Context mContext;
    private boolean mExported;
    private int mMyUid;
    private boolean mNoPerms;
    private PathPermission mPathPermissions[];
    private String mReadPermission;
    private boolean mSingleUser;
    private Transport mTransport;
    private String mWritePermission;

    // Unreferenced inner class android/content/ContentProvider$1

/* anonymous class */
    class _cls1 extends AsyncTask
    {

        protected transient Object doInBackground(Object aobj[])
        {
            func.writeDataToPipe(fds[1], uri, mimeType, opts, args);
            try
            {
                fds[1].close();
            }
            // Misplaced declaration of an exception variable
            catch(Object aobj[])
            {
                Log.w("ContentProvider", "Failure closing pipe", ((Throwable) (aobj)));
            }
            return null;
        }

        final ContentProvider this$0;
        final Object val$args;
        final ParcelFileDescriptor val$fds[];
        final PipeDataWriter val$func;
        final String val$mimeType;
        final Bundle val$opts;
        final Uri val$uri;

            
            {
                this$0 = ContentProvider.this;
                func = pipedatawriter;
                fds = aparcelfiledescriptor;
                uri = uri1;
                mimeType = s;
                opts = bundle;
                args = obj;
                super();
            }
    }

}
