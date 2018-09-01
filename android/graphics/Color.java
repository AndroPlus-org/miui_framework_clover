// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;

import android.util.Half;
import com.android.internal.util.XmlUtils;
import java.util.*;
import java.util.function.DoubleUnaryOperator;

// Referenced classes of package android.graphics:
//            ColorSpace

public class Color
{

    public Color()
    {
        mColorSpace = ColorSpace.get(ColorSpace.Named.SRGB);
    }

    private Color(float f, float f1, float f2, float f3)
    {
        this(f, f1, f2, f3, ColorSpace.get(ColorSpace.Named.SRGB));
    }

    private Color(float f, float f1, float f2, float f3, ColorSpace colorspace)
    {
        mColorSpace = colorspace;
    }

    private Color(float af[], ColorSpace colorspace)
    {
        mComponents = af;
        mColorSpace = colorspace;
    }

    public static int HSVToColor(int i, float af[])
    {
        if(af.length < 3)
            throw new RuntimeException("3 components required for hsv");
        else
            return nativeHSVToColor(i, af);
    }

    public static int HSVToColor(float af[])
    {
        return HSVToColor(255, af);
    }

    public static void RGBToHSV(int i, int j, int k, float af[])
    {
        if(af.length < 3)
        {
            throw new RuntimeException("3 components required for hsv");
        } else
        {
            nativeRGBToHSV(i, j, k, af);
            return;
        }
    }

    public static float alpha(long l)
    {
        if((63L & l) == 0L)
            return (float)(l >> 56 & 255L) / 255F;
        else
            return (float)(l >> 6 & 1023L) / 1023F;
    }

    public static int alpha(int i)
    {
        return i >>> 24;
    }

    public static int argb(float f, float f1, float f2, float f3)
    {
        return (int)(f * 255F + 0.5F) << 24 | (int)(f1 * 255F + 0.5F) << 16 | (int)(f2 * 255F + 0.5F) << 8 | (int)(f3 * 255F + 0.5F);
    }

    public static int argb(int i, int j, int k, int l)
    {
        return i << 24 | j << 16 | k << 8 | l;
    }

    public static float blue(long l)
    {
        if((63L & l) == 0L)
            return (float)(l >> 32 & 255L) / 255F;
        else
            return Half.toFloat((short)(int)(l >> 16 & 65535L));
    }

    public static int blue(int i)
    {
        return i & 0xff;
    }

    public static ColorSpace colorSpace(long l)
    {
        return ColorSpace.get((int)(63L & l));
    }

    public static void colorToHSV(int i, float af[])
    {
        RGBToHSV(i >> 16 & 0xff, i >> 8 & 0xff, i & 0xff, af);
    }

    public static long convert(float f, float f1, float f2, float f3, ColorSpace.Connector connector)
    {
        float af[] = connector.transform(f, f1, f2);
        return pack(af[0], af[1], af[2], f3, connector.getDestination());
    }

    public static long convert(float f, float f1, float f2, float f3, ColorSpace colorspace, ColorSpace colorspace1)
    {
        colorspace = ColorSpace.connect(colorspace, colorspace1).transform(f, f1, f2);
        return pack(colorspace[0], colorspace[1], colorspace[2], f3, colorspace1);
    }

    public static long convert(int i, ColorSpace colorspace)
    {
        return convert((float)(i >> 16 & 0xff) / 255F, (float)(i >> 8 & 0xff) / 255F, (float)(i & 0xff) / 255F, (float)(i >> 24 & 0xff) / 255F, ColorSpace.get(ColorSpace.Named.SRGB), colorspace);
    }

    public static long convert(long l, ColorSpace.Connector connector)
    {
        return convert(red(l), green(l), blue(l), alpha(l), connector);
    }

    public static long convert(long l, ColorSpace colorspace)
    {
        return convert(red(l), green(l), blue(l), alpha(l), colorSpace(l), colorspace);
    }

    public static int getHtmlColor(String s)
    {
        Integer integer = (Integer)sColorNameMap.get(s.toLowerCase(Locale.ROOT));
        if(integer != null)
            return integer.intValue();
        int i;
        try
        {
            i = XmlUtils.convertValueToInt(s, -1);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return -1;
        }
        return i;
    }

    public static float green(long l)
    {
        if((63L & l) == 0L)
            return (float)(l >> 40 & 255L) / 255F;
        else
            return Half.toFloat((short)(int)(l >> 32 & 65535L));
    }

    public static int green(int i)
    {
        return i >> 8 & 0xff;
    }

    public static boolean isInColorSpace(long l, ColorSpace colorspace)
    {
        boolean flag;
        if((int)(63L & l) == colorspace.getId())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean isSrgb(long l)
    {
        return colorSpace(l).isSrgb();
    }

    public static boolean isWideGamut(long l)
    {
        return colorSpace(l).isWideGamut();
    }

    public static float luminance(int i)
    {
        DoubleUnaryOperator doubleunaryoperator = ((ColorSpace.Rgb)ColorSpace.get(ColorSpace.Named.SRGB)).getEotf();
        return (float)(0.21260000000000001D * doubleunaryoperator.applyAsDouble((double)red(i) / 255D) + 0.71519999999999995D * doubleunaryoperator.applyAsDouble((double)green(i) / 255D) + 0.0722D * doubleunaryoperator.applyAsDouble((double)blue(i) / 255D));
    }

    public static float luminance(long l)
    {
        Object obj = colorSpace(l);
        if(((ColorSpace) (obj)).getModel() != ColorSpace.Model.RGB)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("The specified color must be encoded in an RGB color space. The supplied color space is ").append(((ColorSpace) (obj)).getModel()).toString());
        } else
        {
            obj = ((ColorSpace.Rgb)obj).getEotf();
            return saturate((float)(0.21260000000000001D * ((DoubleUnaryOperator) (obj)).applyAsDouble(red(l)) + 0.71519999999999995D * ((DoubleUnaryOperator) (obj)).applyAsDouble(green(l)) + 0.0722D * ((DoubleUnaryOperator) (obj)).applyAsDouble(blue(l))));
        }
    }

    private static native int nativeHSVToColor(int i, float af[]);

    private static native void nativeRGBToHSV(int i, int j, int k, float af[]);

    public static long pack(float f, float f1, float f2)
    {
        return pack(f, f1, f2, 1.0F, ColorSpace.get(ColorSpace.Named.SRGB));
    }

    public static long pack(float f, float f1, float f2, float f3)
    {
        return pack(f, f1, f2, f3, ColorSpace.get(ColorSpace.Named.SRGB));
    }

    public static long pack(float f, float f1, float f2, float f3, ColorSpace colorspace)
    {
        if(colorspace.isSrgb())
            return ((long)((int)(255F * f3 + 0.5F) << 24 | (int)(255F * f + 0.5F) << 16 | (int)(255F * f1 + 0.5F) << 8 | (int)(255F * f2 + 0.5F)) & 0xffffffffL) << 32;
        int i = colorspace.getId();
        if(i == -1)
            throw new IllegalArgumentException("Unknown color space, please use a color space returned by ColorSpace.get()");
        if(colorspace.getComponentCount() > 3)
        {
            throw new IllegalArgumentException("The color space must use a color model with at most 3 components");
        } else
        {
            int j = Half.toHalf(f);
            int k = Half.toHalf(f1);
            int l = Half.toHalf(f2);
            int i1 = (int)(Math.max(0.0F, Math.min(f3, 1.0F)) * 1023F + 0.5F);
            return ((long)j & 65535L) << 48 | ((long)k & 65535L) << 32 | ((long)l & 65535L) << 16 | ((long)i1 & 1023L) << 6 | (long)i & 63L;
        }
    }

    public static long pack(int i)
    {
        return ((long)i & 0xffffffffL) << 32;
    }

    public static int parseColor(String s)
    {
        long l;
        if(s.charAt(0) != '#')
            break MISSING_BLOCK_LABEL_59;
        l = Long.parseLong(s.substring(1), 16);
        if(s.length() != 7) goto _L2; else goto _L1
_L1:
        l |= 0xffffffffff000000L;
_L4:
        return (int)l;
_L2:
        if(s.length() == 9) goto _L4; else goto _L3
_L3:
        throw new IllegalArgumentException("Unknown color");
        s = (Integer)sColorNameMap.get(s.toLowerCase(Locale.ROOT));
        if(s != null)
            return s.intValue();
        else
            throw new IllegalArgumentException("Unknown color");
    }

    public static float red(long l)
    {
        if((63L & l) == 0L)
            return (float)(l >> 48 & 255L) / 255F;
        else
            return Half.toFloat((short)(int)(l >> 48 & 65535L));
    }

    public static int red(int i)
    {
        return i >> 16 & 0xff;
    }

    public static int rgb(float f, float f1, float f2)
    {
        return (int)(f * 255F + 0.5F) << 16 | 0xff000000 | (int)(f1 * 255F + 0.5F) << 8 | (int)(f2 * 255F + 0.5F);
    }

    public static int rgb(int i, int j, int k)
    {
        return i << 16 | 0xff000000 | j << 8 | k;
    }

    private static float saturate(float f)
    {
        if(f > 0.0F) goto _L2; else goto _L1
_L1:
        float f1 = 0.0F;
_L4:
        return f1;
_L2:
        f1 = f;
        if(f >= 1.0F)
            f1 = 1.0F;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static int toArgb(long l)
    {
        if((63L & l) == 0L)
        {
            return (int)(l >> 32);
        } else
        {
            float f = red(l);
            float f1 = green(l);
            float f2 = blue(l);
            float f3 = alpha(l);
            float af[] = ColorSpace.connect(colorSpace(l)).transform(f, f1, f2);
            return (int)(f3 * 255F + 0.5F) << 24 | (int)(af[0] * 255F + 0.5F) << 16 | (int)(af[1] * 255F + 0.5F) << 8 | (int)(af[2] * 255F + 0.5F);
        }
    }

    public static Color valueOf(float f, float f1, float f2)
    {
        return new Color(f, f1, f2, 1.0F);
    }

    public static Color valueOf(float f, float f1, float f2, float f3)
    {
        return new Color(saturate(f), saturate(f1), saturate(f2), saturate(f3));
    }

    public static Color valueOf(float f, float f1, float f2, float f3, ColorSpace colorspace)
    {
        if(colorspace.getComponentCount() > 3)
            throw new IllegalArgumentException("The specified color space must use a color model with at most 3 color components");
        else
            return new Color(f, f1, f2, f3, colorspace);
    }

    public static Color valueOf(int i)
    {
        return new Color((float)(i >> 16 & 0xff) / 255F, (float)(i >> 8 & 0xff) / 255F, (float)(i & 0xff) / 255F, (float)(i >> 24 & 0xff) / 255F, ColorSpace.get(ColorSpace.Named.SRGB));
    }

    public static Color valueOf(long l)
    {
        return new Color(red(l), green(l), blue(l), alpha(l), colorSpace(l));
    }

    public static Color valueOf(float af[], ColorSpace colorspace)
    {
        if(af.length < colorspace.getComponentCount() + 1)
            throw new IllegalArgumentException((new StringBuilder()).append("Received a component array of length ").append(af.length).append(" but the color model requires ").append(colorspace.getComponentCount() + 1).append(" (including alpha)").toString());
        else
            return new Color(Arrays.copyOf(af, colorspace.getComponentCount() + 1), colorspace);
    }

    public float alpha()
    {
        return mComponents[mComponents.length - 1];
    }

    public float blue()
    {
        return mComponents[2];
    }

    public Color convert(ColorSpace colorspace)
    {
        ColorSpace.Connector connector = ColorSpace.connect(mColorSpace, colorspace);
        float af[] = new float[4];
        af[0] = mComponents[0];
        af[1] = mComponents[1];
        af[2] = mComponents[2];
        af[3] = mComponents[3];
        connector.transform(af);
        return new Color(af, colorspace);
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        obj = (Color)obj;
        if(!Arrays.equals(mComponents, ((Color) (obj)).mComponents))
            return false;
        else
            return mColorSpace.equals(((Color) (obj)).mColorSpace);
    }

    public ColorSpace getColorSpace()
    {
        return mColorSpace;
    }

    public float getComponent(int i)
    {
        return mComponents[i];
    }

    public int getComponentCount()
    {
        return mColorSpace.getComponentCount() + 1;
    }

    public float[] getComponents()
    {
        return Arrays.copyOf(mComponents, mComponents.length);
    }

    public float[] getComponents(float af[])
    {
        if(af == null)
            return Arrays.copyOf(mComponents, mComponents.length);
        if(af.length < mComponents.length)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("The specified array's length must be at least ").append(mComponents.length).toString());
        } else
        {
            System.arraycopy(mComponents, 0, af, 0, mComponents.length);
            return af;
        }
    }

    public ColorSpace.Model getModel()
    {
        return mColorSpace.getModel();
    }

    public float green()
    {
        return mComponents[1];
    }

    public int hashCode()
    {
        return Arrays.hashCode(mComponents) * 31 + mColorSpace.hashCode();
    }

    public boolean isSrgb()
    {
        return getColorSpace().isSrgb();
    }

    public boolean isWideGamut()
    {
        return getColorSpace().isWideGamut();
    }

    public float luminance()
    {
        if(mColorSpace.getModel() != ColorSpace.Model.RGB)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("The specified color must be encoded in an RGB color space. The supplied color space is ").append(mColorSpace.getModel()).toString());
        } else
        {
            DoubleUnaryOperator doubleunaryoperator = ((ColorSpace.Rgb)mColorSpace).getEotf();
            return saturate((float)(0.21260000000000001D * doubleunaryoperator.applyAsDouble(mComponents[0]) + 0.71519999999999995D * doubleunaryoperator.applyAsDouble(mComponents[1]) + 0.0722D * doubleunaryoperator.applyAsDouble(mComponents[2])));
        }
    }

    public long pack()
    {
        return pack(mComponents[0], mComponents[1], mComponents[2], mComponents[3], mColorSpace);
    }

    public float red()
    {
        return mComponents[0];
    }

    public int toArgb()
    {
        if(mColorSpace.isSrgb())
        {
            return (int)(mComponents[3] * 255F + 0.5F) << 24 | (int)(mComponents[0] * 255F + 0.5F) << 16 | (int)(mComponents[1] * 255F + 0.5F) << 8 | (int)(mComponents[2] * 255F + 0.5F);
        } else
        {
            float af[] = new float[4];
            af[0] = mComponents[0];
            af[1] = mComponents[1];
            af[2] = mComponents[2];
            af[3] = mComponents[3];
            ColorSpace.connect(mColorSpace).transform(af);
            return (int)(af[3] * 255F + 0.5F) << 24 | (int)(af[0] * 255F + 0.5F) << 16 | (int)(af[1] * 255F + 0.5F) << 8 | (int)(af[2] * 255F + 0.5F);
        }
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("Color(");
        float af[] = mComponents;
        int i = 0;
        for(int j = af.length; i < j; i++)
            stringbuilder.append(af[i]).append(", ");

        stringbuilder.append(mColorSpace.getName());
        stringbuilder.append(')');
        return stringbuilder.toString();
    }

    public static final int BLACK = 0xff000000;
    public static final int BLUE = 0xff0000ff;
    public static final int CYAN = 0xff00ffff;
    public static final int DKGRAY = 0xff444444;
    public static final int GRAY = 0xff888888;
    public static final int GREEN = 0xff00ff00;
    public static final int LTGRAY = 0xffcccccc;
    public static final int MAGENTA = -65281;
    public static final int RED = 0xffff0000;
    public static final int TRANSPARENT = 0;
    public static final int WHITE = -1;
    public static final int YELLOW = -256;
    private static final HashMap sColorNameMap;
    private final ColorSpace mColorSpace;
    private final float mComponents[] = {
        0.0F, 0.0F, 0.0F, 1.0F
    };

    static 
    {
        sColorNameMap = new HashMap();
        sColorNameMap.put("black", Integer.valueOf(0xff000000));
        sColorNameMap.put("darkgray", Integer.valueOf(0xff444444));
        sColorNameMap.put("gray", Integer.valueOf(0xff888888));
        sColorNameMap.put("lightgray", Integer.valueOf(0xffcccccc));
        sColorNameMap.put("white", Integer.valueOf(-1));
        sColorNameMap.put("red", Integer.valueOf(0xffff0000));
        sColorNameMap.put("green", Integer.valueOf(0xff00ff00));
        sColorNameMap.put("blue", Integer.valueOf(0xff0000ff));
        sColorNameMap.put("yellow", Integer.valueOf(-256));
        sColorNameMap.put("cyan", Integer.valueOf(0xff00ffff));
        sColorNameMap.put("magenta", Integer.valueOf(-65281));
        sColorNameMap.put("aqua", Integer.valueOf(0xff00ffff));
        sColorNameMap.put("fuchsia", Integer.valueOf(-65281));
        sColorNameMap.put("darkgrey", Integer.valueOf(0xff444444));
        sColorNameMap.put("grey", Integer.valueOf(0xff888888));
        sColorNameMap.put("lightgrey", Integer.valueOf(0xffcccccc));
        sColorNameMap.put("lime", Integer.valueOf(0xff00ff00));
        sColorNameMap.put("maroon", Integer.valueOf(0xff800000));
        sColorNameMap.put("navy", Integer.valueOf(0xff000080));
        sColorNameMap.put("olive", Integer.valueOf(0xff808000));
        sColorNameMap.put("purple", Integer.valueOf(0xff800080));
        sColorNameMap.put("silver", Integer.valueOf(0xffc0c0c0));
        sColorNameMap.put("teal", Integer.valueOf(0xff008080));
    }
}
