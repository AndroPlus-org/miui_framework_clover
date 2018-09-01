// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;

import android.util.Slog;
import java.io.PrintWriter;
import java.util.ArrayList;

public class LocalLog
{

    public LocalLog(String s)
    {
        mTag = s;
    }

    public boolean dump(PrintWriter printwriter, String s, String s1)
    {
        ArrayList arraylist = mLines;
        arraylist;
        JVM INSTR monitorenter ;
        int i = mLines.size();
        if(i > 0)
            break MISSING_BLOCK_LABEL_28;
        arraylist;
        JVM INSTR monitorexit ;
        return false;
        if(s == null)
            break MISSING_BLOCK_LABEL_37;
        printwriter.println(s);
        i = 0;
_L2:
        if(i >= mLines.size())
            break; /* Loop/switch isn't completed */
        if(s1 == null)
            break MISSING_BLOCK_LABEL_61;
        printwriter.print(s1);
        printwriter.println((String)mLines.get(i));
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        return true;
        printwriter;
        throw printwriter;
    }

    public void w(String s)
    {
        ArrayList arraylist = mLines;
        arraylist;
        JVM INSTR monitorenter ;
        Slog.w(mTag, s);
        if(mLines.size() >= 20)
            mLines.remove(0);
        mLines.add(s);
        arraylist;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s;
    }

    private final ArrayList mLines = new ArrayList(20);
    private final int mMaxLines = 20;
    private final String mTag;
}
