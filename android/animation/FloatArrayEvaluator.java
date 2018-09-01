// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.animation;


// Referenced classes of package android.animation:
//            TypeEvaluator

public class FloatArrayEvaluator
    implements TypeEvaluator
{

    public FloatArrayEvaluator()
    {
    }

    public FloatArrayEvaluator(float af[])
    {
        mArray = af;
    }

    public volatile Object evaluate(float f, Object obj, Object obj1)
    {
        return evaluate(f, (float[])obj, (float[])obj1);
    }

    public float[] evaluate(float f, float af[], float af1[])
    {
        float af2[] = mArray;
        float af3[] = af2;
        if(af2 == null)
            af3 = new float[af.length];
        for(int i = 0; i < af3.length; i++)
        {
            float f1 = af[i];
            af3[i] = (af1[i] - f1) * f + f1;
        }

        return af3;
    }

    private float mArray[];
}
