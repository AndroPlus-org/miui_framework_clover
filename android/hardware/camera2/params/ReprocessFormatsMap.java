// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.params;

import android.hardware.camera2.utils.HashCodeHelpers;
import com.android.internal.util.Preconditions;
import java.util.Arrays;

// Referenced classes of package android.hardware.camera2.params:
//            StreamConfigurationMap

public final class ReprocessFormatsMap
{

    public ReprocessFormatsMap(int ai[])
    {
        Preconditions.checkNotNull(ai, "entry must not be null");
        int i = 0;
        int j = ai.length;
        for(int k = 0; k < ai.length; i++)
        {
            int l = StreamConfigurationMap.checkArgumentFormatInternal(ai[k]);
            j--;
            k++;
            if(j < 1)
                throw new IllegalArgumentException(String.format("Input %x had no output format length listed", new Object[] {
                    Integer.valueOf(l)
                }));
            int i1 = ai[k];
            int j1 = j - 1;
            int k1 = k + 1;
            for(j = 0; j < i1; j++)
                StreamConfigurationMap.checkArgumentFormatInternal(ai[k1 + j]);

            k = k1;
            j = j1;
            if(i1 <= 0)
                continue;
            if(j1 < i1)
                throw new IllegalArgumentException(String.format("Input %x had too few output formats listed (actual: %d, expected: %d)", new Object[] {
                    Integer.valueOf(l), Integer.valueOf(j1), Integer.valueOf(i1)
                }));
            k = k1 + i1;
            j = j1 - i1;
        }

        mEntry = ai;
        mInputCount = i;
    }

    public boolean equals(Object obj)
    {
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        if(obj instanceof ReprocessFormatsMap)
        {
            obj = (ReprocessFormatsMap)obj;
            return Arrays.equals(mEntry, ((ReprocessFormatsMap) (obj)).mEntry);
        } else
        {
            return false;
        }
    }

    public int[] getInputs()
    {
        int ai[] = new int[mInputCount];
        int i = mEntry.length;
        int j = 0;
        for(int k = 0; j < mEntry.length; k++)
        {
            int l = mEntry[j];
            i--;
            j++;
            if(i < 1)
                throw new AssertionError(String.format("Input %x had no output format length listed", new Object[] {
                    Integer.valueOf(l)
                }));
            int i1 = mEntry[j];
            int j1 = i - 1;
            int k1 = j + 1;
            j = k1;
            i = j1;
            if(i1 > 0)
            {
                if(j1 < i1)
                    throw new AssertionError(String.format("Input %x had too few output formats listed (actual: %d, expected: %d)", new Object[] {
                        Integer.valueOf(l), Integer.valueOf(j1), Integer.valueOf(i1)
                    }));
                j = k1 + i1;
                i = j1 - i1;
            }
            ai[k] = l;
        }

        return StreamConfigurationMap.imageFormatToPublic(ai);
    }

    public int[] getOutputs(int i)
    {
        int j = mEntry.length;
        for(int k = 0; k < mEntry.length;)
        {
            int l = mEntry[k];
            int i1 = j - 1;
            k++;
            if(i1 < 1)
                throw new AssertionError(String.format("Input %x had no output format length listed", new Object[] {
                    Integer.valueOf(i)
                }));
            j = mEntry[k];
            i1--;
            k++;
            if(j > 0 && i1 < j)
                throw new AssertionError(String.format("Input %x had too few output formats listed (actual: %d, expected: %d)", new Object[] {
                    Integer.valueOf(i), Integer.valueOf(i1), Integer.valueOf(j)
                }));
            if(l == i)
            {
                int ai[] = new int[j];
                for(i = 0; i < j; i++)
                    ai[i] = mEntry[k + i];

                return StreamConfigurationMap.imageFormatToPublic(ai);
            }
            k += j;
            j = i1 - j;
        }

        throw new IllegalArgumentException(String.format("Input format %x was not one in #getInputs", new Object[] {
            Integer.valueOf(i)
        }));
    }

    public int hashCode()
    {
        return HashCodeHelpers.hashCode(mEntry);
    }

    private final int mEntry[];
    private final int mInputCount;
}
