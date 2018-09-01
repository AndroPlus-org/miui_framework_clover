// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

public class BackgroundFallback
{

    public BackgroundFallback()
    {
    }

    public void draw(ViewGroup viewgroup, ViewGroup viewgroup1, Canvas canvas, View view)
    {
        int i;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        if(!hasFallback())
            return;
        i = viewgroup.getWidth();
        j = viewgroup.getHeight();
        k = i;
        l = j;
        i1 = 0;
        j1 = 0;
        k1 = viewgroup1.getChildCount();
        l1 = 0;
_L5:
        View view1;
        if(l1 >= k1)
            break MISSING_BLOCK_LABEL_251;
        view1 = viewgroup1.getChildAt(l1);
        viewgroup = view1.getBackground();
        if(view1 != view) goto _L2; else goto _L1
_L1:
        if(viewgroup != null || !(view1 instanceof ViewGroup) || ((ViewGroup)view1).getChildCount() != 0) goto _L4; else goto _L3
_L3:
        int i2;
        int j2;
        int k2;
        int l2;
        i2 = l;
        j2 = i1;
        k2 = k;
        l2 = j1;
_L7:
        l1++;
        j1 = l2;
        k = k2;
        i1 = j2;
        l = i2;
          goto _L5
_L2:
        l2 = j1;
        k2 = k;
        j2 = i1;
        i2 = l;
        if(view1.getVisibility() != 0) goto _L7; else goto _L6
_L6:
        l2 = j1;
        k2 = k;
        j2 = i1;
        i2 = l;
        if(viewgroup == null) goto _L7; else goto _L8
_L8:
        l2 = j1;
        k2 = k;
        j2 = i1;
        i2 = l;
        if(viewgroup.getOpacity() != -1) goto _L7; else goto _L4
_L4:
        k2 = Math.min(k, view1.getLeft());
        i2 = Math.min(l, view1.getTop());
        j2 = Math.max(i1, view1.getRight());
        l2 = Math.max(j1, view1.getBottom());
          goto _L7
        if(k >= i1 || l >= j1)
            return;
        if(l > 0)
        {
            mBackgroundFallback.setBounds(0, 0, i, l);
            mBackgroundFallback.draw(canvas);
        }
        if(k > 0)
        {
            mBackgroundFallback.setBounds(0, l, k, j);
            mBackgroundFallback.draw(canvas);
        }
        if(i1 < i)
        {
            mBackgroundFallback.setBounds(i1, l, i, j);
            mBackgroundFallback.draw(canvas);
        }
        if(j1 < j)
        {
            mBackgroundFallback.setBounds(k, j1, i1, j);
            mBackgroundFallback.draw(canvas);
        }
        return;
    }

    public boolean hasFallback()
    {
        boolean flag;
        if(mBackgroundFallback != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void setDrawable(Drawable drawable)
    {
        mBackgroundFallback = drawable;
    }

    private Drawable mBackgroundFallback;
}
