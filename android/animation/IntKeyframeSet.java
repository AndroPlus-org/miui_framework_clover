// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.animation;

import java.util.List;

// Referenced classes of package android.animation:
//            KeyframeSet, Keyframe, TimeInterpolator, TypeEvaluator, 
//            Keyframes

class IntKeyframeSet extends KeyframeSet
    implements Keyframes.IntKeyframes
{

    public transient IntKeyframeSet(Keyframe.IntKeyframe aintkeyframe[])
    {
        super(aintkeyframe);
    }

    public IntKeyframeSet clone()
    {
        List list = mKeyframes;
        int i = mKeyframes.size();
        Keyframe.IntKeyframe aintkeyframe[] = new Keyframe.IntKeyframe[i];
        for(int j = 0; j < i; j++)
            aintkeyframe[j] = (Keyframe.IntKeyframe)((Keyframe)list.get(j)).clone();

        return new IntKeyframeSet(aintkeyframe);
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

    public int getIntValue(float f)
    {
        if(f <= 0.0F)
        {
            Keyframe.IntKeyframe intkeyframe = (Keyframe.IntKeyframe)mKeyframes.get(0);
            Object obj = (Keyframe.IntKeyframe)mKeyframes.get(1);
            int i = intkeyframe.getIntValue();
            int l = ((Keyframe.IntKeyframe) (obj)).getIntValue();
            float f1 = intkeyframe.getFraction();
            float f3 = ((Keyframe.IntKeyframe) (obj)).getFraction();
            obj = ((Keyframe.IntKeyframe) (obj)).getInterpolator();
            float f5 = f;
            if(obj != null)
                f5 = ((TimeInterpolator) (obj)).getInterpolation(f);
            f = (f5 - f1) / (f3 - f1);
            if(mEvaluator == null)
                l = (int)((float)(l - i) * f) + i;
            else
                l = ((Number)mEvaluator.evaluate(f, Integer.valueOf(i), Integer.valueOf(l))).intValue();
            return l;
        }
        if(f >= 1.0F)
        {
            Object obj1 = (Keyframe.IntKeyframe)mKeyframes.get(mNumKeyframes - 2);
            Keyframe.IntKeyframe intkeyframe1 = (Keyframe.IntKeyframe)mKeyframes.get(mNumKeyframes - 1);
            int j = ((Keyframe.IntKeyframe) (obj1)).getIntValue();
            int i1 = intkeyframe1.getIntValue();
            float f2 = ((Keyframe.IntKeyframe) (obj1)).getFraction();
            float f4 = intkeyframe1.getFraction();
            obj1 = intkeyframe1.getInterpolator();
            float f6 = f;
            if(obj1 != null)
                f6 = ((TimeInterpolator) (obj1)).getInterpolation(f);
            f = (f6 - f2) / (f4 - f2);
            if(mEvaluator == null)
                i1 = (int)((float)(i1 - j) * f) + j;
            else
                i1 = ((Number)mEvaluator.evaluate(f, Integer.valueOf(j), Integer.valueOf(i1))).intValue();
            return i1;
        }
        Keyframe.IntKeyframe intkeyframe3 = (Keyframe.IntKeyframe)mKeyframes.get(0);
        for(int j1 = 1; j1 < mNumKeyframes; j1++)
        {
            Keyframe.IntKeyframe intkeyframe2 = (Keyframe.IntKeyframe)mKeyframes.get(j1);
            if(f < intkeyframe2.getFraction())
            {
                TimeInterpolator timeinterpolator = intkeyframe2.getInterpolator();
                float f7 = (f - intkeyframe3.getFraction()) / (intkeyframe2.getFraction() - intkeyframe3.getFraction());
                j1 = intkeyframe3.getIntValue();
                int k = intkeyframe2.getIntValue();
                f = f7;
                if(timeinterpolator != null)
                    f = timeinterpolator.getInterpolation(f7);
                if(mEvaluator == null)
                    j1 = (int)((float)(k - j1) * f) + j1;
                else
                    j1 = ((Number)mEvaluator.evaluate(f, Integer.valueOf(j1), Integer.valueOf(k))).intValue();
                return j1;
            }
            intkeyframe3 = intkeyframe2;
        }

        return ((Number)((Keyframe)mKeyframes.get(mNumKeyframes - 1)).getValue()).intValue();
    }

    public Class getType()
    {
        return java/lang/Integer;
    }

    public Object getValue(float f)
    {
        return Integer.valueOf(getIntValue(f));
    }
}
