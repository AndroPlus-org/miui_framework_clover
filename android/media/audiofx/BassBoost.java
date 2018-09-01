// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.audiofx;

import android.util.Log;
import java.util.StringTokenizer;

// Referenced classes of package android.media.audiofx:
//            AudioEffect

public class BassBoost extends AudioEffect
{
    private class BaseParameterListener
        implements AudioEffect.OnParameterChangeListener
    {

        public void onParameterChange(AudioEffect audioeffect, int i, byte abyte0[], byte abyte1[])
        {
            audioeffect = null;
            Object obj = BassBoost._2D_get1(BassBoost.this);
            obj;
            JVM INSTR monitorenter ;
            if(BassBoost._2D_get0(BassBoost.this) != null)
                audioeffect = BassBoost._2D_get0(BassBoost.this);
            obj;
            JVM INSTR monitorexit ;
            if(audioeffect != null)
            {
                int j = -1;
                byte byte0 = -1;
                if(abyte0.length == 4)
                    j = BassBoost.byteArrayToInt(abyte0, 0);
                short word1 = byte0;
                if(abyte1.length == 2)
                {
                    short word0 = BassBoost.byteArrayToShort(abyte1, 0);
                    word1 = word0;
                }
                if(j != -1 && word1 != -1)
                    audioeffect.onParameterChange(BassBoost.this, i, j, word1);
            }
            return;
            audioeffect;
            throw audioeffect;
        }

        final BassBoost this$0;

        private BaseParameterListener()
        {
            this$0 = BassBoost.this;
            super();
        }

        BaseParameterListener(BaseParameterListener baseparameterlistener)
        {
            this();
        }
    }

    public static interface OnParameterChangeListener
    {

        public abstract void onParameterChange(BassBoost bassboost, int i, int j, short word0);
    }

    public static class Settings
    {

        public String toString()
        {
            return new String((new StringBuilder()).append("BassBoost;strength=").append(Short.toString(strength)).toString());
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
            if(!s.equals("BassBoost"))
                throw new IllegalArgumentException((new StringBuilder()).append("invalid settings for BassBoost: ").append(s).toString());
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


    static OnParameterChangeListener _2D_get0(BassBoost bassboost)
    {
        return bassboost.mParamListener;
    }

    static Object _2D_get1(BassBoost bassboost)
    {
        return bassboost.mParamListenerLock;
    }

    public BassBoost(int i, int j)
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException, RuntimeException
    {
        boolean flag = true;
        super(EFFECT_TYPE_BASS_BOOST, EFFECT_TYPE_NULL, i, j);
        mStrengthSupported = false;
        mParamListener = null;
        mBaseParamListener = null;
        if(j == 0)
            Log.w("BassBoost", "WARNING: attaching a BassBoost to global output mix is deprecated!");
        int ai[] = new int[1];
        checkStatus(getParameter(0, ai));
        if(ai[0] == 0)
            flag = false;
        mStrengthSupported = flag;
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

    public boolean getStrengthSupported()
    {
        return mStrengthSupported;
    }

    public void setParameterListener(OnParameterChangeListener onparameterchangelistener)
    {
        Object obj = mParamListenerLock;
        obj;
        JVM INSTR monitorenter ;
        if(mParamListener == null)
        {
            mParamListener = onparameterchangelistener;
            onparameterchangelistener = JVM INSTR new #6   <Class BassBoost$BaseParameterListener>;
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

    public static final int PARAM_STRENGTH = 1;
    public static final int PARAM_STRENGTH_SUPPORTED = 0;
    private static final String TAG = "BassBoost";
    private BaseParameterListener mBaseParamListener;
    private OnParameterChangeListener mParamListener;
    private final Object mParamListenerLock = new Object();
    private boolean mStrengthSupported;
}
