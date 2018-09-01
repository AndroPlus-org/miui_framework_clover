// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;

import android.util.Pair;
import java.util.*;
import java.util.function.DoubleUnaryOperator;

// Referenced classes of package android.graphics:
//            Paint, Canvas, Typeface, Rect, 
//            Path, RectF, Bitmap

public abstract class ColorSpace
{
    public static final class Adaptation extends Enum
    {

        public static Adaptation valueOf(String s)
        {
            return (Adaptation)Enum.valueOf(android/graphics/ColorSpace$Adaptation, s);
        }

        public static Adaptation[] values()
        {
            return $VALUES;
        }

        private static final Adaptation $VALUES[];
        public static final Adaptation BRADFORD;
        public static final Adaptation CIECAT02;
        public static final Adaptation VON_KRIES;
        final float mTransform[];

        static 
        {
            BRADFORD = new Adaptation("BRADFORD", 0, new float[] {
                0.8951F, -0.7502F, 0.0389F, 0.2664F, 1.7135F, -0.0685F, -0.1614F, 0.0367F, 1.0296F
            });
            VON_KRIES = new Adaptation("VON_KRIES", 1, new float[] {
                0.40024F, -0.2263F, 0.0F, 0.7076F, 1.16532F, 0.0F, -0.08081F, 0.0457F, 0.91822F
            });
            CIECAT02 = new Adaptation("CIECAT02", 2, new float[] {
                0.7328F, -0.7036F, 0.003F, 0.4296F, 1.6975F, 0.0136F, -0.1624F, 0.0061F, 0.9834F
            });
            $VALUES = (new Adaptation[] {
                BRADFORD, VON_KRIES, CIECAT02
            });
        }

        private Adaptation(String s, int i, float af[])
        {
            super(s, i);
            mTransform = af;
        }
    }

    public static class Connector
    {

        private static float[] computeTransform(ColorSpace colorspace, ColorSpace colorspace1, RenderIntent renderintent)
        {
            if(renderintent != RenderIntent.ABSOLUTE)
                return null;
            boolean flag;
            boolean flag1;
            if(colorspace.getModel() == Model.RGB)
                flag = true;
            else
                flag = false;
            if(colorspace1.getModel() == Model.RGB)
                flag1 = true;
            else
                flag1 = false;
            if(flag && flag1)
                return null;
            if(flag || flag1)
            {
                if(!flag)
                    colorspace = colorspace1;
                colorspace1 = (Rgb)colorspace;
                if(flag)
                    colorspace = ColorSpace._2D_wrap10(Rgb._2D_get5(colorspace1));
                else
                    colorspace = ColorSpace._2D_get0();
                if(flag1)
                    colorspace1 = ColorSpace._2D_wrap10(Rgb._2D_get5(colorspace1));
                else
                    colorspace1 = ColorSpace._2D_get0();
                return (new float[] {
                    colorspace[0] / colorspace1[0], colorspace[1] / colorspace1[1], colorspace[2] / colorspace1[2]
                });
            } else
            {
                return null;
            }
        }

        static Connector identity(ColorSpace colorspace)
        {
            return new Connector(colorspace, colorspace, RenderIntent.RELATIVE) {

                public float[] transform(float af[])
                {
                    return af;
                }

            }
;
        }

        public ColorSpace getDestination()
        {
            return mDestination;
        }

        public RenderIntent getRenderIntent()
        {
            return mIntent;
        }

        public ColorSpace getSource()
        {
            return mSource;
        }

        public float[] transform(float f, float f1, float f2)
        {
            return transform(new float[] {
                f, f1, f2
            });
        }

        public float[] transform(float af[])
        {
            af = mTransformSource.toXyz(af);
            if(mTransform != null)
            {
                af[0] = af[0] * mTransform[0];
                af[1] = af[1] * mTransform[1];
                af[2] = af[2] * mTransform[2];
            }
            return mTransformDestination.fromXyz(af);
        }

        private final ColorSpace mDestination;
        private final RenderIntent mIntent;
        private final ColorSpace mSource;
        private final float mTransform[];
        private final ColorSpace mTransformDestination;
        private final ColorSpace mTransformSource;

        Connector(ColorSpace colorspace, ColorSpace colorspace1, RenderIntent renderintent)
        {
            ColorSpace colorspace2;
            ColorSpace colorspace3;
            if(colorspace.getModel() == Model.RGB)
                colorspace2 = ColorSpace.adapt(colorspace, ColorSpace._2D_get0());
            else
                colorspace2 = colorspace;
            if(colorspace1.getModel() == Model.RGB)
                colorspace3 = ColorSpace.adapt(colorspace1, ColorSpace._2D_get0());
            else
                colorspace3 = colorspace1;
            this(colorspace, colorspace1, colorspace2, colorspace3, renderintent, computeTransform(colorspace, colorspace1, renderintent));
        }

        private Connector(ColorSpace colorspace, ColorSpace colorspace1, ColorSpace colorspace2, ColorSpace colorspace3, RenderIntent renderintent, float af[])
        {
            mSource = colorspace;
            mDestination = colorspace1;
            mTransformSource = colorspace2;
            mTransformDestination = colorspace3;
            mIntent = renderintent;
            mTransform = af;
        }

        Connector(ColorSpace colorspace, ColorSpace colorspace1, ColorSpace colorspace2, ColorSpace colorspace3, RenderIntent renderintent, float af[], Connector connector)
        {
            this(colorspace, colorspace1, colorspace2, colorspace3, renderintent, af);
        }
    }

    private static class Connector.Rgb extends Connector
    {

        private static float[] computeTransform(Rgb rgb, Rgb rgb1, RenderIntent renderintent)
        {
            if(ColorSpace._2D_wrap0(Rgb._2D_get5(rgb), Rgb._2D_get5(rgb1)))
                return ColorSpace._2D_wrap9(Rgb._2D_get2(rgb1), Rgb._2D_get4(rgb));
            float af[] = Rgb._2D_get4(rgb);
            float af1[] = Rgb._2D_get2(rgb1);
            float af2[] = ColorSpace._2D_wrap10(Rgb._2D_get5(rgb));
            float af3[] = ColorSpace._2D_wrap10(Rgb._2D_get5(rgb1));
            if(!ColorSpace._2D_wrap0(Rgb._2D_get5(rgb), ColorSpace.ILLUMINANT_D50))
                af = ColorSpace._2D_wrap9(ColorSpace._2D_wrap5(Adaptation.BRADFORD.mTransform, af2, Arrays.copyOf(ColorSpace._2D_get0(), 3)), Rgb._2D_get4(rgb));
            rgb = af1;
            if(!ColorSpace._2D_wrap0(Rgb._2D_get5(rgb1), ColorSpace.ILLUMINANT_D50))
                rgb = ColorSpace._2D_wrap6(ColorSpace._2D_wrap9(ColorSpace._2D_wrap5(Adaptation.BRADFORD.mTransform, af3, Arrays.copyOf(ColorSpace._2D_get0(), 3)), Rgb._2D_get4(rgb1)));
            rgb1 = af;
            if(renderintent == RenderIntent.ABSOLUTE)
                rgb1 = ColorSpace._2D_wrap7(new float[] {
                    af2[0] / af3[0], af2[1] / af3[1], af2[2] / af3[2]
                }, af);
            return ColorSpace._2D_wrap9(rgb, rgb1);
        }

        public float[] transform(float af[])
        {
            af[0] = (float)Rgb._2D_get0(mSource).applyAsDouble(af[0]);
            af[1] = (float)Rgb._2D_get0(mSource).applyAsDouble(af[1]);
            af[2] = (float)Rgb._2D_get0(mSource).applyAsDouble(af[2]);
            ColorSpace._2D_wrap8(mTransform, af);
            af[0] = (float)Rgb._2D_get1(mDestination).applyAsDouble(af[0]);
            af[1] = (float)Rgb._2D_get1(mDestination).applyAsDouble(af[1]);
            af[2] = (float)Rgb._2D_get1(mDestination).applyAsDouble(af[2]);
            return af;
        }

        private final Rgb mDestination;
        private final Rgb mSource;
        private final float mTransform[];

        Connector.Rgb(Rgb rgb, Rgb rgb1, RenderIntent renderintent)
        {
            super(rgb, rgb1, rgb, rgb1, renderintent, null, null);
            mSource = rgb;
            mDestination = rgb1;
            mTransform = computeTransform(rgb, rgb1, renderintent);
        }
    }

    private static final class Lab extends ColorSpace
    {

        private static float clamp(float f, float f1, float f2)
        {
            if(f >= f1)
                if(f > f2)
                    f1 = f2;
                else
                    f1 = f;
            return f1;
        }

        public float[] fromXyz(float af[])
        {
            float f = af[0] / ColorSpace._2D_get0()[0];
            float f1 = af[1] / ColorSpace._2D_get0()[1];
            float f2 = af[2] / ColorSpace._2D_get0()[2];
            if(f > 0.008856452F)
                f = (float)Math.pow(f, 0.33333333333333331D);
            else
                f = 7.787037F * f + 0.137931F;
            if(f1 > 0.008856452F)
                f1 = (float)Math.pow(f1, 0.33333333333333331D);
            else
                f1 = 7.787037F * f1 + 0.137931F;
            if(f2 > 0.008856452F)
                f2 = (float)Math.pow(f2, 0.33333333333333331D);
            else
                f2 = 7.787037F * f2 + 0.137931F;
            af[0] = clamp(116F * f1 - 16F, 0.0F, 100F);
            af[1] = clamp(500F * (f - f1), -128F, 128F);
            af[2] = clamp(200F * (f1 - f2), -128F, 128F);
            return af;
        }

        public float getMaxValue(int i)
        {
            float f;
            if(i == 0)
                f = 100F;
            else
                f = 128F;
            return f;
        }

        public float getMinValue(int i)
        {
            float f;
            if(i == 0)
                f = 0.0F;
            else
                f = -128F;
            return f;
        }

        public boolean isWideGamut()
        {
            return true;
        }

        public float[] toXyz(float af[])
        {
            af[0] = clamp(af[0], 0.0F, 100F);
            af[1] = clamp(af[1], -128F, 128F);
            af[2] = clamp(af[2], -128F, 128F);
            float f = (af[0] + 16F) / 116F;
            float f1 = f + af[1] * 0.002F;
            float f2 = f - af[2] * 0.005F;
            if(f1 > 0.2068966F)
                f1 = f1 * f1 * f1;
            else
                f1 = 0.1284185F * (f1 - 0.137931F);
            if(f > 0.2068966F)
                f = f * f * f;
            else
                f = 0.1284185F * (f - 0.137931F);
            if(f2 > 0.2068966F)
                f2 = f2 * f2 * f2;
            else
                f2 = 0.1284185F * (f2 - 0.137931F);
            af[0] = ColorSpace._2D_get0()[0] * f1;
            af[1] = ColorSpace._2D_get0()[1] * f;
            af[2] = ColorSpace._2D_get0()[2] * f2;
            return af;
        }

        private static final float A = 0.008856452F;
        private static final float B = 7.787037F;
        private static final float C = 0.137931F;
        private static final float D = 0.2068966F;

        private Lab(String s, int i)
        {
            super(s, Model.LAB, i, null);
        }

        Lab(String s, int i, Lab lab)
        {
            this(s, i);
        }
    }

    public static final class Model extends Enum
    {

        public static Model valueOf(String s)
        {
            return (Model)Enum.valueOf(android/graphics/ColorSpace$Model, s);
        }

        public static Model[] values()
        {
            return $VALUES;
        }

        public int getComponentCount()
        {
            return mComponentCount;
        }

        private static final Model $VALUES[];
        public static final Model CMYK;
        public static final Model LAB;
        public static final Model RGB;
        public static final Model XYZ;
        private final int mComponentCount;

        static 
        {
            RGB = new Model("RGB", 0, 3);
            XYZ = new Model("XYZ", 1, 3);
            LAB = new Model("LAB", 2, 3);
            CMYK = new Model("CMYK", 3, 4);
            $VALUES = (new Model[] {
                RGB, XYZ, LAB, CMYK
            });
        }

        private Model(String s, int i, int j)
        {
            super(s, i);
            mComponentCount = j;
        }
    }

    public static final class Named extends Enum
    {

        public static Named valueOf(String s)
        {
            return (Named)Enum.valueOf(android/graphics/ColorSpace$Named, s);
        }

        public static Named[] values()
        {
            return $VALUES;
        }

        private static final Named $VALUES[];
        public static final Named ACES;
        public static final Named ACESCG;
        public static final Named ADOBE_RGB;
        public static final Named BT2020;
        public static final Named BT709;
        public static final Named CIE_LAB;
        public static final Named CIE_XYZ;
        public static final Named DCI_P3;
        public static final Named DISPLAY_P3;
        public static final Named EXTENDED_SRGB;
        public static final Named LINEAR_EXTENDED_SRGB;
        public static final Named LINEAR_SRGB;
        public static final Named NTSC_1953;
        public static final Named PRO_PHOTO_RGB;
        public static final Named SMPTE_C;
        public static final Named SRGB;

        static 
        {
            SRGB = new Named("SRGB", 0);
            LINEAR_SRGB = new Named("LINEAR_SRGB", 1);
            EXTENDED_SRGB = new Named("EXTENDED_SRGB", 2);
            LINEAR_EXTENDED_SRGB = new Named("LINEAR_EXTENDED_SRGB", 3);
            BT709 = new Named("BT709", 4);
            BT2020 = new Named("BT2020", 5);
            DCI_P3 = new Named("DCI_P3", 6);
            DISPLAY_P3 = new Named("DISPLAY_P3", 7);
            NTSC_1953 = new Named("NTSC_1953", 8);
            SMPTE_C = new Named("SMPTE_C", 9);
            ADOBE_RGB = new Named("ADOBE_RGB", 10);
            PRO_PHOTO_RGB = new Named("PRO_PHOTO_RGB", 11);
            ACES = new Named("ACES", 12);
            ACESCG = new Named("ACESCG", 13);
            CIE_XYZ = new Named("CIE_XYZ", 14);
            CIE_LAB = new Named("CIE_LAB", 15);
            $VALUES = (new Named[] {
                SRGB, LINEAR_SRGB, EXTENDED_SRGB, LINEAR_EXTENDED_SRGB, BT709, BT2020, DCI_P3, DISPLAY_P3, NTSC_1953, SMPTE_C, 
                ADOBE_RGB, PRO_PHOTO_RGB, ACES, ACESCG, CIE_XYZ, CIE_LAB
            });
        }

        private Named(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class RenderIntent extends Enum
    {

        public static RenderIntent valueOf(String s)
        {
            return (RenderIntent)Enum.valueOf(android/graphics/ColorSpace$RenderIntent, s);
        }

        public static RenderIntent[] values()
        {
            return $VALUES;
        }

        private static final RenderIntent $VALUES[];
        public static final RenderIntent ABSOLUTE;
        public static final RenderIntent PERCEPTUAL;
        public static final RenderIntent RELATIVE;
        public static final RenderIntent SATURATION;

        static 
        {
            PERCEPTUAL = new RenderIntent("PERCEPTUAL", 0);
            RELATIVE = new RenderIntent("RELATIVE", 1);
            SATURATION = new RenderIntent("SATURATION", 2);
            ABSOLUTE = new RenderIntent("ABSOLUTE", 3);
            $VALUES = (new RenderIntent[] {
                PERCEPTUAL, RELATIVE, SATURATION, ABSOLUTE
            });
        }

        private RenderIntent(String s, int i)
        {
            super(s, i);
        }
    }

    public static class Renderer
    {

        private static void computeChromaticityMesh(float af[], int ai[])
        {
            ColorSpace colorspace = ColorSpace.get(Named.SRGB);
            float af1[] = new float[3];
            int i = 0;
            int j = 0;
            for(int k = 0; k < SPECTRUM_LOCUS_X.length;)
            {
                int l = k % (SPECTRUM_LOCUS_X.length - 1) + 1;
                float f = (float)Math.atan2((double)SPECTRUM_LOCUS_Y[k] - 0.33333333333333331D, (double)SPECTRUM_LOCUS_X[k] - 0.33333333333333331D);
                float f1 = (float)Math.atan2((double)SPECTRUM_LOCUS_Y[l] - 0.33333333333333331D, (double)SPECTRUM_LOCUS_X[l] - 0.33333333333333331D);
                float f2 = (float)Math.pow(sqr((double)SPECTRUM_LOCUS_X[k] - 0.33333333333333331D) + sqr((double)SPECTRUM_LOCUS_Y[k] - 0.33333333333333331D), 0.5D);
                float f3 = (float)Math.pow(sqr((double)SPECTRUM_LOCUS_X[l] - 0.33333333333333331D) + sqr((double)SPECTRUM_LOCUS_Y[l] - 0.33333333333333331D), 0.5D);
                boolean flag = true;
                l = j;
                for(j = ((flag) ? 1 : 0); j <= 32; j++)
                {
                    float f4 = (float)j / 32F;
                    float f5 = (float)(j - 1) / 32F;
                    double d = (double)f2 * Math.cos(f);
                    double d1 = (double)f2 * Math.sin(f);
                    double d2 = (double)f3 * Math.cos(f1);
                    double d3 = (double)f3 * Math.sin(f1);
                    float f6 = (float)((double)f4 * d + 0.33333333333333331D);
                    float f7 = (float)((double)f4 * d1 + 0.33333333333333331D);
                    float f8 = (float)((double)f5 * d + 0.33333333333333331D);
                    float f9 = (float)((double)f5 * d1 + 0.33333333333333331D);
                    float f10 = (float)((double)f5 * d2 + 0.33333333333333331D);
                    f5 = (float)((double)f5 * d3 + 0.33333333333333331D);
                    float f11 = (float)((double)f4 * d2 + 0.33333333333333331D);
                    f4 = (float)((double)f4 * d3 + 0.33333333333333331D);
                    ai[l] = computeColor(af1, f6, f7, 1.0F - f6 - f7, colorspace);
                    ai[l + 1] = computeColor(af1, f8, f9, 1.0F - f8 - f9, colorspace);
                    ai[l + 2] = computeColor(af1, f10, f5, 1.0F - f10 - f5, colorspace);
                    ai[l + 3] = ai[l];
                    ai[l + 4] = ai[l + 2];
                    ai[l + 5] = computeColor(af1, f11, f4, 1.0F - f11 - f4, colorspace);
                    l += 6;
                    int i1 = i + 1;
                    af[i] = f6;
                    int j1 = i1 + 1;
                    af[i1] = f7;
                    i = j1 + 1;
                    af[j1] = f8;
                    i1 = i + 1;
                    af[i] = f9;
                    i = i1 + 1;
                    af[i1] = f10;
                    j1 = i + 1;
                    af[i] = f5;
                    i1 = j1 + 1;
                    af[j1] = f6;
                    i = i1 + 1;
                    af[i1] = f7;
                    i1 = i + 1;
                    af[i] = f10;
                    i = i1 + 1;
                    af[i1] = f5;
                    i1 = i + 1;
                    af[i] = f11;
                    i = i1 + 1;
                    af[i1] = f4;
                }

                k++;
                j = l;
            }

        }

        private static int computeColor(float af[], float f, float f1, float f2, ColorSpace colorspace)
        {
            af[0] = f;
            af[1] = f1;
            af[2] = f2;
            colorspace.fromXyz(af);
            return ((int)(af[0] * 255F) & 0xff) << 16 | 0xff000000 | ((int)(af[1] * 255F) & 0xff) << 8 | (int)(af[2] * 255F) & 0xff;
        }

        private void drawBox(Canvas canvas, int i, int j, Paint paint, Path path)
        {
            byte byte0 = 10;
            float f = 1.0F;
            if(mUcs)
            {
                byte0 = 7;
                f = 1.5F;
            }
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(2.0F);
            paint.setColor(0xffc0c0c0);
            for(int k = 1; k < byte0 - 1; k++)
            {
                float f1 = (float)k / 10F;
                float f4 = (float)i * f1 * f;
                f1 = (float)j - (float)j * f1 * f;
                canvas.drawLine(0.0F, f1, 0.9F * (float)i, f1, paint);
                canvas.drawLine(f4, j, f4, 0.1F * (float)j, paint);
            }

            paint.setStrokeWidth(4F);
            paint.setColor(0xff000000);
            for(int l = 1; l < byte0 - 1; l++)
            {
                float f2 = (float)l / 10F;
                float f5 = (float)i * f2 * f;
                f2 = (float)j - (float)j * f2 * f;
                canvas.drawLine(0.0F, f2, (float)i / 100F, f2, paint);
                canvas.drawLine(f5, j, f5, (float)j - (float)j / 100F, paint);
            }

            paint.setStyle(Paint.Style.FILL);
            paint.setTextSize(36F);
            paint.setTypeface(Typeface.create("sans-serif-light", 0));
            Rect rect = new Rect();
            for(int i1 = 1; i1 < byte0 - 1; i1++)
            {
                String s = (new StringBuilder()).append("0.").append(i1).toString();
                paint.getTextBounds(s, 0, s.length(), rect);
                float f3 = (float)i1 / 10F;
                float f7 = i;
                float f6 = j;
                float f8 = j;
                canvas.drawText(s, (float)i * -0.05F + 10F, (float)rect.height() / 2.0F + (f6 - f8 * f3 * f), paint);
                canvas.drawText(s, f7 * f3 * f - (float)rect.width() / 2.0F, rect.height() + j + 16, paint);
            }

            paint.setStyle(Paint.Style.STROKE);
            path.moveTo(0.0F, j);
            path.lineTo((float)i * 0.9F, j);
            path.lineTo((float)i * 0.9F, (float)j * 0.1F);
            path.lineTo(0.0F, (float)j * 0.1F);
            path.close();
            canvas.drawPath(path, paint);
        }

        private void drawGamuts(Canvas canvas, int i, int j, Paint paint, Path path, float af[], float af1[])
        {
            float f;
            Iterator iterator;
            if(mUcs)
                f = 1.5F;
            else
                f = 1.0F;
            f = 4F / f;
            iterator = mColorSpaces.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                Pair pair = (Pair)iterator.next();
                Object obj = (ColorSpace)pair.first;
                int k = ((Integer)pair.second).intValue();
                if(((ColorSpace) (obj)).getModel() == Model.RGB)
                {
                    obj = (Rgb)obj;
                    getPrimaries(((Rgb) (obj)), af, mUcs);
                    path.rewind();
                    path.moveTo((float)i * af[0], (float)j - (float)j * af[1]);
                    path.lineTo((float)i * af[2], (float)j - (float)j * af[3]);
                    path.lineTo((float)i * af[4], (float)j - (float)j * af[5]);
                    path.close();
                    paint.setStyle(Paint.Style.STROKE);
                    paint.setColor(k);
                    canvas.drawPath(path, paint);
                    if(mShowWhitePoint)
                    {
                        ((Rgb) (obj)).getWhitePoint(af1);
                        if(mUcs)
                            ColorSpace._2D_wrap11(af1);
                        paint.setStyle(Paint.Style.FILL);
                        paint.setColor(k);
                        canvas.drawCircle((float)i * af1[0], (float)j - (float)j * af1[1], f, paint);
                    }
                }
            } while(true);
        }

        private void drawLocus(Canvas canvas, int i, int j, Paint paint, Path path, float af[])
        {
            float af1[];
            int ai[];
            af1 = new float[SPECTRUM_LOCUS_X.length * 32 * 6 * 2];
            ai = new int[af1.length];
            computeChromaticityMesh(af1, ai);
            if(mUcs)
                ColorSpace._2D_wrap11(af1);
            for(int k = 0; k < af1.length; k += 2)
            {
                af1[k] = af1[k] * (float)i;
                af1[k + 1] = (float)j - af1[k + 1] * (float)j;
            }

            if(!mClip || mColorSpaces.size() <= 0) goto _L2; else goto _L1
_L1:
            Iterator iterator = mColorSpaces.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                ColorSpace colorspace = (ColorSpace)((Pair)iterator.next()).first;
                if(colorspace.getModel() != Model.RGB)
                    continue;
                getPrimaries((Rgb)colorspace, af, mUcs);
                break;
            } while(true);
            path.rewind();
            path.moveTo((float)i * af[0], (float)j - (float)j * af[1]);
            path.lineTo((float)i * af[2], (float)j - (float)j * af[3]);
            path.lineTo((float)i * af[4], (float)j - (float)j * af[5]);
            path.close();
            af = new int[ai.length];
            Arrays.fill(af, 0xff6c6c6c);
            canvas.drawVertices(Canvas.VertexMode.TRIANGLES, af1.length, af1, 0, null, 0, af, 0, null, 0, 0, paint);
            canvas.save();
            canvas.clipPath(path);
            canvas.drawVertices(Canvas.VertexMode.TRIANGLES, af1.length, af1, 0, null, 0, ai, 0, null, 0, 0, paint);
            canvas.restore();
_L4:
            j = 372;
            path.reset();
            path.moveTo(af1[372], af1[373]);
            for(i = 2; i < SPECTRUM_LOCUS_X.length; i++)
            {
                j += 384;
                path.lineTo(af1[j], af1[j + 1]);
            }

            break; /* Loop/switch isn't completed */
_L2:
            canvas.drawVertices(Canvas.VertexMode.TRIANGLES, af1.length, af1, 0, null, 0, ai, 0, null, 0, 0, paint);
            if(true) goto _L4; else goto _L3
_L3:
            path.close();
            float f;
            if(mUcs)
                f = 1.5F;
            else
                f = 1.0F;
            paint.setStrokeWidth(4F / f);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(0xff000000);
            canvas.drawPath(path, paint);
            return;
        }

        private void drawPoints(Canvas canvas, int i, int j, Paint paint)
        {
            paint.setStyle(Paint.Style.FILL);
            float f;
            float af[];
            float af1[];
            if(mUcs)
                f = 1.5F;
            else
                f = 1.0F;
            f = 4F / f;
            af = new float[3];
            af1 = new float[2];
            for(Iterator iterator = mPoints.iterator(); iterator.hasNext(); canvas.drawCircle((float)i * af1[0], (float)j - (float)j * af1[1], f, paint))
            {
                Point point = (Point)iterator.next();
                af[0] = point.mRgb[0];
                af[1] = point.mRgb[1];
                af[2] = point.mRgb[2];
                point.mColorSpace.toXyz(af);
                paint.setColor(point.mColor);
                float f1 = af[0] + af[1] + af[2];
                af1[0] = af[0] / f1;
                af1[1] = af[1] / f1;
                if(mUcs)
                    ColorSpace._2D_wrap11(af1);
            }

        }

        private static void getPrimaries(Rgb rgb, float af[], boolean flag)
        {
            if(rgb.equals(ColorSpace.get(Named.EXTENDED_SRGB)) || rgb.equals(ColorSpace.get(Named.LINEAR_EXTENDED_SRGB)))
            {
                af[0] = 1.41F;
                af[1] = 0.33F;
                af[2] = 0.27F;
                af[3] = 1.24F;
                af[4] = -0.23F;
                af[5] = -0.57F;
            } else
            {
                rgb.getPrimaries(af);
            }
            if(flag)
                ColorSpace._2D_wrap11(af);
        }

        private void setTransform(Canvas canvas, int i, int j, float af[])
        {
            RectF rectf = new RectF();
            Iterator iterator = mColorSpaces.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                ColorSpace colorspace = (ColorSpace)((Pair)iterator.next()).first;
                if(colorspace.getModel() == Model.RGB)
                {
                    getPrimaries((Rgb)colorspace, af, mUcs);
                    rectf.left = Math.min(rectf.left, af[4]);
                    rectf.top = Math.min(rectf.top, af[5]);
                    rectf.right = Math.max(rectf.right, af[0]);
                    rectf.bottom = Math.max(rectf.bottom, af[3]);
                }
            } while(true);
            float f;
            float f1;
            if(mUcs)
                f = 0.6F;
            else
                f = 0.9F;
            rectf.left = Math.min(0.0F, rectf.left);
            rectf.top = Math.min(0.0F, rectf.top);
            rectf.right = Math.max(f, rectf.right);
            rectf.bottom = Math.max(f, rectf.bottom);
            f1 = Math.min(f / rectf.width(), f / rectf.height());
            canvas.scale((float)mSize / 1440F, (float)mSize / 1440F);
            canvas.scale(f1, f1);
            canvas.translate(((rectf.width() - f) * (float)i) / 2.0F, ((rectf.height() - f) * (float)j) / 2.0F);
            canvas.translate((float)i * 0.05F, (float)j * -0.05F);
        }

        private void setUcsTransform(Canvas canvas, int i)
        {
            if(mUcs)
            {
                canvas.translate(0.0F, (float)i - (float)i * 1.5F);
                canvas.scale(1.5F, 1.5F);
            }
        }

        private static double sqr(double d)
        {
            return d * d;
        }

        public Renderer add(ColorSpace colorspace, float f, float f1, float f2, int i)
        {
            mPoints.add(new Point(colorspace, new float[] {
                f, f1, f2
            }, i));
            return this;
        }

        public Renderer add(ColorSpace colorspace, int i)
        {
            mColorSpaces.add(new Pair(colorspace, Integer.valueOf(i)));
            return this;
        }

        public Renderer clip(boolean flag)
        {
            mClip = flag;
            return this;
        }

        public Bitmap render()
        {
            Paint paint = new Paint(1);
            Bitmap bitmap = Bitmap.createBitmap(mSize, mSize, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            float af[] = new float[6];
            float af1[] = new float[2];
            Path path = new Path();
            setTransform(canvas, 1440, 1440, af);
            drawBox(canvas, 1440, 1440, paint, path);
            setUcsTransform(canvas, 1440);
            drawLocus(canvas, 1440, 1440, paint, path, af);
            drawGamuts(canvas, 1440, 1440, paint, path, af, af1);
            drawPoints(canvas, 1440, 1440, paint);
            return bitmap;
        }

        public Renderer showWhitePoint(boolean flag)
        {
            mShowWhitePoint = flag;
            return this;
        }

        public Renderer size(int i)
        {
            mSize = Math.max(128, i);
            return this;
        }

        public Renderer uniformChromaticityScale(boolean flag)
        {
            mUcs = flag;
            return this;
        }

        private static final int CHROMATICITY_RESOLUTION = 32;
        private static final int NATIVE_SIZE = 1440;
        private static final double ONE_THIRD = 0.33333333333333331D;
        private static final float SPECTRUM_LOCUS_X[] = {
            0.175596F, 0.172787F, 0.170806F, 0.170085F, 0.160343F, 0.146958F, 0.139149F, 0.133536F, 0.126688F, 0.11583F, 
            0.109616F, 0.099146F, 0.09131F, 0.07813F, 0.068717F, 0.054675F, 0.040763F, 0.027497F, 0.01627F, 0.008169F, 
            0.004876F, 0.003983F, 0.003859F, 0.004646F, 0.007988F, 0.01387F, 0.022244F, 0.027273F, 0.03282F, 0.038851F, 
            0.045327F, 0.052175F, 0.059323F, 0.066713F, 0.074299F, 0.089937F, 0.114155F, 0.138695F, 0.154714F, 0.192865F, 
            0.229607F, 0.26576F, 0.301588F, 0.337346F, 0.373083F, 0.408717F, 0.444043F, 0.478755F, 0.512467F, 0.544767F, 
            0.575132F, 0.602914F, 0.627018F, 0.648215F, 0.665746F, 0.680061F, 0.691487F, 0.700589F, 0.707901F, 0.714015F, 
            0.719017F, 0.723016F, 0.734674F, 0.717203F, 0.699732F, 0.68226F, 0.664789F, 0.647318F, 0.629847F, 0.612376F, 
            0.594905F, 0.577433F, 0.559962F, 0.542491F, 0.52502F, 0.507549F, 0.490077F, 0.472606F, 0.455135F, 0.437664F, 
            0.420193F, 0.402721F, 0.38525F, 0.367779F, 0.350308F, 0.332837F, 0.315366F, 0.297894F, 0.280423F, 0.262952F, 
            0.245481F, 0.22801F, 0.210538F, 0.193067F, 0.175596F
        };
        private static final float SPECTRUM_LOCUS_Y[] = {
            0.005295F, 0.0048F, 0.005472F, 0.005976F, 0.014496F, 0.026643F, 0.035211F, 0.042704F, 0.053441F, 0.073601F, 
            0.086866F, 0.112037F, 0.132737F, 0.170464F, 0.200773F, 0.254155F, 0.317049F, 0.387997F, 0.463035F, 0.538504F, 
            0.587196F, 0.610526F, 0.654897F, 0.67597F, 0.715407F, 0.750246F, 0.779682F, 0.792153F, 0.802971F, 0.812059F, 
            0.81943F, 0.8252F, 0.82946F, 0.832306F, 0.833833F, 0.833316F, 0.826231F, 0.814796F, 0.805884F, 0.781648F, 
            0.754347F, 0.724342F, 0.692326F, 0.658867F, 0.62447F, 0.589626F, 0.554734F, 0.520222F, 0.486611F, 0.454454F, 
            0.424252F, 0.396516F, 0.37251F, 0.351413F, 0.334028F, 0.319765F, 0.308359F, 0.299317F, 0.292044F, 0.285945F, 
            0.280951F, 0.276964F, 0.265326F, 0.2572F, 0.249074F, 0.240948F, 0.232822F, 0.224696F, 0.21657F, 0.208444F, 
            0.200318F, 0.192192F, 0.184066F, 0.17594F, 0.167814F, 0.159688F, 0.151562F, 0.143436F, 0.135311F, 0.127185F, 
            0.119059F, 0.110933F, 0.102807F, 0.094681F, 0.086555F, 0.078429F, 0.070303F, 0.062177F, 0.054051F, 0.045925F, 
            0.037799F, 0.029673F, 0.021547F, 0.013421F, 0.005295F
        };
        private static final float UCS_SCALE = 1.5F;
        private boolean mClip;
        private final List mColorSpaces;
        private final List mPoints;
        private boolean mShowWhitePoint;
        private int mSize;
        private boolean mUcs;


        private Renderer()
        {
            mSize = 1024;
            mShowWhitePoint = true;
            mClip = false;
            mUcs = false;
            mColorSpaces = new ArrayList(2);
            mPoints = new ArrayList(0);
        }

        Renderer(Renderer renderer)
        {
            this();
        }
    }

    private static class Renderer.Point
    {

        final int mColor;
        final ColorSpace mColorSpace;
        final float mRgb[];

        Renderer.Point(ColorSpace colorspace, float af[], int i)
        {
            mColorSpace = colorspace;
            mRgb = af;
            mColor = i;
        }
    }

    public static class Rgb extends ColorSpace
    {

        static DoubleUnaryOperator _2D_get0(Rgb rgb)
        {
            return rgb.mClampedEotf;
        }

        static DoubleUnaryOperator _2D_get1(Rgb rgb)
        {
            return rgb.mClampedOetf;
        }

        static float[] _2D_get2(Rgb rgb)
        {
            return rgb.mInverseTransform;
        }

        static TransferParameters _2D_get3(Rgb rgb)
        {
            return rgb.mTransferParameters;
        }

        static float[] _2D_get4(Rgb rgb)
        {
            return rgb.mTransform;
        }

        static float[] _2D_get5(Rgb rgb)
        {
            return rgb.mWhitePoint;
        }

        private static float area(float af[])
        {
            float f = af[0];
            float f1 = af[1];
            float f2 = af[2];
            float f3 = af[3];
            float f4 = af[4];
            float f5 = af[5];
            f3 = 0.5F * ((f * f3 + f1 * f4 + f2 * f5) - f3 * f4 - f1 * f2 - f * f5);
            f2 = f3;
            if(f3 < 0.0F)
                f2 = -f3;
            return f2;
        }

        private double clamp(double d)
        {
            if(d >= (double)mMin) goto _L2; else goto _L1
_L1:
            float f = mMin;
_L6:
            double d1 = f;
_L4:
            return d1;
_L2:
            d1 = d;
            if(d <= (double)mMax) goto _L4; else goto _L3
_L3:
            f = mMax;
            if(true) goto _L6; else goto _L5
_L5:
        }

        private static float[] computePrimaries(float af[])
        {
            float af1[] = ColorSpace._2D_wrap8(af, new float[] {
                1.0F, 0.0F, 0.0F
            });
            float af2[] = ColorSpace._2D_wrap8(af, new float[] {
                0.0F, 1.0F, 0.0F
            });
            af = ColorSpace._2D_wrap8(af, new float[] {
                0.0F, 0.0F, 1.0F
            });
            float f = af1[0] + af1[1] + af1[2];
            float f1 = af2[0] + af2[1] + af2[2];
            float f2 = af[0] + af[1] + af[2];
            return (new float[] {
                af1[0] / f, af1[1] / f, af2[0] / f1, af2[1] / f1, af[0] / f2, af[1] / f2
            });
        }

        private static float[] computeWhitePoint(float af[])
        {
            af = ColorSpace._2D_wrap8(af, new float[] {
                1.0F, 1.0F, 1.0F
            });
            float f = af[0] + af[1] + af[2];
            return (new float[] {
                af[0] / f, af[1] / f
            });
        }

        private static float[] computeXYZMatrix(float af[], float af1[])
        {
            float f = af[0];
            float f1 = af[1];
            float f2 = af[2];
            float f3 = af[3];
            float f4 = af[4];
            float f5 = af[5];
            float f6 = af1[0];
            float f7 = af1[1];
            float f8 = (1.0F - f) / f1;
            float f9 = (1.0F - f2) / f3;
            float f10 = (1.0F - f4) / f5;
            float f11 = (1.0F - f6) / f7;
            float f12 = f / f1;
            float f13 = f2 / f3;
            float f14 = f4 / f5;
            f7 = f6 / f7;
            f10 = ((f11 - f8) * (f13 - f12) - (f7 - f12) * (f9 - f8)) / ((f10 - f8) * (f13 - f12) - (f14 - f12) * (f9 - f8));
            f11 = (f7 - f12 - (f14 - f12) * f10) / (f13 - f12);
            f12 = 1.0F - f11 - f10;
            f9 = f12 / f1;
            f14 = f11 / f3;
            f13 = f10 / f5;
            return (new float[] {
                f9 * f, f12, (1.0F - f - f1) * f9, f14 * f2, f11, (1.0F - f2 - f3) * f14, f13 * f4, f10, (1.0F - f4 - f5) * f13
            });
        }

        private static boolean contains(float af[], float af1[])
        {
            float af2[] = new float[6];
            af2[0] = af[0] - af1[0];
            af2[1] = af[1] - af1[1];
            af2[2] = af[2] - af1[2];
            af2[3] = af[3] - af1[3];
            af2[4] = af[4] - af1[4];
            af2[5] = af[5] - af1[5];
            if(cross(af2[0], af2[1], af1[0] - af1[4], af1[1] - af1[5]) < 0.0F || cross(af1[0] - af1[2], af1[1] - af1[3], af2[0], af2[1]) < 0.0F)
                return false;
            if(cross(af2[2], af2[3], af1[2] - af1[0], af1[3] - af1[1]) < 0.0F || cross(af1[2] - af1[4], af1[3] - af1[5], af2[2], af2[3]) < 0.0F)
                return false;
            return cross(af2[4], af2[5], af1[4] - af1[2], af1[5] - af1[3]) >= 0.0F && cross(af1[4] - af1[0], af1[5] - af1[1], af2[4], af2[5]) >= 0.0F;
        }

        private static float cross(float f, float f1, float f2, float f3)
        {
            return f * f3 - f1 * f2;
        }

        private static boolean isSrgb(float af[], float af1[], DoubleUnaryOperator doubleunaryoperator, DoubleUnaryOperator doubleunaryoperator1, float f, float f1, int i)
        {
            if(i == 0)
                return true;
            if(!ColorSpace._2D_wrap0(af, ColorSpace._2D_get2()))
                return false;
            if(!ColorSpace._2D_wrap0(af1, ILLUMINANT_D65))
                return false;
            if(doubleunaryoperator.applyAsDouble(0.5D) < 0.50009999999999999D)
                return false;
            if(doubleunaryoperator1.applyAsDouble(0.5D) > 0.50009999999999999D)
                return false;
            if(f != 0.0F)
                return false;
            return f1 == 1.0F;
        }

        private static boolean isWideGamut(float af[], float f, float f1)
        {
label0:
            {
                boolean flag = true;
                boolean flag1;
                if(area(af) / area(ColorSpace._2D_get1()) > 0.9F)
                {
                    flag1 = flag;
                    if(contains(af, ColorSpace._2D_get2()))
                        break label0;
                }
                if(f < 0.0F && f1 > 1.0F)
                    flag1 = flag;
                else
                    flag1 = false;
            }
            return flag1;
        }

        static double lambda$_2D_android_graphics_ColorSpace$Rgb_106776(TransferParameters transferparameters, double d)
        {
            return ColorSpace._2D_wrap2(d, transferparameters.a, transferparameters.b, transferparameters.c, transferparameters.d, transferparameters.g);
        }

        static double lambda$_2D_android_graphics_ColorSpace$Rgb_106922(TransferParameters transferparameters, double d)
        {
            return ColorSpace._2D_wrap1(d, transferparameters.a, transferparameters.b, transferparameters.c, transferparameters.d, transferparameters.e, transferparameters.f, transferparameters.g);
        }

        static double lambda$_2D_android_graphics_ColorSpace$Rgb_107152(TransferParameters transferparameters, double d)
        {
            return ColorSpace._2D_wrap4(d, transferparameters.a, transferparameters.b, transferparameters.c, transferparameters.d, transferparameters.g);
        }

        static double lambda$_2D_android_graphics_ColorSpace$Rgb_107295(TransferParameters transferparameters, double d)
        {
            return ColorSpace._2D_wrap3(d, transferparameters.a, transferparameters.b, transferparameters.c, transferparameters.d, transferparameters.e, transferparameters.f, transferparameters.g);
        }

        static double lambda$_2D_android_graphics_ColorSpace$Rgb_113213(double d, double d1)
        {
            double d2 = d1;
            if(d1 < 0.0D)
                d2 = 0.0D;
            return Math.pow(d2, 1.0D / d);
        }

        static double lambda$_2D_android_graphics_ColorSpace$Rgb_113354(double d, double d1)
        {
            double d2 = d1;
            if(d1 < 0.0D)
                d2 = 0.0D;
            return Math.pow(d2, d);
        }

        private static float[] xyPrimaries(float af[])
        {
            float af1[] = new float[6];
            if(af.length == 9)
            {
                float f = af[0] + af[1] + af[2];
                af1[0] = af[0] / f;
                af1[1] = af[1] / f;
                f = af[3] + af[4] + af[5];
                af1[2] = af[3] / f;
                af1[3] = af[4] / f;
                f = af[6] + af[7] + af[8];
                af1[4] = af[6] / f;
                af1[5] = af[7] / f;
            } else
            {
                System.arraycopy(af, 0, af1, 0, 6);
            }
            return af1;
        }

        private static float[] xyWhitePoint(float af[])
        {
            float af1[] = new float[2];
            if(af.length == 3)
            {
                float f = af[0] + af[1] + af[2];
                af1[0] = af[0] / f;
                af1[1] = af[1] / f;
            } else
            {
                System.arraycopy(af, 0, af1, 0, 2);
            }
            return af1;
        }

        double _2D_android_graphics_ColorSpace$Rgb_2D_mthref_2D_0(double d)
        {
            return clamp(d);
        }

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            if(obj == null || getClass() != obj.getClass())
                return false;
            if(!equals(obj))
                return false;
            obj = (Rgb)obj;
            if(Float.compare(((Rgb) (obj)).mMin, mMin) != 0)
                return false;
            if(Float.compare(((Rgb) (obj)).mMax, mMax) != 0)
                return false;
            if(!Arrays.equals(mWhitePoint, ((Rgb) (obj)).mWhitePoint))
                return false;
            if(!Arrays.equals(mPrimaries, ((Rgb) (obj)).mPrimaries))
                return false;
            if(mTransferParameters != null)
                return mTransferParameters.equals(((Rgb) (obj)).mTransferParameters);
            if(((Rgb) (obj)).mTransferParameters == null)
                return true;
            if(!mOetf.equals(((Rgb) (obj)).mOetf))
                return false;
            else
                return mEotf.equals(((Rgb) (obj)).mEotf);
        }

        public float[] fromLinear(float f, float f1, float f2)
        {
            return fromLinear(new float[] {
                f, f1, f2
            });
        }

        public float[] fromLinear(float af[])
        {
            af[0] = (float)mClampedOetf.applyAsDouble(af[0]);
            af[1] = (float)mClampedOetf.applyAsDouble(af[1]);
            af[2] = (float)mClampedOetf.applyAsDouble(af[2]);
            return af;
        }

        public float[] fromXyz(float af[])
        {
            ColorSpace._2D_wrap8(mInverseTransform, af);
            af[0] = (float)mClampedOetf.applyAsDouble(af[0]);
            af[1] = (float)mClampedOetf.applyAsDouble(af[1]);
            af[2] = (float)mClampedOetf.applyAsDouble(af[2]);
            return af;
        }

        public DoubleUnaryOperator getEotf()
        {
            return mClampedEotf;
        }

        public float[] getInverseTransform()
        {
            return Arrays.copyOf(mInverseTransform, mInverseTransform.length);
        }

        public float[] getInverseTransform(float af[])
        {
            System.arraycopy(mInverseTransform, 0, af, 0, mInverseTransform.length);
            return af;
        }

        public float getMaxValue(int i)
        {
            return mMax;
        }

        public float getMinValue(int i)
        {
            return mMin;
        }

        public DoubleUnaryOperator getOetf()
        {
            return mClampedOetf;
        }

        public float[] getPrimaries()
        {
            return Arrays.copyOf(mPrimaries, mPrimaries.length);
        }

        public float[] getPrimaries(float af[])
        {
            System.arraycopy(mPrimaries, 0, af, 0, mPrimaries.length);
            return af;
        }

        public TransferParameters getTransferParameters()
        {
            return mTransferParameters;
        }

        public float[] getTransform()
        {
            return Arrays.copyOf(mTransform, mTransform.length);
        }

        public float[] getTransform(float af[])
        {
            System.arraycopy(mTransform, 0, af, 0, mTransform.length);
            return af;
        }

        public float[] getWhitePoint()
        {
            return Arrays.copyOf(mWhitePoint, mWhitePoint.length);
        }

        public float[] getWhitePoint(float af[])
        {
            af[0] = mWhitePoint[0];
            af[1] = mWhitePoint[1];
            return af;
        }

        public int hashCode()
        {
            int i = 0;
            int j = hashCode();
            int k = Arrays.hashCode(mWhitePoint);
            int l = Arrays.hashCode(mPrimaries);
            int i1;
            int j1;
            if(mMin != 0.0F)
                i1 = Float.floatToIntBits(mMin);
            else
                i1 = 0;
            if(mMax != 0.0F)
                j1 = Float.floatToIntBits(mMax);
            else
                j1 = 0;
            if(mTransferParameters != null)
                i = mTransferParameters.hashCode();
            j1 = ((((j * 31 + k) * 31 + l) * 31 + i1) * 31 + j1) * 31 + i;
            i1 = j1;
            if(mTransferParameters == null)
                i1 = (j1 * 31 + mOetf.hashCode()) * 31 + mEotf.hashCode();
            return i1;
        }

        public boolean isSrgb()
        {
            return mIsSrgb;
        }

        public boolean isWideGamut()
        {
            return mIsWideGamut;
        }

        public float[] toLinear(float f, float f1, float f2)
        {
            return toLinear(new float[] {
                f, f1, f2
            });
        }

        public float[] toLinear(float af[])
        {
            af[0] = (float)mClampedEotf.applyAsDouble(af[0]);
            af[1] = (float)mClampedEotf.applyAsDouble(af[1]);
            af[2] = (float)mClampedEotf.applyAsDouble(af[2]);
            return af;
        }

        public float[] toXyz(float af[])
        {
            af[0] = (float)mClampedEotf.applyAsDouble(af[0]);
            af[1] = (float)mClampedEotf.applyAsDouble(af[1]);
            af[2] = (float)mClampedEotf.applyAsDouble(af[2]);
            return ColorSpace._2D_wrap8(mTransform, af);
        }

        private final DoubleUnaryOperator mClampedEotf;
        private final DoubleUnaryOperator mClampedOetf;
        private final DoubleUnaryOperator mEotf;
        private final float mInverseTransform[];
        private final boolean mIsSrgb;
        private final boolean mIsWideGamut;
        private final float mMax;
        private final float mMin;
        private final DoubleUnaryOperator mOetf;
        private final float mPrimaries[];
        private TransferParameters mTransferParameters;
        private final float mTransform[];
        private final float mWhitePoint[];

        private Rgb(Rgb rgb, float af[], float af1[])
        {
            super(rgb.getName(), Model.RGB, -1, null);
            mWhitePoint = xyWhitePoint(af1);
            mPrimaries = rgb.mPrimaries;
            mTransform = af;
            mInverseTransform = ColorSpace._2D_wrap6(af);
            mMin = rgb.mMin;
            mMax = rgb.mMax;
            mOetf = rgb.mOetf;
            mEotf = rgb.mEotf;
            mClampedOetf = rgb.mClampedOetf;
            mClampedEotf = rgb.mClampedEotf;
            mIsWideGamut = rgb.mIsWideGamut;
            mIsSrgb = rgb.mIsSrgb;
            mTransferParameters = rgb.mTransferParameters;
        }

        Rgb(Rgb rgb, float af[], float af1[], Rgb rgb1)
        {
            this(rgb, af, af1);
        }

        public Rgb(String s, float af[], double d)
        {
            this(s, computePrimaries(af), computeWhitePoint(af), d, 0.0F, 1.0F, -1);
        }

        public Rgb(String s, float af[], TransferParameters transferparameters)
        {
            this(s, computePrimaries(af), computeWhitePoint(af), transferparameters, -1);
        }

        public Rgb(String s, float af[], DoubleUnaryOperator doubleunaryoperator, DoubleUnaryOperator doubleunaryoperator1)
        {
            this(s, computePrimaries(af), computeWhitePoint(af), doubleunaryoperator, doubleunaryoperator1, 0.0F, 1.0F, -1);
        }

        public Rgb(String s, float af[], float af1[], double d)
        {
            this(s, af, af1, d, 0.0F, 1.0F, -1);
        }

        private Rgb(String s, float af[], float af1[], double d, float f, float f1, 
                int i)
        {
            Object obj;
            Object obj1;
            if(d == 1.0D)
                obj = DoubleUnaryOperator.identity();
            else
                obj = new _.Lambda.ZrP_zejiEXAqfwV_MyP5lE9mYC8._cls2((byte)0, d);
            if(d == 1.0D)
                obj1 = DoubleUnaryOperator.identity();
            else
                obj1 = new _.Lambda.ZrP_zejiEXAqfwV_MyP5lE9mYC8._cls2((byte)1, d);
            this(s, af, af1, ((DoubleUnaryOperator) (obj)), ((DoubleUnaryOperator) (obj1)), f, f1, i);
            if(d == 1.0D)
                s = new TransferParameters(0.0D, 0.0D, 1.0D, 1.0D + (double)Math.ulp(1.0F), d);
            else
                s = new TransferParameters(1.0D, 0.0D, 0.0D, 0.0D, d);
            mTransferParameters = s;
        }

        Rgb(String s, float af[], float af1[], double d, float f, float f1, 
                int i, Rgb rgb)
        {
            this(s, af, af1, d, f, f1, i);
        }

        public Rgb(String s, float af[], float af1[], TransferParameters transferparameters)
        {
            this(s, af, af1, transferparameters, -1);
        }

        private Rgb(String s, float af[], float af1[], TransferParameters transferparameters, int i)
        {
            _.Lambda.ZrP_zejiEXAqfwV_MyP5lE9mYC8._cls1 _lcls1;
            _.Lambda.ZrP_zejiEXAqfwV_MyP5lE9mYC8._cls1 _lcls1_1;
            if(transferparameters.e == 0.0D && transferparameters.f == 0.0D)
                _lcls1 = new _.Lambda.ZrP_zejiEXAqfwV_MyP5lE9mYC8._cls1((byte)0, transferparameters);
            else
                _lcls1 = new _.Lambda.ZrP_zejiEXAqfwV_MyP5lE9mYC8._cls1((byte)1, transferparameters);
            if(transferparameters.e == 0.0D && transferparameters.f == 0.0D)
                _lcls1_1 = new _.Lambda.ZrP_zejiEXAqfwV_MyP5lE9mYC8._cls1((byte)2, transferparameters);
            else
                _lcls1_1 = new _.Lambda.ZrP_zejiEXAqfwV_MyP5lE9mYC8._cls1((byte)3, transferparameters);
            this(s, af, af1, ((DoubleUnaryOperator) (_lcls1)), ((DoubleUnaryOperator) (_lcls1_1)), 0.0F, 1.0F, i);
            mTransferParameters = transferparameters;
        }

        Rgb(String s, float af[], float af1[], TransferParameters transferparameters, int i, Rgb rgb)
        {
            this(s, af, af1, transferparameters, i);
        }

        public Rgb(String s, float af[], float af1[], DoubleUnaryOperator doubleunaryoperator, DoubleUnaryOperator doubleunaryoperator1, float f, float f1)
        {
            this(s, af, af1, doubleunaryoperator, doubleunaryoperator1, f, f1, -1);
        }

        private Rgb(String s, float af[], float af1[], DoubleUnaryOperator doubleunaryoperator, DoubleUnaryOperator doubleunaryoperator1, float f, float f1, 
                int i)
        {
            super(s, Model.RGB, i, null);
            if(af == null || af.length != 6 && af.length != 9)
                throw new IllegalArgumentException("The color space's primaries must be defined as an array of 6 floats in xyY or 9 floats in XYZ");
            if(af1 == null || af1.length != 2 && af1.length != 3)
                throw new IllegalArgumentException("The color space's white point must be defined as an array of 2 floats in xyY or 3 float in XYZ");
            if(doubleunaryoperator == null || doubleunaryoperator1 == null)
                throw new IllegalArgumentException("The transfer functions of a color space cannot be null");
            if(f >= f1)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Invalid range: min=").append(f).append(", max=").append(f1).append("; min must be strictly < max").toString());
            } else
            {
                mWhitePoint = xyWhitePoint(af1);
                mPrimaries = xyPrimaries(af);
                mTransform = computeXYZMatrix(mPrimaries, mWhitePoint);
                mInverseTransform = ColorSpace._2D_wrap6(mTransform);
                mOetf = doubleunaryoperator;
                mEotf = doubleunaryoperator1;
                mMin = f;
                mMax = f1;
                s = new _.Lambda.ZrP_zejiEXAqfwV_MyP5lE9mYC8._cls1((byte)4, this);
                mClampedOetf = doubleunaryoperator.andThen(s);
                mClampedEotf = s.andThen(doubleunaryoperator1);
                mIsWideGamut = isWideGamut(mPrimaries, f, f1);
                mIsSrgb = isSrgb(mPrimaries, mWhitePoint, doubleunaryoperator, doubleunaryoperator1, f, f1, i);
                return;
            }
        }

        Rgb(String s, float af[], float af1[], DoubleUnaryOperator doubleunaryoperator, DoubleUnaryOperator doubleunaryoperator1, float f, float f1, 
                int i, Rgb rgb)
        {
            this(s, af, af1, doubleunaryoperator, doubleunaryoperator1, f, f1, i);
        }
    }

    public static class Rgb.TransferParameters
    {

        public boolean equals(Object obj)
        {
            boolean flag = true;
            if(this == obj)
                return true;
            if(obj == null || getClass() != obj.getClass())
                return false;
            obj = (Rgb.TransferParameters)obj;
            if(Double.compare(((Rgb.TransferParameters) (obj)).a, a) != 0)
                return false;
            if(Double.compare(((Rgb.TransferParameters) (obj)).b, b) != 0)
                return false;
            if(Double.compare(((Rgb.TransferParameters) (obj)).c, c) != 0)
                return false;
            if(Double.compare(((Rgb.TransferParameters) (obj)).d, d) != 0)
                return false;
            if(Double.compare(((Rgb.TransferParameters) (obj)).e, e) != 0)
                return false;
            if(Double.compare(((Rgb.TransferParameters) (obj)).f, f) != 0)
                return false;
            if(Double.compare(((Rgb.TransferParameters) (obj)).g, g) != 0)
                flag = false;
            return flag;
        }

        public int hashCode()
        {
            long l = Double.doubleToLongBits(a);
            int i = (int)(l >>> 32 ^ l);
            l = Double.doubleToLongBits(b);
            int j = (int)(l >>> 32 ^ l);
            l = Double.doubleToLongBits(c);
            int k = (int)(l >>> 32 ^ l);
            l = Double.doubleToLongBits(d);
            int i1 = (int)(l >>> 32 ^ l);
            l = Double.doubleToLongBits(e);
            int j1 = (int)(l >>> 32 ^ l);
            l = Double.doubleToLongBits(f);
            int k1 = (int)(l >>> 32 ^ l);
            l = Double.doubleToLongBits(g);
            return (((((i * 31 + j) * 31 + k) * 31 + i1) * 31 + j1) * 31 + k1) * 31 + (int)(l >>> 32 ^ l);
        }

        public final double a;
        public final double b;
        public final double c;
        public final double d;
        public final double e;
        public final double f;
        public final double g;

        public Rgb.TransferParameters(double d1, double d2, double d3, double d4, double d5)
        {
            this(d1, d2, d3, d4, 0.0D, 0.0D, d5);
        }

        public Rgb.TransferParameters(double d1, double d2, double d3, double d4, double d5, double d6, double d7)
        {
            if(Double.isNaN(d1) || Double.isNaN(d2) || Double.isNaN(d3) || Double.isNaN(d4) || Double.isNaN(d5) || Double.isNaN(d6) || Double.isNaN(d7))
                throw new IllegalArgumentException("Parameters cannot be NaN");
            boolean flag;
            if(d4 >= 0.0D && d4 <= (double)(Math.ulp(1.0F) + 1.0F))
                flag = true;
            else
                flag = false;
            if(!flag)
                throw new IllegalArgumentException((new StringBuilder()).append("Parameter d must be in the range [0..1], was ").append(d4).toString());
            if(d4 == 0.0D && (d1 == 0.0D || d7 == 0.0D))
                throw new IllegalArgumentException("Parameter a or g is zero, the transfer function is constant");
            if(d4 >= 1.0D && d3 == 0.0D)
                throw new IllegalArgumentException("Parameter c is zero, the transfer function is constant");
            if((d1 == 0.0D || d7 == 0.0D) && d3 == 0.0D)
                throw new IllegalArgumentException("Parameter a or g is zero, and c is zero, the transfer function is constant");
            if(d3 < 0.0D)
                throw new IllegalArgumentException("The transfer function must be increasing");
            if(d1 < 0.0D || d7 < 0.0D)
            {
                throw new IllegalArgumentException("The transfer function must be positive or increasing");
            } else
            {
                a = d1;
                b = d2;
                c = d3;
                d = d4;
                e = d5;
                f = d6;
                g = d7;
                return;
            }
        }
    }

    private static final class Xyz extends ColorSpace
    {

        private static float clamp(float f)
        {
            if(f >= -2F) goto _L2; else goto _L1
_L1:
            float f1 = -2F;
_L4:
            return f1;
_L2:
            f1 = f;
            if(f > 2.0F)
                f1 = 2.0F;
            if(true) goto _L4; else goto _L3
_L3:
        }

        public float[] fromXyz(float af[])
        {
            af[0] = clamp(af[0]);
            af[1] = clamp(af[1]);
            af[2] = clamp(af[2]);
            return af;
        }

        public float getMaxValue(int i)
        {
            return 2.0F;
        }

        public float getMinValue(int i)
        {
            return -2F;
        }

        public boolean isWideGamut()
        {
            return true;
        }

        public float[] toXyz(float af[])
        {
            af[0] = clamp(af[0]);
            af[1] = clamp(af[1]);
            af[2] = clamp(af[2]);
            return af;
        }

        private Xyz(String s, int i)
        {
            super(s, Model.XYZ, i, null);
        }

        Xyz(String s, int i, Xyz xyz)
        {
            this(s, i);
        }
    }


    static float[] _2D_get0()
    {
        return ILLUMINANT_D50_XYZ;
    }

    static float[] _2D_get1()
    {
        return NTSC_1953_PRIMARIES;
    }

    static float[] _2D_get2()
    {
        return SRGB_PRIMARIES;
    }

    static boolean _2D_wrap0(float af[], float af1[])
    {
        return compare(af, af1);
    }

    static double _2D_wrap1(double d, double d1, double d2, double d3, 
            double d4, double d5, double d6, double d7)
    {
        return rcpResponse(d, d1, d2, d3, d4, d5, d6, d7);
    }

    static float[] _2D_wrap10(float af[])
    {
        return xyYToXyz(af);
    }

    static void _2D_wrap11(float af[])
    {
        xyYToUv(af);
    }

    static double _2D_wrap2(double d, double d1, double d2, double d3, 
            double d4, double d5)
    {
        return rcpResponse(d, d1, d2, d3, d4, d5);
    }

    static double _2D_wrap3(double d, double d1, double d2, double d3, 
            double d4, double d5, double d6, double d7)
    {
        return response(d, d1, d2, d3, d4, d5, d6, d7);
    }

    static double _2D_wrap4(double d, double d1, double d2, double d3, 
            double d4, double d5)
    {
        return response(d, d1, d2, d3, d4, d5);
    }

    static float[] _2D_wrap5(float af[], float af1[], float af2[])
    {
        return chromaticAdaptation(af, af1, af2);
    }

    static float[] _2D_wrap6(float af[])
    {
        return inverse3x3(af);
    }

    static float[] _2D_wrap7(float af[], float af1[])
    {
        return mul3x3Diag(af, af1);
    }

    static float[] _2D_wrap8(float af[], float af1[])
    {
        return mul3x3Float3(af, af1);
    }

    static float[] _2D_wrap9(float af[], float af1[])
    {
        return mul3x3(af, af1);
    }

    private ColorSpace(String s, Model model, int i)
    {
        if(s == null || s.length() < 1)
            throw new IllegalArgumentException("The name of a color space cannot be null and must contain at least 1 character");
        if(model == null)
            throw new IllegalArgumentException("A color space must have a model");
        if(i < -1 || i > 63)
        {
            throw new IllegalArgumentException("The id must be between -1 and 63");
        } else
        {
            mName = s;
            mModel = model;
            mId = i;
            return;
        }
    }

    ColorSpace(String s, Model model, int i, ColorSpace colorspace)
    {
        this(s, model, i);
    }

    private static double absRcpResponse(double d, double d1, double d2, double d3, 
            double d4, double d5)
    {
        double d6;
        if(d < 0.0D)
            d6 = -d;
        else
            d6 = d;
        return Math.copySign(rcpResponse(d6, d1, d2, d3, d4, d5), d);
    }

    private static double absResponse(double d, double d1, double d2, double d3, 
            double d4, double d5)
    {
        double d6;
        if(d < 0.0D)
            d6 = -d;
        else
            d6 = d;
        return Math.copySign(response(d6, d1, d2, d3, d4, d5), d);
    }

    public static ColorSpace adapt(ColorSpace colorspace, float af[])
    {
        return adapt(colorspace, af, Adaptation.BRADFORD);
    }

    public static ColorSpace adapt(ColorSpace colorspace, float af[], Adaptation adaptation)
    {
        if(colorspace.getModel() == Model.RGB)
        {
            Rgb rgb = (Rgb)colorspace;
            if(compare(Rgb._2D_get5(rgb), af))
                return colorspace;
            if(af.length == 3)
                colorspace = Arrays.copyOf(af, 3);
            else
                colorspace = xyYToXyz(af);
            return new Rgb(rgb, mul3x3(chromaticAdaptation(adaptation.mTransform, xyYToXyz(rgb.getWhitePoint()), colorspace), Rgb._2D_get4(rgb)), af, null);
        } else
        {
            return colorspace;
        }
    }

    private static float[] chromaticAdaptation(float af[], float af1[], float af2[])
    {
        af1 = mul3x3Float3(af, af1);
        af2 = mul3x3Float3(af, af2);
        float f = af2[0] / af1[0];
        float f1 = af2[1] / af1[1];
        float f2 = af2[2] / af1[2];
        return mul3x3(inverse3x3(af), mul3x3Diag(new float[] {
            f, f1, f2
        }, af));
    }

    private static boolean compare(Rgb.TransferParameters transferparameters, Rgb.TransferParameters transferparameters1)
    {
        boolean flag = true;
        if(transferparameters == null && transferparameters1 == null)
            return true;
        if(transferparameters != null && transferparameters1 != null && Math.abs(transferparameters.a - transferparameters1.a) < 0.001D && Math.abs(transferparameters.b - transferparameters1.b) < 0.001D && Math.abs(transferparameters.c - transferparameters1.c) < 0.001D && Math.abs(transferparameters.d - transferparameters1.d) < 0.002D && Math.abs(transferparameters.e - transferparameters1.e) < 0.001D && Math.abs(transferparameters.f - transferparameters1.f) < 0.001D)
        {
            if(Math.abs(transferparameters.g - transferparameters1.g) >= 0.001D)
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    private static boolean compare(float af[], float af1[])
    {
        if(af == af1)
            return true;
        for(int i = 0; i < af.length; i++)
            if(Float.compare(af[i], af1[i]) != 0 && Math.abs(af[i] - af1[i]) > 0.001F)
                return false;

        return true;
    }

    public static Connector connect(ColorSpace colorspace)
    {
        return connect(colorspace, RenderIntent.PERCEPTUAL);
    }

    public static Connector connect(ColorSpace colorspace, RenderIntent renderintent)
    {
        if(colorspace.isSrgb())
            return Connector.identity(colorspace);
        if(colorspace.getModel() == Model.RGB)
            return new Connector.Rgb((Rgb)colorspace, (Rgb)get(Named.SRGB), renderintent);
        else
            return new Connector(colorspace, get(Named.SRGB), renderintent);
    }

    public static Connector connect(ColorSpace colorspace, ColorSpace colorspace1)
    {
        return connect(colorspace, colorspace1, RenderIntent.PERCEPTUAL);
    }

    public static Connector connect(ColorSpace colorspace, ColorSpace colorspace1, RenderIntent renderintent)
    {
        if(colorspace.equals(colorspace1))
            return Connector.identity(colorspace);
        if(colorspace.getModel() == Model.RGB && colorspace1.getModel() == Model.RGB)
            return new Connector.Rgb((Rgb)colorspace, (Rgb)colorspace1, renderintent);
        else
            return new Connector(colorspace, colorspace1, renderintent);
    }

    public static Renderer createRenderer()
    {
        return new Renderer(null);
    }

    static ColorSpace get(int i)
    {
        if(i < 0 || i > Named.values().length)
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid ID, must be in the range [0..").append(Named.values().length).append("]").toString());
        else
            return sNamedColorSpaces[i];
    }

    public static ColorSpace get(Named named)
    {
        return sNamedColorSpaces[named.ordinal()];
    }

    private static float[] inverse3x3(float af[])
    {
        float f = af[0];
        float f1 = af[3];
        float f2 = af[6];
        float f3 = af[1];
        float f4 = af[4];
        float f5 = af[7];
        float f6 = af[2];
        float f7 = af[5];
        float f8 = af[8];
        float f9 = f4 * f8 - f5 * f7;
        float f10 = f5 * f6 - f3 * f8;
        float f11 = f3 * f7 - f4 * f6;
        float f12 = f * f9 + f1 * f10 + f2 * f11;
        af = new float[af.length];
        af[0] = f9 / f12;
        af[1] = f10 / f12;
        af[2] = f11 / f12;
        af[3] = (f2 * f7 - f1 * f8) / f12;
        af[4] = (f * f8 - f2 * f6) / f12;
        af[5] = (f1 * f6 - f * f7) / f12;
        af[6] = (f1 * f5 - f2 * f4) / f12;
        af[7] = (f2 * f3 - f * f5) / f12;
        af[8] = (f * f4 - f1 * f3) / f12;
        return af;
    }

    static double lambda$_2D_android_graphics_ColorSpace_64008(double d)
    {
        return absRcpResponse(d, 0.94786729857819907D, 0.052132701421800952D, 0.077399380804953566D, 0.04045D, 2.3999999999999999D);
    }

    static double lambda$_2D_android_graphics_ColorSpace_64099(double d)
    {
        return absResponse(d, 0.94786729857819907D, 0.052132701421800952D, 0.077399380804953566D, 0.04045D, 2.3999999999999999D);
    }

    public static ColorSpace match(float af[], Rgb.TransferParameters transferparameters)
    {
        ColorSpace acolorspace[] = sNamedColorSpaces;
        int i = 0;
        for(int j = acolorspace.length; i < j; i++)
        {
            ColorSpace colorspace = acolorspace[i];
            if(colorspace.getModel() != Model.RGB)
                continue;
            Rgb rgb = (Rgb)adapt(colorspace, ILLUMINANT_D50_XYZ);
            if(compare(af, Rgb._2D_get4(rgb)) && compare(transferparameters, Rgb._2D_get3(rgb)))
                return colorspace;
        }

        return null;
    }

    private static float[] mul3x3(float af[], float af1[])
    {
        return (new float[] {
            af[0] * af1[0] + af[3] * af1[1] + af[6] * af1[2], af[1] * af1[0] + af[4] * af1[1] + af[7] * af1[2], af[2] * af1[0] + af[5] * af1[1] + af[8] * af1[2], af[0] * af1[3] + af[3] * af1[4] + af[6] * af1[5], af[1] * af1[3] + af[4] * af1[4] + af[7] * af1[5], af[2] * af1[3] + af[5] * af1[4] + af[8] * af1[5], af[0] * af1[6] + af[3] * af1[7] + af[6] * af1[8], af[1] * af1[6] + af[4] * af1[7] + af[7] * af1[8], af[2] * af1[6] + af[5] * af1[7] + af[8] * af1[8]
        });
    }

    private static float[] mul3x3Diag(float af[], float af1[])
    {
        return (new float[] {
            af[0] * af1[0], af[1] * af1[1], af[2] * af1[2], af[0] * af1[3], af[1] * af1[4], af[2] * af1[5], af[0] * af1[6], af[1] * af1[7], af[2] * af1[8]
        });
    }

    private static float[] mul3x3Float3(float af[], float af1[])
    {
        float f = af1[0];
        float f1 = af1[1];
        float f2 = af1[2];
        af1[0] = af[0] * f + af[3] * f1 + af[6] * f2;
        af1[1] = af[1] * f + af[4] * f1 + af[7] * f2;
        af1[2] = af[2] * f + af[5] * f1 + af[8] * f2;
        return af1;
    }

    private static double rcpResponse(double d, double d1, double d2, double d3, 
            double d4, double d5)
    {
        if(d >= d4 * d3)
            d = (Math.pow(d, 1.0D / d5) - d2) / d1;
        else
            d /= d3;
        return d;
    }

    private static double rcpResponse(double d, double d1, double d2, double d3, 
            double d4, double d5, double d6, double d7)
    {
        if(d >= d4 * d3)
            d = (Math.pow(d - d5, 1.0D / d7) - d2) / d1;
        else
            d = (d - d6) / d3;
        return d;
    }

    private static double response(double d, double d1, double d2, double d3, 
            double d4, double d5)
    {
        if(d >= d4)
            d = Math.pow(d1 * d + d2, d5);
        else
            d = d3 * d;
        return d;
    }

    private static double response(double d, double d1, double d2, double d3, 
            double d4, double d5, double d6, double d7)
    {
        if(d >= d4)
            d = Math.pow(d1 * d + d2, d7) + d5;
        else
            d = d3 * d + d6;
        return d;
    }

    private static void xyYToUv(float af[])
    {
        for(int i = 0; i < af.length; i += 2)
        {
            float f = af[i];
            float f1 = af[i + 1];
            float f2 = -2F * f + 12F * f1 + 3F;
            f = (4F * f) / f2;
            f1 = (9F * f1) / f2;
            af[i] = f;
            af[i + 1] = f1;
        }

    }

    private static float[] xyYToXyz(float af[])
    {
        return (new float[] {
            af[0] / af[1], 1.0F, (1.0F - af[0] - af[1]) / af[1]
        });
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        obj = (ColorSpace)obj;
        if(mId != ((ColorSpace) (obj)).mId)
            return false;
        if(!mName.equals(((ColorSpace) (obj)).mName))
            return false;
        if(mModel != ((ColorSpace) (obj)).mModel)
            flag = false;
        return flag;
    }

    public float[] fromXyz(float f, float f1, float f2)
    {
        float af[] = new float[mModel.getComponentCount()];
        af[0] = f;
        af[1] = f1;
        af[2] = f2;
        return fromXyz(af);
    }

    public abstract float[] fromXyz(float af[]);

    public int getComponentCount()
    {
        return mModel.getComponentCount();
    }

    public int getId()
    {
        return mId;
    }

    public abstract float getMaxValue(int i);

    public abstract float getMinValue(int i);

    public Model getModel()
    {
        return mModel;
    }

    public String getName()
    {
        return mName;
    }

    public int hashCode()
    {
        return (mName.hashCode() * 31 + mModel.hashCode()) * 31 + mId;
    }

    public boolean isSrgb()
    {
        return false;
    }

    public abstract boolean isWideGamut();

    public String toString()
    {
        return (new StringBuilder()).append(mName).append(" (id=").append(mId).append(", model=").append(mModel).append(")").toString();
    }

    public float[] toXyz(float f, float f1, float f2)
    {
        return toXyz(new float[] {
            f, f1, f2
        });
    }

    public abstract float[] toXyz(float af[]);

    public static final float ILLUMINANT_A[] = {
        0.44757F, 0.40745F
    };
    public static final float ILLUMINANT_B[] = {
        0.34842F, 0.35161F
    };
    public static final float ILLUMINANT_C[] = {
        0.31006F, 0.31616F
    };
    public static final float ILLUMINANT_D50[] = {
        0.34567F, 0.3585F
    };
    private static final float ILLUMINANT_D50_XYZ[] = {
        0.964212F, 1.0F, 0.825188F
    };
    public static final float ILLUMINANT_D55[] = {
        0.33242F, 0.34743F
    };
    public static final float ILLUMINANT_D60[] = {
        0.32168F, 0.33767F
    };
    public static final float ILLUMINANT_D65[] = {
        0.31271F, 0.32902F
    };
    public static final float ILLUMINANT_D75[] = {
        0.29902F, 0.31485F
    };
    public static final float ILLUMINANT_E[] = {
        0.33333F, 0.33333F
    };
    public static final int MAX_ID = 63;
    public static final int MIN_ID = -1;
    private static final float NTSC_1953_PRIMARIES[] = {
        0.67F, 0.33F, 0.21F, 0.71F, 0.14F, 0.08F
    };
    private static final float SRGB_PRIMARIES[] = {
        0.64F, 0.33F, 0.3F, 0.6F, 0.15F, 0.06F
    };
    private static final ColorSpace sNamedColorSpaces[];
    private final int mId;
    private final Model mModel;
    private final String mName;

    static 
    {
        sNamedColorSpaces = new ColorSpace[Named.values().length];
        sNamedColorSpaces[Named.SRGB.ordinal()] = new Rgb("sRGB IEC61966-2.1", SRGB_PRIMARIES, ILLUMINANT_D65, new Rgb.TransferParameters(0.94786729857819907D, 0.052132701421800952D, 0.077399380804953566D, 0.04045D, 2.3999999999999999D), Named.SRGB.ordinal(), null);
        sNamedColorSpaces[Named.LINEAR_SRGB.ordinal()] = new Rgb("sRGB IEC61966-2.1 (Linear)", SRGB_PRIMARIES, ILLUMINANT_D65, 1.0D, 0.0F, 1.0F, Named.LINEAR_SRGB.ordinal(), null);
        sNamedColorSpaces[Named.EXTENDED_SRGB.ordinal()] = new Rgb("scRGB-nl IEC 61966-2-2:2003", SRGB_PRIMARIES, ILLUMINANT_D65, _.Lambda.ZrP_zejiEXAqfwV_MyP5lE9mYC8.$INST$0, _.Lambda.ZrP_zejiEXAqfwV_MyP5lE9mYC8.$INST$1, -0.799F, 2.399F, Named.EXTENDED_SRGB.ordinal(), null);
        sNamedColorSpaces[Named.LINEAR_EXTENDED_SRGB.ordinal()] = new Rgb("scRGB IEC 61966-2-2:2003", SRGB_PRIMARIES, ILLUMINANT_D65, 1.0D, -0.5F, 7.499F, Named.LINEAR_EXTENDED_SRGB.ordinal(), null);
        Object aobj[] = sNamedColorSpaces;
        int i = Named.BT709.ordinal();
        Object aobj1[] = ILLUMINANT_D65;
        Rgb.TransferParameters transferparameters = new Rgb.TransferParameters(0.90991810737033674D, 0.090081892629663332D, 0.22222222222222221D, 0.081000000000000003D, 2.2222222222222223D);
        int j = Named.BT709.ordinal();
        aobj[i] = new Rgb("Rec. ITU-R BT.709-5", new float[] {
            0.64F, 0.33F, 0.3F, 0.6F, 0.15F, 0.06F
        }, ((float []) (aobj1)), transferparameters, j, null);
        aobj1 = sNamedColorSpaces;
        i = Named.BT2020.ordinal();
        aobj = ILLUMINANT_D65;
        transferparameters = new Rgb.TransferParameters(0.90966978986627856D, 0.090330210133721459D, 0.22222222222222221D, 0.081449999999999995D, 2.2222222222222223D);
        j = Named.BT2020.ordinal();
        aobj1[i] = new Rgb("Rec. ITU-R BT.2020-1", new float[] {
            0.708F, 0.292F, 0.17F, 0.797F, 0.131F, 0.046F
        }, ((float []) (aobj)), transferparameters, j, null);
        Object aobj2[] = sNamedColorSpaces;
        j = Named.DCI_P3.ordinal();
        i = Named.DCI_P3.ordinal();
        aobj2[j] = new Rgb("SMPTE RP 431-2-2007 DCI (P3)", new float[] {
            0.68F, 0.32F, 0.265F, 0.69F, 0.15F, 0.06F
        }, new float[] {
            0.314F, 0.351F
        }, 2.6000000000000001D, 0.0F, 1.0F, i, null);
        aobj = sNamedColorSpaces;
        j = Named.DISPLAY_P3.ordinal();
        aobj1 = ILLUMINANT_D65;
        aobj2 = new Rgb.TransferParameters(0.94786729857819907D, 0.052132701421800952D, 0.077399380804953566D, 0.039D, 2.3999999999999999D);
        i = Named.DISPLAY_P3.ordinal();
        aobj[j] = new Rgb("Display P3", new float[] {
            0.68F, 0.32F, 0.265F, 0.69F, 0.15F, 0.06F
        }, ((float []) (aobj1)), ((Rgb.TransferParameters) (aobj2)), i, null);
        sNamedColorSpaces[Named.NTSC_1953.ordinal()] = new Rgb("NTSC (1953)", NTSC_1953_PRIMARIES, ILLUMINANT_C, new Rgb.TransferParameters(0.90991810737033674D, 0.090081892629663332D, 0.22222222222222221D, 0.081000000000000003D, 2.2222222222222223D), Named.NTSC_1953.ordinal(), null);
        aobj1 = sNamedColorSpaces;
        i = Named.SMPTE_C.ordinal();
        aobj = ILLUMINANT_D65;
        aobj2 = new Rgb.TransferParameters(0.90991810737033674D, 0.090081892629663332D, 0.22222222222222221D, 0.081000000000000003D, 2.2222222222222223D);
        j = Named.SMPTE_C.ordinal();
        aobj1[i] = new Rgb("SMPTE-C RGB", new float[] {
            0.63F, 0.34F, 0.31F, 0.595F, 0.155F, 0.07F
        }, ((float []) (aobj)), ((Rgb.TransferParameters) (aobj2)), j, null);
        aobj = sNamedColorSpaces;
        i = Named.ADOBE_RGB.ordinal();
        aobj2 = ILLUMINANT_D65;
        j = Named.ADOBE_RGB.ordinal();
        aobj[i] = new Rgb("Adobe RGB (1998)", new float[] {
            0.64F, 0.33F, 0.21F, 0.71F, 0.15F, 0.06F
        }, ((float []) (aobj2)), 2.2000000000000002D, 0.0F, 1.0F, j, null);
        aobj1 = sNamedColorSpaces;
        j = Named.PRO_PHOTO_RGB.ordinal();
        aobj = ILLUMINANT_D50;
        aobj2 = new Rgb.TransferParameters(1.0D, 0.0D, 0.0625D, 0.031248000000000001D, 1.8D);
        i = Named.PRO_PHOTO_RGB.ordinal();
        aobj1[j] = new Rgb("ROMM RGB ISO 22028-2:2013", new float[] {
            0.7347F, 0.2653F, 0.1596F, 0.8404F, 0.0366F, 0.0001F
        }, ((float []) (aobj)), ((Rgb.TransferParameters) (aobj2)), i, null);
        aobj2 = sNamedColorSpaces;
        j = Named.ACES.ordinal();
        aobj = ILLUMINANT_D60;
        i = Named.ACES.ordinal();
        aobj2[j] = new Rgb("SMPTE ST 2065-1:2012 ACES", new float[] {
            0.7347F, 0.2653F, 0.0F, 1.0F, 0.0001F, -0.077F
        }, ((float []) (aobj)), 1.0D, -65504F, 65504F, i, null);
        aobj2 = sNamedColorSpaces;
        i = Named.ACESCG.ordinal();
        aobj = ILLUMINANT_D60;
        j = Named.ACESCG.ordinal();
        aobj2[i] = new Rgb("Academy S-2014-004 ACEScg", new float[] {
            0.713F, 0.293F, 0.165F, 0.83F, 0.128F, 0.044F
        }, ((float []) (aobj)), 1.0D, -65504F, 65504F, j, null);
        sNamedColorSpaces[Named.CIE_XYZ.ordinal()] = new Xyz("Generic XYZ", Named.CIE_XYZ.ordinal(), null);
        sNamedColorSpaces[Named.CIE_LAB.ordinal()] = new Lab("Generic L*a*b*", Named.CIE_LAB.ordinal(), null);
    }
}
