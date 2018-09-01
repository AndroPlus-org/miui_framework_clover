// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.telephony.dtmf;

import android.util.Log;
import java.io.IOException;

// Referenced classes of package miui.telephony.dtmf:
//            DTMFAudioInputStream, DTMFDataConveter

public class DTMFUtil
{

    public DTMFUtil()
    {
        this(8000, 3);
    }

    public DTMFUtil(int i, int j)
    {
        mPreCalculatedCosines = new float[TARGET_FREQUENCIES.length];
        mWnkList = new float[TARGET_FREQUENCIES.length];
        mSampleRate = i;
        initPrecalculatedCosines();
        if(j <= 0)
            j = 3;
        mHealthy = j;
    }

    private float[] bufferFilter(float af[], int i)
    {
        if(af.length <= 2)
            return af;
        float f = af[0];
        float f1 = af[1];
        for(i = 0; i < af.length / 2;)
        {
            float f2 = f;
            if(f != af[i])
                f2 = af[i];
            i += 2;
            f = f2;
        }

        for(i = 1; i < af.length / 2;)
        {
            float f3 = f1;
            if(f1 != af[i])
                f3 = af[i];
            i += 2;
            f1 = f3;
        }

        if(f == af[0] && f1 == af[1])
            return (new float[] {
                af[0], af[1]
            });
        else
            return null;
    }

    private float[] dtmfDetectAndValidate(byte abyte0[], int i, boolean flag)
        throws IOException
    {
        int j = 0;
        int k = 0;
        byte abyte1[] = new byte[256];
        float af[] = new float[mHealthy * 2];
        float af1[] = new float[2];
        DTMFAudioInputStream dtmfaudioinputstream = new DTMFAudioInputStream(abyte0);
        DTMFDataConveter dtmfdataconveter = new DTMFDataConveter(i, flag);
        do
        {
            do
            {
                if(k >= mHealthy || j >= abyte0.length)
                {
                    dtmfaudioinputstream.close();
                    float af2[];
                    if(k == mHealthy)
                        return bufferFilter(af, k * 2);
                    else
                        return null;
                }
                if(j + 256 > abyte0.length)
                    i = abyte0.length;
                else
                    i = j + 256;
                dtmfaudioinputstream.read(abyte1, i - j);
                af2 = dtmfFrequenciesDetecter(dtmfdataconveter.byteToFloat(abyte1));
                j = i;
            } while(af2 == null);
            af[k * 2] = af2[0];
            af[k * 2 + 1] = af2[1];
            k++;
            j = i;
        } while(true);
    }

    private float[] dtmfFrequenciesDetecter(float af[])
    {
        boolean aflag[] = new boolean[TARGET_FREQUENCIES.length];
        float af1[] = new float[2];
        af1[1] = -1F;
        af1[0] = -1F;
        int i = getDecibelThreshold(mSampleRate);
        if(i == -1)
        {
            Log.i("DTMFUtil", "can not get threshold");
            return af1;
        }
        float af2[] = new float[TARGET_FREQUENCIES.length];
        for(int j = 0; j < TARGET_FREQUENCIES.length; j++)
        {
            float f = 0.0F;
            float f1 = 0.0F;
            int l = 0;
            float f2;
            do
            {
                f2 = f;
                if(l >= af.length)
                    break;
                f = f1;
                f1 = (mPreCalculatedCosines[j] * f - f2) + af[l];
                l++;
            } while(true);
            af2[j] = (float)(20D * Math.log10(Math.abs(f1 - mWnkList[j] * f2)));
            if(af2[j] > (float)i)
                aflag[j] = true;
        }

        for(int k = 0; k < aflag.length / 2; k++)
        {
            int i1 = (k + 5) % TARGET_FREQUENCIES.length;
            if(!aflag[k] || !aflag[i1])
                continue;
            af1[0] = TARGET_FREQUENCIES[k];
            af1[1] = TARGET_FREQUENCIES[i1];
            if(isValidate(af1))
                return af1;
        }

        return null;
    }

    private static float[] getAudioFloatBuffer(float af[], int i, int j)
    {
        j = (i * j) / 1000;
        if(af.length != 2)
        {
            Log.i("DTMFUtil", "parameter buffer is null");
            return null;
        }
        double d = af[0];
        double d1 = af[1];
        af = new float[j];
        for(j = 0; j < af.length; j++)
        {
            double d2 = (double)j / (double)i;
            af[j] = (float)(0.5D * Math.sin(6.2831853071795862D * d * d2) + 0.5D * Math.sin(6.2831853071795862D * d1 * d2));
        }

        return af;
    }

    private int getDecibelThreshold(int i)
    {
        if(SAMPLE_RATE_LIST.length != DECIBEL_THRESHOLD_LIST.length)
        {
            Log.i("DTMFUtil", "the number of SAMPLE_RATE_LIST and DECIBEL_THRESHOLD_LIST can not match");
            return -1;
        }
        int j = 0;
        do
        {
            if(j >= SAMPLE_RATE_LIST.length || i == SAMPLE_RATE_LIST[j])
                if(j >= SAMPLE_RATE_LIST.length)
                {
                    Log.i("DTMFUtil", "can not find db threshold");
                    return -1;
                } else
                {
                    return DECIBEL_THRESHOLD_LIST[j];
                }
            j++;
        } while(true);
    }

    public static float[] getDualFrequence(char c)
    {
        float af[] = new float[2];
        c;
        JVM INSTR tableswitch 35 57: default 112
    //                   35 267
    //                   36 112
    //                   37 112
    //                   38 112
    //                   39 112
    //                   40 112
    //                   41 112
    //                   42 254
    //                   43 112
    //                   44 112
    //                   45 112
    //                   46 112
    //                   47 112
    //                   48 124
    //                   49 137
    //                   50 150
    //                   51 163
    //                   52 176
    //                   53 189
    //                   54 202
    //                   55 215
    //                   56 228
    //                   57 241;
           goto _L1 _L2 _L1 _L1 _L1 _L1 _L1 _L1 _L3 _L1 _L1 _L1 _L1 _L1 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13
_L1:
        af[1] = -1F;
        af[0] = -1F;
_L15:
        return af;
_L4:
        af[0] = 941F;
        af[1] = 1336F;
        continue; /* Loop/switch isn't completed */
_L5:
        af[0] = 697F;
        af[1] = 1209F;
        continue; /* Loop/switch isn't completed */
_L6:
        af[0] = 697F;
        af[1] = 1336F;
        continue; /* Loop/switch isn't completed */
_L7:
        af[0] = 697F;
        af[1] = 1477F;
        continue; /* Loop/switch isn't completed */
_L8:
        af[0] = 770F;
        af[1] = 1209F;
        continue; /* Loop/switch isn't completed */
_L9:
        af[0] = 770F;
        af[1] = 1336F;
        continue; /* Loop/switch isn't completed */
_L10:
        af[0] = 770F;
        af[1] = 1477F;
        continue; /* Loop/switch isn't completed */
_L11:
        af[0] = 852F;
        af[1] = 1209F;
        continue; /* Loop/switch isn't completed */
_L12:
        af[0] = 852F;
        af[1] = 1336F;
        continue; /* Loop/switch isn't completed */
_L13:
        af[0] = 852F;
        af[1] = 1477F;
        continue; /* Loop/switch isn't completed */
_L3:
        af[0] = 941F;
        af[1] = 1209F;
        continue; /* Loop/switch isn't completed */
_L2:
        af[0] = 941F;
        af[1] = 1477F;
        if(true) goto _L15; else goto _L14
_L14:
    }

    public static float getTargetHighFrequency()
    {
        return 1075F;
    }

    public static float getTargetLowFrequency()
    {
        return 600F;
    }

    public static int getVersion()
    {
        return 2;
    }

    private void initPrecalculatedCosines()
    {
        if(mSampleRate == 0)
        {
            Log.i("DTMFUtil", "fail to dispatching funtion initPrecalculatedCosines: you need to set mSampleRate");
            return;
        }
        for(int i = 0; i < TARGET_FREQUENCIES.length; i++)
        {
            double d = Math.cos(((double)TARGET_FREQUENCIES[i] * 6.2831853071795862D) / (double)mSampleRate);
            mPreCalculatedCosines[i] = (float)(2D * d);
            d = Math.exp(((double)TARGET_FREQUENCIES[i] * -6.2831853071795862D) / (double)mSampleRate);
            mWnkList[i] = (float)d;
        }

    }

    private boolean isValidate(float af[])
    {
        if(af == null)
        {
            Log.i("DTMFUtil", "null parameter");
            return false;
        }
        if(af[1] - af[0] < 268F)
            return false;
        int i = 0;
        int j = 0;
        for(int k = 0; k < TARGET_FREQUENCIES.length; k++)
        {
            if(af[0] == TARGET_FREQUENCIES[k])
                i = k;
            if(af[1] == TARGET_FREQUENCIES[k])
                j = k;
        }

        return j - i == 5;
    }

    public int getHealthy()
    {
        return mHealthy;
    }

    public int getSampleRate()
    {
        return mSampleRate;
    }

    public float[] parseFrequency(byte abyte0[], int i, boolean flag)
        throws IOException
    {
        if(abyte0 == null)
        {
            Log.i("DTMFUtil", "parameter error: null");
            return null;
        } else
        {
            return dtmfDetectAndValidate(abyte0, i, flag);
        }
    }

    public void setHealthy(int i)
    {
        mHealthy = i;
    }

    public void setSampleRate(int i)
    {
        mSampleRate = i;
        initPrecalculatedCosines();
    }

    private static final int DECIBEL_THRESHOLD_LIST[] = {
        23, 32, 31, 34, 37
    };
    private static final int DEFAULT_SAMPLE_RATE = 8000;
    private static final int FREQUENCE_INDEX_GAP = 5;
    private static final int HEALTHY = 3;
    private static final String LOG_TAG = "DTMFUtil";
    private static final int SAMPLE_RATE_LIST[] = {
        8000, 11025, 22050, 32000, 44100
    };
    private static final int STEP = 256;
    private static final float TARGET_FREQUENCIES[] = {
        600F, 697F, 770F, 852F, 941F, 1075F, 1209F, 1336F, 1477F, 1633F
    };
    private static final float TARGET_HIGH_FREQUENCY = 1075F;
    private static final float TARGET_LOW_FREQUENCY = 600F;
    private static final int VERSION = 2;
    private int mHealthy;
    private float mPreCalculatedCosines[];
    private int mSampleRate;
    private float mWnkList[];

}
