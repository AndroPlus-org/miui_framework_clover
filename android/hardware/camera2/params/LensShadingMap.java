// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.params;

import android.hardware.camera2.utils.HashCodeHelpers;
import com.android.internal.util.Preconditions;
import java.util.Arrays;

// Referenced classes of package android.hardware.camera2.params:
//            RggbChannelVector

public final class LensShadingMap
{

    public LensShadingMap(float af[], int i, int j)
    {
        mRows = Preconditions.checkArgumentPositive(i, "rows must be positive");
        mColumns = Preconditions.checkArgumentPositive(j, "columns must be positive");
        mElements = (float[])Preconditions.checkNotNull(af, "elements must not be null");
        if(af.length != getGainFactorCount())
        {
            throw new IllegalArgumentException((new StringBuilder()).append("elements must be ").append(getGainFactorCount()).append(" length, received ").append(af.length).toString());
        } else
        {
            Preconditions.checkArrayElementsInRange(af, 1.0F, 3.402823E+038F, "elements");
            return;
        }
    }

    public void copyGainFactors(float af[], int i)
    {
        Preconditions.checkArgumentNonnegative(i, "offset must not be negative");
        Preconditions.checkNotNull(af, "destination must not be null");
        if(af.length + i < getGainFactorCount())
        {
            throw new ArrayIndexOutOfBoundsException("destination too small to fit elements");
        } else
        {
            System.arraycopy(mElements, 0, af, i, getGainFactorCount());
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
        if(obj instanceof LensShadingMap)
        {
            obj = (LensShadingMap)obj;
            boolean flag1 = flag;
            if(mRows == ((LensShadingMap) (obj)).mRows)
            {
                flag1 = flag;
                if(mColumns == ((LensShadingMap) (obj)).mColumns)
                    flag1 = Arrays.equals(mElements, ((LensShadingMap) (obj)).mElements);
            }
            return flag1;
        } else
        {
            return false;
        }
    }

    public int getColumnCount()
    {
        return mColumns;
    }

    public float getGainFactor(int i, int j, int k)
    {
        if(i < 0 || i > 4)
            throw new IllegalArgumentException("colorChannel out of range");
        if(j < 0 || j >= mColumns)
            throw new IllegalArgumentException("column out of range");
        if(k < 0 || k >= mRows)
            throw new IllegalArgumentException("row out of range");
        else
            return mElements[(mColumns * k + j) * 4 + i];
    }

    public int getGainFactorCount()
    {
        return mRows * mColumns * 4;
    }

    public RggbChannelVector getGainFactorVector(int i, int j)
    {
        if(i < 0 || i >= mColumns)
            throw new IllegalArgumentException("column out of range");
        if(j < 0 || j >= mRows)
        {
            throw new IllegalArgumentException("row out of range");
        } else
        {
            i = (mColumns * j + i) * 4;
            return new RggbChannelVector(mElements[i + 0], mElements[i + 1], mElements[i + 2], mElements[i + 3]);
        }
    }

    public int getRowCount()
    {
        return mRows;
    }

    public int hashCode()
    {
        int i = HashCodeHelpers.hashCode(mElements);
        return HashCodeHelpers.hashCode(new int[] {
            mRows, mColumns, i
        });
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("LensShadingMap{");
        for(int i = 0; i < 4; i++)
        {
            stringbuilder.append((new String[] {
                "R:(", "G_even:(", "G_odd:(", "B:("
            })[i]);
            for(int j = 0; j < mRows; j++)
            {
                stringbuilder.append("[");
                for(int k = 0; k < mColumns; k++)
                {
                    stringbuilder.append(getGainFactor(i, k, j));
                    if(k < mColumns - 1)
                        stringbuilder.append(", ");
                }

                stringbuilder.append("]");
                if(j < mRows - 1)
                    stringbuilder.append(", ");
            }

            stringbuilder.append(")");
            if(i < 3)
                stringbuilder.append(", ");
        }

        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    public static final float MINIMUM_GAIN_FACTOR = 1F;
    private final int mColumns;
    private final float mElements[];
    private final int mRows;
}
