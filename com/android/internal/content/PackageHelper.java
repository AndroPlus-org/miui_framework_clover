// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.content;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.*;
import android.os.storage.*;
import android.util.ArraySet;
import android.util.Log;
import java.io.*;
import java.util.*;
import java.util.zip.*;
import libcore.io.IoUtils;

// Referenced classes of package com.android.internal.content:
//            NativeLibraryHelper

public class PackageHelper
{
    public static abstract class TestableInterface
    {

        public boolean fitsOnInternalStorage(Context context, android.content.pm.PackageInstaller.SessionParams sessionparams)
            throws IOException
        {
            context = getStorageManager(context);
            java.util.UUID uuid = context.getUuidForPath(getDataDirectory());
            boolean flag;
            if(sessionparams.sizeBytes <= context.getAllocatableBytes(uuid, PackageHelper.translateAllocateFlags(sessionparams.installFlags)))
                flag = true;
            else
                flag = false;
            return flag;
        }

        public abstract boolean getAllow3rdPartyOnInternalConfig(Context context);

        public abstract File getDataDirectory();

        public abstract ApplicationInfo getExistingAppInfo(Context context, String s);

        public abstract boolean getForceAllowOnExternalSetting(Context context);

        public abstract StorageManager getStorageManager(Context context);

        public TestableInterface()
        {
        }
    }


    public PackageHelper()
    {
    }

    public static long calculateInstalledSize(android.content.pm.PackageParser.PackageLite packagelite, NativeLibraryHelper.Handle handle, boolean flag, String s)
        throws IOException
    {
        long l = 0L;
        Iterator iterator = packagelite.getAllCodePaths().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            packagelite = new File((String)iterator.next());
            long l1 = l + packagelite.length();
            l = l1;
            if(flag)
                l = l1 + extractPublicFiles(packagelite, null);
        } while(true);
        return l + NativeLibraryHelper.sumNativeBinariesWithOverride(handle, s);
    }

    public static long calculateInstalledSize(android.content.pm.PackageParser.PackageLite packagelite, boolean flag, String s)
        throws IOException
    {
        NativeLibraryHelper.Handle handle = null;
        NativeLibraryHelper.Handle handle1 = NativeLibraryHelper.Handle.create(packagelite);
        handle = handle1;
        long l = calculateInstalledSize(packagelite, handle1, flag, s);
        IoUtils.closeQuietly(handle1);
        return l;
        packagelite;
        IoUtils.closeQuietly(handle);
        throw packagelite;
    }

    private static void copyZipEntry(ZipEntry zipentry, ZipFile zipfile, ZipOutputStream zipoutputstream)
        throws IOException
    {
        byte abyte0[] = new byte[4096];
        ZipEntry zipentry1;
        int i;
        if(zipentry.getMethod() == 0)
            zipentry1 = new ZipEntry(zipentry);
        else
            zipentry1 = new ZipEntry(zipentry.getName());
        zipoutputstream.putNextEntry(zipentry1);
        zipentry = zipfile.getInputStream(zipentry);
        i = zipentry.read(abyte0);
        if(i <= 0)
            break MISSING_BLOCK_LABEL_81;
        zipoutputstream.write(abyte0, 0, i);
        break MISSING_BLOCK_LABEL_35;
        zipfile;
        IoUtils.closeQuietly(zipentry);
        throw zipfile;
        zipoutputstream.flush();
        IoUtils.closeQuietly(zipentry);
        return;
    }

    public static String createSdDir(long l, String s, String s1, int i, boolean flag)
    {
        int j = (int)((0x100000L + l) / 0x100000L);
        IStorageManager istoragemanager;
        istoragemanager = getStorageManager();
        if(istoragemanager.createSecureContainer(s, j + 1, "ext4", s1, i, flag) == 0)
            break MISSING_BLOCK_LABEL_68;
        s1 = JVM INSTR new #173 <Class StringBuilder>;
        s1.StringBuilder();
        Log.e("PackageHelper", s1.append("Failed to create secure container ").append(s).toString());
        return null;
        try
        {
            s = istoragemanager.getSecureContainerPath(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.e("PackageHelper", "StorageManagerService running?");
            return null;
        }
        return s;
    }

    public static boolean destroySdDir(String s)
    {
label0:
        {
            try
            {
                if(getStorageManager().destroySecureContainer(s, true) == 0)
                    break label0;
                StringBuilder stringbuilder = JVM INSTR new #173 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Log.i("PackageHelper", stringbuilder.append("Failed to destroy container ").append(s).toString());
            }
            catch(RemoteException remoteexception)
            {
                Log.e("PackageHelper", (new StringBuilder()).append("Failed to destroy container ").append(s).append(" with exception ").append(remoteexception).toString());
                return false;
            }
            return false;
        }
        return true;
    }

    public static long extractPublicFiles(File file, File file1)
        throws IOException
    {
        FileOutputStream fileoutputstream;
        ZipOutputStream zipoutputstream;
        long l;
        ZipFile zipfile;
        Iterator iterator;
        ZipEntry zipentry;
        long l1;
        if(file1 == null)
        {
            fileoutputstream = null;
            zipoutputstream = null;
        } else
        {
            fileoutputstream = new FileOutputStream(file1);
            zipoutputstream = new ZipOutputStream(fileoutputstream);
            Log.d("PackageHelper", (new StringBuilder()).append("Extracting ").append(file).append(" to ").append(file1).toString());
        }
        l = 0L;
        zipfile = JVM INSTR new #131 <Class ZipFile>;
        zipfile.ZipFile(file.getAbsolutePath());
        iterator = Collections.list(zipfile.entries()).iterator();
_L2:
        do
        {
            if(!iterator.hasNext())
                break MISSING_BLOCK_LABEL_200;
            zipentry = (ZipEntry)iterator.next();
            file = zipentry.getName();
        } while(!"AndroidManifest.xml".equals(file) && !"resources.arsc".equals(file) && !file.startsWith("res/"));
        l1 = l + zipentry.getSize();
        l = l1;
        if(file1 == null) goto _L2; else goto _L1
_L1:
        copyZipEntry(zipentry, zipfile, zipoutputstream);
        l = l1;
          goto _L2
        file;
        try
        {
            zipfile.close();
        }
        // Misplaced declaration of an exception variable
        catch(File file1) { }
        throw file;
        file;
        IoUtils.closeQuietly(zipoutputstream);
        throw file;
        try
        {
            zipfile.close();
        }
        // Misplaced declaration of an exception variable
        catch(File file) { }
        if(file1 == null)
            break MISSING_BLOCK_LABEL_239;
        zipoutputstream.finish();
        zipoutputstream.flush();
        FileUtils.sync(fileoutputstream);
        zipoutputstream.close();
        FileUtils.setPermissions(file1.getAbsolutePath(), 420, -1, -1);
        IoUtils.closeQuietly(zipoutputstream);
        return l;
    }

    public static boolean finalizeSdDir(String s)
    {
label0:
        {
            try
            {
                if(getStorageManager().finalizeSecureContainer(s) == 0)
                    break label0;
                StringBuilder stringbuilder = JVM INSTR new #173 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Log.i("PackageHelper", stringbuilder.append("Failed to finalize container ").append(s).toString());
            }
            catch(RemoteException remoteexception)
            {
                Log.e("PackageHelper", (new StringBuilder()).append("Failed to finalize container ").append(s).append(" with exception ").append(remoteexception).toString());
                return false;
            }
            return false;
        }
        return true;
    }

    public static boolean fitsOnExternal(Context context, android.content.pm.PackageInstaller.SessionParams sessionparams)
    {
        boolean flag = false;
        StorageManager storagemanager = (StorageManager)context.getSystemService(android/os/storage/StorageManager);
        context = storagemanager.getPrimaryVolume();
        boolean flag1 = flag;
        if(sessionparams.sizeBytes > 0L)
        {
            flag1 = flag;
            if(context.isEmulated() ^ true)
            {
                flag1 = flag;
                if("mounted".equals(context.getState()))
                {
                    flag1 = flag;
                    if(sessionparams.sizeBytes <= storagemanager.getStorageBytesUntilLow(context.getPathFile()))
                        flag1 = true;
                }
            }
        }
        return flag1;
    }

    public static boolean fitsOnInternal(Context context, android.content.pm.PackageInstaller.SessionParams sessionparams)
        throws IOException
    {
        context = (StorageManager)context.getSystemService(android/os/storage/StorageManager);
        java.util.UUID uuid = context.getUuidForPath(Environment.getDataDirectory());
        boolean flag;
        if(sessionparams.sizeBytes <= context.getAllocatableBytes(uuid, translateAllocateFlags(sessionparams.installFlags)))
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean fixSdPermissions(String s, int i, String s1)
    {
label0:
        {
            try
            {
                if(getStorageManager().fixPermissionsSecureContainer(s, i, s1) == 0)
                    break label0;
                s1 = JVM INSTR new #173 <Class StringBuilder>;
                s1.StringBuilder();
                Log.i("PackageHelper", s1.append("Failed to fixperms container ").append(s).toString());
            }
            // Misplaced declaration of an exception variable
            catch(String s1)
            {
                Log.e("PackageHelper", (new StringBuilder()).append("Failed to fixperms container ").append(s).append(" with exception ").append(s1).toString());
                return false;
            }
            return false;
        }
        return true;
    }

    private static TestableInterface getDefaultTestableInterface()
    {
        com/android/internal/content/PackageHelper;
        JVM INSTR monitorenter ;
        TestableInterface testableinterface1;
        if(sDefaultTestableInterface == null)
        {
            TestableInterface testableinterface = JVM INSTR new #6   <Class PackageHelper$1>;
            testableinterface._cls1();
            sDefaultTestableInterface = testableinterface;
        }
        testableinterface1 = sDefaultTestableInterface;
        com/android/internal/content/PackageHelper;
        JVM INSTR monitorexit ;
        return testableinterface1;
        Exception exception;
        exception;
        throw exception;
    }

    public static String getSdDir(String s)
    {
        String s1;
        try
        {
            s1 = getStorageManager().getSecureContainerPath(s);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("PackageHelper", (new StringBuilder()).append("Failed to get container path for ").append(s).append(" with exception ").append(remoteexception).toString());
            return null;
        }
        return s1;
    }

    public static String getSdFilesystem(String s)
    {
        String s1;
        try
        {
            s1 = getStorageManager().getSecureContainerFilesystemPath(s);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("PackageHelper", (new StringBuilder()).append("Failed to get container path for ").append(s).append(" with exception ").append(remoteexception).toString());
            return null;
        }
        return s1;
    }

    public static String[] getSecureContainerList()
    {
        String as[];
        try
        {
            as = getStorageManager().getSecureContainerList();
        }
        catch(RemoteException remoteexception)
        {
            Log.e("PackageHelper", (new StringBuilder()).append("Failed to get secure container list with exception").append(remoteexception).toString());
            return null;
        }
        return as;
    }

    public static IStorageManager getStorageManager()
        throws RemoteException
    {
        android.os.IBinder ibinder = ServiceManager.getService("mount");
        if(ibinder != null)
        {
            return android.os.storage.IStorageManager.Stub.asInterface(ibinder);
        } else
        {
            Log.e("PackageHelper", "Can't get storagemanager service");
            throw new RemoteException("Could not contact storagemanager service");
        }
    }

    public static boolean isContainerMounted(String s)
    {
        boolean flag;
        try
        {
            flag = getStorageManager().isSecureContainerMounted(s);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("PackageHelper", (new StringBuilder()).append("Failed to find out if container ").append(s).append(" mounted").toString());
            return false;
        }
        return flag;
    }

    public static String mountSdDir(String s, String s1, int i)
    {
        return mountSdDir(s, s1, i, true);
    }

    public static String mountSdDir(String s, String s1, int i, boolean flag)
    {
        try
        {
            i = getStorageManager().mountSecureContainer(s, s1, i, flag);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.e("PackageHelper", "StorageManagerService running?");
            return null;
        }
        if(i == 0)
            break MISSING_BLOCK_LABEL_57;
        s1 = JVM INSTR new #173 <Class StringBuilder>;
        s1.StringBuilder();
        Log.i("PackageHelper", s1.append("Failed to mount container ").append(s).append(" rc : ").append(i).toString());
        return null;
        s = getStorageManager().getSecureContainerPath(s);
        return s;
    }

    public static boolean renameSdDir(String s, String s1)
    {
        int i;
        StringBuilder stringbuilder;
        try
        {
            i = getStorageManager().renameSecureContainer(s, s1);
        }
        catch(RemoteException remoteexception)
        {
            Log.i("PackageHelper", (new StringBuilder()).append("Failed ot rename  ").append(s).append(" to ").append(s1).append(" with exception : ").append(remoteexception).toString());
            return false;
        }
        if(i == 0)
            break MISSING_BLOCK_LABEL_65;
        stringbuilder = JVM INSTR new #173 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.e("PackageHelper", stringbuilder.append("Failed to rename ").append(s).append(" to ").append(s1).append("with rc ").append(i).toString());
        return false;
        return true;
    }

    public static String replaceEnd(String s, String s1, String s2)
    {
        if(!s.endsWith(s1))
            throw new IllegalArgumentException((new StringBuilder()).append("Expected ").append(s).append(" to end with ").append(s1).toString());
        else
            return (new StringBuilder()).append(s.substring(0, s.length() - s1.length())).append(s2).toString();
    }

    public static boolean resizeSdDir(long l, String s, String s1)
    {
        int i = (int)((l + 0x100000L) / 0x100000L);
        i = getStorageManager().resizeSecureContainer(s, i + 1, s1);
        if(i == 0)
            return true;
        break MISSING_BLOCK_LABEL_44;
        s1;
        Log.e("PackageHelper", "StorageManagerService running?");
        Log.e("PackageHelper", (new StringBuilder()).append("Failed to create secure container ").append(s).toString());
        return false;
    }

    public static int resolveInstallLocation(Context context, android.content.pm.PackageInstaller.SessionParams sessionparams)
        throws IOException
    {
        boolean flag;
        ApplicationInfo applicationinfo;
        flag = true;
        applicationinfo = null;
        ApplicationInfo applicationinfo1 = context.getPackageManager().getApplicationInfo(sessionparams.appPackageName, 0x400000);
        applicationinfo = applicationinfo1;
_L2:
        boolean flag1 = false;
        int i;
        byte byte0;
        boolean flag2;
        boolean flag3;
        if((sessionparams.installFlags & 0x800) != 0)
        {
            i = 1;
            flag1 = true;
            byte0 = 0;
        } else
        if((sessionparams.installFlags & 0x10) != 0)
        {
            i = 1;
            byte0 = 0;
        } else
        if((sessionparams.installFlags & 8) != 0)
        {
            i = 2;
            byte0 = 0;
        } else
        if(sessionparams.installLocation == 1)
        {
            i = 1;
            byte0 = 0;
        } else
        if(sessionparams.installLocation == 2)
        {
            i = 2;
            byte0 = 1;
        } else
        if(sessionparams.installLocation == 0)
        {
            if(applicationinfo != null)
            {
                if((applicationinfo.flags & 0x40000) != 0)
                    i = 2;
                else
                    i = 1;
            } else
            {
                i = 1;
            }
            byte0 = 1;
        } else
        {
            i = 1;
            byte0 = 0;
        }
        flag2 = false;
        if(byte0 != 0 || i == 1)
            flag2 = fitsOnInternal(context, sessionparams);
        flag3 = false;
        if(byte0 != 0 || i == 2)
            flag3 = fitsOnExternal(context, sessionparams);
        if(i == 1)
        {
            if(flag2)
            {
                byte0 = flag;
                if(flag1)
                    byte0 = 3;
                return byte0;
            }
        } else
        if(i == 2 && flag3)
            return 2;
        if(byte0 != 0)
        {
            if(flag2)
                return 1;
            if(flag3)
                return 2;
        }
        return -1;
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        namenotfoundexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static int resolveInstallLocation(Context context, String s, int i, long l, int j)
    {
        android.content.pm.PackageInstaller.SessionParams sessionparams = new android.content.pm.PackageInstaller.SessionParams(-1);
        sessionparams.appPackageName = s;
        sessionparams.installLocation = i;
        sessionparams.sizeBytes = l;
        sessionparams.installFlags = j;
        try
        {
            i = resolveInstallLocation(context, sessionparams);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw new IllegalStateException(context);
        }
        return i;
    }

    public static String resolveInstallVolume(Context context, android.content.pm.PackageInstaller.SessionParams sessionparams)
        throws IOException
    {
        TestableInterface testableinterface = getDefaultTestableInterface();
        return resolveInstallVolume(context, sessionparams.appPackageName, sessionparams.installLocation, sessionparams.sizeBytes, testableinterface);
    }

    public static String resolveInstallVolume(Context context, android.content.pm.PackageInstaller.SessionParams sessionparams, TestableInterface testableinterface)
        throws IOException
    {
        boolean flag = testableinterface.getForceAllowOnExternalSetting(context);
        boolean flag1 = testableinterface.getAllow3rdPartyOnInternalConfig(context);
        ApplicationInfo applicationinfo = testableinterface.getExistingAppInfo(context, sessionparams.appPackageName);
        boolean flag2 = testableinterface.fitsOnInternalStorage(context, sessionparams);
        StorageManager storagemanager = testableinterface.getStorageManager(context);
        if(applicationinfo != null && applicationinfo.isSystemApp())
            if(flag2)
                return StorageManager.UUID_PRIVATE_INTERNAL;
            else
                throw new IOException((new StringBuilder()).append("Not enough space on existing volume ").append(applicationinfo.volumeUuid).append(" for system app ").append(sessionparams.appPackageName).append(" upgrade").toString());
        ArraySet arrayset = new ArraySet();
        context = null;
        long l = 0x8000000000000000L;
        Iterator iterator = storagemanager.getVolumes().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            testableinterface = (VolumeInfo)iterator.next();
            boolean flag3 = "private".equals(((VolumeInfo) (testableinterface)).id);
            if(((VolumeInfo) (testableinterface)).type == 1 && testableinterface.isMountedWritable() && (!flag3 || flag1))
            {
                long l1 = storagemanager.getAllocatableBytes(storagemanager.getUuidForPath(new File(((VolumeInfo) (testableinterface)).path)), translateAllocateFlags(sessionparams.installFlags));
                if(l1 >= sessionparams.sizeBytes)
                    arrayset.add(((VolumeInfo) (testableinterface)).fsUuid);
                if(l1 >= l)
                {
                    context = testableinterface;
                    l = l1;
                }
            }
        } while(true);
        if(!flag && sessionparams.installLocation == 1)
        {
            if(applicationinfo != null && Objects.equals(applicationinfo.volumeUuid, StorageManager.UUID_PRIVATE_INTERNAL) ^ true)
                throw new IOException((new StringBuilder()).append("Cannot automatically move ").append(sessionparams.appPackageName).append(" from ").append(applicationinfo.volumeUuid).append(" to internal storage").toString());
            if(!flag1)
                throw new IOException("Not allowed to install non-system apps on internal storage");
            if(flag2)
                return StorageManager.UUID_PRIVATE_INTERNAL;
            else
                throw new IOException("Requested internal only, but not enough space");
        }
        if(applicationinfo != null)
        {
            if(Objects.equals(applicationinfo.volumeUuid, StorageManager.UUID_PRIVATE_INTERNAL) && flag2)
                return StorageManager.UUID_PRIVATE_INTERNAL;
            if(arrayset.contains(applicationinfo.volumeUuid))
                return applicationinfo.volumeUuid;
            else
                throw new IOException((new StringBuilder()).append("Not enough space on existing volume ").append(applicationinfo.volumeUuid).append(" for ").append(sessionparams.appPackageName).append(" upgrade").toString());
        }
        if(context != null)
            return ((VolumeInfo) (context)).fsUuid;
        else
            throw new IOException((new StringBuilder()).append("No special requests, but no room on allowed volumes.  allow3rdPartyOnInternal? ").append(flag1).toString());
    }

    public static String resolveInstallVolume(Context context, String s, int i, long l, TestableInterface testableinterface)
        throws IOException
    {
        android.content.pm.PackageInstaller.SessionParams sessionparams = new android.content.pm.PackageInstaller.SessionParams(-1);
        sessionparams.appPackageName = s;
        sessionparams.installLocation = i;
        sessionparams.sizeBytes = l;
        return resolveInstallVolume(context, sessionparams, testableinterface);
    }

    public static int translateAllocateFlags(int i)
    {
        return (0x8000 & i) == 0 ? 0 : 1;
    }

    public static boolean unMountSdDir(String s)
    {
        int i;
        StringBuilder stringbuilder;
        try
        {
            i = getStorageManager().unmountSecureContainer(s, true);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.e("PackageHelper", "StorageManagerService running?");
            return false;
        }
        if(i == 0)
            break MISSING_BLOCK_LABEL_55;
        stringbuilder = JVM INSTR new #173 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.e("PackageHelper", stringbuilder.append("Failed to unmount ").append(s).append(" with rc ").append(i).toString());
        return false;
        return true;
    }

    public static final int APP_INSTALL_AUTO = 0;
    public static final int APP_INSTALL_EXTERNAL = 2;
    public static final int APP_INSTALL_INTERNAL = 1;
    public static final int RECOMMEND_FAILED_ALREADY_EXISTS = -4;
    public static final int RECOMMEND_FAILED_INSUFFICIENT_STORAGE = -1;
    public static final int RECOMMEND_FAILED_INVALID_APK = -2;
    public static final int RECOMMEND_FAILED_INVALID_LOCATION = -3;
    public static final int RECOMMEND_FAILED_INVALID_URI = -6;
    public static final int RECOMMEND_FAILED_VERSION_DOWNGRADE = -7;
    public static final int RECOMMEND_INSTALL_EPHEMERAL = 3;
    public static final int RECOMMEND_INSTALL_EXTERNAL = 2;
    public static final int RECOMMEND_INSTALL_INTERNAL = 1;
    public static final int RECOMMEND_MEDIA_UNAVAILABLE = -5;
    private static final String TAG = "PackageHelper";
    private static final boolean localLOGV = false;
    private static TestableInterface sDefaultTestableInterface = null;


    // Unreferenced inner class com/android/internal/content/PackageHelper$1

/* anonymous class */
    static final class _cls1 extends TestableInterface
    {

        public boolean getAllow3rdPartyOnInternalConfig(Context context)
        {
            return context.getResources().getBoolean(0x1120005);
        }

        public File getDataDirectory()
        {
            return Environment.getDataDirectory();
        }

        public ApplicationInfo getExistingAppInfo(Context context, String s)
        {
            Object obj = null;
            try
            {
                context = context.getPackageManager().getApplicationInfo(s, 0x400000);
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                context = obj;
            }
            return context;
        }

        public boolean getForceAllowOnExternalSetting(Context context)
        {
            boolean flag = false;
            if(android.provider.Settings.Global.getInt(context.getContentResolver(), "force_allow_on_external", 0) != 0)
                flag = true;
            return flag;
        }

        public StorageManager getStorageManager(Context context)
        {
            return (StorageManager)context.getSystemService(android/os/storage/StorageManager);
        }

    }

}
