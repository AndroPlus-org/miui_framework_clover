// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.res;


public final class ResourceId
{

    public ResourceId()
    {
    }

    public static boolean isValid(int i)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(i != -1)
        {
            flag1 = flag;
            if((0xff000000 & i) != 0)
            {
                flag1 = flag;
                if((0xff0000 & i) != 0)
                    flag1 = true;
            }
        }
        return flag1;
    }

    public static final int ID_NULL = 0;
}
