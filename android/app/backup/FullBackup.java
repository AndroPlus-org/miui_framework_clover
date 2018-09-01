// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.backup;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.system.ErrnoException;
import android.system.Os;
import android.text.TextUtils;
import android.util.*;
import java.io.*;
import java.util.*;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.app.backup:
//            FullBackupDataOutput

public class FullBackup
{
    public static class BackupScheme
    {

        private File extractCanonicalFile(File file, String s)
        {
            String s1 = s;
            if(s == null)
                s1 = "";
            if(s1.contains(".."))
            {
                if(Log.isLoggable("BackupXmlParserLogging", 2))
                    Log.v("BackupXmlParserLogging", (new StringBuilder()).append("...resolved \"").append(file.getPath()).append(" ").append(s1).append("\", but the \"..\" path is not permitted; skipping.").toString());
                return null;
            }
            if(s1.contains("//"))
            {
                if(Log.isLoggable("BackupXmlParserLogging", 2))
                    Log.v("BackupXmlParserLogging", (new StringBuilder()).append("...resolved \"").append(file.getPath()).append(" ").append(s1).append("\", which contains the invalid \"//\" sequence; skipping.").toString());
                return null;
            } else
            {
                return new File(file, s1);
            }
        }

        private File getDirectoryForCriteriaDomain(String s)
        {
            if(TextUtils.isEmpty(s))
                return null;
            if("file".equals(s))
                return FILES_DIR;
            if("database".equals(s))
                return DATABASE_DIR;
            if("root".equals(s))
                return ROOT_DIR;
            if("sharedpref".equals(s))
                return SHAREDPREF_DIR;
            if("device_file".equals(s))
                return DEVICE_FILES_DIR;
            if("device_database".equals(s))
                return DEVICE_DATABASE_DIR;
            if("device_root".equals(s))
                return DEVICE_ROOT_DIR;
            if("device_sharedpref".equals(s))
                return DEVICE_SHAREDPREF_DIR;
            if("external".equals(s))
                return EXTERNAL_DIR;
            else
                return null;
        }

        private String getTokenForXmlDomain(String s)
        {
            if("root".equals(s))
                return "r";
            if("file".equals(s))
                return "f";
            if("database".equals(s))
                return "db";
            if("sharedpref".equals(s))
                return "sp";
            if("device_root".equals(s))
                return "d_r";
            if("device_file".equals(s))
                return "d_f";
            if("device_database".equals(s))
                return "d_db";
            if("device_sharedpref".equals(s))
                return "d_sp";
            if("external".equals(s))
                return "ef";
            else
                return null;
        }

        private StorageVolume[] getVolumeList()
        {
            if(mStorageManager != null)
            {
                if(mVolumes == null)
                    mVolumes = mStorageManager.getVolumeList();
            } else
            {
                Log.e("FullBackup", "Unable to access Storage Manager");
            }
            return mVolumes;
        }

        private void maybeParseBackupSchemeLocked()
            throws IOException, XmlPullParserException
        {
            mIncludes = new ArrayMap();
            mExcludes = new ArraySet();
            if(mFullBackupContent != 0) goto _L2; else goto _L1
_L1:
            if(Log.isLoggable("BackupXmlParserLogging", 2))
                Log.v("BackupXmlParserLogging", "android:fullBackupContent - \"true\"");
_L4:
            return;
_L2:
            XmlResourceParser xmlresourceparser;
            XmlResourceParser xmlresourceparser1;
            if(Log.isLoggable("BackupXmlParserLogging", 2))
                Log.v("BackupXmlParserLogging", "android:fullBackupContent - found xml resource");
            xmlresourceparser = null;
            xmlresourceparser1 = null;
            XmlResourceParser xmlresourceparser2 = mPackageManager.getResourcesForApplication(mPackageName).getXml(mFullBackupContent);
            xmlresourceparser1 = xmlresourceparser2;
            xmlresourceparser = xmlresourceparser2;
            parseBackupSchemeFromXmlLocked(xmlresourceparser2, mExcludes, mIncludes);
            if(xmlresourceparser2 != null)
                xmlresourceparser2.close();
            if(true) goto _L4; else goto _L3
_L3:
            android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
            namenotfoundexception;
            xmlresourceparser = xmlresourceparser1;
            IOException ioexception = JVM INSTR new #261 <Class IOException>;
            xmlresourceparser = xmlresourceparser1;
            ioexception.IOException(namenotfoundexception);
            xmlresourceparser = xmlresourceparser1;
            throw ioexception;
            Exception exception;
            exception;
            if(xmlresourceparser != null)
                xmlresourceparser.close();
            throw exception;
        }

        private Set parseCurrentTagForDomain(XmlPullParser xmlpullparser, Set set, Map map, String s)
            throws XmlPullParserException
        {
            if("include".equals(xmlpullparser.getName()))
            {
                s = getTokenForXmlDomain(s);
                set = (Set)map.get(s);
                xmlpullparser = set;
                if(set == null)
                {
                    xmlpullparser = new ArraySet();
                    map.put(s, xmlpullparser);
                }
                return xmlpullparser;
            }
            if("exclude".equals(xmlpullparser.getName()))
                return set;
            if(Log.isLoggable("BackupXmlParserLogging", 2))
                Log.v("BackupXmlParserLogging", (new StringBuilder()).append("Invalid tag found in xml \"").append(xmlpullparser.getName()).append("\"; aborting operation.").toString());
            throw new XmlPullParserException((new StringBuilder()).append("Unrecognised tag in backup criteria xml (").append(xmlpullparser.getName()).append(")").toString());
        }

        private String sharedDomainToPath(String s)
            throws IOException
        {
            s = s.substring("shared/".length());
            StorageVolume astoragevolume[] = getVolumeList();
            int i = Integer.parseInt(s);
            if(i < mVolumes.length)
                return astoragevolume[i].getPathFile().getCanonicalPath();
            else
                return null;
        }

        private void validateInnerTagContents(XmlPullParser xmlpullparser)
            throws XmlPullParserException
        {
            if(xmlpullparser.getAttributeCount() > 2)
                throw new XmlPullParserException((new StringBuilder()).append("At most 2 tag attributes allowed for \"").append(xmlpullparser.getName()).append("\" tag (\"domain\" & \"path\".").toString());
            if(!"include".equals(xmlpullparser.getName()) && "exclude".equals(xmlpullparser.getName()) ^ true)
                throw new XmlPullParserException((new StringBuilder()).append("A valid tag is one of \"<include/>\" or \"<exclude/>. You provided \"").append(xmlpullparser.getName()).append("\"").toString());
            else
                return;
        }

        boolean isFullBackupContentEnabled()
        {
            if(mFullBackupContent < 0)
            {
                if(Log.isLoggable("BackupXmlParserLogging", 2))
                    Log.v("BackupXmlParserLogging", "android:fullBackupContent - \"false\"");
                return false;
            } else
            {
                return true;
            }
        }

        public ArraySet maybeParseAndGetCanonicalExcludePaths()
            throws IOException, XmlPullParserException
        {
            this;
            JVM INSTR monitorenter ;
            ArraySet arrayset;
            if(mExcludes == null)
                maybeParseBackupSchemeLocked();
            arrayset = mExcludes;
            this;
            JVM INSTR monitorexit ;
            return arrayset;
            Exception exception;
            exception;
            throw exception;
        }

        public Map maybeParseAndGetCanonicalIncludePaths()
            throws IOException, XmlPullParserException
        {
            this;
            JVM INSTR monitorenter ;
            Map map;
            if(mIncludes == null)
                maybeParseBackupSchemeLocked();
            map = mIncludes;
            this;
            JVM INSTR monitorexit ;
            return map;
            Exception exception;
            exception;
            throw exception;
        }

        public void parseBackupSchemeFromXmlLocked(XmlPullParser xmlpullparser, Set set, Map map)
            throws IOException, XmlPullParserException
        {
            for(int i = xmlpullparser.getEventType(); i != 2; i = xmlpullparser.next());
            if(!"full-backup-content".equals(xmlpullparser.getName()))
                throw new XmlPullParserException((new StringBuilder()).append("Xml file didn't start with correct tag (<full-backup-content>). Found \"").append(xmlpullparser.getName()).append("\"").toString());
            if(Log.isLoggable("BackupXmlParserLogging", 2))
            {
                Log.v("BackupXmlParserLogging", "\n");
                Log.v("BackupXmlParserLogging", "====================================================");
                Log.v("BackupXmlParserLogging", "Found valid fullBackupContent; parsing xml resource.");
                Log.v("BackupXmlParserLogging", "====================================================");
                Log.v("BackupXmlParserLogging", "");
            }
            do
            {
                int j = xmlpullparser.next();
                if(j == 1)
                    break;
                switch(j)
                {
                case 2: // '\002'
                    validateInnerTagContents(xmlpullparser);
                    String s = xmlpullparser.getAttributeValue(null, "domain");
                    File file = getDirectoryForCriteriaDomain(s);
                    if(file == null)
                    {
                        if(Log.isLoggable("BackupXmlParserLogging", 2))
                            Log.v("BackupXmlParserLogging", (new StringBuilder()).append("...parsing \"").append(xmlpullparser.getName()).append("\": ").append("domain=\"").append(s).append("\" invalid; skipping").toString());
                    } else
                    {
                        File file1 = extractCanonicalFile(file, xmlpullparser.getAttributeValue(null, "path"));
                        if(file1 != null)
                        {
                            Set set1 = parseCurrentTagForDomain(xmlpullparser, set, map, s);
                            set1.add(file1.getCanonicalPath());
                            if(Log.isLoggable("BackupXmlParserLogging", 2))
                                Log.v("BackupXmlParserLogging", (new StringBuilder()).append("...parsed ").append(file1.getCanonicalPath()).append(" for domain \"").append(s).append("\"").toString());
                            if("database".equals(s) && file1.isDirectory() ^ true)
                            {
                                String s3 = (new StringBuilder()).append(file1.getCanonicalPath()).append("-journal").toString();
                                set1.add(s3);
                                if(Log.isLoggable("BackupXmlParserLogging", 2))
                                    Log.v("BackupXmlParserLogging", (new StringBuilder()).append("...automatically generated ").append(s3).append(". Ignore if nonexistent.").toString());
                                s3 = (new StringBuilder()).append(file1.getCanonicalPath()).append("-wal").toString();
                                set1.add(s3);
                                if(Log.isLoggable("BackupXmlParserLogging", 2))
                                    Log.v("BackupXmlParserLogging", (new StringBuilder()).append("...automatically generated ").append(s3).append(". Ignore if nonexistent.").toString());
                            }
                            if("sharedpref".equals(s) && file1.isDirectory() ^ true && file1.getCanonicalPath().endsWith(".xml") ^ true)
                            {
                                String s1 = (new StringBuilder()).append(file1.getCanonicalPath()).append(".xml").toString();
                                set1.add(s1);
                                if(Log.isLoggable("BackupXmlParserLogging", 2))
                                    Log.v("BackupXmlParserLogging", (new StringBuilder()).append("...automatically generated ").append(s1).append(". Ignore if nonexistent.").toString());
                            }
                        }
                    }
                    break;
                }
            } while(true);
            if(Log.isLoggable("BackupXmlParserLogging", 2))
            {
                Log.v("BackupXmlParserLogging", "\n");
                Log.v("BackupXmlParserLogging", "Xml resource parsing complete.");
                Log.v("BackupXmlParserLogging", "Final tally.");
                Log.v("BackupXmlParserLogging", "Includes:");
                if(map.isEmpty())
                {
                    Log.v("BackupXmlParserLogging", "  ...nothing specified (This means the entirety of app data minus excludes)");
                } else
                {
                    xmlpullparser = map.entrySet().iterator();
                    while(xmlpullparser.hasNext()) 
                    {
                        map = (java.util.Map.Entry)xmlpullparser.next();
                        Log.v("BackupXmlParserLogging", (new StringBuilder()).append("  domain=").append((String)map.getKey()).toString());
                        map = ((Set)map.getValue()).iterator();
                        while(map.hasNext()) 
                        {
                            String s2 = (String)map.next();
                            Log.v("BackupXmlParserLogging", (new StringBuilder()).append("  ").append(s2).toString());
                        }
                    }
                }
                Log.v("BackupXmlParserLogging", "Excludes:");
                if(set.isEmpty())
                {
                    Log.v("BackupXmlParserLogging", "  ...nothing to exclude.");
                } else
                {
                    set = set.iterator();
                    while(set.hasNext()) 
                    {
                        xmlpullparser = (String)set.next();
                        Log.v("BackupXmlParserLogging", (new StringBuilder()).append("  ").append(xmlpullparser).toString());
                    }
                }
                Log.v("BackupXmlParserLogging", "  ");
                Log.v("BackupXmlParserLogging", "====================================================");
                Log.v("BackupXmlParserLogging", "\n");
            }
        }

        String tokenToDirectoryPath(String s)
        {
            if(s.equals("f"))
                return FILES_DIR.getCanonicalPath();
            if(s.equals("db"))
                return DATABASE_DIR.getCanonicalPath();
            if(s.equals("r"))
                return ROOT_DIR.getCanonicalPath();
            if(s.equals("sp"))
                return SHAREDPREF_DIR.getCanonicalPath();
            if(s.equals("c"))
                return CACHE_DIR.getCanonicalPath();
            if(s.equals("nb"))
                return NOBACKUP_DIR.getCanonicalPath();
            if(s.equals("d_f"))
                return DEVICE_FILES_DIR.getCanonicalPath();
            if(s.equals("d_db"))
                return DEVICE_DATABASE_DIR.getCanonicalPath();
            if(s.equals("d_r"))
                return DEVICE_ROOT_DIR.getCanonicalPath();
            if(s.equals("d_sp"))
                return DEVICE_SHAREDPREF_DIR.getCanonicalPath();
            if(s.equals("d_c"))
                return DEVICE_CACHE_DIR.getCanonicalPath();
            if(s.equals("d_nb"))
                return DEVICE_NOBACKUP_DIR.getCanonicalPath();
            if(!s.equals("ef"))
                break MISSING_BLOCK_LABEL_234;
            if(EXTERNAL_DIR != null)
                return EXTERNAL_DIR.getCanonicalPath();
            return null;
            try
            {
                if(s.startsWith("shared/"))
                    return sharedDomainToPath(s);
                StringBuilder stringbuilder = JVM INSTR new #171 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Log.i("FullBackup", stringbuilder.append("Unrecognized domain ").append(s).toString());
            }
            catch(Exception exception)
            {
                Log.i("FullBackup", (new StringBuilder()).append("Error reading directory for domain: ").append(s).toString());
                return null;
            }
            return null;
        }

        private final File CACHE_DIR;
        private final File DATABASE_DIR;
        private final File DEVICE_CACHE_DIR;
        private final File DEVICE_DATABASE_DIR;
        private final File DEVICE_FILES_DIR;
        private final File DEVICE_NOBACKUP_DIR;
        private final File DEVICE_ROOT_DIR;
        private final File DEVICE_SHAREDPREF_DIR;
        private final File EXTERNAL_DIR;
        private final File FILES_DIR;
        private final File NOBACKUP_DIR;
        private final File ROOT_DIR;
        private final File SHAREDPREF_DIR;
        ArraySet mExcludes;
        final int mFullBackupContent;
        Map mIncludes;
        final PackageManager mPackageManager;
        final String mPackageName;
        final StorageManager mStorageManager;
        private StorageVolume mVolumes[];

        BackupScheme(Context context)
        {
            mVolumes = null;
            mFullBackupContent = context.getApplicationInfo().fullBackupContent;
            mStorageManager = (StorageManager)context.getSystemService("storage");
            mPackageManager = context.getPackageManager();
            mPackageName = context.getPackageName();
            Context context1 = context.createCredentialProtectedStorageContext();
            FILES_DIR = context1.getFilesDir();
            DATABASE_DIR = context1.getDatabasePath("foo").getParentFile();
            ROOT_DIR = context1.getDataDir();
            SHAREDPREF_DIR = context1.getSharedPreferencesPath("foo").getParentFile();
            CACHE_DIR = context1.getCacheDir();
            NOBACKUP_DIR = context1.getNoBackupFilesDir();
            context1 = context.createDeviceProtectedStorageContext();
            DEVICE_FILES_DIR = context1.getFilesDir();
            DEVICE_DATABASE_DIR = context1.getDatabasePath("foo").getParentFile();
            DEVICE_ROOT_DIR = context1.getDataDir();
            DEVICE_SHAREDPREF_DIR = context1.getSharedPreferencesPath("foo").getParentFile();
            DEVICE_CACHE_DIR = context1.getCacheDir();
            DEVICE_NOBACKUP_DIR = context1.getNoBackupFilesDir();
            if(Process.myUid() != 1000)
                EXTERNAL_DIR = context.getExternalFilesDir(null);
            else
                EXTERNAL_DIR = null;
        }
    }


    public FullBackup()
    {
    }

    public static native int backupToTar(String s, String s1, String s2, String s3, String s4, FullBackupDataOutput fullbackupdataoutput);

    static BackupScheme getBackupScheme(Context context)
    {
        android/app/backup/FullBackup;
        JVM INSTR monitorenter ;
        BackupScheme backupscheme = (BackupScheme)kPackageBackupSchemeMap.get(context.getPackageName());
        BackupScheme backupscheme1;
        backupscheme1 = backupscheme;
        if(backupscheme != null)
            break MISSING_BLOCK_LABEL_48;
        backupscheme1 = JVM INSTR new #6   <Class FullBackup$BackupScheme>;
        backupscheme1.BackupScheme(context);
        kPackageBackupSchemeMap.put(context.getPackageName(), backupscheme1);
        android/app/backup/FullBackup;
        JVM INSTR monitorexit ;
        return backupscheme1;
        context;
        throw context;
    }

    public static BackupScheme getBackupSchemeForTest(Context context)
    {
        context = new BackupScheme(context);
        context.mExcludes = new ArraySet();
        context.mIncludes = new ArrayMap();
        return context;
    }

    public static void restoreFile(ParcelFileDescriptor parcelfiledescriptor, long l, int i, long l1, long l2, 
            File file)
        throws IOException
    {
        if(i != 2) goto _L2; else goto _L1
_L1:
        if(file != null)
            file.mkdirs();
_L3:
        if(l1 >= 0L && file != null)
        {
            byte abyte0[];
            Object obj;
            FileInputStream fileinputstream;
            long l3;
            try
            {
                Os.chmod(file.getPath(), (int)(l1 & 448L));
            }
            // Misplaced declaration of an exception variable
            catch(ParcelFileDescriptor parcelfiledescriptor)
            {
                parcelfiledescriptor.rethrowAsIOException();
            }
            file.setLastModified(l2);
        }
        return;
_L2:
        abyte0 = null;
        obj = abyte0;
        if(file != null)
            try
            {
                obj = file.getParentFile();
                if(!((File) (obj)).exists())
                    ((File) (obj)).mkdirs();
                obj = JVM INSTR new #164 <Class FileOutputStream>;
                ((FileOutputStream) (obj)).FileOutputStream(file);
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                Log.e("FullBackup", (new StringBuilder()).append("Unable to create/open file ").append(file.getPath()).toString(), ((Throwable) (obj)));
                obj = abyte0;
            }
        abyte0 = new byte[32768];
        fileinputstream = new FileInputStream(parcelfiledescriptor.getFileDescriptor());
        l3 = l;
_L4:
label0:
        {
            if(l3 > 0L)
            {
                if(l3 > (long)abyte0.length)
                    i = abyte0.length;
                else
                    i = (int)l3;
                i = fileinputstream.read(abyte0, 0, i);
                if(i > 0)
                    break label0;
                Log.w("FullBackup", (new StringBuilder()).append("Incomplete read: expected ").append(l3).append(" but got ").append(l - l3).toString());
            }
            if(obj != null)
                ((FileOutputStream) (obj)).close();
        }
          goto _L3
        parcelfiledescriptor = ((ParcelFileDescriptor) (obj));
        if(obj == null)
            break MISSING_BLOCK_LABEL_272;
        ((FileOutputStream) (obj)).write(abyte0, 0, i);
        parcelfiledescriptor = ((ParcelFileDescriptor) (obj));
_L5:
        l3 -= i;
        obj = parcelfiledescriptor;
          goto _L4
        parcelfiledescriptor;
        Log.e("FullBackup", (new StringBuilder()).append("Unable to write to file ").append(file.getPath()).toString(), parcelfiledescriptor);
        ((FileOutputStream) (obj)).close();
        parcelfiledescriptor = null;
        file.delete();
          goto _L5
    }

    public static final String APK_TREE_TOKEN = "a";
    public static final String APPS_PREFIX = "apps/";
    public static final String CACHE_TREE_TOKEN = "c";
    public static final String CONF_TOKEN_INTENT_EXTRA = "conftoken";
    public static final String DATABASE_TREE_TOKEN = "db";
    public static final String DEVICE_CACHE_TREE_TOKEN = "d_c";
    public static final String DEVICE_DATABASE_TREE_TOKEN = "d_db";
    public static final String DEVICE_FILES_TREE_TOKEN = "d_f";
    public static final String DEVICE_NO_BACKUP_TREE_TOKEN = "d_nb";
    public static final String DEVICE_ROOT_TREE_TOKEN = "d_r";
    public static final String DEVICE_SHAREDPREFS_TREE_TOKEN = "d_sp";
    public static final String FILES_TREE_TOKEN = "f";
    public static final String FULL_BACKUP_INTENT_ACTION = "fullback";
    public static final String FULL_RESTORE_INTENT_ACTION = "fullrest";
    public static final String KEY_VALUE_DATA_TOKEN = "k";
    public static final String MANAGED_EXTERNAL_TREE_TOKEN = "ef";
    public static final String NO_BACKUP_TREE_TOKEN = "nb";
    public static final String OBB_TREE_TOKEN = "obb";
    public static final String ROOT_TREE_TOKEN = "r";
    public static final String SHAREDPREFS_TREE_TOKEN = "sp";
    public static final String SHARED_PREFIX = "shared/";
    public static final String SHARED_STORAGE_TOKEN = "shared";
    static final String TAG = "FullBackup";
    static final String TAG_XML_PARSER = "BackupXmlParserLogging";
    private static final Map kPackageBackupSchemeMap = new ArrayMap();

}
