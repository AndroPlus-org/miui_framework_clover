// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.inputmethodservice;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;

public class CompactExtractEditLayout extends LinearLayout
{

    public CompactExtractEditLayout(Context context)
    {
        super(context);
    }

    public CompactExtractEditLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public CompactExtractEditLayout(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
    }

    private int applyFractionInt(int i, int j)
    {
        return Math.round(getResources().getFraction(i, j, j));
    }

    private void applyProportionalLayout(int i, int j)
    {
        if(getResources().getConfiguration().isScreenRound())
            setGravity(80);
        setLayoutHeight(this, applyFractionInt(0x1130006, j));
        setPadding(applyFractionInt(0x1130007, i), 0, applyFractionInt(0x1130009, i), 0);
        setLayoutMarginBottom(mInputExtractEditText, applyFractionInt(0x113000a, j));
        setLayoutMarginBottom(mInputExtractAccessories, applyFractionInt(0x1130005, j));
    }

    private static void setLayoutHeight(View view, int i)
    {
        android.view.ViewGroup.LayoutParams layoutparams = view.getLayoutParams();
        layoutparams.height = i;
        view.setLayoutParams(layoutparams);
    }

    private static void setLayoutMarginBottom(View view, int i)
    {
        android.view.ViewGroup.MarginLayoutParams marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)view.getLayoutParams();
        marginlayoutparams.bottomMargin = i;
        view.setLayoutParams(marginlayoutparams);
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        if(mPerformLayoutChanges)
        {
            Object obj = getResources();
            Configuration configuration = ((Resources) (obj)).getConfiguration();
            obj = ((Resources) (obj)).getDisplayMetrics();
            int i = ((DisplayMetrics) (obj)).widthPixels;
            int j = ((DisplayMetrics) (obj)).heightPixels;
            int k = j;
            if(configuration.isScreenRound())
            {
                k = j;
                if(j < i)
                    k = i;
            }
            applyProportionalLayout(i, k);
        }
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        mInputExtractEditText = findViewById(0x1020025);
        mInputExtractAccessories = findViewById(0x10202ac);
        mInputExtractAction = findViewById(0x10202ad);
        if(mInputExtractEditText != null && mInputExtractAccessories != null && mInputExtractAction != null)
            mPerformLayoutChanges = true;
    }

    private View mInputExtractAccessories;
    private View mInputExtractAction;
    private View mInputExtractEditText;
    private boolean mPerformLayoutChanges;
}
