// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.system.*;
import android.util.Log;
import dalvik.system.CloseGuard;
import java.io.*;
import java.net.DatagramSocket;
import java.net.Socket;
import java.nio.ByteOrder;
import libcore.io.IoUtils;
import libcore.io.Memory;

// Referenced classes of package android.os:
//            Parcelable, MemoryFile, Handler, Looper, 
//            MessageQueue, Parcel

public class ParcelFileDescriptor
    implements Parcelable, Closeable
{
    public static class AutoCloseInputStream extends FileInputStream
    {

        public void close()
            throws IOException
        {
            mPfd.close();
            super.close();
            return;
            Exception exception;
            exception;
            super.close();
            throw exception;
        }

        public int read()
            throws IOException
        {
            int i = super.read();
            if(i == -1 && mPfd.canDetectErrors())
                mPfd.checkError();
            return i;
        }

        public int read(byte abyte0[])
            throws IOException
        {
            int i = super.read(abyte0);
            if(i == -1 && mPfd.canDetectErrors())
                mPfd.checkError();
            return i;
        }

        public int read(byte abyte0[], int i, int j)
            throws IOException
        {
            i = super.read(abyte0, i, j);
            if(i == -1 && mPfd.canDetectErrors())
                mPfd.checkError();
            return i;
        }

        private final ParcelFileDescriptor mPfd;

        public AutoCloseInputStream(ParcelFileDescriptor parcelfiledescriptor)
        {
            super(parcelfiledescriptor.getFileDescriptor());
            mPfd = parcelfiledescriptor;
        }
    }

    public static class AutoCloseOutputStream extends FileOutputStream
    {

        public void close()
            throws IOException
        {
            mPfd.close();
            super.close();
            return;
            Exception exception;
            exception;
            super.close();
            throw exception;
        }

        private final ParcelFileDescriptor mPfd;

        public AutoCloseOutputStream(ParcelFileDescriptor parcelfiledescriptor)
        {
            super(parcelfiledescriptor.getFileDescriptor());
            mPfd = parcelfiledescriptor;
        }
    }

    public static class FileDescriptorDetachedException extends IOException
    {

        private static final long serialVersionUID = 0xde7ac4edfdL;

        public FileDescriptorDetachedException()
        {
            super("Remote side is detached");
        }
    }

    public static interface OnCloseListener
    {

        public abstract void onClose(IOException ioexception);
    }

    private static class Status
    {

        public IOException asIOException()
        {
            switch(status)
            {
            case -1: 
            default:
                return new IOException((new StringBuilder()).append("Unknown status: ").append(status).toString());

            case -2: 
                return new IOException("Remote side is dead");

            case 0: // '\0'
                return null;

            case 1: // '\001'
                return new IOException((new StringBuilder()).append("Remote error: ").append(msg).toString());

            case 2: // '\002'
                return new FileDescriptorDetachedException();

            case 3: // '\003'
                return new IOException("Remote side was leaked");
            }
        }

        public String toString()
        {
            return (new StringBuilder()).append("{").append(status).append(": ").append(msg).append("}").toString();
        }

        public static final int DEAD = -2;
        public static final int DETACHED = 2;
        public static final int ERROR = 1;
        public static final int LEAKED = 3;
        public static final int OK = 0;
        public static final int SILENCE = -1;
        public final String msg;
        public final int status;

        public Status(int i)
        {
            this(i, null);
        }

        public Status(int i, String s)
        {
            status = i;
            msg = s;
        }
    }


    static Status _2D_wrap0(FileDescriptor filedescriptor, byte abyte0[])
    {
        return readCommStatus(filedescriptor, abyte0);
    }

    public ParcelFileDescriptor(ParcelFileDescriptor parcelfiledescriptor)
    {
        mGuard = CloseGuard.get();
        mWrapped = parcelfiledescriptor;
        mFd = null;
        mCommFd = null;
        mClosed = true;
    }

    public ParcelFileDescriptor(FileDescriptor filedescriptor)
    {
        this(filedescriptor, null);
    }

    public ParcelFileDescriptor(FileDescriptor filedescriptor, FileDescriptor filedescriptor1)
    {
        mGuard = CloseGuard.get();
        if(filedescriptor == null)
        {
            throw new NullPointerException("FileDescriptor must not be null");
        } else
        {
            mWrapped = null;
            mFd = filedescriptor;
            mCommFd = filedescriptor1;
            mGuard.open("close");
            return;
        }
    }

    public static ParcelFileDescriptor adoptFd(int i)
    {
        FileDescriptor filedescriptor = new FileDescriptor();
        filedescriptor.setInt$(i);
        return new ParcelFileDescriptor(filedescriptor);
    }

    private void closeWithStatus(int i, String s)
    {
        if(mClosed)
        {
            return;
        } else
        {
            mClosed = true;
            mGuard.close();
            writeCommStatusAndClose(i, s);
            IoUtils.closeQuietly(mFd);
            releaseResources();
            return;
        }
    }

    private static FileDescriptor[] createCommSocketPair()
        throws IOException
    {
        FileDescriptor filedescriptor;
        FileDescriptor filedescriptor1;
        try
        {
            filedescriptor = JVM INSTR new #118 <Class FileDescriptor>;
            filedescriptor.FileDescriptor();
            filedescriptor1 = JVM INSTR new #118 <Class FileDescriptor>;
            filedescriptor1.FileDescriptor();
            Os.socketpair(OsConstants.AF_UNIX, OsConstants.SOCK_SEQPACKET, 0, filedescriptor, filedescriptor1);
            IoUtils.setBlocking(filedescriptor, false);
            IoUtils.setBlocking(filedescriptor1, false);
        }
        catch(ErrnoException errnoexception)
        {
            throw errnoexception.rethrowAsIOException();
        }
        return (new FileDescriptor[] {
            filedescriptor, filedescriptor1
        });
    }

    public static ParcelFileDescriptor[] createPipe()
        throws IOException
    {
        ParcelFileDescriptor parcelfiledescriptor;
        ParcelFileDescriptor parcelfiledescriptor1;
        try
        {
            FileDescriptor afiledescriptor[] = Os.pipe();
            parcelfiledescriptor1 = JVM INSTR new #2   <Class ParcelFileDescriptor>;
            parcelfiledescriptor1.ParcelFileDescriptor(afiledescriptor[0]);
            parcelfiledescriptor = new ParcelFileDescriptor(afiledescriptor[1]);
        }
        catch(ErrnoException errnoexception)
        {
            throw errnoexception.rethrowAsIOException();
        }
        return (new ParcelFileDescriptor[] {
            parcelfiledescriptor1, parcelfiledescriptor
        });
    }

    public static ParcelFileDescriptor[] createReliablePipe()
        throws IOException
    {
        ParcelFileDescriptor parcelfiledescriptor;
        ParcelFileDescriptor parcelfiledescriptor1;
        try
        {
            FileDescriptor afiledescriptor[] = createCommSocketPair();
            FileDescriptor afiledescriptor1[] = Os.pipe();
            parcelfiledescriptor1 = JVM INSTR new #2   <Class ParcelFileDescriptor>;
            parcelfiledescriptor1.ParcelFileDescriptor(afiledescriptor1[0], afiledescriptor[0]);
            parcelfiledescriptor = new ParcelFileDescriptor(afiledescriptor1[1], afiledescriptor[1]);
        }
        catch(ErrnoException errnoexception)
        {
            throw errnoexception.rethrowAsIOException();
        }
        return (new ParcelFileDescriptor[] {
            parcelfiledescriptor1, parcelfiledescriptor
        });
    }

    public static ParcelFileDescriptor[] createReliableSocketPair()
        throws IOException
    {
        return createReliableSocketPair(OsConstants.SOCK_STREAM);
    }

    public static ParcelFileDescriptor[] createReliableSocketPair(int i)
        throws IOException
    {
        ParcelFileDescriptor parcelfiledescriptor;
        ParcelFileDescriptor parcelfiledescriptor1;
        try
        {
            FileDescriptor afiledescriptor[] = createCommSocketPair();
            FileDescriptor filedescriptor = JVM INSTR new #118 <Class FileDescriptor>;
            filedescriptor.FileDescriptor();
            FileDescriptor filedescriptor1 = JVM INSTR new #118 <Class FileDescriptor>;
            filedescriptor1.FileDescriptor();
            Os.socketpair(OsConstants.AF_UNIX, i, 0, filedescriptor, filedescriptor1);
            parcelfiledescriptor1 = JVM INSTR new #2   <Class ParcelFileDescriptor>;
            parcelfiledescriptor1.ParcelFileDescriptor(filedescriptor, afiledescriptor[0]);
            parcelfiledescriptor = new ParcelFileDescriptor(filedescriptor1, afiledescriptor[1]);
        }
        catch(ErrnoException errnoexception)
        {
            throw errnoexception.rethrowAsIOException();
        }
        return (new ParcelFileDescriptor[] {
            parcelfiledescriptor1, parcelfiledescriptor
        });
    }

    public static ParcelFileDescriptor[] createSocketPair()
        throws IOException
    {
        return createSocketPair(OsConstants.SOCK_STREAM);
    }

    public static ParcelFileDescriptor[] createSocketPair(int i)
        throws IOException
    {
        Object obj;
        ParcelFileDescriptor parcelfiledescriptor;
        try
        {
            FileDescriptor filedescriptor = JVM INSTR new #118 <Class FileDescriptor>;
            filedescriptor.FileDescriptor();
            obj = JVM INSTR new #118 <Class FileDescriptor>;
            ((FileDescriptor) (obj)).FileDescriptor();
            Os.socketpair(OsConstants.AF_UNIX, i, 0, filedescriptor, ((FileDescriptor) (obj)));
            parcelfiledescriptor = JVM INSTR new #2   <Class ParcelFileDescriptor>;
            parcelfiledescriptor.ParcelFileDescriptor(filedescriptor);
            obj = new ParcelFileDescriptor(((FileDescriptor) (obj)));
        }
        catch(ErrnoException errnoexception)
        {
            throw errnoexception.rethrowAsIOException();
        }
        return (new ParcelFileDescriptor[] {
            parcelfiledescriptor, obj
        });
    }

    public static ParcelFileDescriptor dup(FileDescriptor filedescriptor)
        throws IOException
    {
        try
        {
            filedescriptor = new ParcelFileDescriptor(Os.dup(filedescriptor));
        }
        // Misplaced declaration of an exception variable
        catch(FileDescriptor filedescriptor)
        {
            throw filedescriptor.rethrowAsIOException();
        }
        return filedescriptor;
    }

    public static ParcelFileDescriptor fromData(byte abyte0[], String s)
        throws IOException
    {
        Object obj = null;
        if(abyte0 == null)
            return null;
        s = new MemoryFile(s, abyte0.length);
        if(abyte0.length > 0)
            s.writeBytes(abyte0, 0, 0, abyte0.length);
        s.deactivate();
        s = s.getFileDescriptor();
        abyte0 = obj;
        if(s != null)
            abyte0 = new ParcelFileDescriptor(s);
        return abyte0;
    }

    public static ParcelFileDescriptor fromDatagramSocket(DatagramSocket datagramsocket)
    {
        Object obj = null;
        FileDescriptor filedescriptor = datagramsocket.getFileDescriptor$();
        datagramsocket = obj;
        if(filedescriptor != null)
            datagramsocket = new ParcelFileDescriptor(filedescriptor);
        return datagramsocket;
    }

    public static ParcelFileDescriptor fromFd(int i)
        throws IOException
    {
        Object obj = new FileDescriptor();
        ((FileDescriptor) (obj)).setInt$(i);
        try
        {
            obj = new ParcelFileDescriptor(Os.dup(((FileDescriptor) (obj))));
        }
        catch(ErrnoException errnoexception)
        {
            throw errnoexception.rethrowAsIOException();
        }
        return ((ParcelFileDescriptor) (obj));
    }

    public static ParcelFileDescriptor fromFd(FileDescriptor filedescriptor, Handler handler, OnCloseListener oncloselistener)
        throws IOException
    {
        if(handler == null)
            throw new IllegalArgumentException("Handler must not be null");
        if(oncloselistener == null)
        {
            throw new IllegalArgumentException("Listener must not be null");
        } else
        {
            FileDescriptor afiledescriptor[] = createCommSocketPair();
            filedescriptor = new ParcelFileDescriptor(filedescriptor, afiledescriptor[0]);
            handler = handler.getLooper().getQueue();
            handler.addOnFileDescriptorEventListener(afiledescriptor[1], 1, new MessageQueue.OnFileDescriptorEventListener(handler, oncloselistener) {

                public int onFileDescriptorEvents(FileDescriptor filedescriptor1, int i)
                {
                    Status status = null;
                    if((i & 1) != 0)
                        status = ParcelFileDescriptor._2D_wrap0(filedescriptor1, new byte[1024]);
                    else
                    if((i & 4) != 0)
                        status = new Status(-2);
                    if(status != null)
                    {
                        queue.removeOnFileDescriptorEventListener(filedescriptor1);
                        IoUtils.closeQuietly(filedescriptor1);
                        listener.onClose(status.asIOException());
                        return 0;
                    } else
                    {
                        return 1;
                    }
                }

                final OnCloseListener val$listener;
                final MessageQueue val$queue;

            
            {
                queue = messagequeue;
                listener = oncloselistener;
                super();
            }
            }
);
            return filedescriptor;
        }
    }

    public static ParcelFileDescriptor fromSocket(Socket socket)
    {
        Object obj = null;
        FileDescriptor filedescriptor = socket.getFileDescriptor$();
        socket = obj;
        if(filedescriptor != null)
            socket = new ParcelFileDescriptor(filedescriptor);
        return socket;
    }

    public static File getFile(FileDescriptor filedescriptor)
        throws IOException
    {
        try
        {
            Object obj = JVM INSTR new #257 <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            obj = Os.readlink(((StringBuilder) (obj)).append("/proc/self/fd/").append(filedescriptor.getInt$()).toString());
            if(OsConstants.S_ISREG(Os.stat(((String) (obj))).st_mode))
            {
                return new File(((String) (obj)));
            } else
            {
                IOException ioexception = JVM INSTR new #144 <Class IOException>;
                filedescriptor = JVM INSTR new #257 <Class StringBuilder>;
                filedescriptor.StringBuilder();
                ioexception.IOException(filedescriptor.append("Not a regular file: ").append(((String) (obj))).toString());
                throw ioexception;
            }
        }
        // Misplaced declaration of an exception variable
        catch(FileDescriptor filedescriptor)
        {
            throw filedescriptor.rethrowAsIOException();
        }
    }

    private byte[] getOrCreateStatusBuffer()
    {
        if(mStatusBuf == null)
            mStatusBuf = new byte[1024];
        return mStatusBuf;
    }

    public static ParcelFileDescriptor open(File file, int i)
        throws FileNotFoundException
    {
        file = openInternal(file, i);
        if(file == null)
            return null;
        else
            return new ParcelFileDescriptor(file);
    }

    public static ParcelFileDescriptor open(File file, int i, Handler handler, OnCloseListener oncloselistener)
        throws IOException
    {
        if(handler == null)
            throw new IllegalArgumentException("Handler must not be null");
        if(oncloselistener == null)
            throw new IllegalArgumentException("Listener must not be null");
        file = openInternal(file, i);
        if(file == null)
            return null;
        else
            return fromFd(file, handler, oncloselistener);
    }

    private static FileDescriptor openInternal(File file, int i)
        throws FileNotFoundException
    {
        int j;
        if((i & 0x30000000) == 0)
            throw new IllegalArgumentException("Must specify MODE_READ_ONLY, MODE_WRITE_ONLY, or MODE_READ_WRITE");
        j = 0;
        i & 0x30000000;
        JVM INSTR lookupswitch 4: default 68
    //                   0: 169
    //                   268435456: 169
    //                   536870912: 176
    //                   805306368: 183;
           goto _L1 _L2 _L2 _L3 _L4
_L1:
        break; /* Loop/switch isn't completed */
_L4:
        break MISSING_BLOCK_LABEL_183;
_L5:
        int k = j;
        if((0x8000000 & i) != 0)
            k = j | OsConstants.O_CREAT;
        j = k;
        if((0x4000000 & i) != 0)
            j = k | OsConstants.O_TRUNC;
        k = j;
        if((0x2000000 & i) != 0)
            k = j | OsConstants.O_APPEND;
        int l = OsConstants.S_IRWXU | OsConstants.S_IRWXG;
        j = l;
        if((i & 1) != 0)
            j = l | OsConstants.S_IROTH;
        l = j;
        if((i & 2) != 0)
            l = j | OsConstants.S_IWOTH;
        file = file.getPath();
        try
        {
            file = Os.open(file, k, l);
        }
        // Misplaced declaration of an exception variable
        catch(File file)
        {
            throw new FileNotFoundException(file.getMessage());
        }
        return file;
_L2:
        j = OsConstants.O_RDONLY;
          goto _L5
_L3:
        j = OsConstants.O_WRONLY;
          goto _L5
        j = OsConstants.O_RDWR;
          goto _L5
    }

    public static int parseMode(String s)
    {
        int i;
        if("r".equals(s))
            i = 0x10000000;
        else
        if("w".equals(s) || "wt".equals(s))
            i = 0x2c000000;
        else
        if("wa".equals(s))
            i = 0x2a000000;
        else
        if("rw".equals(s))
            i = 0x38000000;
        else
        if("rwt".equals(s))
            i = 0x3c000000;
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Bad mode '").append(s).append("'").toString());
        return i;
    }

    private static Status readCommStatus(FileDescriptor filedescriptor, byte abyte0[])
    {
        int i;
        int j;
        try
        {
            i = Os.read(filedescriptor, abyte0, 0, abyte0.length);
        }
        // Misplaced declaration of an exception variable
        catch(FileDescriptor filedescriptor)
        {
            if(((ErrnoException) (filedescriptor)).errno == OsConstants.EAGAIN)
            {
                return null;
            } else
            {
                Log.d("ParcelFileDescriptor", (new StringBuilder()).append("Failed to read status; assuming dead: ").append(filedescriptor).toString());
                return new Status(-2);
            }
        }
        // Misplaced declaration of an exception variable
        catch(FileDescriptor filedescriptor)
        {
            Log.d("ParcelFileDescriptor", (new StringBuilder()).append("Failed to read status; assuming dead: ").append(filedescriptor).toString());
            return new Status(-2);
        }
        if(i != 0)
            break MISSING_BLOCK_LABEL_23;
        return new Status(-2);
        j = Memory.peekInt(abyte0, 0, ByteOrder.BIG_ENDIAN);
        if(j != 1)
            break MISSING_BLOCK_LABEL_60;
        filedescriptor = JVM INSTR new #360 <Class String>;
        filedescriptor.String(abyte0, 4, i - 4);
        return new Status(j, filedescriptor);
        filedescriptor = new Status(j);
        return filedescriptor;
    }

    private void writeCommStatusAndClose(int i, String s)
    {
        if(mCommFd == null)
        {
            if(s != null)
                Log.w("ParcelFileDescriptor", (new StringBuilder()).append("Unable to inform peer: ").append(s).toString());
            return;
        }
        if(i == 2)
            Log.w("ParcelFileDescriptor", "Peer expected signal when closed; unable to deliver after detach");
        if(i == -1)
        {
            IoUtils.closeQuietly(mCommFd);
            mCommFd = null;
            return;
        }
        Status status;
        mStatus = readCommStatus(mCommFd, getOrCreateStatusBuffer());
        status = mStatus;
        if(status != null)
        {
            IoUtils.closeQuietly(mCommFd);
            mCommFd = null;
            return;
        }
        byte abyte0[];
        abyte0 = getOrCreateStatusBuffer();
        Memory.pokeInt(abyte0, 0, i, ByteOrder.BIG_ENDIAN);
        i = 4;
        if(s == null)
            break MISSING_BLOCK_LABEL_153;
        s = s.getBytes();
        i = Math.min(s.length, abyte0.length - 4);
        System.arraycopy(s, 0, abyte0, 4, i);
        i += 4;
        Os.write(mCommFd, abyte0, 0, i);
_L1:
        IoUtils.closeQuietly(mCommFd);
        mCommFd = null;
        return;
        Object obj;
        obj;
        s = JVM INSTR new #257 <Class StringBuilder>;
        s.StringBuilder();
        Log.w("ParcelFileDescriptor", s.append("Failed to report status: ").append(obj).toString());
          goto _L1
        s;
        IoUtils.closeQuietly(mCommFd);
        mCommFd = null;
        throw s;
        obj;
        s = JVM INSTR new #257 <Class StringBuilder>;
        s.StringBuilder();
        Log.w("ParcelFileDescriptor", s.append("Failed to report status: ").append(obj).toString());
          goto _L1
    }

    public boolean canDetectErrors()
    {
        if(mWrapped != null)
            return mWrapped.canDetectErrors();
        boolean flag;
        if(mCommFd != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void checkError()
        throws IOException
    {
        if(mWrapped != null)
        {
            mWrapped.checkError();
            return;
        }
        if(mStatus == null)
        {
            if(mCommFd == null)
            {
                Log.w("ParcelFileDescriptor", "Peer didn't provide a comm channel; unable to check for errors");
                return;
            }
            mStatus = readCommStatus(mCommFd, getOrCreateStatusBuffer());
        }
        if(mStatus == null || mStatus.status == 0)
            return;
        else
            throw mStatus.asIOException();
    }

    public void close()
        throws IOException
    {
        if(mWrapped == null) goto _L2; else goto _L1
_L1:
        mWrapped.close();
        releaseResources();
_L4:
        return;
        Exception exception;
        exception;
        releaseResources();
        throw exception;
_L2:
        closeWithStatus(0, null);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void closeWithError(String s)
        throws IOException
    {
        if(mWrapped == null) goto _L2; else goto _L1
_L1:
        mWrapped.closeWithError(s);
        releaseResources();
_L4:
        return;
        s;
        releaseResources();
        throw s;
_L2:
        if(s == null)
            throw new IllegalArgumentException("Message must not be null");
        closeWithStatus(1, s);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public int describeContents()
    {
        if(mWrapped != null)
            return mWrapped.describeContents();
        else
            return 1;
    }

    public int detachFd()
    {
        if(mWrapped != null)
            return mWrapped.detachFd();
        if(mClosed)
        {
            throw new IllegalStateException("Already closed");
        } else
        {
            int i = getFd();
            Parcel.clearFileDescriptor(mFd);
            writeCommStatusAndClose(2, null);
            mClosed = true;
            mGuard.close();
            releaseResources();
            return i;
        }
    }

    public ParcelFileDescriptor dup()
        throws IOException
    {
        if(mWrapped != null)
            return mWrapped.dup();
        else
            return dup(getFileDescriptor());
    }

    protected void finalize()
        throws Throwable
    {
        if(mWrapped != null)
            releaseResources();
        if(mGuard != null)
            mGuard.warnIfOpen();
        if(!mClosed)
            closeWithStatus(3, null);
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public int getFd()
    {
        if(mWrapped != null)
            return mWrapped.getFd();
        if(mClosed)
            throw new IllegalStateException("Already closed");
        else
            return mFd.getInt$();
    }

    public FileDescriptor getFileDescriptor()
    {
        if(mWrapped != null)
            return mWrapped.getFileDescriptor();
        else
            return mFd;
    }

    public long getStatSize()
    {
label0:
        {
            if(mWrapped != null)
                return mWrapped.getStatSize();
            long l;
            try
            {
                StructStat structstat = Os.fstat(mFd);
                if(!OsConstants.S_ISREG(structstat.st_mode) && !OsConstants.S_ISLNK(structstat.st_mode))
                    break label0;
                l = structstat.st_size;
            }
            catch(ErrnoException errnoexception)
            {
                Log.w("ParcelFileDescriptor", (new StringBuilder()).append("fstat() failed: ").append(errnoexception).toString());
                return -1L;
            }
            return l;
        }
        return -1L;
    }

    public void releaseResources()
    {
    }

    public long seekTo(long l)
        throws IOException
    {
        if(mWrapped != null)
            return mWrapped.seekTo(l);
        try
        {
            l = Os.lseek(mFd, l, OsConstants.SEEK_SET);
        }
        catch(ErrnoException errnoexception)
        {
            throw errnoexception.rethrowAsIOException();
        }
        return l;
    }

    public String toString()
    {
        if(mWrapped != null)
            return mWrapped.toString();
        else
            return (new StringBuilder()).append("{ParcelFileDescriptor: ").append(mFd).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if(mWrapped == null) goto _L2; else goto _L1
_L1:
        mWrapped.writeToParcel(parcel, i);
        releaseResources();
_L4:
        return;
        parcel;
        releaseResources();
        throw parcel;
_L2:
        if(mCommFd != null)
        {
            parcel.writeInt(1);
            parcel.writeFileDescriptor(mFd);
            parcel.writeFileDescriptor(mCommFd);
        } else
        {
            parcel.writeInt(0);
            parcel.writeFileDescriptor(mFd);
        }
        if((i & 1) != 0 && mClosed ^ true)
            closeWithStatus(-1, null);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public ParcelFileDescriptor createFromParcel(Parcel parcel)
        {
            int i = parcel.readInt();
            FileDescriptor filedescriptor = parcel.readRawFileDescriptor();
            FileDescriptor filedescriptor1 = null;
            if(i != 0)
                filedescriptor1 = parcel.readRawFileDescriptor();
            return new ParcelFileDescriptor(filedescriptor, filedescriptor1);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ParcelFileDescriptor[] newArray(int i)
        {
            return new ParcelFileDescriptor[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int MAX_STATUS = 1024;
    public static final int MODE_APPEND = 0x2000000;
    public static final int MODE_CREATE = 0x8000000;
    public static final int MODE_READ_ONLY = 0x10000000;
    public static final int MODE_READ_WRITE = 0x30000000;
    public static final int MODE_TRUNCATE = 0x4000000;
    public static final int MODE_WORLD_READABLE = 1;
    public static final int MODE_WORLD_WRITEABLE = 2;
    public static final int MODE_WRITE_ONLY = 0x20000000;
    private static final String TAG = "ParcelFileDescriptor";
    private volatile boolean mClosed;
    private FileDescriptor mCommFd;
    private final FileDescriptor mFd;
    private final CloseGuard mGuard;
    private Status mStatus;
    private byte mStatusBuf[];
    private final ParcelFileDescriptor mWrapped;

}
