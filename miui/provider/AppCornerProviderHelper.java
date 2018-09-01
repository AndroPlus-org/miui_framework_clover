// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.provider;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;

// Referenced classes of package miui.provider:
//            AppCornerProviderConstants

public class AppCornerProviderHelper
{

    public AppCornerProviderHelper()
    {
    }

    public static boolean isAllowed(Context context, String s)
    {
        ContentResolver contentresolver;
        contentresolver = context.getContentResolver();
        context = null;
        s = contentresolver.query(AppCornerProviderConstants.URI, new String[] {
            s
        }, null, null, null);
        if(s == null) goto _L2; else goto _L1
_L1:
        context = s;
        int i = s.getCount();
        if(i >= 1) goto _L3; else goto _L2
_L2:
        boolean flag = true;
_L5:
        if(s != null)
            s.close();
        return flag;
_L3:
        context = s;
        if(s.moveToFirst() ^ true) goto _L2; else goto _L4
_L4:
        context = s;
        i = s.getInt(0);
        if(i == 1)
            flag = true;
        else
            flag = false;
          goto _L5
        s;
        if(context != null)
            context.close();
        throw s;
    }
}
