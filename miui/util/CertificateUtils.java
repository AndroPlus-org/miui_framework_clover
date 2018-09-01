// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.util;

import android.content.pm.Signature;
import android.util.Slog;
import java.io.*;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

// Referenced classes of package miui.util:
//            ReflectionUtils

public class CertificateUtils
{

    public CertificateUtils()
    {
    }

    public static boolean collectCertificates(File file, Set set)
    {
        set.clear();
        byte abyte0[] = null;
        miui/util/CertificateUtils;
        JVM INSTR monitorenter ;
        Object obj1 = sReadBuffer;
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_31;
        sReadBuffer = null;
        abyte0 = (byte[])((WeakReference) (obj1)).get();
        byte abyte1[];
        abyte1 = abyte0;
        if(abyte0 != null)
            break MISSING_BLOCK_LABEL_55;
        abyte1 = new byte[8192];
        obj1 = new WeakReference(abyte1);
        miui/util/CertificateUtils;
        JVM INSTR monitorexit ;
        if(android.os.Build.VERSION.SDK_INT < 21) goto _L2; else goto _L1
_L1:
        Object obj = (JarFile)ReflectionUtils.newInstance(java/util/jar/JarFile, new Object[] {
            file.getPath(), Boolean.valueOf(true)
        });
_L16:
        Certificate acertificate[] = null;
        Enumeration enumeration = ((JarFile) (obj)).entries();
_L9:
        JarEntry jarentry;
        Certificate acertificate1[];
        if(!enumeration.hasMoreElements())
            break; /* Loop/switch isn't completed */
        jarentry = (JarEntry)enumeration.nextElement();
        if(jarentry.isDirectory() || jarentry.getName().startsWith("META-INF/"))
            continue; /* Loop/switch isn't completed */
        acertificate1 = loadCertificates(((JarFile) (obj)), jarentry, abyte1);
          goto _L3
        file;
        throw file;
_L2:
        obj = (JarFile)ReflectionUtils.newInstance(java/util/jar/JarFile, new Object[] {
            file.getPath(), Boolean.valueOf(true), Boolean.valueOf(true)
        });
        continue; /* Loop/switch isn't completed */
_L3:
        if(acertificate1 == null)
        {
            int i;
            boolean flag;
            int j;
            boolean flag1;
            try
            {
                obj1 = TAG;
                set = JVM INSTR new #120 <Class StringBuilder>;
                set.StringBuilder();
                Slog.e(((String) (obj1)), set.append("JarFile ").append(file.getPath()).append(" has no certificates at entry ").append(jarentry.getName()).append("; ignoring!").toString());
                ((JarFile) (obj)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Set set)
            {
                Slog.w(TAG, (new StringBuilder()).append("Exception reading ").append(file.getPath()).toString(), set);
                return false;
            }
            // Misplaced declaration of an exception variable
            catch(Set set)
            {
                Slog.w(TAG, (new StringBuilder()).append("Exception reading ").append(file.getPath()).toString(), set);
                return false;
            }
            // Misplaced declaration of an exception variable
            catch(Set set)
            {
                Slog.w(TAG, (new StringBuilder()).append("Exception reading ").append(file.getPath()).toString(), set);
                return false;
            }
            // Misplaced declaration of an exception variable
            catch(Set set)
            {
                Slog.w(TAG, (new StringBuilder()).append("Exception reading ").append(file.getPath()).toString(), set);
                return false;
            }
            // Misplaced declaration of an exception variable
            catch(Set set)
            {
                Slog.w(TAG, (new StringBuilder()).append("Exception reading ").append(file.getPath()).toString(), set);
                return false;
            }
            // Misplaced declaration of an exception variable
            catch(Set set)
            {
                Slog.w(TAG, (new StringBuilder()).append("Exception reading ").append(file.getPath()).toString(), set);
                return false;
            }
            // Misplaced declaration of an exception variable
            catch(Set set)
            {
                Slog.w(TAG, (new StringBuilder()).append("Exception reading ").append(file.getPath()).toString(), set);
                return false;
            }
            return false;
        }
        if(acertificate == null)
        {
            acertificate = acertificate1;
            continue; /* Loop/switch isn't completed */
        }
        i = 0;
_L7:
        if(i >= acertificate.length)
            break; /* Loop/switch isn't completed */
        flag = false;
        j = 0;
_L5:
        flag1 = flag;
        if(j >= acertificate1.length)
            break MISSING_BLOCK_LABEL_328;
        if(acertificate[i] == null)
            break MISSING_BLOCK_LABEL_399;
        if(!acertificate[i].equals(acertificate1[j]))
            break MISSING_BLOCK_LABEL_399;
        flag1 = true;
        if(!flag1)
            break MISSING_BLOCK_LABEL_342;
        if(acertificate.length == acertificate1.length)
            break; /* Loop/switch isn't completed */
        obj1 = TAG;
        set = JVM INSTR new #120 <Class StringBuilder>;
        set.StringBuilder();
        Slog.e(((String) (obj1)), set.append("JarFile ").append(file.getPath()).append(" has mismatched certificates at entry ").append(jarentry.getName()).append("; ignoring!").toString());
        ((JarFile) (obj)).close();
        return false;
        j++;
        if(true) goto _L5; else goto _L4
_L4:
        i++;
        if(true) goto _L7; else goto _L6
_L6:
        if(true) goto _L9; else goto _L8
_L8:
        ((JarFile) (obj)).close();
        miui/util/CertificateUtils;
        JVM INSTR monitorenter ;
        sReadBuffer = ((WeakReference) (obj1));
        miui/util/CertificateUtils;
        JVM INSTR monitorexit ;
        if(acertificate == null) goto _L11; else goto _L10
_L10:
        if(acertificate.length <= 0) goto _L11; else goto _L12
_L12:
        j = acertificate.length;
        i = 0;
_L14:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        obj = JVM INSTR new #153 <Class Signature>;
        ((Signature) (obj)).Signature(acertificate[i].getEncoded());
        set.add(obj);
        i++;
        if(true) goto _L14; else goto _L13
        set;
        miui/util/CertificateUtils;
        JVM INSTR monitorexit ;
        throw set;
_L11:
        obj = TAG;
        set = JVM INSTR new #120 <Class StringBuilder>;
        set.StringBuilder();
        Slog.e(((String) (obj)), set.append("JarFile ").append(file.getPath()).append(" has no certificates; ignoring!").toString());
        return false;
_L13:
        return true;
        if(true) goto _L16; else goto _L15
_L15:
    }

    public static int compareSignatures(Signature asignature[], Signature asignature1[])
    {
        if(asignature == null)
        {
            int i;
            if(asignature1 == null)
                i = 1;
            else
                i = -1;
            return i;
        }
        if(asignature1 == null)
            return -2;
        HashSet hashset = new HashSet();
        int l = asignature.length;
        for(int j = 0; j < l; j++)
            hashset.add(asignature[j]);

        asignature = new HashSet();
        l = asignature1.length;
        for(int k = 0; k < l; k++)
            asignature.add(asignature1[k]);

        return !hashset.equals(asignature) ? -3 : 0;
    }

    private static Certificate[] loadCertificates(JarFile jarfile, JarEntry jarentry, byte abyte0[])
    {
        Object obj = null;
        BufferedInputStream bufferedinputstream = JVM INSTR new #182 <Class BufferedInputStream>;
        bufferedinputstream.BufferedInputStream(jarfile.getInputStream(jarentry));
        while(bufferedinputstream.read(abyte0, 0, abyte0.length) != -1) ;
        bufferedinputstream.close();
        abyte0 = obj;
        if(jarentry == null)
            break MISSING_BLOCK_LABEL_46;
        abyte0 = jarentry.getCertificates();
        return abyte0;
        abyte0;
        Slog.w(TAG, (new StringBuilder()).append("Exception reading ").append(jarentry.getName()).append(" in ").append(jarfile.getName()).toString(), abyte0);
_L2:
        return null;
        abyte0;
        Slog.w(TAG, (new StringBuilder()).append("Exception reading ").append(jarentry.getName()).append(" in ").append(jarfile.getName()).toString(), abyte0);
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static final boolean DEBUG_JAR = false;
    private static final String TAG = miui/util/CertificateUtils.getSimpleName();
    private static WeakReference sReadBuffer;

}
