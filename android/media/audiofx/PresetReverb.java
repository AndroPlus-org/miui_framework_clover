// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.audiofx;

import java.util.StringTokenizer;

// Referenced classes of package android.media.audiofx:
//            AudioEffect

public class PresetReverb extends AudioEffect
{
    private class BaseParameterListener
        implements AudioEffect.OnParameterChangeListener
    {

        public void onParameterChange(AudioEffect audioeffect, int i, byte abyte0[], byte abyte1[])
        {
            audioeffect = null;
            Object obj = PresetReverb._2D_get1(PresetReverb.this);
            obj;
            JVM INSTR monitorenter ;
            if(PresetReverb._2D_get0(PresetReverb.this) != null)
                audioeffect = PresetReverb._2D_get0(PresetReverb.this);
            obj;
            JVM INSTR monitorexit ;
            if(audioeffect != null)
            {
                int j = -1;
                byte byte0 = -1;
                if(abyte0.length == 4)
                    j = PresetReverb.byteArrayToInt(abyte0, 0);
                short word1 = byte0;
                if(abyte1.length == 2)
                {
                    short word0 = PresetReverb.byteArrayToShort(abyte1, 0);
                    word1 = word0;
                }
                if(j != -1 && word1 != -1)
                    audioeffect.onParameterChange(PresetReverb.this, i, j, word1);
            }
            return;
            audioeffect;
            throw audioeffect;
        }

        final PresetReverb this$0;

        private BaseParameterListener()
        {
            this$0 = PresetReverb.this;
            super();
        }

        BaseParameterListener(BaseParameterListener baseparameterlistener)
        {
            this();
        }
    }

    public static interface OnParameterChangeListener
    {

        public abstract void onParameterChange(PresetReverb presetreverb, int i, int j, short word0);
    }

    public static class Settings
    {

        public String toString()
        {
            return new String((new StringBuilder()).append("PresetReverb;preset=").append(Short.toString(preset)).toString());
        }

        public short preset;

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
            if(!s.equals("PresetReverb"))
                throw new IllegalArgumentException((new StringBuilder()).append("invalid settings for PresetReverb: ").append(s).toString());
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
            if(((String) (obj1)).equals("preset"))
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
            preset = Short.parseShort(((StringTokenizer) (obj)).nextToken());
            return;
        }
    }


    static OnParameterChangeListener _2D_get0(PresetReverb presetreverb)
    {
        return presetreverb.mParamListener;
    }

    static Object _2D_get1(PresetReverb presetreverb)
    {
        return presetreverb.mParamListenerLock;
    }

    public PresetReverb(int i, int j)
        throws IllegalArgumentException, UnsupportedOperationException, RuntimeException
    {
        super(EFFECT_TYPE_PRESET_REVERB, EFFECT_TYPE_NULL, i, j);
        mParamListener = null;
        mBaseParamListener = null;
    }

    public short getPreset()
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        short aword0[] = new short[1];
        checkStatus(getParameter(0, aword0));
        return aword0[0];
    }

    public Settings getProperties()
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        Settings settings = new Settings();
        short aword0[] = new short[1];
        checkStatus(getParameter(0, aword0));
        settings.preset = aword0[0];
        return settings;
    }

    public void setParameterListener(OnParameterChangeListener onparameterchangelistener)
    {
        Object obj = mParamListenerLock;
        obj;
        JVM INSTR monitorenter ;
        if(mParamListener == null)
        {
            mParamListener = onparameterchangelistener;
            onparameterchangelistener = JVM INSTR new #6   <Class PresetReverb$BaseParameterListener>;
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

    public void setPreset(short word0)
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        checkStatus(setParameter(0, word0));
    }

    public void setProperties(Settings settings)
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        checkStatus(setParameter(0, settings.preset));
    }

    public static final int PARAM_PRESET = 0;
    public static final short PRESET_LARGEHALL = 5;
    public static final short PRESET_LARGEROOM = 3;
    public static final short PRESET_MEDIUMHALL = 4;
    public static final short PRESET_MEDIUMROOM = 2;
    public static final short PRESET_NONE = 0;
    public static final short PRESET_PLATE = 6;
    public static final short PRESET_SMALLROOM = 1;
    private static final String TAG = "PresetReverb";
    private BaseParameterListener mBaseParamListener;
    private OnParameterChangeListener mParamListener;
    private final Object mParamListenerLock = new Object();
}
