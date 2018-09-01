// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import android.os.FileUtils;
import java.io.*;
import java.util.function.Consumer;
import libcore.io.IoUtils;

// Referenced classes of package android.util:
//            Log, ExceptionUtils

public class AtomicFile
{

    public AtomicFile(File file)
    {
        mBaseName = file;
        mBackupName = new File((new StringBuilder()).append(file.getPath()).append(".bak").toString());
    }

    public void delete()
    {
        mBaseName.delete();
        mBackupName.delete();
    }

    public boolean exists()
    {
        boolean flag;
        if(!mBaseName.exists())
            flag = mBackupName.exists();
        else
            flag = true;
        return flag;
    }

    public void failWrite(FileOutputStream fileoutputstream)
    {
        if(fileoutputstream == null)
            break MISSING_BLOCK_LABEL_33;
        FileUtils.sync(fileoutputstream);
        fileoutputstream.close();
        mBaseName.delete();
        mBackupName.renameTo(mBaseName);
_L1:
        return;
        fileoutputstream;
        Log.w("AtomicFile", "failWrite: Got exception:", fileoutputstream);
          goto _L1
    }

    public void finishWrite(FileOutputStream fileoutputstream)
    {
        if(fileoutputstream == null)
            break MISSING_BLOCK_LABEL_21;
        FileUtils.sync(fileoutputstream);
        fileoutputstream.close();
        mBackupName.delete();
_L1:
        return;
        fileoutputstream;
        Log.w("AtomicFile", "finishWrite: Got exception:", fileoutputstream);
          goto _L1
    }

    public File getBaseFile()
    {
        return mBaseName;
    }

    public long getLastModifiedTime()
    {
        if(mBackupName.exists())
            return mBackupName.lastModified();
        else
            return mBaseName.lastModified();
    }

    public FileOutputStream openAppend()
        throws IOException
    {
        FileOutputStream fileoutputstream;
        try
        {
            fileoutputstream = new FileOutputStream(mBaseName, true);
        }
        catch(FileNotFoundException filenotfoundexception)
        {
            throw new IOException((new StringBuilder()).append("Couldn't append ").append(mBaseName).toString());
        }
        return fileoutputstream;
    }

    public FileInputStream openRead()
        throws FileNotFoundException
    {
        if(mBackupName.exists())
        {
            mBaseName.delete();
            mBackupName.renameTo(mBaseName);
        }
        return new FileInputStream(mBaseName);
    }

    public byte[] readFully()
        throws IOException
    {
        FileInputStream fileinputstream;
        int i;
        fileinputstream = openRead();
        i = 0;
        byte abyte0[] = new byte[fileinputstream.available()];
_L2:
        int j = fileinputstream.read(abyte0, i, abyte0.length - i);
        if(j <= 0)
        {
            fileinputstream.close();
            return abyte0;
        }
        j = i + j;
        int k = fileinputstream.available();
        i = j;
        if(k <= abyte0.length - j) goto _L2; else goto _L1
_L1:
        byte abyte1[];
        abyte1 = new byte[j + k];
        System.arraycopy(abyte0, 0, abyte1, 0, j);
        abyte0 = abyte1;
        i = j;
          goto _L2
        Exception exception;
        exception;
        fileinputstream.close();
        throw exception;
    }

    public FileOutputStream startWrite()
        throws IOException
    {
        Object obj;
        if(mBaseName.exists())
            if(!mBackupName.exists())
            {
                if(!mBaseName.renameTo(mBackupName))
                    Log.w("AtomicFile", (new StringBuilder()).append("Couldn't rename file ").append(mBaseName).append(" to backup file ").append(mBackupName).toString());
            } else
            {
                mBaseName.delete();
            }
        try
        {
            obj = JVM INSTR new #57  <Class FileOutputStream>;
            ((FileOutputStream) (obj)).FileOutputStream(mBaseName);
        }
        catch(FileNotFoundException filenotfoundexception)
        {
            filenotfoundexception = mBaseName.getParentFile();
            if(!filenotfoundexception.mkdirs())
                throw new IOException((new StringBuilder()).append("Couldn't create directory ").append(mBaseName).toString());
            FileUtils.setPermissions(filenotfoundexception.getPath(), 505, -1, -1);
            try
            {
                filenotfoundexception = new FileOutputStream(mBaseName);
            }
            catch(FileNotFoundException filenotfoundexception1)
            {
                throw new IOException((new StringBuilder()).append("Couldn't create ").append(mBaseName).toString());
            }
        }
        return ((FileOutputStream) (obj));
    }

    public void truncate()
        throws IOException
    {
        FileOutputStream fileoutputstream = JVM INSTR new #57  <Class FileOutputStream>;
        fileoutputstream.FileOutputStream(mBaseName);
        FileUtils.sync(fileoutputstream);
        fileoutputstream.close();
_L2:
        return;
        Object obj;
        obj;
        throw new IOException((new StringBuilder()).append("Couldn't append ").append(mBaseName).toString());
        obj;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void write(Consumer consumer)
    {
        FileOutputStream fileoutputstream;
        FileOutputStream fileoutputstream1;
        fileoutputstream = null;
        fileoutputstream1 = null;
        FileOutputStream fileoutputstream2 = startWrite();
        fileoutputstream1 = fileoutputstream2;
        fileoutputstream = fileoutputstream2;
        consumer.accept(fileoutputstream2);
        fileoutputstream1 = fileoutputstream2;
        fileoutputstream = fileoutputstream2;
        finishWrite(fileoutputstream2);
        IoUtils.closeQuietly(fileoutputstream2);
        return;
        consumer;
        fileoutputstream = fileoutputstream1;
        failWrite(fileoutputstream1);
        fileoutputstream = fileoutputstream1;
        throw ExceptionUtils.propagate(consumer);
        consumer;
        IoUtils.closeQuietly(fileoutputstream);
        throw consumer;
    }

    private final File mBackupName;
    private final File mBaseName;
}
