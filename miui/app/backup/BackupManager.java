// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.app.backup;

import android.content.Context;
import android.content.pm.*;
import android.os.*;
import android.util.Log;
import java.io.*;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package miui.app.backup:
//            IBackupManager, IBackupServiceStateObserver

public class BackupManager
{
    public static interface BackupRestoreListener
    {

        public abstract void onBackupDataTransaction(String s, int i, ParcelFileDescriptor parcelfiledescriptor);

        public abstract void onBackupEnd(String s, int i);

        public abstract void onBackupStart(String s, int i);

        public abstract void onCustomProgressChange(String s, int i, int j, long l, long l1);

        public abstract void onError(String s, int i, int j);

        public abstract void onRestoreEnd(String s, int i);

        public abstract void onRestoreProgress(String s, int i, long l);

        public abstract void onRestoreStart(String s, int i);
    }

    private class FullBackupRestoreObserver extends IPackageBackupRestoreObserver.Stub
    {

        public void onBackupEnd(String s, int i)
            throws RemoteException
        {
            Object obj;
            boolean flag;
            InterruptedException interruptedexception;
            try
            {
                BackupManager._2D_get2(BackupManager.this)[1].close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                ((IOException) (obj)).printStackTrace();
            }
            if(BackupManager._2D_get3(BackupManager.this) == null) goto _L2; else goto _L1
_L1:
            obj = BackupManager._2D_get3(BackupManager.this);
            obj;
            JVM INSTR monitorenter ;
_L3:
            flag = BackupManager._2D_get3(BackupManager.this).get();
            if(flag)
                break MISSING_BLOCK_LABEL_85;
            BackupManager._2D_get3(BackupManager.this).wait();
              goto _L3
            interruptedexception;
            interruptedexception.printStackTrace();
              goto _L3
            s;
            throw s;
            obj;
            JVM INSTR monitorexit ;
_L2:
            if(BackupManager._2D_get1(BackupManager.this) != null)
                BackupManager._2D_get1(BackupManager.this).onBackupEnd(s, i);
            obj = BackupManager._2D_get0(BackupManager.this);
            obj;
            JVM INSTR monitorenter ;
            BackupManager._2D_get0(BackupManager.this).notifyAll();
            obj;
            JVM INSTR monitorexit ;
_L4:
            return;
            s;
            obj;
            JVM INSTR monitorexit ;
            throw s;
            s;
            Log.e("Backup:BackupManager", "", s);
              goto _L4
        }

        public void onBackupStart(final String pkg, int i)
            throws RemoteException
        {
            BackupManager._2D_set0(BackupManager.this, new AtomicBoolean(false));
            if(BackupManager._2D_get1(BackupManager.this) != null)
            {
                BackupManager._2D_get1(BackupManager.this).onBackupStart(pkg, i);
                (i. new Thread() {

                    public void run()
                    {
                        AtomicBoolean atomicboolean = BackupManager._2D_get3(_fld0);
                        atomicboolean;
                        JVM INSTR monitorenter ;
                        BackupManager._2D_get1(_fld0).onBackupDataTransaction(pkg, feature, BackupManager._2D_get2(_fld0)[0]);
                        BackupManager._2D_get3(_fld0).set(true);
                        BackupManager._2D_get3(_fld0).notifyAll();
                        atomicboolean;
                        JVM INSTR monitorexit ;
                        BackupManager._2D_get2(_fld0)[0].close();
_L1:
                        return;
                        Object obj;
                        obj;
                        atomicboolean;
                        JVM INSTR monitorexit ;
                        throw obj;
                        obj;
                        try
                        {
                            BackupManager._2D_get2(_fld0)[0].close();
                        }
                        catch(IOException ioexception)
                        {
                            ioexception.printStackTrace();
                        }
                        throw obj;
                        obj;
                        ((IOException) (obj)).printStackTrace();
                          goto _L1
                    }

                    final FullBackupRestoreObserver this$1;
                    final int val$feature;
                    final String val$pkg;

            
            {
                this$1 = final_fullbackuprestoreobserver;
                pkg = s;
                feature = I.this;
                super();
            }
                }
).start();
            }
        }

        public void onCustomProgressChange(String s, int i, int j, long l, long l1)
        {
            if(BackupManager._2D_get1(BackupManager.this) != null)
                BackupManager._2D_get1(BackupManager.this).onCustomProgressChange(s, i, j, l, l1);
        }

        public void onError(String s, int i, int j)
            throws RemoteException
        {
            if(BackupManager._2D_get1(BackupManager.this) != null)
                BackupManager._2D_get1(BackupManager.this).onError(s, i, j);
        }

        public void onRestoreEnd(String s, int i)
            throws RemoteException
        {
            if(BackupManager._2D_get1(BackupManager.this) != null)
                BackupManager._2D_get1(BackupManager.this).onRestoreEnd(s, i);
            Object obj = BackupManager._2D_get0(BackupManager.this);
            obj;
            JVM INSTR monitorenter ;
            BackupManager._2D_get0(BackupManager.this).notifyAll();
            obj;
            JVM INSTR monitorexit ;
_L1:
            return;
            s;
            obj;
            JVM INSTR monitorexit ;
            throw s;
            s;
            Log.e("Backup:BackupManager", "", s);
              goto _L1
        }

        public void onRestoreError(String s, int i, int j)
            throws RemoteException
        {
        }

        public void onRestoreStart(String s, int i)
            throws RemoteException
        {
            if(BackupManager._2D_get1(BackupManager.this) != null)
                BackupManager._2D_get1(BackupManager.this).onRestoreStart(s, i);
        }

        final BackupManager this$0;

        private FullBackupRestoreObserver()
        {
            this$0 = BackupManager.this;
            super();
        }

        FullBackupRestoreObserver(FullBackupRestoreObserver fullbackuprestoreobserver)
        {
            this();
        }
    }


    static Object _2D_get0(BackupManager backupmanager)
    {
        return backupmanager.mBackupRestoreLatch;
    }

    static BackupRestoreListener _2D_get1(BackupManager backupmanager)
    {
        return backupmanager.mBackupRestoreListener;
    }

    static ParcelFileDescriptor[] _2D_get2(BackupManager backupmanager)
    {
        return backupmanager.mPips;
    }

    static AtomicBoolean _2D_get3(BackupManager backupmanager)
    {
        return backupmanager.mTransactionLatch;
    }

    static AtomicBoolean _2D_set0(BackupManager backupmanager, AtomicBoolean atomicboolean)
    {
        backupmanager.mTransactionLatch = atomicboolean;
        return atomicboolean;
    }

    private BackupManager(Context context)
    {
        mTransactionLatch = null;
        mBackupRestoreLatch = new Object();
        mICaller = new Binder();
        mContext = context;
        mService = IBackupManager.Stub.asInterface(ServiceManager.getService("MiuiBackup"));
    }

    public static final BackupManager getBackupManager(Context context)
    {
        BackupManager backupmanager;
        BackupManager backupmanager1;
        if(sWRInstance == null)
            backupmanager = null;
        else
            backupmanager = (BackupManager)sWRInstance.get();
        backupmanager1 = backupmanager;
        if(backupmanager == null)
        {
            backupmanager1 = new BackupManager(context.getApplicationContext());
            sWRInstance = new WeakReference(backupmanager1);
        }
        return backupmanager1;
    }

    public static boolean isProgRecordApp(String s, int i)
    {
        return sProgRecordAppSet.contains(s);
    }

    public static boolean isSysAppForBackup(Context context, String s)
    {
        boolean flag;
        flag = false;
        context = context.getPackageManager();
        boolean flag1 = isSysAppForBackup(context.getPackageInfo(s, 64));
        flag = flag1;
_L2:
        return flag;
        context;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static boolean isSysAppForBackup(PackageInfo packageinfo)
    {
        boolean flag1;
label0:
        {
            boolean flag = false;
            flag1 = flag;
            if(packageinfo == null)
                break label0;
            flag1 = flag;
            if(packageinfo.applicationInfo == null)
                break label0;
            if((packageinfo.applicationInfo.flags & 1) == 0)
            {
                flag1 = flag;
                if(!mSystemAppWhiteSet.contains(packageinfo.applicationInfo.packageName))
                    break label0;
            }
            flag1 = true;
        }
        return flag1;
    }

    public boolean acquire(IBackupServiceStateObserver ibackupservicestateobserver)
    {
        try
        {
            if(!mIsAcquired)
                mIsAcquired = mService.acquire(ibackupservicestateobserver, mICaller);
        }
        // Misplaced declaration of an exception variable
        catch(IBackupServiceStateObserver ibackupservicestateobserver)
        {
            ibackupservicestateobserver.printStackTrace();
        }
        return mIsAcquired;
    }

    public void backupPackage(String s, int i, String s1, String s2, boolean flag, boolean flag1, BackupRestoreListener backuprestorelistener)
    {
        backupPackage(s, i, s1, s2, flag, flag1, false, backuprestorelistener);
    }

    public void backupPackage(String s, int i, String s1, String s2, boolean flag, boolean flag1, boolean flag2, 
            BackupRestoreListener backuprestorelistener)
    {
        if(!mIsAcquired)
            throw new RuntimeException("You must acquire first to use the backup or restore service");
        mBackupRestoreListener = backuprestorelistener;
        if(mService == null)
            break MISSING_BLOCK_LABEL_157;
        mPips = ParcelFileDescriptor.createPipe();
        backuprestorelistener = ((BackupRestoreListener) (mBackupRestoreLatch));
        backuprestorelistener;
        JVM INSTR monitorenter ;
        IBackupManager ibackupmanager = mService;
        ParcelFileDescriptor parcelfiledescriptor = mPips[1];
        ParcelFileDescriptor parcelfiledescriptor1 = mPips[0];
        FullBackupRestoreObserver fullbackuprestoreobserver = JVM INSTR new #11  <Class BackupManager$FullBackupRestoreObserver>;
        fullbackuprestoreobserver.this. FullBackupRestoreObserver(null);
        ibackupmanager.backupPackage(parcelfiledescriptor, parcelfiledescriptor1, s, i, s1, s2, flag, flag1, flag2, fullbackuprestoreobserver);
        mBackupRestoreLatch.wait();
        backuprestorelistener;
        JVM INSTR monitorexit ;
        if(mPips == null)
            break MISSING_BLOCK_LABEL_157;
        if(mPips[0] != null)
            mPips[0].close();
        if(mPips[1] != null)
            mPips[1].close();
_L1:
        return;
        s;
        backuprestorelistener;
        JVM INSTR monitorexit ;
        throw s;
        s;
        Log.e("Backup:BackupManager", "create pipe failed", s);
        if(mPips != null)
            try
            {
                if(mPips[0] != null)
                    mPips[0].close();
                if(mPips[1] != null)
                    mPips[1].close();
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                Log.e("Backup:BackupManager", "IOException", s);
            }
          goto _L1
        s;
        Log.e("Backup:BackupManager", "IOException", s);
          goto _L1
        s;
        Log.e("Backup:BackupManager", "InterruptedException", s);
        if(mPips != null)
            try
            {
                if(mPips[0] != null)
                    mPips[0].close();
                if(mPips[1] != null)
                    mPips[1].close();
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                Log.e("Backup:BackupManager", "IOException", s);
            }
          goto _L1
        s;
        Log.e("Backup:BackupManager", "Remove invoking failed", s);
        if(mPips != null)
            try
            {
                if(mPips[0] != null)
                    mPips[0].close();
                if(mPips[1] != null)
                    mPips[1].close();
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                Log.e("Backup:BackupManager", "IOException", s);
            }
          goto _L1
        s1;
        if(mPips != null)
            try
            {
                if(mPips[0] != null)
                    mPips[0].close();
                if(mPips[1] != null)
                    mPips[1].close();
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                Log.e("Backup:BackupManager", "IOException", s);
            }
        throw s1;
    }

    public String getCurrentRunningPackage()
    {
        String s;
        try
        {
            s = mService.getCurrentRunningPackage();
        }
        catch(RemoteException remoteexception)
        {
            Log.e("Backup:BackupManager", "RemoteException", remoteexception);
            return null;
        }
        return s;
    }

    public int getCurrentWorkingFeature()
    {
        int i;
        try
        {
            i = mService.getCurrentWorkingFeature();
        }
        catch(RemoteException remoteexception)
        {
            Log.e("Backup:BackupManager", "RemoteException", remoteexception);
            return -1;
        }
        return i;
    }

    public int getState()
    {
        int i;
        try
        {
            i = mService.getState();
        }
        catch(RemoteException remoteexception)
        {
            Log.e("Backup:BackupManager", "RemoteException", remoteexception);
            return -1;
        }
        return i;
    }

    public boolean isServiceIdle()
    {
        boolean flag = false;
        boolean flag1 = mService.isServiceIdle();
        flag = flag1;
_L2:
        return flag;
        RemoteException remoteexception;
        remoteexception;
        remoteexception.printStackTrace();
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void release(IBackupServiceStateObserver ibackupservicestateobserver)
    {
        try
        {
            mService.release(ibackupservicestateobserver);
        }
        // Misplaced declaration of an exception variable
        catch(IBackupServiceStateObserver ibackupservicestateobserver)
        {
            ibackupservicestateobserver.printStackTrace();
        }
        mIsAcquired = false;
    }

    public void restoreFile(ParcelFileDescriptor parcelfiledescriptor, String s, boolean flag, BackupRestoreListener backuprestorelistener)
    {
        ParcelFileDescriptor aparcelfiledescriptor[];
        Object obj;
        Object obj1;
        Object obj2;
        ParcelFileDescriptor aparcelfiledescriptor1[];
        ParcelFileDescriptor aparcelfiledescriptor2[];
        Object obj3;
        ParcelFileDescriptor aparcelfiledescriptor3[];
        if(!mIsAcquired)
            throw new RuntimeException("You must acquire first to use the backup or restore service");
        mBackupRestoreListener = backuprestorelistener;
        aparcelfiledescriptor = null;
        obj = null;
        obj1 = null;
        obj2 = null;
        aparcelfiledescriptor1 = null;
        aparcelfiledescriptor2 = aparcelfiledescriptor;
        obj3 = obj;
        aparcelfiledescriptor3 = obj1;
        backuprestorelistener = obj2;
        Object obj4 = mBackupRestoreLatch;
        aparcelfiledescriptor2 = aparcelfiledescriptor;
        obj3 = obj;
        aparcelfiledescriptor3 = obj1;
        backuprestorelistener = obj2;
        obj4;
        JVM INSTR monitorenter ;
        aparcelfiledescriptor = ParcelFileDescriptor.createPipe();
        backuprestorelistener = aparcelfiledescriptor[1];
        aparcelfiledescriptor1 = aparcelfiledescriptor;
        obj3 = JVM INSTR new #6   <Class BackupManager$1>;
        aparcelfiledescriptor1 = aparcelfiledescriptor;
        ((_cls1) (obj3)).this. _cls1();
        aparcelfiledescriptor1 = aparcelfiledescriptor;
        ((_cls1) (obj3)).start();
        aparcelfiledescriptor1 = aparcelfiledescriptor;
        parcelfiledescriptor = mService;
        backuprestorelistener = aparcelfiledescriptor[0];
        aparcelfiledescriptor1 = aparcelfiledescriptor;
        obj3 = JVM INSTR new #11  <Class BackupManager$FullBackupRestoreObserver>;
        aparcelfiledescriptor1 = aparcelfiledescriptor;
        ((FullBackupRestoreObserver) (obj3)).this. FullBackupRestoreObserver(null);
        aparcelfiledescriptor1 = aparcelfiledescriptor;
        parcelfiledescriptor.restoreFile(backuprestorelistener, s, flag, ((IPackageBackupRestoreObserver) (obj3)));
        aparcelfiledescriptor1 = aparcelfiledescriptor;
        mBackupRestoreLatch.wait();
        aparcelfiledescriptor2 = aparcelfiledescriptor;
        obj3 = aparcelfiledescriptor;
        aparcelfiledescriptor3 = aparcelfiledescriptor;
        backuprestorelistener = aparcelfiledescriptor;
        obj4;
        JVM INSTR monitorexit ;
        if(aparcelfiledescriptor == null || aparcelfiledescriptor[0] == null)
            break MISSING_BLOCK_LABEL_221;
        aparcelfiledescriptor[0].close();
_L1:
        return;
        parcelfiledescriptor;
        aparcelfiledescriptor2 = aparcelfiledescriptor1;
        obj3 = aparcelfiledescriptor1;
        aparcelfiledescriptor3 = aparcelfiledescriptor1;
        backuprestorelistener = aparcelfiledescriptor1;
        obj4;
        JVM INSTR monitorexit ;
        aparcelfiledescriptor2 = aparcelfiledescriptor1;
        obj3 = aparcelfiledescriptor1;
        aparcelfiledescriptor3 = aparcelfiledescriptor1;
        backuprestorelistener = aparcelfiledescriptor1;
        throw parcelfiledescriptor;
        parcelfiledescriptor;
        backuprestorelistener = aparcelfiledescriptor2;
        Log.e("Backup:BackupManager", "RemoteException", parcelfiledescriptor);
        if(aparcelfiledescriptor2 != null && aparcelfiledescriptor2[0] != null)
            try
            {
                aparcelfiledescriptor2[0].close();
            }
            // Misplaced declaration of an exception variable
            catch(ParcelFileDescriptor parcelfiledescriptor)
            {
                Log.e("Backup:BackupManager", "IOException", parcelfiledescriptor);
            }
          goto _L1
        parcelfiledescriptor;
        Log.e("Backup:BackupManager", "IOException", parcelfiledescriptor);
          goto _L1
        parcelfiledescriptor;
        backuprestorelistener = ((BackupRestoreListener) (obj3));
        Log.e("Backup:BackupManager", "IOException", parcelfiledescriptor);
        if(obj3 != null && obj3[0] != null)
            try
            {
                obj3[0].close();
            }
            // Misplaced declaration of an exception variable
            catch(ParcelFileDescriptor parcelfiledescriptor)
            {
                Log.e("Backup:BackupManager", "IOException", parcelfiledescriptor);
            }
          goto _L1
        parcelfiledescriptor;
        backuprestorelistener = aparcelfiledescriptor3;
        Log.e("Backup:BackupManager", "InterruptedException", parcelfiledescriptor);
        if(aparcelfiledescriptor3 != null && aparcelfiledescriptor3[0] != null)
            try
            {
                aparcelfiledescriptor3[0].close();
            }
            // Misplaced declaration of an exception variable
            catch(ParcelFileDescriptor parcelfiledescriptor)
            {
                Log.e("Backup:BackupManager", "IOException", parcelfiledescriptor);
            }
          goto _L1
        parcelfiledescriptor;
        if(backuprestorelistener != null && backuprestorelistener[0] != null)
            try
            {
                backuprestorelistener[0].close();
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                Log.e("Backup:BackupManager", "IOException", s);
            }
        throw parcelfiledescriptor;
    }

    public void setCustomProgress(int i, int j, int k)
    {
        String s = mService.getCurrentRunningPackage();
        if(mContext.getPackageName().equals(s))
            mService.setCustomProgress(i, j, k);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        remoteexception.printStackTrace();
          goto _L1
    }

    public void setIsNeedBeKilled(boolean flag)
    {
        mService.setIsNeedBeKilled(mContext.getPackageName(), flag);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("Backup:BackupManager", "RemoteException", remoteexception);
          goto _L1
    }

    void setWorkingError(int i)
    {
        mService.errorOccur(i);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        remoteexception.printStackTrace();
          goto _L1
    }

    public static final String BACKUP_FILE_HEADER_MAGIC = "MIUI BACKUP\n";
    public static final int BACKUP_FILE_VERSION = 2;
    public static String DOMAIN_ATTACH = "miui_att";
    public static String DOMAIN_BAK = "miui_bak";
    public static String DOMAIN_END = "miui_end";
    public static String DOMAIN_META = "miui_meta";
    public static final int ERR_AUTHENTICATION_FAILED = 3;
    public static final int ERR_BAKFILE_BROKEN = 6;
    public static final int ERR_BINDER_DIED = 8;
    public static final int ERR_IO_PERMISSION = 7;
    public static final int ERR_NONE = 0;
    public static final int ERR_NOT_ALLOW = 9;
    public static final int ERR_NO_BACKUPAGENT = 2;
    public static final int ERR_UNKNOWN = 1;
    public static final int ERR_VERSION_TOO_OLD = 5;
    public static final int ERR_VERSION_UNSUPPORTED = 4;
    public static final int FEATURE_FULL_BACKUP = -1;
    public static final int PROG_TYPE_NORMAL = 0;
    public static final int PROG_TYPE_RECORD = 1;
    public static final String SERVICE_NAME = "MiuiBackup";
    public static final int STATE_BACKUP = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_RESTORE = 2;
    private static final String TAG = "Backup:BackupManager";
    public static final int TYPE_NORMAL_BACKUP_TIMEOUT_SCALE = 1;
    public static final int TYPE_RECORD_BACKUP_TIMEOUT_SCALE = 6;
    private static HashSet mSystemAppWhiteSet;
    private static HashSet sProgRecordAppSet;
    private static WeakReference sWRInstance;
    private Object mBackupRestoreLatch;
    private BackupRestoreListener mBackupRestoreListener;
    private Context mContext;
    private IBinder mICaller;
    private boolean mIsAcquired;
    private ParcelFileDescriptor mPips[];
    private IBackupManager mService;
    private AtomicBoolean mTransactionLatch;

    static 
    {
        mSystemAppWhiteSet = new HashSet();
        mSystemAppWhiteSet.add("com.android.browser");
        mSystemAppWhiteSet.add("com.miui.weather2");
        mSystemAppWhiteSet.add("com.miui.notes");
        mSystemAppWhiteSet.add("com.android.email");
        sProgRecordAppSet = new HashSet();
        sProgRecordAppSet.add("com.android.contacts");
        sProgRecordAppSet.add("com.android.mms");
    }

    // Unreferenced inner class miui/app/backup/BackupManager$1

/* anonymous class */
    class _cls1 extends Thread
    {

        public void run()
        {
            Object obj;
            Object obj2;
            Object obj3;
            Object obj4;
            Object obj5;
            Object obj6;
            byte abyte0[];
            obj = null;
            obj2 = null;
            obj3 = null;
            obj4 = null;
            obj5 = null;
            obj6 = obj;
            abyte0 = obj3;
            Object obj1;
            Object obj7;
            int i;
            try
            {
                obj7 = JVM INSTR new #31  <Class FileInputStream>;
            }
            // Misplaced declaration of an exception variable
            catch(Object obj1)
            {
                obj7 = obj2;
                continue; /* Loop/switch isn't completed */
            }
            obj6 = obj;
            abyte0 = obj3;
            ((FileInputStream) (obj7)).FileInputStream(fd.getFileDescriptor());
            obj6 = JVM INSTR new #42  <Class FileOutputStream>;
            ((FileOutputStream) (obj6)).FileOutputStream(writeSide.getFileDescriptor());
            abyte0 = new byte[1024];
_L3:
            i = ((InputStream) (obj7)).read(abyte0);
            if(i <= 0) goto _L2; else goto _L1
_L1:
            ((OutputStream) (obj6)).write(abyte0, 0, i);
              goto _L3
            obj1;
            obj5 = obj6;
_L10:
            obj6 = obj7;
            abyte0 = ((byte []) (obj5));
            Log.e("Backup:BackupManager", "IOException", ((Throwable) (obj1)));
            if(obj7 == null)
                break MISSING_BLOCK_LABEL_124;
            ((InputStream) (obj7)).close();
            if(obj5 == null)
                break MISSING_BLOCK_LABEL_134;
            ((OutputStream) (obj5)).close();
            if(writeSide != null)
                writeSide.close();
_L4:
            return;
_L2:
            if(obj7 == null)
                break MISSING_BLOCK_LABEL_159;
            ((InputStream) (obj7)).close();
            if(obj6 == null)
                break MISSING_BLOCK_LABEL_169;
            ((OutputStream) (obj6)).close();
            if(writeSide != null)
                writeSide.close();
              goto _L4
            obj7;
            Log.e("Backup:BackupManager", "IOException", ((Throwable) (obj7)));
              goto _L4
            obj7;
            Log.e("Backup:BackupManager", "IOException", ((Throwable) (obj7)));
              goto _L4
            obj7;
_L8:
            if(obj6 == null)
                break MISSING_BLOCK_LABEL_228;
            ((InputStream) (obj6)).close();
            if(abyte0 == null)
                break MISSING_BLOCK_LABEL_238;
            abyte0.close();
            if(writeSide != null)
                writeSide.close();
_L6:
            throw obj7;
            obj6;
            Log.e("Backup:BackupManager", "IOException", ((Throwable) (obj6)));
            if(true) goto _L6; else goto _L5
_L5:
            obj1;
            obj6 = obj7;
            abyte0 = obj4;
            obj7 = obj1;
            continue; /* Loop/switch isn't completed */
            obj1;
            abyte0 = ((byte []) (obj6));
            obj6 = obj7;
            obj7 = obj1;
            if(true) goto _L8; else goto _L7
            obj1;
            if(true) goto _L10; else goto _L9
_L9:
        }

        final BackupManager this$0;
        final ParcelFileDescriptor val$fd;
        final ParcelFileDescriptor val$writeSide;

            
            {
                this$0 = BackupManager.this;
                writeSide = parcelfiledescriptor;
                fd = parcelfiledescriptor1;
                super();
            }
    }

}
