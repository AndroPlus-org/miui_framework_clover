// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.provider;

import android.content.*;
import android.net.Uri;
import android.text.TextUtils;
import java.util.Locale;

// Referenced classes of package android.provider:
//            BaseColumns

public class UserDictionary
{
    public static class Words
        implements BaseColumns
    {

        public static void addWord(Context context, String s, int i, int j)
        {
            if(j != 0 && j != 1)
                return;
            Locale locale;
            if(j == 1)
                locale = Locale.getDefault();
            else
                locale = null;
            addWord(context, s, i, null, locale);
        }

        public static void addWord(Context context, String s, int i, String s1, Locale locale)
        {
            Object obj = null;
            ContentResolver contentresolver = context.getContentResolver();
            if(TextUtils.isEmpty(s))
                return;
            int j = i;
            if(i < 0)
                j = 0;
            i = j;
            if(j > 255)
                i = 255;
            ContentValues contentvalues = new ContentValues(5);
            contentvalues.put("word", s);
            contentvalues.put("frequency", Integer.valueOf(i));
            if(locale == null)
                context = obj;
            else
                context = locale.toString();
            contentvalues.put("locale", context);
            contentvalues.put("appid", Integer.valueOf(0));
            contentvalues.put("shortcut", s1);
            contentresolver.insert(CONTENT_URI, contentvalues);
        }

        public static final String APP_ID = "appid";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.userword";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.userword";
        public static final Uri CONTENT_URI = Uri.parse("content://user_dictionary/words");
        public static final String DEFAULT_SORT_ORDER = "frequency DESC";
        public static final String FREQUENCY = "frequency";
        public static final String LOCALE = "locale";
        public static final int LOCALE_TYPE_ALL = 0;
        public static final int LOCALE_TYPE_CURRENT = 1;
        public static final String SHORTCUT = "shortcut";
        public static final String WORD = "word";
        public static final String _ID = "_id";


        public Words()
        {
        }
    }


    public UserDictionary()
    {
    }

    public static final String AUTHORITY = "user_dictionary";
    public static final Uri CONTENT_URI = Uri.parse("content://user_dictionary");
    private static final int FREQUENCY_MAX = 255;
    private static final int FREQUENCY_MIN = 0;

}
