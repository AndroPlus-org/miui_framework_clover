// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.app.Activity;
import android.content.*;
import android.content.pm.UserInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.*;
import android.util.Log;
import com.android.internal.database.SortCursor;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import libcore.io.Streams;

// Referenced classes of package android.media:
//            IAudioService, ExternalRingtonesCursorWrapper, Ringtone, AudioManager, 
//            IRingtonePlayer, Utils, MediaScannerConnection

public class RingtoneManager
{
    private class NewRingtoneScanner
        implements Closeable, MediaScannerConnection.MediaScannerConnectionClient
    {

        public void close()
        {
            mMediaScannerConnection.disconnect();
        }

        public void onMediaScannerConnected()
        {
            mMediaScannerConnection.scanFile(mFile.getAbsolutePath(), null);
        }

        public void onScanCompleted(String s, Uri uri)
        {
            if(uri == null)
            {
                mFile.delete();
                return;
            }
            mQueue.put(uri);
_L1:
            return;
            s;
            Log.e("RingtoneManager", "Unable to put new ringtone Uri in queue", s);
              goto _L1
        }

        public Uri take()
            throws InterruptedException
        {
            return (Uri)mQueue.take();
        }

        private File mFile;
        private MediaScannerConnection mMediaScannerConnection;
        private LinkedBlockingQueue mQueue;
        final RingtoneManager this$0;

        public NewRingtoneScanner(File file)
        {
            this$0 = RingtoneManager.this;
            super();
            mQueue = new LinkedBlockingQueue(1);
            mFile = file;
            mMediaScannerConnection = new MediaScannerConnection(RingtoneManager._2D_get0(RingtoneManager.this), this);
            mMediaScannerConnection.connect();
        }
    }


    static Context _2D_get0(RingtoneManager ringtonemanager)
    {
        return ringtonemanager.mContext;
    }

    public RingtoneManager(Activity activity)
    {
        this(activity, false);
    }

    public RingtoneManager(Activity activity, boolean flag)
    {
        mType = 1;
        mFilterColumns = new ArrayList();
        mStopPreviousRingtone = true;
        mActivity = activity;
        mContext = activity;
        setType(mType);
        mIncludeParentRingtones = flag;
    }

    public RingtoneManager(Context context)
    {
        this(context, false);
    }

    public RingtoneManager(Context context, boolean flag)
    {
        mType = 1;
        mFilterColumns = new ArrayList();
        mStopPreviousRingtone = true;
        mActivity = null;
        mContext = context;
        setType(mType);
        mIncludeParentRingtones = flag;
    }

    private static String constructBooleanTrueWhereClause(List list)
    {
        if(list == null)
            return null;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("(");
        for(int i = list.size() - 1; i >= 0; i--)
            stringbuilder.append((String)list.get(i)).append("=1 or ");

        if(list.size() > 0)
            stringbuilder.setLength(stringbuilder.length() - 4);
        stringbuilder.append(")");
        return stringbuilder.toString();
    }

    private static Context createPackageContextAsUser(Context context, int i)
    {
        try
        {
            context = context.createPackageContextAsUser(context.getPackageName(), 0, UserHandle.of(i));
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            Log.e("RingtoneManager", "Unable to create package context", context);
            return null;
        }
        return context;
    }

    public static void disableSyncFromParent(Context context)
    {
        IAudioService iaudioservice = IAudioService.Stub.asInterface(ServiceManager.getService("audio"));
        iaudioservice.disableRingtoneSync(context.getUserId());
_L1:
        return;
        context;
        Log.e("RingtoneManager", "Unable to disable ringtone sync.");
          goto _L1
    }

    public static void enableSyncFromParent(Context context)
    {
        android.provider.Settings.Secure.putIntForUser(context.getContentResolver(), "sync_parent_sounds", 1, context.getUserId());
    }

    public static Uri getActualDefaultRingtoneUri(Context context, int i)
    {
        Object obj = getSettingForType(i);
        if(obj == null)
            return null;
        obj = android.provider.Settings.System.getStringForUser(context.getContentResolver(), ((String) (obj)), context.getUserId());
        Uri uri;
        if(obj != null)
            obj = Uri.parse(((String) (obj)));
        else
            obj = null;
        uri = ((Uri) (obj));
        if(obj != null)
        {
            uri = ((Uri) (obj));
            if(ContentProvider.getUserIdFromUri(((Uri) (obj))) == context.getUserId())
                uri = ContentProvider.getUriWithoutUserId(((Uri) (obj)));
        }
        return uri;
    }

    public static Uri getCacheForType(int i)
    {
        return getCacheForType(i, UserHandle.getCallingUserId());
    }

    public static Uri getCacheForType(int i, int j)
    {
        if((i & 1) != 0)
            return ContentProvider.maybeAddUserId(android.provider.Settings.System.RINGTONE_CACHE_URI, j);
        if((i & 2) != 0)
            return ContentProvider.maybeAddUserId(android.provider.Settings.System.NOTIFICATION_SOUND_CACHE_URI, j);
        if((i & 4) != 0)
            return ContentProvider.maybeAddUserId(android.provider.Settings.System.ALARM_ALERT_CACHE_URI, j);
        else
            return null;
    }

    public static int getDefaultType(Uri uri)
    {
        uri = ContentProvider.getUriWithoutUserId(uri);
        if(uri == null)
            return -1;
        if(uri.equals(android.provider.Settings.System.DEFAULT_RINGTONE_URI))
            return 1;
        if(uri.equals(android.provider.Settings.System.DEFAULT_NOTIFICATION_URI))
            return 2;
        return !uri.equals(android.provider.Settings.System.DEFAULT_ALARM_ALERT_URI) ? -1 : 4;
    }

    public static Uri getDefaultUri(int i)
    {
        if((i & 1) != 0)
            return android.provider.Settings.System.DEFAULT_RINGTONE_URI;
        if((i & 2) != 0)
            return android.provider.Settings.System.DEFAULT_NOTIFICATION_URI;
        if((i & 4) != 0)
            return android.provider.Settings.System.DEFAULT_ALARM_ALERT_URI;
        else
            return null;
    }

    private static Uri getExistingRingtoneUriFromPath(Context context, String s)
    {
        Object obj;
        Object obj1;
        Object obj2;
        obj = null;
        obj1 = null;
        obj2 = null;
        context = context.getContentResolver().query(android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, new String[] {
            "_id"
        }, "_data=? ", new String[] {
            s
        }, null);
        if(context == null)
            break MISSING_BLOCK_LABEL_63;
        obj2 = context;
        obj1 = context;
        boolean flag = context.moveToFirst();
        if(!(flag ^ true))
            break MISSING_BLOCK_LABEL_87;
        if(context == null)
            break MISSING_BLOCK_LABEL_73;
        context.close();
        context = null;
_L1:
        if(context != null)
            throw context;
        else
            return null;
        context;
          goto _L1
        obj2 = context;
        obj1 = context;
        int i = context.getInt(context.getColumnIndex("_id"));
        if(i != -1)
            break MISSING_BLOCK_LABEL_138;
        if(context == null)
            break MISSING_BLOCK_LABEL_124;
        context.close();
        context = null;
_L2:
        if(context != null)
            throw context;
        else
            return null;
        context;
          goto _L2
        obj2 = context;
        obj1 = context;
        Uri uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        obj2 = context;
        obj1 = context;
        s = JVM INSTR new #88  <Class StringBuilder>;
        obj2 = context;
        obj1 = context;
        s.StringBuilder();
        obj2 = context;
        obj1 = context;
        uri = Uri.withAppendedPath(uri, s.append("").append(i).toString());
        s = obj;
        if(context == null)
            break MISSING_BLOCK_LABEL_207;
        context.close();
        s = obj;
_L3:
        if(s != null)
            throw s;
        else
            return uri;
        s;
          goto _L3
        context;
        throw context;
        s;
_L6:
        obj1 = context;
        if(obj2 == null)
            break MISSING_BLOCK_LABEL_240;
        ((Cursor) (obj2)).close();
        obj1 = context;
_L4:
        if(obj1 != null)
            throw obj1;
        else
            throw s;
        obj2;
        if(context == null)
        {
            obj1 = obj2;
        } else
        {
            obj1 = context;
            if(context != obj2)
            {
                context.addSuppressed(((Throwable) (obj2)));
                obj1 = context;
            }
        }
          goto _L4
        s;
        context = null;
        obj2 = obj1;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private static final String getExternalDirectoryForType(int i)
    {
        switch(i)
        {
        case 3: // '\003'
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Unsupported ringtone type: ").append(i).toString());

        case 1: // '\001'
            return Environment.DIRECTORY_RINGTONES;

        case 2: // '\002'
            return Environment.DIRECTORY_NOTIFICATIONS;

        case 4: // '\004'
            return Environment.DIRECTORY_ALARMS;
        }
    }

    private Cursor getInternalRingtones()
    {
        return query(android.provider.MediaStore.Audio.Media.INTERNAL_CONTENT_URI, INTERNAL_COLUMNS, constructBooleanTrueWhereClause(mFilterColumns), null, "title_key");
    }

    private Cursor getMediaRingtones()
    {
        return getMediaRingtones(mContext);
    }

    private Cursor getMediaRingtones(Context context)
    {
        Cursor cursor = null;
        if(context.checkPermission("android.permission.READ_EXTERNAL_STORAGE", Process.myPid(), Process.myUid()) != 0)
        {
            Log.w("RingtoneManager", "No READ_EXTERNAL_STORAGE permission, ignoring ringtones on ext storage");
            return null;
        }
        String s = Environment.getExternalStorageState();
        if(s.equals("mounted") || s.equals("mounted_ro"))
            cursor = query(android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, MEDIA_COLUMNS, constructBooleanTrueWhereClause(mFilterColumns), null, "title_key", context);
        return cursor;
    }

    private Cursor getParentProfileRingtones()
    {
        UserInfo userinfo = UserManager.get(mContext).getProfileParent(mContext.getUserId());
        if(userinfo != null && userinfo.id != mContext.getUserId())
        {
            Context context = createPackageContextAsUser(mContext, userinfo.id);
            if(context != null)
                return new ExternalRingtonesCursorWrapper(getMediaRingtones(context), userinfo.id);
        }
        return null;
    }

    public static Ringtone getRingtone(Context context, Uri uri)
    {
        return getRingtone(context, uri, -1);
    }

    private static Ringtone getRingtone(Context context, Uri uri, int i)
    {
        Ringtone ringtone;
        try
        {
            ringtone = JVM INSTR new #435 <Class Ringtone>;
            ringtone.Ringtone(context, true);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            Log.e("RingtoneManager", (new StringBuilder()).append("Failed to open ringtone ").append(uri).append(": ").append(context).toString());
            return null;
        }
        if(i < 0)
            break MISSING_BLOCK_LABEL_19;
        ringtone.setStreamType(i);
        ringtone.setUri(uri);
        return ringtone;
    }

    private File getRingtonePathFromUri(Uri uri)
    {
        Object obj;
        Object obj1;
        Object obj2;
        Cursor cursor;
        Cursor cursor1;
        Object obj4;
        obj = null;
        setFilterColumnsList(7);
        obj1 = null;
        obj2 = null;
        cursor = null;
        cursor1 = cursor;
        obj4 = obj2;
        String s = constructBooleanTrueWhereClause(mFilterColumns);
        cursor1 = cursor;
        obj4 = obj2;
        cursor = query(uri, new String[] {
            "_data"
        }, s, null, null);
        uri = obj1;
        if(cursor == null)
            break MISSING_BLOCK_LABEL_115;
        uri = obj1;
        cursor1 = cursor;
        obj4 = cursor;
        if(!cursor.moveToFirst())
            break MISSING_BLOCK_LABEL_115;
        cursor1 = cursor;
        obj4 = cursor;
        uri = cursor.getString(cursor.getColumnIndex("_data"));
        if(cursor == null)
            break MISSING_BLOCK_LABEL_127;
        cursor.close();
        obj4 = null;
        break MISSING_BLOCK_LABEL_130;
        obj4;
          goto _L1
        uri;
        throw uri;
        exception;
        Exception exception1;
        obj4 = cursor1;
        exception1 = exception;
_L4:
        obj3 = uri;
        if(obj4 == null)
            break MISSING_BLOCK_LABEL_174;
        ((Cursor) (obj4)).close();
        obj3 = uri;
_L2:
        if(obj3 != null)
            throw obj3;
        else
            throw exception1;
        throwable;
        if(uri == null)
        {
            obj3 = throwable;
        } else
        {
            obj3 = uri;
            if(uri != throwable)
            {
                uri.addSuppressed(throwable);
                obj3 = uri;
            }
        }
          goto _L2
_L1:
        Exception exception;
        Object obj3;
        Throwable throwable;
        if(obj4 != null)
            throw obj4;
        throwable = obj;
        if(uri != null)
            throwable = new File(uri);
        return throwable;
        exception1;
        uri = null;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static String getSettingForType(int i)
    {
        if((i & 1) != 0)
            return "ringtone";
        if((i & 2) != 0)
            return "notification_sound";
        if((i & 4) != 0)
            return "alarm_alert";
        else
            return null;
    }

    private static Uri getUriFromCursor(Cursor cursor)
    {
        return ContentUris.withAppendedId(Uri.parse(cursor.getString(2)), cursor.getLong(0));
    }

    public static Uri getValidRingtoneUri(Context context)
    {
        RingtoneManager ringtonemanager = new RingtoneManager(context);
        Uri uri = getValidRingtoneUriFromCursorAndClose(context, ringtonemanager.getInternalRingtones());
        Uri uri1 = uri;
        if(uri == null)
            uri1 = getValidRingtoneUriFromCursorAndClose(context, ringtonemanager.getMediaRingtones());
        return uri1;
    }

    private static Uri getValidRingtoneUriFromCursorAndClose(Context context, Cursor cursor)
    {
        if(cursor != null)
        {
            context = null;
            if(cursor.moveToFirst())
                context = getUriFromCursor(cursor);
            cursor.close();
            return context;
        } else
        {
            return null;
        }
    }

    public static boolean isDefault(Uri uri)
    {
        boolean flag;
        if(getDefaultType(uri) != -1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static boolean isExternalRingtoneUri(Uri uri)
    {
        return isRingtoneUriInStorage(uri, android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
    }

    private static boolean isInternalRingtoneUri(Uri uri)
    {
        return isRingtoneUriInStorage(uri, android.provider.MediaStore.Audio.Media.INTERNAL_CONTENT_URI);
    }

    private static boolean isRingtoneUriInStorage(Uri uri, Uri uri1)
    {
        uri = ContentProvider.getUriWithoutUserId(uri);
        boolean flag;
        if(uri == null)
            flag = false;
        else
            flag = uri.toString().startsWith(uri1.toString());
        return flag;
    }

    private static InputStream openRingtone(Context context, Uri uri)
        throws IOException
    {
        Object obj = context.getContentResolver();
        try
        {
            obj = ((ContentResolver) (obj)).openInputStream(uri);
        }
        catch(Object obj1)
        {
            Log.w("RingtoneManager", (new StringBuilder()).append("Failed to open directly; attempting failover: ").append(obj1).toString());
            context = ((AudioManager)context.getSystemService(android/media/AudioManager)).getRingtonePlayer();
            try
            {
                context = new android.os.ParcelFileDescriptor.AutoCloseInputStream(context.openRingtone(uri));
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                throw new IOException(context);
            }
            return context;
        }
        return ((InputStream) (obj));
    }

    private Cursor query(Uri uri, String as[], String s, String as1[], String s1)
    {
        return query(uri, as, s, as1, s1, mContext);
    }

    private Cursor query(Uri uri, String as[], String s, String as1[], String s1, Context context)
    {
        if(mActivity != null)
            return mActivity.managedQuery(uri, as, s, as1, s1);
        else
            return context.getContentResolver().query(uri, as, s, as1, s1);
    }

    public static void setActualDefaultRingtoneUri(Context context, int i, Uri uri)
    {
        Object obj;
        Object obj2;
        Object obj4;
        Object obj6;
        obj = null;
        Object obj1 = null;
        obj2 = getSettingForType(i);
        if(obj2 == null)
            return;
        ContentResolver contentresolver = context.getContentResolver();
        if(android.provider.Settings.Secure.getIntForUser(contentresolver, "sync_parent_sounds", 0, context.getUserId()) == 1)
            disableSyncFromParent(context);
        Object obj3 = uri;
        if(!isInternalRingtoneUri(uri))
            obj3 = ContentProvider.maybeAddUserId(uri, context.getUserId());
        Uri uri1;
        Object obj5;
        OutputStream outputstream;
        if(obj3 != null)
            uri = ((Uri) (obj3)).toString();
        else
            uri = null;
        android.provider.Settings.System.putStringForUser(contentresolver, ((String) (obj2)), uri, context.getUserId());
        if(obj3 == null) goto _L2; else goto _L1
_L1:
        uri1 = getCacheForType(i, context.getUserId());
        obj4 = null;
        obj2 = null;
        obj5 = null;
        outputstream = null;
        obj6 = outputstream;
        uri = obj5;
        obj3 = openRingtone(context, ((Uri) (obj3)));
        obj2 = obj3;
        obj6 = outputstream;
        obj4 = obj3;
        uri = obj5;
        outputstream = contentresolver.openOutputStream(uri1);
        obj2 = obj3;
        obj6 = outputstream;
        obj4 = obj3;
        uri = outputstream;
        Streams.copy(((InputStream) (obj3)), outputstream);
        context = obj1;
        if(outputstream == null)
            break MISSING_BLOCK_LABEL_192;
        outputstream.close();
        context = obj1;
_L18:
        if(obj3 == null)
            break MISSING_BLOCK_LABEL_202;
        ((InputStream) (obj3)).close();
_L6:
        uri = context;
_L4:
        if(uri != null)
            try
            {
                throw uri;
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                Log.w("RingtoneManager", (new StringBuilder()).append("Failed to cache ringtone: ").append(context).toString());
            }
_L2:
        return;
        context;
        continue; /* Loop/switch isn't completed */
        obj2;
        uri = ((Uri) (obj2));
        if(context == null) goto _L4; else goto _L3
_L3:
        if(context == obj2) goto _L6; else goto _L5
_L5:
        context.addSuppressed(((Throwable) (obj2)));
        uri = context;
          goto _L4
        uri;
        throw uri;
        obj4;
_L16:
        if(obj6 == null)
            break MISSING_BLOCK_LABEL_288;
        ((OutputStream) (obj6)).close();
_L10:
        context = uri;
_L8:
        if(obj2 == null)
            break MISSING_BLOCK_LABEL_300;
        ((InputStream) (obj2)).close();
_L14:
        uri = context;
_L12:
        if(uri == null)
            break MISSING_BLOCK_LABEL_360;
        throw uri;
        obj6;
        context = ((Context) (obj6));
        if(uri == null) goto _L8; else goto _L7
_L7:
        if(uri == obj6) goto _L10; else goto _L9
_L9:
        uri.addSuppressed(((Throwable) (obj6)));
        context = uri;
          goto _L8
        obj2;
        uri = ((Uri) (obj2));
        if(context == null) goto _L12; else goto _L11
_L11:
        if(context == obj2) goto _L14; else goto _L13
_L13:
        context.addSuppressed(((Throwable) (obj2)));
        uri = context;
          goto _L12
        throw obj4;
        context;
        obj2 = obj4;
        obj6 = uri;
        obj4 = context;
        uri = obj;
        if(true) goto _L16; else goto _L15
_L15:
        if(true) goto _L18; else goto _L17
_L17:
    }

    private void setFilterColumnsList(int i)
    {
        List list = mFilterColumns;
        list.clear();
        if((i & 1) != 0)
            list.add("is_ringtone");
        if((i & 2) != 0)
            list.add("is_notification");
        if((i & 4) != 0)
            list.add("is_alarm");
    }

    public Uri addCustomExternalRingtone(Uri uri, int i)
        throws FileNotFoundException, IllegalArgumentException, IOException
    {
        Object obj;
        Object obj1;
        Object obj2;
        Object obj4;
        File file;
        Object obj6;
        Object obj7;
        Object obj8;
        String s;
label0:
        {
            obj = null;
            obj1 = null;
            obj2 = null;
            if(!Environment.getExternalStorageState().equals("mounted"))
                throw new IOException("External storage is not mounted. Unable to install ringtones.");
            s = mContext.getContentResolver().getType(uri);
            if(s != null)
            {
                boolean flag;
                if(!s.startsWith("audio/"))
                    flag = s.equals("application/ogg");
                else
                    flag = true;
                if(!(flag ^ true))
                    break label0;
            }
            throw new IllegalArgumentException((new StringBuilder()).append("Ringtone file must have MIME type \"audio/*\". Given file has MIME type \"").append(s).append("\"").toString());
        }
        obj4 = getExternalDirectoryForType(i);
        file = Utils.getUniqueExternalFile(mContext, ((String) (obj4)), Utils.getFileDisplayNameFromUri(mContext, uri), s);
        obj6 = null;
        obj4 = null;
        obj7 = null;
        obj8 = null;
        uri = mContext.getContentResolver().openInputStream(uri);
        obj4 = uri;
        obj6 = uri;
        Object obj3 = JVM INSTR new #625 <Class FileOutputStream>;
        obj4 = uri;
        obj6 = uri;
        ((FileOutputStream) (obj3)).FileOutputStream(file);
        Streams.copy(uri, ((OutputStream) (obj3)));
        if(obj3 == null)
            break MISSING_BLOCK_LABEL_209;
        try
        {
            ((OutputStream) (obj3)).close();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj4)
        {
            continue; /* Loop/switch isn't completed */
        }
        obj4 = null;
_L26:
        if(uri == null)
            break MISSING_BLOCK_LABEL_220;
        uri.close();
_L4:
        uri = ((Uri) (obj4));
          goto _L1
        obj3;
        uri = ((Uri) (obj3));
        if(obj4 == null) goto _L1; else goto _L2
_L2:
        if(obj4 == obj3) goto _L4; else goto _L3
_L3:
        ((Throwable) (obj4)).addSuppressed(((Throwable) (obj3)));
        uri = ((Uri) (obj4));
          goto _L1
        obj3;
        obj6 = obj8;
        uri = ((Uri) (obj4));
_L24:
        throw obj3;
        obj1;
        obj4 = obj3;
        obj3 = obj1;
        obj1 = obj6;
        obj6 = uri;
_L22:
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_303;
        ((OutputStream) (obj1)).close();
_L8:
        uri = ((Uri) (obj4));
_L6:
        if(obj6 == null)
            break MISSING_BLOCK_LABEL_316;
        ((InputStream) (obj6)).close();
_L12:
        obj4 = uri;
_L10:
        if(obj4 != null)
            throw obj4;
        else
            throw obj3;
        obj1;
        uri = ((Uri) (obj1));
        if(obj4 == null) goto _L6; else goto _L5
_L5:
        if(obj4 == obj1) goto _L8; else goto _L7
_L7:
        ((Throwable) (obj4)).addSuppressed(((Throwable) (obj1)));
        uri = ((Uri) (obj4));
          goto _L6
        obj6;
        obj4 = obj6;
        if(uri == null) goto _L10; else goto _L9
_L9:
        if(uri == obj6) goto _L12; else goto _L11
_L11:
        uri.addSuppressed(((Throwable) (obj6)));
        obj4 = uri;
          goto _L10
_L1:
        if(uri != null)
            throw uri;
        obj6 = null;
        obj3 = null;
        uri = JVM INSTR new #6   <Class RingtoneManager$NewRingtoneScanner>;
        uri.this. NewRingtoneScanner(file);
        obj3 = uri.take();
        obj4 = obj;
        if(uri == null)
            break MISSING_BLOCK_LABEL_425;
        uri.close();
        obj4 = obj;
_L15:
        if(obj4 == null) goto _L14; else goto _L13
_L13:
        try
        {
            throw obj4;
        }
        // Misplaced declaration of an exception variable
        catch(Uri uri) { }
_L16:
        throw new IOException("Audio file failed to scan as a ringtone", uri);
        obj4;
          goto _L15
_L14:
        return ((Uri) (obj3));
        obj4;
        uri = ((Uri) (obj3));
_L20:
        throw obj4;
        obj6;
        obj3 = obj4;
        obj4 = obj6;
_L19:
        obj6 = obj3;
        if(uri == null)
            break MISSING_BLOCK_LABEL_488;
        uri.close();
        obj6 = obj3;
_L17:
        if(obj6 == null)
            break MISSING_BLOCK_LABEL_535;
        try
        {
            throw obj6;
        }
        // Misplaced declaration of an exception variable
        catch(Uri uri) { }
          goto _L16
        uri;
label1:
        {
            if(obj3 != null)
                break label1;
            obj6 = uri;
        }
          goto _L17
        obj6 = obj3;
        if(obj3 == uri) goto _L17; else goto _L18
_L18:
        ((Throwable) (obj3)).addSuppressed(uri);
        obj6 = obj3;
          goto _L17
        throw obj4;
        obj4;
        uri = ((Uri) (obj6));
        obj3 = obj1;
          goto _L19
        obj4;
        obj3 = obj1;
          goto _L19
        obj4;
          goto _L20
        obj3;
        obj1 = obj7;
        obj4 = obj2;
        continue; /* Loop/switch isn't completed */
        Object obj5;
        obj5;
        obj6 = uri;
        obj1 = obj3;
        obj3 = obj5;
        obj5 = obj2;
        if(true) goto _L22; else goto _L21
_L21:
        obj5;
        obj6 = obj3;
        obj3 = obj5;
        if(true) goto _L24; else goto _L23
_L23:
        if(true) goto _L26; else goto _L25
_L25:
    }

    public boolean deleteExternalRingtone(Uri uri)
    {
        File file;
        if(!isCustomRingtone(uri))
            return false;
        file = getRingtonePathFromUri(uri);
        if(file == null)
            break MISSING_BLOCK_LABEL_54;
        boolean flag;
        if(mContext.getContentResolver().delete(uri, null, null) <= 0)
            break MISSING_BLOCK_LABEL_54;
        flag = file.delete();
        return flag;
        uri;
        Log.d("RingtoneManager", "Unable to delete custom ringtone", uri);
        return false;
    }

    public Cursor getCursor()
    {
        if(mCursor != null && mCursor.requery())
            return mCursor;
        Object obj = new ArrayList();
        ((ArrayList) (obj)).add(getInternalRingtones());
        ((ArrayList) (obj)).add(getMediaRingtones());
        if(mIncludeParentRingtones)
        {
            Cursor cursor = getParentProfileRingtones();
            if(cursor != null)
                ((ArrayList) (obj)).add(cursor);
        }
        obj = new SortCursor((Cursor[])((ArrayList) (obj)).toArray(new Cursor[((ArrayList) (obj)).size()]), "title_key");
        mCursor = ((Cursor) (obj));
        return ((Cursor) (obj));
    }

    public boolean getIncludeDrm()
    {
        return false;
    }

    public Ringtone getRingtone(int i)
    {
        if(mStopPreviousRingtone && mPreviousRingtone != null)
            mPreviousRingtone.stop();
        mPreviousRingtone = getRingtone(mContext, getRingtoneUri(i), inferStreamType());
        return mPreviousRingtone;
    }

    public int getRingtonePosition(Uri uri)
    {
        if(uri == null)
            return -1;
        Cursor cursor = getCursor();
        int i = cursor.getCount();
        if(!cursor.moveToFirst())
            return -1;
        Uri uri1 = null;
        String s = null;
        for(int j = 0; j < i; j++)
        {
            String s1 = cursor.getString(2);
            if(uri1 == null || s1.equals(s) ^ true)
                uri1 = Uri.parse(s1);
            if(uri.equals(ContentUris.withAppendedId(uri1, cursor.getLong(0))))
                return j;
            cursor.move(1);
            s = s1;
        }

        return -1;
    }

    public Uri getRingtoneUri(int i)
    {
        if(mCursor == null || mCursor.moveToPosition(i) ^ true)
            return null;
        else
            return getUriFromCursor(mCursor);
    }

    public boolean getStopPreviousRingtone()
    {
        return mStopPreviousRingtone;
    }

    public int inferStreamType()
    {
        switch(mType)
        {
        case 3: // '\003'
        default:
            return 2;

        case 4: // '\004'
            return 4;

        case 2: // '\002'
            return 5;
        }
    }

    public boolean isCustomRingtone(Uri uri)
    {
        if(!isExternalRingtoneUri(uri))
            return false;
        if(uri == null)
            uri = null;
        else
            uri = getRingtonePathFromUri(uri);
        if(uri == null)
            uri = null;
        else
            uri = uri.getParentFile();
        if(uri == null)
            return false;
        String as[] = new String[3];
        as[0] = Environment.DIRECTORY_RINGTONES;
        as[1] = Environment.DIRECTORY_NOTIFICATIONS;
        as[2] = Environment.DIRECTORY_ALARMS;
        int i = as.length;
        for(int j = 0; j < i; j++)
            if(uri.equals(Environment.getExternalStoragePublicDirectory(as[j])))
                return true;

        return false;
    }

    public void setIncludeDrm(boolean flag)
    {
        if(flag)
            Log.w("RingtoneManager", "setIncludeDrm no longer supported");
    }

    public void setStopPreviousRingtone(boolean flag)
    {
        mStopPreviousRingtone = flag;
    }

    public void setType(int i)
    {
        if(mCursor != null)
        {
            throw new IllegalStateException("Setting filter columns should be done before querying for ringtones.");
        } else
        {
            mType = i;
            setFilterColumnsList(i);
            return;
        }
    }

    public void stopPreviousRingtone()
    {
        if(mPreviousRingtone != null)
            mPreviousRingtone.stop();
    }

    public static final String ACTION_RINGTONE_PICKER = "android.intent.action.RINGTONE_PICKER";
    public static final String EXTRA_RINGTONE_AUDIO_ATTRIBUTES_FLAGS = "android.intent.extra.ringtone.AUDIO_ATTRIBUTES_FLAGS";
    public static final String EXTRA_RINGTONE_DEFAULT_URI = "android.intent.extra.ringtone.DEFAULT_URI";
    public static final String EXTRA_RINGTONE_EXISTING_URI = "android.intent.extra.ringtone.EXISTING_URI";
    public static final String EXTRA_RINGTONE_INCLUDE_DRM = "android.intent.extra.ringtone.INCLUDE_DRM";
    public static final String EXTRA_RINGTONE_PICKED_URI = "android.intent.extra.ringtone.PICKED_URI";
    public static final String EXTRA_RINGTONE_SHOW_DEFAULT = "android.intent.extra.ringtone.SHOW_DEFAULT";
    public static final String EXTRA_RINGTONE_SHOW_SILENT = "android.intent.extra.ringtone.SHOW_SILENT";
    public static final String EXTRA_RINGTONE_TITLE = "android.intent.extra.ringtone.TITLE";
    public static final String EXTRA_RINGTONE_TYPE = "android.intent.extra.ringtone.TYPE";
    public static final int ID_COLUMN_INDEX = 0;
    private static final String INTERNAL_COLUMNS[];
    private static final String MEDIA_COLUMNS[];
    private static final String TAG = "RingtoneManager";
    public static final int TITLE_COLUMN_INDEX = 1;
    public static final int TYPE_ALARM = 4;
    public static final int TYPE_ALL = 7;
    public static final int TYPE_NOTIFICATION = 2;
    public static final int TYPE_RINGTONE = 1;
    public static final int URI_COLUMN_INDEX = 2;
    private final Activity mActivity;
    private final Context mContext;
    private Cursor mCursor;
    private final List mFilterColumns;
    private boolean mIncludeParentRingtones;
    private Ringtone mPreviousRingtone;
    private boolean mStopPreviousRingtone;
    private int mType;

    static 
    {
        INTERNAL_COLUMNS = (new String[] {
            "_id", "title", (new StringBuilder()).append("\"").append(android.provider.MediaStore.Audio.Media.INTERNAL_CONTENT_URI).append("\"").toString(), "title_key"
        });
        MEDIA_COLUMNS = (new String[] {
            "_id", "title", (new StringBuilder()).append("\"").append(android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI).append("\"").toString(), "title_key"
        });
    }
}
