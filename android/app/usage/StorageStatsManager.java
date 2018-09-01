// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.usage;

import android.content.Context;
import android.os.*;
import android.os.storage.StorageManager;
import com.android.internal.util.Preconditions;
import java.io.IOException;
import java.util.UUID;

// Referenced classes of package android.app.usage:
//            IStorageStatsManager, ExternalStorageStats, StorageStats

public class StorageStatsManager
{

    public StorageStatsManager(Context context, IStorageStatsManager istoragestatsmanager)
    {
        mContext = (Context)Preconditions.checkNotNull(context);
        mService = (IStorageStatsManager)Preconditions.checkNotNull(istoragestatsmanager);
    }

    public long getCacheBytes(String s)
        throws IOException
    {
        return getCacheBytes(StorageManager.convert(s));
    }

    public long getCacheBytes(UUID uuid)
        throws IOException
    {
        long l;
        try
        {
            l = mService.getCacheBytes(StorageManager.convert(uuid), mContext.getOpPackageName());
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

    public long getCacheQuotaBytes(String s, int i)
    {
        long l;
        try
        {
            l = mService.getCacheQuotaBytes(s, i, mContext.getOpPackageName());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return l;
    }

    public long getFreeBytes(String s)
        throws IOException
    {
        return getFreeBytes(StorageManager.convert(s));
    }

    public long getFreeBytes(UUID uuid)
        throws IOException
    {
        long l;
        try
        {
            l = mService.getFreeBytes(StorageManager.convert(uuid), mContext.getOpPackageName());
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

    public long getTotalBytes(String s)
        throws IOException
    {
        return getTotalBytes(StorageManager.convert(s));
    }

    public long getTotalBytes(UUID uuid)
        throws IOException
    {
        long l;
        try
        {
            l = mService.getTotalBytes(StorageManager.convert(uuid), mContext.getOpPackageName());
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

    public boolean isQuotaSupported(String s)
    {
        return isQuotaSupported(StorageManager.convert(s));
    }

    public boolean isQuotaSupported(UUID uuid)
    {
        boolean flag;
        try
        {
            flag = mService.isQuotaSupported(StorageManager.convert(uuid), mContext.getOpPackageName());
        }
        // Misplaced declaration of an exception variable
        catch(UUID uuid)
        {
            throw uuid.rethrowFromSystemServer();
        }
        return flag;
    }

    public ExternalStorageStats queryExternalStatsForUser(String s, UserHandle userhandle)
        throws IOException
    {
        return queryExternalStatsForUser(StorageManager.convert(s), userhandle);
    }

    public ExternalStorageStats queryExternalStatsForUser(UUID uuid, UserHandle userhandle)
        throws IOException
    {
        try
        {
            uuid = mService.queryExternalStatsForUser(StorageManager.convert(uuid), userhandle.getIdentifier(), mContext.getOpPackageName());
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
        return uuid;
    }

    public StorageStats queryStatsForPackage(String s, String s1, UserHandle userhandle)
        throws android.content.pm.PackageManager.NameNotFoundException, IOException
    {
        return queryStatsForPackage(StorageManager.convert(s), s1, userhandle);
    }

    public StorageStats queryStatsForPackage(UUID uuid, String s, UserHandle userhandle)
        throws android.content.pm.PackageManager.NameNotFoundException, IOException
    {
        try
        {
            uuid = mService.queryStatsForPackage(StorageManager.convert(uuid), s, userhandle.getIdentifier(), mContext.getOpPackageName());
        }
        // Misplaced declaration of an exception variable
        catch(UUID uuid)
        {
            uuid.maybeRethrow(android/content/pm/PackageManager$NameNotFoundException);
            uuid.maybeRethrow(java/io/IOException);
            throw new RuntimeException(uuid);
        }
        // Misplaced declaration of an exception variable
        catch(UUID uuid)
        {
            throw uuid.rethrowFromSystemServer();
        }
        return uuid;
    }

    public StorageStats queryStatsForUid(String s, int i)
        throws IOException
    {
        return queryStatsForUid(StorageManager.convert(s), i);
    }

    public StorageStats queryStatsForUid(UUID uuid, int i)
        throws IOException
    {
        try
        {
            uuid = mService.queryStatsForUid(StorageManager.convert(uuid), i, mContext.getOpPackageName());
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
        return uuid;
    }

    public StorageStats queryStatsForUser(String s, UserHandle userhandle)
        throws IOException
    {
        return queryStatsForUser(StorageManager.convert(s), userhandle);
    }

    public StorageStats queryStatsForUser(UUID uuid, UserHandle userhandle)
        throws IOException
    {
        try
        {
            uuid = mService.queryStatsForUser(StorageManager.convert(uuid), userhandle.getIdentifier(), mContext.getOpPackageName());
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
        return uuid;
    }

    private final Context mContext;
    private final IStorageStatsManager mService;
}
