// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.app.PendingIntent;
import android.content.*;
import android.content.pm.PackageManager;
import android.telephony.euicc.EuiccManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.android.internal.logging.MetricsLogger;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.SignatureException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.zip.*;
import libcore.io.Streams;
import sun.security.pkcs.PKCS7;

// Referenced classes of package android.os:
//            RemoteException, IRecoverySystem, FileUtils, PowerManager, 
//            Handler, UserManager, ConditionVariable, UserHandle, 
//            VintfObject, HandlerThread, IRecoverySystemProgressListener

public class RecoverySystem
{
    public static interface ProgressListener
    {

        public abstract void onProgress(int i);
    }


    public RecoverySystem()
    {
        mService = null;
    }

    public RecoverySystem(IRecoverySystem irecoverysystem)
    {
        mService = irecoverysystem;
    }

    private static transient void bootCommand(Context context, String as[])
        throws IOException
    {
        LOG_FILE.delete();
        StringBuilder stringbuilder = new StringBuilder();
        int i = 0;
        for(int j = as.length; i < j; i++)
        {
            String s = as[i];
            if(!TextUtils.isEmpty(s))
            {
                stringbuilder.append(s);
                stringbuilder.append("\n");
            }
        }

        ((RecoverySystem)context.getSystemService("recovery")).rebootRecoveryWithCommand(stringbuilder.toString());
        throw new IOException("Reboot failed (no permissions?)");
    }

    public static void cancelScheduledUpdate(Context context)
        throws IOException
    {
        if(!((RecoverySystem)context.getSystemService("recovery")).clearBcb())
            throw new IOException("cancel scheduled update failed");
        else
            return;
    }

    private boolean clearBcb()
    {
        boolean flag;
        try
        {
            flag = mService.clearBcb();
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return flag;
    }

    private static HashSet getTrustedCerts(File file)
        throws IOException, GeneralSecurityException
    {
        Object obj;
        obj = new HashSet();
        File file1 = file;
        if(file == null)
            file1 = DEFAULT_KEYSTORE;
        file = new ZipFile(file1);
        CertificateFactory certificatefactory;
        Enumeration enumeration;
        certificatefactory = CertificateFactory.getInstance("X.509");
        enumeration = file.entries();
_L1:
        Object obj1;
        if(!enumeration.hasMoreElements())
            break MISSING_BLOCK_LABEL_98;
        obj1 = file.getInputStream((ZipEntry)enumeration.nextElement());
        ((HashSet) (obj)).add((X509Certificate)certificatefactory.generateCertificate(((InputStream) (obj1))));
        ((InputStream) (obj1)).close();
          goto _L1
        obj1;
        file.close();
        throw obj1;
        obj;
        ((InputStream) (obj1)).close();
        throw obj;
        file.close();
        return ((HashSet) (obj));
    }

    public static String handleAftermath(Context context)
    {
        String s;
        boolean flag;
        s = null;
        String s1;
        try
        {
            s1 = FileUtils.readTextFile(LOG_FILE, 0xffff0000, "...\n");
        }
        catch(FileNotFoundException filenotfoundexception)
        {
            Log.i("RecoverySystem", "No recovery log file");
            continue; /* Loop/switch isn't completed */
        }
        catch(IOException ioexception)
        {
            Log.e("RecoverySystem", "Error reading recovery log", ioexception);
            continue; /* Loop/switch isn't completed */
        }
        s = s1;
_L5:
        if(s != null)
            parseLastInstallLog(context);
        flag = BLOCK_MAP_FILE.exists();
        if(flag || !UNCRYPT_PACKAGE_FILE.exists()) goto _L2; else goto _L1
_L1:
        context = null;
        s1 = FileUtils.readTextFile(UNCRYPT_PACKAGE_FILE, 0, null);
        context = s1;
_L3:
        if(context != null && context.startsWith("/data"))
            if(UNCRYPT_PACKAGE_FILE.delete())
                Log.i("RecoverySystem", (new StringBuilder()).append("Deleted: ").append(context).toString());
            else
                Log.e("RecoverySystem", (new StringBuilder()).append("Can't delete: ").append(context).toString());
          goto _L2
        ioexception1;
        Log.e("RecoverySystem", "Error reading uncrypt file", ioexception1);
          goto _L3
_L2:
        context = RECOVERY_DIR.list();
        int i = 0;
        do
        {
            if(context == null || i >= context.length)
                break;
            IOException ioexception1;
            if(!context[i].startsWith("last_") && (!flag || !context[i].equals(BLOCK_MAP_FILE.getName())) && (!flag || !context[i].equals(UNCRYPT_PACKAGE_FILE.getName())))
                recursiveDelete(new File(RECOVERY_DIR, context[i]));
            i++;
        } while(true);
        return s;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public static void installPackage(Context context, File file)
        throws IOException
    {
        installPackage(context, file, false);
    }

    public static void installPackage(Context context, File file, boolean flag)
        throws IOException
    {
        Object obj = sRequestLock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1;
        boolean flag1;
        LOG_FILE.delete();
        UNCRYPT_PACKAGE_FILE.delete();
        obj1 = file.getCanonicalPath();
        file = JVM INSTR new #112 <Class StringBuilder>;
        file.StringBuilder();
        Log.w("RecoverySystem", file.append("!!! REBOOTING TO INSTALL ").append(((String) (obj1))).append(" !!!").toString());
        flag1 = ((String) (obj1)).endsWith("_s.zip");
        file = ((File) (obj1));
        if(!((String) (obj1)).startsWith("/data/"))
            break MISSING_BLOCK_LABEL_234;
        if(!flag)
            break MISSING_BLOCK_LABEL_125;
        if(!BLOCK_MAP_FILE.exists())
        {
            Log.e("RecoverySystem", "Package claimed to have been processed but failed to find the block map file.");
            context = JVM INSTR new #106 <Class IOException>;
            context.IOException("Failed to find block map file");
            throw context;
        }
        break MISSING_BLOCK_LABEL_230;
        context;
        obj;
        JVM INSTR monitorexit ;
        throw context;
        file = JVM INSTR new #303 <Class FileWriter>;
        file.FileWriter(UNCRYPT_PACKAGE_FILE);
        StringBuilder stringbuilder = JVM INSTR new #112 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        file.write(stringbuilder.append(((String) (obj1))).append("\n").toString());
        file.close();
        if(!UNCRYPT_PACKAGE_FILE.setReadable(true, false) || UNCRYPT_PACKAGE_FILE.setWritable(true, false) ^ true)
        {
            file = JVM INSTR new #112 <Class StringBuilder>;
            file.StringBuilder();
            Log.e("RecoverySystem", file.append("Error setting permission for ").append(UNCRYPT_PACKAGE_FILE).toString());
        }
        BLOCK_MAP_FILE.delete();
        file = "@/cache/recovery/block.map";
        obj1 = JVM INSTR new #112 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        file = ((StringBuilder) (obj1)).append("--update_package=").append(file).append("\n").toString();
        obj1 = JVM INSTR new #112 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        obj1 = ((StringBuilder) (obj1)).append("--locale=").append(Locale.getDefault().toLanguageTag()).append("\n").toString();
        StringBuilder stringbuilder1 = JVM INSTR new #112 <Class StringBuilder>;
        stringbuilder1.StringBuilder();
        obj1 = stringbuilder1.append(file).append(((String) (obj1))).toString();
        file = ((File) (obj1));
        if(!flag1)
            break MISSING_BLOCK_LABEL_360;
        file = JVM INSTR new #112 <Class StringBuilder>;
        file.StringBuilder();
        file = file.append(((String) (obj1))).append("--security\n").toString();
        if(!((RecoverySystem)context.getSystemService("recovery")).setupBcb(file))
        {
            context = JVM INSTR new #106 <Class IOException>;
            context.IOException("Setup BCB failed");
            throw context;
        }
        break MISSING_BLOCK_LABEL_396;
        context;
        file.close();
        throw context;
        PowerManager powermanager = (PowerManager)context.getSystemService("power");
        obj1 = "recovery-update";
        file = ((File) (obj1));
        if(!context.getPackageManager().hasSystemFeature("android.software.leanback"))
            break MISSING_BLOCK_LABEL_479;
        file = ((File) (obj1));
        if(((WindowManager)context.getSystemService("window")).getDefaultDisplay().getState() != 2)
        {
            context = JVM INSTR new #112 <Class StringBuilder>;
            context.StringBuilder();
            file = context.append("recovery-update").append(",quiescent").toString();
        }
        powermanager.reboot(file);
        context = JVM INSTR new #106 <Class IOException>;
        context.IOException("Reboot failed (no permissions?)");
        throw context;
    }

    public static void installPackage(Context context, File file, boolean flag, String s, boolean flag1)
        throws IOException
    {
        Object obj = sRequestLock;
        obj;
        JVM INSTR monitorenter ;
        String s1;
        boolean flag2;
        LOG_FILE.delete();
        UNCRYPT_PACKAGE_FILE.delete();
        s1 = file.getCanonicalPath();
        file = JVM INSTR new #112 <Class StringBuilder>;
        file.StringBuilder();
        Log.w("RecoverySystem", file.append("!!! REBOOTING TO INSTALL ").append(s1).append(" !!!").toString());
        flag2 = s1.endsWith("_s.zip");
        file = s1;
        if(!s1.startsWith("/data/"))
            break MISSING_BLOCK_LABEL_237;
        if(!flag)
            break MISSING_BLOCK_LABEL_128;
        if(!BLOCK_MAP_FILE.exists())
        {
            Log.e("RecoverySystem", "Package claimed to have been processed but failed to find the block map file.");
            context = JVM INSTR new #106 <Class IOException>;
            context.IOException("Failed to find block map file");
            throw context;
        }
        break MISSING_BLOCK_LABEL_233;
        context;
        obj;
        JVM INSTR monitorexit ;
        throw context;
        file = JVM INSTR new #303 <Class FileWriter>;
        file.FileWriter(UNCRYPT_PACKAGE_FILE);
        StringBuilder stringbuilder = JVM INSTR new #112 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        file.write(stringbuilder.append(s1).append("\n").toString());
        file.close();
        if(!UNCRYPT_PACKAGE_FILE.setReadable(true, false) || UNCRYPT_PACKAGE_FILE.setWritable(true, false) ^ true)
        {
            file = JVM INSTR new #112 <Class StringBuilder>;
            file.StringBuilder();
            Log.e("RecoverySystem", file.append("Error setting permission for ").append(UNCRYPT_PACKAGE_FILE).toString());
        }
        BLOCK_MAP_FILE.delete();
        file = "@/cache/recovery/block.map";
        Object obj1 = JVM INSTR new #112 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        file = ((StringBuilder) (obj1)).append("--update_package=").append(file).append("\n").toString();
        obj1 = JVM INSTR new #112 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        obj1 = ((StringBuilder) (obj1)).append("--locale=").append(Locale.getDefault().toString()).append("\n").toString();
        StringBuilder stringbuilder1 = JVM INSTR new #112 <Class StringBuilder>;
        stringbuilder1.StringBuilder();
        s = stringbuilder1.append("--export_validate=").append(s).append("\n").toString();
        stringbuilder1 = JVM INSTR new #112 <Class StringBuilder>;
        stringbuilder1.StringBuilder();
        s = stringbuilder1.append(file).append(((String) (obj1))).append(s).toString();
        file = s;
        if(!flag1)
            break MISSING_BLOCK_LABEL_395;
        file = JVM INSTR new #112 <Class StringBuilder>;
        file.StringBuilder();
        file = file.append(s).append("--update_wipe\n").toString();
        s = file;
        if(!flag2)
            break MISSING_BLOCK_LABEL_425;
        s = JVM INSTR new #112 <Class StringBuilder>;
        s.StringBuilder();
        s = s.append(file).append("--security\n").toString();
        if(!((RecoverySystem)context.getSystemService("recovery")).setupBcb(s))
        {
            context = JVM INSTR new #106 <Class IOException>;
            context.IOException("Setup BCB failed");
            throw context;
        }
        break MISSING_BLOCK_LABEL_461;
        context;
        file.close();
        throw context;
        ((PowerManager)context.getSystemService("power")).reboot("recovery-update");
        context = JVM INSTR new #106 <Class IOException>;
        context.IOException("Reboot failed (no permissions?)");
        throw context;
    }

    private static void parseLastInstallLog(Context context)
    {
        Object obj;
        Context context1;
        Object obj1;
        String s;
        obj = null;
        context1 = null;
        obj1 = null;
        s = null;
        Object obj2;
        obj2 = JVM INSTR new #392 <Class BufferedReader>;
        FileReader filereader = JVM INSTR new #394 <Class FileReader>;
        filereader.FileReader(LAST_INSTALL_FILE);
        ((BufferedReader) (obj2)).BufferedReader(filereader);
        int i;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        i = -1;
        j = -1;
        k = -1;
        l = -1;
        i1 = -1;
        j1 = -1;
        k1 = -1;
        l1 = -1;
        i2 = -1;
        j2 = -1;
_L4:
        s = ((BufferedReader) (obj2)).readLine();
        if(s == null) goto _L2; else goto _L1
_L1:
        int k2 = s.indexOf(':');
        if(k2 == -1) goto _L4; else goto _L3
_L3:
        if(k2 + 1 >= s.length()) goto _L4; else goto _L5
_L5:
        obj1 = s.substring(k2 + 1).trim();
        long l2 = Long.parseLong(((String) (obj1)));
        if(!s.startsWith("bytes")) goto _L7; else goto _L6
_L6:
        k2 = Math.toIntExact(l2 / 0x100000L);
_L12:
        if(!s.startsWith("time")) goto _L9; else goto _L8
_L8:
        k = k2;
          goto _L4
        obj1;
        obj1 = JVM INSTR new #112 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        Log.e("RecoverySystem", ((StringBuilder) (obj1)).append("Failed to parse numbers in ").append(s).toString());
          goto _L4
        context;
_L24:
        throw context;
        obj;
        context1 = context;
        context = ((Context) (obj));
_L13:
        obj = context1;
        if(obj2 == null)
            break MISSING_BLOCK_LABEL_217;
        ((BufferedReader) (obj2)).close();
        obj = context1;
_L22:
        if(obj == null) goto _L11; else goto _L10
_L10:
        try
        {
            throw obj;
        }
        // Misplaced declaration of an exception variable
        catch(Context context) { }
_L21:
        Log.e("RecoverySystem", "Failed to read lines in last_install", context);
_L20:
        return;
_L7:
        k2 = Math.toIntExact(l2);
          goto _L12
        obj1;
        obj1 = JVM INSTR new #112 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        Log.e("RecoverySystem", ((StringBuilder) (obj1)).append("Number overflows in ").append(s).toString());
          goto _L4
        context;
          goto _L13
_L9:
        if(!s.startsWith("uncrypt_time"))
            break MISSING_BLOCK_LABEL_300;
        l = k2;
          goto _L4
        if(!s.startsWith("source_build"))
            break MISSING_BLOCK_LABEL_318;
        i1 = k2;
          goto _L4
        if(!s.startsWith("bytes_written"))
            break MISSING_BLOCK_LABEL_352;
        if(i == -1)
            i = k2;
        else
            i += k2;
          goto _L4
        if(!s.startsWith("bytes_stashed"))
            break MISSING_BLOCK_LABEL_386;
        if(j == -1)
            j = k2;
        else
            j += k2;
          goto _L4
        if(!s.startsWith("temperature_start"))
            break MISSING_BLOCK_LABEL_404;
        j1 = k2;
          goto _L4
        if(!s.startsWith("temperature_end"))
            break MISSING_BLOCK_LABEL_422;
        k1 = k2;
          goto _L4
        if(!s.startsWith("temperature_max"))
            break MISSING_BLOCK_LABEL_440;
        l1 = k2;
          goto _L4
        if(!s.startsWith("error")) goto _L15; else goto _L14
_L14:
        i2 = k2;
          goto _L4
_L15:
        if(!s.startsWith("cause")) goto _L4; else goto _L16
_L16:
        j2 = k2;
          goto _L4
_L2:
        if(k == -1)
            break MISSING_BLOCK_LABEL_491;
        MetricsLogger.histogram(context, "ota_time_total", k);
        if(l == -1)
            break MISSING_BLOCK_LABEL_506;
        MetricsLogger.histogram(context, "ota_uncrypt_time", l);
        if(i1 == -1)
            break MISSING_BLOCK_LABEL_521;
        MetricsLogger.histogram(context, "ota_source_version", i1);
        if(i == -1)
            break MISSING_BLOCK_LABEL_536;
        MetricsLogger.histogram(context, "ota_written_in_MiBs", i);
        if(j == -1)
            break MISSING_BLOCK_LABEL_551;
        MetricsLogger.histogram(context, "ota_stashed_in_MiBs", j);
        if(j1 == -1)
            break MISSING_BLOCK_LABEL_566;
        MetricsLogger.histogram(context, "ota_temperature_start", j1);
        if(k1 == -1)
            break MISSING_BLOCK_LABEL_581;
        MetricsLogger.histogram(context, "ota_temperature_end", k1);
        if(l1 == -1)
            break MISSING_BLOCK_LABEL_596;
        MetricsLogger.histogram(context, "ota_temperature_max", l1);
        if(i2 == -1)
            break MISSING_BLOCK_LABEL_611;
        MetricsLogger.histogram(context, "ota_non_ab_error_code", i2);
        if(j2 == -1)
            break MISSING_BLOCK_LABEL_626;
        MetricsLogger.histogram(context, "ota_non_ab_cause_code", j2);
        context = ((Context) (obj));
        if(obj2 == null) goto _L18; else goto _L17
_L17:
        ((BufferedReader) (obj2)).close();
        context = ((Context) (obj));
_L18:
        if(context == null) goto _L20; else goto _L19
_L19:
        try
        {
            throw context;
        }
        // Misplaced declaration of an exception variable
        catch(Context context) { }
          goto _L21
        context;
          goto _L18
        obj2;
label0:
        {
            if(context1 != null)
                break label0;
            obj = obj2;
        }
          goto _L22
        obj = context1;
        if(context1 == obj2) goto _L22; else goto _L23
_L23:
        context1.addSuppressed(((Throwable) (obj2)));
        obj = context1;
          goto _L22
_L11:
        throw context;
        context;
        obj2 = obj1;
          goto _L13
        context;
        obj2 = s;
          goto _L24
    }

    public static void processPackage(Context context, File file, ProgressListener progresslistener)
        throws IOException
    {
        processPackage(context, file, progresslistener, null);
    }

    public static void processPackage(Context context, File file, ProgressListener progresslistener, Handler handler)
        throws IOException
    {
        String s = file.getCanonicalPath();
        if(!s.startsWith("/data/"))
            return;
        RecoverySystem recoverysystem = (RecoverySystem)context.getSystemService("recovery");
        file = null;
        if(progresslistener != null)
        {
            if(handler != null)
                context = handler;
            else
                context = new Handler(context.getMainLooper());
            file = new IRecoverySystemProgressListener.Stub(context, progresslistener) {

                public void onProgress(final int progress)
                {
                    final long now = System.currentTimeMillis();
                    progressHandler.post(listener. new Runnable() {

                        public void run()
                        {
                            if(progress > lastProgress && now - lastPublishTime > 500L)
                            {
                                lastProgress = progress;
                                lastPublishTime = now;
                                listener.onProgress(progress);
                            }
                        }

                        final _cls2 this$1;
                        final ProgressListener val$listener;
                        final long val$now;
                        final int val$progress;

            
            {
                this$1 = final__pcls2;
                progress = i;
                now = l;
                listener = ProgressListener.this;
                super();
            }
                    }
);
                }

                int lastProgress;
                long lastPublishTime;
                final ProgressListener val$listener;
                final Handler val$progressHandler;

            
            {
                progressHandler = handler;
                listener = progresslistener;
                super();
                lastProgress = 0;
                lastPublishTime = System.currentTimeMillis();
            }
            }
;
        }
        if(!recoverysystem.uncrypt(s, file))
            throw new IOException("process package failed");
        else
            return;
    }

    private static boolean readAndVerifyPackageCompatibilityEntry(File file)
        throws IOException
    {
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        Object obj4;
        obj = null;
        obj1 = null;
        obj2 = null;
        obj3 = null;
        obj4 = null;
        Object obj6;
        obj6 = JVM INSTR new #165 <Class ZipFile>;
        ((ZipFile) (obj6)).ZipFile(file);
        file = ((ZipFile) (obj6)).getEntry("compatibility.zip");
        if(file != null)
            break MISSING_BLOCK_LABEL_62;
        file = obj2;
        if(obj6 == null)
            break MISSING_BLOCK_LABEL_50;
        ((ZipFile) (obj6)).close();
        file = obj2;
_L1:
        if(file != null)
            throw file;
        else
            return true;
        file;
          goto _L1
        boolean flag = verifyPackageCompatibility(((ZipFile) (obj6)).getInputStream(file));
        file = obj;
        if(obj6 == null)
            break MISSING_BLOCK_LABEL_87;
        ((ZipFile) (obj6)).close();
        file = obj;
_L2:
        if(file != null)
            throw file;
        else
            return flag;
        file;
          goto _L2
        obj6;
        file = ((File) (obj4));
_L7:
        throw obj6;
        obj1;
        obj4 = obj6;
        obj6 = obj1;
_L5:
        obj1 = obj4;
        if(file == null)
            break MISSING_BLOCK_LABEL_130;
        file.close();
        obj1 = obj4;
_L3:
        if(obj1 != null)
            throw obj1;
        else
            throw obj6;
        file;
        if(obj4 == null)
        {
            obj1 = file;
        } else
        {
            obj1 = obj4;
            if(obj4 != file)
            {
                ((Throwable) (obj4)).addSuppressed(file);
                obj1 = obj4;
            }
        }
          goto _L3
        obj6;
        file = obj3;
        obj4 = obj1;
        continue; /* Loop/switch isn't completed */
        Object obj5;
        obj5;
        file = ((File) (obj6));
        obj6 = obj5;
        obj5 = obj1;
        if(true) goto _L5; else goto _L4
_L4:
        obj5;
        file = ((File) (obj6));
        obj6 = obj5;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public static void rebootPromptAndWipeUserData(Context context, String s)
        throws IOException
    {
        String s1 = null;
        if(!TextUtils.isEmpty(s))
            s1 = (new StringBuilder()).append("--reason=").append(sanitizeArg(s)).toString();
        bootCommand(context, new String[] {
            null, "--prompt_and_wipe_data", s1, (new StringBuilder()).append("--locale=").append(Locale.getDefault().toString()).toString()
        });
    }

    private void rebootRecoveryWithCommand(String s)
    {
        mService.rebootRecoveryWithCommand(s);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static void rebootWipeAb(Context context, File file, String s)
        throws IOException
    {
        String s1 = null;
        if(!TextUtils.isEmpty(s))
            s1 = (new StringBuilder()).append("--reason=").append(sanitizeArg(s)).toString();
        file = file.getCanonicalPath();
        bootCommand(context, new String[] {
            "--wipe_ab", (new StringBuilder()).append("--wipe_package=").append(file).toString(), s1, (new StringBuilder()).append("--locale=").append(Locale.getDefault().toLanguageTag()).toString()
        });
    }

    public static void rebootWipeCache(Context context)
        throws IOException
    {
        rebootWipeCache(context, context.getPackageName());
    }

    public static void rebootWipeCache(Context context, String s)
        throws IOException
    {
        String s1 = null;
        if(!TextUtils.isEmpty(s))
            s1 = (new StringBuilder()).append("--reason=").append(sanitizeArg(s)).toString();
        bootCommand(context, new String[] {
            "--wipe_cache", s1, (new StringBuilder()).append("--locale=").append(Locale.getDefault().toLanguageTag()).toString()
        });
    }

    public static void rebootWipeUserData(Context context)
        throws IOException
    {
        rebootWipeUserData(context, false, context.getPackageName(), false, false);
    }

    public static void rebootWipeUserData(Context context, String s)
        throws IOException
    {
        rebootWipeUserData(context, false, s, false, false);
    }

    public static void rebootWipeUserData(Context context, boolean flag)
        throws IOException
    {
        rebootWipeUserData(context, flag, context.getPackageName(), false, false);
    }

    public static void rebootWipeUserData(Context context, boolean flag, String s, boolean flag1)
        throws IOException
    {
        rebootWipeUserData(context, flag, s, flag1, false);
    }

    public static void rebootWipeUserData(Context context, boolean flag, String s, boolean flag1, boolean flag2)
        throws IOException
    {
        Object obj = (UserManager)context.getSystemService("user");
        if(!flag1 && ((UserManager) (obj)).hasUserRestriction("no_factory_reset"))
            throw new SecurityException("Wiping data is not allowed for this user.");
        obj = new ConditionVariable();
        Object obj1 = new Intent("android.intent.action.MASTER_CLEAR_NOTIFICATION");
        ((Intent) (obj1)).addFlags(0x11000000);
        context.sendOrderedBroadcastAsUser(((Intent) (obj1)), UserHandle.SYSTEM, "android.permission.MASTER_CLEAR", new BroadcastReceiver(((ConditionVariable) (obj))) {

            public void onReceive(Context context1, Intent intent)
            {
                condition.open();
            }

            final ConditionVariable val$condition;

            
            {
                condition = conditionvariable;
                super();
            }
        }
, null, 0, null, null);
        ((ConditionVariable) (obj)).block();
        wipeEuiccData(context, flag2);
        obj = null;
        if(flag)
            obj = "--shutdown_after";
        obj1 = null;
        if(!TextUtils.isEmpty(s))
            obj1 = (new StringBuilder()).append("--reason=").append(sanitizeArg(s)).toString();
        bootCommand(context, new String[] {
            obj, "--wipe_data", obj1, (new StringBuilder()).append("--locale=").append(Locale.getDefault().toLanguageTag()).toString()
        });
    }

    private static void recursiveDelete(File file)
    {
        if(file.isDirectory())
        {
            String as[] = file.list();
            for(int i = 0; as != null && i < as.length; i++)
                recursiveDelete(new File(file, as[i]));

        }
        if(!file.delete())
            Log.e("RecoverySystem", (new StringBuilder()).append("Can't delete: ").append(file).toString());
        else
            Log.i("RecoverySystem", (new StringBuilder()).append("Deleted: ").append(file).toString());
    }

    private static String sanitizeArg(String s)
    {
        return s.replace('\0', '?').replace('\n', '?');
    }

    public static void scheduleUpdateOnBoot(Context context, File file)
        throws IOException
    {
        String s = file.getCanonicalPath();
        boolean flag = s.endsWith("_s.zip");
        file = s;
        if(s.startsWith("/data/"))
            file = "@/cache/recovery/block.map";
        file = (new StringBuilder()).append("--update_package=").append(file).append("\n").toString();
        s = (new StringBuilder()).append("--locale=").append(Locale.getDefault().toLanguageTag()).append("\n").toString();
        s = (new StringBuilder()).append(file).append(s).toString();
        file = s;
        if(flag)
            file = (new StringBuilder()).append(s).append("--security\n").toString();
        if(!((RecoverySystem)context.getSystemService("recovery")).setupBcb(file))
            throw new IOException("schedule update on boot failed");
        else
            return;
    }

    private boolean setupBcb(String s)
    {
        boolean flag;
        try
        {
            flag = mService.setupBcb(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return false;
        }
        return flag;
    }

    private boolean uncrypt(String s, IRecoverySystemProgressListener irecoverysystemprogresslistener)
    {
        boolean flag;
        try
        {
            flag = mService.uncrypt(s, irecoverysystemprogresslistener);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return false;
        }
        return flag;
    }

    public static void verifyPackage(File file, ProgressListener progresslistener, File file1)
        throws IOException, GeneralSecurityException
    {
        long l;
        RandomAccessFile randomaccessfile;
        l = file.length();
        randomaccessfile = new RandomAccessFile(file, "r");
        long l1 = System.currentTimeMillis();
        if(progresslistener == null)
            break MISSING_BLOCK_LABEL_34;
        progresslistener.onProgress(0);
        Object aobj[];
        randomaccessfile.seek(l - 6L);
        aobj = new byte[6];
        randomaccessfile.readFully(((byte []) (aobj)));
        if(aobj[2] == -1 && aobj[3] == -1)
            break MISSING_BLOCK_LABEL_94;
        file = JVM INSTR new #651 <Class SignatureException>;
        file.SignatureException("no signature in file (no footer)");
        throw file;
        file;
        randomaccessfile.close();
        throw file;
        int i;
        int j;
        i = aobj[4] & 0xff | (aobj[5] & 0xff) << 8;
        j = aobj[0] & 0xff | (aobj[1] & 0xff) << 8;
        aobj = new byte[i + 22];
        randomaccessfile.seek(l - (long)(i + 22));
        randomaccessfile.readFully(((byte []) (aobj)));
        break MISSING_BLOCK_LABEL_167;
        int k;
        do
        {
            file = JVM INSTR new #651 <Class SignatureException>;
            file.SignatureException("no signature in file (bad footer)");
            throw file;
        } while(aobj[0] != 80 || aobj[1] != 75 || aobj[2] != 5 || aobj[3] != 6);
        k = 4;
_L2:
        if(k >= aobj.length - 3)
            break; /* Loop/switch isn't completed */
        if(aobj[k] != 80 || aobj[k + 1] != 75 || aobj[k + 2] != 5 || aobj[k + 3] != 6)
            break MISSING_BLOCK_LABEL_286;
        file = JVM INSTR new #651 <Class SignatureException>;
        file.SignatureException("EOCD marker found after start of EOCD");
        throw file;
        k++;
        if(true) goto _L2; else goto _L1
_L1:
        PKCS7 pkcs7;
        pkcs7 = JVM INSTR new #661 <Class PKCS7>;
        ByteArrayInputStream bytearrayinputstream = JVM INSTR new #663 <Class ByteArrayInputStream>;
        bytearrayinputstream.ByteArrayInputStream(((byte []) (aobj)), (i + 22) - j, j);
        pkcs7.PKCS7(bytearrayinputstream);
        aobj = pkcs7.getCertificates();
        if(aobj == null)
            break MISSING_BLOCK_LABEL_344;
        if(aobj.length != 0)
            break MISSING_BLOCK_LABEL_357;
        file = JVM INSTR new #651 <Class SignatureException>;
        file.SignatureException("signature contains no certificates");
        throw file;
        java.security.PublicKey publickey;
        publickey = aobj[0].getPublicKey();
        aobj = pkcs7.getSignerInfos();
        if(aobj == null)
            break MISSING_BLOCK_LABEL_384;
        if(aobj.length != 0)
            break MISSING_BLOCK_LABEL_397;
        file = JVM INSTR new #651 <Class SignatureException>;
        file.SignatureException("signature contains no signedData");
        throw file;
        File file2;
        sun.security.pkcs.SignerInfo signerinfo;
        signerinfo = aobj[0];
        j = 0;
        file2 = file1;
        if(file1 != null)
            break MISSING_BLOCK_LABEL_418;
        file2 = DEFAULT_KEYSTORE;
        file1 = getTrustedCerts(file2).iterator();
_L4:
        boolean flag = j;
        if(!file1.hasNext())
            break; /* Loop/switch isn't completed */
        if(!((X509Certificate)file1.next()).getPublicKey().equals(publickey))
            continue; /* Loop/switch isn't completed */
        flag = true;
        break; /* Loop/switch isn't completed */
        if(true) goto _L4; else goto _L3
_L3:
        if(flag)
            break MISSING_BLOCK_LABEL_483;
        file = JVM INSTR new #651 <Class SignatureException>;
        file.SignatureException("signature doesn't match any trusted key");
        throw file;
        boolean flag1;
        randomaccessfile.seek(0L);
        file1 = JVM INSTR new #6   <Class RecoverySystem$1>;
        file1._cls1(l, i, l1, randomaccessfile, progresslistener);
        file1 = pkcs7.verify(signerinfo, file1);
        flag1 = Thread.interrupted();
        if(progresslistener == null)
            break MISSING_BLOCK_LABEL_531;
        progresslistener.onProgress(100);
        if(!flag1)
            break MISSING_BLOCK_LABEL_549;
        file = JVM INSTR new #651 <Class SignatureException>;
        file.SignatureException("verification was interrupted");
        throw file;
        if(file1 != null)
            break MISSING_BLOCK_LABEL_566;
        file = JVM INSTR new #651 <Class SignatureException>;
        file.SignatureException("signature digest verification failed");
        throw file;
        randomaccessfile.close();
        if(!readAndVerifyPackageCompatibilityEntry(file))
            throw new SignatureException("package compatibility verification failed");
        else
            return;
    }

    public static boolean verifyPackageCompatibility(File file)
        throws IOException
    {
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        obj = null;
        obj1 = null;
        obj2 = null;
        obj3 = null;
        Object obj5;
        obj5 = JVM INSTR new #729 <Class FileInputStream>;
        ((FileInputStream) (obj5)).FileInputStream(file);
        boolean flag = verifyPackageCompatibility(((InputStream) (obj5)));
        file = obj1;
        if(obj5 == null)
            break MISSING_BLOCK_LABEL_41;
        ((InputStream) (obj5)).close();
        file = obj1;
_L1:
        if(file != null)
            throw file;
        else
            return flag;
        file;
          goto _L1
        obj5;
        file = ((File) (obj3));
_L6:
        throw obj5;
        obj;
        obj3 = obj5;
        obj5 = obj;
_L4:
        obj = obj3;
        if(file == null)
            break MISSING_BLOCK_LABEL_84;
        file.close();
        obj = obj3;
_L2:
        if(obj != null)
            throw obj;
        else
            throw obj5;
        file;
        if(obj3 == null)
        {
            obj = file;
        } else
        {
            obj = obj3;
            if(obj3 != file)
            {
                ((Throwable) (obj3)).addSuppressed(file);
                obj = obj3;
            }
        }
          goto _L2
        obj5;
        file = obj2;
        obj3 = obj;
        continue; /* Loop/switch isn't completed */
        Object obj4;
        obj4;
        file = ((File) (obj5));
        obj5 = obj4;
        obj4 = obj;
        if(true) goto _L4; else goto _L3
_L3:
        obj4;
        file = ((File) (obj5));
        obj5 = obj4;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private static boolean verifyPackageCompatibility(InputStream inputstream)
        throws IOException
    {
        ArrayList arraylist = new ArrayList();
        inputstream = new ZipInputStream(inputstream);
        do
        {
            ZipEntry zipentry = inputstream.getNextEntry();
            if(zipentry == null)
                break;
            long l = zipentry.getSize();
            if(l > 0x7fffffffL || l < 0L)
                throw new IOException((new StringBuilder()).append("invalid entry size (").append(l).append(") in the compatibility file").toString());
            byte abyte0[] = new byte[(int)l];
            Streams.readFully(inputstream, abyte0);
            arraylist.add(new String(abyte0, StandardCharsets.UTF_8));
        } while(true);
        if(arraylist.isEmpty())
            throw new IOException("no entries found in the compatibility file");
        boolean flag;
        if(VintfObject.verify((String[])arraylist.toArray(new String[arraylist.size()])) == 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static void wipeEuiccData(Context context, boolean flag)
    {
        EuiccManager euiccmanager;
        if(android.provider.Settings.Global.getInt(context.getContentResolver(), "euicc_provisioned", 0) == 0)
        {
            Log.d("RecoverySystem", "Skipping eUICC wipe/retain as it is not provisioned");
            return;
        }
        euiccmanager = (EuiccManager)context.getSystemService("euicc_service");
        if(euiccmanager == null || !euiccmanager.isEnabled()) goto _L2; else goto _L1
_L1:
        long l;
        long l1;
        CountDownLatch countdownlatch = new CountDownLatch(1);
        BroadcastReceiver broadcastreceiver = new BroadcastReceiver(flag, countdownlatch) {

            public void onReceive(Context context1, Intent intent)
            {
                if("com.android.internal.action.EUICC_FACTORY_RESET".equals(intent.getAction()))
                {
                    if(getResultCode() != 0)
                    {
                        int i = intent.getIntExtra("android.telephony.euicc.extra.EMBEDDED_SUBSCRIPTION_DETAILED_CODE", 0);
                        if(isWipeEuicc)
                            Log.e("RecoverySystem", (new StringBuilder()).append("Error wiping euicc data, Detailed code = ").append(i).toString());
                        else
                            Log.e("RecoverySystem", (new StringBuilder()).append("Error retaining euicc data, Detailed code = ").append(i).toString());
                    } else
                    if(isWipeEuicc)
                        Log.d("RecoverySystem", "Successfully wiped euicc data.");
                    else
                        Log.d("RecoverySystem", "Successfully retained euicc data.");
                    euiccFactoryResetLatch.countDown();
                }
            }

            final CountDownLatch val$euiccFactoryResetLatch;
            final boolean val$isWipeEuicc;

            
            {
                isWipeEuicc = flag;
                euiccFactoryResetLatch = countdownlatch;
                super();
            }
        }
;
        Object obj = new Intent("com.android.internal.action.EUICC_FACTORY_RESET");
        ((Intent) (obj)).setPackage("android");
        obj = PendingIntent.getBroadcastAsUser(context, 0, ((Intent) (obj)), 0x8000000, UserHandle.SYSTEM);
        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction("com.android.internal.action.EUICC_FACTORY_RESET");
        Object obj1 = new HandlerThread("euiccWipeFinishReceiverThread");
        ((HandlerThread) (obj1)).start();
        obj1 = new Handler(((HandlerThread) (obj1)).getLooper());
        context.getApplicationContext().registerReceiver(broadcastreceiver, intentfilter, null, ((Handler) (obj1)));
        if(flag)
            euiccmanager.eraseSubscriptions(((PendingIntent) (obj)));
        else
            euiccmanager.retainSubscriptionsForFactoryReset(((PendingIntent) (obj)));
        l = android.provider.Settings.Global.getLong(context.getContentResolver(), "euicc_factory_reset_timeout_millis", 30000L);
        if(l < 5000L)
        {
            l1 = 5000L;
        } else
        {
            l1 = l;
            if(l > 60000L)
                l1 = 60000L;
        }
        if(countdownlatch.await(l1, TimeUnit.MILLISECONDS)) goto _L4; else goto _L3
_L3:
        if(!flag) goto _L6; else goto _L5
_L5:
        Log.e("RecoverySystem", "Timeout wiping eUICC data.");
_L4:
        context.getApplicationContext().unregisterReceiver(broadcastreceiver);
_L2:
        return;
_L6:
        Log.e("RecoverySystem", "Timeout retaining eUICC data.");
          goto _L4
        context;
        Thread.currentThread().interrupt();
        if(flag)
            Log.e("RecoverySystem", "Wiping eUICC data interrupted", context);
        else
            Log.e("RecoverySystem", "Retaining eUICC data interrupted", context);
          goto _L2
    }

    private static final String ACTION_EUICC_FACTORY_RESET = "com.android.internal.action.EUICC_FACTORY_RESET";
    public static final File BLOCK_MAP_FILE;
    private static final long DEFAULT_EUICC_FACTORY_RESET_TIMEOUT_MILLIS = 30000L;
    private static final File DEFAULT_KEYSTORE = new File("/system/etc/security/otacerts.zip");
    private static final File LAST_INSTALL_FILE;
    private static final String LAST_PREFIX = "last_";
    private static final File LOG_FILE;
    private static final int LOG_FILE_MAX_LENGTH = 0x10000;
    private static final long MAX_EUICC_FACTORY_RESET_TIMEOUT_MILLIS = 60000L;
    private static final long MIN_EUICC_FACTORY_RESET_TIMEOUT_MILLIS = 5000L;
    private static final long PUBLISH_PROGRESS_INTERVAL_MS = 500L;
    private static final File RECOVERY_DIR;
    private static final String TAG = "RecoverySystem";
    public static final File UNCRYPT_PACKAGE_FILE;
    public static final File UNCRYPT_STATUS_FILE;
    private static final Object sRequestLock = new Object();
    private final IRecoverySystem mService;

    static 
    {
        RECOVERY_DIR = new File("/cache/recovery");
        LOG_FILE = new File(RECOVERY_DIR, "log");
        LAST_INSTALL_FILE = new File(RECOVERY_DIR, "last_install");
        BLOCK_MAP_FILE = new File(RECOVERY_DIR, "block.map");
        UNCRYPT_PACKAGE_FILE = new File(RECOVERY_DIR, "uncrypt_file");
        UNCRYPT_STATUS_FILE = new File(RECOVERY_DIR, "uncrypt_status");
    }

    // Unreferenced inner class android/os/RecoverySystem$1

/* anonymous class */
    static final class _cls1 extends InputStream
    {

        public int read()
            throws IOException
        {
            throw new UnsupportedOperationException();
        }

        public int read(byte abyte0[], int i, int j)
            throws IOException
        {
            if(soFar >= toRead)
                return -1;
            if(Thread.currentThread().isInterrupted())
                return -1;
            int k = j;
            if(soFar + (long)j > toRead)
                k = (int)(toRead - soFar);
            i = raf.read(abyte0, i, k);
            soFar = soFar + (long)i;
            if(listenerForInner != null)
            {
                long l = System.currentTimeMillis();
                j = (int)((soFar * 100L) / toRead);
                if(j > lastPercent && l - lastPublishTime > 500L)
                {
                    lastPercent = j;
                    lastPublishTime = l;
                    listenerForInner.onProgress(lastPercent);
                }
            }
            return i;
        }

        int lastPercent;
        long lastPublishTime;
        long soFar;
        long toRead;
        final int val$commentSize;
        final long val$fileLen;
        final ProgressListener val$listenerForInner;
        final RandomAccessFile val$raf;
        final long val$startTimeMillis;

            
            {
                fileLen = l;
                commentSize = i;
                startTimeMillis = l1;
                raf = randomaccessfile;
                listenerForInner = progresslistener;
                super();
                toRead = fileLen - (long)commentSize - 2L;
                soFar = 0L;
                lastPercent = 0;
                lastPublishTime = startTimeMillis;
            }
    }

}
