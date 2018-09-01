// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;


// Referenced classes of package android.util:
//            DisplayMetrics, TypedValueInjector

public class TypedValue
{

    public TypedValue()
    {
        changingConfigurations = -1;
    }

    public static float applyDimension(int i, float f, DisplayMetrics displaymetrics)
    {
        switch(i)
        {
        default:
            return 0.0F;

        case 0: // '\0'
            return f;

        case 1: // '\001'
            return displaymetrics.density * f;

        case 2: // '\002'
            return TypedValueInjector.miuiScale(f, displaymetrics);

        case 3: // '\003'
            return displaymetrics.xdpi * f * 0.01388889F;

        case 4: // '\004'
            return displaymetrics.xdpi * f;

        case 5: // '\005'
            return displaymetrics.xdpi * f * 0.03937008F;
        }
    }

    public static final String coerceToString(int i, int j)
    {
        switch(i)
        {
        case 3: // '\003'
        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
        case 10: // '\n'
        case 11: // '\013'
        case 12: // '\f'
        case 13: // '\r'
        case 14: // '\016'
        case 15: // '\017'
        case 16: // '\020'
        default:
            if(i >= 28 && i <= 31)
                return (new StringBuilder()).append("#").append(Integer.toHexString(j)).toString();
            break;

        case 0: // '\0'
            return null;

        case 1: // '\001'
            return (new StringBuilder()).append("@").append(j).toString();

        case 2: // '\002'
            return (new StringBuilder()).append("?").append(j).toString();

        case 4: // '\004'
            return Float.toString(Float.intBitsToFloat(j));

        case 5: // '\005'
            return (new StringBuilder()).append(Float.toString(complexToFloat(j))).append(DIMENSION_UNIT_STRS[j >> 0 & 0xf]).toString();

        case 6: // '\006'
            return (new StringBuilder()).append(Float.toString(complexToFloat(j) * 100F)).append(FRACTION_UNIT_STRS[j >> 0 & 0xf]).toString();

        case 17: // '\021'
            return (new StringBuilder()).append("0x").append(Integer.toHexString(j)).toString();

        case 18: // '\022'
            String s;
            if(j != 0)
                s = "true";
            else
                s = "false";
            return s;
        }
        if(i >= 16 && i <= 31)
            return Integer.toString(j);
        else
            return null;
    }

    public static float complexToDimension(int i, DisplayMetrics displaymetrics)
    {
        return applyDimension(i >> 0 & 0xf, complexToFloat(i), displaymetrics);
    }

    public static float complexToDimensionNoisy(int i, DisplayMetrics displaymetrics)
    {
        return complexToDimension(i, displaymetrics);
    }

    public static int complexToDimensionPixelOffset(int i, DisplayMetrics displaymetrics)
    {
        return (int)applyDimension(i >> 0 & 0xf, complexToFloat(i), displaymetrics);
    }

    public static int complexToDimensionPixelSize(int i, DisplayMetrics displaymetrics)
    {
        float f = complexToFloat(i);
        float f1 = applyDimension(i >> 0 & 0xf, f, displaymetrics);
        if(f1 >= 0.0F)
            f1 += 0.5F;
        else
            f1 -= 0.5F;
        i = (int)f1;
        if(i != 0)
            return i;
        if(f == 0.0F)
            return 0;
        return f <= 0.0F ? -1 : 1;
    }

    public static float complexToFloat(int i)
    {
        return (float)(i & 0xffffff00) * RADIX_MULTS[i >> 4 & 3];
    }

    public static float complexToFraction(int i, float f, float f1)
    {
        switch(i >> 0 & 0xf)
        {
        default:
            return 0.0F;

        case 0: // '\0'
            return complexToFloat(i) * f;

        case 1: // '\001'
            return complexToFloat(i) * f1;
        }
    }

    public final CharSequence coerceToString()
    {
        int i = type;
        if(i == 3)
            return string;
        else
            return coerceToString(i, data);
    }

    public int getComplexUnit()
    {
        return data >> 0 & 0xf;
    }

    public float getDimension(DisplayMetrics displaymetrics)
    {
        return complexToDimension(data, displaymetrics);
    }

    public final float getFloat()
    {
        return Float.intBitsToFloat(data);
    }

    public float getFraction(float f, float f1)
    {
        return complexToFraction(data, f, f1);
    }

    public void setTo(TypedValue typedvalue)
    {
        type = typedvalue.type;
        string = typedvalue.string;
        data = typedvalue.data;
        assetCookie = typedvalue.assetCookie;
        resourceId = typedvalue.resourceId;
        density = typedvalue.density;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("TypedValue{t=0x").append(Integer.toHexString(type));
        stringbuilder.append("/d=0x").append(Integer.toHexString(data));
        if(type == 3)
        {
            StringBuilder stringbuilder1 = stringbuilder.append(" \"");
            Object obj;
            if(string != null)
                obj = string;
            else
                obj = "<null>";
            stringbuilder1.append(((CharSequence) (obj))).append("\"");
        }
        if(assetCookie != 0)
            stringbuilder.append(" a=").append(assetCookie);
        if(resourceId != 0)
            stringbuilder.append(" r=0x").append(Integer.toHexString(resourceId));
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    public static final int COMPLEX_MANTISSA_MASK = 0xffffff;
    public static final int COMPLEX_MANTISSA_SHIFT = 8;
    public static final int COMPLEX_RADIX_0p23 = 3;
    public static final int COMPLEX_RADIX_16p7 = 1;
    public static final int COMPLEX_RADIX_23p0 = 0;
    public static final int COMPLEX_RADIX_8p15 = 2;
    public static final int COMPLEX_RADIX_MASK = 3;
    public static final int COMPLEX_RADIX_SHIFT = 4;
    public static final int COMPLEX_UNIT_DIP = 1;
    public static final int COMPLEX_UNIT_FRACTION = 0;
    public static final int COMPLEX_UNIT_FRACTION_PARENT = 1;
    public static final int COMPLEX_UNIT_IN = 4;
    public static final int COMPLEX_UNIT_MASK = 15;
    public static final int COMPLEX_UNIT_MM = 5;
    public static final int COMPLEX_UNIT_PT = 3;
    public static final int COMPLEX_UNIT_PX = 0;
    public static final int COMPLEX_UNIT_SHIFT = 0;
    public static final int COMPLEX_UNIT_SP = 2;
    public static final int DATA_NULL_EMPTY = 1;
    public static final int DATA_NULL_UNDEFINED = 0;
    public static final int DENSITY_DEFAULT = 0;
    public static final int DENSITY_NONE = 65535;
    private static final String DIMENSION_UNIT_STRS[] = {
        "px", "dip", "sp", "pt", "in", "mm"
    };
    private static final String FRACTION_UNIT_STRS[] = {
        "%", "%p"
    };
    private static final float MANTISSA_MULT = 0.00390625F;
    private static final float RADIX_MULTS[] = {
        0.00390625F, 3.051758E-005F, 1.192093E-007F, 4.656613E-010F
    };
    public static final int TYPE_ATTRIBUTE = 2;
    public static final int TYPE_DIMENSION = 5;
    public static final int TYPE_FIRST_COLOR_INT = 28;
    public static final int TYPE_FIRST_INT = 16;
    public static final int TYPE_FLOAT = 4;
    public static final int TYPE_FRACTION = 6;
    public static final int TYPE_INT_BOOLEAN = 18;
    public static final int TYPE_INT_COLOR_ARGB4 = 30;
    public static final int TYPE_INT_COLOR_ARGB8 = 28;
    public static final int TYPE_INT_COLOR_RGB4 = 31;
    public static final int TYPE_INT_COLOR_RGB8 = 29;
    public static final int TYPE_INT_DEC = 16;
    public static final int TYPE_INT_HEX = 17;
    public static final int TYPE_LAST_COLOR_INT = 31;
    public static final int TYPE_LAST_INT = 31;
    public static final int TYPE_NULL = 0;
    public static final int TYPE_REFERENCE = 1;
    public static final int TYPE_STRING = 3;
    public int assetCookie;
    public int changingConfigurations;
    public int data;
    public int density;
    public int resourceId;
    public CharSequence string;
    public int type;

}
