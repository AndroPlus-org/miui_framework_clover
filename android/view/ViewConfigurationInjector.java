// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import miui.os.Environment;

// Referenced classes of package android.view:
//            ViewConfiguration

public class ViewConfigurationInjector
{

    public ViewConfigurationInjector()
    {
    }

    static ViewConfiguration get(Context context)
    {
        if(Environment.isUsingMiui(context))
        {
            int i = (int)(context.getResources().getDisplayMetrics().density * 100F);
            return (ViewConfiguration)sConfigrations.get(i);
        } else
        {
            return null;
        }
    }

    static int getOverFlingDistance(Context context, int i)
    {
        if(Environment.isUsingMiui(context))
            return (int)(context.getResources().getDimension(0x110b0022) + 0.5F);
        else
            return i;
    }

    static int getOverScrollDistance(Context context, int i)
    {
        if(Environment.isUsingMiui(context))
            return (int)(context.getResources().getDimension(0x110b0021) + 0.5F);
        else
            return i;
    }

    static boolean needMiuiConfiguration(Context context)
    {
        return Environment.isUsingMiui(context);
    }

    static void put(Context context, ViewConfiguration viewconfiguration)
    {
        if(Environment.isUsingMiui(context))
        {
            int i = (int)(context.getResources().getDisplayMetrics().density * 100F);
            sConfigrations.put(i, viewconfiguration);
        }
    }

    private static final SparseArray sConfigrations = new SparseArray(2);

}
