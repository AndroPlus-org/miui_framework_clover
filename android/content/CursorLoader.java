// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.database.Cursor;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.OperationCanceledException;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Arrays;

// Referenced classes of package android.content:
//            AsyncTaskLoader, Context, ContentResolver

public class CursorLoader extends AsyncTaskLoader
{

    public CursorLoader(Context context)
    {
        super(context);
        mObserver = new Loader.ForceLoadContentObserver(this);
    }

    public CursorLoader(Context context, Uri uri, String as[], String s, String as1[], String s1)
    {
        super(context);
        mObserver = new Loader.ForceLoadContentObserver(this);
        mUri = uri;
        mProjection = as;
        mSelection = s;
        mSelectionArgs = as1;
        mSortOrder = s1;
    }

    public void cancelLoadInBackground()
    {
        super.cancelLoadInBackground();
        this;
        JVM INSTR monitorenter ;
        if(mCancellationSignal != null)
            mCancellationSignal.cancel();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void deliverResult(Cursor cursor)
    {
        if(isReset())
        {
            if(cursor != null)
                cursor.close();
            return;
        }
        Cursor cursor1 = mCursor;
        mCursor = cursor;
        if(isStarted())
            super.deliverResult(cursor);
        if(cursor1 != null && cursor1 != cursor && cursor1.isClosed() ^ true)
            cursor1.close();
    }

    public volatile void deliverResult(Object obj)
    {
        deliverResult((Cursor)obj);
    }

    public void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        super.dump(s, filedescriptor, printwriter, as);
        printwriter.print(s);
        printwriter.print("mUri=");
        printwriter.println(mUri);
        printwriter.print(s);
        printwriter.print("mProjection=");
        printwriter.println(Arrays.toString(mProjection));
        printwriter.print(s);
        printwriter.print("mSelection=");
        printwriter.println(mSelection);
        printwriter.print(s);
        printwriter.print("mSelectionArgs=");
        printwriter.println(Arrays.toString(mSelectionArgs));
        printwriter.print(s);
        printwriter.print("mSortOrder=");
        printwriter.println(mSortOrder);
        printwriter.print(s);
        printwriter.print("mCursor=");
        printwriter.println(mCursor);
        printwriter.print(s);
        printwriter.print("mContentChanged=");
        printwriter.println(mContentChanged);
    }

    public String[] getProjection()
    {
        return mProjection;
    }

    public String getSelection()
    {
        return mSelection;
    }

    public String[] getSelectionArgs()
    {
        return mSelectionArgs;
    }

    public String getSortOrder()
    {
        return mSortOrder;
    }

    public Uri getUri()
    {
        return mUri;
    }

    public Cursor loadInBackground()
    {
        this;
        JVM INSTR monitorenter ;
        if(isLoadInBackgroundCanceled())
        {
            OperationCanceledException operationcanceledexception = JVM INSTR new #137 <Class OperationCanceledException>;
            operationcanceledexception.OperationCanceledException();
            throw operationcanceledexception;
        }
        break MISSING_BLOCK_LABEL_24;
        Object obj;
        obj;
        this;
        JVM INSTR monitorexit ;
        throw obj;
        CancellationSignal cancellationsignal = JVM INSTR new #51  <Class CancellationSignal>;
        cancellationsignal.CancellationSignal();
        mCancellationSignal = cancellationsignal;
        this;
        JVM INSTR monitorexit ;
        Cursor cursor = getContext().getContentResolver().query(mUri, mProjection, mSelection, mSelectionArgs, mSortOrder, mCancellationSignal);
        if(cursor == null)
            break MISSING_BLOCK_LABEL_95;
        cursor.getCount();
        cursor.registerContentObserver(mObserver);
        this;
        JVM INSTR monitorenter ;
        mCancellationSignal = null;
        this;
        JVM INSTR monitorexit ;
        return cursor;
        cancellationsignal;
        cursor.close();
        throw cancellationsignal;
        cancellationsignal;
        this;
        JVM INSTR monitorenter ;
        mCancellationSignal = null;
        this;
        JVM INSTR monitorexit ;
        throw cancellationsignal;
        cancellationsignal;
        throw cancellationsignal;
        cancellationsignal;
        throw cancellationsignal;
    }

    public volatile Object loadInBackground()
    {
        return loadInBackground();
    }

    public void onCanceled(Cursor cursor)
    {
        if(cursor != null && cursor.isClosed() ^ true)
            cursor.close();
    }

    public volatile void onCanceled(Object obj)
    {
        onCanceled((Cursor)obj);
    }

    protected void onReset()
    {
        super.onReset();
        onStopLoading();
        if(mCursor != null && mCursor.isClosed() ^ true)
            mCursor.close();
        mCursor = null;
    }

    protected void onStartLoading()
    {
        if(mCursor != null)
            deliverResult(mCursor);
        if(takeContentChanged() || mCursor == null)
            forceLoad();
    }

    protected void onStopLoading()
    {
        cancelLoad();
    }

    public void setProjection(String as[])
    {
        mProjection = as;
    }

    public void setSelection(String s)
    {
        mSelection = s;
    }

    public void setSelectionArgs(String as[])
    {
        mSelectionArgs = as;
    }

    public void setSortOrder(String s)
    {
        mSortOrder = s;
    }

    public void setUri(Uri uri)
    {
        mUri = uri;
    }

    CancellationSignal mCancellationSignal;
    Cursor mCursor;
    final Loader.ForceLoadContentObserver mObserver;
    String mProjection[];
    String mSelection;
    String mSelectionArgs[];
    String mSortOrder;
    Uri mUri;
}
