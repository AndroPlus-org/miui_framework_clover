// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.style;

import android.graphics.Rasterizer;
import android.text.TextPaint;

// Referenced classes of package android.text.style:
//            CharacterStyle, UpdateAppearance

public class RasterizerSpan extends CharacterStyle
    implements UpdateAppearance
{

    public RasterizerSpan(Rasterizer rasterizer)
    {
        mRasterizer = rasterizer;
    }

    public Rasterizer getRasterizer()
    {
        return mRasterizer;
    }

    public void updateDrawState(TextPaint textpaint)
    {
        textpaint.setRasterizer(mRasterizer);
    }

    private Rasterizer mRasterizer;
}
