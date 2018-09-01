// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.legacy;

import com.android.internal.util.Preconditions;
import java.util.*;

public class SizeAreaComparator
    implements Comparator
{

    public SizeAreaComparator()
    {
    }

    public static android.hardware.Camera.Size findLargestByArea(List list)
    {
        Preconditions.checkNotNull(list, "sizes must not be null");
        return (android.hardware.Camera.Size)Collections.max(list, new SizeAreaComparator());
    }

    public int compare(android.hardware.Camera.Size size, android.hardware.Camera.Size size1)
    {
        boolean flag = true;
        int i = 1;
        Preconditions.checkNotNull(size, "size must not be null");
        Preconditions.checkNotNull(size1, "size2 must not be null");
        if(size.equals(size1))
            return 0;
        long l = size.width;
        long l1 = size1.width;
        long l2 = l * (long)size.height;
        long l3 = l1 * (long)size1.height;
        if(l2 == l3)
        {
            if(l <= l1)
                i = -1;
            return i;
        }
        if(l2 > l3)
            i = ((flag) ? 1 : 0);
        else
            i = -1;
        return i;
    }

    public volatile int compare(Object obj, Object obj1)
    {
        return compare((android.hardware.Camera.Size)obj, (android.hardware.Camera.Size)obj1);
    }
}
