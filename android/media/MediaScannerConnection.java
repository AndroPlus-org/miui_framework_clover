// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.content.*;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;

// Referenced classes of package android.media:
//            IMediaScannerService

public class MediaScannerConnection
    implements ServiceConnection
{
    static class ClientProxy
        implements MediaScannerConnectionClient
    {

        public void onMediaScannerConnected()
        {
            scanNextPath();
        }

        public void onScanCompleted(String s, Uri uri)
        {
            if(mClient != null)
                mClient.onScanCompleted(s, uri);
            scanNextPath();
        }

        void scanNextPath()
        {
            if(mNextPath >= mPaths.length)
            {
                mConnection.disconnect();
                mConnection = null;
                return;
            }
            String s;
            if(mMimeTypes != null)
                s = mMimeTypes[mNextPath];
            else
                s = null;
            mConnection.scanFile(mPaths[mNextPath], s);
            mNextPath = mNextPath + 1;
        }

        final OnScanCompletedListener mClient;
        MediaScannerConnection mConnection;
        final String mMimeTypes[];
        int mNextPath;
        final String mPaths[];

        ClientProxy(String as[], String as1[], OnScanCompletedListener onscancompletedlistener)
        {
            mPaths = as;
            mMimeTypes = as1;
            mClient = onscancompletedlistener;
        }
    }

    public static interface MediaScannerConnectionClient
        extends OnScanCompletedListener
    {

        public abstract void onMediaScannerConnected();

        public abstract void onScanCompleted(String s, Uri uri);
    }

    public static interface OnScanCompletedListener
    {

        public abstract void onScanCompleted(String s, Uri uri);
    }


    static MediaScannerConnectionClient _2D_get0(MediaScannerConnection mediascannerconnection)
    {
        return mediascannerconnection.mClient;
    }

    public MediaScannerConnection(Context context, MediaScannerConnectionClient mediascannerconnectionclient)
    {
        mContext = context;
        mClient = mediascannerconnectionclient;
    }

    public static void scanFile(Context context, String as[], String as1[], OnScanCompletedListener onscancompletedlistener)
    {
        as = new ClientProxy(as, as1, onscancompletedlistener);
        context = new MediaScannerConnection(context, as);
        as.mConnection = context;
        context.connect();
    }

    public void connect()
    {
        this;
        JVM INSTR monitorenter ;
        if(!mConnected)
        {
            Intent intent = JVM INSTR new #66  <Class Intent>;
            intent.Intent(android/media/IMediaScannerService.getName());
            ComponentName componentname = JVM INSTR new #79  <Class ComponentName>;
            componentname.ComponentName("com.android.providers.media", "com.android.providers.media.MediaScannerService");
            intent.setComponent(componentname);
            mContext.bindService(intent, this, 1);
            mConnected = true;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void disconnect()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = mConnected;
        if(!flag)
            break MISSING_BLOCK_LABEL_44;
        Exception exception;
        try
        {
            mContext.unbindService(this);
            if(mClient instanceof ClientProxy)
                mClient = null;
            mService = null;
        }
        catch(IllegalArgumentException illegalargumentexception) { }
        mConnected = false;
        this;
        JVM INSTR monitorexit ;
        return;
        exception;
        throw exception;
    }

    public boolean isConnected()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        if(mService == null)
            break MISSING_BLOCK_LABEL_18;
        flag = mConnected;
_L1:
        this;
        JVM INSTR monitorexit ;
        return flag;
        flag = false;
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    public void onServiceConnected(ComponentName componentname, IBinder ibinder)
    {
        this;
        JVM INSTR monitorenter ;
        mService = IMediaScannerService.Stub.asInterface(ibinder);
        if(mService != null && mClient != null)
            mClient.onMediaScannerConnected();
        this;
        JVM INSTR monitorexit ;
        return;
        componentname;
        throw componentname;
    }

    public void onServiceDisconnected(ComponentName componentname)
    {
        this;
        JVM INSTR monitorenter ;
        mService = null;
        this;
        JVM INSTR monitorexit ;
        return;
        componentname;
        throw componentname;
    }

    public void scanFile(String s, String s1)
    {
        this;
        JVM INSTR monitorenter ;
        if(mService == null || mConnected ^ true)
        {
            s = JVM INSTR new #124 <Class IllegalStateException>;
            s.IllegalStateException("not connected to MediaScannerService");
            throw s;
        }
        break MISSING_BLOCK_LABEL_35;
        s;
        this;
        JVM INSTR monitorexit ;
        throw s;
        try
        {
            mService.requestScanFile(s, s1, mListener);
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
        this;
        JVM INSTR monitorexit ;
    }

    private static final String TAG = "MediaScannerConnection";
    private MediaScannerConnectionClient mClient;
    private boolean mConnected;
    private Context mContext;
    private final IMediaScannerListener.Stub mListener = new IMediaScannerListener.Stub() {

        public void scanCompleted(String s, Uri uri)
        {
            MediaScannerConnectionClient mediascannerconnectionclient1 = MediaScannerConnection._2D_get0(MediaScannerConnection.this);
            if(mediascannerconnectionclient1 != null)
                mediascannerconnectionclient1.onScanCompleted(s, uri);
        }

        final MediaScannerConnection this$0;

            
            {
                this$0 = MediaScannerConnection.this;
                super();
            }
    }
;
    private IMediaScannerService mService;
}
