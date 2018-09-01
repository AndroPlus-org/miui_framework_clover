// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.drm;

import android.os.ParcelFileDescriptor;
import android.system.*;
import android.util.Log;
import java.io.*;
import java.net.UnknownServiceException;
import java.util.Arrays;
import libcore.io.IoBridge;
import libcore.io.Streams;

// Referenced classes of package android.drm:
//            DrmManagerClient, DrmConvertedStatus

public class DrmOutputStream extends OutputStream
{

    public DrmOutputStream(DrmManagerClient drmmanagerclient, ParcelFileDescriptor parcelfiledescriptor, String s)
        throws IOException
    {
        mSessionId = -1;
        mClient = drmmanagerclient;
        mPfd = parcelfiledescriptor;
        mFd = parcelfiledescriptor.getFileDescriptor();
        mSessionId = mClient.openConvertSession(s);
        if(mSessionId == -1)
            throw new UnknownServiceException((new StringBuilder()).append("Failed to open DRM session for ").append(s).toString());
        else
            return;
    }

    public void close()
        throws IOException
    {
        if(mSessionId == -1)
            Log.w("DrmOutputStream", "Closing stream without finishing");
        mPfd.close();
    }

    public void finish()
        throws IOException
    {
        DrmConvertedStatus drmconvertedstatus = mClient.closeConvertSession(mSessionId);
        if(drmconvertedstatus.statusCode == 1)
        {
            try
            {
                Os.lseek(mFd, drmconvertedstatus.offset, OsConstants.SEEK_SET);
            }
            catch(ErrnoException errnoexception)
            {
                errnoexception.rethrowAsIOException();
            }
            IoBridge.write(mFd, drmconvertedstatus.convertedData, 0, drmconvertedstatus.convertedData.length);
            mSessionId = -1;
            return;
        } else
        {
            throw new IOException((new StringBuilder()).append("Unexpected DRM status: ").append(drmconvertedstatus.statusCode).toString());
        }
    }

    public void write(int i)
        throws IOException
    {
        Streams.writeSingleByte(this, i);
    }

    public void write(byte abyte0[], int i, int j)
        throws IOException
    {
        Arrays.checkOffsetAndCount(abyte0.length, i, j);
        if(j != abyte0.length)
        {
            byte abyte1[] = new byte[j];
            System.arraycopy(abyte0, i, abyte1, 0, j);
            abyte0 = abyte1;
        }
        abyte0 = mClient.convertData(mSessionId, abyte0);
        if(((DrmConvertedStatus) (abyte0)).statusCode == 1)
        {
            IoBridge.write(mFd, ((DrmConvertedStatus) (abyte0)).convertedData, 0, ((DrmConvertedStatus) (abyte0)).convertedData.length);
            return;
        } else
        {
            throw new IOException((new StringBuilder()).append("Unexpected DRM status: ").append(((DrmConvertedStatus) (abyte0)).statusCode).toString());
        }
    }

    private static final String TAG = "DrmOutputStream";
    private final DrmManagerClient mClient;
    private final FileDescriptor mFd;
    private final ParcelFileDescriptor mPfd;
    private int mSessionId;
}
