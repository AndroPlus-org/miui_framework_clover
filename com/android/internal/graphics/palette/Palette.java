// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.graphics.palette;

import android.graphics.*;
import android.os.AsyncTask;
import android.util.*;
import com.android.internal.graphics.ColorUtils;
import java.util.*;

// Referenced classes of package com.android.internal.graphics.palette:
//            Target, ColorCutQuantizer, Quantizer

public final class Palette
{
    public static final class Builder
    {

        private int[] getPixelsFromBitmap(Bitmap bitmap)
        {
            int i = bitmap.getWidth();
            int j = bitmap.getHeight();
            int ai[] = new int[i * j];
            bitmap.getPixels(ai, 0, i, 0, 0, i, j);
            if(mRegion == null)
                return ai;
            int l = mRegion.width();
            int i1 = mRegion.height();
            bitmap = new int[l * i1];
            for(int k = 0; k < i1; k++)
                System.arraycopy(ai, (mRegion.top + k) * i + mRegion.left, bitmap, k * l, l);

            return bitmap;
        }

        private Bitmap scaleBitmapDown(Bitmap bitmap)
        {
            double d = -1D;
            double d1;
            if(mResizeArea > 0)
            {
                int i = bitmap.getWidth() * bitmap.getHeight();
                d1 = d;
                if(i > mResizeArea)
                    d1 = Math.sqrt((double)mResizeArea / (double)i);
            } else
            {
                d1 = d;
                if(mResizeMaxDimension > 0)
                {
                    int j = Math.max(bitmap.getWidth(), bitmap.getHeight());
                    d1 = d;
                    if(j > mResizeMaxDimension)
                        d1 = (double)mResizeMaxDimension / (double)j;
                }
            }
            if(d1 <= 0.0D)
                return bitmap;
            else
                return Bitmap.createScaledBitmap(bitmap, (int)Math.ceil((double)bitmap.getWidth() * d1), (int)Math.ceil((double)bitmap.getHeight() * d1), false);
        }

        public Builder addFilter(Filter filter)
        {
            if(filter != null)
                mFilters.add(filter);
            return this;
        }

        public Builder addTarget(Target target)
        {
            if(!mTargets.contains(target))
                mTargets.add(target);
            return this;
        }

        public Builder clearFilters()
        {
            mFilters.clear();
            return this;
        }

        public Builder clearRegion()
        {
            mRegion = null;
            return this;
        }

        public Builder clearTargets()
        {
            if(mTargets != null)
                mTargets.clear();
            return this;
        }

        public AsyncTask generate(PaletteAsyncListener paletteasynclistener)
        {
            if(paletteasynclistener == null)
                throw new IllegalArgumentException("listener can not be null");
            else
                return (paletteasynclistener. new AsyncTask() {

                    protected transient Palette doInBackground(Bitmap abitmap[])
                    {
                        try
                        {
                            abitmap = generate();
                        }
                        // Misplaced declaration of an exception variable
                        catch(Bitmap abitmap[])
                        {
                            Log.e("Palette", "Exception thrown during async generate", abitmap);
                            return null;
                        }
                        return abitmap;
                    }

                    protected volatile Object doInBackground(Object aobj[])
                    {
                        return doInBackground((Bitmap[])aobj);
                    }

                    protected void onPostExecute(Palette palette)
                    {
                        listener.onGenerated(palette);
                    }

                    protected volatile void onPostExecute(Object obj)
                    {
                        onPostExecute((Palette)obj);
                    }

                    final Builder this$1;
                    final PaletteAsyncListener val$listener;

            
            {
                this$1 = final_builder;
                listener = PaletteAsyncListener.this;
                super();
            }
                }
).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Bitmap[] {
                    mBitmap
                });
        }

        public Palette generate()
        {
            Object obj = null;
            if(mBitmap != null)
            {
                Bitmap bitmap = scaleBitmapDown(mBitmap);
                Object obj1 = mRegion;
                if(bitmap != mBitmap && obj1 != null)
                {
                    double d = (double)bitmap.getWidth() / (double)mBitmap.getWidth();
                    obj1.left = (int)Math.floor((double)((Rect) (obj1)).left * d);
                    obj1.top = (int)Math.floor((double)((Rect) (obj1)).top * d);
                    obj1.right = Math.min((int)Math.ceil((double)((Rect) (obj1)).right * d), bitmap.getWidth());
                    obj1.bottom = Math.min((int)Math.ceil((double)((Rect) (obj1)).bottom * d), bitmap.getHeight());
                }
                if(mQuantizer == null)
                    mQuantizer = new ColorCutQuantizer();
                obj1 = mQuantizer;
                int ai[] = getPixelsFromBitmap(bitmap);
                int i = mMaxColors;
                if(!mFilters.isEmpty())
                    obj = (Filter[])mFilters.toArray(new Filter[mFilters.size()]);
                ((Quantizer) (obj1)).quantize(ai, i, ((Filter []) (obj)));
                if(bitmap != mBitmap)
                    bitmap.recycle();
                obj = mQuantizer.getQuantizedColors();
            } else
            {
                obj = mSwatches;
            }
            obj = new Palette(((List) (obj)), mTargets);
            ((Palette) (obj)).generate();
            return ((Palette) (obj));
        }

        public Builder maximumColorCount(int i)
        {
            mMaxColors = i;
            return this;
        }

        public Builder resizeBitmapArea(int i)
        {
            mResizeArea = i;
            mResizeMaxDimension = -1;
            return this;
        }

        public Builder resizeBitmapSize(int i)
        {
            mResizeMaxDimension = i;
            mResizeArea = -1;
            return this;
        }

        public Builder setQuantizer(Quantizer quantizer)
        {
            mQuantizer = quantizer;
            return this;
        }

        public Builder setRegion(int i, int j, int k, int l)
        {
            if(mBitmap != null)
            {
                if(mRegion == null)
                    mRegion = new Rect();
                mRegion.set(0, 0, mBitmap.getWidth(), mBitmap.getHeight());
                if(!mRegion.intersect(i, j, k, l))
                    throw new IllegalArgumentException("The given region must intersect with the Bitmap's dimensions.");
            }
            return this;
        }

        private final Bitmap mBitmap;
        private final List mFilters;
        private int mMaxColors;
        private Quantizer mQuantizer;
        private Rect mRegion;
        private int mResizeArea;
        private int mResizeMaxDimension;
        private final List mSwatches;
        private final List mTargets;

        public Builder(Bitmap bitmap)
        {
            mTargets = new ArrayList();
            mMaxColors = 16;
            mResizeArea = 12544;
            mResizeMaxDimension = -1;
            mFilters = new ArrayList();
            if(bitmap == null || bitmap.isRecycled())
            {
                throw new IllegalArgumentException("Bitmap is not valid");
            } else
            {
                mFilters.add(Palette.DEFAULT_FILTER);
                mBitmap = bitmap;
                mSwatches = null;
                mTargets.add(Target.LIGHT_VIBRANT);
                mTargets.add(Target.VIBRANT);
                mTargets.add(Target.DARK_VIBRANT);
                mTargets.add(Target.LIGHT_MUTED);
                mTargets.add(Target.MUTED);
                mTargets.add(Target.DARK_MUTED);
                return;
            }
        }

        public Builder(List list)
        {
            mTargets = new ArrayList();
            mMaxColors = 16;
            mResizeArea = 12544;
            mResizeMaxDimension = -1;
            mFilters = new ArrayList();
            if(list == null || list.isEmpty())
            {
                throw new IllegalArgumentException("List of Swatches is not valid");
            } else
            {
                mFilters.add(Palette.DEFAULT_FILTER);
                mSwatches = list;
                mBitmap = null;
                return;
            }
        }
    }

    public static interface Filter
    {

        public abstract boolean isAllowed(int i, float af[]);
    }

    public static interface PaletteAsyncListener
    {

        public abstract void onGenerated(Palette palette);
    }

    public static final class Swatch
    {

        private void ensureTextColorsGenerated()
        {
            if(!mGeneratedTextColors)
            {
                int i = ColorUtils.calculateMinimumAlpha(-1, mRgb, 4.5F);
                int j = ColorUtils.calculateMinimumAlpha(-1, mRgb, 3F);
                if(i != -1 && j != -1)
                {
                    mBodyTextColor = ColorUtils.setAlphaComponent(-1, i);
                    mTitleTextColor = ColorUtils.setAlphaComponent(-1, j);
                    mGeneratedTextColors = true;
                    return;
                }
                int k = ColorUtils.calculateMinimumAlpha(0xff000000, mRgb, 4.5F);
                int l = ColorUtils.calculateMinimumAlpha(0xff000000, mRgb, 3F);
                if(k != -1 && l != -1)
                {
                    mBodyTextColor = ColorUtils.setAlphaComponent(0xff000000, k);
                    mTitleTextColor = ColorUtils.setAlphaComponent(0xff000000, l);
                    mGeneratedTextColors = true;
                    return;
                }
                if(i != -1)
                    k = ColorUtils.setAlphaComponent(-1, i);
                else
                    k = ColorUtils.setAlphaComponent(0xff000000, k);
                mBodyTextColor = k;
                if(j != -1)
                    k = ColorUtils.setAlphaComponent(-1, j);
                else
                    k = ColorUtils.setAlphaComponent(0xff000000, l);
                mTitleTextColor = k;
                mGeneratedTextColors = true;
            }
        }

        public boolean equals(Object obj)
        {
            boolean flag = true;
            if(this == obj)
                return true;
            if(obj == null || getClass() != obj.getClass())
                return false;
            obj = (Swatch)obj;
            if(mPopulation != ((Swatch) (obj)).mPopulation || mRgb != ((Swatch) (obj)).mRgb)
                flag = false;
            return flag;
        }

        public int getBodyTextColor()
        {
            ensureTextColorsGenerated();
            return mBodyTextColor;
        }

        public float[] getHsl()
        {
            if(mHsl == null)
                mHsl = new float[3];
            ColorUtils.RGBToHSL(mRed, mGreen, mBlue, mHsl);
            return mHsl;
        }

        public int getPopulation()
        {
            return mPopulation;
        }

        public int getRgb()
        {
            return mRgb;
        }

        public int getTitleTextColor()
        {
            ensureTextColorsGenerated();
            return mTitleTextColor;
        }

        public int hashCode()
        {
            return mRgb * 31 + mPopulation;
        }

        public String toString()
        {
            return (new StringBuilder(getClass().getSimpleName())).append(" [RGB: #").append(Integer.toHexString(getRgb())).append(']').append(" [HSL: ").append(Arrays.toString(getHsl())).append(']').append(" [Population: ").append(mPopulation).append(']').append(" [Title Text: #").append(Integer.toHexString(getTitleTextColor())).append(']').append(" [Body Text: #").append(Integer.toHexString(getBodyTextColor())).append(']').toString();
        }

        private final int mBlue;
        private int mBodyTextColor;
        private boolean mGeneratedTextColors;
        private final int mGreen;
        private float mHsl[];
        private final int mPopulation;
        private final int mRed;
        private final int mRgb;
        private int mTitleTextColor;

        public Swatch(int i, int j)
        {
            mRed = Color.red(i);
            mGreen = Color.green(i);
            mBlue = Color.blue(i);
            mRgb = i;
            mPopulation = j;
        }

        Swatch(int i, int j, int k, int l)
        {
            mRed = i;
            mGreen = j;
            mBlue = k;
            mRgb = Color.rgb(i, j, k);
            mPopulation = l;
        }

        Swatch(float af[], int i)
        {
            this(ColorUtils.HSLToColor(af), i);
            mHsl = af;
        }
    }


    Palette(List list, List list1)
    {
        mSwatches = list;
        mTargets = list1;
    }

    private static float[] copyHslValues(Swatch swatch)
    {
        float af[] = new float[3];
        System.arraycopy(swatch.getHsl(), 0, af, 0, 3);
        return af;
    }

    private Swatch findDominantSwatch()
    {
        int i = 0x80000000;
        Swatch swatch = null;
        int j = 0;
        for(int k = mSwatches.size(); j < k;)
        {
            Swatch swatch1 = (Swatch)mSwatches.get(j);
            int l = i;
            if(swatch1.getPopulation() > i)
            {
                swatch = swatch1;
                l = swatch1.getPopulation();
            }
            j++;
            i = l;
        }

        return swatch;
    }

    public static Builder from(Bitmap bitmap)
    {
        return new Builder(bitmap);
    }

    public static Palette from(List list)
    {
        return (new Builder(list)).generate();
    }

    public static Palette generate(Bitmap bitmap)
    {
        return from(bitmap).generate();
    }

    public static Palette generate(Bitmap bitmap, int i)
    {
        return from(bitmap).maximumColorCount(i).generate();
    }

    public static AsyncTask generateAsync(Bitmap bitmap, int i, PaletteAsyncListener paletteasynclistener)
    {
        return from(bitmap).maximumColorCount(i).generate(paletteasynclistener);
    }

    public static AsyncTask generateAsync(Bitmap bitmap, PaletteAsyncListener paletteasynclistener)
    {
        return from(bitmap).generate(paletteasynclistener);
    }

    private float generateScore(Swatch swatch, Target target)
    {
        float af[] = swatch.getHsl();
        float f = 0.0F;
        float f1 = 0.0F;
        float f2 = 0.0F;
        int i;
        if(mDominantSwatch != null)
            i = mDominantSwatch.getPopulation();
        else
            i = 1;
        if(target.getSaturationWeight() > 0.0F)
            f = target.getSaturationWeight() * (1.0F - Math.abs(af[1] - target.getTargetSaturation()));
        if(target.getLightnessWeight() > 0.0F)
            f1 = target.getLightnessWeight() * (1.0F - Math.abs(af[2] - target.getTargetLightness()));
        if(target.getPopulationWeight() > 0.0F)
            f2 = target.getPopulationWeight() * ((float)swatch.getPopulation() / (float)i);
        return f + f1 + f2;
    }

    private Swatch generateScoredTarget(Target target)
    {
        Swatch swatch = getMaxScoredSwatchForTarget(target);
        if(swatch != null && target.isExclusive())
            mUsedColors.append(swatch.getRgb(), true);
        return swatch;
    }

    private Swatch getMaxScoredSwatchForTarget(Target target)
    {
        float f = 0.0F;
        Swatch swatch = null;
        int i = 0;
        for(int j = mSwatches.size(); i < j;)
        {
            float f1;
            Swatch swatch2;
label0:
            {
                Swatch swatch1 = (Swatch)mSwatches.get(i);
                f1 = f;
                swatch2 = swatch;
                if(!shouldBeScoredForTarget(swatch1, target))
                    break label0;
                float f2 = generateScore(swatch1, target);
                if(swatch != null)
                {
                    f1 = f;
                    swatch2 = swatch;
                    if(f2 <= f)
                        break label0;
                }
                swatch2 = swatch1;
                f1 = f2;
            }
            i++;
            f = f1;
            swatch = swatch2;
        }

        return swatch;
    }

    private boolean shouldBeScoredForTarget(Swatch swatch, Target target)
    {
        float af[] = swatch.getHsl();
        boolean flag;
        if(af[1] >= target.getMinimumSaturation() && af[1] <= target.getMaximumSaturation() && af[2] >= target.getMinimumLightness() && af[2] <= target.getMaximumLightness())
            flag = mUsedColors.get(swatch.getRgb()) ^ true;
        else
            flag = false;
        return flag;
    }

    void generate()
    {
        int i = 0;
        for(int j = mTargets.size(); i < j; i++)
        {
            Target target = (Target)mTargets.get(i);
            target.normalizeWeights();
            mSelectedSwatches.put(target, generateScoredTarget(target));
        }

        mUsedColors.clear();
    }

    public int getColorForTarget(Target target, int i)
    {
        target = getSwatchForTarget(target);
        if(target != null)
            i = target.getRgb();
        return i;
    }

    public int getDarkMutedColor(int i)
    {
        return getColorForTarget(Target.DARK_MUTED, i);
    }

    public Swatch getDarkMutedSwatch()
    {
        return getSwatchForTarget(Target.DARK_MUTED);
    }

    public int getDarkVibrantColor(int i)
    {
        return getColorForTarget(Target.DARK_VIBRANT, i);
    }

    public Swatch getDarkVibrantSwatch()
    {
        return getSwatchForTarget(Target.DARK_VIBRANT);
    }

    public int getDominantColor(int i)
    {
        if(mDominantSwatch != null)
            i = mDominantSwatch.getRgb();
        return i;
    }

    public Swatch getDominantSwatch()
    {
        return mDominantSwatch;
    }

    public int getLightMutedColor(int i)
    {
        return getColorForTarget(Target.LIGHT_MUTED, i);
    }

    public Swatch getLightMutedSwatch()
    {
        return getSwatchForTarget(Target.LIGHT_MUTED);
    }

    public int getLightVibrantColor(int i)
    {
        return getColorForTarget(Target.LIGHT_VIBRANT, i);
    }

    public Swatch getLightVibrantSwatch()
    {
        return getSwatchForTarget(Target.LIGHT_VIBRANT);
    }

    public int getMutedColor(int i)
    {
        return getColorForTarget(Target.MUTED, i);
    }

    public Swatch getMutedSwatch()
    {
        return getSwatchForTarget(Target.MUTED);
    }

    public Swatch getSwatchForTarget(Target target)
    {
        return (Swatch)mSelectedSwatches.get(target);
    }

    public List getSwatches()
    {
        return Collections.unmodifiableList(mSwatches);
    }

    public List getTargets()
    {
        return Collections.unmodifiableList(mTargets);
    }

    public int getVibrantColor(int i)
    {
        return getColorForTarget(Target.VIBRANT, i);
    }

    public Swatch getVibrantSwatch()
    {
        return getSwatchForTarget(Target.VIBRANT);
    }

    static final int DEFAULT_CALCULATE_NUMBER_COLORS = 16;
    static final Filter DEFAULT_FILTER = new Filter() {

        private boolean isBlack(float af[])
        {
            boolean flag;
            if(af[2] <= 0.05F)
                flag = true;
            else
                flag = false;
            return flag;
        }

        private boolean isNearRedILine(float af[])
        {
            boolean flag = true;
            if(af[0] < 10F || af[0] > 37F || af[1] > 0.82F)
                flag = false;
            return flag;
        }

        private boolean isWhite(float af[])
        {
            boolean flag;
            if(af[2] >= 0.95F)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean isAllowed(int i, float af[])
        {
            boolean flag;
            if(!isWhite(af) && isBlack(af) ^ true)
                flag = isNearRedILine(af) ^ true;
            else
                flag = false;
            return flag;
        }

        private static final float BLACK_MAX_LIGHTNESS = 0.05F;
        private static final float WHITE_MIN_LIGHTNESS = 0.95F;

    }
;
    static final int DEFAULT_RESIZE_BITMAP_AREA = 12544;
    static final String LOG_TAG = "Palette";
    static final boolean LOG_TIMINGS = false;
    static final float MIN_CONTRAST_BODY_TEXT = 4.5F;
    static final float MIN_CONTRAST_TITLE_TEXT = 3F;
    private final Swatch mDominantSwatch = findDominantSwatch();
    private final Map mSelectedSwatches = new ArrayMap();
    private final List mSwatches;
    private final List mTargets;
    private final SparseBooleanArray mUsedColors = new SparseBooleanArray();

}
