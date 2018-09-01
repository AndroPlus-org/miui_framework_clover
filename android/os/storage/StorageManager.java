// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.storage;

import android.app.ActivityThread;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.*;
import android.os.*;
import android.system.*;
import android.text.TextUtils;
import android.util.*;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.os.*;
import com.android.internal.util.Preconditions;
import java.io.*;
import java.lang.ref.WeakReference;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package android.os.storage:
//            StorageVolume, IStorageManager, DiskInfo, VolumeInfo, 
//            VolumeRecord, OnObbStateChangeListener, StorageEventListener

public class StorageManager
{
    private class ObbActionListener extends IObbActionListener.Stub
    {

        public int addListener(OnObbStateChangeListener onobbstatechangelistener)
        {
            ObbListenerDelegate obblistenerdelegate = new ObbListenerDelegate(onobbstatechangelistener);
            onobbstatechangelistener = mListeners;
            onobbstatechangelistener;
            JVM INSTR monitorenter ;
            mListeners.put(ObbListenerDelegate._2D_get0(obblistenerdelegate), obblistenerdelegate);
            onobbstatechangelistener;
            JVM INSTR monitorexit ;
            return ObbListenerDelegate._2D_get0(obblistenerdelegate);
            Exception exception;
            exception;
            throw exception;
        }

        public void onObbResult(String s, int i, int j)
        {
            SparseArray sparsearray = mListeners;
            sparsearray;
            JVM INSTR monitorenter ;
            ObbListenerDelegate obblistenerdelegate = (ObbListenerDelegate)mListeners.get(i);
            if(obblistenerdelegate == null)
                break MISSING_BLOCK_LABEL_35;
            mListeners.remove(i);
            sparsearray;
            JVM INSTR monitorexit ;
            if(obblistenerdelegate != null)
                obblistenerdelegate.sendObbStateChanged(s, j);
            return;
            s;
            throw s;
        }

        private SparseArray mListeners;
        final StorageManager this$0;

        private ObbActionListener()
        {
            this$0 = StorageManager.this;
            super();
            mListeners = new SparseArray();
        }

        ObbActionListener(ObbActionListener obbactionlistener)
        {
            this();
        }
    }

    private class ObbListenerDelegate
    {

        static int _2D_get0(ObbListenerDelegate obblistenerdelegate)
        {
            return obblistenerdelegate.nonce;
        }

        OnObbStateChangeListener getListener()
        {
            if(mObbEventListenerRef == null)
                return null;
            else
                return (OnObbStateChangeListener)mObbEventListenerRef.get();
        }

        void sendObbStateChanged(String s, int i)
        {
            mHandler.obtainMessage(0, i, 0, s).sendToTarget();
        }

        private final Handler mHandler;
        private final WeakReference mObbEventListenerRef;
        private final int nonce;
        final StorageManager this$0;

        ObbListenerDelegate(OnObbStateChangeListener onobbstatechangelistener)
        {
            this$0 = StorageManager.this;
            super();
            nonce = StorageManager._2D_wrap0(StorageManager.this);
            mObbEventListenerRef = new WeakReference(onobbstatechangelistener);
            mHandler = new _cls1(StorageManager._2D_get0(StorageManager.this));
        }
    }

    private static class StorageEventListenerDelegate extends IStorageEventListener.Stub
        implements android.os.Handler.Callback
    {

        public boolean handleMessage(Message message)
        {
            SomeArgs someargs = (SomeArgs)message.obj;
            switch(message.what)
            {
            default:
                someargs.recycle();
                return false;

            case 1: // '\001'
                mCallback.onStorageStateChanged((String)someargs.arg1, (String)someargs.arg2, (String)someargs.arg3);
                someargs.recycle();
                return true;

            case 2: // '\002'
                mCallback.onVolumeStateChanged((VolumeInfo)someargs.arg1, someargs.argi2, someargs.argi3);
                someargs.recycle();
                return true;

            case 3: // '\003'
                mCallback.onVolumeRecordChanged((VolumeRecord)someargs.arg1);
                someargs.recycle();
                return true;

            case 4: // '\004'
                mCallback.onVolumeForgotten((String)someargs.arg1);
                someargs.recycle();
                return true;

            case 5: // '\005'
                mCallback.onDiskScanned((DiskInfo)someargs.arg1, someargs.argi2);
                someargs.recycle();
                return true;

            case 6: // '\006'
                mCallback.onDiskDestroyed((DiskInfo)someargs.arg1);
                break;
            }
            someargs.recycle();
            return true;
        }

        public void onDiskDestroyed(DiskInfo diskinfo)
            throws RemoteException
        {
            SomeArgs someargs = SomeArgs.obtain();
            someargs.arg1 = diskinfo;
            mHandler.obtainMessage(6, someargs).sendToTarget();
        }

        public void onDiskScanned(DiskInfo diskinfo, int i)
        {
            SomeArgs someargs = SomeArgs.obtain();
            someargs.arg1 = diskinfo;
            someargs.argi2 = i;
            mHandler.obtainMessage(5, someargs).sendToTarget();
        }

        public void onStorageStateChanged(String s, String s1, String s2)
        {
            SomeArgs someargs = SomeArgs.obtain();
            someargs.arg1 = s;
            someargs.arg2 = s1;
            someargs.arg3 = s2;
            mHandler.obtainMessage(1, someargs).sendToTarget();
        }

        public void onUsbMassStorageConnectionChanged(boolean flag)
            throws RemoteException
        {
        }

        public void onVolumeForgotten(String s)
        {
            SomeArgs someargs = SomeArgs.obtain();
            someargs.arg1 = s;
            mHandler.obtainMessage(4, someargs).sendToTarget();
        }

        public void onVolumeRecordChanged(VolumeRecord volumerecord)
        {
            SomeArgs someargs = SomeArgs.obtain();
            someargs.arg1 = volumerecord;
            mHandler.obtainMessage(3, someargs).sendToTarget();
        }

        public void onVolumeStateChanged(VolumeInfo volumeinfo, int i, int j)
        {
            SomeArgs someargs = SomeArgs.obtain();
            someargs.arg1 = volumeinfo;
            someargs.argi2 = i;
            someargs.argi3 = j;
            mHandler.obtainMessage(2, someargs).sendToTarget();
        }

        private static final int MSG_DISK_DESTROYED = 6;
        private static final int MSG_DISK_SCANNED = 5;
        private static final int MSG_STORAGE_STATE_CHANGED = 1;
        private static final int MSG_VOLUME_FORGOTTEN = 4;
        private static final int MSG_VOLUME_RECORD_CHANGED = 3;
        private static final int MSG_VOLUME_STATE_CHANGED = 2;
        final StorageEventListener mCallback;
        final Handler mHandler;

        public StorageEventListenerDelegate(StorageEventListener storageeventlistener, Looper looper)
        {
            mCallback = storageeventlistener;
            mHandler = new Handler(looper, this);
        }
    }


    static Looper _2D_get0(StorageManager storagemanager)
    {
        return storagemanager.mLooper;
    }

    static int _2D_wrap0(StorageManager storagemanager)
    {
        return storagemanager.getNextNonce();
    }

    public StorageManager(Context context, Looper looper)
        throws android.os.ServiceManager.ServiceNotFoundException
    {
        mFuseAppLoop = null;
        mContext = context;
        mResolver = context.getContentResolver();
        mLooper = looper;
    }

    public static String convert(UUID uuid)
    {
        if(UUID_DEFAULT.equals(uuid))
            return UUID_PRIVATE_INTERNAL;
        if(UUID_PRIMARY_PHYSICAL_.equals(uuid))
            return "primary_physical";
        if(UUID_SYSTEM_.equals(uuid))
            return "system";
        else
            return uuid.toString();
    }

    public static UUID convert(String s)
    {
        if(Objects.equals(s, UUID_PRIVATE_INTERNAL))
            return UUID_DEFAULT;
        if(Objects.equals(s, "primary_physical"))
            return UUID_PRIMARY_PHYSICAL_;
        if(Objects.equals(s, "system"))
            return UUID_SYSTEM_;
        else
            return UUID.fromString(s);
    }

    public static StorageManager from(Context context)
    {
        return (StorageManager)context.getSystemService(android/os/storage/StorageManager);
    }

    private int getNextNonce()
    {
        return mNextNonce.getAndIncrement();
    }

    public static Pair getPrimaryStoragePathAndSize()
    {
        return Pair.create(null, Long.valueOf(FileUtils.roundStorageSize(Environment.getDataDirectory().getTotalSpace())));
    }

    public static StorageVolume getPrimaryVolume(StorageVolume astoragevolume[])
    {
        int i = 0;
        for(int j = astoragevolume.length; i < j; i++)
        {
            StorageVolume storagevolume = astoragevolume[i];
            if(storagevolume.isPrimary())
                return storagevolume;
        }

        throw new IllegalStateException("Missing primary storage");
    }

    public static StorageVolume getStorageVolume(File file, int i)
    {
        return getStorageVolume(getVolumeList(i, 0), file);
    }

    private static StorageVolume getStorageVolume(StorageVolume astoragevolume[], File file)
    {
        int i;
        if(file == null)
            return null;
        File file1;
        int j;
        File file2;
        try
        {
            file1 = file.getCanonicalFile();
        }
        // Misplaced declaration of an exception variable
        catch(StorageVolume astoragevolume[])
        {
            Slog.d("StorageManager", (new StringBuilder()).append("Could not get canonical path for ").append(file).toString());
            return null;
        }
        i = 0;
        j = astoragevolume.length;
_L3:
        if(i >= j) goto _L2; else goto _L1
_L1:
        file = astoragevolume[i];
        file2 = file.getPathFile();
        file2 = file2.getCanonicalFile();
        if(FileUtils.contains(file2, file1))
            return file;
        continue; /* Loop/switch isn't completed */
        file;
        i++;
          goto _L3
_L2:
        return null;
    }

    public static StorageVolume[] getVolumeList(int i, int j)
    {
        IStorageManager istoragemanager = IStorageManager.Stub.asInterface(ServiceManager.getService("mount"));
        String s;
        Object obj;
        StorageVolume astoragevolume[];
        try
        {
            s = ActivityThread.currentOpPackageName();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        obj = s;
        if(s != null)
            break MISSING_BLOCK_LABEL_55;
        obj = ActivityThread.getPackageManager().getPackagesForUid(Process.myUid());
        if(obj == null)
            break MISSING_BLOCK_LABEL_44;
        if(obj.length > 0)
            break MISSING_BLOCK_LABEL_49;
        return new StorageVolume[0];
        obj = obj[0];
        i = ActivityThread.getPackageManager().getPackageUid(((String) (obj)), 0x10000000, i);
        if(i > 0)
            break MISSING_BLOCK_LABEL_79;
        return new StorageVolume[0];
        astoragevolume = istoragemanager.getVolumeList(i, ((String) (obj)), j);
        return astoragevolume;
    }

    public static boolean inCryptKeeperBounce()
    {
        return "trigger_restart_min_framework".equals(SystemProperties.get("vold.decrypt"));
    }

    public static boolean isBlockEncrypted()
    {
        if(!isEncrypted())
            return false;
        else
            return RoSystemProperties.CRYPTO_BLOCK_ENCRYPTED;
    }

    public static boolean isBlockEncrypting()
    {
        return "".equalsIgnoreCase(SystemProperties.get("vold.encrypt_progress", "")) ^ true;
    }

    private static boolean isCacheBehavior(File file, String s)
        throws IOException
    {
        try
        {
            Os.getxattr(file.getAbsolutePath(), s);
        }
        // Misplaced declaration of an exception variable
        catch(File file)
        {
            if(((ErrnoException) (file)).errno != OsConstants.ENODATA)
                throw file.rethrowAsIOException();
            else
                return false;
        }
        return true;
    }

    public static boolean isEncryptable()
    {
        return RoSystemProperties.CRYPTO_ENCRYPTABLE;
    }

    public static boolean isEncrypted()
    {
        return RoSystemProperties.CRYPTO_ENCRYPTED;
    }

    public static boolean isFileEncryptedEmulatedOnly()
    {
        return SystemProperties.getBoolean("persist.sys.emulate_fbe", false);
    }

    public static boolean isFileEncryptedNativeOnly()
    {
        if(!isEncrypted())
            return false;
        else
            return RoSystemProperties.CRYPTO_FILE_ENCRYPTED;
    }

    public static boolean isFileEncryptedNativeOrEmulated()
    {
        boolean flag;
        if(!isFileEncryptedNativeOnly())
            flag = isFileEncryptedEmulatedOnly();
        else
            flag = true;
        return flag;
    }

    public static boolean isNonDefaultBlockEncrypted()
    {
        boolean flag = true;
        if(!isBlockEncrypted())
            return false;
        int i;
        try
        {
            i = IStorageManager.Stub.asInterface(ServiceManager.getService("mount")).getPasswordType();
        }
        catch(RemoteException remoteexception)
        {
            Log.e("StorageManager", "Error getting encryption type");
            return false;
        }
        if(i == 1)
            flag = false;
        return flag;
    }

    public static boolean isUserKeyUnlocked(int i)
    {
        long l;
        if(sStorageManager == null)
            sStorageManager = IStorageManager.Stub.asInterface(ServiceManager.getService("mount"));
        if(sStorageManager == null)
        {
            Slog.w("StorageManager", "Early during boot, assuming locked");
            return false;
        }
        l = Binder.clearCallingIdentity();
        boolean flag = sStorageManager.isUserKeyUnlocked(i);
        Binder.restoreCallingIdentity(l);
        return flag;
        Object obj;
        obj;
        throw ((RemoteException) (obj)).rethrowAsRuntimeException();
        obj;
        Binder.restoreCallingIdentity(l);
        throw obj;
    }

    public static File maybeTranslateEmulatedPathToInternal(File file)
    {
        return file;
    }

    private static void setCacheBehavior(File file, String s, boolean flag)
        throws IOException
    {
        if(!file.isDirectory())
            throw new IOException("Cache behavior can only be set on directories");
        if(!flag)
            break MISSING_BLOCK_LABEL_47;
        Os.setxattr(file.getAbsolutePath(), s, "1".getBytes(StandardCharsets.UTF_8), 0);
_L1:
        return;
        file;
        throw file.rethrowAsIOException();
        try
        {
            Os.removexattr(file.getAbsolutePath(), s);
        }
        // Misplaced declaration of an exception variable
        catch(File file)
        {
            if(((ErrnoException) (file)).errno != OsConstants.ENODATA)
                throw file.rethrowAsIOException();
        }
          goto _L1
    }

    public void allocateBytes(FileDescriptor filedescriptor, long l)
        throws IOException
    {
        allocateBytes(filedescriptor, l, 0);
    }

    public void allocateBytes(FileDescriptor filedescriptor, long l, int i)
        throws IOException
    {
        File file;
        UUID uuid;
        int j;
        file = ParcelFileDescriptor.getFile(filedescriptor);
        uuid = getUuidForPath(file);
        j = 0;
_L2:
        if(j >= 3)
            break; /* Loop/switch isn't completed */
        long l1 = l - Os.fstat(filedescriptor).st_blocks * 512L;
        if(l1 <= 0L)
            break MISSING_BLOCK_LABEL_55;
        allocateBytes(uuid, l1, i);
        Os.posix_fallocate(filedescriptor, 0L, l);
        return;
        ErrnoException errnoexception;
        errnoexception;
        if(errnoexception.errno == OsConstants.ENOSYS || errnoexception.errno == OsConstants.ENOTSUP)
        {
            Log.w("StorageManager", "fallocate() not supported; falling back to ftruncate()");
            Os.ftruncate(filedescriptor, l);
            return;
        }
        try
        {
            throw errnoexception;
        }
        catch(ErrnoException errnoexception1)
        {
            if(errnoexception1.errno == OsConstants.ENOSPC)
            {
                Log.w("StorageManager", "Odd, not enough space; let's try again?");
                j++;
            } else
            {
                throw errnoexception1.rethrowAsIOException();
            }
        }
        if(true) goto _L2; else goto _L1
_L1:
        throw new IOException((new StringBuilder()).append("Well this is embarassing; we can't allocate ").append(l).append(" for ").append(file).toString());
    }

    public void allocateBytes(UUID uuid, long l)
        throws IOException
    {
        allocateBytes(uuid, l, 0);
    }

    public void allocateBytes(UUID uuid, long l, int i)
        throws IOException
    {
        mStorageManager.allocateBytes(convert(uuid), l, i, mContext.getOpPackageName());
_L1:
        return;
        uuid;
        throw uuid.rethrowFromSystemServer();
        uuid;
        uuid.maybeRethrow(java/io/IOException);
          goto _L1
    }

    public long benchmark(String s)
    {
        long l;
        try
        {
            l = mStorageManager.benchmark(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return l;
    }

    public void createUserKey(int i, int j, boolean flag)
    {
        try
        {
            mStorageManager.createUserKey(i, j, flag);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void destroyUserKey(int i)
    {
        try
        {
            mStorageManager.destroyUserKey(i);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void destroyUserStorage(String s, int i, int j)
    {
        try
        {
            mStorageManager.destroyUserStorage(s, i, j);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void disableUsbMassStorage()
    {
    }

    public void enableUsbMassStorage()
    {
    }

    public DiskInfo findDiskById(String s)
    {
        Preconditions.checkNotNull(s);
        for(Iterator iterator = getDisks().iterator(); iterator.hasNext();)
        {
            DiskInfo diskinfo = (DiskInfo)iterator.next();
            if(Objects.equals(diskinfo.id, s))
                return diskinfo;
        }

        return null;
    }

    public VolumeInfo findEmulatedForPrivate(VolumeInfo volumeinfo)
    {
        if(volumeinfo != null)
            return findVolumeById(volumeinfo.getId().replace("private", "emulated"));
        else
            return null;
    }

    public File findPathForUuid(String s)
        throws FileNotFoundException
    {
        VolumeInfo volumeinfo = findVolumeByQualifiedUuid(s);
        if(volumeinfo != null)
            return volumeinfo.getPath();
        else
            throw new FileNotFoundException((new StringBuilder()).append("Failed to find a storage device for ").append(s).toString());
    }

    public VolumeInfo findPrivateForEmulated(VolumeInfo volumeinfo)
    {
        if(volumeinfo != null)
            return findVolumeById(volumeinfo.getId().replace("emulated", "private"));
        else
            return null;
    }

    public VolumeRecord findRecordByUuid(String s)
    {
        Preconditions.checkNotNull(s);
        for(Iterator iterator = getVolumeRecords().iterator(); iterator.hasNext();)
        {
            VolumeRecord volumerecord = (VolumeRecord)iterator.next();
            if(Objects.equals(volumerecord.fsUuid, s))
                return volumerecord;
        }

        return null;
    }

    public VolumeInfo findVolumeById(String s)
    {
        Preconditions.checkNotNull(s);
        for(Iterator iterator = getVolumes().iterator(); iterator.hasNext();)
        {
            VolumeInfo volumeinfo = (VolumeInfo)iterator.next();
            if(Objects.equals(volumeinfo.id, s))
                return volumeinfo;
        }

        return null;
    }

    public VolumeInfo findVolumeByQualifiedUuid(String s)
    {
        if(Objects.equals(UUID_PRIVATE_INTERNAL, s))
            return findVolumeById("private");
        if(Objects.equals("primary_physical", s))
            return getPrimaryPhysicalVolume();
        else
            return findVolumeByUuid(s);
    }

    public VolumeInfo findVolumeByUuid(String s)
    {
        Preconditions.checkNotNull(s);
        for(Iterator iterator = getVolumes().iterator(); iterator.hasNext();)
        {
            VolumeInfo volumeinfo = (VolumeInfo)iterator.next();
            if(Objects.equals(volumeinfo.fsUuid, s))
                return volumeinfo;
        }

        return null;
    }

    public void forgetVolume(String s)
    {
        try
        {
            mStorageManager.forgetVolume(s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void format(String s)
    {
        try
        {
            mStorageManager.format(s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public long getAllocatableBytes(UUID uuid)
        throws IOException
    {
        return getAllocatableBytes(uuid, 0);
    }

    public long getAllocatableBytes(UUID uuid, int i)
        throws IOException
    {
        long l;
        try
        {
            l = mStorageManager.getAllocatableBytes(convert(uuid), i, mContext.getOpPackageName());
        }
        // Misplaced declaration of an exception variable
        catch(UUID uuid)
        {
            uuid.maybeRethrow(java/io/IOException);
            throw new RuntimeException(uuid);
        }
        // Misplaced declaration of an exception variable
        catch(UUID uuid)
        {
            throw uuid.rethrowFromSystemServer();
        }
        return l;
    }

    public String getBestVolumeDescription(VolumeInfo volumeinfo)
    {
        if(volumeinfo == null)
            return null;
        if(!TextUtils.isEmpty(volumeinfo.fsUuid))
        {
            VolumeRecord volumerecord = findRecordByUuid(volumeinfo.fsUuid);
            if(volumerecord != null && TextUtils.isEmpty(volumerecord.nickname) ^ true)
                return volumerecord.nickname;
        }
        if(!TextUtils.isEmpty(volumeinfo.getDescription()))
            return volumeinfo.getDescription();
        if(volumeinfo.disk != null)
            return volumeinfo.disk.getDescription();
        else
            return null;
    }

    public long getCacheQuotaBytes(UUID uuid)
        throws IOException
    {
        long l;
        try
        {
            ApplicationInfo applicationinfo = mContext.getApplicationInfo();
            l = mStorageManager.getCacheQuotaBytes(convert(uuid), applicationinfo.uid);
        }
        // Misplaced declaration of an exception variable
        catch(UUID uuid)
        {
            uuid.maybeRethrow(java/io/IOException);
            throw new RuntimeException(uuid);
        }
        // Misplaced declaration of an exception variable
        catch(UUID uuid)
        {
            throw uuid.rethrowFromSystemServer();
        }
        return l;
    }

    public long getCacheSizeBytes(UUID uuid)
        throws IOException
    {
        long l;
        try
        {
            ApplicationInfo applicationinfo = mContext.getApplicationInfo();
            l = mStorageManager.getCacheSizeBytes(convert(uuid), applicationinfo.uid);
        }
        // Misplaced declaration of an exception variable
        catch(UUID uuid)
        {
            uuid.maybeRethrow(java/io/IOException);
            throw new RuntimeException(uuid);
        }
        // Misplaced declaration of an exception variable
        catch(UUID uuid)
        {
            throw uuid.rethrowFromSystemServer();
        }
        return l;
    }

    public List getDisks()
    {
        List list;
        try
        {
            list = Arrays.asList(mStorageManager.getDisks());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return list;
    }

    public String getMountedObbPath(String s)
    {
        Preconditions.checkNotNull(s, "rawPath cannot be null");
        try
        {
            s = mStorageManager.getMountedObbPath(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return s;
    }

    public VolumeInfo getPrimaryPhysicalVolume()
    {
        for(Iterator iterator = getVolumes().iterator(); iterator.hasNext();)
        {
            VolumeInfo volumeinfo = (VolumeInfo)iterator.next();
            if(volumeinfo.isPrimaryPhysical())
                return volumeinfo;
        }

        return null;
    }

    public long getPrimaryStorageSize()
    {
        return FileUtils.roundStorageSize(Environment.getDataDirectory().getTotalSpace());
    }

    public String getPrimaryStorageUuid()
    {
        String s;
        try
        {
            s = mStorageManager.getPrimaryStorageUuid();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return s;
    }

    public StorageVolume getPrimaryStorageVolume()
    {
        return getVolumeList(UserHandle.myUserId(), 1536)[0];
    }

    public StorageVolume getPrimaryVolume()
    {
        return getPrimaryVolume(getVolumeList());
    }

    public int getProxyFileDescriptorMountPointId()
    {
        Object obj = mFuseAppLoopLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        if(mFuseAppLoop == null)
            break MISSING_BLOCK_LABEL_26;
        i = mFuseAppLoop.getMountPointId();
_L1:
        obj;
        JVM INSTR monitorexit ;
        return i;
        i = -1;
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    public long getStorageBytesUntilLow(File file)
    {
        return file.getUsableSpace() - getStorageFullBytes(file);
    }

    public long getStorageCacheBytes(File file, int i)
    {
        long l = android.provider.Settings.Global.getInt(mResolver, "sys_storage_cache_percentage", 10);
        l = Math.min((file.getTotalSpace() * l) / 100L, android.provider.Settings.Global.getLong(mResolver, "sys_storage_cache_max_bytes", 0x140000000L));
        if((i & 1) != 0)
            return 0L;
        if((i & 2) != 0)
            return 0L;
        if((i & 4) != 0)
            return l / 2L;
        else
            return l;
    }

    public long getStorageFullBytes(File file)
    {
        return android.provider.Settings.Global.getLong(mResolver, "sys_storage_full_threshold_bytes", 0x100000L);
    }

    public long getStorageLowBytes(File file)
    {
        long l = android.provider.Settings.Global.getInt(mResolver, "sys_storage_threshold_percentage", 5);
        return Math.min((file.getTotalSpace() * l) / 100L, android.provider.Settings.Global.getLong(mResolver, "sys_storage_threshold_max_bytes", 0x1f400000L));
    }

    public StorageVolume getStorageVolume(File file)
    {
        return getStorageVolume(getVolumeList(), file);
    }

    public List getStorageVolumes()
    {
        ArrayList arraylist = new ArrayList();
        Collections.addAll(arraylist, getVolumeList(UserHandle.myUserId(), 1536));
        return arraylist;
    }

    public UUID getUuidForPath(File file)
        throws IOException
    {
        int i = 0;
        Preconditions.checkNotNull(file);
        String s = file.getCanonicalPath();
        if(FileUtils.contains(Environment.getDataDirectory().getAbsolutePath(), s))
            return UUID_DEFAULT;
        VolumeInfo avolumeinfo[];
        int j;
        VolumeInfo volumeinfo;
        try
        {
            avolumeinfo = mStorageManager.getVolumes(0);
            j = avolumeinfo.length;
        }
        // Misplaced declaration of an exception variable
        catch(File file)
        {
            throw file.rethrowFromSystemServer();
        }
        if(i >= j)
            break;
        volumeinfo = avolumeinfo[i];
        if(volumeinfo.path == null || !FileUtils.contains(volumeinfo.path, s))
            break MISSING_BLOCK_LABEL_89;
        file = convert(volumeinfo.fsUuid);
        return file;
        for(i++; true;)
            break MISSING_BLOCK_LABEL_46;

        throw new FileNotFoundException((new StringBuilder()).append("Failed to find a storage device for ").append(file).toString());
    }

    public StorageVolume[] getVolumeList()
    {
        return getVolumeList(mContext.getUserId(), 0);
    }

    public String[] getVolumePaths()
    {
        StorageVolume astoragevolume[] = getVolumeList();
        int i = astoragevolume.length;
        String as[] = new String[i];
        for(int j = 0; j < i; j++)
            as[j] = astoragevolume[j].getPath();

        return as;
    }

    public List getVolumeRecords()
    {
        List list;
        try
        {
            list = Arrays.asList(mStorageManager.getVolumeRecords(0));
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return list;
    }

    public String getVolumeState(String s)
    {
        s = getStorageVolume(new File(s));
        if(s != null)
            return s.getState();
        else
            return "unknown";
    }

    public List getVolumes()
    {
        List list;
        try
        {
            list = Arrays.asList(mStorageManager.getVolumes(0));
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return list;
    }

    public List getWritablePrivateVolumes()
    {
        int i = 0;
        ArrayList arraylist;
        VolumeInfo avolumeinfo[];
        int j;
        VolumeInfo volumeinfo;
        try
        {
            arraylist = JVM INSTR new #202 <Class ArrayList>;
            arraylist.ArrayList();
            avolumeinfo = mStorageManager.getVolumes(0);
            j = avolumeinfo.length;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        if(i >= j) goto _L2; else goto _L1
_L1:
        volumeinfo = avolumeinfo[i];
        if(volumeinfo.getType() == 1 && volumeinfo.isMountedWritable())
            arraylist.add(volumeinfo);
        i++;
        break MISSING_BLOCK_LABEL_25;
_L2:
        return arraylist;
    }

    public boolean isAllocationSupported(FileDescriptor filedescriptor)
    {
        try
        {
            getUuidForPath(ParcelFileDescriptor.getFile(filedescriptor));
        }
        // Misplaced declaration of an exception variable
        catch(FileDescriptor filedescriptor)
        {
            return false;
        }
        return true;
    }

    public boolean isCacheBehaviorGroup(File file)
        throws IOException
    {
        return isCacheBehavior(file, "user.cache_group");
    }

    public boolean isCacheBehaviorTombstone(File file)
        throws IOException
    {
        return isCacheBehavior(file, "user.cache_tombstone");
    }

    public boolean isEncrypted(File file)
    {
        if(FileUtils.contains(Environment.getDataDirectory(), file))
            return isEncrypted();
        return FileUtils.contains(Environment.getExpandDirectory(), file);
    }

    public boolean isObbMounted(String s)
    {
        Preconditions.checkNotNull(s, "rawPath cannot be null");
        boolean flag;
        try
        {
            flag = mStorageManager.isObbMounted(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isUsbMassStorageConnected()
    {
        return false;
    }

    public boolean isUsbMassStorageEnabled()
    {
        return false;
    }

    public void lockUserKey(int i)
    {
        try
        {
            mStorageManager.lockUserKey(i);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void mount(String s)
    {
        try
        {
            mStorageManager.mount(s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public boolean mountObb(String s, String s1, OnObbStateChangeListener onobbstatechangelistener)
    {
        Preconditions.checkNotNull(s, "rawPath cannot be null");
        Preconditions.checkNotNull(onobbstatechangelistener, "listener cannot be null");
        try
        {
            Object obj = JVM INSTR new #279 <Class File>;
            ((File) (obj)).File(s);
            obj = ((File) (obj)).getCanonicalPath();
            int i = mObbActionListener.addListener(onobbstatechangelistener);
            mStorageManager.mountObb(s, ((String) (obj)), s1, mObbActionListener, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s1)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Failed to resolve path: ").append(s).toString(), s1);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return true;
    }

    public ParcelFileDescriptor openProxyFileDescriptor(int i, ProxyFileDescriptorCallback proxyfiledescriptorcallback)
        throws IOException
    {
        return openProxyFileDescriptor(i, proxyfiledescriptorcallback, null, null);
    }

    public ParcelFileDescriptor openProxyFileDescriptor(int i, ProxyFileDescriptorCallback proxyfiledescriptorcallback, Handler handler)
        throws IOException
    {
        Preconditions.checkNotNull(handler);
        return openProxyFileDescriptor(i, proxyfiledescriptorcallback, handler, null);
    }

    public ParcelFileDescriptor openProxyFileDescriptor(int i, ProxyFileDescriptorCallback proxyfiledescriptorcallback, Handler handler, ThreadFactory threadfactory)
        throws IOException
    {
        Preconditions.checkNotNull(proxyfiledescriptorcallback);
        MetricsLogger.count(mContext, "storage_open_proxy_file_descriptor", 1);
_L6:
        Object obj = mFuseAppLoopLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag = false;
        if(mFuseAppLoop != null) goto _L2; else goto _L1
_L1:
        AppFuseMount appfusemount = mStorageManager.mountProxyFileDescriptorBridge();
        if(appfusemount != null) goto _L4; else goto _L3
_L3:
        proxyfiledescriptorcallback = JVM INSTR new #329 <Class IOException>;
        proxyfiledescriptorcallback.IOException("Failed to mount proxy bridge");
        throw proxyfiledescriptorcallback;
        proxyfiledescriptorcallback;
_L5:
        obj;
        JVM INSTR monitorexit ;
        throw proxyfiledescriptorcallback;
        proxyfiledescriptorcallback;
_L7:
        throw new IOException(proxyfiledescriptorcallback);
_L4:
        FuseAppLoop fuseapploop = JVM INSTR new #818 <Class FuseAppLoop>;
        fuseapploop.FuseAppLoop(appfusemount.mountPointId, appfusemount.fd, threadfactory);
        mFuseAppLoop = fuseapploop;
        flag = true;
_L2:
        if(handler != null)
            break MISSING_BLOCK_LABEL_126;
        handler = new Handler(Looper.getMainLooper());
        Object obj1;
        int j;
        j = mFuseAppLoop.registerCallback(proxyfiledescriptorcallback, handler);
        obj1 = mStorageManager.openProxyFileDescriptor(mFuseAppLoop.getMountPointId(), j, i);
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_215;
        try
        {
            mFuseAppLoop.unregisterCallback(j);
            obj1 = JVM INSTR new #962 <Class FuseUnavailableMountException>;
            ((FuseUnavailableMountException) (obj1)).FuseUnavailableMountException(mFuseAppLoop.getMountPointId());
            throw obj1;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj1) { }
        if(!flag)
            break MISSING_BLOCK_LABEL_221;
        proxyfiledescriptorcallback = JVM INSTR new #329 <Class IOException>;
        proxyfiledescriptorcallback.IOException(((Throwable) (obj1)));
        throw proxyfiledescriptorcallback;
        proxyfiledescriptorcallback;
          goto _L5
        obj;
        JVM INSTR monitorexit ;
        return ((ParcelFileDescriptor) (obj1));
        mFuseAppLoop = null;
        obj;
        JVM INSTR monitorexit ;
          goto _L6
        proxyfiledescriptorcallback;
          goto _L7
    }

    public void partitionMixed(String s, int i)
    {
        try
        {
            mStorageManager.partitionMixed(s, i);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void partitionPrivate(String s)
    {
        try
        {
            mStorageManager.partitionPrivate(s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void partitionPublic(String s)
    {
        try
        {
            mStorageManager.partitionPublic(s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void prepareUserStorage(String s, int i, int j, int k)
    {
        try
        {
            mStorageManager.prepareUserStorage(s, i, j, k);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void registerListener(StorageEventListener storageeventlistener)
    {
        ArrayList arraylist = mDelegates;
        arraylist;
        JVM INSTR monitorenter ;
        StorageEventListenerDelegate storageeventlistenerdelegate;
        storageeventlistenerdelegate = JVM INSTR new #14  <Class StorageManager$StorageEventListenerDelegate>;
        storageeventlistenerdelegate.StorageEventListenerDelegate(storageeventlistener, mLooper);
        mStorageManager.registerListener(storageeventlistenerdelegate);
        mDelegates.add(storageeventlistenerdelegate);
        arraylist;
        JVM INSTR monitorexit ;
        return;
        storageeventlistener;
        throw storageeventlistener.rethrowFromSystemServer();
        storageeventlistener;
        arraylist;
        JVM INSTR monitorexit ;
        throw storageeventlistener;
    }

    public void secdiscard(String s)
    {
        try
        {
            mStorageManager.secdiscard(s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void setCacheBehaviorGroup(File file, boolean flag)
        throws IOException
    {
        setCacheBehavior(file, "user.cache_group", flag);
    }

    public void setCacheBehaviorTombstone(File file, boolean flag)
        throws IOException
    {
        setCacheBehavior(file, "user.cache_tombstone", flag);
    }

    public void setPrimaryStorageUuid(String s, IPackageMoveObserver ipackagemoveobserver)
    {
        try
        {
            mStorageManager.setPrimaryStorageUuid(s, ipackagemoveobserver);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void setVolumeInited(String s, boolean flag)
    {
        int i = 1;
        IStorageManager istoragemanager;
        try
        {
            istoragemanager = mStorageManager;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        if(!flag)
            i = 0;
        istoragemanager.setVolumeUserFlags(s, i, 1);
        return;
    }

    public void setVolumeNickname(String s, String s1)
    {
        try
        {
            mStorageManager.setVolumeNickname(s, s1);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void setVolumeSnoozed(String s, boolean flag)
    {
        byte byte0 = 2;
        IStorageManager istoragemanager;
        try
        {
            istoragemanager = mStorageManager;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        if(!flag)
            byte0 = 0;
        istoragemanager.setVolumeUserFlags(s, byte0, 2);
        return;
    }

    public void unlockUserKey(int i, int j, byte abyte0[], byte abyte1[])
    {
        try
        {
            mStorageManager.unlockUserKey(i, j, abyte0, abyte1);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            throw abyte0.rethrowFromSystemServer();
        }
    }

    public void unmount(String s)
    {
        try
        {
            mStorageManager.unmount(s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public boolean unmountObb(String s, boolean flag, OnObbStateChangeListener onobbstatechangelistener)
    {
        Preconditions.checkNotNull(s, "rawPath cannot be null");
        Preconditions.checkNotNull(onobbstatechangelistener, "listener cannot be null");
        try
        {
            int i = mObbActionListener.addListener(onobbstatechangelistener);
            mStorageManager.unmountObb(s, flag, mObbActionListener, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return true;
    }

    public void unregisterListener(StorageEventListener storageeventlistener)
    {
        ArrayList arraylist = mDelegates;
        arraylist;
        JVM INSTR monitorenter ;
        Iterator iterator = mDelegates.iterator();
_L2:
        StorageEventListenerDelegate storageeventlistenerdelegate;
        StorageEventListener storageeventlistener1;
        do
        {
            if(!iterator.hasNext())
                break MISSING_BLOCK_LABEL_79;
            storageeventlistenerdelegate = (StorageEventListenerDelegate)iterator.next();
            storageeventlistener1 = storageeventlistenerdelegate.mCallback;
        } while(storageeventlistener1 != storageeventlistener);
        mStorageManager.unregisterListener(storageeventlistenerdelegate);
        iterator.remove();
        if(true) goto _L2; else goto _L1
_L1:
        storageeventlistener;
        throw storageeventlistener;
        storageeventlistener;
        throw storageeventlistener.rethrowFromSystemServer();
        arraylist;
        JVM INSTR monitorexit ;
    }

    public void wipeAdoptableDisks()
    {
        for(Iterator iterator = getDisks().iterator(); iterator.hasNext();)
        {
            Object obj = (DiskInfo)iterator.next();
            String s = ((DiskInfo) (obj)).getId();
            if(((DiskInfo) (obj)).isAdoptable())
            {
                Slog.d("StorageManager", (new StringBuilder()).append("Found adoptable ").append(s).append("; wiping").toString());
                try
                {
                    mStorageManager.partitionPublic(s);
                }
                // Misplaced declaration of an exception variable
                catch(Object obj)
                {
                    Slog.w("StorageManager", (new StringBuilder()).append("Failed to wipe ").append(s).append(", but soldiering onward").toString(), ((Throwable) (obj)));
                }
            } else
            {
                Slog.d("StorageManager", (new StringBuilder()).append("Ignorning non-adoptable disk ").append(((DiskInfo) (obj)).getId()).toString());
            }
        }

    }

    public static final String ACTION_MANAGE_STORAGE = "android.os.storage.action.MANAGE_STORAGE";
    public static final int CRYPT_TYPE_DEFAULT = 1;
    public static final int CRYPT_TYPE_PASSWORD = 0;
    public static final int CRYPT_TYPE_PATTERN = 2;
    public static final int CRYPT_TYPE_PIN = 3;
    public static final int DEBUG_EMULATE_FBE = 2;
    public static final int DEBUG_FORCE_ADOPTABLE = 1;
    public static final int DEBUG_SDCARDFS_FORCE_OFF = 8;
    public static final int DEBUG_SDCARDFS_FORCE_ON = 4;
    public static final int DEBUG_VIRTUAL_DISK = 16;
    private static final long DEFAULT_CACHE_MAX_BYTES = 0x140000000L;
    private static final int DEFAULT_CACHE_PERCENTAGE = 10;
    private static final long DEFAULT_FULL_THRESHOLD_BYTES = 0x100000L;
    private static final long DEFAULT_THRESHOLD_MAX_BYTES = 0x1f400000L;
    private static final int DEFAULT_THRESHOLD_PERCENTAGE = 5;
    public static final int ENCRYPTION_STATE_ERROR_CORRUPT = -4;
    public static final int ENCRYPTION_STATE_ERROR_INCOMPLETE = -2;
    public static final int ENCRYPTION_STATE_ERROR_INCONSISTENT = -3;
    public static final int ENCRYPTION_STATE_ERROR_UNKNOWN = -1;
    public static final int ENCRYPTION_STATE_NONE = 1;
    public static final int ENCRYPTION_STATE_OK = 0;
    public static final String EXTRA_REQUESTED_BYTES = "android.os.storage.extra.REQUESTED_BYTES";
    public static final String EXTRA_UUID = "android.os.storage.extra.UUID";
    public static final int FLAG_ALLOCATE_AGGRESSIVE = 1;
    public static final int FLAG_ALLOCATE_DEFY_ALL_RESERVED = 2;
    public static final int FLAG_ALLOCATE_DEFY_HALF_RESERVED = 4;
    public static final int FLAG_FOR_WRITE = 256;
    public static final int FLAG_INCLUDE_INVISIBLE = 1024;
    public static final int FLAG_REAL_STATE = 512;
    public static final int FLAG_STORAGE_CE = 2;
    public static final int FLAG_STORAGE_DE = 1;
    public static final int FSTRIM_FLAG_BENCHMARK = 2;
    public static final int FSTRIM_FLAG_DEEP = 1;
    public static final String OWNER_INFO_KEY = "OwnerInfo";
    public static final String PASSWORD_VISIBLE_KEY = "PasswordVisible";
    public static final String PATTERN_VISIBLE_KEY = "PatternVisible";
    public static final String PROP_ADOPTABLE_FBE = "persist.sys.adoptable_fbe";
    public static final String PROP_EMULATE_FBE = "persist.sys.emulate_fbe";
    public static final String PROP_FORCE_ADOPTABLE = "persist.fw.force_adoptable";
    public static final String PROP_HAS_ADOPTABLE = "vold.has_adoptable";
    public static final String PROP_PRIMARY_PHYSICAL = "ro.vold.primary_physical";
    public static final String PROP_SDCARDFS = "persist.sys.sdcardfs";
    public static final String PROP_VIRTUAL_DISK = "persist.sys.virtual_disk";
    public static final String SYSTEM_LOCALE_KEY = "SystemLocale";
    private static final String TAG = "StorageManager";
    public static final UUID UUID_DEFAULT = UUID.fromString("41217664-9172-527a-b3d5-edabb50a7d69");
    public static final String UUID_PRIMARY_PHYSICAL = "primary_physical";
    public static final UUID UUID_PRIMARY_PHYSICAL_ = UUID.fromString("0f95a519-dae7-5abf-9519-fbd6209e05fd");
    public static final String UUID_PRIVATE_INTERNAL;
    public static final String UUID_SYSTEM = "system";
    public static final UUID UUID_SYSTEM_ = UUID.fromString("5d258386-e60d-59e3-826d-0089cdd42cc0");
    private static final String XATTR_CACHE_GROUP = "user.cache_group";
    private static final String XATTR_CACHE_TOMBSTONE = "user.cache_tombstone";
    private static volatile IStorageManager sStorageManager = null;
    private final Context mContext;
    private final ArrayList mDelegates = new ArrayList();
    private FuseAppLoop mFuseAppLoop;
    private final Object mFuseAppLoopLock = new Object();
    private final Looper mLooper;
    private final AtomicInteger mNextNonce = new AtomicInteger(0);
    private final ObbActionListener mObbActionListener = new ObbActionListener(null);
    private final ContentResolver mResolver;
    private final IStorageManager mStorageManager = IStorageManager.Stub.asInterface(ServiceManager.getServiceOrThrow("mount"));


    // Unreferenced inner class android/os/storage/StorageManager$ObbListenerDelegate$1

/* anonymous class */
    class ObbListenerDelegate._cls1 extends Handler
    {

        public void handleMessage(Message message)
        {
            OnObbStateChangeListener onobbstatechangelistener = getListener();
            if(onobbstatechangelistener == null)
            {
                return;
            } else
            {
                onobbstatechangelistener.onObbStateChange((String)message.obj, message.arg1);
                return;
            }
        }

        final ObbListenerDelegate this$1;

            
            {
                this$1 = ObbListenerDelegate.this;
                super(looper);
            }
    }

}
