// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.provider;

import android.content.*;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.TextUtils;
import android.util.Log;
import java.io.*;
import java.security.MessageDigest;
import miui.os.Environment;
import miui.os.FileUtils;

public class Recordings
{
    public static class CachedAccount
    {

        public static final Uri CONTENT_URI = Uri.parse("content://records/cached_account");
        public static final String TABLE_NAME = "cached_account";
        public static final String URI_PATH = "cached_account";


        public CachedAccount()
        {
        }
    }

    public static final class CachedAccount.Columns
        implements BaseColumns
    {

        public static final String ACCOUNT_NAME = "account_name";

        public CachedAccount.Columns()
        {
        }
    }

    public static class CallRecords
    {

        public static final Uri CONTENT_URI = Uri.parse("content://records/call_records");
        public static final String TABLE_NAME = "call_records";
        public static final String URI_PATH = "call_records";


        public CallRecords()
        {
        }
    }

    public static final class CallRecords.Columns
        implements BaseColumns
    {

        public static final String NUMBER = "number";
        public static final String RECORD_ID = "record_id";

        public CallRecords.Columns()
        {
        }
    }

    public static class CallRecordsView
    {

        public static final Uri CONTENT_URI = Uri.parse("content://records/call_records_view");
        public static final String URI_PATH = "call_records_view";
        public static final String VIEW_NAME = "call_records_view";


        public CallRecordsView()
        {
        }
    }

    public static class Downloads
    {

        public static final Uri CONTENT_URI = Uri.parse("content://records/downloads");
        public static final String TABLE_NAME = "downloads";
        public static final String URI_PATH = "downloads";


        public Downloads()
        {
        }
    }

    public static final class Downloads.Columns
        implements BaseColumns
    {

        public static final String FILE_ID = "file_id";
        public static final String FILE_NAME = "file_name";
        public static final String FILE_PATH = "file_path";
        public static final String FILE_SIZE = "file_size";
        public static final String PROGRESS = "progress";
        public static final String REC_ID = "rec_id";
        public static final String STATUS = "status";

        public Downloads.Columns()
        {
        }
    }

    public static final class Downloads.Status
    {

        public static final int Downloading = 1;
        public static final int Failed = 4;
        public static final int Paused = 3;
        public static final int Pendding = 2;
        public static final int PenddingByNetwork = 5;
        public static final int Success = 0;

        public Downloads.Status()
        {
        }
    }

    public static class MarkPoints
    {

        public static final Uri CONTENT_URI = Uri.parse("content://records/mark_points");
        public static final String TABLE_NAME = "mark_points";
        public static final String URI_PATH = "mark_points";


        public MarkPoints()
        {
        }
    }

    public static final class MarkPoints.Columns
        implements BaseColumns
    {

        public static final String DESCRIPTION = "desp";
        public static final String E_TAG = "e_tag";
        public static final String FILE_SHA1 = "file_sha1";
        public static final String PATH = "path";
        public static final String RECORD_ID = "record_id";
        public static final String SYNC_DIRTY = "sync_dirty";
        public static final String TIME_POINT = "time_point";
        public static final String TYPE = "type";

        public MarkPoints.Columns()
        {
        }
    }

    public static final class MarkPoints.SyncDirty
    {

        public static final int DIRTY = 1;
        public static final int SYNCED = 0;
        public static final int SYNC_ERROR = 2;

        public MarkPoints.SyncDirty()
        {
        }
    }

    public static class MarkpointsOperations
    {

        public static final Uri CONTENT_URI = Uri.parse("content://records/markpoint_operations");
        public static final String TABLE_NAME = "markpoint_operations";
        public static final String URI_PATH = "markpoint_operations";


        public MarkpointsOperations()
        {
        }
    }

    public static final class MarkpointsOperations.Columns
        implements BaseColumns
    {

        public static final String CLOUD_RECORD_ID = "cloud_record_id";
        public static final String E_TAG = "e_tag";
        public static final String OPER = "oper";
        public static final String REC_ID = "rec_id";

        public MarkpointsOperations.Columns()
        {
        }
    }

    public static final class MarkpointsOperations.Opers
    {

        public static final int ADD = 103;
        public static final int DELETE = 101;
        public static final int UPDATE = 102;

        public MarkpointsOperations.Opers()
        {
        }
    }

    public static class Operations
    {

        public static final Uri CONTENT_URI = Uri.parse("content://records/operations");
        public static final String TABLE_NAME = "operations";
        public static final String URI_PATH = "operations";


        public Operations()
        {
        }
    }

    public static final class Operations.Columns
        implements BaseColumns
    {

        public static final String DESC = "decs";
        public static final String FILE_ID = "file_id";
        public static final String OPER = "oper";
        public static final String REC_ID = "rec_id";

        public Operations.Columns()
        {
        }
    }

    public static final class Operations.Opers
    {

        public static final int DELETE = 0;
        public static final int RENAME = 1;

        public Operations.Opers()
        {
        }
    }

    public static class RecordingNotifications
    {

        public static final Uri CONTENT_URI = Uri.parse("content://records/recordingnotifications");
        public static final String EXTRA_DIRPATH = "extra_dirpath";
        public static final String EXTRA_RECTYPE = "extra_rectype";
        public static final String TABLE_NAME = "recordingnotifications";
        public static final String URI_PATH = "recordingnotifications";


        public RecordingNotifications()
        {
        }
    }

    public static final class RecordingNotifications.Columns
        implements BaseColumns
    {

        public static final String CNT_UNREAD = "cnt_unread";
        public static final String NOTIF_DESC = "NOTIF_DESC";
        public static final String REC_TYPE = "rec_type";

        public RecordingNotifications.Columns()
        {
        }
    }

    public static class Records
    {

        public static final Uri CONTENT_URI = Uri.parse("content://records/records");
        public static final String TABLE_NAME = "records";
        public static final String URI_PATH = "records";


        public Records()
        {
        }
    }

    public static final class Records.Columns
        implements BaseColumns
    {

        public static final String CLOUD_SYNC_TIME = "cloud_sync_time";
        public static final String CONTENT = "content";
        public static final String CREATE_TIME = "create_time";
        public static final String DB_SYNC_TIME = "db_sync_time";
        public static final String DURATION = "duration";
        public static final String FILE_ID = "file_id";
        public static final String FILE_NAME = "file_name";
        public static final String FILE_PATH = "file_path";
        public static final String FILE_SIZE = "file_size";
        public static final String IN_CLOUD = "in_cloud";
        public static final String IN_LOCAL = "in_local";
        public static final String REC_DESC = "rec_desc";
        public static final String REC_TYPE = "rec_type";
        public static final String SHA1 = "sha1";
        public static final String SYNC_DIRTY = "sync_dirty";

        public Records.Columns()
        {
        }
    }

    public static final class Records.InCloud
    {

        public static final int IN_CLOUD = 1;
        public static final int NOT_IN_CLOUD = 0;

        public Records.InCloud()
        {
        }
    }

    public static final class Records.InLocal
    {

        public static final int IN_LOCAL = 1;
        public static final int NOT_IN_LOCAL = 0;

        public Records.InLocal()
        {
        }
    }

    public static final class Records.Order
    {

        public static final String BY_CTREAT_TIME_DESC = "cloud_sync_time DESC";

        public Records.Order()
        {
        }
    }

    public static final class Records.RecType
    {

        public static final int CALL = 1;
        public static final int FM = 2;
        public static final int NORMAL = 0;

        public Records.RecType()
        {
        }
    }

    public static final class Records.SyncDirty
    {

        public static final int DIRTY = 1;
        public static final int SYNCED = 0;

        public Records.SyncDirty()
        {
        }
    }

    public static class SyncTokens
    {

        public static final Uri CONTENT_URI = Uri.parse("content://records/synctokens");
        public static final String OLD_TABLE_NAME = "markpoint_synctoken";
        public static final String TABLE_NAME = "synctokens";
        public static final String URI_PATH = "synctokens";


        public SyncTokens()
        {
        }
    }

    public static final class SyncTokens.Columns
        implements BaseColumns
    {

        public static final String SYNC_EXTRA_INFO = "sync_extra_info";
        public static final String SYNC_TOKEN = "sync_token";
        public static final String SYNC_TOKEN_TYPE = "sync_token_type";
        public static final String WATER_MARK = "water_mark";

        public SyncTokens.Columns()
        {
        }
    }

    public static final class SyncTokens.TokenType
    {

        public static final int TYPE_FILE_LIST = 1;
        public static final int TYPE_MARK_POINT = 0;

        public SyncTokens.TokenType()
        {
        }
    }


    public Recordings()
    {
    }

    private static String byteArrayToHexString(byte abyte0[])
    {
        if(abyte0 == null)
            return null;
        StringBuffer stringbuffer = new StringBuffer(abyte0.length * 2);
        for(int i = 0; i < abyte0.length; i++)
        {
            stringbuffer.append(HEXDIGITS[abyte0[i] >>> 4 & 0xf]);
            stringbuffer.append(HEXDIGITS[abyte0[i] & 0xf]);
        }

        return stringbuffer.toString();
    }

    public static int getNotificationUnreadCount(Context context, String s)
    {
        ContentResolver contentresolver;
        boolean flag;
        contentresolver = context.getContentResolver();
        context = null;
        flag = false;
        s = contentresolver.query(RecordingNotifications.CONTENT_URI, new String[] {
            "cnt_unread"
        }, "rec_type=?", new String[] {
            s
        }, null, null);
        int i;
        i = ((flag) ? 1 : 0);
        if(s == null)
            break MISSING_BLOCK_LABEL_80;
        i = ((flag) ? 1 : 0);
        context = s;
        if(s.getCount() != 1)
            break MISSING_BLOCK_LABEL_80;
        context = s;
        s.moveToFirst();
        context = s;
        i = s.getInt(0);
        if(s != null)
            s.close();
        return i;
        s;
        if(context != null)
            context.close();
        throw s;
    }

    public static String getSha1(File file)
    {
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        Object obj4;
        obj = null;
        obj1 = null;
        obj2 = null;
        obj3 = null;
        obj4 = obj;
        Object obj5 = JVM INSTR new #234 <Class FileInputStream>;
        obj4 = obj;
        ((FileInputStream) (obj5)).FileInputStream(file);
        file = MessageDigest.getInstance("SHA1");
        obj4 = new byte[8192];
_L3:
        int i = ((InputStream) (obj5)).read(((byte []) (obj4)));
        if(i < 0) goto _L2; else goto _L1
_L1:
        file.update(((byte []) (obj4)), 0, i);
          goto _L3
        obj4;
        file = ((File) (obj5));
        obj5 = obj4;
_L9:
        obj4 = file;
        Log.e("SoundRecorder:SoundRecorder", "Exception when getSha1", ((Throwable) (obj5)));
        obj4 = obj2;
        if(file == null)
            break MISSING_BLOCK_LABEL_103;
        file.close();
        obj4 = obj3;
_L5:
        return ((String) (obj4));
_L2:
        obj4 = byteArrayToHexString(file.digest());
        if(obj5 != null)
            try
            {
                ((InputStream) (obj5)).close();
            }
            // Misplaced declaration of an exception variable
            catch(File file)
            {
                Log.e("SoundRecorder:SoundRecorder", "Exception when close inputstream", file);
            }
        continue; /* Loop/switch isn't completed */
        file;
        Log.e("SoundRecorder:SoundRecorder", "Exception when close inputstream", file);
        obj4 = obj3;
        if(true) goto _L5; else goto _L4
_L4:
        file;
_L7:
        if(obj4 != null)
            try
            {
                ((InputStream) (obj4)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj4)
            {
                Log.e("SoundRecorder:SoundRecorder", "Exception when close inputstream", ((Throwable) (obj4)));
            }
        throw file;
        file;
        obj4 = obj5;
        if(true) goto _L7; else goto _L6
_L6:
        obj5;
        file = obj1;
        if(true) goto _L9; else goto _L8
_L8:
    }

    public static void notifyRecording(Context context, String s, long l)
    {
        File file;
        byte byte0;
        if(s == null)
            return;
        file = new File(s);
        byte0 = -1;
        if(!s.startsWith(CALL_RECORD_DIR)) goto _L2; else goto _L1
_L1:
        byte0 = 1;
_L4:
        if(file.exists() && byte0 != -1)
        {
            FileUtils.addNoMedia(RECORDER_ROOT_PATH);
            Object obj = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
            ((Intent) (obj)).setClassName("com.android.providers.media", "com.android.providers.media.MediaScannerReceiver");
            ((Intent) (obj)).setData(Uri.fromFile(file));
            context.sendBroadcast(((Intent) (obj)));
            obj = getSha1(file);
            if(!TextUtils.isEmpty(((CharSequence) (obj))))
            {
                ContentValues contentvalues = new ContentValues();
                contentvalues.put("file_path", s);
                contentvalues.put("file_name", file.getName());
                contentvalues.put("create_time", Long.valueOf(file.lastModified()));
                contentvalues.put("rec_type", Integer.valueOf(byte0));
                contentvalues.put("db_sync_time", Long.valueOf(System.currentTimeMillis()));
                contentvalues.put("duration", Long.valueOf(l / 1000L));
                contentvalues.put("sync_dirty", Integer.valueOf(1));
                contentvalues.put("in_local", Integer.valueOf(1));
                contentvalues.put("in_cloud", Integer.valueOf(0));
                contentvalues.put("sha1", ((String) (obj)));
                context.getContentResolver().insert(Records.CONTENT_URI, contentvalues);
            }
        }
        return;
_L2:
        if(s.startsWith(FM_RECORD_DIR))
            byte0 = 2;
        else
        if(s.startsWith(RECORDER_ROOT_PATH))
            byte0 = 0;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static void setNotificationUnreadCount(Context context, String s, int i, String s1)
    {
        ContentResolver contentresolver;
        String as[];
        contentresolver = context.getContentResolver();
        as = new String[1];
        as[0] = s;
        context = null;
        Cursor cursor = contentresolver.query(RecordingNotifications.CONTENT_URI, new String[] {
            "cnt_unread"
        }, "rec_type=?", as, null, null);
        if(cursor == null)
            break MISSING_BLOCK_LABEL_131;
        context = cursor;
        int j = cursor.getCount();
        boolean flag;
        if(j == 0)
            break MISSING_BLOCK_LABEL_131;
        flag = true;
_L1:
        if(cursor != null)
            cursor.close();
        context = new ContentValues();
        context.put("cnt_unread", Integer.valueOf(i));
        if(s1 != null)
            context.put("NOTIF_DESC", s1);
        if(flag)
        {
            contentresolver.update(RecordingNotifications.CONTENT_URI, context, "rec_type=?", as);
        } else
        {
            context.put("rec_type", s);
            contentresolver.insert(RecordingNotifications.CONTENT_URI, context);
        }
        return;
        flag = false;
          goto _L1
        s;
        if(context != null)
            context.close();
        throw s;
    }

    public static final String AUTHORITY = "records";
    public static final String CALL_RECORD_DIR = (new StringBuilder()).append(RECORDER_ROOT_PATH).append("/call_rec").toString();
    public static final String FM_RECORD_DIR = (new StringBuilder()).append(RECORDER_ROOT_PATH).append("/fm_rec").toString();
    private static final String HEXDIGITS[] = {
        "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", 
        "a", "b", "c", "d", "e", "f"
    };
    private static final String MEDIA_SCANNER_CLASS = "com.android.providers.media.MediaScannerReceiver";
    private static final String MEDIA_SCANNER_PACKAGE = "com.android.providers.media";
    public static final String RECORDER_ROOT_PATH = (new StringBuilder()).append(Environment.getExternalStorageMiuiDirectory().getAbsolutePath()).append("/sound_recorder").toString();
    public static final String SAMPLE_DEFAULT_DIR = "/sound_recorder";
    private static final String TAG = "SoundRecorder:SoundRecorder";

}
