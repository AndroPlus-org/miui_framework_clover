// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.provider;

import android.net.Uri;

public class KeyguardNotification
{

    public KeyguardNotification()
    {
    }

    public static final String AUTHORITY = "keyguard.notification";
    public static final String BASE_URI_STRING = "content://keyguard.notification";
    public static final String CONTENT = "content";
    public static final int CONTENT_INDEX = 3;
    public static final String ICON = "icon";
    public static final int ICON_INDEX = 1;
    public static final String ID = "_id";
    public static final int ID_INDEX = 0;
    public static final String INFO = "info";
    public static final int INFO_INDEX = 5;
    public static final String KEY = "key";
    public static final int KEY_INDEX = 7;
    public static final String PKG = "pkg";
    public static final int PKG_INDEX = 8;
    public static final String PROJECTION[] = {
        "_id", "icon", "title", "content", "time", "info", "subtext", "key", "pkg", "user_id"
    };
    public static final String SUBTEXT = "subtext";
    public static final int SUBTEXT_INDEX = 6;
    public static final String TABLE = "notifications";
    public static final String TIME = "time";
    public static final int TIME_INDEX = 4;
    public static final String TITLE = "title";
    public static final int TITLE_INDEX = 2;
    public static final Uri URI = Uri.parse("content://keyguard.notification/notifications");
    public static final String USERID = "user_id";
    public static final int USERID_INDEX = 9;

}
