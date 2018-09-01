// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;

// Referenced classes of package android.os:
//            UserHandle, SystemProperties

public class Environment
{
    public static class UserEnvironment
    {

        public File[] buildExternalStorageAndroidDataDirs()
        {
            return Environment.buildPaths(getExternalDirs(), new String[] {
                "Android", "data"
            });
        }

        public File[] buildExternalStorageAndroidObbDirs()
        {
            return Environment.buildPaths(getExternalDirs(), new String[] {
                "Android", "obb"
            });
        }

        public File[] buildExternalStorageAppCacheDirs(String s)
        {
            return Environment.buildPaths(getExternalDirs(), new String[] {
                "Android", "data", s, "cache"
            });
        }

        public File[] buildExternalStorageAppDataDirs(String s)
        {
            return Environment.buildPaths(getExternalDirs(), new String[] {
                "Android", "data", s
            });
        }

        public File[] buildExternalStorageAppFilesDirs(String s)
        {
            return Environment.buildPaths(getExternalDirs(), new String[] {
                "Android", "data", s, "files"
            });
        }

        public File[] buildExternalStorageAppMediaDirs(String s)
        {
            return Environment.buildPaths(getExternalDirs(), new String[] {
                "Android", "media", s
            });
        }

        public File[] buildExternalStorageAppObbDirs(String s)
        {
            return Environment.buildPaths(getExternalDirs(), new String[] {
                "Android", "obb", s
            });
        }

        public File[] buildExternalStoragePublicDirs(String s)
        {
            return Environment.buildPaths(getExternalDirs(), new String[] {
                s
            });
        }

        public File[] getExternalDirs()
        {
            StorageVolume astoragevolume[] = StorageManager.getVolumeList(mUserId, 256);
            File afile[] = new File[astoragevolume.length];
            for(int i = 0; i < astoragevolume.length; i++)
                afile[i] = astoragevolume[i].getPathFile();

            return afile;
        }

        public File getExternalStorageDirectory()
        {
            return getExternalDirs()[0];
        }

        public File getExternalStoragePublicDirectory(String s)
        {
            return buildExternalStoragePublicDirs(s)[0];
        }

        private final int mUserId;

        public UserEnvironment(int i)
        {
            mUserId = i;
        }
    }


    public Environment()
    {
    }

    public static File[] buildExternalStorageAndroidDataDirs()
    {
        throwIfUserRequired();
        return sCurrentUser.buildExternalStorageAndroidDataDirs();
    }

    public static File[] buildExternalStorageAppCacheDirs(String s)
    {
        throwIfUserRequired();
        return sCurrentUser.buildExternalStorageAppCacheDirs(s);
    }

    public static File[] buildExternalStorageAppDataDirs(String s)
    {
        throwIfUserRequired();
        return sCurrentUser.buildExternalStorageAppDataDirs(s);
    }

    public static File[] buildExternalStorageAppFilesDirs(String s)
    {
        throwIfUserRequired();
        return sCurrentUser.buildExternalStorageAppFilesDirs(s);
    }

    public static File[] buildExternalStorageAppMediaDirs(String s)
    {
        throwIfUserRequired();
        return sCurrentUser.buildExternalStorageAppMediaDirs(s);
    }

    public static File[] buildExternalStorageAppObbDirs(String s)
    {
        throwIfUserRequired();
        return sCurrentUser.buildExternalStorageAppObbDirs(s);
    }

    public static transient File buildPath(File file, String as[])
    {
        int i = 0;
        int j = as.length;
        while(i < j) 
        {
            String s = as[i];
            if(file == null)
                file = new File(s);
            else
                file = new File(file, s);
            i++;
        }
        return file;
    }

    public static transient File[] buildPaths(File afile[], String as[])
    {
        File afile1[] = new File[afile.length];
        for(int i = 0; i < afile.length; i++)
            afile1[i] = buildPath(afile[i], as);

        return afile1;
    }

    public static File getDataAppDirectory(String s)
    {
        return new File(getDataDirectory(s), "app");
    }

    public static File getDataDirectory()
    {
        return DIR_ANDROID_DATA;
    }

    public static File getDataDirectory(String s)
    {
        if(TextUtils.isEmpty(s))
            return DIR_ANDROID_DATA;
        else
            return new File((new StringBuilder()).append("/mnt/expand/").append(s).toString());
    }

    public static File getDataMiscCeDirectory()
    {
        return buildPath(getDataDirectory(), new String[] {
            "misc_ce"
        });
    }

    public static File getDataMiscCeDirectory(int i)
    {
        return buildPath(getDataDirectory(), new String[] {
            "misc_ce", String.valueOf(i)
        });
    }

    public static File getDataMiscDeDirectory(int i)
    {
        return buildPath(getDataDirectory(), new String[] {
            "misc_de", String.valueOf(i)
        });
    }

    public static File getDataMiscDirectory()
    {
        return new File(getDataDirectory(), "misc");
    }

    public static File getDataPreloadsAppsDirectory()
    {
        return new File(getDataPreloadsDirectory(), "apps");
    }

    public static File getDataPreloadsDemoDirectory()
    {
        return new File(getDataPreloadsDirectory(), "demo");
    }

    public static File getDataPreloadsDirectory()
    {
        return new File(getDataDirectory(), "preloads");
    }

    public static File getDataPreloadsFileCacheDirectory()
    {
        return new File(getDataPreloadsDirectory(), "file_cache");
    }

    public static File getDataPreloadsFileCacheDirectory(String s)
    {
        return new File(getDataPreloadsFileCacheDirectory(), s);
    }

    public static File getDataPreloadsMediaDirectory()
    {
        return new File(getDataPreloadsDirectory(), "media");
    }

    private static File getDataProfilesDeDirectory(int i)
    {
        return buildPath(getDataDirectory(), new String[] {
            "misc", "profiles", "cur", String.valueOf(i)
        });
    }

    public static File getDataProfilesDePackageDirectory(int i, String s)
    {
        return buildPath(getDataProfilesDeDirectory(i), new String[] {
            s
        });
    }

    public static File getDataSystemCeDirectory()
    {
        return buildPath(getDataDirectory(), new String[] {
            "system_ce"
        });
    }

    public static File getDataSystemCeDirectory(int i)
    {
        return buildPath(getDataDirectory(), new String[] {
            "system_ce", String.valueOf(i)
        });
    }

    public static File getDataSystemDeDirectory()
    {
        return buildPath(getDataDirectory(), new String[] {
            "system_de"
        });
    }

    public static File getDataSystemDeDirectory(int i)
    {
        return buildPath(getDataDirectory(), new String[] {
            "system_de", String.valueOf(i)
        });
    }

    public static File getDataSystemDirectory()
    {
        return new File(getDataDirectory(), "system");
    }

    public static File getDataUserCeDirectory(String s)
    {
        return new File(getDataDirectory(s), "user");
    }

    public static File getDataUserCeDirectory(String s, int i)
    {
        return new File(getDataUserCeDirectory(s), String.valueOf(i));
    }

    public static File getDataUserCePackageDirectory(String s, int i, String s1)
    {
        return new File(getDataUserCeDirectory(s, i), s1);
    }

    public static File getDataUserDeDirectory(String s)
    {
        return new File(getDataDirectory(s), "user_de");
    }

    public static File getDataUserDeDirectory(String s, int i)
    {
        return new File(getDataUserDeDirectory(s), String.valueOf(i));
    }

    public static File getDataUserDePackageDirectory(String s, int i, String s1)
    {
        return new File(getDataUserDeDirectory(s, i), s1);
    }

    static File getDirectory(String s, String s1)
    {
        s = System.getenv(s);
        if(s == null)
            s = new File(s1);
        else
            s = new File(s);
        return s;
    }

    public static File getDownloadCacheDirectory()
    {
        return DIR_DOWNLOAD_CACHE;
    }

    public static File getExpandDirectory()
    {
        return DIR_ANDROID_EXPAND;
    }

    public static File getExternalStorageDirectory()
    {
        throwIfUserRequired();
        return sCurrentUser.getExternalDirs()[0];
    }

    public static File getExternalStoragePublicDirectory(String s)
    {
        throwIfUserRequired();
        return sCurrentUser.buildExternalStoragePublicDirs(s)[0];
    }

    public static String getExternalStorageState()
    {
        return getExternalStorageState(sCurrentUser.getExternalDirs()[0]);
    }

    public static String getExternalStorageState(File file)
    {
        file = StorageManager.getStorageVolume(file, UserHandle.myUserId());
        if(file != null)
            return file.getState();
        else
            return "unknown";
    }

    public static File getLegacyExternalStorageDirectory()
    {
        return new File(System.getenv("EXTERNAL_STORAGE"));
    }

    public static File getLegacyExternalStorageObbDirectory()
    {
        return buildPath(getLegacyExternalStorageDirectory(), new String[] {
            "Android", "obb"
        });
    }

    public static File getOdmDirectory()
    {
        return DIR_ODM_ROOT;
    }

    public static File getOemDirectory()
    {
        return DIR_OEM_ROOT;
    }

    public static File getReferenceProfile(String s)
    {
        return buildPath(getDataDirectory(), new String[] {
            "misc", "profiles", "ref", s
        });
    }

    public static File getRootDirectory()
    {
        return DIR_ANDROID_ROOT;
    }

    public static File getStorageDirectory()
    {
        return DIR_ANDROID_STORAGE;
    }

    public static String getStorageState(File file)
    {
        return getExternalStorageState(file);
    }

    public static File getUserConfigDirectory(int i)
    {
        return new File(new File(new File(getDataDirectory(), "misc"), "user"), Integer.toString(i));
    }

    public static File getUserSystemDirectory(int i)
    {
        return new File(new File(getDataSystemDirectory(), "users"), Integer.toString(i));
    }

    public static File getVendorDirectory()
    {
        return DIR_VENDOR_ROOT;
    }

    public static void initForCurrentUser()
    {
        sCurrentUser = new UserEnvironment(UserHandle.myUserId());
    }

    public static boolean isExternalStorageEmulated()
    {
        if(isStorageDisabled())
            return false;
        else
            return isExternalStorageEmulated(sCurrentUser.getExternalDirs()[0]);
    }

    public static boolean isExternalStorageEmulated(File file)
    {
        StorageVolume storagevolume = StorageManager.getStorageVolume(file, UserHandle.myUserId());
        if(storagevolume != null)
            return storagevolume.isEmulated();
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Failed to find storage device at ").append(file).toString());
    }

    public static boolean isExternalStorageRemovable()
    {
        if(isStorageDisabled())
            return false;
        else
            return isExternalStorageRemovable(sCurrentUser.getExternalDirs()[0]);
    }

    public static boolean isExternalStorageRemovable(File file)
    {
        StorageVolume storagevolume = StorageManager.getStorageVolume(file, UserHandle.myUserId());
        if(storagevolume != null)
            return storagevolume.isRemovable();
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Failed to find storage device at ").append(file).toString());
    }

    public static boolean isStandardDirectory(String s)
    {
        String as[] = STANDARD_DIRECTORIES;
        int i = as.length;
        for(int j = 0; j < i; j++)
            if(as[j].equals(s))
                return true;

        return false;
    }

    private static boolean isStorageDisabled()
    {
        return SystemProperties.getBoolean("config.disable_storage", false);
    }

    public static File maybeTranslateEmulatedPathToInternal(File file)
    {
        return StorageManager.maybeTranslateEmulatedPathToInternal(file);
    }

    public static void setUserRequired(boolean flag)
    {
        sUserRequired = flag;
    }

    private static void throwIfUserRequired()
    {
        if(sUserRequired)
            Log.wtf("Environment", "Path requests must specify a user by using UserEnvironment", new Throwable());
    }

    public static String DIRECTORY_ALARMS;
    public static final String DIRECTORY_ANDROID = "Android";
    public static String DIRECTORY_DCIM;
    public static String DIRECTORY_DOCUMENTS;
    public static String DIRECTORY_DOWNLOADS;
    public static String DIRECTORY_MOVIES;
    public static String DIRECTORY_MUSIC;
    public static String DIRECTORY_NOTIFICATIONS;
    public static String DIRECTORY_PICTURES;
    public static String DIRECTORY_PODCASTS;
    public static String DIRECTORY_RINGTONES;
    public static final String DIR_ANDROID = "Android";
    private static final File DIR_ANDROID_DATA = getDirectory("ANDROID_DATA", "/data");
    private static final File DIR_ANDROID_EXPAND = getDirectory("ANDROID_EXPAND", "/mnt/expand");
    private static final File DIR_ANDROID_ROOT = getDirectory("ANDROID_ROOT", "/system");
    private static final File DIR_ANDROID_STORAGE = getDirectory("ANDROID_STORAGE", "/storage");
    private static final String DIR_CACHE = "cache";
    private static final String DIR_DATA = "data";
    private static final File DIR_DOWNLOAD_CACHE = getDirectory("DOWNLOAD_CACHE", "/cache");
    private static final String DIR_FILES = "files";
    private static final String DIR_MEDIA = "media";
    private static final String DIR_OBB = "obb";
    private static final File DIR_ODM_ROOT = getDirectory("ODM_ROOT", "/odm");
    private static final File DIR_OEM_ROOT = getDirectory("OEM_ROOT", "/oem");
    private static final File DIR_VENDOR_ROOT = getDirectory("VENDOR_ROOT", "/vendor");
    private static final String ENV_ANDROID_DATA = "ANDROID_DATA";
    private static final String ENV_ANDROID_EXPAND = "ANDROID_EXPAND";
    private static final String ENV_ANDROID_ROOT = "ANDROID_ROOT";
    private static final String ENV_ANDROID_STORAGE = "ANDROID_STORAGE";
    private static final String ENV_DOWNLOAD_CACHE = "DOWNLOAD_CACHE";
    private static final String ENV_EXTERNAL_STORAGE = "EXTERNAL_STORAGE";
    private static final String ENV_ODM_ROOT = "ODM_ROOT";
    private static final String ENV_OEM_ROOT = "OEM_ROOT";
    private static final String ENV_VENDOR_ROOT = "VENDOR_ROOT";
    public static final String MEDIA_BAD_REMOVAL = "bad_removal";
    public static final String MEDIA_CHECKING = "checking";
    public static final String MEDIA_EJECTING = "ejecting";
    public static final String MEDIA_MOUNTED = "mounted";
    public static final String MEDIA_MOUNTED_READ_ONLY = "mounted_ro";
    public static final String MEDIA_NOFS = "nofs";
    public static final String MEDIA_REMOVED = "removed";
    public static final String MEDIA_SHARED = "shared";
    public static final String MEDIA_UNKNOWN = "unknown";
    public static final String MEDIA_UNMOUNTABLE = "unmountable";
    public static final String MEDIA_UNMOUNTED = "unmounted";
    public static final String STANDARD_DIRECTORIES[];
    private static final String TAG = "Environment";
    private static UserEnvironment sCurrentUser;
    private static boolean sUserRequired;

    static 
    {
        initForCurrentUser();
        DIRECTORY_MUSIC = "Music";
        DIRECTORY_PODCASTS = "Podcasts";
        DIRECTORY_RINGTONES = "Ringtones";
        DIRECTORY_ALARMS = "Alarms";
        DIRECTORY_NOTIFICATIONS = "Notifications";
        DIRECTORY_PICTURES = "Pictures";
        DIRECTORY_MOVIES = "Movies";
        DIRECTORY_DOWNLOADS = "Download";
        DIRECTORY_DCIM = "DCIM";
        DIRECTORY_DOCUMENTS = "Documents";
        STANDARD_DIRECTORIES = (new String[] {
            DIRECTORY_MUSIC, DIRECTORY_PODCASTS, DIRECTORY_RINGTONES, DIRECTORY_ALARMS, DIRECTORY_NOTIFICATIONS, DIRECTORY_PICTURES, DIRECTORY_MOVIES, DIRECTORY_DOWNLOADS, DIRECTORY_DCIM, DIRECTORY_DOCUMENTS
        });
    }
}
