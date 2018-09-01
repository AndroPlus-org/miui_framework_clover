// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.os.*;
import android.util.Slog;
import java.io.*;

public final class TransferPipe
    implements Runnable, Closeable
{
    static interface Caller
    {

        public abstract void go(IInterface iinterface, FileDescriptor filedescriptor, String s, String as[])
            throws RemoteException;
    }


    public TransferPipe()
        throws IOException
    {
        this(null);
    }

    public TransferPipe(String s)
        throws IOException
    {
        mThread = new Thread(this, "TransferPipe");
        mFds = ParcelFileDescriptor.createPipe();
        mBufferPrefix = s;
    }

    public static void dumpAsync(IBinder ibinder, FileDescriptor filedescriptor, String as[])
        throws IOException, RemoteException
    {
        goDump(ibinder, filedescriptor, as);
    }

    static void go(Caller caller, IInterface iinterface, FileDescriptor filedescriptor, String s, String as[])
        throws IOException, RemoteException
    {
        go(caller, iinterface, filedescriptor, s, as, 5000L);
    }

    static void go(Caller caller, IInterface iinterface, FileDescriptor filedescriptor, String s, String as[], long l)
        throws IOException, RemoteException
    {
        Object obj;
        Object obj1;
        obj = null;
        obj1 = null;
        if(!(iinterface.asBinder() instanceof Binder))
            break MISSING_BLOCK_LABEL_34;
        caller.go(iinterface, filedescriptor, s, as);
_L2:
        return;
        caller;
        if(true) goto _L2; else goto _L1
_L1:
        Object obj2;
        Object obj3;
        obj2 = null;
        obj3 = null;
        TransferPipe transferpipe;
        transferpipe = JVM INSTR new #2   <Class TransferPipe>;
        transferpipe.TransferPipe();
        caller.go(iinterface, transferpipe.getWriteFd().getFileDescriptor(), s, as);
        transferpipe.go(filedescriptor, l);
        caller = obj1;
        if(transferpipe == null)
            break MISSING_BLOCK_LABEL_92;
        transferpipe.close();
        caller = obj1;
_L3:
        if(caller != null)
            throw caller;
        else
            return;
        caller;
          goto _L3
        caller;
        iinterface = obj3;
_L8:
        throw caller;
        s;
        filedescriptor = caller;
        caller = s;
_L6:
        s = filedescriptor;
        if(iinterface == null)
            break MISSING_BLOCK_LABEL_125;
        iinterface.close();
        s = filedescriptor;
_L4:
        if(s != null)
            throw s;
        else
            throw caller;
        iinterface;
        if(filedescriptor == null)
        {
            s = iinterface;
        } else
        {
            s = filedescriptor;
            if(filedescriptor != iinterface)
            {
                filedescriptor.addSuppressed(iinterface);
                s = filedescriptor;
            }
        }
          goto _L4
        caller;
        iinterface = obj2;
        filedescriptor = obj;
        continue; /* Loop/switch isn't completed */
        caller;
        iinterface = transferpipe;
        filedescriptor = obj;
        if(true) goto _L6; else goto _L5
_L5:
        caller;
        iinterface = transferpipe;
        if(true) goto _L8; else goto _L7
_L7:
    }

    static void goDump(IBinder ibinder, FileDescriptor filedescriptor, String as[])
        throws IOException, RemoteException
    {
        goDump(ibinder, filedescriptor, as, 5000L);
    }

    static void goDump(IBinder ibinder, FileDescriptor filedescriptor, String as[], long l)
        throws IOException, RemoteException
    {
        Object obj;
        Object obj1;
        obj = null;
        obj1 = null;
        if(!(ibinder instanceof Binder))
            break MISSING_BLOCK_LABEL_26;
        ibinder.dump(filedescriptor, as);
_L2:
        return;
        ibinder;
        if(true) goto _L2; else goto _L1
_L1:
        Object obj2;
        Object obj3;
        obj2 = null;
        obj3 = null;
        Object obj4;
        obj4 = JVM INSTR new #2   <Class TransferPipe>;
        ((TransferPipe) (obj4)).TransferPipe();
        ibinder.dumpAsync(((TransferPipe) (obj4)).getWriteFd().getFileDescriptor(), as);
        ((TransferPipe) (obj4)).go(filedescriptor, l);
        ibinder = obj1;
        if(obj4 == null)
            break MISSING_BLOCK_LABEL_80;
        ((TransferPipe) (obj4)).close();
        ibinder = obj1;
_L3:
        if(ibinder != null)
            throw ibinder;
        else
            return;
        ibinder;
          goto _L3
        ibinder;
        filedescriptor = obj3;
_L8:
        throw ibinder;
        obj4;
        as = ibinder;
        ibinder = ((IBinder) (obj4));
_L6:
        obj4 = as;
        if(filedescriptor == null)
            break MISSING_BLOCK_LABEL_117;
        filedescriptor.close();
        obj4 = as;
_L4:
        if(obj4 != null)
            throw obj4;
        else
            throw ibinder;
        filedescriptor;
        if(as == null)
        {
            obj4 = filedescriptor;
        } else
        {
            obj4 = as;
            if(as != filedescriptor)
            {
                as.addSuppressed(filedescriptor);
                obj4 = as;
            }
        }
          goto _L4
        ibinder;
        filedescriptor = obj2;
        as = obj;
        continue; /* Loop/switch isn't completed */
        ibinder;
        filedescriptor = ((FileDescriptor) (obj4));
        as = obj;
        if(true) goto _L6; else goto _L5
_L5:
        ibinder;
        filedescriptor = ((FileDescriptor) (obj4));
        if(true) goto _L8; else goto _L7
_L7:
    }

    public void close()
    {
        kill();
    }

    void closeFd(int i)
    {
        if(mFds[i] != null)
        {
            try
            {
                mFds[i].close();
            }
            catch(IOException ioexception) { }
            mFds[i] = null;
        }
    }

    ParcelFileDescriptor getReadFd()
    {
        return mFds[0];
    }

    public ParcelFileDescriptor getWriteFd()
    {
        return mFds[1];
    }

    public void go(FileDescriptor filedescriptor)
        throws IOException
    {
        go(filedescriptor, 5000L);
    }

    public void go(FileDescriptor filedescriptor, long l)
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        mOutFd = filedescriptor;
        mEndTime = SystemClock.uptimeMillis() + l;
        closeFd(1);
        mThread.start();
_L1:
        if(mFailure != null || !(mComplete ^ true))
            break MISSING_BLOCK_LABEL_102;
        l = mEndTime - SystemClock.uptimeMillis();
        if(l > 0L)
            break MISSING_BLOCK_LABEL_90;
        mThread.interrupt();
        filedescriptor = JVM INSTR new #36  <Class IOException>;
        filedescriptor.IOException("Timeout");
        throw filedescriptor;
        filedescriptor;
        this;
        JVM INSTR monitorexit ;
        throw filedescriptor;
        filedescriptor;
        kill();
        throw filedescriptor;
        try
        {
            wait(l);
        }
        // Misplaced declaration of an exception variable
        catch(FileDescriptor filedescriptor) { }
          goto _L1
        if(mFailure != null)
        {
            filedescriptor = JVM INSTR new #36  <Class IOException>;
            filedescriptor.IOException(mFailure);
            throw filedescriptor;
        }
        this;
        JVM INSTR monitorexit ;
        kill();
        return;
    }

    public void kill()
    {
        this;
        JVM INSTR monitorenter ;
        closeFd(0);
        closeFd(1);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void run()
    {
        byte abyte0[] = new byte[1024];
        this;
        JVM INSTR monitorenter ;
        Object obj = getReadFd();
        if(obj != null)
            break MISSING_BLOCK_LABEL_28;
        Slog.w("TransferPipe", "Pipe has been closed...");
        this;
        JVM INSTR monitorexit ;
        return;
        FileInputStream fileinputstream;
        FileOutputStream fileoutputstream;
        fileinputstream = JVM INSTR new #167 <Class FileInputStream>;
        fileinputstream.FileInputStream(((ParcelFileDescriptor) (obj)).getFileDescriptor());
        fileoutputstream = new FileOutputStream(mOutFd);
        this;
        JVM INSTR monitorexit ;
        int i;
        obj = null;
        boolean flag = true;
        i = ((flag) ? 1 : 0);
        if(mBufferPrefix != null)
        {
            obj = mBufferPrefix.getBytes();
            i = ((flag) ? 1 : 0);
        }
_L5:
        int j = fileinputstream.read(abyte0);
        if(j <= 0)
            break; /* Loop/switch isn't completed */
        if(obj != null)
            break MISSING_BLOCK_LABEL_134;
        try
        {
            fileoutputstream.write(abyte0, 0, j);
            continue; /* Loop/switch isn't completed */
        }
        // Misplaced declaration of an exception variable
        catch(Object obj) { }
        this;
        JVM INSTR monitorenter ;
        mFailure = ((IOException) (obj)).toString();
        notifyAll();
        this;
        JVM INSTR monitorexit ;
        return;
        obj;
        throw obj;
        boolean flag1;
        int k;
        k = 0;
        boolean flag2 = false;
        flag1 = i;
        i = ((flag2) ? 1 : 0);
_L3:
        if(i >= j) goto _L2; else goto _L1
_L1:
        boolean flag3;
        int l;
        int i1;
        l = i;
        flag3 = flag1;
        i1 = k;
        if(abyte0[i] == 10)
            break MISSING_BLOCK_LABEL_278;
        if(i <= k)
            break MISSING_BLOCK_LABEL_196;
        fileoutputstream.write(abyte0, k, i - k);
        k = i;
        i1 = i;
        flag3 = flag1;
        if(!flag1)
            break MISSING_BLOCK_LABEL_226;
        fileoutputstream.write(((byte []) (obj)));
        flag3 = false;
        i1 = i;
        do
        {
            i = i1 + 1;
            if(i >= j)
                break;
            i1 = i;
        } while(abyte0[i] != 10);
        l = i;
        i1 = k;
        if(i < j)
        {
            flag3 = true;
            i1 = k;
            l = i;
        }
        i = l + 1;
        flag1 = flag3;
        k = i1;
          goto _L3
_L2:
        i = ((flag1) ? 1 : 0);
        if(j <= k)
            continue; /* Loop/switch isn't completed */
        fileoutputstream.write(abyte0, k, j - k);
        i = ((flag1) ? 1 : 0);
        if(true) goto _L5; else goto _L4
_L4:
        mThread.isInterrupted();
        this;
        JVM INSTR monitorenter ;
        mComplete = true;
        notifyAll();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
        exception;
        throw exception;
    }

    public void setBufferPrefix(String s)
    {
        mBufferPrefix = s;
    }

    static final boolean DEBUG = false;
    static final long DEFAULT_TIMEOUT = 5000L;
    static final String TAG = "TransferPipe";
    String mBufferPrefix;
    boolean mComplete;
    long mEndTime;
    String mFailure;
    final ParcelFileDescriptor mFds[];
    FileDescriptor mOutFd;
    final Thread mThread;
}
