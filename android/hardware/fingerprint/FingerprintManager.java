// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.fingerprint;

import android.app.ActivityManager;
import android.app.IActivityManager;
import android.content.Context;
import android.content.pm.UserInfo;
import android.content.res.Resources;
import android.os.*;
import android.security.keystore.AndroidKeyStoreProvider;
import android.util.Log;
import android.util.Slog;
import java.security.Signature;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.Mac;

// Referenced classes of package android.hardware.fingerprint:
//            IFingerprintService, FingerprintFidoIn, Fingerprint, IFingerprintServiceReceiver, 
//            FingerprintFidoOut

public class FingerprintManager
{
    public static abstract class AuthenticationCallback
    {

        public void onAuthenticationAcquired(int i)
        {
        }

        public void onAuthenticationError(int i, CharSequence charsequence)
        {
        }

        public void onAuthenticationFailed()
        {
        }

        public void onAuthenticationHelp(int i, CharSequence charsequence)
        {
        }

        public void onAuthenticationSucceeded(AuthenticationResult authenticationresult)
        {
        }

        public AuthenticationCallback()
        {
        }
    }

    public static abstract class AuthenticationFidoCallback extends AuthenticationCallback
    {

        public void onAuthenticationFidoSucceeded(AuthenticationResult authenticationresult, FingerprintFidoOut fingerprintfidoout)
        {
        }

        public AuthenticationFidoCallback()
        {
        }
    }

    public static class AuthenticationResult
    {

        public CryptoObject getCryptoObject()
        {
            return mCryptoObject;
        }

        public Fingerprint getFingerprint()
        {
            return mFingerprint;
        }

        public int getUserId()
        {
            return mUserId;
        }

        private CryptoObject mCryptoObject;
        private Fingerprint mFingerprint;
        private int mUserId;

        public AuthenticationResult(CryptoObject cryptoobject, Fingerprint fingerprint, int i)
        {
            mCryptoObject = cryptoobject;
            mFingerprint = fingerprint;
            mUserId = i;
        }
    }

    public static final class CryptoObject
    {

        public Cipher getCipher()
        {
            Cipher cipher;
            if(mCrypto instanceof Cipher)
                cipher = (Cipher)mCrypto;
            else
                cipher = null;
            return cipher;
        }

        public Mac getMac()
        {
            Mac mac;
            if(mCrypto instanceof Mac)
                mac = (Mac)mCrypto;
            else
                mac = null;
            return mac;
        }

        public long getOpId()
        {
            long l;
            if(mCrypto != null)
                l = AndroidKeyStoreProvider.getKeyStoreOperationHandle(mCrypto);
            else
                l = 0L;
            return l;
        }

        public Signature getSignature()
        {
            Signature signature;
            if(mCrypto instanceof Signature)
                signature = (Signature)mCrypto;
            else
                signature = null;
            return signature;
        }

        private final Object mCrypto;

        public CryptoObject(Signature signature)
        {
            mCrypto = signature;
        }

        public CryptoObject(Cipher cipher)
        {
            mCrypto = cipher;
        }

        public CryptoObject(Mac mac)
        {
            mCrypto = mac;
        }
    }

    public static abstract class EnrollmentCallback
    {

        public void onEnrollmentError(int i, CharSequence charsequence)
        {
        }

        public void onEnrollmentHelp(int i, CharSequence charsequence)
        {
        }

        public void onEnrollmentProgress(int i)
        {
        }

        public EnrollmentCallback()
        {
        }
    }

    public static abstract class EnumerateCallback
    {

        public void onEnumerate(Fingerprint fingerprint)
        {
        }

        public void onEnumerateError(int i, CharSequence charsequence)
        {
        }

        public EnumerateCallback()
        {
        }
    }

    public static abstract class LockoutResetCallback
    {

        public void onLockoutReset()
        {
        }

        public LockoutResetCallback()
        {
        }
    }

    private class MyHandler extends Handler
    {

        private void sendAcquiredResult(long l, int i, int j)
        {
            String s;
            if(FingerprintManager._2D_get0(FingerprintManager.this) != null)
                FingerprintManager._2D_get0(FingerprintManager.this).onAuthenticationAcquired(i);
            s = FingerprintManager._2D_wrap0(FingerprintManager.this, i, j);
            if(s == null)
                return;
            if(i == 6)
                i = j + 1000;
            if(FingerprintManager._2D_get2(FingerprintManager.this) == null) goto _L2; else goto _L1
_L1:
            FingerprintManager._2D_get2(FingerprintManager.this).onEnrollmentHelp(i, s);
_L4:
            return;
_L2:
            if(FingerprintManager._2D_get0(FingerprintManager.this) != null)
                FingerprintManager._2D_get0(FingerprintManager.this).onAuthenticationHelp(i, s);
            if(true) goto _L4; else goto _L3
_L3:
        }

        private void sendAuthenticatedFailed()
        {
            if(FingerprintManager._2D_get0(FingerprintManager.this) != null)
                FingerprintManager._2D_get0(FingerprintManager.this).onAuthenticationFailed();
        }

        private void sendAuthenticatedSucceeded(Fingerprint fingerprint, int i)
        {
            if(FingerprintManager._2D_get0(FingerprintManager.this) != null)
            {
                fingerprint = new AuthenticationResult(FingerprintManager._2D_get1(FingerprintManager.this), fingerprint, i);
                FingerprintManager._2D_get0(FingerprintManager.this).onAuthenticationSucceeded(fingerprint);
            }
        }

        private void sendEnrollResult(Fingerprint fingerprint, int i)
        {
            if(FingerprintManager._2D_get2(FingerprintManager.this) != null)
                FingerprintManager._2D_get2(FingerprintManager.this).onEnrollmentProgress(i);
        }

        private void sendEnumeratedResult(long l, int i, int j)
        {
            if(FingerprintManager._2D_get3(FingerprintManager.this) != null)
                FingerprintManager._2D_get3(FingerprintManager.this).onEnumerate(new Fingerprint(null, j, i, l));
        }

        private void sendErrorResult(long l, int i, int j)
        {
            int k;
            if(i == 8)
                k = j + 1000;
            else
                k = i;
            if(FingerprintManager._2D_get2(FingerprintManager.this) == null) goto _L2; else goto _L1
_L1:
            FingerprintManager._2D_get2(FingerprintManager.this).onEnrollmentError(k, FingerprintManager._2D_wrap1(FingerprintManager.this, i, j));
_L4:
            return;
_L2:
            if(FingerprintManager._2D_get0(FingerprintManager.this) != null)
                FingerprintManager._2D_get0(FingerprintManager.this).onAuthenticationError(k, FingerprintManager._2D_wrap1(FingerprintManager.this, i, j));
            else
            if(FingerprintManager._2D_get5(FingerprintManager.this) != null)
                FingerprintManager._2D_get5(FingerprintManager.this).onRemovalError(FingerprintManager._2D_get6(FingerprintManager.this), k, FingerprintManager._2D_wrap1(FingerprintManager.this, i, j));
            else
            if(FingerprintManager._2D_get3(FingerprintManager.this) != null)
                FingerprintManager._2D_get3(FingerprintManager.this).onEnumerateError(k, FingerprintManager._2D_wrap1(FingerprintManager.this, i, j));
            if(true) goto _L4; else goto _L3
_L3:
        }

        private void sendRemovedResult(Fingerprint fingerprint, int i)
        {
            if(FingerprintManager._2D_get5(FingerprintManager.this) == null)
                return;
            if(fingerprint == null)
            {
                Log.e("FingerprintManager", "Received MSG_REMOVED, but fingerprint is null");
                return;
            }
            int j = fingerprint.getFingerId();
            int k = FingerprintManager._2D_get6(FingerprintManager.this).getFingerId();
            if(k != 0 && j != 0 && j != k)
            {
                Log.w("FingerprintManager", (new StringBuilder()).append("Finger id didn't match: ").append(j).append(" != ").append(k).toString());
                return;
            }
            j = fingerprint.getGroupId();
            k = FingerprintManager._2D_get6(FingerprintManager.this).getGroupId();
            if(j != k)
            {
                Log.w("FingerprintManager", (new StringBuilder()).append("Group id didn't match: ").append(j).append(" != ").append(k).toString());
                return;
            } else
            {
                FingerprintManager._2D_get5(FingerprintManager.this).onRemovalSucceeded(fingerprint, i);
                return;
            }
        }

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 100 106: default 48
        //                       100 49
        //                       101 67
        //                       102 92
        //                       103 110
        //                       104 117
        //                       105 142
        //                       106 160;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            return;
_L2:
            sendEnrollResult((Fingerprint)message.obj, message.arg1);
            continue; /* Loop/switch isn't completed */
_L3:
            sendAcquiredResult(((Long)message.obj).longValue(), message.arg1, message.arg2);
            continue; /* Loop/switch isn't completed */
_L4:
            sendAuthenticatedSucceeded((Fingerprint)message.obj, message.arg1);
            continue; /* Loop/switch isn't completed */
_L5:
            sendAuthenticatedFailed();
            continue; /* Loop/switch isn't completed */
_L6:
            sendErrorResult(((Long)message.obj).longValue(), message.arg1, message.arg2);
            continue; /* Loop/switch isn't completed */
_L7:
            sendRemovedResult((Fingerprint)message.obj, message.arg1);
            continue; /* Loop/switch isn't completed */
_L8:
            sendEnumeratedResult(((Long)message.obj).longValue(), message.arg1, message.arg2);
            if(true) goto _L1; else goto _L9
_L9:
        }

        final FingerprintManager this$0;

        private MyHandler(Context context)
        {
            this$0 = FingerprintManager.this;
            super(context.getMainLooper());
        }

        MyHandler(Context context, MyHandler myhandler)
        {
            this(context);
        }

        private MyHandler(Looper looper)
        {
            this$0 = FingerprintManager.this;
            super(looper);
        }

        MyHandler(Looper looper, MyHandler myhandler)
        {
            this(looper);
        }
    }

    private class OnAuthenticationCancelListener
        implements android.os.CancellationSignal.OnCancelListener
    {

        public void onCancel()
        {
            FingerprintManager._2D_wrap2(FingerprintManager.this, mCrypto);
        }

        private CryptoObject mCrypto;
        final FingerprintManager this$0;

        public OnAuthenticationCancelListener(CryptoObject cryptoobject)
        {
            this$0 = FingerprintManager.this;
            super();
            mCrypto = cryptoobject;
        }
    }

    private class OnEnrollCancelListener
        implements android.os.CancellationSignal.OnCancelListener
    {

        public void onCancel()
        {
            FingerprintManager._2D_wrap3(FingerprintManager.this);
        }

        final FingerprintManager this$0;

        private OnEnrollCancelListener()
        {
            this$0 = FingerprintManager.this;
            super();
        }

        OnEnrollCancelListener(OnEnrollCancelListener onenrollcancellistener)
        {
            this();
        }
    }

    public static abstract class RemovalCallback
    {

        public void onRemovalError(Fingerprint fingerprint, int i, CharSequence charsequence)
        {
        }

        public void onRemovalSucceeded(Fingerprint fingerprint, int i)
        {
        }

        public RemovalCallback()
        {
        }
    }


    static AuthenticationCallback _2D_get0(FingerprintManager fingerprintmanager)
    {
        return fingerprintmanager.mAuthenticationCallback;
    }

    static CryptoObject _2D_get1(FingerprintManager fingerprintmanager)
    {
        return fingerprintmanager.mCryptoObject;
    }

    static EnrollmentCallback _2D_get2(FingerprintManager fingerprintmanager)
    {
        return fingerprintmanager.mEnrollmentCallback;
    }

    static EnumerateCallback _2D_get3(FingerprintManager fingerprintmanager)
    {
        return fingerprintmanager.mEnumerateCallback;
    }

    static Handler _2D_get4(FingerprintManager fingerprintmanager)
    {
        return fingerprintmanager.mHandler;
    }

    static RemovalCallback _2D_get5(FingerprintManager fingerprintmanager)
    {
        return fingerprintmanager.mRemovalCallback;
    }

    static Fingerprint _2D_get6(FingerprintManager fingerprintmanager)
    {
        return fingerprintmanager.mRemovalFingerprint;
    }

    static String _2D_wrap0(FingerprintManager fingerprintmanager, int i, int j)
    {
        return fingerprintmanager.getAcquiredString(i, j);
    }

    static String _2D_wrap1(FingerprintManager fingerprintmanager, int i, int j)
    {
        return fingerprintmanager.getErrorString(i, j);
    }

    static void _2D_wrap2(FingerprintManager fingerprintmanager, CryptoObject cryptoobject)
    {
        fingerprintmanager.cancelAuthentication(cryptoobject);
    }

    static void _2D_wrap3(FingerprintManager fingerprintmanager)
    {
        fingerprintmanager.cancelEnrollment();
    }

    public FingerprintManager(Context context, IFingerprintService ifingerprintservice)
    {
        mToken = new Binder();
        mServiceReceiver = new IFingerprintServiceReceiver.Stub() {

            public void onAcquired(long l, int i, int j)
            {
                FingerprintManager._2D_get4(FingerprintManager.this).obtainMessage(101, i, j, Long.valueOf(l)).sendToTarget();
            }

            public void onAuthenticationFailed(long l)
            {
                FingerprintManager._2D_get4(FingerprintManager.this).obtainMessage(103).sendToTarget();
            }

            public void onAuthenticationSucceeded(long l, Fingerprint fingerprint, int i)
            {
                FingerprintManager._2D_get4(FingerprintManager.this).obtainMessage(102, i, 0, fingerprint).sendToTarget();
            }

            public void onEnrollResult(long l, int i, int j, int k)
            {
                FingerprintManager._2D_get4(FingerprintManager.this).obtainMessage(100, k, 0, new Fingerprint(null, j, i, l)).sendToTarget();
            }

            public void onEnumerated(long l, int i, int j, int k)
            {
                FingerprintManager._2D_get4(FingerprintManager.this).obtainMessage(106, i, j, Long.valueOf(l)).sendToTarget();
            }

            public void onError(long l, int i, int j)
            {
                FingerprintManager._2D_get4(FingerprintManager.this).obtainMessage(104, i, j, Long.valueOf(l)).sendToTarget();
            }

            public void onRemoved(long l, int i, int j, int k)
            {
                FingerprintManager._2D_get4(FingerprintManager.this).obtainMessage(105, k, 0, new Fingerprint(null, j, i, l)).sendToTarget();
            }

            final FingerprintManager this$0;

            
            {
                this$0 = FingerprintManager.this;
                super();
            }
        }
;
        mContext = context;
        mService = ifingerprintservice;
        if(mService == null)
            Slog.v("FingerprintManager", "FingerprintManagerService was null");
        mHandler = new MyHandler(context, null);
    }

    private void cancelAuthentication(CryptoObject cryptoobject)
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_27;
        mService.cancelAuthentication(mToken, mContext.getOpPackageName());
        return;
        cryptoobject;
        throw cryptoobject.rethrowFromSystemServer();
    }

    private void cancelEnrollment()
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_20;
        mService.cancelEnrollment(mToken);
        return;
        RemoteException remoteexception;
        remoteexception;
        throw remoteexception.rethrowFromSystemServer();
    }

    private long challenge2long(byte abyte0[])
    {
        long l = 0L;
        for(int i = 0; i < 8; i++)
            l = l << 8 | (long)(abyte0[i] & 0xff);

        return l;
    }

    private String getAcquiredString(int i, int j)
    {
        i;
        JVM INSTR tableswitch 0 6: default 44
    //                   0 80
    //                   1 82
    //                   2 92
    //                   3 102
    //                   4 112
    //                   5 122
    //                   6 132;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        Slog.w("FingerprintManager", (new StringBuilder()).append("Invalid acquired message: ").append(i).append(", ").append(j).toString());
        return null;
_L2:
        return null;
_L3:
        return mContext.getString(0x1040222);
_L4:
        return mContext.getString(0x1040221);
_L5:
        return mContext.getString(0x1040220);
_L6:
        return mContext.getString(0x1040224);
_L7:
        return mContext.getString(0x1040223);
_L8:
        String as[] = mContext.getResources().getStringArray(0x1070054);
        if(j < as.length)
            return as[j];
        if(true) goto _L1; else goto _L9
_L9:
    }

    private int getCurrentUserId()
    {
        int i;
        try
        {
            i = ActivityManager.getService().getCurrentUser().id;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    private String getErrorString(int i, int j)
    {
        i;
        JVM INSTR tableswitch 1 9: default 52
    //                   1 100
    //                   2 89
    //                   3 122
    //                   4 111
    //                   5 133
    //                   6 52
    //                   7 144
    //                   8 166
    //                   9 155;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L1 _L7 _L8 _L9
_L1:
        Slog.w("FingerprintManager", (new StringBuilder()).append("Invalid error message: ").append(i).append(", ").append(j).toString());
        return null;
_L3:
        return mContext.getString(0x104022b);
_L2:
        return mContext.getString(0x1040226);
_L5:
        return mContext.getString(0x1040229);
_L4:
        return mContext.getString(0x104022a);
_L6:
        return mContext.getString(0x1040225);
_L7:
        return mContext.getString(0x1040227);
_L9:
        return mContext.getString(0x1040228);
_L8:
        String as[] = mContext.getResources().getStringArray(0x1070055);
        if(j < as.length)
            return as[j];
        if(true) goto _L1; else goto _L10
_L10:
    }

    private void useHandler(Handler handler)
    {
        if(handler == null) goto _L2; else goto _L1
_L1:
        mHandler = new MyHandler(handler.getLooper(), null);
_L4:
        return;
_L2:
        if(mHandler.getLooper() != mContext.getMainLooper())
            mHandler = new MyHandler(mContext.getMainLooper(), null);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void addLockoutResetCallback(LockoutResetCallback lockoutresetcallback)
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_54;
        PowerManager powermanager = (PowerManager)mContext.getSystemService(android/os/PowerManager);
        IFingerprintService ifingerprintservice = mService;
        IFingerprintServiceLockoutResetCallback.Stub stub = JVM INSTR new #8   <Class FingerprintManager$2>;
        stub.this. _cls2();
        ifingerprintservice.addLockoutResetCallback(stub);
_L1:
        return;
        lockoutresetcallback;
        throw lockoutresetcallback.rethrowFromSystemServer();
        Log.w("FingerprintManager", "addLockoutResetCallback(): Service not connected!");
          goto _L1
    }

    public void authenticate(CryptoObject cryptoobject, CancellationSignal cancellationsignal, int i, AuthenticationCallback authenticationcallback, Handler handler)
    {
        authenticate(cryptoobject, cancellationsignal, i, authenticationcallback, handler, UserHandle.myUserId());
    }

    public void authenticate(CryptoObject cryptoobject, CancellationSignal cancellationsignal, int i, AuthenticationCallback authenticationcallback, Handler handler, int j)
    {
        if(authenticationcallback == null)
            throw new IllegalArgumentException("Must supply an authentication callback");
        if(cancellationsignal != null)
        {
            if(cancellationsignal.isCanceled())
            {
                Log.w("FingerprintManager", "authentication already canceled");
                return;
            }
            cancellationsignal.setOnCancelListener(new OnAuthenticationCancelListener(cryptoobject));
        }
        if(mService == null) goto _L2; else goto _L1
_L1:
        useHandler(handler);
        mAuthenticationCallback = authenticationcallback;
        mCryptoObject = cryptoobject;
        if(cryptoobject == null) goto _L4; else goto _L3
_L3:
        long l = cryptoobject.getOpId();
_L5:
        mService.authenticate(mToken, l, j, mServiceReceiver, i, mContext.getOpPackageName());
_L2:
        return;
_L4:
        l = 0L;
          goto _L5
        cryptoobject;
        Log.w("FingerprintManager", "Remote exception while authenticating: ", cryptoobject);
        if(authenticationcallback != null)
            authenticationcallback.onAuthenticationError(1, getErrorString(1, 0));
          goto _L2
    }

    public void authenticateFido(CryptoObject cryptoobject, CancellationSignal cancellationsignal, AuthenticationFidoCallback authenticationfidocallback, FingerprintFidoIn fingerprintfidoin)
    {
        int i;
        i = UserHandle.myUserId();
        if(authenticationfidocallback == null)
            throw new IllegalArgumentException("Must supply an authentication callback");
        if(cancellationsignal != null)
        {
            if(cancellationsignal.isCanceled())
            {
                Log.w("FingerprintManager", "authentication already canceled");
                return;
            }
            cancellationsignal.setOnCancelListener(new OnAuthenticationCancelListener(null));
        }
        if(mService == null)
            break MISSING_BLOCK_LABEL_140;
        useHandler(null);
        mAuthenticationCallback = authenticationfidocallback;
        long l = challenge2long(fingerprintfidoin.getNonce());
        cryptoobject = JVM INSTR new #218 <Class StringBuilder>;
        cryptoobject.StringBuilder();
        Log.i("FingerprintManager", cryptoobject.append("authenticateFido, v1.2, sessionId:").append(l).toString());
        mService.authenticate(mToken, l, i, mServiceReceiver, 0, mContext.getOpPackageName());
_L1:
        return;
        cryptoobject;
        Log.w("FingerprintManager", "Remote exception while authenticating: ", cryptoobject);
        if(authenticationfidocallback != null)
            authenticationfidocallback.onAuthenticationError(1, getErrorString(1, 0));
          goto _L1
    }

    public void enroll(byte abyte0[], CancellationSignal cancellationsignal, int i, int j, EnrollmentCallback enrollmentcallback)
    {
        int k;
        k = j;
        if(j == -2)
            k = getCurrentUserId();
        if(enrollmentcallback == null)
            throw new IllegalArgumentException("Must supply an enrollment callback");
        if(cancellationsignal != null)
        {
            if(cancellationsignal.isCanceled())
            {
                Log.w("FingerprintManager", "enrollment already canceled");
                return;
            }
            cancellationsignal.setOnCancelListener(new OnEnrollCancelListener(null));
        }
        if(mService == null)
            break MISSING_BLOCK_LABEL_108;
        mEnrollmentCallback = enrollmentcallback;
        mService.enroll(mToken, abyte0, k, mServiceReceiver, i, mContext.getOpPackageName());
_L1:
        return;
        abyte0;
        Log.w("FingerprintManager", "Remote exception in enroll: ", abyte0);
        if(enrollmentcallback != null)
            enrollmentcallback.onEnrollmentError(1, getErrorString(1, 0));
          goto _L1
    }

    public void enumerate(int i, EnumerateCallback enumeratecallback)
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_30;
        mEnumerateCallback = enumeratecallback;
        mService.enumerate(mToken, i, mServiceReceiver);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.w("FingerprintManager", "Remote exception in enumerate: ", remoteexception);
        if(enumeratecallback != null)
            enumeratecallback.onEnumerateError(1, getErrorString(1, 0));
          goto _L1
    }

    public long getAuthenticatorId()
    {
        if(mService != null)
        {
            long l;
            try
            {
                l = mService.getAuthenticatorId(mContext.getOpPackageName());
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return l;
        } else
        {
            Log.w("FingerprintManager", "getAuthenticatorId(): Service not connected!");
            return 0L;
        }
    }

    public List getEnrolledFingerprints()
    {
        return getEnrolledFingerprints(UserHandle.myUserId());
    }

    public List getEnrolledFingerprints(int i)
    {
        if(mService != null)
        {
            List list;
            try
            {
                list = mService.getEnrolledFingerprints(i, mContext.getOpPackageName());
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return list;
        } else
        {
            return null;
        }
    }

    public boolean hasEnrolledFingerprints()
    {
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.hasEnrolledFingerprints(UserHandle.myUserId(), mContext.getOpPackageName());
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public boolean hasEnrolledFingerprints(int i)
    {
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.hasEnrolledFingerprints(i, mContext.getOpPackageName());
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public boolean isHardwareDetected()
    {
        if(mService != null)
        {
            boolean flag;
            try
            {
                flag = mService.isHardwareDetected(0L, mContext.getOpPackageName());
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return flag;
        } else
        {
            Log.w("FingerprintManager", "isFingerprintHardwareDetected(): Service not connected!");
            return false;
        }
    }

    public int postEnroll()
    {
        int i = 0;
        if(mService != null)
            try
            {
                i = mService.postEnroll(mToken);
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
        return i;
    }

    public long preEnroll()
    {
        long l = 0L;
        if(mService != null)
            try
            {
                l = mService.preEnroll(mToken);
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
        return l;
    }

    public void remove(Fingerprint fingerprint, int i, RemovalCallback removalcallback)
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_43;
        mRemovalCallback = removalcallback;
        mRemovalFingerprint = fingerprint;
        mService.remove(mToken, fingerprint.getFingerId(), fingerprint.getGroupId(), i, mServiceReceiver);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.w("FingerprintManager", "Remote exception in remove: ", remoteexception);
        if(removalcallback != null)
            removalcallback.onRemovalError(fingerprint, 1, getErrorString(1, 0));
          goto _L1
    }

    public void rename(int i, int j, String s)
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_26;
        mService.rename(i, j, s);
_L1:
        return;
        s;
        throw s.rethrowFromSystemServer();
        Log.w("FingerprintManager", "rename(): Service not connected!");
          goto _L1
    }

    public void resetTimeout(byte abyte0[])
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_24;
        mService.resetTimeout(abyte0);
_L1:
        return;
        abyte0;
        throw abyte0.rethrowFromSystemServer();
        Log.w("FingerprintManager", "resetTimeout(): Service not connected!");
          goto _L1
    }

    public void setActiveUser(int i)
    {
        if(mService == null)
            break MISSING_BLOCK_LABEL_17;
        mService.setActiveUser(i);
        return;
        RemoteException remoteexception;
        remoteexception;
        throw remoteexception.rethrowFromSystemServer();
    }

    private static final boolean DEBUG = true;
    public static final int FINGERPRINT_ACQUIRED_GOOD = 0;
    public static final int FINGERPRINT_ACQUIRED_IMAGER_DIRTY = 3;
    public static final int FINGERPRINT_ACQUIRED_INSUFFICIENT = 2;
    public static final int FINGERPRINT_ACQUIRED_PARTIAL = 1;
    public static final int FINGERPRINT_ACQUIRED_TOO_FAST = 5;
    public static final int FINGERPRINT_ACQUIRED_TOO_SLOW = 4;
    public static final int FINGERPRINT_ACQUIRED_VENDOR = 6;
    public static final int FINGERPRINT_ACQUIRED_VENDOR_BASE = 1000;
    public static final int FINGERPRINT_ERROR_CANCELED = 5;
    public static final int FINGERPRINT_ERROR_HW_UNAVAILABLE = 1;
    public static final int FINGERPRINT_ERROR_LOCKOUT = 7;
    public static final int FINGERPRINT_ERROR_LOCKOUT_PERMANENT = 9;
    public static final int FINGERPRINT_ERROR_NO_SPACE = 4;
    public static final int FINGERPRINT_ERROR_TIMEOUT = 3;
    public static final int FINGERPRINT_ERROR_UNABLE_TO_PROCESS = 2;
    public static final int FINGERPRINT_ERROR_UNABLE_TO_REMOVE = 6;
    public static final int FINGERPRINT_ERROR_USER_CANCELED = 10;
    public static final int FINGERPRINT_ERROR_VENDOR = 8;
    public static final int FINGERPRINT_ERROR_VENDOR_BASE = 1000;
    private static final int MSG_ACQUIRED = 101;
    private static final int MSG_AUTHENTICATION_FAILED = 103;
    private static final int MSG_AUTHENTICATION_SUCCEEDED = 102;
    private static final int MSG_ENROLL_RESULT = 100;
    private static final int MSG_ENUMERATED = 106;
    private static final int MSG_ERROR = 104;
    private static final int MSG_REMOVED = 105;
    private static final String TAG = "FingerprintManager";
    private AuthenticationCallback mAuthenticationCallback;
    private Context mContext;
    private CryptoObject mCryptoObject;
    private EnrollmentCallback mEnrollmentCallback;
    private EnumerateCallback mEnumerateCallback;
    private Handler mHandler;
    private RemovalCallback mRemovalCallback;
    private Fingerprint mRemovalFingerprint;
    private IFingerprintService mService;
    private IFingerprintServiceReceiver mServiceReceiver;
    private IBinder mToken;

    // Unreferenced inner class android/hardware/fingerprint/FingerprintManager$2

/* anonymous class */
    class _cls2 extends IFingerprintServiceLockoutResetCallback.Stub
    {

        static void lambda$_2D_android_hardware_fingerprint_FingerprintManager$2_32444(android.os.PowerManager.WakeLock wakelock, LockoutResetCallback lockoutresetcallback)
        {
            lockoutresetcallback.onLockoutReset();
            wakelock.release();
            return;
            lockoutresetcallback;
            wakelock.release();
            throw lockoutresetcallback;
        }

        public void onLockoutReset(long l, IRemoteCallback iremotecallback)
            throws RemoteException
        {
            android.os.PowerManager.WakeLock wakelock = powerManager.newWakeLock(1, "lockoutResetCallback");
            wakelock.acquire();
            Handler handler = FingerprintManager._2D_get4(FingerprintManager.this);
            _.Lambda.mAn4tgSaQL9G4z96dzSedXu3vvs man4tgsaql9g4z96dzsedxu3vvs = JVM INSTR new #59  <Class _$Lambda$mAn4tgSaQL9G4z96dzSedXu3vvs>;
            man4tgsaql9g4z96dzsedxu3vvs._.Lambda.mAn4tgSaQL9G4z96dzSedXu3vvs(wakelock, callback);
            handler.post(man4tgsaql9g4z96dzsedxu3vvs);
            iremotecallback.sendResult(null);
            return;
            Exception exception;
            exception;
            iremotecallback.sendResult(null);
            throw exception;
        }

        final FingerprintManager this$0;
        final LockoutResetCallback val$callback;
        final PowerManager val$powerManager;

            
            {
                this$0 = FingerprintManager.this;
                powerManager = powermanager;
                callback = lockoutresetcallback;
                super();
            }
    }

}
