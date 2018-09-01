// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.storage;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.*;
import android.provider.DocumentsContract;
import android.text.TextUtils;
import android.util.*;
import com.android.internal.util.IndentingPrintWriter;
import com.android.internal.util.Preconditions;
import java.io.CharArrayWriter;
import java.io.File;
import java.util.Comparator;
import java.util.Objects;

// Referenced classes of package android.os.storage:
//            DiskInfo, StorageManager, StorageVolume

public class VolumeInfo
    implements Parcelable
{

    public VolumeInfo(Parcel parcel)
    {
        mountFlags = 0;
        mountUserId = -1;
        state = 0;
        id = parcel.readString();
        type = parcel.readInt();
        if(parcel.readInt() != 0)
            disk = (DiskInfo)DiskInfo.CREATOR.createFromParcel(parcel);
        else
            disk = null;
        partGuid = parcel.readString();
        mountFlags = parcel.readInt();
        mountUserId = parcel.readInt();
        state = parcel.readInt();
        fsType = parcel.readString();
        fsUuid = parcel.readString();
        fsLabel = parcel.readString();
        path = parcel.readString();
        internalPath = parcel.readString();
    }

    public VolumeInfo(String s, int i, DiskInfo diskinfo, String s1)
    {
        mountFlags = 0;
        mountUserId = -1;
        state = 0;
        id = (String)Preconditions.checkNotNull(s);
        type = i;
        disk = diskinfo;
        partGuid = s1;
    }

    public static int buildStableMtpStorageId(String s)
    {
        if(TextUtils.isEmpty(s))
            return 0;
        int i = 0;
        for(int j = 0; j < s.length(); j++)
            i = i * 31 + s.charAt(j);

        i = (i << 16 ^ i) & 0xffff0000;
        int k = i;
        if(i == 0)
            k = 0x20000;
        i = k;
        if(k == 0x10000)
            i = 0x20000;
        k = i;
        if(i == 0xffff0000)
            k = 0xfffe0000;
        return k | 1;
    }

    public static String getBroadcastForEnvironment(String s)
    {
        return (String)sEnvironmentToBroadcast.get(s);
    }

    public static String getBroadcastForState(int i)
    {
        return getBroadcastForEnvironment(getEnvironmentForState(i));
    }

    public static Comparator getDescriptionComparator()
    {
        return sDescriptionComparator;
    }

    public static String getEnvironmentForState(int i)
    {
        String s = (String)sStateToEnvironment.get(i);
        if(s != null)
            return s;
        else
            return "unknown";
    }

    public Intent buildBrowseIntent()
    {
        android.net.Uri uri;
        Intent intent;
        if(type == 0)
            uri = DocumentsContract.buildRootUri("com.android.externalstorage.documents", fsUuid);
        else
        if(type == 2 && isPrimary())
            uri = DocumentsContract.buildRootUri("com.android.externalstorage.documents", "primary");
        else
            return null;
        intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setDataAndType(uri, "vnd.android.document/root");
        intent.putExtra("android.content.extra.SHOW_ADVANCED", isPrimary());
        return intent;
    }

    public StorageVolume buildStorageVolume(Context context, int i, boolean flag)
    {
        Object obj;
        Object obj1;
        String s1;
        long l;
        long l1;
        int j;
        boolean flag1;
        long l2;
        int i1;
        obj = (StorageManager)context.getSystemService(android/os/storage/StorageManager);
        String s;
        File file;
        VolumeInfo volumeinfo;
        if(flag)
            s = "unmounted";
        else
            s = getEnvironmentForState(state);
        obj1 = getPathForUser(i);
        file = ((File) (obj1));
        if(obj1 == null)
            file = new File("/dev/null");
        obj1 = null;
        s1 = fsUuid;
        l = 0L;
        l1 = 0L;
        j = 0;
        if(type != 2) goto _L2; else goto _L1
_L1:
        flag1 = true;
        volumeinfo = ((StorageManager) (obj)).findPrivateForEmulated(this);
        if(volumeinfo != null)
        {
            obj1 = ((StorageManager) (obj)).getBestVolumeDescription(volumeinfo);
            s1 = volumeinfo.fsUuid;
        }
        if(isPrimary())
            j = 0x10001;
        l2 = ((StorageManager) (obj)).getStorageLowBytes(file);
        if("emulated".equals(id))
        {
            flag = false;
            obj = s1;
            i1 = j;
        } else
        {
            flag = true;
            i1 = j;
            obj = s1;
        }
_L4:
        s1 = ((String) (obj1));
        if(obj1 == null)
            s1 = context.getString(0x104000e);
        return new StorageVolume(id, i1, file, s1, isPrimary(), flag, flag1, l2, false, l1, new UserHandle(i), ((String) (obj)), s);
_L2:
        if(type != 0)
            break; /* Loop/switch isn't completed */
        boolean flag2 = false;
        boolean flag3 = true;
        String s2 = ((StorageManager) (obj)).getBestVolumeDescription(this);
        int k;
        if(isPrimary())
            k = 0x10001;
        else
            k = buildStableMtpStorageId(fsUuid);
        i1 = k;
        obj1 = s2;
        flag = flag3;
        flag1 = flag2;
        l2 = l;
        obj = s1;
        if("vfat".equals(fsType))
        {
            l1 = 0xffffffffL;
            i1 = k;
            obj1 = s2;
            flag = flag3;
            flag1 = flag2;
            l2 = l;
            obj = s1;
        }
        if(true) goto _L4; else goto _L3
_L3:
        throw new IllegalStateException((new StringBuilder()).append("Unexpected volume type ").append(type).toString());
    }

    public VolumeInfo clone()
    {
        Parcel parcel = Parcel.obtain();
        VolumeInfo volumeinfo;
        writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        volumeinfo = (VolumeInfo)CREATOR.createFromParcel(parcel);
        parcel.recycle();
        return volumeinfo;
        Exception exception;
        exception;
        parcel.recycle();
        throw exception;
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public int describeContents()
    {
        return 0;
    }

    public void dump(IndentingPrintWriter indentingprintwriter)
    {
        indentingprintwriter.println((new StringBuilder()).append("VolumeInfo{").append(id).append("}:").toString());
        indentingprintwriter.increaseIndent();
        indentingprintwriter.printPair("type", DebugUtils.valueToString(getClass(), "TYPE_", type));
        indentingprintwriter.printPair("diskId", getDiskId());
        indentingprintwriter.printPair("partGuid", partGuid);
        indentingprintwriter.printPair("mountFlags", DebugUtils.flagsToString(getClass(), "MOUNT_FLAG_", mountFlags));
        indentingprintwriter.printPair("mountUserId", Integer.valueOf(mountUserId));
        indentingprintwriter.printPair("state", DebugUtils.valueToString(getClass(), "STATE_", state));
        indentingprintwriter.println();
        indentingprintwriter.printPair("fsType", fsType);
        indentingprintwriter.printPair("fsUuid", fsUuid);
        indentingprintwriter.printPair("fsLabel", fsLabel);
        indentingprintwriter.println();
        indentingprintwriter.printPair("path", path);
        indentingprintwriter.printPair("internalPath", internalPath);
        indentingprintwriter.decreaseIndent();
        indentingprintwriter.println();
    }

    public boolean equals(Object obj)
    {
        if(obj instanceof VolumeInfo)
            return Objects.equals(id, ((VolumeInfo)obj).id);
        else
            return false;
    }

    public String getDescription()
    {
        if("private".equals(id) || "emulated".equals(id))
            return Resources.getSystem().getString(0x104061e);
        if(!TextUtils.isEmpty(fsLabel))
            return fsLabel;
        else
            return null;
    }

    public DiskInfo getDisk()
    {
        return disk;
    }

    public String getDiskId()
    {
        String s = null;
        if(disk != null)
            s = disk.id;
        return s;
    }

    public String getFsUuid()
    {
        return fsUuid;
    }

    public String getId()
    {
        return id;
    }

    public File getInternalPath()
    {
        File file = null;
        if(internalPath != null)
            file = new File(internalPath);
        return file;
    }

    public File getInternalPathForUser(int i)
    {
        if(type == 0)
            return new File(path.replace("/storage/", "/mnt/media_rw/"));
        else
            return getPathForUser(i);
    }

    public int getMountUserId()
    {
        return mountUserId;
    }

    public File getPath()
    {
        File file = null;
        if(path != null)
            file = new File(path);
        return file;
    }

    public File getPathForUser(int i)
    {
        if(path == null)
            return null;
        if(type == 0)
            return new File(path);
        if(type == 2)
            return new File(path, Integer.toString(i));
        else
            return null;
    }

    public int getState()
    {
        return state;
    }

    public int getStateDescription()
    {
        return sStateToDescrip.get(state, 0);
    }

    public int getType()
    {
        return type;
    }

    public int hashCode()
    {
        return id.hashCode();
    }

    public boolean isMountedReadable()
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(state != 2)
            if(state == 3)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public boolean isMountedWritable()
    {
        boolean flag;
        if(state == 2)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isPrimary()
    {
        boolean flag = false;
        if((mountFlags & 1) != 0)
            flag = true;
        return flag;
    }

    public boolean isPrimaryPhysical()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(isPrimary())
        {
            flag1 = flag;
            if(getType() == 0)
                flag1 = true;
        }
        return flag1;
    }

    public boolean isVisible()
    {
        boolean flag = false;
        if((mountFlags & 2) != 0)
            flag = true;
        return flag;
    }

    public boolean isVisibleForRead(int i)
    {
        if(type == 0)
            if(isPrimary() && mountUserId != i)
                return false;
            else
                return isVisible();
        if(type == 2)
            return isVisible();
        else
            return false;
    }

    public boolean isVisibleForWrite(int i)
    {
        if(type == 0 && mountUserId == i)
            return isVisible();
        if(type == 2)
            return isVisible();
        else
            return false;
    }

    public String toString()
    {
        CharArrayWriter chararraywriter = new CharArrayWriter();
        dump(new IndentingPrintWriter(chararraywriter, "    ", 80));
        return chararraywriter.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(id);
        parcel.writeInt(type);
        if(disk != null)
        {
            parcel.writeInt(1);
            disk.writeToParcel(parcel, i);
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeString(partGuid);
        parcel.writeInt(mountFlags);
        parcel.writeInt(mountUserId);
        parcel.writeInt(state);
        parcel.writeString(fsType);
        parcel.writeString(fsUuid);
        parcel.writeString(fsLabel);
        parcel.writeString(path);
        parcel.writeString(internalPath);
    }

    public static final String ACTION_VOLUME_STATE_CHANGED = "android.os.storage.action.VOLUME_STATE_CHANGED";
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public VolumeInfo createFromParcel(Parcel parcel)
        {
            return new VolumeInfo(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public VolumeInfo[] newArray(int i)
        {
            return new VolumeInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String DOCUMENT_AUTHORITY = "com.android.externalstorage.documents";
    private static final String DOCUMENT_ROOT_PRIMARY_EMULATED = "primary";
    public static final String EXTRA_VOLUME_ID = "android.os.storage.extra.VOLUME_ID";
    public static final String EXTRA_VOLUME_STATE = "android.os.storage.extra.VOLUME_STATE";
    public static final String ID_EMULATED_INTERNAL = "emulated";
    public static final String ID_PRIVATE_INTERNAL = "private";
    public static final int MOUNT_FLAG_PRIMARY = 1;
    public static final int MOUNT_FLAG_VISIBLE = 2;
    public static final int STATE_BAD_REMOVAL = 8;
    public static final int STATE_CHECKING = 1;
    public static final int STATE_EJECTING = 5;
    public static final int STATE_FORMATTING = 4;
    public static final int STATE_MOUNTED = 2;
    public static final int STATE_MOUNTED_READ_ONLY = 3;
    public static final int STATE_REMOVED = 7;
    public static final int STATE_UNMOUNTABLE = 6;
    public static final int STATE_UNMOUNTED = 0;
    public static final int TYPE_ASEC = 3;
    public static final int TYPE_EMULATED = 2;
    public static final int TYPE_OBB = 4;
    public static final int TYPE_PRIVATE = 1;
    public static final int TYPE_PUBLIC = 0;
    private static final Comparator sDescriptionComparator = new Comparator() {

        public int compare(VolumeInfo volumeinfo, VolumeInfo volumeinfo1)
        {
            if("private".equals(volumeinfo.getId()))
                return -1;
            if(volumeinfo.getDescription() == null)
                return 1;
            if(volumeinfo1.getDescription() == null)
                return -1;
            else
                return volumeinfo.getDescription().compareTo(volumeinfo1.getDescription());
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((VolumeInfo)obj, (VolumeInfo)obj1);
        }

    }
;
    private static ArrayMap sEnvironmentToBroadcast;
    private static SparseIntArray sStateToDescrip;
    private static SparseArray sStateToEnvironment;
    public final DiskInfo disk;
    public String fsLabel;
    public String fsType;
    public String fsUuid;
    public final String id;
    public String internalPath;
    public int mountFlags;
    public int mountUserId;
    public final String partGuid;
    public String path;
    public int state;
    public final int type;

    static 
    {
        sStateToEnvironment = new SparseArray();
        sEnvironmentToBroadcast = new ArrayMap();
        sStateToDescrip = new SparseIntArray();
        sStateToEnvironment.put(0, "unmounted");
        sStateToEnvironment.put(1, "checking");
        sStateToEnvironment.put(2, "mounted");
        sStateToEnvironment.put(3, "mounted_ro");
        sStateToEnvironment.put(4, "unmounted");
        sStateToEnvironment.put(5, "ejecting");
        sStateToEnvironment.put(6, "unmountable");
        sStateToEnvironment.put(7, "removed");
        sStateToEnvironment.put(8, "bad_removal");
        sEnvironmentToBroadcast.put("unmounted", "android.intent.action.MEDIA_UNMOUNTED");
        sEnvironmentToBroadcast.put("checking", "android.intent.action.MEDIA_CHECKING");
        sEnvironmentToBroadcast.put("mounted", "android.intent.action.MEDIA_MOUNTED");
        sEnvironmentToBroadcast.put("mounted_ro", "android.intent.action.MEDIA_MOUNTED");
        sEnvironmentToBroadcast.put("ejecting", "android.intent.action.MEDIA_EJECT");
        sEnvironmentToBroadcast.put("unmountable", "android.intent.action.MEDIA_UNMOUNTABLE");
        sEnvironmentToBroadcast.put("removed", "android.intent.action.MEDIA_REMOVED");
        sEnvironmentToBroadcast.put("bad_removal", "android.intent.action.MEDIA_BAD_REMOVAL");
        sStateToDescrip.put(0, 0x1040206);
        sStateToDescrip.put(1, 0x10401fe);
        sStateToDescrip.put(2, 0x1040202);
        sStateToDescrip.put(3, 0x1040203);
        sStateToDescrip.put(4, 0x1040200);
        sStateToDescrip.put(5, 0x10401ff);
        sStateToDescrip.put(6, 0x1040205);
        sStateToDescrip.put(7, 0x1040204);
        sStateToDescrip.put(8, 0x10401fd);
    }
}
