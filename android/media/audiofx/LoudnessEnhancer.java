// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.audiofx;

import android.util.Log;
import java.util.StringTokenizer;

// Referenced classes of package android.media.audiofx:
//            AudioEffect

public class LoudnessEnhancer extends AudioEffect
{
    private class BaseParameterListener
        implements AudioEffect.OnParameterChangeListener
    {

        public void onParameterChange(AudioEffect audioeffect, int i, byte abyte0[], byte abyte1[])
        {
            if(i != 0)
                return;
            audioeffect = null;
            Object obj = LoudnessEnhancer._2D_get1(LoudnessEnhancer.this);
            obj;
            JVM INSTR monitorenter ;
            if(LoudnessEnhancer._2D_get0(LoudnessEnhancer.this) != null)
                audioeffect = LoudnessEnhancer._2D_get0(LoudnessEnhancer.this);
            obj;
            JVM INSTR monitorexit ;
            if(audioeffect != null)
            {
                i = -1;
                int j = 0x80000000;
                if(abyte0.length == 4)
                    i = LoudnessEnhancer.byteArrayToInt(abyte0, 0);
                if(abyte1.length == 4)
                    j = LoudnessEnhancer.byteArrayToInt(abyte1, 0);
                if(i != -1 && j != 0x80000000)
                    audioeffect.onParameterChange(LoudnessEnhancer.this, i, j);
            }
            return;
            audioeffect;
            throw audioeffect;
        }

        final LoudnessEnhancer this$0;

        private BaseParameterListener()
        {
            this$0 = LoudnessEnhancer.this;
            super();
        }

        BaseParameterListener(BaseParameterListener baseparameterlistener)
        {
            this();
        }
    }

    public static interface OnParameterChangeListener
    {

        public abstract void onParameterChange(LoudnessEnhancer loudnessenhancer, int i, int j);
    }

    public static class Settings
    {

        public String toString()
        {
            return new String((new StringBuilder()).append("LoudnessEnhancer;targetGainmB=").append(Integer.toString(targetGainmB)).toString());
        }

        public int targetGainmB;

        public Settings()
        {
        }

        public Settings(String s)
        {
            Object obj;
            Object obj1;
            obj = new StringTokenizer(s, "=;");
            if(((StringTokenizer) (obj)).countTokens() != 3)
                throw new IllegalArgumentException((new StringBuilder()).append("settings: ").append(s).toString());
            s = ((StringTokenizer) (obj)).nextToken();
            if(!s.equals("LoudnessEnhancer"))
                throw new IllegalArgumentException((new StringBuilder()).append("invalid settings for LoudnessEnhancer: ").append(s).toString());
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
            if(((String) (obj1)).equals("targetGainmB"))
                break MISSING_BLOCK_LABEL_179;
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
            targetGainmB = Integer.parseInt(((StringTokenizer) (obj)).nextToken());
            return;
        }
    }


    static OnParameterChangeListener _2D_get0(LoudnessEnhancer loudnessenhancer)
    {
        return loudnessenhancer.mParamListener;
    }

    static Object _2D_get1(LoudnessEnhancer loudnessenhancer)
    {
        return loudnessenhancer.mParamListenerLock;
    }

    public LoudnessEnhancer(int i)
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException, RuntimeException
    {
        super(EFFECT_TYPE_LOUDNESS_ENHANCER, EFFECT_TYPE_NULL, 0, i);
        mParamListener = null;
        mBaseParamListener = null;
        mParamListenerLock = new Object();
        if(i == 0)
            Log.w("LoudnessEnhancer", "WARNING: attaching a LoudnessEnhancer to global output mix is deprecated!");
    }

    public LoudnessEnhancer(int i, int j)
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException, RuntimeException
    {
        super(EFFECT_TYPE_LOUDNESS_ENHANCER, EFFECT_TYPE_NULL, i, j);
        mParamListener = null;
        mBaseParamListener = null;
        mParamListenerLock = new Object();
        if(j == 0)
            Log.w("LoudnessEnhancer", "WARNING: attaching a LoudnessEnhancer to global output mix is deprecated!");
    }

    public Settings getProperties()
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        Settings settings = new Settings();
        int ai[] = new int[1];
        checkStatus(getParameter(0, ai));
        settings.targetGainmB = ai[0];
        return settings;
    }

    public float getTargetGain()
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        int ai[] = new int[1];
        checkStatus(getParameter(0, ai));
        return (float)ai[0];
    }

    public void setParameterListener(OnParameterChangeListener onparameterchangelistener)
    {
        Object obj = mParamListenerLock;
        obj;
        JVM INSTR monitorenter ;
        if(mParamListener == null)
        {
            BaseParameterListener baseparameterlistener = JVM INSTR new #6   <Class LoudnessEnhancer$BaseParameterListener>;
            baseparameterlistener.this. BaseParameterListener(null);
            mBaseParamListener = baseparameterlistener;
            super.setParameterListener(mBaseParamListener);
        }
        mParamListener = onparameterchangelistener;
        obj;
        JVM INSTR monitorexit ;
        return;
        onparameterchangelistener;
        throw onparameterchangelistener;
    }

    public void setProperties(Settings settings)
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        checkStatus(setParameter(0, settings.targetGainmB));
    }

    public void setTargetGain(int i)
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        checkStatus(setParameter(0, i));
    }

    public static final int PARAM_TARGET_GAIN_MB = 0;
    private static final String TAG = "LoudnessEnhancer";
    private BaseParameterListener mBaseParamListener;
    private OnParameterChangeListener mParamListener;
    private final Object mParamListenerLock;
}
