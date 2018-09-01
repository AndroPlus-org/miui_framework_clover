// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;

import android.util.Log;
import java.util.Arrays;

// Referenced classes of package com.android.internal.util:
//            Preconditions

public class ExponentiallyBucketedHistogram
{

    public ExponentiallyBucketedHistogram(int i)
    {
        mData = new int[Preconditions.checkArgumentInRange(i, 1, 31, "numBuckets")];
    }

    public void add(int i)
    {
        if(i <= 0)
        {
            int ai[] = mData;
            ai[0] = ai[0] + 1;
        } else
        {
            int ai1[] = mData;
            i = Math.min(mData.length - 1, 32 - Integer.numberOfLeadingZeros(i));
            ai1[i] = ai1[i] + 1;
        }
    }

    public void log(String s, CharSequence charsequence)
    {
        charsequence = new StringBuilder(charsequence);
        charsequence.append('[');
        int i = 0;
        while(i < mData.length) 
        {
            if(i != 0)
                charsequence.append(", ");
            if(i < mData.length - 1)
            {
                charsequence.append("<");
                charsequence.append(1 << i);
            } else
            {
                charsequence.append(">=");
                charsequence.append(1 << i - 1);
            }
            charsequence.append(": ");
            charsequence.append(mData[i]);
            i++;
        }
        charsequence.append("]");
        Log.d(s, charsequence.toString());
    }

    public void reset()
    {
        Arrays.fill(mData, 0);
    }

    private final int mData[];
}
