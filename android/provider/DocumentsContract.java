// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.provider;

import android.content.*;
import android.content.pm.*;
import android.content.res.AssetFileDescriptor;
import android.graphics.*;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.*;
import android.system.*;
import android.util.Log;
import com.android.internal.util.Preconditions;
import java.io.*;
import java.util.*;
import libcore.io.IoUtils;

public final class DocumentsContract
{
    public static final class Document
    {

        public static final String COLUMN_DISPLAY_NAME = "_display_name";
        public static final String COLUMN_DOCUMENT_ID = "document_id";
        public static final String COLUMN_FLAGS = "flags";
        public static final String COLUMN_ICON = "icon";
        public static final String COLUMN_LAST_MODIFIED = "last_modified";
        public static final String COLUMN_MIME_TYPE = "mime_type";
        public static final String COLUMN_SIZE = "_size";
        public static final String COLUMN_SUMMARY = "summary";
        public static final int FLAG_DIR_PREFERS_GRID = 16;
        public static final int FLAG_DIR_PREFERS_LAST_MODIFIED = 32;
        public static final int FLAG_DIR_SUPPORTS_CREATE = 8;
        public static final int FLAG_PARTIAL = 0x10000;
        public static final int FLAG_SUPPORTS_COPY = 128;
        public static final int FLAG_SUPPORTS_DELETE = 4;
        public static final int FLAG_SUPPORTS_METADATA = 0x20000;
        public static final int FLAG_SUPPORTS_MOVE = 256;
        public static final int FLAG_SUPPORTS_REMOVE = 1024;
        public static final int FLAG_SUPPORTS_RENAME = 64;
        public static final int FLAG_SUPPORTS_SETTINGS = 2048;
        public static final int FLAG_SUPPORTS_THUMBNAIL = 1;
        public static final int FLAG_SUPPORTS_WRITE = 2;
        public static final int FLAG_VIRTUAL_DOCUMENT = 512;
        public static final int FLAG_WEB_LINKABLE = 4096;
        public static final String MIME_TYPE_DIR = "vnd.android.document/directory";

        private Document()
        {
        }
    }

    public static final class Path
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public boolean equals(Object obj)
        {
            boolean flag = false;
            if(this == obj)
                return true;
            if(obj == null || (obj instanceof Path) ^ true)
                return false;
            obj = (Path)obj;
            if(Objects.equals(mRootId, ((Path) (obj)).mRootId))
                flag = Objects.equals(mPath, ((Path) (obj)).mPath);
            return flag;
        }

        public List getPath()
        {
            return mPath;
        }

        public String getRootId()
        {
            return mRootId;
        }

        public int hashCode()
        {
            return Objects.hash(new Object[] {
                mRootId, mPath
            });
        }

        public String toString()
        {
            return (new StringBuilder()).append("DocumentsContract.Path{").append("rootId=").append(mRootId).append(", path=").append(mPath).append("}").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeString(mRootId);
            parcel.writeStringList(mPath);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public Path createFromParcel(Parcel parcel)
            {
                return new Path(parcel.readString(), parcel.createStringArrayList());
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public Path[] newArray(int i)
            {
                return new Path[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private final List mPath;
        private final String mRootId;


        public Path(String s, List list)
        {
            Preconditions.checkCollectionNotEmpty(list, "path");
            Preconditions.checkCollectionElementsNotNull(list, "path");
            mRootId = s;
            mPath = list;
        }
    }

    public static final class Root
    {

        public static final String COLUMN_AVAILABLE_BYTES = "available_bytes";
        public static final String COLUMN_CAPACITY_BYTES = "capacity_bytes";
        public static final String COLUMN_DOCUMENT_ID = "document_id";
        public static final String COLUMN_FLAGS = "flags";
        public static final String COLUMN_ICON = "icon";
        public static final String COLUMN_MIME_TYPES = "mime_types";
        public static final String COLUMN_ROOT_ID = "root_id";
        public static final String COLUMN_SUMMARY = "summary";
        public static final String COLUMN_TITLE = "title";
        public static final int FLAG_ADVANCED = 0x20000;
        public static final int FLAG_EMPTY = 0x10000;
        public static final int FLAG_HAS_SETTINGS = 0x40000;
        public static final int FLAG_LOCAL_ONLY = 2;
        public static final int FLAG_REMOVABLE_SD = 0x80000;
        public static final int FLAG_REMOVABLE_USB = 0x100000;
        public static final int FLAG_SUPPORTS_CREATE = 1;
        public static final int FLAG_SUPPORTS_EJECT = 32;
        public static final int FLAG_SUPPORTS_IS_CHILD = 16;
        public static final int FLAG_SUPPORTS_RECENTS = 4;
        public static final int FLAG_SUPPORTS_SEARCH = 8;
        public static final String MIME_TYPE_ITEM = "vnd.android.document/root";

        private Root()
        {
        }
    }


    private DocumentsContract()
    {
    }

    public static Uri buildChildDocumentsUri(String s, String s1)
    {
        return (new android.net.Uri.Builder()).scheme("content").authority(s).appendPath("document").appendPath(s1).appendPath("children").build();
    }

    public static Uri buildChildDocumentsUriUsingTree(Uri uri, String s)
    {
        return (new android.net.Uri.Builder()).scheme("content").authority(uri.getAuthority()).appendPath("tree").appendPath(getTreeDocumentId(uri)).appendPath("document").appendPath(s).appendPath("children").build();
    }

    public static Uri buildDocumentUri(String s, String s1)
    {
        return (new android.net.Uri.Builder()).scheme("content").authority(s).appendPath("document").appendPath(s1).build();
    }

    public static Uri buildDocumentUriMaybeUsingTree(Uri uri, String s)
    {
        if(isTreeUri(uri))
            return buildDocumentUriUsingTree(uri, s);
        else
            return buildDocumentUri(uri.getAuthority(), s);
    }

    public static Uri buildDocumentUriUsingTree(Uri uri, String s)
    {
        return (new android.net.Uri.Builder()).scheme("content").authority(uri.getAuthority()).appendPath("tree").appendPath(getTreeDocumentId(uri)).appendPath("document").appendPath(s).build();
    }

    public static Uri buildHomeUri()
    {
        return buildRootUri("com.android.externalstorage.documents", "home");
    }

    public static Uri buildRecentDocumentsUri(String s, String s1)
    {
        return (new android.net.Uri.Builder()).scheme("content").authority(s).appendPath("root").appendPath(s1).appendPath("recent").build();
    }

    public static Uri buildRootUri(String s, String s1)
    {
        return (new android.net.Uri.Builder()).scheme("content").authority(s).appendPath("root").appendPath(s1).build();
    }

    public static Uri buildRootsUri(String s)
    {
        return (new android.net.Uri.Builder()).scheme("content").authority(s).appendPath("root").build();
    }

    public static Uri buildSearchDocumentsUri(String s, String s1, String s2)
    {
        return (new android.net.Uri.Builder()).scheme("content").authority(s).appendPath("root").appendPath(s1).appendPath("search").appendQueryParameter("query", s2).build();
    }

    public static Uri buildTreeDocumentUri(String s, String s1)
    {
        return (new android.net.Uri.Builder()).scheme("content").authority(s).appendPath("tree").appendPath(s1).build();
    }

    public static Uri copyDocument(ContentProviderClient contentproviderclient, Uri uri, Uri uri1)
        throws RemoteException
    {
        Bundle bundle = new Bundle();
        bundle.putParcelable("uri", uri);
        bundle.putParcelable("android.content.extra.TARGET_URI", uri1);
        return (Uri)contentproviderclient.call("android:copyDocument", null, bundle).getParcelable("uri");
    }

    public static Uri copyDocument(ContentResolver contentresolver, Uri uri, Uri uri1)
        throws FileNotFoundException
    {
        ContentProviderClient contentproviderclient = contentresolver.acquireUnstableContentProviderClient(uri.getAuthority());
        uri = copyDocument(contentproviderclient, uri, uri1);
        ContentProviderClient.releaseQuietly(contentproviderclient);
        return uri;
        uri;
        Log.w("DocumentsContract", "Failed to copy document", uri);
        rethrowIfNecessary(contentresolver, uri);
        ContentProviderClient.releaseQuietly(contentproviderclient);
        return null;
        contentresolver;
        ContentProviderClient.releaseQuietly(contentproviderclient);
        throw contentresolver;
    }

    public static Uri createDocument(ContentProviderClient contentproviderclient, Uri uri, String s, String s1)
        throws RemoteException
    {
        Bundle bundle = new Bundle();
        bundle.putParcelable("uri", uri);
        bundle.putString("mime_type", s);
        bundle.putString("_display_name", s1);
        return (Uri)contentproviderclient.call("android:createDocument", null, bundle).getParcelable("uri");
    }

    public static Uri createDocument(ContentResolver contentresolver, Uri uri, String s, String s1)
        throws FileNotFoundException
    {
        ContentProviderClient contentproviderclient = contentresolver.acquireUnstableContentProviderClient(uri.getAuthority());
        uri = createDocument(contentproviderclient, uri, s, s1);
        ContentProviderClient.releaseQuietly(contentproviderclient);
        return uri;
        uri;
        Log.w("DocumentsContract", "Failed to create document", uri);
        rethrowIfNecessary(contentresolver, uri);
        ContentProviderClient.releaseQuietly(contentproviderclient);
        return null;
        contentresolver;
        ContentProviderClient.releaseQuietly(contentproviderclient);
        throw contentresolver;
    }

    public static IntentSender createWebLinkIntent(ContentProviderClient contentproviderclient, Uri uri, Bundle bundle)
        throws RemoteException
    {
        Bundle bundle1 = new Bundle();
        bundle1.putParcelable("uri", uri);
        if(bundle != null)
            bundle1.putBundle("options", bundle);
        return (IntentSender)contentproviderclient.call("android:createWebLinkIntent", null, bundle1).getParcelable("result");
    }

    public static IntentSender createWebLinkIntent(ContentResolver contentresolver, Uri uri, Bundle bundle)
        throws FileNotFoundException
    {
        ContentProviderClient contentproviderclient = contentresolver.acquireUnstableContentProviderClient(uri.getAuthority());
        uri = createWebLinkIntent(contentproviderclient, uri, bundle);
        ContentProviderClient.releaseQuietly(contentproviderclient);
        return uri;
        uri;
        Log.w("DocumentsContract", "Failed to create a web link intent", uri);
        rethrowIfNecessary(contentresolver, uri);
        ContentProviderClient.releaseQuietly(contentproviderclient);
        return null;
        contentresolver;
        ContentProviderClient.releaseQuietly(contentproviderclient);
        throw contentresolver;
    }

    public static void deleteDocument(ContentProviderClient contentproviderclient, Uri uri)
        throws RemoteException
    {
        Bundle bundle = new Bundle();
        bundle.putParcelable("uri", uri);
        contentproviderclient.call("android:deleteDocument", null, bundle);
    }

    public static boolean deleteDocument(ContentResolver contentresolver, Uri uri)
        throws FileNotFoundException
    {
        ContentProviderClient contentproviderclient = contentresolver.acquireUnstableContentProviderClient(uri.getAuthority());
        deleteDocument(contentproviderclient, uri);
        ContentProviderClient.releaseQuietly(contentproviderclient);
        return true;
        uri;
        Log.w("DocumentsContract", "Failed to delete document", uri);
        rethrowIfNecessary(contentresolver, uri);
        ContentProviderClient.releaseQuietly(contentproviderclient);
        return false;
        contentresolver;
        ContentProviderClient.releaseQuietly(contentproviderclient);
        throw contentresolver;
    }

    public static void ejectRoot(ContentProviderClient contentproviderclient, Uri uri)
        throws RemoteException
    {
        Bundle bundle = new Bundle();
        bundle.putParcelable("uri", uri);
        contentproviderclient.call("android:ejectRoot", null, bundle);
    }

    public static void ejectRoot(ContentResolver contentresolver, Uri uri)
    {
        contentresolver = contentresolver.acquireUnstableContentProviderClient(uri.getAuthority());
        ejectRoot(((ContentProviderClient) (contentresolver)), uri);
        ContentProviderClient.releaseQuietly(contentresolver);
_L2:
        return;
        uri;
        uri.rethrowAsRuntimeException();
        ContentProviderClient.releaseQuietly(contentresolver);
        if(true) goto _L2; else goto _L1
_L1:
        uri;
        ContentProviderClient.releaseQuietly(contentresolver);
        throw uri;
    }

    public static Path findDocumentPath(ContentProviderClient contentproviderclient, Uri uri)
        throws RemoteException
    {
        Bundle bundle = new Bundle();
        bundle.putParcelable("uri", uri);
        return (Path)contentproviderclient.call("android:findDocumentPath", null, bundle).getParcelable("result");
    }

    public static Path findDocumentPath(ContentResolver contentresolver, Uri uri)
        throws FileNotFoundException
    {
        ContentProviderClient contentproviderclient;
        Preconditions.checkArgument(isTreeUri(uri), (new StringBuilder()).append(uri).append(" is not a tree uri.").toString());
        contentproviderclient = contentresolver.acquireUnstableContentProviderClient(uri.getAuthority());
        uri = findDocumentPath(contentproviderclient, uri);
        ContentProviderClient.releaseQuietly(contentproviderclient);
        return uri;
        uri;
        Log.w("DocumentsContract", "Failed to find path", uri);
        rethrowIfNecessary(contentresolver, uri);
        ContentProviderClient.releaseQuietly(contentproviderclient);
        return null;
        contentresolver;
        ContentProviderClient.releaseQuietly(contentproviderclient);
        throw contentresolver;
    }

    public static String getDocumentId(Uri uri)
    {
        List list = uri.getPathSegments();
        if(list.size() >= 2 && "document".equals(list.get(0)))
            return (String)list.get(1);
        if(list.size() >= 4 && "tree".equals(list.get(0)) && "document".equals(list.get(2)))
            return (String)list.get(3);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid URI: ").append(uri).toString());
    }

    public static Bundle getDocumentMetadata(ContentProviderClient contentproviderclient, Uri uri, String as[])
        throws RemoteException
    {
        Bundle bundle = new Bundle();
        bundle.putParcelable("uri", uri);
        bundle.putStringArray("android:documentMetadataTags", as);
        contentproviderclient = contentproviderclient.call("android:getDocumentMetadata", null, bundle);
        if(contentproviderclient == null)
            throw new RemoteException("Failed to get a response from getDocumentMetadata");
        else
            return contentproviderclient;
    }

    public static Bundle getDocumentMetadata(ContentResolver contentresolver, Uri uri, String as[])
        throws FileNotFoundException
    {
        ContentProviderClient contentproviderclient = contentresolver.acquireUnstableContentProviderClient(uri.getAuthority());
        uri = getDocumentMetadata(contentproviderclient, uri, as);
        ContentProviderClient.releaseQuietly(contentproviderclient);
        return uri;
        uri;
        Log.w("DocumentsContract", "Failed to get document metadata");
        rethrowIfNecessary(contentresolver, uri);
        ContentProviderClient.releaseQuietly(contentproviderclient);
        return null;
        contentresolver;
        ContentProviderClient.releaseQuietly(contentproviderclient);
        throw contentresolver;
    }

    public static Bitmap getDocumentThumbnail(ContentProviderClient contentproviderclient, Uri uri, Point point, CancellationSignal cancellationsignal)
        throws RemoteException, IOException
    {
        Object obj;
        CancellationSignal cancellationsignal1;
        obj = new Bundle();
        ((Bundle) (obj)).putParcelable("android.content.extra.SIZE", point);
        cancellationsignal1 = null;
        cancellationsignal = contentproviderclient.openTypedAssetFileDescriptor(uri, "image/*", ((Bundle) (obj)), cancellationsignal);
        cancellationsignal1 = cancellationsignal;
        uri = cancellationsignal.getFileDescriptor();
        cancellationsignal1 = cancellationsignal;
        long l = cancellationsignal.getStartOffset();
        contentproviderclient = null;
        cancellationsignal1 = cancellationsignal;
        Os.lseek(uri, l, OsConstants.SEEK_SET);
_L5:
        cancellationsignal1 = cancellationsignal;
        obj = JVM INSTR new #417 <Class android.graphics.BitmapFactory$Options>;
        cancellationsignal1 = cancellationsignal;
        ((android.graphics.BitmapFactory.Options) (obj)).android.graphics.BitmapFactory.Options();
        cancellationsignal1 = cancellationsignal;
        obj.inJustDecodeBounds = true;
        if(contentproviderclient == null) goto _L2; else goto _L1
_L1:
        cancellationsignal1 = cancellationsignal;
        BitmapFactory.decodeStream(contentproviderclient, null, ((android.graphics.BitmapFactory.Options) (obj)));
_L6:
        cancellationsignal1 = cancellationsignal;
        int i = ((android.graphics.BitmapFactory.Options) (obj)).outWidth / point.x;
        cancellationsignal1 = cancellationsignal;
        int j = ((android.graphics.BitmapFactory.Options) (obj)).outHeight / point.y;
        cancellationsignal1 = cancellationsignal;
        obj.inJustDecodeBounds = false;
        cancellationsignal1 = cancellationsignal;
        obj.inSampleSize = Math.min(i, j);
        if(contentproviderclient == null) goto _L4; else goto _L3
_L3:
        cancellationsignal1 = cancellationsignal;
        contentproviderclient.reset();
        cancellationsignal1 = cancellationsignal;
        contentproviderclient = BitmapFactory.decodeStream(contentproviderclient, null, ((android.graphics.BitmapFactory.Options) (obj)));
_L7:
        cancellationsignal1 = cancellationsignal;
        uri = cancellationsignal.getExtras();
        if(uri == null)
            break MISSING_BLOCK_LABEL_395;
        cancellationsignal1 = cancellationsignal;
        i = uri.getInt("android.provider.extra.ORIENTATION", 0);
_L9:
        uri = contentproviderclient;
        if(i == 0)
            break MISSING_BLOCK_LABEL_280;
        cancellationsignal1 = cancellationsignal;
        int k = contentproviderclient.getWidth();
        cancellationsignal1 = cancellationsignal;
        j = contentproviderclient.getHeight();
        cancellationsignal1 = cancellationsignal;
        uri = JVM INSTR new #474 <Class Matrix>;
        cancellationsignal1 = cancellationsignal;
        uri.Matrix();
        cancellationsignal1 = cancellationsignal;
        uri.setRotate(i, k / 2, j / 2);
        cancellationsignal1 = cancellationsignal;
        uri = Bitmap.createBitmap(contentproviderclient, 0, 0, k, j, uri, false);
        IoUtils.closeQuietly(cancellationsignal);
        return uri;
        contentproviderclient;
        cancellationsignal1 = cancellationsignal;
        contentproviderclient = JVM INSTR new #453 <Class BufferedInputStream>;
        cancellationsignal1 = cancellationsignal;
        obj = JVM INSTR new #491 <Class FileInputStream>;
        cancellationsignal1 = cancellationsignal;
        ((FileInputStream) (obj)).FileInputStream(uri);
        cancellationsignal1 = cancellationsignal;
        contentproviderclient.BufferedInputStream(((java.io.InputStream) (obj)), 0x20000);
        cancellationsignal1 = cancellationsignal;
        contentproviderclient.mark(0x20000);
          goto _L5
        contentproviderclient;
        IoUtils.closeQuietly(cancellationsignal1);
        throw contentproviderclient;
_L2:
        cancellationsignal1 = cancellationsignal;
        BitmapFactory.decodeFileDescriptor(uri, null, ((android.graphics.BitmapFactory.Options) (obj)));
          goto _L6
_L4:
        cancellationsignal1 = cancellationsignal;
        Os.lseek(uri, l, OsConstants.SEEK_SET);
_L8:
        cancellationsignal1 = cancellationsignal;
        contentproviderclient = BitmapFactory.decodeFileDescriptor(uri, null, ((android.graphics.BitmapFactory.Options) (obj)));
          goto _L7
        contentproviderclient;
        cancellationsignal1 = cancellationsignal;
        contentproviderclient.rethrowAsIOException();
          goto _L8
        i = 0;
          goto _L9
    }

    public static Bitmap getDocumentThumbnail(ContentResolver contentresolver, Uri uri, Point point, CancellationSignal cancellationsignal)
        throws FileNotFoundException
    {
        ContentProviderClient contentproviderclient = contentresolver.acquireUnstableContentProviderClient(uri.getAuthority());
        point = getDocumentThumbnail(contentproviderclient, uri, point, cancellationsignal);
        ContentProviderClient.releaseQuietly(contentproviderclient);
        return point;
        cancellationsignal;
        if(!(cancellationsignal instanceof OperationCanceledException))
        {
            point = JVM INSTR new #312 <Class StringBuilder>;
            point.StringBuilder();
            Log.w("DocumentsContract", point.append("Failed to load thumbnail for ").append(uri).append(": ").append(cancellationsignal).toString());
        }
        rethrowIfNecessary(contentresolver, cancellationsignal);
        ContentProviderClient.releaseQuietly(contentproviderclient);
        return null;
        contentresolver;
        ContentProviderClient.releaseQuietly(contentproviderclient);
        throw contentresolver;
    }

    public static String getRootId(Uri uri)
    {
        List list = uri.getPathSegments();
        if(list.size() >= 2 && "root".equals(list.get(0)))
            return (String)list.get(1);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid URI: ").append(uri).toString());
    }

    public static String getSearchDocumentsQuery(Uri uri)
    {
        return uri.getQueryParameter("query");
    }

    public static String getTreeDocumentId(Uri uri)
    {
        List list = uri.getPathSegments();
        if(list.size() >= 2 && "tree".equals(list.get(0)))
            return (String)list.get(1);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid URI: ").append(uri).toString());
    }

    public static boolean isChildDocument(ContentProviderClient contentproviderclient, Uri uri, Uri uri1)
        throws RemoteException
    {
        Bundle bundle = new Bundle();
        bundle.putParcelable("uri", uri);
        bundle.putParcelable("android.content.extra.TARGET_URI", uri1);
        contentproviderclient = contentproviderclient.call("android:isChildDocument", null, bundle);
        if(contentproviderclient == null)
            throw new RemoteException("Failed to get a reponse from isChildDocument query.");
        if(!contentproviderclient.containsKey("result"))
            throw new RemoteException("Response did not include result field..");
        else
            return contentproviderclient.getBoolean("result");
    }

    public static boolean isContentUri(Uri uri)
    {
        boolean flag;
        if(uri != null)
            flag = "content".equals(uri.getScheme());
        else
            flag = false;
        return flag;
    }

    public static boolean isDocumentUri(Context context, Uri uri)
    {
        boolean flag = false;
        if(isContentUri(uri) && isDocumentsProvider(context, uri.getAuthority()))
        {
            context = uri.getPathSegments();
            if(context.size() == 2)
                return "document".equals(context.get(0));
            if(context.size() == 4)
            {
                if("tree".equals(context.get(0)))
                    flag = "document".equals(context.get(2));
                return flag;
            }
        }
        return false;
    }

    private static boolean isDocumentsProvider(Context context, String s)
    {
        Intent intent = new Intent("android.content.action.DOCUMENTS_PROVIDER");
        for(context = context.getPackageManager().queryIntentContentProviders(intent, 0).iterator(); context.hasNext();)
            if(s.equals(((ResolveInfo)context.next()).providerInfo.authority))
                return true;

        return false;
    }

    public static boolean isManageMode(Uri uri)
    {
        return uri.getBooleanQueryParameter("manage", false);
    }

    public static boolean isRootUri(Context context, Uri uri)
    {
        boolean flag = false;
        if(isContentUri(uri) && isDocumentsProvider(context, uri.getAuthority()))
        {
            context = uri.getPathSegments();
            if(context.size() == 2)
                flag = "root".equals(context.get(0));
            return flag;
        } else
        {
            return false;
        }
    }

    public static boolean isTreeUri(Uri uri)
    {
        boolean flag = false;
        uri = uri.getPathSegments();
        if(uri.size() >= 2)
            flag = "tree".equals(uri.get(0));
        return flag;
    }

    public static Uri moveDocument(ContentProviderClient contentproviderclient, Uri uri, Uri uri1, Uri uri2)
        throws RemoteException
    {
        Bundle bundle = new Bundle();
        bundle.putParcelable("uri", uri);
        bundle.putParcelable("parentUri", uri1);
        bundle.putParcelable("android.content.extra.TARGET_URI", uri2);
        return (Uri)contentproviderclient.call("android:moveDocument", null, bundle).getParcelable("uri");
    }

    public static Uri moveDocument(ContentResolver contentresolver, Uri uri, Uri uri1, Uri uri2)
        throws FileNotFoundException
    {
        ContentProviderClient contentproviderclient = contentresolver.acquireUnstableContentProviderClient(uri.getAuthority());
        uri = moveDocument(contentproviderclient, uri, uri1, uri2);
        ContentProviderClient.releaseQuietly(contentproviderclient);
        return uri;
        uri;
        Log.w("DocumentsContract", "Failed to move document", uri);
        rethrowIfNecessary(contentresolver, uri);
        ContentProviderClient.releaseQuietly(contentproviderclient);
        return null;
        contentresolver;
        ContentProviderClient.releaseQuietly(contentproviderclient);
        throw contentresolver;
    }

    public static AssetFileDescriptor openImageThumbnail(File file)
        throws FileNotFoundException
    {
        ParcelFileDescriptor parcelfiledescriptor;
        Object obj;
        long al[];
        Object obj1;
        parcelfiledescriptor = ParcelFileDescriptor.open(file, 0x10000000);
        obj = null;
        al = null;
        obj1 = obj;
        ExifInterface exifinterface = JVM INSTR new #614 <Class ExifInterface>;
        obj1 = obj;
        exifinterface.ExifInterface(file.getAbsolutePath());
        file = al;
        obj1 = obj;
        exifinterface.getAttributeInt("Orientation", -1);
        JVM INSTR tableswitch 3 8: default 84
    //                   3 148
    //                   4 86
    //                   5 86
    //                   6 121
    //                   7 86
    //                   8 176;
           goto _L1 _L2 _L3 _L3 _L4 _L3 _L5
_L1:
        file = al;
_L3:
        obj1 = file;
        al = exifinterface.getThumbnailRange();
        obj1 = file;
        if(al != null)
        {
            obj1 = file;
            Bundle bundle;
            try
            {
                return new AssetFileDescriptor(parcelfiledescriptor, al[0], al[1], file);
            }
            // Misplaced declaration of an exception variable
            catch(File file) { }
        }
        break; /* Loop/switch isn't completed */
_L4:
        obj1 = obj;
        bundle = new Bundle(1);
        file = bundle;
        bundle.putInt("android.provider.extra.ORIENTATION", 90);
        file = bundle;
        continue; /* Loop/switch isn't completed */
_L2:
        obj1 = obj;
        bundle = new Bundle(1);
        file = bundle;
        bundle.putInt("android.provider.extra.ORIENTATION", 180);
        file = bundle;
        continue; /* Loop/switch isn't completed */
_L5:
        obj1 = obj;
        bundle = new Bundle(1);
        file = bundle;
        bundle.putInt("android.provider.extra.ORIENTATION", 270);
        file = bundle;
        if(true) goto _L3; else goto _L6
_L6:
        return new AssetFileDescriptor(parcelfiledescriptor, 0L, -1L, ((Bundle) (obj1)));
        IOException ioexception;
        ioexception;
        ioexception = file;
        if(true) goto _L6; else goto _L7
_L7:
    }

    public static void removeDocument(ContentProviderClient contentproviderclient, Uri uri, Uri uri1)
        throws RemoteException
    {
        Bundle bundle = new Bundle();
        bundle.putParcelable("uri", uri);
        bundle.putParcelable("parentUri", uri1);
        contentproviderclient.call("android:removeDocument", null, bundle);
    }

    public static boolean removeDocument(ContentResolver contentresolver, Uri uri, Uri uri1)
        throws FileNotFoundException
    {
        ContentProviderClient contentproviderclient = contentresolver.acquireUnstableContentProviderClient(uri.getAuthority());
        removeDocument(contentproviderclient, uri, uri1);
        ContentProviderClient.releaseQuietly(contentproviderclient);
        return true;
        uri;
        Log.w("DocumentsContract", "Failed to remove document", uri);
        rethrowIfNecessary(contentresolver, uri);
        ContentProviderClient.releaseQuietly(contentproviderclient);
        return false;
        contentresolver;
        ContentProviderClient.releaseQuietly(contentproviderclient);
        throw contentresolver;
    }

    public static Uri renameDocument(ContentProviderClient contentproviderclient, Uri uri, String s)
        throws RemoteException
    {
        Bundle bundle = new Bundle();
        bundle.putParcelable("uri", uri);
        bundle.putString("_display_name", s);
        contentproviderclient = (Uri)contentproviderclient.call("android:renameDocument", null, bundle).getParcelable("uri");
        if(contentproviderclient == null)
            contentproviderclient = uri;
        return contentproviderclient;
    }

    public static Uri renameDocument(ContentResolver contentresolver, Uri uri, String s)
        throws FileNotFoundException
    {
        ContentProviderClient contentproviderclient = contentresolver.acquireUnstableContentProviderClient(uri.getAuthority());
        uri = renameDocument(contentproviderclient, uri, s);
        ContentProviderClient.releaseQuietly(contentproviderclient);
        return uri;
        uri;
        Log.w("DocumentsContract", "Failed to rename document", uri);
        rethrowIfNecessary(contentresolver, uri);
        ContentProviderClient.releaseQuietly(contentproviderclient);
        return null;
        contentresolver;
        ContentProviderClient.releaseQuietly(contentproviderclient);
        throw contentresolver;
    }

    private static void rethrowIfNecessary(ContentResolver contentresolver, Exception exception)
        throws FileNotFoundException
    {
        if(contentresolver.getTargetSdkVersion() < 26) goto _L2; else goto _L1
_L1:
        if(!(exception instanceof ParcelableException)) goto _L4; else goto _L3
_L3:
        ((ParcelableException)exception).maybeRethrow(java/io/FileNotFoundException);
_L2:
        return;
_L4:
        if(!(exception instanceof RemoteException))
            break; /* Loop/switch isn't completed */
        ((RemoteException)exception).rethrowAsRuntimeException();
        if(true) goto _L2; else goto _L5
_L5:
        if(!(exception instanceof RuntimeException)) goto _L2; else goto _L6
_L6:
        throw (RuntimeException)exception;
    }

    public static Uri setManageMode(Uri uri)
    {
        return uri.buildUpon().appendQueryParameter("manage", "true").build();
    }

    public static final String ACTION_DOCUMENT_ROOT_SETTINGS = "android.provider.action.DOCUMENT_ROOT_SETTINGS";
    public static final String ACTION_DOCUMENT_SETTINGS = "android.provider.action.DOCUMENT_SETTINGS";
    public static final String ACTION_MANAGE_DOCUMENT = "android.provider.action.MANAGE_DOCUMENT";
    public static final String EXTERNAL_STORAGE_PROVIDER_AUTHORITY = "com.android.externalstorage.documents";
    public static final String EXTRA_ERROR = "error";
    public static final String EXTRA_EXCLUDE_SELF = "android.provider.extra.EXCLUDE_SELF";
    public static final String EXTRA_INFO = "info";
    public static final String EXTRA_INITIAL_URI = "android.provider.extra.INITIAL_URI";
    public static final String EXTRA_LOADING = "loading";
    public static final String EXTRA_METADATA_TAGS = "android:documentMetadataTags";
    public static final String EXTRA_OPTIONS = "options";
    public static final String EXTRA_ORIENTATION = "android.provider.extra.ORIENTATION";
    public static final String EXTRA_PACKAGE_NAME = "android.content.extra.PACKAGE_NAME";
    public static final String EXTRA_PARENT_URI = "parentUri";
    public static final String EXTRA_PROMPT = "android.provider.extra.PROMPT";
    public static final String EXTRA_RESULT = "result";
    public static final String EXTRA_SHOW_ADVANCED = "android.content.extra.SHOW_ADVANCED";
    public static final String EXTRA_TARGET_URI = "android.content.extra.TARGET_URI";
    public static final String EXTRA_URI = "uri";
    public static final String METADATA_EXIF = "android:documentExif";
    public static final String METADATA_TYPES = "android:documentMetadataType";
    public static final String METHOD_COPY_DOCUMENT = "android:copyDocument";
    public static final String METHOD_CREATE_DOCUMENT = "android:createDocument";
    public static final String METHOD_CREATE_WEB_LINK_INTENT = "android:createWebLinkIntent";
    public static final String METHOD_DELETE_DOCUMENT = "android:deleteDocument";
    public static final String METHOD_EJECT_ROOT = "android:ejectRoot";
    public static final String METHOD_FIND_DOCUMENT_PATH = "android:findDocumentPath";
    public static final String METHOD_GET_DOCUMENT_METADATA = "android:getDocumentMetadata";
    public static final String METHOD_IS_CHILD_DOCUMENT = "android:isChildDocument";
    public static final String METHOD_MOVE_DOCUMENT = "android:moveDocument";
    public static final String METHOD_REMOVE_DOCUMENT = "android:removeDocument";
    public static final String METHOD_RENAME_DOCUMENT = "android:renameDocument";
    public static final String PACKAGE_DOCUMENTS_UI = "com.android.documentsui";
    private static final String PARAM_MANAGE = "manage";
    private static final String PARAM_QUERY = "query";
    private static final String PATH_CHILDREN = "children";
    private static final String PATH_DOCUMENT = "document";
    private static final String PATH_RECENT = "recent";
    private static final String PATH_ROOT = "root";
    private static final String PATH_SEARCH = "search";
    private static final String PATH_TREE = "tree";
    public static final String PROVIDER_INTERFACE = "android.content.action.DOCUMENTS_PROVIDER";
    private static final String TAG = "DocumentsContract";
    private static final int THUMBNAIL_BUFFER_SIZE = 0x20000;
}
