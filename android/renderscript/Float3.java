// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;


public class Float3
{

    public Float3()
    {
    }

    public Float3(float f, float f1, float f2)
    {
        x = f;
        y = f1;
        z = f2;
    }

    public Float3(Float3 float3)
    {
        x = float3.x;
        y = float3.y;
        z = float3.z;
    }

    public static Float3 add(Float3 float3, float f)
    {
        Float3 float3_1 = new Float3();
        float3_1.x = float3.x + f;
        float3_1.y = float3.y + f;
        float3_1.z = float3.z + f;
        return float3_1;
    }

    public static Float3 add(Float3 float3, Float3 float3_1)
    {
        Float3 float3_2 = new Float3();
        float3_2.x = float3.x + float3_1.x;
        float3_2.y = float3.y + float3_1.y;
        float3_2.z = float3.z + float3_1.z;
        return float3_2;
    }

    public static Float3 div(Float3 float3, float f)
    {
        Float3 float3_1 = new Float3();
        float3_1.x = float3.x / f;
        float3_1.y = float3.y / f;
        float3_1.z = float3.z / f;
        return float3_1;
    }

    public static Float3 div(Float3 float3, Float3 float3_1)
    {
        Float3 float3_2 = new Float3();
        float3_2.x = float3.x / float3_1.x;
        float3_2.y = float3.y / float3_1.y;
        float3_2.z = float3.z / float3_1.z;
        return float3_2;
    }

    public static Float dotProduct(Float3 float3, Float3 float3_1)
    {
        return new Float(float3_1.x * float3.x + float3_1.y * float3.y + float3_1.z * float3.z);
    }

    public static Float3 mul(Float3 float3, float f)
    {
        Float3 float3_1 = new Float3();
        float3_1.x = float3.x * f;
        float3_1.y = float3.y * f;
        float3_1.z = float3.z * f;
        return float3_1;
    }

    public static Float3 mul(Float3 float3, Float3 float3_1)
    {
        Float3 float3_2 = new Float3();
        float3_2.x = float3.x * float3_1.x;
        float3_2.y = float3.y * float3_1.y;
        float3_2.z = float3.z * float3_1.z;
        return float3_2;
    }

    public static Float3 sub(Float3 float3, float f)
    {
        Float3 float3_1 = new Float3();
        float3_1.x = float3.x - f;
        float3_1.y = float3.y - f;
        float3_1.z = float3.z - f;
        return float3_1;
    }

    public static Float3 sub(Float3 float3, Float3 float3_1)
    {
        Float3 float3_2 = new Float3();
        float3_2.x = float3.x - float3_1.x;
        float3_2.y = float3.y - float3_1.y;
        float3_2.z = float3.z - float3_1.z;
        return float3_2;
    }

    public void add(float f)
    {
        x = x + f;
        y = y + f;
        z = z + f;
    }

    public void add(Float3 float3)
    {
        x = x + float3.x;
        y = y + float3.y;
        z = z + float3.z;
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
            break;
        }
    }

    public void addMultiple(Float3 float3, float f)
    {
        x = x + float3.x * f;
        y = y + float3.y * f;
        z = z + float3.z * f;
    }

    public void copyTo(float af[], int i)
    {
        af[i] = x;
        af[i + 1] = y;
        af[i + 2] = z;
    }

    public void div(float f)
    {
        x = x / f;
        y = y / f;
        z = z / f;
    }

    public void div(Float3 float3)
    {
        x = x / float3.x;
        y = y / float3.y;
        z = z / float3.z;
    }

    public Float dotProduct(Float3 float3)
    {
        return new Float(x * float3.x + y * float3.y + z * float3.z);
    }

    public Float elementSum()
    {
        return new Float(x + y + z);
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
        }
    }

    public int length()
    {
        return 3;
    }

    public void mul(float f)
    {
        x = x * f;
        y = y * f;
        z = z * f;
    }

    public void mul(Float3 float3)
    {
        x = x * float3.x;
        y = y * float3.y;
        z = z * float3.z;
    }

    public void negate()
    {
        x = -x;
        y = -y;
        z = -z;
    }

    public void set(Float3 float3)
    {
        x = float3.x;
        y = float3.y;
        z = float3.z;
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
            break;
        }
    }

    public void setValues(float f, float f1, float f2)
    {
        x = f;
        y = f1;
        z = f2;
    }

    public void sub(float f)
    {
        x = x - f;
        y = y - f;
        z = z - f;
    }

    public void sub(Float3 float3)
    {
        x = x - float3.x;
        y = y - float3.y;
        z = z - float3.z;
    }

    public float x;
    public float y;
    public float z;
}
