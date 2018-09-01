// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.util;

import android.content.res.Resources;
import android.util.TypedValue;

public class ResourceMapper
{

    public ResourceMapper()
    {
    }

    public static int resolveReference(Resources resources, int i)
    {
        TypedValue typedvalue = new TypedValue();
        resources.getValue(i, typedvalue, true);
        if(typedvalue.resourceId != 0)
            i = typedvalue.resourceId;
        return i;
    }
}
