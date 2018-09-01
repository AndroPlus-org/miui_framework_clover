// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security;

import android.app.ActivityThread;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.*;
import android.security.keymaster.ExportResult;
import android.security.keymaster.KeyCharacteristics;
import android.security.keymaster.KeymasterArguments;
import android.security.keymaster.KeymasterBlob;
import android.security.keymaster.KeymasterCertificateChain;
import android.security.keymaster.KeymasterDefs;
import android.security.keymaster.OperationResult;
import android.security.keystore.KeyExpiredException;
import android.security.keystore.KeyNotYetValidException;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.UserNotAuthenticatedException;
import android.util.Log;
import java.security.InvalidKeyException;
import java.util.List;
import java.util.Locale;

// Referenced classes of package android.security:
//            KeyStoreException, IKeystoreService, KeystoreArguments, GateKeeper

public class KeyStore
{
    public static final class State extends Enum
    {

        public static State valueOf(String s)
        {
            return (State)Enum.valueOf(android/security/KeyStore$State, s);
        }

        public static State[] values()
        {
            return $VALUES;
        }

        private static final State $VALUES[];
        public static final State LOCKED;
        public static final State UNINITIALIZED;
        public static final State UNLOCKED;

        static 
        {
            UNLOCKED = new State("UNLOCKED", 0);
            LOCKED = new State("LOCKED", 1);
            UNINITIALIZED = new State("UNINITIALIZED", 2);
            $VALUES = (new State[] {
                UNLOCKED, LOCKED, UNINITIALIZED
            });
        }

        private State(String s, int i)
        {
            super(s, i);
        }
    }


    private KeyStore(IKeystoreService ikeystoreservice)
    {
        mError = 1;
        mBinder = ikeystoreservice;
    }

    public static Context getApplicationContext()
    {
        android.app.Application application = ActivityThread.currentApplication();
        if(application == null)
            throw new IllegalStateException("Failed to obtain application Context from ActivityThread");
        else
            return application;
    }

    private long getFingerprintOnlySid()
    {
        FingerprintManager fingerprintmanager = (FingerprintManager)mContext.getSystemService(android/hardware/fingerprint/FingerprintManager);
        if(fingerprintmanager == null)
            return 0L;
        else
            return fingerprintmanager.getAuthenticatorId();
    }

    public static KeyStore getInstance()
    {
        return new KeyStore(IKeystoreService.Stub.asInterface(ServiceManager.getService("android.security.keystore")));
    }

    public static KeyStoreException getKeyStoreException(int i)
    {
        if(i > 0)
            switch(i)
            {
            case 5: // '\005'
            case 9: // '\t'
            case 10: // '\n'
            case 11: // '\013'
            case 12: // '\f'
            case 13: // '\r'
            case 14: // '\016'
            default:
                return new KeyStoreException(i, String.valueOf(i));

            case 1: // '\001'
                return new KeyStoreException(i, "OK");

            case 2: // '\002'
                return new KeyStoreException(i, "User authentication required");

            case 3: // '\003'
                return new KeyStoreException(i, "Keystore not initialized");

            case 4: // '\004'
                return new KeyStoreException(i, "System error");

            case 6: // '\006'
                return new KeyStoreException(i, "Permission denied");

            case 7: // '\007'
                return new KeyStoreException(i, "Key not found");

            case 8: // '\b'
                return new KeyStoreException(i, "Key blob corrupted");

            case 15: // '\017'
                return new KeyStoreException(i, "Operation requires authorization");
            }
        switch(i)
        {
        default:
            return new KeyStoreException(i, KeymasterDefs.getErrorMessage(i));

        case -16: 
            return new KeyStoreException(i, "Invalid user authentication validity duration");
        }
    }

    private IBinder getToken()
    {
        this;
        JVM INSTR monitorenter ;
        IBinder ibinder;
        if(mToken == null)
        {
            Binder binder = JVM INSTR new #149 <Class Binder>;
            binder.Binder();
            mToken = binder;
        }
        ibinder = mToken;
        this;
        JVM INSTR monitorexit ;
        return ibinder;
        Exception exception;
        exception;
        throw exception;
    }

    public int abort(IBinder ibinder)
    {
        int i;
        try
        {
            i = mBinder.abort(ibinder);
        }
        // Misplaced declaration of an exception variable
        catch(IBinder ibinder)
        {
            Log.w("KeyStore", "Cannot connect to keystore", ibinder);
            return 4;
        }
        return i;
    }

    public int addAuthToken(byte abyte0[])
    {
        int i;
        try
        {
            i = mBinder.addAuthToken(abyte0);
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            Log.w("KeyStore", "Cannot connect to keystore", abyte0);
            return 4;
        }
        return i;
    }

    public boolean addRngEntropy(byte abyte0[])
    {
        boolean flag = true;
        int i;
        try
        {
            i = mBinder.addRngEntropy(abyte0);
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            Log.w("KeyStore", "Cannot connect to keystore", abyte0);
            return false;
        }
        if(i != 1)
            flag = false;
        return flag;
    }

    public int attestDeviceIds(KeymasterArguments keymasterarguments, KeymasterCertificateChain keymastercertificatechain)
    {
        int i;
        try
        {
            i = mBinder.attestDeviceIds(keymasterarguments, keymastercertificatechain);
        }
        // Misplaced declaration of an exception variable
        catch(KeymasterArguments keymasterarguments)
        {
            Log.w("KeyStore", "Cannot connect to keystore", keymasterarguments);
            return 4;
        }
        return i;
    }

    public int attestKey(String s, KeymasterArguments keymasterarguments, KeymasterCertificateChain keymastercertificatechain)
    {
        int i;
        try
        {
            i = mBinder.attestKey(s, keymasterarguments, keymastercertificatechain);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.w("KeyStore", "Cannot connect to keystore", s);
            return 4;
        }
        return i;
    }

    public OperationResult begin(String s, int i, boolean flag, KeymasterArguments keymasterarguments, byte abyte0[])
    {
        return begin(s, i, flag, keymasterarguments, abyte0, -1);
    }

    public OperationResult begin(String s, int i, boolean flag, KeymasterArguments keymasterarguments, byte abyte0[], int j)
    {
        try
        {
            s = mBinder.begin(getToken(), s, i, flag, keymasterarguments, abyte0, j);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.w("KeyStore", "Cannot connect to keystore", s);
            return null;
        }
        return s;
    }

    public boolean clearUid(int i)
    {
        boolean flag = true;
        try
        {
            i = mBinder.clear_uid(i);
        }
        catch(RemoteException remoteexception)
        {
            Log.w("KeyStore", "Cannot connect to keystore", remoteexception);
            return false;
        }
        if(i != 1)
            flag = false;
        return flag;
    }

    public boolean contains(String s)
    {
        return contains(s, -1);
    }

    public boolean contains(String s, int i)
    {
        boolean flag = true;
        try
        {
            i = mBinder.exist(s, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.w("KeyStore", "Cannot connect to keystore", s);
            return false;
        }
        if(i != 1)
            flag = false;
        return flag;
    }

    public boolean delete(String s)
    {
        return delete(s, -1);
    }

    public boolean delete(String s, int i)
    {
        boolean flag = true;
        boolean flag1;
        try
        {
            i = mBinder.del(s, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.w("KeyStore", "Cannot connect to keystore", s);
            return false;
        }
        flag1 = flag;
        if(i != 1)
            if(i == 7)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public boolean duplicate(String s, int i, String s1, int j)
    {
        boolean flag = true;
        try
        {
            i = mBinder.duplicate(s, i, s1, j);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.w("KeyStore", "Cannot connect to keystore", s);
            return false;
        }
        if(i != 1)
            flag = false;
        return flag;
    }

    public ExportResult exportKey(String s, int i, KeymasterBlob keymasterblob, KeymasterBlob keymasterblob1)
    {
        return exportKey(s, i, keymasterblob, keymasterblob1, -1);
    }

    public ExportResult exportKey(String s, int i, KeymasterBlob keymasterblob, KeymasterBlob keymasterblob1, int j)
    {
        try
        {
            s = mBinder.exportKey(s, i, keymasterblob, keymasterblob1, j);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.w("KeyStore", "Cannot connect to keystore", s);
            return null;
        }
        return s;
    }

    public OperationResult finish(IBinder ibinder, KeymasterArguments keymasterarguments, byte abyte0[])
    {
        return finish(ibinder, keymasterarguments, abyte0, null);
    }

    public OperationResult finish(IBinder ibinder, KeymasterArguments keymasterarguments, byte abyte0[], byte abyte1[])
    {
        try
        {
            ibinder = mBinder.finish(ibinder, keymasterarguments, abyte0, abyte1);
        }
        // Misplaced declaration of an exception variable
        catch(IBinder ibinder)
        {
            Log.w("KeyStore", "Cannot connect to keystore", ibinder);
            return null;
        }
        return ibinder;
    }

    public boolean generate(String s, int i, int j, int k, int l, byte abyte0[][])
    {
        boolean flag;
        try
        {
            IKeystoreService ikeystoreservice = mBinder;
            KeystoreArguments keystorearguments = JVM INSTR new #234 <Class KeystoreArguments>;
            keystorearguments.KeystoreArguments(abyte0);
            i = ikeystoreservice.generate(s, i, j, k, l, keystorearguments);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.w("KeyStore", "Cannot connect to keystore", s);
            return false;
        }
        if(i == 1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public int generateKey(String s, KeymasterArguments keymasterarguments, byte abyte0[], int i, int j, KeyCharacteristics keycharacteristics)
    {
        try
        {
            i = mBinder.generateKey(s, keymasterarguments, abyte0, i, j, keycharacteristics);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.w("KeyStore", "Cannot connect to keystore", s);
            return 4;
        }
        return i;
    }

    public int generateKey(String s, KeymasterArguments keymasterarguments, byte abyte0[], int i, KeyCharacteristics keycharacteristics)
    {
        return generateKey(s, keymasterarguments, abyte0, -1, i, keycharacteristics);
    }

    public byte[] get(String s)
    {
        return get(s, -1);
    }

    public byte[] get(String s, int i)
    {
        try
        {
            s = mBinder.get(s, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.w("KeyStore", "Cannot connect to keystore", s);
            return null;
        }
        return s;
    }

    public InvalidKeyException getInvalidKeyException(String s, int i, int j)
    {
        return getInvalidKeyException(s, i, getKeyStoreException(j));
    }

    public InvalidKeyException getInvalidKeyException(String s, int i, KeyStoreException keystoreexception)
    {
        switch(keystoreexception.getErrorCode())
        {
        default:
            return new InvalidKeyException("Keystore operation failed", keystoreexception);

        case 2: // '\002'
            return new UserNotAuthenticatedException();

        case -25: 
            return new KeyExpiredException();

        case -24: 
            return new KeyNotYetValidException();

        case -26: 
        case 15: // '\017'
            keystoreexception = new KeyCharacteristics();
            i = getKeyCharacteristics(s, null, null, i, keystoreexception);
            if(i != 1)
                return new InvalidKeyException("Failed to obtained key characteristics", getKeyStoreException(i));
            s = keystoreexception.getUnsignedLongs(0xa00001f6);
            if(s.isEmpty())
                return new KeyPermanentlyInvalidatedException();
            long l = GateKeeper.getSecureUserId();
            if(l != 0L && s.contains(KeymasterArguments.toUint64(l)))
                return new UserNotAuthenticatedException();
            l = getFingerprintOnlySid();
            if(l != 0L && s.contains(KeymasterArguments.toUint64(l)))
                return new UserNotAuthenticatedException();
            else
                return new KeyPermanentlyInvalidatedException();

        case 3: // '\003'
            return new KeyPermanentlyInvalidatedException();
        }
    }

    public int getKeyCharacteristics(String s, KeymasterBlob keymasterblob, KeymasterBlob keymasterblob1, int i, KeyCharacteristics keycharacteristics)
    {
        try
        {
            i = mBinder.getKeyCharacteristics(s, keymasterblob, keymasterblob1, i, keycharacteristics);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.w("KeyStore", "Cannot connect to keystore", s);
            return 4;
        }
        return i;
    }

    public int getKeyCharacteristics(String s, KeymasterBlob keymasterblob, KeymasterBlob keymasterblob1, KeyCharacteristics keycharacteristics)
    {
        return getKeyCharacteristics(s, keymasterblob, keymasterblob1, -1, keycharacteristics);
    }

    public int getLastError()
    {
        return mError;
    }

    public long getmtime(String s)
    {
        return getmtime(s, -1);
    }

    public long getmtime(String s, int i)
    {
        long l;
        try
        {
            l = mBinder.getmtime(s, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.w("KeyStore", "Cannot connect to keystore", s);
            return -1L;
        }
        if(l == -1L)
            return -1L;
        else
            return 1000L * l;
    }

    public String grant(String s, int i)
    {
        try
        {
            s = mBinder.grant(s, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.w("KeyStore", "Cannot connect to keystore", s);
            return null;
        }
        if(s == "")
            return null;
        else
            return s;
    }

    public int importKey(String s, KeymasterArguments keymasterarguments, int i, byte abyte0[], int j, int k, KeyCharacteristics keycharacteristics)
    {
        try
        {
            i = mBinder.importKey(s, keymasterarguments, i, abyte0, j, k, keycharacteristics);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.w("KeyStore", "Cannot connect to keystore", s);
            return 4;
        }
        return i;
    }

    public int importKey(String s, KeymasterArguments keymasterarguments, int i, byte abyte0[], int j, KeyCharacteristics keycharacteristics)
    {
        return importKey(s, keymasterarguments, i, abyte0, -1, j, keycharacteristics);
    }

    public boolean importKey(String s, byte abyte0[], int i, int j)
    {
        boolean flag = true;
        try
        {
            i = mBinder.import_key(s, abyte0, i, j);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.w("KeyStore", "Cannot connect to keystore", s);
            return false;
        }
        if(i != 1)
            flag = false;
        return flag;
    }

    public int insert(String s, byte abyte0[], int i, int j)
    {
        try
        {
            i = mBinder.insert(s, abyte0, i, j);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.w("KeyStore", "Cannot connect to keystore", s);
            return 4;
        }
        return i;
    }

    public boolean isEmpty()
    {
        return isEmpty(UserHandle.myUserId());
    }

    public boolean isEmpty(int i)
    {
        boolean flag = false;
        try
        {
            i = mBinder.isEmpty(i);
        }
        catch(RemoteException remoteexception)
        {
            Log.w("KeyStore", "Cannot connect to keystore", remoteexception);
            return false;
        }
        if(i != 0)
            flag = true;
        return flag;
    }

    public boolean isHardwareBacked()
    {
        return isHardwareBacked("RSA");
    }

    public boolean isHardwareBacked(String s)
    {
        boolean flag = true;
        int i;
        try
        {
            i = mBinder.is_hardware_backed(s.toUpperCase(Locale.US));
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.w("KeyStore", "Cannot connect to keystore", s);
            return false;
        }
        if(i != 1)
            flag = false;
        return flag;
    }

    public boolean isOperationAuthorized(IBinder ibinder)
    {
        boolean flag;
        try
        {
            flag = mBinder.isOperationAuthorized(ibinder);
        }
        // Misplaced declaration of an exception variable
        catch(IBinder ibinder)
        {
            Log.w("KeyStore", "Cannot connect to keystore", ibinder);
            return false;
        }
        return flag;
    }

    public boolean isUnlocked()
    {
        boolean flag;
        if(state() == State.UNLOCKED)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public String[] list(String s)
    {
        return list(s, -1);
    }

    public String[] list(String s, int i)
    {
        try
        {
            s = mBinder.list(s, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.w("KeyStore", "Cannot connect to keystore", s);
            return null;
        }
        return s;
    }

    public boolean lock()
    {
        return lock(UserHandle.myUserId());
    }

    public boolean lock(int i)
    {
        boolean flag = true;
        try
        {
            i = mBinder.lock(i);
        }
        catch(RemoteException remoteexception)
        {
            Log.w("KeyStore", "Cannot connect to keystore", remoteexception);
            return false;
        }
        if(i != 1)
            flag = false;
        return flag;
    }

    public void onDeviceOffBody()
    {
        mBinder.onDeviceOffBody();
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.w("KeyStore", "Cannot connect to keystore", remoteexception);
          goto _L1
    }

    public void onUserAdded(int i)
    {
        onUserAdded(i, -1);
    }

    public void onUserAdded(int i, int j)
    {
        mBinder.onUserAdded(i, j);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.w("KeyStore", "Cannot connect to keystore", remoteexception);
          goto _L1
    }

    public boolean onUserPasswordChanged(int i, String s)
    {
        boolean flag = true;
        String s1 = s;
        if(s == null)
            s1 = "";
        try
        {
            i = mBinder.onUserPasswordChanged(i, s1);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.w("KeyStore", "Cannot connect to keystore", s);
            return false;
        }
        if(i != 1)
            flag = false;
        return flag;
    }

    public boolean onUserPasswordChanged(String s)
    {
        return onUserPasswordChanged(UserHandle.getUserId(Process.myUid()), s);
    }

    public void onUserRemoved(int i)
    {
        mBinder.onUserRemoved(i);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.w("KeyStore", "Cannot connect to keystore", remoteexception);
          goto _L1
    }

    public boolean put(String s, byte abyte0[], int i, int j)
    {
        boolean flag = true;
        if(insert(s, abyte0, i, j) != 1)
            flag = false;
        return flag;
    }

    public boolean reset()
    {
        boolean flag = true;
        int i;
        try
        {
            i = mBinder.reset();
        }
        catch(RemoteException remoteexception)
        {
            Log.w("KeyStore", "Cannot connect to keystore", remoteexception);
            return false;
        }
        if(i != 1)
            flag = false;
        return flag;
    }

    public byte[] sign(String s, byte abyte0[])
    {
        try
        {
            s = mBinder.sign(s, abyte0);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.w("KeyStore", "Cannot connect to keystore", s);
            return null;
        }
        return s;
    }

    public State state()
    {
        return state(UserHandle.myUserId());
    }

    public State state(int i)
    {
        try
        {
            i = mBinder.getState(i);
        }
        catch(RemoteException remoteexception)
        {
            Log.w("KeyStore", "Cannot connect to keystore", remoteexception);
            throw new AssertionError(remoteexception);
        }
        switch(i)
        {
        default:
            throw new AssertionError(mError);

        case 1: // '\001'
            return State.UNLOCKED;

        case 2: // '\002'
            return State.LOCKED;

        case 3: // '\003'
            return State.UNINITIALIZED;
        }
    }

    public boolean ungrant(String s, int i)
    {
        boolean flag = true;
        try
        {
            i = mBinder.ungrant(s, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.w("KeyStore", "Cannot connect to keystore", s);
            return false;
        }
        if(i != 1)
            flag = false;
        return flag;
    }

    public boolean unlock(int i, String s)
    {
        boolean flag = true;
        try
        {
            mError = mBinder.unlock(i, s);
            i = mError;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.w("KeyStore", "Cannot connect to keystore", s);
            return false;
        }
        if(i != 1)
            flag = false;
        return flag;
    }

    public boolean unlock(String s)
    {
        return unlock(UserHandle.getUserId(Process.myUid()), s);
    }

    public OperationResult update(IBinder ibinder, KeymasterArguments keymasterarguments, byte abyte0[])
    {
        try
        {
            ibinder = mBinder.update(ibinder, keymasterarguments, abyte0);
        }
        // Misplaced declaration of an exception variable
        catch(IBinder ibinder)
        {
            Log.w("KeyStore", "Cannot connect to keystore", ibinder);
            return null;
        }
        return ibinder;
    }

    public boolean verify(String s, byte abyte0[], byte abyte1[])
    {
        boolean flag = true;
        int i;
        try
        {
            i = mBinder.verify(s, abyte0, abyte1);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.w("KeyStore", "Cannot connect to keystore", s);
            return false;
        }
        if(i != 1)
            flag = false;
        return flag;
    }

    public static final int FLAG_CRITICAL_TO_DEVICE_ENCRYPTION = 8;
    public static final int FLAG_ENCRYPTED = 1;
    public static final int FLAG_NONE = 0;
    public static final int KEY_NOT_FOUND = 7;
    public static final int LOCKED = 2;
    public static final int NO_ERROR = 1;
    public static final int OP_AUTH_NEEDED = 15;
    public static final int PERMISSION_DENIED = 6;
    public static final int PROTOCOL_ERROR = 5;
    public static final int SYSTEM_ERROR = 4;
    private static final String TAG = "KeyStore";
    public static final int UID_SELF = -1;
    public static final int UNDEFINED_ACTION = 9;
    public static final int UNINITIALIZED = 3;
    public static final int VALUE_CORRUPTED = 8;
    public static final int WRONG_PASSWORD = 10;
    private final IKeystoreService mBinder;
    private final Context mContext = getApplicationContext();
    private int mError;
    private IBinder mToken;
}
