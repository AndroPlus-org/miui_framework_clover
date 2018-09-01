// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database;

import android.net.Uri;
import android.os.Handler;
import android.os.UserHandle;

// Referenced classes of package android.database:
//            IContentObserver

public abstract class ContentObserver
{
    private final class NotificationRunnable
        implements Runnable
    {

        public void run()
        {
            onChange(mSelfChange, mUri, mUserId);
        }

        private final boolean mSelfChange;
        private final Uri mUri;
        private final int mUserId;
        final ContentObserver this$0;

        public NotificationRunnable(boolean flag, Uri uri, int i)
        {
            this$0 = ContentObserver.this;
            super();
            mSelfChange = flag;
            mUri = uri;
            mUserId = i;
        }
    }

    private static final class Transport extends IContentObserver.Stub
    {

        public void onChange(boolean flag, Uri uri, int i)
        {
            ContentObserver contentobserver = mContentObserver;
            if(contentobserver != null)
                ContentObserver._2D_wrap0(contentobserver, flag, uri, i);
        }

        public void releaseContentObserver()
        {
            mContentObserver = null;
        }

        private ContentObserver mContentObserver;

        public Transport(ContentObserver contentobserver)
        {
            mContentObserver = contentobserver;
        }
    }


    static void _2D_wrap0(ContentObserver contentobserver, boolean flag, Uri uri, int i)
    {
        contentobserver.dispatchChange(flag, uri, i);
    }

    public ContentObserver(Handler handler)
    {
        mHandler = handler;
    }

    private void dispatchChange(boolean flag, Uri uri, int i)
    {
        if(mHandler == null)
            onChange(flag, uri, i);
        else
            mHandler.post(new NotificationRunnable(flag, uri, i));
    }

    public boolean deliverSelfNotifications()
    {
        return false;
    }

    public final void dispatchChange(boolean flag)
    {
        dispatchChange(flag, null);
    }

    public final void dispatchChange(boolean flag, Uri uri)
    {
        dispatchChange(flag, uri, UserHandle.getCallingUserId());
    }

    public IContentObserver getContentObserver()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        Transport transport1;
        if(mTransport == null)
        {
            Transport transport = JVM INSTR new #9   <Class ContentObserver$Transport>;
            transport.Transport(this);
            mTransport = transport;
        }
        transport1 = mTransport;
        obj;
        JVM INSTR monitorexit ;
        return transport1;
        Exception exception;
        exception;
        throw exception;
    }

    public void onChange(boolean flag)
    {
    }

    public void onChange(boolean flag, Uri uri)
    {
        onChange(flag);
    }

    public void onChange(boolean flag, Uri uri, int i)
    {
        onChange(flag, uri);
    }

    public IContentObserver releaseContentObserver()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        Transport transport = mTransport;
        if(transport == null)
            break MISSING_BLOCK_LABEL_25;
        transport.releaseContentObserver();
        mTransport = null;
        obj;
        JVM INSTR monitorexit ;
        return transport;
        Exception exception;
        exception;
        throw exception;
    }

    Handler mHandler;
    private final Object mLock = new Object();
    private Transport mTransport;
}
