// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.io;

import android.system.StructStat;
import libcore.io.Libcore;
import libcore.io.Os;

public class FileStat
{

    public FileStat()
    {
    }

    public static long getCreatedTime(String s)
    {
        long l;
        try
        {
            l = Libcore.os.lstat(s).st_ctime;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return 0L;
        }
        return l * 1000L;
    }
}
