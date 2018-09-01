// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.system.*;
import android.util.Log;
import java.io.*;
import java.nio.ByteOrder;
import java.util.Arrays;
import libcore.io.*;

// Referenced classes of package android.os:
//            ParcelFileDescriptor

public class FileBridge extends Thread
{
    public static class FileBridgeOutputStream extends OutputStream
    {

        private void writeCommandAndBlock(int i, String s)
            throws IOException
        {
            Memory.pokeInt(mTemp, 0, i, ByteOrder.BIG_ENDIAN);
            IoBridge.write(mClient, mTemp, 0, 8);
            if(IoBridge.read(mClient, mTemp, 0, 8) == 8 && Memory.peekInt(mTemp, 0, ByteOrder.BIG_ENDIAN) == i)
                return;
            else
                throw new IOException((new StringBuilder()).append("Failed to execute ").append(s).append(" across bridge").toString());
        }

        public void close()
            throws IOException
        {
            writeCommandAndBlock(3, "close()");
            IoBridge.closeAndSignalBlockedThreads(mClient);
            IoUtils.closeQuietly(mClientPfd);
            return;
            Exception exception;
            exception;
            IoBridge.closeAndSignalBlockedThreads(mClient);
            IoUtils.closeQuietly(mClientPfd);
            throw exception;
        }

        public void fsync()
            throws IOException
        {
            writeCommandAndBlock(2, "fsync()");
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
            Memory.pokeInt(mTemp, 0, 1, ByteOrder.BIG_ENDIAN);
            Memory.pokeInt(mTemp, 4, j, ByteOrder.BIG_ENDIAN);
            IoBridge.write(mClient, mTemp, 0, 8);
            IoBridge.write(mClient, abyte0, i, j);
        }

        private final FileDescriptor mClient;
        private final ParcelFileDescriptor mClientPfd;
        private final byte mTemp[];

        public FileBridgeOutputStream(ParcelFileDescriptor parcelfiledescriptor)
        {
            mTemp = new byte[8];
            mClientPfd = parcelfiledescriptor;
            mClient = parcelfiledescriptor.getFileDescriptor();
        }

        public FileBridgeOutputStream(FileDescriptor filedescriptor)
        {
            mTemp = new byte[8];
            mClientPfd = null;
            mClient = filedescriptor;
        }
    }


    public FileBridge()
    {
        try
        {
            Os.socketpair(OsConstants.AF_UNIX, OsConstants.SOCK_STREAM, 0, mServer, mClient);
            return;
        }
        catch(ErrnoException errnoexception)
        {
            throw new RuntimeException("Failed to create bridge");
        }
    }

    public void forceClose()
    {
        IoUtils.closeQuietly(mTarget);
        IoUtils.closeQuietly(mServer);
        IoUtils.closeQuietly(mClient);
        mClosed = true;
    }

    public FileDescriptor getClientSocket()
    {
        return mClient;
    }

    public boolean isClosed()
    {
        return mClosed;
    }

    public void run()
    {
        Object obj = new byte[8192];
_L4:
        int i;
        if(IoBridge.read(mServer, ((byte []) (obj)), 0, 8) != 8)
            break MISSING_BLOCK_LABEL_209;
        i = Memory.peekInt(((byte []) (obj)), 0, ByteOrder.BIG_ENDIAN);
        if(i != 1) goto _L2; else goto _L1
_L1:
        i = Memory.peekInt(((byte []) (obj)), 4, ByteOrder.BIG_ENDIAN);
_L7:
        if(i <= 0) goto _L4; else goto _L3
_L3:
        int j = IoBridge.read(mServer, ((byte []) (obj)), 0, Math.min(obj.length, i));
        if(j != -1) goto _L6; else goto _L5
_L5:
        try
        {
            obj = JVM INSTR new #80  <Class IOException>;
            StringBuilder stringbuilder = JVM INSTR new #106 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            ((IOException) (obj)).IOException(stringbuilder.append("Unexpected EOF; still expected ").append(i).append(" bytes").toString());
            throw obj;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj) { }
        Log.wtf("FileBridge", "Failed during bridge", ((Throwable) (obj)));
        forceClose();
_L9:
        return;
_L6:
        IoBridge.write(mTarget, ((byte []) (obj)), 0, j);
        i -= j;
          goto _L7
_L2:
        if(i != 2)
            continue; /* Loop/switch isn't completed */
        Os.fsync(mTarget);
        IoBridge.write(mServer, ((byte []) (obj)), 0, 8);
          goto _L4
        obj;
        forceClose();
        throw obj;
        if(i != 3) goto _L4; else goto _L8
_L8:
        Os.fsync(mTarget);
        Os.close(mTarget);
        mClosed = true;
        IoBridge.write(mServer, ((byte []) (obj)), 0, 8);
        forceClose();
          goto _L9
    }

    public void setTargetFile(FileDescriptor filedescriptor)
    {
        mTarget = filedescriptor;
    }

    private static final int CMD_CLOSE = 3;
    private static final int CMD_FSYNC = 2;
    private static final int CMD_WRITE = 1;
    private static final int MSG_LENGTH = 8;
    private static final String TAG = "FileBridge";
    private final FileDescriptor mClient = new FileDescriptor();
    private volatile boolean mClosed;
    private final FileDescriptor mServer = new FileDescriptor();
    private FileDescriptor mTarget;
}
