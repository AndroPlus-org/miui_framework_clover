// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.transition;

import android.content.Context;
import android.graphics.Path;
import android.util.AttributeSet;

public abstract class PathMotion
{

    public PathMotion()
    {
    }

    public PathMotion(Context context, AttributeSet attributeset)
    {
    }

    public abstract Path getPath(float f, float f1, float f2, float f3);
}
