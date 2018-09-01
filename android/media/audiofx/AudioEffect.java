// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.audiofx;

import android.app.ActivityThread;
import android.os.*;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.UUID;

public class AudioEffect
{
    public static class Descriptor
    {

        public String connectMode;
        public String implementor;
        public String name;
        public UUID type;
        public UUID uuid;

        public Descriptor()
        {
        }

        public Descriptor(String s, String s1, String s2, String s3, String s4)
        {
            type = UUID.fromString(s);
            uuid = UUID.fromString(s1);
            connectMode = s2;
            name = s3;
            implementor = s4;
        }
    }

    private class NativeEventHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            boolean flag;
            boolean flag1;
            flag = true;
            flag1 = true;
            if(mAudioEffect == null)
                return;
            message.what;
            JVM INSTR tableswitch 0 2: default 44
        //                       0 139
        //                       1 73
        //                       2 207;
               goto _L1 _L2 _L3 _L4
_L1:
            Log.e("AudioEffect-JAVA", (new StringBuilder()).append("handleMessage() Unknown event type: ").append(message.what).toString());
_L6:
            return;
_L3:
            Object obj = mListenerLock;
            obj;
            JVM INSTR monitorenter ;
            Object obj1 = AudioEffect._2D_get1(mAudioEffect);
            obj;
            JVM INSTR monitorexit ;
            if(obj1 != null)
            {
                obj = mAudioEffect;
                if(message.arg1 == 0)
                    flag1 = false;
                ((OnEnableStatusChangeListener) (obj1)).onEnableStatusChange(((AudioEffect) (obj)), flag1);
            }
            continue; /* Loop/switch isn't completed */
            message;
            throw message;
_L2:
            obj = mListenerLock;
            obj;
            JVM INSTR monitorenter ;
            obj1 = AudioEffect._2D_get0(mAudioEffect);
            obj;
            JVM INSTR monitorexit ;
            if(obj1 != null)
            {
                obj = mAudioEffect;
                boolean flag2;
                if(message.arg1 != 0)
                    flag2 = flag;
                else
                    flag2 = false;
                ((OnControlStatusChangeListener) (obj1)).onControlStatusChange(((AudioEffect) (obj)), flag2);
            }
            continue; /* Loop/switch isn't completed */
            message;
            throw message;
_L4:
            byte abyte0[] = ((byte []) (mListenerLock));
            abyte0;
            JVM INSTR monitorenter ;
            obj = AudioEffect._2D_get2(mAudioEffect);
            abyte0;
            JVM INSTR monitorexit ;
            if(obj != null)
            {
                int i = message.arg1;
                abyte0 = (byte[])message.obj;
                int j = AudioEffect.byteArrayToInt(abyte0, 0);
                int k = AudioEffect.byteArrayToInt(abyte0, 4);
                int l = AudioEffect.byteArrayToInt(abyte0, 8);
                message = new byte[k];
                byte abyte1[] = new byte[l];
                System.arraycopy(abyte0, 12, message, 0, k);
                System.arraycopy(abyte0, i, abyte1, 0, l);
                ((OnParameterChangeListener) (obj)).onParameterChange(mAudioEffect, j, message, abyte1);
            }
            if(true) goto _L6; else goto _L5
_L5:
            message;
            throw message;
        }

        private AudioEffect mAudioEffect;
        final AudioEffect this$0;

        public NativeEventHandler(AudioEffect audioeffect1, Looper looper)
        {
            this$0 = AudioEffect.this;
            super(looper);
            mAudioEffect = audioeffect1;
        }
    }

    public static interface OnControlStatusChangeListener
    {

        public abstract void onControlStatusChange(AudioEffect audioeffect, boolean flag);
    }

    public static interface OnEnableStatusChangeListener
    {

        public abstract void onEnableStatusChange(AudioEffect audioeffect, boolean flag);
    }

    public static interface OnParameterChangeListener
    {

        public abstract void onParameterChange(AudioEffect audioeffect, int i, byte abyte0[], byte abyte1[]);
    }


    static OnControlStatusChangeListener _2D_get0(AudioEffect audioeffect)
    {
        return audioeffect.mControlChangeStatusListener;
    }

    static OnEnableStatusChangeListener _2D_get1(AudioEffect audioeffect)
    {
        return audioeffect.mEnableStatusChangeListener;
    }

    static OnParameterChangeListener _2D_get2(AudioEffect audioeffect)
    {
        return audioeffect.mParameterChangeListener;
    }

    public AudioEffect(UUID uuid, UUID uuid1, int i, int j)
        throws IllegalArgumentException, UnsupportedOperationException, RuntimeException
    {
        mState = 0;
        mStateLock = new Object();
        mEnableStatusChangeListener = null;
        mControlChangeStatusListener = null;
        mParameterChangeListener = null;
        mListenerLock = new Object();
        mNativeEventHandler = null;
        int ai[] = new int[1];
        Descriptor adescriptor[] = new Descriptor[1];
        i = native_setup(new WeakReference(this), uuid.toString(), uuid1.toString(), i, j, ai, adescriptor, ActivityThread.currentOpPackageName());
        if(i != 0 && i != -2)
        {
            Log.e("AudioEffect-JAVA", (new StringBuilder()).append("Error code ").append(i).append(" when initializing AudioEffect.").toString());
            switch(i)
            {
            default:
                throw new RuntimeException((new StringBuilder()).append("Cannot initialize effect engine for type: ").append(uuid).append(" Error: ").append(i).toString());

            case -4: 
                throw new IllegalArgumentException((new StringBuilder()).append("Effect type: ").append(uuid).append(" not supported.").toString());

            case -5: 
                throw new UnsupportedOperationException("Effect library not loaded");
            }
        }
        mId = ai[0];
        mDescriptor = adescriptor[0];
        uuid1 = ((UUID) (mStateLock));
        uuid1;
        JVM INSTR monitorenter ;
        mState = 1;
        uuid1;
        JVM INSTR monitorexit ;
        return;
        uuid;
        throw uuid;
    }

    public static int byteArrayToInt(byte abyte0[])
    {
        return byteArrayToInt(abyte0, 0);
    }

    public static int byteArrayToInt(byte abyte0[], int i)
    {
        abyte0 = ByteBuffer.wrap(abyte0);
        abyte0.order(ByteOrder.nativeOrder());
        return abyte0.getInt(i);
    }

    public static short byteArrayToShort(byte abyte0[])
    {
        return byteArrayToShort(abyte0, 0);
    }

    public static short byteArrayToShort(byte abyte0[], int i)
    {
        abyte0 = ByteBuffer.wrap(abyte0);
        abyte0.order(ByteOrder.nativeOrder());
        return abyte0.getShort(i);
    }

    public static transient byte[] concatArrays(byte abyte0[][])
    {
        int i = 0;
        int j = abyte0.length;
        for(int k = 0; k < j; k++)
            i += abyte0[k].length;

        byte abyte1[] = new byte[i];
        i = 0;
        j = abyte0.length;
        for(int l = 0; l < j; l++)
        {
            byte abyte2[] = abyte0[l];
            System.arraycopy(abyte2, 0, abyte1, i, abyte2.length);
            i += abyte2.length;
        }

        return abyte1;
    }

    private void createNativeEventHandler()
    {
        Looper looper = Looper.myLooper();
        if(looper != null)
        {
            mNativeEventHandler = new NativeEventHandler(this, looper);
        } else
        {
            Looper looper1 = Looper.getMainLooper();
            if(looper1 != null)
                mNativeEventHandler = new NativeEventHandler(this, looper1);
            else
                mNativeEventHandler = null;
        }
    }

    public static byte[] intToByteArray(int i)
    {
        ByteBuffer bytebuffer = ByteBuffer.allocate(4);
        bytebuffer.order(ByteOrder.nativeOrder());
        bytebuffer.putInt(i);
        return bytebuffer.array();
    }

    public static boolean isEffectTypeAvailable(UUID uuid)
    {
        Descriptor adescriptor[] = queryEffects();
        if(adescriptor == null)
            return false;
        for(int i = 0; i < adescriptor.length; i++)
            if(adescriptor[i].type.equals(uuid))
                return true;

        return false;
    }

    public static boolean isError(int i)
    {
        boolean flag = false;
        if(i < 0)
            flag = true;
        return flag;
    }

    private final native int native_command(int i, int j, byte abyte0[], int k, byte abyte1[]);

    private final native void native_finalize();

    private final native boolean native_getEnabled();

    private final native int native_getParameter(int i, byte abyte0[], int j, byte abyte1[]);

    private final native boolean native_hasControl();

    private static final native void native_init();

    private static native Object[] native_query_effects();

    private static native Object[] native_query_pre_processing(int i);

    private final native void native_release();

    private final native int native_setEnabled(boolean flag);

    private final native int native_setParameter(int i, byte abyte0[], int j, byte abyte1[]);

    private final native int native_setup(Object obj, String s, String s1, int i, int j, int ai[], Object aobj[], 
            String s2);

    private static void postEventFromNative(Object obj, int i, int j, int k, Object obj1)
    {
        obj = (AudioEffect)((WeakReference)obj).get();
        if(obj == null)
            return;
        if(((AudioEffect) (obj)).mNativeEventHandler != null)
        {
            obj1 = ((AudioEffect) (obj)).mNativeEventHandler.obtainMessage(i, j, k, obj1);
            ((AudioEffect) (obj)).mNativeEventHandler.sendMessage(((Message) (obj1)));
        }
    }

    public static Descriptor[] queryEffects()
    {
        return (Descriptor[])native_query_effects();
    }

    public static Descriptor[] queryPreProcessings(int i)
    {
        return (Descriptor[])native_query_pre_processing(i);
    }

    public static byte[] shortToByteArray(short word0)
    {
        ByteBuffer bytebuffer = ByteBuffer.allocate(2);
        bytebuffer.order(ByteOrder.nativeOrder());
        bytebuffer.putShort(word0);
        return bytebuffer.array();
    }

    public void checkState(String s)
        throws IllegalStateException
    {
        synchronized(mStateLock)
        {
            if(mState != 1)
            {
                IllegalStateException illegalstateexception = JVM INSTR new #387 <Class IllegalStateException>;
                StringBuilder stringbuilder = JVM INSTR new #218 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                illegalstateexception.IllegalStateException(stringbuilder.append(s).append(" called on uninitialized AudioEffect.").toString());
                throw illegalstateexception;
            }
            break MISSING_BLOCK_LABEL_55;
        }
        obj;
        JVM INSTR monitorexit ;
    }

    public void checkStatus(int i)
    {
        if(isError(i))
            switch(i)
            {
            default:
                throw new RuntimeException("AudioEffect: set/get parameter error");

            case -4: 
                throw new IllegalArgumentException("AudioEffect: bad parameter value");

            case -5: 
                throw new UnsupportedOperationException("AudioEffect: invalid parameter operation");
            }
        else
            return;
    }

    public int command(int i, byte abyte0[], byte abyte1[])
        throws IllegalStateException
    {
        checkState("command()");
        return native_command(i, abyte0.length, abyte0, abyte1.length, abyte1);
    }

    protected void finalize()
    {
        native_finalize();
    }

    public Descriptor getDescriptor()
        throws IllegalStateException
    {
        checkState("getDescriptor()");
        return mDescriptor;
    }

    public boolean getEnabled()
        throws IllegalStateException
    {
        checkState("getEnabled()");
        return native_getEnabled();
    }

    public int getId()
        throws IllegalStateException
    {
        checkState("getId()");
        return mId;
    }

    public int getParameter(int i, byte abyte0[])
        throws IllegalStateException
    {
        return getParameter(intToByteArray(i), abyte0);
    }

    public int getParameter(int i, int ai[])
        throws IllegalStateException
    {
        if(ai.length > 2)
            return -4;
        byte abyte0[] = intToByteArray(i);
        byte abyte1[] = new byte[ai.length * 4];
        i = getParameter(abyte0, abyte1);
        if(i == 4 || i == 8)
        {
            ai[0] = byteArrayToInt(abyte1);
            if(i == 8)
                ai[1] = byteArrayToInt(abyte1, 4);
            i /= 4;
        } else
        {
            i = -1;
        }
        return i;
    }

    public int getParameter(int i, short aword0[])
        throws IllegalStateException
    {
        if(aword0.length > 2)
            return -4;
        byte abyte0[] = intToByteArray(i);
        byte abyte1[] = new byte[aword0.length * 2];
        i = getParameter(abyte0, abyte1);
        if(i == 2 || i == 4)
        {
            aword0[0] = byteArrayToShort(abyte1);
            if(i == 4)
                aword0[1] = byteArrayToShort(abyte1, 2);
            i /= 2;
        } else
        {
            i = -1;
        }
        return i;
    }

    public int getParameter(byte abyte0[], byte abyte1[])
        throws IllegalStateException
    {
        checkState("getParameter()");
        return native_getParameter(abyte0.length, abyte0, abyte1.length, abyte1);
    }

    public int getParameter(int ai[], byte abyte0[])
        throws IllegalStateException
    {
        if(ai.length > 2)
            return -4;
        byte abyte1[] = intToByteArray(ai[0]);
        byte abyte2[] = abyte1;
        if(ai.length > 1)
            abyte2 = concatArrays(new byte[][] {
                abyte1, intToByteArray(ai[1])
            });
        return getParameter(abyte2, abyte0);
    }

    public int getParameter(int ai[], int ai1[])
        throws IllegalStateException
    {
        if(ai.length > 2 || ai1.length > 2)
            return -4;
        byte abyte0[] = intToByteArray(ai[0]);
        byte abyte1[] = abyte0;
        if(ai.length > 1)
            abyte1 = concatArrays(new byte[][] {
                abyte0, intToByteArray(ai[1])
            });
        ai = new byte[ai1.length * 4];
        int i = getParameter(abyte1, ((byte []) (ai)));
        if(i == 4 || i == 8)
        {
            ai1[0] = byteArrayToInt(ai);
            if(i == 8)
                ai1[1] = byteArrayToInt(ai, 4);
            i /= 4;
        } else
        {
            i = -1;
        }
        return i;
    }

    public int getParameter(int ai[], short aword0[])
        throws IllegalStateException
    {
        if(ai.length > 2 || aword0.length > 2)
            return -4;
        byte abyte0[] = intToByteArray(ai[0]);
        byte abyte1[] = abyte0;
        if(ai.length > 1)
            abyte1 = concatArrays(new byte[][] {
                abyte0, intToByteArray(ai[1])
            });
        ai = new byte[aword0.length * 2];
        int i = getParameter(abyte1, ((byte []) (ai)));
        if(i == 2 || i == 4)
        {
            aword0[0] = byteArrayToShort(ai);
            if(i == 4)
                aword0[1] = byteArrayToShort(ai, 2);
            i /= 2;
        } else
        {
            i = -1;
        }
        return i;
    }

    public boolean hasControl()
        throws IllegalStateException
    {
        checkState("hasControl()");
        return native_hasControl();
    }

    public void release()
    {
        Object obj = mStateLock;
        obj;
        JVM INSTR monitorenter ;
        native_release();
        mState = 0;
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setControlStatusListener(OnControlStatusChangeListener oncontrolstatuschangelistener)
    {
        Object obj = mListenerLock;
        obj;
        JVM INSTR monitorenter ;
        mControlChangeStatusListener = oncontrolstatuschangelistener;
        obj;
        JVM INSTR monitorexit ;
        if(oncontrolstatuschangelistener != null && mNativeEventHandler == null)
            createNativeEventHandler();
        return;
        oncontrolstatuschangelistener;
        throw oncontrolstatuschangelistener;
    }

    public void setEnableStatusListener(OnEnableStatusChangeListener onenablestatuschangelistener)
    {
        Object obj = mListenerLock;
        obj;
        JVM INSTR monitorenter ;
        mEnableStatusChangeListener = onenablestatuschangelistener;
        obj;
        JVM INSTR monitorexit ;
        if(onenablestatuschangelistener != null && mNativeEventHandler == null)
            createNativeEventHandler();
        return;
        onenablestatuschangelistener;
        throw onenablestatuschangelistener;
    }

    public int setEnabled(boolean flag)
        throws IllegalStateException
    {
        checkState("setEnabled()");
        return native_setEnabled(flag);
    }

    public int setParameter(int i, int j)
        throws IllegalStateException
    {
        return setParameter(intToByteArray(i), intToByteArray(j));
    }

    public int setParameter(int i, short word0)
        throws IllegalStateException
    {
        return setParameter(intToByteArray(i), shortToByteArray(word0));
    }

    public int setParameter(int i, byte abyte0[])
        throws IllegalStateException
    {
        return setParameter(intToByteArray(i), abyte0);
    }

    public int setParameter(byte abyte0[], byte abyte1[])
        throws IllegalStateException
    {
        checkState("setParameter()");
        return native_setParameter(abyte0.length, abyte0, abyte1.length, abyte1);
    }

    public int setParameter(int ai[], byte abyte0[])
        throws IllegalStateException
    {
        if(ai.length > 2)
            return -4;
        byte abyte1[] = intToByteArray(ai[0]);
        byte abyte2[] = abyte1;
        if(ai.length > 1)
            abyte2 = concatArrays(new byte[][] {
                abyte1, intToByteArray(ai[1])
            });
        return setParameter(abyte2, abyte0);
    }

    public int setParameter(int ai[], int ai1[])
        throws IllegalStateException
    {
        if(ai.length > 2 || ai1.length > 2)
            return -4;
        byte abyte0[] = intToByteArray(ai[0]);
        byte abyte1[] = abyte0;
        if(ai.length > 1)
            abyte1 = concatArrays(new byte[][] {
                abyte0, intToByteArray(ai[1])
            });
        abyte0 = intToByteArray(ai1[0]);
        ai = abyte0;
        if(ai1.length > 1)
            ai = concatArrays(new byte[][] {
                abyte0, intToByteArray(ai1[1])
            });
        return setParameter(abyte1, ((byte []) (ai)));
    }

    public int setParameter(int ai[], short aword0[])
        throws IllegalStateException
    {
        if(ai.length > 2 || aword0.length > 2)
            return -4;
        byte abyte0[] = intToByteArray(ai[0]);
        byte abyte1[] = abyte0;
        if(ai.length > 1)
            abyte1 = concatArrays(new byte[][] {
                abyte0, intToByteArray(ai[1])
            });
        abyte0 = shortToByteArray(aword0[0]);
        ai = abyte0;
        if(aword0.length > 1)
            ai = concatArrays(new byte[][] {
                abyte0, shortToByteArray(aword0[1])
            });
        return setParameter(abyte1, ((byte []) (ai)));
    }

    public void setParameterListener(OnParameterChangeListener onparameterchangelistener)
    {
        Object obj = mListenerLock;
        obj;
        JVM INSTR monitorenter ;
        mParameterChangeListener = onparameterchangelistener;
        obj;
        JVM INSTR monitorexit ;
        if(onparameterchangelistener != null && mNativeEventHandler == null)
            createNativeEventHandler();
        return;
        onparameterchangelistener;
        throw onparameterchangelistener;
    }

    public static final String ACTION_CLOSE_AUDIO_EFFECT_CONTROL_SESSION = "android.media.action.CLOSE_AUDIO_EFFECT_CONTROL_SESSION";
    public static final String ACTION_DISPLAY_AUDIO_EFFECT_CONTROL_PANEL = "android.media.action.DISPLAY_AUDIO_EFFECT_CONTROL_PANEL";
    public static final String ACTION_OPEN_AUDIO_EFFECT_CONTROL_SESSION = "android.media.action.OPEN_AUDIO_EFFECT_CONTROL_SESSION";
    public static final int ALREADY_EXISTS = -2;
    public static final int CONTENT_TYPE_GAME = 2;
    public static final int CONTENT_TYPE_MOVIE = 1;
    public static final int CONTENT_TYPE_MUSIC = 0;
    public static final int CONTENT_TYPE_VOICE = 3;
    public static final String EFFECT_AUXILIARY = "Auxiliary";
    public static final String EFFECT_INSERT = "Insert";
    public static final String EFFECT_PRE_PROCESSING = "Pre Processing";
    public static final UUID EFFECT_TYPE_AEC = UUID.fromString("7b491460-8d4d-11e0-bd61-0002a5d5c51b");
    public static final UUID EFFECT_TYPE_AGC = UUID.fromString("0a8abfe0-654c-11e0-ba26-0002a5d5c51b");
    public static final UUID EFFECT_TYPE_BASS_BOOST = UUID.fromString("0634f220-ddd4-11db-a0fc-0002a5d5c51b");
    public static final UUID EFFECT_TYPE_ENV_REVERB = UUID.fromString("c2e5d5f0-94bd-4763-9cac-4e234d06839e");
    public static final UUID EFFECT_TYPE_EQUALIZER = UUID.fromString("0bed4300-ddd6-11db-8f34-0002a5d5c51b");
    public static final UUID EFFECT_TYPE_LOUDNESS_ENHANCER = UUID.fromString("fe3199be-aed0-413f-87bb-11260eb63cf1");
    public static final UUID EFFECT_TYPE_NS = UUID.fromString("58b4b260-8e06-11e0-aa8e-0002a5d5c51b");
    public static final UUID EFFECT_TYPE_NULL = UUID.fromString("ec7178ec-e5e1-4432-a3f4-4657e6795210");
    public static final UUID EFFECT_TYPE_PRESET_REVERB = UUID.fromString("47382d60-ddd8-11db-bf3a-0002a5d5c51b");
    public static final UUID EFFECT_TYPE_VIRTUALIZER = UUID.fromString("37cc2c00-dddd-11db-8577-0002a5d5c51b");
    public static final int ERROR = -1;
    public static final int ERROR_BAD_VALUE = -4;
    public static final int ERROR_DEAD_OBJECT = -7;
    public static final int ERROR_INVALID_OPERATION = -5;
    public static final int ERROR_NO_INIT = -3;
    public static final int ERROR_NO_MEMORY = -6;
    public static final String EXTRA_AUDIO_SESSION = "android.media.extra.AUDIO_SESSION";
    public static final String EXTRA_CONTENT_TYPE = "android.media.extra.CONTENT_TYPE";
    public static final String EXTRA_PACKAGE_NAME = "android.media.extra.PACKAGE_NAME";
    public static final int NATIVE_EVENT_CONTROL_STATUS = 0;
    public static final int NATIVE_EVENT_ENABLED_STATUS = 1;
    public static final int NATIVE_EVENT_PARAMETER_CHANGED = 2;
    public static final int STATE_INITIALIZED = 1;
    public static final int STATE_UNINITIALIZED = 0;
    public static final int SUCCESS = 0;
    private static final String TAG = "AudioEffect-JAVA";
    private OnControlStatusChangeListener mControlChangeStatusListener;
    private Descriptor mDescriptor;
    private OnEnableStatusChangeListener mEnableStatusChangeListener;
    private int mId;
    private long mJniData;
    public final Object mListenerLock;
    private long mNativeAudioEffect;
    public NativeEventHandler mNativeEventHandler;
    private OnParameterChangeListener mParameterChangeListener;
    private int mState;
    private final Object mStateLock;

    static 
    {
        System.loadLibrary("audioeffect_jni");
        native_init();
    }
}
