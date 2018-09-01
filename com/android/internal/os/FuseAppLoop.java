// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.os.*;
import android.system.ErrnoException;
import android.system.OsConstants;
import android.util.Log;
import android.util.SparseArray;
import com.android.internal.util.Preconditions;
import java.util.*;
import java.util.concurrent.ThreadFactory;

// Referenced classes of package com.android.internal.os:
//            FuseUnavailableMountException

public class FuseAppLoop
    implements android.os.Handler.Callback
{
    private static class Args
    {

        byte data[];
        CallbackEntry entry;
        long inode;
        long offset;
        int size;
        long unique;

        private Args()
        {
        }

        Args(Args args)
        {
            this();
        }
    }

    private static class BytesMap
    {

        void clear()
        {
            mEntries.clear();
        }

        byte[] startUsing(long l)
        {
            BytesMapEntry bytesmapentry = (BytesMapEntry)mEntries.get(Long.valueOf(l));
            BytesMapEntry bytesmapentry1 = bytesmapentry;
            if(bytesmapentry == null)
            {
                bytesmapentry1 = new BytesMapEntry(null);
                mEntries.put(Long.valueOf(l), bytesmapentry1);
            }
            bytesmapentry1.counter = bytesmapentry1.counter + 1;
            return bytesmapentry1.bytes;
        }

        void stopUsing(long l)
        {
            BytesMapEntry bytesmapentry = (BytesMapEntry)mEntries.get(Long.valueOf(l));
            Preconditions.checkNotNull(bytesmapentry);
            bytesmapentry.counter = bytesmapentry.counter - 1;
            if(bytesmapentry.counter <= 0)
                mEntries.remove(Long.valueOf(l));
        }

        final Map mEntries;

        private BytesMap()
        {
            mEntries = new HashMap();
        }

        BytesMap(BytesMap bytesmap)
        {
            this();
        }
    }

    private static class BytesMapEntry
    {

        byte bytes[];
        int counter;

        private BytesMapEntry()
        {
            counter = 0;
            bytes = new byte[0x40000];
        }

        BytesMapEntry(BytesMapEntry bytesmapentry)
        {
            this();
        }
    }

    private static class CallbackEntry
    {

        long getThreadId()
        {
            return handler.getLooper().getThread().getId();
        }

        final ProxyFileDescriptorCallback callback;
        final Handler handler;
        boolean opened;

        CallbackEntry(ProxyFileDescriptorCallback proxyfiledescriptorcallback, Handler handler1)
        {
            callback = (ProxyFileDescriptorCallback)Preconditions.checkNotNull(proxyfiledescriptorcallback);
            handler = (Handler)Preconditions.checkNotNull(handler1);
        }
    }

    public static class UnmountedException extends Exception
    {

        public UnmountedException()
        {
        }
    }


    public FuseAppLoop(int i, ParcelFileDescriptor parcelfiledescriptor, ThreadFactory threadfactory)
    {
        mNextInode = 2;
        mMountPointId = i;
        ThreadFactory threadfactory1 = threadfactory;
        if(threadfactory == null)
            threadfactory1 = sDefaultThreadFactory;
        mInstance = native_new(parcelfiledescriptor.detachFd());
        mThread = threadfactory1.newThread(new _.Lambda._cls7ZK_l4tRY1lJoOPMxlJZMSKtyQY(this));
        mThread.start();
    }

    private static int checkInode(long l)
    {
        Preconditions.checkArgumentInRange(l, 2L, 0x7fffffffL, "checkInode");
        return (int)l;
    }

    private CallbackEntry getCallbackEntryOrThrowLocked(long l)
        throws ErrnoException
    {
        CallbackEntry callbackentry = (CallbackEntry)mCallbackMap.get(checkInode(l));
        if(callbackentry == null)
            throw new ErrnoException("getCallbackEntryOrThrowLocked", OsConstants.ENOENT);
        else
            return callbackentry;
    }

    private static int getError(Exception exception)
    {
        if(exception instanceof ErrnoException)
        {
            int i = ((ErrnoException)exception).errno;
            if(i != OsConstants.ENOSYS)
                return -i;
        }
        return -OsConstants.EBADF;
    }

    private void onCommand(int i, long l, long l1, long l2, 
            int j, byte abyte0[])
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        Args args;
        if(mArgsPool.size() != 0)
            break MISSING_BLOCK_LABEL_139;
        args = JVM INSTR new #10  <Class FuseAppLoop$Args>;
        args.Args(null);
_L2:
        args.unique = l;
        args.inode = l1;
        args.offset = l2;
        args.size = j;
        args.data = abyte0;
        args.entry = getCallbackEntryOrThrowLocked(l1);
        if(!args.entry.handler.sendMessage(Message.obtain(args.entry.handler, i, 0, 0, args)))
        {
            abyte0 = JVM INSTR new #159 <Class ErrnoException>;
            abyte0.ErrnoException("onCommand", OsConstants.EBADF);
            throw abyte0;
        }
          goto _L1
        abyte0;
        replySimpleLocked(l, getError(abyte0));
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        args = (Args)mArgsPool.pop();
          goto _L2
        abyte0;
        throw abyte0;
    }

    private byte[] onOpen(long l, long l1)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1;
        obj1 = getCallbackEntryOrThrowLocked(l1);
        if(((CallbackEntry) (obj1)).opened)
        {
            obj1 = JVM INSTR new #159 <Class ErrnoException>;
            ((ErrnoException) (obj1)).ErrnoException("onOpen", OsConstants.EMFILE);
            throw obj1;
        }
          goto _L1
        obj1;
        replySimpleLocked(l, getError(((Exception) (obj1))));
_L3:
        obj;
        JVM INSTR monitorexit ;
        return null;
_L1:
        if(mInstance == 0L) goto _L3; else goto _L2
_L2:
        byte abyte0[];
        native_replyOpen(mInstance, l, l1);
        obj1.opened = true;
        abyte0 = mBytesMap.startUsing(((CallbackEntry) (obj1)).getThreadId());
        obj;
        JVM INSTR monitorexit ;
        return abyte0;
        Exception exception;
        exception;
        throw exception;
    }

    private void recycleLocked(Args args)
    {
        if(mArgsPool.size() < 50)
            mArgsPool.add(args);
    }

    private void replySimpleLocked(long l, int i)
    {
        if(mInstance != 0L)
            native_replySimple(mInstance, l, i);
    }

    public int getMountPointId()
    {
        return mMountPointId;
    }

    public boolean handleMessage(Message message)
    {
        Args args;
        Object obj;
        long l;
        long l1;
        int i;
        long l2;
        byte abyte0[];
        args = (Args)message.obj;
        obj = args.entry;
        l = args.inode;
        l1 = args.unique;
        i = args.size;
        l2 = args.offset;
        abyte0 = args.data;
        message.what;
        JVM INSTR lookupswitch 6: default 104
    //                   1: 179
    //                   3: 233
    //                   15: 287
    //                   16: 347
    //                   18: 454
    //                   20: 405;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
        abyte0 = JVM INSTR new #284 <Class IllegalArgumentException>;
        obj = JVM INSTR new #286 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        abyte0.IllegalArgumentException(((StringBuilder) (obj)).append("Unknown FUSE command: ").append(message.what).toString());
        throw abyte0;
        obj;
        message = ((Message) (mLock));
        message;
        JVM INSTR monitorenter ;
        Log.e("FuseAppLoop", "", ((Throwable) (obj)));
        replySimpleLocked(l1, getError(((Exception) (obj))));
        recycleLocked(args);
        message;
        JVM INSTR monitorexit ;
_L9:
        return true;
_L2:
        l2 = ((CallbackEntry) (obj)).callback.onGetSize();
        obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mInstance != 0L)
            native_replyLookup(mInstance, l1, l, l2);
        recycleLocked(args);
        obj;
        JVM INSTR monitorexit ;
        continue; /* Loop/switch isn't completed */
        message;
        obj;
        JVM INSTR monitorexit ;
        throw message;
_L3:
        l2 = ((CallbackEntry) (obj)).callback.onGetSize();
        obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mInstance != 0L)
            native_replyGetAttr(mInstance, l1, l, l2);
        recycleLocked(args);
        obj;
        JVM INSTR monitorexit ;
        continue; /* Loop/switch isn't completed */
        message;
        obj;
        JVM INSTR monitorexit ;
        throw message;
_L4:
        i = ((CallbackEntry) (obj)).callback.onRead(l2, i, abyte0);
        message = ((Message) (mLock));
        message;
        JVM INSTR monitorenter ;
        if(mInstance != 0L)
            native_replyRead(mInstance, l1, i, abyte0);
        recycleLocked(args);
        message;
        JVM INSTR monitorexit ;
        continue; /* Loop/switch isn't completed */
        obj;
        message;
        JVM INSTR monitorexit ;
        throw obj;
_L5:
        i = ((CallbackEntry) (obj)).callback.onWrite(l2, i, abyte0);
        message = ((Message) (mLock));
        message;
        JVM INSTR monitorenter ;
        if(mInstance != 0L)
            native_replyWrite(mInstance, l1, i);
        recycleLocked(args);
        message;
        JVM INSTR monitorexit ;
        continue; /* Loop/switch isn't completed */
        obj;
        message;
        JVM INSTR monitorexit ;
        throw obj;
_L7:
        ((CallbackEntry) (obj)).callback.onFsync();
        obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mInstance != 0L)
            native_replySimple(mInstance, l1, 0);
        recycleLocked(args);
        obj;
        JVM INSTR monitorexit ;
        continue; /* Loop/switch isn't completed */
        message;
        obj;
        JVM INSTR monitorexit ;
        throw message;
_L6:
        ((CallbackEntry) (obj)).callback.onRelease();
        message = ((Message) (mLock));
        message;
        JVM INSTR monitorenter ;
        if(mInstance != 0L)
            native_replySimple(mInstance, l1, 0);
        mBytesMap.stopUsing(((CallbackEntry) (obj)).getThreadId());
        recycleLocked(args);
        message;
        JVM INSTR monitorexit ;
        if(true) goto _L9; else goto _L8
_L8:
        Exception exception1;
        exception1;
        message;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        throw exception;
    }

    void lambda$_2D_com_android_internal_os_FuseAppLoop_2801()
    {
        native_start(mInstance);
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        native_delete(mInstance);
        mInstance = 0L;
        mBytesMap.clear();
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    native void native_delete(long l);

    native long native_new(int i);

    native void native_replyGetAttr(long l, long l1, long l2, long l3);

    native void native_replyLookup(long l, long l1, long l2, long l3);

    native void native_replyOpen(long l, long l1, long l2);

    native void native_replyRead(long l, long l1, int i, byte abyte0[]);

    native void native_replySimple(long l, long l1, int i);

    native void native_replyWrite(long l, long l1, int i);

    native void native_start(long l);

    public int registerCallback(ProxyFileDescriptorCallback proxyfiledescriptorcallback, Handler handler)
        throws FuseUnavailableMountException
    {
        boolean flag = true;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        Preconditions.checkNotNull(proxyfiledescriptorcallback);
        Preconditions.checkNotNull(handler);
        boolean flag1;
        if(mCallbackMap.size() < 0x7ffffffd)
            flag1 = true;
        else
            flag1 = false;
        Preconditions.checkState(flag1, "Too many opened files.");
        if(Thread.currentThread().getId() != handler.getLooper().getThread().getId())
            flag1 = flag;
        else
            flag1 = false;
        Preconditions.checkArgument(flag1, "Handler must be different from the current thread");
        if(mInstance == 0L)
        {
            proxyfiledescriptorcallback = JVM INSTR new #365 <Class FuseUnavailableMountException>;
            proxyfiledescriptorcallback.FuseUnavailableMountException(mMountPointId);
            throw proxyfiledescriptorcallback;
        }
        break MISSING_BLOCK_LABEL_117;
        proxyfiledescriptorcallback;
        obj;
        JVM INSTR monitorexit ;
        throw proxyfiledescriptorcallback;
        int i;
        do
        {
            i = mNextInode;
            mNextInode = mNextInode + 1;
            if(mNextInode < 0)
                mNextInode = 2;
        } while(mCallbackMap.get(i) != null);
        SparseArray sparsearray = mCallbackMap;
        CallbackEntry callbackentry = JVM INSTR new #19  <Class FuseAppLoop$CallbackEntry>;
        Handler handler1 = JVM INSTR new #229 <Class Handler>;
        handler1.Handler(handler.getLooper(), this);
        callbackentry.CallbackEntry(proxyfiledescriptorcallback, handler1);
        sparsearray.put(i, callbackentry);
        obj;
        JVM INSTR monitorexit ;
        return i;
    }

    public void unregisterCallback(int i)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        mCallbackMap.remove(i);
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private static final int ARGS_POOL_SIZE = 50;
    private static final boolean DEBUG = Log.isLoggable("FuseAppLoop", 3);
    private static final int FUSE_FSYNC = 20;
    private static final int FUSE_GETATTR = 3;
    private static final int FUSE_LOOKUP = 1;
    private static final int FUSE_MAX_WRITE = 0x40000;
    private static final int FUSE_OK = 0;
    private static final int FUSE_OPEN = 14;
    private static final int FUSE_READ = 15;
    private static final int FUSE_RELEASE = 18;
    private static final int FUSE_WRITE = 16;
    private static final int MIN_INODE = 2;
    public static final int ROOT_INODE = 1;
    private static final String TAG = "FuseAppLoop";
    private static final ThreadFactory sDefaultThreadFactory = new ThreadFactory() {

        public Thread newThread(Runnable runnable)
        {
            return new Thread(runnable, "FuseAppLoop");
        }

    }
;
    private final LinkedList mArgsPool = new LinkedList();
    private final BytesMap mBytesMap = new BytesMap(null);
    private final SparseArray mCallbackMap = new SparseArray();
    private long mInstance;
    private final Object mLock = new Object();
    private final int mMountPointId;
    private int mNextInode;
    private final Thread mThread;

}
