// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.graphics.palette;

import android.graphics.Color;
import android.util.TimingLogger;
import com.android.internal.graphics.ColorUtils;
import java.util.*;

// Referenced classes of package com.android.internal.graphics.palette:
//            Quantizer

final class ColorCutQuantizer
    implements Quantizer
{
    private class Vbox
    {

        final boolean canSplit()
        {
            boolean flag = true;
            if(getColorCount() <= 1)
                flag = false;
            return flag;
        }

        final int findSplitPoint()
        {
            int i = getLongestColorDimension();
            int ai[] = mColors;
            int ai1[] = mHistogram;
            ColorCutQuantizer.modifySignificantOctet(ai, i, mLowerIndex, mUpperIndex);
            Arrays.sort(ai, mLowerIndex, mUpperIndex + 1);
            ColorCutQuantizer.modifySignificantOctet(ai, i, mLowerIndex, mUpperIndex);
            int j = mPopulation / 2;
            i = mLowerIndex;
            int k = 0;
            for(; i <= mUpperIndex; i++)
            {
                k += ai1[ai[i]];
                if(k >= j)
                    return Math.min(mUpperIndex - 1, i);
            }

            return mLowerIndex;
        }

        final void fitBox()
        {
            int ai[] = mColors;
            int ai1[] = mHistogram;
            int i = 0x7fffffff;
            int j = 0x7fffffff;
            int k = 0x7fffffff;
            int l = 0x80000000;
            int i1 = 0x80000000;
            int j1 = 0x80000000;
            int k1 = 0;
            for(int l1 = mLowerIndex; l1 <= mUpperIndex;)
            {
                int i2 = ai[l1];
                int j2 = k1 + ai1[i2];
                int k2 = ColorCutQuantizer.quantizedRed(i2);
                int l2 = ColorCutQuantizer.quantizedGreen(i2);
                k1 = ColorCutQuantizer.quantizedBlue(i2);
                i2 = j1;
                if(k2 > j1)
                    i2 = k2;
                int i3 = k;
                if(k2 < k)
                    i3 = k2;
                j1 = i1;
                if(l2 > i1)
                    j1 = l2;
                k = j;
                if(l2 < j)
                    k = l2;
                i1 = l;
                if(k1 > l)
                    i1 = k1;
                j = i;
                if(k1 < i)
                    j = k1;
                l1++;
                k1 = j2;
                l = i1;
                i1 = j1;
                j1 = i2;
                i = j;
                j = k;
                k = i3;
            }

            mMinRed = k;
            mMaxRed = j1;
            mMinGreen = j;
            mMaxGreen = i1;
            mMinBlue = i;
            mMaxBlue = l;
            mPopulation = k1;
        }

        final Palette.Swatch getAverageColor()
        {
            int ai[] = mColors;
            int ai1[] = mHistogram;
            int i = 0;
            int j = 0;
            int k = 0;
            int l = 0;
            for(int i1 = mLowerIndex; i1 <= mUpperIndex; i1++)
            {
                int j1 = ai[i1];
                int k1 = ai1[j1];
                l += k1;
                i += ColorCutQuantizer.quantizedRed(j1) * k1;
                j += ColorCutQuantizer.quantizedGreen(j1) * k1;
                k += ColorCutQuantizer.quantizedBlue(j1) * k1;
            }

            return new Palette.Swatch(ColorCutQuantizer.approximateToRgb888(Math.round((float)i / (float)l), Math.round((float)j / (float)l), Math.round((float)k / (float)l)), l);
        }

        final int getColorCount()
        {
            return (mUpperIndex + 1) - mLowerIndex;
        }

        final int getLongestColorDimension()
        {
            int i = mMaxRed - mMinRed;
            int j = mMaxGreen - mMinGreen;
            int k = mMaxBlue - mMinBlue;
            if(i >= j && i >= k)
                return -3;
            return j < i || j < k ? -1 : -2;
        }

        final int getVolume()
        {
            return ((mMaxRed - mMinRed) + 1) * ((mMaxGreen - mMinGreen) + 1) * ((mMaxBlue - mMinBlue) + 1);
        }

        final Vbox splitBox()
        {
            if(!canSplit())
            {
                throw new IllegalStateException("Can not split a box with only 1 color");
            } else
            {
                int i = findSplitPoint();
                Vbox vbox = new Vbox(i + 1, mUpperIndex);
                mUpperIndex = i;
                fitBox();
                return vbox;
            }
        }

        private int mLowerIndex;
        private int mMaxBlue;
        private int mMaxGreen;
        private int mMaxRed;
        private int mMinBlue;
        private int mMinGreen;
        private int mMinRed;
        private int mPopulation;
        private int mUpperIndex;
        final ColorCutQuantizer this$0;

        Vbox(int i, int j)
        {
            this$0 = ColorCutQuantizer.this;
            super();
            mLowerIndex = i;
            mUpperIndex = j;
            fitBox();
        }
    }


    ColorCutQuantizer()
    {
    }

    private static int approximateToRgb888(int i)
    {
        return approximateToRgb888(quantizedRed(i), quantizedGreen(i), quantizedBlue(i));
    }

    static int approximateToRgb888(int i, int j, int k)
    {
        return Color.rgb(modifyWordWidth(i, 5, 8), modifyWordWidth(j, 5, 8), modifyWordWidth(k, 5, 8));
    }

    private List generateAverageColors(Collection collection)
    {
        ArrayList arraylist = new ArrayList(collection.size());
        Iterator iterator = collection.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            collection = ((Vbox)iterator.next()).getAverageColor();
            if(!shouldIgnoreColor(collection))
                arraylist.add(collection);
        } while(true);
        return arraylist;
    }

    static void modifySignificantOctet(int ai[], int i, int j, int k)
    {
        i;
        JVM INSTR tableswitch -3 -1: default 28
    //                   -3 28
    //                   -2 29
    //                   -1 68;
           goto _L1 _L1 _L2 _L3
_L1:
        return;
_L2:
        i = j;
        while(i <= k) 
        {
            j = ai[i];
            ai[i] = quantizedGreen(j) << 10 | quantizedRed(j) << 5 | quantizedBlue(j);
            i++;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        i = j;
        while(i <= k) 
        {
            j = ai[i];
            ai[i] = quantizedBlue(j) << 10 | quantizedGreen(j) << 5 | quantizedRed(j);
            i++;
        }
        if(true) goto _L1; else goto _L4
_L4:
    }

    private static int modifyWordWidth(int i, int j, int k)
    {
        if(k > j)
            i <<= k - j;
        else
            i >>= j - k;
        return (1 << k) - 1 & i;
    }

    private static int quantizeFromRgb888(int i)
    {
        return modifyWordWidth(Color.red(i), 8, 5) << 10 | modifyWordWidth(Color.green(i), 8, 5) << 5 | modifyWordWidth(Color.blue(i), 8, 5);
    }

    private List quantizePixels(int i)
    {
        PriorityQueue priorityqueue = new PriorityQueue(i, VBOX_COMPARATOR_VOLUME);
        priorityqueue.offer(new Vbox(0, mColors.length - 1));
        splitBoxes(priorityqueue, i);
        return generateAverageColors(priorityqueue);
    }

    static int quantizedBlue(int i)
    {
        return i & 0x1f;
    }

    static int quantizedGreen(int i)
    {
        return i >> 5 & 0x1f;
    }

    static int quantizedRed(int i)
    {
        return i >> 10 & 0x1f;
    }

    private boolean shouldIgnoreColor(int i)
    {
        i = approximateToRgb888(i);
        ColorUtils.colorToHSL(i, mTempHsl);
        return shouldIgnoreColor(i, mTempHsl);
    }

    private boolean shouldIgnoreColor(int i, float af[])
    {
        if(mFilters != null && mFilters.length > 0)
        {
            int j = 0;
            for(int k = mFilters.length; j < k; j++)
                if(!mFilters[j].isAllowed(i, af))
                    return true;

        }
        return false;
    }

    private boolean shouldIgnoreColor(Palette.Swatch swatch)
    {
        return shouldIgnoreColor(swatch.getRgb(), swatch.getHsl());
    }

    private void splitBoxes(PriorityQueue priorityqueue, int i)
    {
        while(priorityqueue.size() < i) 
        {
            Vbox vbox = (Vbox)priorityqueue.poll();
            if(vbox != null && vbox.canSplit())
            {
                priorityqueue.offer(vbox.splitBox());
                priorityqueue.offer(vbox);
            } else
            {
                return;
            }
        }
    }

    public List getQuantizedColors()
    {
        return mQuantizedColors;
    }

    public void quantize(int ai[], int i, Palette.Filter afilter[])
    {
        mTimingLogger = null;
        mFilters = afilter;
        afilter = new int[32768];
        mHistogram = afilter;
        for(int j = 0; j < ai.length; j++)
        {
            int i1 = quantizeFromRgb888(ai[j]);
            ai[j] = i1;
            afilter[i1] = afilter[i1] + 1;
        }

        int k = 0;
        for(int j1 = 0; j1 < afilter.length;)
        {
            if(afilter[j1] > 0 && shouldIgnoreColor(j1))
                afilter[j1] = 0;
            int i2 = k;
            if(afilter[j1] > 0)
                i2 = k + 1;
            j1++;
            k = i2;
        }

        ai = new int[k];
        mColors = ai;
        int j2 = 0;
        for(int k1 = 0; k1 < afilter.length;)
        {
            int k2 = j2;
            if(afilter[k1] > 0)
            {
                ai[j2] = k1;
                k2 = j2 + 1;
            }
            k1++;
            j2 = k2;
        }

        if(k <= i)
        {
            mQuantizedColors = new ArrayList();
            i = 0;
            for(int l = ai.length; i < l; i++)
            {
                int l1 = ai[i];
                mQuantizedColors.add(new Palette.Swatch(approximateToRgb888(l1), afilter[l1]));
            }

        } else
        {
            mQuantizedColors = quantizePixels(i);
        }
    }

    static final int COMPONENT_BLUE = -1;
    static final int COMPONENT_GREEN = -2;
    static final int COMPONENT_RED = -3;
    private static final String LOG_TAG = "ColorCutQuantizer";
    private static final boolean LOG_TIMINGS = false;
    private static final int QUANTIZE_WORD_MASK = 31;
    private static final int QUANTIZE_WORD_WIDTH = 5;
    private static final Comparator VBOX_COMPARATOR_VOLUME = new Comparator() {

        public int compare(Vbox vbox, Vbox vbox1)
        {
            return vbox1.getVolume() - vbox.getVolume();
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((Vbox)obj, (Vbox)obj1);
        }

    }
;
    int mColors[];
    Palette.Filter mFilters[];
    int mHistogram[];
    List mQuantizedColors;
    private final float mTempHsl[] = new float[3];
    TimingLogger mTimingLogger;

}
