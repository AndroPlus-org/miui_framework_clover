// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.app.ActivityThread;
import android.os.*;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.*;

// Referenced classes of package android.media:
//            UnsupportedSchemeException, DeniedByServerException, NotProvisionedException, ResourceBusyException

public final class MediaDrm
{
    public static final class Certificate
    {

        public byte[] getContent()
        {
            if(mCertificateData == null)
                throw new RuntimeException("Cerfificate is not initialized");
            else
                return mCertificateData;
        }

        public byte[] getWrappedPrivateKey()
        {
            if(mWrappedKey == null)
                throw new RuntimeException("Cerfificate is not initialized");
            else
                return mWrappedKey;
        }

        private byte mCertificateData[];
        private byte mWrappedKey[];

        Certificate()
        {
        }
    }

    public static final class CertificateRequest
    {

        public byte[] getData()
        {
            return mData;
        }

        public String getDefaultUrl()
        {
            return mDefaultUrl;
        }

        private byte mData[];
        private String mDefaultUrl;

        CertificateRequest(byte abyte0[], String s)
        {
            mData = abyte0;
            mDefaultUrl = s;
        }
    }

    public final class CryptoSession
    {

        public byte[] decrypt(byte abyte0[], byte abyte1[], byte abyte2[])
        {
            return MediaDrm._2D_wrap1(MediaDrm.this, mSessionId, abyte0, abyte1, abyte2);
        }

        public byte[] encrypt(byte abyte0[], byte abyte1[], byte abyte2[])
        {
            return MediaDrm._2D_wrap2(MediaDrm.this, mSessionId, abyte0, abyte1, abyte2);
        }

        public byte[] sign(byte abyte0[], byte abyte1[])
        {
            return MediaDrm._2D_wrap3(MediaDrm.this, mSessionId, abyte0, abyte1);
        }

        public boolean verify(byte abyte0[], byte abyte1[], byte abyte2[])
        {
            return MediaDrm._2D_wrap0(MediaDrm.this, mSessionId, abyte0, abyte1, abyte2);
        }

        private byte mSessionId[];
        final MediaDrm this$0;

        CryptoSession(byte abyte0[], String s, String s1)
        {
            this$0 = MediaDrm.this;
            super();
            mSessionId = abyte0;
            MediaDrm._2D_wrap5(MediaDrm.this, abyte0, s);
            MediaDrm._2D_wrap6(MediaDrm.this, abyte0, s1);
        }
    }

    private class EventHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            if(MediaDrm._2D_get0(mMediaDrm) == 0L)
            {
                Log.w("MediaDrm", "MediaDrm went away with unhandled events");
                return;
            }
            switch(message.what)
            {
            default:
                Log.e("MediaDrm", (new StringBuilder()).append("Unknown message type ").append(message.what).toString());
                return;

            case 200: 
                if(MediaDrm._2D_get1(MediaDrm.this) != null && message.obj != null && (message.obj instanceof Parcel))
                {
                    Parcel parcel = (Parcel)message.obj;
                    byte abyte1[] = parcel.createByteArray();
                    byte abyte2[] = abyte1;
                    if(abyte1.length == 0)
                        abyte2 = null;
                    byte abyte0[] = parcel.createByteArray();
                    abyte1 = abyte0;
                    if(abyte0.length == 0)
                        abyte1 = null;
                    Log.i("MediaDrm", (new StringBuilder()).append("Drm event (").append(message.arg1).append(",").append(message.arg2).append(")").toString());
                    MediaDrm._2D_get1(MediaDrm.this).onEvent(mMediaDrm, abyte2, message.arg1, message.arg2, abyte1);
                }
                return;

            case 202: 
                if(MediaDrm._2D_get3(MediaDrm.this) != null && message.obj != null && (message.obj instanceof Parcel))
                {
                    Parcel parcel1 = (Parcel)message.obj;
                    message = parcel1.createByteArray();
                    if(message.length > 0)
                    {
                        List list = MediaDrm._2D_wrap4(MediaDrm.this, parcel1);
                        boolean flag;
                        if(parcel1.readInt() != 0)
                            flag = true;
                        else
                            flag = false;
                        Log.i("MediaDrm", "Drm key status changed");
                        MediaDrm._2D_get3(MediaDrm.this).onKeyStatusChange(mMediaDrm, message, list, flag);
                    }
                }
                return;

            case 201: 
                break;
            }
            if(MediaDrm._2D_get2(MediaDrm.this) != null && message.obj != null && (message.obj instanceof Parcel))
            {
                Parcel parcel2 = (Parcel)message.obj;
                message = parcel2.createByteArray();
                if(message.length > 0)
                {
                    long l = parcel2.readLong();
                    Log.i("MediaDrm", (new StringBuilder()).append("Drm key expiration update: ").append(l).toString());
                    MediaDrm._2D_get2(MediaDrm.this).onExpirationUpdate(mMediaDrm, message, l);
                }
            }
        }

        private MediaDrm mMediaDrm;
        final MediaDrm this$0;

        public EventHandler(MediaDrm mediadrm1, Looper looper)
        {
            this$0 = MediaDrm.this;
            super(looper);
            mMediaDrm = mediadrm1;
        }
    }

    public static final class KeyRequest
    {

        public byte[] getData()
        {
            if(mData == null)
                throw new RuntimeException("KeyRequest is not initialized");
            else
                return mData;
        }

        public String getDefaultUrl()
        {
            if(mDefaultUrl == null)
                throw new RuntimeException("KeyRequest is not initialized");
            else
                return mDefaultUrl;
        }

        public int getRequestType()
        {
            return mRequestType;
        }

        public static final int REQUEST_TYPE_INITIAL = 0;
        public static final int REQUEST_TYPE_RELEASE = 2;
        public static final int REQUEST_TYPE_RENEWAL = 1;
        private byte mData[];
        private String mDefaultUrl;
        private int mRequestType;

        KeyRequest()
        {
        }
    }

    public static final class KeyStatus
    {

        public byte[] getKeyId()
        {
            return mKeyId;
        }

        public int getStatusCode()
        {
            return mStatusCode;
        }

        public static final int STATUS_EXPIRED = 1;
        public static final int STATUS_INTERNAL_ERROR = 4;
        public static final int STATUS_OUTPUT_NOT_ALLOWED = 2;
        public static final int STATUS_PENDING = 3;
        public static final int STATUS_USABLE = 0;
        private final byte mKeyId[];
        private final int mStatusCode;

        KeyStatus(byte abyte0[], int i)
        {
            mKeyId = abyte0;
            mStatusCode = i;
        }
    }

    public static final class MediaDrmStateException extends IllegalStateException
    {

        public String getDiagnosticInfo()
        {
            return mDiagnosticInfo;
        }

        public int getErrorCode()
        {
            return mErrorCode;
        }

        private final String mDiagnosticInfo;
        private final int mErrorCode;

        public MediaDrmStateException(int i, String s)
        {
            super(s);
            mErrorCode = i;
            if(i < 0)
                s = "neg_";
            else
                s = "";
            mDiagnosticInfo = (new StringBuilder()).append("android.media.MediaDrm.error_").append(s).append(Math.abs(i)).toString();
        }
    }

    public static interface OnEventListener
    {

        public abstract void onEvent(MediaDrm mediadrm, byte abyte0[], int i, int j, byte abyte1[]);
    }

    public static interface OnExpirationUpdateListener
    {

        public abstract void onExpirationUpdate(MediaDrm mediadrm, byte abyte0[], long l);
    }

    public static interface OnKeyStatusChangeListener
    {

        public abstract void onKeyStatusChange(MediaDrm mediadrm, byte abyte0[], List list, boolean flag);
    }

    public static final class ProvisionRequest
    {

        public byte[] getData()
        {
            if(mData == null)
                throw new RuntimeException("ProvisionRequest is not initialized");
            else
                return mData;
        }

        public String getDefaultUrl()
        {
            if(mDefaultUrl == null)
                throw new RuntimeException("ProvisionRequest is not initialized");
            else
                return mDefaultUrl;
        }

        private byte mData[];
        private String mDefaultUrl;

        ProvisionRequest()
        {
        }
    }


    static long _2D_get0(MediaDrm mediadrm)
    {
        return mediadrm.mNativeContext;
    }

    static OnEventListener _2D_get1(MediaDrm mediadrm)
    {
        return mediadrm.mOnEventListener;
    }

    static OnExpirationUpdateListener _2D_get2(MediaDrm mediadrm)
    {
        return mediadrm.mOnExpirationUpdateListener;
    }

    static OnKeyStatusChangeListener _2D_get3(MediaDrm mediadrm)
    {
        return mediadrm.mOnKeyStatusChangeListener;
    }

    static boolean _2D_wrap0(MediaDrm mediadrm, byte abyte0[], byte abyte1[], byte abyte2[], byte abyte3[])
    {
        return verifyNative(mediadrm, abyte0, abyte1, abyte2, abyte3);
    }

    static byte[] _2D_wrap1(MediaDrm mediadrm, byte abyte0[], byte abyte1[], byte abyte2[], byte abyte3[])
    {
        return decryptNative(mediadrm, abyte0, abyte1, abyte2, abyte3);
    }

    static byte[] _2D_wrap2(MediaDrm mediadrm, byte abyte0[], byte abyte1[], byte abyte2[], byte abyte3[])
    {
        return encryptNative(mediadrm, abyte0, abyte1, abyte2, abyte3);
    }

    static byte[] _2D_wrap3(MediaDrm mediadrm, byte abyte0[], byte abyte1[], byte abyte2[])
    {
        return signNative(mediadrm, abyte0, abyte1, abyte2);
    }

    static List _2D_wrap4(MediaDrm mediadrm, Parcel parcel)
    {
        return mediadrm.keyStatusListFromParcel(parcel);
    }

    static void _2D_wrap5(MediaDrm mediadrm, byte abyte0[], String s)
    {
        setCipherAlgorithmNative(mediadrm, abyte0, s);
    }

    static void _2D_wrap6(MediaDrm mediadrm, byte abyte0[], String s)
    {
        setMacAlgorithmNative(mediadrm, abyte0, s);
    }

    public MediaDrm(UUID uuid)
        throws UnsupportedSchemeException
    {
        Looper looper = Looper.myLooper();
        if(looper != null)
        {
            mEventHandler = new EventHandler(this, looper);
        } else
        {
            Looper looper1 = Looper.getMainLooper();
            if(looper1 != null)
                mEventHandler = new EventHandler(this, looper1);
            else
                mEventHandler = null;
        }
        native_setup(new WeakReference(this), getByteArrayFromUUID(uuid), ActivityThread.currentOpPackageName());
    }

    private static final native byte[] decryptNative(MediaDrm mediadrm, byte abyte0[], byte abyte1[], byte abyte2[], byte abyte3[]);

    private static final native byte[] encryptNative(MediaDrm mediadrm, byte abyte0[], byte abyte1[], byte abyte2[], byte abyte3[]);

    private static final byte[] getByteArrayFromUUID(UUID uuid)
    {
        long l = uuid.getMostSignificantBits();
        long l1 = uuid.getLeastSignificantBits();
        uuid = new byte[16];
        for(int i = 0; i < 8; i++)
        {
            uuid[i] = (byte)(int)(l >>> (7 - i) * 8);
            uuid[i + 8] = (byte)(int)(l1 >>> (7 - i) * 8);
        }

        return uuid;
    }

    private native ProvisionRequest getProvisionRequestNative(int i, String s);

    public static final boolean isCryptoSchemeSupported(UUID uuid)
    {
        return isCryptoSchemeSupportedNative(getByteArrayFromUUID(uuid), null);
    }

    public static final boolean isCryptoSchemeSupported(UUID uuid, String s)
    {
        return isCryptoSchemeSupportedNative(getByteArrayFromUUID(uuid), s);
    }

    private static final native boolean isCryptoSchemeSupportedNative(byte abyte0[], String s);

    private List keyStatusListFromParcel(Parcel parcel)
    {
        int i = parcel.readInt();
        ArrayList arraylist = new ArrayList(i);
        for(; i > 0; i--)
            arraylist.add(new KeyStatus(parcel.createByteArray(), parcel.readInt()));

        return arraylist;
    }

    private final native void native_finalize();

    private static final native void native_init();

    private final native void native_setup(Object obj, byte abyte0[], String s);

    private static void postEventFromNative(Object obj, int i, int j, int k, Object obj1)
    {
        obj = (MediaDrm)((WeakReference)obj).get();
        if(obj == null)
            return;
        if(((MediaDrm) (obj)).mEventHandler != null)
        {
            obj1 = ((MediaDrm) (obj)).mEventHandler.obtainMessage(i, j, k, obj1);
            ((MediaDrm) (obj)).mEventHandler.sendMessage(((Message) (obj1)));
        }
    }

    private native Certificate provideProvisionResponseNative(byte abyte0[])
        throws DeniedByServerException;

    private static final native void setCipherAlgorithmNative(MediaDrm mediadrm, byte abyte0[], String s);

    private static final native void setMacAlgorithmNative(MediaDrm mediadrm, byte abyte0[], String s);

    private static final native byte[] signNative(MediaDrm mediadrm, byte abyte0[], byte abyte1[], byte abyte2[]);

    private static final native byte[] signRSANative(MediaDrm mediadrm, byte abyte0[], String s, byte abyte1[], byte abyte2[]);

    private static final native boolean verifyNative(MediaDrm mediadrm, byte abyte0[], byte abyte1[], byte abyte2[], byte abyte3[]);

    public native void closeSession(byte abyte0[]);

    protected void finalize()
    {
        native_finalize();
    }

    public CertificateRequest getCertificateRequest(int i, String s)
    {
        s = getProvisionRequestNative(i, s);
        return new CertificateRequest(s.getData(), s.getDefaultUrl());
    }

    public CryptoSession getCryptoSession(byte abyte0[], String s, String s1)
    {
        return new CryptoSession(abyte0, s, s1);
    }

    public native KeyRequest getKeyRequest(byte abyte0[], byte abyte1[], String s, int i, HashMap hashmap)
        throws NotProvisionedException;

    public native byte[] getPropertyByteArray(String s);

    public native String getPropertyString(String s);

    public ProvisionRequest getProvisionRequest()
    {
        return getProvisionRequestNative(0, "");
    }

    public native byte[] getSecureStop(byte abyte0[]);

    public native List getSecureStops();

    public native byte[] openSession()
        throws NotProvisionedException, ResourceBusyException;

    public Certificate provideCertificateResponse(byte abyte0[])
        throws DeniedByServerException
    {
        return provideProvisionResponseNative(abyte0);
    }

    public native byte[] provideKeyResponse(byte abyte0[], byte abyte1[])
        throws NotProvisionedException, DeniedByServerException;

    public void provideProvisionResponse(byte abyte0[])
        throws DeniedByServerException
    {
        provideProvisionResponseNative(abyte0);
    }

    public native HashMap queryKeyStatus(byte abyte0[]);

    public final native void release();

    public native void releaseAllSecureStops();

    public native void releaseSecureStops(byte abyte0[]);

    public native void removeKeys(byte abyte0[]);

    public native void restoreKeys(byte abyte0[], byte abyte1[]);

    public void setOnEventListener(OnEventListener oneventlistener)
    {
        mOnEventListener = oneventlistener;
    }

    public void setOnExpirationUpdateListener(OnExpirationUpdateListener onexpirationupdatelistener, Handler handler)
    {
        if(onexpirationupdatelistener != null)
        {
            if(handler != null)
                handler = handler.getLooper();
            else
                handler = Looper.myLooper();
            if(handler != null && (mEventHandler == null || mEventHandler.getLooper() != handler))
                mEventHandler = new EventHandler(this, handler);
        }
        mOnExpirationUpdateListener = onexpirationupdatelistener;
    }

    public void setOnKeyStatusChangeListener(OnKeyStatusChangeListener onkeystatuschangelistener, Handler handler)
    {
        if(onkeystatuschangelistener != null)
        {
            if(handler != null)
                handler = handler.getLooper();
            else
                handler = Looper.myLooper();
            if(handler != null && (mEventHandler == null || mEventHandler.getLooper() != handler))
                mEventHandler = new EventHandler(this, handler);
        }
        mOnKeyStatusChangeListener = onkeystatuschangelistener;
    }

    public native void setPropertyByteArray(String s, byte abyte0[]);

    public native void setPropertyString(String s, String s1);

    public byte[] signRSA(byte abyte0[], String s, byte abyte1[], byte abyte2[])
    {
        return signRSANative(this, abyte0, s, abyte1, abyte2);
    }

    public static final int CERTIFICATE_TYPE_NONE = 0;
    public static final int CERTIFICATE_TYPE_X509 = 1;
    private static final int DRM_EVENT = 200;
    public static final int EVENT_KEY_EXPIRED = 3;
    public static final int EVENT_KEY_REQUIRED = 2;
    public static final int EVENT_PROVISION_REQUIRED = 1;
    public static final int EVENT_SESSION_RECLAIMED = 5;
    public static final int EVENT_VENDOR_DEFINED = 4;
    private static final int EXPIRATION_UPDATE = 201;
    private static final int KEY_STATUS_CHANGE = 202;
    public static final int KEY_TYPE_OFFLINE = 2;
    public static final int KEY_TYPE_RELEASE = 3;
    public static final int KEY_TYPE_STREAMING = 1;
    private static final String PERMISSION = "android.permission.ACCESS_DRM_CERTIFICATES";
    public static final String PROPERTY_ALGORITHMS = "algorithms";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_DEVICE_UNIQUE_ID = "deviceUniqueId";
    public static final String PROPERTY_VENDOR = "vendor";
    public static final String PROPERTY_VERSION = "version";
    private static final String TAG = "MediaDrm";
    private EventHandler mEventHandler;
    private long mNativeContext;
    private OnEventListener mOnEventListener;
    private EventHandler mOnExpirationUpdateEventHandler;
    private OnExpirationUpdateListener mOnExpirationUpdateListener;
    private EventHandler mOnKeyStatusChangeEventHandler;
    private OnKeyStatusChangeListener mOnKeyStatusChangeListener;

    static 
    {
        System.loadLibrary("media_jni");
        native_init();
    }
}
