// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.os.ShellCommand;
import java.io.PrintStream;

public abstract class BaseCommand
{

    public BaseCommand()
    {
    }

    public String[] getRawArgs()
    {
        return mRawArgs;
    }

    public String nextArg()
    {
        return mArgs.getNextArg();
    }

    public String nextArgRequired()
    {
        return mArgs.getNextArgRequired();
    }

    public String nextOption()
    {
        return mArgs.getNextOption();
    }

    public abstract void onRun()
        throws Exception;

    public abstract void onShowUsage(PrintStream printstream);

    public void run(String as[])
    {
        if(as.length < 1)
        {
            onShowUsage(System.out);
            return;
        }
        mRawArgs = as;
        mArgs.init(null, null, null, null, as, null, 0);
        onRun();
_L1:
        return;
        as;
        as.printStackTrace(System.err);
        System.exit(1);
          goto _L1
        as;
        onShowUsage(System.err);
        System.err.println();
        System.err.println((new StringBuilder()).append("Error: ").append(as.getMessage()).toString());
          goto _L1
    }

    public void showError(String s)
    {
        onShowUsage(System.err);
        System.err.println();
        System.err.println(s);
    }

    public void showUsage()
    {
        onShowUsage(System.err);
    }

    public static final String FATAL_ERROR_CODE = "Error type 1";
    public static final String NO_CLASS_ERROR_CODE = "Error type 3";
    public static final String NO_SYSTEM_ERROR_CODE = "Error type 2";
    protected final ShellCommand mArgs = new ShellCommand() {

        public int onCommand(String s)
        {
            return 0;
        }

        public void onHelp()
        {
        }

        final BaseCommand this$0;

            
            {
                this$0 = BaseCommand.this;
                super();
            }
    }
;
    private String mRawArgs[];
}
