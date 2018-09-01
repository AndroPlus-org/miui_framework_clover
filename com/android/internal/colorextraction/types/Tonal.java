// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.colorextraction.types;

import android.app.WallpaperColors;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.*;
import com.android.internal.graphics.ColorUtils;
import java.io.IOException;
import java.util.*;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package com.android.internal.colorextraction.types:
//            ExtractionType

public class Tonal
    implements ExtractionType
{
    public static class ColorRange
    {

        public boolean containsColor(float f, float f1, float f2)
        {
            if(!mHue.contains(Float.valueOf(f)))
                return false;
            if(!mSaturation.contains(Float.valueOf(f1)))
                return false;
            return mLightness.contains(Float.valueOf(f2));
        }

        public float[] getCenter()
        {
            float f = ((Float)mHue.getLower()).floatValue();
            float f1 = (((Float)mHue.getUpper()).floatValue() - ((Float)mHue.getLower()).floatValue()) / 2.0F;
            float f2 = ((Float)mSaturation.getLower()).floatValue();
            float f3 = (((Float)mSaturation.getUpper()).floatValue() - ((Float)mSaturation.getLower()).floatValue()) / 2.0F;
            float f4 = ((Float)mLightness.getLower()).floatValue();
            return (new float[] {
                f1 + f, f3 + f2, (((Float)mLightness.getUpper()).floatValue() - ((Float)mLightness.getLower()).floatValue()) / 2.0F + f4
            });
        }

        public String toString()
        {
            return String.format("H: %s, S: %s, L %s", new Object[] {
                mHue, mSaturation, mLightness
            });
        }

        private Range mHue;
        private Range mLightness;
        private Range mSaturation;

        public ColorRange(Range range, Range range1, Range range2)
        {
            mHue = range;
            mSaturation = range1;
            mLightness = range2;
        }
    }

    public static class ConfigParser
    {

        private void parseBlacklist(XmlPullParser xmlpullparser)
            throws XmlPullParserException, IOException
        {
            xmlpullparser.require(2, null, "blacklist");
            do
            {
                if(xmlpullparser.next() == 3)
                    break;
                if(xmlpullparser.getEventType() == 2)
                {
                    String s = xmlpullparser.getName();
                    if(s.equals("range"))
                    {
                        mBlacklistedColors.add(readRange(xmlpullparser));
                        xmlpullparser.next();
                    } else
                    {
                        throw new XmlPullParserException((new StringBuilder()).append("Invalid tag: ").append(s).toString(), xmlpullparser, null);
                    }
                }
            } while(true);
        }

        private void parsePalettes(XmlPullParser xmlpullparser)
            throws XmlPullParserException, IOException
        {
            xmlpullparser.require(2, null, "palettes");
            do
            {
                if(xmlpullparser.next() == 3)
                    break;
                if(xmlpullparser.getEventType() == 2)
                {
                    String s = xmlpullparser.getName();
                    if(s.equals("palette"))
                    {
                        mTonalPalettes.add(readPalette(xmlpullparser));
                        xmlpullparser.next();
                    } else
                    {
                        throw new XmlPullParserException((new StringBuilder()).append("Invalid tag: ").append(s).toString());
                    }
                }
            } while(true);
        }

        private float[] readFloatArray(String s)
            throws IOException, XmlPullParserException
        {
            String as[] = s.replaceAll(" ", "").replaceAll("\n", "").split(",");
            s = new float[as.length];
            for(int i = 0; i < as.length; i++)
                s[i] = Float.parseFloat(as[i]);

            return s;
        }

        private TonalPalette readPalette(XmlPullParser xmlpullparser)
            throws XmlPullParserException, IOException
        {
            xmlpullparser.require(2, null, "palette");
            float af[] = readFloatArray(xmlpullparser.getAttributeValue(null, "h"));
            float af1[] = readFloatArray(xmlpullparser.getAttributeValue(null, "s"));
            float af2[];
            for(af2 = readFloatArray(xmlpullparser.getAttributeValue(null, "l")); af == null || af1 == null || af2 == null;)
                throw new XmlPullParserException("Incomplete range tag.", xmlpullparser, null);

            return new TonalPalette(af, af1, af2);
        }

        private ColorRange readRange(XmlPullParser xmlpullparser)
            throws XmlPullParserException, IOException
        {
            xmlpullparser.require(2, null, "range");
            float af[] = readFloatArray(xmlpullparser.getAttributeValue(null, "h"));
            float af1[] = readFloatArray(xmlpullparser.getAttributeValue(null, "s"));
            float af2[];
            for(af2 = readFloatArray(xmlpullparser.getAttributeValue(null, "l")); af == null || af1 == null || af2 == null;)
                throw new XmlPullParserException("Incomplete range tag.", xmlpullparser, null);

            return new ColorRange(new Range(Float.valueOf(af[0]), Float.valueOf(af[1])), new Range(Float.valueOf(af1[0]), Float.valueOf(af1[1])), new Range(Float.valueOf(af2[0]), Float.valueOf(af2[1])));
        }

        public ArrayList getBlacklistedColors()
        {
            return mBlacklistedColors;
        }

        public ArrayList getTonalPalettes()
        {
            return mTonalPalettes;
        }

        private final ArrayList mBlacklistedColors;
        private final ArrayList mTonalPalettes;

        public ConfigParser(Context context)
        {
            mTonalPalettes = new ArrayList();
            mBlacklistedColors = new ArrayList();
            int i;
            String s;
            try
            {
                context = context.getResources().getXml(0x1170004);
                i = context.getEventType();
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                throw new RuntimeException(context);
            }
            if(i == 1)
                break MISSING_BLOCK_LABEL_173;
            if(i != 0 && i != 3)
                break; /* Loop/switch isn't completed */
_L5:
            i = context.next();
            if(true) goto _L2; else goto _L1
_L2:
            break MISSING_BLOCK_LABEL_43;
_L1:
            if(i != 2)
                break MISSING_BLOCK_LABEL_123;
            s = context.getName();
            if(!s.equals("palettes")) goto _L4; else goto _L3
_L3:
            parsePalettes(context);
              goto _L5
_L4:
            if(!s.equals("blacklist")) goto _L5; else goto _L6
_L6:
            parseBlacklist(context);
              goto _L5
            XmlPullParserException xmlpullparserexception = JVM INSTR new #16  <Class XmlPullParserException>;
            StringBuilder stringbuilder = JVM INSTR new #78  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            xmlpullparserexception.XmlPullParserException(stringbuilder.append("Invalid XML event ").append(i).append(" - ").append(context.getName()).toString(), context, null);
            throw xmlpullparserexception;
        }
    }

    static class TonalPalette
    {

        final float h[];
        final float l[];
        final float maxHue;
        final float minHue;
        final float s[];

        TonalPalette(float af[], float af1[], float af2[])
        {
            if(af.length != af1.length || af1.length != af2.length)
                throw new IllegalArgumentException((new StringBuilder()).append("All arrays should have the same size. h: ").append(Arrays.toString(af)).append(" s: ").append(Arrays.toString(af1)).append(" l: ").append(Arrays.toString(af2)).toString());
            h = af;
            s = af1;
            l = af2;
            float f = (1.0F / 0.0F);
            float f1 = (-1.0F / 0.0F);
            int i = 0;
            for(int j = af.length; i < j; i++)
            {
                float f2 = af[i];
                f = Math.min(f2, f);
                f1 = Math.max(f2, f1);
            }

            minHue = f;
            maxHue = f1;
        }
    }


    public Tonal(Context context)
    {
        mTmpHSL = new float[3];
        context = new ConfigParser(context);
        mTonalPalettes = context.getTonalPalettes();
        mBlacklistedColors = context.getBlacklistedColors();
        mGreyPalette = (TonalPalette)mTonalPalettes.get(0);
        mTonalPalettes.remove(0);
    }

    public static void applyFallback(WallpaperColors wallpapercolors, com.android.internal.colorextraction.ColorExtractor.GradientColors gradientcolors)
    {
        boolean flag;
        int i;
        int j;
        if(wallpapercolors != null)
        {
            if((wallpapercolors.getColorHints() & 1) != 0)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = false;
        }
        if(flag)
            i = 0xffe0e0e0;
        else
            i = 0xff212121;
        if(flag)
            j = 0xff9e9e9e;
        else
            j = 0xff000000;
        gradientcolors.setMainColor(i);
        gradientcolors.setSecondaryColor(j);
        gradientcolors.setSupportsDarkText(flag);
    }

    private void applyFallback(WallpaperColors wallpapercolors, com.android.internal.colorextraction.ColorExtractor.GradientColors gradientcolors, com.android.internal.colorextraction.ColorExtractor.GradientColors gradientcolors1, com.android.internal.colorextraction.ColorExtractor.GradientColors gradientcolors2)
    {
        applyFallback(wallpapercolors, gradientcolors);
        applyFallback(wallpapercolors, gradientcolors1);
        applyFallback(wallpapercolors, gradientcolors2);
    }

    private static int bestFit(TonalPalette tonalpalette, float f, float f1, float f2)
    {
        int i = -1;
        float f3 = (1.0F / 0.0F);
        for(int j = 0; j < tonalpalette.h.length;)
        {
            float f4 = Math.abs(f - tonalpalette.h[j]) * 1.0F + Math.abs(f1 - tonalpalette.s[j]) * 1.0F + Math.abs(f2 - tonalpalette.l[j]) * 10F;
            float f5 = f3;
            if(f4 < f3)
            {
                f5 = f4;
                i = j;
            }
            j++;
            f3 = f5;
        }

        return i;
    }

    private TonalPalette findTonalPalette(float f, float f1)
    {
        TonalPalette tonalpalette;
        float f2;
        int i;
        int j;
        if(f1 < 0.05F)
            return mGreyPalette;
        tonalpalette = null;
        f2 = (1.0F / 0.0F);
        i = mTonalPalettes.size();
        j = 0;
_L2:
        TonalPalette tonalpalette1;
        TonalPalette tonalpalette2;
        tonalpalette1 = tonalpalette;
        if(j < i)
        {
            tonalpalette1 = (TonalPalette)mTonalPalettes.get(j);
            break MISSING_BLOCK_LABEL_54;
        }
        do
            return tonalpalette1;
        while(f >= tonalpalette1.minHue && f <= tonalpalette1.maxHue || tonalpalette1.maxHue > 1.0F && f >= 0.0F && f <= fract(tonalpalette1.maxHue) || tonalpalette1.minHue < 0.0F && f >= fract(tonalpalette1.minHue) && f <= 1.0F);
        if(f > tonalpalette1.minHue || tonalpalette1.minHue - f >= f2)
            break; /* Loop/switch isn't completed */
        tonalpalette2 = tonalpalette1;
        f1 = tonalpalette1.minHue - f;
_L3:
        j++;
        tonalpalette = tonalpalette2;
        f2 = f1;
        if(true) goto _L2; else goto _L1
_L1:
        if(f >= tonalpalette1.maxHue && f - tonalpalette1.maxHue < f2)
        {
            tonalpalette2 = tonalpalette1;
            f1 = f - tonalpalette1.maxHue;
        } else
        if(tonalpalette1.maxHue > 1.0F && f >= fract(tonalpalette1.maxHue) && f - fract(tonalpalette1.maxHue) < f2)
        {
            tonalpalette2 = tonalpalette1;
            f1 = f - fract(tonalpalette1.maxHue);
        } else
        {
            tonalpalette2 = tonalpalette;
            f1 = f2;
            if(tonalpalette1.minHue < 0.0F)
            {
                tonalpalette2 = tonalpalette;
                f1 = f2;
                if(f <= fract(tonalpalette1.minHue))
                {
                    tonalpalette2 = tonalpalette;
                    f1 = f2;
                    if(fract(tonalpalette1.minHue) - f < f2)
                    {
                        tonalpalette2 = tonalpalette1;
                        f1 = fract(tonalpalette1.minHue) - f;
                    }
                }
            }
        }
          goto _L3
        if(true) goto _L2; else goto _L4
_L4:
    }

    private static float[] fit(float af[], float f, int i, float f1, float f2)
    {
        float af1[] = new float[af.length];
        float f3 = af[i];
        for(i = 0; i < af.length; i++)
            af1[i] = MathUtils.constrain(af[i] + (f - f3), f1, f2);

        return af1;
    }

    private static float fract(float f)
    {
        return f - (float)Math.floor(f);
    }

    private int getColorInt(int i, float af[], float af1[], float af2[])
    {
        mTmpHSL[0] = fract(af[i]) * 360F;
        mTmpHSL[1] = af1[i];
        mTmpHSL[2] = af2[i];
        return ColorUtils.HSLToColor(mTmpHSL);
    }

    private boolean isBlacklisted(float af[])
    {
        for(int i = mBlacklistedColors.size() - 1; i >= 0; i--)
            if(((ColorRange)mBlacklistedColors.get(i)).containsColor(af[0], af[1], af[2]))
                return true;

        return false;
    }

    private boolean runTonalExtraction(WallpaperColors wallpapercolors, com.android.internal.colorextraction.ColorExtractor.GradientColors gradientcolors, com.android.internal.colorextraction.ColorExtractor.GradientColors gradientcolors1, com.android.internal.colorextraction.ColorExtractor.GradientColors gradientcolors2)
    {
        if(wallpapercolors == null)
            return false;
        Object obj = wallpapercolors.getMainColors();
        int i = ((List) (obj)).size();
        int j = wallpapercolors.getColorHints();
        boolean flag;
        if((j & 1) != 0)
            flag = true;
        else
            flag = false;
        if((j & 4) != 0)
            j = 1;
        else
            j = 0;
        if(i == 0)
            return false;
        float af[] = null;
        float af1[] = new float[3];
        int k = 0;
label0:
        do
        {
label1:
            {
                wallpapercolors = af;
                if(k < i)
                {
                    wallpapercolors = (Color)((List) (obj)).get(k);
                    int l = wallpapercolors.toArgb();
                    ColorUtils.RGBToHSL(Color.red(l), Color.green(l), Color.blue(l), af1);
                    if(j && !(isBlacklisted(af1) ^ true))
                        break label1;
                }
                if(wallpapercolors == null)
                    return false;
                break label0;
            }
            k++;
        } while(true);
        j = wallpapercolors.toArgb();
        ColorUtils.RGBToHSL(Color.red(j), Color.green(j), Color.blue(j), af1);
        af1[0] = af1[0] / 360F;
        obj = findTonalPalette(af1[0], af1[1]);
        if(obj == null)
        {
            Log.w("Tonal", "Could not find a tonal palette!");
            return false;
        }
        i = bestFit(((TonalPalette) (obj)), af1[0], af1[1], af1[2]);
        if(i == -1)
        {
            Log.w("Tonal", "Could not find best fit!");
            return false;
        }
        wallpapercolors = fit(((TonalPalette) (obj)).h, af1[0], i, (-1.0F / 0.0F), (1.0F / 0.0F));
        af = fit(((TonalPalette) (obj)).s, af1[1], i, 0.0F, 1.0F);
        af1 = fit(((TonalPalette) (obj)).l, af1[2], i, 0.0F, 1.0F);
        obj = new StringBuilder((new StringBuilder()).append("Tonal Palette - index: ").append(i).append(". Main color: ").append(Integer.toHexString(getColorInt(i, wallpapercolors, af, af1))).append("\nColors: ").toString());
        for(j = 0; j < wallpapercolors.length; j++)
        {
            ((StringBuilder) (obj)).append(Integer.toHexString(getColorInt(j, wallpapercolors, af, af1)));
            if(j < wallpapercolors.length - 1)
                ((StringBuilder) (obj)).append(", ");
        }

        Log.d("Tonal", ((StringBuilder) (obj)).toString());
        j = getColorInt(i, wallpapercolors, af, af1);
        ColorUtils.colorToHSL(j, mTmpHSL);
        float f = mTmpHSL[2];
        ColorUtils.colorToHSL(0xffe0e0e0, mTmpHSL);
        if(f > mTmpHSL[2])
            return false;
        ColorUtils.colorToHSL(0xff212121, mTmpHSL);
        if(f < mTmpHSL[2])
            return false;
        gradientcolors.setMainColor(j);
        if(i >= 2)
            j = -2;
        else
            j = 2;
        gradientcolors.setSecondaryColor(getColorInt(i + j, wallpapercolors, af, af1));
        if(flag)
            j = wallpapercolors.length - 1;
        else
        if(i < 2)
            j = 0;
        else
            j = Math.min(i, 3);
        if(j >= 2)
            k = -2;
        else
            k = 2;
        gradientcolors1.setMainColor(getColorInt(j, wallpapercolors, af, af1));
        gradientcolors1.setSecondaryColor(getColorInt(j + k, wallpapercolors, af, af1));
        if(flag)
            j = wallpapercolors.length - 1;
        else
        if(i < 2)
            j = 0;
        else
            j = 2;
        if(j >= 2)
            k = -2;
        else
            k = 2;
        gradientcolors2.setMainColor(getColorInt(j, wallpapercolors, af, af1));
        gradientcolors2.setSecondaryColor(getColorInt(j + k, wallpapercolors, af, af1));
        gradientcolors.setSupportsDarkText(flag);
        gradientcolors1.setSupportsDarkText(flag);
        gradientcolors2.setSupportsDarkText(flag);
        Log.d("Tonal", (new StringBuilder()).append("Gradients: \n\tNormal ").append(gradientcolors).append("\n\tDark ").append(gradientcolors1).append("\n\tExtra dark: ").append(gradientcolors2).toString());
        return true;
    }

    public void extractInto(WallpaperColors wallpapercolors, com.android.internal.colorextraction.ColorExtractor.GradientColors gradientcolors, com.android.internal.colorextraction.ColorExtractor.GradientColors gradientcolors1, com.android.internal.colorextraction.ColorExtractor.GradientColors gradientcolors2)
    {
        if(!runTonalExtraction(wallpapercolors, gradientcolors, gradientcolors1, gradientcolors2))
            applyFallback(wallpapercolors, gradientcolors, gradientcolors1, gradientcolors2);
    }

    public List getBlacklistedColors()
    {
        return mBlacklistedColors;
    }

    private static final boolean DEBUG = true;
    private static final float FIT_WEIGHT_H = 1F;
    private static final float FIT_WEIGHT_L = 10F;
    private static final float FIT_WEIGHT_S = 1F;
    public static final int MAIN_COLOR_DARK = 0xff212121;
    public static final int MAIN_COLOR_LIGHT = 0xffe0e0e0;
    public static final int SECONDARY_COLOR_DARK = 0xff000000;
    public static final int SECONDARY_COLOR_LIGHT = 0xff9e9e9e;
    private static final String TAG = "Tonal";
    private final ArrayList mBlacklistedColors;
    private final TonalPalette mGreyPalette;
    private float mTmpHSL[];
    private final ArrayList mTonalPalettes;
}
