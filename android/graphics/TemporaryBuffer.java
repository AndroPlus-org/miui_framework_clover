// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;

import com.android.internal.util.ArrayUtils;

public class TemporaryBuffer
{

    public TemporaryBuffer()
    {
    }

    public static char[] obtain(int i)
    {
        android/graphics/TemporaryBuffer;
        JVM INSTR monitorenter ;
        char ac[];
        ac = sTemp;
        sTemp = null;
        android/graphics/TemporaryBuffer;
        JVM INSTR monitorexit ;
        char ac1[];
label0:
        {
            if(ac != null)
            {
                ac1 = ac;
                if(ac.length >= i)
                    break label0;
            }
            ac1 = ArrayUtils.newUnpaddedCharArray(i);
        }
        return ac1;
        Exception exception;
        exception;
        throw exception;
    }

    public static void recycle(char ac[])
    {
        if(ac.length > 1000)
            return;
        android/graphics/TemporaryBuffer;
        JVM INSTR monitorenter ;
        sTemp = ac;
        android/graphics/TemporaryBuffer;
        JVM INSTR monitorexit ;
        return;
        ac;
        throw ac;
    }

    private static char sTemp[] = null;

}
