// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.backup;

import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeMap;

// Referenced classes of package android.app.backup:
//            BackupDataOutput, BackupHelper, BackupDataInputStream, BackupDataInput

public class BackupHelperDispatcher
{
    private static class Header
    {

        int chunkSize;
        String keyPrefix;

        private Header()
        {
        }

        Header(Header header)
        {
            this();
        }
    }


    public BackupHelperDispatcher()
    {
        mHelpers = new TreeMap();
    }

    private static native int allocateHeader_native(Header header, FileDescriptor filedescriptor);

    private void doOneBackup(ParcelFileDescriptor parcelfiledescriptor, BackupDataOutput backupdataoutput, ParcelFileDescriptor parcelfiledescriptor1, Header header, BackupHelper backuphelper)
        throws IOException
    {
        FileDescriptor filedescriptor = parcelfiledescriptor1.getFileDescriptor();
        int i = allocateHeader_native(header, filedescriptor);
        if(i < 0)
            throw new IOException((new StringBuilder()).append("allocateHeader_native failed (error ").append(i).append(")").toString());
        backupdataoutput.setKeyPrefix(header.keyPrefix);
        backuphelper.performBackup(parcelfiledescriptor, backupdataoutput, parcelfiledescriptor1);
        i = writeHeader_native(header, filedescriptor, i);
        if(i != 0)
            throw new IOException((new StringBuilder()).append("writeHeader_native failed (error ").append(i).append(")").toString());
        else
            return;
    }

    private static native int readHeader_native(Header header, FileDescriptor filedescriptor);

    private static native int skipChunk_native(FileDescriptor filedescriptor, int i);

    private static native int writeHeader_native(Header header, FileDescriptor filedescriptor, int i);

    public void addHelper(String s, BackupHelper backuphelper)
    {
        mHelpers.put(s, backuphelper);
    }

    public void performBackup(ParcelFileDescriptor parcelfiledescriptor, BackupDataOutput backupdataoutput, ParcelFileDescriptor parcelfiledescriptor1)
        throws IOException
    {
        Header header = new Header(null);
        Object obj = (TreeMap)mHelpers.clone();
        if(parcelfiledescriptor != null)
        {
            FileDescriptor filedescriptor = parcelfiledescriptor.getFileDescriptor();
            do
            {
                int i = readHeader_native(header, filedescriptor);
                if(i < 0)
                    break;
                if(i == 0)
                {
                    BackupHelper backuphelper = (BackupHelper)((TreeMap) (obj)).get(header.keyPrefix);
                    Log.d("BackupHelperDispatcher", (new StringBuilder()).append("handling existing helper '").append(header.keyPrefix).append("' ").append(backuphelper).toString());
                    if(backuphelper != null)
                    {
                        doOneBackup(parcelfiledescriptor, backupdataoutput, parcelfiledescriptor1, header, backuphelper);
                        ((TreeMap) (obj)).remove(header.keyPrefix);
                    } else
                    {
                        skipChunk_native(filedescriptor, header.chunkSize);
                    }
                }
            } while(true);
        }
        java.util.Map.Entry entry;
        for(obj = ((TreeMap) (obj)).entrySet().iterator(); ((Iterator) (obj)).hasNext(); doOneBackup(parcelfiledescriptor, backupdataoutput, parcelfiledescriptor1, header, (BackupHelper)entry.getValue()))
        {
            entry = (java.util.Map.Entry)((Iterator) (obj)).next();
            header.keyPrefix = (String)entry.getKey();
            Log.d("BackupHelperDispatcher", (new StringBuilder()).append("handling new helper '").append(header.keyPrefix).append("'").toString());
        }

    }

    public void performRestore(BackupDataInput backupdatainput, int i, ParcelFileDescriptor parcelfiledescriptor)
        throws IOException
    {
        int j = 0;
        BackupDataInputStream backupdatainputstream = new BackupDataInputStream(backupdatainput);
        while(backupdatainput.readNextHeader()) 
        {
            String s = backupdatainput.getKey();
            i = s.indexOf(':');
            if(i > 0)
            {
                Object obj = s.substring(0, i);
                obj = (BackupHelper)mHelpers.get(obj);
                if(obj != null)
                {
                    backupdatainputstream.dataSize = backupdatainput.getDataSize();
                    backupdatainputstream.key = s.substring(i + 1);
                    ((BackupHelper) (obj)).restoreEntity(backupdatainputstream);
                    i = j;
                } else
                {
                    i = j;
                    if(j == 0)
                    {
                        Log.w("BackupHelperDispatcher", (new StringBuilder()).append("Couldn't find helper for: '").append(s).append("'").toString());
                        i = 1;
                    }
                }
            } else
            {
                i = j;
                if(j == 0)
                {
                    Log.w("BackupHelperDispatcher", (new StringBuilder()).append("Entity with no prefix: '").append(s).append("'").toString());
                    i = 1;
                }
            }
            backupdatainput.skipEntityData();
            j = i;
        }
        for(backupdatainput = mHelpers.values().iterator(); backupdatainput.hasNext(); ((BackupHelper)backupdatainput.next()).writeNewStateDescription(parcelfiledescriptor));
    }

    private static final String TAG = "BackupHelperDispatcher";
    TreeMap mHelpers;
}
