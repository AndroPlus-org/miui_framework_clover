// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.animation;

import android.graphics.Path;
import android.util.Log;
import java.util.Arrays;
import java.util.List;

// Referenced classes of package android.animation:
//            Keyframes, Keyframe, FloatKeyframeSet, IntKeyframeSet, 
//            PathKeyframes, TimeInterpolator, TypeEvaluator

public class KeyframeSet
    implements Keyframes
{

    public transient KeyframeSet(Keyframe akeyframe[])
    {
        mNumKeyframes = akeyframe.length;
        mKeyframes = Arrays.asList(akeyframe);
        mFirstKeyframe = akeyframe[0];
        mLastKeyframe = akeyframe[mNumKeyframes - 1];
        mInterpolator = mLastKeyframe.getInterpolator();
    }

    public static transient KeyframeSet ofFloat(float af[])
    {
        boolean flag;
        boolean flag1;
        int i;
        Keyframe.FloatKeyframe afloatkeyframe[];
        flag = false;
        flag1 = false;
        i = af.length;
        afloatkeyframe = new Keyframe.FloatKeyframe[Math.max(i, 2)];
        if(i != 1) goto _L2; else goto _L1
_L1:
        afloatkeyframe[0] = (Keyframe.FloatKeyframe)Keyframe.ofFloat(0.0F);
        afloatkeyframe[1] = (Keyframe.FloatKeyframe)Keyframe.ofFloat(1.0F, af[0]);
        if(Float.isNaN(af[0]))
            flag1 = true;
_L4:
        if(flag1)
            Log.w("Animator", "Bad value (NaN) in float animator");
        return new FloatKeyframeSet(afloatkeyframe);
_L2:
        afloatkeyframe[0] = (Keyframe.FloatKeyframe)Keyframe.ofFloat(0.0F, af[0]);
        int j = 1;
        do
        {
            flag1 = flag;
            if(j >= i)
                continue;
            afloatkeyframe[j] = (Keyframe.FloatKeyframe)Keyframe.ofFloat((float)j / (float)(i - 1), af[j]);
            if(Float.isNaN(af[j]))
                flag = true;
            j++;
        } while(true);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static transient KeyframeSet ofInt(int ai[])
    {
        int i = ai.length;
        Keyframe.IntKeyframe aintkeyframe[] = new Keyframe.IntKeyframe[Math.max(i, 2)];
        if(i == 1)
        {
            aintkeyframe[0] = (Keyframe.IntKeyframe)Keyframe.ofInt(0.0F);
            aintkeyframe[1] = (Keyframe.IntKeyframe)Keyframe.ofInt(1.0F, ai[0]);
        } else
        {
            aintkeyframe[0] = (Keyframe.IntKeyframe)Keyframe.ofInt(0.0F, ai[0]);
            int j = 1;
            while(j < i) 
            {
                aintkeyframe[j] = (Keyframe.IntKeyframe)Keyframe.ofInt((float)j / (float)(i - 1), ai[j]);
                j++;
            }
        }
        return new IntKeyframeSet(aintkeyframe);
    }

    public static transient KeyframeSet ofKeyframe(Keyframe akeyframe[])
    {
        int i = akeyframe.length;
        boolean flag = false;
        boolean flag1 = false;
        boolean flag2 = false;
        int j = 0;
        while(j < i) 
        {
            if(akeyframe[j] instanceof Keyframe.FloatKeyframe)
                flag = true;
            else
            if(akeyframe[j] instanceof Keyframe.IntKeyframe)
                flag1 = true;
            else
                flag2 = true;
            j++;
        }
        if(flag && flag1 ^ true && flag2 ^ true)
        {
            Keyframe.FloatKeyframe afloatkeyframe[] = new Keyframe.FloatKeyframe[i];
            for(int k = 0; k < i; k++)
                afloatkeyframe[k] = (Keyframe.FloatKeyframe)akeyframe[k];

            return new FloatKeyframeSet(afloatkeyframe);
        }
        if(flag1 && flag ^ true && flag2 ^ true)
        {
            Keyframe.IntKeyframe aintkeyframe[] = new Keyframe.IntKeyframe[i];
            for(int l = 0; l < i; l++)
                aintkeyframe[l] = (Keyframe.IntKeyframe)akeyframe[l];

            return new IntKeyframeSet(aintkeyframe);
        } else
        {
            return new KeyframeSet(akeyframe);
        }
    }

    public static transient KeyframeSet ofObject(Object aobj[])
    {
        int i = aobj.length;
        Keyframe.ObjectKeyframe aobjectkeyframe[] = new Keyframe.ObjectKeyframe[Math.max(i, 2)];
        if(i == 1)
        {
            aobjectkeyframe[0] = (Keyframe.ObjectKeyframe)Keyframe.ofObject(0.0F);
            aobjectkeyframe[1] = (Keyframe.ObjectKeyframe)Keyframe.ofObject(1.0F, aobj[0]);
        } else
        {
            aobjectkeyframe[0] = (Keyframe.ObjectKeyframe)Keyframe.ofObject(0.0F, aobj[0]);
            int j = 1;
            while(j < i) 
            {
                aobjectkeyframe[j] = (Keyframe.ObjectKeyframe)Keyframe.ofObject((float)j / (float)(i - 1), aobj[j]);
                j++;
            }
        }
        return new KeyframeSet(aobjectkeyframe);
    }

    public static PathKeyframes ofPath(Path path)
    {
        return new PathKeyframes(path);
    }

    public static PathKeyframes ofPath(Path path, float f)
    {
        return new PathKeyframes(path, f);
    }

    public KeyframeSet clone()
    {
        List list = mKeyframes;
        int i = mKeyframes.size();
        Keyframe akeyframe[] = new Keyframe[i];
        for(int j = 0; j < i; j++)
            akeyframe[j] = ((Keyframe)list.get(j)).clone();

        return new KeyframeSet(akeyframe);
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

    public List getKeyframes()
    {
        return mKeyframes;
    }

    public Class getType()
    {
        return mFirstKeyframe.getType();
    }

    public Object getValue(float f)
    {
        if(mNumKeyframes == 2)
        {
            float f1 = f;
            if(mInterpolator != null)
                f1 = mInterpolator.getInterpolation(f);
            return mEvaluator.evaluate(f1, mFirstKeyframe.getValue(), mLastKeyframe.getValue());
        }
        if(f <= 0.0F)
        {
            Keyframe keyframe = (Keyframe)mKeyframes.get(1);
            TimeInterpolator timeinterpolator1 = keyframe.getInterpolator();
            float f2 = f;
            if(timeinterpolator1 != null)
                f2 = timeinterpolator1.getInterpolation(f);
            f = mFirstKeyframe.getFraction();
            f = (f2 - f) / (keyframe.getFraction() - f);
            return mEvaluator.evaluate(f, mFirstKeyframe.getValue(), keyframe.getValue());
        }
        if(f >= 1.0F)
        {
            Keyframe keyframe2 = (Keyframe)mKeyframes.get(mNumKeyframes - 2);
            TimeInterpolator timeinterpolator = mLastKeyframe.getInterpolator();
            float f3 = f;
            if(timeinterpolator != null)
                f3 = timeinterpolator.getInterpolation(f);
            f = keyframe2.getFraction();
            f = (f3 - f) / (mLastKeyframe.getFraction() - f);
            return mEvaluator.evaluate(f, keyframe2.getValue(), mLastKeyframe.getValue());
        }
        Keyframe keyframe1 = mFirstKeyframe;
        for(int i = 1; i < mNumKeyframes; i++)
        {
            Keyframe keyframe3 = (Keyframe)mKeyframes.get(i);
            if(f < keyframe3.getFraction())
            {
                TimeInterpolator timeinterpolator2 = keyframe3.getInterpolator();
                float f4 = keyframe1.getFraction();
                f4 = (f - f4) / (keyframe3.getFraction() - f4);
                f = f4;
                if(timeinterpolator2 != null)
                    f = timeinterpolator2.getInterpolation(f4);
                return mEvaluator.evaluate(f, keyframe1.getValue(), keyframe3.getValue());
            }
            keyframe1 = keyframe3;
        }

        return mLastKeyframe.getValue();
    }

    public void setEvaluator(TypeEvaluator typeevaluator)
    {
        mEvaluator = typeevaluator;
    }

    public String toString()
    {
        String s = " ";
        for(int i = 0; i < mNumKeyframes; i++)
            s = (new StringBuilder()).append(s).append(((Keyframe)mKeyframes.get(i)).getValue()).append("  ").toString();

        return s;
    }

    TypeEvaluator mEvaluator;
    Keyframe mFirstKeyframe;
    TimeInterpolator mInterpolator;
    List mKeyframes;
    Keyframe mLastKeyframe;
    int mNumKeyframes;
}
