// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.app.backup;

import android.app.backup.*;
import android.content.ContentResolver;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.FileUtils;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.util.Pair;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import miui.os.Build;

// Referenced classes of package miui.app.backup:
//            BackupMeta, BackupManager, FullBackupProxy

public class FullBackupAgent extends BackupAgent
{

    public FullBackupAgent()
    {
    }

    private String getFileName(String s)
    {
        int i = s.lastIndexOf(File.separator);
        if(i >= 0)
            s = s.substring(i + 1, s.length());
        return s;
    }

    public void addAttachedFile(Uri uri, String s)
    {
        if(mAttachedFiles == null)
            mAttachedFiles = new ArrayList();
        mAttachedFiles.add(new Pair(uri, s));
    }

    public void addAttachedFile(String s)
    {
        if(mAttachedFiles == null)
            mAttachedFiles = new ArrayList();
        mAttachedFiles.add(s);
    }

    protected boolean checkVersion(int i)
    {
        boolean flag;
        if(mBackupMeta.version > getVersion(i))
            flag = false;
        else
            flag = true;
        return flag;
    }

    protected int getVersion(int i)
    {
        return 0;
    }

    protected int onAttachRestore(BackupMeta backupmeta, ParcelFileDescriptor parcelfiledescriptor, String s)
        throws IOException
    {
        return 0;
    }

    public void onBackup(ParcelFileDescriptor parcelfiledescriptor, BackupDataOutput backupdataoutput, ParcelFileDescriptor parcelfiledescriptor1)
        throws IOException
    {
    }

    protected int onDataRestore(BackupMeta backupmeta, ParcelFileDescriptor parcelfiledescriptor)
        throws IOException
    {
        return 0;
    }

    protected int onFullBackup(ParcelFileDescriptor parcelfiledescriptor, int i)
        throws IOException
    {
        return 0;
    }

    public final void onFullBackup(FullBackupDataOutput fullbackupdataoutput)
        throws IOException
    {
        String s2;
        int j;
        if(mBackupManager == null)
            mBackupManager = BackupManager.getBackupManager(this);
        Object obj = getPackageManager();
        int i = 0;
        String s = "";
        String s1 = Build.DEVICE;
        s2 = getPackageName();
        String s3 = android.os.Build.VERSION.INCREMENTAL;
        j = mBackupManager.getCurrentWorkingFeature();
        int k = getVersion(j);
        int l = i;
        try
        {
            obj = ((PackageManager) (obj)).getPackageInfo(getPackageName(), 0);
        }
        catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            Log.e("Backup:FullBackupAgent", "NameNotFoundException", namenotfoundexception);
            continue; /* Loop/switch isn't completed */
        }
        l = i;
        i = ((PackageInfo) (obj)).versionCode;
        l = i;
        obj = ((PackageInfo) (obj)).versionName;
        s = ((String) (obj));
        l = i;
_L6:
        s = s.replaceAll("\r", "").replaceAll("\n", "");
        obj = s1.replaceAll("\r", "").replaceAll("\n", "");
        s3 = s3.replaceAll("\r", "").replaceAll("\n", "");
        mBackupMeta = new BackupMeta();
        mBackupMeta.appVersionCode = l;
        mBackupMeta.appVersionName = s;
        mBackupMeta.deviceName = ((String) (obj));
        mBackupMeta.packageName = getPackageName();
        mBackupMeta.miuiVersion = s3;
        mBackupMeta.feature = j;
        mBackupMeta.createTime = System.currentTimeMillis();
        mBackupMeta.version = k;
        mBackupMeta.writeToTar(this, fullbackupdataoutput);
        if(k != 0) goto _L2; else goto _L1
_L1:
        super.onFullBackup(fullbackupdataoutput);
_L4:
        return;
_L2:
        ParcelFileDescriptor parcelfiledescriptor;
        ParcelFileDescriptor parcelfiledescriptor1;
        File file;
        file = new File(getExternalCacheDir(), "_tmp_bak");
        parcelfiledescriptor = null;
        parcelfiledescriptor1 = parcelfiledescriptor;
        file.createNewFile();
        parcelfiledescriptor1 = parcelfiledescriptor;
        parcelfiledescriptor = ParcelFileDescriptor.open(file, 0x20000000);
        parcelfiledescriptor1 = parcelfiledescriptor;
        onFullBackup(parcelfiledescriptor, j);
        parcelfiledescriptor1 = parcelfiledescriptor;
        int i1 = FullBackupProxy.backupToTar(s2, BackupManager.DOMAIN_BAK, null, file.getParent(), file.getAbsolutePath(), fullbackupdataoutput);
        if(i1 == 0)
            break MISSING_BLOCK_LABEL_407;
        parcelfiledescriptor1 = parcelfiledescriptor;
        fullbackupdataoutput = JVM INSTR new #222 <Class StringBuilder>;
        parcelfiledescriptor1 = parcelfiledescriptor;
        fullbackupdataoutput.StringBuilder();
        parcelfiledescriptor1 = parcelfiledescriptor;
        Log.w("Backup:FullBackupAgent", fullbackupdataoutput.append("err when data backup err = ").append(i1).toString());
        parcelfiledescriptor1 = parcelfiledescriptor;
        mBackupManager.setWorkingError(i1);
        if(parcelfiledescriptor != null)
            parcelfiledescriptor.close();
        file.delete();
        return;
        if(parcelfiledescriptor != null)
            parcelfiledescriptor.close();
        file.delete();
        i1 = tarAttaches(s2, fullbackupdataoutput, j);
        if(i1 == 0) goto _L4; else goto _L3
_L3:
        Log.w("Backup:FullBackupAgent", "err when tar attaches");
        mBackupManager.setWorkingError(i1);
        return;
        fullbackupdataoutput;
        if(parcelfiledescriptor1 != null)
            parcelfiledescriptor1.close();
        file.delete();
        throw fullbackupdataoutput;
        if(true) goto _L6; else goto _L5
_L5:
    }

    protected void onOriginalAttachesRestore(BackupMeta backupmeta, ParcelFileDescriptor parcelfiledescriptor, long l, int i, String s, String s1, 
            long l1, long l2)
        throws IOException
    {
        super.onRestoreFile(parcelfiledescriptor, l, i, s, s1, l1, l2);
    }

    public void onRestore(BackupDataInput backupdatainput, int i, ParcelFileDescriptor parcelfiledescriptor)
        throws IOException
    {
    }

    protected int onRestoreEnd(BackupMeta backupmeta)
        throws IOException
    {
        return 0;
    }

    protected final void onRestoreFile(ParcelFileDescriptor parcelfiledescriptor, long l, int i, String s, String s1, long l1, long l2)
        throws IOException
    {
        if(mBackupManager == null)
            mBackupManager = BackupManager.getBackupManager(this);
        StringBuilder stringbuilder = JVM INSTR new #222 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.v("Backup:FullBackupAgent", stringbuilder.append("onRestoreFile type = ").append(i).append(" domain = ").append(s).append(" path = ").append(s1).toString());
        if(!BackupManager.DOMAIN_META.equals(s)) goto _L2; else goto _L1
_L1:
        s = JVM INSTR new #67  <Class BackupMeta>;
        s.BackupMeta();
        mBackupMeta = s;
        mBackupMeta.buildFrom(this, parcelfiledescriptor, l, i, l1, l2);
_L3:
        return;
_L2:
        File file;
        if(!BackupManager.DOMAIN_BAK.equals(s))
            break MISSING_BLOCK_LABEL_317;
        file = JVM INSTR new #27  <Class File>;
        file.File(getExternalCacheDir(), "_tmp_bak");
        s1 = null;
        s = s1;
        FullBackup.restoreFile(parcelfiledescriptor, l, i, l1, l2, file);
        s = s1;
        parcelfiledescriptor = ParcelFileDescriptor.open(file, 0x10000000);
        s = parcelfiledescriptor;
        if(!checkVersion(mBackupMeta.feature))
            break MISSING_BLOCK_LABEL_292;
        s = parcelfiledescriptor;
        i = onDataRestore(mBackupMeta, parcelfiledescriptor);
_L4:
        if(i == 0)
            break MISSING_BLOCK_LABEL_262;
        s = parcelfiledescriptor;
        s1 = JVM INSTR new #222 <Class StringBuilder>;
        s = parcelfiledescriptor;
        s1.StringBuilder();
        s = parcelfiledescriptor;
        Log.w("Backup:FullBackupAgent", s1.append("err when data restoring err = ").append(i).toString());
        s = parcelfiledescriptor;
        mBackupManager.setWorkingError(i);
        if(parcelfiledescriptor == null)
            break MISSING_BLOCK_LABEL_270;
        parcelfiledescriptor.close();
        file.delete();
          goto _L3
        i = 5;
          goto _L4
        parcelfiledescriptor;
        if(s == null)
            break MISSING_BLOCK_LABEL_309;
        s.close();
        file.delete();
        throw parcelfiledescriptor;
        File file1;
        if(!BackupManager.DOMAIN_ATTACH.equals(s))
            break MISSING_BLOCK_LABEL_518;
        s = getFileName(s1);
        file1 = JVM INSTR new #27  <Class File>;
        file1.File(getExternalCacheDir(), s);
        file = null;
        s = file;
        FullBackup.restoreFile(parcelfiledescriptor, l, i, l1, l2, file1);
        s = file;
        parcelfiledescriptor = ParcelFileDescriptor.open(file1, 0x10000000);
        s = parcelfiledescriptor;
        if(!checkVersion(mBackupMeta.feature))
            break MISSING_BLOCK_LABEL_493;
        s = parcelfiledescriptor;
        i = onAttachRestore(mBackupMeta, parcelfiledescriptor, s1);
_L5:
        if(i == 0)
            break MISSING_BLOCK_LABEL_476;
        s = parcelfiledescriptor;
        s1 = JVM INSTR new #222 <Class StringBuilder>;
        s = parcelfiledescriptor;
        s1.StringBuilder();
        s = parcelfiledescriptor;
        Log.w("Backup:FullBackupAgent", s1.append("err when attach restoring err = ").append(i).toString());
        s = parcelfiledescriptor;
        mBackupManager.setWorkingError(i);
        if(parcelfiledescriptor == null)
            break MISSING_BLOCK_LABEL_484;
        parcelfiledescriptor.close();
        file1.delete();
          goto _L3
        i = 5;
          goto _L5
        parcelfiledescriptor;
        if(s == null)
            break MISSING_BLOCK_LABEL_510;
        s.close();
        file1.delete();
        throw parcelfiledescriptor;
        if(!BackupManager.DOMAIN_END.equals(s))
            break MISSING_BLOCK_LABEL_605;
        if(!checkVersion(mBackupMeta.feature))
            break MISSING_BLOCK_LABEL_599;
        i = onRestoreEnd(mBackupMeta);
_L6:
        if(i != 0)
            try
            {
                parcelfiledescriptor = JVM INSTR new #222 <Class StringBuilder>;
                parcelfiledescriptor.StringBuilder();
                Log.w("Backup:FullBackupAgent", parcelfiledescriptor.append("err when restore ending err = ").append(i).toString());
                mBackupManager.setWorkingError(i);
            }
            // Misplaced declaration of an exception variable
            catch(ParcelFileDescriptor parcelfiledescriptor)
            {
                Log.e("Backup:FullBackupAgent", "Exception when restore file", parcelfiledescriptor);
                throw parcelfiledescriptor;
            }
          goto _L3
        i = 5;
          goto _L6
        onOriginalAttachesRestore(mBackupMeta, parcelfiledescriptor, l, i, s, s1, l1, l2);
          goto _L3
    }

    public final void onRestoreFile(ParcelFileDescriptor parcelfiledescriptor, long l, File file, int i, long l1, 
            long l2)
        throws IOException
    {
        super.onRestoreFile(parcelfiledescriptor, l, file, i, l1, l2);
    }

    protected int tarAttaches(String s, FullBackupDataOutput fullbackupdataoutput, int i)
        throws IOException
    {
        if(mAttachedFiles == null) goto _L2; else goto _L1
_L1:
        Iterator iterator = mAttachedFiles.iterator();
_L3:
        Object obj;
        Pair pair;
        InputStream inputstream;
        File file;
        if(!iterator.hasNext())
            break; /* Loop/switch isn't completed */
        obj = iterator.next();
        if(obj instanceof String)
        {
            obj = new File((String)obj);
            if(((File) (obj)).exists())
                FullBackupProxy.backupToTar(s, BackupManager.DOMAIN_ATTACH, null, null, ((File) (obj)).getAbsolutePath(), fullbackupdataoutput);
            continue; /* Loop/switch isn't completed */
        }
        if(!(obj instanceof Pair))
            continue; /* Loop/switch isn't completed */
        pair = (Pair)obj;
        obj = null;
        inputstream = null;
        file = new File(getExternalCacheDir(), (String)pair.second);
        Object obj1 = getContentResolver().openInputStream((Uri)pair.first);
        inputstream = ((InputStream) (obj1));
        obj = obj1;
        FileUtils.copyToFile(((InputStream) (obj1)), file);
        inputstream = ((InputStream) (obj1));
        obj = obj1;
        if(!file.exists())
            break MISSING_BLOCK_LABEL_205;
        inputstream = ((InputStream) (obj1));
        obj = obj1;
        FullBackupProxy.backupToTar(s, BackupManager.DOMAIN_ATTACH, null, file.getParent(), file.getAbsolutePath(), fullbackupdataoutput);
        file.delete();
        if(obj1 != null)
            ((InputStream) (obj1)).close();
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        obj = inputstream;
        obj1 = JVM INSTR new #222 <Class StringBuilder>;
        obj = inputstream;
        ((StringBuilder) (obj1)).StringBuilder();
        obj = inputstream;
        Log.w("Backup:FullBackupAgent", ((StringBuilder) (obj1)).append("Exception when tar attaches for uri ").append(pair.first).append(" name ").append((String)pair.second).append(" skip!").toString(), exception);
        file.delete();
        if(inputstream != null)
            inputstream.close();
        if(true) goto _L3; else goto _L2
        s;
        file.delete();
        if(obj != null)
            ((InputStream) (obj)).close();
        throw s;
_L2:
        return 0;
    }

    private static final String TAG = "Backup:FullBackupAgent";
    private static final String TMP_BAK_NAME = "_tmp_bak";
    private ArrayList mAttachedFiles;
    private BackupManager mBackupManager;
    private BackupMeta mBackupMeta;
}
