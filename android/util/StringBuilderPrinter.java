// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;


// Referenced classes of package android.util:
//            Printer

public class StringBuilderPrinter
    implements Printer
{

    public StringBuilderPrinter(StringBuilder stringbuilder)
    {
        mBuilder = stringbuilder;
    }

    public void println(String s)
    {
        mBuilder.append(s);
        int i = s.length();
        if(i <= 0 || s.charAt(i - 1) != '\n')
            mBuilder.append('\n');
    }

    private final StringBuilder mBuilder;
}
