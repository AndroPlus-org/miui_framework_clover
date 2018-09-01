// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;

import android.os.FileUtils;
import android.util.Slog;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import libcore.io.IoUtils;
import libcore.io.Streams;

// Referenced classes of package com.android.internal.util:
//            Preconditions

public class FileRotator
{
    private static class FileInfo
    {

        public String build()
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append(prefix).append('.').append(startMillis).append('-');
            if(endMillis != 0x7fffffffffffffffL)
                stringbuilder.append(endMillis);
            return stringbuilder.toString();
        }

        public boolean isActive()
        {
            boolean flag;
            if(endMillis == 0x7fffffffffffffffL)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean parse(String s)
        {
            int i;
            int j;
            endMillis = -1L;
            startMillis = -1L;
            i = s.lastIndexOf('.');
            j = s.lastIndexOf('-');
            if(i == -1 || j == -1)
                return false;
            if(!prefix.equals(s.substring(0, i)))
                return false;
            startMillis = Long.parseLong(s.substring(i + 1, j));
            if(s.length() - j != 1) goto _L2; else goto _L1
_L1:
            endMillis = 0x7fffffffffffffffL;
_L4:
            return true;
_L2:
            try
            {
                endMillis = Long.parseLong(s.substring(j + 1));
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                return false;
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        public long endMillis;
        public final String prefix;
        public long startMillis;

        public FileInfo(String s)
        {
            prefix = (String)Preconditions.checkNotNull(s);
        }
    }

    public static interface Reader
    {

        public abstract void read(InputStream inputstream)
            throws IOException;
    }

    public static interface Rewriter
        extends Reader, Writer
    {

        public abstract void reset();

        public abstract boolean shouldWrite();
    }

    public static interface Writer
    {

        public abstract void write(OutputStream outputstream)
            throws IOException;
    }


    public FileRotator(File file, String s, long l, long l1)
    {
        mBasePath = (File)Preconditions.checkNotNull(file);
        mPrefix = (String)Preconditions.checkNotNull(s);
        mRotateAgeMillis = l;
        mDeleteAgeMillis = l1;
        mBasePath.mkdirs();
        file = mBasePath.list();
        int i = 0;
        int j = file.length;
        while(i < j) 
        {
            Object obj = file[i];
            if(((String) (obj)).startsWith(mPrefix))
                if(((String) (obj)).endsWith(".backup"))
                    (new File(mBasePath, ((String) (obj)))).renameTo(new File(mBasePath, ((String) (obj)).substring(0, ((String) (obj)).length() - ".backup".length())));
                else
                if(((String) (obj)).endsWith(".no_backup"))
                {
                    s = new File(mBasePath, ((String) (obj)));
                    obj = new File(mBasePath, ((String) (obj)).substring(0, ((String) (obj)).length() - ".no_backup".length()));
                    s.delete();
                    ((File) (obj)).delete();
                }
            i++;
        }
    }

    private String getActiveName(long l)
    {
        String s = null;
        long l1 = 0x7fffffffffffffffL;
        FileInfo fileinfo = new FileInfo(mPrefix);
        String as[] = mBasePath.list();
        if(as == null)
        {
            fileinfo.startMillis = l;
            fileinfo.endMillis = 0x7fffffffffffffffL;
            return fileinfo.build();
        }
        int i = 0;
        int j = as.length;
        while(i < j) 
        {
            String s1 = as[i];
            long l2;
            String s2;
            if(!fileinfo.parse(s1))
            {
                l2 = l1;
                s2 = s;
            } else
            {
                s2 = s;
                l2 = l1;
                if(fileinfo.isActive())
                {
                    s2 = s;
                    l2 = l1;
                    if(fileinfo.startMillis < l)
                    {
                        s2 = s;
                        l2 = l1;
                        if(fileinfo.startMillis < l1)
                        {
                            s2 = s1;
                            l2 = fileinfo.startMillis;
                        }
                    }
                }
            }
            i++;
            s = s2;
            l1 = l2;
        }
        if(s != null)
        {
            return s;
        } else
        {
            fileinfo.startMillis = l;
            fileinfo.endMillis = 0x7fffffffffffffffL;
            return fileinfo.build();
        }
    }

    private static void readFile(File file, Reader reader)
        throws IOException
    {
        file = new BufferedInputStream(new FileInputStream(file));
        reader.read(file);
        IoUtils.closeQuietly(file);
        return;
        reader;
        IoUtils.closeQuietly(file);
        throw reader;
    }

    private static IOException rethrowAsIoException(Throwable throwable)
        throws IOException
    {
        if(throwable instanceof IOException)
            throw (IOException)throwable;
        else
            throw new IOException(throwable.getMessage(), throwable);
    }

    private void rewriteSingle(Rewriter rewriter, String s)
        throws IOException
    {
        File file;
        file = new File(mBasePath, s);
        rewriter.reset();
        if(!file.exists())
            break MISSING_BLOCK_LABEL_106;
        readFile(file, rewriter);
        if(!rewriter.shouldWrite())
            return;
        s = new File(mBasePath, (new StringBuilder()).append(s).append(".backup").toString());
        file.renameTo(s);
        writeFile(file, rewriter);
        s.delete();
_L1:
        return;
        rewriter;
        file.delete();
        s.renameTo(file);
        throw rethrowAsIoException(rewriter);
        s = new File(mBasePath, (new StringBuilder()).append(s).append(".no_backup").toString());
        s.createNewFile();
        try
        {
            writeFile(file, rewriter);
            s.delete();
        }
        // Misplaced declaration of an exception variable
        catch(Rewriter rewriter)
        {
            file.delete();
            s.delete();
            throw rethrowAsIoException(rewriter);
        }
          goto _L1
    }

    private static void writeFile(File file, Writer writer)
        throws IOException
    {
        BufferedOutputStream bufferedoutputstream;
        file = new FileOutputStream(file);
        bufferedoutputstream = new BufferedOutputStream(file);
        writer.write(bufferedoutputstream);
        bufferedoutputstream.flush();
        FileUtils.sync(file);
        IoUtils.closeQuietly(bufferedoutputstream);
        return;
        writer;
        FileUtils.sync(file);
        IoUtils.closeQuietly(bufferedoutputstream);
        throw writer;
    }

    public void combineActive(final Reader reader, final Writer writer, long l)
        throws IOException
    {
        rewriteActive(new Rewriter() {

            public void read(InputStream inputstream)
                throws IOException
            {
                reader.read(inputstream);
            }

            public void reset()
            {
            }

            public boolean shouldWrite()
            {
                return true;
            }

            public void write(OutputStream outputstream)
                throws IOException
            {
                writer.write(outputstream);
            }

            final FileRotator this$0;
            final Reader val$reader;
            final Writer val$writer;

            
            {
                this$0 = FileRotator.this;
                reader = reader1;
                writer = writer1;
                super();
            }
        }
, l);
    }

    public void deleteAll()
    {
        FileInfo fileinfo = new FileInfo(mPrefix);
        String as[] = mBasePath.list();
        int i = 0;
        for(int j = as.length; i < j; i++)
        {
            String s = as[i];
            if(fileinfo.parse(s))
                (new File(mBasePath, s)).delete();
        }

    }

    public void dumpAll(OutputStream outputstream)
        throws IOException
    {
        if(mBasePath.list() == null)
        {
            Slog.d("FileRotator", "FileRotator dumpAll mBasePath.list() is null.");
            return;
        }
        outputstream = new ZipOutputStream(outputstream);
        FileInfo fileinfo;
        String as[];
        fileinfo = JVM INSTR new #8   <Class FileRotator$FileInfo>;
        fileinfo.FileInfo(mPrefix);
        as = mBasePath.list();
        int i = 0;
        int j = as.length;
_L2:
        Object obj;
        if(i >= j)
            break; /* Loop/switch isn't completed */
        obj = as[i];
        if(!fileinfo.parse(((String) (obj))))
            break MISSING_BLOCK_LABEL_139;
        Object obj1 = JVM INSTR new #229 <Class ZipEntry>;
        ((ZipEntry) (obj1)).ZipEntry(((String) (obj)));
        outputstream.putNextEntry(((ZipEntry) (obj1)));
        obj1 = JVM INSTR new #50  <Class File>;
        ((File) (obj1)).File(mBasePath, ((String) (obj)));
        obj = JVM INSTR new #125 <Class FileInputStream>;
        ((FileInputStream) (obj)).FileInputStream(((File) (obj1)));
        Streams.copy(((InputStream) (obj)), outputstream);
        IoUtils.closeQuietly(((AutoCloseable) (obj)));
        outputstream.closeEntry();
        i++;
        if(true) goto _L2; else goto _L1
        Exception exception;
        exception;
        IoUtils.closeQuietly(((AutoCloseable) (obj)));
        throw exception;
        exception;
        IoUtils.closeQuietly(outputstream);
        throw exception;
_L1:
        IoUtils.closeQuietly(outputstream);
        return;
    }

    public void maybeRotate(long l)
    {
        long l1 = mRotateAgeMillis;
        long l2 = mDeleteAgeMillis;
        FileInfo fileinfo = new FileInfo(mPrefix);
        String as[] = mBasePath.list();
        if(as == null)
            return;
        int i = 0;
        int j = as.length;
        while(i < j) 
        {
            String s = as[i];
            if(fileinfo.parse(s))
                if(fileinfo.isActive())
                {
                    if(fileinfo.startMillis <= l - l1)
                    {
                        fileinfo.endMillis = l;
                        (new File(mBasePath, s)).renameTo(new File(mBasePath, fileinfo.build()));
                    }
                } else
                if(fileinfo.endMillis <= l - l2)
                    (new File(mBasePath, s)).delete();
            i++;
        }
    }

    public void readMatching(Reader reader, long l, long l1)
        throws IOException
    {
        FileInfo fileinfo = new FileInfo(mPrefix);
        String as[] = mBasePath.list();
        int i = 0;
        int j = as.length;
        do
        {
            if(i >= j)
                break;
            String s = as[i];
            if(fileinfo.parse(s) && fileinfo.startMillis <= l1 && l <= fileinfo.endMillis)
                readFile(new File(mBasePath, s), reader);
            i++;
        } while(true);
    }

    public void rewriteActive(Rewriter rewriter, long l)
        throws IOException
    {
        rewriteSingle(rewriter, getActiveName(l));
    }

    public void rewriteAll(Rewriter rewriter)
        throws IOException
    {
        FileInfo fileinfo = new FileInfo(mPrefix);
        String as[] = mBasePath.list();
        int i = 0;
        int j = as.length;
        while(i < j) 
        {
            String s = as[i];
            if(fileinfo.parse(s))
                rewriteSingle(rewriter, s);
            i++;
        }
    }

    private static final boolean LOGD = false;
    private static final String SUFFIX_BACKUP = ".backup";
    private static final String SUFFIX_NO_BACKUP = ".no_backup";
    private static final String TAG = "FileRotator";
    private final File mBasePath;
    private final long mDeleteAgeMillis;
    private final String mPrefix;
    private final long mRotateAgeMillis;
}
