// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.params;

import android.hardware.camera2.utils.HashCodeHelpers;
import android.util.Rational;
import com.android.internal.util.Preconditions;
import java.util.Arrays;

public final class ColorSpaceTransform
{

    public ColorSpaceTransform(int ai[])
    {
        Preconditions.checkNotNull(ai, "elements must not be null");
        if(ai.length != 18)
            throw new IllegalArgumentException("elements must be 18 length");
        for(int i = 0; i < ai.length; i++)
            Preconditions.checkNotNull(ai, (new StringBuilder()).append("element ").append(i).append(" must not be null").toString());

        mElements = Arrays.copyOf(ai, ai.length);
    }

    public ColorSpaceTransform(Rational arational[])
    {
        Preconditions.checkNotNull(arational, "elements must not be null");
        if(arational.length != 9)
            throw new IllegalArgumentException("elements must be 9 length");
        mElements = new int[18];
        for(int i = 0; i < arational.length; i++)
        {
            Preconditions.checkNotNull(arational, (new StringBuilder()).append("element[").append(i).append("] must not be null").toString());
            mElements[i * 2 + 0] = arational[i].getNumerator();
            mElements[i * 2 + 1] = arational[i].getDenominator();
        }

    }

    private String toShortString()
    {
        StringBuilder stringbuilder = new StringBuilder("(");
        int i = 0;
        int j = 0;
        for(; i < 3; i++)
        {
            stringbuilder.append("[");
            for(int k = 0; k < 3;)
            {
                int l = mElements[j + 0];
                int i1 = mElements[j + 1];
                stringbuilder.append(l);
                stringbuilder.append("/");
                stringbuilder.append(i1);
                if(k < 2)
                    stringbuilder.append(", ");
                k++;
                j += 2;
            }

            stringbuilder.append("]");
            if(i < 2)
                stringbuilder.append(", ");
        }

        stringbuilder.append(")");
        return stringbuilder.toString();
    }

    public void copyElements(int ai[], int i)
    {
        Preconditions.checkArgumentNonnegative(i, "offset must not be negative");
        Preconditions.checkNotNull(ai, "destination must not be null");
        if(ai.length - i < 18)
            throw new ArrayIndexOutOfBoundsException("destination too small to fit elements");
        for(int j = 0; j < 18; j++)
            ai[j + i] = mElements[j];

    }

    public void copyElements(Rational arational[], int i)
    {
        Preconditions.checkArgumentNonnegative(i, "offset must not be negative");
        Preconditions.checkNotNull(arational, "destination must not be null");
        if(arational.length - i < 9)
            throw new ArrayIndexOutOfBoundsException("destination too small to fit elements");
        int j = 0;
        for(int k = 0; j < 9; k += 2)
        {
            arational[j + i] = new Rational(mElements[k + 0], mElements[k + 1]);
            j++;
        }

    }

    public boolean equals(Object obj)
    {
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        if(obj instanceof ColorSpaceTransform)
        {
            obj = (ColorSpaceTransform)obj;
            int i = 0;
            for(int j = 0; i < 9; j += 2)
            {
                int k = mElements[j + 0];
                int l = mElements[j + 1];
                int i1 = ((ColorSpaceTransform) (obj)).mElements[j + 0];
                int j1 = ((ColorSpaceTransform) (obj)).mElements[j + 1];
                if(!(new Rational(k, l)).equals(new Rational(i1, j1)))
                    return false;
                i++;
            }

            return true;
        } else
        {
            return false;
        }
    }

    public Rational getElement(int i, int j)
    {
        if(i < 0 || i >= 3)
            throw new IllegalArgumentException("column out of range");
        if(j < 0 || j >= 3)
            throw new IllegalArgumentException("row out of range");
        else
            return new Rational(mElements[(j * 3 + i) * 2 + 0], mElements[(j * 3 + i) * 2 + 1]);
    }

    public int hashCode()
    {
        return HashCodeHelpers.hashCode(mElements);
    }

    public String toString()
    {
        return String.format("ColorSpaceTransform%s", new Object[] {
            toShortString()
        });
    }

    private static final int COLUMNS = 3;
    private static final int COUNT = 9;
    private static final int COUNT_INT = 18;
    private static final int OFFSET_DENOMINATOR = 1;
    private static final int OFFSET_NUMERATOR = 0;
    private static final int RATIONAL_SIZE = 2;
    private static final int ROWS = 3;
    private final int mElements[];
}
