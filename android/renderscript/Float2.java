// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;


public class Float2
{

    public Float2()
    {
    }

    public Float2(float f, float f1)
    {
        x = f;
        y = f1;
    }

    public Float2(Float2 float2)
    {
        x = float2.x;
        y = float2.y;
    }

    public static Float2 add(Float2 float2, float f)
    {
        Float2 float2_1 = new Float2();
        float2_1.x = float2.x + f;
        float2_1.y = float2.y + f;
        return float2_1;
    }

    public static Float2 add(Float2 float2, Float2 float2_1)
    {
        Float2 float2_2 = new Float2();
        float2_2.x = float2.x + float2_1.x;
        float2_2.y = float2.y + float2_1.y;
        return float2_2;
    }

    public static Float2 div(Float2 float2, float f)
    {
        Float2 float2_1 = new Float2();
        float2_1.x = float2.x / f;
        float2_1.y = float2.y / f;
        return float2_1;
    }

    public static Float2 div(Float2 float2, Float2 float2_1)
    {
        Float2 float2_2 = new Float2();
        float2_2.x = float2.x / float2_1.x;
        float2_2.y = float2.y / float2_1.y;
        return float2_2;
    }

    public static float dotProduct(Float2 float2, Float2 float2_1)
    {
        return float2_1.x * float2.x + float2_1.y * float2.y;
    }

    public static Float2 mul(Float2 float2, float f)
    {
        Float2 float2_1 = new Float2();
        float2_1.x = float2.x * f;
        float2_1.y = float2.y * f;
        return float2_1;
    }

    public static Float2 mul(Float2 float2, Float2 float2_1)
    {
        Float2 float2_2 = new Float2();
        float2_2.x = float2.x * float2_1.x;
        float2_2.y = float2.y * float2_1.y;
        return float2_2;
    }

    public static Float2 sub(Float2 float2, float f)
    {
        Float2 float2_1 = new Float2();
        float2_1.x = float2.x - f;
        float2_1.y = float2.y - f;
        return float2_1;
    }

    public static Float2 sub(Float2 float2, Float2 float2_1)
    {
        Float2 float2_2 = new Float2();
        float2_2.x = float2.x - float2_1.x;
        float2_2.y = float2.y - float2_1.y;
        return float2_2;
    }

    public void add(float f)
    {
        x = x + f;
        y = y + f;
    }

    public void add(Float2 float2)
    {
        x = x + float2.x;
        y = y + float2.y;
    }

    public void addAt(int i, float f)
    {
        switch(i)
        {
        default:
            throw new IndexOutOfBoundsException("Index: i");

        case 0: // '\0'
            x = x + f;
            return;

        case 1: // '\001'
            y = y + f;
            break;
        }
    }

    public void addMultiple(Float2 float2, float f)
    {
        x = x + float2.x * f;
        y = y + float2.y * f;
    }

    public void copyTo(float af[], int i)
    {
        af[i] = x;
        af[i + 1] = y;
    }

    public void div(float f)
    {
        x = x / f;
        y = y / f;
    }

    public void div(Float2 float2)
    {
        x = x / float2.x;
        y = y / float2.y;
    }

    public float dotProduct(Float2 float2)
    {
        return x * float2.x + y * float2.y;
    }

    public float elementSum()
    {
        return x + y;
    }

    public float get(int i)
    {
        switch(i)
        {
        default:
            throw new IndexOutOfBoundsException("Index: i");

        case 0: // '\0'
            return x;

        case 1: // '\001'
            return y;
        }
    }

    public int length()
    {
        return 2;
    }

    public void mul(float f)
    {
        x = x * f;
        y = y * f;
    }

    public void mul(Float2 float2)
    {
        x = x * float2.x;
        y = y * float2.y;
    }

    public void negate()
    {
        x = -x;
        y = -y;
    }

    public void set(Float2 float2)
    {
        x = float2.x;
        y = float2.y;
    }

    public void setAt(int i, float f)
    {
        switch(i)
        {
        default:
            throw new IndexOutOfBoundsException("Index: i");

        case 0: // '\0'
            x = f;
            return;

        case 1: // '\001'
            y = f;
            break;
        }
    }

    public void setValues(float f, float f1)
    {
        x = f;
        y = f1;
    }

    public void sub(float f)
    {
        x = x - f;
        y = y - f;
    }

    public void sub(Float2 float2)
    {
        x = x - float2.x;
        y = y - float2.y;
    }

    public float x;
    public float y;
}
