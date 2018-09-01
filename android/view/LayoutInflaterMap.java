// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.content.Context;
import android.util.SparseArray;
import miui.os.Environment;
import miui.util.ResourceMapper;

public class LayoutInflaterMap
{

    public LayoutInflaterMap()
    {
    }

    private static void buildLayoutMap(Context context)
    {
        if(sLayoutMap != null)
            return;
        android/view/LayoutInflaterMap;
        JVM INSTR monitorenter ;
        if(sLayoutMap != null) goto _L2; else goto _L1
_L1:
        SparseArray sparsearray;
        sparsearray = JVM INSTR new #25  <Class SparseArray>;
        sparsearray.SparseArray();
        int i = 0;
_L5:
        int j;
        if(i >= sLayoutPairs.length)
            break MISSING_BLOCK_LABEL_88;
        if(!needResolveReference(sLayoutPairs[i]))
            break MISSING_BLOCK_LABEL_79;
        j = ResourceMapper.resolveReference(context.getResources(), sLayoutPairs[i]);
_L3:
        sparsearray.put(j, Integer.valueOf(sLayoutPairs[i + 1]));
        i += 2;
        continue; /* Loop/switch isn't completed */
        j = sLayoutPairs[i];
          goto _L3
        sLayoutMap = sparsearray;
_L2:
        android/view/LayoutInflaterMap;
        JVM INSTR monitorexit ;
        return;
        context;
        throw context;
        if(true) goto _L5; else goto _L4
_L4:
    }

    static int getResourceId(Context context, int i)
    {
        int j = i;
        int k = j;
        if(Environment.isUsingMiui(context))
        {
            buildLayoutMap(context);
            context = (Integer)sLayoutMap.get(i);
            k = j;
            if(context != null)
                k = context.intValue();
        }
        return k;
    }

    private static boolean needResolveReference(int i)
    {
        boolean flag;
        if((0xff000000 & i) != 0x1000000)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static volatile SparseArray sLayoutMap;
    private static final int sLayoutPairs[] = {
        0x11030029, 0x1103000d
    };

}
