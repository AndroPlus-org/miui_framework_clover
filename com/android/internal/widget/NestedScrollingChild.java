// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;


public interface NestedScrollingChild
{

    public abstract boolean dispatchNestedFling(float f, float f1, boolean flag);

    public abstract boolean dispatchNestedPreFling(float f, float f1);

    public abstract boolean dispatchNestedPreScroll(int i, int j, int ai[], int ai1[]);

    public abstract boolean dispatchNestedScroll(int i, int j, int k, int l, int ai[]);

    public abstract boolean hasNestedScrollingParent();

    public abstract boolean isNestedScrollingEnabled();

    public abstract void setNestedScrollingEnabled(boolean flag);

    public abstract boolean startNestedScroll(int i);

    public abstract void stopNestedScroll();
}
