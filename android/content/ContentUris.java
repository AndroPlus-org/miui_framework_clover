// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.net.Uri;

public class ContentUris
{

    public ContentUris()
    {
    }

    public static android.net.Uri.Builder appendId(android.net.Uri.Builder builder, long l)
    {
        return builder.appendEncodedPath(String.valueOf(l));
    }

    public static long parseId(Uri uri)
    {
        uri = uri.getLastPathSegment();
        long l;
        if(uri == null)
            l = -1L;
        else
            l = Long.parseLong(uri);
        return l;
    }

    public static Uri withAppendedId(Uri uri, long l)
    {
        return appendId(uri.buildUpon(), l).build();
    }
}
