// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

// Referenced classes of package android.widget:
//            ViewAnimator

public class ViewSwitcher extends ViewAnimator
{
    public static interface ViewFactory
    {

        public abstract View makeView();
    }


    public ViewSwitcher(Context context)
    {
        super(context);
    }

    public ViewSwitcher(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    private View obtainView()
    {
        View view = mFactory.makeView();
        FrameLayout.LayoutParams layoutparams = (FrameLayout.LayoutParams)view.getLayoutParams();
        FrameLayout.LayoutParams layoutparams1 = layoutparams;
        if(layoutparams == null)
            layoutparams1 = new FrameLayout.LayoutParams(-1, -2);
        addView(view, layoutparams1);
        return view;
    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutparams)
    {
        if(getChildCount() >= 2)
        {
            throw new IllegalStateException("Can't add more than 2 views to a ViewSwitcher");
        } else
        {
            super.addView(view, i, layoutparams);
            return;
        }
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/ViewSwitcher.getName();
    }

    public View getNextView()
    {
        int i;
        if(mWhichChild == 0)
            i = 1;
        else
            i = 0;
        return getChildAt(i);
    }

    public void reset()
    {
        mFirstTime = true;
        View view = getChildAt(0);
        if(view != null)
            view.setVisibility(8);
        view = getChildAt(1);
        if(view != null)
            view.setVisibility(8);
    }

    public void setFactory(ViewFactory viewfactory)
    {
        mFactory = viewfactory;
        obtainView();
        obtainView();
    }

    ViewFactory mFactory;
}
