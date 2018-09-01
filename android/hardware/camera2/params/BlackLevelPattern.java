// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.params;

import com.android.internal.util.Preconditions;
import java.util.Arrays;

public final class BlackLevelPattern
{

    public BlackLevelPattern(int ai[])
    {
        if(ai == null)
            throw new NullPointerException("Null offsets array passed to constructor");
        if(ai.length < 4)
        {
            throw new IllegalArgumentException("Invalid offsets array length");
        } else
        {
            mCfaOffsets = Arrays.copyOf(ai, 4);
            return;
        }
    }

    public void copyTo(int ai[], int i)
    {
        Preconditions.checkNotNull(ai, "destination must not be null");
        if(i < 0)
            throw new IllegalArgumentException("Null offset passed to copyTo");
        if(ai.length - i < 4)
            throw new ArrayIndexOutOfBoundsException("destination too small to fit elements");
        for(int j = 0; j < 4; j++)
            ai[i + j] = mCfaOffsets[j];

    }

    public boolean equals(Object obj)
    {
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        if(obj instanceof BlackLevelPattern)
            return Arrays.equals(((BlackLevelPattern)obj).mCfaOffsets, mCfaOffsets);
        else
            return false;
    }

    public int getOffsetForIndex(int i, int j)
    {
        if(j < 0 || i < 0)
            throw new IllegalArgumentException("column, row arguments must be positive");
        else
            return mCfaOffsets[(j & 1) << 1 | i & 1];
    }

    public int hashCode()
    {
        return Arrays.hashCode(mCfaOffsets);
    }

    public String toString()
    {
        return String.format("BlackLevelPattern([%d, %d], [%d, %d])", new Object[] {
            Integer.valueOf(mCfaOffsets[0]), Integer.valueOf(mCfaOffsets[1]), Integer.valueOf(mCfaOffsets[2]), Integer.valueOf(mCfaOffsets[3])
        });
    }

    public static final int COUNT = 4;
    private final int mCfaOffsets[];
}
