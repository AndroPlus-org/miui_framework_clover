// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import android.security.keymaster.KeymasterArguments;
import java.security.InvalidKeyException;

// Referenced classes of package android.security.keystore:
//            AndroidKeyStoreSignatureSpiBase, AndroidKeyStoreKey

abstract class AndroidKeyStoreRSASignatureSpi extends AndroidKeyStoreSignatureSpiBase
{
    public static final class MD5WithPKCS1Padding extends PKCS1Padding
    {

        public MD5WithPKCS1Padding()
        {
            super(1);
        }
    }

    public static final class NONEWithPKCS1Padding extends PKCS1Padding
    {

        public NONEWithPKCS1Padding()
        {
            super(0);
        }
    }

    static abstract class PKCS1Padding extends AndroidKeyStoreRSASignatureSpi
    {

        protected final int getAdditionalEntropyAmountForSign()
        {
            return 0;
        }

        PKCS1Padding(int i)
        {
            super(i, 5);
        }
    }

    static abstract class PSSPadding extends AndroidKeyStoreRSASignatureSpi
    {

        protected final int getAdditionalEntropyAmountForSign()
        {
            return 20;
        }

        private static final int SALT_LENGTH_BYTES = 20;

        PSSPadding(int i)
        {
            super(i, 3);
        }
    }

    public static final class SHA1WithPKCS1Padding extends PKCS1Padding
    {

        public SHA1WithPKCS1Padding()
        {
            super(2);
        }
    }

    public static final class SHA1WithPSSPadding extends PSSPadding
    {

        public SHA1WithPSSPadding()
        {
            super(2);
        }
    }

    public static final class SHA224WithPKCS1Padding extends PKCS1Padding
    {

        public SHA224WithPKCS1Padding()
        {
            super(3);
        }
    }

    public static final class SHA224WithPSSPadding extends PSSPadding
    {

        public SHA224WithPSSPadding()
        {
            super(3);
        }
    }

    public static final class SHA256WithPKCS1Padding extends PKCS1Padding
    {

        public SHA256WithPKCS1Padding()
        {
            super(4);
        }
    }

    public static final class SHA256WithPSSPadding extends PSSPadding
    {

        public SHA256WithPSSPadding()
        {
            super(4);
        }
    }

    public static final class SHA384WithPKCS1Padding extends PKCS1Padding
    {

        public SHA384WithPKCS1Padding()
        {
            super(5);
        }
    }

    public static final class SHA384WithPSSPadding extends PSSPadding
    {

        public SHA384WithPSSPadding()
        {
            super(5);
        }
    }

    public static final class SHA512WithPKCS1Padding extends PKCS1Padding
    {

        public SHA512WithPKCS1Padding()
        {
            super(6);
        }
    }

    public static final class SHA512WithPSSPadding extends PSSPadding
    {

        public SHA512WithPSSPadding()
        {
            super(6);
        }
    }


    AndroidKeyStoreRSASignatureSpi(int i, int j)
    {
        mKeymasterDigest = i;
        mKeymasterPadding = j;
    }

    protected final void addAlgorithmSpecificParametersToBegin(KeymasterArguments keymasterarguments)
    {
        keymasterarguments.addEnum(0x10000002, 1);
        keymasterarguments.addEnum(0x20000005, mKeymasterDigest);
        keymasterarguments.addEnum(0x20000006, mKeymasterPadding);
    }

    protected final void initKey(AndroidKeyStoreKey androidkeystorekey)
        throws InvalidKeyException
    {
        if(!"RSA".equalsIgnoreCase(androidkeystorekey.getAlgorithm()))
        {
            throw new InvalidKeyException((new StringBuilder()).append("Unsupported key algorithm: ").append(androidkeystorekey.getAlgorithm()).append(". Only").append("RSA").append(" supported").toString());
        } else
        {
            super.initKey(androidkeystorekey);
            return;
        }
    }

    protected final void resetAll()
    {
        super.resetAll();
    }

    protected final void resetWhilePreservingInitState()
    {
        super.resetWhilePreservingInitState();
    }

    private final int mKeymasterDigest;
    private final int mKeymasterPadding;
}
