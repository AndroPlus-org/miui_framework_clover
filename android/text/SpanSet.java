// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text;

import java.lang.reflect.Array;
import java.util.Arrays;

// Referenced classes of package android.text:
//            Spanned

public class SpanSet
{

    SpanSet(Class class1)
    {
        classType = class1;
        numberOfSpans = 0;
    }

    int getNextTransition(int i, int j)
    {
        for(int k = 0; k < numberOfSpans; k++)
        {
            int l = spanStarts[k];
            int i1 = spanEnds[k];
            int j1 = j;
            if(l > i)
            {
                j1 = j;
                if(l < j)
                    j1 = l;
            }
            j = j1;
            if(i1 <= i)
                continue;
            j = j1;
            if(i1 < j1)
                j = i1;
        }

        return j;
    }

    public boolean hasSpansIntersecting(int i, int j)
    {
        for(int k = 0; k < numberOfSpans;)
            if(spanStarts[k] >= j || spanEnds[k] <= i)
                k++;
            else
                return true;

        return false;
    }

    public void init(Spanned spanned, int i, int j)
    {
        Object aobj[] = spanned.getSpans(i, j, classType);
        int k = aobj.length;
        if(k > 0 && (spans == null || spans.length < k))
        {
            spans = (Object[])Array.newInstance(classType, k);
            spanStarts = new int[k];
            spanEnds = new int[k];
            spanFlags = new int[k];
        }
        j = numberOfSpans;
        numberOfSpans = 0;
        i = 0;
        while(i < k) 
        {
            Object obj = aobj[i];
            int l = spanned.getSpanStart(obj);
            int i1 = spanned.getSpanEnd(obj);
            if(l != i1)
            {
                int j1 = spanned.getSpanFlags(obj);
                spans[numberOfSpans] = obj;
                spanStarts[numberOfSpans] = l;
                spanEnds[numberOfSpans] = i1;
                spanFlags[numberOfSpans] = j1;
                numberOfSpans = numberOfSpans + 1;
            }
            i++;
        }
        if(numberOfSpans < j)
            Arrays.fill(spans, numberOfSpans, j, null);
    }

    public void recycle()
    {
        if(spans != null)
            Arrays.fill(spans, 0, numberOfSpans, null);
    }

    private final Class classType;
    int numberOfSpans;
    int spanEnds[];
    int spanFlags[];
    int spanStarts[];
    Object spans[];
}
