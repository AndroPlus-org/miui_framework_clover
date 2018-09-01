// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import java.util.*;
import libcore.util.EmptyArray;

public abstract class KeyProperties
{
    public static abstract class BlockMode
    {

        public static String[] allFromKeymaster(Collection collection)
        {
            if(collection == null || collection.isEmpty())
                return EmptyArray.STRING;
            String as[] = new String[collection.size()];
            int i = 0;
            for(collection = collection.iterator(); collection.hasNext();)
            {
                as[i] = fromKeymaster(((Integer)collection.next()).intValue());
                i++;
            }

            return as;
        }

        public static int[] allToKeymaster(String as[])
        {
            if(as == null || as.length == 0)
                return EmptyArray.INT;
            int ai[] = new int[as.length];
            for(int i = 0; i < as.length; i++)
                ai[i] = toKeymaster(as[i]);

            return ai;
        }

        public static String fromKeymaster(int i)
        {
            switch(i)
            {
            default:
                throw new IllegalArgumentException((new StringBuilder()).append("Unsupported block mode: ").append(i).toString());

            case 1: // '\001'
                return "ECB";

            case 2: // '\002'
                return "CBC";

            case 3: // '\003'
                return "CTR";

            case 32: // ' '
                return "GCM";
            }
        }

        public static int toKeymaster(String s)
        {
            if("ECB".equalsIgnoreCase(s))
                return 1;
            if("CBC".equalsIgnoreCase(s))
                return 2;
            if("CTR".equalsIgnoreCase(s))
                return 3;
            if("GCM".equalsIgnoreCase(s))
                return 32;
            else
                throw new IllegalArgumentException((new StringBuilder()).append("Unsupported block mode: ").append(s).toString());
        }

        private BlockMode()
        {
        }
    }

    public static abstract class Digest
    {

        public static String[] allFromKeymaster(Collection collection)
        {
            if(collection.isEmpty())
                return EmptyArray.STRING;
            String as[] = new String[collection.size()];
            int i = 0;
            for(collection = collection.iterator(); collection.hasNext();)
            {
                as[i] = fromKeymaster(((Integer)collection.next()).intValue());
                i++;
            }

            return as;
        }

        public static int[] allToKeymaster(String as[])
        {
            int i = 0;
            if(as == null || as.length == 0)
                return EmptyArray.INT;
            int ai[] = new int[as.length];
            int j = 0;
            for(int k = as.length; i < k; i++)
            {
                ai[j] = toKeymaster(as[i]);
                j++;
            }

            return ai;
        }

        public static String fromKeymaster(int i)
        {
            switch(i)
            {
            default:
                throw new IllegalArgumentException((new StringBuilder()).append("Unsupported digest algorithm: ").append(i).toString());

            case 0: // '\0'
                return "NONE";

            case 1: // '\001'
                return "MD5";

            case 2: // '\002'
                return "SHA-1";

            case 3: // '\003'
                return "SHA-224";

            case 4: // '\004'
                return "SHA-256";

            case 5: // '\005'
                return "SHA-384";

            case 6: // '\006'
                return "SHA-512";
            }
        }

        public static String fromKeymasterToSignatureAlgorithmDigest(int i)
        {
            switch(i)
            {
            default:
                throw new IllegalArgumentException((new StringBuilder()).append("Unsupported digest algorithm: ").append(i).toString());

            case 0: // '\0'
                return "NONE";

            case 1: // '\001'
                return "MD5";

            case 2: // '\002'
                return "SHA1";

            case 3: // '\003'
                return "SHA224";

            case 4: // '\004'
                return "SHA256";

            case 5: // '\005'
                return "SHA384";

            case 6: // '\006'
                return "SHA512";
            }
        }

        public static int toKeymaster(String s)
        {
            String s1 = s.toUpperCase(Locale.US);
            if(s1.equals("SHA-1"))
                return 2;
            if(s1.equals("SHA-224"))
                return 3;
            if(s1.equals("SHA-256"))
                return 4;
            if(s1.equals("SHA-384"))
                return 5;
            if(s1.equals("SHA-512"))
                return 6;
            if(s1.equals("NONE"))
                return 0;
            if(s1.equals("MD5"))
                return 1;
            else
                throw new IllegalArgumentException((new StringBuilder()).append("Unsupported digest algorithm: ").append(s).toString());
        }

        private Digest()
        {
        }
    }

    public static abstract class EncryptionPadding
    {

        public static int[] allToKeymaster(String as[])
        {
            if(as == null || as.length == 0)
                return EmptyArray.INT;
            int ai[] = new int[as.length];
            for(int i = 0; i < as.length; i++)
                ai[i] = toKeymaster(as[i]);

            return ai;
        }

        public static String fromKeymaster(int i)
        {
            switch(i)
            {
            default:
                throw new IllegalArgumentException((new StringBuilder()).append("Unsupported encryption padding: ").append(i).toString());

            case 1: // '\001'
                return "NoPadding";

            case 64: // '@'
                return "PKCS7Padding";

            case 4: // '\004'
                return "PKCS1Padding";

            case 2: // '\002'
                return "OAEPPadding";
            }
        }

        public static int toKeymaster(String s)
        {
            if("NoPadding".equalsIgnoreCase(s))
                return 1;
            if("PKCS7Padding".equalsIgnoreCase(s))
                return 64;
            if("PKCS1Padding".equalsIgnoreCase(s))
                return 4;
            if("OAEPPadding".equalsIgnoreCase(s))
                return 2;
            else
                throw new IllegalArgumentException((new StringBuilder()).append("Unsupported encryption padding scheme: ").append(s).toString());
        }

        private EncryptionPadding()
        {
        }
    }

    public static abstract class KeyAlgorithm
    {

        public static String fromKeymasterAsymmetricKeyAlgorithm(int i)
        {
            switch(i)
            {
            case 2: // '\002'
            default:
                throw new IllegalArgumentException((new StringBuilder()).append("Unsupported key algorithm: ").append(i).toString());

            case 3: // '\003'
                return "EC";

            case 1: // '\001'
                return "RSA";
            }
        }

        public static String fromKeymasterSecretKeyAlgorithm(int i, int j)
        {
            switch(i)
            {
            default:
                throw new IllegalArgumentException((new StringBuilder()).append("Unsupported key algorithm: ").append(i).toString());

            case 32: // ' '
                return "AES";

            case 128: 
                switch(j)
                {
                default:
                    throw new IllegalArgumentException((new StringBuilder()).append("Unsupported HMAC digest: ").append(Digest.fromKeymaster(j)).toString());

                case 2: // '\002'
                    return "HmacSHA1";

                case 3: // '\003'
                    return "HmacSHA224";

                case 4: // '\004'
                    return "HmacSHA256";

                case 5: // '\005'
                    return "HmacSHA384";

                case 6: // '\006'
                    return "HmacSHA512";
                }
            }
        }

        public static int toKeymasterAsymmetricKeyAlgorithm(String s)
        {
            if("EC".equalsIgnoreCase(s))
                return 3;
            if("RSA".equalsIgnoreCase(s))
                return 1;
            else
                throw new IllegalArgumentException((new StringBuilder()).append("Unsupported key algorithm: ").append(s).toString());
        }

        public static int toKeymasterDigest(String s)
        {
            s = s.toUpperCase(Locale.US);
            if(s.startsWith("HMAC"))
            {
                s = s.substring("HMAC".length());
                if(s.equals("SHA1"))
                    return 2;
                if(s.equals("SHA224"))
                    return 3;
                if(s.equals("SHA256"))
                    return 4;
                if(s.equals("SHA384"))
                    return 5;
                if(s.equals("SHA512"))
                    return 6;
                else
                    throw new IllegalArgumentException((new StringBuilder()).append("Unsupported HMAC digest: ").append(s).toString());
            } else
            {
                return -1;
            }
        }

        public static int toKeymasterSecretKeyAlgorithm(String s)
        {
            if("AES".equalsIgnoreCase(s))
                return 32;
            if(s.toUpperCase(Locale.US).startsWith("HMAC"))
                return 128;
            else
                throw new IllegalArgumentException((new StringBuilder()).append("Unsupported secret key algorithm: ").append(s).toString());
        }

        private KeyAlgorithm()
        {
        }
    }

    public static abstract class Origin
    {

        public static int fromKeymaster(int i)
        {
            switch(i)
            {
            case 1: // '\001'
            default:
                throw new IllegalArgumentException((new StringBuilder()).append("Unknown origin: ").append(i).toString());

            case 0: // '\0'
                return 1;

            case 2: // '\002'
                return 2;

            case 3: // '\003'
                return 4;
            }
        }

        private Origin()
        {
        }
    }

    public static abstract class Purpose
    {

        public static int allFromKeymaster(Collection collection)
        {
            int i = 0;
            for(collection = collection.iterator(); collection.hasNext();)
                i |= fromKeymaster(((Integer)collection.next()).intValue());

            return i;
        }

        public static int[] allToKeymaster(int i)
        {
            int ai[] = KeyProperties._2D_wrap0(i);
            for(i = 0; i < ai.length; i++)
                ai[i] = toKeymaster(ai[i]);

            return ai;
        }

        public static int fromKeymaster(int i)
        {
            switch(i)
            {
            default:
                throw new IllegalArgumentException((new StringBuilder()).append("Unknown purpose: ").append(i).toString());

            case 0: // '\0'
                return 1;

            case 1: // '\001'
                return 2;

            case 2: // '\002'
                return 4;

            case 3: // '\003'
                return 8;
            }
        }

        public static int toKeymaster(int i)
        {
            switch(i)
            {
            case 3: // '\003'
            case 5: // '\005'
            case 6: // '\006'
            case 7: // '\007'
            default:
                throw new IllegalArgumentException((new StringBuilder()).append("Unknown purpose: ").append(i).toString());

            case 1: // '\001'
                return 0;

            case 2: // '\002'
                return 1;

            case 4: // '\004'
                return 2;

            case 8: // '\b'
                return 3;
            }
        }

        private Purpose()
        {
        }
    }

    static abstract class SignaturePadding
    {

        static int[] allToKeymaster(String as[])
        {
            if(as == null || as.length == 0)
                return EmptyArray.INT;
            int ai[] = new int[as.length];
            for(int i = 0; i < as.length; i++)
                ai[i] = toKeymaster(as[i]);

            return ai;
        }

        static String fromKeymaster(int i)
        {
            switch(i)
            {
            case 4: // '\004'
            default:
                throw new IllegalArgumentException((new StringBuilder()).append("Unsupported signature padding: ").append(i).toString());

            case 5: // '\005'
                return "PKCS1";

            case 3: // '\003'
                return "PSS";
            }
        }

        static int toKeymaster(String s)
        {
            String s1 = s.toUpperCase(Locale.US);
            if(s1.equals("PKCS1"))
                return 5;
            if(s1.equals("PSS"))
                return 3;
            else
                throw new IllegalArgumentException((new StringBuilder()).append("Unsupported signature padding scheme: ").append(s).toString());
        }

        private SignaturePadding()
        {
        }
    }


    static int[] _2D_wrap0(int i)
    {
        return getSetFlags(i);
    }

    private KeyProperties()
    {
    }

    private static int getSetBitCount(int i)
    {
        if(i == 0)
            return 0;
        int j;
        int k;
        for(j = 0; i != 0; j = k)
        {
            k = j;
            if((i & 1) != 0)
                k = j + 1;
            i >>>= 1;
        }

        return j;
    }

    private static int[] getSetFlags(int i)
    {
        if(i == 0)
            return EmptyArray.INT;
        int ai[] = new int[getSetBitCount(i)];
        int j = 0;
        int k = 1;
        while(i != 0) 
        {
            int l = j;
            if((i & 1) != 0)
            {
                ai[j] = k;
                l = j + 1;
            }
            i >>>= 1;
            k <<= 1;
            j = l;
        }
        return ai;
    }

    public static final String BLOCK_MODE_CBC = "CBC";
    public static final String BLOCK_MODE_CTR = "CTR";
    public static final String BLOCK_MODE_ECB = "ECB";
    public static final String BLOCK_MODE_GCM = "GCM";
    public static final String DIGEST_MD5 = "MD5";
    public static final String DIGEST_NONE = "NONE";
    public static final String DIGEST_SHA1 = "SHA-1";
    public static final String DIGEST_SHA224 = "SHA-224";
    public static final String DIGEST_SHA256 = "SHA-256";
    public static final String DIGEST_SHA384 = "SHA-384";
    public static final String DIGEST_SHA512 = "SHA-512";
    public static final String ENCRYPTION_PADDING_NONE = "NoPadding";
    public static final String ENCRYPTION_PADDING_PKCS7 = "PKCS7Padding";
    public static final String ENCRYPTION_PADDING_RSA_OAEP = "OAEPPadding";
    public static final String ENCRYPTION_PADDING_RSA_PKCS1 = "PKCS1Padding";
    public static final String KEY_ALGORITHM_AES = "AES";
    public static final String KEY_ALGORITHM_EC = "EC";
    public static final String KEY_ALGORITHM_HMAC_SHA1 = "HmacSHA1";
    public static final String KEY_ALGORITHM_HMAC_SHA224 = "HmacSHA224";
    public static final String KEY_ALGORITHM_HMAC_SHA256 = "HmacSHA256";
    public static final String KEY_ALGORITHM_HMAC_SHA384 = "HmacSHA384";
    public static final String KEY_ALGORITHM_HMAC_SHA512 = "HmacSHA512";
    public static final String KEY_ALGORITHM_RSA = "RSA";
    public static final int ORIGIN_GENERATED = 1;
    public static final int ORIGIN_IMPORTED = 2;
    public static final int ORIGIN_UNKNOWN = 4;
    public static final int PURPOSE_DECRYPT = 2;
    public static final int PURPOSE_ENCRYPT = 1;
    public static final int PURPOSE_SIGN = 4;
    public static final int PURPOSE_VERIFY = 8;
    public static final String SIGNATURE_PADDING_RSA_PKCS1 = "PKCS1";
    public static final String SIGNATURE_PADDING_RSA_PSS = "PSS";
}
