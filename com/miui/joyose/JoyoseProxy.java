// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.joyose;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public abstract class JoyoseProxy
{

    public JoyoseProxy()
    {
    }

    public static boolean checkSceneWorkState(long l)
    {
        return false;
    }

    public static long getJoyoseSupportSceneList(Context context)
    {
        if(context == null)
            return 0L;
        context = context.getContentResolver();
        long l = 0L;
        context = context.query(Uri.withAppendedPath(CONTENT_URI, "scenemanager"), null, null, null, null);
        long l1 = l;
        if(context != null)
        {
            l1 = l;
            if(context.getCount() == 1)
                do
                {
                    l1 = l;
                    if(!context.moveToNext())
                        break;
                    l = context.getInt(0);
                } while(true);
            context.close();
        }
        return l1;
    }

    public static final String AUTHORITY = "com.xiaomi.joyose";
    public static final Uri CONTENT_URI = Uri.parse("content://com.xiaomi.joyose");

}
