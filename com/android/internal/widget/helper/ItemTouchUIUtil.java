// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget.helper;

import android.graphics.Canvas;
import android.view.View;
import com.android.internal.widget.RecyclerView;

public interface ItemTouchUIUtil
{

    public abstract void clearView(View view);

    public abstract void onDraw(Canvas canvas, RecyclerView recyclerview, View view, float f, float f1, int i, boolean flag);

    public abstract void onDrawOver(Canvas canvas, RecyclerView recyclerview, View view, float f, float f1, int i, boolean flag);

    public abstract void onSelected(View view);
}
