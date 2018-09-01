// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.database.Cursor;
import android.net.Uri;
import android.os.*;
import android.util.Log;
import java.lang.ref.WeakReference;

// Referenced classes of package android.content:
//            ContentResolver, ContentValues

public abstract class AsyncQueryHandler extends Handler
{
    protected static final class WorkerArgs
    {

        public Object cookie;
        public Handler handler;
        public String orderBy;
        public String projection[];
        public Object result;
        public String selection;
        public String selectionArgs[];
        public Uri uri;
        public ContentValues values;

        protected WorkerArgs()
        {
        }
    }

    protected class WorkerHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            Object obj;
            WorkerArgs workerargs;
            int i;
            obj = (ContentResolver)mResolver.get();
            if(obj == null)
                return;
            workerargs = (WorkerArgs)message.obj;
            i = message.what;
            message.arg1;
            JVM INSTR tableswitch 1 4: default 68
        //                       1 96
        //                       2 164
        //                       3 183
        //                       4 213;
               goto _L1 _L2 _L3 _L4 _L5
_L1:
            obj = workerargs.handler.obtainMessage(i);
            obj.obj = workerargs;
            obj.arg1 = message.arg1;
            ((Message) (obj)).sendToTarget();
            return;
_L2:
            Cursor cursor = ((ContentResolver) (obj)).query(workerargs.uri, workerargs.projection, workerargs.selection, workerargs.selectionArgs, workerargs.orderBy);
            obj = cursor;
            if(cursor == null)
                break MISSING_BLOCK_LABEL_141;
            cursor.getCount();
            obj = cursor;
_L6:
            workerargs.result = obj;
            continue; /* Loop/switch isn't completed */
            obj;
            Log.w("AsyncQuery", "Exception thrown during handling EVENT_ARG_QUERY", ((Throwable) (obj)));
            obj = null;
            if(true) goto _L6; else goto _L3
_L3:
            workerargs.result = ((ContentResolver) (obj)).insert(workerargs.uri, workerargs.values);
            continue; /* Loop/switch isn't completed */
_L4:
            workerargs.result = Integer.valueOf(((ContentResolver) (obj)).update(workerargs.uri, workerargs.values, workerargs.selection, workerargs.selectionArgs));
            continue; /* Loop/switch isn't completed */
_L5:
            workerargs.result = Integer.valueOf(((ContentResolver) (obj)).delete(workerargs.uri, workerargs.selection, workerargs.selectionArgs));
            if(true) goto _L1; else goto _L7
_L7:
        }

        final AsyncQueryHandler this$0;

        public WorkerHandler(Looper looper)
        {
            this$0 = AsyncQueryHandler.this;
            super(looper);
        }
    }


    public AsyncQueryHandler(ContentResolver contentresolver)
    {
        mResolver = new WeakReference(contentresolver);
        android/content/AsyncQueryHandler;
        JVM INSTR monitorenter ;
        if(sLooper == null)
        {
            contentresolver = JVM INSTR new #51  <Class HandlerThread>;
            contentresolver.HandlerThread("AsyncQueryWorker");
            contentresolver.start();
            sLooper = contentresolver.getLooper();
        }
        android/content/AsyncQueryHandler;
        JVM INSTR monitorexit ;
        mWorkerThreadHandler = createHandler(sLooper);
        return;
        contentresolver;
        throw contentresolver;
    }

    public final void cancelOperation(int i)
    {
        mWorkerThreadHandler.removeMessages(i);
    }

    protected Handler createHandler(Looper looper)
    {
        return new WorkerHandler(looper);
    }

    public void handleMessage(Message message)
    {
        WorkerArgs workerargs;
        int i;
        workerargs = (WorkerArgs)message.obj;
        i = message.what;
        message.arg1;
        JVM INSTR tableswitch 1 4: default 48
    //                   1 49
    //                   2 68
    //                   3 87
    //                   4 109;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        return;
_L2:
        onQueryComplete(i, workerargs.cookie, (Cursor)workerargs.result);
        continue; /* Loop/switch isn't completed */
_L3:
        onInsertComplete(i, workerargs.cookie, (Uri)workerargs.result);
        continue; /* Loop/switch isn't completed */
_L4:
        onUpdateComplete(i, workerargs.cookie, ((Integer)workerargs.result).intValue());
        continue; /* Loop/switch isn't completed */
_L5:
        onDeleteComplete(i, workerargs.cookie, ((Integer)workerargs.result).intValue());
        if(true) goto _L1; else goto _L6
_L6:
    }

    protected void onDeleteComplete(int i, Object obj, int j)
    {
    }

    protected void onInsertComplete(int i, Object obj, Uri uri)
    {
    }

    protected void onQueryComplete(int i, Object obj, Cursor cursor)
    {
    }

    protected void onUpdateComplete(int i, Object obj, int j)
    {
    }

    public final void startDelete(int i, Object obj, Uri uri, String s, String as[])
    {
        Message message = mWorkerThreadHandler.obtainMessage(i);
        message.arg1 = 4;
        WorkerArgs workerargs = new WorkerArgs();
        workerargs.handler = this;
        workerargs.uri = uri;
        workerargs.cookie = obj;
        workerargs.selection = s;
        workerargs.selectionArgs = as;
        message.obj = workerargs;
        mWorkerThreadHandler.sendMessage(message);
    }

    public final void startInsert(int i, Object obj, Uri uri, ContentValues contentvalues)
    {
        Message message = mWorkerThreadHandler.obtainMessage(i);
        message.arg1 = 2;
        WorkerArgs workerargs = new WorkerArgs();
        workerargs.handler = this;
        workerargs.uri = uri;
        workerargs.cookie = obj;
        workerargs.values = contentvalues;
        message.obj = workerargs;
        mWorkerThreadHandler.sendMessage(message);
    }

    public void startQuery(int i, Object obj, Uri uri, String as[], String s, String as1[], String s1)
    {
        Message message = mWorkerThreadHandler.obtainMessage(i);
        message.arg1 = 1;
        WorkerArgs workerargs = new WorkerArgs();
        workerargs.handler = this;
        workerargs.uri = uri;
        workerargs.projection = as;
        workerargs.selection = s;
        workerargs.selectionArgs = as1;
        workerargs.orderBy = s1;
        workerargs.cookie = obj;
        message.obj = workerargs;
        mWorkerThreadHandler.sendMessage(message);
    }

    public final void startUpdate(int i, Object obj, Uri uri, ContentValues contentvalues, String s, String as[])
    {
        Message message = mWorkerThreadHandler.obtainMessage(i);
        message.arg1 = 3;
        WorkerArgs workerargs = new WorkerArgs();
        workerargs.handler = this;
        workerargs.uri = uri;
        workerargs.cookie = obj;
        workerargs.values = contentvalues;
        workerargs.selection = s;
        workerargs.selectionArgs = as;
        message.obj = workerargs;
        mWorkerThreadHandler.sendMessage(message);
    }

    private static final int EVENT_ARG_DELETE = 4;
    private static final int EVENT_ARG_INSERT = 2;
    private static final int EVENT_ARG_QUERY = 1;
    private static final int EVENT_ARG_UPDATE = 3;
    private static final String TAG = "AsyncQuery";
    private static final boolean localLOGV = false;
    private static Looper sLooper = null;
    final WeakReference mResolver;
    private Handler mWorkerThreadHandler;

}
