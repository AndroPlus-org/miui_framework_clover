// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.util.ExceptionUtils;
import android.util.Log;
import com.android.internal.util.FastPrintWriter;
import java.io.*;
import libcore.io.IoUtils;

// Referenced classes of package android.os:
//            IBinder, RemoteException, BinderProxy, Parcel, 
//            Trace, StrictMode, UserHandle, TransactionTracker, 
//            IInterface, ResultReceiver, ParcelFileDescriptor, ShellCallback

public class Binder
    implements IBinder
{

    public Binder()
    {
        init();
    }

    public static IBinder allowBlocking(IBinder ibinder)
    {
        if(!(ibinder instanceof BinderProxy)) goto _L2; else goto _L1
_L1:
        ((BinderProxy)ibinder).mWarnOnBlocking = false;
_L4:
        return ibinder;
_L2:
        if(ibinder != null)
            try
            {
                if(ibinder.queryLocalInterface(ibinder.getInterfaceDescriptor()) == null)
                {
                    StringBuilder stringbuilder = JVM INSTR new #65  <Class StringBuilder>;
                    stringbuilder.StringBuilder();
                    Log.w("Binder", stringbuilder.append("Unable to allow blocking on interface ").append(ibinder).toString());
                }
            }
            catch(RemoteException remoteexception) { }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static final native void blockUntilThreadAvailable();

    static void checkParcel(IBinder ibinder, int i, Parcel parcel, String s)
    {
    }

    public static final native long clearCallingIdentity();

    public static void copyAllowBlocking(IBinder ibinder, IBinder ibinder1)
    {
        if((ibinder instanceof BinderProxy) && (ibinder1 instanceof BinderProxy))
            ((BinderProxy)ibinder1).mWarnOnBlocking = ((BinderProxy)ibinder).mWarnOnBlocking;
    }

    private final native void destroyBinder();

    public static void disableTracing()
    {
        sTracingEnabled = false;
    }

    public static void enableTracing()
    {
        sTracingEnabled = true;
    }

    private boolean execTransact(int i, long l, long l1, int j)
    {
        Parcel parcel;
        Object obj;
        boolean flag;
        parcel = Parcel.obtain(l);
        obj = Parcel.obtain(l1);
        flag = isTracingEnabled();
        if(!flag)
            break MISSING_BLOCK_LABEL_61;
        StringBuilder stringbuilder = JVM INSTR new #65  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Trace.traceBegin(1L, stringbuilder.append(getClass().getName()).append(":").append(i).toString());
        boolean flag1 = onTransact(i, parcel, ((Parcel) (obj)), j);
        boolean flag3;
        flag3 = flag1;
        if(flag)
        {
            Trace.traceEnd(1L);
            flag3 = flag1;
        }
_L2:
        checkParcel(this, i, ((Parcel) (obj)), "Unreasonably large binder reply buffer");
        ((Parcel) (obj)).recycle();
        parcel.recycle();
        StrictMode.clearGatheredViolations();
        return flag3;
        Object obj1;
        obj1;
        if(LOG_RUNTIME_EXCEPTION)
            Log.w("Binder", "Caught a RuntimeException from the binder stub implementation.", ((Throwable) (obj1)));
        if((j & 1) == 0)
            break MISSING_BLOCK_LABEL_209;
        if(!(obj1 instanceof RemoteException))
            break; /* Loop/switch isn't completed */
        Log.w("Binder", "Binder call failed.", ((Throwable) (obj1)));
_L3:
        boolean flag2 = true;
        flag3 = flag2;
        if(flag)
        {
            Trace.traceEnd(1L);
            flag3 = flag2;
        }
        if(true) goto _L2; else goto _L1
_L1:
        Log.w("Binder", "Caught a RuntimeException from the binder stub implementation.", ((Throwable) (obj1)));
          goto _L3
        obj;
        if(flag)
            Trace.traceEnd(1L);
        throw obj;
        ((Parcel) (obj)).setDataPosition(0);
        ((Parcel) (obj)).writeException(((Exception) (obj1)));
          goto _L3
    }

    public static final native void flushPendingCommands();

    public static final native int getCallingPid();

    public static final native int getCallingUid();

    public static final UserHandle getCallingUserHandle()
    {
        return UserHandle.of(UserHandle.getUserId(getCallingUid()));
    }

    public static final native int getThreadStrictModePolicy();

    public static TransactionTracker getTransactionTracker()
    {
        android/os/Binder;
        JVM INSTR monitorenter ;
        TransactionTracker transactiontracker1;
        if(sTransactionTracker == null)
        {
            TransactionTracker transactiontracker = JVM INSTR new #186 <Class TransactionTracker>;
            transactiontracker.TransactionTracker();
            sTransactionTracker = transactiontracker;
        }
        transactiontracker1 = sTransactionTracker;
        android/os/Binder;
        JVM INSTR monitorexit ;
        return transactiontracker1;
        Exception exception;
        exception;
        throw exception;
    }

    private final native void init();

    public static final boolean isProxy(IInterface iinterface)
    {
        boolean flag;
        if(iinterface.asBinder() != iinterface)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean isTracingEnabled()
    {
        return sTracingEnabled;
    }

    public static final native void joinThreadPool();

    public static final native void restoreCallingIdentity(long l);

    public static void setDumpDisabled(String s)
    {
        sDumpDisabled = s;
    }

    public static final native void setThreadStrictModePolicy(int i);

    public static void setWarnOnBlocking(boolean flag)
    {
        sWarnOnBlocking = flag;
    }

    public static final Object withCleanCallingIdentity(com.android.internal.util.FunctionalUtils.ThrowingSupplier throwingsupplier)
    {
        long l = clearCallingIdentity();
        try
        {
            throwingsupplier = ((com.android.internal.util.FunctionalUtils.ThrowingSupplier) (throwingsupplier.get()));
        }
        // Misplaced declaration of an exception variable
        catch(com.android.internal.util.FunctionalUtils.ThrowingSupplier throwingsupplier)
        {
            restoreCallingIdentity(l);
            if(throwingsupplier != null)
                throw ExceptionUtils.propagate(throwingsupplier);
            else
                return null;
        }
        restoreCallingIdentity(l);
        return throwingsupplier;
        throwingsupplier;
        restoreCallingIdentity(l);
        throw throwingsupplier;
    }

    public static final void withCleanCallingIdentity(com.android.internal.util.FunctionalUtils.ThrowingRunnable throwingrunnable)
    {
        long l = clearCallingIdentity();
        throwingrunnable.run();
        restoreCallingIdentity(l);
_L2:
        return;
        throwingrunnable;
        restoreCallingIdentity(l);
        if(throwingrunnable == null) goto _L2; else goto _L1
_L1:
        throw ExceptionUtils.propagate(throwingrunnable);
        throwingrunnable;
        restoreCallingIdentity(l);
        throw throwingrunnable;
    }

    public void attachInterface(IInterface iinterface, String s)
    {
        mOwner = iinterface;
        mDescriptor = s;
    }

    void doDump(FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        if(sDumpDisabled != null)
            break MISSING_BLOCK_LABEL_63;
        dump(filedescriptor, printwriter, as);
_L1:
        return;
        filedescriptor;
        printwriter.println();
        printwriter.println("Exception occurred while dumping:");
        filedescriptor.printStackTrace(printwriter);
          goto _L1
        filedescriptor;
        printwriter.println((new StringBuilder()).append("Security exception: ").append(filedescriptor.getMessage()).toString());
        throw filedescriptor;
        printwriter.println(sDumpDisabled);
          goto _L1
    }

    protected void dump(FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
    }

    public void dump(FileDescriptor filedescriptor, String as[])
    {
        FastPrintWriter fastprintwriter = new FastPrintWriter(new FileOutputStream(filedescriptor));
        doDump(filedescriptor, fastprintwriter, as);
        fastprintwriter.flush();
        return;
        filedescriptor;
        fastprintwriter.flush();
        throw filedescriptor;
    }

    public void dumpAsync(FileDescriptor filedescriptor, String as[])
    {
        (new Thread(as) {

            public void run()
            {
                dump(fd, pw, args);
                pw.flush();
                return;
                Exception exception;
                exception;
                pw.flush();
                throw exception;
            }

            final Binder this$0;
            final String val$args[];
            final FileDescriptor val$fd;
            final PrintWriter val$pw;

            
            {
                this$0 = Binder.this;
                pw = printwriter;
                fd = filedescriptor;
                args = as;
                super(final_s);
            }
        }
).start();
    }

    protected void finalize()
        throws Throwable
    {
        destroyBinder();
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public String getInterfaceDescriptor()
    {
        return mDescriptor;
    }

    public boolean isBinderAlive()
    {
        return true;
    }

    public void linkToDeath(IBinder.DeathRecipient deathrecipient, int i)
    {
    }

    public void onShellCommand(FileDescriptor filedescriptor, FileDescriptor filedescriptor1, FileDescriptor filedescriptor2, String as[], ShellCallback shellcallback, ResultReceiver resultreceiver)
        throws RemoteException
    {
        if(filedescriptor2 == null)
            filedescriptor2 = filedescriptor1;
        filedescriptor = new FastPrintWriter(new FileOutputStream(filedescriptor2));
        filedescriptor.println("No shell command implementation.");
        filedescriptor.flush();
        resultreceiver.send(0, null);
    }

    protected boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
        throws RemoteException
    {
        Object obj;
        if(i == 0x5f4e5446)
        {
            parcel1.writeString(getInterfaceDescriptor());
            return true;
        }
        if(i != 0x5f444d50)
            break MISSING_BLOCK_LABEL_79;
        obj = parcel.readFileDescriptor();
        parcel = parcel.readStringArray();
        if(obj == null)
            break MISSING_BLOCK_LABEL_55;
        dump(((ParcelFileDescriptor) (obj)).getFileDescriptor(), parcel);
        IoUtils.closeQuietly(((AutoCloseable) (obj)));
        if(parcel1 != null)
            parcel1.writeNoException();
        else
            StrictMode.clearGatheredViolations();
        return true;
        parcel;
        IoUtils.closeQuietly(((AutoCloseable) (obj)));
        throw parcel;
        if(i != 0x5f434d44) goto _L2; else goto _L1
_L1:
        ParcelFileDescriptor parcelfiledescriptor;
        ParcelFileDescriptor parcelfiledescriptor1;
        ParcelFileDescriptor parcelfiledescriptor2;
        String as[];
        ShellCallback shellcallback;
        ResultReceiver resultreceiver;
        parcelfiledescriptor = parcel.readFileDescriptor();
        parcelfiledescriptor1 = parcel.readFileDescriptor();
        parcelfiledescriptor2 = parcel.readFileDescriptor();
        as = parcel.readStringArray();
        shellcallback = (ShellCallback)ShellCallback.CREATOR.createFromParcel(parcel);
        resultreceiver = (ResultReceiver)ResultReceiver.CREATOR.createFromParcel(parcel);
        if(parcelfiledescriptor1 == null) goto _L4; else goto _L3
_L3:
        if(parcelfiledescriptor == null) goto _L6; else goto _L5
_L5:
        parcel = parcelfiledescriptor.getFileDescriptor();
_L11:
        FileDescriptor filedescriptor = parcelfiledescriptor1.getFileDescriptor();
        if(parcelfiledescriptor2 == null) goto _L8; else goto _L7
_L7:
        obj = parcelfiledescriptor2.getFileDescriptor();
_L9:
        shellCommand(parcel, filedescriptor, ((FileDescriptor) (obj)), as, shellcallback, resultreceiver);
_L4:
        IoUtils.closeQuietly(parcelfiledescriptor);
        IoUtils.closeQuietly(parcelfiledescriptor1);
        IoUtils.closeQuietly(parcelfiledescriptor2);
        if(parcel1 != null)
            parcel1.writeNoException();
        else
            StrictMode.clearGatheredViolations();
        return true;
_L6:
        parcel = null;
        continue; /* Loop/switch isn't completed */
_L8:
        obj = parcelfiledescriptor1.getFileDescriptor();
          goto _L9
        parcel;
        IoUtils.closeQuietly(parcelfiledescriptor);
        IoUtils.closeQuietly(parcelfiledescriptor1);
        IoUtils.closeQuietly(parcelfiledescriptor2);
        if(parcel1 != null)
            parcel1.writeNoException();
        else
            StrictMode.clearGatheredViolations();
        throw parcel;
_L2:
        return false;
        if(true) goto _L11; else goto _L10
_L10:
    }

    public boolean pingBinder()
    {
        return true;
    }

    public IInterface queryLocalInterface(String s)
    {
        if(mDescriptor != null && mDescriptor.equals(s))
            return mOwner;
        else
            return null;
    }

    public void shellCommand(FileDescriptor filedescriptor, FileDescriptor filedescriptor1, FileDescriptor filedescriptor2, String as[], ShellCallback shellcallback, ResultReceiver resultreceiver)
        throws RemoteException
    {
        onShellCommand(filedescriptor, filedescriptor1, filedescriptor2, as, shellcallback, resultreceiver);
    }

    public final boolean transact(int i, Parcel parcel, Parcel parcel1, int j)
        throws RemoteException
    {
        if(parcel != null)
            parcel.setDataPosition(0);
        boolean flag = onTransact(i, parcel, parcel1, j);
        if(parcel1 != null)
            parcel1.setDataPosition(0);
        return flag;
    }

    public boolean unlinkToDeath(IBinder.DeathRecipient deathrecipient, int i)
    {
        return true;
    }

    public static final boolean CHECK_PARCEL_SIZE = false;
    private static final boolean FIND_POTENTIAL_LEAKS = false;
    public static boolean LOG_RUNTIME_EXCEPTION = false;
    static final String TAG = "Binder";
    private static volatile String sDumpDisabled = null;
    private static volatile boolean sTracingEnabled = false;
    private static volatile TransactionTracker sTransactionTracker = null;
    static volatile boolean sWarnOnBlocking = false;
    private String mDescriptor;
    private long mObject;
    private IInterface mOwner;

    static 
    {
        LOG_RUNTIME_EXCEPTION = false;
    }
}
