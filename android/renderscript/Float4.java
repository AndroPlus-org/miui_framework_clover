// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;


public class Float4
{

    public Float4()
    {
    }

    public Float4(float f, float f1, float f2, float f3)
    {
        x = f;
        y = f1;
        z = f2;
        w = f3;
    }

    public Float4(Float4 float4)
    {
        x = float4.x;
        y = float4.y;
        z = float4.z;
        w = float4.w;
    }

    public static Float4 add(Float4 float4, float f)
    {
        Float4 float4_1 = new Float4();
        float4_1.x = float4.x + f;
        float4_1.y = float4.y + f;
        float4_1.z = float4.z + f;
        float4_1.w = float4.w + f;
        return float4_1;
    }

    public static Float4 add(Float4 float4, Float4 float4_1)
    {
        Float4 float4_2 = new Float4();
        float4_2.x = float4.x + float4_1.x;
        float4_2.y = float4.y + float4_1.y;
        float4_2.z = float4.z + float4_1.z;
        float4_2.w = float4.w + float4_1.w;
        return float4_2;
    }

    public static Float4 div(Float4 float4, float f)
    {
        Float4 float4_1 = new Float4();
        float4_1.x = float4.x / f;
        float4_1.y = float4.y / f;
        float4_1.z = float4.z / f;
        float4_1.w = float4.w / f;
        return float4_1;
    }

    public static Float4 div(Float4 float4, Float4 float4_1)
    {
        Float4 float4_2 = new Float4();
        float4_2.x = float4.x / float4_1.x;
        float4_2.y = float4.y / float4_1.y;
        float4_2.z = float4.z / float4_1.z;
        float4_2.w = float4.w / float4_1.w;
        return float4_2;
    }

    public static float dotProduct(Float4 float4, Float4 float4_1)
    {
        return float4_1.x * float4.x + float4_1.y * float4.y + float4_1.z * float4.z + float4_1.w * float4.w;
    }

    public static Float4 mul(Float4 float4, float f)
    {
        Float4 float4_1 = new Float4();
        float4_1.x = float4.x * f;
        float4_1.y = float4.y * f;
        float4_1.z = float4.z * f;
        float4_1.w = float4.w * f;
        return float4_1;
    }

    public static Float4 mul(Float4 float4, Float4 float4_1)
    {
        Float4 float4_2 = new Float4();
        float4_2.x = float4.x * float4_1.x;
        float4_2.y = float4.y * float4_1.y;
        float4_2.z = float4.z * float4_1.z;
        float4_2.w = float4.w * float4_1.w;
        return float4_2;
    }

    public static Float4 sub(Float4 float4, float f)
    {
        Float4 float4_1 = new Float4();
        float4_1.x = float4.x - f;
        float4_1.y = float4.y - f;
        float4_1.z = float4.z - f;
        float4_1.w = float4.w - f;
        return float4_1;
    }

    public static Float4 sub(Float4 float4, Float4 float4_1)
    {
        Float4 float4_2 = new Float4();
        float4_2.x = float4.x - float4_1.x;
        float4_2.y = float4.y - float4_1.y;
        float4_2.z = float4.z - float4_1.z;
        float4_2.w = float4.w - float4_1.w;
        return float4_2;
    }

    public void add(float f)
    {
        x = x + f;
        y = y + f;
        z = z + f;
        w = w + f;
    }

    public void add(Float4 float4)
    {
        x = x + float4.x;
        y = y + float4.y;
        z = z + float4.z;
        w = w + float4.w;
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
            return;

        case 2: // '\002'
            z = z + f;
            return;

        case 3: // '\003'
            w = w + f;
            break;
        }
    }

    public void addMultiple(Float4 float4, float f)
    {
        x = x + float4.x * f;
        y = y + float4.y * f;
        z = z + float4.z * f;
        w = w + float4.w * f;
    }

    public void copyTo(float af[], int i)
    {
        af[i] = x;
        af[i + 1] = y;
        af[i + 2] = z;
        af[i + 3] = w;
    }

    public void div(float f)
    {
        x = x / f;
        y = y / f;
        z = z / f;
        w = w / f;
    }

    public void div(Float4 float4)
    {
        x = x / float4.x;
        y = y / float4.y;
        z = z / float4.z;
        w = w / float4.w;
    }

    public float dotProduct(Float4 float4)
    {
        return x * float4.x + y * float4.y + z * float4.z + w * float4.w;
    }

    public float elementSum()
    {
        return x + y + z + w;
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

        case 2: // '\002'
            return z;

        case 3: // '\003'
            return w;
        }
    }

    public int length()
    {
        return 4;
    }

    public void mul(float f)
    {
        x = x * f;
        y = y * f;
        z = z * f;
        w = w * f;
    }

    public void mul(Float4 float4)
    {
        x = x * float4.x;
        y = y * float4.y;
        z = z * float4.z;
        w = w * float4.w;
    }

    public void negate()
    {
        x = -x;
        y = -y;
        z = -z;
        w = -w;
    }

    public void set(Float4 float4)
    {
        x = float4.x;
        y = float4.y;
        z = float4.z;
        w = float4.w;
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
            return;

        case 2: // '\002'
            z = f;
            return;

        case 3: // '\003'
            w = f;
            break;
        }
    }

    public void setValues(float f, float f1, float f2, float f3)
    {
        x = f;
        y = f1;
        z = f2;
        w = f3;
    }

    public void sub(float f)
    {
        x = x - f;
        y = y - f;
        z = z - f;
        w = w - f;
    }

    public void sub(Float4 float4)
    {
        x = x - float4.x;
        y = y - float4.y;
        z = z - float4.z;
        w = w - float4.w;
    }

    public float w;
    public float x;
    public float y;
    public float z;
}
