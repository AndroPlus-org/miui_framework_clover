// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util.jar;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.jar.Attributes;
import sun.security.jca.Providers;
import sun.security.pkcs.PKCS7;
import sun.security.pkcs.SignerInfo;

// Referenced classes of package android.util.jar:
//            StrictJarManifest, StrictJarManifestReader

class StrictJarVerifier
{
    static class VerifierEntry extends OutputStream
    {

        void verify()
        {
            if(!StrictJarVerifier._2D_wrap0(digest.digest(), hash))
            {
                throw StrictJarVerifier._2D_wrap1("META-INF/MANIFEST.MF", name, name);
            } else
            {
                verifiedEntries.put(name, certChains);
                return;
            }
        }

        public void write(int i)
        {
            digest.update((byte)i);
        }

        public void write(byte abyte0[], int i, int j)
        {
            digest.update(abyte0, i, j);
        }

        private final Certificate certChains[][];
        private final MessageDigest digest;
        private final byte hash[];
        private final String name;
        private final Hashtable verifiedEntries;

        VerifierEntry(String s, MessageDigest messagedigest, byte abyte0[], Certificate acertificate[][], Hashtable hashtable)
        {
            name = s;
            digest = messagedigest;
            hash = abyte0;
            certChains = acertificate;
            verifiedEntries = hashtable;
        }
    }


    static boolean _2D_wrap0(byte abyte0[], byte abyte1[])
    {
        return verifyMessageDigest(abyte0, abyte1);
    }

    static SecurityException _2D_wrap1(String s, String s1, String s2)
    {
        return invalidDigest(s, s1, s2);
    }

    StrictJarVerifier(String s, StrictJarManifest strictjarmanifest, HashMap hashmap, boolean flag)
    {
        jarName = s;
        manifest = strictjarmanifest;
        metaEntries = hashmap;
        mainAttributesEnd = strictjarmanifest.getMainAttributesEnd();
        signatureSchemeRollbackProtectionsEnforced = flag;
    }

    private static SecurityException failedVerification(String s, String s1)
    {
        throw new SecurityException((new StringBuilder()).append(s).append(" failed verification of ").append(s1).toString());
    }

    private static SecurityException failedVerification(String s, String s1, Throwable throwable)
    {
        throw new SecurityException((new StringBuilder()).append(s).append(" failed verification of ").append(s1).toString(), throwable);
    }

    private static SecurityException invalidDigest(String s, String s1, String s2)
    {
        throw new SecurityException((new StringBuilder()).append(s).append(" has invalid digest for ").append(s1).append(" in ").append(s2).toString());
    }

    private boolean verify(Attributes attributes, String s, byte abyte0[], int i, int j, boolean flag, boolean flag1)
    {
        int k = 0;
_L2:
        Object obj;
        String s1;
        if(k >= DIGEST_ALGORITHMS.length)
            break MISSING_BLOCK_LABEL_143;
        obj = DIGEST_ALGORITHMS[k];
        s1 = attributes.getValue((new StringBuilder()).append(((String) (obj))).append(s).toString());
        if(s1 != null)
            break; /* Loop/switch isn't completed */
_L3:
        k++;
        if(true) goto _L2; else goto _L1
_L1:
        obj = MessageDigest.getInstance(((String) (obj)));
        NoSuchAlgorithmException nosuchalgorithmexception;
        if(flag && abyte0[j - 1] == 10 && abyte0[j - 2] == 10)
            ((MessageDigest) (obj)).update(abyte0, i, j - 1 - i);
        else
            ((MessageDigest) (obj)).update(abyte0, i, j - i);
        return verifyMessageDigest(((MessageDigest) (obj)).digest(), s1.getBytes(StandardCharsets.ISO_8859_1));
        nosuchalgorithmexception;
          goto _L3
        return flag1;
    }

    static Certificate[] verifyBytes(byte abyte0[], byte abyte1[])
        throws GeneralSecurityException
    {
        Object obj;
        Object obj1;
        obj = null;
        obj1 = null;
        Object obj2 = Providers.startJarVerification();
        obj1 = obj2;
        obj = obj2;
        PKCS7 pkcs7 = JVM INSTR new #162 <Class PKCS7>;
        obj1 = obj2;
        obj = obj2;
        pkcs7.PKCS7(abyte0);
        obj1 = obj2;
        obj = obj2;
        abyte0 = pkcs7.verify(abyte1);
        if(abyte0 == null)
            break MISSING_BLOCK_LABEL_60;
        obj1 = obj2;
        obj = obj2;
        if(abyte0.length != 0)
            break MISSING_BLOCK_LABEL_117;
        obj1 = obj2;
        obj = obj2;
        abyte0 = JVM INSTR new #152 <Class GeneralSecurityException>;
        obj1 = obj2;
        obj = obj2;
        abyte0.GeneralSecurityException("Failed to verify signature: no verified SignerInfos");
        obj1 = obj2;
        obj = obj2;
        try
        {
            throw abyte0;
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte1[])
        {
            obj = obj1;
        }
        abyte0 = JVM INSTR new #152 <Class GeneralSecurityException>;
        obj = obj1;
        abyte0.GeneralSecurityException("IO exception verifying jar cert", abyte1);
        obj = obj1;
        throw abyte0;
        abyte0;
        Providers.stopJarVerification(obj);
        throw abyte0;
        obj1 = obj2;
        obj = obj2;
        abyte0 = abyte0[0].getCertificateChain(pkcs7);
        if(abyte0 != null)
            break MISSING_BLOCK_LABEL_166;
        obj1 = obj2;
        obj = obj2;
        abyte0 = JVM INSTR new #152 <Class GeneralSecurityException>;
        obj1 = obj2;
        obj = obj2;
        abyte0.GeneralSecurityException("Failed to find verified SignerInfo certificate chain");
        obj1 = obj2;
        obj = obj2;
        throw abyte0;
        obj1 = obj2;
        obj = obj2;
        if(!abyte0.isEmpty())
            break MISSING_BLOCK_LABEL_211;
        obj1 = obj2;
        obj = obj2;
        abyte0 = JVM INSTR new #152 <Class GeneralSecurityException>;
        obj1 = obj2;
        obj = obj2;
        abyte0.GeneralSecurityException("Verified SignerInfo certificate chain is emtpy");
        obj1 = obj2;
        obj = obj2;
        throw abyte0;
        obj1 = obj2;
        obj = obj2;
        abyte0 = (Certificate[])abyte0.toArray(new X509Certificate[abyte0.size()]);
        Providers.stopJarVerification(obj2);
        return abyte0;
    }

    private void verifyCertificate(String s)
    {
        String s1;
        byte abyte1[];
        Object obj;
        HashMap hashmap;
        s1 = (new StringBuilder()).append(s.substring(0, s.lastIndexOf('.'))).append(".SF").toString();
        byte abyte0[] = (byte[])metaEntries.get(s1);
        if(abyte0 == null)
            return;
        abyte1 = (byte[])metaEntries.get("META-INF/MANIFEST.MF");
        if(abyte1 == null)
            return;
        s = (byte[])metaEntries.get(s);
        String s2;
        boolean flag;
        int i;
        try
        {
            s = verifyBytes(s, abyte0);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw failedVerification(jarName, s1, s);
        }
        if(s == null)
            break MISSING_BLOCK_LABEL_99;
        certificates.put(s1, s);
        obj = new Attributes();
        hashmap = new HashMap();
        try
        {
            s = JVM INSTR new #239 <Class StrictJarManifestReader>;
            s.StrictJarManifestReader(abyte0, ((Attributes) (obj)));
            s.readEntries(hashmap, null);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return;
        }
        if(!signatureSchemeRollbackProtectionsEnforced) goto _L2; else goto _L1
_L1:
        s = ((Attributes) (obj)).getValue("X-Android-APK-Signed");
        if(s == null) goto _L2; else goto _L3
_L3:
        flag = false;
        s = new StringTokenizer(s, ",");
_L4:
        i = ((flag) ? 1 : 0);
        if(!s.hasMoreTokens())
            break MISSING_BLOCK_LABEL_209;
        s2 = s.nextToken().trim();
        if(s2.isEmpty())
            continue; /* Loop/switch isn't completed */
        i = Integer.parseInt(s2);
        if(i != 2)
            continue; /* Loop/switch isn't completed */
        i = 1;
        if(i != 0)
            throw new SecurityException((new StringBuilder()).append(s1).append(" indicates ").append(jarName).append(" is signed using APK Signature Scheme v2, but no such signature was").append(" found. Signature stripped?").toString());
        break; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        if(true) goto _L4; else goto _L2
_L2:
label0:
        {
            if(((Attributes) (obj)).get(java.util.jar.Attributes.Name.SIGNATURE_VERSION) == null)
                return;
            boolean flag1 = false;
            s = ((Attributes) (obj)).getValue("Created-By");
            if(s != null)
                if(s.indexOf("signtool") != -1)
                    flag1 = true;
                else
                    flag1 = false;
            if(mainAttributesEnd > 0 && flag1 ^ true && !verify(((Attributes) (obj)), "-Digest-Manifest-Main-Attributes", abyte1, 0, mainAttributesEnd, false, true))
                throw failedVerification(jarName, s1);
            StrictJarManifest.Chunk chunk;
            if(flag1)
                s = "-Digest";
            else
                s = "-Digest-Manifest";
            if(verify(((Attributes) (obj)), s, abyte1, 0, abyte1.length, false, false))
                break label0;
            obj = hashmap.entrySet().iterator();
            do
            {
                if(!((Iterator) (obj)).hasNext())
                    break label0;
                s = (java.util.Map.Entry)((Iterator) (obj)).next();
                chunk = manifest.getChunk((String)s.getKey());
                if(chunk == null)
                    return;
            } while(verify((Attributes)s.getValue(), "-Digest", abyte1, chunk.start, chunk.end, flag1, false));
            throw invalidDigest(s1, (String)s.getKey(), jarName);
        }
        metaEntries.put(s1, null);
        signatures.put(s1, hashmap);
        return;
    }

    private static boolean verifyMessageDigest(byte abyte0[], byte abyte1[])
    {
        try
        {
            abyte1 = Base64.getDecoder().decode(abyte1);
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            return false;
        }
        return MessageDigest.isEqual(abyte0, abyte1);
    }

    void addMetaEntry(String s, byte abyte0[])
    {
        metaEntries.put(s.toUpperCase(Locale.US), abyte0);
    }

    Certificate[][] getCertificateChains(String s)
    {
        return (Certificate[][])verifiedEntries.get(s);
    }

    VerifierEntry initEntry(String s)
    {
        Attributes attributes;
        Certificate acertificate[][];
        int i;
        if(manifest == null || signatures.isEmpty())
            return null;
        attributes = manifest.getAttributes(s);
        if(attributes == null)
            return null;
        ArrayList arraylist = new ArrayList();
        Iterator iterator = signatures.entrySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Object obj1 = (java.util.Map.Entry)iterator.next();
            if(((HashMap)((java.util.Map.Entry) (obj1)).getValue()).get(s) != null)
            {
                obj1 = (String)((java.util.Map.Entry) (obj1)).getKey();
                Certificate acertificate1[] = (Certificate[])certificates.get(obj1);
                if(acertificate1 != null)
                    arraylist.add(acertificate1);
            }
        } while(true);
        if(arraylist.isEmpty())
            return null;
        acertificate = (Certificate[][])arraylist.toArray(new Certificate[arraylist.size()][]);
        i = 0;
_L5:
        if(i >= DIGEST_ALGORITHMS.length) goto _L2; else goto _L1
_L1:
        Object obj;
        String s1;
        obj = DIGEST_ALGORITHMS[i];
        s1 = attributes.getValue((new StringBuilder()).append(((String) (obj))).append("-Digest").toString());
        if(s1 != null) goto _L4; else goto _L3
_L3:
        i++;
          goto _L5
_L4:
        byte abyte0[] = s1.getBytes(StandardCharsets.ISO_8859_1);
        obj = new VerifierEntry(s, MessageDigest.getInstance(((String) (obj))), abyte0, acertificate, verifiedEntries);
        return ((VerifierEntry) (obj));
_L2:
        return null;
        NoSuchAlgorithmException nosuchalgorithmexception;
        nosuchalgorithmexception;
          goto _L3
    }

    boolean isSignedJar()
    {
        boolean flag = false;
        if(certificates.size() > 0)
            flag = true;
        return flag;
    }

    boolean readCertificates()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = metaEntries.isEmpty();
        if(!flag)
            break MISSING_BLOCK_LABEL_18;
        this;
        JVM INSTR monitorexit ;
        return false;
        Iterator iterator = metaEntries.keySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            String s = (String)iterator.next();
            if(s.endsWith(".DSA") || s.endsWith(".RSA") || s.endsWith(".EC"))
            {
                verifyCertificate(s);
                iterator.remove();
            }
        } while(true);
        break MISSING_BLOCK_LABEL_99;
        Exception exception;
        exception;
        throw exception;
        this;
        JVM INSTR monitorexit ;
        return true;
    }

    void removeMetaEntries()
    {
        metaEntries.clear();
    }

    private static final String DIGEST_ALGORITHMS[] = {
        "SHA-512", "SHA-384", "SHA-256", "SHA1"
    };
    private final Hashtable certificates = new Hashtable(5);
    private final String jarName;
    private final int mainAttributesEnd;
    private final StrictJarManifest manifest;
    private final HashMap metaEntries;
    private final boolean signatureSchemeRollbackProtectionsEnforced;
    private final Hashtable signatures = new Hashtable(5);
    private final Hashtable verifiedEntries = new Hashtable();

}
