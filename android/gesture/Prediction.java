// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.gesture;


public class Prediction
{

    Prediction(String s, double d)
    {
        name = s;
        score = d;
    }

    public String toString()
    {
        return name;
    }

    public final String name;
    public double score;
}
