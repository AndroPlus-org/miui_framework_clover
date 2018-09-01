// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;


public class PixelFormat
{

    public PixelFormat()
    {
    }

    public static boolean formatHasAlpha(int i)
    {
        switch(i)
        {
        default:
            return false;

        case -3: 
        case -2: 
        case 1: // '\001'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        case 10: // '\n'
        case 22: // '\026'
        case 43: // '+'
            return true;
        }
    }

    public static void getPixelFormatInfo(int i, PixelFormat pixelformat)
    {
        i;
        JVM INSTR lookupswitch 15: default 132
    //                   1: 159
    //                   2: 159
    //                   3: 171
    //                   4: 185
    //                   6: 185
    //                   7: 185
    //                   8: 199
    //                   9: 199
    //                   10: 185
    //                   11: 199
    //                   16: 213
    //                   17: 227
    //                   20: 213
    //                   22: 241
    //                   43: 159;
           goto _L1 _L2 _L2 _L3 _L4 _L4 _L4 _L5 _L5 _L4 _L5 _L6 _L7 _L6 _L8 _L2
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append("unknown pixel format ").append(i).toString());
_L2:
        pixelformat.bitsPerPixel = 32;
        pixelformat.bytesPerPixel = 4;
_L10:
        return;
_L3:
        pixelformat.bitsPerPixel = 24;
        pixelformat.bytesPerPixel = 3;
        continue; /* Loop/switch isn't completed */
_L4:
        pixelformat.bitsPerPixel = 16;
        pixelformat.bytesPerPixel = 2;
        continue; /* Loop/switch isn't completed */
_L5:
        pixelformat.bitsPerPixel = 8;
        pixelformat.bytesPerPixel = 1;
        continue; /* Loop/switch isn't completed */
_L6:
        pixelformat.bitsPerPixel = 16;
        pixelformat.bytesPerPixel = 1;
        continue; /* Loop/switch isn't completed */
_L7:
        pixelformat.bitsPerPixel = 12;
        pixelformat.bytesPerPixel = 1;
        continue; /* Loop/switch isn't completed */
_L8:
        pixelformat.bitsPerPixel = 64;
        pixelformat.bytesPerPixel = 8;
        if(true) goto _L10; else goto _L9
_L9:
    }

    public static boolean isPublicFormat(int i)
    {
        switch(i)
        {
        default:
            return false;

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 22: // '\026'
        case 43: // '+'
            return true;
        }
    }

    public static final int A_8 = 8;
    public static final int JPEG = 256;
    public static final int LA_88 = 10;
    public static final int L_8 = 9;
    public static final int OPAQUE = -1;
    public static final int RGBA_1010102 = 43;
    public static final int RGBA_4444 = 7;
    public static final int RGBA_5551 = 6;
    public static final int RGBA_8888 = 1;
    public static final int RGBA_F16 = 22;
    public static final int RGBX_8888 = 2;
    public static final int RGB_332 = 11;
    public static final int RGB_565 = 4;
    public static final int RGB_888 = 3;
    public static final int TRANSLUCENT = -3;
    public static final int TRANSPARENT = -2;
    public static final int UNKNOWN = 0;
    public static final int YCbCr_420_SP = 17;
    public static final int YCbCr_422_I = 20;
    public static final int YCbCr_422_SP = 16;
    public int bitsPerPixel;
    public int bytesPerPixel;
}
