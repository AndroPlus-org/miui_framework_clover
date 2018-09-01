// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.usb;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.ParcelFileDescriptor;
import com.android.internal.util.Preconditions;
import dalvik.system.CloseGuard;
import java.io.FileDescriptor;
import java.util.concurrent.TimeoutException;

// Referenced classes of package android.hardware.usb:
//            UsbEndpoint, UsbInterface, UsbRequest, UsbConfiguration, 
//            UsbDevice

public class UsbDeviceConnection
{

    public UsbDeviceConnection(UsbDevice usbdevice)
    {
        mDevice = usbdevice;
    }

    private static void checkBounds(byte abyte0[], int i, int j)
    {
        int k;
        if(abyte0 != null)
            k = abyte0.length;
        else
            k = 0;
        while(j < 0 || i < 0 || i + j > k) 
            throw new IllegalArgumentException("Buffer start or length out of bounds.");
    }

    private native int native_bulk_request(int i, byte abyte0[], int j, int k, int l);

    private native boolean native_claim_interface(int i, boolean flag);

    private native void native_close();

    private native int native_control_request(int i, int j, int k, int l, byte abyte0[], int i1, int j1, 
            int k1);

    private native byte[] native_get_desc();

    private native int native_get_fd();

    private native String native_get_serial();

    private native boolean native_open(String s, FileDescriptor filedescriptor);

    private native boolean native_release_interface(int i);

    private native UsbRequest native_request_wait(long l)
        throws TimeoutException;

    private native boolean native_reset_device();

    private native boolean native_set_configuration(int i);

    private native boolean native_set_interface(int i, int j);

    public int bulkTransfer(UsbEndpoint usbendpoint, byte abyte0[], int i, int j)
    {
        return bulkTransfer(usbendpoint, abyte0, 0, i, j);
    }

    public int bulkTransfer(UsbEndpoint usbendpoint, byte abyte0[], int i, int j, int k)
    {
        checkBounds(abyte0, i, j);
        return native_bulk_request(usbendpoint.getAddress(), abyte0, i, j, k);
    }

    public boolean claimInterface(UsbInterface usbinterface, boolean flag)
    {
        return native_claim_interface(usbinterface.getId(), flag);
    }

    public void close()
    {
        if(mNativeContext != 0L)
        {
            native_close();
            mCloseGuard.close();
        }
    }

    public int controlTransfer(int i, int j, int k, int l, byte abyte0[], int i1, int j1)
    {
        return controlTransfer(i, j, k, l, abyte0, 0, i1, j1);
    }

    public int controlTransfer(int i, int j, int k, int l, byte abyte0[], int i1, int j1, 
            int k1)
    {
        checkBounds(abyte0, i1, j1);
        return native_control_request(i, j, k, l, abyte0, i1, j1, k1);
    }

    protected void finalize()
        throws Throwable
    {
        if(mCloseGuard != null)
            mCloseGuard.warnIfOpen();
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public Context getContext()
    {
        return mContext;
    }

    public int getFileDescriptor()
    {
        return native_get_fd();
    }

    public byte[] getRawDescriptors()
    {
        return native_get_desc();
    }

    public String getSerial()
    {
        return native_get_serial();
    }

    boolean open(String s, ParcelFileDescriptor parcelfiledescriptor, Context context)
    {
        mContext = context.getApplicationContext();
        boolean flag = native_open(s, parcelfiledescriptor.getFileDescriptor());
        if(flag)
            mCloseGuard.open("close");
        return flag;
    }

    public boolean releaseInterface(UsbInterface usbinterface)
    {
        return native_release_interface(usbinterface.getId());
    }

    public UsbRequest requestWait()
    {
        UsbRequest usbrequest = null;
        UsbRequest usbrequest1 = native_request_wait(-1L);
        usbrequest = usbrequest1;
_L1:
        if(usbrequest != null)
        {
            TimeoutException timeoutexception;
            boolean flag;
            if(mContext.getApplicationInfo().targetSdkVersion >= 26)
                flag = true;
            else
                flag = false;
            usbrequest.dequeue(flag);
        }
        return usbrequest;
        timeoutexception;
          goto _L1
    }

    public UsbRequest requestWait(long l)
        throws TimeoutException
    {
        UsbRequest usbrequest = native_request_wait(Preconditions.checkArgumentNonnegative(l, "timeout"));
        if(usbrequest != null)
            usbrequest.dequeue(true);
        return usbrequest;
    }

    public boolean resetDevice()
    {
        return native_reset_device();
    }

    public boolean setConfiguration(UsbConfiguration usbconfiguration)
    {
        return native_set_configuration(usbconfiguration.getId());
    }

    public boolean setInterface(UsbInterface usbinterface)
    {
        return native_set_interface(usbinterface.getId(), usbinterface.getAlternateSetting());
    }

    private static final String TAG = "UsbDeviceConnection";
    private final CloseGuard mCloseGuard = CloseGuard.get();
    private Context mContext;
    private final UsbDevice mDevice;
    private long mNativeContext;
}
