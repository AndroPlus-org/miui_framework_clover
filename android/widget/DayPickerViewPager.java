// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import com.android.internal.widget.ViewPager;
import java.util.ArrayList;
import java.util.function.Predicate;

// Referenced classes of package android.widget:
//            DayPickerPagerAdapter, SimpleMonthView

class DayPickerViewPager extends ViewPager
{

    public DayPickerViewPager(Context context)
    {
        this(context, null);
    }

    public DayPickerViewPager(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public DayPickerViewPager(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public DayPickerViewPager(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mMatchParentChildren = new ArrayList(1);
    }

    protected View findViewByPredicateTraversal(Predicate predicate, View view)
    {
        if(predicate.test(this))
            return this;
        SimpleMonthView simplemonthview = ((DayPickerPagerAdapter)getAdapter()).getView(getCurrent());
        if(simplemonthview != view && simplemonthview != null)
        {
            View view1 = simplemonthview.findViewByPredicate(predicate);
            if(view1 != null)
                return view1;
        }
        int i = getChildCount();
        for(int j = 0; j < i; j++)
        {
            View view2 = getChildAt(j);
            if(view2 == view || view2 == simplemonthview)
                continue;
            view2 = view2.findViewByPredicate(predicate);
            if(view2 != null)
                return view2;
        }

        return null;
    }

    protected void onMeasure(int i, int j)
    {
        populate();
        int k = getChildCount();
        int l;
        int j1;
        int k1;
        int i2;
        if(android.view.View.MeasureSpec.getMode(i) == 0x40000000)
        {
            if(android.view.View.MeasureSpec.getMode(j) != 0x40000000)
                l = 1;
            else
                l = 0;
        } else
        {
            l = 1;
        }
        j1 = 0;
        k1 = 0;
        i2 = 0;
        for(int k2 = 0; k2 < k;)
        {
            int l2;
            int i3;
            int k3;
label0:
            {
                View view = getChildAt(k2);
                l2 = i2;
                i3 = j1;
                k3 = k1;
                if(view.getVisibility() == 8)
                    break label0;
                measureChild(view, i, j);
                com.android.internal.widget.ViewPager.LayoutParams layoutparams = (com.android.internal.widget.ViewPager.LayoutParams)view.getLayoutParams();
                k1 = Math.max(k1, view.getMeasuredWidth());
                j1 = Math.max(j1, view.getMeasuredHeight());
                i2 = combineMeasuredStates(i2, view.getMeasuredState());
                l2 = i2;
                i3 = j1;
                k3 = k1;
                if(!l)
                    break label0;
                if(layoutparams.width != -1)
                {
                    l2 = i2;
                    i3 = j1;
                    k3 = k1;
                    if(layoutparams.height != -1)
                        break label0;
                }
                mMatchParentChildren.add(view);
                k3 = k1;
                i3 = j1;
                l2 = i2;
            }
            k2++;
            i2 = l2;
            j1 = i3;
            k1 = k3;
        }

        l = getPaddingLeft();
        int j3 = getPaddingRight();
        j1 = Math.max(j1 + (getPaddingTop() + getPaddingBottom()), getSuggestedMinimumHeight());
        j3 = Math.max(k1 + (l + j3), getSuggestedMinimumWidth());
        Drawable drawable = getForeground();
        k1 = j1;
        l = j3;
        if(drawable != null)
        {
            k1 = Math.max(j1, drawable.getMinimumHeight());
            l = Math.max(j3, drawable.getMinimumWidth());
        }
        setMeasuredDimension(resolveSizeAndState(l, i, i2), resolveSizeAndState(k1, j, i2 << 16));
        j1 = mMatchParentChildren.size();
        if(j1 > 1)
        {
            int i1 = 0;
            while(i1 < j1) 
            {
                View view1 = (View)mMatchParentChildren.get(i1);
                com.android.internal.widget.ViewPager.LayoutParams layoutparams1 = (com.android.internal.widget.ViewPager.LayoutParams)view1.getLayoutParams();
                int l1;
                int j2;
                if(layoutparams1.width == -1)
                    j2 = android.view.View.MeasureSpec.makeMeasureSpec(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), 0x40000000);
                else
                    j2 = getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight(), layoutparams1.width);
                if(layoutparams1.height == -1)
                    l1 = android.view.View.MeasureSpec.makeMeasureSpec(getMeasuredHeight() - getPaddingTop() - getPaddingBottom(), 0x40000000);
                else
                    l1 = getChildMeasureSpec(j, getPaddingTop() + getPaddingBottom(), layoutparams1.height);
                view1.measure(j2, l1);
                i1++;
            }
        }
        mMatchParentChildren.clear();
    }

    private final ArrayList mMatchParentChildren;
}
