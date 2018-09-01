// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.graphics;

import android.graphics.Bitmap;

public class BitmapUtil
{

    public BitmapUtil()
    {
    }

    public static byte[] getBuffer(Bitmap bitmap)
    {
        if(android.os.Build.VERSION.SDK_INT >= 26)
        {
            int i = bitmap.getWidth();
            int j = bitmap.getHeight();
            int k = bitmap.getByteCount() / 4;
            int l = bitmap.getRowBytes() / 4;
            int ai[] = new int[k];
            bitmap.getPixels(ai, 0, l, 0, 0, i, j);
            byte abyte0[] = new byte[bitmap.getByteCount()];
            l = 0;
            do
            {
                bitmap = abyte0;
                if(l >= ai.length)
                    break;
                abyte0[l * 4 + 3] = (byte)(ai[l] >> 24 & 0xff);
                abyte0[l * 4] = (byte)(ai[l] >> 16 & 0xff);
                abyte0[l * 4 + 1] = (byte)(ai[l] >> 8 & 0xff);
                abyte0[l * 4 + 2] = (byte)(ai[l] & 0xff);
                l++;
            } while(true);
        } else
        {
            bitmap = bitmap.mBuffer;
        }
        return bitmap;
    }

    private static final int COLOR_BYTE_SIZE = 4;
}
