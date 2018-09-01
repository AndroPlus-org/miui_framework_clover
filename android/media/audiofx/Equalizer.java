// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.audiofx;

import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

// Referenced classes of package android.media.audiofx:
//            AudioEffect

public class Equalizer extends AudioEffect
{
    private class BaseParameterListener
        implements AudioEffect.OnParameterChangeListener
    {

        public void onParameterChange(AudioEffect audioeffect, int i, byte abyte0[], byte abyte1[])
        {
            audioeffect = null;
            Object obj = Equalizer._2D_get1(Equalizer.this);
            obj;
            JVM INSTR monitorenter ;
            if(Equalizer._2D_get0(Equalizer.this) != null)
                audioeffect = Equalizer._2D_get0(Equalizer.this);
            obj;
            JVM INSTR monitorexit ;
            if(audioeffect == null) goto _L2; else goto _L1
_L1:
            int j;
            int k;
            int l;
            j = -1;
            byte byte0 = -1;
            k = -1;
            l = byte0;
            if(abyte0.length >= 4)
            {
                int i1 = Equalizer.byteArrayToInt(abyte0, 0);
                j = i1;
                l = byte0;
                if(abyte0.length >= 8)
                {
                    l = Equalizer.byteArrayToInt(abyte0, 4);
                    j = i1;
                }
            }
            if(abyte1.length != 2) goto _L4; else goto _L3
_L3:
            k = Equalizer.byteArrayToShort(abyte1, 0);
_L6:
            if(j != -1 && k != -1)
                audioeffect.onParameterChange(Equalizer.this, i, j, l, k);
_L2:
            return;
            audioeffect;
            throw audioeffect;
_L4:
            if(abyte1.length == 4)
                k = Equalizer.byteArrayToInt(abyte1, 0);
            if(true) goto _L6; else goto _L5
_L5:
        }

        final Equalizer this$0;

        private BaseParameterListener()
        {
            this$0 = Equalizer.this;
            super();
        }

        BaseParameterListener(BaseParameterListener baseparameterlistener)
        {
            this();
        }
    }

    public static interface OnParameterChangeListener
    {

        public abstract void onParameterChange(Equalizer equalizer, int i, int j, int k, int l);
    }

    public static class Settings
    {

        public String toString()
        {
            String s = new String((new StringBuilder()).append("Equalizer;curPreset=").append(Short.toString(curPreset)).append(";numBands=").append(Short.toString(numBands)).toString());
            for(int i = 0; i < numBands; i++)
                s = s.concat((new StringBuilder()).append(";band").append(i + 1).append("Level=").append(Short.toString(bandLevels[i])).toString());

            return s;
        }

        public short bandLevels[];
        public short curPreset;
        public short numBands;

        public Settings()
        {
            numBands = (short)0;
            bandLevels = null;
        }

        public Settings(String s)
        {
            Object obj;
            String s1;
            Object obj1;
            numBands = (short)0;
            bandLevels = null;
            obj = new StringTokenizer(s, "=;");
            ((StringTokenizer) (obj)).countTokens();
            if(((StringTokenizer) (obj)).countTokens() < 5)
                throw new IllegalArgumentException((new StringBuilder()).append("settings: ").append(s).toString());
            s1 = ((StringTokenizer) (obj)).nextToken();
            if(!s1.equals("Equalizer"))
                throw new IllegalArgumentException((new StringBuilder()).append("invalid settings for Equalizer: ").append(s1).toString());
            try
            {
                obj1 = ((StringTokenizer) (obj)).nextToken();
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("invalid value for key: ").append(s1).toString());
            }
            s1 = ((String) (obj1));
            if(((String) (obj1)).equals("curPreset"))
                break MISSING_BLOCK_LABEL_201;
            s1 = ((String) (obj1));
            s = JVM INSTR new #37  <Class IllegalArgumentException>;
            s1 = ((String) (obj1));
            obj = JVM INSTR new #39  <Class StringBuilder>;
            s1 = ((String) (obj1));
            ((StringBuilder) (obj)).StringBuilder();
            s1 = ((String) (obj1));
            s.IllegalArgumentException(((StringBuilder) (obj)).append("invalid key name: ").append(((String) (obj1))).toString());
            s1 = ((String) (obj1));
            throw s;
            s1 = ((String) (obj1));
            curPreset = Short.parseShort(((StringTokenizer) (obj)).nextToken());
            s1 = ((String) (obj1));
            obj1 = ((StringTokenizer) (obj)).nextToken();
            s1 = ((String) (obj1));
            if(((String) (obj1)).equals("numBands"))
                break MISSING_BLOCK_LABEL_284;
            s1 = ((String) (obj1));
            obj = JVM INSTR new #37  <Class IllegalArgumentException>;
            s1 = ((String) (obj1));
            s = JVM INSTR new #39  <Class StringBuilder>;
            s1 = ((String) (obj1));
            s.StringBuilder();
            s1 = ((String) (obj1));
            ((IllegalArgumentException) (obj)).IllegalArgumentException(s.append("invalid key name: ").append(((String) (obj1))).toString());
            s1 = ((String) (obj1));
            throw obj;
            s1 = ((String) (obj1));
            numBands = Short.parseShort(((StringTokenizer) (obj)).nextToken());
            s1 = ((String) (obj1));
            if(((StringTokenizer) (obj)).countTokens() == numBands * 2)
                break MISSING_BLOCK_LABEL_363;
            s1 = ((String) (obj1));
            obj = JVM INSTR new #37  <Class IllegalArgumentException>;
            s1 = ((String) (obj1));
            StringBuilder stringbuilder = JVM INSTR new #39  <Class StringBuilder>;
            s1 = ((String) (obj1));
            stringbuilder.StringBuilder();
            s1 = ((String) (obj1));
            ((IllegalArgumentException) (obj)).IllegalArgumentException(stringbuilder.append("settings: ").append(s).toString());
            s1 = ((String) (obj1));
            throw obj;
            s1 = ((String) (obj1));
            bandLevels = new short[numBands];
            int i;
            i = 0;
            s = ((String) (obj1));
_L2:
            s1 = s;
            if(i >= numBands)
                break; /* Loop/switch isn't completed */
            s1 = s;
            s = ((StringTokenizer) (obj)).nextToken();
            s1 = s;
            obj1 = JVM INSTR new #39  <Class StringBuilder>;
            s1 = s;
            ((StringBuilder) (obj1)).StringBuilder();
            s1 = s;
            if(s.equals(((StringBuilder) (obj1)).append("band").append(i + 1).append("Level").toString()))
                break MISSING_BLOCK_LABEL_489;
            s1 = s;
            obj1 = JVM INSTR new #37  <Class IllegalArgumentException>;
            s1 = s;
            obj = JVM INSTR new #39  <Class StringBuilder>;
            s1 = s;
            ((StringBuilder) (obj)).StringBuilder();
            s1 = s;
            ((IllegalArgumentException) (obj1)).IllegalArgumentException(((StringBuilder) (obj)).append("invalid key name: ").append(s).toString());
            s1 = s;
            throw obj1;
            s1 = s;
            bandLevels[i] = Short.parseShort(((StringTokenizer) (obj)).nextToken());
            i++;
            if(true) goto _L2; else goto _L1
_L1:
        }
    }


    static OnParameterChangeListener _2D_get0(Equalizer equalizer)
    {
        return equalizer.mParamListener;
    }

    static Object _2D_get1(Equalizer equalizer)
    {
        return equalizer.mParamListenerLock;
    }

    public Equalizer(int i, int j)
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException, RuntimeException
    {
        super(EFFECT_TYPE_EQUALIZER, EFFECT_TYPE_NULL, i, j);
        mNumBands = (short)0;
        mParamListener = null;
        mBaseParamListener = null;
        mParamListenerLock = new Object();
        if(j == 0)
            Log.w("Equalizer", "WARNING: attaching an Equalizer to global output mix is deprecated!");
        getNumberOfBands();
        mNumPresets = getNumberOfPresets();
        if(mNumPresets == 0) goto _L2; else goto _L1
_L1:
        byte abyte0[];
        int ai[];
        mPresetNames = new String[mNumPresets];
        abyte0 = new byte[32];
        ai = new int[2];
        ai[0] = 8;
        i = 0;
_L3:
        if(i >= mNumPresets)
            break; /* Loop/switch isn't completed */
        ai[1] = i;
        checkStatus(getParameter(ai, abyte0));
        for(j = 0; abyte0[j] != 0; j++);
        String as[];
        String s;
        as = mPresetNames;
        s = JVM INSTR new #110 <Class String>;
        s.String(abyte0, 0, j, "ISO-8859-1");
        as[i] = s;
_L4:
        i++;
        if(true) goto _L3; else goto _L2
        UnsupportedEncodingException unsupportedencodingexception;
        unsupportedencodingexception;
        Log.e("Equalizer", "preset name decode error");
          goto _L4
_L2:
    }

    public short getBand(int i)
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        short aword0[] = new short[1];
        checkStatus(getParameter(new int[] {
            5, i
        }, aword0));
        return aword0[0];
    }

    public int[] getBandFreqRange(short word0)
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        int ai[] = new int[2];
        checkStatus(getParameter(new int[] {
            4, word0
        }, ai));
        return ai;
    }

    public short getBandLevel(short word0)
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        short aword0[] = new short[1];
        checkStatus(getParameter(new int[] {
            2, word0
        }, aword0));
        return aword0[0];
    }

    public short[] getBandLevelRange()
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        short aword0[] = new short[2];
        checkStatus(getParameter(1, aword0));
        return aword0;
    }

    public int getCenterFreq(short word0)
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        int ai[] = new int[1];
        checkStatus(getParameter(new int[] {
            3, word0
        }, ai));
        return ai[0];
    }

    public short getCurrentPreset()
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        short aword0[] = new short[1];
        checkStatus(getParameter(6, aword0));
        return aword0[0];
    }

    public short getNumberOfBands()
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        if(mNumBands != 0)
        {
            return mNumBands;
        } else
        {
            short aword0[] = new short[1];
            checkStatus(getParameter(new int[] {
                0
            }, aword0));
            mNumBands = aword0[0];
            return mNumBands;
        }
    }

    public short getNumberOfPresets()
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        short aword0[] = new short[1];
        checkStatus(getParameter(7, aword0));
        return aword0[0];
    }

    public String getPresetName(short word0)
    {
        if(word0 >= 0 && word0 < mNumPresets)
            return mPresetNames[word0];
        else
            return "";
    }

    public Settings getProperties()
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        byte abyte0[] = new byte[mNumBands * 2 + 4];
        checkStatus(getParameter(9, abyte0));
        Settings settings = new Settings();
        settings.curPreset = byteArrayToShort(abyte0, 0);
        settings.numBands = byteArrayToShort(abyte0, 2);
        settings.bandLevels = new short[mNumBands];
        for(int i = 0; i < mNumBands; i++)
            settings.bandLevels[i] = byteArrayToShort(abyte0, i * 2 + 4);

        return settings;
    }

    public void setBandLevel(short word0, short word1)
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        checkStatus(setParameter(new int[] {
            2, word0
        }, new short[] {
            word1
        }));
    }

    public void setParameterListener(OnParameterChangeListener onparameterchangelistener)
    {
        Object obj = mParamListenerLock;
        obj;
        JVM INSTR monitorenter ;
        if(mParamListener == null)
        {
            mParamListener = onparameterchangelistener;
            onparameterchangelistener = JVM INSTR new #6   <Class Equalizer$BaseParameterListener>;
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
        if(settings.numBands != settings.bandLevels.length || settings.numBands != mNumBands)
            throw new IllegalArgumentException((new StringBuilder()).append("settings invalid band count: ").append(settings.numBands).toString());
        byte abyte0[] = concatArrays(new byte[][] {
            shortToByteArray(settings.curPreset), shortToByteArray(mNumBands)
        });
        for(int i = 0; i < mNumBands; i++)
            abyte0 = concatArrays(new byte[][] {
                abyte0, shortToByteArray(settings.bandLevels[i])
            });

        checkStatus(setParameter(9, abyte0));
    }

    public void usePreset(short word0)
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        checkStatus(setParameter(6, word0));
    }

    public static final int PARAM_BAND_FREQ_RANGE = 4;
    public static final int PARAM_BAND_LEVEL = 2;
    public static final int PARAM_CENTER_FREQ = 3;
    public static final int PARAM_CURRENT_PRESET = 6;
    public static final int PARAM_GET_BAND = 5;
    public static final int PARAM_GET_NUM_OF_PRESETS = 7;
    public static final int PARAM_GET_PRESET_NAME = 8;
    public static final int PARAM_LEVEL_RANGE = 1;
    public static final int PARAM_NUM_BANDS = 0;
    private static final int PARAM_PROPERTIES = 9;
    public static final int PARAM_STRING_SIZE_MAX = 32;
    private static final String TAG = "Equalizer";
    private BaseParameterListener mBaseParamListener;
    private short mNumBands;
    private int mNumPresets;
    private OnParameterChangeListener mParamListener;
    private final Object mParamListenerLock;
    private String mPresetNames[];
}
