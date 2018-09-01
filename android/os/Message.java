// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.util.TimeUtils;
import android.util.proto.ProtoOutputStream;

// Referenced classes of package android.os:
//            Parcelable, Bundle, Parcel, Messenger, 
//            Handler, SystemClock

public final class Message
    implements Parcelable
{

    static void _2D_wrap0(Message message, Parcel parcel)
    {
        message.readFromParcel(parcel);
    }

    public Message()
    {
        sendingUid = -1;
    }

    public static Message obtain()
    {
        Object obj1 = sPoolSync;
        obj1;
        JVM INSTR monitorenter ;
        Message message;
        if(sPool == null)
            break MISSING_BLOCK_LABEL_45;
        message = sPool;
        sPool = message.next;
        message.next = null;
        message.flags = 0;
        sPoolSize--;
        return message;
        obj1;
        JVM INSTR monitorexit ;
        return new Message();
        Exception exception;
        exception;
        throw exception;
    }

    public static Message obtain(Handler handler)
    {
        Message message = obtain();
        message.target = handler;
        return message;
    }

    public static Message obtain(Handler handler, int i)
    {
        Message message = obtain();
        message.target = handler;
        message.what = i;
        return message;
    }

    public static Message obtain(Handler handler, int i, int j, int k)
    {
        Message message = obtain();
        message.target = handler;
        message.what = i;
        message.arg1 = j;
        message.arg2 = k;
        return message;
    }

    public static Message obtain(Handler handler, int i, int j, int k, Object obj1)
    {
        Message message = obtain();
        message.target = handler;
        message.what = i;
        message.arg1 = j;
        message.arg2 = k;
        message.obj = obj1;
        return message;
    }

    public static Message obtain(Handler handler, int i, Object obj1)
    {
        Message message = obtain();
        message.target = handler;
        message.what = i;
        message.obj = obj1;
        return message;
    }

    public static Message obtain(Handler handler, Runnable runnable)
    {
        Message message = obtain();
        message.target = handler;
        message.callback = runnable;
        return message;
    }

    public static Message obtain(Message message)
    {
        Message message1 = obtain();
        message1.what = message.what;
        message1.arg1 = message.arg1;
        message1.arg2 = message.arg2;
        message1.obj = message.obj;
        message1.replyTo = message.replyTo;
        message1.sendingUid = message.sendingUid;
        if(message.data != null)
            message1.data = new Bundle(message.data);
        message1.target = message.target;
        message1.callback = message.callback;
        return message1;
    }

    private void readFromParcel(Parcel parcel)
    {
        what = parcel.readInt();
        arg1 = parcel.readInt();
        arg2 = parcel.readInt();
        if(parcel.readInt() != 0)
            obj = parcel.readParcelable(getClass().getClassLoader());
        when = parcel.readLong();
        data = parcel.readBundle();
        replyTo = Messenger.readMessengerOrNullFromParcel(parcel);
        sendingUid = parcel.readInt();
    }

    public static void updateCheckRecycle(int i)
    {
        if(i < 21)
            gCheckRecycle = false;
    }

    public void copyFrom(Message message)
    {
        flags = message.flags & -2;
        what = message.what;
        arg1 = message.arg1;
        arg2 = message.arg2;
        obj = message.obj;
        replyTo = message.replyTo;
        sendingUid = message.sendingUid;
        if(message.data != null)
            data = (Bundle)message.data.clone();
        else
            data = null;
    }

    public int describeContents()
    {
        return 0;
    }

    public Runnable getCallback()
    {
        return callback;
    }

    public Bundle getData()
    {
        if(data == null)
            data = new Bundle();
        return data;
    }

    public Handler getTarget()
    {
        return target;
    }

    public long getWhen()
    {
        return when;
    }

    public boolean isAsynchronous()
    {
        boolean flag = false;
        if((flags & 2) != 0)
            flag = true;
        return flag;
    }

    boolean isInUse()
    {
        boolean flag = true;
        if((flags & 1) != 1)
            flag = false;
        return flag;
    }

    void markInUse()
    {
        flags = flags | 1;
    }

    public Bundle peekData()
    {
        return data;
    }

    public void recycle()
    {
        if(isInUse())
        {
            if(gCheckRecycle)
                throw new IllegalStateException("This message cannot be recycled because it is still in use.");
            else
                return;
        } else
        {
            recycleUnchecked();
            return;
        }
    }

    void recycleUnchecked()
    {
        flags = 1;
        what = 0;
        arg1 = 0;
        arg2 = 0;
        obj = null;
        replyTo = null;
        sendingUid = -1;
        when = 0L;
        target = null;
        callback = null;
        data = null;
        Object obj1 = sPoolSync;
        obj1;
        JVM INSTR monitorenter ;
        if(sPoolSize < 50)
        {
            next = sPool;
            sPool = this;
            sPoolSize++;
        }
        obj1;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void sendToTarget()
    {
        target.sendMessage(this);
    }

    public void setAsynchronous(boolean flag)
    {
        if(flag)
            flags = flags | 2;
        else
            flags = flags & -3;
    }

    public void setData(Bundle bundle)
    {
        data = bundle;
    }

    public void setTarget(Handler handler)
    {
        target = handler;
    }

    public String toString()
    {
        return toString(SystemClock.uptimeMillis());
    }

    String toString(long l)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("{ when=");
        TimeUtils.formatDuration(when - l, stringbuilder);
        if(target != null)
        {
            if(callback != null)
            {
                stringbuilder.append(" callback=");
                stringbuilder.append(callback.getClass().getName());
            } else
            {
                stringbuilder.append(" what=");
                stringbuilder.append(what);
            }
            if(arg1 != 0)
            {
                stringbuilder.append(" arg1=");
                stringbuilder.append(arg1);
            }
            if(arg2 != 0)
            {
                stringbuilder.append(" arg2=");
                stringbuilder.append(arg2);
            }
            if(obj != null)
            {
                stringbuilder.append(" obj=");
                stringbuilder.append(obj);
            }
            stringbuilder.append(" target=");
            stringbuilder.append(target.getClass().getName());
        } else
        {
            stringbuilder.append(" barrier=");
            stringbuilder.append(arg1);
        }
        stringbuilder.append(" }");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if(callback != null)
            throw new RuntimeException("Can't marshal callbacks across processes.");
        parcel.writeInt(what);
        parcel.writeInt(arg1);
        parcel.writeInt(arg2);
        if(obj != null)
            try
            {
                Parcelable parcelable = (Parcelable)obj;
                parcel.writeInt(1);
                parcel.writeParcelable(parcelable, i);
            }
            // Misplaced declaration of an exception variable
            catch(Parcel parcel)
            {
                throw new RuntimeException("Can't marshal non-Parcelable objects across processes.");
            }
        else
            parcel.writeInt(0);
        parcel.writeLong(when);
        parcel.writeBundle(data);
        Messenger.writeMessengerOrNullToParcel(replyTo, parcel);
        parcel.writeInt(sendingUid);
    }

    void writeToProto(ProtoOutputStream protooutputstream, long l)
    {
        l = protooutputstream.start(l);
        protooutputstream.write(0x10400000001L, when);
        if(target != null)
        {
            if(callback != null)
                protooutputstream.write(0x10e00000002L, callback.getClass().getName());
            else
                protooutputstream.write(0x10300000003L, what);
            if(arg1 != 0)
                protooutputstream.write(0x10300000004L, arg1);
            if(arg2 != 0)
                protooutputstream.write(0x10300000005L, arg2);
            if(obj != null)
                protooutputstream.write(0x10e00000006L, obj.toString());
            protooutputstream.write(0x10e00000007L, target.getClass().getName());
        } else
        {
            protooutputstream.write(0x10300000008L, arg1);
        }
        protooutputstream.end(l);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public Message createFromParcel(Parcel parcel)
        {
            Message message = Message.obtain();
            Message._2D_wrap0(message, parcel);
            return message;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public Message[] newArray(int i)
        {
            return new Message[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    static final int FLAGS_TO_CLEAR_ON_COPY_FROM = 1;
    static final int FLAG_ASYNCHRONOUS = 2;
    static final int FLAG_IN_USE = 1;
    private static final int MAX_POOL_SIZE = 50;
    private static boolean gCheckRecycle = true;
    private static Message sPool;
    private static int sPoolSize = 0;
    private static final Object sPoolSync = new Object();
    public int arg1;
    public int arg2;
    Runnable callback;
    Bundle data;
    int flags;
    Message next;
    public Object obj;
    public Messenger replyTo;
    public int sendingUid;
    Handler target;
    public int what;
    long when;

}
