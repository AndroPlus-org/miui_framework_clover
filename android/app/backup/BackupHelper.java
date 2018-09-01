// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.backup;

import android.os.ParcelFileDescriptor;

// Referenced classes of package android.app.backup:
//            BackupDataOutput, BackupDataInputStream

public interface BackupHelper
{

    public abstract void performBackup(ParcelFileDescriptor parcelfiledescriptor, BackupDataOutput backupdataoutput, ParcelFileDescriptor parcelfiledescriptor1);

    public abstract void restoreEntity(BackupDataInputStream backupdatainputstream);

    public abstract void writeNewStateDescription(ParcelFileDescriptor parcelfiledescriptor);
}
