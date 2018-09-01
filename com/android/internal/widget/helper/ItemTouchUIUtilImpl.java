// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget.helper;

import android.graphics.Canvas;
import android.view.View;
import com.android.internal.widget.RecyclerView;

// Referenced classes of package com.android.internal.widget.helper:
//            ItemTouchUIUtil

class ItemTouchUIUtilImpl
    implements ItemTouchUIUtil
{

    ItemTouchUIUtilImpl()
    {
    }

    private float findMaxElevation(RecyclerView recyclerview, View view)
    {
        int i = recyclerview.getChildCount();
        float f = 0.0F;
        int j = 0;
        while(j < i) 
        {
            View view1 = recyclerview.getChildAt(j);
            float f1;
            if(view1 == view)
            {
                f1 = f;
            } else
            {
                float f2 = view1.getElevation();
                f1 = f;
                if(f2 > f)
                    f1 = f2;
            }
            j++;
            f = f1;
        }
        return f;
    }

    public void clearView(View view)
    {
        Object obj = view.getTag(0x10202c5);
        if(obj != null && (obj instanceof Float))
            view.setElevation(((Float)obj).floatValue());
        view.setTag(0x10202c5, null);
        view.setTranslationX(0.0F);
        view.setTranslationY(0.0F);
    }

    public void onDraw(Canvas canvas, RecyclerView recyclerview, View view, float f, float f1, int i, boolean flag)
    {
        if(flag && view.getTag(0x10202c5) == null)
        {
            float f2 = view.getElevation();
            view.setElevation(1.0F + findMaxElevation(recyclerview, view));
            view.setTag(0x10202c5, Float.valueOf(f2));
        }
        view.setTranslationX(f);
        view.setTranslationY(f1);
    }

    public void onDrawOver(Canvas canvas, RecyclerView recyclerview, View view, float f, float f1, int i, boolean flag)
    {
    }

    public void onSelected(View view)
    {
    }
}
