// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import android.security.KeyStore;
import java.security.*;

// Referenced classes of package android.security.keystore:
//            AndroidKeyStoreSpi, SoterKeyStoreProvider, AndroidKeyStoreProvider

public class SoterKeyStoreSpi extends AndroidKeyStoreSpi
{

    public SoterKeyStoreSpi()
    {
        mKeyStore = null;
        mKeyStore = KeyStore.getInstance();
    }

    private boolean isPrivateKeyEntry(String s)
    {
        if(s == null)
            throw new NullPointerException("alias == null");
        else
            return mKeyStore.contains((new StringBuilder()).append("USRPKEY_").append(s).toString());
    }

    private boolean isSecretKeyEntry(String s)
    {
        if(s == null)
            throw new NullPointerException("alias == null");
        else
            return mKeyStore.contains((new StringBuilder()).append("USRSKEY_").append(s).toString());
    }

    public boolean engineContainsAlias(String s)
    {
        if(s == null)
            throw new NullPointerException("alias == null");
        boolean flag;
        if(!mKeyStore.contains((new StringBuilder()).append("USRPKEY_").append(s).toString()))
            flag = mKeyStore.contains((new StringBuilder()).append("USRCERT_").append(s).toString());
        else
            flag = true;
        return flag;
    }

    public void engineDeleteEntry(String s)
        throws KeyStoreException
    {
        if(!engineContainsAlias(s))
            return;
        if(!(mKeyStore.delete((new StringBuilder()).append("USRPKEY_").append(s).toString()) | mKeyStore.delete((new StringBuilder()).append("USRCERT_").append(s).toString())))
            throw new KeyStoreException((new StringBuilder()).append("Failed to delete entry: ").append(s).toString());
        else
            return;
    }

    public Key engineGetKey(String s, char ac[])
        throws NoSuchAlgorithmException, UnrecoverableKeyException
    {
        if(isPrivateKeyEntry(s))
        {
            s = (new StringBuilder()).append("USRPKEY_").append(s).toString();
            if(ac != null && "from_soter_ui".equals(String.valueOf(ac)))
                return SoterKeyStoreProvider.loadJsonPublicKeyFromKeystore(mKeyStore, s);
            else
                return SoterKeyStoreProvider.loadAndroidKeyStorePrivateKeyFromKeystore(mKeyStore, s);
        }
        if(isSecretKeyEntry(s))
        {
            s = (new StringBuilder()).append("USRSKEY_").append(s).toString();
            return AndroidKeyStoreProvider.loadAndroidKeyStoreSecretKeyFromKeystore(mKeyStore, s, -1);
        } else
        {
            return null;
        }
    }

    private KeyStore mKeyStore;
}
