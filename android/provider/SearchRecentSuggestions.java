// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.provider;

import android.content.*;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import java.util.concurrent.Semaphore;

// Referenced classes of package android.provider:
//            BaseColumns

public class SearchRecentSuggestions
{
    private static class SuggestionColumns
        implements BaseColumns
    {

        public static final String DATE = "date";
        public static final String DISPLAY1 = "display1";
        public static final String DISPLAY2 = "display2";
        public static final String QUERY = "query";

        private SuggestionColumns()
        {
        }
    }


    static Semaphore _2D_get0()
    {
        return sWritesInProgress;
    }

    static void _2D_wrap0(SearchRecentSuggestions searchrecentsuggestions, String s, String s1)
    {
        searchrecentsuggestions.saveRecentQueryBlocking(s, s1);
    }

    public SearchRecentSuggestions(Context context, String s, int i)
    {
        boolean flag = false;
        super();
        if(TextUtils.isEmpty(s) || (i & 1) == 0)
            throw new IllegalArgumentException();
        if((i & 2) != 0)
            flag = true;
        mTwoLineDisplay = flag;
        mContext = context;
        mAuthority = new String(s);
        mSuggestionsUri = Uri.parse((new StringBuilder()).append("content://").append(mAuthority).append("/suggestions").toString());
    }

    private void saveRecentQueryBlocking(String s, String s1)
    {
        ContentResolver contentresolver = mContext.getContentResolver();
        long l = System.currentTimeMillis();
        try
        {
            ContentValues contentvalues = JVM INSTR new #131 <Class ContentValues>;
            contentvalues.ContentValues();
            contentvalues.put("display1", s);
            if(mTwoLineDisplay)
                contentvalues.put("display2", s1);
            contentvalues.put("query", s);
            contentvalues.put("date", Long.valueOf(l));
            contentresolver.insert(mSuggestionsUri, contentvalues);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.e("SearchSuggestions", "saveRecentQuery", s);
        }
        truncateHistory(contentresolver, 250);
    }

    public void clearHistory()
    {
        truncateHistory(mContext.getContentResolver(), 0);
    }

    public void saveRecentQuery(String s, String s1)
    {
        if(TextUtils.isEmpty(s))
            return;
        if(!mTwoLineDisplay && TextUtils.isEmpty(s1) ^ true)
        {
            throw new IllegalArgumentException();
        } else
        {
            (new Thread(s1) {

                public void run()
                {
                    SearchRecentSuggestions._2D_wrap0(SearchRecentSuggestions.this, queryString, line2);
                    SearchRecentSuggestions._2D_get0().release();
                }

                final SearchRecentSuggestions this$0;
                final String val$line2;
                final String val$queryString;

            
            {
                this$0 = SearchRecentSuggestions.this;
                queryString = s1;
                line2 = s2;
                super(final_s);
            }
            }
).start();
            return;
        }
    }

    protected void truncateHistory(ContentResolver contentresolver, int i)
    {
        Object obj;
        if(i < 0)
            throw new IllegalArgumentException();
        obj = null;
        if(i <= 0)
            break MISSING_BLOCK_LABEL_48;
        obj = JVM INSTR new #94  <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        obj = ((StringBuilder) (obj)).append("_id IN (SELECT _id FROM suggestions ORDER BY date DESC LIMIT -1 OFFSET ").append(String.valueOf(i)).append(")").toString();
        contentresolver.delete(mSuggestionsUri, ((String) (obj)), null);
_L1:
        return;
        contentresolver;
        Log.e("SearchSuggestions", "truncateHistory", contentresolver);
          goto _L1
    }

    void waitForSave()
    {
        do
            sWritesInProgress.acquireUninterruptibly();
        while(sWritesInProgress.availablePermits() > 0);
    }

    private static final String LOG_TAG = "SearchSuggestions";
    private static final int MAX_HISTORY_COUNT = 250;
    public static final String QUERIES_PROJECTION_1LINE[] = {
        "_id", "date", "query", "display1"
    };
    public static final String QUERIES_PROJECTION_2LINE[] = {
        "_id", "date", "query", "display1", "display2"
    };
    public static final int QUERIES_PROJECTION_DATE_INDEX = 1;
    public static final int QUERIES_PROJECTION_DISPLAY1_INDEX = 3;
    public static final int QUERIES_PROJECTION_DISPLAY2_INDEX = 4;
    public static final int QUERIES_PROJECTION_QUERY_INDEX = 2;
    private static final Semaphore sWritesInProgress = new Semaphore(0);
    private final String mAuthority;
    private final Context mContext;
    private final Uri mSuggestionsUri;
    private final boolean mTwoLineDisplay;

}
