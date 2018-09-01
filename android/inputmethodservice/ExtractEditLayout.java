// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.inputmethodservice;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;

public class ExtractEditLayout extends LinearLayout
{

    public ExtractEditLayout(Context context)
    {
        super(context);
    }

    public ExtractEditLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public void onFinishInflate()
    {
        super.onFinishInflate();
        mExtractActionButton = (Button)findViewById(0x10202ad);
    }

    Button mExtractActionButton;
}
