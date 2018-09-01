// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.drm;

import android.content.*;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.*;
import android.util.Log;
import dalvik.system.CloseGuard;
import java.io.*;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package android.drm:
//            DrmInfoRequest, DrmSupportInfo, DrmInfo, DrmRights, 
//            DrmUtils, DrmInfoStatus, DrmConvertedStatus, DrmEvent, 
//            DrmErrorEvent, DrmInfoEvent

public class DrmManagerClient
    implements AutoCloseable
{
    private class EventHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            DrmEvent drmevent;
            Object obj;
            HashMap hashmap;
            drmevent = null;
            obj = null;
            hashmap = new HashMap();
            message.what;
            JVM INSTR tableswitch 1001 1002: default 40
        //                       1001 273
        //                       1002 69;
               goto _L1 _L2 _L3
_L1:
            Log.e("DrmManagerClient", (new StringBuilder()).append("Unknown message type ").append(message.what).toString());
            return;
_L3:
            message = (DrmInfo)message.obj;
            DrmInfoStatus drminfostatus = DrmManagerClient._2D_wrap0(DrmManagerClient.this, DrmManagerClient._2D_get3(DrmManagerClient.this), message);
            hashmap.put("drm_info_status_object", drminfostatus);
            hashmap.put("drm_info_object", message);
            if(drminfostatus != null && 1 == drminfostatus.statusCode)
            {
                drmevent = new DrmEvent(DrmManagerClient._2D_get3(DrmManagerClient.this), DrmManagerClient._2D_wrap3(DrmManagerClient.this, drminfostatus.infoType), null, hashmap);
                message = obj;
            } else
            {
                int i;
                if(drminfostatus != null)
                    i = drminfostatus.infoType;
                else
                    i = message.getInfoType();
                message = new DrmErrorEvent(DrmManagerClient._2D_get3(DrmManagerClient.this), DrmManagerClient._2D_wrap2(DrmManagerClient.this, i), null, hashmap);
            }
_L5:
            if(DrmManagerClient._2D_get1(DrmManagerClient.this) != null && drmevent != null)
                DrmManagerClient._2D_get1(DrmManagerClient.this).onEvent(DrmManagerClient.this, drmevent);
            if(DrmManagerClient._2D_get0(DrmManagerClient.this) != null && message != null)
                DrmManagerClient._2D_get0(DrmManagerClient.this).onError(DrmManagerClient.this, message);
            return;
_L2:
            if(DrmManagerClient._2D_wrap1(DrmManagerClient.this, DrmManagerClient._2D_get3(DrmManagerClient.this)) == 0)
            {
                drmevent = new DrmEvent(DrmManagerClient._2D_get3(DrmManagerClient.this), 1001, null);
                message = obj;
            } else
            {
                message = new DrmErrorEvent(DrmManagerClient._2D_get3(DrmManagerClient.this), 2007, null);
            }
            if(true) goto _L5; else goto _L4
_L4:
        }

        final DrmManagerClient this$0;

        public EventHandler(Looper looper)
        {
            this$0 = DrmManagerClient.this;
            super(looper);
        }
    }

    private class InfoHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            Object obj;
            DrmErrorEvent drmerrorevent;
            int i;
            int j;
            obj = null;
            drmerrorevent = null;
            switch(message.what)
            {
            default:
                Log.e("DrmManagerClient", (new StringBuilder()).append("Unknown message type ").append(message.what).toString());
                return;

            case 1: // '\001'
                i = message.arg1;
                break;
            }
            j = message.arg2;
            message = message.obj.toString();
            j;
            JVM INSTR tableswitch 1 6: default 116
        //                       1 222
        //                       2 194
        //                       3 222
        //                       4 222
        //                       5 222
        //                       6 222;
               goto _L1 _L2 _L3 _L2 _L2 _L2 _L2
_L1:
            drmerrorevent = new DrmErrorEvent(i, j, message);
            message = obj;
_L5:
            if(DrmManagerClient._2D_get2(DrmManagerClient.this) != null && message != null)
                DrmManagerClient._2D_get2(DrmManagerClient.this).onInfo(DrmManagerClient.this, message);
            if(DrmManagerClient._2D_get0(DrmManagerClient.this) != null && drmerrorevent != null)
                DrmManagerClient._2D_get0(DrmManagerClient.this).onError(DrmManagerClient.this, drmerrorevent);
            return;
_L3:
            try
            {
                DrmUtils.removeFile(message);
            }
            catch(IOException ioexception)
            {
                ioexception.printStackTrace();
            }
            message = new DrmInfoEvent(i, j, message);
            continue; /* Loop/switch isn't completed */
_L2:
            message = new DrmInfoEvent(i, j, message);
            if(true) goto _L5; else goto _L4
_L4:
        }

        public static final int INFO_EVENT_TYPE = 1;
        final DrmManagerClient this$0;

        public InfoHandler(Looper looper)
        {
            this$0 = DrmManagerClient.this;
            super(looper);
        }
    }

    public static interface OnErrorListener
    {

        public abstract void onError(DrmManagerClient drmmanagerclient, DrmErrorEvent drmerrorevent);
    }

    public static interface OnEventListener
    {

        public abstract void onEvent(DrmManagerClient drmmanagerclient, DrmEvent drmevent);
    }

    public static interface OnInfoListener
    {

        public abstract void onInfo(DrmManagerClient drmmanagerclient, DrmInfoEvent drminfoevent);
    }


    static OnErrorListener _2D_get0(DrmManagerClient drmmanagerclient)
    {
        return drmmanagerclient.mOnErrorListener;
    }

    static OnEventListener _2D_get1(DrmManagerClient drmmanagerclient)
    {
        return drmmanagerclient.mOnEventListener;
    }

    static OnInfoListener _2D_get2(DrmManagerClient drmmanagerclient)
    {
        return drmmanagerclient.mOnInfoListener;
    }

    static int _2D_get3(DrmManagerClient drmmanagerclient)
    {
        return drmmanagerclient.mUniqueId;
    }

    static DrmInfoStatus _2D_wrap0(DrmManagerClient drmmanagerclient, int i, DrmInfo drminfo)
    {
        return drmmanagerclient._processDrmInfo(i, drminfo);
    }

    static int _2D_wrap1(DrmManagerClient drmmanagerclient, int i)
    {
        return drmmanagerclient._removeAllRights(i);
    }

    static int _2D_wrap2(DrmManagerClient drmmanagerclient, int i)
    {
        return drmmanagerclient.getErrorType(i);
    }

    static int _2D_wrap3(DrmManagerClient drmmanagerclient, int i)
    {
        return drmmanagerclient.getEventType(i);
    }

    public DrmManagerClient(Context context)
    {
        mContext = context;
        createEventThreads();
        mUniqueId = _initialize();
        mCloseGuard.open("release");
    }

    private native DrmInfo _acquireDrmInfo(int i, DrmInfoRequest drminforequest);

    private native boolean _canHandle(int i, String s, String s1);

    private native int _checkRightsStatus(int i, String s, int j);

    private native DrmConvertedStatus _closeConvertSession(int i, int j);

    private native DrmConvertedStatus _convertData(int i, int j, byte abyte0[]);

    private native DrmSupportInfo[] _getAllSupportInfo(int i);

    private native ContentValues _getConstraints(int i, String s, int j);

    private native int _getDrmObjectType(int i, String s, String s1);

    private native ContentValues _getMetadata(int i, String s);

    private native String _getOriginalMimeType(int i, String s, FileDescriptor filedescriptor);

    private native int _initialize();

    private native void _installDrmEngine(int i, String s);

    private native int _openConvertSession(int i, String s);

    private native DrmInfoStatus _processDrmInfo(int i, DrmInfo drminfo);

    private native void _release(int i);

    private native int _removeAllRights(int i);

    private native int _removeRights(int i, String s);

    private native int _saveRights(int i, DrmRights drmrights, String s, String s1);

    private native void _setListeners(int i, Object obj);

    private String convertUriToPath(Uri uri)
    {
        Object obj = null;
        if(uri == null) goto _L2; else goto _L1
_L1:
        obj = uri.getScheme();
        if(obj != null && !((String) (obj)).equals("") && !((String) (obj)).equals("file")) goto _L4; else goto _L3
_L3:
        obj = uri.getPath();
_L2:
        return ((String) (obj));
_L4:
        Object obj1;
        if(((String) (obj)).equals("http"))
        {
            obj = uri.toString();
            continue; /* Loop/switch isn't completed */
        }
        if(!((String) (obj)).equals("content"))
            break; /* Loop/switch isn't completed */
        obj1 = null;
        obj = null;
        uri = mContext.getContentResolver().query(uri, new String[] {
            "_data"
        }, null, null, null);
        if(uri == null) goto _L6; else goto _L5
_L5:
        obj = uri;
        obj1 = uri;
        if(uri.getCount() != 0) goto _L7; else goto _L6
_L6:
        obj = uri;
        obj1 = uri;
        Object obj2 = JVM INSTR new #218 <Class IllegalArgumentException>;
        obj = uri;
        obj1 = uri;
        ((IllegalArgumentException) (obj2)).IllegalArgumentException("Given Uri could not be found in media store");
        obj = uri;
        obj1 = uri;
        try
        {
            throw obj2;
        }
        // Misplaced declaration of an exception variable
        catch(Uri uri)
        {
            obj1 = obj;
        }
        uri = JVM INSTR new #218 <Class IllegalArgumentException>;
        obj1 = obj;
        uri.IllegalArgumentException("Given Uri is not formatted in a way so that it can be found in media store.");
        obj1 = obj;
        throw uri;
        uri;
        if(obj1 != null)
            ((Cursor) (obj1)).close();
        throw uri;
_L7:
        obj = uri;
        obj1 = uri;
        if(uri.moveToFirst() ^ true) goto _L6; else goto _L8
_L8:
        obj = uri;
        obj1 = uri;
        obj2 = uri.getString(uri.getColumnIndexOrThrow("_data"));
        String s = ((String) (obj2));
        obj = s;
        if(uri != null)
        {
            uri.close();
            obj = s;
        }
        if(true) goto _L2; else goto _L9
_L9:
        throw new IllegalArgumentException("Given Uri scheme is not supported");
    }

    private void createEventThreads()
    {
        if(mEventHandler == null && mInfoHandler == null)
        {
            mInfoThread = new HandlerThread("DrmManagerClient.InfoHandler");
            mInfoThread.start();
            mInfoHandler = new InfoHandler(mInfoThread.getLooper());
            mEventThread = new HandlerThread("DrmManagerClient.EventHandler");
            mEventThread.start();
            mEventHandler = new EventHandler(mEventThread.getLooper());
        }
    }

    private void createListeners()
    {
        _setListeners(mUniqueId, new WeakReference(this));
    }

    private int getErrorType(int i)
    {
        byte byte0 = -1;
        i;
        JVM INSTR tableswitch 1 3: default 28
    //                   1 32
    //                   2 32
    //                   3 32;
           goto _L1 _L2 _L2 _L2
_L1:
        i = byte0;
_L4:
        return i;
_L2:
        i = 2006;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private int getEventType(int i)
    {
        byte byte0 = -1;
        i;
        JVM INSTR tableswitch 1 3: default 28
    //                   1 32
    //                   2 32
    //                   3 32;
           goto _L1 _L2 _L2 _L2
_L1:
        i = byte0;
_L4:
        return i;
_L2:
        i = 1002;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static void notify(Object obj, int i, int j, String s)
    {
        obj = (DrmManagerClient)((WeakReference)obj).get();
        if(obj != null && ((DrmManagerClient) (obj)).mInfoHandler != null)
        {
            s = ((DrmManagerClient) (obj)).mInfoHandler.obtainMessage(1, i, j, s);
            ((DrmManagerClient) (obj)).mInfoHandler.sendMessage(s);
        }
    }

    public DrmInfo acquireDrmInfo(DrmInfoRequest drminforequest)
    {
        if(drminforequest == null || drminforequest.isValid() ^ true)
            throw new IllegalArgumentException("Given drmInfoRequest is invalid/null");
        else
            return _acquireDrmInfo(mUniqueId, drminforequest);
    }

    public int acquireRights(DrmInfoRequest drminforequest)
    {
        drminforequest = acquireDrmInfo(drminforequest);
        if(drminforequest == null)
            return -2000;
        else
            return processDrmInfo(drminforequest);
    }

    public boolean canHandle(Uri uri, String s)
    {
        if((uri == null || Uri.EMPTY == uri) && (s == null || s.equals("")))
            throw new IllegalArgumentException("Uri or the mimetype should be non null");
        else
            return canHandle(convertUriToPath(uri), s);
    }

    public boolean canHandle(String s, String s1)
    {
        if((s == null || s.equals("")) && (s1 == null || s1.equals("")))
            throw new IllegalArgumentException("Path or the mimetype should be non null");
        else
            return _canHandle(mUniqueId, s, s1);
    }

    public int checkRightsStatus(Uri uri)
    {
        if(uri == null || Uri.EMPTY == uri)
            throw new IllegalArgumentException("Given uri is not valid");
        else
            return checkRightsStatus(convertUriToPath(uri));
    }

    public int checkRightsStatus(Uri uri, int i)
    {
        if(uri == null || Uri.EMPTY == uri)
            throw new IllegalArgumentException("Given uri is not valid");
        else
            return checkRightsStatus(convertUriToPath(uri), i);
    }

    public int checkRightsStatus(String s)
    {
        return checkRightsStatus(s, 0);
    }

    public int checkRightsStatus(String s, int i)
    {
        if(s == null || s.equals("") || DrmStore.Action.isValid(i) ^ true)
            throw new IllegalArgumentException("Given path or action is not valid");
        else
            return _checkRightsStatus(mUniqueId, s, i);
    }

    public void close()
    {
        mCloseGuard.close();
        if(mClosed.compareAndSet(false, true))
        {
            if(mEventHandler != null)
            {
                mEventThread.quit();
                mEventThread = null;
            }
            if(mInfoHandler != null)
            {
                mInfoThread.quit();
                mInfoThread = null;
            }
            mEventHandler = null;
            mInfoHandler = null;
            mOnEventListener = null;
            mOnInfoListener = null;
            mOnErrorListener = null;
            _release(mUniqueId);
        }
    }

    public DrmConvertedStatus closeConvertSession(int i)
    {
        return _closeConvertSession(mUniqueId, i);
    }

    public DrmConvertedStatus convertData(int i, byte abyte0[])
    {
        if(abyte0 == null || abyte0.length <= 0)
            throw new IllegalArgumentException("Given inputData should be non null");
        else
            return _convertData(mUniqueId, i, abyte0);
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

    public String[] getAvailableDrmEngines()
    {
        DrmSupportInfo adrmsupportinfo[] = _getAllSupportInfo(mUniqueId);
        ArrayList arraylist = new ArrayList();
        for(int i = 0; i < adrmsupportinfo.length; i++)
            arraylist.add(adrmsupportinfo[i].getDescriprition());

        return (String[])arraylist.toArray(new String[arraylist.size()]);
    }

    public ContentValues getConstraints(Uri uri, int i)
    {
        if(uri == null || Uri.EMPTY == uri)
            throw new IllegalArgumentException("Uri should be non null");
        else
            return getConstraints(convertUriToPath(uri), i);
    }

    public ContentValues getConstraints(String s, int i)
    {
        if(s == null || s.equals("") || DrmStore.Action.isValid(i) ^ true)
            throw new IllegalArgumentException("Given usage or path is invalid/null");
        else
            return _getConstraints(mUniqueId, s, i);
    }

    public int getDrmObjectType(Uri uri, String s)
    {
        if((uri == null || Uri.EMPTY == uri) && (s == null || s.equals("")))
            throw new IllegalArgumentException("Uri or the mimetype should be non null");
        String s1 = "";
        try
        {
            uri = convertUriToPath(uri);
        }
        // Misplaced declaration of an exception variable
        catch(Uri uri)
        {
            Log.w("DrmManagerClient", "Given Uri could not be found in media store");
            uri = s1;
        }
        return getDrmObjectType(((String) (uri)), s);
    }

    public int getDrmObjectType(String s, String s1)
    {
        if((s == null || s.equals("")) && (s1 == null || s1.equals("")))
            throw new IllegalArgumentException("Path or the mimetype should be non null");
        else
            return _getDrmObjectType(mUniqueId, s, s1);
    }

    public ContentValues getMetadata(Uri uri)
    {
        if(uri == null || Uri.EMPTY == uri)
            throw new IllegalArgumentException("Uri should be non null");
        else
            return getMetadata(convertUriToPath(uri));
    }

    public ContentValues getMetadata(String s)
    {
        if(s == null || s.equals(""))
            throw new IllegalArgumentException("Given path is invalid/null");
        else
            return _getMetadata(mUniqueId, s);
    }

    public String getOriginalMimeType(Uri uri)
    {
        if(uri == null || Uri.EMPTY == uri)
            throw new IllegalArgumentException("Given uri is not valid");
        else
            return getOriginalMimeType(convertUriToPath(uri));
    }

    public String getOriginalMimeType(String s)
    {
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        FileDescriptor filedescriptor;
        Object obj4;
        Object obj5;
        if(s == null || s.equals(""))
            throw new IllegalArgumentException("Given path should be non null");
        obj = null;
        obj1 = null;
        obj2 = null;
        obj3 = null;
        filedescriptor = null;
        obj4 = obj1;
        obj5 = obj2;
        File file = JVM INSTR new #441 <Class File>;
        obj4 = obj1;
        obj5 = obj2;
        file.File(s);
        obj4 = obj1;
        obj5 = obj2;
        if(!file.exists())
            break MISSING_BLOCK_LABEL_110;
        obj4 = obj1;
        obj5 = obj2;
        obj3 = JVM INSTR new #447 <Class FileInputStream>;
        obj4 = obj1;
        obj5 = obj2;
        ((FileInputStream) (obj3)).FileInputStream(file);
        filedescriptor = ((FileInputStream) (obj3)).getFD();
        obj4 = obj3;
        obj5 = obj3;
        s = _getOriginalMimeType(mUniqueId, s, filedescriptor);
        obj5 = s;
        s = ((String) (obj5));
        if(obj3 == null)
            break MISSING_BLOCK_LABEL_149;
        ((FileInputStream) (obj3)).close();
        s = ((String) (obj5));
_L1:
        return s;
        s;
        s = ((String) (obj5));
          goto _L1
        s;
_L5:
        s = obj;
        if(obj4 == null) goto _L1; else goto _L2
_L2:
        ((FileInputStream) (obj4)).close();
        s = obj;
          goto _L1
        s;
        s = obj;
          goto _L1
        s;
_L4:
        if(obj5 != null)
            try
            {
                ((FileInputStream) (obj5)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj3) { }
        throw s;
        s;
        obj5 = obj3;
        if(true) goto _L4; else goto _L3
_L3:
        s;
        obj4 = obj3;
          goto _L5
    }

    public void installDrmEngine(String s)
    {
        if(s == null || s.equals(""))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Given engineFilePath: ").append(s).append("is not valid").toString());
        } else
        {
            _installDrmEngine(mUniqueId, s);
            return;
        }
    }

    public int openConvertSession(String s)
    {
        if(s == null || s.equals(""))
            throw new IllegalArgumentException("Path or the mimeType should be non null");
        else
            return _openConvertSession(mUniqueId, s);
    }

    public int processDrmInfo(DrmInfo drminfo)
    {
        if(drminfo == null || drminfo.isValid() ^ true)
            throw new IllegalArgumentException("Given drmInfo is invalid/null");
        char c = '\uF830';
        if(mEventHandler != null)
        {
            drminfo = mEventHandler.obtainMessage(1002, drminfo);
            if(mEventHandler.sendMessage(drminfo))
                c = '\0';
            else
                c = '\uF830';
        }
        return c;
    }

    public void release()
    {
        close();
    }

    public int removeAllRights()
    {
        char c = '\uF830';
        if(mEventHandler != null)
        {
            Message message = mEventHandler.obtainMessage(1001);
            if(mEventHandler.sendMessage(message))
                c = '\0';
            else
                c = '\uF830';
        }
        return c;
    }

    public int removeRights(Uri uri)
    {
        if(uri == null || Uri.EMPTY == uri)
            throw new IllegalArgumentException("Given uri is not valid");
        else
            return removeRights(convertUriToPath(uri));
    }

    public int removeRights(String s)
    {
        if(s == null || s.equals(""))
            throw new IllegalArgumentException("Given path should be non null");
        else
            return _removeRights(mUniqueId, s);
    }

    public int saveRights(DrmRights drmrights, String s, String s1)
        throws IOException
    {
        if(drmrights == null || drmrights.isValid() ^ true)
            throw new IllegalArgumentException("Given drmRights or contentPath is not valid");
        if(s != null && s.equals("") ^ true)
            DrmUtils.writeToFile(s, drmrights.getData());
        return _saveRights(mUniqueId, drmrights, s, s1);
    }

    public void setOnErrorListener(OnErrorListener onerrorlistener)
    {
        this;
        JVM INSTR monitorenter ;
        mOnErrorListener = onerrorlistener;
        if(onerrorlistener == null)
            break MISSING_BLOCK_LABEL_15;
        createListeners();
        this;
        JVM INSTR monitorexit ;
        return;
        onerrorlistener;
        throw onerrorlistener;
    }

    public void setOnEventListener(OnEventListener oneventlistener)
    {
        this;
        JVM INSTR monitorenter ;
        mOnEventListener = oneventlistener;
        if(oneventlistener == null)
            break MISSING_BLOCK_LABEL_15;
        createListeners();
        this;
        JVM INSTR monitorexit ;
        return;
        oneventlistener;
        throw oneventlistener;
    }

    public void setOnInfoListener(OnInfoListener oninfolistener)
    {
        this;
        JVM INSTR monitorenter ;
        mOnInfoListener = oninfolistener;
        if(oninfolistener == null)
            break MISSING_BLOCK_LABEL_15;
        createListeners();
        this;
        JVM INSTR monitorexit ;
        return;
        oninfolistener;
        throw oninfolistener;
    }

    private static final int ACTION_PROCESS_DRM_INFO = 1002;
    private static final int ACTION_REMOVE_ALL_RIGHTS = 1001;
    public static final int ERROR_NONE = 0;
    public static final int ERROR_UNKNOWN = -2000;
    public static final int INVALID_SESSION = -1;
    private static final String TAG = "DrmManagerClient";
    private final CloseGuard mCloseGuard = CloseGuard.get();
    private final AtomicBoolean mClosed = new AtomicBoolean();
    private Context mContext;
    private EventHandler mEventHandler;
    HandlerThread mEventThread;
    private InfoHandler mInfoHandler;
    HandlerThread mInfoThread;
    private long mNativeContext;
    private OnErrorListener mOnErrorListener;
    private OnEventListener mOnEventListener;
    private OnInfoListener mOnInfoListener;
    private int mUniqueId;

    static 
    {
        System.loadLibrary("drmframework_jni");
    }
}
