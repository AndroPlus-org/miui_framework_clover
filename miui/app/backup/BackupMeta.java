// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.app.backup;

import android.app.backup.FullBackup;
import android.app.backup.FullBackupDataOutput;
import android.content.Context;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.*;

// Referenced classes of package miui.app.backup:
//            BackupManager, FullBackupProxy

public class BackupMeta
{

    public BackupMeta()
    {
        metaVersion = 1;
    }

    void buildFrom(Context context, ParcelFileDescriptor parcelfiledescriptor, long l, int i, long l1, 
            long l2)
        throws IOException
    {
        Object obj;
        context = new File(context.getExternalCacheDir(), "_tmp_meta");
        obj = null;
        FullBackup.restoreFile(parcelfiledescriptor, l, i, l1, l2, context);
        parcelfiledescriptor = JVM INSTR new #54  <Class BufferedReader>;
        FileReader filereader = JVM INSTR new #56  <Class FileReader>;
        filereader.FileReader(context);
        parcelfiledescriptor.BufferedReader(filereader);
        metaVersion = Integer.parseInt(parcelfiledescriptor.readLine());
        if(metaVersion == 1) goto _L2; else goto _L1
_L1:
        Log.e("Backup:BackupMeta", "version error");
_L4:
        if(parcelfiledescriptor != null)
            parcelfiledescriptor.close();
        return;
_L2:
        packageName = parcelfiledescriptor.readLine();
        appVersionCode = Integer.parseInt(parcelfiledescriptor.readLine());
        appVersionName = parcelfiledescriptor.readLine();
        createTime = Long.parseLong(parcelfiledescriptor.readLine());
        version = Integer.parseInt(parcelfiledescriptor.readLine());
        feature = Integer.parseInt(parcelfiledescriptor.readLine());
        deviceName = parcelfiledescriptor.readLine();
        miuiVersion = parcelfiledescriptor.readLine();
        if(parcelfiledescriptor.readLine() != null)
            Log.e("Backup:BackupMeta", "Something wrong in meta file");
        if(true) goto _L4; else goto _L3
_L3:
        context;
_L6:
        if(parcelfiledescriptor != null)
            parcelfiledescriptor.close();
        throw context;
        context;
        parcelfiledescriptor = obj;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public int getAppVersionCode()
    {
        return appVersionCode;
    }

    public String getAppVersionName()
    {
        return appVersionName;
    }

    public long getCreateTime()
    {
        return createTime;
    }

    public String getDeviceName()
    {
        return deviceName;
    }

    public int getFeature()
    {
        return feature;
    }

    public int getMetaVersion()
    {
        return 1;
    }

    public String getMiuiVersion()
    {
        return miuiVersion;
    }

    public String getPackageName()
    {
        return packageName;
    }

    public int getVersion()
    {
        return version;
    }

    public void writeToTar(Context context, FullBackupDataOutput fullbackupdataoutput)
        throws IOException
    {
        File file;
        Object obj;
        file = new File(context.getExternalCacheDir(), "_tmp_meta");
        context = new StringBuilder();
        context.append(String.valueOf(1)).append("\n");
        context.append(packageName).append("\n");
        context.append(String.valueOf(appVersionCode)).append("\n");
        context.append(appVersionName).append("\n");
        context.append(String.valueOf(createTime)).append("\n");
        context.append(String.valueOf(version)).append("\n");
        context.append(String.valueOf(feature)).append("\n");
        context.append(deviceName).append("\n");
        context.append(miuiVersion).append("\n");
        obj = null;
        FileOutputStream fileoutputstream;
        fileoutputstream = JVM INSTR new #141 <Class FileOutputStream>;
        fileoutputstream.FileOutputStream(file);
        fileoutputstream.write(context.toString().getBytes());
        FullBackupProxy.backupToTar(packageName, BackupManager.DOMAIN_META, null, file.getParentFile().getParent(), file.getAbsolutePath(), fullbackupdataoutput);
        if(fileoutputstream != null)
            fileoutputstream.close();
        file.delete();
        return;
        context;
        fullbackupdataoutput = obj;
_L2:
        if(fullbackupdataoutput != null)
            fullbackupdataoutput.close();
        file.delete();
        throw context;
        context;
        fullbackupdataoutput = fileoutputstream;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static final String METAFILE_NAME = "_tmp_meta";
    public static final int META_VERSION = 1;
    private static final String TAG = "Backup:BackupMeta";
    public int appVersionCode;
    public String appVersionName;
    public long createTime;
    public String deviceName;
    public int feature;
    public int metaVersion;
    public String miuiVersion;
    public String packageName;
    public int version;
}
