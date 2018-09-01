// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import java.io.PrintStream;

// Referenced classes of package android.util:
//            Printer

public class PrintStreamPrinter
    implements Printer
{

    public PrintStreamPrinter(PrintStream printstream)
    {
        mPS = printstream;
    }

    public void println(String s)
    {
        mPS.println(s);
    }

    private final PrintStream mPS;
}
