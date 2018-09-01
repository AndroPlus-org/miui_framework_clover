// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.os.Handler;
import android.os.SystemProperties;
import android.system.*;
import android.util.ArrayMap;
import android.util.Slog;
import com.android.internal.os.BackgroundThread;
import dalvik.system.*;
import java.io.FileDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;

// Referenced classes of package android.app:
//            LoadedApk, Application

public class FilePinner
{
    private static class PinTask
        implements Runnable
    {

        public void run()
        {
            ArrayList arraylist = FilePinner._2D_wrap0(mAppInfo, mPkgInfo);
            if(!arraylist.isEmpty())
                FilePinner._2D_wrap1(arraylist, mGoodToLock);
        }

        private ApplicationInfo mAppInfo;
        private boolean mGoodToLock;
        private LoadedApk mPkgInfo;

        public PinTask(ApplicationInfo applicationinfo, LoadedApk loadedapk, boolean flag)
        {
            mGoodToLock = false;
            mGoodToLock = flag;
            mAppInfo = applicationinfo;
            mPkgInfo = loadedapk;
        }
    }


    static ArrayList _2D_wrap0(ApplicationInfo applicationinfo, LoadedApk loadedapk)
    {
        return getFilesToPin(applicationinfo, loadedapk);
    }

    static void _2D_wrap1(ArrayList arraylist, boolean flag)
    {
        recordHotPages(arraylist, flag);
    }

    public FilePinner()
    {
    }

    private static ArrayList getFilesToPin(ApplicationInfo applicationinfo, LoadedApk loadedapk)
    {
        ArrayList arraylist = new ArrayList();
        String s = applicationinfo.getBaseCodePath();
        if(s != null)
        {
            String s1 = "arm";
            arraylist.add(s);
            Object obj = s1;
            if(applicationinfo.primaryCpuAbi != null)
            {
                obj = s1;
                if(VMRuntime.is64BitAbi(applicationinfo.primaryCpuAbi))
                    obj = (new StringBuilder()).append("arm").append("64").toString();
            }
            s1 = null;
            int i;
            int j;
            int k;
            int l;
            int i1;
            if(android.os.Build.VERSION.SDK_INT >= 25)
                try
                {
                    applicationinfo = (String)dalvik/system/DexFile.getDeclaredMethod("getDexFileOutputPath", new Class[] {
                        java/lang/String, java/lang/String
                    }).invoke(null, new Object[] {
                        s, obj
                    });
                }
                // Misplaced declaration of an exception variable
                catch(ApplicationInfo applicationinfo)
                {
                    Slog.e(TAG, "failed get base odex path");
                    applicationinfo = s1;
                }
            else
                applicationinfo = (new StringBuilder()).append(applicationinfo.getCodePath()).append("/oat/").append(((String) (obj))).append("/base.odex").toString();
            if(applicationinfo != null)
                arraylist.add(applicationinfo);
        }
        applicationinfo = loadedapk.getClassLoader();
        if(!(applicationinfo instanceof BaseDexClassLoader))
            break MISSING_BLOCK_LABEL_302;
        loadedapk = ((BaseDexClassLoader)applicationinfo).toString();
        i = 0;
        j = 0;
_L2:
        if(i == -1)
            break MISSING_BLOCK_LABEL_302;
        k = loadedapk.indexOf("dex file \"", j);
        l = loadedapk.indexOf("\",", k);
        j = l;
        i = k;
        if(k == -1) goto _L2; else goto _L1
_L1:
        j = l;
        i = k;
        if(l == -1) goto _L2; else goto _L3
_L3:
        applicationinfo = loadedapk.substring(k + 10, l);
        i1 = applicationinfo.indexOf("dex/");
        j = l;
        i = k;
        if(i1 < 0) goto _L2; else goto _L4
_L4:
        obj = JVM INSTR new #158 <Class StringBuffer>;
        ((StringBuffer) (obj)).StringBuffer(applicationinfo);
        ((StringBuffer) (obj)).insert(i1, "o");
        arraylist.add(((StringBuffer) (obj)).toString());
        j = l;
        i = k;
          goto _L2
        applicationinfo;
        Slog.e(TAG, "failed to find dex files");
        return arraylist;
    }

    static void handlePinAppFile(Application application, LoadedApk loadedapk, Resources resources)
    {
        while(!enablePinAppFile || mIsSelectedApp ^ true || mPinFileDone || application == null || loadedapk == null) 
            return;
        application = application.getApplicationInfo();
        if(application == null)
            return;
        if(resources != null)
            try
            {
                mAppsToPin = resources.getStringArray(0x107000c);
            }
            // Misplaced declaration of an exception variable
            catch(Resources resources)
            {
                Slog.e(TAG, "failed get app to pin");
            }
        if(mAppsToPin != null)
        {
            int i = mAppsToPin.length;
            int j;
label0:
            do
            {
                do
                {
                    j = i - 1;
                    if(j < 0)
                        break label0;
                    i = j;
                } while(mAppsToPin[j] == null);
                i = j;
            } while(!mAppsToPin[j].equals(((ApplicationInfo) (application)).processName));
            if(j < 0)
            {
                mIsSelectedApp = false;
                return;
            }
        } else
        {
            mIsSelectedApp = false;
            return;
        }
        if(mPageProfileCount < 10)
        {
            mPageProfileCount++;
            boolean flag = false;
            if(mPageProfileCount == 10)
            {
                flag = true;
                mPinFileDone = true;
            }
            application = new PinTask(application, loadedapk, flag);
            BackgroundThread.getHandler().postDelayed(application, 5000L);
        }
    }

    private static void recordHotPages(ArrayList arraylist, boolean flag)
    {
        int i;
        int j;
        i = 0;
        j = 0;
_L15:
        Object obj;
        Object obj1;
        Object obj2;
        int k;
        if(j >= arraylist.size())
            break MISSING_BLOCK_LABEL_879;
        obj = new FileDescriptor();
        obj1 = (String)arraylist.get(j);
        obj2 = obj;
        k = i;
        Object obj3 = Os.open(((String) (obj1)), OsConstants.O_RDONLY | OsConstants.O_NOFOLLOW | 0x80000, OsConstants.O_RDONLY);
        obj2 = obj3;
        k = i;
        obj = obj3;
        Object obj4 = Os.fstat(((FileDescriptor) (obj3)));
        obj2 = obj3;
        k = i;
        obj = obj3;
        long l = Os.mmap(0L, ((StructStat) (obj4)).st_size, OsConstants.PROT_READ, OsConstants.MAP_PRIVATE, ((FileDescriptor) (obj3)), 0L);
        obj2 = obj3;
        k = i;
        obj = obj3;
        int i1 = (((int)((StructStat) (obj4)).st_size + 4096) - 1) / 4096;
        obj2 = obj3;
        k = i;
        obj = obj3;
        byte abyte0[] = new byte[i1];
        obj2 = obj3;
        k = i;
        obj = obj3;
        Os.mincore(l, ((StructStat) (obj4)).st_size, abyte0);
        obj2 = obj3;
        k = i;
        obj = obj3;
        byte abyte1[] = (byte[])mfileCaheVecs.get(obj1);
        if(abyte1 == null) goto _L2; else goto _L1
_L1:
        for(int j1 = 0; j1 < i1; j1++)
            abyte1[j1] = (byte)(abyte1[j1] & abyte0[j1]);

        obj2 = obj3;
        k = i;
        obj = obj3;
        mfileCaheVecs.put(obj1, abyte1);
_L5:
        if(flag) goto _L4; else goto _L3
_L3:
        obj2 = obj3;
        k = i;
        obj = obj3;
        Os.munmap(l, ((StructStat) (obj4)).st_size);
        int l1;
        l1 = i;
        if(!((FileDescriptor) (obj3)).valid())
            break MISSING_BLOCK_LABEL_305;
        Os.close(((FileDescriptor) (obj3)));
        l1 = i;
_L7:
        j++;
        i = l1;
        continue; /* Loop/switch isn't completed */
_L2:
        obj2 = obj3;
        k = i;
        obj = obj3;
        mfileCaheVecs.put(obj1, abyte0);
          goto _L5
        obj1;
        obj = obj2;
        obj4 = TAG;
        obj = obj2;
        obj3 = JVM INSTR new #99  <Class StringBuilder>;
        obj = obj2;
        ((StringBuilder) (obj3)).StringBuilder();
        obj = obj2;
        Slog.e(((String) (obj4)), ((StringBuilder) (obj3)).append("Could not pin file with error ").append(((ErrnoException) (obj1)).getMessage()).toString());
        l1 = k;
        if(!((FileDescriptor) (obj2)).valid()) goto _L7; else goto _L6
_L6:
        Os.close(((FileDescriptor) (obj2)));
        l1 = k;
          goto _L7
        obj2;
        Slog.e(TAG, (new StringBuilder()).append("Failed to close fd, error = ").append(((ErrnoException) (obj2)).getMessage()).toString());
        l1 = k;
          goto _L7
        obj2;
        Slog.e(TAG, (new StringBuilder()).append("Failed to close fd, error = ").append(((ErrnoException) (obj2)).getMessage()).toString());
        l1 = i;
          goto _L7
_L4:
        int i2;
        int j2;
        i2 = 0;
        l1 = 0;
        j2 = 0;
_L9:
        int k1;
        int k2;
        k = l1;
        k1 = i;
        if(i2 >= i1)
            break MISSING_BLOCK_LABEL_649;
        if((abyte1[i2] & 1) <= 0)
            break; /* Loop/switch isn't completed */
        k1 = j2 + 1;
        k = i;
        k2 = l1;
_L11:
        i2++;
        l1 = k2;
        j2 = k1;
        i = k;
        if(true) goto _L9; else goto _L8
_L8:
        k2 = l1;
        k1 = j2;
        k = i;
        if(j2 <= 0) goto _L11; else goto _L10
_L10:
        obj2 = obj3;
        k = i;
        obj = obj3;
        Os.mlock((long)((i2 - j2) * 4096) + l, j2 * 4096);
        l1 += j2;
        i += j2;
        k1 = 0;
        k2 = l1;
        k = i;
        if(i <= 12800) goto _L11; else goto _L12
_L12:
        k1 = i;
        k = l1;
        double d;
        d = (double)(k * 100) / (double)i1;
        obj2 = obj3;
        k = k1;
        obj = obj3;
        String s = TAG;
        obj2 = obj3;
        k = k1;
        obj = obj3;
        obj4 = JVM INSTR new #99  <Class StringBuilder>;
        obj2 = obj3;
        k = k1;
        obj = obj3;
        ((StringBuilder) (obj4)).StringBuilder();
        obj2 = obj3;
        k = k1;
        obj = obj3;
        Slog.i(s, ((StringBuilder) (obj4)).append("cached ").append(((String) (obj1))).append(" with ").append(d).append("%").toString());
        l1 = k1;
        if(!((FileDescriptor) (obj3)).valid()) goto _L7; else goto _L13
_L13:
        Os.close(((FileDescriptor) (obj3)));
        l1 = k1;
          goto _L7
        ErrnoException errnoexception;
        errnoexception;
        Slog.e(TAG, (new StringBuilder()).append("Failed to close fd, error = ").append(errnoexception.getMessage()).toString());
        l1 = k1;
          goto _L7
        arraylist;
        if(((FileDescriptor) (obj)).valid())
            try
            {
                Os.close(((FileDescriptor) (obj)));
            }
            catch(ErrnoException errnoexception1)
            {
                Slog.e(TAG, (new StringBuilder()).append("Failed to close fd, error = ").append(errnoexception1.getMessage()).toString());
            }
        throw arraylist;
        return;
        if(true) goto _L15; else goto _L14
_L14:
    }

    private static final int MAX_LOCK_PAGES = 12800;
    private static final int MAX_PROFILE_COUNT = 10;
    private static final int PROFILE_DELAY = 5000;
    private static String TAG = "FilePinner";
    private static boolean enablePinAppFile = SystemProperties.getBoolean("persist.sys.pinappfile", false);
    private static String mAppsToPin[] = {
        " "
    };
    private static boolean mIsSelectedApp = true;
    private static int mPageProfileCount;
    private static boolean mPinFileDone = false;
    private static ArrayMap mfileCaheVecs = new ArrayMap();

}
