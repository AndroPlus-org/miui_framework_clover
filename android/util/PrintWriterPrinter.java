// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import java.io.PrintWriter;

// Referenced classes of package android.util:
//            Printer

public class PrintWriterPrinter
    implements Printer
{

    public PrintWriterPrinter(PrintWriter printwriter)
    {
        mPW = printwriter;
    }

    public void println(String s)
    {
        mPW.println(s);
    }

    private final PrintWriter mPW;
}
