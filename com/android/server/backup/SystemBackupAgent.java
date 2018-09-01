// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.server.backup;

import android.app.IWallpaperManager;
import android.app.backup.*;
import android.os.*;
import android.util.Slog;
import java.io.File;
import java.io.IOException;

// Referenced classes of package com.android.server.backup:
//            AccountSyncSettingsBackupHelper, PreferredActivityBackupHelper, NotificationBackupHelper, PermissionBackupHelper, 
//            UsageStatsBackupHelper, ShortcutBackupHelper, AccountManagerBackupHelper

public class SystemBackupAgent extends BackupAgentHelper
{

    public SystemBackupAgent()
    {
        mWallpaperHelper = null;
    }

    public void onBackup(ParcelFileDescriptor parcelfiledescriptor, BackupDataOutput backupdataoutput, ParcelFileDescriptor parcelfiledescriptor1)
        throws IOException
    {
        addHelper("account_sync_settings", new AccountSyncSettingsBackupHelper(this));
        addHelper("preferred_activities", new PreferredActivityBackupHelper());
        addHelper("notifications", new NotificationBackupHelper(this));
        addHelper("permissions", new PermissionBackupHelper());
        addHelper("usage_stats", new UsageStatsBackupHelper(this));
        addHelper("shortcut_manager", new ShortcutBackupHelper());
        addHelper("account_manager", new AccountManagerBackupHelper());
        super.onBackup(parcelfiledescriptor, backupdataoutput, parcelfiledescriptor1);
    }

    public void onFullBackup(FullBackupDataOutput fullbackupdataoutput)
        throws IOException
    {
    }

    public void onRestore(BackupDataInput backupdatainput, int i, ParcelFileDescriptor parcelfiledescriptor)
        throws IOException
    {
        mWallpaperHelper = new WallpaperBackupHelper(this, new String[] {
            "/data/data/com.android.settings/files/wallpaper"
        });
        addHelper("wallpaper", mWallpaperHelper);
        addHelper("system_files", new WallpaperBackupHelper(this, new String[] {
            "/data/data/com.android.settings/files/wallpaper"
        }));
        addHelper("account_sync_settings", new AccountSyncSettingsBackupHelper(this));
        addHelper("preferred_activities", new PreferredActivityBackupHelper());
        addHelper("notifications", new NotificationBackupHelper(this));
        addHelper("permissions", new PermissionBackupHelper());
        addHelper("usage_stats", new UsageStatsBackupHelper(this));
        addHelper("shortcut_manager", new ShortcutBackupHelper());
        addHelper("account_manager", new AccountManagerBackupHelper());
        super.onRestore(backupdatainput, i, parcelfiledescriptor);
    }

    public void onRestoreFile(ParcelFileDescriptor parcelfiledescriptor, long l, int i, String s, String s1, long l1, long l2)
        throws IOException
    {
        boolean flag1;
        Slog.i("SystemBackupAgent", (new StringBuilder()).append("Restoring file domain=").append(s).append(" path=").append(s1).toString());
        boolean flag = false;
        StringBuilder stringbuilder = null;
        Object obj = stringbuilder;
        flag1 = flag;
        if(s.equals("r"))
            if(s1.equals("wallpaper_info.xml"))
            {
                obj = new File(WALLPAPER_INFO);
                flag1 = true;
            } else
            {
                obj = stringbuilder;
                flag1 = flag;
                if(s1.equals("wallpaper"))
                {
                    obj = new File(WALLPAPER_IMAGE);
                    flag1 = true;
                }
            }
        if(obj != null)
            break MISSING_BLOCK_LABEL_136;
        stringbuilder = JVM INSTR new #131 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Slog.w("SystemBackupAgent", stringbuilder.append("Skipping unrecognized system file: [ ").append(s).append(" : ").append(s1).append(" ]").toString());
        FullBackup.restoreFile(parcelfiledescriptor, l, i, l1, l2, ((File) (obj)));
        if(!flag1)
            break MISSING_BLOCK_LABEL_173;
        parcelfiledescriptor = (IWallpaperManager)ServiceManager.getService("wallpaper");
        if(parcelfiledescriptor == null)
            break MISSING_BLOCK_LABEL_173;
        parcelfiledescriptor.settingsRestored();
_L1:
        return;
        s;
        try
        {
            parcelfiledescriptor = JVM INSTR new #131 <Class StringBuilder>;
            parcelfiledescriptor.StringBuilder();
            Slog.e("SystemBackupAgent", parcelfiledescriptor.append("Couldn't restore settings\n").append(s).toString());
        }
        // Misplaced declaration of an exception variable
        catch(ParcelFileDescriptor parcelfiledescriptor)
        {
            if(flag1)
            {
                (new File(WALLPAPER_IMAGE)).delete();
                (new File(WALLPAPER_INFO)).delete();
            }
        }
          goto _L1
    }

    private static final String ACCOUNT_MANAGER_HELPER = "account_manager";
    private static final String NOTIFICATION_HELPER = "notifications";
    private static final String PERMISSION_HELPER = "permissions";
    private static final String PREFERRED_HELPER = "preferred_activities";
    private static final String SHORTCUT_MANAGER_HELPER = "shortcut_manager";
    private static final String SYNC_SETTINGS_HELPER = "account_sync_settings";
    private static final String TAG = "SystemBackupAgent";
    private static final String USAGE_STATS_HELPER = "usage_stats";
    private static final String WALLPAPER_HELPER = "wallpaper";
    public static final String WALLPAPER_IMAGE = (new File(Environment.getUserSystemDirectory(0), "wallpaper")).getAbsolutePath();
    private static final String WALLPAPER_IMAGE_DIR = Environment.getUserSystemDirectory(0).getAbsolutePath();
    private static final String WALLPAPER_IMAGE_FILENAME = "wallpaper";
    private static final String WALLPAPER_IMAGE_KEY = "/data/data/com.android.settings/files/wallpaper";
    public static final String WALLPAPER_INFO = (new File(Environment.getUserSystemDirectory(0), "wallpaper_info.xml")).getAbsolutePath();
    private static final String WALLPAPER_INFO_DIR = Environment.getUserSystemDirectory(0).getAbsolutePath();
    private static final String WALLPAPER_INFO_FILENAME = "wallpaper_info.xml";
    private WallpaperBackupHelper mWallpaperHelper;

}
