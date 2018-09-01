// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.provider;

import android.net.Uri;

public final class TimeZoneRulesDataContract
{
    public static final class Operation
    {

        public static final String COLUMN_DISTRO_MAJOR_VERSION = "distro_major_version";
        public static final String COLUMN_DISTRO_MINOR_VERSION = "distro_minor_version";
        public static final String COLUMN_REVISION = "revision";
        public static final String COLUMN_RULES_VERSION = "rules_version";
        public static final String COLUMN_TYPE = "type";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(TimeZoneRulesDataContract._2D_get0(), "operation");
        public static final String TYPE_INSTALL = "INSTALL";
        public static final String TYPE_NO_OP = "NOOP";
        public static final String TYPE_UNINSTALL = "UNINSTALL";


        private Operation()
        {
        }
    }


    static Uri _2D_get0()
    {
        return AUTHORITY_URI;
    }

    private TimeZoneRulesDataContract()
    {
    }

    public static final String AUTHORITY = "com.android.timezone";
    private static final Uri AUTHORITY_URI = Uri.parse("content://com.android.timezone");

}
