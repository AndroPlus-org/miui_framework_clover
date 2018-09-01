// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.usb;

import android.util.Log;
import com.android.internal.util.Preconditions;
import dalvik.system.CloseGuard;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;

// Referenced classes of package android.hardware.usb:
//            UsbEndpoint, UsbDeviceConnection

public class UsbRequest
{

    public UsbRequest()
    {
    }

    private native boolean native_cancel();

    private native void native_close();

    private native int native_dequeue_array(byte abyte0[], int i, boolean flag);

    private native int native_dequeue_direct();

    private native boolean native_init(UsbDeviceConnection usbdeviceconnection, int i, int j, int k, int l);

    private native boolean native_queue(ByteBuffer bytebuffer, int i, int j);

    private native boolean native_queue_array(byte abyte0[], int i, boolean flag);

    private native boolean native_queue_direct(ByteBuffer bytebuffer, int i, boolean flag);

    public boolean cancel()
    {
        return native_cancel();
    }

    public void close()
    {
        if(mNativeContext != 0L)
        {
            mEndpoint = null;
            mConnection = null;
            native_close();
            mCloseGuard.close();
        }
    }

    void dequeue(boolean flag)
    {
        boolean flag1;
        Object obj;
        int i;
        if(mEndpoint.getDirection() == 0)
            flag1 = true;
        else
            flag1 = false;
        obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(!mIsUsingNewQueue) goto _L2; else goto _L1
_L1:
        i = native_dequeue_direct();
        mIsUsingNewQueue = false;
        if(mBuffer != null) goto _L4; else goto _L3
_L3:
        mBuffer = null;
        mLength = 0;
        obj;
        JVM INSTR monitorexit ;
        return;
_L4:
        if(mTempBuffer != null)
            break MISSING_BLOCK_LABEL_97;
        mBuffer.position(mBuffer.position() + i);
          goto _L3
        Object obj1;
        obj1;
        throw obj1;
        mTempBuffer.limit(i);
        if(!flag1)
            break MISSING_BLOCK_LABEL_137;
        mBuffer.position(mBuffer.position() + i);
_L5:
        mTempBuffer = null;
          goto _L3
        mBuffer.put(mTempBuffer);
          goto _L5
        obj1;
        mTempBuffer = null;
        throw obj1;
_L2:
        if(!mBuffer.isDirect()) goto _L7; else goto _L6
_L6:
        i = native_dequeue_direct();
_L9:
        if(i < 0) goto _L3; else goto _L8
_L8:
        i = Math.min(i, mLength);
        mBuffer.position(i);
          goto _L3
        obj1;
        if(!flag)
            break MISSING_BLOCK_LABEL_297;
        StringBuilder stringbuilder = JVM INSTR new #117 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.e("UsbRequest", stringbuilder.append("Buffer ").append(mBuffer).append(" does not have enough space to read ").append(i).append(" bytes").toString(), ((Throwable) (obj1)));
        obj1 = JVM INSTR new #146 <Class BufferOverflowException>;
        ((BufferOverflowException) (obj1)).BufferOverflowException();
        throw obj1;
_L7:
        i = native_dequeue_array(mBuffer.array(), mLength, flag1);
          goto _L9
        throw obj1;
          goto _L3
    }

    protected void finalize()
        throws Throwable
    {
        if(mCloseGuard != null)
            mCloseGuard.warnIfOpen();
        close();
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public Object getClientData()
    {
        return mClientData;
    }

    public UsbEndpoint getEndpoint()
    {
        return mEndpoint;
    }

    public boolean initialize(UsbDeviceConnection usbdeviceconnection, UsbEndpoint usbendpoint)
    {
        mEndpoint = usbendpoint;
        mConnection = (UsbDeviceConnection)Preconditions.checkNotNull(usbdeviceconnection, "connection");
        boolean flag = native_init(usbdeviceconnection, usbendpoint.getAddress(), usbendpoint.getAttributes(), usbendpoint.getMaxPacketSize(), usbendpoint.getInterval());
        if(flag)
            mCloseGuard.open("close");
        return flag;
    }

    public boolean queue(ByteBuffer bytebuffer)
    {
        boolean flag;
        boolean flag1;
        Object obj;
        flag = true;
        if(mNativeContext != 0L)
            flag1 = true;
        else
            flag1 = false;
        Preconditions.checkState(flag1, "request is not initialized");
        Preconditions.checkState(mIsUsingNewQueue ^ true, "this request is currently queued");
        if(mEndpoint.getDirection() == 0)
            flag1 = true;
        else
            flag1 = false;
        obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        mBuffer = bytebuffer;
        if(bytebuffer != null) goto _L2; else goto _L1
_L1:
        mIsUsingNewQueue = true;
        flag1 = native_queue(null, 0, 0);
_L4:
        obj;
        JVM INSTR monitorexit ;
        if(!flag1)
        {
            mIsUsingNewQueue = false;
            mTempBuffer = null;
            mBuffer = null;
        }
        return flag1;
_L2:
        Preconditions.checkArgumentInRange(bytebuffer.remaining(), 0, 16384, "number of remaining bytes");
        if(bytebuffer.isReadOnly())
            flag = flag1;
        Preconditions.checkArgument(flag, "buffer can not be read-only when receiving data");
        ByteBuffer bytebuffer1 = bytebuffer;
        if(bytebuffer.isDirect())
            break MISSING_BLOCK_LABEL_206;
        mTempBuffer = ByteBuffer.allocateDirect(mBuffer.remaining());
        if(!flag1)
            break MISSING_BLOCK_LABEL_200;
        mBuffer.mark();
        mTempBuffer.put(mBuffer);
        mTempBuffer.flip();
        mBuffer.reset();
        bytebuffer1 = mTempBuffer;
        mIsUsingNewQueue = true;
        flag1 = native_queue(bytebuffer1, bytebuffer1.position(), bytebuffer1.remaining());
        if(true) goto _L4; else goto _L3
_L3:
        bytebuffer;
        throw bytebuffer;
    }

    public boolean queue(ByteBuffer bytebuffer, int i)
    {
        boolean flag;
        Object obj;
        if(mEndpoint.getDirection() == 0)
            flag = true;
        else
            flag = false;
        obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        mBuffer = bytebuffer;
        mLength = i;
        if(!bytebuffer.isDirect())
            break MISSING_BLOCK_LABEL_70;
        flag = native_queue_direct(bytebuffer, i, flag);
_L1:
        if(flag)
            break MISSING_BLOCK_LABEL_60;
        mBuffer = null;
        mLength = 0;
        obj;
        JVM INSTR monitorexit ;
        return flag;
label0:
        {
            if(!bytebuffer.hasArray())
                break label0;
            flag = native_queue_array(bytebuffer.array(), i, flag);
        }
          goto _L1
        bytebuffer = JVM INSTR new #76  <Class IllegalArgumentException>;
        bytebuffer.IllegalArgumentException("buffer is not direct and has no array");
        throw bytebuffer;
        bytebuffer;
        obj;
        JVM INSTR monitorexit ;
        throw bytebuffer;
    }

    public void setClientData(Object obj)
    {
        mClientData = obj;
    }

    private static final int MAX_USBFS_BUFFER_SIZE = 16384;
    private static final String TAG = "UsbRequest";
    private ByteBuffer mBuffer;
    private Object mClientData;
    private final CloseGuard mCloseGuard = CloseGuard.get();
    private UsbDeviceConnection mConnection;
    private UsbEndpoint mEndpoint;
    private boolean mIsUsingNewQueue;
    private int mLength;
    private final Object mLock = new Object();
    private long mNativeContext;
    private ByteBuffer mTempBuffer;
}
