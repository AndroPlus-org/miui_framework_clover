// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.animation;

import android.graphics.Path;
import android.graphics.PointF;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.animation:
//            Keyframes, TypeEvaluator

public class PathKeyframes
    implements Keyframes
{
    static abstract class FloatKeyframesBase extends SimpleKeyframes
        implements Keyframes.FloatKeyframes
    {

        public Class getType()
        {
            return java/lang/Float;
        }

        public Object getValue(float f)
        {
            return Float.valueOf(getFloatValue(f));
        }

        FloatKeyframesBase()
        {
            super(null);
        }
    }

    static abstract class IntKeyframesBase extends SimpleKeyframes
        implements Keyframes.IntKeyframes
    {

        public Class getType()
        {
            return java/lang/Integer;
        }

        public Object getValue(float f)
        {
            return Integer.valueOf(getIntValue(f));
        }

        IntKeyframesBase()
        {
            super(null);
        }
    }

    private static abstract class SimpleKeyframes
        implements Keyframes
    {

        public Keyframes clone()
        {
            Keyframes keyframes = null;
            Keyframes keyframes1 = (Keyframes)super.clone();
            keyframes = keyframes1;
_L2:
            return keyframes;
            CloneNotSupportedException clonenotsupportedexception;
            clonenotsupportedexception;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }

        public ArrayList getKeyframes()
        {
            return PathKeyframes._2D_get0();
        }

        public volatile List getKeyframes()
        {
            return getKeyframes();
        }

        public void setEvaluator(TypeEvaluator typeevaluator)
        {
        }

        private SimpleKeyframes()
        {
        }

        SimpleKeyframes(SimpleKeyframes simplekeyframes)
        {
            this();
        }
    }


    static ArrayList _2D_get0()
    {
        return EMPTY_KEYFRAMES;
    }

    public PathKeyframes(Path path)
    {
        this(path, 0.5F);
    }

    public PathKeyframes(Path path, float f)
    {
        mTempPointF = new PointF();
        if(path == null || path.isEmpty())
        {
            throw new IllegalArgumentException("The path must not be null or empty");
        } else
        {
            mKeyframeData = path.approximate(f);
            return;
        }
    }

    private static float interpolate(float f, float f1, float f2)
    {
        return (f2 - f1) * f + f1;
    }

    private PointF interpolateInRange(float f, int i, int j)
    {
        i *= 3;
        j *= 3;
        float f1 = mKeyframeData[i + 0];
        f = (f - f1) / (mKeyframeData[j + 0] - f1);
        float f2 = mKeyframeData[i + 1];
        float f3 = mKeyframeData[j + 1];
        float f4 = mKeyframeData[i + 2];
        f1 = mKeyframeData[j + 2];
        f2 = interpolate(f, f2, f3);
        f = interpolate(f, f4, f1);
        mTempPointF.set(f2, f);
        return mTempPointF;
    }

    private PointF pointForIndex(int i)
    {
        i *= 3;
        mTempPointF.set(mKeyframeData[i + 1], mKeyframeData[i + 2]);
        return mTempPointF;
    }

    public Keyframes clone()
    {
        Keyframes keyframes = null;
        Keyframes keyframes1 = (Keyframes)super.clone();
        keyframes = keyframes1;
_L2:
        return keyframes;
        CloneNotSupportedException clonenotsupportedexception;
        clonenotsupportedexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public Keyframes.FloatKeyframes createXFloatKeyframes()
    {
        return new FloatKeyframesBase() {

            public float getFloatValue(float f)
            {
                return ((PointF)getValue(f)).x;
            }

            final PathKeyframes this$0;

            
            {
                this$0 = PathKeyframes.this;
                super();
            }
        }
;
    }

    public Keyframes.IntKeyframes createXIntKeyframes()
    {
        return new IntKeyframesBase() {

            public int getIntValue(float f)
            {
                return Math.round(((PointF)getValue(f)).x);
            }

            final PathKeyframes this$0;

            
            {
                this$0 = PathKeyframes.this;
                super();
            }
        }
;
    }

    public Keyframes.FloatKeyframes createYFloatKeyframes()
    {
        return new FloatKeyframesBase() {

            public float getFloatValue(float f)
            {
                return ((PointF)getValue(f)).y;
            }

            final PathKeyframes this$0;

            
            {
                this$0 = PathKeyframes.this;
                super();
            }
        }
;
    }

    public Keyframes.IntKeyframes createYIntKeyframes()
    {
        return new IntKeyframesBase() {

            public int getIntValue(float f)
            {
                return Math.round(((PointF)getValue(f)).y);
            }

            final PathKeyframes this$0;

            
            {
                this$0 = PathKeyframes.this;
                super();
            }
        }
;
    }

    public ArrayList getKeyframes()
    {
        return EMPTY_KEYFRAMES;
    }

    public volatile List getKeyframes()
    {
        return getKeyframes();
    }

    public Class getType()
    {
        return android/graphics/PointF;
    }

    public Object getValue(float f)
    {
        int i = mKeyframeData.length / 3;
        if(f < 0.0F)
            return interpolateInRange(f, 0, 1);
        if(f > 1.0F)
            return interpolateInRange(f, i - 2, i - 1);
        if(f == 0.0F)
            return pointForIndex(0);
        if(f == 1.0F)
            return pointForIndex(i - 1);
        int j = 0;
        for(i--; j <= i;)
        {
            int k = (j + i) / 2;
            float f1 = mKeyframeData[k * 3 + 0];
            if(f < f1)
                i = k - 1;
            else
            if(f > f1)
                j = k + 1;
            else
                return pointForIndex(k);
        }

        return interpolateInRange(f, i, j);
    }

    public void setEvaluator(TypeEvaluator typeevaluator)
    {
    }

    private static final ArrayList EMPTY_KEYFRAMES = new ArrayList();
    private static final int FRACTION_OFFSET = 0;
    private static final int NUM_COMPONENTS = 3;
    private static final int X_OFFSET = 1;
    private static final int Y_OFFSET = 2;
    private float mKeyframeData[];
    private PointF mTempPointF;

}
