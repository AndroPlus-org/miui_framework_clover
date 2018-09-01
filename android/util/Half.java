// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import sun.misc.FloatingDecimal;

public final class Half extends Number
    implements Comparable
{

    public Half(double d)
    {
        mValue = toHalf((float)d);
    }

    public Half(float f)
    {
        mValue = toHalf(f);
    }

    public Half(String s)
        throws NumberFormatException
    {
        mValue = toHalf(Float.parseFloat(s));
    }

    public Half(short word0)
    {
        mValue = word0;
    }

    public static short abs(short word0)
    {
        return (short)(word0 & 0x7fff);
    }

    public static short ceil(short word0)
    {
        boolean flag;
        int i;
        int j;
        flag = true;
        i = word0 & 0xffff;
        j = i & 0x7fff;
        word0 = i;
        if(j >= 15360) goto _L2; else goto _L1
_L1:
        if(j != 0)
            word0 = flag;
        else
            word0 = 0;
        word0 = i & 0x8000 | -(word0 & i >> 15) & 0x3c00;
_L4:
        return (short)word0;
_L2:
        if(j < 25600)
        {
            word0 = (1 << 25 - (j >> 10)) - 1;
            word0 = i + ((i >> 15) - 1 & word0) & word0;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static int compare(short word0, short word1)
    {
        byte byte0;
        short word2;
        short word3;
        byte0 = -1;
        if(less(word0, word1))
            return -1;
        if(greater(word0, word1))
            return 1;
        if((word0 & 0x7fff) > 31744)
            word2 = 32256;
        else
            word2 = word0;
        if((word1 & 0x7fff) > 31744)
            word3 = 32256;
        else
            word3 = word1;
        if(word2 != word3) goto _L2; else goto _L1
_L1:
        byte0 = 0;
_L4:
        return byte0;
_L2:
        if(word2 >= word3)
            byte0 = 1;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static short copySign(short word0, short word1)
    {
        return (short)(0x8000 & word1 | word0 & 0x7fff);
    }

    public static boolean equals(short word0, short word1)
    {
        boolean flag = true;
        if((word0 & 0x7fff) > 31744)
            return false;
        if((word1 & 0x7fff) > 31744)
            return false;
        boolean flag1 = flag;
        if(word0 != word1)
            if(((word0 | word1) & 0x7fff) == 0)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public static short floor(short word0)
    {
        int i;
        int j;
        int k;
        i = 65535;
        j = word0 & 0xffff;
        k = j & 0x7fff;
        word0 = j;
        if(k >= 15360) goto _L2; else goto _L1
_L1:
        if(j > 32768)
            word0 = i;
        else
            word0 = 0;
        word0 = j & 0x8000 | word0 & 0x3c00;
_L4:
        return (short)word0;
_L2:
        if(k < 25600)
        {
            word0 = (1 << 25 - (k >> 10)) - 1;
            word0 = j + (-(j >> 15) & word0) & word0;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static int getExponent(short word0)
    {
        return (word0 >>> 10 & 0x1f) - 15;
    }

    public static int getSign(short word0)
    {
        if((0x8000 & word0) == 0)
            word0 = 1;
        else
            word0 = -1;
        return word0;
    }

    public static int getSignificand(short word0)
    {
        return word0 & 0x3ff;
    }

    public static boolean greater(short word0, short word1)
    {
        boolean flag = false;
        if((word0 & 0x7fff) > 31744)
            return false;
        if((word1 & 0x7fff) > 31744)
            return false;
        if((word0 & 0x8000) != 0)
            word0 = 32768 - (word0 & 0xffff);
        else
            word0 &= 0xffff;
        if((word1 & 0x8000) != 0)
            word1 = 32768 - (word1 & 0xffff);
        else
            word1 &= 0xffff;
        if(word0 > word1)
            flag = true;
        return flag;
    }

    public static boolean greaterEquals(short word0, short word1)
    {
        boolean flag = false;
        if((word0 & 0x7fff) > 31744)
            return false;
        if((word1 & 0x7fff) > 31744)
            return false;
        if((word0 & 0x8000) != 0)
            word0 = 32768 - (word0 & 0xffff);
        else
            word0 &= 0xffff;
        if((word1 & 0x8000) != 0)
            word1 = 32768 - (word1 & 0xffff);
        else
            word1 &= 0xffff;
        if(word0 >= word1)
            flag = true;
        return flag;
    }

    public static int halfToIntBits(short word0)
    {
        if((word0 & 0x7fff) > 31744)
            word0 = 32256;
        else
            word0 = 0xffff & word0;
        return word0;
    }

    public static int halfToRawIntBits(short word0)
    {
        return 0xffff & word0;
    }

    public static short halfToShortBits(short word0)
    {
        short word1 = word0;
        if((word0 & 0x7fff) > 31744)
        {
            word0 = 32256;
            word1 = word0;
        }
        return word1;
    }

    public static int hashCode(short word0)
    {
        return halfToIntBits(word0);
    }

    public static short intBitsToHalf(int i)
    {
        return (short)(0xffff & i);
    }

    public static boolean isInfinite(short word0)
    {
        boolean flag;
        if((word0 & 0x7fff) == 31744)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean isNaN(short word0)
    {
        boolean flag;
        if((word0 & 0x7fff) > 31744)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean isNormalized(short word0)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if((word0 & 0x7c00) != 0)
        {
            flag1 = flag;
            if((word0 & 0x7c00) != 31744)
                flag1 = true;
        }
        return flag1;
    }

    public static boolean less(short word0, short word1)
    {
        boolean flag = false;
        if((word0 & 0x7fff) > 31744)
            return false;
        if((word1 & 0x7fff) > 31744)
            return false;
        if((word0 & 0x8000) != 0)
            word0 = 32768 - (word0 & 0xffff);
        else
            word0 &= 0xffff;
        if((word1 & 0x8000) != 0)
            word1 = 32768 - (word1 & 0xffff);
        else
            word1 &= 0xffff;
        if(word0 < word1)
            flag = true;
        return flag;
    }

    public static boolean lessEquals(short word0, short word1)
    {
        boolean flag = false;
        if((word0 & 0x7fff) > 31744)
            return false;
        if((word1 & 0x7fff) > 31744)
            return false;
        if((word0 & 0x8000) != 0)
            word0 = 32768 - (word0 & 0xffff);
        else
            word0 &= 0xffff;
        if((word1 & 0x8000) != 0)
            word1 = 32768 - (word1 & 0xffff);
        else
            word1 &= 0xffff;
        if(word0 <= word1)
            flag = true;
        return flag;
    }

    public static short max(short word0, short word1)
    {
        if((word0 & 0x7fff) > 31744)
            return 32256;
        if((word1 & 0x7fff) > 31744)
            return 32256;
        if((word0 & 0x7fff) == 0 && (word1 & 0x7fff) == 0)
        {
            short word2;
            if((word0 & 0x8000) != 0)
                word2 = word1;
            else
                word2 = word0;
            return word2;
        }
        short word3;
        int i;
        int j;
        if((word0 & 0x8000) != 0)
            i = 32768 - (word0 & 0xffff);
        else
            i = word0 & 0xffff;
        if((word1 & 0x8000) != 0)
            j = 32768 - (word1 & 0xffff);
        else
            j = word1 & 0xffff;
        if(i > j)
            word3 = word0;
        else
            word3 = word1;
        return word3;
    }

    public static short min(short word0, short word1)
    {
        if((word0 & 0x7fff) > 31744)
            return 32256;
        if((word1 & 0x7fff) > 31744)
            return 32256;
        if((word0 & 0x7fff) == 0 && (word1 & 0x7fff) == 0)
        {
            short word2;
            if((word0 & 0x8000) != 0)
                word2 = word0;
            else
                word2 = word1;
            return word2;
        }
        short word3;
        int i;
        int j;
        if((word0 & 0x8000) != 0)
            i = 32768 - (word0 & 0xffff);
        else
            i = word0 & 0xffff;
        if((word1 & 0x8000) != 0)
            j = 32768 - (word1 & 0xffff);
        else
            j = word1 & 0xffff;
        if(i < j)
            word3 = word0;
        else
            word3 = word1;
        return word3;
    }

    public static short parseHalf(String s)
        throws NumberFormatException
    {
        return toHalf(FloatingDecimal.parseFloat(s));
    }

    public static short round(short word0)
    {
        int i;
        int j;
        int k;
        i = 65535;
        j = word0 & 0xffff;
        k = j & 0x7fff;
        word0 = j;
        if(k >= 15360) goto _L2; else goto _L1
_L1:
        if(k >= 14336)
            word0 = i;
        else
            word0 = 0;
        word0 = j & 0x8000 | word0 & 0x3c00;
_L4:
        return (short)word0;
_L2:
        if(k < 25600)
        {
            word0 = 25 - (k >> 10);
            word0 = j + (1 << word0 - 1) & (1 << word0) - 1;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static float toFloat(short word0)
    {
        word0 &= 0xffff;
        int i = word0 & 0x8000;
        int j = word0 >>> 10 & 0x1f;
        int k = word0 & 0x3ff;
        word0 = 0;
        int l = 0;
        if(j == 0)
        {
            if(k != 0)
            {
                float f = Float.intBitsToFloat(0x3f000000 + k) - FP32_DENORMAL_FLOAT;
                if(i != 0)
                    f = -f;
                return f;
            }
        } else
        {
            l = k << 13;
            if(j == 31)
                word0 = 255;
            else
                word0 = (j - 15) + 127;
        }
        return Float.intBitsToFloat(i << 16 | word0 << 23 | l);
    }

    public static short toHalf(float f)
    {
        int i;
        int j;
        int k;
        int l;
        boolean flag;
        i = Float.floatToRawIntBits(f);
        j = i >>> 31;
        k = i >>> 23 & 0xff;
        l = i & 0x7fffff;
        flag = false;
        i = 0;
        if(k != 255) goto _L2; else goto _L1
_L1:
        k = 31;
        if(l != 0)
            i = 512;
        else
            i = 0;
_L4:
        return (short)(j << 15 | k << 10 | i);
_L2:
        int j1;
        j1 = (k - 127) + 15;
        if(j1 >= 31)
        {
            k = 49;
            continue; /* Loop/switch isn't completed */
        }
        if(j1 > 0)
            break; /* Loop/switch isn't completed */
        k = ((flag) ? 1 : 0);
        if(j1 >= -10)
        {
            k = (0x800000 | l) >> 1 - j1;
            i = k;
            if((k & 0x1000) != 0)
                i = k + 8192;
            i >>= 13;
            k = ((flag) ? 1 : 0);
        }
        if(true) goto _L4; else goto _L3
_L3:
        k = j1;
        int i1 = l >> 13;
        i = i1;
        if((l & 0x1000) != 0)
            return (short)(j << 15 | (j1 << 10 | i1) + 1);
        if(true) goto _L4; else goto _L5
_L5:
    }

    public static String toHexString(short word0)
    {
        StringBuilder stringbuilder = new StringBuilder();
        int i = word0 & 0xffff;
        word0 = i >>> 15;
        int j = i >>> 10 & 0x1f;
        i &= 0x3ff;
        if(j == 31)
        {
            if(i == 0)
            {
                if(word0 != 0)
                    stringbuilder.append('-');
                stringbuilder.append("Infinity");
            } else
            {
                stringbuilder.append("NaN");
            }
        } else
        {
            if(word0 == 1)
                stringbuilder.append('-');
            if(j == 0)
            {
                if(i == 0)
                {
                    stringbuilder.append("0x0.0p0");
                } else
                {
                    stringbuilder.append("0x0.");
                    stringbuilder.append(Integer.toHexString(i).replaceFirst("0{2,}$", ""));
                    stringbuilder.append("p-14");
                }
            } else
            {
                stringbuilder.append("0x1.");
                stringbuilder.append(Integer.toHexString(i).replaceFirst("0{2,}$", ""));
                stringbuilder.append('p');
                stringbuilder.append(Integer.toString(j - 15));
            }
        }
        return stringbuilder.toString();
    }

    public static String toString(short word0)
    {
        return Float.toString(toFloat(word0));
    }

    public static short trunc(short word0)
    {
        int i;
        int j;
        i = word0 & 0xffff;
        j = i & 0x7fff;
        word0 = i;
        if(j >= 15360) goto _L2; else goto _L1
_L1:
        word0 = i & 0x8000;
_L4:
        return (short)word0;
_L2:
        if(j < 25600)
            word0 = i & (1 << 25 - (j >> 10)) - 1;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static Half valueOf(float f)
    {
        return new Half(f);
    }

    public static Half valueOf(String s)
    {
        return new Half(s);
    }

    public static Half valueOf(short word0)
    {
        return new Half(word0);
    }

    public byte byteValue()
    {
        return (byte)(int)toFloat(mValue);
    }

    public int compareTo(Half half)
    {
        return compare(mValue, half.mValue);
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((Half)obj);
    }

    public double doubleValue()
    {
        return (double)toFloat(mValue);
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(obj instanceof Half)
        {
            flag1 = flag;
            if(halfToIntBits(((Half)obj).mValue) == halfToIntBits(mValue))
                flag1 = true;
        }
        return flag1;
    }

    public float floatValue()
    {
        return toFloat(mValue);
    }

    public short halfValue()
    {
        return mValue;
    }

    public int hashCode()
    {
        return hashCode(mValue);
    }

    public int intValue()
    {
        return (int)toFloat(mValue);
    }

    public boolean isNaN()
    {
        return isNaN(mValue);
    }

    public long longValue()
    {
        return (long)toFloat(mValue);
    }

    public short shortValue()
    {
        return (short)(int)toFloat(mValue);
    }

    public String toString()
    {
        return toString(mValue);
    }

    public static final short EPSILON = 5120;
    private static final int FP16_COMBINED = 32767;
    private static final int FP16_EXPONENT_BIAS = 15;
    private static final int FP16_EXPONENT_MASK = 31;
    private static final int FP16_EXPONENT_MAX = 31744;
    private static final int FP16_EXPONENT_SHIFT = 10;
    private static final int FP16_SIGNIFICAND_MASK = 1023;
    private static final int FP16_SIGN_MASK = 32768;
    private static final int FP16_SIGN_SHIFT = 15;
    private static final float FP32_DENORMAL_FLOAT = Float.intBitsToFloat(0x3f000000);
    private static final int FP32_DENORMAL_MAGIC = 0x3f000000;
    private static final int FP32_EXPONENT_BIAS = 127;
    private static final int FP32_EXPONENT_MASK = 255;
    private static final int FP32_EXPONENT_SHIFT = 23;
    private static final int FP32_SIGNIFICAND_MASK = 0x7fffff;
    private static final int FP32_SIGN_SHIFT = 31;
    public static final short LOWEST_VALUE = -1025;
    public static final int MAX_EXPONENT = 15;
    public static final short MAX_VALUE = 31743;
    public static final int MIN_EXPONENT = -14;
    public static final short MIN_NORMAL = 1024;
    public static final short MIN_VALUE = 1;
    public static final short NEGATIVE_INFINITY = -1024;
    public static final short NEGATIVE_ZERO = -32768;
    public static final short NaN = 32256;
    public static final short POSITIVE_INFINITY = 31744;
    public static final short POSITIVE_ZERO = 0;
    public static final int SIZE = 16;
    private final short mValue;

}
