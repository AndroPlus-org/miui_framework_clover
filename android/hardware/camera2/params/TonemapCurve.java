// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.params;

import android.graphics.PointF;
import android.hardware.camera2.utils.HashCodeHelpers;
import com.android.internal.util.Preconditions;
import java.util.Arrays;

public final class TonemapCurve
{

    public TonemapCurve(float af[], float af1[], float af2[])
    {
        mHashCalculated = false;
        Preconditions.checkNotNull(af, "red must not be null");
        Preconditions.checkNotNull(af1, "green must not be null");
        Preconditions.checkNotNull(af2, "blue must not be null");
        checkArgumentArrayLengthDivisibleBy(af, 2, "red");
        checkArgumentArrayLengthDivisibleBy(af1, 2, "green");
        checkArgumentArrayLengthDivisibleBy(af2, 2, "blue");
        checkArgumentArrayLengthNoLessThan(af, 4, "red");
        checkArgumentArrayLengthNoLessThan(af1, 4, "green");
        checkArgumentArrayLengthNoLessThan(af2, 4, "blue");
        Preconditions.checkArrayElementsInRange(af, 0.0F, 1.0F, "red");
        Preconditions.checkArrayElementsInRange(af1, 0.0F, 1.0F, "green");
        Preconditions.checkArrayElementsInRange(af2, 0.0F, 1.0F, "blue");
        mRed = Arrays.copyOf(af, af.length);
        mGreen = Arrays.copyOf(af1, af1.length);
        mBlue = Arrays.copyOf(af2, af2.length);
    }

    private static void checkArgumentArrayLengthDivisibleBy(float af[], int i, String s)
    {
        if(af.length % i != 0)
            throw new IllegalArgumentException((new StringBuilder()).append(s).append(" size must be divisible by ").append(i).toString());
        else
            return;
    }

    private static void checkArgumentArrayLengthNoLessThan(float af[], int i, String s)
    {
        if(af.length < i)
            throw new IllegalArgumentException((new StringBuilder()).append(s).append(" size must be at least ").append(i).toString());
        else
            return;
    }

    private static int checkArgumentColorChannel(int i)
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException("colorChannel out of range");

        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
            return i;
        }
    }

    private String curveToString(int i)
    {
        checkArgumentColorChannel(i);
        StringBuilder stringbuilder = new StringBuilder("[");
        float af[] = getCurve(i);
        int j = af.length / 2;
        int k = 0;
        for(i = 0; k < j; i += 2)
        {
            stringbuilder.append("(");
            stringbuilder.append(af[i]);
            stringbuilder.append(", ");
            stringbuilder.append(af[i + 1]);
            stringbuilder.append("), ");
            k++;
        }

        stringbuilder.setLength(stringbuilder.length() - 2);
        stringbuilder.append("]");
        return stringbuilder.toString();
    }

    private float[] getCurve(int i)
    {
        switch(i)
        {
        default:
            throw new AssertionError("colorChannel out of range");

        case 0: // '\0'
            return mRed;

        case 1: // '\001'
            return mGreen;

        case 2: // '\002'
            return mBlue;
        }
    }

    public void copyColorCurve(int i, float af[], int j)
    {
        Preconditions.checkArgumentNonnegative(j, "offset must not be negative");
        Preconditions.checkNotNull(af, "destination must not be null");
        if(af.length + j < getPointCount(i) * 2)
        {
            throw new ArrayIndexOutOfBoundsException("destination too small to fit elements");
        } else
        {
            float af1[] = getCurve(i);
            System.arraycopy(af1, 0, af, j, af1.length);
            return;
        }
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        if(obj instanceof TonemapCurve)
        {
            obj = (TonemapCurve)obj;
            boolean flag1 = flag;
            if(Arrays.equals(mRed, ((TonemapCurve) (obj)).mRed))
            {
                flag1 = flag;
                if(Arrays.equals(mGreen, ((TonemapCurve) (obj)).mGreen))
                    flag1 = Arrays.equals(mBlue, ((TonemapCurve) (obj)).mBlue);
            }
            return flag1;
        } else
        {
            return false;
        }
    }

    public PointF getPoint(int i, int j)
    {
        checkArgumentColorChannel(i);
        if(j < 0 || j >= getPointCount(i))
        {
            throw new IllegalArgumentException("index out of range");
        } else
        {
            float af[] = getCurve(i);
            return new PointF(af[j * 2 + 0], af[j * 2 + 1]);
        }
    }

    public int getPointCount(int i)
    {
        checkArgumentColorChannel(i);
        return getCurve(i).length / 2;
    }

    public int hashCode()
    {
        if(mHashCalculated)
        {
            return mHashCode;
        } else
        {
            mHashCode = HashCodeHelpers.hashCodeGeneric(new float[][] {
                mRed, mGreen, mBlue
            });
            mHashCalculated = true;
            return mHashCode;
        }
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("TonemapCurve{");
        stringbuilder.append("R:");
        stringbuilder.append(curveToString(0));
        stringbuilder.append(", G:");
        stringbuilder.append(curveToString(1));
        stringbuilder.append(", B:");
        stringbuilder.append(curveToString(2));
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    public static final int CHANNEL_BLUE = 2;
    public static final int CHANNEL_GREEN = 1;
    public static final int CHANNEL_RED = 0;
    public static final float LEVEL_BLACK = 0F;
    public static final float LEVEL_WHITE = 1F;
    private static final int MIN_CURVE_LENGTH = 4;
    private static final int OFFSET_POINT_IN = 0;
    private static final int OFFSET_POINT_OUT = 1;
    public static final int POINT_SIZE = 2;
    private static final int TONEMAP_MIN_CURVE_POINTS = 2;
    private final float mBlue[];
    private final float mGreen[];
    private boolean mHashCalculated;
    private int mHashCode;
    private final float mRed[];
}
