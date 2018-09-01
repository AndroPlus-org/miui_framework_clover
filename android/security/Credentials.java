// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security;

import android.content.*;
import android.util.Log;
import com.android.org.bouncycastle.util.io.pem.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.*;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.security:
//            KeyStore, KeyChain

public class Credentials
{

    public Credentials()
    {
    }

    public static List convertFromPem(byte abyte0[])
        throws IOException, CertificateException
    {
        abyte0 = new PemReader(new InputStreamReader(new ByteArrayInputStream(abyte0), StandardCharsets.US_ASCII));
        CertificateFactory certificatefactory;
        Object obj;
        certificatefactory = CertificateFactory.getInstance("X509");
        obj = JVM INSTR new #120 <Class ArrayList>;
        ((ArrayList) (obj)).ArrayList();
_L1:
        Object obj1 = abyte0.readPemObject();
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_138;
        if(!((PemObject) (obj1)).getType().equals("CERTIFICATE"))
            break MISSING_BLOCK_LABEL_101;
        ByteArrayInputStream bytearrayinputstream = JVM INSTR new #95  <Class ByteArrayInputStream>;
        bytearrayinputstream.ByteArrayInputStream(((PemObject) (obj1)).getContent());
        ((List) (obj)).add((X509Certificate)certificatefactory.generateCertificate(bytearrayinputstream));
          goto _L1
        obj1;
        abyte0.close();
        throw obj1;
        IllegalArgumentException illegalargumentexception = JVM INSTR new #159 <Class IllegalArgumentException>;
        obj = JVM INSTR new #161 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        illegalargumentexception.IllegalArgumentException(((StringBuilder) (obj)).append("Unknown type ").append(((PemObject) (obj1)).getType()).toString());
        throw illegalargumentexception;
        abyte0.close();
        return ((List) (obj));
    }

    public static transient byte[] convertToPem(Certificate acertificate[])
        throws IOException, CertificateEncodingException
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        PemWriter pemwriter = new PemWriter(new OutputStreamWriter(bytearrayoutputstream, StandardCharsets.US_ASCII));
        int i = 0;
        for(int j = acertificate.length; i < j; i++)
            pemwriter.writeObject(new PemObject("CERTIFICATE", acertificate[i].getEncoded()));

        pemwriter.close();
        return bytearrayoutputstream.toByteArray();
    }

    public static boolean deleteAllTypesForAlias(KeyStore keystore, String s)
    {
        return deleteAllTypesForAlias(keystore, s, -1);
    }

    public static boolean deleteAllTypesForAlias(KeyStore keystore, String s, int i)
    {
        return deletePrivateKeyTypeForAlias(keystore, s, i) & deleteSecretKeyTypeForAlias(keystore, s, i) & deleteCertificateTypesForAlias(keystore, s, i);
    }

    public static boolean deleteCertificateTypesForAlias(KeyStore keystore, String s)
    {
        return deleteCertificateTypesForAlias(keystore, s, -1);
    }

    public static boolean deleteCertificateTypesForAlias(KeyStore keystore, String s, int i)
    {
        return keystore.delete((new StringBuilder()).append("USRCERT_").append(s).toString(), i) & keystore.delete((new StringBuilder()).append("CACERT_").append(s).toString(), i);
    }

    static boolean deletePrivateKeyTypeForAlias(KeyStore keystore, String s)
    {
        return deletePrivateKeyTypeForAlias(keystore, s, -1);
    }

    static boolean deletePrivateKeyTypeForAlias(KeyStore keystore, String s, int i)
    {
        return keystore.delete((new StringBuilder()).append("USRPKEY_").append(s).toString(), i);
    }

    public static boolean deleteSecretKeyTypeForAlias(KeyStore keystore, String s)
    {
        return deleteSecretKeyTypeForAlias(keystore, s, -1);
    }

    public static boolean deleteSecretKeyTypeForAlias(KeyStore keystore, String s, int i)
    {
        return keystore.delete((new StringBuilder()).append("USRSKEY_").append(s).toString(), i);
    }

    public static Credentials getInstance()
    {
        if(singleton == null)
            singleton = new Credentials();
        return singleton;
    }

    public void install(Context context)
    {
        context.startActivity(KeyChain.createInstallIntent());
_L1:
        return;
        context;
        Log.w("Credentials", context.toString());
          goto _L1
    }

    public void install(Context context, String s, byte abyte0[])
    {
        Intent intent = KeyChain.createInstallIntent();
        intent.putExtra(s, abyte0);
        context.startActivity(intent);
_L1:
        return;
        context;
        Log.w("Credentials", context.toString());
          goto _L1
    }

    public void install(Context context, KeyPair keypair)
    {
        Intent intent = KeyChain.createInstallIntent();
        intent.putExtra("PKEY", keypair.getPrivate().getEncoded());
        intent.putExtra("KEY", keypair.getPublic().getEncoded());
        context.startActivity(intent);
_L1:
        return;
        context;
        Log.w("Credentials", context.toString());
          goto _L1
    }

    public void unlock(Context context)
    {
        Intent intent = JVM INSTR new #260 <Class Intent>;
        intent.Intent("com.android.credentials.UNLOCK");
        context.startActivity(intent);
_L1:
        return;
        context;
        Log.w("Credentials", context.toString());
          goto _L1
    }

    public static final String CA_CERTIFICATE = "CACERT_";
    public static final String EXTENSION_CER = ".cer";
    public static final String EXTENSION_CRT = ".crt";
    public static final String EXTENSION_P12 = ".p12";
    public static final String EXTENSION_PFX = ".pfx";
    public static final String EXTRA_CA_CERTIFICATES_DATA = "ca_certificates_data";
    public static final String EXTRA_CA_CERTIFICATES_NAME = "ca_certificates_name";
    public static final String EXTRA_INSTALL_AS_UID = "install_as_uid";
    public static final String EXTRA_PRIVATE_KEY = "PKEY";
    public static final String EXTRA_PUBLIC_KEY = "KEY";
    public static final String EXTRA_USER_CERTIFICATE_DATA = "user_certificate_data";
    public static final String EXTRA_USER_CERTIFICATE_NAME = "user_certificate_name";
    public static final String EXTRA_USER_PRIVATE_KEY_DATA = "user_private_key_data";
    public static final String EXTRA_USER_PRIVATE_KEY_NAME = "user_private_key_name";
    public static final String INSTALL_ACTION = "android.credentials.INSTALL";
    public static final String INSTALL_AS_USER_ACTION = "android.credentials.INSTALL_AS_USER";
    public static final String LOCKDOWN_VPN = "LOCKDOWN_VPN";
    private static final String LOGTAG = "Credentials";
    public static final String UNLOCK_ACTION = "com.android.credentials.UNLOCK";
    public static final String USER_CERTIFICATE = "USRCERT_";
    public static final String USER_PRIVATE_KEY = "USRPKEY_";
    public static final String USER_SECRET_KEY = "USRSKEY_";
    public static final String VPN = "VPN_";
    public static final String WIFI = "WIFI_";
    private static Credentials singleton;
}
