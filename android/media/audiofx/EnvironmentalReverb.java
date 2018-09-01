// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.audiofx;

import java.util.StringTokenizer;

// Referenced classes of package android.media.audiofx:
//            AudioEffect

public class EnvironmentalReverb extends AudioEffect
{
    private class BaseParameterListener
        implements AudioEffect.OnParameterChangeListener
    {

        public void onParameterChange(AudioEffect audioeffect, int i, byte abyte0[], byte abyte1[])
        {
            audioeffect = null;
            Object obj = EnvironmentalReverb._2D_get1(EnvironmentalReverb.this);
            obj;
            JVM INSTR monitorenter ;
            if(EnvironmentalReverb._2D_get0(EnvironmentalReverb.this) != null)
                audioeffect = EnvironmentalReverb._2D_get0(EnvironmentalReverb.this);
            obj;
            JVM INSTR monitorexit ;
            if(audioeffect == null) goto _L2; else goto _L1
_L1:
            int j;
            int k;
            j = -1;
            k = -1;
            if(abyte0.length == 4)
                j = EnvironmentalReverb.byteArrayToInt(abyte0, 0);
            if(abyte1.length != 2) goto _L4; else goto _L3
_L3:
            k = EnvironmentalReverb.byteArrayToShort(abyte1, 0);
_L6:
            if(j != -1 && k != -1)
                audioeffect.onParameterChange(EnvironmentalReverb.this, i, j, k);
_L2:
            return;
            audioeffect;
            throw audioeffect;
_L4:
            if(abyte1.length == 4)
                k = EnvironmentalReverb.byteArrayToInt(abyte1, 0);
            if(true) goto _L6; else goto _L5
_L5:
        }

        final EnvironmentalReverb this$0;

        private BaseParameterListener()
        {
            this$0 = EnvironmentalReverb.this;
            super();
        }

        BaseParameterListener(BaseParameterListener baseparameterlistener)
        {
            this();
        }
    }

    public static interface OnParameterChangeListener
    {

        public abstract void onParameterChange(EnvironmentalReverb environmentalreverb, int i, int j, int k);
    }

    public static class Settings
    {

        public String toString()
        {
            return new String((new StringBuilder()).append("EnvironmentalReverb;roomLevel=").append(Short.toString(roomLevel)).append(";roomHFLevel=").append(Short.toString(roomHFLevel)).append(";decayTime=").append(Integer.toString(decayTime)).append(";decayHFRatio=").append(Short.toString(decayHFRatio)).append(";reflectionsLevel=").append(Short.toString(reflectionsLevel)).append(";reflectionsDelay=").append(Integer.toString(reflectionsDelay)).append(";reverbLevel=").append(Short.toString(reverbLevel)).append(";reverbDelay=").append(Integer.toString(reverbDelay)).append(";diffusion=").append(Short.toString(diffusion)).append(";density=").append(Short.toString(density)).toString());
        }

        public short decayHFRatio;
        public int decayTime;
        public short density;
        public short diffusion;
        public int reflectionsDelay;
        public short reflectionsLevel;
        public int reverbDelay;
        public short reverbLevel;
        public short roomHFLevel;
        public short roomLevel;

        public Settings()
        {
        }

        public Settings(String s)
        {
            Object obj;
            Object obj1;
            obj = new StringTokenizer(s, "=;");
            ((StringTokenizer) (obj)).countTokens();
            if(((StringTokenizer) (obj)).countTokens() != 21)
                throw new IllegalArgumentException((new StringBuilder()).append("settings: ").append(s).toString());
            s = ((StringTokenizer) (obj)).nextToken();
            if(!s.equals("EnvironmentalReverb"))
                throw new IllegalArgumentException((new StringBuilder()).append("invalid settings for EnvironmentalReverb: ").append(s).toString());
            IllegalArgumentException illegalargumentexception;
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
            if(((String) (obj1)).equals("roomLevel"))
                break MISSING_BLOCK_LABEL_185;
            s = ((String) (obj1));
            illegalargumentexception = JVM INSTR new #40  <Class IllegalArgumentException>;
            s = ((String) (obj1));
            obj = JVM INSTR new #42  <Class StringBuilder>;
            s = ((String) (obj1));
            ((StringBuilder) (obj)).StringBuilder();
            s = ((String) (obj1));
            illegalargumentexception.IllegalArgumentException(((StringBuilder) (obj)).append("invalid key name: ").append(((String) (obj1))).toString());
            s = ((String) (obj1));
            throw illegalargumentexception;
            s = ((String) (obj1));
            roomLevel = Short.parseShort(((StringTokenizer) (obj)).nextToken());
            s = ((String) (obj1));
            obj1 = ((StringTokenizer) (obj)).nextToken();
            s = ((String) (obj1));
            if(((String) (obj1)).equals("roomHFLevel"))
                break MISSING_BLOCK_LABEL_260;
            s = ((String) (obj1));
            Object obj2 = JVM INSTR new #40  <Class IllegalArgumentException>;
            s = ((String) (obj1));
            obj = JVM INSTR new #42  <Class StringBuilder>;
            s = ((String) (obj1));
            ((StringBuilder) (obj)).StringBuilder();
            s = ((String) (obj1));
            ((IllegalArgumentException) (obj2)).IllegalArgumentException(((StringBuilder) (obj)).append("invalid key name: ").append(((String) (obj1))).toString());
            s = ((String) (obj1));
            throw obj2;
            s = ((String) (obj1));
            roomHFLevel = Short.parseShort(((StringTokenizer) (obj)).nextToken());
            s = ((String) (obj1));
            obj1 = ((StringTokenizer) (obj)).nextToken();
            s = ((String) (obj1));
            if(((String) (obj1)).equals("decayTime"))
                break MISSING_BLOCK_LABEL_335;
            s = ((String) (obj1));
            obj = JVM INSTR new #40  <Class IllegalArgumentException>;
            s = ((String) (obj1));
            obj2 = JVM INSTR new #42  <Class StringBuilder>;
            s = ((String) (obj1));
            ((StringBuilder) (obj2)).StringBuilder();
            s = ((String) (obj1));
            ((IllegalArgumentException) (obj)).IllegalArgumentException(((StringBuilder) (obj2)).append("invalid key name: ").append(((String) (obj1))).toString());
            s = ((String) (obj1));
            throw obj;
            s = ((String) (obj1));
            decayTime = Integer.parseInt(((StringTokenizer) (obj)).nextToken());
            s = ((String) (obj1));
            obj1 = ((StringTokenizer) (obj)).nextToken();
            s = ((String) (obj1));
            if(((String) (obj1)).equals("decayHFRatio"))
                break MISSING_BLOCK_LABEL_410;
            s = ((String) (obj1));
            obj2 = JVM INSTR new #40  <Class IllegalArgumentException>;
            s = ((String) (obj1));
            obj = JVM INSTR new #42  <Class StringBuilder>;
            s = ((String) (obj1));
            ((StringBuilder) (obj)).StringBuilder();
            s = ((String) (obj1));
            ((IllegalArgumentException) (obj2)).IllegalArgumentException(((StringBuilder) (obj)).append("invalid key name: ").append(((String) (obj1))).toString());
            s = ((String) (obj1));
            throw obj2;
            s = ((String) (obj1));
            decayHFRatio = Short.parseShort(((StringTokenizer) (obj)).nextToken());
            s = ((String) (obj1));
            obj1 = ((StringTokenizer) (obj)).nextToken();
            s = ((String) (obj1));
            if(((String) (obj1)).equals("reflectionsLevel"))
                break MISSING_BLOCK_LABEL_485;
            s = ((String) (obj1));
            obj = JVM INSTR new #40  <Class IllegalArgumentException>;
            s = ((String) (obj1));
            obj2 = JVM INSTR new #42  <Class StringBuilder>;
            s = ((String) (obj1));
            ((StringBuilder) (obj2)).StringBuilder();
            s = ((String) (obj1));
            ((IllegalArgumentException) (obj)).IllegalArgumentException(((StringBuilder) (obj2)).append("invalid key name: ").append(((String) (obj1))).toString());
            s = ((String) (obj1));
            throw obj;
            s = ((String) (obj1));
            reflectionsLevel = Short.parseShort(((StringTokenizer) (obj)).nextToken());
            s = ((String) (obj1));
            obj1 = ((StringTokenizer) (obj)).nextToken();
            s = ((String) (obj1));
            if(((String) (obj1)).equals("reflectionsDelay"))
                break MISSING_BLOCK_LABEL_560;
            s = ((String) (obj1));
            obj = JVM INSTR new #40  <Class IllegalArgumentException>;
            s = ((String) (obj1));
            obj2 = JVM INSTR new #42  <Class StringBuilder>;
            s = ((String) (obj1));
            ((StringBuilder) (obj2)).StringBuilder();
            s = ((String) (obj1));
            ((IllegalArgumentException) (obj)).IllegalArgumentException(((StringBuilder) (obj2)).append("invalid key name: ").append(((String) (obj1))).toString());
            s = ((String) (obj1));
            throw obj;
            s = ((String) (obj1));
            reflectionsDelay = Integer.parseInt(((StringTokenizer) (obj)).nextToken());
            s = ((String) (obj1));
            obj1 = ((StringTokenizer) (obj)).nextToken();
            s = ((String) (obj1));
            if(((String) (obj1)).equals("reverbLevel"))
                break MISSING_BLOCK_LABEL_635;
            s = ((String) (obj1));
            obj2 = JVM INSTR new #40  <Class IllegalArgumentException>;
            s = ((String) (obj1));
            obj = JVM INSTR new #42  <Class StringBuilder>;
            s = ((String) (obj1));
            ((StringBuilder) (obj)).StringBuilder();
            s = ((String) (obj1));
            ((IllegalArgumentException) (obj2)).IllegalArgumentException(((StringBuilder) (obj)).append("invalid key name: ").append(((String) (obj1))).toString());
            s = ((String) (obj1));
            throw obj2;
            s = ((String) (obj1));
            reverbLevel = Short.parseShort(((StringTokenizer) (obj)).nextToken());
            s = ((String) (obj1));
            obj1 = ((StringTokenizer) (obj)).nextToken();
            s = ((String) (obj1));
            if(((String) (obj1)).equals("reverbDelay"))
                break MISSING_BLOCK_LABEL_710;
            s = ((String) (obj1));
            obj = JVM INSTR new #40  <Class IllegalArgumentException>;
            s = ((String) (obj1));
            obj2 = JVM INSTR new #42  <Class StringBuilder>;
            s = ((String) (obj1));
            ((StringBuilder) (obj2)).StringBuilder();
            s = ((String) (obj1));
            ((IllegalArgumentException) (obj)).IllegalArgumentException(((StringBuilder) (obj2)).append("invalid key name: ").append(((String) (obj1))).toString());
            s = ((String) (obj1));
            throw obj;
            s = ((String) (obj1));
            reverbDelay = Integer.parseInt(((StringTokenizer) (obj)).nextToken());
            s = ((String) (obj1));
            obj1 = ((StringTokenizer) (obj)).nextToken();
            s = ((String) (obj1));
            if(((String) (obj1)).equals("diffusion"))
                break MISSING_BLOCK_LABEL_785;
            s = ((String) (obj1));
            obj2 = JVM INSTR new #40  <Class IllegalArgumentException>;
            s = ((String) (obj1));
            obj = JVM INSTR new #42  <Class StringBuilder>;
            s = ((String) (obj1));
            ((StringBuilder) (obj)).StringBuilder();
            s = ((String) (obj1));
            ((IllegalArgumentException) (obj2)).IllegalArgumentException(((StringBuilder) (obj)).append("invalid key name: ").append(((String) (obj1))).toString());
            s = ((String) (obj1));
            throw obj2;
            s = ((String) (obj1));
            diffusion = Short.parseShort(((StringTokenizer) (obj)).nextToken());
            s = ((String) (obj1));
            obj1 = ((StringTokenizer) (obj)).nextToken();
            s = ((String) (obj1));
            if(((String) (obj1)).equals("density"))
                break MISSING_BLOCK_LABEL_860;
            s = ((String) (obj1));
            obj = JVM INSTR new #40  <Class IllegalArgumentException>;
            s = ((String) (obj1));
            obj2 = JVM INSTR new #42  <Class StringBuilder>;
            s = ((String) (obj1));
            ((StringBuilder) (obj2)).StringBuilder();
            s = ((String) (obj1));
            ((IllegalArgumentException) (obj)).IllegalArgumentException(((StringBuilder) (obj2)).append("invalid key name: ").append(((String) (obj1))).toString());
            s = ((String) (obj1));
            throw obj;
            s = ((String) (obj1));
            density = Short.parseShort(((StringTokenizer) (obj)).nextToken());
            return;
        }
    }


    static OnParameterChangeListener _2D_get0(EnvironmentalReverb environmentalreverb)
    {
        return environmentalreverb.mParamListener;
    }

    static Object _2D_get1(EnvironmentalReverb environmentalreverb)
    {
        return environmentalreverb.mParamListenerLock;
    }

    public EnvironmentalReverb(int i, int j)
        throws IllegalArgumentException, UnsupportedOperationException, RuntimeException
    {
        super(EFFECT_TYPE_ENV_REVERB, EFFECT_TYPE_NULL, i, j);
        mParamListener = null;
        mBaseParamListener = null;
    }

    public short getDecayHFRatio()
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        byte abyte0[] = new byte[2];
        checkStatus(getParameter(3, abyte0));
        return byteArrayToShort(abyte0);
    }

    public int getDecayTime()
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        byte abyte0[] = new byte[4];
        checkStatus(getParameter(2, abyte0));
        return byteArrayToInt(abyte0);
    }

    public short getDensity()
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        byte abyte0[] = new byte[2];
        checkStatus(getParameter(9, abyte0));
        return byteArrayToShort(abyte0);
    }

    public short getDiffusion()
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        byte abyte0[] = new byte[2];
        checkStatus(getParameter(8, abyte0));
        return byteArrayToShort(abyte0);
    }

    public Settings getProperties()
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        byte abyte0[] = new byte[PROPERTY_SIZE];
        checkStatus(getParameter(10, abyte0));
        Settings settings = new Settings();
        settings.roomLevel = byteArrayToShort(abyte0, 0);
        settings.roomHFLevel = byteArrayToShort(abyte0, 2);
        settings.decayTime = byteArrayToInt(abyte0, 4);
        settings.decayHFRatio = byteArrayToShort(abyte0, 8);
        settings.reflectionsLevel = byteArrayToShort(abyte0, 10);
        settings.reflectionsDelay = byteArrayToInt(abyte0, 12);
        settings.reverbLevel = byteArrayToShort(abyte0, 16);
        settings.reverbDelay = byteArrayToInt(abyte0, 18);
        settings.diffusion = byteArrayToShort(abyte0, 22);
        settings.density = byteArrayToShort(abyte0, 24);
        return settings;
    }

    public int getReflectionsDelay()
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        byte abyte0[] = new byte[4];
        checkStatus(getParameter(5, abyte0));
        return byteArrayToInt(abyte0);
    }

    public short getReflectionsLevel()
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        byte abyte0[] = new byte[2];
        checkStatus(getParameter(4, abyte0));
        return byteArrayToShort(abyte0);
    }

    public int getReverbDelay()
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        byte abyte0[] = new byte[4];
        checkStatus(getParameter(7, abyte0));
        return byteArrayToInt(abyte0);
    }

    public short getReverbLevel()
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        byte abyte0[] = new byte[2];
        checkStatus(getParameter(6, abyte0));
        return byteArrayToShort(abyte0);
    }

    public short getRoomHFLevel()
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        byte abyte0[] = new byte[2];
        checkStatus(getParameter(1, abyte0));
        return byteArrayToShort(abyte0);
    }

    public short getRoomLevel()
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        byte abyte0[] = new byte[2];
        checkStatus(getParameter(0, abyte0));
        return byteArrayToShort(abyte0);
    }

    public void setDecayHFRatio(short word0)
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        checkStatus(setParameter(3, shortToByteArray(word0)));
    }

    public void setDecayTime(int i)
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        checkStatus(setParameter(2, intToByteArray(i)));
    }

    public void setDensity(short word0)
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        checkStatus(setParameter(9, shortToByteArray(word0)));
    }

    public void setDiffusion(short word0)
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        checkStatus(setParameter(8, shortToByteArray(word0)));
    }

    public void setParameterListener(OnParameterChangeListener onparameterchangelistener)
    {
        Object obj = mParamListenerLock;
        obj;
        JVM INSTR monitorenter ;
        if(mParamListener == null)
        {
            mParamListener = onparameterchangelistener;
            onparameterchangelistener = JVM INSTR new #6   <Class EnvironmentalReverb$BaseParameterListener>;
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
        checkStatus(setParameter(10, concatArrays(new byte[][] {
            shortToByteArray(settings.roomLevel), shortToByteArray(settings.roomHFLevel), intToByteArray(settings.decayTime), shortToByteArray(settings.decayHFRatio), shortToByteArray(settings.reflectionsLevel), intToByteArray(settings.reflectionsDelay), shortToByteArray(settings.reverbLevel), intToByteArray(settings.reverbDelay), shortToByteArray(settings.diffusion), shortToByteArray(settings.density)
        })));
    }

    public void setReflectionsDelay(int i)
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        checkStatus(setParameter(5, intToByteArray(i)));
    }

    public void setReflectionsLevel(short word0)
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        checkStatus(setParameter(4, shortToByteArray(word0)));
    }

    public void setReverbDelay(int i)
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        checkStatus(setParameter(7, intToByteArray(i)));
    }

    public void setReverbLevel(short word0)
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        checkStatus(setParameter(6, shortToByteArray(word0)));
    }

    public void setRoomHFLevel(short word0)
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        checkStatus(setParameter(1, shortToByteArray(word0)));
    }

    public void setRoomLevel(short word0)
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        checkStatus(setParameter(0, shortToByteArray(word0)));
    }

    public static final int PARAM_DECAY_HF_RATIO = 3;
    public static final int PARAM_DECAY_TIME = 2;
    public static final int PARAM_DENSITY = 9;
    public static final int PARAM_DIFFUSION = 8;
    private static final int PARAM_PROPERTIES = 10;
    public static final int PARAM_REFLECTIONS_DELAY = 5;
    public static final int PARAM_REFLECTIONS_LEVEL = 4;
    public static final int PARAM_REVERB_DELAY = 7;
    public static final int PARAM_REVERB_LEVEL = 6;
    public static final int PARAM_ROOM_HF_LEVEL = 1;
    public static final int PARAM_ROOM_LEVEL = 0;
    private static int PROPERTY_SIZE = 0;
    private static final String TAG = "EnvironmentalReverb";
    private BaseParameterListener mBaseParamListener;
    private OnParameterChangeListener mParamListener;
    private final Object mParamListenerLock = new Object();

    static 
    {
        PROPERTY_SIZE = 26;
    }
}
