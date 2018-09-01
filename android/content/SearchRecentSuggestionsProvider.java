// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import java.util.List;

// Referenced classes of package android.content:
//            ContentProvider, Context, ContentResolver, UriMatcher, 
//            ContentValues

public class SearchRecentSuggestionsProvider extends ContentProvider
{
    private static class DatabaseHelper extends SQLiteOpenHelper
    {

        public void onCreate(SQLiteDatabase sqlitedatabase)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("CREATE TABLE suggestions (_id INTEGER PRIMARY KEY,display1 TEXT UNIQUE ON CONFLICT REPLACE");
            if((mNewVersion & 2) != 0)
                stringbuilder.append(",display2 TEXT");
            stringbuilder.append(",query TEXT,date LONG);");
            sqlitedatabase.execSQL(stringbuilder.toString());
        }

        public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
        {
            Log.w("SuggestionsProvider", (new StringBuilder()).append("Upgrading database from version ").append(i).append(" to ").append(j).append(", which will destroy all old data").toString());
            sqlitedatabase.execSQL("DROP TABLE IF EXISTS suggestions");
            onCreate(sqlitedatabase);
        }

        private int mNewVersion;

        public DatabaseHelper(Context context, int i)
        {
            super(context, "suggestions.db", null, i);
            mNewVersion = i;
        }
    }


    public SearchRecentSuggestionsProvider()
    {
    }

    public int delete(Uri uri, String s, String as[])
    {
        SQLiteDatabase sqlitedatabase = mOpenHelper.getWritableDatabase();
        if(uri.getPathSegments().size() != 1)
            throw new IllegalArgumentException("Unknown Uri");
        if(((String)uri.getPathSegments().get(0)).equals("suggestions"))
        {
            int i = sqlitedatabase.delete("suggestions", s, as);
            getContext().getContentResolver().notifyChange(uri, null);
            return i;
        } else
        {
            throw new IllegalArgumentException("Unknown Uri");
        }
    }

    public String getType(Uri uri)
    {
        if(mUriMatcher.match(uri) == 1)
            return "vnd.android.cursor.dir/vnd.android.search.suggest";
        int i = uri.getPathSegments().size();
        if(i >= 1 && ((String)uri.getPathSegments().get(0)).equals("suggestions"))
        {
            if(i == 1)
                return "vnd.android.cursor.dir/suggestion";
            if(i == 2)
                return "vnd.android.cursor.item/suggestion";
        }
        throw new IllegalArgumentException("Unknown Uri");
    }

    public Uri insert(Uri uri, ContentValues contentvalues)
    {
        SQLiteDatabase sqlitedatabase = mOpenHelper.getWritableDatabase();
        int i = uri.getPathSegments().size();
        if(i < 1)
            throw new IllegalArgumentException("Unknown Uri");
        long l = -1L;
        String s = (String)uri.getPathSegments().get(0);
        Object obj = null;
        uri = obj;
        long l2 = l;
        if(s.equals("suggestions"))
        {
            uri = obj;
            l2 = l;
            if(i == 1)
            {
                long l1 = sqlitedatabase.insert("suggestions", "query", contentvalues);
                uri = obj;
                l2 = l1;
                if(l1 > 0L)
                {
                    uri = Uri.withAppendedPath(mSuggestionsUri, String.valueOf(l1));
                    l2 = l1;
                }
            }
        }
        if(l2 < 0L)
        {
            throw new IllegalArgumentException("Unknown Uri");
        } else
        {
            getContext().getContentResolver().notifyChange(uri, null);
            return uri;
        }
    }

    public boolean onCreate()
    {
        if(mAuthority == null || mMode == 0)
        {
            throw new IllegalArgumentException("Provider not configured");
        } else
        {
            int i = mMode;
            mOpenHelper = new DatabaseHelper(getContext(), i + 512);
            return true;
        }
    }

    public Cursor query(Uri uri, String as[], String s, String as1[], String s1)
    {
        SQLiteDatabase sqlitedatabase = mOpenHelper.getReadableDatabase();
        if(mUriMatcher.match(uri) == 1)
        {
            if(TextUtils.isEmpty(as1[0]))
            {
                s = null;
                as = null;
            } else
            {
                s = (new StringBuilder()).append("%").append(as1[0]).append("%").toString();
                if(mTwoLineDisplay)
                {
                    as = new String[2];
                    as[0] = s;
                    as[1] = s;
                } else
                {
                    as = new String[1];
                    as[0] = s;
                }
                s = mSuggestSuggestionClause;
            }
            as = sqlitedatabase.query("suggestions", mSuggestionProjection, s, as, null, null, "date DESC", null);
            as.setNotificationUri(getContext().getContentResolver(), uri);
            return as;
        }
        int i = uri.getPathSegments().size();
        if(i != 1 && i != 2)
            throw new IllegalArgumentException("Unknown Uri");
        String s2 = (String)uri.getPathSegments().get(0);
        if(!s2.equals("suggestions"))
            throw new IllegalArgumentException("Unknown Uri");
        Object obj = null;
        String as2[] = obj;
        if(as != null)
        {
            as2 = obj;
            if(as.length > 0)
            {
                as2 = new String[as.length + 1];
                System.arraycopy(as, 0, as2, 0, as.length);
                as2[as.length] = "_id AS _id";
            }
        }
        as = new StringBuilder(256);
        if(i == 2)
            as.append("(_id = ").append((String)uri.getPathSegments().get(1)).append(")");
        if(s != null && s.length() > 0)
        {
            if(as.length() > 0)
                as.append(" AND ");
            as.append('(');
            as.append(s);
            as.append(')');
        }
        as = sqlitedatabase.query(s2, as2, as.toString(), as1, null, null, s1, null);
        as.setNotificationUri(getContext().getContentResolver(), uri);
        return as;
    }

    protected void setupSuggestions(String s, int i)
    {
        if(TextUtils.isEmpty(s) || (i & 1) == 0)
            throw new IllegalArgumentException();
        boolean flag;
        if((i & 2) != 0)
            flag = true;
        else
            flag = false;
        mTwoLineDisplay = flag;
        mAuthority = new String(s);
        mMode = i;
        mSuggestionsUri = Uri.parse((new StringBuilder()).append("content://").append(mAuthority).append("/suggestions").toString());
        mUriMatcher = new UriMatcher(-1);
        mUriMatcher.addURI(mAuthority, "search_suggest_query", 1);
        if(mTwoLineDisplay)
        {
            mSuggestSuggestionClause = "display1 LIKE ? OR display2 LIKE ?";
            mSuggestionProjection = (new String[] {
                "0 AS suggest_format", "'android.resource://system/17301578' AS suggest_icon_1", "display1 AS suggest_text_1", "display2 AS suggest_text_2", "query AS suggest_intent_query", "_id"
            });
        } else
        {
            mSuggestSuggestionClause = "display1 LIKE ?";
            mSuggestionProjection = (new String[] {
                "0 AS suggest_format", "'android.resource://system/17301578' AS suggest_icon_1", "display1 AS suggest_text_1", "query AS suggest_intent_query", "_id"
            });
        }
    }

    public int update(Uri uri, ContentValues contentvalues, String s, String as[])
    {
        throw new UnsupportedOperationException("Not implemented");
    }

    public static final int DATABASE_MODE_2LINES = 2;
    public static final int DATABASE_MODE_QUERIES = 1;
    private static final int DATABASE_VERSION = 512;
    private static final String NULL_COLUMN = "query";
    private static final String ORDER_BY = "date DESC";
    private static final String TAG = "SuggestionsProvider";
    private static final int URI_MATCH_SUGGEST = 1;
    private static final String sDatabaseName = "suggestions.db";
    private static final String sSuggestions = "suggestions";
    private String mAuthority;
    private int mMode;
    private SQLiteOpenHelper mOpenHelper;
    private String mSuggestSuggestionClause;
    private String mSuggestionProjection[];
    private Uri mSuggestionsUri;
    private boolean mTwoLineDisplay;
    private UriMatcher mUriMatcher;
}
