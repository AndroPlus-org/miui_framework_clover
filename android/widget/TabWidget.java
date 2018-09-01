// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.*;
import android.view.accessibility.AccessibilityEvent;

// Referenced classes of package android.widget:
//            LinearLayout

public class TabWidget extends LinearLayout
    implements android.view.View.OnFocusChangeListener
{
    static interface OnTabSelectionChanged
    {

        public abstract void onTabSelectionChanged(int i, boolean flag);
    }

    private class TabClickListener
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            TabWidget._2D_get0(TabWidget.this).onTabSelectionChanged(mTabIndex, true);
        }

        private final int mTabIndex;
        final TabWidget this$0;

        private TabClickListener(int i)
        {
            this$0 = TabWidget.this;
            super();
            mTabIndex = i;
        }

        TabClickListener(int i, TabClickListener tabclicklistener)
        {
            this(i);
        }
    }


    static OnTabSelectionChanged _2D_get0(TabWidget tabwidget)
    {
        return tabwidget.mSelectionChangedListener;
    }

    public TabWidget(Context context)
    {
        this(context, null);
    }

    public TabWidget(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x1010083);
    }

    public TabWidget(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public TabWidget(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mBounds = new Rect();
        mSelectedTab = -1;
        mDrawBottomStrips = true;
        mImposedTabsHeight = -1;
        attributeset = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.TabWidget, i, j);
        mDrawBottomStrips = attributeset.getBoolean(3, mDrawBottomStrips);
        if(context.getApplicationInfo().targetSdkVersion <= 4)
            i = 1;
        else
            i = 0;
        if(attributeset.hasValueOrEmpty(1))
            mLeftStrip = attributeset.getDrawable(1);
        else
        if(i != 0)
            mLeftStrip = context.getDrawable(0x1080825);
        else
            mLeftStrip = context.getDrawable(0x1080824);
        if(attributeset.hasValueOrEmpty(2))
            mRightStrip = attributeset.getDrawable(2);
        else
        if(i != 0)
            mRightStrip = context.getDrawable(0x1080827);
        else
            mRightStrip = context.getDrawable(0x1080826);
        attributeset.recycle();
        setChildrenDrawingOrderEnabled(true);
    }

    public void addView(View view)
    {
        if(view.getLayoutParams() == null)
        {
            LinearLayout.LayoutParams layoutparams = new LinearLayout.LayoutParams(0, -1, 1.0F);
            layoutparams.setMargins(0, 0, 0, 0);
            view.setLayoutParams(layoutparams);
        }
        view.setFocusable(true);
        view.setClickable(true);
        if(view.getPointerIcon() == null)
            view.setPointerIcon(PointerIcon.getSystemIcon(getContext(), 1002));
        super.addView(view);
        view.setOnClickListener(new TabClickListener(getTabCount() - 1, null));
    }

    public void childDrawableStateChanged(View view)
    {
        if(getTabCount() > 0 && view == getChildTabViewAt(mSelectedTab))
            invalidate();
        super.childDrawableStateChanged(view);
    }

    public void dispatchDraw(Canvas canvas)
    {
        super.dispatchDraw(canvas);
        if(getTabCount() == 0)
            return;
        if(!mDrawBottomStrips)
            return;
        View view = getChildTabViewAt(mSelectedTab);
        Drawable drawable = mLeftStrip;
        Drawable drawable1 = mRightStrip;
        if(drawable != null)
            drawable.setState(view.getDrawableState());
        if(drawable1 != null)
            drawable1.setState(view.getDrawableState());
        if(mStripMoved)
        {
            Rect rect = mBounds;
            rect.left = view.getLeft();
            rect.right = view.getRight();
            int i = getHeight();
            if(drawable != null)
                drawable.setBounds(Math.min(0, rect.left - drawable.getIntrinsicWidth()), i - drawable.getIntrinsicHeight(), rect.left, i);
            if(drawable1 != null)
                drawable1.setBounds(rect.right, i - drawable1.getIntrinsicHeight(), Math.max(getWidth(), rect.right + drawable1.getIntrinsicWidth()), i);
            mStripMoved = false;
        }
        if(drawable != null)
            drawable.draw(canvas);
        if(drawable1 != null)
            drawable1.draw(canvas);
    }

    public void focusCurrentTab(int i)
    {
        int j = mSelectedTab;
        setCurrentTab(i);
        if(j != i)
            getChildTabViewAt(i).requestFocus();
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/TabWidget.getName();
    }

    protected int getChildDrawingOrder(int i, int j)
    {
        if(mSelectedTab == -1)
            return j;
        if(j == i - 1)
            return mSelectedTab;
        if(j >= mSelectedTab)
            return j + 1;
        else
            return j;
    }

    public View getChildTabViewAt(int i)
    {
        return getChildAt(i);
    }

    public Drawable getLeftStripDrawable()
    {
        return mLeftStrip;
    }

    public Drawable getRightStripDrawable()
    {
        return mRightStrip;
    }

    public int getTabCount()
    {
        return getChildCount();
    }

    public boolean isStripEnabled()
    {
        return mDrawBottomStrips;
    }

    void measureChildBeforeLayout(View view, int i, int j, int k, int l, int i1)
    {
        int j1 = j;
        int k1 = l;
        if(!isMeasureWithLargestChildEnabled())
        {
            j1 = j;
            k1 = l;
            if(mImposedTabsHeight >= 0)
            {
                j1 = android.view.View.MeasureSpec.makeMeasureSpec(mImposedTabWidths[i] + k, 0x40000000);
                k1 = android.view.View.MeasureSpec.makeMeasureSpec(mImposedTabsHeight, 0x40000000);
            }
        }
        super.measureChildBeforeLayout(view, i, j1, k, k1, i1);
    }

    void measureHorizontal(int i, int j)
    {
        if(android.view.View.MeasureSpec.getMode(i) == 0)
        {
            super.measureHorizontal(i, j);
            return;
        }
        int k = android.view.View.MeasureSpec.getSize(i);
        int j1 = android.view.View.MeasureSpec.makeSafeMeasureSpec(k, 0);
        mImposedTabsHeight = -1;
        super.measureHorizontal(j1, j);
        int l1 = getMeasuredWidth() - k;
        if(l1 > 0)
        {
            int i2 = getChildCount();
            int k1 = 0;
            int l = 0;
            while(l < i2) 
            {
                if(getChildAt(l).getVisibility() != 8)
                    k1++;
                l++;
            }
            if(k1 > 0)
            {
                if(mImposedTabWidths == null || mImposedTabWidths.length != i2)
                    mImposedTabWidths = new int[i2];
                int i1 = 0;
                while(i1 < i2) 
                {
                    View view = getChildAt(i1);
                    if(view.getVisibility() != 8)
                    {
                        int j2 = view.getMeasuredWidth();
                        int k2 = Math.max(0, j2 - l1 / k1);
                        mImposedTabWidths[i1] = k2;
                        l1 -= j2 - k2;
                        k1--;
                        mImposedTabsHeight = Math.max(mImposedTabsHeight, view.getMeasuredHeight());
                    }
                    i1++;
                }
            }
        }
        super.measureHorizontal(i, j);
    }

    public void onFocusChange(View view, boolean flag)
    {
    }

    public void onInitializeAccessibilityEventInternal(AccessibilityEvent accessibilityevent)
    {
        super.onInitializeAccessibilityEventInternal(accessibilityevent);
        accessibilityevent.setItemCount(getTabCount());
        accessibilityevent.setCurrentItemIndex(mSelectedTab);
    }

    public PointerIcon onResolvePointerIcon(MotionEvent motionevent, int i)
    {
        if(!isEnabled())
            return null;
        else
            return super.onResolvePointerIcon(motionevent, i);
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        mStripMoved = true;
        super.onSizeChanged(i, j, k, l);
    }

    public void removeAllViews()
    {
        super.removeAllViews();
        mSelectedTab = -1;
    }

    public void setCurrentTab(int i)
    {
        while(i < 0 || i >= getTabCount() || i == mSelectedTab) 
            return;
        if(mSelectedTab != -1)
            getChildTabViewAt(mSelectedTab).setSelected(false);
        mSelectedTab = i;
        getChildTabViewAt(mSelectedTab).setSelected(true);
        mStripMoved = true;
    }

    public void setDividerDrawable(int i)
    {
        setDividerDrawable(mContext.getDrawable(i));
    }

    public void setDividerDrawable(Drawable drawable)
    {
        super.setDividerDrawable(drawable);
    }

    public void setEnabled(boolean flag)
    {
        super.setEnabled(flag);
        int i = getTabCount();
        for(int j = 0; j < i; j++)
            getChildTabViewAt(j).setEnabled(flag);

    }

    public void setLeftStripDrawable(int i)
    {
        setLeftStripDrawable(mContext.getDrawable(i));
    }

    public void setLeftStripDrawable(Drawable drawable)
    {
        mLeftStrip = drawable;
        requestLayout();
        invalidate();
    }

    public void setRightStripDrawable(int i)
    {
        setRightStripDrawable(mContext.getDrawable(i));
    }

    public void setRightStripDrawable(Drawable drawable)
    {
        mRightStrip = drawable;
        requestLayout();
        invalidate();
    }

    public void setStripEnabled(boolean flag)
    {
        mDrawBottomStrips = flag;
        invalidate();
    }

    void setTabSelectionListener(OnTabSelectionChanged ontabselectionchanged)
    {
        mSelectionChangedListener = ontabselectionchanged;
    }

    private final Rect mBounds;
    private boolean mDrawBottomStrips;
    private int mImposedTabWidths[];
    private int mImposedTabsHeight;
    private Drawable mLeftStrip;
    private Drawable mRightStrip;
    private int mSelectedTab;
    private OnTabSelectionChanged mSelectionChangedListener;
    private boolean mStripMoved;
}
