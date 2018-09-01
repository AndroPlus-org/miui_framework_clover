// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import android.content.Context;
import android.os.Build;
import android.security.KeyStore;
import android.security.keymaster.KeymasterArguments;
import android.security.keymaster.KeymasterCertificateChain;
import android.telephony.TelephonyManager;
import android.util.ArraySet;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.*;

// Referenced classes of package android.security.keystore:
//            DeviceIdAttestationException

public abstract class AttestationUtils
{

    private AttestationUtils()
    {
    }

    public static X509Certificate[] attestDeviceIds(Context context, int ai[], byte abyte0[])
        throws DeviceIdAttestationException
    {
label0:
        {
            if(ai == null)
                throw new NullPointerException("Missing id types");
            if(abyte0 == null)
                throw new NullPointerException("Missing attestation challenge");
            KeymasterArguments keymasterarguments = new KeymasterArguments();
            keymasterarguments.addBytes(0x900002c4, abyte0);
            abyte0 = new ArraySet(ai.length);
            int i = 0;
            for(int k = ai.length; i < k; i++)
                abyte0.add(Integer.valueOf(ai[i]));

            ai = null;
            if(abyte0.contains(Integer.valueOf(2)) || abyte0.contains(Integer.valueOf(3)))
            {
                context = (TelephonyManager)context.getSystemService("phone");
                ai = context;
                if(context == null)
                    throw new DeviceIdAttestationException("Unable to access telephony service");
            }
            context = abyte0.iterator();
            do
                if(context.hasNext())
                {
                    abyte0 = (Integer)context.next();
                    switch(abyte0.intValue())
                    {
                    default:
                        throw new IllegalArgumentException((new StringBuilder()).append("Unknown device ID type ").append(abyte0).toString());

                    case 1: // '\001'
                        keymasterarguments.addBytes(0x900002c9, Build.getSerial().getBytes(StandardCharsets.UTF_8));
                        break;

                    case 2: // '\002'
                        abyte0 = ai.getImei(0);
                        if(abyte0 == null)
                            throw new DeviceIdAttestationException("Unable to retrieve IMEI");
                        keymasterarguments.addBytes(0x900002ca, abyte0.getBytes(StandardCharsets.UTF_8));
                        break;

                    case 3: // '\003'
                        abyte0 = ai.getDeviceId();
                        if(abyte0 == null)
                            throw new DeviceIdAttestationException("Unable to retrieve MEID");
                        keymasterarguments.addBytes(0x900002cb, abyte0.getBytes(StandardCharsets.UTF_8));
                        break;
                    }
                } else
                {
                    keymasterarguments.addBytes(0x900002c6, Build.BRAND.getBytes(StandardCharsets.UTF_8));
                    keymasterarguments.addBytes(0x900002c7, Build.DEVICE.getBytes(StandardCharsets.UTF_8));
                    keymasterarguments.addBytes(0x900002c8, Build.PRODUCT.getBytes(StandardCharsets.UTF_8));
                    keymasterarguments.addBytes(0x900002cc, Build.MANUFACTURER.getBytes(StandardCharsets.UTF_8));
                    keymasterarguments.addBytes(0x900002cd, Build.MODEL.getBytes(StandardCharsets.UTF_8));
                    context = new KeymasterCertificateChain();
                    int j = KeyStore.getInstance().attestDeviceIds(keymasterarguments, context);
                    if(j != 1)
                        throw new DeviceIdAttestationException("Unable to perform attestation", KeyStore.getKeyStoreException(j));
                    ai = context.getCertificates();
                    if(ai.size() < 2)
                        throw new DeviceIdAttestationException((new StringBuilder()).append("Attestation certificate chain contained ").append(ai.size()).append(" entries. At least two are required.").toString());
                    context = new ByteArrayOutputStream();
                    break label0;
                }
            while(true);
        }
        try
        {
            for(ai = ai.iterator(); ai.hasNext(); context.write((byte[])ai.next()));
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw new DeviceIdAttestationException("Unable to construct certificate chain", context);
        }
        ai = CertificateFactory.getInstance("X.509");
        abyte0 = JVM INSTR new #220 <Class ByteArrayInputStream>;
        abyte0.ByteArrayInputStream(context.toByteArray());
        context = (X509Certificate[])ai.generateCertificates(abyte0).toArray(new X509Certificate[0]);
        return context;
    }

    public static final int ID_TYPE_IMEI = 2;
    public static final int ID_TYPE_MEID = 3;
    public static final int ID_TYPE_SERIAL = 1;
}
