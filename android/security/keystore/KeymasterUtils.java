// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.security.GateKeeper;
import android.security.KeyStore;
import android.security.keymaster.KeymasterArguments;
import com.android.internal.util.ArrayUtils;
import java.security.ProviderException;

public abstract class KeymasterUtils
{

    private KeymasterUtils()
    {
    }

    public static void addMinMacLengthAuthorizationIfNecessary(KeymasterArguments keymasterarguments, int i, int ai[], int ai1[])
    {
        i;
        JVM INSTR lookupswitch 2: default 28
    //                   32: 29
    //                   128: 50;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        if(ArrayUtils.contains(ai, 32))
            keymasterarguments.addUnsignedInt(0x30000008, 96L);
        continue; /* Loop/switch isn't completed */
_L3:
        if(ai1.length != 1)
            throw new ProviderException((new StringBuilder()).append("Unsupported number of authorized digests for HMAC key: ").append(ai1.length).append(". Exactly one digest must be authorized").toString());
        int j = ai1[0];
        i = getDigestOutputSizeBits(j);
        if(i == -1)
            throw new ProviderException((new StringBuilder()).append("HMAC key authorized for unsupported digest: ").append(KeyProperties.Digest.fromKeymaster(j)).toString());
        keymasterarguments.addUnsignedInt(0x30000008, i);
        if(true) goto _L1; else goto _L4
_L4:
    }

    public static void addUserAuthArgs(KeymasterArguments keymasterarguments, boolean flag, int i, boolean flag1, boolean flag2, long l)
    {
        if(!flag)
        {
            keymasterarguments.addBoolean(0x700001f7);
            return;
        }
        if(i == -1)
        {
            FingerprintManager fingerprintmanager = (FingerprintManager)KeyStore.getApplicationContext().getSystemService(android/hardware/fingerprint/FingerprintManager);
            long l1;
            if(fingerprintmanager != null)
                l1 = fingerprintmanager.getAuthenticatorId();
            else
                l1 = 0L;
            if(l1 == 0L)
                throw new IllegalStateException("At least one fingerprint must be enrolled to create keys requiring user authentication for every use");
            if(l == 0L)
                if(flag2)
                    l = l1;
                else
                    l = getRootSid();
            keymasterarguments.addUnsignedLong(0xa00001f6, KeymasterArguments.toUint64(l));
            keymasterarguments.addEnum(0x100001f8, 2);
            if(flag1)
                throw new ProviderException("Key validity extension while device is on-body is not supported for keys requiring fingerprint authentication");
        } else
        {
            if(l == 0L)
                l = getRootSid();
            keymasterarguments.addUnsignedLong(0xa00001f6, KeymasterArguments.toUint64(l));
            keymasterarguments.addEnum(0x100001f8, 3);
            keymasterarguments.addUnsignedInt(0x300001f9, i);
            if(flag1)
                keymasterarguments.addBoolean(0x700001fa);
        }
    }

    public static int getDigestOutputSizeBits(int i)
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown digest: ").append(i).toString());

        case 0: // '\0'
            return -1;

        case 1: // '\001'
            return 128;

        case 2: // '\002'
            return 160;

        case 3: // '\003'
            return 224;

        case 4: // '\004'
            return 256;

        case 5: // '\005'
            return 384;

        case 6: // '\006'
            return 512;
        }
    }

    private static long getRootSid()
    {
        long l = GateKeeper.getSecureUserId();
        if(l == 0L)
            throw new IllegalStateException("Secure lock screen must be enabled to create keys requiring user authentication");
        else
            return l;
    }

    public static boolean isKeymasterBlockModeIndCpaCompatibleWithSymmetricCrypto(int i)
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Unsupported block mode: ").append(i).toString());

        case 1: // '\001'
            return false;

        case 2: // '\002'
        case 3: // '\003'
        case 32: // ' '
            return true;
        }
    }

    public static boolean isKeymasterPaddingSchemeIndCpaCompatibleWithAsymmetricCrypto(int i)
    {
        switch(i)
        {
        case 3: // '\003'
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Unsupported asymmetric encryption padding scheme: ").append(i).toString());

        case 1: // '\001'
            return false;

        case 2: // '\002'
        case 4: // '\004'
            return true;
        }
    }
}
