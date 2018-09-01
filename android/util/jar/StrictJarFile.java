// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util.jar;

import android.system.*;
import dalvik.system.CloseGuard;
import java.io.*;
import java.security.cert.Certificate;
import java.util.*;
import java.util.zip.*;
import libcore.io.*;

// Referenced classes of package android.util.jar:
//            StrictJarManifest, StrictJarVerifier

public final class StrictJarFile
{
    static final class EntryIterator
        implements Iterator
    {

        public boolean hasNext()
        {
            if(nextEntry != null)
                return true;
            ZipEntry zipentry = StrictJarFile._2D_wrap0(iterationHandle);
            if(zipentry == null)
            {
                return false;
            } else
            {
                nextEntry = zipentry;
                return true;
            }
        }

        public volatile Object next()
        {
            return next();
        }

        public ZipEntry next()
        {
            if(nextEntry != null)
            {
                ZipEntry zipentry = nextEntry;
                nextEntry = null;
                return zipentry;
            } else
            {
                return StrictJarFile._2D_wrap0(iterationHandle);
            }
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        private final long iterationHandle;
        private ZipEntry nextEntry;

        EntryIterator(long l, String s)
            throws IOException
        {
            iterationHandle = StrictJarFile._2D_wrap1(l, s);
        }
    }

    public static class FDStream extends InputStream
    {

        public int available()
            throws IOException
        {
            int i;
            if(offset < endOffset)
                i = 1;
            else
                i = 0;
            return i;
        }

        public int read()
            throws IOException
        {
            return Streams.readSingleByte(this);
        }

        public int read(byte abyte0[], int i, int j)
            throws IOException
        {
            FileDescriptor filedescriptor = fd;
            filedescriptor;
            JVM INSTR monitorenter ;
            long l;
            long l1;
            l = endOffset;
            l1 = offset;
            int k;
            l1 = l - l1;
            k = j;
            if((long)j > l1)
                k = (int)l1;
            Os.lseek(fd, offset, OsConstants.SEEK_SET);
            i = IoBridge.read(fd, abyte0, i, k);
            if(i <= 0)
                break MISSING_BLOCK_LABEL_111;
            offset = offset + (long)i;
            filedescriptor;
            JVM INSTR monitorexit ;
            return i;
            ErrnoException errnoexception;
            errnoexception;
            abyte0 = JVM INSTR new #28  <Class IOException>;
            abyte0.IOException(errnoexception);
            throw abyte0;
            abyte0;
            filedescriptor;
            JVM INSTR monitorexit ;
            throw abyte0;
            filedescriptor;
            JVM INSTR monitorexit ;
            return -1;
        }

        public long skip(long l)
            throws IOException
        {
            long l1 = l;
            if(l > endOffset - offset)
                l1 = endOffset - offset;
            offset = offset + l1;
            return l1;
        }

        private long endOffset;
        private final FileDescriptor fd;
        private long offset;

        public FDStream(FileDescriptor filedescriptor, long l, long l1)
        {
            fd = filedescriptor;
            offset = l;
            endOffset = l1;
        }
    }

    static final class JarFileInputStream extends FilterInputStream
    {

        public int available()
            throws IOException
        {
            if(done)
                return 0;
            else
                return super.available();
        }

        public int read()
            throws IOException
        {
            if(done)
                return -1;
            if(count > 0L)
            {
                int i = super.read();
                if(i != -1)
                {
                    entry.write(i);
                    count = count - 1L;
                } else
                {
                    count = 0L;
                }
                if(count == 0L)
                {
                    done = true;
                    entry.verify();
                }
                return i;
            } else
            {
                done = true;
                entry.verify();
                return -1;
            }
        }

        public int read(byte abyte0[], int i, int j)
            throws IOException
        {
            if(done)
                return -1;
            if(count > 0L)
            {
                int k = super.read(abyte0, i, j);
                if(k != -1)
                {
                    j = k;
                    if(count < (long)k)
                        j = (int)count;
                    entry.write(abyte0, i, j);
                    count = count - (long)j;
                } else
                {
                    count = 0L;
                }
                if(count == 0L)
                {
                    done = true;
                    entry.verify();
                }
                return k;
            } else
            {
                done = true;
                entry.verify();
                return -1;
            }
        }

        public long skip(long l)
            throws IOException
        {
            return Streams.skipByReading(this, l);
        }

        private long count;
        private boolean done;
        private final StrictJarVerifier.VerifierEntry entry;

        JarFileInputStream(InputStream inputstream, long l, StrictJarVerifier.VerifierEntry verifierentry)
        {
            super(inputstream);
            done = false;
            entry = verifierentry;
            count = l;
        }
    }

    public static class ZipInflaterInputStream extends InflaterInputStream
    {

        public int available()
            throws IOException
        {
            int i = 0;
            if(closed)
                return 0;
            if(super.available() != 0)
                i = (int)(entry.getSize() - bytesRead);
            return i;
        }

        public int read(byte abyte0[], int i, int j)
            throws IOException
        {
            try
            {
                i = super.read(abyte0, i, j);
            }
            // Misplaced declaration of an exception variable
            catch(byte abyte0[])
            {
                throw new IOException((new StringBuilder()).append("Error reading data for ").append(entry.getName()).append(" near offset ").append(bytesRead).toString(), abyte0);
            }
            if(i == -1)
            {
                if(entry.getSize() != bytesRead)
                    throw new IOException((new StringBuilder()).append("Size mismatch on inflated file: ").append(bytesRead).append(" vs ").append(entry.getSize()).toString());
            } else
            {
                bytesRead = bytesRead + (long)i;
            }
            return i;
        }

        private long bytesRead;
        private final ZipEntry entry;

        public ZipInflaterInputStream(InputStream inputstream, Inflater inflater, int i, ZipEntry zipentry)
        {
            super(inputstream, inflater, i);
            bytesRead = 0L;
            entry = zipentry;
        }
    }


    static ZipEntry _2D_wrap0(long l)
    {
        return nativeNextEntry(l);
    }

    static long _2D_wrap1(long l, String s)
    {
        return nativeStartIteration(l, s);
    }

    public StrictJarFile(FileDescriptor filedescriptor)
        throws IOException, SecurityException
    {
        this(filedescriptor, true, true);
    }

    public StrictJarFile(FileDescriptor filedescriptor, boolean flag, boolean flag1)
        throws IOException, SecurityException
    {
        this((new StringBuilder()).append("[fd:").append(filedescriptor.getInt$()).append("]").toString(), filedescriptor, flag, flag1);
    }

    public StrictJarFile(String s)
        throws IOException, SecurityException
    {
        this(s, true, true);
    }

    private StrictJarFile(String s, FileDescriptor filedescriptor, boolean flag, boolean flag1)
        throws IOException, SecurityException
    {
        guard = CloseGuard.get();
        nativeHandle = nativeOpenJarFile(s, filedescriptor.getInt$());
        fd = filedescriptor;
        if(!flag)
            break MISSING_BLOCK_LABEL_237;
label0:
        {
            Object obj = getMetaEntries();
            Object obj1 = JVM INSTR new #106 <Class StrictJarManifest>;
            ((StrictJarManifest) (obj1)).StrictJarManifest((byte[])((HashMap) (obj)).get("META-INF/MANIFEST.MF"), true);
            manifest = ((StrictJarManifest) (obj1));
            obj1 = JVM INSTR new #122 <Class StrictJarVerifier>;
            ((StrictJarVerifier) (obj1)).StrictJarVerifier(s, manifest, ((HashMap) (obj)), flag1);
            verifier = ((StrictJarVerifier) (obj1));
            obj = manifest.getEntries().keySet().iterator();
            do
            {
                if(!((Iterator) (obj)).hasNext())
                    break label0;
                s = (String)((Iterator) (obj)).next();
            } while(findEntry(s) != null);
            obj1 = JVM INSTR new #46  <Class SecurityException>;
            obj = JVM INSTR new #52  <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            ((SecurityException) (obj1)).SecurityException(((StringBuilder) (obj)).append("File ").append(s).append(" in manifest does not exist").toString());
            throw obj1;
        }
        if(!verifier.readCertificates()) goto _L2; else goto _L1
_L1:
        flag = verifier.isSignedJar();
_L3:
        isSigned = flag;
_L4:
        guard.open("close");
        return;
_L2:
        flag = false;
          goto _L3
        try
        {
            isSigned = false;
            manifest = null;
            verifier = null;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            nativeClose(nativeHandle);
            IoUtils.closeQuietly(filedescriptor);
            closed = true;
            throw s;
        }
          goto _L4
    }

    public StrictJarFile(String s, boolean flag, boolean flag1)
        throws IOException, SecurityException
    {
        this(s, IoBridge.open(s, OsConstants.O_RDONLY), flag, flag1);
    }

    private HashMap getMetaEntries()
        throws IOException
    {
        HashMap hashmap = new HashMap();
        ZipEntry zipentry;
        for(EntryIterator entryiterator = new EntryIterator(nativeHandle, "META-INF/"); entryiterator.hasNext(); hashmap.put(zipentry.getName(), Streams.readFully(getInputStream(zipentry))))
            zipentry = (ZipEntry)entryiterator.next();

        return hashmap;
    }

    private InputStream getZipInputStream(ZipEntry zipentry)
    {
        if(zipentry.getMethod() == 0)
        {
            return new FDStream(fd, zipentry.getDataOffset(), zipentry.getDataOffset() + zipentry.getSize());
        } else
        {
            FDStream fdstream = new FDStream(fd, zipentry.getDataOffset(), zipentry.getDataOffset() + zipentry.getCompressedSize());
            int i = Math.max(1024, (int)Math.min(zipentry.getSize(), 65535L));
            return new ZipInflaterInputStream(fdstream, new Inflater(true), i, zipentry);
        }
    }

    private static native void nativeClose(long l);

    private static native ZipEntry nativeFindEntry(long l, String s);

    private static native ZipEntry nativeNextEntry(long l);

    private static native long nativeOpenJarFile(String s, int i)
        throws IOException;

    private static native long nativeStartIteration(long l, String s);

    public void close()
        throws IOException
    {
        if(!closed)
        {
            if(guard != null)
                guard.close();
            nativeClose(nativeHandle);
            IoUtils.closeQuietly(fd);
            closed = true;
        }
    }

    protected void finalize()
        throws Throwable
    {
        if(guard != null)
            guard.warnIfOpen();
        close();
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public ZipEntry findEntry(String s)
    {
        return nativeFindEntry(nativeHandle, s);
    }

    public Certificate[][] getCertificateChains(ZipEntry zipentry)
    {
        if(isSigned)
            return verifier.getCertificateChains(zipentry.getName());
        else
            return null;
    }

    public Certificate[] getCertificates(ZipEntry zipentry)
    {
        if(isSigned)
        {
            Certificate acertificate[][] = verifier.getCertificateChains(zipentry.getName());
            int i = 0;
            int j = acertificate.length;
            for(int k = 0; k < j; k++)
                i += acertificate[k].length;

            Certificate acertificate1[] = new Certificate[i];
            i = 0;
            j = acertificate.length;
            for(int l = 0; l < j; l++)
            {
                zipentry = acertificate[l];
                System.arraycopy(zipentry, 0, acertificate1, i, zipentry.length);
                i += zipentry.length;
            }

            return acertificate1;
        } else
        {
            return null;
        }
    }

    public InputStream getInputStream(ZipEntry zipentry)
    {
        InputStream inputstream = getZipInputStream(zipentry);
        if(isSigned)
        {
            StrictJarVerifier.VerifierEntry verifierentry = verifier.initEntry(zipentry.getName());
            if(verifierentry == null)
                return inputstream;
            else
                return new JarFileInputStream(inputstream, zipentry.getSize(), verifierentry);
        } else
        {
            return inputstream;
        }
    }

    public StrictJarManifest getManifest()
    {
        return manifest;
    }

    public Iterator iterator()
        throws IOException
    {
        return new EntryIterator(nativeHandle, "");
    }

    private boolean closed;
    private final FileDescriptor fd;
    private final CloseGuard guard;
    private final boolean isSigned;
    private final StrictJarManifest manifest;
    private final long nativeHandle;
    private final StrictJarVerifier verifier;
}
