// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.content.Context;
import android.os.storage.StorageManager;
import android.system.*;
import android.util.Slog;
import java.io.*;
import libcore.io.IoUtils;

// Referenced classes of package android.os:
//            ProxyFileDescriptorCallback, ParcelFileDescriptor

public class RevocableFileDescriptor
{

    static FileDescriptor _2D_get0(RevocableFileDescriptor revocablefiledescriptor)
    {
        return revocablefiledescriptor.mInner;
    }

    static boolean _2D_get1(RevocableFileDescriptor revocablefiledescriptor)
    {
        return revocablefiledescriptor.mRevoked;
    }

    static boolean _2D_set0(RevocableFileDescriptor revocablefiledescriptor, boolean flag)
    {
        revocablefiledescriptor.mRevoked = flag;
        return flag;
    }

    public RevocableFileDescriptor()
    {
        mCallback = new ProxyFileDescriptorCallback() {

            private void checkRevoked()
                throws ErrnoException
            {
                if(RevocableFileDescriptor._2D_get1(RevocableFileDescriptor.this))
                    throw new ErrnoException("RevocableFileDescriptor", OsConstants.EPERM);
                else
                    return;
            }

            public void onFsync()
                throws ErrnoException
            {
                Slog.v("RevocableFileDescriptor", "onFsync()");
                checkRevoked();
                Os.fsync(RevocableFileDescriptor._2D_get0(RevocableFileDescriptor.this));
            }

            public long onGetSize()
                throws ErrnoException
            {
                checkRevoked();
                return Os.fstat(RevocableFileDescriptor._2D_get0(RevocableFileDescriptor.this)).st_size;
            }

            public int onRead(long l, int i, byte abyte0[])
                throws ErrnoException
            {
                int j;
                checkRevoked();
                j = 0;
_L2:
                int k;
                k = j;
                if(j >= i)
                    break MISSING_BLOCK_LABEL_49;
                k = Os.pread(RevocableFileDescriptor._2D_get0(RevocableFileDescriptor.this), abyte0, j, i - j, (long)j + l);
                k = j + k;
                return k;
                InterruptedIOException interruptedioexception;
                interruptedioexception;
                j += interruptedioexception.bytesTransferred;
                if(true) goto _L2; else goto _L1
_L1:
            }

            public void onRelease()
            {
                Slog.v("RevocableFileDescriptor", "onRelease()");
                RevocableFileDescriptor._2D_set0(RevocableFileDescriptor.this, true);
                IoUtils.closeQuietly(RevocableFileDescriptor._2D_get0(RevocableFileDescriptor.this));
            }

            public int onWrite(long l, int i, byte abyte0[])
                throws ErrnoException
            {
                int j;
                checkRevoked();
                j = 0;
_L2:
                int k;
                k = j;
                if(j >= i)
                    break MISSING_BLOCK_LABEL_49;
                k = Os.pwrite(RevocableFileDescriptor._2D_get0(RevocableFileDescriptor.this), abyte0, j, i - j, (long)j + l);
                k = j + k;
                return k;
                InterruptedIOException interruptedioexception;
                interruptedioexception;
                j += interruptedioexception.bytesTransferred;
                if(true) goto _L2; else goto _L1
_L1:
            }

            final RevocableFileDescriptor this$0;

            
            {
                this$0 = RevocableFileDescriptor.this;
                super();
            }
        }
;
    }

    public RevocableFileDescriptor(Context context, File file)
        throws IOException
    {
        mCallback = new _cls1();
        try
        {
            init(context, Os.open(file.getAbsolutePath(), OsConstants.O_CREAT | OsConstants.O_RDWR, 448));
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw context.rethrowAsIOException();
        }
    }

    public RevocableFileDescriptor(Context context, FileDescriptor filedescriptor)
        throws IOException
    {
        mCallback = new _cls1();
        init(context, filedescriptor);
    }

    public ParcelFileDescriptor getRevocableFileDescriptor()
    {
        return mOuter;
    }

    public void init(Context context, FileDescriptor filedescriptor)
        throws IOException
    {
        mInner = filedescriptor;
        mOuter = ((StorageManager)context.getSystemService(android/os/storage/StorageManager)).openProxyFileDescriptor(0x30000000, mCallback);
    }

    public boolean isRevoked()
    {
        return mRevoked;
    }

    public void revoke()
    {
        mRevoked = true;
        IoUtils.closeQuietly(mInner);
    }

    private static final boolean DEBUG = true;
    private static final String TAG = "RevocableFileDescriptor";
    private final ProxyFileDescriptorCallback mCallback;
    private FileDescriptor mInner;
    private ParcelFileDescriptor mOuter;
    private volatile boolean mRevoked;
}
