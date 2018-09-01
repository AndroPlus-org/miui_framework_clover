// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.transition;

import android.util.*;

class TransitionValuesMaps
{

    TransitionValuesMaps()
    {
        viewValues = new ArrayMap();
        idValues = new SparseArray();
        itemIdValues = new LongSparseArray();
        nameValues = new ArrayMap();
    }

    SparseArray idValues;
    LongSparseArray itemIdValues;
    ArrayMap nameValues;
    ArrayMap viewValues;
}
