// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.content.res.AssetFileDescriptor;
import android.database.CrossProcessCursorWrapper;
import android.database.Cursor;
import android.net.Uri;
import android.os.*;
import android.util.Log;
import com.android.internal.util.Preconditions;
import dalvik.system.CloseGuard;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package android.content:
//            ContentResolver, OperationApplicationException, IContentProvider, ContentProvider, 
//            ContentProviderResult, ContentValues

public class ContentProviderClient
    implements AutoCloseable
{
    private final class CursorWrapperInner extends CrossProcessCursorWrapper
    {

        public void close()
        {
            mCloseGuard.close();
            super.close();
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
        final ContentProviderClient this$0;

        CursorWrapperInner(Cursor cursor)
        {
            this$0 = ContentProviderClient.this;
            super(cursor);
            mCloseGuard.open("close");
        }
    }

    private class NotRespondingRunnable
        implements Runnable
    {

        public void run()
        {
            Log.w("ContentProviderClient", (new StringBuilder()).append("Detected provider not responding: ").append(ContentProviderClient._2D_get0(ContentProviderClient.this)).toString());
            ContentProviderClient._2D_get1(ContentProviderClient.this).appNotRespondingViaProvider(ContentProviderClient._2D_get0(ContentProviderClient.this));
        }

        final ContentProviderClient this$0;

        private NotRespondingRunnable()
        {
            this$0 = ContentProviderClient.this;
            super();
        }

        NotRespondingRunnable(NotRespondingRunnable notrespondingrunnable)
        {
            this();
        }
    }


    static IContentProvider _2D_get0(ContentProviderClient contentproviderclient)
    {
        return contentproviderclient.mContentProvider;
    }

    static ContentResolver _2D_get1(ContentProviderClient contentproviderclient)
    {
        return contentproviderclient.mContentResolver;
    }

    public ContentProviderClient(ContentResolver contentresolver, IContentProvider icontentprovider, boolean flag)
    {
        mContentResolver = contentresolver;
        mContentProvider = icontentprovider;
        mPackageName = contentresolver.mPackageName;
        mStable = flag;
        mCloseGuard.open("close");
    }

    private void afterRemote()
    {
        if(mAnrRunnable != null)
            sAnrHandler.removeCallbacks(mAnrRunnable);
    }

    private void beforeRemote()
    {
        if(mAnrRunnable != null)
            sAnrHandler.postDelayed(mAnrRunnable, mAnrTimeout);
    }

    private boolean closeInternal()
    {
        mCloseGuard.close();
        if(mClosed.compareAndSet(false, true))
        {
            if(mStable)
                return mContentResolver.releaseProvider(mContentProvider);
            else
                return mContentResolver.releaseUnstableProvider(mContentProvider);
        } else
        {
            return false;
        }
    }

    public static void releaseQuietly(ContentProviderClient contentproviderclient)
    {
        if(contentproviderclient == null)
            break MISSING_BLOCK_LABEL_9;
        contentproviderclient.release();
_L2:
        return;
        contentproviderclient;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public ContentProviderResult[] applyBatch(ArrayList arraylist)
        throws RemoteException, OperationApplicationException
    {
        Preconditions.checkNotNull(arraylist, "operations");
        beforeRemote();
        arraylist = mContentProvider.applyBatch(mPackageName, arraylist);
        afterRemote();
        return arraylist;
        arraylist;
        if(!mStable)
            mContentResolver.unstableProviderDied(mContentProvider);
        throw arraylist;
        arraylist;
        afterRemote();
        throw arraylist;
    }

    public int bulkInsert(Uri uri, ContentValues acontentvalues[])
        throws RemoteException
    {
        Preconditions.checkNotNull(uri, "url");
        Preconditions.checkNotNull(acontentvalues, "initialValues");
        beforeRemote();
        int i = mContentProvider.bulkInsert(mPackageName, uri, acontentvalues);
        afterRemote();
        return i;
        uri;
        if(!mStable)
            mContentResolver.unstableProviderDied(mContentProvider);
        throw uri;
        uri;
        afterRemote();
        throw uri;
    }

    public Bundle call(String s, String s1, Bundle bundle)
        throws RemoteException
    {
        Preconditions.checkNotNull(s, "method");
        beforeRemote();
        s = mContentProvider.call(mPackageName, s, s1, bundle);
        afterRemote();
        return s;
        s;
        if(!mStable)
            mContentResolver.unstableProviderDied(mContentProvider);
        throw s;
        s;
        afterRemote();
        throw s;
    }

    public final Uri canonicalize(Uri uri)
        throws RemoteException
    {
        Preconditions.checkNotNull(uri, "url");
        beforeRemote();
        uri = mContentProvider.canonicalize(mPackageName, uri);
        afterRemote();
        return uri;
        uri;
        if(!mStable)
            mContentResolver.unstableProviderDied(mContentProvider);
        throw uri;
        uri;
        afterRemote();
        throw uri;
    }

    public void close()
    {
        closeInternal();
    }

    public int delete(Uri uri, String s, String as[])
        throws RemoteException
    {
        Preconditions.checkNotNull(uri, "url");
        beforeRemote();
        int i = mContentProvider.delete(mPackageName, uri, s, as);
        afterRemote();
        return i;
        uri;
        if(!mStable)
            mContentResolver.unstableProviderDied(mContentProvider);
        throw uri;
        uri;
        afterRemote();
        throw uri;
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

    public ContentProvider getLocalContentProvider()
    {
        return ContentProvider.coerceToLocalContentProvider(mContentProvider);
    }

    public String[] getStreamTypes(Uri uri, String s)
        throws RemoteException
    {
        Preconditions.checkNotNull(uri, "url");
        Preconditions.checkNotNull(s, "mimeTypeFilter");
        beforeRemote();
        uri = mContentProvider.getStreamTypes(uri, s);
        afterRemote();
        return uri;
        uri;
        if(!mStable)
            mContentResolver.unstableProviderDied(mContentProvider);
        throw uri;
        uri;
        afterRemote();
        throw uri;
    }

    public String getType(Uri uri)
        throws RemoteException
    {
        Preconditions.checkNotNull(uri, "url");
        beforeRemote();
        uri = mContentProvider.getType(uri);
        afterRemote();
        return uri;
        uri;
        if(!mStable)
            mContentResolver.unstableProviderDied(mContentProvider);
        throw uri;
        uri;
        afterRemote();
        throw uri;
    }

    public Uri insert(Uri uri, ContentValues contentvalues)
        throws RemoteException
    {
        Preconditions.checkNotNull(uri, "url");
        beforeRemote();
        uri = mContentProvider.insert(mPackageName, uri, contentvalues);
        afterRemote();
        return uri;
        uri;
        if(!mStable)
            mContentResolver.unstableProviderDied(mContentProvider);
        throw uri;
        uri;
        afterRemote();
        throw uri;
    }

    public AssetFileDescriptor openAssetFile(Uri uri, String s)
        throws RemoteException, FileNotFoundException
    {
        return openAssetFile(uri, s, null);
    }

    public AssetFileDescriptor openAssetFile(Uri uri, String s, CancellationSignal cancellationsignal)
        throws RemoteException, FileNotFoundException
    {
        android.os.ICancellationSignal icancellationsignal;
        Preconditions.checkNotNull(uri, "url");
        Preconditions.checkNotNull(s, "mode");
        beforeRemote();
        icancellationsignal = null;
        if(cancellationsignal == null)
            break MISSING_BLOCK_LABEL_46;
        cancellationsignal.throwIfCanceled();
        icancellationsignal = mContentProvider.createCancellationSignal();
        cancellationsignal.setRemote(icancellationsignal);
        uri = mContentProvider.openAssetFile(mPackageName, uri, s, icancellationsignal);
        afterRemote();
        return uri;
        uri;
        if(!mStable)
            mContentResolver.unstableProviderDied(mContentProvider);
        throw uri;
        uri;
        afterRemote();
        throw uri;
    }

    public ParcelFileDescriptor openFile(Uri uri, String s)
        throws RemoteException, FileNotFoundException
    {
        return openFile(uri, s, null);
    }

    public ParcelFileDescriptor openFile(Uri uri, String s, CancellationSignal cancellationsignal)
        throws RemoteException, FileNotFoundException
    {
        android.os.ICancellationSignal icancellationsignal;
        Preconditions.checkNotNull(uri, "url");
        Preconditions.checkNotNull(s, "mode");
        beforeRemote();
        icancellationsignal = null;
        if(cancellationsignal == null)
            break MISSING_BLOCK_LABEL_46;
        cancellationsignal.throwIfCanceled();
        icancellationsignal = mContentProvider.createCancellationSignal();
        cancellationsignal.setRemote(icancellationsignal);
        uri = mContentProvider.openFile(mPackageName, uri, s, icancellationsignal, null);
        afterRemote();
        return uri;
        uri;
        if(!mStable)
            mContentResolver.unstableProviderDied(mContentProvider);
        throw uri;
        uri;
        afterRemote();
        throw uri;
    }

    public final AssetFileDescriptor openTypedAssetFileDescriptor(Uri uri, String s, Bundle bundle)
        throws RemoteException, FileNotFoundException
    {
        return openTypedAssetFileDescriptor(uri, s, bundle, null);
    }

    public final AssetFileDescriptor openTypedAssetFileDescriptor(Uri uri, String s, Bundle bundle, CancellationSignal cancellationsignal)
        throws RemoteException, FileNotFoundException
    {
        android.os.ICancellationSignal icancellationsignal;
        Preconditions.checkNotNull(uri, "uri");
        Preconditions.checkNotNull(s, "mimeType");
        beforeRemote();
        icancellationsignal = null;
        if(cancellationsignal == null)
            break MISSING_BLOCK_LABEL_49;
        cancellationsignal.throwIfCanceled();
        icancellationsignal = mContentProvider.createCancellationSignal();
        cancellationsignal.setRemote(icancellationsignal);
        uri = mContentProvider.openTypedAssetFile(mPackageName, uri, s, bundle, icancellationsignal);
        afterRemote();
        return uri;
        uri;
        if(!mStable)
            mContentResolver.unstableProviderDied(mContentProvider);
        throw uri;
        uri;
        afterRemote();
        throw uri;
    }

    public Cursor query(Uri uri, String as[], Bundle bundle, CancellationSignal cancellationsignal)
        throws RemoteException
    {
        android.os.ICancellationSignal icancellationsignal;
        Preconditions.checkNotNull(uri, "url");
        beforeRemote();
        icancellationsignal = null;
        if(cancellationsignal == null)
            break MISSING_BLOCK_LABEL_42;
        cancellationsignal.throwIfCanceled();
        icancellationsignal = mContentProvider.createCancellationSignal();
        cancellationsignal.setRemote(icancellationsignal);
        uri = mContentProvider.query(mPackageName, uri, as, bundle, icancellationsignal);
        if(uri == null)
        {
            afterRemote();
            return null;
        }
        uri = new CursorWrapperInner(uri);
        afterRemote();
        return uri;
        uri;
        if(!mStable)
            mContentResolver.unstableProviderDied(mContentProvider);
        throw uri;
        uri;
        afterRemote();
        throw uri;
    }

    public Cursor query(Uri uri, String as[], String s, String as1[], String s1)
        throws RemoteException
    {
        return query(uri, as, s, as1, s1, null);
    }

    public Cursor query(Uri uri, String as[], String s, String as1[], String s1, CancellationSignal cancellationsignal)
        throws RemoteException
    {
        return query(uri, as, ContentResolver.createSqlQueryBundle(s, as1, s1), cancellationsignal);
    }

    public boolean refresh(Uri uri, Bundle bundle, CancellationSignal cancellationsignal)
        throws RemoteException
    {
        android.os.ICancellationSignal icancellationsignal;
        Preconditions.checkNotNull(uri, "url");
        beforeRemote();
        icancellationsignal = null;
        if(cancellationsignal == null)
            break MISSING_BLOCK_LABEL_39;
        cancellationsignal.throwIfCanceled();
        icancellationsignal = mContentProvider.createCancellationSignal();
        cancellationsignal.setRemote(icancellationsignal);
        boolean flag = mContentProvider.refresh(mPackageName, uri, bundle, icancellationsignal);
        afterRemote();
        return flag;
        uri;
        if(!mStable)
            mContentResolver.unstableProviderDied(mContentProvider);
        throw uri;
        uri;
        afterRemote();
        throw uri;
    }

    public boolean release()
    {
        return closeInternal();
    }

    public void setDetectNotResponding(long l)
    {
        android/content/ContentProviderClient;
        JVM INSTR monitorenter ;
        mAnrTimeout = l;
        if(l <= 0L) goto _L2; else goto _L1
_L1:
        if(mAnrRunnable == null)
        {
            NotRespondingRunnable notrespondingrunnable = JVM INSTR new #11  <Class ContentProviderClient$NotRespondingRunnable>;
            notrespondingrunnable.this. NotRespondingRunnable(null);
            mAnrRunnable = notrespondingrunnable;
        }
        if(sAnrHandler == null)
        {
            Handler handler = JVM INSTR new #83  <Class Handler>;
            handler.Handler(Looper.getMainLooper(), null, true);
            sAnrHandler = handler;
        }
_L4:
        android/content/ContentProviderClient;
        JVM INSTR monitorexit ;
        return;
_L2:
        mAnrRunnable = null;
        if(true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public final Uri uncanonicalize(Uri uri)
        throws RemoteException
    {
        Preconditions.checkNotNull(uri, "url");
        beforeRemote();
        uri = mContentProvider.uncanonicalize(mPackageName, uri);
        afterRemote();
        return uri;
        uri;
        if(!mStable)
            mContentResolver.unstableProviderDied(mContentProvider);
        throw uri;
        uri;
        afterRemote();
        throw uri;
    }

    public int update(Uri uri, ContentValues contentvalues, String s, String as[])
        throws RemoteException
    {
        Preconditions.checkNotNull(uri, "url");
        beforeRemote();
        int i = mContentProvider.update(mPackageName, uri, contentvalues, s, as);
        afterRemote();
        return i;
        uri;
        if(!mStable)
            mContentResolver.unstableProviderDied(mContentProvider);
        throw uri;
        uri;
        afterRemote();
        throw uri;
    }

    private static final String TAG = "ContentProviderClient";
    private static Handler sAnrHandler;
    private NotRespondingRunnable mAnrRunnable;
    private long mAnrTimeout;
    private final CloseGuard mCloseGuard = CloseGuard.get();
    private final AtomicBoolean mClosed = new AtomicBoolean();
    private final IContentProvider mContentProvider;
    private final ContentResolver mContentResolver;
    private final String mPackageName;
    private final boolean mStable;
}
