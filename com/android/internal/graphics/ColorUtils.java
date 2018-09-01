// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.graphics;

import android.graphics.Color;

public final class ColorUtils
{
    private static interface ContrastCalculator
    {

        public abstract double calculateContrast(int i, int j, int k);
    }


    private ColorUtils()
    {
    }

    public static int HSLToColor(float af[])
    {
        float f1;
        float f2;
        float f3;
        int i;
        int j;
        int k;
        int l;
        float f = af[0];
        f1 = af[1];
        f2 = af[2];
        f1 = (1.0F - Math.abs(2.0F * f2 - 1.0F)) * f1;
        f3 = f2 - 0.5F * f1;
        f2 = f1 * (1.0F - Math.abs((f / 60F) % 2.0F - 1.0F));
        i = (int)f / 60;
        j = 0;
        k = 0;
        l = 0;
        i;
        JVM INSTR tableswitch 0 6: default 108
    //                   0 139
    //                   1 176
    //                   2 213
    //                   3 250
    //                   4 287
    //                   5 324
    //                   6 324;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L7
_L1:
        return Color.rgb(constrain(j, 0, 255), constrain(k, 0, 255), constrain(l, 0, 255));
_L2:
        j = Math.round((f1 + f3) * 255F);
        k = Math.round((f2 + f3) * 255F);
        l = Math.round(255F * f3);
        continue; /* Loop/switch isn't completed */
_L3:
        j = Math.round((f2 + f3) * 255F);
        k = Math.round((f1 + f3) * 255F);
        l = Math.round(255F * f3);
        continue; /* Loop/switch isn't completed */
_L4:
        j = Math.round(255F * f3);
        k = Math.round((f1 + f3) * 255F);
        l = Math.round((f2 + f3) * 255F);
        continue; /* Loop/switch isn't completed */
_L5:
        j = Math.round(255F * f3);
        k = Math.round((f2 + f3) * 255F);
        l = Math.round((f1 + f3) * 255F);
        continue; /* Loop/switch isn't completed */
_L6:
        j = Math.round((f2 + f3) * 255F);
        k = Math.round(255F * f3);
        l = Math.round((f1 + f3) * 255F);
        continue; /* Loop/switch isn't completed */
_L7:
        j = Math.round((f1 + f3) * 255F);
        k = Math.round(255F * f3);
        l = Math.round((f2 + f3) * 255F);
        if(true) goto _L1; else goto _L8
_L8:
    }

    public static int LABToColor(double d, double d1, double d2)
    {
        double ad[] = getTempDouble3Array();
        LABToXYZ(d, d1, d2, ad);
        return XYZToColor(ad[0], ad[1], ad[2]);
    }

    public static void LABToXYZ(double d, double d1, double d2, double ad[])
    {
        double d3 = (16D + d) / 116D;
        double d4 = d1 / 500D + d3;
        double d5 = d3 - d2 / 200D;
        d1 = Math.pow(d4, 3D);
        if(d1 <= 0.0088559999999999993D)
            d1 = (116D * d4 - 16D) / 903.29999999999995D;
        if(d > 7.9996247999999985D)
            d = Math.pow(d3, 3D);
        else
            d /= 903.29999999999995D;
        d2 = Math.pow(d5, 3D);
        if(d2 <= 0.0088559999999999993D)
            d2 = (116D * d5 - 16D) / 903.29999999999995D;
        ad[0] = 95.046999999999997D * d1;
        ad[1] = 100D * d;
        ad[2] = 108.883D * d2;
    }

    public static void RGBToHSL(int i, int j, int k, float af[])
    {
        float f;
        float f1;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        f = (float)i / 255F;
        f1 = (float)j / 255F;
        f2 = (float)k / 255F;
        f3 = Math.max(f, Math.max(f1, f2));
        f4 = Math.min(f, Math.min(f1, f2));
        f5 = f3 - f4;
        f6 = (f3 + f4) / 2.0F;
        if(f3 != f4) goto _L2; else goto _L1
_L1:
        f3 = 0.0F;
        f5 = 0.0F;
_L4:
        f2 = (60F * f5) % 360F;
        f5 = f2;
        if(f2 < 0.0F)
            f5 = f2 + 360F;
        af[0] = constrain(f5, 0.0F, 360F);
        af[1] = constrain(f3, 0.0F, 1.0F);
        af[2] = constrain(f6, 0.0F, 1.0F);
        return;
_L2:
        if(f3 != f)
            break; /* Loop/switch isn't completed */
        f3 = ((f1 - f2) / f5) % 6F;
_L5:
        f2 = f5 / (1.0F - Math.abs(2.0F * f6 - 1.0F));
        f5 = f3;
        f3 = f2;
        if(true) goto _L4; else goto _L3
_L3:
        if(f3 == f1)
            f3 = (f2 - f) / f5 + 2.0F;
        else
            f3 = (f - f1) / f5 + 4F;
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }

    public static void RGBToLAB(int i, int j, int k, double ad[])
    {
        RGBToXYZ(i, j, k, ad);
        XYZToLAB(ad[0], ad[1], ad[2], ad);
    }

    public static void RGBToXYZ(int i, int j, int k, double ad[])
    {
        if(ad.length != 3)
            throw new IllegalArgumentException("outXyz must have a length of 3.");
        double d = (double)i / 255D;
        double d1;
        double d2;
        if(d < 0.04045D)
            d /= 12.92D;
        else
            d = Math.pow((0.055D + d) / 1.0549999999999999D, 2.3999999999999999D);
        d1 = (double)j / 255D;
        if(d1 < 0.04045D)
            d1 /= 12.92D;
        else
            d1 = Math.pow((0.055D + d1) / 1.0549999999999999D, 2.3999999999999999D);
        d2 = (double)k / 255D;
        if(d2 < 0.04045D)
            d2 /= 12.92D;
        else
            d2 = Math.pow((0.055D + d2) / 1.0549999999999999D, 2.3999999999999999D);
        ad[0] = (0.41239999999999999D * d + 0.35759999999999997D * d1 + 0.18049999999999999D * d2) * 100D;
        ad[1] = (0.21260000000000001D * d + 0.71519999999999995D * d1 + 0.0722D * d2) * 100D;
        ad[2] = (0.019300000000000001D * d + 0.1192D * d1 + 0.95050000000000001D * d2) * 100D;
    }

    public static int XYZToColor(double d, double d1, double d2)
    {
        double d3 = (3.2406000000000001D * d + -1.5371999999999999D * d1 + -0.49859999999999999D * d2) / 100D;
        double d4 = (-0.96889999999999998D * d + 1.8757999999999999D * d1 + 0.041500000000000002D * d2) / 100D;
        d2 = (0.0557D * d + -0.20399999999999999D * d1 + 1.0569999999999999D * d2) / 100D;
        if(d3 > 0.0031308D)
            d = Math.pow(d3, 0.41666666666666669D) * 1.0549999999999999D - 0.055D;
        else
            d = d3 * 12.92D;
        if(d4 > 0.0031308D)
            d1 = Math.pow(d4, 0.41666666666666669D) * 1.0549999999999999D - 0.055D;
        else
            d1 = d4 * 12.92D;
        if(d2 > 0.0031308D)
            d2 = Math.pow(d2, 0.41666666666666669D) * 1.0549999999999999D - 0.055D;
        else
            d2 *= 12.92D;
        return Color.rgb(constrain((int)Math.round(255D * d), 0, 255), constrain((int)Math.round(255D * d1), 0, 255), constrain((int)Math.round(255D * d2), 0, 255));
    }

    public static void XYZToLAB(double d, double d1, double d2, double ad[])
    {
        if(ad.length != 3)
        {
            throw new IllegalArgumentException("outLab must have a length of 3.");
        } else
        {
            d = pivotXyzComponent(d / 95.046999999999997D);
            d1 = pivotXyzComponent(d1 / 100D);
            d2 = pivotXyzComponent(d2 / 108.883D);
            ad[0] = Math.max(0.0D, 116D * d1 - 16D);
            ad[1] = (d - d1) * 500D;
            ad[2] = (d1 - d2) * 200D;
            return;
        }
    }

    private static int binaryAlphaSearch(int i, int j, float f, ContrastCalculator contrastcalculator)
    {
        int k = 0;
        int l = 0;
        int i1 = 255;
        while(k <= 10 && i1 - l > 1) 
        {
            int j1 = (l + i1) / 2;
            if(contrastcalculator.calculateContrast(i, j, j1) < (double)f)
                l = j1;
            else
                i1 = j1;
            k++;
        }
        return i1;
    }

    public static int blendARGB(int i, int j, float f)
    {
        float f1 = 1.0F - f;
        float f2 = Color.alpha(i);
        float f3 = Color.alpha(j);
        float f4 = Color.red(i);
        float f5 = Color.red(j);
        float f6 = Color.green(i);
        float f7 = Color.green(j);
        float f8 = Color.blue(i);
        float f9 = Color.blue(j);
        return Color.argb((int)(f2 * f1 + f3 * f), (int)(f4 * f1 + f5 * f), (int)(f6 * f1 + f7 * f), (int)(f8 * f1 + f9 * f));
    }

    public static void blendHSL(float af[], float af1[], float f, float af2[])
    {
        if(af2.length != 3)
        {
            throw new IllegalArgumentException("result must have a length of 3.");
        } else
        {
            float f1 = 1.0F - f;
            af2[0] = circularInterpolate(af[0], af1[0], f);
            af2[1] = af[1] * f1 + af1[1] * f;
            af2[2] = af[2] * f1 + af1[2] * f;
            return;
        }
    }

    public static void blendLAB(double ad[], double ad1[], double d, double ad2[])
    {
        if(ad2.length != 3)
        {
            throw new IllegalArgumentException("outResult must have a length of 3.");
        } else
        {
            double d1 = 1.0D - d;
            ad2[0] = ad[0] * d1 + ad1[0] * d;
            ad2[1] = ad[1] * d1 + ad1[1] * d;
            ad2[2] = ad[2] * d1 + ad1[2] * d;
            return;
        }
    }

    public static double calculateContrast(int i, int j)
    {
        if(Color.alpha(j) != 255)
            throw new IllegalArgumentException((new StringBuilder()).append("background can not be translucent: #").append(Integer.toHexString(j)).toString());
        int k = i;
        if(Color.alpha(i) < 255)
            k = compositeColors(i, j);
        double d = calculateLuminance(k) + 0.050000000000000003D;
        double d1 = calculateLuminance(j) + 0.050000000000000003D;
        return Math.max(d, d1) / Math.min(d, d1);
    }

    public static double calculateLuminance(int i)
    {
        double ad[] = getTempDouble3Array();
        colorToXYZ(i, ad);
        return ad[1] / 100D;
    }

    public static int calculateMinimumAlpha(int i, int j, float f)
    {
        if(Color.alpha(j) != 255)
            throw new IllegalArgumentException((new StringBuilder()).append("background can not be translucent: #").append(Integer.toHexString(j)).toString());
        _2D_.Lambda._cls03T1rR3H6Pfo2RsQKEXM1or54G4 _lcls03t1rr3h6pfo2rsqkexm1or54g4 = _.Lambda._cls03T1rR3H6Pfo2RsQKEXM1or54G4.$INST$0;
        if(_lcls03t1rr3h6pfo2rsqkexm1or54g4.calculateContrast(i, j, 255) < (double)f)
            return -1;
        else
            return binaryAlphaSearch(setAlphaComponent(i, 255), j, f, _lcls03t1rr3h6pfo2rsqkexm1or54g4);
    }

    public static int calculateMinimumBackgroundAlpha(int i, int j, float f)
    {
        return binaryAlphaSearch(i, setAlphaComponent(j, 255), f, new _.Lambda._cls03T1rR3H6Pfo2RsQKEXM1or54G4._cls1(setAlphaComponent(i, 255)));
    }

    static float circularInterpolate(float f, float f1, float f2)
    {
        float f3 = f;
        float f4 = f1;
        if(Math.abs(f1 - f) > 180F)
            if(f1 > f)
            {
                f3 = f + 360F;
                f4 = f1;
            } else
            {
                f4 = f1 + 360F;
                f3 = f;
            }
        return ((f4 - f3) * f2 + f3) % 360F;
    }

    public static void colorToHSL(int i, float af[])
    {
        RGBToHSL(Color.red(i), Color.green(i), Color.blue(i), af);
    }

    public static void colorToLAB(int i, double ad[])
    {
        RGBToLAB(Color.red(i), Color.green(i), Color.blue(i), ad);
    }

    public static void colorToXYZ(int i, double ad[])
    {
        RGBToXYZ(Color.red(i), Color.green(i), Color.blue(i), ad);
    }

    private static int compositeAlpha(int i, int j)
    {
        return 255 - ((255 - j) * (255 - i)) / 255;
    }

    public static int compositeColors(int i, int j)
    {
        int k = Color.alpha(j);
        int l = Color.alpha(i);
        int i1 = compositeAlpha(l, k);
        return Color.argb(i1, compositeComponent(Color.red(i), l, Color.red(j), k, i1), compositeComponent(Color.green(i), l, Color.green(j), k, i1), compositeComponent(Color.blue(i), l, Color.blue(j), k, i1));
    }

    private static int compositeComponent(int i, int j, int k, int l, int i1)
    {
        if(i1 == 0)
            return 0;
        else
            return (i * 255 * j + k * l * (255 - j)) / (i1 * 255);
    }

    private static float constrain(float f, float f1, float f2)
    {
        if(f >= f1)
            if(f > f2)
                f1 = f2;
            else
                f1 = f;
        return f1;
    }

    private static int constrain(int i, int j, int k)
    {
        if(i >= j)
            if(i > k)
                j = k;
            else
                j = i;
        return j;
    }

    public static double distanceEuclidean(double ad[], double ad1[])
    {
        return Math.sqrt(Math.pow(ad[0] - ad1[0], 2D) + Math.pow(ad[1] - ad1[1], 2D) + Math.pow(ad[2] - ad1[2], 2D));
    }

    private static double[] getTempDouble3Array()
    {
        double ad[] = (double[])TEMP_ARRAY.get();
        double ad1[] = ad;
        if(ad == null)
        {
            ad1 = new double[3];
            TEMP_ARRAY.set(ad1);
        }
        return ad1;
    }

    static double lambda$_2D_com_android_internal_graphics_ColorUtils_5280(int i, int j, int k, int l)
    {
        return calculateContrast(j, setAlphaComponent(blendARGB(i, k, (float)l / 255F), 255));
    }

    static double lambda$_2D_com_android_internal_graphics_ColorUtils_6537(int i, int j, int k)
    {
        return calculateContrast(setAlphaComponent(i, k), j);
    }

    private static double pivotXyzComponent(double d)
    {
        if(d > 0.0088559999999999993D)
            d = Math.pow(d, 0.33333333333333331D);
        else
            d = (903.29999999999995D * d + 16D) / 116D;
        return d;
    }

    public static int setAlphaComponent(int i, int j)
    {
        if(j < 0 || j > 255)
            throw new IllegalArgumentException("alpha must be between 0 and 255.");
        else
            return 0xffffff & i | j << 24;
    }

    private static final int MIN_ALPHA_SEARCH_MAX_ITERATIONS = 10;
    private static final int MIN_ALPHA_SEARCH_PRECISION = 1;
    private static final ThreadLocal TEMP_ARRAY = new ThreadLocal();
    private static final double XYZ_EPSILON = 0.0088559999999999993D;
    private static final double XYZ_KAPPA = 903.29999999999995D;
    private static final double XYZ_WHITE_REFERENCE_X = 95.046999999999997D;
    private static final double XYZ_WHITE_REFERENCE_Y = 100D;
    private static final double XYZ_WHITE_REFERENCE_Z = 108.883D;

}
