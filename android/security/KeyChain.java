// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.*;
import android.net.Uri;
import android.os.*;
import android.security.keystore.AndroidKeyStoreProvider;
import com.android.org.conscrypt.TrustedCertificateStore;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.security.*;
import java.security.cert.*;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

// Referenced classes of package android.security:
//            IKeyChainService, KeyChainException, KeyStore, KeyChainAliasCallback

public final class KeyChain
{
    private static class AliasResponse extends IKeyChainAliasCallback.Stub
    {

        public void alias(String s)
        {
            keyChainAliasResponse.alias(s);
        }

        private final KeyChainAliasCallback keyChainAliasResponse;

        private AliasResponse(KeyChainAliasCallback keychainaliascallback)
        {
            keyChainAliasResponse = keychainaliascallback;
        }

        AliasResponse(KeyChainAliasCallback keychainaliascallback, AliasResponse aliasresponse)
        {
            this(keychainaliascallback);
        }
    }

    public static class KeyChainConnection
        implements Closeable
    {

        public void close()
        {
            context.unbindService(serviceConnection);
        }

        public IKeyChainService getService()
        {
            return service;
        }

        private final Context context;
        private final IKeyChainService service;
        private final ServiceConnection serviceConnection;

        protected KeyChainConnection(Context context1, ServiceConnection serviceconnection, IKeyChainService ikeychainservice)
        {
            context = context1;
            serviceConnection = serviceconnection;
            service = ikeychainservice;
        }
    }


    public KeyChain()
    {
    }

    public static KeyChainConnection bind(Context context)
        throws InterruptedException
    {
        return bindAsUser(context, Process.myUserHandle());
    }

    public static KeyChainConnection bindAsUser(Context context, UserHandle userhandle)
        throws InterruptedException
    {
        if(context == null)
            throw new NullPointerException("context == null");
        ensureNotOnMainThread(context);
        LinkedBlockingQueue linkedblockingqueue = new LinkedBlockingQueue(1);
        ServiceConnection serviceconnection = new ServiceConnection(linkedblockingqueue) {

            public void onServiceConnected(ComponentName componentname1, IBinder ibinder)
            {
                if(mConnectedAtLeastOnce)
                    break MISSING_BLOCK_LABEL_28;
                mConnectedAtLeastOnce = true;
                q.put(IKeyChainService.Stub.asInterface(Binder.allowBlocking(ibinder)));
_L2:
                return;
                componentname1;
                if(true) goto _L2; else goto _L1
_L1:
            }

            public void onServiceDisconnected(ComponentName componentname1)
            {
            }

            volatile boolean mConnectedAtLeastOnce;
            final BlockingQueue val$q;

            
            {
                q = blockingqueue;
                super();
                mConnectedAtLeastOnce = false;
            }
        }
;
        Intent intent = new Intent(android/security/IKeyChainService.getName());
        ComponentName componentname = intent.resolveSystemService(context.getPackageManager(), 0);
        intent.setComponent(componentname);
        if(componentname == null || context.bindServiceAsUser(intent, serviceconnection, 1, userhandle) ^ true)
            throw new AssertionError("could not bind to KeyChainService");
        else
            return new KeyChainConnection(context, serviceconnection, (IKeyChainService)linkedblockingqueue.take());
    }

    public static void choosePrivateKeyAlias(Activity activity, KeyChainAliasCallback keychainaliascallback, String as[], Principal aprincipal[], Uri uri, String s)
    {
        if(activity == null)
            throw new NullPointerException("activity == null");
        if(keychainaliascallback == null)
        {
            throw new NullPointerException("response == null");
        } else
        {
            as = new Intent("com.android.keychain.CHOOSER");
            as.setPackage("com.android.keychain");
            as.putExtra("response", new AliasResponse(keychainaliascallback, null));
            as.putExtra("uri", uri);
            as.putExtra("alias", s);
            as.putExtra("sender", PendingIntent.getActivity(activity, 0, new Intent(), 0));
            activity.startActivity(as);
            return;
        }
    }

    public static void choosePrivateKeyAlias(Activity activity, KeyChainAliasCallback keychainaliascallback, String as[], Principal aprincipal[], String s, int i, String s1)
    {
        Object obj = null;
        if(s != null)
        {
            obj = new android.net.Uri.Builder();
            StringBuilder stringbuilder = (new StringBuilder()).append(s);
            if(i != -1)
                s = (new StringBuilder()).append(":").append(i).toString();
            else
                s = "";
            obj = ((android.net.Uri.Builder) (obj)).authority(stringbuilder.append(s).toString()).build();
        }
        choosePrivateKeyAlias(activity, keychainaliascallback, as, aprincipal, ((Uri) (obj)), s1);
    }

    public static Intent createInstallIntent()
    {
        Intent intent = new Intent("android.credentials.INSTALL");
        intent.setClassName("com.android.certinstaller", "com.android.certinstaller.CertInstallerMain");
        return intent;
    }

    private static void ensureNotOnMainThread(Context context)
    {
        Looper looper = Looper.myLooper();
        if(looper != null && looper == context.getMainLooper())
            throw new IllegalStateException("calling this from your main thread can lead to deadlock");
        else
            return;
    }

    public static X509Certificate[] getCertificateChain(Context context, String s)
        throws KeyChainException, InterruptedException
    {
        Object obj;
        Object obj1;
        byte abyte0[];
        Object obj2;
        Object obj3;
        if(s == null)
            throw new NullPointerException("alias == null");
        obj = null;
        obj1 = null;
        abyte0 = null;
        obj2 = null;
        obj3 = null;
        context = bind(context.getApplicationContext());
        obj3 = context;
        obj2 = context;
        IKeyChainService ikeychainservice = context.getService();
        obj3 = context;
        obj2 = context;
        byte abyte1[] = ikeychainservice.getCertificate(s);
        if(abyte1 != null)
            break MISSING_BLOCK_LABEL_104;
        s = abyte0;
        if(context == null)
            break MISSING_BLOCK_LABEL_82;
        context.close();
        s = abyte0;
_L1:
        if(s != null)
            try
            {
                throw s;
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                throw new KeyChainException(context);
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                throw new KeyChainException(context);
            }
        else
            return null;
        s;
          goto _L1
        obj3 = context;
        obj2 = context;
        abyte0 = ikeychainservice.getCaCertificates(s);
        s = obj;
        if(context == null)
            break MISSING_BLOCK_LABEL_132;
        context.close();
        s = obj;
_L3:
        if(s == null)
            break; /* Loop/switch isn't completed */
        throw s;
        s;
        if(true) goto _L3; else goto _L2
        context;
        throw context;
        s;
_L7:
        obj2 = context;
        if(obj3 == null)
            break MISSING_BLOCK_LABEL_172;
        ((KeyChainConnection) (obj3)).close();
        obj2 = context;
_L5:
        if(obj2 == null)
            break; /* Loop/switch isn't completed */
        throw obj2;
        obj3;
        if(context == null)
        {
            obj2 = obj3;
            continue; /* Loop/switch isn't completed */
        }
        obj2 = context;
        if(context == obj3)
            continue; /* Loop/switch isn't completed */
        context.addSuppressed(((Throwable) (obj3)));
        obj2 = context;
        if(true) goto _L5; else goto _L4
_L4:
        throw s;
_L2:
        try
        {
            context = toCertificate(abyte1);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw new KeyChainException(context);
        }
        if(abyte0 == null)
            break MISSING_BLOCK_LABEL_285;
        if(abyte0.length != 0)
        {
            obj3 = toCertificates(abyte0);
            s = JVM INSTR new #287 <Class ArrayList>;
            s.ArrayList(((Collection) (obj3)).size() + 1);
            s.add(context);
            s.addAll(((Collection) (obj3)));
            return (X509Certificate[])s.toArray(new X509Certificate[s.size()]);
        }
        s = JVM INSTR new #313 <Class TrustedCertificateStore>;
        s.TrustedCertificateStore();
        context = s.getCertificateChain(context);
        context = (X509Certificate[])context.toArray(new X509Certificate[context.size()]);
        return context;
        s;
        obj3 = obj2;
        context = obj1;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public static PrivateKey getPrivateKey(Context context, String s)
        throws KeyChainException, InterruptedException
    {
        Object obj;
        Object obj1;
        Object obj2;
        obj = null;
        if(s == null)
            throw new NullPointerException("alias == null");
        if(context == null)
            throw new NullPointerException("context == null");
        obj1 = null;
        obj2 = null;
        context = bind(context.getApplicationContext());
        obj2 = context;
        obj1 = context;
        s = context.getService().requestPrivateKey(s);
        if(context == null)
            break MISSING_BLOCK_LABEL_67;
        context.close();
        context = null;
        break MISSING_BLOCK_LABEL_69;
        context;
          goto _L1
        context;
        throw context;
        s;
_L5:
        obj1 = context;
        if(obj2 == null)
            break MISSING_BLOCK_LABEL_107;
        ((KeyChainConnection) (obj2)).close();
        obj1 = context;
_L3:
        if(obj1 == null)
            break; /* Loop/switch isn't completed */
        throw obj1;
        obj2;
        if(context == null)
        {
            obj1 = obj2;
            continue; /* Loop/switch isn't completed */
        }
        obj1 = context;
        if(context == obj2)
            continue; /* Loop/switch isn't completed */
        context.addSuppressed(((Throwable) (obj2)));
        obj1 = context;
        if(true) goto _L3; else goto _L2
_L2:
        throw s;
_L1:
        if(context != null)
            try
            {
                throw context;
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                throw new KeyChainException(context);
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                throw new KeyChainException(context);
            }
        if(s == null)
            return null;
        try
        {
            context = AndroidKeyStoreProvider.loadAndroidKeyStorePrivateKeyFromKeystore(KeyStore.getInstance(), s, -1);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw new KeyChainException(context);
        }
        return context;
        s;
        obj2 = obj1;
        context = obj;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public static boolean isBoundKeyAlgorithm(String s)
    {
        if(!isKeyAlgorithmSupported(s))
            return false;
        else
            return KeyStore.getInstance().isHardwareBacked(s);
    }

    public static boolean isKeyAlgorithmSupported(String s)
    {
        s = s.toUpperCase(Locale.US);
        boolean flag;
        if(!"EC".equals(s))
            flag = "RSA".equals(s);
        else
            flag = true;
        return flag;
    }

    public static X509Certificate toCertificate(byte abyte0[])
    {
        if(abyte0 == null)
            throw new IllegalArgumentException("bytes == null");
        try
        {
            CertificateFactory certificatefactory = CertificateFactory.getInstance("X.509");
            ByteArrayInputStream bytearrayinputstream = JVM INSTR new #384 <Class ByteArrayInputStream>;
            bytearrayinputstream.ByteArrayInputStream(abyte0);
            abyte0 = (X509Certificate)certificatefactory.generateCertificate(bytearrayinputstream);
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            throw new AssertionError(abyte0);
        }
        return abyte0;
    }

    public static Collection toCertificates(byte abyte0[])
    {
        if(abyte0 == null)
            throw new IllegalArgumentException("bytes == null");
        try
        {
            CertificateFactory certificatefactory = CertificateFactory.getInstance("X.509");
            ByteArrayInputStream bytearrayinputstream = JVM INSTR new #384 <Class ByteArrayInputStream>;
            bytearrayinputstream.ByteArrayInputStream(abyte0);
            abyte0 = certificatefactory.generateCertificates(bytearrayinputstream);
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            throw new AssertionError(abyte0);
        }
        return abyte0;
    }

    public static final String ACCOUNT_TYPE = "com.android.keychain";
    private static final String ACTION_CHOOSER = "com.android.keychain.CHOOSER";
    private static final String ACTION_INSTALL = "android.credentials.INSTALL";
    public static final String ACTION_KEYCHAIN_CHANGED = "android.security.action.KEYCHAIN_CHANGED";
    public static final String ACTION_KEY_ACCESS_CHANGED = "android.security.action.KEY_ACCESS_CHANGED";
    public static final String ACTION_STORAGE_CHANGED = "android.security.STORAGE_CHANGED";
    public static final String ACTION_TRUST_STORE_CHANGED = "android.security.action.TRUST_STORE_CHANGED";
    private static final String CERT_INSTALLER_PACKAGE = "com.android.certinstaller";
    public static final String EXTRA_ALIAS = "alias";
    public static final String EXTRA_CERTIFICATE = "CERT";
    public static final String EXTRA_KEY_ACCESSIBLE = "android.security.extra.KEY_ACCESSIBLE";
    public static final String EXTRA_KEY_ALIAS = "android.security.extra.KEY_ALIAS";
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_PKCS12 = "PKCS12";
    public static final String EXTRA_RESPONSE = "response";
    public static final String EXTRA_SENDER = "sender";
    public static final String EXTRA_URI = "uri";
    private static final String KEYCHAIN_PACKAGE = "com.android.keychain";
}
