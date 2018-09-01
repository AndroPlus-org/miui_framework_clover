// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.*;

// Referenced classes of package android.media:
//            MiniThumbFileInjector

public class MiniThumbFile
{

    public MiniThumbFile(Uri uri)
    {
        mUri = uri;
        mBuffer = ByteBuffer.allocateDirect(10000);
    }

    public static MiniThumbFile instance(Uri uri)
    {
        android/media/MiniThumbFile;
        JVM INSTR monitorenter ;
        String s;
        MiniThumbFile minithumbfile;
        s = (String)uri.getPathSegments().get(1);
        minithumbfile = (MiniThumbFile)sThumbFiles.get(s);
        uri = minithumbfile;
        if(minithumbfile != null)
            break MISSING_BLOCK_LABEL_80;
        uri = JVM INSTR new #2   <Class MiniThumbFile>;
        StringBuilder stringbuilder = JVM INSTR new #69  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        uri.MiniThumbFile(Uri.parse(stringbuilder.append("content://media/external/").append(s).append("/media").toString()));
        sThumbFiles.put(s, uri);
        android/media/MiniThumbFile;
        JVM INSTR monitorexit ;
        return uri;
        uri;
        throw uri;
    }

    private RandomAccessFile miniThumbDataFile()
    {
        if(mMiniThumbFile == null)
        {
            removeOldFile();
            String s = randomAccessFilePath(4);
            File file = (new File(s)).getParentFile();
            if(!file.isDirectory() && !file.mkdirs())
                Log.e("MiniThumbFile", (new StringBuilder()).append("Unable to create .thumbnails directory ").append(file.toString()).toString());
            file = new File(s);
            try
            {
                RandomAccessFile randomaccessfile = JVM INSTR new #132 <Class RandomAccessFile>;
                randomaccessfile.RandomAccessFile(file, "rw");
                mMiniThumbFile = randomaccessfile;
            }
            catch(IOException ioexception)
            {
                try
                {
                    RandomAccessFile randomaccessfile1 = JVM INSTR new #132 <Class RandomAccessFile>;
                    randomaccessfile1.RandomAccessFile(file, "r");
                    mMiniThumbFile = randomaccessfile1;
                }
                catch(IOException ioexception1) { }
            }
            if(mMiniThumbFile != null)
                mChannel = mMiniThumbFile.getChannel();
        }
        return mMiniThumbFile;
    }

    private String randomAccessFilePath(int i)
    {
        String s = (new StringBuilder()).append(Environment.getExternalStorageDirectory().toString()).append("/DCIM/.thumbnails").toString();
        return (new StringBuilder()).append(s).append("/.thumbdata").append(i).append("-").append(mUri.hashCode()).toString();
    }

    private void removeOldFile()
    {
        File file;
        file = new File(randomAccessFilePath(3));
        if(!file.exists())
            break MISSING_BLOCK_LABEL_25;
        file.delete();
_L2:
        return;
        SecurityException securityexception;
        securityexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static void reset()
    {
        android/media/MiniThumbFile;
        JVM INSTR monitorenter ;
        for(Iterator iterator = sThumbFiles.values().iterator(); iterator.hasNext(); ((MiniThumbFile)iterator.next()).deactivate());
        break MISSING_BLOCK_LABEL_45;
        Exception exception;
        exception;
        throw exception;
        sThumbFiles.clear();
        android/media/MiniThumbFile;
        JVM INSTR monitorexit ;
    }

    public void deactivate()
    {
        this;
        JVM INSTR monitorenter ;
        RandomAccessFile randomaccessfile = mMiniThumbFile;
        if(randomaccessfile == null)
            break MISSING_BLOCK_LABEL_23;
        Exception exception;
        try
        {
            mMiniThumbFile.close();
            mMiniThumbFile = null;
        }
        catch(IOException ioexception) { }
        this;
        JVM INSTR monitorexit ;
        return;
        exception;
        throw exception;
    }

    public long getMagic(long l)
    {
        this;
        JVM INSTR monitorenter ;
        long l1;
        if(miniThumbDataFile() == null)
            break MISSING_BLOCK_LABEL_213;
        l1 = MiniThumbFileInjector.getPosition(l);
        Object obj;
        Object obj1;
        FileLock filelock;
        FileLock filelock1;
        Object obj3;
        Object obj4;
        obj = null;
        obj1 = null;
        filelock = null;
        filelock1 = filelock;
        obj3 = obj;
        obj4 = obj1;
        mBuffer.clear();
        filelock1 = filelock;
        obj3 = obj;
        obj4 = obj1;
        mBuffer.limit(17);
        filelock1 = filelock;
        obj3 = obj;
        obj4 = obj1;
        filelock = mChannel.lock(l1, 17L, true);
        filelock1 = filelock;
        obj3 = filelock;
        obj4 = filelock;
        if(mChannel.read(mBuffer, l1) != 9)
            break MISSING_BLOCK_LABEL_203;
        filelock1 = filelock;
        obj3 = filelock;
        obj4 = filelock;
        mBuffer.position(0);
        filelock1 = filelock;
        obj3 = filelock;
        obj4 = filelock;
        if(!MiniThumbFileInjector.isMatch(mBuffer, l))
            break MISSING_BLOCK_LABEL_203;
        filelock1 = filelock;
        obj3 = filelock;
        obj4 = filelock;
        l1 = mBuffer.getLong();
        if(filelock == null)
            break MISSING_BLOCK_LABEL_194;
        try
        {
            filelock.release();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj4) { }
        this;
        JVM INSTR monitorexit ;
        return l1;
        if(filelock == null)
            break MISSING_BLOCK_LABEL_213;
        try
        {
            filelock.release();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj4) { }
_L2:
        this;
        JVM INSTR monitorexit ;
        return 0L;
        RuntimeException runtimeexception;
        runtimeexception;
        obj4 = filelock1;
        obj3 = JVM INSTR new #69  <Class StringBuilder>;
        obj4 = filelock1;
        ((StringBuilder) (obj3)).StringBuilder();
        obj4 = filelock1;
        Log.e("MiniThumbFile", ((StringBuilder) (obj3)).append("Got exception when reading magic, id = ").append(l).append(", disk full or mount read-only? ").append(runtimeexception.getClass()).toString());
        if(filelock1 == null) goto _L2; else goto _L1
_L1:
        try
        {
            filelock1.release();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj4) { }
          goto _L2
        Object obj2;
        obj2;
        obj4 = obj3;
        Log.v("MiniThumbFile", "Got exception checking file magic: ", ((Throwable) (obj2)));
        if(obj3 == null) goto _L2; else goto _L3
_L3:
        try
        {
            ((FileLock) (obj3)).release();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj4) { }
          goto _L2
        obj2;
        if(obj4 == null)
            break MISSING_BLOCK_LABEL_344;
        Exception exception;
        try
        {
            ((FileLock) (obj4)).release();
        }
        catch(IOException ioexception) { }
        throw obj2;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public byte[] getMiniThumbFromFile(long l, byte abyte0[])
    {
        this;
        JVM INSTR monitorenter ;
        Object obj = miniThumbDataFile();
        if(obj != null)
            break MISSING_BLOCK_LABEL_17;
        this;
        JVM INSTR monitorexit ;
        return null;
        long l1 = MiniThumbFileInjector.getPosition(l);
        Object obj1;
        Object obj2;
        FileLock filelock;
        FileLock filelock1;
        Object obj3;
        obj1 = null;
        obj2 = null;
        filelock = null;
        filelock1 = filelock;
        obj3 = obj1;
        obj = obj2;
        mBuffer.clear();
        filelock1 = filelock;
        obj3 = obj1;
        obj = obj2;
        filelock = mChannel.lock(l1, 10000L, true);
        filelock1 = filelock;
        obj3 = filelock;
        obj = filelock;
        int i = mChannel.read(mBuffer, l1);
        if(i <= 21)
            break MISSING_BLOCK_LABEL_307;
        filelock1 = filelock;
        obj3 = filelock;
        obj = filelock;
        mBuffer.position(0);
        filelock1 = filelock;
        obj3 = filelock;
        obj = filelock;
        byte byte0 = mBuffer.get();
        filelock1 = filelock;
        obj3 = filelock;
        obj = filelock;
        mBuffer.getLong();
        filelock1 = filelock;
        obj3 = filelock;
        obj = filelock;
        l1 = mBuffer.getLong();
        filelock1 = filelock;
        obj3 = filelock;
        obj = filelock;
        int j = mBuffer.getInt();
        if(i < j + 21 || j == 0 || l1 == 0L || byte0 != 1)
            break MISSING_BLOCK_LABEL_307;
        filelock1 = filelock;
        obj3 = filelock;
        obj = filelock;
        if(abyte0.length < j)
            break MISSING_BLOCK_LABEL_307;
        filelock1 = filelock;
        obj3 = filelock;
        obj = filelock;
        mBuffer.get(abyte0, 0, j);
        if(filelock == null)
            break MISSING_BLOCK_LABEL_298;
        try
        {
            filelock.release();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj) { }
        this;
        JVM INSTR monitorexit ;
        return abyte0;
        if(filelock == null)
            break MISSING_BLOCK_LABEL_317;
        try
        {
            filelock.release();
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[]) { }
_L2:
        this;
        JVM INSTR monitorexit ;
        return null;
        obj3;
        obj = filelock1;
        abyte0 = JVM INSTR new #69  <Class StringBuilder>;
        obj = filelock1;
        abyte0.StringBuilder();
        obj = filelock1;
        Log.e("MiniThumbFile", abyte0.append("Got exception when reading thumbnail, id = ").append(l).append(", disk full or mount read-only? ").append(((RuntimeException) (obj3)).getClass()).toString());
        if(filelock1 == null) goto _L2; else goto _L1
_L1:
        try
        {
            filelock1.release();
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[]) { }
          goto _L2
        IOException ioexception1;
        ioexception1;
        obj = obj3;
        abyte0 = JVM INSTR new #69  <Class StringBuilder>;
        obj = obj3;
        abyte0.StringBuilder();
        obj = obj3;
        Log.w("MiniThumbFile", abyte0.append("got exception when reading thumbnail id=").append(l).append(", exception: ").append(ioexception1).toString());
        if(obj3 == null) goto _L2; else goto _L3
_L3:
        try
        {
            ((FileLock) (obj3)).release();
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[]) { }
          goto _L2
        abyte0;
        if(obj == null)
            break MISSING_BLOCK_LABEL_478;
        try
        {
            ((FileLock) (obj)).release();
        }
        catch(IOException ioexception) { }
        throw abyte0;
        abyte0;
        this;
        JVM INSTR monitorexit ;
        throw abyte0;
    }

    public void saveMiniThumbToFile(byte abyte0[], long l, long l1)
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        RandomAccessFile randomaccessfile = miniThumbDataFile();
        if(randomaccessfile != null)
            break MISSING_BLOCK_LABEL_16;
        this;
        JVM INSTR monitorexit ;
        return;
        long l2 = MiniThumbFileInjector.getPosition(l);
        byte abyte1[];
        Object obj;
        Object obj1;
        Object obj2;
        byte abyte2[];
        Object obj3;
        obj = null;
        obj1 = null;
        obj2 = null;
        abyte1 = null;
        if(abyte0 == null)
            break MISSING_BLOCK_LABEL_264;
        abyte2 = obj;
        obj3 = obj1;
        abyte1 = obj2;
        int i = abyte0.length;
        if(i <= 9979)
            break MISSING_BLOCK_LABEL_65;
        this;
        JVM INSTR monitorexit ;
        return;
        abyte2 = obj;
        obj3 = obj1;
        abyte1 = obj2;
        mBuffer.clear();
        abyte2 = obj;
        obj3 = obj1;
        abyte1 = obj2;
        mBuffer.put((byte)1);
        abyte2 = obj;
        obj3 = obj1;
        abyte1 = obj2;
        mBuffer.putLong(l);
        abyte2 = obj;
        obj3 = obj1;
        abyte1 = obj2;
        mBuffer.putLong(l1);
        abyte2 = obj;
        obj3 = obj1;
        abyte1 = obj2;
        mBuffer.putInt(abyte0.length);
        abyte2 = obj;
        obj3 = obj1;
        abyte1 = obj2;
        mBuffer.put(abyte0);
        abyte2 = obj;
        obj3 = obj1;
        abyte1 = obj2;
        mBuffer.flip();
        abyte2 = obj;
        obj3 = obj1;
        abyte1 = obj2;
        abyte0 = mChannel.lock(l2, 10000L, false);
        abyte2 = abyte0;
        obj3 = abyte0;
        abyte1 = abyte0;
        mChannel.write(mBuffer, l2);
        abyte1 = abyte0;
        if(abyte1 == null)
            break MISSING_BLOCK_LABEL_274;
        try
        {
            abyte1.release();
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[]) { }
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
        abyte0;
        abyte1 = abyte2;
        obj3 = JVM INSTR new #69  <Class StringBuilder>;
        abyte1 = abyte2;
        ((StringBuilder) (obj3)).StringBuilder();
        abyte1 = abyte2;
        Log.e("MiniThumbFile", ((StringBuilder) (obj3)).append("couldn't save mini thumbnail data for ").append(l).append("; disk full or mount read-only? ").append(abyte0.getClass()).toString());
        if(abyte2 == null) goto _L2; else goto _L1
_L1:
        try
        {
            abyte2.release();
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[]) { }
          goto _L2
        IOException ioexception1;
        ioexception1;
        abyte1 = ((byte []) (obj3));
        abyte0 = JVM INSTR new #69  <Class StringBuilder>;
        abyte1 = ((byte []) (obj3));
        abyte0.StringBuilder();
        abyte1 = ((byte []) (obj3));
        Log.e("MiniThumbFile", abyte0.append("couldn't save mini thumbnail data for ").append(l).append("; ").toString(), ioexception1);
        abyte1 = ((byte []) (obj3));
        throw ioexception1;
        abyte0;
        if(abyte1 == null)
            break MISSING_BLOCK_LABEL_423;
        try
        {
            abyte1.release();
        }
        catch(IOException ioexception) { }
        throw abyte0;
        abyte0;
        this;
        JVM INSTR monitorexit ;
        throw abyte0;
    }

    public static final int BYTES_PER_MINTHUMB = 10000;
    private static final int HEADER_SIZE = 21;
    private static final int MINI_THUMB_DATA_FILE_VERSION = 4;
    private static final String TAG = "MiniThumbFile";
    private static final Hashtable sThumbFiles = new Hashtable();
    private ByteBuffer mBuffer;
    private FileChannel mChannel;
    private RandomAccessFile mMiniThumbFile;
    private Uri mUri;

}
