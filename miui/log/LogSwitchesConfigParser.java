// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.log;

import android.util.Log;
import java.io.*;
import java.util.*;

// Referenced classes of package miui.log:
//            AppLogSwitches

public final class LogSwitchesConfigParser
{

    public LogSwitchesConfigParser()
    {
    }

    public static HashMap parseLogSwitchesConfig(String s)
    {
        Iterator iterator;
        Object obj;
        Object obj1;
        Object obj2;
        if(!(new File(s)).exists())
            return new HashMap();
        iterator = null;
        obj = null;
        obj1 = null;
        obj2 = obj;
        ArrayList arraylist = JVM INSTR new #33  <Class ArrayList>;
        obj2 = obj;
        arraylist.ArrayList();
        obj2 = obj;
        Object obj3 = JVM INSTR new #36  <Class BufferedReader>;
        obj2 = obj;
        FileReader filereader = JVM INSTR new #38  <Class FileReader>;
        obj2 = obj;
        filereader.FileReader(s);
        obj2 = obj;
        ((BufferedReader) (obj3)).BufferedReader(filereader);
_L9:
        obj2 = ((BufferedReader) (obj3)).readLine();
        if(obj2 != null) goto _L2; else goto _L1
_L1:
        obj2 = JVM INSTR new #30  <Class HashMap>;
        ((HashMap) (obj2)).HashMap();
        iterator = arraylist.iterator();
_L6:
        if(!iterator.hasNext()) goto _L4; else goto _L3
_L3:
        obj = AppLogSwitches.parseAppLogSwitches((String)iterator.next());
        if(obj == null) goto _L6; else goto _L5
_L5:
        obj1 = (AppLogSwitches)((HashMap) (obj2)).get(((AppLogSwitches) (obj)).uniqueName);
        if(obj1 == null) goto _L8; else goto _L7
_L7:
        ((AppLogSwitches) (obj1)).merge(((AppLogSwitches) (obj)));
          goto _L6
        obj;
_L12:
        obj2 = obj3;
        obj1 = JVM INSTR new #82  <Class StringBuilder>;
        obj2 = obj3;
        ((StringBuilder) (obj1)).StringBuilder();
        obj2 = obj3;
        Log.e("LogSwitchesConfigParser", ((StringBuilder) (obj1)).append("cannot found ").append(s).toString(), ((Throwable) (obj)));
        obj2 = obj3;
        s = new HashMap();
        IOException ioexception;
        if(obj3 != null)
            try
            {
                ((BufferedReader) (obj3)).close();
            }
            catch(IOException ioexception1) { }
        return s;
_L2:
        arraylist.add(((String) (obj2)).replace("\r", "").replace("\n", ""));
          goto _L9
        obj;
_L13:
        obj2 = obj3;
        obj1 = JVM INSTR new #82  <Class StringBuilder>;
        obj2 = obj3;
        ((StringBuilder) (obj1)).StringBuilder();
        obj2 = obj3;
        Log.e("LogSwitchesConfigParser", ((StringBuilder) (obj1)).append("failed to read ").append(s).toString(), ((Throwable) (obj)));
        obj2 = obj3;
        s = new HashMap();
        if(obj3 != null)
            try
            {
                ((BufferedReader) (obj3)).close();
            }
            // Misplaced declaration of an exception variable
            catch(IOException ioexception) { }
        return s;
_L8:
        ((HashMap) (obj2)).put(((AppLogSwitches) (obj)).uniqueName, obj);
          goto _L6
        s;
        obj2 = obj3;
_L11:
        if(obj2 != null)
            try
            {
                ((BufferedReader) (obj2)).close();
            }
            catch(IOException ioexception2) { }
        throw s;
_L4:
        if(obj3 != null)
            try
            {
                ((BufferedReader) (obj3)).close();
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
        return ((HashMap) (obj2));
        s;
        if(true) goto _L11; else goto _L10
_L10:
        obj;
        obj3 = obj1;
          goto _L12
        obj;
        obj3 = iterator;
          goto _L13
    }

    private static final String TAG = "LogSwitchesConfigParser";
}
