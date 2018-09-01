// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.graphics.palette;

import com.android.internal.graphics.ColorUtils;
import com.android.internal.ml.clustering.KMeans;
import java.util.*;

// Referenced classes of package com.android.internal.graphics.palette:
//            Quantizer

public class VariationalKMeansQuantizer
    implements Quantizer
{

    public VariationalKMeansQuantizer()
    {
        this(0.25F);
    }

    public VariationalKMeansQuantizer(float f)
    {
        this(f, 1);
    }

    public VariationalKMeansQuantizer(float f, int i)
    {
        mKMeans = new KMeans(new Random(0L), 30, 0.0F);
        mMinClusterSqDistance = f * f;
        mInitializations = i;
    }

    private List getOptimalKMeans(int i, float af[][])
    {
        List list = null;
        double d = -1.7976931348623157E+308D;
        for(int j = mInitializations; j > 0;)
        {
            double d2;
label0:
            {
                List list1 = mKMeans.predict(i, af);
                double d1 = KMeans.score(list1);
                if(list != null)
                {
                    d2 = d;
                    if(d1 <= d)
                        break label0;
                }
                d2 = d1;
                list = list1;
            }
            j--;
            d = d2;
        }

        return list;
    }

    public List getQuantizedColors()
    {
        return mQuantizedColors;
    }

    public void quantize(int ai[], int i, Palette.Filter afilter[])
    {
        float af[] = new float[3];
        float[] _tmp = af;
        af[0] = 0.0F;
        af[1] = 0.0F;
        af[2] = 0.0F;
        afilter = new float[ai.length][3];
        for(int j = 0; j < ai.length; j++)
        {
            ColorUtils.colorToHSL(ai[j], af);
            afilter[j][0] = af[0] / 360F;
            afilter[j][1] = af[1];
            afilter[j][2] = af[2];
        }

        afilter = getOptimalKMeans(i, afilter);
        for(int k = 0; k < afilter.size(); k++)
        {
            com.android.internal.ml.clustering.KMeans.Mean mean = (com.android.internal.ml.clustering.KMeans.Mean)afilter.get(k);
            float af2[] = mean.getCentroid();
            int l;
            for(i = k + 1; i < afilter.size(); i = l + 1)
            {
                ai = (com.android.internal.ml.clustering.KMeans.Mean)afilter.get(i);
                float af3[] = ai.getCentroid();
                l = i;
                if(KMeans.sqDistance(af2, af3) >= mMinClusterSqDistance)
                    continue;
                afilter.remove(ai);
                mean.getItems().addAll(ai.getItems());
                for(l = 0; l < af2.length; l++)
                    af2[l] = (float)((double)af2[l] + (double)(af3[l] - af2[l]) / 2D);

                l = i - 1;
            }

        }

        mQuantizedColors = new ArrayList();
        ai = afilter.iterator();
        do
        {
            if(!ai.hasNext())
                break;
            afilter = (com.android.internal.ml.clustering.KMeans.Mean)ai.next();
            if(afilter.getItems().size() != 0)
            {
                float af1[] = afilter.getCentroid();
                List list = mQuantizedColors;
                float f = af1[0];
                float f1 = af1[1];
                float f2 = af1[2];
                i = afilter.getItems().size();
                list.add(new Palette.Swatch(new float[] {
                    f * 360F, f1, f2
                }, i));
            }
        } while(true);
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "KMeansQuantizer";
    private final int mInitializations;
    private final KMeans mKMeans;
    private final float mMinClusterSqDistance;
    private List mQuantizedColors;
}
