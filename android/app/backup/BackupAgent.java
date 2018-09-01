// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.backup;

import android.app.QueuedWork;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ApplicationInfo;
import android.os.*;
import android.system.*;
import android.util.ArraySet;
import android.util.Log;
import java.io.*;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import libcore.io.IoUtils;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.app.backup:
//            FullBackup, FullBackupDataOutput, BackupDataOutput, BackupDataInput, 
//            IBackupManager

public abstract class BackupAgent extends ContextWrapper
{
    private class BackupServiceBinder extends android.app.IBackupAgent.Stub
    {

        public void doBackup(ParcelFileDescriptor parcelfiledescriptor, ParcelFileDescriptor parcelfiledescriptor1, ParcelFileDescriptor parcelfiledescriptor2, long l, int i, IBackupManager ibackupmanager)
            throws RemoteException
        {
            long l1;
            BackupDataOutput backupdataoutput;
            l1 = Binder.clearCallingIdentity();
            backupdataoutput = new BackupDataOutput(parcelfiledescriptor1.getFileDescriptor(), l);
            onBackup(parcelfiledescriptor, backupdataoutput, parcelfiledescriptor2);
            BackupAgent._2D_wrap0(BackupAgent.this);
            Binder.restoreCallingIdentity(l1);
            try
            {
                ibackupmanager.opComplete(i, 0L);
            }
            // Misplaced declaration of an exception variable
            catch(IBackupManager ibackupmanager) { }
            if(Binder.getCallingPid() != Process.myPid())
            {
                IoUtils.closeQuietly(parcelfiledescriptor);
                IoUtils.closeQuietly(parcelfiledescriptor1);
                IoUtils.closeQuietly(parcelfiledescriptor2);
            }
            return;
            RuntimeException runtimeexception;
            runtimeexception;
            StringBuilder stringbuilder = JVM INSTR new #82  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.d("BackupServiceBinder", stringbuilder.append("onBackup (").append(getClass().getName()).append(") threw").toString(), runtimeexception);
            throw runtimeexception;
            Object obj;
            obj;
            BackupAgent._2D_wrap0(BackupAgent.this);
            Binder.restoreCallingIdentity(l1);
            Object obj1;
            try
            {
                ibackupmanager.opComplete(i, 0L);
            }
            // Misplaced declaration of an exception variable
            catch(IBackupManager ibackupmanager) { }
            if(Binder.getCallingPid() != Process.myPid())
            {
                IoUtils.closeQuietly(parcelfiledescriptor);
                IoUtils.closeQuietly(parcelfiledescriptor1);
                IoUtils.closeQuietly(parcelfiledescriptor2);
            }
            throw obj;
            obj;
            obj1 = JVM INSTR new #82  <Class StringBuilder>;
            ((StringBuilder) (obj1)).StringBuilder();
            Log.d("BackupServiceBinder", ((StringBuilder) (obj1)).append("onBackup (").append(getClass().getName()).append(") threw").toString(), ((Throwable) (obj)));
            obj1 = JVM INSTR new #31  <Class RuntimeException>;
            ((RuntimeException) (obj1)).RuntimeException(((Throwable) (obj)));
            throw obj1;
        }

        public void doFullBackup(ParcelFileDescriptor parcelfiledescriptor, long l, int i, IBackupManager ibackupmanager)
        {
            long l1;
            l1 = Binder.clearCallingIdentity();
            BackupAgent._2D_wrap0(BackupAgent.this);
            BackupAgent backupagent = BackupAgent.this;
            FullBackupDataOutput fullbackupdataoutput = JVM INSTR new #118 <Class FullBackupDataOutput>;
            fullbackupdataoutput.FullBackupDataOutput(parcelfiledescriptor, l);
            backupagent.onFullBackup(fullbackupdataoutput);
            BackupAgent._2D_wrap0(BackupAgent.this);
            try
            {
                FileOutputStream fileoutputstream1 = JVM INSTR new #127 <Class FileOutputStream>;
                fileoutputstream1.FileOutputStream(parcelfiledescriptor.getFileDescriptor());
                fileoutputstream1.write(new byte[4]);
            }
            catch(IOException ioexception1)
            {
                Log.e("BackupServiceBinder", "Unable to finalize backup stream!");
            }
            Binder.restoreCallingIdentity(l1);
            try
            {
                ibackupmanager.opComplete(i, 0L);
            }
            // Misplaced declaration of an exception variable
            catch(IBackupManager ibackupmanager) { }
            if(Binder.getCallingPid() != Process.myPid())
                IoUtils.closeQuietly(parcelfiledescriptor);
            return;
            RuntimeException runtimeexception;
            runtimeexception;
            StringBuilder stringbuilder = JVM INSTR new #82  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.d("BackupServiceBinder", stringbuilder.append("onFullBackup (").append(getClass().getName()).append(") threw").toString(), runtimeexception);
            throw runtimeexception;
            Object obj1;
            obj1;
            BackupAgent._2D_wrap0(BackupAgent.this);
            Object obj;
            try
            {
                FileOutputStream fileoutputstream = JVM INSTR new #127 <Class FileOutputStream>;
                fileoutputstream.FileOutputStream(parcelfiledescriptor.getFileDescriptor());
                fileoutputstream.write(new byte[4]);
            }
            catch(IOException ioexception)
            {
                Log.e("BackupServiceBinder", "Unable to finalize backup stream!");
            }
            Binder.restoreCallingIdentity(l1);
            try
            {
                ibackupmanager.opComplete(i, 0L);
            }
            // Misplaced declaration of an exception variable
            catch(IBackupManager ibackupmanager) { }
            if(Binder.getCallingPid() != Process.myPid())
                IoUtils.closeQuietly(parcelfiledescriptor);
            throw obj1;
            obj1;
            obj = JVM INSTR new #82  <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            Log.d("BackupServiceBinder", ((StringBuilder) (obj)).append("onFullBackup (").append(getClass().getName()).append(") threw").toString(), ((Throwable) (obj1)));
            obj = JVM INSTR new #31  <Class RuntimeException>;
            ((RuntimeException) (obj)).RuntimeException(((Throwable) (obj1)));
            throw obj;
        }

        public void doMeasureFullBackup(long l, int i, IBackupManager ibackupmanager)
        {
            long l1;
            FullBackupDataOutput fullbackupdataoutput;
            l1 = Binder.clearCallingIdentity();
            fullbackupdataoutput = new FullBackupDataOutput(l);
            BackupAgent._2D_wrap0(BackupAgent.this);
            onFullBackup(fullbackupdataoutput);
            Binder.restoreCallingIdentity(l1);
            ibackupmanager.opComplete(i, fullbackupdataoutput.getSize());
_L2:
            return;
            RuntimeException runtimeexception;
            runtimeexception;
            StringBuilder stringbuilder = JVM INSTR new #82  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.d("BackupServiceBinder", stringbuilder.append("onFullBackup[M] (").append(getClass().getName()).append(") threw").toString(), runtimeexception);
            throw runtimeexception;
            Object obj1;
            obj1;
            Binder.restoreCallingIdentity(l1);
            Object obj;
            try
            {
                ibackupmanager.opComplete(i, fullbackupdataoutput.getSize());
            }
            // Misplaced declaration of an exception variable
            catch(IBackupManager ibackupmanager) { }
            throw obj1;
            obj1;
            obj = JVM INSTR new #82  <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            Log.d("BackupServiceBinder", ((StringBuilder) (obj)).append("onFullBackup[M] (").append(getClass().getName()).append(") threw").toString(), ((Throwable) (obj1)));
            obj = JVM INSTR new #31  <Class RuntimeException>;
            ((RuntimeException) (obj)).RuntimeException(((Throwable) (obj1)));
            throw obj;
            ibackupmanager;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public void doQuotaExceeded(long l, long l1)
        {
            long l2 = Binder.clearCallingIdentity();
            onQuotaExceeded(l, l1);
            BackupAgent._2D_wrap0(BackupAgent.this);
            Binder.restoreCallingIdentity(l2);
            return;
            Object obj;
            obj;
            StringBuilder stringbuilder = JVM INSTR new #82  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.d("BackupServiceBinder", stringbuilder.append("onQuotaExceeded(").append(getClass().getName()).append(") threw").toString(), ((Throwable) (obj)));
            throw obj;
            obj;
            BackupAgent._2D_wrap0(BackupAgent.this);
            Binder.restoreCallingIdentity(l2);
            throw obj;
        }

        public void doRestore(ParcelFileDescriptor parcelfiledescriptor, int i, ParcelFileDescriptor parcelfiledescriptor1, int j, IBackupManager ibackupmanager)
            throws RemoteException
        {
            long l;
            BackupDataInput backupdatainput;
            l = Binder.clearCallingIdentity();
            BackupAgent._2D_wrap0(BackupAgent.this);
            backupdatainput = new BackupDataInput(parcelfiledescriptor.getFileDescriptor());
            onRestore(backupdatainput, i, parcelfiledescriptor1);
            reloadSharedPreferences();
            Binder.restoreCallingIdentity(l);
            try
            {
                ibackupmanager.opComplete(j, 0L);
            }
            // Misplaced declaration of an exception variable
            catch(IBackupManager ibackupmanager) { }
            if(Binder.getCallingPid() != Process.myPid())
            {
                IoUtils.closeQuietly(parcelfiledescriptor);
                IoUtils.closeQuietly(parcelfiledescriptor1);
            }
            return;
            Object obj;
            obj;
            StringBuilder stringbuilder = JVM INSTR new #82  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.d("BackupServiceBinder", stringbuilder.append("onRestore (").append(getClass().getName()).append(") threw").toString(), ((Throwable) (obj)));
            throw obj;
            obj;
            reloadSharedPreferences();
            Binder.restoreCallingIdentity(l);
            Object obj1;
            try
            {
                ibackupmanager.opComplete(j, 0L);
            }
            // Misplaced declaration of an exception variable
            catch(IBackupManager ibackupmanager) { }
            if(Binder.getCallingPid() != Process.myPid())
            {
                IoUtils.closeQuietly(parcelfiledescriptor);
                IoUtils.closeQuietly(parcelfiledescriptor1);
            }
            throw obj;
            obj;
            obj1 = JVM INSTR new #82  <Class StringBuilder>;
            ((StringBuilder) (obj1)).StringBuilder();
            Log.d("BackupServiceBinder", ((StringBuilder) (obj1)).append("onRestore (").append(getClass().getName()).append(") threw").toString(), ((Throwable) (obj)));
            obj1 = JVM INSTR new #31  <Class RuntimeException>;
            ((RuntimeException) (obj1)).RuntimeException(((Throwable) (obj)));
            throw obj1;
        }

        public void doRestoreFile(ParcelFileDescriptor parcelfiledescriptor, long l, int i, String s, String s1, long l1, long l2, int j, IBackupManager ibackupmanager)
            throws RemoteException
        {
            long l3 = Binder.clearCallingIdentity();
            onRestoreFile(parcelfiledescriptor, l, i, s, s1, l1, l2);
            BackupAgent._2D_wrap0(BackupAgent.this);
            reloadSharedPreferences();
            Binder.restoreCallingIdentity(l3);
            try
            {
                ibackupmanager.opComplete(j, 0L);
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
            if(Binder.getCallingPid() != Process.myPid())
                IoUtils.closeQuietly(parcelfiledescriptor);
            return;
            s;
            s1 = JVM INSTR new #82  <Class StringBuilder>;
            s1.StringBuilder();
            Log.d("BackupServiceBinder", s1.append("onRestoreFile (").append(getClass().getName()).append(") threw").toString(), s);
            s1 = JVM INSTR new #31  <Class RuntimeException>;
            s1.RuntimeException(s);
            throw s1;
            s;
            BackupAgent._2D_wrap0(BackupAgent.this);
            reloadSharedPreferences();
            Binder.restoreCallingIdentity(l3);
            try
            {
                ibackupmanager.opComplete(j, 0L);
            }
            // Misplaced declaration of an exception variable
            catch(String s1) { }
            if(Binder.getCallingPid() != Process.myPid())
                IoUtils.closeQuietly(parcelfiledescriptor);
            throw s;
        }

        public void doRestoreFinished(int i, IBackupManager ibackupmanager)
        {
            long l = Binder.clearCallingIdentity();
            onRestoreFinished();
            BackupAgent._2D_wrap0(BackupAgent.this);
            Binder.restoreCallingIdentity(l);
            ibackupmanager.opComplete(i, 0L);
_L2:
            return;
            ibackupmanager;
            if(true) goto _L2; else goto _L1
_L1:
            Exception exception;
            exception;
            StringBuilder stringbuilder = JVM INSTR new #82  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.d("BackupServiceBinder", stringbuilder.append("onRestoreFinished (").append(getClass().getName()).append(") threw").toString(), exception);
            throw exception;
            Exception exception1;
            exception1;
            BackupAgent._2D_wrap0(BackupAgent.this);
            Binder.restoreCallingIdentity(l);
            try
            {
                ibackupmanager.opComplete(i, 0L);
            }
            // Misplaced declaration of an exception variable
            catch(IBackupManager ibackupmanager) { }
            throw exception1;
        }

        public void fail(String s)
        {
            getHandler().post(new FailRunnable(s));
        }

        private static final String TAG = "BackupServiceBinder";
        final BackupAgent this$0;

        private BackupServiceBinder()
        {
            this$0 = BackupAgent.this;
            super();
        }

        BackupServiceBinder(BackupServiceBinder backupservicebinder)
        {
            this();
        }
    }

    static class FailRunnable
        implements Runnable
    {

        public void run()
        {
            throw new IllegalStateException(mMessage);
        }

        private String mMessage;

        FailRunnable(String s)
        {
            mMessage = s;
        }
    }

    class SharedPrefsSynchronizer
        implements Runnable
    {

        public void run()
        {
            QueuedWork.waitToFinish();
            mLatch.countDown();
        }

        public final CountDownLatch mLatch = new CountDownLatch(1);
        final BackupAgent this$0;

        SharedPrefsSynchronizer()
        {
            this$0 = BackupAgent.this;
            super();
        }
    }


    static void _2D_wrap0(BackupAgent backupagent)
    {
        backupagent.waitForSharedPrefs();
    }

    public BackupAgent()
    {
        super(null);
        mHandler = null;
    }

    private void applyXmlFiltersAndDoFullBackupForDomain(String s, String s1, Map map, ArraySet arrayset, ArraySet arrayset1, FullBackupDataOutput fullbackupdataoutput)
        throws IOException
    {
        if(map != null && map.size() != 0) goto _L2; else goto _L1
_L1:
        fullBackupFileTree(s, s1, FullBackup.getBackupScheme(this).tokenToDirectoryPath(s1), arrayset, arrayset1, fullbackupdataoutput);
_L4:
        return;
_L2:
        if(map.get(s1) != null)
        {
            map = ((Set)map.get(s1)).iterator();
            while(map.hasNext()) 
                fullBackupFileTree(s, s1, (String)map.next(), arrayset, arrayset1, fullbackupdataoutput);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private boolean isFileEligibleForRestore(File file)
        throws IOException
    {
        Object obj = FullBackup.getBackupScheme(this);
        if(!((FullBackup.BackupScheme) (obj)).isFullBackupContentEnabled())
        {
            if(Log.isLoggable("BackupXmlParserLogging", 2))
                Log.v("BackupXmlParserLogging", (new StringBuilder()).append("onRestoreFile \"").append(file.getCanonicalPath()).append("\" : fullBackupContent not enabled for ").append(getPackageName()).toString());
            return false;
        }
        String s = file.getCanonicalPath();
        Object obj1;
        try
        {
            obj1 = ((FullBackup.BackupScheme) (obj)).maybeParseAndGetCanonicalIncludePaths();
            obj = ((FullBackup.BackupScheme) (obj)).maybeParseAndGetCanonicalExcludePaths();
        }
        // Misplaced declaration of an exception variable
        catch(File file)
        {
            if(Log.isLoggable("BackupXmlParserLogging", 2))
                Log.v("BackupXmlParserLogging", (new StringBuilder()).append("onRestoreFile \"").append(s).append("\" : Exception trying to parse fullBackupContent xml file!").append(" Aborting onRestoreFile.").toString(), file);
            return false;
        }
        if(obj != null && isFileSpecifiedInPathList(file, ((Collection) (obj))))
        {
            if(Log.isLoggable("BackupXmlParserLogging", 2))
                Log.v("BackupXmlParserLogging", (new StringBuilder()).append("onRestoreFile: \"").append(s).append("\": listed in").append(" excludes; skipping.").toString());
            return false;
        }
        if(obj1 != null && ((Map) (obj1)).isEmpty() ^ true)
        {
            boolean flag = false;
            obj1 = ((Map) (obj1)).values().iterator();
            boolean flag1;
            do
            {
                flag1 = flag;
                if(!((Iterator) (obj1)).hasNext())
                    break;
                flag1 = flag | isFileSpecifiedInPathList(file, (Set)((Iterator) (obj1)).next());
                flag = flag1;
            } while(!flag1);
            if(!flag1)
            {
                if(Log.isLoggable("BackupXmlParserLogging", 2))
                    Log.v("BackupXmlParserLogging", (new StringBuilder()).append("onRestoreFile: Trying to restore \"").append(s).append("\" but it isn't specified").append(" in the included files; skipping.").toString());
                return false;
            }
        }
        return true;
    }

    private boolean isFileSpecifiedInPathList(File file, Collection collection)
        throws IOException
    {
        for(collection = collection.iterator(); collection.hasNext();)
        {
            String s = (String)collection.next();
            File file1 = new File(s);
            if(file1.isDirectory())
                if(file.isDirectory())
                    return file.equals(file1);
                else
                    return file.getCanonicalPath().startsWith(s);
            if(file.equals(file1))
                return true;
        }

        return false;
    }

    private void waitForSharedPrefs()
    {
        SharedPrefsSynchronizer sharedprefssynchronizer;
        Handler handler = getHandler();
        sharedprefssynchronizer = new SharedPrefsSynchronizer();
        handler.postAtFrontOfQueue(sharedprefssynchronizer);
        sharedprefssynchronizer.mLatch.await();
_L2:
        return;
        InterruptedException interruptedexception;
        interruptedexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void attach(Context context)
    {
        attachBaseContext(context);
    }

    public final void fullBackupFile(File file, FullBackupDataOutput fullbackupdataoutput)
    {
        String s;
        Object obj;
        s = null;
        obj = getApplicationInfo();
        String s1;
        String s2;
        String s3;
        String s4;
        String s5;
        String s6;
        String s7;
        String s8;
        String s9;
        String s10;
        String s11;
        String s12;
        String s13;
        String s14;
        Context context = createCredentialProtectedStorageContext();
        s1 = context.getDataDir().getCanonicalPath();
        s2 = context.getFilesDir().getCanonicalPath();
        s3 = context.getNoBackupFilesDir().getCanonicalPath();
        s4 = context.getDatabasePath("foo").getParentFile().getCanonicalPath();
        s5 = context.getSharedPreferencesPath("foo").getParentFile().getCanonicalPath();
        s6 = context.getCacheDir().getCanonicalPath();
        s7 = context.getCodeCacheDir().getCanonicalPath();
        context = createDeviceProtectedStorageContext();
        s8 = context.getDataDir().getCanonicalPath();
        s9 = context.getFilesDir().getCanonicalPath();
        s10 = context.getNoBackupFilesDir().getCanonicalPath();
        s11 = context.getDatabasePath("foo").getParentFile().getCanonicalPath();
        s12 = context.getSharedPreferencesPath("foo").getParentFile().getCanonicalPath();
        s13 = context.getCacheDir().getCanonicalPath();
        s14 = context.getCodeCacheDir().getCanonicalPath();
        if(((ApplicationInfo) (obj)).nativeLibraryDir != null) goto _L2; else goto _L1
_L1:
        Object obj1 = null;
_L4:
        obj = s;
        File file1;
        if(Process.myUid() == 1000)
            break MISSING_BLOCK_LABEL_225;
        file1 = getExternalFilesDir(null);
        obj = s;
        if(file1 == null)
            break MISSING_BLOCK_LABEL_225;
        obj = file1.getCanonicalPath();
        s = file.getCanonicalPath();
        if(s.startsWith(s6) || s.startsWith(s7) || s.startsWith(s3) || s.startsWith(s13) || s.startsWith(s14) || s.startsWith(s10) || s.startsWith(((String) (obj1))))
        {
            Log.w("BackupAgent", "lib, cache, code_cache, and no_backup files are not backed up");
            return;
        }
        break; /* Loop/switch isn't completed */
_L2:
        try
        {
            obj1 = JVM INSTR new #134 <Class File>;
            ((File) (obj1)).File(((ApplicationInfo) (obj)).nativeLibraryDir);
            obj1 = ((File) (obj1)).getCanonicalPath();
        }
        // Misplaced declaration of an exception variable
        catch(File file)
        {
            Log.w("BackupAgent", "Unable to obtain canonical paths");
            return;
        }
        if(true) goto _L4; else goto _L3
_L3:
        if(s.startsWith(s4))
        {
            file = "db";
            obj = s4;
        } else
        if(s.startsWith(s5))
        {
            file = "sp";
            obj = s5;
        } else
        if(s.startsWith(s2))
        {
            file = "f";
            obj = s2;
        } else
        if(s.startsWith(s1))
        {
            file = "r";
            obj = s1;
        } else
        if(s.startsWith(s11))
        {
            file = "d_db";
            obj = s11;
        } else
        if(s.startsWith(s12))
        {
            file = "d_sp";
            obj = s12;
        } else
        if(s.startsWith(s9))
        {
            file = "d_f";
            obj = s9;
        } else
        if(s.startsWith(s8))
        {
            file = "d_r";
            obj = s8;
        } else
        if(obj != null && s.startsWith(((String) (obj))))
        {
            file = "ef";
        } else
        {
            Log.w("BackupAgent", (new StringBuilder()).append("File ").append(s).append(" is in an unsupported location; skipping").toString());
            return;
        }
        FullBackup.backupToTar(getPackageName(), file, null, ((String) (obj)), s, fullbackupdataoutput);
        return;
    }

    protected final void fullBackupFileTree(String s, String s1, String s2, ArraySet arrayset, ArraySet arrayset1, FullBackupDataOutput fullbackupdataoutput)
    {
        String s3;
        File file;
        s3 = FullBackup.getBackupScheme(this).tokenToDirectoryPath(s1);
        if(s3 == null)
            return;
        file = new File(s2);
        if(!file.exists()) goto _L2; else goto _L1
_L1:
        s2 = new LinkedList();
        s2.add(file);
_L7:
        Object obj;
        if(s2.size() <= 0)
            break; /* Loop/switch isn't completed */
        File file1 = (File)s2.remove(0);
        StructStat structstat;
        File afile[];
        int i;
        int j;
        try
        {
            structstat = Os.lstat(file1.getPath());
            if(!OsConstants.S_ISREG(structstat.st_mode) && OsConstants.S_ISDIR(structstat.st_mode) ^ true)
                continue; /* Loop/switch isn't completed */
            obj = file1.getCanonicalPath();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            if(Log.isLoggable("BackupXmlParserLogging", 2))
                Log.v("BackupXmlParserLogging", (new StringBuilder()).append("Error canonicalizing path of ").append(file1).toString());
            continue; /* Loop/switch isn't completed */
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            if(Log.isLoggable("BackupXmlParserLogging", 2))
                Log.v("BackupXmlParserLogging", (new StringBuilder()).append("Error scanning file ").append(file1).append(" : ").append(obj).toString());
            continue; /* Loop/switch isn't completed */
        }
        if(arrayset == null)
            break MISSING_BLOCK_LABEL_122;
        if(arrayset.contains(obj))
            continue; /* Loop/switch isn't completed */
        if(arrayset1 == null)
            break MISSING_BLOCK_LABEL_137;
        if(arrayset1.contains(obj))
            continue; /* Loop/switch isn't completed */
        if(!OsConstants.S_ISDIR(structstat.st_mode)) goto _L4; else goto _L3
_L3:
        afile = file1.listFiles();
        if(afile == null) goto _L4; else goto _L5
_L5:
        i = 0;
        j = afile.length;
_L6:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        s2.add(0, afile[i]);
        i++;
        if(true) goto _L6; else goto _L4
_L4:
        FullBackup.backupToTar(s, s1, null, s3, ((String) (obj)), fullbackupdataoutput);
        if(true) goto _L7; else goto _L2
_L2:
    }

    Handler getHandler()
    {
        if(mHandler == null)
            mHandler = new Handler(Looper.getMainLooper());
        return mHandler;
    }

    public abstract void onBackup(ParcelFileDescriptor parcelfiledescriptor, BackupDataOutput backupdataoutput, ParcelFileDescriptor parcelfiledescriptor1)
        throws IOException;

    public final IBinder onBind()
    {
        return mBinder;
    }

    public void onCreate()
    {
    }

    public void onDestroy()
    {
    }

    public void onFullBackup(FullBackupDataOutput fullbackupdataoutput)
        throws IOException
    {
        Object obj;
        obj = FullBackup.getBackupScheme(this);
        if(!((FullBackup.BackupScheme) (obj)).isFullBackupContentEnabled())
            return;
        Map map;
        ArraySet arrayset;
        String s;
        Object obj1;
        String s1;
        String s2;
        String s3;
        String s4;
        String s5;
        String s6;
        String s7;
        Object obj2;
        String s8;
        String s9;
        String s10;
        String s11;
        String s12;
        ArraySet arrayset1;
        try
        {
            map = ((FullBackup.BackupScheme) (obj)).maybeParseAndGetCanonicalIncludePaths();
            arrayset = ((FullBackup.BackupScheme) (obj)).maybeParseAndGetCanonicalExcludePaths();
        }
        // Misplaced declaration of an exception variable
        catch(FullBackupDataOutput fullbackupdataoutput)
        {
            if(Log.isLoggable("BackupXmlParserLogging", 2))
                Log.v("BackupXmlParserLogging", "Exception trying to parse fullBackupContent xml file! Aborting full backup.", fullbackupdataoutput);
            return;
        }
        s = getPackageName();
        obj = getApplicationInfo();
        obj1 = createCredentialProtectedStorageContext();
        s1 = ((Context) (obj1)).getDataDir().getCanonicalPath();
        s2 = ((Context) (obj1)).getFilesDir().getCanonicalPath();
        s3 = ((Context) (obj1)).getNoBackupFilesDir().getCanonicalPath();
        s4 = ((Context) (obj1)).getDatabasePath("foo").getParentFile().getCanonicalPath();
        s5 = ((Context) (obj1)).getSharedPreferencesPath("foo").getParentFile().getCanonicalPath();
        s6 = ((Context) (obj1)).getCacheDir().getCanonicalPath();
        s7 = ((Context) (obj1)).getCodeCacheDir().getCanonicalPath();
        obj2 = createDeviceProtectedStorageContext();
        s8 = ((Context) (obj2)).getDataDir().getCanonicalPath();
        s9 = ((Context) (obj2)).getFilesDir().getCanonicalPath();
        obj1 = ((Context) (obj2)).getNoBackupFilesDir().getCanonicalPath();
        s10 = ((Context) (obj2)).getDatabasePath("foo").getParentFile().getCanonicalPath();
        s11 = ((Context) (obj2)).getSharedPreferencesPath("foo").getParentFile().getCanonicalPath();
        s12 = ((Context) (obj2)).getCacheDir().getCanonicalPath();
        obj2 = ((Context) (obj2)).getCodeCacheDir().getCanonicalPath();
        if(((ApplicationInfo) (obj)).nativeLibraryDir != null)
            obj = (new File(((ApplicationInfo) (obj)).nativeLibraryDir)).getCanonicalPath();
        else
            obj = null;
        arrayset1 = new ArraySet();
        arrayset1.add(s2);
        arrayset1.add(s3);
        arrayset1.add(s4);
        arrayset1.add(s5);
        arrayset1.add(s6);
        arrayset1.add(s7);
        arrayset1.add(s9);
        arrayset1.add(obj1);
        arrayset1.add(s10);
        arrayset1.add(s11);
        arrayset1.add(s12);
        arrayset1.add(obj2);
        if(obj != null)
            arrayset1.add(obj);
        applyXmlFiltersAndDoFullBackupForDomain(s, "r", map, arrayset, arrayset1, fullbackupdataoutput);
        arrayset1.add(s1);
        applyXmlFiltersAndDoFullBackupForDomain(s, "d_r", map, arrayset, arrayset1, fullbackupdataoutput);
        arrayset1.add(s8);
        arrayset1.remove(s2);
        applyXmlFiltersAndDoFullBackupForDomain(s, "f", map, arrayset, arrayset1, fullbackupdataoutput);
        arrayset1.add(s2);
        arrayset1.remove(s9);
        applyXmlFiltersAndDoFullBackupForDomain(s, "d_f", map, arrayset, arrayset1, fullbackupdataoutput);
        arrayset1.add(s9);
        arrayset1.remove(s4);
        applyXmlFiltersAndDoFullBackupForDomain(s, "db", map, arrayset, arrayset1, fullbackupdataoutput);
        arrayset1.add(s4);
        arrayset1.remove(s10);
        applyXmlFiltersAndDoFullBackupForDomain(s, "d_db", map, arrayset, arrayset1, fullbackupdataoutput);
        arrayset1.add(s10);
        arrayset1.remove(s5);
        applyXmlFiltersAndDoFullBackupForDomain(s, "sp", map, arrayset, arrayset1, fullbackupdataoutput);
        arrayset1.add(s5);
        arrayset1.remove(s11);
        applyXmlFiltersAndDoFullBackupForDomain(s, "d_sp", map, arrayset, arrayset1, fullbackupdataoutput);
        arrayset1.add(s11);
        if(Process.myUid() != 1000 && getExternalFilesDir(null) != null)
            applyXmlFiltersAndDoFullBackupForDomain(s, "ef", map, arrayset, arrayset1, fullbackupdataoutput);
        return;
    }

    public void onQuotaExceeded(long l, long l1)
    {
    }

    public abstract void onRestore(BackupDataInput backupdatainput, int i, ParcelFileDescriptor parcelfiledescriptor)
        throws IOException;

    protected void onRestoreFile(ParcelFileDescriptor parcelfiledescriptor, long l, int i, String s, String s1, long l1, long l2)
        throws IOException
    {
        String s2 = FullBackup.getBackupScheme(this).tokenToDirectoryPath(s);
        if(s.equals("ef"))
            l1 = -1L;
        if(s2 != null)
        {
            s = new File(s2, s1);
            if(s.getCanonicalPath().startsWith((new StringBuilder()).append(s2).append(File.separatorChar).toString()))
            {
                onRestoreFile(parcelfiledescriptor, l, ((File) (s)), i, l1, l2);
                return;
            }
        }
        FullBackup.restoreFile(parcelfiledescriptor, l, i, l1, l2, null);
    }

    public void onRestoreFile(ParcelFileDescriptor parcelfiledescriptor, long l, File file, int i, long l1, 
            long l2)
        throws IOException
    {
        if(!isFileEligibleForRestore(file))
            file = null;
        FullBackup.restoreFile(parcelfiledescriptor, l, i, l1, l2, file);
    }

    public void onRestoreFinished()
    {
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "BackupAgent";
    public static final int TYPE_DIRECTORY = 2;
    public static final int TYPE_EOF = 0;
    public static final int TYPE_FILE = 1;
    public static final int TYPE_SYMLINK = 3;
    private final IBinder mBinder = (new BackupServiceBinder(null)).asBinder();
    Handler mHandler;
}
