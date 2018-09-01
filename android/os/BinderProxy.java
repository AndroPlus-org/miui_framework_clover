// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.util.Log;
import java.io.FileDescriptor;
import java.lang.ref.WeakReference;

// Referenced classes of package android.os:
//            IBinder, Binder, RemoteException, Parcel, 
//            ShellCallback, ResultReceiver, TransactionTracker, Trace, 
//            IInterface

final class BinderProxy
    implements IBinder
{

    BinderProxy()
    {
        mWarnOnBlocking = Binder.sWarnOnBlocking;
    }

    private final native void destroy();

    private static final void sendDeathNotice(IBinder.DeathRecipient deathrecipient)
    {
        deathrecipient.binderDied();
_L1:
        return;
        deathrecipient;
        Log.w("BinderNative", "Uncaught exception from death notification", deathrecipient);
          goto _L1
    }

    public void dump(FileDescriptor filedescriptor, String as[])
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeFileDescriptor(filedescriptor);
        parcel.writeStringArray(as);
        transact(0x5f444d50, parcel, parcel1, 0);
        parcel1.readException();
        parcel.recycle();
        parcel1.recycle();
        return;
        filedescriptor;
        parcel.recycle();
        parcel1.recycle();
        throw filedescriptor;
    }

    public void dumpAsync(FileDescriptor filedescriptor, String as[])
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeFileDescriptor(filedescriptor);
        parcel.writeStringArray(as);
        transact(0x5f444d50, parcel, parcel1, 1);
        parcel.recycle();
        parcel1.recycle();
        return;
        filedescriptor;
        parcel.recycle();
        parcel1.recycle();
        throw filedescriptor;
    }

    protected void finalize()
        throws Throwable
    {
        destroy();
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public native String getInterfaceDescriptor()
        throws RemoteException;

    public native boolean isBinderAlive();

    public native void linkToDeath(IBinder.DeathRecipient deathrecipient, int i)
        throws RemoteException;

    public native boolean pingBinder();

    public IInterface queryLocalInterface(String s)
    {
        return null;
    }

    public void shellCommand(FileDescriptor filedescriptor, FileDescriptor filedescriptor1, FileDescriptor filedescriptor2, String as[], ShellCallback shellcallback, ResultReceiver resultreceiver)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeFileDescriptor(filedescriptor);
        parcel.writeFileDescriptor(filedescriptor1);
        parcel.writeFileDescriptor(filedescriptor2);
        parcel.writeStringArray(as);
        ShellCallback.writeToParcel(shellcallback, parcel);
        resultreceiver.writeToParcel(parcel, 0);
        transact(0x5f434d44, parcel, parcel1, 0);
        parcel1.readException();
        parcel.recycle();
        parcel1.recycle();
        return;
        filedescriptor;
        parcel.recycle();
        parcel1.recycle();
        throw filedescriptor;
    }

    public boolean transact(int i, Parcel parcel, Parcel parcel1, int j)
        throws RemoteException
    {
        boolean flag;
        Binder.checkParcel(this, i, parcel, "Unreasonably large binder buffer");
        if(mWarnOnBlocking && (j & 1) == 0)
        {
            mWarnOnBlocking = false;
            Log.w("Binder", "Outgoing transactions from this process must be FLAG_ONEWAY", new Throwable());
        }
        flag = Binder.isTracingEnabled();
        if(flag)
        {
            Object obj = new Throwable();
            Binder.getTransactionTracker().addTrace(((Throwable) (obj)));
            obj = ((Throwable) (obj)).getStackTrace()[1];
            Trace.traceBegin(1L, (new StringBuilder()).append(((StackTraceElement) (obj)).getClassName()).append(".").append(((StackTraceElement) (obj)).getMethodName()).toString());
        }
        boolean flag1 = transactNative(i, parcel, parcel1, j);
        if(flag)
            Trace.traceEnd(1L);
        return flag1;
        parcel;
        if(flag)
            Trace.traceEnd(1L);
        throw parcel;
    }

    public native boolean transactNative(int i, Parcel parcel, Parcel parcel1, int j)
        throws RemoteException;

    public native boolean unlinkToDeath(IBinder.DeathRecipient deathrecipient, int i);

    private long mObject;
    private long mOrgue;
    private final WeakReference mSelf = new WeakReference(this);
    volatile boolean mWarnOnBlocking;
}
