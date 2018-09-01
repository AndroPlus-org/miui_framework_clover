// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.ml.clustering;

import java.util.*;

public class KMeans
{
    public static class Mean
    {

        public float[] getCentroid()
        {
            return mCentroid;
        }

        public List getItems()
        {
            return mClosestItems;
        }

        public String toString()
        {
            return (new StringBuilder()).append("Mean(centroid: ").append(Arrays.toString(mCentroid)).append(", size: ").append(mClosestItems.size()).append(")").toString();
        }

        float mCentroid[];
        final ArrayList mClosestItems;

        public Mean(int i)
        {
            mClosestItems = new ArrayList();
            mCentroid = new float[i];
        }

        public transient Mean(float af[])
        {
            mClosestItems = new ArrayList();
            mCentroid = af;
        }
    }


    public KMeans()
    {
        this(new Random());
    }

    public KMeans(Random random)
    {
        this(random, 30, 0.005F);
    }

    public KMeans(Random random, int i, float f)
    {
        mRandomState = random;
        mMaxIterations = i;
        mSqConvergenceEpsilon = f * f;
    }

    public static Mean nearestMean(float af[], List list)
    {
        Mean mean = null;
        float f = 3.402823E+038F;
        int i = list.size();
        for(int j = 0; j < i;)
        {
            Mean mean1 = (Mean)list.get(j);
            float f1 = sqDistance(af, mean1.mCentroid);
            float f2 = f;
            if(f1 < f)
            {
                mean = mean1;
                f2 = f1;
            }
            j++;
            f = f2;
        }

        return mean;
    }

    public static double score(List list)
    {
        double d = 0.0D;
        int i = list.size();
        for(int j = 0; j < i; j++)
        {
            Mean mean = (Mean)list.get(j);
            int k = 0;
            while(k < i) 
            {
                Mean mean1 = (Mean)list.get(k);
                if(mean != mean1)
                    d += Math.sqrt(sqDistance(mean.mCentroid, mean1.mCentroid));
                k++;
            }
        }

        return d;
    }

    public static float sqDistance(float af[], float af1[])
    {
        float f = 0.0F;
        int i = af.length;
        for(int j = 0; j < i; j++)
            f += (af[j] - af1[j]) * (af[j] - af1[j]);

        return f;
    }

    private boolean step(ArrayList arraylist, float af[][])
    {
        for(int i = arraylist.size() - 1; i >= 0; i--)
            ((Mean)arraylist.get(i)).mClosestItems.clear();

        for(int j = af.length - 1; j >= 0; j--)
        {
            float af1[] = af[j];
            nearestMean(af1, arraylist).mClosestItems.add(af1);
        }

        boolean flag = true;
        int k = arraylist.size() - 1;
        while(k >= 0) 
        {
            af = (Mean)arraylist.get(k);
            if(((Mean) (af)).mClosestItems.size() != 0)
            {
                float af2[] = ((Mean) (af)).mCentroid;
                af.mCentroid = new float[af2.length];
                for(int l = 0; l < ((Mean) (af)).mClosestItems.size(); l++)
                {
                    for(int j1 = 0; j1 < ((Mean) (af)).mCentroid.length; j1++)
                    {
                        float af3[] = ((Mean) (af)).mCentroid;
                        float f = af3[j1];
                        af3[j1] = ((float[])((Mean) (af)).mClosestItems.get(l))[j1] + f;
                    }

                }

                for(int i1 = 0; i1 < ((Mean) (af)).mCentroid.length; i1++)
                {
                    float af4[] = ((Mean) (af)).mCentroid;
                    af4[i1] = af4[i1] / (float)((Mean) (af)).mClosestItems.size();
                }

                if(sqDistance(af2, ((Mean) (af)).mCentroid) > mSqConvergenceEpsilon)
                    flag = false;
            }
            k--;
        }
        return flag;
    }

    public void checkDataSetSanity(float af[][])
    {
        if(af == null)
            throw new IllegalArgumentException("Data set is null.");
        if(af.length == 0)
            throw new IllegalArgumentException("Data set is empty.");
        if(af[0] == null)
            throw new IllegalArgumentException("Bad data set format.");
        int i = af[0].length;
        int j = af.length;
        for(int k = 1; k < j; k++)
            if(af[k] == null || af[k].length != i)
                throw new IllegalArgumentException("Bad data set format.");

    }

    public List predict(int i, float af[][])
    {
        checkDataSetSanity(af);
        int j = af[0].length;
        ArrayList arraylist = new ArrayList();
        for(int k = 0; k < i; k++)
        {
            Mean mean = new Mean(j);
            for(int l = 0; l < j; l++)
                mean.mCentroid[l] = mRandomState.nextFloat();

            arraylist.add(mean);
        }

        i = 0;
        do
        {
            if(i >= mMaxIterations || step(arraylist, af))
                return arraylist;
            i++;
        } while(true);
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "KMeans";
    private final int mMaxIterations;
    private final Random mRandomState;
    private float mSqConvergenceEpsilon;
}
