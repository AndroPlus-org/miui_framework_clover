// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;


public class ImageFormat
{

    public ImageFormat()
    {
    }

    public static int getBitsPerPixel(int i)
    {
        switch(i)
        {
        default:
            return -1;

        case 4: // '\004'
            return 16;

        case 16: // '\020'
            return 16;

        case 20: // '\024'
            return 16;

        case 842094169: 
            return 12;

        case 538982489: 
            return 8;

        case 540422489: 
        case 1144402265: 
            return 16;

        case 17: // '\021'
            return 12;

        case 35: // '#'
            return 12;

        case 39: // '\''
            return 16;

        case 40: // '('
            return 24;

        case 41: // ')'
            return 24;

        case 42: // '*'
            return 32;

        case 32: // ' '
        case 4098: 
            return 16;

        case 37: // '%'
            return 10;

        case 38: // '&'
            return 12;
        }
    }

    public static boolean isPublicFormat(int i)
    {
        switch(i)
        {
        default:
            return false;

        case 4: // '\004'
        case 16: // '\020'
        case 17: // '\021'
        case 20: // '\024'
        case 32: // ' '
        case 34: // '"'
        case 35: // '#'
        case 36: // '$'
        case 37: // '%'
        case 38: // '&'
        case 39: // '\''
        case 40: // '('
        case 41: // ')'
        case 42: // '*'
        case 256: 
        case 257: 
        case 4098: 
        case 842094169: 
        case 1144402265: 
            return true;
        }
    }

    public static final int DEPTH16 = 0x44363159;
    public static final int DEPTH_POINT_CLOUD = 257;
    public static final int FLEX_RGBA_8888 = 42;
    public static final int FLEX_RGB_888 = 41;
    public static final int JPEG = 256;
    public static final int NV16 = 16;
    public static final int NV21 = 17;
    public static final int PRIVATE = 34;
    public static final int RAW10 = 37;
    public static final int RAW12 = 38;
    public static final int RAW_DEPTH = 4098;
    public static final int RAW_PRIVATE = 36;
    public static final int RAW_SENSOR = 32;
    public static final int RGB_565 = 4;
    public static final int UNKNOWN = 0;
    public static final int Y16 = 0x20363159;
    public static final int Y8 = 0x20203859;
    public static final int YUV_420_888 = 35;
    public static final int YUV_422_888 = 39;
    public static final int YUV_444_888 = 40;
    public static final int YUY2 = 20;
    public static final int YV12 = 0x32315659;
}
