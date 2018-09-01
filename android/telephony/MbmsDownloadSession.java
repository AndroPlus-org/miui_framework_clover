// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.content.*;
import android.os.*;
import android.telephony.mbms.DownloadRequest;
import android.telephony.mbms.DownloadStateCallback;
import android.telephony.mbms.FileInfo;
import android.telephony.mbms.InternalDownloadSessionCallback;
import android.telephony.mbms.InternalDownloadStateCallback;
import android.telephony.mbms.MbmsDownloadSessionCallback;
import android.telephony.mbms.MbmsUtils;
import android.telephony.mbms.vendor.IMbmsDownloadService;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

// Referenced classes of package android.telephony:
//            SubscriptionManager

public class MbmsDownloadSession
    implements AutoCloseable
{

    static String _2D_get0()
    {
        return LOG_TAG;
    }

    static android.os.IBinder.DeathRecipient _2D_get1(MbmsDownloadSession mbmsdownloadsession)
    {
        return mbmsdownloadsession.mDeathRecipient;
    }

    static InternalDownloadSessionCallback _2D_get2(MbmsDownloadSession mbmsdownloadsession)
    {
        return mbmsdownloadsession.mInternalCallback;
    }

    static AtomicReference _2D_get3(MbmsDownloadSession mbmsdownloadsession)
    {
        return mbmsdownloadsession.mService;
    }

    static int _2D_get4(MbmsDownloadSession mbmsdownloadsession)
    {
        return mbmsdownloadsession.mSubscriptionId;
    }

    static AtomicBoolean _2D_get5()
    {
        return sIsInitialized;
    }

    static void _2D_wrap0(MbmsDownloadSession mbmsdownloadsession, int i, String s)
    {
        mbmsdownloadsession.sendErrorToApp(i, s);
    }

    private MbmsDownloadSession(Context context, MbmsDownloadSessionCallback mbmsdownloadsessioncallback, int i, Handler handler)
    {
        mSubscriptionId = -1;
        mDeathRecipient = new android.os.IBinder.DeathRecipient() {

            public void binderDied()
            {
                MbmsDownloadSession._2D_wrap0(MbmsDownloadSession.this, 3, "Received death notification");
            }

            final MbmsDownloadSession this$0;

            
            {
                this$0 = MbmsDownloadSession.this;
                super();
            }
        }
;
        mService = new AtomicReference(null);
        mContext = context;
        mSubscriptionId = i;
        context = handler;
        if(handler == null)
            context = new Handler(Looper.getMainLooper());
        mInternalCallback = new InternalDownloadSessionCallback(mbmsdownloadsessioncallback, context);
    }

    private int bindAndInitialize()
    {
        return MbmsUtils.startBinding(mContext, "android.telephony.action.EmbmsDownload", new ServiceConnection() {

            public void onServiceConnected(ComponentName componentname, IBinder ibinder)
            {
                componentname = android.telephony.mbms.vendor.IMbmsDownloadService.Stub.asInterface(ibinder);
                int i;
                try
                {
                    i = componentname.initialize(MbmsDownloadSession._2D_get4(MbmsDownloadSession.this), MbmsDownloadSession._2D_get2(MbmsDownloadSession.this));
                }
                // Misplaced declaration of an exception variable
                catch(ComponentName componentname)
                {
                    Log.e(MbmsDownloadSession._2D_get0(), "Service died before initialization");
                    MbmsDownloadSession._2D_get5().set(false);
                    return;
                }
                // Misplaced declaration of an exception variable
                catch(ComponentName componentname)
                {
                    Log.e(MbmsDownloadSession._2D_get0(), "Runtime exception during initialization");
                    MbmsDownloadSession._2D_wrap0(MbmsDownloadSession.this, 103, componentname.toString());
                    MbmsDownloadSession._2D_get5().set(false);
                    return;
                }
                if(i != 0)
                {
                    MbmsDownloadSession._2D_wrap0(MbmsDownloadSession.this, i, "Error returned during initialization");
                    MbmsDownloadSession._2D_get5().set(false);
                    return;
                }
                try
                {
                    componentname.asBinder().linkToDeath(MbmsDownloadSession._2D_get1(MbmsDownloadSession.this), 0);
                }
                // Misplaced declaration of an exception variable
                catch(ComponentName componentname)
                {
                    MbmsDownloadSession._2D_wrap0(MbmsDownloadSession.this, 3, "Middleware lost during initialization");
                    MbmsDownloadSession._2D_get5().set(false);
                    return;
                }
                MbmsDownloadSession._2D_get3(MbmsDownloadSession.this).set(componentname);
            }

            public void onServiceDisconnected(ComponentName componentname)
            {
                MbmsDownloadSession._2D_get5().set(false);
                MbmsDownloadSession._2D_get3(MbmsDownloadSession.this).set(null);
            }

            final MbmsDownloadSession this$0;

            
            {
                this$0 = MbmsDownloadSession.this;
                super();
            }
        }
);
    }

    public static MbmsDownloadSession create(Context context, MbmsDownloadSessionCallback mbmsdownloadsessioncallback, int i, Handler handler)
    {
        if(!sIsInitialized.compareAndSet(false, true))
            throw new IllegalStateException("Cannot have two active instances");
        context = new MbmsDownloadSession(context, mbmsdownloadsessioncallback, i, handler);
        i = context.bindAndInitialize();
        if(i != 0)
        {
            sIsInitialized.set(false);
            handler.post(new Runnable(mbmsdownloadsessioncallback, i) {

                public void run()
                {
                    callback.onError(result, null);
                }

                final MbmsDownloadSessionCallback val$callback;
                final int val$result;

            
            {
                callback = mbmsdownloadsessioncallback;
                result = i;
                super();
            }
            }
);
            return null;
        } else
        {
            return context;
        }
    }

    public static MbmsDownloadSession create(Context context, MbmsDownloadSessionCallback mbmsdownloadsessioncallback, Handler handler)
    {
        return create(context, mbmsdownloadsessioncallback, SubscriptionManager.getDefaultSubscriptionId(), handler);
    }

    private void deleteDownloadRequestToken(DownloadRequest downloadrequest)
    {
        downloadrequest = getDownloadRequestTokenPath(downloadrequest);
        if(!downloadrequest.isFile())
        {
            Log.w(LOG_TAG, (new StringBuilder()).append("Attempting to delete non-existent download token at ").append(downloadrequest).toString());
            return;
        }
        if(!downloadrequest.delete())
            Log.w(LOG_TAG, (new StringBuilder()).append("Couldn't delete download token at ").append(downloadrequest).toString());
    }

    private File getDownloadRequestTokenPath(DownloadRequest downloadrequest)
    {
        return new File(MbmsUtils.getEmbmsTempFileDirForService(mContext, downloadrequest.getFileServiceId()), (new StringBuilder()).append(downloadrequest.getHash()).append(".download_token").toString());
    }

    private void sendErrorToApp(int i, String s)
    {
        mInternalCallback.onError(i, s);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void validateTempFileRootSanity(File file)
        throws IOException
    {
        if(!file.exists())
            throw new IllegalArgumentException("Provided directory does not exist");
        if(!file.isDirectory())
            throw new IllegalArgumentException("Provided File is not a directory");
        file = file.getCanonicalPath();
        if(mContext.getDataDir().getCanonicalPath().equals(file))
            throw new IllegalArgumentException("Temp file root cannot be your data dir");
        if(mContext.getCacheDir().getCanonicalPath().equals(file))
            throw new IllegalArgumentException("Temp file root cannot be your cache dir");
        if(mContext.getFilesDir().getCanonicalPath().equals(file))
            throw new IllegalArgumentException("Temp file root cannot be your files dir");
        else
            return;
    }

    private void writeDownloadRequestToken(DownloadRequest downloadrequest)
    {
        File file = getDownloadRequestTokenPath(downloadrequest);
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        if(file.exists())
        {
            Log.w(LOG_TAG, (new StringBuilder()).append("Download token ").append(file.getName()).append(" already exists").toString());
            return;
        }
        try
        {
            if(!file.createNewFile())
            {
                RuntimeException runtimeexception = JVM INSTR new #308 <Class RuntimeException>;
                StringBuilder stringbuilder = JVM INSTR new #198 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                runtimeexception.RuntimeException(stringbuilder.append("Failed to create download token for request ").append(downloadrequest).toString());
                throw runtimeexception;
            }
        }
        catch(IOException ioexception)
        {
            throw new RuntimeException((new StringBuilder()).append("Failed to create download token for request ").append(downloadrequest).append(" due to IOException ").append(ioexception).toString());
        }
    }

    public void cancelDownload(DownloadRequest downloadrequest)
    {
        IMbmsDownloadService imbmsdownloadservice;
        imbmsdownloadservice = (IMbmsDownloadService)mService.get();
        if(imbmsdownloadservice == null)
            throw new IllegalStateException("Middleware not yet bound");
        int i = imbmsdownloadservice.cancelDownload(downloadrequest);
        if(i == 0)
            break MISSING_BLOCK_LABEL_81;
        if(i == 402)
        {
            try
            {
                downloadrequest = JVM INSTR new #253 <Class IllegalArgumentException>;
                downloadrequest.IllegalArgumentException("Unknown download request.");
                throw downloadrequest;
            }
            // Misplaced declaration of an exception variable
            catch(DownloadRequest downloadrequest)
            {
                mService.set(null);
            }
            sendErrorToApp(3, null);
            return;
        }
        sendErrorToApp(i, null);
        return;
        deleteDownloadRequestToken(downloadrequest);
        return;
    }

    public void close()
    {
        IMbmsDownloadService imbmsdownloadservice = (IMbmsDownloadService)mService.get();
        if(imbmsdownloadservice != null)
            break MISSING_BLOCK_LABEL_48;
        Log.i(LOG_TAG, "Service already dead");
        mService.set(null);
        sIsInitialized.set(false);
        mInternalCallback.stop();
        return;
        imbmsdownloadservice.dispose(mSubscriptionId);
        mService.set(null);
        sIsInitialized.set(false);
        mInternalCallback.stop();
_L2:
        return;
        Object obj;
        obj;
        Log.i(LOG_TAG, "Remote exception while disposing of service");
        mService.set(null);
        sIsInitialized.set(false);
        mInternalCallback.stop();
        if(true) goto _L2; else goto _L1
_L1:
        obj;
        mService.set(null);
        sIsInitialized.set(false);
        mInternalCallback.stop();
        throw obj;
    }

    public void download(DownloadRequest downloadrequest)
    {
        IMbmsDownloadService imbmsdownloadservice;
        imbmsdownloadservice = (IMbmsDownloadService)mService.get();
        if(imbmsdownloadservice == null)
            throw new IllegalStateException("Middleware not yet bound");
        if(mContext.getSharedPreferences("MbmsTempFileRootPrefs", 0).getString("mbms_temp_file_root", null) == null)
        {
            File file = new File(mContext.getFilesDir(), "androidMbmsTempFileRoot");
            file.mkdirs();
            setTempFileRootDirectory(file);
        }
        writeDownloadRequestToken(downloadrequest);
        imbmsdownloadservice.download(downloadrequest);
_L1:
        return;
        downloadrequest;
        mService.set(null);
        sendErrorToApp(3, null);
          goto _L1
    }

    public int getDownloadStatus(DownloadRequest downloadrequest, FileInfo fileinfo)
    {
        IMbmsDownloadService imbmsdownloadservice = (IMbmsDownloadService)mService.get();
        if(imbmsdownloadservice == null)
            throw new IllegalStateException("Middleware not yet bound");
        int i;
        try
        {
            i = imbmsdownloadservice.getDownloadStatus(downloadrequest, fileinfo);
        }
        // Misplaced declaration of an exception variable
        catch(DownloadRequest downloadrequest)
        {
            mService.set(null);
            sendErrorToApp(3, null);
            return 0;
        }
        return i;
    }

    public File getTempFileRootDirectory()
    {
        String s = mContext.getSharedPreferences("MbmsTempFileRootPrefs", 0).getString("mbms_temp_file_root", null);
        if(s != null)
            return new File(s);
        else
            return null;
    }

    public List listPendingDownloads()
    {
        Object obj = (IMbmsDownloadService)mService.get();
        if(obj == null)
            throw new IllegalStateException("Middleware not yet bound");
        try
        {
            obj = ((IMbmsDownloadService) (obj)).listPendingDownloads(mSubscriptionId);
        }
        catch(RemoteException remoteexception)
        {
            mService.set(null);
            sendErrorToApp(3, null);
            return Collections.emptyList();
        }
        return ((List) (obj));
    }

    public void registerStateCallback(DownloadRequest downloadrequest, DownloadStateCallback downloadstatecallback, Handler handler)
    {
        IMbmsDownloadService imbmsdownloadservice;
        imbmsdownloadservice = (IMbmsDownloadService)mService.get();
        if(imbmsdownloadservice == null)
            throw new IllegalStateException("Middleware not yet bound");
        handler = new InternalDownloadStateCallback(downloadstatecallback, handler);
        int i = imbmsdownloadservice.registerStateCallback(downloadrequest, handler, downloadstatecallback.getCallbackFilterFlags());
        if(i == 0)
            break MISSING_BLOCK_LABEL_103;
        if(i == 402)
        {
            try
            {
                downloadrequest = JVM INSTR new #253 <Class IllegalArgumentException>;
                downloadrequest.IllegalArgumentException("Unknown download request.");
                throw downloadrequest;
            }
            // Misplaced declaration of an exception variable
            catch(DownloadRequest downloadrequest)
            {
                mService.set(null);
            }
            sendErrorToApp(3, null);
            return;
        }
        sendErrorToApp(i, null);
        return;
        mInternalDownloadCallbacks.put(downloadstatecallback, handler);
        return;
    }

    public void requestUpdateFileServices(List list)
    {
        IMbmsDownloadService imbmsdownloadservice;
        imbmsdownloadservice = (IMbmsDownloadService)mService.get();
        if(imbmsdownloadservice == null)
            throw new IllegalStateException("Middleware not yet bound");
        int i = imbmsdownloadservice.requestUpdateFileServices(mSubscriptionId, list);
        if(i == 0)
            break MISSING_BLOCK_LABEL_48;
        sendErrorToApp(i, null);
_L1:
        return;
        list;
        Log.w(LOG_TAG, "Remote process died");
        mService.set(null);
        sendErrorToApp(3, null);
          goto _L1
    }

    public void resetDownloadKnowledge(DownloadRequest downloadrequest)
    {
        IMbmsDownloadService imbmsdownloadservice;
        imbmsdownloadservice = (IMbmsDownloadService)mService.get();
        if(imbmsdownloadservice == null)
            throw new IllegalStateException("Middleware not yet bound");
        int i = imbmsdownloadservice.resetDownloadKnowledge(downloadrequest);
        if(i != 0)
        {
            if(i != 402)
                break MISSING_BLOCK_LABEL_74;
            try
            {
                downloadrequest = JVM INSTR new #253 <Class IllegalArgumentException>;
                downloadrequest.IllegalArgumentException("Unknown download request.");
                throw downloadrequest;
            }
            // Misplaced declaration of an exception variable
            catch(DownloadRequest downloadrequest)
            {
                mService.set(null);
            }
            sendErrorToApp(3, null);
        }
_L1:
        return;
        sendErrorToApp(i, null);
          goto _L1
    }

    public void setTempFileRootDirectory(File file)
    {
        IMbmsDownloadService imbmsdownloadservice = (IMbmsDownloadService)mService.get();
        if(imbmsdownloadservice == null)
            throw new IllegalStateException("Middleware not yet bound");
        int i;
        try
        {
            validateTempFileRootSanity(file);
        }
        // Misplaced declaration of an exception variable
        catch(File file)
        {
            throw new IllegalStateException("Got IOException checking directory sanity");
        }
        try
        {
            file = file.getCanonicalPath();
        }
        // Misplaced declaration of an exception variable
        catch(File file)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Unable to canonicalize the provided path: ").append(file).toString());
        }
        try
        {
            i = imbmsdownloadservice.setTempFileRootDirectory(mSubscriptionId, file);
        }
        // Misplaced declaration of an exception variable
        catch(File file)
        {
            mService.set(null);
            sendErrorToApp(3, null);
            return;
        }
        if(i == 0)
            break MISSING_BLOCK_LABEL_58;
        sendErrorToApp(i, null);
        mContext.getSharedPreferences("MbmsTempFileRootPrefs", 0).edit().putString("mbms_temp_file_root", file).apply();
        return;
    }

    public void unregisterStateCallback(DownloadRequest downloadrequest, DownloadStateCallback downloadstatecallback)
    {
        IMbmsDownloadService imbmsdownloadservice = (IMbmsDownloadService)mService.get();
        if(imbmsdownloadservice != null)
            break MISSING_BLOCK_LABEL_53;
        downloadrequest = JVM INSTR new #157 <Class IllegalStateException>;
        downloadrequest.IllegalStateException("Middleware not yet bound");
        throw downloadrequest;
        downloadrequest;
        downloadstatecallback = (InternalDownloadStateCallback)mInternalDownloadCallbacks.remove(downloadstatecallback);
        if(downloadstatecallback != null)
            downloadstatecallback.stop();
        throw downloadrequest;
        InternalDownloadStateCallback internaldownloadstatecallback = (InternalDownloadStateCallback)mInternalDownloadCallbacks.get(downloadstatecallback);
        int i = imbmsdownloadservice.unregisterStateCallback(downloadrequest, internaldownloadstatecallback);
        if(i == 0)
            break MISSING_BLOCK_LABEL_120;
        if(i != 402)
            break MISSING_BLOCK_LABEL_143;
        try
        {
            downloadrequest = JVM INSTR new #253 <Class IllegalArgumentException>;
            downloadrequest.IllegalArgumentException("Unknown download request.");
            throw downloadrequest;
        }
        // Misplaced declaration of an exception variable
        catch(DownloadRequest downloadrequest) { }
        mService.set(null);
        sendErrorToApp(3, null);
_L1:
        downloadrequest = (InternalDownloadStateCallback)mInternalDownloadCallbacks.remove(downloadstatecallback);
        if(downloadrequest != null)
            downloadrequest.stop();
        return;
        sendErrorToApp(i, null);
          goto _L1
    }

    public static final String DEFAULT_TOP_LEVEL_TEMP_DIRECTORY = "androidMbmsTempFileRoot";
    public static final String EXTRA_MBMS_COMPLETED_FILE_URI = "android.telephony.extra.MBMS_COMPLETED_FILE_URI";
    public static final String EXTRA_MBMS_DOWNLOAD_REQUEST = "android.telephony.extra.MBMS_DOWNLOAD_REQUEST";
    public static final String EXTRA_MBMS_DOWNLOAD_RESULT = "android.telephony.extra.MBMS_DOWNLOAD_RESULT";
    public static final String EXTRA_MBMS_FILE_INFO = "android.telephony.extra.MBMS_FILE_INFO";
    private static final String LOG_TAG = android/telephony/MbmsDownloadSession.getSimpleName();
    public static final String MBMS_DOWNLOAD_SERVICE_ACTION = "android.telephony.action.EmbmsDownload";
    public static final int RESULT_CANCELLED = 2;
    public static final int RESULT_EXPIRED = 3;
    public static final int RESULT_IO_ERROR = 4;
    public static final int RESULT_SUCCESSFUL = 1;
    public static final int STATUS_ACTIVELY_DOWNLOADING = 1;
    public static final int STATUS_PENDING_DOWNLOAD = 2;
    public static final int STATUS_PENDING_DOWNLOAD_WINDOW = 4;
    public static final int STATUS_PENDING_REPAIR = 3;
    public static final int STATUS_UNKNOWN = 0;
    private static AtomicBoolean sIsInitialized = new AtomicBoolean(false);
    private final Context mContext;
    private android.os.IBinder.DeathRecipient mDeathRecipient;
    private final InternalDownloadSessionCallback mInternalCallback;
    private final Map mInternalDownloadCallbacks = new HashMap();
    private AtomicReference mService;
    private int mSubscriptionId;

}
