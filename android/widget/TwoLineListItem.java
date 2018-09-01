// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

// Referenced classes of package android.widget:
//            RelativeLayout, TextView

public class TwoLineListItem extends RelativeLayout
{

    public TwoLineListItem(Context context)
    {
        this(context, null, 0);
    }

    public TwoLineListItem(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public TwoLineListItem(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public TwoLineListItem(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.TwoLineListItem, i, j).recycle();
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/TwoLineListItem.getName();
    }

    public TextView getText1()
    {
        return mText1;
    }

    public TextView getText2()
    {
        return mText2;
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        mText1 = (TextView)findViewById(0x1020014);
        mText2 = (TextView)findViewById(0x1020015);
    }

    private TextView mText1;
    private TextView mText2;
}
