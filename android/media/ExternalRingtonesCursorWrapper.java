// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.content.ContentProvider;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.net.Uri;

public class ExternalRingtonesCursorWrapper extends CursorWrapper
{

    public ExternalRingtonesCursorWrapper(Cursor cursor, int i)
    {
        super(cursor);
        mUserId = i;
    }

    public String getString(int i)
    {
        String s = super.getString(i);
        String s1 = s;
        if(i == 2)
            s1 = ContentProvider.maybeAddUserId(Uri.parse(s), mUserId).toString();
        return s1;
    }

    private int mUserId;
}
