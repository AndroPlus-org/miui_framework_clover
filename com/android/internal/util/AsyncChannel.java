// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;

import android.content.*;
import android.os.*;
import android.util.Slog;
import java.util.Stack;

// Referenced classes of package com.android.internal.util:
//            AsyncService

public class AsyncChannel
{
    final class _cls1ConnectAsync
        implements Runnable
    {

        public void run()
        {
            int i = connectSrcHandlerToPackageSync(mSrcCtx, mSrcHdlr, mDstPackageName, mDstClassName);
            AsyncChannel._2D_wrap1(AsyncChannel.this, i);
        }

        String mDstClassName;
        String mDstPackageName;
        Context mSrcCtx;
        Handler mSrcHdlr;
        final AsyncChannel this$0;

        _cls1ConnectAsync(Context context, Handler handler, String s, String s1)
        {
            this$0 = AsyncChannel.this;
            super();
            mSrcCtx = context;
            mSrcHdlr = handler;
            mDstPackageName = s;
            mDstClassName = s1;
        }
    }

    class AsyncChannelConnection
        implements ServiceConnection
    {

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            AsyncChannel._2D_set0(AsyncChannel.this, new Messenger(ibinder));
            AsyncChannel._2D_wrap1(AsyncChannel.this, 0);
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            AsyncChannel._2D_wrap0(AsyncChannel.this, 0);
        }

        final AsyncChannel this$0;

        AsyncChannelConnection()
        {
            this$0 = AsyncChannel.this;
            super();
        }
    }

    private final class DeathMonitor
        implements android.os.IBinder.DeathRecipient
    {

        public void binderDied()
        {
            AsyncChannel._2D_wrap0(AsyncChannel.this, 4);
        }

        final AsyncChannel this$0;

        DeathMonitor()
        {
            this$0 = AsyncChannel.this;
            super();
        }
    }

    private static class SyncMessenger
    {

        static Message _2D_wrap0(Messenger messenger, Message message)
        {
            return sendMessageSynchronously(messenger, message);
        }

        private static SyncMessenger obtain()
        {
            Stack stack = sStack;
            stack;
            JVM INSTR monitorenter ;
            SyncMessenger syncmessenger;
            if(!sStack.isEmpty())
                break MISSING_BLOCK_LABEL_127;
            syncmessenger = JVM INSTR new #2   <Class AsyncChannel$SyncMessenger>;
            syncmessenger.SyncMessenger();
            Object obj = JVM INSTR new #48  <Class HandlerThread>;
            StringBuilder stringbuilder = JVM INSTR new #50  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            stringbuilder = stringbuilder.append("SyncHandler-");
            int i = sCount;
            sCount = i + 1;
            ((HandlerThread) (obj)).HandlerThread(stringbuilder.append(i).toString());
            syncmessenger.mHandlerThread = ((HandlerThread) (obj));
            syncmessenger.mHandlerThread.start();
            obj = JVM INSTR new #9   <Class AsyncChannel$SyncMessenger$SyncHandler>;
            syncmessenger.getClass();
            ((SyncHandler) (obj)).syncmessenger. SyncHandler(syncmessenger.mHandlerThread.getLooper(), null);
            syncmessenger.mHandler = ((SyncHandler) (obj));
            obj = JVM INSTR new #87  <Class Messenger>;
            ((Messenger) (obj)).Messenger(syncmessenger.mHandler);
            syncmessenger.mMessenger = ((Messenger) (obj));
_L1:
            stack;
            JVM INSTR monitorexit ;
            return syncmessenger;
            syncmessenger = (SyncMessenger)sStack.pop();
              goto _L1
            Exception exception;
            exception;
            throw exception;
        }

        private void recycle()
        {
            Stack stack = sStack;
            stack;
            JVM INSTR monitorenter ;
            sStack.push(this);
            stack;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        private static Message sendMessageSynchronously(Messenger messenger, Message message)
        {
            SyncMessenger syncmessenger;
            Object obj;
            Messenger messenger1;
            Object obj1;
            Messenger messenger2;
            Messenger messenger3;
            syncmessenger = obtain();
            obj = null;
            messenger1 = null;
            obj1 = null;
            messenger2 = null;
            messenger3 = messenger2;
            if(messenger == null)
                break MISSING_BLOCK_LABEL_167;
            messenger3 = messenger2;
            if(message == null)
                break MISSING_BLOCK_LABEL_167;
            messenger3 = messenger1;
            messenger2 = obj1;
            message.replyTo = syncmessenger.mMessenger;
            messenger3 = messenger1;
            messenger2 = obj1;
            Object obj2 = SyncHandler._2D_get0(syncmessenger.mHandler);
            messenger3 = messenger1;
            messenger2 = obj1;
            obj2;
            JVM INSTR monitorenter ;
            messenger1 = obj;
            if(SyncHandler._2D_get1(syncmessenger.mHandler) == null)
                break MISSING_BLOCK_LABEL_111;
            messenger1 = obj;
            Slog.wtf("AsyncChannel", "mResultMsg should be null here");
            messenger1 = obj;
            SyncHandler._2D_set0(syncmessenger.mHandler, null);
            messenger1 = obj;
            messenger.send(message);
            messenger1 = obj;
            SyncHandler._2D_get0(syncmessenger.mHandler).wait();
            messenger1 = obj;
            messenger = SyncHandler._2D_get1(syncmessenger.mHandler);
            messenger1 = messenger;
            SyncHandler._2D_set0(syncmessenger.mHandler, null);
            messenger3 = messenger;
            messenger2 = messenger;
            obj2;
            JVM INSTR monitorexit ;
            messenger3 = messenger;
_L2:
            syncmessenger.recycle();
            return messenger3;
            messenger;
            messenger3 = messenger1;
            messenger2 = messenger1;
            obj2;
            JVM INSTR monitorexit ;
            messenger3 = messenger1;
            messenger2 = messenger1;
            try
            {
                throw messenger;
            }
            // Misplaced declaration of an exception variable
            catch(Messenger messenger)
            {
                Slog.e("AsyncChannel", "error in sendMessageSynchronously", messenger);
            }
            // Misplaced declaration of an exception variable
            catch(Messenger messenger)
            {
                Slog.e("AsyncChannel", "error in sendMessageSynchronously", messenger);
                messenger3 = messenger2;
            }
            if(true) goto _L2; else goto _L1
_L1:
        }

        private static int sCount = 0;
        private static Stack sStack = new Stack();
        private SyncHandler mHandler;
        private HandlerThread mHandlerThread;
        private Messenger mMessenger;


        private SyncMessenger()
        {
        }
    }

    private class SyncMessenger.SyncHandler extends Handler
    {

        static Object _2D_get0(SyncMessenger.SyncHandler synchandler)
        {
            return synchandler.mLockObject;
        }

        static Message _2D_get1(SyncMessenger.SyncHandler synchandler)
        {
            return synchandler.mResultMsg;
        }

        static Message _2D_set0(SyncMessenger.SyncHandler synchandler, Message message)
        {
            synchandler.mResultMsg = message;
            return message;
        }

        public void handleMessage(Message message)
        {
            Message message1;
            message1 = Message.obtain();
            message1.copyFrom(message);
            message = ((Message) (mLockObject));
            message;
            JVM INSTR monitorenter ;
            mResultMsg = message1;
            mLockObject.notify();
            message;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        private Object mLockObject;
        private Message mResultMsg;
        final SyncMessenger this$1;

        private SyncMessenger.SyncHandler(Looper looper)
        {
            this$1 = SyncMessenger.this;
            super(looper);
            mLockObject = new Object();
        }

        SyncMessenger.SyncHandler(Looper looper, SyncMessenger.SyncHandler synchandler)
        {
            this(looper);
        }
    }


    static Messenger _2D_set0(AsyncChannel asyncchannel, Messenger messenger)
    {
        asyncchannel.mDstMessenger = messenger;
        return messenger;
    }

    static void _2D_wrap0(AsyncChannel asyncchannel, int i)
    {
        asyncchannel.replyDisconnected(i);
    }

    static void _2D_wrap1(AsyncChannel asyncchannel, int i)
    {
        asyncchannel.replyHalfConnected(i);
    }

    public AsyncChannel()
    {
    }

    protected static String cmdToString(int i)
    {
        i -= 0x11000;
        if(i >= 0 && i < sCmdToString.length)
            return sCmdToString[i];
        else
            return null;
    }

    private boolean linkToDeathMonitor()
    {
        if(mConnection == null && mDeathMonitor == null)
        {
            mDeathMonitor = new DeathMonitor();
            try
            {
                mDstMessenger.getBinder().linkToDeath(mDeathMonitor, 0);
            }
            catch(RemoteException remoteexception)
            {
                mDeathMonitor = null;
                return false;
            }
        }
        return true;
    }

    private static void log(String s)
    {
        Slog.d("AsyncChannel", s);
    }

    private void replyDisconnected(int i)
    {
        if(mSrcHandler == null)
        {
            return;
        } else
        {
            Message message = mSrcHandler.obtainMessage(0x11004);
            message.arg1 = i;
            message.obj = this;
            message.replyTo = mDstMessenger;
            mSrcHandler.sendMessage(message);
            return;
        }
    }

    private void replyHalfConnected(int i)
    {
        Message message = mSrcHandler.obtainMessage(0x11000);
        message.arg1 = i;
        message.obj = this;
        message.replyTo = mDstMessenger;
        if(!linkToDeathMonitor())
            message.arg1 = 1;
        mSrcHandler.sendMessage(message);
    }

    public void connect(Context context, Handler handler, Handler handler1)
    {
        connect(context, handler, new Messenger(handler1));
    }

    public void connect(Context context, Handler handler, Messenger messenger)
    {
        connected(context, handler, messenger);
        replyHalfConnected(0);
    }

    public void connect(Context context, Handler handler, Class class1)
    {
        connect(context, handler, class1.getPackage().getName(), class1.getName());
    }

    public void connect(Context context, Handler handler, String s, String s1)
    {
        (new Thread(new _cls1ConnectAsync(context, handler, s, s1))).start();
    }

    public void connect(AsyncService asyncservice, Messenger messenger)
    {
        connect(((Context) (asyncservice)), asyncservice.getHandler(), messenger);
    }

    public int connectSrcHandlerToPackageSync(Context context, Handler handler, String s, String s1)
    {
        int i = 1;
        mConnection = new AsyncChannelConnection();
        mSrcContext = context;
        mSrcHandler = handler;
        mSrcMessenger = new Messenger(handler);
        mDstMessenger = null;
        handler = new Intent("android.intent.action.MAIN");
        handler.setClassName(s, s1);
        if(context.bindService(handler, mConnection, 1))
            i = 0;
        return i;
    }

    public int connectSync(Context context, Handler handler, Handler handler1)
    {
        return connectSync(context, handler, new Messenger(handler1));
    }

    public int connectSync(Context context, Handler handler, Messenger messenger)
    {
        connected(context, handler, messenger);
        return 0;
    }

    public void connected(Context context, Handler handler, Messenger messenger)
    {
        mSrcContext = context;
        mSrcHandler = handler;
        mSrcMessenger = new Messenger(mSrcHandler);
        mDstMessenger = messenger;
    }

    public void disconnect()
    {
        if(mConnection != null && mSrcContext != null)
        {
            mSrcContext.unbindService(mConnection);
            mConnection = null;
        }
        try
        {
            Message message = Message.obtain();
            message.what = 0x11004;
            message.replyTo = mSrcMessenger;
            mDstMessenger.send(message);
        }
        catch(Exception exception) { }
        replyDisconnected(0);
        mSrcHandler = null;
        if(mConnection == null && mDstMessenger != null && mDeathMonitor != null)
        {
            mDstMessenger.getBinder().unlinkToDeath(mDeathMonitor, 0);
            mDeathMonitor = null;
        }
    }

    public void disconnected()
    {
        mSrcContext = null;
        mSrcHandler = null;
        mSrcMessenger = null;
        mDstMessenger = null;
        mDeathMonitor = null;
        mConnection = null;
    }

    public int fullyConnectSync(Context context, Handler handler, Handler handler1)
    {
        int i = connectSync(context, handler, handler1);
        int j = i;
        if(i == 0)
            j = sendMessageSynchronously(0x11001).arg1;
        return j;
    }

    public void replyToMessage(Message message, int i)
    {
        Message message1 = Message.obtain();
        message1.what = i;
        replyToMessage(message, message1);
    }

    public void replyToMessage(Message message, int i, int j)
    {
        Message message1 = Message.obtain();
        message1.what = i;
        message1.arg1 = j;
        replyToMessage(message, message1);
    }

    public void replyToMessage(Message message, int i, int j, int k)
    {
        Message message1 = Message.obtain();
        message1.what = i;
        message1.arg1 = j;
        message1.arg2 = k;
        replyToMessage(message, message1);
    }

    public void replyToMessage(Message message, int i, int j, int k, Object obj)
    {
        Message message1 = Message.obtain();
        message1.what = i;
        message1.arg1 = j;
        message1.arg2 = k;
        message1.obj = obj;
        replyToMessage(message, message1);
    }

    public void replyToMessage(Message message, int i, Object obj)
    {
        Message message1 = Message.obtain();
        message1.what = i;
        message1.obj = obj;
        replyToMessage(message, message1);
    }

    public void replyToMessage(Message message, Message message1)
    {
        message1.replyTo = mSrcMessenger;
        message.replyTo.send(message1);
_L1:
        return;
        message;
        log((new StringBuilder()).append("TODO: handle replyToMessage RemoteException").append(message).toString());
        message.printStackTrace();
          goto _L1
    }

    public void sendMessage(int i)
    {
        Message message = Message.obtain();
        message.what = i;
        sendMessage(message);
    }

    public void sendMessage(int i, int j)
    {
        Message message = Message.obtain();
        message.what = i;
        message.arg1 = j;
        sendMessage(message);
    }

    public void sendMessage(int i, int j, int k)
    {
        Message message = Message.obtain();
        message.what = i;
        message.arg1 = j;
        message.arg2 = k;
        sendMessage(message);
    }

    public void sendMessage(int i, int j, int k, Object obj)
    {
        Message message = Message.obtain();
        message.what = i;
        message.arg1 = j;
        message.arg2 = k;
        message.obj = obj;
        sendMessage(message);
    }

    public void sendMessage(int i, Object obj)
    {
        Message message = Message.obtain();
        message.what = i;
        message.obj = obj;
        sendMessage(message);
    }

    public void sendMessage(Message message)
    {
        message.replyTo = mSrcMessenger;
        mDstMessenger.send(message);
_L1:
        return;
        message;
        replyDisconnected(2);
          goto _L1
    }

    public Message sendMessageSynchronously(int i)
    {
        Message message = Message.obtain();
        message.what = i;
        return sendMessageSynchronously(message);
    }

    public Message sendMessageSynchronously(int i, int j)
    {
        Message message = Message.obtain();
        message.what = i;
        message.arg1 = j;
        return sendMessageSynchronously(message);
    }

    public Message sendMessageSynchronously(int i, int j, int k)
    {
        Message message = Message.obtain();
        message.what = i;
        message.arg1 = j;
        message.arg2 = k;
        return sendMessageSynchronously(message);
    }

    public Message sendMessageSynchronously(int i, int j, int k, Object obj)
    {
        Message message = Message.obtain();
        message.what = i;
        message.arg1 = j;
        message.arg2 = k;
        message.obj = obj;
        return sendMessageSynchronously(message);
    }

    public Message sendMessageSynchronously(int i, Object obj)
    {
        Message message = Message.obtain();
        message.what = i;
        message.obj = obj;
        return sendMessageSynchronously(message);
    }

    public Message sendMessageSynchronously(Message message)
    {
        return SyncMessenger._2D_wrap0(mDstMessenger, message);
    }

    private static final int BASE = 0x11000;
    public static final int CMD_CHANNEL_DISCONNECT = 0x11003;
    public static final int CMD_CHANNEL_DISCONNECTED = 0x11004;
    public static final int CMD_CHANNEL_FULLY_CONNECTED = 0x11002;
    public static final int CMD_CHANNEL_FULL_CONNECTION = 0x11001;
    public static final int CMD_CHANNEL_HALF_CONNECTED = 0x11000;
    private static final int CMD_TO_STRING_COUNT = 5;
    private static final boolean DBG = false;
    public static final int STATUS_BINDING_UNSUCCESSFUL = 1;
    public static final int STATUS_FULL_CONNECTION_REFUSED_ALREADY_CONNECTED = 3;
    public static final int STATUS_REMOTE_DISCONNECTION = 4;
    public static final int STATUS_SEND_UNSUCCESSFUL = 2;
    public static final int STATUS_SUCCESSFUL = 0;
    private static final String TAG = "AsyncChannel";
    private static String sCmdToString[];
    private AsyncChannelConnection mConnection;
    private DeathMonitor mDeathMonitor;
    private Messenger mDstMessenger;
    private Context mSrcContext;
    private Handler mSrcHandler;
    private Messenger mSrcMessenger;

    static 
    {
        sCmdToString = new String[5];
        sCmdToString[0] = "CMD_CHANNEL_HALF_CONNECTED";
        sCmdToString[1] = "CMD_CHANNEL_FULL_CONNECTION";
        sCmdToString[2] = "CMD_CHANNEL_FULLY_CONNECTED";
        sCmdToString[3] = "CMD_CHANNEL_DISCONNECT";
        sCmdToString[4] = "CMD_CHANNEL_DISCONNECTED";
    }
}
