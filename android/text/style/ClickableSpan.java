// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.style;

import android.text.TextPaint;
import android.view.View;

// Referenced classes of package android.text.style:
//            CharacterStyle, UpdateAppearance

public abstract class ClickableSpan extends CharacterStyle
    implements UpdateAppearance
{

    public ClickableSpan()
    {
        int i = sIdCounter;
        sIdCounter = i + 1;
        mId = i;
    }

    public int getId()
    {
        return mId;
    }

    public abstract void onClick(View view);

    public void updateDrawState(TextPaint textpaint)
    {
        textpaint.setColor(textpaint.linkColor);
        textpaint.setUnderlineText(true);
    }

    private static int sIdCounter = 0;
    private int mId;

}
