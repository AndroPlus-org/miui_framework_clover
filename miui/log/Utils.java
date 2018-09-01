// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.log;

import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

final class Utils
{

    Utils()
    {
    }

    private static void createConfigFolder(File file)
    {
        Object obj = new ArrayList();
        for(; file != null && !file.exists(); file = file.getParentFile())
            ((ArrayList) (obj)).add(0, file);

        for(file = ((Iterable) (obj)).iterator(); file.hasNext(); ((File) (obj)).setExecutable(true, false))
        {
            obj = (File)file.next();
            ((File) (obj)).mkdir();
            ((File) (obj)).setReadable(true, false);
            ((File) (obj)).setWritable(true, true);
        }

    }

    static void createLogSwitchesFileIfNotExisted(String s, String s1)
    {
        s = new File(s);
        boolean flag = false;
        if(!s.exists())
            flag = true;
        else
        if(s.isFile())
        {
            s.delete();
            flag = true;
        }
        if(flag)
            createConfigFolder(s);
        s = new File(s1);
        if(s.exists())
            break MISSING_BLOCK_LABEL_69;
        s.createNewFile();
        s.setWritable(true, true);
        s.setReadable(true, false);
        s.setExecutable(false);
_L1:
        return;
        s;
        Log.e("MiuiLogUtils", (new StringBuilder()).append("failed to create file ").append(s1).toString(), s);
          goto _L1
    }

    private static final String TAG = "MiuiLogUtils";
}
