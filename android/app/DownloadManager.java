// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.*;
import android.content.pm.ApplicationInfo;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.net.Uri;
import android.os.*;
import android.provider.BaseColumns;
import android.text.TextUtils;
import android.util.Pair;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

public class DownloadManager
{
    private static class CursorTranslator extends CursorWrapper
    {

        private long getErrorCode(int i)
        {
            while(400 <= i && i < 488 || 500 <= i && i < 600) 
                return (long)i;
            switch(i)
            {
            default:
                return 1000L;

            case 492: 
                return 1001L;

            case 493: 
            case 494: 
                return 1002L;

            case 495: 
                return 1004L;

            case 497: 
                return 1005L;

            case 198: 
                return 1006L;

            case 199: 
                return 1007L;

            case 489: 
                return 1008L;

            case 488: 
                return 1009L;
            }
        }

        private String getLocalUri()
        {
            for(long l = getLong(getColumnIndex("destination")); l == 4L || l == 0L || l == 6L;)
            {
                String s = super.getString(getColumnIndex("local_filename"));
                if(s == null)
                    return null;
                else
                    return Uri.fromFile(new File(s)).toString();
            }

            l = getLong(getColumnIndex("_id"));
            return ContentUris.withAppendedId(android.provider.Downloads.Impl.ALL_DOWNLOADS_CONTENT_URI, l).toString();
        }

        private long getPausedReason(int i)
        {
            switch(i)
            {
            case 197: 
            default:
                return 4L;

            case 194: 
                return 1L;

            case 195: 
                return 2L;

            case 196: 
                return 3L;

            case 193: 
                return 5L;

            case 198: 
                return 6L;
            }
        }

        private long getReason(int i)
        {
            switch(translateStatus(i))
            {
            default:
                return 0L;

            case 16: // '\020'
                return getErrorCode(i);

            case 4: // '\004'
                return getPausedReason(i);
            }
        }

        static int translateStatus(int i)
        {
            switch(i)
            {
            case 191: 
            case 197: 
            case 199: 
            default:
                if(!_2D_assertionsDisabled && !android.provider.Downloads.Impl.isStatusError(i))
                    throw new AssertionError();
                else
                    return 16;

            case 190: 
                return 1;

            case 192: 
                return 2;

            case 193: 
            case 194: 
            case 195: 
            case 196: 
            case 198: 
                return 4;

            case 200: 
                return 8;
            }
        }

        public int getInt(int i)
        {
            return (int)getLong(i);
        }

        public long getLong(int i)
        {
            if(getColumnName(i).equals("reason"))
                return getReason(super.getInt(getColumnIndex("status")));
            if(getColumnName(i).equals("status"))
                return (long)translateStatus(super.getInt(getColumnIndex("status")));
            else
                return super.getLong(i);
        }

        public String getString(int i)
        {
            String s = getColumnName(i);
            if(s.equals("local_uri"))
                return getLocalUri();
            if(s.equals("local_filename") && !mAccessFilename)
                throw new SecurityException("COLUMN_LOCAL_FILENAME is deprecated; use ContentResolver.openFileDescriptor() instead");
            else
                return super.getString(i);
        }

        static final boolean _2D_assertionsDisabled = android/app/DownloadManager$CursorTranslator.desiredAssertionStatus() ^ true;
        private final boolean mAccessFilename;
        private final Uri mBaseUri;


        public CursorTranslator(Cursor cursor, Uri uri, boolean flag)
        {
            super(cursor);
            mBaseUri = uri;
            mAccessFilename = flag;
        }
    }

    public static final class ExtraDownloads
        implements BaseColumns
    {

        public static final String COLUMN_APK_INSTALL_WAY = "download_apk_install_way";
        public static final String COLUMN_APK_PACKGENAME = "apk_package_name";
        public static final String COLUMN_APPOINT_NAME = "appointname";
        public static final String COLUMN_DOWNLOADED_TIME = "downloaded_time";
        public static final String COLUMN_DOWNLOADING_CURRENT_SPEED = "downloading_current_speed";
        public static final String COLUMN_DOWNLOAD_SURPLUS_TIME = "download_surplus_time";
        public static final String COLUMN_EXTRA = "download_extra";
        public static final String COLUMN_EXTRA2 = "download_extra2";
        public static final String COLUMN_FILE_CREATE_TIME = "file_create_time";
        public static final String COLUMN_FILE_HASH = "download_file_hash";
        public static final String COLUMN_IF_RANGE_ID = "if_range_id";
        public static final String COLUMN_SUB_DIRECTORY = "subdirectory";
        public static final String COLUMN_TASK_FOR_THUMBNAIL = "download_task_thumbnail";
        public static final String COLUMN_XL_ACCELERATE_SPEED = "xl_accelerate_speed";
        public static final String COLUMN_XL_TASK_OPEN_MARK = "xl_task_open_mark";
        public static final String COLUMN_XL_VIP_CDN_URL = "xl_vip_cdn_url";
        public static final String COLUMN_XL_VIP_STATUS = "xl_vip_status";
        public static final int CONTROL_PAUSED_WITHOUT_WIFI = 2;

        public ExtraDownloads()
        {
        }
    }

    public static class Query
    {

        private String joinStrings(String s, Iterable iterable)
        {
            StringBuilder stringbuilder = new StringBuilder();
            boolean flag = true;
            for(Iterator iterator = iterable.iterator(); iterator.hasNext();)
            {
                iterable = (String)iterator.next();
                if(!flag)
                    stringbuilder.append(s);
                stringbuilder.append(iterable);
                flag = false;
            }

            return stringbuilder.toString();
        }

        private String statusClause(String s, int i)
        {
            return (new StringBuilder()).append("status").append(s).append("'").append(i).append("'").toString();
        }

        void addExtraSelectionParts(List list)
        {
            if(!TextUtils.isEmpty(mColumnAppData))
                list.add(String.format("%s='%s'", new Object[] {
                    "entity", mColumnAppData
                }));
            if(!TextUtils.isEmpty(mColumnNotificationPackage))
                list.add(String.format("%s='%s'", new Object[] {
                    "notificationpackage", mColumnNotificationPackage
                }));
            if(!TextUtils.isEmpty(mAppendedClause))
                list.add(mAppendedClause);
        }

        public Query orderBy(String s, int i)
        {
            if(i != 1 && i != 2)
                throw new IllegalArgumentException((new StringBuilder()).append("Invalid direction: ").append(i).toString());
            if(s.equals("_id"))
            {
                mOrderByColumn = "_id";
                mOrderDirection = i;
                return this;
            }
            if(s.equals("last_modified_timestamp"))
                mOrderByColumn = "lastmod";
            else
            if(s.equals("total_size"))
                mOrderByColumn = "total_bytes";
            else
                throw new IllegalArgumentException((new StringBuilder()).append("Cannot order by ").append(s).toString());
            mOrderDirection = i;
            return this;
        }

        Cursor runQuery(ContentResolver contentresolver, String as[], Uri uri)
        {
            Object obj = new ArrayList();
            int i;
            String as1[];
            String s;
            if(mIds == null)
                i = 0;
            else
                i = mIds.length;
            if(mFilterString != null)
                i++;
            as1 = new String[i];
            if(i > 0)
            {
                if(mIds != null)
                {
                    ((List) (obj)).add(DownloadManager.getWhereClauseForIds(mIds));
                    DownloadManager.getWhereArgsForIds(mIds, as1);
                }
                if(mFilterString != null)
                {
                    ((List) (obj)).add("title LIKE ?");
                    as1[as1.length - 1] = (new StringBuilder()).append("%").append(mFilterString).append("%").toString();
                }
            }
            if(mStatusFlags != null)
            {
                ArrayList arraylist = new ArrayList();
                if((mStatusFlags.intValue() & 1) != 0)
                    arraylist.add(statusClause("=", 190));
                if((mStatusFlags.intValue() & 2) != 0)
                    arraylist.add(statusClause("=", 192));
                if((mStatusFlags.intValue() & 4) != 0)
                {
                    arraylist.add(statusClause("=", 193));
                    arraylist.add(statusClause("=", 194));
                    arraylist.add(statusClause("=", 195));
                    arraylist.add(statusClause("=", 196));
                    arraylist.add(statusClause("=", 198));
                }
                if((mStatusFlags.intValue() & 8) != 0)
                    arraylist.add(statusClause("=", 200));
                if((mStatusFlags.intValue() & 0x10) != 0)
                    arraylist.add((new StringBuilder()).append("(").append(statusClause(">=", 400)).append(" AND ").append(statusClause("<", 600)).append(")").toString());
                ((List) (obj)).add(joinStrings(" OR ", arraylist));
            }
            if(mOnlyIncludeVisibleInDownloadsUi)
                ((List) (obj)).add("is_visible_in_downloads_ui != '0'");
            addExtraSelectionParts(((List) (obj)));
            ((List) (obj)).add("deleted != '1'");
            obj = joinStrings(" AND ", ((Iterable) (obj)));
            if(mOrderDirection == 1)
                s = "ASC";
            else
                s = "DESC";
            return contentresolver.query(uri, as, ((String) (obj)), as1, (new StringBuilder()).append(mOrderByColumn).append(" ").append(s).toString());
        }

        public Query setFilterByAppData(String s)
        {
            mColumnAppData = s;
            return this;
        }

        public Query setFilterByAppendedClause(String s)
        {
            mAppendedClause = s;
            return this;
        }

        public transient Query setFilterById(long al[])
        {
            mIds = al;
            return this;
        }

        public Query setFilterByNotificationPackage(String s)
        {
            mColumnNotificationPackage = s;
            return this;
        }

        public Query setFilterByStatus(int i)
        {
            mStatusFlags = Integer.valueOf(i);
            return this;
        }

        public Query setFilterByString(String s)
        {
            mFilterString = s;
            return this;
        }

        public Query setOnlyIncludeVisibleInDownloadsUi(boolean flag)
        {
            mOnlyIncludeVisibleInDownloadsUi = flag;
            return this;
        }

        public static final int ORDER_ASCENDING = 1;
        public static final int ORDER_DESCENDING = 2;
        private String mAppendedClause;
        private String mColumnAppData;
        private String mColumnNotificationPackage;
        private String mFilterString;
        private long mIds[];
        private boolean mOnlyIncludeVisibleInDownloadsUi;
        private String mOrderByColumn;
        private int mOrderDirection;
        private Integer mStatusFlags;

        public Query()
        {
            mIds = null;
            mStatusFlags = null;
            mFilterString = null;
            mOrderByColumn = "lastmod";
            mOrderDirection = 2;
            mOnlyIncludeVisibleInDownloadsUi = false;
        }
    }

    public static class Request
    {

        private void checkUri(Uri uri)
            throws IllegalArgumentException
        {
            if(uri == null)
                throw new IllegalArgumentException("uri is null");
            String s = uri.getScheme();
            if(s == null)
                throw new IllegalArgumentException((new StringBuilder()).append("Can not handle uri: ").append(uri).toString());
            s = s.toLowerCase();
            if(!s.equals("http") && s.equals("https") ^ true && s.equals("ftp") ^ true && s.equals("ed2k") ^ true && s.equals("magnet") ^ true)
                throw new IllegalArgumentException((new StringBuilder()).append("Can not handle uri:: ").append(uri).toString());
            else
                return;
        }

        private void encodeHttpHeaders(ContentValues contentvalues)
        {
            int i = 0;
            for(Iterator iterator = mRequestHeaders.iterator(); iterator.hasNext();)
            {
                Object obj = (Pair)iterator.next();
                obj = (new StringBuilder()).append((String)((Pair) (obj)).first).append(": ").append((String)((Pair) (obj)).second).toString();
                contentvalues.put((new StringBuilder()).append("http_header_").append(i).toString(), ((String) (obj)));
                i++;
            }

        }

        private void putIfNonNull(ContentValues contentvalues, String s, Object obj)
        {
            if(obj != null)
                contentvalues.put(s, obj.toString());
        }

        private void setDestinationFromBase(File file, String s)
        {
            if(s == null)
            {
                throw new NullPointerException("subPath cannot be null");
            } else
            {
                mDestinationUri = Uri.withAppendedPath(Uri.fromFile(file), s);
                return;
            }
        }

        public Request addRequestHeader(String s, String s1)
        {
            if(s == null)
                throw new NullPointerException("header cannot be null");
            if(s.contains(":"))
                throw new IllegalArgumentException("header may not contain ':'");
            String s2 = s1;
            if(s1 == null)
                s2 = "";
            mRequestHeaders.add(Pair.create(s, s2));
            return this;
        }

        public void allowScanningByMediaScanner()
        {
            mScannable = true;
        }

        public Request setAllowedNetworkTypes(int i)
        {
            mAllowedNetworkTypes = i;
            return this;
        }

        public Request setAllowedOverMetered(boolean flag)
        {
            mMeteredAllowed = flag;
            return this;
        }

        public Request setAllowedOverRoaming(boolean flag)
        {
            mRoamingAllowed = flag;
            return this;
        }

        public Request setApkPackageName(String s)
        {
            mApkPackageName = s;
            return this;
        }

        public Request setAppData(String s)
        {
            mColumnAppData = s;
            return this;
        }

        public Request setAppointName(String s)
        {
            mAppointName = s;
            return this;
        }

        public void setBypassRecommendedSizeLimit(boolean flag)
        {
            mBypassRecommendedSizeLimit = flag;
        }

        public Request setDescription(CharSequence charsequence)
        {
            mDescription = charsequence;
            return this;
        }

        public Request setDestinationInExternalFilesDir(Context context, String s, String s1)
        {
            context = context.getExternalFilesDir(s);
            if(context == null)
                throw new IllegalStateException("Failed to get external storage files directory");
            if(context.exists())
            {
                if(!context.isDirectory())
                    throw new IllegalStateException((new StringBuilder()).append(context.getAbsolutePath()).append(" already exists and is not a directory").toString());
            } else
            if(!context.mkdirs())
                throw new IllegalStateException((new StringBuilder()).append("Unable to create directory: ").append(context.getAbsolutePath()).toString());
            setDestinationFromBase(context, s1);
            return this;
        }

        public Request setDestinationInExternalPublicDir(String s, String s1)
        {
            s = Environment.getExternalStoragePublicDirectory(s);
            if(s == null)
                throw new IllegalStateException("Failed to get external storage public directory");
            if(s.exists())
            {
                if(!s.isDirectory())
                    throw new IllegalStateException((new StringBuilder()).append(s.getAbsolutePath()).append(" already exists and is not a directory").toString());
            } else
            if(!s.mkdirs())
                throw new IllegalStateException((new StringBuilder()).append("Unable to create directory: ").append(s.getAbsolutePath()).toString());
            setDestinationFromBase(s, s1);
            return this;
        }

        public Request setDestinationToSystemCache()
        {
            mUseSystemCache = true;
            return this;
        }

        public Request setDestinationUri(Uri uri)
        {
            mDestinationUri = uri;
            return this;
        }

        public void setExtra(String s)
        {
            mExtra = s;
        }

        public Request setExtra2(String s)
        {
            mExtra2 = s;
            return this;
        }

        public void setFileHash(String s)
        {
            mFileHash = s;
        }

        public Request setFileIconUri(Uri uri)
        {
            mFileIconUri = uri;
            return this;
        }

        public Request setFileSize(long l)
        {
            mFileSize = l;
            return this;
        }

        public void setInstallWay(int i)
        {
            mInstallWay = i;
        }

        public Request setMimeType(String s)
        {
            mMimeType = s;
            return this;
        }

        public Request setNotificationClass(String s)
        {
            mNotificationClass = s;
            return this;
        }

        public Request setNotificationVisibility(int i)
        {
            mNotificationVisibility = i;
            return this;
        }

        public Request setRequiresCharging(boolean flag)
        {
            if(flag)
                mFlags = mFlags | 1;
            else
                mFlags = mFlags & -2;
            return this;
        }

        public Request setRequiresDeviceIdle(boolean flag)
        {
            if(flag)
                mFlags = mFlags | 2;
            else
                mFlags = mFlags & -3;
            return this;
        }

        public Request setShowRunningNotification(boolean flag)
        {
            Request request;
            if(flag)
                request = setNotificationVisibility(0);
            else
                request = setNotificationVisibility(2);
            return request;
        }

        public Request setTitle(CharSequence charsequence)
        {
            mTitle = charsequence;
            return this;
        }

        public void setUserAgent(String s)
        {
            mUserAgent = s;
        }

        public Request setVisibleInDownloadsUi(boolean flag)
        {
            mIsVisibleInDownloadsUi = flag;
            return this;
        }

        public void setXlVipStatus(int i)
        {
            mXlVipStatus = i;
        }

        ContentValues toContentValues(String s)
        {
            byte byte0 = 2;
            ContentValues contentvalues = new ContentValues();
            if(!_2D_assertionsDisabled && mUri == null)
                throw new AssertionError();
            contentvalues.put("uri", mUri.toString());
            contentvalues.put("is_public_api", Boolean.valueOf(true));
            contentvalues.put("notificationpackage", s);
            byte byte1;
            if(mDestinationUri != null)
            {
                contentvalues.put("destination", Integer.valueOf(4));
                contentvalues.put("hint", mDestinationUri.toString());
            } else
            {
                byte byte2;
                if(mUseSystemCache)
                    byte2 = 5;
                else
                    byte2 = 2;
                contentvalues.put("destination", Integer.valueOf(byte2));
            }
            byte1 = byte0;
            if(mScannable)
                byte1 = 0;
            contentvalues.put("scanned", Integer.valueOf(byte1));
            if(!mRequestHeaders.isEmpty())
                encodeHttpHeaders(contentvalues);
            putIfNonNull(contentvalues, "title", mTitle);
            putIfNonNull(contentvalues, "description", mDescription);
            putIfNonNull(contentvalues, "mimetype", mMimeType);
            contentvalues.put("visibility", Integer.valueOf(mNotificationVisibility));
            contentvalues.put("allowed_network_types", Integer.valueOf(mAllowedNetworkTypes));
            contentvalues.put("allow_roaming", Boolean.valueOf(mRoamingAllowed));
            contentvalues.put("allow_metered", Boolean.valueOf(mMeteredAllowed));
            contentvalues.put("flags", Integer.valueOf(mFlags));
            contentvalues.put("is_visible_in_downloads_ui", Boolean.valueOf(mIsVisibleInDownloadsUi));
            putIfNonNull(contentvalues, "entity", mColumnAppData);
            putIfNonNull(contentvalues, "appointname", mAppointName);
            putIfNonNull(contentvalues, "notificationclass", mNotificationClass);
            putIfNonNull(contentvalues, "useragent", mUserAgent);
            contentvalues.put("total_bytes", Long.valueOf(mFileSize));
            putIfNonNull(contentvalues, "download_task_thumbnail", mFileIconUri);
            putIfNonNull(contentvalues, "download_extra2", mExtra2);
            putIfNonNull(contentvalues, "apk_package_name", mApkPackageName);
            putIfNonNull(contentvalues, "download_file_hash", mFileHash);
            contentvalues.put("download_apk_install_way", Integer.valueOf(mInstallWay));
            putIfNonNull(contentvalues, "download_extra", mExtra);
            return contentvalues;
        }

        static final boolean _2D_assertionsDisabled = android/app/DownloadManager$Request.desiredAssertionStatus() ^ true;
        public static final int INSTALL_WAY_Manual = 2;
        public static final int INSTALL_WAY_NONE = 0;
        public static final int INSTALL_WAY_SILENCE = 1;
        public static final int NETWORK_BLUETOOTH = 4;
        public static final int NETWORK_MOBILE = 1;
        public static final int NETWORK_WIFI = 2;
        private static final int SCANNABLE_VALUE_NO = 2;
        private static final int SCANNABLE_VALUE_YES = 0;
        public static final int VISIBILITY_HIDDEN = 2;
        public static final int VISIBILITY_VISIBLE = 0;
        public static final int VISIBILITY_VISIBLE_NOTIFY_COMPLETED = 1;
        public static final int VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION = 3;
        private int mAllowedNetworkTypes;
        private String mApkPackageName;
        private String mAppointName;
        private boolean mBypassRecommendedSizeLimit;
        private String mColumnAppData;
        private CharSequence mDescription;
        private Uri mDestinationUri;
        private String mExtra;
        private String mExtra2;
        private String mFileHash;
        private Uri mFileIconUri;
        private long mFileSize;
        private int mFlags;
        private int mInstallWay;
        private boolean mIsVisibleInDownloadsUi;
        private boolean mMeteredAllowed;
        private String mMimeType;
        private String mNotificationClass;
        private int mNotificationVisibility;
        private List mRequestHeaders;
        private boolean mRoamingAllowed;
        private boolean mScannable;
        private CharSequence mTitle;
        private Uri mUri;
        private boolean mUseSystemCache;
        private String mUserAgent;
        private int mXlVipStatus;


        public Request(Uri uri)
        {
            mRequestHeaders = new ArrayList();
            mAllowedNetworkTypes = -1;
            mRoamingAllowed = true;
            mMeteredAllowed = true;
            mFlags = 0;
            mIsVisibleInDownloadsUi = true;
            mScannable = false;
            mUseSystemCache = false;
            mNotificationVisibility = 0;
            mFileSize = -1L;
            mXlVipStatus = 0;
            mInstallWay = 0;
            checkUri(uri);
            mUri = uri;
        }

        Request(String s)
        {
            mRequestHeaders = new ArrayList();
            mAllowedNetworkTypes = -1;
            mRoamingAllowed = true;
            mMeteredAllowed = true;
            mFlags = 0;
            mIsVisibleInDownloadsUi = true;
            mScannable = false;
            mUseSystemCache = false;
            mNotificationVisibility = 0;
            mFileSize = -1L;
            mXlVipStatus = 0;
            mInstallWay = 0;
            mUri = Uri.parse(s);
        }
    }


    public DownloadManager(Context context)
    {
        mBaseUri = android.provider.Downloads.Impl.CONTENT_URI;
        mResolver = context.getContentResolver();
        mPackageName = context.getPackageName();
        boolean flag;
        if(context.getApplicationInfo().targetSdkVersion < 24)
            flag = true;
        else
            flag = false;
        mAccessFilename = flag;
    }

    public static void addRunningStatusAndControlRun(ContentValues contentvalues)
    {
        if(contentvalues != null)
        {
            contentvalues.put("status", Integer.valueOf(192));
            contentvalues.put("control", Integer.valueOf(0));
        }
    }

    private static Object[] concatArrays(Object aobj[], Object aobj1[], Class class1)
    {
        class1 = ((Class) ((Object[])Array.newInstance(class1, aobj.length + aobj1.length)));
        System.arraycopy(((Object) (aobj)), 0, class1, 0, aobj.length);
        System.arraycopy(((Object) (aobj1)), 0, class1, aobj.length, aobj1.length);
        return class1;
    }

    public static long getActiveNetworkWarningBytes(Context context)
    {
        return -1L;
    }

    public static int getDownloadStatus(Cursor cursor)
    {
        return cursor.getInt(cursor.getColumnIndex("status"));
    }

    public static Long getMaxBytesOverMobile(Context context)
    {
        long l;
        try
        {
            l = android.provider.Settings.Global.getLong(context.getContentResolver(), "download_manager_max_bytes_over_mobile");
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            return null;
        }
        return Long.valueOf(l);
    }

    public static Long getRecommendedMaxBytesOverMobile(Context context)
    {
        long l;
        try
        {
            l = android.provider.Settings.Global.getLong(context.getContentResolver(), "download_manager_recommended_max_bytes_over_mobile");
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            return null;
        }
        return Long.valueOf(l);
    }

    static String[] getWhereArgsForIds(long al[])
    {
        return getWhereArgsForIds(al, new String[al.length]);
    }

    static String[] getWhereArgsForIds(long al[], String as[])
    {
        if(!_2D_assertionsDisabled && as.length < al.length)
            throw new AssertionError();
        for(int i = 0; i < al.length; i++)
            as[i] = Long.toString(al[i]);

        return as;
    }

    private static String[] getWhereArgsForStatuses(int ai[])
    {
        String as[] = new String[ai.length];
        for(int i = 0; i < ai.length; i++)
            as[i] = Integer.toString(ai[i]);

        return as;
    }

    static String getWhereClauseForIds(long al[])
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("(");
        for(int i = 0; i < al.length; i++)
        {
            if(i > 0)
                stringbuilder.append("OR ");
            stringbuilder.append("_id");
            stringbuilder.append(" = ? ");
        }

        stringbuilder.append(")");
        return stringbuilder.toString();
    }

    private static String getWhereClauseForStatuses(String as[], String as1[])
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("(");
        for(int i = 0; i < as.length; i++)
        {
            if(i > 0)
                stringbuilder.append(as1[i - 1]).append(" ");
            stringbuilder.append("status");
            stringbuilder.append(" ").append(as[i]).append(" ? ");
        }

        stringbuilder.append(")");
        return stringbuilder.toString();
    }

    public static boolean isActiveNetworkExpensive(Context context)
    {
        return false;
    }

    public static boolean isDownloadSuccess(Cursor cursor)
    {
        boolean flag;
        if(cursor.getInt(cursor.getColumnIndex("status")) == 8)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean isDownloading(Cursor cursor)
    {
        boolean flag = false;
        cursor.getInt(cursor.getColumnIndex("status"));
        JVM INSTR tableswitch 1 2: default 40
    //                   1 42
    //                   2 42;
           goto _L1 _L2 _L2
_L1:
        return flag;
_L2:
        flag = true;
        if(true) goto _L1; else goto _L3
_L3:
    }

    public static void setDownloadUpdateProgressRegistration(Context context, long al[], long al1[])
    {
        Intent intent = new Intent("android.intent.action.DOWNLOAD_UPDATE_PROGRESS_REGISTRATION");
        intent.putExtra("intent_extra_register_downloads_update_progress", al);
        intent.putExtra("intent_extra_unregister_downloads_update_progress", al1);
        context.sendBroadcast(intent);
    }

    public static boolean setRecommendedMaxBytesOverMobile(Context context, long l)
    {
        return android.provider.Settings.Global.putLong(context.getContentResolver(), "download_manager_recommended_max_bytes_over_mobile", l);
    }

    public static int translateStatus(int i)
    {
        return CursorTranslator.translateStatus(i);
    }

    private static void validateArgumentIsNonEmpty(String s, String s1)
    {
        if(TextUtils.isEmpty(s1))
            throw new IllegalArgumentException((new StringBuilder()).append(s).append(" can't be null").toString());
        else
            return;
    }

    public long addCompletedDownload(String s, String s1, boolean flag, String s2, String s3, long l, 
            boolean flag1)
    {
        return addCompletedDownload(s, s1, flag, s2, s3, l, flag1, false, null, null);
    }

    public long addCompletedDownload(String s, String s1, boolean flag, String s2, String s3, long l, 
            boolean flag1, Uri uri, Uri uri1)
    {
        return addCompletedDownload(s, s1, flag, s2, s3, l, flag1, false, uri, uri1);
    }

    public long addCompletedDownload(String s, String s1, boolean flag, String s2, String s3, long l, 
            boolean flag1, boolean flag2)
    {
        return addCompletedDownload(s, s1, flag, s2, s3, l, flag1, flag2, null, null);
    }

    public long addCompletedDownload(String s, String s1, boolean flag, String s2, String s3, long l, 
            boolean flag1, boolean flag2, Uri uri, Uri uri1)
    {
        validateArgumentIsNonEmpty("title", s);
        validateArgumentIsNonEmpty("description", s1);
        validateArgumentIsNonEmpty("path", s3);
        validateArgumentIsNonEmpty("mimeType", s2);
        if(l < 0L)
            throw new IllegalArgumentException(" invalid value for param: totalBytes");
        int i;
        if(uri != null)
            uri = new Request(uri);
        else
            uri = new Request("non-dwnldmngr-download-dont-retry2download");
        uri.setTitle(s).setDescription(s1).setMimeType(s2);
        if(uri1 != null)
            uri.addRequestHeader("Referer", uri1.toString());
        s = uri.toContentValues(null);
        s.put("destination", Integer.valueOf(6));
        s.put("_data", s3);
        s.put("status", Integer.valueOf(200));
        s.put("total_bytes", Long.valueOf(l));
        if(flag)
            i = 0;
        else
            i = 2;
        s.put("scanned", Integer.valueOf(i));
        if(flag1)
            i = 3;
        else
            i = 2;
        s.put("visibility", Integer.valueOf(i));
        if(flag2)
            i = 1;
        else
            i = 0;
        s.put("allow_write", Integer.valueOf(i));
        s = mResolver.insert(android.provider.Downloads.Impl.CONTENT_URI, s);
        if(s == null)
            return -1L;
        else
            return Long.parseLong(s.getLastPathSegment());
    }

    public long enqueue(Request request)
    {
        request = request.toContentValues(mPackageName);
        return Long.parseLong(mResolver.insert(android.provider.Downloads.Impl.CONTENT_URI, request).getLastPathSegment());
    }

    public transient void forceDownload(long al[])
    {
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("status", Integer.valueOf(190));
        contentvalues.put("control", Integer.valueOf(0));
        contentvalues.put("bypass_recommended_size_limit", Integer.valueOf(1));
        mResolver.update(mBaseUri, contentvalues, getWhereClauseForIds(al), getWhereArgsForIds(al));
    }

    public Uri getDownloadUri(long l)
    {
        return ContentUris.withAppendedId(android.provider.Downloads.Impl.ALL_DOWNLOADS_CONTENT_URI, l);
    }

    public String getMimeTypeForDownloadedFile(long l)
    {
        Object obj;
        Cursor cursor;
        obj = (new Query()).setFilterById(new long[] {
            l
        });
        cursor = null;
        obj = query(((Query) (obj)));
        if(obj == null)
        {
            if(obj != null)
                ((Cursor) (obj)).close();
            return null;
        }
        cursor = ((Cursor) (obj));
        if(!((Cursor) (obj)).moveToFirst())
            break MISSING_BLOCK_LABEL_87;
        cursor = ((Cursor) (obj));
        String s = ((Cursor) (obj)).getString(((Cursor) (obj)).getColumnIndexOrThrow("media_type"));
        if(obj != null)
            ((Cursor) (obj)).close();
        return s;
        if(obj != null)
            ((Cursor) (obj)).close();
        return null;
        Exception exception;
        exception;
        if(cursor != null)
            cursor.close();
        throw exception;
    }

    public Uri getUriForDownloadedFile(long l)
    {
        Object obj;
        Cursor cursor;
        obj = (new Query()).setFilterById(new long[] {
            l
        });
        cursor = null;
        obj = query(((Query) (obj)));
        if(obj == null)
        {
            if(obj != null)
                ((Cursor) (obj)).close();
            return null;
        }
        cursor = ((Cursor) (obj));
        if(!((Cursor) (obj)).moveToFirst())
            break MISSING_BLOCK_LABEL_102;
        cursor = ((Cursor) (obj));
        if(8 != ((Cursor) (obj)).getInt(((Cursor) (obj)).getColumnIndexOrThrow("status")))
            break MISSING_BLOCK_LABEL_102;
        cursor = ((Cursor) (obj));
        Uri uri = ContentUris.withAppendedId(android.provider.Downloads.Impl.ALL_DOWNLOADS_CONTENT_URI, l);
        if(obj != null)
            ((Cursor) (obj)).close();
        return uri;
        if(obj != null)
            ((Cursor) (obj)).close();
        return null;
        Exception exception;
        exception;
        if(cursor != null)
            cursor.close();
        throw exception;
    }

    public transient int markRowDeleted(long al[])
    {
        if(al == null || al.length == 0)
            throw new IllegalArgumentException("input param 'ids' can't be null");
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("deleted", Integer.valueOf(1));
        if(al.length == 1)
            return mResolver.update(ContentUris.withAppendedId(mBaseUri, al[0]), contentvalues, null, null);
        else
            return mResolver.update(mBaseUri, contentvalues, getWhereClauseForIds(al), getWhereArgsForIds(al));
    }

    public ParcelFileDescriptor openDownloadedFile(long l)
        throws FileNotFoundException
    {
        return mResolver.openFileDescriptor(getDownloadUri(l), "r");
    }

    public transient void pauseDownload(long al[])
    {
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("status", Integer.valueOf(193));
        contentvalues.put("control", Integer.valueOf(1));
        String s = (new StringBuilder()).append("( ").append(getWhereClauseForIds(al)).append(" AND ").append(getWhereClauseForStatuses(new String[] {
            "=", "="
        }, new String[] {
            "OR"
        })).append(")").toString();
        al = (String[])concatArrays(getWhereArgsForIds(al), getWhereArgsForStatuses(new int[] {
            190, 192
        }), java/lang/String);
        mResolver.update(mBaseUri, contentvalues, s, al);
    }

    public Cursor query(Query query1)
    {
        query1 = query1.runQuery(mResolver, MIUI_UNDERLYING_COLUMNS, mBaseUri);
        if(query1 == null)
            return null;
        else
            return new CursorTranslator(query1, mBaseUri, mAccessFilename);
    }

    public transient int remove(long al[])
    {
        return markRowDeleted(al);
    }

    public transient int removeRecordOnly(long al[])
    {
        if(al == null || al.length == 0)
            throw new IllegalArgumentException("input param 'ids' can't be null");
        else
            return mResolver.delete(mBaseUri, getWhereClauseForIds(al), getWhereArgsForIds(al));
    }

    public boolean rename(Context context, long l, String s)
    {
        Object obj;
        Cursor cursor;
        String s1;
        String s2;
        if(!FileUtils.isValidFatFilename(s))
            throw new SecurityException((new StringBuilder()).append(s).append(" is not a valid filename").toString());
        obj = (new Query()).setFilterById(new long[] {
            l
        });
        cursor = null;
        s1 = null;
        s2 = null;
        obj = query(((Query) (obj)));
        if(obj == null)
        {
            if(obj != null)
                ((Cursor) (obj)).close();
            return false;
        }
        cursor = ((Cursor) (obj));
        if(!((Cursor) (obj)).moveToFirst())
            break MISSING_BLOCK_LABEL_193;
        cursor = ((Cursor) (obj));
        int i = ((Cursor) (obj)).getInt(((Cursor) (obj)).getColumnIndexOrThrow("status"));
        if(8 != i)
        {
            if(obj != null)
                ((Cursor) (obj)).close();
            return false;
        }
        cursor = ((Cursor) (obj));
        s1 = ((Cursor) (obj)).getString(((Cursor) (obj)).getColumnIndexOrThrow("title"));
        cursor = ((Cursor) (obj));
        s2 = ((Cursor) (obj)).getString(((Cursor) (obj)).getColumnIndexOrThrow("media_type"));
        if(obj != null)
            ((Cursor) (obj)).close();
        if(s1 == null || s2 == null)
            throw new IllegalStateException((new StringBuilder()).append("Document with id ").append(l).append(" does not exist").toString());
        break MISSING_BLOCK_LABEL_264;
        context;
        if(cursor != null)
            cursor.close();
        throw context;
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        Object obj1 = new File(file, s1);
        file = new File(file, s);
        if(file.exists())
            throw new IllegalStateException((new StringBuilder()).append("Already exists ").append(file).toString());
        if(!((File) (obj1)).renameTo(file))
            throw new IllegalStateException((new StringBuilder()).append("Failed to rename to ").append(file).toString());
        if(s2.startsWith("image/"))
        {
            context.getContentResolver().delete(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "_data=?", new String[] {
                ((File) (obj1)).getAbsolutePath()
            });
            obj1 = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
            ((Intent) (obj1)).setData(Uri.fromFile(file));
            context.sendBroadcast(((Intent) (obj1)));
        }
        context = new ContentValues();
        context.put("title", s);
        context.put("_data", file.toString());
        context.putNull("mediaprovider_uri");
        s = new long[1];
        s[0] = l;
        boolean flag;
        if(mResolver.update(mBaseUri, context, getWhereClauseForIds(s), getWhereArgsForIds(s)) == 1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public transient void restartDownload(long al[])
    {
        Object obj = query((new Query()).setFilterById(al));
        ((Cursor) (obj)).moveToFirst();
_L1:
        int i;
        if(((Cursor) (obj)).isAfterLast())
            break MISSING_BLOCK_LABEL_126;
        i = ((Cursor) (obj)).getInt(((Cursor) (obj)).getColumnIndex("status"));
        if(i == 8 || i == 16)
            break MISSING_BLOCK_LABEL_116;
        al = JVM INSTR new #422 <Class IllegalArgumentException>;
        StringBuilder stringbuilder = JVM INSTR new #362 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        al.IllegalArgumentException(stringbuilder.append("Cannot restart incomplete download: ").append(((Cursor) (obj)).getLong(((Cursor) (obj)).getColumnIndex("_id"))).toString());
        throw al;
        al;
        ((Cursor) (obj)).close();
        throw al;
        ((Cursor) (obj)).moveToNext();
          goto _L1
        ((Cursor) (obj)).close();
        obj = new ContentValues();
        ((ContentValues) (obj)).put("current_bytes", Integer.valueOf(0));
        ((ContentValues) (obj)).put("total_bytes", Integer.valueOf(-1));
        ((ContentValues) (obj)).putNull("_data");
        ((ContentValues) (obj)).put("status", Integer.valueOf(190));
        ((ContentValues) (obj)).put("numfailed", Integer.valueOf(0));
        mResolver.update(mBaseUri, ((ContentValues) (obj)), getWhereClauseForIds(al), getWhereArgsForIds(al));
        return;
    }

    public transient void resumeDownload(long al[])
    {
        ContentValues contentvalues = new ContentValues();
        addRunningStatusAndControlRun(contentvalues);
        String s = (new StringBuilder()).append("( ").append(getWhereClauseForIds(al)).append(" AND ").append(getWhereClauseForStatuses(new String[] {
            "=", "=", "=", "="
        }, new String[] {
            "OR", "OR", "OR"
        })).append(")").toString();
        al = (String[])concatArrays(getWhereArgsForIds(al), getWhereArgsForStatuses(new int[] {
            193, 194, 195, 196
        }), java/lang/String);
        mResolver.update(mBaseUri, contentvalues, s, al);
    }

    public void setAccessAllDownloads(boolean flag)
    {
        if(flag)
            mBaseUri = android.provider.Downloads.Impl.ALL_DOWNLOADS_CONTENT_URI;
        else
            mBaseUri = android.provider.Downloads.Impl.CONTENT_URI;
    }

    public void setAccessFilename(boolean flag)
    {
        mAccessFilename = flag;
    }

    static final boolean _2D_assertionsDisabled = android/app/DownloadManager.desiredAssertionStatus() ^ true;
    public static final String ACTION_DOWNLOAD_COMPLETE = "android.intent.action.DOWNLOAD_COMPLETE";
    public static final String ACTION_DOWNLOAD_COMPLETED = "android.intent.action.DOWNLOAD_COMPLETED";
    public static final String ACTION_DOWNLOAD_DELETED = "android.intent.action.DOWNLOAD_DELETED";
    public static final String ACTION_DOWNLOAD_UPDATED = "android.intent.action.DOWNLOAD_UPDATED";
    public static final String ACTION_DOWNLOAD_UPDATE_PROGRESS_REGISTRATION = "android.intent.action.DOWNLOAD_UPDATE_PROGRESS_REGISTRATION";
    public static final String ACTION_NOTIFICATION_CLICKED = "android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED";
    public static final String ACTION_VIEW_DOWNLOADS = "android.intent.action.VIEW_DOWNLOADS";
    public static final String COLUMN_ALLOW_WRITE = "allow_write";
    public static final String COLUMN_BYTES_DOWNLOADED_SO_FAR = "bytes_so_far";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_LAST_MODIFIED_TIMESTAMP = "last_modified_timestamp";
    public static final String COLUMN_LOCAL_FILENAME = "local_filename";
    public static final String COLUMN_LOCAL_URI = "local_uri";
    public static final String COLUMN_MEDIAPROVIDER_URI = "mediaprovider_uri";
    public static final String COLUMN_MEDIA_TYPE = "media_type";
    public static final String COLUMN_REASON = "reason";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_TOTAL_SIZE_BYTES = "total_size";
    public static final String COLUMN_URI = "uri";
    public static final int ERROR_BLOCKED = 1010;
    public static final int ERROR_CANNOT_RESUME = 1008;
    public static final int ERROR_DEVICE_NOT_FOUND = 1007;
    public static final int ERROR_FILE_ALREADY_EXISTS = 1009;
    public static final int ERROR_FILE_ERROR = 1001;
    public static final int ERROR_HTTP_DATA_ERROR = 1004;
    public static final int ERROR_INSUFFICIENT_SPACE = 1006;
    public static final int ERROR_TOO_MANY_REDIRECTS = 1005;
    public static final int ERROR_UNHANDLED_HTTP_CODE = 1002;
    public static final int ERROR_UNKNOWN = 1000;
    public static final String EXTRA_DOWNLOAD_CURRENT_BYTES = "extra_download_current_bytes";
    public static final String EXTRA_DOWNLOAD_ID = "extra_download_id";
    public static final String EXTRA_DOWNLOAD_STATUS = "extra_download_status";
    public static final String EXTRA_DOWNLOAD_TOTAL_BYTES = "extra_download_total_bytes";
    public static final String EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS = "extra_click_download_ids";
    public static final String INTENT_EXTRAS_SORT_BY_SIZE = "android.app.DownloadManager.extra_sortBySize";
    public static final String INTENT_EXTRA_APPLICATION_PACKAGENAME = "intent_extra_application_packagename";
    public static final String INTENT_EXTRA_REGISTER_DOWNLOADS_UPDATE_PROGRESS = "intent_extra_register_downloads_update_progress";
    public static final String INTENT_EXTRA_UNREGISTER_DOWNLOADS_UPDATE_PROGRESS = "intent_extra_unregister_downloads_update_progress";
    public static final String MIUI_UNDERLYING_COLUMNS[] = (String[])concatArrays(UNDERLYING_COLUMNS, new String[] {
        "entity", "file_create_time", "downloading_current_speed", "download_surplus_time", "xl_accelerate_speed", "downloaded_time", "xl_vip_status", "xl_vip_cdn_url", "xl_task_open_mark", "download_task_thumbnail", 
        "apk_package_name", "download_file_hash", "download_apk_install_way", "download_extra"
    }, java/lang/String);
    private static final String NON_DOWNLOADMANAGER_DOWNLOAD = "non-dwnldmngr-download-dont-retry2download";
    public static final int PAUSED_BY_APP = 5;
    public static final int PAUSED_QUEUED_FOR_WIFI = 3;
    public static final int PAUSED_UNKNOWN = 4;
    public static final int PAUSED_WAITING_FOR_NETWORK = 2;
    public static final int PAUSED_WAITING_TO_RETRY = 1;
    public static final int PAUSE_INSUFFICIENT_SPACE = 6;
    public static final String PERMISSION_SILENCE_INSTALL = "android.permission.XL_SILENCE_INSTALL";
    public static final int STATUS_FAILED = 16;
    public static final int STATUS_PAUSED = 4;
    public static final int STATUS_PENDING = 1;
    public static final int STATUS_RUNNING = 2;
    public static final int STATUS_SUCCESSFUL = 8;
    public static final String UNDERLYING_COLUMNS[] = {
        "_id", "_data AS local_filename", "mediaprovider_uri", "destination", "title", "description", "uri", "status", "hint", "mimetype AS media_type", 
        "total_bytes AS total_size", "lastmod AS last_modified_timestamp", "current_bytes AS bytes_so_far", "allow_write", "errorMsg", "notificationpackage", "'placeholder' AS local_uri", "'placeholder' AS reason"
    };
    private boolean mAccessFilename;
    private Uri mBaseUri;
    private final String mPackageName;
    private final ContentResolver mResolver;

}
