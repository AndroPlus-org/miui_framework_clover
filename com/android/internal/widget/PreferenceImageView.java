// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class PreferenceImageView extends ImageView
{

    public PreferenceImageView(Context context)
    {
        this(context, null);
    }

    public PreferenceImageView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public PreferenceImageView(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public PreferenceImageView(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
    }

    protected void onMeasure(int i, int j)
    {
        int i1;
label0:
        {
            int k = android.view.View.MeasureSpec.getMode(i);
            if(k != 0x80000000)
            {
                i1 = i;
                if(k != 0)
                    break label0;
            }
            int j1 = android.view.View.MeasureSpec.getSize(i);
            int l1 = getMaxWidth();
            i1 = i;
            if(l1 == 0x7fffffff)
                break label0;
            if(l1 >= j1)
            {
                i1 = i;
                if(k != 0)
                    break label0;
            }
            i1 = android.view.View.MeasureSpec.makeMeasureSpec(l1, 0x80000000);
        }
label1:
        {
            int i2 = android.view.View.MeasureSpec.getMode(j);
            if(i2 != 0x80000000)
            {
                i = j;
                if(i2 != 0)
                    break label1;
            }
            int l = android.view.View.MeasureSpec.getSize(j);
            int k1 = getMaxHeight();
            i = j;
            if(k1 == 0x7fffffff)
                break label1;
            if(k1 >= l)
            {
                i = j;
                if(i2 != 0)
                    break label1;
            }
            i = android.view.View.MeasureSpec.makeMeasureSpec(k1, 0x80000000);
        }
        super.onMeasure(i1, i);
    }
}
