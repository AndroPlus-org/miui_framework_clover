// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.utils;


public final class HashCodeHelpers
{

    public HashCodeHelpers()
    {
    }

    public static transient int hashCode(float af[])
    {
        int i = 0;
        if(af == null)
            return 0;
        int j = 1;
        for(int k = af.length; i < k; i++)
            j = (j << 5) - j ^ Float.floatToIntBits(af[i]);

        return j;
    }

    public static transient int hashCode(int ai[])
    {
        int i = 0;
        if(ai == null)
            return 0;
        int j = 1;
        for(int k = ai.length; i < k; i++)
            j = (j << 5) - j ^ ai[i];

        return j;
    }

    public static transient int hashCodeGeneric(Object aobj[])
    {
        int i = 0;
        if(aobj == null)
            return 0;
        int j = 1;
        int k = aobj.length;
        while(i < k) 
        {
            Object obj = aobj[i];
            int l;
            if(obj == null)
                l = 0;
            else
                l = obj.hashCode();
            j = (j << 5) - j ^ l;
            i++;
        }
        return j;
    }
}
