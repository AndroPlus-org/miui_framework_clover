// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi;

import android.os.Parcel;
import java.io.ByteArrayInputStream;
import java.security.*;
import java.security.cert.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

public class ParcelUtil
{

    public ParcelUtil()
    {
    }

    public static X509Certificate readCertificate(Parcel parcel)
    {
        byte abyte0[] = parcel.createByteArray();
        if(abyte0 == null)
            return null;
        try
        {
            CertificateFactory certificatefactory = CertificateFactory.getInstance("X.509");
            parcel = JVM INSTR new #29  <Class ByteArrayInputStream>;
            parcel.ByteArrayInputStream(abyte0);
            parcel = (X509Certificate)certificatefactory.generateCertificate(parcel);
        }
        // Misplaced declaration of an exception variable
        catch(Parcel parcel)
        {
            return null;
        }
        return parcel;
    }

    public static X509Certificate[] readCertificates(Parcel parcel)
    {
        int i = parcel.readInt();
        if(i == 0)
            return null;
        X509Certificate ax509certificate[] = new X509Certificate[i];
        for(int j = 0; j < i; j++)
            ax509certificate[j] = readCertificate(parcel);

        return ax509certificate;
    }

    public static PrivateKey readPrivateKey(Parcel parcel)
    {
        Object obj;
        obj = parcel.readString();
        if(obj == null)
            return null;
        parcel = parcel.createByteArray();
        try
        {
            KeyFactory keyfactory = KeyFactory.getInstance(((String) (obj)));
            obj = JVM INSTR new #63  <Class PKCS8EncodedKeySpec>;
            ((PKCS8EncodedKeySpec) (obj)).PKCS8EncodedKeySpec(parcel);
            parcel = keyfactory.generatePrivate(((java.security.spec.KeySpec) (obj)));
        }
        // Misplaced declaration of an exception variable
        catch(Parcel parcel)
        {
            return null;
        }
        return parcel;
    }

    public static void writeCertificate(Parcel parcel, X509Certificate x509certificate)
    {
        Object obj = null;
        byte abyte0[] = obj;
        if(x509certificate != null)
            try
            {
                abyte0 = x509certificate.getEncoded();
            }
            // Misplaced declaration of an exception variable
            catch(X509Certificate x509certificate)
            {
                abyte0 = obj;
            }
        parcel.writeByteArray(abyte0);
    }

    public static void writeCertificates(Parcel parcel, X509Certificate ax509certificate[])
    {
        if(ax509certificate == null || ax509certificate.length == 0)
        {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(ax509certificate.length);
        for(int i = 0; i < ax509certificate.length; i++)
            writeCertificate(parcel, ax509certificate[i]);

    }

    public static void writePrivateKey(Parcel parcel, PrivateKey privatekey)
    {
        if(privatekey == null)
        {
            parcel.writeString(null);
            return;
        } else
        {
            parcel.writeString(privatekey.getAlgorithm());
            parcel.writeByteArray(privatekey.getEncoded());
            return;
        }
    }
}
