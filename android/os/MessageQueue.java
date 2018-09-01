// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.util.*;
import android.util.proto.ProtoOutputStream;
import java.io.FileDescriptor;
import java.util.ArrayList;

// Referenced classes of package android.os:
//            Message, SystemClock, Binder, Handler

public final class MessageQueue
{
    private static final class FileDescriptorRecord
    {

        public final FileDescriptor mDescriptor;
        public int mEvents;
        public OnFileDescriptorEventListener mListener;
        public int mSeq;

        public FileDescriptorRecord(FileDescriptor filedescriptor, int i, OnFileDescriptorEventListener onfiledescriptoreventlistener)
        {
            mDescriptor = filedescriptor;
            mEvents = i;
            mListener = onfiledescriptoreventlistener;
        }
    }

    public static interface IdleHandler
    {

        public abstract boolean queueIdle();
    }

    public static interface OnFileDescriptorEventListener
    {

        public abstract int onFileDescriptorEvents(FileDescriptor filedescriptor, int i);

        public static final int EVENT_ERROR = 4;
        public static final int EVENT_INPUT = 1;
        public static final int EVENT_OUTPUT = 2;
    }


    MessageQueue(boolean flag)
    {
        mQuitAllowed = flag;
        mPtr = nativeInit();
    }

    private int dispatchEvents(int i, int j)
    {
        this;
        JVM INSTR monitorenter ;
        FileDescriptorRecord filedescriptorrecord = (FileDescriptorRecord)mFileDescriptorRecords.get(i);
        if(filedescriptorrecord != null)
            break MISSING_BLOCK_LABEL_22;
        this;
        JVM INSTR monitorexit ;
        return 0;
        int k = filedescriptorrecord.mEvents;
        j &= k;
        if(j == 0)
            return k;
        OnFileDescriptorEventListener onfiledescriptoreventlistener;
        int l;
        onfiledescriptoreventlistener = filedescriptorrecord.mListener;
        l = filedescriptorrecord.mSeq;
        this;
        JVM INSTR monitorexit ;
        int i1 = onfiledescriptoreventlistener.onFileDescriptorEvents(filedescriptorrecord.mDescriptor, j);
        j = i1;
        if(i1 != 0)
            j = i1 | 4;
        if(j == k) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorenter ;
        i = mFileDescriptorRecords.indexOfKey(i);
        if(i < 0)
            break MISSING_BLOCK_LABEL_142;
        if(mFileDescriptorRecords.valueAt(i) != filedescriptorrecord || filedescriptorrecord.mSeq != l)
            break MISSING_BLOCK_LABEL_142;
        filedescriptorrecord.mEvents = j;
        if(j != 0)
            break MISSING_BLOCK_LABEL_142;
        mFileDescriptorRecords.removeAt(i);
        this;
        JVM INSTR monitorexit ;
_L2:
        return j;
        Exception exception;
        exception;
        throw exception;
        exception;
        throw exception;
    }

    private void dispose()
    {
        if(mPtr != 0L)
        {
            nativeDestroy(mPtr);
            mPtr = 0L;
        }
    }

    private boolean isPollingLocked()
    {
        boolean flag;
        if(!mQuitting)
            flag = nativeIsPolling(mPtr);
        else
            flag = false;
        return flag;
    }

    private static native void nativeDestroy(long l);

    private static native long nativeInit();

    private static native boolean nativeIsPolling(long l);

    private native void nativePollOnce(long l, int i);

    private static native void nativeSetFileDescriptorEvents(long l, int i, int j);

    private static native void nativeWake(long l);

    private int postSyncBarrier(long l)
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        Message message;
        i = mNextBarrierToken;
        mNextBarrierToken = i + 1;
        message = Message.obtain();
        message.markInUse();
        message.when = l;
        message.arg1 = i;
        Object obj;
        Message message1;
        obj = null;
        message1 = null;
        Message message2 = mMessages;
        Message message3 = message2;
        if(l == 0L) goto _L2; else goto _L1
_L1:
        message3 = message2;
        obj = message1;
        if(message2 == null)
            break; /* Loop/switch isn't completed */
        message3 = message2;
        obj = message1;
        if(message2.when > l)
            break; /* Loop/switch isn't completed */
        message1 = message2;
        message2 = message2.next;
        if(true) goto _L1; else goto _L2
_L2:
        if(obj == null)
            break MISSING_BLOCK_LABEL_126;
        message.next = message3;
        obj.next = message;
_L3:
        this;
        JVM INSTR monitorexit ;
        return i;
        message.next = message3;
        mMessages = message;
          goto _L3
        Exception exception;
        exception;
        throw exception;
    }

    private void removeAllFutureMessagesLocked()
    {
        long l;
        Message message;
        l = SystemClock.uptimeMillis();
        message = mMessages;
        if(message == null) goto _L2; else goto _L1
_L1:
        Message message2 = message;
        if(message.when <= l) goto _L4; else goto _L3
_L3:
        removeAllMessagesLocked();
_L2:
        return;
_L6:
        message2 = message;
_L4:
        message = message2.next;
        if(message == null)
            return;
        if(message.when <= l) goto _L6; else goto _L5
_L5:
        message2.next = null;
        message2 = message;
        do
        {
            Message message1 = message2.next;
            message2.recycleUnchecked();
            if(message1 == null)
                continue;
            message2 = message1;
        } while(true);
        if(true) goto _L2; else goto _L7
_L7:
    }

    private void removeAllMessagesLocked()
    {
        Message message1;
        for(Message message = mMessages; message != null; message = message1)
        {
            message1 = message.next;
            message.recycleUnchecked();
        }

        mMessages = null;
    }

    private void updateOnFileDescriptorEventListenerLocked(FileDescriptor filedescriptor, int i, OnFileDescriptorEventListener onfiledescriptoreventlistener)
    {
        int j;
        int k;
        FileDescriptorRecord filedescriptorrecord1;
        j = filedescriptor.getInt$();
        k = -1;
        Object obj = null;
        filedescriptorrecord1 = obj;
        if(mFileDescriptorRecords != null)
        {
            int l = mFileDescriptorRecords.indexOfKey(j);
            k = l;
            filedescriptorrecord1 = obj;
            if(l >= 0)
            {
                FileDescriptorRecord filedescriptorrecord = (FileDescriptorRecord)mFileDescriptorRecords.valueAt(l);
                k = l;
                filedescriptorrecord1 = filedescriptorrecord;
                if(filedescriptorrecord != null)
                {
                    k = l;
                    filedescriptorrecord1 = filedescriptorrecord;
                    if(filedescriptorrecord.mEvents == i)
                        return;
                }
            }
        }
        if(i == 0) goto _L2; else goto _L1
_L1:
        i |= 4;
        if(filedescriptorrecord1 == null)
        {
            if(mFileDescriptorRecords == null)
                mFileDescriptorRecords = new SparseArray();
            filedescriptor = new FileDescriptorRecord(filedescriptor, i, onfiledescriptoreventlistener);
            mFileDescriptorRecords.put(j, filedescriptor);
        } else
        {
            filedescriptorrecord1.mListener = onfiledescriptoreventlistener;
            filedescriptorrecord1.mEvents = i;
            filedescriptorrecord1.mSeq = filedescriptorrecord1.mSeq + 1;
        }
        nativeSetFileDescriptorEvents(mPtr, j, i);
_L4:
        return;
_L2:
        if(filedescriptorrecord1 != null)
        {
            filedescriptorrecord1.mEvents = 0;
            mFileDescriptorRecords.removeAt(k);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void addIdleHandler(IdleHandler idlehandler)
    {
        if(idlehandler == null)
            throw new NullPointerException("Can't add a null IdleHandler");
        this;
        JVM INSTR monitorenter ;
        mIdleHandlers.add(idlehandler);
        this;
        JVM INSTR monitorexit ;
        return;
        idlehandler;
        throw idlehandler;
    }

    public void addOnFileDescriptorEventListener(FileDescriptor filedescriptor, int i, OnFileDescriptorEventListener onfiledescriptoreventlistener)
    {
        if(filedescriptor == null)
            throw new IllegalArgumentException("fd must not be null");
        if(onfiledescriptoreventlistener == null)
            throw new IllegalArgumentException("listener must not be null");
        this;
        JVM INSTR monitorenter ;
        updateOnFileDescriptorEventListenerLocked(filedescriptor, i, onfiledescriptoreventlistener);
        this;
        JVM INSTR monitorexit ;
        return;
        filedescriptor;
        throw filedescriptor;
    }

    void dump(Printer printer, String s, Handler handler)
    {
        this;
        JVM INSTR monitorenter ;
        long l = SystemClock.uptimeMillis();
        int i = 0;
        Message message = mMessages;
_L1:
        if(message == null)
            break MISSING_BLOCK_LABEL_97;
        if(handler == null)
            break MISSING_BLOCK_LABEL_34;
        if(handler != message.target)
            break MISSING_BLOCK_LABEL_84;
        StringBuilder stringbuilder = JVM INSTR new #197 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        printer.println(stringbuilder.append(s).append("Message ").append(i).append(": ").append(message.toString(l)).toString());
        i++;
        message = message.next;
          goto _L1
        handler = JVM INSTR new #197 <Class StringBuilder>;
        handler.StringBuilder();
        printer.println(handler.append(s).append("(Total messages: ").append(i).append(", polling=").append(isPollingLocked()).append(", quitting=").append(mQuitting).append(")").toString());
        this;
        JVM INSTR monitorexit ;
        return;
        printer;
        throw printer;
    }

    boolean enqueueMessage(Message message, long l)
    {
        if(message.target == null)
            throw new IllegalArgumentException("Message must have a target.");
        if(message.isInUse())
            throw new IllegalStateException((new StringBuilder()).append(message).append(" This message is already in use.").toString());
        this;
        JVM INSTR monitorenter ;
        if(!mQuitting)
            break MISSING_BLOCK_LABEL_118;
        IllegalStateException illegalstateexception = JVM INSTR new #243 <Class IllegalStateException>;
        StringBuilder stringbuilder = JVM INSTR new #197 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        illegalstateexception.IllegalStateException(stringbuilder.append(message.target).append(" sending message to a Handler on a dead thread").toString());
        Log.w("MessageQueue", illegalstateexception.getMessage(), illegalstateexception);
        message.recycle();
        this;
        JVM INSTR monitorexit ;
        return false;
        Message message2;
        message.markInUse();
        message.when = l;
        message2 = mMessages;
        if(message2 != null && l != 0L) goto _L2; else goto _L1
_L1:
        boolean flag;
        message.next = message2;
        mMessages = message;
        flag = mBlocked;
_L9:
        if(!flag)
            break MISSING_BLOCK_LABEL_173;
        nativeWake(mPtr);
        this;
        JVM INSTR monitorexit ;
        return true;
_L2:
        if(l < message2.when) goto _L1; else goto _L3
_L3:
        if(!mBlocked || message2.target != null) goto _L5; else goto _L4
_L4:
        flag = message.isAsynchronous();
_L10:
        Message message1 = message2.next;
        if(message1 == null) goto _L7; else goto _L6
_L6:
        if(l >= message1.when) goto _L8; else goto _L7
_L7:
        message.next = message1;
        message2.next = message;
          goto _L9
        message;
        throw message;
_L5:
        flag = false;
          goto _L10
_L8:
        message2 = message1;
        if(!flag) goto _L10; else goto _L11
_L11:
        boolean flag1 = message1.isAsynchronous();
        message2 = message1;
        if(flag1)
        {
            flag = false;
            message2 = message1;
        }
          goto _L10
    }

    protected void finalize()
        throws Throwable
    {
        dispose();
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    boolean hasMessages(Handler handler)
    {
        if(handler == null)
            return false;
        this;
        JVM INSTR monitorenter ;
        Message message = mMessages;
_L2:
        if(message == null)
            break; /* Loop/switch isn't completed */
        Handler handler1 = message.target;
        if(handler1 != handler)
            break MISSING_BLOCK_LABEL_31;
        this;
        JVM INSTR monitorexit ;
        return true;
        message = message.next;
        if(true) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return false;
        handler;
        throw handler;
    }

    boolean hasMessages(Handler handler, int i, Object obj)
    {
        if(handler == null)
            return false;
        this;
        JVM INSTR monitorenter ;
        Message message = mMessages;
_L2:
        if(message == null)
            break; /* Loop/switch isn't completed */
        if(message.target != handler || message.what != i)
            break MISSING_BLOCK_LABEL_58;
        if(obj == null)
            break MISSING_BLOCK_LABEL_54;
        Object obj1 = message.obj;
        if(obj1 != obj)
            break MISSING_BLOCK_LABEL_58;
        this;
        JVM INSTR monitorexit ;
        return true;
        message = message.next;
        if(true) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return false;
        handler;
        throw handler;
    }

    boolean hasMessages(Handler handler, Runnable runnable, Object obj)
    {
        if(handler == null)
            return false;
        this;
        JVM INSTR monitorenter ;
        Message message = mMessages;
_L2:
        if(message == null)
            break; /* Loop/switch isn't completed */
        if(message.target != handler || message.callback != runnable)
            break MISSING_BLOCK_LABEL_58;
        if(obj == null)
            break MISSING_BLOCK_LABEL_54;
        Object obj1 = message.obj;
        if(obj1 != obj)
            break MISSING_BLOCK_LABEL_58;
        this;
        JVM INSTR monitorexit ;
        return true;
        message = message.next;
        if(true) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return false;
        handler;
        throw handler;
    }

    public boolean isIdle()
    {
        boolean flag = true;
        this;
        JVM INSTR monitorenter ;
        long l = SystemClock.uptimeMillis();
        boolean flag1 = flag;
        long l1;
        if(mMessages == null)
            break MISSING_BLOCK_LABEL_37;
        l1 = mMessages.when;
        if(l < l1)
            flag1 = flag;
        else
            flag1 = false;
        this;
        JVM INSTR monitorexit ;
        return flag1;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean isPolling()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = isPollingLocked();
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    Message next()
    {
        long l;
        int i;
        int j;
        l = mPtr;
        if(l == 0L)
            return null;
        i = -1;
        j = 0;
_L12:
        if(j != 0)
            Binder.flushPendingCommands();
        nativePollOnce(l, j);
        this;
        JVM INSTR monitorenter ;
        long l1 = SystemClock.uptimeMillis();
        Message message = null;
        Message message1 = mMessages;
        Message message2;
        Object obj;
        message2 = message1;
        obj = message;
        if(message1 == null)
            break MISSING_BLOCK_LABEL_128;
        message2 = message1;
        obj = message;
        if(message1.target != null)
            break MISSING_BLOCK_LABEL_128;
        obj = message1;
_L2:
        message1 = ((Message) (obj));
        message = ((Message) (obj)).next;
        message2 = message;
        obj = message1;
        if(message == null)
            break MISSING_BLOCK_LABEL_128;
        obj = message;
        if(message.isAsynchronous() ^ true) goto _L2; else goto _L1
_L1:
        obj = message1;
        message2 = message;
        if(message2 == null)
            break MISSING_BLOCK_LABEL_231;
        if(l1 >= message2.when) goto _L4; else goto _L3
_L3:
        j = (int)Math.min(message2.when - l1, 0x7fffffffL);
_L6:
        if(!mQuitting)
            break MISSING_BLOCK_LABEL_237;
        dispose();
        this;
        JVM INSTR monitorexit ;
        return null;
_L4:
        mBlocked = false;
        if(obj == null)
            break MISSING_BLOCK_LABEL_212;
        obj.next = message2.next;
_L5:
        message2.next = null;
        message2.markInUse();
        this;
        JVM INSTR monitorexit ;
        return message2;
        mMessages = message2.next;
          goto _L5
        Exception exception;
        exception;
        throw exception;
        j = -1;
          goto _L6
        int k;
        k = i;
        if(i >= 0)
            break MISSING_BLOCK_LABEL_276;
        if(mMessages == null)
            break MISSING_BLOCK_LABEL_267;
        k = i;
        if(l1 >= mMessages.when)
            break MISSING_BLOCK_LABEL_276;
        k = mIdleHandlers.size();
        if(k > 0)
            break MISSING_BLOCK_LABEL_294;
        mBlocked = true;
        this;
        JVM INSTR monitorexit ;
        i = k;
        continue; /* Loop/switch isn't completed */
        if(mPendingIdleHandlers == null)
            mPendingIdleHandlers = new IdleHandler[Math.max(k, 4)];
        mPendingIdleHandlers = (IdleHandler[])mIdleHandlers.toArray(mPendingIdleHandlers);
        this;
        JVM INSTR monitorexit ;
        i = 0;
_L9:
        boolean flag;
        if(i >= k)
            break MISSING_BLOCK_LABEL_421;
        exception = mPendingIdleHandlers[i];
        mPendingIdleHandlers[i] = null;
        flag = false;
        boolean flag1 = exception.queueIdle();
        flag = flag1;
_L10:
        if(flag) goto _L8; else goto _L7
_L7:
        this;
        JVM INSTR monitorenter ;
        mIdleHandlers.remove(exception);
        this;
        JVM INSTR monitorexit ;
_L8:
        i++;
          goto _L9
        Throwable throwable;
        throwable;
        Log.wtf("MessageQueue", "IdleHandler threw exception", throwable);
          goto _L10
        exception;
        throw exception;
        i = 0;
        j = 0;
        if(true) goto _L12; else goto _L11
_L11:
    }

    public int postSyncBarrier()
    {
        return postSyncBarrier(SystemClock.uptimeMillis());
    }

    void quit(boolean flag)
    {
        if(!mQuitAllowed)
            throw new IllegalStateException("Main thread not allowed to quit.");
        this;
        JVM INSTR monitorenter ;
        boolean flag1 = mQuitting;
        if(!flag1)
            break MISSING_BLOCK_LABEL_32;
        this;
        JVM INSTR monitorexit ;
        return;
        mQuitting = true;
        if(!flag)
            break MISSING_BLOCK_LABEL_55;
        removeAllFutureMessagesLocked();
_L1:
        nativeWake(mPtr);
        this;
        JVM INSTR monitorexit ;
        return;
        removeAllMessagesLocked();
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    void removeCallbacksAndMessages(Handler handler, Object obj)
    {
        if(handler == null)
            return;
        this;
        JVM INSTR monitorenter ;
        Message message = mMessages;
_L2:
        Message message1;
        message1 = message;
        if(message == null)
            break; /* Loop/switch isn't completed */
        message1 = message;
        if(message.target != handler)
            break; /* Loop/switch isn't completed */
        if(obj == null)
            break MISSING_BLOCK_LABEL_45;
        message1 = message;
        if(message.obj != obj)
            break; /* Loop/switch isn't completed */
        message1 = message.next;
        mMessages = message1;
        message.recycleUnchecked();
        message = message1;
        if(true) goto _L2; else goto _L1
_L1:
        if(message1 == null)
            break; /* Loop/switch isn't completed */
        message = message1.next;
        if(message == null)
            break MISSING_BLOCK_LABEL_127;
        if(message.target != handler)
            break MISSING_BLOCK_LABEL_127;
        if(obj == null)
            break MISSING_BLOCK_LABEL_102;
        if(message.obj != obj)
            break MISSING_BLOCK_LABEL_127;
        Message message2 = message.next;
        message.recycleUnchecked();
        message1.next = message2;
        continue; /* Loop/switch isn't completed */
        handler;
        throw handler;
        message1 = message;
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void removeIdleHandler(IdleHandler idlehandler)
    {
        this;
        JVM INSTR monitorenter ;
        mIdleHandlers.remove(idlehandler);
        this;
        JVM INSTR monitorexit ;
        return;
        idlehandler;
        throw idlehandler;
    }

    void removeMessages(Handler handler, int i, Object obj)
    {
        if(handler == null)
            return;
        this;
        JVM INSTR monitorenter ;
        Message message = mMessages;
_L2:
        Message message1;
        message1 = message;
        if(message == null)
            break; /* Loop/switch isn't completed */
        message1 = message;
        if(message.target != handler)
            break; /* Loop/switch isn't completed */
        message1 = message;
        if(message.what != i)
            break; /* Loop/switch isn't completed */
        if(obj == null)
            break MISSING_BLOCK_LABEL_65;
        message1 = message;
        if(message.obj != obj)
            break; /* Loop/switch isn't completed */
        message1 = message.next;
        mMessages = message1;
        message.recycleUnchecked();
        message = message1;
        if(true) goto _L2; else goto _L1
_L1:
        if(message1 == null)
            break; /* Loop/switch isn't completed */
        message = message1.next;
        if(message == null)
            break MISSING_BLOCK_LABEL_165;
        if(message.target != handler || message.what != i)
            break MISSING_BLOCK_LABEL_165;
        if(obj == null)
            break MISSING_BLOCK_LABEL_138;
        if(message.obj != obj)
            break MISSING_BLOCK_LABEL_165;
        Message message2 = message.next;
        message.recycleUnchecked();
        message1.next = message2;
        continue; /* Loop/switch isn't completed */
        handler;
        throw handler;
        message1 = message;
        if(true) goto _L1; else goto _L3
_L3:
    }

    void removeMessages(Handler handler, Runnable runnable, Object obj)
    {
        if(handler == null || runnable == null)
            return;
        this;
        JVM INSTR monitorenter ;
        Message message = mMessages;
_L2:
        Message message1;
        message1 = message;
        if(message == null)
            break; /* Loop/switch isn't completed */
        message1 = message;
        if(message.target != handler)
            break; /* Loop/switch isn't completed */
        message1 = message;
        if(message.callback != runnable)
            break; /* Loop/switch isn't completed */
        if(obj == null)
            break MISSING_BLOCK_LABEL_69;
        message1 = message;
        if(message.obj != obj)
            break; /* Loop/switch isn't completed */
        message1 = message.next;
        mMessages = message1;
        message.recycleUnchecked();
        message = message1;
        if(true) goto _L2; else goto _L1
_L1:
        if(message1 == null)
            break; /* Loop/switch isn't completed */
        message = message1.next;
        if(message == null)
            break MISSING_BLOCK_LABEL_169;
        if(message.target != handler || message.callback != runnable)
            break MISSING_BLOCK_LABEL_169;
        if(obj == null)
            break MISSING_BLOCK_LABEL_142;
        if(message.obj != obj)
            break MISSING_BLOCK_LABEL_169;
        Message message2 = message.next;
        message.recycleUnchecked();
        message1.next = message2;
        continue; /* Loop/switch isn't completed */
        handler;
        throw handler;
        message1 = message;
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void removeOnFileDescriptorEventListener(FileDescriptor filedescriptor)
    {
        if(filedescriptor == null)
            throw new IllegalArgumentException("fd must not be null");
        this;
        JVM INSTR monitorenter ;
        updateOnFileDescriptorEventListenerLocked(filedescriptor, 0, null);
        this;
        JVM INSTR monitorexit ;
        return;
        filedescriptor;
        throw filedescriptor;
    }

    public void removeSyncBarrier(int i)
    {
        this;
        JVM INSTR monitorenter ;
        Object obj = null;
        Object obj1 = mMessages;
_L2:
        if(obj1 == null)
            break; /* Loop/switch isn't completed */
        if(((Message) (obj1)).target == null && ((Message) (obj1)).arg1 == i)
            break; /* Loop/switch isn't completed */
        obj = obj1;
        obj1 = ((Message) (obj1)).next;
        if(true) goto _L2; else goto _L1
_L1:
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_60;
        obj1 = JVM INSTR new #243 <Class IllegalStateException>;
        ((IllegalStateException) (obj1)).IllegalStateException("The specified message queue synchronization  barrier token has not been posted or has already been removed.");
        throw obj1;
        obj1;
        this;
        JVM INSTR monitorexit ;
        throw obj1;
        if(obj == null) goto _L4; else goto _L3
_L3:
        obj.next = ((Message) (obj1)).next;
        i = 0;
_L6:
        ((Message) (obj1)).recycleUnchecked();
        if(i == 0)
            break MISSING_BLOCK_LABEL_98;
        if(mQuitting ^ true)
            nativeWake(mPtr);
        this;
        JVM INSTR monitorexit ;
        return;
_L4:
        mMessages = ((Message) (obj1)).next;
        if(mMessages == null)
            break MISSING_BLOCK_LABEL_128;
        obj = mMessages.target;
        if(obj == null)
            break MISSING_BLOCK_LABEL_133;
        i = 1;
        continue; /* Loop/switch isn't completed */
        i = 0;
        if(true) goto _L6; else goto _L5
_L5:
    }

    void writeToProto(ProtoOutputStream protooutputstream, long l)
    {
        l = protooutputstream.start(l);
        this;
        JVM INSTR monitorenter ;
        Message message = mMessages;
_L1:
        if(message == null)
            break MISSING_BLOCK_LABEL_38;
        message.writeToProto(protooutputstream, 0x21100000001L);
        message = message.next;
          goto _L1
        protooutputstream.write(0x10d00000002L, isPollingLocked());
        protooutputstream.write(0x10d00000003L, mQuitting);
        this;
        JVM INSTR monitorexit ;
        protooutputstream.end(l);
        return;
        protooutputstream;
        throw protooutputstream;
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "MessageQueue";
    private boolean mBlocked;
    private SparseArray mFileDescriptorRecords;
    private final ArrayList mIdleHandlers = new ArrayList();
    Message mMessages;
    private int mNextBarrierToken;
    private IdleHandler mPendingIdleHandlers[];
    private long mPtr;
    private final boolean mQuitAllowed;
    private boolean mQuitting;
}
