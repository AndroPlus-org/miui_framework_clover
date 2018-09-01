// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.animation;

import java.util.List;

// Referenced classes of package android.animation:
//            TypeEvaluator

public interface Keyframes
    extends Cloneable
{
    public static interface FloatKeyframes
        extends Keyframes
    {

        public abstract float getFloatValue(float f);
    }

    public static interface IntKeyframes
        extends Keyframes
    {

        public abstract int getIntValue(float f);
    }


    public abstract Keyframes clone();

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public abstract List getKeyframes();

    public abstract Class getType();

    public abstract Object getValue(float f);

    public abstract void setEvaluator(TypeEvaluator typeevaluator);
}
