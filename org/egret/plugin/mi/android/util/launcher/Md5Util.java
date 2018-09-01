// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.egret.plugin.mi.android.util.launcher;

import java.io.*;
import java.security.MessageDigest;

public class Md5Util
{

    public Md5Util()
    {
    }

    public static boolean checkMd5(File file, String s)
    {
        boolean flag = false;
        if(file == null || s == null)
            return false;
        file = md5(file);
        if(file != null)
            flag = file.equals(s);
        return flag;
    }

    private static String getMd5String(byte abyte0[])
    {
        char ac[] = new char[16];
        char[] _tmp = ac;
        ac[0] = '0';
        ac[1] = '1';
        ac[2] = '2';
        ac[3] = '3';
        ac[4] = '4';
        ac[5] = '5';
        ac[6] = '6';
        ac[7] = '7';
        ac[8] = '8';
        ac[9] = '9';
        ac[10] = 'a';
        ac[11] = 'b';
        ac[12] = 'c';
        ac[13] = 'd';
        ac[14] = 'e';
        ac[15] = 'f';
        int i = abyte0.length;
        char ac1[] = new char[i * 2];
        for(int j = 0; j < i; j++)
        {
            byte byte0 = abyte0[j];
            ac1[j * 2] = ac[byte0 >>> 4 & 0xf];
            ac1[j * 2 + 1] = ac[byte0 & 0xf];
        }

        return new String(ac1);
    }

    public static String md5(File file)
    {
        Object obj;
        Object obj1;
        Object obj2;
        if(!file.exists())
            return null;
        obj = null;
        obj1 = null;
        obj2 = obj;
        MessageDigest messagedigest = MessageDigest.getInstance("MD5");
        obj2 = obj;
        Object obj3 = JVM INSTR new #62  <Class FileInputStream>;
        obj2 = obj;
        ((FileInputStream) (obj3)).FileInputStream(file);
        file = new byte[1024];
_L3:
        int i = ((FileInputStream) (obj3)).read(file);
        if(i <= 0) goto _L2; else goto _L1
_L1:
        messagedigest.update(file, 0, i);
          goto _L3
        obj2;
        file = ((File) (obj3));
        obj3 = obj2;
_L7:
        obj2 = file;
        ((Exception) (obj3)).printStackTrace();
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
        file = getMd5String(messagedigest.digest());
        if(obj3 != null)
            try
            {
                ((FileInputStream) (obj3)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj2)
            {
                ((IOException) (obj2)).printStackTrace();
            }
        return file;
        file;
_L5:
        if(obj2 != null)
            try
            {
                ((FileInputStream) (obj2)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj2)
            {
                ((IOException) (obj2)).printStackTrace();
            }
        throw file;
        file;
        obj2 = obj3;
        if(true) goto _L5; else goto _L4
_L4:
        obj3;
        file = obj1;
        if(true) goto _L7; else goto _L6
_L6:
    }
}
