// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.telephony.dtmf;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;

public class DTMFAudioInputStream extends InputStream
{

    public DTMFAudioInputStream(byte abyte0[])
    {
        if(abyte0 == null)
        {
            Log.i("DTMFAudioInputStream", "parameter error");
            return;
        }
        mByteBuff = new byte[abyte0.length];
        for(int i = 0; i < abyte0.length; i++)
            mByteBuff[i] = abyte0[i];

        mReadedCnt = 0;
    }

    public int read()
        throws IOException
    {
        if(mReadedCnt >= mByteBuff.length)
        {
            return -1;
        } else
        {
            byte abyte0[] = mByteBuff;
            int i = mReadedCnt;
            mReadedCnt = i + 1;
            return abyte0[i];
        }
    }

    public int read(byte abyte0[], int i)
        throws IOException
    {
        if(mByteBuff == null || i <= 0)
        {
            Log.i("DTMFAudioInputStream", "paramenter error:fail to get subdatalist");
            return -1;
        }
        int j = i;
        if(mByteBuff.length - mReadedCnt < i)
            j = mByteBuff.length - mReadedCnt;
        for(int k = 0; k < j; k++)
            abyte0[k] = (byte)read();

        return i;
    }

    private static final String LOG_TAG = "DTMFAudioInputStream";
    private byte mByteBuff[];
    private int mReadedCnt;
}
