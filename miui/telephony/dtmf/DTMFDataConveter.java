// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.telephony.dtmf;

import android.util.Log;

public class DTMFDataConveter
{

    public DTMFDataConveter()
    {
        this(8, true);
    }

    public DTMFDataConveter(int i, boolean flag)
    {
        setBitPerSample(i);
        setSign(flag);
    }

    public float[] byteToFloat(byte abyte0[])
    {
        if(abyte0 == null)
        {
            Log.i("DTMFDataConveter", "bit mode can not match");
            return null;
        }
        float af[] = new float[abyte0.length / (getBitPerSample() / 8)];
        int i = 0;
        while(i < af.length) 
        {
            if(getBitPerSample() == 8)
            {
                if(mSign)
                    af[i] = (float)abyte0[i] / 127F;
                else
                    af[i] = (float)((abyte0[i] & 0xff) - 127) * 0.007874016F;
            } else
            if(getBitPerSample() == 16)
                if(mSign)
                    af[i] = (float)(short)(abyte0[i * 2] & 0xff | abyte0[i * 2 + 1] << 8) * 3.051851E-005F;
                else
                    af[i] = (float)((abyte0[i * 2] & 0xff | (abyte0[i * 2 + 1] & 0xff) << 8) - 32767) * 3.051851E-005F;
            i++;
        }
        return af;
    }

    public byte[] floatToByte(float af[])
    {
        if(af == null)
        {
            Log.i("DTMFDataConveter", "bit mode can not match");
            return null;
        }
        byte abyte0[] = new byte[af.length * (getBitPerSample() / 8)];
        int i = 0;
        while(i < af.length) 
        {
            if(getBitPerSample() == 8)
            {
                if(mSign)
                    abyte0[i] = (byte)(int)(af[i] * 127F);
                else
                    abyte0[i] = (byte)(int)(af[i] * 127F + 127F);
            } else
            if(getBitPerSample() == 16)
                if(mSign)
                {
                    int j = (int)((double)af[i] * 32767D);
                    abyte0[i * 2] = (byte)j;
                    abyte0[i * 2 + 1] = (byte)(j >>> 8);
                } else
                {
                    int k = (int)((double)af[i] * 32767D) + 32767;
                    abyte0[i * 2] = (byte)k;
                    abyte0[i * 2 + 1] = (byte)(k >>> 8);
                }
            i++;
        }
        return abyte0;
    }

    public int getBitPerSample()
    {
        return mBitPerSample;
    }

    public boolean getSign()
    {
        return mSign;
    }

    public void setBitPerSample(int i)
    {
        mBitPerSample = i;
    }

    public void setSign(boolean flag)
    {
        mSign = flag;
    }

    private static final String LOG_TAG = "DTMFDataConveter";
    private int mBitPerSample;
    private boolean mSign;
}
