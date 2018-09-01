// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.animation;

import java.util.List;

// Referenced classes of package android.animation:
//            KeyframeSet, Keyframe, TimeInterpolator, TypeEvaluator, 
//            Keyframes

class FloatKeyframeSet extends KeyframeSet
    implements Keyframes.FloatKeyframes
{

    public transient FloatKeyframeSet(Keyframe.FloatKeyframe afloatkeyframe[])
    {
        super(afloatkeyframe);
    }

    public FloatKeyframeSet clone()
    {
        List list = mKeyframes;
        int i = mKeyframes.size();
        Keyframe.FloatKeyframe afloatkeyframe[] = new Keyframe.FloatKeyframe[i];
        for(int j = 0; j < i; j++)
            afloatkeyframe[j] = (Keyframe.FloatKeyframe)((Keyframe)list.get(j)).clone();

        return new FloatKeyframeSet(afloatkeyframe);
    }

    public volatile KeyframeSet clone()
    {
        return clone();
    }

    public volatile Keyframes clone()
    {
        return clone();
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public float getFloatValue(float f)
    {
        if(f <= 0.0F)
        {
            Object obj = (Keyframe.FloatKeyframe)mKeyframes.get(0);
            Keyframe.FloatKeyframe floatkeyframe1 = (Keyframe.FloatKeyframe)mKeyframes.get(1);
            float f1 = ((Keyframe.FloatKeyframe) (obj)).getFloatValue();
            float f4 = floatkeyframe1.getFloatValue();
            float f7 = ((Keyframe.FloatKeyframe) (obj)).getFraction();
            float f9 = floatkeyframe1.getFraction();
            obj = floatkeyframe1.getInterpolator();
            float f11 = f;
            if(obj != null)
                f11 = ((TimeInterpolator) (obj)).getInterpolation(f);
            f = (f11 - f7) / (f9 - f7);
            if(mEvaluator == null)
                f = (f4 - f1) * f + f1;
            else
                f = ((Number)mEvaluator.evaluate(f, Float.valueOf(f1), Float.valueOf(f4))).floatValue();
            return f;
        }
        if(f >= 1.0F)
        {
            Object obj1 = (Keyframe.FloatKeyframe)mKeyframes.get(mNumKeyframes - 2);
            Keyframe.FloatKeyframe floatkeyframe2 = (Keyframe.FloatKeyframe)mKeyframes.get(mNumKeyframes - 1);
            float f2 = ((Keyframe.FloatKeyframe) (obj1)).getFloatValue();
            float f5 = floatkeyframe2.getFloatValue();
            float f8 = ((Keyframe.FloatKeyframe) (obj1)).getFraction();
            float f10 = floatkeyframe2.getFraction();
            obj1 = floatkeyframe2.getInterpolator();
            float f12 = f;
            if(obj1 != null)
                f12 = ((TimeInterpolator) (obj1)).getInterpolation(f);
            f = (f12 - f8) / (f10 - f8);
            if(mEvaluator == null)
                f = (f5 - f2) * f + f2;
            else
                f = ((Number)mEvaluator.evaluate(f, Float.valueOf(f2), Float.valueOf(f5))).floatValue();
            return f;
        }
        Keyframe.FloatKeyframe floatkeyframe = (Keyframe.FloatKeyframe)mKeyframes.get(0);
        for(int i = 1; i < mNumKeyframes; i++)
        {
            Keyframe.FloatKeyframe floatkeyframe3 = (Keyframe.FloatKeyframe)mKeyframes.get(i);
            if(f < floatkeyframe3.getFraction())
            {
                TimeInterpolator timeinterpolator = floatkeyframe3.getInterpolator();
                float f13 = (f - floatkeyframe.getFraction()) / (floatkeyframe3.getFraction() - floatkeyframe.getFraction());
                float f3 = floatkeyframe.getFloatValue();
                float f6 = floatkeyframe3.getFloatValue();
                f = f13;
                if(timeinterpolator != null)
                    f = timeinterpolator.getInterpolation(f13);
                if(mEvaluator == null)
                    f = (f6 - f3) * f + f3;
                else
                    f = ((Number)mEvaluator.evaluate(f, Float.valueOf(f3), Float.valueOf(f6))).floatValue();
                return f;
            }
            floatkeyframe = floatkeyframe3;
        }

        return ((Number)((Keyframe)mKeyframes.get(mNumKeyframes - 1)).getValue()).floatValue();
    }

    public Class getType()
    {
        return java/lang/Float;
    }

    public Object getValue(float f)
    {
        return Float.valueOf(getFloatValue(f));
    }
}
