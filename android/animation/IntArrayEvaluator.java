// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.animation;


// Referenced classes of package android.animation:
//            TypeEvaluator

public class IntArrayEvaluator
    implements TypeEvaluator
{

    public IntArrayEvaluator()
    {
    }

    public IntArrayEvaluator(int ai[])
    {
        mArray = ai;
    }

    public volatile Object evaluate(float f, Object obj, Object obj1)
    {
        return evaluate(f, (int[])obj, (int[])obj1);
    }

    public int[] evaluate(float f, int ai[], int ai1[])
    {
        int ai2[] = mArray;
        int ai3[] = ai2;
        if(ai2 == null)
            ai3 = new int[ai.length];
        for(int i = 0; i < ai3.length; i++)
        {
            int j = ai[i];
            int k = ai1[i];
            ai3[i] = (int)((float)j + (float)(k - j) * f);
        }

        return ai3;
    }

    private int mArray[];
}
