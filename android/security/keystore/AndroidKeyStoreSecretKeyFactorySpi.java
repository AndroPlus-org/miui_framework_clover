// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import android.security.GateKeeper;
import android.security.KeyStore;
import android.security.keymaster.KeyCharacteristics;
import android.security.keymaster.KeymasterArguments;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.ProviderException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.*;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactorySpi;
import javax.crypto.spec.SecretKeySpec;

// Referenced classes of package android.security.keystore:
//            KeyInfo, KeyGenParameterSpec, AndroidKeyStoreSecretKey, AndroidKeyStoreKey

public class AndroidKeyStoreSecretKeyFactorySpi extends SecretKeyFactorySpi
{

    public AndroidKeyStoreSecretKeyFactorySpi()
    {
    }

    private static BigInteger getGateKeeperSecureUserId()
        throws ProviderException
    {
        BigInteger biginteger;
        try
        {
            biginteger = BigInteger.valueOf(GateKeeper.getSecureUserId());
        }
        catch(IllegalStateException illegalstateexception)
        {
            throw new ProviderException("Failed to get GateKeeper secure user ID", illegalstateexception);
        }
        return biginteger;
    }

    static KeyInfo getKeyInfo(KeyStore keystore, String s, String s1, int i)
    {
        KeyCharacteristics keycharacteristics;
        keycharacteristics = new KeyCharacteristics();
        i = keystore.getKeyCharacteristics(s1, null, null, i, keycharacteristics);
        if(i != 1)
            throw new ProviderException((new StringBuilder()).append("Failed to obtain information about key. Keystore error: ").append(i).toString());
        if(!keycharacteristics.hwEnforced.containsTag(0x100002be)) goto _L2; else goto _L1
_L1:
        boolean flag = true;
        i = KeyProperties.Origin.fromKeymaster(keycharacteristics.hwEnforced.getEnum(0x100002be, -1));
_L3:
        long l = keycharacteristics.getUnsignedInt(0x30000003, -1L);
        if(l == -1L)
            try
            {
                keystore = JVM INSTR new #23  <Class ProviderException>;
                keystore.ProviderException("Key size not available");
                throw keystore;
            }
            // Misplaced declaration of an exception variable
            catch(KeyStore keystore)
            {
                throw new ProviderException("Unsupported key characteristic", keystore);
            }
        break MISSING_BLOCK_LABEL_174;
_L2:
        if(!keycharacteristics.swEnforced.containsTag(0x100002be))
            break MISSING_BLOCK_LABEL_162;
        flag = false;
        i = KeyProperties.Origin.fromKeymaster(keycharacteristics.swEnforced.getEnum(0x100002be, -1));
          goto _L3
        keystore = JVM INSTR new #23  <Class ProviderException>;
        keystore.ProviderException("Key origin not available");
        throw keystore;
        if(l <= 0x7fffffffL)
            break MISSING_BLOCK_LABEL_220;
        keystore = JVM INSTR new #23  <Class ProviderException>;
        s = JVM INSTR new #56  <Class StringBuilder>;
        s.StringBuilder();
        keystore.ProviderException(s.append("Key too large: ").append(l).append(" bits").toString());
        throw keystore;
        int j = (int)l;
        int k;
        ArrayList arraylist;
        Object obj;
        k = KeyProperties.Purpose.allFromKeymaster(keycharacteristics.getEnums(0x20000001));
        arraylist = JVM INSTR new #132 <Class ArrayList>;
        arraylist.ArrayList();
        s1 = JVM INSTR new #132 <Class ArrayList>;
        s1.ArrayList();
        obj = keycharacteristics.getEnums(0x20000006).iterator();
_L4:
        int i1;
        if(!((Iterator) (obj)).hasNext())
            break MISSING_BLOCK_LABEL_359;
        i1 = ((Integer)((Iterator) (obj)).next()).intValue();
        arraylist.add(KeyProperties.EncryptionPadding.fromKeymaster(i1));
          goto _L4
        keystore;
        s1.add(KeyProperties.SignaturePadding.fromKeymaster(i1));
          goto _L4
        keystore;
        keystore = JVM INSTR new #23  <Class ProviderException>;
        s = JVM INSTR new #56  <Class StringBuilder>;
        s.StringBuilder();
        keystore.ProviderException(s.append("Unsupported encryption padding: ").append(i1).toString());
        throw keystore;
        String as[];
        String as1[];
        String as2[];
        int j1;
        keystore = (String[])arraylist.toArray(new String[arraylist.size()]);
        as = (String[])s1.toArray(new String[s1.size()]);
        as1 = KeyProperties.Digest.allFromKeymaster(keycharacteristics.getEnums(0x20000005));
        as2 = KeyProperties.BlockMode.allFromKeymaster(keycharacteristics.getEnums(0x20000004));
        i1 = keycharacteristics.swEnforced.getEnum(0x100001f8, 0);
        j1 = keycharacteristics.hwEnforced.getEnum(0x100001f8, 0);
        obj = keycharacteristics.getUnsignedLongs(0xa00001f6);
        java.util.Date date = keycharacteristics.getDate(0x60000190);
        java.util.Date date1 = keycharacteristics.getDate(0x60000191);
        s1 = keycharacteristics.getDate(0x60000192);
        boolean flag1 = keycharacteristics.getBoolean(0x700001f7) ^ true;
        long l1 = keycharacteristics.getUnsignedInt(0x300001f9, -1L);
        if(l1 > 0x7fffffffL)
            throw new ProviderException((new StringBuilder()).append("User authentication timeout validity too long: ").append(l1).append(" seconds").toString());
        boolean flag2;
        boolean flag3;
        boolean flag4;
        if(flag1 && j1 != 0)
        {
            if(i1 == 0)
                flag2 = true;
            else
                flag2 = false;
        } else
        {
            flag2 = false;
        }
        flag3 = keycharacteristics.hwEnforced.getBoolean(0x700001fa);
        flag4 = false;
        if(i1 == 2 || j1 == 2)
            if(obj != null && ((List) (obj)).isEmpty() ^ true)
                flag4 = ((List) (obj)).contains(getGateKeeperSecureUserId()) ^ true;
            else
                flag4 = false;
        return new KeyInfo(s, flag, i, j, date, date1, s1, k, keystore, as, as1, as2, flag1, (int)l1, flag2, flag3, flag4);
    }

    protected SecretKey engineGenerateSecret(KeySpec keyspec)
        throws InvalidKeySpecException
    {
        throw new InvalidKeySpecException((new StringBuilder()).append("To generate secret key in Android Keystore, use KeyGenerator initialized with ").append(android/security/keystore/KeyGenParameterSpec.getName()).toString());
    }

    protected KeySpec engineGetKeySpec(SecretKey secretkey, Class class1)
        throws InvalidKeySpecException
    {
        if(class1 == null)
            throw new InvalidKeySpecException("keySpecClass == null");
        if(!(secretkey instanceof AndroidKeyStoreSecretKey))
        {
            class1 = (new StringBuilder()).append("Only Android KeyStore secret keys supported: ");
            if(secretkey != null)
                secretkey = secretkey.getClass().getName();
            else
                secretkey = "null";
            throw new InvalidKeySpecException(class1.append(secretkey).toString());
        }
        if(javax/crypto/spec/SecretKeySpec.isAssignableFrom(class1))
            throw new InvalidKeySpecException("Key material export of Android KeyStore keys is not supported");
        if(!android/security/keystore/KeyInfo.equals(class1))
            throw new InvalidKeySpecException((new StringBuilder()).append("Unsupported key spec: ").append(class1.getName()).toString());
        secretkey = (AndroidKeyStoreKey)secretkey;
        String s = secretkey.getAlias();
        if(s.startsWith("USRSKEY_"))
        {
            class1 = s.substring("USRSKEY_".length());
            return getKeyInfo(mKeyStore, class1, s, secretkey.getUid());
        } else
        {
            throw new InvalidKeySpecException((new StringBuilder()).append("Invalid key alias: ").append(s).toString());
        }
    }

    protected SecretKey engineTranslateKey(SecretKey secretkey)
        throws InvalidKeyException
    {
        if(secretkey == null)
            throw new InvalidKeyException("key == null");
        if(!(secretkey instanceof AndroidKeyStoreSecretKey))
            throw new InvalidKeyException("To import a secret key into Android Keystore, use KeyStore.setEntry");
        else
            return secretkey;
    }

    private final KeyStore mKeyStore = KeyStore.getInstance();
}
