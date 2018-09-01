// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.audiofx;

import android.media.AudioDeviceInfo;
import android.media.AudioFormat;
import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.StringTokenizer;

// Referenced classes of package android.media.audiofx:
//            AudioEffect

public class Virtualizer extends AudioEffect
{
    private class BaseParameterListener
        implements AudioEffect.OnParameterChangeListener
    {

        public void onParameterChange(AudioEffect audioeffect, int i, byte abyte0[], byte abyte1[])
        {
            audioeffect = null;
            Object obj = Virtualizer._2D_get1(Virtualizer.this);
            obj;
            JVM INSTR monitorenter ;
            if(Virtualizer._2D_get0(Virtualizer.this) != null)
                audioeffect = Virtualizer._2D_get0(Virtualizer.this);
            obj;
            JVM INSTR monitorexit ;
            if(audioeffect != null)
            {
                int j = -1;
                byte byte0 = -1;
                if(abyte0.length == 4)
                    j = Virtualizer.byteArrayToInt(abyte0, 0);
                short word1 = byte0;
                if(abyte1.length == 2)
                {
                    short word0 = Virtualizer.byteArrayToShort(abyte1, 0);
                    word1 = word0;
                }
                if(j != -1 && word1 != -1)
                    audioeffect.onParameterChange(Virtualizer.this, i, j, word1);
            }
            return;
            audioeffect;
            throw audioeffect;
        }

        final Virtualizer this$0;

        private BaseParameterListener()
        {
            this$0 = Virtualizer.this;
            super();
        }

        BaseParameterListener(BaseParameterListener baseparameterlistener)
        {
            this();
        }
    }

    public static interface OnParameterChangeListener
    {

        public abstract void onParameterChange(Virtualizer virtualizer, int i, int j, short word0);
    }

    public static class Settings
    {

        public String toString()
        {
            return new String((new StringBuilder()).append("Virtualizer;strength=").append(Short.toString(strength)).toString());
        }

        public short strength;

        public Settings()
        {
        }

        public Settings(String s)
        {
            Object obj;
            Object obj1;
            obj = new StringTokenizer(s, "=;");
            ((StringTokenizer) (obj)).countTokens();
            if(((StringTokenizer) (obj)).countTokens() != 3)
                throw new IllegalArgumentException((new StringBuilder()).append("settings: ").append(s).toString());
            s = ((StringTokenizer) (obj)).nextToken();
            if(!s.equals("Virtualizer"))
                throw new IllegalArgumentException((new StringBuilder()).append("invalid settings for Virtualizer: ").append(s).toString());
            StringBuilder stringbuilder;
            try
            {
                obj1 = ((StringTokenizer) (obj)).nextToken();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj1)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("invalid value for key: ").append(s).toString());
            }
            s = ((String) (obj1));
            if(((String) (obj1)).equals("strength"))
                break MISSING_BLOCK_LABEL_184;
            s = ((String) (obj1));
            obj = JVM INSTR new #30  <Class IllegalArgumentException>;
            s = ((String) (obj1));
            stringbuilder = JVM INSTR new #32  <Class StringBuilder>;
            s = ((String) (obj1));
            stringbuilder.StringBuilder();
            s = ((String) (obj1));
            ((IllegalArgumentException) (obj)).IllegalArgumentException(stringbuilder.append("invalid key name: ").append(((String) (obj1))).toString());
            s = ((String) (obj1));
            throw obj;
            s = ((String) (obj1));
            strength = Short.parseShort(((StringTokenizer) (obj)).nextToken());
            return;
        }
    }


    static OnParameterChangeListener _2D_get0(Virtualizer virtualizer)
    {
        return virtualizer.mParamListener;
    }

    static Object _2D_get1(Virtualizer virtualizer)
    {
        return virtualizer.mParamListenerLock;
    }

    public Virtualizer(int i, int j)
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException, RuntimeException
    {
        boolean flag = true;
        super(EFFECT_TYPE_VIRTUALIZER, EFFECT_TYPE_NULL, i, j);
        mStrengthSupported = false;
        mParamListener = null;
        mBaseParamListener = null;
        if(j == 0)
            Log.w("Virtualizer", "WARNING: attaching a Virtualizer to global output mix is deprecated!");
        int ai[] = new int[1];
        checkStatus(getParameter(0, ai));
        if(ai[0] == 0)
            flag = false;
        mStrengthSupported = flag;
    }

    private static int deviceToMode(int i)
    {
        switch(i)
        {
        case 15: // '\017'
        case 16: // '\020'
        case 17: // '\021'
        case 18: // '\022'
        case 20: // '\024'
        case 21: // '\025'
        default:
            return 0;

        case 1: // '\001'
        case 3: // '\003'
        case 4: // '\004'
        case 7: // '\007'
        case 22: // '\026'
            return 2;

        case 2: // '\002'
        case 5: // '\005'
        case 6: // '\006'
        case 8: // '\b'
        case 9: // '\t'
        case 10: // '\n'
        case 11: // '\013'
        case 12: // '\f'
        case 13: // '\r'
        case 14: // '\016'
        case 19: // '\023'
            return 3;
        }
    }

    private boolean getAnglesInt(int i, int j, int ai[])
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        if(i == 0)
            throw new IllegalArgumentException("Virtualizer: illegal CHANNEL_INVALID channel mask");
        if(i == 1)
            i = 12;
        int k = AudioFormat.channelCountFromOutChannelMask(i);
        if(ai != null && ai.length < k * 3)
        {
            Log.e("Virtualizer", (new StringBuilder()).append("Size of array for angles cannot accomodate number of channels in mask (").append(k).append(")").toString());
            throw new IllegalArgumentException((new StringBuilder()).append("Virtualizer: array for channel / angle pairs is too small: is ").append(ai.length).append(", should be ").append(k * 3).toString());
        }
        ByteBuffer bytebuffer = ByteBuffer.allocate(12);
        bytebuffer.order(ByteOrder.nativeOrder());
        bytebuffer.putInt(2);
        bytebuffer.putInt(AudioFormat.convertChannelOutMaskToNativeMask(i));
        bytebuffer.putInt(AudioDeviceInfo.convertDeviceTypeToInternalDevice(j));
        byte abyte0[] = new byte[k * 4 * 3];
        i = getParameter(bytebuffer.array(), abyte0);
        if(i >= 0)
        {
            if(ai != null)
            {
                ByteBuffer bytebuffer1 = ByteBuffer.wrap(abyte0);
                bytebuffer1.order(ByteOrder.nativeOrder());
                for(i = 0; i < k; i++)
                {
                    ai[i * 3] = AudioFormat.convertNativeChannelMaskToOutMask(bytebuffer1.getInt(i * 4 * 3));
                    ai[i * 3 + 1] = bytebuffer1.getInt(i * 4 * 3 + 4);
                    ai[i * 3 + 2] = bytebuffer1.getInt(i * 4 * 3 + 8);
                }

            }
            return true;
        }
        if(i == -4)
        {
            return false;
        } else
        {
            checkStatus(i);
            Log.e("Virtualizer", (new StringBuilder()).append("unexpected status code ").append(i).append(" after getParameter(PARAM_VIRTUAL_SPEAKER_ANGLES)").toString());
            return false;
        }
    }

    private static int getDeviceForModeForce(int i)
        throws IllegalArgumentException
    {
        if(i == 1)
            return 0;
        else
            return getDeviceForModeQuery(i);
    }

    private static int getDeviceForModeQuery(int i)
        throws IllegalArgumentException
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Virtualizer: illegal virtualization mode ").append(i).toString());

        case 2: // '\002'
            return 4;

        case 3: // '\003'
            return 2;
        }
    }

    public boolean canVirtualize(int i, int j)
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        return getAnglesInt(i, getDeviceForModeQuery(j), null);
    }

    public boolean forceVirtualizationMode(int i)
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        i = setParameter(3, AudioDeviceInfo.convertDeviceTypeToInternalDevice(getDeviceForModeForce(i)));
        if(i >= 0)
            return true;
        if(i == -4)
        {
            return false;
        } else
        {
            checkStatus(i);
            Log.e("Virtualizer", (new StringBuilder()).append("unexpected status code ").append(i).append(" after setParameter(PARAM_FORCE_VIRTUALIZATION_MODE)").toString());
            return false;
        }
    }

    public Settings getProperties()
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        Settings settings = new Settings();
        short aword0[] = new short[1];
        checkStatus(getParameter(1, aword0));
        settings.strength = aword0[0];
        return settings;
    }

    public short getRoundedStrength()
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        short aword0[] = new short[1];
        checkStatus(getParameter(1, aword0));
        return aword0[0];
    }

    public boolean getSpeakerAngles(int i, int j, int ai[])
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        if(ai == null)
            throw new IllegalArgumentException("Virtualizer: illegal null channel / angle array");
        else
            return getAnglesInt(i, getDeviceForModeQuery(j), ai);
    }

    public boolean getStrengthSupported()
    {
        return mStrengthSupported;
    }

    public int getVirtualizationMode()
        throws IllegalStateException, UnsupportedOperationException
    {
        int ai[] = new int[1];
        int i = getParameter(4, ai);
        if(i >= 0)
            return deviceToMode(AudioDeviceInfo.convertInternalDeviceToDeviceType(ai[0]));
        if(i == -4)
        {
            return 0;
        } else
        {
            checkStatus(i);
            Log.e("Virtualizer", (new StringBuilder()).append("unexpected status code ").append(i).append(" after getParameter(PARAM_VIRTUALIZATION_MODE)").toString());
            return 0;
        }
    }

    public void setParameterListener(OnParameterChangeListener onparameterchangelistener)
    {
        Object obj = mParamListenerLock;
        obj;
        JVM INSTR monitorenter ;
        if(mParamListener == null)
        {
            mParamListener = onparameterchangelistener;
            onparameterchangelistener = JVM INSTR new #6   <Class Virtualizer$BaseParameterListener>;
            onparameterchangelistener.this. BaseParameterListener(null);
            mBaseParamListener = onparameterchangelistener;
            super.setParameterListener(mBaseParamListener);
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        onparameterchangelistener;
        throw onparameterchangelistener;
    }

    public void setProperties(Settings settings)
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        checkStatus(setParameter(1, settings.strength));
    }

    public void setStrength(short word0)
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        checkStatus(setParameter(1, word0));
    }

    private static final boolean DEBUG = false;
    public static final int PARAM_FORCE_VIRTUALIZATION_MODE = 3;
    public static final int PARAM_STRENGTH = 1;
    public static final int PARAM_STRENGTH_SUPPORTED = 0;
    public static final int PARAM_VIRTUALIZATION_MODE = 4;
    public static final int PARAM_VIRTUAL_SPEAKER_ANGLES = 2;
    private static final String TAG = "Virtualizer";
    public static final int VIRTUALIZATION_MODE_AUTO = 1;
    public static final int VIRTUALIZATION_MODE_BINAURAL = 2;
    public static final int VIRTUALIZATION_MODE_OFF = 0;
    public static final int VIRTUALIZATION_MODE_TRANSAURAL = 3;
    private BaseParameterListener mBaseParamListener;
    private OnParameterChangeListener mParamListener;
    private final Object mParamListenerLock = new Object();
    private boolean mStrengthSupported;
}
