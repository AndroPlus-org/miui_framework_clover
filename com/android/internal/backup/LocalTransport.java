// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.backup;

import android.app.backup.*;
import android.content.*;
import android.content.pm.PackageInfo;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.util.ArrayMap;
import android.util.Log;
import com.android.org.bouncycastle.util.encoders.Base64;
import java.io.*;
import java.util.*;
import libcore.io.IoUtils;

public class LocalTransport extends BackupTransport
{
    static class DecodedFilename
        implements Comparable
    {

        public int compareTo(DecodedFilename decodedfilename)
        {
            return key.compareTo(decodedfilename.key);
        }

        public volatile int compareTo(Object obj)
        {
            return compareTo((DecodedFilename)obj);
        }

        public File file;
        public String key;

        public DecodedFilename(File file1)
        {
            file = file1;
            key = new String(Base64.decode(file1.getName()));
        }
    }

    private class KVOperation
    {

        final String key;
        final LocalTransport this$0;
        final byte value[];

        KVOperation(String s, byte abyte0[])
        {
            this$0 = LocalTransport.this;
            super();
            key = s;
            value = abyte0;
        }
    }


    public LocalTransport(Context context)
    {
        mDataDir = new File(Environment.getDownloadCacheDirectory(), "backup");
        mCurrentSetDir = new File(mDataDir, Long.toString(1L));
        mCurrentSetIncrementalDir = new File(mCurrentSetDir, "_delta");
        mCurrentSetFullDir = new File(mCurrentSetDir, "_full");
        mRestorePackages = null;
        mRestorePackage = -1;
        mContext = context;
        makeDataDirs();
    }

    private ArrayList contentsByKey(File file)
    {
        int i = 0;
        file = file.listFiles();
        if(file == null || file.length == 0)
            return null;
        ArrayList arraylist = new ArrayList();
        for(int j = file.length; i < j; i++)
            arraylist.add(new DecodedFilename(file[i]));

        Collections.sort(arraylist);
        return arraylist;
    }

    private void deleteContents(File file)
    {
        File afile[] = file.listFiles();
        if(afile != null)
        {
            int i = 0;
            for(int j = afile.length; i < j; i++)
            {
                file = afile[i];
                if(file.isDirectory())
                    deleteContents(file);
                file.delete();
            }

        }
    }

    private void makeDataDirs()
    {
        mCurrentSetDir.mkdirs();
        mCurrentSetFullDir.mkdir();
        mCurrentSetIncrementalDir.mkdir();
    }

    private ArrayList parseBackupStream(ParcelFileDescriptor parcelfiledescriptor)
        throws IOException
    {
        ArrayList arraylist = new ArrayList();
        BackupDataInput backupdatainput = new BackupDataInput(parcelfiledescriptor.getFileDescriptor());
        while(backupdatainput.readNextHeader()) 
        {
            String s = new String(Base64.encode(backupdatainput.getKey().getBytes()));
            int i = backupdatainput.getDataSize();
            if(i >= 0)
                parcelfiledescriptor = new byte[i];
            else
                parcelfiledescriptor = null;
            if(i >= 0)
                backupdatainput.readEntityData(parcelfiledescriptor, 0, i);
            arraylist.add(new KVOperation(s, parcelfiledescriptor));
        }
        return arraylist;
    }

    private int parseKeySizes(File file, ArrayMap arraymap)
    {
        int i = 0;
        int j = 0;
        String as[] = file.list();
        if(as != null)
        {
            int k = 0;
            int l = as.length;
            do
            {
                i = j;
                if(k >= l)
                    break;
                String s = as[k];
                i = (int)(new File(file, s)).length();
                j += i;
                arraymap.put(s, Integer.valueOf(i));
                k++;
            } while(true);
        }
        return i;
    }

    private void resetFullRestoreState()
    {
        IoUtils.closeQuietly(mCurFullRestoreStream);
        mCurFullRestoreStream = null;
        mFullRestoreSocketStream = null;
        mFullRestoreBuffer = null;
    }

    private File tarballFile(String s)
    {
        return new File(mCurrentSetFullDir, s);
    }

    private int tearDownFullBackup()
    {
        if(mSocket == null)
            break MISSING_BLOCK_LABEL_55;
        try
        {
            if(mFullBackupOutputStream != null)
            {
                mFullBackupOutputStream.flush();
                mFullBackupOutputStream.close();
            }
            mSocketInputStream = null;
            mFullTargetPackage = null;
            mSocket.close();
        }
        catch(IOException ioexception)
        {
            mSocket = null;
            mFullBackupOutputStream = null;
            return -1000;
        }
        mSocket = null;
        mFullBackupOutputStream = null;
        return 0;
        Exception exception;
        exception;
        mSocket = null;
        mFullBackupOutputStream = null;
        throw exception;
    }

    public int abortFullRestore()
    {
        if(mRestoreType != 2)
        {
            throw new IllegalStateException("abortFullRestore() but not currently restoring");
        } else
        {
            resetFullRestoreState();
            mRestoreType = 0;
            return 0;
        }
    }

    public void cancelFullBackup()
    {
        File file = tarballFile(mFullTargetPackage);
        tearDownFullBackup();
        if(file.exists())
            file.delete();
    }

    public int checkFullBackupSize(long l)
    {
        char c = '\0';
        if(l > 0L) goto _L2; else goto _L1
_L1:
        c = '\uFC16';
_L4:
        return c;
_L2:
        if(l > 0x1900000L)
            c = '\uFC13';
        if(true) goto _L4; else goto _L3
_L3:
    }

    public int clearBackupData(PackageInfo packageinfo)
    {
        File file = new File(mCurrentSetIncrementalDir, packageinfo.packageName);
        File afile1[] = file.listFiles();
        if(afile1 != null)
        {
            int i = afile1.length;
            for(int k = 0; k < i; k++)
                afile1[k].delete();

            file.delete();
        }
        packageinfo = new File(mCurrentSetFullDir, packageinfo.packageName);
        File afile[] = packageinfo.listFiles();
        if(afile != null)
        {
            int j = afile.length;
            for(int l = 0; l < j; l++)
                afile[l].delete();

            packageinfo.delete();
        }
        return 0;
    }

    public Intent configurationIntent()
    {
        return null;
    }

    public String currentDestinationString()
    {
        return "Backing up to debug-only private cache";
    }

    public Intent dataManagementIntent()
    {
        return null;
    }

    public String dataManagementLabel()
    {
        return "";
    }

    public int finishBackup()
    {
        return tearDownFullBackup();
    }

    public void finishRestore()
    {
        if(mRestoreType == 2)
            resetFullRestoreState();
        mRestoreType = 0;
    }

    public RestoreSet[] getAvailableRestoreSets()
    {
        long al[] = new long[POSSIBLE_SETS.length + 1];
        Object aobj[] = POSSIBLE_SETS;
        int i = 0;
        int j = aobj.length;
        int k = 0;
        for(; i < j; i++)
        {
            long l1 = aobj[i];
            if((new File(mDataDir, Long.toString(l1))).exists())
            {
                int i1 = k + 1;
                al[k] = l1;
                k = i1;
            }
        }

        al[k] = 1L;
        aobj = new RestoreSet[k + 1];
        for(int l = 0; l < aobj.length; l++)
            aobj[l] = new RestoreSet("Local disk image", "flash", al[l]);

        return ((RestoreSet []) (aobj));
    }

    public long getBackupQuota(String s, boolean flag)
    {
        long l;
        if(flag)
            l = 0x1900000L;
        else
            l = 0x500000L;
        return l;
    }

    public long getCurrentRestoreSet()
    {
        return 1L;
    }

    public int getNextFullRestoreDataChunk(ParcelFileDescriptor parcelfiledescriptor)
    {
        int i;
        if(mRestoreType != 2)
            throw new IllegalStateException("Asked for full restore data for non-stream package");
        if(mCurFullRestoreStream == null)
        {
            String s = mRestorePackages[mRestorePackage].packageName;
            File file = new File(mRestoreSetFullDir, s);
            try
            {
                FileInputStream fileinputstream = JVM INSTR new #338 <Class FileInputStream>;
                fileinputstream.FileInputStream(file);
                mCurFullRestoreStream = fileinputstream;
            }
            // Misplaced declaration of an exception variable
            catch(ParcelFileDescriptor parcelfiledescriptor)
            {
                Log.e("LocalTransport", (new StringBuilder()).append("Unable to read archive for ").append(s).toString());
                return -1002;
            }
            mFullRestoreSocketStream = new FileOutputStream(parcelfiledescriptor.getFileDescriptor());
            mFullRestoreBuffer = new byte[2048];
        }
        try
        {
            i = mCurFullRestoreStream.read(mFullRestoreBuffer);
        }
        // Misplaced declaration of an exception variable
        catch(ParcelFileDescriptor parcelfiledescriptor)
        {
            return -1000;
        }
        if(i >= 0) goto _L2; else goto _L1
_L1:
        i = -1;
_L3:
        return i;
_L2:
        if(i != 0)
            break MISSING_BLOCK_LABEL_168;
        Log.w("LocalTransport", "read() of archive file returned 0; treating as EOF");
        i = -1;
          goto _L3
        mFullRestoreSocketStream.write(mFullRestoreBuffer, 0, i);
          goto _L3
        parcelfiledescriptor;
        throw parcelfiledescriptor;
    }

    public int getRestoreData(ParcelFileDescriptor parcelfiledescriptor)
    {
        Object obj;
        if(mRestorePackages == null)
            throw new IllegalStateException("startRestore not called");
        if(mRestorePackage < 0)
            throw new IllegalStateException("nextRestorePackage not called");
        if(mRestoreType != 1)
            throw new IllegalStateException("getRestoreData(fd) for non-key/value dataset");
        File file = new File(mRestoreSetIncrementalDir, mRestorePackages[mRestorePackage].packageName);
        obj = contentsByKey(file);
        if(obj == null)
        {
            Log.e("LocalTransport", (new StringBuilder()).append("No keys for package: ").append(file).toString());
            return -1000;
        }
        parcelfiledescriptor = new BackupDataOutput(parcelfiledescriptor.getFileDescriptor());
        Iterator iterator = ((Iterable) (obj)).iterator();
_L1:
        DecodedFilename decodedfilename;
        File file1;
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_246;
        decodedfilename = (DecodedFilename)iterator.next();
        file1 = decodedfilename.file;
        obj = JVM INSTR new #338 <Class FileInputStream>;
        ((FileInputStream) (obj)).FileInputStream(file1);
        int i = (int)file1.length();
        byte abyte0[] = new byte[i];
        ((FileInputStream) (obj)).read(abyte0);
        parcelfiledescriptor.writeEntityHeader(decodedfilename.key, i);
        parcelfiledescriptor.writeEntityData(abyte0, i);
        try
        {
            ((FileInputStream) (obj)).close();
        }
        // Misplaced declaration of an exception variable
        catch(ParcelFileDescriptor parcelfiledescriptor)
        {
            Log.e("LocalTransport", "Unable to read backup records", parcelfiledescriptor);
            return -1000;
        }
          goto _L1
        parcelfiledescriptor;
        ((FileInputStream) (obj)).close();
        throw parcelfiledescriptor;
        return 0;
    }

    public int initializeDevice()
    {
        deleteContents(mCurrentSetDir);
        makeDataDirs();
        return 0;
    }

    public String name()
    {
        return (new ComponentName(mContext, getClass())).flattenToShortString();
    }

    public RestoreDescription nextRestorePackage()
    {
        if(mRestorePackages == null)
            throw new IllegalStateException("startRestore not called");
        boolean flag = false;
        do
        {
            int i = mRestorePackage + 1;
            mRestorePackage = i;
            if(i < mRestorePackages.length)
            {
                String s = mRestorePackages[mRestorePackage].packageName;
                String as[] = (new File(mRestoreSetIncrementalDir, s)).list();
                boolean flag2 = flag;
                if(as != null)
                {
                    flag2 = flag;
                    if(as.length > 0)
                    {
                        mRestoreType = 1;
                        flag2 = true;
                    }
                }
                boolean flag1 = flag2;
                if(!flag2)
                {
                    flag1 = flag2;
                    if((new File(mRestoreSetFullDir, s)).length() > 0L)
                    {
                        mRestoreType = 2;
                        mCurFullRestoreStream = null;
                        flag1 = true;
                    }
                }
                flag = flag1;
                if(flag1)
                    return new RestoreDescription(s, mRestoreType);
            } else
            {
                return RestoreDescription.NO_MORE_PACKAGES;
            }
        } while(true);
    }

    public int performBackup(PackageInfo packageinfo, ParcelFileDescriptor parcelfiledescriptor)
    {
        File file;
        Iterator iterator1;
        file = new File(mCurrentSetIncrementalDir, packageinfo.packageName);
        file.mkdirs();
        ArrayList arraylist;
        int i;
        Iterator iterator;
        try
        {
            arraylist = parseBackupStream(parcelfiledescriptor);
        }
        // Misplaced declaration of an exception variable
        catch(PackageInfo packageinfo)
        {
            Log.v("LocalTransport", "Exception reading backup input", packageinfo);
            return -1000;
        }
        parcelfiledescriptor = new ArrayMap();
        i = parseKeySizes(file, parcelfiledescriptor);
        iterator = arraylist.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            KVOperation kvoperation = (KVOperation)iterator.next();
            packageinfo = (Integer)parcelfiledescriptor.get(kvoperation.key);
            int j = i;
            if(packageinfo != null)
                j = i - packageinfo.intValue();
            i = j;
            if(kvoperation.value != null)
                i = j + kvoperation.value.length;
        } while(true);
        if((long)i > 0x500000L)
            return -1005;
        iterator1 = arraylist.iterator();
_L4:
        if(!iterator1.hasNext()) goto _L2; else goto _L1
_L1:
        KVOperation kvoperation1;
        File file1;
        kvoperation1 = (KVOperation)iterator1.next();
        file1 = new File(file, kvoperation1.key);
        file1.delete();
        if(kvoperation1.value == null) goto _L4; else goto _L3
_L3:
        Object obj;
        Object obj1;
        Object obj2;
        obj1 = null;
        obj = null;
        obj2 = null;
        parcelfiledescriptor = null;
        packageinfo = JVM INSTR new #341 <Class FileOutputStream>;
        packageinfo.FileOutputStream(file1);
        packageinfo.write(kvoperation1.value, 0, kvoperation1.value.length);
        parcelfiledescriptor = obj;
        if(packageinfo == null) goto _L6; else goto _L5
_L5:
        packageinfo.close();
        parcelfiledescriptor = obj;
_L6:
        if(parcelfiledescriptor == null) goto _L4; else goto _L7
_L7:
        try
        {
            throw parcelfiledescriptor;
        }
        // Misplaced declaration of an exception variable
        catch(PackageInfo packageinfo) { }
_L8:
        Log.e("LocalTransport", (new StringBuilder()).append("Unable to update key file ").append(file1).toString());
        return -1000;
        parcelfiledescriptor;
          goto _L6
        packageinfo;
_L12:
        throw packageinfo;
        obj1;
        obj2 = packageinfo;
        packageinfo = ((PackageInfo) (obj1));
_L11:
        obj1 = obj2;
        if(parcelfiledescriptor == null)
            break MISSING_BLOCK_LABEL_340;
        parcelfiledescriptor.close();
        obj1 = obj2;
_L9:
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_387;
        try
        {
            throw obj1;
        }
        // Misplaced declaration of an exception variable
        catch(PackageInfo packageinfo) { }
          goto _L8
        parcelfiledescriptor;
label0:
        {
            if(obj2 != null)
                break label0;
            obj1 = parcelfiledescriptor;
        }
          goto _L9
        obj1 = obj2;
        if(obj2 == parcelfiledescriptor) goto _L9; else goto _L10
_L10:
        ((Throwable) (obj2)).addSuppressed(parcelfiledescriptor);
        obj1 = obj2;
          goto _L9
        throw packageinfo;
_L2:
        return 0;
        packageinfo;
        parcelfiledescriptor = ((ParcelFileDescriptor) (obj2));
        obj2 = obj1;
          goto _L11
        Object obj3;
        obj3;
        parcelfiledescriptor = packageinfo;
        packageinfo = ((PackageInfo) (obj3));
        obj3 = obj1;
          goto _L11
        obj3;
        parcelfiledescriptor = packageinfo;
        packageinfo = ((PackageInfo) (obj3));
          goto _L12
    }

    public int performFullBackup(PackageInfo packageinfo, ParcelFileDescriptor parcelfiledescriptor)
    {
        if(mSocket != null)
        {
            Log.e("LocalTransport", "Attempt to initiate full backup while one is in progress");
            return -1000;
        }
        try
        {
            mFullBackupSize = 0L;
            mSocket = ParcelFileDescriptor.dup(parcelfiledescriptor.getFileDescriptor());
            parcelfiledescriptor = JVM INSTR new #338 <Class FileInputStream>;
            parcelfiledescriptor.FileInputStream(mSocket.getFileDescriptor());
            mSocketInputStream = parcelfiledescriptor;
        }
        // Misplaced declaration of an exception variable
        catch(PackageInfo packageinfo)
        {
            Log.e("LocalTransport", "Unable to process socket for full backup");
            return -1000;
        }
        mFullTargetPackage = packageinfo.packageName;
        mFullBackupBuffer = new byte[4096];
        return 0;
    }

    public long requestBackupTime()
    {
        return 0L;
    }

    public long requestFullBackupTime()
    {
        return 0L;
    }

    public int sendBackupData(int i)
    {
        int j;
        if(mSocket == null)
        {
            Log.w("LocalTransport", "Attempted sendBackupData before performFullBackup");
            return -1000;
        }
        mFullBackupSize = mFullBackupSize + (long)i;
        if(mFullBackupSize > 0x1900000L)
            return -1005;
        if(i > mFullBackupBuffer.length)
            mFullBackupBuffer = new byte[i];
        if(mFullBackupOutputStream == null)
        {
            FileOutputStream fileoutputstream;
            try
            {
                fileoutputstream = new FileOutputStream(tarballFile(mFullTargetPackage));
            }
            catch(FileNotFoundException filenotfoundexception)
            {
                return -1000;
            }
            mFullBackupOutputStream = new BufferedOutputStream(fileoutputstream);
        }
_L2:
        if(i <= 0)
            break; /* Loop/switch isn't completed */
        try
        {
            j = mSocketInputStream.read(mFullBackupBuffer, 0, i);
        }
        catch(IOException ioexception)
        {
            Log.e("LocalTransport", (new StringBuilder()).append("Error handling backup data for ").append(mFullTargetPackage).toString());
            return -1000;
        }
        if(j >= 0)
            break MISSING_BLOCK_LABEL_137;
        Log.w("LocalTransport", "Unexpected EOD; failing backup");
        return -1000;
        mFullBackupOutputStream.write(mFullBackupBuffer, 0, j);
        i -= j;
        if(true) goto _L2; else goto _L1
_L1:
        return 0;
    }

    public int startRestore(long l, PackageInfo apackageinfo[])
    {
        mRestorePackages = apackageinfo;
        mRestorePackage = -1;
        mRestoreSetDir = new File(mDataDir, Long.toString(l));
        mRestoreSetIncrementalDir = new File(mRestoreSetDir, "_delta");
        mRestoreSetFullDir = new File(mRestoreSetDir, "_full");
        return 0;
    }

    public String transportDirName()
    {
        return "com.android.internal.backup.LocalTransport";
    }

    private static final long CURRENT_SET_TOKEN = 1L;
    private static final boolean DEBUG = false;
    private static final long FULL_BACKUP_SIZE_QUOTA = 0x1900000L;
    private static final String FULL_DATA_DIR = "_full";
    private static final String INCREMENTAL_DIR = "_delta";
    private static final long KEY_VALUE_BACKUP_SIZE_QUOTA = 0x500000L;
    static final long POSSIBLE_SETS[] = {
        2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L
    };
    private static final String TAG = "LocalTransport";
    private static final String TRANSPORT_DATA_MANAGEMENT_LABEL = "";
    private static final String TRANSPORT_DESTINATION_STRING = "Backing up to debug-only private cache";
    private static final String TRANSPORT_DIR_NAME = "com.android.internal.backup.LocalTransport";
    private Context mContext;
    private FileInputStream mCurFullRestoreStream;
    private File mCurrentSetDir;
    private File mCurrentSetFullDir;
    private File mCurrentSetIncrementalDir;
    private File mDataDir;
    private byte mFullBackupBuffer[];
    private BufferedOutputStream mFullBackupOutputStream;
    private long mFullBackupSize;
    private byte mFullRestoreBuffer[];
    private FileOutputStream mFullRestoreSocketStream;
    private String mFullTargetPackage;
    private int mRestorePackage;
    private PackageInfo mRestorePackages[];
    private File mRestoreSetDir;
    private File mRestoreSetFullDir;
    private File mRestoreSetIncrementalDir;
    private int mRestoreType;
    private ParcelFileDescriptor mSocket;
    private FileInputStream mSocketInputStream;

}
