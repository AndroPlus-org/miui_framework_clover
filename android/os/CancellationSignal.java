// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            RemoteException, ICancellationSignal, OperationCanceledException

public final class CancellationSignal
{
    public static interface OnCancelListener
    {

        public abstract void onCancel();
    }

    private static final class Transport extends ICancellationSignal.Stub
    {

        public void cancel()
            throws RemoteException
        {
            mCancellationSignal.cancel();
        }

        final CancellationSignal mCancellationSignal;

        private Transport()
        {
            mCancellationSignal = new CancellationSignal();
        }

        Transport(Transport transport)
        {
            this();
        }
    }


    public CancellationSignal()
    {
    }

    public static ICancellationSignal createTransport()
    {
        return new Transport(null);
    }

    public static CancellationSignal fromTransport(ICancellationSignal icancellationsignal)
    {
        if(icancellationsignal instanceof Transport)
            return ((Transport)icancellationsignal).mCancellationSignal;
        else
            return null;
    }

    private void waitForCancelFinishedLocked()
    {
        while(mCancelInProgress) 
            try
            {
                wait();
            }
            catch(InterruptedException interruptedexception) { }
    }

    public void cancel()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = mIsCanceled;
        if(!flag)
            break MISSING_BLOCK_LABEL_14;
        this;
        JVM INSTR monitorexit ;
        return;
        OnCancelListener oncancellistener;
        ICancellationSignal icancellationsignal;
        mIsCanceled = true;
        mCancelInProgress = true;
        oncancellistener = mOnCancelListener;
        icancellationsignal = mRemote;
        this;
        JVM INSTR monitorexit ;
        if(oncancellistener == null)
            break MISSING_BLOCK_LABEL_46;
        oncancellistener.onCancel();
        if(icancellationsignal == null)
            break MISSING_BLOCK_LABEL_56;
        Object obj;
        try
        {
            icancellationsignal.cancel();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj) { }
        this;
        JVM INSTR monitorenter ;
        mCancelInProgress = false;
        notifyAll();
        this;
        JVM INSTR monitorexit ;
        return;
        obj;
        throw obj;
        obj;
        throw obj;
        obj;
        this;
        JVM INSTR monitorenter ;
        mCancelInProgress = false;
        notifyAll();
        this;
        JVM INSTR monitorexit ;
        throw obj;
        obj;
        throw obj;
    }

    public boolean isCanceled()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = mIsCanceled;
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public void setOnCancelListener(OnCancelListener oncancellistener)
    {
        this;
        JVM INSTR monitorenter ;
        OnCancelListener oncancellistener1;
        waitForCancelFinishedLocked();
        oncancellistener1 = mOnCancelListener;
        if(oncancellistener1 != oncancellistener)
            break MISSING_BLOCK_LABEL_19;
        this;
        JVM INSTR monitorexit ;
        return;
        boolean flag;
        mOnCancelListener = oncancellistener;
        flag = mIsCanceled;
        if(flag && oncancellistener != null)
            break MISSING_BLOCK_LABEL_40;
        this;
        JVM INSTR monitorexit ;
        return;
        this;
        JVM INSTR monitorexit ;
        oncancellistener.onCancel();
        return;
        oncancellistener;
        throw oncancellistener;
    }

    public void setRemote(ICancellationSignal icancellationsignal)
    {
        this;
        JVM INSTR monitorenter ;
        ICancellationSignal icancellationsignal1;
        waitForCancelFinishedLocked();
        icancellationsignal1 = mRemote;
        if(icancellationsignal1 != icancellationsignal)
            break MISSING_BLOCK_LABEL_19;
        this;
        JVM INSTR monitorexit ;
        return;
        boolean flag;
        mRemote = icancellationsignal;
        flag = mIsCanceled;
        if(flag && icancellationsignal != null)
            break MISSING_BLOCK_LABEL_40;
        this;
        JVM INSTR monitorexit ;
        return;
        this;
        JVM INSTR monitorexit ;
        icancellationsignal.cancel();
_L2:
        return;
        icancellationsignal;
        throw icancellationsignal;
        icancellationsignal;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void throwIfCanceled()
    {
        if(isCanceled())
            throw new OperationCanceledException();
        else
            return;
    }

    private boolean mCancelInProgress;
    private boolean mIsCanceled;
    private OnCancelListener mOnCancelListener;
    private ICancellationSignal mRemote;
}
