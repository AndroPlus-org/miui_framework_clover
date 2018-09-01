// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.server;

import android.content.*;
import android.content.pm.IPackageManager;
import android.os.*;
import android.os.storage.StorageManager;
import android.provider.Downloads;
import android.text.TextUtils;
import android.util.*;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.util.FastXmlSerializer;
import com.android.internal.util.XmlUtils;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xmlpull.v1.*;

// Referenced classes of package com.android.server:
//            BootReceiverInjector

public class BootReceiver extends BroadcastReceiver
{

    static int _2D_get0()
    {
        return LOG_SIZE;
    }

    static File _2D_get1()
    {
        return TOMBSTONE_DIR;
    }

    static HashMap _2D_wrap0()
    {
        return readTimestamps();
    }

    static void _2D_wrap1(DropBoxManager dropboxmanager, HashMap hashmap, String s, String s1, int i, String s2)
    {
        addFileToDropBox(dropboxmanager, hashmap, s, s1, i, s2);
    }

    static void _2D_wrap2(BootReceiver bootreceiver, Context context)
    {
        bootreceiver.logBootEvents(context);
    }

    static void _2D_wrap3(BootReceiver bootreceiver, Context context)
    {
        bootreceiver.removeOldUpdatePackages(context);
    }

    static void _2D_wrap4(BootReceiver bootreceiver, HashMap hashmap)
    {
        bootreceiver.writeTimestamps(hashmap);
    }

    public BootReceiver()
    {
    }

    private static void addAuditErrorsToDropBox(DropBoxManager dropboxmanager, HashMap hashmap, String s, int i, String s1)
        throws IOException
    {
        if(dropboxmanager == null || dropboxmanager.isTagEnabled(s1) ^ true)
            return;
        Slog.i("BootReceiver", "Copying audit failures to DropBox");
        Object obj = new File("/proc/last_kmsg");
        long l = ((File) (obj)).lastModified();
        long l2 = l;
        if(l <= 0L)
        {
            obj = new File("/sys/fs/pstore/console-ramoops");
            long l1 = ((File) (obj)).lastModified();
            l2 = l1;
            if(l1 <= 0L)
            {
                obj = new File("/sys/fs/pstore/console-ramoops-0");
                l2 = ((File) (obj)).lastModified();
            }
        }
        if(l2 <= 0L)
            return;
        if(hashmap.containsKey(s1) && ((Long)hashmap.get(s1)).longValue() == l2)
            return;
        hashmap.put(s1, Long.valueOf(l2));
        obj = FileUtils.readTextFile(((File) (obj)), i, "[[TRUNCATED]]\n");
        hashmap = new StringBuilder();
        String as[] = ((String) (obj)).split("\n");
        i = 0;
        for(int j = as.length; i < j; i++)
        {
            String s2 = as[i];
            if(s2.contains("audit"))
                hashmap.append(s2).append("\n");
        }

        Slog.i("BootReceiver", (new StringBuilder()).append("Copied ").append(hashmap.toString().length()).append(" worth of audits to DropBox").toString());
        dropboxmanager.addText(s1, (new StringBuilder()).append(s).append(hashmap.toString()).toString());
    }

    private static void addFileToDropBox(DropBoxManager dropboxmanager, HashMap hashmap, String s, String s1, int i, String s2)
        throws IOException
    {
        addFileWithFootersToDropBox(dropboxmanager, hashmap, s, "", s1, i, s2);
    }

    private static void addFileWithFootersToDropBox(DropBoxManager dropboxmanager, HashMap hashmap, String s, String s1, String s2, int i, String s3)
        throws IOException
    {
        if(dropboxmanager == null || dropboxmanager.isTagEnabled(s3) ^ true)
            return;
        File file = new File(s2);
        long l = file.lastModified();
        if(l <= 0L)
            return;
        if(hashmap.containsKey(s2) && ((Long)hashmap.get(s2)).longValue() == l)
        {
            return;
        } else
        {
            hashmap.put(s2, Long.valueOf(l));
            Slog.i("BootReceiver", (new StringBuilder()).append("Copying ").append(s2).append(" to DropBox (").append(s3).append(")").toString());
            dropboxmanager.addText(s3, (new StringBuilder()).append(s).append(FileUtils.readTextFile(file, i, "[[TRUNCATED]]\n")).append(s1).toString());
            return;
        }
    }

    private static void addFsckErrorsToDropBoxAndLogFsStat(DropBoxManager dropboxmanager, HashMap hashmap, String s, int i, String s1)
        throws IOException
    {
        boolean flag = true;
        if(dropboxmanager == null || dropboxmanager.isTagEnabled(s1) ^ true)
            flag = false;
        boolean flag1 = false;
        Slog.i("BootReceiver", "Checking for fsck errors");
        File file = new File("/dev/fscklogs/log");
        if(file.lastModified() <= 0L)
            return;
        String s2 = FileUtils.readTextFile(file, i, "[[TRUNCATED]]\n");
        Pattern pattern = Pattern.compile("fs_stat,[^,]*/([^/,]+),(0x[0-9a-fA-F]+)");
        String as[] = s2.split("\n");
        int j = 0;
        int k = 0;
        int l = 0;
        int i1 = as.length;
        while(l < i1) 
        {
            String s3 = as[l];
            int j1;
            int k1;
            if(s3.contains("FILE SYSTEM WAS MODIFIED"))
            {
                j1 = 1;
                k1 = k;
            } else
            {
                k1 = k;
                j1 = ((flag1) ? 1 : 0);
                if(s3.contains("fs_stat"))
                {
                    Matcher matcher = pattern.matcher(s3);
                    if(matcher.find())
                    {
                        j1 = Integer.decode(matcher.group(2)).intValue();
                        if((j1 & 8) != 0)
                        {
                            Slog.i("BootReceiver", (new StringBuilder()).append("file system unclean shutdown, fs_stat:0x").append(Integer.toHexString(j1)).toString());
                            flag1 = true;
                        }
                        handleFsckFsStat(matcher, as, k, j);
                        k1 = j;
                        j1 = ((flag1) ? 1 : 0);
                    } else
                    {
                        Slog.w("BootReceiver", (new StringBuilder()).append("cannot parse fs_stat:").append(s3).toString());
                        k1 = k;
                        j1 = ((flag1) ? 1 : 0);
                    }
                }
            }
            j++;
            l++;
            k = k1;
            flag1 = j1;
        }
        if(flag && flag1)
            addFileToDropBox(dropboxmanager, hashmap, s, "/dev/fscklogs/log", i, s1);
        file.delete();
    }

    public static int fixFsckFsStat(String s, int i, String as[], int j, int k)
    {
        int l;
        int i1;
        l = i;
        i1 = l;
        if((i & 0x400) == 0) goto _L2; else goto _L1
_L1:
        Pattern pattern;
        Pattern pattern1;
        String s1;
        boolean flag;
        boolean flag1;
        boolean flag2;
        Object obj;
        pattern = Pattern.compile("Pass ([1-9]E?):");
        pattern1 = Pattern.compile("Inode [0-9]+ extent tree.*could be shorter");
        s1 = "";
        flag = false;
        i1 = 0;
        flag1 = false;
        flag2 = false;
        obj = null;
_L7:
        int j1;
        int k1;
        Object obj1;
        j1 = ((flag2) ? 1 : 0);
        k1 = i1;
        obj1 = obj;
        if(j >= k) goto _L4; else goto _L3
_L3:
        Object obj2 = as[j];
        if(!((String) (obj2)).contains("FILE SYSTEM WAS MODIFIED")) goto _L6; else goto _L5
_L5:
        obj1 = obj;
        k1 = i1;
        j1 = ((flag2) ? 1 : 0);
_L4:
        boolean flag3;
        boolean flag4;
        if(j1 != 0)
        {
            i1 = l;
            if(obj1 != null)
            {
                Slog.i("BootReceiver", (new StringBuilder()).append("fs_stat, partition:").append(s).append(" fix:").append(((String) (obj1))).toString());
                i1 = l;
            }
        } else
        {
label0:
            {
                if(k1 == 0 || !(flag ^ true))
                    break label0;
                Slog.i("BootReceiver", (new StringBuilder()).append("fs_stat, got quota fix without tree optimization, partition:").append(s).toString());
                i1 = l;
            }
        }
_L2:
        return i1;
_L6:
        if(((String) (obj2)).startsWith("Pass "))
        {
            obj2 = pattern.matcher(((CharSequence) (obj2)));
            obj1 = s1;
            k1 = i1;
            flag3 = flag1;
            flag4 = flag;
            j1 = j;
            if(((Matcher) (obj2)).find())
            {
                obj1 = ((Matcher) (obj2)).group(1);
                j1 = j;
                flag4 = flag;
                flag3 = flag1;
                k1 = i1;
            }
        } else
        {
label1:
            {
                if(!((String) (obj2)).startsWith("Inode "))
                    break MISSING_BLOCK_LABEL_352;
                if(!pattern1.matcher(((CharSequence) (obj2))).find() || !s1.equals("1"))
                    break label1;
                flag4 = true;
                Slog.i("BootReceiver", (new StringBuilder()).append("fs_stat, partition:").append(s).append(" found tree optimization:").append(((String) (obj2))).toString());
                obj1 = s1;
                k1 = i1;
                flag3 = flag1;
                j1 = j;
            }
        }
_L9:
        j = j1 + 1;
        s1 = ((String) (obj1));
        i1 = k1;
        flag1 = flag3;
        flag = flag4;
          goto _L7
        j1 = 1;
        obj1 = obj2;
        k1 = i1;
          goto _L4
        if(!((String) (obj2)).startsWith("[QUOTA WARNING]") || !s1.equals("5"))
            break MISSING_BLOCK_LABEL_452;
        Slog.i("BootReceiver", (new StringBuilder()).append("fs_stat, partition:").append(s).append(" found quota warning:").append(((String) (obj2))).toString());
        k1 = 1;
        i1 = 1;
        obj1 = s1;
        flag3 = flag1;
        flag4 = flag;
        j1 = j;
        if(flag) goto _L9; else goto _L8
_L8:
        obj1 = obj2;
        j1 = ((flag2) ? 1 : 0);
        k1 = i1;
          goto _L4
        if(!((String) (obj2)).startsWith("Update quota info")) goto _L11; else goto _L10
_L10:
        obj1 = s1;
        k1 = i1;
        flag3 = flag1;
        flag4 = flag;
        j1 = j;
        if(s1.equals("5")) goto _L9; else goto _L11
_L11:
label2:
        {
            if(!((String) (obj2)).startsWith("Timestamp(s) on inode") || !((String) (obj2)).contains("beyond 2310-04-04 are likely pre-1970") || !s1.equals("1"))
                break label2;
            Slog.i("BootReceiver", (new StringBuilder()).append("fs_stat, partition:").append(s).append(" found timestamp adjustment:").append(((String) (obj2))).toString());
            j1 = j;
            if(as[j + 1].contains("Fix? yes"))
                j1 = j + 1;
            flag3 = true;
            obj1 = s1;
            k1 = i1;
            flag4 = flag;
        }
          goto _L9
        obj2 = ((String) (obj2)).trim();
        obj1 = s1;
        k1 = i1;
        flag3 = flag1;
        flag4 = flag;
        j1 = j;
        if(((String) (obj2)).isEmpty()) goto _L9; else goto _L12
_L12:
        obj1 = s1;
        k1 = i1;
        flag3 = flag1;
        flag4 = flag;
        j1 = j;
        if(!(s1.isEmpty() ^ true)) goto _L9; else goto _L13
_L13:
        j1 = 1;
        obj1 = obj2;
        k1 = i1;
          goto _L4
        if(flag && k1 != 0) goto _L15; else goto _L14
_L14:
        i1 = l;
        if(!flag1) goto _L2; else goto _L15
_L15:
        Slog.i("BootReceiver", (new StringBuilder()).append("fs_stat, partition:").append(s).append(" fix ignored").toString());
        i1 = i & 0xfffffbff;
          goto _L2
    }

    private String getBootHeadersToLogAndUpdate()
        throws IOException
    {
        String s = getPreviousBootHeaders();
        String s1 = getCurrentBootHeaders();
        try
        {
            FileUtils.stringToFile(lastHeaderFile, s1);
        }
        catch(IOException ioexception)
        {
            Slog.e("BootReceiver", (new StringBuilder()).append("Error writing ").append(lastHeaderFile).toString(), ioexception);
        }
        if(s == null)
            return (new StringBuilder()).append("isPrevious: false\n").append(s1).toString();
        else
            return (new StringBuilder()).append("isPrevious: true\n").append(s).toString();
    }

    private String getCurrentBootHeaders()
        throws IOException
    {
        return (new StringBuilder(512)).append("Build: ").append(Build.FINGERPRINT).append("\n").append("Hardware: ").append(Build.BOARD).append("\n").append("Revision: ").append(SystemProperties.get("ro.revision", "")).append("\n").append("Bootloader: ").append(Build.BOOTLOADER).append("\n").append("Radio: ").append(Build.RADIO).append("\n").append("Kernel: ").append(FileUtils.readTextFile(new File("/proc/version"), 1024, "...\n")).append("\n").toString();
    }

    private String getPreviousBootHeaders()
    {
        String s;
        try
        {
            s = FileUtils.readTextFile(lastHeaderFile, 0, null);
        }
        catch(IOException ioexception)
        {
            return null;
        }
        return s;
    }

    private static void handleFsckFsStat(Matcher matcher, String as[], int i, int j)
    {
        String s = matcher.group(1);
        int k;
        try
        {
            k = Integer.decode(matcher.group(2)).intValue();
        }
        // Misplaced declaration of an exception variable
        catch(String as[])
        {
            Slog.w("BootReceiver", (new StringBuilder()).append("cannot parse fs_stat: partition:").append(s).append(" stat:").append(matcher.group(2)).toString());
            return;
        }
        i = fixFsckFsStat(s, k, as, i, j);
        MetricsLogger.histogram(null, (new StringBuilder()).append("boot_fs_stat_").append(s).toString(), i);
        Slog.i("BootReceiver", (new StringBuilder()).append("fs_stat, partition:").append(s).append(" stat:0x").append(Integer.toHexString(i)).toString());
    }

    private void logBootEvents(Context context)
        throws IOException
    {
        DropBoxManager dropboxmanager;
        String s;
        Object obj;
        dropboxmanager = (DropBoxManager)context.getSystemService("dropbox");
        s = getBootHeadersToLogAndUpdate();
        obj = SystemProperties.get("ro.boot.bootreason", null);
        context = RecoverySystem.handleAftermath(context);
        if(context != null && dropboxmanager != null)
            dropboxmanager.addText("SYSTEM_RECOVERY_LOG", (new StringBuilder()).append(s).append(context).toString());
        BootReceiverInjector.onBootCompleted();
        context = "";
        if(obj != null)
            context = (new StringBuilder(512)).append("\n").append("Boot info:\n").append("Last boot reason: ").append(((String) (obj))).append("\n").toString();
        obj = readTimestamps();
        if(SystemProperties.getLong("ro.runtime.firstboot", 0L) != 0L) goto _L2; else goto _L1
_L1:
        int i;
        if(!StorageManager.inCryptKeeperBounce())
            SystemProperties.set("ro.runtime.firstboot", Long.toString(System.currentTimeMillis()));
        if(dropboxmanager != null)
            dropboxmanager.addText("SYSTEM_BOOT", s);
        addFileWithFootersToDropBox(dropboxmanager, ((HashMap) (obj)), s, context, "/proc/last_kmsg", -LOG_SIZE, "SYSTEM_LAST_KMSG");
        addFileWithFootersToDropBox(dropboxmanager, ((HashMap) (obj)), s, context, "/sys/fs/pstore/console-ramoops", -LOG_SIZE, "SYSTEM_LAST_KMSG");
        addFileWithFootersToDropBox(dropboxmanager, ((HashMap) (obj)), s, context, "/sys/fs/pstore/console-ramoops-0", -LOG_SIZE, "SYSTEM_LAST_KMSG");
        addFileToDropBox(dropboxmanager, ((HashMap) (obj)), s, "/cache/recovery/log", -LOG_SIZE, "SYSTEM_RECOVERY_LOG");
        addFileToDropBox(dropboxmanager, ((HashMap) (obj)), s, "/cache/recovery/last_kmsg", -LOG_SIZE, "SYSTEM_RECOVERY_KMSG");
        addAuditErrorsToDropBox(dropboxmanager, ((HashMap) (obj)), s, -LOG_SIZE, "SYSTEM_AUDIT");
_L4:
        logFsShutdownTime();
        logFsMountTime();
        addFsckErrorsToDropBoxAndLogFsStat(dropboxmanager, ((HashMap) (obj)), s, -LOG_SIZE, "SYSTEM_FSCK");
        logSystemServerShutdownTimeMetrics();
        context = TOMBSTONE_DIR.listFiles();
        for(i = 0; context != null && i < context.length; i++)
            if(context[i].isFile())
                addFileToDropBox(dropboxmanager, ((HashMap) (obj)), s, context[i].getPath(), LOG_SIZE, "SYSTEM_TOMBSTONE");

        break; /* Loop/switch isn't completed */
_L2:
        if(dropboxmanager != null)
            dropboxmanager.addText("SYSTEM_RESTART", s);
        if(true) goto _L4; else goto _L3
_L3:
        writeTimestamps(((HashMap) (obj)));
        sTombstoneObserver = new FileObserver(dropboxmanager, s) {

            public void onEvent(int j, String s1)
            {
                HashMap hashmap = BootReceiver._2D_wrap0();
                try
                {
                    File file = JVM INSTR new #37  <Class File>;
                    file.File(BootReceiver._2D_get1(), s1);
                    if(file.isFile())
                        BootReceiver._2D_wrap1(db, hashmap, headers, file.getPath(), BootReceiver._2D_get0(), "SYSTEM_TOMBSTONE");
                }
                // Misplaced declaration of an exception variable
                catch(String s1)
                {
                    Slog.e("BootReceiver", "Can't log tombstone", s1);
                }
                BootReceiver._2D_wrap4(BootReceiver.this, hashmap);
            }

            final BootReceiver this$0;
            final DropBoxManager val$db;
            final String val$headers;

            
            {
                this$0 = BootReceiver.this;
                db = dropboxmanager;
                headers = s1;
                super(final_s, final_i);
            }
        }
;
        sTombstoneObserver.startWatching();
        return;
    }

    private static void logFsMountTime()
    {
        String as[] = MOUNT_DURATION_PROPS_POSTFIX;
        int i = as.length;
        for(int j = 0; j < i; j++)
        {
            String s = as[j];
            int k = SystemProperties.getInt((new StringBuilder()).append("ro.boottime.init.mount_all.").append(s).toString(), 0);
            if(k != 0)
                MetricsLogger.histogram(null, (new StringBuilder()).append("boot_mount_all_duration_").append(s).toString(), k);
        }

    }

    private static void logFsShutdownTime()
    {
        Object obj = null;
        String as[] = LAST_KMSG_FILES;
        int i = 0;
        int j = as.length;
        Object obj1;
        do
        {
            obj1 = obj;
            if(i >= j)
                break;
            obj1 = new File(as[i]);
            if(((File) (obj1)).exists())
                break;
            i++;
        } while(true);
        if(obj1 == null)
            return;
        try
        {
            obj1 = FileUtils.readTextFile(((File) (obj1)), -16384, null);
        }
        catch(IOException ioexception)
        {
            Slog.w("BootReceiver", "cannot read last msg", ioexception);
            return;
        }
        obj1 = Pattern.compile("powerctl_shutdown_time_ms:([0-9]+):([0-9]+)", 8).matcher(((CharSequence) (obj1)));
        if(((Matcher) (obj1)).find())
        {
            MetricsLogger.histogram(null, "boot_fs_shutdown_duration", Integer.parseInt(((Matcher) (obj1)).group(1)));
            MetricsLogger.histogram(null, "boot_fs_shutdown_umount_stat", Integer.parseInt(((Matcher) (obj1)).group(2)));
            Slog.i("BootReceiver", (new StringBuilder()).append("boot_fs_shutdown,").append(((Matcher) (obj1)).group(1)).append(",").append(((Matcher) (obj1)).group(2)).toString());
        } else
        {
            MetricsLogger.histogram(null, "boot_fs_shutdown_umount_stat", 4);
            Slog.w("BootReceiver", "boot_fs_shutdown, string not found");
        }
    }

    private static void logSystemServerShutdownTimeMetrics()
    {
        File file = new File("/data/system/shutdown-metrics.txt");
        String as[] = null;
        Object obj = as;
        if(file.exists())
            try
            {
                obj = FileUtils.readTextFile(file, 0, null);
            }
            catch(IOException ioexception)
            {
                Slog.e("BootReceiver", (new StringBuilder()).append("Problem reading ").append(file).toString(), ioexception);
                ioexception = as;
            }
        if(!TextUtils.isEmpty(((CharSequence) (obj))))
        {
            as = ((String) (obj)).split(",");
            int i = as.length;
            int j = 0;
            do
            {
                if(j >= i)
                    break;
                String as1[] = as[j].split(":");
                if(as1.length != 2)
                    Slog.e("BootReceiver", (new StringBuilder()).append("Wrong format of shutdown metrics - ").append(((String) (obj))).toString());
                else
                if(as1[0].startsWith("shutdown_"))
                    logTronShutdownMetric(as1[0], as1[1]);
                j++;
            } while(true);
        }
        file.delete();
    }

    private static void logTronShutdownMetric(String s, String s1)
    {
        int i;
        try
        {
            i = Integer.parseInt(s1);
        }
        catch(NumberFormatException numberformatexception)
        {
            Slog.e("BootReceiver", (new StringBuilder()).append("Cannot parse metric ").append(s).append(" int value - ").append(s1).toString());
            return;
        }
        if(i >= 0)
            MetricsLogger.histogram(null, s, i);
    }

    private static HashMap readTimestamps()
    {
        AtomicFile atomicfile = sFile;
        atomicfile;
        JVM INSTR monitorenter ;
        HashMap hashmap;
        hashmap = JVM INSTR new #181 <Class HashMap>;
        hashmap.HashMap();
        boolean flag;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        boolean flag4;
        boolean flag5;
        IllegalStateException illegalstateexception;
        Object obj;
        Object obj1;
        Object obj3;
        flag = false;
        flag1 = false;
        flag2 = false;
        flag3 = false;
        flag4 = false;
        flag5 = false;
        illegalstateexception = null;
        obj = null;
        obj1 = null;
        obj3 = null;
        Object obj5 = sFile.openRead();
        obj3 = obj5;
        obj1 = obj5;
        XmlPullParser xmlpullparser = Xml.newPullParser();
        obj3 = obj5;
        obj1 = obj5;
        xmlpullparser.setInput(((java.io.InputStream) (obj5)), StandardCharsets.UTF_8.name());
_L2:
        obj3 = obj5;
        obj1 = obj5;
        int i = xmlpullparser.next();
        if(i != 2 && i != 1) goto _L2; else goto _L1
_L1:
        if(i == 2) goto _L4; else goto _L3
_L3:
        obj3 = obj5;
        obj1 = obj5;
        illegalstateexception = JVM INSTR new #595 <Class IllegalStateException>;
        obj3 = obj5;
        obj1 = obj5;
        illegalstateexception.IllegalStateException("no start tag found");
        obj3 = obj5;
        obj1 = obj5;
        try
        {
            throw illegalstateexception;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj1) { }
        throw obj1;
        obj5;
_L11:
        boolean flag6;
        boolean flag7;
        boolean flag8;
        int j;
        boolean flag9;
        obj = obj1;
        if(obj3 == null)
            break MISSING_BLOCK_LABEL_208;
        flag6 = flag5;
        flag7 = flag;
        flag8 = flag1;
        j = ((flag2) ? 1 : 0);
        flag9 = flag3;
        i = ((flag4) ? 1 : 0);
        ((FileInputStream) (obj3)).close();
        obj = obj1;
_L18:
        if(obj == null) goto _L6; else goto _L5
_L5:
        flag6 = flag5;
        flag7 = flag;
        flag8 = flag1;
        j = ((flag2) ? 1 : 0);
        flag9 = flag3;
        i = ((flag4) ? 1 : 0);
        throw obj;
        obj3;
        i = ((flag6) ? 1 : 0);
        obj3 = JVM INSTR new #212 <Class StringBuilder>;
        i = ((flag6) ? 1 : 0);
        ((StringBuilder) (obj3)).StringBuilder();
        i = ((flag6) ? 1 : 0);
        Slog.i("BootReceiver", ((StringBuilder) (obj3)).append("No existing last log timestamp file ").append(sFile.getBaseFile()).append("; starting empty").toString());
        if(flag6)
            break MISSING_BLOCK_LABEL_303;
        hashmap.clear();
_L15:
        atomicfile;
        JVM INSTR monitorexit ;
        return hashmap;
_L4:
        obj3 = obj5;
        obj1 = obj5;
        i = xmlpullparser.getDepth();
_L10:
        obj3 = obj5;
        obj1 = obj5;
        j = xmlpullparser.next();
        if(j == 1)
            break MISSING_BLOCK_LABEL_546;
        if(j != 3) goto _L8; else goto _L7
_L7:
        obj3 = obj5;
        obj1 = obj5;
        if(xmlpullparser.getDepth() <= i)
            break MISSING_BLOCK_LABEL_546;
_L8:
        if(j == 3 || j == 4) goto _L10; else goto _L9
_L9:
        obj3 = obj5;
        obj1 = obj5;
        if(!xmlpullparser.getName().equals("log"))
            break MISSING_BLOCK_LABEL_469;
        obj3 = obj5;
        obj1 = obj5;
        hashmap.put(xmlpullparser.getAttributeValue(null, "filename"), Long.valueOf(Long.valueOf(xmlpullparser.getAttributeValue(null, "timestamp")).longValue()));
          goto _L10
        obj5;
        obj3 = obj1;
        obj1 = obj;
          goto _L11
        obj3 = obj5;
        obj1 = obj5;
        StringBuilder stringbuilder = JVM INSTR new #212 <Class StringBuilder>;
        obj3 = obj5;
        obj1 = obj5;
        stringbuilder.StringBuilder();
        obj3 = obj5;
        obj1 = obj5;
        Slog.w("BootReceiver", stringbuilder.append("Unknown tag: ").append(xmlpullparser.getName()).toString());
        obj3 = obj5;
        obj1 = obj5;
        XmlUtils.skipCurrentTag(xmlpullparser);
          goto _L10
        flag = true;
        flag1 = true;
        flag2 = true;
        flag3 = true;
        flag4 = true;
        flag5 = true;
        obj3 = illegalstateexception;
        if(obj5 == null)
            break MISSING_BLOCK_LABEL_602;
        flag6 = flag5;
        flag7 = flag;
        flag8 = flag1;
        j = ((flag2) ? 1 : 0);
        flag9 = flag3;
        i = ((flag4) ? 1 : 0);
        ((FileInputStream) (obj5)).close();
        obj3 = illegalstateexception;
_L16:
        if(obj3 == null) goto _L13; else goto _L12
_L12:
        flag6 = flag5;
        flag7 = flag;
        flag8 = flag1;
        j = ((flag2) ? 1 : 0);
        flag9 = flag3;
        i = ((flag4) ? 1 : 0);
        throw obj3;
        Object obj4;
        obj4;
        i = ((flag7) ? 1 : 0);
        obj1 = JVM INSTR new #212 <Class StringBuilder>;
        i = ((flag7) ? 1 : 0);
        ((StringBuilder) (obj1)).StringBuilder();
        i = ((flag7) ? 1 : 0);
        Slog.w("BootReceiver", ((StringBuilder) (obj1)).append("Failed parsing ").append(obj4).toString());
        if(flag7) goto _L15; else goto _L14
_L14:
        hashmap.clear();
          goto _L15
        obj4;
        throw obj4;
        obj4;
          goto _L16
        obj4;
label0:
        {
            if(obj1 != null)
                break label0;
            obj = obj4;
        }
        if(true) goto _L18; else goto _L17
_L17:
        obj = obj1;
        if(obj1 == obj4) goto _L18; else goto _L19
_L19:
        flag6 = flag5;
        flag7 = flag;
        flag8 = flag1;
        j = ((flag2) ? 1 : 0);
        flag9 = flag3;
        i = ((flag4) ? 1 : 0);
        ((Throwable) (obj1)).addSuppressed(((Throwable) (obj4)));
        obj = obj1;
          goto _L18
        Object obj2;
        obj2;
        i = ((flag8) ? 1 : 0);
        obj4 = JVM INSTR new #212 <Class StringBuilder>;
        i = ((flag8) ? 1 : 0);
        ((StringBuilder) (obj4)).StringBuilder();
        i = ((flag8) ? 1 : 0);
        Slog.w("BootReceiver", ((StringBuilder) (obj4)).append("Failed parsing ").append(obj2).toString());
        if(flag8) goto _L15; else goto _L20
_L20:
        hashmap.clear();
          goto _L15
_L6:
        flag6 = flag5;
        flag7 = flag;
        flag8 = flag1;
        j = ((flag2) ? 1 : 0);
        flag9 = flag3;
        i = ((flag4) ? 1 : 0);
        throw obj5;
        obj2;
        i = j;
        obj4 = JVM INSTR new #212 <Class StringBuilder>;
        i = j;
        ((StringBuilder) (obj4)).StringBuilder();
        i = j;
        Slog.w("BootReceiver", ((StringBuilder) (obj4)).append("Failed parsing ").append(obj2).toString());
        if(j != 0) goto _L15; else goto _L21
_L21:
        hashmap.clear();
          goto _L15
_L13:
        if(true) goto _L15; else goto _L22
_L22:
        hashmap.clear();
          goto _L15
        obj4;
        i = ((flag9) ? 1 : 0);
        obj2 = JVM INSTR new #212 <Class StringBuilder>;
        i = ((flag9) ? 1 : 0);
        ((StringBuilder) (obj2)).StringBuilder();
        i = ((flag9) ? 1 : 0);
        Slog.w("BootReceiver", ((StringBuilder) (obj2)).append("Failed parsing ").append(obj4).toString());
        if(flag9) goto _L15; else goto _L23
_L23:
        hashmap.clear();
          goto _L15
        obj4;
        if(i != 0)
            break MISSING_BLOCK_LABEL_984;
        hashmap.clear();
        throw obj4;
          goto _L11
    }

    private void removeOldUpdatePackages(Context context)
    {
        Downloads.removeAllDownloadsByPackage(context, "com.google.android.systemupdater", "com.google.android.systemupdater.SystemUpdateReceiver");
    }

    private void writeTimestamps(HashMap hashmap)
    {
        AtomicFile atomicfile = sFile;
        atomicfile;
        JVM INSTR monitorenter ;
        Object obj = sFile.startWrite();
        Object obj1;
        try
        {
            obj1 = JVM INSTR new #693 <Class FastXmlSerializer>;
            ((FastXmlSerializer) (obj1)).FastXmlSerializer();
            ((XmlSerializer) (obj1)).setOutput(((java.io.OutputStream) (obj)), StandardCharsets.UTF_8.name());
            ((XmlSerializer) (obj1)).startDocument(null, Boolean.valueOf(true));
            ((XmlSerializer) (obj1)).startTag(null, "log-files");
            for(Iterator iterator = hashmap.keySet().iterator(); iterator.hasNext(); ((XmlSerializer) (obj1)).endTag(null, "log"))
            {
                String s = (String)iterator.next();
                ((XmlSerializer) (obj1)).startTag(null, "log");
                ((XmlSerializer) (obj1)).attribute(null, "filename", s);
                ((XmlSerializer) (obj1)).attribute(null, "timestamp", ((Long)hashmap.get(s)).toString());
            }

            break MISSING_BLOCK_LABEL_232;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj1) { }
        hashmap = JVM INSTR new #212 <Class StringBuilder>;
        hashmap.StringBuilder();
        Slog.w("BootReceiver", hashmap.append("Failed to write timestamp file, using the backup: ").append(obj1).toString());
        sFile.failWrite(((java.io.FileOutputStream) (obj)));
_L1:
        atomicfile;
        JVM INSTR monitorexit ;
        return;
        obj;
        hashmap = JVM INSTR new #212 <Class StringBuilder>;
        hashmap.StringBuilder();
        Slog.w("BootReceiver", hashmap.append("Failed to write timestamp file: ").append(obj).toString());
        atomicfile;
        JVM INSTR monitorexit ;
        return;
        ((XmlSerializer) (obj1)).endTag(null, "log-files");
        ((XmlSerializer) (obj1)).endDocument();
        sFile.finishWrite(((java.io.FileOutputStream) (obj)));
          goto _L1
        hashmap;
        throw hashmap;
    }

    public void onReceive(final Context context, Intent intent)
    {
        (new Thread() {

            public void run()
            {
                boolean flag;
                boolean flag1;
                try
                {
                    BootReceiver._2D_wrap2(BootReceiver.this, context);
                }
                catch(Exception exception)
                {
                    Slog.e("BootReceiver", "Can't log boot events", exception);
                }
                flag = false;
                flag1 = android.content.pm.IPackageManager.Stub.asInterface(ServiceManager.getService("package")).isOnlyCoreApps();
                flag = flag1;
_L3:
                if(flag)
                    break MISSING_BLOCK_LABEL_44;
                BootReceiver._2D_wrap3(BootReceiver.this, context);
_L1:
                return;
                Object obj;
                obj;
                Slog.e("BootReceiver", "Can't remove old update packages", ((Throwable) (obj)));
                  goto _L1
                obj;
                if(true) goto _L3; else goto _L2
_L2:
            }

            final BootReceiver this$0;
            final Context val$context;

            
            {
                this$0 = BootReceiver.this;
                context = context1;
                super();
            }
        }
).start();
    }

    private static final String FSCK_FS_MODIFIED = "FILE SYSTEM WAS MODIFIED";
    private static final String FSCK_PASS_PATTERN = "Pass ([1-9]E?):";
    private static final String FSCK_TREE_OPTIMIZATION_PATTERN = "Inode [0-9]+ extent tree.*could be shorter";
    private static final int FS_STAT_FS_FIXED = 1024;
    private static final String FS_STAT_PATTERN = "fs_stat,[^,]*/([^/,]+),(0x[0-9a-fA-F]+)";
    private static final int FS_STAT_UNCLEAN_SHUTDOWN = 8;
    private static final String LAST_HEADER_FILE = "last-header.txt";
    private static final String LAST_KMSG_FILES[] = {
        "/sys/fs/pstore/console-ramoops", "/proc/last_kmsg"
    };
    private static final String LAST_SHUTDOWN_TIME_PATTERN = "powerctl_shutdown_time_ms:([0-9]+):([0-9]+)";
    private static final String LOG_FILES_FILE = "log-files.xml";
    private static final int LOG_SIZE;
    private static final String MOUNT_DURATION_PROPS_POSTFIX[] = {
        "early", "default", "late"
    };
    private static final String OLD_UPDATER_CLASS = "com.google.android.systemupdater.SystemUpdateReceiver";
    private static final String OLD_UPDATER_PACKAGE = "com.google.android.systemupdater";
    private static final String SHUTDOWN_METRICS_FILE = "/data/system/shutdown-metrics.txt";
    private static final String SHUTDOWN_TRON_METRICS_PREFIX = "shutdown_";
    private static final String TAG = "BootReceiver";
    private static final File TOMBSTONE_DIR = new File("/data/tombstones");
    private static final int UMOUNT_STATUS_NOT_AVAILABLE = 4;
    private static final File lastHeaderFile = new File(Environment.getDataSystemDirectory(), "last-header.txt");
    private static final AtomicFile sFile = new AtomicFile(new File(Environment.getDataSystemDirectory(), "log-files.xml"));
    private static FileObserver sTombstoneObserver = null;

    static 
    {
        int i;
        if(SystemProperties.getInt("ro.debuggable", 0) == 1)
            i = 0x18000;
        else
            i = 0x10000;
        LOG_SIZE = i;
    }
}
