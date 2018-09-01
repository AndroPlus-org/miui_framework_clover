// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.mtp;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.os.*;
import com.android.internal.util.Preconditions;
import dalvik.system.CloseGuard;
import java.io.IOException;

// Referenced classes of package android.mtp:
//            MtpDeviceInfo, MtpObjectInfo, MtpStorageInfo, MtpEvent

public final class MtpDevice
{

    static void _2D_wrap0(MtpDevice mtpdevice, int i)
    {
        mtpdevice.native_discard_event_request(i);
    }

    public MtpDevice(UsbDevice usbdevice)
    {
        mCloseGuard = CloseGuard.get();
        Preconditions.checkNotNull(usbdevice);
        mDevice = usbdevice;
    }

    private native void native_close();

    private native boolean native_delete_object(int i);

    private native void native_discard_event_request(int i);

    private native MtpDeviceInfo native_get_device_info();

    private native byte[] native_get_object(int i, long l);

    private native int[] native_get_object_handles(int i, int j, int k);

    private native MtpObjectInfo native_get_object_info(int i);

    private native long native_get_object_size_long(int i, int j)
        throws IOException;

    private native int native_get_parent(int i);

    private native long native_get_partial_object(int i, long l, long l1, byte abyte0[])
        throws IOException;

    private native int native_get_partial_object_64(int i, long l, long l1, byte abyte0[])
        throws IOException;

    private native int native_get_storage_id(int i);

    private native int[] native_get_storage_ids();

    private native MtpStorageInfo native_get_storage_info(int i);

    private native byte[] native_get_thumbnail(int i);

    private native boolean native_import_file(int i, int j);

    private native boolean native_import_file(int i, String s);

    private native boolean native_open(String s, int i);

    private native MtpEvent native_reap_event_request(int i)
        throws IOException;

    private native boolean native_send_object(int i, long l, int j);

    private native MtpObjectInfo native_send_object_info(MtpObjectInfo mtpobjectinfo);

    private native int native_submit_event_request()
        throws IOException;

    public void close()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mConnection != null)
        {
            mCloseGuard.close();
            native_close();
            mConnection.close();
            mConnection = null;
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean deleteObject(int i)
    {
        return native_delete_object(i);
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

    public int getDeviceId()
    {
        return mDevice.getDeviceId();
    }

    public MtpDeviceInfo getDeviceInfo()
    {
        return native_get_device_info();
    }

    public String getDeviceName()
    {
        return mDevice.getDeviceName();
    }

    public byte[] getObject(int i, int j)
    {
        Preconditions.checkArgumentNonnegative(j, "objectSize should not be negative");
        return native_get_object(i, j);
    }

    public int[] getObjectHandles(int i, int j, int k)
    {
        return native_get_object_handles(i, j, k);
    }

    public MtpObjectInfo getObjectInfo(int i)
    {
        return native_get_object_info(i);
    }

    public long getObjectSizeLong(int i, int j)
        throws IOException
    {
        return native_get_object_size_long(i, j);
    }

    public long getParent(int i)
    {
        return (long)native_get_parent(i);
    }

    public long getPartialObject(int i, long l, long l1, byte abyte0[])
        throws IOException
    {
        return native_get_partial_object(i, l, l1, abyte0);
    }

    public long getPartialObject64(int i, long l, long l1, byte abyte0[])
        throws IOException
    {
        return (long)native_get_partial_object_64(i, l, l1, abyte0);
    }

    public long getStorageId(int i)
    {
        return (long)native_get_storage_id(i);
    }

    public int[] getStorageIds()
    {
        return native_get_storage_ids();
    }

    public MtpStorageInfo getStorageInfo(int i)
    {
        return native_get_storage_info(i);
    }

    public byte[] getThumbnail(int i)
    {
        return native_get_thumbnail(i);
    }

    public boolean importFile(int i, ParcelFileDescriptor parcelfiledescriptor)
    {
        return native_import_file(i, parcelfiledescriptor.getFd());
    }

    public boolean importFile(int i, String s)
    {
        return native_import_file(i, s);
    }

    public boolean open(UsbDeviceConnection usbdeviceconnection)
    {
        boolean flag;
        Context context;
        flag = false;
        context = usbdeviceconnection.getContext();
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag1;
        flag1 = flag;
        if(context == null)
            break MISSING_BLOCK_LABEL_60;
        flag1 = flag;
        if(!((UserManager)context.getSystemService("user")).hasUserRestriction("no_usb_file_transfer"))
            flag1 = native_open(mDevice.getDeviceName(), usbdeviceconnection.getFileDescriptor());
        if(flag1) goto _L2; else goto _L1
_L1:
        usbdeviceconnection.close();
_L4:
        obj;
        JVM INSTR monitorexit ;
        return flag1;
_L2:
        mConnection = usbdeviceconnection;
        mCloseGuard.open("close");
        if(true) goto _L4; else goto _L3
_L3:
        usbdeviceconnection;
        throw usbdeviceconnection;
    }

    public MtpEvent readEvent(CancellationSignal cancellationsignal)
        throws IOException
    {
        final int handle;
        boolean flag = false;
        handle = native_submit_event_request();
        if(handle >= 0)
            flag = true;
        Preconditions.checkState(flag, "Other thread is reading an event.");
        if(cancellationsignal != null)
            cancellationsignal.setOnCancelListener(new android.os.CancellationSignal.OnCancelListener() {

                public void onCancel()
                {
                    MtpDevice._2D_wrap0(MtpDevice.this, handle);
                }

                final MtpDevice this$0;
                final int val$handle;

            
            {
                this$0 = MtpDevice.this;
                handle = i;
                super();
            }
            }
);
        MtpEvent mtpevent = native_reap_event_request(handle);
        if(cancellationsignal != null)
            cancellationsignal.setOnCancelListener(null);
        return mtpevent;
        Exception exception;
        exception;
        if(cancellationsignal != null)
            cancellationsignal.setOnCancelListener(null);
        throw exception;
    }

    public boolean sendObject(int i, long l, ParcelFileDescriptor parcelfiledescriptor)
    {
        return native_send_object(i, l, parcelfiledescriptor.getFd());
    }

    public MtpObjectInfo sendObjectInfo(MtpObjectInfo mtpobjectinfo)
    {
        return native_send_object_info(mtpobjectinfo);
    }

    public String toString()
    {
        return mDevice.getDeviceName();
    }

    private static final String TAG = "MtpDevice";
    private CloseGuard mCloseGuard;
    private UsbDeviceConnection mConnection;
    private final UsbDevice mDevice;
    private final Object mLock = new Object();
    private long mNativeContext;

    static 
    {
        System.loadLibrary("media_jni");
    }
}
