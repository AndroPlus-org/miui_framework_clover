// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.backup;

import android.app.WallpaperManager;
import android.content.Context;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.util.Slog;
import java.io.*;

// Referenced classes of package android.app.backup:
//            FileBackupHelperBase, BackupHelper, BackupDataInputStream, BackupDataOutput

public class WallpaperBackupHelper extends FileBackupHelperBase
    implements BackupHelper
{

    public WallpaperBackupHelper(Context context, String as[])
    {
        super(context);
        mContext = context;
        mKeys = as;
        mWpm = (WallpaperManager)context.getSystemService("wallpaper");
    }

    public void performBackup(ParcelFileDescriptor parcelfiledescriptor, BackupDataOutput backupdataoutput, ParcelFileDescriptor parcelfiledescriptor1)
    {
    }

    public void restoreEntity(BackupDataInputStream backupdatainputstream)
    {
        Object obj;
        Object obj1;
        Object obj3;
        obj = null;
        obj1 = null;
        obj3 = backupdatainputstream.getKey();
        if(!isKeyInList(((String) (obj3)), mKeys) || !((String) (obj3)).equals("/data/data/com.android.settings/files/wallpaper")) goto _L2; else goto _L1
_L1:
        File file = new File(STAGE_FILE);
        boolean flag = writeFile(file, backupdatainputstream);
        if(!flag) goto _L4; else goto _L3
_L3:
        Object obj4;
        obj4 = null;
        obj3 = null;
        backupdatainputstream = JVM INSTR new #101 <Class FileInputStream>;
        backupdatainputstream.FileInputStream(file);
        mWpm.setStream(backupdatainputstream);
        obj3 = obj1;
        if(backupdatainputstream == null)
            break MISSING_BLOCK_LABEL_97;
        backupdatainputstream.close();
        obj3 = obj1;
_L7:
        if(obj3 == null) goto _L6; else goto _L5
_L5:
        try
        {
            throw obj3;
        }
        // Misplaced declaration of an exception variable
        catch(BackupDataInputStream backupdatainputstream) { }
_L8:
        obj3 = JVM INSTR new #113 <Class StringBuilder>;
        ((StringBuilder) (obj3)).StringBuilder();
        Slog.e("WallpaperBackupHelper", ((StringBuilder) (obj3)).append("Unable to set restored wallpaper: ").append(backupdatainputstream.getMessage()).toString());
_L6:
        file.delete();
_L2:
        return;
        obj3;
          goto _L7
        backupdatainputstream;
_L12:
        throw backupdatainputstream;
        obj;
        obj1 = backupdatainputstream;
        backupdatainputstream = ((BackupDataInputStream) (obj));
_L11:
        obj = obj1;
        if(obj3 == null)
            break MISSING_BLOCK_LABEL_173;
        ((FileInputStream) (obj3)).close();
        obj = obj1;
_L9:
        if(obj == null)
            break MISSING_BLOCK_LABEL_223;
        try
        {
            throw obj;
        }
        // Misplaced declaration of an exception variable
        catch(BackupDataInputStream backupdatainputstream) { }
          goto _L8
        obj3;
label0:
        {
            if(obj1 != null)
                break label0;
            obj = obj3;
        }
          goto _L9
        obj = obj1;
        if(obj1 == obj3) goto _L9; else goto _L10
_L10:
        ((Throwable) (obj1)).addSuppressed(((Throwable) (obj3)));
        obj = obj1;
          goto _L9
        backupdatainputstream;
        file.delete();
        throw backupdatainputstream;
        throw backupdatainputstream;
_L4:
        Slog.e("WallpaperBackupHelper", "Unable to save restored wallpaper");
          goto _L6
        backupdatainputstream;
        obj3 = obj4;
        obj1 = obj;
          goto _L11
        Object obj2;
        obj2;
        obj3 = backupdatainputstream;
        backupdatainputstream = ((BackupDataInputStream) (obj2));
        obj2 = obj;
          goto _L11
        obj2;
        obj3 = backupdatainputstream;
        backupdatainputstream = ((BackupDataInputStream) (obj2));
          goto _L12
    }

    public volatile void writeNewStateDescription(ParcelFileDescriptor parcelfiledescriptor)
    {
        super.writeNewStateDescription(parcelfiledescriptor);
    }

    private static final boolean DEBUG = false;
    private static final String STAGE_FILE = (new File(Environment.getUserSystemDirectory(0), "wallpaper-tmp")).getAbsolutePath();
    private static final String TAG = "WallpaperBackupHelper";
    public static final String WALLPAPER_IMAGE_KEY = "/data/data/com.android.settings/files/wallpaper";
    public static final String WALLPAPER_INFO_KEY = "/data/system/wallpaper_info.xml";
    private final String mKeys[];
    private final WallpaperManager mWpm;

}
