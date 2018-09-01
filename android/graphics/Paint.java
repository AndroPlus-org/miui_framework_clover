// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;

import android.graphics.fonts.FontVariationAxis;
import android.os.LocaleList;
import android.text.*;
import java.util.*;
import libcore.util.NativeAllocationRegistry;

// Referenced classes of package android.graphics:
//            TemporaryBuffer, Path, Shader, ColorFilter, 
//            Typeface, MaskFilter, PathEffect, Xfermode, 
//            Rect, Rasterizer

public class Paint
{
    public static final class Align extends Enum
    {

        public static Align valueOf(String s)
        {
            return (Align)Enum.valueOf(android/graphics/Paint$Align, s);
        }

        public static Align[] values()
        {
            return $VALUES;
        }

        private static final Align $VALUES[];
        public static final Align CENTER;
        public static final Align LEFT;
        public static final Align RIGHT;
        final int nativeInt;

        static 
        {
            LEFT = new Align("LEFT", 0, 0);
            CENTER = new Align("CENTER", 1, 1);
            RIGHT = new Align("RIGHT", 2, 2);
            $VALUES = (new Align[] {
                LEFT, CENTER, RIGHT
            });
        }

        private Align(String s, int i, int j)
        {
            super(s, i);
            nativeInt = j;
        }
    }

    public static final class Cap extends Enum
    {

        public static Cap valueOf(String s)
        {
            return (Cap)Enum.valueOf(android/graphics/Paint$Cap, s);
        }

        public static Cap[] values()
        {
            return $VALUES;
        }

        private static final Cap $VALUES[];
        public static final Cap BUTT;
        public static final Cap ROUND;
        public static final Cap SQUARE;
        final int nativeInt;

        static 
        {
            BUTT = new Cap("BUTT", 0, 0);
            ROUND = new Cap("ROUND", 1, 1);
            SQUARE = new Cap("SQUARE", 2, 2);
            $VALUES = (new Cap[] {
                BUTT, ROUND, SQUARE
            });
        }

        private Cap(String s, int i, int j)
        {
            super(s, i);
            nativeInt = j;
        }
    }

    public static class FontMetrics
    {

        public float ascent;
        public float bottom;
        public float descent;
        public float leading;
        public float top;

        public FontMetrics()
        {
        }
    }

    public static class FontMetricsInt
    {

        public String toString()
        {
            return (new StringBuilder()).append("FontMetricsInt: top=").append(top).append(" ascent=").append(ascent).append(" descent=").append(descent).append(" bottom=").append(bottom).append(" leading=").append(leading).toString();
        }

        public int ascent;
        public int bottom;
        public int descent;
        public int leading;
        public int top;

        public FontMetricsInt()
        {
        }
    }

    public static final class Join extends Enum
    {

        public static Join valueOf(String s)
        {
            return (Join)Enum.valueOf(android/graphics/Paint$Join, s);
        }

        public static Join[] values()
        {
            return $VALUES;
        }

        private static final Join $VALUES[];
        public static final Join BEVEL;
        public static final Join MITER;
        public static final Join ROUND;
        final int nativeInt;

        static 
        {
            MITER = new Join("MITER", 0, 0);
            ROUND = new Join("ROUND", 1, 1);
            BEVEL = new Join("BEVEL", 2, 2);
            $VALUES = (new Join[] {
                MITER, ROUND, BEVEL
            });
        }

        private Join(String s, int i, int j)
        {
            super(s, i);
            nativeInt = j;
        }
    }

    private static class NoImagePreloadHolder
    {

        public static final NativeAllocationRegistry sRegistry = new NativeAllocationRegistry(android/graphics/Paint.getClassLoader(), Paint._2D_wrap0(), 98L);


        private NoImagePreloadHolder()
        {
        }
    }

    public static final class Style extends Enum
    {

        public static Style valueOf(String s)
        {
            return (Style)Enum.valueOf(android/graphics/Paint$Style, s);
        }

        public static Style[] values()
        {
            return $VALUES;
        }

        private static final Style $VALUES[];
        public static final Style FILL;
        public static final Style FILL_AND_STROKE;
        public static final Style STROKE;
        final int nativeInt;

        static 
        {
            FILL = new Style("FILL", 0, 0);
            STROKE = new Style("STROKE", 1, 1);
            FILL_AND_STROKE = new Style("FILL_AND_STROKE", 2, 2);
            $VALUES = (new Style[] {
                FILL, STROKE, FILL_AND_STROKE
            });
        }

        private Style(String s, int i, int j)
        {
            super(s, i);
            nativeInt = j;
        }
    }


    static long _2D_wrap0()
    {
        return nGetNativeFinalizer();
    }

    public Paint()
    {
        this(0);
    }

    public Paint(int i)
    {
        mBidiFlags = 2;
        mNativePaint = nInit();
        NoImagePreloadHolder.sRegistry.registerNativeAllocation(this, mNativePaint);
        setFlags(i | 0x500);
        mInvCompatScaling = 1.0F;
        mCompatScaling = 1.0F;
        setTextLocales(LocaleList.getAdjustedDefault());
    }

    public Paint(Paint paint)
    {
        mBidiFlags = 2;
        mNativePaint = nInitWithPaint(paint.getNativeInstance());
        NoImagePreloadHolder.sRegistry.registerNativeAllocation(this, mNativePaint);
        setClassVariablesFrom(paint);
    }

    private static native float nAscent(long l, long l1);

    private static native int nBreakText(long l, long l1, String s, boolean flag, float f, int i, 
            float af[]);

    private static native int nBreakText(long l, long l1, char ac[], int i, int j, float f, 
            int k, float af[]);

    private static native float nDescent(long l, long l1);

    private static native int nGetAlpha(long l);

    private static native void nGetCharArrayBounds(long l, long l1, char ac[], int i, int j, int k, 
            Rect rect);

    private static native int nGetColor(long l);

    private static native boolean nGetFillPath(long l, long l1, long l2);

    private static native int nGetFlags(long l);

    private static native float nGetFontMetrics(long l, long l1, FontMetrics fontmetrics);

    private static native int nGetFontMetricsInt(long l, long l1, FontMetricsInt fontmetricsint);

    private static native int nGetHinting(long l);

    private static native int nGetHyphenEdit(long l);

    private static native float nGetLetterSpacing(long l);

    private static native long nGetNativeFinalizer();

    private static native int nGetOffsetForAdvance(long l, long l1, char ac[], int i, int j, int k, 
            int i1, boolean flag, float f);

    private static native float nGetRunAdvance(long l, long l1, char ac[], int i, int j, int k, 
            int i1, boolean flag, int j1);

    private static native float nGetStrikeThruPosition(long l, long l1);

    private static native float nGetStrikeThruThickness(long l, long l1);

    private static native void nGetStringBounds(long l, long l1, String s, int i, int j, int k, 
            Rect rect);

    private static native int nGetStrokeCap(long l);

    private static native int nGetStrokeJoin(long l);

    private static native float nGetStrokeMiter(long l);

    private static native float nGetStrokeWidth(long l);

    private static native int nGetStyle(long l);

    private static native float nGetTextAdvances(long l, long l1, String s, int i, int j, int k, 
            int i1, int j1, float af[], int k1);

    private static native float nGetTextAdvances(long l, long l1, char ac[], int i, int j, int k, 
            int i1, int j1, float af[], int k1);

    private static native int nGetTextAlign(long l);

    private static native void nGetTextPath(long l, long l1, int i, String s, int j, int k, 
            float f, float f1, long l2);

    private static native void nGetTextPath(long l, long l1, int i, char ac[], int j, int k, 
            float f, float f1, long l2);

    private native int nGetTextRunCursor(long l, long l1, String s, int i, int j, 
            int k, int i1, int j1);

    private native int nGetTextRunCursor(long l, long l1, char ac[], int i, int j, 
            int k, int i1, int j1);

    private static native float nGetTextScaleX(long l);

    private static native float nGetTextSize(long l);

    private static native float nGetTextSkewX(long l);

    private static native float nGetUnderlinePosition(long l, long l1);

    private static native float nGetUnderlineThickness(long l, long l1);

    private static native float nGetWordSpacing(long l);

    private static native boolean nHasGlyph(long l, long l1, int i, String s);

    private static native boolean nHasShadowLayer(long l);

    private static native long nInit();

    private static native long nInitWithPaint(long l);

    private static native boolean nIsElegantTextHeight(long l);

    private static native void nReset(long l);

    private static native void nSet(long l, long l1);

    private static native void nSetAlpha(long l, int i);

    private static native void nSetAntiAlias(long l, boolean flag);

    private static native void nSetColor(long l, int i);

    private static native long nSetColorFilter(long l, long l1);

    private static native void nSetDither(long l, boolean flag);

    private static native void nSetElegantTextHeight(long l, boolean flag);

    private static native void nSetFakeBoldText(long l, boolean flag);

    private static native void nSetFilterBitmap(long l, boolean flag);

    private static native void nSetFlags(long l, int i);

    private static native void nSetFontFeatureSettings(long l, String s);

    private static native void nSetHinting(long l, int i);

    private static native void nSetHyphenEdit(long l, int i);

    private static native void nSetLetterSpacing(long l, float f);

    private static native void nSetLinearText(long l, boolean flag);

    private static native long nSetMaskFilter(long l, long l1);

    private static native long nSetPathEffect(long l, long l1);

    private static native long nSetShader(long l, long l1);

    private static native void nSetShadowLayer(long l, float f, float f1, float f2, int i);

    private static native void nSetStrikeThruText(long l, boolean flag);

    private static native void nSetStrokeCap(long l, int i);

    private static native void nSetStrokeJoin(long l, int i);

    private static native void nSetStrokeMiter(long l, float f);

    private static native void nSetStrokeWidth(long l, float f);

    private static native void nSetStyle(long l, int i);

    private static native void nSetSubpixelText(long l, boolean flag);

    private static native void nSetTextAlign(long l, int i);

    private static native int nSetTextLocales(long l, String s);

    private static native void nSetTextLocalesByMinikinLangListId(long l, int i);

    private static native void nSetTextScaleX(long l, float f);

    private static native void nSetTextSize(long l, float f);

    private static native void nSetTextSkewX(long l, float f);

    private static native long nSetTypeface(long l, long l1);

    private static native void nSetUnderlineText(long l, boolean flag);

    private static native void nSetWordSpacing(long l, float f);

    private static native void nSetXfermode(long l, int i);

    private void setClassVariablesFrom(Paint paint)
    {
        mColorFilter = paint.mColorFilter;
        mMaskFilter = paint.mMaskFilter;
        mPathEffect = paint.mPathEffect;
        mShader = paint.mShader;
        mNativeShader = paint.mNativeShader;
        mTypeface = paint.mTypeface;
        mNativeTypeface = paint.mNativeTypeface;
        mXfermode = paint.mXfermode;
        mHasCompatScaling = paint.mHasCompatScaling;
        mCompatScaling = paint.mCompatScaling;
        mInvCompatScaling = paint.mInvCompatScaling;
        mBidiFlags = paint.mBidiFlags;
        mLocales = paint.mLocales;
        mFontFeatureSettings = paint.mFontFeatureSettings;
        mFontVariationSettings = paint.mFontVariationSettings;
        mShadowLayerRadius = paint.mShadowLayerRadius;
        mShadowLayerDx = paint.mShadowLayerDx;
        mShadowLayerDy = paint.mShadowLayerDy;
        mShadowLayerColor = paint.mShadowLayerColor;
    }

    private void syncTextLocalesWithMinikin()
    {
        String s = mLocales.toLanguageTags();
        Object obj = sCacheLock;
        obj;
        JVM INSTR monitorenter ;
        Integer integer = (Integer)sMinikinLangListIdCache.get(s);
        if(integer != null)
            break MISSING_BLOCK_LABEL_55;
        int i = nSetTextLocales(mNativePaint, s);
        sMinikinLangListIdCache.put(s, Integer.valueOf(i));
        obj;
        JVM INSTR monitorexit ;
        return;
        obj;
        JVM INSTR monitorexit ;
        nSetTextLocalesByMinikinLangListId(mNativePaint, integer.intValue());
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public float ascent()
    {
        return nAscent(mNativePaint, mNativeTypeface);
    }

    public int breakText(CharSequence charsequence, int i, int j, boolean flag, float f, float af[])
    {
        if(charsequence == null)
            throw new IllegalArgumentException("text cannot be null");
        if((i | j | j - i | charsequence.length() - j) < 0)
            throw new IndexOutOfBoundsException();
        if(charsequence.length() == 0 || i == j)
            return 0;
        if(i == 0 && (charsequence instanceof String) && j == charsequence.length())
            return breakText((String)charsequence, flag, f, af);
        char ac[] = TemporaryBuffer.obtain(j - i);
        TextUtils.getChars(charsequence, i, j, ac, 0);
        if(flag)
            i = breakText(ac, 0, j - i, f, af);
        else
            i = breakText(ac, 0, -(j - i), f, af);
        TemporaryBuffer.recycle(ac);
        return i;
    }

    public int breakText(String s, boolean flag, float f, float af[])
    {
        if(s == null)
            throw new IllegalArgumentException("text cannot be null");
        if(s.length() == 0)
            return 0;
        if(!mHasCompatScaling)
            return nBreakText(mNativePaint, mNativeTypeface, s, flag, f, mBidiFlags, af);
        float f1 = getTextSize();
        setTextSize(mCompatScaling * f1);
        int i = nBreakText(mNativePaint, mNativeTypeface, s, flag, f * mCompatScaling, mBidiFlags, af);
        setTextSize(f1);
        if(af != null)
            af[0] = af[0] * mInvCompatScaling;
        return i;
    }

    public int breakText(char ac[], int i, int j, float f, float af[])
    {
        if(ac == null)
            throw new IllegalArgumentException("text cannot be null");
        if(i < 0 || ac.length - i < Math.abs(j))
            throw new ArrayIndexOutOfBoundsException();
        if(ac.length == 0 || j == 0)
            return 0;
        if(!mHasCompatScaling)
            return nBreakText(mNativePaint, mNativeTypeface, ac, i, j, f, mBidiFlags, af);
        float f1 = getTextSize();
        setTextSize(mCompatScaling * f1);
        i = nBreakText(mNativePaint, mNativeTypeface, ac, i, j, f * mCompatScaling, mBidiFlags, af);
        setTextSize(f1);
        if(af != null)
            af[0] = af[0] * mInvCompatScaling;
        return i;
    }

    public void clearShadowLayer()
    {
        setShadowLayer(0.0F, 0.0F, 0.0F, 0);
    }

    public float descent()
    {
        return nDescent(mNativePaint, mNativeTypeface);
    }

    public int getAlpha()
    {
        return nGetAlpha(mNativePaint);
    }

    public int getBidiFlags()
    {
        return mBidiFlags;
    }

    public int getColor()
    {
        return nGetColor(mNativePaint);
    }

    public ColorFilter getColorFilter()
    {
        return mColorFilter;
    }

    public boolean getFillPath(Path path, Path path1)
    {
        return nGetFillPath(mNativePaint, path.readOnlyNI(), path1.mutateNI());
    }

    public int getFlags()
    {
        return nGetFlags(mNativePaint);
    }

    public String getFontFeatureSettings()
    {
        return mFontFeatureSettings;
    }

    public float getFontMetrics(FontMetrics fontmetrics)
    {
        return nGetFontMetrics(mNativePaint, mNativeTypeface, fontmetrics);
    }

    public FontMetrics getFontMetrics()
    {
        FontMetrics fontmetrics = new FontMetrics();
        getFontMetrics(fontmetrics);
        return fontmetrics;
    }

    public int getFontMetricsInt(FontMetricsInt fontmetricsint)
    {
        return nGetFontMetricsInt(mNativePaint, mNativeTypeface, fontmetricsint);
    }

    public FontMetricsInt getFontMetricsInt()
    {
        FontMetricsInt fontmetricsint = new FontMetricsInt();
        getFontMetricsInt(fontmetricsint);
        return fontmetricsint;
    }

    public float getFontSpacing()
    {
        return getFontMetrics(null);
    }

    public String getFontVariationSettings()
    {
        return mFontVariationSettings;
    }

    public int getHinting()
    {
        return nGetHinting(mNativePaint);
    }

    public int getHyphenEdit()
    {
        return nGetHyphenEdit(mNativePaint);
    }

    public float getLetterSpacing()
    {
        return nGetLetterSpacing(mNativePaint);
    }

    public MaskFilter getMaskFilter()
    {
        return mMaskFilter;
    }

    public long getNativeInstance()
    {
        long l;
        if(mShader == null)
            l = 0L;
        else
            l = mShader.getNativeInstance();
        if(l != mNativeShader)
        {
            mNativeShader = l;
            nSetShader(mNativePaint, mNativeShader);
        }
        if(mColorFilter == null)
            l = 0L;
        else
            l = mColorFilter.getNativeInstance();
        if(l != mNativeColorFilter)
        {
            mNativeColorFilter = l;
            nSetColorFilter(mNativePaint, mNativeColorFilter);
        }
        return mNativePaint;
    }

    public int getOffsetForAdvance(CharSequence charsequence, int i, int j, int k, int l, boolean flag, float f)
    {
        if(charsequence == null)
            throw new IllegalArgumentException("text cannot be null");
        if((k | i | j | l | i - k | j - i | l - j | charsequence.length() - l) < 0)
        {
            throw new IndexOutOfBoundsException();
        } else
        {
            char ac[] = TemporaryBuffer.obtain(l - k);
            TextUtils.getChars(charsequence, k, l, ac, 0);
            i = getOffsetForAdvance(ac, i - k, j - k, 0, l - k, flag, f);
            TemporaryBuffer.recycle(ac);
            return i + k;
        }
    }

    public int getOffsetForAdvance(char ac[], int i, int j, int k, int l, boolean flag, float f)
    {
        if(ac == null)
            throw new IllegalArgumentException("text cannot be null");
        if((k | i | j | l | i - k | j - i | l - j | ac.length - l) < 0)
            throw new IndexOutOfBoundsException();
        else
            return nGetOffsetForAdvance(mNativePaint, mNativeTypeface, ac, i, j, k, l, flag, f);
    }

    public PathEffect getPathEffect()
    {
        return mPathEffect;
    }

    public Rasterizer getRasterizer()
    {
        return null;
    }

    public float getRunAdvance(CharSequence charsequence, int i, int j, int k, int l, boolean flag, int i1)
    {
        if(charsequence == null)
            throw new IllegalArgumentException("text cannot be null");
        if((k | i | i1 | j | l | i - k | i1 - i | j - i1 | l - j | charsequence.length() - l) < 0)
            throw new IndexOutOfBoundsException();
        if(j == i)
        {
            return 0.0F;
        } else
        {
            char ac[] = TemporaryBuffer.obtain(l - k);
            TextUtils.getChars(charsequence, k, l, ac, 0);
            float f = getRunAdvance(ac, i - k, j - k, 0, l - k, flag, i1 - k);
            TemporaryBuffer.recycle(ac);
            return f;
        }
    }

    public float getRunAdvance(char ac[], int i, int j, int k, int l, boolean flag, int i1)
    {
        if(ac == null)
            throw new IllegalArgumentException("text cannot be null");
        if((k | i | i1 | j | l | i - k | i1 - i | j - i1 | l - j | ac.length - l) < 0)
            throw new IndexOutOfBoundsException();
        if(j == i)
            return 0.0F;
        else
            return nGetRunAdvance(mNativePaint, mNativeTypeface, ac, i, j, k, l, flag, i1);
    }

    public Shader getShader()
    {
        return mShader;
    }

    public float getStrikeThruPosition()
    {
        return nGetStrikeThruPosition(mNativePaint, mNativeTypeface);
    }

    public float getStrikeThruThickness()
    {
        return nGetStrikeThruThickness(mNativePaint, mNativeTypeface);
    }

    public Cap getStrokeCap()
    {
        return sCapArray[nGetStrokeCap(mNativePaint)];
    }

    public Join getStrokeJoin()
    {
        return sJoinArray[nGetStrokeJoin(mNativePaint)];
    }

    public float getStrokeMiter()
    {
        return nGetStrokeMiter(mNativePaint);
    }

    public float getStrokeWidth()
    {
        return nGetStrokeWidth(mNativePaint);
    }

    public Style getStyle()
    {
        return sStyleArray[nGetStyle(mNativePaint)];
    }

    public Align getTextAlign()
    {
        return sAlignArray[nGetTextAlign(mNativePaint)];
    }

    public void getTextBounds(CharSequence charsequence, int i, int j, Rect rect)
    {
        if((i | j | j - i | charsequence.length() - j) < 0)
            throw new IndexOutOfBoundsException();
        if(rect == null)
        {
            throw new NullPointerException("need bounds Rect");
        } else
        {
            char ac[] = TemporaryBuffer.obtain(j - i);
            TextUtils.getChars(charsequence, i, j, ac, 0);
            getTextBounds(ac, 0, j - i, rect);
            TemporaryBuffer.recycle(ac);
            return;
        }
    }

    public void getTextBounds(String s, int i, int j, Rect rect)
    {
        if((i | j | j - i | s.length() - j) < 0)
            throw new IndexOutOfBoundsException();
        if(rect == null)
        {
            throw new NullPointerException("need bounds Rect");
        } else
        {
            nGetStringBounds(mNativePaint, mNativeTypeface, s, i, j, mBidiFlags, rect);
            return;
        }
    }

    public void getTextBounds(char ac[], int i, int j, Rect rect)
    {
        if((i | j) < 0 || i + j > ac.length)
            throw new ArrayIndexOutOfBoundsException();
        if(rect == null)
        {
            throw new NullPointerException("need bounds Rect");
        } else
        {
            nGetCharArrayBounds(mNativePaint, mNativeTypeface, ac, i, j, mBidiFlags, rect);
            return;
        }
    }

    public Locale getTextLocale()
    {
        return mLocales.get(0);
    }

    public LocaleList getTextLocales()
    {
        return mLocales;
    }

    public void getTextPath(String s, int i, int j, float f, float f1, Path path)
    {
        if((i | j | j - i | s.length() - j) < 0)
        {
            throw new IndexOutOfBoundsException();
        } else
        {
            nGetTextPath(mNativePaint, mNativeTypeface, mBidiFlags, s, i, j, f, f1, path.mutateNI());
            return;
        }
    }

    public void getTextPath(char ac[], int i, int j, float f, float f1, Path path)
    {
        if((i | j) < 0 || i + j > ac.length)
        {
            throw new ArrayIndexOutOfBoundsException();
        } else
        {
            nGetTextPath(mNativePaint, mNativeTypeface, mBidiFlags, ac, i, j, f, f1, path.mutateNI());
            return;
        }
    }

    public float getTextRunAdvances(CharSequence charsequence, int i, int j, int k, int l, boolean flag, float af[], 
            int i1)
    {
        if(charsequence == null)
            throw new IllegalArgumentException("text cannot be null");
        int j1 = charsequence.length();
        int k1;
        if(af == null)
            k1 = 0;
        else
            k1 = af.length - i1 - (j - i);
        if((k1 | (j1 - l | (i | j | k | l | i1 | j - i | i - k | l - j))) < 0)
            throw new IndexOutOfBoundsException();
        if(charsequence instanceof String)
            return getTextRunAdvances((String)charsequence, i, j, k, l, flag, af, i1);
        if((charsequence instanceof SpannedString) || (charsequence instanceof SpannableString))
            return getTextRunAdvances(charsequence.toString(), i, j, k, l, flag, af, i1);
        if(charsequence instanceof GraphicsOperations)
            return ((GraphicsOperations)charsequence).getTextRunAdvances(i, j, k, l, flag, af, i1, this);
        if(charsequence.length() == 0 || j == i)
        {
            return 0.0F;
        } else
        {
            int l1 = l - k;
            char ac[] = TemporaryBuffer.obtain(l1);
            TextUtils.getChars(charsequence, k, l, ac, 0);
            float f = getTextRunAdvances(ac, i - k, j - i, 0, l1, flag, af, i1);
            TemporaryBuffer.recycle(ac);
            return f;
        }
    }

    public float getTextRunAdvances(String s, int i, int j, int k, int l, boolean flag, float af[], 
            int i1)
    {
        if(s == null)
            throw new IllegalArgumentException("text cannot be null");
        int j1 = s.length();
        int k1;
        if(af == null)
            k1 = 0;
        else
            k1 = af.length - i1 - (j - i);
        if((k1 | (j1 - l | (i | j | k | l | i1 | j - i | i - k | l - j))) < 0)
            throw new IndexOutOfBoundsException();
        if(s.length() == 0 || i == j)
            return 0.0F;
        if(!mHasCompatScaling)
        {
            long l1 = mNativePaint;
            long l3 = mNativeTypeface;
            if(flag)
                k1 = 5;
            else
                k1 = 4;
            return nGetTextAdvances(l1, l3, s, i, j, k, l, k1, af, i1);
        }
        float f = getTextSize();
        setTextSize(mCompatScaling * f);
        long l4 = mNativePaint;
        long l2 = mNativeTypeface;
        float f1;
        if(flag)
            k1 = 5;
        else
            k1 = 4;
        f1 = nGetTextAdvances(l4, l2, s, i, j, k, l, k1, af, i1);
        setTextSize(f);
        if(af != null)
            for(k = i1; k < i1 + (j - i); k++)
                af[k] = af[k] * mInvCompatScaling;

        return mInvCompatScaling * f1;
    }

    public float getTextRunAdvances(char ac[], int i, int j, int k, int l, boolean flag, float af[], 
            int i1)
    {
        if(ac == null)
            throw new IllegalArgumentException("text cannot be null");
        int j1 = ac.length;
        int k1;
        if(af == null)
            k1 = 0;
        else
            k1 = af.length - (i1 + j);
        if((k1 | (j1 - (k + l) | (i | j | k | l | i1 | i - k | l - j | (k + l) - (i + j)))) < 0)
            throw new IndexOutOfBoundsException();
        if(ac.length == 0 || j == 0)
            return 0.0F;
        if(!mHasCompatScaling)
        {
            long l1 = mNativePaint;
            long l3 = mNativeTypeface;
            if(flag)
                k1 = 5;
            else
                k1 = 4;
            return nGetTextAdvances(l1, l3, ac, i, j, k, l, k1, af, i1);
        }
        float f = getTextSize();
        setTextSize(mCompatScaling * f);
        long l4 = mNativePaint;
        long l2 = mNativeTypeface;
        float f1;
        if(flag)
            k1 = 5;
        else
            k1 = 4;
        f1 = nGetTextAdvances(l4, l2, ac, i, j, k, l, k1, af, i1);
        setTextSize(f);
        if(af != null)
            for(i = i1; i < i1 + j; i++)
                af[i] = af[i] * mInvCompatScaling;

        return mInvCompatScaling * f1;
    }

    public int getTextRunCursor(CharSequence charsequence, int i, int j, int k, int l, int i1)
    {
        if((charsequence instanceof String) || (charsequence instanceof SpannedString) || (charsequence instanceof SpannableString))
            return getTextRunCursor(charsequence.toString(), i, j, k, l, i1);
        if(charsequence instanceof GraphicsOperations)
            return ((GraphicsOperations)charsequence).getTextRunCursor(i, j, k, l, i1, this);
        int j1 = j - i;
        char ac[] = TemporaryBuffer.obtain(j1);
        TextUtils.getChars(charsequence, i, j, ac, 0);
        j = getTextRunCursor(ac, 0, j1, k, l - i, i1);
        TemporaryBuffer.recycle(ac);
        if(j == -1)
            i = -1;
        else
            i = j + i;
        return i;
    }

    public int getTextRunCursor(String s, int i, int j, int k, int l, int i1)
    {
        if((i | j | l | j - i | l - i | j - l | s.length() - j | i1) < 0 || i1 > 4)
            throw new IndexOutOfBoundsException();
        else
            return nGetTextRunCursor(mNativePaint, mNativeTypeface, s, i, j, k, l, i1);
    }

    public int getTextRunCursor(char ac[], int i, int j, int k, int l, int i1)
    {
        int j1 = i + j;
        if((i | j1 | l | j1 - i | l - i | j1 - l | ac.length - j1 | i1) < 0 || i1 > 4)
            throw new IndexOutOfBoundsException();
        else
            return nGetTextRunCursor(mNativePaint, mNativeTypeface, ac, i, j, k, l, i1);
    }

    public float getTextScaleX()
    {
        return nGetTextScaleX(mNativePaint);
    }

    public float getTextSize()
    {
        return nGetTextSize(mNativePaint);
    }

    public float getTextSkewX()
    {
        return nGetTextSkewX(mNativePaint);
    }

    public int getTextWidths(CharSequence charsequence, int i, int j, float af[])
    {
        if(charsequence == null)
            throw new IllegalArgumentException("text cannot be null");
        if((i | j | j - i | charsequence.length() - j) < 0)
            throw new IndexOutOfBoundsException();
        if(j - i > af.length)
            throw new ArrayIndexOutOfBoundsException();
        if(charsequence.length() == 0 || i == j)
            return 0;
        if(charsequence instanceof String)
            return getTextWidths((String)charsequence, i, j, af);
        if((charsequence instanceof SpannedString) || (charsequence instanceof SpannableString))
            return getTextWidths(charsequence.toString(), i, j, af);
        if(charsequence instanceof GraphicsOperations)
        {
            return ((GraphicsOperations)charsequence).getTextWidths(i, j, af, this);
        } else
        {
            char ac[] = TemporaryBuffer.obtain(j - i);
            TextUtils.getChars(charsequence, i, j, ac, 0);
            i = getTextWidths(ac, 0, j - i, af);
            TemporaryBuffer.recycle(ac);
            return i;
        }
    }

    public int getTextWidths(String s, int i, int j, float af[])
    {
        if(s == null)
            throw new IllegalArgumentException("text cannot be null");
        if((i | j | j - i | s.length() - j) < 0)
            throw new IndexOutOfBoundsException();
        if(j - i > af.length)
            throw new ArrayIndexOutOfBoundsException();
        if(s.length() == 0 || i == j)
            return 0;
        if(!mHasCompatScaling)
        {
            nGetTextAdvances(mNativePaint, mNativeTypeface, s, i, j, i, j, mBidiFlags, af, 0);
            return j - i;
        }
        float f = getTextSize();
        setTextSize(mCompatScaling * f);
        nGetTextAdvances(mNativePaint, mNativeTypeface, s, i, j, i, j, mBidiFlags, af, 0);
        setTextSize(f);
        for(int k = 0; k < j - i; k++)
            af[k] = af[k] * mInvCompatScaling;

        return j - i;
    }

    public int getTextWidths(String s, float af[])
    {
        return getTextWidths(s, 0, s.length(), af);
    }

    public int getTextWidths(char ac[], int i, int j, float af[])
    {
        if(ac == null)
            throw new IllegalArgumentException("text cannot be null");
        while((i | j) < 0 || i + j > ac.length || j > af.length) 
            throw new ArrayIndexOutOfBoundsException();
        if(ac.length == 0 || j == 0)
            return 0;
        if(!mHasCompatScaling)
        {
            nGetTextAdvances(mNativePaint, mNativeTypeface, ac, i, j, i, j, mBidiFlags, af, 0);
            return j;
        }
        float f = getTextSize();
        setTextSize(mCompatScaling * f);
        nGetTextAdvances(mNativePaint, mNativeTypeface, ac, i, j, i, j, mBidiFlags, af, 0);
        setTextSize(f);
        for(i = 0; i < j; i++)
            af[i] = af[i] * mInvCompatScaling;

        return j;
    }

    public Typeface getTypeface()
    {
        return mTypeface;
    }

    public float getUnderlinePosition()
    {
        return nGetUnderlinePosition(mNativePaint, mNativeTypeface);
    }

    public float getUnderlineThickness()
    {
        return nGetUnderlineThickness(mNativePaint, mNativeTypeface);
    }

    public float getWordSpacing()
    {
        return nGetWordSpacing(mNativePaint);
    }

    public Xfermode getXfermode()
    {
        return mXfermode;
    }

    public boolean hasEqualAttributes(Paint paint)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mColorFilter == paint.mColorFilter)
        {
            flag1 = flag;
            if(mMaskFilter == paint.mMaskFilter)
            {
                flag1 = flag;
                if(mPathEffect == paint.mPathEffect)
                {
                    flag1 = flag;
                    if(mShader == paint.mShader)
                    {
                        flag1 = flag;
                        if(mTypeface == paint.mTypeface)
                        {
                            flag1 = flag;
                            if(mXfermode == paint.mXfermode)
                            {
                                flag1 = flag;
                                if(mHasCompatScaling == paint.mHasCompatScaling)
                                {
                                    flag1 = flag;
                                    if(mCompatScaling == paint.mCompatScaling)
                                    {
                                        flag1 = flag;
                                        if(mInvCompatScaling == paint.mInvCompatScaling)
                                        {
                                            flag1 = flag;
                                            if(mBidiFlags == paint.mBidiFlags)
                                            {
                                                flag1 = flag;
                                                if(mLocales.equals(paint.mLocales))
                                                {
                                                    flag1 = flag;
                                                    if(TextUtils.equals(mFontFeatureSettings, paint.mFontFeatureSettings))
                                                    {
                                                        flag1 = flag;
                                                        if(TextUtils.equals(mFontVariationSettings, paint.mFontVariationSettings))
                                                        {
                                                            flag1 = flag;
                                                            if(mShadowLayerRadius == paint.mShadowLayerRadius)
                                                            {
                                                                flag1 = flag;
                                                                if(mShadowLayerDx == paint.mShadowLayerDx)
                                                                {
                                                                    flag1 = flag;
                                                                    if(mShadowLayerDy == paint.mShadowLayerDy)
                                                                    {
                                                                        flag1 = flag;
                                                                        if(mShadowLayerColor == paint.mShadowLayerColor)
                                                                        {
                                                                            flag1 = flag;
                                                                            if(getFlags() == paint.getFlags())
                                                                            {
                                                                                flag1 = flag;
                                                                                if(getHinting() == paint.getHinting())
                                                                                {
                                                                                    flag1 = flag;
                                                                                    if(getStyle() == paint.getStyle())
                                                                                    {
                                                                                        flag1 = flag;
                                                                                        if(getColor() == paint.getColor())
                                                                                        {
                                                                                            flag1 = flag;
                                                                                            if(getStrokeWidth() == paint.getStrokeWidth())
                                                                                            {
                                                                                                flag1 = flag;
                                                                                                if(getStrokeMiter() == paint.getStrokeMiter())
                                                                                                {
                                                                                                    flag1 = flag;
                                                                                                    if(getStrokeCap() == paint.getStrokeCap())
                                                                                                    {
                                                                                                        flag1 = flag;
                                                                                                        if(getStrokeJoin() == paint.getStrokeJoin())
                                                                                                        {
                                                                                                            flag1 = flag;
                                                                                                            if(getTextAlign() == paint.getTextAlign())
                                                                                                            {
                                                                                                                flag1 = flag;
                                                                                                                if(isElegantTextHeight() == paint.isElegantTextHeight())
                                                                                                                {
                                                                                                                    flag1 = flag;
                                                                                                                    if(getTextSize() == paint.getTextSize())
                                                                                                                    {
                                                                                                                        flag1 = flag;
                                                                                                                        if(getTextScaleX() == paint.getTextScaleX())
                                                                                                                        {
                                                                                                                            flag1 = flag;
                                                                                                                            if(getTextSkewX() == paint.getTextSkewX())
                                                                                                                            {
                                                                                                                                flag1 = flag;
                                                                                                                                if(getLetterSpacing() == paint.getLetterSpacing())
                                                                                                                                {
                                                                                                                                    flag1 = flag;
                                                                                                                                    if(getWordSpacing() == paint.getWordSpacing())
                                                                                                                                    {
                                                                                                                                        flag1 = flag;
                                                                                                                                        if(getHyphenEdit() == paint.getHyphenEdit())
                                                                                                                                            flag1 = true;
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return flag1;
    }

    public boolean hasGlyph(String s)
    {
        return nHasGlyph(mNativePaint, mNativeTypeface, mBidiFlags, s);
    }

    public boolean hasShadowLayer()
    {
        return nHasShadowLayer(mNativePaint);
    }

    public final boolean isAntiAlias()
    {
        boolean flag = false;
        if((getFlags() & 1) != 0)
            flag = true;
        return flag;
    }

    public final boolean isDither()
    {
        boolean flag = false;
        if((getFlags() & 4) != 0)
            flag = true;
        return flag;
    }

    public boolean isElegantTextHeight()
    {
        return nIsElegantTextHeight(mNativePaint);
    }

    public final boolean isFakeBoldText()
    {
        boolean flag = false;
        if((getFlags() & 0x20) != 0)
            flag = true;
        return flag;
    }

    public final boolean isFilterBitmap()
    {
        boolean flag = false;
        if((getFlags() & 2) != 0)
            flag = true;
        return flag;
    }

    public final boolean isLinearText()
    {
        boolean flag = false;
        if((getFlags() & 0x40) != 0)
            flag = true;
        return flag;
    }

    public final boolean isStrikeThruText()
    {
        boolean flag = false;
        if((getFlags() & 0x10) != 0)
            flag = true;
        return flag;
    }

    public final boolean isSubpixelText()
    {
        boolean flag = false;
        if((getFlags() & 0x80) != 0)
            flag = true;
        return flag;
    }

    public final boolean isUnderlineText()
    {
        boolean flag = false;
        if((getFlags() & 8) != 0)
            flag = true;
        return flag;
    }

    public float measureText(CharSequence charsequence, int i, int j)
    {
        if(charsequence == null)
            throw new IllegalArgumentException("text cannot be null");
        if((i | j | j - i | charsequence.length() - j) < 0)
            throw new IndexOutOfBoundsException();
        if(charsequence.length() == 0 || i == j)
            return 0.0F;
        if(charsequence instanceof String)
            return measureText((String)charsequence, i, j);
        if((charsequence instanceof SpannedString) || (charsequence instanceof SpannableString))
            return measureText(charsequence.toString(), i, j);
        if(charsequence instanceof GraphicsOperations)
        {
            return ((GraphicsOperations)charsequence).measureText(i, j, this);
        } else
        {
            char ac[] = TemporaryBuffer.obtain(j - i);
            TextUtils.getChars(charsequence, i, j, ac, 0);
            float f = measureText(ac, 0, j - i);
            TemporaryBuffer.recycle(ac);
            return f;
        }
    }

    public float measureText(String s)
    {
        if(s == null)
            throw new IllegalArgumentException("text cannot be null");
        else
            return measureText(s, 0, s.length());
    }

    public float measureText(String s, int i, int j)
    {
        if(s == null)
            throw new IllegalArgumentException("text cannot be null");
        if((i | j | j - i | s.length() - j) < 0)
            throw new IndexOutOfBoundsException();
        if(s.length() == 0 || i == j)
            return 0.0F;
        if(!mHasCompatScaling)
        {
            return (float)Math.ceil(nGetTextAdvances(mNativePaint, mNativeTypeface, s, i, j, i, j, mBidiFlags, null, 0));
        } else
        {
            float f = getTextSize();
            setTextSize(mCompatScaling * f);
            float f1 = nGetTextAdvances(mNativePaint, mNativeTypeface, s, i, j, i, j, mBidiFlags, null, 0);
            setTextSize(f);
            return (float)Math.ceil(mInvCompatScaling * f1);
        }
    }

    public float measureText(char ac[], int i, int j)
    {
        if(ac == null)
            throw new IllegalArgumentException("text cannot be null");
        if((i | j) < 0 || i + j > ac.length)
            throw new ArrayIndexOutOfBoundsException();
        if(ac.length == 0 || j == 0)
            return 0.0F;
        if(!mHasCompatScaling)
        {
            return (float)Math.ceil(nGetTextAdvances(mNativePaint, mNativeTypeface, ac, i, j, i, j, mBidiFlags, null, 0));
        } else
        {
            float f = getTextSize();
            setTextSize(mCompatScaling * f);
            float f1 = nGetTextAdvances(mNativePaint, mNativeTypeface, ac, i, j, i, j, mBidiFlags, null, 0);
            setTextSize(f);
            return (float)Math.ceil(mInvCompatScaling * f1);
        }
    }

    public void reset()
    {
        nReset(mNativePaint);
        setFlags(1280);
        mColorFilter = null;
        mMaskFilter = null;
        mPathEffect = null;
        mShader = null;
        mNativeShader = 0L;
        mTypeface = null;
        mNativeTypeface = 0L;
        mXfermode = null;
        mHasCompatScaling = false;
        mCompatScaling = 1.0F;
        mInvCompatScaling = 1.0F;
        mBidiFlags = 2;
        setTextLocales(LocaleList.getAdjustedDefault());
        setElegantTextHeight(false);
        mFontFeatureSettings = null;
        mFontVariationSettings = null;
        mShadowLayerRadius = 0.0F;
        mShadowLayerDx = 0.0F;
        mShadowLayerDy = 0.0F;
        mShadowLayerColor = 0;
    }

    public void set(Paint paint)
    {
        if(this != paint)
        {
            nSet(mNativePaint, paint.mNativePaint);
            setClassVariablesFrom(paint);
        }
    }

    public void setARGB(int i, int j, int k, int l)
    {
        setColor(i << 24 | j << 16 | k << 8 | l);
    }

    public void setAlpha(int i)
    {
        nSetAlpha(mNativePaint, i);
    }

    public void setAntiAlias(boolean flag)
    {
        nSetAntiAlias(mNativePaint, flag);
    }

    public void setBidiFlags(int i)
    {
        i &= 7;
        if(i > 5)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("unknown bidi flag: ").append(i).toString());
        } else
        {
            mBidiFlags = i;
            return;
        }
    }

    public void setColor(int i)
    {
        nSetColor(mNativePaint, i);
    }

    public ColorFilter setColorFilter(ColorFilter colorfilter)
    {
        if(mColorFilter != colorfilter)
            mNativeColorFilter = -1L;
        mColorFilter = colorfilter;
        return colorfilter;
    }

    public void setCompatibilityScaling(float f)
    {
        if((double)f == 1.0D)
        {
            mHasCompatScaling = false;
            mInvCompatScaling = 1.0F;
            mCompatScaling = 1.0F;
        } else
        {
            mHasCompatScaling = true;
            mCompatScaling = f;
            mInvCompatScaling = 1.0F / f;
        }
    }

    public void setDither(boolean flag)
    {
        nSetDither(mNativePaint, flag);
    }

    public void setElegantTextHeight(boolean flag)
    {
        nSetElegantTextHeight(mNativePaint, flag);
    }

    public void setFakeBoldText(boolean flag)
    {
        nSetFakeBoldText(mNativePaint, flag);
    }

    public void setFilterBitmap(boolean flag)
    {
        nSetFilterBitmap(mNativePaint, flag);
    }

    public void setFlags(int i)
    {
        nSetFlags(mNativePaint, i);
    }

    public void setFontFeatureSettings(String s)
    {
        String s1 = s;
        if(s != null)
        {
            s1 = s;
            if(s.equals(""))
                s1 = null;
        }
        while(s1 == null && mFontFeatureSettings == null || s1 != null && s1.equals(mFontFeatureSettings)) 
            return;
        mFontFeatureSettings = s1;
        nSetFontFeatureSettings(mNativePaint, s1);
    }

    public boolean setFontVariationSettings(String s)
    {
        String s1 = TextUtils.nullIfEmpty(s);
        if(s1 == mFontVariationSettings || s1 != null && s1.equals(mFontVariationSettings))
            return true;
        if(s1 == null || s1.length() == 0)
        {
            mFontVariationSettings = null;
            setTypeface(Typeface.createFromTypefaceWithVariation(mTypeface, Collections.emptyList()));
            return true;
        }
        FontVariationAxis afontvariationaxis[];
        ArrayList arraylist;
        int i;
        if(mTypeface == null)
            s = Typeface.DEFAULT;
        else
            s = mTypeface;
        afontvariationaxis = FontVariationAxis.fromFontVariationSettings(s1);
        arraylist = new ArrayList();
        i = afontvariationaxis.length;
        for(int j = 0; j < i; j++)
        {
            FontVariationAxis fontvariationaxis = afontvariationaxis[j];
            if(s.isSupportedAxes(fontvariationaxis.getOpenTypeTagValue()))
                arraylist.add(fontvariationaxis);
        }

        if(arraylist.isEmpty())
        {
            return false;
        } else
        {
            mFontVariationSettings = s1;
            setTypeface(Typeface.createFromTypefaceWithVariation(s, arraylist));
            return true;
        }
    }

    public void setHinting(int i)
    {
        nSetHinting(mNativePaint, i);
    }

    public void setHyphenEdit(int i)
    {
        nSetHyphenEdit(mNativePaint, i);
    }

    public void setLetterSpacing(float f)
    {
        nSetLetterSpacing(mNativePaint, f);
    }

    public void setLinearText(boolean flag)
    {
        nSetLinearText(mNativePaint, flag);
    }

    public MaskFilter setMaskFilter(MaskFilter maskfilter)
    {
        long l = 0L;
        if(maskfilter != null)
            l = maskfilter.native_instance;
        nSetMaskFilter(mNativePaint, l);
        mMaskFilter = maskfilter;
        return maskfilter;
    }

    public PathEffect setPathEffect(PathEffect patheffect)
    {
        long l = 0L;
        if(patheffect != null)
            l = patheffect.native_instance;
        nSetPathEffect(mNativePaint, l);
        mPathEffect = patheffect;
        return patheffect;
    }

    public Rasterizer setRasterizer(Rasterizer rasterizer)
    {
        return rasterizer;
    }

    public Shader setShader(Shader shader)
    {
        if(mShader != shader)
        {
            mNativeShader = -1L;
            nSetShader(mNativePaint, 0L);
        }
        mShader = shader;
        return shader;
    }

    public void setShadowLayer(float f, float f1, float f2, int i)
    {
        mShadowLayerRadius = f;
        mShadowLayerDx = f1;
        mShadowLayerDy = f2;
        mShadowLayerColor = i;
        nSetShadowLayer(mNativePaint, f, f1, f2, i);
    }

    public void setStrikeThruText(boolean flag)
    {
        nSetStrikeThruText(mNativePaint, flag);
    }

    public void setStrokeCap(Cap cap)
    {
        nSetStrokeCap(mNativePaint, cap.nativeInt);
    }

    public void setStrokeJoin(Join join)
    {
        nSetStrokeJoin(mNativePaint, join.nativeInt);
    }

    public void setStrokeMiter(float f)
    {
        nSetStrokeMiter(mNativePaint, f);
    }

    public void setStrokeWidth(float f)
    {
        nSetStrokeWidth(mNativePaint, f);
    }

    public void setStyle(Style style)
    {
        nSetStyle(mNativePaint, style.nativeInt);
    }

    public void setSubpixelText(boolean flag)
    {
        nSetSubpixelText(mNativePaint, flag);
    }

    public void setTextAlign(Align align)
    {
        nSetTextAlign(mNativePaint, align.nativeInt);
    }

    public void setTextLocale(Locale locale)
    {
        if(locale == null)
            throw new IllegalArgumentException("locale cannot be null");
        if(mLocales != null && mLocales.size() == 1 && locale.equals(mLocales.get(0)))
        {
            return;
        } else
        {
            mLocales = new LocaleList(new Locale[] {
                locale
            });
            syncTextLocalesWithMinikin();
            return;
        }
    }

    public void setTextLocales(LocaleList localelist)
    {
        if(localelist == null || localelist.isEmpty())
            throw new IllegalArgumentException("locales cannot be null or empty");
        if(localelist.equals(mLocales))
        {
            return;
        } else
        {
            mLocales = localelist;
            syncTextLocalesWithMinikin();
            return;
        }
    }

    public void setTextScaleX(float f)
    {
        nSetTextScaleX(mNativePaint, f);
    }

    public void setTextSize(float f)
    {
        nSetTextSize(mNativePaint, f);
    }

    public void setTextSkewX(float f)
    {
        nSetTextSkewX(mNativePaint, f);
    }

    public Typeface setTypeface(Typeface typeface)
    {
        long l = 0L;
        if(typeface != null)
            l = typeface.native_instance;
        nSetTypeface(mNativePaint, l);
        mTypeface = typeface;
        mNativeTypeface = l;
        return typeface;
    }

    public void setUnderlineText(boolean flag)
    {
        nSetUnderlineText(mNativePaint, flag);
    }

    public void setWordSpacing(float f)
    {
        nSetWordSpacing(mNativePaint, f);
    }

    public Xfermode setXfermode(Xfermode xfermode)
    {
        int i;
        int j;
        if(xfermode != null)
            i = xfermode.porterDuffMode;
        else
            i = Xfermode.DEFAULT;
        if(mXfermode != null)
            j = mXfermode.porterDuffMode;
        else
            j = Xfermode.DEFAULT;
        if(i != j)
            nSetXfermode(mNativePaint, i);
        mXfermode = xfermode;
        return xfermode;
    }

    public static final int ANTI_ALIAS_FLAG = 1;
    public static final int AUTO_HINTING_TEXT_FLAG = 2048;
    public static final int BIDI_DEFAULT_LTR = 2;
    public static final int BIDI_DEFAULT_RTL = 3;
    private static final int BIDI_FLAG_MASK = 7;
    public static final int BIDI_FORCE_LTR = 4;
    public static final int BIDI_FORCE_RTL = 5;
    public static final int BIDI_LTR = 0;
    private static final int BIDI_MAX_FLAG_VALUE = 5;
    public static final int BIDI_RTL = 1;
    public static final int CURSOR_AFTER = 0;
    public static final int CURSOR_AT = 4;
    public static final int CURSOR_AT_OR_AFTER = 1;
    public static final int CURSOR_AT_OR_BEFORE = 3;
    public static final int CURSOR_BEFORE = 2;
    private static final int CURSOR_OPT_MAX_VALUE = 4;
    public static final int DEV_KERN_TEXT_FLAG = 256;
    public static final int DIRECTION_LTR = 0;
    public static final int DIRECTION_RTL = 1;
    public static final int DITHER_FLAG = 4;
    public static final int EMBEDDED_BITMAP_TEXT_FLAG = 1024;
    public static final int FAKE_BOLD_TEXT_FLAG = 32;
    public static final int FILTER_BITMAP_FLAG = 2;
    static final int HIDDEN_DEFAULT_PAINT_FLAGS = 1280;
    public static final int HINTING_OFF = 0;
    public static final int HINTING_ON = 1;
    public static final int HYPHENEDIT_MASK_END_OF_LINE = 7;
    public static final int HYPHENEDIT_MASK_START_OF_LINE = 24;
    public static final int LCD_RENDER_TEXT_FLAG = 512;
    public static final int LINEAR_TEXT_FLAG = 64;
    private static final long NATIVE_PAINT_SIZE = 98L;
    public static final int STRIKE_THRU_TEXT_FLAG = 16;
    public static final int SUBPIXEL_TEXT_FLAG = 128;
    public static final int UNDERLINE_TEXT_FLAG = 8;
    public static final int VERTICAL_TEXT_FLAG = 4096;
    static final Align sAlignArray[];
    private static final Object sCacheLock = new Object();
    static final Cap sCapArray[];
    static final Join sJoinArray[];
    private static final HashMap sMinikinLangListIdCache = new HashMap();
    static final Style sStyleArray[];
    public int mBidiFlags;
    private ColorFilter mColorFilter;
    private float mCompatScaling;
    private String mFontFeatureSettings;
    private String mFontVariationSettings;
    private boolean mHasCompatScaling;
    private float mInvCompatScaling;
    private LocaleList mLocales;
    private MaskFilter mMaskFilter;
    private long mNativeColorFilter;
    private long mNativePaint;
    private long mNativeShader;
    public long mNativeTypeface;
    private PathEffect mPathEffect;
    private Shader mShader;
    private int mShadowLayerColor;
    private float mShadowLayerDx;
    private float mShadowLayerDy;
    private float mShadowLayerRadius;
    private Typeface mTypeface;
    private Xfermode mXfermode;

    static 
    {
        sStyleArray = (new Style[] {
            Style.FILL, Style.STROKE, Style.FILL_AND_STROKE
        });
        sCapArray = (new Cap[] {
            Cap.BUTT, Cap.ROUND, Cap.SQUARE
        });
        sJoinArray = (new Join[] {
            Join.MITER, Join.ROUND, Join.BEVEL
        });
        sAlignArray = (new Align[] {
            Align.LEFT, Align.CENTER, Align.RIGHT
        });
    }
}
