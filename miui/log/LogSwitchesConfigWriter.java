// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.log;

import android.util.Log;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;

// Referenced classes of package miui.log:
//            Utils, AppLogSwitches

public final class LogSwitchesConfigWriter
{

    public LogSwitchesConfigWriter(String s, String s1)
    {
        logSwitchesFolder = s;
        logSwitchesFileName = s1;
        logSwitchesFilePath = (new StringBuilder()).append(s).append("/").append(s1).toString();
    }

    public void write(HashMap hashmap)
    {
        this;
        JVM INSTR monitorenter ;
        Object obj;
        StringBuilder stringbuilder;
        Object obj1;
        obj = null;
        stringbuilder = null;
        obj1 = obj;
        Utils.createLogSwitchesFileIfNotExisted(logSwitchesFolder, logSwitchesFilePath);
        obj1 = obj;
        Object obj2 = JVM INSTR new #47  <Class BufferedWriter>;
        obj1 = obj;
        FileWriter filewriter = JVM INSTR new #49  <Class FileWriter>;
        obj1 = obj;
        filewriter.FileWriter(logSwitchesFilePath);
        obj1 = obj;
        ((BufferedWriter) (obj2)).BufferedWriter(filewriter);
        for(hashmap = hashmap.values().iterator(); hashmap.hasNext(); ((BufferedWriter) (obj2)).newLine())
            ((BufferedWriter) (obj2)).write(((AppLogSwitches)hashmap.next()).toString());

          goto _L1
        obj1;
        hashmap = ((HashMap) (obj2));
        obj2 = obj1;
_L7:
        obj1 = hashmap;
        stringbuilder = JVM INSTR new #22  <Class StringBuilder>;
        obj1 = hashmap;
        stringbuilder.StringBuilder();
        obj1 = hashmap;
        Log.e("LogSwitchesConfigWriter", stringbuilder.append("failed to write ").append(logSwitchesFilePath).toString(), ((Throwable) (obj2)));
        if(hashmap == null)
            break MISSING_BLOCK_LABEL_160;
        try
        {
            hashmap.close();
        }
        // Misplaced declaration of an exception variable
        catch(HashMap hashmap) { }
        this;
        JVM INSTR monitorexit ;
        return;
_L1:
        ((BufferedWriter) (obj2)).flush();
        if(obj2 == null)
            break MISSING_BLOCK_LABEL_160;
        try
        {
            ((BufferedWriter) (obj2)).close();
        }
        // Misplaced declaration of an exception variable
        catch(HashMap hashmap) { }
        break MISSING_BLOCK_LABEL_160;
        hashmap;
_L5:
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_200;
        try
        {
            ((BufferedWriter) (obj1)).close();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj1) { }
        throw hashmap;
        hashmap;
_L3:
        this;
        JVM INSTR monitorexit ;
        throw hashmap;
        hashmap;
        if(true) goto _L3; else goto _L2
_L2:
        hashmap;
        obj1 = obj2;
        if(true) goto _L5; else goto _L4
_L4:
        obj2;
        hashmap = stringbuilder;
        if(true) goto _L7; else goto _L6
_L6:
    }

    protected static final String TAG = "LogSwitchesConfigWriter";
    private final String logSwitchesFileName;
    private final String logSwitchesFilePath;
    private final String logSwitchesFolder;
}
