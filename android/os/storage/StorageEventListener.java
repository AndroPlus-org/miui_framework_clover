// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.storage;


// Referenced classes of package android.os.storage:
//            DiskInfo, VolumeRecord, VolumeInfo

public class StorageEventListener
{

    public StorageEventListener()
    {
    }

    public void onDiskDestroyed(DiskInfo diskinfo)
    {
    }

    public void onDiskScanned(DiskInfo diskinfo, int i)
    {
    }

    public void onStorageStateChanged(String s, String s1, String s2)
    {
    }

    public void onUsbMassStorageConnectionChanged(boolean flag)
    {
    }

    public void onVolumeForgotten(String s)
    {
    }

    public void onVolumeRecordChanged(VolumeRecord volumerecord)
    {
    }

    public void onVolumeStateChanged(VolumeInfo volumeinfo, int i, int j)
    {
    }
}
