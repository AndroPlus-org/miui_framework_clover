// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text;

import android.graphics.BaseCanvas;
import android.graphics.Paint;

public interface GraphicsOperations
    extends CharSequence
{

    public abstract void drawText(BaseCanvas basecanvas, int i, int j, float f, float f1, Paint paint);

    public abstract void drawTextRun(BaseCanvas basecanvas, int i, int j, int k, int l, float f, float f1, 
            boolean flag, Paint paint);

    public abstract float getTextRunAdvances(int i, int j, int k, int l, boolean flag, float af[], int i1, 
            Paint paint);

    public abstract int getTextRunCursor(int i, int j, int k, int l, int i1, Paint paint);

    public abstract int getTextWidths(int i, int j, float af[], Paint paint);

    public abstract float measureText(int i, int j, Paint paint);
}
