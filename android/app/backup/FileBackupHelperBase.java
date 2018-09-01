// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.backup;

import android.content.Context;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.File;
import java.io.FileDescriptor;

// Referenced classes of package android.app.backup:
//            BackupDataOutput, BackupDataInputStream, BackupDataInput

class FileBackupHelperBase
{

    FileBackupHelperBase(Context context)
    {
        mPtr = ctor();
        mContext = context;
    }

    private static native long ctor();

    private static native void dtor(long l);

    static void performBackup_checked(ParcelFileDescriptor parcelfiledescriptor, BackupDataOutput backupdataoutput, ParcelFileDescriptor parcelfiledescriptor1, String as[], String as1[])
    {
        if(as.length == 0)
            return;
        int i = as.length;
        for(int j = 0; j < i; j++)
        {
            String s = as[j];
            if(s.charAt(0) != '/')
                throw new RuntimeException((new StringBuilder()).append("files must have all absolute paths: ").append(s).toString());
        }

        if(as.length != as1.length)
            throw new RuntimeException((new StringBuilder()).append("files.length=").append(as.length).append(" keys.length=").append(as1.length).toString());
        if(parcelfiledescriptor != null)
            parcelfiledescriptor = parcelfiledescriptor.getFileDescriptor();
        else
            parcelfiledescriptor = null;
        parcelfiledescriptor1 = parcelfiledescriptor1.getFileDescriptor();
        if(parcelfiledescriptor1 == null)
            throw new NullPointerException();
        int k = performBackup_native(parcelfiledescriptor, backupdataoutput.mBackupWriter, parcelfiledescriptor1, as, as1);
        if(k != 0)
            throw new RuntimeException((new StringBuilder()).append("Backup failed 0x").append(Integer.toHexString(k)).toString());
        else
            return;
    }

    private static native int performBackup_native(FileDescriptor filedescriptor, long l, FileDescriptor filedescriptor1, String as[], String as1[]);

    private static native int writeFile_native(long l, String s, long l1);

    private static native int writeSnapshot_native(long l, FileDescriptor filedescriptor);

    protected void finalize()
        throws Throwable
    {
        dtor(mPtr);
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    boolean isKeyInList(String s, String as[])
    {
        int i = as.length;
        for(int j = 0; j < i; j++)
            if(as[j].equals(s))
                return true;

        return false;
    }

    boolean writeFile(File file, BackupDataInputStream backupdatainputstream)
    {
        boolean flag = true;
        file.getParentFile().mkdirs();
        int i = writeFile_native(mPtr, file.getAbsolutePath(), backupdatainputstream.mData.mBackupReader);
        if(i != 0 && !mExceptionLogged)
        {
            Log.e("FileBackupHelperBase", (new StringBuilder()).append("Failed restoring file '").append(file).append("' for app '").append(mContext.getPackageName()).append("' result=0x").append(Integer.toHexString(i)).toString());
            mExceptionLogged = true;
        }
        if(i != 0)
            flag = false;
        return flag;
    }

    public void writeNewStateDescription(ParcelFileDescriptor parcelfiledescriptor)
    {
        writeSnapshot_native(mPtr, parcelfiledescriptor.getFileDescriptor());
    }

    private static final String TAG = "FileBackupHelperBase";
    Context mContext;
    boolean mExceptionLogged;
    long mPtr;
}
