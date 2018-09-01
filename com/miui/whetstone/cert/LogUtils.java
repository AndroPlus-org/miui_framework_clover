// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone.cert;


public class LogUtils
{

    public LogUtils()
    {
    }

    public static void switchData(int i, byte abyte0[])
    {
        if(abyte0 == null) goto _L2; else goto _L1
_L1:
        i;
        JVM INSTR tableswitch 1 1: default 24
    //                   1 25;
           goto _L2 _L3
_L2:
        return;
_L3:
        i = 0;
        while(i < abyte0.length - 1) 
        {
            int j = abyte0[i];
            abyte0[i] = abyte0[i + 1];
            abyte0[i + 1] = (byte)j;
            i += 2;
        }
        if(true) goto _L2; else goto _L4
_L4:
    }

    public static final int LOG_TYPE_IP = 1;
}
