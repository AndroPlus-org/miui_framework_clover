// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.egret.plugin.mi.android.util.launcher;

import java.io.*;

public class FileUtil
{

    public FileUtil()
    {
    }

    public static boolean Copy(File file, File file1)
    {
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        File file2;
        if(file == null || file1 == null)
            return false;
        obj = null;
        obj1 = null;
        obj2 = null;
        obj3 = null;
        obj4 = null;
        obj5 = obj;
        file2 = obj2;
        Object obj6;
        int i;
        try
        {
            obj6 = JVM INSTR new #18  <Class FileInputStream>;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj6)
        {
            file = obj1;
            file1 = obj4;
            continue; /* Loop/switch isn't completed */
        }
        obj5 = obj;
        file2 = obj2;
        ((FileInputStream) (obj6)).FileInputStream(file);
        file = JVM INSTR new #23  <Class FileOutputStream>;
        file.FileOutputStream(file1);
        file1 = new byte[1024];
_L3:
        i = ((FileInputStream) (obj6)).read(file1, 0, 1024);
        if(i <= 0) goto _L2; else goto _L1
_L1:
        file.write(file1, 0, i);
          goto _L3
        obj5;
        file1 = file;
        file = ((File) (obj6));
        obj6 = obj5;
_L7:
        obj5 = file;
        file2 = file1;
        ((IOException) (obj6)).printStackTrace();
        if(file == null)
            break MISSING_BLOCK_LABEL_121;
        file.close();
        if(file1 != null)
            try
            {
                file1.close();
            }
            // Misplaced declaration of an exception variable
            catch(File file)
            {
                file.printStackTrace();
            }
        return false;
_L2:
        if(obj6 == null)
            break MISSING_BLOCK_LABEL_141;
        ((FileInputStream) (obj6)).close();
        if(file != null)
            try
            {
                file.close();
            }
            // Misplaced declaration of an exception variable
            catch(File file)
            {
                file.printStackTrace();
            }
        return true;
        file;
_L5:
        if(obj5 == null)
            break MISSING_BLOCK_LABEL_178;
        ((FileInputStream) (obj5)).close();
        if(file2 != null)
            try
            {
                file2.close();
            }
            // Misplaced declaration of an exception variable
            catch(File file1)
            {
                file1.printStackTrace();
            }
        throw file;
        file;
        obj5 = obj6;
        file2 = obj3;
        continue; /* Loop/switch isn't completed */
        file1;
        obj5 = obj6;
        file2 = file;
        file = file1;
        if(true) goto _L5; else goto _L4
        file1;
        file = ((File) (obj6));
        obj6 = file1;
        file1 = obj4;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public static boolean CopyToRoot(File file, File file1)
    {
        if(file == null || file1 == null)
            return false;
        else
            return Copy(file, new File(file1, file.getName()));
    }

    public static boolean Move(File file, File file1)
    {
        boolean flag;
        if(file != null && file1 != null)
            flag = file.renameTo(file1);
        else
            flag = false;
        return flag;
    }

    public static boolean MoveToRoot(File file, File file1)
    {
        if(file == null || file1 == null)
            return false;
        else
            return Move(file, new File(file1, file.getName()));
    }

    public static String readFile(File file)
    {
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        obj = null;
        obj1 = null;
        obj2 = null;
        obj3 = null;
        obj4 = null;
        obj5 = obj2;
        obj6 = obj;
        Object obj7;
        try
        {
            obj7 = JVM INSTR new #65  <Class FileReader>;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj3)
        {
            obj7 = obj4;
            file = obj1;
            continue; /* Loop/switch isn't completed */
        }
        obj5 = obj2;
        obj6 = obj;
        ((FileReader) (obj7)).FileReader(file);
        file = JVM INSTR new #68  <Class BufferedReader>;
        file.BufferedReader(((java.io.Reader) (obj7)));
        obj5 = JVM INSTR new #73  <Class StringBuilder>;
        ((StringBuilder) (obj5)).StringBuilder();
_L3:
        obj6 = file.readLine();
        if(obj6 == null) goto _L2; else goto _L1
_L1:
        ((StringBuilder) (obj5)).append(((String) (obj6)));
          goto _L3
        obj3;
        obj5 = file;
        file = ((File) (obj7));
        obj7 = obj5;
_L7:
        obj5 = obj7;
        obj6 = file;
        ((Exception) (obj3)).printStackTrace();
        if(obj7 == null)
            break MISSING_BLOCK_LABEL_111;
        ((BufferedReader) (obj7)).close();
        if(file != null)
            try
            {
                file.close();
            }
            // Misplaced declaration of an exception variable
            catch(File file)
            {
                file.printStackTrace();
            }
        return null;
_L2:
        obj5 = ((StringBuilder) (obj5)).toString();
        if(file == null)
            break MISSING_BLOCK_LABEL_136;
        file.close();
        if(obj7 != null)
            try
            {
                ((FileReader) (obj7)).close();
            }
            // Misplaced declaration of an exception variable
            catch(File file)
            {
                file.printStackTrace();
            }
        return ((String) (obj5));
        file;
_L5:
        if(obj5 == null)
            break MISSING_BLOCK_LABEL_176;
        ((BufferedReader) (obj5)).close();
        if(obj6 != null)
            try
            {
                ((FileReader) (obj6)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj7)
            {
                ((Exception) (obj7)).printStackTrace();
            }
        throw file;
        file;
        obj5 = obj3;
        obj6 = obj7;
        continue; /* Loop/switch isn't completed */
        obj3;
        obj5 = file;
        obj6 = obj7;
        file = ((File) (obj3));
        if(true) goto _L5; else goto _L4
        obj3;
        file = ((File) (obj7));
        obj7 = obj4;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public static boolean writeFile(File file, String s)
    {
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj7;
        obj = null;
        obj1 = null;
        obj2 = null;
        obj3 = null;
        obj4 = null;
        obj5 = obj2;
        obj7 = obj;
        Object obj8 = JVM INSTR new #91  <Class FileWriter>;
        obj5 = obj2;
        obj7 = obj;
        ((FileWriter) (obj8)).FileWriter(file);
        try
        {
            file = JVM INSTR new #94  <Class BufferedWriter>;
            file.BufferedWriter(((java.io.Writer) (obj8)));
        }
        // Misplaced declaration of an exception variable
        catch(File file)
        {
            Object obj6 = obj8;
            s = obj4;
            obj8 = file;
            file = ((File) (obj6));
            continue; /* Loop/switch isn't completed */
        }
        file.write(s);
        if(file == null)
            break MISSING_BLOCK_LABEL_61;
        file.close();
        if(obj8 != null)
            try
            {
                ((FileWriter) (obj8)).close();
            }
            // Misplaced declaration of an exception variable
            catch(File file)
            {
                file.printStackTrace();
            }
        return true;
        obj8;
        file = obj1;
        s = obj4;
_L4:
        obj5 = s;
        obj7 = file;
        ((IOException) (obj8)).printStackTrace();
        if(s == null)
            break MISSING_BLOCK_LABEL_107;
        s.close();
        if(file != null)
            try
            {
                file.close();
            }
            // Misplaced declaration of an exception variable
            catch(File file)
            {
                file.printStackTrace();
            }
        return false;
        file;
_L2:
        if(obj5 == null)
            break MISSING_BLOCK_LABEL_136;
        ((BufferedWriter) (obj5)).close();
        if(obj7 != null)
            try
            {
                ((FileWriter) (obj7)).close();
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                s.printStackTrace();
            }
        throw file;
        file;
        obj5 = obj3;
        obj7 = obj8;
        continue; /* Loop/switch isn't completed */
        s;
        obj5 = file;
        obj7 = obj8;
        file = s;
        if(true) goto _L2; else goto _L1
_L1:
        break MISSING_BLOCK_LABEL_48;
        IOException ioexception;
        ioexception;
        s = file;
        file = ((File) (obj8));
        obj8 = ioexception;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static final int BUFFER_SIZE = 1024;
}
