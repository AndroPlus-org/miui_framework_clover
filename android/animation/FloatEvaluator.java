// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.animation;


// Referenced classes of package android.animation:
//            TypeEvaluator

public class FloatEvaluator
    implements TypeEvaluator
{

    public FloatEvaluator()
    {
    }

    public Float evaluate(float f, Number number, Number number1)
    {
        float f1 = number.floatValue();
        return Float.valueOf((number1.floatValue() - f1) * f + f1);
    }

    public volatile Object evaluate(float f, Object obj, Object obj1)
    {
        return evaluate(f, (Number)obj, (Number)obj1);
    }
}
