// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.provider;

import android.content.*;
import android.content.pm.ProviderInfo;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Point;
import android.net.Uri;
import android.os.*;
import android.util.Log;
import java.io.FileNotFoundException;
import java.util.*;
import libcore.io.IoUtils;

// Referenced classes of package android.provider:
//            DocumentsContract

public abstract class DocumentsProvider extends ContentProvider
{

    public DocumentsProvider()
    {
    }

    private Bundle callUnchecked(String s, String s1, Bundle bundle)
        throws FileNotFoundException
    {
        Context context;
        Bundle bundle1;
        Object obj;
        context = getContext();
        bundle1 = new Bundle();
        if("android:ejectRoot".equals(s))
        {
            s = (Uri)bundle.getParcelable("uri");
            enforceWritePermissionInner(s, getCallingPackage(), null);
            ejectRoot(DocumentsContract.getRootId(s));
            return bundle1;
        }
        obj = (Uri)bundle.getParcelable("uri");
        String s2 = ((Uri) (obj)).getAuthority();
        s1 = DocumentsContract.getDocumentId(((Uri) (obj)));
        if(!mAuthority.equals(s2))
            throw new SecurityException((new StringBuilder()).append("Requested authority ").append(s2).append(" doesn't match provider ").append(mAuthority).toString());
        enforceTree(((Uri) (obj)));
        if(!"android:isChildDocument".equals(s)) goto _L2; else goto _L1
_L1:
        enforceReadPermissionInner(((Uri) (obj)), getCallingPackage(), null);
        bundle = (Uri)bundle.getParcelable("android.content.extra.TARGET_URI");
        s = bundle.getAuthority();
        bundle = DocumentsContract.getDocumentId(bundle);
        boolean flag;
        if(mAuthority.equals(s))
            flag = isChildDocument(s1, bundle);
        else
            flag = false;
        bundle1.putBoolean("result", flag);
_L4:
        return bundle1;
_L2:
        if("android:createDocument".equals(s))
        {
            enforceWritePermissionInner(((Uri) (obj)), getCallingPackage(), null);
            bundle1.putParcelable("uri", DocumentsContract.buildDocumentUriMaybeUsingTree(((Uri) (obj)), createDocument(s1, bundle.getString("mime_type"), bundle.getString("_display_name"))));
            continue; /* Loop/switch isn't completed */
        }
        if("android:createWebLinkIntent".equals(s))
        {
            enforceWritePermissionInner(((Uri) (obj)), getCallingPackage(), null);
            bundle1.putParcelable("result", createWebLinkIntent(s1, bundle.getBundle("options")));
            continue; /* Loop/switch isn't completed */
        }
        if("android:renameDocument".equals(s))
        {
            enforceWritePermissionInner(((Uri) (obj)), getCallingPackage(), null);
            s = renameDocument(s1, bundle.getString("_display_name"));
            if(s != null)
            {
                s = DocumentsContract.buildDocumentUriMaybeUsingTree(((Uri) (obj)), s);
                if(!DocumentsContract.isTreeUri(s))
                {
                    int i = getCallingOrSelfUriPermissionModeFlags(context, ((Uri) (obj)));
                    context.grantUriPermission(getCallingPackage(), s, i);
                }
                bundle1.putParcelable("uri", s);
                revokeDocumentPermission(s1);
            }
            continue; /* Loop/switch isn't completed */
        }
        if("android:deleteDocument".equals(s))
        {
            enforceWritePermissionInner(((Uri) (obj)), getCallingPackage(), null);
            deleteDocument(s1);
            revokeDocumentPermission(s1);
            continue; /* Loop/switch isn't completed */
        }
        if("android:copyDocument".equals(s))
        {
            bundle = (Uri)bundle.getParcelable("android.content.extra.TARGET_URI");
            s = DocumentsContract.getDocumentId(bundle);
            enforceReadPermissionInner(((Uri) (obj)), getCallingPackage(), null);
            enforceWritePermissionInner(bundle, getCallingPackage(), null);
            s = copyDocument(s1, s);
            if(s != null)
            {
                s = DocumentsContract.buildDocumentUriMaybeUsingTree(((Uri) (obj)), s);
                if(!DocumentsContract.isTreeUri(s))
                {
                    int j = getCallingOrSelfUriPermissionModeFlags(context, ((Uri) (obj)));
                    context.grantUriPermission(getCallingPackage(), s, j);
                }
                bundle1.putParcelable("uri", s);
            }
            continue; /* Loop/switch isn't completed */
        }
        if("android:moveDocument".equals(s))
        {
            Uri uri = (Uri)bundle.getParcelable("parentUri");
            s = DocumentsContract.getDocumentId(uri);
            Uri uri1 = (Uri)bundle.getParcelable("android.content.extra.TARGET_URI");
            bundle = DocumentsContract.getDocumentId(uri1);
            enforceWritePermissionInner(((Uri) (obj)), getCallingPackage(), null);
            enforceReadPermissionInner(uri, getCallingPackage(), null);
            enforceWritePermissionInner(uri1, getCallingPackage(), null);
            s = moveDocument(s1, s, bundle);
            if(s != null)
            {
                s = DocumentsContract.buildDocumentUriMaybeUsingTree(((Uri) (obj)), s);
                if(!DocumentsContract.isTreeUri(s))
                {
                    int k = getCallingOrSelfUriPermissionModeFlags(context, ((Uri) (obj)));
                    context.grantUriPermission(getCallingPackage(), s, k);
                }
                bundle1.putParcelable("uri", s);
            }
            continue; /* Loop/switch isn't completed */
        }
        if("android:removeDocument".equals(s))
        {
            s = (Uri)bundle.getParcelable("parentUri");
            bundle = DocumentsContract.getDocumentId(s);
            enforceReadPermissionInner(s, getCallingPackage(), null);
            enforceWritePermissionInner(((Uri) (obj)), getCallingPackage(), null);
            removeDocument(s1, bundle);
            continue; /* Loop/switch isn't completed */
        }
        if(!"android:findDocumentPath".equals(s))
            break; /* Loop/switch isn't completed */
        boolean flag1 = DocumentsContract.isTreeUri(((Uri) (obj)));
        if(flag1)
            enforceReadPermissionInner(((Uri) (obj)), getCallingPackage(), null);
        else
            getContext().enforceCallingPermission("android.permission.MANAGE_DOCUMENTS", null);
        if(flag1)
            bundle = DocumentsContract.getTreeDocumentId(((Uri) (obj)));
        else
            bundle = null;
        obj = findDocumentPath(bundle, s1);
        s = ((String) (obj));
        if(flag1)
        {
            s1 = ((String) (obj));
            if(!Objects.equals(((DocumentsContract.Path) (obj)).getPath().get(0), bundle))
            {
                Log.wtf("DocumentsProvider", (new StringBuilder()).append("Provider doesn't return path from the tree root. Expected: ").append(bundle).append(" found: ").append((String)((DocumentsContract.Path) (obj)).getPath().get(0)).toString());
                for(s = new LinkedList(((DocumentsContract.Path) (obj)).getPath()); s.size() > 1 && Objects.equals(s.getFirst(), bundle) ^ true; s.removeFirst());
                s1 = new DocumentsContract.Path(null, s);
            }
            s = s1;
            if(s1.getRootId() != null)
            {
                Log.wtf("DocumentsProvider", (new StringBuilder()).append("Provider returns root id :").append(s1.getRootId()).append(" unexpectedly. Erase root id.").toString());
                s = new DocumentsContract.Path(null, s1.getPath());
            }
        }
        bundle1.putParcelable("result", s);
        if(true) goto _L4; else goto _L3
_L3:
        if("android:getDocumentMetadata".equals(s))
            return getDocumentMetadata(s1, bundle.getStringArray("android:documentMetadataTags"));
        else
            throw new UnsupportedOperationException((new StringBuilder()).append("Method not supported ").append(s).toString());
    }

    private void enforceTree(Uri uri)
    {
        if(DocumentsContract.isTreeUri(uri))
        {
            String s = DocumentsContract.getTreeDocumentId(uri);
            uri = DocumentsContract.getDocumentId(uri);
            if(Objects.equals(s, uri))
                return;
            if(!isChildDocument(s, uri))
                throw new SecurityException((new StringBuilder()).append("Document ").append(uri).append(" is not a descendant of ").append(s).toString());
        }
    }

    private static int getCallingOrSelfUriPermissionModeFlags(Context context, Uri uri)
    {
        int i = 0;
        if(context.checkCallingOrSelfUriPermission(uri, 1) == 0)
            i = 1;
        int j = i;
        if(context.checkCallingOrSelfUriPermission(uri, 2) == 0)
            j = i | 2;
        i = j;
        if(context.checkCallingOrSelfUriPermission(uri, 65) == 0)
            i = j | 0x40;
        return i;
    }

    private static String getSortClause(Bundle bundle)
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
        return s1;
    }

    public static boolean mimeTypeMatches(String s, String s1)
    {
        if(s1 == null)
            return false;
        if(s == null || "*/*".equals(s))
            return true;
        if(s.equals(s1))
            return true;
        if(s.endsWith("/*"))
            return s.regionMatches(0, s1, 0, s.indexOf('/'));
        else
            return false;
    }

    private final AssetFileDescriptor openTypedAssetFileImpl(Uri uri, String s, Bundle bundle, CancellationSignal cancellationsignal)
        throws FileNotFoundException
    {
        enforceTree(uri);
        String s1 = DocumentsContract.getDocumentId(uri);
        if(bundle != null && bundle.containsKey("android.content.extra.SIZE"))
            return openDocumentThumbnail(s1, (Point)bundle.getParcelable("android.content.extra.SIZE"), cancellationsignal);
        if("*/*".equals(s))
            return openAssetFile(uri, "r");
        String s2 = getType(uri);
        if(s2 != null && ClipDescription.compareMimeTypes(s2, s))
            return openAssetFile(uri, "r");
        else
            return openTypedDocument(s1, s, bundle, cancellationsignal);
    }

    private void registerAuthority(String s)
    {
        mAuthority = s;
        mMatcher = new UriMatcher(-1);
        mMatcher.addURI(mAuthority, "root", 1);
        mMatcher.addURI(mAuthority, "root/*", 2);
        mMatcher.addURI(mAuthority, "root/*/recent", 3);
        mMatcher.addURI(mAuthority, "root/*/search", 4);
        mMatcher.addURI(mAuthority, "document/*", 5);
        mMatcher.addURI(mAuthority, "document/*/children", 6);
        mMatcher.addURI(mAuthority, "tree/*/document/*", 7);
        mMatcher.addURI(mAuthority, "tree/*/document/*/children", 8);
    }

    public void attachInfo(Context context, ProviderInfo providerinfo)
    {
        registerAuthority(providerinfo.authority);
        if(!providerinfo.exported)
            throw new SecurityException("Provider must be exported");
        if(!providerinfo.grantUriPermissions)
            throw new SecurityException("Provider must grantUriPermissions");
        if(!"android.permission.MANAGE_DOCUMENTS".equals(providerinfo.readPermission) || "android.permission.MANAGE_DOCUMENTS".equals(providerinfo.writePermission) ^ true)
        {
            throw new SecurityException("Provider must be protected by MANAGE_DOCUMENTS");
        } else
        {
            super.attachInfo(context, providerinfo);
            return;
        }
    }

    public void attachInfoForTesting(Context context, ProviderInfo providerinfo)
    {
        registerAuthority(providerinfo.authority);
        super.attachInfoForTesting(context, providerinfo);
    }

    public Bundle call(String s, String s1, Bundle bundle)
    {
        if(!s.startsWith("android:"))
            return super.call(s, s1, bundle);
        try
        {
            s = callUnchecked(s, s1, bundle);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new ParcelableException(s);
        }
        return s;
    }

    public Uri canonicalize(Uri uri)
    {
        Context context = getContext();
        switch(mMatcher.match(uri))
        {
        default:
            return null;

        case 7: // '\007'
            enforceTree(uri);
            break;
        }
        Uri uri1 = DocumentsContract.buildDocumentUri(uri.getAuthority(), DocumentsContract.getDocumentId(uri));
        int i = getCallingOrSelfUriPermissionModeFlags(context, uri);
        context.grantUriPermission(getCallingPackage(), uri1, i);
        return uri1;
    }

    public String copyDocument(String s, String s1)
        throws FileNotFoundException
    {
        throw new UnsupportedOperationException("Copy not supported");
    }

    public String createDocument(String s, String s1, String s2)
        throws FileNotFoundException
    {
        throw new UnsupportedOperationException("Create not supported");
    }

    public IntentSender createWebLinkIntent(String s, Bundle bundle)
        throws FileNotFoundException
    {
        throw new UnsupportedOperationException("createWebLink is not supported.");
    }

    public final int delete(Uri uri, String s, String as[])
    {
        throw new UnsupportedOperationException("Delete not supported");
    }

    public void deleteDocument(String s)
        throws FileNotFoundException
    {
        throw new UnsupportedOperationException("Delete not supported");
    }

    public void ejectRoot(String s)
    {
        throw new UnsupportedOperationException("Eject not supported");
    }

    public DocumentsContract.Path findDocumentPath(String s, String s1)
        throws FileNotFoundException
    {
        throw new UnsupportedOperationException("findDocumentPath not supported.");
    }

    public Bundle getDocumentMetadata(String s, String as[])
        throws FileNotFoundException
    {
        throw new UnsupportedOperationException("Metadata not supported");
    }

    public String[] getDocumentStreamTypes(String s, String s1)
    {
        String s2;
        String s3;
        s2 = null;
        s3 = null;
        String s4;
        try
        {
            s = queryDocument(s, null);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            IoUtils.closeQuietly(s3);
            return null;
        }
        s3 = s;
        s2 = s;
        if(!s.moveToFirst())
            break MISSING_BLOCK_LABEL_109;
        s3 = s;
        s2 = s;
        s4 = s.getString(s.getColumnIndexOrThrow("mime_type"));
        s3 = s;
        s2 = s;
        if((512L & s.getLong(s.getColumnIndexOrThrow("flags"))) != 0L || s4 == null)
            break MISSING_BLOCK_LABEL_109;
        s3 = s;
        s2 = s;
        if(mimeTypeMatches(s1, s4))
        {
            IoUtils.closeQuietly(s);
            return (new String[] {
                s4
            });
        }
        IoUtils.closeQuietly(s);
        return null;
        s;
        IoUtils.closeQuietly(s2);
        throw s;
    }

    public String getDocumentType(String s)
        throws FileNotFoundException
    {
        s = queryDocument(s, null);
        String s1;
        if(!s.moveToFirst())
            break MISSING_BLOCK_LABEL_37;
        s1 = s.getString(s.getColumnIndexOrThrow("mime_type"));
        IoUtils.closeQuietly(s);
        return s1;
        IoUtils.closeQuietly(s);
        return null;
        Exception exception;
        exception;
        IoUtils.closeQuietly(s);
        throw exception;
    }

    public String[] getStreamTypes(Uri uri, String s)
    {
        enforceTree(uri);
        return getDocumentStreamTypes(DocumentsContract.getDocumentId(uri), s);
    }

    public final String getType(Uri uri)
    {
        mMatcher.match(uri);
        JVM INSTR tableswitch 2 7: default 48
    //                   2 50
    //                   3 48
    //                   4 48
    //                   5 54
    //                   6 48
    //                   7 54;
           goto _L1 _L2 _L1 _L1 _L3 _L1 _L3
_L1:
        return null;
_L2:
        return "vnd.android.document/root";
_L3:
        try
        {
            enforceTree(uri);
            uri = getDocumentType(DocumentsContract.getDocumentId(uri));
        }
        // Misplaced declaration of an exception variable
        catch(Uri uri)
        {
            Log.w("DocumentsProvider", "Failed during getType", uri);
            return null;
        }
        return uri;
    }

    public final Uri insert(Uri uri, ContentValues contentvalues)
    {
        throw new UnsupportedOperationException("Insert not supported");
    }

    public boolean isChildDocument(String s, String s1)
    {
        return false;
    }

    public String moveDocument(String s, String s1, String s2)
        throws FileNotFoundException
    {
        throw new UnsupportedOperationException("Move not supported");
    }

    public final AssetFileDescriptor openAssetFile(Uri uri, String s)
        throws FileNotFoundException
    {
        Object obj = null;
        enforceTree(uri);
        s = openDocument(DocumentsContract.getDocumentId(uri), s, null);
        uri = obj;
        if(s != null)
            uri = new AssetFileDescriptor(s, 0L, -1L);
        return uri;
    }

    public final AssetFileDescriptor openAssetFile(Uri uri, String s, CancellationSignal cancellationsignal)
        throws FileNotFoundException
    {
        Object obj = null;
        enforceTree(uri);
        s = openDocument(DocumentsContract.getDocumentId(uri), s, cancellationsignal);
        uri = obj;
        if(s != null)
            uri = new AssetFileDescriptor(s, 0L, -1L);
        return uri;
    }

    public abstract ParcelFileDescriptor openDocument(String s, String s1, CancellationSignal cancellationsignal)
        throws FileNotFoundException;

    public AssetFileDescriptor openDocumentThumbnail(String s, Point point, CancellationSignal cancellationsignal)
        throws FileNotFoundException
    {
        throw new UnsupportedOperationException("Thumbnails not supported");
    }

    public final ParcelFileDescriptor openFile(Uri uri, String s)
        throws FileNotFoundException
    {
        enforceTree(uri);
        return openDocument(DocumentsContract.getDocumentId(uri), s, null);
    }

    public final ParcelFileDescriptor openFile(Uri uri, String s, CancellationSignal cancellationsignal)
        throws FileNotFoundException
    {
        enforceTree(uri);
        return openDocument(DocumentsContract.getDocumentId(uri), s, cancellationsignal);
    }

    public final AssetFileDescriptor openTypedAssetFile(Uri uri, String s, Bundle bundle)
        throws FileNotFoundException
    {
        return openTypedAssetFileImpl(uri, s, bundle, null);
    }

    public final AssetFileDescriptor openTypedAssetFile(Uri uri, String s, Bundle bundle, CancellationSignal cancellationsignal)
        throws FileNotFoundException
    {
        return openTypedAssetFileImpl(uri, s, bundle, cancellationsignal);
    }

    public AssetFileDescriptor openTypedDocument(String s, String s1, Bundle bundle, CancellationSignal cancellationsignal)
        throws FileNotFoundException
    {
        throw new FileNotFoundException("The requested MIME type is not supported.");
    }

    public final Cursor query(Uri uri, String as[], Bundle bundle, CancellationSignal cancellationsignal)
    {
        mMatcher.match(uri);
        JVM INSTR tableswitch 1 8: default 56
    //                   1 101
    //                   2 56
    //                   3 107
    //                   4 117
    //                   5 131
    //                   6 146
    //                   7 131
    //                   8 146;
           goto _L1 _L2 _L1 _L3 _L4 _L5 _L6 _L5 _L6
_L1:
        as = JVM INSTR new #285 <Class UnsupportedOperationException>;
        bundle = JVM INSTR new #90  <Class StringBuilder>;
        bundle.StringBuilder();
        as.UnsupportedOperationException(bundle.append("Unsupported Uri ").append(uri).toString());
        throw as;
        uri;
        Log.w("DocumentsProvider", "Failed during query", uri);
        return null;
_L2:
        return queryRoots(as);
_L3:
        return queryRecentDocuments(DocumentsContract.getRootId(uri), as);
_L4:
        return querySearchDocuments(DocumentsContract.getRootId(uri), DocumentsContract.getSearchDocumentsQuery(uri), as);
_L5:
        enforceTree(uri);
        return queryDocument(DocumentsContract.getDocumentId(uri), as);
_L6:
        enforceTree(uri);
        if(DocumentsContract.isManageMode(uri))
            return queryChildDocumentsForManage(DocumentsContract.getDocumentId(uri), as, getSortClause(bundle));
        uri = queryChildDocuments(DocumentsContract.getDocumentId(uri), as, bundle);
        return uri;
    }

    public final Cursor query(Uri uri, String as[], String s, String as1[], String s1)
    {
        throw new UnsupportedOperationException("Pre-Android-O query format not supported.");
    }

    public Cursor query(Uri uri, String as[], String s, String as1[], String s1, CancellationSignal cancellationsignal)
    {
        throw new UnsupportedOperationException("Pre-Android-O query format not supported.");
    }

    public Cursor queryChildDocuments(String s, String as[], Bundle bundle)
        throws FileNotFoundException
    {
        return queryChildDocuments(s, as, getSortClause(bundle));
    }

    public abstract Cursor queryChildDocuments(String s, String as[], String s1)
        throws FileNotFoundException;

    public Cursor queryChildDocumentsForManage(String s, String as[], String s1)
        throws FileNotFoundException
    {
        throw new UnsupportedOperationException("Manage not supported");
    }

    public abstract Cursor queryDocument(String s, String as[])
        throws FileNotFoundException;

    public Cursor queryRecentDocuments(String s, String as[])
        throws FileNotFoundException
    {
        throw new UnsupportedOperationException("Recent not supported");
    }

    public abstract Cursor queryRoots(String as[])
        throws FileNotFoundException;

    public Cursor querySearchDocuments(String s, String s1, String as[])
        throws FileNotFoundException
    {
        throw new UnsupportedOperationException("Search not supported");
    }

    public void removeDocument(String s, String s1)
        throws FileNotFoundException
    {
        throw new UnsupportedOperationException("Remove not supported");
    }

    public String renameDocument(String s, String s1)
        throws FileNotFoundException
    {
        throw new UnsupportedOperationException("Rename not supported");
    }

    public final void revokeDocumentPermission(String s)
    {
        Context context = getContext();
        context.revokeUriPermission(DocumentsContract.buildDocumentUri(mAuthority, s), -1);
        context.revokeUriPermission(DocumentsContract.buildTreeDocumentUri(mAuthority, s), -1);
    }

    public final int update(Uri uri, ContentValues contentvalues, String s, String as[])
    {
        throw new UnsupportedOperationException("Update not supported");
    }

    private static final int MATCH_CHILDREN = 6;
    private static final int MATCH_CHILDREN_TREE = 8;
    private static final int MATCH_DOCUMENT = 5;
    private static final int MATCH_DOCUMENT_TREE = 7;
    private static final int MATCH_RECENT = 3;
    private static final int MATCH_ROOT = 2;
    private static final int MATCH_ROOTS = 1;
    private static final int MATCH_SEARCH = 4;
    private static final String TAG = "DocumentsProvider";
    private String mAuthority;
    private UriMatcher mMatcher;
}
