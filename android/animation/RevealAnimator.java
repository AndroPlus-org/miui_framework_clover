// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.animation;

import android.view.RenderNodeAnimator;
import android.view.View;

public class RevealAnimator extends RenderNodeAnimator
{

    public RevealAnimator(View view, int i, int j, float f, float f1)
    {
        super(i, j, f, f1);
        mClipView = view;
        setTarget(mClipView);
    }

    protected void onFinished()
    {
        mClipView.setRevealClip(false, 0.0F, 0.0F, 0.0F);
        super.onFinished();
    }

    private View mClipView;
}
