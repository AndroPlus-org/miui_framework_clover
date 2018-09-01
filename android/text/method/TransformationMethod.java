// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.method;

import android.graphics.Rect;
import android.view.View;

public interface TransformationMethod
{

    public abstract CharSequence getTransformation(CharSequence charsequence, View view);

    public abstract void onFocusChanged(View view, CharSequence charsequence, boolean flag, int i, Rect rect);
}
