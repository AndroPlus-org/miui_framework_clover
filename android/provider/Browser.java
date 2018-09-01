// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.provider;

import android.content.*;
import android.database.*;
import android.net.Uri;
import android.util.SeempLog;

// Referenced classes of package android.provider:
//            BaseColumns

public class Browser
{
    public static class BookmarkColumns
        implements BaseColumns
    {

        public static final String BOOKMARK = "bookmark";
        public static final String CREATED = "created";
        public static final String DATE = "date";
        public static final String FAVICON = "favicon";
        public static final String THUMBNAIL = "thumbnail";
        public static final String TITLE = "title";
        public static final String TOUCH_ICON = "touch_icon";
        public static final String URL = "url";
        public static final String USER_ENTERED = "user_entered";
        public static final String VISITS = "visits";

        public BookmarkColumns()
        {
        }
    }

    public static class SearchColumns
        implements BaseColumns
    {

        public static final String DATE = "date";
        public static final String SEARCH = "search";
        public static final String URL = "url";

        public SearchColumns()
        {
        }
    }


    public Browser()
    {
    }

    private static final void addOrUrlEquals(StringBuilder stringbuilder)
    {
        stringbuilder.append(" OR url = ");
    }

    public static final void addSearchUrl(ContentResolver contentresolver, String s)
    {
    }

    public static final boolean canClearHistory(ContentResolver contentresolver)
    {
        return false;
    }

    public static final void clearHistory(ContentResolver contentresolver)
    {
        SeempLog.record(37);
    }

    public static final void clearSearches(ContentResolver contentresolver)
    {
    }

    public static final void deleteFromHistory(ContentResolver contentresolver, String s)
    {
    }

    public static final void deleteHistoryTimeFrame(ContentResolver contentresolver, long l, long l1)
    {
    }

    public static final Cursor getAllBookmarks(ContentResolver contentresolver)
        throws IllegalStateException
    {
        SeempLog.record(32);
        return new MatrixCursor(new String[] {
            "url"
        }, 0);
    }

    public static final Cursor getAllVisitedUrls(ContentResolver contentresolver)
        throws IllegalStateException
    {
        SeempLog.record(33);
        return new MatrixCursor(new String[] {
            "url"
        }, 0);
    }

    public static final String[] getVisitedHistory(ContentResolver contentresolver)
    {
        SeempLog.record(35);
        return new String[0];
    }

    private static final Cursor getVisitedLike(ContentResolver contentresolver, String s)
    {
        SeempLog.record(34);
        boolean flag = false;
        Object obj = s;
        if(s.startsWith("http://"))
            obj = s.substring(7);
        else
        if(s.startsWith("https://"))
        {
            obj = s.substring(8);
            flag = true;
        }
        s = ((String) (obj));
        if(((String) (obj)).startsWith("www."))
            s = ((String) (obj)).substring(4);
        if(flag)
        {
            obj = new StringBuilder("url = ");
            DatabaseUtils.appendEscapedSQLString(((StringBuilder) (obj)), (new StringBuilder()).append("https://").append(s).toString());
            addOrUrlEquals(((StringBuilder) (obj)));
            DatabaseUtils.appendEscapedSQLString(((StringBuilder) (obj)), (new StringBuilder()).append("https://www.").append(s).toString());
            s = ((String) (obj));
        } else
        {
            StringBuilder stringbuilder = new StringBuilder("url = ");
            DatabaseUtils.appendEscapedSQLString(stringbuilder, s);
            addOrUrlEquals(stringbuilder);
            String s1 = (new StringBuilder()).append("www.").append(s).toString();
            DatabaseUtils.appendEscapedSQLString(stringbuilder, s1);
            addOrUrlEquals(stringbuilder);
            DatabaseUtils.appendEscapedSQLString(stringbuilder, (new StringBuilder()).append("http://").append(s).toString());
            addOrUrlEquals(stringbuilder);
            DatabaseUtils.appendEscapedSQLString(stringbuilder, (new StringBuilder()).append("http://").append(s1).toString());
            s = stringbuilder;
        }
        obj = BrowserContract.History.CONTENT_URI;
        s = s.toString();
        return contentresolver.query(((Uri) (obj)), new String[] {
            "_id", "visits"
        }, s, null, null);
    }

    public static final void requestAllIcons(ContentResolver contentresolver, String s, android.webkit.WebIconDatabase.IconListener iconlistener)
    {
        SeempLog.record(36);
    }

    public static final void saveBookmark(Context context, String s, String s1)
    {
    }

    public static final void sendString(Context context, String s)
    {
        sendString(context, s, context.getString(0x10405b3));
    }

    public static final void sendString(Context context, String s, String s1)
    {
        Intent intent;
        intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", s);
        s = Intent.createChooser(intent, s1);
        s.setFlags(0x10000000);
        context.startActivity(s);
_L2:
        return;
        context;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static final void truncateHistory(ContentResolver contentresolver)
    {
    }

    public static final void updateVisitedHistory(ContentResolver contentresolver, String s, boolean flag)
    {
    }

    public static final Uri BOOKMARKS_URI = Uri.parse("content://browser/bookmarks");
    public static final String EXTRA_APPLICATION_ID = "com.android.browser.application_id";
    public static final String EXTRA_CREATE_NEW_TAB = "create_new_tab";
    public static final String EXTRA_HEADERS = "com.android.browser.headers";
    public static final String EXTRA_SHARE_FAVICON = "share_favicon";
    public static final String EXTRA_SHARE_SCREENSHOT = "share_screenshot";
    public static final String HISTORY_PROJECTION[] = {
        "_id", "url", "visits", "date", "bookmark", "title", "favicon", "thumbnail", "touch_icon", "user_entered"
    };
    public static final int HISTORY_PROJECTION_BOOKMARK_INDEX = 4;
    public static final int HISTORY_PROJECTION_DATE_INDEX = 3;
    public static final int HISTORY_PROJECTION_FAVICON_INDEX = 6;
    public static final int HISTORY_PROJECTION_ID_INDEX = 0;
    public static final int HISTORY_PROJECTION_THUMBNAIL_INDEX = 7;
    public static final int HISTORY_PROJECTION_TITLE_INDEX = 5;
    public static final int HISTORY_PROJECTION_TOUCH_ICON_INDEX = 8;
    public static final int HISTORY_PROJECTION_URL_INDEX = 1;
    public static final int HISTORY_PROJECTION_VISITS_INDEX = 2;
    public static final String INITIAL_ZOOM_LEVEL = "browser.initialZoomLevel";
    private static final String LOGTAG = "browser";
    private static final int MAX_HISTORY_COUNT = 250;
    public static final String SEARCHES_PROJECTION[] = {
        "_id", "search", "date"
    };
    public static final int SEARCHES_PROJECTION_DATE_INDEX = 2;
    public static final int SEARCHES_PROJECTION_SEARCH_INDEX = 1;
    public static final Uri SEARCHES_URI = Uri.parse("content://browser/searches");
    public static final String TRUNCATE_HISTORY_PROJECTION[] = {
        "_id", "date"
    };
    public static final int TRUNCATE_HISTORY_PROJECTION_ID_INDEX = 0;
    public static final int TRUNCATE_N_OLDEST = 5;

}
