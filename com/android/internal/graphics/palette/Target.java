// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.graphics.palette;


public final class Target
{
    public static final class Builder
    {

        public Target build()
        {
            return mTarget;
        }

        public Builder setExclusive(boolean flag)
        {
            mTarget.mIsExclusive = flag;
            return this;
        }

        public Builder setLightnessWeight(float f)
        {
            mTarget.mWeights[1] = f;
            return this;
        }

        public Builder setMaximumLightness(float f)
        {
            mTarget.mLightnessTargets[2] = f;
            return this;
        }

        public Builder setMaximumSaturation(float f)
        {
            mTarget.mSaturationTargets[2] = f;
            return this;
        }

        public Builder setMinimumLightness(float f)
        {
            mTarget.mLightnessTargets[0] = f;
            return this;
        }

        public Builder setMinimumSaturation(float f)
        {
            mTarget.mSaturationTargets[0] = f;
            return this;
        }

        public Builder setPopulationWeight(float f)
        {
            mTarget.mWeights[2] = f;
            return this;
        }

        public Builder setSaturationWeight(float f)
        {
            mTarget.mWeights[0] = f;
            return this;
        }

        public Builder setTargetLightness(float f)
        {
            mTarget.mLightnessTargets[1] = f;
            return this;
        }

        public Builder setTargetSaturation(float f)
        {
            mTarget.mSaturationTargets[1] = f;
            return this;
        }

        private final Target mTarget;

        public Builder()
        {
            mTarget = new Target();
        }

        public Builder(Target target)
        {
            mTarget = new Target(target);
        }
    }


    Target()
    {
        mSaturationTargets = new float[3];
        mLightnessTargets = new float[3];
        mWeights = new float[3];
        mIsExclusive = true;
        setTargetDefaultValues(mSaturationTargets);
        setTargetDefaultValues(mLightnessTargets);
        setDefaultWeights();
    }

    Target(Target target)
    {
        mSaturationTargets = new float[3];
        mLightnessTargets = new float[3];
        mWeights = new float[3];
        mIsExclusive = true;
        System.arraycopy(target.mSaturationTargets, 0, mSaturationTargets, 0, mSaturationTargets.length);
        System.arraycopy(target.mLightnessTargets, 0, mLightnessTargets, 0, mLightnessTargets.length);
        System.arraycopy(target.mWeights, 0, mWeights, 0, mWeights.length);
    }

    private static void setDefaultDarkLightnessValues(Target target)
    {
        target.mLightnessTargets[1] = 0.26F;
        target.mLightnessTargets[2] = 0.45F;
    }

    private static void setDefaultLightLightnessValues(Target target)
    {
        target.mLightnessTargets[0] = 0.55F;
        target.mLightnessTargets[1] = 0.74F;
    }

    private static void setDefaultMutedSaturationValues(Target target)
    {
        target.mSaturationTargets[1] = 0.3F;
        target.mSaturationTargets[2] = 0.4F;
    }

    private static void setDefaultNormalLightnessValues(Target target)
    {
        target.mLightnessTargets[0] = 0.3F;
        target.mLightnessTargets[1] = 0.5F;
        target.mLightnessTargets[2] = 0.7F;
    }

    private static void setDefaultVibrantSaturationValues(Target target)
    {
        target.mSaturationTargets[0] = 0.35F;
        target.mSaturationTargets[1] = 1.0F;
    }

    private void setDefaultWeights()
    {
        mWeights[0] = 0.24F;
        mWeights[1] = 0.52F;
        mWeights[2] = 0.24F;
    }

    private static void setTargetDefaultValues(float af[])
    {
        af[0] = 0.0F;
        af[1] = 0.5F;
        af[2] = 1.0F;
    }

    public float getLightnessWeight()
    {
        return mWeights[1];
    }

    public float getMaximumLightness()
    {
        return mLightnessTargets[2];
    }

    public float getMaximumSaturation()
    {
        return mSaturationTargets[2];
    }

    public float getMinimumLightness()
    {
        return mLightnessTargets[0];
    }

    public float getMinimumSaturation()
    {
        return mSaturationTargets[0];
    }

    public float getPopulationWeight()
    {
        return mWeights[2];
    }

    public float getSaturationWeight()
    {
        return mWeights[0];
    }

    public float getTargetLightness()
    {
        return mLightnessTargets[1];
    }

    public float getTargetSaturation()
    {
        return mSaturationTargets[1];
    }

    public boolean isExclusive()
    {
        return mIsExclusive;
    }

    void normalizeWeights()
    {
        float f = 0.0F;
        int i = 0;
        for(int k = mWeights.length; i < k;)
        {
            float f1 = mWeights[i];
            float f2 = f;
            if(f1 > 0.0F)
                f2 = f + f1;
            i++;
            f = f2;
        }

        if(f != 0.0F)
        {
            int j = 0;
            for(int l = mWeights.length; j < l; j++)
                if(mWeights[j] > 0.0F)
                {
                    float af[] = mWeights;
                    af[j] = af[j] / f;
                }

        }
    }

    public static final Target DARK_MUTED;
    public static final Target DARK_VIBRANT;
    static final int INDEX_MAX = 2;
    static final int INDEX_MIN = 0;
    static final int INDEX_TARGET = 1;
    static final int INDEX_WEIGHT_LUMA = 1;
    static final int INDEX_WEIGHT_POP = 2;
    static final int INDEX_WEIGHT_SAT = 0;
    public static final Target LIGHT_MUTED;
    public static final Target LIGHT_VIBRANT;
    private static final float MAX_DARK_LUMA = 0.45F;
    private static final float MAX_MUTED_SATURATION = 0.4F;
    private static final float MAX_NORMAL_LUMA = 0.7F;
    private static final float MIN_LIGHT_LUMA = 0.55F;
    private static final float MIN_NORMAL_LUMA = 0.3F;
    private static final float MIN_VIBRANT_SATURATION = 0.35F;
    public static final Target MUTED;
    private static final float TARGET_DARK_LUMA = 0.26F;
    private static final float TARGET_LIGHT_LUMA = 0.74F;
    private static final float TARGET_MUTED_SATURATION = 0.3F;
    private static final float TARGET_NORMAL_LUMA = 0.5F;
    private static final float TARGET_VIBRANT_SATURATION = 1F;
    public static final Target VIBRANT;
    private static final float WEIGHT_LUMA = 0.52F;
    private static final float WEIGHT_POPULATION = 0.24F;
    private static final float WEIGHT_SATURATION = 0.24F;
    boolean mIsExclusive;
    final float mLightnessTargets[];
    final float mSaturationTargets[];
    final float mWeights[];

    static 
    {
        LIGHT_VIBRANT = new Target();
        setDefaultLightLightnessValues(LIGHT_VIBRANT);
        setDefaultVibrantSaturationValues(LIGHT_VIBRANT);
        VIBRANT = new Target();
        setDefaultNormalLightnessValues(VIBRANT);
        setDefaultVibrantSaturationValues(VIBRANT);
        DARK_VIBRANT = new Target();
        setDefaultDarkLightnessValues(DARK_VIBRANT);
        setDefaultVibrantSaturationValues(DARK_VIBRANT);
        LIGHT_MUTED = new Target();
        setDefaultLightLightnessValues(LIGHT_MUTED);
        setDefaultMutedSaturationValues(LIGHT_MUTED);
        MUTED = new Target();
        setDefaultNormalLightnessValues(MUTED);
        setDefaultMutedSaturationValues(MUTED);
        DARK_MUTED = new Target();
        setDefaultDarkLightnessValues(DARK_MUTED);
        setDefaultMutedSaturationValues(DARK_MUTED);
    }
}
