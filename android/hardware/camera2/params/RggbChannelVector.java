// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.params;

import com.android.internal.util.Preconditions;

public final class RggbChannelVector
{

    public RggbChannelVector(float f, float f1, float f2, float f3)
    {
        mRed = Preconditions.checkArgumentFinite(f, "red");
        mGreenEven = Preconditions.checkArgumentFinite(f1, "greenEven");
        mGreenOdd = Preconditions.checkArgumentFinite(f2, "greenOdd");
        mBlue = Preconditions.checkArgumentFinite(f3, "blue");
    }

    private String toShortString()
    {
        return String.format("{R:%f, G_even:%f, G_odd:%f, B:%f}", new Object[] {
            Float.valueOf(mRed), Float.valueOf(mGreenEven), Float.valueOf(mGreenOdd), Float.valueOf(mBlue)
        });
    }

    public void copyTo(float af[], int i)
    {
        Preconditions.checkNotNull(af, "destination must not be null");
        if(af.length - i < 4)
        {
            throw new ArrayIndexOutOfBoundsException("destination too small to fit elements");
        } else
        {
            af[i + 0] = mRed;
            af[i + 1] = mGreenEven;
            af[i + 2] = mGreenOdd;
            af[i + 3] = mBlue;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        if(obj instanceof RggbChannelVector)
        {
            obj = (RggbChannelVector)obj;
            if(mRed == ((RggbChannelVector) (obj)).mRed && mGreenEven == ((RggbChannelVector) (obj)).mGreenEven && mGreenOdd == ((RggbChannelVector) (obj)).mGreenOdd)
            {
                if(mBlue != ((RggbChannelVector) (obj)).mBlue)
                    flag = false;
            } else
            {
                flag = false;
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public float getBlue()
    {
        return mBlue;
    }

    public float getComponent(int i)
    {
        if(i < 0 || i >= 4)
            throw new IllegalArgumentException("Color channel out of range");
        switch(i)
        {
        default:
            throw new AssertionError((new StringBuilder()).append("Unhandled case ").append(i).toString());

        case 0: // '\0'
            return mRed;

        case 1: // '\001'
            return mGreenEven;

        case 2: // '\002'
            return mGreenOdd;

        case 3: // '\003'
            return mBlue;
        }
    }

    public float getGreenEven()
    {
        return mGreenEven;
    }

    public float getGreenOdd()
    {
        return mGreenOdd;
    }

    public final float getRed()
    {
        return mRed;
    }

    public int hashCode()
    {
        return Float.floatToIntBits(mRed) ^ Float.floatToIntBits(mGreenEven) ^ Float.floatToIntBits(mGreenOdd) ^ Float.floatToIntBits(mBlue);
    }

    public String toString()
    {
        return String.format("RggbChannelVector%s", new Object[] {
            toShortString()
        });
    }

    public static final int BLUE = 3;
    public static final int COUNT = 4;
    public static final int GREEN_EVEN = 1;
    public static final int GREEN_ODD = 2;
    public static final int RED = 0;
    private final float mBlue;
    private final float mGreenEven;
    private final float mGreenOdd;
    private final float mRed;
}
