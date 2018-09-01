// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.view.View;

// Referenced classes of package com.android.internal.widget:
//            OrientationHelper

class ScrollbarHelper
{

    ScrollbarHelper()
    {
    }

    static int computeScrollExtent(RecyclerView.State state, OrientationHelper orientationhelper, View view, View view1, RecyclerView.LayoutManager layoutmanager, boolean flag)
    {
        while(layoutmanager.getChildCount() == 0 || state.getItemCount() == 0 || view == null || view1 == null) 
            return 0;
        if(!flag)
        {
            return Math.abs(layoutmanager.getPosition(view) - layoutmanager.getPosition(view1)) + 1;
        } else
        {
            int i = orientationhelper.getDecoratedEnd(view1);
            int j = orientationhelper.getDecoratedStart(view);
            return Math.min(orientationhelper.getTotalSpace(), i - j);
        }
    }

    static int computeScrollOffset(RecyclerView.State state, OrientationHelper orientationhelper, View view, View view1, RecyclerView.LayoutManager layoutmanager, boolean flag, boolean flag1)
    {
        while(layoutmanager.getChildCount() == 0 || state.getItemCount() == 0 || view == null || view1 == null) 
            return 0;
        int i = Math.min(layoutmanager.getPosition(view), layoutmanager.getPosition(view1));
        int k = Math.max(layoutmanager.getPosition(view), layoutmanager.getPosition(view1));
        if(flag1)
            k = Math.max(0, state.getItemCount() - k - 1);
        else
            k = Math.max(0, i);
        if(!flag)
        {
            return k;
        } else
        {
            int j = Math.abs(orientationhelper.getDecoratedEnd(view1) - orientationhelper.getDecoratedStart(view));
            int l = Math.abs(layoutmanager.getPosition(view) - layoutmanager.getPosition(view1));
            float f = (float)j / (float)(l + 1);
            return Math.round((float)k * f + (float)(orientationhelper.getStartAfterPadding() - orientationhelper.getDecoratedStart(view)));
        }
    }

    static int computeScrollRange(RecyclerView.State state, OrientationHelper orientationhelper, View view, View view1, RecyclerView.LayoutManager layoutmanager, boolean flag)
    {
        while(layoutmanager.getChildCount() == 0 || state.getItemCount() == 0 || view == null || view1 == null) 
            return 0;
        if(!flag)
        {
            return state.getItemCount();
        } else
        {
            int i = orientationhelper.getDecoratedEnd(view1);
            int j = orientationhelper.getDecoratedStart(view);
            int k = Math.abs(layoutmanager.getPosition(view) - layoutmanager.getPosition(view1));
            return (int)(((float)(i - j) / (float)(k + 1)) * (float)state.getItemCount());
        }
    }
}
