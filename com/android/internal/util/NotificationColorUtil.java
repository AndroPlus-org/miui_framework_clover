// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.*;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.*;
import android.util.Log;
import android.util.Pair;
import java.util.Arrays;
import java.util.WeakHashMap;

// Referenced classes of package com.android.internal.util:
//            ImageUtils

public class NotificationColorUtil
{
    private static class ColorUtilsFromCompat
    {

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
        //                       0 139
        //                       1 176
        //                       2 213
        //                       3 250
        //                       4 287
        //                       5 324
        //                       6 324;
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
            f2 = 0.0F;
            f5 = 0.0F;
_L4:
            f1 = (60F * f5) % 360F;
            f5 = f1;
            if(f1 < 0.0F)
                f5 = f1 + 360F;
            af[0] = constrain(f5, 0.0F, 360F);
            af[1] = constrain(f2, 0.0F, 1.0F);
            af[2] = constrain(f6, 0.0F, 1.0F);
            return;
_L2:
            if(f3 != f)
                break; /* Loop/switch isn't completed */
            f2 = ((f1 - f2) / f5) % 6F;
_L5:
            f1 = f5 / (1.0F - Math.abs(2.0F * f6 - 1.0F));
            f5 = f2;
            f2 = f1;
            if(true) goto _L4; else goto _L3
_L3:
            if(f3 == f1)
                f2 = (f2 - f) / f5 + 2.0F;
            else
                f2 = (f - f1) / f5 + 4F;
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

        public static double calculateContrast(int i, int j)
        {
            if(Color.alpha(j) != 255)
                Log.wtf("NotificationColorUtil", (new StringBuilder()).append("background can not be translucent: #").append(Integer.toHexString(j)).toString());
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

        public static double[] getTempDouble3Array()
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

        private static double pivotXyzComponent(double d)
        {
            if(d > 0.0088559999999999993D)
                d = Math.pow(d, 0.33333333333333331D);
            else
                d = (903.29999999999995D * d + 16D) / 116D;
            return d;
        }

        private static final int MIN_ALPHA_SEARCH_MAX_ITERATIONS = 10;
        private static final int MIN_ALPHA_SEARCH_PRECISION = 1;
        private static final ThreadLocal TEMP_ARRAY = new ThreadLocal();
        private static final double XYZ_EPSILON = 0.0088559999999999993D;
        private static final double XYZ_KAPPA = 903.29999999999995D;
        private static final double XYZ_WHITE_REFERENCE_X = 95.046999999999997D;
        private static final double XYZ_WHITE_REFERENCE_Y = 100D;
        private static final double XYZ_WHITE_REFERENCE_Z = 108.883D;


        private ColorUtilsFromCompat()
        {
        }
    }


    private NotificationColorUtil(Context context)
    {
        mGrayscaleIconMaxSize = context.getResources().getDimensionPixelSize(0x1050005);
    }

    public static double calculateContrast(int i, int j)
    {
        return ColorUtilsFromCompat.calculateContrast(i, j);
    }

    public static double calculateLuminance(int i)
    {
        return ColorUtilsFromCompat.calculateLuminance(i);
    }

    public static int changeColorLightness(int i, int j)
    {
        double ad[] = ColorUtilsFromCompat.getTempDouble3Array();
        ColorUtilsFromCompat.colorToLAB(i, ad);
        ad[0] = Math.max(Math.min(100D, ad[0] + (double)j), 0.0D);
        return ColorUtilsFromCompat.LABToColor(ad[0], ad[1], ad[2]);
    }

    public static CharSequence clearColorSpans(CharSequence charsequence)
    {
        Spanned spanned;
        Object aobj[];
        SpannableStringBuilder spannablestringbuilder;
        int i;
        int j;
        Object obj;
        if(charsequence instanceof Spanned)
        {
            spanned = (Spanned)charsequence;
            aobj = spanned.getSpans(0, spanned.length(), java/lang/Object);
            spannablestringbuilder = new SpannableStringBuilder(spanned.toString());
            i = aobj.length;
            j = 0;
        } else
        {
            return charsequence;
        }
        if(j >= i) goto _L2; else goto _L1
_L1:
        obj = aobj[j];
        charsequence = ((CharSequence) (obj));
        if(obj instanceof CharacterStyle)
            charsequence = ((CharacterStyle)obj).getUnderlying();
        if(!(charsequence instanceof TextAppearanceSpan)) goto _L4; else goto _L3
_L3:
        TextAppearanceSpan textappearancespan = (TextAppearanceSpan)charsequence;
        if(textappearancespan.getTextColor() != null)
            charsequence = new TextAppearanceSpan(textappearancespan.getFamily(), textappearancespan.getTextStyle(), textappearancespan.getTextSize(), null, textappearancespan.getLinkTextColor());
_L7:
        spannablestringbuilder.setSpan(charsequence, spanned.getSpanStart(obj), spanned.getSpanEnd(obj), spanned.getSpanFlags(obj));
_L6:
        j++;
        continue; /* Loop/switch isn't completed */
_L4:
        if((charsequence instanceof ForegroundColorSpan) || (charsequence instanceof BackgroundColorSpan)) goto _L6; else goto _L5
_L5:
        charsequence = ((CharSequence) (obj));
        if(true) goto _L7; else goto _L2
_L2:
        return spannablestringbuilder;
        if(true) goto _L9; else goto _L8
_L9:
        break MISSING_BLOCK_LABEL_49;
_L8:
    }

    public static int compositeColors(int i, int j)
    {
        return ColorUtilsFromCompat.compositeColors(i, j);
    }

    private static String contrastChange(int i, int j, int k)
    {
        return String.format("from %.2f:1 to %.2f:1", new Object[] {
            Double.valueOf(ColorUtilsFromCompat.calculateContrast(i, k)), Double.valueOf(ColorUtilsFromCompat.calculateContrast(j, k))
        });
    }

    public static int ensureLargeTextContrast(int i, int j, boolean flag)
    {
        if(flag)
            i = findContrastColorAgainstDark(i, j, true, 3D);
        else
            i = findContrastColor(i, j, true, 3D);
        return i;
    }

    public static int ensureTextBackgroundColor(int i, int j, int k)
    {
        return findContrastColor(findContrastColor(i, k, false, 3D), j, false, 4.5D);
    }

    private static int ensureTextContrast(int i, int j, boolean flag)
    {
        if(flag)
            i = findContrastColorAgainstDark(i, j, true, 4.5D);
        else
            i = findContrastColor(i, j, true, 4.5D);
        return i;
    }

    public static int ensureTextContrastOnBlack(int i)
    {
        return findContrastColorAgainstDark(i, 0xff000000, true, 12D);
    }

    public static int findAlphaToMeetContrast(int i, int j, double d)
    {
        if(ColorUtilsFromCompat.calculateContrast(i, j) >= d)
            return i;
        int k = Color.alpha(i);
        int l = Color.red(i);
        int i1 = Color.green(i);
        int j1 = Color.blue(i);
        int k1 = 255;
        i = 0;
        while(i < 15 && k1 - k > 0) 
        {
            int l1 = (k + k1) / 2;
            if(ColorUtilsFromCompat.calculateContrast(Color.argb(l1, l, i1, j1), j) > d)
                k1 = l1;
            else
                k = l1;
            i++;
        }
        return Color.argb(k1, l, i1, j1);
    }

    public static int findContrastColor(int i, int j, boolean flag, double d)
    {
        int k;
        if(flag)
            k = i;
        else
            k = j;
        if(!flag)
            j = i;
        if(ColorUtilsFromCompat.calculateContrast(k, j) >= d)
            return i;
        double ad[] = new double[3];
        double d1;
        double d2;
        double d3;
        double d4;
        if(flag)
            i = k;
        else
            i = j;
        ColorUtilsFromCompat.colorToLAB(i, ad);
        d1 = 0.0D;
        d2 = ad[0];
        d3 = ad[1];
        d4 = ad[2];
        i = 0;
        while(i < 15 && d2 - d1 > 1.0000000000000001E-005D) 
        {
            double d5 = (d1 + d2) / 2D;
            if(flag)
                k = ColorUtilsFromCompat.LABToColor(d5, d3, d4);
            else
                j = ColorUtilsFromCompat.LABToColor(d5, d3, d4);
            if(ColorUtilsFromCompat.calculateContrast(k, j) > d)
                d1 = d5;
            else
                d2 = d5;
            i++;
        }
        return ColorUtilsFromCompat.LABToColor(d1, d3, d4);
    }

    public static int findContrastColorAgainstDark(int i, int j, boolean flag, double d)
    {
        int k;
        if(flag)
            k = i;
        else
            k = j;
        if(!flag)
            j = i;
        if(ColorUtilsFromCompat.calculateContrast(k, j) >= d)
            return i;
        float af[] = new float[3];
        float f;
        float f1;
        if(flag)
            i = k;
        else
            i = j;
        ColorUtilsFromCompat.colorToHSL(i, af);
        f = af[2];
        f1 = 1.0F;
        i = 0;
        while(i < 15 && (double)(f1 - f) > 1.0000000000000001E-005D) 
        {
            float f2 = (f + f1) / 2.0F;
            af[2] = f2;
            if(flag)
                k = ColorUtilsFromCompat.HSLToColor(af);
            else
                j = ColorUtilsFromCompat.HSLToColor(af);
            if(ColorUtilsFromCompat.calculateContrast(k, j) > d)
                f1 = f2;
            else
                f = f2;
            i++;
        }
        if(!flag)
            k = j;
        return k;
    }

    public static NotificationColorUtil getInstance(Context context)
    {
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        if(sInstance == null)
        {
            NotificationColorUtil notificationcolorutil = JVM INSTR new #2   <Class NotificationColorUtil>;
            notificationcolorutil.NotificationColorUtil(context);
            sInstance = notificationcolorutil;
        }
        context = sInstance;
        obj;
        JVM INSTR monitorexit ;
        return context;
        context;
        throw context;
    }

    public static int getShiftedColor(int i, int j)
    {
        double ad[] = ColorUtilsFromCompat.getTempDouble3Array();
        ColorUtilsFromCompat.colorToLAB(i, ad);
        if(ad[0] >= 4D)
            ad[0] = Math.max(0.0D, ad[0] - (double)j);
        else
            ad[0] = Math.min(100D, ad[0] + (double)j);
        return ColorUtilsFromCompat.LABToColor(ad[0], ad[1], ad[2]);
    }

    public static boolean isColorLight(int i)
    {
        boolean flag;
        if(calculateLuminance(i) > 0.5D)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private int processColor(int i)
    {
        return Color.argb(Color.alpha(i), 255 - Color.red(i), 255 - Color.green(i), 255 - Color.blue(i));
    }

    private TextAppearanceSpan processTextAppearanceSpan(TextAppearanceSpan textappearancespan)
    {
        ColorStateList colorstatelist = textappearancespan.getTextColor();
        if(colorstatelist != null)
        {
            int ai[] = colorstatelist.getColors();
            boolean flag = false;
            int ai1[];
            for(int i = 0; i < ai.length; ai = ai1)
            {
                boolean flag1 = flag;
                ai1 = ai;
                if(ImageUtils.isGrayscale(ai[i]))
                {
                    ai1 = ai;
                    if(!flag)
                        ai1 = Arrays.copyOf(ai, ai.length);
                    ai1[i] = processColor(ai1[i]);
                    flag1 = true;
                }
                i++;
                flag = flag1;
            }

            if(flag)
                return new TextAppearanceSpan(textappearancespan.getFamily(), textappearancespan.getTextStyle(), textappearancespan.getTextSize(), new ColorStateList(colorstatelist.getStates(), ai), textappearancespan.getLinkTextColor());
        }
        return textappearancespan;
    }

    public static int resolveActionBarColor(Context context, int i)
    {
        if(i == 0)
            return context.getColor(0x10600e7);
        else
            return getShiftedColor(i, 7);
    }

    public static int resolveAmbientColor(Context context, int i)
    {
        return ensureTextContrastOnBlack(resolveColor(context, i));
    }

    public static int resolveColor(Context context, int i)
    {
        if(i == 0)
            return context.getColor(0x10600ec);
        else
            return i;
    }

    public static int resolveContrastColor(Context context, int i, int j)
    {
        return resolveContrastColor(context, i, j, false);
    }

    public static int resolveContrastColor(Context context, int i, int j, boolean flag)
    {
        return ensureTextContrast(ensureLargeTextContrast(resolveColor(context, i), context.getColor(0x10600e7), flag), j, flag);
    }

    public static int resolvePrimaryColor(Context context, int i)
    {
        if(shouldUseDark(i))
            return context.getColor(0x10600f1);
        else
            return context.getColor(0x10600f0);
    }

    public static int resolveSecondaryColor(Context context, int i)
    {
        if(shouldUseDark(i))
            return context.getColor(0x10600f4);
        else
            return context.getColor(0x10600f3);
    }

    public static boolean satisfiesTextContrast(int i, int j)
    {
        boolean flag;
        if(calculateContrast(j, i) >= 4.5D)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static boolean shouldUseDark(int i)
    {
        boolean flag;
        boolean flag1;
        if(i == 0)
            flag = true;
        else
            flag = false;
        flag1 = flag;
        if(!flag)
            if(ColorUtilsFromCompat.calculateLuminance(i) > 0.5D)
                flag1 = true;
            else
                flag1 = false;
        return flag1;
    }

    public CharSequence invertCharSequenceColors(CharSequence charsequence)
    {
        if(charsequence instanceof Spanned)
        {
            Spanned spanned = (Spanned)charsequence;
            Object aobj[] = spanned.getSpans(0, spanned.length(), java/lang/Object);
            SpannableStringBuilder spannablestringbuilder = new SpannableStringBuilder(spanned.toString());
            int i = aobj.length;
            int j = 0;
            while(j < i) 
            {
                Object obj = aobj[j];
                charsequence = ((CharSequence) (obj));
                if(obj instanceof CharacterStyle)
                    charsequence = ((CharacterStyle)obj).getUnderlying();
                if(charsequence instanceof TextAppearanceSpan)
                {
                    TextAppearanceSpan textappearancespan = processTextAppearanceSpan((TextAppearanceSpan)obj);
                    if(textappearancespan != charsequence)
                        charsequence = textappearancespan;
                    else
                        charsequence = ((CharSequence) (obj));
                } else
                if(charsequence instanceof ForegroundColorSpan)
                    charsequence = new ForegroundColorSpan(processColor(((ForegroundColorSpan)charsequence).getForegroundColor()));
                else
                    charsequence = ((CharSequence) (obj));
                spannablestringbuilder.setSpan(charsequence, spanned.getSpanStart(obj), spanned.getSpanEnd(obj), spanned.getSpanFlags(obj));
                j++;
            }
            return spannablestringbuilder;
        } else
        {
            return charsequence;
        }
    }

    public boolean isGrayscaleIcon(Context context, int i)
    {
        if(i != 0)
        {
            boolean flag;
            try
            {
                flag = isGrayscaleIcon(context.getDrawable(i));
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                Log.e("NotificationColorUtil", (new StringBuilder()).append("Drawable not found: ").append(i).toString());
                return false;
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public boolean isGrayscaleIcon(Context context, Icon icon)
    {
        if(icon == null)
            return false;
        switch(icon.getType())
        {
        default:
            return false;

        case 1: // '\001'
            return isGrayscaleIcon(icon.getBitmap());

        case 2: // '\002'
            return isGrayscaleIcon(context, icon.getResId());
        }
    }

    public boolean isGrayscaleIcon(Bitmap bitmap)
    {
        if(bitmap.getWidth() > mGrayscaleIconMaxSize || bitmap.getHeight() > mGrayscaleIconMaxSize)
            return false;
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        Pair pair = (Pair)mGrayscaleBitmapCache.get(bitmap);
        if(pair == null)
            break MISSING_BLOCK_LABEL_80;
        boolean flag;
        if(((Integer)pair.second).intValue() != bitmap.getGenerationId())
            break MISSING_BLOCK_LABEL_80;
        flag = ((Boolean)pair.first).booleanValue();
        obj;
        JVM INSTR monitorexit ;
        return flag;
        obj;
        JVM INSTR monitorexit ;
        obj = mImageUtils;
        obj;
        JVM INSTR monitorenter ;
        int i;
        flag = mImageUtils.isGrayscale(bitmap);
        i = bitmap.getGenerationId();
        obj;
        JVM INSTR monitorexit ;
        obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        mGrayscaleBitmapCache.put(bitmap, Pair.create(Boolean.valueOf(flag), Integer.valueOf(i)));
        obj;
        JVM INSTR monitorexit ;
        return flag;
        bitmap;
        throw bitmap;
        bitmap;
        throw bitmap;
        bitmap;
        throw bitmap;
    }

    public boolean isGrayscaleIcon(Drawable drawable)
    {
        boolean flag = false;
        boolean flag1 = false;
        if(drawable == null)
            return false;
        if(drawable instanceof BitmapDrawable)
        {
            drawable = (BitmapDrawable)drawable;
            if(drawable.getBitmap() != null)
                flag1 = isGrayscaleIcon(drawable.getBitmap());
            return flag1;
        }
        if(drawable instanceof AnimationDrawable)
        {
            drawable = (AnimationDrawable)drawable;
            boolean flag2 = flag;
            if(drawable.getNumberOfFrames() > 0)
                flag2 = isGrayscaleIcon(drawable.getFrame(0));
            return flag2;
        }
        return drawable instanceof VectorDrawable;
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "NotificationColorUtil";
    private static NotificationColorUtil sInstance;
    private static final Object sLock = new Object();
    private final WeakHashMap mGrayscaleBitmapCache = new WeakHashMap();
    private final int mGrayscaleIconMaxSize;
    private final ImageUtils mImageUtils = new ImageUtils();

}
