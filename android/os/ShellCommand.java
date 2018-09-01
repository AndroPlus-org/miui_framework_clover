// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import com.android.internal.util.FastPrintWriter;
import java.io.*;

// Referenced classes of package android.os:
//            ResultReceiver, Binder, ShellCallback, ParcelFileDescriptor

public abstract class ShellCommand
{

    public ShellCommand()
    {
    }

    public int exec(Binder binder, FileDescriptor filedescriptor, FileDescriptor filedescriptor1, FileDescriptor filedescriptor2, String as[], ShellCallback shellcallback, ResultReceiver resultreceiver)
    {
        int i;
        byte byte0;
        String s;
        if(as != null && as.length > 0)
        {
            s = as[0];
            i = 1;
        } else
        {
            s = null;
            i = 0;
        }
        init(binder, filedescriptor, filedescriptor1, filedescriptor2, as, shellcallback, i);
        mCmd = s;
        mResultReceiver = resultreceiver;
        byte0 = -1;
        i = onCommand(mCmd);
        if(mOutPrintWriter != null)
            mOutPrintWriter.flush();
        if(mErrPrintWriter != null)
            mErrPrintWriter.flush();
        mResultReceiver.send(i, null);
_L1:
        return i;
        binder;
        filedescriptor = getErrPrintWriter();
        filedescriptor.println();
        filedescriptor.println("Exception occurred while executing:");
        binder.printStackTrace(filedescriptor);
        if(mOutPrintWriter != null)
            mOutPrintWriter.flush();
        if(mErrPrintWriter != null)
            mErrPrintWriter.flush();
        mResultReceiver.send(-1, null);
        i = byte0;
          goto _L1
        filedescriptor;
        binder = getErrPrintWriter();
        filedescriptor1 = JVM INSTR new #95  <Class StringBuilder>;
        filedescriptor1.StringBuilder();
        binder.println(filedescriptor1.append("Security exception: ").append(filedescriptor.getMessage()).toString());
        binder.println();
        filedescriptor.printStackTrace(binder);
        if(mOutPrintWriter != null)
            mOutPrintWriter.flush();
        if(mErrPrintWriter != null)
            mErrPrintWriter.flush();
        mResultReceiver.send(-1, null);
        i = byte0;
          goto _L1
        binder;
        if(mOutPrintWriter != null)
            mOutPrintWriter.flush();
        if(mErrPrintWriter != null)
            mErrPrintWriter.flush();
        mResultReceiver.send(-1, null);
        throw binder;
    }

    public InputStream getBufferedInputStream()
    {
        if(mInputStream == null)
            mInputStream = new BufferedInputStream(getRawInputStream());
        return mInputStream;
    }

    public PrintWriter getErrPrintWriter()
    {
        if(mErr == null)
            return getOutPrintWriter();
        if(mErrPrintWriter == null)
            mErrPrintWriter = new FastPrintWriter(getRawErrorStream());
        return mErrPrintWriter;
    }

    public String getNextArg()
    {
        if(mCurArgData != null)
        {
            String s = mCurArgData;
            mCurArgData = null;
            return s;
        }
        if(mArgPos < mArgs.length)
        {
            String as[] = mArgs;
            int i = mArgPos;
            mArgPos = i + 1;
            return as[i];
        } else
        {
            return null;
        }
    }

    public String getNextArgRequired()
    {
        String s = getNextArg();
        if(s == null)
        {
            s = mArgs[mArgPos - 1];
            throw new IllegalArgumentException((new StringBuilder()).append("Argument expected after \"").append(s).append("\"").toString());
        } else
        {
            return s;
        }
    }

    public String getNextOption()
    {
        if(mCurArgData != null)
        {
            String s = mArgs[mArgPos - 1];
            throw new IllegalArgumentException((new StringBuilder()).append("No argument expected after \"").append(s).append("\"").toString());
        }
        if(mArgPos >= mArgs.length)
            return null;
        String s1 = mArgs[mArgPos];
        if(!s1.startsWith("-"))
            return null;
        mArgPos = mArgPos + 1;
        if(s1.equals("--"))
            return null;
        if(s1.length() > 1 && s1.charAt(1) != '-')
        {
            if(s1.length() > 2)
            {
                mCurArgData = s1.substring(2);
                return s1.substring(0, 2);
            } else
            {
                mCurArgData = null;
                return s1;
            }
        } else
        {
            mCurArgData = null;
            return s1;
        }
    }

    public PrintWriter getOutPrintWriter()
    {
        if(mOutPrintWriter == null)
            mOutPrintWriter = new FastPrintWriter(getRawOutputStream());
        return mOutPrintWriter;
    }

    public OutputStream getRawErrorStream()
    {
        if(mFileErr == null)
            mFileErr = new FileOutputStream(mErr);
        return mFileErr;
    }

    public InputStream getRawInputStream()
    {
        if(mFileIn == null)
            mFileIn = new FileInputStream(mIn);
        return mFileIn;
    }

    public OutputStream getRawOutputStream()
    {
        if(mFileOut == null)
            mFileOut = new FileOutputStream(mOut);
        return mFileOut;
    }

    public ShellCallback getShellCallback()
    {
        return mShellCallback;
    }

    public int handleDefaultCommands(String s)
    {
        if("dump".equals(s))
        {
            s = new String[mArgs.length - 1];
            System.arraycopy(mArgs, 1, s, 0, mArgs.length - 1);
            mTarget.doDump(mOut, getOutPrintWriter(), s);
            return 0;
        }
        if(s == null || "help".equals(s) || "-h".equals(s))
            onHelp();
        else
            getOutPrintWriter().println((new StringBuilder()).append("Unknown command: ").append(s).toString());
        return -1;
    }

    public void init(Binder binder, FileDescriptor filedescriptor, FileDescriptor filedescriptor1, FileDescriptor filedescriptor2, String as[], ShellCallback shellcallback, int i)
    {
        mTarget = binder;
        mIn = filedescriptor;
        mOut = filedescriptor1;
        mErr = filedescriptor2;
        mArgs = as;
        mShellCallback = shellcallback;
        mResultReceiver = null;
        mCmd = null;
        mArgPos = i;
        mCurArgData = null;
        mFileIn = null;
        mFileOut = null;
        mFileErr = null;
        mOutPrintWriter = null;
        mErrPrintWriter = null;
        mInputStream = null;
    }

    public abstract int onCommand(String s);

    public abstract void onHelp();

    public ParcelFileDescriptor openOutputFileForSystem(String s)
    {
        ParcelFileDescriptor parcelfiledescriptor = getShellCallback().openOutputFile(s, "u:r:system_server:s0");
        if(parcelfiledescriptor != null)
            return parcelfiledescriptor;
        break MISSING_BLOCK_LABEL_47;
        RuntimeException runtimeexception;
        runtimeexception;
        getErrPrintWriter().println((new StringBuilder()).append("Failure opening file: ").append(runtimeexception.getMessage()).toString());
        getErrPrintWriter().println((new StringBuilder()).append("Error: Unable to open file: ").append(s).toString());
        getErrPrintWriter().println("Consider using a file under /data/local/tmp/");
        return null;
    }

    public String peekNextArg()
    {
        if(mCurArgData != null)
            return mCurArgData;
        if(mArgPos < mArgs.length)
            return mArgs[mArgPos];
        else
            return null;
    }

    static final boolean DEBUG = false;
    static final String TAG = "ShellCommand";
    private int mArgPos;
    private String mArgs[];
    private String mCmd;
    private String mCurArgData;
    private FileDescriptor mErr;
    private FastPrintWriter mErrPrintWriter;
    private FileOutputStream mFileErr;
    private FileInputStream mFileIn;
    private FileOutputStream mFileOut;
    private FileDescriptor mIn;
    private InputStream mInputStream;
    private FileDescriptor mOut;
    private FastPrintWriter mOutPrintWriter;
    private ResultReceiver mResultReceiver;
    private ShellCallback mShellCallback;
    private Binder mTarget;
}
