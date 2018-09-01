// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.pm.IPackageManager;
import android.os.*;
import android.system.ErrnoException;
import android.util.Slog;
import dalvik.system.BaseDexClassLoader;
import dalvik.system.VMRuntime;
import java.io.File;
import java.io.IOException;
import java.util.*;
import libcore.io.Libcore;
import libcore.io.Os;

// Referenced classes of package android.app:
//            ActivityThread

class DexLoadReporter
    implements dalvik.system.BaseDexClassLoader.Reporter
{

    private DexLoadReporter()
    {
    }

    static DexLoadReporter getInstance()
    {
        return INSTANCE;
    }

    private boolean isSecondaryDexFile(String s, String as[])
    {
        int i = as.length;
        for(int j = 0; j < i; j++)
            if(FileUtils.contains(as[j], s))
                return true;

        return false;
    }

    private void notifyPackageManager(List list, List list1)
    {
        ArrayList arraylist;
        arraylist = new ArrayList(list1.size());
        for(list = list.iterator(); list.hasNext(); arraylist.add(((BaseDexClassLoader)list.next()).getClass().getName()));
        list = ActivityThread.currentPackageName();
        ActivityThread.getPackageManager().notifyDexLoad(list, arraylist, list1, VMRuntime.getRuntime().vmInstructionSet());
_L1:
        return;
        list1;
        Slog.e("DexLoadReporter", (new StringBuilder()).append("Failed to notify PM about dex load for package ").append(list).toString(), list1);
          goto _L1
    }

    private void registerSecondaryDexForProfiling(String s, String as[])
    {
        if(!isSecondaryDexFile(s, as))
            return;
        File file;
        try
        {
            file = new File(Libcore.os.realpath(s));
        }
        // Misplaced declaration of an exception variable
        catch(String as[])
        {
            Slog.e("DexLoadReporter", (new StringBuilder()).append("Failed to get the real path of secondary dex ").append(s).append(":").append(as.getMessage()).toString());
            return;
        }
        as = new File(file.getParent(), "oat");
        file = new File(as, (new StringBuilder()).append(file.getName()).append(".cur.prof").toString());
        if(!as.exists() && !as.mkdir())
        {
            Slog.e("DexLoadReporter", (new StringBuilder()).append("Could not create the profile directory: ").append(file).toString());
            return;
        }
        try
        {
            file.createNewFile();
        }
        // Misplaced declaration of an exception variable
        catch(String as[])
        {
            Slog.e("DexLoadReporter", (new StringBuilder()).append("Failed to create profile for secondary dex ").append(s).append(":").append(as.getMessage()).toString());
            return;
        }
        VMRuntime.registerAppInfo(file.getPath(), new String[] {
            s
        });
    }

    private void registerSecondaryDexForProfiling(String as[])
    {
        int i;
        i = 0;
        if(!SystemProperties.getBoolean("dalvik.vm.dexopt.secondary", false))
            return;
        Set set = mDataDirs;
        set;
        JVM INSTR monitorenter ;
        String as1[] = (String[])mDataDirs.toArray(new String[0]);
        set;
        JVM INSTR monitorexit ;
        for(int j = as.length; i < j; i++)
            registerSecondaryDexForProfiling(as[i], as1);

        break MISSING_BLOCK_LABEL_69;
        as;
        throw as;
    }

    void registerAppDataDir(String s, String s1)
    {
        if(s1 == null) goto _L2; else goto _L1
_L1:
        s = mDataDirs;
        s;
        JVM INSTR monitorenter ;
        mDataDirs.add(s1);
        s;
        JVM INSTR monitorexit ;
_L2:
        return;
        s1;
        throw s1;
    }

    public void report(List list, List list1)
    {
        if(list.size() != list1.size())
        {
            Slog.wtf("DexLoadReporter", "Bad call to DexLoadReporter: argument size mismatch");
            return;
        }
        if(list1.isEmpty())
        {
            Slog.wtf("DexLoadReporter", "Bad call to DexLoadReporter: empty dex paths");
            return;
        }
        String as[] = ((String)list1.get(0)).split(File.pathSeparator);
        if(as.length == 0)
        {
            return;
        } else
        {
            notifyPackageManager(list, list1);
            registerSecondaryDexForProfiling(as);
            return;
        }
    }

    private static final boolean DEBUG = false;
    private static final DexLoadReporter INSTANCE = new DexLoadReporter();
    private static final String TAG = "DexLoadReporter";
    private final Set mDataDirs = new HashSet();

}
